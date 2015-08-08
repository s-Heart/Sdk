package uld.game.unite;

import org.xuehu.aj.uc.R;

import uld.sdk.unite.GameInfo;
import uld.sdk.unite.InitCallBackListener;
import uld.sdk.unite.InitResult;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import cn.uc.gamesdk.UCCallbackListener;
import cn.uc.gamesdk.UCCallbackListenerNullException;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCGameSDKStatusCode;

public class MainActivity extends Activity {

	private Button btnWelcome = null;
	private Button btnExit = null;
	public static Context context = null;

	// --------------------------------------傲剑 请使用以下参数----------------------
	public static final int gameId = 8;
	public static int serverId = 1001;
	// 游老大分配的平台编号
	public static final String PID = "uld100008";

	// --------------------------------------傲剑 请使用以下参数----------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = MainActivity.this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		/*
		 * 设置启动画面 必接项
		 */
		this.setContentView(R.layout.splashscreen);

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				initGame();
			}
		};

		Handler handler = new Handler();
		handler.postDelayed(runnable, 500);

	}

	private static Boolean sIsInitSuccess = false;
	private void initGame() {
		// 初始化

		uld.sdk.unite.GameInfo gameInfo = new GameInfo();
		gameInfo.setGameId(gameId);
		gameInfo.setServerId(serverId);
		gameInfo.setUldPid(PID);
		
		try {
			UCGameSDK.defaultSDK().setLogoutNotifyListener(new UCCallbackListener<String>(
					
					) {

						@Override
						public void callback(int arg0, String arg1) {
							// TODO Auto-generated method stub
							
						}
			});
		} catch (UCCallbackListenerNullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		uld.sdk.unite.UldPlatform.getInstance().init(MainActivity.this, gameInfo, new InitCallBackListener(){

			@Override
			public void onInitFinished(InitResult initResult) {
				// TODO Auto-generated method stub
				
				// 此时可以获取 此游戏包的 渠道编号，和渠道名称
				int channelId = initResult.getChannelId();
				String channelName = initResult.getChannelName();
				int errCode = initResult.getErrCode();
				String errMsg = initResult.getErrMsg();
				
				if (errCode == 0) {
					sIsInitSuccess = true;
				}
				
				Log.d("test", String.valueOf(channelId));
				Log.d("test", channelName);
				Log.d("test", String.valueOf(errCode));
				Log.d("test", errMsg);
			}
			
		});

		setContentView(R.layout.activity_main);

		// GameInterface.initializeApp(this);
		findViews();

		setListeners();

	}

	private void findViews() {
		btnWelcome = (Button) findViewById(R.id.btnWelcome);
		btnExit = (Button) findViewById(R.id.btnExit);
	}

	private void setListeners() {
		btnWelcome.setOnClickListener(new WelcomeListener());
		btnExit.setOnClickListener(new ExitListener());
	}

	public static int testId = 1;

	class WelcomeListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			LoginSDK();
		}
	}

	/**
	 * 登陆接口调用
	 */
	private void LoginSDK() {
		
		if (!sIsInitSuccess) {
			Toast.makeText(MainActivity.this, "初始化中，请稍后再试", Toast.LENGTH_SHORT).show();
			return;
		}
		
		System.out.println("LoginSDK:" + testId++);
		// 传入游戏方的GameInfo
		GameInfo gameInfo = new GameInfo();
		gameInfo.setServerId(serverId);
		gameInfo.setGameId(gameId);

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

					Log.d("UcLoginResult", loginResult.getChannelUserId());
					Log.d("UcLoginResult", String.valueOf(loginResult.getChannelId()));
					Log.d("UcLoginResult", loginResult.getChannelName());
					// 9109楚汉争霸没有该字段所以为空
					Log.d("UcLoginResult", "" + loginResult.getTimeSign());
					// Toast.makeText(
					// MainActivity.this,
					// "登录成功用户id:"
					// + loginResult
					// .getChannelUserId()
					// + "渠道编号"
					// + loginResult.getChannelId()
					// +Toast.LENGTH_LONG).show();

					Intent intent = new Intent();
					intent.setClass(MainActivity.this, PlayGameActivity.class);
					startActivity(intent);
				} else {
					// 用户未登录成功，只能返回渠道编号，此渠道编号为此游戏包的渠道编号。
					int channelId = loginResult.getChannelId();
					// todo something.
					// Toast.makeText(
					// MainActivity.this,
					// "登录失败，返回消息："
					// + loginResult.getLoginErrorMsg(),
					// Toast.LENGTH_LONG).show();
					
					//
					// Intent intent = new Intent();
					// intent.setClass(MainActivity.this, PlayGameActivity.class);
					// startActivity(intent);
				}
			}
		});

		try {

			UCGameSDK.defaultSDK().createFloatButton(MainActivity.this, new UCCallbackListener<String>() {

				@Override
				public void callback(int statuscode, String data) {
					Log.d("SelectServerActivity`floatButton Callback", "statusCode == " + statuscode + "  data == " + data);
				}
			});
			// 显示悬浮图标，游戏可在某些场景选择隐藏此图标，避免影响游戏体验
			UCGameSDK.defaultSDK().showFloatButton(MainActivity.this, 100, 50, true);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 退出接口调用
	 */
	class ExitListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// 退出接口
			exitGame();
		}
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
		UCGameSDK.defaultSDK().exitSDK(MainActivity.this,new UCCallbackListener<String>() {

			@Override
			public void callback(int statuscode, String data) {
				if(UCGameSDKStatusCode.SDK_EXIT_CONTINUE==statuscode){
					//继续游戏的逻辑
				}else if (UCGameSDKStatusCode.SDK_EXIT==statuscode) {
					//退出游戏的逻辑
					UCGameSDK.defaultSDK().destoryFloatButton(MainActivity.this);
					MainActivity.this.finish();
				}
			}
		});
		
	}

	protected void onDestroy() {
		super.onDestroy();
		UCGameSDK.defaultSDK().destoryFloatButton(this);
	}
}
