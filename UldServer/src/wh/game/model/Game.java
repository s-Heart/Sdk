package wh.game.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Game(){
		 this.status = (byte)0;
	}
		
	private int gameId;
	/** 
	 * 获取游戏编号
	 * @return 游戏编号
	 */
	public int getGameId()
	{
		return gameId;
	}
	
	/**
	 * 设置游戏编号
	 * @param 游戏编号
	 */
	public void setGameId(int value)
	{
		gameId = value;
	}
	

	private wh.game.model.Provider provider;
	/**
	 * 获取游戏服务商
	 * @return 游戏服务商
	 */
	public wh.game.model.Provider getProvider(){
		return this.provider;
	}
	
	/**
	 * 设置游戏服务商
	 * @param provider 游戏服务商
	 */
	public void setProvider(wh.game.model.Provider provider){
		this.provider = provider;
	}
	
	private int providerId;
	/**
	 * 获取服务商编号
	 * @return 服务商编号
	 */
	public int getProviderId(){
		if(provider != null){
			return provider.getProviderId();
		}else{
			return providerId;
		}
	}
	
	/**
	 * 设置服务商编号
	 * @param 服务商编号
	 */
	public void setProviderId(int value){
		if(provider != null){
			provider.setProviderId(value);
		}else{
			providerId = value;
		}
	}
	/**
	 * 获取服务商名称
	 * @return 服务商名称
	 */
	public String getProviderName(){
		if(provider != null){
			return provider.getProviderName();
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
	

	private wh.game.model.GameCategory gameCategory;
	/**
	 * 获取游戏类别
	 * @return 游戏类别
	 */
	public wh.game.model.GameCategory getGameCategory(){
		return this.gameCategory;
	}
	
	/**
	 * 设置游戏类别
	 * @param gameCategory 游戏类别
	 */
	public void setGameCategory(wh.game.model.GameCategory gameCategory){
		this.gameCategory = gameCategory;
	}
	
	private int gameCategoryId;
	/**
	 * 获取类别编号
	 * @return 类别编号
	 */
	public int getGameCategoryId(){
		if(gameCategory != null){
			return gameCategory.getGameCategoryId();
		}else{
			return gameCategoryId;
		}
	}
	
	/**
	 * 设置类别编号
	 * @param 类别编号
	 */
	public void setGameCategoryId(int value){
		if(gameCategory != null){
			gameCategory.setGameCategoryId(value);
		}else{
			gameCategoryId = value;
		}
	}

	/**
	 * 获取类别名称
	 * @return 类别名称
	 */
	public String getGameCategoryName(){
		if(gameCategory != null){
			return gameCategory.getName();
		}else{
			return "";
		}
	
	}	
	
	private String gameName;
	/** 
	 * 获取游戏名称
	 * @return 游戏名称
	 */
	public String getGameName()
	{
		return gameName;
	}
	
	/**
	 * 设置游戏名称
	 * @param 游戏名称
	 */
	public void setGameName(String value)
	{
		gameName = value;
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
	
	
	
	private int createDateYear;
	/** 
	 * 获取创建日期年，eg：2011
	 * @return 创建日期年，eg：2011
	 */
	public int getCreateDateYear()
	{
		return createDateYear;
	}
	
	/**
	 * 设置创建日期年，eg：2011
	 * @param 创建日期年，eg：2011
	 */
	public void setCreateDateYear(int value)
	{
		createDateYear = value;
	}
	
	
	private int createDateMonth;
	/** 
	 * 获取创建日期月，eg：201101
	 * @return 创建日期月，eg：201101
	 */
	public int getCreateDateMonth()
	{
		return createDateMonth;
	}
	
	/**
	 * 设置创建日期月，eg：201101
	 * @param 创建日期月，eg：201101
	 */
	public void setCreateDateMonth(int value)
	{
		createDateMonth = value;
	}
	
	
	private int createDateDay;
	/** 
	 * 获取创建日期天，eg：20110101
	 * @return 创建日期天，eg：20110101
	 */
	public int getCreateDateDay()
	{
		return createDateDay;
	}
	
	/**
	 * 设置创建日期天，eg：20110101
	 * @param 创建日期天，eg：20110101
	 */
	public void setCreateDateDay(int value)
	{
		createDateDay = value;
	}
	

	private byte gameType;
	/**
	 * 获取上下架状态：0、上下架状态；1、上架；2、下架；
	 * @return 上下架状态：0、上下架状态；1、上架；2、下架；
	 */
	public byte getGameType(){
		return gameType;
	}
	
	/**
	 * 设置上下架状态：0、上下架状态；1、上架；2、下架；
	 * @param 上下架状态：0、上下架状态；1、上架；2、下架；
	 */
	public void setGameType(byte value){
		gameType = value;
	}	

	private byte enableType;
	/**
	 * 获取启用状态：0、启用状态；1、开启_时间无关；2、即将开启_时间相关；4、停止_维护；8、内测_需要激活码且时间相关；
	 * @return 启用状态：0、启用状态；1、开启_时间无关；2、即将开启_时间相关；4、停止_维护；8、内测_需要激活码且时间相关；
	 */
	public byte getEnableType(){
		return enableType;
	}
	
	/**
	 * 设置启用状态：0、启用状态；1、开启_时间无关；2、即将开启_时间相关；4、停止_维护；8、内测_需要激活码且时间相关；
	 * @param 启用状态：0、启用状态；1、开启_时间无关；2、即将开启_时间相关；4、停止_维护；8、内测_需要激活码且时间相关；
	 */
	public void setEnableType(byte value){
		enableType = value;
	}	

	private byte status;
	/**
	 * 获取状态：0、状态；1、显示；2、不显示；
	 * @return 状态：0、状态；1、显示；2、不显示；
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * 设置状态：0、状态；1、显示；2、不显示；
	 * @param 状态：0、状态；1、显示；2、不显示；
	 */
	public void setStatus(byte value){
		status = value;
	}	

	private byte gameMoneyType;
	/**
	 * 获取游戏币类型：0、游戏币类型；1、元宝；2、金子；3、仙币；4、点卷；
	 * @return 游戏币类型：0、游戏币类型；1、元宝；2、金子；3、仙币；4、点卷；
	 */
	public byte getGameMoneyType(){
		return gameMoneyType;
	}
	
	/**
	 * 设置游戏币类型：0、游戏币类型；1、元宝；2、金子；3、仙币；4、点卷；
	 * @param 游戏币类型：0、游戏币类型；1、元宝；2、金子；3、仙币；4、点卷；
	 */
	public void setGameMoneyType(byte value){
		gameMoneyType = value;
	}	
	
	private int gameMoneyRate;
	/** 
	 * 获取游戏币兑换比例，比如1元兑换50元宝
	 * @return 游戏币兑换比例，比如1元兑换50元宝
	 */
	public int getGameMoneyRate()
	{
		return gameMoneyRate;
	}
	
	/**
	 * 设置游戏币兑换比例，比如1元兑换50元宝
	 * @param 游戏币兑换比例，比如1元兑换50元宝
	 */
	public void setGameMoneyRate(int value)
	{
		gameMoneyRate = value;
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
	
	private String homeUrl;
	/** 
	 * 获取官网地址
	 * @return 官网地址
	 */
	public String getHomeUrl()
	{
		return homeUrl;
	}
	
	/**
	 * 设置官网地址
	 * @param 官网地址
	 */
	public void setHomeUrl(String value)
	{
		homeUrl = value;
	}
	
	
	private String cPosterPath;
	/** 
	 * 获取推荐游戏图片
	 * @return 推荐游戏图片
	 */
	public String getCPosterPath()
	{
		return cPosterPath;
	}
	
	/**
	 * 设置推荐游戏图片
	 * @param 推荐游戏图片
	 */
	public void setCPosterPath(String value)
	{
		cPosterPath = value;
	}
	
	
	private String sPosterPath;
	/** 
	 * 获取游戏类别图片
	 * @return 游戏类别图片
	 */
	public String getSPosterPath()
	{
		return sPosterPath;
	}
	
	/**
	 * 设置游戏类别图片
	 * @param 游戏类别图片
	 */
	public void setSPosterPath(String value)
	{
		sPosterPath = value;
	}
	
	
	private String lPosterPath;
	/** 
	 * 获取服务器列表图片
	 * @return 服务器列表图片
	 */
	public String getLPosterPath()
	{
		return lPosterPath;
	}
	
	/**
	 * 设置服务器列表图片
	 * @param 服务器列表图片
	 */
	public void setLPosterPath(String value)
	{
		lPosterPath = value;
	}
	
}	


