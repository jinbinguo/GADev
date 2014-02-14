package com.kingdee.eas.ga.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractWInfo extends com.kingdee.eas.myframework.template.base.SimpleDatabaseInfo implements Serializable 
{
    public AbstractWInfo()
    {
        this("id");
    }
    protected AbstractWInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:W's 类别代码property 
     */
    public String getTypeCode()
    {
        return getString("typeCode");
    }
    public void setTypeCode(String item)
    {
        setString("typeCode", item);
    }
    /**
     * Object:W's 结算对象property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum getSettleObject()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum.getEnum(getString("settleObject"));
    }
    public void setSettleObject(com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum item)
    {
		if (item != null) {
        setString("settleObject", item.getValue());
		}
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("9871DC0C");
    }
}