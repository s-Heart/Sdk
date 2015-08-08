package uld.sdk.others;

import java.util.Date;
import java.util.Map;

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
import uld.sdk.unite.SwitcheCallBackListener;
import uld.sdk.unite.SwitcheResult;
import uld.sdk.unite.UldBase;
import uld.sdk.unite.UldPlatform;
import wh.order.model.OrderEnum;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.anzhi.usercenter.sdk.AnzhiUserCenter;
import com.anzhi.usercenter.sdk.inter.AnzhiCallback;
import com.anzhi.usercenter.sdk.inter.KeybackCall;
import com.anzhi.usercenter.sdk.item.CPInfo;

/**
 * sdk版本：3.1 更新时间：2014-4-4 15:53:05 
 * @author Tony
 *
 */
public class SdkAnZhi extends UldBase {

	private String appChannel = "AnZhi";
	private Context sContext;

	private final String APPKEY = "1386150400BdWm3q07qrvn220Vpoq5";
	private final String APPSECRET = "Xty2inIf62ej8Vx7GLsHGfww";

	private static int mOrderId = 0;
	private static LoginCallBackListener mLoginCallBackListener=null;
	private static RechargeCallBackListener mRechargeCallBackListener = null;
	private static SwitcheCallBackListener mSwitcheCallBackListener = null;
	private static AnzhiCallback mCallback = null;
	private static KeybackCall mKeybackCall=null;
	private static AnzhiCallback mLoginoutCallback = null;
	
	private String desc = "";
	private String uid = "";
	private String login_name = "";
	private String sid = "";

	private int mGameId = 0;
	private int mServerId = 0;
	private int mMobileDeviceId = 0;
	private int mStatisticAnalysisId = 0;
	private int mChannelId = 0;
	private int mChannelSubId = 0;
	private Activity ac = null;

	private LoginResult loginResult = null;
	private RechargeResult rechargeResult=null;
	

	private static boolean isLogin = false;
	private static SdkAnZhi sInstance=null;
	
	private SdkAnZhi(){
		
	}
	
	public static SdkAnZhi getInstance() {
		if (sInstance == null) {
			sInstance = new SdkAnZhi();
		}
		return sInstance;
	}

	/**
	 * 初始化SDK
	 * 
	 * @param context
	 *            上下文
	 */
	public void initSDK(Context context) {

		final CPInfo info = new CPInfo();
		info.setAppKey(APPKEY);
		info.setSecret(APPSECRET);
		// info.setOpenOfficialLogin(true); // 是否开启游戏官方账号登录，默认为关闭
		info.setChannel(appChannel);
		info.setGameName("傲剑");
		AnzhiUserCenter.getInstance().setCPInfo(info);
		
	}

	public void setSwitcheCallBack(SwitcheCallBackListener switcheCallBackListener) {
		mSwitcheCallBackListener = switcheCallBackListener;
	}

