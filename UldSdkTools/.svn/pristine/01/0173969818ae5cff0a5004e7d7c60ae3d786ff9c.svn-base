package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Feedback implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Feedback(){
		 this.status = (byte)0;
	}
		
	private int feedbackId;
	/** 
	 * 获取问题反馈编号
	 * @return 问题反馈编号
	 */
	public int getFeedbackId()
	{
		return feedbackId;
	}
	
	/**
	 * 设置问题反馈编号
	 * @param 问题反馈编号
	 */
	public void setFeedbackId(int value)
	{
		feedbackId = value;
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
	

	private byte feedbackType;
	/**
	 * 获取问题类型：0、问题类型；1、平台相关；2、售后相关；4、订单相关；8、产品相关；16、其他；
	 * @return 问题类型：0、问题类型；1、平台相关；2、售后相关；4、订单相关；8、产品相关；16、其他；
	 */
	public byte getFeedbackType(){
		return feedbackType;
	}
	
	/**
	 * 设置问题类型：0、问题类型；1、平台相关；2、售后相关；4、订单相关；8、产品相关；16、其他；
	 * @param 问题类型：0、问题类型；1、平台相关；2、售后相关；4、订单相关；8、产品相关；16、其他；
	 */
	public void setFeedbackType(byte value){
		feedbackType = value;
	}	
	
	private String realName;
	/** 
	 * 获取真实姓名
	 * @return 真实姓名
	 */
	public String getRealName()
	{
		return realName;
	}
	
	/**
	 * 设置真实姓名
	 * @param 真实姓名
	 */
	public void setRealName(String value)
	{
		realName = value;
	}
	
	
	private String mobilePhone;
	/** 
	 * 获取手机
	 * @return 手机
	 */
	public String getMobilePhone()
	{
		return mobilePhone;
	}
	
	/**
	 * 设置手机
	 * @param 手机
	 */
	public void setMobilePhone(String value)
	{
		mobilePhone = value;
	}
	
	
	private String email;
	/** 
	 * 获取电子邮件
	 * @return 电子邮件
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * 设置电子邮件
	 * @param 电子邮件
	 */
	public void setEmail(String value)
	{
		email = value;
	}
	
	
	private String content;
	/** 
	 * 获取描述
	 * @return 描述
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * 设置描述
	 * @param 描述
	 */
	public void setContent(String value)
	{
		content = value;
	}
	

	private byte feedbackDealType;
	/**
	 * 获取问题处理状态：0、问题处理状态；1、已处理；2、未处理；
	 * @return 问题处理状态：0、问题处理状态；1、已处理；2、未处理；
	 */
	public byte getFeedbackDealType(){
		return feedbackDealType;
	}
	
	/**
	 * 设置问题处理状态：0、问题处理状态；1、已处理；2、未处理；
	 * @param 问题处理状态：0、问题处理状态；1、已处理；2、未处理；
	 */
	public void setFeedbackDealType(byte value){
		feedbackDealType = value;
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
	
	
	
	private int parentId;
	/** 
	 * 获取父编号
	 * @return 父编号
	 */
	public int getParentId()
	{
		return parentId;
	}
	
	/**
	 * 设置父编号
	 * @param 父编号
	 */
	public void setParentId(int value)
	{
		parentId = value;
	}
	
	
	private String parentIdList;
	/** 
	 * 获取父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 * @return 父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 */
	public String getParentIdList()
	{
		return parentIdList;
	}
	
	/**
	 * 设置父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 * @param 父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 */
	public void setParentIdList(String value)
	{
		parentIdList = value;
	}
	
	
	private int level;
	/** 
	 * 获取级别，从0开始，0为最高级别
	 * @return 级别，从0开始，0为最高级别
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * 设置级别，从0开始，0为最高级别
	 * @param 级别，从0开始，0为最高级别
	 */
	public void setLevel(int value)
	{
		level = value;
	}
	

	private byte manageType;
	/**
	 * 获取管理问题类型：0、管理问题类型；1、提问；2、回复；
	 * @return 管理问题类型：0、管理问题类型；1、提问；2、回复；
	 */
	public byte getManageType(){
		return manageType;
	}
	
	/**
	 * 设置管理问题类型：0、管理问题类型；1、提问；2、回复；
	 * @param 管理问题类型：0、管理问题类型；1、提问；2、回复；
	 */
	public void setManageType(byte value){
		manageType = value;
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


