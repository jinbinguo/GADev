package com.kingdee.eas.myframework.test;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class AAACollection extends AbstractObjectCollection 
{
    public AAACollection()
    {
        super(AAAInfo.class);
    }
    public boolean add(AAAInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(AAACollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(AAAInfo item)
    {
        return removeObject(item);
    }
    public AAAInfo get(int index)
    {
        return(AAAInfo)getObject(index);
    }
    public AAAInfo get(Object key)
    {
        return(AAAInfo)getObject(key);
    }
    public void set(int index, AAAInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(AAAInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(AAAInfo item)
    {
        return super.indexOf(item);
    }
}