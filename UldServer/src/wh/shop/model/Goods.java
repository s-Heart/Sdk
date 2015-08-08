package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class Goods implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public Goods(){
		 this.status = (byte)0;
	}
		
	private int goodsId;
	/** 
	 * 获取商品编号
	 * @return 商品编号
	 */
	public int getGoodsId()
	{
		return goodsId;
	}
	
	/**
	 * 设置商品编号
	 * @param 商品编号
	 */
	public void setGoodsId(int value)
	{
		goodsId = value;
	}
	

	private wh.shop.model.GoodsCategory goodsCategory;
	/**
	 * 获取商品分类
	 * @return 商品分类
	 */
	public wh.shop.model.GoodsCategory getGoodsCategory(){
		return this.goodsCategory;
	}
	
	/**
	 * 设置商品分类
	 * @param goodsCategory 商品分类
	 */
	public void setGoodsCategory(wh.shop.model.GoodsCategory goodsCategory){
		this.goodsCategory = goodsCategory;
	}
	
	private int goodsCategoryId;
	/**
	 * 获取商品分类编号
	 * @return 商品分类编号
	 */
	public int getGoodsCategoryId(){
		if(goodsCategory != null){
			return goodsCategory.getGoodsCategoryId();
		}else{
			return goodsCategoryId;
		}
	}
	
	/**
	 * 设置商品分类编号
	 * @param 商品分类编号
	 */
	public void setGoodsCategoryId(int value){
		if(goodsCategory != null){
			goodsCategory.setGoodsCategoryId(value);
		}else{
			goodsCategoryId = value;
		}
	}

	/**
	 * 获取商品分类名称
	 * @return 商品分类名称
	 */
	public String getGoodsCategoryName(){
		if(goodsCategory != null){
			return goodsCategory.getName();
		}else{
			return "";
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
	

	private wh.shop.model.Product product;
	/**
	 * 获取产品
	 * @return 产品
	 */
	public wh.shop.model.Product getProduct(){
		return this.product;
	}
	
	/**
	 * 设置产品
	 * @param product 产品
	 */
	public void setProduct(wh.shop.model.Product product){
		this.product = product;
	}
	
	private int productId;
	/**
	 * 获取产品编号
	 * @return 产品编号
	 */
	public int getProductId(){
		if(product != null){
			return product.getProductId();
		}else{
			return productId;
		}
	}
	
	/**
	 * 设置产品编号
	 * @param 产品编号
	 */
	public void setProductId(int value){
		if(product != null){
			product.setProductId(value);
		}else{
			productId = value;
		}
	}

	/**
	 * 获取产品名称
	 * @return 产品名称
	 */
	public String getProductName(){
		if(product != null){
			return product.getName();
		}else{
			return "";
		}
	
	}	
	
	private int categoryParentId;
	/** 
	 * 获取类型父编号
	 * @return 类型父编号
	 */
	public int getCategoryParentId()
	{
		return categoryParentId;
	}
	
	/**
	 * 设置类型父编号
	 * @param 类型父编号
	 */
	public void setCategoryParentId(int value)
	{
		categoryParentId = value;
	}
	
	
	private String categoryParentIdList;
	/** 
	 * 获取父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 * @return 父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 */
	public String getCategoryParentIdList()
	{
		return categoryParentIdList;
	}
	
	/**
	 * 设置父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 * @param 父编号列表，如果没有父编号，则为本身，否则为1,2,其中1为父编号，2为本身编号
	 */
	public void setCategoryParentIdList(String value)
	{
		categoryParentIdList = value;
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
	
	
	private String goodsDescription;
	/** 
	 * 获取商品描述
	 * @return 商品描述
	 */
	public String getGoodsDescription()
	{
		return goodsDescription;
	}
	
	/**
	 * 设置商品描述
	 * @param 商品描述
	 */
	public void setGoodsDescription(String value)
	{
		goodsDescription = value;
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
	
	
	private int price;
	/** 
	 * 获取价格
	 * @return 价格
	 */
	public int getPrice()
	{
		return price;
	}
	
	/**
	 * 设置价格
	 * @param 价格
	 */
	public void setPrice(int value)
	{
		price = value;
	}
	
	
	private int stockAmount;
	/** 
	 * 获取库存数量
	 * @return 库存数量
	 */
	public int getStockAmount()
	{
		return stockAmount;
	}
	
	/**
	 * 设置库存数量
	 * @param 库存数量
	 */
	public void setStockAmount(int value)
	{
		stockAmount = value;
	}
	
	
	private int soldAmount;
	/** 
	 * 获取售出数量
	 * @return 售出数量
	 */
	public int getSoldAmount()
	{
		return soldAmount;
	}
	
	/**
	 * 设置售出数量
	 * @param 售出数量
	 */
	public void setSoldAmount(int value)
	{
		soldAmount = value;
	}
	
	
	private int clickAmount;
	/** 
	 * 获取点击次数
	 * @return 点击次数
	 */
	public int getClickAmount()
	{
		return clickAmount;
	}
	
	/**
	 * 设置点击次数
	 * @param 点击次数
	 */
	public void setClickAmount(int value)
	{
		clickAmount = value;
	}
	
	
	private int shareAmount;
	/** 
	 * 获取分享次数
	 * @return 分享次数
	 */
	public int getShareAmount()
	{
		return shareAmount;
	}
	
	/**
	 * 设置分享次数
	 * @param 分享次数
	 */
	public void setShareAmount(int value)
	{
		shareAmount = value;
	}
	
	
	private String color;
	/** 
	 * 获取颜色
	 * @return 颜色
	 */
	public String getColor()
	{
		return color;
	}
	
	/**
	 * 设置颜色
	 * @param 颜色
	 */
	public void setColor(String value)
	{
		color = value;
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
	
	
	private int returnFortune;
	/** 
	 * 获取返还积分
	 * @return 返还积分
	 */
	public int getReturnFortune()
	{
		return returnFortune;
	}
	
	/**
	 * 设置返还积分
	 * @param 返还积分
	 */
	public void setReturnFortune(int value)
	{
		returnFortune = value;
	}
	
	
	private int returnPlat;
	/** 
	 * 获取返还平台币
	 * @return 返还平台币
	 */
	public int getReturnPlat()
	{
		return returnPlat;
	}
	
	/**
	 * 设置返还平台币
	 * @param 返还平台币
	 */
	public void setReturnPlat(int value)
	{
		returnPlat = value;
	}
	

	private byte activeType;
	/**
	 * 获取活动类型：0、活动类型；1、热门商品；2、最新上架；4、促销；
	 * @return 活动类型：0、活动类型；1、热门商品；2、最新上架；4、促销；
	 */
	public byte getActiveType(){
		return activeType;
	}
	
	/**
	 * 设置活动类型：0、活动类型；1、热门商品；2、最新上架；4、促销；
	 * @param 活动类型：0、活动类型；1、热门商品；2、最新上架；4、促销；
	 */
	public void setActiveType(byte value){
		activeType = value;
	}	
	
	private int activePlatPrice;
	/** 
	 * 获取活动平台价格
	 * @return 活动平台价格
	 */
	public int getActivePlatPrice()
	{
		return activePlatPrice;
	}
	
	/**
	 * 设置活动平台价格
	 * @param 活动平台价格
	 */
	public void setActivePlatPrice(int value)
	{
		activePlatPrice = value;
	}
	
	
	private int activeFortunePrice;
	/** 
	 * 获取活动积分价格
	 * @return 活动积分价格
	 */
	public int getActiveFortunePrice()
	{
		return activeFortunePrice;
	}
	
	/**
	 * 设置活动积分价格
	 * @param 活动积分价格
	 */
	public void setActiveFortunePrice(int value)
	{
		activeFortunePrice = value;
	}
	

	private Date activeStartDate;
	/**
	 * 获取活动开始日期
	 * @return 活动开始日期
	 */
	public Date getActiveStartDate()
	{   
		if(activeStartDate == null){
			return Utility.getDbMinDate();
		}
		return activeStartDate;
	}
	
	/**
	 * 设置活动开始日期
	 * @param 活动开始日期
	 */
	public void setActiveStartDate(Date value)
	{   
		activeStartDate = value;
	}
	
	private Date activeStartDateBegin;
	/**
	 * 获取活动开始日期
	 * @return 活动开始日期
	 */
	public Date getActiveStartDateBegin()
	{   
		if(activeStartDateBegin == null){
			return Utility.getDbMinDate();
		}
		return activeStartDateBegin;
	}
	
	/**
	 * 设置活动开始日期
	 * @param 活动开始日期
	 */
	public void setActiveStartDateBegin(Date value)
	{   
		activeStartDateBegin = value;
	}
	
	private Date activeStartDateEnd;
	/**
	 * 获取活动开始日期
	 * @return 活动开始日期
	 */
	public Date getActiveStartDateEnd()
	{   
		if(activeStartDateEnd == null){
			return Utility.getDbMinDate();
		}
		return activeStartDateEnd;
	}
	
	/**
	 * 设置活动开始日期
	 * @param 活动开始日期
	 */
	public void setActiveStartDateEnd(Date value)
	{   
		activeStartDateEnd = value;
	}
	
	
	
	private int activeStartDateDay;
	/** 
	 * 获取活动开始日期天
	 * @return 活动开始日期天
	 */
	public int getActiveStartDateDay()
	{
		return activeStartDateDay;
	}
	
	/**
	 * 设置活动开始日期天
	 * @param 活动开始日期天
	 */
	public void setActiveStartDateDay(int value)
	{
		activeStartDateDay = value;
	}
	

	private Date activeEndDate;
	/**
	 * 获取活动结束日期
	 * @return 活动结束日期
	 */
	public Date getActiveEndDate()
	{   
		if(activeEndDate == null){
			return Utility.getDbMinDate();
		}
		return activeEndDate;
	}
	
	/**
	 * 设置活动结束日期
	 * @param 活动结束日期
	 */
	public void setActiveEndDate(Date value)
	{   
		activeEndDate = value;
	}
	
	private Date activeEndDateBegin;
	/**
	 * 获取活动结束日期
	 * @return 活动结束日期
	 */
	public Date getActiveEndDateBegin()
	{   
		if(activeEndDateBegin == null){
			return Utility.getDbMinDate();
		}
		return activeEndDateBegin;
	}
	
	/**
	 * 设置活动结束日期
	 * @param 活动结束日期
	 */
	public void setActiveEndDateBegin(Date value)
	{   
		activeEndDateBegin = value;
	}
	
	private Date activeEndDateEnd;
	/**
	 * 获取活动结束日期
	 * @return 活动结束日期
	 */
	public Date getActiveEndDateEnd()
	{   
		if(activeEndDateEnd == null){
			return Utility.getDbMinDate();
		}
		return activeEndDateEnd;
	}
	
	/**
	 * 设置活动结束日期
	 * @param 活动结束日期
	 */
	public void setActiveEndDateEnd(Date value)
	{   
		activeEndDateEnd = value;
	}
	
	
	
	private int activeEndDateDay;
	/** 
	 * 获取活动结束日期天
	 * @return 活动结束日期天
	 */
	public int getActiveEndDateDay()
	{
		return activeEndDateDay;
	}
	
	/**
	 * 设置活动结束日期天
	 * @param 活动结束日期天
	 */
	public void setActiveEndDateDay(int value)
	{
		activeEndDateDay = value;
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
	

	private byte recommendType;
	/**
	 * 获取是否推荐：0、推荐类型；1、推荐；2、不推荐；
	 * @return 是否推荐：0、推荐类型；1、推荐；2、不推荐；
	 */
	public byte getRecommendType(){
		return recommendType;
	}
	
	/**
	 * 设置是否推荐：0、推荐类型；1、推荐；2、不推荐；
	 * @param 是否推荐：0、推荐类型；1、推荐；2、不推荐；
	 */
	public void setRecommendType(byte value){
		recommendType = value;
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


