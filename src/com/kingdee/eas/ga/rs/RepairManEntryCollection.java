package com.kingdee.eas.ga.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairManEntryCollection extends AbstractObjectCollection 
{
    public RepairManEntryCollection()
    {
        super(RepairManEntryInfo.class);
    }
    public boolean add(RepairManEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairManEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairManEntryInfo item)
    {
        return removeObject(item);
    }
    public RepairManEntryInfo get(int index)
    {
        return(RepairManEntryInfo)getObject(index);
    }
    public RepairManEntryInfo get(Object key)
    {
        return(RepairManEntryInfo)getObject(key);
    }
    public void set(int index, RepairManEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairManEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairManEntryInfo item)
    {
        return super.indexOf(item);
    }
}