package wh.member.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class UserMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public UserMessage(){
		 this.status = (byte)0;
	}
		
	private int userMessageId;
	/** 
	 * 获取用户消息编号
	 * @return 用户消息编号
	 */
	public int getUserMessageId()
	{
		return userMessageId;
	}
	
	/**
	 * 设置用户消息编号
	 * @param 用户消息编号
	 */
	public void setUserMessageId(int value)
	{
		userMessageId = value;
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

	private wh.member.model.Message message;
	/**
	 * 获取消息
	 * @return 消息
	 */
	public wh.member.model.Message getMessage(){
		return this.message;
	}
	
	/**
	 * 设置消息
	 * @param message 消息
	 */
	public void setMessage(wh.member.model.Message message){
		this.message = message;
	}
	
	private int messageId;
	/**
	 * 获取消息编号
	 * @return 消息编号
	 */
	public int getMessageId(){
		if(message != null){
			return message.getMessageId();
		}else{
			return messageId;
		}
	}
	
	/**
	 * 设置消息编号
	 * @param 消息编号
	 */
	public void setMessageId(int value){
		if(message != null){
			message.setMessageId(value);
		}else{
			messageId = value;
		}
	}

	/**
	 * 获取消息名称
	 * @return 消息名称
	 */
	public String getMessageName(){
		if(getMessage() != null){
			return getMessage().getTitle();
		}else{
			return "";
		}
	}	

	private byte readType;
	/**
	 * 获取已读类型：0、是否已读；1、已读；2、未读；
	 * @return 已读类型：0、是否已读；1、已读；2、未读；
	 */
	public byte getReadType(){
		return readType;
	}
	
	/**
	 * 设置已读类型：0、是否已读；1、已读；2、未读；
	 * @param 已读类型：0、是否已读；1、已读；2、未读；
	 */
	public void setReadType(byte value){
		readType = value;
	}	

	private byte transferType;
	/**
	 * 获取消息传输方式：0、消息传输方式；1、接收；2、发送；
	 * @return 消息传输方式：0、消息传输方式；1、接收；2、发送；
	 */
	public byte getTransferType(){
		return transferType;
	}
	
	/**
	 * 设置消息传输方式：0、消息传输方式；1、接收；2、发送；
	 * @param 消息传输方式：0、消息传输方式；1、接收；2、发送；
	 */
	public void setTransferType(byte value){
		transferType = value;
	}	

	private byte messageType;
	/**
	 * 获取消息类型：0、消息类型；1、系统；2、个人；
	 * @return 消息类型：0、消息类型；1、系统；2、个人；
	 */
	public byte getMessageType(){
		return messageType;
	}
	
	/**
	 * 设置消息类型：0、消息类型；1、系统；2、个人；
	 * @param 消息类型：0、消息类型；1、系统；2、个人；
	 */
	public void setMessageType(byte value){
		messageType = value;
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
	
	private int sourceUserId;
	/** 
	 * 获取消息来源用户编号
	 * @return 消息来源用户编号
	 */
	public int getSourceUserId()
	{
		return sourceUserId;
	}
	
	/**
	 * 设置消息来源用户编号
	 * @param 消息来源用户编号
	 */
	public void setSourceUserId(int value)
	{
		sourceUserId = value;
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
	
	
}	


