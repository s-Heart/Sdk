package wh.member.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Refund数据访问层
 */
public class Refund
{
	/**
	 * 创建更新退款
	 * 
	 * @param model 
	 *        退款
	 * @return 退款编号
	 */
	public int createUpdate(wh.member.model.Refund model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RefundId", model.getRefundId());
		inParameters.put("RUserId", model.getRUserId());
		inParameters.put("UserName", model.getUserName());
		inParameters.put("RGameId", model.getRGameId());
		inParameters.put("GameName", model.getGameName());
		inParameters.put("RServerId", model.getRServerId());
		inParameters.put("ServerName", model.getServerName());
		inParameters.put("RefundType", model.getRefundType());
		inParameters.put("RoleName", model.getRoleName());
		inParameters.put("GameMoneyRate", model.getGameMoneyRate());
		inParameters.put("RealGameMoneyValue", model.getRealGameMoneyValue());
		inParameters.put("ApplyGameMoneyValue", model.getApplyGameMoneyValue());
		inParameters.put("RefundValue", model.getRefundValue());
		inParameters.put("Remark", model.getRemark());
		inParameters.put("SourceIP", model.getSourceIP());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("ModifyDate", Utility.getSqlDate(model.getModifyDate()));
		
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
			"{? = call WH_Member_Refund_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取退款
	 * 
	 * @param id 
	 *        退款编号
	 * @return 退款
     */
	public wh.member.model.Refund get(int id) throws Exception{
		wh.member.model.Refund model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RefundId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_Refund_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取退款列表
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
	 * @return 退款列表
     */
	public List<wh.member.model.Refund> getList(wh.member.model.Refund queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.member.model.Refund> list = new ArrayList<wh.member.model.Refund>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("RefundId", queryModel.getRefundId());
		inParameters.put("RUserId", queryModel.getRUserId());
		inParameters.put("UserName", queryModel.getUserName());
		inParameters.put("RGameId", queryModel.getRGameId());
		inParameters.put("GameName", queryModel.getGameName());
		inParameters.put("RServerId", queryModel.getRServerId());
		inParameters.put("ServerName", queryModel.getServerName());
		inParameters.put("RefundType", queryModel.getRefundType());
		inParameters.put("RoleName", queryModel.getRoleName());
		inParameters.put("GameMoneyRate", queryModel.getGameMoneyRate());
		inParameters.put("RealGameMoneyValue", queryModel.getRealGameMoneyValue());
		inParameters.put("ApplyGameMoneyValue", queryModel.getApplyGameMoneyValue());
		inParameters.put("RefundValue", queryModel.getRefundValue());
		inParameters.put("Remark", queryModel.getRemark());
		inParameters.put("SourceIP", queryModel.getSourceIP());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("ModifyDateBegin", Utility.getSqlDate(queryModel.getModifyDateBegin()));
		inParameters.put("ModifyDateEnd", Utility.getSqlDate(queryModel.getModifyDateEnd()));
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_Refund_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除退款
	 * 
	 * @param id 退款编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("RefundId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_Refund_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取退款
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.member.model.Refund getModel(ResultSet rs) throws SQLException{
		wh.member.model.Refund m = null;
		if (rs != null)
        {
            m = new wh.member.model.Refund();
			try{
				m.setRefundId(rs.getInt("RefundId"));
				m.setRUserId(rs.getInt("RUserId"));
				m.setUserName(rs.getString("UserName"));
				m.setRGameId(rs.getInt("RGameId"));
				m.setGameName(rs.getString("GameName"));
				m.setRServerId(rs.getInt("RServerId"));
				m.setServerName(rs.getString("ServerName"));
				m.setRefundType(rs.getByte("RefundType"));
				m.setRoleName(rs.getString("RoleName"));
				m.setGameMoneyRate(rs.getInt("GameMoneyRate"));
				m.setRealGameMoneyValue(rs.getInt("RealGameMoneyValue"));
				m.setApplyGameMoneyValue(rs.getInt("ApplyGameMoneyValue"));
				m.setRefundValue(rs.getInt("RefundValue"));
				m.setRemark(rs.getString("Remark"));
				m.setSourceIP(rs.getString("SourceIP"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


