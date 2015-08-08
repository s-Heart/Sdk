package wh.shop.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Goods数据访问层
 */
public class Goods
{
	/**
	 * 创建更新商品
	 * 
	 * @param model 
	 *        商品
	 * @return 商品编号
	 */
	public int createUpdate(wh.shop.model.Goods model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsId", model.getGoodsId());
		inParameters.put("GoodsCategoryId", model.getGoodsCategoryId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("ProductId", model.getProductId());
		inParameters.put("CategoryParentId", model.getCategoryParentId());
		inParameters.put("CategoryParentIdList", model.getCategoryParentIdList());
		inParameters.put("Name", model.getName());
		inParameters.put("GoodsDescription", model.getGoodsDescription());
		inParameters.put("PaymentType", model.getPaymentType());
		inParameters.put("NeedDB", model.getNeedDB());
		inParameters.put("NeedFortune", model.getNeedFortune());
		inParameters.put("Price", model.getPrice());
		inParameters.put("StockAmount", model.getStockAmount());
		inParameters.put("SoldAmount", model.getSoldAmount());
		inParameters.put("ClickAmount", model.getClickAmount());
		inParameters.put("ShareAmount", model.getShareAmount());
		inParameters.put("Color", model.getColor());
		inParameters.put("PosterPath", model.getPosterPath());
		inParameters.put("ReturnFortune", model.getReturnFortune());
		inParameters.put("ReturnPlat", model.getReturnPlat());
		inParameters.put("ActiveType", model.getActiveType());
		inParameters.put("ActivePlatPrice", model.getActivePlatPrice());
		inParameters.put("ActiveFortunePrice", model.getActiveFortunePrice());
		inParameters.put("ActiveStartDate", Utility.getSqlDate(model.getActiveStartDate()));
		inParameters.put("ActiveStartDateDay", Utility.getDateDay(model.getActiveStartDate()));
		inParameters.put("ActiveEndDate", Utility.getSqlDate(model.getActiveEndDate()));
		inParameters.put("ActiveEndDateDay", Utility.getDateDay(model.getActiveEndDate()));
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(model.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(model.getCreateDate()));
		inParameters.put("ModifyDate", Utility.getSqlDate(model.getModifyDate()));
		inParameters.put("ModifyDateDay", Utility.getDateDay(model.getModifyDate()));
		inParameters.put("ModifyDateMonth", Utility.getDateMonth(model.getModifyDate()));
		inParameters.put("RecommendType", model.getRecommendType());
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
			"{? = call WH_Shop_Goods_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取商品
	 * 
	 * @param id 
	 *        商品编号
	 * @return 商品
     */
	public wh.shop.model.Goods get(int id) throws Exception{
		wh.shop.model.Goods model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_Goods_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取商品列表
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
	 * @return 商品列表
     */
	public List<wh.shop.model.Goods> getList(wh.shop.model.Goods queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.shop.model.Goods> list = new ArrayList<wh.shop.model.Goods>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("GoodsId", queryModel.getGoodsId());
		inParameters.put("GoodsCategoryId", queryModel.getGoodsCategoryId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("ProductId", queryModel.getProductId());
		inParameters.put("CategoryParentId", queryModel.getCategoryParentId());
		inParameters.put("CategoryParentIdList", queryModel.getCategoryParentIdList());
		inParameters.put("Name", queryModel.getName());
		inParameters.put("GoodsDescription", queryModel.getGoodsDescription());
		inParameters.put("PaymentType", queryModel.getPaymentType());
		inParameters.put("NeedDB", queryModel.getNeedDB());
		inParameters.put("NeedFortune", queryModel.getNeedFortune());
		inParameters.put("Price", queryModel.getPrice());
		inParameters.put("StockAmount", queryModel.getStockAmount());
		inParameters.put("SoldAmount", queryModel.getSoldAmount());
		inParameters.put("ClickAmount", queryModel.getClickAmount());
		inParameters.put("ShareAmount", queryModel.getShareAmount());
		inParameters.put("Color", queryModel.getColor());
		inParameters.put("PosterPath", queryModel.getPosterPath());
		inParameters.put("ReturnFortune", queryModel.getReturnFortune());
		inParameters.put("ReturnPlat", queryModel.getReturnPlat());
		inParameters.put("ActiveType", queryModel.getActiveType());
		inParameters.put("ActivePlatPrice", queryModel.getActivePlatPrice());
		inParameters.put("ActiveFortunePrice", queryModel.getActiveFortunePrice());
		inParameters.put("ActiveStartDateBegin", Utility.getSqlDate(queryModel.getActiveStartDateBegin()));
		inParameters.put("ActiveStartDateEnd", Utility.getSqlDate(queryModel.getActiveStartDateEnd()));
		inParameters.put("ActiveStartDateDay", Utility.getDateDay(queryModel.getActiveStartDate()));
		inParameters.put("ActiveEndDateBegin", Utility.getSqlDate(queryModel.getActiveEndDateBegin()));
		inParameters.put("ActiveEndDateEnd", Utility.getSqlDate(queryModel.getActiveEndDateEnd()));
		inParameters.put("ActiveEndDateDay", Utility.getDateDay(queryModel.getActiveEndDate()));
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("CreateDateDay", Utility.getDateDay(queryModel.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(queryModel.getCreateDate()));
		inParameters.put("ModifyDateBegin", Utility.getSqlDate(queryModel.getModifyDateBegin()));
		inParameters.put("ModifyDateEnd", Utility.getSqlDate(queryModel.getModifyDateEnd()));
		inParameters.put("ModifyDateDay", Utility.getDateDay(queryModel.getModifyDate()));
		inParameters.put("ModifyDateMonth", Utility.getDateMonth(queryModel.getModifyDate()));
		inParameters.put("RecommendType", queryModel.getRecommendType());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_Goods_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除商品
	 * 
	 * @param id 商品编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Shop_Goods_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	
	/**
	 * 修改商品状态
	 * 
	 * @param goodsId 
	 *        商品编号
	 * @param status 
	 *        状态：0、状态；1、有效；2、无效；
	 */
	public void modifyStatus(int goodsId, byte status) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsId", goodsId);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Shop_Goods_ModifyStatus(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	/**
	 * 获取商品
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.shop.model.Goods getModel(ResultSet rs) throws SQLException{
		wh.shop.model.Goods m = null;
		if (rs != null)
        {
            m = new wh.shop.model.Goods();
			try{
				m.setGoodsId(rs.getInt("GoodsId"));
				wh.shop.model.GoodsCategory goodsCategory = new wh.shop.model.GoodsCategory();
				goodsCategory.setGoodsCategoryId(rs.getInt("GoodsCategory_GoodsCategoryId"));
				goodsCategory.setManager(new wh.member.model.Manager());
				goodsCategory.getManager().setManagerId(rs.getInt("GoodsCategory_ManagerId"));
				goodsCategory.setParentId(rs.getInt("GoodsCategory_ParentId"));
				goodsCategory.setParentIdList(rs.getString("GoodsCategory_ParentIdList"));
				goodsCategory.setLevel(rs.getInt("GoodsCategory_Level"));
				goodsCategory.setName(rs.getString("GoodsCategory_Name"));
				goodsCategory.setContent(rs.getString("GoodsCategory_Content"));
				goodsCategory.setPosterPath(rs.getString("GoodsCategory_PosterPath"));
				goodsCategory.setCreateDate(rs.getTimestamp("GoodsCategory_CreateDate"));
				goodsCategory.setStatus(rs.getByte("GoodsCategory_Status"));
				m.setGoodsCategory(goodsCategory);
				
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
				
				wh.shop.model.Product product = new wh.shop.model.Product();
				product.setProductId(rs.getInt("Product_ProductId"));
				product.setGoodsCategory(new wh.shop.model.GoodsCategory());
				product.getGoodsCategory().setGoodsCategoryId(rs.getInt("Product_GoodsCategoryId"));
				product.setName(rs.getString("Product_Name"));
				product.setContent(rs.getString("Product_Content"));
				product.setCreateDate(rs.getTimestamp("Product_CreateDate"));
				product.setModifyDate(rs.getTimestamp("Product_ModifyDate"));
				product.setStatus(rs.getByte("Product_Status"));
				m.setProduct(product);
				
				m.setCategoryParentId(rs.getInt("CategoryParentId"));
				m.setCategoryParentIdList(rs.getString("CategoryParentIdList"));
				m.setName(rs.getString("Name"));
				m.setGoodsDescription(rs.getString("GoodsDescription"));
				m.setPaymentType(rs.getByte("PaymentType"));
				m.setNeedDB(rs.getInt("NeedDB"));
				m.setNeedFortune(rs.getInt("NeedFortune"));
				m.setPrice(rs.getInt("Price"));
				m.setStockAmount(rs.getInt("StockAmount"));
				m.setSoldAmount(rs.getInt("SoldAmount"));
				m.setClickAmount(rs.getInt("ClickAmount"));
				m.setShareAmount(rs.getInt("ShareAmount"));
				m.setColor(rs.getString("Color"));
				m.setPosterPath(rs.getString("PosterPath"));
				m.setReturnFortune(rs.getInt("ReturnFortune"));
				m.setReturnPlat(rs.getInt("ReturnPlat"));
				m.setActiveType(rs.getByte("ActiveType"));
				m.setActivePlatPrice(rs.getInt("ActivePlatPrice"));
				m.setActiveFortunePrice(rs.getInt("ActiveFortunePrice"));
				m.setActiveStartDate(rs.getTimestamp("ActiveStartDate"));
				m.setActiveEndDate(rs.getTimestamp("ActiveEndDate"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
				m.setModifyDate(rs.getTimestamp("ModifyDate"));
				m.setRecommendType(rs.getByte("RecommendType"));
				m.setStatus(rs.getByte("Status"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


