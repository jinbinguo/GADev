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
     * Object: 采购订单 's 分录 property 
     */
    public com.kingdee.eas.train.PurOrderEntryCollection getEntrys()
    {
        return (com.kingdee.eas.train.PurOrderEntryCollection)get("entrys");
    }
    /**
     * Object:采购订单's 是否生成凭证property 
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
     * Object: 采购订单 's 采购组织 property 
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
     * Object: 采购订单 's 供应商 property 
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
     * Object: 采购订单 's 采购员 property 
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
     * Object:采购订单's 付款方式property 
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
     * Object:采购订单's 状态property 
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
     * Object:采购订单's 审核时间property 
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