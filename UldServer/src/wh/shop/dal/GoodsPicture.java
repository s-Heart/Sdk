package wh.shop.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * GoodsPicture数据访问层
 */
public class GoodsPicture
{
	/**
	 * 创建更新商品图片
	 * 
	 * @param model 
	 *        商品图片
	 * @return 商品图片编号
	 */
	public int createUpdate(wh.shop.model.GoodsPicture model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsPictureId", model.getGoodsPictureId());
		inParameters.put("GoodsId", model.getGoodsId());
		inParameters.put("ManagerId", model.getManagerId());
		inParameters.put("Name", model.getName());
		inParameters.put("PosterPath", model.getPosterPath());
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
			"{? = call WH_Shop_GoodsPicture_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取商品图片
	 * 
	 * @param id 
	 *        商品图片编号
	 * @return 商品图片
     */
	public wh.shop.model.GoodsPicture get(int id) throws Exception{
		wh.shop.model.GoodsPicture model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsPictureId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_GoodsPicture_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取商品图片列表
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
	 * @return 商品图片列表
     */
	public List<wh.shop.model.GoodsPicture> getList(wh.shop.model.GoodsPicture queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.shop.model.GoodsPicture> list = new ArrayList<wh.shop.model.GoodsPicture>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("GoodsPictureId", queryModel.getGoodsPictureId());
		inParameters.put("GoodsId", queryModel.getGoodsId());
		inParameters.put("ManagerId", queryModel.getManagerId());
		inParameters.put("Name", queryModel.getName());
		inParameters.put("PosterPath", queryModel.getPosterPath());
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_GoodsPicture_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除商品图片
	 * 
	 * @param id 商品图片编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("GoodsPictureId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Shop_GoodsPicture_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取商品图片
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.shop.model.GoodsPicture getModel(ResultSet rs) throws SQLException{
		wh.shop.model.GoodsPicture m = null;
		if (rs != null)
        {
            m = new wh.shop.model.GoodsPicture();
			try{
				m.setGoodsPictureId(rs.getInt("GoodsPictureId"));
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
				
				m.setName(rs.getString("Name"));
				m.setPosterPath(rs.getString("PosterPath"));
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


