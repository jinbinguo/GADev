package com.kingdee.eas.ga.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractCustomerAccountInfo extends com.kingdee.eas.myframework.template.base.SimpleDatabaseInfo implements Serializable 
{
    public AbstractCustomerAccountInfo()
    {
        this("id");
    }
    protected AbstractCustomerAccountInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:�ͻ��˻�'s ������������property 
     */
    public String getRetailSaleType()
    {
        return getString("retailSaleType");
    }
    public void setRetailSaleType(String item)
    {
        setString("retailSaleType", item);
    }
    /**
     * Object:�ͻ��˻�'s �����ۿ���%property 
     */
    public java.math.BigDecimal getRetailDiscountRate()
    {
        return getBigDecimal("retailDiscountRate");
    }
    public void setRetailDiscountRate(java.math.BigDecimal item)
    {
        setBigDecimal("retailDiscountRate", item);
    }
    /**
     * Object:�ͻ��˻�'s ά���ۿ���%property 
     */
    public java.math.BigDecimal getRepairDiscountRate()
    {
        return getBigDecimal("repairDiscountRate");
    }
    public void setRepairDiscountRate(java.math.BigDecimal item)
    {
        setBigDecimal("repairDiscountRate", item);
    }
    /**
     * Object:�ͻ��˻�'s ������property 
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
     * Object: �ͻ��˻� 's ��Ӧ����ͻ� property 
     */
    public com.kingdee.eas.basedata.master.cssp.CustomerInfo getFinCustomer()
    {
        return (com.kingdee.eas.basedata.master.cssp.CustomerInfo)get("finCustomer");
    }
    public void setFinCustomer(com.kingdee.eas.basedata.master.cssp.CustomerInfo item)
    {
        put("finCustomer", item);
    }
    /**
     * Object:�ͻ��˻�'s ά����������property 
     */
    public String getRepairSaleType()
    {
        return getString("repairSaleType");
    }
    public void setRepairSaleType(String item)
    {
        setString("repairSaleType", item);
    }
    /**
     * Object:�ͻ��˻�'s ��������property 
     */
    public com.kingdee.eas.ga.rs.SettlementTypeEnum getSettlementType()
    {
        return com.kingdee.eas.ga.rs.SettlementTypeEnum.getEnum(getInt("settlementType"));
    }
    public void setSettlementType(com.kingdee.eas.ga.rs.SettlementTypeEnum item)
    {
		if (item != null) {
        setInt("settlementType", item.getValue());
		}
    }
    /**
     * Object: �ͻ��˻� 's ��Ӧ��ҵ�ͻ� property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo getAtsCustomer()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo)get("atsCustomer");
    }
    public void setAtsCustomer(com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo item)
    {
        put("atsCustomer", item);
    }
    /**
     * Object: �ͻ��˻� 's ��˾ property 
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
        return new BOSObjectType("8B9E1F44");
    }
}