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
     * Object:����'s ������Դproperty 
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
     * Object: ���� 's Ʒ�� property 
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
     * Object:����'s ���̺�property 
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
     * Object:����'s ��������property 
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
     * Object:����'s ���ƺ�property 
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
     * Object:����'s ������ɫproperty 
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
     * Object: ���� 's ���� property 
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
     * Object: ���� 's ��ϵ property 
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
     * Object:����'s ����Ʒ�Ƴ���property 
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
     * Object:����'s ������������property 
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
     * Object:����'s ����˵��property 
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
     * Object:����'s ��;״̬property 
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
     * Object:����'s PDI״̬property 
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
     * Object:����'s Ԥ��״̬property 
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
     * Object:����'s �䳵״̬property 
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
     * Object:����'s �������״̬property 
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
     * Object: ���� 's ������˾ property 
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
     * Object: ���� 's ��ǰ��˾ property 
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
     * Object: ���� 's ��ǰ�ֿ� property 
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
     * Object: ���� 's ��ǰ��λ property 
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
     * Object:����'s ��������property 
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
     * Object:����'s ���޿���property 
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
     * Object:����'s �ϸ�֤��property 
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
     * Object:����'s ���ڵ�֤��property 
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
     * Object:����'s �̼쵥��property 
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
     * Object:����'s �泵����property 
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
     * Object:����'s Կ�׺�property 
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
     * Object:����'s Կ��λ��property 
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
     * Object: ���� 's ���� property 
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
     * Object:����'s ��������property 
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
     * Object:����'s �绰property 
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
     * Object:����'s �������˺�property 
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
     * Object:����'s ��������property 
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
     * Object:����'s ����˵��property 
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
     * Object: ���� 's ����ά������ property 
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
     * Object: ���� 's ����������� property 
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
     * Object:����'s ת���������property 
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
     * Object:����'s ���޿�ʼ����property 
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
     * Object:����'s ���޽�������property 
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
     * Object:����'s ��ʻ֤������property 
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
     * Object:����'s ������������property 
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
     * Object:����'s �̱���쵽����property 
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
     * Object:����'s ��ַproperty 
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
     * Object:����'s ���������property 
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
     * Object:����'s ����״̬property 
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
     * Object: ���� 's ��ɫ property 
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
     * Object: ���� 's ѡװ property 
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
     * Object: ���� 's ���� property 
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
     * Object: ���� 's ��Ҫ�ó��� property 
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
     * Object: ���� 's ��ʻ֤��������� property 
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
     * Object:����'s ˰��ǼǺ�/���֤��property 
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
     * Object:����'s �ɹ��������property 
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
     * Object:����'s ��ʼ����泵��property 
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
     * Object:����'s ���Ͱ汾property 
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
     * Object:����'s ��Ѻ�ϸ�֤property 
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
     * Object:����'s �ϸ�֤���ܵ�λproperty 
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
     * Object:����'s ��װ��־property 
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
     * Object: ���� 's ��������ά������ property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleBelongCollection getBelong()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.VehicleBelongCollection)get("Belong");
    }
    /**
     * Object: ���� 's ���չ�˾ property 
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
     * Object:����'s ���յ�������property 
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
     * Object:����'s ������property 
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
     * Object: ���� 's ���۹�˾ property 
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
     * Object:����'s ��������property 
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
     * Object:����'s ��������property 
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
     * Object:����'s ��ʻ֤ע������property 
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
     * Object: ���� 's ����Ȩ��˾ property 
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
     * Object:����'s ί��״̬property 
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
     * Object:����'s �ʱ�property 
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
     * Object:����'s ��ǿ�յ�������property 
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
     * Object:����'s ��ǿ�ձ�����property 
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
     * Object:����'s ·�ŷѵ�����property 
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
     * Object: ���� 's ���ڳ��� property 
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
     * Object: ���� 's �����ͻ� property 
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
     * Object:����'s �����ᳵ״̬property 
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
     * Object: ���� 's �������� property 
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
     * Object:����'s ��������۸�property 
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
     * Object:����'s �ϸ�֤��ŵص�property 
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
     * Object:����'s ��������property 
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
     * Object: ���� 's ά�ޱ�ע property 
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