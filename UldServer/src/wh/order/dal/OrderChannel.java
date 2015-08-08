package wh.order.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * OrderChannel数据访问层
 */
public class OrderChannel
{
	/**
	 * 创建更新订单渠道
	 * 
	 * @param model 
	 *        订单渠道
	 * @return 订单渠道编号
	 */
	public int createUpdate(wh.order.model.OrderChannel model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderChannelId", model.getOrderChannelId());
		inParameters.put("LocalOrderId", model.getLocalOrderId());
		inParameters.put("RegChannelId", model.getRegChannelId());
		inParameters.put("OcChannelId", model.getOcChannelId());
		inParameters.put("GameChannelId", model.getGameChannelId());
		inParameters.put("ReservedInt", model.getReservedInt());
		inParameters.put("ReservedStr", model.getReservedStr());
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
			"{? = call WH_Order_OrderChannel_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取订单渠道
	 * 
	 * @param id 
	 *        订单渠道编号
	 * @return 订单渠道
     */
	public wh.order.model.OrderChannel get(int id) throws Exception{
		wh.order.model.OrderChannel model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderChannelId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Order_OrderChannel_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取订单渠道列表
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
	 * @return 订单渠道列表
     */
	public List<wh.order.model.OrderChannel> getList(wh.order.model.OrderChannel queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.order.model.OrderChannel> list = new ArrayList<wh.order.model.OrderChannel>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("OrderChannelId", queryModel.getOrderChannelId());
		inParameters.put("LocalOrderId", queryModel.getLocalOrderId());
		inParameters.put("RegChannelId", queryModel.getRegChannelId());
		inParameters.put("OcChannelId", queryModel.getOcChannelId());
		inParameters.put("GameChannelId", queryModel.getGameChannelId());
		inParameters.put("ReservedInt", queryModel.getReservedInt());
		inParameters.put("ReservedStr", queryModel.getReservedStr());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Order_OrderChannel_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除订单渠道
	 * 
	 * @param id 订单渠道编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderChannelId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Order_OrderChannel_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改订单渠道状态
	 * 
	 * @param orderChannelId 
	 *        订单渠道编号
	 * @param status 
	 *        状态：0、状态；1、显示；2、不显示；
	 */
	public void modifyStatus(int orderChannelId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderChannelId", orderChannelId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Order_OrderChannel_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取订单渠道
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.order.model.OrderChannel getModel(ResultSet rs) throws SQLException{
		wh.order.model.OrderChannel m = null;
		if (rs != null)
        {
            m = new wh.order.model.OrderChannel();
			try{
				m.setOrderChannelId(rs.getInt("OrderChannelId"));
				m.setLocalOrderId(rs.getInt("LocalOrderId"));
				m.setRegChannelId(rs.getInt("RegChannelId"));
				m.setOcChannelId(rs.getInt("OcChannelId"));
				m.setGameChannelId(rs.getInt("GameChannelId"));
				m.setReservedInt(rs.getInt("ReservedInt"));
				m.setReservedStr(rs.getInt("ReservedStr"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


