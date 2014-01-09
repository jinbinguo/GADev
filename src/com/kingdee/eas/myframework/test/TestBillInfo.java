package com.kingdee.eas.myframework.test;

import java.io.Serializable;

public class TestBillInfo extends AbstractTestBillInfo implements Serializable 
{
    public TestBillInfo()
    {
        super();
    }
    protected TestBillInfo(String pkField)
    {
        super(pkField);
    }
}