package uld.game.unite;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import org.xuehu.aj.mi.R;

import uld.sdk.tools.Utility;
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import uld.sdk.unite.OnInitFinishListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btnWelcome = null;
	private Button btnLogout = null;
	public static Context context = null;

	// public static final int gameId = 1;
	// public static int serverId = 1;

	public static final boolean IsXiaomi = true;

	// --------------------------------------傲剑请使用以下参数----------------------
	public static final int gameId = 8;
	public static int serverId = 1001;

	// --------------------------------------傲剑请使用以下参数----------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = MainActivity.this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		initGame();
	}

	private final static int HandleMessage_BeforeInitFinished = 1;
	private final static int HandleMessage_LoginFinished = 2;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case HandleMessage_BeforeInitFinished:
				initGame();
				break;
			case HandleMessage_LoginFinished:
				LoginResult loginResult = (LoginResult) msg.obj;

				if (loginResult.getIsLogin()) {
					String userId = loginResult.getChannelUserId();
					// 用户登录成功后，返回该用户注册时的渠道编号，渠道名称
					int channelId = loginResult.getChannelId();

						Toast.makeText(
								MainActivity.this,
								"登录成功用户id:" + loginResult.getChannelUserId() + "渠道编号" + loginResult.getChannelId() + ",Timesign:"
										+ loginResult.getTimeSign(), Toast.LENGTH_LONG).show();

						Log.d("test", "登录成功用户id:" + loginResult.getChannelUserId() + "渠道编号" + loginResult.getChannelId()
								+ ",Timesign:" + loginResult.getTimeSign());

						// TODO: TimeSign is used for validating on  server.

						Intent intent = new Intent();
						intent.setClass(MainActivity.this, PlayGameActivity.class);
						startActivity(intent);
				} else {
					Toast.makeText(MainActivity.this, "登录失败，返回消息：" + loginResult.getLoginErrorMsg(), Toast.LENGTH_LONG).show();
				}

				break;
			default:
				break;
			}
		}

	};

	private void initGame() {
		uld.sdk.unite.GameInfo gameInfo = new GameInfo();
		gameInfo.setGameId(gameId);
		gameInfo.setServerId(serverId);

		uld.sdk.unite.UldPlatform.getInstance().init(MainActivity.this, gameInfo);

		setContentView(R.layout.activity_main);

		findViews();

		setListeners();
	}

	private void findViews() {
		btnWelcome = (Button) findViewById(R.id.btnWelcome);
		btnLogout = (Button) findViewById(R.id.btnLogout);
	}

	private void setListeners() {
		if (btnWelcome != null) {
			btnWelcome.setOnClickListener(new WelcomeListener());
		}
		btnLogout.setOnClickListener(new LogoutListener());
	}

	public static int testId = 1;

	class WelcomeListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			LoginSDK();
		}
	}

	// 退出程序
	class LogoutListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			System.exit(0);
			finish();
		}

	}

	private void LoginSDK() {

		System.out.println("LoginSDK:" + testId++);

		uld.sdk.unite.UldPlatform.getInstance().login(MainActivity.this, new LoginCallBackListener() {

			@Override
			public void onLoginFinished(LoginResult loginResult) {
				System.out.println("LoginSDKCallBack:" + testId++);
				mHandler.sendMessage(mHandler.obtainMessage(HandleMessage_LoginFinished, loginResult));
			}
		});
	}

	// 退出程序处理----------------------------------------------------
	private int quitCount = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitGame();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void exitGame() {
		MainActivity.this.finish();
	}

	protected void onDestroy() {
		super.onDestroy();
	}
}
