package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public User(){
		 this.status = (byte)0;
	}
		
	private int userId;
	/** 
	 * 获取用户编号
	 * @return 用户编号
	 */
	public int getUserId()
	{
		return userId;
	}
	
	/**
	 * 设置用户编号
	 * @param 用户编号
	 */
	public void setUserId(int value)
	{
		userId = value;
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

	private wh.member.model.UserPortrait userPortrait;
	/**
	 * 获取用户默认头像
	 * @return 用户默认头像
	 */
	public wh.member.model.UserPortrait getUserPortrait(){
		return this.userPortrait;
	}
	
	/**
	 * 设置用户默认头像
	 * @param userPortrait 用户默认头像
	 */
	public void setUserPortrait(wh.member.model.UserPortrait userPortrait){
		this.userPortrait = userPortrait;
	}
	
	private int userPortraitId;
	/**
	 * 获取默认头像编号
	 * @return 默认头像编号
	 */
	public int getUserPortraitId(){
		if(userPortrait != null){
			return userPortrait.getUserPortraitId();
		}else{
			return userPortraitId;
		}
	}
	
	/**
	 * 设置默认头像编号
	 * @param 默认头像编号
	 */
	public void setUserPortraitId(int value){
		if(userPortrait != null){
			userPortrait.setUserPortraitId(value);
		}else{
			userPortraitId = value;
		}
	}

	/**
	 * 获取默认头像名称
	 * @return 默认头像名称
	 */
	public String getUserPortraitName(){
		if(getUserPortrait() != null){
			return getUserPortrait().getTitle();
		}else{
			return "";
		}
	}	

	private wh.promotion.model.StatisticAnalysis statisticAnalysis;
	/**
	 * 获取统计分析
	 * @return 统计分析
	 */
	public wh.promotion.model.StatisticAnalysis getStatisticAnalysis(){
		return this.statisticAnalysis;
	}
	
	/**
	 * 设置统计分析
	 * @param statisticAnalysis 统计分析
	 */
	public void setStatisticAnalysis(wh.promotion.model.StatisticAnalysis statisticAnalysis){
		this.statisticAnalysis = statisticAnalysis;
	}
	
	private int statisticAnalysisId;
	/**
	 * 获取统计分析编号
	 * @return 统计分析编号
	 */
	public int getStatisticAnalysisId(){
		if(statisticAnalysis != null){
			return statisticAnalysis.getStatisticAnalysisId();
		}else{
			return statisticAnalysisId;
		}
	}
	
	/**
	 * 设置统计分析编号
	 * @param 统计分析编号
	 */
	public void setStatisticAnalysisId(int value){
		if(statisticAnalysis != null){
			statisticAnalysis.setStatisticAnalysisId(value);
		}else{
			statisticAnalysisId = value;
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
	
	private String userName;
	/** 
	 * 获取用户名
	 * @return 用户名
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * 设置用户名
	 * @param 用户名
	 */
	public void setUserName(String value)
	{
		userName = value;
	}
	
	
	private String password;
	/** 
	 * 获取密码
	 * @return 密码
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * 设置密码
	 * @param 密码
	 */
	public void setPassword(String value)
	{
		password = value;
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
	
	
	private String nickName;
	/** 
	 * 获取昵称
	 * @return 昵称
	 */
	public String getNickName()
	{
		return nickName;
	}
	
	/**
	 * 设置昵称
	 * @param 昵称
	 */
	public void setNickName(String value)
	{
		nickName = value;
	}
	

	private byte genderType;
	/**
	 * 获取性别：0、性别；1、男；2、女；
	 * @return 性别：0、性别；1、男；2、女；
	 */
	public byte getGenderType(){
		return genderType;
	}
	
	/**
	 * 设置性别：0、性别；1、男；2、女；
	 * @param 性别：0、性别；1、男；2、女；
	 */
	public void setGenderType(byte value){
		genderType = value;
	}	

	private Date birthday;
	/**
	 * 获取出生日期
	 * @return 出生日期
	 */
	public Date getBirthday()
	{   
		if(birthday == null){
			return Utility.getDbMinDate();
		}
		return birthday;
	}
	
	/**
	 * 设置出生日期
	 * @param 出生日期
	 */
	public void setBirthday(Date value)
	{   
		birthday = value;
	}
	
	private Date birthdayBegin;
	/**
	 * 获取出生日期
	 * @return 出生日期
	 */
	public Date getBirthdayBegin()
	{   
		if(birthdayBegin == null){
			return Utility.getDbMinDate();
		}
		return birthdayBegin;
	}
	
	/**
	 * 设置出生日期
	 * @param 出生日期
	 */
	public void setBirthdayBegin(Date value)
	{   
		birthdayBegin = value;
	}
	
	private Date birthdayEnd;
	/**
	 * 获取出生日期
	 * @return 出生日期
	 */
	public Date getBirthdayEnd()
	{   
		if(birthdayEnd == null){
			return Utility.getDbMinDate();
		}
		return birthdayEnd;
	}
	
	/**
	 * 设置出生日期
	 * @param 出生日期
	 */
	public void setBirthdayEnd(Date value)
	{   
		birthdayEnd = value;
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

	private int sourceType;
	/**
	 * 获取来源类型：0、来源类型；1、导航；2、角色；4、喔喔；8、明珠；16、问仙；32、仙域；64、龙门；128、傲视；256、星客；512、手机；1024、其它；
	 * @return 来源类型：0、来源类型；1、导航；2、角色；4、喔喔；8、明珠；16、问仙；32、仙域；64、龙门；128、傲视；256、星客；512、手机；1024、其它；
	 */
	public int getSourceType(){
		return sourceType;
	}
	
	/**
	 * 设置来源类型：0、来源类型；1、导航；2、角色；4、喔喔；8、明珠；16、问仙；32、仙域；64、龙门；128、傲视；256、星客；512、手机；1024、其它；
	 * @param 来源类型：0、来源类型；1、导航；2、角色；4、喔喔；8、明珠；16、问仙；32、仙域；64、龙门；128、傲视；256、星客；512、手机；1024、其它；
	 */
	public void setSourceType(int value){
		sourceType = value;
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
	
	
	private String address;
	/** 
	 * 获取地址
	 * @return 地址
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * 设置地址
	 * @param 地址
	 */
	public void setAddress(String value)
	{
		address = value;
	}
	
	
	private String postcode;
	/** 
	 * 获取邮编
	 * @return 邮编
	 */
	public String getPostcode()
	{
		return postcode;
	}
	
	/**
	 * 设置邮编
	 * @param 邮编
	 */
	public void setPostcode(String value)
	{
		postcode = value;
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
	
	
	private String email1;
	/** 
	 * 获取Email1
	 * @return Email1
	 */
	public String getEmail1()
	{
		return email1;
	}
	
	/**
	 * 设置Email1
	 * @param Email1
	 */
	public void setEmail1(String value)
	{
		email1 = value;
	}
	
	
	private String email2;
	/** 
	 * 获取Email2
	 * @return Email2
	 */
	public String getEmail2()
	{
		return email2;
	}
	
	/**
	 * 设置Email2
	 * @param Email2
	 */
	public void setEmail2(String value)
	{
		email2 = value;
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
	
	
	private String otherMobilePhone;
	/** 
	 * 获取其它手机号，以,号分隔
	 * @return 其它手机号，以,号分隔
	 */
	public String getOtherMobilePhone()
	{
		return otherMobilePhone;
	}
	
	/**
	 * 设置其它手机号，以,号分隔
	 * @param 其它手机号，以,号分隔
	 */
	public void setOtherMobilePhone(String value)
	{
		otherMobilePhone = value;
	}
	
	
	private String qq;
	/** 
	 * 获取QQ
	 * @return QQ
	 */
	public String getQQ()
	{
		return qq;
	}
	
	/**
	 * 设置QQ
	 * @param QQ
	 */
	public void setQQ(String value)
	{
		qq = value;
	}
	
	
	private String mSN;
	/** 
	 * 获取MSN
	 * @return MSN
	 */
	public String getMSN()
	{
		return mSN;
	}
	
	/**
	 * 设置MSN
	 * @param MSN
	 */
	public void setMSN(String value)
	{
		mSN = value;
	}
	

	private int memberRankType;
	/**
	 * 获取会员等级：0、会员等级；1、普通会员；2、铜牌会员；3、白银会员；4、黄金会员；5、白金会员；6、钻石会员；7、皇冠会员；
	 * @return 会员等级：0、会员等级；1、普通会员；2、铜牌会员；3、白银会员；4、黄金会员；5、白金会员；6、钻石会员；7、皇冠会员；
	 */
	public int getMemberRankType(){
		return memberRankType;
	}
	
	/**
	 * 设置会员等级：0、会员等级；1、普通会员；2、铜牌会员；3、白银会员；4、黄金会员；5、白金会员；6、钻石会员；7、皇冠会员；
	 * @param 会员等级：0、会员等级；1、普通会员；2、铜牌会员；3、白银会员；4、黄金会员；5、白金会员；6、钻石会员；7、皇冠会员；
	 */
	public void setMemberRankType(int value){
		memberRankType = value;
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
	
	

	private int authenticationType;
	/**
	 * 获取密保认证类型：0、全部；1、未进入认证；2、密保认证未通过；4、密保认证中；8、密保认证通过；
	 * @return 密保认证类型：0、全部；1、未进入认证；2、密保认证未通过；4、密保认证中；8、密保认证通过；
	 */
	public int getAuthenticationType(){
		return authenticationType;
	}
	
	/**
	 * 设置密保认证类型：0、全部；1、未进入认证；2、密保认证未通过；4、密保认证中；8、密保认证通过；
	 * @param 密保认证类型：0、全部；1、未进入认证；2、密保认证未通过；4、密保认证中；8、密保认证通过；
	 */
	public void setAuthenticationType(int value){
		authenticationType = value;
	}	

	private int emailAuthenticationType;
	/**
	 * 获取邮箱认证类型：0、全部；1、未进入认证；2、邮箱认证未通过；4、邮箱认证中；8、邮箱认证通过；
	 * @return 邮箱认证类型：0、全部；1、未进入认证；2、邮箱认证未通过；4、邮箱认证中；8、邮箱认证通过；
	 */
	public int getEmailAuthenticationType(){
		return emailAuthenticationType;
	}
	
	/**
	 * 设置邮箱认证类型：0、全部；1、未进入认证；2、邮箱认证未通过；4、邮箱认证中；8、邮箱认证通过；
	 * @param 邮箱认证类型：0、全部；1、未进入认证；2、邮箱认证未通过；4、邮箱认证中；8、邮箱认证通过；
	 */
	public void setEmailAuthenticationType(int value){
		emailAuthenticationType = value;
	}	

	private int mobileAuthenticationType;
	/**
	 * 获取手机认证类型：0、全部；1、未进入认证；2、手机认证未通过；4、手机认证中；8、手机认证通过；
	 * @return 手机认证类型：0、全部；1、未进入认证；2、手机认证未通过；4、手机认证中；8、手机认证通过；
	 */
	public int getMobileAuthenticationType(){
		return mobileAuthenticationType;
	}
	
	/**
	 * 设置手机认证类型：0、全部；1、未进入认证；2、手机认证未通过；4、手机认证中；8、手机认证通过；
	 * @param 手机认证类型：0、全部；1、未进入认证；2、手机认证未通过；4、手机认证中；8、手机认证通过；
	 */
	public void setMobileAuthenticationType(int value){
		mobileAuthenticationType = value;
	}	

	private int indulgenceAuthenticationType;
	/**
	 * 获取防沉迷认证类型：0、全部；1、未进入认证；2、防沉迷认证未通过；4、防沉迷认证中；8、防沉迷认证通过；
	 * @return 防沉迷认证类型：0、全部；1、未进入认证；2、防沉迷认证未通过；4、防沉迷认证中；8、防沉迷认证通过；
	 */
	public int getIndulgenceAuthenticationType(){
		return indulgenceAuthenticationType;
	}
	
	/**
	 * 设置防沉迷认证类型：0、全部；1、未进入认证；2、防沉迷认证未通过；4、防沉迷认证中；8、防沉迷认证通过；
	 * @param 防沉迷认证类型：0、全部；1、未进入认证；2、防沉迷认证未通过；4、防沉迷认证中；8、防沉迷认证通过；
	 */
	public void setIndulgenceAuthenticationType(int value){
		indulgenceAuthenticationType = value;
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
	
	private String mBRealName;
	/** 
	 * 获取密保真实姓名
	 * @return 密保真实姓名
	 */
	public String getMBRealName()
	{
		return mBRealName;
	}
	
	/**
	 * 设置密保真实姓名
	 * @param 密保真实姓名
	 */
	public void setMBRealName(String value)
	{
		mBRealName = value;
	}
	
	
	private String mBIDCard;
	/** 
	 * 获取密保身份证
	 * @return 密保身份证
	 */
	public String getMBIDCard()
	{
		return mBIDCard;
	}
	
	/**
	 * 设置密保身份证
	 * @param 密保身份证
	 */
	public void setMBIDCard(String value)
	{
		mBIDCard = value;
	}
	
	
	private String mBMobilePhone;
	/** 
	 * 获取密保手机
	 * @return 密保手机
	 */
	public String getMBMobilePhone()
	{
		return mBMobilePhone;
	}
	
	/**
	 * 设置密保手机
	 * @param 密保手机
	 */
	public void setMBMobilePhone(String value)
	{
		mBMobilePhone = value;
	}
	
	
	private String mBEmail;
	/** 
	 * 获取密保Email
	 * @return 密保Email
	 */
	public String getMBEmail()
	{
		return mBEmail;
	}
	
	/**
	 * 设置密保Email
	 * @param 密保Email
	 */
	public void setMBEmail(String value)
	{
		mBEmail = value;
	}
	

	private int passordQuestionOneType;
	/**
	 * 获取密码保护问题1：0、请选择密码保护问题；1、我的初中名称；2、我的高中名称；3、我的大学名称；
	 * @return 密码保护问题1：0、请选择密码保护问题；1、我的初中名称；2、我的高中名称；3、我的大学名称；
	 */
	public int getPassordQuestionOneType(){
		return passordQuestionOneType;
	}
	
	/**
	 * 设置密码保护问题1：0、请选择密码保护问题；1、我的初中名称；2、我的高中名称；3、我的大学名称；
	 * @param 密码保护问题1：0、请选择密码保护问题；1、我的初中名称；2、我的高中名称；3、我的大学名称；
	 */
	public void setPassordQuestionOneType(int value){
		passordQuestionOneType = value;
	}	
	
	private String passordQuestionOneValue;
	/** 
	 * 获取密码保护问题1值
	 * @return 密码保护问题1值
	 */
	public String getPassordQuestionOneValue()
	{
		return passordQuestionOneValue;
	}
	
	/**
	 * 设置密码保护问题1值
	 * @param 密码保护问题1值
	 */
	public void setPassordQuestionOneValue(String value)
	{
		passordQuestionOneValue = value;
	}
	
	
	private String passordAnswerOne;
	/** 
	 * 获取保护问题1答案
	 * @return 保护问题1答案
	 */
	public String getPassordAnswerOne()
	{
		return passordAnswerOne;
	}
	
	/**
	 * 设置保护问题1答案
	 * @param 保护问题1答案
	 */
	public void setPassordAnswerOne(String value)
	{
		passordAnswerOne = value;
	}
	

	private int passordQuestionTwoType;
	/**
	 * 获取密码保护问题2：0、请选择密码保护问题；1、我的出生地；2、我父亲的名字；3、我母亲的名字；
	 * @return 密码保护问题2：0、请选择密码保护问题；1、我的出生地；2、我父亲的名字；3、我母亲的名字；
	 */
	public int getPassordQuestionTwoType(){
		return passordQuestionTwoType;
	}
	
	/**
	 * 设置密码保护问题2：0、请选择密码保护问题；1、我的出生地；2、我父亲的名字；3、我母亲的名字；
	 * @param 密码保护问题2：0、请选择密码保护问题；1、我的出生地；2、我父亲的名字；3、我母亲的名字；
	 */
	public void setPassordQuestionTwoType(int value){
		passordQuestionTwoType = value;
	}	
	
	private String passordQuestionTwoValue;
	/** 
	 * 获取密码保护问题2值
	 * @return 密码保护问题2值
	 */
	public String getPassordQuestionTwoValue()
	{
		return passordQuestionTwoValue;
	}
	
	/**
	 * 设置密码保护问题2值
	 * @param 密码保护问题2值
	 */
	public void setPassordQuestionTwoValue(String value)
	{
		passordQuestionTwoValue = value;
	}
	
	
	private String passordAnswerTwo;
	/** 
	 * 获取密码保护问题2答案
	 * @return 密码保护问题2答案
	 */
	public String getPassordAnswerTwo()
	{
		return passordAnswerTwo;
	}
	
	/**
	 * 设置密码保护问题2答案
	 * @param 密码保护问题2答案
	 */
	public void setPassordAnswerTwo(String value)
	{
		passordAnswerTwo = value;
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
	
	private int registerGameId;
	/** 
	 * 获取注册游戏编号
	 * @return 注册游戏编号
	 */
	public int getRegisterGameId()
	{
		return registerGameId;
	}
	
	/**
	 * 设置注册游戏编号
	 * @param 注册游戏编号
	 */
	public void setRegisterGameId(int value)
	{
		registerGameId = value;
	}
	
	
	private int registerServerId;
	/** 
	 * 获取注册服务器编号
	 * @return 注册服务器编号
	 */
	public int getRegisterServerId()
	{
		return registerServerId;
	}
	
	/**
	 * 设置注册服务器编号
	 * @param 注册服务器编号
	 */
	public void setRegisterServerId(int value)
	{
		registerServerId = value;
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
	
	private String rawPassword;
	/** 
	 * 获取原始密码
	 * @return 原始密码
	 */
	public String getRawPassword()
	{
		return rawPassword;
	}
	
	/**
	 * 设置原始密码
	 * @param 原始密码
	 */
	public void setRawPassword(String value)
	{
		rawPassword = value;
	}
	
}	


