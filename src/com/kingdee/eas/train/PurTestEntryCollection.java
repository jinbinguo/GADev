package com.kingdee.eas.train;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class PurTestEntryCollection extends AbstractObjectCollection 
{
    public PurTestEntryCollection()
    {
        super(PurTestEntryInfo.class);
    }
    public boolean add(PurTestEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(PurTestEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(PurTestEntryInfo item)
    {
        return removeObject(item);
    }
    public PurTestEntryInfo get(int index)
    {
        return(PurTestEntryInfo)getObject(index);
    }
    public PurTestEntryInfo get(Object key)
    {
        return(PurTestEntryInfo)getObject(key);
    }
    public void set(int index, PurTestEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(PurTestEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(PurTestEntryInfo item)
    {
        return super.indexOf(item);
    }
}