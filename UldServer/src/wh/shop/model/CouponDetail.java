package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class CouponDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public CouponDetail(){
		;
	}
		
	private int couponDetailId;
	/** 
	 * 获取优惠券详情编号
	 * @return 优惠券详情编号
	 */
	public int getCouponDetailId()
	{
		return couponDetailId;
	}
	
	/**
	 * 设置优惠券详情编号
	 * @param 优惠券详情编号
	 */
	public void setCouponDetailId(int value)
	{
		couponDetailId = value;
	}
	

	private wh.shop.model.Coupon coupon;
	/**
	 * 获取优惠券
	 * @return 优惠券
	 */
	public wh.shop.model.Coupon getCoupon(){
		return this.coupon;
	}
	
	/**
	 * 设置优惠券
	 * @param coupon 优惠券
	 */
	public void setCoupon(wh.shop.model.Coupon coupon){
		this.coupon = coupon;
	}
	
	private int couponId;
	/**
	 * 获取优惠券编号
	 * @return 优惠券编号
	 */
	public int getCouponId(){
		if(coupon != null){
			return coupon.getCouponId();
		}else{
			return couponId;
		}
	}
	
	/**
	 * 设置优惠券编号
	 * @param 优惠券编号
	 */
	public void setCouponId(int value){
		if(coupon != null){
			coupon.setCouponId(value);
		}else{
			couponId = value;
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
	
	
	private String remark;
	/** 
	 * 获取备注
	 * @return 备注
	 */
	public String getRemark()
	{
		return remark;
	}
	
	/**
	 * 设置备注
	 * @param 备注
	 */
	public void setRemark(String value)
	{
		remark = value;
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