	public void login(final Context context, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {
		mLoginCallBackListener=loginCallBackListener;
		sContext = context;
		mGameId = gameInfo.getGameId();
		mServerId = gameInfo.getServerId();

		AnzhiUserCenter.getInstance().login(context,false);

		mCallback = new AnzhiCallback() {

			@Override
			public void onCallback(CPInfo cpInfo, final String result) {
				loginResult = new LoginResult();
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("登录失败");

				JSONObject json = null;
				try {
					json = new JSONObject(result);
				} catch (Exception e) {
					Log.d("anzhi", "jonson fail, msg:" + e.getMessage());
				}

				if (json == null) {
					loginResult.setLoginErrorMsg("json 对象获取失败");
					mLoginCallBackListener.onLoginFinished(loginResult);
					return;
				}

				String key = json.optString("callback_key");
				System.out.println(result);
				if ("key_login".equals(key)) {

					int code = json.optInt("code");
					desc = json.optString("code_desc");
					uid = json.optString("uid");
					login_name = json.optString("login_name");
					sid = json.optString("sid");
					if (code == 200) {
						MessageHeader messageHeader = new MessageHeader();
						messageHeader.init();

						MessageBody messageBody = new MessageBody("uld.sdk.bll.UserAnZhi", "login", new Class<?>[] { int.class, int.class,
								int.class, int.class, int.class, int.class, String.class, String.class, String.class,String.class},
								new Object[] { mGameId, mServerId, UldPlatform.mobileDeviceId, UldPlatform.statisticAnalysisId, UldPlatform.sChannelId, UldPlatform.sChannelSubId, sid,
										uid, login_name ,UldPlatform.sDeviceName});
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
						loginResult.setLoginErrorMsg("登录失败");
					}
					mLoginCallBackListener.onLoginFinished(loginResult);
				} else if ("key_pay".equals(key)) {
					// result 内容：
					// {”callback_key”:”key_pay”,
					// ”ver”:”1.0”,
					// ”order_id”:”订单号”,
					// ”price”:”金额(元)”,
					// ”code”:状态码(200 支付成功，201 等待支付完成，其它值为失败),
					// ”code_desc”:状态描述信息,
					// ”time”:”时间”,
					// “pay_type”:”支付方式 ID,10 为安智币,11 为支付宝,12 为银联,13 为财付通,14
					// 为充值卡”}
					RechargeResult rechargeResult = new RechargeResult();
					String order_id = json.optString("order_id");
					String pay_type = json.optString("pay_type");
					String price = json.optString("price");
					int code = json.optInt("code");
					if (code == 200) {
						rechargeResult.setOrderId(order_id);
						rechargeResult.setOrderMsg("充值成功");
					} else if (code == 201) {
						rechargeResult.setOrderId(order_id);
						rechargeResult.setOrderMsg("等待支付完成");
					} else {
						rechargeResult.setOrderId(order_id);
						rechargeResult.setOrderMsg("支付失败");
					}
					mRechargeCallBackListener.onRechargeUiFinished(rechargeResult);
				} else if ("key_logout".equals(key)) {
					// result 内容：
					// {”callback_key”:”key_logout”,”ver”:”1.0”}（在改回调必须将现有游戏推出可以进入登录界面或者
					// 回到桌面、或者游戏的登录界面）
					// ac = (Activity) sContext;
					// ac.finish();
					// System.exit(0);
					SwitcheResult switchResult = new SwitcheResult();
					switchResult.setSwitcheMsg("success");
					Log.d("Logtou", "Logtou");
					mSwitcheCallBackListener.onSwitchFinished(switchResult);

				}
			}
		};
		
		mKeybackCall=new KeybackCall() {
			@Override
			public void KeybackCall(String st) {
				if(st.equals("Login")){
					loginResult = new LoginResult();
					loginResult.setIsLogin(false);
					loginResult.setLoginErrorMsg("用户取消了登录");
					mLoginCallBackListener.onLoginFinished(loginResult);	
				}else if(st.equals("gamePay")){
					rechargeResult=new RechargeResult();
					rechargeResult.setOrderId("0");
					rechargeResult.setOrderMsg("用户取消了充值");
					mRechargeCallBackListener.onRechargeUiFinished(rechargeResult);
				}
			}
		};
		AnzhiUserCenter.getInstance().setKeybackCall(mKeybackCall);
		AnzhiUserCenter.getInstance().setCallback(mCallback);
	}

	public void recharge(final Context context, final RechargeCallBackListener rechargeCallBackListener) {
		mOrderId = 0;
		sContext = context;
		mRechargeCallBackListener = rechargeCallBackListener;
		pay(mRechargeCallBackListener);
		AnzhiUserCenter.getInstance().pay(sContext, 0, 0, "傲剑充值", String.valueOf(mOrderId));

	}

	private void pay(final RechargeCallBackListener rechargeCallBackListener) {
		final wh.order.model.Order order = new wh.order.model.Order();
		order.setGameId(UldPlatform.gameInfo.getGameId());
		order.setServerId(UldPlatform.gameInfo.getServerId());

		// order.setRealPayAccount(BigDecimal.valueOf(payValue));
		// order.setPayAccount(BigDecimal.valueOf(payValue));
		// order.setChargedUserId(UldPlatform.sUser.getUserId());
		// order.setChargedUserName(UldPlatform.sChannelSubName);
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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserAnZhi", "createOrder", new Class<?>[] { wh.order.model.Order.class,
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
	}

	public void viewUser(Context context) {
		sContext = context;
		AnzhiUserCenter.getInstance().viewUserInfo(context);
	}

	public void finishGame(Context context) {
		sContext = context;
		AnzhiUserCenter.getInstance().logout(context);
		AnzhiUserCenter.getInstance().gameOver(context);
	}

	public void Cancellation(Context context) {
		sContext = context;
		mLoginoutCallback = new AnzhiCallback() {
			@Override
			public void onCallback(CPInfo cpInfo, final String result) {
				JSONObject json = null;
				try {
					json = new JSONObject(result);
				} catch (Exception e) {
					Log.d("anzhi", "jonson fail, msg:" + e.getMessage());
				}
				if (json == null) {
					Log.d("anzhi", "logout callback jonson is null !");
					return;
				}
				// {”callback_key”:”key_logout”,”ver”:”1.0”}
				String key = json.optString("callback_key");
				System.out.println(result);
				if ("key_logout".equals(key)) {
					ac = (Activity) sContext;
					ac.finish();
				}
			}
		};
		AnzhiUserCenter.getInstance().setCallback(mLoginoutCallback);
		AnzhiUserCenter.getInstance().logout(sContext);
	}
}
