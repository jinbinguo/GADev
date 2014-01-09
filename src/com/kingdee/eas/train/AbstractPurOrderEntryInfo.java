package com.kingdee.eas.train;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractPurOrderEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractPurOrderEntryInfo()
    {
        this("id");
    }
    protected AbstractPurOrderEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 分录 's 单据头 property 
     */
    public com.kingdee.eas.train.PurOrderInfo getParent()
    {
        return (com.kingdee.eas.train.PurOrderInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.train.PurOrderInfo item)
    {
        put("parent", item);
    }
    /**
     * Object: 分录 's 物料 property 
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
     * Object: 分录 's 计量单位 property 
     */
    public com.kingdee.eas.basedata.assistant.MeasureUnitInfo getUnit()
    {
        return (com.kingdee.eas.basedata.assistant.MeasureUnitInfo)get("unit");
    }
    public void setUnit(com.kingdee.eas.basedata.assistant.MeasureUnitInfo item)
    {
        put("unit", item);
    }
    /**
     * Object:分录's 订货数量property 
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
     * Object:分录's 单价property 
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
     * Object:分录's 金额property 
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
     * Object:分录's 交货日期property 
     */
    public java.util.Date getDeliveryDate()
    {
        return getDate("deliveryDate");
    }
    public void setDeliveryDate(java.util.Date item)
    {
        setDate("deliveryDate", item);
    }
    /**
     * Object:分录's 已入库数property 
     */
    public java.math.BigDecimal getInsideQty()
    {
        return getBigDecimal("insideQty");
    }
    public void setInsideQty(java.math.BigDecimal item)
    {
        setBigDecimal("insideQty", item);
    }
    /**
     * Object:分录's 来源单号property 
     */
    public String getSourceBillNumber()
    {
        return getString("sourceBillNumber");
    }
    public void setSourceBillNumber(String item)
    {
        setString("sourceBillNumber", item);
    }
    /**
     * Object:分录's 来源单据idproperty 
     */
    public String getSourceBillId()
    {
        return getString("sourceBillId");
    }
    public void setSourceBillId(String item)
    {
        setString("sourceBillId", item);
    }
    /**
     * Object:分录's 来源单据分录IDproperty 
     */
    public String getSourceBillEntryId()
    {
        return getString("sourceBillEntryId");
    }
    public void setSourceBillEntryId(String item)
    {
        setString("sourceBillEntryId", item);
    }
    /**
     * Object:分录's 来源单据分录序号property 
     */
    public int getSourceBillEntrySeq()
    {
        return getInt("sourceBillEntrySeq");
    }
    public void setSourceBillEntrySeq(int item)
    {
        setInt("sourceBillEntrySeq", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("3A709C39");
    }
}