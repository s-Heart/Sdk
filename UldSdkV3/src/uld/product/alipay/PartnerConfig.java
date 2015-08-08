package uld.product.alipay;

public class PartnerConfig {

//	 // 合作商户ID。用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
//	 public static final String PARTNER = "2088101568358171";
//	 // 商户收款的支付宝账号
//	 public static final String SELLER = "2088101568358171";
//	 // 商户（RSA）私钥
//	 public static final String RSA_PRIVATE =
//	 "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANEliZbjWsu4KpsHAFAMAJoa9x5nfS8t0+xhEAvQ5qheFPv+rEwRne8Mr/D+a3uH3iEz7890kGp6lnPrLKhvSWbKXJmZnPWjYnyVnANL48hQZfvxclUF/Qkxfe2EIttZbuM8nIOIyh5Dfx398kWHVS6TPZrda/VJm9C3cph/b0a3AgMBAAECgYAtQJX4k9C9a2esi2NB7pbiwRre9T1cy+mip421QMnnfBPGQmA9RUKKyo/28NWIsOka/gXROUNWBpgvFJ9hAlM7CjcKzqTV4ph0IQ5XSo0TWvYaJq9aJAelCH+RMa0/If58AIOrJ+qGYESRO4386xAhxXQb89RpTBEIy3M0LxtlIQJBAO7rCW1RXSRtT7Aj5Fb4FmOhlukqL7yLNlhuz2KY+axcyFqzihM39zDB1sF7XYN94b21elLAZntJkufzW67Gk1sCQQDgGZZs3IU5lvVokOJaTZVTynDKAYFMlv6t76yD7DkRtVh/2U9UZOew1fLQvBRDcWP8flpQdXLsLQkV1XfYP8TVAkEAoK5aDLdn2RPbQC8jZoo7JI6MnAvPRxKpXhhISZtwb0eHR9jvx7Uf/h6ffEinv8NtitT+i6DyS4BT2MOGqajLeQJAFHVhjTiolPRaHRy0/Wd9zXN6zoZKppJWV8y8pCKJpzs2BB3zpxG7MSKnEzVIaEvOw/tJBXVjc3o9DRg646wWrQJBAOBeQVevEq1m8dmEgiQDeajUolJeItwTw3eksrH8CypEWOgGsIdafGCWexSBs68+3zs2mR32cPquPmT99HSoVUI=";
//	 // 支付宝（RSA）公钥 用签约支付宝账号登录ms.alipay.com后，在密钥管理页面获取。
//	 public static final String RSA_ALIPAY_PUBLIC =
//	 "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPVyp1OBahVYt/pbVsT1DAzbZNRKREqQDz2S18wM1h+P5Of8XNxTTtqTE12Tgriij7WfIUQtnBq/dJaDx5OJ7MOubKvWEzdhJUHCWCnKmI5e/UFrDj169mRVO3k+HeILRnKUiFuRcAFiyVMKnTtp2/GYcvt+tHNCIcfN1NKFofFwIDAQAB";
//	 // 支付宝安全支付服务apk的名称，必须与assets目录下的apk名称一致
//	 public static final String ALIPAY_PLUGIN_NAME =
//	 "alipay_plugin_20120428msp.apk";

	/**这些参数全部从服务器获取*/
	// 合作商户ID。用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
	public static String PARTNER = "";
	// 商户收款的支付宝账号
	public static String SELLER = "";
	// 商户（RSA）私钥
	public static String RSA_PRIVATE = "";
			// 支付宝（RSA）公钥 用签约支付宝账号登录ms.alipay.com后，在密钥管理页面获取。
	public static String RSA_ALIPAY_PUBLIC = "";
	// 支付宝安全支付服务apk的名称，必须与assets目录下的apk名称一致
	public static final String ALIPAY_PLUGIN_NAME = "Alipay_msp_online.apk";
	
	public static final String ALIPAY_QIANBAO_NAME="Alipay_Qianbao.apk";

}
