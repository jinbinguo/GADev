package com.kingdee.eas.ga.syncdata.app;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ObjectNotFoundException;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.data.SortType;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemInfo;
import com.kingdee.eas.auto4s.bdm.pbd.BrandInfo;
import com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.eas.auto4s.bdm.rsm.AmountClassifyEnum;
import com.kingdee.eas.auto4s.bdm.rsm.FReturnRepairEnum;
import com.kingdee.eas.auto4s.bdm.rsm.OilQuantityEnum;
import com.kingdee.eas.auto4s.bdm.rsm.PaymentClassifyInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum;
import com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairGroupInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairItemStatusEnum;
import com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo;
import com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum;
import com.kingdee.eas.auto4s.bdm.rsm.WarrantyTypeInfo;
import com.kingdee.eas.auto4s.bdm.util.AutoPriceManager;
import com.kingdee.eas.auto4s.rsm.rs.IRepairWO;
import com.kingdee.eas.auto4s.rsm.rs.RepairWOCollection;
import com.kingdee.eas.auto4s.rsm.rs.RepairWOFactory;
import com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairItemEntryCollection;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairItemEntryInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryCollection;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOTotalAmountEntryCollection;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOTotalAmountEntryInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum;
import com.kingdee.eas.basedata.assistant.CurrencyInfo;
import com.kingdee.eas.basedata.assistant.MeasureUnitInfo;
import com.kingdee.eas.basedata.assistant.PaymentTypeInfo;
import com.kingdee.eas.basedata.master.cssp.SupplierInfo;
import com.kingdee.eas.basedata.master.material.MaterialGroupInfo;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo;
import com.kingdee.eas.basedata.org.SaleOrgUnitInfo;
import com.kingdee.eas.basedata.org.StorageOrgUnitInfo;
import com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.basedata.scm.common.BillTypeInfo;
import com.kingdee.eas.basedata.scm.common.BizTypeInfo;
import com.kingdee.eas.basedata.scm.common.TransactionTypeInfo;
import com.kingdee.eas.basedata.scm.im.inv.StoreStateInfo;
import com.kingdee.eas.basedata.scm.im.inv.StoreTypeInfo;
import com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryCollection;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryInfo;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryFactory;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryInfo;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Collection;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Info;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry3Collection;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry3Info;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntryCollection;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntryInfo;
import com.kingdee.eas.ga.syncdata.DMSWipBillFactory;
import com.kingdee.eas.ga.syncdata.DMSWipBillInfo;
import com.kingdee.eas.ga.syncdata.DMSWipBillTypeEnum;
import com.kingdee.eas.ga.syncdata.IDMSInOutQuery;
import com.kingdee.eas.ga.syncdata.IDMSWipBill;
import com.kingdee.eas.myframework.util.CodingRuleUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.vo.ServerReturnInfo;
import com.kingdee.eas.scm.common.BillBaseStatusEnum;
import com.kingdee.eas.scm.common.PurchaseTypeEnum;
import com.kingdee.eas.scm.im.inv.IOtherInWarehsBill;
import com.kingdee.eas.scm.im.inv.IOtherIssueBill;
import com.kingdee.eas.scm.im.inv.IPurInWarehsBill;
import com.kingdee.eas.scm.im.inv.ISaleIssueBill;
import com.kingdee.eas.scm.im.inv.OtherInWarehsBillCollection;
import com.kingdee.eas.scm.im.inv.OtherInWarehsBillEntryCollection;
import com.kingdee.eas.scm.im.inv.OtherInWarehsBillEntryInfo;
import com.kingdee.eas.scm.im.inv.OtherInWarehsBillFactory;
import com.kingdee.eas.scm.im.inv.OtherInWarehsBillInfo;
import com.kingdee.eas.scm.im.inv.OtherIssueBillCollection;
import com.kingdee.eas.scm.im.inv.OtherIssueBillEntryCollection;
import com.kingdee.eas.scm.im.inv.OtherIssueBillEntryInfo;
import com.kingdee.eas.scm.im.inv.OtherIssueBillFactory;
import com.kingdee.eas.scm.im.inv.OtherIssueBillInfo;
import com.kingdee.eas.scm.im.inv.PurInWarehsBillCollection;
import com.kingdee.eas.scm.im.inv.PurInWarehsBillFactory;
import com.kingdee.eas.scm.im.inv.PurInWarehsBillInfo;
import com.kingdee.eas.scm.im.inv.PurInWarehsEntryCollection;
import com.kingdee.eas.scm.im.inv.PurInWarehsEntryInfo;
import com.kingdee.eas.scm.im.inv.SaleIssueBillCollection;
import com.kingdee.eas.scm.im.inv.SaleIssueBillFactory;
import com.kingdee.eas.scm.im.inv.SaleIssueBillInfo;
import com.kingdee.eas.scm.im.inv.SaleIssueEntryCollection;
import com.kingdee.eas.scm.im.inv.SaleIssueEntryInfo;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

public class SyncDataFacadeControllerBean extends AbstractSyncDataFacadeControllerBean
{
    private static Logger logger =  Logger.getLogger("com.kingdee.eas.ga.syncdata.app.SyncDataFacadeControllerBean");
    
    private IDMSWipBill dmsWipBill = null;
    private IRepairWO repairWO = null;
    
    private IDMSInOutQuery dmsTradeInquire = null;
    private IPurInWarehsBill purInwarehsBill = null;
    private IOtherInWarehsBill otherInwarehsBill = null;
    private ISaleIssueBill saleIssueBill = null;
    private IOtherIssueBill otherIssueBill = null;
    
    private SimpleDateFormat sfYear = new SimpleDateFormat("yyyy");
    private SimpleDateFormat sfMonth = new SimpleDateFormat("yyyyMM");
    private SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat sfPeriod = new SimpleDateFormat("MM");
   
    
    
    protected ServerReturnInfo _syncWipBill(Context ctx, IObjectValue serviceOrgInfo, IObjectPK dmsWipBillPk)
    		throws BOSException,EASBizException {
    	ServerReturnInfo returnInfo = new ServerReturnInfo();
    	returnInfo.setSuccess(true);
    	returnInfo.setExption(false);
    	long start = System.currentTimeMillis();
    	returnInfo.appendSpentMsg("获取DMSWIP单数据耗时(ms):");
    	if (dmsWipBill == null) dmsWipBill = DMSWipBillFactory.getLocalInstance(ctx);
    	if (repairWO == null) repairWO = RepairWOFactory.getLocalInstance(ctx);
    	
    	
    	DMSWipBillInfo dmsWipBillInfo = dmsWipBill.getDMSWipBillInfo(dmsWipBillPk);
    	returnInfo.addSpentMsg(String.valueOf(System.currentTimeMillis()-start));
    	
    	try {
    		HashMap<String,RepairWOInfo> hashRepairWOInfo = new HashMap<String, RepairWOInfo>();
    		//WIP头
    		parseWipHead(ctx,hashRepairWOInfo,dmsWipBillInfo,returnInfo);
    		//零件行
    		parseWipMaterial(ctx,hashRepairWOInfo,dmsWipBillInfo,returnInfo);
    		//工时行
    		parseWipManHour(ctx,hashRepairWOInfo,dmsWipBillInfo,returnInfo);
    		//累计金额合计
    		if (!returnInfo.isExption)
    			parseTotalAmonut(ctx,hashRepairWOInfo,returnInfo);
    		//保存
    		if (!returnInfo.isExption)
    		batchSaveRepariWOInfo(ctx,hashRepairWOInfo);
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new EASBizException(new NumericExceptionSubItem("",PublicUtils.getStackTrace(e)));
    		
    	}

    	return returnInfo;
    }
    
