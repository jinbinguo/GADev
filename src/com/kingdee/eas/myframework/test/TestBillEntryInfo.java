package com.kingdee.eas.myframework.test;

import java.io.Serializable;

public class TestBillEntryInfo extends AbstractTestBillEntryInfo implements Serializable 
{
    public TestBillEntryInfo()
    {
        super();
    }
    protected TestBillEntryInfo(String pkField)
    {
        super(pkField);
    }
}