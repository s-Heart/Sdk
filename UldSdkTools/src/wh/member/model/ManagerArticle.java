package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class ManagerArticle implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public ManagerArticle(){
		 this.status = (byte)0;
	}
		
	private int managerArticleId;
	/** 
	 * 获取感想编号
	 * @return 感想编号
	 */
	public int getManagerArticleId()
	{
		return managerArticleId;
	}
	
	/**
	 * 设置感想编号
	 * @param 感想编号
	 */
	public void setManagerArticleId(int value)
	{
		managerArticleId = value;
	}
	
	
	private int mAManagerId;
	/** 
	 * 获取发布人编号
	 * @return 发布人编号
	 */
	public int getMAManagerId()
	{
		return mAManagerId;
	}
	
	/**
	 * 设置发布人编号
	 * @param 发布人编号
	 */
	public void setMAManagerId(int value)
	{
		mAManagerId = value;
	}
	
	
	private String mAManagerName;
	/** 
	 * 获取发布人名称
	 * @return 发布人名称
	 */
	public String getMAManagerName()
	{
		return mAManagerName;
	}
	
	/**
	 * 设置发布人名称
	 * @param 发布人名称
	 */
	public void setMAManagerName(String value)
	{
		mAManagerName = value;
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
	
	
	private String contentDescription;
	/** 
	 * 获取内容描述
	 * @return 内容描述
	 */
	public String getContentDescription()
	{
		return contentDescription;
	}
	
	/**
	 * 设置内容描述
	 * @param 内容描述
	 */
	public void setContentDescription(String value)
	{
		contentDescription = value;
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


