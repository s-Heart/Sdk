package uld.sdk.others;

import java.util.Date;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import uld.sdk.unite.UldBase;
import uld.sdk.unite.UldPlatform;
import wh.order.model.OrderEnum;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.duoku.platform.DkErrorCode;
import com.duoku.platform.DkPlatform;
import com.duoku.platform.DkPlatformSettings;
import com.duoku.platform.DkPlatformSettings.GameCategory;
import com.duoku.platform.DkProtocolConfig;
import com.duoku.platform.DkProtocolKeys;
import com.duoku.platform.IDKSDKCallBack;
import com.duoku.platform.ui.DKContainerActivity;
import com.duoku.platform.ui.DKPaycenterActivity;

/**
 * sdk版本：1.3.1 最后更新：2014年5月15日13:38:03
 * 
 * @author Tony
 * 
 */
public class SdkBaiDuDuoKu extends UldBase {

	private static SdkBaiDuDuoKu instance = null;
	private static Activity sActivity = null;

	private static Context sContext = null;
	private static String _userId = null;//用户id
	private static String _userSessionId = null;//用户sessionId
	// private OnExitChargeCenterListener mOnExitChargeCenterListener;

	public static SdkBaiDuDuoKu getInstance() {
		if (instance == null) {
			instance = new SdkBaiDuDuoKu();
		}
		return instance;
	}

	/*
	 * 初始化
	 */
	public void initSDK(final Activity activity) {

		DkPlatformSettings appInfo = new DkPlatformSettings();
		appInfo.setGameCategory(GameCategory.ONLINE_Game);
		appInfo.setAppid("1764");// 应用ID，由多酷分配
		appInfo.setAppkey("ef593f28c501778ec2a860c127714349");// 应用Key，由多酷分配
		// appInfo.setmApp_secret(APPSECRET_CHUHAN);//
		// 应用appSecret，数据交互加密使用，由多酷分配
		// 设置SDK的横竖屏显示
		appInfo.setOrient(DkPlatformSettings.SCREEN_ORIENTATION_LANDSCAPE);
		DkPlatform.init(activity, appInfo); // 初始化多酷SDK
		// 2、初始化结束之后，设置悬浮窗回调，目前悬浮窗中仅切换帐号功能支持回调，暂时只需处理这一种情况。不设置该回调或者设置错误回调，将无法通过上线测试。
//		setDkSuspendWindowCallBack();
	}

