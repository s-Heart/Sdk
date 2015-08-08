package wh.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class OrderLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public OrderLog(){
		 this.status = (byte)0;
	}
		
	private int orderLogId;
	/** 
	 * 获取日志编号
	 * @return 日志编号
	 */
	public int getOrderLogId()
	{
		return orderLogId;
	}
	
	/**
	 * 设置日志编号
	 * @param 日志编号
	 */
	public void setOrderLogId(int value)
	{
		orderLogId = value;
	}
	

	private wh.order.model.Order order;
	/**
	 * 获取订单
	 * @return 订单
	 */
	public wh.order.model.Order getOrder(){
		return this.order;
	}
	
	/**
	 * 设置订单
	 * @param order 订单
	 */
	public void setOrder(wh.order.model.Order order){
		this.order = order;
	}
	
	private int orderId;
	/**
	 * 获取订单编号
	 * @return 订单编号
	 */
	public int getOrderId(){
		if(order != null){
			return order.getOrderId();
		}else{
			return orderId;
		}
	}
	
	/**
	 * 设置订单编号
	 * @param 订单编号
	 */
	public void setOrderId(int value){
		if(order != null){
			order.setOrderId(value);
		}else{
			orderId = value;
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
	
	
	private String logName;
	/** 
	 * 获取日志名称
	 * @return 日志名称
	 */
	public String getLogName()
	{
		return logName;
	}
	
	/**
	 * 设置日志名称
	 * @param 日志名称
	 */
	public void setLogName(String value)
	{
		logName = value;
	}
	
	
	private String description;
	/** 
	 * 获取描述
	 * @return 描述
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * 设置描述
	 * @param 描述
	 */
	public void setDescription(String value)
	{
		description = value;
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


