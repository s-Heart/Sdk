package uld.sdk.others;

import java.util.Map;

import cn.uc.gamesdk.UCCallbackListener;
import cn.uc.gamesdk.UCCallbackListenerNullException;
import cn.uc.gamesdk.UCGameSDK;
import cn.uc.gamesdk.UCGameSDKStatusCode;
import cn.uc.gamesdk.UCLogLevel;
import cn.uc.gamesdk.UCLoginFaceType;
import cn.uc.gamesdk.UCOrientation;
import cn.uc.gamesdk.info.FeatureSwitch;
import cn.uc.gamesdk.info.GameParamInfo;
import cn.uc.gamesdk.info.OrderInfo;
import cn.uc.gamesdk.info.PaymentInfo;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.GotoPlatCallBackListener;
import uld.sdk.unite.InitCallBackListener;
import uld.sdk.unite.InitCallBackListener.ULDSDKCallBackStatus;
import uld.sdk.unite.InitResult;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import uld.sdk.unite.LogoutCallBackListener;
import uld.sdk.unite.LogoutResult;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import uld.sdk.unite.UldBase;
import uld.sdk.unite.UldPlatform;
import android.R.bool;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.text.format.Time;
import android.util.Log;

/**
 * UC-SDK v.3.3.3 最后更新时间：2014-5-22 16:00:10
 * 日志:
 * 2014-5-22 16:00:21-------------------------
 * 删除uc原退出接口,将其调用改为游戏方处理,具体调用见demo
 * 
 * @author 史少杰
 * 
 */
public class SdkUc extends UldBase {
	private static SdkUc instance = null;
	private static Boolean sIsUcInitSuccess = false;

	public static SdkUc getInstance() {
		if (instance == null) {
			instance = new SdkUc();
		}
		return instance;
	}

	/**
	 * 初始化SDK
	 * 
	 * @param context
	 *            上下文
	 * @param gameInfo
	 *            参数
	 * @param initCallBackListener
	 *            回调函数
	 */
	public void initSDK(final Context context, GameInfo gameInfo, final InitCallBackListener initCallBackListener) {

		// Log.d("SdkUc", "sIsUcInitSuccess" + sIsUcInitSuccess);
		if (sIsUcInitSuccess) {
			return;
		}

		GameParamInfo gameParams = new GameParamInfo();
		// int gameId = uld.sdk.tools.Utility.getInt(getMetaData(context,
		// "gameId"));
		// int serverId = uld.sdk.tools.Utility.getInt(getMetaData(context,
		// "serverId"));
		// int cpId = uld.sdk.tools.Utility.getInt(getMetaData(context,
		// "cpId"));

		int gameId = 534887;
		int serverId = 2546;
		int cpId = 23992;
		gameParams.setGameId(gameId);
		gameParams.setServerId(serverId);
		gameParams.setCpId(cpId);
		// 设置是否支持查询充值历史和显示切换账号功能，如果不设置，默认具有查询充值历史记录和
		// 切换账户功能
		// gameParams.setFeatureSwitch(new FeatureSwitch(true, true));

		try {
			// 设置登录界面：
			// USE_WIDGET - 简版登录界面
			// USE_STANDARD - 标准版登录界面
			// UCGameSDK.defaultSDK().setLoginUISwitch(UCLoginFaceType.USE_WIDGET);

			UCGameSDK.defaultSDK().setOrientation(UCOrientation.LANDSCAPE);

			Log.d("SdkUc", "UCSDK初始化开始");

			Boolean debugMode = false;
			Log.d("SdkUc", "调试状态" + debugMode);
			UCGameSDK.defaultSDK().initSDK(context, UCLogLevel.ERROR, debugMode, gameParams, new UCCallbackListener<String>() {
				@Override
				public void callback(int code, String msg) {
					InitResult initResult = new InitResult();
					initResult.setErrCode(1);
					initResult.setErrMsg("初始化失败");

					switch (code) {
					case UCGameSDKStatusCode.SUCCESS:
						sIsUcInitSuccess = true;
						// Log.d("SdkUc", "UCSDK初始化成功" + sIsUcInitSuccess);
						initResult.setErrCode(0);
						initResult.setErrMsg("初始化成功");
						initCallBackListener.onInitFinished(initResult);
						break;
					case UCGameSDKStatusCode.INIT_FAIL:
						initCallBackListener.onInitFinished(initResult);
						Log.d("SdkUc", "UCSDK初始化失败1111");
						break;
					default:
						// Log.d("SdkUc", "UCSDK初始化失败");
						initCallBackListener.onInitFinished(initResult);
						break;
					}
				}
			});
		} catch (UCCallbackListenerNullException e) {

			InitResult initResult = new InitResult();
			initResult.setErrCode(1);
			initResult.setErrMsg("初始化失败" + e.getMessage());

			initCallBackListener.onInitFinished(initResult);
		}
	}

