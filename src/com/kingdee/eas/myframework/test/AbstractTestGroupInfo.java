package com.kingdee.eas.myframework.test;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractTestGroupInfo extends com.kingdee.eas.myframework.template.base.GroupDatabaseInfo implements Serializable 
{
    public AbstractTestGroupInfo()
    {
        this("id");
    }
    protected AbstractTestGroupInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: TestGroup 's ×é±ð property 
     */
    public com.kingdee.eas.myframework.test.TestGroupTreeInfo getTreeid()
    {
        return (com.kingdee.eas.myframework.test.TestGroupTreeInfo)get("treeid");
    }
    public void setTreeid(com.kingdee.eas.myframework.test.TestGroupTreeInfo item)
    {
        put("treeid", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("43AE6F25");
    }
}