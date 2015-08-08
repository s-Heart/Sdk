import java.util.Iterator;
import java.util.Vector;

import uld.sdk.tools.Base64;
import uld.sdk.tools.Config;
import uld.sdk.tools.RSAEncrypt;

public class RsaTest2 {
	public static void main(String[] args) {
		// TimeSign = RSA(time + '_' + addValue)
		String timeString = "{\"time\": \"2013-11-11 15:26:32\",\"addValue\":\"1\",\"channelid\":\"11\",\"md5\":\"ef39736c92171f5f52a4911ba479e59a\",\"time\":\"2013-11-12 15:08:25\",\"userid\":\"253155922\"}";
		System.out.println("明文：" + timeString);
		System.out.println("明文长度：" + timeString.getBytes().length);

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
		Iterator<String> iterator = cutVector.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		String timeSign = "";

		RSAEncrypt rsaEncrypt = new RSAEncrypt();

		try {
			rsaEncrypt.loadPublicKey(Config.GAME_TIMESIGN_RSA_PUBLIC_KEY_8);
			rsaEncrypt.loadPrivateKey(Config.GAME_TIMESIGN_RSA_PRIVATE_KEY_8);

			//加密
			Vector<String> signStrings=new Vector<String>();
			for (int i = 0; i < cutVector.size(); i++) {
				byte[] encodeBytes = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(), cutVector.get(i).getBytes("UTF-8"));
				signStrings.add(Base64.encode(encodeBytes));
			}
			
			System.out.println("加密后每段长度为："+signStrings.get(0).length());
			System.out.println("加密后每段长度为："+signStrings.get(1).length());
			System.out.println("timeSign:"+timeSign);

			// Java利用私钥解密
			for (int i = 0; i < signStrings.size(); i++) {
				byte[] encodeByte = rsaEncrypt.decrypt(rsaEncrypt.getPrivateKey(), Base64.decode(signStrings.get(i)));
				String timeString2 = RSAEncrypt.byteArrayToString(encodeByte);
				System.out.println("decode timeSign result is: " + timeString2);
				System.out.println("orgin string is :" + new String(encodeByte, "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
