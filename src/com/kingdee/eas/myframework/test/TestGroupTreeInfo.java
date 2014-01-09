package com.kingdee.eas.myframework.test;

import java.io.Serializable;

public class TestGroupTreeInfo extends AbstractTestGroupTreeInfo implements Serializable 
{
    public TestGroupTreeInfo()
    {
        super();
    }
    protected TestGroupTreeInfo(String pkField)
    {
        super(pkField);
    }
}