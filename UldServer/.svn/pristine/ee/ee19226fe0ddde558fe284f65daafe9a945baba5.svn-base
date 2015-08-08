package wh.member.bll;

import java.util.ArrayList;
import java.util.List;
import uld.sdk.model.MessageReturn;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;

/**
 * Manager业务逻辑层
 */
public class Manager
{
	private static Manager instance = new Manager();
	public static Manager getInstance() {
		return instance;
	}
	private static wh.member.dal.Manager dal = new wh.member.dal.Manager();

	/**
	 * 创建更新管理员
	 * 
	 * @param model 
	 *        管理员
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 管理员编号
	 */
	public int createUpdate(wh.member.model.Manager model, RefObject<MyErr> refMyErr) {
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
	 * 创建更新管理员
	 * 
	 * @param model 
	 *        管理员
	 * @return 消息对象
	 */
	public MessageReturn createUpdate(wh.member.model.Manager model) {
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
	 * 获取管理员
	 * 
	 * @param id 
	 *        管理员编号
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return wh.member.model.Manager
     */
	public wh.member.model.Manager get(int id, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.member.model.Manager model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取管理员
	 * 
	 * @param id 
	 *        管理员编号
	 * @return 消息对象
     */
	public MessageReturn get(int id) {
		MessageReturn messageReturn = new MessageReturn();
		wh.member.model.Manager model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 获取管理员列表
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
	 * @return List<wh.member.model.Manager>
     */
	public List<wh.member.model.Manager> getList(wh.member.model.Manager queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType, RefObject<MyErr> refMyErr){
		MyErr myErr = new MyErr();
		List<wh.member.model.Manager> list = new ArrayList<wh.member.model.Manager>();
		try {
			list = dal.getList(queryModel, totalCount, isAll, pageIndex, pageSize, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}
	
	/**
	 * 获取管理员列表
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
	public MessageReturn getList(wh.member.model.Manager queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType){
		MessageReturn messageReturn = new MessageReturn();
		List<wh.member.model.Manager> list = new ArrayList<wh.member.model.Manager>();
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
	 * 检查用户名称是否存在
	 * 
	 * @param userName 
	 *        用户名称
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return
	 */
	public boolean checkUserName(String userName, RefObject<MyErr> refMyErr){
		MyErr myErr = new MyErr();
		boolean retValue = true;
		try {
			dal.checkUserName(userName);
		} catch (Exception e) {
			retValue = false;
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return retValue;
	}
	
	/**
	 * 检查用户名称是否存在
	 * 
	 * @param userName 
	 *        用户名称
	 * @return 消息对象
	 */
	public MessageReturn checkUserName(String userName){
		MessageReturn messageReturn = new MessageReturn();
		boolean retValue = true;
		try {
			dal.checkUserName(userName);
		} catch (Exception e) {
			retValue = false;
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(retValue);
		return messageReturn;
	}

	/**
	 * 获取管理员
	 * 
	 * @param userName 
	 *        管理员名称
	 * @param password 
	 *        密码
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return wh.member.model.Manager
	 */
	public wh.member.model.Manager login(String userName, String password, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.member.model.Manager model = null;
		try {
			model = dal.login(userName, password);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取管理员
	 * 
	 * @param userName 
	 *        管理员名称
	 * @param password 
	 *        密码
	 * @return 消息对象
	 */
	public MessageReturn login(String userName, String password) {
		MessageReturn messageReturn = new MessageReturn();
		wh.member.model.Manager model = null;
		try {
			model = dal.login(userName, password);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 修改密码
	 * 
	 * @param userName
	 *        用户名称
	 * @param oldPassword
	 *        旧密码
	 * @param newPassword
	 *        新密码
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return boolean
	 */
	public boolean modifyPassword(String userName, String oldPassword, String newPassword, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		boolean retValue = true;
		try {
			dal.modifyPassword(userName, oldPassword, newPassword);
		} catch (Exception e) {
			retValue = false;
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return retValue;
	}
	
	/**
	 * 修改密码
	 * 
	 * @param userName
	 *        用户名称
	 * @param oldPassword
	 *        旧密码
	 * @param newPassword
	 *        新密码
	 * @return 消息对象
	 */
	public MessageReturn modifyPassword(String userName, String oldPassword, String newPassword) {
		MessageReturn messageReturn = new MessageReturn();
		boolean retValue = true;
		try {
			dal.modifyPassword(userName, oldPassword, newPassword);
		} catch (Exception e) {
			retValue = false;
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(retValue);
		return messageReturn;
	}
	
	/**
	 * 删除管理员
	 * 
	 * @param id 
	 *        管理员编号
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
	 * 删除管理员
	 * 
	 * @param id 
	 *        管理员编号
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

