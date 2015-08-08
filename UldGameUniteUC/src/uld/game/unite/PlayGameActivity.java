package uld.game.unite;

import org.xuehu.aj.uc.R;

import uld.sdk.tools.Utility;
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LogoutCallBackListener;
import uld.sdk.unite.LogoutResult;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import android.app.Activity;
import android.os.Bundle;
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
import cn.uc.gamesdk.UCFloatButtonCreateException;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCGameSDKStatusCode;

import org.xuehu.aj.uc.R;

public class PlayGameActivity extends Activity {

	private Button btnRecharge = null;
	// private Button btnLogoutUc = null;
	private Button btnExit = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_playgame);

		findViews();

		setListeners();

		// 调用悬浮按钮
		ucSdkFloatButton();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		try {
			UCGameSDK.defaultSDK().showFloatButton(PlayGameActivity.this, 100, 50, true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 必接功能<br>
	 * 悬浮按钮创建及显示<br>
	 * 悬浮按钮必须保证在SDK进行初始化成功之后再进行创建需要在UI线程中调用<br>
	 * 创建时出现java.io.IOException: Is a directory的警告日志属于正常情况，不影响调用<br>
	 * <br>
	 * 如果一个 Activity 已经创建了悬浮按钮，其在退出时必须调用销毁方法，如果不调用该方法，<br>
	 * 程序会发生错误，但不会导致程序崩溃。<br>
	 * 建议在 Activity 的 onDestory() 方法中调用销毁方法。<br>
	 */
	private void ucSdkFloatButton() {
		PlayGameActivity.this.runOnUiThread(new Runnable() {
			public void run() {
				try {
					// 创建悬浮按钮。该悬浮按钮将悬浮显示在GameActivity页面上，点击时将会展开悬浮菜单，菜单中含有
					// SDK 一些功能的操作入口。
					// 创建完成后，并不自动显示，需要调用showFloatButton(Activity,
					// double, double, boolean)方法进行显示或隐藏。
					UCGameSDK.defaultSDK().createFloatButton(PlayGameActivity.this, new UCCallbackListener<String>() {

						@Override
						public void callback(int statuscode, String data) {
							Log.d("SelectServerActivity`floatButton Callback", "statusCode == " + statuscode + "  data == "
									+ data);
						}
					});
					// 显示悬浮图标，游戏可在某些场景选择隐藏此图标，避免影响游戏体验
					UCGameSDK.defaultSDK().showFloatButton(PlayGameActivity.this, 100, 50, true);

				} catch (UCCallbackListenerNullException e) {
					e.printStackTrace();
				} catch (UCFloatButtonCreateException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		UCGameSDK.defaultSDK().destoryFloatButton(PlayGameActivity.this);
	}

	private void findViews() {
		btnRecharge = (Button) findViewById(R.id.btnRecharge);
		btnExit = (Button) findViewById(R.id.btnExitUC);
	}

	private void setListeners() {
		btnRecharge.setOnClickListener(new RechargeClickListener());
		// btnLogoutUc.setOnClickListener(new LogoutUcClickListener());
		btnExit.setOnClickListener(new ExitListener());
	}

	// 充值调用
	class RechargeClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// 初始化
			uld.sdk.unite.GameInfo gameInfo = new GameInfo();
			gameInfo.setGameId(MainActivity.gameId);
			gameInfo.setServerId(MainActivity.serverId);
			gameInfo.setUldPid(MainActivity.PID);
			gameInfo.setPlayerId("your player id");
			gameInfo.setRoleName("your role name");
			gameInfo.setGrade("role grade");

			uld.sdk.unite.UldPlatform.getInstance().recharge(PlayGameActivity.this, gameInfo, new RechargeCallBackListener() {

				public void onRechargeUiFinished(RechargeResult rechargeResult) {
					if (!Utility.isEmpty(rechargeResult.getOrderId())) {
						Toast.makeText(
								PlayGameActivity.this,
								"订单编号：" + rechargeResult.getOrderId() + "订单信息:" + rechargeResult.getOrderMsg() + "充值金额:"
										+ rechargeResult.getPayAccount(), Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(PlayGameActivity.this, "取消订单，订单信息" + rechargeResult.getOrderMsg(), Toast.LENGTH_SHORT)
								.show();
					}
				}
			});
		}
	}

	/**
	 * 注销当前账号监听
	 * 
	 * @author Tony
	 * 
	 */
	class LogoutUcClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			uld.sdk.unite.UldPlatform.getInstance().logout(new LogoutCallBackListener() {

				@Override
				public void onLogoutFinished(LogoutResult logoutResult) {
					if (logoutResult.getIsLogout()) {
						Toast.makeText(PlayGameActivity.this, "注销成功请重新登录", Toast.LENGTH_LONG).show();
						PlayGameActivity.this.finish();
					} else {
						Toast.makeText(PlayGameActivity.this, "注销失败，请看debug日志查看信息", Toast.LENGTH_LONG).show();
						Log.d("logoutResult", logoutResult.getLogoutErrorMsg());
					}
				}
			});
		}

	}

	// 退出程序
	class ExitListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			exitGame();
		}

	}

	private int quitCount = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			quitCount++;
			if (quitCount == 2) {
				exitGame();
			} else {
				Toast.makeText(PlayGameActivity.this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
			}
			return true;
		} else {
			quitCount = 0;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void exitGame() {
		UCGameSDK.defaultSDK().exitSDK(PlayGameActivity.this,new UCCallbackListener<String>() {

			@Override
			public void callback(int statuscode, String data) {
				if(UCGameSDKStatusCode.SDK_EXIT_CONTINUE==statuscode){
					//继续游戏的逻辑
				}else if (UCGameSDKStatusCode.SDK_EXIT==statuscode) {
					//退出游戏的逻辑
					UCGameSDK.defaultSDK().destoryFloatButton(PlayGameActivity.this);
					PlayGameActivity.this.finish();
				}
			}
		});
	}

}
