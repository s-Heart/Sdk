package uld.sdk.bll;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.upomp.pay.sign.Md5;

import uld.sdk.model.MessageReturn;
import uld.sdk.tools.LogHelper;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;
import wh.order.model.Order;

public class UserIFeng {

	public MessageReturn login(String service, int partner_Id, int game_Id, int server_Id, String ticket, String partnerKey,
			final int giGameId, final int giServerId, final int channelId, final int channelSubId, final String channelUserId,
			final int statisticAnalysisId, final int mobileDeviceId) {
		MessageReturn messageReturn = new MessageReturn();

		String urlString = "http://union.play.ifeng.com/mservice";
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(urlString);
		sbBuffer.append("?service=" + service);
		sbBuffer.append("&partner_id=" + partner_Id);
		sbBuffer.append("&game_id=" + game_Id);
		sbBuffer.append("&server_id=" + server_Id);
		sbBuffer.append("&ticket=" + ticket);

		String sign = "";

		System.out.println(partner_Id + game_Id + server_Id + ticket + partnerKey);

		sign = (Utility.encodeMD5(String.valueOf(partner_Id) + game_Id + server_Id + ticket + partnerKey)).toUpperCase();
		System.out.println(sign);
		sbBuffer.append("&sign=" + sign);
		// 数据返回格式 默认为json 可以为xml
		sbBuffer.append("&formart=" + "json");

		urlString = sbBuffer.toString();

		try {
			String result = Utility.getWebContent(urlString);
			LogHelper.log(result);
			System.out.println(result);
			IFengErrorMsg loginError = JSON.parseObject(result, IFengErrorMsg.class);
			// 返回成功
			if (loginError.code == 1) {
				Map<String, String> tempMap = new HashMap<String, String>();
				tempMap.put("UserId", channelUserId);
				tempMap.put("UserName", "");
				messageReturn.setRetObject(tempMap);
				// 模拟登陆：记录用户的登陆次数
				new Thread() {
					@Override
					public void run() {
						UserUnite.getInstance().login(giGameId, giServerId, channelId, channelSubId, channelUserId, mobileDeviceId,
								statisticAnalysisId);
					}
				}.start();
			} else {
				// 登录失败
				messageReturn.setErr(-1, loginError.msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return messageReturn;
	}

	public MessageReturn createOrder(wh.order.model.Order model, String channelUserId, int channelId, int channelSubId, String playerId) {
		String userName = UserUnite.getUserNameByChannelUserId(channelId, channelSubId, channelUserId);
		RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);
		wh.member.model.User user = wh.member.bll.User.getInstance().getByName(userName, "", 1, refMyErr);
		int userId = 0;
		if (user != null) {
			userId = user.getUserId();
		}

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
		model.setServerId(ServerTools.getServerIdBySequenceNumber(model.getGameId(), model.getServerId(), "UserIFeng-createOrder"));

		MessageReturn messageReturn = wh.order.bll.Order.getInstance().createUpdate(model);

		if (Utility.isEmpty(playerId)) {
			playerId = String.valueOf(userId);
		}

		wh.order.model.OrderLog orderLog = new wh.order.model.OrderLog();
		orderLog.setCreateDate(new Date());
		orderLog.setLogName("playerid");
		orderLog.setDescription(playerId);
		/** messageReturn.getRetObject(); "Ret"=="return"; */
		orderLog.setOrderId(Utility.getInt(messageReturn.getRetObject()));
		wh.order.bll.OrderLog.getInstance().createUpdate(orderLog);

		return messageReturn;
	}

}
