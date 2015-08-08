package uld.sdk.unite;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import org.apache.http.util.EncodingUtils;

import com.qihoo.gamecenter.sdk.protocols.pay.ProtocolConfigs;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.others.Sdk360;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import wh.member.model.User;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.WindowManager;

public class UldPlatform extends UldBase {

	private static UldPlatform sInstance = null;
	public static User sUser = null;
	public static String channelUserId = "";

	// 游戏面
	public static Activity sContext = null;
	public static GameInfo gameInfo = null;
	@SuppressWarnings("unused")
	private Boolean isUpdate = true;

	public static int sChannelId = 0;
	public static int sChannelSubId = 0;
	public static String sChannelSubName = "";

	// 游老大统计需要
	@SuppressWarnings("unused")
	private static boolean isStat = false;
	@SuppressWarnings("unused")
	private String uldPid = null;
	@SuppressWarnings("unused")
	private String uldKey = null;
	private static String OS = "Android";
	private static String OSModel = android.os.Build.VERSION.RELEASE;
	private static String deviceBrand = android.os.Build.BRAND;
	private static String deviceProduct = android.os.Build.PRODUCT;
	public static int mobileDeviceId;
	public static int statisticAnalysisId;

	// 基础信息
	public static String sDeviceName = "";
	private static String sMobilePhone = "";

	private final static int ChannelSubId91 = 22;
	private final static int ChannelSubIdUld = 13;
	private final static int ChannelSubIdUc = 20;
	// private final static int ChannelSubIdJiFeng = 36;
	// private final static int ChannelSubIdBaoruan = 47;
	// private final static int ChannelSubIdJiYou = 42;
	// private final static int ChannelSubIdXiaoMi = 52;
	// private final static int ChannelSubIdSiKai = 53;
	// private final static int ChannelSubIdDianJin = 54;
	// private final static int ChannelSubIdShouYou = 70;
	// private final static int ChannelSubIdThreeG = 41;
	// private final static int ChannelSubId360 = 1000000;
	private final static int ChannelSubId360 = 25;

	// private final static int ChannelSubIdAnzhi = 77;
	// private final static int ChannelSubIdBaoDuDuoKu = 29;
	// private final static int ChannelSubIdWanDouJia = 93;

	private UldPlatform() {
		sChannelSubName = "";
	}

	public static UldPlatform getInstance() {
		if (sInstance == null) {
			sInstance = new UldPlatform();
		}
		return sInstance;
	}

	@SuppressWarnings("static-access")
	public void beforeInitGame(Activity context, uld.sdk.unite.GameInfo gameInfo, final OnInitFinishListener onInitFinishListener) {
		sInstance.sContext = context;
		sInstance.gameInfo = gameInfo;
		// 获取渠道
		sChannelSubName = "";
		String channelData = getDataFromAsset("Channel.txt").trim();
		if (!Utility.isEmpty(channelData)) {
			String[] channelDatas = channelData.split("_");
			if (channelDatas.length >= 2) {
				String str = new String(channelDatas[0]);
				sChannelId = Utility.getInt(str);
				sChannelSubId = Utility.getInt(channelDatas[1]);
				// sChannelSubName = channelDatas[2];
				sChannelSubName = "";
			}
		}

		Log.d("uldsdk", "beforeInitGame- sChannelSubId" + sChannelSubId);
		// if (sChannelSubId == ChannelSubId91) {
		// Sdk91.getInstance().init91SDK325(context, onInitFinishListener);
		// }
	}

