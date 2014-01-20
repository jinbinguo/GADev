package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class DMSWipBillEntryCollection extends AbstractObjectCollection 
{
    public DMSWipBillEntryCollection()
    {
        super(DMSWipBillEntryInfo.class);
    }
    public boolean add(DMSWipBillEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(DMSWipBillEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(DMSWipBillEntryInfo item)
    {
        return removeObject(item);
    }
    public DMSWipBillEntryInfo get(int index)
    {
        return(DMSWipBillEntryInfo)getObject(index);
    }
    public DMSWipBillEntryInfo get(Object key)
    {
        return(DMSWipBillEntryInfo)getObject(key);
    }
    public void set(int index, DMSWipBillEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(DMSWipBillEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(DMSWipBillEntryInfo item)
    {
        return super.indexOf(item);
    }
}