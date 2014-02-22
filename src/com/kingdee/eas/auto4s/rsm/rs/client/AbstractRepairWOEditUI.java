/**
 * output package name
 */
package com.kingdee.eas.auto4s.rsm.rs.client;

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
public abstract class AbstractRepairWOEditUI extends com.kingdee.eas.auto4s.autoframework.core.client.AutoBillBaseEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractRepairWOEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDSplitPane kDSplitPane1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOldID;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRepairBookingID;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contReturnRepair;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel1;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kDTRWOPane;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contInsuranCompany;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contSeries;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contModel;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contVIN;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCustomerDiscountClassify;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contTel;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRepairSender;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCustomer;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contnextMaintainmiles;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNextMaintainDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCompanyNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCustomerRequest;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsWaitForStore;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsWash;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsPriorAssign;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRepairType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contWarrantyType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contConsignation;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contKeyNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOilQty;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contMile;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsStat;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRepairTotalAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contIntendDeliveryTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contComeTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contSA;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contVehicle;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBrand;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDBtnMulAssign;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDBtnWorkTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer3;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kdbSelVip;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDVipItemDisRate;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDVipSparDisRate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer4;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer5;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer6;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chekIsReceive;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsPay;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer7;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDRecentlyComeTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtEngineNum;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtInsuranCompany;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtSeries;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtModel;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtVIN;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCustomerDiscountClassify;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtTel;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtRepairSender;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCustomer;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtnextMaintainmiles;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkNextMaintainDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtCompanyNumber;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane scrollPaneCustomerRequest;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtCustomerRequest;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtRepairType;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtWarrantyType;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtConsignation;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtKeyNumber;
    protected com.kingdee.bos.ctrl.swing.KDComboBox OilQty;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtMile;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtRepairTotalAmount;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkIntendDeliveryTime;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkComeTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtSA;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtVehicle;
    protected com.kingdee.bos.ctrl.swing.KDComboBox Status;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtOrgUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtBrand;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane ScrollPaneDis;
    protected com.kingdee.bos.ctrl.swing.KDTextArea kDTDis;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtVip;
    protected com.kingdee.bos.ctrl.swing.KDComboBox repairWay;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtGroupOrgunit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtSupplier;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtAccountCfg;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPRwoItem;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPRwoSp;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPSpk;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPAct;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPAcc;
    protected com.kingdee.bos.ctrl.swing.KDPanel KDPAmount;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPBreak;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtRWORepairItemEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtRWORepairItemEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtRWOSparepartEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtRWOSparepartEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtRWORepairPkgEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtRWORepairPkgEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtRWOActivityEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtRWOActivityEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtRWOAttachmentItemEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtRWOAttachmentItemEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtRWOTotalAmountEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtRWOTotalAmountEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtRepairBreakEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtRepairBreakEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtOldID;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtRepairBookingID;
    protected com.kingdee.bos.ctrl.swing.KDComboBox ReturnRepair;
    protected javax.swing.JToolBar.Separator separator9;
    protected javax.swing.JToolBar.Separator separator4;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAdd;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnEnterAdd;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnSuggest;
    protected javax.swing.JToolBar.Separator separator5;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnInvalid;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnUninvalid;
    protected javax.swing.JToolBar.Separator separator6;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnBo;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnCancelBo;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnDispatching;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDWorkButton9;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnTimeBooking;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAdjust;
    protected javax.swing.JToolBar.Separator separator8;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnCustomerInfo;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnVehicleInfo;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnRepairHistory;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnCallBack;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnCustomerComplain;
    protected javax.swing.JToolBar.Separator separator10;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnPricePrint;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnCancelAssign;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnUnTimeBooking;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDBtnRefresh;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAddCustomer;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAddVehicle;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDViewVipCard;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDViewVehicleAdvice;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnViewVipPrefer;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemAudit;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemUnAudit;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kDTabbedPane1;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPBizInfo;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPRepairInfo;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPVehicleInfo;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPHideField;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRemark;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcustomInfo;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsaleType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCustomerAccount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcustomerAccountName;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contgaDept;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane scrollPaneRemark;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtRemark;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane scrollPanecustomInfo;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtcustomInfo;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtsaleType;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCustomerAccount;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtcustomerAccountName;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtgaDept;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contdept;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtdept;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPRwoItemSp;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtRWOItemSpEntry;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtRWOItemSpEntry_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer8;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer labRepairItem;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer labMaterial;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbT;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtRepairItem;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtMaterial;
    protected com.kingdee.bos.ctrl.swing.KDCheckBoxMenuItem menuItemIsShowStdItemspEntry;
    protected com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo editData = null;
    protected ActionAdd actionAdd = null;
    protected ActionEnterAdd actionEnterAdd = null;
    protected ActionBo actionBo = null;
    protected ActionCancelBo actionCancelBo = null;
    protected ActionInvalid actionInvalid = null;
    protected ActionUninvalid actionUninvalid = null;
    protected ActionDispatching actionDispatching = null;
    protected ActionTimeBooking actionTimeBooking = null;
    protected ActionAdjust actionAdjust = null;
    protected ActionSuggest actionSuggest = null;
    protected ActionItemIssue actionItemIssue = null;
    protected ActionCustomer actionCustomer = null;
    protected ActionVehicle actionVehicle = null;
    protected ActionHistory actionHistory = null;
    protected ActionCallBack actionCallBack = null;
    protected ActionComplain actionComplain = null;
    protected ActionPrintPrice actionPrintPrice = null;
    protected ActionCancelAssign actionCancelAssign = null;
    protected KDBtnMulAssignAction kDBtnMulAssignAction = null;
    protected KDBtnWorkTimeAction kDBtnWorkTimeAction = null;
    protected ActionUnTimeBooking actionUnTimeBooking = null;
    protected ActionSelectMaterial actionSelectMaterial = null;
    protected ActionRefresh actionRefresh = null;
    protected KdbSelVipAction kdbSelVipAction = null;
    protected kDVipDisRateAction kDVipDisRateAction = null;
    protected ActionAddVehicle actionAddVehicle = null;
    protected ActionAddCustomer actionAddCustomer = null;
    protected ActionViewVipCard actionViewVipCard = null;
    protected ActionViewVehicleAdvice actionViewVehicleAdvice = null;
    protected ActionViewVipPrefer actionViewVipPrefer = null;
    protected ActionIsShowStdItemspEntry actionIsShowStdItemspEntry = null;
    /**
     * output class constructor
     */
    public AbstractRepairWOEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractRepairWOEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSubmit
        String _tempStr = null;
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        actionSubmit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
        actionSubmit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
        actionSubmit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.NAME");
        actionSubmit.putValue(ItemAction.NAME, _tempStr);
        this.actionSubmit.setBindWorkFlow(true);
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrint
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
        //actionAdd
        this.actionAdd = new ActionAdd(this);
        getActionManager().registerAction("actionAdd", actionAdd);
         this.actionAdd.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionEnterAdd
        this.actionEnterAdd = new ActionEnterAdd(this);
        getActionManager().registerAction("actionEnterAdd", actionEnterAdd);
         this.actionEnterAdd.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionBo
        this.actionBo = new ActionBo(this);
        getActionManager().registerAction("actionBo", actionBo);
         this.actionBo.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCancelBo
        this.actionCancelBo = new ActionCancelBo(this);
        getActionManager().registerAction("actionCancelBo", actionCancelBo);
         this.actionCancelBo.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionInvalid
        this.actionInvalid = new ActionInvalid(this);
        getActionManager().registerAction("actionInvalid", actionInvalid);
         this.actionInvalid.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionUninvalid
        this.actionUninvalid = new ActionUninvalid(this);
        getActionManager().registerAction("actionUninvalid", actionUninvalid);
         this.actionUninvalid.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionDispatching
        this.actionDispatching = new ActionDispatching(this);
        getActionManager().registerAction("actionDispatching", actionDispatching);
        this.actionDispatching.setBindWorkFlow(true);
         this.actionDispatching.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionTimeBooking
        this.actionTimeBooking = new ActionTimeBooking(this);
        getActionManager().registerAction("actionTimeBooking", actionTimeBooking);
         this.actionTimeBooking.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAdjust
        this.actionAdjust = new ActionAdjust(this);
        getActionManager().registerAction("actionAdjust", actionAdjust);
         this.actionAdjust.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionSuggest
        this.actionSuggest = new ActionSuggest(this);
        getActionManager().registerAction("actionSuggest", actionSuggest);
         this.actionSuggest.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionItemIssue
        this.actionItemIssue = new ActionItemIssue(this);
        getActionManager().registerAction("actionItemIssue", actionItemIssue);
         this.actionItemIssue.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCustomer
        this.actionCustomer = new ActionCustomer(this);
        getActionManager().registerAction("actionCustomer", actionCustomer);
         this.actionCustomer.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionVehicle
        this.actionVehicle = new ActionVehicle(this);
        getActionManager().registerAction("actionVehicle", actionVehicle);
         this.actionVehicle.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionHistory
        this.actionHistory = new ActionHistory(this);
        getActionManager().registerAction("actionHistory", actionHistory);
         this.actionHistory.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCallBack
        this.actionCallBack = new ActionCallBack(this);
        getActionManager().registerAction("actionCallBack", actionCallBack);
         this.actionCallBack.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionComplain
        this.actionComplain = new ActionComplain(this);
        getActionManager().registerAction("actionComplain", actionComplain);
         this.actionComplain.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionPrintPrice
        this.actionPrintPrice = new ActionPrintPrice(this);
        getActionManager().registerAction("actionPrintPrice", actionPrintPrice);
         this.actionPrintPrice.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCancelAssign
        this.actionCancelAssign = new ActionCancelAssign(this);
        getActionManager().registerAction("actionCancelAssign", actionCancelAssign);
         this.actionCancelAssign.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //kDBtnMulAssignAction
        this.kDBtnMulAssignAction = new KDBtnMulAssignAction(this);
        getActionManager().registerAction("kDBtnMulAssignAction", kDBtnMulAssignAction);
         this.kDBtnMulAssignAction.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //kDBtnWorkTimeAction
        this.kDBtnWorkTimeAction = new KDBtnWorkTimeAction(this);
        getActionManager().registerAction("kDBtnWorkTimeAction", kDBtnWorkTimeAction);
         this.kDBtnWorkTimeAction.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionUnTimeBooking
        this.actionUnTimeBooking = new ActionUnTimeBooking(this);
        getActionManager().registerAction("actionUnTimeBooking", actionUnTimeBooking);
         this.actionUnTimeBooking.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionSelectMaterial
        this.actionSelectMaterial = new ActionSelectMaterial(this);
        getActionManager().registerAction("actionSelectMaterial", actionSelectMaterial);
         this.actionSelectMaterial.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRefresh
        this.actionRefresh = new ActionRefresh(this);
        getActionManager().registerAction("actionRefresh", actionRefresh);
         this.actionRefresh.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //kdbSelVipAction
        this.kdbSelVipAction = new KdbSelVipAction(this);
        getActionManager().registerAction("kdbSelVipAction", kdbSelVipAction);
         this.kdbSelVipAction.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //kDVipDisRateAction
        this.kDVipDisRateAction = new kDVipDisRateAction(this);
        getActionManager().registerAction("kDVipDisRateAction", kDVipDisRateAction);
         this.kDVipDisRateAction.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAddVehicle
        this.actionAddVehicle = new ActionAddVehicle(this);
        getActionManager().registerAction("actionAddVehicle", actionAddVehicle);
         this.actionAddVehicle.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAddCustomer
        this.actionAddCustomer = new ActionAddCustomer(this);
        getActionManager().registerAction("actionAddCustomer", actionAddCustomer);
         this.actionAddCustomer.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewVipCard
        this.actionViewVipCard = new ActionViewVipCard(this);
        getActionManager().registerAction("actionViewVipCard", actionViewVipCard);
         this.actionViewVipCard.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewVehicleAdvice
        this.actionViewVehicleAdvice = new ActionViewVehicleAdvice(this);
        getActionManager().registerAction("actionViewVehicleAdvice", actionViewVehicleAdvice);
         this.actionViewVehicleAdvice.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewVipPrefer
        this.actionViewVipPrefer = new ActionViewVipPrefer(this);
        getActionManager().registerAction("actionViewVipPrefer", actionViewVipPrefer);
         this.actionViewVipPrefer.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionIsShowStdItemspEntry
        this.actionIsShowStdItemspEntry = new ActionIsShowStdItemspEntry(this);
        getActionManager().registerAction("actionIsShowStdItemspEntry", actionIsShowStdItemspEntry);
         this.actionIsShowStdItemspEntry.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.kDSplitPane1 = new com.kingdee.bos.ctrl.swing.KDSplitPane();
        this.contOldID = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contRepairBookingID = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contReturnRepair = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDPanel1 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDTRWOPane = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contInsuranCompany = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contSeries = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contModel = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contVIN = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCustomerDiscountClassify = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contTel = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contRepairSender = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCustomer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contnextMaintainmiles = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNextMaintainDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCompanyNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCustomerRequest = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkIsWaitForStore = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkIsWash = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkIsPriorAssign = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contRepairType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contWarrantyType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contConsignation = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contKeyNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contOilQty = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contMile = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkIsStat = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contRepairTotalAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contIntendDeliveryTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contComeTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contSA = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contVehicle = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBrand = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDescription = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDBtnMulAssign = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDBtnWorkTime = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDLabelContainer3 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kdbSelVip = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDVipItemDisRate = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDVipSparDisRate = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDLabelContainer4 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer5 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer6 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chekIsReceive = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkIsPay = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.kDLabelContainer7 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDRecentlyComeTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtEngineNum = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtInsuranCompany = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtSeries = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtModel = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtVIN = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtCustomerDiscountClassify = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtTel = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtRepairSender = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtCustomer = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtnextMaintainmiles = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.pkNextMaintainDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtCompanyNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.scrollPaneCustomerRequest = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txtCustomerRequest = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.prmtRepairType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtWarrantyType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtConsignation = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtKeyNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.OilQty = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtMile = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtRepairTotalAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.pkIntendDeliveryTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pkComeTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtSA = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtVehicle = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.Status = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtBrand = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.ScrollPaneDis = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.kDTDis = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtVip = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.repairWay = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtGroupOrgunit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtSupplier = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtAccountCfg = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDPRwoItem = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPRwoSp = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPSpk = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPAct = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPAcc = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.KDPAmount = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPBreak = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kdtRWORepairItemEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtRWOSparepartEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtRWORepairPkgEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtRWOActivityEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtRWOAttachmentItemEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtRWOTotalAmountEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtRepairBreakEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.txtOldID = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtRepairBookingID = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.ReturnRepair = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.separator9 = new javax.swing.JToolBar.Separator();
        this.separator4 = new javax.swing.JToolBar.Separator();
        this.btnAdd = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnEnterAdd = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnSuggest = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator5 = new javax.swing.JToolBar.Separator();
        this.btnInvalid = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnUninvalid = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator6 = new javax.swing.JToolBar.Separator();
        this.btnBo = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnCancelBo = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnDispatching = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDWorkButton9 = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnTimeBooking = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAdjust = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator8 = new javax.swing.JToolBar.Separator();
        this.btnCustomerInfo = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnVehicleInfo = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnRepairHistory = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnCallBack = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnCustomerComplain = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator10 = new javax.swing.JToolBar.Separator();
        this.btnPricePrint = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnCancelAssign = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnUnTimeBooking = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDBtnRefresh = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAddCustomer = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAddVehicle = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDViewVipCard = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDViewVehicleAdvice = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnViewVipPrefer = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.menuItemAudit = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemUnAudit = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.kDTabbedPane1 = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.kDPBizInfo = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPRepairInfo = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPVehicleInfo = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPHideField = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.contRemark = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcustomInfo = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsaleType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCustomerAccount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcustomerAccountName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contgaDept = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.scrollPaneRemark = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txtRemark = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.scrollPanecustomInfo = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txtcustomInfo = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.txtsaleType = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtCustomerAccount = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtcustomerAccountName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtgaDept = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.contdept = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtdept = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDPRwoItemSp = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kdtRWOItemSpEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kDLabelContainer8 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.labRepairItem = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.labMaterial = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.cmbT = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtRepairItem = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtMaterial = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.menuItemIsShowStdItemspEntry = new com.kingdee.bos.ctrl.swing.KDCheckBoxMenuItem();
        this.kDSplitPane1.setName("kDSplitPane1");
        this.contOldID.setName("contOldID");
        this.contRepairBookingID.setName("contRepairBookingID");
        this.contReturnRepair.setName("contReturnRepair");
        this.kDPanel1.setName("kDPanel1");
        this.kDTRWOPane.setName("kDTRWOPane");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.contInsuranCompany.setName("contInsuranCompany");
        this.contSeries.setName("contSeries");
        this.contModel.setName("contModel");
        this.contVIN.setName("contVIN");
        this.contCustomerDiscountClassify.setName("contCustomerDiscountClassify");
        this.contTel.setName("contTel");
        this.contRepairSender.setName("contRepairSender");
        this.contCustomer.setName("contCustomer");
        this.contnextMaintainmiles.setName("contnextMaintainmiles");
        this.contNextMaintainDate.setName("contNextMaintainDate");
        this.contCompanyNumber.setName("contCompanyNumber");
        this.contCustomerRequest.setName("contCustomerRequest");
        this.chkIsWaitForStore.setName("chkIsWaitForStore");
        this.chkIsWash.setName("chkIsWash");
        this.chkIsPriorAssign.setName("chkIsPriorAssign");
        this.contRepairType.setName("contRepairType");
        this.contWarrantyType.setName("contWarrantyType");
        this.contConsignation.setName("contConsignation");
        this.contKeyNumber.setName("contKeyNumber");
        this.contOilQty.setName("contOilQty");
        this.contMile.setName("contMile");
        this.chkIsStat.setName("chkIsStat");
        this.contRepairTotalAmount.setName("contRepairTotalAmount");
        this.contIntendDeliveryTime.setName("contIntendDeliveryTime");
        this.contComeTime.setName("contComeTime");
        this.contSA.setName("contSA");
        this.contVehicle.setName("contVehicle");
        this.contStatus.setName("contStatus");
        this.contOrgUnit.setName("contOrgUnit");
        this.contBrand.setName("contBrand");
        this.contDescription.setName("contDescription");
        this.contNumber.setName("contNumber");
        this.kDBtnMulAssign.setName("kDBtnMulAssign");
        this.kDBtnWorkTime.setName("kDBtnWorkTime");
        this.kDLabelContainer3.setName("kDLabelContainer3");
        this.kdbSelVip.setName("kdbSelVip");
        this.kDVipItemDisRate.setName("kDVipItemDisRate");
        this.kDVipSparDisRate.setName("kDVipSparDisRate");
        this.kDLabelContainer4.setName("kDLabelContainer4");
        this.kDLabelContainer5.setName("kDLabelContainer5");
        this.kDLabelContainer6.setName("kDLabelContainer6");
        this.chekIsReceive.setName("chekIsReceive");
        this.chkIsPay.setName("chkIsPay");
        this.kDLabelContainer7.setName("kDLabelContainer7");
        this.kDRecentlyComeTime.setName("kDRecentlyComeTime");
        this.txtEngineNum.setName("txtEngineNum");
        this.prmtInsuranCompany.setName("prmtInsuranCompany");
        this.txtSeries.setName("txtSeries");
        this.txtModel.setName("txtModel");
        this.txtVIN.setName("txtVIN");
        this.prmtCustomerDiscountClassify.setName("prmtCustomerDiscountClassify");
        this.txtTel.setName("txtTel");
        this.txtRepairSender.setName("txtRepairSender");
        this.prmtCustomer.setName("prmtCustomer");
        this.txtnextMaintainmiles.setName("txtnextMaintainmiles");
        this.pkNextMaintainDate.setName("pkNextMaintainDate");
        this.txtCompanyNumber.setName("txtCompanyNumber");
        this.scrollPaneCustomerRequest.setName("scrollPaneCustomerRequest");
        this.txtCustomerRequest.setName("txtCustomerRequest");
        this.prmtRepairType.setName("prmtRepairType");
        this.prmtWarrantyType.setName("prmtWarrantyType");
        this.txtConsignation.setName("txtConsignation");
        this.txtKeyNumber.setName("txtKeyNumber");
        this.OilQty.setName("OilQty");
        this.txtMile.setName("txtMile");
        this.txtRepairTotalAmount.setName("txtRepairTotalAmount");
        this.pkIntendDeliveryTime.setName("pkIntendDeliveryTime");
        this.pkComeTime.setName("pkComeTime");
        this.prmtSA.setName("prmtSA");
        this.prmtVehicle.setName("prmtVehicle");
        this.Status.setName("Status");
        this.prmtOrgUnit.setName("prmtOrgUnit");
        this.prmtBrand.setName("prmtBrand");
        this.ScrollPaneDis.setName("ScrollPaneDis");
        this.kDTDis.setName("kDTDis");
        this.txtNumber.setName("txtNumber");
        this.prmtVip.setName("prmtVip");
        this.repairWay.setName("repairWay");
        this.prmtGroupOrgunit.setName("prmtGroupOrgunit");
        this.prmtSupplier.setName("prmtSupplier");
        this.prmtAccountCfg.setName("prmtAccountCfg");
        this.kDPRwoItem.setName("kDPRwoItem");
        this.kDPRwoSp.setName("kDPRwoSp");
        this.kDPSpk.setName("kDPSpk");
        this.kDPAct.setName("kDPAct");
        this.kDPAcc.setName("kDPAcc");
        this.KDPAmount.setName("KDPAmount");
        this.kDPBreak.setName("kDPBreak");
        this.kdtRWORepairItemEntry.setName("kdtRWORepairItemEntry");
        this.kdtRWOSparepartEntry.setName("kdtRWOSparepartEntry");
        this.kdtRWORepairPkgEntry.setName("kdtRWORepairPkgEntry");
        this.kdtRWOActivityEntry.setName("kdtRWOActivityEntry");
        this.kdtRWOAttachmentItemEntry.setName("kdtRWOAttachmentItemEntry");
        this.kdtRWOTotalAmountEntry.setName("kdtRWOTotalAmountEntry");
        this.kdtRepairBreakEntry.setName("kdtRepairBreakEntry");
        this.txtOldID.setName("txtOldID");
        this.txtRepairBookingID.setName("txtRepairBookingID");
        this.ReturnRepair.setName("ReturnRepair");
        this.separator9.setName("separator9");
        this.separator4.setName("separator4");
        this.btnAdd.setName("btnAdd");
        this.btnEnterAdd.setName("btnEnterAdd");
        this.btnSuggest.setName("btnSuggest");
        this.separator5.setName("separator5");
        this.btnInvalid.setName("btnInvalid");
        this.btnUninvalid.setName("btnUninvalid");
        this.separator6.setName("separator6");
        this.btnBo.setName("btnBo");
        this.btnCancelBo.setName("btnCancelBo");
        this.btnDispatching.setName("btnDispatching");
        this.kDWorkButton9.setName("kDWorkButton9");
        this.btnTimeBooking.setName("btnTimeBooking");
        this.btnAdjust.setName("btnAdjust");
        this.separator8.setName("separator8");
        this.btnCustomerInfo.setName("btnCustomerInfo");
        this.btnVehicleInfo.setName("btnVehicleInfo");
        this.btnRepairHistory.setName("btnRepairHistory");
        this.btnCallBack.setName("btnCallBack");
        this.btnCustomerComplain.setName("btnCustomerComplain");
        this.separator10.setName("separator10");
        this.btnPricePrint.setName("btnPricePrint");
        this.btnCancelAssign.setName("btnCancelAssign");
        this.btnUnTimeBooking.setName("btnUnTimeBooking");
        this.kDBtnRefresh.setName("kDBtnRefresh");
        this.btnAddCustomer.setName("btnAddCustomer");
        this.btnAddVehicle.setName("btnAddVehicle");
        this.kDViewVipCard.setName("kDViewVipCard");
        this.kDViewVehicleAdvice.setName("kDViewVehicleAdvice");
        this.btnViewVipPrefer.setName("btnViewVipPrefer");
        this.menuItemAudit.setName("menuItemAudit");
        this.menuItemUnAudit.setName("menuItemUnAudit");
        this.kDTabbedPane1.setName("kDTabbedPane1");
        this.kDPBizInfo.setName("kDPBizInfo");
        this.kDPRepairInfo.setName("kDPRepairInfo");
        this.kDPVehicleInfo.setName("kDPVehicleInfo");
        this.kDPHideField.setName("kDPHideField");
        this.contRemark.setName("contRemark");
        this.contcustomInfo.setName("contcustomInfo");
        this.contsaleType.setName("contsaleType");
        this.contCustomerAccount.setName("contCustomerAccount");
        this.contcustomerAccountName.setName("contcustomerAccountName");
        this.contgaDept.setName("contgaDept");
        this.scrollPaneRemark.setName("scrollPaneRemark");
        this.txtRemark.setName("txtRemark");
        this.scrollPanecustomInfo.setName("scrollPanecustomInfo");
        this.txtcustomInfo.setName("txtcustomInfo");
        this.txtsaleType.setName("txtsaleType");
        this.prmtCustomerAccount.setName("prmtCustomerAccount");
        this.txtcustomerAccountName.setName("txtcustomerAccountName");
        this.txtgaDept.setName("txtgaDept");
        this.contdept.setName("contdept");
        this.prmtdept.setName("prmtdept");
        this.kDPRwoItemSp.setName("kDPRwoItemSp");
        this.kdtRWOItemSpEntry.setName("kdtRWOItemSpEntry");
        this.kDLabelContainer8.setName("kDLabelContainer8");
        this.labRepairItem.setName("labRepairItem");
        this.labMaterial.setName("labMaterial");
        this.cmbT.setName("cmbT");
        this.prmtRepairItem.setName("prmtRepairItem");
        this.prmtMaterial.setName("prmtMaterial");
        this.menuItemIsShowStdItemspEntry.setName("menuItemIsShowStdItemspEntry");
        // CoreUI
        this.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent e) {
                try {
                    CoreUI_focusLost(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                try {
                    CoreUI_keyPressed(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });		
        this.btnCopy.setEnabled(false);		
        this.btnAttachment.setVisible(false);		
        this.btnAttachment.setEnabled(false);		
        this.btnTraceUp.setVisible(false);		
        this.btnTraceDown.setVisible(false);		
        this.btnCreateTo.setVisible(true);		
        this.btnAddLine.setVisible(false);		
        this.btnAddLine.setEnabled(false);		
        this.btnCopyLine.setVisible(false);		
        this.btnCopyLine.setEnabled(false);		
        this.btnInsertLine.setVisible(false);		
        this.btnInsertLine.setEnabled(false);		
        this.btnRemoveLine.setVisible(false);		
        this.btnAuditResult.setVisible(false);		
        this.btnMultiapprove.setVisible(false);		
        this.btnNextPerson.setVisible(false);		
        this.separator1.setVisible(false);		
        this.menuItemCreateTo.setVisible(true);		
        this.separator3.setVisible(false);		
        this.menuItemTraceUp.setVisible(false);		
        this.menuItemTraceDown.setVisible(false);		
        this.menuTable1.setVisible(false);		
        this.menuItemAddLine.setVisible(false);		
        this.menuItemCopyLine.setVisible(false);		
        this.menuItemInsertLine.setVisible(false);		
        this.menuItemRemoveLine.setVisible(false);		
        this.menuItemViewSubmitProccess.setVisible(false);		
        this.menuItemViewDoProccess.setVisible(false);		
        this.menuItemAuditResult.setVisible(false);
        // kDSplitPane1		
        this.kDSplitPane1.setDividerLocation(240);		
        this.kDSplitPane1.setOneTouchExpandable(true);		
        this.kDSplitPane1.setOrientation(0);
        // contOldID		
        this.contOldID.setBoundLabelText(resHelper.getString("contOldID.boundLabelText"));		
        this.contOldID.setBoundLabelLength(100);		
        this.contOldID.setBoundLabelUnderline(true);		
        this.contOldID.setVisible(false);		
        this.contOldID.setEnabled(false);
        // contRepairBookingID		
        this.contRepairBookingID.setBoundLabelText(resHelper.getString("contRepairBookingID.boundLabelText"));		
        this.contRepairBookingID.setBoundLabelLength(100);		
        this.contRepairBookingID.setBoundLabelUnderline(true);		
        this.contRepairBookingID.setVisible(false);		
        this.contRepairBookingID.setEnabled(false);
        // contReturnRepair		
        this.contReturnRepair.setBoundLabelText(resHelper.getString("contReturnRepair.boundLabelText"));		
        this.contReturnRepair.setBoundLabelLength(100);		
        this.contReturnRepair.setBoundLabelUnderline(true);		
        this.contReturnRepair.setVisible(true);
        // kDPanel1
        // kDTRWOPane
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));		
        this.kDLabelContainer2.setBoundLabelLength(100);		
        this.kDLabelContainer2.setBoundLabelUnderline(true);
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));		
        this.kDLabelContainer1.setBoundLabelLength(100);		
        this.kDLabelContainer1.setBoundLabelUnderline(true);
        // contInsuranCompany		
        this.contInsuranCompany.setBoundLabelText(resHelper.getString("contInsuranCompany.boundLabelText"));		
        this.contInsuranCompany.setBoundLabelLength(100);		
        this.contInsuranCompany.setBoundLabelUnderline(true);		
        this.contInsuranCompany.setVisible(true);
        // contSeries		
        this.contSeries.setBoundLabelText(resHelper.getString("contSeries.boundLabelText"));		
        this.contSeries.setBoundLabelLength(100);		
        this.contSeries.setBoundLabelUnderline(true);		
        this.contSeries.setVisible(true);
        // contModel		
        this.contModel.setBoundLabelText(resHelper.getString("contModel.boundLabelText"));		
        this.contModel.setBoundLabelLength(100);		
        this.contModel.setBoundLabelUnderline(true);		
        this.contModel.setVisible(true);
        // contVIN		
        this.contVIN.setBoundLabelText(resHelper.getString("contVIN.boundLabelText"));		
        this.contVIN.setBoundLabelLength(100);		
        this.contVIN.setBoundLabelUnderline(true);		
        this.contVIN.setVisible(true);
        // contCustomerDiscountClassify		
        this.contCustomerDiscountClassify.setBoundLabelText(resHelper.getString("contCustomerDiscountClassify.boundLabelText"));		
        this.contCustomerDiscountClassify.setBoundLabelLength(100);		
        this.contCustomerDiscountClassify.setBoundLabelUnderline(true);		
        this.contCustomerDiscountClassify.setVisible(true);
        // contTel		
        this.contTel.setBoundLabelText(resHelper.getString("contTel.boundLabelText"));		
        this.contTel.setBoundLabelLength(100);		
        this.contTel.setBoundLabelUnderline(true);		
        this.contTel.setVisible(true);
        // contRepairSender		
        this.contRepairSender.setBoundLabelText(resHelper.getString("contRepairSender.boundLabelText"));		
        this.contRepairSender.setBoundLabelLength(100);		
        this.contRepairSender.setBoundLabelUnderline(true);		
        this.contRepairSender.setVisible(true);
        // contCustomer		
        this.contCustomer.setBoundLabelText(resHelper.getString("contCustomer.boundLabelText"));		
        this.contCustomer.setBoundLabelLength(100);		
        this.contCustomer.setBoundLabelUnderline(true);		
        this.contCustomer.setVisible(true);
        // contnextMaintainmiles		
        this.contnextMaintainmiles.setBoundLabelText(resHelper.getString("contnextMaintainmiles.boundLabelText"));		
        this.contnextMaintainmiles.setBoundLabelLength(100);		
        this.contnextMaintainmiles.setBoundLabelUnderline(true);		
        this.contnextMaintainmiles.setVisible(true);
        // contNextMaintainDate		
        this.contNextMaintainDate.setBoundLabelText(resHelper.getString("contNextMaintainDate.boundLabelText"));		
        this.contNextMaintainDate.setBoundLabelLength(100);		
        this.contNextMaintainDate.setBoundLabelUnderline(true);		
        this.contNextMaintainDate.setVisible(true);
        // contCompanyNumber		
        this.contCompanyNumber.setBoundLabelText(resHelper.getString("contCompanyNumber.boundLabelText"));		
        this.contCompanyNumber.setBoundLabelLength(100);		
        this.contCompanyNumber.setBoundLabelUnderline(true);		
        this.contCompanyNumber.setVisible(true);
        // contCustomerRequest		
        this.contCustomerRequest.setBoundLabelText(resHelper.getString("contCustomerRequest.boundLabelText"));		
        this.contCustomerRequest.setBoundLabelLength(100);		
        this.contCustomerRequest.setBoundLabelUnderline(true);		
        this.contCustomerRequest.setVisible(true);
        // chkIsWaitForStore		
        this.chkIsWaitForStore.setText(resHelper.getString("chkIsWaitForStore.text"));		
        this.chkIsWaitForStore.setHorizontalAlignment(2);
        // chkIsWash		
        this.chkIsWash.setText(resHelper.getString("chkIsWash.text"));		
        this.chkIsWash.setHorizontalAlignment(2);
        // chkIsPriorAssign		
        this.chkIsPriorAssign.setText(resHelper.getString("chkIsPriorAssign.text"));		
        this.chkIsPriorAssign.setHorizontalAlignment(2);
        // contRepairType		
        this.contRepairType.setBoundLabelText(resHelper.getString("contRepairType.boundLabelText"));		
        this.contRepairType.setBoundLabelLength(100);		
        this.contRepairType.setBoundLabelUnderline(true);		
        this.contRepairType.setVisible(true);
        // contWarrantyType		
        this.contWarrantyType.setBoundLabelText(resHelper.getString("contWarrantyType.boundLabelText"));		
        this.contWarrantyType.setBoundLabelLength(100);		
        this.contWarrantyType.setBoundLabelUnderline(true);		
        this.contWarrantyType.setVisible(true);
        // contConsignation		
        this.contConsignation.setBoundLabelText(resHelper.getString("contConsignation.boundLabelText"));		
        this.contConsignation.setBoundLabelLength(100);		
        this.contConsignation.setBoundLabelUnderline(true);		
        this.contConsignation.setVisible(true);
        // contKeyNumber		
        this.contKeyNumber.setBoundLabelText(resHelper.getString("contKeyNumber.boundLabelText"));		
        this.contKeyNumber.setBoundLabelLength(100);		
        this.contKeyNumber.setBoundLabelUnderline(true);		
        this.contKeyNumber.setVisible(true);
        // contOilQty		
        this.contOilQty.setBoundLabelText(resHelper.getString("contOilQty.boundLabelText"));		
        this.contOilQty.setBoundLabelLength(100);		
        this.contOilQty.setBoundLabelUnderline(true);		
        this.contOilQty.setVisible(true);
        // contMile		
        this.contMile.setBoundLabelText(resHelper.getString("contMile.boundLabelText"));		
        this.contMile.setBoundLabelLength(100);		
        this.contMile.setBoundLabelUnderline(true);		
        this.contMile.setVisible(true);
        // chkIsStat		
        this.chkIsStat.setText(resHelper.getString("chkIsStat.text"));		
        this.chkIsStat.setHorizontalAlignment(2);
        // contRepairTotalAmount		
        this.contRepairTotalAmount.setBoundLabelText(resHelper.getString("contRepairTotalAmount.boundLabelText"));		
        this.contRepairTotalAmount.setBoundLabelLength(100);		
        this.contRepairTotalAmount.setBoundLabelUnderline(true);		
        this.contRepairTotalAmount.setVisible(true);
        // contIntendDeliveryTime		
        this.contIntendDeliveryTime.setBoundLabelText(resHelper.getString("contIntendDeliveryTime.boundLabelText"));		
        this.contIntendDeliveryTime.setBoundLabelLength(100);		
        this.contIntendDeliveryTime.setBoundLabelUnderline(true);		
        this.contIntendDeliveryTime.setVisible(true);
        // contComeTime		
        this.contComeTime.setBoundLabelText(resHelper.getString("contComeTime.boundLabelText"));		
        this.contComeTime.setBoundLabelLength(100);		
        this.contComeTime.setBoundLabelUnderline(true);		
        this.contComeTime.setVisible(true);
        // contSA		
        this.contSA.setBoundLabelText(resHelper.getString("contSA.boundLabelText"));		
        this.contSA.setBoundLabelLength(100);		
        this.contSA.setBoundLabelUnderline(true);		
        this.contSA.setVisible(true);
        // contVehicle		
        this.contVehicle.setBoundLabelText(resHelper.getString("contVehicle.boundLabelText"));		
        this.contVehicle.setBoundLabelLength(100);		
        this.contVehicle.setBoundLabelUnderline(true);		
        this.contVehicle.setVisible(true);
        // contStatus		
        this.contStatus.setBoundLabelText(resHelper.getString("contStatus.boundLabelText"));		
        this.contStatus.setBoundLabelLength(100);		
        this.contStatus.setBoundLabelUnderline(true);		
        this.contStatus.setVisible(true);		
        this.contStatus.setEnabled(false);
        // contOrgUnit		
        this.contOrgUnit.setBoundLabelText(resHelper.getString("contOrgUnit.boundLabelText"));		
        this.contOrgUnit.setBoundLabelLength(100);		
        this.contOrgUnit.setBoundLabelUnderline(true);		
        this.contOrgUnit.setVisible(true);
        // contBrand		
        this.contBrand.setBoundLabelText(resHelper.getString("contBrand.boundLabelText"));		
        this.contBrand.setBoundLabelLength(100);		
        this.contBrand.setBoundLabelUnderline(true);		
        this.contBrand.setVisible(true);
        // contDescription		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));		
        this.contDescription.setBoundLabelLength(100);		
        this.contDescription.setBoundLabelUnderline(true);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);
        // kDBtnMulAssign
        this.kDBtnMulAssign.setAction((IItemAction)ActionProxyFactory.getProxy(kDBtnMulAssignAction, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDBtnMulAssign.setText(resHelper.getString("kDBtnMulAssign.text"));
        // kDBtnWorkTime
        this.kDBtnWorkTime.setAction((IItemAction)ActionProxyFactory.getProxy(kDBtnWorkTimeAction, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDBtnWorkTime.setText(resHelper.getString("kDBtnWorkTime.text"));
        // kDLabelContainer3		
        this.kDLabelContainer3.setBoundLabelText(resHelper.getString("kDLabelContainer3.boundLabelText"));		
        this.kDLabelContainer3.setBoundLabelLength(100);		
        this.kDLabelContainer3.setBoundLabelUnderline(true);
        // kdbSelVip
        this.kdbSelVip.setAction((IItemAction)ActionProxyFactory.getProxy(kdbSelVipAction, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kdbSelVip.setText(resHelper.getString("kdbSelVip.text"));
        // kDVipItemDisRate
        this.kDVipItemDisRate.setAction((IItemAction)ActionProxyFactory.getProxy(kDVipDisRateAction, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDVipItemDisRate.setText(resHelper.getString("kDVipItemDisRate.text"));
        // kDVipSparDisRate
        this.kDVipSparDisRate.setAction((IItemAction)ActionProxyFactory.getProxy(kDVipDisRateAction, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDVipSparDisRate.setText(resHelper.getString("kDVipSparDisRate.text"));
        // kDLabelContainer4		
        this.kDLabelContainer4.setBoundLabelText(resHelper.getString("kDLabelContainer4.boundLabelText"));		
        this.kDLabelContainer4.setBoundLabelLength(100);		
        this.kDLabelContainer4.setBoundLabelUnderline(true);
        // kDLabelContainer5		
        this.kDLabelContainer5.setBoundLabelText(resHelper.getString("kDLabelContainer5.boundLabelText"));		
        this.kDLabelContainer5.setBoundLabelLength(100);		
        this.kDLabelContainer5.setBoundLabelUnderline(true);
        // kDLabelContainer6		
        this.kDLabelContainer6.setBoundLabelText(resHelper.getString("kDLabelContainer6.boundLabelText"));		
        this.kDLabelContainer6.setBoundLabelLength(100);		
        this.kDLabelContainer6.setBoundLabelUnderline(true);
        // chekIsReceive		
        this.chekIsReceive.setText(resHelper.getString("chekIsReceive.text"));		
        this.chekIsReceive.setVisible(false);
        // chkIsPay		
        this.chkIsPay.setText(resHelper.getString("chkIsPay.text"));		
        this.chkIsPay.setVisible(false);
        this.chkIsPay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    chkIsPay_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDLabelContainer7		
        this.kDLabelContainer7.setBoundLabelText(resHelper.getString("kDLabelContainer7.boundLabelText"));		
        this.kDLabelContainer7.setBoundLabelLength(100);		
        this.kDLabelContainer7.setBoundLabelUnderline(true);
        // kDRecentlyComeTime		
        this.kDRecentlyComeTime.setEnabled(false);
        // txtEngineNum		
        this.txtEngineNum.setEnabled(false);		
        this.txtEngineNum.setMaxLength(44);		
        this.txtEngineNum.setHorizontalAlignment(2);
        // prmtInsuranCompany		
        this.prmtInsuranCompany.setQueryInfo("com.kingdee.eas.auto4s.bdm.vam.app.InsuranceCompanyQuery");		
        this.prmtInsuranCompany.setEditable(true);		
        this.prmtInsuranCompany.setDisplayFormat("$name$");		
        this.prmtInsuranCompany.setEditFormat("$number$");		
        this.prmtInsuranCompany.setCommitFormat("$number$");		
        this.prmtInsuranCompany.setRequired(false);
        this.prmtInsuranCompany.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtInsuranCompany_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtSeries		
        this.txtSeries.setHorizontalAlignment(2);		
        this.txtSeries.setMaxLength(44);		
        this.txtSeries.setRequired(false);
        // txtModel		
        this.txtModel.setHorizontalAlignment(2);		
        this.txtModel.setMaxLength(44);		
        this.txtModel.setRequired(false);
        // txtVIN		
        this.txtVIN.setVisible(true);		
        this.txtVIN.setHorizontalAlignment(2);		
        this.txtVIN.setMaxLength(80);		
        this.txtVIN.setRequired(false);
        this.txtVIN.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent e) {
                try {
                    txtVIN_focusLost(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        this.txtVIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                try {
                    txtVIN_keyPressed(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        // prmtCustomerDiscountClassify		
        this.prmtCustomerDiscountClassify.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.CSSPGroupExportQuery");		
        this.prmtCustomerDiscountClassify.setEditable(true);		
        this.prmtCustomerDiscountClassify.setDisplayFormat("$name$");		
        this.prmtCustomerDiscountClassify.setEditFormat("$number$");		
        this.prmtCustomerDiscountClassify.setCommitFormat("$number$");		
        this.prmtCustomerDiscountClassify.setRequired(false);
        // txtTel		
        this.txtTel.setHorizontalAlignment(2);		
        this.txtTel.setMaxLength(255);		
        this.txtTel.setRequired(true);
        // txtRepairSender		
        this.txtRepairSender.setHorizontalAlignment(2);		
        this.txtRepairSender.setMaxLength(255);		
        this.txtRepairSender.setRequired(true);
        // prmtCustomer		
        this.prmtCustomer.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.CustomerQuery");		
        this.prmtCustomer.setEditable(true);		
        this.prmtCustomer.setDisplayFormat("$name$");		
        this.prmtCustomer.setEditFormat("$number$");		
        this.prmtCustomer.setCommitFormat("$number$");		
        this.prmtCustomer.setRequired(false);
        prmtCustomer.addDataChangeListener(new DataChangeListener() {
		public void dataChanged(DataChangeEvent e) {
			try {
				prmtCustomer_Changed();
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        // txtnextMaintainmiles		
        this.txtnextMaintainmiles.setEnabled(false);
        // pkNextMaintainDate		
        this.pkNextMaintainDate.setRequired(false);		
        this.pkNextMaintainDate.setEnabled(false);
        // txtCompanyNumber		
        this.txtCompanyNumber.setHorizontalAlignment(2);		
        this.txtCompanyNumber.setMaxLength(80);		
        this.txtCompanyNumber.setRequired(false);		
        this.txtCompanyNumber.setEnabled(false);
        // scrollPaneCustomerRequest
        // txtCustomerRequest		
        this.txtCustomerRequest.setRequired(false);		
        this.txtCustomerRequest.setMaxLength(255);
        // prmtRepairType		
        this.prmtRepairType.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairTypeQuery");		
        this.prmtRepairType.setEditable(true);		
        this.prmtRepairType.setDisplayFormat("$name$");		
        this.prmtRepairType.setEditFormat("$number$");		
        this.prmtRepairType.setCommitFormat("$number$");		
        this.prmtRepairType.setRequired(true);
        // prmtWarrantyType		
        this.prmtWarrantyType.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.WarrantyTypeQuery");		
        this.prmtWarrantyType.setEditable(true);		
        this.prmtWarrantyType.setDisplayFormat("$name$");		
        this.prmtWarrantyType.setEditFormat("$number$");		
        this.prmtWarrantyType.setCommitFormat("$number$");		
        this.prmtWarrantyType.setRequired(false);
        // txtConsignation
        this.txtConsignation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    txtConsignation_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // txtKeyNumber
        // OilQty		
        this.OilQty.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.OilQuantityEnum").toArray());		
        this.OilQty.setRequired(false);
        // txtMile		
        this.txtMile.setHorizontalAlignment(2);		
        this.txtMile.setDataType(1);		
        this.txtMile.setSupportedEmpty(true);		
        this.txtMile.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtMile.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtMile.setPrecision(10);		
        this.txtMile.setRequired(true);
        this.txtMile.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtMile_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtRepairTotalAmount		
        this.txtRepairTotalAmount.setHorizontalAlignment(2);		
        this.txtRepairTotalAmount.setDataType(1);		
        this.txtRepairTotalAmount.setSupportedEmpty(true);		
        this.txtRepairTotalAmount.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtRepairTotalAmount.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtRepairTotalAmount.setPrecision(10);		
        this.txtRepairTotalAmount.setRequired(false);
        // pkIntendDeliveryTime		
        this.pkIntendDeliveryTime.setRequired(true);		
        this.pkIntendDeliveryTime.setTimeEnabled(true);
        // pkComeTime		
        this.pkComeTime.setRequired(true);		
        this.pkComeTime.setTimeEnabled(true);
        this.pkComeTime.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    pkComeTime_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtSA		
        this.prmtSA.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");		
        this.prmtSA.setEditable(true);		
        this.prmtSA.setDisplayFormat("$name$");		
        this.prmtSA.setEditFormat("$number$");		
        this.prmtSA.setCommitFormat("$number$");		
        this.prmtSA.setRequired(true);
        // prmtVehicle		
        this.prmtVehicle.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.VehicleQuery");		
        this.prmtVehicle.setEditable(true);		
        this.prmtVehicle.setDisplayFormat("$plateNum$");		
        this.prmtVehicle.setEditFormat("$number$");		
        this.prmtVehicle.setCommitFormat("$number$");		
        this.prmtVehicle.setRequired(false);
        prmtVehicle.addDataChangeListener(new DataChangeListener() {
		public void dataChanged(DataChangeEvent e) {
			try {
				prmtVehicle_Changed();
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        this.prmtVehicle.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtVehicle_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.prmtVehicle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                try {
                    prmtVehicle_keyPressed(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        // Status		
        this.Status.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum").toArray());		
        this.Status.setRequired(false);
        // prmtOrgUnit		
        this.prmtOrgUnit.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.ServiceOrgUnitQuery");		
        this.prmtOrgUnit.setEditable(true);		
        this.prmtOrgUnit.setDisplayFormat("$name$");		
        this.prmtOrgUnit.setEditFormat("$number$");		
        this.prmtOrgUnit.setCommitFormat("$number$");		
        this.prmtOrgUnit.setRequired(true);
        		setOrgF7(prmtOrgUnit,com.kingdee.eas.basedata.org.OrgType.getEnum("Admin"));
					
        // prmtBrand		
        this.prmtBrand.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.BrandQuery");		
        this.prmtBrand.setEditable(true);		
        this.prmtBrand.setDisplayFormat("$name$");		
        this.prmtBrand.setEditFormat("$number$");		
        this.prmtBrand.setCommitFormat("$number$");		
        this.prmtBrand.setRequired(false);		
        this.prmtBrand.setEnabled(false);
        this.prmtBrand.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtBrand_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // ScrollPaneDis
        // kDTDis		
        this.kDTDis.setRequired(false);		
        this.kDTDis.setMaxLength(255);
        // txtNumber		
        this.txtNumber.setMaxLength(80);		
        this.txtNumber.setRequired(true);
        // prmtVip		
        this.prmtVip.setEnabled(false);
        this.prmtVip.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtVip_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // repairWay		
        this.repairWay.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum").toArray());
        this.repairWay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    repairWay_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtGroupOrgunit		
        this.prmtGroupOrgunit.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.ServiceOrgUnitQuery");		
        this.prmtGroupOrgunit.setCommitFormat("$number$");		
        this.prmtGroupOrgunit.setEditFormat("$number$");		
        this.prmtGroupOrgunit.setDisplayFormat("$name$");
        // prmtSupplier		
        this.prmtSupplier.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.PSupplierQuery");		
        this.prmtSupplier.setCommitFormat("$number$");		
        this.prmtSupplier.setEditFormat("$number$");		
        this.prmtSupplier.setDisplayFormat("$name$");
        // prmtAccountCfg		
        this.prmtAccountCfg.setDisplayFormat("$name$");		
        this.prmtAccountCfg.setEditFormat("$number$");		
        this.prmtAccountCfg.setCommitFormat("$number$");		
        this.prmtAccountCfg.setEnabled(false);
        this.prmtAccountCfg.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtAccountCfg_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDPRwoItem
        this.kDPRwoItem.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent e) {
                try {
                    kDPRwoItem_hierarchyChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDPRwoSp
        this.kDPRwoSp.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent e) {
                try {
                    kDPRwoSp_hierarchyChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDPSpk
        this.kDPSpk.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent e) {
                try {
                    kDPSpk_hierarchyChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDPAct
        this.kDPAct.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent e) {
                try {
                    kDPAct_hierarchyChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDPAcc
        this.kDPAcc.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent e) {
                try {
                    kDPAcc_hierarchyChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // KDPAmount
        this.KDPAmount.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent e) {
                try {
                    KDPAmount_hierarchyChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDPBreak		
        this.kDPBreak.setEnabled(false);		
        this.kDPBreak.setVisible(false);
        // kdtRWORepairItemEntry
		String kdtRWORepairItemEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol7\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol8\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol9\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol10\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol11\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol12\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol13\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol14\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol15\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol16\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol17\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol18\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol20\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol21\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol25\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol27\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol28\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol29\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol30\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol31\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol32\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol33\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol34\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol35\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol36\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol37\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol38\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol39\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\" t:configured=\"true\"><t:ColumnGroup><t:Column t:key=\"ID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:styleID=\"sCol0\" /><t:Column t:key=\"RepairItem\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"1\" /><t:Column t:key=\"RepairItemName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"ItemRemark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" /><t:Column t:key=\"PaymentClassify\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"4\" /><t:Column t:key=\"SettleObject\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"5\" /><t:Column t:key=\"RepairClassify\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"6\" /><t:Column t:key=\"RepairPkg\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /><t:Column t:key=\"ServiceActivity\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" t:styleID=\"sCol8\" /><t:Column t:key=\"StdWorkTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" t:styleID=\"sCol9\" /><t:Column t:key=\"AssignWorkTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" t:styleID=\"sCol10\" /><t:Column t:key=\"WorkTimePrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" t:styleID=\"sCol11\" /><t:Column t:key=\"WorkTimeAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" t:styleID=\"sCol12\" /><t:Column t:key=\"DiscountRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" t:styleID=\"sCol13\" /><t:Column t:key=\"DiscountAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" t:styleID=\"sCol14\" /><t:Column t:key=\"ARAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" t:styleID=\"sCol15\" /><t:Column t:key=\"ActualAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" t:styleID=\"sCol16\" /><t:Column t:key=\"IsAppend\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"17\" t:styleID=\"sCol17\" /><t:Column t:key=\"IsReturnRepair\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" t:styleID=\"sCol18\" /><t:Column t:key=\"ReworkReason\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"19\" /><t:Column t:key=\"IsDelete\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"20\" t:styleID=\"sCol20\" /><t:Column t:key=\"ItemStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"21\" t:styleID=\"sCol21\" /><t:Column t:key=\"IsMulAssign\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"22\" /><t:Column t:key=\"WorkGroup\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"23\" /><t:Column t:key=\"Person\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"24\" /><t:Column t:key=\"repairGroupEntryId\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"25\" t:styleID=\"sCol25\" /><t:Column t:key=\"WorkStation\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"26\" /><t:Column t:key=\"ActualWorkTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"27\" t:styleID=\"sCol27\" /><t:Column t:key=\"WagePrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"28\" t:styleID=\"sCol28\" /><t:Column t:key=\"WorkTimeCost\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"29\" t:styleID=\"sCol29\" /><t:Column t:key=\"IsTimeEdit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"30\" t:styleID=\"sCol30\" /><t:Column t:key=\"OldDiscountRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"31\" t:styleID=\"sCol31\" /><t:Column t:key=\"cardRepServiceID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"32\" t:styleID=\"sCol32\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"33\" t:styleID=\"sCol33\" /><t:Column t:key=\"WorkTimeStdPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol34\" /><t:Column t:key=\"wipLineNo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"35\" t:configured=\"true\" t:styleID=\"sCol35\" /><t:Column t:key=\"wipFactLineNo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"36\" t:configured=\"true\" t:styleID=\"sCol36\" /><t:Column t:key=\"itemSpEntryId\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"37\" t:configured=\"true\" t:styleID=\"sCol37\" /><t:Column t:key=\"wbprice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"38\" t:configured=\"true\" t:styleID=\"sCol38\" /><t:Column t:key=\"isCreateTo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"39\" t:configured=\"true\" t:styleID=\"sCol39\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{ID}</t:Cell><t:Cell>$Resource{RepairItem}</t:Cell><t:Cell>$Resource{RepairItemName}</t:Cell><t:Cell>$Resource{ItemRemark}</t:Cell><t:Cell>$Resource{PaymentClassify}</t:Cell><t:Cell>$Resource{SettleObject}</t:Cell><t:Cell>$Resource{RepairClassify}</t:Cell><t:Cell>$Resource{RepairPkg}</t:Cell><t:Cell>$Resource{ServiceActivity}</t:Cell><t:Cell>$Resource{StdWorkTime}</t:Cell><t:Cell>$Resource{AssignWorkTime}</t:Cell><t:Cell>$Resource{WorkTimePrice}</t:Cell><t:Cell>$Resource{WorkTimeAmount}</t:Cell><t:Cell>$Resource{DiscountRate}</t:Cell><t:Cell>$Resource{DiscountAmount}</t:Cell><t:Cell>$Resource{ARAmount}</t:Cell><t:Cell>$Resource{ActualAmount}</t:Cell><t:Cell>$Resource{IsAppend}</t:Cell><t:Cell>$Resource{IsReturnRepair}</t:Cell><t:Cell>$Resource{ReworkReason}</t:Cell><t:Cell>$Resource{IsDelete}</t:Cell><t:Cell>$Resource{ItemStatus}</t:Cell><t:Cell>$Resource{IsMulAssign}</t:Cell><t:Cell>$Resource{WorkGroup}</t:Cell><t:Cell>$Resource{Person}</t:Cell><t:Cell>$Resource{repairGroupEntryId}</t:Cell><t:Cell>$Resource{WorkStation}</t:Cell><t:Cell>$Resource{ActualWorkTime}</t:Cell><t:Cell>$Resource{WagePrice}</t:Cell><t:Cell>$Resource{WorkTimeCost}</t:Cell><t:Cell>$Resource{IsTimeEdit}</t:Cell><t:Cell>$Resource{OldDiscountRate}</t:Cell><t:Cell>$Resource{cardRepServiceID}</t:Cell><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{WorkTimeStdPrice}</t:Cell><t:Cell>$Resource{wipLineNo}</t:Cell><t:Cell>$Resource{wipFactLineNo}</t:Cell><t:Cell>$Resource{itemSpEntryId}</t:Cell><t:Cell>$Resource{wbprice}</t:Cell><t:Cell>$Resource{isCreateTo}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtRWORepairItemEntry.setFormatXml(resHelper.translateString("kdtRWORepairItemEntry",kdtRWORepairItemEntryStrXML));
        kdtRWORepairItemEntry.addKDTEditListener(new KDTEditAdapter() {
		public void editStopped(KDTEditEvent e) {
			try {
				kdtRWORepairItemEntry_Changed(e.getRowIndex(),e.getColIndex());
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        this.kdtRWORepairItemEntry.addKDTMouseListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTMouseListener() {
            public void tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) {
                try {
                    kdtRWORepairItemEntry_tableClicked(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.kdtRWORepairItemEntry.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStarting(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtRWORepairItemEntry_editStarting(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtRWORepairItemEntry.putBindContents("editData",new String[] {"id","RepairItem","RepairItem.name","ItemRemark","PaymentClassify","SettleObject","RepairClassify","RepairPkg","ServiceActivity","StdWorkTime","AssignWorkTime","WorkTimePrice","WorkTimeAmount","DiscountRate","DiscountAmount","ARAmount","ActualAmount","IsAppend","IsReturnRepair","ReworkReason","IsDelete","ItemStatus","IsMulTiAssign","WorkGroup","Person","","WorkStation","ActualWorkTime","WagePrice","WorkTimeCost","IsTimeEdit","OldDiscountRate","FCardRepServiceID","seq","WorkTimeStdPrice","wipLineNo","wipFactLineNo","itemSpEntryId","wbprice","isCreateTo"});


        this.kdtRWORepairItemEntry.checkParsed();
        final KDBizPromptBox kdtRWORepairItemEntry_RepairItem_PromptBox = new KDBizPromptBox();
        kdtRWORepairItemEntry_RepairItem_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairItemQuery");
        kdtRWORepairItemEntry_RepairItem_PromptBox.setVisible(true);
        kdtRWORepairItemEntry_RepairItem_PromptBox.setEditable(true);
        kdtRWORepairItemEntry_RepairItem_PromptBox.setDisplayFormat("$number$");
        kdtRWORepairItemEntry_RepairItem_PromptBox.setEditFormat("$number$");
        kdtRWORepairItemEntry_RepairItem_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWORepairItemEntry_RepairItem_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_RepairItem_PromptBox);
        this.kdtRWORepairItemEntry.getColumn("RepairItem").setEditor(kdtRWORepairItemEntry_RepairItem_CellEditor);
        ObjectValueRender kdtRWORepairItemEntry_RepairItem_OVR = new ObjectValueRender();
        kdtRWORepairItemEntry_RepairItem_OVR.setFormat(new BizDataFormat("$number$"));
        this.kdtRWORepairItemEntry.getColumn("RepairItem").setRenderer(kdtRWORepairItemEntry_RepairItem_OVR);
        			kdtRWORepairItemEntry_RepairItem_PromptBox.addSelectorListener(new SelectorListener() {
			com.kingdee.eas.auto4s.bdm.rsm.client.RepairItemListUI kdtRWORepairItemEntry_RepairItem_PromptBox_F7ListUI = null;
			public void willShow(SelectorEvent e) {
				if (kdtRWORepairItemEntry_RepairItem_PromptBox_F7ListUI == null) {
					try {
						kdtRWORepairItemEntry_RepairItem_PromptBox_F7ListUI = new com.kingdee.eas.auto4s.bdm.rsm.client.RepairItemListUI();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					HashMap ctx = new HashMap();
					ctx.put("bizUIOwner",javax.swing.SwingUtilities.getWindowAncestor(kdtRWORepairItemEntry_RepairItem_PromptBox_F7ListUI));
					kdtRWORepairItemEntry_RepairItem_PromptBox_F7ListUI.setF7Use(true,ctx);
					kdtRWORepairItemEntry_RepairItem_PromptBox.setSelector(kdtRWORepairItemEntry_RepairItem_PromptBox_F7ListUI);
				}
			}
		});
					
        KDTextArea kdtRWORepairItemEntry_ItemRemark_TextArea = new KDTextArea();
        kdtRWORepairItemEntry_ItemRemark_TextArea.setName("kdtRWORepairItemEntry_ItemRemark_TextArea");
        kdtRWORepairItemEntry_ItemRemark_TextArea.setMaxLength(255);
        KDTDefaultCellEditor kdtRWORepairItemEntry_ItemRemark_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_ItemRemark_TextArea);
        this.kdtRWORepairItemEntry.getColumn("ItemRemark").setEditor(kdtRWORepairItemEntry_ItemRemark_CellEditor);
        final KDBizPromptBox kdtRWORepairItemEntry_PaymentClassify_PromptBox = new KDBizPromptBox();
        kdtRWORepairItemEntry_PaymentClassify_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.PaymentClassifyQuery");
        kdtRWORepairItemEntry_PaymentClassify_PromptBox.setVisible(true);
        kdtRWORepairItemEntry_PaymentClassify_PromptBox.setEditable(true);
        kdtRWORepairItemEntry_PaymentClassify_PromptBox.setDisplayFormat("$number$");
        kdtRWORepairItemEntry_PaymentClassify_PromptBox.setEditFormat("$number$");
        kdtRWORepairItemEntry_PaymentClassify_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWORepairItemEntry_PaymentClassify_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_PaymentClassify_PromptBox);
        this.kdtRWORepairItemEntry.getColumn("PaymentClassify").setEditor(kdtRWORepairItemEntry_PaymentClassify_CellEditor);
        ObjectValueRender kdtRWORepairItemEntry_PaymentClassify_OVR = new ObjectValueRender();
        kdtRWORepairItemEntry_PaymentClassify_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWORepairItemEntry.getColumn("PaymentClassify").setRenderer(kdtRWORepairItemEntry_PaymentClassify_OVR);
        KDComboBox kdtRWORepairItemEntry_SettleObject_ComboBox = new KDComboBox();
        kdtRWORepairItemEntry_SettleObject_ComboBox.setName("kdtRWORepairItemEntry_SettleObject_ComboBox");
        kdtRWORepairItemEntry_SettleObject_ComboBox.setVisible(true);
        kdtRWORepairItemEntry_SettleObject_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum").toArray());
        KDTDefaultCellEditor kdtRWORepairItemEntry_SettleObject_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_SettleObject_ComboBox);
        this.kdtRWORepairItemEntry.getColumn("SettleObject").setEditor(kdtRWORepairItemEntry_SettleObject_CellEditor);
        final KDBizPromptBox kdtRWORepairItemEntry_RepairClassify_PromptBox = new KDBizPromptBox();
        kdtRWORepairItemEntry_RepairClassify_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairClassifyQuery");
        kdtRWORepairItemEntry_RepairClassify_PromptBox.setVisible(true);
        kdtRWORepairItemEntry_RepairClassify_PromptBox.setEditable(true);
        kdtRWORepairItemEntry_RepairClassify_PromptBox.setDisplayFormat("$number$");
        kdtRWORepairItemEntry_RepairClassify_PromptBox.setEditFormat("$number$");
        kdtRWORepairItemEntry_RepairClassify_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWORepairItemEntry_RepairClassify_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_RepairClassify_PromptBox);
        this.kdtRWORepairItemEntry.getColumn("RepairClassify").setEditor(kdtRWORepairItemEntry_RepairClassify_CellEditor);
        ObjectValueRender kdtRWORepairItemEntry_RepairClassify_OVR = new ObjectValueRender();
        kdtRWORepairItemEntry_RepairClassify_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWORepairItemEntry.getColumn("RepairClassify").setRenderer(kdtRWORepairItemEntry_RepairClassify_OVR);
        final KDBizPromptBox kdtRWORepairItemEntry_RepairPkg_PromptBox = new KDBizPromptBox();
        kdtRWORepairItemEntry_RepairPkg_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairPkgQuery");
        kdtRWORepairItemEntry_RepairPkg_PromptBox.setVisible(true);
        kdtRWORepairItemEntry_RepairPkg_PromptBox.setEditable(true);
        kdtRWORepairItemEntry_RepairPkg_PromptBox.setDisplayFormat("$number$");
        kdtRWORepairItemEntry_RepairPkg_PromptBox.setEditFormat("$number$");
        kdtRWORepairItemEntry_RepairPkg_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWORepairItemEntry_RepairPkg_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_RepairPkg_PromptBox);
        this.kdtRWORepairItemEntry.getColumn("RepairPkg").setEditor(kdtRWORepairItemEntry_RepairPkg_CellEditor);
        ObjectValueRender kdtRWORepairItemEntry_RepairPkg_OVR = new ObjectValueRender();
        kdtRWORepairItemEntry_RepairPkg_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWORepairItemEntry.getColumn("RepairPkg").setRenderer(kdtRWORepairItemEntry_RepairPkg_OVR);
        final KDBizPromptBox kdtRWORepairItemEntry_ServiceActivity_PromptBox = new KDBizPromptBox();
        kdtRWORepairItemEntry_ServiceActivity_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.rsm.rs.app.ServiceActivityQuery");
        kdtRWORepairItemEntry_ServiceActivity_PromptBox.setVisible(true);
        kdtRWORepairItemEntry_ServiceActivity_PromptBox.setEditable(true);
        kdtRWORepairItemEntry_ServiceActivity_PromptBox.setDisplayFormat("$number$");
        kdtRWORepairItemEntry_ServiceActivity_PromptBox.setEditFormat("$number$");
        kdtRWORepairItemEntry_ServiceActivity_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWORepairItemEntry_ServiceActivity_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_ServiceActivity_PromptBox);
        this.kdtRWORepairItemEntry.getColumn("ServiceActivity").setEditor(kdtRWORepairItemEntry_ServiceActivity_CellEditor);
        ObjectValueRender kdtRWORepairItemEntry_ServiceActivity_OVR = new ObjectValueRender();
        kdtRWORepairItemEntry_ServiceActivity_OVR.setFormat(new BizDataFormat("$Name_l2$"));
        this.kdtRWORepairItemEntry.getColumn("ServiceActivity").setRenderer(kdtRWORepairItemEntry_ServiceActivity_OVR);
        			EntityViewInfo evikdtRWORepairItemEntry_ServiceActivity_PromptBox = new EntityViewInfo ();
		evikdtRWORepairItemEntry_ServiceActivity_PromptBox.setFilter(com.kingdee.eas.framework.FrameWorkUtils.getF7FilterInfoByAuthorizedOrg(com.kingdee.eas.basedata.org.OrgType.getEnum("Admin"),"OrgUnit.id"));
		kdtRWORepairItemEntry_ServiceActivity_PromptBox.setEntityViewInfo(evikdtRWORepairItemEntry_ServiceActivity_PromptBox);
					
        KDFormattedTextField kdtRWORepairItemEntry_StdWorkTime_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_StdWorkTime_TextField.setName("kdtRWORepairItemEntry_StdWorkTime_TextField");
        kdtRWORepairItemEntry_StdWorkTime_TextField.setVisible(true);
        kdtRWORepairItemEntry_StdWorkTime_TextField.setEditable(true);
        kdtRWORepairItemEntry_StdWorkTime_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_StdWorkTime_TextField.setDataType(1);
        	kdtRWORepairItemEntry_StdWorkTime_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_StdWorkTime_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_StdWorkTime_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_StdWorkTime_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_StdWorkTime_TextField);
        this.kdtRWORepairItemEntry.getColumn("StdWorkTime").setEditor(kdtRWORepairItemEntry_StdWorkTime_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_AssignWorkTime_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_AssignWorkTime_TextField.setName("kdtRWORepairItemEntry_AssignWorkTime_TextField");
        kdtRWORepairItemEntry_AssignWorkTime_TextField.setVisible(true);
        kdtRWORepairItemEntry_AssignWorkTime_TextField.setEditable(true);
        kdtRWORepairItemEntry_AssignWorkTime_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_AssignWorkTime_TextField.setDataType(1);
        	kdtRWORepairItemEntry_AssignWorkTime_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_AssignWorkTime_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_AssignWorkTime_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_AssignWorkTime_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_AssignWorkTime_TextField);
        this.kdtRWORepairItemEntry.getColumn("AssignWorkTime").setEditor(kdtRWORepairItemEntry_AssignWorkTime_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_WorkTimePrice_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_WorkTimePrice_TextField.setName("kdtRWORepairItemEntry_WorkTimePrice_TextField");
        kdtRWORepairItemEntry_WorkTimePrice_TextField.setVisible(true);
        kdtRWORepairItemEntry_WorkTimePrice_TextField.setEditable(true);
        kdtRWORepairItemEntry_WorkTimePrice_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_WorkTimePrice_TextField.setDataType(1);
        	kdtRWORepairItemEntry_WorkTimePrice_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_WorkTimePrice_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_WorkTimePrice_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_WorkTimePrice_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_WorkTimePrice_TextField);
        this.kdtRWORepairItemEntry.getColumn("WorkTimePrice").setEditor(kdtRWORepairItemEntry_WorkTimePrice_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_WorkTimeAmount_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_WorkTimeAmount_TextField.setName("kdtRWORepairItemEntry_WorkTimeAmount_TextField");
        kdtRWORepairItemEntry_WorkTimeAmount_TextField.setVisible(true);
        kdtRWORepairItemEntry_WorkTimeAmount_TextField.setEditable(true);
        kdtRWORepairItemEntry_WorkTimeAmount_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_WorkTimeAmount_TextField.setDataType(1);
        	kdtRWORepairItemEntry_WorkTimeAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_WorkTimeAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_WorkTimeAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_WorkTimeAmount_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_WorkTimeAmount_TextField);
        this.kdtRWORepairItemEntry.getColumn("WorkTimeAmount").setEditor(kdtRWORepairItemEntry_WorkTimeAmount_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_DiscountRate_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_DiscountRate_TextField.setName("kdtRWORepairItemEntry_DiscountRate_TextField");
        kdtRWORepairItemEntry_DiscountRate_TextField.setVisible(true);
        kdtRWORepairItemEntry_DiscountRate_TextField.setEditable(true);
        kdtRWORepairItemEntry_DiscountRate_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_DiscountRate_TextField.setDataType(1);
        	kdtRWORepairItemEntry_DiscountRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_DiscountRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_DiscountRate_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_DiscountRate_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_DiscountRate_TextField);
        this.kdtRWORepairItemEntry.getColumn("DiscountRate").setEditor(kdtRWORepairItemEntry_DiscountRate_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_DiscountAmount_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_DiscountAmount_TextField.setName("kdtRWORepairItemEntry_DiscountAmount_TextField");
        kdtRWORepairItemEntry_DiscountAmount_TextField.setVisible(true);
        kdtRWORepairItemEntry_DiscountAmount_TextField.setEditable(true);
        kdtRWORepairItemEntry_DiscountAmount_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_DiscountAmount_TextField.setDataType(1);
        	kdtRWORepairItemEntry_DiscountAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_DiscountAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_DiscountAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_DiscountAmount_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_DiscountAmount_TextField);
        this.kdtRWORepairItemEntry.getColumn("DiscountAmount").setEditor(kdtRWORepairItemEntry_DiscountAmount_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_ARAmount_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_ARAmount_TextField.setName("kdtRWORepairItemEntry_ARAmount_TextField");
        kdtRWORepairItemEntry_ARAmount_TextField.setVisible(true);
        kdtRWORepairItemEntry_ARAmount_TextField.setEditable(true);
        kdtRWORepairItemEntry_ARAmount_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_ARAmount_TextField.setDataType(1);
        	kdtRWORepairItemEntry_ARAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_ARAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_ARAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_ARAmount_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_ARAmount_TextField);
        this.kdtRWORepairItemEntry.getColumn("ARAmount").setEditor(kdtRWORepairItemEntry_ARAmount_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_ActualAmount_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_ActualAmount_TextField.setName("kdtRWORepairItemEntry_ActualAmount_TextField");
        kdtRWORepairItemEntry_ActualAmount_TextField.setVisible(true);
        kdtRWORepairItemEntry_ActualAmount_TextField.setEditable(true);
        kdtRWORepairItemEntry_ActualAmount_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_ActualAmount_TextField.setDataType(1);
        	kdtRWORepairItemEntry_ActualAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_ActualAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_ActualAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_ActualAmount_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_ActualAmount_TextField);
        this.kdtRWORepairItemEntry.getColumn("ActualAmount").setEditor(kdtRWORepairItemEntry_ActualAmount_CellEditor);
        KDCheckBox kdtRWORepairItemEntry_IsAppend_CheckBox = new KDCheckBox();
        kdtRWORepairItemEntry_IsAppend_CheckBox.setName("kdtRWORepairItemEntry_IsAppend_CheckBox");
        KDTDefaultCellEditor kdtRWORepairItemEntry_IsAppend_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_IsAppend_CheckBox);
        this.kdtRWORepairItemEntry.getColumn("IsAppend").setEditor(kdtRWORepairItemEntry_IsAppend_CellEditor);
        KDCheckBox kdtRWORepairItemEntry_IsReturnRepair_CheckBox = new KDCheckBox();
        kdtRWORepairItemEntry_IsReturnRepair_CheckBox.setName("kdtRWORepairItemEntry_IsReturnRepair_CheckBox");
        KDTDefaultCellEditor kdtRWORepairItemEntry_IsReturnRepair_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_IsReturnRepair_CheckBox);
        this.kdtRWORepairItemEntry.getColumn("IsReturnRepair").setEditor(kdtRWORepairItemEntry_IsReturnRepair_CellEditor);
        final KDBizPromptBox kdtRWORepairItemEntry_ReworkReason_PromptBox = new KDBizPromptBox();
        kdtRWORepairItemEntry_ReworkReason_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.ReworkReasonQuery");
        kdtRWORepairItemEntry_ReworkReason_PromptBox.setVisible(true);
        kdtRWORepairItemEntry_ReworkReason_PromptBox.setEditable(true);
        kdtRWORepairItemEntry_ReworkReason_PromptBox.setDisplayFormat("$number$");
        kdtRWORepairItemEntry_ReworkReason_PromptBox.setEditFormat("$number$");
        kdtRWORepairItemEntry_ReworkReason_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWORepairItemEntry_ReworkReason_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_ReworkReason_PromptBox);
        this.kdtRWORepairItemEntry.getColumn("ReworkReason").setEditor(kdtRWORepairItemEntry_ReworkReason_CellEditor);
        ObjectValueRender kdtRWORepairItemEntry_ReworkReason_OVR = new ObjectValueRender();
        kdtRWORepairItemEntry_ReworkReason_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWORepairItemEntry.getColumn("ReworkReason").setRenderer(kdtRWORepairItemEntry_ReworkReason_OVR);
        KDCheckBox kdtRWORepairItemEntry_IsDelete_CheckBox = new KDCheckBox();
        kdtRWORepairItemEntry_IsDelete_CheckBox.setName("kdtRWORepairItemEntry_IsDelete_CheckBox");
        KDTDefaultCellEditor kdtRWORepairItemEntry_IsDelete_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_IsDelete_CheckBox);
        this.kdtRWORepairItemEntry.getColumn("IsDelete").setEditor(kdtRWORepairItemEntry_IsDelete_CellEditor);
        KDComboBox kdtRWORepairItemEntry_ItemStatus_ComboBox = new KDComboBox();
        kdtRWORepairItemEntry_ItemStatus_ComboBox.setName("kdtRWORepairItemEntry_ItemStatus_ComboBox");
        kdtRWORepairItemEntry_ItemStatus_ComboBox.setVisible(true);
        kdtRWORepairItemEntry_ItemStatus_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.RepairItemStatusEnum").toArray());
        KDTDefaultCellEditor kdtRWORepairItemEntry_ItemStatus_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_ItemStatus_ComboBox);
        this.kdtRWORepairItemEntry.getColumn("ItemStatus").setEditor(kdtRWORepairItemEntry_ItemStatus_CellEditor);
        KDCheckBox kdtRWORepairItemEntry_IsMulAssign_CheckBox = new KDCheckBox();
        kdtRWORepairItemEntry_IsMulAssign_CheckBox.setName("kdtRWORepairItemEntry_IsMulAssign_CheckBox");
        KDTDefaultCellEditor kdtRWORepairItemEntry_IsMulAssign_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_IsMulAssign_CheckBox);
        this.kdtRWORepairItemEntry.getColumn("IsMulAssign").setEditor(kdtRWORepairItemEntry_IsMulAssign_CellEditor);
        final KDBizPromptBox kdtRWORepairItemEntry_WorkGroup_PromptBox = new KDBizPromptBox();
        kdtRWORepairItemEntry_WorkGroup_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairGroupQuery");
        kdtRWORepairItemEntry_WorkGroup_PromptBox.setVisible(true);
        kdtRWORepairItemEntry_WorkGroup_PromptBox.setEditable(true);
        kdtRWORepairItemEntry_WorkGroup_PromptBox.setDisplayFormat("$number$");
        kdtRWORepairItemEntry_WorkGroup_PromptBox.setEditFormat("$number$");
        kdtRWORepairItemEntry_WorkGroup_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWORepairItemEntry_WorkGroup_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_WorkGroup_PromptBox);
        this.kdtRWORepairItemEntry.getColumn("WorkGroup").setEditor(kdtRWORepairItemEntry_WorkGroup_CellEditor);
        ObjectValueRender kdtRWORepairItemEntry_WorkGroup_OVR = new ObjectValueRender();
        kdtRWORepairItemEntry_WorkGroup_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWORepairItemEntry.getColumn("WorkGroup").setRenderer(kdtRWORepairItemEntry_WorkGroup_OVR);
        final KDBizPromptBox kdtRWORepairItemEntry_Person_PromptBox = new KDBizPromptBox();
        kdtRWORepairItemEntry_Person_PromptBox.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
        kdtRWORepairItemEntry_Person_PromptBox.setVisible(true);
        kdtRWORepairItemEntry_Person_PromptBox.setEditable(true);
        kdtRWORepairItemEntry_Person_PromptBox.setDisplayFormat("$number$");
        kdtRWORepairItemEntry_Person_PromptBox.setEditFormat("$number$");
        kdtRWORepairItemEntry_Person_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWORepairItemEntry_Person_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_Person_PromptBox);
        this.kdtRWORepairItemEntry.getColumn("Person").setEditor(kdtRWORepairItemEntry_Person_CellEditor);
        ObjectValueRender kdtRWORepairItemEntry_Person_OVR = new ObjectValueRender();
        kdtRWORepairItemEntry_Person_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWORepairItemEntry.getColumn("Person").setRenderer(kdtRWORepairItemEntry_Person_OVR);
        final KDBizPromptBox kdtRWORepairItemEntry_WorkStation_PromptBox = new KDBizPromptBox();
        kdtRWORepairItemEntry_WorkStation_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.WorkstationQuery");
        kdtRWORepairItemEntry_WorkStation_PromptBox.setVisible(true);
        kdtRWORepairItemEntry_WorkStation_PromptBox.setEditable(true);
        kdtRWORepairItemEntry_WorkStation_PromptBox.setDisplayFormat("$number$");
        kdtRWORepairItemEntry_WorkStation_PromptBox.setEditFormat("$number$");
        kdtRWORepairItemEntry_WorkStation_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWORepairItemEntry_WorkStation_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_WorkStation_PromptBox);
        this.kdtRWORepairItemEntry.getColumn("WorkStation").setEditor(kdtRWORepairItemEntry_WorkStation_CellEditor);
        ObjectValueRender kdtRWORepairItemEntry_WorkStation_OVR = new ObjectValueRender();
        kdtRWORepairItemEntry_WorkStation_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWORepairItemEntry.getColumn("WorkStation").setRenderer(kdtRWORepairItemEntry_WorkStation_OVR);
        KDFormattedTextField kdtRWORepairItemEntry_ActualWorkTime_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_ActualWorkTime_TextField.setName("kdtRWORepairItemEntry_ActualWorkTime_TextField");
        kdtRWORepairItemEntry_ActualWorkTime_TextField.setVisible(true);
        kdtRWORepairItemEntry_ActualWorkTime_TextField.setEditable(true);
        kdtRWORepairItemEntry_ActualWorkTime_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_ActualWorkTime_TextField.setDataType(1);
        	kdtRWORepairItemEntry_ActualWorkTime_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_ActualWorkTime_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_ActualWorkTime_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_ActualWorkTime_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_ActualWorkTime_TextField);
        this.kdtRWORepairItemEntry.getColumn("ActualWorkTime").setEditor(kdtRWORepairItemEntry_ActualWorkTime_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_WagePrice_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_WagePrice_TextField.setName("kdtRWORepairItemEntry_WagePrice_TextField");
        kdtRWORepairItemEntry_WagePrice_TextField.setVisible(true);
        kdtRWORepairItemEntry_WagePrice_TextField.setEditable(true);
        kdtRWORepairItemEntry_WagePrice_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_WagePrice_TextField.setDataType(1);
        	kdtRWORepairItemEntry_WagePrice_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_WagePrice_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_WagePrice_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_WagePrice_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_WagePrice_TextField);
        this.kdtRWORepairItemEntry.getColumn("WagePrice").setEditor(kdtRWORepairItemEntry_WagePrice_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_WorkTimeCost_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_WorkTimeCost_TextField.setName("kdtRWORepairItemEntry_WorkTimeCost_TextField");
        kdtRWORepairItemEntry_WorkTimeCost_TextField.setVisible(true);
        kdtRWORepairItemEntry_WorkTimeCost_TextField.setEditable(true);
        kdtRWORepairItemEntry_WorkTimeCost_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_WorkTimeCost_TextField.setDataType(1);
        	kdtRWORepairItemEntry_WorkTimeCost_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_WorkTimeCost_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_WorkTimeCost_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_WorkTimeCost_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_WorkTimeCost_TextField);
        this.kdtRWORepairItemEntry.getColumn("WorkTimeCost").setEditor(kdtRWORepairItemEntry_WorkTimeCost_CellEditor);
        KDCheckBox kdtRWORepairItemEntry_IsTimeEdit_CheckBox = new KDCheckBox();
        kdtRWORepairItemEntry_IsTimeEdit_CheckBox.setName("kdtRWORepairItemEntry_IsTimeEdit_CheckBox");
        KDTDefaultCellEditor kdtRWORepairItemEntry_IsTimeEdit_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_IsTimeEdit_CheckBox);
        this.kdtRWORepairItemEntry.getColumn("IsTimeEdit").setEditor(kdtRWORepairItemEntry_IsTimeEdit_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_OldDiscountRate_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_OldDiscountRate_TextField.setName("kdtRWORepairItemEntry_OldDiscountRate_TextField");
        kdtRWORepairItemEntry_OldDiscountRate_TextField.setVisible(true);
        kdtRWORepairItemEntry_OldDiscountRate_TextField.setEditable(true);
        kdtRWORepairItemEntry_OldDiscountRate_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_OldDiscountRate_TextField.setDataType(1);
        	kdtRWORepairItemEntry_OldDiscountRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_OldDiscountRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_OldDiscountRate_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_OldDiscountRate_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_OldDiscountRate_TextField);
        this.kdtRWORepairItemEntry.getColumn("OldDiscountRate").setEditor(kdtRWORepairItemEntry_OldDiscountRate_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_WorkTimeStdPrice_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_WorkTimeStdPrice_TextField.setName("kdtRWORepairItemEntry_WorkTimeStdPrice_TextField");
        kdtRWORepairItemEntry_WorkTimeStdPrice_TextField.setVisible(true);
        kdtRWORepairItemEntry_WorkTimeStdPrice_TextField.setEditable(true);
        kdtRWORepairItemEntry_WorkTimeStdPrice_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_WorkTimeStdPrice_TextField.setDataType(1);
        	kdtRWORepairItemEntry_WorkTimeStdPrice_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_WorkTimeStdPrice_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_WorkTimeStdPrice_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_WorkTimeStdPrice_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_WorkTimeStdPrice_TextField);
        this.kdtRWORepairItemEntry.getColumn("WorkTimeStdPrice").setEditor(kdtRWORepairItemEntry_WorkTimeStdPrice_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_wipLineNo_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_wipLineNo_TextField.setName("kdtRWORepairItemEntry_wipLineNo_TextField");
        kdtRWORepairItemEntry_wipLineNo_TextField.setVisible(true);
        kdtRWORepairItemEntry_wipLineNo_TextField.setEditable(true);
        kdtRWORepairItemEntry_wipLineNo_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_wipLineNo_TextField.setDataType(0);
        KDTDefaultCellEditor kdtRWORepairItemEntry_wipLineNo_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_wipLineNo_TextField);
        this.kdtRWORepairItemEntry.getColumn("wipLineNo").setEditor(kdtRWORepairItemEntry_wipLineNo_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_wipFactLineNo_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_wipFactLineNo_TextField.setName("kdtRWORepairItemEntry_wipFactLineNo_TextField");
        kdtRWORepairItemEntry_wipFactLineNo_TextField.setVisible(true);
        kdtRWORepairItemEntry_wipFactLineNo_TextField.setEditable(true);
        kdtRWORepairItemEntry_wipFactLineNo_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_wipFactLineNo_TextField.setDataType(0);
        KDTDefaultCellEditor kdtRWORepairItemEntry_wipFactLineNo_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_wipFactLineNo_TextField);
        this.kdtRWORepairItemEntry.getColumn("wipFactLineNo").setEditor(kdtRWORepairItemEntry_wipFactLineNo_CellEditor);
        KDFormattedTextField kdtRWORepairItemEntry_wbprice_TextField = new KDFormattedTextField();
        kdtRWORepairItemEntry_wbprice_TextField.setName("kdtRWORepairItemEntry_wbprice_TextField");
        kdtRWORepairItemEntry_wbprice_TextField.setVisible(true);
        kdtRWORepairItemEntry_wbprice_TextField.setEditable(true);
        kdtRWORepairItemEntry_wbprice_TextField.setHorizontalAlignment(2);
        kdtRWORepairItemEntry_wbprice_TextField.setDataType(1);
        	kdtRWORepairItemEntry_wbprice_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairItemEntry_wbprice_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairItemEntry_wbprice_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairItemEntry_wbprice_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_wbprice_TextField);
        this.kdtRWORepairItemEntry.getColumn("wbprice").setEditor(kdtRWORepairItemEntry_wbprice_CellEditor);
        KDCheckBox kdtRWORepairItemEntry_isCreateTo_CheckBox = new KDCheckBox();
        kdtRWORepairItemEntry_isCreateTo_CheckBox.setName("kdtRWORepairItemEntry_isCreateTo_CheckBox");
        KDTDefaultCellEditor kdtRWORepairItemEntry_isCreateTo_CellEditor = new KDTDefaultCellEditor(kdtRWORepairItemEntry_isCreateTo_CheckBox);
        this.kdtRWORepairItemEntry.getColumn("isCreateTo").setEditor(kdtRWORepairItemEntry_isCreateTo_CellEditor);
        // kdtRWOSparepartEntry
		String kdtRWOSparepartEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol8\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol9\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol10\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol12\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol13\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol14\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol16\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol17\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol18\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol19\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol20\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol21\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol22\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol23\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol24\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol25\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol26\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol27\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol28\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol29\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol32\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol33\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol34\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol35\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol36\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol37\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol39\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol40\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\" t:configured=\"true\"><t:ColumnGroup><t:Column t:key=\"ID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"Material\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"1\" /><t:Column t:key=\"MaterialName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"model\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" /><t:Column t:key=\"PaymentClassify\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"4\" /><t:Column t:key=\"SettleObject\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"5\" /><t:Column t:key=\"RepairClassify\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"6\" /><t:Column t:key=\"RepairPkg\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" /><t:Column t:key=\"ServiceActivity\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" t:styleID=\"sCol8\" /><t:Column t:key=\"IsBO\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" t:styleID=\"sCol9\" /><t:Column t:key=\"IntendArrivalTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" t:styleID=\"sCol10\" /><t:Column t:key=\"AssistProperty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" /><t:Column t:key=\"Qty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"12\" t:styleID=\"sCol12\" /><t:Column t:key=\"Unit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" t:styleID=\"sCol13\" /><t:Column t:key=\"BaseQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" t:styleID=\"sCol14\" /><t:Column t:key=\"BaseUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" /><t:Column t:key=\"NoIssueQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" t:styleID=\"sCol16\" /><t:Column t:key=\"IssueQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"17\" t:styleID=\"sCol17\" /><t:Column t:key=\"TaxPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" t:styleID=\"sCol18\" /><t:Column t:key=\"TaxRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"19\" t:styleID=\"sCol19\" /><t:Column t:key=\"NoTaxPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"20\" t:styleID=\"sCol20\" /><t:Column t:key=\"TaxAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"21\" t:styleID=\"sCol21\" /><t:Column t:key=\"NoTaxAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"22\" t:styleID=\"sCol22\" /><t:Column t:key=\"DiscountRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"23\" t:styleID=\"sCol23\" /><t:Column t:key=\"DiscountAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"24\" t:styleID=\"sCol24\" /><t:Column t:key=\"ARAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"25\" t:styleID=\"sCol25\" /><t:Column t:key=\"ActualAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"26\" t:styleID=\"sCol26\" /><t:Column t:key=\"Tax\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"27\" t:styleID=\"sCol27\" /><t:Column t:key=\"IsAppend\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"28\" t:styleID=\"sCol28\" /><t:Column t:key=\"IsDelete\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"29\" t:styleID=\"sCol29\" /><t:Column t:key=\"RepairItem\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"30\" /><t:Column t:key=\"Remark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"31\" /><t:Column t:key=\"InstantStore\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"32\" t:styleID=\"sCol32\" /><t:Column t:key=\"OldDiscountRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"33\" t:styleID=\"sCol33\" /><t:Column t:key=\"MaterialGroup\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"34\" t:styleID=\"sCol34\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"35\" t:styleID=\"sCol35\" /><t:Column t:key=\"wipLineNo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"36\" t:configured=\"true\" t:styleID=\"sCol36\" /><t:Column t:key=\"wipFactLineNo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"37\" t:configured=\"true\" t:styleID=\"sCol37\" /><t:Column t:key=\"isCT\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"38\" t:configured=\"true\" /><t:Column t:key=\"itemSpEntryId\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"39\" t:configured=\"true\" t:styleID=\"sCol39\" /><t:Column t:key=\"isCreateTo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"40\" t:configured=\"true\" t:styleID=\"sCol40\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{ID}</t:Cell><t:Cell>$Resource{Material}</t:Cell><t:Cell>$Resource{MaterialName}</t:Cell><t:Cell>$Resource{model}</t:Cell><t:Cell>$Resource{PaymentClassify}</t:Cell><t:Cell>$Resource{SettleObject}</t:Cell><t:Cell>$Resource{RepairClassify}</t:Cell><t:Cell>$Resource{RepairPkg}</t:Cell><t:Cell>$Resource{ServiceActivity}</t:Cell><t:Cell>$Resource{IsBO}</t:Cell><t:Cell>$Resource{IntendArrivalTime}</t:Cell><t:Cell>$Resource{AssistProperty}</t:Cell><t:Cell>$Resource{Qty}</t:Cell><t:Cell>$Resource{Unit}</t:Cell><t:Cell>$Resource{BaseQty}</t:Cell><t:Cell>$Resource{BaseUnit}</t:Cell><t:Cell>$Resource{NoIssueQty}</t:Cell><t:Cell>$Resource{IssueQty}</t:Cell><t:Cell>$Resource{TaxPrice}</t:Cell><t:Cell>$Resource{TaxRate}</t:Cell><t:Cell>$Resource{NoTaxPrice}</t:Cell><t:Cell>$Resource{TaxAmount}</t:Cell><t:Cell>$Resource{NoTaxAmount}</t:Cell><t:Cell>$Resource{DiscountRate}</t:Cell><t:Cell>$Resource{DiscountAmount}</t:Cell><t:Cell>$Resource{ARAmount}</t:Cell><t:Cell>$Resource{ActualAmount}</t:Cell><t:Cell>$Resource{Tax}</t:Cell><t:Cell>$Resource{IsAppend}</t:Cell><t:Cell>$Resource{IsDelete}</t:Cell><t:Cell>$Resource{RepairItem}</t:Cell><t:Cell>$Resource{Remark}</t:Cell><t:Cell>$Resource{InstantStore}</t:Cell><t:Cell>$Resource{OldDiscountRate}</t:Cell><t:Cell>$Resource{MaterialGroup}</t:Cell><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{wipLineNo}</t:Cell><t:Cell>$Resource{wipFactLineNo}</t:Cell><t:Cell>$Resource{isCT}</t:Cell><t:Cell>$Resource{itemSpEntryId}</t:Cell><t:Cell>$Resource{isCreateTo}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtRWOSparepartEntry.setFormatXml(resHelper.translateString("kdtRWOSparepartEntry",kdtRWOSparepartEntryStrXML));
        kdtRWOSparepartEntry.addKDTEditListener(new KDTEditAdapter() {
		public void editStopped(KDTEditEvent e) {
			try {
				kdtRWOSparepartEntry_Changed(e.getRowIndex(),e.getColIndex());
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        this.kdtRWOSparepartEntry.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStarting(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtRWOSparepartEntry_editStarting(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtRWOSparepartEntry.putBindContents("editData",new String[] {"id","Material","Material.name","","PaymentClassify","SettleObject","RepairClassify","RepairPkg","ServiceActivity","IsBO","IntendArrivalTime","AssistProperty","Qty","Unit","BaseQty","BaseUnit","NoIssueQty","IssueQty","TaxPrice","TaxRate","NoTaxPrice","TaxAmount","NoTaxAmount","DiscountRate","DiscountAmount","ARAmount","ActualAmount","Tax","IsAppend","IsDelete","RepairItem","Remark","InstantStore","OldDiscountRate","MaterialGroup","seq","wipLineNo","wipFactLineNo","isCT","itemSpEntryId","isCreateTo"});


        this.kdtRWOSparepartEntry.checkParsed();
        final KDBizPromptBox kdtRWOSparepartEntry_Material_PromptBox = new KDBizPromptBox();
        kdtRWOSparepartEntry_Material_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7MaterialBaseInfoQuery");
        kdtRWOSparepartEntry_Material_PromptBox.setVisible(true);
        kdtRWOSparepartEntry_Material_PromptBox.setEditable(true);
        kdtRWOSparepartEntry_Material_PromptBox.setDisplayFormat("$number$");
        kdtRWOSparepartEntry_Material_PromptBox.setEditFormat("$number$");
        kdtRWOSparepartEntry_Material_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOSparepartEntry_Material_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_Material_PromptBox);
        this.kdtRWOSparepartEntry.getColumn("Material").setEditor(kdtRWOSparepartEntry_Material_CellEditor);
        ObjectValueRender kdtRWOSparepartEntry_Material_OVR = new ObjectValueRender();
        kdtRWOSparepartEntry_Material_OVR.setFormat(new BizDataFormat("$number$"));
        this.kdtRWOSparepartEntry.getColumn("Material").setRenderer(kdtRWOSparepartEntry_Material_OVR);
        final KDBizPromptBox kdtRWOSparepartEntry_PaymentClassify_PromptBox = new KDBizPromptBox();
        kdtRWOSparepartEntry_PaymentClassify_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.PaymentClassifyQuery");
        kdtRWOSparepartEntry_PaymentClassify_PromptBox.setVisible(true);
        kdtRWOSparepartEntry_PaymentClassify_PromptBox.setEditable(true);
        kdtRWOSparepartEntry_PaymentClassify_PromptBox.setDisplayFormat("$number$");
        kdtRWOSparepartEntry_PaymentClassify_PromptBox.setEditFormat("$number$");
        kdtRWOSparepartEntry_PaymentClassify_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOSparepartEntry_PaymentClassify_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_PaymentClassify_PromptBox);
        this.kdtRWOSparepartEntry.getColumn("PaymentClassify").setEditor(kdtRWOSparepartEntry_PaymentClassify_CellEditor);
        ObjectValueRender kdtRWOSparepartEntry_PaymentClassify_OVR = new ObjectValueRender();
        kdtRWOSparepartEntry_PaymentClassify_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWOSparepartEntry.getColumn("PaymentClassify").setRenderer(kdtRWOSparepartEntry_PaymentClassify_OVR);
        KDComboBox kdtRWOSparepartEntry_SettleObject_ComboBox = new KDComboBox();
        kdtRWOSparepartEntry_SettleObject_ComboBox.setName("kdtRWOSparepartEntry_SettleObject_ComboBox");
        kdtRWOSparepartEntry_SettleObject_ComboBox.setVisible(true);
        kdtRWOSparepartEntry_SettleObject_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum").toArray());
        KDTDefaultCellEditor kdtRWOSparepartEntry_SettleObject_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_SettleObject_ComboBox);
        this.kdtRWOSparepartEntry.getColumn("SettleObject").setEditor(kdtRWOSparepartEntry_SettleObject_CellEditor);
        final KDBizPromptBox kdtRWOSparepartEntry_RepairClassify_PromptBox = new KDBizPromptBox();
        kdtRWOSparepartEntry_RepairClassify_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairClassifyQuery");
        kdtRWOSparepartEntry_RepairClassify_PromptBox.setVisible(true);
        kdtRWOSparepartEntry_RepairClassify_PromptBox.setEditable(true);
        kdtRWOSparepartEntry_RepairClassify_PromptBox.setDisplayFormat("$number$");
        kdtRWOSparepartEntry_RepairClassify_PromptBox.setEditFormat("$number$");
        kdtRWOSparepartEntry_RepairClassify_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOSparepartEntry_RepairClassify_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_RepairClassify_PromptBox);
        this.kdtRWOSparepartEntry.getColumn("RepairClassify").setEditor(kdtRWOSparepartEntry_RepairClassify_CellEditor);
        ObjectValueRender kdtRWOSparepartEntry_RepairClassify_OVR = new ObjectValueRender();
        kdtRWOSparepartEntry_RepairClassify_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWOSparepartEntry.getColumn("RepairClassify").setRenderer(kdtRWOSparepartEntry_RepairClassify_OVR);
        final KDBizPromptBox kdtRWOSparepartEntry_RepairPkg_PromptBox = new KDBizPromptBox();
        kdtRWOSparepartEntry_RepairPkg_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairPkgQuery");
        kdtRWOSparepartEntry_RepairPkg_PromptBox.setVisible(true);
        kdtRWOSparepartEntry_RepairPkg_PromptBox.setEditable(true);
        kdtRWOSparepartEntry_RepairPkg_PromptBox.setDisplayFormat("$number$");
        kdtRWOSparepartEntry_RepairPkg_PromptBox.setEditFormat("$number$");
        kdtRWOSparepartEntry_RepairPkg_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOSparepartEntry_RepairPkg_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_RepairPkg_PromptBox);
        this.kdtRWOSparepartEntry.getColumn("RepairPkg").setEditor(kdtRWOSparepartEntry_RepairPkg_CellEditor);
        ObjectValueRender kdtRWOSparepartEntry_RepairPkg_OVR = new ObjectValueRender();
        kdtRWOSparepartEntry_RepairPkg_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWOSparepartEntry.getColumn("RepairPkg").setRenderer(kdtRWOSparepartEntry_RepairPkg_OVR);
        final KDBizPromptBox kdtRWOSparepartEntry_ServiceActivity_PromptBox = new KDBizPromptBox();
        kdtRWOSparepartEntry_ServiceActivity_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.rsm.rs.app.ServiceActivityQuery");
        kdtRWOSparepartEntry_ServiceActivity_PromptBox.setVisible(true);
        kdtRWOSparepartEntry_ServiceActivity_PromptBox.setEditable(true);
        kdtRWOSparepartEntry_ServiceActivity_PromptBox.setDisplayFormat("$number$");
        kdtRWOSparepartEntry_ServiceActivity_PromptBox.setEditFormat("$number$");
        kdtRWOSparepartEntry_ServiceActivity_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOSparepartEntry_ServiceActivity_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_ServiceActivity_PromptBox);
        this.kdtRWOSparepartEntry.getColumn("ServiceActivity").setEditor(kdtRWOSparepartEntry_ServiceActivity_CellEditor);
        ObjectValueRender kdtRWOSparepartEntry_ServiceActivity_OVR = new ObjectValueRender();
        kdtRWOSparepartEntry_ServiceActivity_OVR.setFormat(new BizDataFormat("$Name_l2$"));
        this.kdtRWOSparepartEntry.getColumn("ServiceActivity").setRenderer(kdtRWOSparepartEntry_ServiceActivity_OVR);
        			EntityViewInfo evikdtRWOSparepartEntry_ServiceActivity_PromptBox = new EntityViewInfo ();
		evikdtRWOSparepartEntry_ServiceActivity_PromptBox.setFilter(com.kingdee.eas.framework.FrameWorkUtils.getF7FilterInfoByAuthorizedOrg(com.kingdee.eas.basedata.org.OrgType.getEnum("Admin"),"OrgUnit.id"));
		kdtRWOSparepartEntry_ServiceActivity_PromptBox.setEntityViewInfo(evikdtRWOSparepartEntry_ServiceActivity_PromptBox);
					
        KDCheckBox kdtRWOSparepartEntry_IsBO_CheckBox = new KDCheckBox();
        kdtRWOSparepartEntry_IsBO_CheckBox.setName("kdtRWOSparepartEntry_IsBO_CheckBox");
        KDTDefaultCellEditor kdtRWOSparepartEntry_IsBO_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_IsBO_CheckBox);
        this.kdtRWOSparepartEntry.getColumn("IsBO").setEditor(kdtRWOSparepartEntry_IsBO_CellEditor);
        KDDatePicker kdtRWOSparepartEntry_IntendArrivalTime_DatePicker = new KDDatePicker();
        kdtRWOSparepartEntry_IntendArrivalTime_DatePicker.setName("kdtRWOSparepartEntry_IntendArrivalTime_DatePicker");
        kdtRWOSparepartEntry_IntendArrivalTime_DatePicker.setVisible(true);
        kdtRWOSparepartEntry_IntendArrivalTime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtRWOSparepartEntry_IntendArrivalTime_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_IntendArrivalTime_DatePicker);
        this.kdtRWOSparepartEntry.getColumn("IntendArrivalTime").setEditor(kdtRWOSparepartEntry_IntendArrivalTime_CellEditor);
        final KDBizPromptBox kdtRWOSparepartEntry_AssistProperty_PromptBox = new KDBizPromptBox();
        kdtRWOSparepartEntry_AssistProperty_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7AsstAttrValueQuery");
        kdtRWOSparepartEntry_AssistProperty_PromptBox.setVisible(true);
        kdtRWOSparepartEntry_AssistProperty_PromptBox.setEditable(true);
        kdtRWOSparepartEntry_AssistProperty_PromptBox.setDisplayFormat("$number$");
        kdtRWOSparepartEntry_AssistProperty_PromptBox.setEditFormat("$number$");
        kdtRWOSparepartEntry_AssistProperty_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOSparepartEntry_AssistProperty_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_AssistProperty_PromptBox);
        this.kdtRWOSparepartEntry.getColumn("AssistProperty").setEditor(kdtRWOSparepartEntry_AssistProperty_CellEditor);
        ObjectValueRender kdtRWOSparepartEntry_AssistProperty_OVR = new ObjectValueRender();
        kdtRWOSparepartEntry_AssistProperty_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWOSparepartEntry.getColumn("AssistProperty").setRenderer(kdtRWOSparepartEntry_AssistProperty_OVR);
        KDFormattedTextField kdtRWOSparepartEntry_Qty_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_Qty_TextField.setName("kdtRWOSparepartEntry_Qty_TextField");
        kdtRWOSparepartEntry_Qty_TextField.setVisible(true);
        kdtRWOSparepartEntry_Qty_TextField.setEditable(true);
        kdtRWOSparepartEntry_Qty_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_Qty_TextField.setDataType(1);
        	kdtRWOSparepartEntry_Qty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_Qty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_Qty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_Qty_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_Qty_TextField);
        this.kdtRWOSparepartEntry.getColumn("Qty").setEditor(kdtRWOSparepartEntry_Qty_CellEditor);
        final KDBizPromptBox kdtRWOSparepartEntry_Unit_PromptBox = new KDBizPromptBox();
        kdtRWOSparepartEntry_Unit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7MeasureUnitQuery");
        kdtRWOSparepartEntry_Unit_PromptBox.setVisible(true);
        kdtRWOSparepartEntry_Unit_PromptBox.setEditable(true);
        kdtRWOSparepartEntry_Unit_PromptBox.setDisplayFormat("$number$");
        kdtRWOSparepartEntry_Unit_PromptBox.setEditFormat("$number$");
        kdtRWOSparepartEntry_Unit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOSparepartEntry_Unit_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_Unit_PromptBox);
        this.kdtRWOSparepartEntry.getColumn("Unit").setEditor(kdtRWOSparepartEntry_Unit_CellEditor);
        ObjectValueRender kdtRWOSparepartEntry_Unit_OVR = new ObjectValueRender();
        kdtRWOSparepartEntry_Unit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWOSparepartEntry.getColumn("Unit").setRenderer(kdtRWOSparepartEntry_Unit_OVR);
        KDFormattedTextField kdtRWOSparepartEntry_BaseQty_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_BaseQty_TextField.setName("kdtRWOSparepartEntry_BaseQty_TextField");
        kdtRWOSparepartEntry_BaseQty_TextField.setVisible(true);
        kdtRWOSparepartEntry_BaseQty_TextField.setEditable(true);
        kdtRWOSparepartEntry_BaseQty_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_BaseQty_TextField.setDataType(1);
        	kdtRWOSparepartEntry_BaseQty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_BaseQty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_BaseQty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_BaseQty_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_BaseQty_TextField);
        this.kdtRWOSparepartEntry.getColumn("BaseQty").setEditor(kdtRWOSparepartEntry_BaseQty_CellEditor);
        final KDBizPromptBox kdtRWOSparepartEntry_BaseUnit_PromptBox = new KDBizPromptBox();
        kdtRWOSparepartEntry_BaseUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7MeasureUnitQuery");
        kdtRWOSparepartEntry_BaseUnit_PromptBox.setVisible(true);
        kdtRWOSparepartEntry_BaseUnit_PromptBox.setEditable(true);
        kdtRWOSparepartEntry_BaseUnit_PromptBox.setDisplayFormat("$number$");
        kdtRWOSparepartEntry_BaseUnit_PromptBox.setEditFormat("$number$");
        kdtRWOSparepartEntry_BaseUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOSparepartEntry_BaseUnit_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_BaseUnit_PromptBox);
        this.kdtRWOSparepartEntry.getColumn("BaseUnit").setEditor(kdtRWOSparepartEntry_BaseUnit_CellEditor);
        ObjectValueRender kdtRWOSparepartEntry_BaseUnit_OVR = new ObjectValueRender();
        kdtRWOSparepartEntry_BaseUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWOSparepartEntry.getColumn("BaseUnit").setRenderer(kdtRWOSparepartEntry_BaseUnit_OVR);
        KDFormattedTextField kdtRWOSparepartEntry_NoIssueQty_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_NoIssueQty_TextField.setName("kdtRWOSparepartEntry_NoIssueQty_TextField");
        kdtRWOSparepartEntry_NoIssueQty_TextField.setVisible(true);
        kdtRWOSparepartEntry_NoIssueQty_TextField.setEditable(true);
        kdtRWOSparepartEntry_NoIssueQty_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_NoIssueQty_TextField.setDataType(1);
        	kdtRWOSparepartEntry_NoIssueQty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_NoIssueQty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_NoIssueQty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_NoIssueQty_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_NoIssueQty_TextField);
        this.kdtRWOSparepartEntry.getColumn("NoIssueQty").setEditor(kdtRWOSparepartEntry_NoIssueQty_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_IssueQty_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_IssueQty_TextField.setName("kdtRWOSparepartEntry_IssueQty_TextField");
        kdtRWOSparepartEntry_IssueQty_TextField.setVisible(true);
        kdtRWOSparepartEntry_IssueQty_TextField.setEditable(true);
        kdtRWOSparepartEntry_IssueQty_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_IssueQty_TextField.setDataType(1);
        	kdtRWOSparepartEntry_IssueQty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_IssueQty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_IssueQty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_IssueQty_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_IssueQty_TextField);
        this.kdtRWOSparepartEntry.getColumn("IssueQty").setEditor(kdtRWOSparepartEntry_IssueQty_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_TaxPrice_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_TaxPrice_TextField.setName("kdtRWOSparepartEntry_TaxPrice_TextField");
        kdtRWOSparepartEntry_TaxPrice_TextField.setVisible(true);
        kdtRWOSparepartEntry_TaxPrice_TextField.setEditable(true);
        kdtRWOSparepartEntry_TaxPrice_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_TaxPrice_TextField.setDataType(1);
        	kdtRWOSparepartEntry_TaxPrice_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_TaxPrice_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_TaxPrice_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_TaxPrice_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_TaxPrice_TextField);
        this.kdtRWOSparepartEntry.getColumn("TaxPrice").setEditor(kdtRWOSparepartEntry_TaxPrice_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_TaxRate_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_TaxRate_TextField.setName("kdtRWOSparepartEntry_TaxRate_TextField");
        kdtRWOSparepartEntry_TaxRate_TextField.setVisible(true);
        kdtRWOSparepartEntry_TaxRate_TextField.setEditable(true);
        kdtRWOSparepartEntry_TaxRate_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_TaxRate_TextField.setDataType(1);
        	kdtRWOSparepartEntry_TaxRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_TaxRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_TaxRate_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_TaxRate_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_TaxRate_TextField);
        this.kdtRWOSparepartEntry.getColumn("TaxRate").setEditor(kdtRWOSparepartEntry_TaxRate_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_NoTaxPrice_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_NoTaxPrice_TextField.setName("kdtRWOSparepartEntry_NoTaxPrice_TextField");
        kdtRWOSparepartEntry_NoTaxPrice_TextField.setVisible(true);
        kdtRWOSparepartEntry_NoTaxPrice_TextField.setEditable(true);
        kdtRWOSparepartEntry_NoTaxPrice_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_NoTaxPrice_TextField.setDataType(1);
        	kdtRWOSparepartEntry_NoTaxPrice_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_NoTaxPrice_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_NoTaxPrice_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_NoTaxPrice_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_NoTaxPrice_TextField);
        this.kdtRWOSparepartEntry.getColumn("NoTaxPrice").setEditor(kdtRWOSparepartEntry_NoTaxPrice_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_TaxAmount_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_TaxAmount_TextField.setName("kdtRWOSparepartEntry_TaxAmount_TextField");
        kdtRWOSparepartEntry_TaxAmount_TextField.setVisible(true);
        kdtRWOSparepartEntry_TaxAmount_TextField.setEditable(true);
        kdtRWOSparepartEntry_TaxAmount_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_TaxAmount_TextField.setDataType(1);
        	kdtRWOSparepartEntry_TaxAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_TaxAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_TaxAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_TaxAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_TaxAmount_TextField);
        this.kdtRWOSparepartEntry.getColumn("TaxAmount").setEditor(kdtRWOSparepartEntry_TaxAmount_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_NoTaxAmount_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_NoTaxAmount_TextField.setName("kdtRWOSparepartEntry_NoTaxAmount_TextField");
        kdtRWOSparepartEntry_NoTaxAmount_TextField.setVisible(true);
        kdtRWOSparepartEntry_NoTaxAmount_TextField.setEditable(true);
        kdtRWOSparepartEntry_NoTaxAmount_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_NoTaxAmount_TextField.setDataType(1);
        	kdtRWOSparepartEntry_NoTaxAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_NoTaxAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_NoTaxAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_NoTaxAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_NoTaxAmount_TextField);
        this.kdtRWOSparepartEntry.getColumn("NoTaxAmount").setEditor(kdtRWOSparepartEntry_NoTaxAmount_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_DiscountRate_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_DiscountRate_TextField.setName("kdtRWOSparepartEntry_DiscountRate_TextField");
        kdtRWOSparepartEntry_DiscountRate_TextField.setVisible(true);
        kdtRWOSparepartEntry_DiscountRate_TextField.setEditable(true);
        kdtRWOSparepartEntry_DiscountRate_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_DiscountRate_TextField.setDataType(1);
        	kdtRWOSparepartEntry_DiscountRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_DiscountRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_DiscountRate_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_DiscountRate_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_DiscountRate_TextField);
        this.kdtRWOSparepartEntry.getColumn("DiscountRate").setEditor(kdtRWOSparepartEntry_DiscountRate_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_DiscountAmount_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_DiscountAmount_TextField.setName("kdtRWOSparepartEntry_DiscountAmount_TextField");
        kdtRWOSparepartEntry_DiscountAmount_TextField.setVisible(true);
        kdtRWOSparepartEntry_DiscountAmount_TextField.setEditable(true);
        kdtRWOSparepartEntry_DiscountAmount_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_DiscountAmount_TextField.setDataType(1);
        	kdtRWOSparepartEntry_DiscountAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_DiscountAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_DiscountAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_DiscountAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_DiscountAmount_TextField);
        this.kdtRWOSparepartEntry.getColumn("DiscountAmount").setEditor(kdtRWOSparepartEntry_DiscountAmount_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_ARAmount_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_ARAmount_TextField.setName("kdtRWOSparepartEntry_ARAmount_TextField");
        kdtRWOSparepartEntry_ARAmount_TextField.setVisible(true);
        kdtRWOSparepartEntry_ARAmount_TextField.setEditable(true);
        kdtRWOSparepartEntry_ARAmount_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_ARAmount_TextField.setDataType(1);
        	kdtRWOSparepartEntry_ARAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_ARAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_ARAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_ARAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_ARAmount_TextField);
        this.kdtRWOSparepartEntry.getColumn("ARAmount").setEditor(kdtRWOSparepartEntry_ARAmount_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_ActualAmount_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_ActualAmount_TextField.setName("kdtRWOSparepartEntry_ActualAmount_TextField");
        kdtRWOSparepartEntry_ActualAmount_TextField.setVisible(true);
        kdtRWOSparepartEntry_ActualAmount_TextField.setEditable(true);
        kdtRWOSparepartEntry_ActualAmount_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_ActualAmount_TextField.setDataType(1);
        	kdtRWOSparepartEntry_ActualAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_ActualAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_ActualAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_ActualAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_ActualAmount_TextField);
        this.kdtRWOSparepartEntry.getColumn("ActualAmount").setEditor(kdtRWOSparepartEntry_ActualAmount_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_Tax_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_Tax_TextField.setName("kdtRWOSparepartEntry_Tax_TextField");
        kdtRWOSparepartEntry_Tax_TextField.setVisible(true);
        kdtRWOSparepartEntry_Tax_TextField.setEditable(true);
        kdtRWOSparepartEntry_Tax_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_Tax_TextField.setDataType(1);
        	kdtRWOSparepartEntry_Tax_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_Tax_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_Tax_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_Tax_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_Tax_TextField);
        this.kdtRWOSparepartEntry.getColumn("Tax").setEditor(kdtRWOSparepartEntry_Tax_CellEditor);
        KDCheckBox kdtRWOSparepartEntry_IsAppend_CheckBox = new KDCheckBox();
        kdtRWOSparepartEntry_IsAppend_CheckBox.setName("kdtRWOSparepartEntry_IsAppend_CheckBox");
        KDTDefaultCellEditor kdtRWOSparepartEntry_IsAppend_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_IsAppend_CheckBox);
        this.kdtRWOSparepartEntry.getColumn("IsAppend").setEditor(kdtRWOSparepartEntry_IsAppend_CellEditor);
        KDCheckBox kdtRWOSparepartEntry_IsDelete_CheckBox = new KDCheckBox();
        kdtRWOSparepartEntry_IsDelete_CheckBox.setName("kdtRWOSparepartEntry_IsDelete_CheckBox");
        KDTDefaultCellEditor kdtRWOSparepartEntry_IsDelete_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_IsDelete_CheckBox);
        this.kdtRWOSparepartEntry.getColumn("IsDelete").setEditor(kdtRWOSparepartEntry_IsDelete_CellEditor);
        final KDBizPromptBox kdtRWOSparepartEntry_RepairItem_PromptBox = new KDBizPromptBox();
        kdtRWOSparepartEntry_RepairItem_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairItemQuery");
        kdtRWOSparepartEntry_RepairItem_PromptBox.setVisible(true);
        kdtRWOSparepartEntry_RepairItem_PromptBox.setEditable(true);
        kdtRWOSparepartEntry_RepairItem_PromptBox.setDisplayFormat("$number$");
        kdtRWOSparepartEntry_RepairItem_PromptBox.setEditFormat("$number$");
        kdtRWOSparepartEntry_RepairItem_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOSparepartEntry_RepairItem_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_RepairItem_PromptBox);
        this.kdtRWOSparepartEntry.getColumn("RepairItem").setEditor(kdtRWOSparepartEntry_RepairItem_CellEditor);
        ObjectValueRender kdtRWOSparepartEntry_RepairItem_OVR = new ObjectValueRender();
        kdtRWOSparepartEntry_RepairItem_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWOSparepartEntry.getColumn("RepairItem").setRenderer(kdtRWOSparepartEntry_RepairItem_OVR);
        			kdtRWOSparepartEntry_RepairItem_PromptBox.addSelectorListener(new SelectorListener() {
			com.kingdee.eas.auto4s.bdm.rsm.client.RepairItemListUI kdtRWOSparepartEntry_RepairItem_PromptBox_F7ListUI = null;
			public void willShow(SelectorEvent e) {
				if (kdtRWOSparepartEntry_RepairItem_PromptBox_F7ListUI == null) {
					try {
						kdtRWOSparepartEntry_RepairItem_PromptBox_F7ListUI = new com.kingdee.eas.auto4s.bdm.rsm.client.RepairItemListUI();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					HashMap ctx = new HashMap();
					ctx.put("bizUIOwner",javax.swing.SwingUtilities.getWindowAncestor(kdtRWOSparepartEntry_RepairItem_PromptBox_F7ListUI));
					kdtRWOSparepartEntry_RepairItem_PromptBox_F7ListUI.setF7Use(true,ctx);
					kdtRWOSparepartEntry_RepairItem_PromptBox.setSelector(kdtRWOSparepartEntry_RepairItem_PromptBox_F7ListUI);
				}
			}
		});
					
        KDTextArea kdtRWOSparepartEntry_Remark_TextArea = new KDTextArea();
        kdtRWOSparepartEntry_Remark_TextArea.setName("kdtRWOSparepartEntry_Remark_TextArea");
        kdtRWOSparepartEntry_Remark_TextArea.setMaxLength(255);
        KDTDefaultCellEditor kdtRWOSparepartEntry_Remark_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_Remark_TextArea);
        this.kdtRWOSparepartEntry.getColumn("Remark").setEditor(kdtRWOSparepartEntry_Remark_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_InstantStore_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_InstantStore_TextField.setName("kdtRWOSparepartEntry_InstantStore_TextField");
        kdtRWOSparepartEntry_InstantStore_TextField.setVisible(true);
        kdtRWOSparepartEntry_InstantStore_TextField.setEditable(true);
        kdtRWOSparepartEntry_InstantStore_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_InstantStore_TextField.setDataType(1);
        	kdtRWOSparepartEntry_InstantStore_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_InstantStore_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_InstantStore_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_InstantStore_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_InstantStore_TextField);
        this.kdtRWOSparepartEntry.getColumn("InstantStore").setEditor(kdtRWOSparepartEntry_InstantStore_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_OldDiscountRate_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_OldDiscountRate_TextField.setName("kdtRWOSparepartEntry_OldDiscountRate_TextField");
        kdtRWOSparepartEntry_OldDiscountRate_TextField.setVisible(true);
        kdtRWOSparepartEntry_OldDiscountRate_TextField.setEditable(true);
        kdtRWOSparepartEntry_OldDiscountRate_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_OldDiscountRate_TextField.setDataType(1);
        	kdtRWOSparepartEntry_OldDiscountRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOSparepartEntry_OldDiscountRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOSparepartEntry_OldDiscountRate_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOSparepartEntry_OldDiscountRate_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_OldDiscountRate_TextField);
        this.kdtRWOSparepartEntry.getColumn("OldDiscountRate").setEditor(kdtRWOSparepartEntry_OldDiscountRate_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_wipLineNo_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_wipLineNo_TextField.setName("kdtRWOSparepartEntry_wipLineNo_TextField");
        kdtRWOSparepartEntry_wipLineNo_TextField.setVisible(true);
        kdtRWOSparepartEntry_wipLineNo_TextField.setEditable(true);
        kdtRWOSparepartEntry_wipLineNo_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_wipLineNo_TextField.setDataType(0);
        KDTDefaultCellEditor kdtRWOSparepartEntry_wipLineNo_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_wipLineNo_TextField);
        this.kdtRWOSparepartEntry.getColumn("wipLineNo").setEditor(kdtRWOSparepartEntry_wipLineNo_CellEditor);
        KDFormattedTextField kdtRWOSparepartEntry_wipFactLineNo_TextField = new KDFormattedTextField();
        kdtRWOSparepartEntry_wipFactLineNo_TextField.setName("kdtRWOSparepartEntry_wipFactLineNo_TextField");
        kdtRWOSparepartEntry_wipFactLineNo_TextField.setVisible(true);
        kdtRWOSparepartEntry_wipFactLineNo_TextField.setEditable(true);
        kdtRWOSparepartEntry_wipFactLineNo_TextField.setHorizontalAlignment(2);
        kdtRWOSparepartEntry_wipFactLineNo_TextField.setDataType(0);
        KDTDefaultCellEditor kdtRWOSparepartEntry_wipFactLineNo_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_wipFactLineNo_TextField);
        this.kdtRWOSparepartEntry.getColumn("wipFactLineNo").setEditor(kdtRWOSparepartEntry_wipFactLineNo_CellEditor);
        KDCheckBox kdtRWOSparepartEntry_isCT_CheckBox = new KDCheckBox();
        kdtRWOSparepartEntry_isCT_CheckBox.setName("kdtRWOSparepartEntry_isCT_CheckBox");
        KDTDefaultCellEditor kdtRWOSparepartEntry_isCT_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_isCT_CheckBox);
        this.kdtRWOSparepartEntry.getColumn("isCT").setEditor(kdtRWOSparepartEntry_isCT_CellEditor);
        KDCheckBox kdtRWOSparepartEntry_isCreateTo_CheckBox = new KDCheckBox();
        kdtRWOSparepartEntry_isCreateTo_CheckBox.setName("kdtRWOSparepartEntry_isCreateTo_CheckBox");
        KDTDefaultCellEditor kdtRWOSparepartEntry_isCreateTo_CellEditor = new KDTDefaultCellEditor(kdtRWOSparepartEntry_isCreateTo_CheckBox);
        this.kdtRWOSparepartEntry.getColumn("isCreateTo").setEditor(kdtRWOSparepartEntry_isCreateTo_CellEditor);
        // kdtRWORepairPkgEntry
		String kdtRWORepairPkgEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol1\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol3\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol4\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol5\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol6\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol7\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol8\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"RepairPkg\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"0\" /><t:Column t:key=\"RepairPkgName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:styleID=\"sCol1\" /><t:Column t:key=\"Classify\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"EffectTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /><t:Column t:key=\"InvalidTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"OldPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /><t:Column t:key=\"Price\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"DiscountAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" t:styleID=\"sCol8\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{RepairPkg}</t:Cell><t:Cell>$Resource{RepairPkgName}</t:Cell><t:Cell>$Resource{Classify}</t:Cell><t:Cell>$Resource{EffectTime}</t:Cell><t:Cell>$Resource{InvalidTime}</t:Cell><t:Cell>$Resource{OldPrice}</t:Cell><t:Cell>$Resource{Price}</t:Cell><t:Cell>$Resource{DiscountAmount}</t:Cell><t:Cell>$Resource{seq}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtRWORepairPkgEntry.setFormatXml(resHelper.translateString("kdtRWORepairPkgEntry",kdtRWORepairPkgEntryStrXML));
        kdtRWORepairPkgEntry.addKDTEditListener(new KDTEditAdapter() {
		public void editStopped(KDTEditEvent e) {
			try {
				kdtRWORepairPkgEntry_Changed(e.getRowIndex(),e.getColIndex());
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        this.kdtRWORepairPkgEntry.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtRWORepairPkgEntry_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtRWORepairPkgEntry.putBindContents("editData",new String[] {"RepairPkg","RepairPkg.name","Classify","EffectTime","InvalidTime","OldPrice","Price","DiscountAmount","seq"});


        this.kdtRWORepairPkgEntry.checkParsed();
        final KDBizPromptBox kdtRWORepairPkgEntry_RepairPkg_PromptBox = new KDBizPromptBox();
        kdtRWORepairPkgEntry_RepairPkg_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairPkgQuery");
        kdtRWORepairPkgEntry_RepairPkg_PromptBox.setVisible(true);
        kdtRWORepairPkgEntry_RepairPkg_PromptBox.setEditable(true);
        kdtRWORepairPkgEntry_RepairPkg_PromptBox.setDisplayFormat("$number$");
        kdtRWORepairPkgEntry_RepairPkg_PromptBox.setEditFormat("$number$");
        kdtRWORepairPkgEntry_RepairPkg_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWORepairPkgEntry_RepairPkg_CellEditor = new KDTDefaultCellEditor(kdtRWORepairPkgEntry_RepairPkg_PromptBox);
        this.kdtRWORepairPkgEntry.getColumn("RepairPkg").setEditor(kdtRWORepairPkgEntry_RepairPkg_CellEditor);
        ObjectValueRender kdtRWORepairPkgEntry_RepairPkg_OVR = new ObjectValueRender();
        kdtRWORepairPkgEntry_RepairPkg_OVR.setFormat(new BizDataFormat("$number$"));
        this.kdtRWORepairPkgEntry.getColumn("RepairPkg").setRenderer(kdtRWORepairPkgEntry_RepairPkg_OVR);
        KDTextField kdtRWORepairPkgEntry_RepairPkgName_TextField = new KDTextField();
        kdtRWORepairPkgEntry_RepairPkgName_TextField.setName("kdtRWORepairPkgEntry_RepairPkgName_TextField");
        kdtRWORepairPkgEntry_RepairPkgName_TextField.setMaxLength(255);
        KDTDefaultCellEditor kdtRWORepairPkgEntry_RepairPkgName_CellEditor = new KDTDefaultCellEditor(kdtRWORepairPkgEntry_RepairPkgName_TextField);
        this.kdtRWORepairPkgEntry.getColumn("RepairPkgName").setEditor(kdtRWORepairPkgEntry_RepairPkgName_CellEditor);
        KDComboBox kdtRWORepairPkgEntry_Classify_ComboBox = new KDComboBox();
        kdtRWORepairPkgEntry_Classify_ComboBox.setName("kdtRWORepairPkgEntry_Classify_ComboBox");
        kdtRWORepairPkgEntry_Classify_ComboBox.setVisible(true);
        kdtRWORepairPkgEntry_Classify_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.RepairPkgTypeEnum").toArray());
        KDTDefaultCellEditor kdtRWORepairPkgEntry_Classify_CellEditor = new KDTDefaultCellEditor(kdtRWORepairPkgEntry_Classify_ComboBox);
        this.kdtRWORepairPkgEntry.getColumn("Classify").setEditor(kdtRWORepairPkgEntry_Classify_CellEditor);
        KDDatePicker kdtRWORepairPkgEntry_EffectTime_DatePicker = new KDDatePicker();
        kdtRWORepairPkgEntry_EffectTime_DatePicker.setName("kdtRWORepairPkgEntry_EffectTime_DatePicker");
        kdtRWORepairPkgEntry_EffectTime_DatePicker.setVisible(true);
        kdtRWORepairPkgEntry_EffectTime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtRWORepairPkgEntry_EffectTime_CellEditor = new KDTDefaultCellEditor(kdtRWORepairPkgEntry_EffectTime_DatePicker);
        this.kdtRWORepairPkgEntry.getColumn("EffectTime").setEditor(kdtRWORepairPkgEntry_EffectTime_CellEditor);
        KDDatePicker kdtRWORepairPkgEntry_InvalidTime_DatePicker = new KDDatePicker();
        kdtRWORepairPkgEntry_InvalidTime_DatePicker.setName("kdtRWORepairPkgEntry_InvalidTime_DatePicker");
        kdtRWORepairPkgEntry_InvalidTime_DatePicker.setVisible(true);
        kdtRWORepairPkgEntry_InvalidTime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtRWORepairPkgEntry_InvalidTime_CellEditor = new KDTDefaultCellEditor(kdtRWORepairPkgEntry_InvalidTime_DatePicker);
        this.kdtRWORepairPkgEntry.getColumn("InvalidTime").setEditor(kdtRWORepairPkgEntry_InvalidTime_CellEditor);
        KDFormattedTextField kdtRWORepairPkgEntry_OldPrice_TextField = new KDFormattedTextField();
        kdtRWORepairPkgEntry_OldPrice_TextField.setName("kdtRWORepairPkgEntry_OldPrice_TextField");
        kdtRWORepairPkgEntry_OldPrice_TextField.setVisible(true);
        kdtRWORepairPkgEntry_OldPrice_TextField.setEditable(true);
        kdtRWORepairPkgEntry_OldPrice_TextField.setHorizontalAlignment(2);
        kdtRWORepairPkgEntry_OldPrice_TextField.setDataType(1);
        	kdtRWORepairPkgEntry_OldPrice_TextField.setMinimumValue(new java.math.BigDecimal("-3.4028234663852886E38"));
        	kdtRWORepairPkgEntry_OldPrice_TextField.setMaximumValue(new java.math.BigDecimal("3.4028234663852886E38"));
        kdtRWORepairPkgEntry_OldPrice_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairPkgEntry_OldPrice_CellEditor = new KDTDefaultCellEditor(kdtRWORepairPkgEntry_OldPrice_TextField);
        this.kdtRWORepairPkgEntry.getColumn("OldPrice").setEditor(kdtRWORepairPkgEntry_OldPrice_CellEditor);
        KDFormattedTextField kdtRWORepairPkgEntry_Price_TextField = new KDFormattedTextField();
        kdtRWORepairPkgEntry_Price_TextField.setName("kdtRWORepairPkgEntry_Price_TextField");
        kdtRWORepairPkgEntry_Price_TextField.setVisible(true);
        kdtRWORepairPkgEntry_Price_TextField.setEditable(true);
        kdtRWORepairPkgEntry_Price_TextField.setHorizontalAlignment(2);
        kdtRWORepairPkgEntry_Price_TextField.setDataType(1);
        	kdtRWORepairPkgEntry_Price_TextField.setMinimumValue(new java.math.BigDecimal("-3.4028234663852886E38"));
        	kdtRWORepairPkgEntry_Price_TextField.setMaximumValue(new java.math.BigDecimal("3.4028234663852886E38"));
        kdtRWORepairPkgEntry_Price_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairPkgEntry_Price_CellEditor = new KDTDefaultCellEditor(kdtRWORepairPkgEntry_Price_TextField);
        this.kdtRWORepairPkgEntry.getColumn("Price").setEditor(kdtRWORepairPkgEntry_Price_CellEditor);
        KDFormattedTextField kdtRWORepairPkgEntry_DiscountAmount_TextField = new KDFormattedTextField();
        kdtRWORepairPkgEntry_DiscountAmount_TextField.setName("kdtRWORepairPkgEntry_DiscountAmount_TextField");
        kdtRWORepairPkgEntry_DiscountAmount_TextField.setVisible(true);
        kdtRWORepairPkgEntry_DiscountAmount_TextField.setEditable(true);
        kdtRWORepairPkgEntry_DiscountAmount_TextField.setHorizontalAlignment(2);
        kdtRWORepairPkgEntry_DiscountAmount_TextField.setDataType(1);
        	kdtRWORepairPkgEntry_DiscountAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWORepairPkgEntry_DiscountAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWORepairPkgEntry_DiscountAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWORepairPkgEntry_DiscountAmount_CellEditor = new KDTDefaultCellEditor(kdtRWORepairPkgEntry_DiscountAmount_TextField);
        this.kdtRWORepairPkgEntry.getColumn("DiscountAmount").setEditor(kdtRWORepairPkgEntry_DiscountAmount_CellEditor);
        // kdtRWOActivityEntry
		String kdtRWOActivityEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol1\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol3\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol4\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol5\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol6\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"ServiceActivity\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"0\" /><t:Column t:key=\"ServiceActivityName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:styleID=\"sCol1\" /><t:Column t:key=\"ActivityType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"BeginTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /><t:Column t:key=\"FinishTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"FeeTotalAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{ServiceActivity}</t:Cell><t:Cell>$Resource{ServiceActivityName}</t:Cell><t:Cell>$Resource{ActivityType}</t:Cell><t:Cell>$Resource{BeginTime}</t:Cell><t:Cell>$Resource{FinishTime}</t:Cell><t:Cell>$Resource{FeeTotalAmount}</t:Cell><t:Cell>$Resource{seq}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtRWOActivityEntry.setFormatXml(resHelper.translateString("kdtRWOActivityEntry",kdtRWOActivityEntryStrXML));
        kdtRWOActivityEntry.addKDTEditListener(new KDTEditAdapter() {
		public void editStopped(KDTEditEvent e) {
			try {
				kdtRWOActivityEntry_Changed(e.getRowIndex(),e.getColIndex());
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        this.kdtRWOActivityEntry.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtRWOActivityEntry_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtRWOActivityEntry.putBindContents("editData",new String[] {"ServiceActivity","ServiceActivity.Name_l2","ActivityType","BeginTime","FinishTime","FeeTotalAmount","seq"});


        this.kdtRWOActivityEntry.checkParsed();
        final KDBizPromptBox kdtRWOActivityEntry_ServiceActivity_PromptBox = new KDBizPromptBox();
        kdtRWOActivityEntry_ServiceActivity_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.rsm.rs.app.ServiceActivityQuery");
        kdtRWOActivityEntry_ServiceActivity_PromptBox.setVisible(true);
        kdtRWOActivityEntry_ServiceActivity_PromptBox.setEditable(true);
        kdtRWOActivityEntry_ServiceActivity_PromptBox.setDisplayFormat("$number$");
        kdtRWOActivityEntry_ServiceActivity_PromptBox.setEditFormat("$number$");
        kdtRWOActivityEntry_ServiceActivity_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOActivityEntry_ServiceActivity_CellEditor = new KDTDefaultCellEditor(kdtRWOActivityEntry_ServiceActivity_PromptBox);
        this.kdtRWOActivityEntry.getColumn("ServiceActivity").setEditor(kdtRWOActivityEntry_ServiceActivity_CellEditor);
        ObjectValueRender kdtRWOActivityEntry_ServiceActivity_OVR = new ObjectValueRender();
        kdtRWOActivityEntry_ServiceActivity_OVR.setFormat(new BizDataFormat("$number$"));
        this.kdtRWOActivityEntry.getColumn("ServiceActivity").setRenderer(kdtRWOActivityEntry_ServiceActivity_OVR);
        			EntityViewInfo evikdtRWOActivityEntry_ServiceActivity_PromptBox = new EntityViewInfo ();
		evikdtRWOActivityEntry_ServiceActivity_PromptBox.setFilter(com.kingdee.eas.framework.FrameWorkUtils.getF7FilterInfoByAuthorizedOrg(com.kingdee.eas.basedata.org.OrgType.getEnum("Admin"),"OrgUnit.id"));
		kdtRWOActivityEntry_ServiceActivity_PromptBox.setEntityViewInfo(evikdtRWOActivityEntry_ServiceActivity_PromptBox);
					
        KDTextField kdtRWOActivityEntry_ServiceActivityName_TextField = new KDTextField();
        kdtRWOActivityEntry_ServiceActivityName_TextField.setName("kdtRWOActivityEntry_ServiceActivityName_TextField");
        kdtRWOActivityEntry_ServiceActivityName_TextField.setMaxLength(255);
        KDTDefaultCellEditor kdtRWOActivityEntry_ServiceActivityName_CellEditor = new KDTDefaultCellEditor(kdtRWOActivityEntry_ServiceActivityName_TextField);
        this.kdtRWOActivityEntry.getColumn("ServiceActivityName").setEditor(kdtRWOActivityEntry_ServiceActivityName_CellEditor);
        KDComboBox kdtRWOActivityEntry_ActivityType_ComboBox = new KDComboBox();
        kdtRWOActivityEntry_ActivityType_ComboBox.setName("kdtRWOActivityEntry_ActivityType_ComboBox");
        kdtRWOActivityEntry_ActivityType_ComboBox.setVisible(true);
        kdtRWOActivityEntry_ActivityType_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.ServiceActivityEnum").toArray());
        KDTDefaultCellEditor kdtRWOActivityEntry_ActivityType_CellEditor = new KDTDefaultCellEditor(kdtRWOActivityEntry_ActivityType_ComboBox);
        this.kdtRWOActivityEntry.getColumn("ActivityType").setEditor(kdtRWOActivityEntry_ActivityType_CellEditor);
        KDDatePicker kdtRWOActivityEntry_BeginTime_DatePicker = new KDDatePicker();
        kdtRWOActivityEntry_BeginTime_DatePicker.setName("kdtRWOActivityEntry_BeginTime_DatePicker");
        kdtRWOActivityEntry_BeginTime_DatePicker.setVisible(true);
        kdtRWOActivityEntry_BeginTime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtRWOActivityEntry_BeginTime_CellEditor = new KDTDefaultCellEditor(kdtRWOActivityEntry_BeginTime_DatePicker);
        this.kdtRWOActivityEntry.getColumn("BeginTime").setEditor(kdtRWOActivityEntry_BeginTime_CellEditor);
        KDDatePicker kdtRWOActivityEntry_FinishTime_DatePicker = new KDDatePicker();
        kdtRWOActivityEntry_FinishTime_DatePicker.setName("kdtRWOActivityEntry_FinishTime_DatePicker");
        kdtRWOActivityEntry_FinishTime_DatePicker.setVisible(true);
        kdtRWOActivityEntry_FinishTime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtRWOActivityEntry_FinishTime_CellEditor = new KDTDefaultCellEditor(kdtRWOActivityEntry_FinishTime_DatePicker);
        this.kdtRWOActivityEntry.getColumn("FinishTime").setEditor(kdtRWOActivityEntry_FinishTime_CellEditor);
        KDFormattedTextField kdtRWOActivityEntry_FeeTotalAmount_TextField = new KDFormattedTextField();
        kdtRWOActivityEntry_FeeTotalAmount_TextField.setName("kdtRWOActivityEntry_FeeTotalAmount_TextField");
        kdtRWOActivityEntry_FeeTotalAmount_TextField.setVisible(true);
        kdtRWOActivityEntry_FeeTotalAmount_TextField.setEditable(true);
        kdtRWOActivityEntry_FeeTotalAmount_TextField.setHorizontalAlignment(2);
        kdtRWOActivityEntry_FeeTotalAmount_TextField.setDataType(1);
        	kdtRWOActivityEntry_FeeTotalAmount_TextField.setMinimumValue(new java.math.BigDecimal("-3.4028234663852886E38"));
        	kdtRWOActivityEntry_FeeTotalAmount_TextField.setMaximumValue(new java.math.BigDecimal("3.4028234663852886E38"));
        kdtRWOActivityEntry_FeeTotalAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOActivityEntry_FeeTotalAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOActivityEntry_FeeTotalAmount_TextField);
        this.kdtRWOActivityEntry.getColumn("FeeTotalAmount").setEditor(kdtRWOActivityEntry_FeeTotalAmount_CellEditor);
        // kdtRWOAttachmentItemEntry
		String kdtRWOAttachmentItemEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol1\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol3\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol4\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol5\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol6\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol8\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"AttaItem\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"0\" /><t:Column t:key=\"AttaItemName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:styleID=\"sCol1\" /><t:Column t:key=\"AttaItemAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"DiscountRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /><t:Column t:key=\"DiscountAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"ARAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /><t:Column t:key=\"Cost\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"Remark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" t:styleID=\"sCol8\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{AttaItem}</t:Cell><t:Cell>$Resource{AttaItemName}</t:Cell><t:Cell>$Resource{AttaItemAmount}</t:Cell><t:Cell>$Resource{DiscountRate}</t:Cell><t:Cell>$Resource{DiscountAmount}</t:Cell><t:Cell>$Resource{ARAmount}</t:Cell><t:Cell>$Resource{Cost}</t:Cell><t:Cell>$Resource{Remark}</t:Cell><t:Cell>$Resource{seq}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtRWOAttachmentItemEntry.setFormatXml(resHelper.translateString("kdtRWOAttachmentItemEntry",kdtRWOAttachmentItemEntryStrXML));
        kdtRWOAttachmentItemEntry.addKDTEditListener(new KDTEditAdapter() {
		public void editStopped(KDTEditEvent e) {
			try {
				kdtRWOAttachmentItemEntry_Changed(e.getRowIndex(),e.getColIndex());
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});


                this.kdtRWOAttachmentItemEntry.putBindContents("editData",new String[] {"AttaItem","AttaItem.name","AttaItemAmount","DiscountRate","DiscountAmount","ARAmount","Cost","Remark","seq"});


        this.kdtRWOAttachmentItemEntry.checkParsed();
        final KDBizPromptBox kdtRWOAttachmentItemEntry_AttaItem_PromptBox = new KDBizPromptBox();
        kdtRWOAttachmentItemEntry_AttaItem_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.AttachmentItemQuery");
        kdtRWOAttachmentItemEntry_AttaItem_PromptBox.setVisible(true);
        kdtRWOAttachmentItemEntry_AttaItem_PromptBox.setEditable(true);
        kdtRWOAttachmentItemEntry_AttaItem_PromptBox.setDisplayFormat("$number$");
        kdtRWOAttachmentItemEntry_AttaItem_PromptBox.setEditFormat("$number$");
        kdtRWOAttachmentItemEntry_AttaItem_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOAttachmentItemEntry_AttaItem_CellEditor = new KDTDefaultCellEditor(kdtRWOAttachmentItemEntry_AttaItem_PromptBox);
        this.kdtRWOAttachmentItemEntry.getColumn("AttaItem").setEditor(kdtRWOAttachmentItemEntry_AttaItem_CellEditor);
        ObjectValueRender kdtRWOAttachmentItemEntry_AttaItem_OVR = new ObjectValueRender();
        kdtRWOAttachmentItemEntry_AttaItem_OVR.setFormat(new BizDataFormat("$number$"));
        this.kdtRWOAttachmentItemEntry.getColumn("AttaItem").setRenderer(kdtRWOAttachmentItemEntry_AttaItem_OVR);
        KDFormattedTextField kdtRWOAttachmentItemEntry_AttaItemAmount_TextField = new KDFormattedTextField();
        kdtRWOAttachmentItemEntry_AttaItemAmount_TextField.setName("kdtRWOAttachmentItemEntry_AttaItemAmount_TextField");
        kdtRWOAttachmentItemEntry_AttaItemAmount_TextField.setVisible(true);
        kdtRWOAttachmentItemEntry_AttaItemAmount_TextField.setEditable(true);
        kdtRWOAttachmentItemEntry_AttaItemAmount_TextField.setHorizontalAlignment(2);
        kdtRWOAttachmentItemEntry_AttaItemAmount_TextField.setDataType(1);
        	kdtRWOAttachmentItemEntry_AttaItemAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOAttachmentItemEntry_AttaItemAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOAttachmentItemEntry_AttaItemAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOAttachmentItemEntry_AttaItemAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOAttachmentItemEntry_AttaItemAmount_TextField);
        this.kdtRWOAttachmentItemEntry.getColumn("AttaItemAmount").setEditor(kdtRWOAttachmentItemEntry_AttaItemAmount_CellEditor);
        KDFormattedTextField kdtRWOAttachmentItemEntry_DiscountRate_TextField = new KDFormattedTextField();
        kdtRWOAttachmentItemEntry_DiscountRate_TextField.setName("kdtRWOAttachmentItemEntry_DiscountRate_TextField");
        kdtRWOAttachmentItemEntry_DiscountRate_TextField.setVisible(true);
        kdtRWOAttachmentItemEntry_DiscountRate_TextField.setEditable(true);
        kdtRWOAttachmentItemEntry_DiscountRate_TextField.setHorizontalAlignment(2);
        kdtRWOAttachmentItemEntry_DiscountRate_TextField.setDataType(1);
        	kdtRWOAttachmentItemEntry_DiscountRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOAttachmentItemEntry_DiscountRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOAttachmentItemEntry_DiscountRate_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOAttachmentItemEntry_DiscountRate_CellEditor = new KDTDefaultCellEditor(kdtRWOAttachmentItemEntry_DiscountRate_TextField);
        this.kdtRWOAttachmentItemEntry.getColumn("DiscountRate").setEditor(kdtRWOAttachmentItemEntry_DiscountRate_CellEditor);
        KDFormattedTextField kdtRWOAttachmentItemEntry_DiscountAmount_TextField = new KDFormattedTextField();
        kdtRWOAttachmentItemEntry_DiscountAmount_TextField.setName("kdtRWOAttachmentItemEntry_DiscountAmount_TextField");
        kdtRWOAttachmentItemEntry_DiscountAmount_TextField.setVisible(true);
        kdtRWOAttachmentItemEntry_DiscountAmount_TextField.setEditable(true);
        kdtRWOAttachmentItemEntry_DiscountAmount_TextField.setHorizontalAlignment(2);
        kdtRWOAttachmentItemEntry_DiscountAmount_TextField.setDataType(1);
        	kdtRWOAttachmentItemEntry_DiscountAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOAttachmentItemEntry_DiscountAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOAttachmentItemEntry_DiscountAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOAttachmentItemEntry_DiscountAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOAttachmentItemEntry_DiscountAmount_TextField);
        this.kdtRWOAttachmentItemEntry.getColumn("DiscountAmount").setEditor(kdtRWOAttachmentItemEntry_DiscountAmount_CellEditor);
        KDFormattedTextField kdtRWOAttachmentItemEntry_ARAmount_TextField = new KDFormattedTextField();
        kdtRWOAttachmentItemEntry_ARAmount_TextField.setName("kdtRWOAttachmentItemEntry_ARAmount_TextField");
        kdtRWOAttachmentItemEntry_ARAmount_TextField.setVisible(true);
        kdtRWOAttachmentItemEntry_ARAmount_TextField.setEditable(true);
        kdtRWOAttachmentItemEntry_ARAmount_TextField.setHorizontalAlignment(2);
        kdtRWOAttachmentItemEntry_ARAmount_TextField.setDataType(1);
        	kdtRWOAttachmentItemEntry_ARAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOAttachmentItemEntry_ARAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOAttachmentItemEntry_ARAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOAttachmentItemEntry_ARAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOAttachmentItemEntry_ARAmount_TextField);
        this.kdtRWOAttachmentItemEntry.getColumn("ARAmount").setEditor(kdtRWOAttachmentItemEntry_ARAmount_CellEditor);
        KDFormattedTextField kdtRWOAttachmentItemEntry_Cost_TextField = new KDFormattedTextField();
        kdtRWOAttachmentItemEntry_Cost_TextField.setName("kdtRWOAttachmentItemEntry_Cost_TextField");
        kdtRWOAttachmentItemEntry_Cost_TextField.setVisible(true);
        kdtRWOAttachmentItemEntry_Cost_TextField.setEditable(true);
        kdtRWOAttachmentItemEntry_Cost_TextField.setHorizontalAlignment(2);
        kdtRWOAttachmentItemEntry_Cost_TextField.setDataType(1);
        	kdtRWOAttachmentItemEntry_Cost_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOAttachmentItemEntry_Cost_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOAttachmentItemEntry_Cost_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOAttachmentItemEntry_Cost_CellEditor = new KDTDefaultCellEditor(kdtRWOAttachmentItemEntry_Cost_TextField);
        this.kdtRWOAttachmentItemEntry.getColumn("Cost").setEditor(kdtRWOAttachmentItemEntry_Cost_CellEditor);
        KDTextArea kdtRWOAttachmentItemEntry_Remark_TextArea = new KDTextArea();
        kdtRWOAttachmentItemEntry_Remark_TextArea.setName("kdtRWOAttachmentItemEntry_Remark_TextArea");
        kdtRWOAttachmentItemEntry_Remark_TextArea.setMaxLength(255);
        KDTDefaultCellEditor kdtRWOAttachmentItemEntry_Remark_CellEditor = new KDTDefaultCellEditor(kdtRWOAttachmentItemEntry_Remark_TextArea);
        this.kdtRWOAttachmentItemEntry.getColumn("Remark").setEditor(kdtRWOAttachmentItemEntry_Remark_CellEditor);
        // kdtRWOTotalAmountEntry
		String kdtRWOTotalAmountEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol1\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol3\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol4\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol5\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol6\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol7\"><c:Protection locked=\"true\" hidden=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol8\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"SettleObject\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:styleID=\"sCol0\" /><t:Column t:key=\"AmountClassify\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:styleID=\"sCol1\" /><t:Column t:key=\"OldAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"DiscountRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /><t:Column t:key=\"SettleAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"NoTaxAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /><t:Column t:key=\"TaxAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"ARAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" t:styleID=\"sCol8\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{SettleObject}</t:Cell><t:Cell>$Resource{AmountClassify}</t:Cell><t:Cell>$Resource{OldAmount}</t:Cell><t:Cell>$Resource{DiscountRate}</t:Cell><t:Cell>$Resource{SettleAmount}</t:Cell><t:Cell>$Resource{NoTaxAmount}</t:Cell><t:Cell>$Resource{TaxAmount}</t:Cell><t:Cell>$Resource{ARAmount}</t:Cell><t:Cell>$Resource{seq}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtRWOTotalAmountEntry.setFormatXml(resHelper.translateString("kdtRWOTotalAmountEntry",kdtRWOTotalAmountEntryStrXML));

                this.kdtRWOTotalAmountEntry.putBindContents("editData",new String[] {"SettleObject","AmountClassify","OldAmount","DiscountRate","SettleAmount","NoTaxAmount","TaxAmount","ARAmount","seq"});


        this.kdtRWOTotalAmountEntry.checkParsed();
        KDComboBox kdtRWOTotalAmountEntry_SettleObject_ComboBox = new KDComboBox();
        kdtRWOTotalAmountEntry_SettleObject_ComboBox.setName("kdtRWOTotalAmountEntry_SettleObject_ComboBox");
        kdtRWOTotalAmountEntry_SettleObject_ComboBox.setVisible(true);
        kdtRWOTotalAmountEntry_SettleObject_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum").toArray());
        KDTDefaultCellEditor kdtRWOTotalAmountEntry_SettleObject_CellEditor = new KDTDefaultCellEditor(kdtRWOTotalAmountEntry_SettleObject_ComboBox);
        this.kdtRWOTotalAmountEntry.getColumn("SettleObject").setEditor(kdtRWOTotalAmountEntry_SettleObject_CellEditor);
        KDComboBox kdtRWOTotalAmountEntry_AmountClassify_ComboBox = new KDComboBox();
        kdtRWOTotalAmountEntry_AmountClassify_ComboBox.setName("kdtRWOTotalAmountEntry_AmountClassify_ComboBox");
        kdtRWOTotalAmountEntry_AmountClassify_ComboBox.setVisible(true);
        kdtRWOTotalAmountEntry_AmountClassify_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.AmountClassifyEnum").toArray());
        KDTDefaultCellEditor kdtRWOTotalAmountEntry_AmountClassify_CellEditor = new KDTDefaultCellEditor(kdtRWOTotalAmountEntry_AmountClassify_ComboBox);
        this.kdtRWOTotalAmountEntry.getColumn("AmountClassify").setEditor(kdtRWOTotalAmountEntry_AmountClassify_CellEditor);
        KDFormattedTextField kdtRWOTotalAmountEntry_OldAmount_TextField = new KDFormattedTextField();
        kdtRWOTotalAmountEntry_OldAmount_TextField.setName("kdtRWOTotalAmountEntry_OldAmount_TextField");
        kdtRWOTotalAmountEntry_OldAmount_TextField.setVisible(true);
        kdtRWOTotalAmountEntry_OldAmount_TextField.setEditable(true);
        kdtRWOTotalAmountEntry_OldAmount_TextField.setHorizontalAlignment(2);
        kdtRWOTotalAmountEntry_OldAmount_TextField.setDataType(1);
        	kdtRWOTotalAmountEntry_OldAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOTotalAmountEntry_OldAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOTotalAmountEntry_OldAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOTotalAmountEntry_OldAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOTotalAmountEntry_OldAmount_TextField);
        this.kdtRWOTotalAmountEntry.getColumn("OldAmount").setEditor(kdtRWOTotalAmountEntry_OldAmount_CellEditor);
        KDFormattedTextField kdtRWOTotalAmountEntry_DiscountRate_TextField = new KDFormattedTextField();
        kdtRWOTotalAmountEntry_DiscountRate_TextField.setName("kdtRWOTotalAmountEntry_DiscountRate_TextField");
        kdtRWOTotalAmountEntry_DiscountRate_TextField.setVisible(true);
        kdtRWOTotalAmountEntry_DiscountRate_TextField.setEditable(true);
        kdtRWOTotalAmountEntry_DiscountRate_TextField.setHorizontalAlignment(2);
        kdtRWOTotalAmountEntry_DiscountRate_TextField.setDataType(1);
        	kdtRWOTotalAmountEntry_DiscountRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOTotalAmountEntry_DiscountRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOTotalAmountEntry_DiscountRate_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOTotalAmountEntry_DiscountRate_CellEditor = new KDTDefaultCellEditor(kdtRWOTotalAmountEntry_DiscountRate_TextField);
        this.kdtRWOTotalAmountEntry.getColumn("DiscountRate").setEditor(kdtRWOTotalAmountEntry_DiscountRate_CellEditor);
        KDFormattedTextField kdtRWOTotalAmountEntry_SettleAmount_TextField = new KDFormattedTextField();
        kdtRWOTotalAmountEntry_SettleAmount_TextField.setName("kdtRWOTotalAmountEntry_SettleAmount_TextField");
        kdtRWOTotalAmountEntry_SettleAmount_TextField.setVisible(true);
        kdtRWOTotalAmountEntry_SettleAmount_TextField.setEditable(true);
        kdtRWOTotalAmountEntry_SettleAmount_TextField.setHorizontalAlignment(2);
        kdtRWOTotalAmountEntry_SettleAmount_TextField.setDataType(1);
        	kdtRWOTotalAmountEntry_SettleAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOTotalAmountEntry_SettleAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOTotalAmountEntry_SettleAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOTotalAmountEntry_SettleAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOTotalAmountEntry_SettleAmount_TextField);
        this.kdtRWOTotalAmountEntry.getColumn("SettleAmount").setEditor(kdtRWOTotalAmountEntry_SettleAmount_CellEditor);
        KDFormattedTextField kdtRWOTotalAmountEntry_NoTaxAmount_TextField = new KDFormattedTextField();
        kdtRWOTotalAmountEntry_NoTaxAmount_TextField.setName("kdtRWOTotalAmountEntry_NoTaxAmount_TextField");
        kdtRWOTotalAmountEntry_NoTaxAmount_TextField.setVisible(true);
        kdtRWOTotalAmountEntry_NoTaxAmount_TextField.setEditable(true);
        kdtRWOTotalAmountEntry_NoTaxAmount_TextField.setHorizontalAlignment(2);
        kdtRWOTotalAmountEntry_NoTaxAmount_TextField.setDataType(1);
        	kdtRWOTotalAmountEntry_NoTaxAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOTotalAmountEntry_NoTaxAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOTotalAmountEntry_NoTaxAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOTotalAmountEntry_NoTaxAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOTotalAmountEntry_NoTaxAmount_TextField);
        this.kdtRWOTotalAmountEntry.getColumn("NoTaxAmount").setEditor(kdtRWOTotalAmountEntry_NoTaxAmount_CellEditor);
        KDFormattedTextField kdtRWOTotalAmountEntry_TaxAmount_TextField = new KDFormattedTextField();
        kdtRWOTotalAmountEntry_TaxAmount_TextField.setName("kdtRWOTotalAmountEntry_TaxAmount_TextField");
        kdtRWOTotalAmountEntry_TaxAmount_TextField.setVisible(true);
        kdtRWOTotalAmountEntry_TaxAmount_TextField.setEditable(true);
        kdtRWOTotalAmountEntry_TaxAmount_TextField.setHorizontalAlignment(2);
        kdtRWOTotalAmountEntry_TaxAmount_TextField.setDataType(1);
        	kdtRWOTotalAmountEntry_TaxAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOTotalAmountEntry_TaxAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOTotalAmountEntry_TaxAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOTotalAmountEntry_TaxAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOTotalAmountEntry_TaxAmount_TextField);
        this.kdtRWOTotalAmountEntry.getColumn("TaxAmount").setEditor(kdtRWOTotalAmountEntry_TaxAmount_CellEditor);
        KDFormattedTextField kdtRWOTotalAmountEntry_ARAmount_TextField = new KDFormattedTextField();
        kdtRWOTotalAmountEntry_ARAmount_TextField.setName("kdtRWOTotalAmountEntry_ARAmount_TextField");
        kdtRWOTotalAmountEntry_ARAmount_TextField.setVisible(true);
        kdtRWOTotalAmountEntry_ARAmount_TextField.setEditable(true);
        kdtRWOTotalAmountEntry_ARAmount_TextField.setHorizontalAlignment(2);
        kdtRWOTotalAmountEntry_ARAmount_TextField.setDataType(1);
        	kdtRWOTotalAmountEntry_ARAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOTotalAmountEntry_ARAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOTotalAmountEntry_ARAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOTotalAmountEntry_ARAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOTotalAmountEntry_ARAmount_TextField);
        this.kdtRWOTotalAmountEntry.getColumn("ARAmount").setEditor(kdtRWOTotalAmountEntry_ARAmount_CellEditor);
        // kdtRepairBreakEntry
		String kdtRepairBreakEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol1\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol5\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"StartTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:styleID=\"sCol0\" /><t:Column t:key=\"Finishtime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:styleID=\"sCol1\" /><t:Column t:key=\"Reason\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /><t:Column t:key=\"BreakType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" /><t:Column t:key=\"Remark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{StartTime}</t:Cell><t:Cell>$Resource{Finishtime}</t:Cell><t:Cell>$Resource{Reason}</t:Cell><t:Cell>$Resource{BreakType}</t:Cell><t:Cell>$Resource{Remark}</t:Cell><t:Cell>$Resource{seq}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtRepairBreakEntry.setFormatXml(resHelper.translateString("kdtRepairBreakEntry",kdtRepairBreakEntryStrXML));

                this.kdtRepairBreakEntry.putBindContents("editData",new String[] {"StartTime","Finishtime","Reason","BreakType","Remark","seq"});


        this.kdtRepairBreakEntry.checkParsed();
        KDDatePicker kdtRepairBreakEntry_StartTime_DatePicker = new KDDatePicker();
        kdtRepairBreakEntry_StartTime_DatePicker.setName("kdtRepairBreakEntry_StartTime_DatePicker");
        kdtRepairBreakEntry_StartTime_DatePicker.setVisible(true);
        kdtRepairBreakEntry_StartTime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtRepairBreakEntry_StartTime_CellEditor = new KDTDefaultCellEditor(kdtRepairBreakEntry_StartTime_DatePicker);
        this.kdtRepairBreakEntry.getColumn("StartTime").setEditor(kdtRepairBreakEntry_StartTime_CellEditor);
        KDDatePicker kdtRepairBreakEntry_Finishtime_DatePicker = new KDDatePicker();
        kdtRepairBreakEntry_Finishtime_DatePicker.setName("kdtRepairBreakEntry_Finishtime_DatePicker");
        kdtRepairBreakEntry_Finishtime_DatePicker.setVisible(true);
        kdtRepairBreakEntry_Finishtime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtRepairBreakEntry_Finishtime_CellEditor = new KDTDefaultCellEditor(kdtRepairBreakEntry_Finishtime_DatePicker);
        this.kdtRepairBreakEntry.getColumn("Finishtime").setEditor(kdtRepairBreakEntry_Finishtime_CellEditor);
        KDTextArea kdtRepairBreakEntry_Reason_TextArea = new KDTextArea();
        kdtRepairBreakEntry_Reason_TextArea.setName("kdtRepairBreakEntry_Reason_TextArea");
        kdtRepairBreakEntry_Reason_TextArea.setMaxLength(255);
        KDTDefaultCellEditor kdtRepairBreakEntry_Reason_CellEditor = new KDTDefaultCellEditor(kdtRepairBreakEntry_Reason_TextArea);
        this.kdtRepairBreakEntry.getColumn("Reason").setEditor(kdtRepairBreakEntry_Reason_CellEditor);
        final KDBizPromptBox kdtRepairBreakEntry_BreakType_PromptBox = new KDBizPromptBox();
        kdtRepairBreakEntry_BreakType_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.BreakTypeQuery");
        kdtRepairBreakEntry_BreakType_PromptBox.setVisible(true);
        kdtRepairBreakEntry_BreakType_PromptBox.setEditable(true);
        kdtRepairBreakEntry_BreakType_PromptBox.setDisplayFormat("$number$");
        kdtRepairBreakEntry_BreakType_PromptBox.setEditFormat("$number$");
        kdtRepairBreakEntry_BreakType_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRepairBreakEntry_BreakType_CellEditor = new KDTDefaultCellEditor(kdtRepairBreakEntry_BreakType_PromptBox);
        this.kdtRepairBreakEntry.getColumn("BreakType").setEditor(kdtRepairBreakEntry_BreakType_CellEditor);
        ObjectValueRender kdtRepairBreakEntry_BreakType_OVR = new ObjectValueRender();
        kdtRepairBreakEntry_BreakType_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRepairBreakEntry.getColumn("BreakType").setRenderer(kdtRepairBreakEntry_BreakType_OVR);
        KDTextArea kdtRepairBreakEntry_Remark_TextArea = new KDTextArea();
        kdtRepairBreakEntry_Remark_TextArea.setName("kdtRepairBreakEntry_Remark_TextArea");
        kdtRepairBreakEntry_Remark_TextArea.setMaxLength(255);
        KDTDefaultCellEditor kdtRepairBreakEntry_Remark_CellEditor = new KDTDefaultCellEditor(kdtRepairBreakEntry_Remark_TextArea);
        this.kdtRepairBreakEntry.getColumn("Remark").setEditor(kdtRepairBreakEntry_Remark_CellEditor);
        // txtOldID		
        this.txtOldID.setHorizontalAlignment(2);		
        this.txtOldID.setMaxLength(44);		
        this.txtOldID.setRequired(false);
        // txtRepairBookingID		
        this.txtRepairBookingID.setHorizontalAlignment(2);		
        this.txtRepairBookingID.setMaxLength(44);		
        this.txtRepairBookingID.setRequired(false);
        // ReturnRepair		
        this.ReturnRepair.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.FReturnRepairEnum").toArray());		
        this.ReturnRepair.setRequired(false);
        // separator9
        // separator4
        // btnAdd
        this.btnAdd.setAction((IItemAction)ActionProxyFactory.getProxy(actionAdd, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAdd.setText(resHelper.getString("btnAdd.text"));		
        this.btnAdd.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_upenumnew"));
        // btnEnterAdd
        this.btnEnterAdd.setAction((IItemAction)ActionProxyFactory.getProxy(actionEnterAdd, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnEnterAdd.setText(resHelper.getString("btnEnterAdd.text"));		
        this.btnEnterAdd.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_execute"));
        // btnSuggest
        this.btnSuggest.setAction((IItemAction)ActionProxyFactory.getProxy(actionSuggest, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnSuggest.setText(resHelper.getString("btnSuggest.text"));		
        this.btnSuggest.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_creat"));
        // separator5
        // btnInvalid
        this.btnInvalid.setAction((IItemAction)ActionProxyFactory.getProxy(actionInvalid, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnInvalid.setText(resHelper.getString("btnInvalid.text"));		
        this.btnInvalid.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_blankout"));
        // btnUninvalid
        this.btnUninvalid.setAction((IItemAction)ActionProxyFactory.getProxy(actionUninvalid, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnUninvalid.setText(resHelper.getString("btnUninvalid.text"));		
        this.btnUninvalid.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_fblankout"));
        // separator6
        // btnBo
        this.btnBo.setAction((IItemAction)ActionProxyFactory.getProxy(actionBo, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnBo.setText(resHelper.getString("btnBo.text"));		
        this.btnBo.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_review"));
        // btnCancelBo
        this.btnCancelBo.setAction((IItemAction)ActionProxyFactory.getProxy(actionCancelBo, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCancelBo.setText(resHelper.getString("btnCancelBo.text"));		
        this.btnCancelBo.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_unreview"));
        // btnDispatching
        this.btnDispatching.setAction((IItemAction)ActionProxyFactory.getProxy(actionDispatching, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnDispatching.setText(resHelper.getString("btnDispatching.text"));		
        this.btnDispatching.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_handworkcollate"));
        // kDWorkButton9
        this.kDWorkButton9.setAction((IItemAction)ActionProxyFactory.getProxy(actionItemIssue, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDWorkButton9.setText(resHelper.getString("kDWorkButton9.text"));		
        this.kDWorkButton9.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_output"));
        // btnTimeBooking
        this.btnTimeBooking.setAction((IItemAction)ActionProxyFactory.getProxy(actionTimeBooking, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnTimeBooking.setText(resHelper.getString("btnTimeBooking.text"));		
        this.btnTimeBooking.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_timingrefer"));
        // btnAdjust
        this.btnAdjust.setAction((IItemAction)ActionProxyFactory.getProxy(actionAdjust, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAdjust.setText(resHelper.getString("btnAdjust.text"));		
        this.btnAdjust.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_balancecheck"));
        // separator8
        // btnCustomerInfo
        this.btnCustomerInfo.setAction((IItemAction)ActionProxyFactory.getProxy(actionCustomer, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCustomerInfo.setText(resHelper.getString("btnCustomerInfo.text"));		
        this.btnCustomerInfo.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_lookup"));
        // btnVehicleInfo
        this.btnVehicleInfo.setAction((IItemAction)ActionProxyFactory.getProxy(actionVehicle, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnVehicleInfo.setText(resHelper.getString("btnVehicleInfo.text"));		
        this.btnVehicleInfo.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_lookup"));
        // btnRepairHistory
        this.btnRepairHistory.setAction((IItemAction)ActionProxyFactory.getProxy(actionHistory, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnRepairHistory.setText(resHelper.getString("btnRepairHistory.text"));		
        this.btnRepairHistory.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_encryptview"));
        // btnCallBack
        this.btnCallBack.setAction((IItemAction)ActionProxyFactory.getProxy(actionCallBack, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCallBack.setText(resHelper.getString("btnCallBack.text"));		
        this.btnCallBack.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_lookup"));
        // btnCustomerComplain
        this.btnCustomerComplain.setAction((IItemAction)ActionProxyFactory.getProxy(actionComplain, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCustomerComplain.setText(resHelper.getString("btnCustomerComplain.text"));		
        this.btnCustomerComplain.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_lookup"));
        // separator10
        // btnPricePrint
        this.btnPricePrint.setAction((IItemAction)ActionProxyFactory.getProxy(actionPrintPrice, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnPricePrint.setText(resHelper.getString("btnPricePrint.text"));		
        this.btnPricePrint.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_print"));
        // btnCancelAssign
        this.btnCancelAssign.setAction((IItemAction)ActionProxyFactory.getProxy(actionCancelAssign, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCancelAssign.setText(resHelper.getString("btnCancelAssign.text"));
        // btnUnTimeBooking
        this.btnUnTimeBooking.setAction((IItemAction)ActionProxyFactory.getProxy(actionUnTimeBooking, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnUnTimeBooking.setText(resHelper.getString("btnUnTimeBooking.text"));
        // kDBtnRefresh
        this.kDBtnRefresh.setAction((IItemAction)ActionProxyFactory.getProxy(actionRefresh, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDBtnRefresh.setText(resHelper.getString("kDBtnRefresh.text"));
        // btnAddCustomer
        this.btnAddCustomer.setAction((IItemAction)ActionProxyFactory.getProxy(actionAddCustomer, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAddCustomer.setText(resHelper.getString("btnAddCustomer.text"));
        // btnAddVehicle
        this.btnAddVehicle.setAction((IItemAction)ActionProxyFactory.getProxy(actionAddVehicle, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAddVehicle.setText(resHelper.getString("btnAddVehicle.text"));
        // kDViewVipCard
        this.kDViewVipCard.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewVipCard, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDViewVipCard.setText(resHelper.getString("kDViewVipCard.text"));
        // kDViewVehicleAdvice
        this.kDViewVehicleAdvice.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewVehicleAdvice, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDViewVehicleAdvice.setText(resHelper.getString("kDViewVehicleAdvice.text"));
        // btnViewVipPrefer
        this.btnViewVipPrefer.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewVipPrefer, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnViewVipPrefer.setText(resHelper.getString("btnViewVipPrefer.text"));
        // menuItemAudit		
        this.menuItemAudit.setText(resHelper.getString("menuItemAudit.text"));
        // menuItemUnAudit		
        this.menuItemUnAudit.setText(resHelper.getString("menuItemUnAudit.text"));
        // kDTabbedPane1
        // kDPBizInfo
        // kDPRepairInfo
        // kDPVehicleInfo
        // kDPHideField
        // contRemark		
        this.contRemark.setBoundLabelText(resHelper.getString("contRemark.boundLabelText"));		
        this.contRemark.setBoundLabelLength(100);		
        this.contRemark.setBoundLabelUnderline(true);		
        this.contRemark.setVisible(true);
        // contcustomInfo		
        this.contcustomInfo.setBoundLabelText(resHelper.getString("contcustomInfo.boundLabelText"));		
        this.contcustomInfo.setBoundLabelLength(100);		
        this.contcustomInfo.setBoundLabelUnderline(true);		
        this.contcustomInfo.setVisible(true);
        // contsaleType		
        this.contsaleType.setBoundLabelText(resHelper.getString("contsaleType.boundLabelText"));		
        this.contsaleType.setBoundLabelLength(100);		
        this.contsaleType.setBoundLabelUnderline(true);		
        this.contsaleType.setVisible(true);
        // contCustomerAccount		
        this.contCustomerAccount.setBoundLabelText(resHelper.getString("contCustomerAccount.boundLabelText"));		
        this.contCustomerAccount.setBoundLabelLength(100);		
        this.contCustomerAccount.setBoundLabelUnderline(true);		
        this.contCustomerAccount.setVisible(true);
        // contcustomerAccountName		
        this.contcustomerAccountName.setBoundLabelText(resHelper.getString("contcustomerAccountName.boundLabelText"));		
        this.contcustomerAccountName.setBoundLabelLength(100);		
        this.contcustomerAccountName.setBoundLabelUnderline(true);		
        this.contcustomerAccountName.setVisible(true);
        // contgaDept		
        this.contgaDept.setBoundLabelText(resHelper.getString("contgaDept.boundLabelText"));		
        this.contgaDept.setBoundLabelLength(100);		
        this.contgaDept.setBoundLabelUnderline(true);		
        this.contgaDept.setVisible(true);
        // scrollPaneRemark
        // txtRemark		
        this.txtRemark.setRequired(false);		
        this.txtRemark.setMaxLength(255);
        // scrollPanecustomInfo
        // txtcustomInfo		
        this.txtcustomInfo.setRequired(false);		
        this.txtcustomInfo.setMaxLength(255);
        // txtsaleType		
        this.txtsaleType.setHorizontalAlignment(2);		
        this.txtsaleType.setMaxLength(100);		
        this.txtsaleType.setRequired(false);		
        this.txtsaleType.setEnabled(false);
        // prmtCustomerAccount		
        this.prmtCustomerAccount.setQueryInfo("com.kingdee.eas.ga.rs.app.CustomerAccountQuery");		
        this.prmtCustomerAccount.setEditable(true);		
        this.prmtCustomerAccount.setDisplayFormat("$number$");		
        this.prmtCustomerAccount.setEditFormat("$number$");		
        this.prmtCustomerAccount.setCommitFormat("$number$");		
        this.prmtCustomerAccount.setRequired(true);
        prmtCustomerAccount.addDataChangeListener(new DataChangeListener() {
		public void dataChanged(DataChangeEvent e) {
			try {
				prmtCustomerAccount_Changed();
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        // txtcustomerAccountName		
        this.txtcustomerAccountName.setHorizontalAlignment(2);		
        this.txtcustomerAccountName.setMaxLength(80);		
        this.txtcustomerAccountName.setRequired(false);		
        this.txtcustomerAccountName.setEnabled(false);
        // txtgaDept		
        this.txtgaDept.setVisible(true);		
        this.txtgaDept.setHorizontalAlignment(2);		
        this.txtgaDept.setMaxLength(100);		
        this.txtgaDept.setRequired(false);
        // contdept		
        this.contdept.setBoundLabelText(resHelper.getString("contdept.boundLabelText"));		
        this.contdept.setBoundLabelLength(100);		
        this.contdept.setBoundLabelUnderline(true);		
        this.contdept.setVisible(true);
        // prmtdept		
        this.prmtdept.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");		
        this.prmtdept.setEditable(true);		
        this.prmtdept.setDisplayFormat("$name$");		
        this.prmtdept.setEditFormat("$number$");		
        this.prmtdept.setCommitFormat("$number$");		
        this.prmtdept.setRequired(false);
        // kDPRwoItemSp
        // kdtRWOItemSpEntry
		String kdtRWOItemSpEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol1\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol3\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol4\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol8\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol10\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol11\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol12\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol13\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol14\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol15\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol16\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol17\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol18\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol19\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol20\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol24\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol26\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"t\" t:width=\"30\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol1\" /><t:Column t:key=\"itemspNum\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol2\" /><t:Column t:key=\"repairItem\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /><t:Column t:key=\"material\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"itemspName\" t:width=\"250\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" /><t:Column t:key=\"taocan\" t:width=\"50\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"w\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"settlementObject\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol8\" /><t:Column t:key=\"isCT\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"qty\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol10\" /><t:Column t:key=\"unIssueQty\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol11\" /><t:Column t:key=\"issueQty\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol12\" /><t:Column t:key=\"price\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol13\" /><t:Column t:key=\"discountRate\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol14\" /><t:Column t:key=\"amount\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol15\" /><t:Column t:key=\"taxRate\" t:width=\"60\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol16\" /><t:Column t:key=\"i\" t:width=\"40\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol17\" /><t:Column t:key=\"wipFactLineNo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol18\" /><t:Column t:key=\"wipLineNo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol19\" /><t:Column t:key=\"isCreateTo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol20\" /><t:Column t:key=\"saleType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"rts\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"billNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"postingDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol24\" /><t:Column t:key=\"isAPSettle\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"costAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol26\" /><t:Column t:key=\"account\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"isDelete\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{t}</t:Cell><t:Cell>$Resource{itemspNum}</t:Cell><t:Cell>$Resource{repairItem}</t:Cell><t:Cell>$Resource{material}</t:Cell><t:Cell>$Resource{itemspName}</t:Cell><t:Cell>$Resource{taocan}</t:Cell><t:Cell>$Resource{w}</t:Cell><t:Cell>$Resource{settlementObject}</t:Cell><t:Cell>$Resource{isCT}</t:Cell><t:Cell>$Resource{qty}</t:Cell><t:Cell>$Resource{unIssueQty}</t:Cell><t:Cell>$Resource{issueQty}</t:Cell><t:Cell>$Resource{price}</t:Cell><t:Cell>$Resource{discountRate}</t:Cell><t:Cell>$Resource{amount}</t:Cell><t:Cell>$Resource{taxRate}</t:Cell><t:Cell>$Resource{i}</t:Cell><t:Cell>$Resource{wipFactLineNo}</t:Cell><t:Cell>$Resource{wipLineNo}</t:Cell><t:Cell>$Resource{isCreateTo}</t:Cell><t:Cell>$Resource{saleType}</t:Cell><t:Cell>$Resource{rts}</t:Cell><t:Cell>$Resource{billNum}</t:Cell><t:Cell>$Resource{postingDate}</t:Cell><t:Cell>$Resource{isAPSettle}</t:Cell><t:Cell>$Resource{costAmount}</t:Cell><t:Cell>$Resource{account}</t:Cell><t:Cell>$Resource{isDelete}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtRWOItemSpEntry.setFormatXml(resHelper.translateString("kdtRWOItemSpEntry",kdtRWOItemSpEntryStrXML));

                this.kdtRWOItemSpEntry.putBindContents("editData",new String[] {"seq","t","itemspNum","repairItem","material","itemspName","taocan","w","settlementObject","isCT","qty","unIssueQty","issueQty","price","discountRate","amount","taxRate","i","wipFactLineNo","wipLineNo","isCreateTo","saleType","rts","billNum","postingDate","isAPSettle","costAmount","account","isDelete"});


        this.kdtRWOItemSpEntry.checkParsed();
        KDComboBox kdtRWOItemSpEntry_t_ComboBox = new KDComboBox();
        kdtRWOItemSpEntry_t_ComboBox.setName("kdtRWOItemSpEntry_t_ComboBox");
        kdtRWOItemSpEntry_t_ComboBox.setVisible(true);
        kdtRWOItemSpEntry_t_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.ga.rs.TEnum").toArray());
        KDTDefaultCellEditor kdtRWOItemSpEntry_t_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_t_ComboBox);
        this.kdtRWOItemSpEntry.getColumn("t").setEditor(kdtRWOItemSpEntry_t_CellEditor);
        KDTextField kdtRWOItemSpEntry_itemspNum_TextField = new KDTextField();
        kdtRWOItemSpEntry_itemspNum_TextField.setName("kdtRWOItemSpEntry_itemspNum_TextField");
        kdtRWOItemSpEntry_itemspNum_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRWOItemSpEntry_itemspNum_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_itemspNum_TextField);
        this.kdtRWOItemSpEntry.getColumn("itemspNum").setEditor(kdtRWOItemSpEntry_itemspNum_CellEditor);
        final KDBizPromptBox kdtRWOItemSpEntry_repairItem_PromptBox = new KDBizPromptBox();
        kdtRWOItemSpEntry_repairItem_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairItemQuery");
        kdtRWOItemSpEntry_repairItem_PromptBox.setVisible(true);
        kdtRWOItemSpEntry_repairItem_PromptBox.setEditable(true);
        kdtRWOItemSpEntry_repairItem_PromptBox.setDisplayFormat("$number$");
        kdtRWOItemSpEntry_repairItem_PromptBox.setEditFormat("$number$");
        kdtRWOItemSpEntry_repairItem_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOItemSpEntry_repairItem_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_repairItem_PromptBox);
        this.kdtRWOItemSpEntry.getColumn("repairItem").setEditor(kdtRWOItemSpEntry_repairItem_CellEditor);
        ObjectValueRender kdtRWOItemSpEntry_repairItem_OVR = new ObjectValueRender();
        kdtRWOItemSpEntry_repairItem_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWOItemSpEntry.getColumn("repairItem").setRenderer(kdtRWOItemSpEntry_repairItem_OVR);
        			kdtRWOItemSpEntry_repairItem_PromptBox.addSelectorListener(new SelectorListener() {
			com.kingdee.eas.auto4s.bdm.rsm.client.RepairItemListUI kdtRWOItemSpEntry_repairItem_PromptBox_F7ListUI = null;
			public void willShow(SelectorEvent e) {
				if (kdtRWOItemSpEntry_repairItem_PromptBox_F7ListUI == null) {
					try {
						kdtRWOItemSpEntry_repairItem_PromptBox_F7ListUI = new com.kingdee.eas.auto4s.bdm.rsm.client.RepairItemListUI();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					HashMap ctx = new HashMap();
					ctx.put("bizUIOwner",javax.swing.SwingUtilities.getWindowAncestor(kdtRWOItemSpEntry_repairItem_PromptBox_F7ListUI));
					kdtRWOItemSpEntry_repairItem_PromptBox_F7ListUI.setF7Use(true,ctx);
					kdtRWOItemSpEntry_repairItem_PromptBox.setSelector(kdtRWOItemSpEntry_repairItem_PromptBox_F7ListUI);
				}
			}
		});
					
        final KDBizPromptBox kdtRWOItemSpEntry_material_PromptBox = new KDBizPromptBox();
        kdtRWOItemSpEntry_material_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7MaterialBaseInfoQuery");
        kdtRWOItemSpEntry_material_PromptBox.setVisible(true);
        kdtRWOItemSpEntry_material_PromptBox.setEditable(true);
        kdtRWOItemSpEntry_material_PromptBox.setDisplayFormat("$number$");
        kdtRWOItemSpEntry_material_PromptBox.setEditFormat("$number$");
        kdtRWOItemSpEntry_material_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOItemSpEntry_material_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_material_PromptBox);
        this.kdtRWOItemSpEntry.getColumn("material").setEditor(kdtRWOItemSpEntry_material_CellEditor);
        ObjectValueRender kdtRWOItemSpEntry_material_OVR = new ObjectValueRender();
        kdtRWOItemSpEntry_material_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtRWOItemSpEntry.getColumn("material").setRenderer(kdtRWOItemSpEntry_material_OVR);
        KDTextField kdtRWOItemSpEntry_itemspName_TextField = new KDTextField();
        kdtRWOItemSpEntry_itemspName_TextField.setName("kdtRWOItemSpEntry_itemspName_TextField");
        kdtRWOItemSpEntry_itemspName_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRWOItemSpEntry_itemspName_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_itemspName_TextField);
        this.kdtRWOItemSpEntry.getColumn("itemspName").setEditor(kdtRWOItemSpEntry_itemspName_CellEditor);
        KDTextField kdtRWOItemSpEntry_taocan_TextField = new KDTextField();
        kdtRWOItemSpEntry_taocan_TextField.setName("kdtRWOItemSpEntry_taocan_TextField");
        kdtRWOItemSpEntry_taocan_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRWOItemSpEntry_taocan_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_taocan_TextField);
        this.kdtRWOItemSpEntry.getColumn("taocan").setEditor(kdtRWOItemSpEntry_taocan_CellEditor);
        final KDBizPromptBox kdtRWOItemSpEntry_w_PromptBox = new KDBizPromptBox();
        kdtRWOItemSpEntry_w_PromptBox.setQueryInfo("com.kingdee.eas.ga.rs.app.WQuery");
        kdtRWOItemSpEntry_w_PromptBox.setVisible(true);
        kdtRWOItemSpEntry_w_PromptBox.setEditable(true);
        kdtRWOItemSpEntry_w_PromptBox.setDisplayFormat("$number$");
        kdtRWOItemSpEntry_w_PromptBox.setEditFormat("$number$");
        kdtRWOItemSpEntry_w_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtRWOItemSpEntry_w_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_w_PromptBox);
        this.kdtRWOItemSpEntry.getColumn("w").setEditor(kdtRWOItemSpEntry_w_CellEditor);
        ObjectValueRender kdtRWOItemSpEntry_w_OVR = new ObjectValueRender();
        kdtRWOItemSpEntry_w_OVR.setFormat(new BizDataFormat("$number$"));
        this.kdtRWOItemSpEntry.getColumn("w").setRenderer(kdtRWOItemSpEntry_w_OVR);
        KDComboBox kdtRWOItemSpEntry_settlementObject_ComboBox = new KDComboBox();
        kdtRWOItemSpEntry_settlementObject_ComboBox.setName("kdtRWOItemSpEntry_settlementObject_ComboBox");
        kdtRWOItemSpEntry_settlementObject_ComboBox.setVisible(true);
        kdtRWOItemSpEntry_settlementObject_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum").toArray());
        KDTDefaultCellEditor kdtRWOItemSpEntry_settlementObject_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_settlementObject_ComboBox);
        this.kdtRWOItemSpEntry.getColumn("settlementObject").setEditor(kdtRWOItemSpEntry_settlementObject_CellEditor);
        KDCheckBox kdtRWOItemSpEntry_isCT_CheckBox = new KDCheckBox();
        kdtRWOItemSpEntry_isCT_CheckBox.setName("kdtRWOItemSpEntry_isCT_CheckBox");
        KDTDefaultCellEditor kdtRWOItemSpEntry_isCT_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_isCT_CheckBox);
        this.kdtRWOItemSpEntry.getColumn("isCT").setEditor(kdtRWOItemSpEntry_isCT_CellEditor);
        KDFormattedTextField kdtRWOItemSpEntry_qty_TextField = new KDFormattedTextField();
        kdtRWOItemSpEntry_qty_TextField.setName("kdtRWOItemSpEntry_qty_TextField");
        kdtRWOItemSpEntry_qty_TextField.setVisible(true);
        kdtRWOItemSpEntry_qty_TextField.setEditable(true);
        kdtRWOItemSpEntry_qty_TextField.setHorizontalAlignment(2);
        kdtRWOItemSpEntry_qty_TextField.setDataType(1);
        	kdtRWOItemSpEntry_qty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOItemSpEntry_qty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOItemSpEntry_qty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOItemSpEntry_qty_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_qty_TextField);
        this.kdtRWOItemSpEntry.getColumn("qty").setEditor(kdtRWOItemSpEntry_qty_CellEditor);
        KDFormattedTextField kdtRWOItemSpEntry_unIssueQty_TextField = new KDFormattedTextField();
        kdtRWOItemSpEntry_unIssueQty_TextField.setName("kdtRWOItemSpEntry_unIssueQty_TextField");
        kdtRWOItemSpEntry_unIssueQty_TextField.setVisible(true);
        kdtRWOItemSpEntry_unIssueQty_TextField.setEditable(true);
        kdtRWOItemSpEntry_unIssueQty_TextField.setHorizontalAlignment(2);
        kdtRWOItemSpEntry_unIssueQty_TextField.setDataType(1);
        	kdtRWOItemSpEntry_unIssueQty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOItemSpEntry_unIssueQty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOItemSpEntry_unIssueQty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOItemSpEntry_unIssueQty_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_unIssueQty_TextField);
        this.kdtRWOItemSpEntry.getColumn("unIssueQty").setEditor(kdtRWOItemSpEntry_unIssueQty_CellEditor);
        KDFormattedTextField kdtRWOItemSpEntry_issueQty_TextField = new KDFormattedTextField();
        kdtRWOItemSpEntry_issueQty_TextField.setName("kdtRWOItemSpEntry_issueQty_TextField");
        kdtRWOItemSpEntry_issueQty_TextField.setVisible(true);
        kdtRWOItemSpEntry_issueQty_TextField.setEditable(true);
        kdtRWOItemSpEntry_issueQty_TextField.setHorizontalAlignment(2);
        kdtRWOItemSpEntry_issueQty_TextField.setDataType(1);
        	kdtRWOItemSpEntry_issueQty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOItemSpEntry_issueQty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOItemSpEntry_issueQty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOItemSpEntry_issueQty_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_issueQty_TextField);
        this.kdtRWOItemSpEntry.getColumn("issueQty").setEditor(kdtRWOItemSpEntry_issueQty_CellEditor);
        KDFormattedTextField kdtRWOItemSpEntry_price_TextField = new KDFormattedTextField();
        kdtRWOItemSpEntry_price_TextField.setName("kdtRWOItemSpEntry_price_TextField");
        kdtRWOItemSpEntry_price_TextField.setVisible(true);
        kdtRWOItemSpEntry_price_TextField.setEditable(true);
        kdtRWOItemSpEntry_price_TextField.setHorizontalAlignment(2);
        kdtRWOItemSpEntry_price_TextField.setDataType(1);
        	kdtRWOItemSpEntry_price_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOItemSpEntry_price_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOItemSpEntry_price_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOItemSpEntry_price_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_price_TextField);
        this.kdtRWOItemSpEntry.getColumn("price").setEditor(kdtRWOItemSpEntry_price_CellEditor);
        KDFormattedTextField kdtRWOItemSpEntry_discountRate_TextField = new KDFormattedTextField();
        kdtRWOItemSpEntry_discountRate_TextField.setName("kdtRWOItemSpEntry_discountRate_TextField");
        kdtRWOItemSpEntry_discountRate_TextField.setVisible(true);
        kdtRWOItemSpEntry_discountRate_TextField.setEditable(true);
        kdtRWOItemSpEntry_discountRate_TextField.setHorizontalAlignment(2);
        kdtRWOItemSpEntry_discountRate_TextField.setDataType(1);
        	kdtRWOItemSpEntry_discountRate_TextField.setMinimumValue(new java.math.BigDecimal("-999.99"));
        	kdtRWOItemSpEntry_discountRate_TextField.setMaximumValue(new java.math.BigDecimal("999.99"));
        kdtRWOItemSpEntry_discountRate_TextField.setPrecision(2);
        KDTDefaultCellEditor kdtRWOItemSpEntry_discountRate_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_discountRate_TextField);
        this.kdtRWOItemSpEntry.getColumn("discountRate").setEditor(kdtRWOItemSpEntry_discountRate_CellEditor);
        KDFormattedTextField kdtRWOItemSpEntry_amount_TextField = new KDFormattedTextField();
        kdtRWOItemSpEntry_amount_TextField.setName("kdtRWOItemSpEntry_amount_TextField");
        kdtRWOItemSpEntry_amount_TextField.setVisible(true);
        kdtRWOItemSpEntry_amount_TextField.setEditable(true);
        kdtRWOItemSpEntry_amount_TextField.setHorizontalAlignment(2);
        kdtRWOItemSpEntry_amount_TextField.setDataType(1);
        	kdtRWOItemSpEntry_amount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOItemSpEntry_amount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOItemSpEntry_amount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOItemSpEntry_amount_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_amount_TextField);
        this.kdtRWOItemSpEntry.getColumn("amount").setEditor(kdtRWOItemSpEntry_amount_CellEditor);
        KDFormattedTextField kdtRWOItemSpEntry_taxRate_TextField = new KDFormattedTextField();
        kdtRWOItemSpEntry_taxRate_TextField.setName("kdtRWOItemSpEntry_taxRate_TextField");
        kdtRWOItemSpEntry_taxRate_TextField.setVisible(true);
        kdtRWOItemSpEntry_taxRate_TextField.setEditable(true);
        kdtRWOItemSpEntry_taxRate_TextField.setHorizontalAlignment(2);
        kdtRWOItemSpEntry_taxRate_TextField.setDataType(1);
        	kdtRWOItemSpEntry_taxRate_TextField.setMinimumValue(new java.math.BigDecimal("-999.99"));
        	kdtRWOItemSpEntry_taxRate_TextField.setMaximumValue(new java.math.BigDecimal("999.99"));
        kdtRWOItemSpEntry_taxRate_TextField.setPrecision(2);
        KDTDefaultCellEditor kdtRWOItemSpEntry_taxRate_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_taxRate_TextField);
        this.kdtRWOItemSpEntry.getColumn("taxRate").setEditor(kdtRWOItemSpEntry_taxRate_CellEditor);
        KDComboBox kdtRWOItemSpEntry_i_ComboBox = new KDComboBox();
        kdtRWOItemSpEntry_i_ComboBox.setName("kdtRWOItemSpEntry_i_ComboBox");
        kdtRWOItemSpEntry_i_ComboBox.setVisible(true);
        kdtRWOItemSpEntry_i_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.ga.rs.IEnum").toArray());
        KDTDefaultCellEditor kdtRWOItemSpEntry_i_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_i_ComboBox);
        this.kdtRWOItemSpEntry.getColumn("i").setEditor(kdtRWOItemSpEntry_i_CellEditor);
        KDFormattedTextField kdtRWOItemSpEntry_wipFactLineNo_TextField = new KDFormattedTextField();
        kdtRWOItemSpEntry_wipFactLineNo_TextField.setName("kdtRWOItemSpEntry_wipFactLineNo_TextField");
        kdtRWOItemSpEntry_wipFactLineNo_TextField.setVisible(true);
        kdtRWOItemSpEntry_wipFactLineNo_TextField.setEditable(true);
        kdtRWOItemSpEntry_wipFactLineNo_TextField.setHorizontalAlignment(2);
        kdtRWOItemSpEntry_wipFactLineNo_TextField.setDataType(0);
        KDTDefaultCellEditor kdtRWOItemSpEntry_wipFactLineNo_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_wipFactLineNo_TextField);
        this.kdtRWOItemSpEntry.getColumn("wipFactLineNo").setEditor(kdtRWOItemSpEntry_wipFactLineNo_CellEditor);
        KDFormattedTextField kdtRWOItemSpEntry_wipLineNo_TextField = new KDFormattedTextField();
        kdtRWOItemSpEntry_wipLineNo_TextField.setName("kdtRWOItemSpEntry_wipLineNo_TextField");
        kdtRWOItemSpEntry_wipLineNo_TextField.setVisible(true);
        kdtRWOItemSpEntry_wipLineNo_TextField.setEditable(true);
        kdtRWOItemSpEntry_wipLineNo_TextField.setHorizontalAlignment(2);
        kdtRWOItemSpEntry_wipLineNo_TextField.setDataType(0);
        KDTDefaultCellEditor kdtRWOItemSpEntry_wipLineNo_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_wipLineNo_TextField);
        this.kdtRWOItemSpEntry.getColumn("wipLineNo").setEditor(kdtRWOItemSpEntry_wipLineNo_CellEditor);
        KDCheckBox kdtRWOItemSpEntry_isCreateTo_CheckBox = new KDCheckBox();
        kdtRWOItemSpEntry_isCreateTo_CheckBox.setName("kdtRWOItemSpEntry_isCreateTo_CheckBox");
        KDTDefaultCellEditor kdtRWOItemSpEntry_isCreateTo_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_isCreateTo_CheckBox);
        this.kdtRWOItemSpEntry.getColumn("isCreateTo").setEditor(kdtRWOItemSpEntry_isCreateTo_CellEditor);
        KDTextField kdtRWOItemSpEntry_saleType_TextField = new KDTextField();
        kdtRWOItemSpEntry_saleType_TextField.setName("kdtRWOItemSpEntry_saleType_TextField");
        kdtRWOItemSpEntry_saleType_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRWOItemSpEntry_saleType_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_saleType_TextField);
        this.kdtRWOItemSpEntry.getColumn("saleType").setEditor(kdtRWOItemSpEntry_saleType_CellEditor);
        KDTextField kdtRWOItemSpEntry_rts_TextField = new KDTextField();
        kdtRWOItemSpEntry_rts_TextField.setName("kdtRWOItemSpEntry_rts_TextField");
        kdtRWOItemSpEntry_rts_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRWOItemSpEntry_rts_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_rts_TextField);
        this.kdtRWOItemSpEntry.getColumn("rts").setEditor(kdtRWOItemSpEntry_rts_CellEditor);
        KDTextField kdtRWOItemSpEntry_billNum_TextField = new KDTextField();
        kdtRWOItemSpEntry_billNum_TextField.setName("kdtRWOItemSpEntry_billNum_TextField");
        kdtRWOItemSpEntry_billNum_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRWOItemSpEntry_billNum_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_billNum_TextField);
        this.kdtRWOItemSpEntry.getColumn("billNum").setEditor(kdtRWOItemSpEntry_billNum_CellEditor);
        KDDatePicker kdtRWOItemSpEntry_postingDate_DatePicker = new KDDatePicker();
        kdtRWOItemSpEntry_postingDate_DatePicker.setName("kdtRWOItemSpEntry_postingDate_DatePicker");
        kdtRWOItemSpEntry_postingDate_DatePicker.setVisible(true);
        kdtRWOItemSpEntry_postingDate_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtRWOItemSpEntry_postingDate_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_postingDate_DatePicker);
        this.kdtRWOItemSpEntry.getColumn("postingDate").setEditor(kdtRWOItemSpEntry_postingDate_CellEditor);
        KDCheckBox kdtRWOItemSpEntry_isAPSettle_CheckBox = new KDCheckBox();
        kdtRWOItemSpEntry_isAPSettle_CheckBox.setName("kdtRWOItemSpEntry_isAPSettle_CheckBox");
        KDTDefaultCellEditor kdtRWOItemSpEntry_isAPSettle_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_isAPSettle_CheckBox);
        this.kdtRWOItemSpEntry.getColumn("isAPSettle").setEditor(kdtRWOItemSpEntry_isAPSettle_CellEditor);
        KDFormattedTextField kdtRWOItemSpEntry_costAmount_TextField = new KDFormattedTextField();
        kdtRWOItemSpEntry_costAmount_TextField.setName("kdtRWOItemSpEntry_costAmount_TextField");
        kdtRWOItemSpEntry_costAmount_TextField.setVisible(true);
        kdtRWOItemSpEntry_costAmount_TextField.setEditable(true);
        kdtRWOItemSpEntry_costAmount_TextField.setHorizontalAlignment(2);
        kdtRWOItemSpEntry_costAmount_TextField.setDataType(1);
        	kdtRWOItemSpEntry_costAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtRWOItemSpEntry_costAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtRWOItemSpEntry_costAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtRWOItemSpEntry_costAmount_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_costAmount_TextField);
        this.kdtRWOItemSpEntry.getColumn("costAmount").setEditor(kdtRWOItemSpEntry_costAmount_CellEditor);
        KDTextField kdtRWOItemSpEntry_account_TextField = new KDTextField();
        kdtRWOItemSpEntry_account_TextField.setName("kdtRWOItemSpEntry_account_TextField");
        kdtRWOItemSpEntry_account_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtRWOItemSpEntry_account_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_account_TextField);
        this.kdtRWOItemSpEntry.getColumn("account").setEditor(kdtRWOItemSpEntry_account_CellEditor);
        KDCheckBox kdtRWOItemSpEntry_isDelete_CheckBox = new KDCheckBox();
        kdtRWOItemSpEntry_isDelete_CheckBox.setName("kdtRWOItemSpEntry_isDelete_CheckBox");
        KDTDefaultCellEditor kdtRWOItemSpEntry_isDelete_CellEditor = new KDTDefaultCellEditor(kdtRWOItemSpEntry_isDelete_CheckBox);
        this.kdtRWOItemSpEntry.getColumn("isDelete").setEditor(kdtRWOItemSpEntry_isDelete_CellEditor);
        // kDLabelContainer8		
        this.kDLabelContainer8.setBoundLabelText(resHelper.getString("kDLabelContainer8.boundLabelText"));		
        this.kDLabelContainer8.setBoundLabelUnderline(true);		
        this.kDLabelContainer8.setBoundLabelLength(40);
        // labRepairItem		
        this.labRepairItem.setBoundLabelText(resHelper.getString("labRepairItem.boundLabelText"));		
        this.labRepairItem.setBoundLabelUnderline(true);		
        this.labRepairItem.setBoundLabelLength(40);		
        this.labRepairItem.setVisible(false);
        // labMaterial		
        this.labMaterial.setBoundLabelText(resHelper.getString("labMaterial.boundLabelText"));		
        this.labMaterial.setBoundLabelUnderline(true);		
        this.labMaterial.setBoundLabelLength(40);
        // cmbT		
        this.cmbT.addItems(EnumUtils.getEnumList("com.kingdee.eas.ga.rs.TEnum").toArray());		
        this.cmbT.setSelectedIndex(0);
        this.cmbT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    cmbT_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtRepairItem
        // prmtMaterial
        // menuItemIsShowStdItemspEntry
        this.menuItemIsShowStdItemspEntry.setAction((IItemAction)ActionProxyFactory.getProxy(actionIsShowStdItemspEntry, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemIsShowStdItemspEntry.setText(resHelper.getString("menuItemIsShowStdItemspEntry.text"));		
        this.menuItemIsShowStdItemspEntry.setMnemonic(72);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {txtVIN,cmbT,txtRemark,prmtVehicle,prmtBrand,prmtOrgUnit,Status,prmtSA,pkComeTime,pkIntendDeliveryTime,txtRepairTotalAmount,chkIsStat,txtMile,OilQty,txtKeyNumber,txtConsignation,prmtWarrantyType,prmtInsuranCompany,prmtRepairType,chkIsPriorAssign,chkIsWash,chkIsWaitForStore,txtCustomerRequest,txtCompanyNumber,pkNextMaintainDate,prmtCustomer,txtRepairSender,txtTel,prmtCustomerDiscountClassify,txtOldID,txtRepairBookingID,ReturnRepair,kDRecentlyComeTime,txtNumber,kDTDis,prmtAccountCfg,chkIsPay,chekIsReceive,repairWay,prmtGroupOrgunit,prmtSupplier,prmtVip,prmtdept,txtcustomInfo,txtsaleType,prmtCustomerAccount,txtcustomerAccountName,kdtRepairBreakEntry,kdtRWOTotalAmountEntry,kdtRWOAttachmentItemEntry,kdtRWOActivityEntry,kdtRWORepairPkgEntry,kdtRWOSparepartEntry,kdtRWORepairItemEntry,kdtRWOItemSpEntry,txtgaDept}));
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
        this.setBounds(new Rectangle(0, 0, 1022, 773));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1022, 773));
        kDSplitPane1.setBounds(new Rectangle(21, 0, 975, 764));
        this.add(kDSplitPane1, new KDLayout.Constraints(21, 0, 975, 764, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contOldID.setBounds(new Rectangle(641, 749, 270, 19));
        this.add(contOldID, new KDLayout.Constraints(641, 749, 270, 19, 0));
        contRepairBookingID.setBounds(new Rectangle(667, 753, 270, 19));
        this.add(contRepairBookingID, new KDLayout.Constraints(667, 753, 270, 19, 0));
        contReturnRepair.setBounds(new Rectangle(724, 758, 270, 19));
        this.add(contReturnRepair, new KDLayout.Constraints(724, 758, 270, 19, 0));
        //kDSplitPane1
        kDSplitPane1.add(kDPanel1, "top");
        kDSplitPane1.add(kDTRWOPane, "bottom");
        //kDPanel1
        kDPanel1.setLayout(new KDLayout());
        kDPanel1.putClientProperty("OriginalBounds", new Rectangle(0, 0, 974, 239));        contCompanyNumber.setBounds(new Rectangle(343, 10, 270, 19));
        kDPanel1.add(contCompanyNumber, new KDLayout.Constraints(343, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contStatus.setBounds(new Rectangle(683, 10, 270, 19));
        kDPanel1.add(contStatus, new KDLayout.Constraints(683, 10, 270, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNumber.setBounds(new Rectangle(2, 10, 270, 19));
        kDPanel1.add(contNumber, new KDLayout.Constraints(2, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDTabbedPane1.setBounds(new Rectangle(2, 41, 968, 182));
        kDPanel1.add(kDTabbedPane1, new KDLayout.Constraints(2, 41, 968, 182, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //contCompanyNumber
        contCompanyNumber.setBoundEditor(txtCompanyNumber);
        //contStatus
        contStatus.setBoundEditor(Status);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //kDTabbedPane1
        kDTabbedPane1.add(kDPBizInfo, resHelper.getString("kDPBizInfo.constraints"));
        kDTabbedPane1.add(kDPRepairInfo, resHelper.getString("kDPRepairInfo.constraints"));
        kDTabbedPane1.add(kDPVehicleInfo, resHelper.getString("kDPVehicleInfo.constraints"));
        kDTabbedPane1.add(kDPHideField, resHelper.getString("kDPHideField.constraints"));
        //kDPBizInfo
        kDPBizInfo.setLayout(new KDLayout());
        kDPBizInfo.putClientProperty("OriginalBounds", new Rectangle(0, 0, 967, 149));        contCustomer.setBounds(new Rectangle(351, 6, 276, 19));
        kDPBizInfo.add(contCustomer, new KDLayout.Constraints(351, 6, 276, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contSA.setBounds(new Rectangle(10, 66, 270, 19));
        kDPBizInfo.add(contSA, new KDLayout.Constraints(10, 66, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contVehicle.setBounds(new Rectangle(10, 6, 270, 19));
        kDPBizInfo.add(contVehicle, new KDLayout.Constraints(10, 6, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contOrgUnit.setBounds(new Rectangle(10, 35, 270, 19));
        kDPBizInfo.add(contOrgUnit, new KDLayout.Constraints(10, 35, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contRemark.setBounds(new Rectangle(349, 93, 609, 43));
        kDPBizInfo.add(contRemark, new KDLayout.Constraints(349, 93, 609, 43, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contcustomInfo.setBounds(new Rectangle(349, 35, 277, 50));
        kDPBizInfo.add(contcustomInfo, new KDLayout.Constraints(349, 35, 277, 50, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contsaleType.setBounds(new Rectangle(688, 66, 270, 19));
        kDPBizInfo.add(contsaleType, new KDLayout.Constraints(688, 66, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contCustomerAccount.setBounds(new Rectangle(688, 6, 270, 19));
        kDPBizInfo.add(contCustomerAccount, new KDLayout.Constraints(688, 6, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contcustomerAccountName.setBounds(new Rectangle(688, 35, 270, 19));
        kDPBizInfo.add(contcustomerAccountName, new KDLayout.Constraints(688, 35, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contgaDept.setBounds(new Rectangle(10, 93, 270, 19));
        kDPBizInfo.add(contgaDept, new KDLayout.Constraints(10, 93, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contCustomer
        contCustomer.setBoundEditor(prmtCustomer);
        //contSA
        contSA.setBoundEditor(prmtSA);
        //contVehicle
        contVehicle.setBoundEditor(prmtVehicle);
        //contOrgUnit
        contOrgUnit.setBoundEditor(prmtOrgUnit);
        //contRemark
        contRemark.setBoundEditor(scrollPaneRemark);
        //scrollPaneRemark
        scrollPaneRemark.getViewport().add(txtRemark, null);
        //contcustomInfo
        contcustomInfo.setBoundEditor(scrollPanecustomInfo);
        //scrollPanecustomInfo
        scrollPanecustomInfo.getViewport().add(txtcustomInfo, null);
        //contsaleType
        contsaleType.setBoundEditor(txtsaleType);
        //contCustomerAccount
        contCustomerAccount.setBoundEditor(prmtCustomerAccount);
        //contcustomerAccountName
        contcustomerAccountName.setBoundEditor(txtcustomerAccountName);
        //contgaDept
        contgaDept.setBoundEditor(txtgaDept);
        //kDPRepairInfo
        kDPRepairInfo.setLayout(new KDLayout());
        kDPRepairInfo.putClientProperty("OriginalBounds", new Rectangle(0, 0, 967, 149));        contTel.setBounds(new Rectangle(342, 49, 270, 19));
        kDPRepairInfo.add(contTel, new KDLayout.Constraints(342, 49, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contRepairSender.setBounds(new Rectangle(13, 49, 270, 19));
        kDPRepairInfo.add(contRepairSender, new KDLayout.Constraints(13, 49, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCustomerRequest.setBounds(new Rectangle(672, 48, 271, 57));
        kDPRepairInfo.add(contCustomerRequest, new KDLayout.Constraints(672, 48, 271, 57, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contMile.setBounds(new Rectangle(672, 11, 270, 19));
        kDPRepairInfo.add(contMile, new KDLayout.Constraints(672, 11, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contIntendDeliveryTime.setBounds(new Rectangle(342, 11, 270, 19));
        kDPRepairInfo.add(contIntendDeliveryTime, new KDLayout.Constraints(342, 11, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contComeTime.setBounds(new Rectangle(13, 13, 270, 19));
        kDPRepairInfo.add(contComeTime, new KDLayout.Constraints(13, 13, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer4.setBounds(new Rectangle(13, 86, 270, 19));
        kDPRepairInfo.add(kDLabelContainer4, new KDLayout.Constraints(13, 86, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer6.setBounds(new Rectangle(342, 86, 270, 19));
        kDPRepairInfo.add(kDLabelContainer6, new KDLayout.Constraints(342, 86, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contTel
        contTel.setBoundEditor(txtTel);
        //contRepairSender
        contRepairSender.setBoundEditor(txtRepairSender);
        //contCustomerRequest
        contCustomerRequest.setBoundEditor(scrollPaneCustomerRequest);
        //scrollPaneCustomerRequest
        scrollPaneCustomerRequest.getViewport().add(txtCustomerRequest, null);
        //contMile
        contMile.setBoundEditor(txtMile);
        //contIntendDeliveryTime
        contIntendDeliveryTime.setBoundEditor(pkIntendDeliveryTime);
        //contComeTime
        contComeTime.setBoundEditor(pkComeTime);
        //kDLabelContainer4
        kDLabelContainer4.setBoundEditor(repairWay);
        //kDLabelContainer6
        kDLabelContainer6.setBoundEditor(prmtSupplier);
        //kDPVehicleInfo
        kDPVehicleInfo.setLayout(new KDLayout());
        kDPVehicleInfo.putClientProperty("OriginalBounds", new Rectangle(0, 0, 967, 149));        kDLabelContainer1.setBounds(new Rectangle(352, 10, 270, 19));
        kDPVehicleInfo.add(kDLabelContainer1, new KDLayout.Constraints(352, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contSeries.setBounds(new Rectangle(352, 44, 270, 19));
        kDPVehicleInfo.add(contSeries, new KDLayout.Constraints(352, 44, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contModel.setBounds(new Rectangle(686, 41, 270, 19));
        kDPVehicleInfo.add(contModel, new KDLayout.Constraints(686, 41, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contVIN.setBounds(new Rectangle(17, 10, 270, 19));
        kDPVehicleInfo.add(contVIN, new KDLayout.Constraints(17, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBrand.setBounds(new Rectangle(19, 44, 270, 19));
        kDPVehicleInfo.add(contBrand, new KDLayout.Constraints(19, 44, 270, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(txtEngineNum);
        //contSeries
        contSeries.setBoundEditor(txtSeries);
        //contModel
        contModel.setBoundEditor(txtModel);
        //contVIN
        contVIN.setBoundEditor(txtVIN);
        //contBrand
        contBrand.setBoundEditor(prmtBrand);
        //kDPHideField
        kDPHideField.setLayout(new KDLayout());
        kDPHideField.putClientProperty("OriginalBounds", new Rectangle(0, 0, 967, 149));        kDLabelContainer2.setBounds(new Rectangle(5, 25, 270, 19));
        kDPHideField.add(kDLabelContainer2, new KDLayout.Constraints(5, 25, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contInsuranCompany.setBounds(new Rectangle(282, 95, 270, 19));
        kDPHideField.add(contInsuranCompany, new KDLayout.Constraints(282, 95, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCustomerDiscountClassify.setBounds(new Rectangle(8, 7, 270, 19));
        kDPHideField.add(contCustomerDiscountClassify, new KDLayout.Constraints(8, 7, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contnextMaintainmiles.setBounds(new Rectangle(2, 47, 270, 19));
        kDPHideField.add(contnextMaintainmiles, new KDLayout.Constraints(2, 47, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNextMaintainDate.setBounds(new Rectangle(558, 26, 270, 19));
        kDPHideField.add(contNextMaintainDate, new KDLayout.Constraints(558, 26, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkIsWaitForStore.setBounds(new Rectangle(380, 4, 88, 19));
        kDPHideField.add(chkIsWaitForStore, new KDLayout.Constraints(380, 4, 88, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkIsWash.setBounds(new Rectangle(525, 4, 78, 19));
        kDPHideField.add(chkIsWash, new KDLayout.Constraints(525, 4, 78, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkIsPriorAssign.setBounds(new Rectangle(452, 4, 102, 19));
        kDPHideField.add(chkIsPriorAssign, new KDLayout.Constraints(452, 4, 102, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contRepairType.setBounds(new Rectangle(281, 69, 270, 19));
        kDPHideField.add(contRepairType, new KDLayout.Constraints(281, 69, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contWarrantyType.setBounds(new Rectangle(2, 94, 270, 19));
        kDPHideField.add(contWarrantyType, new KDLayout.Constraints(2, 94, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contConsignation.setBounds(new Rectangle(559, 115, 270, 19));
        kDPHideField.add(contConsignation, new KDLayout.Constraints(559, 115, 270, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contKeyNumber.setBounds(new Rectangle(557, 91, 270, 19));
        kDPHideField.add(contKeyNumber, new KDLayout.Constraints(557, 91, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contOilQty.setBounds(new Rectangle(287, 120, 270, 19));
        kDPHideField.add(contOilQty, new KDLayout.Constraints(287, 120, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkIsStat.setBounds(new Rectangle(282, 4, 131, 19));
        kDPHideField.add(chkIsStat, new KDLayout.Constraints(282, 4, 131, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contRepairTotalAmount.setBounds(new Rectangle(558, 69, 270, 19));
        kDPHideField.add(contRepairTotalAmount, new KDLayout.Constraints(558, 69, 270, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contDescription.setBounds(new Rectangle(5, 122, 270, 18));
        kDPHideField.add(contDescription, new KDLayout.Constraints(5, 122, 270, 18, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDBtnMulAssign.setBounds(new Rectangle(834, 61, 114, 19));
        kDPHideField.add(kDBtnMulAssign, new KDLayout.Constraints(834, 61, 114, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDBtnWorkTime.setBounds(new Rectangle(833, 38, 124, 19));
        kDPHideField.add(kDBtnWorkTime, new KDLayout.Constraints(833, 38, 124, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        kDLabelContainer3.setBounds(new Rectangle(556, 46, 270, 19));
        kDPHideField.add(kDLabelContainer3, new KDLayout.Constraints(556, 46, 270, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kdbSelVip.setBounds(new Rectangle(833, 21, 121, 19));
        kDPHideField.add(kdbSelVip, new KDLayout.Constraints(833, 21, 121, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDVipItemDisRate.setBounds(new Rectangle(836, 83, 116, 19));
        kDPHideField.add(kDVipItemDisRate, new KDLayout.Constraints(836, 83, 116, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDVipSparDisRate.setBounds(new Rectangle(836, 107, 117, 19));
        kDPHideField.add(kDVipSparDisRate, new KDLayout.Constraints(836, 107, 117, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer5.setBounds(new Rectangle(281, 46, 270, 19));
        kDPHideField.add(kDLabelContainer5, new KDLayout.Constraints(281, 46, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chekIsReceive.setBounds(new Rectangle(281, 28, 156, 19));
        kDPHideField.add(chekIsReceive, new KDLayout.Constraints(281, 28, 156, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkIsPay.setBounds(new Rectangle(402, 26, 118, 19));
        kDPHideField.add(chkIsPay, new KDLayout.Constraints(402, 26, 118, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDLabelContainer7.setBounds(new Rectangle(5, 70, 270, 19));
        kDPHideField.add(kDLabelContainer7, new KDLayout.Constraints(5, 70, 270, 19, KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contdept.setBounds(new Rectangle(601, 2, 270, 19));
        kDPHideField.add(contdept, new KDLayout.Constraints(601, 2, 270, 19, 0));
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(kDRecentlyComeTime);
        //contInsuranCompany
        contInsuranCompany.setBoundEditor(prmtInsuranCompany);
        //contCustomerDiscountClassify
        contCustomerDiscountClassify.setBoundEditor(prmtCustomerDiscountClassify);
        //contnextMaintainmiles
        contnextMaintainmiles.setBoundEditor(txtnextMaintainmiles);
        //contNextMaintainDate
        contNextMaintainDate.setBoundEditor(pkNextMaintainDate);
        //contRepairType
        contRepairType.setBoundEditor(prmtRepairType);
        //contWarrantyType
        contWarrantyType.setBoundEditor(prmtWarrantyType);
        //contConsignation
        contConsignation.setBoundEditor(txtConsignation);
        //contKeyNumber
        contKeyNumber.setBoundEditor(txtKeyNumber);
        //contOilQty
        contOilQty.setBoundEditor(OilQty);
        //contRepairTotalAmount
        contRepairTotalAmount.setBoundEditor(txtRepairTotalAmount);
        //contDescription
        contDescription.setBoundEditor(ScrollPaneDis);
        //ScrollPaneDis
        ScrollPaneDis.getViewport().add(kDTDis, null);
        //kDLabelContainer3
        kDLabelContainer3.setBoundEditor(prmtVip);
        //kDLabelContainer5
        kDLabelContainer5.setBoundEditor(prmtGroupOrgunit);
        //kDLabelContainer7
        kDLabelContainer7.setBoundEditor(prmtAccountCfg);
        //contdept
        contdept.setBoundEditor(prmtdept);
        //kDTRWOPane
        kDTRWOPane.add(kDPRwoItem, resHelper.getString("kDPRwoItem.constraints"));
        kDTRWOPane.add(kDPRwoSp, resHelper.getString("kDPRwoSp.constraints"));
        kDTRWOPane.add(kDPSpk, resHelper.getString("kDPSpk.constraints"));
        kDTRWOPane.add(kDPAct, resHelper.getString("kDPAct.constraints"));
        kDTRWOPane.add(kDPAcc, resHelper.getString("kDPAcc.constraints"));
        kDTRWOPane.add(KDPAmount, resHelper.getString("KDPAmount.constraints"));
        kDTRWOPane.add(kDPBreak, resHelper.getString("kDPBreak.constraints"));
        kDTRWOPane.add(kDPRwoItemSp, resHelper.getString("kDPRwoItemSp.constraints"));
        //kDPRwoItem
        kDPRwoItem.setLayout(new KDLayout());
        kDPRwoItem.putClientProperty("OriginalBounds", new Rectangle(0, 0, 973, 480));        kdtRWORepairItemEntry.setBounds(new Rectangle(0, 0, 973, 470));
        kdtRWORepairItemEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtRWORepairItemEntry,new com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairItemEntryInfo(),null,false);
        kDPRwoItem.add(kdtRWORepairItemEntry_detailPanel, new KDLayout.Constraints(0, 0, 973, 470, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
		kdtRWORepairItemEntry_detailPanel.addAddListener(new com.kingdee.eas.framework.client.multiDetail.IDetailPanelListener() {
			public void beforeEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
				IObjectValue vo = event.getObjectValue();
vo.put("SettleObject","1");
vo.put("ItemStatus","1");
		vo.put("WorkTimeStdPrice",new java.math.BigDecimal(0));
			}
			public void afterEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
			}
		});
        //kDPRwoSp
        kDPRwoSp.setLayout(new KDLayout());
        kDPRwoSp.putClientProperty("OriginalBounds", new Rectangle(0, 0, 973, 480));        kdtRWOSparepartEntry.setBounds(new Rectangle(0, 0, 973, 478));
        kdtRWOSparepartEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtRWOSparepartEntry,new com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryInfo(),null,false);
        kDPRwoSp.add(kdtRWOSparepartEntry_detailPanel, new KDLayout.Constraints(0, 0, 973, 478, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
		kdtRWOSparepartEntry_detailPanel.addAddListener(new com.kingdee.eas.framework.client.multiDetail.IDetailPanelListener() {
			public void beforeEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
				IObjectValue vo = event.getObjectValue();
vo.put("SettleObject","1");
			}
			public void afterEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
			}
		});
        //kDPSpk
        kDPSpk.setLayout(new KDLayout());
        kDPSpk.putClientProperty("OriginalBounds", new Rectangle(0, 0, 973, 480));        kdtRWORepairPkgEntry.setBounds(new Rectangle(0, 0, 973, 479));
        kdtRWORepairPkgEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtRWORepairPkgEntry,new com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairPkgEntryInfo(),null,false);
        kDPSpk.add(kdtRWORepairPkgEntry_detailPanel, new KDLayout.Constraints(0, 0, 973, 479, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //kDPAct
        kDPAct.setLayout(new KDLayout());
        kDPAct.putClientProperty("OriginalBounds", new Rectangle(0, 0, 973, 480));        kdtRWOActivityEntry.setBounds(new Rectangle(0, 0, 973, 475));
        kdtRWOActivityEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtRWOActivityEntry,new com.kingdee.eas.auto4s.rsm.rs.RepairWORWOActivityEntryInfo(),null,false);
        kDPAct.add(kdtRWOActivityEntry_detailPanel, new KDLayout.Constraints(0, 0, 973, 475, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //kDPAcc
        kDPAcc.setLayout(new KDLayout());
        kDPAcc.putClientProperty("OriginalBounds", new Rectangle(0, 0, 973, 480));        kdtRWOAttachmentItemEntry.setBounds(new Rectangle(0, 0, 973, 475));
        kdtRWOAttachmentItemEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtRWOAttachmentItemEntry,new com.kingdee.eas.auto4s.rsm.rs.RepairWORWOAttachmentItemEntryInfo(),null,false);
        kDPAcc.add(kdtRWOAttachmentItemEntry_detailPanel, new KDLayout.Constraints(0, 0, 973, 475, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //KDPAmount
        KDPAmount.setLayout(new KDLayout());
        KDPAmount.putClientProperty("OriginalBounds", new Rectangle(0, 0, 973, 480));        kdtRWOTotalAmountEntry.setBounds(new Rectangle(0, 0, 973, 478));
        kdtRWOTotalAmountEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtRWOTotalAmountEntry,new com.kingdee.eas.auto4s.rsm.rs.RepairWORWOTotalAmountEntryInfo(),null,false);
        KDPAmount.add(kdtRWOTotalAmountEntry_detailPanel, new KDLayout.Constraints(0, 0, 973, 478, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
		kdtRWOTotalAmountEntry_detailPanel.addAddListener(new com.kingdee.eas.framework.client.multiDetail.IDetailPanelListener() {
			public void beforeEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
				IObjectValue vo = event.getObjectValue();
vo.put("SettleObject","1");
vo.put("AmountClassify","1");
		vo.put("ARAmount",new java.math.BigDecimal(0));
			}
			public void afterEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
			}
		});
        //kDPBreak
        kDPBreak.setLayout(new KDLayout());
        kDPBreak.putClientProperty("OriginalBounds", new Rectangle(0, 0, 973, 480));        kdtRepairBreakEntry.setBounds(new Rectangle(0, 0, 971, 477));
        kdtRepairBreakEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtRepairBreakEntry,new com.kingdee.eas.auto4s.rsm.rs.RepairWORepairBreakEntryInfo(),null,false);
        kDPBreak.add(kdtRepairBreakEntry_detailPanel, new KDLayout.Constraints(0, 0, 971, 477, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //kDPRwoItemSp
        kDPRwoItemSp.setLayout(new KDLayout());
        kDPRwoItemSp.putClientProperty("OriginalBounds", new Rectangle(0, 0, 973, 480));        kdtRWOItemSpEntry.setBounds(new Rectangle(4, 34, 962, 442));
        kdtRWOItemSpEntry_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtRWOItemSpEntry,new com.kingdee.eas.auto4s.rsm.rs.RepairWORWOItemSpEntryInfo(),null,false);
        kDPRwoItemSp.add(kdtRWOItemSpEntry_detailPanel, new KDLayout.Constraints(4, 34, 962, 442, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
		kdtRWOItemSpEntry_detailPanel.addAddListener(new com.kingdee.eas.framework.client.multiDetail.IDetailPanelListener() {
			public void beforeEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
				IObjectValue vo = event.getObjectValue();
vo.put("t","P");
vo.put("settlementObject","1");
		vo.put("taxRate",new java.math.BigDecimal(17));
vo.put("i","I");
			}
			public void afterEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
			}
		});
        kDLabelContainer8.setBounds(new Rectangle(14, 6, 93, 19));
        kDPRwoItemSp.add(kDLabelContainer8, new KDLayout.Constraints(14, 6, 93, 19, 0));
        labRepairItem.setBounds(new Rectangle(153, 5, 159, 19));
        kDPRwoItemSp.add(labRepairItem, new KDLayout.Constraints(153, 5, 159, 19, 0));
        labMaterial.setBounds(new Rectangle(153, 5, 159, 19));
        kDPRwoItemSp.add(labMaterial, new KDLayout.Constraints(153, 5, 159, 19, 0));
        //kDLabelContainer8
        kDLabelContainer8.setBoundEditor(cmbT);
        //labRepairItem
        labRepairItem.setBoundEditor(prmtRepairItem);
        //labMaterial
        labMaterial.setBoundEditor(prmtMaterial);
        //contOldID
        contOldID.setBoundEditor(txtOldID);
        //contRepairBookingID
        contRepairBookingID.setBoundEditor(txtRepairBookingID);
        //contReturnRepair
        contReturnRepair.setBoundEditor(ReturnRepair);

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
        this.menuBar.add(menuTable1);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkflow);
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
        menuFile.add(kDSeparator6);
        menuFile.add(menuItemSendMail);
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
        menuEdit.add(separator1);
        menuEdit.add(menuItemCreateFrom);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyFrom);
        menuEdit.add(separatorEdit1);
        menuEdit.add(menuItemEnterToNextRow);
        menuEdit.add(separator2);
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
        menuView.add(separator3);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(kDSeparator7);
        menuView.add(menuItemLocate);
        menuView.add(menuItemIsShowStdItemspEntry);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        //menuTable1
        menuTable1.add(menuItemAddLine);
        menuTable1.add(menuItemCopyLine);
        menuTable1.add(menuItemInsertLine);
        menuTable1.add(menuItemRemoveLine);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuWorkflow
        menuWorkflow.add(menuItemMultiapprove);
        menuWorkflow.add(menuItemNextPerson);
        menuWorkflow.add(menuItemWorkFlowList);
        menuWorkflow.add(MenuItemWFG);
        menuWorkflow.add(menuItemStartWorkFlow);
        menuWorkflow.add(separatorWF2);
        menuWorkflow.add(menuItemAudit);
        menuWorkflow.add(menuItemUnAudit);
        menuWorkflow.add(separatorWF1);
        menuWorkflow.add(menuItemAuditResult);
        menuWorkflow.add(menuItemViewSubmitProccess);
        menuWorkflow.add(menuItemViewDoProccess);
        menuWorkflow.add(kDSeparator5);
        menuWorkflow.add(kDMenuItemSendMessage);
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
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnCloud);
        this.toolBar.add(separator9);
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnEdit);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(separator4);
        this.toolBar.add(btnAdd);
        this.toolBar.add(btnEnterAdd);
        this.toolBar.add(btnSuggest);
        this.toolBar.add(separator5);
        this.toolBar.add(btnInvalid);
        this.toolBar.add(btnUninvalid);
        this.toolBar.add(separator6);
        this.toolBar.add(btnBo);
        this.toolBar.add(btnCancelBo);
        this.toolBar.add(btnDispatching);
        this.toolBar.add(kDWorkButton9);
        this.toolBar.add(btnTimeBooking);
        this.toolBar.add(btnAdjust);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(separator8);
        this.toolBar.add(btnCustomerInfo);
        this.toolBar.add(btnVehicleInfo);
        this.toolBar.add(btnRepairHistory);
        this.toolBar.add(btnCallBack);
        this.toolBar.add(btnCustomerComplain);
        this.toolBar.add(separator10);
        this.toolBar.add(btnPricePrint);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnCopyLine);
        this.toolBar.add(btnInsertLine);
        this.toolBar.add(btnRemoveLine);
        this.toolBar.add(separatorFW6);
        this.toolBar.add(separatorFW9);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnWFViewSubmitProccess);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(btnCancelAssign);
        this.toolBar.add(btnUnTimeBooking);
        this.toolBar.add(kDBtnRefresh);
        this.toolBar.add(btnAddCustomer);
        this.toolBar.add(btnAddVehicle);
        this.toolBar.add(kDViewVipCard);
        this.toolBar.add(kDViewVehicleAdvice);
        this.toolBar.add(btnViewVipPrefer);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("IsWaitForStore", boolean.class, this.chkIsWaitForStore, "selected");
		dataBinder.registerBinding("IsWash", boolean.class, this.chkIsWash, "selected");
		dataBinder.registerBinding("IsPriorAssign", boolean.class, this.chkIsPriorAssign, "selected");
		dataBinder.registerBinding("IsStat", boolean.class, this.chkIsStat, "selected");
		dataBinder.registerBinding("IsReceive", int.class, this.chekIsReceive, "selected");
		dataBinder.registerBinding("IsPay", int.class, this.chkIsPay, "selected");
		dataBinder.registerBinding("RecentlyComeTime", java.util.Date.class, this.kDRecentlyComeTime, "value");
		dataBinder.registerBinding("Vehicle.engineNum", String.class, this.txtEngineNum, "text");
		dataBinder.registerBinding("InsuranCompany", com.kingdee.eas.auto4s.bdm.vam.InsuranceCompanyInfo.class, this.prmtInsuranCompany, "data");
		dataBinder.registerBinding("Vehicle.vIN", String.class, this.txtVIN, "text");
		dataBinder.registerBinding("CustomerDiscountClassify", com.kingdee.eas.basedata.master.cssp.CSSPGroupInfo.class, this.prmtCustomerDiscountClassify, "data");
		dataBinder.registerBinding("Tel", String.class, this.txtTel, "text");
		dataBinder.registerBinding("RepairSender", String.class, this.txtRepairSender, "text");
		dataBinder.registerBinding("Customer", com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo.class, this.prmtCustomer, "data");
		dataBinder.registerBinding("NextMaintainDate", java.util.Date.class, this.pkNextMaintainDate, "value");
		dataBinder.registerBinding("CompanyNumber", String.class, this.txtCompanyNumber, "text");
		dataBinder.registerBinding("CustomerRequest", String.class, this.txtCustomerRequest, "text");
		dataBinder.registerBinding("RepairType", com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo.class, this.prmtRepairType, "data");
		dataBinder.registerBinding("WarrantyType", com.kingdee.eas.auto4s.bdm.rsm.WarrantyTypeInfo.class, this.prmtWarrantyType, "data");
		dataBinder.registerBinding("Consignation", String.class, this.txtConsignation, "text");
		dataBinder.registerBinding("KeyNumber", String.class, this.txtKeyNumber, "text");
		dataBinder.registerBinding("OilQty", com.kingdee.eas.auto4s.bdm.rsm.OilQuantityEnum.class, this.OilQty, "selectedItem");
		dataBinder.registerBinding("Mile", java.math.BigDecimal.class, this.txtMile, "value");
		dataBinder.registerBinding("RepairTotalAmount", java.math.BigDecimal.class, this.txtRepairTotalAmount, "value");
		dataBinder.registerBinding("IntendDeliveryTime", java.util.Date.class, this.pkIntendDeliveryTime, "value");
		dataBinder.registerBinding("ComeTime", java.util.Date.class, this.pkComeTime, "value");
		dataBinder.registerBinding("SA", com.kingdee.eas.basedata.person.PersonInfo.class, this.prmtSA, "data");
		dataBinder.registerBinding("Vehicle", com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo.class, this.prmtVehicle, "data");
		dataBinder.registerBinding("Status", com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum.class, this.Status, "selectedItem");
		dataBinder.registerBinding("OrgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtOrgUnit, "data");
		dataBinder.registerBinding("Brand", com.kingdee.eas.auto4s.bdm.pbd.BrandInfo.class, this.prmtBrand, "data");
		dataBinder.registerBinding("description", String.class, this.kDTDis, "text");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("card", com.kingdee.eas.auto4s.vip.mb.CardInfo.class, this.prmtVip, "data");
		dataBinder.registerBinding("RepairWay", com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum.class, this.repairWay, "selectedItem");
		dataBinder.registerBinding("GroupOrgunit", com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo.class, this.prmtGroupOrgunit, "data");
		dataBinder.registerBinding("Supplier", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtSupplier, "data");
		dataBinder.registerBinding("accountCFG", com.kingdee.eas.auto4s.bdm.vip.AccountCFGInfo.class, this.prmtAccountCfg, "data");
		dataBinder.registerBinding("RWORepairItemEntry.seq", int.class, this.kdtRWORepairItemEntry, "seq.text");
		dataBinder.registerBinding("RWORepairItemEntry", com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairItemEntryInfo.class, this.kdtRWORepairItemEntry, "userObject");
		dataBinder.registerBinding("RWORepairItemEntry.PaymentClassify", java.lang.Object.class, this.kdtRWORepairItemEntry, "PaymentClassify.text");
		dataBinder.registerBinding("RWORepairItemEntry.SettleObject", com.kingdee.util.enums.Enum.class, this.kdtRWORepairItemEntry, "SettleObject.text");
		dataBinder.registerBinding("RWORepairItemEntry.RepairPkg", java.lang.Object.class, this.kdtRWORepairItemEntry, "RepairPkg.text");
		dataBinder.registerBinding("RWORepairItemEntry.StdWorkTime", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "StdWorkTime.text");
		dataBinder.registerBinding("RWORepairItemEntry.WorkTimePrice", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "WorkTimePrice.text");
		dataBinder.registerBinding("RWORepairItemEntry.WorkTimeAmount", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "WorkTimeAmount.text");
		dataBinder.registerBinding("RWORepairItemEntry.DiscountRate", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "DiscountRate.text");
		dataBinder.registerBinding("RWORepairItemEntry.DiscountAmount", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "DiscountAmount.text");
		dataBinder.registerBinding("RWORepairItemEntry.RepairClassify", java.lang.Object.class, this.kdtRWORepairItemEntry, "RepairClassify.text");
		dataBinder.registerBinding("RWORepairItemEntry.AssignWorkTime", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "AssignWorkTime.text");
		dataBinder.registerBinding("RWORepairItemEntry.ARAmount", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "ARAmount.text");
		dataBinder.registerBinding("RWORepairItemEntry.ActualAmount", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "ActualAmount.text");
		dataBinder.registerBinding("RWORepairItemEntry.IsAppend", boolean.class, this.kdtRWORepairItemEntry, "IsAppend.text");
		dataBinder.registerBinding("RWORepairItemEntry.IsReturnRepair", boolean.class, this.kdtRWORepairItemEntry, "IsReturnRepair.text");
		dataBinder.registerBinding("RWORepairItemEntry.ReworkReason", java.lang.Object.class, this.kdtRWORepairItemEntry, "ReworkReason.text");
		dataBinder.registerBinding("RWORepairItemEntry.IsDelete", boolean.class, this.kdtRWORepairItemEntry, "IsDelete.text");
		dataBinder.registerBinding("RWORepairItemEntry.ItemStatus", com.kingdee.util.enums.Enum.class, this.kdtRWORepairItemEntry, "ItemStatus.text");
		dataBinder.registerBinding("RWORepairItemEntry.WorkStation", java.lang.Object.class, this.kdtRWORepairItemEntry, "WorkStation.text");
		dataBinder.registerBinding("RWORepairItemEntry.ActualWorkTime", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "ActualWorkTime.text");
		dataBinder.registerBinding("RWORepairItemEntry.ItemRemark", String.class, this.kdtRWORepairItemEntry, "ItemRemark.text");
		dataBinder.registerBinding("RWORepairItemEntry.ServiceActivity", java.lang.Object.class, this.kdtRWORepairItemEntry, "ServiceActivity.text");
		dataBinder.registerBinding("RWORepairItemEntry.Person", java.lang.Object.class, this.kdtRWORepairItemEntry, "Person.text");
		dataBinder.registerBinding("RWORepairItemEntry.WorkGroup", java.lang.Object.class, this.kdtRWORepairItemEntry, "WorkGroup.text");
		dataBinder.registerBinding("RWORepairItemEntry.RepairItem", java.lang.Object.class, this.kdtRWORepairItemEntry, "RepairItem.text");
		dataBinder.registerBinding("RWORepairItemEntry.WagePrice", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "WagePrice.text");
		dataBinder.registerBinding("RWORepairItemEntry.WorkTimeCost", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "WorkTimeCost.text");
		dataBinder.registerBinding("RWORepairItemEntry.RepairItem.name", String.class, this.kdtRWORepairItemEntry, "RepairItemName.text");
		dataBinder.registerBinding("RWORepairItemEntry.IsTimeEdit", boolean.class, this.kdtRWORepairItemEntry, "IsTimeEdit.text");
		dataBinder.registerBinding("RWORepairItemEntry.id", com.kingdee.bos.util.BOSUuid.class, this.kdtRWORepairItemEntry, "ID.text");
		dataBinder.registerBinding("RWORepairItemEntry.OldDiscountRate", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "OldDiscountRate.text");
		dataBinder.registerBinding("RWORepairItemEntry.IsMulTiAssign", boolean.class, this.kdtRWORepairItemEntry, "IsMulAssign.text");
		dataBinder.registerBinding("RWORepairItemEntry.FCardRepServiceID", String.class, this.kdtRWORepairItemEntry, "cardRepServiceID.text");
		dataBinder.registerBinding("RWORepairItemEntry.WorkTimeStdPrice", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "WorkTimeStdPrice.text");
		dataBinder.registerBinding("RWORepairItemEntry.wipLineNo", int.class, this.kdtRWORepairItemEntry, "wipLineNo.text");
		dataBinder.registerBinding("RWORepairItemEntry.wipFactLineNo", int.class, this.kdtRWORepairItemEntry, "wipFactLineNo.text");
		dataBinder.registerBinding("RWORepairItemEntry.itemSpEntryId", String.class, this.kdtRWORepairItemEntry, "itemSpEntryId.text");
		dataBinder.registerBinding("RWORepairItemEntry.wbprice", java.math.BigDecimal.class, this.kdtRWORepairItemEntry, "wbprice.text");
		dataBinder.registerBinding("RWORepairItemEntry.isCreateTo", boolean.class, this.kdtRWORepairItemEntry, "isCreateTo.text");
		dataBinder.registerBinding("RWOSparepartEntry.seq", int.class, this.kdtRWOSparepartEntry, "seq.text");
		dataBinder.registerBinding("RWOSparepartEntry", com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryInfo.class, this.kdtRWOSparepartEntry, "userObject");
		dataBinder.registerBinding("RWOSparepartEntry.SettleObject", com.kingdee.util.enums.Enum.class, this.kdtRWOSparepartEntry, "SettleObject.text");
		dataBinder.registerBinding("RWOSparepartEntry.Qty", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "Qty.text");
		dataBinder.registerBinding("RWOSparepartEntry.IssueQty", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "IssueQty.text");
		dataBinder.registerBinding("RWOSparepartEntry.TaxPrice", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "TaxPrice.text");
		dataBinder.registerBinding("RWOSparepartEntry.TaxRate", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "TaxRate.text");
		dataBinder.registerBinding("RWOSparepartEntry.NoTaxPrice", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "NoTaxPrice.text");
		dataBinder.registerBinding("RWOSparepartEntry.TaxAmount", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "TaxAmount.text");
		dataBinder.registerBinding("RWOSparepartEntry.NoTaxAmount", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "NoTaxAmount.text");
		dataBinder.registerBinding("RWOSparepartEntry.IsBO", boolean.class, this.kdtRWOSparepartEntry, "IsBO.text");
		dataBinder.registerBinding("RWOSparepartEntry.Tax", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "Tax.text");
		dataBinder.registerBinding("RWOSparepartEntry.DiscountRate", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "DiscountRate.text");
		dataBinder.registerBinding("RWOSparepartEntry.DiscountAmount", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "DiscountAmount.text");
		dataBinder.registerBinding("RWOSparepartEntry.ARAmount", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "ARAmount.text");
		dataBinder.registerBinding("RWOSparepartEntry.ActualAmount", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "ActualAmount.text");
		dataBinder.registerBinding("RWOSparepartEntry.IsAppend", boolean.class, this.kdtRWOSparepartEntry, "IsAppend.text");
		dataBinder.registerBinding("RWOSparepartEntry.IsDelete", boolean.class, this.kdtRWOSparepartEntry, "IsDelete.text");
		dataBinder.registerBinding("RWOSparepartEntry.RepairItem", java.lang.Object.class, this.kdtRWOSparepartEntry, "RepairItem.text");
		dataBinder.registerBinding("RWOSparepartEntry.Remark", String.class, this.kdtRWOSparepartEntry, "Remark.text");
		dataBinder.registerBinding("RWOSparepartEntry.InstantStore", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "InstantStore.text");
		dataBinder.registerBinding("RWOSparepartEntry.IntendArrivalTime", java.util.Date.class, this.kdtRWOSparepartEntry, "IntendArrivalTime.text");
		dataBinder.registerBinding("RWOSparepartEntry.RepairClassify", java.lang.Object.class, this.kdtRWOSparepartEntry, "RepairClassify.text");
		dataBinder.registerBinding("RWOSparepartEntry.PaymentClassify", java.lang.Object.class, this.kdtRWOSparepartEntry, "PaymentClassify.text");
		dataBinder.registerBinding("RWOSparepartEntry.Material", java.lang.Object.class, this.kdtRWOSparepartEntry, "Material.text");
		dataBinder.registerBinding("RWOSparepartEntry.Material.name", String.class, this.kdtRWOSparepartEntry, "MaterialName.text");
		dataBinder.registerBinding("RWOSparepartEntry.ServiceActivity", java.lang.Object.class, this.kdtRWOSparepartEntry, "ServiceActivity.text");
		dataBinder.registerBinding("RWOSparepartEntry.Unit", java.lang.Object.class, this.kdtRWOSparepartEntry, "Unit.text");
		dataBinder.registerBinding("RWOSparepartEntry.RepairPkg", java.lang.Object.class, this.kdtRWOSparepartEntry, "RepairPkg.text");
		dataBinder.registerBinding("RWOSparepartEntry.NoIssueQty", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "NoIssueQty.text");
		dataBinder.registerBinding("RWOSparepartEntry.AssistProperty", java.lang.Object.class, this.kdtRWOSparepartEntry, "AssistProperty.text");
		dataBinder.registerBinding("RWOSparepartEntry.BaseQty", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "BaseQty.text");
		dataBinder.registerBinding("RWOSparepartEntry.BaseUnit", java.lang.Object.class, this.kdtRWOSparepartEntry, "BaseUnit.text");
		dataBinder.registerBinding("RWOSparepartEntry.id", com.kingdee.bos.util.BOSUuid.class, this.kdtRWOSparepartEntry, "ID.text");
		dataBinder.registerBinding("RWOSparepartEntry.OldDiscountRate", java.math.BigDecimal.class, this.kdtRWOSparepartEntry, "OldDiscountRate.text");
		dataBinder.registerBinding("RWOSparepartEntry.MaterialGroup", com.kingdee.eas.basedata.master.material.MaterialGroupInfo.class, this.kdtRWOSparepartEntry, "MaterialGroup.text");
		dataBinder.registerBinding("RWOSparepartEntry.wipLineNo", int.class, this.kdtRWOSparepartEntry, "wipLineNo.text");
		dataBinder.registerBinding("RWOSparepartEntry.wipFactLineNo", int.class, this.kdtRWOSparepartEntry, "wipFactLineNo.text");
		dataBinder.registerBinding("RWOSparepartEntry.isCT", boolean.class, this.kdtRWOSparepartEntry, "isCT.text");
		dataBinder.registerBinding("RWOSparepartEntry.itemSpEntryId", String.class, this.kdtRWOSparepartEntry, "itemSpEntryId.text");
		dataBinder.registerBinding("RWOSparepartEntry.isCreateTo", boolean.class, this.kdtRWOSparepartEntry, "isCreateTo.text");
		dataBinder.registerBinding("RWORepairPkgEntry.seq", int.class, this.kdtRWORepairPkgEntry, "seq.text");
		dataBinder.registerBinding("RWORepairPkgEntry", com.kingdee.eas.auto4s.rsm.rs.RepairWORWORepairPkgEntryInfo.class, this.kdtRWORepairPkgEntry, "userObject");
		dataBinder.registerBinding("RWORepairPkgEntry.RepairPkg", java.lang.Object.class, this.kdtRWORepairPkgEntry, "RepairPkg.text");
		dataBinder.registerBinding("RWORepairPkgEntry.RepairPkg.name", String.class, this.kdtRWORepairPkgEntry, "RepairPkgName.text");
		dataBinder.registerBinding("RWORepairPkgEntry.Classify", com.kingdee.util.enums.Enum.class, this.kdtRWORepairPkgEntry, "Classify.text");
		dataBinder.registerBinding("RWORepairPkgEntry.EffectTime", java.util.Date.class, this.kdtRWORepairPkgEntry, "EffectTime.text");
		dataBinder.registerBinding("RWORepairPkgEntry.InvalidTime", java.util.Date.class, this.kdtRWORepairPkgEntry, "InvalidTime.text");
		dataBinder.registerBinding("RWORepairPkgEntry.OldPrice", java.math.BigDecimal.class, this.kdtRWORepairPkgEntry, "OldPrice.text");
		dataBinder.registerBinding("RWORepairPkgEntry.Price", java.math.BigDecimal.class, this.kdtRWORepairPkgEntry, "Price.text");
		dataBinder.registerBinding("RWORepairPkgEntry.DiscountAmount", java.math.BigDecimal.class, this.kdtRWORepairPkgEntry, "DiscountAmount.text");
		dataBinder.registerBinding("RWOActivityEntry.seq", int.class, this.kdtRWOActivityEntry, "seq.text");
		dataBinder.registerBinding("RWOActivityEntry", com.kingdee.eas.auto4s.rsm.rs.RepairWORWOActivityEntryInfo.class, this.kdtRWOActivityEntry, "userObject");
		dataBinder.registerBinding("RWOActivityEntry.ServiceActivity", java.lang.Object.class, this.kdtRWOActivityEntry, "ServiceActivity.text");
		dataBinder.registerBinding("RWOActivityEntry.ServiceActivity.Name_l2", String.class, this.kdtRWOActivityEntry, "ServiceActivityName.text");
		dataBinder.registerBinding("RWOActivityEntry.ActivityType", com.kingdee.util.enums.Enum.class, this.kdtRWOActivityEntry, "ActivityType.text");
		dataBinder.registerBinding("RWOActivityEntry.BeginTime", java.util.Date.class, this.kdtRWOActivityEntry, "BeginTime.text");
		dataBinder.registerBinding("RWOActivityEntry.FinishTime", java.util.Date.class, this.kdtRWOActivityEntry, "FinishTime.text");
		dataBinder.registerBinding("RWOActivityEntry.FeeTotalAmount", java.math.BigDecimal.class, this.kdtRWOActivityEntry, "FeeTotalAmount.text");
		dataBinder.registerBinding("RWOAttachmentItemEntry.seq", int.class, this.kdtRWOAttachmentItemEntry, "seq.text");
		dataBinder.registerBinding("RWOAttachmentItemEntry", com.kingdee.eas.auto4s.rsm.rs.RepairWORWOAttachmentItemEntryInfo.class, this.kdtRWOAttachmentItemEntry, "userObject");
		dataBinder.registerBinding("RWOAttachmentItemEntry.AttaItemAmount", java.math.BigDecimal.class, this.kdtRWOAttachmentItemEntry, "AttaItemAmount.text");
		dataBinder.registerBinding("RWOAttachmentItemEntry.DiscountRate", java.math.BigDecimal.class, this.kdtRWOAttachmentItemEntry, "DiscountRate.text");
		dataBinder.registerBinding("RWOAttachmentItemEntry.DiscountAmount", java.math.BigDecimal.class, this.kdtRWOAttachmentItemEntry, "DiscountAmount.text");
		dataBinder.registerBinding("RWOAttachmentItemEntry.ARAmount", java.math.BigDecimal.class, this.kdtRWOAttachmentItemEntry, "ARAmount.text");
		dataBinder.registerBinding("RWOAttachmentItemEntry.Cost", java.math.BigDecimal.class, this.kdtRWOAttachmentItemEntry, "Cost.text");
		dataBinder.registerBinding("RWOAttachmentItemEntry.Remark", String.class, this.kdtRWOAttachmentItemEntry, "Remark.text");
		dataBinder.registerBinding("RWOAttachmentItemEntry.AttaItem", java.lang.Object.class, this.kdtRWOAttachmentItemEntry, "AttaItem.text");
		dataBinder.registerBinding("RWOAttachmentItemEntry.AttaItem.name", String.class, this.kdtRWOAttachmentItemEntry, "AttaItemName.text");
		dataBinder.registerBinding("RWOTotalAmountEntry.seq", int.class, this.kdtRWOTotalAmountEntry, "seq.text");
		dataBinder.registerBinding("RWOTotalAmountEntry", com.kingdee.eas.auto4s.rsm.rs.RepairWORWOTotalAmountEntryInfo.class, this.kdtRWOTotalAmountEntry, "userObject");
		dataBinder.registerBinding("RWOTotalAmountEntry.SettleObject", com.kingdee.util.enums.Enum.class, this.kdtRWOTotalAmountEntry, "SettleObject.text");
		dataBinder.registerBinding("RWOTotalAmountEntry.OldAmount", java.math.BigDecimal.class, this.kdtRWOTotalAmountEntry, "OldAmount.text");
		dataBinder.registerBinding("RWOTotalAmountEntry.SettleAmount", java.math.BigDecimal.class, this.kdtRWOTotalAmountEntry, "SettleAmount.text");
		dataBinder.registerBinding("RWOTotalAmountEntry.NoTaxAmount", java.math.BigDecimal.class, this.kdtRWOTotalAmountEntry, "NoTaxAmount.text");
		dataBinder.registerBinding("RWOTotalAmountEntry.TaxAmount", java.math.BigDecimal.class, this.kdtRWOTotalAmountEntry, "TaxAmount.text");
		dataBinder.registerBinding("RWOTotalAmountEntry.DiscountRate", java.math.BigDecimal.class, this.kdtRWOTotalAmountEntry, "DiscountRate.text");
		dataBinder.registerBinding("RWOTotalAmountEntry.AmountClassify", com.kingdee.util.enums.Enum.class, this.kdtRWOTotalAmountEntry, "AmountClassify.text");
		dataBinder.registerBinding("RWOTotalAmountEntry.ARAmount", java.math.BigDecimal.class, this.kdtRWOTotalAmountEntry, "ARAmount.text");
		dataBinder.registerBinding("RepairBreakEntry.seq", int.class, this.kdtRepairBreakEntry, "seq.text");
		dataBinder.registerBinding("RepairBreakEntry", com.kingdee.eas.auto4s.rsm.rs.RepairWORepairBreakEntryInfo.class, this.kdtRepairBreakEntry, "userObject");
		dataBinder.registerBinding("RepairBreakEntry.StartTime", java.util.Date.class, this.kdtRepairBreakEntry, "StartTime.text");
		dataBinder.registerBinding("RepairBreakEntry.Finishtime", java.util.Date.class, this.kdtRepairBreakEntry, "Finishtime.text");
		dataBinder.registerBinding("RepairBreakEntry.Reason", String.class, this.kdtRepairBreakEntry, "Reason.text");
		dataBinder.registerBinding("RepairBreakEntry.BreakType", java.lang.Object.class, this.kdtRepairBreakEntry, "BreakType.text");
		dataBinder.registerBinding("RepairBreakEntry.Remark", String.class, this.kdtRepairBreakEntry, "Remark.text");
		dataBinder.registerBinding("OldID", String.class, this.txtOldID, "text");
		dataBinder.registerBinding("RepairBookingID", String.class, this.txtRepairBookingID, "text");
		dataBinder.registerBinding("ReturnRepair", com.kingdee.eas.auto4s.bdm.rsm.FReturnRepairEnum.class, this.ReturnRepair, "selectedItem");
		dataBinder.registerBinding("Remark", String.class, this.txtRemark, "text");
		dataBinder.registerBinding("customInfo", String.class, this.txtcustomInfo, "text");
		dataBinder.registerBinding("saleType", String.class, this.txtsaleType, "text");
		dataBinder.registerBinding("CustomerAccount", com.kingdee.eas.ga.rs.CustomerAccountInfo.class, this.prmtCustomerAccount, "data");
		dataBinder.registerBinding("customerAccountName", String.class, this.txtcustomerAccountName, "text");
		dataBinder.registerBinding("gaDept", String.class, this.txtgaDept, "text");
		dataBinder.registerBinding("dept", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtdept, "data");
		dataBinder.registerBinding("RWOItemSpEntry.seq", int.class, this.kdtRWOItemSpEntry, "seq.text");
		dataBinder.registerBinding("RWOItemSpEntry", com.kingdee.eas.auto4s.rsm.rs.RepairWORWOItemSpEntryInfo.class, this.kdtRWOItemSpEntry, "userObject");
		dataBinder.registerBinding("RWOItemSpEntry.t", com.kingdee.util.enums.Enum.class, this.kdtRWOItemSpEntry, "t.text");
		dataBinder.registerBinding("RWOItemSpEntry.itemspNum", String.class, this.kdtRWOItemSpEntry, "itemspNum.text");
		dataBinder.registerBinding("RWOItemSpEntry.itemspName", String.class, this.kdtRWOItemSpEntry, "itemspName.text");
		dataBinder.registerBinding("RWOItemSpEntry.repairItem", java.lang.Object.class, this.kdtRWOItemSpEntry, "repairItem.text");
		dataBinder.registerBinding("RWOItemSpEntry.material", java.lang.Object.class, this.kdtRWOItemSpEntry, "material.text");
		dataBinder.registerBinding("RWOItemSpEntry.taocan", String.class, this.kdtRWOItemSpEntry, "taocan.text");
		dataBinder.registerBinding("RWOItemSpEntry.qty", java.math.BigDecimal.class, this.kdtRWOItemSpEntry, "qty.text");
		dataBinder.registerBinding("RWOItemSpEntry.price", java.math.BigDecimal.class, this.kdtRWOItemSpEntry, "price.text");
		dataBinder.registerBinding("RWOItemSpEntry.discountRate", java.math.BigDecimal.class, this.kdtRWOItemSpEntry, "discountRate.text");
		dataBinder.registerBinding("RWOItemSpEntry.amount", java.math.BigDecimal.class, this.kdtRWOItemSpEntry, "amount.text");
		dataBinder.registerBinding("RWOItemSpEntry.i", com.kingdee.util.enums.Enum.class, this.kdtRWOItemSpEntry, "i.text");
		dataBinder.registerBinding("RWOItemSpEntry.isCT", boolean.class, this.kdtRWOItemSpEntry, "isCT.text");
		dataBinder.registerBinding("RWOItemSpEntry.unIssueQty", java.math.BigDecimal.class, this.kdtRWOItemSpEntry, "unIssueQty.text");
		dataBinder.registerBinding("RWOItemSpEntry.issueQty", java.math.BigDecimal.class, this.kdtRWOItemSpEntry, "issueQty.text");
		dataBinder.registerBinding("RWOItemSpEntry.taxRate", java.math.BigDecimal.class, this.kdtRWOItemSpEntry, "taxRate.text");
		dataBinder.registerBinding("RWOItemSpEntry.settlementObject", com.kingdee.util.enums.Enum.class, this.kdtRWOItemSpEntry, "settlementObject.text");
		dataBinder.registerBinding("RWOItemSpEntry.w", java.lang.Object.class, this.kdtRWOItemSpEntry, "w.text");
		dataBinder.registerBinding("RWOItemSpEntry.wipLineNo", int.class, this.kdtRWOItemSpEntry, "wipLineNo.text");
		dataBinder.registerBinding("RWOItemSpEntry.wipFactLineNo", int.class, this.kdtRWOItemSpEntry, "wipFactLineNo.text");
		dataBinder.registerBinding("RWOItemSpEntry.isCreateTo", boolean.class, this.kdtRWOItemSpEntry, "isCreateTo.text");
		dataBinder.registerBinding("RWOItemSpEntry.saleType", String.class, this.kdtRWOItemSpEntry, "saleType.text");
		dataBinder.registerBinding("RWOItemSpEntry.rts", String.class, this.kdtRWOItemSpEntry, "rts.text");
		dataBinder.registerBinding("RWOItemSpEntry.billNum", String.class, this.kdtRWOItemSpEntry, "billNum.text");
		dataBinder.registerBinding("RWOItemSpEntry.postingDate", java.util.Date.class, this.kdtRWOItemSpEntry, "postingDate.text");
		dataBinder.registerBinding("RWOItemSpEntry.isAPSettle", boolean.class, this.kdtRWOItemSpEntry, "isAPSettle.text");
		dataBinder.registerBinding("RWOItemSpEntry.costAmount", java.math.BigDecimal.class, this.kdtRWOItemSpEntry, "costAmount.text");
		dataBinder.registerBinding("RWOItemSpEntry.account", String.class, this.kdtRWOItemSpEntry, "account.text");
		dataBinder.registerBinding("RWOItemSpEntry.isDelete", boolean.class, this.kdtRWOItemSpEntry, "isDelete.text");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.auto4s.rsm.rs.app.RepairWOEditUIHandler";
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
        this.txtVIN.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo)ov;
    }
    protected void removeByPK(IObjectPK pk) throws Exception {
    	IObjectValue editData = this.editData;
    	super.removeByPK(pk);
    	recycleNumberByOrg(editData,"Admin",editData.getString("number"));
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
			protected com.kingdee.eas.basedata.org.OrgType getMainBizOrgType() {
			return com.kingdee.eas.basedata.org.OrgType.getEnum("Admin");
		}

	protected KDBizPromptBox getMainBizOrg() {
		return prmtOrgUnit;
}


    /**
     * output loadFields method
     */
    public void loadFields()
    {
        		setAutoNumberByOrg("Admin");
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
		getValidateHelper().registerBindProperty("IsWaitForStore", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("IsWash", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("IsPriorAssign", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("IsStat", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("IsReceive", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("IsPay", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RecentlyComeTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Vehicle.engineNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("InsuranCompany", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Vehicle.vIN", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("CustomerDiscountClassify", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Tel", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairSender", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Customer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("NextMaintainDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("CompanyNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("CustomerRequest", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("WarrantyType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Consignation", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("KeyNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("OilQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Mile", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairTotalAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("IntendDeliveryTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ComeTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("SA", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Vehicle", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Status", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("OrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Brand", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("card", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairWay", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("GroupOrgunit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Supplier", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("accountCFG", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.PaymentClassify", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.SettleObject", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.RepairPkg", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.StdWorkTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.WorkTimePrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.WorkTimeAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.DiscountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.DiscountAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.RepairClassify", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.AssignWorkTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.ARAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.ActualAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.IsAppend", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.IsReturnRepair", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.ReworkReason", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.IsDelete", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.ItemStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.WorkStation", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.ActualWorkTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.ItemRemark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.ServiceActivity", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.Person", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.WorkGroup", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.RepairItem", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.WagePrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.WorkTimeCost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.RepairItem.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.IsTimeEdit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.OldDiscountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.IsMulTiAssign", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.FCardRepServiceID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.WorkTimeStdPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.wipLineNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.wipFactLineNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.itemSpEntryId", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.wbprice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairItemEntry.isCreateTo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.SettleObject", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.Qty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.IssueQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.TaxPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.TaxRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.NoTaxPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.TaxAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.NoTaxAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.IsBO", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.Tax", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.DiscountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.DiscountAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.ARAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.ActualAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.IsAppend", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.IsDelete", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.RepairItem", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.Remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.InstantStore", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.IntendArrivalTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.RepairClassify", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.PaymentClassify", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.Material", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.Material.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.ServiceActivity", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.Unit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.RepairPkg", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.NoIssueQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.AssistProperty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.BaseQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.BaseUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.OldDiscountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.MaterialGroup", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.wipLineNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.wipFactLineNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.isCT", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.itemSpEntryId", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOSparepartEntry.isCreateTo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairPkgEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairPkgEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairPkgEntry.RepairPkg", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairPkgEntry.RepairPkg.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairPkgEntry.Classify", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairPkgEntry.EffectTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairPkgEntry.InvalidTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairPkgEntry.OldPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairPkgEntry.Price", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWORepairPkgEntry.DiscountAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOActivityEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOActivityEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOActivityEntry.ServiceActivity", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOActivityEntry.ServiceActivity.Name_l2", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOActivityEntry.ActivityType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOActivityEntry.BeginTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOActivityEntry.FinishTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOActivityEntry.FeeTotalAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOAttachmentItemEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOAttachmentItemEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOAttachmentItemEntry.AttaItemAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOAttachmentItemEntry.DiscountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOAttachmentItemEntry.DiscountAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOAttachmentItemEntry.ARAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOAttachmentItemEntry.Cost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOAttachmentItemEntry.Remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOAttachmentItemEntry.AttaItem", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOAttachmentItemEntry.AttaItem.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOTotalAmountEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOTotalAmountEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOTotalAmountEntry.SettleObject", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOTotalAmountEntry.OldAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOTotalAmountEntry.SettleAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOTotalAmountEntry.NoTaxAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOTotalAmountEntry.TaxAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOTotalAmountEntry.DiscountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOTotalAmountEntry.AmountClassify", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOTotalAmountEntry.ARAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairBreakEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairBreakEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairBreakEntry.StartTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairBreakEntry.Finishtime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairBreakEntry.Reason", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairBreakEntry.BreakType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairBreakEntry.Remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("OldID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RepairBookingID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ReturnRepair", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("customInfo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("saleType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("CustomerAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("customerAccountName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("gaDept", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("dept", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.t", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.itemspNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.itemspName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.repairItem", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.material", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.taocan", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.qty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.price", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.discountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.i", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.isCT", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.unIssueQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.issueQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.taxRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.settlementObject", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.w", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.wipLineNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.wipFactLineNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.isCreateTo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.saleType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.rts", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.billNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.postingDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.isAPSettle", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.costAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.account", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("RWOItemSpEntry.isDelete", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
        } else if (STATUS_EDIT.equals(this.oprtState)) {
        } else if (STATUS_VIEW.equals(this.oprtState)) {
        } else if (STATUS_FINDVIEW.equals(this.oprtState)) {
        }
    }

    /**
     * output CoreUI_keyPressed method
     */
    protected void CoreUI_keyPressed(java.awt.event.KeyEvent e) throws Exception
    {
    }

    /**
     * output CoreUI_focusLost method
     */
    protected void CoreUI_focusLost(java.awt.event.FocusEvent e) throws Exception
    {
    }

    /**
     * output chkIsPay_itemStateChanged method
     */
    protected void chkIsPay_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output prmtInsuranCompany_dataChanged method
     */
    protected void prmtInsuranCompany_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output txtVIN_keyPressed method
     */
    protected void txtVIN_keyPressed(java.awt.event.KeyEvent e) throws Exception
    {
    }

    /**
     * output txtVIN_focusLost method
     */
    protected void txtVIN_focusLost(java.awt.event.FocusEvent e) throws Exception
    {
    }

    /**
     * output txtConsignation_actionPerformed method
     */
    protected void txtConsignation_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output txtMile_dataChanged method
     */
    protected void txtMile_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output pkComeTime_dataChanged method
     */
    protected void pkComeTime_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtVehicle_dataChanged method
     */
    protected void prmtVehicle_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtVehicle_keyPressed method
     */
    protected void prmtVehicle_keyPressed(java.awt.event.KeyEvent e) throws Exception
    {
    }

    /**
     * output prmtBrand_dataChanged method
     */
    protected void prmtBrand_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output prmtVip_dataChanged method
     */
    protected void prmtVip_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output repairWay_itemStateChanged method
     */
    protected void repairWay_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output prmtAccountCfg_dataChanged method
     */
    protected void prmtAccountCfg_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output kDPRwoItem_hierarchyChanged method
     */
    protected void kDPRwoItem_hierarchyChanged(java.awt.event.HierarchyEvent e) throws Exception
    {
    }

    /**
     * output kDPRwoSp_hierarchyChanged method
     */
    protected void kDPRwoSp_hierarchyChanged(java.awt.event.HierarchyEvent e) throws Exception
    {
    }

    /**
     * output kDPSpk_hierarchyChanged method
     */
    protected void kDPSpk_hierarchyChanged(java.awt.event.HierarchyEvent e) throws Exception
    {
    }

    /**
     * output kDPAct_hierarchyChanged method
     */
    protected void kDPAct_hierarchyChanged(java.awt.event.HierarchyEvent e) throws Exception
    {
    }

    /**
     * output kDPAcc_hierarchyChanged method
     */
    protected void kDPAcc_hierarchyChanged(java.awt.event.HierarchyEvent e) throws Exception
    {
    }

    /**
     * output KDPAmount_hierarchyChanged method
     */
    protected void KDPAmount_hierarchyChanged(java.awt.event.HierarchyEvent e) throws Exception
    {
    }

    /**
     * output kdtRWORepairItemEntry_editStarting method
     */
    protected void kdtRWORepairItemEntry_editStarting(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtRWORepairItemEntry_tableClicked method
     */
    protected void kdtRWORepairItemEntry_tableClicked(com.kingdee.bos.ctrl.kdf.table.event.KDTMouseEvent e) throws Exception
    {
    }

    /**
     * output kdtRWOSparepartEntry_editStarting method
     */
    protected void kdtRWOSparepartEntry_editStarting(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtRWORepairPkgEntry_editStopped method
     */
    protected void kdtRWORepairPkgEntry_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtRWOActivityEntry_editStopped method
     */
    protected void kdtRWOActivityEntry_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output cmbT_itemStateChanged method
     */
    protected void cmbT_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }


    /**
     * output prmtCustomer_Changed() method
     */
    public void prmtCustomer_Changed() throws Exception
    {
        System.out.println("prmtCustomer_Changed() Function is executed!");
            txtTel.setText(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)prmtCustomer.getData(),"Phone")));


    }

    /**
     * output prmtVehicle_Changed() method
     */
    public void prmtVehicle_Changed() throws Exception
    {
        System.out.println("prmtVehicle_Changed() Function is executed!");
            txtVIN.setText(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)prmtVehicle.getData(),"vIN")));


    }

    /**
     * output kdtRWORepairItemEntry_Changed(int rowIndex,int colIndex) method
     */
    public void kdtRWORepairItemEntry_Changed(int rowIndex,int colIndex) throws Exception
    {
            if ("RepairItem".equalsIgnoreCase(kdtRWORepairItemEntry.getColumn(colIndex).getKey())) {

}


    }

    /**
     * output kdtRWOSparepartEntry_Changed(int rowIndex,int colIndex) method
     */
    public void kdtRWOSparepartEntry_Changed(int rowIndex,int colIndex) throws Exception
    {
            if ("Material".equalsIgnoreCase(kdtRWOSparepartEntry.getColumn(colIndex).getKey())) {
kdtRWOSparepartEntry.getCell(rowIndex,"MaterialName").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWOSparepartEntry.getCell(rowIndex,"Material").getValue(),"name"));

}


    }

    /**
     * output kdtRWORepairPkgEntry_Changed(int rowIndex,int colIndex) method
     */
    public void kdtRWORepairPkgEntry_Changed(int rowIndex,int colIndex) throws Exception
    {
            if ("RepairPkg".equalsIgnoreCase(kdtRWORepairPkgEntry.getColumn(colIndex).getKey())) {
kdtRWORepairPkgEntry.getCell(rowIndex,"RepairPkgName").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWORepairPkgEntry.getCell(rowIndex,"RepairPkg").getValue(),"name"));

}

    if ("RepairPkg".equalsIgnoreCase(kdtRWORepairPkgEntry.getColumn(colIndex).getKey())) {
kdtRWORepairPkgEntry.getCell(rowIndex,"Classify").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWORepairPkgEntry.getCell(rowIndex,"RepairPkg").getValue(),"classify"));

}

    if ("RepairPkg".equalsIgnoreCase(kdtRWORepairPkgEntry.getColumn(colIndex).getKey())) {
kdtRWORepairPkgEntry.getCell(rowIndex,"EffectTime").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getDateValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWORepairPkgEntry.getCell(rowIndex,"RepairPkg").getValue(),"effectTime")));

}

    if ("RepairPkg".equalsIgnoreCase(kdtRWORepairPkgEntry.getColumn(colIndex).getKey())) {
kdtRWORepairPkgEntry.getCell(rowIndex,"InvalidTime").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getDateValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWORepairPkgEntry.getCell(rowIndex,"RepairPkg").getValue(),"invalidTime")));

}

    if ("RepairPkg".equalsIgnoreCase(kdtRWORepairPkgEntry.getColumn(colIndex).getKey())) {
kdtRWORepairPkgEntry.getCell(rowIndex,"OldPrice").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getBigDecimal(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWORepairPkgEntry.getCell(rowIndex,"RepairPkg").getValue(),"oldPrice")));

}

    if ("RepairPkg".equalsIgnoreCase(kdtRWORepairPkgEntry.getColumn(colIndex).getKey())) {
kdtRWORepairPkgEntry.getCell(rowIndex,"Price").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getBigDecimal(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWORepairPkgEntry.getCell(rowIndex,"RepairPkg").getValue(),"price")));

}


    }

    /**
     * output kdtRWOActivityEntry_Changed(int rowIndex,int colIndex) method
     */
    public void kdtRWOActivityEntry_Changed(int rowIndex,int colIndex) throws Exception
    {
            if ("ServiceActivity".equalsIgnoreCase(kdtRWOActivityEntry.getColumn(colIndex).getKey())) {
kdtRWOActivityEntry.setName(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWOActivityEntry.getCell(rowIndex,"ServiceActivity").getValue(),"Name_l2")));

}

    if ("ServiceActivity".equalsIgnoreCase(kdtRWOActivityEntry.getColumn(colIndex).getKey())) {
kdtRWOActivityEntry.getCell(rowIndex,"ActivityType").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWOActivityEntry.getCell(rowIndex,"ServiceActivity").getValue(),"ActivityType"));

}

    if ("ServiceActivity".equalsIgnoreCase(kdtRWOActivityEntry.getColumn(colIndex).getKey())) {
kdtRWOActivityEntry.getCell(rowIndex,"BeginTime").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getDateValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWOActivityEntry.getCell(rowIndex,"ServiceActivity").getValue(),"BeginTime")));

}

    if ("ServiceActivity".equalsIgnoreCase(kdtRWOActivityEntry.getColumn(colIndex).getKey())) {
kdtRWOActivityEntry.getCell(rowIndex,"FinishTime").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getDateValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWOActivityEntry.getCell(rowIndex,"ServiceActivity").getValue(),"FinishTime")));

}

    if ("ServiceActivity".equalsIgnoreCase(kdtRWOActivityEntry.getColumn(colIndex).getKey())) {
kdtRWOActivityEntry.getCell(rowIndex,"FeeTotalAmount").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getBigDecimal(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWOActivityEntry.getCell(rowIndex,"ServiceActivity").getValue(),"FeeTotalAmount")));

}


    }

    /**
     * output kdtRWOAttachmentItemEntry_Changed(int rowIndex,int colIndex) method
     */
    public void kdtRWOAttachmentItemEntry_Changed(int rowIndex,int colIndex) throws Exception
    {
            if ("AttaItem".equalsIgnoreCase(kdtRWOAttachmentItemEntry.getColumn(colIndex).getKey())) {
kdtRWOAttachmentItemEntry.getCell(rowIndex,"AttaItemName").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtRWOAttachmentItemEntry.getCell(rowIndex,"AttaItem").getValue(),"name"));

}


    }

    /**
     * output prmtCustomerAccount_Changed() method
     */
    public void prmtCustomerAccount_Changed() throws Exception
    {
        System.out.println("prmtCustomerAccount_Changed() Function is executed!");
            txtcustomerAccountName.setText(com.kingdee.bos.ui.face.UIRuleUtil.getString(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)prmtCustomerAccount.getData(),"name")));


    }
    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
		String selectorAll = System.getProperty("selector.all");
		if(StringUtils.isEmpty(selectorAll)){
			selectorAll = "true";
		}
        sic.add(new SelectorItemInfo("IsWaitForStore"));
        sic.add(new SelectorItemInfo("IsWash"));
        sic.add(new SelectorItemInfo("IsPriorAssign"));
        sic.add(new SelectorItemInfo("IsStat"));
        sic.add(new SelectorItemInfo("IsReceive"));
        sic.add(new SelectorItemInfo("IsPay"));
        sic.add(new SelectorItemInfo("RecentlyComeTime"));
        sic.add(new SelectorItemInfo("Vehicle.engineNum"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("InsuranCompany.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("InsuranCompany.id"));
        	sic.add(new SelectorItemInfo("InsuranCompany.number"));
        	sic.add(new SelectorItemInfo("InsuranCompany.name"));
		}
        sic.add(new SelectorItemInfo("Vehicle.vIN"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("CustomerDiscountClassify.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("CustomerDiscountClassify.id"));
        	sic.add(new SelectorItemInfo("CustomerDiscountClassify.number"));
        	sic.add(new SelectorItemInfo("CustomerDiscountClassify.name"));
		}
        sic.add(new SelectorItemInfo("Tel"));
        sic.add(new SelectorItemInfo("RepairSender"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Customer.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("Customer.id"));
        	sic.add(new SelectorItemInfo("Customer.number"));
        	sic.add(new SelectorItemInfo("Customer.name"));
		}
        sic.add(new SelectorItemInfo("NextMaintainDate"));
        sic.add(new SelectorItemInfo("CompanyNumber"));
        sic.add(new SelectorItemInfo("CustomerRequest"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RepairType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("RepairType.id"));
        	sic.add(new SelectorItemInfo("RepairType.number"));
        	sic.add(new SelectorItemInfo("RepairType.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("WarrantyType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("WarrantyType.id"));
        	sic.add(new SelectorItemInfo("WarrantyType.number"));
        	sic.add(new SelectorItemInfo("WarrantyType.name"));
		}
        sic.add(new SelectorItemInfo("Consignation"));
        sic.add(new SelectorItemInfo("KeyNumber"));
        sic.add(new SelectorItemInfo("OilQty"));
        sic.add(new SelectorItemInfo("Mile"));
        sic.add(new SelectorItemInfo("RepairTotalAmount"));
        sic.add(new SelectorItemInfo("IntendDeliveryTime"));
        sic.add(new SelectorItemInfo("ComeTime"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("SA.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("SA.id"));
        	sic.add(new SelectorItemInfo("SA.number"));
        	sic.add(new SelectorItemInfo("SA.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Vehicle.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("Vehicle.id"));
        	sic.add(new SelectorItemInfo("Vehicle.number"));
        	sic.add(new SelectorItemInfo("Vehicle.name"));
        	sic.add(new SelectorItemInfo("Vehicle.plateNum"));
		}
        sic.add(new SelectorItemInfo("Status"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("OrgUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("OrgUnit.id"));
        	sic.add(new SelectorItemInfo("OrgUnit.number"));
        	sic.add(new SelectorItemInfo("OrgUnit.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Brand.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("Brand.id"));
        	sic.add(new SelectorItemInfo("Brand.number"));
        	sic.add(new SelectorItemInfo("Brand.name"));
		}
        sic.add(new SelectorItemInfo("description"));
        sic.add(new SelectorItemInfo("number"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("card.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("card.id"));
        	sic.add(new SelectorItemInfo("card.number"));
		}
        sic.add(new SelectorItemInfo("RepairWay"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("GroupOrgunit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("GroupOrgunit.id"));
        	sic.add(new SelectorItemInfo("GroupOrgunit.number"));
        	sic.add(new SelectorItemInfo("GroupOrgunit.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Supplier.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("Supplier.id"));
        	sic.add(new SelectorItemInfo("Supplier.number"));
        	sic.add(new SelectorItemInfo("Supplier.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("accountCFG.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("accountCFG.id"));
        	sic.add(new SelectorItemInfo("accountCFG.number"));
        	sic.add(new SelectorItemInfo("accountCFG.name"));
		}
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairItemEntry.*"));
		}
		else{
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairItemEntry.PaymentClassify.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWORepairItemEntry.PaymentClassify.id"));
			sic.add(new SelectorItemInfo("RWORepairItemEntry.PaymentClassify.name"));
        	sic.add(new SelectorItemInfo("RWORepairItemEntry.PaymentClassify.number"));
		}
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.SettleObject"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairPkg.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairPkg.id"));
			sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairPkg.name"));
        	sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairPkg.number"));
		}
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.StdWorkTime"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkTimePrice"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkTimeAmount"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.DiscountRate"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.DiscountAmount"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairClassify.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairClassify.id"));
			sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairClassify.name"));
        	sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairClassify.number"));
		}
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.AssignWorkTime"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.ARAmount"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.ActualAmount"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.IsAppend"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.IsReturnRepair"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairItemEntry.ReworkReason.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWORepairItemEntry.ReworkReason.id"));
			sic.add(new SelectorItemInfo("RWORepairItemEntry.ReworkReason.name"));
        	sic.add(new SelectorItemInfo("RWORepairItemEntry.ReworkReason.number"));
		}
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.IsDelete"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.ItemStatus"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkStation.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkStation.id"));
			sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkStation.name"));
        	sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkStation.number"));
		}
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.ActualWorkTime"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.ItemRemark"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairItemEntry.ServiceActivity.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWORepairItemEntry.ServiceActivity.id"));
			sic.add(new SelectorItemInfo("RWORepairItemEntry.ServiceActivity.Name_l2"));
        	sic.add(new SelectorItemInfo("RWORepairItemEntry.ServiceActivity.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairItemEntry.Person.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWORepairItemEntry.Person.id"));
			sic.add(new SelectorItemInfo("RWORepairItemEntry.Person.name"));
        	sic.add(new SelectorItemInfo("RWORepairItemEntry.Person.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkGroup.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkGroup.id"));
			sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkGroup.name"));
        	sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkGroup.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairItem.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairItem.id"));
			sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairItem.number"));
			sic.add(new SelectorItemInfo("RWORepairItemEntry.RepairItem.name"));
		}
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.WagePrice"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkTimeCost"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.IsTimeEdit"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.id"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.OldDiscountRate"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.IsMulTiAssign"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.FCardRepServiceID"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.WorkTimeStdPrice"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.wipLineNo"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.wipFactLineNo"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.itemSpEntryId"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.wbprice"));
    	sic.add(new SelectorItemInfo("RWORepairItemEntry.isCreateTo"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.SettleObject"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.Qty"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.IssueQty"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.TaxPrice"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.TaxRate"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.NoTaxPrice"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.TaxAmount"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.NoTaxAmount"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.IsBO"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.Tax"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.DiscountRate"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.DiscountAmount"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.ARAmount"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.ActualAmount"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.IsAppend"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.IsDelete"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairItem.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairItem.id"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairItem.name"));
        	sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairItem.number"));
		}
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.Remark"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.InstantStore"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.IntendArrivalTime"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairClassify.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairClassify.id"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairClassify.name"));
        	sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairClassify.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.PaymentClassify.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOSparepartEntry.PaymentClassify.id"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.PaymentClassify.name"));
        	sic.add(new SelectorItemInfo("RWOSparepartEntry.PaymentClassify.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.Material.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOSparepartEntry.Material.id"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.Material.number"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.Material.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.ServiceActivity.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOSparepartEntry.ServiceActivity.id"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.ServiceActivity.Name_l2"));
        	sic.add(new SelectorItemInfo("RWOSparepartEntry.ServiceActivity.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.Unit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOSparepartEntry.Unit.id"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.Unit.name"));
        	sic.add(new SelectorItemInfo("RWOSparepartEntry.Unit.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairPkg.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairPkg.id"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairPkg.name"));
        	sic.add(new SelectorItemInfo("RWOSparepartEntry.RepairPkg.number"));
		}
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.NoIssueQty"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.AssistProperty.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOSparepartEntry.AssistProperty.id"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.AssistProperty.name"));
        	sic.add(new SelectorItemInfo("RWOSparepartEntry.AssistProperty.number"));
		}
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.BaseQty"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.BaseUnit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOSparepartEntry.BaseUnit.id"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.BaseUnit.name"));
        	sic.add(new SelectorItemInfo("RWOSparepartEntry.BaseUnit.number"));
		}
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.id"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.OldDiscountRate"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOSparepartEntry.MaterialGroup.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOSparepartEntry.MaterialGroup.id"));
			sic.add(new SelectorItemInfo("RWOSparepartEntry.MaterialGroup.name"));
        	sic.add(new SelectorItemInfo("RWOSparepartEntry.MaterialGroup.number"));
		}
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.wipLineNo"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.wipFactLineNo"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.isCT"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.itemSpEntryId"));
    	sic.add(new SelectorItemInfo("RWOSparepartEntry.isCreateTo"));
    	sic.add(new SelectorItemInfo("RWORepairPkgEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairPkgEntry.*"));
		}
		else{
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWORepairPkgEntry.RepairPkg.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWORepairPkgEntry.RepairPkg.id"));
			sic.add(new SelectorItemInfo("RWORepairPkgEntry.RepairPkg.number"));
			sic.add(new SelectorItemInfo("RWORepairPkgEntry.RepairPkg.name"));
		}
    	sic.add(new SelectorItemInfo("RWORepairPkgEntry.Classify"));
    	sic.add(new SelectorItemInfo("RWORepairPkgEntry.EffectTime"));
    	sic.add(new SelectorItemInfo("RWORepairPkgEntry.InvalidTime"));
    	sic.add(new SelectorItemInfo("RWORepairPkgEntry.OldPrice"));
    	sic.add(new SelectorItemInfo("RWORepairPkgEntry.Price"));
    	sic.add(new SelectorItemInfo("RWORepairPkgEntry.DiscountAmount"));
    	sic.add(new SelectorItemInfo("RWOActivityEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOActivityEntry.*"));
		}
		else{
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOActivityEntry.ServiceActivity.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOActivityEntry.ServiceActivity.id"));
			sic.add(new SelectorItemInfo("RWOActivityEntry.ServiceActivity.number"));
		}
    	sic.add(new SelectorItemInfo("RWOActivityEntry.ServiceActivity.Name_l2"));
    	sic.add(new SelectorItemInfo("RWOActivityEntry.ActivityType"));
    	sic.add(new SelectorItemInfo("RWOActivityEntry.BeginTime"));
    	sic.add(new SelectorItemInfo("RWOActivityEntry.FinishTime"));
    	sic.add(new SelectorItemInfo("RWOActivityEntry.FeeTotalAmount"));
    	sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.AttaItemAmount"));
    	sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.DiscountRate"));
    	sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.DiscountAmount"));
    	sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.ARAmount"));
    	sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.Cost"));
    	sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.Remark"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.AttaItem.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.AttaItem.id"));
			sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.AttaItem.number"));
			sic.add(new SelectorItemInfo("RWOAttachmentItemEntry.AttaItem.name"));
		}
    	sic.add(new SelectorItemInfo("RWOTotalAmountEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOTotalAmountEntry.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("RWOTotalAmountEntry.SettleObject"));
    	sic.add(new SelectorItemInfo("RWOTotalAmountEntry.OldAmount"));
    	sic.add(new SelectorItemInfo("RWOTotalAmountEntry.SettleAmount"));
    	sic.add(new SelectorItemInfo("RWOTotalAmountEntry.NoTaxAmount"));
    	sic.add(new SelectorItemInfo("RWOTotalAmountEntry.TaxAmount"));
    	sic.add(new SelectorItemInfo("RWOTotalAmountEntry.DiscountRate"));
    	sic.add(new SelectorItemInfo("RWOTotalAmountEntry.AmountClassify"));
    	sic.add(new SelectorItemInfo("RWOTotalAmountEntry.ARAmount"));
    	sic.add(new SelectorItemInfo("RepairBreakEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RepairBreakEntry.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("RepairBreakEntry.StartTime"));
    	sic.add(new SelectorItemInfo("RepairBreakEntry.Finishtime"));
    	sic.add(new SelectorItemInfo("RepairBreakEntry.Reason"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RepairBreakEntry.BreakType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RepairBreakEntry.BreakType.id"));
			sic.add(new SelectorItemInfo("RepairBreakEntry.BreakType.name"));
        	sic.add(new SelectorItemInfo("RepairBreakEntry.BreakType.number"));
		}
    	sic.add(new SelectorItemInfo("RepairBreakEntry.Remark"));
        sic.add(new SelectorItemInfo("OldID"));
        sic.add(new SelectorItemInfo("RepairBookingID"));
        sic.add(new SelectorItemInfo("ReturnRepair"));
        sic.add(new SelectorItemInfo("Remark"));
        sic.add(new SelectorItemInfo("customInfo"));
        sic.add(new SelectorItemInfo("saleType"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("CustomerAccount.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("CustomerAccount.id"));
        	sic.add(new SelectorItemInfo("CustomerAccount.number"));
        	sic.add(new SelectorItemInfo("CustomerAccount.name"));
		}
        sic.add(new SelectorItemInfo("customerAccountName"));
        sic.add(new SelectorItemInfo("gaDept"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("dept.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("dept.id"));
        	sic.add(new SelectorItemInfo("dept.number"));
        	sic.add(new SelectorItemInfo("dept.name"));
		}
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOItemSpEntry.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.t"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.itemspNum"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.itemspName"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOItemSpEntry.repairItem.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOItemSpEntry.repairItem.id"));
			sic.add(new SelectorItemInfo("RWOItemSpEntry.repairItem.name"));
        	sic.add(new SelectorItemInfo("RWOItemSpEntry.repairItem.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOItemSpEntry.material.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOItemSpEntry.material.id"));
			sic.add(new SelectorItemInfo("RWOItemSpEntry.material.name"));
        	sic.add(new SelectorItemInfo("RWOItemSpEntry.material.number"));
		}
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.taocan"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.qty"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.price"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.discountRate"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.amount"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.i"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.isCT"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.unIssueQty"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.issueQty"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.taxRate"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.settlementObject"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("RWOItemSpEntry.w.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("RWOItemSpEntry.w.id"));
			sic.add(new SelectorItemInfo("RWOItemSpEntry.w.number"));
			sic.add(new SelectorItemInfo("RWOItemSpEntry.w.name"));
		}
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.wipLineNo"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.wipFactLineNo"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.isCreateTo"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.saleType"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.rts"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.billNum"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.postingDate"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.isAPSettle"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.costAmount"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.account"));
    	sic.add(new SelectorItemInfo("RWOItemSpEntry.isDelete"));
        return sic;
    }        
    	

    /**
     * output actionSubmit_actionPerformed method
     */
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSubmit_actionPerformed(e);
    }
    	

    /**
     * output actionPrint_actionPerformed method
     */
    public void actionPrint_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
    	if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.print(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionPrintPreview_actionPerformed method
     */
    public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
        if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.printPreview(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionAdd_actionPerformed method
     */
    public void actionAdd_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionEnterAdd_actionPerformed method
     */
    public void actionEnterAdd_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionBo_actionPerformed method
     */
    public void actionBo_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCancelBo_actionPerformed method
     */
    public void actionCancelBo_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionInvalid_actionPerformed method
     */
    public void actionInvalid_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionUninvalid_actionPerformed method
     */
    public void actionUninvalid_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionDispatching_actionPerformed method
     */
    public void actionDispatching_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionTimeBooking_actionPerformed method
     */
    public void actionTimeBooking_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAdjust_actionPerformed method
     */
    public void actionAdjust_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionSuggest_actionPerformed method
     */
    public void actionSuggest_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionItemIssue_actionPerformed method
     */
    public void actionItemIssue_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCustomer_actionPerformed method
     */
    public void actionCustomer_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionVehicle_actionPerformed method
     */
    public void actionVehicle_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionHistory_actionPerformed method
     */
    public void actionHistory_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCallBack_actionPerformed method
     */
    public void actionCallBack_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionComplain_actionPerformed method
     */
    public void actionComplain_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionPrintPrice_actionPerformed method
     */
    public void actionPrintPrice_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCancelAssign_actionPerformed method
     */
    public void actionCancelAssign_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output kDBtnMulAssignAction_actionPerformed method
     */
    public void kDBtnMulAssignAction_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output kDBtnWorkTimeAction_actionPerformed method
     */
    public void kDBtnWorkTimeAction_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionUnTimeBooking_actionPerformed method
     */
    public void actionUnTimeBooking_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionSelectMaterial_actionPerformed method
     */
    public void actionSelectMaterial_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionRefresh_actionPerformed method
     */
    public void actionRefresh_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output kdbSelVipAction_actionPerformed method
     */
    public void kdbSelVipAction_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output kDVipDisRateAction_actionPerformed method
     */
    public void kDVipDisRateAction_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAddVehicle_actionPerformed method
     */
    public void actionAddVehicle_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAddCustomer_actionPerformed method
     */
    public void actionAddCustomer_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewVipCard_actionPerformed method
     */
    public void actionViewVipCard_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewVehicleAdvice_actionPerformed method
     */
    public void actionViewVehicleAdvice_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewVipPrefer_actionPerformed method
     */
    public void actionViewVipPrefer_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionIsShowStdItemspEntry_actionPerformed method
     */
    public void actionIsShowStdItemspEntry_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionSubmit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSubmit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSubmit() {
    	return false;
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
	public RequestContext prepareActionAdd(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAdd() {
    	return false;
    }
	public RequestContext prepareActionEnterAdd(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionEnterAdd() {
    	return false;
    }
	public RequestContext prepareActionBo(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionBo() {
    	return false;
    }
	public RequestContext prepareActionCancelBo(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCancelBo() {
    	return false;
    }
	public RequestContext prepareActionInvalid(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionInvalid() {
    	return false;
    }
	public RequestContext prepareActionUninvalid(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionUninvalid() {
    	return false;
    }
	public RequestContext prepareActionDispatching(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionDispatching() {
    	return false;
    }
	public RequestContext prepareActionTimeBooking(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionTimeBooking() {
    	return false;
    }
	public RequestContext prepareActionAdjust(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAdjust() {
    	return false;
    }
	public RequestContext prepareActionSuggest(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSuggest() {
    	return false;
    }
	public RequestContext prepareActionItemIssue(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionItemIssue() {
    	return false;
    }
	public RequestContext prepareActionCustomer(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCustomer() {
    	return false;
    }
	public RequestContext prepareActionVehicle(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionVehicle() {
    	return false;
    }
	public RequestContext prepareActionHistory(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionHistory() {
    	return false;
    }
	public RequestContext prepareActionCallBack(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCallBack() {
    	return false;
    }
	public RequestContext prepareActionComplain(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionComplain() {
    	return false;
    }
	public RequestContext prepareActionPrintPrice(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrintPrice() {
    	return false;
    }
	public RequestContext prepareActionCancelAssign(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCancelAssign() {
    	return false;
    }
	public RequestContext prepareKDBtnMulAssignAction(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareKDBtnMulAssignAction() {
    	return false;
    }
	public RequestContext prepareKDBtnWorkTimeAction(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareKDBtnWorkTimeAction() {
    	return false;
    }
	public RequestContext prepareActionUnTimeBooking(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionUnTimeBooking() {
    	return false;
    }
	public RequestContext prepareActionSelectMaterial(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSelectMaterial() {
    	return false;
    }
	public RequestContext prepareActionRefresh(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionRefresh() {
    	return false;
    }
	public RequestContext prepareKdbSelVipAction(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareKdbSelVipAction() {
    	return false;
    }
	public RequestContext preparekDVipDisRateAction(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPreparekDVipDisRateAction() {
    	return false;
    }
	public RequestContext prepareActionAddVehicle(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAddVehicle() {
    	return false;
    }
	public RequestContext prepareActionAddCustomer(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAddCustomer() {
    	return false;
    }
	public RequestContext prepareActionViewVipCard(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionViewVipCard() {
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
	public RequestContext prepareActionViewVipPrefer(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionViewVipPrefer() {
    	return false;
    }
	public RequestContext prepareActionIsShowStdItemspEntry(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionIsShowStdItemspEntry() {
    	return false;
    }

    /**
     * output ActionAdd class
     */     
    protected class ActionAdd extends ItemAction {     
    
        public ActionAdd()
        {
            this(null);
        }

        public ActionAdd(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAdd.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAdd.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAdd.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionAdd", "actionAdd_actionPerformed", e);
        }
    }

    /**
     * output ActionEnterAdd class
     */     
    protected class ActionEnterAdd extends ItemAction {     
    
        public ActionEnterAdd()
        {
            this(null);
        }

        public ActionEnterAdd(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionEnterAdd.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionEnterAdd.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionEnterAdd.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionEnterAdd", "actionEnterAdd_actionPerformed", e);
        }
    }

    /**
     * output ActionBo class
     */     
    protected class ActionBo extends ItemAction {     
    
        public ActionBo()
        {
            this(null);
        }

        public ActionBo(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionBo.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionBo.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionBo.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionBo", "actionBo_actionPerformed", e);
        }
    }

    /**
     * output ActionCancelBo class
     */     
    protected class ActionCancelBo extends ItemAction {     
    
        public ActionCancelBo()
        {
            this(null);
        }

        public ActionCancelBo(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionCancelBo.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCancelBo.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCancelBo.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionCancelBo", "actionCancelBo_actionPerformed", e);
        }
    }

    /**
     * output ActionInvalid class
     */     
    protected class ActionInvalid extends ItemAction {     
    
        public ActionInvalid()
        {
            this(null);
        }

        public ActionInvalid(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionInvalid.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionInvalid.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionInvalid.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionInvalid", "actionInvalid_actionPerformed", e);
        }
    }

    /**
     * output ActionUninvalid class
     */     
    protected class ActionUninvalid extends ItemAction {     
    
        public ActionUninvalid()
        {
            this(null);
        }

        public ActionUninvalid(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionUninvalid.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionUninvalid.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionUninvalid.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionUninvalid", "actionUninvalid_actionPerformed", e);
        }
    }

    /**
     * output ActionDispatching class
     */     
    protected class ActionDispatching extends ItemAction {     
    
        public ActionDispatching()
        {
            this(null);
        }

        public ActionDispatching(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionDispatching.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionDispatching.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionDispatching.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionDispatching", "actionDispatching_actionPerformed", e);
        }
    }

    /**
     * output ActionTimeBooking class
     */     
    protected class ActionTimeBooking extends ItemAction {     
    
        public ActionTimeBooking()
        {
            this(null);
        }

        public ActionTimeBooking(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionTimeBooking.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTimeBooking.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTimeBooking.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionTimeBooking", "actionTimeBooking_actionPerformed", e);
        }
    }

    /**
     * output ActionAdjust class
     */     
    protected class ActionAdjust extends ItemAction {     
    
        public ActionAdjust()
        {
            this(null);
        }

        public ActionAdjust(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionAdjust.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAdjust.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAdjust.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionAdjust", "actionAdjust_actionPerformed", e);
        }
    }

    /**
     * output ActionSuggest class
     */     
    protected class ActionSuggest extends ItemAction {     
    
        public ActionSuggest()
        {
            this(null);
        }

        public ActionSuggest(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionSuggest.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSuggest.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSuggest.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionSuggest", "actionSuggest_actionPerformed", e);
        }
    }

    /**
     * output ActionItemIssue class
     */     
    protected class ActionItemIssue extends ItemAction {     
    
        public ActionItemIssue()
        {
            this(null);
        }

        public ActionItemIssue(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionItemIssue.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionItemIssue.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionItemIssue.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionItemIssue", "actionItemIssue_actionPerformed", e);
        }
    }

    /**
     * output ActionCustomer class
     */     
    protected class ActionCustomer extends ItemAction {     
    
        public ActionCustomer()
        {
            this(null);
        }

        public ActionCustomer(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionCustomer.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCustomer.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCustomer.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionCustomer", "actionCustomer_actionPerformed", e);
        }
    }

    /**
     * output ActionVehicle class
     */     
    protected class ActionVehicle extends ItemAction {     
    
        public ActionVehicle()
        {
            this(null);
        }

        public ActionVehicle(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionVehicle.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionVehicle.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionVehicle.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionVehicle", "actionVehicle_actionPerformed", e);
        }
    }

    /**
     * output ActionHistory class
     */     
    protected class ActionHistory extends ItemAction {     
    
        public ActionHistory()
        {
            this(null);
        }

        public ActionHistory(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionHistory.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionHistory.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionHistory.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionHistory", "actionHistory_actionPerformed", e);
        }
    }

    /**
     * output ActionCallBack class
     */     
    protected class ActionCallBack extends ItemAction {     
    
        public ActionCallBack()
        {
            this(null);
        }

        public ActionCallBack(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionCallBack.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCallBack.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCallBack.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionCallBack", "actionCallBack_actionPerformed", e);
        }
    }

    /**
     * output ActionComplain class
     */     
    protected class ActionComplain extends ItemAction {     
    
        public ActionComplain()
        {
            this(null);
        }

        public ActionComplain(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionComplain.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionComplain.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionComplain.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionComplain", "actionComplain_actionPerformed", e);
        }
    }

    /**
     * output ActionPrintPrice class
     */     
    protected class ActionPrintPrice extends ItemAction {     
    
        public ActionPrintPrice()
        {
            this(null);
        }

        public ActionPrintPrice(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionPrintPrice.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionPrintPrice.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionPrintPrice.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionPrintPrice", "actionPrintPrice_actionPerformed", e);
        }
    }

    /**
     * output ActionCancelAssign class
     */     
    protected class ActionCancelAssign extends ItemAction {     
    
        public ActionCancelAssign()
        {
            this(null);
        }

        public ActionCancelAssign(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionCancelAssign.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCancelAssign.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCancelAssign.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionCancelAssign", "actionCancelAssign_actionPerformed", e);
        }
    }

    /**
     * output KDBtnMulAssignAction class
     */     
    protected class KDBtnMulAssignAction extends ItemAction {     
    
        public KDBtnMulAssignAction()
        {
            this(null);
        }

        public KDBtnMulAssignAction(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("KDBtnMulAssignAction.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("KDBtnMulAssignAction.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("KDBtnMulAssignAction.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "KDBtnMulAssignAction", "kDBtnMulAssignAction_actionPerformed", e);
        }
    }

    /**
     * output KDBtnWorkTimeAction class
     */     
    protected class KDBtnWorkTimeAction extends ItemAction {     
    
        public KDBtnWorkTimeAction()
        {
            this(null);
        }

        public KDBtnWorkTimeAction(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("KDBtnWorkTimeAction.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("KDBtnWorkTimeAction.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("KDBtnWorkTimeAction.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "KDBtnWorkTimeAction", "kDBtnWorkTimeAction_actionPerformed", e);
        }
    }

    /**
     * output ActionUnTimeBooking class
     */     
    protected class ActionUnTimeBooking extends ItemAction {     
    
        public ActionUnTimeBooking()
        {
            this(null);
        }

        public ActionUnTimeBooking(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionUnTimeBooking.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionUnTimeBooking.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionUnTimeBooking.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionUnTimeBooking", "actionUnTimeBooking_actionPerformed", e);
        }
    }

    /**
     * output ActionSelectMaterial class
     */     
    protected class ActionSelectMaterial extends ItemAction {     
    
        public ActionSelectMaterial()
        {
            this(null);
        }

        public ActionSelectMaterial(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionSelectMaterial.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSelectMaterial.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSelectMaterial.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionSelectMaterial", "actionSelectMaterial_actionPerformed", e);
        }
    }

    /**
     * output ActionRefresh class
     */     
    protected class ActionRefresh extends ItemAction {     
    
        public ActionRefresh()
        {
            this(null);
        }

        public ActionRefresh(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionRefresh.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRefresh.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRefresh.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionRefresh", "actionRefresh_actionPerformed", e);
        }
    }

    /**
     * output KdbSelVipAction class
     */     
    protected class KdbSelVipAction extends ItemAction {     
    
        public KdbSelVipAction()
        {
            this(null);
        }

        public KdbSelVipAction(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("KdbSelVipAction.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("KdbSelVipAction.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("KdbSelVipAction.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "KdbSelVipAction", "kdbSelVipAction_actionPerformed", e);
        }
    }

    /**
     * output kDVipDisRateAction class
     */     
    protected class kDVipDisRateAction extends ItemAction {     
    
        public kDVipDisRateAction()
        {
            this(null);
        }

        public kDVipDisRateAction(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("kDVipDisRateAction.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("kDVipDisRateAction.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("kDVipDisRateAction.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "kDVipDisRateAction", "kDVipDisRateAction_actionPerformed", e);
        }
    }

    /**
     * output ActionAddVehicle class
     */     
    protected class ActionAddVehicle extends ItemAction {     
    
        public ActionAddVehicle()
        {
            this(null);
        }

        public ActionAddVehicle(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAddVehicle.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddVehicle.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddVehicle.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionAddVehicle", "actionAddVehicle_actionPerformed", e);
        }
    }

    /**
     * output ActionAddCustomer class
     */     
    protected class ActionAddCustomer extends ItemAction {     
    
        public ActionAddCustomer()
        {
            this(null);
        }

        public ActionAddCustomer(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAddCustomer.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddCustomer.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddCustomer.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionAddCustomer", "actionAddCustomer_actionPerformed", e);
        }
    }

    /**
     * output ActionViewVipCard class
     */     
    protected class ActionViewVipCard extends ItemAction {     
    
        public ActionViewVipCard()
        {
            this(null);
        }

        public ActionViewVipCard(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionViewVipCard.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewVipCard.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewVipCard.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionViewVipCard", "actionViewVipCard_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionViewVehicleAdvice", "actionViewVehicleAdvice_actionPerformed", e);
        }
    }

    /**
     * output ActionViewVipPrefer class
     */     
    protected class ActionViewVipPrefer extends ItemAction {     
    
        public ActionViewVipPrefer()
        {
            this(null);
        }

        public ActionViewVipPrefer(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionViewVipPrefer.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewVipPrefer.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewVipPrefer.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionViewVipPrefer", "actionViewVipPrefer_actionPerformed", e);
        }
    }

    /**
     * output ActionIsShowStdItemspEntry class
     */     
    protected class ActionIsShowStdItemspEntry extends ItemAction {     
    
        public ActionIsShowStdItemspEntry()
        {
            this(null);
        }

        public ActionIsShowStdItemspEntry(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl H"));
            _tempStr = resHelper.getString("ActionIsShowStdItemspEntry.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionIsShowStdItemspEntry.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionIsShowStdItemspEntry.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOEditUI.this, "ActionIsShowStdItemspEntry", "actionIsShowStdItemspEntry_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.auto4s.rsm.rs.client", "RepairWOEditUI");
    }
    /**
     * output isBindWorkFlow method
     */
    public boolean isBindWorkFlow()
    {
        return true;
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.auto4s.rsm.rs.client.RepairWOEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.auto4s.rsm.rs.RepairWOFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo objectValue = new com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo();
				if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Admin")) != null)
			objectValue.put("OrgUnit",com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Admin")));
 
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/auto4s/rsm/rs/RepairWO";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.auto4s.rsm.rs.app.RepairWOQuery");
	}
    
        
					protected void beforeStoreFields(ActionEvent arg0) throws Exception {
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtTel.getText())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"送修人电话"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtRepairSender.getText())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"送修人"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(prmtRepairType.getData())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"维修类型"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtMile.getValue())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"进厂行驶里程"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(pkIntendDeliveryTime.getValue())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"预计交车时间"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(pkComeTime.getValue())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"进厂时间"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(prmtSA.getData())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"服务顾问"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(prmtOrgUnit.getData())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"公司"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtNumber.getText())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"单据编号"});
		}
			super.beforeStoreFields(arg0);
		}

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kdtRWORepairItemEntry;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("OilQty","1");
vo.put("ReturnRepair","1");
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}        
				protected void setTableToSumField() {
			setTableToSumField(kdtRWORepairItemEntry,new String[] {"StdWorkTime","AssignWorkTime","WorkTimeAmount","DiscountAmount","ARAmount","WorkTimeCost"});
			setTableToSumField(kdtRWOSparepartEntry,new String[] {"Qty"});
			setTableToSumField(kdtRWORepairPkgEntry,new String[] {"OldPrice","Price","DiscountAmount"});
			setTableToSumField(kdtRWOActivityEntry,new String[] {"FeeTotalAmount"});
			setTableToSumField(kdtRWOAttachmentItemEntry,new String[] {"AttaItemAmount","DiscountAmount","ARAmount","Cost"});
			setTableToSumField(kdtRWOTotalAmountEntry,new String[] {"OldAmount"});
		}


}