	// 初始化sdk
	@SuppressWarnings("static-access")
	public InitResult init(Activity context, uld.sdk.unite.GameInfo gameInfo) {

		InitResult initResult = new InitResult();

		sInstance.sContext = context;
		sInstance.gameInfo = gameInfo;

		// 获取渠道
		sChannelSubName = "";
		String channelData = getDataFromAsset("Channel.txt").trim();
		if (!Utility.isEmpty(channelData)) {
			String[] channelDatas = channelData.split("_");
			if (channelDatas.length >= 2) {
				String str = new String(channelDatas[0]);
				sChannelId = Utility.getInt(str);
				sChannelSubId = Utility.getInt(channelDatas[1]);
				// sChannelSubName = channelDatas[2];
				sChannelSubName = "";
			}
		}

		basicInfoInit();

		handoutInit();

		initResult.setChannelId(sChannelId);
		initResult.setChannelName(sChannelSubName);
		return initResult;
	}

	/*
	 * 检查更新
	 */
	@SuppressWarnings("unused")
	private void update(Boolean isUpdate) {
		sInstance.isUpdate = isUpdate;
	}

	public static boolean isLogin = false;

	/*
	 * 登录
	 */
	@SuppressWarnings("static-access")
	public void login(Activity context, final LoginCallBackListener loginCallBackListener) {
		sInstance.sContext = context;
		isLogin = false;

		handoutLogin(new LoginCallBackListener() {
			@Override
			public void onLoginFinished(LoginResult loginResult) {
				if (!isLogin) {
					isLogin = true;

					// 给游戏方发我们平台的子渠道
					loginResult.setChannelId(sChannelSubId);
					UldPlatform.channelUserId = loginResult.getChannelUserId();
					// loginResult.setChannelName(sChannelSubName);
					loginResult.setChannelName("");

					if (loginResult.isLogin) {

						// String tempString = gameInfo.getUldPid() + "&" +
						// loginResult.getChannelUserId() + "&"
						// + loginResult.getChannelId() + "&" +
						// gameInfo.getUldLoginKey();

						// Log.d("test", "uld raw sign:" + tempString);
						// Log.d("test", "uld sign:" +
						// Utility.encodeMD5(tempString));

						loginResult.setSign(Utility.encodeMD5(gameInfo.getUldPid() + "&" + loginResult.getChannelUserId() + "&"
								+ loginResult.getChannelId() + "&" + gameInfo.getUldLoginKey()));
					}

					loginCallBackListener.onLoginFinished(loginResult);
				}
			}
		});
	}

	/**
	 * 从asset中获取文件并读取数据
	 * 
	 * @param fileName
	 * @return
	 */
	protected String getDataFromAsset(String fileName) {
		String result = "";
		try {
			InputStream in = sContext.getResources().getAssets().open(fileName); // 从Assets中的文件获取输入流
			int length = in.available(); // 获取文件的字节数
			byte[] buffer = new byte[length]; // 创建byte数组
			in.read(buffer); // 将文件中的数据读取到byte数组中
			result = EncodingUtils.getString(buffer, "utf-8"); // 将byte数组转换成指定格式的字符串
			in.close();
		} catch (Exception e) {
			// log(e);
		}
		return result;
	}

	/*
	 * 获取基础信息，设备编号，手机号，记录统计信息，设备编号等处理
	 */
	@SuppressWarnings("static-access")
	private void basicInfoInit() {
		// 如果IMEI没有则使用System.ANDROID_ID,如果还没有则使用MAC
		TelephonyManager telephonyManager = (TelephonyManager) sContext.getSystemService(sContext.TELEPHONY_SERVICE);
		sDeviceName = telephonyManager.getDeviceId();
		if (Utility.isEmpty(sDeviceName) || sDeviceName.equals("000000000000000")) {
			sDeviceName = System.getString(sContext.getContentResolver(), System.ANDROID_ID);
		}

		if (Utility.isEmpty(sDeviceName) || sDeviceName.equals("000000000000000")) {
			WifiManager wifiManager = (WifiManager) sContext.getSystemService(Context.WIFI_SERVICE);
			if (wifiManager != null) {
				WifiInfo wifiInfo = wifiManager.getConnectionInfo();
				if (wifiInfo != null && !uld.sdk.tools.Utility.isEmpty(wifiInfo.getMacAddress())) {
					sDeviceName = wifiInfo.getMacAddress().replaceAll(":", "");
				}
			}
		}

		sMobilePhone = telephonyManager.getLine1Number();

		new Thread() {
			@Override
			public void run() {
				super.run();
				stat();
			}
		}.start();
	}

