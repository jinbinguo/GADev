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
     * Object: ��¼ 's ����ͷ property 
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
     * Object:��¼'s ����property 
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
     * Object:��¼'s ѡ��property 
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
     * Object:��¼'s P/O Rqnproperty 
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
     * Object:��¼'s �˵�property 
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
     * Object:��¼'s WIP��property 
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
     * Object:��¼'s �к�property 
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
     * Object:��¼'s �ͻ�property 
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
     * Object:��¼'s ��Ӧ��property 
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
     * Object:��¼'s ������property 
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
     * Object:��¼'s ����property 
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
     * Object:��¼'s ��Ӧ����property 
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
     * Object:��¼'s ʣ����property 
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
     * Object:��¼'s ���property 
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
     * Object:��¼'s �ɱ�property 
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
     * Object:��¼'s Lproperty 
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
     * Object:��¼'s Tproperty 
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
     * Object: ��¼ 's EAS��Ӧ�� property 
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
     * Object: ��¼ 's EAS�ͻ� property 
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
     * Object: ��¼ 's EAS�ֿ� property 
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
     * Object: ��¼ 's EASά�޹��� property 
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
     * Object:��¼'s EAS��˰����property 
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
     * Object: ��¼ 's EAS���� property 
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
     * Object: ��¼ 's EAS����������λ property 
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
     * Object:��¼'s EASά�޹��������¼IDproperty 
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
     * Object: ��¼ 's EAS���� property 
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
     * Object:��¼'s EAS��������к�property 
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
     * Object:��¼'s EASά�޹�����property 
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
     * Object:��¼'s ��ת��property 
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