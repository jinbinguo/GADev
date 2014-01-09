package com.kingdee.eas.train;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class PurTestCollection extends AbstractObjectCollection 
{
    public PurTestCollection()
    {
        super(PurTestInfo.class);
    }
    public boolean add(PurTestInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(PurTestCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(PurTestInfo item)
    {
        return removeObject(item);
    }
    public PurTestInfo get(int index)
    {
        return(PurTestInfo)getObject(index);
    }
    public PurTestInfo get(Object key)
    {
        return(PurTestInfo)getObject(key);
    }
    public void set(int index, PurTestInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(PurTestInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(PurTestInfo item)
    {
        return super.indexOf(item);
    }
}