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
     * Object: 维修项目 's null property 
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
     * Object: 维修项目 's 付费类别 property 
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
     * Object:维修项目's 结算对象property 
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
     * Object: 维修项目 's 维修种类 property 
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
     * Object: 维修项目 's 套餐 property 
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
     * Object:维修项目's 标准工时property 
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
     * Object:维修项目's 工时单价property 
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
     * Object:维修项目's 工时金额property 
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
     * Object:维修项目's 折扣率(%)property 
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
     * Object:维修项目's 优惠金额property 
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
     * Object:维修项目's 应收金额property 
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
     * Object:维修项目's 是否追加property 
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
     * Object:维修项目's 是否返修property 
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
     * Object:维修项目's 删除标记property 
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
     * Object:维修项目's 维修项目状态property 
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
     * Object: 维修项目 's 返修原因 property 
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
     * Object: 维修项目 's 维修工位 property 
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
     * Object:维修项目's 实际工时property 
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
     * Object:维修项目's 维修项目说明property 
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
     * Object:维修项目's 派工工时property 
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
     * Object:维修项目's 实收金额property 
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
     * Object:维修项目's 预计开始时间property 
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
     * Object:维修项目's 实际开始时间property 
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
     * Object:维修项目's 实际结束时间property 
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
     * Object: 维修项目 's 质检员 property 
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
     * Object:维修项目's 质检时间property 
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
     * Object:维修项目's 质检结果property 
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
     * Object:维修项目's 作废时的维修项目状态property 
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
     * Object:维修项目's 预计结束时间property 
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
     * Object: 维修项目 's 服务活动 property 
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
     * Object: 维修项目 's 维修技师 property 
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
     * Object: 维修项目 's 维修班组 property 
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
     * Object: 维修项目 's 维修项目编码 property 
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
     * Object:维修项目's 工资单价property 
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
     * Object:维修项目's 工时成本property 
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
     * Object: 维修项目 's 源单类型 property 
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
     * Object:维修项目's 源单IDproperty 
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
     * Object:维修项目's 源单单号property 
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
     * Object:维修项目's 源单分录IDproperty 
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
     * Object:维修项目's 源单行号property 
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
     * Object:维修项目's 工时是否可以修改property 
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
     * Object:维修项目's 原折扣率(%)property 
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
     * Object:维修项目's 是否多人派工property 
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
     * Object:维修项目's 会员服务项目IDproperty 
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
     * Object: 维修项目 's 维修类型 property 
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
     * Object:维修项目's 工时标准单价property 
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
     * Object:维修项目's DMS行号property 
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
     * Object:维修项目's DMS实际行号property 
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
     * Object:维修项目's 项目/配件明细分录IDproperty 
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