package org.xuehu.aj.am;

import java.util.Date;

import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.gionee.gsp.GnException;

public class GameClient extends Activity {

	private Button btnWelcome = null;
	private Button btnLogout = null;
	private uld.sdk.unite.GameInfo gameInfo = null;

	private IntentFilter mIntentFilter = null;
	private String accountLogoutNotifyAction = "";

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
		gameInfo = new GameInfo();
		gameInfo.setGameId(gameId);
		gameInfo.setServerId(serverId);

		uld.sdk.unite.UldPlatform.getInstance().init(GameClient.this, gameInfo, GameClient.class);


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
	}

	class WelcomeListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			try {
				LoginSDK();
			} catch (GnException e) {
				e.printStackTrace();
			}
		}
	}


	// 是否自动登录，一般登录请使用true,如果通过金立sdk切换后，在获取onNewIntent的值。
	private Boolean isAutoLogin = true;
	private void LoginSDK() throws GnException {
		gameInfo.setUldIsAutoLogin(isAutoLogin);
		uld.sdk.unite.UldPlatform.getInstance().login(GameClient.this,
				gameInfo, new LoginCallBackListener() {

					@Override
					public void onLoginFinished(LoginResult loginResult) {
						if (loginResult.getIsLogin()) {
								Toast.makeText(
										GameClient.this,
										"登录成功用户id:"
												+ loginResult
														.getChannelUserId()
												+ "渠道编号"
												+ loginResult.getChannelId()
												+ ",Sign:"
												+ loginResult.getTimeSign(),
										Toast.LENGTH_LONG).show();
								Intent intent = new Intent();
								intent.setClass(GameClient.this,
										PlayGameActivity.class);
								startActivity(intent);
							} else {
								// 用户未登录成功，只能返回渠道编号，此渠道编号为此游戏包的渠道编号。
								Toast.makeText(
										GameClient.this,
										"登录失败，返回消息："
												+ loginResult
														.getLoginErrorMsg(),
										Toast.LENGTH_LONG).show();
							}
						}

				});

	}

	protected void onDestroy() {
		super.onDestroy();

	}

	// 金立悬浮框中 切换用户
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		isAutoLogin = true;
		if (intent != null && intent.getBooleanExtra("jinlisdk_relogin", false)) {
			// 如果是收到切换账号通知的登录,会自动清除用户名和密码，需要用户输入用户名和密码
			isAutoLogin = false;
		}
		
		try {
			LoginSDK();
		} catch (GnException e) {
			e.printStackTrace();
		}
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
			Toast.makeText(GameClient.this, "再按一次退出游戏", Toast.LENGTH_SHORT).show();
			// Do something.
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

				Toast.makeText(GameClient.this, "再按一次退出游戏", Toast.LENGTH_SHORT).show();
				return true;
			}
		} else {
			quitCount = 0;
		}

		return super.onKeyUp(keyCode, event);
	}

}
