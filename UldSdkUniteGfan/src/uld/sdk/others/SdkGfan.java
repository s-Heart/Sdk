package uld.sdk.others;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.mappn.sdk.pay.GfanChargeCallback;
import com.mappn.sdk.pay.GfanPay;
import com.mappn.sdk.pay.GfanPayCallback;
import com.mappn.sdk.pay.model.Order;
import com.mappn.sdk.uc.GfanUCCallback;
import com.mappn.sdk.uc.GfanUCenter;
import com.mappn.sdk.uc.User;

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
import android.util.Log;

/**
 * 
 * 机锋sdk 版本:v3.5 最后更新：2014年5月16日17:53:01
 * 
 * @author caoyuqing
 * 
 */
public class SdkGfan extends UldBase {
	private static SdkGfan sInstance = new SdkGfan();
	private static RechargeCallBackListener mRechargeCallBackListener = null;
	private static int mOrderId = 0;

	private SdkGfan() {

	}

	public static SdkGfan getInstance() {
		return sInstance;
	}

	/**
	 * 初始化SDK
	 * 
	 * @param sContext
	 */
	public void initSDK(Activity sContext) {
		GfanPay.getInstance(sContext).init();
	}

	public void loginSDK(Activity sContext, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {

		final LoginResult loginResult = new LoginResult();

		// 机锋调用接口处理
		GfanUCenter.login(sContext, new GfanUCCallback() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5146827508207981982L;

			@Override
			public void onSuccess(User user, int loginType) {
				String token = user.getToken();
				UldPlatform.channelUserId = String.valueOf(user.getUid());
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("请稍后再试");
					// 登录成功
					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();

					MessageBody messageBody = new MessageBody("uld.sdk.bll.UserGfan", "login", new Class<?>[] { int.class, int.class,
							int.class, int.class, int.class, int.class, String.class, String.class,String.class}, new Object[] { gameInfo.getGameId(),
							gameInfo.getServerId(), UldPlatform.mobileDeviceId, UldPlatform.statisticAnalysisId, UldPlatform.sChannelId,
							UldPlatform.sChannelSubId, UldPlatform.channelUserId, UldPlatform.sDeviceName, token });
					MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
					if (messageReturn.findErr()) {
						loginResult.setIsLogin(false);
						loginResult.setLoginErrorMsg(messageReturn.getErrMsg());
					} else {
						// 通知游戏客户端登录成功
						@SuppressWarnings("unchecked")
						Map<String, String> retMap = (Map<String, String>) messageReturn.getRetObject();
						if (retMap != null) {
							loginResult.setIsLogin(true);
							loginResult.setChannelUserId(retMap.get("uid"));
							loginResult.setChannelUserName(retMap.get("UserName"));
							loginResult.setTimeSign(retMap.get("TimeSign"));

							Log.d("test", "uid:" + retMap.get("uid"));
							Log.d("test", "TimeSign:" + retMap.get("TimeSign"));
						} else {
							loginResult.setIsLogin(false);
							loginResult.setChannelUserId("");
							loginResult.setLoginErrorMsg("服务器异常,请稍后再试");
						}
					}
					
					loginCallBackListener.onLoginFinished(loginResult);
			}

			@Override
			public void onError(int type) {
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("机锋登陆/注册失败");
				loginCallBackListener.onLoginFinished(loginResult);
			}
		});
	}

	private Dialog alertDialog;

	/**
	 * 
	 * @param sContext
	 * @param rechargeCallBackListener
	 */
	public void recharge(final Activity sContext, final RechargeCallBackListener rechargeCallBackListener) {
		mRechargeCallBackListener = rechargeCallBackListener;
		showLoading();

		final wh.order.model.Order order = new wh.order.model.Order();
		order.setGameId(UldPlatform.gameInfo.getGameId());
		order.setServerId(UldPlatform.gameInfo.getServerId());
		order.setRealPayAccount(BigDecimal.valueOf(0));
		order.setPayAccount(BigDecimal.valueOf(0));
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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserGfan", "createOrder", new Class<?>[] { wh.order.model.Order.class,
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
		
		//机锋支付所需orderBean
		//机锋劵：人民币 = 10:1
		Order gfanOrder=new Order("傲剑","金锭充值",10,String.valueOf(mOrderId));
		GfanPay.getInstance(sContext).pay(gfanOrder, new GfanPayCallback() {
			
			@Override
			public void onSuccess(User user, Order order) {
				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderId(String.valueOf(mOrderId));
				rechargeResult.setOrderType(0);
				rechargeResult.setOrderMsg("消费"+order.getNumber()+"机锋劵");
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
			
			@Override
			public void onError(User arg0) {
				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderId(String.valueOf(mOrderId));
				rechargeResult.setOrderType(2);
				rechargeResult.setOrderMsg("支付失败");
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
		});
	}


	public void gotofinish(Activity sContext) {
		// 无退出接口
	}

	public void logoutSDK(Context sContext, final LogoutCallBackListener logoutCallBackListener) {
		GfanUCenter.logout((Activity) sContext);
		LogoutResult logoutResult=new LogoutResult();
		logoutResult.setLogoutErrorMsg("注销成功");
		logoutResult.setIsLogout(true);
		logoutCallBackListener.onLogoutFinished(logoutResult);
	};
}
