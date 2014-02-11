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
     * Object: ά����� 's null property 
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
     * Object:ά�����'s �������property 
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
     * Object:ά�����'s ����property 
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
     * Object:ά�����'s �ѳ�������property 
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
     * Object:ά�����'s ��˰����property 
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
     * Object:ά�����'s ˰��(%)property 
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
     * Object:ά�����'s ����˰����property 
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
     * Object:ά�����'s ��˰���property 
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
     * Object:ά�����'s ����˰���property 
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
     * Object:ά�����'s �Ƿ�BO��property 
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
     * Object:ά�����'s ˰��property 
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
     * Object:ά�����'s �ۿ���(%)property 
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
     * Object:ά�����'s �Żݽ��property 
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
     * Object:ά�����'s Ӧ�ս��property 
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
     * Object:ά�����'s ʵ�ս��property 
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
     * Object:ά�����'s �Ƿ�׷��property 
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
     * Object:ά�����'s ɾ�����property 
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
     * Object: ά����� 's ����ά���� property 
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
     * Object:ά�����'s ��עproperty 
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
     * Object:ά�����'s ��ʱ���property 
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
     * Object:ά�����'s Ԥ�Ƶ���ʱ��property 
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
     * Object: ά����� 's ά������ property 
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
     * Object: ά����� 's ������� property 
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
     * Object: ά����� 's ������� property 
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
     * Object: ά����� 's ���� property 
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
     * Object: ά����� 's ��λ property 
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
     * Object: ά����� 's �ײ� property 
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
     * Object:ά�����'s Դ���к�property 
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
     * Object: ά����� 's Դ������ property 
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
     * Object:ά�����'s Դ��IDproperty 
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
     * Object:ά�����'s Դ����¼IDproperty 
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
     * Object:ά�����'s Դ������property 
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
     * Object:ά�����'s δ��������property 
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
     * Object: ά����� 's �������� property 
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
     * Object:ά�����'s ����������λ����property 
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
     * Object: ά����� 's ����������λ property 
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
     * Object:ά�����'s ԭ�ۿ���(%)property 
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
     * Object:ά�����'s �Ƿ�ѡ��property 
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
     * Object: ά����� 's ���Ϸ��� property 
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
     * Object: ά����� 's ά������ property 
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
     * Object:ά�����'s DMS�к�property 
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
     * Object:ά�����'s DMSʵ���к�property 
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
     * Object:ά�����'s �Ƿ����property 
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
     * Object:ά�����'s ��Ŀ/�����ϸ��¼IDproperty 
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