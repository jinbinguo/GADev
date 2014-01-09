package com.kingdee.eas.myframework.template.base;

import java.io.Serializable;

public class SimpleBizBillEntryInfo extends AbstractSimpleBizBillEntryInfo implements Serializable 
{
    public SimpleBizBillEntryInfo()
    {
        super();
    }
    protected SimpleBizBillEntryInfo(String pkField)
    {
        super(pkField);
    }
}