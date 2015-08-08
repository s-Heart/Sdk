package wh.content.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Content implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Content(){
		 this.status = (byte)0;
	}
		
	private int contentId;
	/** 
	 * 获取内容编号
	 * @return 内容编号
	 */
	public int getContentId()
	{
		return contentId;
	}
	
	/**
	 * 设置内容编号
	 * @param 内容编号
	 */
	public void setContentId(int value)
	{
		contentId = value;
	}
	

	private wh.content.model.Category category;
	/**
	 * 获取类别表
	 * @return 类别表
	 */
	public wh.content.model.Category getCategory(){
		return this.category;
	}
	
	/**
	 * 设置类别表
	 * @param category 类别表
	 */
	public void setCategory(wh.content.model.Category category){
		this.category = category;
	}
	
	private int categoryId;
	/**
	 * 获取类别编号
	 * @return 类别编号
	 */
	public int getCategoryId(){
		if(category != null){
			return category.getCategoryId();
		}else{
			return categoryId;
		}
	}
	
	/**
	 * 设置类别编号
	 * @param 类别编号
	 */
	public void setCategoryId(int value){
		if(category != null){
			category.setCategoryId(value);
		}else{
			categoryId = value;
		}
	}
	/**
	 * 获取类别名称
	 * @return 类别名称
	 */
	public String getCategoryName(){
		if(category != null){
			return category.getCategoryName();
		}else{
			return "";
		}
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
	
	
	private int fKTableId;
	/** 
	 * 获取外键表编号
	 * @return 外键表编号
	 */
	public int getFKTableId()
	{
		return fKTableId;
	}
	
	/**
	 * 设置外键表编号
	 * @param 外键表编号
	 */
	public void setFKTableId(int value)
	{
		fKTableId = value;
	}
	

	private byte fKTableNameType;
	/**
	 * 获取外键表名称类型：0、外键表名称类型；1、服务器；2、游戏；
	 * @return 外键表名称类型：0、外键表名称类型；1、服务器；2、游戏；
	 */
	public byte getFKTableNameType(){
		return fKTableNameType;
	}
	
	/**
	 * 设置外键表名称类型：0、外键表名称类型；1、服务器；2、游戏；
	 * @param 外键表名称类型：0、外键表名称类型；1、服务器；2、游戏；
	 */
	public void setFKTableNameType(byte value){
		fKTableNameType = value;
	}	
	
	private String contentName;
	/** 
	 * 获取名称
	 * @return 名称
	 */
	public String getContentName()
	{
		return contentName;
	}
	
	/**
	 * 设置名称
	 * @param 名称
	 */
	public void setContentName(String value)
	{
		contentName = value;
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
	

	private Date beginDate;
	/**
	 * 获取开始日期
	 * @return 开始日期
	 */
	public Date getBeginDate()
	{   
		if(beginDate == null){
			return Utility.getDbMinDate();
		}
		return beginDate;
	}
	
	/**
	 * 设置开始日期
	 * @param 开始日期
	 */
	public void setBeginDate(Date value)
	{   
		beginDate = value;
	}
	
	private Date beginDateBegin;
	/**
	 * 获取开始日期
	 * @return 开始日期
	 */
	public Date getBeginDateBegin()
	{   
		if(beginDateBegin == null){
			return Utility.getDbMinDate();
		}
		return beginDateBegin;
	}
	
	/**
	 * 设置开始日期
	 * @param 开始日期
	 */
	public void setBeginDateBegin(Date value)
	{   
		beginDateBegin = value;
	}
	
	private Date beginDateEnd;
	/**
	 * 获取开始日期
	 * @return 开始日期
	 */
	public Date getBeginDateEnd()
	{   
		if(beginDateEnd == null){
			return Utility.getDbMinDate();
		}
		return beginDateEnd;
	}
	
	/**
	 * 设置开始日期
	 * @param 开始日期
	 */
	public void setBeginDateEnd(Date value)
	{   
		beginDateEnd = value;
	}
	
	

	private Date endDate;
	/**
	 * 获取结束日期
	 * @return 结束日期
	 */
	public Date getEndDate()
	{   
		if(endDate == null){
			return Utility.getDbMinDate();
		}
		return endDate;
	}
	
	/**
	 * 设置结束日期
	 * @param 结束日期
	 */
	public void setEndDate(Date value)
	{   
		endDate = value;
	}
	
	private Date endDateBegin;
	/**
	 * 获取结束日期
	 * @return 结束日期
	 */
	public Date getEndDateBegin()
	{   
		if(endDateBegin == null){
			return Utility.getDbMinDate();
		}
		return endDateBegin;
	}
	
	/**
	 * 设置结束日期
	 * @param 结束日期
	 */
	public void setEndDateBegin(Date value)
	{   
		endDateBegin = value;
	}
	
	private Date endDateEnd;
	/**
	 * 获取结束日期
	 * @return 结束日期
	 */
	public Date getEndDateEnd()
	{   
		if(endDateEnd == null){
			return Utility.getDbMinDate();
		}
		return endDateEnd;
	}
	
	/**
	 * 设置结束日期
	 * @param 结束日期
	 */
	public void setEndDateEnd(Date value)
	{   
		endDateEnd = value;
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
	
	
	private String videosPath;
	/** 
	 * 获取视频路径
	 * @return 视频路径
	 */
	public String getVideosPath()
	{
		return videosPath;
	}
	
	/**
	 * 设置视频路径
	 * @param 视频路径
	 */
	public void setVideosPath(String value)
	{
		videosPath = value;
	}
	
	
	private String linkUrl;
	/** 
	 * 获取链接地址
	 * @return 链接地址
	 */
	public String getLinkUrl()
	{
		return linkUrl;
	}
	
	/**
	 * 设置链接地址
	 * @param 链接地址
	 */
	public void setLinkUrl(String value)
	{
		linkUrl = value;
	}
	
	
	private String field9;
	/** 
	 * 获取字段1
	 * @return 字段1
	 */
	public String getField9()
	{
		return field9;
	}
	
	/**
	 * 设置字段1
	 * @param 字段1
	 */
	public void setField9(String value)
	{
		field9 = value;
	}
	
	
	private String field10;
	/** 
	 * 获取字段2
	 * @return 字段2
	 */
	public String getField10()
	{
		return field10;
	}
	
	/**
	 * 设置字段2
	 * @param 字段2
	 */
	public void setField10(String value)
	{
		field10 = value;
	}
	
	
	private String field11;
	/** 
	 * 获取字段3
	 * @return 字段3
	 */
	public String getField11()
	{
		return field11;
	}
	
	/**
	 * 设置字段3
	 * @param 字段3
	 */
	public void setField11(String value)
	{
		field11 = value;
	}
	
	
	private String field12;
	/** 
	 * 获取字段4
	 * @return 字段4
	 */
	public String getField12()
	{
		return field12;
	}
	
	/**
	 * 设置字段4
	 * @param 字段4
	 */
	public void setField12(String value)
	{
		field12 = value;
	}
	

	private byte recommendType;
	/**
	 * 获取是否推荐：0、推荐类型；1、推荐；2、不推荐；
	 * @return 是否推荐：0、推荐类型；1、推荐；2、不推荐；
	 */
	public byte getRecommendType(){
		return recommendType;
	}
	
	/**
	 * 设置是否推荐：0、推荐类型；1、推荐；2、不推荐；
	 * @param 是否推荐：0、推荐类型；1、推荐；2、不推荐；
	 */
	public void setRecommendType(byte value){
		recommendType = value;
	}	

	private byte displayPositionType;
	/**
	 * 获取显示位置：0、显示位置；1、首页；2、新闻页；
	 * @return 显示位置：0、显示位置；1、首页；2、新闻页；
	 */
	public byte getDisplayPositionType(){
		return displayPositionType;
	}
	
	/**
	 * 设置显示位置：0、显示位置；1、首页；2、新闻页；
	 * @param 显示位置：0、显示位置；1、首页；2、新闻页；
	 */
	public void setDisplayPositionType(byte value){
		displayPositionType = value;
	}	
	
	private int orderIndex;
	/** 
	 * 获取排序标识，倒序排列
	 * @return 排序标识，倒序排列
	 */
	public int getOrderIndex()
	{
		return orderIndex;
	}
	
	/**
	 * 设置排序标识，倒序排列
	 * @param 排序标识，倒序排列
	 */
	public void setOrderIndex(int value)
	{
		orderIndex = value;
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
	
	private int categoryParentId;
	/** 
	 * 获取类型父编号
	 * @return 类型父编号
	 */
	public int getCategoryParentId()
	{
		return categoryParentId;
	}
	
	/**
	 * 设置类型父编号
	 * @param 类型父编号
	 */
	public void setCategoryParentId(int value)
	{
		categoryParentId = value;
	}
	
	
	private String categoryParentIdList;
	/** 
	 * 获取父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 * @return 父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 */
	public String getCategoryParentIdList()
	{
		return categoryParentIdList;
	}
	
	/**
	 * 设置父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 * @param 父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 */
	public void setCategoryParentIdList(String value)
	{
		categoryParentIdList = value;
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
	

	private Date modifyDate;
	/**
	 * 获取修改日期
	 * @return 修改日期
	 */
	public Date getModifyDate()
	{   
		if(modifyDate == null){
			return Utility.getDbMinDate();
		}
		return modifyDate;
	}
	
	/**
	 * 设置修改日期
	 * @param 修改日期
	 */
	public void setModifyDate(Date value)
	{   
		modifyDate = value;
	}
	
	private Date modifyDateBegin;
	/**
	 * 获取修改日期
	 * @return 修改日期
	 */
	public Date getModifyDateBegin()
	{   
		if(modifyDateBegin == null){
			return Utility.getDbMinDate();
		}
		return modifyDateBegin;
	}
	
	/**
	 * 设置修改日期
	 * @param 修改日期
	 */
	public void setModifyDateBegin(Date value)
	{   
		modifyDateBegin = value;
	}
	
	private Date modifyDateEnd;
	/**
	 * 获取修改日期
	 * @return 修改日期
	 */
	public Date getModifyDateEnd()
	{   
		if(modifyDateEnd == null){
			return Utility.getDbMinDate();
		}
		return modifyDateEnd;
	}
	
	/**
	 * 设置修改日期
	 * @param 修改日期
	 */
	public void setModifyDateEnd(Date value)
	{   
		modifyDateEnd = value;
	}
	
	
}	


