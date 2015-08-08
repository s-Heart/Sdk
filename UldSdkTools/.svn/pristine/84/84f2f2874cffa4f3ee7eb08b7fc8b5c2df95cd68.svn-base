package wh.promotion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class ChannelInterfaceParams implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public ChannelInterfaceParams(){
		;
	}
		
	private int channelInterfaceParamsId;
	/** 
	 * 获取渠道接口参数编号
	 * @return 渠道接口参数编号
	 */
	public int getChannelInterfaceParamsId()
	{
		return channelInterfaceParamsId;
	}
	
	/**
	 * 设置渠道接口参数编号
	 * @param 渠道接口参数编号
	 */
	public void setChannelInterfaceParamsId(int value)
	{
		channelInterfaceParamsId = value;
	}
	

	private wh.promotion.model.ChannelInterface channelInterface;
	/**
	 * 获取渠道接口
	 * @return 渠道接口
	 */
	public wh.promotion.model.ChannelInterface getChannelInterface(){
		return this.channelInterface;
	}
	
	/**
	 * 设置渠道接口
	 * @param channelInterface 渠道接口
	 */
	public void setChannelInterface(wh.promotion.model.ChannelInterface channelInterface){
		this.channelInterface = channelInterface;
	}
	
	private int channelInterfaceId;
	/**
	 * 获取渠道接口编号
	 * @return 渠道接口编号
	 */
	public int getChannelInterfaceId(){
		if(channelInterface != null){
			return channelInterface.getChannelInterfaceId();
		}else{
			return channelInterfaceId;
		}
	}
	
	/**
	 * 设置渠道接口编号
	 * @param 渠道接口编号
	 */
	public void setChannelInterfaceId(int value){
		if(channelInterface != null){
			channelInterface.setChannelInterfaceId(value);
		}else{
			channelInterfaceId = value;
		}
	}

	/**
	 * 获取渠道接口名称
	 * @return 渠道接口名称
	 */
	public String getChannelInterfaceName(){
		if(channelInterface != null){
			return channelInterface.getName();
		}else{
			return "";
		}
	
	}	
	
	private String paramName;
	/** 
	 * 获取参数名
	 * @return 参数名
	 */
	public String getParamName()
	{
		return paramName;
	}
	
	/**
	 * 设置参数名
	 * @param 参数名
	 */
	public void setParamName(String value)
	{
		paramName = value;
	}
	
	
	private String paramValue;
	/** 
	 * 获取参数值
	 * @return 参数值
	 */
	public String getParamValue()
	{
		return paramValue;
	}
	
	/**
	 * 设置参数值
	 * @param 参数值
	 */
	public void setParamValue(String value)
	{
		paramValue = value;
	}
	
	
	private String paramExplain;
	/** 
	 * 获取说明
	 * @return 说明
	 */
	public String getParamExplain()
	{
		return paramExplain;
	}
	
	/**
	 * 设置说明
	 * @param 说明
	 */
	public void setParamExplain(String value)
	{
		paramExplain = value;
	}
	

	private byte signType;
	/**
	 * 获取签名类型：0、签名类型；1、参与签名；2、不参与签名；
	 * @return 签名类型：0、签名类型；1、参与签名；2、不参与签名；
	 */
	public byte getSignType(){
		return signType;
	}
	
	/**
	 * 设置签名类型：0、签名类型；1、参与签名；2、不参与签名；
	 * @param 签名类型：0、签名类型；1、参与签名；2、不参与签名；
	 */
	public void setSignType(byte value){
		signType = value;
	}	
	
	private byte signIndex;
	/** 
	 * 获取签名顺序，升充排列，从1开始
	 * @return 签名顺序，升充排列，从1开始
	 */
	public byte getSignIndex()
	{
		return signIndex;
	}
	
	/**
	 * 设置签名顺序，升充排列，从1开始
	 * @param 签名顺序，升充排列，从1开始
	 */
	public void setSignIndex(byte value)
	{
		signIndex = value;
	}
	

	private byte paramType;
	/**
	 * 获取参数类型：0、参数类型；1、传入参数；2、返回参数；
	 * @return 参数类型：0、参数类型；1、传入参数；2、返回参数；
	 */
	public byte getParamType(){
		return paramType;
	}
	
	/**
	 * 设置参数类型：0、参数类型；1、传入参数；2、返回参数；
	 * @param 参数类型：0、参数类型；1、传入参数；2、返回参数；
	 */
	public void setParamType(byte value){
		paramType = value;
	}	

	private byte cIInType;
	/**
	 * 获取传入参数类型：0、传入参数类型；1、合作方用户帐号编号；2、广告验证码常量；3、用户编号；4、用户名称；5、签名；6、签名Key；
	 * @return 传入参数类型：0、传入参数类型；1、合作方用户帐号编号；2、广告验证码常量；3、用户编号；4、用户名称；5、签名；6、签名Key；
	 */
	public byte getCIInType(){
		return cIInType;
	}
	
	/**
	 * 设置传入参数类型：0、传入参数类型；1、合作方用户帐号编号；2、广告验证码常量；3、用户编号；4、用户名称；5、签名；6、签名Key；
	 * @param 传入参数类型：0、传入参数类型；1、合作方用户帐号编号；2、广告验证码常量；3、用户编号；4、用户名称；5、签名；6、签名Key；
	 */
	public void setCIInType(byte value){
		cIInType = value;
	}	

	private byte cIOutType;
	/**
	 * 获取传出参数类型：0、传出参数类型；
	 * @return 传出参数类型：0、传出参数类型；
	 */
	public byte getCIOutType(){
		return cIOutType;
	}
	
	/**
	 * 设置传出参数类型：0、传出参数类型；
	 * @param 传出参数类型：0、传出参数类型；
	 */
	public void setCIOutType(byte value){
		cIOutType = value;
	}	
}	


