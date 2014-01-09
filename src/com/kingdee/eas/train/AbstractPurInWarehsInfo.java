package com.kingdee.eas.train;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractPurInWarehsInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractPurInWarehsInfo()
    {
        this("id");
    }
    protected AbstractPurInWarehsInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.train.PurInWarehsEntryCollection());
    }
    /**
     * Object: �ɹ���ⵥ 's ��¼ property 
     */
    public com.kingdee.eas.train.PurInWarehsEntryCollection getEntrys()
    {
        return (com.kingdee.eas.train.PurInWarehsEntryCollection)get("entrys");
    }
    /**
     * Object:�ɹ���ⵥ's �Ƿ�����ƾ֤property 
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
     * Object: �ɹ���ⵥ 's �ɹ���֯ property 
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
     * Object: �ɹ���ⵥ 's ��Ӧ�� property 
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
     * Object: �ɹ���ⵥ 's �ɹ�Ա property 
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
     * Object:�ɹ���ⵥ's ���ʽproperty 
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
     * Object:�ɹ���ⵥ's ���ʱ��property 
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
        return new BOSObjectType("13788182");
    }
}