package com.kingdee.eas.myframework.test;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractTestBillEntryInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillEntryInfo implements Serializable 
{
    public AbstractTestBillEntryInfo()
    {
        this("id");
    }
    protected AbstractTestBillEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 分录 's 单据头 property 
     */
    public com.kingdee.eas.myframework.test.TestBillInfo getParent()
    {
        return (com.kingdee.eas.myframework.test.TestBillInfo)get("Parent");
    }
    public void setParent(com.kingdee.eas.myframework.test.TestBillInfo item)
    {
        put("Parent", item);
    }
    /**
     * Object:分录's Test2property 
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
        return new BOSObjectType("9E6F1651");
    }
}