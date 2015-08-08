package uld.sdk.others;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import uld.sdk.unite.AntiAddictedCallBackListener;
import uld.sdk.unite.AntiAddictedResult;
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
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.qihoopay.insdk.activity.ContainerActivity;
import com.qihoopay.insdk.matrix.Matrix;
import com.qihoopay.sdk.protocols.IDispatcherCallback;
import com.qihoopay.sdk.protocols.ProtocolConfigs;
import com.qihoopay.sdk.protocols.ProtocolKeys;

/**
 * 
 * sdk版本：1.0.0 
 * 最后更新时间：2014-4-1 20:12:35
 * @author Tony
 *
 */
public class Sdk360 extends UldBase {

	// 每次登录后，从服务器获取，如果需要更新，提示用户重新登录
	private static String access_token = "";
	private static String refresh_token = "";
	private static Sdk360 sInstance = null;
	private static String ProductName = "金锭";
	private static String Rate = "";
	private AntiAddictedResult antiaddictedResult = null;
	@SuppressWarnings("unused")
	private String username = "";

	// 360SDK登录返回的Json字符串中的Json name，代表CODE模式登录返回的Authorization Code（授权码，60秒有效）。
	private static final String AUTH_CODE = "code";
	// 登录响应模式：CODE模式。
	protected static final String RESPONSE_TYPE_CODE = "code";
	private static final String TAG = "sdk360";

	@SuppressWarnings("unused")
	private static String CHUHAN_APPKEY = "";
	@SuppressWarnings("unused")
	private static String CHUHAN_APPCHANNEL = "";

	@SuppressWarnings("unused")
	private static String Private_Key = "";

	private static int UldUserId = 0;
	private static String UserName = "";

	public static Sdk360 getInstance() {
		if (sInstance == null) {
			sInstance = new Sdk360();
		}
		return sInstance;
	}

	public void initSDK(Activity sContext) {
		CHUHAN_APPKEY = getMetaData(UldPlatform.sContext, "QHOPENSDK_APPKEY");
		CHUHAN_APPCHANNEL = getMetaData(UldPlatform.sContext, "QHOPENSDK_CHANNEL");
		Private_Key = getMetaData(UldPlatform.sContext, "QHOPENSDK_PRIVATEKEY");
		// 传入竖屏动画参数
		Matrix.init(sContext, false, new IDispatcherCallback() {
			@Override
			public void onFinished(String data) {
				// do noting
			}
		});
	}

	private LoginCallBackListener mloginCallBackListener;
	private RechargeCallBackListener mRechargeCallBackListener;
	private int mOrderId;

	public void loginSDK(Activity sContext, GameInfo gameInfo, LoginCallBackListener loginCallBackListener) {

		boolean isLandScape = true;
		boolean isBgTransparent = true;
		mloginCallBackListener = loginCallBackListener;
		Intent intent = getLoginIntent(isLandScape, isBgTransparent);
		Matrix.invokeActivity(UldPlatform.sContext, intent, mLoginCallback);
	}

