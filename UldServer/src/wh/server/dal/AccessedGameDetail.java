package wh.server.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * AccessedGameDetail数据访问层
 */
public class AccessedGameDetail
{
	/**
	 * 创建更新登录过的游戏详情
	 * 
	 * @param model 
	 *        登录过的游戏详情
	 * @return 登录过的游戏详情编号
	 */
	public int createUpdate(wh.server.model.AccessedGameDetail model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("AccessedGameDetailId", model.getAccessedGameDetailId());
		inParameters.put("ServerId", model.getServerId());
		inParameters.put("GameId", model.getGameId());
		inParameters.put("AccessedGameId", model.getAccessedGameId());
		inParameters.put("UserId", model.getUserId());
		inParameters.put("AccessedDate", Utility.getSqlDate(model.getAccessedDate()));
		inParameters.put("AccessedDateYear", Utility.getDateYear(model.getAccessedDate()));
		inParameters.put("AccessedDateMonth", Utility.getDateMonth(model.getAccessedDate()));
		inParameters.put("AccessedDateDay", Utility.getDateDay(model.getAccessedDate()));
		inParameters.put("LeaveDate", Utility.getSqlDate(model.getLeaveDate()));
		inParameters.put("OnLineDuration", model.getOnLineDuration());
		inParameters.put("OnLineType", model.getOnLineType());
		inParameters.put("CurrentContinuousDays", model.getCurrentContinuousDays());
		inParameters.put("CurrentLoginTimes", model.getCurrentLoginTimes());
		inParameters.put("CurrentLoginDays", model.getCurrentLoginDays());
		inParameters.put("ContinuousServerDays", model.getContinuousServerDays());
		inParameters.put("LoginServerTimes", model.getLoginServerTimes());
		inParameters.put("ContinuousGameDays", model.getContinuousGameDays());
		inParameters.put("LoginGameTimes", model.getLoginGameTimes());
		inParameters.put("SourceIP", model.getSourceIP());
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
			"{? = call WH_Server_AccessedGameDetail_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取登录过的游戏详情
	 * 
	 * @param id 
	 *        登录过的游戏详情编号
	 * @return 登录过的游戏详情
     */
	public wh.server.model.AccessedGameDetail get(int id) throws Exception{
		wh.server.model.AccessedGameDetail model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("AccessedGameDetailId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Server_AccessedGameDetail_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取登录过的游戏详情列表
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
	 * @return 登录过的游戏详情列表
     */
	public List<wh.server.model.AccessedGameDetail> getList(wh.server.model.AccessedGameDetail queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.server.model.AccessedGameDetail> list = new ArrayList<wh.server.model.AccessedGameDetail>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("AccessedGameDetailId", queryModel.getAccessedGameDetailId());
		inParameters.put("ServerId", queryModel.getServerId());
		inParameters.put("GameId", queryModel.getGameId());
		inParameters.put("AccessedGameId", queryModel.getAccessedGameId());
		inParameters.put("UserId", queryModel.getUserId());
		inParameters.put("AccessedDateBegin", Utility.getSqlDate(queryModel.getAccessedDateBegin()));
		inParameters.put("AccessedDateEnd", Utility.getSqlDate(queryModel.getAccessedDateEnd()));
		inParameters.put("AccessedDateYear", Utility.getDateYear(queryModel.getAccessedDate()));
		inParameters.put("AccessedDateMonth", Utility.getDateMonth(queryModel.getAccessedDate()));
		inParameters.put("AccessedDateDay", Utility.getDateDay(queryModel.getAccessedDate()));
		inParameters.put("LeaveDateBegin", Utility.getSqlDate(queryModel.getLeaveDateBegin()));
		inParameters.put("LeaveDateEnd", Utility.getSqlDate(queryModel.getLeaveDateEnd()));
		inParameters.put("OnLineDuration", queryModel.getOnLineDuration());
		inParameters.put("OnLineType", queryModel.getOnLineType());
		inParameters.put("CurrentContinuousDays", queryModel.getCurrentContinuousDays());
		inParameters.put("CurrentLoginTimes", queryModel.getCurrentLoginTimes());
		inParameters.put("CurrentLoginDays", queryModel.getCurrentLoginDays());
		inParameters.put("ContinuousServerDays", queryModel.getContinuousServerDays());
		inParameters.put("LoginServerTimes", queryModel.getLoginServerTimes());
		inParameters.put("ContinuousGameDays", queryModel.getContinuousGameDays());
		inParameters.put("LoginGameTimes", queryModel.getLoginGameTimes());
		inParameters.put("SourceIP", queryModel.getSourceIP());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Server_AccessedGameDetail_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除登录过的游戏详情
	 * 
	 * @param id 登录过的游戏详情编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("AccessedGameDetailId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Server_AccessedGameDetail_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改登录过的游戏详情状态
	 * 
	 * @param accessedGameDetailId 
	 *        登录过的游戏详情编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int accessedGameDetailId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("AccessedGameDetailId", accessedGameDetailId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Server_AccessedGameDetail_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取登录过的游戏详情
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.server.model.AccessedGameDetail getModel(ResultSet rs) throws SQLException{
		wh.server.model.AccessedGameDetail m = null;
		if (rs != null)
        {
            m = new wh.server.model.AccessedGameDetail();
			try{
				m.setAccessedGameDetailId(rs.getInt("AccessedGameDetailId"));
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
				
				wh.server.model.AccessedGame accessedGame = new wh.server.model.AccessedGame();
				accessedGame.setAccessedGameId(rs.getInt("AccessedGame_AccessedGameId"));
				accessedGame.setGame(new wh.game.model.Game());
				accessedGame.getGame().setGameId(rs.getInt("AccessedGame_GameId"));
				accessedGame.setServer(new wh.game.model.Server());
				accessedGame.getServer().setServerId(rs.getInt("AccessedGame_ServerId"));
				accessedGame.setGameCategory(new wh.game.model.GameCategory());
				accessedGame.getGameCategory().setGameCategoryId(rs.getInt("AccessedGame_GameCategoryId"));
				accessedGame.setUser(new wh.member.model.User());
				accessedGame.getUser().setUserId(rs.getInt("AccessedGame_UserId"));
				accessedGame.setLatestAccessedDate(rs.getTimestamp("AccessedGame_LatestAccessedDate"));
				accessedGame.setSourceIP(rs.getString("AccessedGame_SourceIP"));
				accessedGame.setStatus(rs.getByte("AccessedGame_Status"));
				m.setAccessedGame(accessedGame);
				
				wh.member.model.User user = new wh.member.model.User();
				user.setUserId(rs.getInt("User_UserId"));
				user.setManager(new wh.member.model.Manager());
				user.getManager().setManagerId(rs.getInt("User_ManagerId"));
				user.setChannel(new wh.promotion.model.Channel());
				user.getChannel().setChannelId(rs.getInt("User_ChannelId"));
				user.setUserPortrait(new wh.member.model.UserPortrait());
				user.getUserPortrait().setUserPortraitId(rs.getInt("User_UserPortraitId"));
				user.setStatisticAnalysis(new wh.promotion.model.StatisticAnalysis());
				user.getStatisticAnalysis().setStatisticAnalysisId(rs.getInt("User_StatisticAnalysisId"));
				user.setChannelSub(new wh.promotion.model.ChannelSub());
				user.getChannelSub().setChannelSubId(rs.getInt("User_ChannelSubId"));
				user.setUserName(rs.getString("User_UserName"));
				user.setPassword(rs.getString("User_Password"));
				user.setRealName(rs.getString("User_RealName"));
				user.setNickName(rs.getString("User_NickName"));
				user.setGenderType(rs.getByte("User_GenderType"));
				user.setBirthday(rs.getTimestamp("User_Birthday"));
				user.setChannelType(rs.getByte("User_ChannelType"));
				user.setSourceType(rs.getInt("User_SourceType"));
				user.setIDCard(rs.getString("User_IDCard"));
				user.setAddress(rs.getString("User_Address"));
				user.setPostcode(rs.getString("User_Postcode"));
				user.setPosterPath(rs.getString("User_PosterPath"));
				user.setEmail1(rs.getString("User_Email1"));
				user.setEmail2(rs.getString("User_Email2"));
				user.setTel(rs.getString("User_Tel"));
				user.setMobilePhone(rs.getString("User_MobilePhone"));
				user.setOtherMobilePhone(rs.getString("User_OtherMobilePhone"));
				user.setQQ(rs.getString("User_QQ"));
				user.setMSN(rs.getString("User_MSN"));
				user.setMemberRankType(rs.getInt("User_MemberRankType"));
				user.setCreateDate(rs.getTimestamp("User_CreateDate"));
				user.setModifyDate(rs.getTimestamp("User_ModifyDate"));
				user.setAuthenticationType(rs.getInt("User_AuthenticationType"));
				user.setEmailAuthenticationType(rs.getInt("User_EmailAuthenticationType"));
				user.setMobileAuthenticationType(rs.getInt("User_MobileAuthenticationType"));
				user.setIndulgenceAuthenticationType(rs.getInt("User_IndulgenceAuthenticationType"));
				user.setOnLineType(rs.getByte("User_OnLineType"));
				user.setMBRealName(rs.getString("User_MBRealName"));
				user.setMBIDCard(rs.getString("User_MBIDCard"));
				user.setMBMobilePhone(rs.getString("User_MBMobilePhone"));
				user.setMBEmail(rs.getString("User_MBEmail"));
				user.setPassordQuestionOneType(rs.getInt("User_PassordQuestionOneType"));
				user.setPassordQuestionOneValue(rs.getString("User_PassordQuestionOneValue"));
				user.setPassordAnswerOne(rs.getString("User_PassordAnswerOne"));
				user.setPassordQuestionTwoType(rs.getInt("User_PassordQuestionTwoType"));
				user.setPassordQuestionTwoValue(rs.getString("User_PassordQuestionTwoValue"));
				user.setPassordAnswerTwo(rs.getString("User_PassordAnswerTwo"));
				user.setStatus(rs.getByte("User_Status"));
				user.setRegisterGameId(rs.getInt("User_RegisterGameId"));
				user.setRegisterServerId(rs.getInt("User_RegisterServerId"));
				user.setMobileDevice(new wh.promotion.model.MobileDevice());
				user.getMobileDevice().setMobileDeviceId(rs.getInt("User_MobileDeviceId"));
				user.setRawPassword(rs.getString("User_RawPassword"));
				m.setUser(user);
				
				m.setAccessedDate(rs.getTimestamp("AccessedDate"));
				m.setLeaveDate(rs.getTimestamp("LeaveDate"));
				m.setOnLineDuration(rs.getInt("OnLineDuration"));
				m.setOnLineType(rs.getByte("OnLineType"));
				m.setCurrentContinuousDays(rs.getInt("CurrentContinuousDays"));
				m.setCurrentLoginTimes(rs.getInt("CurrentLoginTimes"));
				m.setCurrentLoginDays(rs.getInt("CurrentLoginDays"));
				m.setContinuousServerDays(rs.getInt("ContinuousServerDays"));
				m.setLoginServerTimes(rs.getInt("LoginServerTimes"));
				m.setContinuousGameDays(rs.getInt("ContinuousGameDays"));
				m.setLoginGameTimes(rs.getInt("LoginGameTimes"));
				m.setSourceIP(rs.getString("SourceIP"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


