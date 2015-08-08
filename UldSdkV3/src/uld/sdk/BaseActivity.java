package uld.sdk;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.http.util.EncodingUtils;

import uld.sdk.model.MessageBody;
import uld.sdk.model.MessageHeader;
import uld.sdk.model.MessageReturn;
import uld.sdk.socket.ThreadManager;
import uld.sdk.tools.Utility;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class BaseActivity extends Activity {
	private static int userId;
	private static int mobileDeviceId;
	private static int statisticAnalysisId;
	private static String deviceId = "";
	private static int gameId;
	private static int serverId;
	// private static int sequenceNumber;
	private static String playerId = "";
	private static int channelId;
	private static int channelSubId;
	private static String channelSubName;
	private static int rechargeRate = 10;//FIXME 不同游戏注意此处的充值比率
	private static int currentOrderId;
	// 1:充值已提交、2:充值失败、0充值成功
	private static int currentOrderType = 2;
	public static final int REQUESTCODE_RECHARGE = 0;
	public static final int REQUESTCODE_LOGIN = 1;
	public static final int REQUESTCODE_QUERYINFO = 2;
	public static final int REQUESTCODE_REGISTER = 3;
	private static String rechargeUnit = "金锭";//FIXME 不同游戏注意此处的充值单位
	private static String OS = "Android";
	private static String OSModel = android.os.Build.VERSION.RELEASE;
	private static String deviceBrand = android.os.Build.BRAND;
	private static String deviceProduct = android.os.Build.PRODUCT;
	private static String mobilePhone = "";
	private static String queryUserInfoKey = "";
	protected static String UserNameSplit = "|";
	protected static String UserNameSplitRegular = "\\|";
	
	//客服电话 bbs
	private static String content="";

	public static String getContent() {
		return content;
	}

	private static boolean isStat = false;
	public ProgressDialog dialog = null;
	public final static String SHAREDUSERNAME = uld.sdk.tools.SdkConfig.Aj_SHAREDUSERNAME;
	
	// 屏幕分辨率配置
	public static final int valueOfWidthDivideDensity = 600;

	/**
	 * 用户信息Activity
	 */
	//private static List<Activity> userActivities = new ArrayList<Activity>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!isStat) {
			new Thread() {
				public void run() {
					if (Utility.isEmpty(BaseActivity.deviceId)) {
						try {
							String channelData = getDataFromAsset("Channel.txt").trim();
							if (!Utility.isEmpty(channelData)) {
								String[] channelDatas = channelData.split("_");
								if (channelDatas.length >= 2) {
									channelId = Utility.getInt(channelDatas[0]);
									channelSubId = Utility.getInt(channelDatas[1]);
									channelSubName = "";
								}
							}
							// 如果IMEI没有则使用System.ANDROID_ID,如果还没有则使用MAC
							TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
							BaseActivity.deviceId = telephonyManager.getDeviceId();
							if (Utility.isEmpty(BaseActivity.deviceId) || BaseActivity.deviceId.equals("000000000000000")) {
								BaseActivity.deviceId = System.getString(getContentResolver(), System.ANDROID_ID);
							}

							if (Utility.isEmpty(BaseActivity.deviceId) || BaseActivity.deviceId.equals("000000000000000")) {
								WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
								if (wifiManager != null) {
									WifiInfo wifiInfo = wifiManager.getConnectionInfo();
									if (wifiInfo != null) {
										BaseActivity.deviceId = wifiInfo.getMacAddress().replaceAll(":", "");
									}
								}
							}

							mobilePhone = telephonyManager.getLine1Number();
						} catch (Exception e) {
						}
					}

					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();
					wh.promotion.model.MobileDevice mobileDevice = new wh.promotion.model.MobileDevice();
					// 如果IMEI没有则使用System.ANDROID_ID,如果还没有则使用MAC
					mobileDevice.setMobileDeviceName(BaseActivity.deviceId);
					mobileDevice.setOS(OS);
					mobileDevice.setOSModel(OSModel);
					mobileDevice.setDeviceBrand(deviceBrand);
					mobileDevice.setDeviceProduct(deviceProduct);
					mobileDevice.setMobilePhone(mobilePhone);
					// Remark字段：分辨率+ip地址：
					String remark = getWindowManager().getDefaultDisplay().getWidth() + "_"
							+ getWindowManager().getDefaultDisplay().getHeight();
					remark += "|*|" + getLocalIPAddress();
					mobileDevice.setRemark(remark);

					MessageBody messageBody = new MessageBody("uld.sdk.bll.StatisticAnalysis", "Stat", new Class<?>[] {
							int.class, int.class, int.class, wh.promotion.model.MobileDevice.class }, new Object[] {
							BaseActivity.gameId, BaseActivity.channelId, BaseActivity.channelSubId, mobileDevice });
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
			}.start();
		}
	}

	public static int getMobileDeviceId() {
		return mobileDeviceId;
	}

	public static void setMobileDeviceId(int mobileDeviceId) {
		BaseActivity.mobileDeviceId = mobileDeviceId;
	}

	public static int getStatisticAnalysisId() {
		return statisticAnalysisId;
	}

	public static void setStatisticAnalysisId(int statisticAnalysisId) {
		BaseActivity.statisticAnalysisId = statisticAnalysisId;
	}

	public static String getDeviceId() {
		return deviceId;
	}

	public static int getGameId() {
		return gameId;
	}

	public static void setGameId(int gameId) {
		BaseActivity.gameId = gameId;
	}

	public static int getServerId() {
		return serverId;
	}

	public static void setServerId(int serverId) {
		BaseActivity.serverId = serverId;
	}

	// public static int getSequenceNumber() {
	// return sequenceNumber;
	// }
	//
	// public static void setSequenceNumber(int sequenceNumber) {
	// BaseActivity.sequenceNumber = sequenceNumber;
	// }

	public static String getPlayerId() {
		return playerId;
	}

	public static void setPlayerId(String playerId) {
		BaseActivity.playerId = playerId;
	}

	public static int getChannelId() {
		return channelId;
	}

	public static int getChannelSubId() {
		return channelSubId;
	}

	public static String getChannelSubName() {
		return channelSubName;
	}

	public static void setChannelSubName(String channelSubName) {
		BaseActivity.channelSubName = channelSubName;
	}

	public static String getMobilePhone() {
		return mobilePhone;
	}

	public static int getRechargeRate() {
		return rechargeRate;
	}

	public static void setRechargeRate(int rechargeRate) {
		BaseActivity.rechargeRate = rechargeRate;
	}

	public static int getCurrentOrderId() {
		return currentOrderId;
	}

	public static void setCurrentOrderId(int currentOrderId) {
		BaseActivity.currentOrderId = currentOrderId;
	}

	public static int getCurrentOrderType() {
		return currentOrderType;
	}

	public static void setCurrentOrderType(int currentOrderType) {
		BaseActivity.currentOrderType = currentOrderType;
	}

	public static String getRechargeUnit() {
		return rechargeUnit;
	}

	public static void setRechargeUnit(String rechargeUnit) {
		BaseActivity.rechargeUnit = rechargeUnit;
	}

	public static void setContent(String content){
		BaseActivity.content=content;
	}
	public static int getUserId() {
		return userId;
	}

	public static void setUserId(int userId) {
		BaseActivity.userId = userId;
	}

	public static String getQueryUserInfoKey() {
		return queryUserInfoKey;
	}

	public static void setQueryUserInfoKey(String queryUserInfoKey) {
		BaseActivity.queryUserInfoKey = queryUserInfoKey;
	}

	public ProgressDialog showDialog() {
		String message = getString(R.string.uld_dialogmsg);
		ProgressDialog dialog = ProgressDialog.show(this, "", message);
		return dialog;
	}

	public ProgressDialog showDialog(String message) {
		ProgressDialog dialog = ProgressDialog.show(this, "", message);
		return dialog;
	}

	public ProgressDialog showDialog(String message, String title) {
		ProgressDialog dialog = ProgressDialog.show(this, title, message);
		return dialog;
	}

	public void CheckEditText(EditText txt, String errMsg) {
		txt.setTextColor(getResources().getColor(R.color.uld_errmsgcolor));
		Toast.makeText(txt.getContext(), errMsg, Toast.LENGTH_LONG).show();
	}

	public void CheckEditText(EditText txt, int resId) {
		txt.setTextColor(getResources().getColor(R.color.uld_errmsgcolor));
		Toast.makeText(getApplicationContext(), getString(resId), Toast.LENGTH_LONG).show();
	}

	public void showToast(CharSequence text, int duration) {
		Toast.makeText(getApplicationContext(), text, duration).show();
	}

	public void showToastThread(CharSequence text, int duration) {
		Looper.prepare();
		Toast.makeText(getApplicationContext(), text, duration).show();
		Looper.loop();
	}

	public void setSharedData(String name, String[] keys, String[] values) {
		SharedPreferences sharedPreferences = getSharedPreferences(name, 0);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		for (int i = 0; i < keys.length; i++) {
			editor.putString(keys[i], values[i]);
		}
		editor.commit();
	}

	public void setSharedUsernamesAndPwds(String name, String userName, String password) {
		
		SharedPreferences sharedPreferences = getSharedPreferences(name, 0);
		SharedPreferences.Editor editor = sharedPreferences.edit();

		String[] names = sharedPreferences.getString("UserNames", "").split(UserNameSplitRegular);
		String[] pwds = sharedPreferences.getString("Passwords", "").split(UserNameSplitRegular);

		StringBuilder namesStringBuilder = new StringBuilder();
		StringBuilder pwdsStringBuilder = new StringBuilder();
		
		namesStringBuilder.append(UserNameSplit + userName + UserNameSplit);
		pwdsStringBuilder.append(UserNameSplit + password + UserNameSplit);
		
		for (int i = 0; i < names.length; i++) {
			if (Utility.isEmpty(names[i]) || names[i].equals(userName)) {
				continue;
			}
			
			namesStringBuilder.append(names[i] + UserNameSplit);
			pwdsStringBuilder.append(pwds[i] + UserNameSplit);
		}
		
		editor.putString("UserNames", namesStringBuilder.toString());
		editor.putString("Passwords", pwdsStringBuilder.toString());
		
		editor.commit();
	}

	public SharedPreferences getSharedData(String name) {
		return getSharedPreferences(name, 0);
	}

	/**
	 * 记录客户端异常信息
	 * 
	 * @param e
	 */
	public static void log(Throwable e) {
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.init();
		MessageBody messageBody = new MessageBody("uld.sdk.bll.StatisticAnalysis", "logErr", new Class<?>[] { String.class,
				int.class, Throwable.class }, new Object[] { deviceId, mobileDeviceId, e });
		ThreadManager.sendMessageInvoke(messageHeader, messageBody);
	}

	/**
	 * 获取Intent
	 * 
	 * @param gameInterface
	 *        uld.sdk.model.GameInterface 游戏接口参数 需要 gameInterface不为null
	 * @return
	 */
	public static Intent getIntent(uld.sdk.model.GameInterface gameInterface) {
		Intent intent = new Intent(gameInterface.getIntentAction());

		Bundle bundle = new Bundle();
		for (String key : gameInterface.getStrBundleMap().keySet()) {
			bundle.putString(key, gameInterface.getStrBundleMap().get(key));
		}
		
		if (Utility.isEmpty(bundle.getString("UserId"))) {
			bundle.putString("UserId", Integer.toString(BaseActivity.getUserId()));
		}
		// 快速注册 登录返回加入UserName
		if (Utility.isEmpty(bundle.getString("UserName"))) {
			bundle.putString("UserName", gameInterface.getUserName());
		}
		
		if (Utility.isEmpty(bundle.getString("TimeSign"))) {
			bundle.putString("TimeSign", gameInterface.getStrBundleMap().get("TimeSign"));
		}

		intent.putExtras(bundle);

		return intent;
	}

	/**
	 * 从asset中获取文件并读取数据
	 * 
	 * @param fileName
	 * @return
	 */
	public String getDataFromAsset(String fileName) {
		String result = "";
		try {
			InputStream in = getResources().getAssets().open(fileName); // 从Assets中的文件获取输入流
			int length = in.available(); // 获取文件的字节数
			byte[] buffer = new byte[length]; // 创建byte数组
			in.read(buffer); // 将文件中的数据读取到byte数组中
			result = EncodingUtils.getString(buffer, "utf-8"); // 将byte数组转换成指定格式的字符串
			in.close();
		} catch (Exception e) {
			log(e);
		}
		return result;
	}

	public String getLocalIPAddress() {
		try {
			for (Enumeration<NetworkInterface> mEnumeration = NetworkInterface.getNetworkInterfaces(); mEnumeration
					.hasMoreElements();) {
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

	/**
	 * 添加用户相关Activity
	 * 
	 * @param activity
	 */
	// public static void AddUserActivity(Activity activity) {
	// if (!userActivities.contains(activity)) {
	// userActivities.add(activity);
	// }
	// }

	/**
	 * 删除所有用户相关的Activity
	 */
	// public static void removeAllUserActivities() {
	// for (Activity activity : userActivities) {
	// activity.finish();
	// }
	// userActivities.removeAll(userActivities);
	// }
}
