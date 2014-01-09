package com.kingdee.eas.myframework.msgengine;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class MailServerConfigCollection extends AbstractObjectCollection 
{
    public MailServerConfigCollection()
    {
        super(MailServerConfigInfo.class);
    }
    public boolean add(MailServerConfigInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(MailServerConfigCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(MailServerConfigInfo item)
    {
        return removeObject(item);
    }
    public MailServerConfigInfo get(int index)
    {
        return(MailServerConfigInfo)getObject(index);
    }
    public MailServerConfigInfo get(Object key)
    {
        return(MailServerConfigInfo)getObject(key);
    }
    public void set(int index, MailServerConfigInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(MailServerConfigInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(MailServerConfigInfo item)
    {
        return super.indexOf(item);
    }
}