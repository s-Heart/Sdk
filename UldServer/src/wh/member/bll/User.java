package wh.member.bll;

import java.util.ArrayList;
import java.util.List;
import uld.sdk.model.MessageReturn;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;

/**
 * User业务逻辑层
 */
public class User {
	private static User instance = new User();

	public static User getInstance() {
		return instance;
	}

	private static wh.member.dal.User dal = new wh.member.dal.User();

	/**
	 * 创建更新用户
	 * 
	 * @param model
	 *            用户
	 * @param refMyErr
	 *            错误传递对象，传入时不可为空
	 * @return 用户编号
	 */
	public int createUpdate(wh.member.model.User model, RefObject<MyErr> refMyErr) {
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
	 * 创建更新用户
	 * 
	 * @param model
	 *            用户
	 * @return 消息对象
	 */
	public MessageReturn createUpdate(wh.member.model.User model) {
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
	 * 获取用户
	 * 
	 * @param id
	 *            用户编号
	 * @param refMyErr
	 *            错误传递对象，传入时不可为空
	 * @return wh.member.model.User
	 */
	public wh.member.model.User get(int id, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.member.model.User model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}

	/**
	 * 获取用户
	 * 
	 * @param id
	 *            用户编号
	 * @return 消息对象
	 */
	public MessageReturn get(int id) {
		MessageReturn messageReturn = new MessageReturn();
		wh.member.model.User model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}

	/**
	 * 获取用户列表
	 * 
	 * @param queryModel
	 *            查询用户实体
	 * @param totalCount
	 *            返回总数量
	 * @param isAll
	 *            是否获取所有数据，false、否；true、是；
	 * @param pageIndex
	 *            页码，从1开始(>=1)
	 * @param pageSize
	 *            每页显示数量
	 * @param fldSort
	 *            排序字段，默认""则按照主键编号排序
	 * @param sortType
	 *            排序方法，0为升序，1为降序
	 * @param refMyErr
	 *            错误传递对象，传入时不可为空
	 * @return List<wh.member.model.User>
	 */
	public List<wh.member.model.User> getList(wh.member.model.User queryModel, RefObject<Integer> totalCount, boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType,
			RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		List<wh.member.model.User> list = new ArrayList<wh.member.model.User>();
		try {
			list = dal.getList(queryModel, totalCount, isAll, pageIndex, pageSize, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}

	/**
	 * 获取用户列表
	 * 
	 * @param queryModel
	 *            查询用户实体
	 * @param totalCount
	 *            返回总数量
	 * @param isAll
	 *            是否获取所有数据，false、否；true、是；
	 * @param pageIndex
	 *            页码，从1开始(>=1)
	 * @param pageSize
	 *            每页显示数量
	 * @param fldSort
	 *            排序字段，默认""则按照主键编号排序
	 * @param sortType
	 *            排序方法，0为升序，1为降序
	 * @return 消息对象
	 */
	public MessageReturn getList(wh.member.model.User queryModel, RefObject<Integer> totalCount, boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) {
		MessageReturn messageReturn = new MessageReturn();
		List<wh.member.model.User> list = new ArrayList<wh.member.model.User>();
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
	 *            用户名称
	 * @param refMyErr
	 *            错误传递对象，传入时不可为空
	 * @return
	 */
	public boolean checkUserName(String userName, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		boolean retValue = false;
		try {
			dal.checkUserName(userName);
		} catch (Exception e) {
			retValue = true;
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return retValue;
	}

	/**
	 * 检查用户名称是否存在
	 * 
	 * @param userName
	 *            用户名称
	 * @return 消息对象
	 */
	public MessageReturn checkUserName(String userName) {
		MessageReturn messageReturn = new MessageReturn();
		boolean retValue = false;
		try {
			dal.checkUserName(userName);
		} catch (Exception e) {
			retValue = true;
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(retValue);
		return messageReturn;
	}

	/**
	 * 获取用户
	 * 
	 * @param userName
	 *            用户名称
	 * @param password
	 *            密码
	 * @param refMyErr
	 *            错误传递对象，传入时不可为空
	 * @return wh.member.model.User
	 */
	public wh.member.model.User login(String userName, String password, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.member.model.User model = null;
		try {
			model = dal.login(userName, password);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}

	/**
	 * 获取用户
	 * 
	 * @param userName
	 *            用户名称
	 * @param password
	 *            密码
	 * @return 消息对象
	 */
	public MessageReturn login(String userName, String password) {
		MessageReturn messageReturn = new MessageReturn();
		wh.member.model.User model = null;
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
	 *            用户名称
	 * @param oldPassword
	 *            旧密码
	 * @param newPassword
	 *            新密码
	 * @param refMyErr
	 *            错误传递对象，传入时不可为空
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
	 *            用户名称
	 * @param oldPassword
	 *            旧密码
	 * @param newPassword
	 *            新密码
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
	 * 删除用户
	 * 
	 * @param id
	 *            用户编号
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
	 * 删除用户
	 * 
	 * @param id
	 *            用户编号
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
	 * 获取用户根据用户名称
	 * 
	 * @param UserName
	 *            用户名称
	 * @param fldSort
	 *            排序字段，默认""则按照主键编号排序
	 * @param sortType
	 *            排序方法，0为升序，1为降序
	 * @param refMyErr
	 *            错误传递对象，传入时不可为空
	 * @return 用户
	 */
	public wh.member.model.User getByName(String userName, String fldSort, int sortType, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.member.model.User model = null;
		try {
			model = dal.getByName(userName, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}

	/**
	 * 获取用户根据用户名称
	 * 
	 * @param UserName
	 *            用户名称
	 * @param fldSort
	 *            排序字段，默认""则按照主键编号排序
	 * @param sortType
	 *            排序方法，0为升序，1为降序
	 * @return 消息对象
	 */
	public MessageReturn getByName(String userName, String fldSort, int sortType) {
		MessageReturn messageReturn = new MessageReturn();
		wh.member.model.User model = null;
		try {
			model = dal.getByName(userName, fldSort, sortType);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}

	/**
	 * 获取用户列表根据用户名称
	 * 
	 * @param UserName
	 *            用户名称
	 * @param fldSort
	 *            排序字段，默认""则按照主键编号排序
	 * @param sortType
	 *            排序方法，0为升序，1为降序
	 * @param queryCount
	 *            查询总数量
	 * @param refMyErr
	 *            错误传递对象，传入时不可为空
	 * @return 用户列表
	 */
	public List<wh.member.model.User> getListByName(String userName, String fldSort, int sortType, int queryCount, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		List<wh.member.model.User> list = new ArrayList<wh.member.model.User>();
		try {
			list = dal.getListByName(userName, fldSort, sortType, queryCount);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}

	/**
	 * 获取用户列表根据用户名称
	 * 
	 * @param UserName
	 *            用户名称
	 * @param fldSort
	 *            排序字段，默认""则按照主键编号排序
	 * @param sortType
	 *            排序方法，0为升序，1为降序
	 * @param queryCount
	 *            查询总数量
	 * @return 消息对象
	 */
	public MessageReturn getListByName(String userName, String fldSort, int sortType, int queryCount) throws Exception {
		MessageReturn messageReturn = new MessageReturn();
		List<wh.member.model.User> list = new ArrayList<wh.member.model.User>();
		try {
			list = dal.getListByName(userName, fldSort, sortType, queryCount);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(list);
		return messageReturn;
	}
}
