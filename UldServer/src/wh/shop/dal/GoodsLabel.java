package wh.shop.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * GoodsLabel数据访问层
 */
public class GoodsLabel
{
	/**
	 * 创建更新商品标签
	 * 
	 * @param model 
	 *        商品标签
	 * @return 商品标签编号
	 */
	public int createUpdate(wh.shop.model.GoodsLabel model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsLabelId", model.getGoodsLabelId());
		inParameters.put("GoodsId", model.getGoodsId());
		inParameters.put("Name", model.getName());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("SearchAmount", model.getSearchAmount());
		
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
			"{? = call WH_Shop_GoodsLabel_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取商品标签
	 * 
	 * @param id 
	 *        商品标签编号
	 * @return 商品标签
     */
	public wh.shop.model.GoodsLabel get(int id) throws Exception{
		wh.shop.model.GoodsLabel model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsLabelId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_GoodsLabel_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取商品标签列表
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
	 * @return 商品标签列表
     */
	public List<wh.shop.model.GoodsLabel> getList(wh.shop.model.GoodsLabel queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.shop.model.GoodsLabel> list = new ArrayList<wh.shop.model.GoodsLabel>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("GoodsLabelId", queryModel.getGoodsLabelId());
		inParameters.put("GoodsId", queryModel.getGoodsId());
		inParameters.put("Name", queryModel.getName());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("SearchAmount", queryModel.getSearchAmount());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_GoodsLabel_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除商品标签
	 * 
	 * @param id 商品标签编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsLabelId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Shop_GoodsLabel_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取商品标签
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.shop.model.GoodsLabel getModel(ResultSet rs) throws SQLException{
		wh.shop.model.GoodsLabel m = null;
		if (rs != null)
        {
            m = new wh.shop.model.GoodsLabel();
			try{
				m.setGoodsLabelId(rs.getInt("GoodsLabelId"));
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
				
				m.setName(rs.getString("Name"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setSearchAmount(rs.getInt("SearchAmount"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