	private static boolean mIsUcLoginSuccess = false;

	/**
	 * 登录SDK
	 * 
	 * @param context
	 *            上下文
	 * @param loginCallBackListener
	 *            登录回调函数
	 */
	public void LoginSDK(Activity context, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {
		mIsUcLoginSuccess = false;

		Log.d("SdkUc", "loginSDk" + String.valueOf(gameInfo.getServerId()));
		try {

			// int i = 0;
			// // 等待UC初始化完成
			// while (!sIsUcInitSuccess && i < 15) {
			// Thread.sleep(1000);
			// i++;
			// }

			UCGameSDK.defaultSDK().login(context, new UCCallbackListener<String>() {
				@Override
				public void callback(int code, String msg) {
					LoginResult loginResult = new LoginResult();
					switch (code) {
					case UCGameSDKStatusCode.SUCCESS:
						Log.d("SdkUc", "login callback SUCCESS");
						mIsUcLoginSuccess = true;
						break;
					case UCGameSDKStatusCode.LOGIN_EXIT:
						Log.d("SdkUc", "login callback LOGIN_EXIT, mIsUcLoginSuccess =" + mIsUcLoginSuccess);
						if (mIsUcLoginSuccess) {
							checkSId(UCGameSDK.defaultSDK().getSid(), gameInfo, loginCallBackListener);
						} else {
							loginResult.setIsLogin(false);
							loginResult.setLoginErrorMsg("登录退出");
							loginCallBackListener.onLoginFinished(loginResult);
						}
						break;
					case UCGameSDKStatusCode.NO_INIT:
						Log.d("SdkUc", "login callback NO_INIT");
						loginResult.setIsLogin(false);
						loginResult.setLoginErrorMsg("未初始化");
						loginCallBackListener.onLoginFinished(loginResult);
						break;
					default:
						Log.d("SdkUc", "login callback default, code = " + code);
						loginResult.setIsLogin(false);
						loginResult.setLoginErrorMsg("其他错误");
						loginCallBackListener.onLoginFinished(loginResult);
						break;
					}
				}
			});
		} catch (UCCallbackListenerNullException e) {
			Log.d("SdkUc", "login callback default, error, UCCallbackListenerNullException" + e.getMessage());
			e.printStackTrace();
			String errMsg = "UCCallbackListenerNullException";
			if (e != null && e.getMessage() != null) {
				errMsg = e.getMessage();
			}

			LoginResult loginResult = new LoginResult();
			loginResult.setIsLogin(false);
			loginResult.setLoginErrorMsg(errMsg);
			loginCallBackListener.onLoginFinished(loginResult);
		} catch (Exception e) {
			Log.d("SdkUc", "login callback default, error, Exception" + e.getMessage());

			LoginResult loginResult = new LoginResult();
			loginResult.setIsLogin(false);
			loginResult.setLoginErrorMsg(e.getMessage());
			loginCallBackListener.onLoginFinished(loginResult);
		}
	}

	/**
	 * 验证帐号
	 * 
	 * @param sid
	 *            SessionId
	 * @param loginCallBackListener
	 *            登录回调函数
	 */
	private void checkSId(final String sid, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {
		new Thread() {
			@Override
			public void run() {
				LoginResult loginResult = new LoginResult();
				MessageHeader messageHeader = new MessageHeader();
				messageHeader.init();
				MessageBody messageBody = new MessageBody("uld.sdk.bll.UserUC", "checkSid", new Class<?>[] { String.class, int.class,
						int.class, int.class, int.class, int.class, int.class, String.class }, new Object[] { sid, gameInfo.getGameId(),
						gameInfo.getServerId(), UldPlatform.sChannelId, UldPlatform.sChannelSubId, UldPlatform.mobileDeviceId,
						UldPlatform.statisticAnalysisId, UldPlatform.sDeviceName });

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
					} else {
						loginResult.setIsLogin(false);
						loginResult.setChannelUserId("");
						loginResult.setLoginErrorMsg("登录失败");
					}
				}
				loginCallBackListener.onLoginFinished(loginResult);
			}
		}.start();
	}

