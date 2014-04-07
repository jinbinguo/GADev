package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractDMSPrintContentEntryInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillEntryInfo implements Serializable 
{
    public AbstractDMSPrintContentEntryInfo()
    {
        this("id");
    }
    protected AbstractDMSPrintContentEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 分录 's 单据头 property 
     */
    public com.kingdee.eas.ga.syncdata.DMSPrintContentInfo getParent()
    {
        return (com.kingdee.eas.ga.syncdata.DMSPrintContentInfo)get("Parent");
    }
    public void setParent(com.kingdee.eas.ga.syncdata.DMSPrintContentInfo item)
    {
        put("Parent", item);
    }
    /**
     * Object:分录's 内容property 
     */
    public String getContent()
    {
        return getString("content");
    }
    public void setContent(String item)
    {
        setString("content", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("5C9DBE63");
    }
}