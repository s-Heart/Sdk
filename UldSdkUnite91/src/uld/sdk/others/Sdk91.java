package uld.sdk.others;

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
import uld.sdk.unite.OnInitFinishListener;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import uld.sdk.unite.UldBase;
import uld.sdk.unite.UldPlatform;
import wh.order.model.OrderEnum;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.Toast;

import com.nd.commplatform.NdCallbackListener;
import com.nd.commplatform.NdCommplatform;
import com.nd.commplatform.NdErrorCode;
import com.nd.commplatform.NdMiscCallbackListener;
import com.nd.commplatform.NdMiscCallbackListener.OnPlatformBackground;
import com.nd.commplatform.OnInitCompleteListener;
import com.nd.commplatform.entry.NdAppInfo;

// SDK 版本3.2.6.3
public class Sdk91 extends UldBase {
	private static Sdk91 sInstance = null;
	private HandlerThread mSDKSetupThread;
	private boolean mCheckUpdated = false;
	private OnInitCompleteListener mOnInitCompleteListener;
	// get平台appid,appKey;
	public static int AppId_91 = 106670;
	public static String AppKey_91 = "b67abdb22bfd16d36dcf0328f48f2193476f32ca1ee8452f";

	public static Sdk91 getInstance() {
		if (sInstance == null) {
			sInstance = new Sdk91();
		}
		return sInstance;
	}

	public void init91SdkThread(final Context context) {

		// SDK 初始化比较久，使用独立线程
		mSDKSetupThread = new HandlerThread("init[91sdk]", android.os.Process.THREAD_PRIORITY_BACKGROUND);
		mSDKSetupThread.start();
		new Handler().post(new Runnable() {

			@Override
			public void run() {
				init91SDK(context);
			}
		});
	}

	/**
	 * 初始化91SDK
	 */
	public void init91SDK325(final Activity context, final OnInitFinishListener onInitFinishListener) {
		// showLoading();

		NdAppInfo appInfo = new NdAppInfo();
		appInfo.setCtx(UldPlatform.sContext);

		AppId_91 = uld.sdk.tools.Utility.getInt(getMetaData(UldPlatform.sContext, "uldAppId"));
		AppKey_91 = getMetaData(UldPlatform.sContext, "uldAppKey");

		appInfo.setAppId(AppId_91);
		appInfo.setAppKey(AppKey_91);

		Log.d("91sdk", "AppId_91:" + AppId_91);
		Log.d("91sdk", "AppKey_91:" + AppKey_91);
		mOnInitCompleteListener = new OnInitCompleteListener() {

			@Override
			protected void onComplete(int ndFlag) {
				Log.d("91sdk", "initial finish ndFlag" + ndFlag);
				// ctx.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
				onInitFinishListener.onComplete(ndFlag);
			}
		};
		appInfo.setNdVersionCheckStatus(NdAppInfo.ND_VERSION_CHECK_LEVEL_STRICT);
		int orient = NdCommplatform.SCREEN_ORIENTATION_LANDSCAPE;
		NdCommplatform.getInstance().ndSetScreenOrientation(orient);

		// NdCommplatform.getInstance().ndLogout(NdCommplatform.LOGOUT_TO_RESET_AUTO_LOGIN_CONFIG,
		// context);
		// TODO: 发布前取消测试模式
		// NdCommplatform.getInstance().ndSetDebugMode(0);
		NdCommplatform.getInstance().ndInit(context, appInfo, mOnInitCompleteListener);

		// 注销会话以取消自动登录
		// NdCommplatform.getInstance().ndLogout(NdCommplatform.LOGOUT_TO_RESET_AUTO_LOGIN_CONFIG,
		// context);
		// Log.d("91SDK", "initial");
		// if (!mCheckUpdated) {
		// Log.d("91SDK", "begin update");
		// mCheckUpdated = true;
		// updateVersion_91();
		// Log.d("91SDK", "end update");
		// hideLoading();
		// } else {
		// hideLoading();
		// }
	}

