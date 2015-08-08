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
import uld.sdk.unite.LogoutResult;
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
import android.util.Log;

import com.downjoy.CallbackListener;
import com.downjoy.Downjoy;
import com.downjoy.DownjoyError;

/**
 * Sdk Version:3.0.3
 * Update Time:2014-5-16 10:44:09
 * @author caoyuqing
 *
 */
public class SdkDownjoy extends UldBase {
	private static SdkDownjoy sInstance = new SdkDownjoy();
	private int mOrderId = 0;

	private SdkDownjoy() {

	}

	public static SdkDownjoy getInstance() {
		return sInstance;
	}

	/**
	 * 初始化SDK，
	 * 
	 * @param sContext
	 */
	public void initSDK(Activity sContext) {
		String merchantId = getMetaData(sContext, "merchantId");
		String appId = getMetaData(sContext, "appId");
		//传入我们服务器的SeqNum
		String serverSeqNum = getMetaData(sContext, "serverSeqNum");
		String appKey = getMetaData(sContext, "appKey");
		Downjoy.getInstance(sContext, merchantId, appId, serverSeqNum, appKey);
		// 设置登录成功后属否显示当乐游戏中心的悬浮按钮
        // 注意：
        // 此处应在调用登录接口之前设置，默认值是true，即登录成功后自动显示当乐游戏中心的悬浮按钮。
        // 如果此处设置为false，登录成功后，不显示当乐游戏中心的悬浮按钮。
        Downjoy.getInstance().setInitLocation(Downjoy.LOCATION_CENTER_HORIZONTAL_BOTTOM);

	}

