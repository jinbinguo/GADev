package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class DMSWipBillEntry2Collection extends AbstractObjectCollection 
{
    public DMSWipBillEntry2Collection()
    {
        super(DMSWipBillEntry2Info.class);
    }
    public boolean add(DMSWipBillEntry2Info item)
    {
        return addObject(item);
    }
    public boolean addCollection(DMSWipBillEntry2Collection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(DMSWipBillEntry2Info item)
    {
        return removeObject(item);
    }
    public DMSWipBillEntry2Info get(int index)
    {
        return(DMSWipBillEntry2Info)getObject(index);
    }
    public DMSWipBillEntry2Info get(Object key)
    {
        return(DMSWipBillEntry2Info)getObject(key);
    }
    public void set(int index, DMSWipBillEntry2Info item)
    {
        setObject(index, item);
    }
    public boolean contains(DMSWipBillEntry2Info item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(DMSWipBillEntry2Info item)
    {
        return super.indexOf(item);
    }
}