package com.kingdee.eas.ga.util;

import java.io.Serializable;
import java.util.HashMap;

import com.kingdee.bos.Context;
import com.kingdee.eas.auto4s.bdm.pbd.BrandInfo;
import com.kingdee.eas.auto4s.bdm.pbd.IVehicle;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleFactory;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.eas.auto4s.bdm.rsm.IPaymentClassify;
import com.kingdee.eas.auto4s.bdm.rsm.IRepairClassify;
import com.kingdee.eas.auto4s.bdm.rsm.IRepairItem;
import com.kingdee.eas.auto4s.bdm.rsm.IRepairType;
import com.kingdee.eas.auto4s.bdm.rsm.IWarrantyType;
import com.kingdee.eas.auto4s.bdm.rsm.PaymentClassifyFactory;
import com.kingdee.eas.auto4s.bdm.rsm.PaymentClassifyInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyFactory;
import com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairItemFactory;
import com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairTypeFactory;
import com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo;
import com.kingdee.eas.auto4s.bdm.rsm.WarrantyTypeFactory;
import com.kingdee.eas.auto4s.bdm.rsm.WarrantyTypeInfo;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.basedata.scm.common.ITransactionType;
import com.kingdee.eas.basedata.scm.common.TransactionTypeFactory;
import com.kingdee.eas.basedata.scm.common.TransactionTypeInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.ga.rs.CustomerAccountFactory;
import com.kingdee.eas.ga.rs.CustomerAccountInfo;
import com.kingdee.eas.ga.rs.ICustomerAccount;
import com.kingdee.eas.ga.rs.IRepairWOBizType;
import com.kingdee.eas.ga.rs.RepairWOBizTypeFactory;
import com.kingdee.eas.ga.rs.RepairWOBizTypeInfo;
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
	 * ��ȡƷ��
	 * @param ctx
	 * @param brandName
	 * @return
	 * @throws Exception 
	 */
	public static BrandInfo getBrandByName(Context ctx, String brandName) throws Exception {
		//Ʒ�� 
		if (hashBrand.isEmpty()) getBrandCol(ctx);
		BrandInfo defaultBrand = hashBrand.get(brandName);
		if (defaultBrand != null) {
			return defaultBrand;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("����Ʒ��[%s]����ȷ!",brandName)));
		}
	}
	/**
	 * ��Ʒ�ƻ�ȡĬ�ϡ��������͡�
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	public static WarrantyTypeInfo getDefaultWarrantType(Context ctx,BrandInfo brandInfo) throws Exception {
		
		//Ʒ�� 
		if (hashBrand.isEmpty()) getBrandCol(ctx);
		BrandInfo defaultBrand_BMW = hashBrand.get("BMW"); //BMW 001
		BrandInfo defaultBrand_MINI = hashBrand.get("MINI"); //MINI 002
    	
		//��������
    	IWarrantyType warrantyType = null;
    	if (ctx == null) warrantyType = WarrantyTypeFactory.getRemoteInstance();
    	else  warrantyType = WarrantyTypeFactory.getLocalInstance(ctx); 
    	
    	
    	//DMS�������ͣ������б�-���� XMZB-DMS-01
    	WarrantyTypeInfo defaultWarrantyType_BWM = warrantyType.getWarrantyTypeInfo(String.format("where number='%s'","XMZB-DMS-01"));
    	//DMS�������ͣ������б�-MINI�� XMZB-DMS-02
    	WarrantyTypeInfo defaultWarrantyType_MINI = warrantyType.getWarrantyTypeInfo(String.format("where number='%s'","XMZB-DMS-02"));
    
		if (brandInfo == null) 
			throw new EASBizException(new NumericExceptionSubItem("","����Ʒ�Ʋ���Ϊ��"));
		if (PublicUtils.equals(brandInfo, defaultBrand_BMW)) {
			return defaultWarrantyType_BWM;
		} else if (PublicUtils.equals(brandInfo, defaultBrand_MINI)) {
			return defaultWarrantyType_MINI;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("����Ʒ��[%s]����ȷ!",brandInfo.getName())));
		}

	}
	
	
	/**
	 * ��Ʒ�ƻ�ȡĬ�ϡ�ά�����͡�
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	public static RepairTypeInfo getDefaultRepairType(Context ctx,BrandInfo brandInfo) throws Exception {
		
		//Ʒ�� 
		if (hashBrand.isEmpty()) getBrandCol(ctx);
		BrandInfo defaultBrand_BMW = hashBrand.get("BMW"); //BMW 001
		BrandInfo defaultBrand_MINI = hashBrand.get("MINI"); //MINI 002
    	
    	//ά������
		IRepairType repairType = null;
		if (ctx == null) repairType = RepairTypeFactory.getRemoteInstance();
		else repairType = RepairTypeFactory.getLocalInstance(ctx);
		
		//DMSά�����ͣ������б�-���� XMZB-DMS-01
		RepairTypeInfo defaultRepairType_BMW = repairType.getRepairTypeInfo(String.format("where number='%s'", "XMZB-DMS-01"));
		//DMSά�����ͣ������б�-MINI��XMZB-DMS-02
		RepairTypeInfo defaultRepairType_MINI = repairType.getRepairTypeInfo(String.format("where number='%s'", "XMZB-DMS-02"));
    	
		if (brandInfo == null) 
			throw new EASBizException(new NumericExceptionSubItem("","����Ʒ�Ʋ���Ϊ��"));
		if (PublicUtils.equals(brandInfo, defaultBrand_BMW)) {
			return defaultRepairType_BMW;
		} else if (PublicUtils.equals(brandInfo, defaultBrand_MINI)) {
			return defaultRepairType_MINI;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("����Ʒ��[%s]����ȷ!",brandInfo.getName())));
		}
	}
	
	
	/**
	 * ��Ʒ�ƻ�ȡĬ�ϡ�ά�����ࡱ
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	public static RepairClassifyInfo getDefaultRepairClassify(Context ctx,BrandInfo brandInfo) throws Exception {

		//Ʒ�� 
		if (hashBrand.isEmpty()) getBrandCol(ctx);
		BrandInfo defaultBrand_BMW = hashBrand.get("BMW"); //BMW 001
		BrandInfo defaultBrand_MINI = hashBrand.get("MINI"); //MINI 002
    	
    	//ά������
		IRepairClassify repairClassify = null;
		if (ctx == null) repairClassify = RepairClassifyFactory.getRemoteInstance();
		else repairClassify = RepairClassifyFactory.getLocalInstance(ctx);
		
		//DMSά�����ͣ������б�-���� XMZB-DMS-01
		RepairClassifyInfo defaultRepairClassify_BMW = repairClassify.getRepairClassifyInfo(String.format("where number='%s'", "XMZB-DMS-01"));
		//DMSά�����ͣ������б�-MINI��XMZB-DMS-02
		RepairClassifyInfo defaultRepairClassify_MINI = repairClassify.getRepairClassifyInfo(String.format("where number='%s'", "XMZB-DMS-02"));
    
    	
		if (brandInfo == null) 
			throw new EASBizException(new NumericExceptionSubItem("","����Ʒ�Ʋ���Ϊ��"));
		if (PublicUtils.equals(brandInfo, defaultBrand_BMW)) {
			return defaultRepairClassify_BMW;
		} else if (PublicUtils.equals(brandInfo, defaultBrand_MINI)) {
			return defaultRepairClassify_MINI;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("����Ʒ��[%s]����ȷ!",brandInfo.getName())));
		}
	}
	
	/**
	 *  ��Ʒ�ƻ�ȡĬ�ϡ�ά����Ŀ-TXT��
	 * @param ctx
	 * @param brandInfo
	 * @return
	 * @throws Exception
	 */
	public static RepairItemInfo getDefaultRepairItemForTXT(Context ctx,BrandInfo brandInfo) throws Exception {

		//Ʒ�� 
		if (hashBrand.isEmpty()) getBrandCol(ctx);
		BrandInfo defaultBrand_BMW = hashBrand.get("BMW"); //BMW 001
		BrandInfo defaultBrand_MINI = hashBrand.get("MINI"); //MINI 002
		//ά����Ŀ
		IRepairItem repairItem = null;
		if (ctx == null) repairItem = RepairItemFactory.getRemoteInstance();
		else repairItem = RepairItemFactory.getLocalInstance(ctx);
		
		//DMSά����ĿTXT�������б�-���� TXT-1
		RepairItemInfo defaultRepairItemTXT_BMW = repairItem.getRepairItemInfo(String.format("where number='%s'", "TXT-1"));
		//DMSά����ĿTXT�������б�-MINI��TXT-2
		RepairItemInfo defaultRepairItemTXT_MINI = repairItem.getRepairItemInfo(String.format("where number='%s'", "TXT-2"));
    
    	
		if (brandInfo == null) 
			throw new EASBizException(new NumericExceptionSubItem("","����Ʒ�Ʋ���Ϊ��"));
		if (PublicUtils.equals(brandInfo, defaultBrand_BMW)) {
			return defaultRepairItemTXT_BMW;
		} else if (PublicUtils.equals(brandInfo, defaultBrand_MINI)) {
			return defaultRepairItemTXT_MINI;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("����Ʒ��[%s]����ȷ!",brandInfo.getName())));
		}
	
	}
	
	/**
	 *  ��Ʒ�ƻ�ȡĬ�ϡ�ά����Ŀ-����ȯ��
	 * @param ctx
	 * @param brandInfo
	 * @return
	 * @throws Exception
	 */
	public static RepairItemInfo getDefaultRepairItemForDJQ(Context ctx,BrandInfo brandInfo) throws Exception {

		//Ʒ�� 
		if (hashBrand.isEmpty()) getBrandCol(ctx);
		BrandInfo defaultBrand_BMW = hashBrand.get("BMW"); //BMW 001
		BrandInfo defaultBrand_MINI = hashBrand.get("MINI"); //MINI 002
		//ά����Ŀ
		IRepairItem repairItem = null;
		if (ctx == null) repairItem = RepairItemFactory.getRemoteInstance();
		else repairItem = RepairItemFactory.getLocalInstance(ctx);
		
		//DMSά����Ŀ-����ȯ�������б�-����DJQ-1
		RepairItemInfo defaultRepairItemDJQ_BMW = repairItem.getRepairItemInfo(String.format("where number='%s'", "DJQ-1"));
		//DMSά����Ŀ-����ȯ�������б�-MINI��DJQ-2
		RepairItemInfo defaultRepairItemDJQ_MINI = repairItem.getRepairItemInfo(String.format("where number='%s'", "DJQ-2"));
    
    	
		if (brandInfo == null) 
			throw new EASBizException(new NumericExceptionSubItem("","����Ʒ�Ʋ���Ϊ��"));
		if (PublicUtils.equals(brandInfo, defaultBrand_BMW)) {
			return defaultRepairItemDJQ_BMW;
		} else if (PublicUtils.equals(brandInfo, defaultBrand_MINI)) {
			return defaultRepairItemDJQ_MINI;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("����Ʒ��[%s]����ȷ!",brandInfo.getName())));
		}
	
	}
	
	/**
	 * Ĭ�ϳ���
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static VehicleInfo getDefualtVehicleInfo(Context ctx) throws Exception {
		IVehicle vehicle = null;
		if (ctx == null) vehicle = VehicleFactory.getRemoteInstance();
		else  vehicle = VehicleFactory.getLocalInstance(ctx);
		VehicleInfo defaultVehicleInfo = vehicle.getVehicleInfo("where plateNum='00001' ");
		return defaultVehicleInfo;
	}
	
	/**
	 * Ĭ�Ͽͻ��˺�	
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static CustomerAccountInfo getDefaultCustomerAccountInfo(Context ctx) throws Exception  {
		ICustomerAccount customerAccount = null; 
		if (ctx == null) customerAccount = CustomerAccountFactory.getRemoteInstance();
		else customerAccount = CustomerAccountFactory.getLocalInstance(ctx);
		CustomerAccountInfo defaultCustomerAccountInfo = customerAccount.getCustomerAccountInfo("where number='C0000001'");
		return defaultCustomerAccountInfo;
	}
	/**
	 * Ĭ�� ά����Ŀ
	 * @param ctx
	 * @param brandInfo
	 * @return
	 * @throws Exception
	 */
	public static RepairItemInfo getDefaultRepairItemInfo(Context ctx, BrandInfo brandInfo) throws Exception {
		//Ʒ�� 
		if (hashBrand.isEmpty()) getBrandCol(ctx);
		BrandInfo defaultBrand_BMW = hashBrand.get("BMW"); //BMW 001
		BrandInfo defaultBrand_MINI = hashBrand.get("MINI"); //MINI 002
    	
    	//ά������
		IRepairItem repairItem = null;
		if (ctx == null) repairItem = RepairItemFactory.getRemoteInstance();
		else repairItem = RepairItemFactory.getLocalInstance(ctx);
		
		//DMSά�����ͣ������б�-���� XMZB-DMS-01
		RepairItemInfo defaultRepairItem_BMW = repairItem.getRepairItemInfo(String.format("where number='%s'", "XMZB-DMS-01"));
		//DMSά�����ͣ������б�-MINI��XMZB-DMS-02
		RepairItemInfo defaultRepairItem_MINI = repairItem.getRepairItemInfo(String.format("where number='%s'", "XMZB-DMS-02"));
    
    	
		if (brandInfo == null) 
			throw new EASBizException(new NumericExceptionSubItem("","����Ʒ�Ʋ���Ϊ��"));
		if (PublicUtils.equals(brandInfo, defaultBrand_BMW)) {
			return defaultRepairItem_BMW;
		} else if (PublicUtils.equals(brandInfo, defaultBrand_MINI)) {
			return defaultRepairItem_MINI;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("����Ʒ��[%s]����ȷ!",brandInfo.getName())));
		}
	}
	/**
	 * Ĭ�� �������
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static PaymentClassifyInfo getDefaultPaymentClassifyInfo(Context ctx) throws Exception {
		IPaymentClassify paymentClassify = null;
		if (ctx == null) paymentClassify = PaymentClassifyFactory.getRemoteInstance();
		else paymentClassify = PaymentClassifyFactory.getLocalInstance(ctx);
		PaymentClassifyInfo defaultPaymentClassifyInfo = paymentClassify.getPaymentClassifyInfo(String.format("where number='%s'","DMS"));
		return defaultPaymentClassifyInfo;
	}
	/**
	 * Ĭ�� ��֯ID �����б��������޹�˾ ����1001���ʵ�����ҵ����֯
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static String getDefaultOrgId(Context ctx) throws Exception {
		String sql = String.format("select fid from t_org_baseunit where fnumber='%s'", "1001");
		IRowSet rs = DBUtils.executeQuery(ctx, sql);
		if (rs != null && rs.next())
			return rs.getString("fid");
		return null;
	}
	
	/**
	 * Ĭ�� ������Ա
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static PersonInfo getDefaultSAPerson(Context ctx) throws Exception {
		String sql = "select FID,FName_L2,FNumber from T_BD_Person where FNumber='DMS'";
		IRowSet rs = DBUtils.executeQuery(ctx, sql);
		if (rs != null && rs.next()) {
			PersonInfo defaultPersonInfo = new PersonInfo();
			defaultPersonInfo.put("id", rs.getString("FID"));
			defaultPersonInfo.put("name", rs.getString("FName_l2"));
			defaultPersonInfo.put("number", rs.getString("FNumber"));
			return defaultPersonInfo;
		} else {
			throw new EASBizException(new NumericExceptionSubItem("","�����ڱ���ΪDMS��ְԱ��������ӡ�"));
		}
	}
	/**
	 * Ĭ�� �������(�������)��������
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static TransactionTypeInfo getDefaultTranForTransferInWarehs(Context ctx) throws Exception {
		ITransactionType transactionType = null;
		if (ctx ==null) transactionType = TransactionTypeFactory.getRemoteInstance();
		else transactionType = TransactionTypeFactory.getLocalInstance(ctx);
		TransactionTypeInfo defaultTransactionInfo  = transactionType.getTransactionTypeInfo("where number='034-1'");
		return defaultTransactionInfo;
	}
	
	/**
	 * Ĭ�� �������(��ӯ���)��������
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static TransactionTypeInfo getDefaultTranForProfitInWarehs(Context ctx) throws Exception {
		ITransactionType transactionType = null;
		if (ctx ==null) transactionType = TransactionTypeFactory.getRemoteInstance();
		else transactionType = TransactionTypeFactory.getLocalInstance(ctx);
		TransactionTypeInfo defaultTransactionInfo  = transactionType.getTransactionTypeInfo("where number='034-2'");
		return defaultTransactionInfo;
	}
	
	
	/**
	 * Ĭ�� ��������(��������)��������
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static TransactionTypeInfo getDefaultTranForTransferOutWarehs(Context ctx) throws Exception {
		ITransactionType transactionType = null;
		if (ctx ==null) transactionType = TransactionTypeFactory.getRemoteInstance();
		else transactionType = TransactionTypeFactory.getLocalInstance(ctx);
		TransactionTypeInfo defaultTransactionInfo  = transactionType.getTransactionTypeInfo("where number='030-1'");
		return defaultTransactionInfo;
	}
	
	/**
	 * Ĭ�� ��������(�̿�����)��������
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static TransactionTypeInfo getDefaultTranForLossOutWarehs(Context ctx) throws Exception {
		ITransactionType transactionType = null;
		if (ctx ==null) transactionType = TransactionTypeFactory.getRemoteInstance();
		else transactionType = TransactionTypeFactory.getLocalInstance(ctx);
		TransactionTypeInfo defaultTransactionInfo  = transactionType.getTransactionTypeInfo("where number='030-2'");
		return defaultTransactionInfo;
	}
	
	/**
	 * Ĭ�� ҵ������
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	public static RepairWOBizTypeInfo getDefaultBizType(Context ctx) throws Exception {
		IRepairWOBizType repairWOBizType = null;
		if (repairWOBizType == null) repairWOBizType = RepairWOBizTypeFactory.getRemoteInstance();
		else repairWOBizType = RepairWOBizTypeFactory.getLocalInstance(ctx);
		RepairWOBizTypeInfo defaultBizType = repairWOBizType.getRepairWOBizTypeInfo("where number='0001'");
		return defaultBizType;
	}
}
