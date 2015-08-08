package wh.promotion.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Poster数据访问层
 */
public class Poster
{
	/**
	 * 创建更新海报
	 * 
	 * @param model 
	 *        海报
	 * @return 海报编号
	 */
	public int createUpdate(wh.promotion.model.Poster model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("PosterId", model.getPosterId());
		inParameters.put("Name", model.getName());
		inParameters.put("PosterAddress", model.getPosterAddress());
		inParameters.put("FlashPath", model.getFlashPath());
		inParameters.put("ExtendedCost", model.getExtendedCost());
		inParameters.put("Status", model.getStatus());
		inParameters.put("Code", model.getCode());
		inParameters.put("PosterCategoryId", model.getPosterCategoryId());
		inParameters.put("Width", model.getWidth());
		inParameters.put("Hight", model.getHight());
		inParameters.put("PosterFileType", model.getPosterFileType());
		
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
			"{? = call WH_Promotion_Poster_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取海报
	 * 
	 * @param id 
	 *        海报编号
	 * @return 海报
     */
	public wh.promotion.model.Poster get(int id) throws Exception{
		wh.promotion.model.Poster model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("PosterId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Poster_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取海报列表
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
	 * @return 海报列表
     */
	public List<wh.promotion.model.Poster> getList(wh.promotion.model.Poster queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.promotion.model.Poster> list = new ArrayList<wh.promotion.model.Poster>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("PosterId", queryModel.getPosterId());
		inParameters.put("Name", queryModel.getName());
		inParameters.put("PosterAddress", queryModel.getPosterAddress());
		inParameters.put("FlashPath", queryModel.getFlashPath());
		inParameters.put("ExtendedCost", queryModel.getExtendedCost());
		inParameters.put("Status", queryModel.getStatus());
		inParameters.put("Code", queryModel.getCode());
		inParameters.put("PosterCategoryId", queryModel.getPosterCategoryId());
		inParameters.put("Width", queryModel.getWidth());
		inParameters.put("Hight", queryModel.getHight());
		inParameters.put("PosterFileType", queryModel.getPosterFileType());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Poster_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除海报
	 * 
	 * @param id 海报编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("PosterId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Poster_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改海报状态
	 * 
	 * @param posterId 
	 *        海报编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int posterId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("PosterId", posterId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Promotion_Poster_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取海报
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.promotion.model.Poster getModel(ResultSet rs) throws SQLException{
		wh.promotion.model.Poster m = null;
		if (rs != null)
        {
            m = new wh.promotion.model.Poster();
			try{
				m.setPosterId(rs.getInt("PosterId"));
				m.setName(rs.getString("Name"));
				m.setPosterAddress(rs.getString("PosterAddress"));
				m.setFlashPath(rs.getString("FlashPath"));
				m.setExtendedCost(rs.getBigDecimal("ExtendedCost"));
				m.setStatus(rs.getByte("Status"));
				m.setCode(rs.getString("Code"));
				wh.promotion.model.PosterCategory posterCategory = new wh.promotion.model.PosterCategory();
				posterCategory.setPosterCategoryId(rs.getInt("PosterCategory_PosterCategoryId"));
				posterCategory.setName(rs.getString("PosterCategory_Name"));
				posterCategory.setCode(rs.getString("PosterCategory_Code"));
				posterCategory.setCreateDate(rs.getTimestamp("PosterCategory_CreateDate"));
				posterCategory.setModifyDate(rs.getTimestamp("PosterCategory_ModifyDate"));
				posterCategory.setStatus(rs.getByte("PosterCategory_Status"));
				m.setPosterCategory(posterCategory);
				
				m.setWidth(rs.getInt("Width"));
				m.setHight(rs.getInt("Hight"));
				m.setPosterFileType(rs.getByte("PosterFileType"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


