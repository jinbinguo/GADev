package com.kingdee.eas.myframework.template.base;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractSimpleBizBillEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractSimpleBizBillEntryInfo()
    {
        this("");
    }
    protected AbstractSimpleBizBillEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:分录's 行状态property 
     */
    public com.kingdee.eas.scm.common.EntryBaseStatusEnum getLineStatus()
    {
        return com.kingdee.eas.scm.common.EntryBaseStatusEnum.getEnum(getInt("lineStatus"));
    }
    public void setLineStatus(com.kingdee.eas.scm.common.EntryBaseStatusEnum item)
    {
		if (item != null) {
        setInt("lineStatus", item.getValue());
		}
    }
    /**
     * Object:分录's 行备注property 
     */
    public String getLineDesc()
    {
        return getString("lineDesc");
    }
    public void setLineDesc(String item)
    {
        setString("lineDesc", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("F41D60E1");
    }
}