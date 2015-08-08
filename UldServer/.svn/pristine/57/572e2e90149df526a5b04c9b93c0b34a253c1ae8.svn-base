package com.upomp.pay.info;

import java.io.File;

import uld.sdk.tools.LogHelper;

public class Upomp_Pay_Info {

	// 正式环境需要修改参数
	// a.商户公钥证书、商户私钥证书
	// b.商户私钥证书密码、假名
	// c.前置服务器生产地址
	// d.前置验签公钥
	// e.生产环境的商户ID

	// -------------------------------------------------测试环境 Begin-------------------------
	// 商户公钥证书：这是商户公钥证书898000000000002.cer所读出公钥串。
	// public static String merchant_public_cer =
	// "MIIDuDCCAyGgAwIBAgIQaOXUUCzukC6m5EAlw0LdZjANBgkqhkiG9w0BAQUFADAkMQswCQYDVQQGEwJDTjEVMBMGA1UEChMMQ0ZDQSBURVNUIENBMB4XDTExMDgxNzAyNDU1M1oXDTEyMDgxNzAyNDU1M1owfjELMAkGA1UEBhMCQ04xFTATBgNVBAoTDENGQ0EgVEVTVCBDQTERMA8GA1UECxMITG9jYWwgUkExFDASBgNVBAsTC0VudGVycHJpc2VzMS8wLQYDVQQDFCYwNDFAWjIwMTEwODE3QDg5ODAwMDAwMDAwMDAwMkAwMDAwMDAwMzCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEAzD7Xy03ptoXR7jx3BxGD5GN2Fsivu/QprnYZF+Axby8LjVNGs97tHn8CHfXzvFMqAvsd4dkKzKrTG+dOmrlunYLGFrntIHl8Mx3liFkGLYFuJUy1+HF/hIRAMPIkDux6AAhbbCZlawdx5faHkM5OQg2KGeBcD+8NUJA6IYOunIUCAwEAAaOCAY8wggGLMB8GA1UdIwQYMBaAFEZy3CVynwJOVYO1gPkL2+mTs/RFMB0GA1UdDgQWBBSrHyJHSuW1lYnmxBBh6ulilF4xSTALBgNVHQ8EBAMCBPAwDAYDVR0TBAUwAwEBADA7BgNVHSUENDAyBggrBgEFBQcDAQYIKwYBBQUHAwIGCCsGAQUFBwMDBggrBgEFBQcDBAYIKwYBBQUHAwgwgfAGA1UdHwSB6DCB5TBPoE2gS6RJMEcxCzAJBgNVBAYTAkNOMRUwEwYDVQQKEwxDRkNBIFRFU1QgQ0ExDDAKBgNVBAsTA0NSTDETMBEGA1UEAxMKY3JsMTI3XzE1MzCBkaCBjqCBi4aBiGxkYXA6Ly90ZXN0bGRhcC5jZmNhLmNvbS5jbjozODkvQ049Y3JsMTI3XzE1MyxPVT1DUkwsTz1DRkNBIFRFU1QgQ0EsQz1DTj9jZXJ0aWZpY2F0ZVJldm9jYXRpb25MaXN0P2Jhc2U/b2JqZWN0Y2xhc3M9Y1JMRGlzdHJpYnV0aW9uUG9pbnQwDQYJKoZIhvcNAQEFBQADgYEARfg4YNGNETrJx+gy74UmPJ326T7H2hIE/lRcTyonq4NFpXmssau+TDV7btLUuhDxBGF1JysknFjeNAKMl9ZFGjKbOGGpQ7nfnEC8HIM7cp2n+gSlADRZbc8PHrqxLbjxsKoSUUFfh3PhfNXtWLfTxi5TT+hm6coV1K/EUX4t0AY=";
	// // 签名加密的私钥串和密码
	// public static String alias = "889ce7a52067a87f905c91f502c69644_d1cba47d-cbb1-4e29-9d77-8d1fe1b0dccd";
	// // 商户私钥898000000000002.p12的密码
	// public final static String password = "898000000000002";
	// // 私钥路径
	// public final static String PrivatePath = getFilePath() + "\\898000000000002.p12";
	// // 商户ID，测试环境：898000000000002；生产环境：商户真实的商户证书ID号
	// public static String merchantId = "898000000000002";
	// 定义前置网络通信地址
	// public final static String YinlianConnectionUrl = "http://211.154.166.219/qzjy/MerOrderAction/deal.action";
	// -------------------------------------------------测试环境 End-------------------------

