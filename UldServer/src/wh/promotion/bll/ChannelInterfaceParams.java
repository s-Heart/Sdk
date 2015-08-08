﻿package wh.promotion.bll;

import java.util.ArrayList;
import java.util.List;
import uld.sdk.model.MessageReturn;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;

/**
 * ChannelInterfaceParams业务逻辑层
 */
public class ChannelInterfaceParams
{
	private static ChannelInterfaceParams instance = new ChannelInterfaceParams();
	public static ChannelInterfaceParams getInstance() {
		return instance;
	}
	private static wh.promotion.dal.ChannelInterfaceParams dal = new wh.promotion.dal.ChannelInterfaceParams();

	/**
	 * 创建更新渠道接口参数
	 * 
	 * @param model 
	 *        渠道接口参数
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 渠道接口参数编号
	 */
	public int createUpdate(wh.promotion.model.ChannelInterfaceParams model, RefObject<MyErr> refMyErr) {
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
	 * 创建更新渠道接口参数
	 * 
	 * @param model 
	 *        渠道接口参数
	 * @return 消息对象
	 */
	public MessageReturn createUpdate(wh.promotion.model.ChannelInterfaceParams model) {
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
	 * 获取渠道接口参数
	 * 
	 * @param id 
	 *        渠道接口参数编号
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return wh.promotion.model.ChannelInterfaceParams
     */
	public wh.promotion.model.ChannelInterfaceParams get(int id, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.promotion.model.ChannelInterfaceParams model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取渠道接口参数
	 * 
	 * @param id 
	 *        渠道接口参数编号
	 * @return 消息对象
     */
	public MessageReturn get(int id) {
		MessageReturn messageReturn = new MessageReturn();
		wh.promotion.model.ChannelInterfaceParams model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 获取渠道接口参数列表
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
	 * @return List<wh.promotion.model.ChannelInterfaceParams>
     */
	public List<wh.promotion.model.ChannelInterfaceParams> getList(wh.promotion.model.ChannelInterfaceParams queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType, RefObject<MyErr> refMyErr){
		MyErr myErr = new MyErr();
		List<wh.promotion.model.ChannelInterfaceParams> list = new ArrayList<wh.promotion.model.ChannelInterfaceParams>();
		try {
			list = dal.getList(queryModel, totalCount, isAll, pageIndex, pageSize, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}
	
	/**
	 * 获取渠道接口参数列表
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
	public MessageReturn getList(wh.promotion.model.ChannelInterfaceParams queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType){
		MessageReturn messageReturn = new MessageReturn();
		List<wh.promotion.model.ChannelInterfaceParams> list = new ArrayList<wh.promotion.model.ChannelInterfaceParams>();
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
	 * 删除渠道接口参数
	 * 
	 * @param id 
	 *        渠道接口参数编号
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
	 * 删除渠道接口参数
	 * 
	 * @param id 
	 *        渠道接口参数编号
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

