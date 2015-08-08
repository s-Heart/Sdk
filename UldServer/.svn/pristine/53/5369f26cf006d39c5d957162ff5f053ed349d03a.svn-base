package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class OpenUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public OpenUser(){
		 this.status = (byte)0;
	}
		
	private int openUserId;
	/** 
	 * 获取开放平台用户编号
	 * @return 开放平台用户编号
	 */
	public int getOpenUserId()
	{
		return openUserId;
	}
	
	/**
	 * 设置开放平台用户编号
	 * @param 开放平台用户编号
	 */
	public void setOpenUserId(int value)
	{
		openUserId = value;
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
	
	private String openId;
	/** 
	 * 获取第三方平台ID
	 * @return 第三方平台ID
	 */
	public String getOpenId()
	{
		return openId;
	}
	
	/**
	 * 设置第三方平台ID
	 * @param 第三方平台ID
	 */
	public void setOpenId(String value)
	{
		openId = value;
	}
	

	private byte openType;
	/**
	 * 获取平台类型：0、平台类型；1、QQ；2、sina微博；4、YY；8、一七一七三；
	 * @return 平台类型：0、平台类型；1、QQ；2、sina微博；4、YY；8、一七一七三；
	 */
	public byte getOpenType(){
		return openType;
	}
	
	/**
	 * 设置平台类型：0、平台类型；1、QQ；2、sina微博；4、YY；8、一七一七三；
	 * @param 平台类型：0、平台类型；1、QQ；2、sina微博；4、YY；8、一七一七三；
	 */
	public void setOpenType(byte value){
		openType = value;
	}	

	private byte bindType;
	/**
	 * 获取绑定类型：0、绑定类型；1、已绑定；2、未绑定；
	 * @return 绑定类型：0、绑定类型；1、已绑定；2、未绑定；
	 */
	public byte getBindType(){
		return bindType;
	}
	
	/**
	 * 设置绑定类型：0、绑定类型；1、已绑定；2、未绑定；
	 * @param 绑定类型：0、绑定类型；1、已绑定；2、未绑定；
	 */
	public void setBindType(byte value){
		bindType = value;
	}	
	
	private String accessToken;
	/** 
	 * 获取授权码
	 * @return 授权码
	 */
	public String getAccessToken()
	{
		return accessToken;
	}
	
	/**
	 * 设置授权码
	 * @param 授权码
	 */
	public void setAccessToken(String value)
	{
		accessToken = value;
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
	
	

	private Date expireDate;
	/**
	 * 获取过期时间，针对授权码
	 * @return 过期时间，针对授权码
	 */
	public Date getExpireDate()
	{   
		if(expireDate == null){
			return Utility.getDbMinDate();
		}
		return expireDate;
	}
	
	/**
	 * 设置过期时间，针对授权码
	 * @param 过期时间，针对授权码
	 */
	public void setExpireDate(Date value)
	{   
		expireDate = value;
	}
	
	private Date expireDateBegin;
	/**
	 * 获取过期时间，针对授权码
	 * @return 过期时间，针对授权码
	 */
	public Date getExpireDateBegin()
	{   
		if(expireDateBegin == null){
			return Utility.getDbMinDate();
		}
		return expireDateBegin;
	}
	
	/**
	 * 设置过期时间，针对授权码
	 * @param 过期时间，针对授权码
	 */
	public void setExpireDateBegin(Date value)
	{   
		expireDateBegin = value;
	}
	
	private Date expireDateEnd;
	/**
	 * 获取过期时间，针对授权码
	 * @return 过期时间，针对授权码
	 */
	public Date getExpireDateEnd()
	{   
		if(expireDateEnd == null){
			return Utility.getDbMinDate();
		}
		return expireDateEnd;
	}
	
	/**
	 * 设置过期时间，针对授权码
	 * @param 过期时间，针对授权码
	 */
	public void setExpireDateEnd(Date value)
	{   
		expireDateEnd = value;
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


