package wh.game.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Game数据访问层
 */
public class Game
{
	/**
	 * 创建更新游戏
	 * 
	 * @param model 
	 *        游戏
	 * @return 游戏编号
	 */
	public int createUpdate(wh.game.model.Game model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GameId", model.getGameId());
		inParameters.put("ProviderId", model.getProviderId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("GameCategoryId", model.getGameCategoryId());
		inParameters.put("GameName", model.getGameName());
		inParameters.put("PosterPath", model.getPosterPath());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("CreateDateYear", Utility.getDateYear(model.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(model.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(model.getCreateDate()));
		inParameters.put("GameType", model.getGameType());
		inParameters.put("EnableType", model.getEnableType());
		inParameters.put("Status", model.getStatus());
		inParameters.put("GameMoneyType", model.getGameMoneyType());
		inParameters.put("GameMoneyRate", model.getGameMoneyRate());
		inParameters.put("Content", model.getContent());
		inParameters.put("RecommendType", model.getRecommendType());
		inParameters.put("HomeUrl", model.getHomeUrl());
		inParameters.put("CPosterPath", model.getCPosterPath());
		inParameters.put("SPosterPath", model.getSPosterPath());
		inParameters.put("LPosterPath", model.getLPosterPath());
		
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
			"{? = call WH_Game_Game_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取游戏
	 * 
	 * @param id 
	 *        游戏编号
	 * @return 游戏
     */
	public wh.game.model.Game get(int id) throws Exception{
		wh.game.model.Game model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GameId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_Game_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取游戏列表
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
	 * @return 游戏列表
     */
	public List<wh.game.model.Game> getList(wh.game.model.Game queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.game.model.Game> list = new ArrayList<wh.game.model.Game>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("GameId", queryModel.getGameId());
		inParameters.put("ProviderId", queryModel.getProviderId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("GameCategoryId", queryModel.getGameCategoryId());
		inParameters.put("GameName", queryModel.getGameName());
		inParameters.put("PosterPath", queryModel.getPosterPath());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("CreateDateYear", Utility.getDateYear(queryModel.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(queryModel.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(queryModel.getCreateDate()));
		inParameters.put("GameType", queryModel.getGameType());
		inParameters.put("EnableType", queryModel.getEnableType());
		inParameters.put("Status", queryModel.getStatus());
		inParameters.put("GameMoneyType", queryModel.getGameMoneyType());
		inParameters.put("GameMoneyRate", queryModel.getGameMoneyRate());
		inParameters.put("Content", queryModel.getContent());
		inParameters.put("RecommendType", queryModel.getRecommendType());
		inParameters.put("HomeUrl", queryModel.getHomeUrl());
		inParameters.put("CPosterPath", queryModel.getCPosterPath());
		inParameters.put("SPosterPath", queryModel.getSPosterPath());
		inParameters.put("LPosterPath", queryModel.getLPosterPath());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_Game_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除游戏
	 * 
	 * @param id 游戏编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GameId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Game_Game_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取游戏根据游戏名称
	 * 
	 * @param GameName 
	 *        游戏名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @return 游戏
	 */
	public wh.game.model.Game getByName(String gameName, String fldSort, int sortType) throws Exception{
		wh.game.model.Game model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GameName", gameName);
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_Game_GetByName(?,?,?,?,?)}", inParameters, outParameters);
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
	 * 获取游戏列表根据游戏名称
	 * 
	 * @param GameName 
	 *        游戏名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @return 游戏列表
	 */
	public List<wh.game.model.Game> getListByName(String gameName, String fldSort, int sortType, int queryCount) throws Exception{
		List<wh.game.model.Game> list = new ArrayList<wh.game.model.Game>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GameName", gameName);
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_Game_GetListByName(?,?,?,?,?,?)}", inParameters, outParameters);
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
	 * 修改游戏状态
	 * 
	 * @param gameId 
	 *        游戏编号
	 * @param status 
	 *        状态：0、状态；1、显示；2、不显示；
	 */
	public void modifyStatus(int gameId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GameId", gameId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Game_Game_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取游戏
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.game.model.Game getModel(ResultSet rs) throws SQLException{
		wh.game.model.Game m = null;
		if (rs != null)
        {
            m = new wh.game.model.Game();
			try{
				m.setGameId(rs.getInt("GameId"));
				wh.game.model.Provider provider = new wh.game.model.Provider();
				provider.setProviderId(rs.getInt("Provider_ProviderId"));
				provider.setManager(new wh.member.model.Manager());
				provider.getManager().setManagerId(rs.getInt("Provider_ManagerId"));
				provider.setProviderName(rs.getString("Provider_ProviderName"));
				provider.setDescription(rs.getString("Provider_Description"));
				provider.setCreateDate(rs.getTimestamp("Provider_CreateDate"));
				provider.setStatus(rs.getByte("Provider_Status"));
				m.setProvider(provider);
				
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
				
				wh.game.model.GameCategory gameCategory = new wh.game.model.GameCategory();
				gameCategory.setGameCategoryId(rs.getInt("GameCategory_GameCategoryId"));
				gameCategory.setName(rs.getString("GameCategory_Name"));
				gameCategory.setDescription(rs.getString("GameCategory_Description"));
				gameCategory.setStatus(rs.getByte("GameCategory_Status"));
				m.setGameCategory(gameCategory);
				
				m.setGameName(rs.getString("GameName"));
				m.setPosterPath(rs.getString("PosterPath"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setGameType(rs.getByte("GameType"));
				m.setEnableType(rs.getByte("EnableType"));
				m.setStatus(rs.getByte("Status"));
				m.setGameMoneyType(rs.getByte("GameMoneyType"));
				m.setGameMoneyRate(rs.getInt("GameMoneyRate"));
				m.setContent(rs.getString("Content"));
				m.setRecommendType(rs.getByte("RecommendType"));
				m.setHomeUrl(rs.getString("HomeUrl"));
				m.setCPosterPath(rs.getString("CPosterPath"));
				m.setSPosterPath(rs.getString("SPosterPath"));
				m.setLPosterPath(rs.getString("LPosterPath"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


