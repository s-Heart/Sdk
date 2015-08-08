﻿package uld.sdk.bll;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import uld.sdk.model.MessageReturn;
import uld.sdk.tools.Base64;
import uld.sdk.tools.Config;
import uld.sdk.tools.LogHelper;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RSAEncrypt;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;
import wh.game.model.GameEnum.GameMoneyType;
import wh.order.model.OrderChannel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Tony
 * 注意:getUserByAccessCode0801是修正并持续维护的接口，如需更新请只更新“0801”相关接口
 * 时间:2014-2-22 11:29:03
 *
 */
public class User360 {

	private String checkSidUrl0801 = "https://openapi.360.cn/oauth2/access_token";
	
	
	/**
	 * 修改bug,处理注册用户MobileDeviceId=0 ,添加设备Id识别的操作,如果设备Id为0,则根据新传递过来的sDeviceId
	 * 来对mobiledeviceId与statisticAnalysisId赋值之后再做其他相关操作.
	 * 
	 * @param accessCode
	 * @param gameId
	 * @param serverId
	 * @param channelId
	 * @param channelSubId
	 * @param mobileDeviceId
	 * @param statisticAnalysisId
	 * @param sMobileDeviceName
	 * @return
	 */
	public MessageReturn getUserByAccessCode(String accessCode, int gameId, int serverId, int channelId, int channelSubId,
			int mobileDeviceId, int statisticAnalysisId,String sMobileDeviceName) {
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

		return getUserByAccessCode(accessCode, gameId, serverId, channelId, channelSubId, mobileDeviceId, statisticAnalysisId);
	}
	

	public MessageReturn getUserByAccessCode(String accessCode, int gameIdp, int serverIdp, int channelIdp, int channelSubIdp,
			int mobiledeviceIdp, int statisticAnalysisIdp) {
		String checkSidUrl = "http://payunite.ulaoda.com/ThreeSixZeroCheckSid.ashx?code=" + accessCode;
		MessageReturn messageReturn = new MessageReturn();
		final int gameId = gameIdp;
		final int serverId = serverIdp;
		final int channelId = channelIdp;
		final int channelSubId = channelSubIdp;
		final int mobiledeviceId = mobiledeviceIdp;
		final int statisticAnalysisId = statisticAnalysisIdp;

		LogHelper.log("User360-请求checksid地址：" + checkSidUrl);

		Map<String, String> retMap = null;
		try {
			String retValue = Utility.getWebContent(checkSidUrl);
			LogHelper.log("User360-请求checksid地址结果：" + retValue);
			if (!Utility.isEmpty(retValue)) {
				retMap = (Map<String, String>) JSONObject.parseObject(retValue, Map.class);
				System.out.println(retMap);
			} else {
				messageReturn.setErr(-1, "网络连接错误！请重新连接");
				return messageReturn;
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
				tempMap.put("AccessToken", retMap.get("AccessToken"));

				final String channelUserId = tempMap.get("UserId");
				// 登录,并返回游老大id，以供充值userid
				MessageReturn mr = UserUnite.getInstance().login(gameId, serverId, channelId, channelSubId, channelUserId, mobiledeviceId,
						statisticAnalysisId);
				if (!mr.findErr()) {
					tempMap.put("UldUserId", String.valueOf(((wh.member.model.User) mr.getRetObject()).getUserId()));
					tempMap.put("UldUserName", ((wh.member.model.User) mr.getRetObject()).getUserName());
				}

				messageReturn.setRetObject(tempMap);
			}
		}
		return messageReturn;
	}
	
	/**注意下为新版sdk所访问的接口，如需更新请保持上面接口不变而改变下面接口*/
	
