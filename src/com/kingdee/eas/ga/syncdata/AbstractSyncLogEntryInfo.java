package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractSyncLogEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractSyncLogEntryInfo()
    {
        this("id");
    }
    protected AbstractSyncLogEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 分录 's 单据头 property 
     */
    public com.kingdee.eas.ga.syncdata.SyncLogInfo getParent()
    {
        return (com.kingdee.eas.ga.syncdata.SyncLogInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.ga.syncdata.SyncLogInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:分录's 日志时间property 
     */
    public java.sql.Time getLogTime()
    {
        return getTime("logTime");
    }
    public void setLogTime(java.sql.Time item)
    {
        setTime("logTime", item);
    }
    /**
     * Object:分录's 日志内容property 
     */
    public String getLogContent()
    {
        return getString("logContent");
    }
    public void setLogContent(String item)
    {
        setString("logContent", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("2DA9E1D0");
    }
}