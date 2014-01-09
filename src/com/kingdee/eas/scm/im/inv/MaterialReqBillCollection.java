package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class MaterialReqBillCollection extends AbstractObjectCollection 
{
    public MaterialReqBillCollection()
    {
        super(MaterialReqBillInfo.class);
    }
    public boolean add(MaterialReqBillInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(MaterialReqBillCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(MaterialReqBillInfo item)
    {
        return removeObject(item);
    }
    public MaterialReqBillInfo get(int index)
    {
        return(MaterialReqBillInfo)getObject(index);
    }
    public MaterialReqBillInfo get(Object key)
    {
        return(MaterialReqBillInfo)getObject(key);
    }
    public void set(int index, MaterialReqBillInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(MaterialReqBillInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(MaterialReqBillInfo item)
    {
        return super.indexOf(item);
    }
}