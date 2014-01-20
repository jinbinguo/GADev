package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractDMSWipBillInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillInfo implements Serializable 
{
    public AbstractDMSWipBillInfo()
    {
        this("id");
    }
    protected AbstractDMSWipBillInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.ga.syncdata.DMSWipBillEntryCollection());
        put("entry3", new com.kingdee.eas.ga.syncdata.DMSWipBillEntry3Collection());
        put("entry2", new com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Collection());
    }
    /**
     * Object: DMSWIP单 's Wip表头 property 
     */
    public com.kingdee.eas.ga.syncdata.DMSWipBillEntryCollection getEntrys()
    {
        return (com.kingdee.eas.ga.syncdata.DMSWipBillEntryCollection)get("entrys");
    }
    /**
     * Object: DMSWIP单 's 工时行 property 
     */
    public com.kingdee.eas.ga.syncdata.DMSWipBillEntry3Collection getEntry3()
    {
        return (com.kingdee.eas.ga.syncdata.DMSWipBillEntry3Collection)get("entry3");
    }
    /**
     * Object: DMSWIP单 's 零件行 property 
     */
    public com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Collection getEntry2()
    {
        return (com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Collection)get("entry2");
    }
    /**
     * Object: DMSWIP单 's 公司 property 
     */
    public com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo getServiceOrgUnit()
    {
        return (com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo)get("serviceOrgUnit");
    }
    public void setServiceOrgUnit(com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo item)
    {
        put("serviceOrgUnit", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("71CA40E2");
    }
}