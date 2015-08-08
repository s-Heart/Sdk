package wh.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class OrderDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public OrderDetail(){
		 this.status = (byte)0;
	}
		
	private int orderDetailId;
	/** 
	 * 获取订单详情
	 * @return 订单详情
	 */
	public int getOrderDetailId()
	{
		return orderDetailId;
	}
	
	/**
	 * 设置订单详情
	 * @param 订单详情
	 */
	public void setOrderDetailId(int value)
	{
		orderDetailId = value;
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
	
	
	private BigDecimal payAccount;
	/** 
	 * 获取充值金额
	 * @return 充值金额
	 */
	public BigDecimal getPayAccount()
	{
		return payAccount;
	}
	
	/**
	 * 设置充值金额
	 * @param 充值金额
	 */
	public void setPayAccount(BigDecimal value)
	{
		payAccount = value;
	}
	

	private byte payType;
	/**
	 * 获取支付类型：0、支付类型；1、网上银行充值；2、支付宝充值；4、手机卡充值；8、声讯充值；16、点卡充值；32、PayPal充值；
	 * @return 支付类型：0、支付类型；1、网上银行充值；2、支付宝充值；4、手机卡充值；8、声讯充值；16、点卡充值；32、PayPal充值；
	 */
	public byte getPayType(){
		return payType;
	}
	
	/**
	 * 设置支付类型：0、支付类型；1、网上银行充值；2、支付宝充值；4、手机卡充值；8、声讯充值；16、点卡充值；32、PayPal充值；
	 * @param 支付类型：0、支付类型；1、网上银行充值；2、支付宝充值；4、手机卡充值；8、声讯充值；16、点卡充值；32、PayPal充值；
	 */
	public void setPayType(byte value){
		payType = value;
	}	

	private byte cardType;
	/**
	 * 获取卡类型：0、卡类型；1、神州行；2、联通卡；4、电信卡；
	 * @return 卡类型：0、卡类型；1、神州行；2、联通卡；4、电信卡；
	 */
	public byte getCardType(){
		return cardType;
	}
	
	/**
	 * 设置卡类型：0、卡类型；1、神州行；2、联通卡；4、电信卡；
	 * @param 卡类型：0、卡类型；1、神州行；2、联通卡；4、电信卡；
	 */
	public void setCardType(byte value){
		cardType = value;
	}	
	
	private String cardNumber;
	/** 
	 * 获取卡号
	 * @return 卡号
	 */
	public String getCardNumber()
	{
		return cardNumber;
	}
	
	/**
	 * 设置卡号
	 * @param 卡号
	 */
	public void setCardNumber(String value)
	{
		cardNumber = value;
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
	
	

	private byte orderType;
	/**
	 * 获取订单类型：0、订单类型；1、已提交；2、处理成功；4、处理失败；8、已提交游戏方；16、游戏方处理成功；32、游戏方处理失败；64、掉单；
	 * @return 订单类型：0、订单类型；1、已提交；2、处理成功；4、处理失败；8、已提交游戏方；16、游戏方处理成功；32、游戏方处理失败；64、掉单；
	 */
	public byte getOrderType(){
		return orderType;
	}
	
	/**
	 * 设置订单类型：0、订单类型；1、已提交；2、处理成功；4、处理失败；8、已提交游戏方；16、游戏方处理成功；32、游戏方处理失败；64、掉单；
	 * @param 订单类型：0、订单类型；1、已提交；2、处理成功；4、处理失败；8、已提交游戏方；16、游戏方处理成功；32、游戏方处理失败；64、掉单；
	 */
	public void setOrderType(byte value){
		orderType = value;
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


