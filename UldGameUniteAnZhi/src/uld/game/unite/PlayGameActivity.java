package uld.game.unite;

import org.xuehu.aj.anzhi.R;

import uld.sdk.tools.Utility;
import uld.sdk.unite.GameInfo;
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
	private Button btnLoginOut = null;

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
		btnLoginOut = (Button) findViewById(R.id.btncancellation);
	}

	private void setListeners() {
		btnRecharge.setOnClickListener(new RechargeClickListener());
		btnViewUserInfo.setOnClickListener(new ViewUserInfoClickListener());
		btnLoginOut.setOnClickListener(new LoginOutoClickListener());
	}

	// 充值调用
	class RechargeClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// 初始化
			uld.sdk.unite.GameInfo gameInfo = new GameInfo();
			gameInfo.setGameId(MainActivity.gameId);
			gameInfo.setServerId(MainActivity.serverId);
			gameInfo.setPlayerId("your player id");

			uld.sdk.unite.UldPlatform.getInstance().recharge(gameInfo, PlayGameActivity.this, new RechargeCallBackListener() {

				public void onRechargeUiFinished(RechargeResult rechargeResult) {
					if (!Utility.isEmpty(rechargeResult.getOrderId())) {

						Toast.makeText(
								PlayGameActivity.this,
								"订单编号：" + rechargeResult.getOrderId() + "订单信息:" + rechargeResult.getOrderMsg() + "充值金额:"
										+ rechargeResult.getPayAccount(), Toast.LENGTH_SHORT).show();

					} else {

						Toast.makeText(PlayGameActivity.this, "取消订单，订单信息" + rechargeResult.getOrderMsg(), Toast.LENGTH_SHORT).show();

					}
				}
			});
		}
	}

	// 转到渠道平台
	class ViewUserInfoClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			uld.sdk.unite.UldPlatform.getInstance().gotoPlatform(PlayGameActivity.this);
		}
	}

	// 切换账号
	class LoginOutoClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			uld.sdk.unite.UldPlatform.getInstance().gotoCancellation(PlayGameActivity.this);
		}

	}
}
