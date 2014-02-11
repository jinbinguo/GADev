package com.kingdee.eas.auto4s.rsm.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairWORWOAttachmentItemEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractRepairWORWOAttachmentItemEntryInfo()
    {
        this("id");
    }
    protected AbstractRepairWORWOAttachmentItemEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 附加项目 's null property 
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
     * Object:附加项目's 附加项目金额property 
     */
    public java.math.BigDecimal getAttaItemAmount()
    {
        return getBigDecimal("AttaItemAmount");
    }
    public void setAttaItemAmount(java.math.BigDecimal item)
    {
        setBigDecimal("AttaItemAmount", item);
    }
    /**
     * Object:附加项目's 折扣率(%)property 
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
     * Object:附加项目's 优惠金额property 
     */
    public java.math.BigDecimal getDiscountAmount()
    {
        return getBigDecimal("DiscountAmount");
    }
    public void setDiscountAmount(java.math.BigDecimal item)
    {
        setBigDecimal("DiscountAmount", item);
    }
    /**
     * Object:附加项目's 应收金额property 
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
     * Object:附加项目's 成本property 
     */
    public java.math.BigDecimal getCost()
    {
        return getBigDecimal("Cost");
    }
    public void setCost(java.math.BigDecimal item)
    {
        setBigDecimal("Cost", item);
    }
    /**
     * Object:附加项目's 附加项目备注property 
     */
    public String getRemark()
    {
        return getString("Remark");
    }
    public void setRemark(String item)
    {
        setString("Remark", item);
    }
    /**
     * Object: 附加项目 's 附加项目编码 property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.AttachmentItemInfo getAttaItem()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.AttachmentItemInfo)get("AttaItem");
    }
    public void setAttaItem(com.kingdee.eas.auto4s.bdm.rsm.AttachmentItemInfo item)
    {
        put("AttaItem", item);
    }
    /**
     * Object: 附加项目 's 源单类型 property 
     */
    public com.kingdee.eas.basedata.scm.common.BillTypeInfo getSourceBillType()
    {
        return (com.kingdee.eas.basedata.scm.common.BillTypeInfo)get("SourceBillType");
    }
    public void setSourceBillType(com.kingdee.eas.basedata.scm.common.BillTypeInfo item)
    {
        put("SourceBillType", item);
    }
    /**
     * Object:附加项目's 源单IDproperty 
     */
    public String getSourceBillID()
    {
        return getString("SourceBillID");
    }
    public void setSourceBillID(String item)
    {
        setString("SourceBillID", item);
    }
    /**
     * Object:附加项目's 源单分录IDproperty 
     */
    public String getSourceBillEntryID()
    {
        return getString("SourceBillEntryID");
    }
    public void setSourceBillEntryID(String item)
    {
        setString("SourceBillEntryID", item);
    }
    /**
     * Object:附加项目's 源单单号property 
     */
    public String getSourceBillNumber()
    {
        return getString("SourceBillNumber");
    }
    public void setSourceBillNumber(String item)
    {
        setString("SourceBillNumber", item);
    }
    /**
     * Object:附加项目's 源单行号property 
     */
    public int getSourceBillEntrySeq()
    {
        return getInt("SourceBillEntrySeq");
    }
    public void setSourceBillEntrySeq(int item)
    {
        setInt("SourceBillEntrySeq", item);
    }
    /**
     * Object: 附加项目 's 维修类型 property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo getRepairType()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo)get("repairType");
    }
    public void setRepairType(com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo item)
    {
        put("repairType", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("A0C5DF7C");
    }
}