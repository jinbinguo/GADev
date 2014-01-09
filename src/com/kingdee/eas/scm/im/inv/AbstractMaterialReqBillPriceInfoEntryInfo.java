package com.kingdee.eas.scm.im.inv;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractMaterialReqBillPriceInfoEntryInfo extends com.kingdee.eas.scm.common.SCMBillEntryBaseInfo implements Serializable 
{
    public AbstractMaterialReqBillPriceInfoEntryInfo()
    {
        this("id");
    }
    protected AbstractMaterialReqBillPriceInfoEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: �۸���Ϣ 's ���ϳ��ⵥ��¼ID property 
     */
    public com.kingdee.eas.scm.im.inv.MaterialReqBillInfo getParent()
    {
        return (com.kingdee.eas.scm.im.inv.MaterialReqBillInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.scm.im.inv.MaterialReqBillInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:�۸���Ϣ's ����property 
     */
    public java.math.BigDecimal getPrice()
    {
        return getBigDecimal("price");
    }
    public void setPrice(java.math.BigDecimal item)
    {
        setBigDecimal("price", item);
    }
    /**
     * Object:�۸���Ϣ's �ۿ۷�ʽproperty 
     */
    public com.kingdee.eas.basedata.scm.common.DiscountModeEnum getDiscountType()
    {
        return com.kingdee.eas.basedata.scm.common.DiscountModeEnum.getEnum(getInt("discountType"));
    }
    public void setDiscountType(com.kingdee.eas.basedata.scm.common.DiscountModeEnum item)
    {
		if (item != null) {
        setInt("discountType", item.getValue());
		}
    }
    /**
     * Object:�۸���Ϣ's �ۿ���property 
     */
    public java.math.BigDecimal getDiscount()
    {
        return getBigDecimal("discount");
    }
    public void setDiscount(java.math.BigDecimal item)
    {
        setBigDecimal("discount", item);
    }
    /**
     * Object:�۸���Ϣ's ʵ�ʵ���property 
     */
    public java.math.BigDecimal getRealPrice()
    {
        return getBigDecimal("realPrice");
    }
    public void setRealPrice(java.math.BigDecimal item)
    {
        setBigDecimal("realPrice", item);
    }
    /**
     * Object:�۸���Ϣ's ˰��property 
     */
    public java.math.BigDecimal getTaxRate()
    {
        return getBigDecimal("taxRate");
    }
    public void setTaxRate(java.math.BigDecimal item)
    {
        setBigDecimal("taxRate", item);
    }
    /**
     * Object:�۸���Ϣ's ��˰����property 
     */
    public java.math.BigDecimal getTaxPrice()
    {
        return getBigDecimal("taxPrice");
    }
    public void setTaxPrice(java.math.BigDecimal item)
    {
        setBigDecimal("taxPrice", item);
    }
    /**
     * Object:�۸���Ϣ's ʵ�ʺ�˰����property 
     */
    public java.math.BigDecimal getRealTaxPrice()
    {
        return getBigDecimal("realTaxPrice");
    }
    public void setRealTaxPrice(java.math.BigDecimal item)
    {
        setBigDecimal("realTaxPrice", item);
    }
    /**
     * Object:�۸���Ϣ's ���ϳ��ⵥ��¼IDproperty 
     */
    public String getMaterialReqEntryID()
    {
        return getString("materialReqEntryID");
    }
    public void setMaterialReqEntryID(String item)
    {
        setString("materialReqEntryID", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("97A53E9B");
    }
}