package uld.sdk.others;

import java.math.BigDecimal;
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
import uld.sdk.unite.GameInfo;
import uld.sdk.unite.LoginCallBackListener;
import uld.sdk.unite.LoginResult;
import uld.sdk.unite.R;
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import uld.sdk.unite.UldBase;
import uld.sdk.unite.UldPlatform;
import wh.order.model.OrderEnum;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.gionee.gsp.GnCommplatform;
import com.gionee.gsp.GnEType;
import com.gionee.gsp.GnException;
import com.gionee.gsp.base.AmigoPayer;
import com.gionee.gsp.base.AmigoPayer.MyPayCallback;
import com.gionee.gsp.service.account.sdk.listener.IGnUiListener;

/**
 * 2014年5月12日19:38:51 版本号2.0.8f
 * 
 * @author cl
 * 
 */
public class SdkJinLi extends UldBase {
	private String TAG = "SdkJinLi";
	private static final String APIKEY = "A696AE38311E43769560A4ACB9956BE2";// FIXME
																			// 请先填写参数
	public static final String RE_LOGION = "reLogion";
	public static final int LOGIN_SUCCESS = 1001;
	public static final int LOGIN_REQUEST_CODE = 1111;
	public static final String USER_ID = "u";
	public static final String PLAYER_ID = "pid";
	public static final String NAME = "na";
	public static final String ACCOUNT_STATUS = "accountStatus";
	public static final String ASSOCIATE_STRING = "as";
	public static final String TELEPHONE_NUMBER = "tn";

	private static SdkJinLi sInstance = null;
	// 从Demo中得出来的。

	protected static String sAccountLogoutNotifyAction = "com.gionee.gsp.accountLogout";

	private RechargeCallBackListener mRechargeCallBackListener = null;
	private Dialog alertDialog = null;
	private MyPayCallback myPayCallback = null;
	private AmigoPayer mAmigoPayer = null;

	private String playerId = "";

	private LoginResult loginResult;
	protected RechargeResult rechargeResult;
	protected float payValue = 0;
	private String mOrderJinliJson = "";
	protected String mOrderId = "";
	private Intent intent;
	private GameInfo gameInfo;
	private LoginCallBackListener loginCallBackListener;

	public static SdkJinLi getInstance() {
		if (sInstance == null) {
			sInstance = new SdkJinLi();
		}
		return sInstance;
	}

	/**
	 * 初始化金立SDK
	 */
	public void initSDK(Activity activity, Class<?> mainClass) {
		Intent intent = new Intent(UldPlatform.sActivity, mainClass);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(RE_LOGION, true);
		GnCommplatform.getInstance(activity).init(activity, GnEType.GAME, APIKEY, intent);
	}

