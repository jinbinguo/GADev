package com.kingdee.eas.ga.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairManCollection extends AbstractObjectCollection 
{
    public RepairManCollection()
    {
        super(RepairManInfo.class);
    }
    public boolean add(RepairManInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairManCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairManInfo item)
    {
        return removeObject(item);
    }
    public RepairManInfo get(int index)
    {
        return(RepairManInfo)getObject(index);
    }
    public RepairManInfo get(Object key)
    {
        return(RepairManInfo)getObject(key);
    }
    public void set(int index, RepairManInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairManInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairManInfo item)
    {
        return super.indexOf(item);
    }
}