	// 判断是否已提交订单
	public static boolean isSubmitOrder = false;

	public void paySdk(final Context context, final GameInfo gameInfo, final RechargeCallBackListener rechargeCallBackListener) {
		isSubmitOrder = false;

		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setAllowContinuousPay(false);
		// paymentInfo.setServerId(gameInfo.getServerId());
		paymentInfo.setRoleId(gameInfo.getPlayerId());
		paymentInfo.setRoleName(gameInfo.getRoleName());
		paymentInfo.setGrade(gameInfo.getGrade());
		paymentInfo.setCustomInfo("gameId=" + gameInfo.getGameId() + "#serverId=" + gameInfo.getServerId());
		Log.d("ULDSDK", "paymentInfo.customInfo" + paymentInfo.getCustomInfo());

		try {
			UCGameSDK.defaultSDK().pay(context, paymentInfo, new UCCallbackListener<OrderInfo>() {
				@Override
				public void callback(int code, OrderInfo orderInfo) {
					RechargeResult rechargeResult = new RechargeResult();
					Log.d("uldrecharge-uc", "ucorder");
					Log.d("uldrecharge-uccode", String.valueOf(code));

					if (isSubmitOrder) {
						// rechargeResult.setOrderType(0);
						// rechargeResult.setOrderMsg("充值成功");
					} else {
						switch (code) {
						case UCGameSDKStatusCode.NO_INIT:
							// 未初始化，需要进行初始化
							rechargeResult.setOrderId("");
							rechargeResult.setOrderMsg("尚未充值");
							rechargeResult.setOrderType(3);
							rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
							break;
						case UCGameSDKStatusCode.SUCCESS:
							// 订单提交成功
							if (orderInfo != null) {
								isSubmitOrder = true;

								String orderId = orderInfo.getOrderId();
								rechargeResult.setOrderId("");
								rechargeResult.setOrderType(1);
								rechargeResult.setOrderMsg("订单提交成功");
								Log.d("uldrecharge-ucresult s", rechargeResult.getOrderMsg());
								float amount = orderInfo.getOrderAmount();
								int payWay = orderInfo.getPayWay();
								String payWayName = orderInfo.getPayWayName();
								checkOrder(UldPlatform.channelUserId, gameInfo, orderId, amount, payWay, payWayName,
										rechargeCallBackListener);
							} else {
								rechargeResult.setOrderType(2);
								rechargeResult.setOrderMsg("充值失败");
								Log.d("uldrecharge-ucresult f", rechargeResult.getOrderMsg());
								rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
							}
							break;
						case UCGameSDKStatusCode.PAY_USER_EXIT:
							// 用户退出充值界面
							rechargeResult.setOrderId("");
							rechargeResult.setOrderMsg("尚未充值");
							rechargeResult.setOrderType(3);
							rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
							break;
						default:
							rechargeResult.setOrderId("");
							rechargeResult.setOrderMsg("尚未充值");
							rechargeResult.setOrderType(3);
							rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
							break;
						}
					}
				}
			});
		} catch (UCCallbackListenerNullException e) {
			RechargeResult rechargeResult = new RechargeResult();
			rechargeResult.setOrderId("");
			rechargeResult.setOrderMsg("");
			rechargeResult.setOrderType(3);
			rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			// e.printStackTrace();

		}
		Log.d("ULDSDK", "paySdk end");
	}

