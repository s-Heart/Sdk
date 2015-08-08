package uld.game.unite;

import java.util.Date;

import org.json.JSONObject;
import org.xuehu.aj.DK.R;

import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import uld.sdk.unite.UldPlatform;
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

import com.duoku.platform.DkErrorCode;
import com.duoku.platform.DkPlatform;
import com.duoku.platform.DkProtocolKeys;
import com.duoku.platform.IDKSDKCallBack;

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
		// 2、初始化结束之后，设置悬浮窗回调，目前悬浮窗中仅切换帐号功能支持回调，暂时只需处理这一种情况。不设置该回调或者设置错误回调，将无法通过上线测试。
		setDkSuspendWindowCallBack();
	}

	private void initGame() {
		// 初始化
		uld.sdk.unite.GameInfo gameInfo = new GameInfo();
		gameInfo.setGameId(gameId);
		gameInfo.setServerId(serverId);

		uld.sdk.unite.UldPlatform.getInstance().init(MainActivity.this, gameInfo);
		setContentView(R.layout.activity_main);

		findViews();

		setListeners();
	}

	// 设置悬浮窗回调
	private void setDkSuspendWindowCallBack() {
		DkPlatform.setDKSuspendWindowCallBack(new IDKSDKCallBack() {

			@Override
			public void onResponse(String paramString) {
				// paramString为返回结果JSON串，切换帐号json串示例如下：
				// {"state_code":2005}
				int _stateCode = 0;
				try {
					JSONObject _jsonObj = new JSONObject(paramString);
					_stateCode = _jsonObj.getInt(DkProtocolKeys.FUNCTION_STATE_CODE);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (_stateCode == DkErrorCode.DK_CHANGE_USER) {
					// 切换帐号处理逻辑，以下代码仅为示例代码，cp可根据自身需要进行操作，如重新弹出登录界面等
					Intent i = UldPlatform.sContext.getPackageManager().getLaunchIntentForPackage(MainActivity.this.getPackageName());
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					MainActivity.this.startActivity(i);
				}
			}
		});
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

		uld.sdk.unite.GameInfo gameInfo = new GameInfo();
		gameInfo.setGameId(gameId);
		gameInfo.setServerId(serverId);

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

					Toast.makeText(
							MainActivity.this,
							"登录成功用户id:" + loginResult.getChannelUserId() + "渠道编号" + loginResult.getChannelId() + ",Timesign:"
									+ loginResult.getTimeSign(), Toast.LENGTH_LONG).show();

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

					Toast.makeText(MainActivity.this, "登录失败，返回消息：" + loginResult.getLoginErrorMsg(), Toast.LENGTH_LONG).show();

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

	/**
	 * 关闭悬浮窗的操作
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		DkPlatform.destroy(this); // 释放资源
	}
}
