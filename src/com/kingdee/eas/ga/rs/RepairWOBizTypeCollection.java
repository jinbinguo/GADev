package com.kingdee.eas.ga.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairWOBizTypeCollection extends AbstractObjectCollection 
{
    public RepairWOBizTypeCollection()
    {
        super(RepairWOBizTypeInfo.class);
    }
    public boolean add(RepairWOBizTypeInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairWOBizTypeCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairWOBizTypeInfo item)
    {
        return removeObject(item);
    }
    public RepairWOBizTypeInfo get(int index)
    {
        return(RepairWOBizTypeInfo)getObject(index);
    }
    public RepairWOBizTypeInfo get(Object key)
    {
        return(RepairWOBizTypeInfo)getObject(key);
    }
    public void set(int index, RepairWOBizTypeInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairWOBizTypeInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairWOBizTypeInfo item)
    {
        return super.indexOf(item);
    }
}