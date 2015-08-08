package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class GoodsOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public GoodsOrder(){
		 this.status = (byte)0;
	}
		
	private int goodsOrderId;
	/** 
	 * 获取商品订单编号
	 * @return 商品订单编号
	 */
	public int getGoodsOrderId()
	{
		return goodsOrderId;
	}
	
	/**
	 * 设置商品订单编号
	 * @param 商品订单编号
	 */
	public void setGoodsOrderId(int value)
	{
		goodsOrderId = value;
	}
	

	private wh.member.model.FortuneDetail fortuneDetail;
	/**
	 * 获取幸运点详情
	 * @return 幸运点详情
	 */
	public wh.member.model.FortuneDetail getFortuneDetail(){
		return this.fortuneDetail;
	}
	
	/**
	 * 设置幸运点详情
	 * @param fortuneDetail 幸运点详情
	 */
	public void setFortuneDetail(wh.member.model.FortuneDetail fortuneDetail){
		this.fortuneDetail = fortuneDetail;
	}
	
	private int fortuneDetailId;
	/**
	 * 获取幸运点详情编号
	 * @return 幸运点详情编号
	 */
	public int getFortuneDetailId(){
		if(fortuneDetail != null){
			return fortuneDetail.getFortuneDetailId();
		}else{
			return fortuneDetailId;
		}
	}
	
	/**
	 * 设置幸运点详情编号
	 * @param 幸运点详情编号
	 */
	public void setFortuneDetailId(int value){
		if(fortuneDetail != null){
			fortuneDetail.setFortuneDetailId(value);
		}else{
			fortuneDetailId = value;
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
	

	private byte paymentType;
	/**
	 * 获取支付类型：0、支付类型；1、幸运点；2、平台币；4、幸运点平台币；
	 * @return 支付类型：0、支付类型；1、幸运点；2、平台币；4、幸运点平台币；
	 */
	public byte getPaymentType(){
		return paymentType;
	}
	
	/**
	 * 设置支付类型：0、支付类型；1、幸运点；2、平台币；4、幸运点平台币；
	 * @param 支付类型：0、支付类型；1、幸运点；2、平台币；4、幸运点平台币；
	 */
	public void setPaymentType(byte value){
		paymentType = value;
	}	
	
	private int needDB;
	/** 
	 * 获取所需D币
	 * @return 所需D币
	 */
	public int getNeedDB()
	{
		return needDB;
	}
	
	/**
	 * 设置所需D币
	 * @param 所需D币
	 */
	public void setNeedDB(int value)
	{
		needDB = value;
	}
	
	
	private int needFortune;
	/** 
	 * 获取所需幸运点
	 * @return 所需幸运点
	 */
	public int getNeedFortune()
	{
		return needFortune;
	}
	
	/**
	 * 设置所需幸运点
	 * @param 所需幸运点
	 */
	public void setNeedFortune(int value)
	{
		needFortune = value;
	}
	
	
	private int shipping;
	/** 
	 * 获取运费
	 * @return 运费
	 */
	public int getShipping()
	{
		return shipping;
	}
	
	/**
	 * 设置运费
	 * @param 运费
	 */
	public void setShipping(int value)
	{
		shipping = value;
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
	
	
	private int goodsCount;
	/** 
	 * 获取商品数量
	 * @return 商品数量
	 */
	public int getGoodsCount()
	{
		return goodsCount;
	}
	
	/**
	 * 设置商品数量
	 * @param 商品数量
	 */
	public void setGoodsCount(int value)
	{
		goodsCount = value;
	}
	
	
	private int goodsTotalCount;
	/** 
	 * 获取商品总数量
	 * @return 商品总数量
	 */
	public int getGoodsTotalCount()
	{
		return goodsTotalCount;
	}
	
	/**
	 * 设置商品总数量
	 * @param 商品总数量
	 */
	public void setGoodsTotalCount(int value)
	{
		goodsTotalCount = value;
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
	
	
	private String addressee;
	/** 
	 * 获取收件人
	 * @return 收件人
	 */
	public String getAddressee()
	{
		return addressee;
	}
	
	/**
	 * 设置收件人
	 * @param 收件人
	 */
	public void setAddressee(String value)
	{
		addressee = value;
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
	
	
	private String email;
	/** 
	 * 获取电子邮件
	 * @return 电子邮件
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * 设置电子邮件
	 * @param 电子邮件
	 */
	public void setEmail(String value)
	{
		email = value;
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
	

	private int shopOrderType;
	/**
	 * 获取订单类型：0、订单类型；1、未发货；2、发货中；4、已发货；8、已签收；16、已完成；32、已取消；64、退款申请；128、退货中；256、已退货；
	 * @return 订单类型：0、订单类型；1、未发货；2、发货中；4、已发货；8、已签收；16、已完成；32、已取消；64、退款申请；128、退货中；256、已退货；
	 */
	public int getShopOrderType(){
		return shopOrderType;
	}
	
	/**
	 * 设置订单类型：0、订单类型；1、未发货；2、发货中；4、已发货；8、已签收；16、已完成；32、已取消；64、退款申请；128、退货中；256、已退货；
	 * @param 订单类型：0、订单类型；1、未发货；2、发货中；4、已发货；8、已签收；16、已完成；32、已取消；64、退款申请；128、退货中；256、已退货；
	 */
	public void setShopOrderType(int value){
		shopOrderType = value;
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


