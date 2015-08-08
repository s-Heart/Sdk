package uld.sdk.bll;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import uld.sdk.model.MessageReturn;
import uld.sdk.tools.Base64;
import uld.sdk.tools.Config;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RSAEncrypt;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;
import wh.order.model.OrderChannel;

import com.alibaba.fastjson.JSON;

public class UserWanDouJia {
	/**
	 * 修改bug,处理注册用户MobileDeviceId=0 ,添加设备Id识别的操作,如果设备Id为0,则根据新传递过来的sDeviceId
	 * 来对mobiledeviceId与statisticAnalysisId赋值之后再做其他相关操作.
	 * 
	 * @param gameId
	 * @param serverId
	 * @param mobiledeviceId
	 * @param statisticAnalysisId
	 * @param channelId
	 * @param channelSubId
	 * @param sid
	 * @param uid
	 * @param sMobileDeviceName
	 * @return
	 */
	public MessageReturn login(int gameId,int serverId,int mobileDeviceId,int statisticAnalysisId,
			int channelId,int channelSubId,String sid,String uid,String sMobileDeviceName) {
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

		return login(gameId, serverId, mobileDeviceId, statisticAnalysisId, channelId, channelSubId, sid, uid);
		
	}
	
	

	public MessageReturn login(final int gameId, final int serverId, final int mobiledeviceId, final int statisticAnalysisId,
			final int channelId, final int channelSubId, final String sid, final String uid) {
		MessageReturn messageReturn = new MessageReturn();

		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("UserId", uid);
		tempMap.put("UserName", "");
		messageReturn.setRetObject(tempMap);

		new Thread() {
			public void run() {
				UserUnite.getInstance().login(gameId, serverId, channelId, channelSubId, uid, mobiledeviceId, statisticAnalysisId);
			}
		}.start();
		// 针对于傲剑游戏提供的时间签名
		setTimeSignForAojian(messageReturn, gameId, tempMap, channelId, uid);

		// 登录成功后进行验证
		String appid = "100000611";
		String token = sid;
		Loginauthentication(appid, token, uid);

		uld.sdk.tools.LogHelper.log("WanDouJia-appid:" + appid);
		uld.sdk.tools.LogHelper.log("WanDouJia-token:" + token);
		uld.sdk.tools.LogHelper.log("WanDouJia-uid:" + uid);

		return messageReturn;
	}

	private void Loginauthentication(String appid, String token, String uid) {
		String checkSidUrl = "https://pay.wandoujia.com/api/uid/check";
		String url = checkSidUrl + "?uid=" + uid + "&token=" + URLEncoder.encode(token) + "&appkey_id=" + String.valueOf(appid);
		try {
			String retValue = Utility.getWebContent(url);
			uld.sdk.tools.LogHelper.log("WanDouJia-url:" + url);
			uld.sdk.tools.LogHelper.log("WanDouJia-https->results:" + retValue);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setTimeSignForAojian(MessageReturn messageReturn, int gameId, Map<String, String> tempMap, int channelId, String uid) {
		if (gameId == 8) {
			RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);
			// 其中
			// timesign = RSA({"time":
			// "2013-11-11 15:26:32","addValue":"1","channelid":"13","userid":"uld123456","MD5":"8C5EABE5012EA60BEF37742D54E9C607"})
			// MD5 = MD5(time + addValue + channelid + userid);
			String time = Utility.getFormatDate();
			int addValue = wh.member.bll.HBUser.getInstance().getMaxHbUserId(refMyErr);
			String channelid = String.valueOf(channelId);
			String userid = uid;
			// TODO: MD5加密排列方式的确定
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
		model.setServerId(ServerTools.getServerIdBySequenceNumber(model.getGameId(), model.getServerId(), "UserWanDouJia-createOrder"));

		MessageReturn messageReturn = wh.order.bll.Order.getInstance().createUpdate(model);

		if (Utility.isEmpty(playerId)) {
			playerId = String.valueOf(userId);
		}

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

		return messageReturn;
	}
}
