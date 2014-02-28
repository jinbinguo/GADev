package com.kingdee.eas.ga.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairPackageCollection extends AbstractObjectCollection 
{
    public RepairPackageCollection()
    {
        super(RepairPackageInfo.class);
    }
    public boolean add(RepairPackageInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairPackageCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairPackageInfo item)
    {
        return removeObject(item);
    }
    public RepairPackageInfo get(int index)
    {
        return(RepairPackageInfo)getObject(index);
    }
    public RepairPackageInfo get(Object key)
    {
        return(RepairPackageInfo)getObject(key);
    }
    public void set(int index, RepairPackageInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairPackageInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairPackageInfo item)
    {
        return super.indexOf(item);
    }
}