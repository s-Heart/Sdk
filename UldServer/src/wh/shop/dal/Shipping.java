﻿package wh.shop.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import uld.sdk.tools.*;

/**
 * Shipping数据访问层
 */
public class Shipping
{
	/**
	 * 创建更新运费
	 * 
	 * @param model 
	 *        运费
	 * @return 运费编号
	 */
	public int createUpdate(wh.shop.model.Shipping model) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ShippingId", model.getShippingId());
		inParameters.put("ProvinceId", model.getProvinceId());
		inParameters.put("CityId", model.getCityId());
		inParameters.put("DistrictId", model.getDistrictId());
		inParameters.put("Price", model.getPrice());
		
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
			"{? = call WH_Shop_Shipping_CreateUpdate(" + sbBuilder.toString() + ")}", inParameters, outParameters);		
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
		
		return retValue;
	}
	
	/**
	 * 获取运费
	 * 
	 * @param id 
	 *        运费编号
	 * @return 运费
     */
	public wh.shop.model.Shipping get(int id) throws Exception{
		wh.shop.model.Shipping model = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ShippingId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
        
		ResultSet rs = null;
		CallableStatement cstmt = null;
		int errNo = 0;
		String errMsg = "";
		try {
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_Shipping_Get(?,?,?)}", inParameters, outParameters);
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
	 * 获取运费列表
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
	 * @return 运费列表
     */
	public List<wh.shop.model.Shipping> getList(wh.shop.model.Shipping queryModel, RefObject<Integer> totalCount, 
		boolean isAll, int pageIndex, int pageSize, String fldSort, int sortType) throws Exception {
		List<wh.shop.model.Shipping> list = new ArrayList<wh.shop.model.Shipping>();
		if (Utility.isEmpty(fldSort)) {
			fldSort = "";
		}
		
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("IsAll", isAll);
		inParameters.put("PageIndex", pageIndex);
		inParameters.put("PageSize", pageSize);
		inParameters.put("FldSort", fldSort);
		inParameters.put("SortType", sortType);
		
		inParameters.put("ShippingId", queryModel.getShippingId());
		inParameters.put("ProvinceId", queryModel.getProvinceId());
		inParameters.put("CityId", queryModel.getCityId());
		inParameters.put("DistrictId", queryModel.getDistrictId());
		inParameters.put("Price", queryModel.getPrice());
		
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
			rs = SQLHelper.runProcedure(Config.CONNECTION_STRING_ULD, "{call WH_Shop_Shipping_GetList(" + sbBuilder.toString() + ")}",
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
	 * 删除运费
	 * 
	 * @param id 运费编号
	 */
	public void remove(int id) throws Exception{
		Map<String, Object> inParameters = new HashMap<String, Object>();
		inParameters.put("ShippingId", id);
		
		OutParameters outParameters = new OutParameters();
		outParameters.putOutParmType("ErrNo", java.sql.Types.INTEGER);
		outParameters.putOutParmType("ErrMsg", java.sql.Types.NVARCHAR);
		
		try {
			SQLHelper.runProcedureNonQuery(Config.CONNECTION_STRING_ULD, "{call WH_Shop_Shipping_Remove(?,?,?)}", inParameters, outParameters);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
		
		int errNo = Integer.valueOf(outParameters.getOutParmValue("ErrNo").toString());
		String errMsg = outParameters.getOutParmValue("ErrMsg").toString();
		MyErr.checkErr(errNo, errMsg);
	}
	
	

	
	/**
	 * 获取运费
	 * @param rs ResultSet
	 * @return
	 */
	protected static wh.shop.model.Shipping getModel(ResultSet rs) throws SQLException{
		wh.shop.model.Shipping m = null;
		if (rs != null)
        {
            m = new wh.shop.model.Shipping();
			try{
				m.setShippingId(rs.getInt("ShippingId"));
				wh.shop.model.Province province = new wh.shop.model.Province();
				province.setProvinceId(rs.getInt("Province_ProvinceId"));
				province.setName(rs.getString("Province_Name"));
				province.setSpellName(rs.getString("Province_SpellName"));
				province.setCreateDate(rs.getTimestamp("Province_CreateDate"));
				m.setProvince(province);
				
				wh.shop.model.City city = new wh.shop.model.City();
				city.setCityId(rs.getInt("City_CityId"));
				city.setName(rs.getString("City_Name"));
				city.setSpellName(rs.getString("City_SpellName"));
				city.setCreateDate(rs.getTimestamp("City_CreateDate"));
				city.setProvince(new wh.shop.model.Province());
				city.getProvince().setProvinceId(rs.getInt("City_ProvinceId"));
				m.setCity(city);
				
				wh.shop.model.District district = new wh.shop.model.District();
				district.setDistrictId(rs.getInt("District_DistrictId"));
				district.setName(rs.getString("District_Name"));
				district.setSpellName(rs.getString("District_SpellName"));
				district.setCreateDate(rs.getTimestamp("District_CreateDate"));
				district.setProvince(new wh.shop.model.Province());
				district.getProvince().setProvinceId(rs.getInt("District_ProvinceId"));
				district.setCity(new wh.shop.model.City());
				district.getCity().setCityId(rs.getInt("District_CityId"));
				m.setDistrict(district);
				
				m.setPrice(rs.getInt("Price"));
			}catch (SQLException e) {
				throw new SQLException(e.getMessage(),e);
			}
        }
        return m;
	} 
}


