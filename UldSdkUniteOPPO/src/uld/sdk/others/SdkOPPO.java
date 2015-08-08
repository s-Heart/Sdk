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
import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.nearme.gamecenter.open.api.ApiCallback;
import com.nearme.gamecenter.open.api.GameCenterSDK;
import com.nearme.gamecenter.open.api.GameCenterSettings;
import com.nearme.gamecenter.open.api.RatePayInfo;
import com.nearme.oauth.model.UserInfo;

/**
 * 更新时间：2014-5-15 14:03:21
 * 
 * @version 1.6.3
 * @author 史少杰
 */
public class SdkOPPO extends UldBase {
	private static SdkOPPO sInstance = new SdkOPPO();

	private SdkOPPO() {
	}

	public static SdkOPPO getInstance() {
		return sInstance;
	}

	public void initSDK(Activity sContext) {
		String gameKey = getMetaData(sContext, "gameKey");
		String gameSecret = getMetaData(sContext, "gameSecret");
		final GameCenterSettings gameCenterSettings = new GameCenterSettings(gameKey, gameSecret) {

			@Override
			public void onForceUpgradeCancel() {
				// sdk由于某些原因登出，此方法通知cp，cp需要在此处清理当前的登录状态并重新请求登陆，
				// 可以发广播通知页面重新登录
			}

			@Override
			public void onForceReLogin() {

			}
		};

		GameCenterSettings.isDebugModel = false;// 测试log开关，正式发布前调整为false
		GameCenterSettings.isOritationPort = false;// 控制SDK的横竖屏方向 false为横屏

		GameCenterSDK.init(gameCenterSettings, sContext);

	}

	// 设置为静态变量是因为此变量在不同线程中连续调用，为保证调用顺序正确，
	// 需要在恰当的位置将传过来的此变量赋值为静态变量，再到最后调用此变量。
	private static LoginCallBackListener mLoginCallBackListener;

	/**
	 * 登陆接口
	 * 
	 * @param sContext
	 * @param gameInfo
	 * @param loginCallBackListener
	 */
	public void loginSDK(final Activity sContext, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {

		final LoginResult loginResult = new LoginResult();

		GameCenterSDK.setmCurrentContext(sContext);
		GameCenterSDK.getInstance().doLogin(new ApiCallback() {
			@Override
			public void onSuccess(String content, int code) {
				mLoginCallBackListener = loginCallBackListener;
				getOppoUserInfo(sContext, gameInfo, loginResult);
			}

			@Override
			public void onFailure(String content, int code) {
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg(content);
				loginCallBackListener.onLoginFinished(loginResult);
			}

		}, sContext);
	}

	// 获取用户信息
	private void getOppoUserInfo(Activity sContext, final GameInfo gameInfo, final LoginResult loginResult) {
		GameCenterSDK.getInstance().doGetUserInfo(new ApiCallback() {
			@Override
			public void onSuccess(String content, int code) {
				Log.d("test-oppo", content);
				UserInfo userInfo = new UserInfo(content);
				// 直接将返回的ssoid放入channelUserid中
				UldPlatform.channelUserId = userInfo.id;
				if (!Utility.isEmpty(UldPlatform.channelUserId)) {
					// 取到了用户信息
					// 登录成功
					// 再取一次

					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();

					MessageBody messageBody = new MessageBody("uld.sdk.bll.UserOPPO", "login", new Class<?>[] { int.class, int.class,
							int.class, int.class, int.class, int.class, String.class,String.class}, new Object[] { gameInfo.getGameId(),
							gameInfo.getServerId(), UldPlatform.mobileDeviceId, UldPlatform.statisticAnalysisId, UldPlatform.sChannelId,
							UldPlatform.sChannelSubId, UldPlatform.channelUserId,UldPlatform.sDeviceName});
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
							loginResult.setChannelUserId(retMap.get("Ssoid"));
							loginResult.setChannelUserName(retMap.get("UserName"));
							loginResult.setTimeSign(retMap.get("TimeSign"));
							UldPlatform.channelUserId = retMap.get("Ssoid");
						} else {
							loginResult.setIsLogin(false);
							loginResult.setChannelUserId("");
							loginResult.setLoginErrorMsg("登录失败");
						}

					}
					mLoginCallBackListener.onLoginFinished(loginResult);
				} else {
					// 登录失败处理
					loginResult.setLoginErrorMsg("未获取Ssoid");
					mLoginCallBackListener.onLoginFinished(loginResult);
				}

			}

			@Override
			public void onFailure(String content, int code) {

			}
		}, sContext);
	}

	private Dialog alertDialog;

	public void recharge(final Activity sContext, final RechargeCallBackListener rechargeCallBackListener) {
		// 游老大生成订单
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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserOPPO", "createOrder", new Class<?>[] { wh.order.model.Order.class,
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

		RatePayInfo payInfo = new RatePayInfo(String.valueOf(order.getOrderId()), "自定义字段");
		payInfo.setProductDesc("商品描述");
		payInfo.setProductName("*10金锭");
		payInfo.setCallbackUrl("http://payunite.ulaoda.com/OppoCallBack.ashx");
		payInfo.setRate(1);
		payInfo.setDefaultShowCount(10);
		GameCenterSDK.setmCurrentContext(sContext);
		GameCenterSDK.getInstance().doRateKebiPayment(new ApiCallback() {

			@Override
			public void onSuccess(String content, int code) {
				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderType(0);
				rechargeResult.setOrderId(String.valueOf(order.getOrderId()));
				rechargeResult.setOrderMsg("充值成功");
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}

			@Override
			public void onFailure(String content, int code) {
				// 不回调
				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderType(2);
				rechargeResult.setOrderId(String.valueOf(order.getOrderId()));
				rechargeResult.setOrderMsg(content);
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
		}, payInfo, sContext);

	}

	public void logoutSDK(Context sContext, final LogoutCallBackListener logoutCallBackListener) {
		// 无登出接口
	}

	public void gotoPlatform(Activity sContext) {
		// 不用监听回调
		GameCenterSDK.setmCurrentContext(sContext);
		GameCenterSDK.getInstance().doShowGameCenter(sContext);
	}

	public void gotofinish(Activity sContext) {
		// 无退出接口
	}
}
