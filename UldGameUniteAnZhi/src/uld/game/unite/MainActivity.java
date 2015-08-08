package uld.game.unite;

import java.util.Date;

import org.xuehu.aj.anzhi.R;

import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import uld.sdk.unite.SwitcheCallBackListener;
import uld.sdk.unite.SwitcheResult;
import android.app.Activity;
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
	private Button btnLogout = null;

	// --------------------------------------请使用以下参数----------------------
	public static final int gameId = 8;
	public static int serverId = 1001;

	// -------------------------------------- 请使用以下参数----------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		initGame();

	}

	private void initGame() {
		// 初始化
		uld.sdk.unite.GameInfo gameInfo = new GameInfo();
		gameInfo.setGameId(gameId);
		gameInfo.setServerId(serverId);

		uld.sdk.unite.UldPlatform.getInstance().init(MainActivity.this, gameInfo);
		uld.sdk.unite.UldPlatform.getInstance().gotoSetSwitcheCallBack(new SwitcheCallBackListener() {

			@Override
			public void onSwitchFinished(SwitcheResult SwitcheResult) {
				// TODO Auto-generated method stub
				String sSwitch = SwitcheResult.getSwitcheMsg();
				Log.d("Switch", sSwitch);
			}
		});
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
			uld.sdk.unite.UldPlatform.getInstance().gotofinish(MainActivity.this);
			System.exit(0);
			finish();
		}

	}

	private void LoginSDK() {

		System.out.println("LoginSDK:" + testId++);

		uld.sdk.unite.UldPlatform.getInstance().login(MainActivity.this, new LoginCallBackListener() {

			@SuppressWarnings("unused")
			@Override
			public void onLoginFinished(LoginResult loginResult) {
				System.out.println("LoginSDKCallBack:" + testId++);
				if (loginResult.getIsLogin()) {
					String userId = loginResult.getChannelUserId();

					// 用户登录成功后，返回该用户注册时的渠道编号，渠道名称
					int channelId = loginResult.getChannelId();
					String channelString = loginResult.getChannelName();
					//
					// Toast.makeText(
					// MainActivity.this,
					// "登录成功用户id:" + loginResult.getChannelUserId() + "渠道编号" +
					// loginResult.getChannelId() + ",Timesign:"
					// + loginResult.getTimeSign(), Toast.LENGTH_LONG).show();

					Log.d("test", "登录成功用户id:" + loginResult.getChannelUserId() + "渠道编号" + loginResult.getChannelId() + ",Timesign:"
							+ loginResult.getTimeSign());

					// TODO: TimeSign is used for validating on server.

					Intent intent = new Intent();
					intent.setClass(MainActivity.this, PlayGameActivity.class);
					startActivity(intent);

				} else {
					// 用户未登录成功，只能返回渠道编号，此渠道编号为此游戏包的渠道编号。
					int channelId = loginResult.getChannelId();
					// todo something.
					// Toast.makeText(MainActivity.this, "登录失败，返回消息：" +
					// loginResult.getLoginErrorMsg(),
					// Toast.LENGTH_LONG).show();
					Log.d("test", "登录失败");

				}
			}
		});
	}

	// 退出程序处理----------------------------------------------------
	private int quitCount = 0;

	private long firstClickTime = 0;

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && quitCount == 0) {
			firstClickTime = new Date().getTime();
			quitCount++;
			System.out.println(keyCode);
			System.out.println(event.getRepeatCount());
			Toast.makeText(MainActivity.this, "再按一次退出游戏", Toast.LENGTH_SHORT).show();
			// Do something.
			uld.sdk.unite.UldPlatform.getInstance().gotofinish(MainActivity.this);
			System.exit(0);
			finish();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_BACK && quitCount == 1) {
			long timespan = new Date().getTime() - firstClickTime;
			if (timespan > 0 && timespan <= 3000) {
				System.exit(0);
				finish();
			} else {
				quitCount = 1;
				firstClickTime = new Date().getTime();

				Toast.makeText(MainActivity.this, "再按一次退出游戏", Toast.LENGTH_SHORT).show();
				return true;
			}
		} else {
			quitCount = 0;
		}

		return super.onKeyUp(keyCode, event);
	}

	protected void onDestroy() {
		super.onDestroy();
		System.exit(0);
		// 或者下面这种方式
		// android.os.Process.killProcess(android.os.Process.myPid());
	}
}
