package com.kingdee.eas.train;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class PurInWarehsEntryCollection extends AbstractObjectCollection 
{
    public PurInWarehsEntryCollection()
    {
        super(PurInWarehsEntryInfo.class);
    }
    public boolean add(PurInWarehsEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(PurInWarehsEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(PurInWarehsEntryInfo item)
    {
        return removeObject(item);
    }
    public PurInWarehsEntryInfo get(int index)
    {
        return(PurInWarehsEntryInfo)getObject(index);
    }
    public PurInWarehsEntryInfo get(Object key)
    {
        return(PurInWarehsEntryInfo)getObject(key);
    }
    public void set(int index, PurInWarehsEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(PurInWarehsEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(PurInWarehsEntryInfo item)
    {
        return super.indexOf(item);
    }
}