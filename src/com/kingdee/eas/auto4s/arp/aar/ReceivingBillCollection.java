package com.kingdee.eas.auto4s.arp.aar;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class ReceivingBillCollection extends AbstractObjectCollection 
{
    public ReceivingBillCollection()
    {
        super(ReceivingBillInfo.class);
    }
    public boolean add(ReceivingBillInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(ReceivingBillCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(ReceivingBillInfo item)
    {
        return removeObject(item);
    }
    public ReceivingBillInfo get(int index)
    {
        return(ReceivingBillInfo)getObject(index);
    }
    public ReceivingBillInfo get(Object key)
    {
        return(ReceivingBillInfo)getObject(key);
    }
    public void set(int index, ReceivingBillInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(ReceivingBillInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(ReceivingBillInfo item)
    {
        return super.indexOf(item);
    }
}