package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class DMSWipBillEntry3Collection extends AbstractObjectCollection 
{
    public DMSWipBillEntry3Collection()
    {
        super(DMSWipBillEntry3Info.class);
    }
    public boolean add(DMSWipBillEntry3Info item)
    {
        return addObject(item);
    }
    public boolean addCollection(DMSWipBillEntry3Collection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(DMSWipBillEntry3Info item)
    {
        return removeObject(item);
    }
    public DMSWipBillEntry3Info get(int index)
    {
        return(DMSWipBillEntry3Info)getObject(index);
    }
    public DMSWipBillEntry3Info get(Object key)
    {
        return(DMSWipBillEntry3Info)getObject(key);
    }
    public void set(int index, DMSWipBillEntry3Info item)
    {
        setObject(index, item);
    }
    public boolean contains(DMSWipBillEntry3Info item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(DMSWipBillEntry3Info item)
    {
        return super.indexOf(item);
    }
}