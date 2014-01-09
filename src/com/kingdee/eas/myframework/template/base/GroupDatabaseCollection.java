package com.kingdee.eas.myframework.template.base;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class GroupDatabaseCollection extends AbstractObjectCollection 
{
    public GroupDatabaseCollection()
    {
        super(GroupDatabaseInfo.class);
    }
    public boolean add(GroupDatabaseInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(GroupDatabaseCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(GroupDatabaseInfo item)
    {
        return removeObject(item);
    }
    public GroupDatabaseInfo get(int index)
    {
        return(GroupDatabaseInfo)getObject(index);
    }
    public GroupDatabaseInfo get(Object key)
    {
        return(GroupDatabaseInfo)getObject(key);
    }
    public void set(int index, GroupDatabaseInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(GroupDatabaseInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(GroupDatabaseInfo item)
    {
        return super.indexOf(item);
    }
}