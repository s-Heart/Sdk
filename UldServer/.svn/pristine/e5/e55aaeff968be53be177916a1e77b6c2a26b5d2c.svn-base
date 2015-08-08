package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class AccountDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public AccountDetail(){
		 this.status = (byte)0;
	}
		
	private int accountDetailId;
	/** 
	 * 获取详情编号
	 * @return 详情编号
	 */
	public int getAccountDetailId()
	{
		return accountDetailId;
	}
	
	/**
	 * 设置详情编号
	 * @param 详情编号
	 */
	public void setAccountDetailId(int value)
	{
		accountDetailId = value;
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

	private wh.server.model.ServerAccount serverAccount;
	/**
	 * 获取服务器帐户
	 * @return 服务器帐户
	 */
	public wh.server.model.ServerAccount getServerAccount(){
		return this.serverAccount;
	}
	
	/**
	 * 设置服务器帐户
	 * @param serverAccount 服务器帐户
	 */
	public void setServerAccount(wh.server.model.ServerAccount serverAccount){
		this.serverAccount = serverAccount;
	}
	
	private int serverAccountId;
	/**
	 * 获取服务器帐户编号
	 * @return 服务器帐户编号
	 */
	public int getServerAccountId(){
		if(serverAccount != null){
			return serverAccount.getServerAccountId();
		}else{
			return serverAccountId;
		}
	}
	
	/**
	 * 设置服务器帐户编号
	 * @param 服务器帐户编号
	 */
	public void setServerAccountId(int value){
		if(serverAccount != null){
			serverAccount.setServerAccountId(value);
		}else{
			serverAccountId = value;
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
	

	private wh.member.model.Account account;
	/**
	 * 获取帐户
	 * @return 帐户
	 */
	public wh.member.model.Account getAccount(){
		return this.account;
	}
	
	/**
	 * 设置帐户
	 * @param account 帐户
	 */
	public void setAccount(wh.member.model.Account account){
		this.account = account;
	}
	
	private int accountId;
	/**
	 * 获取帐户编号
	 * @return 帐户编号
	 */
	public int getAccountId(){
		if(account != null){
			return account.getAccountId();
		}else{
			return accountId;
		}
	}
	
	/**
	 * 设置帐户编号
	 * @param 帐户编号
	 */
	public void setAccountId(int value){
		if(account != null){
			account.setAccountId(value);
		}else{
			accountId = value;
		}
	}
	
	
	private BigDecimal accountValue;
	/** 
	 * 获取帐户值
	 * @return 帐户值
	 */
	public BigDecimal getAccountValue()
	{
		return accountValue;
	}
	
	/**
	 * 设置帐户值
	 * @param 帐户值
	 */
	public void setAccountValue(BigDecimal value)
	{
		accountValue = value;
	}
	

	private byte useType;
	/**
	 * 获取使用类型：0、使用类型；1、获得；2、消费；
	 * @return 使用类型：0、使用类型；1、获得；2、消费；
	 */
	public byte getUseType(){
		return useType;
	}
	
	/**
	 * 设置使用类型：0、使用类型；1、获得；2、消费；
	 * @param 使用类型：0、使用类型；1、获得；2、消费；
	 */
	public void setUseType(byte value){
		useType = value;
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


