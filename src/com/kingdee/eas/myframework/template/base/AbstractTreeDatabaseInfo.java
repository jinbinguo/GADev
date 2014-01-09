package com.kingdee.eas.myframework.template.base;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractTreeDatabaseInfo extends com.kingdee.eas.framework.TreeBaseInfo implements Serializable 
{
    public AbstractTreeDatabaseInfo()
    {
        this("");
    }
    protected AbstractTreeDatabaseInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:树型基础资料's 系统预设property 
     */
    public boolean isScheduled()
    {
        return getBoolean("scheduled");
    }
    public void setScheduled(boolean item)
    {
        setBoolean("scheduled", item);
    }
    /**
     * Object:树型基础资料's 状态property 
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
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("672D2DB0");
    }
}