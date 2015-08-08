package com.lenovo.id.pay.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovo.lsf.open.LenovoGameSdk;
import com.lenovo.mpay.ifmgr.IPayResultCallback;
import com.lenovo.mpay.ifmgr.SDKApi;
import com.lenovo.mpay.tools.PayRequest;

public class PayActivity extends Activity {

	public Button payButton;
	private AlertDialog.Builder alertDialogBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 支付预读接口
		SDKApi.preGettingData(PayActivity.this, Config.appid);
		showView();
	}

	public void showView() {

		setContentView(R.layout.sdk_main_activity);

		// 1.按次
		final Button pAnci = (Button) findViewById(R.id.pay_anci);
		pAnci.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startPay(1, 10);
			}
		});
		// 2.买断
		findViewById(R.id.pay_maiduan).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startPay(2, 10);
					}
				});

		// 3.批量购买
		findViewById(R.id.pay_pilianggoumai).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startPay(3, 10);
					}
				});
		// 4.包次数
		findViewById(R.id.pay_baoci).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startPay(4, 10);
			}
		});
		// 5.包自然时长
		findViewById(R.id.pay_baoshichang).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startPay(5, 10);
					}
				});
		// 6.按次免费试用
		findViewById(R.id.pay_mianfei).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startPay(6, 10);
					}
				});

		// 7.开发价格
		final EditText etPrice = (EditText) findViewById(R.id.et_price);
		final Button pkaifangjiage = (Button) findViewById(R.id.pay_kaifangjiage);
		pkaifangjiage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String price = etPrice.getText().toString().trim();
				if (TextUtils.isEmpty(price)) {
					Toast.makeText(PayActivity.this, "请输入收费金额",
							Toast.LENGTH_SHORT).show();
					etPrice.requestFocus();
				} else if (Integer.parseInt(price) <= 0) {
					Toast.makeText(PayActivity.this, "收费金额应大于0",
							Toast.LENGTH_SHORT).show();
					etPrice.requestFocus();

				} else {
					startPay(7, Integer.parseInt(price));
				}

			}
		});

		// 退出游戏应用示例
		alertDialogBuilder = new AlertDialog.Builder(this)
				.setMessage("是否确定退出？").setCancelable(true)
				.setPositiveButton("是", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						PayActivity.this.finish();
						/*
						 * 退出游戏应用请务必调用下面方法
						 */
						LenovoGameSdk.exit();
					}
				})
				.setNegativeButton("否", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

	}

	public void startPay(int waresid, int price) {
		PayRequest payRequest = new PayRequest();
		// 请填写商品自己的参数
		payRequest.addParam("notifyurl", Config.notifyurl);
		payRequest.addParam("appid", Config.appid);
		payRequest.addParam("waresid", waresid);
		payRequest.addParam("exorderno", "sample" + System.currentTimeMillis());
		payRequest.addParam("price", price);
		payRequest.addParam("cpprivateinfo", "123456");
		// 生成签名，创建请求url
		String paramUrl = payRequest.genSignedUrlParamString(Config.appkey);
		SDKApi.startPay(PayActivity.this, paramUrl, new IPayResultCallback() {
			@Override
			public void onPayResult(int resultCode, String signValue,
					String resultInfo) {// resultInfo = 应用编号&商品编号&外部订单号
				if (SDKApi.PAY_SUCCESS == resultCode) {
					Log.e(Config.TAG, "signValue = " + signValue);
					if (null == signValue) {
						// 没有签名值，默认采用finish()，请根据需要修改
						Log.e(Config.TAG, "signValue is null ");
						Toast.makeText(PayActivity.this, "sample:没有签名值",
								Toast.LENGTH_SHORT).show();
					}
					boolean flag = PayRequest.isLegalSign(signValue,
							Config.appkey);
					if (flag) {
						Log.e(Config.TAG, "islegalsign: true");
						Toast.makeText(PayActivity.this, "sample:支付成功",
								Toast.LENGTH_SHORT).show();
						// 合法签名值，支付成功，请添加支付成功后的业务逻辑
					} else {
						Toast.makeText(PayActivity.this,
								"sample:支付成功，但是验证签名失败", Toast.LENGTH_SHORT)
								.show();
						// 非法签名值，默认采用finish()，请根据需要修改
					}
				} else if (SDKApi.PAY_CANCEL == resultCode) {
					Toast.makeText(PayActivity.this, "sample:取消支付",
							Toast.LENGTH_SHORT).show();
					// 取消支付处理，默认采用finish()，请根据需要修改
					Log.e(Config.TAG, "return cancel");
				} else {
					Toast.makeText(PayActivity.this, "sample:支付失败",
							Toast.LENGTH_SHORT).show();
					// 计费失败处理，默认采用finish()，请根据需要修改
					Log.e(Config.TAG, "return Error");
				}

			}
		});
	}

	/**
	 * 返回键
	 */
	@Override
	public void onBackPressed() {
		alertDialogBuilder.show();
	}

}