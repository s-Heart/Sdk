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
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.wandoujia.sdk.plugin.paydef.LoginCallBack;
import com.wandoujia.sdk.plugin.paydef.PayCallBack;
import com.wandoujia.sdk.plugin.paydef.User;
import com.wandoujia.sdk.plugin.paydef.WandouAccount;
import com.wandoujia.sdk.plugin.paydef.WandouOrder;
import com.wandoujia.sdk.plugin.paydef.WandouPay;
import com.wandoujia.sdk.plugin.paysdkimpl.PayConfig;
import com.wandoujia.sdk.plugin.paysdkimpl.WandouAccountImpl;
import com.wandoujia.sdk.plugin.paysdkimpl.WandouPayImpl;
import com.wandoujia.wandoujiapaymentplugin.utils.MSG;

/**
 * Sdk version:2.5.0
 * Update time:2014-5-16 10:42:01
 * @author caoyuqing
 *
 */
public class SdkWanDouJia extends UldBase {

	// 开发者appkey_id
	final String appkey_id = "100000611";
	// 开发者 安全秘钥
	final String secretkey = "d1e6318d798515473d7b161754a04483";
	// 开发者的appid
	final String appid = "1131";
	private static SdkWanDouJia instance = null;
	private static LoginCallBackListener mLoginCallBackListener;
	private Dialog alertDialog;
	private static RechargeCallBackListener mRechargeCallBackListener = null;

	private WandouAccount account = new WandouAccountImpl();

	public static SdkWanDouJia getInstance() {
		if (instance == null) {
			instance = new SdkWanDouJia();
		}
		return instance;
	}

	// 初始化
	public void initSDK(final Activity context) {
		// 渠道初始化
		/**
		 * @param context
		 *            上下文
		 * @param 100000611 appkid
		 * @param secretkey
		 *            appkey
		 */
		PayConfig.init(context, "100000611", secretkey);
	}

	// 登录接口
	/**
	 * @param context
	 *            上下文
	 * @param gameInfo
	 *            游戏信息
	 * @param loginCallBackListener
	 *            登录回调
	 */
	public void loginSDK(Activity context, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {
		mLoginCallBackListener = loginCallBackListener;

		/**
		 * @param Context
		 *            上下文
		 * @param true confirm 为 true，则每次都出现登录界面登录
		 * @param LoginCallBack
		 *            登录回调
		 */
		account.doLogin((Activity) context, true, new LoginCallBack() {

			// 登录失败
			@Override
			public void onError(int code, String info) {
				mHandler.sendMessage(mHandler.obtainMessage(HandleMessage_CallLoginFail, code));
				Log.d("SdkWdj", MSG.trans(info));
			}

			// 登录成功
			@Override
			public void onSuccess(User user, int type) {
				mHandler.sendMessage(mHandler.obtainMessage(HandleMessage_CallLogin, user));
			}
		});

	}

	/**
	 * @param context
	 *            上下文
	 * @param rechargeCallBackListener
	 *            充值回调
	 */
	public void recharge(final Activity context, final RechargeCallBackListener rechargeCallBackListener) {
		mRechargeCallBackListener = rechargeCallBackListener;

		// 选择充值金额
		final int checkedItem = 0;
		final String[] payValueAry = new String[] { "10", "20", "30", "50", "100", "200", "300", "500", "1000", "2000" };

		alertDialog = new AlertDialog.Builder(context).setTitle("请选择充值金额，单位：元")
				.setSingleChoiceItems(payValueAry, checkedItem, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						float payValue = (float) Integer.valueOf(payValueAry[which]);
						if (alertDialog != null) {
							alertDialog.dismiss();
						}
						// Log.d("SDKAnzhi", "充值金额：" + payValue);
						// paySdk(payValue, rechargeCallBackListener);
						mHandler.sendMessage(mHandler.obtainMessage(HandleMessage_CallPay, payValue));
					}
				}).setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						float payValue = (float) Integer.valueOf(payValueAry[checkedItem]);
						if (alertDialog != null) {
							alertDialog.dismiss();
						}
						// Log.d("SDKAnzhi", "充值金额：" + payValue);
						// paySdk(payValue, rechargeCallBackListener);

