package wh.promotion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class RegTemplate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public RegTemplate(){
		 this.status = (byte)0;
	}
		
	private int regTemplateId;
	/** 
	 * 获取注册弹出层模板编号
	 * @return 注册弹出层模板编号
	 */
	public int getRegTemplateId()
	{
		return regTemplateId;
	}
	
	/**
	 * 设置注册弹出层模板编号
	 * @param 注册弹出层模板编号
	 */
	public void setRegTemplateId(int value)
	{
		regTemplateId = value;
	}
	
	
	private String description;
	/** 
	 * 获取描述
	 * @return 描述
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * 设置描述
	 * @param 描述
	 */
	public void setDescription(String value)
	{
		description = value;
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
	
	private String bGPosterPath;
	/** 
	 * 获取背景图
	 * @return 背景图
	 */
	public String getBGPosterPath()
	{
		return bGPosterPath;
	}
	
	/**
	 * 设置背景图
	 * @param 背景图
	 */
	public void setBGPosterPath(String value)
	{
		bGPosterPath = value;
	}
	
	
	private String regPosterPath;
	/** 
	 * 获取注册图片
	 * @return 注册图片
	 */
	public String getRegPosterPath()
	{
		return regPosterPath;
	}
	
	/**
	 * 设置注册图片
	 * @param 注册图片
	 */
	public void setRegPosterPath(String value)
	{
		regPosterPath = value;
	}
	
	
	private String otherPosterPath;
	/** 
	 * 获取其它图片，多个的话以；号分隔
	 * @return 其它图片，多个的话以；号分隔
	 */
	public String getOtherPosterPath()
	{
		return otherPosterPath;
	}
	
	/**
	 * 设置其它图片，多个的话以；号分隔
	 * @param 其它图片，多个的话以；号分隔
	 */
	public void setOtherPosterPath(String value)
	{
		otherPosterPath = value;
	}
	
}	


