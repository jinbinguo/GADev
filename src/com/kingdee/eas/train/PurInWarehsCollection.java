package com.kingdee.eas.train;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class PurInWarehsCollection extends AbstractObjectCollection 
{
    public PurInWarehsCollection()
    {
        super(PurInWarehsInfo.class);
    }
    public boolean add(PurInWarehsInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(PurInWarehsCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(PurInWarehsInfo item)
    {
        return removeObject(item);
    }
    public PurInWarehsInfo get(int index)
    {
        return(PurInWarehsInfo)getObject(index);
    }
    public PurInWarehsInfo get(Object key)
    {
        return(PurInWarehsInfo)getObject(key);
    }
    public void set(int index, PurInWarehsInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(PurInWarehsInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(PurInWarehsInfo item)
    {
        return super.indexOf(item);
    }
}