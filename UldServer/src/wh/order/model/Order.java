package wh.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Order implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Order(){
		 this.status = (byte)0;
	}
		
	private int orderId;
	/** 
	 * 获取订单编号
	 * @return 订单编号
	 */
	public int getOrderId()
	{
		return orderId;
	}
	
	/**
	 * 设置订单编号
	 * @param 订单编号
	 */
	public void setOrderId(int value)
	{
		orderId = value;
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
	
	private int chargedUserId;
	/** 
	 * 获取被充值用户编号
	 * @return 被充值用户编号
	 */
	public int getChargedUserId()
	{
		return chargedUserId;
	}
	
	/**
	 * 设置被充值用户编号
	 * @param 被充值用户编号
	 */
	public void setChargedUserId(int value)
	{
		chargedUserId = value;
	}
	
	
	private String chargedUserName;
	/** 
	 * 获取被充值用户名称
	 * @return 被充值用户名称
	 */
	public String getChargedUserName()
	{
		return chargedUserName;
	}
	
	/**
	 * 设置被充值用户名称
	 * @param 被充值用户名称
	 */
	public void setChargedUserName(String value)
	{
		chargedUserName = value;
	}
	

	private byte payType;
	/**
	 * 获取支付类型：0、支付类型；1、网上银行；2、支付宝；3、手机卡；4、声讯；5、点卡；6、PayPal；7、神州行；8、联通；9、电信；10、钱包；65、骏网一卡通支付；66、盛大卡；67、网易一卡通；68、完美一卡通支付；69、征途卡支付；70、久游一卡通支付；71、易宝E卡通支付；72、纵游一卡通；73、天下一卡通；74、G币返还；
	 * @return 支付类型：0、支付类型；1、网上银行；2、支付宝；3、手机卡；4、声讯；5、点卡；6、PayPal；7、神州行；8、联通；9、电信；10、钱包；65、骏网一卡通支付；66、盛大卡；67、网易一卡通；68、完美一卡通支付；69、征途卡支付；70、久游一卡通支付；71、易宝E卡通支付；72、纵游一卡通；73、天下一卡通；74、G币返还；
	 */
	public byte getPayType(){
		return payType;
	}
	
	/**
	 * 设置支付类型：0、支付类型；1、网上银行；2、支付宝；3、手机卡；4、声讯；5、点卡；6、PayPal；7、神州行；8、联通；9、电信；10、钱包；65、骏网一卡通支付；66、盛大卡；67、网易一卡通；68、完美一卡通支付；69、征途卡支付；70、久游一卡通支付；71、易宝E卡通支付；72、纵游一卡通；73、天下一卡通；74、G币返还；
	 * @param 支付类型：0、支付类型；1、网上银行；2、支付宝；3、手机卡；4、声讯；5、点卡；6、PayPal；7、神州行；8、联通；9、电信；10、钱包；65、骏网一卡通支付；66、盛大卡；67、网易一卡通；68、完美一卡通支付；69、征途卡支付；70、久游一卡通支付；71、易宝E卡通支付；72、纵游一卡通；73、天下一卡通；74、G币返还；
	 */
	public void setPayType(byte value){
		payType = value;
	}	
	
	private BigDecimal payAccount;
	/** 
	 * 获取充值金额，扣除手续费后的金额
	 * @return 充值金额，扣除手续费后的金额
	 */
	public BigDecimal getPayAccount()
	{
		return payAccount;
	}
	
	/**
	 * 设置充值金额，扣除手续费后的金额
	 * @param 充值金额，扣除手续费后的金额
	 */
	public void setPayAccount(BigDecimal value)
	{
		payAccount = value;
	}
	

	private byte accountType;
	/**
	 * 获取帐户类型：0、全部；1、D币；2、幸运点；
	 * @return 帐户类型：0、全部；1、D币；2、幸运点；
	 */
	public byte getAccountType(){
		return accountType;
	}
	
	/**
	 * 设置帐户类型：0、全部；1、D币；2、幸运点；
	 * @param 帐户类型：0、全部；1、D币；2、幸运点；
	 */
	public void setAccountType(byte value){
		accountType = value;
	}	
	
	private String realName;
	/** 
	 * 获取真实姓名
	 * @return 真实姓名
	 */
	public String getRealName()
	{
		return realName;
	}
	
	/**
	 * 设置真实姓名
	 * @param 真实姓名
	 */
	public void setRealName(String value)
	{
		realName = value;
	}
	
	
	private String iDCard;
	/** 
	 * 获取身份证
	 * @return 身份证
	 */
	public String getIDCard()
	{
		return iDCard;
	}
	
	/**
	 * 设置身份证
	 * @param 身份证
	 */
	public void setIDCard(String value)
	{
		iDCard = value;
	}
	
	
	private String mobilePhone;
	/** 
	 * 获取手机
	 * @return 手机
	 */
	public String getMobilePhone()
	{
		return mobilePhone;
	}
	
	/**
	 * 设置手机
	 * @param 手机
	 */
	public void setMobilePhone(String value)
	{
		mobilePhone = value;
	}
	
	
	private String tel;
	/** 
	 * 获取多个Tel，以,号分隔
	 * @return 多个Tel，以,号分隔
	 */
	public String getTel()
	{
		return tel;
	}
	
	/**
	 * 设置多个Tel，以,号分隔
	 * @param 多个Tel，以,号分隔
	 */
	public void setTel(String value)
	{
		tel = value;
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
	
	
	
	private int modifyDateYear;
	/** 
	 * 获取修改日期年
	 * @return 修改日期年
	 */
	public int getModifyDateYear()
	{
		return modifyDateYear;
	}
	
	/**
	 * 设置修改日期年
	 * @param 修改日期年
	 */
	public void setModifyDateYear(int value)
	{
		modifyDateYear = value;
	}
	
	
	private int modifyDateMonth;
	/** 
	 * 获取修改日期月
	 * @return 修改日期月
	 */
	public int getModifyDateMonth()
	{
		return modifyDateMonth;
	}
	
	/**
	 * 设置修改日期月
	 * @param 修改日期月
	 */
	public void setModifyDateMonth(int value)
	{
		modifyDateMonth = value;
	}
	
	
	private int modifyDateDay;
	/** 
	 * 获取修改日期天
	 * @return 修改日期天
	 */
	public int getModifyDateDay()
	{
		return modifyDateDay;
	}
	
	/**
	 * 设置修改日期天
	 * @param 修改日期天
	 */
	public void setModifyDateDay(int value)
	{
		modifyDateDay = value;
	}
	

	private byte orderType;
	/**
	 * 获取订单类型：0、订单类型；1、已提交；2、处理成功；4、处理失败；8、已提交游戏方；16、游戏方处理成功；32、游戏方处理失败；64、掉单；
	 * @return 订单类型：0、订单类型；1、已提交；2、处理成功；4、处理失败；8、已提交游戏方；16、游戏方处理成功；32、游戏方处理失败；64、掉单；
	 */
	public byte getOrderType(){
		return orderType;
	}
	
	/**
	 * 设置订单类型：0、订单类型；1、已提交；2、处理成功；4、处理失败；8、已提交游戏方；16、游戏方处理成功；32、游戏方处理失败；64、掉单；
	 * @param 订单类型：0、订单类型；1、已提交；2、处理成功；4、处理失败；8、已提交游戏方；16、游戏方处理成功；32、游戏方处理失败；64、掉单；
	 */
	public void setOrderType(byte value){
		orderType = value;
	}	
	
	private int chargedServerDays;
	/** 
	 * 获取当前连接充值的服务器天数，针对同一用户，同一游戏，同一服务器，只有在充值成功的时候才修改此值
	 * @return 当前连接充值的服务器天数，针对同一用户，同一游戏，同一服务器，只有在充值成功的时候才修改此值
	 */
	public int getChargedServerDays()
	{
		return chargedServerDays;
	}
	
	/**
	 * 设置当前连接充值的服务器天数，针对同一用户，同一游戏，同一服务器，只有在充值成功的时候才修改此值
	 * @param 当前连接充值的服务器天数，针对同一用户，同一游戏，同一服务器，只有在充值成功的时候才修改此值
	 */
	public void setChargedServerDays(int value)
	{
		chargedServerDays = value;
	}
	
	
	private int chargedGameDays;
	/** 
	 * 获取当前连接充值的游戏天数，针对同一用户，同一游戏，只有在充值成功的时候才修改此值
	 * @return 当前连接充值的游戏天数，针对同一用户，同一游戏，只有在充值成功的时候才修改此值
	 */
	public int getChargedGameDays()
	{
		return chargedGameDays;
	}
	
	/**
	 * 设置当前连接充值的游戏天数，针对同一用户，同一游戏，只有在充值成功的时候才修改此值
	 * @param 当前连接充值的游戏天数，针对同一用户，同一游戏，只有在充值成功的时候才修改此值
	 */
	public void setChargedGameDays(int value)
	{
		chargedGameDays = value;
	}
	
	
	private int chargedUserDays;
	/** 
	 * 获取当前连接充值的用户天数，针对同一用户，只有在充值成功的时候才修改此值
	 * @return 当前连接充值的用户天数，针对同一用户，只有在充值成功的时候才修改此值
	 */
	public int getChargedUserDays()
	{
		return chargedUserDays;
	}
	
	/**
	 * 设置当前连接充值的用户天数，针对同一用户，只有在充值成功的时候才修改此值
	 * @param 当前连接充值的用户天数，针对同一用户，只有在充值成功的时候才修改此值
	 */
	public void setChargedUserDays(int value)
	{
		chargedUserDays = value;
	}
	
	
	private int chargeServerTimes;
	/** 
	 * 获取当前充值服务器次数，针对同一用户，同一游戏，同一服务器，只有在充值成功时才修改此值
	 * @return 当前充值服务器次数，针对同一用户，同一游戏，同一服务器，只有在充值成功时才修改此值
	 */
	public int getChargeServerTimes()
	{
		return chargeServerTimes;
	}
	
	/**
	 * 设置当前充值服务器次数，针对同一用户，同一游戏，同一服务器，只有在充值成功时才修改此值
	 * @param 当前充值服务器次数，针对同一用户，同一游戏，同一服务器，只有在充值成功时才修改此值
	 */
	public void setChargeServerTimes(int value)
	{
		chargeServerTimes = value;
	}
	
	
	private int chargeGameTimes;
	/** 
	 * 获取当前充值游戏次数，针对同一用户，同一游戏，只有在充值成功时才修改此值
	 * @return 当前充值游戏次数，针对同一用户，同一游戏，只有在充值成功时才修改此值
	 */
	public int getChargeGameTimes()
	{
		return chargeGameTimes;
	}
	
	/**
	 * 设置当前充值游戏次数，针对同一用户，同一游戏，只有在充值成功时才修改此值
	 * @param 当前充值游戏次数，针对同一用户，同一游戏，只有在充值成功时才修改此值
	 */
	public void setChargeGameTimes(int value)
	{
		chargeGameTimes = value;
	}
	
	
	private int chargeUserTimes;
	/** 
	 * 获取当前充值用户次数，针对同一用户，只有在充值成功时才修改此值
	 * @return 当前充值用户次数，针对同一用户，只有在充值成功时才修改此值
	 */
	public int getChargeUserTimes()
	{
		return chargeUserTimes;
	}
	
	/**
	 * 设置当前充值用户次数，针对同一用户，只有在充值成功时才修改此值
	 * @param 当前充值用户次数，针对同一用户，只有在充值成功时才修改此值
	 */
	public void setChargeUserTimes(int value)
	{
		chargeUserTimes = value;
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

	private byte chargeType;
	/**
	 * 获取充值类型：0、充值类型；1、充值钱包；2、充值游戏；4、钱包充值游戏；
	 * @return 充值类型：0、充值类型；1、充值钱包；2、充值游戏；4、钱包充值游戏；
	 */
	public byte getChargeType(){
		return chargeType;
	}
	
	/**
	 * 设置充值类型：0、充值类型；1、充值钱包；2、充值游戏；4、钱包充值游戏；
	 * @param 充值类型：0、充值类型；1、充值钱包；2、充值游戏；4、钱包充值游戏；
	 */
	public void setChargeType(byte value){
		chargeType = value;
	}	
	
	private long customOrderId;
	/** 
	 * 获取自定义订单编号
	 * @return 自定义订单编号
	 */
	public long getCustomOrderId()
	{
		return customOrderId;
	}
	
	/**
	 * 设置自定义订单编号
	 * @param 自定义订单编号
	 */
	public void setCustomOrderId(long value)
	{
		customOrderId = value;
	}
	
	
	private BigDecimal realPayAccount;
	/** 
	 * 获取实际充值金额，没有扣除任何费用的金额
	 * @return 实际充值金额，没有扣除任何费用的金额
	 */
	public BigDecimal getRealPayAccount()
	{
		return realPayAccount;
	}
	
	/**
	 * 设置实际充值金额，没有扣除任何费用的金额
	 * @param 实际充值金额，没有扣除任何费用的金额
	 */
	public void setRealPayAccount(BigDecimal value)
	{
		realPayAccount = value;
	}
	

	private int paySourceType;
	/**
	 * 获取充值来源：0、充值来源；1、九亿网；2、手机；4、Android客户端；8、Iphone客户端；
	 * @return 充值来源：0、充值来源；1、九亿网；2、手机；4、Android客户端；8、Iphone客户端；
	 */
	public int getPaySourceType(){
		return paySourceType;
	}
	
	/**
	 * 设置充值来源：0、充值来源；1、九亿网；2、手机；4、Android客户端；8、Iphone客户端；
	 * @param 充值来源：0、充值来源；1、九亿网；2、手机；4、Android客户端；8、Iphone客户端；
	 */
	public void setPaySourceType(int value){
		paySourceType = value;
	}	
}	


