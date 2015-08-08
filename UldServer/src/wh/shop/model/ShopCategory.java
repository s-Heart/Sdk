package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class ShopCategory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public ShopCategory(){
		;
	}
		
	private int shopCategoryId;
	/** 
	 * 获取商城分类编号
	 * @return 商城分类编号
	 */
	public int getShopCategoryId()
	{
		return shopCategoryId;
	}
	
	/**
	 * 设置商城分类编号
	 * @param 商城分类编号
	 */
	public void setShopCategoryId(int value)
	{
		shopCategoryId = value;
	}
	
	
	private int parentId;
	/** 
	 * 获取父编号
	 * @return 父编号
	 */
	public int getParentId()
	{
		return parentId;
	}
	
	/**
	 * 设置父编号
	 * @param 父编号
	 */
	public void setParentId(int value)
	{
		parentId = value;
	}
	
	
	private String parentIdList;
	/** 
	 * 获取父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 * @return 父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 */
	public String getParentIdList()
	{
		return parentIdList;
	}
	
	/**
	 * 设置父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 * @param 父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 */
	public void setParentIdList(String value)
	{
		parentIdList = value;
	}
	
	
	private int level;
	/** 
	 * 获取级别，从0开始，0为最高级别
	 * @return 级别，从0开始，0为最高级别
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * 设置级别，从0开始，0为最高级别
	 * @param 级别，从0开始，0为最高级别
	 */
	public void setLevel(int value)
	{
		level = value;
	}
	
	
	private String categoryName;
	/** 
	 * 获取名称
	 * @return 名称
	 */
	public String getCategoryName()
	{
		return categoryName;
	}
	
	/**
	 * 设置名称
	 * @param 名称
	 */
	public void setCategoryName(String value)
	{
		categoryName = value;
	}
	
	
	private String categoryDescription;
	/** 
	 * 获取描述
	 * @return 描述
	 */
	public String getCategoryDescription()
	{
		return categoryDescription;
	}
	
	/**
	 * 设置描述
	 * @param 描述
	 */
	public void setCategoryDescription(String value)
	{
		categoryDescription = value;
	}
	
	
	private String field9;
	/** 
	 * 获取字段1
	 * @return 字段1
	 */
	public String getField9()
	{
		return field9;
	}
	
	/**
	 * 设置字段1
	 * @param 字段1
	 */
	public void setField9(String value)
	{
		field9 = value;
	}
	
	
	private String field10;
	/** 
	 * 获取字段2
	 * @return 字段2
	 */
	public String getField10()
	{
		return field10;
	}
	
	/**
	 * 设置字段2
	 * @param 字段2
	 */
	public void setField10(String value)
	{
		field10 = value;
	}
	
	
	private String field11;
	/** 
	 * 获取字段3
	 * @return 字段3
	 */
	public String getField11()
	{
		return field11;
	}
	
	/**
	 * 设置字段3
	 * @param 字段3
	 */
	public void setField11(String value)
	{
		field11 = value;
	}
	
	
	private String field12;
	/** 
	 * 获取字段4
	 * @return 字段4
	 */
	public String getField12()
	{
		return field12;
	}
	
	/**
	 * 设置字段4
	 * @param 字段4
	 */
	public void setField12(String value)
	{
		field12 = value;
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
	
	
}	


