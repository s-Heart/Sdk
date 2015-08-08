package com.lenovo.id.pay.sample;

public class Config {

	//调用支付接口使用的appid，申请流程详见文档
	//此参数集成时需要修改
	public static final String appid = "20000000000002200000";
	//支付密钥，申请流程详见文档
	//此参数集成时需要修改
	public static final String appkey = "MTUyOThDOUY2NTQ4RDQzREYyM0ExRTAzRDFEQjhBOEE4QzlGNjUxMk1UTXlNVGMwTnpRd05qVTBNakkwTkRZeU5Ea3JNVEl6TURrMk16WXlOREEzTXpnM05EazROVFk1T1RjME16ZzJNRFEzT0RJME1qQXpOak01";
	//接收支付接口通知的url
	//此参数集成时需要修改
	public static final String notifyurl = "http://192.168.0.140:8094/monizhuang/api?type=100";
	
	public static String TAG = "sample";
	
	//sample使用的realm，调用lenovo id接口时使用，开发者需要修改此参数为自己的realm，申请realm流程详见文档
	//此参数集成时需要修改
	public static final String RID = "lenovoid_example.lenovo.com";

}
