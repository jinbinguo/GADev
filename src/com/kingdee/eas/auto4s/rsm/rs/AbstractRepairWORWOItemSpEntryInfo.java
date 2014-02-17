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
     * Object: 项目配件明细 's null property 
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
     * Object:项目配件明细's Tproperty 
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
     * Object:项目配件明细's 项目property 
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
     * Object:项目配件明细's 项目说明property 
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
     * Object: 项目配件明细 's 维修项目 property 
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
     * Object: 项目配件明细 's 维修配件 property 
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
     * Object:项目配件明细's 套餐property 
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
     * Object:项目配件明细's 数量property 
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
     * Object:项目配件明细's 价格property 
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
     * Object:项目配件明细's 折扣property 
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
     * Object:项目配件明细's 总计property 
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
     * Object:项目配件明细's Iproperty 
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
     * Object:项目配件明细's 是否拆退property 
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
     * Object:项目配件明细's 未出库数property 
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
     * Object:项目配件明细's 已出数库property 
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
     * Object:项目配件明细's 税率property 
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
     * Object:项目配件明细's 结算对象property 
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
     * Object: 项目配件明细 's W property 
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
     * Object:项目配件明细's DMS实际行号property 
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
     * Object:项目配件明细's DMS行号property 
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
     * Object:项目配件明细's 是否推单property 
     */
    public boolean isIsCreateTo()
    {
        return getBoolean("isCreateTo");
    }
    public void setIsCreateTo(boolean item)
    {
        setBoolean("isCreateTo", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("FF1F0E1A");
    }
}