package wh.promotion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Poster implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Poster(){
		 this.status = (byte)0;
	}
		
	private int posterId;
	/** 
	 * 获取海报编号
	 * @return 海报编号
	 */
	public int getPosterId()
	{
		return posterId;
	}
	
	/**
	 * 设置海报编号
	 * @param 海报编号
	 */
	public void setPosterId(int value)
	{
		posterId = value;
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
	
	
	private String posterAddress;
	/** 
	 * 获取海报物理路径
	 * @return 海报物理路径
	 */
	public String getPosterAddress()
	{
		return posterAddress;
	}
	
	/**
	 * 设置海报物理路径
	 * @param 海报物理路径
	 */
	public void setPosterAddress(String value)
	{
		posterAddress = value;
	}
	
	
	private String flashPath;
	/** 
	 * 获取Flash路径
	 * @return Flash路径
	 */
	public String getFlashPath()
	{
		return flashPath;
	}
	
	/**
	 * 设置Flash路径
	 * @param Flash路径
	 */
	public void setFlashPath(String value)
	{
		flashPath = value;
	}
	
	
	private BigDecimal extendedCost;
	/** 
	 * 获取推广成本
	 * @return 推广成本
	 */
	public BigDecimal getExtendedCost()
	{
		return extendedCost;
	}
	
	/**
	 * 设置推广成本
	 * @param 推广成本
	 */
	public void setExtendedCost(BigDecimal value)
	{
		extendedCost = value;
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
	
	private String code;
	/** 
	 * 获取编码
	 * @return 编码
	 */
	public String getCode()
	{
		return code;
	}
	
	/**
	 * 设置编码
	 * @param 编码
	 */
	public void setCode(String value)
	{
		code = value;
	}
	

	private wh.promotion.model.PosterCategory posterCategory;
	/**
	 * 获取海报分类
	 * @return 海报分类
	 */
	public wh.promotion.model.PosterCategory getPosterCategory(){
		return this.posterCategory;
	}
	
	/**
	 * 设置海报分类
	 * @param posterCategory 海报分类
	 */
	public void setPosterCategory(wh.promotion.model.PosterCategory posterCategory){
		this.posterCategory = posterCategory;
	}
	
	private int posterCategoryId;
	/**
	 * 获取海报分类编号
	 * @return 海报分类编号
	 */
	public int getPosterCategoryId(){
		if(posterCategory != null){
			return posterCategory.getPosterCategoryId();
		}else{
			return posterCategoryId;
		}
	}
	
	/**
	 * 设置海报分类编号
	 * @param 海报分类编号
	 */
	public void setPosterCategoryId(int value){
		if(posterCategory != null){
			posterCategory.setPosterCategoryId(value);
		}else{
			posterCategoryId = value;
		}
	}

	/**
	 * 获取海报分类名称
	 * @return 海报分类名称
	 */
	public String getPosterCategoryName(){
		if(posterCategory != null){
			return posterCategory.getName();
		}else{
			return "";
		}
	
	}	
	
	private int width;
	/** 
	 * 获取宽度
	 * @return 宽度
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * 设置宽度
	 * @param 宽度
	 */
	public void setWidth(int value)
	{
		width = value;
	}
	
	
	private int hight;
	/** 
	 * 获取高度
	 * @return 高度
	 */
	public int getHight()
	{
		return hight;
	}
	
	/**
	 * 设置高度
	 * @param 高度
	 */
	public void setHight(int value)
	{
		hight = value;
	}
	

	private byte posterFileType;
	/**
	 * 获取海报文件类型：0、海报文件类型；1、Rar；2、Zip；3、Swf；
	 * @return 海报文件类型：0、海报文件类型；1、Rar；2、Zip；3、Swf；
	 */
	public byte getPosterFileType(){
		return posterFileType;
	}
	
	/**
	 * 设置海报文件类型：0、海报文件类型；1、Rar；2、Zip；3、Swf；
	 * @param 海报文件类型：0、海报文件类型；1、Rar；2、Zip；3、Swf；
	 */
	public void setPosterFileType(byte value){
		posterFileType = value;
	}	
}	