	/**
	 * 登陆接口
	 * 
	 * @param sContext
	 * @param gameInfo
	 * @param loginCallBackListener
	 */
	public void loginSDK(Activity sContext, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {

		Downjoy.getInstance().openLoginDialog(sContext, new CallbackListener() {

			@Override
			public void onLoginSuccess(Bundle bundle) {
				LoginResult loginResult = new LoginResult();
				String memberId = bundle.getString(Downjoy.DJ_PREFIX_STR + "mid");
				String username = bundle.getString(Downjoy.DJ_PREFIX_STR + "username");
				String nickname = bundle.getString(Downjoy.DJ_PREFIX_STR + "nickname");
				String token = bundle.getString(Downjoy.DJ_PREFIX_STR + "token");

				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("请稍后再试");
				// 登录成功
				MessageHeader messageHeader = new MessageHeader();
				messageHeader.init();

				MessageBody messageBody = new MessageBody("uld.sdk.bll.UserDownjoy", "login", new Class<?>[] { int.class, int.class,
						int.class, int.class, int.class, int.class, String.class, String.class,String.class }, new Object[] { gameInfo.getGameId(),
						gameInfo.getServerId(), UldPlatform.mobileDeviceId, UldPlatform.statisticAnalysisId, UldPlatform.sChannelId,
						UldPlatform.sChannelSubId, memberId, token ,UldPlatform.sDeviceName});
				MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
				if (messageReturn.findErr()) {
					loginResult.setIsLogin(false);
					loginResult.setLoginErrorMsg(messageReturn.getErrMsg());
					loginCallBackListener.onLoginFinished(loginResult);
				} else {
					// 通知游戏客户端登录成功
					@SuppressWarnings("unchecked")
					Map<String, String> retMap = (Map<String, String>) messageReturn.getRetObject();
					if (retMap != null) {
						loginResult.setIsLogin(true);
						loginResult.setChannelUserId(retMap.get("memberId"));
						loginResult.setChannelUserName(retMap.get("UserName"));
						loginResult.setTimeSign(retMap.get("TimeSign"));
						UldPlatform.channelUserId = retMap.get("memberId");

						Log.d("test", "channelUserId:" + UldPlatform.channelUserId);
						Log.d("test", "memberId:" + retMap.get("memberId"));
						Log.d("test", "TimeSign:" + retMap.get("TimeSign"));
						loginCallBackListener.onLoginFinished(loginResult);
					} else {
						loginResult.setIsLogin(false);
						loginResult.setChannelUserId("");
						loginResult.setLoginErrorMsg("服务器异常,请稍后再试");
						loginCallBackListener.onLoginFinished(loginResult);
					}
				}
				
			}

			@Override
			public void onLoginError(DownjoyError error) {
				LoginResult loginResult = new LoginResult();
				int errorCode = error.getMErrorCode();
				String errorMsg = error.getMErrorMessage();
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("当乐登陆失败");
				loginCallBackListener.onLoginFinished(loginResult);
			}

			@Override
			public void onError(Error error) {
				LoginResult loginResult = new LoginResult();
				String errorMessage = error.getMessage();
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg(errorMessage);
				loginCallBackListener.onLoginFinished(loginResult);
			}
		});
		
	}

	private Dialog alertDialog;

	public void recharge(final Activity sContext, final RechargeCallBackListener rechargeCallBackListener) {
		// 先提供选择支付金额选择列表，将值传给当乐的支付接口,注意，金额和传给支付接口的金额
		// 的计价单位分别为元、分
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

	protected void paySdk(float payValue, final RechargeCallBackListener rechargeCallBackListener, Activity sContext) {
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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserDownjoy", "createOrder", new Class<?>[] { wh.order.model.Order.class,
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
		// 打开支付界面,获得订单号
		// 参数名 说明
		// context 应用上下文对象。
		// money 支付总额，单位：元。
		// productName 商品名称。
		// extInfo cp 自定义信息，计费结果通知时原样返回。多为 cp 订单号。
		// listener 回调监听类，具体实现方式可见代码示例。
		Downjoy.getInstance().openPaymentDialog(sContext, payValue, "金锭", String.valueOf(mOrderId), new CallbackListener() {

			@Override
			public void onPaymentSuccess(String orderNo) {
				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderType(0);
				rechargeResult.setOrderId(String.valueOf(mOrderId));
				rechargeResult.setOrderMsg("充值成功");
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}

			@Override
			public void onPaymentError(DownjoyError error, String orderNo) {
				int errorCode = error.getMErrorCode();
				String errorMsg = error.getMErrorMessage();
				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderType(2);
				rechargeResult.setOrderMsg(errorMsg);
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}

			@Override
			public void onError(Error error) {

				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderMsg(rechargeResult.getOrderMsg());
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
		});

	}

	/**
	 * 登出游戏的接口
	 * @param sContext
	 * @param logoutCallBackListener
	 */
	public void logoutSDK(Context sContext, final LogoutCallBackListener logoutCallBackListener) {
		Downjoy.getInstance().logout(sContext, new CallbackListener() {

			@Override
			public void onLogoutSuccess() {
				LogoutResult logoutResult = new LogoutResult();
				logoutResult.setIsLogout(true);
				logoutCallBackListener.onLogoutFinished(logoutResult);
			}

			@Override
			public void onLogoutError(DownjoyError error) {
				int errorCode = error.getMErrorCode();
				String errorMsg = error.getMErrorMessage();
				LogoutResult logoutResult = new LogoutResult();
				logoutResult.setIsLogout(false);
				logoutResult.setLogoutErrorMsg(errorMsg);
				logoutCallBackListener.onLogoutFinished(logoutResult);
			}

			@Override
			public void onError(Error error) {
				LogoutResult logoutResult = new LogoutResult();
				logoutResult.setLogoutErrorMsg(logoutResult.getLogoutErrorMsg());
				logoutCallBackListener.onLogoutFinished(logoutResult);
			}
		});
	}

	/**
	 * 用户中心
	 * @param sContext
	 */
	public void gotoPlatform(Activity sContext) {
		// 不用监听回调
		Downjoy.getInstance().openMemberCenterDialog(sContext, new CallbackListener() {
		});
	}

	public void gotofinish(Activity sContext) {
		// 无退出接口
	}
}
