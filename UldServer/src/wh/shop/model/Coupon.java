package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Coupon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Coupon(){
		;
	}
		
	private int couponId;
	/** 
	 * 获取优惠券编号
	 * @return 优惠券编号
	 */
	public int getCouponId()
	{
		return couponId;
	}
	
	/**
	 * 设置优惠券编号
	 * @param 优惠券编号
	 */
	public void setCouponId(int value)
	{
		couponId = value;
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

	private byte couponType;
	/**
	 * 获取优惠券类别：0、优惠券类别；1、平台卷；
	 * @return 优惠券类别：0、优惠券类别；1、平台卷；
	 */
	public byte getCouponType(){
		return couponType;
	}
	
	/**
	 * 设置优惠券类别：0、优惠券类别；1、平台卷；
	 * @param 优惠券类别：0、优惠券类别；1、平台卷；
	 */
	public void setCouponType(byte value){
		couponType = value;
	}	
	
	private int needDB;
	/** 
	 * 获取所需D币
	 * @return 所需D币
	 */
	public int getNeedDB()
	{
		return needDB;
	}
	
	/**
	 * 设置所需D币
	 * @param 所需D币
	 */
	public void setNeedDB(int value)
	{
		needDB = value;
	}
	
	
	private int needFortune;
	/** 
	 * 获取所需幸运点
	 * @return 所需幸运点
	 */
	public int getNeedFortune()
	{
		return needFortune;
	}
	
	/**
	 * 设置所需幸运点
	 * @param 所需幸运点
	 */
	public void setNeedFortune(int value)
	{
		needFortune = value;
	}
	
	
	private int needConsumeValue;
	/** 
	 * 获取所需消费金额
	 * @return 所需消费金额
	 */
	public int getNeedConsumeValue()
	{
		return needConsumeValue;
	}
	
	/**
	 * 设置所需消费金额
	 * @param 所需消费金额
	 */
	public void setNeedConsumeValue(int value)
	{
		needConsumeValue = value;
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
	
	

	private Date effectiveDate;
	/**
	 * 获取生效日期
	 * @return 生效日期
	 */
	public Date getEffectiveDate()
	{   
		if(effectiveDate == null){
			return Utility.getDbMinDate();
		}
		return effectiveDate;
	}
	
	/**
	 * 设置生效日期
	 * @param 生效日期
	 */
	public void setEffectiveDate(Date value)
	{   
		effectiveDate = value;
	}
	
	private Date effectiveDateBegin;
	/**
	 * 获取生效日期
	 * @return 生效日期
	 */
	public Date getEffectiveDateBegin()
	{   
		if(effectiveDateBegin == null){
			return Utility.getDbMinDate();
		}
		return effectiveDateBegin;
	}
	
	/**
	 * 设置生效日期
	 * @param 生效日期
	 */
	public void setEffectiveDateBegin(Date value)
	{   
		effectiveDateBegin = value;
	}
	
	private Date effectiveDateEnd;
	/**
	 * 获取生效日期
	 * @return 生效日期
	 */
	public Date getEffectiveDateEnd()
	{   
		if(effectiveDateEnd == null){
			return Utility.getDbMinDate();
		}
		return effectiveDateEnd;
	}
	
	/**
	 * 设置生效日期
	 * @param 生效日期
	 */
	public void setEffectiveDateEnd(Date value)
	{   
		effectiveDateEnd = value;
	}
	
	

	private Date expiredDate;
	/**
	 * 获取失效日期
	 * @return 失效日期
	 */
	public Date getExpiredDate()
	{   
		if(expiredDate == null){
			return Utility.getDbMinDate();
		}
		return expiredDate;
	}
	
	/**
	 * 设置失效日期
	 * @param 失效日期
	 */
	public void setExpiredDate(Date value)
	{   
		expiredDate = value;
	}
	
	private Date expiredDateBegin;
	/**
	 * 获取失效日期
	 * @return 失效日期
	 */
	public Date getExpiredDateBegin()
	{   
		if(expiredDateBegin == null){
			return Utility.getDbMinDate();
		}
		return expiredDateBegin;
	}
	
	/**
	 * 设置失效日期
	 * @param 失效日期
	 */
	public void setExpiredDateBegin(Date value)
	{   
		expiredDateBegin = value;
	}
	
	private Date expiredDateEnd;
	/**
	 * 获取失效日期
	 * @return 失效日期
	 */
	public Date getExpiredDateEnd()
	{   
		if(expiredDateEnd == null){
			return Utility.getDbMinDate();
		}
		return expiredDateEnd;
	}
	
	/**
	 * 设置失效日期
	 * @param 失效日期
	 */
	public void setExpiredDateEnd(Date value)
	{   
		expiredDateEnd = value;
	}
	
	

	private byte usedType;
	/**
	 * 获取是否被使用：0、是否被使用；1、未被使用；2、被使用；
	 * @return 是否被使用：0、是否被使用；1、未被使用；2、被使用；
	 */
	public byte getUsedType(){
		return usedType;
	}
	
	/**
	 * 设置是否被使用：0、是否被使用；1、未被使用；2、被使用；
	 * @param 是否被使用：0、是否被使用；1、未被使用；2、被使用；
	 */
	public void setUsedType(byte value){
		usedType = value;
	}	
}	


