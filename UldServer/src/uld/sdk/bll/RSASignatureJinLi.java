package uld.sdk.bll;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import uld.sdk.tools.Base64;

/**
 * RSA签名验签类
 * 
 * @author tianxb
 * @date 2012-12-1 下午2:23:27
 * @since 2.0.0
 */
public class RSASignatureJinLi {

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	/**
	 * RSA签名
	 * 
	 * @param content
	 *            待签名数据
	 * @param privateKey
	 *            商户私钥
	 * @param encode
	 *            字符集编码
	 * @return 签名值
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws InvalidKeyException
	 */
	public static String sign(String content, String privateKey, String encode) throws IOException, NoSuchAlgorithmException,
			InvalidKeySpecException, SignatureException, InvalidKeyException {

		// 问李甲具体怎么处理，一下为金立的处理方法

		String charset = "utf-8";
		if (!UserJinLi.isBlank(encode)) {
			charset = encode;
		}
		PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
		KeyFactory keyf = KeyFactory.getInstance("RSA");
		PrivateKey priKey = keyf.generatePrivate(priPKCS8);

		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
		signature.initSign(priKey);
		signature.update(content.getBytes(charset));
		byte[] signed = signature.sign();

		// byte 转string
		String s = Base64.encode(signed);
		return s;

	}

	/**
	 * <pre>
	 * <p>函数功能说明:RSA验签名检查</p>
	 * <p>修改者名字:guocl</p>
	 * <p>修改日期:2012-11-30</p>
	 * <p>修改内容:抛异常</p>
	 * </pre>
	 * 
	 * @param content
	 *            待签名数据
	 * @param sign
	 *            签名值
	 * @param publicKey
	 *            支付宝公钥
	 * @param encode
	 *            字符集编码
	 * @return 布尔值
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public static boolean doCheck(String content, String sign, String publicKey, String encode) throws NoSuchAlgorithmException,
			IOException, InvalidKeySpecException, InvalidKeyException, SignatureException {

		String charset = "utf-8";
		if (!UserJinLi.isBlank(encode)) {
			charset = encode;
		}

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64.decode(publicKey);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

		signature.initVerify(pubKey);
		signature.update(content.getBytes(charset));

		boolean bverify = signature.verify(Base64.decode(sign));
		return bverify;

	}
}
