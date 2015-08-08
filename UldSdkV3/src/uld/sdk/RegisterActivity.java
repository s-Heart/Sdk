package uld.sdk;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import uld.sdk.R;
import wh.member.model.User;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Selection;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends BaseActivity {

	private Button btnLogin = null;
	private Button btnRegister = null;

	private EditText txtUserName = null;
	private EditText txtPassword = null;

	private boolean bIsExistUserName = false;
	uld.sdk.model.GameInterface gameInterface = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int widthDivideDensity = (int) (outMetrics.widthPixels / outMetrics.scaledDensity);

		if (widthDivideDensity <= valueOfWidthDivideDensity) {
			setContentView(R.layout.activity_register_320);
		} else {
			setContentView(R.layout.activity_register);
		}

		getDataFromIntent();

		// 获取视图
		findViews();

		// 设置监听事件
		setListeners();
	}

	private void getDataFromIntent() {
		Intent intent = getIntent();
		if (null != intent) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				setGameId(Utility.getInt(bundle.getString("GameId")));
			}
		}
	}

	private void findViews() {
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnRegister = (Button) findViewById(R.id.btnRegister);

		txtUserName = (EditText) findViewById(R.id.txtUserName);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
	}

	private void setListeners() {
		btnLogin.setOnClickListener(new LoginListener());
		btnRegister.setOnClickListener(new RegisterListener());

		txtUserName.setOnFocusChangeListener(new UserNameFocusChangeListener());
		txtPassword.setOnFocusChangeListener(new PasswordFocusChangelistener());
	}

	class LoginListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	class RegisterListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			final String userName = txtUserName.getText().toString();
			final String password = txtPassword.getText().toString();

			if (userName.length() < userName.getBytes().length) {
				CheckEditText(txtUserName, "用户名不合法");
				return;
			}

			if (Utility.isEmpty(userName) || userName.length() < 6 || userName.length() > 20) {
				CheckEditText(txtUserName, R.string.uld_usernameerrmsg);
				return;
			} else if (Utility.isEmpty(password)) {
				CheckEditText(txtPassword, R.string.uld_passwordemptymsg);
				return;
			} else if (password.length() < 6 || password.length() > 20) {
				CheckEditText(txtPassword, R.string.uld_passworderrmsg);
				return;
			} else if (bIsExistUserName) {
				CheckEditText(txtUserName, "用户名已经存在");
				return;
			}

			dialog = showDialog();
			new Thread() {
				public void run() {
					User user = new User();
					user.setUserName(userName);
					user.setPassword(Utility.encodeMD5(password));
					user.setRawPassword(password);
					user.setChannelId(BaseActivity.getChannelId());
					user.setChannelSubId(BaseActivity.getChannelSubId());
					user.setRegisterGameId(BaseActivity.getGameId());
					user.setRegisterServerId(BaseActivity.getServerId());
					user.setMobileDeviceId(BaseActivity.getMobileDeviceId());
					user.setStatisticAnalysisId(BaseActivity.getStatisticAnalysisId());
					user.setMobilePhone(BaseActivity.getMobilePhone());

					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();

					MessageBody messageBody = new MessageBody();
					messageBody.setClassPathName("uld.sdk.bll.User");
					messageBody.setMethodName("createUpdate");
					messageBody.setParmTypeList(new Class<?>[] { String.class, User.class });
					messageBody.setParmList(new Object[] { BaseActivity.getDeviceId(), user });
					MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
					dialog.dismiss();
					if (messageReturn != null) {
						if (messageReturn.getRetObject() != null) {
							gameInterface = (uld.sdk.model.GameInterface) messageReturn.getRetObject();
						}
						if (messageReturn.getErrNo() == 0) {
							if (null == gameInterface) {
								showToastThread("注册失败，请重试", Toast.LENGTH_LONG);
							} else {
								setUserId(gameInterface.getUserId());
								setRechargeRate(gameInterface.getRechargeRate());
								setRechargeUnit(gameInterface.getRechargeUnit());
								setContent(gameInterface.getStrBundleMap().get("Content"));

								if (Utility.isEmpty(gameInterface.getUserName())) {
									gameInterface.setUserName(userName);
								}

								String[] keys = new String[] { "UserName", "Password" };
								String[] values = new String[] { userName, password };
								setSharedData(SHAREDUSERNAME, keys, values);
								setSharedUsernamesAndPwds(SHAREDUSERNAME, userName, password);

								finish();
							}
						} else {
							showToastThread(messageReturn.getErrMsg(), Toast.LENGTH_LONG);
						}
					} else {
						showToastThread("处理消息失败", Toast.LENGTH_LONG);
					}
				}
			}.start();
		}
	}

	class UserNameFocusChangeListener implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (!hasFocus) {
				final String userName = txtUserName.getText().toString();
				if (Utility.isEmpty(userName) || userName.length() < 6 || userName.length() > 20) {
					CheckEditText(txtUserName, R.string.uld_usernameerrmsg);
				} else {
					txtUserName.setTextColor(getResources().getColor(R.color.uld_okmsgcolor));

					new Thread() {
						public void run() {
							MessageHeader messageHeader = new MessageHeader();
							messageHeader.init();

							MessageBody messageBody = new MessageBody();
							messageBody.setClassPathName("wh.member.bll.User");
							messageBody.setMethodName("checkUserName");
							messageBody.setParmTypeList(new Class<?>[] { String.class });
							messageBody.setParmList(new Object[] { userName });
							MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
							if (messageReturn != null) {
								bIsExistUserName = Boolean.valueOf(messageReturn.getRetObject().toString());
								if (bIsExistUserName) {
									txtUserName.post(new Runnable() {
										@Override
										public void run() {
											txtUserName.setTextColor(getResources().getColor(R.color.uld_errmsgcolor));
										}
									});
									showToastThread("用户名已经存在", Toast.LENGTH_SHORT);
								}
							}
						}
					}.start();
				}
			} else {
				txtUserName.setTextColor(getResources().getColor(R.color.uld_tvcolor));
				Selection.setSelection(txtUserName.getText(), txtUserName.getText().length());
			}
		}
	}

	class PasswordFocusChangelistener implements OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (!hasFocus) {
				String password = txtPassword.getText().toString();
				if (Utility.isEmpty(password)) {
					CheckEditText(txtPassword, R.string.uld_passwordemptymsg);
				} else if (password.length() < 6 || password.length() > 20) {
					CheckEditText(txtPassword, R.string.uld_passworderrmsg);
				} else {
					txtPassword.setTextColor(getResources().getColor(R.color.uld_okmsgcolor));
				}
			} else {
				txtPassword.setTextColor(getResources().getColor(R.color.uld_tvcolor));
				Selection.setSelection(txtPassword.getText(), txtPassword.getText().length());
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		// 设置输入的数据到SharedPreferences
		setDataToSharedPreferences();
	}

	@Override
	protected void onResume() {
		super.onResume();

		// 获取数据从SharedPreferences
		getDataFromSharedPreferences();
	}

	private void setDataToSharedPreferences() {
		String userName = txtUserName.getText().toString();
		String[] keys = null;
		String[] values = null;

		keys = new String[] { "RegUserName", "RegPassword" };
		values = new String[] { userName, "" };
		setSharedData(SHAREDUSERNAME, keys, values);
	}

	private void getDataFromSharedPreferences() {
		SharedPreferences sharedPreferences = getSharedData(SHAREDUSERNAME);
		txtUserName.setText(sharedPreferences.getString("RegUserName", ""));

		if (txtUserName.isFocused()) {
			Selection.setSelection(txtUserName.getText(), txtUserName.getText().length());
		}
	}

	@Override
	public void finish() {
		if (gameInterface != null) {
			setResult(RESULT_OK, getIntent(gameInterface));
		} else {
			setResult(RESULT_OK, null);
		}
		super.finish();
	}
}
