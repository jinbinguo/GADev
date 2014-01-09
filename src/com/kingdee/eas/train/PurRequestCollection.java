package com.kingdee.eas.train;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class PurRequestCollection extends AbstractObjectCollection 
{
    public PurRequestCollection()
    {
        super(PurRequestInfo.class);
    }
    public boolean add(PurRequestInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(PurRequestCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(PurRequestInfo item)
    {
        return removeObject(item);
    }
    public PurRequestInfo get(int index)
    {
        return(PurRequestInfo)getObject(index);
    }
    public PurRequestInfo get(Object key)
    {
        return(PurRequestInfo)getObject(key);
    }
    public void set(int index, PurRequestInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(PurRequestInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(PurRequestInfo item)
    {
        return super.indexOf(item);
    }
}