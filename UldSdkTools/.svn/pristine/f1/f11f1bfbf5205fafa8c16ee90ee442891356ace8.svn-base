package wh.promotion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class ChannelInterface implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public ChannelInterface(){
		 this.status = (byte)0;
	}
		
	private int channelInterfaceId;
	/** 
	 * 获取渠道接口编号
	 * @return 渠道接口编号
	 */
	public int getChannelInterfaceId()
	{
		return channelInterfaceId;
	}
	
	/**
	 * 设置渠道接口编号
	 * @param 渠道接口编号
	 */
	public void setChannelInterfaceId(int value)
	{
		channelInterfaceId = value;
	}
	

	private wh.promotion.model.Channel channel;
	/**
	 * 获取渠道
	 * @return 渠道
	 */
	public wh.promotion.model.Channel getChannel(){
		return this.channel;
	}
	
	/**
	 * 设置渠道
	 * @param channel 渠道
	 */
	public void setChannel(wh.promotion.model.Channel channel){
		this.channel = channel;
	}
	
	private int channelId;
	/**
	 * 获取渠道编号
	 * @return 渠道编号
	 */
	public int getChannelId(){
		if(channel != null){
			return channel.getChannelId();
		}else{
			return channelId;
		}
	}
	
	/**
	 * 设置渠道编号
	 * @param 渠道编号
	 */
	public void setChannelId(int value){
		if(channel != null){
			channel.setChannelId(value);
		}else{
			channelId = value;
		}
	}
	/**
	 * 获取渠道名称
	 * @return 渠道名称
	 */
	public String getChannelName(){
		if(channel != null){
			return channel.getChannelName();
		}else{
			return "";
		}
	}	

	private wh.promotion.model.ChannelSub channelSub;
	/**
	 * 获取子渠道
	 * @return 子渠道
	 */
	public wh.promotion.model.ChannelSub getChannelSub(){
		return this.channelSub;
	}
	
	/**
	 * 设置子渠道
	 * @param channelSub 子渠道
	 */
	public void setChannelSub(wh.promotion.model.ChannelSub channelSub){
		this.channelSub = channelSub;
	}
	
	private int channelSubId;
	/**
	 * 获取子渠道编号
	 * @return 子渠道编号
	 */
	public int getChannelSubId(){
		if(channelSub != null){
			return channelSub.getChannelSubId();
		}else{
			return channelSubId;
		}
	}
	
	/**
	 * 设置子渠道编号
	 * @param 子渠道编号
	 */
	public void setChannelSubId(int value){
		if(channelSub != null){
			channelSub.setChannelSubId(value);
		}else{
			channelSubId = value;
		}
	}
	/**
	 * 获取子渠道名称
	 * @return 子渠道名称
	 */
	public String getChannelSubName(){
		if(channelSub != null){
			return channelSub.getChannelSubName();
		}else{
			return "";
		}
	}	
	
	private String name;
	/** 
	 * 获取名称
	 * @return 名称
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * 设置名称
	 * @param 名称
	 */
	public void setName(String value)
	{
		name = value;
	}
	
	
	private String url;
	/** 
	 * 获取接口Url
	 * @return 接口Url
	 */
	public String getUrl()
	{
		return url;
	}
	
	/**
	 * 设置接口Url
	 * @param 接口Url
	 */
	public void setUrl(String value)
	{
		url = value;
	}
	

	private byte cIType;
	/**
	 * 获取接口类型：0、接口类型；1、第三方注册接口；
	 * @return 接口类型：0、接口类型；1、第三方注册接口；
	 */
	public byte getCIType(){
		return cIType;
	}
	
	/**
	 * 设置接口类型：0、接口类型；1、第三方注册接口；
	 * @param 接口类型：0、接口类型；1、第三方注册接口；
	 */
	public void setCIType(byte value){
		cIType = value;
	}	

	private Date createDate;
	/**
	 * 获取创建日期
	 * @return 创建日期
	 */
	public Date getCreateDate()
	{   
		if(createDate == null){
			return Utility.getDbMinDate();
		}
		return createDate;
	}
	
	/**
	 * 设置创建日期
	 * @param 创建日期
	 */
	public void setCreateDate(Date value)
	{   
		createDate = value;
	}
	
	private Date createDateBegin;
	/**
	 * 获取创建日期
	 * @return 创建日期
	 */
	public Date getCreateDateBegin()
	{   
		if(createDateBegin == null){
			return Utility.getDbMinDate();
		}
		return createDateBegin;
	}
	
	/**
	 * 设置创建日期
	 * @param 创建日期
	 */
	public void setCreateDateBegin(Date value)
	{   
		createDateBegin = value;
	}
	
	private Date createDateEnd;
	/**
	 * 获取创建日期
	 * @return 创建日期
	 */
	public Date getCreateDateEnd()
	{   
		if(createDateEnd == null){
			return Utility.getDbMinDate();
		}
		return createDateEnd;
	}
	
	/**
	 * 设置创建日期
	 * @param 创建日期
	 */
	public void setCreateDateEnd(Date value)
	{   
		createDateEnd = value;
	}
	
	

	private byte status;
	/**
	 * 获取状态：0、状态；1、有效；2、无效；
	 * @return 状态：0、状态；1、有效；2、无效；
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * 设置状态：0、状态；1、有效；2、无效；
	 * @param 状态：0、状态；1、有效；2、无效；
	 */
	public void setStatus(byte value){
		status = value;
	}	
}	


