package com.kingdee.eas.train;

import java.io.Serializable;

public class PurTestEntryInfo extends AbstractPurTestEntryInfo implements Serializable 
{
    public PurTestEntryInfo()
    {
        super();
    }
    protected PurTestEntryInfo(String pkField)
    {
        super(pkField);
    }
}