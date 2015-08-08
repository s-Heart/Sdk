package uld.sdk;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import uld.sdk.usercontrols.LinkTextView;
import uld.sdk.R;
import wh.member.model.User;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.Selection;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

	private Button btnClose = null;
	private Button btnRegister = null;
	private Button btnRegisterQuick = null;
	private Button btnLogin = null;
	private LinkTextView ltvForgetPassword = null;

	private CheckBox cbRememberPassword = null;

	private EditText txtUserName = null;
	private EditText txtPassword = null;
	private Intent loginIntent = null;

	private Spinner spUserNames = null;
	private String[] historyUserNames = null;
	private String[] historyPwds = null;

	private static LoginCallBackListener loginCallBackListener = null;

	public static void show(Context context, int gameId, int serverId, LoginCallBackListener loginCallBackListener) {
		BaseActivity.setGameId(gameId);
		BaseActivity.setServerId(serverId);
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
		LoginActivity.loginCallBackListener = loginCallBackListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int widthDivideDensity = (int)(outMetrics.widthPixels / outMetrics.scaledDensity);
		
		if (widthDivideDensity <= valueOfWidthDivideDensity) {
			setContentView(R.layout.activity_login_320);
		} else {
			setContentView(R.layout.activity_login);
		}

		// 获取视图
		findViews();

		// 获取数据从游戏平台
		getDataFromIntent();

		// 设置监听事件
		setListeners();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			ClearPasswordFocus();
		}
		return super.onTouchEvent(event);
	}

	private void findViews() {
		btnClose = (Button) findViewById(R.id.btnClose);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnRegisterQuick = (Button) findViewById(R.id.btnRegisterQuick);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		ltvForgetPassword = (LinkTextView) findViewById(R.id.ltvForgetPassword);
		ltvForgetPassword.initBackgroundColor(R.color.uld_bg);
		ltvForgetPassword.initTextColorColor(R.color.uld_tvcolororange, R.color.uld_tvcolororange);

		cbRememberPassword = (CheckBox) findViewById(R.id.cbRememberPassword);

		txtUserName = (EditText) findViewById(R.id.txtUserName);
		txtPassword = (EditText) findViewById(R.id.txtPassword);
		txtPassword.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

		spUserNames = (Spinner) findViewById(R.id.spUserNames);
	}

	private void getDataFromIntent() {
		Intent intent = getIntent();
		if (null != intent) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				setGameId(Utility.getInt(bundle.getString("GameId")));
				setServerId(Utility.getInt(bundle.getString("ServerId")));
			}
		}
	}

	private void setListeners() {
		btnClose.setOnClickListener(new CloseListener());
		btnRegister.setOnClickListener(new RegisterListener());
		btnRegisterQuick.setOnClickListener(new RegisterQuickListener());
		btnLogin.setOnClickListener(new LoginListener());
		ltvForgetPassword.setOnClickListener(new ForgetPasswordListener());

		cbRememberPassword.setOnCheckedChangeListener(new RememberPasswordCheckedChangeListener());

		txtUserName.setOnFocusChangeListener(new UserNameFocusChangeListener());
		txtPassword.setOnFocusChangeListener(new PasswordFocusChangelistener());

		spUserNames.setOnItemSelectedListener(new UserNamesSelectedListener());

	}

	class CloseListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	class RegisterListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, RegisterActivity.class);
			startActivityForResult(intent, BaseActivity.REQUESTCODE_LOGIN);
		}
	}

	class RegisterQuickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			ClearPasswordFocus();
			dialog = showDialog();
			new Thread() {
				public void run() {
					User user = new User();
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
					messageBody.setMethodName("registerQuick");
					messageBody.setParmTypeList(new Class<?>[] { String.class, User.class });
					messageBody.setParmList(new Object[] { BaseActivity.getDeviceId(), user });
					MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
					dialog.dismiss();
					if (messageReturn != null) {
						uld.sdk.model.GameInterface gameInterface = null;
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

								final String userName = gameInterface.getUserName();
								final String password = gameInterface.getPassword();
								txtUserName.post(new Runnable() {
									@Override
									public void run() {
										txtUserName.setText(userName);
									}
								});
								txtPassword.post(new Runnable() {
									@Override
									public void run() {
										txtPassword.setText(password);
									}
								});
								setSharedUsernamesAndPwds(SHAREDUSERNAME, userName, password);

								Intent intent = new Intent();
								Bundle bundle = new Bundle();
								bundle.putString("ResultTip", "恭喜您，注册成功！");
								bundle.putString("UserName", userName);
								bundle.putString("Password", password);
								bundle.putSerializable("GameInterface", gameInterface);
								intent.putExtras(bundle);
								intent.setClass(LoginActivity.this, RegisterResultActivity.class);
								startActivityForResult(intent, REQUESTCODE_LOGIN);
							}
						} else {
							showToastThread("服务器烦忙，请重试", Toast.LENGTH_LONG);
						}
					} else {
						showToastThread("处理消息失败", Toast.LENGTH_LONG);
					}
				}
			}.start();
		}
	}

	class LoginListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			final String userName = txtUserName.getText().toString();
			final String password = txtPassword.getText().toString();
			final Boolean isRememberPwd = cbRememberPassword.isChecked();

			if (Utility.isEmpty(userName) || userName.length() < 6 || userName.length() > 20) {
				CheckEditText(txtUserName, R.string.uld_usernameerrmsg);
				return;
			} else if (Utility.isEmpty(password)) {
				CheckEditText(txtPassword, R.string.uld_passwordemptymsg);
				return;
			} else if (password.length() < 6 || password.length() > 20) {
				CheckEditText(txtPassword, R.string.uld_passworderrmsg);
				return;
			}
			ClearPasswordFocus();

			dialog = showDialog();

			new Thread() {
				public void run() {
					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();

					MessageBody messageBody = new MessageBody();
					messageBody.setClassPathName("uld.sdk.bll.User");
					messageBody.setMethodName("login");
					messageBody.setParmList(new Object[] { userName, Utility.encodeMD5(password), getGameId(), getServerId() });
					messageBody.setParmTypeList(new Class<?>[] { String.class, String.class, int.class, int.class });
					MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
					dialog.dismiss();

					if (messageReturn != null) {
						uld.sdk.model.GameInterface gameInterface = null;
						if (messageReturn.getRetObject() != null) {
							gameInterface = (uld.sdk.model.GameInterface) messageReturn.getRetObject();
						}
						if (messageReturn.getErrNo() == 0) {
							if (null == gameInterface) {
								showToastThread("登录失败，用户名或密码错误", Toast.LENGTH_LONG);
							} else {
								setUserId(gameInterface.getUserId());
								setRechargeRate(gameInterface.getRechargeRate());
								setRechargeUnit(gameInterface.getRechargeUnit());
								//得到bbs 客服电话等信息 分隔符为|_|
								setContent(gameInterface.getStrBundleMap().get("Content"));
								Log.d("test", "内容："+gameInterface.getStrBundleMap().get("Content"));
								if (Utility.isEmpty(gameInterface.getUserName())) {
									gameInterface.setUserName(userName);
								}
								loginIntent = getIntent(gameInterface);
								if (isRememberPwd) {
									setSharedUsernamesAndPwds(SHAREDUSERNAME, userName, password);
								} else {
									setSharedUsernamesAndPwds(SHAREDUSERNAME, userName, "");
								}

								//修改此处，游戏界面不需要Looper.prepare()
								btnLogin.post(new Runnable() {

									@Override
									public void run() {
										finish();
									}
								});
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

	class ForgetPasswordListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, ForgetPasswordActivity.class);
			startActivity(intent);
		}
	}

	class RememberPasswordCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			ClearPasswordFocus();
		}
	}

	class UserNameFocusChangeListener implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (!hasFocus) {
				String userName = txtUserName.getText().toString();
				if (Utility.isEmpty(userName) || userName.length() < 6 || userName.length() > 20) {
					CheckEditText(txtUserName, R.string.uld_usernameerrmsg);
				} else {
					txtUserName.setTextColor(getResources().getColor(R.color.uld_okmsgcolor));
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
					txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
				}
			} else {
				txtPassword.setTextColor(getResources().getColor(R.color.uld_tvcolor));
				txtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				Selection.setSelection(txtPassword.getText(), txtPassword.getText().length());
			}
		}
	}

	class UserNamesSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			if (historyUserNames != null && historyUserNames.length > pos && historyUserNames[pos] != null) {
				txtUserName.setText(historyUserNames[pos]);
			}
			if (historyPwds != null && historyPwds.length > pos && historyPwds[pos] != null) {
				txtPassword.setText(historyPwds[pos]);
				if (Utility.isEmpty(historyPwds[pos])) {
					cbRememberPassword.setChecked(false);
				} else {
					cbRememberPassword.setChecked(true);
				}
			}
			// Toast.makeText(LoginActivity.this, String.valueOf(pos), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {

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
		String password = txtPassword.getText().toString();
		String[] keys = null;
		String[] values = null;

		if (cbRememberPassword.isChecked()) {
			keys = new String[] { "UserName", "Password" };
			values = new String[] { userName, password };
		} else {
			keys = new String[] { "UserName", "Password" };
			values = new String[] { userName, "" };
		}
		// 储存用户名密码，记住密码使用。
		setSharedData(SHAREDUSERNAME, keys, values);
		// 储存用户名密码，查询用户信息使用。
		keys = new String[] { "View_UserName", "View_Password" };
		values = new String[] { userName, password };
		setSharedData(SHAREDUSERNAME, keys, values);
	}

	private void getDataFromSharedPreferences() {

		SharedPreferences sharedPreferences = getSharedData(SHAREDUSERNAME);
		txtUserName.setText(sharedPreferences.getString("UserName", ""));

		txtPassword.setText(sharedPreferences.getString("Password", ""));

		if (txtUserName.isFocused()) {
			Selection.setSelection(txtUserName.getText(), txtUserName.getText().length());
		} else if (txtPassword.isFocused()) {
			Selection.setSelection(txtPassword.getText(), txtPassword.getText().length());
		}

		String usernames = sharedPreferences.getString("UserNames", "");
		String[] namesData = usernames.split(BaseActivity.UserNameSplitRegular);
		if (namesData.length >= 2) {

			historyUserNames = new String[namesData.length - 1];
			for (int i = 0; i < historyUserNames.length; i++) {
				historyUserNames[i] = namesData[i + 1];
			}

			String pwds = sharedPreferences.getString("Passwords", "");
			String[] pwdsData = pwds.split(BaseActivity.UserNameSplitRegular);
			historyPwds = new String[pwdsData.length - 1];
			for (int i = 0; i < historyPwds.length; i++) {
				historyPwds[i] = pwdsData[i + 1];
			}
		}

		spUserNames.setVisibility(View.GONE);
		if (historyUserNames != null && historyUserNames.length > 0) {
			spUserNames.setVisibility(View.VISIBLE);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_usernames_item, historyUserNames);
			adapter.setDropDownViewResource(R.layout.spinner_usernames_item);
			spUserNames.setAdapter(adapter);
		}
	}

	/**
	 * 清除密码的焦点
	 */
	private void ClearPasswordFocus() {
		txtPassword.clearFocus();
	}

	private boolean isFromReg = false;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		isFromReg = true;
		loginIntent = data;
		finish();
	}

	@Override
	public void finish() {
		
		Log.d("test", "finish begin");
		// 从注册返回，没有注册
		if (loginIntent == null && isFromReg) {
			//无法关闭bug修复2013年11月20日17:11:14
			isFromReg=!isFromReg;
			Log.d("test", "finish should be from reg page, but not register.");
			return;
		}

		LoginResult loginResult = new LoginResult();
		loginResult.setLoginErrorMsg("用户取消登录！");
		if (loginIntent != null) {
			Bundle bundle = loginIntent.getExtras();
			if (bundle != null) {
				int userId = Utility.getInt(bundle.getString("UserId"));
				String userName = bundle.getString("UserName");

				loginResult.setLoginErrorMsg("");
				loginResult.setIsLogin(true);

				loginResult.setChannelId(BaseActivity.getChannelSubId());
				loginResult.setChannelName(BaseActivity.getChannelSubName());

				loginResult.setChannelUserId(String.valueOf(userId));
				loginResult.setChannelName(userName);
				
				// 
				loginResult.setTimeSign(bundle.getString("TimeSign"));
			}
		}
		loginCallBackListener.onLoginFinished(loginResult);
		super.finish();
		

		// if (isFromReg) {
		// if (loginIntent != null) {
		// setResult(Activity.RESULT_OK, loginIntent);
		// super.finish();
		// } else {
		// isFromReg = false;
		// }
		// } else {
		// setResult(Activity.RESULT_OK, loginIntent);
		// super.finish();
		// }
	}

}