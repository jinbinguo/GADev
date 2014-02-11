package com.kingdee.eas.auto4s.rsm.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairWORWORepairPkgEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractRepairWORWORepairPkgEntryInfo()
    {
        this("id");
    }
    protected AbstractRepairWORWORepairPkgEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 套餐信息 's null property 
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
     * Object: 套餐信息 's 套餐编码 property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairPkgInfo getRepairPkg()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairPkgInfo)get("RepairPkg");
    }
    public void setRepairPkg(com.kingdee.eas.auto4s.bdm.rsm.RepairPkgInfo item)
    {
        put("RepairPkg", item);
    }
    /**
     * Object:套餐信息's 套餐种类property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairPkgTypeEnum getClassify()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.RepairPkgTypeEnum.getEnum(getString("Classify"));
    }
    public void setClassify(com.kingdee.eas.auto4s.bdm.rsm.RepairPkgTypeEnum item)
    {
		if (item != null) {
        setString("Classify", item.getValue());
		}
    }
    /**
     * Object:套餐信息's 生效日期property 
     */
    public java.util.Date getEffectTime()
    {
        return getDate("EffectTime");
    }
    public void setEffectTime(java.util.Date item)
    {
        setDate("EffectTime", item);
    }
    /**
     * Object:套餐信息's 失效日期property 
     */
    public java.util.Date getInvalidTime()
    {
        return getDate("InvalidTime");
    }
    public void setInvalidTime(java.util.Date item)
    {
        setDate("InvalidTime", item);
    }
    /**
     * Object:套餐信息's 套餐原价property 
     */
    public java.math.BigDecimal getOldPrice()
    {
        return getBigDecimal("OldPrice");
    }
    public void setOldPrice(java.math.BigDecimal item)
    {
        setBigDecimal("OldPrice", item);
    }
    /**
     * Object:套餐信息's 套餐现价property 
     */
    public java.math.BigDecimal getPrice()
    {
        return getBigDecimal("Price");
    }
    public void setPrice(java.math.BigDecimal item)
    {
        setBigDecimal("Price", item);
    }
    /**
     * Object:套餐信息's 套餐优惠金额property 
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
     * Object:套餐信息's 源单分录IDproperty 
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
     * Object:套餐信息's 源单行号property 
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
     * Object:套餐信息's 源单IDproperty 
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
     * Object:套餐信息's 源单单号property 
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
     * Object: 套餐信息 's 源单类型 property 
     */
    public com.kingdee.eas.basedata.scm.common.BillTypeInfo getSourceBillType()
    {
        return (com.kingdee.eas.basedata.scm.common.BillTypeInfo)get("SourceBillType");
    }
    public void setSourceBillType(com.kingdee.eas.basedata.scm.common.BillTypeInfo item)
    {
        put("SourceBillType", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("15E83473");
    }
}