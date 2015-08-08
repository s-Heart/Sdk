package wh.game.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * ActiveCode数据访问层
 */
public class ActiveCode
{
	/**
	 * 创建更新激活码
	 * 
	 * @param model 
	 *        激活码
	 * @return 激活码编号
	 */
	public int createUpdate(wh.game.model.ActiveCode model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ActiveCodeId", model.getActiveCodeId());
		inParameters.put("GameId", model.getGameId());
		inParameters.put("ServerId", model.getServerId());
		inParameters.put("Name", model.getName());
		inParameters.put("Code", model.getCode());
		inParameters.put("ActiveCodeType", model.getActiveCodeType());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("ModifyDate", Utility.getSqlDate(model.getModifyDate()));
		inParameters.put("Status", model.getStatus());
		inParameters.put("UsedType", model.getUsedType());
		
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
			"{? = call WH_Game_ActiveCode_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取激活码
	 * 
	 * @param id 
	 *        激活码编号
	 * @return 激活码
     */
	public wh.game.model.ActiveCode get(int id) throws Exception{
		wh.game.model.ActiveCode model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ActiveCodeId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_ActiveCode_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取激活码列表
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
	 * @return 激活码列表
     */
	public List<wh.game.model.ActiveCode> getList(wh.game.model.ActiveCode queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.game.model.ActiveCode> list = new ArrayList<wh.game.model.ActiveCode>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("ActiveCodeId", queryModel.getActiveCodeId());
		inParameters.put("GameId", queryModel.getGameId());
		inParameters.put("ServerId", queryModel.getServerId());
		inParameters.put("Name", queryModel.getName());
		inParameters.put("Code", queryModel.getCode());
		inParameters.put("ActiveCodeType", queryModel.getActiveCodeType());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("ModifyDateBegin", Utility.getSqlDate(queryModel.getModifyDateBegin()));
		inParameters.put("ModifyDateEnd", Utility.getSqlDate(queryModel.getModifyDateEnd()));
		inParameters.put("Status", queryModel.getStatus());
		inParameters.put("UsedType", queryModel.getUsedType());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_ActiveCode_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除激活码
	 * 
	 * @param id 激活码编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ActiveCodeId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Game_ActiveCode_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改激活码状态
	 * 
	 * @param activeCodeId 
	 *        激活码编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int activeCodeId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ActiveCodeId", activeCodeId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Game_ActiveCode_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取激活码
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.game.model.ActiveCode getModel(ResultSet rs) throws SQLException{
		wh.game.model.ActiveCode m = null;
		if (rs != null)
        {
            m = new wh.game.model.ActiveCode();
			try{
				m.setActiveCodeId(rs.getInt("ActiveCodeId"));
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
				
				wh.game.model.Server server = new wh.game.model.Server();
				server.setServerId(rs.getInt("Server_ServerId"));
				server.setGame(new wh.game.model.Game());
				server.getGame().setGameId(rs.getInt("Server_GameId"));
				server.setManager(new wh.member.model.Manager());
				server.getManager().setManagerId(rs.getInt("Server_ManagerId"));
				server.setProvider(new wh.game.model.Provider());
				server.getProvider().setProviderId(rs.getInt("Server_ProviderId"));
				server.setServerName(rs.getString("Server_ServerName"));
				server.setServerCode(rs.getString("Server_ServerCode"));
				server.setSequenceNumber(rs.getInt("Server_SequenceNumber"));
				server.setServerIP(rs.getString("Server_ServerIP"));
				server.setServerDomain(rs.getString("Server_ServerDomain"));
				server.setServerPort(rs.getString("Server_ServerPort"));
				server.setServerUrl(rs.getString("Server_ServerUrl"));
				server.setOpenDate(rs.getTimestamp("Server_OpenDate"));
				server.setCreateDate(rs.getTimestamp("Server_CreateDate"));
				server.setLineType(rs.getByte("Server_LineType"));
				server.setEnableType(rs.getByte("Server_EnableType"));
				server.setRechargeType(rs.getByte("Server_RechargeType"));
				server.setRecommendType(rs.getByte("Server_RecommendType"));
				server.setEnableMessageType(rs.getByte("Server_EnableMessageType"));
				server.setServerType(rs.getByte("Server_ServerType"));
				server.setStatus(rs.getByte("Server_Status"));
				m.setServer(server);
				
				m.setName(rs.getString("Name"));
				m.setCode(rs.getString("Code"));
				m.setActiveCodeType(rs.getByte("ActiveCodeType"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
				m.setStatus(rs.getByte("Status"));
				m.setUsedType(rs.getByte("UsedType"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