	// 登录接口
	public void loginSDK(Activity context, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {

		DkPlatform.invokeActivity(context, getLoginIntent(), new IDKSDKCallBack() {
			@Override
			public void onResponse(String paramString) {
				// onResponse()方法中的paramString为返回结果JSON串，例如：登录成功通知内容格式如下：
				// {"user_id":"103256","user_name":"dktest","user_sessionid":"52469875215469875462545698754562","state_code":1021}
				// 其中：state_code为状态码，"user_id"为用户id,
				// "user_name"为用户的用户名，"user_sessionid"为用户的sessionid
				int _loginState = 0;
				String _userName = null;
				JSONObject jsonObj;
				try {
					jsonObj = new JSONObject(paramString);
					_loginState = jsonObj.getInt(DkProtocolKeys.FUNCTION_STATE_CODE);// 用户登录状态
					_userName = jsonObj.getString(DkProtocolKeys.USER_NAME);// 用户姓名
					_userId = jsonObj.getString(DkProtocolKeys.USER_ID);// 用户id
					_userSessionId = jsonObj.getString(DkProtocolKeys.USER_SESSIONID);// 用户seesionId
				} catch (JSONException e) {

				}
				LoginResult loginResult = new LoginResult();
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("请稍后再试");
				if (DkErrorCode.DK_LOGIN_SUCCESS == _loginState) {
					String uid = _userId;
					String sid = _userSessionId;

					// 登录成功
					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();

					MessageBody messageBody = new MessageBody("uld.sdk.bll.UserBaiDuDuoKu", "login", new Class<?>[] { int.class, int.class,
							int.class, int.class, int.class, int.class, String.class, String.class, String.class }, new Object[] {
							gameInfo.getGameId(), gameInfo.getServerId(), UldPlatform.mobileDeviceId, UldPlatform.statisticAnalysisId,
							UldPlatform.sChannelId, UldPlatform.sChannelSubId, sid, uid, UldPlatform.sDeviceName });
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
							loginResult.setChannelUserId(retMap.get("UserId"));
							loginResult.setChannelUserName(retMap.get("UserName"));
							loginResult.setTimeSign(retMap.get("TimeSign"));
							// TODO 外层处理 全局渠道userId，签名，渠道id
						} else {
							loginResult.setIsLogin(false);
							loginResult.setChannelUserId("");
							loginResult.setLoginErrorMsg("服务器异常,请稍后再试");
						}
					}
					loginCallBackListener.onLoginFinished(loginResult);
				} else if (DkErrorCode.DK_LOGIN_CANCELED == _loginState) {
					// 取消登录回调，必须正确处理
					loginResult.setIsLogin(false);
					loginResult.setChannelUserId("");
					loginResult.setLoginErrorMsg("取消登录回调，必须正确处理");
					loginCallBackListener.onLoginFinished(loginResult);
				} else if (DkErrorCode.DK_NEEDLOGIN == _loginState) {
					// 用户登录状态失效回调，必须正确处理
					loginResult.setIsLogin(false);
					loginResult.setChannelUserId("");
					loginResult.setLoginErrorMsg("用户登录状态失效回调，必须正确处理");
					loginCallBackListener.onLoginFinished(loginResult);
				}
			}
		});
	}

	public Intent getLoginIntent() {
		Bundle bundle = new Bundle();
		bundle.putInt(DkProtocolKeys.FUNCTION_CODE, DkProtocolConfig.FUNCTION_LOGIN);
		Intent intent = new Intent(UldPlatform.sContext, DKContainerActivity.class);
		intent.putExtras(bundle);
		return intent;
	}

	// 充值接口
	/**
	 * @param context
	 *            上下文
	 * @param gameInfo
	 *            游戏信息
	 * @param rechargeCallBackListener
	 *            充值回调
	 */
	public void recharge(final Activity context, final GameInfo gameInfo, final RechargeCallBackListener rechargeCallBackListener) {

		showLoading();

		final wh.order.model.Order order = new wh.order.model.Order();
		order.setGameId(UldPlatform.gameInfo.getGameId());
		order.setServerId(UldPlatform.gameInfo.getServerId());
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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserBaiDuDuoKu", "createOrder", new Class<?>[] { wh.order.model.Order.class,
				String.class, int.class, int.class, String.class }, new Object[] { order, UldPlatform.channelUserId,
				UldPlatform.sChannelId, UldPlatform.sChannelSubId, _userId });
		MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
		hideLoading();
		if (!messageReturn.findErr()) {
			if (messageReturn.getRetObject() != null) {
				order.setOrderId(Utility.getInt(messageReturn.getRetObject()));
			}
		}

		if (order.getOrderId() <= 0) {
			RechargeResult rechargeResult = new RechargeResult();
			rechargeResult.setOrderId("");
			rechargeResult.setOrderType(3);
			rechargeResult.setOrderMsg("其他错误");
			rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			return;
		}

		/**
		 * 参数说明
		 * 
		 * @param amount
		 *            定额支付消费金额（人民币），如购买1元的物品或者游戏币，则amount为1；若不需要定额支付则传入0
		 * @param exchangeRatio
		 *            兑换比例，如1酷币兑换100游戏币，则兑换比例为100
		 * @param strGamebiName
		 *            游戏币名称，如金币、符石、元宝等
		 * @param strOrderId
		 *            cp生成的订单号，充值结束后，多酷服务器会通知业务服务器该订单号及充值结果
		 * @param strPayDesc
		 *            支付描述，cp可以在此处自定义字段完成自己的需求，若不需要传入空串，请勿传null
		 * @return 返回Intent对象
		 */
		DkPlatform.invokeActivity(context, getRechargeIntent(0, 10, "金锭", String.valueOf(order.getOrderId()), "游老大"),
				new IDKSDKCallBack() {

					@Override
					public void onResponse(String paramString) {
						Log.i(getClass().getName(), paramString);
						
						try {
							JSONObject jsonObject = new JSONObject(paramString);
							
							int mStateCode = jsonObject.getInt(DkProtocolKeys.FUNCTION_STATE_CODE);		// 状态码
							String mMessage = jsonObject.getString(DkProtocolKeys.FUNCTION_MESSAGE);		// 信息
							String mOrderId = (!jsonObject.isNull(DkProtocolKeys.FUNCTION_ORDER_ID)) 
									? jsonObject.getString(DkProtocolKeys.FUNCTION_ORDER_ID) : "" ;				// 订单号
							
							if(mStateCode == DkErrorCode.DK_ORDER_NEED_CHECK) { 									// 需要查询订单
								
								RechargeResult rechargeResult = new RechargeResult();
								rechargeResult.setOrderId(mOrderId);
								rechargeResult.setOrderType(0);
								rechargeResult.setOrderMsg("充值成功");
								rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
							} else if (mStateCode == DkErrorCode.DK_ORDER_NOT_CHECK) {							// 不需要查询订单
								RechargeResult rechargeResult = new RechargeResult();
								rechargeResult.setOrderId(mOrderId);
								rechargeResult.setOrderType(2);
								rechargeResult.setOrderMsg("充值失败");
								rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
							}
					}catch(Exception e){
						
					}
					}
				});

		Log.d("DuoKu", String.valueOf(order.getOrderId()));

	}

	
	/**
	 * 参数说明
	 * 
	 * @param amount
	 *            定额支付消费金额（人民币），如购买1元的物品或者游戏币，则amount为1；若不需要定额支付则传入0
	 * @param exchangeRatio
	 *            兑换比例，如1酷币兑换100游戏币，则兑换比例为100
	 * @param strGamebiName
	 *            游戏币名称，如金币、符石、元宝等
	 * @param strOrderId
	 *            cp生成的订单号，充值结束后，多酷服务器会通知业务服务器该订单号及充值结果
	 * @param strPayDesc
	 *            支付描述，cp可以在此处自定义字段完成自己的需求，若不需要传入空串，请勿传null
	 * @return 返回Intent对象
	 */
	private Intent getRechargeIntent(int amount, int exchangeRatio, String strGamebiName, String strOrderId, String strPayDesc) {
		Bundle bundle = new Bundle();
		bundle.putInt(DkProtocolKeys.FUNCTION_CODE, DkProtocolConfig.FUNCTION_Pay);
		bundle.putString(DkProtocolKeys.FUNCTION_AMOUNT, amount + ""); // 金额（转换成String）
		bundle.putString(DkProtocolKeys.FUNCTION_EXCHANGE_RATIO, exchangeRatio + ""); // 兑换比例
																						// （转换成String）
		bundle.putString(DkProtocolKeys.FUNCTION_ORDER_ID, strOrderId); // 订单号
		bundle.putString(DkProtocolKeys.FUNCTION_PAY_DESC, strPayDesc); // 支付描述
		bundle.putString(DkProtocolKeys.FUNCTION_GAMEBI_NAME, strGamebiName); // 游戏币名称
		Intent intent = new Intent(UldPlatform.sContext, DKPaycenterActivity.class);
		intent.putExtras(bundle);

		return intent;
	}

