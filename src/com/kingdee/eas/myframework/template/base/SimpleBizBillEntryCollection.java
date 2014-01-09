package com.kingdee.eas.myframework.template.base;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class SimpleBizBillEntryCollection extends AbstractObjectCollection 
{
    public SimpleBizBillEntryCollection()
    {
        super(SimpleBizBillEntryInfo.class);
    }
    public boolean add(SimpleBizBillEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(SimpleBizBillEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(SimpleBizBillEntryInfo item)
    {
        return removeObject(item);
    }
    public SimpleBizBillEntryInfo get(int index)
    {
        return(SimpleBizBillEntryInfo)getObject(index);
    }
    public SimpleBizBillEntryInfo get(Object key)
    {
        return(SimpleBizBillEntryInfo)getObject(key);
    }
    public void set(int index, SimpleBizBillEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(SimpleBizBillEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(SimpleBizBillEntryInfo item)
    {
        return super.indexOf(item);
    }
}