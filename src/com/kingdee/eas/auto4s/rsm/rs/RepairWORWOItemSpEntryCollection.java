package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairWORWOItemSpEntryCollection extends AbstractObjectCollection 
{
    public RepairWORWOItemSpEntryCollection()
    {
        super(RepairWORWOItemSpEntryInfo.class);
    }
    public boolean add(RepairWORWOItemSpEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairWORWOItemSpEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairWORWOItemSpEntryInfo item)
    {
        return removeObject(item);
    }
    public RepairWORWOItemSpEntryInfo get(int index)
    {
        return(RepairWORWOItemSpEntryInfo)getObject(index);
    }
    public RepairWORWOItemSpEntryInfo get(Object key)
    {
        return(RepairWORWOItemSpEntryInfo)getObject(key);
    }
    public void set(int index, RepairWORWOItemSpEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairWORWOItemSpEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairWORWOItemSpEntryInfo item)
    {
        return super.indexOf(item);
    }
}