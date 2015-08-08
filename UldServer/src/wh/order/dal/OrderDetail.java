package wh.order.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * OrderDetail数据访问层
 */
public class OrderDetail
{
	/**
	 * 创建更新订单详情
	 * 
	 * @param model 
	 *        订单详情
	 * @return 订单详情编号
	 */
	public int createUpdate(wh.order.model.OrderDetail model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderDetailId", model.getOrderDetailId());
		inParameters.put("UserId", model.getUserId());
		inParameters.put("OrderId", model.getOrderId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("PayAccount", model.getPayAccount());
		inParameters.put("PayType", model.getPayType());
		inParameters.put("CardType", model.getCardType());
		inParameters.put("CardNumber", model.getCardNumber());
		inParameters.put("Password", model.getPassword());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("ModifyDate", Utility.getSqlDate(model.getModifyDate()));
		inParameters.put("OrderType", model.getOrderType());
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
			"{? = call WH_Order_OrderDetail_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取订单详情
	 * 
	 * @param id 
	 *        订单详情编号
	 * @return 订单详情
     */
	public wh.order.model.OrderDetail get(int id) throws Exception{
		wh.order.model.OrderDetail model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderDetailId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Order_OrderDetail_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取订单详情列表
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
	 * @return 订单详情列表
     */
	public List<wh.order.model.OrderDetail> getList(wh.order.model.OrderDetail queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.order.model.OrderDetail> list = new ArrayList<wh.order.model.OrderDetail>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("OrderDetailId", queryModel.getOrderDetailId());
		inParameters.put("UserId", queryModel.getUserId());
		inParameters.put("OrderId", queryModel.getOrderId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("PayAccount", queryModel.getPayAccount());
		inParameters.put("PayType", queryModel.getPayType());
		inParameters.put("CardType", queryModel.getCardType());
		inParameters.put("CardNumber", queryModel.getCardNumber());
		inParameters.put("Password", queryModel.getPassword());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("ModifyDateBegin", Utility.getSqlDate(queryModel.getModifyDateBegin()));
		inParameters.put("ModifyDateEnd", Utility.getSqlDate(queryModel.getModifyDateEnd()));
		inParameters.put("OrderType", queryModel.getOrderType());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Order_OrderDetail_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除订单详情
	 * 
	 * @param id 订单详情编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderDetailId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Order_OrderDetail_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改订单详情状态
	 * 
	 * @param orderDetailId 
	 *        订单详情
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int orderDetailId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("OrderDetailId", orderDetailId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Order_OrderDetail_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取订单详情
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.order.model.OrderDetail getModel(ResultSet rs) throws SQLException{
		wh.order.model.OrderDetail m = null;
		if (rs != null)
        {
            m = new wh.order.model.OrderDetail();
			try{
				m.setOrderDetailId(rs.getInt("OrderDetailId"));
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
				
				wh.order.model.Order order = new wh.order.model.Order();
				order.setOrderId(rs.getInt("Order_OrderId"));
				order.setServer(new wh.game.model.Server());
				order.getServer().setServerId(rs.getInt("Order_ServerId"));
				order.setGame(new wh.game.model.Game());
				order.getGame().setGameId(rs.getInt("Order_GameId"));
				order.setManager(new wh.member.model.Manager());
				order.getManager().setManagerId(rs.getInt("Order_ManagerId"));
				order.setUser(new wh.member.model.User());
				order.getUser().setUserId(rs.getInt("Order_UserId"));
				order.setChargedUserId(rs.getInt("Order_ChargedUserId"));
				order.setChargedUserName(rs.getString("Order_ChargedUserName"));
				order.setPayType(rs.getByte("Order_PayType"));
				order.setPayAccount(rs.getBigDecimal("Order_PayAccount"));
				order.setAccountType(rs.getByte("Order_AccountType"));
				order.setRealName(rs.getString("Order_RealName"));
				order.setIDCard(rs.getString("Order_IDCard"));
				order.setMobilePhone(rs.getString("Order_MobilePhone"));
				order.setTel(rs.getString("Order_Tel"));
				order.setCreateDate(rs.getTimestamp("Order_CreateDate"));
				order.setModifyDate(rs.getTimestamp("Order_ModifyDate"));
				order.setOrderType(rs.getByte("Order_OrderType"));
				order.setChargedServerDays(rs.getInt("Order_ChargedServerDays"));
				order.setChargedGameDays(rs.getInt("Order_ChargedGameDays"));
				order.setChargedUserDays(rs.getInt("Order_ChargedUserDays"));
				order.setChargeServerTimes(rs.getInt("Order_ChargeServerTimes"));
				order.setChargeGameTimes(rs.getInt("Order_ChargeGameTimes"));
				order.setChargeUserTimes(rs.getInt("Order_ChargeUserTimes"));
				order.setStatus(rs.getByte("Order_Status"));
				order.setChargeType(rs.getByte("Order_ChargeType"));
				order.setCustomOrderId(rs.getLong("Order_CustomOrderId"));
				order.setRealPayAccount(rs.getBigDecimal("Order_RealPayAccount"));
				order.setPaySourceType(rs.getInt("Order_PaySourceType"));
				m.setOrder(order);
				
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
				
				m.setPayAccount(rs.getBigDecimal("PayAccount"));
				m.setPayType(rs.getByte("PayType"));
				m.setCardType(rs.getByte("CardType"));
				m.setCardNumber(rs.getString("CardNumber"));
				m.setPassword(rs.getString("Password"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
				m.setOrderType(rs.getByte("OrderType"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


