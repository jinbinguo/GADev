package com.kingdee.eas.myframework.test;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractTestBillInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillInfo implements Serializable 
{
    public AbstractTestBillInfo()
    {
        this("id");
    }
    protected AbstractTestBillInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.myframework.test.TestBillEntryCollection());
    }
    /**
     * Object: TestBill 's ·ÖÂ¼ property 
     */
    public com.kingdee.eas.myframework.test.TestBillEntryCollection getEntrys()
    {
        return (com.kingdee.eas.myframework.test.TestBillEntryCollection)get("entrys");
    }
    /**
     * Object:TestBill's Test1property 
     */
    public String getBIMUDF0001()
    {
        return getString("BIMUDF0001");
    }
    public void setBIMUDF0001(String item)
    {
        setString("BIMUDF0001", item);
    }
    /**
     * Object:TestBill's ±¸×¢property 
     */
    public String getBIMUDF0005()
    {
        return getString("BIMUDF0005");
    }
    public void setBIMUDF0005(String item)
    {
        setString("BIMUDF0005", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("C01BFEA1");
    }
}