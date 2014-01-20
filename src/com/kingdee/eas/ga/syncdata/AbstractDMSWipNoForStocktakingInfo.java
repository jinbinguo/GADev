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
     * Object:DMS�̵�WIP��'s ���WIP��property 
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
     * Object: DMS�̵�WIP�� 's ��˾ property 
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