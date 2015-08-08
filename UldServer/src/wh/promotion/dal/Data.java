package wh.promotion.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Data数据访问层
 */
public class Data
{
	/**
	 * 创建更新推广渠道数据
	 * 
	 * @param model 
	 *        推广渠道数据
	 * @return 推广渠道数据编号
	 */
	public int createUpdate(wh.promotion.model.Data model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("DataId", model.getDataId());
		inParameters.put("DataChannelId", model.getDataChannelId());
		inParameters.put("DataChannelSubId", model.getDataChannelSubId());
		inParameters.put("DataUserId", model.getDataUserId());
		inParameters.put("UserName", model.getUserName());
		inParameters.put("OtherSiteId", model.getOtherSiteId());
		inParameters.put("RegisterDate", Utility.getSqlDate(model.getRegisterDate()));
		inParameters.put("RoleAndLevel", model.getRoleAndLevel());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		
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
			"{? = call WH_Promotion_Data_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取推广渠道数据
	 * 
	 * @param id 
	 *        推广渠道数据编号
	 * @return 推广渠道数据
     */
	public wh.promotion.model.Data get(int id) throws Exception{
		wh.promotion.model.Data model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("DataId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Data_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取推广渠道数据列表
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
	 * @return 推广渠道数据列表
     */
	public List<wh.promotion.model.Data> getList(wh.promotion.model.Data queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.promotion.model.Data> list = new ArrayList<wh.promotion.model.Data>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("DataId", queryModel.getDataId());
		inParameters.put("DataChannelId", queryModel.getDataChannelId());
		inParameters.put("DataChannelSubId", queryModel.getDataChannelSubId());
		inParameters.put("DataUserId", queryModel.getDataUserId());
		inParameters.put("UserName", queryModel.getUserName());
		inParameters.put("OtherSiteId", queryModel.getOtherSiteId());
		inParameters.put("RegisterDateBegin", Utility.getSqlDate(queryModel.getRegisterDateBegin()));
		inParameters.put("RegisterDateEnd", Utility.getSqlDate(queryModel.getRegisterDateEnd()));
		inParameters.put("RoleAndLevel", queryModel.getRoleAndLevel());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Data_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除推广渠道数据
	 * 
	 * @param id 推广渠道数据编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("DataId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Data_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取推广渠道数据
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.promotion.model.Data getModel(ResultSet rs) throws SQLException{
		wh.promotion.model.Data m = null;
		if (rs != null)
        {
            m = new wh.promotion.model.Data();
			try{
				m.setDataId(rs.getInt("DataId"));
				m.setDataChannelId(rs.getInt("DataChannelId"));
				m.setDataChannelSubId(rs.getInt("DataChannelSubId"));
				m.setDataUserId(rs.getInt("DataUserId"));
				m.setUserName(rs.getString("UserName"));
				m.setOtherSiteId(rs.getString("OtherSiteId"));
				m.setRegisterDate(rs.getTimestamp("RegisterDate"));
				m.setRoleAndLevel(rs.getString("RoleAndLevel"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


