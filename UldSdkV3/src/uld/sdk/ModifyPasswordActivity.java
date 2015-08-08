package uld.sdk;

import uld.sdk.model.GameInterface;
import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import uld.sdk.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Selection;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyPasswordActivity extends BaseActivity {

	private Button btnBack = null;
	private Button btnSubmit = null;

	private EditText txtOldPassword = null;
	private EditText txtNewPassword = null;
	private EditText txtConfirmPassword = null;

	private String userName = "";
	private GameInterface gameInterface = null;

	Intent modifyIntent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int widthDivideDensity = (int)(outMetrics.widthPixels / outMetrics.scaledDensity);
		
		if (widthDivideDensity <= valueOfWidthDivideDensity) {
			setContentView(R.layout.activity_modifypassword_320);
		} else {
			setContentView(R.layout.activity_modifypassword);
		}

		findViews();

		setListeners();

		getDataFromIntent();

		getDataFromSharedPreferences();
	}

	private void findViews() {
		btnBack = (Button) findViewById(R.id.btnBack);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);

		txtOldPassword = (EditText) findViewById(R.id.txtOldPassword);
		txtNewPassword = (EditText) findViewById(R.id.txtNewPassword);
		txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
	}

	private void setListeners() {
		btnBack.setOnClickListener(new BackClickListener());
		btnSubmit.setOnClickListener(new SubmitClickListener());

		txtOldPassword.setOnFocusChangeListener(new PasswordFocusChangeListener());
		txtOldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

		txtNewPassword.setOnFocusChangeListener(new PasswordFocusChangeListener());
		txtNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

		txtConfirmPassword.setOnFocusChangeListener(new ConfirmPasswordFocusChangelistener());
		txtConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
	}

	class BackClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			finish();
		}
	}

	class SubmitClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			final String oldPassword = txtOldPassword.getText().toString();
			final String newPassword = txtNewPassword.getText().toString();
			final String confirmPassword = txtConfirmPassword.getText().toString();
			if (Utility.isEmpty(oldPassword) || oldPassword.length() < 6 || oldPassword.length() > 20) {
				CheckEditText(txtOldPassword, R.string.uld_passworderrmsg);
				return;
			} else if (Utility.isEmpty(newPassword) || newPassword.length() < 6 || newPassword.length() > 20) {
				CheckEditText(txtNewPassword, R.string.uld_passworderrmsg);
				return;
			} else if (!confirmPassword.equals(newPassword)) {
				CheckEditText(txtConfirmPassword, R.string.uld_confirmpassworderrmsg);
				return;
			}
			dialog = showDialog();
			new Thread() {
				public void run() {
					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();

					MessageBody messageBody = new MessageBody();
					messageBody.setClassPathName("uld.sdk.bll.User");
					messageBody.setMethodName("modifyPassword");
					messageBody.setParmTypeList(new Class<?>[] { int.class, String.class, String.class, String.class});
					messageBody.setParmList(new Object[] { BaseActivity.getUserId(), userName, oldPassword, newPassword});
					MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
					dialog.dismiss();
					if (messageReturn != null) {
						if (messageReturn.getErrNo() == 0) {
							//储存用户名密码，记住密码使用。
							String[] keys = new String[] { "UserName", "Password" };
							String[] values = new String[] { userName, newPassword };
							setSharedData(SHAREDUSERNAME, keys, values);
							
							//储存用户名密码，查询用户信息使用。
							keys = new String[] { "View_UserName", "View_Password" };
							values = new String[] { userName, newPassword };
							setSharedData(SHAREDUSERNAME, keys, values);

							modifyIntent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putString("ResultTip", "恭喜您，密码修改成功！");
							if (gameInterface != null) {
								//从Login传过来的gameInterface
								gameInterface.setPassword(newPassword);
								bundle.putSerializable("GameInterface", gameInterface);
							}else {
								//修复BUG 2013年11月20日18:02:01
								//从用户中心过来之后并没有gameInterface 因此新建对象将密码传过去
								gameInterface=new GameInterface();
								gameInterface.setPassword(newPassword);
								bundle.putSerializable("GameInterface", gameInterface);
								gameInterface=null;
								//修复BUG 2013年11月20日18:02:01
							}
							bundle.putBoolean("IsFromModifyPassword", true);
							modifyIntent.putExtras(bundle);
							finish();

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

	class PasswordFocusChangeListener implements OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			EditText txtPassword = (EditText) v;
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

	class ConfirmPasswordFocusChangelistener implements OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (!hasFocus) {
				String confirmPassword = txtConfirmPassword.getText().toString();
				String password = txtNewPassword.getText().toString();
				if (!confirmPassword.equals(password)) {
					CheckEditText(txtConfirmPassword, R.string.uld_confirmpassworderrmsg);
				} else {
					txtConfirmPassword.setTextColor(getResources().getColor(R.color.uld_okmsgcolor));
					txtConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
				}
			} else {
				txtConfirmPassword.setTextColor(getResources().getColor(R.color.uld_tvcolor));
				txtConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
				Selection.setSelection(txtConfirmPassword.getText(), txtConfirmPassword.getText().length());
			}
		}
	}

	private void getDataFromIntent() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			gameInterface = (GameInterface) bundle.getSerializable("GameInterface");
		}
	}

	private void getDataFromSharedPreferences() {
		SharedPreferences sharedPreferences = getSharedData(SHAREDUSERNAME);
		userName = sharedPreferences.getString("UserName", "");
		txtOldPassword.setText(sharedPreferences.getString("View_Password", ""));
	}

	@Override
	public void finish() {
		if (modifyIntent != null) {
			setResult(Activity.RESULT_OK, modifyIntent);
		} else {
			setResult(Activity.RESULT_OK, getIntent());
		}
		super.finish();
	}

}
