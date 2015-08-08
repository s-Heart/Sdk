package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class GoodsCategory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public GoodsCategory(){
		 this.status = (byte)0;
	}
		
	private int goodsCategoryId;
	/** 
	 * 获取商品分类编号
	 * @return 商品分类编号
	 */
	public int getGoodsCategoryId()
	{
		return goodsCategoryId;
	}
	
	/**
	 * 设置商品分类编号
	 * @param 商品分类编号
	 */
	public void setGoodsCategoryId(int value)
	{
		goodsCategoryId = value;
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
	
	
	private String content;
	/** 
	 * 获取描述
	 * @return 描述
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * 设置描述
	 * @param 描述
	 */
	public void setContent(String value)
	{
		content = value;
	}
	
	
	private String posterPath;
	/** 
	 * 获取图片路径
	 * @return 图片路径
	 */
	public String getPosterPath()
	{
		return posterPath;
	}
	
	/**
	 * 设置图片路径
	 * @param 图片路径
	 */
	public void setPosterPath(String value)
	{
		posterPath = value;
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


