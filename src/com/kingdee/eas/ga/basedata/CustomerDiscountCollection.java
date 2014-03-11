package com.kingdee.eas.ga.basedata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class CustomerDiscountCollection extends AbstractObjectCollection 
{
    public CustomerDiscountCollection()
    {
        super(CustomerDiscountInfo.class);
    }
    public boolean add(CustomerDiscountInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(CustomerDiscountCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(CustomerDiscountInfo item)
    {
        return removeObject(item);
    }
    public CustomerDiscountInfo get(int index)
    {
        return(CustomerDiscountInfo)getObject(index);
    }
    public CustomerDiscountInfo get(Object key)
    {
        return(CustomerDiscountInfo)getObject(key);
    }
    public void set(int index, CustomerDiscountInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(CustomerDiscountInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(CustomerDiscountInfo item)
    {
        return super.indexOf(item);
    }
}