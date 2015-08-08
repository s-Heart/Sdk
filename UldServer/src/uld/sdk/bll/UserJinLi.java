package uld.sdk.bll;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import uld.sdk.model.MessageReturn;
import uld.sdk.tools.Base64;
import uld.sdk.tools.Config;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RSAEncrypt;
import uld.sdk.tools.RefObject;
import uld.sdk.tools.Utility;
import wh.order.model.OrderChannel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UserJinLi {

	private static final String GIONEE_PAY_INIT = "https://pay.gionee.com/order/create";
	private static final String API_KEY = "A696AE38311E43769560A4ACB9956BE2";
	private static final String PRIVTE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJoTbCewaIkrArnhzCM9OiFl0TzE8icRO29egGiZCPo0waim7tpuveXu1R1FuMYmfIyp+kAAf9EcZsBoKmcrg9VJBEyF/kCNJE0k07EZOK2t5Xs+mdJ1v2pZj1pqsXssXOr2PIWY9pEavQsvltoirzBsDwclTe4B1sCA/v2L4sYHAgMBAAECgYAzlS8PdpL44UpvWvYSEiyFA+ZyNulvROimF3hcDGgiBEmVohWzUYIIGdaFj1MWG8p0+W9LwB8KrjyETBtuIPZoi4k0chXkd6HdA5UfikdlqtV6svaRPNglumW6IhTUN/jdZtUdzaGrq7szqr6hgqaBNDVD68UObSmkH0FKA/JBGQJBAM8YRj9EFxkVMn/pcgW3X9Ai/U9KaGsmkb5KOvFHq5GLqZkQKrxM3S2QUjZd2oul+9+WvI82CY/8jyoMORUz+tMCQQC+de+4JP8GDXCSPsP0VHFSUWElQiJTwqsAwvnKPFC1rOUBn2/vytXf6Pm6yRpFR9DLKphN2/ERC4UvQwasv199AkBDDFbh8MeRpV0+MHRsCVyJ5Goim7KGzmvtMdc3er6/VnpIRgWwBSqur8UOOQCiqdCMKR9PNm2OInSvZlms1bFlAkEAoIV/o8Cym6RKI0f5GXzuAYYLDT7e9Z9VRpANvQ/2qhfs7uA0lHJsrYmKqI2Dsa5kYEcnFD/xvf7qojmob4HfdQJBAIu+YK/UY3K+k2ab2Tx/63HQdimf3SosrYDn/0sBprHAHe0vVCzJRHADBWPj77NAFtqaVIFefWMDOhJWobQ/evg=";

	
	//---------金立token验证中需要的参数
	static String verify_url = "https://id.gionee.com/account/verify.do";
	static String apiKey = API_KEY; // 商户申请获取的APIKey
	static String secretKey ="66ABABAEADCD43B9AF6005C8A5ED7300";// 商户申请获取的SecretKey
	static String host = "id.gionee.com";
	static String port = "443";
	static String url = "/account/verify.do?s=true";
	static String method = "POST";
	/**
	 * 修改bug,处理注册用户MobileDeviceId=0 ,添加设备Id识别的操作,如果设备Id为0,则根据新传递过来的sDeviceId
	 * 来对mobiledeviceId与statisticAnalysisId赋值之后再做其他相关操作.
	 * 
	 * @param gameId
	 * @param serverId
	 * @param mobileDeviceId
	 * @param statisticAnalysisId
	 * @param channelId
	 * @param channelSubId
	 * @param sid
	 * @param uid
	 * @param sMobileDeviceName
	 * @return
	 */
	public MessageReturn login(final int gameId, final int serverId, int mobileDeviceId, int statisticAnalysisId, final int channelId,
			final int channelSubId, String amigoToken, final String playerId, String sMobileDeviceName) {
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

		return login(gameId, serverId, mobileDeviceId, statisticAnalysisId,
				channelId, channelSubId,amigoToken,playerId);
		
	}
	
	public MessageReturn login(final int gameId, final int serverId,
			int mobileDeviceId, int statisticAnalysisId, final int channelId,
			final int channelSubId,final String amigoToken, final String playerId) {
		
		MessageReturn messageReturn = new MessageReturn();
		if(apiKey.equals("")) {
			System.out.println(String.format("apiKey is empty!"));
			
		}
		if(secretKey.equals("")) {
			System.out.println(String.format("secretKey is empty!"));
			
		}
		
		//进行token的验证
		String result = verify(amigoToken);
		JinLiToken token = new JinLiToken();
		token = JSON.parseObject(result, JinLiToken.class);
//		if(!token.getR().isEmpty() && !token.getWid().isEmpty()){
//				messageReturn.setErrMsg("error_msg:" + "token未验证通过"); 
//		}else{
		final int fmobileDeviceId = mobileDeviceId;
		final int fstatisticAnalysisId = statisticAnalysisId;
		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("UserId", playerId);
		tempMap.put("UserName", "");
		setTimeSignForAojian(messageReturn, gameId, tempMap, channelId, playerId);//进行时间的签名

		new Thread() {
			public void run() {
				UserUnite.getInstance().login(gameId, serverId, channelId,
						channelSubId, playerId, fmobileDeviceId,
						fstatisticAnalysisId);
			}
		}.start();
//		}
		return messageReturn;
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

		Date createDate = new Date();
		model.setCreateDate(createDate);
		model.setModifyDate(new Date());

		// 注：因从客户端传过来的ServerId实际为SequenceNumber，所以在此要转化一下
		model.setServerId(ServerTools.getServerIdBySequenceNumber(model.getGameId(), model.getServerId(), "UserJinLi-createOrder"));

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
		orderLog.setOrderId(Utility.getInt(messageReturn.getRetObject()));
		orderLog.setStatus((byte) 1);
		orderLog.setUserId(userId);
		wh.order.bll.OrderLog.getInstance().createUpdate(orderLog);

		// 向金立发送支付订单
		String payvaule = String.valueOf(model.getRealPayAccount());
		String orderid = String.valueOf(Utility.getInt(messageReturn.getRetObject()));
		String orederjson = tojinlicreateorder(playerId, orderid, createDate, payvaule);

		// 为了给客户端传更多的值 把messageReturn 放入mReturn 中的reMapandmrt的容器中;
		// 到客户端进行解析mReturn 从而得到orederjson 和messageReturn;
		MessageReturn mReturn = new MessageReturn();

		HashMap<String, Object> reMapandmrt = new HashMap<String, Object>();

		reMapandmrt.put("orederjson", orederjson);
		reMapandmrt.put("messageReturn", messageReturn);

		mReturn.setRetObject(reMapandmrt);
		return mReturn;

	}

	private String tojinlicreateorder(String playerId, String orderid, Date createDate, String payvaule) {
		// TODO Auto-generated method stub

		// 用户id
		String player_id = playerId;
		// api_key
		String api_key = API_KEY;
		// 商品总金额
		String deal_price = payvaule;
		// 付款方式：1为立即付款，2为货到付款
		String deliver_type = String.valueOf(1);
		// 订单有效时间 默认值为10 用默认值则不参与签名
		// String expire_time ="";
		// 发给金立 uld的回调地址
		String notify_url = "http://payunite.ulaoda.com/JinLiCallBack.ashx";
		// 订单号
		String out_order_no = orderid;
		double countpay = 10.0;
		//数量
		double count =countpay * Double.parseDouble(payvaule);
		// 商品名称
		String subject = count +"金锭";
		// 订单创建时间
		String submit_time = SubmitTimeUtilJinLi.toString(createDate);

		String total_fee = payvaule;
		String sign = "";
		String signContent = api_key + deal_price + deliver_type + notify_url + out_order_no + subject + submit_time + total_fee;

		System.out.println("signContent=" + signContent.length());

		// 生成json字符串
		JSONObject jspost = null;
		try {
			sign = RSASignatureJinLi.sign(signContent.toString(), PRIVTE_KEY, "utf-8");

			jspost = new JSONObject();
			jspost.put("player_id", player_id);
			jspost.put("api_key", api_key);
			jspost.put("deal_price", deal_price);
			jspost.put("deliver_type", deliver_type);
			jspost.put("notify_url", notify_url);
			jspost.put("out_order_no", out_order_no);
			jspost.put("subject", subject);
			jspost.put("submit_time", submit_time);
			jspost.put("total_fee", total_fee);
			jspost.put("sign", sign);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 发送post 请求
		String boby = jspost.toJSONString();
		String result = "";
		try {
			System.out.println("GIONEE_PAY_INIT=" + GIONEE_PAY_INIT);
			System.out.println("boby=" + boby);
			result = HttpUtilsJinLi.post(GIONEE_PAY_INIT, boby);
			uld.sdk.tools.LogHelper.log("Jinli-http-url:" + GIONEE_PAY_INIT);
			uld.sdk.tools.LogHelper.log("Jinli-http-boby:" + boby);
			uld.sdk.tools.LogHelper.log("Jinli-http-result:" + result);
			System.out.println("result=" + result);
			if (!Utility.isEmpty(result)) {
				return result;
			} else {
				uld.sdk.tools.LogHelper.log("Jinli-http-result-error:" + result);
				return "";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	// 若为空null或者是\t \n \f \r 这些值返回为真
	static boolean isBlank(String str) {

		if (null == str) {
			return true;
		}

		str = str.replace("\t", " ");
		str = str.replace("\n", " ");
		str = str.replace("\f", " ");
		str = str.replace("\r", " ");
		for (int i = 0; i < str.length(); i++) {
			str = str.replace(" ", "");
		}
		str = str.replace(" ", "");
		boolean t = (null == str || str.length() == 0 || "null".equalsIgnoreCase(str));
		return t;

	}
	// -------------------------金立提供代码请求token验证用户---------------------------//
	

		// verify 方法封装了 验证方法，调用此方法即可完成帐号安全验证
		public static String verify(String amigoToken) {
			HttpsURLConnection httpURLConnection = null;
			OutputStream out;
			String result = null;
			TrustManager[] tm = { new MyX509TrustManager() };
			try {
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new java.security.SecureRandom());
				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();
				URL sendUrl = new URL(verify_url);
				httpURLConnection = (HttpsURLConnection) sendUrl.openConnection();
				httpURLConnection.setSSLSocketFactory(ssf);
				httpURLConnection.setDoInput(true); // true表示允许获得输入流,读取服务器响应的数据,该属性默认值为true
				httpURLConnection.setDoOutput(true); // true表示允许获得输出流,向远程服务器发送数据,该属性默认值为false
				httpURLConnection.setUseCaches(false); // 禁止缓存
				int timeout = 30000;
				httpURLConnection.setReadTimeout(timeout); // 30秒读取超时
				httpURLConnection.setConnectTimeout(timeout); // 30秒连接超时
				String method = "POST";
				httpURLConnection.setRequestMethod(method);
				httpURLConnection.setRequestProperty("Authorization",
						builderAuthorization());
				out = httpURLConnection.getOutputStream();
				out.write(amigoToken.getBytes());
				out.flush();
				out.close();
				InputStream in = httpURLConnection.getInputStream();
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				byte[] buff = new byte[1024];
				int len = -1;
				while ((len = in.read(buff)) != -1) {
					buffer.write(buff, 0, len);
				}
				System.out.println(String.format("verify sucess response:%s",
						buffer.toString()));

				result = buffer.toString();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * 用于token的验证
		 * 
		 * @author cl
		 * 
		 */
		static class MyX509TrustManager implements X509TrustManager {

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}

		}

		private static String builderAuthorization() {

			Long ts = System.currentTimeMillis() / 1000;
			String nonce = StringUtil.randomStr().substring(0, 8);
			String mac = CryptoUtility.macSig(host, port, secretKey, ts.toString(),
					nonce, method, url);

			StringBuilder authStr = new StringBuilder();
			authStr.append("MAC ");
			authStr.append(String.format("id=\"%s\"", apiKey));
			authStr.append(String.format(",ts=\"%s\"", ts));
			authStr.append(String.format(",nonce=\"%s\"", nonce));
			authStr.append(String.format(",mac=\"%s\"", mac));
			return authStr.toString();
		}

		/*
		 * 制作mac的签名
		 */
		static class CryptoUtility {

			private static final String MAC_NAME = "HmacSHA1";

			public static String macSig(String host, String port, String macKey,
					String timestamp, String nonce, String method, String uri) {
				// 1. build mac string
				// 2. hmac-sha1
				// 3. base64-encoded

				StringBuffer buffer = new StringBuffer();
				buffer.append(timestamp).append("\n");
				buffer.append(nonce).append("\n");
				buffer.append(method.toUpperCase()).append("\n");
				buffer.append(uri).append("\n");
				buffer.append(host.toLowerCase()).append("\n");
				buffer.append(port).append("\n");
				buffer.append("\n");
				String text = buffer.toString();

				byte[] ciphertext = null;
				try {
					ciphertext = hmacSHA1Encrypt(macKey, text);
				} catch (Throwable e) {
					e.printStackTrace();
					return null;
				}

				String sigString = Base64.encode(ciphertext);
				return sigString;
			}

			public static byte[] hmacSHA1Encrypt(String encryptKey,
					String encryptText) throws InvalidKeyException,
					NoSuchAlgorithmException {
				Mac mac = Mac.getInstance(MAC_NAME);
				mac.init(new SecretKeySpec(StringUtil.getBytes(encryptKey),
						MAC_NAME));
				return mac.doFinal(StringUtil.getBytes(encryptText));
			}

		}

		static class StringUtil {
			public static final String UTF8 = "UTF-8";
			private static final byte[] BYTEARRAY = new byte[0];

			public static boolean isNullOrEmpty(String s) {
				if (s == null || s.isEmpty() || s.trim().isEmpty())
					return true;
				return false;
			}

			public static String randomStr() {
				return CamelUtility.uuidToString(UUID.randomUUID());
			}

			public static byte[] getBytes(String value) {
				return getBytes(value, UTF8);
			}

			public static byte[] getBytes(String value, String charset) {
				if (isNullOrEmpty(value))
					return BYTEARRAY;
				if (isNullOrEmpty(charset))
					charset = UTF8;
				try {
					return value.getBytes(charset);
				} catch (UnsupportedEncodingException e) {
					return BYTEARRAY;
				}
			}
		}

		static class CamelUtility {
			public static final int SizeOfUUID = 16;
			private static final int SizeOfLong = 8;
			private static final int BitsOfByte = 8;
			private static final int MBLShift = (SizeOfLong - 1) * BitsOfByte;

			private static final char[] HEX_CHAR_TABLE = { '0', '1', '2', '3', '4',
					'5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

			public static String uuidToString(UUID uuid) {
				long[] ll = { uuid.getMostSignificantBits(),
						uuid.getLeastSignificantBits() };
				StringBuilder str = new StringBuilder(SizeOfUUID * 2);
				for (int m = 0; m < ll.length; ++m) {
					for (int i = MBLShift; i > 0; i -= BitsOfByte)
						formatAsHex((byte) (ll[m] >>> i), str);
					formatAsHex((byte) (ll[m]), str);
				}
				return str.toString();
			}

			public static void formatAsHex(byte b, StringBuilder s) {
				s.append(HEX_CHAR_TABLE[(b >>> 4) & 0x0F]);
				s.append(HEX_CHAR_TABLE[b & 0x0F]);
			}

		}
}
