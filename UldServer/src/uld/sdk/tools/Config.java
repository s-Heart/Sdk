package uld.sdk.tools;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
	// 说明如果要读取配置文件
	public static String ConfigFileNameFullName = System.getProperty("user.dir") + "/appConfig.properties";

	// 支付宝快捷支付密钥及参数
	public static final String PARTNER = "2088701769240000";
	public static final String SELLER = "2088701769240000";
	public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALadvb5pBuVMit15ghtahpyo6/g5U6DczYgQSFMTIp7odfXZa8LyhszyjsQOhAKlWae4KHOsLsgwJPhfTQaz8Zk6DNghShnIdlwBt5Q6lmpyC4S4jxfWb/ZA9+iQZhDnQEfZ5KwI6H4aaoDgrSmTvn0tRzsTZqYRHFwhxxH5qLnvAgMBAAECgYAFvi4yZ8EkwuR3FJQn4g/rq1BIbR0ZKPh5t/FeU3K6Rps3ERBXgCMHHPWqar5yCtsn8B2tjZOnDgSLnwugE1acqlpn5vUdo/Iw2ZzM3+oL3i8HT/BAbdyHPI2E8Drb+xuzRCqH69YZy6FwDZ+0i3Q7X+MUCyfMrvjePXZ4OdMASQJBAOc7DSi+hFhw6xYlCQr4yvaeVZwic49LpiaaN1xs/hqsz+8wMl488Mzl8en0oAzNiu7YhbiDzB/T0UrIJ1P/4NUCQQDKLY2wgBT+wuOXhk95x6Ivq5nQFqRDi4VBZhPbrwRlBfHoviclBAlMno7U9x5v3ZcumfFR9l9fw1x3wvYJvvGzAkAw5BJ/N/FBxfZfA0urLzmy9X2CeuJWPRePd/IWr9D2kO2SVG2B8X60xDXnUtr4eglTQSLdBbkGTcIl/rYaE7NVAkBiOLVQXTlV95qYP7EWyczaefS06nsNMZggiJX3MVTiO3wmBVOBIS/fYFylzBSmZjIPesyJdDcrj/65VCcx3ukPAkEAvTdufMWwlbCZAkTH9mEJZj2cxtR1HqzT6MNxmhSP5yLMAZKyxwZ/xSxXP27/14JDB/b8sEcMinFnZcWsblohiA==";
	public static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfzIVQ+qLam5C3Xfc85egmEoNK4npOUbRqMxdiXb20Hb70ZhU8YxvYXbKTY0m4qQLcKrFLk4rO2qsnVoC/GAXcjhKndUUDtfYmwWSJufn4TEmYC222m8NIEa5IXCmdu8PLaPrpFidtUr+J2bMWB1KuvFCgSEUqgdN07H5NpjoLKQIDAQAB";

	// // 合作商户ID。用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。
	// public static final String PARTNER = "2088101568358171";
	// // 商户收款的支付宝账号
	// public static final String SELLER = "2088101568358171";
	// // 商户（RSA）私钥
	// public static final String RSA_PRIVATE =
	// "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANEliZbjWsu4KpsHAFAMAJoa9x5nfS8t0+xhEAvQ5qheFPv+rEwRne8Mr/D+a3uH3iEz7890kGp6lnPrLKhvSWbKXJmZnPWjYnyVnANL48hQZfvxclUF/Qkxfe2EIttZbuM8nIOIyh5Dfx398kWHVS6TPZrda/VJm9C3cph/b0a3AgMBAAECgYAtQJX4k9C9a2esi2NB7pbiwRre9T1cy+mip421QMnnfBPGQmA9RUKKyo/28NWIsOka/gXROUNWBpgvFJ9hAlM7CjcKzqTV4ph0IQ5XSo0TWvYaJq9aJAelCH+RMa0/If58AIOrJ+qGYESRO4386xAhxXQb89RpTBEIy3M0LxtlIQJBAO7rCW1RXSRtT7Aj5Fb4FmOhlukqL7yLNlhuz2KY+axcyFqzihM39zDB1sF7XYN94b21elLAZntJkufzW67Gk1sCQQDgGZZs3IU5lvVokOJaTZVTynDKAYFMlv6t76yD7DkRtVh/2U9UZOew1fLQvBRDcWP8flpQdXLsLQkV1XfYP8TVAkEAoK5aDLdn2RPbQC8jZoo7JI6MnAvPRxKpXhhISZtwb0eHR9jvx7Uf/h6ffEinv8NtitT+i6DyS4BT2MOGqajLeQJAFHVhjTiolPRaHRy0/Wd9zXN6zoZKppJWV8y8pCKJpzs2BB3zpxG7MSKnEzVIaEvOw/tJBXVjc3o9DRg646wWrQJBAOBeQVevEq1m8dmEgiQDeajUolJeItwTw3eksrH8CypEWOgGsIdafGCWexSBs68+3zs2mR32cPquPmT99HSoVUI=";
	// // 支付宝（RSA）公钥 用签约支付宝账号登录ms.alipay.com后，在密钥管理页面获取。
	// public static final String RSA_ALIPAY_PUBLIC =
	// "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDPVyp1OBahVYt/pbVsT1DAzbZNRKREqQDz2S18wM1h+P5Of8XNxTTtqTE12Tgriij7WfIUQtnBq/dJaDx5OJ7MOubKvWEzdhJUHCWCnKmI5e/UFrDj169mRVO3k+HeILRnKUiFuRcAFiyVMKnTtp2/GYcvt+tHNCIcfN1NKFofFwIDAQAB";

	public static final String GAME_TIMESIGN_RSA_PRIVATE_KEY_8 = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAL5Y2etVUdARwTdXCK4490Axnk0aCzgOKSfq935dNRG0DCf/zqN3sSfzoXizrWmrvOMAE2xWRryWl6rQOr1dtc5AA8ckG3g/IQoD0Qn1cx4YXrdR4lg+Y3lWCsa0sl0+gUtjkB1OI1/e/r4IfIigOzQ1azDc5Juiy0xuDCbtnkD7AgMBAAECgYEAjuu39HODkm9CXIMFCYvMWm7nJY1Ajz5SuJ48Zq0lg71kw0PXq/XbFtxfiXmf45AHq4oLC3Vcd427I45mDHmoX4ApaaKozk/+QOGdBuM1mattfqX2D/MDqbUNa2yVo1lN9/4KtWf9+vKIusL70+iYTOuNAw946M/9cN9qBxZfkcECQQDvcz5ncjfLA9fsqYwhHtAWf7+5RuUifZyJn4fvgpqjGth5qP+1zzE+xo+5mQA79w+8iXpz0CIx+zMR+MtgUxotAkEAy4DK7pdvBXdhSZDpFLuJZINPj60baV/IRsi4NG3l59kPwXn41MizY3pT9EqBKySGVzOgXtzT9TZ6WVBLxrGIxwJAbmnpwLG+YHLpQkv8PdmDgpxtv/Wc+waJBDz35c2HmOZsI811/FQKFTRiWebfYMdQDlQmjvjOi9WVexgKT+SCEQJBAItIOBx6qvLr1Ttbohudi5iuwuqMSWGiu9mukVzZBuzbRIxgZe2E/DqOxB1ETQnxMr1GSXID5FJFmhG9AlAYvIsCQQDEVQ1hO9q2nOeAkD5w6ly3G72uI1jBduzRLyCLUte+QmNKA+0b2LoIyCzgZk9ghlIU3m86TLpVNxGEyha4uLId";
	public static final String GAME_TIMESIGN_RSA_PUBLIC_KEY_8 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+WNnrVVHQEcE3VwiuOPdAMZ5NGgs4Dikn6vd+XTURtAwn/86jd7En86F4s61pq7zjABNsVka8lpeq0Dq9XbXOQAPHJBt4PyEKA9EJ9XMeGF63UeJYPmN5VgrGtLJdPoFLY5AdTiNf3v6+CHyIoDs0NWsw3OSbostMbgwm7Z5A+wIDAQAB";
	
	public static String DateFormat_Database = "yyyy-MM-dd HH:mm:ss.SSS";
	public static String DateFormat_Database_Year = "yyyy";
	public static String DateFormat_Database_Month = "yyyyMM";
	public static String DateFormat_Database_Day = "yyyyMMdd";
	public static String DateFormat_Normal = "yyyy-MM-dd HH:mm:ss";

	// public static String CONNECTION_STRING_ULD =
	// "jdbc:sqlserver://127.0.0.1:1433;databaseName=ULD;user=sa;password=q`1234;";
	public static String CONNECTION_STRING_ULD = getConfig("ConnectionString");
	public static String UldServerWelcome = getConfig("UldServerWelcome");
	public static final int SERVERPORT = uld.sdk.tools.Utility.getInt(getConfig("SERVERPORT"));

	public static String getConfig(String key) {
		if (key == null || key.equals("") || key.equals("null")) {
			return "";
		}

		String result = "";
		try {
			FileInputStream in = new FileInputStream(ConfigFileNameFullName);
			Properties per = new Properties();
			per.load(in);

			result = new String(per.getProperty(key).getBytes("ISO-8859-1"), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