	/**
	 * 登录接口
	 * 
	 * @param sContext
	 * @param gameInfo
	 * @param loginCallBackListener
	 * @throws GnException
	 */
	public void loginSDK(Activity sContext, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) throws GnException {

		if (!GnCommplatform.getInstance(sContext).checkLocalEnvironment(sContext)) {
			LoginResult loginResult = new LoginResult();
			loginResult.setIsLogin(false);
			loginResult.setLoginErrorMsg("未安装插件");
			loginCallBackListener.onLoginFinished(loginResult);
		}

		GnCommplatform.getInstance(sContext).loginAccount(sContext, LOGIN_REQUEST_CODE, gameInfo.getUldIsAutoLogin(), new IGnUiListener() {
			@Override
			public void onError(Exception arg0) {
				LoginResult loginResult = new LoginResult();
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("登录失败");
				loginCallBackListener.onLoginFinished(loginResult);
			}

			@Override
			public void onComplete(JSONObject jsonObject) {
				// 登录成功，处理自己的业务。
				try {

					LoginResult loginResult = new LoginResult();
					// 获取pid 平台用户id
					String playerId = (String) jsonObject.get(PLAYER_ID);
					// 获取amigoUserId
					// String amigoUserId = (String) jsonObject
					// .get(USER_ID);
					// 获取amigoToken 服务端验证
					String amigoToken = (String) jsonObject.get(ASSOCIATE_STRING);

					// 获取用户手机号
					// String amigoTn = (String) jsonObject
					// .get(TELEPHONE_NUMBER);
					// 获取玩家昵称
					// String amigoNa = (String) jsonObject.get(NAME);
					// System.out.println("amigoNa........" + amigoNa);

					loginResult.setIsLogin(false);
					loginResult.setLoginErrorMsg("请稍后再试");

					// 登陆成功
					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();

					MessageBody messageBody = new MessageBody("uld.sdk.bll.UserJinLi", "login", new Class<?>[] { int.class, int.class,
							int.class, int.class, int.class, int.class, String.class, String.class, String.class }, new Object[] {
							gameInfo.getGameId(), gameInfo.getServerId(), UldPlatform.mobileDeviceId, UldPlatform.statisticAnalysisId,
							UldPlatform.sChannelId, UldPlatform.sChannelSubId, amigoToken, playerId, UldPlatform.sDeviceName });
					MessageReturn messageReturn = ThreadManager.sendMessage(messageHeader, messageBody);
					if (messageReturn.findErr()) {
						loginResult.setIsLogin(false);
						loginResult.setLoginErrorMsg(messageReturn.getErrMsg());
						loginCallBackListener.onLoginFinished(loginResult);
					} else {
						// 通知游戏客户端登陆成功
						@SuppressWarnings("unchecked")
						Map<String, String> reMap = (Map<String, String>) messageReturn.getRetObject();
						if (reMap != null) {
							loginResult.setIsLogin(true);
							loginResult.setChannelUserId(reMap.get("UserId"));
							loginResult.setTimeSign(reMap.get("TimeSign"));
							UldPlatform.channelUserId = reMap.get("amigoUserId");
							loginCallBackListener.onLoginFinished(loginResult);
						} else {
							loginResult.setIsLogin(false);
							loginResult.setChannelUserId("");
							loginResult.setLoginErrorMsg("服务器异常，请稍后再试");
							loginCallBackListener.onLoginFinished(loginResult);
						}
					}

				} catch (JSONException e) {

				}
			}

			@Override
			public void onCancel() {
				LoginResult loginResult = new LoginResult();
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("取消登录");
				loginCallBackListener.onLoginFinished(loginResult);
			}
		});
	}

