package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class SignInLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public SignInLog(){
		;
	}
		
	private int signInLogId;
	/** 
	 * 获取签道记录编号
	 * @return 签道记录编号
	 */
	public int getSignInLogId()
	{
		return signInLogId;
	}
	
	/**
	 * 设置签道记录编号
	 * @param 签道记录编号
	 */
	public void setSignInLogId(int value)
	{
		signInLogId = value;
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
	
	
	private int currentContinuousDays;
	/** 
	 * 获取当前连续天数，必须是连续的，如果有隔天则重新从1开始，只针对同一用户
	 * @return 当前连续天数，必须是连续的，如果有隔天则重新从1开始，只针对同一用户
	 */
	public int getCurrentContinuousDays()
	{
		return currentContinuousDays;
	}
	
	/**
	 * 设置当前连续天数，必须是连续的，如果有隔天则重新从1开始，只针对同一用户
	 * @param 当前连续天数，必须是连续的，如果有隔天则重新从1开始，只针对同一用户
	 */
	public void setCurrentContinuousDays(int value)
	{
		currentContinuousDays = value;
	}
	
	
	private int currentLoginTimes;
	/** 
	 * 获取当前登录次数，每登录一次加1，只针对同一用户
	 * @return 当前登录次数，每登录一次加1，只针对同一用户
	 */
	public int getCurrentLoginTimes()
	{
		return currentLoginTimes;
	}
	
	/**
	 * 设置当前登录次数，每登录一次加1，只针对同一用户
	 * @param 当前登录次数，每登录一次加1，只针对同一用户
	 */
	public void setCurrentLoginTimes(int value)
	{
		currentLoginTimes = value;
	}
	
	
	private int currentLoginDays;
	/** 
	 * 获取当前登录天数，每次登录如果日期天和上次登录日期天不一样，则加1，只针对同一用户
	 * @return 当前登录天数，每次登录如果日期天和上次登录日期天不一样，则加1，只针对同一用户
	 */
	public int getCurrentLoginDays()
	{
		return currentLoginDays;
	}
	
	/**
	 * 设置当前登录天数，每次登录如果日期天和上次登录日期天不一样，则加1，只针对同一用户
	 * @param 当前登录天数，每次登录如果日期天和上次登录日期天不一样，则加1，只针对同一用户
	 */
	public void setCurrentLoginDays(int value)
	{
		currentLoginDays = value;
	}
	
	
	private String sourceIP;
	/** 
	 * 获取来源IP
	 * @return 来源IP
	 */
	public String getSourceIP()
	{
		return sourceIP;
	}
	
	/**
	 * 设置来源IP
	 * @param 来源IP
	 */
	public void setSourceIP(String value)
	{
		sourceIP = value;
	}
	
}	


