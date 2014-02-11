package com.kingdee.eas.auto4s.rsm.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairWORWOTotalAmountEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractRepairWORWOTotalAmountEntryInfo()
    {
        this("id");
    }
    protected AbstractRepairWORWOTotalAmountEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 金额汇总 's null property 
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
     * Object:金额汇总's 结算对象property 
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
     * Object:金额汇总's 原金额property 
     */
    public java.math.BigDecimal getOldAmount()
    {
        return getBigDecimal("OldAmount");
    }
    public void setOldAmount(java.math.BigDecimal item)
    {
        setBigDecimal("OldAmount", item);
    }
    /**
     * Object:金额汇总's 应收金额property 
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
     * Object:金额汇总's 结算金额property 
     */
    public java.math.BigDecimal getSettleAmount()
    {
        return getBigDecimal("SettleAmount");
    }
    public void setSettleAmount(java.math.BigDecimal item)
    {
        setBigDecimal("SettleAmount", item);
    }
    /**
     * Object:金额汇总's 不含税金额property 
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
     * Object:金额汇总's 税额property 
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
     * Object:金额汇总's 折扣率(%)property 
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
     * Object:金额汇总's 类别property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.AmountClassifyEnum getAmountClassify()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.AmountClassifyEnum.getEnum(getString("AmountClassify"));
    }
    public void setAmountClassify(com.kingdee.eas.auto4s.bdm.rsm.AmountClassifyEnum item)
    {
		if (item != null) {
        setString("AmountClassify", item.getValue());
		}
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("CD6110D6");
    }
}