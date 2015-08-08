package wh.promotion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class PosterChannel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public PosterChannel(){
		 this.status = (byte)0;
	}
		
	private int posterChannelId;
	/** 
	 * 获取海报渠道编号
	 * @return 海报渠道编号
	 */
	public int getPosterChannelId()
	{
		return posterChannelId;
	}
	
	/**
	 * 设置海报渠道编号
	 * @param 海报渠道编号
	 */
	public void setPosterChannelId(int value)
	{
		posterChannelId = value;
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

	private wh.promotion.model.Poster poster;
	/**
	 * 获取海报
	 * @return 海报
	 */
	public wh.promotion.model.Poster getPoster(){
		return this.poster;
	}
	
	/**
	 * 设置海报
	 * @param poster 海报
	 */
	public void setPoster(wh.promotion.model.Poster poster){
		this.poster = poster;
	}
	
	private int posterId;
	/**
	 * 获取海报编号
	 * @return 海报编号
	 */
	public int getPosterId(){
		if(poster != null){
			return poster.getPosterId();
		}else{
			return posterId;
		}
	}
	
	/**
	 * 设置海报编号
	 * @param 海报编号
	 */
	public void setPosterId(int value){
		if(poster != null){
			poster.setPosterId(value);
		}else{
			posterId = value;
		}
	}

	/**
	 * 获取海报名称
	 * @return 海报名称
	 */
	public String getPosterName(){
		if(poster != null){
			return poster.getName();
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
	
	
	private String code;
	/** 
	 * 获取编码
	 * @return 编码
	 */
	public String getCode()
	{
		return code;
	}
	
	/**
	 * 设置编码
	 * @param 编码
	 */
	public void setCode(String value)
	{
		code = value;
	}
	
	
	private String posterUrl;
	/** 
	 * 获取海报地址
	 * @return 海报地址
	 */
	public String getPosterUrl()
	{
		return posterUrl;
	}
	
	/**
	 * 设置海报地址
	 * @param 海报地址
	 */
	public void setPosterUrl(String value)
	{
		posterUrl = value;
	}
	
	
	private String posterAddress;
	/** 
	 * 获取海报物理路径
	 * @return 海报物理路径
	 */
	public String getPosterAddress()
	{
		return posterAddress;
	}
	
	/**
	 * 设置海报物理路径
	 * @param 海报物理路径
	 */
	public void setPosterAddress(String value)
	{
		posterAddress = value;
	}
	
	
	private String flashPath;
	/** 
	 * 获取Flash路径
	 * @return Flash路径
	 */
	public String getFlashPath()
	{
		return flashPath;
	}
	
	/**
	 * 设置Flash路径
	 * @param Flash路径
	 */
	public void setFlashPath(String value)
	{
		flashPath = value;
	}
	
	
	private String registerLoginCode;
	/** 
	 * 获取注册登录代码
	 * @return 注册登录代码
	 */
	public String getRegisterLoginCode()
	{
		return registerLoginCode;
	}
	
	/**
	 * 设置注册登录代码
	 * @param 注册登录代码
	 */
	public void setRegisterLoginCode(String value)
	{
		registerLoginCode = value;
	}
	
	
	private String myStatisticCode;
	/** 
	 * 获取自己统计代码
	 * @return 自己统计代码
	 */
	public String getMyStatisticCode()
	{
		return myStatisticCode;
	}
	
	/**
	 * 设置自己统计代码
	 * @param 自己统计代码
	 */
	public void setMyStatisticCode(String value)
	{
		myStatisticCode = value;
	}
	
	
	private String otherStatisticCode;
	/** 
	 * 获取其它统计代码
	 * @return 其它统计代码
	 */
	public String getOtherStatisticCode()
	{
		return otherStatisticCode;
	}
	
	/**
	 * 设置其它统计代码
	 * @param 其它统计代码
	 */
	public void setOtherStatisticCode(String value)
	{
		otherStatisticCode = value;
	}
	
	
	private BigDecimal extendedCost;
	/** 
	 * 获取推广成本
	 * @return 推广成本
	 */
	public BigDecimal getExtendedCost()
	{
		return extendedCost;
	}
	
	/**
	 * 设置推广成本
	 * @param 推广成本
	 */
	public void setExtendedCost(BigDecimal value)
	{
		extendedCost = value;
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

	private wh.promotion.model.Domain domain;
	/**
	 * 获取海报域名
	 * @return 海报域名
	 */
	public wh.promotion.model.Domain getDomain(){
		return this.domain;
	}
	
	/**
	 * 设置海报域名
	 * @param domain 海报域名
	 */
	public void setDomain(wh.promotion.model.Domain domain){
		this.domain = domain;
	}
	
	private int domainId;
	/**
	 * 获取域名编号
	 * @return 域名编号
	 */
	public int getDomainId(){
		if(domain != null){
			return domain.getDomainId();
		}else{
			return domainId;
		}
	}
	
	/**
	 * 设置域名编号
	 * @param 域名编号
	 */
	public void setDomainId(int value){
		if(domain != null){
			domain.setDomainId(value);
		}else{
			domainId = value;
		}
	}

	/**
	 * 获取域名名称
	 * @return 域名名称
	 */
	public String getDomainName(){
		if(domain != null){
			return domain.getName();
		}else{
			return "";
		}
	
	}	

	private wh.promotion.model.RegTemplate regTemplate;
	/**
	 * 获取注册弹出层模板
	 * @return 注册弹出层模板
	 */
	public wh.promotion.model.RegTemplate getRegTemplate(){
		return this.regTemplate;
	}
	
	/**
	 * 设置注册弹出层模板
	 * @param regTemplate 注册弹出层模板
	 */
	public void setRegTemplate(wh.promotion.model.RegTemplate regTemplate){
		this.regTemplate = regTemplate;
	}
	
	private int regTemplateId;
	/**
	 * 获取注册弹出层模板编号
	 * @return 注册弹出层模板编号
	 */
	public int getRegTemplateId(){
		if(regTemplate != null){
			return regTemplate.getRegTemplateId();
		}else{
			return regTemplateId;
		}
	}
	
	/**
	 * 设置注册弹出层模板编号
	 * @param 注册弹出层模板编号
	 */
	public void setRegTemplateId(int value){
		if(regTemplate != null){
			regTemplate.setRegTemplateId(value);
		}else{
			regTemplateId = value;
		}
	}

	/**
	 * 获取注册弹出层模板名称
	 * @return 注册弹出层模板名称
	 */
	public String getRegTemplateName(){
		if(regTemplate != null){
			return regTemplate.getName();
		}else{
			return "";
		}
	
	}	

	private byte registerCodeType;
	/**
	 * 获取注册框类型：0、注册框类型；1、宿主；2、弹出；4、居中；8、右下；16、左下；32、右上；64、自定义；128、中下；
	 * @return 注册框类型：0、注册框类型；1、宿主；2、弹出；4、居中；8、右下；16、左下；32、右上；64、自定义；128、中下；
	 */
	public byte getRegisterCodeType(){
		return registerCodeType;
	}
	
	/**
	 * 设置注册框类型：0、注册框类型；1、宿主；2、弹出；4、居中；8、右下；16、左下；32、右上；64、自定义；128、中下；
	 * @param 注册框类型：0、注册框类型；1、宿主；2、弹出；4、居中；8、右下；16、左下；32、右上；64、自定义；128、中下；
	 */
	public void setRegisterCodeType(byte value){
		registerCodeType = value;
	}	

	private byte titleType;
	/**
	 * 获取标题类型：0、标题类型；1、动态；2、静态；
	 * @return 标题类型：0、标题类型；1、动态；2、静态；
	 */
	public byte getTitleType(){
		return titleType;
	}
	
	/**
	 * 设置标题类型：0、标题类型；1、动态；2、静态；
	 * @param 标题类型：0、标题类型；1、动态；2、静态；
	 */
	public void setTitleType(byte value){
		titleType = value;
	}	
	
	private String staticTitle;
	/** 
	 * 获取静态标题
	 * @return 静态标题
	 */
	public String getStaticTitle()
	{
		return staticTitle;
	}
	
	/**
	 * 设置静态标题
	 * @param 静态标题
	 */
	public void setStaticTitle(String value)
	{
		staticTitle = value;
	}
	
	
	private String dynamicTitle;
	/** 
	 * 获取动态标题，以|_|分隔
	 * @return 动态标题，以|_|分隔
	 */
	public String getDynamicTitle()
	{
		return dynamicTitle;
	}
	
	/**
	 * 设置动态标题，以|_|分隔
	 * @param 动态标题，以|_|分隔
	 */
	public void setDynamicTitle(String value)
	{
		dynamicTitle = value;
	}
	
	
	private String keyWord;
	/** 
	 * 获取关键字
	 * @return 关键字
	 */
	public String getKeyWord()
	{
		return keyWord;
	}
	
	/**
	 * 设置关键字
	 * @param 关键字
	 */
	public void setKeyWord(String value)
	{
		keyWord = value;
	}
	
	
	private String htmlDesc;
	/** 
	 * 获取描述
	 * @return 描述
	 */
	public String getHtmlDesc()
	{
		return htmlDesc;
	}
	
	/**
	 * 设置描述
	 * @param 描述
	 */
	public void setHtmlDesc(String value)
	{
		htmlDesc = value;
	}
	
	
	private String backgroundColor;
	/** 
	 * 获取背景颜色
	 * @return 背景颜色
	 */
	public String getBackgroundColor()
	{
		return backgroundColor;
	}
	
	/**
	 * 设置背景颜色
	 * @param 背景颜色
	 */
	public void setBackgroundColor(String value)
	{
		backgroundColor = value;
	}
	
}	


