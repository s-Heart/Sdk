package wh.promotion.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * PosterChannel数据访问层
 */
public class PosterChannel
{
	/**
	 * 创建更新海报渠道
	 * 
	 * @param model 
	 *        海报渠道
	 * @return 海报渠道编号
	 */
	public int createUpdate(wh.promotion.model.PosterChannel model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("PosterChannelId", model.getPosterChannelId());
		inParameters.put("ServerId", model.getServerId());
		inParameters.put("GameId", model.getGameId());
		inParameters.put("ChannelSubId", model.getChannelSubId());
		inParameters.put("PosterId", model.getPosterId());
		inParameters.put("ChannelId", model.getChannelId());
		inParameters.put("Name", model.getName());
		inParameters.put("Code", model.getCode());
		inParameters.put("PosterUrl", model.getPosterUrl());
		inParameters.put("PosterAddress", model.getPosterAddress());
		inParameters.put("FlashPath", model.getFlashPath());
		inParameters.put("RegisterLoginCode", model.getRegisterLoginCode());
		inParameters.put("MyStatisticCode", model.getMyStatisticCode());
		inParameters.put("OtherStatisticCode", model.getOtherStatisticCode());
		inParameters.put("ExtendedCost", model.getExtendedCost());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("ModifyDate", Utility.getSqlDate(model.getModifyDate()));
		inParameters.put("Status", model.getStatus());
		inParameters.put("DomainId", model.getDomainId());
		inParameters.put("RegTemplateId", model.getRegTemplateId());
		inParameters.put("RegisterCodeType", model.getRegisterCodeType());
		inParameters.put("TitleType", model.getTitleType());
		inParameters.put("StaticTitle", model.getStaticTitle());
		inParameters.put("DynamicTitle", model.getDynamicTitle());
		inParameters.put("KeyWord", model.getKeyWord());
		inParameters.put("HtmlDesc", model.getHtmlDesc());
		inParameters.put("BackgroundColor", model.getBackgroundColor());
		
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
			"{? = call WH_Promotion_PosterChannel_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取海报渠道
	 * 
	 * @param id 
	 *        海报渠道编号
	 * @return 海报渠道
     */
	public wh.promotion.model.PosterChannel get(int id) throws Exception{
		wh.promotion.model.PosterChannel model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("PosterChannelId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_PosterChannel_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取海报渠道列表
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
	 * @return 海报渠道列表
     */
	public List<wh.promotion.model.PosterChannel> getList(wh.promotion.model.PosterChannel queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.promotion.model.PosterChannel> list = new ArrayList<wh.promotion.model.PosterChannel>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("PosterChannelId", queryModel.getPosterChannelId());
		inParameters.put("ServerId", queryModel.getServerId());
		inParameters.put("GameId", queryModel.getGameId());
		inParameters.put("ChannelSubId", queryModel.getChannelSubId());
		inParameters.put("PosterId", queryModel.getPosterId());
		inParameters.put("ChannelId", queryModel.getChannelId());
		inParameters.put("Name", queryModel.getName());
		inParameters.put("Code", queryModel.getCode());
		inParameters.put("PosterUrl", queryModel.getPosterUrl());
		inParameters.put("PosterAddress", queryModel.getPosterAddress());
		inParameters.put("FlashPath", queryModel.getFlashPath());
		inParameters.put("RegisterLoginCode", queryModel.getRegisterLoginCode());
		inParameters.put("MyStatisticCode", queryModel.getMyStatisticCode());
		inParameters.put("OtherStatisticCode", queryModel.getOtherStatisticCode());
		inParameters.put("ExtendedCost", queryModel.getExtendedCost());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("ModifyDateBegin", Utility.getSqlDate(queryModel.getModifyDateBegin()));
		inParameters.put("ModifyDateEnd", Utility.getSqlDate(queryModel.getModifyDateEnd()));
		inParameters.put("Status", queryModel.getStatus());
		inParameters.put("DomainId", queryModel.getDomainId());
		inParameters.put("RegTemplateId", queryModel.getRegTemplateId());
		inParameters.put("RegisterCodeType", queryModel.getRegisterCodeType());
		inParameters.put("TitleType", queryModel.getTitleType());
		inParameters.put("StaticTitle", queryModel.getStaticTitle());
		inParameters.put("DynamicTitle", queryModel.getDynamicTitle());
		inParameters.put("KeyWord", queryModel.getKeyWord());
		inParameters.put("HtmlDesc", queryModel.getHtmlDesc());
		inParameters.put("BackgroundColor", queryModel.getBackgroundColor());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_PosterChannel_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除海报渠道
	 * 
	 * @param id 海报渠道编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("PosterChannelId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_PosterChannel_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改海报渠道状态
	 * 
	 * @param posterChannelId 
	 *        海报渠道编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int posterChannelId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("PosterChannelId", posterChannelId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_PosterChannel_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取海报渠道
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.promotion.model.PosterChannel getModel(ResultSet rs) throws SQLException{
		wh.promotion.model.PosterChannel m = null;
		if (rs != null)
        {
            m = new wh.promotion.model.PosterChannel();
			try{
				m.setPosterChannelId(rs.getInt("PosterChannelId"));
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
				
				wh.promotion.model.Poster poster = new wh.promotion.model.Poster();
				poster.setPosterId(rs.getInt("Poster_PosterId"));
				poster.setName(rs.getString("Poster_Name"));
				poster.setPosterAddress(rs.getString("Poster_PosterAddress"));
				poster.setFlashPath(rs.getString("Poster_FlashPath"));
				poster.setExtendedCost(rs.getBigDecimal("Poster_ExtendedCost"));
				poster.setStatus(rs.getByte("Poster_Status"));
				poster.setCode(rs.getString("Poster_Code"));
				poster.setPosterCategory(new wh.promotion.model.PosterCategory());
				poster.getPosterCategory().setPosterCategoryId(rs.getInt("Poster_PosterCategoryId"));
				poster.setWidth(rs.getInt("Poster_Width"));
				poster.setHight(rs.getInt("Poster_Hight"));
				poster.setPosterFileType(rs.getByte("Poster_PosterFileType"));
				m.setPoster(poster);
				
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
				
				m.setName(rs.getString("Name"));
				m.setCode(rs.getString("Code"));
				m.setPosterUrl(rs.getString("PosterUrl"));
				m.setPosterAddress(rs.getString("PosterAddress"));
				m.setFlashPath(rs.getString("FlashPath"));
				m.setRegisterLoginCode(rs.getString("RegisterLoginCode"));
				m.setMyStatisticCode(rs.getString("MyStatisticCode"));
				m.setOtherStatisticCode(rs.getString("OtherStatisticCode"));
				m.setExtendedCost(rs.getBigDecimal("ExtendedCost"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
				m.setStatus(rs.getByte("Status"));
				wh.promotion.model.Domain domain = new wh.promotion.model.Domain();
				domain.setDomainId(rs.getInt("Domain_DomainId"));
				domain.setName(rs.getString("Domain_Name"));
				domain.setStatus(rs.getByte("Domain_Status"));
				m.setDomain(domain);
				
				wh.promotion.model.RegTemplate regTemplate = new wh.promotion.model.RegTemplate();
				regTemplate.setRegTemplateId(rs.getInt("RegTemplate_RegTemplateId"));
				regTemplate.setDescription(rs.getString("RegTemplate_Description"));
				regTemplate.setName(rs.getString("RegTemplate_Name"));
				regTemplate.setStatus(rs.getByte("RegTemplate_Status"));
				regTemplate.setBGPosterPath(rs.getString("RegTemplate_BGPosterPath"));
				regTemplate.setRegPosterPath(rs.getString("RegTemplate_RegPosterPath"));
				regTemplate.setOtherPosterPath(rs.getString("RegTemplate_OtherPosterPath"));
				m.setRegTemplate(regTemplate);
				
				m.setRegisterCodeType(rs.getByte("RegisterCodeType"));
				m.setTitleType(rs.getByte("TitleType"));
				m.setStaticTitle(rs.getString("StaticTitle"));
				m.setDynamicTitle(rs.getString("DynamicTitle"));
				m.setKeyWord(rs.getString("KeyWord"));
				m.setHtmlDesc(rs.getString("HtmlDesc"));
				m.setBackgroundColor(rs.getString("BackgroundColor"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


