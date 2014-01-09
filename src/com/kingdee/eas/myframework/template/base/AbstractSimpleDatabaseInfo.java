package com.kingdee.eas.myframework.template.base;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractSimpleDatabaseInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractSimpleDatabaseInfo()
    {
        this("id");
    }
    protected AbstractSimpleDatabaseInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:简单基本资料's 状态property 
     */
    public com.kingdee.eas.basedata.ncm.DeletedStatusEnum getDeletedStatus()
    {
        return com.kingdee.eas.basedata.ncm.DeletedStatusEnum.getEnum(getInt("deletedStatus"));
    }
    public void setDeletedStatus(com.kingdee.eas.basedata.ncm.DeletedStatusEnum item)
    {
		if (item != null) {
        setInt("deletedStatus", item.getValue());
		}
    }
    /**
     * Object:简单基本资料's 系统预设property 
     */
    public boolean isScheduled()
    {
        return getBoolean("scheduled");
    }
    public void setScheduled(boolean item)
    {
        setBoolean("scheduled", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("D082D604");
    }
}