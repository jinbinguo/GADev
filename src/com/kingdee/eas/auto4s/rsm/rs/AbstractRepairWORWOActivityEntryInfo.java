package com.kingdee.eas.auto4s.rsm.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairWORWOActivityEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractRepairWORWOActivityEntryInfo()
    {
        this("id");
    }
    protected AbstractRepairWORWOActivityEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: ���� 's null property 
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
     * Object: ���� 's ����� property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.ServiceActivityInfo getServiceActivity()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.ServiceActivityInfo)get("ServiceActivity");
    }
    public void setServiceActivity(com.kingdee.eas.auto4s.rsm.rs.ServiceActivityInfo item)
    {
        put("ServiceActivity", item);
    }
    /**
     * Object:����'s �����property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.ServiceActivityEnum getActivityType()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.ServiceActivityEnum.getEnum(getString("ActivityType"));
    }
    public void setActivityType(com.kingdee.eas.auto4s.bdm.rsm.ServiceActivityEnum item)
    {
		if (item != null) {
        setString("ActivityType", item.getValue());
		}
    }
    /**
     * Object:����'s ���ʼ����property 
     */
    public java.util.Date getBeginTime()
    {
        return getDate("BeginTime");
    }
    public void setBeginTime(java.util.Date item)
    {
        setDate("BeginTime", item);
    }
    /**
     * Object:����'s ���������property 
     */
    public java.util.Date getFinishTime()
    {
        return getDate("FinishTime");
    }
    public void setFinishTime(java.util.Date item)
    {
        setDate("FinishTime", item);
    }
    /**
     * Object:����'s ������ܼ�property 
     */
    public java.math.BigDecimal getFeeTotalAmount()
    {
        return getBigDecimal("FeeTotalAmount");
    }
    public void setFeeTotalAmount(java.math.BigDecimal item)
    {
        setBigDecimal("FeeTotalAmount", item);
    }
    /**
     * Object:����'s Դ����¼IDproperty 
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
     * Object:����'s Դ���к�property 
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
     * Object:����'s Դ��IDproperty 
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
     * Object:����'s Դ������property 
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
     * Object: ���� 's Դ������ property 
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
        return new BOSObjectType("3572D6E3");
    }
}