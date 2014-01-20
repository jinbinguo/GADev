package com.kingdee.eas.myframework.test;

import java.io.Serializable;

public class AAAInfo extends AbstractAAAInfo implements Serializable 
{
    public AAAInfo()
    {
        super();
    }
    protected AAAInfo(String pkField)
    {
        super(pkField);
    }
}