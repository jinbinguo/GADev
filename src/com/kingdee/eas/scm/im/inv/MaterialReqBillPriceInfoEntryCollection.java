package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class MaterialReqBillPriceInfoEntryCollection extends AbstractObjectCollection 
{
    public MaterialReqBillPriceInfoEntryCollection()
    {
        super(MaterialReqBillPriceInfoEntryInfo.class);
    }
    public boolean add(MaterialReqBillPriceInfoEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(MaterialReqBillPriceInfoEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(MaterialReqBillPriceInfoEntryInfo item)
    {
        return removeObject(item);
    }
    public MaterialReqBillPriceInfoEntryInfo get(int index)
    {
        return(MaterialReqBillPriceInfoEntryInfo)getObject(index);
    }
    public MaterialReqBillPriceInfoEntryInfo get(Object key)
    {
        return(MaterialReqBillPriceInfoEntryInfo)getObject(key);
    }
    public void set(int index, MaterialReqBillPriceInfoEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(MaterialReqBillPriceInfoEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(MaterialReqBillPriceInfoEntryInfo item)
    {
        return super.indexOf(item);
    }
}