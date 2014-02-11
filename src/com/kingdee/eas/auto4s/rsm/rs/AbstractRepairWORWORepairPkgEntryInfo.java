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
     * Object: �ײ���Ϣ 's null property 
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
     * Object: �ײ���Ϣ 's �ײͱ��� property 
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
     * Object:�ײ���Ϣ's �ײ�����property 
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
     * Object:�ײ���Ϣ's ��Ч����property 
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
     * Object:�ײ���Ϣ's ʧЧ����property 
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
     * Object:�ײ���Ϣ's �ײ�ԭ��property 
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
     * Object:�ײ���Ϣ's �ײ��ּ�property 
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
     * Object:�ײ���Ϣ's �ײ��Żݽ��property 
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
     * Object:�ײ���Ϣ's Դ����¼IDproperty 
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
     * Object:�ײ���Ϣ's Դ���к�property 
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
     * Object:�ײ���Ϣ's Դ��IDproperty 
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
     * Object:�ײ���Ϣ's Դ������property 
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
     * Object: �ײ���Ϣ 's Դ������ property 
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