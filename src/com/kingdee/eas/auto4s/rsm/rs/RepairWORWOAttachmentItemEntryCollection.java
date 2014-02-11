package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class RepairWORWOAttachmentItemEntryCollection extends AbstractObjectCollection 
{
    public RepairWORWOAttachmentItemEntryCollection()
    {
        super(RepairWORWOAttachmentItemEntryInfo.class);
    }
    public boolean add(RepairWORWOAttachmentItemEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(RepairWORWOAttachmentItemEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(RepairWORWOAttachmentItemEntryInfo item)
    {
        return removeObject(item);
    }
    public RepairWORWOAttachmentItemEntryInfo get(int index)
    {
        return(RepairWORWOAttachmentItemEntryInfo)getObject(index);
    }
    public RepairWORWOAttachmentItemEntryInfo get(Object key)
    {
        return(RepairWORWOAttachmentItemEntryInfo)getObject(key);
    }
    public void set(int index, RepairWORWOAttachmentItemEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(RepairWORWOAttachmentItemEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(RepairWORWOAttachmentItemEntryInfo item)
    {
        return super.indexOf(item);
    }
}