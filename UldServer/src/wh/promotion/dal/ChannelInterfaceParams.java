package wh.promotion.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * ChannelInterfaceParams数据访问层
 */
public class ChannelInterfaceParams
{
	/**
	 * 创建更新渠道接口参数
	 * 
	 * @param model 
	 *        渠道接口参数
	 * @return 渠道接口参数编号
	 */
	public int createUpdate(wh.promotion.model.ChannelInterfaceParams model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelInterfaceParamsId", model.getChannelInterfaceParamsId());
		inParameters.put("ChannelInterfaceId", model.getChannelInterfaceId());
		inParameters.put("ParamName", model.getParamName());
		inParameters.put("ParamValue", model.getParamValue());
		inParameters.put("ParamExplain", model.getParamExplain());
		inParameters.put("SignType", model.getSignType());
		inParameters.put("SignIndex", model.getSignIndex());
		inParameters.put("ParamType", model.getParamType());
		inParameters.put("CIInType", model.getCIInType());
		inParameters.put("CIOutType", model.getCIOutType());
		
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
			"{? = call WH_Promotion_ChannelInterfaceParams_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取渠道接口参数
	 * 
	 * @param id 
	 *        渠道接口参数编号
	 * @return 渠道接口参数
     */
	public wh.promotion.model.ChannelInterfaceParams get(int id) throws Exception{
		wh.promotion.model.ChannelInterfaceParams model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelInterfaceParamsId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_ChannelInterfaceParams_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取渠道接口参数列表
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
	 * @return 渠道接口参数列表
     */
	public List<wh.promotion.model.ChannelInterfaceParams> getList(wh.promotion.model.ChannelInterfaceParams queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.promotion.model.ChannelInterfaceParams> list = new ArrayList<wh.promotion.model.ChannelInterfaceParams>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("ChannelInterfaceParamsId", queryModel.getChannelInterfaceParamsId());
		inParameters.put("ChannelInterfaceId", queryModel.getChannelInterfaceId());
		inParameters.put("ParamName", queryModel.getParamName());
		inParameters.put("ParamValue", queryModel.getParamValue());
		inParameters.put("ParamExplain", queryModel.getParamExplain());
		inParameters.put("SignType", queryModel.getSignType());
		inParameters.put("SignIndex", queryModel.getSignIndex());
		inParameters.put("ParamType", queryModel.getParamType());
		inParameters.put("CIInType", queryModel.getCIInType());
		inParameters.put("CIOutType", queryModel.getCIOutType());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_ChannelInterfaceParams_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除渠道接口参数
	 * 
	 * @param id 渠道接口参数编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ChannelInterfaceParamsId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_ChannelInterfaceParams_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取渠道接口参数
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.promotion.model.ChannelInterfaceParams getModel(ResultSet rs) throws SQLException{
		wh.promotion.model.ChannelInterfaceParams m = null;
		if (rs != null)
        {
            m = new wh.promotion.model.ChannelInterfaceParams();
			try{
				m.setChannelInterfaceParamsId(rs.getInt("ChannelInterfaceParamsId"));
				wh.promotion.model.ChannelInterface channelInterface = new wh.promotion.model.ChannelInterface();
				channelInterface.setChannelInterfaceId(rs.getInt("ChannelInterface_ChannelInterfaceId"));
				channelInterface.setChannel(new wh.promotion.model.Channel());
				channelInterface.getChannel().setChannelId(rs.getInt("ChannelInterface_ChannelId"));
				channelInterface.setChannelSub(new wh.promotion.model.ChannelSub());
				channelInterface.getChannelSub().setChannelSubId(rs.getInt("ChannelInterface_ChannelSubId"));
				channelInterface.setName(rs.getString("ChannelInterface_Name"));
				channelInterface.setUrl(rs.getString("ChannelInterface_Url"));
				channelInterface.setCIType(rs.getByte("ChannelInterface_CIType"));
				channelInterface.setCreateDate(rs.getTimestamp("ChannelInterface_CreateDate"));
				channelInterface.setStatus(rs.getByte("ChannelInterface_Status"));
				m.setChannelInterface(channelInterface);
				
				m.setParamName(rs.getString("ParamName"));
				m.setParamValue(rs.getString("ParamValue"));
				m.setParamExplain(rs.getString("ParamExplain"));
				m.setSignType(rs.getByte("SignType"));
				m.setSignIndex(rs.getByte("SignIndex"));
				m.setParamType(rs.getByte("ParamType"));
				m.setCIInType(rs.getByte("CIInType"));
				m.setCIOutType(rs.getByte("CIOutType"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


