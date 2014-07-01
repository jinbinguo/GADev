package com.kingdee.eas.scm.im.inv;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractSaleIssueBillInfo extends com.kingdee.eas.scm.im.inv.InvBillBaseInfo implements Serializable 
{
    public AbstractSaleIssueBillInfo()
    {
        this("id");
    }
    protected AbstractSaleIssueBillInfo(String pkField)
    {
        super(pkField);
        put("entry", new com.kingdee.eas.scm.im.inv.SaleIssueEntryCollection());
    }
    /**
     * Object: ���۳��ⵥ 's ���۳��ⵥ��¼ property 
     */
    public com.kingdee.eas.scm.im.inv.SaleIssueEntryCollection getEntry()
    {
        return (com.kingdee.eas.scm.im.inv.SaleIssueEntryCollection)get("entry");
    }
    /**
     * Object: ���۳��ⵥ 's �ͻ��ͻ� property 
     */
    public com.kingdee.eas.basedata.master.cssp.CustomerInfo getCustomer()
    {
        return (com.kingdee.eas.basedata.master.cssp.CustomerInfo)get("customer");
    }
    public void setCustomer(com.kingdee.eas.basedata.master.cssp.CustomerInfo item)
    {
        put("customer", item);
    }
    /**
     * Object: ���۳��ⵥ 's �ұ� property 
     */
    public com.kingdee.eas.basedata.assistant.CurrencyInfo getCurrency()
    {
        return (com.kingdee.eas.basedata.assistant.CurrencyInfo)get("currency");
    }
    public void setCurrency(com.kingdee.eas.basedata.assistant.CurrencyInfo item)
    {
        put("currency", item);
    }
    /**
     * Object:���۳��ⵥ's ����property 
     */
    public java.math.BigDecimal getExchangeRate()
    {
        return getBigDecimal("exchangeRate");
    }
    public void setExchangeRate(java.math.BigDecimal item)
    {
        setBigDecimal("exchangeRate", item);
    }
    /**
     * Object: ���۳��ⵥ 's ���ʽ property 
     */
    public com.kingdee.eas.basedata.assistant.PaymentTypeInfo getPaymentType()
    {
        return (com.kingdee.eas.basedata.assistant.PaymentTypeInfo)get("paymentType");
    }
    public void setPaymentType(com.kingdee.eas.basedata.assistant.PaymentTypeInfo item)
    {
        put("paymentType", item);
    }
    /**
     * Object:���۳��ⵥ's ���㷽ʽproperty 
     */
    public com.kingdee.eas.basedata.assistant.ConvertModeEnum getConvertMode()
    {
        return com.kingdee.eas.basedata.assistant.ConvertModeEnum.getEnum(getInt("convertMode"));
    }
    public void setConvertMode(com.kingdee.eas.basedata.assistant.ConvertModeEnum item)
    {
		if (item != null) {
        setInt("convertMode", item.getValue());
		}
    }
    /**
     * Object:���۳��ⵥ's �Ƿ�ϵͳ����property 
     */
    public boolean isIsSysBill()
    {
        return getBoolean("isSysBill");
    }
    public void setIsSysBill(boolean item)
    {
        setBoolean("isSysBill", item);
    }
    /**
     * Object:���۳��ⵥ's ʵ��ҵ������property 
     */
    public java.util.Date getActBizDate()
    {
        return getDate("actBizDate");
    }
    public void setActBizDate(java.util.Date item)
    {
        setDate("actBizDate", item);
    }
    /**
     * Object:���۳��ⵥ's �Ƿ�����ҵ��Ӧ��property 
     */
    public boolean isIsGenBizAR()
    {
        return getBoolean("isGenBizAR");
    }
    public void setIsGenBizAR(boolean item)
    {
        setBoolean("isGenBizAR", item);
    }
    /**
     * Object:���۳��ⵥ's �Ƿ�˰property 
     */
    public boolean isIsInTax()
    {
        return getBoolean("isInTax");
    }
    public void setIsInTax(boolean item)
    {
        setBoolean("isInTax", item);
    }
    /**
     * Object:���۳��ⵥ's ��˰�ܺϼƱ�λ��property 
     */
    public java.math.BigDecimal getTotalLocalAmount()
    {
        return getBigDecimal("totalLocalAmount");
    }
    public void setTotalLocalAmount(java.math.BigDecimal item)
    {
        setBigDecimal("totalLocalAmount", item);
    }
    /**
     * Object:���۳��ⵥ's ���������㷨property 
     */
    public com.kingdee.eas.scm.common.BillRelationOptionEnum getBillRelationOption()
    {
        return com.kingdee.eas.scm.common.BillRelationOptionEnum.getEnum(getInt("billRelationOption"));
    }
    public void setBillRelationOption(com.kingdee.eas.scm.common.BillRelationOptionEnum item)
    {
		if (item != null) {
        setInt("billRelationOption", item.getValue());
		}
    }
    /**
     * Object:���۳��ⵥ's �۸���Դproperty 
     */
    public com.kingdee.eas.basedata.scm.im.inv.FetchPriceEnum getPriceSource()
    {
        return com.kingdee.eas.basedata.scm.im.inv.FetchPriceEnum.getEnum(getString("priceSource"));
    }
    public void setPriceSource(com.kingdee.eas.basedata.scm.im.inv.FetchPriceEnum item)
    {
		if (item != null) {
        setString("priceSource", item.getValue());
		}
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("CC3E933B");
    }
}