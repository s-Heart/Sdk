package com.downjoy.hj.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.downjoy.CallbackListener;
import com.downjoy.Downjoy;
import com.downjoy.DownjoyError;
import com.downjoy.util.Util;

public class DemoActivity extends Activity {

	/** 当乐游戏中心实例 */
	private Downjoy downjoy;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setTitle("2013-08-23 11:53 当日第1版");
		Window window = getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		params.flags = params.flags | WindowManager.LayoutParams.FLAG_FULLSCREEN;
		window.setAttributes(params);

		// 初始化当乐游戏中心
		final String merchantId = "101"; // 当乐分配的MERCHANT_ID
		final String appId = "195"; // 当乐分配的APP_ID
		final String serverSeqNum = "1"; // 当乐分配的 ERVER_SEQ_NUM，
											// 不同服务器序列号可使用不同计费通知地址
		final String appKey = "j5VEvxhc"; // 当乐分配的 APP_KEY

		// 获取当乐游戏中心的实例
		downjoy = Downjoy.getInstance(DemoActivity.this, merchantId, appId, serverSeqNum, appKey);

		// 设置登录成功后是否显示当乐游戏中心的悬浮按钮
		// 注意：
		// 此处应在调用登录接口之前设置，默认值是true，即登录成功后自动显示当乐游戏中心的悬浮按钮。
		// 如果此处设置为false，登录成功后，不显示当乐游戏中心的悬浮按钮。
		downjoy.showDownjoyIconAfterLogined(true);

		// 登录
		findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				final Context context = view.getContext();

