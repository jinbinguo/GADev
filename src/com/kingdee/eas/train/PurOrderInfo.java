package com.kingdee.eas.train;

import java.io.Serializable;

public class PurOrderInfo extends AbstractPurOrderInfo implements Serializable 
{
    public PurOrderInfo()
    {
        super();
    }
    protected PurOrderInfo(String pkField)
    {
        super(pkField);
    }
}