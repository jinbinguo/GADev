package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairWORepairBreakEntryCollection extends AbstractObjectCollection 
{
    public RepairWORepairBreakEntryCollection()
    {
        super(RepairWORepairBreakEntryInfo.class);
    }
    public boolean add(RepairWORepairBreakEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairWORepairBreakEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairWORepairBreakEntryInfo item)
    {
        return removeObject(item);
    }
    public RepairWORepairBreakEntryInfo get(int index)
    {
        return(RepairWORepairBreakEntryInfo)getObject(index);
    }
    public RepairWORepairBreakEntryInfo get(Object key)
    {
        return(RepairWORepairBreakEntryInfo)getObject(key);
    }
    public void set(int index, RepairWORepairBreakEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairWORepairBreakEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairWORepairBreakEntryInfo item)
    {
        return super.indexOf(item);
    }
}