package com.kingdee.eas.myframework.test;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class TestDatabaseCollection extends AbstractObjectCollection 
{
    public TestDatabaseCollection()
    {
        super(TestDatabaseInfo.class);
    }
    public boolean add(TestDatabaseInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(TestDatabaseCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(TestDatabaseInfo item)
    {
        return removeObject(item);
    }
    public TestDatabaseInfo get(int index)
    {
        return(TestDatabaseInfo)getObject(index);
    }
    public TestDatabaseInfo get(Object key)
    {
        return(TestDatabaseInfo)getObject(key);
    }
    public void set(int index, TestDatabaseInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(TestDatabaseInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(TestDatabaseInfo item)
    {
        return super.indexOf(item);
    }
}