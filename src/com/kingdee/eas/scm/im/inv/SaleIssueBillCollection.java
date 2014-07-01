package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class SaleIssueBillCollection extends AbstractObjectCollection 
{
    public SaleIssueBillCollection()
    {
        super(SaleIssueBillInfo.class);
    }
    public boolean add(SaleIssueBillInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(SaleIssueBillCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(SaleIssueBillInfo item)
    {
        return removeObject(item);
    }
    public SaleIssueBillInfo get(int index)
    {
        return(SaleIssueBillInfo)getObject(index);
    }
    public SaleIssueBillInfo get(Object key)
    {
        return(SaleIssueBillInfo)getObject(key);
    }
    public void set(int index, SaleIssueBillInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(SaleIssueBillInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(SaleIssueBillInfo item)
    {
        return super.indexOf(item);
    }
}