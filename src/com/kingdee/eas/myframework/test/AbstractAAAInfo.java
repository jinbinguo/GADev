package com.kingdee.eas.myframework.test;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractAAAInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractAAAInfo()
    {
        this("id");
    }
    protected AbstractAAAInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.myframework.test.AAAEntryCollection());
    }
    /**
     * Object: AAA 's 分录 property 
     */
    public com.kingdee.eas.myframework.test.AAAEntryCollection getEntrys()
    {
        return (com.kingdee.eas.myframework.test.AAAEntryCollection)get("entrys");
    }
    /**
     * Object:AAA's 是否生成凭证property 
     */
    public boolean isFivouchered()
    {
        return getBoolean("Fivouchered");
    }
    public void setFivouchered(boolean item)
    {
        setBoolean("Fivouchered", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("9AA38859");
    }
}