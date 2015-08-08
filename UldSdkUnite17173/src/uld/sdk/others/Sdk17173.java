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
import android.util.Log;

import com.cyouwanwan.sdk.GameLib;
import com.cyouwanwan.sdk.callback.LoginCallback;
import com.cyouwanwan.sdk.callback.LogoutCallback;
import com.cyouwanwan.sdk.callback.PayCallback;

/**
 * 文档版本：1.0.8 SDK版本：1.0 最后更新：2013年10月24日16:06:05
 * 
 * @author 史少杰
 * 
 */
public class Sdk17173 extends UldBase {
	private static Sdk17173 sInstance = new Sdk17173();
	private static RechargeCallBackListener mRechargeCallBackListener = null;
	private static int mOrderId = 0;

	private Sdk17173() {

	}

	public static Sdk17173 getInstance() {
		return sInstance;
	}

	/**
	 * 初始化SDK，设置从37玩玩平台获取的游戏ID和私密串，
	 * 需要在app启动时进行设定，并且只能调用一次，多次调用会抛出IllegalStateException异常。
	 * GameLib.initialize(gameId, gameSecret,vendorName, distributionChannel);
	 * 
	 * @param sContext
	 */
	public void initSDK(Activity sContext) {
		// vendorName 自己命名，其他37玩玩平台获取
		String gameId = getMetaData(sContext, "gameId");
		String gameSecret = getMetaData(sContext, "gameSecret");
		String vendorName = getMetaData(sContext, "vendorName");
		String distributionChannel = getMetaData(sContext, "distributionChannel");
		GameLib.initialize(gameId, gameSecret, vendorName, distributionChannel);
	}

	public void loginSDK(Activity sContext, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {

		final LoginResult loginResult = new LoginResult();

		// 37玩调用接口处理

		GameLib.getInstance(sContext).login(new LoginCallback() {

			@Override
			public void onLoginSuccess(String token) {
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("请稍后再试");
				// 登录成功
				MessageHeader messageHeader = new MessageHeader();
				messageHeader.init();

				MessageBody messageBody = new MessageBody("uld.sdk.bll.User17173", "login", new Class<?>[] { int.class, int.class,
						int.class, int.class, int.class, int.class, String.class ,String.class}, new Object[] { gameInfo.getGameId(),
						gameInfo.getServerId(), UldPlatform.mobileDeviceId, UldPlatform.statisticAnalysisId, UldPlatform.sChannelId,
						UldPlatform.sChannelSubId, token ,UldPlatform.sDeviceName});
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
						loginResult.setChannelUserId(retMap.get("usergameid"));
						loginResult.setChannelUserName(retMap.get("UserName"));
						loginResult.setTimeSign(retMap.get("TimeSign"));
						UldPlatform.channelUserId = retMap.get("usergameid");
						Log.d("test", "usergameid:" + retMap.get("usergameid"));
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
			public void onError() {
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("37玩玩sdk登陆失败");
				loginCallBackListener.onLoginFinished(loginResult);
			}

			@Override
			public void onCancel() {
				// loginResult.setIsLogin(false);
				// loginResult.setLoginErrorMsg("用户取消登录");
				// loginCallBackListener.onLoginFinished(loginResult);
			}
		});
	}

	/**
	 * Gamelib.pay(String productId, String productName, int productPrice, int
	 * quantity, String orderId, PayCallback payCallback)
	 * 
	 * productId 购买商品编号 游戏内商品编号 productName 购买商品名称 游戏内商品名称， 例如“1000元宝”
	 * productPrice 商品的人民币价格（以分为单位） 例如需花费1元人民币，则传100 quantity 购买商品的数量
	 * 商品数量，如果是充值，数量传1 orderId 订单编号
	 * 此参数平台服务端会原样回传给厂商服务端接口，因此也可以拼接传递厂商需要的其他参数（例如服务器编号等） payCallback
	 * 用于处理支付的结果。打开支付界面:1:成功;2:失败;3:关闭支付界面;
	 * 
	 * @param sContext
	 * @param rechargeCallBackListener
	 */

	private Dialog alertDialog;

	public void recharge(final Activity sContext, final RechargeCallBackListener rechargeCallBackListener) {
		// 先提供选择支付金额选择列表，将值传给37玩玩的支付接口,注意，金额和传给支付接口的金额
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

	protected void paySdk(float payValue, RechargeCallBackListener rechargeCallBackListener, Activity sContext) {
		mRechargeCallBackListener = rechargeCallBackListener;
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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.User17173", "createOrder", new Class<?>[] { wh.order.model.Order.class,
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
		PayCallback payCallback = new PayCallback() {
			@Override
			public void onPayError() {
				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderMsg("用户放弃了支付");
				mRechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}

			@Override
			public void onPayCancel() {
				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderMsg("打开支付操作失败");
				mRechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
			
		};
		// 参数名 含义 使用说明
		// productId 购买商品编号 游戏内商品编号
		// productName 购买商品名称 游戏内商品名称， 例如“1000元宝”
		// productPrice 商品的人民币价格（以分为单位） 例如需花费1元人民币，则传100
		// quantity 购买商品的数量 商品数量，如果是充值，数量传1
		// orderId 订单编号 此参数平台服务端会原样回传给厂商服务端接口，因此也可以拼接传递厂商需要的其他参数（例如服务器编号等）
		// payCallback 用于处理支付的结果。 打开支付界面:1:成功;2:失败;3:关闭支付界面;

		/*
		 * 规定 <orderId>::orderId&【5-11位】(MD5(orderId+key))
		 */
		String key = "ulaoda";
		String orderid = String.valueOf(mOrderId);
		String md5String = uld.sdk.tools.Utility.encodeMD5(orderid + key);
		String md5subString = md5String.substring(6, 12);
		String formatString = md5subString + "&" + orderid;
		GameLib.getInstance(sContext).pay("0","金锭",(int) (payValue * 100), 1, formatString,
				payCallback);
	}

	public void gotofinish(Activity sContext) {
		GameLib.exit();
	}

	public void logoutSDK(Context sContext, final LogoutCallBackListener logoutCallBackListener) {
		GameLib.getInstance(sContext).logout(new LogoutCallback() {

			@Override
			public void onLogoutSuccess() {
				uld.sdk.unite.LogoutResult logoutResult = new uld.sdk.unite.LogoutResult();
				logoutResult.setIsLogout(true);
				logoutResult.setChannelUserId(UldPlatform.channelUserId);
				logoutCallBackListener.onLogoutFinished(logoutResult);
			}
		});
	};
}