						mHandler.sendMessage(mHandler.obtainMessage(HandleMessage_CallPay, payValue));
					}
				}).create();
		alertDialog.show();
	}

	private final static int HandleMessage_CallLogin = 1;
	private final static int HandleMessage_CallPay = 2;
	private final static int HandleMessage_CallLoginFail = 3;
	protected static final int HandleMessage_CallLogoutSuccess = 4;
	private static final int HandleMessage_CallLogoutFail=5;
	private Handler mHandler = new Handler() {

		

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case HandleMessage_CallLogin:
				LoginSuccess((User) msg.obj);
				break;
			case HandleMessage_CallPay:
				paySdk((Float) msg.obj);
				break;
			case HandleMessage_CallLoginFail:
				LoginResult loginResult = new LoginResult();
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("登录失败");
				mLoginCallBackListener.onLoginFinished(loginResult);
				break;
			case HandleMessage_CallLogoutSuccess:
				 Log.d("logout", "success:" + ((User)msg.obj).getUsername());
	               LogoutResult logoutResult=new LogoutResult();
	               logoutResult.setIsLogout(true);
	               mLogoutCallBackListener.onLogoutFinished(logoutResult);
	               break;
			case HandleMessage_CallLogoutFail:
				Log.d("logout", "fail:" + msg.obj.toString());
				 logoutResult=new LogoutResult();
                 logoutResult.setIsLogout(false);
                 logoutResult.setLogoutErrorMsg(msg.obj.toString());
                 mLogoutCallBackListener.onLogoutFinished(logoutResult);        
				break;
			default:
				break;
			}
		}

	};
	private LogoutCallBackListener mLogoutCallBackListener;

	// 渠道方充值成功后调用uld服务端
	/**
	 * @param payValue
	 *            充值金额
	 */
	public void paySdk(float payValue) {
		final RechargeCallBackListener rechargeCallBackListener = mRechargeCallBackListener;

		showLoading();

		final wh.order.model.Order order = new wh.order.model.Order();
		order.setGameId(UldPlatform.gameInfo.getGameId());
		order.setServerId(UldPlatform.gameInfo.getServerId());
		int userId = uld.sdk.tools.Utility.getInt(UldPlatform.channelUserId);
		Log.d("WanDouJiaSdk", "userId " + userId);

		if (userId == 0) {
			RechargeResult rechargeResult = new RechargeResult();
			rechargeResult.setOrderId("");
			rechargeResult.setOrderType(3);
			rechargeResult.setOrderMsg("请重新登录");
			rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			return;
		}
		order.setUserId(userId);
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

		// 生成ULD订单
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.init();
		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserWanDouJia", "createOrder", new Class<?>[] { wh.order.model.Order.class,
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

		// 生成豌豆荚订单 - 如果成功则直接付款
		// 三个参数分别是 游戏名(String)，商品(String)，价格(Long)单位是分
		BigDecimal payValueBigDecimal = new BigDecimal((int) payValue * 100);
		final WandouOrder wandouOrder = new WandouOrder("傲剑", String.valueOf(payValue * 10) + "金锭", Long.valueOf(payValueBigDecimal
				.longValue()));
		wandouOrder.out_trade_no = String.valueOf(order.getOrderId());
		final WandouPay wandoupay = new WandouPayImpl();
		// 充值回调
		wandoupay.pay(UldPlatform.sContext, wandouOrder, new PayCallBack() {
			// 充值成功
			@Override
			public void onSuccess(User user, WandouOrder order) {

				Log.d("Tag_WanDouJia", "pay onSuccess");

				Log.d("Tag_WanDouJia", "uldorderId=" + order.out_trade_no);
				Log.d("Tag_WanDouJia", "wdjorderId=" + order.order_no);

				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderId(String.valueOf(order.out_trade_no));
				rechargeResult.setOrderType(0);
				rechargeResult.setOrderMsg("充值成功");
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}

			// 充值失败
			@Override
			public void onError(User user, WandouOrder order) {
				Log.d("Tag_WanDouJia", "pay onError");

				Log.d("Tag_WanDouJia", "uldorderId=" + order.out_trade_no);
				Log.d("Tag_WanDouJia", "wdjorderId=" + order.order_no);

				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderId(String.valueOf(order.out_trade_no));
				rechargeResult.setOrderType(2);
				rechargeResult.setOrderMsg("充值失败");
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
		});
	}

	// 渠道登录成功后调用uld服务端
	/**
	 * @param user
	 *            渠道传过来的用户信息
	 */
	protected void LoginSuccess(User user) {

		String uid = String.valueOf(user.getUid());
		String sid = user.getToken();

		Log.d("Tag_WanDouJia", "success:+" + user);
		LoginResult loginResult = new LoginResult();
		loginResult.setIsLogin(false);
		loginResult.setLoginErrorMsg("登录失败");
		// 登录成功
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.init();

		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserWanDouJia", "login", new Class<?>[] { int.class, int.class, int.class,
				int.class, int.class, int.class, String.class, String.class, String.class }, new Object[] {
				UldPlatform.gameInfo.getGameId(), UldPlatform.gameInfo.getServerId(), UldPlatform.mobileDeviceId,
				UldPlatform.statisticAnalysisId, UldPlatform.sChannelId, UldPlatform.sChannelSubId, sid, uid, UldPlatform.sDeviceName });
		MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);

		if (messageReturn.findErr()) {
			loginResult.setIsLogin(false);
			loginResult.setLoginErrorMsg(messageReturn.getErrMsg());
		} else {
			// 通知游戏客户端登录成功
			Map<String, String> retMap = (Map<String, String>) messageReturn.getRetObject();
			if (retMap != null) {
				loginResult.setIsLogin(true);
				loginResult.setChannelUserId(retMap.get("UserId"));
				loginResult.setChannelUserName(retMap.get("UserName"));
				loginResult.setTimeSign(retMap.get("TimeSign"));
				// 外层处理 全局渠道userId，签名，渠道id
			} else {
				loginResult.setIsLogin(false);
				loginResult.setChannelUserId("");
				loginResult.setLoginErrorMsg("登录失败");
			}
		}
		mLoginCallBackListener.onLoginFinished(loginResult);

	}
	/**
	 * 注销
	 * @param activity 
	 */
	public void logout(Activity activity, final LogoutCallBackListener logoutCallBackListener){
		mLogoutCallBackListener=logoutCallBackListener;
		 account.doLogout(activity, new LoginCallBack() {

             @Override
             public void onSuccess(User user, int type) {
            	 mHandler.sendMessage(mHandler.obtainMessage(HandleMessage_CallLogoutSuccess, user));
             }

             @Override
             public void onError(int returnCode, String info) {
            	 mHandler.sendMessage(mHandler.obtainMessage(HandleMessage_CallLogoutFail, info));
             }
           });
	}

	
	
}
