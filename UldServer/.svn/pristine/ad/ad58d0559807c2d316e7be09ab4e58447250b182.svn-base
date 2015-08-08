package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Rebate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Rebate(){
		 this.status = (byte)0;
	}
		
	private int rebateId;
	/** 
	 * 获取返利编号
	 * @return 返利编号
	 */
	public int getRebateId()
	{
		return rebateId;
	}
	
	/**
	 * 设置返利编号
	 * @param 返利编号
	 */
	public void setRebateId(int value)
	{
		rebateId = value;
	}
	
	
	private int mManagerId;
	/** 
	 * 获取管理员编号
	 * @return 管理员编号
	 */
	public int getMManagerId()
	{
		return mManagerId;
	}
	
	/**
	 * 设置管理员编号
	 * @param 管理员编号
	 */
	public void setMManagerId(int value)
	{
		mManagerId = value;
	}
	
	
	private String mManagerName;
	/** 
	 * 获取管理员名称
	 * @return 管理员名称
	 */
	public String getMManagerName()
	{
		return mManagerName;
	}
	
	/**
	 * 设置管理员名称
	 * @param 管理员名称
	 */
	public void setMManagerName(String value)
	{
		mManagerName = value;
	}
	
	
	private String title;
	/** 
	 * 获取标题
	 * @return 标题
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * 设置标题
	 * @param 标题
	 */
	public void setTitle(String value)
	{
		title = value;
	}
	
	
	private int mUserId;
	/** 
	 * 获取用户编号
	 * @return 用户编号
	 */
	public int getMUserId()
	{
		return mUserId;
	}
	
	/**
	 * 设置用户编号
	 * @param 用户编号
	 */
	public void setMUserId(int value)
	{
		mUserId = value;
	}
	
	
	private String mUserName;
	/** 
	 * 获取用户名
	 * @return 用户名
	 */
	public String getMUserName()
	{
		return mUserName;
	}
	
	/**
	 * 设置用户名
	 * @param 用户名
	 */
	public void setMUserName(String value)
	{
		mUserName = value;
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
	
	
	private BigDecimal rebateAccount;
	/** 
	 * 获取返利总金额
	 * @return 返利总金额
	 */
	public BigDecimal getRebateAccount()
	{
		return rebateAccount;
	}
	
	/**
	 * 设置返利总金额
	 * @param 返利总金额
	 */
	public void setRebateAccount(BigDecimal value)
	{
		rebateAccount = value;
	}
	
	
	private BigDecimal singleRebateAccount;
	/** 
	 * 获取单次返利金额
	 * @return 单次返利金额
	 */
	public BigDecimal getSingleRebateAccount()
	{
		return singleRebateAccount;
	}
	
	/**
	 * 设置单次返利金额
	 * @param 单次返利金额
	 */
	public void setSingleRebateAccount(BigDecimal value)
	{
		singleRebateAccount = value;
	}
	
	
	private int rebateCount;
	/** 
	 * 获取返利次数
	 * @return 返利次数
	 */
	public int getRebateCount()
	{
		return rebateCount;
	}
	
	/**
	 * 设置返利次数
	 * @param 返利次数
	 */
	public void setRebateCount(int value)
	{
		rebateCount = value;
	}
	

	private Date rebateBeginDate;
	/**
	 * 获取返利开始时间
	 * @return 返利开始时间
	 */
	public Date getRebateBeginDate()
	{   
		if(rebateBeginDate == null){
			return Utility.getDbMinDate();
		}
		return rebateBeginDate;
	}
	
	/**
	 * 设置返利开始时间
	 * @param 返利开始时间
	 */
	public void setRebateBeginDate(Date value)
	{   
		rebateBeginDate = value;
	}
	
	private Date rebateBeginDateBegin;
	/**
	 * 获取返利开始时间
	 * @return 返利开始时间
	 */
	public Date getRebateBeginDateBegin()
	{   
		if(rebateBeginDateBegin == null){
			return Utility.getDbMinDate();
		}
		return rebateBeginDateBegin;
	}
	
	/**
	 * 设置返利开始时间
	 * @param 返利开始时间
	 */
	public void setRebateBeginDateBegin(Date value)
	{   
		rebateBeginDateBegin = value;
	}
	
	private Date rebateBeginDateEnd;
	/**
	 * 获取返利开始时间
	 * @return 返利开始时间
	 */
	public Date getRebateBeginDateEnd()
	{   
		if(rebateBeginDateEnd == null){
			return Utility.getDbMinDate();
		}
		return rebateBeginDateEnd;
	}
	
	/**
	 * 设置返利开始时间
	 * @param 返利开始时间
	 */
	public void setRebateBeginDateEnd(Date value)
	{   
		rebateBeginDateEnd = value;
	}
	
	

	private Date rebateEndDate;
	/**
	 * 获取返利结束时间
	 * @return 返利结束时间
	 */
	public Date getRebateEndDate()
	{   
		if(rebateEndDate == null){
			return Utility.getDbMinDate();
		}
		return rebateEndDate;
	}
	
	/**
	 * 设置返利结束时间
	 * @param 返利结束时间
	 */
	public void setRebateEndDate(Date value)
	{   
		rebateEndDate = value;
	}
	
	private Date rebateEndDateBegin;
	/**
	 * 获取返利结束时间
	 * @return 返利结束时间
	 */
	public Date getRebateEndDateBegin()
	{   
		if(rebateEndDateBegin == null){
			return Utility.getDbMinDate();
		}
		return rebateEndDateBegin;
	}
	
	/**
	 * 设置返利结束时间
	 * @param 返利结束时间
	 */
	public void setRebateEndDateBegin(Date value)
	{   
		rebateEndDateBegin = value;
	}
	
	private Date rebateEndDateEnd;
	/**
	 * 获取返利结束时间
	 * @return 返利结束时间
	 */
	public Date getRebateEndDateEnd()
	{   
		if(rebateEndDateEnd == null){
			return Utility.getDbMinDate();
		}
		return rebateEndDateEnd;
	}
	
	/**
	 * 设置返利结束时间
	 * @param 返利结束时间
	 */
	public void setRebateEndDateEnd(Date value)
	{   
		rebateEndDateEnd = value;
	}
	
	
	
	private String description;
	/** 
	 * 获取描述
	 * @return 描述
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * 设置描述
	 * @param 描述
	 */
	public void setDescription(String value)
	{
		description = value;
	}
	

	private byte processType;
	/**
	 * 获取返利进度类型：0、返利进度类型；1、未开始执行；2、执行中；4、执行结束；
	 * @return 返利进度类型：0、返利进度类型；1、未开始执行；2、执行中；4、执行结束；
	 */
	public byte getProcessType(){
		return processType;
	}
	
	/**
	 * 设置返利进度类型：0、返利进度类型；1、未开始执行；2、执行中；4、执行结束；
	 * @param 返利进度类型：0、返利进度类型；1、未开始执行；2、执行中；4、执行结束；
	 */
	public void setProcessType(byte value){
		processType = value;
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


