package com.kingdee.eas.ga.basedata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractCustomerDiscountEntryInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillEntryInfo implements Serializable 
{
    public AbstractCustomerDiscountEntryInfo()
    {
        this("id");
    }
    protected AbstractCustomerDiscountEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 分录 's 单据头 property 
     */
    public com.kingdee.eas.ga.basedata.CustomerDiscountInfo getParent()
    {
        return (com.kingdee.eas.ga.basedata.CustomerDiscountInfo)get("Parent");
    }
    public void setParent(com.kingdee.eas.ga.basedata.CustomerDiscountInfo item)
    {
        put("Parent", item);
    }
    /**
     * Object: 分录 's 客户 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo getAtsCustomer()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo)get("atsCustomer");
    }
    public void setAtsCustomer(com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo item)
    {
        put("atsCustomer", item);
    }
    /**
     * Object:分录's 维修折扣率property 
     */
    public java.math.BigDecimal getRepairDiscountRate()
    {
        return getBigDecimal("repairDiscountRate");
    }
    public void setRepairDiscountRate(java.math.BigDecimal item)
    {
        setBigDecimal("repairDiscountRate", item);
    }
    /**
     * Object:分录's 零售折扣率property 
     */
    public java.math.BigDecimal getRetailDiscountRate()
    {
        return getBigDecimal("retailDiscountRate");
    }
    public void setRetailDiscountRate(java.math.BigDecimal item)
    {
        setBigDecimal("retailDiscountRate", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("772DEF82");
    }
}