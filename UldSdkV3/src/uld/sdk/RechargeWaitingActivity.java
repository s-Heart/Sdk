package uld.sdk;

import java.util.Dictionary;
import java.util.Hashtable;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.R;
import wh.order.model.OrderEnum.OrderType;
import wh.order.model.OrderEnum.PayType;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RechargeWaitingActivity extends BaseActivity {
	private Button btnBack = null;
	private TextView tvRechargeTitle = null;
	private TextView tvServiceHotline = null;
	private TextView tvRechargeGameTip = null;
	private Button btnBackGame = null;
	private ProgressBar pbRechargeLoading = null;
	private TextView tvRechargeMsg = null;
	private TextView tvRechargeGame = null;
	private LinearLayout llRechargeGame = null;

	private Dictionary<PayType, String> rechargeDictionary = new Hashtable<PayType, String>();

	private int rechargeValue = 0;
	private PayType payType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		int widthDivideDensity = (int) (outMetrics.widthPixels / outMetrics.scaledDensity);

		if (widthDivideDensity <= valueOfWidthDivideDensity) {
			setContentView(R.layout.activity_rechargewaiting_320);
		} else {
			setContentView(R.layout.activity_rechargewaiting);
		}

		initRechargeDictionary();

		findViews();

		setListeners();

		getDataFromIntent();

		setRechargeTitle(payType);

		setRechargeGameTip(rechargeValue);

		// setBackGameStyle(btnBackGame.isEnabled());

		checkRechargeResult();
	}

	private void initRechargeDictionary() {
		if (rechargeDictionary.size() == 0) {
			rechargeDictionary.put(PayType.PayPal, getString(R.string.uld_paypal));
			// 网上银行-银联
			rechargeDictionary.put(PayType.网上银行, getString(R.string.uld_ylzf));
			rechargeDictionary.put(PayType.支付宝, getString(R.string.uld_alipay));
			rechargeDictionary.put(PayType.电信, getString(R.string.uld_zgdx));
			rechargeDictionary.put(PayType.联通, getString(R.string.uld_zglt));
			rechargeDictionary.put(PayType.神州行, getString(R.string.uld_zgydk));
			rechargeDictionary.put(PayType.骏网一卡通支付, getString(R.string.uld_jwykt));
			rechargeDictionary.put(PayType.盛大卡, getString(R.string.uld_sdk));
			rechargeDictionary.put(PayType.网易一卡通, getString(R.string.uld_wyykt));
			rechargeDictionary.put(PayType.完美一卡通支付, getString(R.string.uld_wmykt));
			rechargeDictionary.put(PayType.征途卡支付, getString(R.string.uld_ztk));
			rechargeDictionary.put(PayType.久游一卡通支付, getString(R.string.uld_jyykt));
			rechargeDictionary.put(PayType.易宝E卡通支付, getString(R.string.uld_ybekt));
			rechargeDictionary.put(PayType.纵游一卡通, getString(R.string.uld_zyykt));
			rechargeDictionary.put(PayType.天下一卡通, getString(R.string.uld_txykt));
		}
	}

	private void findViews() {
		btnBack = (Button) findViewById(R.id.btnBack);
		tvRechargeTitle = (TextView) findViewById(R.id.tvRechargeTitle);
		tvRechargeGameTip = (TextView) findViewById(R.id.tvRechargeGameTip);
		btnBackGame = (Button) findViewById(R.id.btnBackGame);
		pbRechargeLoading = (ProgressBar) findViewById(R.id.pbRechargeLoading);
		tvRechargeMsg = (TextView) findViewById(R.id.tvRechargeMsg);
		tvRechargeGame = (TextView) findViewById(R.id.tvRechargeGame);
		llRechargeGame = (LinearLayout) findViewById(R.id.llRechargeGame);

		// tvServiceHotline = (TextView) findViewById(R.id.tvServiceHotline);
		// String servicehotline = tvServiceHotline.getText().toString();
		// servicehotline = servicehotline.replace("#", "-");
		// tvServiceHotline.setText(servicehotline);

		tvServiceHotline = (TextView) findViewById(R.id.tvServiceHotline);
		// 更改客服热线与
		StringBuffer servicehotline = new StringBuffer();
		String[] homeUrls = getContent().split("\\|_\\|");
		for (int i = 0; i < homeUrls.length; i++) {
			if (homeUrls[i].contains("hotline")) {
				servicehotline.append(homeUrls[i]);
			}
		}
		String hotline = servicehotline.toString().replace("hotline=", "客服热线:");
		tvServiceHotline.setText(hotline);
	}

	private void setListeners() {
		btnBack.setOnClickListener(new BackClickListener());
		btnBackGame.setOnClickListener(new BackGameClickListener());
	}

	class BackClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	class BackGameClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			finish();
		}
	}

	private void getDataFromIntent() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		BaseActivity.setCurrentOrderId(bundle.getInt("OrderId"));
		rechargeValue = bundle.getInt("RechargeValue");
		payType = PayType.parse(bundle.getByte("PayType"));
	}

	private void setRechargeGameTip(Integer rechargeValue) {
		double payTypeRate = 1;
		// switch (payType) {
		// case 网上银行:
		// case G币返还:
		// case 钱包:
		// case 支付宝:
		// case PayPal:
		// payTypeRate = 1;
		// break;
		// case 神州行:
		// payTypeRate = 0.9;
		// break;
		// default:
		// payTypeRate = 0.8;
		// break;
		// }

		String rechargeGameTip = (int) (payTypeRate * rechargeValue * getRechargeRate()) + getRechargeUnit();
		tvRechargeGameTip.setText(rechargeGameTip);
	}

	private void setRechargeTitle(PayType payType) {
		tvRechargeTitle.setText("\"" + rechargeDictionary.get(payType) + "\"");
	}

	private void setBackGameStyle(boolean enabled) {
		if (enabled) {
			btnBackGame.setTextColor(getResources().getColor(R.color.uld_white));
		} else {
			btnBackGame.setTextColor(getResources().getColor(R.color.uld_disabledcolor));
		}
	}

	private void checkRechargeResult() {
		new Thread() {
			public void run() {
				boolean isOver = false;
				int i = 0;
				while (!isOver) {
					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();

					MessageBody messageBody = new MessageBody();
					messageBody.setClassPathName("uld.sdk.bll.User");
					messageBody.setMethodName("getOrderType");
					messageBody.setParmList(new Object[] { BaseActivity.getCurrentOrderId() });
					messageBody.setParmTypeList(new Class<?>[] { int.class });
					MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
					if (messageReturn.findErr()) {
						setRechargeFailedTip(messageReturn.getErrMsg());
						isOver = true;
						break;
					} else {
						OrderType orderType = (OrderType) messageReturn.getRetObject();

						// 1:充值已提交、2:充值失败、0充值成功
						if (orderType == OrderType.游戏方处理成功) {
							BaseActivity.setCurrentOrderType(0);
							setRechargeSucceedTip("充值成功");
							isOver = true;
							break;
						} else if (orderType == OrderType.游戏方处理失败) {
							BaseActivity.setCurrentOrderType(1);
							setRechargeSucceedTip("您的订单已提交成功，正在处理，您可以返回游戏进行等待");
							isOver = true;
							break;
						} else if (orderType == OrderType.处理失败) {
							BaseActivity.setCurrentOrderType(2);
							setRechargeFailedTip("处理失败，卡号或者密码错误");
							isOver = true;
							break;
						} else {
							i++;
							if (i > 2) {
								BaseActivity.setCurrentOrderType(1);
								setRechargeFailedTip("您的订单已提交成功，正在处理，您可以返回游戏进行等待");
								isOver = true;
								break;
							}
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
							}
						}
					}
				}
			}
		}.start();
	}

	private void setRechargeFailedTip(final String failedMsg) {
		pbRechargeLoading.post(new Runnable() {
			@Override
			public void run() {
				pbRechargeLoading.setVisibility(View.GONE);
			}
		});
		tvRechargeMsg.post(new Runnable() {
			@Override
			public void run() {
				tvRechargeMsg.setText(failedMsg);
				tvRechargeMsg.setTextColor(getResources().getColor(R.color.uld_errmsgcolor));
			}
		});
		llRechargeGame.post(new Runnable() {
			@Override
			public void run() {
				llRechargeGame.setVisibility(View.GONE);
			}
		});
	}

	private void setRechargeSucceedTip(final String succeedMsg) {
		pbRechargeLoading.post(new Runnable() {
			@Override
			public void run() {
				pbRechargeLoading.setVisibility(View.GONE);
			}
		});
		tvRechargeMsg.post(new Runnable() {
			@Override
			public void run() {
				tvRechargeMsg.setText(succeedMsg);
				tvRechargeMsg.setTextColor(getResources().getColor(R.color.uld_sucesscolor));
			}
		});

		tvRechargeGame.post(new Runnable() {
			@Override
			public void run() {
				tvRechargeGame.setText("恭喜您获得：");
			}
		});
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
