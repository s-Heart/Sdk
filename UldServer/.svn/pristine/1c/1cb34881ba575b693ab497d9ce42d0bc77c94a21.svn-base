package wh.game.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * GameInterfaceParams数据访问层
 */
public class GameInterfaceParams
{
	/**
	 * 创建更新游戏接口参数
	 * 
	 * @param model 
	 *        游戏接口参数
	 * @return 游戏接口参数编号
	 */
	public int createUpdate(wh.game.model.GameInterfaceParams model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GameInterfaceParamsId", model.getGameInterfaceParamsId());
		inParameters.put("GameInterfaceId", model.getGameInterfaceId());
		inParameters.put("GameId", model.getGameId());
		inParameters.put("ParamName", model.getParamName());
		inParameters.put("ParamValue", model.getParamValue());
		inParameters.put("ParamExplain", model.getParamExplain());
		inParameters.put("SignType", model.getSignType());
		inParameters.put("SignIndex", model.getSignIndex());
		inParameters.put("ParamType", model.getParamType());
		inParameters.put("ParamInType", model.getParamInType());
		inParameters.put("ParamOutType", model.getParamOutType());
		inParameters.put("SignFormatValue", model.getSignFormatValue());
		
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
			"{? = call WH_Game_GameInterfaceParams_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取游戏接口参数
	 * 
	 * @param id 
	 *        游戏接口参数编号
	 * @return 游戏接口参数
     */
	public wh.game.model.GameInterfaceParams get(int id) throws Exception{
		wh.game.model.GameInterfaceParams model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GameInterfaceParamsId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_GameInterfaceParams_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取游戏接口参数列表
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
	 * @return 游戏接口参数列表
     */
	public List<wh.game.model.GameInterfaceParams> getList(wh.game.model.GameInterfaceParams queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.game.model.GameInterfaceParams> list = new ArrayList<wh.game.model.GameInterfaceParams>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("GameInterfaceParamsId", queryModel.getGameInterfaceParamsId());
		inParameters.put("GameInterfaceId", queryModel.getGameInterfaceId());
		inParameters.put("GameId", queryModel.getGameId());
		inParameters.put("ParamName", queryModel.getParamName());
		inParameters.put("ParamValue", queryModel.getParamValue());
		inParameters.put("ParamExplain", queryModel.getParamExplain());
		inParameters.put("SignType", queryModel.getSignType());
		inParameters.put("SignIndex", queryModel.getSignIndex());
		inParameters.put("ParamType", queryModel.getParamType());
		inParameters.put("ParamInType", queryModel.getParamInType());
		inParameters.put("ParamOutType", queryModel.getParamOutType());
		inParameters.put("SignFormatValue", queryModel.getSignFormatValue());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Game_GameInterfaceParams_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除游戏接口参数
	 * 
	 * @param id 游戏接口参数编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GameInterfaceParamsId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Game_GameInterfaceParams_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取游戏接口参数
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.game.model.GameInterfaceParams getModel(ResultSet rs) throws SQLException{
		wh.game.model.GameInterfaceParams m = null;
		if (rs != null)
        {
            m = new wh.game.model.GameInterfaceParams();
			try{
				m.setGameInterfaceParamsId(rs.getInt("GameInterfaceParamsId"));
				wh.game.model.GameInterface gameInterface = new wh.game.model.GameInterface();
				gameInterface.setGameInterfaceId(rs.getInt("GameInterface_GameInterfaceId"));
				gameInterface.setGame(new wh.game.model.Game());
				gameInterface.getGame().setGameId(rs.getInt("GameInterface_GameId"));
				gameInterface.setName(rs.getString("GameInterface_Name"));
				gameInterface.setUrl(rs.getString("GameInterface_Url"));
				gameInterface.setInterfaceType(rs.getByte("GameInterface_InterfaceType"));
				gameInterface.setPayUnitType(rs.getByte("GameInterface_PayUnitType"));
				gameInterface.setCreateDate(rs.getTimestamp("GameInterface_CreateDate"));
				gameInterface.setStatus(rs.getByte("GameInterface_Status"));
				gameInterface.setSignJoinSymbol(rs.getString("GameInterface_SignJoinSymbol"));
				m.setGameInterface(gameInterface);
				
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
				
				m.setParamName(rs.getString("ParamName"));
				m.setParamValue(rs.getString("ParamValue"));
				m.setParamExplain(rs.getString("ParamExplain"));
				m.setSignType(rs.getByte("SignType"));
				m.setSignIndex(rs.getByte("SignIndex"));
				m.setParamType(rs.getByte("ParamType"));
				m.setParamInType(rs.getByte("ParamInType"));
				m.setParamOutType(rs.getByte("ParamOutType"));
				m.setSignFormatValue(rs.getString("SignFormatValue"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


