package wh.shop.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * ShopContent数据访问层
 */
public class ShopContent
{
	/**
	 * 创建更新商城内容
	 * 
	 * @param model 
	 *        商城内容
	 * @return 商城内容编号
	 */
	public int createUpdate(wh.shop.model.ShopContent model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ShopContentId", model.getShopContentId());
		inParameters.put("ShopCategoryId", model.getShopCategoryId());
		inParameters.put("CategoryParentId", model.getCategoryParentId());
		inParameters.put("CategoryParentIdList", model.getCategoryParentIdList());
		inParameters.put("Level", model.getLevel());
		inParameters.put("FKTableId", model.getFKTableId());
		inParameters.put("FKTableNameType", model.getFKTableNameType());
		inParameters.put("ContentName", model.getContentName());
		inParameters.put("ContentDescription", model.getContentDescription());
		inParameters.put("BeginDate", Utility.getSqlDate(model.getBeginDate()));
		inParameters.put("EndDate", Utility.getSqlDate(model.getEndDate()));
		inParameters.put("Price", model.getPrice());
		inParameters.put("PriceAnother", model.getPriceAnother());
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
			"{? = call WH_Shop_ShopContent_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取商城内容
	 * 
	 * @param id 
	 *        商城内容编号
	 * @return 商城内容
     */
	public wh.shop.model.ShopContent get(int id) throws Exception{
		wh.shop.model.ShopContent model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ShopContentId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_ShopContent_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取商城内容列表
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
	 * @return 商城内容列表
     */
	public List<wh.shop.model.ShopContent> getList(wh.shop.model.ShopContent queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.shop.model.ShopContent> list = new ArrayList<wh.shop.model.ShopContent>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("ShopContentId", queryModel.getShopContentId());
		inParameters.put("ShopCategoryId", queryModel.getShopCategoryId());
		inParameters.put("CategoryParentId", queryModel.getCategoryParentId());
		inParameters.put("CategoryParentIdList", queryModel.getCategoryParentIdList());
		inParameters.put("Level", queryModel.getLevel());
		inParameters.put("FKTableId", queryModel.getFKTableId());
		inParameters.put("FKTableNameType", queryModel.getFKTableNameType());
		inParameters.put("ContentName", queryModel.getContentName());
		inParameters.put("ContentDescription", queryModel.getContentDescription());
		inParameters.put("BeginDateBegin", Utility.getSqlDate(queryModel.getBeginDateBegin()));
		inParameters.put("BeginDateEnd", Utility.getSqlDate(queryModel.getBeginDateEnd()));
		inParameters.put("EndDateBegin", Utility.getSqlDate(queryModel.getEndDateBegin()));
		inParameters.put("EndDateEnd", Utility.getSqlDate(queryModel.getEndDateEnd()));
		inParameters.put("Price", queryModel.getPrice());
		inParameters.put("PriceAnother", queryModel.getPriceAnother());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_ShopContent_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除商城内容
	 * 
	 * @param id 商城内容编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ShopContentId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Shop_ShopContent_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取商城内容
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.shop.model.ShopContent getModel(ResultSet rs) throws SQLException{
		wh.shop.model.ShopContent m = null;
		if (rs != null)
        {
            m = new wh.shop.model.ShopContent();
			try{
				m.setShopContentId(rs.getInt("ShopContentId"));
				wh.shop.model.ShopCategory shopCategory = new wh.shop.model.ShopCategory();
				shopCategory.setShopCategoryId(rs.getInt("ShopCategory_ShopCategoryId"));
				shopCategory.setParentId(rs.getInt("ShopCategory_ParentId"));
				shopCategory.setParentIdList(rs.getString("ShopCategory_ParentIdList"));
				shopCategory.setLevel(rs.getInt("ShopCategory_Level"));
				shopCategory.setCategoryName(rs.getString("ShopCategory_CategoryName"));
				shopCategory.setCategoryDescription(rs.getString("ShopCategory_CategoryDescription"));
				shopCategory.setField9(rs.getString("ShopCategory_Field9"));
				shopCategory.setField10(rs.getString("ShopCategory_Field10"));
				shopCategory.setField11(rs.getString("ShopCategory_Field11"));
				shopCategory.setField12(rs.getString("ShopCategory_Field12"));
				shopCategory.setCreateDate(rs.getTimestamp("ShopCategory_CreateDate"));
				shopCategory.setModifyDate(rs.getTimestamp("ShopCategory_ModifyDate"));
				m.setShopCategory(shopCategory);
				
				m.setCategoryParentId(rs.getInt("CategoryParentId"));
				m.setCategoryParentIdList(rs.getString("CategoryParentIdList"));
				m.setLevel(rs.getInt("Level"));
				m.setFKTableId(rs.getInt("FKTableId"));
				m.setFKTableNameType(rs.getByte("FKTableNameType"));
				m.setContentName(rs.getString("ContentName"));
				m.setContentDescription(rs.getString("ContentDescription"));
				m.setBeginDate(rs.getTimestamp("BeginDate"));
				m.setEndDate(rs.getTimestamp("EndDate"));
				m.setPrice(rs.getInt("Price"));
				m.setPriceAnother(rs.getInt("PriceAnother"));
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
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


