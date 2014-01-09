package com.kingdee.eas.train;

import java.io.Serializable;

public class PurRequestInfo extends AbstractPurRequestInfo implements Serializable 
{
    public PurRequestInfo()
    {
        super();
    }
    protected PurRequestInfo(String pkField)
    {
        super(pkField);
    }
}