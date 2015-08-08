package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class UserBasic implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public UserBasic(){
		 this.status = (byte)0;
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
	
	private int userBasicId;
	/** 
	 * 获取
	 * @return 
	 */
	public int getUserBasicId()
	{
		return userBasicId;
	}
	
	/**
	 * 设置
	 * @param 
	 */
	public void setUserBasicId(int value)
	{
		userBasicId = value;
	}
	
	
	private String mRealName;
	/** 
	 * 获取真实姓名
	 * @return 真实姓名
	 */
	public String getMRealName()
	{
		return mRealName;
	}
	
	/**
	 * 设置真实姓名
	 * @param 真实姓名
	 */
	public void setMRealName(String value)
	{
		mRealName = value;
	}
	
	
	private String mMobilePhone;
	/** 
	 * 获取多个Tel，以,号分隔
	 * @return 多个Tel，以,号分隔
	 */
	public String getMMobilePhone()
	{
		return mMobilePhone;
	}
	
	/**
	 * 设置多个Tel，以,号分隔
	 * @param 多个Tel，以,号分隔
	 */
	public void setMMobilePhone(String value)
	{
		mMobilePhone = value;
	}
	

	private byte mGenderType;
	/**
	 * 获取性别：0、性别；1、男；2、女；
	 * @return 性别：0、性别；1、男；2、女；
	 */
	public byte getMGenderType(){
		return mGenderType;
	}
	
	/**
	 * 设置性别：0、性别；1、男；2、女；
	 * @param 性别：0、性别；1、男；2、女；
	 */
	public void setMGenderType(byte value){
		mGenderType = value;
	}	
	
	private String mAddress;
	/** 
	 * 获取地址
	 * @return 地址
	 */
	public String getMAddress()
	{
		return mAddress;
	}
	
	/**
	 * 设置地址
	 * @param 地址
	 */
	public void setMAddress(String value)
	{
		mAddress = value;
	}
	
	
	private int mAge;
	/** 
	 * 获取
	 * @return 
	 */
	public int getMAge()
	{
		return mAge;
	}
	
	/**
	 * 设置
	 * @param 
	 */
	public void setMAge(int value)
	{
		mAge = value;
	}
	

	private Date mBirthday;
	/**
	 * 获取
	 * @return 
	 */
	public Date getMBirthday()
	{   
		if(mBirthday == null){
			return Utility.getDbMinDate();
		}
		return mBirthday;
	}
	
	/**
	 * 设置
	 * @param 
	 */
	public void setMBirthday(Date value)
	{   
		mBirthday = value;
	}
	
	private Date mBirthdayBegin;
	/**
	 * 获取
	 * @return 
	 */
	public Date getMBirthdayBegin()
	{   
		if(mBirthdayBegin == null){
			return Utility.getDbMinDate();
		}
		return mBirthdayBegin;
	}
	
	/**
	 * 设置
	 * @param 
	 */
	public void setMBirthdayBegin(Date value)
	{   
		mBirthdayBegin = value;
	}
	
	private Date mBirthdayEnd;
	/**
	 * 获取
	 * @return 
	 */
	public Date getMBirthdayEnd()
	{   
		if(mBirthdayEnd == null){
			return Utility.getDbMinDate();
		}
		return mBirthdayEnd;
	}
	
	/**
	 * 设置
	 * @param 
	 */
	public void setMBirthdayEnd(Date value)
	{   
		mBirthdayEnd = value;
	}
	
	

	private byte mOnLinetype;
	/**
	 * 获取在线类型：0、在线类型；1、在线；2、下线；
	 * @return 在线类型：0、在线类型；1、在线；2、下线；
	 */
	public byte getMOnLinetype(){
		return mOnLinetype;
	}
	
	/**
	 * 设置在线类型：0、在线类型；1、在线；2、下线；
	 * @param 在线类型：0、在线类型；1、在线；2、下线；
	 */
	public void setMOnLinetype(byte value){
		mOnLinetype = value;
	}	
	
	private String mNickName;
	/** 
	 * 获取昵称
	 * @return 昵称
	 */
	public String getMNickName()
	{
		return mNickName;
	}
	
	/**
	 * 设置昵称
	 * @param 昵称
	 */
	public void setMNickName(String value)
	{
		mNickName = value;
	}
	
	
	private String roleName;
	/** 
	 * 获取角色名称
	 * @return 角色名称
	 */
	public String getRoleName()
	{
		return roleName;
	}
	
	/**
	 * 设置角色名称
	 * @param 角色名称
	 */
	public void setRoleName(String value)
	{
		roleName = value;
	}
	
	
	private int roleLevel;
	/** 
	 * 获取等级
	 * @return 等级
	 */
	public int getRoleLevel()
	{
		return roleLevel;
	}
	
	/**
	 * 设置等级
	 * @param 等级
	 */
	public void setRoleLevel(int value)
	{
		roleLevel = value;
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
	
	private BigDecimal totalPayAccount;
	/** 
	 * 获取总充值金额
	 * @return 总充值金额
	 */
	public BigDecimal getTotalPayAccount()
	{
		return totalPayAccount;
	}
	
	/**
	 * 设置总充值金额
	 * @param 总充值金额
	 */
	public void setTotalPayAccount(BigDecimal value)
	{
		totalPayAccount = value;
	}
	
}	


