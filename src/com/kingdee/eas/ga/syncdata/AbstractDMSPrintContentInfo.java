package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractDMSPrintContentInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillInfo implements Serializable 
{
    public AbstractDMSPrintContentInfo()
    {
        this("id");
    }
    protected AbstractDMSPrintContentInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.ga.syncdata.DMSPrintContentEntryCollection());
    }
    /**
     * Object: DMS打印内容 's 分录 property 
     */
    public com.kingdee.eas.ga.syncdata.DMSPrintContentEntryCollection getEntrys()
    {
        return (com.kingdee.eas.ga.syncdata.DMSPrintContentEntryCollection)get("entrys");
    }
    /**
     * Object: DMS打印内容 's 公司 property 
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
        return new BOSObjectType("B165134F");
    }
}