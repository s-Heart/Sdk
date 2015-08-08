package wh.game.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Server implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Server(){
		 this.status = (byte)0;
	}
		
	private int serverId;
	/** 
	 * 获取服务编号
	 * @return 服务编号
	 */
	public int getServerId()
	{
		return serverId;
	}
	
	/**
	 * 设置服务编号
	 * @param 服务编号
	 */
	public void setServerId(int value)
	{
		serverId = value;
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
	
	private String serverName;
	/** 
	 * 获取服务器名称
	 * @return 服务器名称
	 */
	public String getServerName()
	{
		return serverName;
	}
	
	/**
	 * 设置服务器名称
	 * @param 服务器名称
	 */
	public void setServerName(String value)
	{
		serverName = value;
	}
	
	
	private String serverCode;
	/** 
	 * 获取服务器编码
	 * @return 服务器编码
	 */
	public String getServerCode()
	{
		return serverCode;
	}
	
	/**
	 * 设置服务器编码
	 * @param 服务器编码
	 */
	public void setServerCode(String value)
	{
		serverCode = value;
	}
	
	
	private int sequenceNumber;
	/** 
	 * 获取自增序号，十位补全
	 * @return 自增序号，十位补全
	 */
	public int getSequenceNumber()
	{
		return sequenceNumber;
	}
	
	/**
	 * 设置自增序号，十位补全
	 * @param 自增序号，十位补全
	 */
	public void setSequenceNumber(int value)
	{
		sequenceNumber = value;
	}
	
	
	private String serverIP;
	/** 
	 * 获取服务器IP
	 * @return 服务器IP
	 */
	public String getServerIP()
	{
		return serverIP;
	}
	
	/**
	 * 设置服务器IP
	 * @param 服务器IP
	 */
	public void setServerIP(String value)
	{
		serverIP = value;
	}
	
	
	private String serverDomain;
	/** 
	 * 获取服务器域名
	 * @return 服务器域名
	 */
	public String getServerDomain()
	{
		return serverDomain;
	}
	
	/**
	 * 设置服务器域名
	 * @param 服务器域名
	 */
	public void setServerDomain(String value)
	{
		serverDomain = value;
	}
	
	
	private String serverPort;
	/** 
	 * 获取服务器端口
	 * @return 服务器端口
	 */
	public String getServerPort()
	{
		return serverPort;
	}
	
	/**
	 * 设置服务器端口
	 * @param 服务器端口
	 */
	public void setServerPort(String value)
	{
		serverPort = value;
	}
	
	
	private String serverUrl;
	/** 
	 * 获取服务器Url
	 * @return 服务器Url
	 */
	public String getServerUrl()
	{
		return serverUrl;
	}
	
	/**
	 * 设置服务器Url
	 * @param 服务器Url
	 */
	public void setServerUrl(String value)
	{
		serverUrl = value;
	}
	

	private Date openDate;
	/**
	 * 获取开服日期
	 * @return 开服日期
	 */
	public Date getOpenDate()
	{   
		if(openDate == null){
			return Utility.getDbMinDate();
		}
		return openDate;
	}
	
	/**
	 * 设置开服日期
	 * @param 开服日期
	 */
	public void setOpenDate(Date value)
	{   
		openDate = value;
	}
	
	private Date openDateBegin;
	/**
	 * 获取开服日期
	 * @return 开服日期
	 */
	public Date getOpenDateBegin()
	{   
		if(openDateBegin == null){
			return Utility.getDbMinDate();
		}
		return openDateBegin;
	}
	
	/**
	 * 设置开服日期
	 * @param 开服日期
	 */
	public void setOpenDateBegin(Date value)
	{   
		openDateBegin = value;
	}
	
	private Date openDateEnd;
	/**
	 * 获取开服日期
	 * @return 开服日期
	 */
	public Date getOpenDateEnd()
	{   
		if(openDateEnd == null){
			return Utility.getDbMinDate();
		}
		return openDateEnd;
	}
	
	/**
	 * 设置开服日期
	 * @param 开服日期
	 */
	public void setOpenDateEnd(Date value)
	{   
		openDateEnd = value;
	}
	
	
	
	private int openDateYear;
	/** 
	 * 获取开服日期年
	 * @return 开服日期年
	 */
	public int getOpenDateYear()
	{
		return openDateYear;
	}
	
	/**
	 * 设置开服日期年
	 * @param 开服日期年
	 */
	public void setOpenDateYear(int value)
	{
		openDateYear = value;
	}
	
	
	private int openDateMonth;
	/** 
	 * 获取开服日期月
	 * @return 开服日期月
	 */
	public int getOpenDateMonth()
	{
		return openDateMonth;
	}
	
	/**
	 * 设置开服日期月
	 * @param 开服日期月
	 */
	public void setOpenDateMonth(int value)
	{
		openDateMonth = value;
	}
	
	
	private int openDateDay;
	/** 
	 * 获取开服日期日
	 * @return 开服日期日
	 */
	public int getOpenDateDay()
	{
		return openDateDay;
	}
	
	/**
	 * 设置开服日期日
	 * @param 开服日期日
	 */
	public void setOpenDateDay(int value)
	{
		openDateDay = value;
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
	

	private byte lineType;
	/**
	 * 获取线路类型：0、线路类型；1、电信；2、网通；4、双线；
	 * @return 线路类型：0、线路类型；1、电信；2、网通；4、双线；
	 */
	public byte getLineType(){
		return lineType;
	}
	
	/**
	 * 设置线路类型：0、线路类型；1、电信；2、网通；4、双线；
	 * @param 线路类型：0、线路类型；1、电信；2、网通；4、双线；
	 */
	public void setLineType(byte value){
		lineType = value;
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

	private byte rechargeType;
	/**
	 * 获取充值类型：0、充值类型；1、允许充值；2、禁用充值；
	 * @return 充值类型：0、充值类型；1、允许充值；2、禁用充值；
	 */
	public byte getRechargeType(){
		return rechargeType;
	}
	
	/**
	 * 设置充值类型：0、充值类型；1、允许充值；2、禁用充值；
	 * @param 充值类型：0、充值类型；1、允许充值；2、禁用充值；
	 */
	public void setRechargeType(byte value){
		rechargeType = value;
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

	private byte enableMessageType;
	/**
	 * 获取启用消息类型：0、启用消息类型；1、启用；2、不启用；
	 * @return 启用消息类型：0、启用消息类型；1、启用；2、不启用；
	 */
	public byte getEnableMessageType(){
		return enableMessageType;
	}
	
	/**
	 * 设置启用消息类型：0、启用消息类型；1、启用；2、不启用；
	 * @param 启用消息类型：0、启用消息类型；1、启用；2、不启用；
	 */
	public void setEnableMessageType(byte value){
		enableMessageType = value;
	}	

	private byte serverType;
	/**
	 * 获取服务器类型：0、服务器类型；1、普通；2、新服；4、维护；8、火爆；
	 * @return 服务器类型：0、服务器类型；1、普通；2、新服；4、维护；8、火爆；
	 */
	public byte getServerType(){
		return serverType;
	}
	
	/**
	 * 设置服务器类型：0、服务器类型；1、普通；2、新服；4、维护；8、火爆；
	 * @param 服务器类型：0、服务器类型；1、普通；2、新服；4、维护；8、火爆；
	 */
	public void setServerType(byte value){
		serverType = value;
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
}	


