package com.kingdee.eas.train;

import java.io.Serializable;

public class PurOrderEntryInfo extends AbstractPurOrderEntryInfo implements Serializable 
{
    public PurOrderEntryInfo()
    {
        super();
    }
    protected PurOrderEntryInfo(String pkField)
    {
        super(pkField);
    }
}