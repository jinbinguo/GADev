package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;

public class SyncLogInfo extends AbstractSyncLogInfo implements Serializable 
{
    public SyncLogInfo()
    {
        super();
    }
    protected SyncLogInfo(String pkField)
    {
        super(pkField);
    }
}