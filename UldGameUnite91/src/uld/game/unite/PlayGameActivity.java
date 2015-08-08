package uld.game.unite;

import com.nd.commplatform.gc.widget.NdToolBar;
import com.nd.commplatform.gc.widget.NdToolBarPlace;
import org.xuehu.aj.aj91.R;

import uld.sdk.tools.Utility;
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
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

	private NdToolBar ndToolBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_playgame);

		ndToolBar = NdToolBar.create(this, NdToolBarPlace.NdToolBarBottomRight);
		ndToolBar.show();

		findViews();

		setListeners();
	}

	private void findViews() {

		btnRecharge = (Button) findViewById(R.id.btnRecharge);
		btnViewUserInfo = (Button) findViewById(R.id.btnViewUserInfo);

		btn91BBS = (Button) findViewById(R.id.btnBBS);
		btn91UserFeedBack = (Button) findViewById(R.id.btnUserFeedBack);
	}

	private void setListeners() {
		btnRecharge.setOnClickListener(new RechargeClickListener());
		btnViewUserInfo.setOnClickListener(new ViewUserInfoClickListener());

		btn91BBS.setOnClickListener(new BBSClickListener());
		btn91UserFeedBack.setOnClickListener(new UserFeedBackListener());
	}

	// 充值调用
	class RechargeClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// 初始化
			uld.sdk.unite.GameInfo gameInfo = new GameInfo();
			gameInfo.setGameId(MainActivity.gameId);
			gameInfo.setServerId(MainActivity.serverId);

			uld.sdk.unite.UldPlatform.getInstance().recharge(gameInfo,
					PlayGameActivity.this, new RechargeCallBackListener() {

						public void onRechargeUiFinished(
								RechargeResult rechargeResult) {
							if (!Utility.isEmpty(rechargeResult.getOrderId())) {
								// if (MainActivity.IsXiaomi) {
								// Looper.prepare();
								// }
								Toast.makeText(
										PlayGameActivity.this,
										"订单编号："
												+ rechargeResult.getOrderId()
												+ "订单信息:"
												+ rechargeResult.getOrderMsg()
												+ "充值金额:"
												+ rechargeResult
														.getPayAccount(),
										Toast.LENGTH_SHORT).show();
								// if (MainActivity.IsXiaomi) {
								// Looper.loop();
								// }
							} else {
								// if (MainActivity.IsXiaomi) {
								// Looper.prepare();
								// }
								Toast.makeText(
										PlayGameActivity.this,
										"取消订单，订单信息"
												+ rechargeResult.getOrderMsg(),
										Toast.LENGTH_SHORT).show();
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
			uld.sdk.unite.UldPlatform.getInstance().gotoPlatform(
					PlayGameActivity.this);
		}

	}

	private int quitCount = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			quitCount++;
			if (quitCount == 2) {
				// if(MainActivity.Is91NewSdk){
				// NdCommplatform.getInstance().ndExit(new
				// OnExitCompleteListener(this) {
				//
				// @Override
				// public void onComplete() {
				// System.exit(0);
				// finish();
				// }
				// });
				// }else{
				System.exit(0);
				finish();
				// }

			} else {
				Toast.makeText(PlayGameActivity.this, "再按一次返回退出程序",
						Toast.LENGTH_SHORT).show();
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
