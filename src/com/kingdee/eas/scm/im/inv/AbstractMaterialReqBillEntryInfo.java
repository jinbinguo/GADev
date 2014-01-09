package com.kingdee.eas.scm.im.inv;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractMaterialReqBillEntryInfo extends com.kingdee.eas.scm.im.inv.InvBillBaseEntryInfo implements Serializable 
{
    public AbstractMaterialReqBillEntryInfo()
    {
        this("id");
    }
    protected AbstractMaterialReqBillEntryInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 领料出库单体 's 领料出库单头 property 
     */
    public com.kingdee.eas.scm.im.inv.MaterialReqBillInfo getParent()
    {
        return (com.kingdee.eas.scm.im.inv.MaterialReqBillInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.scm.im.inv.MaterialReqBillInfo item)
    {
        put("parent", item);
    }
    /**
     * Object: 领料出库单体 's 产品编码 property 
     */
    public com.kingdee.eas.basedata.master.material.MaterialInfo getProductID()
    {
        return (com.kingdee.eas.basedata.master.material.MaterialInfo)get("productID");
    }
    public void setProductID(com.kingdee.eas.basedata.master.material.MaterialInfo item)
    {
        put("productID", item);
    }
    /**
     * Object: 领料出库单体 's 成本对象 property 
     */
    public com.kingdee.eas.basedata.assistant.CostObjectInfo getCostObject()
    {
        return (com.kingdee.eas.basedata.assistant.CostObjectInfo)get("costObject");
    }
    public void setCostObject(com.kingdee.eas.basedata.assistant.CostObjectInfo item)
    {
        put("costObject", item);
    }
    /**
     * Object:领料出库单体's 生产订单单号property 
     */
    public String getOrderNumber()
    {
        return getString("orderNumber");
    }
    public void setOrderNumber(String item)
    {
        setString("orderNumber", item);
    }
    /**
     * Object:领料出库单体's 生产订单分录号property 
     */
    public String getPoBillEntryID()
    {
        return getString("poBillEntryID");
    }
    public void setPoBillEntryID(String item)
    {
        setString("poBillEntryID", item);
    }
    /**
     * Object:领料出库单体's 可退货基本数量property 
     */
    public java.math.BigDecimal getUnReturnedBaseQty()
    {
        return getBigDecimal("unReturnedBaseQty");
    }
    public void setUnReturnedBaseQty(java.math.BigDecimal item)
    {
        setBigDecimal("unReturnedBaseQty", item);
    }
    /**
     * Object: 领料出库单体 's 成本项目 property 
     */
    public com.kingdee.eas.basedata.assistant.CostItemInfo getCostItem()
    {
        return (com.kingdee.eas.basedata.assistant.CostItemInfo)get("costItem");
    }
    public void setCostItem(com.kingdee.eas.basedata.assistant.CostItemInfo item)
    {
        put("costItem", item);
    }
    /**
     * Object: 领料出库单体 's 成本对象组 property 
     */
    public com.kingdee.eas.basedata.assistant.CostObjectSuiteInfo getCostObjectSuite()
    {
        return (com.kingdee.eas.basedata.assistant.CostObjectSuiteInfo)get("costObjectSuite");
    }
    public void setCostObjectSuite(com.kingdee.eas.basedata.assistant.CostObjectSuiteInfo item)
    {
        put("costObjectSuite", item);
    }
    /**
     * Object:领料出库单体's 销售订单IDproperty 
     */
    public String getSaleOrderID()
    {
        return getString("saleOrderID");
    }
    public void setSaleOrderID(String item)
    {
        setString("saleOrderID", item);
    }
    /**
     * Object:领料出库单体's 销售订单编号property 
     */
    public String getSaleOrderNum()
    {
        return getString("saleOrderNum");
    }
    public void setSaleOrderNum(String item)
    {
        setString("saleOrderNum", item);
    }
    /**
     * Object:领料出库单体's 应发数量property 
     */
    public java.math.BigDecimal getIssueQty()
    {
        return getBigDecimal("issueQty");
    }
    public void setIssueQty(java.math.BigDecimal item)
    {
        setBigDecimal("issueQty", item);
    }
    /**
     * Object:领料出库单体's 应发基本数量property 
     */
    public java.math.BigDecimal getBaseIssueQty()
    {
        return getBigDecimal("baseIssueQty");
    }
    public void setBaseIssueQty(java.math.BigDecimal item)
    {
        setBigDecimal("baseIssueQty", item);
    }
    /**
     * Object:领料出库单体's 领料日期property 
     */
    public java.sql.Timestamp getPickingDate()
    {
        return getTimestamp("pickingDate");
    }
    public void setPickingDate(java.sql.Timestamp item)
    {
        setTimestamp("pickingDate", item);
    }
    /**
     * Object:领料出库单体's 需用日期property 
     */
    public java.sql.Timestamp getDemandDate()
    {
        return getTimestamp("demandDate");
    }
    public void setDemandDate(java.sql.Timestamp item)
    {
        setTimestamp("demandDate", item);
    }
    /**
     * Object: 领料出库单体 's 生产线 property 
     */
    public com.kingdee.eas.mm.basedata.ProductLineInfo getProductLine()
    {
        return (com.kingdee.eas.mm.basedata.ProductLineInfo)get("productLine");
    }
    public void setProductLine(com.kingdee.eas.mm.basedata.ProductLineInfo item)
    {
        put("productLine", item);
    }
    /**
     * Object: 领料出库单体 's 班组 property 
     */
    public com.kingdee.eas.mm.basedata.ClassGroupInfo getClassGroup()
    {
        return (com.kingdee.eas.mm.basedata.ClassGroupInfo)get("classGroup");
    }
    public void setClassGroup(com.kingdee.eas.mm.basedata.ClassGroupInfo item)
    {
        put("classGroup", item);
    }
    /**
     * Object: 领料出库单体 's 负责人 property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getPerson()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("person");
    }
    public void setPerson(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("person", item);
    }
    /**
     * Object: 领料出库单体 's 领料工序 property 
     */
    public com.kingdee.eas.mm.basedata.ProductLineWPEntryInfo getProductLineWP()
    {
        return (com.kingdee.eas.mm.basedata.ProductLineWPEntryInfo)get("productLineWP");
    }
    public void setProductLineWP(com.kingdee.eas.mm.basedata.ProductLineWPEntryInfo item)
    {
        put("productLineWP", item);
    }
    /**
     * Object:领料出库单体's 生产订单idproperty 
     */
    public String getOrderBillId()
    {
        return getString("orderBillId");
    }
    public void setOrderBillId(String item)
    {
        setString("orderBillId", item);
    }
    /**
     * Object:领料出库单体's 累计关联数量property 
     */
    public java.math.BigDecimal getFaCardQty()
    {
        return getBigDecimal("faCardQty");
    }
    public void setFaCardQty(java.math.BigDecimal item)
    {
        setBigDecimal("faCardQty", item);
    }
    /**
     * Object:领料出库单体's 核心单据编号property 
     */
    public String getCoreBillNumber()
    {
        return getString("coreBillNumber");
    }
    public void setCoreBillNumber(String item)
    {
        setString("coreBillNumber", item);
    }
    /**
     * Object: 领料出库单体 's 核心单据类型 property 
     */
    public com.kingdee.eas.basedata.scm.common.BillTypeInfo getCoreBillType()
    {
        return (com.kingdee.eas.basedata.scm.common.BillTypeInfo)get("coreBillType");
    }
    public void setCoreBillType(com.kingdee.eas.basedata.scm.common.BillTypeInfo item)
    {
        put("coreBillType", item);
    }
    /**
     * Object:领料出库单体's 核心单据IDproperty 
     */
    public String getCoreBillID()
    {
        return getString("coreBillID");
    }
    public void setCoreBillID(String item)
    {
        setString("coreBillID", item);
    }
    /**
     * Object:领料出库单体's 核心单据分录IDproperty 
     */
    public String getCoreBillEntryID()
    {
        return getString("coreBillEntryID");
    }
    public void setCoreBillEntryID(String item)
    {
        setString("coreBillEntryID", item);
    }
    /**
     * Object:领料出库单体's 核心单据分录序列号property 
     */
    public int getCoreBillEntrySe()
    {
        return getInt("coreBillEntrySe");
    }
    public void setCoreBillEntrySe(int item)
    {
        setInt("coreBillEntrySe", item);
    }
    /**
     * Object: 领料出库单体 's 加工物料 property 
     */
    public com.kingdee.eas.basedata.master.material.MaterialInfo getProcessMaterial()
    {
        return (com.kingdee.eas.basedata.master.material.MaterialInfo)get("processMaterial");
    }
    public void setProcessMaterial(com.kingdee.eas.basedata.master.material.MaterialInfo item)
    {
        put("processMaterial", item);
    }
    /**
     * Object: 领料出库单体 's 发料部门 property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getIssueAdminOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("issueAdminOrgUnit");
    }
    public void setIssueAdminOrgUnit(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("issueAdminOrgUnit", item);
    }
    /**
     * Object:领料出库单体's 委外已核销数量property 
     */
    public java.math.BigDecimal getSubWrittenOffQty()
    {
        return getBigDecimal("subWrittenOffQty");
    }
    public void setSubWrittenOffQty(java.math.BigDecimal item)
    {
        setBigDecimal("subWrittenOffQty", item);
    }
    /**
     * Object:领料出库单体's 委外已核销基本数量property 
     */
    public java.math.BigDecimal getSubWrittenOffBaseQty()
    {
        return getBigDecimal("subWrittenOffBaseQty");
    }
    public void setSubWrittenOffBaseQty(java.math.BigDecimal item)
    {
        setBigDecimal("subWrittenOffBaseQty", item);
    }
    /**
     * Object:领料出库单体's 委外未核销数量property 
     */
    public java.math.BigDecimal getSubUnWriteOffQty()
    {
        return getBigDecimal("subUnWriteOffQty");
    }
    public void setSubUnWriteOffQty(java.math.BigDecimal item)
    {
        setBigDecimal("subUnWriteOffQty", item);
    }
    /**
     * Object:领料出库单体's 委外未核销基本数量property 
     */
    public java.math.BigDecimal getSubUnWriteOffBaseQty()
    {
        return getBigDecimal("subUnWriteOffBaseQty");
    }
    public void setSubUnWriteOffBaseQty(java.math.BigDecimal item)
    {
        setBigDecimal("subUnWriteOffBaseQty", item);
    }
    /**
     * Object: 领料出库单体 's 发料人员 property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getIssuePerson()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("issuePerson");
    }
    public void setIssuePerson(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("issuePerson", item);
    }
    /**
     * Object:领料出库单体's 工序号property 
     */
    public int getOperationNo()
    {
        return getInt("operationNo");
    }
    public void setOperationNo(int item)
    {
        setInt("operationNo", item);
    }
    /**
     * Object: 领料出库单体 's 工序 property 
     */
    public com.kingdee.eas.mm.basedata.OperationInfo getOperation()
    {
        return (com.kingdee.eas.mm.basedata.OperationInfo)get("operation");
    }
    public void setOperation(com.kingdee.eas.mm.basedata.OperationInfo item)
    {
        put("operation", item);
    }
    /**
     * Object: 领料出库单体 's 工作中心 property 
     */
    public com.kingdee.eas.mm.basedata.WorkCenterInfo getWorkCenter()
    {
        return (com.kingdee.eas.mm.basedata.WorkCenterInfo)get("workCenter");
    }
    public void setWorkCenter(com.kingdee.eas.mm.basedata.WorkCenterInfo item)
    {
        put("workCenter", item);
    }
    /**
     * Object:领料出库单体's 分配标志property 
     */
    public boolean isIsAdmeasure()
    {
        return getBoolean("isAdmeasure");
    }
    public void setIsAdmeasure(boolean item)
    {
        setBoolean("isAdmeasure", item);
    }
    /**
     * Object:领料出库单体's 返工property 
     */
    public boolean isIsReWork()
    {
        return getBoolean("isReWork");
    }
    public void setIsReWork(boolean item)
    {
        setBoolean("isReWork", item);
    }
    /**
     * Object:领料出库单体's property 
     */
    public String getCoreOrderID()
    {
        return getString("coreOrderID");
    }
    public void setCoreOrderID(String item)
    {
        setString("coreOrderID", item);
    }
    /**
     * Object:领料出库单体's property 
     */
    public String getCoreOrderEntryID()
    {
        return getString("coreOrderEntryID");
    }
    public void setCoreOrderEntryID(String item)
    {
        setString("coreOrderEntryID", item);
    }
    /**
     * Object:领料出库单体's property 
     */
    public String getCoreNumber()
    {
        return getString("coreNumber");
    }
    public void setCoreNumber(String item)
    {
        setString("coreNumber", item);
    }
    /**
     * Object:领料出库单体's property 
     */
    public int getCoreOrderEntrySeq()
    {
        return getInt("coreOrderEntrySeq");
    }
    public void setCoreOrderEntrySeq(int item)
    {
        setInt("coreOrderEntrySeq", item);
    }
    /**
     * Object:领料出库单体's property 
     */
    public String getTraceNo()
    {
        return getString("traceNo");
    }
    public void setTraceNo(String item)
    {
        setString("traceNo", item);
    }
    /**
     * Object:领料出库单体's 已核销金额property 
     */
    public java.math.BigDecimal getScWrittenOffAmount()
    {
        return getBigDecimal("scWrittenOffAmount");
    }
    public void setScWrittenOffAmount(java.math.BigDecimal item)
    {
        setBigDecimal("scWrittenOffAmount", item);
    }
    /**
     * Object:领料出库单体's 未核销金额property 
     */
    public java.math.BigDecimal getScUnWrittenOffAmount()
    {
        return getBigDecimal("scUnWrittenOffAmount");
    }
    public void setScUnWrittenOffAmount(java.math.BigDecimal item)
    {
        setBigDecimal("scUnWrittenOffAmount", item);
    }
    /**
     * Object: 领料出库单体 's 客户 property 
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
     * Object: 领料出库单体 's 供应商 property 
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
     * Object:领料出库单体's 单价property 
     */
    public java.math.BigDecimal getPrice()
    {
        return getBigDecimal("price");
    }
    public void setPrice(java.math.BigDecimal item)
    {
        setBigDecimal("price", item);
    }
    /**
     * Object:领料出库单体's 金额property 
     */
    public java.math.BigDecimal getAmount()
    {
        return getBigDecimal("amount");
    }
    public void setAmount(java.math.BigDecimal item)
    {
        setBigDecimal("amount", item);
    }
    /**
     * Object: 领料出库单体 's 供方仓库 property 
     */
    public com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo getSupplyWarehouse()
    {
        return (com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo)get("supplyWarehouse");
    }
    public void setSupplyWarehouse(com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo item)
    {
        put("supplyWarehouse", item);
    }
    /**
     * Object: 领料出库单体 's 供方库位 property 
     */
    public com.kingdee.eas.basedata.scm.im.inv.LocationInfo getSupplyLocation()
    {
        return (com.kingdee.eas.basedata.scm.im.inv.LocationInfo)get("supplyLocation");
    }
    public void setSupplyLocation(com.kingdee.eas.basedata.scm.im.inv.LocationInfo item)
    {
        put("supplyLocation", item);
    }
    /**
     * Object:领料出库单体's 结算价property 
     */
    public java.math.BigDecimal getSettlePrice()
    {
        return getBigDecimal("settlePrice");
    }
    public void setSettlePrice(java.math.BigDecimal item)
    {
        setBigDecimal("settlePrice", item);
    }
    /**
     * Object: 领料出库单体 's 领料人 property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getPicker()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("picker");
    }
    public void setPicker(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("picker", item);
    }
    /**
     * Object:领料出库单体's 业务日期property 
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
     * Object: 领料出库单体 's 主制部门 property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getAdminOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("adminOrgUnit");
    }
    public void setAdminOrgUnit(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("adminOrgUnit", item);
    }
    /**
     * Object: 领料出库单体 's 成本中心 property 
     */
    public com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo getCostCenterOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo)get("costCenterOrgUnit");
    }
    public void setCostCenterOrgUnit(com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo item)
    {
        put("costCenterOrgUnit", item);
    }
    /**
     * Object: 领料出库单体 's 备份对象 property 
     */
    public com.kingdee.eas.basedata.assistant.CostObjectInfo getCostObject2()
    {
        return (com.kingdee.eas.basedata.assistant.CostObjectInfo)get("costObject2");
    }
    public void setCostObject2(com.kingdee.eas.basedata.assistant.CostObjectInfo item)
    {
        put("costObject2", item);
    }
    /**
     * Object:领料出库单体's 核销成本调整property 
     */
    public boolean isIsAdjust()
    {
        return getBoolean("isAdjust");
    }
    public void setIsAdjust(boolean item)
    {
        setBoolean("isAdjust", item);
    }
    /**
     * Object:领料出库单体's 调整单编码property 
     */
    public String getAdjustNum()
    {
        return getString("adjustNum");
    }
    public void setAdjustNum(String item)
    {
        setString("adjustNum", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("11774BB4");
    }
}