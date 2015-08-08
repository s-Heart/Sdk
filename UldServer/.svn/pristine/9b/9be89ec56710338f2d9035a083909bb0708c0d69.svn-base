package wh.game.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class GameInterface implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public GameInterface(){
		 this.status = (byte)0;
	}
		
	private int gameInterfaceId;
	/** 
	 * 获取游戏接口编号
	 * @return 游戏接口编号
	 */
	public int getGameInterfaceId()
	{
		return gameInterfaceId;
	}
	
	/**
	 * 设置游戏接口编号
	 * @param 游戏接口编号
	 */
	public void setGameInterfaceId(int value)
	{
		gameInterfaceId = value;
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
	
	
	private String url;
	/** 
	 * 获取接口Url
	 * @return 接口Url
	 */
	public String getUrl()
	{
		return url;
	}
	
	/**
	 * 设置接口Url
	 * @param 接口Url
	 */
	public void setUrl(String value)
	{
		url = value;
	}
	

	private byte interfaceType;
	/**
	 * 获取接口类型：0、接口类型；1、登录；2、充值；4、查询帐号是否存在；8、获取游角色与等级；
	 * @return 接口类型：0、接口类型；1、登录；2、充值；4、查询帐号是否存在；8、获取游角色与等级；
	 */
	public byte getInterfaceType(){
		return interfaceType;
	}
	
	/**
	 * 设置接口类型：0、接口类型；1、登录；2、充值；4、查询帐号是否存在；8、获取游角色与等级；
	 * @param 接口类型：0、接口类型；1、登录；2、充值；4、查询帐号是否存在；8、获取游角色与等级；
	 */
	public void setInterfaceType(byte value){
		interfaceType = value;
	}	

	private byte payUnitType;
	/**
	 * 获取充值单位：0、充值单位；1、分；2、元；
	 * @return 充值单位：0、充值单位；1、分；2、元；
	 */
	public byte getPayUnitType(){
		return payUnitType;
	}
	
	/**
	 * 设置充值单位：0、充值单位；1、分；2、元；
	 * @param 充值单位：0、充值单位；1、分；2、元；
	 */
	public void setPayUnitType(byte value){
		payUnitType = value;
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
	
	private String signJoinSymbol;
	/** 
	 * 获取签名连接符
	 * @return 签名连接符
	 */
	public String getSignJoinSymbol()
	{
		return signJoinSymbol;
	}
	
	/**
	 * 设置签名连接符
	 * @param 签名连接符
	 */
	public void setSignJoinSymbol(String value)
	{
		signJoinSymbol = value;
	}
	
}	


