package wh.member.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Message数据访问层
 */
public class Message
{
	/**
	 * 创建更新消息
	 * 
	 * @param model 
	 *        消息
	 * @return 消息编号
	 */
	public int createUpdate(wh.member.model.Message model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("MessageId", model.getMessageId());
		inParameters.put("Title", model.getTitle());
		inParameters.put("Content", model.getContent());
		inParameters.put("MessageType", model.getMessageType());
		inParameters.put("EffectiveDate", Utility.getSqlDate(model.getEffectiveDate()));
		inParameters.put("EffectiveDateDay", Utility.getDateDay(model.getEffectiveDate()));
		inParameters.put("ExpiryDate", Utility.getSqlDate(model.getExpiryDate()));
		inParameters.put("ExpiryDateDay", Utility.getDateDay(model.getExpiryDate()));
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
			"{? = call WH_Member_Message_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取消息
	 * 
	 * @param id 
	 *        消息编号
	 * @return 消息
     */
	public wh.member.model.Message get(int id) throws Exception{
		wh.member.model.Message model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("MessageId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_Message_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取消息列表
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
	 * @return 消息列表
     */
	public List<wh.member.model.Message> getList(wh.member.model.Message queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.member.model.Message> list = new ArrayList<wh.member.model.Message>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("MessageId", queryModel.getMessageId());
		inParameters.put("Title", queryModel.getTitle());
		inParameters.put("Content", queryModel.getContent());
		inParameters.put("MessageType", queryModel.getMessageType());
		inParameters.put("EffectiveDateBegin", Utility.getSqlDate(queryModel.getEffectiveDateBegin()));
		inParameters.put("EffectiveDateEnd", Utility.getSqlDate(queryModel.getEffectiveDateEnd()));
		inParameters.put("EffectiveDateDay", Utility.getDateDay(queryModel.getEffectiveDate()));
		inParameters.put("ExpiryDateBegin", Utility.getSqlDate(queryModel.getExpiryDateBegin()));
		inParameters.put("ExpiryDateEnd", Utility.getSqlDate(queryModel.getExpiryDateEnd()));
		inParameters.put("ExpiryDateDay", Utility.getDateDay(queryModel.getExpiryDate()));
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_Message_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除消息
	 * 
	 * @param id 消息编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("MessageId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_Message_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改消息状态
	 * 
	 * @param messageId 
	 *        消息编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int messageId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("MessageId", messageId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_Message_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取消息
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.member.model.Message getModel(ResultSet rs) throws SQLException{
		wh.member.model.Message m = null;
		if (rs != null)
        {
            m = new wh.member.model.Message();
			try{
				m.setMessageId(rs.getInt("MessageId"));
				m.setTitle(rs.getString("Title"));
				m.setContent(rs.getString("Content"));
				m.setMessageType(rs.getByte("MessageType"));
				m.setEffectiveDate(rs.getTimestamp("EffectiveDate"));
				m.setExpiryDate(rs.getTimestamp("ExpiryDate"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


