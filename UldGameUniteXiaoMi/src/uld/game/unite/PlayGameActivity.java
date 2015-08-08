package uld.game.unite;

import org.xuehu.aj.mi.R;

import com.xiaomi.gamecenter.sdk.MiCommplatform;
import com.xiaomi.gamecenter.sdk.MiErrorCode;
import com.xiaomi.gamecenter.sdk.OnLoginProcessListener;
import com.xiaomi.gamecenter.sdk.entry.MiAccountInfo;

import uld.sdk.tools.Utility;
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginResult;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import android.app.Activity;
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

public class PlayGameActivity extends Activity {

	private Button btnRecharge = null;
	private Button btnViewUserInfo = null;

	private Button btn91BBS = null;
	private Button btn91UserFeedBack = null;
	private Button btnXiaomiLogout = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_playgame);

		findViews();

		setListeners();
	}

	private void findViews() {

		btnRecharge = (Button) findViewById(R.id.btnRecharge);
		btnViewUserInfo = (Button) findViewById(R.id.btnViewUserInfo);

		btn91BBS = (Button) findViewById(R.id.btnBBS);
		btn91UserFeedBack = (Button) findViewById(R.id.btnUserFeedBack);

		btnXiaomiLogout = (Button) findViewById(R.id.btnLogout);
	}

	private void setListeners() {
		btnRecharge.setOnClickListener(new RechargeClickListener());
		btnViewUserInfo.setOnClickListener(new ViewUserInfoClickListener());

		btn91BBS.setOnClickListener(new BBSClickListener());
		btn91UserFeedBack.setOnClickListener(new UserFeedBackListener());

		btnXiaomiLogout.setOnClickListener(new LogoutClickListener());
	}

	// 充值调用
	class RechargeClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// 初始化
			uld.sdk.unite.GameInfo gameInfo = new GameInfo();
			gameInfo.setGameId(MainActivity.gameId);
			gameInfo.setServerId(MainActivity.serverId);

			uld.sdk.unite.UldPlatform.getInstance().recharge(gameInfo, PlayGameActivity.this, new RechargeCallBackListener() {

				public void onRechargeUiFinished(RechargeResult rechargeResult) {
					mHandler.sendMessage(mHandler.obtainMessage(HandleMessage_RechargeFinished, rechargeResult));
					
				}
			});
		}
	}

	// 转到渠道平台，如91平台；修改用户信息，如密码，
	class ViewUserInfoClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			uld.sdk.unite.UldPlatform.getInstance().gotoPlatform(PlayGameActivity.this);
		}

	}

	class LogoutClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			MiCommplatform.getInstance().miLogout(new OnLoginProcessListener() {

				@Override
				public void finishLoginProcess(int code, MiAccountInfo arg1) {
					switch (code) {
					case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_LOGINOUT_FAIL:
						// 注销失败
						break;
					case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_LOGINOUT_SUCCESS:
						// 注销成功 游戏当结束当前页面，返回登录界面
//						PlayGameActivity.this.finish();
						break;
					case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_ACTION_EXECUTED:
						// 操作正在进行中
						break;
					default:
						break;
					}
				}
			});
		}
	}

	private int quitCount = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			quitCount++;
			if (quitCount == 2) {
				System.exit(0);
				finish();
			} else {
				Toast.makeText(PlayGameActivity.this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
			}
			return true;
		} else {
			quitCount = 0;
		}
		return super.onKeyDown(keyCode, event);
	}

	// 论坛
	class BBSClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// if(MainActivity.Is91NewSdk){
			// NdCommplatform.getInstance().ndEnterAppBBS(PlayGameActivity.this,
			// 0);
			// }
		}

	}

	// 反馈
	class UserFeedBackListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// if(MainActivity.Is91NewSdk){
			// NdCommplatform.getInstance().ndUserFeedback(PlayGameActivity.this);
			// }
		}

	}

	
	private final static int HandleMessage_RechargeFinished = 1;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case HandleMessage_RechargeFinished:
				RechargeResult rechargeResult = (RechargeResult)msg.obj;
				if (!Utility.isEmpty(rechargeResult.getOrderId())) {
					Toast.makeText(
							PlayGameActivity.this,
							"订单编号：" + rechargeResult.getOrderId() + "订单信息:" + rechargeResult.getOrderMsg() + "充值金额:"
									+ rechargeResult.getPayAccount(), Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(PlayGameActivity.this, "取消订单，订单信息" + rechargeResult.getOrderMsg(), Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}
		}

	};
}
