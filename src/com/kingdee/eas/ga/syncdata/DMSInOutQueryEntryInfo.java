package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;

public class DMSInOutQueryEntryInfo extends AbstractDMSInOutQueryEntryInfo implements Serializable 
{
    public DMSInOutQueryEntryInfo()
    {
        super();
    }
    protected DMSInOutQueryEntryInfo(String pkField)
    {
        super(pkField);
    }
}