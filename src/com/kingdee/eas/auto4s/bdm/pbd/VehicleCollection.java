package com.kingdee.eas.auto4s.bdm.pbd;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class VehicleCollection extends AbstractObjectCollection 
{
    public VehicleCollection()
    {
        super(VehicleInfo.class);
    }
    public boolean add(VehicleInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(VehicleCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(VehicleInfo item)
    {
        return removeObject(item);
    }
    public VehicleInfo get(int index)
    {
        return(VehicleInfo)getObject(index);
    }
    public VehicleInfo get(Object key)
    {
        return(VehicleInfo)getObject(key);
    }
    public void set(int index, VehicleInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(VehicleInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(VehicleInfo item)
    {
        return super.indexOf(item);
    }
}