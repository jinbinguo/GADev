package com.kingdee.eas.ga.basedata;

import java.io.Serializable;

public class CustomerDiscountInfo extends AbstractCustomerDiscountInfo implements Serializable 
{
    public CustomerDiscountInfo()
    {
        super();
    }
    protected CustomerDiscountInfo(String pkField)
    {
        super(pkField);
    }
}