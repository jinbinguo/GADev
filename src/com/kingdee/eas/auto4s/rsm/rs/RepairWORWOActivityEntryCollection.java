package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairWORWOActivityEntryCollection extends AbstractObjectCollection 
{
    public RepairWORWOActivityEntryCollection()
    {
        super(RepairWORWOActivityEntryInfo.class);
    }
    public boolean add(RepairWORWOActivityEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairWORWOActivityEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairWORWOActivityEntryInfo item)
    {
        return removeObject(item);
    }
    public RepairWORWOActivityEntryInfo get(int index)
    {
        return(RepairWORWOActivityEntryInfo)getObject(index);
    }
    public RepairWORWOActivityEntryInfo get(Object key)
    {
        return(RepairWORWOActivityEntryInfo)getObject(key);
    }
    public void set(int index, RepairWORWOActivityEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairWORWOActivityEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairWORWOActivityEntryInfo item)
    {
        return super.indexOf(item);
    }
}