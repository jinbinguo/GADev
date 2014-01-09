package com.kingdee.eas.train;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractPurRequestEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractPurRequestEntryInfo()
    {
        this("id");
    }
    protected AbstractPurRequestEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 分录 's 单据头 property 
     */
    public com.kingdee.eas.train.PurRequestInfo getParent()
    {
        return (com.kingdee.eas.train.PurRequestInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.train.PurRequestInfo item)
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
     * Object:分录's 申请数量property 
     */
    public java.math.BigDecimal getRequestQty()
    {
        return getBigDecimal("requestQty");
    }
    public void setRequestQty(java.math.BigDecimal item)
    {
        setBigDecimal("requestQty", item);
    }
    /**
     * Object:分录's 已订货数量property 
     */
    public java.math.BigDecimal getOrderedQty()
    {
        return getBigDecimal("orderedQty");
    }
    public void setOrderedQty(java.math.BigDecimal item)
    {
        setBigDecimal("orderedQty", item);
    }
    /**
     * Object:分录's 建议采购单价property 
     */
    public java.math.BigDecimal getSuggestPrice()
    {
        return getBigDecimal("suggestPrice");
    }
    public void setSuggestPrice(java.math.BigDecimal item)
    {
        setBigDecimal("suggestPrice", item);
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
     * Object:分录's 需求日期property 
     */
    public java.util.Date getRequestDate()
    {
        return getDate("requestDate");
    }
    public void setRequestDate(java.util.Date item)
    {
        setDate("requestDate", item);
    }
    /**
     * Object:分录's 行状态property 
     */
    public com.kingdee.eas.scm.common.EntryBaseStatusEnum getLineStatus()
    {
        return com.kingdee.eas.scm.common.EntryBaseStatusEnum.getEnum(getInt("lineStatus"));
    }
    public void setLineStatus(com.kingdee.eas.scm.common.EntryBaseStatusEnum item)
    {
		if (item != null) {
        setInt("lineStatus", item.getValue());
		}
    }
    /**
     * Object:分录's 未订货数量property 
     */
    public java.math.BigDecimal getUnOrderedQty()
    {
        return getBigDecimal("unOrderedQty");
    }
    public void setUnOrderedQty(java.math.BigDecimal item)
    {
        setBigDecimal("unOrderedQty", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("D6C84858");
    }
}