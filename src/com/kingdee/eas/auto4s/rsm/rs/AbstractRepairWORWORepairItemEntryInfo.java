package com.kingdee.eas.auto4s.rsm.rs;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractRepairWORWORepairItemEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractRepairWORWORepairItemEntryInfo()
    {
        this("id");
    }
    protected AbstractRepairWORWORepairItemEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: ά����Ŀ 's null property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo getParent()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo item)
    {
        put("parent", item);
    }
    /**
     * Object: ά����Ŀ 's ������� property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.PaymentClassifyInfo getPaymentClassify()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.PaymentClassifyInfo)get("PaymentClassify");
    }
    public void setPaymentClassify(com.kingdee.eas.auto4s.bdm.rsm.PaymentClassifyInfo item)
    {
        put("PaymentClassify", item);
    }
    /**
     * Object:ά����Ŀ's �������property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum getSettleObject()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum.getEnum(getString("SettleObject"));
    }
    public void setSettleObject(com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum item)
    {
		if (item != null) {
        setString("SettleObject", item.getValue());
		}
    }
    /**
     * Object: ά����Ŀ 's ά������ property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyInfo getRepairClassify()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyInfo)get("RepairClassify");
    }
    public void setRepairClassify(com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyInfo item)
    {
        put("RepairClassify", item);
    }
    /**
     * Object: ά����Ŀ 's �ײ� property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairPkgInfo getRepairPkg()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairPkgInfo)get("RepairPkg");
    }
    public void setRepairPkg(com.kingdee.eas.auto4s.bdm.rsm.RepairPkgInfo item)
    {
        put("RepairPkg", item);
    }
    /**
     * Object:ά����Ŀ's ��׼��ʱproperty 
     */
    public java.math.BigDecimal getStdWorkTime()
    {
        return getBigDecimal("StdWorkTime");
    }
    public void setStdWorkTime(java.math.BigDecimal item)
    {
        setBigDecimal("StdWorkTime", item);
    }
    /**
     * Object:ά����Ŀ's ��ʱ����property 
     */
    public java.math.BigDecimal getWorkTimePrice()
    {
        return getBigDecimal("WorkTimePrice");
    }
    public void setWorkTimePrice(java.math.BigDecimal item)
    {
        setBigDecimal("WorkTimePrice", item);
    }
    /**
     * Object:ά����Ŀ's ��ʱ���property 
     */
    public java.math.BigDecimal getWorkTimeAmount()
    {
        return getBigDecimal("WorkTimeAmount");
    }
    public void setWorkTimeAmount(java.math.BigDecimal item)
    {
        setBigDecimal("WorkTimeAmount", item);
    }
    /**
     * Object:ά����Ŀ's �ۿ���(%)property 
     */
    public java.math.BigDecimal getDiscountRate()
    {
        return getBigDecimal("DiscountRate");
    }
    public void setDiscountRate(java.math.BigDecimal item)
    {
        setBigDecimal("DiscountRate", item);
    }
    /**
     * Object:ά����Ŀ's �Żݽ��property 
     */
    public java.math.BigDecimal getDiscountAmount()
    {
        return getBigDecimal("DiscountAmount");
    }
    public void setDiscountAmount(java.math.BigDecimal item)
    {
        setBigDecimal("DiscountAmount", item);
    }
    /**
     * Object:ά����Ŀ's Ӧ�ս��property 
     */
    public java.math.BigDecimal getARAmount()
    {
        return getBigDecimal("ARAmount");
    }
    public void setARAmount(java.math.BigDecimal item)
    {
        setBigDecimal("ARAmount", item);
    }
    /**
     * Object:ά����Ŀ's �Ƿ�׷��property 
     */
    public boolean isIsAppend()
    {
        return getBoolean("IsAppend");
    }
    public void setIsAppend(boolean item)
    {
        setBoolean("IsAppend", item);
    }
    /**
     * Object:ά����Ŀ's �Ƿ���property 
     */
    public boolean isIsReturnRepair()
    {
        return getBoolean("IsReturnRepair");
    }
    public void setIsReturnRepair(boolean item)
    {
        setBoolean("IsReturnRepair", item);
    }
    /**
     * Object:ά����Ŀ's ɾ�����property 
     */
    public boolean isIsDelete()
    {
        return getBoolean("IsDelete");
    }
    public void setIsDelete(boolean item)
    {
        setBoolean("IsDelete", item);
    }
    /**
     * Object:ά����Ŀ's ά����Ŀ״̬property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairItemStatusEnum getItemStatus()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.RepairItemStatusEnum.getEnum(getString("ItemStatus"));
    }
    public void setItemStatus(com.kingdee.eas.auto4s.bdm.rsm.RepairItemStatusEnum item)
    {
		if (item != null) {
        setString("ItemStatus", item.getValue());
		}
    }
    /**
     * Object: ά����Ŀ 's ����ԭ�� property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.ReworkReasonInfo getReworkReason()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.ReworkReasonInfo)get("ReworkReason");
    }
    public void setReworkReason(com.kingdee.eas.auto4s.bdm.rsm.ReworkReasonInfo item)
    {
        put("ReworkReason", item);
    }
    /**
     * Object: ά����Ŀ 's ά�޹�λ property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.WorkstationInfo getWorkStation()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.WorkstationInfo)get("WorkStation");
    }
    public void setWorkStation(com.kingdee.eas.auto4s.bdm.rsm.WorkstationInfo item)
    {
        put("WorkStation", item);
    }
    /**
     * Object:ά����Ŀ's ʵ�ʹ�ʱproperty 
     */
    public java.math.BigDecimal getActualWorkTime()
    {
        return getBigDecimal("ActualWorkTime");
    }
    public void setActualWorkTime(java.math.BigDecimal item)
    {
        setBigDecimal("ActualWorkTime", item);
    }
    /**
     * Object:ά����Ŀ's ά����Ŀ˵��property 
     */
    public String getItemRemark()
    {
        return getString("ItemRemark");
    }
    public void setItemRemark(String item)
    {
        setString("ItemRemark", item);
    }
    /**
     * Object:ά����Ŀ's �ɹ���ʱproperty 
     */
    public java.math.BigDecimal getAssignWorkTime()
    {
        return getBigDecimal("AssignWorkTime");
    }
    public void setAssignWorkTime(java.math.BigDecimal item)
    {
        setBigDecimal("AssignWorkTime", item);
    }
    /**
     * Object:ά����Ŀ's ʵ�ս��property 
     */
    public java.math.BigDecimal getActualAmount()
    {
        return getBigDecimal("ActualAmount");
    }
    public void setActualAmount(java.math.BigDecimal item)
    {
        setBigDecimal("ActualAmount", item);
    }
    /**
     * Object:ά����Ŀ's Ԥ�ƿ�ʼʱ��property 
     */
    public java.sql.Time getStartTime()
    {
        return getTime("StartTime");
    }
    public void setStartTime(java.sql.Time item)
    {
        setTime("StartTime", item);
    }
    /**
     * Object:ά����Ŀ's ʵ�ʿ�ʼʱ��property 
     */
    public java.sql.Time getActualStartTime()
    {
        return getTime("ActualStartTime");
    }
    public void setActualStartTime(java.sql.Time item)
    {
        setTime("ActualStartTime", item);
    }
    /**
     * Object:ά����Ŀ's ʵ�ʽ���ʱ��property 
     */
    public java.sql.Time getActualFinishTime()
    {
        return getTime("ActualFinishTime");
    }
    public void setActualFinishTime(java.sql.Time item)
    {
        setTime("ActualFinishTime", item);
    }
    /**
     * Object: ά����Ŀ 's �ʼ�Ա property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getInspector()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("Inspector");
    }
    public void setInspector(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("Inspector", item);
    }
    /**
     * Object:ά����Ŀ's �ʼ�ʱ��property 
     */
    public java.util.Date getInspectTime()
    {
        return getDate("InspectTime");
    }
    public void setInspectTime(java.util.Date item)
    {
        setDate("InspectTime", item);
    }
    /**
     * Object:ά����Ŀ's �ʼ���property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairInspectEnum getResult()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.RepairInspectEnum.getEnum(getString("Result"));
    }
    public void setResult(com.kingdee.eas.auto4s.bdm.rsm.RepairInspectEnum item)
    {
		if (item != null) {
        setString("Result", item.getValue());
		}
    }
    /**
     * Object:ά����Ŀ's ����ʱ��ά����Ŀ״̬property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairItemStatusEnum getStatusWhenCanceled()
    {
        return com.kingdee.eas.auto4s.bdm.rsm.RepairItemStatusEnum.getEnum(getString("StatusWhenCanceled"));
    }
    public void setStatusWhenCanceled(com.kingdee.eas.auto4s.bdm.rsm.RepairItemStatusEnum item)
    {
		if (item != null) {
        setString("StatusWhenCanceled", item.getValue());
		}
    }
    /**
     * Object:ά����Ŀ's Ԥ�ƽ���ʱ��property 
     */
    public java.sql.Time getFinishTime()
    {
        return getTime("FinishTime");
    }
    public void setFinishTime(java.sql.Time item)
    {
        setTime("FinishTime", item);
    }
    /**
     * Object: ά����Ŀ 's ���� property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.ServiceActivityInfo getServiceActivity()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.ServiceActivityInfo)get("ServiceActivity");
    }
    public void setServiceActivity(com.kingdee.eas.auto4s.rsm.rs.ServiceActivityInfo item)
    {
        put("ServiceActivity", item);
    }
    /**
     * Object: ά����Ŀ 's ά�޼�ʦ property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getPerson()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("Person");
    }
    public void setPerson(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("Person", item);
    }
    /**
     * Object: ά����Ŀ 's ά�ް��� property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairGroupInfo getWorkGroup()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairGroupInfo)get("WorkGroup");
    }
    public void setWorkGroup(com.kingdee.eas.auto4s.bdm.rsm.RepairGroupInfo item)
    {
        put("WorkGroup", item);
    }
    /**
     * Object: ά����Ŀ 's ά����Ŀ���� property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo getRepairItem()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo)get("RepairItem");
    }
    public void setRepairItem(com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo item)
    {
        put("RepairItem", item);
    }
    /**
     * Object:ά����Ŀ's ���ʵ���property 
     */
    public java.math.BigDecimal getWagePrice()
    {
        return getBigDecimal("WagePrice");
    }
    public void setWagePrice(java.math.BigDecimal item)
    {
        setBigDecimal("WagePrice", item);
    }
    /**
     * Object:ά����Ŀ's ��ʱ�ɱ�property 
     */
    public java.math.BigDecimal getWorkTimeCost()
    {
        return getBigDecimal("WorkTimeCost");
    }
    public void setWorkTimeCost(java.math.BigDecimal item)
    {
        setBigDecimal("WorkTimeCost", item);
    }
    /**
     * Object: ά����Ŀ 's Դ������ property 
     */
    public com.kingdee.eas.basedata.scm.common.BillTypeInfo getSourceBillType()
    {
        return (com.kingdee.eas.basedata.scm.common.BillTypeInfo)get("SourceBillType");
    }
    public void setSourceBillType(com.kingdee.eas.basedata.scm.common.BillTypeInfo item)
    {
        put("SourceBillType", item);
    }
    /**
     * Object:ά����Ŀ's Դ��IDproperty 
     */
    public String getSourceBillID()
    {
        return getString("SourceBillID");
    }
    public void setSourceBillID(String item)
    {
        setString("SourceBillID", item);
    }
    /**
     * Object:ά����Ŀ's Դ������property 
     */
    public String getSourceBillNumber()
    {
        return getString("SourceBillNumber");
    }
    public void setSourceBillNumber(String item)
    {
        setString("SourceBillNumber", item);
    }
    /**
     * Object:ά����Ŀ's Դ����¼IDproperty 
     */
    public String getSourceBillEntryID()
    {
        return getString("SourceBillEntryID");
    }
    public void setSourceBillEntryID(String item)
    {
        setString("SourceBillEntryID", item);
    }
    /**
     * Object:ά����Ŀ's Դ���к�property 
     */
    public int getSourceBillEntrySeq()
    {
        return getInt("SourceBillEntrySeq");
    }
    public void setSourceBillEntrySeq(int item)
    {
        setInt("SourceBillEntrySeq", item);
    }
    /**
     * Object:ά����Ŀ's ��ʱ�Ƿ�����޸�property 
     */
    public boolean isIsTimeEdit()
    {
        return getBoolean("IsTimeEdit");
    }
    public void setIsTimeEdit(boolean item)
    {
        setBoolean("IsTimeEdit", item);
    }
    /**
     * Object:ά����Ŀ's ԭ�ۿ���(%)property 
     */
    public java.math.BigDecimal getOldDiscountRate()
    {
        return getBigDecimal("OldDiscountRate");
    }
    public void setOldDiscountRate(java.math.BigDecimal item)
    {
        setBigDecimal("OldDiscountRate", item);
    }
    /**
     * Object:ά����Ŀ's �Ƿ�����ɹ�property 
     */
    public boolean isIsMulTiAssign()
    {
        return getBoolean("IsMulTiAssign");
    }
    public void setIsMulTiAssign(boolean item)
    {
        setBoolean("IsMulTiAssign", item);
    }
    /**
     * Object:ά����Ŀ's ��Ա������ĿIDproperty 
     */
    public String getFCardRepServiceID()
    {
        return getString("FCardRepServiceID");
    }
    public void setFCardRepServiceID(String item)
    {
        setString("FCardRepServiceID", item);
    }
    /**
     * Object: ά����Ŀ 's ά������ property 
     */
    public com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo getRepairType()
    {
        return (com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo)get("RepairType");
    }
    public void setRepairType(com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo item)
    {
        put("RepairType", item);
    }
    /**
     * Object:ά����Ŀ's ��ʱ��׼����property 
     */
    public java.math.BigDecimal getWorkTimeStdPrice()
    {
        return getBigDecimal("WorkTimeStdPrice");
    }
    public void setWorkTimeStdPrice(java.math.BigDecimal item)
    {
        setBigDecimal("WorkTimeStdPrice", item);
    }
    /**
     * Object:ά����Ŀ's DMS�к�property 
     */
    public int getWipLineNo()
    {
        return getInt("wipLineNo");
    }
    public void setWipLineNo(int item)
    {
        setInt("wipLineNo", item);
    }
    /**
     * Object:ά����Ŀ's DMSʵ���к�property 
     */
    public int getWipFactLineNo()
    {
        return getInt("wipFactLineNo");
    }
    public void setWipFactLineNo(int item)
    {
        setInt("wipFactLineNo", item);
    }
    /**
     * Object:ά����Ŀ's ��Ŀ/�����ϸ��¼IDproperty 
     */
    public String getItemSpEntryId()
    {
        return getString("itemSpEntryId");
    }
    public void setItemSpEntryId(String item)
    {
        setString("itemSpEntryId", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("185AD052");
    }
}