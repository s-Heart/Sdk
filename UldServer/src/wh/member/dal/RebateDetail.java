package wh.member.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * RebateDetail数据访问层
 */
public class RebateDetail
{
	/**
	 * 创建更新返利详情
	 * 
	 * @param model 
	 *        返利详情
	 * @return 返利详情编号
	 */
	public int createUpdate(wh.member.model.RebateDetail model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RebateDetailId", model.getRebateDetailId());
		inParameters.put("RebateId", model.getRebateId());
		inParameters.put("Title", model.getTitle());
		inParameters.put("MUserId", model.getMUserId());
		inParameters.put("MUserName", model.getMUserName());
		inParameters.put("SingleRebateAccount", model.getSingleRebateAccount());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("ExecuteType", model.getExecuteType());
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
			"{? = call WH_Member_RebateDetail_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取返利详情
	 * 
	 * @param id 
	 *        返利详情编号
	 * @return 返利详情
     */
	public wh.member.model.RebateDetail get(int id) throws Exception{
		wh.member.model.RebateDetail model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RebateDetailId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_RebateDetail_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取返利详情列表
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
	 * @return 返利详情列表
     */
	public List<wh.member.model.RebateDetail> getList(wh.member.model.RebateDetail queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.member.model.RebateDetail> list = new ArrayList<wh.member.model.RebateDetail>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("RebateDetailId", queryModel.getRebateDetailId());
		inParameters.put("RebateId", queryModel.getRebateId());
		inParameters.put("Title", queryModel.getTitle());
		inParameters.put("MUserId", queryModel.getMUserId());
		inParameters.put("MUserName", queryModel.getMUserName());
		inParameters.put("SingleRebateAccount", queryModel.getSingleRebateAccount());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("ExecuteType", queryModel.getExecuteType());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_RebateDetail_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除返利详情
	 * 
	 * @param id 返利详情编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RebateDetailId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_RebateDetail_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改返利详情状态
	 * 
	 * @param rebateDetailId 
	 *        返利详情编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int rebateDetailId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RebateDetailId", rebateDetailId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_RebateDetail_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取返利详情
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.member.model.RebateDetail getModel(ResultSet rs) throws SQLException{
		wh.member.model.RebateDetail m = null;
		if (rs != null)
        {
            m = new wh.member.model.RebateDetail();
			try{
				m.setRebateDetailId(rs.getInt("RebateDetailId"));
				wh.member.model.Rebate rebate = new wh.member.model.Rebate();
				rebate.setRebateId(rs.getInt("Rebate_RebateId"));
				rebate.setMManagerId(rs.getInt("Rebate_MManagerId"));
				rebate.setMManagerName(rs.getString("Rebate_MManagerName"));
				rebate.setTitle(rs.getString("Rebate_Title"));
				rebate.setMUserId(rs.getInt("Rebate_MUserId"));
				rebate.setMUserName(rs.getString("Rebate_MUserName"));
				rebate.setPayAccount(rs.getBigDecimal("Rebate_PayAccount"));
				rebate.setRebateAccount(rs.getBigDecimal("Rebate_RebateAccount"));
				rebate.setSingleRebateAccount(rs.getBigDecimal("Rebate_SingleRebateAccount"));
				rebate.setRebateCount(rs.getInt("Rebate_RebateCount"));
				rebate.setRebateBeginDate(rs.getTimestamp("Rebate_RebateBeginDate"));
				rebate.setRebateEndDate(rs.getTimestamp("Rebate_RebateEndDate"));
				rebate.setDescription(rs.getString("Rebate_Description"));
				rebate.setProcessType(rs.getByte("Rebate_ProcessType"));
				rebate.setStatus(rs.getByte("Rebate_Status"));
				m.setRebate(rebate);
				
				m.setTitle(rs.getString("Title"));
				m.setMUserId(rs.getInt("MUserId"));
				m.setMUserName(rs.getString("MUserName"));
				m.setSingleRebateAccount(rs.getBigDecimal("SingleRebateAccount"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setExecuteType(rs.getByte("ExecuteType"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


