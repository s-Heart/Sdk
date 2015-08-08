package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Refund implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Refund(){
		;
	}
		
	private int refundId;
	/** 
	 * 获取退款编号
	 * @return 退款编号
	 */
	public int getRefundId()
	{
		return refundId;
	}
	
	/**
	 * 设置退款编号
	 * @param 退款编号
	 */
	public void setRefundId(int value)
	{
		refundId = value;
	}
	
	
	private int rUserId;
	/** 
	 * 获取用户编号
	 * @return 用户编号
	 */
	public int getRUserId()
	{
		return rUserId;
	}
	
	/**
	 * 设置用户编号
	 * @param 用户编号
	 */
	public void setRUserId(int value)
	{
		rUserId = value;
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
	
	
	private int rGameId;
	/** 
	 * 获取游戏编号
	 * @return 游戏编号
	 */
	public int getRGameId()
	{
		return rGameId;
	}
	
	/**
	 * 设置游戏编号
	 * @param 游戏编号
	 */
	public void setRGameId(int value)
	{
		rGameId = value;
	}
	
	
	private String gameName;
	/** 
	 * 获取游戏名称
	 * @return 游戏名称
	 */
	public String getGameName()
	{
		return gameName;
	}
	
	/**
	 * 设置游戏名称
	 * @param 游戏名称
	 */
	public void setGameName(String value)
	{
		gameName = value;
	}
	
	
	private int rServerId;
	/** 
	 * 获取服务器编号
	 * @return 服务器编号
	 */
	public int getRServerId()
	{
		return rServerId;
	}
	
	/**
	 * 设置服务器编号
	 * @param 服务器编号
	 */
	public void setRServerId(int value)
	{
		rServerId = value;
	}
	
	
	private String serverName;
	/** 
	 * 获取服务器名称
	 * @return 服务器名称
	 */
	public String getServerName()
	{
		return serverName;
	}
	
	/**
	 * 设置服务器名称
	 * @param 服务器名称
	 */
	public void setServerName(String value)
	{
		serverName = value;
	}
	

	private byte refundType;
	/**
	 * 获取退款状态：0、退款状态；1、退款中；2、退款成功；4、退款失败；
	 * @return 退款状态：0、退款状态；1、退款中；2、退款成功；4、退款失败；
	 */
	public byte getRefundType(){
		return refundType;
	}
	
	/**
	 * 设置退款状态：0、退款状态；1、退款中；2、退款成功；4、退款失败；
	 * @param 退款状态：0、退款状态；1、退款中；2、退款成功；4、退款失败；
	 */
	public void setRefundType(byte value){
		refundType = value;
	}	
	
	private String roleName;
	/** 
	 * 获取角色名称
	 * @return 角色名称
	 */
	public String getRoleName()
	{
		return roleName;
	}
	
	/**
	 * 设置角色名称
	 * @param 角色名称
	 */
	public void setRoleName(String value)
	{
		roleName = value;
	}
	
	
	private int gameMoneyRate;
	/** 
	 * 获取游戏币兑换比例，比如1元兑换50元宝
	 * @return 游戏币兑换比例，比如1元兑换50元宝
	 */
	public int getGameMoneyRate()
	{
		return gameMoneyRate;
	}
	
	/**
	 * 设置游戏币兑换比例，比如1元兑换50元宝
	 * @param 游戏币兑换比例，比如1元兑换50元宝
	 */
	public void setGameMoneyRate(int value)
	{
		gameMoneyRate = value;
	}
	
	
	private int realGameMoneyValue;
	/** 
	 * 获取实际游戏币值
	 * @return 实际游戏币值
	 */
	public int getRealGameMoneyValue()
	{
		return realGameMoneyValue;
	}
	
	/**
	 * 设置实际游戏币值
	 * @param 实际游戏币值
	 */
	public void setRealGameMoneyValue(int value)
	{
		realGameMoneyValue = value;
	}
	
	
	private int applyGameMoneyValue;
	/** 
	 * 获取用户申请游戏币值
	 * @return 用户申请游戏币值
	 */
	public int getApplyGameMoneyValue()
	{
		return applyGameMoneyValue;
	}
	
	/**
	 * 设置用户申请游戏币值
	 * @param 用户申请游戏币值
	 */
	public void setApplyGameMoneyValue(int value)
	{
		applyGameMoneyValue = value;
	}
	
	
	private int refundValue;
	/** 
	 * 获取退款金额，单位T币
	 * @return 退款金额，单位T币
	 */
	public int getRefundValue()
	{
		return refundValue;
	}
	
	/**
	 * 设置退款金额，单位T币
	 * @param 退款金额，单位T币
	 */
	public void setRefundValue(int value)
	{
		refundValue = value;
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
	
	
}	


