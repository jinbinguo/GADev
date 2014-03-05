package com.kingdee.eas.auto4s.bdm.pbd;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class VehicleRepairSenderCollection extends AbstractObjectCollection 
{
    public VehicleRepairSenderCollection()
    {
        super(VehicleRepairSenderInfo.class);
    }
    public boolean add(VehicleRepairSenderInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(VehicleRepairSenderCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(VehicleRepairSenderInfo item)
    {
        return removeObject(item);
    }
    public VehicleRepairSenderInfo get(int index)
    {
        return(VehicleRepairSenderInfo)getObject(index);
    }
    public VehicleRepairSenderInfo get(Object key)
    {
        return(VehicleRepairSenderInfo)getObject(key);
    }
    public void set(int index, VehicleRepairSenderInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(VehicleRepairSenderInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(VehicleRepairSenderInfo item)
    {
        return super.indexOf(item);
    }
}