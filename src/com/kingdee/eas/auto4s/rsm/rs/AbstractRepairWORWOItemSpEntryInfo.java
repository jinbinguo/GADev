package com.kingdee.eas.auto4s.rsm.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairWORWOItemSpEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractRepairWORWOItemSpEntryInfo()
    {
        this("id");
    }
    protected AbstractRepairWORWOItemSpEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: ��Ŀ�����ϸ 's null property 
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
     * Object:��Ŀ�����ϸ's Tproperty 
     */
    public com.kingdee.eas.ga.rs.TEnum getT()
    {
        return com.kingdee.eas.ga.rs.TEnum.getEnum(getString("t"));
    }
    public void setT(com.kingdee.eas.ga.rs.TEnum item)
    {
		if (item != null) {
        setString("t", item.getValue());
		}
    }
    /**
     * Object:��Ŀ�����ϸ's ��Ŀproperty 
     */
    public String getItemspNum()
    {
        return getString("itemspNum");
    }
    public void setItemspNum(String item)
    {
        setString("itemspNum", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ��Ŀ˵��property 
     */
    public String getItemspName()
    {
        return getString("itemspName");
    }
    public void setItemspName(String item)
    {
        setString("itemspName", item);
    }
    /**
     * Object: ��Ŀ�����ϸ 's ά����Ŀ property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo getRepairItem()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo)get("repairItem");
    }
    public void setRepairItem(com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo item)
    {
        put("repairItem", item);
    }
    /**
     * Object: ��Ŀ�����ϸ 's ά����� property 
     */
    public com.kingdee.eas.basedata.master.material.MaterialInfo getMaterial()
    {
        return (com.kingdee.eas.basedata.master.material.MaterialInfo)get("material");
    }
    public void setMaterial(com.kingdee.eas.basedata.master.material.MaterialInfo item)
    {
        put("material", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ����property 
     */
    public java.math.BigDecimal getQty()
    {
        return getBigDecimal("qty");
    }
    public void setQty(java.math.BigDecimal item)
    {
        setBigDecimal("qty", item);
    }
    /**
     * Object:��Ŀ�����ϸ's δ˰�۸�property 
     */
    public java.math.BigDecimal getPrice()
    {
        return getBigDecimal("price");
    }
    public void setPrice(java.math.BigDecimal item)
    {
        setBigDecimal("price", item);
    }
    /**
     * Object:��Ŀ�����ϸ's �ۿ�property 
     */
    public java.math.BigDecimal getDiscountRate()
    {
        return getBigDecimal("discountRate");
    }
    public void setDiscountRate(java.math.BigDecimal item)
    {
        setBigDecimal("discountRate", item);
    }
    /**
     * Object:��Ŀ�����ϸ's �ܼ�property 
     */
    public java.math.BigDecimal getAmount()
    {
        return getBigDecimal("amount");
    }
    public void setAmount(java.math.BigDecimal item)
    {
        setBigDecimal("amount", item);
    }
    /**
     * Object:��Ŀ�����ϸ's �˵�״̬property 
     */
    public com.kingdee.eas.ga.rs.IEnum getI()
    {
        return com.kingdee.eas.ga.rs.IEnum.getEnum(getString("i"));
    }
    public void setI(com.kingdee.eas.ga.rs.IEnum item)
    {
		if (item != null) {
        setString("i", item.getValue());
		}
    }
    /**
     * Object:��Ŀ�����ϸ's �Ƿ����property 
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
     * Object:��Ŀ�����ϸ's δ������property 
     */
    public java.math.BigDecimal getUnIssueQty()
    {
        return getBigDecimal("unIssueQty");
    }
    public void setUnIssueQty(java.math.BigDecimal item)
    {
        setBigDecimal("unIssueQty", item);
    }
    /**
     * Object:��Ŀ�����ϸ's �ѳ�����property 
     */
    public java.math.BigDecimal getIssueQty()
    {
        return getBigDecimal("issueQty");
    }
    public void setIssueQty(java.math.BigDecimal item)
    {
        setBigDecimal("issueQty", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ˰��property 
     */
    public java.math.BigDecimal getTaxRate()
    {
        return getBigDecimal("taxRate");
    }
    public void setTaxRate(java.math.BigDecimal item)
    {
        setBigDecimal("taxRate", item);
    }
    /**
     * Object:��Ŀ�����ϸ's �������property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum getSettlementObject()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum.getEnum(getString("settlementObject"));
    }
    public void setSettlementObject(com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum item)
    {
		if (item != null) {
        setString("settlementObject", item.getValue());
		}
    }
    /**
     * Object: ��Ŀ�����ϸ 's W property 
     */
    public com.kingdee.eas.ga.rs.WInfo getW()
    {
        return (com.kingdee.eas.ga.rs.WInfo)get("w");
    }
    public void setW(com.kingdee.eas.ga.rs.WInfo item)
    {
        put("w", item);
    }
    /**
     * Object:��Ŀ�����ϸ's DMSʵ���к�property 
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
     * Object:��Ŀ�����ϸ's DMS�к�property 
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
     * Object:��Ŀ�����ϸ's �Ƿ��Ƶ�property 
     */
    public boolean isIsCreateTo()
    {
        return getBoolean("isCreateTo");
    }
    public void setIsCreateTo(boolean item)
    {
        setBoolean("isCreateTo", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ��������property 
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
     * Object:��Ŀ�����ϸ's RTS����property 
     */
    public String getRts()
    {
        return getString("rts");
    }
    public void setRts(String item)
    {
        setString("rts", item);
    }
    /**
     * Object:��Ŀ�����ϸ's �˵����property 
     */
    public String getBillNum()
    {
        return getString("billNum");
    }
    public void setBillNum(String item)
    {
        setString("billNum", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ��������property 
     */
    public java.util.Date getPostingDate()
    {
        return getDate("postingDate");
    }
    public void setPostingDate(java.util.Date item)
    {
        setDate("postingDate", item);
    }
    /**
     * Object:��Ŀ�����ϸ's Ӧ������property 
     */
    public boolean isIsAPSettle()
    {
        return getBoolean("isAPSettle");
    }
    public void setIsAPSettle(boolean item)
    {
        setBoolean("isAPSettle", item);
    }
    /**
     * Object:��Ŀ�����ϸ's �ɱ���property 
     */
    public java.math.BigDecimal getCostAmount()
    {
        return getBigDecimal("costAmount");
    }
    public void setCostAmount(java.math.BigDecimal item)
    {
        setBigDecimal("costAmount", item);
    }
    /**
     * Object:��Ŀ�����ϸ's �˻�property 
     */
    public String getAccount()
    {
        return getString("account");
    }
    public void setAccount(String item)
    {
        setString("account", item);
    }
    /**
     * Object:��Ŀ�����ϸ's �Ƿ�ɾ��property 
     */
    public boolean isIsDelete()
    {
        return getBoolean("isDelete");
    }
    public void setIsDelete(boolean item)
    {
        setBoolean("isDelete", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ��˰�۸�property 
     */
    public java.math.BigDecimal getTaxPrice()
    {
        return getBigDecimal("taxPrice");
    }
    public void setTaxPrice(java.math.BigDecimal item)
    {
        setBigDecimal("taxPrice", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ��˰�ܼ�property 
     */
    public java.math.BigDecimal getTaxAmount()
    {
        return getBigDecimal("taxAmount");
    }
    public void setTaxAmount(java.math.BigDecimal item)
    {
        setBigDecimal("taxAmount", item);
    }
    /**
     * Object: ��Ŀ�����ϸ 's �ײ� property 
     */
    public com.kingdee.eas.ga.rs.RepairPackageInfo getRepairPkg()
    {
        return (com.kingdee.eas.ga.rs.RepairPackageInfo)get("repairPkg");
    }
    public void setRepairPkg(com.kingdee.eas.ga.rs.RepairPackageInfo item)
    {
        put("repairPkg", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ���÷ֵ�property 
     */
    public java.math.BigDecimal getAllocateExenseRate()
    {
        return getBigDecimal("allocateExenseRate");
    }
    public void setAllocateExenseRate(java.math.BigDecimal item)
    {
        setBigDecimal("allocateExenseRate", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ά�޷�ʽproperty 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum getRepairWay()
    {
        return com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum.getEnum(getString("repairWay"));
    }
    public void setRepairWay(com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum item)
    {
		if (item != null) {
        setString("repairWay", item.getValue());
		}
    }
    /**
     * Object: ��Ŀ�����ϸ 's ά�������˾ property 
     */
    public com.kingdee.eas.basedata.master.cssp.SupplierInfo getSupplier()
    {
        return (com.kingdee.eas.basedata.master.cssp.SupplierInfo)get("supplier");
    }
    public void setSupplier(com.kingdee.eas.basedata.master.cssp.SupplierInfo item)
    {
        put("supplier", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ��ʼʵ�ʵ���property 
     */
    public java.math.BigDecimal getInitFactPrice()
    {
        return getBigDecimal("initFactPrice");
    }
    public void setInitFactPrice(java.math.BigDecimal item)
    {
        setBigDecimal("initFactPrice", item);
    }
    /**
     * Object:��Ŀ�����ϸ's ����۸�property 
     */
    public java.math.BigDecimal getWprice()
    {
        return getBigDecimal("wprice");
    }
    public void setWprice(java.math.BigDecimal item)
    {
        setBigDecimal("wprice", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("FF1F0E1A");
    }
}