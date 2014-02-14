package com.kingdee.eas.ga.util;

import java.io.Serializable;
import java.util.HashMap;

import com.kingdee.bos.Context;
import com.kingdee.eas.auto4s.bdm.pbd.BrandInfo;
import com.kingdee.eas.auto4s.bdm.pbd.IVehicle;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleFactory;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.eas.auto4s.bdm.rsm.IRepairClassify;
import com.kingdee.eas.auto4s.bdm.rsm.IRepairType;
import com.kingdee.eas.auto4s.bdm.rsm.IWarrantyType;
import com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyFactory;
import com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairTypeFactory;
import com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo;
import com.kingdee.eas.auto4s.bdm.rsm.WarrantyTypeFactory;
import com.kingdee.eas.auto4s.bdm.rsm.WarrantyTypeInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.ga.rs.CustomerAccountFactory;
import com.kingdee.eas.ga.rs.CustomerAccountInfo;
import com.kingdee.eas.ga.rs.ICustomerAccount;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

public class GAUtils implements Serializable {
	
	private static HashMap<String, BrandInfo> hashBrand = new HashMap<String, BrandInfo>();
	
	private static void getBrandCol(Context ctx) throws Exception {
		String sql = "select fid,fnumber,fname_l2 from T_ATS_Brand where fnumber='001' or fnumber='002'";
		IRowSet rs = DBUtils.executeQuery(ctx, sql);
		while (rs != null && rs.next()) {
			BrandInfo brandInfo = new BrandInfo();
			brandInfo.put("id", rs.getString("fid"));
			brandInfo.put("number", rs.getString("fnumber"));
			brandInfo.put("name", rs.getString("fname_l2"));
			hashBrand.put(rs.getString("fname_l2"), brandInfo);
		}
	}
	
