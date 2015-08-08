import java.util.Iterator;
import java.util.Vector;

import uld.sdk.tools.Base64;
import uld.sdk.tools.Config;
import uld.sdk.tools.RSAEncrypt;

public class RsaTest3 {
	public static void main(String[] args) {
		

		String timeSign = "FXXrUUpx6qDExcGi0J+6lNfiJ52ujD8VJ/LQ+SpqSSxY19Lk5cCSHWeJbBDwKRAglQLr4dSdXYX34Jo5rHYLjDK5xIKUI7O+BF/oPrX+vmU5uSDsJFeyBLbhkr3fj21CCQ4WWoEZMmCzhJCAE8didcpHlTyoUEqHFg4vgPeLWy0=V5PhbBAaOCg+wK9X0VL1V+rPsQrTbk4bSMTZgXJKYGJoHwvLbqdD+etpqaUxgMSW5Ym9xL2UBI6vzdUUQpUSG7lWSPsgTKBQPyXXMIfUaY3HnEGWIwNwXLgTwTPbrLalOLt2nAc/x1NM7/EivG3BzyzfeEhJPV4azgbWjD3mU0Y=";
		Vector<String> signStrings=new Vector<String>();
		signStrings.add(timeSign.substring(0, 172));
		signStrings.add(timeSign.substring(172,timeSign.length()));
		RSAEncrypt rsaEncrypt = new RSAEncrypt();

		try {
			rsaEncrypt.loadPublicKey(Config.GAME_TIMESIGN_RSA_PUBLIC_KEY_8);
			rsaEncrypt.loadPrivateKey(Config.GAME_TIMESIGN_RSA_PRIVATE_KEY_8);

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
