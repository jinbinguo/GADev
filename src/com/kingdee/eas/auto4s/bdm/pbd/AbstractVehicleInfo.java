package com.kingdee.eas.auto4s.bdm.pbd;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractVehicleInfo extends com.kingdee.eas.framework.DataBaseInfo implements Serializable 
{
    public AbstractVehicleInfo()
    {
        this("id");
    }
    protected AbstractVehicleInfo(String pkField)
    {
        super(pkField);
        put("Belong", new com.kingdee.eas.auto4s.bdm.pbd.VehicleBelongCollection());
        put("RepairRemark", new com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairRemarkCollection());
    }
    /**
     * Object:车辆's 车辆来源property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleSourceEnum getSource()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.VehicleSourceEnum.getEnum(getString("source"));
    }
    public void setSource(com.kingdee.eas.auto4s.bdm.pbd.VehicleSourceEnum item)
    {
		if (item != null) {
        setString("source", item.getValue());
		}
    }
    /**
     * Object: 车辆 's 品牌 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.BrandInfo getBrand()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.BrandInfo)get("brand");
    }
    public void setBrand(com.kingdee.eas.auto4s.bdm.pbd.BrandInfo item)
    {
        put("brand", item);
    }
    /**
     * Object:车辆's 底盘号property 
     */
    public String getVIN()
    {
        return getString("vIN");
    }
    public void setVIN(String item)
    {
        setString("vIN", item);
    }
    /**
     * Object:车辆's 发动机号property 
     */
    public String getEngineNum()
    {
        return getString("engineNum");
    }
    public void setEngineNum(String item)
    {
        setString("engineNum", item);
    }
    /**
     * Object:车辆's 车牌号property 
     */
    public String getPlateNum()
    {
        return getString("plateNum");
    }
    public void setPlateNum(String item)
    {
        setString("plateNum", item);
    }
    /**
     * Object:车辆's 车牌颜色property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.PlateColourEnum getPlateColor()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.PlateColourEnum.getEnum(getString("plateColor"));
    }
    public void setPlateColor(com.kingdee.eas.auto4s.bdm.pbd.PlateColourEnum item)
    {
		if (item != null) {
        setString("plateColor", item.getValue());
		}
    }
    /**
     * Object: 车辆 's 车型 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.ModelInfo getModel()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.ModelInfo)get("model");
    }
    public void setModel(com.kingdee.eas.auto4s.bdm.pbd.ModelInfo item)
    {
        put("model", item);
    }
    /**
     * Object: 车辆 's 车系 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.SeriesInfo getSeries()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.SeriesInfo)get("series");
    }
    public void setSeries(com.kingdee.eas.auto4s.bdm.pbd.SeriesInfo item)
    {
        put("series", item);
    }
    /**
     * Object:车辆's 其他品牌车辆property 
     */
    public boolean isOtherBrandVehicle()
    {
        return getBoolean("otherBrandVehicle");
    }
    public void setOtherBrandVehicle(boolean item)
    {
        setBoolean("otherBrandVehicle", item);
    }
    /**
     * Object:车辆's 车辆生成类型property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleCreateTypeEnum getVehicleCreateType()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.VehicleCreateTypeEnum.getEnum(getString("vehicleCreateType"));
    }
    public void setVehicleCreateType(com.kingdee.eas.auto4s.bdm.pbd.VehicleCreateTypeEnum item)
    {
		if (item != null) {
        setString("vehicleCreateType", item.getValue());
		}
    }
    /**
     * Object:车辆's 车辆说明property 
     */
    public String getVehicleRemark()
    {
        return getString("vehicleRemark");
    }
    public void setVehicleRemark(String item)
    {
        setString("vehicleRemark", item);
    }
    /**
     * Object:车辆's 在途状态property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.OnRoadStatusEnum getOnRoadStatus()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.OnRoadStatusEnum.getEnum(getString("onRoadStatus"));
    }
    public void setOnRoadStatus(com.kingdee.eas.auto4s.bdm.pbd.OnRoadStatusEnum item)
    {
		if (item != null) {
        setString("onRoadStatus", item.getValue());
		}
    }
    /**
     * Object:车辆's PDI状态property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.PDIStatusEnum getPDIStatus()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.PDIStatusEnum.getEnum(getString("pDIStatus"));
    }
    public void setPDIStatus(com.kingdee.eas.auto4s.bdm.pbd.PDIStatusEnum item)
    {
		if (item != null) {
        setString("pDIStatus", item.getValue());
		}
    }
    /**
     * Object:车辆's 预留状态property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.ReservedStatusEnum getReservedStatus()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.ReservedStatusEnum.getEnum(getString("reservedStatus"));
    }
    public void setReservedStatus(com.kingdee.eas.auto4s.bdm.pbd.ReservedStatusEnum item)
    {
		if (item != null) {
        setString("reservedStatus", item.getValue());
		}
    }
    /**
     * Object:车辆's 配车状态property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.AssignStatusEnum getAssignStatus()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.AssignStatusEnum.getEnum(getString("assignStatus"));
    }
    public void setAssignStatus(com.kingdee.eas.auto4s.bdm.pbd.AssignStatusEnum item)
    {
		if (item != null) {
        setString("assignStatus", item.getValue());
		}
    }
    /**
     * Object:车辆's 虚拟出库状态property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VirtualIssueStatusEnum getVirtualIssueStatus()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.VirtualIssueStatusEnum.getEnum(getString("virtualIssueStatus"));
    }
    public void setVirtualIssueStatus(com.kingdee.eas.auto4s.bdm.pbd.VirtualIssueStatusEnum item)
    {
		if (item != null) {
        setString("virtualIssueStatus", item.getValue());
		}
    }
    /**
     * Object: 车辆 's 购车公司 property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getBuyAutoOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("buyAutoOrgUnit");
    }
    public void setBuyAutoOrgUnit(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("buyAutoOrgUnit", item);
    }
    /**
     * Object: 车辆 's 当前公司 property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("orgUnit");
    }
    public void setOrgUnit(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("orgUnit", item);
    }
    /**
     * Object: 车辆 's 当前仓库 property 
     */
    public com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo getWarehouse()
    {
        return (com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo)get("warehouse");
    }
    public void setWarehouse(com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo item)
    {
        put("warehouse", item);
    }
    /**
     * Object: 车辆 's 当前库位 property 
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
     * Object:车辆's 生产日期property 
     */
    public java.util.Date getProductDate()
    {
        return getDate("productDate");
    }
    public void setProductDate(java.util.Date item)
    {
        setDate("productDate", item);
    }
    /**
     * Object:车辆's 保修卡号property 
     */
    public String getGuaranteeNum()
    {
        return getString("guaranteeNum");
    }
    public void setGuaranteeNum(String item)
    {
        setString("guaranteeNum", item);
    }
    /**
     * Object:车辆's 合格证号property 
     */
    public String getCertificationNum()
    {
        return getString("certificationNum");
    }
    public void setCertificationNum(String item)
    {
        setString("certificationNum", item);
    }
    /**
     * Object:车辆's 进口单证号property 
     */
    public String getImportDocNum()
    {
        return getString("importDocNum");
    }
    public void setImportDocNum(String item)
    {
        setString("importDocNum", item);
    }
    /**
     * Object:车辆's 商检单号property 
     */
    public String getInspectionNum()
    {
        return getString("inspectionNum");
    }
    public void setInspectionNum(String item)
    {
        setString("inspectionNum", item);
    }
    /**
     * Object:车辆's 随车资料property 
     */
    public String getVehicleInfo()
    {
        return getString("vehicleInfo");
    }
    public void setVehicleInfo(String item)
    {
        setString("vehicleInfo", item);
    }
    /**
     * Object:车辆's 钥匙号property 
     */
    public String getKeyNum()
    {
        return getString("keyNum");
    }
    public void setKeyNum(String item)
    {
        setString("keyNum", item);
    }
    /**
     * Object:车辆's 钥匙位置property 
     */
    public String getKeyPlace()
    {
        return getString("keyPlace");
    }
    public void setKeyPlace(String item)
    {
        setString("keyPlace", item);
    }
    /**
     * Object: 车辆 's 车主 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo getCustomer()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo)get("customer");
    }
    public void setCustomer(com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo item)
    {
        put("customer", item);
    }
    /**
     * Object:车辆's 车主名称property 
     */
    public String getOwnerName()
    {
        return getString("ownerName");
    }
    public void setOwnerName(String item)
    {
        setString("ownerName", item);
    }
    /**
     * Object:车辆's 电话property 
     */
    public String getPhone()
    {
        return getString("phone");
    }
    public void setPhone(String item)
    {
        setString("phone", item);
    }
    /**
     * Object:车辆's 开户行账号property 
     */
    public String getBankAccount()
    {
        return getString("bankAccount");
    }
    public void setBankAccount(String item)
    {
        setString("bankAccount", item);
    }
    /**
     * Object:车辆's 过户日期property 
     */
    public java.util.Date getTransferDate()
    {
        return getDate("transferDate");
    }
    public void setTransferDate(java.util.Date item)
    {
        setDate("transferDate", item);
    }
    /**
     * Object:车辆's 过户说明property 
     */
    public String getTransferRemark()
    {
        return getString("transferRemark");
    }
    public void setTransferRemark(String item)
    {
        setString("transferRemark", item);
    }
    /**
     * Object: 车辆 's 所属维修中心 property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getRepairOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("repairOrgUnit");
    }
    public void setRepairOrgUnit(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("repairOrgUnit", item);
    }
    /**
     * Object: 车辆 's 所属服务顾问 property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getServicePerson()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("servicePerson");
    }
    public void setServicePerson(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("servicePerson", item);
    }
    /**
     * Object:车辆's 转入服务日期property 
     */
    public java.util.Date getToServiceDate()
    {
        return getDate("toServiceDate");
    }
    public void setToServiceDate(java.util.Date item)
    {
        setDate("toServiceDate", item);
    }
    /**
     * Object:车辆's 保修开始日期property 
     */
    public java.util.Date getWarrantyStartDate()
    {
        return getDate("warrantyStartDate");
    }
    public void setWarrantyStartDate(java.util.Date item)
    {
        setDate("warrantyStartDate", item);
    }
    /**
     * Object:车辆's 保修结束日期property 
     */
    public java.util.Date getWarrantyEndDate()
    {
        return getDate("warrantyEndDate");
    }
    public void setWarrantyEndDate(java.util.Date item)
    {
        setDate("warrantyEndDate", item);
    }
    /**
     * Object:车辆's 行驶证到期日property 
     */
    public java.util.Date getVRCExpireDate()
    {
        return getDate("vRCExpireDate");
    }
    public void setVRCExpireDate(java.util.Date item)
    {
        setDate("vRCExpireDate", item);
    }
    /**
     * Object:车辆's 车辆年审到期日property 
     */
    public java.util.Date getYearExamineExpireDate()
    {
        return getDate("yearExamineExpireDate");
    }
    public void setYearExamineExpireDate(java.util.Date item)
    {
        setDate("yearExamineExpireDate", item);
    }
    /**
     * Object:车辆's 绿标年检到期日property 
     */
    public java.util.Date getGreenLabelExpireDate()
    {
        return getDate("greenLabelExpireDate");
    }
    public void setGreenLabelExpireDate(java.util.Date item)
    {
        setDate("greenLabelExpireDate", item);
    }
    /**
     * Object:车辆's 地址property 
     */
    public String getAddress()
    {
        return getString("address");
    }
    public void setAddress(String item)
    {
        setString("address", item);
    }
    /**
     * Object:车辆's 保修里程数property 
     */
    public java.math.BigDecimal getWarrantyMile()
    {
        return getBigDecimal("warrantyMile");
    }
    public void setWarrantyMile(java.math.BigDecimal item)
    {
        setBigDecimal("warrantyMile", item);
    }
    /**
     * Object:车辆's 车辆状态property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleStatusEnum getVehicleStatus()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.VehicleStatusEnum.getEnum(getString("vehicleStatus"));
    }
    public void setVehicleStatus(com.kingdee.eas.auto4s.bdm.pbd.VehicleStatusEnum item)
    {
		if (item != null) {
        setString("vehicleStatus", item.getValue());
		}
    }
    /**
     * Object: 车辆 's 颜色 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.SeriesColorInfo getColor()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.SeriesColorInfo)get("color");
    }
    public void setColor(com.kingdee.eas.auto4s.bdm.pbd.SeriesColorInfo item)
    {
        put("color", item);
    }
    /**
     * Object: 车辆 's 选装 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.OptionItemCombineInfo getOptionItemCombine()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.OptionItemCombineInfo)get("optionItemCombine");
    }
    public void setOptionItemCombine(com.kingdee.eas.auto4s.bdm.pbd.OptionItemCombineInfo item)
    {
        put("optionItemCombine", item);
    }
    /**
     * Object: 车辆 's 内饰 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.SeriesInnerInfo getInner()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.SeriesInnerInfo)get("inner");
    }
    public void setInner(com.kingdee.eas.auto4s.bdm.pbd.SeriesInnerInfo item)
    {
        put("inner", item);
    }
    /**
     * Object: 车辆 's 主要用车人 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.ContactPersonInfo getMainUser()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.ContactPersonInfo)get("mainUser");
    }
    public void setMainUser(com.kingdee.eas.auto4s.bdm.pbd.ContactPersonInfo item)
    {
        put("mainUser", item);
    }
    /**
     * Object: 车辆 's 行驶证年检所属地 property 
     */
    public com.kingdee.eas.basedata.assistant.CityInfo getVRCBelongPlace()
    {
        return (com.kingdee.eas.basedata.assistant.CityInfo)get("vRCBelongPlace");
    }
    public void setVRCBelongPlace(com.kingdee.eas.basedata.assistant.CityInfo item)
    {
        put("vRCBelongPlace", item);
    }
    /**
     * Object:车辆's 税务登记号/身份证号property 
     */
    public String getIDNum()
    {
        return getString("iDNum");
    }
    public void setIDNum(String item)
    {
        setString("iDNum", item);
    }
    /**
     * Object:车辆's 采购入库日期property 
     */
    public java.util.Date getPurReceiveDate()
    {
        return getDate("purReceiveDate");
    }
    public void setPurReceiveDate(java.util.Date item)
    {
        setDate("purReceiveDate", item);
    }
    /**
     * Object:车辆's 初始化库存车辆property 
     */
    public boolean isInitVehicle()
    {
        return getBoolean("initVehicle");
    }
    public void setInitVehicle(boolean item)
    {
        setBoolean("initVehicle", item);
    }
    /**
     * Object:车辆's 车型版本property 
     */
    public int getModelVersion()
    {
        return getInt("modelVersion");
    }
    public void setModelVersion(int item)
    {
        setInt("modelVersion", item);
    }
    /**
     * Object:车辆's 抵押合格证property 
     */
    public boolean isIsMortgage()
    {
        return getBoolean("isMortgage");
    }
    public void setIsMortgage(boolean item)
    {
        setBoolean("isMortgage", item);
    }
    /**
     * Object:车辆's 合格证保管单位property 
     */
    public String getKeepingOrgUnit()
    {
        return getString("keepingOrgUnit");
    }
    public void setKeepingOrgUnit(String item)
    {
        setString("keepingOrgUnit", item);
    }
    /**
     * Object:车辆's 加装标志property 
     */
    public boolean isIsDecoration()
    {
        return getBoolean("isDecoration");
    }
    public void setIsDecoration(boolean item)
    {
        setBoolean("isDecoration", item);
    }
    /**
     * Object: 车辆 's 车辆所属维修中心 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleBelongCollection getBelong()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.VehicleBelongCollection)get("Belong");
    }
    /**
     * Object: 车辆 's 保险公司 property 
     */
    public com.kingdee.eas.auto4s.bdm.vam.InsuranceCompanyInfo getInsuCompany()
    {
        return (com.kingdee.eas.auto4s.bdm.vam.InsuranceCompanyInfo)get("insuCompany");
    }
    public void setInsuCompany(com.kingdee.eas.auto4s.bdm.vam.InsuranceCompanyInfo item)
    {
        put("insuCompany", item);
    }
    /**
     * Object:车辆's 保险到期日期property 
     */
    public java.util.Date getInsuInvalidDate()
    {
        return getDate("insuInvalidDate");
    }
    public void setInsuInvalidDate(java.util.Date item)
    {
        setDate("insuInvalidDate", item);
    }
    /**
     * Object:车辆's 保单号property 
     */
    public String getInsuranceNumber()
    {
        return getString("insuranceNumber");
    }
    public void setInsuranceNumber(String item)
    {
        setString("insuranceNumber", item);
    }
    /**
     * Object: 车辆 's 销售公司 property 
     */
    public com.kingdee.eas.basedata.org.SaleOrgUnitInfo getSaleOrg()
    {
        return (com.kingdee.eas.basedata.org.SaleOrgUnitInfo)get("saleOrg");
    }
    public void setSaleOrg(com.kingdee.eas.basedata.org.SaleOrgUnitInfo item)
    {
        put("saleOrg", item);
    }
    /**
     * Object:车辆's 销售日期property 
     */
    public java.util.Date getSaleDate()
    {
        return getDate("saleDate");
    }
    public void setSaleDate(java.util.Date item)
    {
        setDate("saleDate", item);
    }
    /**
     * Object:车辆's 上牌日期property 
     */
    public java.util.Date getPlateDate()
    {
        return getDate("plateDate");
    }
    public void setPlateDate(java.util.Date item)
    {
        setDate("plateDate", item);
    }
    /**
     * Object:车辆's 行驶证注册日期property 
     */
    public java.util.Date getVRCRegDate()
    {
        return getDate("vRCRegDate");
    }
    public void setVRCRegDate(java.util.Date item)
    {
        setDate("vRCRegDate", item);
    }
    /**
     * Object: 车辆 's 所有权公司 property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getBelongOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("belongOrgUnit");
    }
    public void setBelongOrgUnit(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("belongOrgUnit", item);
    }
    /**
     * Object:车辆's 委托状态property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.TrustEnum getTrustStatus()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.TrustEnum.getEnum(getString("trustStatus"));
    }
    public void setTrustStatus(com.kingdee.eas.auto4s.bdm.pbd.TrustEnum item)
    {
		if (item != null) {
        setString("trustStatus", item.getValue());
		}
    }
    /**
     * Object:车辆's 邮编property 
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
     * Object:车辆's 交强险到期日期property 
     */
    public java.util.Date getTraffInvalidDate()
    {
        return getDate("traffInvalidDate");
    }
    public void setTraffInvalidDate(java.util.Date item)
    {
        setDate("traffInvalidDate", item);
    }
    /**
     * Object:车辆's 交强险保单号property 
     */
    public String getTraffInsuNumber()
    {
        return getString("traffInsuNumber");
    }
    public void setTraffInsuNumber(String item)
    {
        setString("traffInsuNumber", item);
    }
    /**
     * Object:车辆's 路桥费到期日property 
     */
    public java.util.Date getBridgeFeeDate()
    {
        return getDate("bridgeFeeDate");
    }
    public void setBridgeFeeDate(java.util.Date item)
    {
        setDate("bridgeFeeDate", item);
    }
    /**
     * Object: 车辆 's 所在城市 property 
     */
    public com.kingdee.eas.basedata.assistant.CityInfo getCity()
    {
        return (com.kingdee.eas.basedata.assistant.CityInfo)get("city");
    }
    public void setCity(com.kingdee.eas.basedata.assistant.CityInfo item)
    {
        put("city", item);
    }
    /**
     * Object: 车辆 's 订单客户 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo getOrderCustomer()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo)get("orderCustomer");
    }
    public void setOrderCustomer(com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo item)
    {
        put("orderCustomer", item);
    }
    /**
     * Object:车辆's 二网提车状态property 
     */
    public com.kingdee.eas.auto4s.bdm.vm.SubstoreVehicleStatusEnum getSubstoreStatus()
    {
        return com.kingdee.eas.auto4s.bdm.vm.SubstoreVehicleStatusEnum.getEnum(getString("substoreStatus"));
    }
    public void setSubstoreStatus(com.kingdee.eas.auto4s.bdm.vm.SubstoreVehicleStatusEnum item)
    {
		if (item != null) {
        setString("substoreStatus", item.getValue());
		}
    }
    /**
     * Object: 车辆 's 二级网点 property 
     */
    public com.kingdee.eas.auto4s.bdm.vm.SubAutoStoreInfo getSubAutoStore()
    {
        return (com.kingdee.eas.auto4s.bdm.vm.SubAutoStoreInfo)get("subAutoStore");
    }
    public void setSubAutoStore(com.kingdee.eas.auto4s.bdm.vm.SubAutoStoreInfo item)
    {
        put("subAutoStore", item);
    }
    /**
     * Object:车辆's 已设特殊价格property 
     */
    public boolean isIsSpecialPrice()
    {
        return getBoolean("isSpecialPrice");
    }
    public void setIsSpecialPrice(boolean item)
    {
        setBoolean("isSpecialPrice", item);
    }
    /**
     * Object:车辆's 合格证存放地点property 
     */
    public String getKeepingLocation()
    {
        return getString("keepingLocation");
    }
    public void setKeepingLocation(String item)
    {
        setString("keepingLocation", item);
    }
    /**
     * Object:车辆's 下线日期property 
     */
    public java.util.Date getOfflineDate()
    {
        return getDate("offlineDate");
    }
    public void setOfflineDate(java.util.Date item)
    {
        setDate("offlineDate", item);
    }
    /**
     * Object: 车辆 's 维修备注 property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairRemarkCollection getRepairRemark()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairRemarkCollection)get("RepairRemark");
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("9E9FFF45");
    }
}