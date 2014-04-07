package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class DMSPrintContentEntryCollection extends AbstractObjectCollection 
{
    public DMSPrintContentEntryCollection()
    {
        super(DMSPrintContentEntryInfo.class);
    }
    public boolean add(DMSPrintContentEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(DMSPrintContentEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(DMSPrintContentEntryInfo item)
    {
        return removeObject(item);
    }
    public DMSPrintContentEntryInfo get(int index)
    {
        return(DMSPrintContentEntryInfo)getObject(index);
    }
    public DMSPrintContentEntryInfo get(Object key)
    {
        return(DMSPrintContentEntryInfo)getObject(key);
    }
    public void set(int index, DMSPrintContentEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(DMSPrintContentEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(DMSPrintContentEntryInfo item)
    {
        return super.indexOf(item);
    }
}