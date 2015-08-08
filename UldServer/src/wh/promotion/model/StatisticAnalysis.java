package wh.promotion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class StatisticAnalysis implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public StatisticAnalysis(){
		;
	}
		
	private int statisticAnalysisId;
	/** 
	 * 获取统计分析编号
	 * @return 统计分析编号
	 */
	public int getStatisticAnalysisId()
	{
		return statisticAnalysisId;
	}
	
	/**
	 * 设置统计分析编号
	 * @param 统计分析编号
	 */
	public void setStatisticAnalysisId(int value)
	{
		statisticAnalysisId = value;
	}
	

	private wh.promotion.model.ChannelSub channelSub;
	/**
	 * 获取子渠道
	 * @return 子渠道
	 */
	public wh.promotion.model.ChannelSub getChannelSub(){
		return this.channelSub;
	}
	
	/**
	 * 设置子渠道
	 * @param channelSub 子渠道
	 */
	public void setChannelSub(wh.promotion.model.ChannelSub channelSub){
		this.channelSub = channelSub;
	}
	
	private int channelSubId;
	/**
	 * 获取子渠道编号
	 * @return 子渠道编号
	 */
	public int getChannelSubId(){
		if(channelSub != null){
			return channelSub.getChannelSubId();
		}else{
			return channelSubId;
		}
	}
	
	/**
	 * 设置子渠道编号
	 * @param 子渠道编号
	 */
	public void setChannelSubId(int value){
		if(channelSub != null){
			channelSub.setChannelSubId(value);
		}else{
			channelSubId = value;
		}
	}
	/**
	 * 获取子渠道名称
	 * @return 子渠道名称
	 */
	public String getChannelSubName(){
		if(channelSub != null){
			return channelSub.getChannelSubName();
		}else{
			return "";
		}
	}	

	private wh.promotion.model.Channel channel;
	/**
	 * 获取渠道
	 * @return 渠道
	 */
	public wh.promotion.model.Channel getChannel(){
		return this.channel;
	}
	
	/**
	 * 设置渠道
	 * @param channel 渠道
	 */
	public void setChannel(wh.promotion.model.Channel channel){
		this.channel = channel;
	}
	
	private int channelId;
	/**
	 * 获取渠道编号
	 * @return 渠道编号
	 */
	public int getChannelId(){
		if(channel != null){
			return channel.getChannelId();
		}else{
			return channelId;
		}
	}
	
	/**
	 * 设置渠道编号
	 * @param 渠道编号
	 */
	public void setChannelId(int value){
		if(channel != null){
			channel.setChannelId(value);
		}else{
			channelId = value;
		}
	}
	/**
	 * 获取渠道名称
	 * @return 渠道名称
	 */
	public String getChannelName(){
		if(channel != null){
			return channel.getChannelName();
		}else{
			return "";
		}
	}	

	private byte channelType;
	/**
	 * 获取渠道类型：0、渠道类型；1、自然渠道；2、推广渠道；
	 * @return 渠道类型：0、渠道类型；1、自然渠道；2、推广渠道；
	 */
	public byte getChannelType(){
		return channelType;
	}
	
	/**
	 * 设置渠道类型：0、渠道类型；1、自然渠道；2、推广渠道；
	 * @param 渠道类型：0、渠道类型；1、自然渠道；2、推广渠道；
	 */
	public void setChannelType(byte value){
		channelType = value;
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
	

	private wh.promotion.model.MobileDevice mobileDevice;
	/**
	 * 获取移动设备
	 * @return 移动设备
	 */
	public wh.promotion.model.MobileDevice getMobileDevice(){
		return this.mobileDevice;
	}
	
	/**
	 * 设置移动设备
	 * @param mobileDevice 移动设备
	 */
	public void setMobileDevice(wh.promotion.model.MobileDevice mobileDevice){
		this.mobileDevice = mobileDevice;
	}
	
	private int mobileDeviceId;
	/**
	 * 获取移动设备编号
	 * @return 移动设备编号
	 */
	public int getMobileDeviceId(){
		if(mobileDevice != null){
			return mobileDevice.getMobileDeviceId();
		}else{
			return mobileDeviceId;
		}
	}
	
	/**
	 * 设置移动设备编号
	 * @param 移动设备编号
	 */
	public void setMobileDeviceId(int value){
		if(mobileDevice != null){
			mobileDevice.setMobileDeviceId(value);
		}else{
			mobileDeviceId = value;
		}
	}
	/**
	 * 获取移动设备名称
	 * @return 移动设备名称
	 */
	public String getMobileDeviceName(){
		if(mobileDevice != null){
			return mobileDevice.getMobileDeviceName();
		}else{
			return "";
		}
	}	

	private wh.game.model.Game game;
	/**
	 * 获取游戏
	 * @return 游戏
	 */
	public wh.game.model.Game getGame(){
		return this.game;
	}
	
	/**
	 * 设置游戏
	 * @param game 游戏
	 */
	public void setGame(wh.game.model.Game game){
		this.game = game;
	}
	
	private int gameId;
	/**
	 * 获取游戏编号
	 * @return 游戏编号
	 */
	public int getGameId(){
		if(game != null){
			return game.getGameId();
		}else{
			return gameId;
		}
	}
	
	/**
	 * 设置游戏编号
	 * @param 游戏编号
	 */
	public void setGameId(int value){
		if(game != null){
			game.setGameId(value);
		}else{
			gameId = value;
		}
	}
	/**
	 * 获取游戏名称
	 * @return 游戏名称
	 */
	public String getGameName(){
		if(game != null){
			return game.getGameName();
		}else{
			return "";
		}
	}	
}	


