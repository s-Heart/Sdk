package wh.member.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * ManagerLog数据访问层
 */
public class ManagerLog
{
	/**
	 * 创建更新管理员登录日志
	 * 
	 * @param model 
	 *        管理员登录日志
	 * @return 管理员登录日志编号
	 */
	public int createUpdate(wh.member.model.ManagerLog model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ManagerLogId", model.getManagerLogId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("AccessedDate", Utility.getSqlDate(model.getAccessedDate()));
		inParameters.put("AccessedDateYear", Utility.getDateYear(model.getAccessedDate()));
		inParameters.put("AccessedDateMonth", Utility.getDateMonth(model.getAccessedDate()));
		inParameters.put("AccessedDateDay", Utility.getDateDay(model.getAccessedDate()));
		inParameters.put("LeaveDate", Utility.getSqlDate(model.getLeaveDate()));
		inParameters.put("OnLineDuration", model.getOnLineDuration());
		inParameters.put("OnLineType", model.getOnLineType());
		inParameters.put("SourceIP", model.getSourceIP());
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
			"{? = call WH_Member_ManagerLog_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取管理员登录日志
	 * 
	 * @param id 
	 *        管理员登录日志编号
	 * @return 管理员登录日志
     */
	public wh.member.model.ManagerLog get(int id) throws Exception{
		wh.member.model.ManagerLog model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ManagerLogId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_ManagerLog_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取管理员登录日志列表
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
	 * @return 管理员登录日志列表
     */
	public List<wh.member.model.ManagerLog> getList(wh.member.model.ManagerLog queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.member.model.ManagerLog> list = new ArrayList<wh.member.model.ManagerLog>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("ManagerLogId", queryModel.getManagerLogId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("AccessedDateBegin", Utility.getSqlDate(queryModel.getAccessedDateBegin()));
		inParameters.put("AccessedDateEnd", Utility.getSqlDate(queryModel.getAccessedDateEnd()));
		inParameters.put("AccessedDateYear", Utility.getDateYear(queryModel.getAccessedDate()));
		inParameters.put("AccessedDateMonth", Utility.getDateMonth(queryModel.getAccessedDate()));
		inParameters.put("AccessedDateDay", Utility.getDateDay(queryModel.getAccessedDate()));
		inParameters.put("LeaveDateBegin", Utility.getSqlDate(queryModel.getLeaveDateBegin()));
		inParameters.put("LeaveDateEnd", Utility.getSqlDate(queryModel.getLeaveDateEnd()));
		inParameters.put("OnLineDuration", queryModel.getOnLineDuration());
		inParameters.put("OnLineType", queryModel.getOnLineType());
		inParameters.put("SourceIP", queryModel.getSourceIP());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_ManagerLog_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除管理员登录日志
	 * 
	 * @param id 管理员登录日志编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ManagerLogId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_ManagerLog_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改管理员登录日志状态
	 * 
	 * @param managerLogId 
	 *        管理员登录日志编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int managerLogId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ManagerLogId", managerLogId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_ManagerLog_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取管理员登录日志
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.member.model.ManagerLog getModel(ResultSet rs) throws SQLException{
		wh.member.model.ManagerLog m = null;
		if (rs != null)
        {
            m = new wh.member.model.ManagerLog();
			try{
				m.setManagerLogId(rs.getInt("ManagerLogId"));
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
				
				m.setAccessedDate(rs.getTimestamp("AccessedDate"));
				m.setLeaveDate(rs.getTimestamp("LeaveDate"));
				m.setOnLineDuration(rs.getInt("OnLineDuration"));
				m.setOnLineType(rs.getByte("OnLineType"));
				m.setSourceIP(rs.getString("SourceIP"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


