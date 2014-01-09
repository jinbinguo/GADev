package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class MaterialReqBillEntryCollection extends AbstractObjectCollection 
{
    public MaterialReqBillEntryCollection()
    {
        super(MaterialReqBillEntryInfo.class);
    }
    public boolean add(MaterialReqBillEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(MaterialReqBillEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(MaterialReqBillEntryInfo item)
    {
        return removeObject(item);
    }
    public MaterialReqBillEntryInfo get(int index)
    {
        return(MaterialReqBillEntryInfo)getObject(index);
    }
    public MaterialReqBillEntryInfo get(Object key)
    {
        return(MaterialReqBillEntryInfo)getObject(key);
    }
    public void set(int index, MaterialReqBillEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(MaterialReqBillEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(MaterialReqBillEntryInfo item)
    {
        return super.indexOf(item);
    }
}