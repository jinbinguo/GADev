package com.kingdee.eas.train;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class PurRequestEntryCollection extends AbstractObjectCollection 
{
    public PurRequestEntryCollection()
    {
        super(PurRequestEntryInfo.class);
    }
    public boolean add(PurRequestEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(PurRequestEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(PurRequestEntryInfo item)
    {
        return removeObject(item);
    }
    public PurRequestEntryInfo get(int index)
    {
        return(PurRequestEntryInfo)getObject(index);
    }
    public PurRequestEntryInfo get(Object key)
    {
        return(PurRequestEntryInfo)getObject(key);
    }
    public void set(int index, PurRequestEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(PurRequestEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(PurRequestEntryInfo item)
    {
        return super.indexOf(item);
    }
}