package wh.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class AccessedGame implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public AccessedGame(){
		 this.status = (byte)0;
	}
		
	private int accessedGameId;
	/** 
	 * 获取登录过的游戏
	 * @return 登录过的游戏
	 */
	public int getAccessedGameId()
	{
		return accessedGameId;
	}
	
	/**
	 * 设置登录过的游戏
	 * @param 登录过的游戏
	 */
	public void setAccessedGameId(int value)
	{
		accessedGameId = value;
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

	private Date latestAccessedDate;
	/**
	 * 获取最后一次登录时间
	 * @return 最后一次登录时间
	 */
	public Date getLatestAccessedDate()
	{   
		if(latestAccessedDate == null){
			return Utility.getDbMinDate();
		}
		return latestAccessedDate;
	}
	
	/**
	 * 设置最后一次登录时间
	 * @param 最后一次登录时间
	 */
	public void setLatestAccessedDate(Date value)
	{   
		latestAccessedDate = value;
	}
	
	private Date latestAccessedDateBegin;
	/**
	 * 获取最后一次登录时间
	 * @return 最后一次登录时间
	 */
	public Date getLatestAccessedDateBegin()
	{   
		if(latestAccessedDateBegin == null){
			return Utility.getDbMinDate();
		}
		return latestAccessedDateBegin;
	}
	
	/**
	 * 设置最后一次登录时间
	 * @param 最后一次登录时间
	 */
	public void setLatestAccessedDateBegin(Date value)
	{   
		latestAccessedDateBegin = value;
	}
	
	private Date latestAccessedDateEnd;
	/**
	 * 获取最后一次登录时间
	 * @return 最后一次登录时间
	 */
	public Date getLatestAccessedDateEnd()
	{   
		if(latestAccessedDateEnd == null){
			return Utility.getDbMinDate();
		}
		return latestAccessedDateEnd;
	}
	
	/**
	 * 设置最后一次登录时间
	 * @param 最后一次登录时间
	 */
	public void setLatestAccessedDateEnd(Date value)
	{   
		latestAccessedDateEnd = value;
	}
	
	
	
	private int latestAccessedDateYear;
	/** 
	 * 获取最后一次登录时间年
	 * @return 最后一次登录时间年
	 */
	public int getLatestAccessedDateYear()
	{
		return latestAccessedDateYear;
	}
	
	/**
	 * 设置最后一次登录时间年
	 * @param 最后一次登录时间年
	 */
	public void setLatestAccessedDateYear(int value)
	{
		latestAccessedDateYear = value;
	}
	
	
	private int latestAccessedDateMonth;
	/** 
	 * 获取最后一次登录时间月
	 * @return 最后一次登录时间月
	 */
	public int getLatestAccessedDateMonth()
	{
		return latestAccessedDateMonth;
	}
	
	/**
	 * 设置最后一次登录时间月
	 * @param 最后一次登录时间月
	 */
	public void setLatestAccessedDateMonth(int value)
	{
		latestAccessedDateMonth = value;
	}
	
	
	private int latestAccessedDateDay;
	/** 
	 * 获取最后一次登录时间天
	 * @return 最后一次登录时间天
	 */
	public int getLatestAccessedDateDay()
	{
		return latestAccessedDateDay;
	}
	
	/**
	 * 设置最后一次登录时间天
	 * @param 最后一次登录时间天
	 */
	public void setLatestAccessedDateDay(int value)
	{
		latestAccessedDateDay = value;
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


