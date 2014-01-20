package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractDMSInOutQueryInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillInfo implements Serializable 
{
    public AbstractDMSInOutQueryInfo()
    {
        this("id");
    }
    protected AbstractDMSInOutQueryInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryCollection());
    }
    /**
     * Object: DMS交易查询 's 分录 property 
     */
    public com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryCollection getEntrys()
    {
        return (com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryCollection)get("entrys");
    }
    /**
     * Object: DMS交易查询 's 公司 property 
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
        return new BOSObjectType("9FEBF162");
    }
}