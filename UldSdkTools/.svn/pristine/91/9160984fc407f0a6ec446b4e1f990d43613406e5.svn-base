package uld.sdk.tools;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DESCBC {

	// 解密数据
	public String decrypt(String message, String key) throws Exception {

		byte[] bytesrc = convertHexString(message);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));

		cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

		byte[] retByte = cipher.doFinal(bytesrc);
		return new String(retByte);
	}

	public byte[] encrypt(String message, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
		return cipher.doFinal(message.getBytes("UTF-8"));
	}

	public byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}

		return digest;
	}

	public String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (plainText.length() < 2)
				plainText = "0" + plainText;
			hexString.append(plainText);
		}

		return hexString.toString();
	}

	/**
	 * 使用des加密
	 * @param key
	 *            生成密钥的key
	 * @param str
	 *            要加密的字符串
	 * @return
	 * @throws Exception
	 */
	public static String encrypDes(String key, String str) throws Exception {
		DESCBC des = new DESCBC();
		String jiami = java.net.URLEncoder.encode(str, "utf-8").toLowerCase();
//		return des.toHexString(des.encrypt(jiami, key)).toUpperCase();
		return Base64.encode(des.toHexString(des.encrypt(jiami, key)).toUpperCase().getBytes());
	}

	/**
	 * 使用des解密
	 * @param key
	 *            生成密钥的key
	 * @param desStr
	 * @return
	 */
	public static String parseDes(String key, String desStr) throws Exception {
		byte[] bytes = Base64.decode(desStr);
		desStr = new String(bytes, "ISO-8859-1");
		DESCBC des = new DESCBC();
		return java.net.URLDecoder.decode(des.decrypt(desStr, key), "utf-8");
	}
}