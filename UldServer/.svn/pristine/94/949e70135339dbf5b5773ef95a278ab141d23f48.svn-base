package wh.order.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Order数据访问层
 */
public class Order
{
	/**
	 * 创建更新订单
	 * 
	 * @param model 
	 *        订单
	 * @return 订单编号
	 */
	public int createUpdate(wh.order.model.Order model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderId", model.getOrderId());
		inParameters.put("ServerId", model.getServerId());
		inParameters.put("GameId", model.getGameId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("UserId", model.getUserId());
		inParameters.put("ChargedUserId", model.getChargedUserId());
		inParameters.put("ChargedUserName", model.getChargedUserName());
		inParameters.put("PayType", model.getPayType());
		inParameters.put("PayAccount", model.getPayAccount());
		inParameters.put("AccountType", model.getAccountType());
		inParameters.put("RealName", model.getRealName());
		inParameters.put("IDCard", model.getIDCard());
		inParameters.put("MobilePhone", model.getMobilePhone());
		inParameters.put("Tel", model.getTel());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("CreateDateYear", Utility.getDateYear(model.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(model.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(model.getCreateDate()));
		inParameters.put("ModifyDate", Utility.getSqlDate(model.getModifyDate()));
		inParameters.put("ModifyDateYear", Utility.getDateYear(model.getModifyDate()));
		inParameters.put("ModifyDateMonth", Utility.getDateMonth(model.getModifyDate()));
		inParameters.put("ModifyDateDay", Utility.getDateDay(model.getModifyDate()));
		inParameters.put("OrderType", model.getOrderType());
		inParameters.put("ChargedServerDays", model.getChargedServerDays());
		inParameters.put("ChargedGameDays", model.getChargedGameDays());
		inParameters.put("ChargedUserDays", model.getChargedUserDays());
		inParameters.put("ChargeServerTimes", model.getChargeServerTimes());
		inParameters.put("ChargeGameTimes", model.getChargeGameTimes());
		inParameters.put("ChargeUserTimes", model.getChargeUserTimes());
		inParameters.put("Status", model.getStatus());
		inParameters.put("ChargeType", model.getChargeType());
		inParameters.put("CustomOrderId", model.getCustomOrderId());
		inParameters.put("RealPayAccount", model.getRealPayAccount());
		inParameters.put("PaySourceType", model.getPaySourceType());
		
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
			"{? = call WH_Order_Order_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取订单
	 * 
	 * @param id 
	 *        订单编号
	 * @return 订单
     */
	public wh.order.model.Order get(int id) throws Exception{
		wh.order.model.Order model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Order_Order_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取订单列表
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
	 * @return 订单列表
     */
	public List<wh.order.model.Order> getList(wh.order.model.Order queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.order.model.Order> list = new ArrayList<wh.order.model.Order>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("OrderId", queryModel.getOrderId());
		inParameters.put("ServerId", queryModel.getServerId());
		inParameters.put("GameId", queryModel.getGameId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("UserId", queryModel.getUserId());
		inParameters.put("ChargedUserId", queryModel.getChargedUserId());
		inParameters.put("ChargedUserName", queryModel.getChargedUserName());
		inParameters.put("PayType", queryModel.getPayType());
		inParameters.put("PayAccount", queryModel.getPayAccount());
		inParameters.put("AccountType", queryModel.getAccountType());
		inParameters.put("RealName", queryModel.getRealName());
		inParameters.put("IDCard", queryModel.getIDCard());
		inParameters.put("MobilePhone", queryModel.getMobilePhone());
		inParameters.put("Tel", queryModel.getTel());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("CreateDateYear", Utility.getDateYear(queryModel.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(queryModel.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(queryModel.getCreateDate()));
		inParameters.put("ModifyDateBegin", Utility.getSqlDate(queryModel.getModifyDateBegin()));
		inParameters.put("ModifyDateEnd", Utility.getSqlDate(queryModel.getModifyDateEnd()));
		inParameters.put("ModifyDateYear", Utility.getDateYear(queryModel.getModifyDate()));
		inParameters.put("ModifyDateMonth", Utility.getDateMonth(queryModel.getModifyDate()));
		inParameters.put("ModifyDateDay", Utility.getDateDay(queryModel.getModifyDate()));
		inParameters.put("OrderType", queryModel.getOrderType());
		inParameters.put("ChargedServerDays", queryModel.getChargedServerDays());
		inParameters.put("ChargedGameDays", queryModel.getChargedGameDays());
		inParameters.put("ChargedUserDays", queryModel.getChargedUserDays());
		inParameters.put("ChargeServerTimes", queryModel.getChargeServerTimes());
		inParameters.put("ChargeGameTimes", queryModel.getChargeGameTimes());
		inParameters.put("ChargeUserTimes", queryModel.getChargeUserTimes());
		inParameters.put("Status", queryModel.getStatus());
		inParameters.put("ChargeType", queryModel.getChargeType());
		inParameters.put("CustomOrderId", queryModel.getCustomOrderId());
		inParameters.put("RealPayAccount", queryModel.getRealPayAccount());
		inParameters.put("PaySourceType", queryModel.getPaySourceType());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Order_Order_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除订单
	 * 
	 * @param id 订单编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Order_Order_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改订单状态
	 * 
	 * @param orderId 
	 *        订单编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int orderId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderId", orderId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Order_Order_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取订单
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.order.model.Order getModel(ResultSet rs) throws SQLException{
		wh.order.model.Order m = null;
		if (rs != null)
        {
            m = new wh.order.model.Order();
			try{
				m.setOrderId(rs.getInt("OrderId"));
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
				
				m.setChargedUserId(rs.getInt("ChargedUserId"));
				m.setChargedUserName(rs.getString("ChargedUserName"));
				m.setPayType(rs.getByte("PayType"));
				m.setPayAccount(rs.getBigDecimal("PayAccount"));
				m.setAccountType(rs.getByte("AccountType"));
				m.setRealName(rs.getString("RealName"));
				m.setIDCard(rs.getString("IDCard"));
				m.setMobilePhone(rs.getString("MobilePhone"));
				m.setTel(rs.getString("Tel"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
				m.setOrderType(rs.getByte("OrderType"));
				m.setChargedServerDays(rs.getInt("ChargedServerDays"));
				m.setChargedGameDays(rs.getInt("ChargedGameDays"));
				m.setChargedUserDays(rs.getInt("ChargedUserDays"));
				m.setChargeServerTimes(rs.getInt("ChargeServerTimes"));
				m.setChargeGameTimes(rs.getInt("ChargeGameTimes"));
				m.setChargeUserTimes(rs.getInt("ChargeUserTimes"));
				m.setStatus(rs.getByte("Status"));
				m.setChargeType(rs.getByte("ChargeType"));
				m.setCustomOrderId(rs.getLong("CustomOrderId"));
				m.setRealPayAccount(rs.getBigDecimal("RealPayAccount"));
				m.setPaySourceType(rs.getInt("PaySourceType"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


