package uld.sdk.others;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import uld.sdk.unite.LogoutCallBackListener;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import uld.sdk.unite.UldBase;
import uld.sdk.unite.UldPlatform;
import wh.order.model.OrderEnum;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.sogou.gamecenter.sdk.SogouGamePlatform;
import com.sogou.gamecenter.sdk.bean.SogouAppInfo;
import com.sogou.gamecenter.sdk.bean.UserInfo;
import com.sogou.gamecenter.sdk.listener.InitCallbackListener;
import com.sogou.gamecenter.sdk.listener.LoginCallbackListener;
import com.sogou.gamecenter.sdk.listener.PayCallbackListener;

/**
 * 更新时间：2014-3-1 16:56:38 sdk版本：1.0 文档版本：1.6
 * 
 * @author 史少杰
 */
public class SdkSouGou extends UldBase {
	private static final String TAG = "SdkSougou=======>>>>>>";
	/**
	 * 此两个变量用来处理登录状态下使用handler所传递的msg.what,用来区别不同的loginResult并处理相应的回调
	 */
	private static final int LOGINFAILED = 0;
	private static final int LOGINSUCCESS = 1;

	private static SdkSouGou sInstance = new SdkSouGou();

	private SdkSouGou() {
	}

	public static SdkSouGou getInstance() {
		return sInstance;
	}

	public void initSDK(Activity sContext) {
		// FIXME 此接口要放在 SDK 初始化接口之前，mode 为 true 是开发环境，false 是正式环境
		SogouGamePlatform.getInstance().setDevelopMode(false);
		// 游戏为横屏模式 isPortrait 设置为 false，竖屏设置 true
		SogouGamePlatform.getInstance().setOrientation(false);

		// 设置应用信息，由搜狗游戏平台统一分配
		SogouAppInfo sogouAppInfo = new SogouAppInfo();
		sogouAppInfo.gid = Integer.valueOf(getMetaData(sContext, "gid"));
		sogouAppInfo.gameName = "傲剑";
		sogouAppInfo.appKey = getMetaData(sContext, "appKey");
		// SDK初始化
		SogouGamePlatform.getInstance().init(sContext, sogouAppInfo, new InitCallbackListener() {
			@Override
			public void initSuccess() {
				// 初始化完成后，调用自动登录接口
			}

			@Override
			public void initFail(int code, String msg) {
				// 初始化失败，游戏方自行控制
			}
		});
	}

	private LoginCallBackListener mLoginCallBackListener;

