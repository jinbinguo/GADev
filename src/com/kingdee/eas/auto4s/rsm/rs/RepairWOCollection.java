package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairWOCollection extends AbstractObjectCollection 
{
    public RepairWOCollection()
    {
        super(RepairWOInfo.class);
    }
    public boolean add(RepairWOInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairWOCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairWOInfo item)
    {
        return removeObject(item);
    }
    public RepairWOInfo get(int index)
    {
        return(RepairWOInfo)getObject(index);
    }
    public RepairWOInfo get(Object key)
    {
        return(RepairWOInfo)getObject(key);
    }
    public void set(int index, RepairWOInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairWOInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairWOInfo item)
    {
        return super.indexOf(item);
    }
}