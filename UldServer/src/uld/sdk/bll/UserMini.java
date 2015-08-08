package uld.sdk.bll;

import java.math.BigDecimal;
import java.util.Date;

import uld.sdk.model.GameInterface;
import uld.sdk.model.MessageReturn;
import uld.sdk.tools.LogHelper;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;
import wh.member.model.HBUserEnum.DealType;

public class UserMini {
	private final String userNameSpliter = "cminiuser";
	private static UserMini instance = new UserMini();

	public static UserMini getInstance() {
		return instance;
	}

	private RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);

	/**
	 * 根据大渠道用户Id生成用户名称
	 * 
	 * @param user
	 * @return
	 */
	private String getUserNameByChannelUserId(int channelId, int channelSubId, String channelUserId) {
		String userName = "";

		// 自然渠道
		String channelName = "zr";
		// 推广渠道
		if (channelSubId > 0 && !Utility.isEmpty(channelUserId)) {
			wh.promotion.model.ChannelSub channelSub = wh.promotion.bll.ChannelSub.getInstance().get(channelSubId, refMyErr);
			if (channelSub != null) {
				channelName = Utility.cn2py(channelSub.getChannelName());
				channelName = channelName.replace("_", "") + "_";

				channelName += Utility.cn2py(channelSub.getChannelSubName());
			}
		}

		// 渠道名称简称pinyin_子渠道拼音_channelId_subchannelId_分隔符_channelUserId
		StringBuilder sb = new StringBuilder();
		sb.append(channelName + "_");
		sb.append(channelId + "_");
		sb.append(channelSubId + "_");
		sb.append(userNameSpliter + "_");
		sb.append(channelUserId);
		userName = sb.toString();

		return userName;
	}

	public MessageReturn register(int gameId, int channelId, int channelSubId, wh.promotion.model.MobileDevice mobileDevice,
			String channelUserId, wh.member.model.User user) {
		if (user!=null) {
			LogHelper.log("UserMini-register-serverId"+user.getRegisterServerId());
		}
		else {
			LogHelper.log("UserMini-register-user is null.");			
		}
		MessageReturn messageReturn = new MessageReturn();

		String userNameString = getUserNameByChannelUserId(channelId, channelSubId, channelUserId);
		if (Utility.isEmpty(userNameString)) {
			messageReturn.setErr(-1, "子渠道编号" + channelSubId + "未找到或大渠道用户编号为空");
		} else {
			user.setUserName(userNameString);
			user.setRawPassword(Utility.getRandomStr(8));
			user.setPassword(Utility.encodeMD5(user.getRawPassword()));

			//uld.sdk.bll.User.getInstance().createUpdate内部会处理，所以在此不用处理serverId
			messageReturn = uld.sdk.bll.User.getInstance().createUpdate(mobileDevice.getMobileDeviceName(), user);
		}

		return messageReturn;
	}

	public MessageReturn login(int gameId, int serverId, int channelId, int channelSubId, String channelUserId, String deviceId) {
		LogHelper.log("UserMini-login-serverId:"+serverId);
		MessageReturn messageReturn = new MessageReturn();
		String userName = getUserNameByChannelUserId(channelId, channelSubId, channelUserId);
		wh.member.model.User model = wh.member.bll.User.getInstance().getByName(userName, "", 0, refMyErr);

		if (model == null) {
			model = new wh.member.model.User();
			model.setUserName(userName);
			model.setRawPassword(Utility.getRandomStr(8));
			model.setPassword(Utility.encodeMD5(model.getRawPassword()));
			model.setRegisterGameId(gameId);
			model.setRegisterServerId(serverId);
			model.setChannelId(channelId);
			model.setChannelSubId(channelSubId);

			//uld.sdk.bll.User.getInstance().createUpdate内部会处理，所以在此不用处理serverId
			MessageReturn mr = uld.sdk.bll.User.getInstance().createUpdate(deviceId, model);
			if (mr.getErrNo() == 0 && mr.getRetObject() != null) {
				GameInterface gi = (GameInterface) mr.getRetObject();
				model.setUserId(gi.getUserId());
			}
		}

		if (model.getUserId() > 0) {
			model.setRegisterServerId(ServerTools.getServerIdBySequenceNumber(gameId, serverId,"UserMini-login"));
			SetRegisterOrLogin(model.getUserId(), gameId, model.getRegisterServerId(), model.getMobileDeviceId());
		}

		return messageReturn;
	}

	/**
	 * 记录用户登录游戏日志Mini
	 * 
	 * @param userId
	 * @param mobileDeviceId
	 */
	private void SetRegisterOrLogin(int userId, int gameId, int serverId, int mobileDeviceId) {
		wh.member.model.HBUser hbUser = new wh.member.model.HBUser();
		hbUser.setLocalUserId(userId);
		hbUser.setDealType(DealType.登录游戏未处理.getValue());
		hbUser.setCreateDate(new Date());
		hbUser.setOtherInfo("0_" + gameId + "_" + serverId + "_" + mobileDeviceId);
		uld.sdk.tools.LogHelper.log("Mini-GameCategoryId_GameId_ServerId_MobileDeviceId:" + hbUser.getOtherInfo());
		wh.member.bll.HBUser.getInstance().createUpdate(hbUser);
	}

	/**
	 * 
	 * @param gameId
	 * @param serverId
	 * @param channelId
	 * @param channelSubId
	 * @param channelUserId
	 * @param payType
	 *        充值类型，1：银行卡；2：支付宝；3：游戏卡；4其他；
	 * @param payAccount
	 * @return
	 */
	public MessageReturn rechargeSuccess(int gameId, int serverId, int channelId, int channelSubId, String channelUserId,
			String deviceId, int payType, int payAccount) {
		MessageReturn messageReturn = new MessageReturn();
		String userName = getUserNameByChannelUserId(channelId, channelSubId, channelUserId);
		wh.member.model.User model = wh.member.bll.User.getInstance().getByName(userName, "", 0, refMyErr);

		if (model == null) {
			model = new wh.member.model.User();
			model.setUserName(userName);
			model.setRawPassword(Utility.getRandomStr(8));
			model.setPassword(Utility.encodeMD5(model.getRawPassword()));
			model.setRegisterGameId(gameId);
			model.setRegisterServerId(serverId);
			model.setChannelId(channelId);
			model.setChannelSubId(channelSubId);

			//uld.sdk.bll.User.getInstance().createUpdate内部会处理，所以在此不用处理serverId
			MessageReturn mr = uld.sdk.bll.User.getInstance().createUpdate(deviceId, model);
			if (mr.getErrNo() == 0 && mr.getRetObject() != null) {
				GameInterface gi = (GameInterface) mr.getRetObject();
				model.setUserId(gi.getUserId());
			}
		}

		if (model.getUserId() > 0) {
			wh.order.model.Order order = new wh.order.model.Order();
			order.setCreateDate(new Date());
			order.setStatus(wh.order.model.OrderEnum.OrderStatus.有效.getValue());
			order.setOrderType(wh.order.model.OrderEnum.OrderType.游戏方处理成功.getValue());
			order.setPaySourceType(wh.order.model.OrderEnum.PaySourceType.Android客户端.getValue());

			wh.order.model.OrderEnum.PayType orderPayType = wh.order.model.OrderEnum.PayType.支付类型;
			switch (payType) {
			case 1:
				orderPayType = wh.order.model.OrderEnum.PayType.网上银行;
				break;
			case 2:
				orderPayType = wh.order.model.OrderEnum.PayType.支付宝;
				break;
			case 3:
				orderPayType = wh.order.model.OrderEnum.PayType.点卡;
				break;
			default:
				break;
			}
			order.setPayType(orderPayType.getValue());
			order.setPayAccount(BigDecimal.valueOf(payAccount));
			order.setRealPayAccount(BigDecimal.valueOf(payAccount));
			order.setAccountType(wh.order.model.OrderEnum.AccountType.D币.getValue());
			order.setChargedUserId(model.getUserId());
			order.setChargedUserName(model.getUserName());
			order.setChargeType(wh.order.model.OrderEnum.ChargeType.充值游戏.getValue());
			order.setGameId(gameId);
			order.setServerId(serverId);
			order.setMobilePhone(model.getMobilePhone());
			order.setModifyDate(order.getCreateDate());
			order.setRealName(model.getRealName());
			order.setUserId(model.getUserId());

			order.setChargedServerDays(1);
			order.setChargeServerTimes(1);
			order.setChargedGameDays(1);
			order.setChargeGameTimes(1);
			order.setChargedUserDays(1);
			order.setChargeUserTimes(1);

			//注：因从客户端传过来的ServerId实际为SequenceNumber，所以在此要转化一下
			order.setServerId(ServerTools.getServerIdBySequenceNumber(order.getGameId(), order.getServerId(),"UserMini-rechargeSuccess"));
			
			order.setOrderId(wh.order.bll.Order.getInstance().createUpdate(order, refMyErr));

			if (order.getOrderId() > 0) {
				// 订单详情
				wh.order.model.OrderDetail orderDetail = new wh.order.model.OrderDetail();
				orderDetail.setOrderId(order.getOrderId());
				orderDetail.setCreateDate(new Date());
				orderDetail.setModifyDate(orderDetail.getCreateDate());
				orderDetail.setOrderType(wh.order.model.OrderEnum.OrderType.游戏方处理成功.getValue());
				orderDetail.setPayAccount(order.getPayAccount());
				orderDetail.setPayType(order.getPayType());
				orderDetail.setStatus(wh.order.model.OrderDetailEnum.OrderDetailStatus.有效.getValue());
				wh.order.bll.OrderDetail.getInstance().createUpdate(orderDetail, refMyErr);

				// 订单日志
				wh.order.model.OrderLog orderLog = new wh.order.model.OrderLog();

				String logName = "通过大渠道商充值";
				wh.promotion.model.ChannelSub channelSub = null;
				if (channelSubId > 0) {
					channelSub = wh.promotion.bll.ChannelSub.getInstance().get(channelId, refMyErr);
					if (channelSub != null) {
						logName = "通过渠道" + channelSub.getChannelName() + "_" + channelSub.getChannelSubName() + "充值";
					}
				}
				orderLog.setCreateDate(new Date());
				orderLog.setLogName(logName);
				orderLog.setOrderId(order.getOrderId());
				orderLog.setStatus(wh.order.model.OrderLogEnum.OrderLogStatus.有效.getValue());
				orderLog.setUserId(order.getUserId());
				wh.order.bll.OrderLog.getInstance().createUpdate(orderLog, refMyErr);

				// 订单天数等处理
				wh.member.model.HBUser hbUser = new wh.member.model.HBUser();
				hbUser.setLocalUserId(order.getChargedUserId());
				hbUser.setDealType(wh.member.model.HBUserEnum.DealType.充值ChargedDays未处理.getValue());
				hbUser.setCreateDate(new Date());
				hbUser.setOtherInfo(gameId + "_" + order.getServerId() + "_" + order.getOrderId());
				uld.sdk.tools.LogHelper.log("GameId_ServerId_OrderId:" + hbUser.getOtherInfo());
				wh.member.bll.HBUser.getInstance().createUpdate(hbUser, refMyErr);
			}
		} else {
			messageReturn.setErr(-1, "充值用户不存在");
		}

		return messageReturn;
	}
}
