package uld.sdk.bll;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import uld.sdk.model.MessageReturn;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;

public class UserDianJin {

	public MessageReturn checkSid(String sid, int gameIdp, int serverIdp, int channelIdp, int channelSubIdp, int mobiledeviceIdp, int statisticAnalysisIdp, String userId) {
		MessageReturn messageReturn = new MessageReturn();
		final int gameId = gameIdp;
		final int serverId = serverIdp;
		final int channelId = channelIdp;
		final int channelSubId = channelSubIdp;
		final int mobiledeviceId = mobiledeviceIdp;
		final int statisticAnalysisId = statisticAnalysisIdp;

		String checkSidUrl = "http://payunite.ulaoda.com/DianJinCheckSid.ashx?sid=" + sid + "&userId=" + userId;
		Map<String, String> retMap = null;
		try {
			String retValue = Utility.getWebContent(checkSidUrl);
			if (!Utility.isEmpty(retValue)) {
				retMap = (Map<String, String>) JSONObject.parseObject(retValue, Map.class);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (retMap != null) {
			int errNo = Utility.getInt(retMap.get("ErrNo"));
			if (errNo < 0) {
				messageReturn.setErr(errNo, retMap.get("ErrMsg"));
			} else {
				Map<String, String> tempMap = new HashMap<String, String>();
				tempMap.put("UserId", retMap.get("UserId"));
				tempMap.put("UserName", retMap.get("UserName"));
				messageReturn.setRetObject(tempMap);

				final String channelUserId = tempMap.get("UserId");
				// 模拟登录
				new Thread() {
					public void run() {
						UserUnite.getInstance().login(gameId, serverId, channelId, channelSubId, channelUserId, mobiledeviceId, statisticAnalysisId);
					}
				}.start();
			}
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

		model.setCreateDate(new Date());
		model.setModifyDate(new Date());

		//注：因从客户端传过来的ServerId实际为SequenceNumber，所以在此要转化一下
		model.setServerId(ServerTools.getServerIdBySequenceNumber(model.getGameId(), model.getServerId(),"UserDianJin-createOrder"));
		
		MessageReturn messageReturn = wh.order.bll.Order.getInstance().createUpdate(model);

		if (Utility.isEmpty(playerId)) {
			playerId = String.valueOf(userId);
		}
		wh.order.model.OrderLog orderLog = new wh.order.model.OrderLog();
		orderLog.setCreateDate(new Date());
		orderLog.setLogName("playerid");
		orderLog.setDescription(playerId);
		orderLog.setOrderId(Utility.getInt(messageReturn.getRetObject()));
		orderLog.setStatus((byte) 1);
		orderLog.setUserId(userId);
		wh.order.bll.OrderLog.getInstance().createUpdate(orderLog);

		return messageReturn;
	}
}