//	// 设置悬浮窗回调
//		public void setDkSuspendWindowCallBack() {
//			DkPlatform.setDKSuspendWindowCallBack(new IDKSDKCallBack() {
//
//				@Override
//				public void onResponse(String paramString) {
//					// paramString为返回结果JSON串，切换帐号json串示例如下：
//					// {"state_code":2005}
//					int _stateCode = 0;
//					try {
//						JSONObject _jsonObj = new JSONObject(paramString);
//						_stateCode = _jsonObj.getInt(DkProtocolKeys.FUNCTION_STATE_CODE);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//
//					if (_stateCode == DkErrorCode.DK_CHANGE_USER) {
//						// 切换帐号处理逻辑，以下代码仅为示例代码，cp可根据自身需要进行操作，如重新弹出登录界面等
//						Intent i = UldPlatform.sContext.getPackageManager().getLaunchIntentForPackage(UldPlatform.sContext.getPackageName());
//						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						UldPlatform.sContext.startActivity(i);
//					}
//				}
//			});
//		}
	// 退出游戏
	/**
	 * @param context
	 */
	public void finishGame(Context context) {
		// DkPlatform.getInstance().dkLogout((Activity) context);
		// 若不想下次登录时进行自动登录，CP 可在退出时调用该接口；该接口将清除自
		// 动登录状态。
		DkPlatform.doDKUserLogout();
	}
}
