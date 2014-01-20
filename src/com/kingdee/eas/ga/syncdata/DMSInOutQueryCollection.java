package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class DMSInOutQueryCollection extends AbstractObjectCollection 
{
    public DMSInOutQueryCollection()
    {
        super(DMSInOutQueryInfo.class);
    }
    public boolean add(DMSInOutQueryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(DMSInOutQueryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(DMSInOutQueryInfo item)
    {
        return removeObject(item);
    }
    public DMSInOutQueryInfo get(int index)
    {
        return(DMSInOutQueryInfo)getObject(index);
    }
    public DMSInOutQueryInfo get(Object key)
    {
        return(DMSInOutQueryInfo)getObject(key);
    }
    public void set(int index, DMSInOutQueryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(DMSInOutQueryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(DMSInOutQueryInfo item)
    {
        return super.indexOf(item);
    }
}