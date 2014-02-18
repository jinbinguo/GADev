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
     * Object: 零件行 's null property 
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
     * Object:零件行's 账单编号property 
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
     * Object:零件行's 账单状态property 
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
     * Object:零件行's 定货状态property 
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
     * Object:零件行's 零件编号property 
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
     * Object:零件行's WIP号property 
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
     * Object:零件行's 索赔代码property 
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
     * Object:零件行's 实际行号（排序）property 
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
     * Object:零件行's 最后编辑日期property 
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
     * Object:零件行's 说明property 
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
     * Object:零件行's 折扣百分比property 
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
     * Object:零件行's 行号property 
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
     * Object:零件行's 定货数量property 
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
     * Object:零件行's 销售价格property 
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
     * Object:零件行's 增值税代码property 
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
     * Object:零件行's 账户编号property 
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
     * Object:零件行's 销售类型property 
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