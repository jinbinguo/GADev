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
public abstract class AbstractRepairWOListUI extends com.kingdee.eas.auto4s.autoframework.core.client.AutoBillBaseListUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractRepairWOListUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer coutComeTime1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contComeTime2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contVehicle;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contSA;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBrand;
    protected com.kingdee.bos.ctrl.swing.KDButton btnSearch;
    protected com.kingdee.bos.ctrl.swing.KDButton btnReset;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDComeTime1;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kdComeTime2;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtVehicle;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDComboBox Status;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtSA;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtOrgUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtBrand;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtVIN;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtRepairType;
    protected javax.swing.JToolBar.Separator separator1;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnInvalid;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnUninvalid;
    protected javax.swing.JToolBar.Separator separator2;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnCustomerInfo;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnVehicleInfo;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnRepairHistory;
    protected javax.swing.JToolBar.Separator separator3;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnPricePrint;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAddCustomer;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAddVehicle;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDViewVipCard;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDViewVehicleAdvice;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton KdBtnViewVipPrefer;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer3;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtVehicleEx;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtNumberEx;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbGABillStatus;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtVINEx;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbSettlePrinted;
    protected ActionTDPrint actionTDPrint = null;
    protected ActionTDPrintPreview actionTDPrintPreview = null;
    protected ActionInvalid actionInvalid = null;
    protected ActionUninvalid actionUninvalid = null;
    protected ActionPrintPrice actionPrintPrice = null;
    protected ActionCustomer actionCustomer = null;
    protected ActionVehicle actionVehicle = null;
    protected ActionHistory actionHistory = null;
    protected ActionAddCustomer actionAddCustomer = null;
    protected ActionAddVehicle actionAddVehicle = null;
    protected ActionViewVipCard actionViewVipCard = null;
    protected ActionViewVehicleAdvice actionViewVehicleAdvice = null;
    protected ActionViewVipPrefer actionViewVipPrefer = null;
    protected ActionAudit actionAudit = null;
    protected ActionUnAudit actionUnAudit = null;
    public final static String STATUS_VIEW = "VIEW";
    /**
     * output class constructor
     */
    public AbstractRepairWOListUI() throws Exception
    {
        super();
        this.defaultObjectName = "mainQuery";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractRepairWOListUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        mainQueryPK = new MetaDataPK("com.kingdee.eas.auto4s.rsm.rs.app", "RepairWOQuery");
        //actionRemove
        String _tempStr = null;
        actionRemove.setEnabled(true);
        actionRemove.setDaemonRun(false);

        actionRemove.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
        _tempStr = resHelper.getString("ActionRemove.SHORT_DESCRIPTION");
        actionRemove.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionRemove.LONG_DESCRIPTION");
        actionRemove.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionRemove.NAME");
        actionRemove.putValue(ItemAction.NAME, _tempStr);
        this.actionRemove.setBindWorkFlow(true);
         this.actionRemove.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionRemove.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionRemove.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionTDPrint
        this.actionTDPrint = new ActionTDPrint(this);
        getActionManager().registerAction("actionTDPrint", actionTDPrint);
         this.actionTDPrint.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionTDPrintPreview
        this.actionTDPrintPreview = new ActionTDPrintPreview(this);
        getActionManager().registerAction("actionTDPrintPreview", actionTDPrintPreview);
         this.actionTDPrintPreview.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionInvalid
        this.actionInvalid = new ActionInvalid(this);
        getActionManager().registerAction("actionInvalid", actionInvalid);
         this.actionInvalid.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionUninvalid
        this.actionUninvalid = new ActionUninvalid(this);
        getActionManager().registerAction("actionUninvalid", actionUninvalid);
         this.actionUninvalid.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionPrintPrice
        this.actionPrintPrice = new ActionPrintPrice(this);
        getActionManager().registerAction("actionPrintPrice", actionPrintPrice);
         this.actionPrintPrice.addService(new com.kingdee.eas.framework.client.service.PermissionService());
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
        //actionAddCustomer
        this.actionAddCustomer = new ActionAddCustomer(this);
        getActionManager().registerAction("actionAddCustomer", actionAddCustomer);
         this.actionAddCustomer.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAddVehicle
        this.actionAddVehicle = new ActionAddVehicle(this);
        getActionManager().registerAction("actionAddVehicle", actionAddVehicle);
         this.actionAddVehicle.addService(new com.kingdee.eas.framework.client.service.PermissionService());
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
        //actionAudit
        this.actionAudit = new ActionAudit(this);
        getActionManager().registerAction("actionAudit", actionAudit);
        this.actionAudit.setBindWorkFlow(true);
        this.actionAudit.setExtendProperty("canForewarn", "true");
        this.actionAudit.setExtendProperty("userDefined", "true");
        this.actionAudit.setExtendProperty("isObjectUpdateLock", "false");
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.WorkFlowService());
        //actionUnAudit
        this.actionUnAudit = new ActionUnAudit(this);
        getActionManager().registerAction("actionUnAudit", actionUnAudit);
        this.actionUnAudit.setBindWorkFlow(true);
        this.actionUnAudit.setExtendProperty("canForewarn", "true");
        this.actionUnAudit.setExtendProperty("userDefined", "true");
        this.actionUnAudit.setExtendProperty("isObjectUpdateLock", "false");
         this.actionUnAudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionUnAudit.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
         this.actionUnAudit.addService(new com.kingdee.eas.framework.client.service.WorkFlowService());
        this.coutComeTime1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contComeTime2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contVehicle = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contSA = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBrand = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnSearch = new com.kingdee.bos.ctrl.swing.KDButton();
        this.btnReset = new com.kingdee.bos.ctrl.swing.KDButton();
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDComeTime1 = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.kdComeTime2 = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtVehicle = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.Status = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtSA = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtBrand = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtVIN = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtRepairType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.separator1 = new javax.swing.JToolBar.Separator();
        this.btnInvalid = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnUninvalid = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator2 = new javax.swing.JToolBar.Separator();
        this.btnCustomerInfo = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnVehicleInfo = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnRepairHistory = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator3 = new javax.swing.JToolBar.Separator();
        this.btnPricePrint = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAddCustomer = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAddVehicle = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDViewVipCard = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDViewVehicleAdvice = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.KdBtnViewVipPrefer = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDLabelContainer3 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtVehicleEx = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.txtNumberEx = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.cmbGABillStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtVINEx = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.cmbSettlePrinted = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.coutComeTime1.setName("coutComeTime1");
        this.contComeTime2.setName("contComeTime2");
        this.contVehicle.setName("contVehicle");
        this.contNumber.setName("contNumber");
        this.contStatus.setName("contStatus");
        this.contSA.setName("contSA");
        this.contOrgUnit.setName("contOrgUnit");
        this.contBrand.setName("contBrand");
        this.btnSearch.setName("btnSearch");
        this.btnReset.setName("btnReset");
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.kDComeTime1.setName("kDComeTime1");
        this.kdComeTime2.setName("kdComeTime2");
        this.txtVehicle.setName("txtVehicle");
        this.txtNumber.setName("txtNumber");
        this.Status.setName("Status");
        this.prmtSA.setName("prmtSA");
        this.prmtOrgUnit.setName("prmtOrgUnit");
        this.prmtBrand.setName("prmtBrand");
        this.txtVIN.setName("txtVIN");
        this.prmtRepairType.setName("prmtRepairType");
        this.separator1.setName("separator1");
        this.btnInvalid.setName("btnInvalid");
        this.btnUninvalid.setName("btnUninvalid");
        this.separator2.setName("separator2");
        this.btnCustomerInfo.setName("btnCustomerInfo");
        this.btnVehicleInfo.setName("btnVehicleInfo");
        this.btnRepairHistory.setName("btnRepairHistory");
        this.separator3.setName("separator3");
        this.btnPricePrint.setName("btnPricePrint");
        this.btnAddCustomer.setName("btnAddCustomer");
        this.btnAddVehicle.setName("btnAddVehicle");
        this.kDViewVipCard.setName("kDViewVipCard");
        this.kDViewVehicleAdvice.setName("kDViewVehicleAdvice");
        this.KdBtnViewVipPrefer.setName("KdBtnViewVipPrefer");
        this.kDLabelContainer3.setName("kDLabelContainer3");
        this.txtVehicleEx.setName("txtVehicleEx");
        this.txtNumberEx.setName("txtNumberEx");
        this.cmbGABillStatus.setName("cmbGABillStatus");
        this.txtVINEx.setName("txtVINEx");
        this.cmbSettlePrinted.setName("cmbSettlePrinted");
        // CoreUI
		String tblMainStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol14\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol16\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol17\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol18\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol19\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol20\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol21\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol22\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol23\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol24\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol25\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol26\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol29\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol31\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol32\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol33\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol34\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol35\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\" t:configured=\"true\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"gaBillStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:configured=\"true\" /><t:Column t:key=\"number\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"dmswip\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:configured=\"true\" /><t:Column t:key=\"Vehicle.plateNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"Vehicle.vIN\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"series.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"model.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"Customer.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"CustomerDiscount.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"RepairSender\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"Tel\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"ComeTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"IntendDeliveryTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"FactDeliveryTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" t:styleID=\"sCol14\" /><t:Column t:key=\"Mile\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" /><t:Column t:key=\"KeyNumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" t:styleID=\"sCol16\" /><t:Column t:key=\"Status\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" t:styleID=\"sCol17\" /><t:Column t:key=\"RepairType.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" t:styleID=\"sCol18\" /><t:Column t:key=\"WarrantyType.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"17\" t:styleID=\"sCol19\" /><t:Column t:key=\"CompanyNumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" t:styleID=\"sCol20\" /><t:Column t:key=\"InsuranCompany.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"19\" t:styleID=\"sCol21\" /><t:Column t:key=\"IsStat\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"20\" t:styleID=\"sCol22\" /><t:Column t:key=\"IsWash\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"21\" t:styleID=\"sCol23\" /><t:Column t:key=\"IsWaitForStore\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"22\" t:styleID=\"sCol24\" /><t:Column t:key=\"IsPriorAssign\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"23\" t:styleID=\"sCol25\" /><t:Column t:key=\"IsWorkCost\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"24\" t:styleID=\"sCol26\" /><t:Column t:key=\"RepairTotalAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"25\" /><t:Column t:key=\"RepairWay\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"26\" /><t:Column t:key=\"groupOrgunit.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"27\" t:styleID=\"sCol29\" /><t:Column t:key=\"Supplier.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"28\" /><t:Column t:key=\"IsReceive\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"29\" t:styleID=\"sCol31\" /><t:Column t:key=\"IsPay\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"30\" t:styleID=\"sCol32\" /><t:Column t:key=\"RWOSparepartEntry.Qty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"31\" t:styleID=\"sCol33\" /><t:Column t:key=\"RWOSparepartEntry.IssueQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"32\" t:styleID=\"sCol34\" /><t:Column t:key=\"RWOSparepartEntry.NoIssueQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"33\" t:styleID=\"sCol35\" /><t:Column t:key=\"SA.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"34\" /><t:Column t:key=\"bizPerson.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"37\" t:configured=\"true\" /><t:Column t:key=\"OrgUnit.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"35\" /><t:Column t:key=\"Brand.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"36\" /><t:Column t:key=\"isPrintedSettle\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"40\" t:configured=\"true\" /><t:Column t:key=\"wipRemark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"41\" t:configured=\"true\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{gaBillStatus}</t:Cell><t:Cell>$Resource{number}</t:Cell><t:Cell>$Resource{dmswip}</t:Cell><t:Cell>$Resource{Vehicle.plateNum}</t:Cell><t:Cell>$Resource{Vehicle.vIN}</t:Cell><t:Cell>$Resource{series.name}</t:Cell><t:Cell>$Resource{model.name}</t:Cell><t:Cell>$Resource{Customer.name}</t:Cell><t:Cell>$Resource{CustomerDiscount.name}</t:Cell><t:Cell>$Resource{RepairSender}</t:Cell><t:Cell>$Resource{Tel}</t:Cell><t:Cell>$Resource{ComeTime}</t:Cell><t:Cell>$Resource{IntendDeliveryTime}</t:Cell><t:Cell>$Resource{FactDeliveryTime}</t:Cell><t:Cell>$Resource{Mile}</t:Cell><t:Cell>$Resource{KeyNumber}</t:Cell><t:Cell>$Resource{Status}</t:Cell><t:Cell>$Resource{RepairType.name}</t:Cell><t:Cell>$Resource{WarrantyType.name}</t:Cell><t:Cell>$Resource{CompanyNumber}</t:Cell><t:Cell>$Resource{InsuranCompany.name}</t:Cell><t:Cell>$Resource{IsStat}</t:Cell><t:Cell>$Resource{IsWash}</t:Cell><t:Cell>$Resource{IsWaitForStore}</t:Cell><t:Cell>$Resource{IsPriorAssign}</t:Cell><t:Cell>$Resource{IsWorkCost}</t:Cell><t:Cell>$Resource{RepairTotalAmount}</t:Cell><t:Cell>$Resource{RepairWay}</t:Cell><t:Cell>$Resource{groupOrgunit.name}</t:Cell><t:Cell>$Resource{Supplier.name}</t:Cell><t:Cell>$Resource{IsReceive}</t:Cell><t:Cell>$Resource{IsPay}</t:Cell><t:Cell>$Resource{RWOSparepartEntry.Qty}</t:Cell><t:Cell>$Resource{RWOSparepartEntry.IssueQty}</t:Cell><t:Cell>$Resource{RWOSparepartEntry.NoIssueQty}</t:Cell><t:Cell>$Resource{SA.name}</t:Cell><t:Cell>$Resource{bizPerson.name}</t:Cell><t:Cell>$Resource{OrgUnit.name}</t:Cell><t:Cell>$Resource{Brand.name}</t:Cell><t:Cell>$Resource{isPrintedSettle}</t:Cell><t:Cell>$Resource{wipRemark}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.tblMain.setFormatXml(resHelper.translateString("tblMain",tblMainStrXML));
                this.tblMain.putBindContents("mainQuery",new String[] {"id","gaBillStatus","number","dmsWip","Vehicle.plateNum","Vehicle.vIN","series.name","model.name","Customer.name","CustomerDiscount.name","RepairSender","Tel","ComeTime","IntendDeliveryTime","FactDeliveryTime","Mile","KeyNumber","Status","RepairType.name","WarrantyType.name","CompanyNumber","InsuranCompany.name","IsStat","IsWash","IsWaitForStore","IsPriorAssign","IsWorkCost","RepairTotalAmount","RepairWay","groupOrgunit.name","Supplier.name","IsReceive","IsPay","RWOSparepartEntry.Qty","RWOSparepartEntry.IssueQty","RWOSparepartEntry.NoIssueQty","SA.name","bizPerson.name","OrgUnit.name","Brand.name","isPrintedSettle","wipRemark"});


        this.tblMain.checkParsed();
        this.tblMain.getGroupManager().setGroup(true);		
        this.btnLocate.setText(resHelper.getString("btnLocate.text"));		
        this.btnQuery.setText(resHelper.getString("btnQuery.text"));		
        this.separatorFW2.setVisible(true);		
        this.btnAuditResult.setVisible(false);		
        this.menuItemCopyTo.setVisible(false);		
        this.kDSeparator4.setVisible(false);		
        this.kDSeparator6.setVisible(false);		
        this.menuItemViewDoProccess.setVisible(false);		
        this.menuItemAuditResult.setVisible(false);
        // coutComeTime1		
        this.coutComeTime1.setBoundLabelText(resHelper.getString("coutComeTime1.boundLabelText"));		
        this.coutComeTime1.setBoundLabelLength(100);
        // contComeTime2		
        this.contComeTime2.setBoundLabelText(resHelper.getString("contComeTime2.boundLabelText"));		
        this.contComeTime2.setBoundLabelLength(100);
        // contVehicle		
        this.contVehicle.setBoundLabelText(resHelper.getString("contVehicle.boundLabelText"));		
        this.contVehicle.setBoundLabelLength(100);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);
        // contStatus		
        this.contStatus.setBoundLabelText(resHelper.getString("contStatus.boundLabelText"));		
        this.contStatus.setBoundLabelLength(100);
        // contSA		
        this.contSA.setBoundLabelText(resHelper.getString("contSA.boundLabelText"));		
        this.contSA.setBoundLabelLength(100);
        // contOrgUnit		
        this.contOrgUnit.setBoundLabelText(resHelper.getString("contOrgUnit.boundLabelText"));		
        this.contOrgUnit.setBoundLabelLength(100);
        // contBrand		
        this.contBrand.setBoundLabelText(resHelper.getString("contBrand.boundLabelText"));		
        this.contBrand.setBoundLabelLength(100);
        // btnSearch		
        this.btnSearch.setText(resHelper.getString("btnSearch.text"));
        this.btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnSearch_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // btnReset		
        this.btnReset.setText(resHelper.getString("btnReset.text"));
        this.btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnReset_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));		
        this.kDLabelContainer1.setBoundLabelLength(100);
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));		
        this.kDLabelContainer2.setBoundLabelLength(100);
        // kDComeTime1
        // kdComeTime2
        // txtVehicle
        // txtNumber
        // Status		
        this.Status.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum").toArray());		
        this.Status.setVisible(false);
        // prmtSA		
        this.prmtSA.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");		
        this.prmtSA.setDisplayFormat("$name$");		
        this.prmtSA.setEditFormat("$number$");		
        this.prmtSA.setCommitFormat("$number$;$name$");
        // prmtOrgUnit		
        this.prmtOrgUnit.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.ServiceOrgUnitQuery");		
        this.prmtOrgUnit.setCommitFormat("$number$");		
        this.prmtOrgUnit.setEditFormat("$number$");		
        this.prmtOrgUnit.setDisplayFormat("$name$");
        this.prmtOrgUnit.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtOrgUnit_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtBrand		
        this.prmtBrand.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.BrandQuery");		
        this.prmtBrand.setCommitFormat("$number$");		
        this.prmtBrand.setEditFormat("$number$");		
        this.prmtBrand.setDisplayFormat("$name$");
        // txtVIN		
        this.txtVIN.setMaxLength(44);
        // prmtRepairType		
        this.prmtRepairType.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.RepairTypeQuery");		
        this.prmtRepairType.setCommitFormat("$number$");		
        this.prmtRepairType.setEditFormat("$number$");		
        this.prmtRepairType.setDisplayFormat("$name$");
        // separator1
        // btnInvalid
        this.btnInvalid.setAction((IItemAction)ActionProxyFactory.getProxy(actionInvalid, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnInvalid.setText(resHelper.getString("btnInvalid.text"));		
        this.btnInvalid.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_blankout"));
        // btnUninvalid
        this.btnUninvalid.setAction((IItemAction)ActionProxyFactory.getProxy(actionUninvalid, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnUninvalid.setText(resHelper.getString("btnUninvalid.text"));		
        this.btnUninvalid.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_fblankout"));
        // separator2
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
        this.btnRepairHistory.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_find"));
        // separator3
        // btnPricePrint
        this.btnPricePrint.setAction((IItemAction)ActionProxyFactory.getProxy(actionPrintPrice, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnPricePrint.setText(resHelper.getString("btnPricePrint.text"));		
        this.btnPricePrint.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_print"));
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
        // KdBtnViewVipPrefer
        this.KdBtnViewVipPrefer.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewVipPrefer, new Class[] { IItemAction.class }, getServiceContext()));		
        this.KdBtnViewVipPrefer.setText(resHelper.getString("KdBtnViewVipPrefer.text"));
        // kDLabelContainer3		
        this.kDLabelContainer3.setBoundLabelText(resHelper.getString("kDLabelContainer3.boundLabelText"));		
        this.kDLabelContainer3.setBoundLabelLength(100);
        // txtVehicleEx		
        this.txtVehicleEx.setAutoscrolls(false);
        // txtNumberEx		
        this.txtNumberEx.setAutoscrolls(false);
        // cmbGABillStatus		
        this.cmbGABillStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.ga.rs.RepairWOStatusEnum").toArray());
        // txtVINEx		
        this.txtVINEx.setAutoscrolls(false);
        // cmbSettlePrinted		
        this.cmbSettlePrinted.addItems(resHelper.getArray("cmbSettlePrinted.items"));
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
        this.setBounds(new Rectangle(10, 10, 1013, 629));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(10, 10, 1013, 629));
        tblMain.setBounds(new Rectangle(4, 135, 1005, 487));
        this.add(tblMain, new KDLayout.Constraints(4, 135, 1005, 487, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        coutComeTime1.setBounds(new Rectangle(12, 44, 270, 19));
        this.add(coutComeTime1, new KDLayout.Constraints(12, 44, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contComeTime2.setBounds(new Rectangle(364, 44, 270, 19));
        this.add(contComeTime2, new KDLayout.Constraints(364, 44, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contVehicle.setBounds(new Rectangle(364, 13, 270, 19));
        this.add(contVehicle, new KDLayout.Constraints(364, 13, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNumber.setBounds(new Rectangle(12, 13, 270, 19));
        this.add(contNumber, new KDLayout.Constraints(12, 13, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contStatus.setBounds(new Rectangle(722, 44, 270, 19));
        this.add(contStatus, new KDLayout.Constraints(722, 44, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contSA.setBounds(new Rectangle(722, 75, 270, 19));
        this.add(contSA, new KDLayout.Constraints(722, 75, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contOrgUnit.setBounds(new Rectangle(12, 75, 270, 19));
        this.add(contOrgUnit, new KDLayout.Constraints(12, 75, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBrand.setBounds(new Rectangle(364, 75, 270, 19));
        this.add(contBrand, new KDLayout.Constraints(364, 75, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnSearch.setBounds(new Rectangle(733, 106, 73, 21));
        this.add(btnSearch, new KDLayout.Constraints(733, 106, 73, 21, KDLayout.Constraints.ANCHOR_RIGHT));
        btnReset.setBounds(new Rectangle(862, 106, 73, 21));
        this.add(btnReset, new KDLayout.Constraints(862, 106, 73, 21, KDLayout.Constraints.ANCHOR_RIGHT));
        kDLabelContainer1.setBounds(new Rectangle(722, 13, 270, 19));
        this.add(kDLabelContainer1, new KDLayout.Constraints(722, 13, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        kDLabelContainer2.setBounds(new Rectangle(12, 106, 270, 19));
        this.add(kDLabelContainer2, new KDLayout.Constraints(12, 106, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        txtVehicle.setBounds(new Rectangle(353, 3, 170, 19));
        this.add(txtVehicle, new KDLayout.Constraints(353, 3, 170, 19, 0));
        txtNumber.setBounds(new Rectangle(70, 4, 170, 19));
        this.add(txtNumber, new KDLayout.Constraints(70, 4, 170, 19, 0));
        Status.setBounds(new Rectangle(395, 62, 170, 19));
        this.add(Status, new KDLayout.Constraints(395, 62, 170, 19, 0));
        txtVIN.setBounds(new Rectangle(719, -3, 170, 19));
        this.add(txtVIN, new KDLayout.Constraints(719, -3, 170, 19, 0));
        kDLabelContainer3.setBounds(new Rectangle(364, 106, 270, 19));
        this.add(kDLabelContainer3, new KDLayout.Constraints(364, 106, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //coutComeTime1
        coutComeTime1.setBoundEditor(kDComeTime1);
        //contComeTime2
        contComeTime2.setBoundEditor(kdComeTime2);
        //contVehicle
        contVehicle.setBoundEditor(txtVehicleEx);
        //contNumber
        contNumber.setBoundEditor(txtNumberEx);
        //contStatus
        contStatus.setBoundEditor(cmbGABillStatus);
        //contSA
        contSA.setBoundEditor(prmtSA);
        //contOrgUnit
        contOrgUnit.setBoundEditor(prmtOrgUnit);
        //contBrand
        contBrand.setBoundEditor(prmtBrand);
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(txtVINEx);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(prmtRepairType);
        //kDLabelContainer3
        kDLabelContainer3.setBoundEditor(cmbSettlePrinted);

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
        this.menuBar.add(menuWorkFlow);
        this.menuBar.add(menuTools);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(menuItemImportData);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemExportData);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(separatorFile1);
        menuFile.add(menuItemCloudShare);
        menuFile.add(MenuItemAttachment);
        menuFile.add(kDSeparator1);
        menuFile.add(kdSeparatorFWFile1);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemExitCurrent);
        //menuEdit
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(kDSeparator3);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyTo);
        menuEdit.add(kDSeparator4);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemView);
        menuView.add(menuItemLocate);
        menuView.add(kDSeparator5);
        menuView.add(menuItemQuery);
        menuView.add(menuItemRefresh);
        menuView.add(menuItemSwitchView);
        menuView.add(separatorView1);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(menuItemQueryScheme);
        menuView.add(kDSeparator6);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(menuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuWorkFlow
        menuWorkFlow.add(menuItemViewDoProccess);
        menuWorkFlow.add(menuItemMultiapprove);
        menuWorkFlow.add(menuItemWorkFlowG);
        menuWorkFlow.add(menuItemWorkFlowList);
        menuWorkFlow.add(separatorWF1);
        menuWorkFlow.add(menuItemNextPerson);
        menuWorkFlow.add(menuItemAuditResult);
        menuWorkFlow.add(kDSeparator7);
        menuWorkFlow.add(menuItemSendSmsMessage);
        //menuTools
        menuTools.add(menuMail);
        menuTools.add(menuItemStartWorkFlow);
        menuTools.add(menuItemPublishReport);
        //menuMail
        menuMail.add(menuItemToHTML);
        menuMail.add(menuItemCopyScreen);
        menuMail.add(menuItemToExcel);
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
        this.toolBar.add(btnView);
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnEdit);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnRemove);
        this.toolBar.add(separator1);
        this.toolBar.add(btnRefresh);
        this.toolBar.add(btnQuery);
        this.toolBar.add(btnLocate);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnInvalid);
        this.toolBar.add(btnUninvalid);
        this.toolBar.add(separator2);
        this.toolBar.add(btnCustomerInfo);
        this.toolBar.add(btnVehicleInfo);
        this.toolBar.add(btnRepairHistory);
        this.toolBar.add(separator3);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPricePrint);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(btnCopyTo);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(btnQueryScheme);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnWorkFlowList);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnAddCustomer);
        this.toolBar.add(btnAddVehicle);
        this.toolBar.add(kDViewVipCard);
        this.toolBar.add(kDViewVehicleAdvice);
        this.toolBar.add(KdBtnViewVipPrefer);


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.auto4s.rsm.rs.app.RepairWOListUIHandler";
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
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
    }
	protected void Remove() throws Exception {
    	IObjectValue editData = getBizInterface().getValue(new com.kingdee.bos.dao.ormapping.ObjectUuidPK(BOSUuid.read(getSelectedKeyValue())));
    	super.Remove();
    	recycleNumberByOrg(editData,"",editData.getString("number"));
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
			protected com.kingdee.eas.basedata.org.OrgType getMainBizOrgType() {
			return com.kingdee.eas.basedata.org.OrgType.getEnum("Admin");
		}


    /**
     * output loadFields method
     */
    public void loadFields()
    {
        dataBinder.loadFields();
    }
    /**
     * output storeFields method
     */
    public void storeFields()
    {
		dataBinder.storeFields();
    }

	/**
	 * ????????§µ??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
    }

    /**
     * output btnSearch_actionPerformed method
     */
    protected void btnSearch_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnReset_actionPerformed method
     */
    protected void btnReset_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output prmtOrgUnit_dataChanged method
     */
    protected void prmtOrgUnit_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

	public SelectorItemCollection getBOTPSelectors() {
			SelectorItemCollection sic = new SelectorItemCollection();
			return sic;
	}

	protected FilterInfo getDefaultFilterForQuery() {
			FilterInfo filter = super.getDefaultFilterForQuery();
			if (filter == null)
				filter = new FilterInfo();
			FilterInfo otherFilter = com.kingdee.eas.framework.FrameWorkUtils.getF7FilterInfoByAuthorizedOrg(com.kingdee.eas.basedata.org.OrgType.getEnum("Admin"),"OrgUnit.id",true);
			if (otherFilter != null) {
				for(int i=0,n=otherFilter.getFilterItems().size();i<n;i++){
					FilterItemInfo curFilterItem =otherFilter.getFilterItems().get(i);
					if(curFilterItem.getCompareValue()!=null && (curFilterItem.getCompareValue() instanceof String)){
						if("*".equalsIgnoreCase((String)(curFilterItem.getCompareValue()))){
							BOSUuid nullUuid=BOSUuid.create("nullnull");
							curFilterItem.setCompareValue(nullUuid.toString());
						}
					}
				}
				try {	
					if (com.kingdee.eas.framework.util.FilterUtility.hasFilterItem(filter))
						filter.mergeFilter(otherFilter, "AND");
					else
						filter = otherFilter;
				} catch (Exception e) {
					this.handUIException(e);
				}
			}
			return filter;
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
        sic.add(new SelectorItemInfo("id"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("Vehicle.plateNum"));
        sic.add(new SelectorItemInfo("Vehicle.vIN"));
        sic.add(new SelectorItemInfo("series.name"));
        sic.add(new SelectorItemInfo("model.name"));
        sic.add(new SelectorItemInfo("Customer.name"));
        sic.add(new SelectorItemInfo("CustomerDiscount.name"));
        sic.add(new SelectorItemInfo("RepairSender"));
        sic.add(new SelectorItemInfo("Tel"));
        sic.add(new SelectorItemInfo("ComeTime"));
        sic.add(new SelectorItemInfo("IntendDeliveryTime"));
        sic.add(new SelectorItemInfo("Mile"));
        sic.add(new SelectorItemInfo("KeyNumber"));
        sic.add(new SelectorItemInfo("Status"));
        sic.add(new SelectorItemInfo("RepairType.name"));
        sic.add(new SelectorItemInfo("WarrantyType.name"));
        sic.add(new SelectorItemInfo("CompanyNumber"));
        sic.add(new SelectorItemInfo("InsuranCompany.name"));
        sic.add(new SelectorItemInfo("IsStat"));
        sic.add(new SelectorItemInfo("IsWash"));
        sic.add(new SelectorItemInfo("IsWaitForStore"));
        sic.add(new SelectorItemInfo("IsPriorAssign"));
        sic.add(new SelectorItemInfo("RepairTotalAmount"));
        sic.add(new SelectorItemInfo("SA.name"));
        sic.add(new SelectorItemInfo("OrgUnit.name"));
        sic.add(new SelectorItemInfo("Brand.name"));
        sic.add(new SelectorItemInfo("RWOSparepartEntry.Qty"));
        sic.add(new SelectorItemInfo("RWOSparepartEntry.IssueQty"));
        sic.add(new SelectorItemInfo("RWOSparepartEntry.NoIssueQty"));
        sic.add(new SelectorItemInfo("FactDeliveryTime"));
        sic.add(new SelectorItemInfo("IsWorkCost"));
        sic.add(new SelectorItemInfo("RepairWay"));
        sic.add(new SelectorItemInfo("groupOrgunit.name"));
        sic.add(new SelectorItemInfo("Supplier.name"));
        sic.add(new SelectorItemInfo("IsReceive"));
        sic.add(new SelectorItemInfo("IsPay"));
        sic.add(new SelectorItemInfo("gaBillStatus"));
        sic.add(new SelectorItemInfo("isPrintedSettle"));
        sic.add(new SelectorItemInfo("Saler"));
        sic.add(new SelectorItemInfo("wipRemark"));
        sic.add(new SelectorItemInfo("bizPerson.name"));
        sic.add(new SelectorItemInfo("dmsWip"));
        return sic;
    }            protected java.util.List getQuerySorterFields() 
    { 
        java.util.List sorterFieldList = new ArrayList(); 
        sorterFieldList.add("createTime"); 
        return sorterFieldList; 
    } 
    protected java.util.List getQueryPKFields() 
    { 
        java.util.List pkList = new ArrayList(); 
        pkList.add("id"); 
        return pkList;
    }
    	

    /**
     * output actionRemove_actionPerformed method
     */
    public void actionRemove_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionRemove_actionPerformed(e);
    }
    	

    /**
     * output actionTDPrint_actionPerformed method
     */
    public void actionTDPrint_actionPerformed(ActionEvent e) throws Exception
    {
        checkSelected();        
    	ArrayList idList =super.getSelectedIdValues();
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.reportone.r1.print.data.AbstractPrintDataProvider data = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.DefaultNoteDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.print(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionTDPrintPreview_actionPerformed method
     */
    public void actionTDPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        checkSelected();
    	ArrayList idList =super.getSelectedIdValues();
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.reportone.r1.print.data.AbstractPrintDataProvider data = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.DefaultNoteDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.printPreview(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
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
     * output actionPrintPrice_actionPerformed method
     */
    public void actionPrintPrice_actionPerformed(ActionEvent e) throws Exception
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
     * output actionAddCustomer_actionPerformed method
     */
    public void actionAddCustomer_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAddVehicle_actionPerformed method
     */
    public void actionAddVehicle_actionPerformed(ActionEvent e) throws Exception
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
     * output actionAudit_actionPerformed method
     */
    public void actionAudit_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionUnAudit_actionPerformed method
     */
    public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionRemove(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionRemove(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionRemove() {
    	return false;
    }
	public RequestContext prepareActionTDPrint(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionTDPrint() {
    	return false;
    }
	public RequestContext prepareActionTDPrintPreview(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionTDPrintPreview() {
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
	public RequestContext prepareActionAudit(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAudit() {
    	return false;
    }
	public RequestContext prepareActionUnAudit(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionUnAudit() {
    	return false;
    }

    /**
     * output ActionTDPrint class
     */     
    protected class ActionTDPrint extends ItemAction {     
    
        public ActionTDPrint()
        {
            this(null);
        }

        public ActionTDPrint(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionTDPrint.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTDPrint.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTDPrint.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionTDPrint", "actionTDPrint_actionPerformed", e);
        }
    }

    /**
     * output ActionTDPrintPreview class
     */     
    protected class ActionTDPrintPreview extends ItemAction {     
    
        public ActionTDPrintPreview()
        {
            this(null);
        }

        public ActionTDPrintPreview(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionTDPrintPreview.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTDPrintPreview.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTDPrintPreview.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionTDPrintPreview", "actionTDPrintPreview_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionInvalid", "actionInvalid_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionUninvalid", "actionUninvalid_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionPrintPrice", "actionPrintPrice_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionCustomer", "actionCustomer_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionVehicle", "actionVehicle_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionHistory", "actionHistory_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionAddCustomer", "actionAddCustomer_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionAddVehicle", "actionAddVehicle_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionViewVipCard", "actionViewVipCard_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionViewVehicleAdvice", "actionViewVehicleAdvice_actionPerformed", e);
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
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionViewVipPrefer", "actionViewVipPrefer_actionPerformed", e);
        }
    }

    /**
     * output ActionAudit class
     */     
    protected class ActionAudit extends ItemAction {     
    
        public ActionAudit()
        {
            this(null);
        }

        public ActionAudit(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAudit.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAudit.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAudit.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionAudit", "actionAudit_actionPerformed", e);
        }
    }

    /**
     * output ActionUnAudit class
     */     
    protected class ActionUnAudit extends ItemAction {     
    
        public ActionUnAudit()
        {
            this(null);
        }

        public ActionUnAudit(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionUnAudit.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionUnAudit.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionUnAudit.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractRepairWOListUI.this, "ActionUnAudit", "actionUnAudit_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.auto4s.rsm.rs.client", "RepairWOListUI");
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
        return objectValue;
    }

    /**
     * output getMergeColumnKeys method
     */
    public String[] getMergeColumnKeys()
    {
        return new String[] {"id","gaBillStatus","number","dmswip","Vehicle.plateNum","Vehicle.vIN","series.name","model.name","Customer.name","CustomerDiscount.name","RepairSender","Tel","ComeTime","IntendDeliveryTime","FactDeliveryTime","Mile","KeyNumber","Status","RepairType.name","WarrantyType.name","CompanyNumber","InsuranCompany.name","IsStat","IsWash","IsWaitForStore","IsPriorAssign","IsWorkCost","RepairTotalAmount","RepairWay","groupOrgunit.name","Supplier.name","IsReceive","IsPay","SA.name","bizPerson.name","OrgUnit.name","Brand.name","isPrintedSettle","wipRemark"};
    }



	protected String getTDFileName() {
    	return "/bim/auto4s/rsm/rs/RepairWO";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.auto4s.rsm.rs.app.RepairWOQuery");
	}        
				protected boolean isFootVisible() {
			return true;
		}


}