	public void recharege(final Activity context, final RechargeCallBackListener rechargeCallBackListener) {

		mRechargeCallBackListener = rechargeCallBackListener;

		// 选择充值金额
		final int checkedItem = 0;
		final String[] payValueAry = new String[] { "10", "20", "30", "50", "100", "200", "300", "500", "1000", "2000" };

		alertDialog = new AlertDialog.Builder(context).setTitle("请选择充值金额，单位：元")
				.setSingleChoiceItems(payValueAry, checkedItem, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						payValue = (float) Integer.valueOf(payValueAry[which]);
						if (alertDialog != null) {
							alertDialog.dismiss();
						}
						// Log.d("SDKAnzhi", "充值金额：" + payValue);
						// paySdk(payValue, rechargeCallBackListener);
						Log.d(TAG, "向发送服务端发送数据");
						rachageHandler();
					}
				}).setPositiveButton("确认", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						payValue = (float) Integer.valueOf(payValueAry[checkedItem]);
						if (alertDialog != null) {
							alertDialog.dismiss();
						}

						Log.d(TAG, "向发送服务端发送数据");
						rachageHandler();

					}
				}).create();
		alertDialog.show();

	}

	Handler handlerrachage = null;

	protected void rachageHandler() {

		getUldOrder();

		// handlerrachage = new Handler() {
		//
		// public void handleMessage(Message msg) {
		// super.handleMessage(msg);
		//
		// Log.d(TAG, "handlerrachage中..........向金立发送数据之前");
		// openJinLiPayUI();
		// Log.d(TAG, "handlerrachage中..........向金立发送数据之后");
		//
		// // mRechargeCallBackListener.onRechargeUiFinished(rechargeResult);
		// }
		// };

		// Threadrechage rachageThread = new Threadrechage();
		// new Thread(rachageThread).start();

	}

	@SuppressWarnings("unchecked")
	public void getUldOrder() {
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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.UserJinLi", "createOrder", new Class<?>[] { wh.order.model.Order.class,
				String.class, int.class, int.class, String.class }, new Object[] { order, UldPlatform.channelUserId,
				UldPlatform.sChannelId, UldPlatform.sChannelSubId, playerId });
		MessageReturn mReturn = ThreadManager.sendMessage(messageHeader, messageBody);

		HashMap<String, Object> reMapandmrt = new HashMap<String, Object>();
		reMapandmrt = (HashMap<String, Object>) (mReturn.getRetObject());
		mOrderJinliJson = (String) reMapandmrt.get("orederjson");
		MessageReturn messageReturn = (MessageReturn) reMapandmrt.get("messageReturn");

		if (!messageReturn.findErr()) {
			if (messageReturn.getRetObject() != null) {
				order.setOrderId(Utility.getInt(messageReturn.getRetObject()));
				mOrderId = String.valueOf(order.getOrderId());
			}
		}

		if (order.getOrderId() <= 0) {
			rechargeResult = new RechargeResult();
			rechargeResult.setOrderId("");
			rechargeResult.setOrderType(3);
			rechargeResult.setOrderMsg("其他错误");
			mRechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			return;
		}

		openJinLiPayUI();

	}

	protected void openJinLiPayUI() {
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject json = new JSONObject(mOrderJinliJson);
			String out_order_no = (String) json.get("out_order_no");
			String api_key = (String) json.get("api_key");
			String submit_time = (String) json.get("submit_time");

			jsonObject.put("out_order_no", out_order_no);
			jsonObject.put("api_key", api_key);
			jsonObject.put("submit_time", submit_time);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		String orderInfo = jsonObject.toString();

		mAmigoPayer = new AmigoPayer(UldPlatform.sActivity);
		myPayCallback = mAmigoPayer.new MyPayCallback() {
			/*
			 * 支付结束
			 * 
			 * @params stateCode 支付结果的返回码，代码成功还是失败 返回码定义
			 */
			@Override
			public void payEnd(String stateCode) {
				// 这句话必须，否则不会自动调起收银台
				super.payEnd(stateCode);

				rechargeResult = new RechargeResult();

				int code = Integer.parseInt(stateCode);
				switch (code) {
				case 9000:
					rechargeResult.setOrderId(mOrderId);
					rechargeResult.setPayAccount(payValue);
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg("充值成功");
					break;
				case 4000:
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg("系统异常");
					break;
				case 4001:
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg("数据格式不正确");
					break;
				case 4002:
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg("空间不足升级失败");
					break;
				case 4003:
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg("账号安全级别低，升级失败");
					break;
				case 4006:
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg("订单支付失败");
					break;
				case 6000:
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg("支付服务正在进行升级操作或异常");
					break;
				case 6001:
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg("用户中途取消支付操作");
					break;
				case 6002:
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg("网络连接异常");
					break;
				}
				mRechargeCallBackListener.onRechargeUiFinished(rechargeResult);
			}
		};

		try {
			mAmigoPayer.pay(orderInfo, myPayCallback, mAmigoPayer.getBitmap(R.drawable.aojian));
		} catch (GnException e) {
			Log.e(SdkJinLi.class.getSimpleName(), "调用mAmigoPayer.pay异常，err:" + e.getMessage());
		}

	}

	/**
	 * 返回一个当前应用的图标，会在支付的收银台显示该图标
	 * 
	 * @param iconId
	 * @return
	 */
	public Bitmap getBitmap(int iconId) {
		Resources res = UldPlatform.sActivity.getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, iconId);
		return bmp;
	}

	class Threadrechage implements Runnable {
		public void run() {

			Log.d(TAG, "Threadrechage中......向发送服务端发送数据之前");
			getUldOrder();
			Log.d(TAG, "Threadrechage中......向发送服务端发送数据完成");
			Log.d("threadrachage.......", "mThread........");
			Message msg = new Message();
			msg.what = 1;
			// handlerrachage.sendMessage(msg);

		}
	}
}
