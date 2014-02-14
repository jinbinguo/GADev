package com.kingdee.eas.base.permission;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class UserCollection extends AbstractObjectCollection 
{
    public UserCollection()
    {
        super(UserInfo.class);
    }
    public boolean add(UserInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(UserCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(UserInfo item)
    {
        return removeObject(item);
    }
    public UserInfo get(int index)
    {
        return(UserInfo)getObject(index);
    }
    public UserInfo get(Object key)
    {
        return(UserInfo)getObject(key);
    }
    public void set(int index, UserInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(UserInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(UserInfo item)
    {
        return super.indexOf(item);
    }
}