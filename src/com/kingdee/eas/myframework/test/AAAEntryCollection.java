package com.kingdee.eas.myframework.test;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class AAAEntryCollection extends AbstractObjectCollection 
{
    public AAAEntryCollection()
    {
        super(AAAEntryInfo.class);
    }
    public boolean add(AAAEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(AAAEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(AAAEntryInfo item)
    {
        return removeObject(item);
    }
    public AAAEntryInfo get(int index)
    {
        return(AAAEntryInfo)getObject(index);
    }
    public AAAEntryInfo get(Object key)
    {
        return(AAAEntryInfo)getObject(key);
    }
    public void set(int index, AAAEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(AAAEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(AAAEntryInfo item)
    {
        return super.indexOf(item);
    }
}