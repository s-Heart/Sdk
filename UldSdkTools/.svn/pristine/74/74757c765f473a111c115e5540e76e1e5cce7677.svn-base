package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class GoodsLabel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public GoodsLabel(){
		;
	}
		
	private int goodsLabelId;
	/** 
	 * 获取商品标签编号
	 * @return 商品标签编号
	 */
	public int getGoodsLabelId()
	{
		return goodsLabelId;
	}
	
	/**
	 * 设置商品标签编号
	 * @param 商品标签编号
	 */
	public void setGoodsLabelId(int value)
	{
		goodsLabelId = value;
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
	
	private String name;
	/** 
	 * 获取名称
	 * @return 名称
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * 设置名称
	 * @param 名称
	 */
	public void setName(String value)
	{
		name = value;
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
	
	
	
	private int searchAmount;
	/** 
	 * 获取搜索数量
	 * @return 搜索数量
	 */
	public int getSearchAmount()
	{
		return searchAmount;
	}
	
	/**
	 * 设置搜索数量
	 * @param 搜索数量
	 */
	public void setSearchAmount(int value)
	{
		searchAmount = value;
	}
	
}	