	/**
	 * 登陆接口
	 * 
	 * @param sContext
	 * @param gameInfo
	 * @param loginCallBackListener
	 */
	public void loginSDK(final Activity sContext, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {
		mLoginCallBackListener = loginCallBackListener;
		final LoginResult loginResult = new LoginResult();
		final Message msg = new Message();
		SogouGamePlatform.getInstance().login(sContext, new LoginCallbackListener() {

			@Override
			public void loginSuccess(int code, UserInfo userInfo) {
				UldPlatform.channelUserId = String.valueOf(userInfo.getUserId());
				final String Session = userInfo.getSessionKey();
				final String userName = userInfo.getUser();
				new Thread() {
					public void run() {

						MessageHeader messageHeader = new MessageHeader();
						messageHeader.init();

						MessageBody messageBody = new MessageBody("uld.sdk.bll.UserSouGou", "login", new Class<?>[] { int.class, int.class,
								int.class, int.class, int.class, int.class, String.class, String.class, String.class }, new Object[] {
								gameInfo.getGameId(), gameInfo.getServerId(), UldPlatform.mobileDeviceId, UldPlatform.statisticAnalysisId,
								UldPlatform.sChannelId, UldPlatform.sChannelSubId, UldPlatform.channelUserId, UldPlatform.sDeviceName, Session});
						MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
						if (messageReturn.findErr()) {
							loginResult.setIsLogin(false);
							loginResult.setLoginErrorMsg(messageReturn.getErrMsg());
							msg.what = LOGINFAILED;
							msg.obj = loginResult;
						} else {
							// 通知游戏客户端登录成功
							@SuppressWarnings("unchecked")
							Map<String, String> retMap = (Map<String, String>) messageReturn.getRetObject();
							if (retMap != null) {
								loginResult.setIsLogin(true);
								loginResult.setChannelUserId(retMap.get("usergameid"));
								loginResult.setChannelUserName(userName);
								loginResult.setTimeSign(retMap.get("TimeSign"));
								msg.what = LOGINSUCCESS;
								msg.obj = loginResult;
							} else {
								loginResult.setIsLogin(false);
								loginResult.setChannelUserId("");
								loginResult.setLoginErrorMsg("登录失败");
								msg.what = LOGINFAILED;
								msg.obj = loginResult;
							}
							mHandler.sendMessage(msg);
						}
					};
				}.start();
			}

			@Override
			public void loginFail(int code, String loginMsg) {
				// 登录失败处理
				new Thread() {
					public void run() {
						msg.what = LOGINFAILED;
						loginResult.setLoginErrorMsg("登录失败处理");
						msg.obj = loginResult;
						mHandler.sendMessage(msg);
					};
				}.start();
			}
		});

		;
	}

	/**
	 * mHandler 处理网络交互的全局变量
	 */
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			LoginResult loginResult = null;
			switch (msg.what) {
			case LOGINSUCCESS:
				loginResult = (LoginResult) msg.obj;
				mLoginCallBackListener.onLoginFinished(loginResult);
				break;
			case LOGINFAILED:
			default:
				loginResult = (LoginResult) msg.obj;
				mLoginCallBackListener.onLoginFinished(loginResult);
				break;
			}
		};
	};

	private Dialog alertDialog;
	private int mOrderId;

	public void recharge(final Activity sContext, final RechargeCallBackListener rechargeCallBackListener) {

		final int checkedItem = 0;
		final String[] payValueAry = new String[] { "10", "20", "30", "50", "100", "200", "300", "500" };

		alertDialog = new AlertDialog.Builder(sContext).setTitle("请选择充值金额，单位：元")
				.setSingleChoiceItems(payValueAry, checkedItem, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						float payValue = (float) Integer.valueOf(payValueAry[which]);
						if (alertDialog != null) {
							alertDialog.dismiss();
						}
						// Log.d("SDK17173", "充值金额：" + payValue);
						paySdk(payValue, rechargeCallBackListener, sContext);
					}

				}).setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						float payValue = (float) Integer.valueOf(payValueAry[checkedItem]);
						if (alertDialog != null) {
							alertDialog.dismiss();
						}
						// Log.d("SDKAnzhi", "充值金额：" + payValue);
						paySdk(payValue, rechargeCallBackListener, sContext);
					}
				}).create();
		alertDialog.show();

	}

	private void paySdk(float payValue, final RechargeCallBackListener rechargeCallBackListener, Activity sContext) {
		// 游老大生成订单
		showLoading();
		final wh.order.model.Order order = new wh.order.model.Order();
		order.setGameId(UldPlatform.gameInfo.getGameId());
		order.setServerId(UldPlatform.gameInfo.getServerId());
		order.setRealPayAccount(BigDecimal.valueOf(payValue));
		order.setPayAccount(BigDecimal.valueOf(payValue));
		order.setPaySourceType(OrderEnum.PaySourceType.Android客户端.getValue());
		order.setOrderType(OrderEnum.OrderType.已提交.getValue());
		order.setAccountType(OrderEnum.AccountType.D币.getValue());
		order.setChargeType(OrderEnum.ChargeType.充值游戏.getValue());
		order.setCreateDate(new Date());
		order.setModifyDate(new Date());
		// 75为其他渠道充值
		order.setPayType((byte) 75);
		order.setStatus((byte) 1);

		// 生成订单
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.init();
		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserSouGou", "createOrder", new Class<?>[] { wh.order.model.Order.class,
				String.class, int.class, int.class, String.class }, new Object[] { order, UldPlatform.channelUserId,
				UldPlatform.sChannelId, UldPlatform.sChannelSubId, "" });
		MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
		hideLoading();

		if (!messageReturn.findErr()) {
			if (messageReturn.getRetObject() != null) {
				order.setOrderId(Utility.getInt(messageReturn.getRetObject()));
			}
		} else {
			// showToast(messageReturn.getErrMsg(), Toast.LENGTH_LONG);
		}
		if (order.getOrderId() <= 0) {
			RechargeResult rechargeResult = new RechargeResult();
			rechargeResult.setOrderId("");
			rechargeResult.setOrderType(3);
			rechargeResult.setOrderMsg("其他错误");
			rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			return;
		}

		mOrderId = order.getOrderId();

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("currency", "金锭");
		data.put("rate", 10);
		data.put("amount", order.getPayAccount());
		data.put("product_name", "金锭");
		data.put("app_data", "傲剑搜狗");

		SogouGamePlatform.getInstance().pay(sContext, data, new PayCallbackListener() {

			@Override
			public void paySuccess(String orderId, String appData) {
				RechargeResult rechargeResult=new RechargeResult();
				rechargeResult.setOrderMsg("充值成功");
				rechargeResult.setOrderId(String.valueOf(mOrderId));
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}

			@Override
			public void payFail(int code, String orderId, String appData) {
				RechargeResult rechargeResult=new RechargeResult();
				rechargeResult.setOrderMsg("充值失败");
				rechargeResult.setOrderId(String.valueOf(mOrderId));
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
		});
	}

	public void logoutSDK(Context sContext, final LogoutCallBackListener logoutCallBackListener) {
		// 登出接口
	}

	public void gotoPlatform(Activity sContext) {
		// 不用监听回调
	}

	public void gotofinish(Activity sContext) {
		SogouGamePlatform.getInstance().onTerminate();
	}
}