	/**
	 * 初始化91SDK
	 */
	private void init91SDK(Context context) {
		showLoading();

		NdAppInfo appInfo = new NdAppInfo();
		appInfo.setCtx(UldPlatform.sContext);

		AppId_91 = uld.sdk.tools.Utility.getInt(getMetaData(UldPlatform.sContext, "uldAppId"));
		AppKey_91 = getMetaData(UldPlatform.sContext, "uldAppKey");

		appInfo.setAppId(AppId_91);
		appInfo.setAppKey(AppKey_91);
		NdCommplatform.getInstance().initial(0, appInfo); // 初始化91SDK

		int orient = NdCommplatform.SCREEN_ORIENTATION_LANDSCAPE;

		NdCommplatform.getInstance().ndSetScreenOrientation(orient);

		// TODO: 发布前取消测试模式
		// NdCommplatform.getInstance().ndSetDebugMode(0);

		// 注销会话以取消自动登录
		// NdCommplatform.getInstance().ndLogout(NdCommplatform.LOGOUT_TO_RESET_AUTO_LOGIN_CONFIG,
		// context);
		Log.d("91SDK", "initial");
		if (!mCheckUpdated) {
			Log.d("91SDK", "begin update");
			mCheckUpdated = true;
			updateVersion_91();
			Log.d("91SDK", "end update");
			hideLoading();
		} else {
			hideLoading();
		}
	}

