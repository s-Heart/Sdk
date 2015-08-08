package wh.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class AccessedGameDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public AccessedGameDetail(){
		 this.status = (byte)0;
	}
		
	private int accessedGameDetailId;
	/** 
	 * 获取登录过的游戏详情编号
	 * @return 登录过的游戏详情编号
	 */
	public int getAccessedGameDetailId()
	{
		return accessedGameDetailId;
	}
	
	/**
	 * 设置登录过的游戏详情编号
	 * @param 登录过的游戏详情编号
	 */
	public void setAccessedGameDetailId(int value)
	{
		accessedGameDetailId = value;
	}
	

	private wh.game.model.Server server;
	/**
	 * 获取服务器
	 * @return 服务器
	 */
	public wh.game.model.Server getServer(){
		return this.server;
	}
	
	/**
	 * 设置服务器
	 * @param server 服务器
	 */
	public void setServer(wh.game.model.Server server){
		this.server = server;
	}
	
	private int serverId;
	/**
	 * 获取服务编号
	 * @return 服务编号
	 */
	public int getServerId(){
		if(server != null){
			return server.getServerId();
		}else{
			return serverId;
		}
	}
	
	/**
	 * 设置服务编号
	 * @param 服务编号
	 */
	public void setServerId(int value){
		if(server != null){
			server.setServerId(value);
		}else{
			serverId = value;
		}
	}
	/**
	 * 获取服务名称
	 * @return 服务名称
	 */
	public String getServerName(){
		if(server != null){
			return server.getServerName();
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

	private wh.server.model.AccessedGame accessedGame;
	/**
	 * 获取登录过的游戏
	 * @return 登录过的游戏
	 */
	public wh.server.model.AccessedGame getAccessedGame(){
		return this.accessedGame;
	}
	
	/**
	 * 设置登录过的游戏
	 * @param accessedGame 登录过的游戏
	 */
	public void setAccessedGame(wh.server.model.AccessedGame accessedGame){
		this.accessedGame = accessedGame;
	}
	
	private int accessedGameId;
	/**
	 * 获取登录过的游戏
	 * @return 登录过的游戏
	 */
	public int getAccessedGameId(){
		if(accessedGame != null){
			return accessedGame.getAccessedGameId();
		}else{
			return accessedGameId;
		}
	}
	
	/**
	 * 设置登录过的游戏
	 * @param 登录过的游戏
	 */
	public void setAccessedGameId(int value){
		if(accessedGame != null){
			accessedGame.setAccessedGameId(value);
		}else{
			accessedGameId = value;
		}
	}
	

	private wh.member.model.User user;
	/**
	 * 获取用户
	 * @return 用户
	 */
	public wh.member.model.User getUser(){
		return this.user;
	}
	
	/**
	 * 设置用户
	 * @param user 用户
	 */
	public void setUser(wh.member.model.User user){
		this.user = user;
	}
	
	private int userId;
	/**
	 * 获取用户编号
	 * @return 用户编号
	 */
	public int getUserId(){
		if(user != null){
			return user.getUserId();
		}else{
			return userId;
		}
	}
	
	/**
	 * 设置用户编号
	 * @param 用户编号
	 */
	public void setUserId(int value){
		if(user != null){
			user.setUserId(value);
		}else{
			userId = value;
		}
	}
	/**
	 * 获取用户名称
	 * @return 用户名称
	 */
	public String getUserName(){
		if(user != null){
			return user.getUserName();
		}else{
			return "";
		}
	}	

	private Date accessedDate;
	/**
	 * 获取登录时间
	 * @return 登录时间
	 */
	public Date getAccessedDate()
	{   
		if(accessedDate == null){
			return Utility.getDbMinDate();
		}
		return accessedDate;
	}
	
	/**
	 * 设置登录时间
	 * @param 登录时间
	 */
	public void setAccessedDate(Date value)
	{   
		accessedDate = value;
	}
	
	private Date accessedDateBegin;
	/**
	 * 获取登录时间
	 * @return 登录时间
	 */
	public Date getAccessedDateBegin()
	{   
		if(accessedDateBegin == null){
			return Utility.getDbMinDate();
		}
		return accessedDateBegin;
	}
	
	/**
	 * 设置登录时间
	 * @param 登录时间
	 */
	public void setAccessedDateBegin(Date value)
	{   
		accessedDateBegin = value;
	}
	
	private Date accessedDateEnd;
	/**
	 * 获取登录时间
	 * @return 登录时间
	 */
	public Date getAccessedDateEnd()
	{   
		if(accessedDateEnd == null){
			return Utility.getDbMinDate();
		}
		return accessedDateEnd;
	}
	
	/**
	 * 设置登录时间
	 * @param 登录时间
	 */
	public void setAccessedDateEnd(Date value)
	{   
		accessedDateEnd = value;
	}
	
	
	
	private int accessedDateYear;
	/** 
	 * 获取登录时间年
	 * @return 登录时间年
	 */
	public int getAccessedDateYear()
	{
		return accessedDateYear;
	}
	
	/**
	 * 设置登录时间年
	 * @param 登录时间年
	 */
	public void setAccessedDateYear(int value)
	{
		accessedDateYear = value;
	}
	
	
	private int accessedDateMonth;
	/** 
	 * 获取登录时间月
	 * @return 登录时间月
	 */
	public int getAccessedDateMonth()
	{
		return accessedDateMonth;
	}
	
	/**
	 * 设置登录时间月
	 * @param 登录时间月
	 */
	public void setAccessedDateMonth(int value)
	{
		accessedDateMonth = value;
	}
	
	
	private int accessedDateDay;
	/** 
	 * 获取登录时间天
	 * @return 登录时间天
	 */
	public int getAccessedDateDay()
	{
		return accessedDateDay;
	}
	
	/**
	 * 设置登录时间天
	 * @param 登录时间天
	 */
	public void setAccessedDateDay(int value)
	{
		accessedDateDay = value;
	}
	

	private Date leaveDate;
	/**
	 * 获取离开时间
	 * @return 离开时间
	 */
	public Date getLeaveDate()
	{   
		if(leaveDate == null){
			return Utility.getDbMinDate();
		}
		return leaveDate;
	}
	
	/**
	 * 设置离开时间
	 * @param 离开时间
	 */
	public void setLeaveDate(Date value)
	{   
		leaveDate = value;
	}
	
	private Date leaveDateBegin;
	/**
	 * 获取离开时间
	 * @return 离开时间
	 */
	public Date getLeaveDateBegin()
	{   
		if(leaveDateBegin == null){
			return Utility.getDbMinDate();
		}
		return leaveDateBegin;
	}
	
	/**
	 * 设置离开时间
	 * @param 离开时间
	 */
	public void setLeaveDateBegin(Date value)
	{   
		leaveDateBegin = value;
	}
	
	private Date leaveDateEnd;
	/**
	 * 获取离开时间
	 * @return 离开时间
	 */
	public Date getLeaveDateEnd()
	{   
		if(leaveDateEnd == null){
			return Utility.getDbMinDate();
		}
		return leaveDateEnd;
	}
	
	/**
	 * 设置离开时间
	 * @param 离开时间
	 */
	public void setLeaveDateEnd(Date value)
	{   
		leaveDateEnd = value;
	}
	
	
	
	private int onLineDuration;
	/** 
	 * 获取在线时长，单位(秒)
	 * @return 在线时长，单位(秒)
	 */
	public int getOnLineDuration()
	{
		return onLineDuration;
	}
	
	/**
	 * 设置在线时长，单位(秒)
	 * @param 在线时长，单位(秒)
	 */
	public void setOnLineDuration(int value)
	{
		onLineDuration = value;
	}
	

	private byte onLineType;
	/**
	 * 获取在线类型：0、在线类型；1、在线；2、下线；
	 * @return 在线类型：0、在线类型；1、在线；2、下线；
	 */
	public byte getOnLineType(){
		return onLineType;
	}
	
	/**
	 * 设置在线类型：0、在线类型；1、在线；2、下线；
	 * @param 在线类型：0、在线类型；1、在线；2、下线；
	 */
	public void setOnLineType(byte value){
		onLineType = value;
	}	
	
	private int currentContinuousDays;
	/** 
	 * 获取当前连续天数，必须是连续的，如果有隔天则重新从1开始，只针对同一用户
	 * @return 当前连续天数，必须是连续的，如果有隔天则重新从1开始，只针对同一用户
	 */
	public int getCurrentContinuousDays()
	{
		return currentContinuousDays;
	}
	
	/**
	 * 设置当前连续天数，必须是连续的，如果有隔天则重新从1开始，只针对同一用户
	 * @param 当前连续天数，必须是连续的，如果有隔天则重新从1开始，只针对同一用户
	 */
	public void setCurrentContinuousDays(int value)
	{
		currentContinuousDays = value;
	}
	
	
	private int currentLoginTimes;
	/** 
	 * 获取当前登录次数，每登录一次加1，只针对同一用户
	 * @return 当前登录次数，每登录一次加1，只针对同一用户
	 */
	public int getCurrentLoginTimes()
	{
		return currentLoginTimes;
	}
	
	/**
	 * 设置当前登录次数，每登录一次加1，只针对同一用户
	 * @param 当前登录次数，每登录一次加1，只针对同一用户
	 */
	public void setCurrentLoginTimes(int value)
	{
		currentLoginTimes = value;
	}
	
	
	private int currentLoginDays;
	/** 
	 * 获取当前登录天数，每次登录如果日期天和上次登录日期天不一样，则加1，只针对同一用户
	 * @return 当前登录天数，每次登录如果日期天和上次登录日期天不一样，则加1，只针对同一用户
	 */
	public int getCurrentLoginDays()
	{
		return currentLoginDays;
	}
	
	/**
	 * 设置当前登录天数，每次登录如果日期天和上次登录日期天不一样，则加1，只针对同一用户
	 * @param 当前登录天数，每次登录如果日期天和上次登录日期天不一样，则加1，只针对同一用户
	 */
	public void setCurrentLoginDays(int value)
	{
		currentLoginDays = value;
	}
	
	
	private int continuousServerDays;
	/** 
	 * 获取当前连接登录同一用户，同一游戏，同一服务器天数
	 * @return 当前连接登录同一用户，同一游戏，同一服务器天数
	 */
	public int getContinuousServerDays()
	{
		return continuousServerDays;
	}
	
	/**
	 * 设置当前连接登录同一用户，同一游戏，同一服务器天数
	 * @param 当前连接登录同一用户，同一游戏，同一服务器天数
	 */
	public void setContinuousServerDays(int value)
	{
		continuousServerDays = value;
	}
	
	
	private int loginServerTimes;
	/** 
	 * 获取当前登录同一用户，同一游戏，同一服务器次数
	 * @return 当前登录同一用户，同一游戏，同一服务器次数
	 */
	public int getLoginServerTimes()
	{
		return loginServerTimes;
	}
	
	/**
	 * 设置当前登录同一用户，同一游戏，同一服务器次数
	 * @param 当前登录同一用户，同一游戏，同一服务器次数
	 */
	public void setLoginServerTimes(int value)
	{
		loginServerTimes = value;
	}
	
	
	private int continuousGameDays;
	/** 
	 * 获取当前登录同一游戏的天数，针对同一用户，同一游戏
	 * @return 当前登录同一游戏的天数，针对同一用户，同一游戏
	 */
	public int getContinuousGameDays()
	{
		return continuousGameDays;
	}
	
	/**
	 * 设置当前登录同一游戏的天数，针对同一用户，同一游戏
	 * @param 当前登录同一游戏的天数，针对同一用户，同一游戏
	 */
	public void setContinuousGameDays(int value)
	{
		continuousGameDays = value;
	}
	
	
	private int loginGameTimes;
	/** 
	 * 获取当前登录同一游戏的次数，针对同一用户，同一游戏
	 * @return 当前登录同一游戏的次数，针对同一用户，同一游戏
	 */
	public int getLoginGameTimes()
	{
		return loginGameTimes;
	}
	
	/**
	 * 设置当前登录同一游戏的次数，针对同一用户，同一游戏
	 * @param 当前登录同一游戏的次数，针对同一用户，同一游戏
	 */
	public void setLoginGameTimes(int value)
	{
		loginGameTimes = value;
	}
	
	
	private String sourceIP;
	/** 
	 * 获取来源IP
	 * @return 来源IP
	 */
	public String getSourceIP()
	{
		return sourceIP;
	}
	
	/**
	 * 设置来源IP
	 * @param 来源IP
	 */
	public void setSourceIP(String value)
	{
		sourceIP = value;
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


