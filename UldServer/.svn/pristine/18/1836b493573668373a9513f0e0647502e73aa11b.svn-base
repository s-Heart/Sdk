package uld.sdk.bll;

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
import uld.sdk.tools.HttpRequest;
import uld.sdk.tools.LogHelper;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RSAEncrypt;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;
import wh.order.model.OrderChannel;

import com.alibaba.fastjson.JSON;

/**
 * 文档版本：2.9.0 SDK版本：2.0 最后更新：2014-2-15 19:51:58</br>
 * 
 * 注意事项</br> 1.如果需要解析，可能会在服务端添加一个新的jar包，jdom.jar</br> 2.发送GET请求出现异常！Server
 * returned HTTP response code: 406,与联想技术沟通，让其提供测试的URL地址</br>
 * 
 * 
 * @author 史少杰
 * 
 */
public class UserLenovo {
	/** OAuth验证接口地址 */
	private static String OAUTH_URL = "http://passport.lenovo.com/interserver/authen/1.2/getaccountid";

	/** realm：服务安全域标识 */
	private static String REALM = "10000630.realm.lenovoidapps.com";

	/**
	 * token用来登陆验证来确定ChannelUserId 其他参数供游老大服务端登陆
	 * 
	 * @param gameId
	 * @param serverId
	 * @param mobiledeviceId
	 * @param statisticAnalysisId
	 * @param channelId
	 * @param channelSubId
	 * @param sessionId
	 * @param uid
	 * @param appid
	 * @param appKey
	 * @param deviceName
	 * @return
	 */
	public MessageReturn login(final int gameId, final int ServerId, int mobileDeviceId, int statisticAnalysisId, final int channelId,
			final int channelSubId, final String channelUserId, String sMobileDeviceName, String token) {

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

		final int fmobileDeviceId = mobileDeviceId;
		final int fstatisticAnalysisId = statisticAnalysisId;

		// 开始token验证---------------------------------------
		String url = OAUTH_URL;
		String param = "lpsust=" + token + "&realm=" + REALM;
		String xmlDoc = null;
		try {
			// xmlDoc = Utility.getWebContent(url);
			xmlDoc = HttpRequest.sendGet(url, param);
		} catch (Exception e) {
			LogHelper.log(Level.WARNING, e.getMessage());
		}

		MessageReturn messageReturn = new MessageReturn();
		if (xmlDoc != null) {
			if (xmlDoc.contains("<AccountID>")) {
				final String usergameid = xmlDoc.substring(xmlDoc.indexOf("<AccountID>") + 11, xmlDoc.indexOf("</AccountID>"));
				String userName = xmlDoc.substring(xmlDoc.indexOf("<Username>") + 11, xmlDoc.indexOf("</Username>"));
				System.out.println(usergameid);
				System.out.println(userName);
				// 返回成功
				Map<String, String> tempMap = new HashMap<String, String>();
				tempMap.put("usergameid", usergameid);
				tempMap.put("UserName", userName);

				// 模拟登录
				new Thread() {
					public void run() {
						LogHelper.log("UserLenovo->login thread begin" + String.valueOf(ServerId));
						UserUnite.getInstance().login(gameId, ServerId, channelId, channelSubId, usergameid, fmobileDeviceId,
								fstatisticAnalysisId);
						LogHelper.log("UserLenovo->login thread end" + String.valueOf(ServerId));
					}
				}.start();

				// 针对于傲剑游戏提供的时间签名
				setTimeSignForAojian(messageReturn, gameId, tempMap, channelId, usergameid);
			}
		} else {
			messageReturn.setErrMsg("请求格式错误");
		}
		return messageReturn;
	}

	// 针对于傲剑游戏提供的时间签名，RSA签名之后将值返回给客户端
	private void setTimeSignForAojian(MessageReturn messageReturn, final int gameId, Map<String, String> tempMap, int channelid,
			String usergameid) {
		if (gameId == 8) {
			RefObject<MyErr> refMyErr = new RefObject<MyErr>(null);
			;
			// 其中
			// timesign：
			// RSA({"time":"2013-11-11 15:26:32","addValue":"1","channelid":"13","userid":"uld123456","MD5":"8C5EABE5012EA60BEF37742D54E9C607"})
			// "MD5" = MD5(time + addValue + channelid + userid);
			String time = Utility.getFormatDate();
			int addValue = wh.member.bll.HBUser.getInstance().getMaxHbUserId(refMyErr);
			String channelId = String.valueOf(channelid);
			String userid = usergameid;
			String md5String = Utility.encodeMD5(time + addValue + channelId + userid);
			TimeSignObject tso = new TimeSignObject();
			tso.setTime(time);
			tso.setAddvalue(String.valueOf(addValue));
			tso.setChannelid(channelId);
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
	 * 创建游老大订单
	 * 
	 * @param model
	 * @param channelUserId
	 * @param channelId
	 * @param channelSubId
	 * @param playerId
	 * @return
	 */
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
		model.setServerId(ServerTools.getServerIdBySequenceNumber(model.getGameId(), model.getServerId(), "UserLenovo-createOrder"));

		MessageReturn messageReturn = wh.order.bll.Order.getInstance().createUpdate(model);

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
