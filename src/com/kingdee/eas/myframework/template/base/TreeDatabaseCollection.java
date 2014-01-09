package com.kingdee.eas.myframework.template.base;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class TreeDatabaseCollection extends AbstractObjectCollection 
{
    public TreeDatabaseCollection()
    {
        super(TreeDatabaseInfo.class);
    }
    public boolean add(TreeDatabaseInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(TreeDatabaseCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(TreeDatabaseInfo item)
    {
        return removeObject(item);
    }
    public TreeDatabaseInfo get(int index)
    {
        return(TreeDatabaseInfo)getObject(index);
    }
    public TreeDatabaseInfo get(Object key)
    {
        return(TreeDatabaseInfo)getObject(key);
    }
    public void set(int index, TreeDatabaseInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(TreeDatabaseInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(TreeDatabaseInfo item)
    {
        return super.indexOf(item);
    }
}