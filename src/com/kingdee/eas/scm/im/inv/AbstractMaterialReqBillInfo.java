package com.kingdee.eas.scm.im.inv;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractMaterialReqBillInfo extends com.kingdee.eas.scm.im.inv.InvBillBaseInfo implements Serializable 
{
    public AbstractMaterialReqBillInfo()
    {
        this("id");
    }
    protected AbstractMaterialReqBillInfo(String pkField)
    {
        super(pkField);
        put("priceInfo", new com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryCollection());
        put("entry", new com.kingdee.eas.scm.im.inv.MaterialReqBillEntryCollection());
    }
    /**
     * Object: ���ϳ��ⵥ 's ���ϳ��ⵥ�� property 
     */
    public com.kingdee.eas.scm.im.inv.MaterialReqBillEntryCollection getEntry()
    {
        return (com.kingdee.eas.scm.im.inv.MaterialReqBillEntryCollection)get("entry");
    }
    /**
     * Object: ���ϳ��ⵥ 's ��Ӧ�� property 
     */
    public com.kingdee.eas.basedata.master.cssp.SupplierInfo getSupplier()
    {
        return (com.kingdee.eas.basedata.master.cssp.SupplierInfo)get("supplier");
    }
    public void setSupplier(com.kingdee.eas.basedata.master.cssp.SupplierInfo item)
    {
        put("supplier", item);
    }
    /**
     * Object:���ϳ��ⵥ's �ɹ����property 
     */
    public com.kingdee.eas.scm.common.PurchaseTypeEnum getPurchaseType()
    {
        return com.kingdee.eas.scm.common.PurchaseTypeEnum.getEnum(getInt("purchaseType"));
    }
    public void setPurchaseType(com.kingdee.eas.scm.common.PurchaseTypeEnum item)
    {
		if (item != null) {
        setInt("purchaseType", item.getValue());
		}
    }
    /**
     * Object:���ϳ��ⵥ's �����ʶproperty 
     */
    public boolean isIsBackflush()
    {
        return getBoolean("isBackflush");
    }
    public void setIsBackflush(boolean item)
    {
        setBoolean("isBackflush", item);
    }
    /**
     * Object:���ϳ��ⵥ's ���嵥��property 
     */
    public String getBackflushBillID()
    {
        return getString("backflushBillID");
    }
    public void setBackflushBillID(String item)
    {
        setString("backflushBillID", item);
    }
    /**
     * Object:���ϳ��ⵥ's property 
     */
    public String getBackflushBillTypeID()
    {
        return getString("backflushBillTypeID");
    }
    public void setBackflushBillTypeID(String item)
    {
        setString("backflushBillTypeID", item);
    }
    /**
     * Object: ���ϳ��ⵥ 's ��Ӧ�������֯ property 
     */
    public com.kingdee.eas.basedata.org.StorageOrgUnitInfo getSupplyStoreOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.StorageOrgUnitInfo)get("supplyStoreOrgUnit");
    }
    public void setSupplyStoreOrgUnit(com.kingdee.eas.basedata.org.StorageOrgUnitInfo item)
    {
        put("supplyStoreOrgUnit", item);
    }
    /**
     * Object: ���ϳ��ⵥ 's ��Ӧ��������֯ property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getSupplyCompanyOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("supplyCompanyOrgUnit");
    }
    public void setSupplyCompanyOrgUnit(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("supplyCompanyOrgUnit", item);
    }
    /**
     * Object: ���ϳ��ⵥ 's ���󷽲�����֯ property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getDemandCompanyOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("demandCompanyOrgUnit");
    }
    public void setDemandCompanyOrgUnit(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("demandCompanyOrgUnit", item);
    }
    /**
     * Object: ���ϳ��ⵥ 's �۸���Ϣ property 
     */
    public com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryCollection getPriceInfo()
    {
        return (com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryCollection)get("priceInfo");
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("500AB75E");
    }
}