package uld.sdk;

import uld.sdk.model.GameInterface;
import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class RegisterResultActivity extends BaseActivity {

	private Button btnBack = null;
	private Button btnModifyPassword = null;
	private Button btnBackGame = null;

	private TextView tvResultTip = null;
	private TextView tvUserName = null;
	private TextView tvPassword = null;

	private GameInterface gameInterface = null;

	public static void show(Context context) {
		Intent intent = new Intent(context, RegisterResultActivity.class);
		context.startActivity(intent);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int widthDivideDensity = (int)(outMetrics.widthPixels / outMetrics.scaledDensity);
		
		if (widthDivideDensity <= valueOfWidthDivideDensity) {
			setContentView(R.layout.activity_registerresult_320);
		} else {
			setContentView(R.layout.activity_registerresult);
		}

		findViews();

		setListeners();

		getDataFromIntent();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getDataFromSharedPreferences();
	}

	private void findViews() {
		btnBack = (Button) findViewById(R.id.btnBack);
		btnModifyPassword = (Button) findViewById(R.id.btnModifyPassword);
		btnBackGame = (Button) findViewById(R.id.btnBackGame);

		tvResultTip = (TextView) findViewById(R.id.tvResultTip);
		tvUserName = (TextView) findViewById(R.id.tvUserName);
		tvPassword = (TextView) findViewById(R.id.tvPassword);
	}

	private void setListeners() {
		btnBack.setOnClickListener(new BackClickListener());
		btnModifyPassword.setOnClickListener(new ModifyPasswordClickListener());
		btnBackGame.setOnClickListener(new BackGameClickListener());
	}

	class BackClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	class ModifyPasswordClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			if (gameInterface != null) {
				bundle.putSerializable("GameInterface", gameInterface);
			}
			intent.putExtras(bundle);
			intent.setClass(RegisterResultActivity.this, ModifyPasswordActivity.class);
			startActivityForResult(intent, 0);
		}
	}

	class BackGameClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	private void getDataFromIntent() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			tvResultTip.setText(bundle.getString("ResultTip"));
			gameInterface = (GameInterface) bundle.getSerializable("GameInterface");
			int userId = Utility.getInt(bundle.getString("UserId"));
			if (userId > 0 && BaseActivity.getUserId() != userId) {
				BaseActivity.setUserId(userId);
			}
			int gameId = Utility.getInt(bundle.getString("GameId"));
			if (gameId > 0 && BaseActivity.getGameId() != gameId) {
				BaseActivity.setGameId(gameId);
			}
			BaseActivity.setQueryUserInfoKey(bundle.getString("Key"));
		}
		if (Utility.isEmpty(tvResultTip.getText().toString())) {
			tvResultTip.setVisibility(View.GONE);
		} else {
			tvResultTip.setVisibility(View.VISIBLE);
		}
	}

	private void getDataFromSharedPreferences() {
		SharedPreferences sharedPreferences = getSharedData(SHAREDUSERNAME);
		tvUserName.setText(sharedPreferences.getString("View_UserName", ""));
		tvPassword.setText(sharedPreferences.getString("View_Password", ""));
	}

	@Override
	public void finish() {
		if (gameInterface == null) {
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("UserId", String.valueOf(BaseActivity.getUserId()));
			intent.putExtras(bundle);
			setResult(Activity.RESULT_OK, intent);
		} else {
			//快速注册
			setResult(Activity.RESULT_OK, getIntent(gameInterface));
			
			MessageHeader messageHeader = new MessageHeader();
			messageHeader.init();

			MessageBody messageBody = new MessageBody();
			messageBody.setClassPathName("uld.sdk.bll.User");
			messageBody.setMethodName("login");

			SharedPreferences sharedPreferences = getSharedData(SHAREDUSERNAME);
			String userName = sharedPreferences.getString("UserName", "");
			String pwd = sharedPreferences.getString("Password", "");
			
			if (!Utility.isEmpty(userName) && !(Utility.isEmpty(pwd))) {
				messageBody.setParmList(new Object[] { userName, Utility.encodeMD5(pwd), getGameId(), getServerId()});
				messageBody.setParmTypeList(new Class<?>[] { String.class, String.class, int.class, int.class });
				
				ThreadManager.sendMessageInvoke(messageHeader, messageBody);
			}
		}
		super.finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Bundle bundle = data.getExtras();
		boolean IsFromModifyPassword = false;
		if (bundle != null) {
			gameInterface = (GameInterface) bundle.getSerializable("GameInterface");
			IsFromModifyPassword = bundle.getBoolean("IsFromModifyPassword");
		}
		if (IsFromModifyPassword) {
			tvResultTip.setText(bundle.getString("ResultTip"));
			//处理bug	 2013年10月30日10:15:23
			String[] keys=new String[]{"Passwords"};
			String[] values=new String[]{gameInterface.getPassword()};
			setSharedData(SHAREDUSERNAME, keys, values);
			//处理bug 2013年10月30日10:15:29
			
			int userId = Utility.getInt(bundle.getString("UserId"));
			if (userId > 0 && BaseActivity.getUserId() != userId) {
				BaseActivity.setUserId(userId);
			}
			int gameId = Utility.getInt(bundle.getString("GameId"));
			if (gameId > 0 && BaseActivity.getGameId() != gameId) {
				BaseActivity.setGameId(gameId);
			}
			if (Utility.isEmpty(tvResultTip.getText().toString())) {
				tvResultTip.setVisibility(View.GONE);
			} else {
				tvResultTip.setVisibility(View.VISIBLE);
			}
			
		}
	}

}
