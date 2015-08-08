package wh.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import uld.sdk.tools.Utility;


public class GoodsComment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 无参构造函数
	 */
	public GoodsComment(){
		 this.status = (byte)0;
	}
		
	private int goodsCommentId;
	/** 
	 * 获取评论编号
	 * @return 评论编号
	 */
	public int getGoodsCommentId()
	{
		return goodsCommentId;
	}
	
	/**
	 * 设置评论编号
	 * @param 评论编号
	 */
	public void setGoodsCommentId(int value)
	{
		goodsCommentId = value;
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
	

	private wh.shop.model.Goods goods;
	/**
	 * 获取商品
	 * @return 商品
	 */
	public wh.shop.model.Goods getGoods(){
		return this.goods;
	}
	
	/**
	 * 设置商品
	 * @param goods 商品
	 */
	public void setGoods(wh.shop.model.Goods goods){
		this.goods = goods;
	}
	
	private int goodsId;
	/**
	 * 获取商品编号
	 * @return 商品编号
	 */
	public int getGoodsId(){
		if(goods != null){
			return goods.getGoodsId();
		}else{
			return goodsId;
		}
	}
	
	/**
	 * 设置商品编号
	 * @param 商品编号
	 */
	public void setGoodsId(int value){
		if(goods != null){
			goods.setGoodsId(value);
		}else{
			goodsId = value;
		}
	}

	/**
	 * 获取商品名称
	 * @return 商品名称
	 */
	public String getGoodsName(){
		if(goods != null){
			return goods.getName();
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
	
	
	private String content;
	/** 
	 * 获取描述
	 * @return 描述
	 */
	public String getContent()
	{
		return content;
	}
	
	/**
	 * 设置描述
	 * @param 描述
	 */
	public void setContent(String value)
	{
		content = value;
	}
	
	
	private int score;
	/** 
	 * 获取分数
	 * @return 分数
	 */
	public int getScore()
	{
		return score;
	}
	
	/**
	 * 设置分数
	 * @param 分数
	 */
	public void setScore(int value)
	{
		score = value;
	}
	

	private int starType;
	/**
	 * 获取星级数量：0、星级数量；1、一个星；2、一个半星；4、二个星；8、二个半星；16、三个星；32、三个半星；64、四个星；128、四个半星；256、五个星；
	 * @return 星级数量：0、星级数量；1、一个星；2、一个半星；4、二个星；8、二个半星；16、三个星；32、三个半星；64、四个星；128、四个半星；256、五个星；
	 */
	public int getStarType(){
		return starType;
	}
	
	/**
	 * 设置星级数量：0、星级数量；1、一个星；2、一个半星；4、二个星；8、二个半星；16、三个星；32、三个半星；64、四个星；128、四个半星；256、五个星；
	 * @param 星级数量：0、星级数量；1、一个星；2、一个半星；4、二个星；8、二个半星；16、三个星；32、三个半星；64、四个星；128、四个半星；256、五个星；
	 */
	public void setStarType(int value){
		starType = value;
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
	
	

	private byte commentType;
	/**
	 * 获取评论类型：0、评论类型；1、好评；2、差评；3、其他；
	 * @return 评论类型：0、评论类型；1、好评；2、差评；3、其他；
	 */
	public byte getCommentType(){
		return commentType;
	}
	
	/**
	 * 设置评论类型：0、评论类型；1、好评；2、差评；3、其他；
	 * @param 评论类型：0、评论类型；1、好评；2、差评；3、其他；
	 */
	public void setCommentType(byte value){
		commentType = value;
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


