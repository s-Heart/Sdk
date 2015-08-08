package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Province implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Province(){
		;
	}
		
	private int provinceId;
	/** 
	 * 获取省编号
	 * @return 省编号
	 */
	public int getProvinceId()
	{
		return provinceId;
	}
	
	/**
	 * 设置省编号
	 * @param 省编号
	 */
	public void setProvinceId(int value)
	{
		provinceId = value;
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
	
	
}	


