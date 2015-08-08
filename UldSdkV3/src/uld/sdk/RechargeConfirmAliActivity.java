package uld.sdk;

import java.util.Locale;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import uld.sdk.R;
import wh.member.model.User;
import wh.order.model.OrderEnum.ChargeType;
import wh.order.model.OrderEnum.PaySourceType;
import wh.order.model.OrderEnum.PayType;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

public class RechargeConfirmAliActivity extends BaseActivity {

	private WebView wvAli = null;
	private Button btnBack = null;
	private LinearLayout llLoading = null;
	private int rechargeValue = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int widthDivideDensity = (int) (outMetrics.widthPixels / outMetrics.scaledDensity);

		if (widthDivideDensity <= valueOfWidthDivideDensity) {
			setContentView(R.layout.activity_rechargealiconfirm_320);
		} else {
			setContentView(R.layout.activity_rechargealiconfirm);
		}

		findViews();

		getDataFromIntent();

		setListeners();

		initViewsAndData();
	}

	private void initViewsAndData() {

		BaseActivity.setCurrentOrderId(0);
		BaseActivity.setCurrentOrderType(3);

		// 设置WebView属性
		// wvAli.setVisibility(View.GONE);
		WebSettings webSettings = wvAli.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setBuiltInZoomControls(true);

		int userId = getUserId();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("http://wappayali.ulaoda.com/req.ashx?");
		sBuilder.append("userId=" + userId);
		sBuilder.append("&sign=" + Utility.encodeMD5(userId + "_ulaoda_recharge"));
		sBuilder.append("&paySourceType=" + PaySourceType.Android客户端.getValue());
		sBuilder.append("&chargeType=" + ChargeType.充值游戏.getValue());
		sBuilder.append("&payType=" + PayType.支付宝.getValue());
		sBuilder.append("&gameId=" + getGameId());
		sBuilder.append("&serverId=" + getServerId());
		sBuilder.append("&playerId=" + getPlayerId());
		sBuilder.append("&payAccount=" + rechargeValue);
		sBuilder.append("&mobilePhone=" + getMobilePhone());
		sBuilder.append("&title=UldGame");

		// 添加两个字段给亚雄生成订单与orderChannel表数据
		// 还剩RegChannelId由亚雄根据userId去查表找寻,客户端不建议频繁使用查表操作
		sBuilder.append("&ocChannelId=" + getChannelId());
		sBuilder.append("&gameChannelId=" + 7);

		sBuilder.toString();
		wvAli.loadUrl(sBuilder.toString());
	}

	private void getDataFromIntent() {
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				rechargeValue = bundle.getInt("RechargeValue");
			}
		}
	}

	private void findViews() {
		llLoading = (LinearLayout) findViewById(R.id.llLoading);
		btnBack = (Button) findViewById(R.id.btnBack);

		wvAli = (WebView) findViewById(R.id.wvAli);
	}

	private int webFinishedTimes = 0;

	private void setListeners() {
		btnBack.setOnClickListener(new BackClickListener());
		wvAli.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				webFinishedTimes++;
				if (url.contains("wappaygw.alipay.com/cashier/") || webFinishedTimes == 2) {
					stopWaitingAlipay();
				}
				// Toast.makeText(RechargeConfirmAliActivity.this, url,
				// Toast.LENGTH_SHORT).show();
				// System.out.println(url);

				// Toast.makeText(RechargeConfirmAliActivity.this, "success",
				// Toast.LENGTH_SHORT).show();
				if (url.toLowerCase(Locale.CHINA).contains("wappayali.ulaoda.com/success.ashx")) {
					String[] paraStrings = url.split("orderid=");
					if (paraStrings.length > 1) {
						BaseActivity.setCurrentOrderId(Utility.getInt(paraStrings[1]));
						BaseActivity.setCurrentOrderType(0);
					}
					finish();
				} else if (url.toLowerCase(Locale.CHINA).contains("wappayali.ulaoda.com/fail.ashx")) {
					String[] paraStrings = url.split("orderid=");
					if (paraStrings.length > 1) {
						BaseActivity.setCurrentOrderId(Utility.getInt(paraStrings[1]));
						BaseActivity.setCurrentOrderType(2);
					}
					finish();
				}
			}
		});
	}

	class BackClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	private void stopWaitingAlipay() {
		llLoading.setVisibility(View.GONE);
	}

	@Override
	public void finish() {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("OrderId", Integer.toString(BaseActivity.getCurrentOrderId()));
		bundle.putString("OrderType", Integer.toString(BaseActivity.getCurrentOrderType()));
		bundle.putString("PayAccount", String.valueOf(rechargeValue));
		intent.putExtras(bundle);
		setResult(Activity.RESULT_OK, intent);
		super.finish();
	}

}
