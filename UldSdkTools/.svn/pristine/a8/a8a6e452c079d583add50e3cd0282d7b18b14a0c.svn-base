package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class CustomerService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public CustomerService(){
		 this.status = (byte)0;
	}
		
	private int customerServiceId;
	/** 
	 * 获取客服编号
	 * @return 客服编号
	 */
	public int getCustomerServiceId()
	{
		return customerServiceId;
	}
	
	/**
	 * 设置客服编号
	 * @param 客服编号
	 */
	public void setCustomerServiceId(int value)
	{
		customerServiceId = value;
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
	
	

	private Date expiryDate;
	/**
	 * 获取失效日期
	 * @return 失效日期
	 */
	public Date getExpiryDate()
	{   
		if(expiryDate == null){
			return Utility.getDbMinDate();
		}
		return expiryDate;
	}
	
	/**
	 * 设置失效日期
	 * @param 失效日期
	 */
	public void setExpiryDate(Date value)
	{   
		expiryDate = value;
	}
	
	private Date expiryDateBegin;
	/**
	 * 获取失效日期
	 * @return 失效日期
	 */
	public Date getExpiryDateBegin()
	{   
		if(expiryDateBegin == null){
			return Utility.getDbMinDate();
		}
		return expiryDateBegin;
	}
	
	/**
	 * 设置失效日期
	 * @param 失效日期
	 */
	public void setExpiryDateBegin(Date value)
	{   
		expiryDateBegin = value;
	}
	
	private Date expiryDateEnd;
	/**
	 * 获取失效日期
	 * @return 失效日期
	 */
	public Date getExpiryDateEnd()
	{   
		if(expiryDateEnd == null){
			return Utility.getDbMinDate();
		}
		return expiryDateEnd;
	}
	
	/**
	 * 设置失效日期
	 * @param 失效日期
	 */
	public void setExpiryDateEnd(Date value)
	{   
		expiryDateEnd = value;
	}
	
	

	private byte enableType;
	/**
	 * 获取启用状态：0、启用状态；1、开启；2、暂停；4、停止；
	 * @return 启用状态：0、启用状态；1、开启；2、暂停；4、停止；
	 */
	public byte getEnableType(){
		return enableType;
	}
	
	/**
	 * 设置启用状态：0、启用状态；1、开启；2、暂停；4、停止；
	 * @param 启用状态：0、启用状态；1、开启；2、暂停；4、停止；
	 */
	public void setEnableType(byte value){
		enableType = value;
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


