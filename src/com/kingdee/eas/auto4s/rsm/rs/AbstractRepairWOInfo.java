package com.kingdee.eas.auto4s.rsm.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairWOInfo extends com.kingdee.eas.auto4s.autoframework.core.AutoBillBaseInfo implements Serializable 
{
    public AbstractRepairWOInfo()
    {
        this("id");
    }
    protected AbstractRepairWOInfo(String pkField)
    {
        super(pkField);
        put("RWOActivityEntry", new com.kingdee.eas.auto4s.rsm.rs.RepairWORWOActivityEntryCollection());
        put("RWOSparepartEntry", new com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryCollection());
        put("RWOItemSpEntry", new com.kingdee.eas.auto4s.rsm.rs.RepairWORWOItemSpEntryCollection());
        put("RepairBreakEntry", new com.kingdee.eas.auto4s.rsm.rs.RepairWORepairBreakEntryCollection());
        put("RWORepairItemEntry", new com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairItemEntryCollection());
        put("RWOAttachmentItemEntry", new com.kingdee.eas.auto4s.rsm.rs.RepairWORWOAttachmentItemEntryCollection());
        put("RWOTotalAmountEntry", new com.kingdee.eas.auto4s.rsm.rs.RepairWORWOTotalAmountEntryCollection());
        put("RWORepairPkgEntry", new com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairPkgEntryCollection());
    }
    /**
     * Object: ά�޹��� 's ά����Ŀ property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairItemEntryCollection getRWORepairItemEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairItemEntryCollection)get("RWORepairItemEntry");
    }
    /**
     * Object: ά�޹��� 's ά����� property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryCollection getRWOSparepartEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryCollection)get("RWOSparepartEntry");
    }
    /**
     * Object: ά�޹��� 's �ײ���Ϣ property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairPkgEntryCollection getRWORepairPkgEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairPkgEntryCollection)get("RWORepairPkgEntry");
    }
    /**
     * Object: ά�޹��� 's ���� property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWOActivityEntryCollection getRWOActivityEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWOActivityEntryCollection)get("RWOActivityEntry");
    }
    /**
     * Object: ά�޹��� 's ������Ŀ property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWOAttachmentItemEntryCollection getRWOAttachmentItemEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWOAttachmentItemEntryCollection)get("RWOAttachmentItemEntry");
    }
    /**
     * Object: ά�޹��� 's ������ property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWOTotalAmountEntryCollection getRWOTotalAmountEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWOTotalAmountEntryCollection)get("RWOTotalAmountEntry");
    }
    /**
     * Object: ά�޹��� 's Ʒ�� property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.BrandInfo getBrand()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.BrandInfo)get("Brand");
    }
    public void setBrand(com.kingdee.eas.auto4s.bdm.pbd.BrandInfo item)
    {
        put("Brand", item);
    }
    /**
     * Object: ά�޹��� 's ��˾ property 
     */
    public com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo getOrgUnit()
    {
        return (com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo)get("OrgUnit");
    }
    public void setOrgUnit(com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo item)
    {
        put("OrgUnit", item);
    }
    /**
     * Object:ά�޹���'s ����״̬property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum getStatus()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum.getEnum(getString("Status"));
    }
    public void setStatus(com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum item)
    {
		if (item != null) {
        setString("Status", item.getValue());
		}
    }
    /**
     * Object:ά�޹���'s ��עproperty 
     */
    public String getRemark()
    {
        return getString("Remark");
    }
    public void setRemark(String item)
    {
        setString("Remark", item);
    }
    /**
     * Object: ά�޹��� 's ���� property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo getVehicle()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo)get("Vehicle");
    }
    public void setVehicle(com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo item)
    {
        put("Vehicle", item);
    }
    /**
     * Object: ά�޹��� 's ������� property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getSA()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("SA");
    }
    public void setSA(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("SA", item);
    }
    /**
     * Object:ά�޹���'s ����ʱ��property 
     */
    public java.util.Date getComeTime()
    {
        return getDate("ComeTime");
    }
    public void setComeTime(java.util.Date item)
    {
        setDate("ComeTime", item);
    }
    /**
     * Object:ά�޹���'s Ԥ�ƽ���ʱ��property 
     */
    public java.util.Date getIntendDeliveryTime()
    {
        return getDate("IntendDeliveryTime");
    }
    public void setIntendDeliveryTime(java.util.Date item)
    {
        setDate("IntendDeliveryTime", item);
    }
    /**
     * Object:ά�޹���'s ά�޷��úϼ�property 
     */
    public java.math.BigDecimal getRepairTotalAmount()
    {
        return getBigDecimal("RepairTotalAmount");
    }
    public void setRepairTotalAmount(java.math.BigDecimal item)
    {
        setBigDecimal("RepairTotalAmount", item);
    }
    /**
     * Object:ά�޹���'s ���տ���ϼ�property 
     */
    public java.math.BigDecimal getReceiveTotalAmount()
    {
        return getBigDecimal("ReceiveTotalAmount");
    }
    public void setReceiveTotalAmount(java.math.BigDecimal item)
    {
        setBigDecimal("ReceiveTotalAmount", item);
    }
    /**
     * Object:ά�޹���'s �ѿ�Ʊ���ϼ�property 
     */
    public java.math.BigDecimal getInvoicedTotalAmount()
    {
        return getBigDecimal("InvoicedTotalAmount");
    }
    public void setInvoicedTotalAmount(java.math.BigDecimal item)
    {
        setBigDecimal("InvoicedTotalAmount", item);
    }
    /**
     * Object:ά�޹���'s �Ƿ���������̨��property 
     */
    public boolean isIsStat()
    {
        return getBoolean("IsStat");
    }
    public void setIsStat(boolean item)
    {
        setBoolean("IsStat", item);
    }
    /**
     * Object:ά�޹���'s ������ʻ���property 
     */
    public java.math.BigDecimal getMile()
    {
        return getBigDecimal("Mile");
    }
    public void setMile(java.math.BigDecimal item)
    {
        setBigDecimal("Mile", item);
    }
    /**
     * Object:ά�޹���'s ����property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.OilQuantityEnum getOilQty()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.OilQuantityEnum.getEnum(getString("OilQty"));
    }
    public void setOilQty(com.kingdee.eas.auto4s.bdm.rsm.OilQuantityEnum item)
    {
		if (item != null) {
        setString("OilQty", item.getValue());
		}
    }
    /**
     * Object:ά�޹���'s Կ�׺�property 
     */
    public String getKeyNumber()
    {
        return getString("KeyNumber");
    }
    public void setKeyNumber(String item)
    {
        setString("KeyNumber", item);
    }
    /**
     * Object:ά�޹���'s ������Ʒ�Ĵ�property 
     */
    public String getConsignation()
    {
        return getString("Consignation");
    }
    public void setConsignation(String item)
    {
        setString("Consignation", item);
    }
    /**
     * Object: ά�޹��� 's �������� property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.WarrantyTypeInfo getWarrantyType()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.WarrantyTypeInfo)get("WarrantyType");
    }
    public void setWarrantyType(com.kingdee.eas.auto4s.bdm.rsm.WarrantyTypeInfo item)
    {
        put("WarrantyType", item);
    }
    /**
     * Object: ά�޹��� 's ���չ�˾ property 
     */
    public com.kingdee.eas.auto4s.bdm.vam.InsuranceCompanyInfo getInsuranCompany()
    {
        return (com.kingdee.eas.auto4s.bdm.vam.InsuranceCompanyInfo)get("InsuranCompany");
    }
    public void setInsuranCompany(com.kingdee.eas.auto4s.bdm.vam.InsuranceCompanyInfo item)
    {
        put("InsuranCompany", item);
    }
    /**
     * Object: ά�޹��� 's ά������ property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo getRepairType()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo)get("RepairType");
    }
    public void setRepairType(com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo item)
    {
        put("RepairType", item);
    }
    /**
     * Object:ά�޹���'s �Ƿ������ɹ�property 
     */
    public boolean isIsPriorAssign()
    {
        return getBoolean("IsPriorAssign");
    }
    public void setIsPriorAssign(boolean item)
    {
        setBoolean("IsPriorAssign", item);
    }
    /**
     * Object:ά�޹���'s �Ƿ�ϴ��property 
     */
    public boolean isIsWash()
    {
        return getBoolean("IsWash");
    }
    public void setIsWash(boolean item)
    {
        setBoolean("IsWash", item);
    }
    /**
     * Object:ά�޹���'s �Ƿ��ڵ�ȴ�property 
     */
    public boolean isIsWaitForStore()
    {
        return getBoolean("IsWaitForStore");
    }
    public void setIsWaitForStore(boolean item)
    {
        setBoolean("IsWaitForStore", item);
    }
    /**
     * Object:ά�޹���'s �ͻ�Ҫ��property 
     */
    public String getCustomerRequest()
    {
        return getString("CustomerRequest");
    }
    public void setCustomerRequest(String item)
    {
        setString("CustomerRequest", item);
    }
    /**
     * Object:ά�޹���'s ���ұ��property 
     */
    public String getCompanyNumber()
    {
        return getString("CompanyNumber");
    }
    public void setCompanyNumber(String item)
    {
        setString("CompanyNumber", item);
    }
    /**
     * Object:ά�޹���'s ϴ����עproperty 
     */
    public String getWashRemark()
    {
        return getString("WashRemark");
    }
    public void setWashRemark(String item)
    {
        setString("WashRemark", item);
    }
    /**
     * Object:ά�޹���'s ϴ�����ʱ��property 
     */
    public java.util.Date getFinishTime()
    {
        return getDate("FinishTime");
    }
    public void setFinishTime(java.util.Date item)
    {
        setDate("FinishTime", item);
    }
    /**
     * Object: ά�޹��� 's ϴ��Ա property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getWasher()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("Washer");
    }
    public void setWasher(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("Washer", item);
    }
    /**
     * Object: ά�޹��� 's �ж����� property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.BreakTypeInfo getBreakType()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.BreakTypeInfo)get("BreakType");
    }
    public void setBreakType(com.kingdee.eas.auto4s.bdm.rsm.BreakTypeInfo item)
    {
        put("BreakType", item);
    }
    /**
     * Object:ά�޹���'s ����ʱ�ĵ���״̬property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum getStatusWhenCanceled()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum.getEnum(getString("StatusWhenCanceled"));
    }
    public void setStatusWhenCanceled(com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum item)
    {
		if (item != null) {
        setString("StatusWhenCanceled", item.getValue());
		}
    }
    /**
     * Object:ά�޹���'s �´α�������property 
     */
    public java.util.Date getNextMaintainDate()
    {
        return getDate("NextMaintainDate");
    }
    public void setNextMaintainDate(java.util.Date item)
    {
        setDate("NextMaintainDate", item);
    }
    /**
     * Object:ά�޹���'s �´α��������property 
     */
    public String getNextMaintainmiles()
    {
        return getString("nextMaintainmiles");
    }
    public void setNextMaintainmiles(String item)
    {
        setString("nextMaintainmiles", item);
    }
    /**
     * Object: ά�޹��� 's Դ������ property 
     */
    public com.kingdee.eas.basedata.scm.common.BillTypeInfo getSourceBillType()
    {
        return (com.kingdee.eas.basedata.scm.common.BillTypeInfo)get("SourceBillType");
    }
    public void setSourceBillType(com.kingdee.eas.basedata.scm.common.BillTypeInfo item)
    {
        put("SourceBillType", item);
    }
    /**
     * Object:ά�޹���'s Դ����¼IDproperty 
     */
    public String getSourceBillEntryID()
    {
        return getString("SourceBillEntryID");
    }
    public void setSourceBillEntryID(String item)
    {
        setString("SourceBillEntryID", item);
    }
    /**
     * Object:ά�޹���'s Դ������property 
     */
    public String getSourceBillNumber()
    {
        return getString("SourceBillNumber");
    }
    public void setSourceBillNumber(String item)
    {
        setString("SourceBillNumber", item);
    }
    /**
     * Object:ά�޹���'s Դ���к�property 
     */
    public int getSourceBillEntrySeq()
    {
        return getInt("SourceBillEntrySeq");
    }
    public void setSourceBillEntrySeq(int item)
    {
        setInt("SourceBillEntrySeq", item);
    }
    /**
     * Object: ά�޹��� 's ���� property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo getCustomer()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo)get("Customer");
    }
    public void setCustomer(com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo item)
    {
        put("Customer", item);
    }
    /**
     * Object:ά�޹���'s ������property 
     */
    public String getRepairSender()
    {
        return getString("RepairSender");
    }
    public void setRepairSender(String item)
    {
        setString("RepairSender", item);
    }
    /**
     * Object:ά�޹���'s �����˵绰property 
     */
    public String getTel()
    {
        return getString("Tel");
    }
    public void setTel(String item)
    {
        setString("Tel", item);
    }
    /**
     * Object: ά�޹��� 's ά���ж� property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORepairBreakEntryCollection getRepairBreakEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORepairBreakEntryCollection)get("RepairBreakEntry");
    }
    /**
     * Object: ά�޹��� 's �ͻ��ۿ۷��� property 
     */
    public com.kingdee.eas.basedata.master.cssp.CSSPGroupInfo getCustomerDiscountClassify()
    {
        return (com.kingdee.eas.basedata.master.cssp.CSSPGroupInfo)get("CustomerDiscountClassify");
    }
    public void setCustomerDiscountClassify(com.kingdee.eas.basedata.master.cssp.CSSPGroupInfo item)
    {
        put("CustomerDiscountClassify", item);
    }
    /**
     * Object:ά�޹���'s ԭ������property 
     */
    public String getOldID()
    {
        return getString("OldID");
    }
    public void setOldID(String item)
    {
        setString("OldID", item);
    }
    /**
     * Object:ά�޹���'s ά��ԤԼ����property 
     */
    public String getRepairBookingID()
    {
        return getString("RepairBookingID");
    }
    public void setRepairBookingID(String item)
    {
        setString("RepairBookingID", item);
    }
    /**
     * Object:ά�޹���'s ��������property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.FReturnRepairEnum getReturnRepair()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.FReturnRepairEnum.getEnum(getString("ReturnRepair"));
    }
    public void setReturnRepair(com.kingdee.eas.auto4s.bdm.rsm.FReturnRepairEnum item)
    {
		if (item != null) {
        setString("ReturnRepair", item.getValue());
		}
    }
    /**
     * Object:ά�޹���'s ������property 
     */
    public boolean isIsClaim()
    {
        return getBoolean("IsClaim");
    }
    public void setIsClaim(boolean item)
    {
        setBoolean("IsClaim", item);
    }
    /**
     * Object:ά�޹���'s ������property 
     */
    public boolean isIsWarranty()
    {
        return getBoolean("IsWarranty");
    }
    public void setIsWarranty(boolean item)
    {
        setBoolean("IsWarranty", item);
    }
    /**
     * Object:ά�޹���'s ʵ�ʽ���ʱ��property 
     */
    public java.util.Date getFactDeliveryTime()
    {
        return getDate("FactDeliveryTime");
    }
    public void setFactDeliveryTime(java.util.Date item)
    {
        setDate("FactDeliveryTime", item);
    }
    /**
     * Object:ά�޹���'s ԭ��������property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.FReturnRepairEnum getOldReturnRepair()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.FReturnRepairEnum.getEnum(getString("OldReturnRepair"));
    }
    public void setOldReturnRepair(com.kingdee.eas.auto4s.bdm.rsm.FReturnRepairEnum item)
    {
		if (item != null) {
        setString("OldReturnRepair", item.getValue());
		}
    }
    /**
     * Object:ά�޹���'s �ϴν���ʱ��property 
     */
    public java.util.Date getRecentlyComeTime()
    {
        return getDate("RecentlyComeTime");
    }
    public void setRecentlyComeTime(java.util.Date item)
    {
        setDate("RecentlyComeTime", item);
    }
    /**
     * Object:ά�޹���'s �汾��property 
     */
    public int getVersion()
    {
        return getInt("Version");
    }
    public void setVersion(int item)
    {
        setInt("Version", item);
    }
    /**
     * Object: ά�޹��� 's ��Ա�� property 
     */
    public com.kingdee.eas.auto4s.vip.mb.CardInfo getCard()
    {
        return (com.kingdee.eas.auto4s.vip.mb.CardInfo)get("card");
    }
    public void setCard(com.kingdee.eas.auto4s.vip.mb.CardInfo item)
    {
        put("card", item);
    }
    /**
     * Object:ά�޹���'s �ϴν������property 
     */
    public java.math.BigDecimal getRecentlyComeMile()
    {
        return getBigDecimal("RecentlyComeMile");
    }
    public void setRecentlyComeMile(java.math.BigDecimal item)
    {
        setBigDecimal("RecentlyComeMile", item);
    }
    /**
     * Object:ά�޹���'s �´α����޸�property 
     */
    public boolean isIsNextWarrEdit()
    {
        return getBoolean("isNextWarrEdit");
    }
    public void setIsNextWarrEdit(boolean item)
    {
        setBoolean("isNextWarrEdit", item);
    }
    /**
     * Object:ά�޹���'s ���ɹ�ʱ�ɱ�property 
     */
    public boolean isIsWorkCost()
    {
        return getBoolean("IsWorkCost");
    }
    public void setIsWorkCost(boolean item)
    {
        setBoolean("IsWorkCost", item);
    }
    /**
     * Object: ά�޹��� 's ά�������˾ property 
     */
    public com.kingdee.eas.basedata.master.cssp.SupplierInfo getSupplier()
    {
        return (com.kingdee.eas.basedata.master.cssp.SupplierInfo)get("Supplier");
    }
    public void setSupplier(com.kingdee.eas.basedata.master.cssp.SupplierInfo item)
    {
        put("Supplier", item);
    }
    /**
     * Object: ά�޹��� 's ά�޼��Ź�˾ property 
     */
    public com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo getGroupOrgunit()
    {
        return (com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo)get("GroupOrgunit");
    }
    public void setGroupOrgunit(com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo item)
    {
        put("GroupOrgunit", item);
    }
    /**
     * Object:ά�޹���'s ά�޷�ʽproperty 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum getRepairWay()
    {
        return com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum.getEnum(getString("RepairWay"));
    }
    public void setRepairWay(com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum item)
    {
		if (item != null) {
        setString("RepairWay", item.getValue());
		}
    }
    /**
     * Object:ά�޹���'s �������ڲ�Ӧ��property 
     */
    public boolean isIsReceive()
    {
        return getBoolean("IsReceive");
    }
    public void setIsReceive(boolean item)
    {
        setBoolean("IsReceive", item);
    }
    /**
     * Object:ά�޹���'s �������ڲ�Ӧ��property 
     */
    public boolean isIsPay()
    {
        return getBoolean("IsPay");
    }
    public void setIsPay(boolean item)
    {
        setBoolean("IsPay", item);
    }
    /**
     * Object: ά�޹��� 's �˻����� property 
     */
    public com.kingdee.eas.auto4s.bdm.vip.AccountCFGInfo getAccountCFG()
    {
        return (com.kingdee.eas.auto4s.bdm.vip.AccountCFGInfo)get("accountCFG");
    }
    public void setAccountCFG(com.kingdee.eas.auto4s.bdm.vip.AccountCFGInfo item)
    {
        put("accountCFG", item);
    }
    /**
     * Object: ά�޹��� 's ��Ŀ�����ϸ property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWOItemSpEntryCollection getRWOItemSpEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWOItemSpEntryCollection)get("RWOItemSpEntry");
    }
    /**
     * Object: ά�޹��� 's ���� property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getDept()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("dept");
    }
    public void setDept(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("dept", item);
    }
    /**
     * Object:ά�޹���'s �ͻ���Ϣproperty 
     */
    public String getCustomInfo()
    {
        return getString("customInfo");
    }
    public void setCustomInfo(String item)
    {
        setString("customInfo", item);
    }
    /**
     * Object:ά�޹���'s ��������property 
     */
    public String getSaleType()
    {
        return getString("saleType");
    }
    public void setSaleType(String item)
    {
        setString("saleType", item);
    }
    /**
     * Object: ά�޹��� 's �˻����� property 
     */
    public com.kingdee.eas.ga.rs.CustomerAccountInfo getCustomerAccount()
    {
        return (com.kingdee.eas.ga.rs.CustomerAccountInfo)get("CustomerAccount");
    }
    public void setCustomerAccount(com.kingdee.eas.ga.rs.CustomerAccountInfo item)
    {
        put("CustomerAccount", item);
    }
    /**
     * Object:ά�޹���'s �˻�����property 
     */
    public String getCustomerAccountName()
    {
        return getString("customerAccountName");
    }
    public void setCustomerAccountName(String item)
    {
        setString("customerAccountName", item);
    }
    /**
     * Object:ά�޹���'s ����property 
     */
    public String getGaDept()
    {
        return getString("gaDept");
    }
    public void setGaDept(String item)
    {
        setString("gaDept", item);
    }
    /**
     * Object: ά�޹��� 's ҵ������ property 
     */
    public com.kingdee.eas.ga.rs.RepairWOBizTypeInfo getRepairBizType()
    {
        return (com.kingdee.eas.ga.rs.RepairWOBizTypeInfo)get("repairBizType");
    }
    public void setRepairBizType(com.kingdee.eas.ga.rs.RepairWOBizTypeInfo item)
    {
        put("repairBizType", item);
    }
    /**
     * Object:ά�޹���'s ����״̬property 
     */
    public com.kingdee.eas.ga.rs.RepairWOStatusEnum getGaBillStatus()
    {
        return com.kingdee.eas.ga.rs.RepairWOStatusEnum.getEnum(getString("gaBillStatus"));
    }
    public void setGaBillStatus(com.kingdee.eas.ga.rs.RepairWOStatusEnum item)
    {
		if (item != null) {
        setString("gaBillStatus", item.getValue());
		}
    }
    /**
     * Object:ά�޹���'s �׵�����property 
     */
    public java.util.Date getFirstBookInDate()
    {
        return getDate("firstBookInDate");
    }
    public void setFirstBookInDate(java.util.Date item)
    {
        setDate("firstBookInDate", item);
    }
    /**
     * Object:ά�޹���'s ҵ��Ա(��)property 
     */
    public String getSaler()
    {
        return getString("Saler");
    }
    public void setSaler(String item)
    {
        setString("Saler", item);
    }
    /**
     * Object:ά�޹���'s �Ƿ��ӡ���㵥property 
     */
    public boolean isIsPrintedSettle()
    {
        return getBoolean("isPrintedSettle");
    }
    public void setIsPrintedSettle(boolean item)
    {
        setBoolean("isPrintedSettle", item);
    }
    /**
     * Object:ά�޹���'s ��עproperty 
     */
    public String getWipRemark()
    {
        return getString("wipRemark");
    }
    public void setWipRemark(String item)
    {
        setString("wipRemark", item);
    }
    /**
     * Object: ά�޹��� 's ҵ��Ա property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getBizPerson()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("bizPerson");
    }
    public void setBizPerson(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("bizPerson", item);
    }
    /**
     * Object: ά�޹��� 's ҵ���� property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getBizDept()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("bizDept");
    }
    public void setBizDept(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("bizDept", item);
    }
    /**
     * Object:ά�޹���'s DMSWIPproperty 
     */
    public String getDmsWip()
    {
        return getString("dmsWip");
    }
    public void setDmsWip(String item)
    {
        setString("dmsWip", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("FDBE5ECA");
    }
}