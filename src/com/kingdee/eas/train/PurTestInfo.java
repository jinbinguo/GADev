package com.kingdee.eas.train;

import java.io.Serializable;

public class PurTestInfo extends AbstractPurTestInfo implements Serializable 
{
    public PurTestInfo()
    {
        super();
    }
    protected PurTestInfo(String pkField)
    {
        super(pkField);
    }
}