package wh.member.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Rebate数据访问层
 */
public class Rebate
{
	/**
	 * 创建更新返利
	 * 
	 * @param model 
	 *        返利
	 * @return 返利编号
	 */
	public int createUpdate(wh.member.model.Rebate model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RebateId", model.getRebateId());
		inParameters.put("MManagerId", model.getMManagerId());
		inParameters.put("MManagerName", model.getMManagerName());
		inParameters.put("Title", model.getTitle());
		inParameters.put("MUserId", model.getMUserId());
		inParameters.put("MUserName", model.getMUserName());
		inParameters.put("PayAccount", model.getPayAccount());
		inParameters.put("RebateAccount", model.getRebateAccount());
		inParameters.put("SingleRebateAccount", model.getSingleRebateAccount());
		inParameters.put("RebateCount", model.getRebateCount());
		inParameters.put("RebateBeginDate", Utility.getSqlDate(model.getRebateBeginDate()));
		inParameters.put("RebateEndDate", Utility.getSqlDate(model.getRebateEndDate()));
		inParameters.put("Description", model.getDescription());
		inParameters.put("ProcessType", model.getProcessType());
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
			"{? = call WH_Member_Rebate_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取返利
	 * 
	 * @param id 
	 *        返利编号
	 * @return 返利
     */
	public wh.member.model.Rebate get(int id) throws Exception{
		wh.member.model.Rebate model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RebateId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_Rebate_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取返利列表
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
	 * @return 返利列表
     */
	public List<wh.member.model.Rebate> getList(wh.member.model.Rebate queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.member.model.Rebate> list = new ArrayList<wh.member.model.Rebate>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("RebateId", queryModel.getRebateId());
		inParameters.put("MManagerId", queryModel.getMManagerId());
		inParameters.put("MManagerName", queryModel.getMManagerName());
		inParameters.put("Title", queryModel.getTitle());
		inParameters.put("MUserId", queryModel.getMUserId());
		inParameters.put("MUserName", queryModel.getMUserName());
		inParameters.put("PayAccount", queryModel.getPayAccount());
		inParameters.put("RebateAccount", queryModel.getRebateAccount());
		inParameters.put("SingleRebateAccount", queryModel.getSingleRebateAccount());
		inParameters.put("RebateCount", queryModel.getRebateCount());
		inParameters.put("RebateBeginDateBegin", Utility.getSqlDate(queryModel.getRebateBeginDateBegin()));
		inParameters.put("RebateBeginDateEnd", Utility.getSqlDate(queryModel.getRebateBeginDateEnd()));
		inParameters.put("RebateEndDateBegin", Utility.getSqlDate(queryModel.getRebateEndDateBegin()));
		inParameters.put("RebateEndDateEnd", Utility.getSqlDate(queryModel.getRebateEndDateEnd()));
		inParameters.put("Description", queryModel.getDescription());
		inParameters.put("ProcessType", queryModel.getProcessType());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_Rebate_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除返利
	 * 
	 * @param id 返利编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RebateId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_Rebate_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改返利状态
	 * 
	 * @param rebateId 
	 *        返利编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int rebateId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RebateId", rebateId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_Rebate_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取返利
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.member.model.Rebate getModel(ResultSet rs) throws SQLException{
		wh.member.model.Rebate m = null;
		if (rs != null)
        {
            m = new wh.member.model.Rebate();
			try{
				m.setRebateId(rs.getInt("RebateId"));
				m.setMManagerId(rs.getInt("MManagerId"));
				m.setMManagerName(rs.getString("MManagerName"));
				m.setTitle(rs.getString("Title"));
				m.setMUserId(rs.getInt("MUserId"));
				m.setMUserName(rs.getString("MUserName"));
				m.setPayAccount(rs.getBigDecimal("PayAccount"));
				m.setRebateAccount(rs.getBigDecimal("RebateAccount"));
				m.setSingleRebateAccount(rs.getBigDecimal("SingleRebateAccount"));
				m.setRebateCount(rs.getInt("RebateCount"));
				m.setRebateBeginDate(rs.getTimestamp("RebateBeginDate"));
				m.setRebateEndDate(rs.getTimestamp("RebateEndDate"));
				m.setDescription(rs.getString("Description"));
				m.setProcessType(rs.getByte("ProcessType"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


