package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class GoodsOrderLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public GoodsOrderLog(){
		;
	}
		
	private int goodsOrderLogId;
	/** 
	 * 获取商品订单日志编号
	 * @return 商品订单日志编号
	 */
	public int getGoodsOrderLogId()
	{
		return goodsOrderLogId;
	}
	
	/**
	 * 设置商品订单日志编号
	 * @param 商品订单日志编号
	 */
	public void setGoodsOrderLogId(int value)
	{
		goodsOrderLogId = value;
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

	private wh.shop.model.GoodsOrder goodsOrder;
	/**
	 * 获取商品订单
	 * @return 商品订单
	 */
	public wh.shop.model.GoodsOrder getGoodsOrder(){
		return this.goodsOrder;
	}
	
	/**
	 * 设置商品订单
	 * @param goodsOrder 商品订单
	 */
	public void setGoodsOrder(wh.shop.model.GoodsOrder goodsOrder){
		this.goodsOrder = goodsOrder;
	}
	
	private int goodsOrderId;
	/**
	 * 获取商品订单编号
	 * @return 商品订单编号
	 */
	public int getGoodsOrderId(){
		if(goodsOrder != null){
			return goodsOrder.getGoodsOrderId();
		}else{
			return goodsOrderId;
		}
	}
	
	/**
	 * 设置商品订单编号
	 * @param 商品订单编号
	 */
	public void setGoodsOrderId(int value){
		if(goodsOrder != null){
			goodsOrder.setGoodsOrderId(value);
		}else{
			goodsOrderId = value;
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
	

	private int shopOrderType;
	/**
	 * 获取订单类型：0、订单类型；1、未发货；2、发货中；4、已发货；8、已签收；16、已完成；32、已取消；64、退款申请；128、退货中；256、已退货；
	 * @return 订单类型：0、订单类型；1、未发货；2、发货中；4、已发货；8、已签收；16、已完成；32、已取消；64、退款申请；128、退货中；256、已退货；
	 */
	public int getShopOrderType(){
		return shopOrderType;
	}
	
	/**
	 * 设置订单类型：0、订单类型；1、未发货；2、发货中；4、已发货；8、已签收；16、已完成；32、已取消；64、退款申请；128、退货中；256、已退货；
	 * @param 订单类型：0、订单类型；1、未发货；2、发货中；4、已发货；8、已签收；16、已完成；32、已取消；64、退款申请；128、退货中；256、已退货；
	 */
	public void setShopOrderType(int value){
		shopOrderType = value;
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
	
}	


