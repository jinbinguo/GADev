package com.kingdee.eas.auto4s.bdm.pbd;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractVehicleRepairSenderInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractVehicleRepairSenderInfo()
    {
        this("id");
    }
    protected AbstractVehicleRepairSenderInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: ά������Ϣ 's null property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo getParent()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:ά������Ϣ's ����������property 
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
     * Object:ά������Ϣ's �����˵绰property 
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
     * Object:ά������Ϣ's �����˵�ַproperty 
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
     * Object:ά������Ϣ's �ʱ�property 
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
     * Object:ά������Ϣ's ���֤��property 
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
     * Object:ά������Ϣ's ����property 
     */
    public String getEmail()
    {
        return getString("email");
    }
    public void setEmail(String item)
    {
        setString("email", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("75621C07");
    }
}