				downjoy.openLoginDialog(context, new CallbackListener() {

					@Override
					public void onLoginSuccess(Bundle bundle) {
						String memberId = bundle.getString(Downjoy.DJ_PREFIX_STR + "mid");
						String username = bundle.getString(Downjoy.DJ_PREFIX_STR + "username");
						String nickname = bundle.getString(Downjoy.DJ_PREFIX_STR + "nickname");
						String token = bundle.getString(Downjoy.DJ_PREFIX_STR + "token");

						Util.alert(context, "mid:" + memberId + "\nusername:" + username + "\nnickname:" + nickname + "\ntoken:" + token);
					}

					@Override
					public void onLoginError(DownjoyError error) {
						int errorCode = error.getMErrorCode();
						String errorMsg = error.getMErrorMessage();
						Util.alert(context, "onLoginError:" + errorCode + "|" + errorMsg);
					}

					@Override
					public void onError(Error error) {
						String errorMessage = error.getMessage();
						Util.alert(context, "onError:" + errorMessage);
					}
				});
			}
		});

		// 个人中心
		findViewById(R.id.info).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				final Context context = view.getContext();
				downjoy.openMemberCenterDialog(context, new CallbackListener() {

					@Override
					public void onError(Error error) {
						String errorMessage = error.getMessage();
						Util.alert(context, "onError:" + errorMessage);
					}
				});
			}
		});

		// 登出
		findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				final Context context = view.getContext();
				downjoy.logout(context, new CallbackListener() {

					@Override
					public void onLogoutSuccess() {
						Util.alert(context, "logout ok");
					}

					@Override
					public void onLogoutError(DownjoyError error) {
						int errorCode = error.getMErrorCode();
						String errorMsg = error.getMErrorMessage();
						Util.alert(context, "onLogoutError:" + errorCode + "|" + errorMsg);
					}

					@Override
					public void onError(Error error) {
						Util.alert(context, "onError:" + error.getMessage());
					}
				});
			}
		});

		// 进入商品支付(0)
		findViewById(R.id.pay_0).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Context context = v.getContext();
				if (!Util.isLogined(context)) {
					Util.showToast(context, "您还没有登录...");
					return;
				}
				float money = 0.0f; // 商品价格，单位：元
				String productName = "测试商品"; // 商品名称
				String extInfo = "123"; // CP自定义信息，多为CP订单号

				// 打开支付界面,获得订单号
				downjoy.openPaymentDialog(context, money, productName, extInfo, new CallbackListener() {

					@Override
					public void onPaymentSuccess(String orderNo) {
						Util.alert(context, "payment success! \n orderNo:" + orderNo);
					}

					@Override
					public void onPaymentError(DownjoyError error, String orderNo) {
						int errorCode = error.getMErrorCode();
						String errorMsg = error.getMErrorMessage();
						Util.alert(context, "onPaymentError:" + errorCode + "|" + errorMsg + "\n orderNo:" + orderNo);
					}

					@Override
					public void onError(Error error) {
						Util.alert(context, "onError:" + error.getMessage());
					}
				});
			}
		});

		// 进入商品支付(0.01)
		findViewById(R.id.pay_1).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Context context = v.getContext();
				if (!Util.isLogined(context)) {
					Util.showToast(context, "您还没有登录...");
					return;
				}
				float money = 0.01f; // 商品价格，单位：元
				String productName = "测试商品"; // 商品名称
				String extInfo = "1234"; // CP自定义信息，多为CP订单号

				// 打开支付界面,获得订单号
				downjoy.openPaymentDialog(context, money, productName, extInfo, new CallbackListener() {

					@Override
					public void onPaymentSuccess(String orderNo) {
						Util.alert(context, "payment success! \n orderNo:" + orderNo);
					}

					@Override
					public void onPaymentError(DownjoyError error, String orderNo) {
						int errorCode = error.getMErrorCode();
						String errorMsg = error.getMErrorMessage();
						Util.alert(context, "onPaymentError:" + errorCode + "|" + errorMsg + "\n orderNo:" + orderNo);
					}

					@Override
					public void onError(Error error) {
						Util.alert(context, "onError:" + error.getMessage());
					}
				});
			}
		});

		// 获取用户信息
		findViewById(R.id.get_info).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Context context = v.getContext();

				downjoy.getInfo(context, new CallbackListener() {

					@Override
					public void onInfoSuccess(Bundle bundle) {
						String memberId = bundle.getString(Downjoy.DJ_PREFIX_STR + "mid");
						String username = bundle.getString(Downjoy.DJ_PREFIX_STR + "username");
						String nickname = bundle.getString(Downjoy.DJ_PREFIX_STR + "nickname");
						String gender = bundle.getString(Downjoy.DJ_PREFIX_STR + "gender");
						int level = Integer.parseInt(bundle.getString(Downjoy.DJ_PREFIX_STR + "level"));
						String avatarUrl = bundle.getString(Downjoy.DJ_PREFIX_STR + "avatarUrl");
						long createdDate = Long.parseLong(bundle.getString(Downjoy.DJ_PREFIX_STR + "createdDate"));

						Util.alert(context, "mid: " + memberId + "\n username: " + username + "\n nickname: " + nickname + "\n gender: "
								+ gender + "\n level: " + level + "\n avatarUrl: " + avatarUrl + "\n createdDate: " + createdDate);
					}

					@Override
					public void onInfoError(DownjoyError error) {
						int errorCode = error.getMErrorCode();
						String errorMsg = error.getMErrorMessage();
						Util.alert(context, "onInfoError:" + errorCode + "|" + errorMsg);
					}

					@Override
					public void onError(Error error) {
						String errorMessage = error.getMessage();
						Util.alert(context, "onError:" + errorMessage);
					}
				});

			}
		});

		// 跳转到另外一个Activity
		findViewById(R.id.start_another_activity).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(DemoActivity.this, AnotherActivity.class));
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (downjoy != null) {
			downjoy.resume(DemoActivity.this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (downjoy != null) {
			downjoy.pause();
		}
	}

	public void onDestroy() {
		super.onDestroy();
		// 执行登出操作
		downjoy.logout(this, new CallbackListener() {

			@Override
			public void onLogoutSuccess() {
				Util.alert(getBaseContext(), "logout ok");
			}

			@Override
			public void onLogoutError(DownjoyError error) {
				int errorCode = error.getMErrorCode();
				String errorMsg = error.getMErrorMessage();
				Util.alert(getBaseContext(), "onLogoutError:" + errorCode + "|" + errorMsg);
			}

			@Override
			public void onError(Error error) {
				Util.alert(getBaseContext(), "onError:" + error.getMessage());
			}
		});

		// 只有在程序退出的时候调用
		if (downjoy != null) {
			downjoy.destroy();
			downjoy = null;
		}
	}

}