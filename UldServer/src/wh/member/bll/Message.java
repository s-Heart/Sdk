﻿package wh.member.bll;

import java.util.ArrayList;
import java.util.List;
import uld.sdk.model.MessageReturn;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;

/**
 * Message业务逻辑层
 */
public class Message
{
	private static Message instance = new Message();
	public static Message getInstance() {
		return instance;
	}
	private static wh.member.dal.Message dal = new wh.member.dal.Message();

	/**
	 * 创建更新消息
	 * 
	 * @param model 
	 *        消息
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 消息编号
	 */
	public int createUpdate(wh.member.model.Message model, RefObject<MyErr> refMyErr) {
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
	 * 创建更新消息
	 * 
	 * @param model 
	 *        消息
	 * @return 消息对象
	 */
	public MessageReturn createUpdate(wh.member.model.Message model) {
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
	 * 获取消息
	 * 
	 * @param id 
	 *        消息编号
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return wh.member.model.Message
     */
	public wh.member.model.Message get(int id, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.member.model.Message model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取消息
	 * 
	 * @param id 
	 *        消息编号
	 * @return 消息对象
     */
	public MessageReturn get(int id) {
		MessageReturn messageReturn = new MessageReturn();
		wh.member.model.Message model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 获取消息列表
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
	 * @return List<wh.member.model.Message>
     */
	public List<wh.member.model.Message> getList(wh.member.model.Message queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType, RefObject<MyErr> refMyErr){
		MyErr myErr = new MyErr();
		List<wh.member.model.Message> list = new ArrayList<wh.member.model.Message>();
		try {
			list = dal.getList(queryModel, totalCount, isAll, pageIndex, pageSize, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}
	
	/**
	 * 获取消息列表
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
	public MessageReturn getList(wh.member.model.Message queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType){
		MessageReturn messageReturn = new MessageReturn();
		List<wh.member.model.Message> list = new ArrayList<wh.member.model.Message>();
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
	 * 删除消息
	 * 
	 * @param id 
	 *        消息编号
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
	 * 删除消息
	 * 
	 * @param id 
	 *        消息编号
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

