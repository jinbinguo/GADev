package com.kingdee.eas.train;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractPurRequestInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractPurRequestInfo()
    {
        this("id");
    }
    protected AbstractPurRequestInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.train.PurRequestEntryCollection());
    }
    /**
     * Object: 采购申请单 's 分录 property 
     */
    public com.kingdee.eas.train.PurRequestEntryCollection getEntrys()
    {
        return (com.kingdee.eas.train.PurRequestEntryCollection)get("entrys");
    }
    /**
     * Object:采购申请单's 是否生成凭证property 
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
     * Object: 采购申请单 's 采购组织 property 
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
     * Object: 采购申请单 's 供应商 property 
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
     * Object: 采购申请单 's 申请部门 property 
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
     * Object: 采购申请单 's 申请人 property 
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
     * Object:采购申请单's 状态property 
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
     * Object:采购申请单's 审核日期property 
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
        return new BOSObjectType("3A2F643A");
    }
}