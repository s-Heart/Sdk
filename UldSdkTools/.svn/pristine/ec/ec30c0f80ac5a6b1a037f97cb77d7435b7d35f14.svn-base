package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class ManagerLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public ManagerLog(){
		 this.status = (byte)0;
	}
		
	private int managerLogId;
	/** 
	 * 获取管理员登录日志编号
	 * @return 管理员登录日志编号
	 */
	public int getManagerLogId()
	{
		return managerLogId;
	}
	
	/**
	 * 设置管理员登录日志编号
	 * @param 管理员登录日志编号
	 */
	public void setManagerLogId(int value)
	{
		managerLogId = value;
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
	

	private Date accessedDate;
	/**
	 * 获取登录时间
	 * @return 登录时间
	 */
	public Date getAccessedDate()
	{   
		if(accessedDate == null){
			return Utility.getDbMinDate();
		}
		return accessedDate;
	}
	
	/**
	 * 设置登录时间
	 * @param 登录时间
	 */
	public void setAccessedDate(Date value)
	{   
		accessedDate = value;
	}
	
	private Date accessedDateBegin;
	/**
	 * 获取登录时间
	 * @return 登录时间
	 */
	public Date getAccessedDateBegin()
	{   
		if(accessedDateBegin == null){
			return Utility.getDbMinDate();
		}
		return accessedDateBegin;
	}
	
	/**
	 * 设置登录时间
	 * @param 登录时间
	 */
	public void setAccessedDateBegin(Date value)
	{   
		accessedDateBegin = value;
	}
	
	private Date accessedDateEnd;
	/**
	 * 获取登录时间
	 * @return 登录时间
	 */
	public Date getAccessedDateEnd()
	{   
		if(accessedDateEnd == null){
			return Utility.getDbMinDate();
		}
		return accessedDateEnd;
	}
	
	/**
	 * 设置登录时间
	 * @param 登录时间
	 */
	public void setAccessedDateEnd(Date value)
	{   
		accessedDateEnd = value;
	}
	
	
	
	private int accessedDateYear;
	/** 
	 * 获取登录时间年
	 * @return 登录时间年
	 */
	public int getAccessedDateYear()
	{
		return accessedDateYear;
	}
	
	/**
	 * 设置登录时间年
	 * @param 登录时间年
	 */
	public void setAccessedDateYear(int value)
	{
		accessedDateYear = value;
	}
	
	
	private int accessedDateMonth;
	/** 
	 * 获取登录时间月
	 * @return 登录时间月
	 */
	public int getAccessedDateMonth()
	{
		return accessedDateMonth;
	}
	
	/**
	 * 设置登录时间月
	 * @param 登录时间月
	 */
	public void setAccessedDateMonth(int value)
	{
		accessedDateMonth = value;
	}
	
	
	private int accessedDateDay;
	/** 
	 * 获取登录时间天
	 * @return 登录时间天
	 */
	public int getAccessedDateDay()
	{
		return accessedDateDay;
	}
	
	/**
	 * 设置登录时间天
	 * @param 登录时间天
	 */
	public void setAccessedDateDay(int value)
	{
		accessedDateDay = value;
	}
	

	private Date leaveDate;
	/**
	 * 获取离开时间
	 * @return 离开时间
	 */
	public Date getLeaveDate()
	{   
		if(leaveDate == null){
			return Utility.getDbMinDate();
		}
		return leaveDate;
	}
	
	/**
	 * 设置离开时间
	 * @param 离开时间
	 */
	public void setLeaveDate(Date value)
	{   
		leaveDate = value;
	}
	
	private Date leaveDateBegin;
	/**
	 * 获取离开时间
	 * @return 离开时间
	 */
	public Date getLeaveDateBegin()
	{   
		if(leaveDateBegin == null){
			return Utility.getDbMinDate();
		}
		return leaveDateBegin;
	}
	
	/**
	 * 设置离开时间
	 * @param 离开时间
	 */
	public void setLeaveDateBegin(Date value)
	{   
		leaveDateBegin = value;
	}
	
	private Date leaveDateEnd;
	/**
	 * 获取离开时间
	 * @return 离开时间
	 */
	public Date getLeaveDateEnd()
	{   
		if(leaveDateEnd == null){
			return Utility.getDbMinDate();
		}
		return leaveDateEnd;
	}
	
	/**
	 * 设置离开时间
	 * @param 离开时间
	 */
	public void setLeaveDateEnd(Date value)
	{   
		leaveDateEnd = value;
	}
	
	
	
	private int onLineDuration;
	/** 
	 * 获取在线时长，单位(秒)
	 * @return 在线时长，单位(秒)
	 */
	public int getOnLineDuration()
	{
		return onLineDuration;
	}
	
	/**
	 * 设置在线时长，单位(秒)
	 * @param 在线时长，单位(秒)
	 */
	public void setOnLineDuration(int value)
	{
		onLineDuration = value;
	}
	

	private byte onLineType;
	/**
	 * 获取在线类型：0、在线类型；1、在线；2、下线；
	 * @return 在线类型：0、在线类型；1、在线；2、下线；
	 */
	public byte getOnLineType(){
		return onLineType;
	}
	
	/**
	 * 设置在线类型：0、在线类型；1、在线；2、下线；
	 * @param 在线类型：0、在线类型；1、在线；2、下线；
	 */
	public void setOnLineType(byte value){
		onLineType = value;
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


