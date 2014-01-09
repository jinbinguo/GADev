package com.kingdee.eas.train;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractPurTestInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractPurTestInfo()
    {
        this("id");
    }
    protected AbstractPurTestInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.train.PurTestEntryCollection());
    }
    /**
     * Object: �ɹ����� 's ��¼ property 
     */
    public com.kingdee.eas.train.PurTestEntryCollection getEntrys()
    {
        return (com.kingdee.eas.train.PurTestEntryCollection)get("entrys");
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
     * Object: �ɹ����� 's ������ property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getRequestPerson()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("requestPerson");
    }
    public void setRequestPerson(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("requestPerson", item);
    }
    /**
     * Object: �ɹ����� 's ���벿�� property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getRequestDept()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("requestDept");
    }
    public void setRequestDept(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("requestDept", item);
    }
    /**
     * Object:�ɹ�����'s �������property 
     */
    public java.util.Date getAuditDate()
    {
        return getDate("auditDate");
    }
    public void setAuditDate(java.util.Date item)
    {
        setDate("auditDate", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("D1DC2AC7");
    }
}