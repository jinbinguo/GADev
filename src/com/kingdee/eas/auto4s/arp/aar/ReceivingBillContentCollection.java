package com.kingdee.eas.auto4s.arp.aar;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class ReceivingBillContentCollection extends AbstractObjectCollection 
{
    public ReceivingBillContentCollection()
    {
        super(ReceivingBillContentInfo.class);
    }
    public boolean add(ReceivingBillContentInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(ReceivingBillContentCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(ReceivingBillContentInfo item)
    {
        return removeObject(item);
    }
    public ReceivingBillContentInfo get(int index)
    {
        return(ReceivingBillContentInfo)getObject(index);
    }
    public ReceivingBillContentInfo get(Object key)
    {
        return(ReceivingBillContentInfo)getObject(key);
    }
    public void set(int index, ReceivingBillContentInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(ReceivingBillContentInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(ReceivingBillContentInfo item)
    {
        return super.indexOf(item);
    }
}