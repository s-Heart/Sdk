package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class ShoppingCart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public ShoppingCart(){
		;
	}
		
	private int shoppingCartId;
	/** 
	 * 获取购物车编号
	 * @return 购物车编号
	 */
	public int getShoppingCartId()
	{
		return shoppingCartId;
	}
	
	/**
	 * 设置购物车编号
	 * @param 购物车编号
	 */
	public void setShoppingCartId(int value)
	{
		shoppingCartId = value;
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
	
	
}	


