package com.kingdee.eas.ga.basedata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class CustomerDiscountEntryCollection extends AbstractObjectCollection 
{
    public CustomerDiscountEntryCollection()
    {
        super(CustomerDiscountEntryInfo.class);
    }
    public boolean add(CustomerDiscountEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(CustomerDiscountEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(CustomerDiscountEntryInfo item)
    {
        return removeObject(item);
    }
    public CustomerDiscountEntryInfo get(int index)
    {
        return(CustomerDiscountEntryInfo)getObject(index);
    }
    public CustomerDiscountEntryInfo get(Object key)
    {
        return(CustomerDiscountEntryInfo)getObject(key);
    }
    public void set(int index, CustomerDiscountEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(CustomerDiscountEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(CustomerDiscountEntryInfo item)
    {
        return super.indexOf(item);
    }
}