	@SuppressWarnings("static-access")
	private void stat() {
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.init();
		wh.promotion.model.MobileDevice mobileDevice = new wh.promotion.model.MobileDevice();
		// 如果IMEI没有则使用System.ANDROID_ID,如果还没有则使用MAC
		mobileDevice.setMobileDeviceName(sDeviceName);
		mobileDevice.setOS(OS);
		mobileDevice.setOSModel(OSModel);
		mobileDevice.setDeviceBrand(deviceBrand);
		mobileDevice.setDeviceProduct(deviceProduct);
		mobileDevice.setMobilePhone(sMobilePhone);
		// Remark字段：分辨率+ip地址：
		WindowManager windowManager = (WindowManager) sContext.getSystemService(sContext.WINDOW_SERVICE);
		String remark = windowManager.getDefaultDisplay().getWidth() + "_" + windowManager.getDefaultDisplay().getHeight();
		remark += "|*|" + getLocalIPAddress();
		mobileDevice.setRemark(remark);

		MessageBody messageBody = new MessageBody("uld.sdk.bll.StatisticAnalysis", "Stat", new Class<?>[] { int.class, int.class,
				int.class, wh.promotion.model.MobileDevice.class },
				new Object[] { gameInfo.gameId, sChannelId, sChannelSubId, mobileDevice });
		MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
		uld.sdk.model.StatisticAnalysis sAnalysis = null;
		if (messageReturn.getRetObject() != null) {
			sAnalysis = (uld.sdk.model.StatisticAnalysis) messageReturn.getRetObject();
		}
		if (sAnalysis != null) {
			mobileDeviceId = sAnalysis.getMobileDeviceId();
			statisticAnalysisId = sAnalysis.getStatisticAnalysisId();
		}
		if (mobileDeviceId > 0) {
			isStat = true;
		}
	}

