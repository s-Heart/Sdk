package uld.sdk.bll;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Response;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipayLogger;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;

import uld.sdk.model.MessageReturn;
import uld.sdk.tools.Config;
import uld.sdk.tools.LogHelper;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;
import wh.member.model.User;
import wh.order.model.Order;
import wh.order.model.OrderChannel;

/**
 * 游老大快捷支付充值时调用此处的服务器响应逻辑类
 * @author 史少杰
 * 2013年12月5日11:48:48
 *
 */
public class UserQianbao {

	private static final String SERVERURL = "http://openapi.alipaydev.com/gateway.do";
	private static String appId;

	public MessageReturn login(final int gameId, final int serverId, final int mobiledeviceId, final int statisticAnalysisId,
			final int channelId, final int channelSubId, String appId, final String userId, String auth_code) {
		MessageReturn messageReturn = new MessageReturn();
		try {
			messageReturn.setErrNo(-1);
			this.appId = appId;
			// 5拿AliUserId/授权码验证唤起合法性,并使用传过来的alipay_user_id创建账号
			AlipayClient client = new DefaultAlipayClient(SERVERURL, appId, Config.RSA_PRIVATE, "json");
			AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
			request.setCode(auth_code);
			LogHelper.log("auth_code:" + auth_code);
			request.setGrantType("authorization_code");

			
			AlipaySystemOauthTokenResponse response = client.execute(request, auth_code);
			String acessToken = response.getAccessToken();
			String refreshToken = response.getRefreshToken();
			if (response != null) {
				messageReturn = UserUnite.getInstance().login(gameId, serverId, channelId, channelSubId, userId, mobiledeviceId,
						statisticAnalysisId);
				wh.member.model.User model = (User) messageReturn.getRetObject();
				Map<String, Object> retMap = new HashMap<String, Object>();
				retMap.put("model", model);
				retMap.put("acessToken", acessToken);
				retMap.put("refreshToken", refreshToken);
				messageReturn.setRetObject(retMap);
				return messageReturn;
			}
		} catch (AlipayApiException e) {
			LogHelper.log(e.getErrMsg());
		}
		return messageReturn;

	}

	//用刷新token换取访问token
	public MessageReturn getAcessTokenByRefreshToken(String refreshToken) {
		MessageReturn messageReturn = new MessageReturn();
		try {
			AlipayClient client = new DefaultAlipayClient(SERVERURL, appId, Config.RSA_PRIVATE, "json");
			AlipaySystemOauthTokenRequest req = new AlipaySystemOauthTokenRequest();
			req.setGrantType("refresh_token");
			AlipaySystemOauthTokenResponse res = client.execute(req, refreshToken);
			if(res.getAccessToken()!=null){
				messageReturn.setRetObject(res.getAccessToken());
			}else{
				messageReturn.setErrNo(-1);
			}
		} catch (AlipayApiException e) {
//			e.printStackTrace();
			LogHelper.log(e.getErrMsg());
		}
		return messageReturn;
	}

	public MessageReturn createOrder(wh.order.model.Order model, String userId, String tel,int ocChannelId,int gameChannelId) {
		RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);
		wh.member.model.User user = wh.member.bll.User.getInstance().get(Integer.parseInt(userId), refMyErr);

		model.setUserId(user.getUserId());
		model.setChargedUserId(user.getUserId());
		model.setChargedUserName(user.getUserName());
		if (model.getPayAccount() == null) {
			model.setPayAccount(BigDecimal.ZERO);
		}
		if (model.getRealPayAccount() == null) {
			model.setRealPayAccount(BigDecimal.ZERO);
		}
		model.setCreateDate(new Date());
		model.setModifyDate(new Date());

		// 注：因从客户端传过来的ServerId实际为SequenceNumber，所以在此要转化一下
		model.setServerId(ServerTools.getServerIdBySequenceNumber(model.getGameId(), model.getServerId(), "UserUld-createOrder"));

		MessageReturn messageReturn = wh.order.bll.Order.getInstance().createUpdate(model);
		Map<String, Object> retObject = new HashMap<String, Object>();
		int orderId=(Integer) messageReturn.getRetObject();
		retObject.put("orderid", orderId);
		retObject.put("partner", Config.PARTNER);
		retObject.put("seller", Config.SELLER);
		retObject.put("privatekey", Config.RSA_PRIVATE);
		retObject.put("publickey", Config.RSA_ALIPAY_PUBLIC);
		
		//将数据写入orderChannel表中
		wh.order.model.OrderChannel orderChannel = new OrderChannel();
		orderChannel.setLocalOrderId(orderId);
		orderChannel.setOcChannelId(ocChannelId);
		orderChannel.setRegChannelId(user.getChannelId());
		orderChannel.setGameChannelId(gameChannelId);//传递过来时就是写死的，所以这里的数据也可以写死7
		orderChannel.setStatus((byte) 1);
		wh.order.bll.OrderChannel.getInstance().createUpdate(orderChannel);
		
		
		messageReturn.setRetObject(retObject);

		wh.order.model.OrderLog orderLog = new wh.order.model.OrderLog();
		orderLog.setCreateDate(new Date());
		orderLog.setUserId(Utility.getInt(userId));
		orderLog.setLogName("playerid");
		orderLog.setDescription(userId);
		/** messageReturn.getRetObject(); "Ret"=="return"; */
		orderLog.setOrderId(Utility.getInt(retObject.get("orderid")));
		wh.order.bll.OrderLog.getInstance().createUpdate(orderLog);

		return messageReturn;
	}
	

}
