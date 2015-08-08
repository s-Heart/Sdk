package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Product(){
		 this.status = (byte)0;
	}
		
	private int productId;
	/** 
	 * 获取产品编号
	 * @return 产品编号
	 */
	public int getProductId()
	{
		return productId;
	}
	
	/**
	 * 设置产品编号
	 * @param 产品编号
	 */
	public void setProductId(int value)
	{
		productId = value;
	}
	

	private wh.shop.model.GoodsCategory goodsCategory;
	/**
	 * 获取商品分类
	 * @return 商品分类
	 */
	public wh.shop.model.GoodsCategory getGoodsCategory(){
		return this.goodsCategory;
	}
	
	/**
	 * 设置商品分类
	 * @param goodsCategory 商品分类
	 */
	public void setGoodsCategory(wh.shop.model.GoodsCategory goodsCategory){
		this.goodsCategory = goodsCategory;
	}
	
	private int goodsCategoryId;
	/**
	 * 获取商品分类编号
	 * @return 商品分类编号
	 */
	public int getGoodsCategoryId(){
		if(goodsCategory != null){
			return goodsCategory.getGoodsCategoryId();
		}else{
			return goodsCategoryId;
		}
	}
	
	/**
	 * 设置商品分类编号
	 * @param 商品分类编号
	 */
	public void setGoodsCategoryId(int value){
		if(goodsCategory != null){
			goodsCategory.setGoodsCategoryId(value);
		}else{
			goodsCategoryId = value;
		}
	}

	/**
	 * 获取商品分类名称
	 * @return 商品分类名称
	 */
	public String getGoodsCategoryName(){
		if(goodsCategory != null){
			return goodsCategory.getName();
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


