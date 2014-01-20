package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractDMSWipBillEntryInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillEntryInfo implements Serializable 
{
    public AbstractDMSWipBillEntryInfo()
    {
        this("id");
    }
    protected AbstractDMSWipBillEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: Wip表头 's 单据头 property 
     */
    public com.kingdee.eas.ga.syncdata.DMSWipBillInfo getParent()
    {
        return (com.kingdee.eas.ga.syncdata.DMSWipBillInfo)get("Parent");
    }
    public void setParent(com.kingdee.eas.ga.syncdata.DMSWipBillInfo item)
    {
        put("Parent", item);
    }
    /**
     * Object:Wip表头's 底盘号property 
     */
    public String getVin()
    {
        return getString("vin");
    }
    public void setVin(String item)
    {
        setString("vin", item);
    }
    /**
     * Object:Wip表头's 创建日期property 
     */
    public java.util.Date getCreateTime()
    {
        return getDate("createTime");
    }
    public void setCreateTime(java.util.Date item)
    {
        setDate("createTime", item);
    }
    /**
     * Object:Wip表头's 进厂里程数property 
     */
    public java.math.BigDecimal getMileage()
    {
        return getBigDecimal("mileage");
    }
    public void setMileage(java.math.BigDecimal item)
    {
        setBigDecimal("mileage", item);
    }
    /**
     * Object:Wip表头's 牌照号property 
     */
    public String getPlateNum()
    {
        return getString("plateNum");
    }
    public void setPlateNum(String item)
    {
        setString("plateNum", item);
    }
    /**
     * Object:Wip表头's WIP号property 
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
     * Object:Wip表头's 账户代码property 
     */
    public String getAccountCode()
    {
        return getString("accountCode");
    }
    public void setAccountCode(String item)
    {
        setString("accountCode", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("D84514B0");
    }
}