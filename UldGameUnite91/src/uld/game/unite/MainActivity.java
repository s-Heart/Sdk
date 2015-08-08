package uld.game.unite;

import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import uld.sdk.unite.OnInitFinishListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.nd.commplatform.gc.widget.NdToolBar;
import com.nd.commplatform.gc.widget.NdToolBarPlace;
import org.xuehu.aj.aj91.R;

public class MainActivity extends Activity {

	private Button btnWelcome = null;
	private Button btnLogout = null;
	public static Context context = null;
	public static final boolean Is91NewSdk = true;

	// --------------------------------------傲剑请使用以下参数----------------------
	public static final int gameId = 8;
	public static int serverId = 1001;

	// --------------------------------------傲剑 请使用以下参数----------------------

	private NdToolBar ndToolBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = MainActivity.this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		if (Is91NewSdk) {
			// 初始化
			uld.sdk.unite.GameInfo gameInfo = new GameInfo();
			gameInfo.setGameId(gameId);
			gameInfo.setServerId(serverId);
			uld.sdk.unite.UldPlatform.getInstance().beforeInitGame(MainActivity.this, gameInfo, new OnInitFinishListener() {

				@Override
				public void onComplete(int flag) {
					mHandler.sendMessage(mHandler.obtainMessage(HandleMessage_BeforeInitFinished, flag));

				}
			});
			setContentView(R.layout.activity_main);

			ndToolBar = NdToolBar.create(this, NdToolBarPlace.NdToolBarBottomRight);
			ndToolBar.show();

			findViews();

			setListeners();

		} else {
			initGame();
		}

	}

	private final static int HandleMessage_BeforeInitFinished = 1;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case HandleMessage_BeforeInitFinished:
				initGame();
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
				if (loginResult.getIsLogin()) {
					String userId = loginResult.getChannelUserId();

					// 用户登录成功后，返回该用户注册时的渠道编号，渠道名称
					int channelId = loginResult.getChannelId();
					// String channelString =
					// loginResult.getChannelName();

					String signFromUldString = loginResult.getSign();

					if (true) {

						// Toast.makeText(
						// MainActivity.this,
						// "登录成功用户id:"
						// + loginResult
						// .getChannelUserId()
						// + "渠道编号"
						// + loginResult.getChannelId()
						// + ",渠道名称:"
						// + loginResult.getChannelName()
						// + ",Timesign:"
						// + loginResult.getTimeSign(),
						// Toast.LENGTH_LONG).show();
						Toast.makeText(
								MainActivity.this,
								"登录成功用户id:" + loginResult.getChannelUserId() + "渠道编号" + loginResult.getChannelId() + ",Timesign:"
										+ loginResult.getTimeSign(), Toast.LENGTH_LONG).show();
						Log.d("test", "登录成功用户id:" + loginResult.getChannelUserId() + "渠道编号" + loginResult.getChannelId() + ",渠道名称:"
								+ loginResult.getChannelName() + ",Timesign:" + loginResult.getTimeSign());

						Intent intent = new Intent();
						intent.setClass(MainActivity.this, PlayGameActivity.class);
						startActivity(intent);
					} else {

						Toast.makeText(MainActivity.this, "登录失败,错误原因加密验证未通过：", Toast.LENGTH_LONG).show();

					}

				} else {
					// 用户未登录成功，只能返回渠道编号，此渠道编号为此游戏包的渠道编号。
					int channelId = loginResult.getChannelId();
					// todo something.
					// if (IsXiaomi) {
					// Looper.prepare();
					// }
					Toast.makeText(MainActivity.this, "登录失败，返回消息：" + loginResult.getLoginErrorMsg(), Toast.LENGTH_LONG).show();
					// if (IsXiaomi) {
					// Looper.loop();
					// }
				}
			}
		});
	}

	// 退出程序处理----------------------------------------------------
	private int quitCount = 0;

	// @Override
	// public boolean onKeyUp(int keyCode, KeyEvent event) {
	// if (keyCode == KeyEvent.KEYCODE_BACK) {
	// quitCount++;
	// if (quitCount == 2) {
	// finish();
	// } else {
	// Toast.makeText(MainActivity.this, "再按一次返回退出程序",
	// Toast.LENGTH_SHORT).show();
	// }
	// return true;
	// } else {
	// quitCount = 0;
	// }
	//
	// return super.onKeyUp(keyCode, event);
	// }

	// private long firstClickTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// if (keyCode == KeyEvent.KEYCODE_BACK && quitCount == 0) {
		// firstClickTime = new Date().getTime();
		// quitCount++;
		// System.out.println(keyCode);
		// System.out.println(event.getRepeatCount());
		// Toast.makeText(MainActivity.this, "再按一次退出游戏",
		// Toast.LENGTH_SHORT).show();
		// // Do something.
		// return true;
		// } else if (keyCode == KeyEvent.KEYCODE_BACK && quitCount == 1) {
		// long timespan = new Date().getTime() - firstClickTime;
		// if (timespan > 0 && timespan <= 3000) {
		// exitGame();
		//
		// // System.exit(0);
		// // finish();
		// } else {
		// quitCount = 1;
		// firstClickTime = new Date().getTime();
		// Toast.makeText(MainActivity.this, "再按一次退出游戏",
		// Toast.LENGTH_SHORT).show();
		// return true;
		// }
		// } else {
		// quitCount = 0;
		// }

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitGame();
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void exitGame() {
		MainActivity.this.finish();
		// GameInterface.initializeApp(MainActivity.this);
		// GameExitCallback gameExitCallback=new
		// GameInterface.GameExitCallback() {
		// @Override
		// public void onConfirmExit() {
		// MainActivity.this.finish();
		// }
		//
		// @Override
		// public void onCancelExit() {
		// Log.e("test", "You have back to game UI...");
		// }
		// };
		// GameInterface.exit(MainActivity.this,gameExitCallback);
	}

	protected void onDestroy() {

		if (ndToolBar != null) {
			ndToolBar.recycle();
			ndToolBar = null;
		}
		super.onDestroy();

		// System.exit(0);
		// 或者下面这种方式
		// android.os.Process.killProcess(android.os.Process.myPid());
	}
}
