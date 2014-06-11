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
     * Object: ͬ����־��ѯ 's ��¼ property 
     */
    public com.kingdee.eas.ga.syncdata.SyncLogEntryCollection getEntrys()
    {
        return (com.kingdee.eas.ga.syncdata.SyncLogEntryCollection)get("entrys");
    }
    /**
     * Object:ͬ����־��ѯ's �����ļ�����property 
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
     * Object:ͬ����־��ѯ's ����״̬property 
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
     * Object:ͬ����־��ѯ's ������Դproperty 
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