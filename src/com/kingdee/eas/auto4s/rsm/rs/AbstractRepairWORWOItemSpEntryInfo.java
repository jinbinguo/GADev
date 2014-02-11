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
     * Object: ��Ŀ/�����ϸ 's null property 
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
     * Object:��Ŀ/�����ϸ's Tproperty 
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
     * Object:��Ŀ/�����ϸ's ��Ŀproperty 
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
     * Object:��Ŀ/�����ϸ's ��Ŀ˵��property 
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
     * Object: ��Ŀ/�����ϸ 's ά����Ŀ property 
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
     * Object: ��Ŀ/�����ϸ 's ά����� property 
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
     * Object:��Ŀ/�����ϸ's �ײ�property 
     */
    public String getTaocan()
    {
        return getString("taocan");
    }
    public void setTaocan(String item)
    {
        setString("taocan", item);
    }
    /**
     * Object: ��Ŀ/�����ϸ 's W property 
     */
    public com.kingdee.eas.ep.DataBaseCustomInfo getW()
    {
        return (com.kingdee.eas.ep.DataBaseCustomInfo)get("w");
    }
    public void setW(com.kingdee.eas.ep.DataBaseCustomInfo item)
    {
        put("w", item);
    }
    /**
     * Object:��Ŀ/�����ϸ's ����property 
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
     * Object:��Ŀ/�����ϸ's �۸�property 
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
     * Object:��Ŀ/�����ϸ's �ۿ�%property 
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
     * Object:��Ŀ/�����ϸ's �ܼ�property 
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
     * Object:��Ŀ/�����ϸ's Iproperty 
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
     * Object:��Ŀ/�����ϸ's �Ƿ����property 
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
     * Object:��Ŀ/�����ϸ's δ������property 
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
     * Object:��Ŀ/�����ϸ's �ѳ�����property 
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
     * Object:��Ŀ/�����ϸ's ˰��%property 
     */
    public java.math.BigDecimal getTaxRate()
    {
        return getBigDecimal("taxRate");
    }
    public void setTaxRate(java.math.BigDecimal item)
    {
        setBigDecimal("taxRate", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("FF1F0E1A");
    }
}