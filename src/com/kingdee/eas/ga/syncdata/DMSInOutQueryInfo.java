package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;

public class DMSInOutQueryInfo extends AbstractDMSInOutQueryInfo implements Serializable 
{
    public DMSInOutQueryInfo()
    {
        super();
    }
    protected DMSInOutQueryInfo(String pkField)
    {
        super(pkField);
    }
}