package com.kingdee.eas.ga.rs;

import java.io.Serializable;

public class CustomerAccountInfo extends AbstractCustomerAccountInfo implements Serializable 
{
    public CustomerAccountInfo()
    {
        super();
    }
    protected CustomerAccountInfo(String pkField)
    {
        super(pkField);
    }
}