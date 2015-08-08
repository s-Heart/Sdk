package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class OrderAddress implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public OrderAddress(){
		;
	}
		
	private int orderAddressId;
	/** 
	 * 获取商品订单地址编号
	 * @return 商品订单地址编号
	 */
	public int getOrderAddressId()
	{
		return orderAddressId;
	}
	
	/**
	 * 设置商品订单地址编号
	 * @param 商品订单地址编号
	 */
	public void setOrderAddressId(int value)
	{
		orderAddressId = value;
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
	
	private String ring;
	/** 
	 * 获取环
	 * @return 环
	 */
	public String getRing()
	{
		return ring;
	}
	
	/**
	 * 设置环
	 * @param 环
	 */
	public void setRing(String value)
	{
		ring = value;
	}
	
	
	private String street;
	/** 
	 * 获取街道
	 * @return 街道
	 */
	public String getStreet()
	{
		return street;
	}
	
	/**
	 * 设置街道
	 * @param 街道
	 */
	public void setStreet(String value)
	{
		street = value;
	}
	
	
	private String address;
	/** 
	 * 获取地址
	 * @return 地址
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * 设置地址
	 * @param 地址
	 */
	public void setAddress(String value)
	{
		address = value;
	}
	
	
	private String postcode;
	/** 
	 * 获取邮编
	 * @return 邮编
	 */
	public String getPostcode()
	{
		return postcode;
	}
	
	/**
	 * 设置邮编
	 * @param 邮编
	 */
	public void setPostcode(String value)
	{
		postcode = value;
	}
	
	
	private String addressee;
	/** 
	 * 获取收件人
	 * @return 收件人
	 */
	public String getAddressee()
	{
		return addressee;
	}
	
	/**
	 * 设置收件人
	 * @param 收件人
	 */
	public void setAddressee(String value)
	{
		addressee = value;
	}
	
	
	private String mobilePhone;
	/** 
	 * 获取手机
	 * @return 手机
	 */
	public String getMobilePhone()
	{
		return mobilePhone;
	}
	
	/**
	 * 设置手机
	 * @param 手机
	 */
	public void setMobilePhone(String value)
	{
		mobilePhone = value;
	}
	
	
	private String tel;
	/** 
	 * 获取多个Tel，以,号分隔
	 * @return 多个Tel，以,号分隔
	 */
	public String getTel()
	{
		return tel;
	}
	
	/**
	 * 设置多个Tel，以,号分隔
	 * @param 多个Tel，以,号分隔
	 */
	public void setTel(String value)
	{
		tel = value;
	}
	
	
	private String email;
	/** 
	 * 获取电子邮件
	 * @return 电子邮件
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * 设置电子邮件
	 * @param 电子邮件
	 */
	public void setEmail(String value)
	{
		email = value;
	}
	
	
	private String qq;
	/** 
	 * 获取QQ
	 * @return QQ
	 */
	public String getQQ()
	{
		return qq;
	}
	
	/**
	 * 设置QQ
	 * @param QQ
	 */
	public void setQQ(String value)
	{
		qq = value;
	}
	
	
	private String mSN;
	/** 
	 * 获取MSN
	 * @return MSN
	 */
	public String getMSN()
	{
		return mSN;
	}
	
	/**
	 * 设置MSN
	 * @param MSN
	 */
	public void setMSN(String value)
	{
		mSN = value;
	}
	

	private byte defaultType;
	/**
	 * 获取默认类型：0、默认类型；1、是；2、否；
	 * @return 默认类型：0、默认类型；1、是；2、否；
	 */
	public byte getDefaultType(){
		return defaultType;
	}
	
	/**
	 * 设置默认类型：0、默认类型；1、是；2、否；
	 * @param 默认类型：0、默认类型；1、是；2、否；
	 */
	public void setDefaultType(byte value){
		defaultType = value;
	}	
}	


