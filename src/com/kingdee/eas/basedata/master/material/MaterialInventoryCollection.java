package com.kingdee.eas.basedata.master.material;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class MaterialInventoryCollection extends AbstractObjectCollection 
{
    public MaterialInventoryCollection()
    {
        super(MaterialInventoryInfo.class);
    }
    public boolean add(MaterialInventoryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(MaterialInventoryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(MaterialInventoryInfo item)
    {
        return removeObject(item);
    }
    public MaterialInventoryInfo get(int index)
    {
        return(MaterialInventoryInfo)getObject(index);
    }
    public MaterialInventoryInfo get(Object key)
    {
        return(MaterialInventoryInfo)getObject(key);
    }
    public void set(int index, MaterialInventoryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(MaterialInventoryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(MaterialInventoryInfo item)
    {
        return super.indexOf(item);
    }
}