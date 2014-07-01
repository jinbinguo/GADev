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
     * Object: 维修工单 's 维修项目 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairItemEntryCollection getRWORepairItemEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairItemEntryCollection)get("RWORepairItemEntry");
    }
    /**
     * Object: 维修工单 's 维修配件 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryCollection getRWOSparepartEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryCollection)get("RWOSparepartEntry");
    }
    /**
     * Object: 维修工单 's 套餐信息 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairPkgEntryCollection getRWORepairPkgEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairPkgEntryCollection)get("RWORepairPkgEntry");
    }
    /**
     * Object: 维修工单 's 服务活动 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWOActivityEntryCollection getRWOActivityEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWOActivityEntryCollection)get("RWOActivityEntry");
    }
    /**
     * Object: 维修工单 's 附加项目 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWOAttachmentItemEntryCollection getRWOAttachmentItemEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWOAttachmentItemEntryCollection)get("RWOAttachmentItemEntry");
    }
    /**
     * Object: 维修工单 's 金额汇总 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWOTotalAmountEntryCollection getRWOTotalAmountEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWOTotalAmountEntryCollection)get("RWOTotalAmountEntry");
    }
    /**
     * Object: 维修工单 's 品牌 property 
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
     * Object: 维修工单 's 公司 property 
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
     * Object:维修工单's 单据状态property 
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
     * Object:维修工单's 备注property 
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
     * Object: 维修工单 's 车辆 property 
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
     * Object: 维修工单 's 服务顾问 property 
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
     * Object:维修工单's 进厂时间property 
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
     * Object:维修工单's 预计交车时间property 
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
     * Object:维修工单's 维修费用合计property 
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
     * Object:维修工单's 已收款金额合计property 
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
     * Object:维修工单's 已开票金额合计property 
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
     * Object:维修工单's 是否计算进出厂台次property 
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
     * Object:维修工单's 进厂行驶里程property 
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
     * Object:维修工单's 油量property 
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
     * Object:维修工单's 钥匙号property 
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
     * Object:维修工单's 贵重物品寄存property 
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
     * Object: 维修工单 's 保修类型 property 
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
     * Object: 维修工单 's 保险公司 property 
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
     * Object: 维修工单 's 维修类型 property 
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
     * Object:维修工单's 是否优先派工property 
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
     * Object:维修工单's 是否洗车property 
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
     * Object:维修工单's 是否在店等待property 
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
     * Object:维修工单's 客户要求property 
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
     * Object:维修工单's 厂家编号property 
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
     * Object:维修工单's 洗车备注property 
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
     * Object:维修工单's 洗车完成时间property 
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
     * Object: 维修工单 's 洗车员 property 
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
     * Object: 维修工单 's 中断类型 property 
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
     * Object:维修工单's 作废时的单据状态property 
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
     * Object:维修工单's 下次保养日期property 
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
     * Object:维修工单's 下次保养里程数property 
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
     * Object: 维修工单 's 源单类型 property 
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
     * Object:维修工单's 源单分录IDproperty 
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
     * Object:维修工单's 源单单号property 
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
     * Object:维修工单's 源单行号property 
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
     * Object: 维修工单 's 车主 property 
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
     * Object:维修工单's 送修人property 
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
     * Object:维修工单's 送修人电话property 
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
     * Object: 维修工单 's 维修中断 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORepairBreakEntryCollection getRepairBreakEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORepairBreakEntryCollection)get("RepairBreakEntry");
    }
    /**
     * Object: 维修工单 's 客户折扣分类 property 
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
     * Object:维修工单's 原工单号property 
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
     * Object:维修工单's 维修预约单号property 
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
     * Object:维修工单's 返修类型property 
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
     * Object:维修工单's 理赔标记property 
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
     * Object:维修工单's 索赔标记property 
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
     * Object:维修工单's 实际交车时间property 
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
     * Object:维修工单's 原返修类型property 
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
     * Object:维修工单's 上次进厂时间property 
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
     * Object:维修工单's 版本号property 
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
     * Object: 维修工单 's 会员卡 property 
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
     * Object:维修工单's 上次进厂里程property 
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
     * Object:维修工单's 下次保养修改property 
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
     * Object:维修工单's 生成工时成本property 
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
     * Object: 维修工单 's 维修外包公司 property 
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
     * Object: 维修工单 's 维修集团公司 property 
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
     * Object:维修工单's 维修方式property 
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
     * Object:维修工单's 已生成内部应收property 
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
     * Object:维修工单's 已生成内部应付property 
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
     * Object: 维修工单 's 账户类型 property 
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
     * Object: 维修工单 's 项目配件明细 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWORWOItemSpEntryCollection getRWOItemSpEntry()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWORWOItemSpEntryCollection)get("RWOItemSpEntry");
    }
    /**
     * Object: 维修工单 's 部门 property 
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
     * Object:维修工单's 客户信息property 
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
     * Object:维修工单's 销售类型property 
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
     * Object: 维修工单 's 账户代码 property 
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
     * Object:维修工单's 账户名称property 
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
     * Object:维修工单's 部门property 
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
     * Object: 维修工单 's 业务类型 property 
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
     * Object:维修工单's 单据状态property 
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
     * Object:维修工单's 首登日期property 
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
     * Object:维修工单's 业务员(旧)property 
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
     * Object:维修工单's 是否打印结算单property 
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
     * Object:维修工单's 备注property 
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
     * Object: 维修工单 's 业务员 property 
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
     * Object: 维修工单 's 业务部门 property 
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
     * Object:维修工单's DMSWIPproperty 
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