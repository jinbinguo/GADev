package com.kingdee.eas.myframework.test;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class TestGroupCollection extends AbstractObjectCollection 
{
    public TestGroupCollection()
    {
        super(TestGroupInfo.class);
    }
    public boolean add(TestGroupInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(TestGroupCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(TestGroupInfo item)
    {
        return removeObject(item);
    }
    public TestGroupInfo get(int index)
    {
        return(TestGroupInfo)getObject(index);
    }
    public TestGroupInfo get(Object key)
    {
        return(TestGroupInfo)getObject(key);
    }
    public void set(int index, TestGroupInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(TestGroupInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(TestGroupInfo item)
    {
        return super.indexOf(item);
    }
}