	private void checkOrder(String channelUserId, GameInfo gameInfo, String orderId, float amount, int payWay, String payWayName,
			RechargeCallBackListener rechargeCallBackListener) {
		// 去服务器创建相应订单提交成功，此时不需要告知游戏方
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.init();

		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserUC", "checkOrder", new Class<?>[] { String.class, String.class,
				int.class, int.class, int.class, int.class, String.class, float.class, int.class, String.class }, new Object[] {
				gameInfo.getPlayerId(), channelUserId, UldPlatform.sChannelId, UldPlatform.sChannelSubId, gameInfo.getGameId(),
				gameInfo.getServerId(), orderId, amount, payWay, payWayName });
		MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);

		// 通知游戏客户端订单提交成功
		RechargeResult rechargeResult = new RechargeResult();
		// 需要返回给游戏方：订单编号、订单金额、订单状态
		rechargeResult.setOrderType(1);
		rechargeResult.setOrderId((String) messageReturn.getRetObject());
		rechargeResult.setOrderMsg("订单提交成功");
		rechargeResult.setPayAccount(amount);
		rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
	}

	public void enterUc(Context context, final GotoPlatCallBackListener gotoPlatCallBackListener) {
		try {
			UCGameSDK.defaultSDK().enterUserCenter(context, new UCCallbackListener<String>() {

				@Override
				public void callback(int code, String msg) {
					switch (code) {
					case UCGameSDKStatusCode.NO_INIT:
						if (gotoPlatCallBackListener != null) {
							gotoPlatCallBackListener.onFinished("-1", "没有初始化");
						}
						// 没有初始化
						break;
					case UCGameSDKStatusCode.NO_LOGIN:
						if (gotoPlatCallBackListener != null) {
							gotoPlatCallBackListener.onFinished("-1", "尚未登录");
						}
						break;
					case UCGameSDKStatusCode.SUCCESS:
						if (gotoPlatCallBackListener != null) {
							gotoPlatCallBackListener.onFinished("0", "成功");
						}
						break;
					default:
						break;
					}
				}
			});
		} catch (UCCallbackListenerNullException e) {

		}
	}

	// 注销登录
	public void logoutSDK(LogoutCallBackListener logoutCallBackListener) {
		LogoutResult logoutResult = new LogoutResult();
		logoutResult.setIsLogout(true);
		try {
			UCGameSDK.defaultSDK().logout();
		} catch (UCCallbackListenerNullException e) {
			logoutResult.setIsLogout(false);
			logoutResult.setLogoutErrorMsg(e.getMessage());
		}
		logoutCallBackListener.onLogoutFinished(logoutResult);
	}

	// 退出sdk
	public void exit() {
		//uc新版中替换了登出接口,建议游戏方直接调用,调用方法见Demo
	}

	public void exit(Activity activity) {
	}

	// 用户中心
	public void gotoPlatform(Activity sContext) {
		try {
			UCGameSDK.defaultSDK().enterUserCenter(sContext, new UCCallbackListener<String>() {

				@Override
				public void callback(int arg0, String arg1) {

				}
			});
		} catch (UCCallbackListenerNullException e) {
		}
	}
}
