package com.kingdee.eas.myframework.test;

import java.io.Serializable;

public class TestGroupInfo extends AbstractTestGroupInfo implements Serializable 
{
    public TestGroupInfo()
    {
        super();
    }
    protected TestGroupInfo(String pkField)
    {
        super(pkField);
    }
}