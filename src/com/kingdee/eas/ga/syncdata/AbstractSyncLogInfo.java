package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractSyncLogInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractSyncLogInfo()
    {
        this("id");
    }
    protected AbstractSyncLogInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.ga.syncdata.SyncLogEntryCollection());
    }
    /**
     * Object: 同步日志查询 's 分录 property 
     */
    public com.kingdee.eas.ga.syncdata.SyncLogEntryCollection getEntrys()
    {
        return (com.kingdee.eas.ga.syncdata.SyncLogEntryCollection)get("entrys");
    }
    /**
     * Object:同步日志查询's 引入文件名称property 
     */
    public String getExcelFilename()
    {
        return getString("excelFilename");
    }
    public void setExcelFilename(String item)
    {
        setString("excelFilename", item);
    }
    /**
     * Object:同步日志查询's 引入状态property 
     */
    public com.kingdee.eas.ga.syncdata.ImportStateEnum getImportState()
    {
        return com.kingdee.eas.ga.syncdata.ImportStateEnum.getEnum(getString("importState"));
    }
    public void setImportState(com.kingdee.eas.ga.syncdata.ImportStateEnum item)
    {
		if (item != null) {
        setString("importState", item.getValue());
		}
    }
    /**
     * Object:同步日志查询's 引入来源property 
     */
    public String getImportOrg()
    {
        return getString("importOrg");
    }
    public void setImportOrg(String item)
    {
        setString("importOrg", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("2BA1BFC2");
    }
}