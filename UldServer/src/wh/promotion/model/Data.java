package wh.promotion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Data implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Data(){
		;
	}
		
	private int dataId;
	/** 
	 * 获取数据编号
	 * @return 数据编号
	 */
	public int getDataId()
	{
		return dataId;
	}
	
	/**
	 * 设置数据编号
	 * @param 数据编号
	 */
	public void setDataId(int value)
	{
		dataId = value;
	}
	
	
	private int dataChannelId;
	/** 
	 * 获取数据渠道编号
	 * @return 数据渠道编号
	 */
	public int getDataChannelId()
	{
		return dataChannelId;
	}
	
	/**
	 * 设置数据渠道编号
	 * @param 数据渠道编号
	 */
	public void setDataChannelId(int value)
	{
		dataChannelId = value;
	}
	
	
	private int dataChannelSubId;
	/** 
	 * 获取数据渠道子编号
	 * @return 数据渠道子编号
	 */
	public int getDataChannelSubId()
	{
		return dataChannelSubId;
	}
	
	/**
	 * 设置数据渠道子编号
	 * @param 数据渠道子编号
	 */
	public void setDataChannelSubId(int value)
	{
		dataChannelSubId = value;
	}
	
	
	private int dataUserId;
	/** 
	 * 获取数据用户编号
	 * @return 数据用户编号
	 */
	public int getDataUserId()
	{
		return dataUserId;
	}
	
	/**
	 * 设置数据用户编号
	 * @param 数据用户编号
	 */
	public void setDataUserId(int value)
	{
		dataUserId = value;
	}
	
	
	private String userName;
	/** 
	 * 获取用户名
	 * @return 用户名
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * 设置用户名
	 * @param 用户名
	 */
	public void setUserName(String value)
	{
		userName = value;
	}
	
	
	private String otherSiteId;
	/** 
	 * 获取其它网站Id
	 * @return 其它网站Id
	 */
	public String getOtherSiteId()
	{
		return otherSiteId;
	}
	
	/**
	 * 设置其它网站Id
	 * @param 其它网站Id
	 */
	public void setOtherSiteId(String value)
	{
		otherSiteId = value;
	}
	

	private Date registerDate;
	/**
	 * 获取注册日期
	 * @return 注册日期
	 */
	public Date getRegisterDate()
	{   
		if(registerDate == null){
			return Utility.getDbMinDate();
		}
		return registerDate;
	}
	
	/**
	 * 设置注册日期
	 * @param 注册日期
	 */
	public void setRegisterDate(Date value)
	{   
		registerDate = value;
	}
	
	private Date registerDateBegin;
	/**
	 * 获取注册日期
	 * @return 注册日期
	 */
	public Date getRegisterDateBegin()
	{   
		if(registerDateBegin == null){
			return Utility.getDbMinDate();
		}
		return registerDateBegin;
	}
	
	/**
	 * 设置注册日期
	 * @param 注册日期
	 */
	public void setRegisterDateBegin(Date value)
	{   
		registerDateBegin = value;
	}
	
	private Date registerDateEnd;
	/**
	 * 获取注册日期
	 * @return 注册日期
	 */
	public Date getRegisterDateEnd()
	{   
		if(registerDateEnd == null){
			return Utility.getDbMinDate();
		}
		return registerDateEnd;
	}
	
	/**
	 * 设置注册日期
	 * @param 注册日期
	 */
	public void setRegisterDateEnd(Date value)
	{   
		registerDateEnd = value;
	}
	
	
	
	private String roleAndLevel;
	/** 
	 * 获取角色和级别
	 * @return 角色和级别
	 */
	public String getRoleAndLevel()
	{
		return roleAndLevel;
	}
	
	/**
	 * 设置角色和级别
	 * @param 角色和级别
	 */
	public void setRoleAndLevel(String value)
	{
		roleAndLevel = value;
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
	
	
}	