	/**
	 * 91帐号登录
	 * 
	 */
	public void login(Context context, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {

		showLoading();

		NdCommplatform.getInstance().ndLogin(context, new NdMiscCallbackListener.OnLoginProcessListener() {

			@Override
			public void finishLoginProcess(int code) {

				LoginResult loginResult = new LoginResult();
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("登录失败");

				String tip = "";

				// 登录的返回码检查
				if (code == NdErrorCode.ND_COM_PLATFORM_SUCCESS) {

					String sessionId = com.nd.commplatform.NdCommplatform.getInstance().getSessionId();
					String uid = com.nd.commplatform.NdCommplatform.getInstance().getLoginUin();

					if (!Utility.isEmpty(sessionId)) {
						MessageHeader messageHeader = new MessageHeader();
						messageHeader.init();

						MessageBody messageBody = new MessageBody("uld.sdk.bll.User91", "login", new Class<?>[] { int.class, int.class,
								int.class, int.class, int.class, int.class, String.class, String.class, int.class, String.class },
								new Object[] { gameInfo.getGameId(), gameInfo.getServerId(), UldPlatform.mobileDeviceId,
										UldPlatform.statisticAnalysisId, UldPlatform.sChannelId, UldPlatform.sChannelSubId, sessionId, uid,
										AppId_91, AppKey_91, UldPlatform.sDeviceName });
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
								// TODO 外层处理 全局渠道userId，签名，渠道id
							} else {
								loginResult.setIsLogin(false);
								loginResult.setChannelUserId("");
								loginResult.setLoginErrorMsg("登录失败");
							}
						}
					}

					hideLoading();
				} else if (code == NdErrorCode.ND_COM_PLATFORM_ERROR_CANCEL) {
					tip = "取消登录";
					loginResult.setLoginErrorMsg(tip);
					hideLoading();
				} else {
					tip = "网络错误，请退出游戏重新登录";
					loginResult.setLoginErrorMsg(tip);
					hideLoading();
				}

				loginCallBackListener.onLoginFinished(loginResult);
			}
		});

	}

	public void recharege(final RechargeCallBackListener rechargeCallBackListener) {

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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.User91", "createOrder", new Class<?>[] { wh.order.model.Order.class,
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

		// 异步记录playerid 已在服务器处理
		// wh.order.model.OrderLog orderLog = new wh.order.model.OrderLog();
		// orderLog.setCreateDate(new Date());
		// orderLog.setLogName("playerid");
		// orderLog.setDescription(UldPlatform.gameInfo.getPlayerId());
		// orderLog.setOrderId(order.getOrderId());
		// orderLog.setCreateDate(new Date());
		// orderLog.setStatus((byte) 1);
		// orderLog.setUserId(UldPlatform.sUser.getUserId());
		// messageHeader.init();
		// messageBody = new MessageBody("wh.order.bll.OrderLog",
		// "createUpdate", new Class<?>[] { wh.order.model.OrderLog.class },
		// new Object[] { orderLog });
		// ThreadManager.sendMessageInvoke(messageHeader, messageBody);

		int result = NdCommplatform.getInstance().ndUniPayForCoin(String.valueOf(order.getOrderId()), 0, "傲剑金锭充值", UldPlatform.sContext);

		// ND_COM_PLATFORM_ERROR_HAS_NOT_LOGIN 未登录
		// ND_COM_PLATFORM_SUCCESS 成功进入界面
		if (result == NdErrorCode.ND_COM_PLATFORM_SUCCESS) {
			NdCommplatform.getInstance().setOnPlatformBackground(new OnPlatformBackground() {

				@Override
				public void onPlatformBackground() {

					RechargeResult rechargeResult = new RechargeResult();
					rechargeResult.setOrderId(String.valueOf(order.getOrderId()));
					rechargeResult.setOrderType(1);
					rechargeResult.setOrderMsg("充值已提交");

					NdCommplatform.getInstance().setOnPlatformBackground(null);
					rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
				}
			});
		} else {
			RechargeResult rechargeResult = new RechargeResult();
			rechargeResult.setOrderId(String.valueOf(order.getOrderId()));
			rechargeResult.setOrderType(2);
			rechargeResult.setOrderMsg("平台登录过期或账号在其他地方登录，请重新登录。");
			rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
		}
	}

	/**
	 * 检查版本更新
	 */
	private void updateVersion_91() {
		NdCallbackListener<Integer> callback = new NdCallbackListener<Integer>() {

			@Override
			public void callback(int responseCode, Integer t) {
				hideLoading();
				if (responseCode == NdErrorCode.ND_COM_PLATFORM_SUCCESS) {
					switch (t) {
					case NdCommplatform.UPDATESTATUS_NONE:
						// 本地已是最新版本，此时玩家可直接进入游戏
						Log.d("91sdk", "本地已是最新版本，此时玩家可直接进入游戏");
						break;
					case NdCommplatform.UPDATESTATUS_UNMOUNTED_SDCARD:
						Log.d("91sdk", "玩家手机无SD卡，不进行更新");
						// 玩家手机无SD卡，不进行更新
						break;
					case NdCommplatform.UPDATESTATUS_CANCEL_UPDATE:
						// 玩家取消更新，此时玩家可继续玩旧版本的游戏
						Log.d("91sdk", "玩家取消更新，此时玩家可继续玩旧版本的游戏");
						break;
					case NdCommplatform.UPDATESTATUS_CHECK_FAILURE:
						// 新版本检查失败
						Log.d("91sdk", "新版本检查失败");
						break;
					case NdCommplatform.UPDATESTATUS_FORCES_LOADING:
						// 玩家选择了强制更新，此时如果说游戏要在新版本才能玩的话，可强制玩家推出游戏，等下载安装完后再继续。
						// 此时会在手机桌面的的消息通知栏下载本游戏的最新版本
						Log.d("91sdk", "玩家选择了强制更新，");
						UldPlatform.sContext.finish();
						break;
					case NdCommplatform.UPDATESTATUS_RECOMMEND_LOADING:
						// 玩家选择了推荐更新，此时会在手机桌面的的消息通知栏下载本游戏的最新版本，不影响玩家继续玩本游戏，等下载完成后玩家可自行安装
						Log.d("91sdk", "玩家选择了推荐更新，");
						break;
					default: {
					}
					}

				} else {
					Toast.makeText(UldPlatform.sContext, "网络异常，请检查网络", Toast.LENGTH_LONG).show();
					UldPlatform.sContext.finish();
				}
			}
		};
		// 91SDK 检查版本更新
		// NdCommplatform.getInstance().ndAppVersionUpdate(UldPlatform.sContext,
		// callback);
	}
}
