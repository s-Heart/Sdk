package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Message(){
		 this.status = (byte)0;
	}
		
	private int messageId;
	/** 
	 * 获取消息编号
	 * @return 消息编号
	 */
	public int getMessageId()
	{
		return messageId;
	}
	
	/**
	 * 设置消息编号
	 * @param 消息编号
	 */
	public void setMessageId(int value)
	{
		messageId = value;
	}
	
	
	private String title;
	/** 
	 * 获取标题
	 * @return 标题
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * 设置标题
	 * @param 标题
	 */
	public void setTitle(String value)
	{
		title = value;
	}
	
	
	private String content;
	/** 
	 * 获取内容
	 * @return 内容
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * 设置内容
	 * @param 内容
	 */
	public void setContent(String value)
	{
		content = value;
	}
	

	private byte messageType;
	/**
	 * 获取消息类型：0、消息类型；1、系统；2、个人；
	 * @return 消息类型：0、消息类型；1、系统；2、个人；
	 */
	public byte getMessageType(){
		return messageType;
	}
	
	/**
	 * 设置消息类型：0、消息类型；1、系统；2、个人；
	 * @param 消息类型：0、消息类型；1、系统；2、个人；
	 */
	public void setMessageType(byte value){
		messageType = value;
	}	

	private Date effectiveDate;
	/**
	 * 获取生效日期
	 * @return 生效日期
	 */
	public Date getEffectiveDate()
	{   
		if(effectiveDate == null){
			return Utility.getDbMinDate();
		}
		return effectiveDate;
	}
	
	/**
	 * 设置生效日期
	 * @param 生效日期
	 */
	public void setEffectiveDate(Date value)
	{   
		effectiveDate = value;
	}
	
	private Date effectiveDateBegin;
	/**
	 * 获取生效日期
	 * @return 生效日期
	 */
	public Date getEffectiveDateBegin()
	{   
		if(effectiveDateBegin == null){
			return Utility.getDbMinDate();
		}
		return effectiveDateBegin;
	}
	
	/**
	 * 设置生效日期
	 * @param 生效日期
	 */
	public void setEffectiveDateBegin(Date value)
	{   
		effectiveDateBegin = value;
	}
	
	private Date effectiveDateEnd;
	/**
	 * 获取生效日期
	 * @return 生效日期
	 */
	public Date getEffectiveDateEnd()
	{   
		if(effectiveDateEnd == null){
			return Utility.getDbMinDate();
		}
		return effectiveDateEnd;
	}
	
	/**
	 * 设置生效日期
	 * @param 生效日期
	 */
	public void setEffectiveDateEnd(Date value)
	{   
		effectiveDateEnd = value;
	}
	
	
	
	private int effectiveDateDay;
	/** 
	 * 获取生效日期天
	 * @return 生效日期天
	 */
	public int getEffectiveDateDay()
	{
		return effectiveDateDay;
	}
	
	/**
	 * 设置生效日期天
	 * @param 生效日期天
	 */
	public void setEffectiveDateDay(int value)
	{
		effectiveDateDay = value;
	}
	

	private Date expiryDate;
	/**
	 * 获取失效日期
	 * @return 失效日期
	 */
	public Date getExpiryDate()
	{   
		if(expiryDate == null){
			return Utility.getDbMinDate();
		}
		return expiryDate;
	}
	
	/**
	 * 设置失效日期
	 * @param 失效日期
	 */
	public void setExpiryDate(Date value)
	{   
		expiryDate = value;
	}
	
	private Date expiryDateBegin;
	/**
	 * 获取失效日期
	 * @return 失效日期
	 */
	public Date getExpiryDateBegin()
	{   
		if(expiryDateBegin == null){
			return Utility.getDbMinDate();
		}
		return expiryDateBegin;
	}
	
	/**
	 * 设置失效日期
	 * @param 失效日期
	 */
	public void setExpiryDateBegin(Date value)
	{   
		expiryDateBegin = value;
	}
	
	private Date expiryDateEnd;
	/**
	 * 获取失效日期
	 * @return 失效日期
	 */
	public Date getExpiryDateEnd()
	{   
		if(expiryDateEnd == null){
			return Utility.getDbMinDate();
		}
		return expiryDateEnd;
	}
	
	/**
	 * 设置失效日期
	 * @param 失效日期
	 */
	public void setExpiryDateEnd(Date value)
	{   
		expiryDateEnd = value;
	}
	
	
	
	private int expiryDateDay;
	/** 
	 * 获取失效日期天
	 * @return 失效日期天
	 */
	public int getExpiryDateDay()
	{
		return expiryDateDay;
	}
	
	/**
	 * 设置失效日期天
	 * @param 失效日期天
	 */
	public void setExpiryDateDay(int value)
	{
		expiryDateDay = value;
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


