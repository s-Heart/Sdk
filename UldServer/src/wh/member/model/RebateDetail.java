package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class RebateDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public RebateDetail(){
		 this.status = (byte)0;
	}
		
	private int rebateDetailId;
	/** 
	 * 获取返利详情编号
	 * @return 返利详情编号
	 */
	public int getRebateDetailId()
	{
		return rebateDetailId;
	}
	
	/**
	 * 设置返利详情编号
	 * @param 返利详情编号
	 */
	public void setRebateDetailId(int value)
	{
		rebateDetailId = value;
	}
	

	private wh.member.model.Rebate rebate;
	/**
	 * 获取返利
	 * @return 返利
	 */
	public wh.member.model.Rebate getRebate(){
		return this.rebate;
	}
	
	/**
	 * 设置返利
	 * @param rebate 返利
	 */
	public void setRebate(wh.member.model.Rebate rebate){
		this.rebate = rebate;
	}
	
	private int rebateId;
	/**
	 * 获取返利编号
	 * @return 返利编号
	 */
	public int getRebateId(){
		if(rebate != null){
			return rebate.getRebateId();
		}else{
			return rebateId;
		}
	}
	
	/**
	 * 设置返利编号
	 * @param 返利编号
	 */
	public void setRebateId(int value){
		if(rebate != null){
			rebate.setRebateId(value);
		}else{
			rebateId = value;
		}
	}

	/**
	 * 获取返利名称
	 * @return 返利名称
	 */
	public String getRebateName(){
		if(getRebate() != null){
			return getRebate().getTitle();
		}else{
			return "";
		}
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
	
	

	private byte executeType;
	/**
	 * 获取返利执行情况：0、返利执行情况；1、执行成功；2、执行失败；
	 * @return 返利执行情况：0、返利执行情况；1、执行成功；2、执行失败；
	 */
	public byte getExecuteType(){
		return executeType;
	}
	
	/**
	 * 设置返利执行情况：0、返利执行情况；1、执行成功；2、执行失败；
	 * @param 返利执行情况：0、返利执行情况；1、执行成功；2、执行失败；
	 */
	public void setExecuteType(byte value){
		executeType = value;
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


