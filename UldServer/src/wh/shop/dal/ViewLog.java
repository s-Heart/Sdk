﻿package wh.shop.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * ViewLog数据访问层
 */
public class ViewLog
{
	/**
	 * 创建更新浏览日志
	 * 
	 * @param model 
	 *        浏览日志
	 * @return 浏览日志编号
	 */
	public int createUpdate(wh.shop.model.ViewLog model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ViewLogId", model.getViewLogId());
		inParameters.put("UserId", model.getUserId());
		inParameters.put("GoodsId", model.getGoodsId());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(model.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(model.getCreateDate()));
		inParameters.put("CreateDateYear", Utility.getDateYear(model.getCreateDate()));
		
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
			"{? = call WH_Shop_ViewLog_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取浏览日志
	 * 
	 * @param id 
	 *        浏览日志编号
	 * @return 浏览日志
     */
	public wh.shop.model.ViewLog get(int id) throws Exception{
		wh.shop.model.ViewLog model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ViewLogId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_ViewLog_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取浏览日志列表
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
	 * @return 浏览日志列表
     */
	public List<wh.shop.model.ViewLog> getList(wh.shop.model.ViewLog queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.shop.model.ViewLog> list = new ArrayList<wh.shop.model.ViewLog>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("ViewLogId", queryModel.getViewLogId());
		inParameters.put("UserId", queryModel.getUserId());
		inParameters.put("GoodsId", queryModel.getGoodsId());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("CreateDateDay", Utility.getDateDay(queryModel.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(queryModel.getCreateDate()));
		inParameters.put("CreateDateYear", Utility.getDateYear(queryModel.getCreateDate()));
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_ViewLog_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除浏览日志
	 * 
	 * @param id 浏览日志编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ViewLogId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Shop_ViewLog_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取浏览日志
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.shop.model.ViewLog getModel(ResultSet rs) throws SQLException{
		wh.shop.model.ViewLog m = null;
		if (rs != null)
        {
            m = new wh.shop.model.ViewLog();
			try{
				m.setViewLogId(rs.getInt("ViewLogId"));
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
				
				wh.shop.model.Goods goods = new wh.shop.model.Goods();
				goods.setGoodsId(rs.getInt("Goods_GoodsId"));
				goods.setGoodsCategory(new wh.shop.model.GoodsCategory());
				goods.getGoodsCategory().setGoodsCategoryId(rs.getInt("Goods_GoodsCategoryId"));
				goods.setManager(new wh.member.model.Manager());
				goods.getManager().setManagerId(rs.getInt("Goods_ManagerId"));
				goods.setProduct(new wh.shop.model.Product());
				goods.getProduct().setProductId(rs.getInt("Goods_ProductId"));
				goods.setCategoryParentId(rs.getInt("Goods_CategoryParentId"));
				goods.setCategoryParentIdList(rs.getString("Goods_CategoryParentIdList"));
				goods.setName(rs.getString("Goods_Name"));
				goods.setGoodsDescription(rs.getString("Goods_GoodsDescription"));
				goods.setPaymentType(rs.getByte("Goods_PaymentType"));
				goods.setNeedDB(rs.getInt("Goods_NeedDB"));
				goods.setNeedFortune(rs.getInt("Goods_NeedFortune"));
				goods.setPrice(rs.getInt("Goods_Price"));
				goods.setStockAmount(rs.getInt("Goods_StockAmount"));
				goods.setSoldAmount(rs.getInt("Goods_SoldAmount"));
				goods.setClickAmount(rs.getInt("Goods_ClickAmount"));
				goods.setShareAmount(rs.getInt("Goods_ShareAmount"));
				goods.setColor(rs.getString("Goods_Color"));
				goods.setPosterPath(rs.getString("Goods_PosterPath"));
				goods.setReturnFortune(rs.getInt("Goods_ReturnFortune"));
				goods.setReturnPlat(rs.getInt("Goods_ReturnPlat"));
				goods.setActiveType(rs.getByte("Goods_ActiveType"));
				goods.setActivePlatPrice(rs.getInt("Goods_ActivePlatPrice"));
				goods.setActiveFortunePrice(rs.getInt("Goods_ActiveFortunePrice"));
				goods.setActiveStartDate(rs.getTimestamp("Goods_ActiveStartDate"));
				goods.setActiveEndDate(rs.getTimestamp("Goods_ActiveEndDate"));
				goods.setCreateDate(rs.getTimestamp("Goods_CreateDate"));
				goods.setModifyDate(rs.getTimestamp("Goods_ModifyDate"));
				goods.setRecommendType(rs.getByte("Goods_RecommendType"));
				goods.setStatus(rs.getByte("Goods_Status"));
				m.setGoods(goods);
				
				m.setCreateDate(rs.getTimestamp("CreateDate"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


