﻿package uld.sdk.bll;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;

import uld.sdk.model.MessageReturn;
import uld.sdk.tools.Base64;
import uld.sdk.tools.Config;
import uld.sdk.tools.LogHelper;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RSAEncrypt;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;
import wh.order.model.OrderChannel;
import wh.order.model.OrderEnum;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UserUC {

	/**
	 * 修改bug,处理注册用户MobileDeviceId=0 ,添加设备Id识别的操作,如果设备Id为0,则根据新传递过来的sDeviceId
	 * 来对mobiledeviceId与statisticAnalysisId赋值之后再做其他相关操作.
	 * 
	 * @param sid
	 * @param gameId
	 * @param serverId
	 * @param channelId
	 * @param channelSubId
	 * @param mobileDeviceId
	 * @param statisticAnalysisId
	 * @param sMobileDeviceName
	 * @return
	 */
	public MessageReturn checkSid(String sid, int gameId, int serverId, int channelId, int channelSubId, int mobileDeviceId,
			int statisticAnalysisId,String sMobileDeviceName) {
		Date date = new Date();
		RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);

		if (mobileDeviceId == 0) {
			// 移动设备表数据
			wh.promotion.model.MobileDevice mobileDevice = wh.promotion.bll.MobileDevice.getInstance().getByName(sMobileDeviceName, "", 1,
					refMyErr);
			if (null == mobileDevice) {
				mobileDevice = new wh.promotion.model.MobileDevice();
				mobileDevice.setMobileDeviceName(sMobileDeviceName);
				mobileDevice.setCreateDate(date);
				mobileDevice.setModifyDate(date);
				mobileDevice.setMobileDeviceId(wh.promotion.bll.MobileDevice.getInstance().createUpdate(mobileDevice, refMyErr));
			}

			mobileDeviceId = mobileDevice.getMobileDeviceId();
		}

		if (statisticAnalysisId == 0) {
			// 统计分析表数据
			wh.promotion.model.StatisticAnalysis statisticAnalysis = new wh.promotion.model.StatisticAnalysis();
			statisticAnalysis.setMobileDeviceId(mobileDeviceId);
			statisticAnalysis.setGameId(gameId);
			statisticAnalysis.setChannelId(channelId);
			statisticAnalysis.setChannelSubId(channelSubId);
			if (statisticAnalysis.getChannelId() > 0 || statisticAnalysis.getChannelSubId() > 0) {
				statisticAnalysis.setChannelType((byte) 2);
			} else {
				statisticAnalysis.setChannelType((byte) 1);
			}

			RefObject<Integer> totalCount = new RefObject<Integer>(0);

			List<wh.promotion.model.StatisticAnalysis> saList = wh.promotion.bll.StatisticAnalysis.getInstance().getList(statisticAnalysis,
					totalCount, false, 1, 1, "", 1, refMyErr);
			if (saList.size() > 0) {
				statisticAnalysis = saList.get(0);
			} else {
				statisticAnalysis.setCreateDate(date);
				statisticAnalysis.setStatisticAnalysisId(wh.promotion.bll.StatisticAnalysis.getInstance().createUpdate(statisticAnalysis,
						refMyErr));
			}

			statisticAnalysisId = statisticAnalysis.getStatisticAnalysisId();
		}

		return checkSid(sid, gameId, serverId, channelId, channelSubId, mobileDeviceId, statisticAnalysisId);
	}
	
	
	@SuppressWarnings("unchecked")
	public MessageReturn checkSid(String sid, int gameIdp, int serverIdp, int channelIdp, int channelSubIdp, int mobiledeviceIdp,
			int statisticAnalysisIdp) {
		MessageReturn messageReturn = new MessageReturn();

		final int gameId = gameIdp;
		final int serverId = serverIdp;
		final int channelId = channelIdp;
		final int channelSubId = channelSubIdp;
		final int mobiledeviceId = mobiledeviceIdp;
		final int statisticAnalysisId = statisticAnalysisIdp;
		LogHelper.log("UserUc->checkSid gameId：" + String.valueOf(gameId));
		LogHelper.log("UserUc->checkSid serverId：" + String.valueOf(serverId));

		String checkSidUrl = "http://payunite.ulaoda.com/UCCheckSid.ashx?sid=" + sid + "&gameId=" + gameId;
		LogHelper.log("UserUc->checkSid url:" + checkSidUrl);
		Map<String, String> retMap = null;
		try {
			String retValue = Utility.getWebContent(checkSidUrl);
			if (!Utility.isEmpty(retValue)) {
				retMap = (Map<String, String>) JSONObject.parseObject(retValue, Map.class);
			} else {
				LogHelper.log("UserUc->checkSid retMap is empty:" + checkSidUrl);
			}
		} catch (IOException e) {
			e.printStackTrace();
			LogHelper.log("UserUc->checkSid exception:" + e.getMessage());
		}

		if (retMap != null) {
			int errNo = Utility.getInt(retMap.get("ErrNo"));
			if (errNo < 0) {
				messageReturn.setErr(errNo, retMap.get("ErrMsg"));
			} else {
				Map<String, String> tempMap = new HashMap<String, String>();
				String ucid = retMap.get("ucid");
				tempMap.put("UserId", retMap.get("ucid"));
				tempMap.put("UserName", retMap.get("nickName"));

				final String channelUserId = tempMap.get("UserId");

				// 模拟登录
				new Thread() {
					public void run() {
						LogHelper.log("UserUc->checkSid thread begin" + String.valueOf(serverId));
						UserUnite.getInstance().login(gameId, serverId, channelId, channelSubId, channelUserId, mobiledeviceId,
								statisticAnalysisId);
						LogHelper.log("UserUc->checkSid thread end" + String.valueOf(serverId));
					}
				}.start();

				// 针对于傲剑游戏提供的时间签名
				setTimeSignForAojian(messageReturn, gameId, tempMap, channelId, ucid);
			}
		}
		return messageReturn;
	}

	// 针对于傲剑游戏提供的时间签名，RSA签名之后将值返回给客户端
	private void setTimeSignForAojian(MessageReturn messageReturn, final int gameId, Map<String, String> tempMap, int channelId, String ucid) {
		if (gameId == 8) {
			RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);
			;
			// 其中
			// timesign = RSA({"time":
			// "2013-11-11 15:26:32","addValue":"1","channelid":"13","userid":"uld123456","MD5":"8C5EABE5012EA60BEF37742D54E9C607"})
			// MD5 = MD5(time + addValue + channelid + userid);
			String time = Utility.getFormatDate();
			int addValue = wh.member.bll.HBUser.getInstance().getMaxHbUserId(refMyErr);
			String channelid = String.valueOf(channelId);
			String userid = ucid;
			String md5String = Utility.encodeMD5(time + addValue + channelId + userid);
			TimeSignObject tso = new TimeSignObject();
			tso.setTime(time);
			tso.setAddvalue(String.valueOf(addValue));
			tso.setChannelid(channelid);
			tso.setUserid(userid);
			tso.setMd5(md5String);

			String timeString = JSON.toJSONString(tso);

			// 处理截取的字符串存入cutVector中，再将cutVector中的字符串依次加密
			int timeStringLength = timeString.getBytes().length;
			int cutLength = 100;

			Vector<String> cutVector = new Vector<String>();
			for (int i = 0; i < timeStringLength; i += cutLength) {
				if (i < timeStringLength && timeStringLength / (cutLength + i) != 0) {
					cutVector.add(timeString.substring(i, i + cutLength));
				} else {
					cutVector.add(timeString.substring(i, timeStringLength));
				}
			}

			// 签名准备
			String timeSign = "";

			RSAEncrypt rsaEncrypt = new RSAEncrypt();

			try {
				rsaEncrypt.loadPublicKey(Config.getConfig("GAME_TIMESIGN_RSA_PUBLIC_KEY_" + gameId));
				rsaEncrypt.loadPrivateKey(Config.getConfig("GAME_TIMESIGN_RSA_PRIVATE_KEY_" + gameId));

				Vector<String> signStrings = new Vector<String>();
				for (int i = 0; i < cutVector.size(); i++) {
					byte[] encodeBytes = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(), cutVector.get(i).getBytes("UTF-8"));
					signStrings.add(Base64.encode(encodeBytes));
				}

				Iterator<String> iterator2 = signStrings.iterator();
				while (iterator2.hasNext()) {
					timeSign += iterator2.next();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			tempMap.put("TimeSign", timeSign);
		}
		messageReturn.setRetObject(tempMap);
	}

	/**
	 * 在我们平台创建订单
	 * 
	 * @param playerId
	 *            角色编号，供订单充值成功后调用游戏服务器，传给游戏方
	 * @param channelUserId
	 *            渠道用户编号
	 * @param channleId
	 *            我们定义的渠道编号
	 * @param channelSubId
	 *            我们定义的渠道子编号
	 * @param gameId
	 *            我们定义的游戏编号
	 * @param serverId
	 *            我们定义的服务器编号
	 * @param orderId
	 *            渠道方的订单编号
	 * @param amount
	 *            渠道方的订单金额
	 * @param payWay
	 *            渠道方的充值方式
	 * @param payWayName
	 *            渠道方的充值方式名称
	 * @return
	 */
	public MessageReturn checkOrder(String playerId, String channelUserId, int channelId, int channelSubId, int gameId, int serverId,
			String orderId, float amount, int payWay, String payWayName) {

		MessageReturn messageReturn = new MessageReturn();

		LogHelper.log("channelUserId:" + channelUserId);
		LogHelper.log("channelId:" + channelId);
		LogHelper.log("channelSubId:" + channelSubId);
		LogHelper.log("gameId:" + gameId);
		LogHelper.log("orderId:" + orderId);
		LogHelper.log("amount:" + amount);

		// 创建一下订单，不需要告知游戏方，因为会在客户端通知游戏方，订单提交成功
		// 对于订单处理成功，渠道方会调用我们的一个地址，在那里需要通知游戏方订单处理成功
		String userName = UserUnite.getUserNameByChannelUserId(channelId, channelSubId, channelUserId);
		LogHelper.log("userName:" + userName);
		RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);

		wh.member.model.User user = wh.member.bll.User.getInstance().getByName(userName, "", 1, refMyErr);
		int userId = 0;
		if (user != null) {
			userId = user.getUserId();

			LogHelper.log("user is not null userId:" + userId);
		}
		LogHelper.log("userId:" + userId);

		wh.order.model.Order order = null;
		// 需要先判断一下UC的这个订单是否回调web 充值端已经创建，如果已经创建则不再创建
		wh.order.model.Order queryModel = new wh.order.model.Order();
		queryModel.setTel(channelUserId + "_UCUSERIDORDERID_" + orderId);
		queryModel.setStatus((byte) 1);
		RefObject<Integer> totalCount = new RefObject<Integer>(0);
		java.util.List<wh.order.model.Order> list = wh.order.bll.Order.getInstance().getList(queryModel, totalCount, true, 1, 1, "", 0,
				refMyErr);

		if (list.size() > 0) {
			order = list.get(0);
			messageReturn.setRetObject(order.getOrderId());//统一传游老大订单编号
		}

		// 如果web服务端并没有创建orderBean,那就根据Uc传过来的参数创建OrderBean
		// 谁创建orderBean谁创建orderChannelBean
		if (order == null) {
			order = new wh.order.model.Order();
			order.setGameId(gameId);
			order.setServerId(serverId);
			order.setUserId(userId);
			order.setChargedUserId(userId);
			order.setChargedUserName(userName);
			order.setPaySourceType(OrderEnum.PaySourceType.Android客户端.getValue());
			order.setOrderType(OrderEnum.OrderType.已提交.getValue());
			order.setAccountType(OrderEnum.AccountType.D币.getValue());
			order.setChargeType(OrderEnum.ChargeType.充值游戏.getValue());
			order.setCreateDate(new Date());
			order.setModifyDate(new Date());

			order.setTel(channelUserId + "_UCUSERIDORDERID_" + orderId);
			order.setPayAccount(BigDecimal.valueOf((amount)));
			order.setRealPayAccount(BigDecimal.valueOf(amount));

			// 75为其他渠道充值
			order.setPayType((byte) 75);
			order.setStatus((byte) 1);

			// 注：因从客户端传过来的ServerId实际为SequenceNumber，所以在此要转化一下
			order.setServerId(ServerTools.getServerIdBySequenceNumber(order.getGameId(), order.getServerId(), "UserUC-checkOrder"));

			messageReturn = wh.order.bll.Order.getInstance().createUpdate(order);

			// 将数据写入OrderChannel表中
			// OrderChannel 需要的参数
			// localOrderId 本地订单编号ok
			// regChannelId 注册渠道编号---user.getChannelId()
			// ocChannelId 实际渠道编号---channel.txt
			// gameChannelId 游戏方渠道编号--->大渠道实际，小渠道7
			// status=1
			wh.order.model.OrderChannel orderChannelModel = new OrderChannel();
			orderChannelModel.setLocalOrderId(Utility.getInt(messageReturn.getRetObject()));
			orderChannelModel.setRegChannelId(user.getChannelId());
			orderChannelModel.setOcChannelId(channelId);
			orderChannelModel.setGameChannelId(channelId);
			orderChannelModel.setStatus((byte) 1);
			wh.order.bll.OrderChannel.getInstance().createUpdate(orderChannelModel);

			wh.order.model.OrderLog orderLog = new wh.order.model.OrderLog();
			orderLog.setCreateDate(new Date());
			orderLog.setLogName("playerid");
			orderLog.setDescription(playerId);
			orderLog.setOrderId(Utility.getInt(messageReturn.getRetObject()));
			orderLog.setStatus((byte) 1);
			orderLog.setUserId(userId);
			wh.order.bll.OrderLog.getInstance().createUpdate(orderLog);
		}

		return messageReturn;
	}
}
