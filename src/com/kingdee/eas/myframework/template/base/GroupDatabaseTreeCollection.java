package com.kingdee.eas.myframework.template.base;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class GroupDatabaseTreeCollection extends AbstractObjectCollection 
{
    public GroupDatabaseTreeCollection()
    {
        super(GroupDatabaseTreeInfo.class);
    }
    public boolean add(GroupDatabaseTreeInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(GroupDatabaseTreeCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(GroupDatabaseTreeInfo item)
    {
        return removeObject(item);
    }
    public GroupDatabaseTreeInfo get(int index)
    {
        return(GroupDatabaseTreeInfo)getObject(index);
    }
    public GroupDatabaseTreeInfo get(Object key)
    {
        return(GroupDatabaseTreeInfo)getObject(key);
    }
    public void set(int index, GroupDatabaseTreeInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(GroupDatabaseTreeInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(GroupDatabaseTreeInfo item)
    {
        return super.indexOf(item);
    }
}