package com.kingdee.eas.myframework.test;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractAAAEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractAAAEntryInfo()
    {
        this("id");
    }
    protected AbstractAAAEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 分录 's 单据头 property 
     */
    public com.kingdee.eas.myframework.test.AAAInfo getParent()
    {
        return (com.kingdee.eas.myframework.test.AAAInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.myframework.test.AAAInfo item)
    {
        put("parent", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("4230DF99");
    }
}