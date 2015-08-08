package uld.game.unite;

import org.xuehu.aj.aj360.R;

import uld.sdk.tools.Utility;
import uld.sdk.unite.AntiAddictedCallBackListener;
import uld.sdk.unite.AntiAddictedResult;
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import android.app.Activity;
import android.os.Bundle;
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
	private Button btnsetFloatingBar = null;
	private Button btnAntiAddiction = null;
	private Button btnRealNameRegister = null;

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

		btnsetFloatingBar = (Button) findViewById(R.id.btnsetfloatingbar);
		btnAntiAddiction = (Button) findViewById(R.id.btnantiaddiction);
		btnRealNameRegister = (Button) findViewById(R.id.btnrealnameregister);

	}

	private void setListeners() {
		btnRecharge.setOnClickListener(new RechargeClickListener());
		btnViewUserInfo.setOnClickListener(new ViewUserInfoClickListener());

		btn91BBS.setOnClickListener(new BBSClickListener());
		btn91UserFeedBack.setOnClickListener(new UserFeedBackListener());

		btnsetFloatingBar.setOnClickListener(new FloatingbarListener());
		btnAntiAddiction.setOnClickListener(new AntiAddictionListener());
		btnRealNameRegister.setOnClickListener(new RealNameRegisterListener());
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
					if (!Utility.isEmpty(rechargeResult.getOrderId())) {
						// if (MainActivity.IsXiaomi) {
						// Looper.prepare();
						// }
						Toast.makeText(
								PlayGameActivity.this,
								"订单编号：" + rechargeResult.getOrderId() + "订单信息:" + rechargeResult.getOrderMsg() + "充值金额:"
										+ rechargeResult.getPayAccount(), Toast.LENGTH_SHORT).show();
						// if (MainActivity.IsXiaomi) {
						// Looper.loop();
						// }
					} else {
						// if (MainActivity.IsXiaomi) {
						// Looper.prepare();
						// }
						Toast.makeText(PlayGameActivity.this, "取消订单，订单信息" + rechargeResult.getOrderMsg(), Toast.LENGTH_SHORT).show();
						// if (MainActivity.IsXiaomi) {
						// Looper.loop();
						// }
					}
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

	private int quitCount = 0;

	// @Override
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	// if (keyCode == KeyEvent.KEYCODE_BACK) {
	// quitCount++;
	// if (quitCount == 2) {
	// System.exit(0);
	// } else {
	// Toast.makeText(PlayGameActivity.this, "再按一次返回退出程序",
	// Toast.LENGTH_SHORT).show();
	// }
	// return true;
	// } else {
	// quitCount = 0;
	// }
	// return super.onKeyDown(keyCode, event);
	// }

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

	// 浮动窗
	class FloatingbarListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			uld.sdk.unite.UldPlatform.getInstance().gotoFloatingBar(PlayGameActivity.this);
		}
	}

	// 防沉迷
	class AntiAddictionListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			uld.sdk.unite.UldPlatform.getInstance().gotoAntiAddiction(PlayGameActivity.this, new AntiAddictedCallBackListener() {

				@Override
				public void onAntiAddictedFinished(AntiAddictedResult antiaddictedResult) {
					antiaddictedResult.getAntiAddictionMsg();
				}
			});
		}
	}

	// 实名注册
	class RealNameRegisterListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			uld.sdk.unite.UldPlatform.getInstance().gotoRealNameRegister(PlayGameActivity.this);
		}
	}

	// 浮动窗销毁
	protected void onDestroy() {
		uld.sdk.unite.UldPlatform.getInstance().gotoFinishFloatingBar(PlayGameActivity.this);
		super.onDestroy();
	}

}
