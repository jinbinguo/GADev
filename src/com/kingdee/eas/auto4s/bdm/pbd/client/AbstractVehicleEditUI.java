/**
 * output package name
 */
package com.kingdee.eas.auto4s.bdm.pbd.client;

import org.apache.log4j.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.KeyStroke;

import com.kingdee.bos.ctrl.swing.*;
import com.kingdee.bos.ctrl.kdf.table.*;
import com.kingdee.bos.ctrl.kdf.data.event.*;
import com.kingdee.bos.dao.*;
import com.kingdee.bos.dao.query.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.ui.face.*;
import com.kingdee.bos.ui.util.ResourceBundleHelper;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.enums.EnumUtils;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.bos.ctrl.swing.event.*;
import com.kingdee.bos.ctrl.kdf.table.event.*;
import com.kingdee.bos.ctrl.extendcontrols.*;
import com.kingdee.bos.ctrl.kdf.util.render.*;
import com.kingdee.bos.ui.face.IItemAction;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.bos.ui.util.IUIActionPostman;
import com.kingdee.bos.appframework.client.servicebinding.ActionProxyFactory;
import com.kingdee.bos.appframework.uistatemanage.ActionStateConst;
import com.kingdee.bos.appframework.validator.ValidateHelper;
import com.kingdee.bos.appframework.uip.UINavigator;


/**
 * output class name
 */
