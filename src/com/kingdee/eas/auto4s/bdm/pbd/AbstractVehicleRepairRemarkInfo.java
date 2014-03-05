package com.kingdee.eas.auto4s.bdm.pbd;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractVehicleRepairRemarkInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractVehicleRepairRemarkInfo()
    {
        this("id");
    }
    protected AbstractVehicleRepairRemarkInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 维修备注 's null property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo getParent()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:维修备注's 备注property 
     */
    public String getRemark()
    {
        return getString("remark");
    }
    public void setRemark(String item)
    {
        setString("remark", item);
    }
    /**
     * Object: 维修备注 's 维修工单 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo getRepairWO()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo)get("repairWO");
    }
    public void setRepairWO(com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo item)
    {
        put("repairWO", item);
    }
    /**
     * Object:维修备注's 创建日期property 
     */
    public java.util.Date getCreateTime()
    {
        return getDate("createTime");
    }
    public void setCreateTime(java.util.Date item)
    {
        setDate("createTime", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("73ACC552");
    }
}