	private String getLocalIPAddress() {
		try {
			for (Enumeration<NetworkInterface> mEnumeration = NetworkInterface.getNetworkInterfaces(); mEnumeration.hasMoreElements();) {
				NetworkInterface intf = mEnumeration.nextElement();
				for (Enumeration<InetAddress> enumIPAddr = intf.getInetAddresses(); enumIPAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIPAddr.nextElement();
					// 如果不是回环地址
					if (!inetAddress.isLoopbackAddress()) {
						// 直接返回本地IP地址
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (Exception ex) {
			// Log.e("Error", ex.toString());
		}
		return null;
	}

	// 充值
	public void recharge(GameInfo gameInfo, Activity context, final RechargeCallBackListener rechargeCallBackListener) {

		sContext = context;
		UldPlatform.gameInfo = gameInfo;

		handoutRecharge(new RechargeCallBackListener() {
			@Override
			public void onRechargeUiFinished(RechargeResult rechargeResult) {
				// 设置渠道id
				rechargeResult.setChannelId(sChannelId);
				rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
		});

		// if (sChannelSubId == ChannelSubIdUld) {
		// SdkUld.getInstance().recharge(context, rechargeCallBackListener);
		// return;
		// }
		//
		// showLoading();
		//
		// wh.order.model.Order order = new wh.order.model.Order();
		// order.setGameId(gameInfo.gameId);
		// order.setServerId(gameInfo.serverId);
		// order.setUserId(sUser.getUserId());
		// order.setChargedUserId(sUser.getUserId());
		// order.setChargedUserName(sUser.getUserName());
		// order.setPaySourceType(OrderEnum.PaySourceType.Android客户端.getValue());
		// order.setOrderType(OrderEnum.OrderType.已提交.getValue());
		// order.setAccountType(OrderEnum.AccountType.D币.getValue());
		// order.setChargeType(OrderEnum.ChargeType.充值游戏.getValue());
		// order.setCreateDate(new Date());
		// order.setModifyDate(new Date());
		// // 75为其他渠道充值
		// order.setPayType((byte) 75);
		// order.setStatus((byte) 1);
		//
		// // 生成订单
		// MessageHeader messageHeader = new MessageHeader();
		// messageHeader.init();
		// MessageBody messageBody = new MessageBody("wh.order.bll.Order",
		// "createUpdate",
		// new Class<?>[] { wh.order.model.Order.class }, new Object[] { order
		// });
		// MessageReturn messageReturn =
		// ThreadManager.sendMessage(messageHeader, messageBody);
		//
		// hideLoading();
		// if (!messageReturn.findErr()) {
		// if (messageReturn.getRetObject() != null) {
		// order.setOrderId(Utility.getInt(messageReturn.getRetObject()));
		// }
		// } else {
		// showToast(messageReturn.getErrMsg(), Toast.LENGTH_LONG);
		// }
		//
		// if (order.getOrderId() > 0) {
		// handoutRecharge(order, "楚汉争霸元宝充值", rechargeCallBackListener);
		// } else {
		// RechargeResult rechargeResult = new RechargeResult();
		// rechargeResult.setOrderId("");
		// rechargeResult.setOrderType(3);
		// rechargeResult.setOrderMsg("尚未充值");
		//
		// rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
		// }
	}

	// 进入渠道平台
	public void gotoPlatform(Activity context) {
		sContext = context;
		if (sChannelSubId == ChannelSubId360) {
		}
	}

	public void gotoPlatform(Context context, GotoPlatCallBackListener gotoPlatCallBackListener) {
		if (sChannelSubId == ChannelSubId91) {

		} else if (sChannelSubId == ChannelSubIdUld) {

		} else if (sChannelSubId == ChannelSubIdUc) {

		}
	}

	// 渠道分发初始化
	private void handoutInit() {
		if (sChannelSubId == ChannelSubId360) {
			Sdk360.getInstance().initSDK(sContext);
		}
	}

	// 渠道分发登录
	private void handoutLogin(LoginCallBackListener loginCallBackListener) {
		if (sChannelSubId == ChannelSubId360) {
			Sdk360.getInstance().loginSDK(sContext, gameInfo, loginCallBackListener);
		}
	}

	// 渠道分发充值
	private void handoutRecharge(RechargeCallBackListener rechargeCallBackListener) {
		if (sChannelSubId == ChannelSubId360) {
			Sdk360.getInstance().recharge(sContext, gameInfo, rechargeCallBackListener);
		}
	}

	// 退出游戏
	public void gotofinish(Activity context) {
		sContext = context;
		if (sChannelSubId == ChannelSubId360) {
			Sdk360.getInstance().finishGame(context);
		}
	}

	// 浮动窗
	public void gotoFloatingBar(Activity context) {
		sContext = context;
		if (sChannelSubId == ChannelSubId360) {
			Sdk360.getInstance().setFloatingBar(context);
		}
	}

	// 浮动窗销毁
	public void gotoFinishFloatingBar(Activity context) {
		sContext = context;
		if (sChannelSubId == ChannelSubId360) {
			Sdk360.getInstance().setFinishFloatingBar(context);
		}
	}

	// 防沉迷
	public void gotoAntiAddiction(Activity context, AntiAddictedCallBackListener antiaddictedCallBackListener) {
		sContext = context;
		if (sChannelSubId == ChannelSubId360) {
			Sdk360.getInstance().getAntiAddictionIntent(context, antiaddictedCallBackListener);
		}
	}

	// 实名注册
	public void gotoRealNameRegister(Activity context) {
		sContext = context;
		if (sChannelSubId == ChannelSubId360) {
			Sdk360.getInstance().doSdkRealNameRegister(context);
		}
	}
}
