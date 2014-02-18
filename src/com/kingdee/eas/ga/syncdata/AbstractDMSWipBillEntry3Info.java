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
     * Object: ��ʱ�� 's null property 
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
     * Object:��ʱ��'s �˻�����property 
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
     * Object:��ʱ��'s ˵��property 
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
     * Object:��ʱ��'s ����״̬property 
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
     * Object:��ʱ��'s ��ֵ˰����property 
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
     * Object:��ʱ��'s WIP��property 
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
     * Object:��ʱ��'s �������property 
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
     * Object:��ʱ��'s �˵����property 
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
     * Object:��ʱ��'s �˵�״̬property 
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
     * Object:��ʱ��'s ʵ���У�����property 
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
     * Object:��ʱ��'s ��׼ʱ��property 
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
     * Object:��ʱ��'s �༭����property 
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
     * Object:��ʱ��'s �ۿ۰ٷֱ�property 
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
     * Object:��ʱ��'s �к�property 
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
     * Object:��ʱ��'s ��λ������property 
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
     * Object:��ʱ��'s Сʱ��ʱ��property 
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
     * Object:��ʱ��'s ��������property 
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
     * Object:��ʱ��'s ��������property 
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
     * Object:��ʱ��'s RTS����property 
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