package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairWORWORepairItemEntryCollection extends AbstractObjectCollection 
{
    public RepairWORWORepairItemEntryCollection()
    {
        super(RepairWORWORepairItemEntryInfo.class);
    }
    public boolean add(RepairWORWORepairItemEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairWORWORepairItemEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairWORWORepairItemEntryInfo item)
    {
        return removeObject(item);
    }
    public RepairWORWORepairItemEntryInfo get(int index)
    {
        return(RepairWORWORepairItemEntryInfo)getObject(index);
    }
    public RepairWORWORepairItemEntryInfo get(Object key)
    {
        return(RepairWORWORepairItemEntryInfo)getObject(key);
    }
    public void set(int index, RepairWORWORepairItemEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairWORWORepairItemEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairWORWORepairItemEntryInfo item)
    {
        return super.indexOf(item);
    }
}