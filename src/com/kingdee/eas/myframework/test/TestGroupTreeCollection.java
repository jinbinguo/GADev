package com.kingdee.eas.myframework.test;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class TestGroupTreeCollection extends AbstractObjectCollection 
{
    public TestGroupTreeCollection()
    {
        super(TestGroupTreeInfo.class);
    }
    public boolean add(TestGroupTreeInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(TestGroupTreeCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(TestGroupTreeInfo item)
    {
        return removeObject(item);
    }
    public TestGroupTreeInfo get(int index)
    {
        return(TestGroupTreeInfo)getObject(index);
    }
    public TestGroupTreeInfo get(Object key)
    {
        return(TestGroupTreeInfo)getObject(key);
    }
    public void set(int index, TestGroupTreeInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(TestGroupTreeInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(TestGroupTreeInfo item)
    {
        return super.indexOf(item);
    }
}