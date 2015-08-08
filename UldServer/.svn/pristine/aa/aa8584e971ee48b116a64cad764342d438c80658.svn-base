package wh.member.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * User数据访问层
 */
public class User
{
	/**
	 * 创建更新用户
	 * 
	 * @param model 
	 *        用户
	 * @return 用户编号
	 */
	public int createUpdate(wh.member.model.User model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("UserId", model.getUserId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("ChannelId", model.getChannelId());
		inParameters.put("UserPortraitId", model.getUserPortraitId());
		inParameters.put("StatisticAnalysisId", model.getStatisticAnalysisId());
		inParameters.put("ChannelSubId", model.getChannelSubId());
		inParameters.put("UserName", model.getUserName());
		inParameters.put("Password", model.getPassword());
		inParameters.put("RealName", model.getRealName());
		inParameters.put("NickName", model.getNickName());
		inParameters.put("GenderType", model.getGenderType());
		inParameters.put("Birthday", Utility.getSqlDate(model.getBirthday()));
		inParameters.put("ChannelType", model.getChannelType());
		inParameters.put("SourceType", model.getSourceType());
		inParameters.put("IDCard", model.getIDCard());
		inParameters.put("Address", model.getAddress());
		inParameters.put("Postcode", model.getPostcode());
		inParameters.put("PosterPath", model.getPosterPath());
		inParameters.put("Email1", model.getEmail1());
		inParameters.put("Email2", model.getEmail2());
		inParameters.put("Tel", model.getTel());
		inParameters.put("MobilePhone", model.getMobilePhone());
		inParameters.put("OtherMobilePhone", model.getOtherMobilePhone());
		inParameters.put("QQ", model.getQQ());
		inParameters.put("MSN", model.getMSN());
		inParameters.put("MemberRankType", model.getMemberRankType());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("CreateDateYear", Utility.getDateYear(model.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(model.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(model.getCreateDate()));
		inParameters.put("ModifyDate", Utility.getSqlDate(model.getModifyDate()));
		inParameters.put("AuthenticationType", model.getAuthenticationType());
		inParameters.put("EmailAuthenticationType", model.getEmailAuthenticationType());
		inParameters.put("MobileAuthenticationType", model.getMobileAuthenticationType());
		inParameters.put("IndulgenceAuthenticationType", model.getIndulgenceAuthenticationType());
		inParameters.put("OnLineType", model.getOnLineType());
		inParameters.put("MBRealName", model.getMBRealName());
		inParameters.put("MBIDCard", model.getMBIDCard());
		inParameters.put("MBMobilePhone", model.getMBMobilePhone());
		inParameters.put("MBEmail", model.getMBEmail());
		inParameters.put("PassordQuestionOneType", model.getPassordQuestionOneType());
		inParameters.put("PassordQuestionOneValue", model.getPassordQuestionOneValue());
		inParameters.put("PassordAnswerOne", model.getPassordAnswerOne());
		inParameters.put("PassordQuestionTwoType", model.getPassordQuestionTwoType());
		inParameters.put("PassordQuestionTwoValue", model.getPassordQuestionTwoValue());
		inParameters.put("PassordAnswerTwo", model.getPassordAnswerTwo());
		inParameters.put("Status", model.getStatus());
		inParameters.put("RegisterGameId", model.getRegisterGameId());
		inParameters.put("RegisterServerId", model.getRegisterServerId());
		inParameters.put("MobileDeviceId", model.getMobileDeviceId());
		inParameters.put("RawPassword", model.getRawPassword());
		
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
			"{? = call WH_Member_User_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取用户
	 * 
	 * @param id 
	 *        用户编号
	 * @return 用户
     */
	public wh.member.model.User get(int id) throws Exception{
		wh.member.model.User model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("UserId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_User_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取用户列表
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
	 * @return 用户列表
     */
	public List<wh.member.model.User> getList(wh.member.model.User queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.member.model.User> list = new ArrayList<wh.member.model.User>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("UserId", queryModel.getUserId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("ChannelId", queryModel.getChannelId());
		inParameters.put("UserPortraitId", queryModel.getUserPortraitId());
		inParameters.put("StatisticAnalysisId", queryModel.getStatisticAnalysisId());
		inParameters.put("ChannelSubId", queryModel.getChannelSubId());
		inParameters.put("UserName", queryModel.getUserName());
		inParameters.put("Password", queryModel.getPassword());
		inParameters.put("RealName", queryModel.getRealName());
		inParameters.put("NickName", queryModel.getNickName());
		inParameters.put("GenderType", queryModel.getGenderType());
		inParameters.put("BirthdayBegin", Utility.getSqlDate(queryModel.getBirthdayBegin()));
		inParameters.put("BirthdayEnd", Utility.getSqlDate(queryModel.getBirthdayEnd()));
		inParameters.put("ChannelType", queryModel.getChannelType());
		inParameters.put("SourceType", queryModel.getSourceType());
		inParameters.put("IDCard", queryModel.getIDCard());
		inParameters.put("Address", queryModel.getAddress());
		inParameters.put("Postcode", queryModel.getPostcode());
		inParameters.put("PosterPath", queryModel.getPosterPath());
		inParameters.put("Email1", queryModel.getEmail1());
		inParameters.put("Email2", queryModel.getEmail2());
		inParameters.put("Tel", queryModel.getTel());
		inParameters.put("MobilePhone", queryModel.getMobilePhone());
		inParameters.put("OtherMobilePhone", queryModel.getOtherMobilePhone());
		inParameters.put("QQ", queryModel.getQQ());
		inParameters.put("MSN", queryModel.getMSN());
		inParameters.put("MemberRankType", queryModel.getMemberRankType());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("CreateDateYear", Utility.getDateYear(queryModel.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(queryModel.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(queryModel.getCreateDate()));
		inParameters.put("ModifyDateBegin", Utility.getSqlDate(queryModel.getModifyDateBegin()));
		inParameters.put("ModifyDateEnd", Utility.getSqlDate(queryModel.getModifyDateEnd()));
		inParameters.put("AuthenticationType", queryModel.getAuthenticationType());
		inParameters.put("EmailAuthenticationType", queryModel.getEmailAuthenticationType());
		inParameters.put("MobileAuthenticationType", queryModel.getMobileAuthenticationType());
		inParameters.put("IndulgenceAuthenticationType", queryModel.getIndulgenceAuthenticationType());
		inParameters.put("OnLineType", queryModel.getOnLineType());
		inParameters.put("MBRealName", queryModel.getMBRealName());
		inParameters.put("MBIDCard", queryModel.getMBIDCard());
		inParameters.put("MBMobilePhone", queryModel.getMBMobilePhone());
		inParameters.put("MBEmail", queryModel.getMBEmail());
		inParameters.put("PassordQuestionOneType", queryModel.getPassordQuestionOneType());
		inParameters.put("PassordQuestionOneValue", queryModel.getPassordQuestionOneValue());
		inParameters.put("PassordAnswerOne", queryModel.getPassordAnswerOne());
		inParameters.put("PassordQuestionTwoType", queryModel.getPassordQuestionTwoType());
		inParameters.put("PassordQuestionTwoValue", queryModel.getPassordQuestionTwoValue());
		inParameters.put("PassordAnswerTwo", queryModel.getPassordAnswerTwo());
		inParameters.put("Status", queryModel.getStatus());
		inParameters.put("RegisterGameId", queryModel.getRegisterGameId());
		inParameters.put("RegisterServerId", queryModel.getRegisterServerId());
		inParameters.put("MobileDeviceId", queryModel.getMobileDeviceId());
		inParameters.put("RawPassword", queryModel.getRawPassword());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_User_GetList(" + sbBuilder.toString() + ")}",
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
	 * 检查用户名称是否存在
	 * 
	 * @param userName 
	 *        用户名称
	 * @return boolean
	 */
	public void checkUserName(String userName) throws Exception {
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("UserName", userName);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_User_CheckUserName(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}

	/**
	 * 获取用户
	 * 
	 * @param userName 
	 *        用户名称
	 * @param password 
	 *        密码
	 * @return 用户
	 */
	public wh.member.model.User login(String userName, String password) throws Exception {
		wh.member.model.User model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("UserName", userName);
		inParameters.put("Password", password);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_User_Login(?,?,?,?)}", inParameters, outParameters);
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
	 * 修改密码
	 * 
	 * @param userName
	 *        用户名称
	 * @param oldPassword
	 *        旧密码
	 * @param newPassword
	 *        新密码
	 * @return boolean
	 */
	public void modifyPassword(String userName, String oldPassword, String newPassword) throws Exception {
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("UserName", userName);
		inParameters.put("OldPassword", oldPassword);
		inParameters.put("NewPassword", newPassword);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_User_ModifyPassword(?,?,?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 删除用户
	 * 
	 * @param id 用户编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("UserId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_User_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取用户根据用户名称
	 * 
	 * @param UserName 
	 *        用户名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @return 用户
	 */
	public wh.member.model.User getByName(String userName, String fldSort, int sortType) throws Exception{
		wh.member.model.User model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("UserName", userName);
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_User_GetByName(?,?,?,?,?)}", inParameters, outParameters);
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
	 * 获取用户列表根据用户名称
	 * 
	 * @param UserName 
	 *        用户名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @return 用户列表
	 */
	public List<wh.member.model.User> getListByName(String userName, String fldSort, int sortType, int queryCount) throws Exception{
		List<wh.member.model.User> list = new ArrayList<wh.member.model.User>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("UserName", userName);
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Member_User_GetListByName(?,?,?,?,?,?)}", inParameters, outParameters);
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
	 * 修改用户状态
	 * 
	 * @param userId 
	 *        用户编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int userId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("UserId", userId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Member_User_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取用户
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.member.model.User getModel(ResultSet rs) throws SQLException{
		wh.member.model.User m = null;
		if (rs != null)
        {
            m = new wh.member.model.User();
			try{
				m.setUserId(rs.getInt("UserId"));
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
				
				wh.promotion.model.Channel channel = new wh.promotion.model.Channel();
				channel.setChannelId(rs.getInt("Channel_ChannelId"));
				channel.setManager(new wh.member.model.Manager());
				channel.getManager().setManagerId(rs.getInt("Channel_ManagerId"));
				channel.setChannelType(rs.getByte("Channel_ChannelType"));
				channel.setChannelName(rs.getString("Channel_ChannelName"));
				channel.setCreateDate(rs.getTimestamp("Channel_CreateDate"));
				channel.setModifyDate(rs.getTimestamp("Channel_ModifyDate"));
				channel.setStatus(rs.getByte("Channel_Status"));
				channel.setCode(rs.getString("Channel_Code"));
				m.setChannel(channel);
				
				wh.member.model.UserPortrait userPortrait = new wh.member.model.UserPortrait();
				userPortrait.setUserPortraitId(rs.getInt("UserPortrait_UserPortraitId"));
				userPortrait.setTitle(rs.getString("UserPortrait_Title"));
				userPortrait.setPosterPath(rs.getString("UserPortrait_PosterPath"));
				userPortrait.setStatus(rs.getByte("UserPortrait_Status"));
				m.setUserPortrait(userPortrait);
				
				wh.promotion.model.StatisticAnalysis statisticAnalysis = new wh.promotion.model.StatisticAnalysis();
				statisticAnalysis.setStatisticAnalysisId(rs.getInt("StatisticAnalysis_StatisticAnalysisId"));
				statisticAnalysis.setChannelSub(new wh.promotion.model.ChannelSub());
				statisticAnalysis.getChannelSub().setChannelSubId(rs.getInt("StatisticAnalysis_ChannelSubId"));
				statisticAnalysis.setChannel(new wh.promotion.model.Channel());
				statisticAnalysis.getChannel().setChannelId(rs.getInt("StatisticAnalysis_ChannelId"));
				statisticAnalysis.setChannelType(rs.getByte("StatisticAnalysis_ChannelType"));
				statisticAnalysis.setCreateDate(rs.getTimestamp("StatisticAnalysis_CreateDate"));
				statisticAnalysis.setMobileDevice(new wh.promotion.model.MobileDevice());
				statisticAnalysis.getMobileDevice().setMobileDeviceId(rs.getInt("StatisticAnalysis_MobileDeviceId"));
				statisticAnalysis.setGame(new wh.game.model.Game());
				statisticAnalysis.getGame().setGameId(rs.getInt("StatisticAnalysis_GameId"));
				m.setStatisticAnalysis(statisticAnalysis);
				
				wh.promotion.model.ChannelSub channelSub = new wh.promotion.model.ChannelSub();
				channelSub.setChannelSubId(rs.getInt("ChannelSub_ChannelSubId"));
				channelSub.setManager(new wh.member.model.Manager());
				channelSub.getManager().setManagerId(rs.getInt("ChannelSub_ManagerId"));
				channelSub.setChannel(new wh.promotion.model.Channel());
				channelSub.getChannel().setChannelId(rs.getInt("ChannelSub_ChannelId"));
				channelSub.setChannelType(rs.getByte("ChannelSub_ChannelType"));
				channelSub.setChannelSubName(rs.getString("ChannelSub_ChannelSubName"));
				channelSub.setCreateDate(rs.getTimestamp("ChannelSub_CreateDate"));
				channelSub.setModifyDate(rs.getTimestamp("ChannelSub_ModifyDate"));
				channelSub.setStatus(rs.getByte("ChannelSub_Status"));
				channelSub.setCode(rs.getString("ChannelSub_Code"));
				m.setChannelSub(channelSub);
				
				m.setUserName(rs.getString("UserName"));
				m.setPassword(rs.getString("Password"));
				m.setRealName(rs.getString("RealName"));
				m.setNickName(rs.getString("NickName"));
				m.setGenderType(rs.getByte("GenderType"));
				m.setBirthday(rs.getTimestamp("Birthday"));
				m.setChannelType(rs.getByte("ChannelType"));
				m.setSourceType(rs.getInt("SourceType"));
				m.setIDCard(rs.getString("IDCard"));
				m.setAddress(rs.getString("Address"));
				m.setPostcode(rs.getString("Postcode"));
				m.setPosterPath(rs.getString("PosterPath"));
				m.setEmail1(rs.getString("Email1"));
				m.setEmail2(rs.getString("Email2"));
				m.setTel(rs.getString("Tel"));
				m.setMobilePhone(rs.getString("MobilePhone"));
				m.setOtherMobilePhone(rs.getString("OtherMobilePhone"));
				m.setQQ(rs.getString("QQ"));
				m.setMSN(rs.getString("MSN"));
				m.setMemberRankType(rs.getInt("MemberRankType"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
				m.setAuthenticationType(rs.getInt("AuthenticationType"));
				m.setEmailAuthenticationType(rs.getInt("EmailAuthenticationType"));
				m.setMobileAuthenticationType(rs.getInt("MobileAuthenticationType"));
				m.setIndulgenceAuthenticationType(rs.getInt("IndulgenceAuthenticationType"));
				m.setOnLineType(rs.getByte("OnLineType"));
				m.setMBRealName(rs.getString("MBRealName"));
				m.setMBIDCard(rs.getString("MBIDCard"));
				m.setMBMobilePhone(rs.getString("MBMobilePhone"));
				m.setMBEmail(rs.getString("MBEmail"));
				m.setPassordQuestionOneType(rs.getInt("PassordQuestionOneType"));
				m.setPassordQuestionOneValue(rs.getString("PassordQuestionOneValue"));
				m.setPassordAnswerOne(rs.getString("PassordAnswerOne"));
				m.setPassordQuestionTwoType(rs.getInt("PassordQuestionTwoType"));
				m.setPassordQuestionTwoValue(rs.getString("PassordQuestionTwoValue"));
				m.setPassordAnswerTwo(rs.getString("PassordAnswerTwo"));
				m.setStatus(rs.getByte("Status"));
				m.setRegisterGameId(rs.getInt("RegisterGameId"));
				m.setRegisterServerId(rs.getInt("RegisterServerId"));
				wh.promotion.model.MobileDevice mobileDevice = new wh.promotion.model.MobileDevice();
				mobileDevice.setMobileDeviceId(rs.getInt("MobileDevice_MobileDeviceId"));
				mobileDevice.setMobileDeviceName(rs.getString("MobileDevice_MobileDeviceName"));
				mobileDevice.setOS(rs.getString("MobileDevice_OS"));
				mobileDevice.setOSModel(rs.getString("MobileDevice_OSModel"));
				mobileDevice.setMobilePhone(rs.getString("MobileDevice_MobilePhone"));
				mobileDevice.setDeviceBrand(rs.getString("MobileDevice_DeviceBrand"));
				mobileDevice.setDeviceProduct(rs.getString("MobileDevice_DeviceProduct"));
				mobileDevice.setRemark(rs.getString("MobileDevice_Remark"));
				mobileDevice.setCreateDate(rs.getTimestamp("MobileDevice_CreateDate"));
				mobileDevice.setModifyDate(rs.getTimestamp("MobileDevice_ModifyDate"));
				m.setMobileDevice(mobileDevice);
				
				m.setRawPassword(rs.getString("RawPassword"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


