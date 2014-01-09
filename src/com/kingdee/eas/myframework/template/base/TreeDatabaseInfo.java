package com.kingdee.eas.myframework.template.base;

import java.io.Serializable;

public class TreeDatabaseInfo extends AbstractTreeDatabaseInfo implements Serializable 
{
    public TreeDatabaseInfo()
    {
        super();
    }
    protected TreeDatabaseInfo(String pkField)
    {
        super(pkField);
    }
}