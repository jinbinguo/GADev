package com.kingdee.eas.ga.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class WCollection extends AbstractObjectCollection 
{
    public WCollection()
    {
        super(WInfo.class);
    }
    public boolean add(WInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(WCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(WInfo item)
    {
        return removeObject(item);
    }
    public WInfo get(int index)
    {
        return(WInfo)getObject(index);
    }
    public WInfo get(Object key)
    {
        return(WInfo)getObject(key);
    }
    public void set(int index, WInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(WInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(WInfo item)
    {
        return super.indexOf(item);
    }
}