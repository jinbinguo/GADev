package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairWORWOTotalAmountEntryCollection extends AbstractObjectCollection 
{
    public RepairWORWOTotalAmountEntryCollection()
    {
        super(RepairWORWOTotalAmountEntryInfo.class);
    }
    public boolean add(RepairWORWOTotalAmountEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairWORWOTotalAmountEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairWORWOTotalAmountEntryInfo item)
    {
        return removeObject(item);
    }
    public RepairWORWOTotalAmountEntryInfo get(int index)
    {
        return(RepairWORWOTotalAmountEntryInfo)getObject(index);
    }
    public RepairWORWOTotalAmountEntryInfo get(Object key)
    {
        return(RepairWORWOTotalAmountEntryInfo)getObject(key);
    }
    public void set(int index, RepairWORWOTotalAmountEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairWORWOTotalAmountEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairWORWOTotalAmountEntryInfo item)
    {
        return super.indexOf(item);
    }
}