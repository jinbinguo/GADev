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
    	returnInfo.appendSpentMsg("��ȡDMSWIP�����ݺ�ʱ(ms):");
    	if (dmsWipBill == null) dmsWipBill = DMSWipBillFactory.getLocalInstance(ctx);
    	if (repairWO == null) repairWO = RepairWOFactory.getLocalInstance(ctx);
    	
    	
    	DMSWipBillInfo dmsWipBillInfo = dmsWipBill.getDMSWipBillInfo(dmsWipBillPk);
    	returnInfo.addSpentMsg(String.valueOf(System.currentTimeMillis()-start));
    	
    	try {
    		HashMap<String,RepairWOInfo> hashRepairWOInfo = new HashMap<String, RepairWOInfo>();
    		//WIPͷ
    		parseWipHead(ctx,hashRepairWOInfo,dmsWipBillInfo,returnInfo);
    		//�����
    		parseWipMaterial(ctx,hashRepairWOInfo,dmsWipBillInfo,returnInfo);
    		//��ʱ��
    		parseWipManHour(ctx,hashRepairWOInfo,dmsWipBillInfo,returnInfo);
    		//�ۼƽ��ϼ�
    		if (!returnInfo.isExption)
    			parseTotalAmonut(ctx,hashRepairWOInfo,returnInfo);
    		//����
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
    	final String DEFAULT_DMS_SAID = "zfcAAAADYhGA733t"; //DMS���� GA2014010063
    	PersonInfo defaultSA = new PersonInfo();
    	defaultSA.put("id", DEFAULT_DMS_SAID);
    	
    	//TODO Ĭ�ϳ��� -����
    	final String DEFAULT_DMS_Vehicle = "r1AAAAAAhl+en/9F";
    	VehicleInfo defaultVehicle = new VehicleInfo();
    	defaultVehicle.put("id", DEFAULT_DMS_Vehicle);
    	
    	//Ʒ�� 
    	final String DEFAULT_DMS_Brand_BWM = "zfcAAAAABfHINe3g"; //BWM 001
    	BrandInfo defaultBrand_BWM = new BrandInfo();
    	defaultBrand_BWM.put("id", DEFAULT_DMS_Brand_BWM);
    	
    	final String DEFAULT_DMS_Brand_MINI = "zfcAAAAABfLINe3g"; //MINI 002
    	BrandInfo defaultBrand_MINI = new BrandInfo();
    	defaultBrand_MINI.put("id", DEFAULT_DMS_Brand_MINI);
    	
    	
    	//��������
    	final String DEFAULT_WarrantyType_BWM = "zfcAAAADYhrW0/uD"; //DMS�������ͣ������б�-���� XMZB-DMS-01
    	WarrantyTypeInfo defaultWarrantyType_BWM = new WarrantyTypeInfo();
    	defaultWarrantyType_BWM.put("id", DEFAULT_WarrantyType_BWM);
    	
    	final String DEFAULT_WarrantyType_MINI = "zfcAAAADYhvW0/uD"; //DMS�������ͣ������б�-MINI���� XMZB-DMS-02
    	WarrantyTypeInfo defaultWarrantyType_MINI = new WarrantyTypeInfo();
    	defaultWarrantyType_MINI.put("id", DEFAULT_WarrantyType_MINI);
    	
    	//ά������
    	final String DEFAULT_RepairType_BWM = "zfcAAAADYhcA1NDU"; //DMSά�����ͣ������б�-���� XMZB-DMS-01
    	RepairTypeInfo defaultRepairType_BWM = new RepairTypeInfo();
    	defaultRepairType_BWM.put("id",  DEFAULT_RepairType_BWM);
    	
    	final String DEFAULT_RepairType_MINI = "zfcAAAADYhcA1NDU"; //DMSά�����ͣ������б�-MINI��XMZB-DMS-02
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
    			expMsg = String.format("DMSWIP��-[WIPͷ],��%d��,�������ڲ���Ϊ��", seq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		//--wip��
    		String wip = wipHeadInfo.getWip();
    		try {
    			int wipNo = Integer.valueOf(wip);
    			if (wipNo < 10001 || wipNo > 65000) {
    				expMsg = String.format("DMSWIP��-[WIPͷ],��%d��,WIP��[%s]���Ϸ���Ӧ����10001~65000֮��",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
    			}
    		} catch (Exception e) {
    			expMsg = String.format("DMSWIP��-[WIPͷ],��%d��,WIP��[%s]��ʽ����ȷ",seq,wip);
    			addExceptionMsg(returnInfo, expMsg);
    		}
    		//wip�Զ���ʶ�����ͨ��win + "-" + �������
    		
    		String keyWip = wip + "-" + String.valueOf(sfYear.format(creatTime));
    		
    		if (isIgnoreWipForStocktaking(accountCode)) { 
    			//TODO ����̵�WIP
    			hashWipForStocktaking.put(keyWip, keyWip);
    			
    			continue;
    		}
    		

    		
    		
    		//-----------��ѯά�޹������п��ܸ���ά�޹���
    		long l = System.currentTimeMillis();
    		RepairWOInfo repairWOInfo = null;
    		try {
    			sql = String.format("select FID,FNumber from T_ATS_RepairWO where FOldID='%s'",keyWip);
    			IRowSet rs = DbUtil.executeQuery(ctx, sql);
    			if (rs != null && rs.next()) {
    				IObjectPK pk = new ObjectUuidPK(rs.getString("FID"));
        			repairWOInfo = repairWO.getRepairWOInfo(pk);
    			}
    			spentMsg = String.format("DMSWIP��-[WIPͷ],��%d��,getRepairWOInfo��ʱ(ms):%s", seq,String.valueOf(System.currentTimeMillis()-l));
    			returnInfo.addSpentMsg(spentMsg);
    		} catch (Exception ee) {
    			if (ee instanceof ObjectNotFoundException) {
    				spentMsg = String.format("DMSWIP��-[WIPͷ],��%d��,������ά�޹���getRepairWOInfo��ʱ(ms):%d", seq, String.valueOf(System.currentTimeMillis()-l));
    				returnInfo.addSpentMsg(spentMsg);
    			} else {
    				expMsg = String.format("DMSWIP��-[WIPͷ],��%d��,WIP��[%s]�ظ�",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
    			}
    			ee.printStackTrace();
    		}
    		if (repairWOInfo == null) repairWOInfo = new RepairWOInfo();
    		
    		//�������
    		repairWOInfo.setSA(defaultSA);
    		
    		if (hashRepairWOInfo.get(keyWip) != null) {
    			expMsg = String.format("DMSWIP��-[WIPͷ],��%d��,WIP��[%s]�ظ�",seq,wip);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		//���ұ��
    		repairWOInfo.setCompanyNumber(wip);
    		//���̺�
    		String vin = wipHeadInfo.getVin();
    		if ("".equals(vin)) {
    			repairWOInfo.setVehicle(defaultVehicle);
    		} else {
    			sql = String.format("select FID,FOrderCustomerID,FOwnerName,FPhone,FBrandID from T_ATS_Vehicle where FVIN='%s'", vin);
    			IRowSet rs = DbUtil.executeQuery(ctx, sql);
    			if (rs == null || !rs.next()) {
    				expMsg = String.format("DMSWIP��-[WIPͷ],��%d��,���̺�[%s]�������ڻ�����������",seq,vin);
        			addExceptionMsg(returnInfo, expMsg);
    			} else {
    				//rs.next();
    				//����
    				VehicleInfo vehicleInfo = new VehicleInfo();
    				vehicleInfo.put("id", rs.getString("FID"));
    				repairWOInfo.setVehicle(vehicleInfo);
    				//�ͻ�
    				CustomerInfo customerInfo = new CustomerInfo();
    				customerInfo.put("id", rs.getString("FOrderCustomerID"));
    				repairWOInfo.setCustomer(customerInfo);
    				vehicleInfo.setCustomer(customerInfo);
    				//������
    				repairWOInfo.setRepairSender(rs.getString("FOwnerName"));
 
    				//�����˵绰 
    				repairWOInfo.setTel(rs.getString("FPhone"));
    				//Ʒ��
    				BrandInfo brandInfo = new BrandInfo();
    				brandInfo.put("id", rs.getString("FBrandID"));
    				vehicleInfo.setBrand(brandInfo);
    				repairWOInfo.setBrand(brandInfo);

    			}
    		}
    
    		//��˾
    		repairWOInfo.setOrgUnit(serviceOrgInfo);
    		//����״̬
    		repairWOInfo.setStatus(RepairBillStatusEnum.SENDING);
    		//����ʱ��
    		repairWOInfo.setComeTime(creatTime);
    		//Ԥ�ƽ���ʱ��
    		repairWOInfo.setIntendDeliveryTime(creatTime);
    		//ά�޷��úϼ�    		repairWOInfo.setRepairTotalAmount(item)
    		//���տ���ϼ�    		repairWOInfo.setReceiveTotalAmount(item)
    		//�ѿ�Ʊ���ϼ�			repairWOInfo.setInvoicedTotalAmount(item)
    		
    		//�Ƿ���������̨��
    		repairWOInfo.setIsStat(true);
    		//���������
    		repairWOInfo.setMile(wipHeadInfo.getMileage());
    		//����
    		repairWOInfo.setOilQty(OilQuantityEnum.ZERO);
    		if (DEFAULT_DMS_Brand_BWM.equals(repairWOInfo.getBrand().getString("id"))) {
    			//��������
        		repairWOInfo.setWarrantyType(defaultWarrantyType_BWM);
        		//ά������
        		repairWOInfo.setRepairType(defaultRepairType_BWM);
    			
    		} else if (DEFAULT_DMS_Brand_MINI.equals(repairWOInfo.getBrand().getString("id"))) {
    			//��������
        		repairWOInfo.setWarrantyType(defaultWarrantyType_MINI);
        		//ά������
        		repairWOInfo.setRepairType(defaultRepairType_BWM);
    		}
    		
    		
    		//�Ƿ��ڵ�ȴ�
    		repairWOInfo.setIsWaitForStore(true);
    		//ԭ������ --WIP��+���
    		repairWOInfo.setOldID(keyWip);
    		//��������
    		repairWOInfo.setReturnRepair(FReturnRepairEnum.δ����);
    		//ά�޷�ʽ
    		repairWOInfo.setRepairWay(RepairWayEnum.DEFAULT);
    		//ҵ������
    		repairWOInfo.setBizDate(creatTime);
    		
    		hashRepairWOInfo.put(keyWip, repairWOInfo);
    	}
    	//�����̵�WIP
    	
    	String[] keyWips = PublicUtils.hashKeyToArray(hashWipForStocktaking);
    	if (keyWips != null && keyWips.length > 0) {
	    	sql = "insert into CT_SYN_DMSCheckWipNo(FID,CFServiceOrgUnitID,CFKeyWip) " +
	    			"values (newbosid('92B6011A'),'" + serviceOrgInfo.getString("id") + "',?)";
	    	DbUtil.execute(ctx, sql, keyWips);
    	}

    	
    	spentMsg = String.format("��ȡWIPͷ�����ݺ�ʱ(ms):%s", String.valueOf(System.currentTimeMillis()-start));
    	returnInfo.addSpentMsg(spentMsg);
    }
    
    private void parseWipMaterial(Context ctx,HashMap<String,RepairWOInfo> hashRepairWOInfo,DMSWipBillInfo dmsWipBillInfo,ServerReturnInfo returnInfo) throws Exception {
    	String expMsg;
    	String spentMsg;
    	String sql;
    	
    	//������� DMS�������
    	final String DEFAULT_Paymentclassify = "zfcAAAADYhjnpYpd";
    	PaymentClassifyInfo defaultPaymentclassify = new PaymentClassifyInfo();
    	defaultPaymentclassify.put("id", DEFAULT_Paymentclassify);
    	
    	//ά������ 
    	final String DEFAULT_RepairClassify_BWM = "zfcAAAADYh5HVb8e"; //DMSά�����ࣨ�����б�-���� XMZB-DMS-01
    	RepairClassifyInfo defaultRepairClassify_BWM = new RepairClassifyInfo();
    	defaultRepairClassify_BWM.put("id", DEFAULT_RepairClassify_BWM);
    	
    	final String DEFAULT_RepairClassify_MINI = "zfcAAAADYh9HVb8e"; //DMSά�����ࣨ�����б�-MINI�� XMZB-DMS-02
    	RepairClassifyInfo defaultRepairClassify_MINI = new RepairClassifyInfo();
    	defaultRepairClassify_MINI.put("id", DEFAULT_RepairClassify_MINI);
    	
    	//Ʒ�� 
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
    			expMsg = String.format("DMSWIP��-[�����],��%d��,�к�[%s]���Ϸ�,Ӧ�ڴ���1����",seq,lineSeq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		String wip = wipMaterialInfo.getWip(); 
    		try {
    			int wipNo = Integer.valueOf(wip);
    			if (wipNo < 10001 || wipNo > 65000) {
    				expMsg = String.format("DMSWIP��-[�����],��%d��,WIP��[%s]���Ϸ���Ӧ����10001~65000֮��",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
    			}
    		} catch (Exception e) {
    			expMsg = String.format("DMSWIP��-[�����],��%d��,WIP��[%s]��ʽ����ȷ",seq,wip);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		
    		Date editDate = hashAdjustDate.get(wip); //�Ȳ鿴�Ƿ񱻵�����
    		if (editDate == null) editDate = wipMaterialInfo.getLastEditTime();
    		long l = System.currentTimeMillis();
    		String keyWip = wip + "-" + String.valueOf(sfYear.format(editDate));
    		//ServiceOrgUnitInfo serviceOrgUnit = dmsWipBillInfo.getServiceOrgUnit();
    		
    		RepairWOInfo repairWOInfo = hashRepairWOInfo.get(keyWip);
    		if (repairWOInfo == null) { //�ڵ�ǰWIPͷ��Ҳ���
    			//��ά�޹���������ȡ,ȡ��ǰ�༭��ݻ�ǰ�༭ǰһ��ݣ�����������
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
    				repairWOInfo = repairWOCol.get(0); //ȡǰ��һ��
    				hashAdjustDate.put(wip, repairWOInfo.getBizDate()); //����ʱ�� Ϊά�޹�����ҵ��ʱ��
    			} else {
    				expMsg = String.format("DMSWIP��-[�����],��%d��,������WIP��[%s]��ά�޹���",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
    				continue;
    			}
    			hashRepairWOInfo.put(keyWip, repairWOInfo);
    		}
    		spentMsg = String.format("DMSWIP��-[�����]����DMSWIP�����ݺ�ʱ(ms):%s", String.valueOf(System.currentTimeMillis()-l));
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
    		
    		//DMS���۽��(����˰)
    		BigDecimal saleNoTaxAmount = wipMaterialInfo.getSalePrice();
    		if (saleNoTaxAmount == null || saleNoTaxAmount.compareTo(BigDecimal.ZERO) <= 0) {
    			expMsg = String.format("DMSWIP��-[�����],��%d��,���۽��������0",seq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		l = System.currentTimeMillis();
    		
    		//DMS�к�
    		sparepartEntryInfo.put("wipLineNo", lineSeq);
    		//DMSʵ���к�
    		sparepartEntryInfo.put("wipFactLineNo", factLineSeq);
    		
    		//�������
    		sparepartEntryInfo.setSettleObject(SettlementObjectEnum.CUST);
    		//����/DMS��������
    		BigDecimal qty = wipMaterialInfo.getOrderQty();
    		if (qty == null || qty.compareTo(BigDecimal.ZERO) <= 0) {
    			expMsg = String.format("DMSWIP��-[�����],��%d��,���������������0",seq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		sparepartEntryInfo.setQty(qty);
    		//DMS���۵���(����˰)
    		BigDecimal saleNoTaxPrice = saleNoTaxAmount.divide(qty,10,BigDecimal.ROUND_HALF_UP);
    		//˰�� 17.0%
    		BigDecimal taxRate = new BigDecimal(17.00);
    		sparepartEntryInfo.setTaxRate(taxRate);
    		
    		//��˰���� =(1+˰��%/100)*DMS���۵���(����˰)
    		BigDecimal saleTaxPrice = saleNoTaxPrice.multiply(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)));
    		sparepartEntryInfo.setTaxPrice(saleTaxPrice);
    		
    		//����˰����=DMS���۵���(����˰)
    		sparepartEntryInfo.setNoTaxPrice(saleNoTaxPrice);
    		//��˰���=��˰����*����
    		BigDecimal saleTaxAmount = saleTaxPrice.multiply(qty);
    		sparepartEntryInfo.setTaxAmount(saleTaxAmount);
    		//����˰���=DMS���۵���(����˰)*����
    		sparepartEntryInfo.setNoTaxAmount(saleNoTaxPrice.multiply(qty));
    		
    		//˰��=����˰����*˰��/100
    		sparepartEntryInfo.setTax(saleNoTaxPrice.multiply(taxRate.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP)));
    		//�ۿ���
    		BigDecimal discountRate = wipMaterialInfo.getDiscountRate();
    		sparepartEntryInfo.setDiscountRate(discountRate);
    		//�Żݽ��=��˰���*�ۿ���/100
    		BigDecimal discountAmount = saleTaxAmount.multiply(discountRate.divide(new BigDecimal(100.00),10,BigDecimal.ROUND_HALF_UP));
    		sparepartEntryInfo.setDiscountAmount(discountAmount);
    		//Ӧ�ս��=��˰���-�Żݽ��
    		sparepartEntryInfo.setARAmount(saleTaxAmount.subtract(discountAmount));
    		//ʵ�ս��=��˰���-�Żݽ��
    		sparepartEntryInfo.setActualAmount(saleTaxAmount.subtract(discountAmount));
    		
    		//TODO �Ƿ�ɾ����DMS����״̬="D",��Ϊ�����У����������EAS���Ƿ�ɾ��һ������ȷ��    
    		if ("D".equals(wipMaterialInfo.getOrderStatus())) {
    			sparepartEntryInfo.setIsDelete(true);
    		}
    		//�������
    		sparepartEntryInfo.setPaymentClassify(defaultPaymentclassify);
    		//ά������
    		if (defaultBrand_BWM.equals(repairWOInfo.getBrand().getString("id"))) {
    			sparepartEntryInfo.setRepairClassify(defaultRepairClassify_BWM);
    		} else if (defaultBrand_MINI.equals(repairWOInfo.getBrand().getString("id"))) {
    			sparepartEntryInfo.setRepairClassify(defaultRepairClassify_MINI);
    		}

    		//����
    		String materialNum = wipMaterialInfo.getMaterialNum();
    		if (PublicUtils.isEmpty(materialNum)) {
    			expMsg = String.format("DMSWIP��-[�����],��%d��,�����Ų���Ϊ��",seq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		spentMsg = String.format("DMSWIP��-[�����]��ֵsparepartEntryInfo��ʱ(ms):%s", String.valueOf(System.currentTimeMillis()-l));
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
    				spentMsg = String.format("DMSWIP��-[�����]�����������ݺ�ʱ(ms):%s", String.valueOf(System.currentTimeMillis()-l));
    				returnInfo.addSpentMsg(spentMsg);
    				hashMaterial.put(materialNum, materialInfo);
    			} else {
    				expMsg = String.format("DMSWIP��-[�����],��%d��,������[%s]��������EASϵͳ",seq,materialNum);
        			addExceptionMsg(returnInfo, expMsg);
        			spentMsg = String.format("DMSWIP��-[�����]�����������ݺ�ʱ(ms):%s", String.valueOf(System.currentTimeMillis()-l));
        			returnInfo.addSpentMsg(spentMsg);
        			continue;
    			}
    		}
    		
    		
    		
    		sparepartEntryInfo.setMaterial(materialInfo);
    		//������λ
    		sparepartEntryInfo.setUnit(materialInfo.getBaseUnit());
    		//δ������
    		sparepartEntryInfo.setNoIssueQty(qty);
    		//��������
    		sparepartEntryInfo.setBaseQty(qty);
    		//����������λ
    		sparepartEntryInfo.setBaseUnit(materialInfo.getBaseUnit());
    		//���Ϸ���
    		sparepartEntryInfo.setMaterialGroup(materialInfo.getMaterialGroup());
    	}
    	
    	spentMsg = String.format("��ȡ��������ݺ�ʱ(ms):%s", String.valueOf(System.currentTimeMillis()-start));
    	returnInfo.addSpentMsg(spentMsg);
    }
    
    private void parseWipManHour(Context ctx,HashMap<String,RepairWOInfo> hashRepairWOInfo,DMSWipBillInfo dmsWipBillInfo,ServerReturnInfo returnInfo) throws Exception {
       	String expMsg;
    	String spentMsg;
    	String sql;
    	
    	//Ʒ�� 
    	final String DEFAULT_DMS_Brand_BWM = "zfcAAAAABfHINe3g"; //BWM 001
    	BrandInfo defaultBrand_BWM = new BrandInfo();
    	defaultBrand_BWM.put("id", DEFAULT_DMS_Brand_BWM);
    	
    	final String DEFAULT_DMS_Brand_MINI = "zfcAAAAABfLINe3g"; //MINI 002
    	BrandInfo defaultBrand_MINI = new BrandInfo();
    	defaultBrand_MINI.put("id", DEFAULT_DMS_Brand_MINI);
    	
    	//������� DMS�������
    	final String DEFAULT_Paymentclassify = "zfcAAAADYhjnpYpd";
    	PaymentClassifyInfo defaultPaymentclassify = new PaymentClassifyInfo();
    	defaultPaymentclassify.put("id", DEFAULT_Paymentclassify);
    	
    	//ά������ 
    	final String DEFAULT_RepairClassify_BWM = "zfcAAAADYh5HVb8e"; //DMSά�����ࣨ�����б�-���� XMZB-DMS-01
    	RepairClassifyInfo defaultRepairClassify_BWM = new RepairClassifyInfo();
    	defaultRepairClassify_BWM.put("id", DEFAULT_RepairClassify_BWM);
    	
    	final String DEFAULT_RepairClassify_MINI = "zfcAAAADYh9HVb8e"; //DMSά�����ࣨ�����б�-MINI�� XMZB-DMS-02
    	RepairClassifyInfo defaultRepairClassify_MINI = new RepairClassifyInfo();
    	defaultRepairClassify_MINI.put("id", DEFAULT_RepairClassify_MINI);
    	
    	//ά�ް���
    	final String DEFAULT_RepairGroup_BWM = "zfcAAAADYjAZCugl"; //DMSά�ް��飨�����б�-���� XMZB-DMS-01
    	RepairGroupInfo defaultRepairGroup_BWM = new RepairGroupInfo();
    	defaultRepairGroup_BWM.put("id", DEFAULT_RepairGroup_BWM);
    	
    	final String DEFAULT_RepairGroup_MINI = "zfcAAAADYjIZCugl"; //DMSά�ް��飨�����б�-MINI�� XMZB-DMS-02
    	RepairGroupInfo defaultRepairGroup_MINI = new RepairGroupInfo();
    	defaultRepairGroup_MINI.put("id", DEFAULT_RepairGroup_MINI);
    	
    	
    	//ά����Ŀ  
    	final String DEFAULT_RepairItem_BWM = "zfcAAAADYiAAz7yt"; //DMSά����Ŀ�������б�-����XMZB-DMS-01
    	RepairItemInfo defaultRepairItem_BWM = new RepairItemInfo();
    	defaultRepairItem_BWM.put("id", DEFAULT_RepairItem_BWM);
    	
    	final String DEFAULT_RepairItem_MINI = "zfcAAAADYiIAz7yt"; //DMSά����Ŀ�������б�-MINI��XMZB-DMS-02
    	RepairItemInfo defaultRepairItem_MINI = new RepairItemInfo();
    	defaultRepairItem_MINI.put("id", DEFAULT_RepairItem_MINI);
    	
    	//ά������
    	final String DEFAULT_RepairType_BWM = "zfcAAAADYhcA1NDU"; //DMSά�����ͣ������б�-����XMZB-DMS-01
    	RepairTypeInfo defaultRepairType_BWM = new RepairTypeInfo();
    	defaultRepairType_BWM.put("id",  DEFAULT_RepairType_BWM);
    	
    	final String DEFAULT_RepairType_MINI = "zfcAAAADYhkA1NDU"; //DMSά�����ͣ������б�-MINI��XMZB-DMS-02
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
    			expMsg = String.format("DMSWIP��-[��ʱ��],��%d��,�к�[%s]���Ϸ�,Ӧ���ڵ���1",seq,lineSeq);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		String wip = wipManHourInfo.getWip(); 
    		try {
    			int wipNo = Integer.valueOf(wip);
    			if (wipNo < 10001 || wipNo > 65000) {
    				expMsg = String.format("DMSWIP��-[��ʱ��],��%d��,WIP��[%s]���Ϸ���Ӧ����10001~65000֮��",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
    			}
    		} catch (Exception e) {
    			expMsg = String.format("DMSWIP��-[��ʱ��],��%d��,WIP��[%s]��ʽ����ȷ",seq,wip);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		
    		Date editDate = hashAdjustDate.get(wip); //�Ȳ鿴�Ƿ񱻵�����
    		if (editDate == null) editDate = wipManHourInfo.getLastEditTime();
    		
    		String keyWip = wip + "-" + String.valueOf(sfYear.format(editDate));
    		//ServiceOrgUnitInfo serviceOrgUnit = dmsWipBillInfo.getServiceOrgUnit();
    		
    		RepairWOInfo repairWOInfo = hashRepairWOInfo.get(keyWip);
    		long l = System.currentTimeMillis();
    		if (repairWOInfo == null) { //�ڵ�ǰWIPͷ��Ҳ���
    			//��ά�޹���������ȡ,ȡ��ǰ�༭��ݻ�ǰ�༭ǰһ��ݣ�����������
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
    				repairWOInfo = repairWOCol.get(0); //ȡǰ��һ��
    				hashAdjustDate.put(wip, repairWOInfo.getBizDate()); //����ʱ�� Ϊά�޹�����ҵ��ʱ��
    			} else {
    				expMsg = String.format("DMSWIP��-[��ʱ��],��%d��,������WIP��[%s]��ά�޹���",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
    				continue;
    			}
    			hashRepairWOInfo.put(keyWip, repairWOInfo);
    		}
    		spentMsg = String.format("DMSWIP��-[��ʱ��]����DMSWIP�����ݺ�ʱ(ms):%s", String.valueOf(System.currentTimeMillis()-l));
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

    		//DMS�к�
    		repairItemEntryInfo.put("wipLineNo", lineSeq);
    		//DMSʵ���к�
    		repairItemEntryInfo.put("wipFactLineNo", factLineSeq);
    		//�������
    		repairItemEntryInfo.setPaymentClassify(defaultPaymentclassify);
    		//�������-�ͻ�
    		repairItemEntryInfo.setSettleObject(SettlementObjectEnum.CUST);
    		if (DEFAULT_DMS_Brand_BWM.equals(repairWOInfo.getBrand().getString("id"))) {
    			//ά������
        		repairItemEntryInfo.setRepairClassify(defaultRepairClassify_BWM);
        		//ά�ް���
        		repairItemEntryInfo.setWorkGroup(defaultRepairGroup_BWM);
        		//ά����Ŀ
        		repairItemEntryInfo.setRepairItem(defaultRepairItem_BWM);
        		//ά������
        		repairItemEntryInfo.setRepairType(defaultRepairType_BWM);
    			
    		} else if (DEFAULT_DMS_Brand_MINI.equals(repairWOInfo.getBrand().getString("id"))) {
    			//ά������
        		repairItemEntryInfo.setRepairClassify(defaultRepairClassify_MINI);
        		//ά�ް���
        		repairItemEntryInfo.setWorkGroup(defaultRepairGroup_MINI);
        		//ά����Ŀ
        		repairItemEntryInfo.setRepairItem(defaultRepairItem_MINI);
        		//ά������
        		repairItemEntryInfo.setRepairType(defaultRepairType_MINI);
    		}
    		
    		//��׼��ʱ
    		BigDecimal standardHour = wipManHourInfo.getStandardHour();
    		repairItemEntryInfo.setStdWorkTime(standardHour);
    		//��ʱ����=DMSСʱ��ʱ��/(60/DMS��λ������)��DMS��λ������Ϊ0��ʱ����Ϊ0
    		BigDecimal workTimePrice = null;
    		BigDecimal hourRate = wipManHourInfo.getHourRate(); //Сʱ��ʱ��
    		int unitMI = wipManHourInfo.getUnitMI(); //���۷�����
    		if (hourRate == null || unitMI == 0) {
    			workTimePrice = BigDecimal.ZERO;	
    		} else {
    			workTimePrice = hourRate.divide(new BigDecimal(60).divide(new BigDecimal(unitMI),10,BigDecimal.ROUND_HALF_UP),10,BigDecimal.ROUND_HALF_UP);
    		}
    		repairItemEntryInfo.setWorkTimePrice(workTimePrice);
    		//��ʱ���=��׼��ʱ*��ʱ����
    		BigDecimal workTimeAmount = wipManHourInfo.getStandardHour().multiply(workTimePrice);
    		repairItemEntryInfo.setWorkTimeAmount(workTimeAmount);
    		//�ۿ���
    		BigDecimal discountRate = wipManHourInfo.getDiscountRate();
    		repairItemEntryInfo.setDiscountRate(discountRate);
    		//�ۿ۽��
    		BigDecimal discountAmount = workTimeAmount.multiply(discountRate);
    		//Ӧ�ս��=��ʱ���-�ۿ۽��
    		repairItemEntryInfo.setARAmount(workTimeAmount.subtract(discountAmount));
    		//TODO ά����Ŀ״̬
    		repairItemEntryInfo.setItemStatus(RepairItemStatusEnum.SENDING);
    		//ʵ�ʹ�ʱ=��׼��ʱ
    		repairItemEntryInfo.setActualWorkTime(standardHour);
    		//ά����Ŀ˵��
    		repairItemEntryInfo.setItemRemark(wipManHourInfo.getRemark());
    		//�ɹ���ʱ=��׼��ʱ
    		repairItemEntryInfo.setAssignWorkTime(standardHour);
    		//ʵ�ս��=��ʱ���-�ۿ۽��
    		repairItemEntryInfo.setActualAmount(workTimeAmount.subtract(discountAmount));
    		
    		//���ʵ���=DMS��ʱ����
    		repairItemEntryInfo.setWagePrice(workTimePrice);
    		//��ʱ�ɱ�=DMS��ʱ���� * ��׼��ʱ
    		repairItemEntryInfo.setWorkTimeCost(workTimePrice.multiply(standardHour));
    		
    		//��ʱ��׼����=DMS��ʱ����
    		repairItemEntryInfo.setWorkTimeStdPrice(workTimePrice);
    	}
    	
    	spentMsg = String.format("��ȡ��ʱ�����ݺ�ʱ(ms):%s", String.valueOf(System.currentTimeMillis()-start));
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
    		RepairWORWOSparepartEntryCollection sparepartEntryCol = repairWOInfo.getRWOSparepartEntry(); //���
    		RepairWORWORepairItemEntryCollection repairItemEntryCol = repairWOInfo.getRWORepairItemEntry(); //ά����Ŀ
    		if (sparepartEntryCol.isEmpty() && repairItemEntryCol.isEmpty()) {
    			expMsg = String.format("DMSWIP��-WIP��[%s]ͬʱ����������л�ʱ�У����ܱ���",wip);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		
    		 //���
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
    		 //ά����Ŀ
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

    		RepairWORWOTotalAmountEntryCollection totalAmountEntryCol = new RepairWORWOTotalAmountEntryCollection(); //�ۼƽ��
    		BigDecimal allTotalAmount = BigDecimal.ZERO;
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.COMPANY,hashTotalAmountBySettle,totalAmountEntryCol));
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.CUST,hashTotalAmountBySettle,totalAmountEntryCol));
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.INNER,hashTotalAmountBySettle,totalAmountEntryCol));
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.INSURAN,hashTotalAmountBySettle,totalAmountEntryCol));
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.OTHER,hashTotalAmountBySettle,totalAmountEntryCol));
    		allTotalAmount.add(totalAmount(SettlementObjectEnum.CLUB,hashTotalAmountBySettle,totalAmountEntryCol));
    		repairWOInfo.setRepairTotalAmount(allTotalAmount); //ά�޺ϼƽ��
    		
    		spentMsg = String.format("����WIP��[%s]�ĺϼƽ��(ms):%s", wip,String.valueOf(System.currentTimeMillis()-start));
        	returnInfo.addSpentMsg(spentMsg);
    	}
    	spentMsg = String.format("����ϼƽ��(ms):%s", String.valueOf(System.currentTimeMillis()-start));
    	returnInfo.addSpentMsg(spentMsg);
    	
    }
    
    private BigDecimal totalAmount(SettlementObjectEnum settleObj,HashMap<SettlementObjectEnum, TotalAmountBySettObj> hashTotalAmountBySettle,
    		RepairWORWOTotalAmountEntryCollection totalAmountEntryCol) {
    	int seq = totalAmountEntryCol.size();
    	TotalAmountBySettObj totalAmountBySettObj = hashTotalAmountBySettle.get(settleObj);
    	if (totalAmountBySettObj != null) {
			seq++;
			//��� �ϼ�
			RepairWORWOTotalAmountEntryInfo totalAmountSparepart = new RepairWORWOTotalAmountEntryInfo();
			totalAmountSparepart.setSettleObject(settleObj);
			totalAmountSparepart.setOldAmount(totalAmountBySettObj.sparepartTaxAmount);
			totalAmountSparepart.setARAmount(totalAmountBySettObj.sparepartSettleAmout);
			totalAmountSparepart.setSettleAmount(totalAmountBySettObj.sparepartSettleAmout);
			totalAmountSparepart.setNoTaxAmount(totalAmountBySettObj.sparepartNotTaxAmout);
			totalAmountSparepart.setTaxAmount(totalAmountBySettObj.sparepartTax);
			//�ۿ���=(1 - ������/ԭ���) * 100
			if (totalAmountSparepart.getOldAmount().compareTo(BigDecimal.ZERO) == 0)
				totalAmountSparepart.setDiscountRate(BigDecimal.ZERO);
			else
				totalAmountSparepart.setDiscountRate(BigDecimal.ONE.subtract(
					totalAmountSparepart.getSettleAmount().divide(totalAmountSparepart.getOldAmount(),10,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100.00)));
			totalAmountSparepart.setAmountClassify(AmountClassifyEnum.SPAREPART);
			totalAmountSparepart.setSeq(seq);
			totalAmountEntryCol.add(totalAmountSparepart);
			
			//ά����Ŀ �ϼ�
			seq++;
			RepairWORWOTotalAmountEntryInfo totalAmountRepairItem = new RepairWORWOTotalAmountEntryInfo();
			totalAmountRepairItem.setSettleObject(settleObj);
			totalAmountRepairItem.setOldAmount(totalAmountBySettObj.repairItemTaxAmount);
			totalAmountRepairItem.setARAmount(totalAmountBySettObj.repairItemSettleAmout);
			totalAmountRepairItem.setSettleAmount(totalAmountBySettObj.repairItemSettleAmout);
			totalAmountRepairItem.setNoTaxAmount(totalAmountBySettObj.repairItemNotTaxAmout);
			totalAmountRepairItem.setTaxAmount(totalAmountBySettObj.repairItemTax);
			//�ۿ���=(1 - ������/ԭ���) * 100
			if (totalAmountRepairItem.getOldAmount().compareTo(BigDecimal.ZERO) == 0)
				totalAmountRepairItem.setDiscountRate(BigDecimal.ZERO);
			else
				totalAmountRepairItem.setDiscountRate(BigDecimal.ONE.subtract(
					totalAmountRepairItem.getSettleAmount().divide(totalAmountRepairItem.getOldAmount(),10,BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100.00)));
			totalAmountRepairItem.setAmountClassify(AmountClassifyEnum.REPAIRITEM);
			totalAmountRepairItem.setSeq(seq);
			totalAmountEntryCol.add(totalAmountRepairItem);
			
			//С��
			seq++;
			RepairWORWOTotalAmountEntryInfo totalAmountSub = new RepairWORWOTotalAmountEntryInfo();
			totalAmountSub.setSettleObject(null);
			totalAmountSub.setOldAmount(totalAmountBySettObj.repairItemTaxAmount.add(totalAmountBySettObj.sparepartTaxAmount));
			totalAmountSub.setARAmount(totalAmountBySettObj.repairItemSettleAmout.add(totalAmountBySettObj.sparepartSettleAmout));
			totalAmountSub.setSettleAmount(totalAmountBySettObj.repairItemSettleAmout.add(totalAmountBySettObj.sparepartSettleAmout));
			totalAmountSub.setNoTaxAmount(totalAmountBySettObj.repairItemNotTaxAmout.add(totalAmountBySettObj.sparepartNotTaxAmout));
			totalAmountSub.setTaxAmount(totalAmountBySettObj.repairItemTax.add(totalAmountBySettObj.sparepartTax));
			//�ۿ���=0
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
    
    //����������ۼ�
    class TotalAmountBySettObj {
    	
    	SettlementObjectEnum settleObj = null;
    	
    	//���-ԭ���
    	BigDecimal sparepartTaxAmount = BigDecimal.ZERO;
    	//���-������
    	BigDecimal sparepartSettleAmout = BigDecimal.ZERO;
    	//���-����˰���
    	BigDecimal sparepartNotTaxAmout = BigDecimal.ZERO;
    	//���-˰��
    	BigDecimal sparepartTax = BigDecimal.ZERO;
    	
    	
    	//ά����Ŀ-ԭ���
    	BigDecimal repairItemTaxAmount = BigDecimal.ZERO;
    	//ά����Ŀ-������
    	BigDecimal repairItemSettleAmout = BigDecimal.ZERO;
    	//ά����Ŀ-����˰���
    	BigDecimal repairItemNotTaxAmout = BigDecimal.ZERO;
    	//ά����Ŀ-˰��
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
    	returnInfo.appendSpentMsg("��ȡDMS���ײ�ѯ�����ݺ�ʱ(ms):");
    	
    	if (dmsTradeInquire == null) dmsTradeInquire = DMSInOutQueryFactory.getLocalInstance(ctx);
    	if (purInwarehsBill == null) purInwarehsBill = PurInWarehsBillFactory.getLocalInstance(ctx);
    	if (otherInwarehsBill == null) otherInwarehsBill = OtherInWarehsBillFactory.getLocalInstance(ctx);
    	if (saleIssueBill == null) saleIssueBill = SaleIssueBillFactory.getLocalInstance(ctx);
    	if (otherIssueBill == null) otherIssueBill = OtherIssueBillFactory.getLocalInstance(ctx);
    	
    	
    	
    	
    	try {
    		HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup = new HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>>();
    		//����EAS�� ������
    		updateTradInquireForEASData(ctx,dmsTradeInqirePk);
    		DMSInOutQueryInfo dmsTradeInquireInfo = dmsTradeInquire.getDMSInOutQueryInfo(dmsTradeInqirePk);
        	returnInfo.addSpentMsg(String.valueOf(System.currentTimeMillis()-start));
    		
    		//���ײ�ѯ�������ݷ���
    		parseTradeInquireGroup(ctx,hashTradeInquireGroup,dmsTradeInquireInfo,returnInfo);
    		
    		
    		//���鱣��
    		if (!returnInfo.isExption)
    			batchSaveInOutBill(ctx,hashTradeInquireGroup);
    		
    		//����ά�޹���
    		
    			
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
		//ȡά�޹��������˰����
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
    	String DEFAULT_PURCHASE_ID = "zfcAAAAAABjM567U"; //�����б��������޹�˾
    	PurchaseOrgUnitInfo defaultPurchaseOrgUnit = new PurchaseOrgUnitInfo();
    	defaultPurchaseOrgUnit.put("id", DEFAULT_PURCHASE_ID);
    	
    	String DEFAULT_Storage_ID = "zfcAAAAAABjM567U"; //�����б��������޹�˾
    	StorageOrgUnitInfo defaultStorageOrgUnit = new StorageOrgUnitInfo();
    	defaultStorageOrgUnit.put("id", DEFAULT_Storage_ID);
    	
    	String DEFAULT_Company_ID = "zfcAAAAAABjM567U"; //�����б��������޹�˾
    	CompanyOrgUnitInfo defaultCompanyOrgUnit = new CompanyOrgUnitInfo();
    	defaultCompanyOrgUnit.put("id", DEFAULT_Company_ID);
    	
    	String DEFAULT_Currency_ID = "dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC"; //�����
    	CurrencyInfo defaultCurrency = new CurrencyInfo();
    	defaultCurrency.put("id", DEFAULT_Currency_ID);
    	
    	String DEFAULT_PaymentType_ID = "2fa35444-5a23-43fb-99ee-6d4fa5f260da6BCA0AB5";  //�޹�
    	PaymentTypeInfo defaultPaymentType = new PaymentTypeInfo();
    	defaultPaymentType.put("id", DEFAULT_PaymentType_ID);
    	
    	String DEFAULT_bizType_purInWarehs_ID = "d8e80652-0106-1000-e000-04c5c0a812202407435C"; //��ͨ�ɹ� 110
    	BizTypeInfo defaultBizType_PurInwarehs = new BizTypeInfo();
    	defaultBizType_PurInwarehs.put("id", DEFAULT_bizType_purInWarehs_ID);
    	
    	String DEFAULT_Tran_PurInWarehs_ID = "DawAAAAPoACwCNyn"; //��ͨ�ɹ�/ί����� 001
    	TransactionTypeInfo defaultTran_PurInWarehs = new TransactionTypeInfo();
    	defaultTran_PurInWarehs.put("id", DEFAULT_Tran_PurInWarehs_ID);
    	
    	String DEFAULT_bizType_purReturn_ID = "d8e80652-0107-1000-e000-04c5c0a812202407435C"; //��ͨ�ɹ��˻�  111
    	BizTypeInfo defaultBizType_PurReturn = new BizTypeInfo();
    	defaultBizType_PurReturn.put("id", DEFAULT_bizType_purReturn_ID);
    	
    	String DEFAULT_Tran_PurInReturn_ID = "DawAAAAPoAGwCNyn"; //��ͨ�ɹ�/ί���˻� 002
    	TransactionTypeInfo defaultTran_PurReturn = new TransactionTypeInfo();
    	defaultTran_PurReturn.put("id", DEFAULT_Tran_PurInReturn_ID);
    	
    	String Default_BillType_ID = "50957179-0105-1000-e000-015fc0a812fd463ED552"; //��������-�ɹ����
    	BillTypeInfo default_BillType = new BillTypeInfo();
    	default_BillType.put("id", Default_BillType_ID);
    	
    	
    	
    	
    	while (itKey.hasNext()) {
    		String key = itKey.next();
    		DMSInOutQueryEntryCollection dmsTradeInquireEntryCol = hashDMSTradeInquireEntryCol.get(key);
    		PurInWarehsBillInfo purInWarehsBillInfo = new PurInWarehsBillInfo();
    		PurInWarehsEntryCollection purInWarehsEntryCol = new PurInWarehsEntryCollection();
    		Date bizDate = dmsTradeInquireEntryCol.get(0).getBizDate();
    		
    		//������
    		BigDecimal totalQty = PublicUtils.BIGDECIMAL0;
    		//�ܺ�˰���
    		BigDecimal totalTaxAmount = PublicUtils.BIGDECIMAL0;
    		//�ܲ���˰���
    		BigDecimal totalNotTaxAmount = PublicUtils.BIGDECIMAL0;
    		
    		
    		
    		
    		for (int i = 0; i < dmsTradeInquireEntryCol.size(); i++) {
    			DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo = dmsTradeInquireEntryCol.get(i);
    			String warehouseNum = dmsTradeInquireEntryInfo.getL();
    			WarehouseInfo warehouseInfo = dmsTradeInquireEntryInfo.getEasWarehouse();
    			
    			PurInWarehsEntryInfo purInWarehsEntryInfo = new PurInWarehsEntryInfo();
    			//˰��
    			BigDecimal taxRate = new BigDecimal(17.00);
    			//��˰����
    			BigDecimal taxPrice = dmsTradeInquireEntryInfo.getEasTaxPrice();
    			//����˰����=��˰����/(1+˰��/100)
    			BigDecimal notTaxPrice = taxPrice.divide(PublicUtils.BIGDECIMAL1.add(taxRate.divide(PublicUtils.BIGDECIMAL100,10,BigDecimal.ROUND_HALF_UP)),10,BigDecimal.ROUND_HALF_UP);
    			//����
    			BigDecimal qty = dmsTradeInquireEntryInfo.getSupplyQty();
    			//��˰���
    			BigDecimal taxAmount = taxPrice.multiply(qty);
    			//����˰���=����˰����*����
    			BigDecimal notTaxAmount = notTaxPrice.multiply(qty);
    			//˰��=��˰���-����˰���
    			BigDecimal tax = taxAmount.subtract(notTaxAmount);
    			
    			
    			totalQty = totalQty.add(qty);
    			totalTaxAmount = totalTaxAmount.add(taxAmount);
    			totalNotTaxAmount = totalNotTaxAmount.add(notTaxAmount);
    			
    			//�Ѻ�������
    			purInWarehsEntryInfo.setWrittenOffQty(PublicUtils.BIGDECIMAL0);
    			//�Ѻ������
    			purInWarehsEntryInfo.setWrittenOffAmount(PublicUtils.BIGDECIMAL0);
    			//δ��������
    			purInWarehsEntryInfo.setUnWriteOffQty(qty);
    			//δ�������
    			purInWarehsEntryInfo.setUnWriteOffAmount(taxAmount);
    			//�ۼ��տ���
    			purInWarehsEntryInfo.setReceiveAmount(PublicUtils.BIGDECIMAL0);
    			//˰��
    			purInWarehsEntryInfo.setTaxRate(taxRate);
    			//˰��
    			purInWarehsEntryInfo.setTax(tax);
    			//��λ��˰��
    			purInWarehsEntryInfo.setLocalTax(tax);
    			//��λ�ҵ���
    			purInWarehsEntryInfo.setLocalPrice(taxPrice);
    			//��λ�ҽ��
    			purInWarehsEntryInfo.setLocalAmount(taxAmount);
    			//�Ѻ�����������
    			purInWarehsEntryInfo.setWrittenOffBaseQty(PublicUtils.BIGDECIMAL0);
    			//δ�����������
    			purInWarehsEntryInfo.setUnWriteOffBaseQty(qty);
    			//���˻���������
    			purInWarehsEntryInfo.setUnReturnedBaseQty(qty);
    			//��˰����
    			purInWarehsEntryInfo.setTaxPrice(taxPrice);
    			//ʵ�ʵ���
    			purInWarehsEntryInfo.setActualPrice(notTaxPrice);
    			//�ɹ���֯
    			purInWarehsEntryInfo.setPurchaseOrgUnit(defaultPurchaseOrgUnit);
    			//�Ƿ�������֯�����ջ���֯
    			purInWarehsEntryInfo.setIsRequestToReceived(true);
    			//�Ƿ���ȫ����
    			purInWarehsEntryInfo.setIsFullWriteOff(false);
    			//�ջ������֯
    			purInWarehsEntryInfo.setReceiveStorageOrgUnit(defaultStorageOrgUnit);
    			//�ɹ��ɱ�
    			purInWarehsEntryInfo.setPurchaseCost(notTaxAmount);
    			//��λ�ɹ��ɱ�
    			purInWarehsEntryInfo.setUnitPurchaseCost(notTaxPrice);
    			//��˰�ϼ�
    			purInWarehsEntryInfo.setTaxAmount(taxAmount);
    			//��λ�Ҽ�˰�ϼ�
    			purInWarehsEntryInfo.setLocalTaxAmount(taxAmount);
    			//ʵ�ʺ�˰����
    			purInWarehsEntryInfo.setActualTaxPrice(taxPrice);
    			//�ۿ���
    			purInWarehsEntryInfo.setDiscountRate(PublicUtils.BIGDECIMAL0);
    			//�ۿ۶�
    			purInWarehsEntryInfo.setDiscountAmount(PublicUtils.BIGDECIMAL0);
    			//����
    			purInWarehsEntryInfo.setPrice(notTaxPrice);
    			//���
    			purInWarehsEntryInfo.setAmount(notTaxAmount);
    			//ҵ������ 
    			purInWarehsEntryInfo.setBizDate(bizDate);
    			
    			
    			//InvBillBaseEntry������
    			//�����֯
    			purInWarehsEntryInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    			//������֯
    			purInWarehsEntryInfo.setCompanyOrgUnit(defaultCompanyOrgUnit);
    			//�ֿ�
    			purInWarehsEntryInfo.setWarehouse(warehouseInfo);
    			//��������
    			purInWarehsEntryInfo.setBaseQty(qty);
    			//��λ��׼�ɱ�
    			purInWarehsEntryInfo.setUnitStandardCost(notTaxPrice);
    			//��׼�ɱ�
    			purInWarehsEntryInfo.setStandardCost(notTaxAmount);
    			//��λʵ�ʳɱ�
    			purInWarehsEntryInfo.setUnitActualCost(notTaxPrice);
    			//ʵ�ʳɱ�
    			purInWarehsEntryInfo.setActualCost(notTaxAmount);
    			//����
    			String materialNum = dmsTradeInquireEntryInfo.getMaterialNum();
    			MaterialInfo materialInfo = dmsTradeInquireEntryInfo.getEasMaterial();
    			purInWarehsEntryInfo.setMaterial(materialInfo);
    			//����
    			purInWarehsEntryInfo.setQty(qty);
    			//������λ
    			purInWarehsEntryInfo.setUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			//����������λ
    			purInWarehsEntryInfo.setBaseUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			
    			purInWarehsEntryCol.add(purInWarehsEntryInfo);
    		}
       		String supplierNum = dmsTradeInquireEntryCol.get(0).getSupplier();
    		SupplierInfo supplierInfo = dmsTradeInquireEntryCol.get(0).getEasSupplier();
    		//��Ӧ��
    		purInWarehsBillInfo.setSupplier(supplierInfo);
    		//�ұ�
    		purInWarehsBillInfo.setCurrency(defaultCurrency);
    		//����
    		purInWarehsBillInfo.setExchangeRate(PublicUtils.BIGDECIMAL1);
    		//���ʽ
    		purInWarehsBillInfo.setPaymentType(defaultPaymentType);
    		//TODO ��λ���ܽ��
    		//purInWarehsBillInfo.setTotalLocalAmount(null);
    		//�ɹ����  -�⹺
    		purInWarehsBillInfo.setPurchaseType(PurchaseTypeEnum.PURCHASE);
    		//�Ƿ�˰
    		purInWarehsBillInfo.setIsInTax(true);
    		//�Ƿ����˰
    		purInWarehsBillInfo.setIsPriceInTax(true);
    		//�����֯
    		purInWarehsBillInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    		//������
    		purInWarehsBillInfo.setTotalQty(totalQty);
    		//�ܽ��
    		purInWarehsBillInfo.setTotalAmount(totalTaxAmount);
    		// �ܱ�׼�ɱ�
    		purInWarehsBillInfo.setTotalStandardCost(totalNotTaxAmount);
    		// ��ʵ�ʳɱ�
    		purInWarehsBillInfo.setTotalActualCost(totalNotTaxAmount);
    		//�Ƿ�����
    		if (DMSWipBillTypeEnum.PurInWarehs.equals(billTypeEnum)) {
    			//��������
    			purInWarehsBillInfo.setTransactionType(defaultTran_PurInWarehs);
    			//ҵ������
    			purInWarehsBillInfo.setBizType(defaultBizType_PurInwarehs);
    			
    		} else if (DMSWipBillTypeEnum.PurReturn.equals(billTypeEnum)) {
    			//��������
    			purInWarehsBillInfo.setTransactionType(defaultTran_PurReturn);
    			//ҵ������
    			purInWarehsBillInfo.setBizType(defaultBizType_PurReturn);
    		}
    		purInWarehsBillInfo.setMonth(Integer.valueOf(sfMonth.format(bizDate)));
    		purInWarehsBillInfo.setDay(Integer.valueOf(sfDate.format(bizDate)));
    		
    		//SCMBillBase
    		//״̬
    		purInWarehsBillInfo.setBaseStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
    		//��������
    		purInWarehsBillInfo.setBillType(default_BillType);
    		
    		purInWarehsBillInfo.setYear(Integer.valueOf(sfYear.format(bizDate)));
    		purInWarehsBillInfo.setPeriod(Integer.valueOf(sfPeriod.format(bizDate)));
    		purInWarehsBillInfo.setBizDate(bizDate);
    		
    		purInWarehsBillInfo.put("entry", purInWarehsEntryCol);
    		purInWarehsBillCol.add(purInWarehsBillInfo);
    	}
    	//����
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
    	String DEFAULT_SALEORG_ID = "zfcAAAAAABjM567U"; //�����б��������޹�˾
    	SaleOrgUnitInfo defaultSaleOrgUnit = new SaleOrgUnitInfo();
    	defaultSaleOrgUnit.put("id", DEFAULT_SALEORG_ID);
    	
    	String DEFAULT_Storage_ID = "zfcAAAAAABjM567U"; //�����б��������޹�˾
    	StorageOrgUnitInfo defaultStorageOrgUnit = new StorageOrgUnitInfo();
    	defaultStorageOrgUnit.put("id", DEFAULT_Storage_ID);
    	
    	String DEFAULT_Company_ID = "zfcAAAAAABjM567U"; //�����б��������޹�˾
    	CompanyOrgUnitInfo defaultCompanyOrgUnit = new CompanyOrgUnitInfo();
    	defaultCompanyOrgUnit.put("id", DEFAULT_Company_ID);
    	
    	String DEFAULT_Currency_ID = "dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC"; //�����
    	CurrencyInfo defaultCurrency = new CurrencyInfo();
    	defaultCurrency.put("id", DEFAULT_Currency_ID);
    	
    	String DEFAULT_PaymentType_ID = "91f078d7-fb90-4827-83e2-3538237b67a06BCA0AB5";  //����
    	PaymentTypeInfo defaultPaymentType = new PaymentTypeInfo();
    	defaultPaymentType.put("id", DEFAULT_PaymentType_ID);
    	
    	String DEFAULT_bizType_SaleIssue_ID = "d8e80652-010e-1000-e000-04c5c0a812202407435C"; //��ͨ���� 210
    	BizTypeInfo defaultBizType_SaleIssue = new BizTypeInfo();
    	defaultBizType_SaleIssue.put("id", DEFAULT_bizType_SaleIssue_ID);
    	
    	String DEFAULT_Tran_SaleIssue_ID = "DawAAAAPoAywCNyn"; //��ͨ���۳��� 010
    	TransactionTypeInfo defaultTran_SaleIssue = new TransactionTypeInfo();
    	defaultTran_SaleIssue.put("id", DEFAULT_Tran_SaleIssue_ID);
    	
    	String DEFAULT_bizType_SaleReturn_ID = "d8e80652-0110-1000-e000-04c5c0a812202407435C"; //��ͨ�����˻�  211
    	BizTypeInfo defaultBizType_SaleReturn = new BizTypeInfo();
    	defaultBizType_SaleReturn.put("id", DEFAULT_bizType_SaleReturn_ID);
    	
    	String DEFAULT_Tran_SaleReturn_ID = "DawAAAAPoA2wCNyn"; //��ͨ�����˻� 011
    	TransactionTypeInfo defaultTran_SaleReturn = new TransactionTypeInfo();
    	defaultTran_SaleReturn.put("id", DEFAULT_Tran_SaleReturn_ID);
    	
    	String Default_BillType_ID = "50957179-0105-1000-e000-015bc0a812fd463ED552"; //��������-���۳���
    	BillTypeInfo default_BillType = new BillTypeInfo();
    	default_BillType.put("id", Default_BillType_ID);
    	
    	
    	
    	
    	while (itKey.hasNext()) {
    		String key = itKey.next();
    		DMSInOutQueryEntryCollection dmsTradeInquireEntryCol = hashDMSTradeInquireEntryCol.get(key);
    		SaleIssueBillInfo saleIssueBillInfo = new SaleIssueBillInfo();
    		SaleIssueEntryCollection saleIssueEntryCol = new SaleIssueEntryCollection();
    		Date bizDate = dmsTradeInquireEntryCol.get(0).getBizDate();
    		
    		//������
    		BigDecimal totalQty = PublicUtils.BIGDECIMAL0;
    		//�ܺ�˰���
    		BigDecimal totalTaxAmount = PublicUtils.BIGDECIMAL0;
    		//�ܲ���˰���
    		BigDecimal totalNotTaxAmount = PublicUtils.BIGDECIMAL0;
    		

    		
    		for (int i = 0; i < dmsTradeInquireEntryCol.size(); i++) {
    			DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo = dmsTradeInquireEntryCol.get(i);
    		
    			WarehouseInfo warehouseInfo = dmsTradeInquireEntryInfo.getEasWarehouse();
    			com.kingdee.eas.basedata.master.cssp.CustomerInfo stdCustomerInfo = dmsTradeInquireEntryInfo.getEasCustomer();
    			
    			SaleIssueEntryInfo saleIssueEntryInfo = new SaleIssueEntryInfo();
    			//˰��
    			BigDecimal taxRate = new BigDecimal(17.00);
    			//��˰����
    			BigDecimal taxPrice = dmsTradeInquireEntryInfo.getEasTaxPrice();
    			//����˰����=��˰����/(1+˰��/100)
    			BigDecimal notTaxPrice = taxPrice.divide(PublicUtils.BIGDECIMAL1.add(taxRate.divide(PublicUtils.BIGDECIMAL100,10,BigDecimal.ROUND_HALF_UP)),10,BigDecimal.ROUND_HALF_UP);
    			//����
    			BigDecimal qty = dmsTradeInquireEntryInfo.getQty();
    			//��˰���
    			BigDecimal taxAmount = taxPrice.multiply(qty);
    			//����˰���=����˰����*����
    			BigDecimal notTaxAmount = notTaxPrice.multiply(qty);
    			//˰��=��˰���-����˰���
    			BigDecimal tax = taxAmount.subtract(notTaxAmount);
    			
    			
    			totalQty = totalQty.add(qty);
    			totalTaxAmount = totalTaxAmount.add(taxAmount);
    			totalNotTaxAmount = totalNotTaxAmount.add(notTaxAmount);
    			
    			
    			//�Ѻ�������
    			saleIssueEntryInfo.setWrittenOffQty(PublicUtils.BIGDECIMAL0);
    			//�Ѻ������
    			saleIssueEntryInfo.setWrittenOffAmount(PublicUtils.BIGDECIMAL0);
    			//δ��������
    			saleIssueEntryInfo.setUnWriteOffQty(qty);
    			//δ�������
    			saleIssueEntryInfo.setUnWriteOffAmount(taxAmount);
    			//˰��
    			saleIssueEntryInfo.setTaxRate(taxRate);
    			//˰��
    			saleIssueEntryInfo.setTax(tax);
    			//��λ��˰��
    			saleIssueEntryInfo.setLocalTax(tax);
    			//��λ�ҵ���
    			saleIssueEntryInfo.setLocalPrice(taxPrice);
    			//��λ�ҽ��
    			saleIssueEntryInfo.setLocalAmount(taxAmount);
    			//�Ѻ�����������
    			saleIssueEntryInfo.setWrittenOffBaseQty(PublicUtils.BIGDECIMAL0);
    			//δ������������
    			saleIssueEntryInfo.setUnWriteOffBaseQty(qty);
   
    			//��˰����
    			saleIssueEntryInfo.setTaxPrice(taxPrice);
    			//ʵ�ʵ���
    			saleIssueEntryInfo.setActualPrice(notTaxPrice);
    			//������֯
    			saleIssueEntryInfo.setSaleOrgUnit(defaultSaleOrgUnit);
    			//�����ɳ�������
    			saleIssueEntryInfo.setUndeliverQty(qty);
    			//�����ɷ������������
    			saleIssueEntryInfo.setUndeliverBaseQty(qty);
    			//�Է�δ�������
    			saleIssueEntryInfo.setUnInQty(qty);
    			//�Է�δ����������
    			saleIssueEntryInfo.setUnInBaseQty(qty);
    			//�ۼ��������
    			saleIssueEntryInfo.setTotalInWarehsQty(qty);
    			//�����ͻ�
    			saleIssueEntryInfo.setOrderCustomer(stdCustomerInfo);
    			//�տ�ͻ�
    			saleIssueEntryInfo.setPaymentCustomer(stdCustomerInfo);
    			//������λʵ�ʳɱ�
    			saleIssueEntryInfo.setBaseUnitActualCost(notTaxPrice);
    			//����
    			saleIssueEntryInfo.setSalePrice(notTaxPrice);
    			//��˰����
    			saleIssueEntryInfo.setPrice(taxPrice);
    			//��˰�ϼ�
    			saleIssueEntryInfo.setAmount(taxAmount);
    			//����˰���
    			saleIssueEntryInfo.setNonTaxAmount(notTaxAmount);
    			//��λ�Ҳ���˰���
    			saleIssueEntryInfo.setLocalNonTaxAmount(notTaxAmount);
    			//��λ�Һ�˰���
    			saleIssueEntryInfo.setLocalAmount(taxAmount);
    			//����
    			saleIssueEntryInfo.setBizDate(bizDate);
    			//�ۿ���
    			saleIssueEntryInfo.setDiscount(PublicUtils.BIGDECIMAL0);
    			//�ۿ۶�
    			saleIssueEntryInfo.setDiscountAmount(PublicUtils.BIGDECIMAL0);
    		 
    			
    			
    			//InvBillBaseEntry������
    			//�����֯
    			saleIssueEntryInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    			//������֯
    			saleIssueEntryInfo.setCompanyOrgUnit(defaultCompanyOrgUnit);
    			//�ֿ�
    			saleIssueEntryInfo.setWarehouse(warehouseInfo);
    			//��������
    			saleIssueEntryInfo.setBaseQty(qty);
    			//����
    			saleIssueEntryInfo.setQty(qty);
    		
    			//��λ��׼�ɱ�
    			saleIssueEntryInfo.setUnitStandardCost(notTaxPrice);
    			//��׼�ɱ�
    			saleIssueEntryInfo.setStandardCost(notTaxAmount);
    			//��λʵ�ʳɱ�
    			saleIssueEntryInfo.setUnitActualCost(notTaxPrice);
    			//ʵ�ʳɱ�
    			saleIssueEntryInfo.setActualCost(notTaxAmount);
    			//����
    			MaterialInfo materialInfo = dmsTradeInquireEntryInfo.getEasMaterial();
    			saleIssueEntryInfo.setMaterial(materialInfo);
    			//������λ
    			saleIssueEntryInfo.setUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			//����������λ
    			saleIssueEntryInfo.setBaseUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			
    			saleIssueEntryCol.add(saleIssueEntryInfo);
    		}
       		
    		saleIssueBillInfo.setCustomer(dmsTradeInquireEntryCol.get(0).getEasCustomer());
    		//�ұ�
    		saleIssueBillInfo.setCurrency(defaultCurrency);
    		//����
    		saleIssueBillInfo.setExchangeRate(PublicUtils.BIGDECIMAL1);
    		//���ʽ
    		saleIssueBillInfo.setPaymentType(defaultPaymentType);
    		//ʵ��ҵ������
    		saleIssueBillInfo.setActBizDate(bizDate);
    		//�Ƿ�˰
    		saleIssueBillInfo.setIsInTax(true);
    		//��λ���ܼ�˰�ϼ�
    		saleIssueBillInfo.setTotalLocalAmount(totalTaxAmount);
    		//�����֯
    		saleIssueBillInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    		//������
    		saleIssueBillInfo.setTotalQty(totalQty);
    		//�ܽ��
    		saleIssueBillInfo.setTotalAmount(totalTaxAmount);
    		// �ܱ�׼�ɱ�
    		saleIssueBillInfo.setTotalStandardCost(totalNotTaxAmount);
    		// ��ʵ�ʳɱ�
    		saleIssueBillInfo.setTotalActualCost(totalNotTaxAmount);
    		//�Ƿ�����
    		if (DMSWipBillTypeEnum.SaleIssue.equals(billTypeEnum)) {
    			//��������
    			saleIssueBillInfo.setTransactionType(defaultTran_SaleIssue);
    			//ҵ������
    			saleIssueBillInfo.setBizType(defaultBizType_SaleIssue);
    			
    		} else if (DMSWipBillTypeEnum.SaleReturn.equals(billTypeEnum)) {
    			//��������
    			saleIssueBillInfo.setTransactionType(defaultTran_SaleReturn);
    			//ҵ������
    			saleIssueBillInfo.setBizType(defaultBizType_SaleReturn);
    		}
    		saleIssueBillInfo.setMonth(Integer.valueOf(sfMonth.format(bizDate)));
    		saleIssueBillInfo.setDay(Integer.valueOf(sfDate.format(bizDate)));
    		
    		//SCMBillBase
    		//״̬
    		saleIssueBillInfo.setBaseStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
    		//��������
    		saleIssueBillInfo.setBillType(default_BillType);
    		
    		saleIssueBillInfo.setYear(Integer.valueOf(sfYear.format(bizDate)));
    		saleIssueBillInfo.setPeriod(Integer.valueOf(sfPeriod.format(bizDate)));
    		saleIssueBillInfo.setBizDate(bizDate);
    		
    		saleIssueBillInfo.put("entry", saleIssueEntryCol);
    		saleIssueBillCol.add(saleIssueBillInfo);
    	}
    	//����
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
    	
    	String DEFAULT_Storage_ID = "zfcAAAAAABjM567U"; //�����б��������޹�˾
    	StorageOrgUnitInfo defaultStorageOrgUnit = new StorageOrgUnitInfo();
    	defaultStorageOrgUnit.put("id", DEFAULT_Storage_ID);
    	
    	String DEFAULT_Company_ID = "zfcAAAAAABjM567U"; //�����б��������޹�˾
    	CompanyOrgUnitInfo defaultCompanyOrgUnit = new CompanyOrgUnitInfo();
    	defaultCompanyOrgUnit.put("id", DEFAULT_Company_ID);
    	
    	String DEFAULT_Currency_ID = "dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC"; //�����
    	CurrencyInfo defaultCurrency = new CurrencyInfo();
    	defaultCurrency.put("id", DEFAULT_Currency_ID);
    	
    	String DEFAULT_StoreType_ID = "181875d5-0105-1000-e000-0111c0a812fd97D461A6"; //��ͨ
    	StoreTypeInfo defaultStoreType = new StoreTypeInfo();
    	defaultStoreType.put("id", DEFAULT_StoreType_ID);
    	
    	String DEFAULT_StoreState_ID = "181875d5-0105-1000-e000-012ec0a812fd62A73FA5"; //����
    	StoreStateInfo defaultStoreState = new StoreStateInfo();
    	defaultStoreState.put("id", DEFAULT_StoreState_ID);
    	
    	String DEFAULT_bizType_OtherIssue_ID = "Nz878AEgEADgAABMwKg/GiQHQ1w="; //��ͨ���� 510
    	BizTypeInfo defaultBizType_OtherIssue = new BizTypeInfo();
    	defaultBizType_OtherIssue.put("id", DEFAULT_bizType_OtherIssue_ID);
    	
    	String DEFAULT_Tran_TransferOutWarehs_ID = "zfcAAAADYjawCNyn"; //��������(��������) 030-1
    	TransactionTypeInfo defaultTran_TransferOutWarehs = new TransactionTypeInfo();
    	defaultTran_TransferOutWarehs.put("id", DEFAULT_Tran_TransferOutWarehs_ID);
    	
    	String DEFAULT_Tran_LossOutWarehs_ID = "zfcAAAADYjewCNyn"; //��������(�̿�����) 030-1
    	TransactionTypeInfo defaultTran_LossOutWarehs = new TransactionTypeInfo();
    	defaultTran_LossOutWarehs.put("id", DEFAULT_Tran_LossOutWarehs_ID);
    	
    	String Default_BillType_ID = "50957179-0105-1000-e000-0177c0a812fd463ED552"; //��������-�������ⵥ
    	BillTypeInfo default_BillType = new BillTypeInfo();
    	default_BillType.put("id", Default_BillType_ID);
    	
    	while (itKey.hasNext()) {
    		String key = itKey.next();
    		DMSInOutQueryEntryCollection dmsTradeInquireEntryCol = hashDMSTradeInquireEntryCol.get(key);
    		OtherIssueBillInfo otherIssueBillInfo = new OtherIssueBillInfo();
    		OtherIssueBillEntryCollection otherIssueEntryCol = new OtherIssueBillEntryCollection();
    		Date bizDate = dmsTradeInquireEntryCol.get(0).getBizDate();
    		
    		//������
    		BigDecimal totalQty = PublicUtils.BIGDECIMAL0;
    		//�ܺ�˰���
    		BigDecimal totalTaxAmount = PublicUtils.BIGDECIMAL0;
    		//�ܲ���˰���
    		BigDecimal totalNotTaxAmount = PublicUtils.BIGDECIMAL0;
    		

    		
    		for (int i = 0; i < dmsTradeInquireEntryCol.size(); i++) {
    			DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo = dmsTradeInquireEntryCol.get(i);
    		
    			WarehouseInfo warehouseInfo = dmsTradeInquireEntryInfo.getEasWarehouse();
    			com.kingdee.eas.basedata.master.cssp.CustomerInfo stdCustomerInfo = dmsTradeInquireEntryInfo.getEasCustomer();
    			
    			OtherIssueBillEntryInfo otherIssueEntryInfo = new OtherIssueBillEntryInfo();
    			//˰��
    			BigDecimal taxRate = new BigDecimal(17.00);
    			//��˰����
    			BigDecimal taxPrice = dmsTradeInquireEntryInfo.getEasTaxPrice();
    			//����˰����=��˰����/(1+˰��/100)
    			BigDecimal notTaxPrice = taxPrice.divide(PublicUtils.BIGDECIMAL1.add(taxRate.divide(PublicUtils.BIGDECIMAL100,10,BigDecimal.ROUND_HALF_UP)),10,BigDecimal.ROUND_HALF_UP);
    			//����
    			BigDecimal qty = dmsTradeInquireEntryInfo.getQty();
    			//��˰���
    			BigDecimal taxAmount = taxPrice.multiply(qty);
    			//����˰���=����˰����*����
    			BigDecimal notTaxAmount = notTaxPrice.multiply(qty);
    			//˰��=��˰���-����˰���
    			BigDecimal tax = taxAmount.subtract(notTaxAmount);
    			
    			
    			totalQty = totalQty.add(qty);
    			totalTaxAmount = totalTaxAmount.add(taxAmount);
    			totalNotTaxAmount = totalNotTaxAmount.add(notTaxAmount);
    			
    			
    			//�������
    			otherIssueEntryInfo.setStoreType(defaultStoreType);
    			//���״̬
    			otherIssueEntryInfo.setStoreStatus(defaultStoreState);
    			//��������ʵ�ʳɱ�
    			otherIssueEntryInfo.setBaseUnitActualcost(notTaxPrice);
    			//����
    			otherIssueEntryInfo.setPrice(taxPrice);
    			//���
    			otherIssueEntryInfo.setAmount(taxAmount);
    			//ҵ������
    			otherIssueEntryInfo.setBizDate(bizDate);
    		 
    			
    			
    			//InvBillBaseEntry������
    			//�����֯
    			otherIssueEntryInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    			//������֯
    			otherIssueEntryInfo.setCompanyOrgUnit(defaultCompanyOrgUnit);
    			//�ֿ�
    			otherIssueEntryInfo.setWarehouse(warehouseInfo);
    			//��������
    			otherIssueEntryInfo.setBaseQty(qty);
    			//����
    			otherIssueEntryInfo.setQty(qty);
    			//��λ��׼�ɱ�
    			otherIssueEntryInfo.setUnitStandardCost(notTaxPrice);
    			//��׼�ɱ�
    			otherIssueEntryInfo.setStandardCost(notTaxAmount);
    			//��λʵ�ʳɱ�
    			otherIssueEntryInfo.setUnitActualCost(notTaxPrice);
    			//ʵ�ʳɱ�
    			otherIssueEntryInfo.setActualCost(notTaxAmount);
    			//����
    			MaterialInfo materialInfo = dmsTradeInquireEntryInfo.getEasMaterial();
    			otherIssueEntryInfo.setMaterial(materialInfo);
    			//������λ
    			otherIssueEntryInfo.setUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			//����������λ
    			otherIssueEntryInfo.setBaseUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			
    			otherIssueEntryCol.add(otherIssueEntryInfo);
    		}
       		
    		
    		//InvBillBase
    		String number =CodingRuleUtils.getNumber(ctx, otherIssueBillInfo, defaultStorageOrgUnit.getString("id"));
    		otherIssueBillInfo.setNumber(number);
    		
    		//�����֯
    		otherIssueBillInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    		//������
    		otherIssueBillInfo.setTotalQty(totalQty);
    		//�ܽ��
    		otherIssueBillInfo.setTotalAmount(totalTaxAmount);
    		// �ܱ�׼�ɱ�
    		otherIssueBillInfo.setTotalStandardCost(totalNotTaxAmount);
    		// ��ʵ�ʳɱ�
    		otherIssueBillInfo.setTotalActualCost(totalNotTaxAmount);
    		//ҵ������
    		otherIssueBillInfo.setBizType(defaultBizType_OtherIssue);
			
    		//�Ƿ�����
    		if (DMSWipBillTypeEnum.TransferOutWarehs.equals(billTypeEnum)) {
    			//��������
    			otherIssueBillInfo.setTransactionType(defaultTran_TransferOutWarehs);	
    		} else if (DMSWipBillTypeEnum.LossOutWarehs.equals(billTypeEnum)) {
    			//��������
    			otherIssueBillInfo.setTransactionType(defaultTran_LossOutWarehs);
    		}
    		otherIssueBillInfo.setMonth(Integer.valueOf(sfMonth.format(bizDate)));
    		otherIssueBillInfo.setDay(Integer.valueOf(sfDate.format(bizDate)));
    		
    		//SCMBillBase
    		//״̬
    		otherIssueBillInfo.setBaseStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
    		//��������
    		otherIssueBillInfo.setBillType(default_BillType);
    		
    		otherIssueBillInfo.setYear(Integer.valueOf(sfYear.format(bizDate)));
    		otherIssueBillInfo.setPeriod(Integer.valueOf(sfPeriod.format(bizDate)));
    		otherIssueBillInfo.setBizDate(bizDate);
    		
    		otherIssueBillInfo.put("entry", otherIssueEntryCol);
    		otherIssueBillCol.add(otherIssueBillInfo);
    		
    		
    	}
    	//����
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
    	
    	String DEFAULT_Storage_ID = "zfcAAAAAABjM567U"; //�����б��������޹�˾
    	StorageOrgUnitInfo defaultStorageOrgUnit = new StorageOrgUnitInfo();
    	defaultStorageOrgUnit.put("id", DEFAULT_Storage_ID);
    	
    	String DEFAULT_Company_ID = "zfcAAAAAABjM567U"; //�����б��������޹�˾
    	CompanyOrgUnitInfo defaultCompanyOrgUnit = new CompanyOrgUnitInfo();
    	defaultCompanyOrgUnit.put("id", DEFAULT_Company_ID);
    	
    	String DEFAULT_Currency_ID = "dfd38d11-00fd-1000-e000-1ebdc0a8100dDEB58FDC"; //�����
    	CurrencyInfo defaultCurrency = new CurrencyInfo();
    	defaultCurrency.put("id", DEFAULT_Currency_ID);
    	
    	String DEFAULT_StoreType_ID = "181875d5-0105-1000-e000-0111c0a812fd97D461A6"; //��ͨ
    	StoreTypeInfo defaultStoreType = new StoreTypeInfo();
    	defaultStoreType.put("id", DEFAULT_StoreType_ID);
    	
    	String DEFAULT_StoreState_ID = "181875d5-0105-1000-e000-012ec0a812fd62A73FA5"; //����
    	StoreStateInfo defaultStoreState = new StoreStateInfo();
    	defaultStoreState.put("id", DEFAULT_StoreState_ID);
    	
    	String DEFAULT_bizType_OtherInWarehs_ID = "N5d2igEgEADgAABywKg/GiQHQ1w="; //��ͨ��� 500
    	BizTypeInfo defaultBizType_OtherInWarehs = new BizTypeInfo();
    	defaultBizType_OtherInWarehs.put("id", DEFAULT_bizType_OtherInWarehs_ID);
    	
    	String DEFAULT_Tran_TransferInWarehs_ID = "zfcAAAADYjSwCNyn"; //�������(�������) 034-1
    	TransactionTypeInfo defaultTran_TransferInWarehs = new TransactionTypeInfo();
    	defaultTran_TransferInWarehs.put("id", DEFAULT_Tran_TransferInWarehs_ID);
    	
    	String DEFAULT_Tran_ProfitInWarehs_ID = "zfcAAAADYjWwCNyn"; //�������(��ӯ���) 034-2
    	TransactionTypeInfo defaultTran_ProfitInWarehs = new TransactionTypeInfo();
    	defaultTran_ProfitInWarehs.put("id", DEFAULT_Tran_ProfitInWarehs_ID);
    	
    	String Default_BillType_ID = "50957179-0105-1000-e000-017bc0a812fd463ED552"; //��������-������ⵥ
    	BillTypeInfo default_BillType = new BillTypeInfo();
    	default_BillType.put("id", Default_BillType_ID);
    	
    	while (itKey.hasNext()) {
    		String key = itKey.next();
    		DMSInOutQueryEntryCollection dmsTradeInquireEntryCol = hashDMSTradeInquireEntryCol.get(key);
    		OtherInWarehsBillInfo otherInWarehsBillInfo = new OtherInWarehsBillInfo();
    		OtherInWarehsBillEntryCollection otherInWarehsEntryCol = new OtherInWarehsBillEntryCollection();
    		Date bizDate = dmsTradeInquireEntryCol.get(0).getBizDate();
    		
    		//������
    		BigDecimal totalQty = PublicUtils.BIGDECIMAL0;
    		//�ܺ�˰���
    		BigDecimal totalTaxAmount = PublicUtils.BIGDECIMAL0;
    		//�ܲ���˰���
    		BigDecimal totalNotTaxAmount = PublicUtils.BIGDECIMAL0;
    		

    		
    		for (int i = 0; i < dmsTradeInquireEntryCol.size(); i++) {
    			DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo = dmsTradeInquireEntryCol.get(i);
    		
    			WarehouseInfo warehouseInfo = dmsTradeInquireEntryInfo.getEasWarehouse();
    			com.kingdee.eas.basedata.master.cssp.CustomerInfo stdCustomerInfo = dmsTradeInquireEntryInfo.getEasCustomer();
    			
    			OtherInWarehsBillEntryInfo otherInWarehsEntryInfo = new OtherInWarehsBillEntryInfo();
    			//˰��
    			BigDecimal taxRate = new BigDecimal(17.00);
    			//��˰����
    			BigDecimal taxPrice = dmsTradeInquireEntryInfo.getEasTaxPrice();
    			//����˰����=��˰����/(1+˰��/100)
    			BigDecimal notTaxPrice = taxPrice.divide(PublicUtils.BIGDECIMAL1.add(taxRate.divide(PublicUtils.BIGDECIMAL100,10,BigDecimal.ROUND_HALF_UP)),10,BigDecimal.ROUND_HALF_UP);
    			//����
    			BigDecimal qty = dmsTradeInquireEntryInfo.getQty();
    			//��˰���
    			BigDecimal taxAmount = taxPrice.multiply(qty);
    			//����˰���=����˰����*����
    			BigDecimal notTaxAmount = notTaxPrice.multiply(qty);
    			//˰��=��˰���-����˰���
    			BigDecimal tax = taxAmount.subtract(notTaxAmount);
    			
    			
    			totalQty = totalQty.add(qty);
    			totalTaxAmount = totalTaxAmount.add(taxAmount);
    			totalNotTaxAmount = totalNotTaxAmount.add(notTaxAmount);
    			
    			
    			//�������
    			otherInWarehsEntryInfo.setStoreType(defaultStoreType);
    			//���״̬
    			otherInWarehsEntryInfo.setStoreStatus(defaultStoreState);
    			//����
    			otherInWarehsEntryInfo.setPrice(taxPrice);
    			//���
    			otherInWarehsEntryInfo.setAmount(taxAmount);
    			//ҵ������
    			otherInWarehsEntryInfo.setBizDate(bizDate);
    		 
    			
    			
    			//InvBillBaseEntry������
    			//�����֯
    			otherInWarehsEntryInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    			//������֯
    			otherInWarehsEntryInfo.setCompanyOrgUnit(defaultCompanyOrgUnit);
    			//�ֿ�
    			otherInWarehsEntryInfo.setWarehouse(warehouseInfo);
    			//��������
    			otherInWarehsEntryInfo.setBaseQty(qty);
    			//����
    			otherInWarehsEntryInfo.setQty(qty);
    			//��λ��׼�ɱ�
    			otherInWarehsEntryInfo.setUnitStandardCost(notTaxPrice);
    			//��׼�ɱ�
    			otherInWarehsEntryInfo.setStandardCost(notTaxAmount);
    			//��λʵ�ʳɱ�
    			otherInWarehsEntryInfo.setUnitActualCost(notTaxPrice);
    			//ʵ�ʳɱ�
    			otherInWarehsEntryInfo.setActualCost(notTaxAmount);
    			//����
    			MaterialInfo materialInfo = dmsTradeInquireEntryInfo.getEasMaterial();
    			otherInWarehsEntryInfo.setMaterial(materialInfo);
    			//������λ
    			otherInWarehsEntryInfo.setUnit(dmsTradeInquireEntryInfo.getEasBaseUnit());
    			//����������λ
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
    		//�����֯
    		otherInWarehsBillInfo.setStorageOrgUnit(defaultStorageOrgUnit);
    		//������
    		otherInWarehsBillInfo.setTotalQty(totalQty);
    		//�ܽ��
    		otherInWarehsBillInfo.setTotalAmount(totalTaxAmount);
    		// �ܱ�׼�ɱ�
    		otherInWarehsBillInfo.setTotalStandardCost(totalNotTaxAmount);
    		// ��ʵ�ʳɱ�
    		otherInWarehsBillInfo.setTotalActualCost(totalNotTaxAmount);
    		//ҵ������
    		otherInWarehsBillInfo.setBizType(defaultBizType_OtherInWarehs);
			
    		//�Ƿ�����
    		if (DMSWipBillTypeEnum.TransferInWarehs.equals(billTypeEnum)) {
    			//��������
    			otherInWarehsBillInfo.setTransactionType(defaultTran_TransferInWarehs);	
    		} else if (DMSWipBillTypeEnum.ProfitInWarehs.equals(billTypeEnum)) {
    			//��������
    			otherInWarehsBillInfo.setTransactionType(defaultTran_ProfitInWarehs);
    		}
    		otherInWarehsBillInfo.setMonth(Integer.valueOf(sfMonth.format(bizDate)));
    		otherInWarehsBillInfo.setDay(Integer.valueOf(sfDate.format(bizDate)));
    		
    		//SCMBillBase
    		//״̬
    		otherInWarehsBillInfo.setBaseStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
    		//��������
    		otherInWarehsBillInfo.setBillType(default_BillType);
    		
    		otherInWarehsBillInfo.setYear(Integer.valueOf(sfYear.format(bizDate)));
    		otherInWarehsBillInfo.setPeriod(Integer.valueOf(sfPeriod.format(bizDate)));
    		otherInWarehsBillInfo.setBizDate(bizDate);
    		
    		otherInWarehsBillInfo.put("entry", otherInWarehsEntryCol);
    		otherInWarehsBillCol.add(otherInWarehsBillInfo);
    		
    		
    		
    	}
    	
    	//����
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
			//ͨ�ü�� ���ݺϷ���
			int seq = dmsTradeInquireEntryInfo.getSeq();
			if ("A".equals(dmsTradeInquireEntryInfo.getT())) continue;
			
			//����
			Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
			if (bizDate == null) {
				expMsg = String.format("DMS���ײ�ѯ��%d��,���ⲻ�Ϸ�,����Ϊ��.",seq);
    			addExceptionMsg(returnInfo, expMsg);
    			return;
			}
			
			//������
			
			String materialNum = dmsTradeInquireEntryInfo.getMaterialNum();
			MaterialInfo materialInfo = dmsTradeInquireEntryInfo.getEasMaterial();
    		if (materialInfo == null) {
    			expMsg = String.format("DMS���ײ�ѯ,��%d��,������[%s]��������EASϵͳ",seq,materialNum);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
			
			//[T]=Pʱ�����[��Ӧ����]����Ϊ�ա����ܵ���0
    		if ("P".equals(dmsTradeInquireEntryInfo.getT())) {
    			BigDecimal supplyQty =dmsTradeInquireEntryInfo.getSupplyQty();
    			if (supplyQty == null || supplyQty.compareTo(PublicUtils.BIGDECIMAL0) == 0) {
    				expMsg = String.format("DMS���ײ�ѯ��%d�й�Ӧ�������Ϸ�,����Ϊ�ջ����0.",seq);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
    			}
    		}
			
			//[T]=Sʱ�����[����]����Ϊ�գ����ܵ���0
    		if ("S".equals(dmsTradeInquireEntryInfo.getT())) {
    			BigDecimal qty =dmsTradeInquireEntryInfo.getQty();
    			if (qty == null || qty.compareTo(PublicUtils.BIGDECIMAL0) == 0) {
    				expMsg = String.format("DMS���ײ�ѯ��%d���������Ϸ�,����Ϊ�ջ����0.",seq);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
    			}
    		}
			
			//��Ӧ�� [T] = P
    		if ("P".equals(dmsTradeInquireEntryInfo.getT())) {
    			String supplierNum = dmsTradeInquireEntryInfo.getSupplier();
				SupplierInfo supplierInfo = dmsTradeInquireEntryInfo.getEasSupplier();
	    		if (supplierInfo == null) {
	    			expMsg = String.format("DMS���ײ�ѯ,��%d��,��Ӧ��[%s]��������EASϵͳ",seq,supplierNum);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
	    		}
    		}
			
    		//�ֿ�
    		String warehouseNum = dmsTradeInquireEntryInfo.getL();
    		WarehouseInfo warehouseInfo = dmsTradeInquireEntryInfo.getEasWarehouse();
    		if (warehouseInfo == null) {
    			expMsg = String.format("DMS���ײ�ѯ,��%d��,�ֿ�[%s]��������EASϵͳ",seq,warehouseNum);
    			addExceptionMsg(returnInfo, expMsg);
    			continue;
    		}
    		
    		
			//WIP�ţ����ɹ��⣬����Ҫ����Ƿ������ά�޹���
    		if ("S".equals(dmsTradeInquireEntryInfo.getT())) {
    			RepairWOInfo repairWoInfo =  dmsTradeInquireEntryInfo.getEasRepairWO();
    			String wip = dmsTradeInquireEntryInfo.getWip();
    			if (repairWoInfo == null) {
        			expMsg = String.format("DMS���ײ�ѯ,��%d��,WIP[%s]ά�޹�����������EASϵͳ",seq,wip);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
        		}
    			int wipLineNo = dmsTradeInquireEntryInfo.getLineSeq();
    			if (dmsTradeInquireEntryInfo.getEasCustomer() == null || dmsTradeInquireEntryInfo.getEasTaxPrice() == null) {
    				expMsg = String.format("DMS���ײ�ѯ,��%d��,WIP[%s]ά�޹���,WIP�к�[%d]��������EASϵͳ",seq,wip,wipLineNo);
        			addExceptionMsg(returnInfo, expMsg);
        			continue;
    			}
    		}


			DMSWipBillTypeEnum billTypeEnum = getWipBillType(dmsTradeInquireEntryInfo, keyWipForStocktaking);
			
			if (DMSWipBillTypeEnum.PurInWarehs.equals(billTypeEnum) || DMSWipBillTypeEnum.PurReturn.equals(billTypeEnum)) { //�ɹ�
				groupForPurInwarehs(ctx,billTypeEnum,hashTradeInquireGroup,dmsTradeInquireEntryInfo,returnInfo);
			} else if (DMSWipBillTypeEnum.ProfitInWarehs.equals(billTypeEnum) || DMSWipBillTypeEnum.TransferInWarehs.equals(billTypeEnum)) { //������
				groupForProfitInWarehs(ctx,billTypeEnum,hashTradeInquireGroup,dmsTradeInquireEntryInfo,returnInfo);
			} else if (DMSWipBillTypeEnum.LossOutWarehs.equals(billTypeEnum) || DMSWipBillTypeEnum.TransferOutWarehs.equals(billTypeEnum)) { //������
				groupForSaleIssue(ctx,billTypeEnum,hashTradeInquireGroup,dmsTradeInquireEntryInfo,returnInfo);
			} else if (DMSWipBillTypeEnum.SaleIssue.equals(billTypeEnum) || DMSWipBillTypeEnum.SaleReturn.equals(billTypeEnum)) { //����
				groupForOtherIssue(ctx,billTypeEnum,hashTradeInquireGroup,dmsTradeInquireEntryInfo,returnInfo);
			} 
			
			
		}
    }
    
    //�ɹ�������
    private void groupForPurInwarehs(Context ctx, DMSWipBillTypeEnum billTypeEnum,HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup,
    		DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo,ServerReturnInfo returnInfo) throws Exception {
	    	String expMsg = "";
	    	int seq = dmsTradeInquireEntryInfo.getSeq();
			boolean isPlus = dmsTradeInquireEntryInfo.getSupplyQty().compareTo(BigDecimal.ZERO) > 0;
			Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
			String rqn = dmsTradeInquireEntryInfo.getRqn();
			//key = [����]+[P/O Rqn]+[��Ӧ����]����
			String key = sfDate.format(bizDate) + rqn + String.valueOf(isPlus);
		
			//������ݾݺϷ���
    		//P/O Rqn
    
    		if (PublicUtils.isEmpty(rqn)) {
    			expMsg = String.format("DMS���ײ�ѯ],��%d��,P/O Rqn���Ϸ�,����Ϊ��.",seq);
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
    
    //����������
    private void groupForProfitInWarehs(Context ctx, DMSWipBillTypeEnum billTypeEnum,HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup,
    		DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo,ServerReturnInfo returnInfo) throws Exception {
	    	String expMsg = "";
	    	int seq = dmsTradeInquireEntryInfo.getSeq();
			Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
			String wip = dmsTradeInquireEntryInfo.getWip();
			//key = [����]+ [WIP��]
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
    
    //���۳������
    private void groupForSaleIssue(Context ctx, DMSWipBillTypeEnum billTypeEnum,HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup,
    		DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo,ServerReturnInfo returnInfo) throws Exception {
	    	String expMsg = "";
	    	int seq = dmsTradeInquireEntryInfo.getSeq();
			Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
			String wip = dmsTradeInquireEntryInfo.getWip();
			//key = [����]+ [WIP��]
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
    
    
    //�����������
    private void groupForOtherIssue(Context ctx, DMSWipBillTypeEnum billTypeEnum,HashMap<DMSWipBillTypeEnum, HashMap<String,DMSInOutQueryEntryCollection>> hashTradeInquireGroup,
    		DMSInOutQueryEntryInfo dmsTradeInquireEntryInfo,ServerReturnInfo returnInfo) throws Exception {
	    	String expMsg = "";
	    	int seq = dmsTradeInquireEntryInfo.getSeq();
			Date bizDate = dmsTradeInquireEntryInfo.getBizDate();
			String wip = dmsTradeInquireEntryInfo.getWip();
			//key = [����]+ [WIP��]
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
    	
    	if ("P".equals(t) && supplyQty.compareTo(BigDecimal.ZERO) > 0) { //�ɹ����
    		return DMSWipBillTypeEnum.PurInWarehs;
    	} else if ("P".equals(t) && supplyQty.compareTo(BigDecimal.ZERO) < 0) { //�ɹ��˻�
    		return DMSWipBillTypeEnum.PurReturn;
    	} else if ("S".equals(t) && "@TransTo".equals(customer)) { //�������(����)
    		return DMSWipBillTypeEnum.TransferInWarehs;
    	} else if ("S".equals(t) && (hashWipStocktaking.get(keyWip) != null || hashWipStocktaking.get(keyWip2) != null)
    			&& qty.compareTo(BigDecimal.ZERO) < 0) { //�������(��ӯ)
    		return DMSWipBillTypeEnum.ProfitInWarehs;
    	} else if ("S".equals(t) && "@TransFr".equals(customer)) { //��������(����)
    		return DMSWipBillTypeEnum.TransferOutWarehs;
    	} else if ("S".equals(t) && (hashWipStocktaking.get(keyWip) != null || hashWipStocktaking.get(keyWip2) != null)
    			&& qty.compareTo(BigDecimal.ZERO) > 0) { //��������(�̿�)
    		return DMSWipBillTypeEnum.LossOutWarehs;
    	} else if ("S".equals(t) && !"@TransTo".equals(customer) && !"@TransTo".equals(customer) &&
    			hashWipStocktaking.get(keyWip) == null && hashWipStocktaking.get(keyWip2) == null &&
    			qty.compareTo(BigDecimal.ZERO) > 0) { //��ͨ����
    		return DMSWipBillTypeEnum.SaleIssue;
    	} else if ("S".equals(t) && !"@TransTo".equals(customer) && !"@TransTo".equals(customer) &&
    			hashWipStocktaking.get(keyWip) == null && hashWipStocktaking.get(keyWip2) == null &&
    			qty.compareTo(BigDecimal.ZERO) < 0) { //�����˻�
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