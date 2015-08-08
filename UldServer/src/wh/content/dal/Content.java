package wh.content.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Content数据访问层
 */
public class Content
{
	/**
	 * 创建更新内容表
	 * 
	 * @param model 
	 *        内容表
	 * @return 内容表编号
	 */
	public int createUpdate(wh.content.model.Content model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ContentId", model.getContentId());
		inParameters.put("CategoryId", model.getCategoryId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("FKTableId", model.getFKTableId());
		inParameters.put("FKTableNameType", model.getFKTableNameType());
		inParameters.put("ContentName", model.getContentName());
		inParameters.put("ContentDescription", model.getContentDescription());
		inParameters.put("BeginDate", Utility.getSqlDate(model.getBeginDate()));
		inParameters.put("EndDate", Utility.getSqlDate(model.getEndDate()));
		inParameters.put("Price", model.getPrice());
		inParameters.put("PosterPath", model.getPosterPath());
		inParameters.put("VideosPath", model.getVideosPath());
		inParameters.put("LinkUrl", model.getLinkUrl());
		inParameters.put("Field9", model.getField9());
		inParameters.put("Field10", model.getField10());
		inParameters.put("Field11", model.getField11());
		inParameters.put("Field12", model.getField12());
		inParameters.put("RecommendType", model.getRecommendType());
		inParameters.put("DisplayPositionType", model.getDisplayPositionType());
		inParameters.put("OrderIndex", model.getOrderIndex());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("Status", model.getStatus());
		inParameters.put("CategoryParentId", model.getCategoryParentId());
		inParameters.put("CategoryParentIdList", model.getCategoryParentIdList());
		inParameters.put("Level", model.getLevel());
		inParameters.put("ModifyDate", Utility.getSqlDate(model.getModifyDate()));
		
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
			"{? = call WH_Content_Content_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取内容表
	 * 
	 * @param id 
	 *        内容表编号
	 * @return 内容表
     */
	public wh.content.model.Content get(int id) throws Exception{
		wh.content.model.Content model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ContentId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Content_Content_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取内容表列表
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
	 * @return 内容表列表
     */
	public List<wh.content.model.Content> getList(wh.content.model.Content queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.content.model.Content> list = new ArrayList<wh.content.model.Content>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("ContentId", queryModel.getContentId());
		inParameters.put("CategoryId", queryModel.getCategoryId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("FKTableId", queryModel.getFKTableId());
		inParameters.put("FKTableNameType", queryModel.getFKTableNameType());
		inParameters.put("ContentName", queryModel.getContentName());
		inParameters.put("ContentDescription", queryModel.getContentDescription());
		inParameters.put("BeginDateBegin", Utility.getSqlDate(queryModel.getBeginDateBegin()));
		inParameters.put("BeginDateEnd", Utility.getSqlDate(queryModel.getBeginDateEnd()));
		inParameters.put("EndDateBegin", Utility.getSqlDate(queryModel.getEndDateBegin()));
		inParameters.put("EndDateEnd", Utility.getSqlDate(queryModel.getEndDateEnd()));
		inParameters.put("Price", queryModel.getPrice());
		inParameters.put("PosterPath", queryModel.getPosterPath());
		inParameters.put("VideosPath", queryModel.getVideosPath());
		inParameters.put("LinkUrl", queryModel.getLinkUrl());
		inParameters.put("Field9", queryModel.getField9());
		inParameters.put("Field10", queryModel.getField10());
		inParameters.put("Field11", queryModel.getField11());
		inParameters.put("Field12", queryModel.getField12());
		inParameters.put("RecommendType", queryModel.getRecommendType());
		inParameters.put("DisplayPositionType", queryModel.getDisplayPositionType());
		inParameters.put("OrderIndex", queryModel.getOrderIndex());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("Status", queryModel.getStatus());
		inParameters.put("CategoryParentId", queryModel.getCategoryParentId());
		inParameters.put("CategoryParentIdList", queryModel.getCategoryParentIdList());
		inParameters.put("Level", queryModel.getLevel());
		inParameters.put("ModifyDateBegin", Utility.getSqlDate(queryModel.getModifyDateBegin()));
		inParameters.put("ModifyDateEnd", Utility.getSqlDate(queryModel.getModifyDateEnd()));
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Content_Content_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除内容表
	 * 
	 * @param id 内容表编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ContentId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Content_Content_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取内容表根据内容表名称
	 * 
	 * @param ContentName 
	 *        内容表名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @return 内容表
	 */
	public wh.content.model.Content getByName(String contentName, String fldSort, int sortType) throws Exception{
		wh.content.model.Content model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ContentName", contentName);
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Content_Content_GetByName(?,?,?,?,?)}", inParameters, outParameters);
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
	 * 获取内容表列表根据内容表名称
	 * 
	 * @param ContentName 
	 *        内容表名称
	 * @param fldSort 
	 *        排序字段，默认""则按照主键编号排序
	 * @param sortType 
	 *        排序方法，0为升序，1为降序
	 * @param queryCount 
	 *        查询总数量
	 * @return 内容表列表
	 */
	public List<wh.content.model.Content> getListByName(String contentName, String fldSort, int sortType, int queryCount) throws Exception{
		List<wh.content.model.Content> list = new ArrayList<wh.content.model.Content>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ContentName", contentName);
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Content_Content_GetListByName(?,?,?,?,?,?)}", inParameters, outParameters);
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
	 * 修改内容表状态
	 * 
	 * @param contentId 
	 *        内容编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int contentId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ContentId", contentId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Content_Content_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取内容表
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.content.model.Content getModel(ResultSet rs) throws SQLException{
		wh.content.model.Content m = null;
		if (rs != null)
        {
            m = new wh.content.model.Content();
			try{
				m.setContentId(rs.getInt("ContentId"));
				wh.content.model.Category category = new wh.content.model.Category();
				category.setCategoryId(rs.getInt("Category_CategoryId"));
				category.setManager(new wh.member.model.Manager());
				category.getManager().setManagerId(rs.getInt("Category_ManagerId"));
				category.setCategoryName(rs.getString("Category_CategoryName"));
				category.setCategoryDescription(rs.getString("Category_CategoryDescription"));
				category.setField9(rs.getString("Category_Field9"));
				category.setField10(rs.getString("Category_Field10"));
				category.setField11(rs.getString("Category_Field11"));
				category.setField12(rs.getString("Category_Field12"));
				category.setParentId(rs.getInt("Category_ParentId"));
				category.setParentIdList(rs.getString("Category_ParentIdList"));
				category.setLevel(rs.getInt("Category_Level"));
				category.setCreateDate(rs.getTimestamp("Category_CreateDate"));
				category.setModifyDate(rs.getTimestamp("Category_ModifyDate"));
				m.setCategory(category);
				
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
				
				m.setFKTableId(rs.getInt("FKTableId"));
				m.setFKTableNameType(rs.getByte("FKTableNameType"));
				m.setContentName(rs.getString("ContentName"));
				m.setContentDescription(rs.getString("ContentDescription"));
				m.setBeginDate(rs.getTimestamp("BeginDate"));
				m.setEndDate(rs.getTimestamp("EndDate"));
				m.setPrice(rs.getInt("Price"));
				m.setPosterPath(rs.getString("PosterPath"));
				m.setVideosPath(rs.getString("VideosPath"));
				m.setLinkUrl(rs.getString("LinkUrl"));
				m.setField9(rs.getString("Field9"));
				m.setField10(rs.getString("Field10"));
				m.setField11(rs.getString("Field11"));
				m.setField12(rs.getString("Field12"));
				m.setRecommendType(rs.getByte("RecommendType"));
				m.setDisplayPositionType(rs.getByte("DisplayPositionType"));
				m.setOrderIndex(rs.getInt("OrderIndex"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setStatus(rs.getByte("Status"));
				m.setCategoryParentId(rs.getInt("CategoryParentId"));
				m.setCategoryParentIdList(rs.getString("CategoryParentIdList"));
				m.setLevel(rs.getInt("Level"));
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


