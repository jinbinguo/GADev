package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractDMSWipNoForStocktakingInfo extends com.kingdee.eas.framework.CoreBaseInfo implements Serializable 
{
    public AbstractDMSWipNoForStocktakingInfo()
    {
        this("id");
    }
    protected AbstractDMSWipNoForStocktakingInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:DMS盘点WIP号's 年份WIP号property 
     */
    public String getKeyWip()
    {
        return getString("keyWip");
    }
    public void setKeyWip(String item)
    {
        setString("keyWip", item);
    }
    /**
     * Object: DMS盘点WIP号 's 公司 property 
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
        return new BOSObjectType("18AD6E0D");
    }
}