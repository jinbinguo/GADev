package com.kingdee.eas.myframework.test;

import java.io.Serializable;

public class AAAEntryInfo extends AbstractAAAEntryInfo implements Serializable 
{
    public AAAEntryInfo()
    {
        super();
    }
    protected AAAEntryInfo(String pkField)
    {
        super(pkField);
    }
}