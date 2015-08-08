package uld.game.unite;

import org.xuehu.aj.gfan.R;

import uld.sdk.tools.Utility;
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class PlayGameActivity extends Activity {

	private Button btnRecharge = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_playgame);

		findViews();

		setListeners();
	}

	private void findViews() {
		btnRecharge = (Button) findViewById(R.id.btnRecharge);
	}

	private void setListeners() {
		btnRecharge.setOnClickListener(new RechargeClickListener());

	}

	// 充值调用接口
	class RechargeClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// 初始化
			uld.sdk.unite.GameInfo gameInfo = new GameInfo();
			gameInfo.setGameId(MainActivity.gameId);
			gameInfo.setServerId(MainActivity.serverId);
			gameInfo.setUldPid(MainActivity.PID);
			gameInfo.setPlayerId("your player id");

			uld.sdk.unite.UldPlatform.getInstance().recharge(gameInfo,
					PlayGameActivity.this, new RechargeCallBackListener() {

						public void onRechargeUiFinished(
								RechargeResult rechargeResult) {
							if (!Utility.isEmpty(rechargeResult.getOrderId())) {
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
							} else {
								Toast.makeText(
										PlayGameActivity.this,
										"取消订单，订单信息"
												+ rechargeResult.getOrderMsg(),
										Toast.LENGTH_SHORT).show();
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
				exit();
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

	// 先调用退出接口，再退出游戏
	private void exit() {
		uld.sdk.unite.UldPlatform.getInstance().gotoFinish(
				PlayGameActivity.this);
		finish();
	}

}
