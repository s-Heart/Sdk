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
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import uld.sdk.unite.UldPlatform;
import wh.order.model.OrderEnum;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.xiaomi.gamecenter.sdk.GameInfoField;
import com.xiaomi.gamecenter.sdk.MiCommplatform;
import com.xiaomi.gamecenter.sdk.MiErrorCode;
//import com.xiaomi.gamecenter.sdk.MiGameSDKApplication;
import com.xiaomi.gamecenter.sdk.OnLoginProcessListener;
import com.xiaomi.gamecenter.sdk.OnPayProcessListener;
import com.xiaomi.gamecenter.sdk.entry.MiAccountInfo;
import com.xiaomi.gamecenter.sdk.entry.MiAppInfo;
import com.xiaomi.gamecenter.sdk.entry.MiBuyInfoOnline;
import com.xiaomi.gamecenter.sdk.entry.MiGameType;
import com.xiaomi.gamecenter.sdk.entry.PayMode;
import com.xiaomi.gamecenter.sdk.entry.ScreenOrientation;

/**
 * @version XiaoMiSdk3.3.5
 * @time 2014-5-14 15:25:57
 */
public class SdkXiaoMi extends android.app.Application {
	public static String APPID = "21051";
	public static String APPKEY = "30bef3ea-5548-457e-fb5a-52771c47dc07";

	private static SdkXiaoMi instance = null;

	public static SdkXiaoMi getInstance() {
		if (instance == null) {
			instance = new SdkXiaoMi();
		}
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();

//		CHUHAN_APPID = uld.sdk.tools.Utility.getInt(getMetaData(UldPlatform.sContext, "uldAppId"));
//		CHUHAN_APPKEY = getMetaData(UldPlatform.sContext, "uldAppKey");
		Log.d("uldAppId-xiaomi", String.valueOf(APPID));
		Log.d("uldAppKey-xiaomi", APPKEY);

		MiAppInfo appInfo = new MiAppInfo();
		appInfo.setAppId(APPID);
		appInfo.setAppKey(APPKEY);
		appInfo.setAppType(MiGameType.online);
		// 设置为大众支付方式（即米币余额不足时，先提示用户充值米币）
		appInfo.setPayMode(PayMode.custom);
		appInfo.setOrientation(ScreenOrientation.horizontal);
		MiCommplatform.Init(this, appInfo);
	}

