package wh.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class OrderChannel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public OrderChannel(){
		 this.status = (byte)0;
	}
		
	private int orderChannelId;
	/** 
	 * 获取订单渠道编号
	 * @return 订单渠道编号
	 */
	public int getOrderChannelId()
	{
		return orderChannelId;
	}
	
	/**
	 * 设置订单渠道编号
	 * @param 订单渠道编号
	 */
	public void setOrderChannelId(int value)
	{
		orderChannelId = value;
	}
	
	
	private int localOrderId;
	/** 
	 * 获取本地订单编号
	 * @return 本地订单编号
	 */
	public int getLocalOrderId()
	{
		return localOrderId;
	}
	
	/**
	 * 设置本地订单编号
	 * @param 本地订单编号
	 */
	public void setLocalOrderId(int value)
	{
		localOrderId = value;
	}
	
	
	private int regChannelId;
	/** 
	 * 获取注册渠道编号
	 * @return 注册渠道编号
	 */
	public int getRegChannelId()
	{
		return regChannelId;
	}
	
	/**
	 * 设置注册渠道编号
	 * @param 注册渠道编号
	 */
	public void setRegChannelId(int value)
	{
		regChannelId = value;
	}
	
	
	private int ocChannelId;
	/** 
	 * 获取实际渠道编号
	 * @return 实际渠道编号
	 */
	public int getOcChannelId()
	{
		return ocChannelId;
	}
	
	/**
	 * 设置实际渠道编号
	 * @param 实际渠道编号
	 */
	public void setOcChannelId(int value)
	{
		ocChannelId = value;
	}
	
	
	private int gameChannelId;
	/** 
	 * 获取游戏方渠道编号
	 * @return 游戏方渠道编号
	 */
	public int getGameChannelId()
	{
		return gameChannelId;
	}
	
	/**
	 * 设置游戏方渠道编号
	 * @param 游戏方渠道编号
	 */
	public void setGameChannelId(int value)
	{
		gameChannelId = value;
	}
	
	
	private int reservedInt;
	/** 
	 * 获取预留值
	 * @return 预留值
	 */
	public int getReservedInt()
	{
		return reservedInt;
	}
	
	/**
	 * 设置预留值
	 * @param 预留值
	 */
	public void setReservedInt(int value)
	{
		reservedInt = value;
	}
	
	
	private int reservedStr;
	/** 
	 * 获取预留串
	 * @return 预留串
	 */
	public int getReservedStr()
	{
		return reservedStr;
	}
	
	/**
	 * 设置预留串
	 * @param 预留串
	 */
	public void setReservedStr(int value)
	{
		reservedStr = value;
	}
	

	private byte status;
	/**
	 * 获取状态：0、状态；1、显示；2、不显示；
	 * @return 状态：0、状态；1、显示；2、不显示；
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * 设置状态：0、状态；1、显示；2、不显示；
	 * @param 状态：0、状态；1、显示；2、不显示；
	 */
	public void setStatus(byte value){
		status = value;
	}	
}	


