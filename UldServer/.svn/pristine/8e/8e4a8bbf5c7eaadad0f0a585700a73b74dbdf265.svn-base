package wh.game.bll;

import java.util.ArrayList;
import java.util.List;
import uld.sdk.model.MessageReturn;
import uld.sdk.tools.MyErr;
import uld.sdk.tools.RefObject;

/**
 * Game业务逻辑层
 */
public class Game
{
	private static Game instance = new Game();
	public static Game getInstance() {
		return instance;
	}
	private static wh.game.dal.Game dal = new wh.game.dal.Game();

	/**
	 * 创建更新游戏
	 * 
	 * @param model 
	 *        游戏
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 游戏编号
	 */
	public int createUpdate(wh.game.model.Game model, RefObject<MyErr> refMyErr) {
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
	 * 创建更新游戏
	 * 
	 * @param model 
	 *        游戏
	 * @return 消息对象
	 */
	public MessageReturn createUpdate(wh.game.model.Game model) {
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
	 * 获取游戏
	 * 
	 * @param id 
	 *        游戏编号
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return wh.game.model.Game
     */
	public wh.game.model.Game get(int id, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.game.model.Game model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取游戏
	 * 
	 * @param id 
	 *        游戏编号
	 * @return 消息对象
     */
	public MessageReturn get(int id) {
		MessageReturn messageReturn = new MessageReturn();
		wh.game.model.Game model = null;
		try {
			model = dal.get(id);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 获取游戏列表
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
	 * @return List<wh.game.model.Game>
     */
	public List<wh.game.model.Game> getList(wh.game.model.Game queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType, RefObject<MyErr> refMyErr){
		MyErr myErr = new MyErr();
		List<wh.game.model.Game> list = new ArrayList<wh.game.model.Game>();
		try {
			list = dal.getList(queryModel, totalCount, isAll, pageIndex, pageSize, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}
	
	/**
	 * 获取游戏列表
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
	public MessageReturn getList(wh.game.model.Game queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType){
		MessageReturn messageReturn = new MessageReturn();
		List<wh.game.model.Game> list = new ArrayList<wh.game.model.Game>();
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
	 * 删除游戏
	 * 
	 * @param id 
	 *        游戏编号
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
	 * 删除游戏
	 * 
	 * @param id 
	 *        游戏编号
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
	 * 获取游戏根据游戏名称
	 * 
	 * @param GameName 
	 *        游戏名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 游戏
	 */
	public wh.game.model.Game getByName(String gameName, String fldSort, int sortType, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		wh.game.model.Game model = null;
		try {
			model = dal.getByName(gameName, fldSort, sortType);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return model;
	}
	
	/**
	 * 获取游戏根据游戏名称
	 * 
	 * @param GameName 
	 *        游戏名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @return 消息对象
	 */
	public MessageReturn getByName(String gameName, String fldSort, int sortType) {
		MessageReturn messageReturn = new MessageReturn();
		wh.game.model.Game model = null;
		try {
			model = dal.getByName(gameName, fldSort, sortType);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(model);
		return messageReturn;
	}
	
	/**
	 * 获取游戏列表根据游戏名称
	 * 
	 * @param GameName 
	 *        游戏名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @param refMyErr 
	 *        错误传递对象，传入时不可为空
	 * @return 游戏列表
	 */
	public List<wh.game.model.Game> getListByName(String gameName, String fldSort, int sortType, int queryCount, RefObject<MyErr> refMyErr) {
		MyErr myErr = new MyErr();
		List<wh.game.model.Game> list = new ArrayList<wh.game.model.Game>();
		try {
			list = dal.getListByName(gameName, fldSort, sortType, queryCount);
		} catch (Exception e) {
			myErr.setErr(-1, e.getMessage());
		}
		refMyErr.argvalue = myErr;
		return list;
	}
	
	/**
	 * 获取游戏列表根据游戏名称
	 * 
	 * @param GameName 
	 *        游戏名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @return 消息对象
	 */
	public MessageReturn getListByName(String gameName, String fldSort, int sortType, int queryCount) throws Exception{
		MessageReturn messageReturn = new MessageReturn();
		List<wh.game.model.Game> list = new ArrayList<wh.game.model.Game>();
		try {
			list = dal.getListByName(gameName, fldSort, sortType, queryCount);
		} catch (Exception e) {
			messageReturn.setErr(-1, e.getMessage());
		}
		messageReturn.setRetObject(list);
		return messageReturn;
	}
}

