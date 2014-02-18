package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractDMSWipBillEntry3Info extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractDMSWipBillEntry3Info()
    {
        this("id");
    }
    protected AbstractDMSWipBillEntry3Info(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 工时行 's null property 
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
     * Object:工时行's 账户代码property 
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
     * Object:工时行's 说明property 
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
     * Object:工时行's 调度状态property 
     */
    public String getDispatchStatus()
    {
        return getString("dispatchStatus");
    }
    public void setDispatchStatus(String item)
    {
        setString("dispatchStatus", item);
    }
    /**
     * Object:工时行's 增值税代码property 
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
     * Object:工时行's WIP号property 
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
     * Object:工时行's 付款代码property 
     */
    public String getPayCode()
    {
        return getString("payCode");
    }
    public void setPayCode(String item)
    {
        setString("payCode", item);
    }
    /**
     * Object:工时行's 账单编号property 
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
     * Object:工时行's 账单状态property 
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
     * Object:工时行's 实际行（排序）property 
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
     * Object:工时行's 标准时间property 
     */
    public java.math.BigDecimal getStandardHour()
    {
        return getBigDecimal("standardHour");
    }
    public void setStandardHour(java.math.BigDecimal item)
    {
        setBigDecimal("standardHour", item);
    }
    /**
     * Object:工时行's 编辑日期property 
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
     * Object:工时行's 折扣百分比property 
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
     * Object:工时行's 行号property 
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
     * Object:工时行's 单位分钟数property 
     */
    public int getUnitMI()
    {
        return getInt("unitMI");
    }
    public void setUnitMI(int item)
    {
        setInt("unitMI", item);
    }
    /**
     * Object:工时行's 小时工时率property 
     */
    public java.math.BigDecimal getHourRate()
    {
        return getBigDecimal("hourRate");
    }
    public void setHourRate(java.math.BigDecimal item)
    {
        setBigDecimal("hourRate", item);
    }
    /**
     * Object:工时行's 销售类型property 
     */
    public String getSaleType()
    {
        return getString("saleType");
    }
    public void setSaleType(String item)
    {
        setString("saleType", item);
    }
    /**
     * Object:工时行's 过账日期property 
     */
    public java.util.Date getPostingDate()
    {
        return getDate("postingDate");
    }
    public void setPostingDate(java.util.Date item)
    {
        setDate("postingDate", item);
    }
    /**
     * Object:工时行's RTS代码property 
     */
    public String getRts()
    {
        return getString("rts");
    }
    public void setRts(String item)
    {
        setString("rts", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("305D8183");
    }
}