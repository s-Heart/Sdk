﻿package wh.shop.bll;

import java.util.ArrayList;
import java.util.List;
import uld.sdk.model.MessageReturn;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;

/**
 * OrderAddress业务逻辑层
 */
public class OrderAddress
{
	private static OrderAddress instance = new OrderAddress();
	public static OrderAddress getInstance() {
		return instance;
	}
	private static wh.shop.dal.OrderAddress dal = new wh.shop.dal.OrderAddress();

	/**
	 * 创建更新商品订单地址
	 * 
	 * @param model 
	 *        商品订单地址
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 商品订单地址编号
	 */
	public int createUpdate(wh.shop.model.OrderAddress model, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		int retValue = 0;
		try {
			retValue = dal.createUpdate(model);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return retValue;
	}
	
	/**
	 * 创建更新商品订单地址
	 * 
	 * @param model 
	 *        商品订单地址
	 * @return 消息对象
	 */
	public MessageReturn createUpdate(wh.shop.model.OrderAddress model) {
		MessageReturn messageReturn = new MessageReturn();
		int retValue = 0;
		try {
			retValue = dal.createUpdate(model);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(retValue);
		return messageReturn;
	}
	
	/**
	 * 获取商品订单地址
	 * 
	 * @param id 
	 *        商品订单地址编号
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return wh.shop.model.OrderAddress
     */
	public wh.shop.model.OrderAddress get(int id, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.shop.model.OrderAddress model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取商品订单地址
	 * 
	 * @param id 
	 *        商品订单地址编号
	 * @return 消息对象
     */
	public MessageReturn get(int id) {
		MessageReturn messageReturn = new MessageReturn();
		wh.shop.model.OrderAddress model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 获取商品订单地址列表
	 * 
	 * @param queryModel 
	 *        查询用户实体
	 * @param totalCount 
	 *        返回总数量
	 * @param isAll 
	 *        是否获取所有数据，false、否；true、是；
	 * @param pageIndex 
	 *        页码，从1开始(>=1)
	 * @param pageSize 
	 *        每页显示数量
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return List<wh.shop.model.OrderAddress>
     */
	public List<wh.shop.model.OrderAddress> getList(wh.shop.model.OrderAddress queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType, RefObject<MyErr> refMyErr){
		MyErr myErr = new MyErr();
		List<wh.shop.model.OrderAddress> list = new ArrayList<wh.shop.model.OrderAddress>();
		try {
			list = dal.getList(queryModel, totalCount, isAll, pageIndex, pageSize, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}
	
	/**
	 * 获取商品订单地址列表
	 * 
	 * @param queryModel 
	 *        查询用户实体
	 * @param totalCount 
	 *        返回总数量
	 * @param isAll 
	 *        是否获取所有数据，false、否；true、是；
	 * @param pageIndex 
	 *        页码，从1开始(>=1)
	 * @param pageSize 
	 *        每页显示数量
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @return 消息对象
     */
	public MessageReturn getList(wh.shop.model.OrderAddress queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType){
		MessageReturn messageReturn = new MessageReturn();
		List<wh.shop.model.OrderAddress> list = new ArrayList<wh.shop.model.OrderAddress>();
		try {
			list = dal.getList(queryModel, totalCount, isAll, pageIndex, pageSize, fldSort, sortType);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setTotalCount(totalCount.argvalue);
		messageReturn.setRetObject(list);
		return messageReturn;
	}
	
	
	/**
	 * 删除商品订单地址
	 * 
	 * @param id 
	 *        商品订单地址编号
	 * @return boolean
	 */
	public boolean remove(int id, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		boolean retValue = true;
		try {
			dal.remove(id);
		} catch (Exception e) {
			retValue = false;
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return retValue;
	}
	
	/**
	 * 删除商品订单地址
	 * 
	 * @param id 
	 *        商品订单地址编号
	 * @return 消息对象
	 */
	public MessageReturn remove(int id) {
		MessageReturn messageReturn = new MessageReturn();
		boolean retValue = true;
		try {
			dal.remove(id);
		} catch (Exception e) {
			retValue = false;
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(retValue);
		return messageReturn;
	}
	
}

