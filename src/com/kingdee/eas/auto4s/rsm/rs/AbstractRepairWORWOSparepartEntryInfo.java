package com.kingdee.eas.auto4s.rsm.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairWORWOSparepartEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractRepairWORWOSparepartEntryInfo()
    {
        this("id");
    }
    protected AbstractRepairWORWOSparepartEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 维修配件 's null property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo getParent()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:维修配件's 结算对象property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum getSettleObject()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum.getEnum(getString("SettleObject"));
    }
    public void setSettleObject(com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum item)
    {
		if (item != null) {
        setString("SettleObject", item.getValue());
		}
    }
    /**
     * Object:维修配件's 数量property 
     */
    public java.math.BigDecimal getQty()
    {
        return getBigDecimal("Qty");
    }
    public void setQty(java.math.BigDecimal item)
    {
        setBigDecimal("Qty", item);
    }
    /**
     * Object:维修配件's 已出库数量property 
     */
    public java.math.BigDecimal getIssueQty()
    {
        return getBigDecimal("IssueQty");
    }
    public void setIssueQty(java.math.BigDecimal item)
    {
        setBigDecimal("IssueQty", item);
    }
    /**
     * Object:维修配件's 含税单价property 
     */
    public java.math.BigDecimal getTaxPrice()
    {
        return getBigDecimal("TaxPrice");
    }
    public void setTaxPrice(java.math.BigDecimal item)
    {
        setBigDecimal("TaxPrice", item);
    }
    /**
     * Object:维修配件's 税率(%)property 
     */
    public java.math.BigDecimal getTaxRate()
    {
        return getBigDecimal("TaxRate");
    }
    public void setTaxRate(java.math.BigDecimal item)
    {
        setBigDecimal("TaxRate", item);
    }
    /**
     * Object:维修配件's 不含税单价property 
     */
    public java.math.BigDecimal getNoTaxPrice()
    {
        return getBigDecimal("NoTaxPrice");
    }
    public void setNoTaxPrice(java.math.BigDecimal item)
    {
        setBigDecimal("NoTaxPrice", item);
    }
    /**
     * Object:维修配件's 含税金额property 
     */
    public java.math.BigDecimal getTaxAmount()
    {
        return getBigDecimal("TaxAmount");
    }
    public void setTaxAmount(java.math.BigDecimal item)
    {
        setBigDecimal("TaxAmount", item);
    }
    /**
     * Object:维修配件's 不含税金额property 
     */
    public java.math.BigDecimal getNoTaxAmount()
    {
        return getBigDecimal("NoTaxAmount");
    }
    public void setNoTaxAmount(java.math.BigDecimal item)
    {
        setBigDecimal("NoTaxAmount", item);
    }
    /**
     * Object:维修配件's 是否BO件property 
     */
    public boolean isIsBO()
    {
        return getBoolean("IsBO");
    }
    public void setIsBO(boolean item)
    {
        setBoolean("IsBO", item);
    }
    /**
     * Object:维修配件's 税额property 
     */
    public java.math.BigDecimal getTax()
    {
        return getBigDecimal("Tax");
    }
    public void setTax(java.math.BigDecimal item)
    {
        setBigDecimal("Tax", item);
    }
    /**
     * Object:维修配件's 折扣率(%)property 
     */
    public java.math.BigDecimal getDiscountRate()
    {
        return getBigDecimal("DiscountRate");
    }
    public void setDiscountRate(java.math.BigDecimal item)
    {
        setBigDecimal("DiscountRate", item);
    }
    /**
     * Object:维修配件's 优惠金额property 
     */
    public java.math.BigDecimal getDiscountAmount()
    {
        return getBigDecimal("DiscountAmount");
    }
    public void setDiscountAmount(java.math.BigDecimal item)
    {
        setBigDecimal("DiscountAmount", item);
    }
    /**
     * Object:维修配件's 应收金额property 
     */
    public java.math.BigDecimal getARAmount()
    {
        return getBigDecimal("ARAmount");
    }
    public void setARAmount(java.math.BigDecimal item)
    {
        setBigDecimal("ARAmount", item);
    }
    /**
     * Object:维修配件's 实收金额property 
     */
    public java.math.BigDecimal getActualAmount()
    {
        return getBigDecimal("ActualAmount");
    }
    public void setActualAmount(java.math.BigDecimal item)
    {
        setBigDecimal("ActualAmount", item);
    }
    /**
     * Object:维修配件's 是否追加property 
     */
    public boolean isIsAppend()
    {
        return getBoolean("IsAppend");
    }
    public void setIsAppend(boolean item)
    {
        setBoolean("IsAppend", item);
    }
    /**
     * Object:维修配件's 删除标记property 
     */
    public boolean isIsDelete()
    {
        return getBoolean("IsDelete");
    }
    public void setIsDelete(boolean item)
    {
        setBoolean("IsDelete", item);
    }
    /**
     * Object: 维修配件 's 所属维修项 property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo getRepairItem()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo)get("RepairItem");
    }
    public void setRepairItem(com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo item)
    {
        put("RepairItem", item);
    }
    /**
     * Object:维修配件's 备注property 
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
     * Object:维修配件's 即时库存property 
     */
    public java.math.BigDecimal getInstantStore()
    {
        return getBigDecimal("InstantStore");
    }
    public void setInstantStore(java.math.BigDecimal item)
    {
        setBigDecimal("InstantStore", item);
    }
    /**
     * Object:维修配件's 预计到货时间property 
     */
    public java.util.Date getIntendArrivalTime()
    {
        return getDate("IntendArrivalTime");
    }
    public void setIntendArrivalTime(java.util.Date item)
    {
        setDate("IntendArrivalTime", item);
    }
    /**
     * Object: 维修配件 's 维修种类 property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyInfo getRepairClassify()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyInfo)get("RepairClassify");
    }
    public void setRepairClassify(com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyInfo item)
    {
        put("RepairClassify", item);
    }
    /**
     * Object: 维修配件 's 付费类别 property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.PaymentClassifyInfo getPaymentClassify()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.PaymentClassifyInfo)get("PaymentClassify");
    }
    public void setPaymentClassify(com.kingdee.eas.auto4s.bdm.rsm.PaymentClassifyInfo item)
    {
        put("PaymentClassify", item);
    }
    /**
     * Object: 维修配件 's 配件编码 property 
     */
    public com.kingdee.eas.basedata.master.material.MaterialInfo getMaterial()
    {
        return (com.kingdee.eas.basedata.master.material.MaterialInfo)get("Material");
    }
    public void setMaterial(com.kingdee.eas.basedata.master.material.MaterialInfo item)
    {
        put("Material", item);
    }
    /**
     * Object: 维修配件 's 服务活动 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.ServiceActivityInfo getServiceActivity()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.ServiceActivityInfo)get("ServiceActivity");
    }
    public void setServiceActivity(com.kingdee.eas.auto4s.rsm.rs.ServiceActivityInfo item)
    {
        put("ServiceActivity", item);
    }
    /**
     * Object: 维修配件 's 单位 property 
     */
    public com.kingdee.eas.basedata.assistant.MeasureUnitInfo getUnit()
    {
        return (com.kingdee.eas.basedata.assistant.MeasureUnitInfo)get("Unit");
    }
    public void setUnit(com.kingdee.eas.basedata.assistant.MeasureUnitInfo item)
    {
        put("Unit", item);
    }
    /**
     * Object: 维修配件 's 套餐 property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairPkgInfo getRepairPkg()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairPkgInfo)get("RepairPkg");
    }
    public void setRepairPkg(com.kingdee.eas.auto4s.bdm.rsm.RepairPkgInfo item)
    {
        put("RepairPkg", item);
    }
    /**
     * Object:维修配件's 源单行号property 
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
     * Object: 维修配件 's 源单类型 property 
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
     * Object:维修配件's 源单IDproperty 
     */
    public String getSourceBillID()
    {
        return getString("SourceBillID");
    }
    public void setSourceBillID(String item)
    {
        setString("SourceBillID", item);
    }
    /**
     * Object:维修配件's 源单分录IDproperty 
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
     * Object:维修配件's 源单单号property 
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
     * Object:维修配件's 未出库数量property 
     */
    public java.math.BigDecimal getNoIssueQty()
    {
        return getBigDecimal("NoIssueQty");
    }
    public void setNoIssueQty(java.math.BigDecimal item)
    {
        setBigDecimal("NoIssueQty", item);
    }
    /**
     * Object: 维修配件 's 辅助属性 property 
     */
    public com.kingdee.eas.basedata.master.material.AsstAttrValueInfo getAssistProperty()
    {
        return (com.kingdee.eas.basedata.master.material.AsstAttrValueInfo)get("AssistProperty");
    }
    public void setAssistProperty(com.kingdee.eas.basedata.master.material.AsstAttrValueInfo item)
    {
        put("AssistProperty", item);
    }
    /**
     * Object:维修配件's 基本计量单位数量property 
     */
    public java.math.BigDecimal getBaseQty()
    {
        return getBigDecimal("BaseQty");
    }
    public void setBaseQty(java.math.BigDecimal item)
    {
        setBigDecimal("BaseQty", item);
    }
    /**
     * Object: 维修配件 's 基本计量单位 property 
     */
    public com.kingdee.eas.basedata.assistant.MeasureUnitInfo getBaseUnit()
    {
        return (com.kingdee.eas.basedata.assistant.MeasureUnitInfo)get("BaseUnit");
    }
    public void setBaseUnit(com.kingdee.eas.basedata.assistant.MeasureUnitInfo item)
    {
        put("BaseUnit", item);
    }
    /**
     * Object:维修配件's 原折扣率(%)property 
     */
    public java.math.BigDecimal getOldDiscountRate()
    {
        return getBigDecimal("OldDiscountRate");
    }
    public void setOldDiscountRate(java.math.BigDecimal item)
    {
        setBigDecimal("OldDiscountRate", item);
    }
    /**
     * Object:维修配件's 是否选中property 
     */
    public boolean isIsSelect()
    {
        return getBoolean("isSelect");
    }
    public void setIsSelect(boolean item)
    {
        setBoolean("isSelect", item);
    }
    /**
     * Object: 维修配件 's 物料分类 property 
     */
    public com.kingdee.eas.basedata.master.material.MaterialGroupInfo getMaterialGroup()
    {
        return (com.kingdee.eas.basedata.master.material.MaterialGroupInfo)get("MaterialGroup");
    }
    public void setMaterialGroup(com.kingdee.eas.basedata.master.material.MaterialGroupInfo item)
    {
        put("MaterialGroup", item);
    }
    /**
     * Object: 维修配件 's 维修类型 property 
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
     * Object:维修配件's DMS行号property 
     */
    public int getWipLineNo()
    {
        return getInt("wipLineNo");
    }
    public void setWipLineNo(int item)
    {
        setInt("wipLineNo", item);
    }
    /**
     * Object:维修配件's DMS实际行号property 
     */
    public int getWipFactLineNo()
    {
        return getInt("wipFactLineNo");
    }
    public void setWipFactLineNo(int item)
    {
        setInt("wipFactLineNo", item);
    }
    /**
     * Object:维修配件's 是否拆退property 
     */
    public boolean isIsCT()
    {
        return getBoolean("isCT");
    }
    public void setIsCT(boolean item)
    {
        setBoolean("isCT", item);
    }
    /**
     * Object:维修配件's 项目/配件明细分录IDproperty 
     */
    public String getItemSpEntryId()
    {
        return getString("itemSpEntryId");
    }
    public void setItemSpEntryId(String item)
    {
        setString("itemSpEntryId", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("1BEE6B08");
    }
}