package com.kingdee.eas.myframework.template.base;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class SimpleDatabaseCollection extends AbstractObjectCollection 
{
    public SimpleDatabaseCollection()
    {
        super(SimpleDatabaseInfo.class);
    }
    public boolean add(SimpleDatabaseInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(SimpleDatabaseCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(SimpleDatabaseInfo item)
    {
        return removeObject(item);
    }
    public SimpleDatabaseInfo get(int index)
    {
        return(SimpleDatabaseInfo)getObject(index);
    }
    public SimpleDatabaseInfo get(Object key)
    {
        return(SimpleDatabaseInfo)getObject(key);
    }
    public void set(int index, SimpleDatabaseInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(SimpleDatabaseInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(SimpleDatabaseInfo item)
    {
        return super.indexOf(item);
    }
}