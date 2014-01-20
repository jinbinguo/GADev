package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class DMSWipNoForStocktakingCollection extends AbstractObjectCollection 
{
    public DMSWipNoForStocktakingCollection()
    {
        super(DMSWipNoForStocktakingInfo.class);
    }
    public boolean add(DMSWipNoForStocktakingInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(DMSWipNoForStocktakingCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(DMSWipNoForStocktakingInfo item)
    {
        return removeObject(item);
    }
    public DMSWipNoForStocktakingInfo get(int index)
    {
        return(DMSWipNoForStocktakingInfo)getObject(index);
    }
    public DMSWipNoForStocktakingInfo get(Object key)
    {
        return(DMSWipNoForStocktakingInfo)getObject(key);
    }
    public void set(int index, DMSWipNoForStocktakingInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(DMSWipNoForStocktakingInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(DMSWipNoForStocktakingInfo item)
    {
        return super.indexOf(item);
    }
}