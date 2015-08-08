package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Shipping implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Shipping(){
		;
	}
		
	private int shippingId;
	/** 
	 * 获取运费编号
	 * @return 运费编号
	 */
	public int getShippingId()
	{
		return shippingId;
	}
	
	/**
	 * 设置运费编号
	 * @param 运费编号
	 */
	public void setShippingId(int value)
	{
		shippingId = value;
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

	private wh.shop.model.City city;
	/**
	 * 获取市
	 * @return 市
	 */
	public wh.shop.model.City getCity(){
		return this.city;
	}
	
	/**
	 * 设置市
	 * @param city 市
	 */
	public void setCity(wh.shop.model.City city){
		this.city = city;
	}
	
	private int cityId;
	/**
	 * 获取市编号
	 * @return 市编号
	 */
	public int getCityId(){
		if(city != null){
			return city.getCityId();
		}else{
			return cityId;
		}
	}
	
	/**
	 * 设置市编号
	 * @param 市编号
	 */
	public void setCityId(int value){
		if(city != null){
			city.setCityId(value);
		}else{
			cityId = value;
		}
	}

	/**
	 * 获取市名称
	 * @return 市名称
	 */
	public String getCityName(){
		if(city != null){
			return city.getName();
		}else{
			return "";
		}
	
	}	

	private wh.shop.model.District district;
	/**
	 * 获取区县
	 * @return 区县
	 */
	public wh.shop.model.District getDistrict(){
		return this.district;
	}
	
	/**
	 * 设置区县
	 * @param district 区县
	 */
	public void setDistrict(wh.shop.model.District district){
		this.district = district;
	}
	
	private int districtId;
	/**
	 * 获取区县编号
	 * @return 区县编号
	 */
	public int getDistrictId(){
		if(district != null){
			return district.getDistrictId();
		}else{
			return districtId;
		}
	}
	
	/**
	 * 设置区县编号
	 * @param 区县编号
	 */
	public void setDistrictId(int value){
		if(district != null){
			district.setDistrictId(value);
		}else{
			districtId = value;
		}
	}

	/**
	 * 获取区县名称
	 * @return 区县名称
	 */
	public String getDistrictName(){
		if(district != null){
			return district.getName();
		}else{
			return "";
		}
	
	}	
	
	private int price;
	/** 
	 * 获取价格
	 * @return 价格
	 */
	public int getPrice()
	{
		return price;
	}
	
	/**
	 * 设置价格
	 * @param 价格
	 */
	public void setPrice(int value)
	{
		price = value;
	}
	
}	


