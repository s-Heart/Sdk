package wh.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class SACategory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public SACategory(){
		 this.status = (byte)0;
	}
		
	private int sACategoryId;
	/** 
	 * 获取服务器帐户分类编号
	 * @return 服务器帐户分类编号
	 */
	public int getSACategoryId()
	{
		return sACategoryId;
	}
	
	/**
	 * 设置服务器帐户分类编号
	 * @param 服务器帐户分类编号
	 */
	public void setSACategoryId(int value)
	{
		sACategoryId = value;
	}
	

	private wh.member.model.Manager manager;
	/**
	 * 获取管理员
	 * @return 管理员
	 */
	public wh.member.model.Manager getManager(){
		return this.manager;
	}
	
	/**
	 * 设置管理员
	 * @param manager 管理员
	 */
	public void setManager(wh.member.model.Manager manager){
		this.manager = manager;
	}
	
	private int managerId;
	/**
	 * 获取管理员编号
	 * @return 管理员编号
	 */
	public int getManagerId(){
		if(manager != null){
			return manager.getManagerId();
		}else{
			return managerId;
		}
	}
	
	/**
	 * 设置管理员编号
	 * @param 管理员编号
	 */
	public void setManagerId(int value){
		if(manager != null){
			manager.setManagerId(value);
		}else{
			managerId = value;
		}
	}
	
	
	private String sACategoryName;
	/** 
	 * 获取分类名称
	 * @return 分类名称
	 */
	public String getSACategoryName()
	{
		return sACategoryName;
	}
	
	/**
	 * 设置分类名称
	 * @param 分类名称
	 */
	public void setSACategoryName(String value)
	{
		sACategoryName = value;
	}
	
	
	private int level;
	/** 
	 * 获取级别
	 * @return 级别
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * 设置级别
	 * @param 级别
	 */
	public void setLevel(int value)
	{
		level = value;
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


