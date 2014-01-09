package com.kingdee.eas.train;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractPurOrderInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractPurOrderInfo()
    {
        this("id");
    }
    protected AbstractPurOrderInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.train.PurOrderEntryCollection());
    }
    /**
     * Object: �ɹ����� 's ��¼ property 
     */
    public com.kingdee.eas.train.PurOrderEntryCollection getEntrys()
    {
        return (com.kingdee.eas.train.PurOrderEntryCollection)get("entrys");
    }
    /**
     * Object:�ɹ�����'s �Ƿ�����ƾ֤property 
     */
    public boolean isFivouchered()
    {
        return getBoolean("Fivouchered");
    }
    public void setFivouchered(boolean item)
    {
        setBoolean("Fivouchered", item);
    }
    /**
     * Object: �ɹ����� 's �ɹ���֯ property 
     */
    public com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo getPurOrg()
    {
        return (com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo)get("purOrg");
    }
    public void setPurOrg(com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo item)
    {
        put("purOrg", item);
    }
    /**
     * Object: �ɹ����� 's ��Ӧ�� property 
     */
    public com.kingdee.eas.basedata.master.cssp.SupplierInfo getSupplier()
    {
        return (com.kingdee.eas.basedata.master.cssp.SupplierInfo)get("supplier");
    }
    public void setSupplier(com.kingdee.eas.basedata.master.cssp.SupplierInfo item)
    {
        put("supplier", item);
    }
    /**
     * Object: �ɹ����� 's �ɹ�Ա property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getPurPerson()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("purPerson");
    }
    public void setPurPerson(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("purPerson", item);
    }
    /**
     * Object:�ɹ�����'s ���ʽproperty 
     */
    public com.kingdee.eas.train.PayType getPayType()
    {
        return com.kingdee.eas.train.PayType.getEnum(getInt("payType"));
    }
    public void setPayType(com.kingdee.eas.train.PayType item)
    {
		if (item != null) {
        setInt("payType", item.getValue());
		}
    }
    /**
     * Object:�ɹ�����'s ״̬property 
     */
    public com.kingdee.eas.scm.common.BillBaseStatusEnum getStatus()
    {
        return com.kingdee.eas.scm.common.BillBaseStatusEnum.getEnum(getInt("status"));
    }
    public void setStatus(com.kingdee.eas.scm.common.BillBaseStatusEnum item)
    {
		if (item != null) {
        setInt("status", item.getValue());
		}
    }
    /**
     * Object:�ɹ�����'s ���ʱ��property 
     */
    public java.util.Date getAuditTime()
    {
        return getDate("auditTime");
    }
    public void setAuditTime(java.util.Date item)
    {
        setDate("auditTime", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("696867B9");
    }
}