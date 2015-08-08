﻿package wh.content.bll;

import java.util.ArrayList;
import java.util.List;
import uld.sdk.model.MessageReturn;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;

/**
 * Category业务逻辑层
 */
public class Category
{
	private static Category instance = new Category();
	public static Category getInstance() {
		return instance;
	}
	private static wh.content.dal.Category dal = new wh.content.dal.Category();

	/**
	 * 创建更新类别表
	 * 
	 * @param model 
	 *        类别表
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 类别表编号
	 */
	public int createUpdate(wh.content.model.Category model, RefObject<MyErr> refMyErr) {
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
	 * 创建更新类别表
	 * 
	 * @param model 
	 *        类别表
	 * @return 消息对象
	 */
	public MessageReturn createUpdate(wh.content.model.Category model) {
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
	 * 获取类别表
	 * 
	 * @param id 
	 *        类别表编号
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return wh.content.model.Category
     */
	public wh.content.model.Category get(int id, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.content.model.Category model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取类别表
	 * 
	 * @param id 
	 *        类别表编号
	 * @return 消息对象
     */
	public MessageReturn get(int id) {
		MessageReturn messageReturn = new MessageReturn();
		wh.content.model.Category model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 获取类别表列表
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
	 * @return List<wh.content.model.Category>
     */
	public List<wh.content.model.Category> getList(wh.content.model.Category queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType, RefObject<MyErr> refMyErr){
		MyErr myErr = new MyErr();
		List<wh.content.model.Category> list = new ArrayList<wh.content.model.Category>();
		try {
			list = dal.getList(queryModel, totalCount, isAll, pageIndex, pageSize, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}
	
	/**
	 * 获取类别表列表
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
	public MessageReturn getList(wh.content.model.Category queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType){
		MessageReturn messageReturn = new MessageReturn();
		List<wh.content.model.Category> list = new ArrayList<wh.content.model.Category>();
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
	 * 删除类别表
	 * 
	 * @param id 
	 *        类别表编号
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
	 * 删除类别表
	 * 
	 * @param id 
	 *        类别表编号
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
	
	/**
	 * 获取类别表根据类别表名称
	 * 
	 * @param CategoryName 
	 *        类别表名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 类别表
	 */
	public wh.content.model.Category getByName(String categoryName, String fldSort, int sortType, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.content.model.Category model = null;
		try {
			model = dal.getByName(categoryName, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取类别表根据类别表名称
	 * 
	 * @param CategoryName 
	 *        类别表名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @return 消息对象
	 */
	public MessageReturn getByName(String categoryName, String fldSort, int sortType) {
		MessageReturn messageReturn = new MessageReturn();
		wh.content.model.Category model = null;
		try {
			model = dal.getByName(categoryName, fldSort, sortType);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 获取类别表列表根据类别表名称
	 * 
	 * @param CategoryName 
	 *        类别表名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 类别表列表
	 */
	public List<wh.content.model.Category> getListByName(String categoryName, String fldSort, int sortType, int queryCount, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		List<wh.content.model.Category> list = new ArrayList<wh.content.model.Category>();
		try {
			list = dal.getListByName(categoryName, fldSort, sortType, queryCount);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}
	
	/**
	 * 获取类别表列表根据类别表名称
	 * 
	 * @param CategoryName 
	 *        类别表名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @return 消息对象
	 */
	public MessageReturn getListByName(String categoryName, String fldSort, int sortType, int queryCount) throws Exception{
		MessageReturn messageReturn = new MessageReturn();
		List<wh.content.model.Category> list = new ArrayList<wh.content.model.Category>();
		try {
			list = dal.getListByName(categoryName, fldSort, sortType, queryCount);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(list);
		return messageReturn;
	}
}

