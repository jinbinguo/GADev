package com.kingdee.eas.ga.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class CustomerAccountCollection extends AbstractObjectCollection 
{
    public CustomerAccountCollection()
    {
        super(CustomerAccountInfo.class);
    }
    public boolean add(CustomerAccountInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(CustomerAccountCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(CustomerAccountInfo item)
    {
        return removeObject(item);
    }
    public CustomerAccountInfo get(int index)
    {
        return(CustomerAccountInfo)getObject(index);
    }
    public CustomerAccountInfo get(Object key)
    {
        return(CustomerAccountInfo)getObject(key);
    }
    public void set(int index, CustomerAccountInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(CustomerAccountInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(CustomerAccountInfo item)
    {
        return super.indexOf(item);
    }
}