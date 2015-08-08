package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Manager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Manager(){
		 this.status = (byte)0;
	}
		
	private int managerId;
	/** 
	 * 获取管理员编号
	 * @return 管理员编号
	 */
	public int getManagerId()
	{
		return managerId;
	}
	
	/**
	 * 设置管理员编号
	 * @param 管理员编号
	 */
	public void setManagerId(int value)
	{
		managerId = value;
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
	
	
	private String password;
	/** 
	 * 获取密码
	 * @return 密码
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * 设置密码
	 * @param 密码
	 */
	public void setPassword(String value)
	{
		password = value;
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
	

	private byte genderType;
	/**
	 * 获取性别：0、性别；1、男；2、女；
	 * @return 性别：0、性别；1、男；2、女；
	 */
	public byte getGenderType(){
		return genderType;
	}
	
	/**
	 * 设置性别：0、性别；1、男；2、女；
	 * @param 性别：0、性别；1、男；2、女；
	 */
	public void setGenderType(byte value){
		genderType = value;
	}	
	
	private String iDCard;
	/** 
	 * 获取身份证
	 * @return 身份证
	 */
	public String getIDCard()
	{
		return iDCard;
	}
	
	/**
	 * 设置身份证
	 * @param 身份证
	 */
	public void setIDCard(String value)
	{
		iDCard = value;
	}
	
	
	private String tel;
	/** 
	 * 获取多个Tel，以,号分隔
	 * @return 多个Tel，以,号分隔
	 */
	public String getTel()
	{
		return tel;
	}
	
	/**
	 * 设置多个Tel，以,号分隔
	 * @param 多个Tel，以,号分隔
	 */
	public void setTel(String value)
	{
		tel = value;
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
	
	
	private String otherMobilePhone;
	/** 
	 * 获取其它手机号，以,号分隔
	 * @return 其它手机号，以,号分隔
	 */
	public String getOtherMobilePhone()
	{
		return otherMobilePhone;
	}
	
	/**
	 * 设置其它手机号，以,号分隔
	 * @param 其它手机号，以,号分隔
	 */
	public void setOtherMobilePhone(String value)
	{
		otherMobilePhone = value;
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
	
	

	private int managerType;
	/**
	 * 获取管理员类型：0、管理员类型；1、客服；2、内容管理员；4、海报管理员；8、游戏管理员；16、用户管理员；32、统计分析管理员；64、管理员；128、超级管理员；256、渠道推广搜索；512、渠道推广联盟；1024、渠道推广垂媒；2048、渠道推广自然；4096、渠道推广全部；8192、返利管理员；16384、GS前台管理员；32768、GS后台管理员；
	 * @return 管理员类型：0、管理员类型；1、客服；2、内容管理员；4、海报管理员；8、游戏管理员；16、用户管理员；32、统计分析管理员；64、管理员；128、超级管理员；256、渠道推广搜索；512、渠道推广联盟；1024、渠道推广垂媒；2048、渠道推广自然；4096、渠道推广全部；8192、返利管理员；16384、GS前台管理员；32768、GS后台管理员；
	 */
	public int getManagerType(){
		return managerType;
	}
	
	/**
	 * 设置管理员类型：0、管理员类型；1、客服；2、内容管理员；4、海报管理员；8、游戏管理员；16、用户管理员；32、统计分析管理员；64、管理员；128、超级管理员；256、渠道推广搜索；512、渠道推广联盟；1024、渠道推广垂媒；2048、渠道推广自然；4096、渠道推广全部；8192、返利管理员；16384、GS前台管理员；32768、GS后台管理员；
	 * @param 管理员类型：0、管理员类型；1、客服；2、内容管理员；4、海报管理员；8、游戏管理员；16、用户管理员；32、统计分析管理员；64、管理员；128、超级管理员；256、渠道推广搜索；512、渠道推广联盟；1024、渠道推广垂媒；2048、渠道推广自然；4096、渠道推广全部；8192、返利管理员；16384、GS前台管理员；32768、GS后台管理员；
	 */
	public void setManagerType(int value){
		managerType = value;
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

	private byte accessType;
	/**
	 * 获取访问权限：0、访问权限；1、查询；2、编辑；4、删除；
	 * @return 访问权限：0、访问权限；1、查询；2、编辑；4、删除；
	 */
	public byte getAccessType(){
		return accessType;
	}
	
	/**
	 * 设置访问权限：0、访问权限；1、查询；2、编辑；4、删除；
	 * @param 访问权限：0、访问权限；1、查询；2、编辑；4、删除；
	 */
	public void setAccessType(byte value){
		accessType = value;
	}	
}	


