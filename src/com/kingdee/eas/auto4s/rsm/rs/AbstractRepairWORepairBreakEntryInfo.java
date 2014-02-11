package com.kingdee.eas.auto4s.rsm.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairWORepairBreakEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractRepairWORepairBreakEntryInfo()
    {
        this("id");
    }
    protected AbstractRepairWORepairBreakEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 维修中断 's null property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo getParent()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:维修中断's 中断开始时间property 
     */
    public java.util.Date getStartTime()
    {
        return getDate("StartTime");
    }
    public void setStartTime(java.util.Date item)
    {
        setDate("StartTime", item);
    }
    /**
     * Object:维修中断's 中断结束时间property 
     */
    public java.util.Date getFinishtime()
    {
        return getDate("Finishtime");
    }
    public void setFinishtime(java.util.Date item)
    {
        setDate("Finishtime", item);
    }
    /**
     * Object:维修中断's 中断原因property 
     */
    public String getReason()
    {
        return getString("Reason");
    }
    public void setReason(String item)
    {
        setString("Reason", item);
    }
    /**
     * Object: 维修中断 's 中断类型 property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.BreakTypeInfo getBreakType()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.BreakTypeInfo)get("BreakType");
    }
    public void setBreakType(com.kingdee.eas.auto4s.bdm.rsm.BreakTypeInfo item)
    {
        put("BreakType", item);
    }
    /**
     * Object:维修中断's 备注property 
     */
    public String getRemark()
    {
        return getString("Remark");
    }
    public void setRemark(String item)
    {
        setString("Remark", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("892C9F62");
    }
}