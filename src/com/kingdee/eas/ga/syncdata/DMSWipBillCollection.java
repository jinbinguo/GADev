package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class DMSWipBillCollection extends AbstractObjectCollection 
{
    public DMSWipBillCollection()
    {
        super(DMSWipBillInfo.class);
    }
    public boolean add(DMSWipBillInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(DMSWipBillCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(DMSWipBillInfo item)
    {
        return removeObject(item);
    }
    public DMSWipBillInfo get(int index)
    {
        return(DMSWipBillInfo)getObject(index);
    }
    public DMSWipBillInfo get(Object key)
    {
        return(DMSWipBillInfo)getObject(key);
    }
    public void set(int index, DMSWipBillInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(DMSWipBillInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(DMSWipBillInfo item)
    {
        return super.indexOf(item);
    }
}