package uld.sdk.bll;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import uld.sdk.model.MessageReturn;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;
import wh.game.model.GameEnum.GameMoneyType;
import wh.order.bll.Order;

public class UserMobileBase {
	public MessageReturn createOrder(wh.order.model.Order model, String channelUserId, int channelId, int channelSubId, String playerId) {

		RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);
		wh.member.model.User user = wh.member.bll.User.getInstance().get(model.getUserId(), refMyErr);
		int userId = 0;
		if (user != null) {
			userId = user.getUserId();
		}

		model.setUserId(user.getUserId());
		model.setChargedUserId(user.getUserId());

		// model.setChargedUserName(user.getUserName());

		if (model.getPayAccount() == null) {
			model.setPayAccount(BigDecimal.ZERO);
		}
		if (model.getRealPayAccount() == null) {
			model.setRealPayAccount(BigDecimal.ZERO);
		}

		model.setCreateDate(new Date());
		model.setModifyDate(new Date());

		// 注：因从客户端传过来的ServerId实际为SequenceNumber，所以在此要转化一下
		model.setServerId(ServerTools.getServerIdBySequenceNumber(model.getGameId(), model.getServerId(), "UserMobileBase-createOrder"));

		MessageReturn mr = wh.order.bll.Order.getInstance().createUpdate(model);

		int orderId = 0;
		if (!mr.findErr()) {
			orderId = uld.sdk.tools.Utility.getInt(mr.getRetObject());
		}

		MobileBase mb = getMobileBase(model.getGameId());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mb", mb);
		map.put("orderId", orderId);
		// MobileBase cpParam 的key
		map.put("key", "ulaoda");

		wh.game.model.Game game = wh.game.bll.Game.getInstance().get(model.getGameId(), refMyErr);
		if (game != null) {
			map.put("GameRate", String.valueOf(game.getGameMoneyRate()));
			map.put("GameMoneyType", GameMoneyType.parse(game.getGameMoneyType()).toString());
			map.put("GameName", game.getGameName());
			map.put("Content", game.getContent());

		}
		mr.setRetObject(map);

		if (Utility.isEmpty(playerId)) {
			playerId = String.valueOf(userId);
		}
		wh.order.model.OrderLog orderLog = new wh.order.model.OrderLog();
		orderLog.setCreateDate(new Date());
		orderLog.setLogName("playerid");
		orderLog.setDescription(playerId);
		orderLog.setOrderId(orderId);
		orderLog.setStatus((byte) 1);
		orderLog.setUserId(userId);
		wh.order.bll.OrderLog.getInstance().createUpdate(orderLog);

		return mr;
	}

	// 短信指令所有信息
	private MobileBase getMobileBase(int gameid) {
		if (gameid == 3) {

			MobileBase mb = new MobileBase();
			mb.setFeeType("12");
			mb.setCpId("772063");
			mb.setcPServiceID("000076111000");

			mb.setfID("1000");
			mb.setPackageID("000000000000");
			mb.setcPSign("000000");

			// 道具计费代码
			HashMap<String, Object> map = new HashMap<String, Object>();
			ArrayList<String> alkey = new ArrayList<String>();
			ArrayList<String> alvalue = new ArrayList<String>();
			alkey.add("000076111001");
			alvalue.add("500");
			alkey.add("000076111002");
			alvalue.add("800");
			map.put("key", alkey);
			map.put("value", alvalue);
			mb.setConsumeCodeString(map);
			return mb;
		} else {
			return null;
		}
	}
}
