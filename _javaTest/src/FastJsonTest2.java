import uld.sdk.tools.Base64;
import uld.sdk.tools.Config;
import uld.sdk.tools.RSAEncrypt;

import com.alibaba.fastjson.JSON;


public class FastJsonTest2 {

	public static void main(String[] args) {
		Student student=new Student();
		student.setName("tony");
		student.setAge("24");
		student.setGender("male");
		student.setTimeSign("asasdsasadew");
		String result=JSON.toJSONString(student);
		System.out.println(result);
		String timeSign = "";

		RSAEncrypt rsaEncrypt = new RSAEncrypt();

		try {
			rsaEncrypt.loadPublicKey(Config.GAME_TIMESIGN_RSA_PUBLIC_KEY_8);
			rsaEncrypt.loadPrivateKey(Config.GAME_TIMESIGN_RSA_PRIVATE_KEY_8);

			byte[] encodeBytes = rsaEncrypt.encrypt(rsaEncrypt.getPrivateKey(),
					result.getBytes("UTF-8"));
			timeSign = Base64.encode(encodeBytes);

			// Java利用私钥解密
			byte[] encodeByte = rsaEncrypt.decrypt(rsaEncrypt.getPublicKey(),
					Base64.decode(timeSign));
			String timeString2 = RSAEncrypt.byteArrayToString(encodeByte);
			System.out.println("decode timeSign result is: " + timeString2);
			System.out.println("orgin string is :"
					+ new String(encodeByte, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
