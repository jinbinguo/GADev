package com.kingdee.eas.auto4s.bdm.pbd;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class VehicleRepairRemarkCollection extends AbstractObjectCollection 
{
    public VehicleRepairRemarkCollection()
    {
        super(VehicleRepairRemarkInfo.class);
    }
    public boolean add(VehicleRepairRemarkInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(VehicleRepairRemarkCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(VehicleRepairRemarkInfo item)
    {
        return removeObject(item);
    }
    public VehicleRepairRemarkInfo get(int index)
    {
        return(VehicleRepairRemarkInfo)getObject(index);
    }
    public VehicleRepairRemarkInfo get(Object key)
    {
        return(VehicleRepairRemarkInfo)getObject(key);
    }
    public void set(int index, VehicleRepairRemarkInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(VehicleRepairRemarkInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(VehicleRepairRemarkInfo item)
    {
        return super.indexOf(item);
    }
}