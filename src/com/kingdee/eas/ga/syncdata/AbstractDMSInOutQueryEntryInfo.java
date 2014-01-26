package com.kingdee.eas.ga.syncdata;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractDMSInOutQueryEntryInfo extends com.kingdee.eas.myframework.template.base.SimpleBizBillEntryInfo implements Serializable 
{
    public AbstractDMSInOutQueryEntryInfo()
    {
        this("id");
    }
    protected AbstractDMSInOutQueryEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 分录 's 单据头 property 
     */
    public com.kingdee.eas.ga.syncdata.DMSInOutQueryInfo getParent()
    {
        return (com.kingdee.eas.ga.syncdata.DMSInOutQueryInfo)get("Parent");
    }
    public void setParent(com.kingdee.eas.ga.syncdata.DMSInOutQueryInfo item)
    {
        put("Parent", item);
    }
    /**
     * Object:分录's 出库property 
     */
    public java.util.Date getBizDate()
    {
        return getDate("bizDate");
    }
    public void setBizDate(java.util.Date item)
    {
        setDate("bizDate", item);
    }
    /**
     * Object:分录's 选项property 
     */
    public String getOption()
    {
        return getString("option");
    }
    public void setOption(String item)
    {
        setString("option", item);
    }
    /**
     * Object:分录's P/O Rqnproperty 
     */
    public String getRqn()
    {
        return getString("rqn");
    }
    public void setRqn(String item)
    {
        setString("rqn", item);
    }
    /**
     * Object:分录's 账单property 
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
     * Object:分录's WIP号property 
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
     * Object:分录's 行号property 
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
     * Object:分录's 客户property 
     */
    public String getCustomer()
    {
        return getString("customer");
    }
    public void setCustomer(String item)
    {
        setString("customer", item);
    }
    /**
     * Object:分录's 供应商property 
     */
    public String getSupplier()
    {
        return getString("supplier");
    }
    public void setSupplier(String item)
    {
        setString("supplier", item);
    }
    /**
     * Object:分录's 零件编号property 
     */
    public String getMaterialNum()
    {
        return getString("materialNum");
    }
    public void setMaterialNum(String item)
    {
        setString("materialNum", item);
    }
    /**
     * Object:分录's 数量property 
     */
    public java.math.BigDecimal getQty()
    {
        return getBigDecimal("qty");
    }
    public void setQty(java.math.BigDecimal item)
    {
        setBigDecimal("qty", item);
    }
    /**
     * Object:分录's 供应数量property 
     */
    public java.math.BigDecimal getSupplyQty()
    {
        return getBigDecimal("supplyQty");
    }
    public void setSupplyQty(java.math.BigDecimal item)
    {
        setBigDecimal("supplyQty", item);
    }
    /**
     * Object:分录's 剩余数property 
     */
    public java.math.BigDecimal getRemainQty()
    {
        return getBigDecimal("remainQty");
    }
    public void setRemainQty(java.math.BigDecimal item)
    {
        setBigDecimal("remainQty", item);
    }
    /**
     * Object:分录's 审计property 
     */
    public String getAudit()
    {
        return getString("audit");
    }
    public void setAudit(String item)
    {
        setString("audit", item);
    }
    /**
     * Object:分录's 成本property 
     */
    public java.math.BigDecimal getCost()
    {
        return getBigDecimal("cost");
    }
    public void setCost(java.math.BigDecimal item)
    {
        setBigDecimal("cost", item);
    }
    /**
     * Object:分录's Lproperty 
     */
    public String getL()
    {
        return getString("L");
    }
    public void setL(String item)
    {
        setString("L", item);
    }
    /**
     * Object:分录's Tproperty 
     */
    public String getT()
    {
        return getString("T");
    }
    public void setT(String item)
    {
        setString("T", item);
    }
    /**
     * Object: 分录 's EAS供应商 property 
     */
    public com.kingdee.eas.basedata.master.cssp.SupplierInfo getEasSupplier()
    {
        return (com.kingdee.eas.basedata.master.cssp.SupplierInfo)get("easSupplier");
    }
    public void setEasSupplier(com.kingdee.eas.basedata.master.cssp.SupplierInfo item)
    {
        put("easSupplier", item);
    }
    /**
     * Object: 分录 's EAS客户 property 
     */
    public com.kingdee.eas.basedata.master.cssp.CustomerInfo getEasCustomer()
    {
        return (com.kingdee.eas.basedata.master.cssp.CustomerInfo)get("easCustomer");
    }
    public void setEasCustomer(com.kingdee.eas.basedata.master.cssp.CustomerInfo item)
    {
        put("easCustomer", item);
    }
    /**
     * Object: 分录 's EAS仓库 property 
     */
    public com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo getEasWarehouse()
    {
        return (com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo)get("easWarehouse");
    }
    public void setEasWarehouse(com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo item)
    {
        put("easWarehouse", item);
    }
    /**
     * Object: 分录 's EAS维修工单 property 
     */
    public com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo getEasRepairWO()
    {
        return (com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo)get("easRepairWO");
    }
    public void setEasRepairWO(com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo item)
    {
        put("easRepairWO", item);
    }
    /**
     * Object:分录's EAS含税单价property 
     */
    public java.math.BigDecimal getEasTaxPrice()
    {
        return getBigDecimal("easTaxPrice");
    }
    public void setEasTaxPrice(java.math.BigDecimal item)
    {
        setBigDecimal("easTaxPrice", item);
    }
    /**
     * Object: 分录 's EAS物料 property 
     */
    public com.kingdee.eas.basedata.master.material.MaterialInfo getEasMaterial()
    {
        return (com.kingdee.eas.basedata.master.material.MaterialInfo)get("easMaterial");
    }
    public void setEasMaterial(com.kingdee.eas.basedata.master.material.MaterialInfo item)
    {
        put("easMaterial", item);
    }
    /**
     * Object: 分录 's EAS基本计量单位 property 
     */
    public com.kingdee.eas.basedata.assistant.MeasureUnitInfo getEasBaseUnit()
    {
        return (com.kingdee.eas.basedata.assistant.MeasureUnitInfo)get("easBaseUnit");
    }
    public void setEasBaseUnit(com.kingdee.eas.basedata.assistant.MeasureUnitInfo item)
    {
        put("easBaseUnit", item);
    }
    /**
     * Object:分录's EAS维修工单配件分录IDproperty 
     */
    public String getEasRepairWOEntryId()
    {
        return getString("easRepairWOEntryId");
    }
    public void setEasRepairWOEntryId(String item)
    {
        setString("easRepairWOEntryId", item);
    }
    /**
     * Object: 分录 's EAS车辆 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo getEasVehicle()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo)get("easVehicle");
    }
    public void setEasVehicle(com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo item)
    {
        put("easVehicle", item);
    }
    /**
     * Object:分录's EAS工单配件行号property 
     */
    public int getEasRepairWOEntrySeq()
    {
        return getInt("easRepairWOEntrySeq");
    }
    public void setEasRepairWOEntrySeq(int item)
    {
        setInt("easRepairWOEntrySeq", item);
    }
    /**
     * Object:分录's EAS维修工单号property 
     */
    public String getEasRepairWONumber()
    {
        return getString("easRepairWONumber");
    }
    public void setEasRepairWONumber(String item)
    {
        setString("easRepairWONumber", item);
    }
    /**
     * Object:分录's 已转入property 
     */
    public boolean isIsTransferred()
    {
        return getBoolean("isTransferred");
    }
    public void setIsTransferred(boolean item)
    {
        setBoolean("isTransferred", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("861DB430");
    }
}