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
     * Object: 销售出库单 's 销售出库单分录 property 
     */
    public com.kingdee.eas.scm.im.inv.SaleIssueEntryCollection getEntry()
    {
        return (com.kingdee.eas.scm.im.inv.SaleIssueEntryCollection)get("entry");
    }
    /**
     * Object: 销售出库单 's 送货客户 property 
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
     * Object: 销售出库单 's 币别 property 
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
     * Object:销售出库单's 汇率property 
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
     * Object: 销售出库单 's 付款方式 property 
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
     * Object:销售出库单's 折算方式property 
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
     * Object:销售出库单's 是否系统单据property 
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
     * Object:销售出库单's 实际业务日期property 
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
     * Object:销售出库单's 是否生成业务应收property 
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
     * Object:销售出库单's 是否含税property 
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
     * Object:销售出库单's 价税总合计本位币property 
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
     * Object:销售出库单's 整单关联算法property 
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
     * Object:销售出库单's 价格来源property 
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