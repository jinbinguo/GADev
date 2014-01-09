package com.kingdee.eas.scm.cal;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class CostAdjustBillCollection extends AbstractObjectCollection 
{
    public CostAdjustBillCollection()
    {
        super(CostAdjustBillInfo.class);
    }
    public boolean add(CostAdjustBillInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(CostAdjustBillCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(CostAdjustBillInfo item)
    {
        return removeObject(item);
    }
    public CostAdjustBillInfo get(int index)
    {
        return(CostAdjustBillInfo)getObject(index);
    }
    public CostAdjustBillInfo get(Object key)
    {
        return(CostAdjustBillInfo)getObject(key);
    }
    public void set(int index, CostAdjustBillInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(CostAdjustBillInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(CostAdjustBillInfo item)
    {
        return super.indexOf(item);
    }
}