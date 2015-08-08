package uld.sdk.others;

import java.math.BigDecimal;
import java.util.Date;
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

import com.lenovo.lsf.open.LenovoGameSdk;
import com.lenovo.lsf.open.LenovoGameSdk.OnAuthenListener;
import com.lenovo.mpay.ifmgr.IPayResultCallback;
import com.lenovo.mpay.ifmgr.SDKApi;
import com.lenovo.mpay.tools.PayRequest;

/**
 * 更新时间：2014-2-14 16:24:58
 * 
 * @version 2.0
 * @author 史少杰
 */
public class SdkLenovo extends UldBase {
	private static final String TAG = "SdkLenovo=======>>>>>>";
	/**
	 * 此两个变量用来处理登录状态下使用handler所传递的msg.what,用来区别不同的loginResult并处理相应的回调
	 */
	private static final int LOGINFAILED = 0;
	private static final int LOGINSUCCESS = 1;

	private static SdkLenovo sInstance = new SdkLenovo();

	private SdkLenovo() {
	}

	public static SdkLenovo getInstance() {
		return sInstance;
	}

	public void initSDK(Activity sContext) {
		String appid = getMetaData(sContext, "appid");

		String appkey = getMetaData(sContext, "appkey");
		Log.d(TAG, "appid:" + appid);
		Log.d(TAG, "appkey:" + appkey);
		com.lenovo.mpay.ifmgr.SDKApi.init(sContext, 0, appid); // 0为横屏，1为竖屏
		// 支付预读接口
		SDKApi.preGettingData(sContext, appid);
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
		Bundle loginOption = new Bundle();
		loginOption.putBoolean(LenovoGameSdk.SHOW_WELCOME, false);
		LenovoGameSdk.quickLogin(sContext, new OnAuthenListener() {

			@Override
			public void onFinished(boolean success, final String data) {
				// success 返回值，认证状态：true 成功，false 失败；
				// data 成功为Service Ticket数据；失败为错误码，格式形如：USS-0100。
				// 如果是cancel代表用户在登录界面取消；
				// 如果是USS开头，可通过调用getLastErrrorString()函数，获取错误信息。

				final LoginResult loginResult = new LoginResult();
				final Message msg = new Message();

				if (success) {
					new Thread() {
						public void run() {
							// 登录成功
							// 调用API getStData获取token
							// flag false, 优先从系统缓存取有效ST数据
							String token = data;
							MessageHeader messageHeader = new MessageHeader();
							messageHeader.init();

							MessageBody messageBody = new MessageBody("uld.sdk.bll.UserLenovo", "login", new Class<?>[] { int.class,
									int.class, int.class, int.class, int.class, int.class, String.class, String.class, String.class },
									new Object[] { gameInfo.getGameId(), gameInfo.getServerId(), UldPlatform.mobileDeviceId,
											UldPlatform.statisticAnalysisId, UldPlatform.sChannelId, UldPlatform.sChannelSubId,
											UldPlatform.channelUserId, UldPlatform.sDeviceName, token });
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
									loginResult.setChannelUserName(retMap.get("UserName"));
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
				} else {
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
			}
		}, loginOption);
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
		// 在支付之前需调用预支付接口
		SDKApi.preGettingData(sContext, getMetaData(sContext, "appid"));
		paySdk(0, rechargeCallBackListener, sContext);
	}

	private void paySdk(int payValue, final RechargeCallBackListener rechargeCallBackListener, final Activity sContext) {
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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserLenovo", "createOrder", new Class<?>[] { wh.order.model.Order.class,
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
		PayRequest payRequest = new PayRequest();
		// 请填写商品自己的参数
		String notifyurl = "http://payunite.ulaoda.com/LenovoNotifyBack.ashx";
		payRequest.addParam("notifyurl", notifyurl);
		payRequest.addParam("appid", getMetaData(sContext, "appid"));
		payRequest.addParam("waresid", 1);// 后台商品名称的编码
		payRequest.addParam("exorderno", String.valueOf(mOrderId));
		payRequest.addParam("price", payValue);
		payRequest.addParam("cpprivateinfo", "金锭");

		// 生成签名，创建请求url
		String appkey = getMetaData(sContext, "appkey");
		Log.d(TAG, "appkey------->" + appkey);
		String paramUrl = payRequest.genSignedUrlParamString(appkey);// 这个接口出现问题
		SDKApi.startPay(sContext, paramUrl, new IPayResultCallback() {
			@Override
			public void onPayResult(int resultCode, String signValue, String resultInfo) {// resultInfo=应用编号&商品编号&外部订单号
				RechargeResult rechargeResult = new RechargeResult();

				if (SDKApi.PAY_SUCCESS == resultCode) {
					if (null == signValue) {
						// 没有签名值，默认采用finish()，请根据需要修改
						rechargeResult.setOrderMsg("没有签名值");
					}
					boolean flag = PayRequest.isLegalSign(signValue, getMetaData(sContext, "appkey"));
					if (flag) {
						// 合法签名值，支付成功，请添加支付成功后的业务逻辑
						rechargeResult.setOrderType(0);
						rechargeResult.setOrderId(String.valueOf(mOrderId));// FIXME
						rechargeResult.setOrderMsg("充值成功");
					} else {
						// 非法签名值，默认采用finish()，请根据需要修改
						rechargeResult.setOrderId(String.valueOf(mOrderId));
						rechargeResult.setOrderMsg("非法签名值");
					}
				} else if (SDKApi.PAY_CANCEL == resultCode) {
					// 取消支付处理，默认采用finish()，请根据需要修改
					rechargeResult.setOrderId(String.valueOf(mOrderId));
					rechargeResult.setOrderMsg("取消支付");
				} else {
					// 计费失败处理，默认采用finish()，请根据需要修改
					rechargeResult.setOrderId(String.valueOf(mOrderId));
					rechargeResult.setOrderMsg("计费失败");
				}
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
		});
	}

	public void logoutSDK(Context sContext, final LogoutCallBackListener logoutCallBackListener) {
		// 登出接口
		LenovoGameSdk.exit();
	}

	public void gotoPlatform(Activity sContext) {
		// 不用监听回调
	}

	public void gotofinish(Activity sContext) {
		// 无退出接口
	}
}
