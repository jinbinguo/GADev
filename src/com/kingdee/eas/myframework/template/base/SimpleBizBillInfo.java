package com.kingdee.eas.myframework.template.base;

import java.io.Serializable;

public class SimpleBizBillInfo extends AbstractSimpleBizBillInfo implements Serializable 
{
    public SimpleBizBillInfo()
    {
        super();
    }
    protected SimpleBizBillInfo(String pkField)
    {
        super(pkField);
    }
}