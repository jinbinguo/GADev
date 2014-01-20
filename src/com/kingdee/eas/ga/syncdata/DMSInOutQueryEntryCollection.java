package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class DMSInOutQueryEntryCollection extends AbstractObjectCollection 
{
    public DMSInOutQueryEntryCollection()
    {
        super(DMSInOutQueryEntryInfo.class);
    }
    public boolean add(DMSInOutQueryEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(DMSInOutQueryEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(DMSInOutQueryEntryInfo item)
    {
        return removeObject(item);
    }
    public DMSInOutQueryEntryInfo get(int index)
    {
        return(DMSInOutQueryEntryInfo)getObject(index);
    }
    public DMSInOutQueryEntryInfo get(Object key)
    {
        return(DMSInOutQueryEntryInfo)getObject(key);
    }
    public void set(int index, DMSInOutQueryEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(DMSInOutQueryEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(DMSInOutQueryEntryInfo item)
    {
        return super.indexOf(item);
    }
}