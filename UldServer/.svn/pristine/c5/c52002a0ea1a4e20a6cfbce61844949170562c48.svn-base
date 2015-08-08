package wh.server.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * SACategory数据访问层
 */
public class SACategory
{
	/**
	 * 创建更新服务器帐户分类
	 * 
	 * @param model 
	 *        服务器帐户分类
	 * @return 服务器帐户分类编号
	 */
	public int createUpdate(wh.server.model.SACategory model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("SACategoryId", model.getSACategoryId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("SACategoryName", model.getSACategoryName());
		inParameters.put("Level", model.getLevel());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("Status", model.getStatus());
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		int parasCount = inParameters.size() + outParameters.getOutParmTypes().size();
		StringBuilder sbBuilder = new StringBuilder();
		for (int i = 0; i < parasCount; i++) {
			if (0 == i) {
				sbBuilder.append("?");
			} else {
				sbBuilder.append(",?");
			}
		}
		
		int retValue = SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD,
			"{? = call WH_Server_SACategory_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取服务器帐户分类
	 * 
	 * @param id 
	 *        服务器帐户分类编号
	 * @return 服务器帐户分类
     */
	public wh.server.model.SACategory get(int id) throws Exception{
		wh.server.model.SACategory model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("SACategoryId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Server_SACategory_Get(?,?,?)}", inParameters, outParameters);
			if (rs != null) {
				rs.next();
				model = getModel(rs);
				
				cstmt = (CallableStatement)rs.getStatement();
				errNo = cstmt.getInt("ErrNo");
				errMsg = cstmt.getString("ErrMsg");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		} finally{
			SQLHelper.closeAll(cstmt);
		}
		
		MyErr.checkErr(errNo, errMsg);
		return model;
	}
	
	/**
	 * 获取服务器帐户分类列表
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
	 * @return 服务器帐户分类列表
     */
	public List<wh.server.model.SACategory> getList(wh.server.model.SACategory queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.server.model.SACategory> list = new ArrayList<wh.server.model.SACategory>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("SACategoryId", queryModel.getSACategoryId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("SACategoryName", queryModel.getSACategoryName());
		inParameters.put("Level", queryModel.getLevel());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("Status", queryModel.getStatus());
		
		Map<String, Integer> outParameters = new HashMap<String, Integer>();
		outParameters.put("ErrNo", java.sql.Types.INTEGER);
		outParameters.put("ErrMsg", java.sql.Types.NVARCHAR);
		outParameters.put("TotalCount", java.sql.Types.INTEGER);
		
        int parasCount = inParameters.size() + outParameters.size();
		StringBuilder sbBuilder = new StringBuilder();
		for (int i = 0; i < parasCount; i++) {
			if (0 == i) {
				sbBuilder.append("?");
			}
			else {
				sbBuilder.append(",?");
			}
		}
		
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Server_SACategory_GetList(" + sbBuilder.toString() + ")}",
					inParameters, outParameters);
			if (rs != null) {
				while (rs.next()) {
					list.add(getModel(rs));
				}
				
				cstmt = (CallableStatement)rs.getStatement();
				errNo = cstmt.getInt("ErrNo");
				errMsg = cstmt.getString("ErrMsg");
				totalCount.argvalue = cstmt.getInt("TotalCount");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		} finally {
			SQLHelper.closeAll(cstmt);
		}
		
		MyErr.checkErr(errNo, errMsg);
		return list;
	}
	
	
	/**
	 * 删除服务器帐户分类
	 * 
	 * @param id 服务器帐户分类编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("SACategoryId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Server_SACategory_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取服务器帐户分类根据服务器帐户分类名称
	 * 
	 * @param SACategoryName 
	 *        服务器帐户分类名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @return 服务器帐户分类
	 */
	public wh.server.model.SACategory getByName(String sACategoryName, String fldSort, int sortType) throws Exception{
		wh.server.model.SACategory model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("SACategoryName", sACategoryName);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Server_SACategory_GetByName(?,?,?,?,?)}", inParameters, outParameters);
			if (rs != null) {
				rs.next();
				model = getModel(rs);
				
				cstmt = (CallableStatement)rs.getStatement();
				errNo = cstmt.getInt("ErrNo");
				errMsg = cstmt.getString("ErrMsg");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		} finally{
			SQLHelper.closeAll(cstmt);
		}
		
		MyErr.checkErr(errNo, errMsg);
		return model;
	}
	
	/**
	 * 获取服务器帐户分类列表根据服务器帐户分类名称
	 * 
	 * @param SACategoryName 
	 *        服务器帐户分类名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @return 服务器帐户分类列表
	 */
	public List<wh.server.model.SACategory> getListByName(String sACategoryName, String fldSort, int sortType, int queryCount) throws Exception{
		List<wh.server.model.SACategory> list = new ArrayList<wh.server.model.SACategory>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("SACategoryName", sACategoryName);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		inParameters.put("QueryCount", queryCount);
		
		Map<String, Integer> outParameters = new HashMap<String, Integer>();
		outParameters.put("ErrNo", java.sql.Types.INTEGER);
		outParameters.put("ErrMsg", java.sql.Types.NVARCHAR);
		
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Server_SACategory_GetListByName(?,?,?,?,?,?)}", inParameters, outParameters);
			if (rs != null) {
				while (rs.next()) {
					list.add(getModel(rs));
				}
				
				cstmt = (CallableStatement)rs.getStatement();
				errNo = cstmt.getInt("ErrNo");
				errMsg = cstmt.getString("ErrMsg");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		} finally {
			SQLHelper.closeAll(cstmt);
		}
		
		MyErr.checkErr(errNo, errMsg);
		return list;
	}
	
	/**
	 * 修改服务器帐户分类状态
	 * 
	 * @param sACategoryId 
	 *        服务器帐户分类编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int sACategoryId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("SACategoryId", sACategoryId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Server_SACategory_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取服务器帐户分类
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.server.model.SACategory getModel(ResultSet rs) throws SQLException{
		wh.server.model.SACategory m = null;
		if (rs != null)
        {
            m = new wh.server.model.SACategory();
			try{
				m.setSACategoryId(rs.getInt("SACategoryId"));
				wh.member.model.Manager manager = new wh.member.model.Manager();
				manager.setManagerId(rs.getInt("Manager_ManagerId"));
				manager.setUserName(rs.getString("Manager_UserName"));
				manager.setPassword(rs.getString("Manager_Password"));
				manager.setRealName(rs.getString("Manager_RealName"));
				manager.setGenderType(rs.getByte("Manager_GenderType"));
				manager.setIDCard(rs.getString("Manager_IDCard"));
				manager.setTel(rs.getString("Manager_Tel"));
				manager.setMobilePhone(rs.getString("Manager_MobilePhone"));
				manager.setOtherMobilePhone(rs.getString("Manager_OtherMobilePhone"));
				manager.setCreateDate(rs.getTimestamp("Manager_CreateDate"));
				manager.setModifyDate(rs.getTimestamp("Manager_ModifyDate"));
				manager.setManagerType(rs.getInt("Manager_ManagerType"));
				manager.setStatus(rs.getByte("Manager_Status"));
				manager.setAccessType(rs.getByte("Manager_AccessType"));
				m.setManager(manager);
				
				m.setSACategoryName(rs.getString("SACategoryName"));
				m.setLevel(rs.getInt("Level"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


