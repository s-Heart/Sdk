package wh.promotion.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * ChannelInterface数据访问层
 */
public class ChannelInterface
{
	/**
	 * 创建更新渠道接口
	 * 
	 * @param model 
	 *        渠道接口
	 * @return 渠道接口编号
	 */
	public int createUpdate(wh.promotion.model.ChannelInterface model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelInterfaceId", model.getChannelInterfaceId());
		inParameters.put("ChannelId", model.getChannelId());
		inParameters.put("ChannelSubId", model.getChannelSubId());
		inParameters.put("Name", model.getName());
		inParameters.put("Url", model.getUrl());
		inParameters.put("CIType", model.getCIType());
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
			"{? = call WH_Promotion_ChannelInterface_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取渠道接口
	 * 
	 * @param id 
	 *        渠道接口编号
	 * @return 渠道接口
     */
	public wh.promotion.model.ChannelInterface get(int id) throws Exception{
		wh.promotion.model.ChannelInterface model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelInterfaceId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_ChannelInterface_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取渠道接口列表
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
	 * @return 渠道接口列表
     */
	public List<wh.promotion.model.ChannelInterface> getList(wh.promotion.model.ChannelInterface queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.promotion.model.ChannelInterface> list = new ArrayList<wh.promotion.model.ChannelInterface>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("ChannelInterfaceId", queryModel.getChannelInterfaceId());
		inParameters.put("ChannelId", queryModel.getChannelId());
		inParameters.put("ChannelSubId", queryModel.getChannelSubId());
		inParameters.put("Name", queryModel.getName());
		inParameters.put("Url", queryModel.getUrl());
		inParameters.put("CIType", queryModel.getCIType());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_ChannelInterface_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除渠道接口
	 * 
	 * @param id 渠道接口编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelInterfaceId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_ChannelInterface_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改渠道接口状态
	 * 
	 * @param channelInterfaceId 
	 *        渠道接口编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int channelInterfaceId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelInterfaceId", channelInterfaceId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_ChannelInterface_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取渠道接口
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.promotion.model.ChannelInterface getModel(ResultSet rs) throws SQLException{
		wh.promotion.model.ChannelInterface m = null;
		if (rs != null)
        {
            m = new wh.promotion.model.ChannelInterface();
			try{
				m.setChannelInterfaceId(rs.getInt("ChannelInterfaceId"));
				wh.promotion.model.Channel channel = new wh.promotion.model.Channel();
				channel.setChannelId(rs.getInt("Channel_ChannelId"));
				channel.setManager(new wh.member.model.Manager());
				channel.getManager().setManagerId(rs.getInt("Channel_ManagerId"));
				channel.setChannelType(rs.getByte("Channel_ChannelType"));
				channel.setChannelName(rs.getString("Channel_ChannelName"));
				channel.setCreateDate(rs.getTimestamp("Channel_CreateDate"));
				channel.setModifyDate(rs.getTimestamp("Channel_ModifyDate"));
				channel.setStatus(rs.getByte("Channel_Status"));
				channel.setCode(rs.getString("Channel_Code"));
				m.setChannel(channel);
				
				wh.promotion.model.ChannelSub channelSub = new wh.promotion.model.ChannelSub();
				channelSub.setChannelSubId(rs.getInt("ChannelSub_ChannelSubId"));
				channelSub.setManager(new wh.member.model.Manager());
				channelSub.getManager().setManagerId(rs.getInt("ChannelSub_ManagerId"));
				channelSub.setChannel(new wh.promotion.model.Channel());
				channelSub.getChannel().setChannelId(rs.getInt("ChannelSub_ChannelId"));
				channelSub.setChannelType(rs.getByte("ChannelSub_ChannelType"));
				channelSub.setChannelSubName(rs.getString("ChannelSub_ChannelSubName"));
				channelSub.setCreateDate(rs.getTimestamp("ChannelSub_CreateDate"));
				channelSub.setModifyDate(rs.getTimestamp("ChannelSub_ModifyDate"));
				channelSub.setStatus(rs.getByte("ChannelSub_Status"));
				channelSub.setCode(rs.getString("ChannelSub_Code"));
				m.setChannelSub(channelSub);
				
				m.setName(rs.getString("Name"));
				m.setUrl(rs.getString("Url"));
				m.setCIType(rs.getByte("CIType"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


