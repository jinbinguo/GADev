package com.kingdee.eas.myframework.test;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractTestGroupTreeInfo extends com.kingdee.eas.myframework.template.base.GroupDatabaseTreeInfo implements Serializable 
{
    public AbstractTestGroupTreeInfo()
    {
        this("id");
    }
    protected AbstractTestGroupTreeInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: TestGroup组别 's 父结点 property 
     */
    public com.kingdee.eas.myframework.test.TestGroupTreeInfo getParent()
    {
        return (com.kingdee.eas.myframework.test.TestGroupTreeInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.myframework.test.TestGroupTreeInfo item)
    {
        put("parent", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("DB103C63");
    }
}