	@SuppressWarnings("unchecked")
	public void recharge(Activity sContext, GameInfo gameInfo, RechargeCallBackListener rechargeCallBackListener) {
		mOrderId = 0;

		boolean isLandScape = true;

		showLoading();

		final wh.order.model.Order order = new wh.order.model.Order();
		order.setGameId(UldPlatform.gameInfo.getGameId());
		order.setServerId(UldPlatform.gameInfo.getServerId());
		// order.setUserId(UldPlatform.sUser.getUserId());
		// order.setChargedUserId(UldPlatform.sUser.getUserId());
		// order.setChargedUserName(UldPlatform.sUser.getUserName());
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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.User360", "createOrder", new Class<?>[] { wh.order.model.Order.class,
				String.class, int.class, int.class, String.class, String.class }, new Object[] { order, UldPlatform.channelUserId,
				UldPlatform.sChannelId, UldPlatform.sChannelSubId, "", refresh_token });
		MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
		HashMap<String, Object> reMapandmrt = new HashMap<String, Object>();
		HashMap<String, Object> reMap = new HashMap<String, Object>();
		reMapandmrt = (HashMap<String, Object>) (messageReturn.getRetObject());
		MessageReturn mrt = (MessageReturn) reMapandmrt.get("mrt");
		reMap = (HashMap<String, Object>) reMapandmrt.get("reMap");
		access_token = (String) reMap.get("access_token");
		refresh_token = (String) reMap.get("refresh_token");
		hideLoading();
		if (!mrt.findErr()) {
			if (messageReturn.getRetObject() != null) {
				order.setOrderId(Utility.getInt(mrt.getRetObject()));
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

		mRechargeCallBackListener = rechargeCallBackListener;
		Intent intent = getPayIntent(isLandScape, mOrderId);

		// 必需参数，使用360SDK的支付模块。
		intent.putExtra(ProtocolKeys.FUNCTION_CODE, ProtocolConfigs.FUNC_CODE_PAY);

		// 界面相关参数，360SDK登录界面背景是否透明。
		intent.putExtra(ProtocolKeys.IS_LOGIN_BG_TRANSPARENT, true);

		Matrix.invokeActivity(UldPlatform.sContext, intent, mPayCallback);
	}

	// -----------------------------------360 回调函数-------------------------
	// 登录、注册的回调
	private IDispatcherCallback mLoginCallback = new IDispatcherCallback() {

		@Override
		public void onFinished(String data) {
			Log.d(TAG, "mLoginCallback, data is " + data);

			LoginResult loginResult = new LoginResult();
			if (data != null) {
				// 从sdk获取AuthorizationCode
				// String authorizationCode = parseAuthorizationCode(data);
				HashMap<String, String> codeMap = parseAuthorizationCode(data);
				// 请求应用服务器，用AuthorizationCode换取AccessToken
				// 用AccessToken换取UserInfo

				MessageHeader messageHeader = new MessageHeader();
				messageHeader.init();
				MessageBody messageBody = new MessageBody("uld.sdk.bll.User360", "getUserByAccessCode0801", new Class<?>[] { HashMap.class,
						int.class, int.class, int.class, int.class, int.class, int.class,String.class }, new Object[] { codeMap,
						UldPlatform.gameInfo.getGameId(), UldPlatform.gameInfo.getServerId(), UldPlatform.sChannelId,
						UldPlatform.sChannelSubId, UldPlatform.mobileDeviceId, UldPlatform.statisticAnalysisId,UldPlatform.sDeviceName });

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

						Rate = retMap.get("GameRate");
						ProductName = retMap.get("GameMoneyType");
						UldUserId = Utility.getInt(retMap.get("UldUserId"));
						UserName = retMap.get("UserName");
						access_token = retMap.get("access_token");
						refresh_token = retMap.get("refresh_token");
						// TODO 外层处理 全局渠道userId，签名，渠道id
					} else {
						loginResult.setIsLogin(false);
						loginResult.setChannelUserId("");
						loginResult.setLoginErrorMsg("登录失败");
					}
				}
			} else {
				loginResult.setIsLogin(false);
				loginResult.setChannelUserId("");
				loginResult.setLoginErrorMsg("用户取消了登录");
			}

			mloginCallBackListener.onLoginFinished(loginResult);
		}
	};

	// 支付的回调
	private IDispatcherCallback mPayCallback = new IDispatcherCallback() {

		@Override
		public void onFinished(String data) {
			Log.d(TAG, "mPayCallback, data is " + data);
			JSONObject jsonRes;
			try {
				jsonRes = new JSONObject(data);
				// error_code 状态码 0 支付成功 -1 支付取消 1 支付失败 2 支付进行中
				// error_msg 状态描述
				int errorCode = jsonRes.getInt("error_code");
				String errorMsg = jsonRes.getString("error_msg");

				RechargeResult rechargeResult = new RechargeResult();
				if (errorCode == 0) {
					rechargeResult.setOrderId(String.valueOf(mOrderId));
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg(errorMsg);
				} else if (errorCode == 1) {
					rechargeResult.setOrderId(String.valueOf(mOrderId));
					rechargeResult.setOrderType(2);
					rechargeResult.setOrderMsg(errorMsg);
				} else if (errorCode == 2) {
					rechargeResult.setOrderId(String.valueOf(mOrderId));
					rechargeResult.setOrderType(1);
					rechargeResult.setOrderMsg(errorMsg);
				} else if (errorCode == -1) {
					rechargeResult.setOrderId(String.valueOf(mOrderId));
					rechargeResult.setOrderType(3);
					rechargeResult.setOrderMsg(errorMsg);
				}
				mRechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};

	// ------------------------------------------- 360 私用方法--------------------
	/**
	 * 从Json字符中获取授权码
	 * 
	 * @param data
	 *            Json字符串
	 * @return 授权码
	 */
	private HashMap<String, String> parseAuthorizationCode(String data) {
		HashMap<String, String> map = new HashMap<String, String>();
		String authorizationCode = null;
		if (!TextUtils.isEmpty(data)) {
			try {
				JSONObject json = new JSONObject(data);
				int errCode = json.optInt("error_code");
				String error_code = String.valueOf(errCode);
				map.put("error_code", error_code);
				if (errCode == 0) {
					// 只支持code登陆模式
					JSONObject content = json.optJSONObject("data");
					authorizationCode = content.optString(AUTH_CODE);
					map.put("authorizationCode", authorizationCode);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		Log.d(TAG, "parseAuthorizationCode=" + authorizationCode);
		return map;
	}

	/***
	 * 生成调用360SDK登录接口的Intent
	 * 
	 * @param isLandScape
	 *            是否横屏
	 * @param isBgTransparent
	 *            是否背景透明
	 * @param appKey
	 *            应用或游戏的AppKey
	 * @param appChannel
	 *            应用或游戏的自定义子渠道
	 * @return Intent
	 */
	private Intent getLoginIntent(boolean isLandScape, boolean isBgTransparent) {

		Bundle bundle = new Bundle();

		// 界面相关参数，360SDK界面是否以横屏显示。
		bundle.putBoolean(ProtocolKeys.IS_SCREEN_ORIENTATION_LANDSCAPE, isLandScape);

		// 界面相关参数，360SDK登录界面背景是否透明。
		bundle.putBoolean(ProtocolKeys.IS_LOGIN_BG_TRANSPARENT, isBgTransparent);

		// *** 以下非界面相关参数 ***

		// 必需参数，登录回应模式：CODE模式，即返回Authorization Code的模式。
		bundle.putString(ProtocolKeys.RESPONSE_TYPE, RESPONSE_TYPE_CODE);
		// 必需参数，使用360SDK的登录模块。
		bundle.putInt(ProtocolKeys.FUNCTION_CODE, ProtocolConfigs.FUNC_CODE_LOGIN);

		Intent intent = new Intent(UldPlatform.sContext, ContainerActivity.class);
		intent.putExtras(bundle);

		return intent;
	}

	/***
	 * 生成调用360SDK支付接口的Intent
	 * 
	 * @param isLandScape
	 * @param pay
	 * @return Intent
	 */
	private Intent getPayIntent(boolean isLandScape, int orderId) {

		Bundle bundle = new Bundle();
		// *** 以下非界面相关参数 ***

		// 必需参数，所购买商品名称，应用指定，建议中文，最大10个中文字。

		// 实际为游戏币单位
		bundle.putString(ProtocolKeys.PRODUCT_NAME, ProductName);

		// 必需参数，购买商品的商品id，应用指定，最大16字符。
		bundle.putString(ProtocolKeys.PRODUCT_ID, "游老大充值");

		// 必需参数，所购买商品金额, 以分为单位。金额大于等于100分，360SDK运行定额支付流程； 金额数为0，360SDK运行不定额支付流程。
		bundle.putString(ProtocolKeys.AMOUNT, "0");

		// 必需参数，应用方提供的支付结果通知uri，最大255字符。360服务器将把支付接口回调给该uri，具体协议请查看文档中，支付结果通知接口–应用服务器提供接口。
		bundle.putString(ProtocolKeys.NOTIFY_URI, "http://payunite.ulaoda.com/ThreeSixZeroCallback.ashx");

		// 必需参数，游戏或应用名称，最大16中文字。
		bundle.putString(ProtocolKeys.APP_NAME, "游老大");

		// 必需参数，应用内的用户名，如游戏角色名。 若应用内绑定360账号和应用账号，则可用360用户名，最大16中文字。（充值不分区服，
		// 充到统一的用户账户，各区服角色均可使用）。
		// 360用户名
		bundle.putString(ProtocolKeys.APP_USER_NAME, UserName);
		System.out.println(UserName);

		// 必需参数，应用内的用户id。
		// 若应用内绑定360账号和应用账号，充值不分区服，充到统一的用户账户，各区服角色均可使用，则可用360用户ID最大32字符。
		// 为了表示用的是uld的uerid
		bundle.putString(ProtocolKeys.APP_USER_ID, String.valueOf(UldUserId));

		// 可选参数，应用扩展信息1，原样返回，最大255字符。
		bundle.putString(ProtocolKeys.APP_EXT_1, "gameId=" + UldPlatform.gameInfo.getGameId());
		Log.d("test", "gameId=" + bundle.getString(ProtocolKeys.APP_EXT_1));

		// 可选参数，应用扩展信息2，原样返回，最大255字符。
		bundle.putString(ProtocolKeys.APP_EXT_2, "serverId=" + UldPlatform.gameInfo.getServerId());
		Log.d("test", "serverId=" + bundle.getString(ProtocolKeys.APP_EXT_2));

		// 必需参数，360账号id，整数。
		// TODO
		bundle.putString(ProtocolKeys.QIHOO_USER_ID, UldPlatform.channelUserId);

		// 设置QihooPay中的参数。
		// 必需参数，用户access token，要使用注意过期和刷新问题，最大64字符。
		bundle.putString(ProtocolKeys.ACCESS_TOKEN, access_token);

		// 可选参数，应用订单号，应用内必须唯一，最大32字符。
		bundle.putString(ProtocolKeys.APP_ORDER_ID, String.valueOf(orderId));

		// 必需参数，人民币与游戏充值币的默认比例，例如2，代表1元人民币可以兑换2个游戏币，整数。
		bundle.putString(ProtocolKeys.RATE, Rate);

		// 界面相关参数，360SDK界面是否以横屏显示。
		bundle.putBoolean(ProtocolKeys.IS_SCREEN_ORIENTATION_LANDSCAPE, isLandScape);
		Intent intent = new Intent(UldPlatform.sContext, ContainerActivity.class);
		intent.putExtras(bundle);

		return intent;
	}

	@SuppressWarnings("unused")
	private String getImei() {
		return ((TelephonyManager) UldPlatform.sContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}

	static Context sFinishContext = null;

	// 退出游戏
	public void finishGame(Context context) {

		sFinishContext = context;
		Bundle bundle = new Bundle();
		bundle.putBoolean(ProtocolKeys.IS_SCREEN_ORIENTATION_LANDSCAPE, true);
		// 必需参数，使用 360SDK 的退出模块。
		bundle.putInt(ProtocolKeys.FUNCTION_CODE, ProtocolConfigs.FUNC_CODE_QUIT);

		Intent intent = new Intent(context, ContainerActivity.class);
		intent.putExtras(bundle);
		Matrix.invokeActivity(context, intent, mQuitCallback);

	}

	private IDispatcherCallback mQuitCallback = new IDispatcherCallback() {
		@Override
		public void onFinished(String data) {
			int whichValue = 0;
			if (data.split(",").length >= 2) {
				String[] keyValues = data.split(",");
				whichValue = Utility.getInt(keyValues[0].split(":")[1]);
			}
			if (whichValue == 1) {
				// 进入论坛
				Bundle bundle = new Bundle();
				bundle.putBoolean(ProtocolKeys.IS_SCREEN_ORIENTATION_LANDSCAPE, true);
				bundle.putInt(ProtocolKeys.FUNCTION_CODE, ProtocolConfigs.FUNC_CODE_BBS);
				Intent intent = new Intent(sFinishContext, ContainerActivity.class);
				intent.putExtras(bundle);
				((Activity) sFinishContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				Matrix.invokeActivity(sFinishContext, intent, null);
				System.exit(0);
			} else if (whichValue == 2) {
				// 退出游戏
				((Activity) sFinishContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				((Activity) sFinishContext).finish();
				System.exit(0);
			} else {
				((Activity) sFinishContext).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
				System.exit(0);
			}
		}
	};

	// 实名注册的接口
	public void doSdkRealNameRegister(Context scontext) {

		Bundle bundle = new Bundle();
		// 界面相关参数，360SDK 界面是否以横屏显示。
		bundle.putBoolean(ProtocolKeys.IS_SCREEN_ORIENTATION_LANDSCAPE, true);
		// 背景是否透明
		bundle.putBoolean(ProtocolKeys.IS_LOGIN_BG_TRANSPARENT, true);
		// 必需参数，360 账号 id，整数。
		bundle.putString(ProtocolKeys.QIHOO_USER_ID, UldPlatform.channelUserId);
		// 必需参数，使用 360SDK 的实名注册模块。
		bundle.putInt(ProtocolKeys.FUNCTION_CODE, ProtocolConfigs.FUNC_CODE_REAL_NAME_REGISTER);
		Intent intent = new Intent(scontext, ContainerActivity.class);
		intent.putExtras(bundle);
		Matrix.invokeActivity(scontext, intent, mRealNameRegisterCallback);
	}

	// 实名注册的回调
	private IDispatcherCallback mRealNameRegisterCallback = new IDispatcherCallback() {
		@Override
		public void onFinished(String data) {
			// 返回（无数据，仅通知调用方流程结束） 返回值为null
		}
	};

	// 防沉迷接口
	public void getAntiAddictionIntent(Context scontext, final AntiAddictedCallBackListener antiaddictedCallBackListener) {

		Bundle bundle = new Bundle();
		// 必需参数，用户 access token，要使用注意过期和刷新问题，最大 64 字符。
		bundle.putString(ProtocolKeys.ACCESS_TOKEN, access_token);
		// 必需参数，360 账号 id，整数。
		bundle.putString(ProtocolKeys.QIHOO_USER_ID, UldPlatform.channelUserId);
		// 必需参数，使用 360SDK 的防沉迷查询模块。
		bundle.putInt(ProtocolKeys.FUNCTION_CODE, ProtocolConfigs.FUNC_CODE_ANTI_ADDICTION_QUERY);
		Intent intent = new Intent(scontext, ContainerActivity.class);
		intent.putExtras(bundle);
		antiaddictedResult = new AntiAddictedResult();
		Matrix.execute(scontext, intent, new IDispatcherCallback() {
			@Override
			public void onFinished(String data) {
				if (data.contains("\"status\":\"2\"")) {
					data = "2";
				} else if (data.contains("\"status\":\"1\"")) {
					data = "1";
				} else {
					data = "0";
				}
				antiaddictedResult.setAntiAddictionMsg(data);
				antiaddictedCallBackListener.onAntiAddictedFinished(antiaddictedResult);
			}
		});
	}

	// 浮动窗
	public void setFloatingBar(Context context) {
		Bundle bundle = new Bundle();
		// 界面相关参数，360SDK界面是否以横屏显示。
		bundle.putBoolean(ProtocolKeys.IS_SCREEN_ORIENTATION_LANDSCAPE, true);

		bundle.putInt(ProtocolKeys.FUNCTION_CODE, ProtocolConfigs.FUNC_CODE_SETTINGS);

		Intent intent = new Intent(context, ContainerActivity.class);
		intent.putExtras(bundle);

		Matrix.execute(context, intent, new IDispatcherCallback() {
			@Override
			public void onFinished(String data) {

			}
		});
	}

	// 浮动窗销毁
	public void setFinishFloatingBar(Context context) {
		Matrix.destroy(context);
	}
}
