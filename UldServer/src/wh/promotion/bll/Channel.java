package wh.promotion.bll;

import java.util.ArrayList;
import java.util.List;
import uld.sdk.model.MessageReturn;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;

/**
 * Channel业务逻辑层
 */
public class Channel
{
	private static Channel instance = new Channel();
	public static Channel getInstance() {
		return instance;
	}
	private static wh.promotion.dal.Channel dal = new wh.promotion.dal.Channel();

	/**
	 * 创建更新渠道
	 * 
	 * @param model 
	 *        渠道
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 渠道编号
	 */
	public int createUpdate(wh.promotion.model.Channel model, RefObject<MyErr> refMyErr) {
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
	 * 创建更新渠道
	 * 
	 * @param model 
	 *        渠道
	 * @return 消息对象
	 */
	public MessageReturn createUpdate(wh.promotion.model.Channel model) {
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
	 * 获取渠道
	 * 
	 * @param id 
	 *        渠道编号
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return wh.promotion.model.Channel
     */
	public wh.promotion.model.Channel get(int id, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.promotion.model.Channel model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取渠道
	 * 
	 * @param id 
	 *        渠道编号
	 * @return 消息对象
     */
	public MessageReturn get(int id) {
		MessageReturn messageReturn = new MessageReturn();
		wh.promotion.model.Channel model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 获取渠道列表
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
	 * @return List<wh.promotion.model.Channel>
     */
	public List<wh.promotion.model.Channel> getList(wh.promotion.model.Channel queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType, RefObject<MyErr> refMyErr){
		MyErr myErr = new MyErr();
		List<wh.promotion.model.Channel> list = new ArrayList<wh.promotion.model.Channel>();
		try {
			list = dal.getList(queryModel, totalCount, isAll, pageIndex, pageSize, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}
	
	/**
	 * 获取渠道列表
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
	public MessageReturn getList(wh.promotion.model.Channel queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType){
		MessageReturn messageReturn = new MessageReturn();
		List<wh.promotion.model.Channel> list = new ArrayList<wh.promotion.model.Channel>();
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
	 * 删除渠道
	 * 
	 * @param id 
	 *        渠道编号
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
	 * 删除渠道
	 * 
	 * @param id 
	 *        渠道编号
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
	 * 获取渠道根据渠道名称
	 * 
	 * @param ChannelName 
	 *        渠道名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 渠道
	 */
	public wh.promotion.model.Channel getByName(String channelName, String fldSort, int sortType, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.promotion.model.Channel model = null;
		try {
			model = dal.getByName(channelName, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取渠道根据渠道名称
	 * 
	 * @param ChannelName 
	 *        渠道名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @return 消息对象
	 */
	public MessageReturn getByName(String channelName, String fldSort, int sortType) {
		MessageReturn messageReturn = new MessageReturn();
		wh.promotion.model.Channel model = null;
		try {
			model = dal.getByName(channelName, fldSort, sortType);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 获取渠道列表根据渠道名称
	 * 
	 * @param ChannelName 
	 *        渠道名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 渠道列表
	 */
	public List<wh.promotion.model.Channel> getListByName(String channelName, String fldSort, int sortType, int queryCount, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		List<wh.promotion.model.Channel> list = new ArrayList<wh.promotion.model.Channel>();
		try {
			list = dal.getListByName(channelName, fldSort, sortType, queryCount);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}
	
	/**
	 * 获取渠道列表根据渠道名称
	 * 
	 * @param ChannelName 
	 *        渠道名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @return 消息对象
	 */
	public MessageReturn getListByName(String channelName, String fldSort, int sortType, int queryCount) throws Exception{
		MessageReturn messageReturn = new MessageReturn();
		List<wh.promotion.model.Channel> list = new ArrayList<wh.promotion.model.Channel>();
		try {
			list = dal.getListByName(channelName, fldSort, sortType, queryCount);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(list);
		return messageReturn;
	}
}

