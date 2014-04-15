package com.kingdee.eas.basedata.master.material;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractMaterialInventoryMaterialLocInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractMaterialInventoryMaterialLocInfo()
    {
        this("id");
    }
    protected AbstractMaterialInventoryMaterialLocInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: ���ϻ�λ 's null property 
     */
    public com.kingdee.eas.basedata.master.material.MaterialInventoryInfo getParent()
    {
        return (com.kingdee.eas.basedata.master.material.MaterialInventoryInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.basedata.master.material.MaterialInventoryInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:���ϻ�λ's ��λproperty 
     */
    public String getLoc()
    {
        return getString("loc");
    }
    public void setLoc(String item)
    {
        setString("loc", item);
    }
    /**
     * Object:���ϻ�λ's ����ֿ�property 
     */
    public com.kingdee.eas.ga.basedata.BMWLocEnum getBmwLoc()
    {
        return com.kingdee.eas.ga.basedata.BMWLocEnum.getEnum(getString("bmwLoc"));
    }
    public void setBmwLoc(com.kingdee.eas.ga.basedata.BMWLocEnum item)
    {
		if (item != null) {
        setString("bmwLoc", item.getValue());
		}
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("928F7517");
    }
}