	public void loginSDK(Activity context, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {

		MiCommplatform.getInstance().miLogin(context, new OnLoginProcessListener() {
			@Override
			public void finishLoginProcess(int code, MiAccountInfo maInfo) {

				LoginResult loginResult = new LoginResult();
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("登录失败");

				// Log.d("XiaomiSDk", "finishLoginProcess code:" +
				// code);
				switch (code) {
				case MiErrorCode.MI_XIAOMI_GAMECENTER_SUCCESS:
					// 登陆成功
					// 获取用户的登陆后的 UID （即用户唯一标识）
					String uid = String.valueOf(maInfo.getUid());
					// 获取用户的登陆的 Session（请参考 4.2.3.3 流程校验 Session 有效性）
					String session = maInfo.getSessionId(); // 若没有登录返回
															// null
					// 请开发者完成将 uid 和 session 提交给开发者自己服务器进行 session 验证
					// Log.d("XiaomiSDK", "uid = " + uid + ",Session = "
					// + session);

					if (!Utility.isEmpty(session)) {
						MessageHeader messageHeader = new MessageHeader();
						messageHeader.init();

						MessageBody messageBody = new MessageBody("uld.sdk.bll.UserXiaoMi", "login", new Class<?>[] { int.class, int.class,
								int.class, int.class, int.class, int.class, String.class, String.class, String.class, String.class,String.class },
								new Object[] { gameInfo.getGameId(), gameInfo.getServerId(), UldPlatform.mobileDeviceId,
										UldPlatform.statisticAnalysisId, UldPlatform.sChannelId, UldPlatform.sChannelSubId, session, uid,
										String.valueOf(APPID), APPKEY ,UldPlatform.sDeviceName});
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
					} else {
						loginResult.setIsLogin(false);
						loginResult.setChannelUserId("");
						loginResult.setLoginErrorMsg("登陆失败，请检查SD卡是否可写，如手机连接电脑请断开。");
					}
					break;
				case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_LOGIN_FAIL:
					// 登陆失败
					loginResult.setLoginErrorMsg("用户取消了登录");
					break;
				case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_CANCEL:
					// 取消登录
					loginResult.setLoginErrorMsg("取消登录");
					break;
				default:
					// 登录失败
					loginResult.setLoginErrorMsg("default");
					break;
				}

				loginCallBackListener.onLoginFinished(loginResult);
			}
		});
	}


	private Dialog alertDialog;

	public void recharge(final Activity context, final RechargeCallBackListener rechargeCallBackListener) {

		final int checkedItem = 0;
		final String[] payValueAry = new String[] { "10", "20", "30", "50", "100", "200", "300", "500", "1000", "2000" };

		alertDialog = new AlertDialog.Builder(context).setTitle("单位:米币(1小米币相当于1元钱)")
				.setSingleChoiceItems(payValueAry, checkedItem, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (alertDialog != null) {
							alertDialog.dismiss();
						}
						paySDKMethod(context, rechargeCallBackListener, Integer.valueOf(payValueAry[which]));
					}
				}).setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (alertDialog != null) {
							alertDialog.dismiss();
						}
						paySDKMethod(context, rechargeCallBackListener, Integer.valueOf(payValueAry[checkedItem]));
					}
				}).create();
		alertDialog.show();
	}

	public void paySDKMethod(final Activity context, final RechargeCallBackListener rechargeCallBackListener, final int payAccount) {
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
		order.setPayAccount(BigDecimal.valueOf(payAccount));
		order.setRealPayAccount(BigDecimal.valueOf(payAccount));

		// 生成订单
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.init();
		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserXiaoMi", "createOrder", new Class<?>[] { wh.order.model.Order.class,
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

		MiBuyInfoOnline online = new MiBuyInfoOnline();
		online.setCpOrderId(String.valueOf(order.getOrderId())); // 订单号唯一（不为空）
		online.setCpUserInfo("GameId=" + UldPlatform.gameInfo.getGameId() + ";ServerId=" + UldPlatform.gameInfo.getServerId()); // 此参数在用户支付成功后会透传给
		online.setMiBi(payAccount); // 必须是大于 1 的整数，10 代表 10 米币，即 10 元人民币（不为空）

		Bundle mBundle = new Bundle();
		mBundle.putString(GameInfoField.GAME_USER_BALANCE, ""); // 用户余额
		mBundle.putString(GameInfoField.GAME_USER_GAMER_VIP, ""); // vip等级
		mBundle.putString(GameInfoField.GAME_USER_LV, ""); // 角色等级
		mBundle.putString(GameInfoField.GAME_USER_PARTY_NAME, ""); // 工会，帮派
		mBundle.putString(GameInfoField.GAME_USER_ROLE_NAME, ""); // 角色名称
		mBundle.putString(GameInfoField.GAME_USER_ROLEID, ""); // 角色id
		mBundle.putString(GameInfoField.GAME_USER_SERVER_NAME, ""); // 所在服务器

		MiCommplatform.getInstance().miUniPayOnline(context, online, mBundle, new OnPayProcessListener() {
			@Override
			public void finishPayProcess(int code) {

				RechargeResult rechargeResult = new RechargeResult();
				rechargeResult.setOrderId(String.valueOf(order.getOrderId()));
				rechargeResult.setOrderType(2);
				rechargeResult.setPayAccount(payAccount);

				switch (code) {
				case MiErrorCode.MI_XIAOMI_GAMECENTER_SUCCESS:
					// 购买成功
					rechargeResult.setOrderId(String.valueOf(order.getOrderId()));
					rechargeResult.setOrderType(1);
					rechargeResult.setOrderMsg("充值已提交");
					break;
				case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_PAY_CANCEL:
					// 取消购买
					rechargeResult.setOrderMsg("取消购买");
					rechargeResult.setOrderType(3);
					rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
					break;
				case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_PAY_FAILURE:
					// 购买失败
					rechargeResult.setOrderMsg("购买失败");
					break;
				case MiErrorCode.MI_XIAOMI_GAMECENTER_ERROR_LOGIN_FAIL:
					MiCommplatform.getInstance().miLogin(context, new OnLoginProcessListener() {

						@Override
						public void finishLoginProcess(int arg0, MiAccountInfo arg1) {
							Log.d("SDK_XiaoMi", "二次登陆,处理首次登录时无法充值的问题");
						}
					});
					rechargeResult.setOrderMsg("请重试。");
					break;
				default:
					// 购买失败
					rechargeResult.setOrderMsg("购买失败");
					break;
				}
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
		});
	}

	private ProgressDialog progressDialog;

	protected void showLoading() {
		progressDialog = new ProgressDialog(UldPlatform.sContext);
		progressDialog.setMessage("正在加载...");
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	protected void showLoading(String tips) {
		progressDialog = new ProgressDialog(UldPlatform.sContext);
		progressDialog.setMessage(tips);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	protected void hideLoading() {
		if (progressDialog != null) {
			progressDialog.cancel();
			progressDialog = null;
		}
	}

	public String getMetaData(Context context, String metaName) {
		String str = "";

		ApplicationInfo info;
		try {
			info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
			str = info.metaData.getString(metaName);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;
	}
}
