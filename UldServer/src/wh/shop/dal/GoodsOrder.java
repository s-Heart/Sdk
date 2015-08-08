package wh.shop.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * GoodsOrder数据访问层
 */
public class GoodsOrder
{
	/**
	 * 创建更新商品订单
	 * 
	 * @param model 
	 *        商品订单
	 * @return 商品订单编号
	 */
	public int createUpdate(wh.shop.model.GoodsOrder model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsOrderId", model.getGoodsOrderId());
		inParameters.put("FortuneDetailId", model.getFortuneDetailId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("UserId", model.getUserId());
		inParameters.put("AccountDetailId", model.getAccountDetailId());
		inParameters.put("PaymentType", model.getPaymentType());
		inParameters.put("NeedDB", model.getNeedDB());
		inParameters.put("NeedFortune", model.getNeedFortune());
		inParameters.put("Shipping", model.getShipping());
		inParameters.put("Remark", model.getRemark());
		inParameters.put("GoodsCount", model.getGoodsCount());
		inParameters.put("GoodsTotalCount", model.getGoodsTotalCount());
		inParameters.put("IDCard", model.getIDCard());
		inParameters.put("Postcode", model.getPostcode());
		inParameters.put("Address", model.getAddress());
		inParameters.put("Addressee", model.getAddressee());
		inParameters.put("MobilePhone", model.getMobilePhone());
		inParameters.put("Tel", model.getTel());
		inParameters.put("Email", model.getEmail());
		inParameters.put("QQ", model.getQQ());
		inParameters.put("MSN", model.getMSN());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("CreateDateYear", Utility.getDateYear(model.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(model.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(model.getCreateDate()));
		inParameters.put("ModifyDate", Utility.getSqlDate(model.getModifyDate()));
		inParameters.put("ModifyDateYear", Utility.getDateYear(model.getModifyDate()));
		inParameters.put("ModifyDateMonth", Utility.getDateMonth(model.getModifyDate()));
		inParameters.put("ModifyDateDay", Utility.getDateDay(model.getModifyDate()));
		inParameters.put("ShopOrderType", model.getShopOrderType());
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
			"{? = call WH_Shop_GoodsOrder_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取商品订单
	 * 
	 * @param id 
	 *        商品订单编号
	 * @return 商品订单
     */
	public wh.shop.model.GoodsOrder get(int id) throws Exception{
		wh.shop.model.GoodsOrder model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsOrderId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_GoodsOrder_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取商品订单列表
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
	 * @return 商品订单列表
     */
	public List<wh.shop.model.GoodsOrder> getList(wh.shop.model.GoodsOrder queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.shop.model.GoodsOrder> list = new ArrayList<wh.shop.model.GoodsOrder>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("GoodsOrderId", queryModel.getGoodsOrderId());
		inParameters.put("FortuneDetailId", queryModel.getFortuneDetailId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("UserId", queryModel.getUserId());
		inParameters.put("AccountDetailId", queryModel.getAccountDetailId());
		inParameters.put("PaymentType", queryModel.getPaymentType());
		inParameters.put("NeedDB", queryModel.getNeedDB());
		inParameters.put("NeedFortune", queryModel.getNeedFortune());
		inParameters.put("Shipping", queryModel.getShipping());
		inParameters.put("Remark", queryModel.getRemark());
		inParameters.put("GoodsCount", queryModel.getGoodsCount());
		inParameters.put("GoodsTotalCount", queryModel.getGoodsTotalCount());
		inParameters.put("IDCard", queryModel.getIDCard());
		inParameters.put("Postcode", queryModel.getPostcode());
		inParameters.put("Address", queryModel.getAddress());
		inParameters.put("Addressee", queryModel.getAddressee());
		inParameters.put("MobilePhone", queryModel.getMobilePhone());
		inParameters.put("Tel", queryModel.getTel());
		inParameters.put("Email", queryModel.getEmail());
		inParameters.put("QQ", queryModel.getQQ());
		inParameters.put("MSN", queryModel.getMSN());
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
		inParameters.put("ShopOrderType", queryModel.getShopOrderType());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_GoodsOrder_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除商品订单
	 * 
	 * @param id 商品订单编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsOrderId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Shop_GoodsOrder_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改商品订单状态
	 * 
	 * @param goodsOrderId 
	 *        商品订单编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int goodsOrderId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsOrderId", goodsOrderId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Shop_GoodsOrder_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取商品订单
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.shop.model.GoodsOrder getModel(ResultSet rs) throws SQLException{
		wh.shop.model.GoodsOrder m = null;
		if (rs != null)
        {
            m = new wh.shop.model.GoodsOrder();
			try{
				m.setGoodsOrderId(rs.getInt("GoodsOrderId"));
				wh.member.model.FortuneDetail fortuneDetail = new wh.member.model.FortuneDetail();
				fortuneDetail.setFortuneDetailId(rs.getInt("FortuneDetail_FortuneDetailId"));
				fortuneDetail.setUser(new wh.member.model.User());
				fortuneDetail.getUser().setUserId(rs.getInt("FortuneDetail_UserId"));
				fortuneDetail.setAccountDetail(new wh.member.model.AccountDetail());
				fortuneDetail.getAccountDetail().setAccountDetailId(rs.getInt("FortuneDetail_AccountDetailId"));
				fortuneDetail.setFortune(new wh.member.model.Fortune());
				fortuneDetail.getFortune().setFortuneId(rs.getInt("FortuneDetail_FortuneId"));
				fortuneDetail.setFortuneGetType(rs.getByte("FortuneDetail_FortuneGetType"));
				fortuneDetail.setFortuneValue(rs.getInt("FortuneDetail_FortuneValue"));
				fortuneDetail.setFortuneType(rs.getByte("FortuneDetail_FortuneType"));
				fortuneDetail.setUseType(rs.getByte("FortuneDetail_UseType"));
				fortuneDetail.setRemark(rs.getString("FortuneDetail_Remark"));
				fortuneDetail.setCreateDate(rs.getTimestamp("FortuneDetail_CreateDate"));
				fortuneDetail.setStatus(rs.getByte("FortuneDetail_Status"));
				m.setFortuneDetail(fortuneDetail);
				
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
				
				wh.member.model.AccountDetail accountDetail = new wh.member.model.AccountDetail();
				accountDetail.setAccountDetailId(rs.getInt("AccountDetail_AccountDetailId"));
				accountDetail.setUser(new wh.member.model.User());
				accountDetail.getUser().setUserId(rs.getInt("AccountDetail_UserId"));
				accountDetail.setServerAccount(new wh.server.model.ServerAccount());
				accountDetail.getServerAccount().setServerAccountId(rs.getInt("AccountDetail_ServerAccountId"));
				accountDetail.setOrder(new wh.order.model.Order());
				accountDetail.getOrder().setOrderId(rs.getInt("AccountDetail_OrderId"));
				accountDetail.setAccount(new wh.member.model.Account());
				accountDetail.getAccount().setAccountId(rs.getInt("AccountDetail_AccountId"));
				accountDetail.setAccountValue(rs.getBigDecimal("AccountDetail_AccountValue"));
				accountDetail.setUseType(rs.getByte("AccountDetail_UseType"));
				accountDetail.setMobilePhone(rs.getString("AccountDetail_MobilePhone"));
				accountDetail.setCreateDate(rs.getTimestamp("AccountDetail_CreateDate"));
				accountDetail.setModifyDate(rs.getTimestamp("AccountDetail_ModifyDate"));
				accountDetail.setStatus(rs.getByte("AccountDetail_Status"));
				m.setAccountDetail(accountDetail);
				
				m.setPaymentType(rs.getByte("PaymentType"));
				m.setNeedDB(rs.getInt("NeedDB"));
				m.setNeedFortune(rs.getInt("NeedFortune"));
				m.setShipping(rs.getInt("Shipping"));
				m.setRemark(rs.getString("Remark"));
				m.setGoodsCount(rs.getInt("GoodsCount"));
				m.setGoodsTotalCount(rs.getInt("GoodsTotalCount"));
				m.setIDCard(rs.getString("IDCard"));
				m.setPostcode(rs.getString("Postcode"));
				m.setAddress(rs.getString("Address"));
				m.setAddressee(rs.getString("Addressee"));
				m.setMobilePhone(rs.getString("MobilePhone"));
				m.setTel(rs.getString("Tel"));
				m.setEmail(rs.getString("Email"));
				m.setQQ(rs.getString("QQ"));
				m.setMSN(rs.getString("MSN"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
				m.setShopOrderType(rs.getInt("ShopOrderType"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


