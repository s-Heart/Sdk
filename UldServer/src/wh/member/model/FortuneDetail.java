package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class FortuneDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public FortuneDetail(){
		 this.status = (byte)0;
	}
		
	private int fortuneDetailId;
	/** 
	 * 获取幸运点详情编号
	 * @return 幸运点详情编号
	 */
	public int getFortuneDetailId()
	{
		return fortuneDetailId;
	}
	
	/**
	 * 设置幸运点详情编号
	 * @param 幸运点详情编号
	 */
	public void setFortuneDetailId(int value)
	{
		fortuneDetailId = value;
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

	private wh.member.model.AccountDetail accountDetail;
	/**
	 * 获取帐户详情
	 * @return 帐户详情
	 */
	public wh.member.model.AccountDetail getAccountDetail(){
		return this.accountDetail;
	}
	
	/**
	 * 设置帐户详情
	 * @param accountDetail 帐户详情
	 */
	public void setAccountDetail(wh.member.model.AccountDetail accountDetail){
		this.accountDetail = accountDetail;
	}
	
	private int accountDetailId;
	/**
	 * 获取详情编号
	 * @return 详情编号
	 */
	public int getAccountDetailId(){
		if(accountDetail != null){
			return accountDetail.getAccountDetailId();
		}else{
			return accountDetailId;
		}
	}
	
	/**
	 * 设置详情编号
	 * @param 详情编号
	 */
	public void setAccountDetailId(int value){
		if(accountDetail != null){
			accountDetail.setAccountDetailId(value);
		}else{
			accountDetailId = value;
		}
	}
	

	private wh.member.model.Fortune fortune;
	/**
	 * 获取幸运点
	 * @return 幸运点
	 */
	public wh.member.model.Fortune getFortune(){
		return this.fortune;
	}
	
	/**
	 * 设置幸运点
	 * @param fortune 幸运点
	 */
	public void setFortune(wh.member.model.Fortune fortune){
		this.fortune = fortune;
	}
	
	private int fortuneId;
	/**
	 * 获取幸运点编号
	 * @return 幸运点编号
	 */
	public int getFortuneId(){
		if(fortune != null){
			return fortune.getFortuneId();
		}else{
			return fortuneId;
		}
	}
	
	/**
	 * 设置幸运点编号
	 * @param 幸运点编号
	 */
	public void setFortuneId(int value){
		if(fortune != null){
			fortune.setFortuneId(value);
		}else{
			fortuneId = value;
		}
	}
	

	private byte fortuneGetType;
	/**
	 * 获取幸运点获得方式：0、幸运点获得方式；1、充值；2、登录签到；3、注册；4、一元红包；5、满月红包；6、生日红包；
	 * @return 幸运点获得方式：0、幸运点获得方式；1、充值；2、登录签到；3、注册；4、一元红包；5、满月红包；6、生日红包；
	 */
	public byte getFortuneGetType(){
		return fortuneGetType;
	}
	
	/**
	 * 设置幸运点获得方式：0、幸运点获得方式；1、充值；2、登录签到；3、注册；4、一元红包；5、满月红包；6、生日红包；
	 * @param 幸运点获得方式：0、幸运点获得方式；1、充值；2、登录签到；3、注册；4、一元红包；5、满月红包；6、生日红包；
	 */
	public void setFortuneGetType(byte value){
		fortuneGetType = value;
	}	
	
	private int fortuneValue;
	/** 
	 * 获取幸运点值
	 * @return 幸运点值
	 */
	public int getFortuneValue()
	{
		return fortuneValue;
	}
	
	/**
	 * 设置幸运点值
	 * @param 幸运点值
	 */
	public void setFortuneValue(int value)
	{
		fortuneValue = value;
	}
	

	private byte fortuneType;
	/**
	 * 获取幸运点类型：0、幸运点类型；1、幸运点；
	 * @return 幸运点类型：0、幸运点类型；1、幸运点；
	 */
	public byte getFortuneType(){
		return fortuneType;
	}
	
	/**
	 * 设置幸运点类型：0、幸运点类型；1、幸运点；
	 * @param 幸运点类型：0、幸运点类型；1、幸运点；
	 */
	public void setFortuneType(byte value){
		fortuneType = value;
	}	

	private byte useType;
	/**
	 * 获取使用类型：0、使用类型；1、获得；2、消费；
	 * @return 使用类型：0、使用类型；1、获得；2、消费；
	 */
	public byte getUseType(){
		return useType;
	}
	
	/**
	 * 设置使用类型：0、使用类型；1、获得；2、消费；
	 * @param 使用类型：0、使用类型；1、获得；2、消费；
	 */
	public void setUseType(byte value){
		useType = value;
	}	
	
	private String remark;
	/** 
	 * 获取备注
	 * @return 备注
	 */
	public String getRemark()
	{
		return remark;
	}
	
	/**
	 * 设置备注
	 * @param 备注
	 */
	public void setRemark(String value)
	{
		remark = value;
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
	
}	


