package com.kingdee.eas.ga.rs;

import java.io.Serializable;

public class WInfo extends AbstractWInfo implements Serializable 
{
    public WInfo()
    {
        super();
    }
    protected WInfo(String pkField)
    {
        super(pkField);
    }
}