package uld.game.unite;



import org.xuehu.aj.sougou.R;

import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import uld.sdk.unite.LogoutCallBackListener;
import uld.sdk.unite.LogoutResult;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
	private Button btnExit = null;
	private Button btnLogoutOPPO = null;
	public static Context context = null;

	// --------------------------------------傲剑 请使用以下参数----------------------
	public static final int gameId = 8;
	public static int serverId = 1001;

	// --------------------------------------傲剑 请使用以下参数----------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = MainActivity.this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		initGame();

	}

	private void initGame() {
		// 初始化
		uld.sdk.unite.GameInfo gameInfo = new GameInfo();
		gameInfo.setGameId(gameId);
		gameInfo.setServerId(serverId);

		uld.sdk.unite.InitResult initResult = uld.sdk.unite.UldPlatform.getInstance().init(MainActivity.this, gameInfo);
		// 此时可以获取 此游戏包的 渠道编号，和渠道名称
		int channelId = initResult.getChannelId();
		String channelName = initResult.getChannelName();
		Log.d("test", String.valueOf(channelId));
		Log.d("test", channelName);

		setContentView(R.layout.activity_main);

		// GameInterface.initializeApp(this);
		findViews();

		setListeners();
	}

	private void findViews() {
		btnWelcome = (Button) findViewById(R.id.btnWelcome);
		btnExit = (Button) findViewById(R.id.btnExit);
		btnLogoutOPPO = (Button) findViewById(R.id.btnLogout17173);
	}

	private void setListeners() {
		btnWelcome.setOnClickListener(new WelcomeListener());
		btnExit.setOnClickListener(new ExitListener());
		btnLogoutOPPO.setOnClickListener(new LogoutDownjoyListener());
	}

	public static int testId = 1;

	class WelcomeListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			LoginSDK();
		}
	}

	// 注销用户接口
	class LogoutDownjoyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			uld.sdk.unite.UldPlatform.getInstance().logout(MainActivity.this, new LogoutCallBackListener() {

				@Override
				public void onLogoutFinished(LogoutResult logoutResult) {
					if (logoutResult.getIsLogout()) {
						Toast.makeText(MainActivity.this, "注销成功，请重新登录", Toast.LENGTH_LONG).show();
						Log.d("test", "channeluserid:" + logoutResult.getChannelUserId());
					}
				}

			});
		}

	}

	// 退出接口
	class ExitListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			exitGame();
		}

	}

	// 登陆接口
	private void LoginSDK() {

		System.out.println("LoginSDK:" + testId++);

		uld.sdk.unite.UldPlatform.getInstance().login(MainActivity.this, new LoginCallBackListener() {

			@Override
			public void onLoginFinished(LoginResult loginResult) {
				System.out.println("LoginSDKCallBack:" + testId++);
				if (loginResult.getIsLogin()) {
					String userId = loginResult.getChannelUserId();

					// 用户登录成功后，返回该用户注册时的渠道编号，渠道名称
					int channelId = loginResult.getChannelId();
					String channelString = loginResult.getChannelName();

					String signFromUldString = loginResult.getSign();

					Toast.makeText(
							MainActivity.this,
							"登录成功用户id:" + loginResult.getChannelUserId() + "渠道编号" + loginResult.getChannelId() + "TimeSign:"
									+ loginResult.getTimeSign(), Toast.LENGTH_LONG).show();

					Intent intent = new Intent();
					intent.setClass(MainActivity.this, PlayGameActivity.class);
					startActivity(intent);

				} else {
					// 用户未登录成功，只能返回渠道编号，此渠道编号为此游戏包的渠道编号。
					int channelId = loginResult.getChannelId();
					// todo something.
					Toast.makeText(MainActivity.this, "登录失败，返回消息：" + loginResult.getLoginErrorMsg(), Toast.LENGTH_LONG).show();
				}
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
		uld.sdk.unite.UldPlatform.getInstance().gotoFinish(MainActivity.this);
		System.exit(0);
		finish();
	}

	protected void onDestroy() {
		super.onDestroy();

		// System.exit(0);
		// 或者下面这种方式
		// android.os.Process.killProcess(android.os.Process.myPid());
	}
}
