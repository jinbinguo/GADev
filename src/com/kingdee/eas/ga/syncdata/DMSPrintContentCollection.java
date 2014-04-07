package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class DMSPrintContentCollection extends AbstractObjectCollection 
{
    public DMSPrintContentCollection()
    {
        super(DMSPrintContentInfo.class);
    }
    public boolean add(DMSPrintContentInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(DMSPrintContentCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(DMSPrintContentInfo item)
    {
        return removeObject(item);
    }
    public DMSPrintContentInfo get(int index)
    {
        return(DMSPrintContentInfo)getObject(index);
    }
    public DMSPrintContentInfo get(Object key)
    {
        return(DMSPrintContentInfo)getObject(key);
    }
    public void set(int index, DMSPrintContentInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(DMSPrintContentInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(DMSPrintContentInfo item)
    {
        return super.indexOf(item);
    }
}