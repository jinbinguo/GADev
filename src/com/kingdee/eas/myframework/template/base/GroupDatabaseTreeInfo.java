package com.kingdee.eas.myframework.template.base;

import java.io.Serializable;

public class GroupDatabaseTreeInfo extends AbstractGroupDatabaseTreeInfo implements Serializable 
{
    public GroupDatabaseTreeInfo()
    {
        super();
    }
    protected GroupDatabaseTreeInfo(String pkField)
    {
        super(pkField);
    }
}