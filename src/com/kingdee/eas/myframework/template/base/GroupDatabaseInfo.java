package com.kingdee.eas.myframework.template.base;

import java.io.Serializable;

public class GroupDatabaseInfo extends AbstractGroupDatabaseInfo implements Serializable 
{
    public GroupDatabaseInfo()
    {
        super();
    }
    protected GroupDatabaseInfo(String pkField)
    {
        super(pkField);
    }
}