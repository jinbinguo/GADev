package com.kingdee.eas.myframework.test;

import java.io.Serializable;

public class TestTreeInfo extends AbstractTestTreeInfo implements Serializable 
{
    public TestTreeInfo()
    {
        super();
    }
    protected TestTreeInfo(String pkField)
    {
        super(pkField);
    }
}