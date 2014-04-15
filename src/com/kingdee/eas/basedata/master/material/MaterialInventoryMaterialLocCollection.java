package com.kingdee.eas.basedata.master.material;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class MaterialInventoryMaterialLocCollection extends AbstractObjectCollection 
{
    public MaterialInventoryMaterialLocCollection()
    {
        super(MaterialInventoryMaterialLocInfo.class);
    }
    public boolean add(MaterialInventoryMaterialLocInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(MaterialInventoryMaterialLocCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(MaterialInventoryMaterialLocInfo item)
    {
        return removeObject(item);
    }
    public MaterialInventoryMaterialLocInfo get(int index)
    {
        return(MaterialInventoryMaterialLocInfo)getObject(index);
    }
    public MaterialInventoryMaterialLocInfo get(Object key)
    {
        return(MaterialInventoryMaterialLocInfo)getObject(key);
    }
    public void set(int index, MaterialInventoryMaterialLocInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(MaterialInventoryMaterialLocInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(MaterialInventoryMaterialLocInfo item)
    {
        return super.indexOf(item);
    }
}