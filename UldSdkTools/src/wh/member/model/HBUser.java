package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class HBUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public HBUser(){
		;
	}
		
	private int hbUserId;
	/** 
	 * 获取海报用户编号
	 * @return 海报用户编号
	 */
	public int getHbUserId()
	{
		return hbUserId;
	}
	
	/**
	 * 设置海报用户编号
	 * @param 海报用户编号
	 */
	public void setHbUserId(int value)
	{
		hbUserId = value;
	}
	
	
	private int localUserId;
	/** 
	 * 获取本地用户编号
	 * @return 本地用户编号
	 */
	public int getLocalUserId()
	{
		return localUserId;
	}
	
	/**
	 * 设置本地用户编号
	 * @param 本地用户编号
	 */
	public void setLocalUserId(int value)
	{
		localUserId = value;
	}
	

	private byte dealType;
	/**
	 * 获取处理状态：0、处理状态；1、未处理；2、已处理；4、登录未处理；8、登录已处理；16、登录游戏未处理；32、登录游戏已处理；64、充值ChargedDays未处理；128、充值ChargedDays已处理；129、BBS用户同步未处理；130、BBS用户同步已处理；
	 * @return 处理状态：0、处理状态；1、未处理；2、已处理；4、登录未处理；8、登录已处理；16、登录游戏未处理；32、登录游戏已处理；64、充值ChargedDays未处理；128、充值ChargedDays已处理；129、BBS用户同步未处理；130、BBS用户同步已处理；
	 */
	public byte getDealType(){
		return dealType;
	}
	
	/**
	 * 设置处理状态：0、处理状态；1、未处理；2、已处理；4、登录未处理；8、登录已处理；16、登录游戏未处理；32、登录游戏已处理；64、充值ChargedDays未处理；128、充值ChargedDays已处理；129、BBS用户同步未处理；130、BBS用户同步已处理；
	 * @param 处理状态：0、处理状态；1、未处理；2、已处理；4、登录未处理；8、登录已处理；16、登录游戏未处理；32、登录游戏已处理；64、充值ChargedDays未处理；128、充值ChargedDays已处理；129、BBS用户同步未处理；130、BBS用户同步已处理；
	 */
	public void setDealType(byte value){
		dealType = value;
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
	
	
	
	private String otherInfo;
	/** 
	 * 获取其它信息
	 * @return 其它信息
	 */
	public String getOtherInfo()
	{
		return otherInfo;
	}
	
	/**
	 * 设置其它信息
	 * @param 其它信息
	 */
	public void setOtherInfo(String value)
	{
		otherInfo = value;
	}
	
}	


