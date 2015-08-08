package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class GoodsOrderDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public GoodsOrderDetail(){
		 this.status = (byte)0;
	}
		
	private int goodsOrderDetailId;
	/** 
	 * 获取商品订单详情编号
	 * @return 商品订单详情编号
	 */
	public int getGoodsOrderDetailId()
	{
		return goodsOrderDetailId;
	}
	
	/**
	 * 设置商品订单详情编号
	 * @param 商品订单详情编号
	 */
	public void setGoodsOrderDetailId(int value)
	{
		goodsOrderDetailId = value;
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
	

	private wh.shop.model.Goods goods;
	/**
	 * 获取商品
	 * @return 商品
	 */
	public wh.shop.model.Goods getGoods(){
		return this.goods;
	}
	
	/**
	 * 设置商品
	 * @param goods 商品
	 */
	public void setGoods(wh.shop.model.Goods goods){
		this.goods = goods;
	}
	
	private int goodsId;
	/**
	 * 获取商品编号
	 * @return 商品编号
	 */
	public int getGoodsId(){
		if(goods != null){
			return goods.getGoodsId();
		}else{
			return goodsId;
		}
	}
	
	/**
	 * 设置商品编号
	 * @param 商品编号
	 */
	public void setGoodsId(int value){
		if(goods != null){
			goods.setGoodsId(value);
		}else{
			goodsId = value;
		}
	}

	/**
	 * 获取商品名称
	 * @return 商品名称
	 */
	public String getGoodsName(){
		if(goods != null){
			return goods.getName();
		}else{
			return "";
		}
	
	}	
	
	private int subCategoryId;
	/** 
	 * 获取子类型编号
	 * @return 子类型编号
	 */
	public int getSubCategoryId()
	{
		return subCategoryId;
	}
	
	/**
	 * 设置子类型编号
	 * @param 子类型编号
	 */
	public void setSubCategoryId(int value)
	{
		subCategoryId = value;
	}
	
	
	private int categoryParentId;
	/** 
	 * 获取类型父编号
	 * @return 类型父编号
	 */
	public int getCategoryParentId()
	{
		return categoryParentId;
	}
	
	/**
	 * 设置类型父编号
	 * @param 类型父编号
	 */
	public void setCategoryParentId(int value)
	{
		categoryParentId = value;
	}
	
	
	private String categoryParentIdList;
	/** 
	 * 获取父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 * @return 父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 */
	public String getCategoryParentIdList()
	{
		return categoryParentIdList;
	}
	
	/**
	 * 设置父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 * @param 父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 */
	public void setCategoryParentIdList(String value)
	{
		categoryParentIdList = value;
	}
	
	
	private int quantity;
	/** 
	 * 获取数量
	 * @return 数量
	 */
	public int getQuantity()
	{
		return quantity;
	}
	
	/**
	 * 设置数量
	 * @param 数量
	 */
	public void setQuantity(int value)
	{
		quantity = value;
	}
	

	private byte paymentType;
	/**
	 * 获取支付类型：0、支付类型；1、幸运点；2、平台币；4、幸运点平台币；
	 * @return 支付类型：0、支付类型；1、幸运点；2、平台币；4、幸运点平台币；
	 */
	public byte getPaymentType(){
		return paymentType;
	}
	
	/**
	 * 设置支付类型：0、支付类型；1、幸运点；2、平台币；4、幸运点平台币；
	 * @param 支付类型：0、支付类型；1、幸运点；2、平台币；4、幸运点平台币；
	 */
	public void setPaymentType(byte value){
		paymentType = value;
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


