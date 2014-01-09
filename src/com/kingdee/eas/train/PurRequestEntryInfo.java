package com.kingdee.eas.train;

import java.io.Serializable;

public class PurRequestEntryInfo extends AbstractPurRequestEntryInfo implements Serializable 
{
    public PurRequestEntryInfo()
    {
        super();
    }
    protected PurRequestEntryInfo(String pkField)
    {
        super(pkField);
    }
}