public abstract class AbstractVehicleEditUI extends com.kingdee.eas.framework.client.EditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractVehicleEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contnumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsource;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbrand;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvIN;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contengineNum;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contplateNum;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contplateColor;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contmodel;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kDTabbedPane1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contlastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contlastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contconfig;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contseries;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkotherBrandVehicle;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvehicleCreateType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvehicleRemark;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkinitVehicle;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkisSpecialPrice;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDComboBox source;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtbrand;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtvIN;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtengineNum;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtplateNum;
    protected com.kingdee.bos.ctrl.swing.KDComboBox plateColor;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtmodel;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel1;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel2;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel3;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel4;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer3;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer continner;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer4;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer5;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer7;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer8;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer9;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer10;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer11;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer12;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer6;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contoptionitemcombine;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcolor;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contmodelVersion;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkisDecoration;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox1;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox2;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox3;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtinner;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox4;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox5;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox7;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox8;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox9;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox10;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox11;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox12;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox kDBizPromptBox6;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtoptionitemcombine;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtcolor;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtmodelVersion;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contonRoadStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contpDIStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contreservedStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contassignStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvirtualIssueStatus;
    protected com.kingdee.bos.ctrl.swing.KDSeparator kDSeparator5;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbuyAutoOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contorgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contwarehouse;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contlocation;
    protected com.kingdee.bos.ctrl.swing.KDSeparator kDSeparator6;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contproductDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contguaranteeNum;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcertificationNum;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contimportDocNum;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer continspectionNum;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvehicleInfo;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contkeyNum;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contkeyPlace;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvehicleStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contpurReceiveDate;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkisMortgage;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contkeepingOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsaleOrg;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsaleDate;
    protected com.kingdee.bos.ctrl.swing.KDSeparator kDSeparator8;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbelongOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttrustStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsubstoreStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsubAutoStore;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contkeepingLocation;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contofflineDate;
    protected com.kingdee.bos.ctrl.swing.KDComboBox onRoadStatus;
    protected com.kingdee.bos.ctrl.swing.KDComboBox pDIStatus;
    protected com.kingdee.bos.ctrl.swing.KDComboBox reservedStatus;
    protected com.kingdee.bos.ctrl.swing.KDComboBox assignStatus;
    protected com.kingdee.bos.ctrl.swing.KDComboBox virtualIssueStatus;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtbuyAutoOrgUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtorgUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtwarehouse;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtlocation;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkproductDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtguaranteeNum;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtcertificationNum;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtimportDocNum;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtinspectionNum;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtvehicleInfo;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtkeyNum;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtkeyPlace;
    protected com.kingdee.bos.ctrl.swing.KDComboBox vehicleStatus;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkpurReceiveDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtkeepingOrgUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtsaleOrg;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pksaleDate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtbelongOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDComboBox trustStatus;
    protected com.kingdee.bos.ctrl.swing.KDComboBox substoreStatus;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtsubAutoStore;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtkeepingLocation;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkofflineDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcustomer;
    protected com.kingdee.bos.ctrl.swing.KDContainer kDContainer1;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDViewCustomerButton;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDOwnerChangeButton;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contmainUser;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDAddNewCustomerButton;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contorderCustomer;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtcustomer;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel5;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contownerName;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contphone;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbankAccount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttransferDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttransferRemark;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contaddress;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contiDNum;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contzipCode;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtownerName;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtphone;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtbankAccount;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pktransferDate;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane scrollPanetransferRemark;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txttransferRemark;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtaddress;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtiDNum;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtzipCode;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtmainUser;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtorderCustomer;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contrepairOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contservicePerson;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttoServiceDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contwarrantyStartDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contwarrantyEndDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvRCExpireDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contyearExamineExpireDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contgreenLabelExpireDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contwarrantyMile;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvRCBelongPlace;
    protected com.kingdee.bos.ctrl.swing.KDContainer kDContainer2;
    protected com.kingdee.bos.ctrl.swing.KDContainer kDContainer3;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer continsuInvalidDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer continsuCompany;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer continsuranceNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contplateDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvRCSendDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttraffInvalidDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttraffInsuNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbridgeFeeDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcity;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtrepairOrgUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtservicePerson;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pktoServiceDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkwarrantyStartDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkwarrantyEndDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkvRCExpireDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkyearExamineExpireDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkgreenLabelExpireDate;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtwarrantyMile;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtvRCBelongPlace;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDVehicleMiles;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kDVehicleMilesTabel;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel7;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtBelong;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtBelong_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkinsuInvalidDate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtinsuCompany;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtinsuranceNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkplateDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkvRCRegDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pktraffInvalidDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txttraffInsuNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkbridgeFeeDate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtcity;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtcreator;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkcreateTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtlastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pklastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtconfig;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtseries;
    protected com.kingdee.bos.ctrl.swing.KDComboBox vehicleCreateType;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane scrollPanevehicleRemark;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtvehicleRemark;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDViewRecordButton;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnRelateBusiness;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDViewVehicleAdvice;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel6;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel8;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtRepairSender;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtRepairSender_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtRepairRemark;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtRepairRemark_detailPanel = null;
    protected com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo editData = null;
    protected actionInstallRecord actionInstallRecord = null;
    protected actionRepairRecord actionRepairRecord = null;
    protected actionInsuranceRecord actionInsuranceRecord = null;
    protected actionAgent actionAgent = null;
    protected actionVeiwRecord actionVeiwRecord = null;
    protected actionOwnerChangeButton actionOwnerChangeButton = null;
    protected actionAddNewCustomer actionAddNewCustomer = null;
    protected ActionRelateBusiness actionRelateBusiness = null;
    protected ActionAutoSaleOrderView actionAutoSaleOrderView = null;
    protected ActionAutoPurOrderView actionAutoPurOrderView = null;
    protected ActionPurReceiveView actionPurReceiveView = null;
    protected ActionSaleIssueView actionSaleIssueView = null;
    protected ActionVehicleInsuranceView actionVehicleInsuranceView = null;
    protected ActionVehicleHangtagView actionVehicleHangtagView = null;
    protected ActionDecorationOrderView actionDecorationOrderView = null;
    protected ActionVehicleMortgageView actionVehicleMortgageView = null;
    protected ActionGetVehicleMiles actionGetVehicleMiles = null;
    protected ActionViewVehicleAdvice actionViewVehicleAdvice = null;
    protected ActionAutoVehicleRepairWOItem actionautoVehicleRepairWOItem = null;
    /**
     * output class constructor
     */
    public AbstractVehicleEditUI() throws Exception
    {
        super();
        this.defaultObjectName = "editData";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractVehicleEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionPrint
        String _tempStr = null;
        actionPrint.setEnabled(true);
        actionPrint.setDaemonRun(false);

        actionPrint.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl P"));
        _tempStr = resHelper.getString("ActionPrint.SHORT_DESCRIPTION");
        actionPrint.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.LONG_DESCRIPTION");
        actionPrint.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.NAME");
        actionPrint.putValue(ItemAction.NAME, _tempStr);
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrintPreview
        actionPrintPreview.setEnabled(true);
        actionPrintPreview.setDaemonRun(false);

        actionPrintPreview.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl P"));
        _tempStr = resHelper.getString("ActionPrintPreview.SHORT_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.LONG_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.NAME");
        actionPrintPreview.putValue(ItemAction.NAME, _tempStr);
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionInstallRecord
        this.actionInstallRecord = new actionInstallRecord(this);
        getActionManager().registerAction("actionInstallRecord", actionInstallRecord);
         this.actionInstallRecord.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRepairRecord
        this.actionRepairRecord = new actionRepairRecord(this);
        getActionManager().registerAction("actionRepairRecord", actionRepairRecord);
         this.actionRepairRecord.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionInsuranceRecord
        this.actionInsuranceRecord = new actionInsuranceRecord(this);
        getActionManager().registerAction("actionInsuranceRecord", actionInsuranceRecord);
         this.actionInsuranceRecord.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAgent
        this.actionAgent = new actionAgent(this);
        getActionManager().registerAction("actionAgent", actionAgent);
         this.actionAgent.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionVeiwRecord
        this.actionVeiwRecord = new actionVeiwRecord(this);
        getActionManager().registerAction("actionVeiwRecord", actionVeiwRecord);
         this.actionVeiwRecord.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionOwnerChangeButton
        this.actionOwnerChangeButton = new actionOwnerChangeButton(this);
        getActionManager().registerAction("actionOwnerChangeButton", actionOwnerChangeButton);
         this.actionOwnerChangeButton.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAddNewCustomer
        this.actionAddNewCustomer = new actionAddNewCustomer(this);
        getActionManager().registerAction("actionAddNewCustomer", actionAddNewCustomer);
         this.actionAddNewCustomer.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRelateBusiness
        this.actionRelateBusiness = new ActionRelateBusiness(this);
        getActionManager().registerAction("actionRelateBusiness", actionRelateBusiness);
         this.actionRelateBusiness.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAutoSaleOrderView
        this.actionAutoSaleOrderView = new ActionAutoSaleOrderView(this);
        getActionManager().registerAction("actionAutoSaleOrderView", actionAutoSaleOrderView);
         this.actionAutoSaleOrderView.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAutoPurOrderView
        this.actionAutoPurOrderView = new ActionAutoPurOrderView(this);
        getActionManager().registerAction("actionAutoPurOrderView", actionAutoPurOrderView);
         this.actionAutoPurOrderView.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionPurReceiveView
        this.actionPurReceiveView = new ActionPurReceiveView(this);
        getActionManager().registerAction("actionPurReceiveView", actionPurReceiveView);
         this.actionPurReceiveView.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionSaleIssueView
        this.actionSaleIssueView = new ActionSaleIssueView(this);
        getActionManager().registerAction("actionSaleIssueView", actionSaleIssueView);
         this.actionSaleIssueView.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionVehicleInsuranceView
        this.actionVehicleInsuranceView = new ActionVehicleInsuranceView(this);
        getActionManager().registerAction("actionVehicleInsuranceView", actionVehicleInsuranceView);
         this.actionVehicleInsuranceView.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionVehicleHangtagView
        this.actionVehicleHangtagView = new ActionVehicleHangtagView(this);
        getActionManager().registerAction("actionVehicleHangtagView", actionVehicleHangtagView);
         this.actionVehicleHangtagView.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionDecorationOrderView
        this.actionDecorationOrderView = new ActionDecorationOrderView(this);
        getActionManager().registerAction("actionDecorationOrderView", actionDecorationOrderView);
         this.actionDecorationOrderView.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionVehicleMortgageView
        this.actionVehicleMortgageView = new ActionVehicleMortgageView(this);
        getActionManager().registerAction("actionVehicleMortgageView", actionVehicleMortgageView);
         this.actionVehicleMortgageView.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionGetVehicleMiles
        this.actionGetVehicleMiles = new ActionGetVehicleMiles(this);
        getActionManager().registerAction("actionGetVehicleMiles", actionGetVehicleMiles);
         this.actionGetVehicleMiles.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewVehicleAdvice
        this.actionViewVehicleAdvice = new ActionViewVehicleAdvice(this);
        getActionManager().registerAction("actionViewVehicleAdvice", actionViewVehicleAdvice);
         this.actionViewVehicleAdvice.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionautoVehicleRepairWOItem
        this.actionautoVehicleRepairWOItem = new ActionAutoVehicleRepairWOItem(this);
        getActionManager().registerAction("actionautoVehicleRepairWOItem", actionautoVehicleRepairWOItem);
         this.actionautoVehicleRepairWOItem.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.contnumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsource = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbrand = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contvIN = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contengineNum = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contplateNum = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contplateColor = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contmodel = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDTabbedPane1 = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.contcreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contlastUpdateUser = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contlastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contconfig = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contseries = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkotherBrandVehicle = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contvehicleCreateType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contvehicleRemark = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkinitVehicle = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkisSpecialPrice = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.source = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtbrand = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtvIN = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtengineNum = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtplateNum = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.plateColor = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtmodel = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDPanel1 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel2 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel3 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel4 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer3 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.continner = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer4 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer5 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer7 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer8 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer9 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer10 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer11 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer12 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer6 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contoptionitemcombine = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcolor = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contmodelVersion = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkisDecoration = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.kDBizPromptBox1 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox2 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox3 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtinner = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox4 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox5 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox7 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox8 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox9 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox10 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox11 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox12 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDBizPromptBox6 = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtoptionitemcombine = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtcolor = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtmodelVersion = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.contonRoadStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contpDIStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contreservedStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contassignStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contvirtualIssueStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDSeparator5 = new com.kingdee.bos.ctrl.swing.KDSeparator();
        this.contbuyAutoOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contorgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contwarehouse = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contlocation = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDSeparator6 = new com.kingdee.bos.ctrl.swing.KDSeparator();
        this.contproductDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contguaranteeNum = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcertificationNum = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contimportDocNum = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.continspectionNum = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contvehicleInfo = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contkeyNum = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contkeyPlace = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contvehicleStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contpurReceiveDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkisMortgage = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contkeepingOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsaleOrg = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsaleDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDSeparator8 = new com.kingdee.bos.ctrl.swing.KDSeparator();
        this.contbelongOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttrustStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsubstoreStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsubAutoStore = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contkeepingLocation = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contofflineDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.onRoadStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.pDIStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.reservedStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.assignStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.virtualIssueStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtbuyAutoOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtorgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtwarehouse = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtlocation = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.pkproductDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtguaranteeNum = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtcertificationNum = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtimportDocNum = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtinspectionNum = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtvehicleInfo = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtkeyNum = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtkeyPlace = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.vehicleStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.pkpurReceiveDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtkeepingOrgUnit = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtsaleOrg = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.pksaleDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtbelongOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.trustStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.substoreStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtsubAutoStore = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtkeepingLocation = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkofflineDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.contcustomer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDContainer1 = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.kDViewCustomerButton = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDOwnerChangeButton = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.contmainUser = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDAddNewCustomerButton = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.contorderCustomer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtcustomer = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDPanel5 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.contownerName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contphone = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbankAccount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttransferDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttransferRemark = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contaddress = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contiDNum = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contzipCode = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtownerName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtphone = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtbankAccount = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pktransferDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.scrollPanetransferRemark = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txttransferRemark = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.txtaddress = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtiDNum = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtzipCode = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtmainUser = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtorderCustomer = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.contrepairOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contservicePerson = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttoServiceDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contwarrantyStartDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contwarrantyEndDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contvRCExpireDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contyearExamineExpireDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contgreenLabelExpireDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contwarrantyMile = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contvRCBelongPlace = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDContainer2 = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.kDContainer3 = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.continsuInvalidDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.continsuCompany = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.continsuranceNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contplateDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contvRCSendDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttraffInvalidDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttraffInsuNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbridgeFeeDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcity = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtrepairOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtservicePerson = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.pktoServiceDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pkwarrantyStartDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pkwarrantyEndDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pkvRCExpireDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pkyearExamineExpireDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pkgreenLabelExpireDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtwarrantyMile = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.prmtvRCBelongPlace = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDVehicleMiles = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDVehicleMilesTabel = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kDPanel7 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kdtBelong = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.pkinsuInvalidDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtinsuCompany = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtinsuranceNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkplateDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pkvRCRegDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pktraffInvalidDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txttraffInsuNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkbridgeFeeDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtcity = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtcreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.pkcreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtlastUpdateUser = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.pklastUpdateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtconfig = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtseries = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.vehicleCreateType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.scrollPanevehicleRemark = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txtvehicleRemark = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.kDViewRecordButton = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnRelateBusiness = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDViewVehicleAdvice = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDPanel6 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel8 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kdtRepairSender = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtRepairRemark = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.contnumber.setName("contnumber");
        this.contsource.setName("contsource");
        this.contbrand.setName("contbrand");
        this.contvIN.setName("contvIN");
        this.contengineNum.setName("contengineNum");
        this.contplateNum.setName("contplateNum");
        this.contplateColor.setName("contplateColor");
        this.contmodel.setName("contmodel");
        this.kDTabbedPane1.setName("kDTabbedPane1");
        this.contcreator.setName("contcreator");
        this.contcreateTime.setName("contcreateTime");
        this.contlastUpdateUser.setName("contlastUpdateUser");
        this.contlastUpdateTime.setName("contlastUpdateTime");
        this.contconfig.setName("contconfig");
        this.contseries.setName("contseries");
        this.chkotherBrandVehicle.setName("chkotherBrandVehicle");
        this.contvehicleCreateType.setName("contvehicleCreateType");
        this.contvehicleRemark.setName("contvehicleRemark");
        this.chkinitVehicle.setName("chkinitVehicle");
        this.chkisSpecialPrice.setName("chkisSpecialPrice");
        this.txtNumber.setName("txtNumber");
        this.source.setName("source");
        this.prmtbrand.setName("prmtbrand");
        this.txtvIN.setName("txtvIN");
        this.txtengineNum.setName("txtengineNum");
        this.txtplateNum.setName("txtplateNum");
        this.plateColor.setName("plateColor");
        this.prmtmodel.setName("prmtmodel");
        this.kDPanel1.setName("kDPanel1");
        this.kDPanel2.setName("kDPanel2");
        this.kDPanel3.setName("kDPanel3");
        this.kDPanel4.setName("kDPanel4");
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.kDLabelContainer3.setName("kDLabelContainer3");
        this.continner.setName("continner");
        this.kDLabelContainer4.setName("kDLabelContainer4");
        this.kDLabelContainer5.setName("kDLabelContainer5");
        this.kDLabelContainer7.setName("kDLabelContainer7");
        this.kDLabelContainer8.setName("kDLabelContainer8");
        this.kDLabelContainer9.setName("kDLabelContainer9");
        this.kDLabelContainer10.setName("kDLabelContainer10");
        this.kDLabelContainer11.setName("kDLabelContainer11");
        this.kDLabelContainer12.setName("kDLabelContainer12");
        this.kDLabelContainer6.setName("kDLabelContainer6");
        this.contoptionitemcombine.setName("contoptionitemcombine");
        this.contcolor.setName("contcolor");
        this.contmodelVersion.setName("contmodelVersion");
        this.chkisDecoration.setName("chkisDecoration");
        this.kDBizPromptBox1.setName("kDBizPromptBox1");
        this.kDBizPromptBox2.setName("kDBizPromptBox2");
        this.kDBizPromptBox3.setName("kDBizPromptBox3");
        this.prmtinner.setName("prmtinner");
        this.kDBizPromptBox4.setName("kDBizPromptBox4");
        this.kDBizPromptBox5.setName("kDBizPromptBox5");
        this.kDBizPromptBox7.setName("kDBizPromptBox7");
        this.kDBizPromptBox8.setName("kDBizPromptBox8");
        this.kDBizPromptBox9.setName("kDBizPromptBox9");
        this.kDBizPromptBox10.setName("kDBizPromptBox10");
        this.kDBizPromptBox11.setName("kDBizPromptBox11");
        this.kDBizPromptBox12.setName("kDBizPromptBox12");
        this.kDBizPromptBox6.setName("kDBizPromptBox6");
        this.prmtoptionitemcombine.setName("prmtoptionitemcombine");
        this.prmtcolor.setName("prmtcolor");
        this.txtmodelVersion.setName("txtmodelVersion");
        this.contonRoadStatus.setName("contonRoadStatus");
        this.contpDIStatus.setName("contpDIStatus");
        this.contreservedStatus.setName("contreservedStatus");
        this.contassignStatus.setName("contassignStatus");
        this.contvirtualIssueStatus.setName("contvirtualIssueStatus");
        this.kDSeparator5.setName("kDSeparator5");
        this.contbuyAutoOrgUnit.setName("contbuyAutoOrgUnit");
        this.contorgUnit.setName("contorgUnit");
        this.contwarehouse.setName("contwarehouse");
        this.contlocation.setName("contlocation");
        this.kDSeparator6.setName("kDSeparator6");
        this.contproductDate.setName("contproductDate");
        this.contguaranteeNum.setName("contguaranteeNum");
        this.contcertificationNum.setName("contcertificationNum");
        this.contimportDocNum.setName("contimportDocNum");
        this.continspectionNum.setName("continspectionNum");
        this.contvehicleInfo.setName("contvehicleInfo");
        this.contkeyNum.setName("contkeyNum");
        this.contkeyPlace.setName("contkeyPlace");
        this.contvehicleStatus.setName("contvehicleStatus");
        this.contpurReceiveDate.setName("contpurReceiveDate");
        this.chkisMortgage.setName("chkisMortgage");
        this.contkeepingOrgUnit.setName("contkeepingOrgUnit");
        this.contsaleOrg.setName("contsaleOrg");
        this.contsaleDate.setName("contsaleDate");
        this.kDSeparator8.setName("kDSeparator8");
        this.contbelongOrgUnit.setName("contbelongOrgUnit");
        this.conttrustStatus.setName("conttrustStatus");
        this.contsubstoreStatus.setName("contsubstoreStatus");
        this.contsubAutoStore.setName("contsubAutoStore");
        this.contkeepingLocation.setName("contkeepingLocation");
        this.contofflineDate.setName("contofflineDate");
        this.onRoadStatus.setName("onRoadStatus");
        this.pDIStatus.setName("pDIStatus");
        this.reservedStatus.setName("reservedStatus");
        this.assignStatus.setName("assignStatus");
        this.virtualIssueStatus.setName("virtualIssueStatus");
        this.prmtbuyAutoOrgUnit.setName("prmtbuyAutoOrgUnit");
        this.prmtorgUnit.setName("prmtorgUnit");
        this.prmtwarehouse.setName("prmtwarehouse");
        this.prmtlocation.setName("prmtlocation");
        this.pkproductDate.setName("pkproductDate");
        this.txtguaranteeNum.setName("txtguaranteeNum");
        this.txtcertificationNum.setName("txtcertificationNum");
        this.txtimportDocNum.setName("txtimportDocNum");
        this.txtinspectionNum.setName("txtinspectionNum");
        this.txtvehicleInfo.setName("txtvehicleInfo");
        this.txtkeyNum.setName("txtkeyNum");
        this.txtkeyPlace.setName("txtkeyPlace");
        this.vehicleStatus.setName("vehicleStatus");
        this.pkpurReceiveDate.setName("pkpurReceiveDate");
        this.txtkeepingOrgUnit.setName("txtkeepingOrgUnit");
        this.prmtsaleOrg.setName("prmtsaleOrg");
        this.pksaleDate.setName("pksaleDate");
        this.prmtbelongOrgUnit.setName("prmtbelongOrgUnit");
        this.trustStatus.setName("trustStatus");
        this.substoreStatus.setName("substoreStatus");
        this.prmtsubAutoStore.setName("prmtsubAutoStore");
        this.txtkeepingLocation.setName("txtkeepingLocation");
        this.pkofflineDate.setName("pkofflineDate");
        this.contcustomer.setName("contcustomer");
        this.kDContainer1.setName("kDContainer1");
        this.kDViewCustomerButton.setName("kDViewCustomerButton");
        this.kDOwnerChangeButton.setName("kDOwnerChangeButton");
        this.contmainUser.setName("contmainUser");
        this.kDAddNewCustomerButton.setName("kDAddNewCustomerButton");
        this.contorderCustomer.setName("contorderCustomer");
        this.prmtcustomer.setName("prmtcustomer");
        this.kDPanel5.setName("kDPanel5");
        this.contownerName.setName("contownerName");
        this.contphone.setName("contphone");
        this.contbankAccount.setName("contbankAccount");
        this.conttransferDate.setName("conttransferDate");
        this.conttransferRemark.setName("conttransferRemark");
        this.contaddress.setName("contaddress");
        this.contiDNum.setName("contiDNum");
        this.contzipCode.setName("contzipCode");
        this.txtownerName.setName("txtownerName");
        this.txtphone.setName("txtphone");
        this.txtbankAccount.setName("txtbankAccount");
        this.pktransferDate.setName("pktransferDate");
        this.scrollPanetransferRemark.setName("scrollPanetransferRemark");
        this.txttransferRemark.setName("txttransferRemark");
        this.txtaddress.setName("txtaddress");
        this.txtiDNum.setName("txtiDNum");
        this.txtzipCode.setName("txtzipCode");
        this.prmtmainUser.setName("prmtmainUser");
        this.prmtorderCustomer.setName("prmtorderCustomer");
        this.contrepairOrgUnit.setName("contrepairOrgUnit");
        this.contservicePerson.setName("contservicePerson");
        this.conttoServiceDate.setName("conttoServiceDate");
        this.contwarrantyStartDate.setName("contwarrantyStartDate");
        this.contwarrantyEndDate.setName("contwarrantyEndDate");
        this.contvRCExpireDate.setName("contvRCExpireDate");
        this.contyearExamineExpireDate.setName("contyearExamineExpireDate");
        this.contgreenLabelExpireDate.setName("contgreenLabelExpireDate");
        this.contwarrantyMile.setName("contwarrantyMile");
        this.contvRCBelongPlace.setName("contvRCBelongPlace");
        this.kDContainer2.setName("kDContainer2");
        this.kDContainer3.setName("kDContainer3");
        this.continsuInvalidDate.setName("continsuInvalidDate");
        this.continsuCompany.setName("continsuCompany");
        this.continsuranceNumber.setName("continsuranceNumber");
        this.contplateDate.setName("contplateDate");
        this.contvRCSendDate.setName("contvRCSendDate");
        this.conttraffInvalidDate.setName("conttraffInvalidDate");
        this.conttraffInsuNumber.setName("conttraffInsuNumber");
        this.contbridgeFeeDate.setName("contbridgeFeeDate");
        this.contcity.setName("contcity");
        this.prmtrepairOrgUnit.setName("prmtrepairOrgUnit");
        this.prmtservicePerson.setName("prmtservicePerson");
        this.pktoServiceDate.setName("pktoServiceDate");
        this.pkwarrantyStartDate.setName("pkwarrantyStartDate");
        this.pkwarrantyEndDate.setName("pkwarrantyEndDate");
        this.pkvRCExpireDate.setName("pkvRCExpireDate");
        this.pkyearExamineExpireDate.setName("pkyearExamineExpireDate");
        this.pkgreenLabelExpireDate.setName("pkgreenLabelExpireDate");
        this.txtwarrantyMile.setName("txtwarrantyMile");
        this.prmtvRCBelongPlace.setName("prmtvRCBelongPlace");
        this.kDVehicleMiles.setName("kDVehicleMiles");
        this.kDVehicleMilesTabel.setName("kDVehicleMilesTabel");
        this.kDPanel7.setName("kDPanel7");
        this.kdtBelong.setName("kdtBelong");
        this.pkinsuInvalidDate.setName("pkinsuInvalidDate");
        this.prmtinsuCompany.setName("prmtinsuCompany");
        this.txtinsuranceNumber.setName("txtinsuranceNumber");
        this.pkplateDate.setName("pkplateDate");
        this.pkvRCRegDate.setName("pkvRCRegDate");
        this.pktraffInvalidDate.setName("pktraffInvalidDate");
        this.txttraffInsuNumber.setName("txttraffInsuNumber");
        this.pkbridgeFeeDate.setName("pkbridgeFeeDate");
        this.prmtcity.setName("prmtcity");
        this.prmtcreator.setName("prmtcreator");
        this.pkcreateTime.setName("pkcreateTime");
        this.prmtlastUpdateUser.setName("prmtlastUpdateUser");
        this.pklastUpdateTime.setName("pklastUpdateTime");
        this.txtconfig.setName("txtconfig");
        this.prmtseries.setName("prmtseries");
        this.vehicleCreateType.setName("vehicleCreateType");
        this.scrollPanevehicleRemark.setName("scrollPanevehicleRemark");
        this.txtvehicleRemark.setName("txtvehicleRemark");
        this.kDViewRecordButton.setName("kDViewRecordButton");
        this.btnRelateBusiness.setName("btnRelateBusiness");
        this.kDViewVehicleAdvice.setName("kDViewVehicleAdvice");
        this.kDPanel6.setName("kDPanel6");
        this.kDPanel8.setName("kDPanel8");
        this.kdtRepairSender.setName("kdtRepairSender");
        this.kdtRepairRemark.setName("kdtRepairRemark");
        // CoreUI		
        this.btnSave.setVisible(false);		
        this.btnCopy.setVisible(false);		
        this.btnCancelCancel.setVisible(false);		
        this.btnCancel.setVisible(false);		
        this.menuItemPrint.setVisible(true);		
        this.menuItemPrintPreview.setVisible(true);
        // contnumber		
        this.contnumber.setBoundLabelText(resHelper.getString("contnumber.boundLabelText"));		
        this.contnumber.setBoundLabelLength(100);		
        this.contnumber.setBoundLabelUnderline(true);
        // contsource		
        this.contsource.setBoundLabelText(resHelper.getString("contsource.boundLabelText"));		
        this.contsource.setBoundLabelLength(100);		
        this.contsource.setBoundLabelUnderline(true);		
        this.contsource.setVisible(true);		
        this.contsource.setEnabled(false);
        // contbrand		
        this.contbrand.setBoundLabelText(resHelper.getString("contbrand.boundLabelText"));		
        this.contbrand.setBoundLabelLength(100);		
        this.contbrand.setBoundLabelUnderline(true);		
        this.contbrand.setVisible(true);		
        this.contbrand.setEnabled(false);
        // contvIN		
        this.contvIN.setBoundLabelText(resHelper.getString("contvIN.boundLabelText"));		
        this.contvIN.setBoundLabelLength(100);		
        this.contvIN.setBoundLabelUnderline(true);		
        this.contvIN.setVisible(true);
        // contengineNum		
        this.contengineNum.setBoundLabelText(resHelper.getString("contengineNum.boundLabelText"));		
        this.contengineNum.setBoundLabelLength(100);		
        this.contengineNum.setBoundLabelUnderline(true);		
        this.contengineNum.setVisible(true);
        // contplateNum		
        this.contplateNum.setBoundLabelText(resHelper.getString("contplateNum.boundLabelText"));		
        this.contplateNum.setBoundLabelLength(100);		
        this.contplateNum.setBoundLabelUnderline(true);		
        this.contplateNum.setVisible(true);
        // contplateColor		
        this.contplateColor.setBoundLabelText(resHelper.getString("contplateColor.boundLabelText"));		
        this.contplateColor.setBoundLabelLength(100);		
        this.contplateColor.setBoundLabelUnderline(true);		
        this.contplateColor.setVisible(true);
        // contmodel		
        this.contmodel.setBoundLabelText(resHelper.getString("contmodel.boundLabelText"));		
        this.contmodel.setBoundLabelLength(100);		
        this.contmodel.setBoundLabelUnderline(true);		
        this.contmodel.setVisible(true);
        // kDTabbedPane1
        // contcreator		
        this.contcreator.setBoundLabelText(resHelper.getString("contcreator.boundLabelText"));		
        this.contcreator.setBoundLabelLength(100);		
        this.contcreator.setBoundLabelUnderline(true);		
        this.contcreator.setVisible(true);		
        this.contcreator.setEnabled(false);
        // contcreateTime		
        this.contcreateTime.setBoundLabelText(resHelper.getString("contcreateTime.boundLabelText"));		
        this.contcreateTime.setBoundLabelLength(100);		
        this.contcreateTime.setBoundLabelUnderline(true);		
        this.contcreateTime.setVisible(true);		
        this.contcreateTime.setEnabled(false);
        // contlastUpdateUser		
        this.contlastUpdateUser.setBoundLabelText(resHelper.getString("contlastUpdateUser.boundLabelText"));		
        this.contlastUpdateUser.setBoundLabelLength(100);		
        this.contlastUpdateUser.setBoundLabelUnderline(true);		
        this.contlastUpdateUser.setVisible(true);		
        this.contlastUpdateUser.setEnabled(false);
        // contlastUpdateTime		
        this.contlastUpdateTime.setBoundLabelText(resHelper.getString("contlastUpdateTime.boundLabelText"));		
        this.contlastUpdateTime.setBoundLabelLength(100);		
        this.contlastUpdateTime.setBoundLabelUnderline(true);		
        this.contlastUpdateTime.setVisible(true);		
        this.contlastUpdateTime.setEnabled(false);
        // contconfig		
        this.contconfig.setBoundLabelText(resHelper.getString("contconfig.boundLabelText"));		
        this.contconfig.setBoundLabelLength(100);		
        this.contconfig.setBoundLabelUnderline(true);		
        this.contconfig.setEnabled(false);
        // contseries		
        this.contseries.setBoundLabelText(resHelper.getString("contseries.boundLabelText"));		
        this.contseries.setBoundLabelLength(100);		
        this.contseries.setBoundLabelUnderline(true);		
        this.contseries.setVisible(true);		
        this.contseries.setEnabled(false);
        // chkotherBrandVehicle		
        this.chkotherBrandVehicle.setText(resHelper.getString("chkotherBrandVehicle.text"));		
        this.chkotherBrandVehicle.setHorizontalAlignment(2);
        this.chkotherBrandVehicle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    chkotherBrandVehicle_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // contvehicleCreateType		
        this.contvehicleCreateType.setBoundLabelText(resHelper.getString("contvehicleCreateType.boundLabelText"));		
        this.contvehicleCreateType.setBoundLabelLength(100);		
        this.contvehicleCreateType.setBoundLabelUnderline(true);
        // contvehicleRemark		
        this.contvehicleRemark.setBoundLabelText(resHelper.getString("contvehicleRemark.boundLabelText"));		
        this.contvehicleRemark.setBoundLabelLength(100);		
        this.contvehicleRemark.setBoundLabelUnderline(true);		
        this.contvehicleRemark.setVisible(true);
        // chkinitVehicle		
        this.chkinitVehicle.setText(resHelper.getString("chkinitVehicle.text"));		
        this.chkinitVehicle.setHorizontalAlignment(2);
        this.chkinitVehicle.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    chkinitVehicle_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // chkisSpecialPrice		
        this.chkisSpecialPrice.setText(resHelper.getString("chkisSpecialPrice.text"));		
        this.chkisSpecialPrice.setVisible(false);		
        this.chkisSpecialPrice.setHorizontalAlignment(2);
        // txtNumber		
        this.txtNumber.setMaxLength(80);
        // source		
        this.source.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.VehicleSourceEnum").toArray());		
        this.source.setRequired(true);		
        this.source.setEnabled(false);
        this.source.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    source_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtbrand		
        this.prmtbrand.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.BrandQuery");		
        this.prmtbrand.setEditable(true);		
        this.prmtbrand.setDisplayFormat("$name$");		
        this.prmtbrand.setEditFormat("$number$");		
        this.prmtbrand.setCommitFormat("$number$");		
        this.prmtbrand.setRequired(false);		
        this.prmtbrand.setEnabled(false);
        this.prmtbrand.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtbrand_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.prmtbrand.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtbrand_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtvIN		
        this.txtvIN.setHorizontalAlignment(2);		
        this.txtvIN.setMaxLength(44);		
        this.txtvIN.setRequired(false);
        // txtengineNum		
        this.txtengineNum.setHorizontalAlignment(2);		
        this.txtengineNum.setMaxLength(44);		
        this.txtengineNum.setRequired(false);
        // txtplateNum		
        this.txtplateNum.setHorizontalAlignment(2);		
        this.txtplateNum.setMaxLength(44);		
        this.txtplateNum.setRequired(false);
        // plateColor		
        this.plateColor.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.PlateColourEnum").toArray());		
        this.plateColor.setRequired(false);
        // prmtmodel		
        this.prmtmodel.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.ModelQuery");		
        this.prmtmodel.setEditable(true);		
        this.prmtmodel.setDisplayFormat("$name$");		
        this.prmtmodel.setEditFormat("$number$");		
        this.prmtmodel.setCommitFormat("$number$");		
        this.prmtmodel.setRequired(false);
        this.prmtmodel.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtmodel_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.prmtmodel.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtmodel_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.prmtmodel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent e) {
                try {
                    prmtmodel_focusLost(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        // kDPanel1
        // kDPanel2
        // kDPanel3		
        this.kDPanel3.setBorder(null);
        // kDPanel4
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));		
        this.kDLabelContainer1.setBoundLabelLength(100);		
        this.kDLabelContainer1.setBoundLabelUnderline(true);		
        this.kDLabelContainer1.setEnabled(false);
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));		
        this.kDLabelContainer2.setBoundLabelLength(100);		
        this.kDLabelContainer2.setBoundLabelUnderline(true);		
        this.kDLabelContainer2.setEnabled(false);
        // kDLabelContainer3		
        this.kDLabelContainer3.setBoundLabelText(resHelper.getString("kDLabelContainer3.boundLabelText"));		
        this.kDLabelContainer3.setBoundLabelLength(100);		
        this.kDLabelContainer3.setBoundLabelUnderline(true);		
        this.kDLabelContainer3.setEnabled(false);
        // continner		
        this.continner.setBoundLabelText(resHelper.getString("continner.boundLabelText"));		
        this.continner.setBoundLabelLength(100);		
        this.continner.setBoundLabelUnderline(true);
        // kDLabelContainer4		
        this.kDLabelContainer4.setBoundLabelText(resHelper.getString("kDLabelContainer4.boundLabelText"));		
        this.kDLabelContainer4.setBoundLabelLength(100);		
        this.kDLabelContainer4.setBoundLabelUnderline(true);		
        this.kDLabelContainer4.setEnabled(false);
        // kDLabelContainer5		
        this.kDLabelContainer5.setBoundLabelText(resHelper.getString("kDLabelContainer5.boundLabelText"));		
        this.kDLabelContainer5.setBoundLabelLength(100);		
        this.kDLabelContainer5.setBoundLabelUnderline(true);		
        this.kDLabelContainer5.setEnabled(false);
        // kDLabelContainer7		
        this.kDLabelContainer7.setBoundLabelText(resHelper.getString("kDLabelContainer7.boundLabelText"));		
        this.kDLabelContainer7.setBoundLabelLength(100);		
        this.kDLabelContainer7.setBoundLabelUnderline(true);		
        this.kDLabelContainer7.setEnabled(false);
        // kDLabelContainer8		
        this.kDLabelContainer8.setBoundLabelText(resHelper.getString("kDLabelContainer8.boundLabelText"));		
        this.kDLabelContainer8.setBoundLabelLength(100);		
        this.kDLabelContainer8.setBoundLabelUnderline(true);		
        this.kDLabelContainer8.setEnabled(false);
        // kDLabelContainer9		
        this.kDLabelContainer9.setBoundLabelText(resHelper.getString("kDLabelContainer9.boundLabelText"));		
        this.kDLabelContainer9.setBoundLabelLength(100);		
        this.kDLabelContainer9.setBoundLabelUnderline(true);		
        this.kDLabelContainer9.setEnabled(false);
        // kDLabelContainer10		
        this.kDLabelContainer10.setBoundLabelText(resHelper.getString("kDLabelContainer10.boundLabelText"));		
        this.kDLabelContainer10.setBoundLabelLength(100);		
        this.kDLabelContainer10.setBoundLabelUnderline(true);		
        this.kDLabelContainer10.setEnabled(false);
        // kDLabelContainer11		
        this.kDLabelContainer11.setBoundLabelText(resHelper.getString("kDLabelContainer11.boundLabelText"));		
        this.kDLabelContainer11.setBoundLabelLength(100);		
        this.kDLabelContainer11.setBoundLabelUnderline(true);		
        this.kDLabelContainer11.setEnabled(false);
        // kDLabelContainer12		
        this.kDLabelContainer12.setBoundLabelText(resHelper.getString("kDLabelContainer12.boundLabelText"));		
        this.kDLabelContainer12.setBoundLabelLength(100);		
        this.kDLabelContainer12.setBoundLabelUnderline(true);		
        this.kDLabelContainer12.setEnabled(false);
        // kDLabelContainer6		
        this.kDLabelContainer6.setBoundLabelText(resHelper.getString("kDLabelContainer6.boundLabelText"));		
        this.kDLabelContainer6.setBoundLabelLength(100);		
        this.kDLabelContainer6.setBoundLabelUnderline(true);		
        this.kDLabelContainer6.setEnabled(false);
        // contoptionitemcombine		
        this.contoptionitemcombine.setBoundLabelText(resHelper.getString("contoptionitemcombine.boundLabelText"));		
        this.contoptionitemcombine.setBoundLabelLength(100);		
        this.contoptionitemcombine.setBoundLabelUnderline(true);
        // contcolor		
        this.contcolor.setBoundLabelText(resHelper.getString("contcolor.boundLabelText"));		
        this.contcolor.setBoundLabelLength(100);		
        this.contcolor.setBoundLabelUnderline(true);
        // contmodelVersion		
        this.contmodelVersion.setBoundLabelText(resHelper.getString("contmodelVersion.boundLabelText"));		
        this.contmodelVersion.setBoundLabelLength(100);		
        this.contmodelVersion.setBoundLabelUnderline(true);		
        this.contmodelVersion.setVisible(false);
        // chkisDecoration		
        this.chkisDecoration.setText(resHelper.getString("chkisDecoration.text"));		
        this.chkisDecoration.setVisible(true);		
        this.chkisDecoration.setHorizontalAlignment(2);		
        this.chkisDecoration.setEnabled(false);
        // kDBizPromptBox1		
        this.kDBizPromptBox1.setEnabled(false);
        this.kDBizPromptBox1.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox1_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox2		
        this.kDBizPromptBox2.setEnabled(false);
        this.kDBizPromptBox2.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox2_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox3		
        this.kDBizPromptBox3.setEnabled(false);
        this.kDBizPromptBox3.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox3_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtinner
        this.prmtinner.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtinner_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox4		
        this.kDBizPromptBox4.setEnabled(false);
        this.kDBizPromptBox4.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox4_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox5		
        this.kDBizPromptBox5.setEnabled(false);
        this.kDBizPromptBox5.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox5_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox7		
        this.kDBizPromptBox7.setEnabled(false);
        this.kDBizPromptBox7.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox7_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox8		
        this.kDBizPromptBox8.setEnabled(false);
        this.kDBizPromptBox8.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox8_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox9		
        this.kDBizPromptBox9.setEnabled(false);
        this.kDBizPromptBox9.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox9_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox10		
        this.kDBizPromptBox10.setEnabled(false);
        this.kDBizPromptBox10.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox10_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox11		
        this.kDBizPromptBox11.setEnabled(false);
        this.kDBizPromptBox11.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox11_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox12		
        this.kDBizPromptBox12.setEnabled(false);
        this.kDBizPromptBox12.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox12_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDBizPromptBox6		
        this.kDBizPromptBox6.setEnabled(false);
        this.kDBizPromptBox6.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    kDBizPromptBox6_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtoptionitemcombine
        this.prmtoptionitemcombine.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtoptionitemcombine_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtcolor
        this.prmtcolor.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtcolor_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtmodelVersion		
        this.txtmodelVersion.setVisible(false);		
        this.txtmodelVersion.setHorizontalAlignment(2);		
        this.txtmodelVersion.setDataType(0);		
        this.txtmodelVersion.setSupportedEmpty(true);		
        this.txtmodelVersion.setRequired(false);
        // contonRoadStatus		
        this.contonRoadStatus.setBoundLabelText(resHelper.getString("contonRoadStatus.boundLabelText"));		
        this.contonRoadStatus.setBoundLabelLength(100);		
        this.contonRoadStatus.setBoundLabelUnderline(true);		
        this.contonRoadStatus.setVisible(true);		
        this.contonRoadStatus.setEnabled(false);
        // contpDIStatus		
        this.contpDIStatus.setBoundLabelText(resHelper.getString("contpDIStatus.boundLabelText"));		
        this.contpDIStatus.setBoundLabelLength(100);		
        this.contpDIStatus.setBoundLabelUnderline(true);		
        this.contpDIStatus.setVisible(true);		
        this.contpDIStatus.setEnabled(false);
        // contreservedStatus		
        this.contreservedStatus.setBoundLabelText(resHelper.getString("contreservedStatus.boundLabelText"));		
        this.contreservedStatus.setBoundLabelLength(100);		
        this.contreservedStatus.setBoundLabelUnderline(true);		
        this.contreservedStatus.setVisible(true);		
        this.contreservedStatus.setEnabled(false);
        // contassignStatus		
        this.contassignStatus.setBoundLabelText(resHelper.getString("contassignStatus.boundLabelText"));		
        this.contassignStatus.setBoundLabelLength(100);		
        this.contassignStatus.setBoundLabelUnderline(true);		
        this.contassignStatus.setVisible(true);		
        this.contassignStatus.setEnabled(false);
        // contvirtualIssueStatus		
        this.contvirtualIssueStatus.setBoundLabelText(resHelper.getString("contvirtualIssueStatus.boundLabelText"));		
        this.contvirtualIssueStatus.setBoundLabelLength(100);		
        this.contvirtualIssueStatus.setBoundLabelUnderline(true);		
        this.contvirtualIssueStatus.setVisible(true);		
        this.contvirtualIssueStatus.setEnabled(false);
        // kDSeparator5
        // contbuyAutoOrgUnit		
        this.contbuyAutoOrgUnit.setBoundLabelText(resHelper.getString("contbuyAutoOrgUnit.boundLabelText"));		
        this.contbuyAutoOrgUnit.setBoundLabelLength(100);		
        this.contbuyAutoOrgUnit.setBoundLabelUnderline(true);		
        this.contbuyAutoOrgUnit.setVisible(true);		
        this.contbuyAutoOrgUnit.setEnabled(false);
        // contorgUnit		
        this.contorgUnit.setBoundLabelText(resHelper.getString("contorgUnit.boundLabelText"));		
        this.contorgUnit.setBoundLabelLength(100);		
        this.contorgUnit.setBoundLabelUnderline(true);		
        this.contorgUnit.setVisible(true);		
        this.contorgUnit.setEnabled(false);
        // contwarehouse		
        this.contwarehouse.setBoundLabelText(resHelper.getString("contwarehouse.boundLabelText"));		
        this.contwarehouse.setBoundLabelLength(100);		
        this.contwarehouse.setBoundLabelUnderline(true);		
        this.contwarehouse.setVisible(true);		
        this.contwarehouse.setEnabled(false);
        // contlocation		
        this.contlocation.setBoundLabelText(resHelper.getString("contlocation.boundLabelText"));		
        this.contlocation.setBoundLabelLength(100);		
        this.contlocation.setBoundLabelUnderline(true);		
        this.contlocation.setVisible(true);		
        this.contlocation.setEnabled(false);
        // kDSeparator6
        // contproductDate		
        this.contproductDate.setBoundLabelText(resHelper.getString("contproductDate.boundLabelText"));		
        this.contproductDate.setBoundLabelLength(100);		
        this.contproductDate.setBoundLabelUnderline(true);		
        this.contproductDate.setVisible(true);
        // contguaranteeNum		
        this.contguaranteeNum.setBoundLabelText(resHelper.getString("contguaranteeNum.boundLabelText"));		
        this.contguaranteeNum.setBoundLabelLength(100);		
        this.contguaranteeNum.setBoundLabelUnderline(true);		
        this.contguaranteeNum.setVisible(true);
        // contcertificationNum		
        this.contcertificationNum.setBoundLabelText(resHelper.getString("contcertificationNum.boundLabelText"));		
        this.contcertificationNum.setBoundLabelLength(100);		
        this.contcertificationNum.setBoundLabelUnderline(true);		
        this.contcertificationNum.setVisible(true);		
        this.contcertificationNum.setEnabled(false);
        // contimportDocNum		
        this.contimportDocNum.setBoundLabelText(resHelper.getString("contimportDocNum.boundLabelText"));		
        this.contimportDocNum.setBoundLabelLength(100);		
        this.contimportDocNum.setBoundLabelUnderline(true);		
        this.contimportDocNum.setVisible(true);
        // continspectionNum		
        this.continspectionNum.setBoundLabelText(resHelper.getString("continspectionNum.boundLabelText"));		
        this.continspectionNum.setBoundLabelLength(100);		
        this.continspectionNum.setBoundLabelUnderline(true);		
        this.continspectionNum.setVisible(true);
        // contvehicleInfo		
        this.contvehicleInfo.setBoundLabelText(resHelper.getString("contvehicleInfo.boundLabelText"));		
        this.contvehicleInfo.setBoundLabelLength(100);		
        this.contvehicleInfo.setBoundLabelUnderline(true);		
        this.contvehicleInfo.setVisible(true);
        // contkeyNum		
        this.contkeyNum.setBoundLabelText(resHelper.getString("contkeyNum.boundLabelText"));		
        this.contkeyNum.setBoundLabelLength(100);		
        this.contkeyNum.setBoundLabelUnderline(true);		
        this.contkeyNum.setVisible(true);
        // contkeyPlace		
        this.contkeyPlace.setBoundLabelText(resHelper.getString("contkeyPlace.boundLabelText"));		
        this.contkeyPlace.setBoundLabelLength(100);		
        this.contkeyPlace.setBoundLabelUnderline(true);		
        this.contkeyPlace.setVisible(true);
        // contvehicleStatus		
        this.contvehicleStatus.setBoundLabelText(resHelper.getString("contvehicleStatus.boundLabelText"));		
        this.contvehicleStatus.setBoundLabelLength(100);		
        this.contvehicleStatus.setBoundLabelUnderline(true);		
        this.contvehicleStatus.setVisible(true);		
        this.contvehicleStatus.setEnabled(false);
        // contpurReceiveDate		
        this.contpurReceiveDate.setBoundLabelText(resHelper.getString("contpurReceiveDate.boundLabelText"));		
        this.contpurReceiveDate.setBoundLabelLength(100);		
        this.contpurReceiveDate.setBoundLabelUnderline(true);		
        this.contpurReceiveDate.setVisible(true);		
        this.contpurReceiveDate.setEnabled(false);
        // chkisMortgage		
        this.chkisMortgage.setText(resHelper.getString("chkisMortgage.text"));		
        this.chkisMortgage.setVisible(true);		
        this.chkisMortgage.setHorizontalAlignment(2);		
        this.chkisMortgage.setEnabled(false);
        // contkeepingOrgUnit		
        this.contkeepingOrgUnit.setBoundLabelText(resHelper.getString("contkeepingOrgUnit.boundLabelText"));		
        this.contkeepingOrgUnit.setBoundLabelLength(100);		
        this.contkeepingOrgUnit.setBoundLabelUnderline(true);		
        this.contkeepingOrgUnit.setVisible(true);		
        this.contkeepingOrgUnit.setEnabled(false);
        // contsaleOrg		
        this.contsaleOrg.setBoundLabelText(resHelper.getString("contsaleOrg.boundLabelText"));		
        this.contsaleOrg.setBoundLabelLength(100);		
        this.contsaleOrg.setBoundLabelUnderline(true);		
        this.contsaleOrg.setVisible(true);
        // contsaleDate		
        this.contsaleDate.setBoundLabelText(resHelper.getString("contsaleDate.boundLabelText"));		
        this.contsaleDate.setBoundLabelLength(100);		
        this.contsaleDate.setBoundLabelUnderline(true);		
        this.contsaleDate.setVisible(true);
        // kDSeparator8
        // contbelongOrgUnit		
        this.contbelongOrgUnit.setBoundLabelText(resHelper.getString("contbelongOrgUnit.boundLabelText"));		
        this.contbelongOrgUnit.setBoundLabelLength(100);		
        this.contbelongOrgUnit.setBoundLabelUnderline(true);		
        this.contbelongOrgUnit.setVisible(true);		
        this.contbelongOrgUnit.setEnabled(false);
        // conttrustStatus		
        this.conttrustStatus.setBoundLabelText(resHelper.getString("conttrustStatus.boundLabelText"));		
        this.conttrustStatus.setBoundLabelLength(100);		
        this.conttrustStatus.setBoundLabelUnderline(true);		
        this.conttrustStatus.setVisible(true);		
        this.conttrustStatus.setEnabled(false);
        // contsubstoreStatus		
        this.contsubstoreStatus.setBoundLabelText(resHelper.getString("contsubstoreStatus.boundLabelText"));		
        this.contsubstoreStatus.setBoundLabelLength(100);		
        this.contsubstoreStatus.setBoundLabelUnderline(true);		
        this.contsubstoreStatus.setVisible(true);		
        this.contsubstoreStatus.setEnabled(false);
        // contsubAutoStore		
        this.contsubAutoStore.setBoundLabelText(resHelper.getString("contsubAutoStore.boundLabelText"));		
        this.contsubAutoStore.setBoundLabelLength(100);		
        this.contsubAutoStore.setBoundLabelUnderline(true);		
        this.contsubAutoStore.setVisible(true);		
        this.contsubAutoStore.setEnabled(false);
        // contkeepingLocation		
        this.contkeepingLocation.setBoundLabelText(resHelper.getString("contkeepingLocation.boundLabelText"));		
        this.contkeepingLocation.setBoundLabelLength(100);		
        this.contkeepingLocation.setBoundLabelUnderline(true);		
        this.contkeepingLocation.setVisible(true);		
        this.contkeepingLocation.setEnabled(false);
        // contofflineDate		
        this.contofflineDate.setBoundLabelText(resHelper.getString("contofflineDate.boundLabelText"));		
        this.contofflineDate.setBoundLabelLength(100);		
        this.contofflineDate.setBoundLabelUnderline(true);		
        this.contofflineDate.setVisible(true);
        // onRoadStatus		
        this.onRoadStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.OnRoadStatusEnum").toArray());		
        this.onRoadStatus.setRequired(false);		
        this.onRoadStatus.setEnabled(false);
        // pDIStatus		
        this.pDIStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.PDIStatusEnum").toArray());		
        this.pDIStatus.setRequired(false);		
        this.pDIStatus.setEnabled(false);
        // reservedStatus		
        this.reservedStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.ReservedStatusEnum").toArray());		
        this.reservedStatus.setRequired(false);		
        this.reservedStatus.setEnabled(false);
        // assignStatus		
        this.assignStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.AssignStatusEnum").toArray());		
        this.assignStatus.setRequired(false);		
        this.assignStatus.setEnabled(false);
        // virtualIssueStatus		
        this.virtualIssueStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.VirtualIssueStatusEnum").toArray());		
        this.virtualIssueStatus.setRequired(false);		
        this.virtualIssueStatus.setEnabled(false);
        // prmtbuyAutoOrgUnit		
        this.prmtbuyAutoOrgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");		
        this.prmtbuyAutoOrgUnit.setEditable(true);		
        this.prmtbuyAutoOrgUnit.setDisplayFormat("$name$");		
        this.prmtbuyAutoOrgUnit.setEditFormat("$number$");		
        this.prmtbuyAutoOrgUnit.setCommitFormat("$number$");		
        this.prmtbuyAutoOrgUnit.setRequired(false);		
        this.prmtbuyAutoOrgUnit.setEnabled(false);
        // prmtorgUnit		
        this.prmtorgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");		
        this.prmtorgUnit.setEditable(true);		
        this.prmtorgUnit.setDisplayFormat("$name$");		
        this.prmtorgUnit.setEditFormat("$number$");		
        this.prmtorgUnit.setCommitFormat("$number$");		
        this.prmtorgUnit.setRequired(false);		
        this.prmtorgUnit.setEnabled(false);
        this.prmtorgUnit.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtorgUnit_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtwarehouse		
        this.prmtwarehouse.setQueryInfo("com.kingdee.eas.basedata.scm.im.inv.app.F7WarehouseQuery");		
        this.prmtwarehouse.setEditable(true);		
        this.prmtwarehouse.setDisplayFormat("$name$");		
        this.prmtwarehouse.setEditFormat("$number$");		
        this.prmtwarehouse.setCommitFormat("$number$");		
        this.prmtwarehouse.setRequired(false);		
        this.prmtwarehouse.setEnabled(false);
        this.prmtwarehouse.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtwarehouse_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.prmtwarehouse.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtwarehouse_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtlocation		
        this.prmtlocation.setQueryInfo("com.kingdee.eas.basedata.scm.im.inv.app.F7LocationQuery");		
        this.prmtlocation.setEditable(true);		
        this.prmtlocation.setDisplayFormat("$name$");		
        this.prmtlocation.setEditFormat("$number$");		
        this.prmtlocation.setCommitFormat("$number$");		
        this.prmtlocation.setRequired(false);		
        this.prmtlocation.setEnabled(false);
        this.prmtlocation.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtlocation_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // pkproductDate		
        this.pkproductDate.setRequired(false);
        // txtguaranteeNum		
        this.txtguaranteeNum.setHorizontalAlignment(2);		
        this.txtguaranteeNum.setMaxLength(100);		
        this.txtguaranteeNum.setRequired(false);
        // txtcertificationNum		
        this.txtcertificationNum.setHorizontalAlignment(2);		
        this.txtcertificationNum.setMaxLength(100);		
        this.txtcertificationNum.setRequired(false);		
        this.txtcertificationNum.setEnabled(false);
        // txtimportDocNum		
        this.txtimportDocNum.setHorizontalAlignment(2);		
        this.txtimportDocNum.setMaxLength(100);		
        this.txtimportDocNum.setRequired(false);
        // txtinspectionNum		
        this.txtinspectionNum.setHorizontalAlignment(2);		
        this.txtinspectionNum.setMaxLength(100);		
        this.txtinspectionNum.setRequired(false);
        // txtvehicleInfo		
        this.txtvehicleInfo.setHorizontalAlignment(2);		
        this.txtvehicleInfo.setMaxLength(100);		
        this.txtvehicleInfo.setRequired(false);
        // txtkeyNum		
        this.txtkeyNum.setHorizontalAlignment(2);		
        this.txtkeyNum.setMaxLength(100);		
        this.txtkeyNum.setRequired(false);
        // txtkeyPlace		
        this.txtkeyPlace.setHorizontalAlignment(2);		
        this.txtkeyPlace.setMaxLength(100);		
        this.txtkeyPlace.setRequired(false);
        // vehicleStatus		
        this.vehicleStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.VehicleStatusEnum").toArray());		
        this.vehicleStatus.setRequired(false);		
        this.vehicleStatus.setEnabled(false);
        // pkpurReceiveDate		
        this.pkpurReceiveDate.setRequired(false);		
        this.pkpurReceiveDate.setEnabled(false);
        // txtkeepingOrgUnit		
        this.txtkeepingOrgUnit.setVisible(true);		
        this.txtkeepingOrgUnit.setHorizontalAlignment(2);		
        this.txtkeepingOrgUnit.setMaxLength(100);		
        this.txtkeepingOrgUnit.setRequired(false);		
        this.txtkeepingOrgUnit.setEnabled(false);
        // prmtsaleOrg		
        this.prmtsaleOrg.setQueryInfo("com.kingdee.eas.basedata.org.app.SaleItemQuery");		
        this.prmtsaleOrg.setVisible(true);		
        this.prmtsaleOrg.setEditable(true);		
        this.prmtsaleOrg.setDisplayFormat("$name$");		
        this.prmtsaleOrg.setEditFormat("$number$");		
        this.prmtsaleOrg.setCommitFormat("$number$");		
        this.prmtsaleOrg.setRequired(false);
        // pksaleDate		
        this.pksaleDate.setVisible(true);		
        this.pksaleDate.setRequired(false);
        // prmtbelongOrgUnit		
        this.prmtbelongOrgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");		
        this.prmtbelongOrgUnit.setVisible(true);		
        this.prmtbelongOrgUnit.setEditable(true);		
        this.prmtbelongOrgUnit.setDisplayFormat("$name$");		
        this.prmtbelongOrgUnit.setEditFormat("$number$");		
        this.prmtbelongOrgUnit.setCommitFormat("$number$");		
        this.prmtbelongOrgUnit.setRequired(false);		
        this.prmtbelongOrgUnit.setEnabled(false);
        // trustStatus		
        this.trustStatus.setVisible(true);		
        this.trustStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.TrustEnum").toArray());		
        this.trustStatus.setRequired(false);		
        this.trustStatus.setEnabled(false);
        // substoreStatus		
        this.substoreStatus.setVisible(true);		
        this.substoreStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.vm.SubstoreVehicleStatusEnum").toArray());		
        this.substoreStatus.setRequired(false);		
        this.substoreStatus.setEnabled(false);
        // prmtsubAutoStore		
        this.prmtsubAutoStore.setQueryInfo("com.kingdee.eas.auto4s.bdm.vm.app.SubAutoStoreQuery");		
        this.prmtsubAutoStore.setVisible(true);		
        this.prmtsubAutoStore.setEditable(true);		
        this.prmtsubAutoStore.setDisplayFormat("$name$");		
        this.prmtsubAutoStore.setEditFormat("$number$");		
        this.prmtsubAutoStore.setCommitFormat("$number$");		
        this.prmtsubAutoStore.setRequired(false);		
        this.prmtsubAutoStore.setEnabled(false);
        // txtkeepingLocation		
        this.txtkeepingLocation.setVisible(true);		
        this.txtkeepingLocation.setHorizontalAlignment(2);		
        this.txtkeepingLocation.setMaxLength(100);		
        this.txtkeepingLocation.setRequired(false);		
        this.txtkeepingLocation.setEnabled(false);
        // pkofflineDate		
        this.pkofflineDate.setVisible(true);		
        this.pkofflineDate.setRequired(false);
        // contcustomer		
        this.contcustomer.setBoundLabelText(resHelper.getString("contcustomer.boundLabelText"));		
        this.contcustomer.setBoundLabelLength(100);		
        this.contcustomer.setBoundLabelUnderline(true);		
        this.contcustomer.setVisible(true);
        // kDContainer1		
        this.kDContainer1.setTitle(resHelper.getString("kDContainer1.title"));		
        this.kDContainer1.setTitleLength(50);
        // kDViewCustomerButton		
        this.kDViewCustomerButton.setText(resHelper.getString("kDViewCustomerButton.text"));		
        this.kDViewCustomerButton.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_view"));
        this.kDViewCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    kDViewCustomerButton_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // kDOwnerChangeButton
        this.kDOwnerChangeButton.setAction((IItemAction)ActionProxyFactory.getProxy(actionOwnerChangeButton, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDOwnerChangeButton.setText(resHelper.getString("kDOwnerChangeButton.text"));		
        this.kDOwnerChangeButton.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_edit"));		
        this.kDOwnerChangeButton.setVisible(false);
        this.kDOwnerChangeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    kDOwnerChangeButton_mouseClicked(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        this.kDOwnerChangeButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                try {
                    kDOwnerChangeButton_keyPressed(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        // contmainUser		
        this.contmainUser.setBoundLabelText(resHelper.getString("contmainUser.boundLabelText"));		
        this.contmainUser.setBoundLabelLength(100);		
        this.contmainUser.setBoundLabelUnderline(true);		
        this.contmainUser.setVisible(false);
        // kDAddNewCustomerButton
        this.kDAddNewCustomerButton.setAction((IItemAction)ActionProxyFactory.getProxy(actionAddNewCustomer, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDAddNewCustomerButton.setText(resHelper.getString("kDAddNewCustomerButton.text"));		
        this.kDAddNewCustomerButton.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_new"));
        // contorderCustomer		
        this.contorderCustomer.setBoundLabelText(resHelper.getString("contorderCustomer.boundLabelText"));		
        this.contorderCustomer.setBoundLabelLength(100);		
        this.contorderCustomer.setBoundLabelUnderline(true);		
        this.contorderCustomer.setVisible(true);		
        this.contorderCustomer.setEnabled(false);
        // prmtcustomer		
        this.prmtcustomer.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.CustomerQuery");		
        this.prmtcustomer.setEditable(true);		
        this.prmtcustomer.setDisplayFormat("$name$");		
        this.prmtcustomer.setEditFormat("$number$");		
        this.prmtcustomer.setCommitFormat("$number$");		
        this.prmtcustomer.setRequired(false);
        this.prmtcustomer.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtcustomer_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDPanel5
        // contownerName		
        this.contownerName.setBoundLabelText(resHelper.getString("contownerName.boundLabelText"));		
        this.contownerName.setBoundLabelLength(150);		
        this.contownerName.setBoundLabelUnderline(true);		
        this.contownerName.setVisible(true);		
        this.contownerName.setEnabled(false);
        // contphone		
        this.contphone.setBoundLabelText(resHelper.getString("contphone.boundLabelText"));		
        this.contphone.setBoundLabelLength(150);		
        this.contphone.setBoundLabelUnderline(true);		
        this.contphone.setVisible(true);		
        this.contphone.setEnabled(false);
        // contbankAccount		
        this.contbankAccount.setBoundLabelText(resHelper.getString("contbankAccount.boundLabelText"));		
        this.contbankAccount.setBoundLabelLength(150);		
        this.contbankAccount.setBoundLabelUnderline(true);		
        this.contbankAccount.setVisible(true);		
        this.contbankAccount.setEnabled(false);
        // conttransferDate		
        this.conttransferDate.setBoundLabelText(resHelper.getString("conttransferDate.boundLabelText"));		
        this.conttransferDate.setBoundLabelLength(150);		
        this.conttransferDate.setBoundLabelUnderline(true);		
        this.conttransferDate.setVisible(true);		
        this.conttransferDate.setEnabled(false);
        // conttransferRemark		
        this.conttransferRemark.setBoundLabelText(resHelper.getString("conttransferRemark.boundLabelText"));		
        this.conttransferRemark.setBoundLabelLength(150);		
        this.conttransferRemark.setBoundLabelUnderline(true);		
        this.conttransferRemark.setVisible(true);		
        this.conttransferRemark.setEnabled(false);
        // contaddress		
        this.contaddress.setBoundLabelText(resHelper.getString("contaddress.boundLabelText"));		
        this.contaddress.setBoundLabelLength(150);		
        this.contaddress.setBoundLabelUnderline(true);		
        this.contaddress.setVisible(true);		
        this.contaddress.setEnabled(false);
        // contiDNum		
        this.contiDNum.setBoundLabelText(resHelper.getString("contiDNum.boundLabelText"));		
        this.contiDNum.setBoundLabelLength(150);		
        this.contiDNum.setBoundLabelUnderline(true);		
        this.contiDNum.setVisible(true);		
        this.contiDNum.setEnabled(false);
        // contzipCode		
        this.contzipCode.setBoundLabelText(resHelper.getString("contzipCode.boundLabelText"));		
        this.contzipCode.setBoundLabelLength(150);		
        this.contzipCode.setBoundLabelUnderline(true);		
        this.contzipCode.setVisible(true);		
        this.contzipCode.setEnabled(false);
        // txtownerName		
        this.txtownerName.setHorizontalAlignment(2);		
        this.txtownerName.setMaxLength(100);		
        this.txtownerName.setRequired(false);		
        this.txtownerName.setEnabled(false);
        // txtphone		
        this.txtphone.setHorizontalAlignment(2);		
        this.txtphone.setMaxLength(100);		
        this.txtphone.setRequired(false);		
        this.txtphone.setEnabled(false);
        // txtbankAccount		
        this.txtbankAccount.setHorizontalAlignment(2);		
        this.txtbankAccount.setMaxLength(100);		
        this.txtbankAccount.setRequired(false);		
        this.txtbankAccount.setEnabled(false);
        // pktransferDate		
        this.pktransferDate.setRequired(false);		
        this.pktransferDate.setEnabled(false);
        // scrollPanetransferRemark
        // txttransferRemark		
        this.txttransferRemark.setRequired(false);		
        this.txttransferRemark.setMaxLength(400);		
        this.txttransferRemark.setEnabled(false);
        // txtaddress		
        this.txtaddress.setHorizontalAlignment(2);		
        this.txtaddress.setMaxLength(255);		
        this.txtaddress.setRequired(false);		
        this.txtaddress.setEnabled(false);
        // txtiDNum		
        this.txtiDNum.setHorizontalAlignment(2);		
        this.txtiDNum.setMaxLength(100);		
        this.txtiDNum.setRequired(false);		
        this.txtiDNum.setEnabled(false);
        // txtzipCode		
        this.txtzipCode.setVisible(true);		
        this.txtzipCode.setHorizontalAlignment(2);		
        this.txtzipCode.setMaxLength(80);		
        this.txtzipCode.setRequired(false);		
        this.txtzipCode.setEnabled(false);
        // prmtmainUser		
        this.prmtmainUser.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.ContactPersonQuery");		
        this.prmtmainUser.setEditable(true);		
        this.prmtmainUser.setDisplayFormat("$isMainUser$");		
        this.prmtmainUser.setEditFormat("$number$");		
        this.prmtmainUser.setCommitFormat("$number$");		
        this.prmtmainUser.setRequired(false);		
        this.prmtmainUser.setVisible(false);
        this.prmtmainUser.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtmainUser_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtorderCustomer		
        this.prmtorderCustomer.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.CustomerQuery");		
        this.prmtorderCustomer.setVisible(true);		
        this.prmtorderCustomer.setEditable(true);		
        this.prmtorderCustomer.setDisplayFormat("$name$");		
        this.prmtorderCustomer.setEditFormat("$number$");		
        this.prmtorderCustomer.setCommitFormat("$number$");		
        this.prmtorderCustomer.setRequired(false);		
        this.prmtorderCustomer.setEnabled(false);
        // contrepairOrgUnit		
        this.contrepairOrgUnit.setBoundLabelText(resHelper.getString("contrepairOrgUnit.boundLabelText"));		
        this.contrepairOrgUnit.setBoundLabelLength(100);		
        this.contrepairOrgUnit.setBoundLabelUnderline(true);		
        this.contrepairOrgUnit.setVisible(false);
        // contservicePerson		
        this.contservicePerson.setBoundLabelText(resHelper.getString("contservicePerson.boundLabelText"));		
        this.contservicePerson.setBoundLabelLength(100);		
        this.contservicePerson.setBoundLabelUnderline(true);		
        this.contservicePerson.setVisible(false);
        // conttoServiceDate		
        this.conttoServiceDate.setBoundLabelText(resHelper.getString("conttoServiceDate.boundLabelText"));		
        this.conttoServiceDate.setBoundLabelLength(100);		
        this.conttoServiceDate.setBoundLabelUnderline(true);		
        this.conttoServiceDate.setVisible(false);
        // contwarrantyStartDate		
        this.contwarrantyStartDate.setBoundLabelText(resHelper.getString("contwarrantyStartDate.boundLabelText"));		
        this.contwarrantyStartDate.setBoundLabelLength(100);		
        this.contwarrantyStartDate.setBoundLabelUnderline(true);		
        this.contwarrantyStartDate.setVisible(true);
        // contwarrantyEndDate		
        this.contwarrantyEndDate.setBoundLabelText(resHelper.getString("contwarrantyEndDate.boundLabelText"));		
        this.contwarrantyEndDate.setBoundLabelLength(100);		
        this.contwarrantyEndDate.setBoundLabelUnderline(true);		
        this.contwarrantyEndDate.setVisible(true);
        // contvRCExpireDate		
        this.contvRCExpireDate.setBoundLabelText(resHelper.getString("contvRCExpireDate.boundLabelText"));		
        this.contvRCExpireDate.setBoundLabelLength(100);		
        this.contvRCExpireDate.setBoundLabelUnderline(true);		
        this.contvRCExpireDate.setVisible(true);
        // contyearExamineExpireDate		
        this.contyearExamineExpireDate.setBoundLabelText(resHelper.getString("contyearExamineExpireDate.boundLabelText"));		
        this.contyearExamineExpireDate.setBoundLabelLength(100);		
        this.contyearExamineExpireDate.setBoundLabelUnderline(true);		
        this.contyearExamineExpireDate.setVisible(true);
        // contgreenLabelExpireDate		
        this.contgreenLabelExpireDate.setBoundLabelText(resHelper.getString("contgreenLabelExpireDate.boundLabelText"));		
        this.contgreenLabelExpireDate.setBoundLabelLength(100);		
        this.contgreenLabelExpireDate.setBoundLabelUnderline(true);		
        this.contgreenLabelExpireDate.setVisible(true);
        // contwarrantyMile		
        this.contwarrantyMile.setBoundLabelText(resHelper.getString("contwarrantyMile.boundLabelText"));		
        this.contwarrantyMile.setBoundLabelLength(100);		
        this.contwarrantyMile.setBoundLabelUnderline(true);		
        this.contwarrantyMile.setVisible(true);
        // contvRCBelongPlace		
        this.contvRCBelongPlace.setBoundLabelText(resHelper.getString("contvRCBelongPlace.boundLabelText"));		
        this.contvRCBelongPlace.setBoundLabelLength(100);		
        this.contvRCBelongPlace.setBoundLabelUnderline(true);		
        this.contvRCBelongPlace.setVisible(true);
        // kDContainer2		
        this.kDContainer2.setTitle(resHelper.getString("kDContainer2.title"));
        // kDContainer3		
        this.kDContainer3.setTitle(resHelper.getString("kDContainer3.title"));
        // continsuInvalidDate		
        this.continsuInvalidDate.setBoundLabelText(resHelper.getString("continsuInvalidDate.boundLabelText"));		
        this.continsuInvalidDate.setBoundLabelLength(100);		
        this.continsuInvalidDate.setBoundLabelUnderline(true);		
        this.continsuInvalidDate.setVisible(true);
        // continsuCompany		
        this.continsuCompany.setBoundLabelText(resHelper.getString("continsuCompany.boundLabelText"));		
        this.continsuCompany.setBoundLabelLength(100);		
        this.continsuCompany.setBoundLabelUnderline(true);		
        this.continsuCompany.setVisible(true);
        // continsuranceNumber		
        this.continsuranceNumber.setBoundLabelText(resHelper.getString("continsuranceNumber.boundLabelText"));		
        this.continsuranceNumber.setBoundLabelLength(100);		
        this.continsuranceNumber.setBoundLabelUnderline(true);		
        this.continsuranceNumber.setVisible(true);
        // contplateDate		
        this.contplateDate.setBoundLabelText(resHelper.getString("contplateDate.boundLabelText"));		
        this.contplateDate.setBoundLabelLength(100);		
        this.contplateDate.setBoundLabelUnderline(true);		
        this.contplateDate.setVisible(true);
        // contvRCSendDate		
        this.contvRCSendDate.setBoundLabelText(resHelper.getString("contvRCSendDate.boundLabelText"));		
        this.contvRCSendDate.setBoundLabelLength(100);		
        this.contvRCSendDate.setBoundLabelUnderline(true);		
        this.contvRCSendDate.setVisible(true);
        // conttraffInvalidDate		
        this.conttraffInvalidDate.setBoundLabelText(resHelper.getString("conttraffInvalidDate.boundLabelText"));		
        this.conttraffInvalidDate.setBoundLabelLength(100);		
        this.conttraffInvalidDate.setBoundLabelUnderline(true);		
        this.conttraffInvalidDate.setVisible(true);
        // conttraffInsuNumber		
        this.conttraffInsuNumber.setBoundLabelText(resHelper.getString("conttraffInsuNumber.boundLabelText"));		
        this.conttraffInsuNumber.setBoundLabelLength(100);		
        this.conttraffInsuNumber.setBoundLabelUnderline(true);		
        this.conttraffInsuNumber.setVisible(true);
        // contbridgeFeeDate		
        this.contbridgeFeeDate.setBoundLabelText(resHelper.getString("contbridgeFeeDate.boundLabelText"));		
        this.contbridgeFeeDate.setBoundLabelLength(100);		
        this.contbridgeFeeDate.setBoundLabelUnderline(true);		
        this.contbridgeFeeDate.setVisible(true);
        // contcity		
        this.contcity.setBoundLabelText(resHelper.getString("contcity.boundLabelText"));		
        this.contcity.setBoundLabelLength(100);		
        this.contcity.setBoundLabelUnderline(true);		
        this.contcity.setVisible(true);
        // prmtrepairOrgUnit		
        this.prmtrepairOrgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");		
        this.prmtrepairOrgUnit.setEditable(true);		
        this.prmtrepairOrgUnit.setDisplayFormat("$name$");		
        this.prmtrepairOrgUnit.setEditFormat("$number$");		
        this.prmtrepairOrgUnit.setCommitFormat("$number$");		
        this.prmtrepairOrgUnit.setRequired(false);
        // prmtservicePerson		
        this.prmtservicePerson.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");		
        this.prmtservicePerson.setEditable(true);		
        this.prmtservicePerson.setDisplayFormat("$name$");		
        this.prmtservicePerson.setEditFormat("$number$");		
        this.prmtservicePerson.setCommitFormat("$number$");		
        this.prmtservicePerson.setRequired(false);
        // pktoServiceDate		
        this.pktoServiceDate.setRequired(false);
        // pkwarrantyStartDate		
        this.pkwarrantyStartDate.setRequired(false);
        // pkwarrantyEndDate		
        this.pkwarrantyEndDate.setRequired(false);
        // pkvRCExpireDate		
        this.pkvRCExpireDate.setRequired(false);
        // pkyearExamineExpireDate		
        this.pkyearExamineExpireDate.setRequired(false);
        // pkgreenLabelExpireDate		
        this.pkgreenLabelExpireDate.setRequired(false);
        // txtwarrantyMile		
        this.txtwarrantyMile.setHorizontalAlignment(2);		
        this.txtwarrantyMile.setDataType(1);		
        this.txtwarrantyMile.setSupportedEmpty(true);		
        this.txtwarrantyMile.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtwarrantyMile.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtwarrantyMile.setPrecision(4);		
        this.txtwarrantyMile.setRequired(false);
        // prmtvRCBelongPlace		
        this.prmtvRCBelongPlace.setQueryInfo("com.kingdee.eas.basedata.assistant.app.CityQuery");		
        this.prmtvRCBelongPlace.setEditable(true);		
        this.prmtvRCBelongPlace.setDisplayFormat("$name$");		
        this.prmtvRCBelongPlace.setEditFormat("$number$");		
        this.prmtvRCBelongPlace.setCommitFormat("$number$");		
        this.prmtvRCBelongPlace.setRequired(false);
        // kDVehicleMiles
        // kDVehicleMilesTabel
		String kDVehicleMilesTabelStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol1\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"returnTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"orgUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:styleID=\"sCol1\" /><t:Column t:key=\"returnMile\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /></t:ColumnGroup><t:Head><t:Row t:name=\"head1\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{returnTime}</t:Cell><t:Cell>$Resource{orgUnit}</t:Cell><t:Cell>$Resource{returnMile}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kDVehicleMilesTabel.setFormatXml(resHelper.translateString("kDVehicleMilesTabel",kDVehicleMilesTabelStrXML));

        

        this.kDVehicleMilesTabel.checkParsed();
        // kDPanel7
        // kdtBelong
		String kdtBelongStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol3\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"orgUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"person\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"serviceDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /><t:Column t:key=\"isDefault\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{orgUnit}</t:Cell><t:Cell>$Resource{person}</t:Cell><t:Cell>$Resource{serviceDate}</t:Cell><t:Cell>$Resource{isDefault}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtBelong.setFormatXml(resHelper.translateString("kdtBelong",kdtBelongStrXML));
        this.kdtBelong.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtBelong_editStopping(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
            public void editStarting(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtBelong_editStarting(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtBelong.putBindContents("editData",new String[] {"seq","orgUnit","person","serviceDate","isDefault"});


        this.kdtBelong.checkParsed();
        final KDBizPromptBox kdtBelong_orgUnit_PromptBox = new KDBizPromptBox();
        kdtBelong_orgUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");
        kdtBelong_orgUnit_PromptBox.setVisible(true);
        kdtBelong_orgUnit_PromptBox.setEditable(true);
        kdtBelong_orgUnit_PromptBox.setDisplayFormat("$number$");
        kdtBelong_orgUnit_PromptBox.setEditFormat("$number$");
        kdtBelong_orgUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtBelong_orgUnit_CellEditor = new KDTDefaultCellEditor(kdtBelong_orgUnit_PromptBox);
        this.kdtBelong.getColumn("orgUnit").setEditor(kdtBelong_orgUnit_CellEditor);
        ObjectValueRender kdtBelong_orgUnit_OVR = new ObjectValueRender();
        kdtBelong_orgUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtBelong.getColumn("orgUnit").setRenderer(kdtBelong_orgUnit_OVR);
        final KDBizPromptBox kdtBelong_person_PromptBox = new KDBizPromptBox();
        kdtBelong_person_PromptBox.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
        kdtBelong_person_PromptBox.setVisible(true);
        kdtBelong_person_PromptBox.setEditable(true);
        kdtBelong_person_PromptBox.setDisplayFormat("$number$");
        kdtBelong_person_PromptBox.setEditFormat("$number$");
        kdtBelong_person_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtBelong_person_CellEditor = new KDTDefaultCellEditor(kdtBelong_person_PromptBox);
        this.kdtBelong.getColumn("person").setEditor(kdtBelong_person_CellEditor);
        ObjectValueRender kdtBelong_person_OVR = new ObjectValueRender();
        kdtBelong_person_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtBelong.getColumn("person").setRenderer(kdtBelong_person_OVR);
        KDDatePicker kdtBelong_serviceDate_DatePicker = new KDDatePicker();
        kdtBelong_serviceDate_DatePicker.setName("kdtBelong_serviceDate_DatePicker");
        kdtBelong_serviceDate_DatePicker.setVisible(true);
        kdtBelong_serviceDate_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtBelong_serviceDate_CellEditor = new KDTDefaultCellEditor(kdtBelong_serviceDate_DatePicker);
        this.kdtBelong.getColumn("serviceDate").setEditor(kdtBelong_serviceDate_CellEditor);
        KDCheckBox kdtBelong_isDefault_CheckBox = new KDCheckBox();
        kdtBelong_isDefault_CheckBox.setName("kdtBelong_isDefault_CheckBox");
        KDTDefaultCellEditor kdtBelong_isDefault_CellEditor = new KDTDefaultCellEditor(kdtBelong_isDefault_CheckBox);
        this.kdtBelong.getColumn("isDefault").setEditor(kdtBelong_isDefault_CellEditor);
        // pkinsuInvalidDate		
        this.pkinsuInvalidDate.setVisible(true);		
        this.pkinsuInvalidDate.setRequired(false);
        // prmtinsuCompany		
        this.prmtinsuCompany.setQueryInfo("com.kingdee.eas.auto4s.bdm.vam.app.InsuranceCompanyQuery");		
        this.prmtinsuCompany.setVisible(true);		
        this.prmtinsuCompany.setEditable(true);		
        this.prmtinsuCompany.setDisplayFormat("$name$");		
        this.prmtinsuCompany.setEditFormat("$number$");		
        this.prmtinsuCompany.setCommitFormat("$number$");		
        this.prmtinsuCompany.setRequired(false);
        // txtinsuranceNumber		
        this.txtinsuranceNumber.setVisible(true);		
        this.txtinsuranceNumber.setHorizontalAlignment(2);		
        this.txtinsuranceNumber.setMaxLength(100);		
        this.txtinsuranceNumber.setRequired(false);
        // pkplateDate		
        this.pkplateDate.setVisible(true);		
        this.pkplateDate.setRequired(false);
        // pkvRCRegDate		
        this.pkvRCRegDate.setVisible(true);		
        this.pkvRCRegDate.setRequired(false);
        // pktraffInvalidDate		
        this.pktraffInvalidDate.setVisible(true);		
        this.pktraffInvalidDate.setRequired(false);
        // txttraffInsuNumber		
        this.txttraffInsuNumber.setVisible(true);		
        this.txttraffInsuNumber.setHorizontalAlignment(2);		
        this.txttraffInsuNumber.setMaxLength(100);		
        this.txttraffInsuNumber.setRequired(false);
        // pkbridgeFeeDate		
        this.pkbridgeFeeDate.setVisible(true);		
        this.pkbridgeFeeDate.setRequired(false);
        // prmtcity		
        this.prmtcity.setQueryInfo("com.kingdee.eas.basedata.assistant.app.CityQuery");		
        this.prmtcity.setVisible(true);		
        this.prmtcity.setEditable(true);		
        this.prmtcity.setDisplayFormat("$name$");		
        this.prmtcity.setEditFormat("$number$");		
        this.prmtcity.setCommitFormat("$number$");		
        this.prmtcity.setRequired(false);
        // prmtcreator		
        this.prmtcreator.setEditable(true);		
        this.prmtcreator.setDisplayFormat("$name$");		
        this.prmtcreator.setEditFormat("$number$");		
        this.prmtcreator.setCommitFormat("$number$");		
        this.prmtcreator.setRequired(false);		
        this.prmtcreator.setEnabled(false);
        // pkcreateTime		
        this.pkcreateTime.setRequired(false);		
        this.pkcreateTime.setEnabled(false);
        // prmtlastUpdateUser		
        this.prmtlastUpdateUser.setEditable(true);		
        this.prmtlastUpdateUser.setDisplayFormat("$name$");		
        this.prmtlastUpdateUser.setEditFormat("$number$");		
        this.prmtlastUpdateUser.setCommitFormat("$number$");		
        this.prmtlastUpdateUser.setRequired(false);		
        this.prmtlastUpdateUser.setEnabled(false);
        // pklastUpdateTime		
        this.pklastUpdateTime.setRequired(false);		
        this.pklastUpdateTime.setEnabled(false);
        // txtconfig		
        this.txtconfig.setEnabled(false);
        // prmtseries		
        this.prmtseries.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.SeriesQuery");		
        this.prmtseries.setEditable(true);		
        this.prmtseries.setDisplayFormat("$name$");		
        this.prmtseries.setEditFormat("$number$");		
        this.prmtseries.setCommitFormat("$number$");		
        this.prmtseries.setRequired(false);		
        this.prmtseries.setEnabled(false);
        this.prmtseries.addSelectorListener(new com.kingdee.bos.ctrl.swing.event.SelectorListener() {
            public void willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) {
                try {
                    prmtseries_willShow(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.prmtseries.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtseries_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // vehicleCreateType		
        this.vehicleCreateType.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.VehicleCreateTypeEnum").toArray());		
        this.vehicleCreateType.setRequired(false);
        this.vehicleCreateType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    vehicleCreateType_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // scrollPanevehicleRemark
        // txtvehicleRemark		
        this.txtvehicleRemark.setRequired(false);		
        this.txtvehicleRemark.setMaxLength(255);
        // kDViewRecordButton
        this.kDViewRecordButton.setAction((IItemAction)ActionProxyFactory.getProxy(actionVeiwRecord, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDViewRecordButton.setText(resHelper.getString("kDViewRecordButton.text"));		
        this.kDViewRecordButton.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTree_view"));		
        this.kDViewRecordButton.setVisible(false);
        // btnRelateBusiness		
        this.btnRelateBusiness.setText(resHelper.getString("btnRelateBusiness.text"));		
        this.btnRelateBusiness.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTree_view"));
        // kDViewVehicleAdvice
        this.kDViewVehicleAdvice.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewVehicleAdvice, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDViewVehicleAdvice.setText(resHelper.getString("kDViewVehicleAdvice.text"));		
        this.kDViewVehicleAdvice.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTree_view"));
        // kDPanel6
        // kDPanel8
        // kdtRepairSender
		String kdtRepairSenderStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol6\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" /><t:Column t:key=\"tel\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"addr\" t:width=\"800\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"zipCode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"idNumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"email\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol6\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{name}</t:Cell><t:Cell>$Resource{tel}</t:Cell><t:Cell>$Resource{addr}</t:Cell><t:Cell>$Resource{zipCode}</t:Cell><t:Cell>$Resource{idNumber}</t:Cell><t:Cell>$Resource{email}</t:Cell><t:Cell>$Resource{seq}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtRepairSender.setFormatXml(resHelper.translateString("kdtRepairSender",kdtRepairSenderStrXML));

                this.kdtRepairSender.putBindContents("editData",new String[] {"name","tel","addr","zipCode","idNumber","email","seq"});


        this.kdtRepairSender.checkParsed();
        KDTextField kdtRepairSender_name_TextField = new KDTextField();
        kdtRepairSender_name_TextField.setName("kdtRepairSender_name_TextField");
        kdtRepairSender_name_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRepairSender_name_CellEditor = new KDTDefaultCellEditor(kdtRepairSender_name_TextField);
        this.kdtRepairSender.getColumn("name").setEditor(kdtRepairSender_name_CellEditor);
        KDTextField kdtRepairSender_tel_TextField = new KDTextField();
        kdtRepairSender_tel_TextField.setName("kdtRepairSender_tel_TextField");
        kdtRepairSender_tel_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRepairSender_tel_CellEditor = new KDTDefaultCellEditor(kdtRepairSender_tel_TextField);
        this.kdtRepairSender.getColumn("tel").setEditor(kdtRepairSender_tel_CellEditor);
        KDTextField kdtRepairSender_addr_TextField = new KDTextField();
        kdtRepairSender_addr_TextField.setName("kdtRepairSender_addr_TextField");
        kdtRepairSender_addr_TextField.setMaxLength(255);
        KDTDefaultCellEditor kdtRepairSender_addr_CellEditor = new KDTDefaultCellEditor(kdtRepairSender_addr_TextField);
        this.kdtRepairSender.getColumn("addr").setEditor(kdtRepairSender_addr_CellEditor);
        KDTextField kdtRepairSender_zipCode_TextField = new KDTextField();
        kdtRepairSender_zipCode_TextField.setName("kdtRepairSender_zipCode_TextField");
        kdtRepairSender_zipCode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRepairSender_zipCode_CellEditor = new KDTDefaultCellEditor(kdtRepairSender_zipCode_TextField);
        this.kdtRepairSender.getColumn("zipCode").setEditor(kdtRepairSender_zipCode_CellEditor);
        KDTextField kdtRepairSender_idNumber_TextField = new KDTextField();
        kdtRepairSender_idNumber_TextField.setName("kdtRepairSender_idNumber_TextField");
        kdtRepairSender_idNumber_TextField.setMaxLength(18);
        KDTDefaultCellEditor kdtRepairSender_idNumber_CellEditor = new KDTDefaultCellEditor(kdtRepairSender_idNumber_TextField);
        this.kdtRepairSender.getColumn("idNumber").setEditor(kdtRepairSender_idNumber_CellEditor);
        KDTextField kdtRepairSender_email_TextField = new KDTextField();
        kdtRepairSender_email_TextField.setName("kdtRepairSender_email_TextField");
        kdtRepairSender_email_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRepairSender_email_CellEditor = new KDTDefaultCellEditor(kdtRepairSender_email_TextField);
        this.kdtRepairSender.getColumn("email").setEditor(kdtRepairSender_email_CellEditor);
        // kdtRepairRemark
		String kdtRepairRemarkStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol2\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol3\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"createTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:styleID=\"sCol0\" /><t:Column t:key=\"remark\" t:width=\"1500\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"repairWO\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{createTime}</t:Cell><t:Cell>$Resource{remark}</t:Cell><t:Cell>$Resource{repairWO}</t:Cell><t:Cell>$Resource{seq}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtRepairRemark.setFormatXml(resHelper.translateString("kdtRepairRemark",kdtRepairRemarkStrXML));

                this.kdtRepairRemark.putBindContents("editData",new String[] {"createTime","remark","repairWO","seq"});


        this.kdtRepairRemark.checkParsed();
        KDDatePicker kdtRepairRemark_createTime_DatePicker = new KDDatePicker();
        kdtRepairRemark_createTime_DatePicker.setName("kdtRepairRemark_createTime_DatePicker");
        kdtRepairRemark_createTime_DatePicker.setVisible(true);
        kdtRepairRemark_createTime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtRepairRemark_createTime_CellEditor = new KDTDefaultCellEditor(kdtRepairRemark_createTime_DatePicker);
        this.kdtRepairRemark.getColumn("createTime").setEditor(kdtRepairRemark_createTime_CellEditor);
        KDTextArea kdtRepairRemark_remark_TextArea = new KDTextArea();
        kdtRepairRemark_remark_TextArea.setName("kdtRepairRemark_remark_TextArea");
        kdtRepairRemark_remark_TextArea.setMaxLength(255);
        KDTDefaultCellEditor kdtRepairRemark_remark_CellEditor = new KDTDefaultCellEditor(kdtRepairRemark_remark_TextArea);
        this.kdtRepairRemark.getColumn("remark").setEditor(kdtRepairRemark_remark_CellEditor);
        final KDBizPromptBox kdtRepairRemark_repairWO_PromptBox = new KDBizPromptBox();
        kdtRepairRemark_repairWO_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.rsm.rs.app.RepairWOQuery");
        kdtRepairRemark_repairWO_PromptBox.setVisible(true);
        kdtRepairRemark_repairWO_PromptBox.setEditable(true);
        kdtRepairRemark_repairWO_PromptBox.setDisplayFormat("$number$");
        kdtRepairRemark_repairWO_PromptBox.setEditFormat("$number$");
        kdtRepairRemark_repairWO_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRepairRemark_repairWO_CellEditor = new KDTDefaultCellEditor(kdtRepairRemark_repairWO_PromptBox);
        this.kdtRepairRemark.getColumn("repairWO").setEditor(kdtRepairRemark_repairWO_CellEditor);
        ObjectValueRender kdtRepairRemark_repairWO_OVR = new ObjectValueRender();
        kdtRepairRemark_repairWO_OVR.setFormat(new BizDataFormat("$number$"));
        this.kdtRepairRemark.getColumn("repairWO").setRenderer(kdtRepairRemark_repairWO_OVR);
        			EntityViewInfo evikdtRepairRemark_repairWO_PromptBox = new EntityViewInfo ();
		evikdtRepairRemark_repairWO_PromptBox.setFilter(com.kingdee.eas.framework.FrameWorkUtils.getF7FilterInfoByAuthorizedOrg(com.kingdee.eas.basedata.org.OrgType.getEnum("Admin"),"OrgUnit.id"));
		kdtRepairRemark_repairWO_PromptBox.setEntityViewInfo(evikdtRepairRemark_repairWO_PromptBox);
					
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {pklastUpdateTime,prmtlastUpdateUser,pkcreateTime,prmtcreator,prmtcity,pkbridgeFeeDate,txttraffInsuNumber,pktraffInvalidDate,txtzipCode,trustStatus,prmtbelongOrgUnit,pkvRCRegDate,pkplateDate,pksaleDate,prmtsaleOrg,txtinsuranceNumber,pkinsuInvalidDate,prmtinsuCompany,chkisDecoration,txtkeepingOrgUnit,chkisMortgage,txtmodelVersion,chkinitVehicle,pkpurReceiveDate,txtiDNum,prmtvRCBelongPlace,kdtRepairRemark,prmtmainUser,kdtRepairSender,vehicleStatus,txtwarrantyMile,txtaddress,pkgreenLabelExpireDate,pkyearExamineExpireDate,pkvRCExpireDate,pkwarrantyEndDate,pkwarrantyStartDate,pktoServiceDate,prmtservicePerson,prmtrepairOrgUnit,txttransferRemark,pktransferDate,txtbankAccount,txtphone,kdtBelong,txtownerName,prmtcustomer,txtkeyPlace,txtkeyNum,txtvehicleInfo,txtinspectionNum,txtimportDocNum,txtcertificationNum,txtguaranteeNum,pkproductDate,prmtlocation,prmtwarehouse,prmtorgUnit,prmtbuyAutoOrgUnit,virtualIssueStatus,assignStatus,reservedStatus,pDIStatus,onRoadStatus,vehicleCreateType,prmtseries,prmtbrand,source,prmtmodel,txtvIN,txtengineNum,txtNumber,txtplateNum,plateColor,chkotherBrandVehicle,txtvehicleRemark,prmtinner,prmtcolor,prmtoptionitemcombine,prmtorderCustomer,substoreStatus,prmtsubAutoStore,chkisSpecialPrice,txtkeepingLocation,pkofflineDate}));
        this.setFocusCycleRoot(true);
		//Register control's property binding
		registerBindings();
		registerUIState();


    }

	public com.kingdee.bos.ctrl.swing.KDToolBar[] getUIMultiToolBar(){
		java.util.List list = new java.util.ArrayList();
		com.kingdee.bos.ctrl.swing.KDToolBar[] bars = super.getUIMultiToolBar();
		if (bars != null) {
			list.addAll(java.util.Arrays.asList(bars));
		}
		return (com.kingdee.bos.ctrl.swing.KDToolBar[])list.toArray(new com.kingdee.bos.ctrl.swing.KDToolBar[list.size()]);
	}




    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(0, 0, 1013, 629));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1013, 629));
        contnumber.setBounds(new Rectangle(672, 58, 270, 19));
        this.add(contnumber, new KDLayout.Constraints(672, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contsource.setBounds(new Rectangle(672, 34, 270, 19));
        this.add(contsource, new KDLayout.Constraints(672, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbrand.setBounds(new Rectangle(672, 10, 270, 19));
        this.add(contbrand, new KDLayout.Constraints(672, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvIN.setBounds(new Rectangle(10, 58, 270, 19));
        this.add(contvIN, new KDLayout.Constraints(10, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contengineNum.setBounds(new Rectangle(341, 58, 270, 19));
        this.add(contengineNum, new KDLayout.Constraints(341, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contplateNum.setBounds(new Rectangle(10, 82, 270, 19));
        this.add(contplateNum, new KDLayout.Constraints(10, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contplateColor.setBounds(new Rectangle(341, 82, 270, 19));
        this.add(contplateColor, new KDLayout.Constraints(341, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contmodel.setBounds(new Rectangle(10, 10, 270, 19));
        this.add(contmodel, new KDLayout.Constraints(10, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDTabbedPane1.setBounds(new Rectangle(10, 156, 934, 404));
        this.add(kDTabbedPane1, new KDLayout.Constraints(10, 156, 934, 404, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcreator.setBounds(new Rectangle(10, 567, 270, 19));
        this.add(contcreator, new KDLayout.Constraints(10, 567, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcreateTime.setBounds(new Rectangle(10, 591, 270, 19));
        this.add(contcreateTime, new KDLayout.Constraints(10, 591, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contlastUpdateUser.setBounds(new Rectangle(341, 567, 270, 19));
        this.add(contlastUpdateUser, new KDLayout.Constraints(341, 567, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contlastUpdateTime.setBounds(new Rectangle(341, 591, 270, 19));
        this.add(contlastUpdateTime, new KDLayout.Constraints(341, 591, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contconfig.setBounds(new Rectangle(10, 34, 601, 19));
        this.add(contconfig, new KDLayout.Constraints(10, 34, 601, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contseries.setBounds(new Rectangle(341, 10, 270, 19));
        this.add(contseries, new KDLayout.Constraints(341, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkotherBrandVehicle.setBounds(new Rectangle(672, 106, 270, 19));
        this.add(chkotherBrandVehicle, new KDLayout.Constraints(672, 106, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvehicleCreateType.setBounds(new Rectangle(672, 82, 270, 19));
        this.add(contvehicleCreateType, new KDLayout.Constraints(672, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvehicleRemark.setBounds(new Rectangle(10, 106, 601, 40));
        this.add(contvehicleRemark, new KDLayout.Constraints(10, 106, 601, 40, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkinitVehicle.setBounds(new Rectangle(672, 129, 270, 19));
        this.add(chkinitVehicle, new KDLayout.Constraints(672, 129, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkisSpecialPrice.setBounds(new Rectangle(796, 109, 270, 19));
        this.add(chkisSpecialPrice, new KDLayout.Constraints(796, 109, 270, 19, 0));
        //contnumber
        contnumber.setBoundEditor(txtNumber);
        //contsource
        contsource.setBoundEditor(source);
        //contbrand
        contbrand.setBoundEditor(prmtbrand);
        //contvIN
        contvIN.setBoundEditor(txtvIN);
        //contengineNum
        contengineNum.setBoundEditor(txtengineNum);
        //contplateNum
        contplateNum.setBoundEditor(txtplateNum);
        //contplateColor
        contplateColor.setBoundEditor(plateColor);
        //contmodel
        contmodel.setBoundEditor(prmtmodel);
        //kDTabbedPane1
        kDTabbedPane1.add(kDPanel1, resHelper.getString("kDPanel1.constraints"));
        kDTabbedPane1.add(kDPanel2, resHelper.getString("kDPanel2.constraints"));
        kDTabbedPane1.add(kDPanel3, resHelper.getString("kDPanel3.constraints"));
        kDTabbedPane1.add(kDPanel4, resHelper.getString("kDPanel4.constraints"));
        kDTabbedPane1.add(kDPanel6, resHelper.getString("kDPanel6.constraints"));
        kDTabbedPane1.add(kDPanel8, resHelper.getString("kDPanel8.constraints"));
        //kDPanel1
        kDPanel1.setLayout(new KDLayout());
        kDPanel1.putClientProperty("OriginalBounds", new Rectangle(0, 0, 933, 371));        kDLabelContainer1.setBounds(new Rectangle(9, 25, 270, 19));
        kDPanel1.add(kDLabelContainer1, new KDLayout.Constraints(9, 25, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer2.setBounds(new Rectangle(9, 66, 270, 19));
        kDPanel1.add(kDLabelContainer2, new KDLayout.Constraints(9, 66, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer3.setBounds(new Rectangle(9, 107, 270, 19));
        kDPanel1.add(kDLabelContainer3, new KDLayout.Constraints(9, 107, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        continner.setBounds(new Rectangle(9, 268, 270, 19));
        kDPanel1.add(continner, new KDLayout.Constraints(9, 268, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer4.setBounds(new Rectangle(9, 148, 270, 19));
        kDPanel1.add(kDLabelContainer4, new KDLayout.Constraints(9, 148, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer5.setBounds(new Rectangle(9, 189, 270, 19));
        kDPanel1.add(kDLabelContainer5, new KDLayout.Constraints(9, 189, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer7.setBounds(new Rectangle(323, 25, 270, 19));
        kDPanel1.add(kDLabelContainer7, new KDLayout.Constraints(323, 25, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer8.setBounds(new Rectangle(323, 66, 270, 19));
        kDPanel1.add(kDLabelContainer8, new KDLayout.Constraints(323, 66, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer9.setBounds(new Rectangle(323, 107, 270, 19));
        kDPanel1.add(kDLabelContainer9, new KDLayout.Constraints(323, 107, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer10.setBounds(new Rectangle(323, 148, 270, 19));
        kDPanel1.add(kDLabelContainer10, new KDLayout.Constraints(323, 148, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer11.setBounds(new Rectangle(323, 189, 270, 19));
        kDPanel1.add(kDLabelContainer11, new KDLayout.Constraints(323, 189, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer12.setBounds(new Rectangle(323, 230, 270, 19));
        kDPanel1.add(kDLabelContainer12, new KDLayout.Constraints(323, 230, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer6.setBounds(new Rectangle(9, 230, 270, 19));
        kDPanel1.add(kDLabelContainer6, new KDLayout.Constraints(9, 230, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contoptionitemcombine.setBounds(new Rectangle(9, 300, 585, 19));
        kDPanel1.add(contoptionitemcombine, new KDLayout.Constraints(9, 300, 585, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcolor.setBounds(new Rectangle(322, 269, 270, 19));
        kDPanel1.add(contcolor, new KDLayout.Constraints(322, 269, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contmodelVersion.setBounds(new Rectangle(627, 56, 270, 19));
        kDPanel1.add(contmodelVersion, new KDLayout.Constraints(627, 56, 270, 19, 0));
        chkisDecoration.setBounds(new Rectangle(9, 332, 270, 19));
        kDPanel1.add(chkisDecoration, new KDLayout.Constraints(9, 332, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(kDBizPromptBox1);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(kDBizPromptBox2);
        //kDLabelContainer3
        kDLabelContainer3.setBoundEditor(kDBizPromptBox3);
        //continner
        continner.setBoundEditor(prmtinner);
        //kDLabelContainer4
        kDLabelContainer4.setBoundEditor(kDBizPromptBox4);
        //kDLabelContainer5
        kDLabelContainer5.setBoundEditor(kDBizPromptBox5);
        //kDLabelContainer7
        kDLabelContainer7.setBoundEditor(kDBizPromptBox7);
        //kDLabelContainer8
        kDLabelContainer8.setBoundEditor(kDBizPromptBox8);
        //kDLabelContainer9
        kDLabelContainer9.setBoundEditor(kDBizPromptBox9);
        //kDLabelContainer10
        kDLabelContainer10.setBoundEditor(kDBizPromptBox10);
        //kDLabelContainer11
        kDLabelContainer11.setBoundEditor(kDBizPromptBox11);
        //kDLabelContainer12
        kDLabelContainer12.setBoundEditor(kDBizPromptBox12);
        //kDLabelContainer6
        kDLabelContainer6.setBoundEditor(kDBizPromptBox6);
        //contoptionitemcombine
        contoptionitemcombine.setBoundEditor(prmtoptionitemcombine);
        //contcolor
        contcolor.setBoundEditor(prmtcolor);
        //contmodelVersion
        contmodelVersion.setBoundEditor(txtmodelVersion);
        //kDPanel2
        kDPanel2.setLayout(new KDLayout());
        kDPanel2.putClientProperty("OriginalBounds", new Rectangle(0, 0, 933, 371));        contonRoadStatus.setBounds(new Rectangle(327, 20, 270, 19));
        kDPanel2.add(contonRoadStatus, new KDLayout.Constraints(327, 20, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contpDIStatus.setBounds(new Rectangle(645, 20, 270, 19));
        kDPanel2.add(contpDIStatus, new KDLayout.Constraints(645, 20, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contreservedStatus.setBounds(new Rectangle(10, 49, 270, 19));
        kDPanel2.add(contreservedStatus, new KDLayout.Constraints(10, 49, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contassignStatus.setBounds(new Rectangle(327, 49, 270, 19));
        kDPanel2.add(contassignStatus, new KDLayout.Constraints(327, 49, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvirtualIssueStatus.setBounds(new Rectangle(645, 49, 270, 19));
        kDPanel2.add(contvirtualIssueStatus, new KDLayout.Constraints(645, 49, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDSeparator5.setBounds(new Rectangle(4, 107, 914, 8));
        kDPanel2.add(kDSeparator5, new KDLayout.Constraints(4, 107, 914, 8, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbuyAutoOrgUnit.setBounds(new Rectangle(10, 125, 270, 19));
        kDPanel2.add(contbuyAutoOrgUnit, new KDLayout.Constraints(10, 125, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contorgUnit.setBounds(new Rectangle(10, 154, 270, 19));
        kDPanel2.add(contorgUnit, new KDLayout.Constraints(10, 154, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contwarehouse.setBounds(new Rectangle(327, 154, 270, 19));
        kDPanel2.add(contwarehouse, new KDLayout.Constraints(327, 154, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contlocation.setBounds(new Rectangle(645, 154, 270, 19));
        kDPanel2.add(contlocation, new KDLayout.Constraints(645, 154, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDSeparator6.setBounds(new Rectangle(4, 183, 913, 7));
        kDPanel2.add(kDSeparator6, new KDLayout.Constraints(4, 183, 913, 7, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contproductDate.setBounds(new Rectangle(10, 200, 270, 19));
        kDPanel2.add(contproductDate, new KDLayout.Constraints(10, 200, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contguaranteeNum.setBounds(new Rectangle(327, 200, 270, 19));
        kDPanel2.add(contguaranteeNum, new KDLayout.Constraints(327, 200, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcertificationNum.setBounds(new Rectangle(645, 200, 270, 19));
        kDPanel2.add(contcertificationNum, new KDLayout.Constraints(645, 200, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contimportDocNum.setBounds(new Rectangle(10, 229, 270, 19));
        kDPanel2.add(contimportDocNum, new KDLayout.Constraints(10, 229, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        continspectionNum.setBounds(new Rectangle(327, 229, 270, 19));
        kDPanel2.add(continspectionNum, new KDLayout.Constraints(327, 229, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvehicleInfo.setBounds(new Rectangle(10, 287, 270, 19));
        kDPanel2.add(contvehicleInfo, new KDLayout.Constraints(10, 287, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contkeyNum.setBounds(new Rectangle(10, 258, 270, 19));
        kDPanel2.add(contkeyNum, new KDLayout.Constraints(10, 258, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contkeyPlace.setBounds(new Rectangle(327, 258, 270, 19));
        kDPanel2.add(contkeyPlace, new KDLayout.Constraints(327, 258, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvehicleStatus.setBounds(new Rectangle(9, 20, 270, 19));
        kDPanel2.add(contvehicleStatus, new KDLayout.Constraints(9, 20, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contpurReceiveDate.setBounds(new Rectangle(327, 125, 270, 19));
        kDPanel2.add(contpurReceiveDate, new KDLayout.Constraints(327, 125, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkisMortgage.setBounds(new Rectangle(645, 287, 270, 19));
        kDPanel2.add(chkisMortgage, new KDLayout.Constraints(645, 287, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contkeepingOrgUnit.setBounds(new Rectangle(645, 229, 270, 19));
        kDPanel2.add(contkeepingOrgUnit, new KDLayout.Constraints(645, 229, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contsaleOrg.setBounds(new Rectangle(10, 327, 270, 19));
        kDPanel2.add(contsaleOrg, new KDLayout.Constraints(10, 327, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contsaleDate.setBounds(new Rectangle(327, 327, 270, 19));
        kDPanel2.add(contsaleDate, new KDLayout.Constraints(327, 327, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDSeparator8.setBounds(new Rectangle(4, 316, 913, 7));
        kDPanel2.add(kDSeparator8, new KDLayout.Constraints(4, 316, 913, 7, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbelongOrgUnit.setBounds(new Rectangle(645, 125, 270, 19));
        kDPanel2.add(contbelongOrgUnit, new KDLayout.Constraints(645, 125, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conttrustStatus.setBounds(new Rectangle(10, 78, 270, 19));
        kDPanel2.add(conttrustStatus, new KDLayout.Constraints(10, 78, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contsubstoreStatus.setBounds(new Rectangle(327, 78, 270, 19));
        kDPanel2.add(contsubstoreStatus, new KDLayout.Constraints(327, 78, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contsubAutoStore.setBounds(new Rectangle(645, 327, 270, 19));
        kDPanel2.add(contsubAutoStore, new KDLayout.Constraints(645, 327, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contkeepingLocation.setBounds(new Rectangle(645, 258, 270, 19));
        kDPanel2.add(contkeepingLocation, new KDLayout.Constraints(645, 258, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contofflineDate.setBounds(new Rectangle(327, 287, 270, 19));
        kDPanel2.add(contofflineDate, new KDLayout.Constraints(327, 287, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contonRoadStatus
        contonRoadStatus.setBoundEditor(onRoadStatus);
        //contpDIStatus
        contpDIStatus.setBoundEditor(pDIStatus);
        //contreservedStatus
        contreservedStatus.setBoundEditor(reservedStatus);
        //contassignStatus
        contassignStatus.setBoundEditor(assignStatus);
        //contvirtualIssueStatus
        contvirtualIssueStatus.setBoundEditor(virtualIssueStatus);
        //contbuyAutoOrgUnit
        contbuyAutoOrgUnit.setBoundEditor(prmtbuyAutoOrgUnit);
        //contorgUnit
        contorgUnit.setBoundEditor(prmtorgUnit);
        //contwarehouse
        contwarehouse.setBoundEditor(prmtwarehouse);
        //contlocation
        contlocation.setBoundEditor(prmtlocation);
        //contproductDate
        contproductDate.setBoundEditor(pkproductDate);
        //contguaranteeNum
        contguaranteeNum.setBoundEditor(txtguaranteeNum);
        //contcertificationNum
        contcertificationNum.setBoundEditor(txtcertificationNum);
        //contimportDocNum
        contimportDocNum.setBoundEditor(txtimportDocNum);
        //continspectionNum
        continspectionNum.setBoundEditor(txtinspectionNum);
        //contvehicleInfo
        contvehicleInfo.setBoundEditor(txtvehicleInfo);
        //contkeyNum
        contkeyNum.setBoundEditor(txtkeyNum);
        //contkeyPlace
        contkeyPlace.setBoundEditor(txtkeyPlace);
        //contvehicleStatus
        contvehicleStatus.setBoundEditor(vehicleStatus);
        //contpurReceiveDate
        contpurReceiveDate.setBoundEditor(pkpurReceiveDate);
        //contkeepingOrgUnit
        contkeepingOrgUnit.setBoundEditor(txtkeepingOrgUnit);
        //contsaleOrg
        contsaleOrg.setBoundEditor(prmtsaleOrg);
        //contsaleDate
        contsaleDate.setBoundEditor(pksaleDate);
        //contbelongOrgUnit
        contbelongOrgUnit.setBoundEditor(prmtbelongOrgUnit);
        //conttrustStatus
        conttrustStatus.setBoundEditor(trustStatus);
        //contsubstoreStatus
        contsubstoreStatus.setBoundEditor(substoreStatus);
        //contsubAutoStore
        contsubAutoStore.setBoundEditor(prmtsubAutoStore);
        //contkeepingLocation
        contkeepingLocation.setBoundEditor(txtkeepingLocation);
        //contofflineDate
        contofflineDate.setBoundEditor(pkofflineDate);
        //kDPanel3
        kDPanel3.setLayout(new KDLayout());
        kDPanel3.putClientProperty("OriginalBounds", new Rectangle(0, 0, 933, 371));        contcustomer.setBounds(new Rectangle(16, 21, 270, 19));
        kDPanel3.add(contcustomer, new KDLayout.Constraints(16, 21, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDContainer1.setBounds(new Rectangle(16, 76, 430, 255));
        kDPanel3.add(kDContainer1, new KDLayout.Constraints(16, 76, 430, 255, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDViewCustomerButton.setBounds(new Rectangle(390, 21, 85, 19));
        kDPanel3.add(kDViewCustomerButton, new KDLayout.Constraints(390, 21, 85, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDOwnerChangeButton.setBounds(new Rectangle(308, 48, 107, 19));
        kDPanel3.add(kDOwnerChangeButton, new KDLayout.Constraints(308, 48, 107, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contmainUser.setBounds(new Rectangle(431, 51, 270, 19));
        kDPanel3.add(contmainUser, new KDLayout.Constraints(431, 51, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDAddNewCustomerButton.setBounds(new Rectangle(297, 21, 85, 19));
        kDPanel3.add(kDAddNewCustomerButton, new KDLayout.Constraints(297, 21, 85, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contorderCustomer.setBounds(new Rectangle(16, 48, 270, 19));
        kDPanel3.add(contorderCustomer, new KDLayout.Constraints(16, 48, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contcustomer
        contcustomer.setBoundEditor(prmtcustomer);
        //kDContainer1
        kDContainer1.getContentPane().setLayout(new KDLayout());
        kDContainer1.getContentPane().putClientProperty("OriginalBounds", new Rectangle(16, 76, 430, 255));        kDPanel5.setBounds(new Rectangle(0, 0, 410, 240));
        kDContainer1.getContentPane().add(kDPanel5, new KDLayout.Constraints(0, 0, 410, 240, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDPanel5
        kDPanel5.setLayout(new KDLayout());
        kDPanel5.putClientProperty("OriginalBounds", new Rectangle(0, 0, 410, 240));        contownerName.setBounds(new Rectangle(28, 6, 350, 19));
        kDPanel5.add(contownerName, new KDLayout.Constraints(28, 6, 350, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contphone.setBounds(new Rectangle(28, 81, 350, 19));
        kDPanel5.add(contphone, new KDLayout.Constraints(28, 81, 350, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbankAccount.setBounds(new Rectangle(28, 106, 350, 19));
        kDPanel5.add(contbankAccount, new KDLayout.Constraints(28, 106, 350, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conttransferDate.setBounds(new Rectangle(28, 156, 350, 19));
        kDPanel5.add(conttransferDate, new KDLayout.Constraints(28, 156, 350, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conttransferRemark.setBounds(new Rectangle(28, 182, 350, 42));
        kDPanel5.add(conttransferRemark, new KDLayout.Constraints(28, 182, 350, 42, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contaddress.setBounds(new Rectangle(28, 31, 350, 19));
        kDPanel5.add(contaddress, new KDLayout.Constraints(28, 31, 350, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contiDNum.setBounds(new Rectangle(28, 131, 350, 19));
        kDPanel5.add(contiDNum, new KDLayout.Constraints(28, 131, 350, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contzipCode.setBounds(new Rectangle(28, 56, 350, 19));
        kDPanel5.add(contzipCode, new KDLayout.Constraints(28, 56, 350, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contownerName
        contownerName.setBoundEditor(txtownerName);
        //contphone
        contphone.setBoundEditor(txtphone);
        //contbankAccount
        contbankAccount.setBoundEditor(txtbankAccount);
        //conttransferDate
        conttransferDate.setBoundEditor(pktransferDate);
        //conttransferRemark
        conttransferRemark.setBoundEditor(scrollPanetransferRemark);
        //scrollPanetransferRemark
        scrollPanetransferRemark.getViewport().add(txttransferRemark, null);
        //contaddress
        contaddress.setBoundEditor(txtaddress);
        //contiDNum
        contiDNum.setBoundEditor(txtiDNum);
        //contzipCode
        contzipCode.setBoundEditor(txtzipCode);
        //contmainUser
        contmainUser.setBoundEditor(prmtmainUser);
        //contorderCustomer
        contorderCustomer.setBoundEditor(prmtorderCustomer);
        //kDPanel4
        kDPanel4.setLayout(new KDLayout());
        kDPanel4.putClientProperty("OriginalBounds", new Rectangle(0, 0, 933, 371));        contrepairOrgUnit.setBounds(new Rectangle(359, 252, 270, 19));
        kDPanel4.add(contrepairOrgUnit, new KDLayout.Constraints(359, 252, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contservicePerson.setBounds(new Rectangle(356, 286, 270, 19));
        kDPanel4.add(contservicePerson, new KDLayout.Constraints(356, 286, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conttoServiceDate.setBounds(new Rectangle(362, 214, 270, 19));
        kDPanel4.add(conttoServiceDate, new KDLayout.Constraints(362, 214, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contwarrantyStartDate.setBounds(new Rectangle(327, 75, 270, 19));
        kDPanel4.add(contwarrantyStartDate, new KDLayout.Constraints(327, 75, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contwarrantyEndDate.setBounds(new Rectangle(645, 75, 270, 19));
        kDPanel4.add(contwarrantyEndDate, new KDLayout.Constraints(645, 75, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvRCExpireDate.setBounds(new Rectangle(327, 103, 270, 19));
        kDPanel4.add(contvRCExpireDate, new KDLayout.Constraints(327, 103, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contyearExamineExpireDate.setBounds(new Rectangle(10, 131, 270, 19));
        kDPanel4.add(contyearExamineExpireDate, new KDLayout.Constraints(10, 131, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contgreenLabelExpireDate.setBounds(new Rectangle(327, 131, 270, 19));
        kDPanel4.add(contgreenLabelExpireDate, new KDLayout.Constraints(327, 131, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contwarrantyMile.setBounds(new Rectangle(10, 75, 270, 19));
        kDPanel4.add(contwarrantyMile, new KDLayout.Constraints(10, 75, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvRCBelongPlace.setBounds(new Rectangle(645, 103, 270, 19));
        kDPanel4.add(contvRCBelongPlace, new KDLayout.Constraints(645, 103, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDContainer2.setBounds(new Rectangle(10, 185, 356, 179));
        kDPanel4.add(kDContainer2, new KDLayout.Constraints(10, 185, 356, 179, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDContainer3.setBounds(new Rectangle(531, 186, 384, 178));
        kDPanel4.add(kDContainer3, new KDLayout.Constraints(531, 186, 384, 178, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        continsuInvalidDate.setBounds(new Rectangle(10, 19, 270, 19));
        kDPanel4.add(continsuInvalidDate, new KDLayout.Constraints(10, 19, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        continsuCompany.setBounds(new Rectangle(327, 19, 270, 19));
        kDPanel4.add(continsuCompany, new KDLayout.Constraints(327, 19, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        continsuranceNumber.setBounds(new Rectangle(645, 19, 270, 19));
        kDPanel4.add(continsuranceNumber, new KDLayout.Constraints(645, 19, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contplateDate.setBounds(new Rectangle(645, 131, 270, 19));
        kDPanel4.add(contplateDate, new KDLayout.Constraints(645, 131, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvRCSendDate.setBounds(new Rectangle(10, 103, 270, 19));
        kDPanel4.add(contvRCSendDate, new KDLayout.Constraints(10, 103, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conttraffInvalidDate.setBounds(new Rectangle(10, 47, 270, 19));
        kDPanel4.add(conttraffInvalidDate, new KDLayout.Constraints(10, 47, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conttraffInsuNumber.setBounds(new Rectangle(327, 47, 270, 19));
        kDPanel4.add(conttraffInsuNumber, new KDLayout.Constraints(327, 47, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbridgeFeeDate.setBounds(new Rectangle(645, 47, 270, 19));
        kDPanel4.add(contbridgeFeeDate, new KDLayout.Constraints(645, 47, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcity.setBounds(new Rectangle(10, 159, 270, 19));
        kDPanel4.add(contcity, new KDLayout.Constraints(10, 159, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contrepairOrgUnit
        contrepairOrgUnit.setBoundEditor(prmtrepairOrgUnit);
        //contservicePerson
        contservicePerson.setBoundEditor(prmtservicePerson);
        //conttoServiceDate
        conttoServiceDate.setBoundEditor(pktoServiceDate);
        //contwarrantyStartDate
        contwarrantyStartDate.setBoundEditor(pkwarrantyStartDate);
        //contwarrantyEndDate
        contwarrantyEndDate.setBoundEditor(pkwarrantyEndDate);
        //contvRCExpireDate
        contvRCExpireDate.setBoundEditor(pkvRCExpireDate);
        //contyearExamineExpireDate
        contyearExamineExpireDate.setBoundEditor(pkyearExamineExpireDate);
        //contgreenLabelExpireDate
        contgreenLabelExpireDate.setBoundEditor(pkgreenLabelExpireDate);
        //contwarrantyMile
        contwarrantyMile.setBoundEditor(txtwarrantyMile);
        //contvRCBelongPlace
        contvRCBelongPlace.setBoundEditor(prmtvRCBelongPlace);
        //kDContainer2
        kDContainer2.getContentPane().setLayout(new KDLayout());
        kDContainer2.getContentPane().putClientProperty("OriginalBounds", new Rectangle(10, 185, 356, 179));        kDVehicleMiles.setBounds(new Rectangle(0, 0, 338, 180));
        kDContainer2.getContentPane().add(kDVehicleMiles, new KDLayout.Constraints(0, 0, 338, 180, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDVehicleMiles
        kDVehicleMiles.setLayout(new KDLayout());
        kDVehicleMiles.putClientProperty("OriginalBounds", new Rectangle(0, 0, 338, 180));        kDVehicleMilesTabel.setBounds(new Rectangle(2, 28, 332, 149));
        kDVehicleMiles.add(kDVehicleMilesTabel, new KDLayout.Constraints(2, 28, 332, 149, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDContainer3
        kDContainer3.getContentPane().setLayout(new KDLayout());
        kDContainer3.getContentPane().putClientProperty("OriginalBounds", new Rectangle(531, 186, 384, 178));        kDPanel7.setBounds(new Rectangle(0, 1, 367, 175));
        kDContainer3.getContentPane().add(kDPanel7, new KDLayout.Constraints(0, 1, 367, 175, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDPanel7
        kDPanel7.setLayout(new KDLayout());
        kDPanel7.putClientProperty("OriginalBounds", new Rectangle(0, 1, 367, 175));        kdtBelong.setBounds(new Rectangle(7, 2, 357, 165));
        kdtBelong_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtBelong,new com.kingdee.eas.auto4s.bdm.pbd.VehicleBelongInfo(),null,false);
        kDPanel7.add(kdtBelong_detailPanel, new KDLayout.Constraints(7, 2, 357, 165, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //continsuInvalidDate
        continsuInvalidDate.setBoundEditor(pkinsuInvalidDate);
        //continsuCompany
        continsuCompany.setBoundEditor(prmtinsuCompany);
        //continsuranceNumber
        continsuranceNumber.setBoundEditor(txtinsuranceNumber);
        //contplateDate
        contplateDate.setBoundEditor(pkplateDate);
        //contvRCSendDate
        contvRCSendDate.setBoundEditor(pkvRCRegDate);
        //conttraffInvalidDate
        conttraffInvalidDate.setBoundEditor(pktraffInvalidDate);
        //conttraffInsuNumber
        conttraffInsuNumber.setBoundEditor(txttraffInsuNumber);
        //contbridgeFeeDate
        contbridgeFeeDate.setBoundEditor(pkbridgeFeeDate);
        //contcity
        contcity.setBoundEditor(prmtcity);
        //kDPanel6
        kDPanel6.setLayout(new KDLayout());
        kDPanel6.putClientProperty("OriginalBounds", new Rectangle(0, 0, 933, 371));        kdtRepairSender.setBounds(new Rectangle(2, 4, 924, 364));
        kdtRepairSender_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtRepairSender,new com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairSenderInfo(),null,false);
        kDPanel6.add(kdtRepairSender_detailPanel, new KDLayout.Constraints(2, 4, 924, 364, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //kDPanel8
        kDPanel8.setLayout(new KDLayout());
        kDPanel8.putClientProperty("OriginalBounds", new Rectangle(0, 0, 933, 371));        kdtRepairRemark.setBounds(new Rectangle(1, 0, 928, 370));
        kdtRepairRemark_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtRepairRemark,new com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairRemarkInfo(),null,false);
        kDPanel8.add(kdtRepairRemark_detailPanel, new KDLayout.Constraints(1, 0, 928, 370, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //contcreator
        contcreator.setBoundEditor(prmtcreator);
        //contcreateTime
        contcreateTime.setBoundEditor(pkcreateTime);
        //contlastUpdateUser
        contlastUpdateUser.setBoundEditor(prmtlastUpdateUser);
        //contlastUpdateTime
        contlastUpdateTime.setBoundEditor(pklastUpdateTime);
        //contconfig
        contconfig.setBoundEditor(txtconfig);
        //contseries
        contseries.setBoundEditor(prmtseries);
        //contvehicleCreateType
        contvehicleCreateType.setBoundEditor(vehicleCreateType);
        //contvehicleRemark
        contvehicleRemark.setBoundEditor(scrollPanevehicleRemark);
        //scrollPanevehicleRemark
        scrollPanevehicleRemark.getViewport().add(txtvehicleRemark, null);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuEdit);
        this.menuBar.add(MenuService);
        this.menuBar.add(menuView);
        this.menuBar.add(menuBiz);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuItemCloudShare);
        menuFile.add(menuSubmitOption);
        menuFile.add(kdSeparatorFWFile1);
        menuFile.add(rMenuItemSubmit);
        menuFile.add(rMenuItemSubmitAndAddNew);
        menuFile.add(rMenuItemSubmitAndPrint);
        menuFile.add(separatorFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator3);
        menuFile.add(menuItemExitCurrent);
        //menuSubmitOption
        menuSubmitOption.add(chkMenuItemSubmitAndAddNew);
        menuSubmitOption.add(chkMenuItemSubmitAndPrint);
        //menuEdit
        menuEdit.add(menuItemCopy);
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(kDSeparator4);
        menuEdit.add(menuItemReset);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemFirst);
        menuView.add(menuItemPre);
        menuView.add(menuItemNext);
        menuView.add(menuItemLast);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        //menuTool
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuHelp
        menuHelp.add(menuItemHelp);
        menuHelp.add(kDSeparator12);
        menuHelp.add(menuItemRegPro);
        menuHelp.add(menuItemPersonalSite);
        menuHelp.add(helpseparatorDiv);
        menuHelp.add(menuitemProductval);
        menuHelp.add(kDSeparatorProduct);
        menuHelp.add(menuItemAbout);

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnCloud);
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnEdit);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(kDViewRecordButton);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnRelateBusiness);
        this.toolBar.add(kDViewVehicleAdvice);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("otherBrandVehicle", boolean.class, this.chkotherBrandVehicle, "selected");
		dataBinder.registerBinding("initVehicle", boolean.class, this.chkinitVehicle, "selected");
		dataBinder.registerBinding("isSpecialPrice", boolean.class, this.chkisSpecialPrice, "selected");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("source", com.kingdee.eas.auto4s.bdm.pbd.VehicleSourceEnum.class, this.source, "selectedItem");
		dataBinder.registerBinding("brand", com.kingdee.eas.auto4s.bdm.pbd.BrandInfo.class, this.prmtbrand, "data");
		dataBinder.registerBinding("vIN", String.class, this.txtvIN, "text");
		dataBinder.registerBinding("engineNum", String.class, this.txtengineNum, "text");
		dataBinder.registerBinding("plateNum", String.class, this.txtplateNum, "text");
		dataBinder.registerBinding("plateColor", com.kingdee.eas.auto4s.bdm.pbd.PlateColourEnum.class, this.plateColor, "selectedItem");
		dataBinder.registerBinding("model", com.kingdee.eas.auto4s.bdm.pbd.ModelInfo.class, this.prmtmodel, "data");
		dataBinder.registerBinding("isDecoration", boolean.class, this.chkisDecoration, "selected");
		dataBinder.registerBinding("inner", com.kingdee.eas.auto4s.bdm.pbd.SeriesInnerInfo.class, this.prmtinner, "data");
		dataBinder.registerBinding("optionItemCombine", com.kingdee.eas.auto4s.bdm.pbd.OptionItemEntryInfo.class, this.prmtoptionitemcombine, "data");
		dataBinder.registerBinding("color", com.kingdee.eas.auto4s.bdm.pbd.SeriesColorInfo.class, this.prmtcolor, "data");
		dataBinder.registerBinding("modelVersion", int.class, this.txtmodelVersion, "value");
		dataBinder.registerBinding("isMortgage", boolean.class, this.chkisMortgage, "selected");
		dataBinder.registerBinding("onRoadStatus", com.kingdee.eas.auto4s.bdm.pbd.OnRoadStatusEnum.class, this.onRoadStatus, "selectedItem");
		dataBinder.registerBinding("pDIStatus", com.kingdee.eas.auto4s.bdm.pbd.PDIStatusEnum.class, this.pDIStatus, "selectedItem");
		dataBinder.registerBinding("reservedStatus", com.kingdee.eas.auto4s.bdm.pbd.ReservedStatusEnum.class, this.reservedStatus, "selectedItem");
		dataBinder.registerBinding("assignStatus", com.kingdee.eas.auto4s.bdm.pbd.AssignStatusEnum.class, this.assignStatus, "selectedItem");
		dataBinder.registerBinding("virtualIssueStatus", com.kingdee.eas.auto4s.bdm.pbd.VirtualIssueStatusEnum.class, this.virtualIssueStatus, "selectedItem");
		dataBinder.registerBinding("buyAutoOrgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtbuyAutoOrgUnit, "data");
		dataBinder.registerBinding("orgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtorgUnit, "data");
		dataBinder.registerBinding("warehouse", com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo.class, this.prmtwarehouse, "data");
		dataBinder.registerBinding("location", com.kingdee.eas.basedata.scm.im.inv.LocationInfo.class, this.prmtlocation, "data");
		dataBinder.registerBinding("productDate", java.util.Date.class, this.pkproductDate, "value");
		dataBinder.registerBinding("guaranteeNum", String.class, this.txtguaranteeNum, "text");
		dataBinder.registerBinding("certificationNum", String.class, this.txtcertificationNum, "text");
		dataBinder.registerBinding("importDocNum", String.class, this.txtimportDocNum, "text");
		dataBinder.registerBinding("inspectionNum", String.class, this.txtinspectionNum, "text");
		dataBinder.registerBinding("vehicleInfo", String.class, this.txtvehicleInfo, "text");
		dataBinder.registerBinding("keyNum", String.class, this.txtkeyNum, "text");
		dataBinder.registerBinding("keyPlace", String.class, this.txtkeyPlace, "text");
		dataBinder.registerBinding("vehicleStatus", com.kingdee.eas.auto4s.bdm.pbd.VehicleStatusEnum.class, this.vehicleStatus, "selectedItem");
		dataBinder.registerBinding("purReceiveDate", java.util.Date.class, this.pkpurReceiveDate, "value");
		dataBinder.registerBinding("keepingOrgUnit", String.class, this.txtkeepingOrgUnit, "text");
		dataBinder.registerBinding("saleOrg", com.kingdee.eas.basedata.org.SaleOrgUnitInfo.class, this.prmtsaleOrg, "data");
		dataBinder.registerBinding("saleDate", java.util.Date.class, this.pksaleDate, "value");
		dataBinder.registerBinding("belongOrgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtbelongOrgUnit, "data");
		dataBinder.registerBinding("trustStatus", com.kingdee.eas.auto4s.bdm.pbd.TrustEnum.class, this.trustStatus, "selectedItem");
		dataBinder.registerBinding("substoreStatus", com.kingdee.eas.auto4s.bdm.vm.SubstoreVehicleStatusEnum.class, this.substoreStatus, "selectedItem");
		dataBinder.registerBinding("subAutoStore", com.kingdee.eas.auto4s.bdm.vm.SubAutoStoreInfo.class, this.prmtsubAutoStore, "data");
		dataBinder.registerBinding("keepingLocation", String.class, this.txtkeepingLocation, "text");
		dataBinder.registerBinding("offlineDate", java.util.Date.class, this.pkofflineDate, "value");
		dataBinder.registerBinding("customer", com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo.class, this.prmtcustomer, "data");
		dataBinder.registerBinding("ownerName", String.class, this.txtownerName, "text");
		dataBinder.registerBinding("phone", String.class, this.txtphone, "text");
		dataBinder.registerBinding("bankAccount", String.class, this.txtbankAccount, "text");
		dataBinder.registerBinding("transferDate", java.util.Date.class, this.pktransferDate, "value");
		dataBinder.registerBinding("transferRemark", String.class, this.txttransferRemark, "text");
		dataBinder.registerBinding("address", String.class, this.txtaddress, "text");
		dataBinder.registerBinding("iDNum", String.class, this.txtiDNum, "text");
		dataBinder.registerBinding("zipCode", String.class, this.txtzipCode, "text");
		dataBinder.registerBinding("mainUser", com.kingdee.eas.auto4s.bdm.pbd.ContactPersonInfo.class, this.prmtmainUser, "data");
		dataBinder.registerBinding("orderCustomer", com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo.class, this.prmtorderCustomer, "data");
		dataBinder.registerBinding("repairOrgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtrepairOrgUnit, "data");
		dataBinder.registerBinding("servicePerson", com.kingdee.eas.basedata.person.PersonInfo.class, this.prmtservicePerson, "data");
		dataBinder.registerBinding("toServiceDate", java.util.Date.class, this.pktoServiceDate, "value");
		dataBinder.registerBinding("warrantyStartDate", java.util.Date.class, this.pkwarrantyStartDate, "value");
		dataBinder.registerBinding("warrantyEndDate", java.util.Date.class, this.pkwarrantyEndDate, "value");
		dataBinder.registerBinding("vRCExpireDate", java.util.Date.class, this.pkvRCExpireDate, "value");
		dataBinder.registerBinding("yearExamineExpireDate", java.util.Date.class, this.pkyearExamineExpireDate, "value");
		dataBinder.registerBinding("greenLabelExpireDate", java.util.Date.class, this.pkgreenLabelExpireDate, "value");
		dataBinder.registerBinding("warrantyMile", java.math.BigDecimal.class, this.txtwarrantyMile, "value");
		dataBinder.registerBinding("vRCBelongPlace", com.kingdee.eas.basedata.assistant.CityInfo.class, this.prmtvRCBelongPlace, "data");
		dataBinder.registerBinding("Belong.seq", int.class, this.kdtBelong, "seq.text");
		dataBinder.registerBinding("Belong", com.kingdee.eas.auto4s.bdm.pbd.VehicleBelongInfo.class, this.kdtBelong, "userObject");
		dataBinder.registerBinding("Belong.orgUnit", java.lang.Object.class, this.kdtBelong, "orgUnit.text");
		dataBinder.registerBinding("Belong.person", java.lang.Object.class, this.kdtBelong, "person.text");
		dataBinder.registerBinding("Belong.isDefault", boolean.class, this.kdtBelong, "isDefault.text");
		dataBinder.registerBinding("Belong.serviceDate", java.util.Date.class, this.kdtBelong, "serviceDate.text");
		dataBinder.registerBinding("insuInvalidDate", java.util.Date.class, this.pkinsuInvalidDate, "value");
		dataBinder.registerBinding("insuCompany", com.kingdee.eas.auto4s.bdm.vam.InsuranceCompanyInfo.class, this.prmtinsuCompany, "data");
		dataBinder.registerBinding("insuranceNumber", String.class, this.txtinsuranceNumber, "text");
		dataBinder.registerBinding("plateDate", java.util.Date.class, this.pkplateDate, "value");
		dataBinder.registerBinding("vRCRegDate", java.util.Date.class, this.pkvRCRegDate, "value");
		dataBinder.registerBinding("traffInvalidDate", java.util.Date.class, this.pktraffInvalidDate, "value");
		dataBinder.registerBinding("traffInsuNumber", String.class, this.txttraffInsuNumber, "text");
		dataBinder.registerBinding("bridgeFeeDate", java.util.Date.class, this.pkbridgeFeeDate, "value");
		dataBinder.registerBinding("city", com.kingdee.eas.basedata.assistant.CityInfo.class, this.prmtcity, "data");
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtcreator, "data");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.pkcreateTime, "value");
		dataBinder.registerBinding("lastUpdateUser", com.kingdee.eas.base.permission.UserInfo.class, this.prmtlastUpdateUser, "data");
		dataBinder.registerBinding("lastUpdateTime", java.sql.Timestamp.class, this.pklastUpdateTime, "value");
		dataBinder.registerBinding("series", com.kingdee.eas.auto4s.bdm.pbd.SeriesInfo.class, this.prmtseries, "data");
		dataBinder.registerBinding("vehicleCreateType", com.kingdee.eas.auto4s.bdm.pbd.VehicleCreateTypeEnum.class, this.vehicleCreateType, "selectedItem");
		dataBinder.registerBinding("vehicleRemark", String.class, this.txtvehicleRemark, "text");
		dataBinder.registerBinding("RepairSender.seq", int.class, this.kdtRepairSender, "seq.text");
		dataBinder.registerBinding("RepairSender", com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairSenderInfo.class, this.kdtRepairSender, "userObject");
		dataBinder.registerBinding("RepairSender.name", String.class, this.kdtRepairSender, "name.text");
		dataBinder.registerBinding("RepairSender.tel", String.class, this.kdtRepairSender, "tel.text");
		dataBinder.registerBinding("RepairSender.addr", String.class, this.kdtRepairSender, "addr.text");
		dataBinder.registerBinding("RepairSender.zipCode", String.class, this.kdtRepairSender, "zipCode.text");
		dataBinder.registerBinding("RepairSender.idNumber", String.class, this.kdtRepairSender, "idNumber.text");
		dataBinder.registerBinding("RepairSender.email", String.class, this.kdtRepairSender, "email.text");
		dataBinder.registerBinding("RepairRemark.seq", int.class, this.kdtRepairRemark, "seq.text");
		dataBinder.registerBinding("RepairRemark", com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairRemarkInfo.class, this.kdtRepairRemark, "userObject");
		dataBinder.registerBinding("RepairRemark.remark", String.class, this.kdtRepairRemark, "remark.text");
		dataBinder.registerBinding("RepairRemark.repairWO", java.lang.Object.class, this.kdtRepairRemark, "repairWO.text");
		dataBinder.registerBinding("RepairRemark.createTime", java.util.Date.class, this.kdtRepairRemark, "createTime.text");		
	}
	//Regiester UI State
	private void registerUIState(){
	        getActionManager().registerUIState(STATUS_ADDNEW, this.txtNumber, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_EDIT, this.txtNumber, ActionStateConst.ENABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.txtNumber, ActionStateConst.DISABLED);		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.auto4s.bdm.pbd.app.VehicleEditUIHandler";
	}
	public IUIActionPostman prepareInit() {
		IUIActionPostman clientHanlder = super.prepareInit();
		if (clientHanlder != null) {
			RequestContext request = new RequestContext();
    		request.setClassName(getUIHandlerClassName());
			clientHanlder.setRequestContext(request);
		}
		return clientHanlder;
    }
	
	public boolean isPrepareInit() {
    	return false;
    }
    protected void initUIP() {
        super.initUIP();
    }


    /**
     * output onShow method
     */
    public void onShow() throws Exception
    {
        super.onShow();
        this.pklastUpdateTime.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo)ov;
    }
    protected void removeByPK(IObjectPK pk) throws Exception {
    	IObjectValue editData = this.editData;
    	super.removeByPK(pk);
    	recycleNumberByOrg(editData,"NONE",editData.getString("number"));
    }
    
    protected void recycleNumberByOrg(IObjectValue editData,String orgType,String number) {
        if (!StringUtils.isEmpty(number))
        {
            try {
            	String companyID = null;            
            	com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID =com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}				
				if (!StringUtils.isEmpty(companyID) && iCodingRuleManager.isExist(editData, companyID) && iCodingRuleManager.isUseIntermitNumber(editData, companyID)) {
					iCodingRuleManager.recycleNumber(editData,companyID,number);					
				}
            }
            catch (Exception e)
            {
                handUIException(e);
            }
        }
    }
    protected void setAutoNumberByOrg(String orgType) {
    	if (editData == null) return;
		if (editData.getNumber() == null) {
            try {
            	String companyID = null;
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID = com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}
				com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
		        if (iCodingRuleManager.isExist(editData, companyID)) {
		            if (iCodingRuleManager.isAddView(editData, companyID)) {
		            	editData.setNumber(iCodingRuleManager.getNumber(editData,companyID));
		            }
	                txtNumber.setEnabled(false);
		        }
            }
            catch (Exception e) {
                handUIException(e);
                this.oldData = editData;
                com.kingdee.eas.util.SysUtil.abort();
            } 
        } 
        else {
            if (editData.getNumber().trim().length() > 0) {
                txtNumber.setText(editData.getNumber());
            }
        }
    }

    /**
     * output loadFields method
     */
    public void loadFields()
    {
        		setAutoNumberByOrg("NONE");
        dataBinder.loadFields();
    }
		protected void setOrgF7(KDBizPromptBox f7,com.kingdee.eas.basedata.org.OrgType orgType) throws Exception
		{
			com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer oufip = new com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer(orgType);
			oufip.getModel().setIsCUFilter(true);
			f7.setFilterInfoProducer(oufip);
		}

    /**
     * output storeFields method
     */
    public void storeFields()
    {
		dataBinder.storeFields();
    }

	/**
	 * ????????У??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );
		getValidateHelper().registerBindProperty("otherBrandVehicle", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("initVehicle", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isSpecialPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("source", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("brand", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("vIN", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("engineNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("plateNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("plateColor", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("model", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isDecoration", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("inner", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("optionItemCombine", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("color", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("modelVersion", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isMortgage", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("onRoadStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("pDIStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("reservedStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("assignStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("virtualIssueStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("buyAutoOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("orgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("warehouse", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("location", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("productDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("guaranteeNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("certificationNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("importDocNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("inspectionNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("vehicleInfo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("keyNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("keyPlace", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("vehicleStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("purReceiveDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("keepingOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("saleOrg", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("saleDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("belongOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("trustStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("substoreStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("subAutoStore", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("keepingLocation", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("offlineDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("customer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ownerName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("phone", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bankAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("transferDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("transferRemark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("address", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("iDNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("zipCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("mainUser", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("orderCustomer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("repairOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("servicePerson", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("toServiceDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("warrantyStartDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("warrantyEndDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("vRCExpireDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("yearExamineExpireDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("greenLabelExpireDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("warrantyMile", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("vRCBelongPlace", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Belong.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Belong", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Belong.orgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Belong.person", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Belong.isDefault", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Belong.serviceDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("insuInvalidDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("insuCompany", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("insuranceNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("plateDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("vRCRegDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("traffInvalidDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("traffInsuNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bridgeFeeDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("city", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateUser", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("series", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("vehicleCreateType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("vehicleRemark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairSender.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairSender", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairSender.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairSender.tel", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairSender.addr", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairSender.zipCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairSender.idNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairSender.email", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairRemark.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairRemark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairRemark.remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairRemark.repairWO", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairRemark.createTime", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
		            this.txtNumber.setEnabled(true);
        } else if (STATUS_EDIT.equals(this.oprtState)) {
		            this.txtNumber.setEnabled(true);
        } else if (STATUS_VIEW.equals(this.oprtState)) {
		            this.txtNumber.setEnabled(false);
        }
    }

    /**
     * output chkotherBrandVehicle_itemStateChanged method
     */
    protected void chkotherBrandVehicle_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output chkinitVehicle_itemStateChanged method
     */
    protected void chkinitVehicle_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output source_itemStateChanged method
     */
    protected void source_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output prmtbrand_willShow method
     */
    protected void prmtbrand_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output prmtbrand_dataChanged method
     */
    protected void prmtbrand_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtmodel_willShow method
     */
    protected void prmtmodel_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output prmtmodel_dataChanged method
     */
    protected void prmtmodel_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtmodel_focusLost method
     */
    protected void prmtmodel_focusLost(java.awt.event.FocusEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox1_willShow method
     */
    protected void kDBizPromptBox1_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox2_willShow method
     */
    protected void kDBizPromptBox2_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox3_willShow method
     */
    protected void kDBizPromptBox3_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output prmtinner_willShow method
     */
    protected void prmtinner_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox4_willShow method
     */
    protected void kDBizPromptBox4_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox5_willShow method
     */
    protected void kDBizPromptBox5_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox7_willShow method
     */
    protected void kDBizPromptBox7_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox8_willShow method
     */
    protected void kDBizPromptBox8_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox9_willShow method
     */
    protected void kDBizPromptBox9_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox10_willShow method
     */
    protected void kDBizPromptBox10_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox11_willShow method
     */
    protected void kDBizPromptBox11_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox12_willShow method
     */
    protected void kDBizPromptBox12_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDBizPromptBox6_willShow method
     */
    protected void kDBizPromptBox6_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output prmtoptionitemcombine_willShow method
     */
    protected void prmtoptionitemcombine_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output prmtcolor_willShow method
     */
    protected void prmtcolor_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output prmtorgUnit_dataChanged method
     */
    protected void prmtorgUnit_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtwarehouse_willShow method
     */
    protected void prmtwarehouse_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output prmtwarehouse_dataChanged method
     */
    protected void prmtwarehouse_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtlocation_willShow method
     */
    protected void prmtlocation_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kDViewCustomerButton_actionPerformed method
     */
    protected void kDViewCustomerButton_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output kDOwnerChangeButton_keyPressed method
     */
    protected void kDOwnerChangeButton_keyPressed(java.awt.event.KeyEvent e) throws Exception
    {
    }

    /**
     * output kDOwnerChangeButton_mouseClicked method
     */
    protected void kDOwnerChangeButton_mouseClicked(java.awt.event.MouseEvent e) throws Exception
    {
    }

    /**
     * output prmtcustomer_dataChanged method
     */
    protected void prmtcustomer_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtmainUser_willShow method
     */
    protected void prmtmainUser_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output kdtBelong_editStopping method
     */
    protected void kdtBelong_editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtBelong_editStarting method
     */
    protected void kdtBelong_editStarting(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output prmtseries_willShow method
     */
    protected void prmtseries_willShow(com.kingdee.bos.ctrl.swing.event.SelectorEvent e) throws Exception
    {
    }

    /**
     * output prmtseries_dataChanged method
     */
    protected void prmtseries_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output vehicleCreateType_itemStateChanged method
     */
    protected void vehicleCreateType_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
			sic.add(new SelectorItemInfo("otherBrandVehicle"));
			sic.add(new SelectorItemInfo("initVehicle"));
			sic.add(new SelectorItemInfo("isSpecialPrice"));
			sic.add(new SelectorItemInfo("number"));
			sic.add(new SelectorItemInfo("source"));
			sic.add(new SelectorItemInfo("brand.*"));
			sic.add(new SelectorItemInfo("brand.id"));
			sic.add(new SelectorItemInfo("brand.number"));
			sic.add(new SelectorItemInfo("brand.name"));
			sic.add(new SelectorItemInfo("vIN"));
			sic.add(new SelectorItemInfo("engineNum"));
			sic.add(new SelectorItemInfo("plateNum"));
			sic.add(new SelectorItemInfo("plateColor"));
			sic.add(new SelectorItemInfo("model.*"));
			sic.add(new SelectorItemInfo("model.id"));
			sic.add(new SelectorItemInfo("model.number"));
			sic.add(new SelectorItemInfo("model.name"));
			sic.add(new SelectorItemInfo("isDecoration"));
			sic.add(new SelectorItemInfo("inner.id"));
			sic.add(new SelectorItemInfo("inner.number"));
			sic.add(new SelectorItemInfo("inner.name"));
			sic.add(new SelectorItemInfo("optionItemCombine.*"));
			sic.add(new SelectorItemInfo("optionItemCombine.id"));
			sic.add(new SelectorItemInfo("color.id"));
			sic.add(new SelectorItemInfo("color.number"));
			sic.add(new SelectorItemInfo("color.name"));
			sic.add(new SelectorItemInfo("modelVersion"));
			sic.add(new SelectorItemInfo("isMortgage"));
			sic.add(new SelectorItemInfo("onRoadStatus"));
			sic.add(new SelectorItemInfo("pDIStatus"));
			sic.add(new SelectorItemInfo("reservedStatus"));
			sic.add(new SelectorItemInfo("assignStatus"));
			sic.add(new SelectorItemInfo("virtualIssueStatus"));
			sic.add(new SelectorItemInfo("buyAutoOrgUnit.id"));
			sic.add(new SelectorItemInfo("buyAutoOrgUnit.number"));
			sic.add(new SelectorItemInfo("buyAutoOrgUnit.name"));
			sic.add(new SelectorItemInfo("orgUnit.*"));
			sic.add(new SelectorItemInfo("orgUnit.id"));
			sic.add(new SelectorItemInfo("orgUnit.number"));
			sic.add(new SelectorItemInfo("orgUnit.name"));
			sic.add(new SelectorItemInfo("warehouse.id"));
			sic.add(new SelectorItemInfo("warehouse.number"));
			sic.add(new SelectorItemInfo("warehouse.name"));
			sic.add(new SelectorItemInfo("location.id"));
			sic.add(new SelectorItemInfo("location.number"));
			sic.add(new SelectorItemInfo("location.name"));
			sic.add(new SelectorItemInfo("productDate"));
			sic.add(new SelectorItemInfo("guaranteeNum"));
			sic.add(new SelectorItemInfo("certificationNum"));
			sic.add(new SelectorItemInfo("importDocNum"));
			sic.add(new SelectorItemInfo("inspectionNum"));
			sic.add(new SelectorItemInfo("vehicleInfo"));
			sic.add(new SelectorItemInfo("keyNum"));
			sic.add(new SelectorItemInfo("keyPlace"));
			sic.add(new SelectorItemInfo("vehicleStatus"));
			sic.add(new SelectorItemInfo("purReceiveDate"));
			sic.add(new SelectorItemInfo("keepingOrgUnit"));
			sic.add(new SelectorItemInfo("saleOrg.id"));
			sic.add(new SelectorItemInfo("saleOrg.number"));
			sic.add(new SelectorItemInfo("saleOrg.name"));
			sic.add(new SelectorItemInfo("saleDate"));
			sic.add(new SelectorItemInfo("belongOrgUnit.id"));
			sic.add(new SelectorItemInfo("belongOrgUnit.number"));
			sic.add(new SelectorItemInfo("belongOrgUnit.name"));
			sic.add(new SelectorItemInfo("trustStatus"));
			sic.add(new SelectorItemInfo("substoreStatus"));
			sic.add(new SelectorItemInfo("subAutoStore.id"));
			sic.add(new SelectorItemInfo("subAutoStore.number"));
			sic.add(new SelectorItemInfo("subAutoStore.name"));
			sic.add(new SelectorItemInfo("customer.*"));
			sic.add(new SelectorItemInfo("customer.id"));
			sic.add(new SelectorItemInfo("customer.number"));
			sic.add(new SelectorItemInfo("customer.name"));
			sic.add(new SelectorItemInfo("ownerName"));
			sic.add(new SelectorItemInfo("phone"));
			sic.add(new SelectorItemInfo("bankAccount"));
			sic.add(new SelectorItemInfo("transferDate"));
			sic.add(new SelectorItemInfo("transferRemark"));
			sic.add(new SelectorItemInfo("address"));
			sic.add(new SelectorItemInfo("iDNum"));
			sic.add(new SelectorItemInfo("zipCode"));
			sic.add(new SelectorItemInfo("mainUser.id"));
			sic.add(new SelectorItemInfo("mainUser.number"));
			sic.add(new SelectorItemInfo("mainUser.name"));
			sic.add(new SelectorItemInfo("mainUser.isMainUser"));
			sic.add(new SelectorItemInfo("orderCustomer.*"));
			sic.add(new SelectorItemInfo("orderCustomer.id"));
			sic.add(new SelectorItemInfo("orderCustomer.number"));
			sic.add(new SelectorItemInfo("orderCustomer.name"));
			sic.add(new SelectorItemInfo("repairOrgUnit.id"));
			sic.add(new SelectorItemInfo("repairOrgUnit.number"));
			sic.add(new SelectorItemInfo("repairOrgUnit.name"));
			sic.add(new SelectorItemInfo("servicePerson.id"));
			sic.add(new SelectorItemInfo("servicePerson.number"));
			sic.add(new SelectorItemInfo("servicePerson.name"));
			sic.add(new SelectorItemInfo("toServiceDate"));
			sic.add(new SelectorItemInfo("warrantyStartDate"));
			sic.add(new SelectorItemInfo("warrantyEndDate"));
			sic.add(new SelectorItemInfo("vRCExpireDate"));
			sic.add(new SelectorItemInfo("yearExamineExpireDate"));
			sic.add(new SelectorItemInfo("greenLabelExpireDate"));
			sic.add(new SelectorItemInfo("warrantyMile"));
			sic.add(new SelectorItemInfo("vRCBelongPlace.id"));
			sic.add(new SelectorItemInfo("vRCBelongPlace.number"));
			sic.add(new SelectorItemInfo("vRCBelongPlace.name"));
			sic.add(new SelectorItemInfo("Belong.seq"));
			sic.add(new SelectorItemInfo("Belong.*"));
			sic.add(new SelectorItemInfo("Belong.orgUnit.*"));
			sic.add(new SelectorItemInfo("Belong.orgUnit.id"));
			sic.add(new SelectorItemInfo("Belong.orgUnit.name"));
			sic.add(new SelectorItemInfo("Belong.orgUnit.number"));
			sic.add(new SelectorItemInfo("Belong.person.*"));
			sic.add(new SelectorItemInfo("Belong.person.id"));
			sic.add(new SelectorItemInfo("Belong.person.name"));
			sic.add(new SelectorItemInfo("Belong.person.number"));
			sic.add(new SelectorItemInfo("Belong.isDefault"));
			sic.add(new SelectorItemInfo("Belong.serviceDate"));
			sic.add(new SelectorItemInfo("insuInvalidDate"));
			sic.add(new SelectorItemInfo("insuCompany.id"));
			sic.add(new SelectorItemInfo("insuCompany.number"));
			sic.add(new SelectorItemInfo("insuCompany.name"));
			sic.add(new SelectorItemInfo("insuranceNumber"));
			sic.add(new SelectorItemInfo("plateDate"));
			sic.add(new SelectorItemInfo("vRCRegDate"));
			sic.add(new SelectorItemInfo("traffInvalidDate"));
			sic.add(new SelectorItemInfo("traffInsuNumber"));
			sic.add(new SelectorItemInfo("bridgeFeeDate"));
			sic.add(new SelectorItemInfo("city.id"));
			sic.add(new SelectorItemInfo("city.number"));
			sic.add(new SelectorItemInfo("city.name"));
			sic.add(new SelectorItemInfo("creator.*"));
			sic.add(new SelectorItemInfo("creator.id"));
			sic.add(new SelectorItemInfo("creator.number"));
			sic.add(new SelectorItemInfo("creator.name"));
			sic.add(new SelectorItemInfo("createTime"));
			sic.add(new SelectorItemInfo("lastUpdateUser.*"));
			sic.add(new SelectorItemInfo("lastUpdateUser.id"));
			sic.add(new SelectorItemInfo("lastUpdateUser.number"));
			sic.add(new SelectorItemInfo("lastUpdateUser.name"));
			sic.add(new SelectorItemInfo("lastUpdateTime"));
			sic.add(new SelectorItemInfo("series.*"));
			sic.add(new SelectorItemInfo("series.id"));
			sic.add(new SelectorItemInfo("series.number"));
			sic.add(new SelectorItemInfo("series.name"));
			sic.add(new SelectorItemInfo("vehicleCreateType"));
			sic.add(new SelectorItemInfo("vehicleRemark"));
			sic.add(new SelectorItemInfo("keepingLocation "));
			sic.add(new SelectorItemInfo("offlineDate"));
        return sic;
    }        
    	

    /**
     * output actionPrint_actionPerformed method
     */
    public void actionPrint_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPrint_actionPerformed(e);
    }
    	

    /**
     * output actionPrintPreview_actionPerformed method
     */
    public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPrintPreview_actionPerformed(e);
    }
    	

    /**
     * output actionInstallRecord_actionPerformed method
     */
    public void actionInstallRecord_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionRepairRecord_actionPerformed method
     */
    public void actionRepairRecord_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionInsuranceRecord_actionPerformed method
     */
    public void actionInsuranceRecord_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAgent_actionPerformed method
     */
    public void actionAgent_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionVeiwRecord_actionPerformed method
     */
    public void actionVeiwRecord_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionOwnerChangeButton_actionPerformed method
     */
    public void actionOwnerChangeButton_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAddNewCustomer_actionPerformed method
     */
    public void actionAddNewCustomer_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionRelateBusiness_actionPerformed method
     */
    public void actionRelateBusiness_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAutoSaleOrderView_actionPerformed method
     */
    public void actionAutoSaleOrderView_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAutoPurOrderView_actionPerformed method
     */
    public void actionAutoPurOrderView_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionPurReceiveView_actionPerformed method
     */
    public void actionPurReceiveView_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionSaleIssueView_actionPerformed method
     */
    public void actionSaleIssueView_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionVehicleInsuranceView_actionPerformed method
     */
    public void actionVehicleInsuranceView_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionVehicleHangtagView_actionPerformed method
     */
    public void actionVehicleHangtagView_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionDecorationOrderView_actionPerformed method
     */
    public void actionDecorationOrderView_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionVehicleMortgageView_actionPerformed method
     */
    public void actionVehicleMortgageView_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionGetVehicleMiles_actionPerformed method
     */
    public void actionGetVehicleMiles_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewVehicleAdvice_actionPerformed method
     */
    public void actionViewVehicleAdvice_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAutoVehicleRepairWOItem_actionPerformed method
     */
    public void actionAutoVehicleRepairWOItem_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionPrint(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrint(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrint() {
    	return false;
    }
	public RequestContext prepareActionPrintPreview(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrintPreview(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrintPreview() {
    	return false;
    }
	public RequestContext prepareactionInstallRecord(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionInstallRecord() {
    	return false;
    }
	public RequestContext prepareactionRepairRecord(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionRepairRecord() {
    	return false;
    }
	public RequestContext prepareactionInsuranceRecord(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionInsuranceRecord() {
    	return false;
    }
	public RequestContext prepareactionAgent(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionAgent() {
    	return false;
    }
	public RequestContext prepareactionVeiwRecord(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionVeiwRecord() {
    	return false;
    }
	public RequestContext prepareactionOwnerChangeButton(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionOwnerChangeButton() {
    	return false;
    }
	public RequestContext prepareactionAddNewCustomer(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionAddNewCustomer() {
    	return false;
    }
	public RequestContext prepareActionRelateBusiness(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionRelateBusiness() {
    	return false;
    }
	public RequestContext prepareActionAutoSaleOrderView(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAutoSaleOrderView() {
    	return false;
    }
	public RequestContext prepareActionAutoPurOrderView(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAutoPurOrderView() {
    	return false;
    }
	public RequestContext prepareActionPurReceiveView(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPurReceiveView() {
    	return false;
    }
	public RequestContext prepareActionSaleIssueView(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSaleIssueView() {
    	return false;
    }
	public RequestContext prepareActionVehicleInsuranceView(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionVehicleInsuranceView() {
    	return false;
    }
	public RequestContext prepareActionVehicleHangtagView(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionVehicleHangtagView() {
    	return false;
    }
	public RequestContext prepareActionDecorationOrderView(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionDecorationOrderView() {
    	return false;
    }
	public RequestContext prepareActionVehicleMortgageView(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionVehicleMortgageView() {
    	return false;
    }
	public RequestContext prepareActionGetVehicleMiles(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionGetVehicleMiles() {
    	return false;
    }
	public RequestContext prepareActionViewVehicleAdvice(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionViewVehicleAdvice() {
    	return false;
    }
	public RequestContext prepareActionAutoVehicleRepairWOItem(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAutoVehicleRepairWOItem() {
    	return false;
    }

    /**
     * output actionInstallRecord class
     */     
    protected class actionInstallRecord extends ItemAction {     
    
        public actionInstallRecord()
        {
            this(null);
        }

        public actionInstallRecord(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionInstallRecord.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionInstallRecord.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionInstallRecord.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "actionInstallRecord", "actionInstallRecord_actionPerformed", e);
        }
    }

    /**
     * output actionRepairRecord class
     */     
    protected class actionRepairRecord extends ItemAction {     
    
        public actionRepairRecord()
        {
            this(null);
        }

        public actionRepairRecord(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionRepairRecord.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionRepairRecord.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionRepairRecord.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "actionRepairRecord", "actionRepairRecord_actionPerformed", e);
        }
    }

    /**
     * output actionInsuranceRecord class
     */     
    protected class actionInsuranceRecord extends ItemAction {     
    
        public actionInsuranceRecord()
        {
            this(null);
        }

        public actionInsuranceRecord(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionInsuranceRecord.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionInsuranceRecord.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionInsuranceRecord.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "actionInsuranceRecord", "actionInsuranceRecord_actionPerformed", e);
        }
    }

    /**
     * output actionAgent class
     */     
    protected class actionAgent extends ItemAction {     
    
        public actionAgent()
        {
            this(null);
        }

        public actionAgent(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionAgent.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAgent.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAgent.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "actionAgent", "actionAgent_actionPerformed", e);
        }
    }

    /**
     * output actionVeiwRecord class
     */     
    protected class actionVeiwRecord extends ItemAction {     
    
        public actionVeiwRecord()
        {
            this(null);
        }

        public actionVeiwRecord(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionVeiwRecord.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionVeiwRecord.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionVeiwRecord.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "actionVeiwRecord", "actionVeiwRecord_actionPerformed", e);
        }
    }

    /**
     * output actionOwnerChangeButton class
     */     
    protected class actionOwnerChangeButton extends ItemAction {     
    
        public actionOwnerChangeButton()
        {
            this(null);
        }

        public actionOwnerChangeButton(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionOwnerChangeButton.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionOwnerChangeButton.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionOwnerChangeButton.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "actionOwnerChangeButton", "actionOwnerChangeButton_actionPerformed", e);
        }
    }

    /**
     * output actionAddNewCustomer class
     */     
    protected class actionAddNewCustomer extends ItemAction {     
    
        public actionAddNewCustomer()
        {
            this(null);
        }

        public actionAddNewCustomer(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionAddNewCustomer.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAddNewCustomer.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAddNewCustomer.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "actionAddNewCustomer", "actionAddNewCustomer_actionPerformed", e);
        }
    }

    /**
     * output ActionRelateBusiness class
     */     
    protected class ActionRelateBusiness extends ItemAction {     
    
        public ActionRelateBusiness()
        {
            this(null);
        }

        public ActionRelateBusiness(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionRelateBusiness.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRelateBusiness.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRelateBusiness.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionRelateBusiness", "actionRelateBusiness_actionPerformed", e);
        }
    }

    /**
     * output ActionAutoSaleOrderView class
     */     
    protected class ActionAutoSaleOrderView extends ItemAction {     
    
        public ActionAutoSaleOrderView()
        {
            this(null);
        }

        public ActionAutoSaleOrderView(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAutoSaleOrderView.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAutoSaleOrderView.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAutoSaleOrderView.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionAutoSaleOrderView", "actionAutoSaleOrderView_actionPerformed", e);
        }
    }

    /**
     * output ActionAutoPurOrderView class
     */     
    protected class ActionAutoPurOrderView extends ItemAction {     
    
        public ActionAutoPurOrderView()
        {
            this(null);
        }

        public ActionAutoPurOrderView(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAutoPurOrderView.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAutoPurOrderView.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAutoPurOrderView.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionAutoPurOrderView", "actionAutoPurOrderView_actionPerformed", e);
        }
    }

    /**
     * output ActionPurReceiveView class
     */     
    protected class ActionPurReceiveView extends ItemAction {     
    
        public ActionPurReceiveView()
        {
            this(null);
        }

        public ActionPurReceiveView(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionPurReceiveView.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionPurReceiveView.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionPurReceiveView.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionPurReceiveView", "actionPurReceiveView_actionPerformed", e);
        }
    }

    /**
     * output ActionSaleIssueView class
     */     
    protected class ActionSaleIssueView extends ItemAction {     
    
        public ActionSaleIssueView()
        {
            this(null);
        }

        public ActionSaleIssueView(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionSaleIssueView.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSaleIssueView.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSaleIssueView.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionSaleIssueView", "actionSaleIssueView_actionPerformed", e);
        }
    }

    /**
     * output ActionVehicleInsuranceView class
     */     
    protected class ActionVehicleInsuranceView extends ItemAction {     
    
        public ActionVehicleInsuranceView()
        {
            this(null);
        }

        public ActionVehicleInsuranceView(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionVehicleInsuranceView.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionVehicleInsuranceView.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionVehicleInsuranceView.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionVehicleInsuranceView", "actionVehicleInsuranceView_actionPerformed", e);
        }
    }

    /**
     * output ActionVehicleHangtagView class
     */     
    protected class ActionVehicleHangtagView extends ItemAction {     
    
        public ActionVehicleHangtagView()
        {
            this(null);
        }

        public ActionVehicleHangtagView(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionVehicleHangtagView.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionVehicleHangtagView.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionVehicleHangtagView.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionVehicleHangtagView", "actionVehicleHangtagView_actionPerformed", e);
        }
    }

    /**
     * output ActionDecorationOrderView class
     */     
    protected class ActionDecorationOrderView extends ItemAction {     
    
        public ActionDecorationOrderView()
        {
            this(null);
        }

        public ActionDecorationOrderView(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionDecorationOrderView.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionDecorationOrderView.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionDecorationOrderView.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionDecorationOrderView", "actionDecorationOrderView_actionPerformed", e);
        }
    }

    /**
     * output ActionVehicleMortgageView class
     */     
    protected class ActionVehicleMortgageView extends ItemAction {     
    
        public ActionVehicleMortgageView()
        {
            this(null);
        }

        public ActionVehicleMortgageView(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionVehicleMortgageView.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionVehicleMortgageView.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionVehicleMortgageView.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionVehicleMortgageView", "actionVehicleMortgageView_actionPerformed", e);
        }
    }

    /**
     * output ActionGetVehicleMiles class
     */     
    protected class ActionGetVehicleMiles extends ItemAction {     
    
        public ActionGetVehicleMiles()
        {
            this(null);
        }

        public ActionGetVehicleMiles(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionGetVehicleMiles.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionGetVehicleMiles.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionGetVehicleMiles.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionGetVehicleMiles", "actionGetVehicleMiles_actionPerformed", e);
        }
    }

    /**
     * output ActionViewVehicleAdvice class
     */     
    protected class ActionViewVehicleAdvice extends ItemAction {     
    
        public ActionViewVehicleAdvice()
        {
            this(null);
        }

        public ActionViewVehicleAdvice(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionViewVehicleAdvice.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewVehicleAdvice.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewVehicleAdvice.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionViewVehicleAdvice", "actionViewVehicleAdvice_actionPerformed", e);
        }
    }

    /**
     * output ActionAutoVehicleRepairWOItem class
     */     
    protected class ActionAutoVehicleRepairWOItem extends ItemAction {     
    
        public ActionAutoVehicleRepairWOItem()
        {
            this(null);
        }

        public ActionAutoVehicleRepairWOItem(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAutoVehicleRepairWOItem.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAutoVehicleRepairWOItem.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAutoVehicleRepairWOItem.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractVehicleEditUI.this, "ActionAutoVehicleRepairWOItem", "actionAutoVehicleRepairWOItem_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.auto4s.bdm.pbd.client", "VehicleEditUI");
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.client.VehicleEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.auto4s.bdm.pbd.VehicleFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo objectValue = new com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


        
					protected void beforeStoreFields(ActionEvent arg0) throws Exception {
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(source.getSelectedItem())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"车辆来源"});
		}
		for (int i=0,n=kdtRepairSender.getRowCount();i<n;i++) {
			if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(kdtRepairSender.getCell(i,"name").getValue())) {
				throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"送修人姓名"});
			}
		}
			super.beforeStoreFields(arg0);
		}

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kDVehicleMilesTabel;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("source","2");
vo.put("trustStatus","1");
vo.put("substoreStatus","1");
vo.put("vehicleCreateType","2");
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}