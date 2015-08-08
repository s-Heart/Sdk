﻿package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class City implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public City(){
		;
	}
		
	private int cityId;
	/** 
	 * 获取市编号
	 * @return 市编号
	 */
	public int getCityId()
	{
		return cityId;
	}
	
	/**
	 * 设置市编号
	 * @param 市编号
	 */
	public void setCityId(int value)
	{
		cityId = value;
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
	
	
	private String spellName;
	/** 
	 * 获取拼音名称
	 * @return 拼音名称
	 */
	public String getSpellName()
	{
		return spellName;
	}
	
	/**
	 * 设置拼音名称
	 * @param 拼音名称
	 */
	public void setSpellName(String value)
	{
		spellName = value;
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
	
	

	private wh.shop.model.Province province;
	/**
	 * 获取省
	 * @return 省
	 */
	public wh.shop.model.Province getProvince(){
		return this.province;
	}
	
	/**
	 * 设置省
	 * @param province 省
	 */
	public void setProvince(wh.shop.model.Province province){
		this.province = province;
	}
	
	private int provinceId;
	/**
	 * 获取省编号
	 * @return 省编号
	 */
	public int getProvinceId(){
		if(province != null){
			return province.getProvinceId();
		}else{
			return provinceId;
		}
	}
	
	/**
	 * 设置省编号
	 * @param 省编号
	 */
	public void setProvinceId(int value){
		if(province != null){
			province.setProvinceId(value);
		}else{
			provinceId = value;
		}
	}

	/**
	 * 获取省名称
	 * @return 省名称
	 */
	public String getProvinceName(){
		if(province != null){
			return province.getName();
		}else{
			return "";
		}
	
	}	
}	


