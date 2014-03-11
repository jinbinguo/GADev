package com.kingdee.eas.ga.basedata;

import java.io.Serializable;

public class CustomerDiscountEntryInfo extends AbstractCustomerDiscountEntryInfo implements Serializable 
{
    public CustomerDiscountEntryInfo()
    {
        super();
    }
    protected CustomerDiscountEntryInfo(String pkField)
    {
        super(pkField);
    }
}