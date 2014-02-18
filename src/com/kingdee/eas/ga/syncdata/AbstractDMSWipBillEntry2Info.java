package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractDMSWipBillEntry2Info extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractDMSWipBillEntry2Info()
    {
        this("id");
    }
    protected AbstractDMSWipBillEntry2Info(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: ����� 's null property 
     */
    public com.kingdee.eas.ga.syncdata.DMSWipBillInfo getParent()
    {
        return (com.kingdee.eas.ga.syncdata.DMSWipBillInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.ga.syncdata.DMSWipBillInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:�����'s �˵����property 
     */
    public String getBillNum()
    {
        return getString("billNum");
    }
    public void setBillNum(String item)
    {
        setString("billNum", item);
    }
    /**
     * Object:�����'s �˵�״̬property 
     */
    public String getBillStatus()
    {
        return getString("billStatus");
    }
    public void setBillStatus(String item)
    {
        setString("billStatus", item);
    }
    /**
     * Object:�����'s ����״̬property 
     */
    public String getOrderStatus()
    {
        return getString("orderStatus");
    }
    public void setOrderStatus(String item)
    {
        setString("orderStatus", item);
    }
    /**
     * Object:�����'s ������property 
     */
    public String getMaterialNum()
    {
        return getString("materialNum");
    }
    public void setMaterialNum(String item)
    {
        setString("materialNum", item);
    }
    /**
     * Object:�����'s WIP��property 
     */
    public String getWip()
    {
        return getString("wip");
    }
    public void setWip(String item)
    {
        setString("wip", item);
    }
    /**
     * Object:�����'s �������property 
     */
    public String getChaimCode()
    {
        return getString("chaimCode");
    }
    public void setChaimCode(String item)
    {
        setString("chaimCode", item);
    }
    /**
     * Object:�����'s ʵ���кţ�����property 
     */
    public int getRealLineSeq()
    {
        return getInt("realLineSeq");
    }
    public void setRealLineSeq(int item)
    {
        setInt("realLineSeq", item);
    }
    /**
     * Object:�����'s ���༭����property 
     */
    public java.util.Date getLastEditTime()
    {
        return getDate("lastEditTime");
    }
    public void setLastEditTime(java.util.Date item)
    {
        setDate("lastEditTime", item);
    }
    /**
     * Object:�����'s ˵��property 
     */
    public String getRemark()
    {
        return getString("remark");
    }
    public void setRemark(String item)
    {
        setString("remark", item);
    }
    /**
     * Object:�����'s �ۿ۰ٷֱ�property 
     */
    public java.math.BigDecimal getDiscountRate()
    {
        return getBigDecimal("discountRate");
    }
    public void setDiscountRate(java.math.BigDecimal item)
    {
        setBigDecimal("discountRate", item);
    }
    /**
     * Object:�����'s �к�property 
     */
    public int getLineSeq()
    {
        return getInt("lineSeq");
    }
    public void setLineSeq(int item)
    {
        setInt("lineSeq", item);
    }
    /**
     * Object:�����'s ��������property 
     */
    public java.math.BigDecimal getOrderQty()
    {
        return getBigDecimal("orderQty");
    }
    public void setOrderQty(java.math.BigDecimal item)
    {
        setBigDecimal("orderQty", item);
    }
    /**
     * Object:�����'s ���ۼ۸�property 
     */
    public java.math.BigDecimal getSalePrice()
    {
        return getBigDecimal("salePrice");
    }
    public void setSalePrice(java.math.BigDecimal item)
    {
        setBigDecimal("salePrice", item);
    }
    /**
     * Object:�����'s ��ֵ˰����property 
     */
    public String getTaxBillCode()
    {
        return getString("taxBillCode");
    }
    public void setTaxBillCode(String item)
    {
        setString("taxBillCode", item);
    }
    /**
     * Object:�����'s �˻����property 
     */
    public String getAccountCode()
    {
        return getString("accountCode");
    }
    public void setAccountCode(String item)
    {
        setString("accountCode", item);
    }
    /**
     * Object:�����'s ��������property 
     */
    public String getSaleType()
    {
        return getString("saleType");
    }
    public void setSaleType(String item)
    {
        setString("saleType", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("305D8182");
    }
}