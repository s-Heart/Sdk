package wh.promotion.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Channel数据访问层
 */
public class Channel
{
	/**
	 * 创建更新渠道
	 * 
	 * @param model 
	 *        渠道
	 * @return 渠道编号
	 */
	public int createUpdate(wh.promotion.model.Channel model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelId", model.getChannelId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("ChannelType", model.getChannelType());
		inParameters.put("ChannelName", model.getChannelName());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("ModifyDate", Utility.getSqlDate(model.getModifyDate()));
		inParameters.put("Status", model.getStatus());
		inParameters.put("Code", model.getCode());
		
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
			"{? = call WH_Promotion_Channel_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取渠道
	 * 
	 * @param id 
	 *        渠道编号
	 * @return 渠道
     */
	public wh.promotion.model.Channel get(int id) throws Exception{
		wh.promotion.model.Channel model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Channel_Get(?,?,?)}", inParameters, outParameters);
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
	 * @return 渠道列表
     */
	public List<wh.promotion.model.Channel> getList(wh.promotion.model.Channel queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.promotion.model.Channel> list = new ArrayList<wh.promotion.model.Channel>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("ChannelId", queryModel.getChannelId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("ChannelType", queryModel.getChannelType());
		inParameters.put("ChannelName", queryModel.getChannelName());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("ModifyDateBegin", Utility.getSqlDate(queryModel.getModifyDateBegin()));
		inParameters.put("ModifyDateEnd", Utility.getSqlDate(queryModel.getModifyDateEnd()));
		inParameters.put("Status", queryModel.getStatus());
		inParameters.put("Code", queryModel.getCode());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Channel_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除渠道
	 * 
	 * @param id 渠道编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Channel_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
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
	 * @return 渠道
	 */
	public wh.promotion.model.Channel getByName(String channelName, String fldSort, int sortType) throws Exception{
		wh.promotion.model.Channel model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelName", channelName);
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Channel_GetByName(?,?,?,?,?)}", inParameters, outParameters);
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
	 * @return 渠道列表
	 */
	public List<wh.promotion.model.Channel> getListByName(String channelName, String fldSort, int sortType, int queryCount) throws Exception{
		List<wh.promotion.model.Channel> list = new ArrayList<wh.promotion.model.Channel>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelName", channelName);
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Channel_GetListByName(?,?,?,?,?,?)}", inParameters, outParameters);
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
	 * 修改渠道状态
	 * 
	 * @param channelId 
	 *        渠道编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int channelId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelId", channelId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Channel_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取渠道
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.promotion.model.Channel getModel(ResultSet rs) throws SQLException{
		wh.promotion.model.Channel m = null;
		if (rs != null)
        {
            m = new wh.promotion.model.Channel();
			try{
				m.setChannelId(rs.getInt("ChannelId"));
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
				
				m.setChannelType(rs.getByte("ChannelType"));
				m.setChannelName(rs.getString("ChannelName"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
				m.setStatus(rs.getByte("Status"));
				m.setCode(rs.getString("Code"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


