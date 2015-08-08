/**
 * Copyright (C) 2013, all rights reserved.
 * Company	SHENZHEN YUNZHONGFEI TECHNOLOGY CORP., LTD. 
 * Author	lailong
 * Since	2013-10-14
 */
package com.nearme.gamecenter.open.demo.nearme.gamecenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nearme.gamecenter.open.api.ApiCallback;
import com.nearme.gamecenter.open.api.FixedPayInfo;
import com.nearme.gamecenter.open.api.GameCenterSDK;
import com.nearme.gamecenter.open.api.GameCenterSettings;
import com.nearme.gamecenter.open.api.PayInfo;
import com.nearme.gamecenter.open.api.RatePayInfo;
import com.nearme.gamecenter.open.core.util.ImageGetter;
import com.nearme.gamecenter.open.core.util.Util;
import com.nearme.oauth.model.NDouProductInfo;
import com.nearme.oauth.model.UserInfo;

/**
 * 
 * @Author lailong
 * @Since 2013-10-14
 */
public class OpenSDKDemoNewActivity extends ListActivity {

	private List<Entry> mTestBeans = new ArrayList<Entry>();

	private Context mContext;

	private View mHeadView;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameCenterSDK.setmCurrentContext(this);
		if (GameCenterSettings.isOritationPort) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		mContext = this;
		initApiTest();
		initHeadView();
		setListAdapter(new ArrayAdapter<Entry>(this,
				android.R.layout.simple_list_item_1, mTestBeans));
		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					return;
				}
				mTestBeans.get(position - 1).run();
			}
		});
	}

	/**
	 * 
	 */
	private void initHeadView() {
		mHeadView = LayoutInflater.from(this).inflate(R.layout.act_head, null);
		getListView().addHeaderView(mHeadView);
		refreshHeadView();
		refreshNDou();
	}

	private void refreshHeadView() {

		final TextView userName = (TextView) mHeadView
				.findViewById(R.id.username);
		final TextView nbao = (TextView) mHeadView.findViewById(R.id.nbao);

		final ImageView icon = (ImageView) mHeadView.findViewById(R.id.icon);

		GameCenterSDK.getInstance().doGetUserInfo(new ApiCallback() {

			@Override
			public void onSuccess(String content, int code) {
				try {
					final UserInfo userInfo = new UserInfo(content);
					userName.setText(userInfo.username);
					nbao.setText(userInfo.gameBalance / 100 + "");
					new ImageGetter(icon).execute(userInfo.profilePictureUrl);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(String content, int code) {

			}
		}, this);
	}

	private void refreshNDou() {
		final TextView ndou = (TextView) mHeadView.findViewById(R.id.ndou);

		GameCenterSDK.getInstance().doGetUserNDou(new ApiCallback() {

			@Override
			public void onSuccess(String content, int code) {
				try {
					final JSONObject json = new JSONObject(content);
					final String d = json.getString("integralBalance");
					ndou.setText(d);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(String content, int code) {

			}
		}, this);
	}

	private abstract class ApiTestBean implements Entry {

		private String mDescription;

		private ApiTestBean(final String description) {
			mDescription = description;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.nearme.gamecenter.open.demo.nearme.gamecenter.OpenSDKDemoNewActivity
		 * .Entry#getDescription()
		 */
		@Override
		public String getDescription() {
			return mDescription;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return getDescription();
		}

	}

	private interface Entry {

		public String toString();

		public String getDescription();

		public void run();
	}

	private final void makeToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * 初始化测试bean
	 */
	private void initApiTest() {

		initGetUserInfo();

		initGetUserKebi();

		initPaymentForKebiNormal();

		initPaymentForKebiRate();

		initPaymentForKebiFixed();

		initKebiCharge();

		initShowGameCenter();

		initShowForum();

		initReLogin();

		initShowProfileSetting();

		initSprite();

		initGetUserNDou();

		initPaymentForNDou();

		initSwitchScreen();

	}

	/**
	 * 
	 */
	private void initPaymentForKebiFixed() {
		final String des = "API_doFixedKebiPayment_消耗用户可币(固定金额)";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				showInputKebiFixed();
			}
		};
		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initPaymentForKebiRate() {
		final String des = "API_doRateKebiPayment_消耗用户可币(比例)";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				showInputKebiRate();
			}
		};
		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initKebiCharge() {
		final String des = "API_doShowKebiCharge_显示可币充值页面";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				GameCenterSDK.getInstance().doShowKebiCharege(
						new ApiCallback() {

							@Override
							public void onSuccess(String content, int code) {
								refreshHeadView();
								makeToast("刷新可币");
							}

							@Override
							public void onFailure(String content, int code) {
								makeToast("可币充值失败");
							}
						}, OpenSDKDemoNewActivity.this);
			}
		};
		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initSwitchScreen() {
		final String des = "API_横竖屏切换";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				if (GameCenterSettings.isOritationPort) {
					GameCenterSettings.isOritationPort = false;
				} else {
					GameCenterSettings.isOritationPort = true;
				}
			}
		};
		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initPaymentForNDou() {
		final String des = "API_doPaymentForNDou_消耗用户N豆";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				showInputNDou();
			}
		};
		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initGetUserNDou() {
		final String des = "API_doGetUserNDou_获取用户N豆余额";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				GameCenterSDK.getInstance().doGetUserNDou(new ApiCallback() {
					@Override
					public void onSuccess(String arg0, int arg1) {
						makeToast("获取用户N豆成功:" + arg0 + "#" + arg1);

					}

					@Override
					public void onFailure(String arg0, int code) {
						makeToast("获取用户N豆失败:" + arg0 + "#" + code);
					}
				}, OpenSDKDemoNewActivity.this);
			}

		};
		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initSprite() {

		final String des1 = "API_doShowSprite_显示游戏堂精灵";

		final Entry bean1 = new ApiTestBean(des1) {

			@Override
			public void run() {
				GameCenterSDK.getInstance().doShowSprite(
						OpenSDKDemoNewActivity.this);
			}
		};

		mTestBeans.add(bean1);

		final String des2 = "API_doDismissSprite_消失游戏堂精灵";

		final Entry bean2 = new ApiTestBean(des2) {

			@Override
			public void run() {
				GameCenterSDK.getInstance().doDismissSprite(
						OpenSDKDemoNewActivity.this);
			}
		};

		mTestBeans.add(bean2);
	}

	/**
	 * 
	 */
	private void initShowProfileSetting() {

		final String des = "API_doShowProfileSetting_显示用户信息";

		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				GameCenterSDK.getInstance().doShowProfileSetting(
						OpenSDKDemoNewActivity.this);
			}
		};

		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initReLogin() {

		final String des = "API_doReLogin_切换账号";

		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				GameCenterSDK.getInstance().doReLogin(new ApiCallback() {

					@Override
					public void onSuccess(String content, int code) {
						makeToast("切换账号成功：" + content + "#" + code);
						refreshHeadView();
						refreshNDou();
					}

					@Override
					public void onFailure(String content, int code) {
						makeToast("切换账号失败：" + content + "#" + code);
					}
				}, OpenSDKDemoNewActivity.this);
			}
		};

		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initShowForum() {
		final String des = "API_doShowForum_打开论坛";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				GameCenterSDK.getInstance().doShowForum(
						OpenSDKDemoNewActivity.this);
			}
		};
		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initShowGameCenter() {
		final String des = "API_doShowGameCenter_打开游戏堂";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				GameCenterSDK.getInstance().doShowGameCenter(
						OpenSDKDemoNewActivity.this);
			}
		};
		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initPaymentForKebiNormal() {
		final String des = "API_doNormalKebiPayment_消耗用户可币(普通)";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				showInputKebiNormal();
			}
		};
		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initGetUserKebi() {
		final String des = "API_doCheckBalance_获取用户可币余额";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				GameCenterSDK.getInstance().doCheckBalance(new ApiCallback() {
					@Override
					public void onSuccess(String arg0, int arg1) {
						try {
							JSONObject jsonObject = new JSONObject(arg0);
							String balance = jsonObject.getString("gameBalance");
							makeToast("获取用户可币成功:" + arg0 + "#" + arg1 + "#"
									+ balance);
						} catch (Exception e) {
							makeToast("获取用户可币失败:" + arg0 + "#" + arg1);
						}
					}

					@Override
					public void onFailure(String arg0, int code) {
						makeToast("获取用户可币失败:" + arg0 + "#" + code);
					}
				}, OpenSDKDemoNewActivity.this);
			}

		};
		mTestBeans.add(bean);
	}

	/**
	 * 
	 */
	private void initGetUserInfo() {
		final String des = "API_doGetUserInfo_获取用户信息";
		final Entry bean = new ApiTestBean(des) {

			@Override
			public void run() {
				GameCenterSDK.getInstance().doGetUserInfo(new ApiCallback() {
					@Override
					public void onSuccess(String arg0, int arg1) {
						makeToast("获取用户信息成功:" + arg0 + "#" + arg1);
						try {
							JSONObject jsonObject = new JSONObject(arg0);
							jsonObject.getJSONObject(
									"BriefUser").getString("userName");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(String arg0, int code) {
						makeToast("获取用户信息失败:" + arg0 + "#" + code);
					}
				}, OpenSDKDemoNewActivity.this);
			}
		};
		mTestBeans.add(bean);
	}

	private void showInputKebiNormal() {
		LayoutInflater factory = LayoutInflater.from(mContext);
		final View textEntryView = factory.inflate(
				R.layout.alert_dialog_text_entry, null);
		new AlertDialog.Builder(mContext)
				.setTitle("请输入你要支付的可币金额,单位为分")
				.setView(textEntryView)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						EditText tv = (EditText) textEntryView
								.findViewById(R.id.username_edit);
						final String username = tv.getText().toString();
						if (TextUtils.isEmpty(username)) {
							return;
						} else {
							try {
								callNormalKebiPayment(Integer
										.parseInt(username));
							} catch (Exception e) {
							}
						}
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						}).create().show();
	}

	private void showInputKebiFixed() {
		LayoutInflater factory = LayoutInflater.from(mContext);
		final View textEntryView = factory.inflate(
				R.layout.alert_dialog_text_entry, null);
		new AlertDialog.Builder(mContext)
				.setTitle("请输入你要支付的可币金额,单位为分")
				.setView(textEntryView)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						EditText tv = (EditText) textEntryView
								.findViewById(R.id.username_edit);
						final String username = tv.getText().toString();
						if (TextUtils.isEmpty(username)) {
							return;
						} else {
							try {
								callFixedKebiPayment(Integer.parseInt(username));
							} catch (Exception e) {
							}
						}
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						}).create().show();
	}
	
	private void showInputKebiRate() {
		LayoutInflater factory = LayoutInflater.from(mContext);
		final View textEntryView = factory.inflate(
				R.layout.alert_dialog_text_entry, null);
		new AlertDialog.Builder(mContext)
				.setTitle("请输入您的货币兑换RMB的比例")
				.setView(textEntryView)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						EditText tv = (EditText) textEntryView
								.findViewById(R.id.username_edit);
						final String username = tv.getText().toString();
						if (TextUtils.isEmpty(username)) {
							return;
						} else {
							try {
								int rate = Integer.parseInt(username);
								if (rate == 0) {
									return;
								}
								callRateKebiPayment(rate);
							} catch (Exception e) {
							}
						}
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						}).create().show();
	}

	private void showInputNDou() {
		LayoutInflater factory = LayoutInflater.from(mContext);
		final View textEntryView = factory.inflate(
				R.layout.alert_dialog_text_entry, null);
		new AlertDialog.Builder(mContext)
				.setTitle("请输入你要支付的N豆金额")
				.setView(textEntryView)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						EditText tv = (EditText) textEntryView
								.findViewById(R.id.username_edit);
						final String username = tv.getText().toString();
						if (TextUtils.isEmpty(username)) {
							return;
						} else {
							try {
								callNDouPayment(Integer.parseInt(username));
							} catch (Exception e) {
							}
						}
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

							}
						}).create().show();
	}

	private void callNormalKebiPayment(int amount) {
		final PayInfo payInfo = new PayInfo(System.currentTimeMillis()
				+ new Random().nextInt(1000) + "", "自定义字段", (int) amount);
		payInfo.setProductDesc("商品描述");
		payInfo.setProductName("商品名");
		payInfo.setCallbackUrl("http://gamecenter.wanyol.com:8080/gamecenter/callback_test_url");
		GameCenterSDK.getInstance().doNormalKebiPayment(kebiPayment, payInfo,
				this);
	}

	private void callRateKebiPayment(int rate) {
		final RatePayInfo payInfo = new RatePayInfo(System.currentTimeMillis()
				+ new Random().nextInt(1000) + "", "自定义字段");
		payInfo.setProductDesc("商品描述");
		payInfo.setProductName("红钻");
		payInfo.setCallbackUrl("http://gamecenter.wanyol.com:8080/gamecenter/callback_test_url");
		payInfo.setRate(rate);
		payInfo.setDefaultShowCount(rate + new Random().nextInt(10));
		GameCenterSDK.getInstance().doRateKebiPayment(kebiPayment, payInfo,
				this);
	}

	private void callFixedKebiPayment(int amount) {
		final FixedPayInfo payInfo = new FixedPayInfo(
				System.currentTimeMillis() + new Random().nextInt(1000) + "",
				"自定义字段", amount);
		payInfo.setProductDesc("商品描述");
		payInfo.setProductName("符石");
		payInfo.setCallbackUrl("http://gamecenter.wanyol.com:8080/gamecenter/callback_test_url");
		payInfo.setGoodsCount(300);
		GameCenterSDK.getInstance().doFixedKebiPayment(kebiPayment, payInfo,
				this);
	}

	private void callNDouPayment(int amount) {
		final NDouProductInfo p1 = new NDouProductInfo(
				amount,
				"http://gamecenter.wanyol.com:8080/gamecenter/callback_test_url",
				System.currentTimeMillis() + new Random().nextInt(1000) + "",
				"商品名", "商品描述", "自定义字段");
		GameCenterSDK.getInstance().doPaymentForNDou(new ApiCallback() {

			@Override
			public void onSuccess(String content, int code) {
				refreshNDou();
				makeToast("消耗N豆成功：" + content + "#" + code);
			}

			@Override
			public void onFailure(String content, int code) {
				makeToast("消耗N豆失败：" + content + "#" + code);
			}
		}, p1, this);
	}

	private ApiCallback kebiPayment = new ApiCallback() {
		@Override
		public void onSuccess(String content, int code) {
			Util.makeToast("消耗可币成功:" + content + "#" + code,
					OpenSDKDemoNewActivity.this);
			refreshHeadView();
		}

		@Override
		public void onFailure(String content, int code) {
			Util.makeToast("消耗可币失败:" + content, OpenSDKDemoNewActivity.this);
		}
	};
	
	private void doGetAccessToken() {
		final String data = GameCenterSDK.getInstance().doGetAccessToken();
		final String token_key = data.split("&")[0].split("=")[1];
		final String token_secret = data.split("&")[1].split("=")[1];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		GameCenterSDK.getInstance().doShowSprite(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		GameCenterSDK.getInstance().doDismissSprite(this);
	}
}
