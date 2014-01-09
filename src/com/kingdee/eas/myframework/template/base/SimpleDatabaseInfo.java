package com.kingdee.eas.myframework.template.base;

import java.io.Serializable;

public class SimpleDatabaseInfo extends AbstractSimpleDatabaseInfo implements Serializable 
{
    public SimpleDatabaseInfo()
    {
        super();
    }
    protected SimpleDatabaseInfo(String pkField)
    {
        super(pkField);
    }
}