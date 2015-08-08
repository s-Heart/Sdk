package wh.game.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Server数据访问层
 */
public class Server
{
	/**
	 * 创建更新服务器
	 * 
	 * @param model 
	 *        服务器
	 * @return 服务器编号
	 */
	public int createUpdate(wh.game.model.Server model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ServerId", model.getServerId());
		inParameters.put("GameId", model.getGameId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("ProviderId", model.getProviderId());
		inParameters.put("ServerName", model.getServerName());
		inParameters.put("ServerCode", model.getServerCode());
		inParameters.put("SequenceNumber", model.getSequenceNumber());
		inParameters.put("ServerIP", model.getServerIP());
		inParameters.put("ServerDomain", model.getServerDomain());
		inParameters.put("ServerPort", model.getServerPort());
		inParameters.put("ServerUrl", model.getServerUrl());
		inParameters.put("OpenDate", Utility.getSqlDate(model.getOpenDate()));
		inParameters.put("OpenDateYear", Utility.getDateYear(model.getOpenDate()));
		inParameters.put("OpenDateMonth", Utility.getDateMonth(model.getOpenDate()));
		inParameters.put("OpenDateDay", Utility.getDateDay(model.getOpenDate()));
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("CreateDateYear", Utility.getDateYear(model.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(model.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(model.getCreateDate()));
		inParameters.put("LineType", model.getLineType());
		inParameters.put("EnableType", model.getEnableType());
		inParameters.put("RechargeType", model.getRechargeType());
		inParameters.put("RecommendType", model.getRecommendType());
		inParameters.put("EnableMessageType", model.getEnableMessageType());
		inParameters.put("ServerType", model.getServerType());
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
			"{? = call WH_Game_Server_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取服务器
	 * 
	 * @param id 
	 *        服务器编号
	 * @return 服务器
     */
	public wh.game.model.Server get(int id) throws Exception{
		wh.game.model.Server model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ServerId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_Server_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取服务器列表
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
	 * @return 服务器列表
     */
	public List<wh.game.model.Server> getList(wh.game.model.Server queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.game.model.Server> list = new ArrayList<wh.game.model.Server>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("ServerId", queryModel.getServerId());
		inParameters.put("GameId", queryModel.getGameId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("ProviderId", queryModel.getProviderId());
		inParameters.put("ServerName", queryModel.getServerName());
		inParameters.put("ServerCode", queryModel.getServerCode());
		inParameters.put("SequenceNumber", queryModel.getSequenceNumber());
		inParameters.put("ServerIP", queryModel.getServerIP());
		inParameters.put("ServerDomain", queryModel.getServerDomain());
		inParameters.put("ServerPort", queryModel.getServerPort());
		inParameters.put("ServerUrl", queryModel.getServerUrl());
		inParameters.put("OpenDateBegin", Utility.getSqlDate(queryModel.getOpenDateBegin()));
		inParameters.put("OpenDateEnd", Utility.getSqlDate(queryModel.getOpenDateEnd()));
		inParameters.put("OpenDateYear", Utility.getDateYear(queryModel.getOpenDate()));
		inParameters.put("OpenDateMonth", Utility.getDateMonth(queryModel.getOpenDate()));
		inParameters.put("OpenDateDay", Utility.getDateDay(queryModel.getOpenDate()));
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("CreateDateYear", Utility.getDateYear(queryModel.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(queryModel.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(queryModel.getCreateDate()));
		inParameters.put("LineType", queryModel.getLineType());
		inParameters.put("EnableType", queryModel.getEnableType());
		inParameters.put("RechargeType", queryModel.getRechargeType());
		inParameters.put("RecommendType", queryModel.getRecommendType());
		inParameters.put("EnableMessageType", queryModel.getEnableMessageType());
		inParameters.put("ServerType", queryModel.getServerType());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_Server_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除服务器
	 * 
	 * @param id 服务器编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ServerId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Game_Server_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取服务器根据服务器名称
	 * 
	 * @param ServerName 
	 *        服务器名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @return 服务器
	 */
	public wh.game.model.Server getByName(String serverName, String fldSort, int sortType) throws Exception{
		wh.game.model.Server model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ServerName", serverName);
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_Server_GetByName(?,?,?,?,?)}", inParameters, outParameters);
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
	 * 获取服务器列表根据服务器名称
	 * 
	 * @param ServerName 
	 *        服务器名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @return 服务器列表
	 */
	public List<wh.game.model.Server> getListByName(String serverName, String fldSort, int sortType, int queryCount) throws Exception{
		List<wh.game.model.Server> list = new ArrayList<wh.game.model.Server>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ServerName", serverName);
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_Server_GetListByName(?,?,?,?,?,?)}", inParameters, outParameters);
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
	 * 修改服务器状态
	 * 
	 * @param serverId 
	 *        服务编号
	 * @param status 
	 *        状态：0、状态；1、显示；2、不显示；
	 */
	public void modifyStatus(int serverId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ServerId", serverId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Game_Server_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取服务器
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.game.model.Server getModel(ResultSet rs) throws SQLException{
		wh.game.model.Server m = null;
		if (rs != null)
        {
            m = new wh.game.model.Server();
			try{
				m.setServerId(rs.getInt("ServerId"));
				wh.game.model.Game game = new wh.game.model.Game();
				game.setGameId(rs.getInt("Game_GameId"));
				game.setProvider(new wh.game.model.Provider());
				game.getProvider().setProviderId(rs.getInt("Game_ProviderId"));
				game.setManager(new wh.member.model.Manager());
				game.getManager().setManagerId(rs.getInt("Game_ManagerId"));
				game.setGameCategory(new wh.game.model.GameCategory());
				game.getGameCategory().setGameCategoryId(rs.getInt("Game_GameCategoryId"));
				game.setGameName(rs.getString("Game_GameName"));
				game.setPosterPath(rs.getString("Game_PosterPath"));
				game.setCreateDate(rs.getTimestamp("Game_CreateDate"));
				game.setGameType(rs.getByte("Game_GameType"));
				game.setEnableType(rs.getByte("Game_EnableType"));
				game.setStatus(rs.getByte("Game_Status"));
				game.setGameMoneyType(rs.getByte("Game_GameMoneyType"));
				game.setGameMoneyRate(rs.getInt("Game_GameMoneyRate"));
				game.setContent(rs.getString("Game_Content"));
				game.setRecommendType(rs.getByte("Game_RecommendType"));
				game.setHomeUrl(rs.getString("Game_HomeUrl"));
				game.setCPosterPath(rs.getString("Game_CPosterPath"));
				game.setSPosterPath(rs.getString("Game_SPosterPath"));
				game.setLPosterPath(rs.getString("Game_LPosterPath"));
				m.setGame(game);
				
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
				
				wh.game.model.Provider provider = new wh.game.model.Provider();
				provider.setProviderId(rs.getInt("Provider_ProviderId"));
				provider.setManager(new wh.member.model.Manager());
				provider.getManager().setManagerId(rs.getInt("Provider_ManagerId"));
				provider.setProviderName(rs.getString("Provider_ProviderName"));
				provider.setDescription(rs.getString("Provider_Description"));
				provider.setCreateDate(rs.getTimestamp("Provider_CreateDate"));
				provider.setStatus(rs.getByte("Provider_Status"));
				m.setProvider(provider);
				
				m.setServerName(rs.getString("ServerName"));
				m.setServerCode(rs.getString("ServerCode"));
				m.setSequenceNumber(rs.getInt("SequenceNumber"));
				m.setServerIP(rs.getString("ServerIP"));
				m.setServerDomain(rs.getString("ServerDomain"));
				m.setServerPort(rs.getString("ServerPort"));
				m.setServerUrl(rs.getString("ServerUrl"));
				m.setOpenDate(rs.getTimestamp("OpenDate"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setLineType(rs.getByte("LineType"));
				m.setEnableType(rs.getByte("EnableType"));
				m.setRechargeType(rs.getByte("RechargeType"));
				m.setRecommendType(rs.getByte("RecommendType"));
				m.setEnableMessageType(rs.getByte("EnableMessageType"));
				m.setServerType(rs.getByte("ServerType"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


