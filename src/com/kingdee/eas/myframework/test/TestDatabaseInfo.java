package com.kingdee.eas.myframework.test;

import java.io.Serializable;

public class TestDatabaseInfo extends AbstractTestDatabaseInfo implements Serializable 
{
    public TestDatabaseInfo()
    {
        super();
    }
    protected TestDatabaseInfo(String pkField)
    {
        super(pkField);
    }
}