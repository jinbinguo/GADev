package com.kingdee.eas.ga.basedata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractCustomerDiscountInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillInfo implements Serializable 
{
    public AbstractCustomerDiscountInfo()
    {
        this("id");
    }
    protected AbstractCustomerDiscountInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.ga.basedata.CustomerDiscountEntryCollection());
    }
    /**
     * Object: �ͻ��ۿ� 's ��¼ property 
     */
    public com.kingdee.eas.ga.basedata.CustomerDiscountEntryCollection getEntrys()
    {
        return (com.kingdee.eas.ga.basedata.CustomerDiscountEntryCollection)get("entrys");
    }
    /**
     * Object:�ͻ��ۿ�'s ��Ч����property 
     */
    public java.util.Date getEffectiveDate()
    {
        return getDate("effectiveDate");
    }
    public void setEffectiveDate(java.util.Date item)
    {
        setDate("effectiveDate", item);
    }
    /**
     * Object:�ͻ��ۿ�'s ʧЧ����property 
     */
    public java.util.Date getExpirationDate()
    {
        return getDate("expirationDate");
    }
    public void setExpirationDate(java.util.Date item)
    {
        setDate("expirationDate", item);
    }
    /**
     * Object: �ͻ��ۿ� 's ��˾ property 
     */
    public com.kingdee.eas.basedata.org.SaleOrgUnitInfo getSaleOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.SaleOrgUnitInfo)get("saleOrgUnit");
    }
    public void setSaleOrgUnit(com.kingdee.eas.basedata.org.SaleOrgUnitInfo item)
    {
        put("saleOrgUnit", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("E9F46AD0");
    }
}