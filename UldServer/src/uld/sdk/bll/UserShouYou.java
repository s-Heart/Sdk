package uld.sdk.bll;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import uld.sdk.model.MessageReturn;
import uld.sdk.nineone.LoginError;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;

public class UserShouYou {
	public MessageReturn login(final int gameId, final int serverId, final int mobiledeviceId, final int statisticAnalysisId,
			final int channelId, final int channelSubId, final String uid) {
		MessageReturn messageReturn = new MessageReturn();

		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("UserId", uid);
		tempMap.put("UserName", "");
		messageReturn.setRetObject(tempMap);

		new Thread() {
			public void run() {
				UserUnite.getInstance()
						.login(gameId, serverId, channelId, channelSubId, uid, mobiledeviceId, statisticAnalysisId);
			}
		}.start();

		return messageReturn;
	}

	public MessageReturn createOrder(wh.order.model.Order model, String channelUserId, int channelId, int channelSubId,
			String playerId) {
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

		//注：因从客户端传过来的ServerId实际为SequenceNumber，所以在此要转化一下
		model.setServerId(ServerTools.getServerIdBySequenceNumber(model.getGameId(), model.getServerId(),"UserShouYou-createOrder"));
		
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