    private void parseWipHead(Context ctx,HashMap<String,RepairWOInfo> hashRepairWOInfo,DMSWipBillInfo dmsWipBillInfo,ServerReturnInfo returnInfo) throws Exception {
    	
    	String expMsg;
    	String spentMsg;
    	String sql;
    	HashMap<String,String> hashWipForStocktaking = new HashMap<String, String>();
    	final String DEFAULT_DMS_SAID = "zfcAAAADYhGA733t"; //DMS顾问 GA2014010063
    	PersonInfo defaultSA = new PersonInfo();
    	defaultSA.put("id", DEFAULT_DMS_SAID);
    	
    	//TODO 默认车辆 -调整
    	final String DEFAULT_DMS_Vehicle = "r1AAAAAAhl+en/9F";
    	VehicleInfo defaultVehicle = new VehicleInfo();
    	defaultVehicle.put("id", DEFAULT_DMS_Vehicle);
    	
    	//品牌 
    	final String DEFAULT_DMS_Brand_BWM = "zfcAAAAABfHINe3g"; //BWM 001
    	BrandInfo defaultBrand_BWM = new BrandInfo();
    	defaultBrand_BWM.put("id", DEFAULT_DMS_Brand_BWM);
    	
    	final String DEFAULT_DMS_Brand_MINI = "zfcAAAAABfLINe3g"; //MINI 002
    	BrandInfo defaultBrand_MINI = new BrandInfo();
    	defaultBrand_MINI.put("id", DEFAULT_DMS_Brand_MINI);
    	
    	
    	//保修类型
    	final String DEFAULT_WarrantyType_BWM = "zfcAAAADYhrW0/uD"; //DMS保修类型（厦门中宝-宝马） XMZB-DMS-01
    	WarrantyTypeInfo defaultWarrantyType_BWM = new WarrantyTypeInfo();
    	defaultWarrantyType_BWM.put("id", DEFAULT_WarrantyType_BWM);
    	
    	final String DEFAULT_WarrantyType_MINI = "zfcAAAADYhvW0/uD"; //DMS保修类型（厦门中宝-MINI）） XMZB-DMS-02
    	WarrantyTypeInfo defaultWarrantyType_MINI = new WarrantyTypeInfo();
    	defaultWarrantyType_MINI.put("id", DEFAULT_WarrantyType_MINI);
    	
    	//维修类型
    	final String DEFAULT_RepairType_BWM = "zfcAAAADYhcA1NDU"; //DMS维修类型（厦门中宝-宝马） XMZB-DMS-01
    	RepairTypeInfo defaultRepairType_BWM = new RepairTypeInfo();
    	defaultRepairType_BWM.put("id",  DEFAULT_RepairType_BWM);
    	
    	final String DEFAULT_RepairType_MINI = "zfcAAAADYhcA1NDU"; //DMS维修类型（厦门中宝-MINI）XMZB-DMS-02
    	RepairTypeInfo defaultRepairType_MINI = new RepairTypeInfo();
    	defaultRepairType_MINI.put("id",  DEFAULT_RepairType_MINI);
    	
    	long start = System.currentTimeMillis();
    	String number = dmsWipBillInfo.getNumber();
    	ServiceOrgUnitInfo serviceOrgInfo = dmsWipBillInfo.getServiceOrgUnit();

    	returnInfo.appendSpentMsg("");
    	DMSWipBillEntryCollection wipHeadCol =  dmsWipBillInfo.getEntrys();
    	
    	for (int i = 0; i < wipHeadCol.size(); i++) {
    		DMSWipBillEntryInfo wipHeadInfo = wipHeadCol.get(i);
    		String accountCode = wipHeadInfo.getAccountCode();
    		
    		int seq = wipHeadInfo.getSeq();
    		Date creatTime = wipHeadInfo.getCreateTime();
    		if (creatTime == null) {
    			expMsg = String.format("DMSWIP单-[WIP头],第%d行,创建日期不能为空", seq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		//--wip号
    		String wip = wipHeadInfo.getWip();
    		try {
    			int wipNo = Integer.valueOf(wip);
    			if (wipNo < 10001 || wipNo > 65000) {
    				expMsg = String.format("DMSWIP单-[WIP头],第%d行,WIP号[%s]不合法，应介于10001~65000之间",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
    			}
    		} catch (Exception e) {
    			expMsg = String.format("DMSWIP单-[WIP头],第%d行,WIP号[%s]格式不正确",seq,wip);
    			addExceptionMsg(returnInfo, expMsg);
    		}
    		//wip自定义识别符，通过win + "-" + 创建年份
    		
    		String keyWip = wip + "-" + String.valueOf(sfYear.format(creatTime));
    		
    		if (isIgnoreWipForStocktaking(accountCode)) { 
    			//TODO 添加盘点WIP
    			hashWipForStocktaking.put(keyWip, keyWip);
    			
    			continue;
    		}
    		

    		
    		
    		//-----------查询维修工单，有可能更新维修工单
    		long l = System.currentTimeMillis();
    		RepairWOInfo repairWOInfo = null;
    		try {
    			sql = String.format("select FID,FNumber from T_ATS_RepairWO where FOldID='%s'",keyWip);
    			IRowSet rs = DbUtil.executeQuery(ctx, sql);
    			if (rs != null && rs.next()) {
    				IObjectPK pk = new ObjectUuidPK(rs.getString("FID"));
        			repairWOInfo = repairWO.getRepairWOInfo(pk);
    			}
    			spentMsg = String.format("DMSWIP单-[WIP头],第%d行,getRepairWOInfo耗时(ms):%s", seq,String.valueOf(System.currentTimeMillis()-l));
    			returnInfo.addSpentMsg(spentMsg);
    		} catch (Exception ee) {
    			if (ee instanceof ObjectNotFoundException) {
    				spentMsg = String.format("DMSWIP单-[WIP头],第%d行,不存在维修工单getRepairWOInfo耗时(ms):%d", seq, String.valueOf(System.currentTimeMillis()-l));
    				returnInfo.addSpentMsg(spentMsg);
    			} else {
    				expMsg = String.format("DMSWIP单-[WIP头],第%d行,WIP号[%s]重复",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
    			}
    			ee.printStackTrace();
    		}
    		if (repairWOInfo == null) repairWOInfo = new RepairWOInfo();
    		
    		//服务顾问
    		repairWOInfo.setSA(defaultSA);
    		
    		if (hashRepairWOInfo.get(keyWip) != null) {
    			expMsg = String.format("DMSWIP单-[WIP头],第%d行,WIP号[%s]重复",seq,wip);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		//厂家编号
    		repairWOInfo.setCompanyNumber(wip);
    		//底盘号
    		String vin = wipHeadInfo.getVin();
    		if ("".equals(vin)) {
    			repairWOInfo.setVehicle(defaultVehicle);
    		} else {
    			sql = String.format("select FID,FOrderCustomerID,FOwnerName,FPhone,FBrandID from T_ATS_Vehicle where FVIN='%s'", vin);
    			IRowSet rs = DbUtil.executeQuery(ctx, sql);
    			if (rs == null || !rs.next()) {
    				expMsg = String.format("DMSWIP单-[WIP头],第%d行,底盘号[%s]不存在于基础车辆档案",seq,vin);
        			addExceptionMsg(returnInfo, expMsg);
    			} else {
    				//rs.next();
    				//车辆
    				VehicleInfo vehicleInfo = new VehicleInfo();
    				vehicleInfo.put("id", rs.getString("FID"));
    				repairWOInfo.setVehicle(vehicleInfo);
    				//客户
    				CustomerInfo customerInfo = new CustomerInfo();
    				customerInfo.put("id", rs.getString("FOrderCustomerID"));
    				repairWOInfo.setCustomer(customerInfo);
    				vehicleInfo.setCustomer(customerInfo);
    				//送修人
    				repairWOInfo.setRepairSender(rs.getString("FOwnerName"));
 
    				//送修人电话 
    				repairWOInfo.setTel(rs.getString("FPhone"));
    				//品牌
    				BrandInfo brandInfo = new BrandInfo();
    				brandInfo.put("id", rs.getString("FBrandID"));
    				vehicleInfo.setBrand(brandInfo);
    				repairWOInfo.setBrand(brandInfo);

    			}
    		}
    
    		//公司
    		repairWOInfo.setOrgUnit(serviceOrgInfo);
    		//单据状态
    		repairWOInfo.setStatus(RepairBillStatusEnum.SENDING);
    		//进厂时间
    		repairWOInfo.setComeTime(creatTime);
    		//预计交车时间
    		repairWOInfo.setIntendDeliveryTime(creatTime);
    		//维修费用合计    		repairWOInfo.setRepairTotalAmount(item)
    		//已收款金额合计    		repairWOInfo.setReceiveTotalAmount(item)
    		//已开票金额合计			repairWOInfo.setInvoicedTotalAmount(item)
    		
    		//是否计算进出厂台次
    		repairWOInfo.setIsStat(true);
    		//进厂里程数
    		repairWOInfo.setMile(wipHeadInfo.getMileage());
    		//油量
    		repairWOInfo.setOilQty(OilQuantityEnum.ZERO);
    		if (DEFAULT_DMS_Brand_BWM.equals(repairWOInfo.getBrand().getString("id"))) {
    			//保修类型
        		repairWOInfo.setWarrantyType(defaultWarrantyType_BWM);
        		//维修类型
        		repairWOInfo.setRepairType(defaultRepairType_BWM);
    			
    		} else if (DEFAULT_DMS_Brand_MINI.equals(repairWOInfo.getBrand().getString("id"))) {
    			//保修类型
        		repairWOInfo.setWarrantyType(defaultWarrantyType_MINI);
        		//维修类型
        		repairWOInfo.setRepairType(defaultRepairType_BWM);
    		}
    		
    		
    		//是否在店等待
    		repairWOInfo.setIsWaitForStore(true);
    		//原工单号 --WIP号+年份
    		repairWOInfo.setOldID(keyWip);
    		//返修类型
    		repairWOInfo.setReturnRepair(FReturnRepairEnum.未返修);
    		//维修方式
    		repairWOInfo.setRepairWay(RepairWayEnum.DEFAULT);
    		//业务日期
    		repairWOInfo.setBizDate(creatTime);
    		
    		hashRepairWOInfo.put(keyWip, repairWOInfo);
    	}
    	//保存盘点WIP
    	
    	String[] keyWips = PublicUtils.hashKeyToArray(hashWipForStocktaking);
    	if (keyWips != null && keyWips.length > 0) {
	    	sql = "insert into CT_SYN_DMSCheckWipNo(FID,CFServiceOrgUnitID,CFKeyWip) " +
	    			"values (newbosid('92B6011A'),'" + serviceOrgInfo.getString("id") + "',?)";
	    	DbUtil.execute(ctx, sql, keyWips);
    	}

    	
    	spentMsg = String.format("获取WIP头单数据耗时(ms):%s", String.valueOf(System.currentTimeMillis()-start));
    	returnInfo.addSpentMsg(spentMsg);
    }
    
    private void parseWipMaterial(Context ctx,HashMap<String,RepairWOInfo> hashRepairWOInfo,DMSWipBillInfo dmsWipBillInfo,ServerReturnInfo returnInfo) throws Exception {
    	String expMsg;
    	String spentMsg;
    	String sql;
    	
    	//付费类别 DMS付费类别
    	final String DEFAULT_Paymentclassify = "zfcAAAADYhjnpYpd";
    	PaymentClassifyInfo defaultPaymentclassify = new PaymentClassifyInfo();
    	defaultPaymentclassify.put("id", DEFAULT_Paymentclassify);
    	
    	//维修种类 
    	final String DEFAULT_RepairClassify_BWM = "zfcAAAADYh5HVb8e"; //DMS维修种类（厦门中宝-宝马） XMZB-DMS-01
    	RepairClassifyInfo defaultRepairClassify_BWM = new RepairClassifyInfo();
    	defaultRepairClassify_BWM.put("id", DEFAULT_RepairClassify_BWM);
    	
    	final String DEFAULT_RepairClassify_MINI = "zfcAAAADYh9HVb8e"; //DMS维修种类（厦门中宝-MINI） XMZB-DMS-02
    	RepairClassifyInfo defaultRepairClassify_MINI = new RepairClassifyInfo();
    	defaultRepairClassify_MINI.put("id", DEFAULT_RepairClassify_MINI);
    	
    	//品牌 
    	final String DEFAULT_DMS_Brand_BWM = "zfcAAAAABfHINe3g"; //BWM 001
    	BrandInfo defaultBrand_BWM = new BrandInfo();
    	defaultBrand_BWM.put("id", DEFAULT_DMS_Brand_BWM);
    	
    	final String DEFAULT_DMS_Brand_MINI = "zfcAAAAABfLINe3g"; //MINI 002
    	BrandInfo defaultBrand_MINI = new BrandInfo();
    	defaultBrand_MINI.put("id", DEFAULT_DMS_Brand_MINI);
    	
    	long start = System.currentTimeMillis();
    	DMSWipBillEntry2Collection wipMaterialCol =  dmsWipBillInfo.getEntry2();
    	HashMap<String,Date> hashAdjustDate = new HashMap<String, Date>();
    	HashMap<String,MaterialInfo> hashMaterial = new HashMap<String,MaterialInfo>();
    	HashMap<String,HashMap<Integer,RepairWORWOSparepartEntryInfo>> hashSparepart = new HashMap<String, HashMap<Integer,RepairWORWOSparepartEntryInfo>>();
    	for (int i = 0; i < wipMaterialCol.size(); i++) {
    		DMSWipBillEntry2Info wipMaterialInfo = wipMaterialCol.get(i);
    		String accountCode = wipMaterialInfo.getAccountCode();
    		if (isIgnoreWipForStocktaking(accountCode)) continue;
    		int seq = wipMaterialInfo.getSeq();
    		int lineSeq = wipMaterialInfo.getLineSeq();
    		int factLineSeq = wipMaterialInfo.getRealLineSeq();
    		if (lineSeq<1) {
    			expMsg = String.format("DMSWIP单-[零件行],第%d行,行号[%s]不合法,应于大于1整数",seq,lineSeq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		String wip = wipMaterialInfo.getWip(); 
    		try {
    			int wipNo = Integer.valueOf(wip);
    			if (wipNo < 10001 || wipNo > 65000) {
    				expMsg = String.format("DMSWIP单-[零件行],第%d行,WIP号[%s]不合法，应介于10001~65000之间",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
    			}
    		} catch (Exception e) {
    			expMsg = String.format("DMSWIP单-[零件行],第%d行,WIP号[%s]格式不正确",seq,wip);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		
    		Date editDate = hashAdjustDate.get(wip); //先查看是否被调整过
    		if (editDate == null) editDate = wipMaterialInfo.getLastEditTime();
    		long l = System.currentTimeMillis();
    		String keyWip = wip + "-" + String.valueOf(sfYear.format(editDate));
    		//ServiceOrgUnitInfo serviceOrgUnit = dmsWipBillInfo.getServiceOrgUnit();
    		
    		RepairWOInfo repairWOInfo = hashRepairWOInfo.get(keyWip);
    		if (repairWOInfo == null) { //在当前WIP头里，找不到
    			//从维修工单里重新取,取当前编辑年份或当前编辑前一年份，按单据日期
    			Calendar calendar = new GregorianCalendar();
    			calendar.setTime(editDate);
    			calendar.add(Calendar.YEAR, -1);

    			String keyWip2 = wip + "-" + String.valueOf(sfYear.format(calendar.getTime()));
    			EntityViewInfo entityViewInfo = new EntityViewInfo();
    			FilterInfo filterInfo = new FilterInfo();
    			filterInfo.getFilterItems().add(new FilterItemInfo("OldID", keyWip));
    			filterInfo.getFilterItems().add(new FilterItemInfo("oldID", keyWip2));
    			filterInfo.setMaskString("#0 or #1");
    			entityViewInfo.setFilter(filterInfo);
    			SorterItemCollection sorterItemCol = new SorterItemCollection();
    			SorterItemInfo scBizDate = new SorterItemInfo("bizDate");
    			scBizDate.setSortType(SortType.DESCEND);
    			sorterItemCol.add(scBizDate);
    			entityViewInfo.setSorter(sorterItemCol);
    			RepairWOCollection repairWOCol =  repairWO.getRepairWOCollection(entityViewInfo);
    			if (!repairWOCol.isEmpty()) {
    				repairWOInfo = repairWOCol.get(0); //取前面一个
    				hashAdjustDate.put(wip, repairWOInfo.getBizDate()); //调整时间 为维修工单的业务时间
    			} else {
    				expMsg = String.format("DMSWIP单-[零件行],第%d行,不存在WIP号[%s]的维修工单",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
    				continue;
    			}
    			hashRepairWOInfo.put(keyWip, repairWOInfo);
    		}
    		spentMsg = String.format("DMSWIP单-[零件行]查找DMSWIP单数据耗时(ms):%s", String.valueOf(System.currentTimeMillis()-l));
    		returnInfo.addSpentMsg(spentMsg);
    		
    		HashMap<Integer,RepairWORWOSparepartEntryInfo> hashSparepartEntry = null;
    		hashSparepartEntry = hashSparepart.get(wip);
    		if (hashSparepartEntry == null)	{
    			hashSparepartEntry = new HashMap<Integer, RepairWORWOSparepartEntryInfo>();
    			RepairWORWOSparepartEntryCollection sparepartCol = repairWOInfo.getRWOSparepartEntry();
    			for (int j = 0; j < sparepartCol.size(); j++) {
    				RepairWORWOSparepartEntryInfo sparepartEntryInfo = sparepartCol.get(j);
    				int wipLineNo = sparepartEntryInfo.getInt("wipLineNo");
    				hashSparepartEntry.put(wipLineNo, sparepartEntryInfo);	
    			}
    			hashSparepart.put(wip, hashSparepartEntry);
    		}
    		RepairWORWOSparepartEntryInfo sparepartEntryInfo = hashSparepartEntry.get(lineSeq);
    		if (sparepartEntryInfo == null) {
    			sparepartEntryInfo = new RepairWORWOSparepartEntryInfo();
    			hashSparepartEntry.put(lineSeq, sparepartEntryInfo);
    			repairWOInfo.getRWOSparepartEntry().add(sparepartEntryInfo);
    		}
    		
    		//DMS销售金额(不含税)
    		BigDecimal saleNoTaxAmount = wipMaterialInfo.getSalePrice();
    		if (saleNoTaxAmount == null || saleNoTaxAmount.compareTo(BigDecimal.ZERO) <= 0) {
    			expMsg = String.format("DMSWIP单-[零件行],第%d行,销售金额必须大于0",seq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		l = System.currentTimeMillis();
    		
    		//DMS行号
    		sparepartEntryInfo.put("wipLineNo", lineSeq);
    		//DMS实际行号
    		sparepartEntryInfo.put("wipFactLineNo", factLineSeq);
    		
    		//结算对象
    		sparepartEntryInfo.setSettleObject(SettlementObjectEnum.CUST);
    		//数量/DMS定货数量
    		BigDecimal qty = wipMaterialInfo.getOrderQty();
    		if (qty == null || qty.compareTo(BigDecimal.ZERO) <= 0) {
    			expMsg = String.format("DMSWIP单-[零件行],第%d行,定货数量必须大于0",seq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		sparepartEntryInfo.setQty(qty);
    		//DMS销售单价(不含税)
    		BigDecimal saleNoTaxPrice = saleNoTaxAmount.divide(qty,10,BigDecimal.ROUND_HALF_UP);
    		//税率 17.0%
    		BigDecimal taxRate = new BigDecimal(17.00);
    		sparepartEntryInfo.setTaxRate(taxRate);
    		
    		//含税单价 =(1+税率%/100)*DMS销售单价(不含税)
    		BigDecimal saleTaxPrice = saleNoTaxPrice.multiply(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)));
    		sparepartEntryInfo.setTaxPrice(saleTaxPrice);
    		
    		//不含税单价=DMS销售单价(不含税)
    		sparepartEntryInfo.setNoTaxPrice(saleNoTaxPrice);
    		//含税金额=含税单价*数量
    		BigDecimal saleTaxAmount = saleTaxPrice.multiply(qty);
    		sparepartEntryInfo.setTaxAmount(saleTaxAmount);
    		//不含税金额=DMS销售单价(不含税)*数量
    		sparepartEntryInfo.setNoTaxAmount(saleNoTaxPrice.multiply(qty));
    		
    		//税额=不含税单价*税率/100
    		sparepartEntryInfo.setTax(saleNoTaxPrice.multiply(taxRate.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)));
    		//折扣率
    		BigDecimal discountRate = wipMaterialInfo.getDiscountRate();
    		sparepartEntryInfo.setDiscountRate(discountRate);
    		//优惠金额=含税金额*折扣率/100
    		BigDecimal discountAmount = saleTaxAmount.multiply(discountRate.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP));
    		sparepartEntryInfo.setDiscountAmount(discountAmount);
    		//应收金额=含税金额-优惠金额
    		sparepartEntryInfo.setARAmount(saleTaxAmount.subtract(discountAmount));
    		//实收金额=含税金额-优惠金额
    		sparepartEntryInfo.setActualAmount(saleTaxAmount.subtract(discountAmount));
    		
    		//TODO 是否删除，DMS定货状态="D",则为作废行，这个可能与EAS的是否删除一样，待确认    
    		if ("D".equals(wipMaterialInfo.getOrderStatus())) {
    			sparepartEntryInfo.setIsDelete(true);
    		}
    		//付费类别
    		sparepartEntryInfo.setPaymentClassify(defaultPaymentclassify);
    		//维修种类
    		if (defaultBrand_BWM.equals(repairWOInfo.getBrand().getString("id"))) {
    			sparepartEntryInfo.setRepairClassify(defaultRepairClassify_BWM);
    		} else if (defaultBrand_MINI.equals(repairWOInfo.getBrand().getString("id"))) {
    			sparepartEntryInfo.setRepairClassify(defaultRepairClassify_MINI);
    		}

    		//物料
    		String materialNum = wipMaterialInfo.getMaterialNum();
    		if (PublicUtils.isEmpty(materialNum)) {
    			expMsg = String.format("DMSWIP单-[零件行],第%d行,零件编号不能为空",seq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		spentMsg = String.format("DMSWIP单-[零件行]赋值sparepartEntryInfo耗时(ms):%s", String.valueOf(System.currentTimeMillis()-l));
    		returnInfo.addSpentMsg(spentMsg);
    		l = System.currentTimeMillis();

    		MaterialInfo materialInfo = hashMaterial.get(materialNum);
    		if (materialInfo == null) {
    			sql = String.format("select FID,FBaseUnit,FMaterialGroupID from T_BD_Material where FNumber='%s'", materialNum);
    			IRowSet rs = DbUtil.executeQuery(ctx, sql);
    			if (rs != null && rs.next()) {
    				materialInfo = new MaterialInfo();
    				materialInfo.put("id", rs.getString("FID"));
    				MeasureUnitInfo unitInfo = new MeasureUnitInfo();
    				unitInfo.put("id", rs.getString("FBaseUnit"));
    				materialInfo.setBaseUnit(unitInfo);
    				MaterialGroupInfo materialGroupInfo = new MaterialGroupInfo();
    				materialGroupInfo.put("id", rs.getString("FMaterialGroupID"));
    				materialInfo.setMaterialGroup(materialGroupInfo);
    				spentMsg = String.format("DMSWIP单-[零件行]查找物料数据耗时(ms):%s", String.valueOf(System.currentTimeMillis()-l));
    				returnInfo.addSpentMsg(spentMsg);
    				hashMaterial.put(materialNum, materialInfo);
    			} else {
    				expMsg = String.format("DMSWIP单-[零件行],第%d行,零件编号[%s]不存在于EAS系统",seq,materialNum);
        			addExceptionMsg(returnInfo, expMsg);
        			spentMsg = String.format("DMSWIP单-[零件行]查找物料数据耗时(ms):%s", String.valueOf(System.currentTimeMillis()-l));
        			returnInfo.addSpentMsg(spentMsg);
        			continue;
    			}
    		}
    		
    		
    		
    		sparepartEntryInfo.setMaterial(materialInfo);
    		//计量单位
    		sparepartEntryInfo.setUnit(materialInfo.getBaseUnit());
    		//未出库数
    		sparepartEntryInfo.setNoIssueQty(qty);
    		//基本数量
    		sparepartEntryInfo.setBaseQty(qty);
    		//基本计量单位
    		sparepartEntryInfo.setBaseUnit(materialInfo.getBaseUnit());
    		//物料分类
    		sparepartEntryInfo.setMaterialGroup(materialInfo.getMaterialGroup());
    	}
    	
    	spentMsg = String.format("获取零件行数据耗时(ms):%s", String.valueOf(System.currentTimeMillis()-start));
    	returnInfo.addSpentMsg(spentMsg);
    }
    
    private void parseWipManHour(Context ctx,HashMap<String,RepairWOInfo> hashRepairWOInfo,DMSWipBillInfo dmsWipBillInfo,ServerReturnInfo returnInfo) throws Exception {
       	String expMsg;
    	String spentMsg;
    	String sql;
    	
    	//品牌 
    	final String DEFAULT_DMS_Brand_BWM = "zfcAAAAABfHINe3g"; //BWM 001
    	BrandInfo defaultBrand_BWM = new BrandInfo();
    	defaultBrand_BWM.put("id", DEFAULT_DMS_Brand_BWM);
    	
    	final String DEFAULT_DMS_Brand_MINI = "zfcAAAAABfLINe3g"; //MINI 002
    	BrandInfo defaultBrand_MINI = new BrandInfo();
    	defaultBrand_MINI.put("id", DEFAULT_DMS_Brand_MINI);
    	
    	//付费类别 DMS付费类别
    	final String DEFAULT_Paymentclassify = "zfcAAAADYhjnpYpd";
    	PaymentClassifyInfo defaultPaymentclassify = new PaymentClassifyInfo();
    	defaultPaymentclassify.put("id", DEFAULT_Paymentclassify);
    	
    	//维修种类 
    	final String DEFAULT_RepairClassify_BWM = "zfcAAAADYh5HVb8e"; //DMS维修种类（厦门中宝-宝马） XMZB-DMS-01
    	RepairClassifyInfo defaultRepairClassify_BWM = new RepairClassifyInfo();
    	defaultRepairClassify_BWM.put("id", DEFAULT_RepairClassify_BWM);
    	
    	final String DEFAULT_RepairClassify_MINI = "zfcAAAADYh9HVb8e"; //DMS维修种类（厦门中宝-MINI） XMZB-DMS-02
    	RepairClassifyInfo defaultRepairClassify_MINI = new RepairClassifyInfo();
    	defaultRepairClassify_MINI.put("id", DEFAULT_RepairClassify_MINI);
    	
    	//维修班组
    	final String DEFAULT_RepairGroup_BWM = "zfcAAAADYjAZCugl"; //DMS维修班组（厦门中宝-宝马） XMZB-DMS-01
    	RepairGroupInfo defaultRepairGroup_BWM = new RepairGroupInfo();
    	defaultRepairGroup_BWM.put("id", DEFAULT_RepairGroup_BWM);
    	
    	final String DEFAULT_RepairGroup_MINI = "zfcAAAADYjIZCugl"; //DMS维修班组（厦门中宝-MINI） XMZB-DMS-02
    	RepairGroupInfo defaultRepairGroup_MINI = new RepairGroupInfo();
    	defaultRepairGroup_MINI.put("id", DEFAULT_RepairGroup_MINI);
    	
    	
    	//维修项目  
    	final String DEFAULT_RepairItem_BWM = "zfcAAAADYiAAz7yt"; //DMS维修项目（厦门中宝-宝马）XMZB-DMS-01
    	RepairItemInfo defaultRepairItem_BWM = new RepairItemInfo();
    	defaultRepairItem_BWM.put("id", DEFAULT_RepairItem_BWM);
    	
    	final String DEFAULT_RepairItem_MINI = "zfcAAAADYiIAz7yt"; //DMS维修项目（厦门中宝-MINI）XMZB-DMS-02
    	RepairItemInfo defaultRepairItem_MINI = new RepairItemInfo();
    	defaultRepairItem_MINI.put("id", DEFAULT_RepairItem_MINI);
    	
    	//维修类型
    	final String DEFAULT_RepairType_BWM = "zfcAAAADYhcA1NDU"; //DMS维修类型（厦门中宝-宝马）XMZB-DMS-01
    	RepairTypeInfo defaultRepairType_BWM = new RepairTypeInfo();
    	defaultRepairType_BWM.put("id",  DEFAULT_RepairType_BWM);
    	
    	final String DEFAULT_RepairType_MINI = "zfcAAAADYhkA1NDU"; //DMS维修类型（厦门中宝-MINI）XMZB-DMS-02
    	RepairTypeInfo defaultRepairType_MINI = new RepairTypeInfo();
    	defaultRepairType_MINI.put("id",  DEFAULT_RepairType_MINI);
    	
    	long start = System.currentTimeMillis();
    	DMSWipBillEntry3Collection wipManHourCol =  dmsWipBillInfo.getEntry3();
    	HashMap<String,Date> hashAdjustDate = new HashMap<String, Date>();
    	//HashMap<String,MaterialInfo> hashRepairItem = new HashMap<String,MaterialInfo>();
    	HashMap<String,HashMap<Integer,RepairWORWORepairItemEntryInfo>> hashRWORepairItem = new HashMap<String, HashMap<Integer,RepairWORWORepairItemEntryInfo>>();
    	for (int i = 0; i < wipManHourCol.size(); i++) {
    		DMSWipBillEntry3Info wipManHourInfo = wipManHourCol.get(i);
    		String accountCode = wipManHourInfo.getAccountCode();
    		if (isIgnoreWipForStocktaking(accountCode)) continue;
    		int seq = wipManHourInfo.getSeq();
    		int lineSeq = wipManHourInfo.getLineSeq();
    		int factLineSeq = wipManHourInfo.getRealLineSeq();
    		if (lineSeq<1) {
    			expMsg = String.format("DMSWIP单-[工时行],第%d行,行号[%s]不合法,应大于等于1",seq,lineSeq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		String wip = wipManHourInfo.getWip(); 
    		try {
    			int wipNo = Integer.valueOf(wip);
    			if (wipNo < 10001 || wipNo > 65000) {
    				expMsg = String.format("DMSWIP单-[工时行],第%d行,WIP号[%s]不合法，应介于10001~65000之间",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
    			}
    		} catch (Exception e) {
    			expMsg = String.format("DMSWIP单-[工时行],第%d行,WIP号[%s]格式不正确",seq,wip);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		
    		Date editDate = hashAdjustDate.get(wip); //先查看是否被调整过
    		if (editDate == null) editDate = wipManHourInfo.getLastEditTime();
    		
    		String keyWip = wip + "-" + String.valueOf(sfYear.format(editDate));
    		//ServiceOrgUnitInfo serviceOrgUnit = dmsWipBillInfo.getServiceOrgUnit();
    		
    		RepairWOInfo repairWOInfo = hashRepairWOInfo.get(keyWip);
    		long l = System.currentTimeMillis();
    		if (repairWOInfo == null) { //在当前WIP头里，找不到
    			//从维修工单里重新取,取当前编辑年份或当前编辑前一年份，按单据日期
    			Calendar calendar = new GregorianCalendar();
    			calendar.setTime(editDate);
    			calendar.add(Calendar.YEAR, -1);

    			String keyWip2 = wip + "-" + String.valueOf(sfYear.format(calendar.getTime()));
    			EntityViewInfo entityViewInfo = new EntityViewInfo();
    			FilterInfo filterInfo = new FilterInfo();
    			filterInfo.getFilterItems().add(new FilterItemInfo("OldID", keyWip));
    			filterInfo.getFilterItems().add(new FilterItemInfo("oldID", keyWip2));
    			filterInfo.setMaskString("#0 or #1");
    			entityViewInfo.setFilter(filterInfo);
    			SorterItemCollection sorterItemCol = new SorterItemCollection();
    			SorterItemInfo scBizDate = new SorterItemInfo("bizDate");
    			scBizDate.setSortType(SortType.DESCEND);
    			sorterItemCol.add(scBizDate);
    			entityViewInfo.setSorter(sorterItemCol);
    			RepairWOCollection repairWOCol =  repairWO.getRepairWOCollection(entityViewInfo);
    			if (!repairWOCol.isEmpty()) {
    				repairWOInfo = repairWOCol.get(0); //取前面一个
    				hashAdjustDate.put(wip, repairWOInfo.getBizDate()); //调整时间 为维修工单的业务时间
    			} else {
    				expMsg = String.format("DMSWIP单-[工时行],第%d行,不存在WIP号[%s]的维修工单",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
    				continue;
    			}
    			hashRepairWOInfo.put(keyWip, repairWOInfo);
    		}
    		spentMsg = String.format("DMSWIP单-[工时行]查找DMSWIP单数据耗时(ms):%s", String.valueOf(System.currentTimeMillis()-l));
    		returnInfo.addSpentMsg(spentMsg);
    		HashMap<Integer,RepairWORWORepairItemEntryInfo> hashRepairItemEntry = null;
    		hashRepairItemEntry = hashRWORepairItem.get(wip);
    		if (hashRepairItemEntry == null)	{
    			hashRepairItemEntry = new HashMap<Integer, RepairWORWORepairItemEntryInfo>();
    			RepairWORWORepairItemEntryCollection repairItemCol = repairWOInfo.getRWORepairItemEntry();
    			for (int j = 0; j < repairItemCol.size(); j++) {
    				RepairWORWORepairItemEntryInfo repairItemEntryInfo = repairItemCol.get(j);
    				int wipLineNo = repairItemEntryInfo.getInt("wipLineNo");
    				hashRepairItemEntry.put(wipLineNo, repairItemEntryInfo);	
    			}
    			hashRWORepairItem.put(wip, hashRepairItemEntry);
    		}
    		RepairWORWORepairItemEntryInfo repairItemEntryInfo = hashRepairItemEntry.get(lineSeq);
    		if (repairItemEntryInfo == null) {
    			repairItemEntryInfo = new RepairWORWORepairItemEntryInfo();
    			hashRepairItemEntry.put(lineSeq, repairItemEntryInfo);
    			repairWOInfo.getRWORepairItemEntry().add(repairItemEntryInfo);
    		}

    		//DMS行号
    		repairItemEntryInfo.put("wipLineNo", lineSeq);
    		//DMS实际行号
    		repairItemEntryInfo.put("wipFactLineNo", factLineSeq);
    		//付费类别
    		repairItemEntryInfo.setPaymentClassify(defaultPaymentclassify);
    		//结算对象-客户
    		repairItemEntryInfo.setSettleObject(SettlementObjectEnum.CUST);
    		if (DEFAULT_DMS_Brand_BWM.equals(repairWOInfo.getBrand().getString("id"))) {
    			//维修种类
        		repairItemEntryInfo.setRepairClassify(defaultRepairClassify_BWM);
        		//维修班组
        		repairItemEntryInfo.setWorkGroup(defaultRepairGroup_BWM);
        		//维修项目
        		repairItemEntryInfo.setRepairItem(defaultRepairItem_BWM);
        		//维修类型
        		repairItemEntryInfo.setRepairType(defaultRepairType_BWM);
    			
    		} else if (DEFAULT_DMS_Brand_MINI.equals(repairWOInfo.getBrand().getString("id"))) {
    			//维修种类
        		repairItemEntryInfo.setRepairClassify(defaultRepairClassify_MINI);
        		//维修班组
        		repairItemEntryInfo.setWorkGroup(defaultRepairGroup_MINI);
        		//维修项目
        		repairItemEntryInfo.setRepairItem(defaultRepairItem_MINI);
        		//维修类型
        		repairItemEntryInfo.setRepairType(defaultRepairType_MINI);
    		}
    		
    		//标准工时
    		BigDecimal standardHour = wipManHourInfo.getStandardHour();
    		repairItemEntryInfo.setStdWorkTime(standardHour);
    		//工时单价=DMS小时工时率/(60/DMS单位分钟数)，DMS单位分钟数为0则工时单价为0
    		BigDecimal workTimePrice = null;
    		BigDecimal hourRate = wipManHourInfo.getHourRate(); //小时工时率
    		int unitMI = wipManHourInfo.getUnitMI(); //单价分钟数
    		if (hourRate == null || unitMI == 0) {
    			workTimePrice = BigDecimal.ZERO;	
    		} else {
    			workTimePrice = hourRate.divide(new BigDecimal(60).divide(new BigDecimal(unitMI),10,BigDecimal.ROUND_HALF_UP),10,BigDecimal.ROUND_HALF_UP);
    		}
    		repairItemEntryInfo.setWorkTimePrice(workTimePrice);
    		//工时金额=标准工时*工时单价
    		BigDecimal workTimeAmount = wipManHourInfo.getStandardHour().multiply(workTimePrice);
    		repairItemEntryInfo.setWorkTimeAmount(workTimeAmount);
    		//折扣率
    		BigDecimal discountRate = wipManHourInfo.getDiscountRate();
    		repairItemEntryInfo.setDiscountRate(discountRate);
    		//折扣金额
    		BigDecimal discountAmount = workTimeAmount.multiply(discountRate);
    		//应收金额=工时金额-折扣金额
    		repairItemEntryInfo.setARAmount(workTimeAmount.subtract(discountAmount));
    		//TODO 维修项目状态
    		repairItemEntryInfo.setItemStatus(RepairItemStatusEnum.SENDING);
    		//实际工时=标准工时
    		repairItemEntryInfo.setActualWorkTime(standardHour);
    		//维修项目说明
    		repairItemEntryInfo.setItemRemark(wipManHourInfo.getRemark());
    		//派工工时=标准工时
    		repairItemEntryInfo.setAssignWorkTime(standardHour);
    		//实收金额=工时金额-折扣金额
    		repairItemEntryInfo.setActualAmount(workTimeAmount.subtract(discountAmount));
    		
    		//工资单价=DMS工时单价
    		repairItemEntryInfo.setWagePrice(workTimePrice);
    		//工时成本=DMS工时单价 * 标准工时
    		repairItemEntryInfo.setWorkTimeCost(workTimePrice.multiply(standardHour));
    		
    		//工时标准单价=DMS工时单价
    		repairItemEntryInfo.setWorkTimeStdPrice(workTimePrice);
    	}
    	
    	spentMsg = String.format("获取工时行数据耗时(ms):%s", String.valueOf(System.currentTimeMillis()-start));
    	returnInfo.addSpentMsg(spentMsg);
    }
    
    private void parseTotalAmonut(Context ctx,HashMap<String,RepairWOInfo> hashRepairWOInfo,ServerReturnInfo returnInfo) throws Exception {
    	String expMsg = "";
    	String spentMsg = "";
    	Set<String> setKey = hashRepairWOInfo.keySet();
    	Iterator<String> itKey = setKey.iterator();
    	long start = System.currentTimeMillis();
    	while(itKey.hasNext()) {
    		long l = System.currentTimeMillis();
    		HashMap<SettlementObjectEnum,TotalAmountBySettObj> hashTotalAmountBySettle = new HashMap<SettlementObjectEnum, TotalAmountBySettObj>();
    		String key =itKey.next();
    		RepairWOInfo repairWOInfo = hashRepairWOInfo.get(key);
    		String wip = repairWOInfo.getCompanyNumber();
    		RepairWORWOSparepartEntryCollection sparepartEntryCol = repairWOInfo.getRWOSparepartEntry(); //配件
    		RepairWORWORepairItemEntryCollection repairItemEntryCol = repairWOInfo.getRWORepairItemEntry(); //维修项目
    		if (sparepartEntryCol.isEmpty() && repairItemEntryCol.isEmpty()) {
    			expMsg = String.format("DMSWIP单-WIP号[%s]同时不存在零件行或工时行，不能保存",wip);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		
    		 //配件
    		for (int i = 0;i < sparepartEntryCol.size(); i++) {
    			RepairWORWOSparepartEntryInfo sparepartEntryInfo = sparepartEntryCol.get(i);
    			SettlementObjectEnum settleObj = sparepartEntryInfo.getSettleObject();
    			TotalAmountBySettObj totalAmountBySettObj = hashTotalAmountBySettle.get(settleObj);
    			if (totalAmountBySettObj == null) {
    				totalAmountBySettObj = new TotalAmountBySettObj();
    				totalAmountBySettObj.settleObj = settleObj;
    				hashTotalAmountBySettle.put(settleObj, totalAmountBySettObj);
    			}
    			totalAmountBySettObj.sparepartTaxAmount = totalAmountBySettObj.sparepartTaxAmount.add(sparepartEntryInfo.getTaxAmount());
    			totalAmountBySettObj.sparepartSettleAmout = totalAmountBySettObj.sparepartSettleAmout.add(sparepartEntryInfo.getARAmount());
    			totalAmountBySettObj.sparepartNotTaxAmout = totalAmountBySettObj.sparepartNotTaxAmout.add(sparepartEntryInfo.getNoTaxAmount());
    			totalAmountBySettObj.sparepartTax = totalAmountBySettObj.sparepartTax.add(sparepartEntryInfo.getTax());
    		}
    		 //维修项目
    		for (int i = 0;i < repairItemEntryCol.size(); i++) {
    			RepairWORWORepairItemEntryInfo repairItemEntryInfo = repairItemEntryCol.get(i);
    			SettlementObjectEnum settleObj = repairItemEntryInfo.getSettleObject();
    			TotalAmountBySettObj totalAmountBySettObj = hashTotalAmountBySettle.get(settleObj);
    			if (totalAmountBySettObj == null) {
    				totalAmountBySettObj = new TotalAmountBySettObj();
    				totalAmountBySettObj.settleObj = settleObj;
    				hashTotalAmountBySettle.put(settleObj, totalAmountBySettObj);
    			}
    			totalAmountBySettObj.repairItemTaxAmount = totalAmountBySettObj.repairItemTaxAmount.add(repairItemEntryInfo.getWorkTimeAmount());
    			totalAmountBySettObj.repairItemSettleAmout = totalAmountBySettObj.repairItemSettleAmout.add(repairItemEntryInfo.getARAmount());
    			CustomerInfo customerInfo = repairWOInfo.getCustomer();
    			BigDecimal taxRate = AutoPriceManager.getCustomerTaxRate(ctx, customerInfo);
    			BigDecimal notTaxAmount = 
    				repairItemEntryInfo.getARAmount().divide(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)),10,BigDecimal.ROUND_HALF_UP);
    			totalAmountBySettObj.repairItemNotTaxAmout = totalAmountBySettObj.repairItemNotTaxAmout.add(notTaxAmount);
    			totalAmountBySettObj.repairItemTax = totalAmountBySettObj.repairItemTax.add(taxRate.multiply(notTaxAmount));
    		}

    		RepairWORWOTotalAmountEntryCollection totalAmountEntryCol = new RepairWORWOTotalAmountEntryCollection(); //累计金额
    		BigDecimal allTotalAmount = BigDecimal.ZERO;
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.COMPANY,hashTotalAmountBySettle,totalAmountEntryCol));
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.CUST,hashTotalAmountBySettle,totalAmountEntryCol));
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.INNER,hashTotalAmountBySettle,totalAmountEntryCol));
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.INSURAN,hashTotalAmountBySettle,totalAmountEntryCol));
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.OTHER,hashTotalAmountBySettle,totalAmountEntryCol));
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.CLUB,hashTotalAmountBySettle,totalAmountEntryCol));
    		repairWOInfo.setRepairTotalAmount(allTotalAmount); //维修合计金额
    		
    		spentMsg = String.format("计算WIP号[%s]的合计金额(ms):%s", wip,String.valueOf(System.currentTimeMillis()-start));
        	returnInfo.addSpentMsg(spentMsg);
    	}
    	spentMsg = String.format("计算合计金额(ms):%s", String.valueOf(System.currentTimeMillis()-start));
    	returnInfo.addSpentMsg(spentMsg);
    	
    }
    
    private BigDecimal totalAmount(SettlementObjectEnum settleObj,HashMap<SettlementObjectEnum, TotalAmountBySettObj> hashTotalAmountBySettle,
    		RepairWORWOTotalAmountEntryCollection totalAmountEntryCol) {
    	int seq = totalAmountEntryCol.size();
    	TotalAmountBySettObj totalAmountBySettObj = hashTotalAmountBySettle.get(settleObj);
    	if (totalAmountBySettObj != null) {
			seq++;
			//配件 合计
			RepairWORWOTotalAmountEntryInfo totalAmountSparepart = new RepairWORWOTotalAmountEntryInfo();
			totalAmountSparepart.setSettleObject(settleObj);
			totalAmountSparepart.setOldAmount(totalAmountBySettObj.sparepartTaxAmount);
			totalAmountSparepart.setARAmount(totalAmountBySettObj.sparepartSettleAmout);
			totalAmountSparepart.setSettleAmount(totalAmountBySettObj.sparepartSettleAmout);
			totalAmountSparepart.setNoTaxAmount(totalAmountBySettObj.sparepartNotTaxAmout);
			totalAmountSparepart.setTaxAmount(totalAmountBySettObj.sparepartTax);
			//折扣率=(1 - 结算金额/原金额) * 100
			if (totalAmountSparepart.getOldAmount().compareTo(BigDecimal.ZERO) == 0)
				totalAmountSparepart.setDiscountRate(BigDecimal.ZERO);
			else
				totalAmountSparepart.setDiscountRate(BigDecimal.ONE.subtract(
					totalAmountSparepart.getSettleAmount().divide(totalAmountSparepart.getOldAmount(),10,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100.00)));
			totalAmountSparepart.setAmountClassify(AmountClassifyEnum.SPAREPART);
			totalAmountSparepart.setSeq(seq);
			totalAmountEntryCol.add(totalAmountSparepart);
			
			//维修项目 合计
			seq++;
			RepairWORWOTotalAmountEntryInfo totalAmountRepairItem = new RepairWORWOTotalAmountEntryInfo();
			totalAmountRepairItem.setSettleObject(settleObj);
			totalAmountRepairItem.setOldAmount(totalAmountBySettObj.repairItemTaxAmount);
			totalAmountRepairItem.setARAmount(totalAmountBySettObj.repairItemSettleAmout);
			totalAmountRepairItem.setSettleAmount(totalAmountBySettObj.repairItemSettleAmout);
			totalAmountRepairItem.setNoTaxAmount(totalAmountBySettObj.repairItemNotTaxAmout);
			totalAmountRepairItem.setTaxAmount(totalAmountBySettObj.repairItemTax);
			//折扣率=(1 - 结算金额/原金额) * 100
			if (totalAmountRepairItem.getOldAmount().compareTo(BigDecimal.ZERO) == 0)
				totalAmountRepairItem.setDiscountRate(BigDecimal.ZERO);
			else
				totalAmountRepairItem.setDiscountRate(BigDecimal.ONE.subtract(
					totalAmountRepairItem.getSettleAmount().divide(totalAmountRepairItem.getOldAmount(),10,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100.00)));
			totalAmountRepairItem.setAmountClassify(AmountClassifyEnum.REPAIRITEM);
			totalAmountRepairItem.setSeq(seq);
			totalAmountEntryCol.add(totalAmountRepairItem);
			
			//小计
			seq++;
			RepairWORWOTotalAmountEntryInfo totalAmountSub = new RepairWORWOTotalAmountEntryInfo();
			totalAmountSub.setSettleObject(null);
			totalAmountSub.setOldAmount(totalAmountBySettObj.repairItemTaxAmount.add(totalAmountBySettObj.sparepartTaxAmount));
			totalAmountSub.setARAmount(totalAmountBySettObj.repairItemSettleAmout.add(totalAmountBySettObj.sparepartSettleAmout));
			totalAmountSub.setSettleAmount(totalAmountBySettObj.repairItemSettleAmout.add(totalAmountBySettObj.sparepartSettleAmout));
			totalAmountSub.setNoTaxAmount(totalAmountBySettObj.repairItemNotTaxAmout.add(totalAmountBySettObj.sparepartNotTaxAmout));
			totalAmountSub.setTaxAmount(totalAmountBySettObj.repairItemTax.add(totalAmountBySettObj.sparepartTax));
			//折扣率=0
			totalAmountSub.setDiscountRate(null);
			totalAmountSub.setAmountClassify(null);
			totalAmountSub.setSeq(seq);
			totalAmountEntryCol.add(totalAmountSub);
			
			return totalAmountSub.getARAmount();
		}
		return BigDecimal.ZERO;
    	
    }
    
    private void batchSaveRepariWOInfo(Context ctx, HashMap<String,RepairWOInfo> hashRepairWOInfo) throws Exception {
    	Set<String> setKey = hashRepairWOInfo.keySet();
    	Iterator<String> itKey = setKey.iterator();
    	while(itKey.hasNext()) {
    		String key = itKey.next();
    		RepairWOInfo repairWOInfo = hashRepairWOInfo.get(key);
    		repairWO.save(repairWOInfo);
    	}
    }
    
    //按结算对象累计
    class TotalAmountBySettObj {
    	
    	SettlementObjectEnum settleObj = null;
    	
    	//配件-原金额
    	BigDecimal sparepartTaxAmount = BigDecimal.ZERO;
    	//配件-结算金额
    	BigDecimal sparepartSettleAmout = BigDecimal.ZERO;
    	//配件-不含税金额
    	BigDecimal sparepartNotTaxAmout = BigDecimal.ZERO;
    	//配件-税额
    	BigDecimal sparepartTax = BigDecimal.ZERO;
    	
    	
    	//维修项目-原金额
    	BigDecimal repairItemTaxAmount = BigDecimal.ZERO;
    	//维修项目-结算金额
    	BigDecimal repairItemSettleAmout = BigDecimal.ZERO;
    	//维修项目-不含税金额
    	BigDecimal repairItemNotTaxAmout = BigDecimal.ZERO;
    	//维修项目-税额
    	BigDecimal repairItemTax = BigDecimal.ZERO;
    	
    }
    
    private void addExceptionMsg(ServerReturnInfo returnInfo,String msg) {
    	returnInfo.setExption(true);
    	returnInfo.addExptionMsg(msg);
    }
    
    private boolean isIgnoreWipForStocktaking(String accountCode) {
    	if ("I0000013".equals(accountCode) ||
    		"I0000014".equals(accountCode) ||
    		"I0000015".equals(accountCode)) {
    		return true;
    	}
    	
    	return false;
    }
    
    @Override
    protected ServerReturnInfo _syncTradeInquire(Context ctx,
    		IObjectValue serviceOrgInfo, IObjectPK dmsTradeInqirePk)
    		throws BOSException, EASBizException {
    	ServerReturnInfo returnInfo = new ServerReturnInfo();
 
    	returnInfo.setSuccess(true);
    	returnInfo.setExption(false);
    	long start = System.currentTimeMillis();
    	returnInfo.appendSpentMsg("获取DMS交易查询单数据耗时(ms):");
    	
    	if (dmsTradeInquire == null) dmsTradeInquire = DMSInOutQueryFactory.getLocalInstance(ctx);
    	if (purInwarehsBill == null) purInwarehsBill = PurInWarehsBillFactory.getLocalInstance(ctx);
    	if (otherInwarehsBill == null) otherInwarehsBill = OtherInWarehsBillFactory.getLocalInstance(ctx);
    	if (saleIssueBill == null) saleIssueBill = SaleIssueBillFactory.getLocalInstance(ctx);
    	if (otherIssueBill == null) otherIssueBill = OtherIssueBillFactory.getLocalInstance(ctx);
    	
    	
    	
    	
    	try {
    		HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup = new HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>>();
    		//更新EAS相 关数据
    		updateTradInquireForEASData(ctx,dmsTradeInqirePk);
    		DMSInOutQueryInfo dmsTradeInquireInfo = dmsTradeInquire.getDMSInOutQueryInfo(dmsTradeInqirePk);
        	returnInfo.addSpentMsg(String.valueOf(System.currentTimeMillis()-start));
    		
    		//交易查询，按单据分组
    		parseTradeInquireGroup(ctx,hashTradeInquireGroup,dmsTradeInquireInfo,returnInfo);
    		
    		
    		//分组保存
    		if (!returnInfo.isExption)
    			batchSaveInOutBill(ctx,hashTradeInquireGroup);
    		
    		//关联维修工单
    		
    			
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new EASBizException(new NumericExceptionSubItem("",PublicUtils.getStackTrace(e)));
    		
    	}
	
    	return returnInfo;
    }
    
    private void updateTradInquireForEASData(Context ctx,
			IObjectPK dmsTradeInqirePk) throws Exception{
		String sql = "/*dialect*/ update a "  +
					"set a.CFEasSupplierID=(select fid from T_BD_Supplier where fnumber=a.CFSupplier)," +
					"a.CFEasWarehouseID=(select fid from T_DB_WAREHOUSE where FNumber=a.CFL)," +
					"a.CFEasMaterialId=(select fid from T_BD_Material where fnumber=a.CFMaterialNum)," +
					"a.CFEasBaseUnitID=(select FBaseUnit from T_BD_Material where fnumber=a.CFMaterialNum), " +
					"a.CFEasTaxPrice=(case when a.CFCost=0 then 0.0001 else a.CFCost end)," +
					"a.CFEasRepairWOID=(select fid from T_ATS_RepairWO aa where (aa.FOldID=(a.CFWip+'-'+convert(varchar(4),a.CFBizDate,120))" +
					"or aa.FOldID=(a.CFWip+'-'+convert(varchar(4),DATEADD(year,-1,a.CFBizDate),120))))" +
					" from CT_SYN_DMSInOutQueryEntry a" +
					" left join CT_SYN_DMSInOutQuery b on a.FParentID=b.FID" +
					" where (a.CFT='P' or a.CFT='S') and CFBizDate is not null" +
					" and b.FID='" + dmsTradeInqirePk.toString() + "'";
		DbUtil.execute(ctx, sql);
		//取维修工单配件含税单价
		sql = "/*dialect*/update a" +
		" set a.CFEasTaxPrice = b.FTaxPrice," +
		"a.CFEasCustomerID=(select FStdCustomerID from T_ATS_Customer where fid = c.FCustomerID)" +
		" from CT_SYN_DMSInOutQueryEntry a" +
		" left join T_ATS_RWOSparepartEntry b on b.FParentID=a.CFEasRepairWOID and b.CFWipLineNo=a.CFLineSeq" +
		" left join T_ATS_RepairWO c on c.FID=b.FParentID" +
		" where a.CFEasRepairWOID<> '' and a.FParentID='" + dmsTradeInqirePk.toString() + "'";
		DbUtil.execute(ctx, sql);
		
	}

	private void batchSaveInOutBill(Context ctx,HashMap<DMSWipBillTypeEnum, HashMap<String, DMSInOutQueryEntryCollection>> hashTradeInquireGroup) throws Exception {
    	
		HashMap<String, DMSInOutQueryEntryCollection> dmsTradeInquireEntryCol = hashTradeInquireGroup.get(DMSWipBillTypeEnum.PurInWarehs);
    	batchSavePurInware(ctx,DMSWipBillTypeEnum.PurInWarehs,dmsTradeInquireEntryCol);
    	dmsTradeInquireEntryCol = hashTradeInquireGroup.get(DMSWipBillTypeEnum.SaleReturn);
    	batchSaveSaleIssue(ctx,DMSWipBillTypeEnum.SaleReturn,dmsTradeInquireEntryCol);
    	dmsTradeInquireEntryCol = hashTradeInquireGroup.get(DMSWipBillTypeEnum.TransferInWarehs);
    	batchSaveOtherInWarehs(ctx,DMSWipBillTypeEnum.TransferInWarehs,dmsTradeInquireEntryCol);
    	dmsTradeInquireEntryCol = hashTradeInquireGroup.get(DMSWipBillTypeEnum.ProfitInWarehs);
    	batchSaveOtherInWarehs(ctx,DMSWipBillTypeEnum.ProfitInWarehs,dmsTradeInquireEntryCol);
    	
    	dmsTradeInquireEntryCol = hashTradeInquireGroup.get(DMSWipBillTypeEnum.PurReturn);
    	batchSavePurInware(ctx,DMSWipBillTypeEnum.PurReturn,dmsTradeInquireEntryCol);   	
    	dmsTradeInquireEntryCol = hashTradeInquireGroup.get(DMSWipBillTypeEnum.SaleIssue);
    	batchSaveSaleIssue(ctx,DMSWipBillTypeEnum.SaleIssue,dmsTradeInquireEntryCol);
    	dmsTradeInquireEntryCol = hashTradeInquireGroup.get(DMSWipBillTypeEnum.TransferOutWarehs);
    	batchSaveOtherIssue(ctx,DMSWipBillTypeEnum.TransferOutWarehs,dmsTradeInquireEntryCol);
    	dmsTradeInquireEntryCol = hashTradeInquireGroup.get(DMSWipBillTypeEnum.LossOutWarehs);
    	batchSaveOtherIssue(ctx,DMSWipBillTypeEnum.LossOutWarehs,dmsTradeInquireEntryCol); 	
	}
    private void batchSavePurInware(Context ctx,DMSWipBillTypeEnum billTypeEnum,HashMap<String, DMSInOutQueryEntryCollection> hashDMSTradeInquireEntryCol) throws Exception {
    	if (hashDMSTradeInquireEntryCol == null) return;
    	Set<String> setKey = hashDMSTradeInquireEntryCol.keySet();
    	Iterator<String> itKey = setKey.iterator();
    	PurInWarehsBillCollection purInWarehsBillCol = new PurInWarehsBillCollection();
    	String DEFAULT_PURCHASE_ID = "zfcAAAAAABjM567U"; //厦门中宝汽车有限公司
    	PurchaseOrgUnitInfo defaultPurchaseOrgUnit = new PurchaseOrgUnitInfo();
    	defaultPurchaseOrgUnit.put("id", DEFAULT_PURCHASE_ID);
    	
    	String DEFAULT_Storage_ID = "zfcAAAAAABjM567U"; //厦门中宝汽车有限公司
    	StorageOrgUnitInfo defaultStorageOrgUnit = new StorageOrgUnitInfo();
    	defaultStorageOrgUnit.put("id", DEFAULT_Storage_ID);
    	
    	String DEFAULT_Company_ID = "zfcAAAAAABjM567U"; //厦门中宝汽车有限公司
    	CompanyOrgUnitInfo defaultCompanyOrgUnit = new CompanyOrgUnitInfo();
    	defaultCompanyOrgUnit.put("id", DEFAULT_Company_ID);
    	
    	String DEFAULT_Currency_ID = "dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC"; //人民币
    	CurrencyInfo defaultCurrency = new CurrencyInfo();
    	defaultCurrency.put("id", DEFAULT_Currency_ID);
    	
    	String DEFAULT_PaymentType_ID = "2fa35444-5a23-43fb-99ee-6d4fa5f260da6BCA0AB5";  //赊购
    	PaymentTypeInfo defaultPaymentType = new PaymentTypeInfo();
    	defaultPaymentType.put("id", DEFAULT_PaymentType_ID);
    	
    	String DEFAULT_bizType_purInWarehs_ID = "d8e80652-0106-1000-e000-04c5c0a812202407435C"; //普通采购 110
    	BizTypeInfo defaultBizType_PurInwarehs = new BizTypeInfo();
    	defaultBizType_PurInwarehs.put("id", DEFAULT_bizType_purInWarehs_ID);
    	
    	String DEFAULT_Tran_PurInWarehs_ID = "DawAAAAPoACwCNyn"; //普通采购/委外入库 001
    	TransactionTypeInfo defaultTran_PurInWarehs = new TransactionTypeInfo();
    	defaultTran_PurInWarehs.put("id", DEFAULT_Tran_PurInWarehs_ID);
    	
    	String DEFAULT_bizType_purReturn_ID = "d8e80652-0107-1000-e000-04c5c0a812202407435C"; //普通采购退货  111
    	BizTypeInfo defaultBizType_PurReturn = new BizTypeInfo();
    	defaultBizType_PurReturn.put("id", DEFAULT_bizType_purReturn_ID);
    	
    	String DEFAULT_Tran_PurInReturn_ID = "DawAAAAPoAGwCNyn"; //普通采购/委外退货 002
    	TransactionTypeInfo defaultTran_PurReturn = new TransactionTypeInfo();
    	defaultTran_PurReturn.put("id", DEFAULT_Tran_PurInReturn_ID);
    	
    	String Default_BillType_ID = "50957179-0105-1000-e000-015fc0a812fd463ED552"; //单据类型-采购入库
    	BillTypeInfo default_BillType = new BillTypeInfo();
    	default_BillType.put("id", Default_BillType_ID);
    	
    	
    	
    	
    	while (itKey.hasNext()) {
    		String key = itKey.next();
    		DMSInOutQueryEntryCollection dmsTradeInquireEntryCol = hashDMSTradeInquireEntryCol.get(key);
    		PurInWarehsBillInfo purInWarehsBillInfo = new PurInWarehsBillInfo();
    		PurInWarehsEntryCollection purInWarehsEntryCol = new PurInWarehsEntryCollection();
    		Date bizDate = dmsTradeInquireEntryCol.get(0).getBizDate();
    		
    		//总数量
    		BigDecimal totalQty = PublicUtils.BIGDECIMAL0;
    		//总含税金额
    		BigDecimal totalTaxAmount = PublicUtils.BIGDECIMAL0;
    		//总不含税金额
    		BigDecimal totalNotTaxAmount = PublicUtils.BIGDECIMAL0;
    		
    		
    		
    		
    		for (int i = 0; i < dmsTradeInquireEntryCol.size(); i++) {
    			DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo = dmsTradeInquireEntryCol.get(i);
    			String warehouseNum = dmsTradeInquireEntryInfo.getL();
    			WarehouseInfo warehouseInfo = dmsTradeInquireEntryInfo.getEasWarehouse();
    			
    			PurInWarehsEntryInfo purInWarehsEntryInfo = new PurInWarehsEntryInfo();
    			//税率
    			BigDecimal taxRate = new BigDecimal(17.00);
    			//含税单价
    			BigDecimal taxPrice = dmsTradeInquireEntryInfo.getEasTaxPrice();
    			//不含税单价=含税单价/(1+税率/100)
    			BigDecimal notTaxPrice = taxPrice.divide(PublicUtils.BIGDECIMAL1.add(taxRate.divide(PublicUtils.BIGDECIMAL100,10,BigDecimal.ROUND_HALF_UP)),10,BigDecimal.ROUND_HALF_UP);
    			//数量
    			BigDecimal qty = dmsTradeInquireEntryInfo.getSupplyQty();
    			//含税金额
    			BigDecimal taxAmount = taxPrice.multiply(qty);
    			//不含税金额=不含税单价*数量
    			BigDecimal notTaxAmount = notTaxPrice.multiply(qty);
    			//税额=含税金额-不含税金额
    			BigDecimal tax = taxAmount.subtract(notTaxAmount);
    			
    			
    			totalQty = totalQty.add(qty);
    			totalTaxAmount = totalTaxAmount.add(taxAmount);
    			totalNotTaxAmount = totalNotTaxAmount.add(notTaxAmount);
    			
    			//已核销数量
    			purInWarehsEntryInfo.setWrittenOffQty(PublicUtils.BIGDECIMAL0);
    			//已核销金额
    			purInWarehsEntryInfo.setWrittenOffAmount(PublicUtils.BIGDECIMAL0);
    			//未核销数量
    			purInWarehsEntryInfo.setUnWriteOffQty(qty);
    			//未核销金额
    			purInWarehsEntryInfo.setUnWriteOffAmount(taxAmount);
    			//累计收款金额
    			purInWarehsEntryInfo.setReceiveAmount(PublicUtils.BIGDECIMAL0);
    			//税率
    			purInWarehsEntryInfo.setTaxRate(taxRate);
    			//税额
    			purInWarehsEntryInfo.setTax(tax);
    			//本位币税额
    			purInWarehsEntryInfo.setLocalTax(tax);
    			//本位币单价
    			purInWarehsEntryInfo.setLocalPrice(taxPrice);
    			//本位币金额
    			purInWarehsEntryInfo.setLocalAmount(taxAmount);
    			//已核销基本数量
    			purInWarehsEntryInfo.setWrittenOffBaseQty(PublicUtils.BIGDECIMAL0);
    			//未核销基本金额
    			purInWarehsEntryInfo.setUnWriteOffBaseQty(qty);
    			//可退货基本数量
    			purInWarehsEntryInfo.setUnReturnedBaseQty(qty);
    			//含税单价
    			purInWarehsEntryInfo.setTaxPrice(taxPrice);
    			//实际单价
    			purInWarehsEntryInfo.setActualPrice(notTaxPrice);
    			//采购组织
    			purInWarehsEntryInfo.setPurchaseOrgUnit(defaultPurchaseOrgUnit);
    			//是否申请组织等于收货组织
    			purInWarehsEntryInfo.setIsRequestToReceived(true);
    			//是否完全核销
    			purInWarehsEntryInfo.setIsFullWriteOff(false);
    			//收货库存组织
    			purInWarehsEntryInfo.setReceiveStorageOrgUnit(defaultStorageOrgUnit);
    			//采购成本
    			purInWarehsEntryInfo.setPurchaseCost(notTaxAmount);
    			//单位采购成本
    			purInWarehsEntryInfo.setUnitPurchaseCost(notTaxPrice);
    			//价税合计
    			purInWarehsEntryInfo.setTaxAmount(taxAmount);
    			//本位币价税合计
    			purInWarehsEntryInfo.setLocalTaxAmount(taxAmount);
    			//实际含税单价
    			purInWarehsEntryInfo.setActualTaxPrice(taxPrice);
    			//折扣率
    			purInWarehsEntryInfo.setDiscountRate(PublicUtils.BIGDECIMAL0);
    			//折扣额
    			purInWarehsEntryInfo.setDiscountAmount(PublicUtils.BIGDECIMAL0);
    			//单价
    			purInWarehsEntryInfo.setPrice(notTaxPrice);
    			//金额
    			purInWarehsEntryInfo.setAmount(notTaxAmount);
    			//业务日期 
    			purInWarehsEntryInfo.setBizDate(bizDate);
    			
    			
    			//InvBillBaseEntry库存基类
    			//库存组织
    			purInWarehsEntryInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    			//财务组织
    			purInWarehsEntryInfo.setCompanyOrgUnit(defaultCompanyOrgUnit);
    			//仓库
    			purInWarehsEntryInfo.setWarehouse(warehouseInfo);
    			//基本数量
    			purInWarehsEntryInfo.setBaseQty(qty);
    			//单位标准成本
    			purInWarehsEntryInfo.setUnitStandardCost(notTaxPrice);
    			//标准成本
    			purInWarehsEntryInfo.setStandardCost(notTaxAmount);
    			//单位实际成本
    			purInWarehsEntryInfo.setUnitActualCost(notTaxPrice);
    			//实际成本
    			purInWarehsEntryInfo.setActualCost(notTaxAmount);
    			//物料
    			String materialNum = dmsTradeInquireEntryInfo.getMaterialNum();
    			MaterialInfo materialInfo = dmsTradeInquireEntryInfo.getEasMaterial();
    			purInWarehsEntryInfo.setMaterial(materialInfo);
    			//数量
    			purInWarehsEntryInfo.setQty(qty);
    			//计量单位
    			purInWarehsEntryInfo.setUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			//基本计量单位
    			purInWarehsEntryInfo.setBaseUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			
    			purInWarehsEntryCol.add(purInWarehsEntryInfo);
    		}
       		String supplierNum = dmsTradeInquireEntryCol.get(0).getSupplier();
    		SupplierInfo supplierInfo = dmsTradeInquireEntryCol.get(0).getEasSupplier();
    		//供应商
    		purInWarehsBillInfo.setSupplier(supplierInfo);
    		//币别
    		purInWarehsBillInfo.setCurrency(defaultCurrency);
    		//汇率
    		purInWarehsBillInfo.setExchangeRate(PublicUtils.BIGDECIMAL1);
    		//付款方式
    		purInWarehsBillInfo.setPaymentType(defaultPaymentType);
    		//TODO 本位币总金额
    		//purInWarehsBillInfo.setTotalLocalAmount(null);
    		//采购类别  -外购
    		purInWarehsBillInfo.setPurchaseType(PurchaseTypeEnum.PURCHASE);
    		//是否含税
    		purInWarehsBillInfo.setIsInTax(true);
    		//是否价外税
    		purInWarehsBillInfo.setIsPriceInTax(true);
    		//库存组织
    		purInWarehsBillInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    		//总数量
    		purInWarehsBillInfo.setTotalQty(totalQty);
    		//总金额
    		purInWarehsBillInfo.setTotalAmount(totalTaxAmount);
    		// 总标准成本
    		purInWarehsBillInfo.setTotalStandardCost(totalNotTaxAmount);
    		// 总实际成本
    		purInWarehsBillInfo.setTotalActualCost(totalNotTaxAmount);
    		//是否类型
    		if (DMSWipBillTypeEnum.PurInWarehs.equals(billTypeEnum)) {
    			//事务类型
    			purInWarehsBillInfo.setTransactionType(defaultTran_PurInWarehs);
    			//业务类型
    			purInWarehsBillInfo.setBizType(defaultBizType_PurInwarehs);
    			
    		} else if (DMSWipBillTypeEnum.PurReturn.equals(billTypeEnum)) {
    			//事务类型
    			purInWarehsBillInfo.setTransactionType(defaultTran_PurReturn);
    			//业务类型
    			purInWarehsBillInfo.setBizType(defaultBizType_PurReturn);
    		}
    		purInWarehsBillInfo.setMonth(Integer.valueOf(sfMonth.format(bizDate)));
    		purInWarehsBillInfo.setDay(Integer.valueOf(sfDate.format(bizDate)));
    		
    		//SCMBillBase
    		//状态
    		purInWarehsBillInfo.setBaseStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
    		//单据类型
    		purInWarehsBillInfo.setBillType(default_BillType);
    		
    		purInWarehsBillInfo.setYear(Integer.valueOf(sfYear.format(bizDate)));
    		purInWarehsBillInfo.setPeriod(Integer.valueOf(sfPeriod.format(bizDate)));
    		purInWarehsBillInfo.setBizDate(bizDate);
    		
    		purInWarehsBillInfo.put("entry", purInWarehsEntryCol);
    		purInWarehsBillCol.add(purInWarehsBillInfo);
    	}
    	//保存
    	for (int i=0; i < purInWarehsBillCol.size(); i++) {
    		PurInWarehsBillInfo purInWarehsBillInfo  = purInWarehsBillCol.get(i);
    		purInwarehsBill.save(purInWarehsBillInfo);
    	}
    }
    
    
    private void batchSaveSaleIssue(Context ctx,DMSWipBillTypeEnum billTypeEnum,HashMap<String, DMSInOutQueryEntryCollection> hashDMSTradeInquireEntryCol) throws Exception {
    	if (hashDMSTradeInquireEntryCol == null) return;
    	Set<String> setKey = hashDMSTradeInquireEntryCol.keySet();
    	Iterator<String> itKey = setKey.iterator();
    	SaleIssueBillCollection saleIssueBillCol = new SaleIssueBillCollection();
    	String DEFAULT_SALEORG_ID = "zfcAAAAAABjM567U"; //厦门中宝汽车有限公司
    	SaleOrgUnitInfo defaultSaleOrgUnit = new SaleOrgUnitInfo();
    	defaultSaleOrgUnit.put("id", DEFAULT_SALEORG_ID);
    	
    	String DEFAULT_Storage_ID = "zfcAAAAAABjM567U"; //厦门中宝汽车有限公司
    	StorageOrgUnitInfo defaultStorageOrgUnit = new StorageOrgUnitInfo();
    	defaultStorageOrgUnit.put("id", DEFAULT_Storage_ID);
    	
    	String DEFAULT_Company_ID = "zfcAAAAAABjM567U"; //厦门中宝汽车有限公司
    	CompanyOrgUnitInfo defaultCompanyOrgUnit = new CompanyOrgUnitInfo();
    	defaultCompanyOrgUnit.put("id", DEFAULT_Company_ID);
    	
    	String DEFAULT_Currency_ID = "dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC"; //人民币
    	CurrencyInfo defaultCurrency = new CurrencyInfo();
    	defaultCurrency.put("id", DEFAULT_Currency_ID);
    	
    	String DEFAULT_PaymentType_ID = "91f078d7-fb90-4827-83e2-3538237b67a06BCA0AB5";  //赊销
    	PaymentTypeInfo defaultPaymentType = new PaymentTypeInfo();
    	defaultPaymentType.put("id", DEFAULT_PaymentType_ID);
    	
    	String DEFAULT_bizType_SaleIssue_ID = "d8e80652-010e-1000-e000-04c5c0a812202407435C"; //普通销售 210
    	BizTypeInfo defaultBizType_SaleIssue = new BizTypeInfo();
    	defaultBizType_SaleIssue.put("id", DEFAULT_bizType_SaleIssue_ID);
    	
    	String DEFAULT_Tran_SaleIssue_ID = "DawAAAAPoAywCNyn"; //普通销售出库 010
    	TransactionTypeInfo defaultTran_SaleIssue = new TransactionTypeInfo();
    	defaultTran_SaleIssue.put("id", DEFAULT_Tran_SaleIssue_ID);
    	
    	String DEFAULT_bizType_SaleReturn_ID = "d8e80652-0110-1000-e000-04c5c0a812202407435C"; //普通销售退货  211
    	BizTypeInfo defaultBizType_SaleReturn = new BizTypeInfo();
    	defaultBizType_SaleReturn.put("id", DEFAULT_bizType_SaleReturn_ID);
    	
    	String DEFAULT_Tran_SaleReturn_ID = "DawAAAAPoA2wCNyn"; //普通销售退货 011
    	TransactionTypeInfo defaultTran_SaleReturn = new TransactionTypeInfo();
    	defaultTran_SaleReturn.put("id", DEFAULT_Tran_SaleReturn_ID);
    	
    	String Default_BillType_ID = "50957179-0105-1000-e000-015bc0a812fd463ED552"; //单据类型-销售出库
    	BillTypeInfo default_BillType = new BillTypeInfo();
    	default_BillType.put("id", Default_BillType_ID);
    	
    	
    	
    	
    	while (itKey.hasNext()) {
    		String key = itKey.next();
    		DMSInOutQueryEntryCollection dmsTradeInquireEntryCol = hashDMSTradeInquireEntryCol.get(key);
    		SaleIssueBillInfo saleIssueBillInfo = new SaleIssueBillInfo();
    		SaleIssueEntryCollection saleIssueEntryCol = new SaleIssueEntryCollection();
    		Date bizDate = dmsTradeInquireEntryCol.get(0).getBizDate();
    		
    		//总数量
    		BigDecimal totalQty = PublicUtils.BIGDECIMAL0;
    		//总含税金额
    		BigDecimal totalTaxAmount = PublicUtils.BIGDECIMAL0;
    		//总不含税金额
    		BigDecimal totalNotTaxAmount = PublicUtils.BIGDECIMAL0;
    		

    		
    		for (int i = 0; i < dmsTradeInquireEntryCol.size(); i++) {
    			DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo = dmsTradeInquireEntryCol.get(i);
    		
    			WarehouseInfo warehouseInfo = dmsTradeInquireEntryInfo.getEasWarehouse();
    			com.kingdee.eas.basedata.master.cssp.CustomerInfo stdCustomerInfo = dmsTradeInquireEntryInfo.getEasCustomer();
    			
    			SaleIssueEntryInfo saleIssueEntryInfo = new SaleIssueEntryInfo();
    			//税率
    			BigDecimal taxRate = new BigDecimal(17.00);
    			//含税单价
    			BigDecimal taxPrice = dmsTradeInquireEntryInfo.getEasTaxPrice();
    			//不含税单价=含税单价/(1+税率/100)
    			BigDecimal notTaxPrice = taxPrice.divide(PublicUtils.BIGDECIMAL1.add(taxRate.divide(PublicUtils.BIGDECIMAL100,10,BigDecimal.ROUND_HALF_UP)),10,BigDecimal.ROUND_HALF_UP);
    			//数量
    			BigDecimal qty = dmsTradeInquireEntryInfo.getQty();
    			//含税金额
    			BigDecimal taxAmount = taxPrice.multiply(qty);
    			//不含税金额=不含税单价*数量
    			BigDecimal notTaxAmount = notTaxPrice.multiply(qty);
    			//税额=含税金额-不含税金额
    			BigDecimal tax = taxAmount.subtract(notTaxAmount);
    			
    			
    			totalQty = totalQty.add(qty);
    			totalTaxAmount = totalTaxAmount.add(taxAmount);
    			totalNotTaxAmount = totalNotTaxAmount.add(notTaxAmount);
    			
    			
    			//已核销数量
    			saleIssueEntryInfo.setWrittenOffQty(PublicUtils.BIGDECIMAL0);
    			//已核销金额
    			saleIssueEntryInfo.setWrittenOffAmount(PublicUtils.BIGDECIMAL0);
    			//未核销数量
    			saleIssueEntryInfo.setUnWriteOffQty(qty);
    			//未核销金额
    			saleIssueEntryInfo.setUnWriteOffAmount(taxAmount);
    			//税率
    			saleIssueEntryInfo.setTaxRate(taxRate);
    			//税额
    			saleIssueEntryInfo.setTax(tax);
    			//本位币税额
    			saleIssueEntryInfo.setLocalTax(tax);
    			//本位币单价
    			saleIssueEntryInfo.setLocalPrice(taxPrice);
    			//本位币金额
    			saleIssueEntryInfo.setLocalAmount(taxAmount);
    			//已核销基本数量
    			saleIssueEntryInfo.setWrittenOffBaseQty(PublicUtils.BIGDECIMAL0);
    			//未核销基本数量
    			saleIssueEntryInfo.setUnWriteOffBaseQty(qty);
   
    			//含税单价
    			saleIssueEntryInfo.setTaxPrice(taxPrice);
    			//实际单价
    			saleIssueEntryInfo.setActualPrice(notTaxPrice);
    			//销售组织
    			saleIssueEntryInfo.setSaleOrgUnit(defaultSaleOrgUnit);
    			//待发可出库数量
    			saleIssueEntryInfo.setUndeliverQty(qty);
    			//待发可发出库基本数量
    			saleIssueEntryInfo.setUndeliverBaseQty(qty);
    			//对方未入库数量
    			saleIssueEntryInfo.setUnInQty(qty);
    			//对方未入库基本数量
    			saleIssueEntryInfo.setUnInBaseQty(qty);
    			//累计入库数量
    			saleIssueEntryInfo.setTotalInWarehsQty(qty);
    			//订货客户
    			saleIssueEntryInfo.setOrderCustomer(stdCustomerInfo);
    			//收款客户
    			saleIssueEntryInfo.setPaymentCustomer(stdCustomerInfo);
    			//基础单位实际成本
    			saleIssueEntryInfo.setBaseUnitActualCost(notTaxPrice);
    			//单价
    			saleIssueEntryInfo.setSalePrice(notTaxPrice);
    			//含税单价
    			saleIssueEntryInfo.setPrice(taxPrice);
    			//价税合计
    			saleIssueEntryInfo.setAmount(taxAmount);
    			//不含税金额
    			saleIssueEntryInfo.setNonTaxAmount(notTaxAmount);
    			//本位币不含税金额
    			saleIssueEntryInfo.setLocalNonTaxAmount(notTaxAmount);
    			//本位币含税金额
    			saleIssueEntryInfo.setLocalAmount(taxAmount);
    			//日期
    			saleIssueEntryInfo.setBizDate(bizDate);
    			//折扣率
    			saleIssueEntryInfo.setDiscount(PublicUtils.BIGDECIMAL0);
    			//折扣额
    			saleIssueEntryInfo.setDiscountAmount(PublicUtils.BIGDECIMAL0);
    		 
    			
    			
    			//InvBillBaseEntry库存基类
    			//库存组织
    			saleIssueEntryInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    			//财务组织
    			saleIssueEntryInfo.setCompanyOrgUnit(defaultCompanyOrgUnit);
    			//仓库
    			saleIssueEntryInfo.setWarehouse(warehouseInfo);
    			//基本数量
    			saleIssueEntryInfo.setBaseQty(qty);
    			//数量
    			saleIssueEntryInfo.setQty(qty);
    		
    			//单位标准成本
    			saleIssueEntryInfo.setUnitStandardCost(notTaxPrice);
    			//标准成本
    			saleIssueEntryInfo.setStandardCost(notTaxAmount);
    			//单位实际成本
    			saleIssueEntryInfo.setUnitActualCost(notTaxPrice);
    			//实际成本
    			saleIssueEntryInfo.setActualCost(notTaxAmount);
    			//物料
    			MaterialInfo materialInfo = dmsTradeInquireEntryInfo.getEasMaterial();
    			saleIssueEntryInfo.setMaterial(materialInfo);
    			//计量单位
    			saleIssueEntryInfo.setUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			//基本计量单位
    			saleIssueEntryInfo.setBaseUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			
    			saleIssueEntryCol.add(saleIssueEntryInfo);
    		}
       		
    		saleIssueBillInfo.setCustomer(dmsTradeInquireEntryCol.get(0).getEasCustomer());
    		//币别
    		saleIssueBillInfo.setCurrency(defaultCurrency);
    		//汇率
    		saleIssueBillInfo.setExchangeRate(PublicUtils.BIGDECIMAL1);
    		//付款方式
    		saleIssueBillInfo.setPaymentType(defaultPaymentType);
    		//实际业务日期
    		saleIssueBillInfo.setActBizDate(bizDate);
    		//是否含税
    		saleIssueBillInfo.setIsInTax(true);
    		//本位币总价税合计
    		saleIssueBillInfo.setTotalLocalAmount(totalTaxAmount);
    		//库存组织
    		saleIssueBillInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    		//总数量
    		saleIssueBillInfo.setTotalQty(totalQty);
    		//总金额
    		saleIssueBillInfo.setTotalAmount(totalTaxAmount);
    		// 总标准成本
    		saleIssueBillInfo.setTotalStandardCost(totalNotTaxAmount);
    		// 总实际成本
    		saleIssueBillInfo.setTotalActualCost(totalNotTaxAmount);
    		//是否类型
    		if (DMSWipBillTypeEnum.SaleIssue.equals(billTypeEnum)) {
    			//事务类型
    			saleIssueBillInfo.setTransactionType(defaultTran_SaleIssue);
    			//业务类型
    			saleIssueBillInfo.setBizType(defaultBizType_SaleIssue);
    			
    		} else if (DMSWipBillTypeEnum.SaleReturn.equals(billTypeEnum)) {
    			//事务类型
    			saleIssueBillInfo.setTransactionType(defaultTran_SaleReturn);
    			//业务类型
    			saleIssueBillInfo.setBizType(defaultBizType_SaleReturn);
    		}
    		saleIssueBillInfo.setMonth(Integer.valueOf(sfMonth.format(bizDate)));
    		saleIssueBillInfo.setDay(Integer.valueOf(sfDate.format(bizDate)));
    		
    		//SCMBillBase
    		//状态
    		saleIssueBillInfo.setBaseStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
    		//单据类型
    		saleIssueBillInfo.setBillType(default_BillType);
    		
    		saleIssueBillInfo.setYear(Integer.valueOf(sfYear.format(bizDate)));
    		saleIssueBillInfo.setPeriod(Integer.valueOf(sfPeriod.format(bizDate)));
    		saleIssueBillInfo.setBizDate(bizDate);
    		
    		saleIssueBillInfo.put("entry", saleIssueEntryCol);
    		saleIssueBillCol.add(saleIssueBillInfo);
    	}
    	//保存
    	for (int i=0; i < saleIssueBillCol.size(); i++) {
    		SaleIssueBillInfo saleIssueBillInfo  = saleIssueBillCol.get(i);
    		saleIssueBill.save(saleIssueBillInfo);
    	}
    }
    
    private void batchSaveOtherIssue(Context ctx,DMSWipBillTypeEnum billTypeEnum,HashMap<String, DMSInOutQueryEntryCollection> hashDMSTradeInquireEntryCol) throws Exception {
    	if (hashDMSTradeInquireEntryCol == null) return;
    	Set<String> setKey = hashDMSTradeInquireEntryCol.keySet();
    	Iterator<String> itKey = setKey.iterator();
    	OtherIssueBillCollection otherIssueBillCol = new OtherIssueBillCollection();
    	
    	String DEFAULT_Storage_ID = "zfcAAAAAABjM567U"; //厦门中宝汽车有限公司
    	StorageOrgUnitInfo defaultStorageOrgUnit = new StorageOrgUnitInfo();
    	defaultStorageOrgUnit.put("id", DEFAULT_Storage_ID);
    	
    	String DEFAULT_Company_ID = "zfcAAAAAABjM567U"; //厦门中宝汽车有限公司
    	CompanyOrgUnitInfo defaultCompanyOrgUnit = new CompanyOrgUnitInfo();
    	defaultCompanyOrgUnit.put("id", DEFAULT_Company_ID);
    	
    	String DEFAULT_Currency_ID = "dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC"; //人民币
    	CurrencyInfo defaultCurrency = new CurrencyInfo();
    	defaultCurrency.put("id", DEFAULT_Currency_ID);
    	
    	String DEFAULT_StoreType_ID = "181875d5-0105-1000-e000-0111c0a812fd97D461A6"; //普通
    	StoreTypeInfo defaultStoreType = new StoreTypeInfo();
    	defaultStoreType.put("id", DEFAULT_StoreType_ID);
    	
    	String DEFAULT_StoreState_ID = "181875d5-0105-1000-e000-012ec0a812fd62A73FA5"; //可用
    	StoreStateInfo defaultStoreState = new StoreStateInfo();
    	defaultStoreState.put("id", DEFAULT_StoreState_ID);
    	
    	String DEFAULT_bizType_OtherIssue_ID = "Nz878AEgEADgAABMwKg/GiQHQ1w="; //普通出库 510
    	BizTypeInfo defaultBizType_OtherIssue = new BizTypeInfo();
    	defaultBizType_OtherIssue.put("id", DEFAULT_bizType_OtherIssue_ID);
    	
    	String DEFAULT_Tran_TransferOutWarehs_ID = "zfcAAAADYjawCNyn"; //其他出库(调拨出库) 030-1
    	TransactionTypeInfo defaultTran_TransferOutWarehs = new TransactionTypeInfo();
    	defaultTran_TransferOutWarehs.put("id", DEFAULT_Tran_TransferOutWarehs_ID);
    	
    	String DEFAULT_Tran_LossOutWarehs_ID = "zfcAAAADYjewCNyn"; //其他出库(盘亏出库) 030-1
    	TransactionTypeInfo defaultTran_LossOutWarehs = new TransactionTypeInfo();
    	defaultTran_LossOutWarehs.put("id", DEFAULT_Tran_LossOutWarehs_ID);
    	
    	String Default_BillType_ID = "50957179-0105-1000-e000-0177c0a812fd463ED552"; //单据类型-其他出库单
    	BillTypeInfo default_BillType = new BillTypeInfo();
    	default_BillType.put("id", Default_BillType_ID);
    	
    	while (itKey.hasNext()) {
    		String key = itKey.next();
    		DMSInOutQueryEntryCollection dmsTradeInquireEntryCol = hashDMSTradeInquireEntryCol.get(key);
    		OtherIssueBillInfo otherIssueBillInfo = new OtherIssueBillInfo();
    		OtherIssueBillEntryCollection otherIssueEntryCol = new OtherIssueBillEntryCollection();
    		Date bizDate = dmsTradeInquireEntryCol.get(0).getBizDate();
    		
    		//总数量
    		BigDecimal totalQty = PublicUtils.BIGDECIMAL0;
    		//总含税金额
    		BigDecimal totalTaxAmount = PublicUtils.BIGDECIMAL0;
    		//总不含税金额
    		BigDecimal totalNotTaxAmount = PublicUtils.BIGDECIMAL0;
    		

    		
    		for (int i = 0; i < dmsTradeInquireEntryCol.size(); i++) {
    			DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo = dmsTradeInquireEntryCol.get(i);
    		
    			WarehouseInfo warehouseInfo = dmsTradeInquireEntryInfo.getEasWarehouse();
    			com.kingdee.eas.basedata.master.cssp.CustomerInfo stdCustomerInfo = dmsTradeInquireEntryInfo.getEasCustomer();
    			
    			OtherIssueBillEntryInfo otherIssueEntryInfo = new OtherIssueBillEntryInfo();
    			//税率
    			BigDecimal taxRate = new BigDecimal(17.00);
    			//含税单价
    			BigDecimal taxPrice = dmsTradeInquireEntryInfo.getEasTaxPrice();
    			//不含税单价=含税单价/(1+税率/100)
    			BigDecimal notTaxPrice = taxPrice.divide(PublicUtils.BIGDECIMAL1.add(taxRate.divide(PublicUtils.BIGDECIMAL100,10,BigDecimal.ROUND_HALF_UP)),10,BigDecimal.ROUND_HALF_UP);
    			//数量
    			BigDecimal qty = dmsTradeInquireEntryInfo.getQty();
    			//含税金额
    			BigDecimal taxAmount = taxPrice.multiply(qty);
    			//不含税金额=不含税单价*数量
    			BigDecimal notTaxAmount = notTaxPrice.multiply(qty);
    			//税额=含税金额-不含税金额
    			BigDecimal tax = taxAmount.subtract(notTaxAmount);
    			
    			
    			totalQty = totalQty.add(qty);
    			totalTaxAmount = totalTaxAmount.add(taxAmount);
    			totalNotTaxAmount = totalNotTaxAmount.add(notTaxAmount);
    			
    			
    			//库存类型
    			otherIssueEntryInfo.setStoreType(defaultStoreType);
    			//库存状态
    			otherIssueEntryInfo.setStoreStatus(defaultStoreState);
    			//基本单价实际成本
    			otherIssueEntryInfo.setBaseUnitActualcost(notTaxPrice);
    			//单价
    			otherIssueEntryInfo.setPrice(taxPrice);
    			//金额
    			otherIssueEntryInfo.setAmount(taxAmount);
    			//业务日期
    			otherIssueEntryInfo.setBizDate(bizDate);
    		 
    			
    			
    			//InvBillBaseEntry库存基类
    			//库存组织
    			otherIssueEntryInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    			//财务组织
    			otherIssueEntryInfo.setCompanyOrgUnit(defaultCompanyOrgUnit);
    			//仓库
    			otherIssueEntryInfo.setWarehouse(warehouseInfo);
    			//基本数量
    			otherIssueEntryInfo.setBaseQty(qty);
    			//数量
    			otherIssueEntryInfo.setQty(qty);
    			//单位标准成本
    			otherIssueEntryInfo.setUnitStandardCost(notTaxPrice);
    			//标准成本
    			otherIssueEntryInfo.setStandardCost(notTaxAmount);
    			//单位实际成本
    			otherIssueEntryInfo.setUnitActualCost(notTaxPrice);
    			//实际成本
    			otherIssueEntryInfo.setActualCost(notTaxAmount);
    			//物料
    			MaterialInfo materialInfo = dmsTradeInquireEntryInfo.getEasMaterial();
    			otherIssueEntryInfo.setMaterial(materialInfo);
    			//计量单位
    			otherIssueEntryInfo.setUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			//基本计量单位
    			otherIssueEntryInfo.setBaseUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			
    			otherIssueEntryCol.add(otherIssueEntryInfo);
    		}
       		
    		
    		//InvBillBase
    		String number =CodingRuleUtils.getNumber(ctx, otherIssueBillInfo, defaultStorageOrgUnit.getString("id"));
    		otherIssueBillInfo.setNumber(number);
    		
    		//库存组织
    		otherIssueBillInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    		//总数量
    		otherIssueBillInfo.setTotalQty(totalQty);
    		//总金额
    		otherIssueBillInfo.setTotalAmount(totalTaxAmount);
    		// 总标准成本
    		otherIssueBillInfo.setTotalStandardCost(totalNotTaxAmount);
    		// 总实际成本
    		otherIssueBillInfo.setTotalActualCost(totalNotTaxAmount);
    		//业务类型
    		otherIssueBillInfo.setBizType(defaultBizType_OtherIssue);
			
    		//是否类型
    		if (DMSWipBillTypeEnum.TransferOutWarehs.equals(billTypeEnum)) {
    			//事务类型
    			otherIssueBillInfo.setTransactionType(defaultTran_TransferOutWarehs);	
    		} else if (DMSWipBillTypeEnum.LossOutWarehs.equals(billTypeEnum)) {
    			//事务类型
    			otherIssueBillInfo.setTransactionType(defaultTran_LossOutWarehs);
    		}
    		otherIssueBillInfo.setMonth(Integer.valueOf(sfMonth.format(bizDate)));
    		otherIssueBillInfo.setDay(Integer.valueOf(sfDate.format(bizDate)));
    		
    		//SCMBillBase
    		//状态
    		otherIssueBillInfo.setBaseStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
    		//单据类型
    		otherIssueBillInfo.setBillType(default_BillType);
    		
    		otherIssueBillInfo.setYear(Integer.valueOf(sfYear.format(bizDate)));
    		otherIssueBillInfo.setPeriod(Integer.valueOf(sfPeriod.format(bizDate)));
    		otherIssueBillInfo.setBizDate(bizDate);
    		
    		otherIssueBillInfo.put("entry", otherIssueEntryCol);
    		otherIssueBillCol.add(otherIssueBillInfo);
    		
    		
    	}
    	//保存
    	for (int i=0; i < otherIssueBillCol.size(); i++) {
    		OtherIssueBillInfo otherIssueBillInfo  = otherIssueBillCol.get(i);
    		otherIssueBill.save(otherIssueBillInfo);
    	}
    	
    }
    
    private void batchSaveOtherInWarehs(Context ctx,DMSWipBillTypeEnum billTypeEnum,HashMap<String, DMSInOutQueryEntryCollection> hashDMSTradeInquireEntryCol) throws Exception {
    	if (hashDMSTradeInquireEntryCol == null) return;
    	Set<String> setKey = hashDMSTradeInquireEntryCol.keySet();
    	Iterator<String> itKey = setKey.iterator();
    	OtherInWarehsBillCollection otherInWarehsBillCol = new OtherInWarehsBillCollection();
    	
    	String DEFAULT_Storage_ID = "zfcAAAAAABjM567U"; //厦门中宝汽车有限公司
    	StorageOrgUnitInfo defaultStorageOrgUnit = new StorageOrgUnitInfo();
    	defaultStorageOrgUnit.put("id", DEFAULT_Storage_ID);
    	
    	String DEFAULT_Company_ID = "zfcAAAAAABjM567U"; //厦门中宝汽车有限公司
    	CompanyOrgUnitInfo defaultCompanyOrgUnit = new CompanyOrgUnitInfo();
    	defaultCompanyOrgUnit.put("id", DEFAULT_Company_ID);
    	
    	String DEFAULT_Currency_ID = "dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC"; //人民币
    	CurrencyInfo defaultCurrency = new CurrencyInfo();
    	defaultCurrency.put("id", DEFAULT_Currency_ID);
    	
    	String DEFAULT_StoreType_ID = "181875d5-0105-1000-e000-0111c0a812fd97D461A6"; //普通
    	StoreTypeInfo defaultStoreType = new StoreTypeInfo();
    	defaultStoreType.put("id", DEFAULT_StoreType_ID);
    	
    	String DEFAULT_StoreState_ID = "181875d5-0105-1000-e000-012ec0a812fd62A73FA5"; //可用
    	StoreStateInfo defaultStoreState = new StoreStateInfo();
    	defaultStoreState.put("id", DEFAULT_StoreState_ID);
    	
    	String DEFAULT_bizType_OtherInWarehs_ID = "N5d2igEgEADgAABywKg/GiQHQ1w="; //普通入库 500
    	BizTypeInfo defaultBizType_OtherInWarehs = new BizTypeInfo();
    	defaultBizType_OtherInWarehs.put("id", DEFAULT_bizType_OtherInWarehs_ID);
    	
    	String DEFAULT_Tran_TransferInWarehs_ID = "zfcAAAADYjSwCNyn"; //其他入库(调拨入库) 034-1
    	TransactionTypeInfo defaultTran_TransferInWarehs = new TransactionTypeInfo();
    	defaultTran_TransferInWarehs.put("id", DEFAULT_Tran_TransferInWarehs_ID);
    	
    	String DEFAULT_Tran_ProfitInWarehs_ID = "zfcAAAADYjWwCNyn"; //其他入库(盘盈入库) 034-2
    	TransactionTypeInfo defaultTran_ProfitInWarehs = new TransactionTypeInfo();
    	defaultTran_ProfitInWarehs.put("id", DEFAULT_Tran_ProfitInWarehs_ID);
    	
    	String Default_BillType_ID = "50957179-0105-1000-e000-017bc0a812fd463ED552"; //单据类型-其他入库单
    	BillTypeInfo default_BillType = new BillTypeInfo();
    	default_BillType.put("id", Default_BillType_ID);
    	
    	while (itKey.hasNext()) {
    		String key = itKey.next();
    		DMSInOutQueryEntryCollection dmsTradeInquireEntryCol = hashDMSTradeInquireEntryCol.get(key);
    		OtherInWarehsBillInfo otherInWarehsBillInfo = new OtherInWarehsBillInfo();
    		OtherInWarehsBillEntryCollection otherInWarehsEntryCol = new OtherInWarehsBillEntryCollection();
    		Date bizDate = dmsTradeInquireEntryCol.get(0).getBizDate();
    		
    		//总数量
    		BigDecimal totalQty = PublicUtils.BIGDECIMAL0;
    		//总含税金额
    		BigDecimal totalTaxAmount = PublicUtils.BIGDECIMAL0;
    		//总不含税金额
    		BigDecimal totalNotTaxAmount = PublicUtils.BIGDECIMAL0;
    		

    		
    		for (int i = 0; i < dmsTradeInquireEntryCol.size(); i++) {
    			DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo = dmsTradeInquireEntryCol.get(i);
    		
    			WarehouseInfo warehouseInfo = dmsTradeInquireEntryInfo.getEasWarehouse();
    			com.kingdee.eas.basedata.master.cssp.CustomerInfo stdCustomerInfo = dmsTradeInquireEntryInfo.getEasCustomer();
    			
    			OtherInWarehsBillEntryInfo otherInWarehsEntryInfo = new OtherInWarehsBillEntryInfo();
    			//税率
    			BigDecimal taxRate = new BigDecimal(17.00);
    			//含税单价
    			BigDecimal taxPrice = dmsTradeInquireEntryInfo.getEasTaxPrice();
    			//不含税单价=含税单价/(1+税率/100)
    			BigDecimal notTaxPrice = taxPrice.divide(PublicUtils.BIGDECIMAL1.add(taxRate.divide(PublicUtils.BIGDECIMAL100,10,BigDecimal.ROUND_HALF_UP)),10,BigDecimal.ROUND_HALF_UP);
    			//数量
    			BigDecimal qty = dmsTradeInquireEntryInfo.getQty();
    			//含税金额
    			BigDecimal taxAmount = taxPrice.multiply(qty);
    			//不含税金额=不含税单价*数量
    			BigDecimal notTaxAmount = notTaxPrice.multiply(qty);
    			//税额=含税金额-不含税金额
    			BigDecimal tax = taxAmount.subtract(notTaxAmount);
    			
    			
    			totalQty = totalQty.add(qty);
    			totalTaxAmount = totalTaxAmount.add(taxAmount);
    			totalNotTaxAmount = totalNotTaxAmount.add(notTaxAmount);
    			
    			
    			//库存类型
    			otherInWarehsEntryInfo.setStoreType(defaultStoreType);
    			//库存状态
    			otherInWarehsEntryInfo.setStoreStatus(defaultStoreState);
    			//单价
    			otherInWarehsEntryInfo.setPrice(taxPrice);
    			//金额
    			otherInWarehsEntryInfo.setAmount(taxAmount);
    			//业务日期
    			otherInWarehsEntryInfo.setBizDate(bizDate);
    		 
    			
    			
    			//InvBillBaseEntry库存基类
    			//库存组织
    			otherInWarehsEntryInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    			//财务组织
    			otherInWarehsEntryInfo.setCompanyOrgUnit(defaultCompanyOrgUnit);
    			//仓库
    			otherInWarehsEntryInfo.setWarehouse(warehouseInfo);
    			//基本数量
    			otherInWarehsEntryInfo.setBaseQty(qty);
    			//数量
    			otherInWarehsEntryInfo.setQty(qty);
    			//单位标准成本
    			otherInWarehsEntryInfo.setUnitStandardCost(notTaxPrice);
    			//标准成本
    			otherInWarehsEntryInfo.setStandardCost(notTaxAmount);
    			//单位实际成本
    			otherInWarehsEntryInfo.setUnitActualCost(notTaxPrice);
    			//实际成本
    			otherInWarehsEntryInfo.setActualCost(notTaxAmount);
    			//物料
    			MaterialInfo materialInfo = dmsTradeInquireEntryInfo.getEasMaterial();
    			otherInWarehsEntryInfo.setMaterial(materialInfo);
    			//计量单位
    			otherInWarehsEntryInfo.setUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			//基本计量单位
    			otherInWarehsEntryInfo.setBaseUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			
    			//FAssistQty,FReverseQty, FIsPresent,
    			otherInWarehsEntryInfo.setAssistQty(PublicUtils.BIGDECIMAL0);
    			otherInWarehsEntryInfo.setReverseQty(PublicUtils.BIGDECIMAL0);
    			otherInWarehsEntryInfo.setIsPresent(false);
    			
    			
    			
    			otherInWarehsEntryCol.add(otherInWarehsEntryInfo);
    		}
    		
       		
    		
    		//InvBillBase
    		String number =CodingRuleUtils.getNumber(ctx, otherInWarehsBillInfo, defaultStorageOrgUnit.getString("id"));
    		otherInWarehsBillInfo.setNumber(number);
    		//库存组织
    		otherInWarehsBillInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    		//总数量
    		otherInWarehsBillInfo.setTotalQty(totalQty);
    		//总金额
    		otherInWarehsBillInfo.setTotalAmount(totalTaxAmount);
    		// 总标准成本
    		otherInWarehsBillInfo.setTotalStandardCost(totalNotTaxAmount);
    		// 总实际成本
    		otherInWarehsBillInfo.setTotalActualCost(totalNotTaxAmount);
    		//业务类型
    		otherInWarehsBillInfo.setBizType(defaultBizType_OtherInWarehs);
			
    		//是否类型
    		if (DMSWipBillTypeEnum.TransferInWarehs.equals(billTypeEnum)) {
    			//事务类型
    			otherInWarehsBillInfo.setTransactionType(defaultTran_TransferInWarehs);	
    		} else if (DMSWipBillTypeEnum.ProfitInWarehs.equals(billTypeEnum)) {
    			//事务类型
    			otherInWarehsBillInfo.setTransactionType(defaultTran_ProfitInWarehs);
    		}
    		otherInWarehsBillInfo.setMonth(Integer.valueOf(sfMonth.format(bizDate)));
    		otherInWarehsBillInfo.setDay(Integer.valueOf(sfDate.format(bizDate)));
    		
    		//SCMBillBase
    		//状态
    		otherInWarehsBillInfo.setBaseStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
    		//单据类型
    		otherInWarehsBillInfo.setBillType(default_BillType);
    		
    		otherInWarehsBillInfo.setYear(Integer.valueOf(sfYear.format(bizDate)));
    		otherInWarehsBillInfo.setPeriod(Integer.valueOf(sfPeriod.format(bizDate)));
    		otherInWarehsBillInfo.setBizDate(bizDate);
    		
    		otherInWarehsBillInfo.put("entry", otherInWarehsEntryCol);
    		otherInWarehsBillCol.add(otherInWarehsBillInfo);
    		
    		
    		
    	}
    	
    	//保存
    	for (int i=0; i < otherInWarehsBillCol.size(); i++) {
    		OtherInWarehsBillInfo otherInWarehsBillInfo  = otherInWarehsBillCol.get(i);
    		otherInwarehsBill.save(otherInWarehsBillInfo);
    	}
    }

	private void parseTradeInquireGroup(Context ctx,HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup,
    		DMSInOutQueryInfo dmsTradeInquireInfo,ServerReturnInfo returnInfo) throws Exception {
    	ServiceOrgUnitInfo serviceOrgInfo = dmsTradeInquireInfo.getServiceOrgUnit();
    	HashMap<String,String> keyWipForStocktaking = getAllWipStocktaking(ctx, serviceOrgInfo);
    	String expMsg = "";
    	String sql = "";
    	String spentMsg="";
		
		DMSInOutQueryEntryCollection dmsTradeInquireEntryCol = dmsTradeInquireInfo.getEntrys();
		for (int i = 0; i < dmsTradeInquireEntryCol.size(); i++) {
			DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo = dmsTradeInquireEntryCol.get(i);
			//通用检查 数据合法性
			int seq = dmsTradeInquireEntryInfo.getSeq();
			if ("A".equals(dmsTradeInquireEntryInfo.getT())) continue;
			
			//出库
			Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
			if (bizDate == null) {
				expMsg = String.format("DMS交易查询第%d行,出库不合法,不能为空.",seq);
    			addExceptionMsg(returnInfo, expMsg);
    			return;
			}
			
			//零件编号
			
			String materialNum = dmsTradeInquireEntryInfo.getMaterialNum();
			MaterialInfo materialInfo = dmsTradeInquireEntryInfo.getEasMaterial();
    		if (materialInfo == null) {
    			expMsg = String.format("DMS交易查询,第%d行,零件编号[%s]不存在于EAS系统",seq,materialNum);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
			
			//[T]=P时，检查[供应数量]不能为空、不能等于0
    		if ("P".equals(dmsTradeInquireEntryInfo.getT())) {
    			BigDecimal supplyQty =dmsTradeInquireEntryInfo.getSupplyQty();
    			if (supplyQty == null || supplyQty.compareTo(PublicUtils.BIGDECIMAL0) == 0) {
    				expMsg = String.format("DMS交易查询第%d行供应数量不合法,不能为空或等于0.",seq);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
    			}
    		}
			
			//[T]=S时，检查[数量]不能为空，不能等于0
    		if ("S".equals(dmsTradeInquireEntryInfo.getT())) {
    			BigDecimal qty =dmsTradeInquireEntryInfo.getQty();
    			if (qty == null || qty.compareTo(PublicUtils.BIGDECIMAL0) == 0) {
    				expMsg = String.format("DMS交易查询第%d行数量不合法,不能为空或等于0.",seq);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
    			}
    		}
			
			//供应商 [T] = P
    		if ("P".equals(dmsTradeInquireEntryInfo.getT())) {
    			String supplierNum = dmsTradeInquireEntryInfo.getSupplier();
				SupplierInfo supplierInfo = dmsTradeInquireEntryInfo.getEasSupplier();
	    		if (supplierInfo == null) {
	    			expMsg = String.format("DMS交易查询,第%d行,供应商[%s]不存在于EAS系统",seq,supplierNum);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
	    		}
    		}
			
    		//仓库
    		String warehouseNum = dmsTradeInquireEntryInfo.getL();
    		WarehouseInfo warehouseInfo = dmsTradeInquireEntryInfo.getEasWarehouse();
    		if (warehouseInfo == null) {
    			expMsg = String.format("DMS交易查询,第%d行,仓库[%s]不存在于EAS系统",seq,warehouseNum);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		
			//WIP号，除采购外，都需要检查是否存在于维修工单
    		if ("S".equals(dmsTradeInquireEntryInfo.getT())) {
    			RepairWOInfo repairWoInfo =  dmsTradeInquireEntryInfo.getEasRepairWO();
    			String wip = dmsTradeInquireEntryInfo.getWip();
    			if (repairWoInfo == null) {
        			expMsg = String.format("DMS交易查询,第%d行,WIP[%s]维修工单不存在于EAS系统",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
        		}
    			int wipLineNo = dmsTradeInquireEntryInfo.getLineSeq();
    			if (dmsTradeInquireEntryInfo.getEasCustomer() == null || dmsTradeInquireEntryInfo.getEasTaxPrice() == null) {
    				expMsg = String.format("DMS交易查询,第%d行,WIP[%s]维修工单,WIP行号[%d]不存在于EAS系统",seq,wip,wipLineNo);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
    			}
    		}


			DMSWipBillTypeEnum billTypeEnum = getWipBillType(dmsTradeInquireEntryInfo, keyWipForStocktaking);
			
			if (DMSWipBillTypeEnum.PurInWarehs.equals(billTypeEnum) || DMSWipBillTypeEnum.PurReturn.equals(billTypeEnum)) { //采购
				groupForPurInwarehs(ctx,billTypeEnum,hashTradeInquireGroup,dmsTradeInquireEntryInfo,returnInfo);
			} else if (DMSWipBillTypeEnum.ProfitInWarehs.equals(billTypeEnum) || DMSWipBillTypeEnum.TransferInWarehs.equals(billTypeEnum)) { //其他入
				groupForProfitInWarehs(ctx,billTypeEnum,hashTradeInquireGroup,dmsTradeInquireEntryInfo,returnInfo);
			} else if (DMSWipBillTypeEnum.LossOutWarehs.equals(billTypeEnum) || DMSWipBillTypeEnum.TransferOutWarehs.equals(billTypeEnum)) { //其他出
				groupForSaleIssue(ctx,billTypeEnum,hashTradeInquireGroup,dmsTradeInquireEntryInfo,returnInfo);
			} else if (DMSWipBillTypeEnum.SaleIssue.equals(billTypeEnum) || DMSWipBillTypeEnum.SaleReturn.equals(billTypeEnum)) { //销售
				groupForOtherIssue(ctx,billTypeEnum,hashTradeInquireGroup,dmsTradeInquireEntryInfo,returnInfo);
			} 
			
			
		}
    }
    
    //采购入库分组
    private void groupForPurInwarehs(Context ctx, DMSWipBillTypeEnum billTypeEnum,HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup,
    		DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo,ServerReturnInfo returnInfo) throws Exception {
	    	String expMsg = "";
	    	int seq = dmsTradeInquireEntryInfo.getSeq();
			boolean isPlus = dmsTradeInquireEntryInfo.getSupplyQty().compareTo(BigDecimal.ZERO) > 0;
			Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
			String rqn = dmsTradeInquireEntryInfo.getRqn();
			//key = [出库]+[P/O Rqn]+[供应数量]正负
			String key = sfDate.format(bizDate) + rqn + String.valueOf(isPlus);
		
			//检查数据据合法性
    		//P/O Rqn
    
    		if (PublicUtils.isEmpty(rqn)) {
    			expMsg = String.format("DMS交易查询],第%d行,P/O Rqn不合法,不能为空.",seq);
    			addExceptionMsg(returnInfo, expMsg);
    			return;
    		}
    		
    		HashMap<String,DMSInOutQueryEntryCollection> hashTradeInquireCol = hashTradeInquireGroup.get(billTypeEnum);
    		if (hashTradeInquireCol == null) {
    			hashTradeInquireCol = new HashMap<String, DMSInOutQueryEntryCollection>();
    			hashTradeInquireGroup.put(billTypeEnum, hashTradeInquireCol);
    		}
    		DMSInOutQueryEntryCollection hashTradeInquireEntryCol = hashTradeInquireCol.get(key);
    		if (hashTradeInquireEntryCol == null) {
    			hashTradeInquireEntryCol = new DMSInOutQueryEntryCollection();
    			hashTradeInquireCol.put(key, hashTradeInquireEntryCol);
    		}
    		hashTradeInquireEntryCol.add(dmsTradeInquireEntryInfo);
    }
    
    //其他入库分组
    private void groupForProfitInWarehs(Context ctx, DMSWipBillTypeEnum billTypeEnum,HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup,
    		DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo,ServerReturnInfo returnInfo) throws Exception {
	    	String expMsg = "";
	    	int seq = dmsTradeInquireEntryInfo.getSeq();
			Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
			String wip = dmsTradeInquireEntryInfo.getWip();
			//key = [出库]+ [WIP号]
			String key = sfDate.format(bizDate) + wip;
    		
    		HashMap<String,DMSInOutQueryEntryCollection> hashTradeInquireCol = hashTradeInquireGroup.get(billTypeEnum);
    		if (hashTradeInquireCol == null) {
    			hashTradeInquireCol = new HashMap<String, DMSInOutQueryEntryCollection>();
    			hashTradeInquireGroup.put(billTypeEnum, hashTradeInquireCol);
    		}
    		DMSInOutQueryEntryCollection hashTradeInquireEntryCol = hashTradeInquireCol.get(key);
    		if (hashTradeInquireEntryCol == null) {
    			hashTradeInquireEntryCol = new DMSInOutQueryEntryCollection();
    			hashTradeInquireCol.put(key, hashTradeInquireEntryCol);
    		}
    		hashTradeInquireEntryCol.add(dmsTradeInquireEntryInfo);
    }
    
    //销售出库分组
    private void groupForSaleIssue(Context ctx, DMSWipBillTypeEnum billTypeEnum,HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup,
    		DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo,ServerReturnInfo returnInfo) throws Exception {
	    	String expMsg = "";
	    	int seq = dmsTradeInquireEntryInfo.getSeq();
			Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
			String wip = dmsTradeInquireEntryInfo.getWip();
			//key = [出库]+ [WIP号]
			String key = sfDate.format(bizDate) + wip;
    		
    		HashMap<String,DMSInOutQueryEntryCollection> hashTradeInquireCol = hashTradeInquireGroup.get(billTypeEnum);
    		if (hashTradeInquireCol == null) {
    			hashTradeInquireCol = new HashMap<String, DMSInOutQueryEntryCollection>();
    			hashTradeInquireGroup.put(billTypeEnum, hashTradeInquireCol);
    		}
    		DMSInOutQueryEntryCollection hashTradeInquireEntryCol = hashTradeInquireCol.get(key);
    		if (hashTradeInquireEntryCol == null) {
    			hashTradeInquireEntryCol = new DMSInOutQueryEntryCollection();
    			hashTradeInquireCol.put(key, hashTradeInquireEntryCol);
    		}
    		hashTradeInquireEntryCol.add(dmsTradeInquireEntryInfo);
    }
    
    
    //其他出库分组
    private void groupForOtherIssue(Context ctx, DMSWipBillTypeEnum billTypeEnum,HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup,
    		DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo,ServerReturnInfo returnInfo) throws Exception {
	    	String expMsg = "";
	    	int seq = dmsTradeInquireEntryInfo.getSeq();
			Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
			String wip = dmsTradeInquireEntryInfo.getWip();
			//key = [出库]+ [WIP号]
			String key = sfDate.format(bizDate) + wip;
    		
    		HashMap<String,DMSInOutQueryEntryCollection> hashTradeInquireCol = hashTradeInquireGroup.get(billTypeEnum);
    		if (hashTradeInquireCol == null) {
    			hashTradeInquireCol = new HashMap<String, DMSInOutQueryEntryCollection>();
    			hashTradeInquireGroup.put(billTypeEnum, hashTradeInquireCol);
    		}
    		DMSInOutQueryEntryCollection hashTradeInquireEntryCol = hashTradeInquireCol.get(key);
    		if (hashTradeInquireEntryCol == null) {
    			hashTradeInquireEntryCol = new DMSInOutQueryEntryCollection();
    			hashTradeInquireCol.put(key, hashTradeInquireEntryCol);
    		}
    		hashTradeInquireEntryCol.add(dmsTradeInquireEntryInfo);
    }
    private DMSWipBillTypeEnum getWipBillType(DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo, HashMap<String,String> hashWipStocktaking) throws Exception {
    	String t = dmsTradeInquireEntryInfo.getT();
    	BigDecimal qty = dmsTradeInquireEntryInfo.getQty();
    	BigDecimal supplyQty = dmsTradeInquireEntryInfo.getSupplyQty();
    	String customer = dmsTradeInquireEntryInfo.getCustomer();
    	String wip = dmsTradeInquireEntryInfo.getWip();
    	Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
    	String keyWip =  wip + "-" + sfYear.format(bizDate);
    	Calendar calendar = new GregorianCalendar();
		calendar.setTime(bizDate);
		calendar.add(Calendar.YEAR, -1);
    	String keyWip2 = wip + "-" + sfYear.format(calendar.getTime());
    	
    	if ("P".equals(t) && supplyQty.compareTo(BigDecimal.ZERO) > 0) { //采购入库
    		return DMSWipBillTypeEnum.PurInWarehs;
    	} else if ("P".equals(t) && supplyQty.compareTo(BigDecimal.ZERO) < 0) { //采购退货
    		return DMSWipBillTypeEnum.PurReturn;
    	} else if ("S".equals(t) && "@TransTo".equals(customer)) { //其他入库(调拨)
    		return DMSWipBillTypeEnum.TransferInWarehs;
    	} else if ("S".equals(t) && (hashWipStocktaking.get(keyWip) != null || hashWipStocktaking.get(keyWip2) != null)
    			&& qty.compareTo(BigDecimal.ZERO) < 0) { //其他入库(盘盈)
    		return DMSWipBillTypeEnum.ProfitInWarehs;
    	} else if ("S".equals(t) && "@TransFr".equals(customer)) { //其他出库(调拨)
    		return DMSWipBillTypeEnum.TransferOutWarehs;
    	} else if ("S".equals(t) && (hashWipStocktaking.get(keyWip) != null || hashWipStocktaking.get(keyWip2) != null)
    			&& qty.compareTo(BigDecimal.ZERO) > 0) { //其他出库(盘亏)
    		return DMSWipBillTypeEnum.LossOutWarehs;
    	} else if ("S".equals(t) && !"@TransTo".equals(customer) && !"@TransTo".equals(customer) &&
    			hashWipStocktaking.get(keyWip) == null && hashWipStocktaking.get(keyWip2) == null &&
    			qty.compareTo(BigDecimal.ZERO) > 0) { //普通销售
    		return DMSWipBillTypeEnum.SaleIssue;
    	} else if ("S".equals(t) && !"@TransTo".equals(customer) && !"@TransTo".equals(customer) &&
    			hashWipStocktaking.get(keyWip) == null && hashWipStocktaking.get(keyWip2) == null &&
    			qty.compareTo(BigDecimal.ZERO) < 0) { //销售退货
    		return DMSWipBillTypeEnum.SaleReturn;
    	}
    	
    	return null;
    	
    }
    
    private HashMap<String,String> getAllWipStocktaking(Context ctx, ServiceOrgUnitInfo serviceOrgUnit) throws Exception {
    	HashMap<String,String> keyWip = new HashMap<String, String>();
    	String sql = String.format("select distinct CFKeyWip from CT_SYN_DMSCheckWipNo where CFServiceOrgUnitID='%s'",serviceOrgUnit.getString("id"));
    	
    	IRowSet rs = DbUtil.executeQuery(ctx, sql);
    	while (rs!=null && rs.next()) {
    		keyWip.put(rs.getString("CFKeyWip"), rs.getString("CFKeyWip"));
    	}
    	return keyWip;
    }
    
    
}