	/**
	 * 按品牌获取默认“保修类型”
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	public static WarrantyTypeInfo getDefaultWarrantType(Context ctx,BrandInfo brandInfo) throws Exception {
		
		//品牌 
		if (hashBrand.isEmpty()) getBrandCol(ctx);
		BrandInfo defaultBrand_BMW = hashBrand.get("BMW"); //BMW 001
		BrandInfo defaultBrand_MINI = hashBrand.get("MINI"); //MINI 002
    	
		//保修类型
    	IWarrantyType warrantyType = null;
    	if (ctx == null) warrantyType = WarrantyTypeFactory.getRemoteInstance();
    	else  warrantyType = WarrantyTypeFactory.getLocalInstance(ctx); 
    	
    	
    	//DMS保修类型（厦门中宝-宝马） XMZB-DMS-01
    	WarrantyTypeInfo defaultWarrantyType_BWM = warrantyType.getWarrantyTypeInfo(String.format("where number='%s'","XMZB-DMS-01"));
    	//DMS保修类型（厦门中宝-MINI） XMZB-DMS-02
    	WarrantyTypeInfo defaultWarrantyType_MINI = warrantyType.getWarrantyTypeInfo(String.format("where number='%s'","XMZB-DMS-02"));
    
		if (brandInfo == null) 
			throw new EASBizException(new NumericExceptionSubItem("","车辆品牌不能为空"));
		if (PublicUtils.equals(brandInfo, defaultBrand_BMW)) {
			return defaultWarrantyType_BWM;
		} else if (PublicUtils.equals(brandInfo, defaultBrand_MINI)) {
			return defaultWarrantyType_MINI;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("车辆品牌[%s]不正确!",brandInfo.getName())));
		}

	}
	
	
	/**
	 * 按品牌获取默认“维修类型”
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	public static RepairTypeInfo getDefaultRepairType(Context ctx,BrandInfo brandInfo) throws Exception {
		
		//品牌 
		if (hashBrand.isEmpty()) getBrandCol(ctx);
		BrandInfo defaultBrand_BMW = hashBrand.get("BMW"); //BMW 001
		BrandInfo defaultBrand_MINI = hashBrand.get("MINI"); //MINI 002
    	
    	//维修类型
		IRepairType repairType = null;
		if (ctx == null) repairType = RepairTypeFactory.getRemoteInstance();
		else repairType = RepairTypeFactory.getLocalInstance(ctx);
		
		//DMS维修类型（厦门中宝-宝马） XMZB-DMS-01
		RepairTypeInfo defaultRepairType_BMW = repairType.getRepairTypeInfo(String.format("where number='%s'", "XMZB-DMS-01"));
		//DMS维修类型（厦门中宝-MINI）XMZB-DMS-02
		RepairTypeInfo defaultRepairType_MINI = repairType.getRepairTypeInfo(String.format("where number='%s'", "XMZB-DMS-02"));
    	
		if (brandInfo == null) 
			throw new EASBizException(new NumericExceptionSubItem("","车辆品牌不能为空"));
		if (PublicUtils.equals(brandInfo, defaultBrand_BMW)) {
			return defaultRepairType_BMW;
		} else if (PublicUtils.equals(brandInfo, defaultBrand_MINI)) {
			return defaultRepairType_MINI;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("车辆品牌[%s]不正确!",brandInfo.getName())));
		}
	}
	
	
	/**
	 * 按品牌获取默认“维修种类”
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	public static RepairClassifyInfo getDefaultRepairClassify(Context ctx,BrandInfo brandInfo) throws Exception {

		//品牌 
		if (hashBrand.isEmpty()) getBrandCol(ctx);
		BrandInfo defaultBrand_BMW = hashBrand.get("BMW"); //BMW 001
		BrandInfo defaultBrand_MINI = hashBrand.get("MINI"); //MINI 002
    	
    	//维修种类
		IRepairClassify repairClassify = null;
		if (ctx == null) repairClassify = RepairClassifyFactory.getRemoteInstance();
		else repairClassify = RepairClassifyFactory.getLocalInstance(ctx);
		
		//DMS维修类型（厦门中宝-宝马） XMZB-DMS-01
		RepairClassifyInfo defaultRepairClassify_BMW = repairClassify.getRepairClassifyInfo(String.format("where number='%s'", "XMZB-DMS-01"));
		//DMS维修类型（厦门中宝-MINI）XMZB-DMS-02
		RepairClassifyInfo defaultRepairClassify_MINI = repairClassify.getRepairClassifyInfo(String.format("where number='%s'", "XMZB-DMS-02"));
    
    	
		if (brandInfo == null) 
			throw new EASBizException(new NumericExceptionSubItem("","车辆品牌不能为空"));
		if (PublicUtils.equals(brandInfo, defaultBrand_BMW)) {
			return defaultRepairClassify_BMW;
		} else if (PublicUtils.equals(brandInfo, defaultBrand_MINI)) {
			return defaultRepairClassify_MINI;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("车辆品牌[%s]不正确!",brandInfo.getName())));
		}
	}
	
	public static VehicleInfo getDefualtVehicleInfo(Context ctx) throws Exception {
		IVehicle vehicle = null;
		if (ctx == null) vehicle = VehicleFactory.getRemoteInstance();
		else  vehicle = VehicleFactory.getLocalInstance(ctx);
		VehicleInfo defaultVehicleInfo = vehicle.getVehicleInfo("where plateNum='00001' ");
		return defaultVehicleInfo;
	}
	
	public static CustomerAccountInfo getDefaultCustomerAccountInfo(Context ctx) throws Exception  {
		ICustomerAccount customerAccount = null; 
		if (ctx == null) customerAccount = CustomerAccountFactory.getRemoteInstance();
		else customerAccount = CustomerAccountFactory.getLocalInstance(ctx);
		CustomerAccountInfo defaultCustomerAccountInfo = customerAccount.getCustomerAccountInfo("where number='C0000001'");
		return defaultCustomerAccountInfo;
	}
}