	/**
	 * 修改bug,处理注册用户MobileDeviceId=0 ,添加设备Id识别的操作,如果设备Id为0,则根据新传递过来的sDeviceId
	 * 来对mobiledeviceId与statisticAnalysisId赋值之后再做其他相关操作.
	 * @param map
	 * @param gameId
	 * @param serverId
	 * @param channelId
	 * @param channelSubId
	 * @param mobileDeviceId
	 * @param statisticAnalysisId
	 * @param sMobileDeviceName
	 * @return
	 */
	public MessageReturn getUserByAccessCode0801(HashMap<String, String> map, int gameId, int serverId, int channelId,
			int channelSubId, int mobileDeviceId, int statisticAnalysisId,String sMobileDeviceName) {
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

		return getUserByAccessCode0801(map, gameId, serverId, channelId, channelSubId, mobileDeviceId, statisticAnalysisId);
	}
	
	
	
	
	
	
	public MessageReturn getUserByAccessCode0801(HashMap<String, String> map, int gameIdp, int serverIdp, int channelIdp,
			int channelSubIdp, int mobiledeviceIdp, int statisticAnalysisIdp) {
		// String checkSidUrl =
		// "http://payunite.ulaoda.com/ThreeSixZeroCheckSid0801.ashx?code=" +
		// accessCode;

		checkSidUrl0801 = "https://openapi.360.cn/oauth2/access_token";
		String hostUrl = "https://openapi.360.cn";
		MessageReturn messageReturn = new MessageReturn();
		final int gameId = gameIdp;
		final int serverId = serverIdp;
		final int channelId = channelIdp;
		final int channelSubId = channelSubIdp;
		final int mobiledeviceId = mobiledeviceIdp;
		final int statisticAnalysisId = statisticAnalysisIdp;

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("code", map.get("authorizationCode"));
		params.put("redirect_uri", "oob");

		/** 游戏在平台上分配的唯一标识 */
		String appKey_360 = Config.getConfig("360_appKey_" + gameId);
		/** 游戏平台给游戏分配的一个私密字符串,只有游戏开发商和平台知道，用于通信加密校验 */
		String appSecret_360 = Config.getConfig("360_appSecret_" + gameId);
		// 火影海贼王
		params.put("client_secret", appSecret_360);
		params.put("client_id", appKey_360);

		params.put("grant_type", "authorization_code");

		LogHelper.log("User360-请求checksid地址：" + checkSidUrl0801);
		HashMap<String, String> retValueUser = null;
		Map<String, String> retMap0801 = null;
		try {

			String retValue0801 = Util360.requestUrl(checkSidUrl0801, params, true);
			LogHelper.log("User360-请求checksid地址结果：" + retValue0801);
			if (!Utility.isEmpty(retValue0801)) {
				retMap0801 = (Map<String, String>) JSONObject.parseObject(retValue0801, Map.class);
				String token360 = retMap0801.get("access_token");
				HashMap<String, String> userToken360Map = new HashMap<String, String>();
				userToken360Map.put("access_token", token360);
				userToken360Map.put("refresh_token", retMap0801.get("refresh_token"));
				String retUserValue0801 = Util360.requestUrl(hostUrl + "/user/me.json", userToken360Map, true);
				if (!Utility.isEmpty(retValue0801)) {
					retValueUser = (HashMap<String, String>) JSONObject.parseObject(retUserValue0801, HashMap.class);
				} else {
					messageReturn.setErr(-1, "网络连接错误！请重新连接");
					return messageReturn;
				}
			} else {
				messageReturn.setErr(-1, "网络连接错误！请重新连接");
				return messageReturn;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (retValueUser != null) {
			int errNo = Utility.getInt(map.get("error_code"));
			if (errNo < 0) {
				messageReturn.setErr(errNo, "网络连接错误！请重新连接");
			} else {
				Map<String, String> tempMap = new HashMap<String, String>();
				tempMap.put("UserId", retValueUser.get("id"));
				tempMap.put("UserName", retValueUser.get("name"));
				tempMap.put("refresh_token", retMap0801.get("refresh_token"));
				tempMap.put("access_token", retMap0801.get("access_token"));
				// 增加game相关参数， 充值比例，充值单位
				RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);
				wh.game.model.Game game = wh.game.bll.Game.getInstance().get(gameId, refMyErr);
				if (game != null) {
					tempMap.put("GameRate", String.valueOf(game.getGameMoneyRate()));
					tempMap.put("GameMoneyType", GameMoneyType.parse(game.getGameMoneyType()).toString());
				}

				final String channelUserId = tempMap.get("UserId");

				// 登录,并返回游老大id，以供充值userid

				MessageReturn mr = UserUnite.getInstance().login(gameId, serverId, channelId, channelSubId, channelUserId, mobiledeviceId,
						statisticAnalysisId);
				if (!mr.findErr()) {
					tempMap.put("UldUserId", String.valueOf(((wh.member.model.User) mr.getRetObject()).getUserId()));
					tempMap.put("UldUserName", ((wh.member.model.User) mr.getRetObject()).getUserName());
				}

				// 针对于傲剑游戏提供的时间签名
				setTimeSignForAojian(messageReturn, gameId, tempMap, channelId, channelUserId);
			}
		}
		return messageReturn;
	}
	
	

	private void setTimeSignForAojian(MessageReturn messageReturn, int gameId, Map<String, String> tempMap, int channelId,
			String channelUserId) {
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
			String userid = channelUserId;
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

	public MessageReturn createOrder(wh.order.model.Order model, String channelUserId, int channelId, int channelSubId, String playerId,
			String refresh_token) {

		String userName = UserUnite.getUserNameByChannelUserId(channelId, channelSubId, channelUserId);

		HashMap<String, Object> reMapandmrt = new HashMap<String, Object>();
		RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);
		wh.member.model.User user = wh.member.bll.User.getInstance().getByName(userName, "", 1, refMyErr);
		int userId = 0;
		if (user != null) {
			userId = user.getUserId();
		}

		model.setUserId(user.getUserId());
		model.setPayAccount(BigDecimal.ZERO);
		model.setRealPayAccount(BigDecimal.ZERO);
		model.setChargedUserId(user.getUserId());
		model.setChargedUserName(user.getUserName());

		model.setCreateDate(new Date());
		model.setModifyDate(new Date());

		// 注：因从客户端传过来的ServerId实际为SequenceNumber，所以在此要转化一下
		model.setServerId(ServerTools.getServerIdBySequenceNumber(model.getGameId(), model.getServerId(), "User360-createOrder"));

		MessageReturn mrt = wh.order.bll.Order.getInstance().createUpdate(model);
		final int gameId = model.getGameId();
		HashMap<String, String> reMap = new HashMap<String, String>();
		reMap = gettoken(refresh_token, gameId);

		reMapandmrt.put("reMap", reMap);
		reMapandmrt.put("mrt", mrt);
		MessageReturn messageReturn = new MessageReturn();
		messageReturn.setRetObject(reMapandmrt);

		// 将数据写入OrderChannel表中
		// OrderChannel 需要的参数
		// localOrderId 本地订单编号ok
		// regChannelId 注册渠道编号---user.getChannelId()
		// ocChannelId 实际渠道编号---channel.txt
		// gameChannelId 游戏方渠道编号--->大渠道实际，小渠道7
		// status=1
		wh.order.model.OrderChannel orderChannelModel = new OrderChannel();
		orderChannelModel.setLocalOrderId(Utility.getInt(mrt.getRetObject()));
		orderChannelModel.setRegChannelId(user.getChannelId());
		orderChannelModel.setOcChannelId(channelId);
		orderChannelModel.setGameChannelId(channelId);
		orderChannelModel.setStatus((byte) 1);
		wh.order.bll.OrderChannel.getInstance().createUpdate(orderChannelModel);

		if (Utility.isEmpty(playerId)) {
			playerId = String.valueOf(userId);
		}
		wh.order.model.OrderLog orderLog = new wh.order.model.OrderLog();
		orderLog.setCreateDate(new Date());
		orderLog.setLogName("playerid");
		orderLog.setDescription(playerId);
		orderLog.setOrderId(Utility.getInt(mrt.getRetObject()));
		orderLog.setStatus((byte) 1);
		orderLog.setUserId(userId);
		wh.order.bll.OrderLog.getInstance().createUpdate(orderLog);

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

		model.setPayAccount(BigDecimal.ZERO);
		model.setRealPayAccount(BigDecimal.ZERO);

		model.setCreateDate(new Date());
		model.setModifyDate(new Date());

		// 注：因从客户端传过来的ServerId实际为SequenceNumber，所以在此要转化一下
		model.setServerId(ServerTools.getServerIdBySequenceNumber(model.getGameId(), model.getServerId(), "User360-createOrder"));

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

	private HashMap<String, String> gettoken(String refresh_token, int gameId) {

		checkSidUrl0801 = "https://openapi.360.cn/oauth2/access_token";
		HashMap<String, String> retokenmap = new HashMap();
		if (refresh_token == null) {
			System.err.println("refresh_token不能为空");
		}
		HashMap<String, String> data = new HashMap();
		data.put("grant_type", "refresh_token");
		data.put("refresh_token", refresh_token);
		/** 游戏在平台上分配的唯一标识 */
		String appKey_360 = Config.getConfig("360_appKey_" + gameId);
		/** 游戏平台给游戏分配的一个私密字符串,只有游戏开发商和平台知道，用于通信加密校验 */
		String appSecret_360 = Config.getConfig("360_appSecret_" + gameId);
		// 火影海贼王
		data.put("client_id", appKey_360);
		data.put("client_secret", appSecret_360);
		data.put("scope", "basic");
		String refreshtoken = refresh_token;

		try {
			refreshtoken = Util360.requestUrl(checkSidUrl0801, data, true);
			if (!Utility.isEmpty(refreshtoken)) {
				retokenmap = (HashMap<String, String>) JSONObject.parseObject(refreshtoken, HashMap.class);
				return retokenmap;
			} else {
				retokenmap.put("access_token", refresh_token);
				retokenmap.put("refresh_token", refresh_token);
				System.out.println("refresh_token生成错误");
				return retokenmap;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retokenmap;
	}
}