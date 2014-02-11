package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairWORWORepairPkgEntryCollection extends AbstractObjectCollection 
{
    public RepairWORWORepairPkgEntryCollection()
    {
        super(RepairWORWORepairPkgEntryInfo.class);
    }
    public boolean add(RepairWORWORepairPkgEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairWORWORepairPkgEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairWORWORepairPkgEntryInfo item)
    {
        return removeObject(item);
    }
    public RepairWORWORepairPkgEntryInfo get(int index)
    {
        return(RepairWORWORepairPkgEntryInfo)getObject(index);
    }
    public RepairWORWORepairPkgEntryInfo get(Object key)
    {
        return(RepairWORWORepairPkgEntryInfo)getObject(key);
    }
    public void set(int index, RepairWORWORepairPkgEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairWORWORepairPkgEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairWORWORepairPkgEntryInfo item)
    {
        return super.indexOf(item);
    }
}