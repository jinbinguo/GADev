package com.kingdee.eas.ga.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairManInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillInfo implements Serializable 
{
    public AbstractRepairManInfo()
    {
        this("id");
    }
    protected AbstractRepairManInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.ga.rs.RepairManEntryCollection());
    }
    /**
     * Object: ������ 's ��¼ property 
     */
    public com.kingdee.eas.ga.rs.RepairManEntryCollection getEntrys()
    {
        return (com.kingdee.eas.ga.rs.RepairManEntryCollection)get("entrys");
    }
    /**
     * Object:������'s ����property 
     */
    public String getName()
    {
        return getString("name");
    }
    public void setName(String item)
    {
        setString("name", item);
    }
    /**
     * Object:������'s �绰property 
     */
    public String getTel()
    {
        return getString("tel");
    }
    public void setTel(String item)
    {
        setString("tel", item);
    }
    /**
     * Object:������'s ����property 
     */
    public String getEmail()
    {
        return getString("email");
    }
    public void setEmail(String item)
    {
        setString("email", item);
    }
    /**
     * Object:������'s ���֤��property 
     */
    public String getIdNumber()
    {
        return getString("idNumber");
    }
    public void setIdNumber(String item)
    {
        setString("idNumber", item);
    }
    /**
     * Object:������'s �ʱ�property 
     */
    public String getZipCode()
    {
        return getString("zipCode");
    }
    public void setZipCode(String item)
    {
        setString("zipCode", item);
    }
    /**
     * Object:������'s ��ַproperty 
     */
    public String getAddr()
    {
        return getString("addr");
    }
    public void setAddr(String item)
    {
        setString("addr", item);
    }
    /**
     * Object: ������ 's ��˾ property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("orgUnit");
    }
    public void setOrgUnit(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("orgUnit", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("40C83D22");
    }
}