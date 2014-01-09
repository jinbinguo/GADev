package com.kingdee.eas.myframework.template.base;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractGroupDatabaseInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractGroupDatabaseInfo()
    {
        this("");
    }
    protected AbstractGroupDatabaseInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:分组基础资料's 状态property 
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
     * Object:分组基础资料's 系统预设property 
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
        return new BOSObjectType("61E00983");
    }
}