package com.kingdee.eas.myframework.test;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractTestTreeInfo extends com.kingdee.eas.myframework.template.base.TreeDatabaseInfo implements Serializable 
{
    public AbstractTestTreeInfo()
    {
        this("id");
    }
    protected AbstractTestTreeInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: TestTree 's ¸¸½Úµã property 
     */
    public com.kingdee.eas.myframework.test.TestTreeInfo getParent()
    {
        return (com.kingdee.eas.myframework.test.TestTreeInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.myframework.test.TestTreeInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:TestTree's BIMUDF0001property 
     */
    public String getBIMUDF0001()
    {
        return getString("BIMUDF0001");
    }
    public void setBIMUDF0001(String item)
    {
        setString("BIMUDF0001", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("C0244E38");
    }
}