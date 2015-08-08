package wh.promotion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class ChannelSub implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public ChannelSub(){
		 this.status = (byte)0;
	}
		
	private int channelSubId;
	/** 
	 * 获取子渠道编号
	 * @return 子渠道编号
	 */
	public int getChannelSubId()
	{
		return channelSubId;
	}
	
	/**
	 * 设置子渠道编号
	 * @param 子渠道编号
	 */
	public void setChannelSubId(int value)
	{
		channelSubId = value;
	}
	

	private wh.member.model.Manager manager;
	/**
	 * 获取管理员
	 * @return 管理员
	 */
	public wh.member.model.Manager getManager(){
		return this.manager;
	}
	
	/**
	 * 设置管理员
	 * @param manager 管理员
	 */
	public void setManager(wh.member.model.Manager manager){
		this.manager = manager;
	}
	
	private int managerId;
	/**
	 * 获取管理员编号
	 * @return 管理员编号
	 */
	public int getManagerId(){
		if(manager != null){
			return manager.getManagerId();
		}else{
			return managerId;
		}
	}
	
	/**
	 * 设置管理员编号
	 * @param 管理员编号
	 */
	public void setManagerId(int value){
		if(manager != null){
			manager.setManagerId(value);
		}else{
			managerId = value;
		}
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

	private byte channelType;
	/**
	 * 获取渠道类型：0、渠道类型；1、自然渠道；2、推广渠道；
	 * @return 渠道类型：0、渠道类型；1、自然渠道；2、推广渠道；
	 */
	public byte getChannelType(){
		return channelType;
	}
	
	/**
	 * 设置渠道类型：0、渠道类型；1、自然渠道；2、推广渠道；
	 * @param 渠道类型：0、渠道类型；1、自然渠道；2、推广渠道；
	 */
	public void setChannelType(byte value){
		channelType = value;
	}	
	
	private String channelSubName;
	/** 
	 * 获取子渠道名称
	 * @return 子渠道名称
	 */
	public String getChannelSubName()
	{
		return channelSubName;
	}
	
	/**
	 * 设置子渠道名称
	 * @param 子渠道名称
	 */
	public void setChannelSubName(String value)
	{
		channelSubName = value;
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
	
	

	private Date modifyDate;
	/**
	 * 获取修改日期
	 * @return 修改日期
	 */
	public Date getModifyDate()
	{   
		if(modifyDate == null){
			return Utility.getDbMinDate();
		}
		return modifyDate;
	}
	
	/**
	 * 设置修改日期
	 * @param 修改日期
	 */
	public void setModifyDate(Date value)
	{   
		modifyDate = value;
	}
	
	private Date modifyDateBegin;
	/**
	 * 获取修改日期
	 * @return 修改日期
	 */
	public Date getModifyDateBegin()
	{   
		if(modifyDateBegin == null){
			return Utility.getDbMinDate();
		}
		return modifyDateBegin;
	}
	
	/**
	 * 设置修改日期
	 * @param 修改日期
	 */
	public void setModifyDateBegin(Date value)
	{   
		modifyDateBegin = value;
	}
	
	private Date modifyDateEnd;
	/**
	 * 获取修改日期
	 * @return 修改日期
	 */
	public Date getModifyDateEnd()
	{   
		if(modifyDateEnd == null){
			return Utility.getDbMinDate();
		}
		return modifyDateEnd;
	}
	
	/**
	 * 设置修改日期
	 * @param 修改日期
	 */
	public void setModifyDateEnd(Date value)
	{   
		modifyDateEnd = value;
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
	
	private String code;
	/** 
	 * 获取编码
	 * @return 编码
	 */
	public String getCode()
	{
		return code;
	}
	
	/**
	 * 设置编码
	 * @param 编码
	 */
	public void setCode(String value)
	{
		code = value;
	}
	
}	


