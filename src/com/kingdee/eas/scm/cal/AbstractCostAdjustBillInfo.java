package com.kingdee.eas.scm.cal;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractCostAdjustBillInfo extends com.kingdee.eas.scm.im.inv.InvBillBaseInfo implements Serializable 
{
    public AbstractCostAdjustBillInfo()
    {
        this("id");
    }
    protected AbstractCostAdjustBillInfo(String pkField)
    {
        super(pkField);
        put("entry", new com.kingdee.eas.scm.cal.CostAdjustBillEntryCollection());
    }
    /**
     * Object: �ɱ������� 's ��Ӧ�� property 
     */
    public com.kingdee.eas.basedata.master.cssp.SupplierInfo getSupplier()
    {
        return (com.kingdee.eas.basedata.master.cssp.SupplierInfo)get("supplier");
    }
    public void setSupplier(com.kingdee.eas.basedata.master.cssp.SupplierInfo item)
    {
        put("supplier", item);
    }
    /**
     * Object: �ɱ������� 's �ͻ� property 
     */
    public com.kingdee.eas.basedata.master.cssp.CustomerInfo getCustomer()
    {
        return (com.kingdee.eas.basedata.master.cssp.CustomerInfo)get("customer");
    }
    public void setCustomer(com.kingdee.eas.basedata.master.cssp.CustomerInfo item)
    {
        put("customer", item);
    }
    /**
     * Object: �ɱ������� 's �ɱ���������¼ property 
     */
    public com.kingdee.eas.scm.cal.CostAdjustBillEntryCollection getEntry()
    {
        return (com.kingdee.eas.scm.cal.CostAdjustBillEntryCollection)get("entry");
    }
    /**
     * Object:�ɱ�������'s ��������property 
     */
    public com.kingdee.eas.scm.cal.IssueTypeEnum getIssueType()
    {
        return com.kingdee.eas.scm.cal.IssueTypeEnum.getEnum(getInt("issueType"));
    }
    public void setIssueType(com.kingdee.eas.scm.cal.IssueTypeEnum item)
    {
		if (item != null) {
        setInt("issueType", item.getValue());
		}
    }
    /**
     * Object:�ɱ�������'s ��������property 
     */
    public com.kingdee.eas.scm.cal.CostAdjuestCreateTypeEnum getCreateType()
    {
        return com.kingdee.eas.scm.cal.CostAdjuestCreateTypeEnum.getEnum(getInt("createType"));
    }
    public void setCreateType(com.kingdee.eas.scm.cal.CostAdjuestCreateTypeEnum item)
    {
		if (item != null) {
        setInt("createType", item.getValue());
		}
    }
    /**
     * Object:�ɱ�������'s ҵ������property 
     */
    public com.kingdee.eas.scm.cal.CalculateKindEnum getCalculateKind()
    {
        return com.kingdee.eas.scm.cal.CalculateKindEnum.getEnum(getInt("calculateKind"));
    }
    public void setCalculateKind(com.kingdee.eas.scm.cal.CalculateKindEnum item)
    {
		if (item != null) {
        setInt("calculateKind", item.getValue());
		}
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("8FA62986");
    }
}