	// -------------------------------------------------正式环境 Begin-------------------------
	// 商户公钥证书：这是商户公钥证书898000000000002.cer所读出公钥串。
	public static String merchant_public_cer = "MIID3jCCA0egAwIBAgIQSnJ1jAgRMbjOJbAalx0C7jANBgkqhkiG9w0BAQUFADAqMQswCQYDVQQGEwJDTjEbMBkGA1UEChMSQ0ZDQSBPcGVyYXRpb24gQ0EyMB4XDTEzMDQyMjA4NDExOVoXDTE1MDQyMjA4NDExOVowgZAxCzAJBgNVBAYTAkNOMRswGQYDVQQKExJDRkNBIE9wZXJhdGlvbiBDQTIxETAPBgNVBAsTCExvY2FsIFJBMRQwEgYDVQQLEwtFbnRlcnByaXNlczE7MDkGA1UEAxQyMDQxQDgzMTAwMDAwMDAwODMwNDBAODAyMzEwMDQ4OTkwNzQ1OlNJR05AMDAwMDA5MTYwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBALPTK5MXO/xQuknMH+79tAlsNA2eQzvl+ccnTM72b7k0nSbztnz7Zq5fPE6U/jHEHNMv57ZgiNpMG1x5IxllQKrlyIva7TKki3UmzjUHUWsNZvG3n8lhYZa43K+U+MSbEJl7M6CUtl9HVmbeNMeYosuAnnpxbe7+K0fYdckRelFfAgMBAAGjggGcMIIBmDAfBgNVHSMEGDAWgBTwje2zQbv77wgeVQLDMTfvPBROzTAdBgNVHQ4EFgQU+/f+/xI3/VMJrXduzu9iyZyFZP8wCwYDVR0PBAQDAgTwMAwGA1UdEwQFMAMBAQAwOwYDVR0lBDQwMgYIKwYBBQUHAwEGCCsGAQUFBwMCBggrBgEFBQcDAwYIKwYBBQUHAwQGCCsGAQUFBwMIMIH9BgNVHR8EgfUwgfIwVqBUoFKkUDBOMQswCQYDVQQGEwJDTjEbMBkGA1UEChMSQ0ZDQSBPcGVyYXRpb24gQ0EyMQwwCgYDVQQLEwNDUkwxFDASBgNVBAMTC2NybDEwNF84MTA0MIGXoIGUoIGRhoGObGRhcDovL2NlcnQ4NjMuY2ZjYS5jb20uY246Mzg5L0NOPWNybDEwNF84MTA0LE9VPUNSTCxPPUNGQ0EgT3BlcmF0aW9uIENBMixDPUNOP2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3Q/YmFzZT9vYmplY3RjbGFzcz1jUkxEaXN0cmlidXRpb25Qb2ludDANBgkqhkiG9w0BAQUFAAOBgQAkoXOP9q7l+qpBmRp4uPXyRIo1f+jhBYHzSTj+SCxNrgComq9Pj0gMORx0zL5ewIzEsiCoxIHQ30Gk57K4cH0p+So8Pe4ZbmBf2HUtrsEc7wH06QcDN3ELJ2CsZPYYzOzbNAvJa/wCj6uWNr9bqJXAgeytUelKYOsnyEIQ2DGoxA==";
	// 签名加密的私钥串和密码
	public static String alias = "c23b203ddb5a0376d1c45284232f6f03_e81e600b-87b4-4bfc-9e4f-ef3191227d50";
	// 商户私钥898000000000002.p12的密码
	public final static String password = "chuanglian";
	// 私钥路径
	public final static String PrivatePath = getFilePath() + "\\YinlianCertP12.p12";
	// 商户ID，测试环境：898000000000002；生产环境：商户真实的商户证书ID号 
	public static String merchantId = "802310048990745";

	// 定义前置网络通信地址
	// 正式环境地址：http://mobilepay.unionpaysecure.com/qzjy/MerOrderAction/deal.action
	public final static String YinlianConnectionUrl = "http://mobilepay.unionpaysecure.com/qzjy/MerOrderAction/deal.action";
	// -------------------------------------------------正式环境 End-------------------------

	/**
	 * 银联手机支付常用参数
	 */
	// 商户名称，商户可以自行修改
	public static String merchantName = "游老大";

	// 商户订单号，测试环境：建议输入9位；生产环境：最少8位，最多32
	public static String merchantOrderId = "90987040";
	// 商户订单时间：建议与当前系统时间同步，且与现实时间相同。
	public static String merchantOrderTime = "20120711173003";
	// 订单金额：单位为分。1为1分，10为1角；该处请不要出现特殊符号，例如“.”
	public static String merchantOrderAmt = "1";
	// 订单描述：对于该订单的描述
	public static String merchantOrderDesc = "充值0.01元";
	// 订单超时时间:建议传""。
	public static String transTimeout = "";
	// 该URL与payurl相同，填写为商户服务器地址。
	public static String backEndUrl = "http://payyinlian.ulaoda.com/callback.ashx";
	// 签名原串
	public static String originalsign;
	// 签名
	public static String xmlSign = "";

	// 查询类型
	public final static Byte[] type = { 01, 31, 04 };
	// 交易流水号
	public static String cupsQid = "201207111302472273732";
	// 日志标签
	public static String tag = "Sys";
	// *****************以下为商户调起插件参数***********************//
	public static String CMD_PAY_PLUGIN = "cmd_pay_plugin";
	// 商户包名
	public static String MERCHANT_PACKAGE = "uld.sdk";

	/**
	 * 得到要记录的日志的路径及文件名称
	 * 
	 * @return
	 */
	public static String getFilePath() {
		StringBuffer logPath = new StringBuffer();
		try {
			logPath.append(System.getProperty("user.dir"));
			logPath.append("\\yinlianfiles");
			// File file = new File(logPath.toString());
			// if (!file.exists()) {
			// file.mkdir();
			// }
		} catch (Exception e) {
			LogHelper.log("获取银联加密文件失败:" + e.getMessage());
		}
		return logPath.toString();
	}
}
