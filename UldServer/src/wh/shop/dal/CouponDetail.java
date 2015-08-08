package wh.shop.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * CouponDetail数据访问层
 */
public class CouponDetail
{
	/**
	 * 创建更新优惠券详情
	 * 
	 * @param model 
	 *        优惠券详情
	 * @return 优惠券详情编号
	 */
	public int createUpdate(wh.shop.model.CouponDetail model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("CouponDetailId", model.getCouponDetailId());
		inParameters.put("CouponId", model.getCouponId());
		inParameters.put("GoodsOrderId", model.getGoodsOrderId());
		inParameters.put("Remark", model.getRemark());
		inParameters.put("CreateDate", Utility.getSqlDate(model.getCreateDate()));
		inParameters.put("CreateDateDay", Utility.getDateDay(model.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(model.getCreateDate()));
		inParameters.put("CreateDateYear", Utility.getDateYear(model.getCreateDate()));
		
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
			"{? = call WH_Shop_CouponDetail_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取优惠券详情
	 * 
	 * @param id 
	 *        优惠券详情编号
	 * @return 优惠券详情
     */
	public wh.shop.model.CouponDetail get(int id) throws Exception{
		wh.shop.model.CouponDetail model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("CouponDetailId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_CouponDetail_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取优惠券详情列表
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
	 * @return 优惠券详情列表
     */
	public List<wh.shop.model.CouponDetail> getList(wh.shop.model.CouponDetail queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.shop.model.CouponDetail> list = new ArrayList<wh.shop.model.CouponDetail>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("CouponDetailId", queryModel.getCouponDetailId());
		inParameters.put("CouponId", queryModel.getCouponId());
		inParameters.put("GoodsOrderId", queryModel.getGoodsOrderId());
		inParameters.put("Remark", queryModel.getRemark());
		inParameters.put("CreateDateBegin", Utility.getSqlDate(queryModel.getCreateDateBegin()));
		inParameters.put("CreateDateEnd", Utility.getSqlDate(queryModel.getCreateDateEnd()));
		inParameters.put("CreateDateDay", Utility.getDateDay(queryModel.getCreateDate()));
		inParameters.put("CreateDateMonth", Utility.getDateMonth(queryModel.getCreateDate()));
		inParameters.put("CreateDateYear", Utility.getDateYear(queryModel.getCreateDate()));
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_CouponDetail_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除优惠券详情
	 * 
	 * @param id 优惠券详情编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("CouponDetailId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Shop_CouponDetail_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取优惠券详情
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.shop.model.CouponDetail getModel(ResultSet rs) throws SQLException{
		wh.shop.model.CouponDetail m = null;
		if (rs != null)
        {
            m = new wh.shop.model.CouponDetail();
			try{
				m.setCouponDetailId(rs.getInt("CouponDetailId"));
				wh.shop.model.Coupon coupon = new wh.shop.model.Coupon();
				coupon.setCouponId(rs.getInt("Coupon_CouponId"));
				coupon.setUser(new wh.member.model.User());
				coupon.getUser().setUserId(rs.getInt("Coupon_UserId"));
				coupon.setCouponType(rs.getByte("Coupon_CouponType"));
				coupon.setNeedDB(rs.getInt("Coupon_NeedDB"));
				coupon.setNeedFortune(rs.getInt("Coupon_NeedFortune"));
				coupon.setNeedConsumeValue(rs.getInt("Coupon_NeedConsumeValue"));
				coupon.setCreateDate(rs.getTimestamp("Coupon_CreateDate"));
				coupon.setEffectiveDate(rs.getTimestamp("Coupon_EffectiveDate"));
				coupon.setExpiredDate(rs.getTimestamp("Coupon_ExpiredDate"));
				coupon.setUsedType(rs.getByte("Coupon_UsedType"));
				m.setCoupon(coupon);
				
				wh.shop.model.GoodsOrder goodsOrder = new wh.shop.model.GoodsOrder();
				goodsOrder.setGoodsOrderId(rs.getInt("GoodsOrder_GoodsOrderId"));
				goodsOrder.setFortuneDetail(new wh.member.model.FortuneDetail());
				goodsOrder.getFortuneDetail().setFortuneDetailId(rs.getInt("GoodsOrder_FortuneDetailId"));
				goodsOrder.setManager(new wh.member.model.Manager());
				goodsOrder.getManager().setManagerId(rs.getInt("GoodsOrder_ManagerId"));
				goodsOrder.setUser(new wh.member.model.User());
				goodsOrder.getUser().setUserId(rs.getInt("GoodsOrder_UserId"));
				goodsOrder.setAccountDetail(new wh.member.model.AccountDetail());
				goodsOrder.getAccountDetail().setAccountDetailId(rs.getInt("GoodsOrder_AccountDetailId"));
				goodsOrder.setPaymentType(rs.getByte("GoodsOrder_PaymentType"));
				goodsOrder.setNeedDB(rs.getInt("GoodsOrder_NeedDB"));
				goodsOrder.setNeedFortune(rs.getInt("GoodsOrder_NeedFortune"));
				goodsOrder.setShipping(rs.getInt("GoodsOrder_Shipping"));
				goodsOrder.setRemark(rs.getString("GoodsOrder_Remark"));
				goodsOrder.setGoodsCount(rs.getInt("GoodsOrder_GoodsCount"));
				goodsOrder.setGoodsTotalCount(rs.getInt("GoodsOrder_GoodsTotalCount"));
				goodsOrder.setIDCard(rs.getString("GoodsOrder_IDCard"));
				goodsOrder.setPostcode(rs.getString("GoodsOrder_Postcode"));
				goodsOrder.setAddress(rs.getString("GoodsOrder_Address"));
				goodsOrder.setAddressee(rs.getString("GoodsOrder_Addressee"));
				goodsOrder.setMobilePhone(rs.getString("GoodsOrder_MobilePhone"));
				goodsOrder.setTel(rs.getString("GoodsOrder_Tel"));
				goodsOrder.setEmail(rs.getString("GoodsOrder_Email"));
				goodsOrder.setQQ(rs.getString("GoodsOrder_QQ"));
				goodsOrder.setMSN(rs.getString("GoodsOrder_MSN"));
				goodsOrder.setCreateDate(rs.getTimestamp("GoodsOrder_CreateDate"));
				goodsOrder.setModifyDate(rs.getTimestamp("GoodsOrder_ModifyDate"));
				goodsOrder.setShopOrderType(rs.getInt("GoodsOrder_ShopOrderType"));
				goodsOrder.setStatus(rs.getByte("GoodsOrder_Status"));
				m.setGoodsOrder(goodsOrder);
				
				m.setRemark(rs.getString("Remark"));
				m.setCreateDate(rs.getTimestamp("CreateDate"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


