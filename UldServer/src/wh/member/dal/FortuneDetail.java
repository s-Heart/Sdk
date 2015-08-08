package wh.member.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * FortuneDetail数据访问层
 */
public class FortuneDetail
{
	/**
	 * 创建更新幸运点详情
	 * 
	 * @param model 
	 *        幸运点详情
	 * @return 幸运点详情编号
	 */
	public int createUpdate(wh.member.model.FortuneDetail model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("FortuneDetailId", model.getFortuneDetailId());
		inParameters.put("UserId", model.getUserId());
		inParameters.put("AccountDetailId", model.getAccountDetailId());
		inParameters.put("FortuneId", model.getFortuneId());
		inParameters.put("FortuneGetType", model.getFortuneGetType());
		inParameters.put("FortuneValue", model.getFortuneValue());
		inParameters.put("FortuneType", model.getFortuneType());
		inParameters.put("UseType", model.getUseType());
		inParameters.put("Remark", model.getRemark());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("Status", model.getStatus());
		inParameters.put("CreateDateYear", Utility.getDateYear(model.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(model.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(model.getCreateDate()));
		
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
			"{? = call WH_Member_FortuneDetail_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取幸运点详情
	 * 
	 * @param id 
	 *        幸运点详情编号
	 * @return 幸运点详情
     */
	public wh.member.model.FortuneDetail get(int id) throws Exception{
		wh.member.model.FortuneDetail model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("FortuneDetailId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_FortuneDetail_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取幸运点详情列表
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
	 * @return 幸运点详情列表
     */
	public List<wh.member.model.FortuneDetail> getList(wh.member.model.FortuneDetail queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.member.model.FortuneDetail> list = new ArrayList<wh.member.model.FortuneDetail>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("FortuneDetailId", queryModel.getFortuneDetailId());
		inParameters.put("UserId", queryModel.getUserId());
		inParameters.put("AccountDetailId", queryModel.getAccountDetailId());
		inParameters.put("FortuneId", queryModel.getFortuneId());
		inParameters.put("FortuneGetType", queryModel.getFortuneGetType());
		inParameters.put("FortuneValue", queryModel.getFortuneValue());
		inParameters.put("FortuneType", queryModel.getFortuneType());
		inParameters.put("UseType", queryModel.getUseType());
		inParameters.put("Remark", queryModel.getRemark());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("Status", queryModel.getStatus());
		inParameters.put("CreateDateYear", Utility.getDateYear(queryModel.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(queryModel.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(queryModel.getCreateDate()));
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_FortuneDetail_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除幸运点详情
	 * 
	 * @param id 幸运点详情编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("FortuneDetailId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_FortuneDetail_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改幸运点详情状态
	 * 
	 * @param fortuneDetailId 
	 *        幸运点详情编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int fortuneDetailId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("FortuneDetailId", fortuneDetailId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_FortuneDetail_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取幸运点详情
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.member.model.FortuneDetail getModel(ResultSet rs) throws SQLException{
		wh.member.model.FortuneDetail m = null;
		if (rs != null)
        {
            m = new wh.member.model.FortuneDetail();
			try{
				m.setFortuneDetailId(rs.getInt("FortuneDetailId"));
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
				
				wh.member.model.Fortune fortune = new wh.member.model.Fortune();
				fortune.setFortuneId(rs.getInt("Fortune_FortuneId"));
				fortune.setUser(new wh.member.model.User());
				fortune.getUser().setUserId(rs.getInt("Fortune_UserId"));
				fortune.setFortuneValue(rs.getInt("Fortune_FortuneValue"));
				fortune.setFortuneType(rs.getByte("Fortune_FortuneType"));
				fortune.setCreateDate(rs.getTimestamp("Fortune_CreateDate"));
				fortune.setModifyDate(rs.getTimestamp("Fortune_ModifyDate"));
				fortune.setStatus(rs.getByte("Fortune_Status"));
				m.setFortune(fortune);
				
				m.setFortuneGetType(rs.getByte("FortuneGetType"));
				m.setFortuneValue(rs.getInt("FortuneValue"));
				m.setFortuneType(rs.getByte("FortuneType"));
				m.setUseType(rs.getByte("UseType"));
				m.setRemark(rs.getString("Remark"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


