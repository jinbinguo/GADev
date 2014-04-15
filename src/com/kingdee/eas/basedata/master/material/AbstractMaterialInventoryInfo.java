package com.kingdee.eas.basedata.master.material;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractMaterialInventoryInfo extends com.kingdee.eas.basedata.master.material.MaterialPropertyBaseInfo implements Serializable 
{
    public AbstractMaterialInventoryInfo()
    {
        this("id");
    }
    protected AbstractMaterialInventoryInfo(String pkField)
    {
        super(pkField);
        put("MaterialLoc", new com.kingdee.eas.basedata.master.material.MaterialInventoryMaterialLocCollection());
    }
    /**
     * Object:物料库存资料's 库存管理property 
     */
    public boolean isIsControl()
    {
        return getBoolean("isControl");
    }
    public void setIsControl(boolean item)
    {
        setBoolean("isControl", item);
    }
    /**
     * Object:物料库存资料's 安全库存量property 
     */
    public java.math.BigDecimal getQtySafety()
    {
        return getBigDecimal("qtySafety");
    }
    public void setQtySafety(java.math.BigDecimal item)
    {
        setBigDecimal("qtySafety", item);
    }
    /**
     * Object:物料库存资料's 最低库存量property 
     */
    public java.math.BigDecimal getQtyMin()
    {
        return getBigDecimal("qtyMin");
    }
    public void setQtyMin(java.math.BigDecimal item)
    {
        setBigDecimal("qtyMin", item);
    }
    /**
     * Object:物料库存资料's 最高库存量property 
     */
    public java.math.BigDecimal getQtyMax()
    {
        return getBigDecimal("qtyMax");
    }
    public void setQtyMax(java.math.BigDecimal item)
    {
        setBigDecimal("qtyMax", item);
    }
    /**
     * Object:物料库存资料's 库存下限天数property 
     */
    public int getDaysBottom()
    {
        return getInt("daysBottom");
    }
    public void setDaysBottom(int item)
    {
        setInt("daysBottom", item);
    }
    /**
     * Object:物料库存资料's 库存上限天数property 
     */
    public int getDaysTop()
    {
        return getInt("daysTop");
    }
    public void setDaysTop(int item)
    {
        setInt("daysTop", item);
    }
    /**
     * Object:物料库存资料's 最大周转天数property 
     */
    public int getDaysTurnover()
    {
        return getInt("daysTurnover");
    }
    public void setDaysTurnover(int item)
    {
        setInt("daysTurnover", item);
    }
    /**
     * Object:物料库存资料's 允许负库存property 
     */
    public boolean isIsNegative()
    {
        return getBoolean("isNegative");
    }
    public void setIsNegative(boolean item)
    {
        setBoolean("isNegative", item);
    }
    /**
     * Object:物料库存资料's 使用批号管理property 
     */
    public boolean isIsBatchNo()
    {
        return getBoolean("isBatchNo");
    }
    public void setIsBatchNo(boolean item)
    {
        setBoolean("isBatchNo", item);
    }
    /**
     * Object:物料库存资料's 使用序列号管理property 
     */
    public boolean isIsSequenceNo()
    {
        return getBoolean("isSequenceNo");
    }
    public void setIsSequenceNo(boolean item)
    {
        setBoolean("isSequenceNo", item);
    }
    /**
     * Object:物料库存资料's 批次管理property 
     */
    public boolean isIsLotNumber()
    {
        return getBoolean("isLotNumber");
    }
    public void setIsLotNumber(boolean item)
    {
        setBoolean("isLotNumber", item);
    }
    /**
     * Object:物料库存资料's 条码管理property 
     */
    public boolean isIsBarcode()
    {
        return getBoolean("isBarcode");
    }
    public void setIsBarcode(boolean item)
    {
        setBoolean("isBarcode", item);
    }
    /**
     * Object:物料库存资料's 最小包装量property 
     */
    public java.math.BigDecimal getQtyMinPackage()
    {
        return getBigDecimal("qtyMinPackage");
    }
    public void setQtyMinPackage(java.math.BigDecimal item)
    {
        setBigDecimal("qtyMinPackage", item);
    }
    /**
     * Object:物料库存资料's ABC分类property 
     */
    public com.kingdee.eas.basedata.master.material.ABCEnum getAbcType()
    {
        return com.kingdee.eas.basedata.master.material.ABCEnum.getEnum(getInt("abcType"));
    }
    public void setAbcType(com.kingdee.eas.basedata.master.material.ABCEnum item)
    {
		if (item != null) {
        setInt("abcType", item.getValue());
		}
    }
    /**
     * Object:物料库存资料's 组合件property 
     */
    public boolean isIsCompages()
    {
        return getBoolean("isCompages");
    }
    public void setIsCompages(boolean item)
    {
        setBoolean("isCompages", item);
    }
    /**
     * Object:物料库存资料's 出库优先方式property 
     */
    public com.kingdee.eas.basedata.master.material.IssuePriorityEnum getIssuePriorityMode()
    {
        return com.kingdee.eas.basedata.master.material.IssuePriorityEnum.getEnum(getInt("issuePriorityMode"));
    }
    public void setIssuePriorityMode(com.kingdee.eas.basedata.master.material.IssuePriorityEnum item)
    {
		if (item != null) {
        setInt("issuePriorityMode", item.getValue());
		}
    }
    /**
     * Object: 物料库存资料 's 库存计量单位 property 
     */
    public com.kingdee.eas.basedata.assistant.MeasureUnitInfo getUnit()
    {
        return (com.kingdee.eas.basedata.assistant.MeasureUnitInfo)get("unit");
    }
    public void setUnit(com.kingdee.eas.basedata.assistant.MeasureUnitInfo item)
    {
        put("unit", item);
    }
    /**
     * Object:物料库存资料's 保质期管理property 
     */
    public boolean isIsPeriodValid()
    {
        return getBoolean("isPeriodValid");
    }
    public void setIsPeriodValid(boolean item)
    {
        setBoolean("isPeriodValid", item);
    }
    /**
     * Object:物料库存资料's 保质期property 
     */
    public int getPeriodValid()
    {
        return getInt("periodValid");
    }
    public void setPeriodValid(int item)
    {
        setInt("periodValid", item);
    }
    /**
     * Object:物料库存资料's 保质期单位property 
     */
    public com.kingdee.eas.basedata.master.material.TimeUnitEnum getPeriodValidUnit()
    {
        return com.kingdee.eas.basedata.master.material.TimeUnitEnum.getEnum(getInt("periodValidUnit"));
    }
    public void setPeriodValidUnit(com.kingdee.eas.basedata.master.material.TimeUnitEnum item)
    {
		if (item != null) {
        setInt("periodValidUnit", item.getValue());
		}
    }
    /**
     * Object:物料库存资料's 入库失效提前期property 
     */
    public int getInWarehsAhead()
    {
        return getInt("inWarehsAhead");
    }
    public void setInWarehsAhead(int item)
    {
        setInt("inWarehsAhead", item);
    }
    /**
     * Object:物料库存资料's 出库失效提前期property 
     */
    public int getOutWarehsAhead()
    {
        return getInt("outWarehsAhead");
    }
    public void setOutWarehsAhead(int item)
    {
        setInt("outWarehsAhead", item);
    }
    /**
     * Object:物料库存资料's 预警提前期property 
     */
    public int getPrepWarnAhead()
    {
        return getInt("prepWarnAhead");
    }
    public void setPrepWarnAhead(int item)
    {
        setInt("prepWarnAhead", item);
    }
    /**
     * Object:物料库存资料's 提前期单位property 
     */
    public com.kingdee.eas.basedata.master.material.TimeUnitEnum getAheadUnit()
    {
        return com.kingdee.eas.basedata.master.material.TimeUnitEnum.getEnum(getInt("aheadUnit"));
    }
    public void setAheadUnit(com.kingdee.eas.basedata.master.material.TimeUnitEnum item)
    {
		if (item != null) {
        setInt("aheadUnit", item.getValue());
		}
    }
    /**
     * Object: 物料库存资料 's 库存计划员 property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getInvPlanner()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("invPlanner");
    }
    public void setInvPlanner(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("invPlanner", item);
    }
    /**
     * Object:物料库存资料's 计划方式property 
     */
    public com.kingdee.eas.basedata.master.material.PlanningModeEnum getPlanningMode()
    {
        return com.kingdee.eas.basedata.master.material.PlanningModeEnum.getEnum(getInt("planningMode"));
    }
    public void setPlanningMode(com.kingdee.eas.basedata.master.material.PlanningModeEnum item)
    {
		if (item != null) {
        setInt("planningMode", item.getValue());
		}
    }
    /**
     * Object:物料库存资料's 再订货点property 
     */
    public java.math.BigDecimal getReBookQty()
    {
        return getBigDecimal("reBookQty");
    }
    public void setReBookQty(java.math.BigDecimal item)
    {
        setBigDecimal("reBookQty", item);
    }
    /**
     * Object:物料库存资料's 物料消耗速率property 
     */
    public java.math.BigDecimal getConsumeSpeed()
    {
        return getBigDecimal("consumeSpeed");
    }
    public void setConsumeSpeed(java.math.BigDecimal item)
    {
        setBigDecimal("consumeSpeed", item);
    }
    /**
     * Object:物料库存资料's 采购提前期（天）property 
     */
    public int getPurchasingAheadDate()
    {
        return getInt("purchasingAheadDate");
    }
    public void setPurchasingAheadDate(int item)
    {
        setInt("purchasingAheadDate", item);
    }
    /**
     * Object:物料库存资料's 批量政策property 
     */
    public com.kingdee.eas.basedata.master.material.BatchPolicyEnum getBatchPolicy()
    {
        return com.kingdee.eas.basedata.master.material.BatchPolicyEnum.getEnum(getInt("batchPolicy"));
    }
    public void setBatchPolicy(com.kingdee.eas.basedata.master.material.BatchPolicyEnum item)
    {
		if (item != null) {
        setInt("batchPolicy", item.getValue());
		}
    }
    /**
     * Object:物料库存资料's 固定批量property 
     */
    public java.math.BigDecimal getFixationBatchQty()
    {
        return getBigDecimal("fixationBatchQty");
    }
    public void setFixationBatchQty(java.math.BigDecimal item)
    {
        setBigDecimal("fixationBatchQty", item);
    }
    /**
     * Object: 物料库存资料 's 默认仓库 property 
     */
    public com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo getDefaultWarehouse()
    {
        return (com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo)get("defaultWarehouse");
    }
    public void setDefaultWarehouse(com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo item)
    {
        put("defaultWarehouse", item);
    }
    /**
     * Object:物料库存资料's 目标周转天数property 
     */
    public java.math.BigDecimal getDaysPlanTurnover()
    {
        return getBigDecimal("daysPlanTurnover");
    }
    public void setDaysPlanTurnover(java.math.BigDecimal item)
    {
        setBigDecimal("daysPlanTurnover", item);
    }
    /**
     * Object:物料库存资料's 减价比例(%)property 
     */
    public java.math.BigDecimal getCheapRate()
    {
        return getBigDecimal("cheapRate");
    }
    public void setCheapRate(java.math.BigDecimal item)
    {
        setBigDecimal("cheapRate", item);
    }
    /**
     * Object: 物料库存资料 's 质检组织 property 
     */
    public com.kingdee.eas.basedata.org.QualityOrgUnitInfo getQualityOrg()
    {
        return (com.kingdee.eas.basedata.org.QualityOrgUnitInfo)get("qualityOrg");
    }
    public void setQualityOrg(com.kingdee.eas.basedata.org.QualityOrgUnitInfo item)
    {
        put("qualityOrg", item);
    }
    /**
     * Object:物料库存资料's 检验property 
     */
    public boolean isIsCheck()
    {
        return getBoolean("isCheck");
    }
    public void setIsCheck(boolean item)
    {
        setBoolean("isCheck", item);
    }
    /**
     * Object:物料库存资料's 是否项目号property 
     */
    public boolean isIsProjectNumber()
    {
        return getBoolean("isProjectNumber");
    }
    public void setIsProjectNumber(boolean item)
    {
        setBoolean("isProjectNumber", item);
    }
    /**
     * Object:物料库存资料's 是否跟踪号property 
     */
    public boolean isIsTrackingNumber()
    {
        return getBoolean("isTrackingNumber");
    }
    public void setIsTrackingNumber(boolean item)
    {
        setBoolean("isTrackingNumber", item);
    }
    /**
     * Object:物料库存资料's 工具费率property 
     */
    public java.math.BigDecimal getToolRate()
    {
        return getBigDecimal("toolRate");
    }
    public void setToolRate(java.math.BigDecimal item)
    {
        setBigDecimal("toolRate", item);
    }
    /**
     * Object: 物料库存资料 's 默认库位 property 
     */
    public com.kingdee.eas.basedata.scm.im.inv.LocationInfo getLocation()
    {
        return (com.kingdee.eas.basedata.scm.im.inv.LocationInfo)get("location");
    }
    public void setLocation(com.kingdee.eas.basedata.scm.im.inv.LocationInfo item)
    {
        put("location", item);
    }
    /**
     * Object:物料库存资料's 数量property 
     */
    public java.math.BigDecimal getCostQty()
    {
        return getBigDecimal("CostQty");
    }
    public void setCostQty(java.math.BigDecimal item)
    {
        setBigDecimal("CostQty", item);
    }
    /**
     * Object:物料库存资料's 成本价property 
     */
    public java.math.BigDecimal getCostPrice()
    {
        return getBigDecimal("CostPrice");
    }
    public void setCostPrice(java.math.BigDecimal item)
    {
        setBigDecimal("CostPrice", item);
    }
    /**
     * Object: 物料库存资料 's 物料货位 property 
     */
    public com.kingdee.eas.basedata.master.material.MaterialInventoryMaterialLocCollection getMaterialLoc()
    {
        return (com.kingdee.eas.basedata.master.material.MaterialInventoryMaterialLocCollection)get("MaterialLoc");
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("557E499F");
    }
}