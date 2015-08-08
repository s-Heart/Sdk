package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Account(){
		 this.status = (byte)0;
	}
		
	private int accountId;
	/** 
	 * 获取帐户编号
	 * @return 帐户编号
	 */
	public int getAccountId()
	{
		return accountId;
	}
	
	/**
	 * 设置帐户编号
	 * @param 帐户编号
	 */
	public void setAccountId(int value)
	{
		accountId = value;
	}
	

	private wh.member.model.User user;
	/**
	 * 获取用户
	 * @return 用户
	 */
	public wh.member.model.User getUser(){
		return this.user;
	}
	
	/**
	 * 设置用户
	 * @param user 用户
	 */
	public void setUser(wh.member.model.User user){
		this.user = user;
	}
	
	private int userId;
	/**
	 * 获取用户编号
	 * @return 用户编号
	 */
	public int getUserId(){
		if(user != null){
			return user.getUserId();
		}else{
			return userId;
		}
	}
	
	/**
	 * 设置用户编号
	 * @param 用户编号
	 */
	public void setUserId(int value){
		if(user != null){
			user.setUserId(value);
		}else{
			userId = value;
		}
	}
	/**
	 * 获取用户名称
	 * @return 用户名称
	 */
	public String getUserName(){
		if(user != null){
			return user.getUserName();
		}else{
			return "";
		}
	}	
	
	private BigDecimal accountValue;
	/** 
	 * 获取帐户值
	 * @return 帐户值
	 */
	public BigDecimal getAccountValue()
	{
		return accountValue;
	}
	
	/**
	 * 设置帐户值
	 * @param 帐户值
	 */
	public void setAccountValue(BigDecimal value)
	{
		accountValue = value;
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
	
	
	
	private int createDateYear;
	/** 
	 * 获取创建日期年，eg：2011
	 * @return 创建日期年，eg：2011
	 */
	public int getCreateDateYear()
	{
		return createDateYear;
	}
	
	/**
	 * 设置创建日期年，eg：2011
	 * @param 创建日期年，eg：2011
	 */
	public void setCreateDateYear(int value)
	{
		createDateYear = value;
	}
	
	
	private int createDateMonth;
	/** 
	 * 获取创建日期月，eg：201101
	 * @return 创建日期月，eg：201101
	 */
	public int getCreateDateMonth()
	{
		return createDateMonth;
	}
	
	/**
	 * 设置创建日期月，eg：201101
	 * @param 创建日期月，eg：201101
	 */
	public void setCreateDateMonth(int value)
	{
		createDateMonth = value;
	}
	
	
	private int createDateDay;
	/** 
	 * 获取创建日期天，eg：20110101
	 * @return 创建日期天，eg：20110101
	 */
	public int getCreateDateDay()
	{
		return createDateDay;
	}
	
	/**
	 * 设置创建日期天，eg：20110101
	 * @param 创建日期天，eg：20110101
	 */
	public void setCreateDateDay(int value)
	{
		createDateDay = value;
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
}	


