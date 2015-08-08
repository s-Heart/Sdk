package wh.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class ServerAccount implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public ServerAccount(){
		 this.status = (byte)0;
	}
		
	private int serverAccountId;
	/** 
	 * 获取服务器帐户编号
	 * @return 服务器帐户编号
	 */
	public int getServerAccountId()
	{
		return serverAccountId;
	}
	
	/**
	 * 设置服务器帐户编号
	 * @param 服务器帐户编号
	 */
	public void setServerAccountId(int value)
	{
		serverAccountId = value;
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

	private wh.server.model.SACategory sACategory;
	/**
	 * 获取服务器帐户分类
	 * @return 服务器帐户分类
	 */
	public wh.server.model.SACategory getSACategory(){
		return this.sACategory;
	}
	
	/**
	 * 设置服务器帐户分类
	 * @param sACategory 服务器帐户分类
	 */
	public void setSACategory(wh.server.model.SACategory sACategory){
		this.sACategory = sACategory;
	}
	
	private int sACategoryId;
	/**
	 * 获取服务器帐户分类编号
	 * @return 服务器帐户分类编号
	 */
	public int getSACategoryId(){
		if(sACategory != null){
			return sACategory.getSACategoryId();
		}else{
			return sACategoryId;
		}
	}
	
	/**
	 * 设置服务器帐户分类编号
	 * @param 服务器帐户分类编号
	 */
	public void setSACategoryId(int value){
		if(sACategory != null){
			sACategory.setSACategoryId(value);
		}else{
			sACategoryId = value;
		}
	}
	/**
	 * 获取服务器帐户分类名称
	 * @return 服务器帐户分类名称
	 */
	public String getSACategoryName(){
		if(sACategory != null){
			return sACategory.getSACategoryName();
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
	
	private BigDecimal accountValue;
	/** 
	 * 获取帐户值
	 * @return 帐户值
	 */
	public BigDecimal getAccountValue()
	{
		return accountValue;
	}
	
	/**
	 * 设置帐户值
	 * @param 帐户值
	 */
	public void setAccountValue(BigDecimal value)
	{
		accountValue = value;
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
}	


