package com.kingdee.eas.myframework.template.base;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class SimpleBizBillCollection extends AbstractObjectCollection 
{
    public SimpleBizBillCollection()
    {
        super(SimpleBizBillInfo.class);
    }
    public boolean add(SimpleBizBillInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(SimpleBizBillCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(SimpleBizBillInfo item)
    {
        return removeObject(item);
    }
    public SimpleBizBillInfo get(int index)
    {
        return(SimpleBizBillInfo)getObject(index);
    }
    public SimpleBizBillInfo get(Object key)
    {
        return(SimpleBizBillInfo)getObject(key);
    }
    public void set(int index, SimpleBizBillInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(SimpleBizBillInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(SimpleBizBillInfo item)
    {
        return super.indexOf(item);
    }
}