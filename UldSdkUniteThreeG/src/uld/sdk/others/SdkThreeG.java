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
import uld.sdk.unite.RechargeCallBackListener;
import uld.sdk.unite.RechargeResult;
import uld.sdk.unite.UldBase;
import uld.sdk.unite.UldPlatform;
import wh.order.model.OrderEnum;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.jiubang.game2324.Game2324Manager;
import com.jiubang.game2324.InitCallBack;
import com.jiubang.game2324.LoginCallBack;
import com.jiubang.game2324.RechargeCallBack;

/**
 * sdk版本：3.0
 * 最后更新：2014-4-1 17:03:34
 * @author Tony
 *
 */
public class SdkThreeG extends UldBase {
	private static int mThreeGgaimeid = 0;
	private static int mThreeGcpid = 0;

	private static SdkThreeG instance = null;

	public static SdkThreeG getInstance() {
		if (instance == null) {
			instance = new SdkThreeG();
		}
		return instance;
	}

	public void initSDK(final Context context) {

		mThreeGgaimeid = 2447;
		mThreeGcpid = 2446;

		Log.d("uldAppId-ThreeG-GAMEID", String.valueOf(mThreeGgaimeid));
		Log.d("uldAppKey-ThreeG-CPID", String.valueOf(mThreeGcpid));

		Game2324Manager.getInstance().Init(context.getApplicationContext(), mThreeGgaimeid, mThreeGcpid, new InitCallBack() {

			@Override
			public void callback(int arg0) {
				if (arg0 == Game2324Manager.INIT_CODE_SUCCESS) {
					Log.d("uldAppId-ThreeG-init", "SUCCESS");

				}
			}
		});

		// Game2324Manager.getInstance().ClearUserInfo();
	}

	public void loginSDK(Activity context, final GameInfo gameInfo, final LoginCallBackListener loginCallBackListener) {

		Game2324Manager.getInstance().Login(context, new LoginCallBack() {

			// code 登录是否成功 uid=token 就是token，用户相对游戏的唯一标示 sid SessionID，用于校验用户会话的
			@Override
			public void callback(int code, String uid, String sid) {

				LoginResult loginResult = new LoginResult();
				loginResult.setIsLogin(false);
				loginResult.setLoginErrorMsg("登录失败");

				if (code == Game2324Manager.LOGIN_CODE_SUCCESS) {

					// 登录成功
					MessageHeader messageHeader = new MessageHeader();
					messageHeader.init();

					MessageBody messageBody = new MessageBody("uld.sdk.bll.User3G", "login", new Class<?>[] { int.class, int.class,
							int.class, int.class, int.class, int.class, String.class, String.class,String.class }, new Object[] { gameInfo.getGameId(),
							gameInfo.getServerId(), UldPlatform.mobileDeviceId, UldPlatform.statisticAnalysisId, UldPlatform.sChannelId,
							UldPlatform.sChannelSubId, sid, uid ,UldPlatform.sDeviceName});
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
							loginResult.setLoginErrorMsg("登录成功");
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
					Log.d("uldsdk", "2324 LOGIN_CODE_fail");
					// 登录失败
				}
				loginCallBackListener.onLoginFinished(loginResult);
			}
		});
	}

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
		MessageBody messageBody = new MessageBody("uld.sdk.bll.User3G", "createOrder", new Class<?>[] { wh.order.model.Order.class,
				String.class, int.class, int.class, String.class }, new Object[] { order, UldPlatform.channelUserId,
				UldPlatform.sChannelId, UldPlatform.sChannelSubId, "playerid" });
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

		// context
		// 订单金额
		// 回调函数，会在进入界面是回调
		// 回传参数，长度50 "gameId=" + gameInfo.getGameId() + "#serverId=" +
		// gameInfo.getServerId()
		Game2324Manager.getInstance().Recharge(context, 0, new RechargeCallBack() {

			@Override
			public void callback(String arg0) {
				System.out.println(arg0);
				Log.e("arg0", arg0);
				if (arg0 != null && arg0.length() > 0) {
					RechargeResult rechargeResult = new RechargeResult();
					rechargeResult.setOrderId(String.valueOf(order.getOrderId()));
					rechargeResult.setOrderType(0);
					rechargeResult.setOrderMsg("充值成功");
					rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
				} else {
					RechargeResult rechargeResult = new RechargeResult();
					rechargeResult.setOrderId(String.valueOf(order.getOrderId()));
					rechargeResult.setOrderType(2);
					rechargeResult.setOrderMsg("充值失败");
					rechargeCallBackListener.onRechargeUiFinished(rechargeResult);
				}
			}
		}, "orderId=" + order.getOrderId() + "#gameId=" + gameInfo.getGameId() + "#serverId=" + gameInfo.getServerId());

	}

	public void finishgame() {
		Game2324Manager.getInstance().ClearUserInfo();
	}

}
