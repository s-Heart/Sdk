package wh.promotion.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * StatisticAnalysis数据访问层
 */
public class StatisticAnalysis
{
	/**
	 * 创建更新统计分析
	 * 
	 * @param model 
	 *        统计分析
	 * @return 统计分析编号
	 */
	public int createUpdate(wh.promotion.model.StatisticAnalysis model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("StatisticAnalysisId", model.getStatisticAnalysisId());
		inParameters.put("ChannelSubId", model.getChannelSubId());
		inParameters.put("ChannelId", model.getChannelId());
		inParameters.put("ChannelType", model.getChannelType());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("CreateDateYear", Utility.getDateYear(model.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(model.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(model.getCreateDate()));
		inParameters.put("MobileDeviceId", model.getMobileDeviceId());
		inParameters.put("GameId", model.getGameId());
		
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
			"{? = call WH_Promotion_StatisticAnalysis_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取统计分析
	 * 
	 * @param id 
	 *        统计分析编号
	 * @return 统计分析
     */
	public wh.promotion.model.StatisticAnalysis get(int id) throws Exception{
		wh.promotion.model.StatisticAnalysis model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("StatisticAnalysisId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_StatisticAnalysis_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取统计分析列表
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
	 * @return 统计分析列表
     */
	public List<wh.promotion.model.StatisticAnalysis> getList(wh.promotion.model.StatisticAnalysis queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.promotion.model.StatisticAnalysis> list = new ArrayList<wh.promotion.model.StatisticAnalysis>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("StatisticAnalysisId", queryModel.getStatisticAnalysisId());
		inParameters.put("ChannelSubId", queryModel.getChannelSubId());
		inParameters.put("ChannelId", queryModel.getChannelId());
		inParameters.put("ChannelType", queryModel.getChannelType());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("CreateDateYear", Utility.getDateYear(queryModel.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(queryModel.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(queryModel.getCreateDate()));
		inParameters.put("MobileDeviceId", queryModel.getMobileDeviceId());
		inParameters.put("GameId", queryModel.getGameId());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_StatisticAnalysis_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除统计分析
	 * 
	 * @param id 统计分析编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("StatisticAnalysisId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_StatisticAnalysis_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取统计分析
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.promotion.model.StatisticAnalysis getModel(ResultSet rs) throws SQLException{
		wh.promotion.model.StatisticAnalysis m = null;
		if (rs != null)
        {
            m = new wh.promotion.model.StatisticAnalysis();
			try{
				m.setStatisticAnalysisId(rs.getInt("StatisticAnalysisId"));
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
				
				m.setChannelType(rs.getByte("ChannelType"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
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
				
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


