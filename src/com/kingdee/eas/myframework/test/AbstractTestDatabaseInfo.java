package com.kingdee.eas.myframework.test;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractTestDatabaseInfo extends com.kingdee.eas.myframework.template.base.SimpleDatabaseInfo implements Serializable 
{
    public AbstractTestDatabaseInfo()
    {
        this("id");
    }
    protected AbstractTestDatabaseInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:TestDatabase's test1property 
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
        return new BOSObjectType("74940F15");
    }
}