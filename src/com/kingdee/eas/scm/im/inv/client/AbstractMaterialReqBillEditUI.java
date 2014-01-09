/**
 * output package name
 */
package com.kingdee.eas.scm.im.inv.client;

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
public abstract class AbstractMaterialReqBillEditUI extends com.kingdee.eas.scm.im.inv.client.InvBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractMaterialReqBillEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditor;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBaseStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contTransactionType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contStorageOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDepartment;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contSourceBillType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contTotalAmount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAddStandardCost;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAddFactCost;
    protected com.kingdee.bos.ctrl.swing.KDSeparator kDSeparatorLine;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox cboxIsOffset;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox cboxIsInitBill;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCostCenterUnit;
    protected com.kingdee.bos.ctrl.swing.KDContainer ctnMain;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizType;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox isBackflush;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsupplier;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer supplycon;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer prmtPurchaseType;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCreator;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDPCreatDate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDPUpdatDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkBizDate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtAuditor;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDPAuditDate;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboBaseStatus;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtTransactionType;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtStorageOrgUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtDepartment;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtSourceBillType;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtTotalAmount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtAddStandardCost;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtAddFactCost;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCostCenterUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox bizInventory;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kdtabbedData;
    protected com.kingdee.bos.ctrl.swing.KDPanel basePanel;
    protected com.kingdee.bos.ctrl.swing.KDPanel priceInfoPanel;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtEntry;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdPriceInfo;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtBizType;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtDescription;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtHeadSupplier;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtSupplyStorageOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDComboBox purchaseType;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnOffSet;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnQuickAddLine;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemOffset;
    protected com.kingdee.bos.ctrl.swing.KDCheckBoxMenuItem chkmenuItemCostObjectSuite;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemQuickAddLine;
    protected com.kingdee.eas.scm.im.inv.MaterialReqBillInfo editData = null;
    protected ActionOffSet actionOffSet = null;
    protected ActionQuickAddLine actionQuickAddLine = null;
    protected ActionCalculateLot actionCalculateLot = null;
    protected ActionCostObjectSuite actionCostObjectSuite = null;
    protected ActionCalculateDynQty actionCalculateDynQty = null;
    /**
     * output class constructor
     */
    public AbstractMaterialReqBillEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractMaterialReqBillEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSubmit
        String _tempStr = null;
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        actionSubmit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        actionSubmit.putValue(ItemAction.MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
        _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
        actionSubmit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
        actionSubmit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.NAME");
        actionSubmit.putValue(ItemAction.NAME, _tempStr);
        this.actionSubmit.setBindWorkFlow(true);
        this.actionSubmit.setExtendProperty("Mutex", "Lock_SCMIM_CloseAccount,0");
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
        //actionAddNew
        actionAddNew.setEnabled(true);
        actionAddNew.setDaemonRun(false);

        actionAddNew.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl N"));
        _tempStr = resHelper.getString("ActionAddNew.SHORT_DESCRIPTION");
        actionAddNew.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAddNew.LONG_DESCRIPTION");
        actionAddNew.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAddNew.NAME");
        actionAddNew.putValue(ItemAction.NAME, _tempStr);
        this.actionAddNew.setExtendProperty("Mutex", "Lock_SCMIM_CloseAccount,0");
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionEdit
        actionEdit.setEnabled(true);
        actionEdit.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionEdit.SHORT_DESCRIPTION");
        actionEdit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionEdit.LONG_DESCRIPTION");
        actionEdit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionEdit.NAME");
        actionEdit.putValue(ItemAction.NAME, _tempStr);
        this.actionEdit.setExtendProperty("Mutex", "Lock_SCMIM_CloseAccount,0");
         this.actionEdit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionEdit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionEdit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionMultiapprove
        actionMultiapprove.setEnabled(true);
        actionMultiapprove.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionMultiapprove.SHORT_DESCRIPTION");
        actionMultiapprove.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionMultiapprove.LONG_DESCRIPTION");
        actionMultiapprove.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionMultiapprove.NAME");
        actionMultiapprove.putValue(ItemAction.NAME, _tempStr);
         this.actionMultiapprove.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionNextPerson
        actionNextPerson.setEnabled(true);
        actionNextPerson.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionNextPerson.SHORT_DESCRIPTION");
        actionNextPerson.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionNextPerson.LONG_DESCRIPTION");
        actionNextPerson.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionNextPerson.NAME");
        actionNextPerson.putValue(ItemAction.NAME, _tempStr);
         this.actionNextPerson.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAudit
        actionAudit.setEnabled(true);
        actionAudit.setDaemonRun(false);

        actionAudit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl F9"));
        _tempStr = resHelper.getString("ActionAudit.SHORT_DESCRIPTION");
        actionAudit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAudit.LONG_DESCRIPTION");
        actionAudit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAudit.NAME");
        actionAudit.putValue(ItemAction.NAME, _tempStr);
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionUnAudit
        actionUnAudit.setEnabled(true);
        actionUnAudit.setDaemonRun(false);

        actionUnAudit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl F10"));
        _tempStr = resHelper.getString("ActionUnAudit.SHORT_DESCRIPTION");
        actionUnAudit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionUnAudit.LONG_DESCRIPTION");
        actionUnAudit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionUnAudit.NAME");
        actionUnAudit.putValue(ItemAction.NAME, _tempStr);
        this.actionUnAudit.setBindWorkFlow(true);
         this.actionUnAudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionOffSet
        this.actionOffSet = new ActionOffSet(this);
        getActionManager().registerAction("actionOffSet", actionOffSet);
         this.actionOffSet.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionQuickAddLine
        this.actionQuickAddLine = new ActionQuickAddLine(this);
        getActionManager().registerAction("actionQuickAddLine", actionQuickAddLine);
         this.actionQuickAddLine.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCalculateLot
        this.actionCalculateLot = new ActionCalculateLot(this);
        getActionManager().registerAction("actionCalculateLot", actionCalculateLot);
         this.actionCalculateLot.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCostObjectSuite
        this.actionCostObjectSuite = new ActionCostObjectSuite(this);
        getActionManager().registerAction("actionCostObjectSuite", actionCostObjectSuite);
         this.actionCostObjectSuite.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCalculateDynQty
        this.actionCalculateDynQty = new ActionCalculateDynQty(this);
        getActionManager().registerAction("actionCalculateDynQty", actionCalculateDynQty);
         this.actionCalculateDynQty.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.contCreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateUser = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBizDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditor = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBaseStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contTransactionType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contStorageOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDepartment = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contSourceBillType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contTotalAmount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAddStandardCost = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAddFactCost = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDSeparatorLine = new com.kingdee.bos.ctrl.swing.KDSeparator();
        this.cboxIsOffset = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.cboxIsInitBill = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contCostCenterUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.ctnMain = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.contBizType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.isBackflush = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contDescription = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsupplier = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.supplycon = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtPurchaseType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtCreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDPCreatDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtLastUpdateUser = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDPUpdatDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkBizDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtAuditor = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDPAuditDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.comboBaseStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtTransactionType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtStorageOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtDepartment = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtSourceBillType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtTotalAmount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtAddStandardCost = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtAddFactCost = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.prmtCostCenterUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.bizInventory = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kdtabbedData = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.basePanel = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.priceInfoPanel = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kdtEntry = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdPriceInfo = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.prmtBizType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtDescription = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtHeadSupplier = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtSupplyStorageOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.purchaseType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.btnOffSet = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnQuickAddLine = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.menuItemOffset = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.chkmenuItemCostObjectSuite = new com.kingdee.bos.ctrl.swing.KDCheckBoxMenuItem();
        this.menuItemQuickAddLine = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.contCreator.setName("contCreator");
        this.contCreateTime.setName("contCreateTime");
        this.contLastUpdateUser.setName("contLastUpdateUser");
        this.contLastUpdateTime.setName("contLastUpdateTime");
        this.contNumber.setName("contNumber");
        this.contBizDate.setName("contBizDate");
        this.contAuditor.setName("contAuditor");
        this.contAuditTime.setName("contAuditTime");
        this.contBaseStatus.setName("contBaseStatus");
        this.contTransactionType.setName("contTransactionType");
        this.contStorageOrgUnit.setName("contStorageOrgUnit");
        this.contDepartment.setName("contDepartment");
        this.contSourceBillType.setName("contSourceBillType");
        this.contTotalAmount.setName("contTotalAmount");
        this.contAddStandardCost.setName("contAddStandardCost");
        this.contAddFactCost.setName("contAddFactCost");
        this.kDSeparatorLine.setName("kDSeparatorLine");
        this.cboxIsOffset.setName("cboxIsOffset");
        this.cboxIsInitBill.setName("cboxIsInitBill");
        this.contCostCenterUnit.setName("contCostCenterUnit");
        this.ctnMain.setName("ctnMain");
        this.contBizType.setName("contBizType");
        this.isBackflush.setName("isBackflush");
        this.contDescription.setName("contDescription");
        this.contsupplier.setName("contsupplier");
        this.supplycon.setName("supplycon");
        this.prmtPurchaseType.setName("prmtPurchaseType");
        this.prmtCreator.setName("prmtCreator");
        this.kDDPCreatDate.setName("kDDPCreatDate");
        this.prmtLastUpdateUser.setName("prmtLastUpdateUser");
        this.kDDPUpdatDate.setName("kDDPUpdatDate");
        this.txtNumber.setName("txtNumber");
        this.pkBizDate.setName("pkBizDate");
        this.prmtAuditor.setName("prmtAuditor");
        this.kDDPAuditDate.setName("kDDPAuditDate");
        this.comboBaseStatus.setName("comboBaseStatus");
        this.prmtTransactionType.setName("prmtTransactionType");
        this.prmtStorageOrgUnit.setName("prmtStorageOrgUnit");
        this.prmtDepartment.setName("prmtDepartment");
        this.prmtSourceBillType.setName("prmtSourceBillType");
        this.txtTotalAmount.setName("txtTotalAmount");
        this.txtAddStandardCost.setName("txtAddStandardCost");
        this.txtAddFactCost.setName("txtAddFactCost");
        this.prmtCostCenterUnit.setName("prmtCostCenterUnit");
        this.bizInventory.setName("bizInventory");
        this.kdtabbedData.setName("kdtabbedData");
        this.basePanel.setName("basePanel");
        this.priceInfoPanel.setName("priceInfoPanel");
        this.kdtEntry.setName("kdtEntry");
        this.kdPriceInfo.setName("kdPriceInfo");
        this.prmtBizType.setName("prmtBizType");
        this.txtDescription.setName("txtDescription");
        this.prmtHeadSupplier.setName("prmtHeadSupplier");
        this.prmtSupplyStorageOrgUnit.setName("prmtSupplyStorageOrgUnit");
        this.purchaseType.setName("purchaseType");
        this.btnOffSet.setName("btnOffSet");
        this.btnQuickAddLine.setName("btnQuickAddLine");
        this.menuItemOffset.setName("menuItemOffset");
        this.chkmenuItemCostObjectSuite.setName("chkmenuItemCostObjectSuite");
        this.menuItemQuickAddLine.setName("menuItemQuickAddLine");
        // CoreUI		
        this.setPreferredSize(new Dimension(1013,629));		
        this.btnCancelCancel.setVisible(false);		
        this.btnCancelCancel.setEnabled(false);		
        this.btnCancel.setEnabled(false);		
        this.btnCancel.setVisible(false);		
        this.kDSeparator3.setVisible(true);		
        this.separatorFile1.setVisible(false);		
        this.menuItemCancelCancel.setVisible(false);		
        this.menuItemCancel.setVisible(false);		
        this.separatorFW1.setVisible(false);		
        this.btnCopyFrom.setVisible(false);		
        this.btnCopyFrom.setEnabled(false);		
        this.btnAuditResult.setVisible(false);		
        this.separator2.setVisible(true);		
        this.menuItemAuditResult.setVisible(false);		
        this.separatorEdit1.setVisible(true);		
        this.kDSeparator6.setVisible(false);		
        this.separator4.setOrientation(1);		
        this.separator5.setOrientation(1);		
        this.separator6.setOrientation(1);		
        this.separator7.setEnabled(true);		
        this.separator7.setVisible(true);		
        this.menuItemQueryGeneralInventory.setText(resHelper.getString("menuItemQueryGeneralInventory.text"));		
        this.menuItemQueryGeneralInventory.setMnemonic(84);		
        this.menuItemQueryByMaterial.setText(resHelper.getString("menuItemQueryByMaterial.text"));		
        this.menuItemQueryByMaterial.setMnemonic(67);		
        this.menuItemMaterialView.setText(resHelper.getString("menuItemMaterialView.text"));		
        this.menuItemMaterialView.setMnemonic(65);		
        this.menuBizProcess.setVisible(true);
        // contCreator		
        this.contCreator.setBoundLabelText(resHelper.getString("contCreator.boundLabelText"));		
        this.contCreator.setBoundLabelLength(100);		
        this.contCreator.setBoundLabelUnderline(true);		
        this.contCreator.setBoundLabelAlignment(7);		
        this.contCreator.setVisible(true);
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);		
        this.contCreateTime.setBoundLabelAlignment(7);		
        this.contCreateTime.setVisible(true);
        // contLastUpdateUser		
        this.contLastUpdateUser.setBoundLabelText(resHelper.getString("contLastUpdateUser.boundLabelText"));		
        this.contLastUpdateUser.setBoundLabelLength(100);		
        this.contLastUpdateUser.setBoundLabelUnderline(true);		
        this.contLastUpdateUser.setBoundLabelAlignment(7);		
        this.contLastUpdateUser.setVisible(true);
        // contLastUpdateTime		
        this.contLastUpdateTime.setBoundLabelText(resHelper.getString("contLastUpdateTime.boundLabelText"));		
        this.contLastUpdateTime.setBoundLabelLength(100);		
        this.contLastUpdateTime.setBoundLabelUnderline(true);		
        this.contLastUpdateTime.setBoundLabelAlignment(7);		
        this.contLastUpdateTime.setVisible(true);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);		
        this.contNumber.setBoundLabelAlignment(7);		
        this.contNumber.setVisible(true);
        // contBizDate		
        this.contBizDate.setBoundLabelText(resHelper.getString("contBizDate.boundLabelText"));		
        this.contBizDate.setBoundLabelLength(100);		
        this.contBizDate.setBoundLabelUnderline(true);		
        this.contBizDate.setBoundLabelAlignment(7);		
        this.contBizDate.setVisible(true);
        // contAuditor		
        this.contAuditor.setBoundLabelText(resHelper.getString("contAuditor.boundLabelText"));		
        this.contAuditor.setBoundLabelLength(100);		
        this.contAuditor.setBoundLabelUnderline(true);		
        this.contAuditor.setBoundLabelAlignment(7);		
        this.contAuditor.setVisible(true);
        // contAuditTime		
        this.contAuditTime.setBoundLabelText(resHelper.getString("contAuditTime.boundLabelText"));		
        this.contAuditTime.setBoundLabelLength(100);		
        this.contAuditTime.setBoundLabelUnderline(true);		
        this.contAuditTime.setBoundLabelAlignment(7);		
        this.contAuditTime.setVisible(true);
        // contBaseStatus		
        this.contBaseStatus.setBoundLabelText(resHelper.getString("contBaseStatus.boundLabelText"));		
        this.contBaseStatus.setBoundLabelLength(100);		
        this.contBaseStatus.setBoundLabelUnderline(true);		
        this.contBaseStatus.setBoundLabelAlignment(7);		
        this.contBaseStatus.setVisible(true);
        // contTransactionType		
        this.contTransactionType.setBoundLabelText(resHelper.getString("contTransactionType.boundLabelText"));		
        this.contTransactionType.setBoundLabelLength(100);		
        this.contTransactionType.setBoundLabelUnderline(true);		
        this.contTransactionType.setBoundLabelAlignment(7);		
        this.contTransactionType.setVisible(true);
        // contStorageOrgUnit		
        this.contStorageOrgUnit.setBoundLabelText(resHelper.getString("contStorageOrgUnit.boundLabelText"));		
        this.contStorageOrgUnit.setBoundLabelLength(100);		
        this.contStorageOrgUnit.setBoundLabelUnderline(true);		
        this.contStorageOrgUnit.setBoundLabelAlignment(7);		
        this.contStorageOrgUnit.setVisible(true);
        // contDepartment		
        this.contDepartment.setBoundLabelText(resHelper.getString("contDepartment.boundLabelText"));		
        this.contDepartment.setBoundLabelLength(100);		
        this.contDepartment.setBoundLabelUnderline(true);		
        this.contDepartment.setBoundLabelAlignment(7);		
        this.contDepartment.setVisible(true);
        // contSourceBillType		
        this.contSourceBillType.setBoundLabelText(resHelper.getString("contSourceBillType.boundLabelText"));		
        this.contSourceBillType.setBoundLabelLength(100);		
        this.contSourceBillType.setBoundLabelUnderline(true);		
        this.contSourceBillType.setBoundLabelAlignment(7);		
        this.contSourceBillType.setVisible(true);
        // contTotalAmount		
        this.contTotalAmount.setBoundLabelText(resHelper.getString("contTotalAmount.boundLabelText"));		
        this.contTotalAmount.setBoundLabelLength(100);		
        this.contTotalAmount.setBoundLabelUnderline(true);		
        this.contTotalAmount.setVisible(false);		
        this.contTotalAmount.setBoundLabelAlignment(7);
        // contAddStandardCost		
        this.contAddStandardCost.setBoundLabelText(resHelper.getString("contAddStandardCost.boundLabelText"));		
        this.contAddStandardCost.setBoundLabelLength(100);		
        this.contAddStandardCost.setBoundLabelUnderline(true);		
        this.contAddStandardCost.setBoundLabelAlignment(7);		
        this.contAddStandardCost.setVisible(false);
        // contAddFactCost		
        this.contAddFactCost.setBoundLabelText(resHelper.getString("contAddFactCost.boundLabelText"));		
        this.contAddFactCost.setBoundLabelLength(100);		
        this.contAddFactCost.setBoundLabelUnderline(true);		
        this.contAddFactCost.setBoundLabelAlignment(7);		
        this.contAddFactCost.setVisible(false);
        // kDSeparatorLine		
        this.kDSeparatorLine.setVisible(true);		
        this.kDSeparatorLine.setEnabled(true);
        // cboxIsOffset		
        this.cboxIsOffset.setText(resHelper.getString("cboxIsOffset.text"));		
        this.cboxIsOffset.setEnabled(false);		
        this.cboxIsOffset.setVisible(true);		
        this.cboxIsOffset.setHorizontalAlignment(2);
        // cboxIsInitBill		
        this.cboxIsInitBill.setText(resHelper.getString("cboxIsInitBill.text"));		
        this.cboxIsInitBill.setVisible(false);		
        this.cboxIsInitBill.setEnabled(false);		
        this.cboxIsInitBill.setHorizontalAlignment(2);
        // contCostCenterUnit		
        this.contCostCenterUnit.setBoundLabelText(resHelper.getString("contCostCenterUnit.boundLabelText"));		
        this.contCostCenterUnit.setBoundLabelLength(100);		
        this.contCostCenterUnit.setBoundLabelUnderline(true);		
        this.contCostCenterUnit.setBoundLabelAlignment(7);		
        this.contCostCenterUnit.setVisible(true);
        // ctnMain
        // contBizType		
        this.contBizType.setBoundLabelText(resHelper.getString("contBizType.boundLabelText"));		
        this.contBizType.setBoundLabelLength(100);		
        this.contBizType.setBoundLabelUnderline(true);		
        this.contBizType.setBoundLabelAlignment(7);		
        this.contBizType.setVisible(true);
        // isBackflush		
        this.isBackflush.setText(resHelper.getString("isBackflush.text"));		
        this.isBackflush.setVisible(false);		
        this.isBackflush.setHorizontalAlignment(2);
        // contDescription		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));		
        this.contDescription.setBoundLabelLength(100);		
        this.contDescription.setBoundLabelUnderline(true);		
        this.contDescription.setBoundLabelAlignment(7);		
        this.contDescription.setVisible(true);
        // contsupplier		
        this.contsupplier.setBoundLabelText(resHelper.getString("contsupplier.boundLabelText"));		
        this.contsupplier.setBoundLabelLength(100);		
        this.contsupplier.setBoundLabelUnderline(true);		
        this.contsupplier.setBoundLabelAlignment(7);		
        this.contsupplier.setVisible(true);
        // supplycon		
        this.supplycon.setBoundLabelText(resHelper.getString("supplycon.boundLabelText"));		
        this.supplycon.setBoundLabelLength(100);		
        this.supplycon.setBoundLabelUnderline(true);		
        this.supplycon.setBoundLabelAlignment(7);		
        this.supplycon.setVisible(true);
        // prmtPurchaseType		
        this.prmtPurchaseType.setBoundLabelText(resHelper.getString("prmtPurchaseType.boundLabelText"));		
        this.prmtPurchaseType.setBoundLabelLength(100);		
        this.prmtPurchaseType.setBoundLabelUnderline(true);		
        this.prmtPurchaseType.setBoundLabelAlignment(7);		
        this.prmtPurchaseType.setVisible(false);
        // prmtCreator		
        this.prmtCreator.setDisplayFormat("$name$");		
        this.prmtCreator.setEditFormat("$number$");		
        this.prmtCreator.setCommitFormat("$number$");		
        this.prmtCreator.setQueryInfo("com.kingdee.eas.base.permission.app.UserSimpleQuery");		
        this.prmtCreator.setEnabled(false);		
        this.prmtCreator.setVisible(true);		
        this.prmtCreator.setRequired(false);
        // kDDPCreatDate		
        this.kDDPCreatDate.setEnabled(false);		
        this.kDDPCreatDate.setVisible(true);		
        this.kDDPCreatDate.setRequired(false);
        // prmtLastUpdateUser		
        this.prmtLastUpdateUser.setDisplayFormat("$name$");		
        this.prmtLastUpdateUser.setEditFormat("$number$");		
        this.prmtLastUpdateUser.setCommitFormat("$number$");		
        this.prmtLastUpdateUser.setQueryInfo("com.kingdee.eas.base.permission.app.UserSimpleQuery");		
        this.prmtLastUpdateUser.setEnabled(false);		
        this.prmtLastUpdateUser.setVisible(true);		
        this.prmtLastUpdateUser.setRequired(false);
        // kDDPUpdatDate		
        this.kDDPUpdatDate.setEnabled(false);		
        this.kDDPUpdatDate.setVisible(true);		
        this.kDDPUpdatDate.setRequired(false);
        // txtNumber		
        this.txtNumber.setRequired(true);		
        this.txtNumber.setVisible(true);		
        this.txtNumber.setEnabled(true);		
        this.txtNumber.setHorizontalAlignment(2);
        // pkBizDate		
        this.pkBizDate.setRequired(true);		
        this.pkBizDate.setVisible(true);		
        this.pkBizDate.setEnabled(true);
        // prmtAuditor		
        this.prmtAuditor.setDisplayFormat("$name$");		
        this.prmtAuditor.setEditFormat("$number$");		
        this.prmtAuditor.setCommitFormat("$number$");		
        this.prmtAuditor.setQueryInfo("com.kingdee.eas.base.permission.app.UserSimpleQuery");		
        this.prmtAuditor.setEnabled(false);		
        this.prmtAuditor.setVisible(true);		
        this.prmtAuditor.setRequired(false);
        // kDDPAuditDate		
        this.kDDPAuditDate.setEnabled(false);		
        this.kDDPAuditDate.setVisible(true);		
        this.kDDPAuditDate.setRequired(false);
        // comboBaseStatus		
        this.comboBaseStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.scm.common.BillBaseStatusEnum").toArray());		
        this.comboBaseStatus.setRequired(true);		
        this.comboBaseStatus.setEnabled(false);		
        this.comboBaseStatus.setVisible(true);
        // prmtTransactionType		
        this.prmtTransactionType.setLabelVisible(true);		
        this.prmtTransactionType.setDisplayFormat("$name$");		
        this.prmtTransactionType.setEditFormat("$number$");		
        this.prmtTransactionType.setCommitFormat("$number$");		
        this.prmtTransactionType.setQueryInfo("com.kingdee.eas.basedata.scm.common.app.F7TransactionTypeInvQuery");		
        this.prmtTransactionType.setRequired(true);		
        this.prmtTransactionType.setEditable(true);		
        this.prmtTransactionType.setVisible(true);		
        this.prmtTransactionType.setEnabled(true);
        // prmtStorageOrgUnit		
        this.prmtStorageOrgUnit.setLabelVisible(true);		
        this.prmtStorageOrgUnit.setDisplayFormat("$name$");		
        this.prmtStorageOrgUnit.setEditFormat("$number$");		
        this.prmtStorageOrgUnit.setCommitFormat("$number$");		
        this.prmtStorageOrgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.StorageOrgUnitQuery");		
        this.prmtStorageOrgUnit.setRequired(true);		
        this.prmtStorageOrgUnit.setEditable(true);		
        this.prmtStorageOrgUnit.setVisible(true);		
        this.prmtStorageOrgUnit.setEnabled(true);
        // prmtDepartment		
        this.prmtDepartment.setLabelVisible(true);		
        this.prmtDepartment.setDisplayFormat("$name$");		
        this.prmtDepartment.setEditFormat("$number$");		
        this.prmtDepartment.setCommitFormat("$number$");		
        this.prmtDepartment.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminOrgUnitQuery");		
        this.prmtDepartment.setEditable(true);		
        this.prmtDepartment.setVisible(true);		
        this.prmtDepartment.setEnabled(true);		
        this.prmtDepartment.setRequired(false);
        // prmtSourceBillType		
        this.prmtSourceBillType.setDisplayFormat("$name$");		
        this.prmtSourceBillType.setEditFormat("$number$");		
        this.prmtSourceBillType.setCommitFormat("$number$");		
        this.prmtSourceBillType.setQueryInfo("com.kingdee.eas.basedata.scm.common.app.F7BillTypeQuery");		
        this.prmtSourceBillType.setEditable(true);		
        this.prmtSourceBillType.setEnabled(false);		
        this.prmtSourceBillType.setVisible(true);		
        this.prmtSourceBillType.setRequired(false);
        // txtTotalAmount		
        this.txtTotalAmount.setEnabled(false);		
        this.txtTotalAmount.setDataType(1);		
        this.txtTotalAmount.setVisible(false);		
        this.txtTotalAmount.setHorizontalAlignment(2);		
        this.txtTotalAmount.setRequired(false);
        // txtAddStandardCost		
        this.txtAddStandardCost.setEnabled(false);		
        this.txtAddStandardCost.setDataType(1);		
        this.txtAddStandardCost.setVisible(true);		
        this.txtAddStandardCost.setHorizontalAlignment(2);		
        this.txtAddStandardCost.setRequired(false);
        // txtAddFactCost		
        this.txtAddFactCost.setEnabled(false);		
        this.txtAddFactCost.setDataType(1);		
        this.txtAddFactCost.setVisible(true);		
        this.txtAddFactCost.setHorizontalAlignment(2);		
        this.txtAddFactCost.setRequired(false);
        // prmtCostCenterUnit		
        this.prmtCostCenterUnit.setDisplayFormat("$name$");		
        this.prmtCostCenterUnit.setEditFormat("$number$");		
        this.prmtCostCenterUnit.setCommitFormat("$number$");		
        this.prmtCostCenterUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.CostCenterItemQuery");		
        this.prmtCostCenterUnit.setEnabled(false);		
        this.prmtCostCenterUnit.setLabelVisible(true);		
        this.prmtCostCenterUnit.setEditable(true);		
        this.prmtCostCenterUnit.setVisible(true);		
        this.prmtCostCenterUnit.setRequired(false);
        // bizInventory		
        this.bizInventory.setQueryInfo("com.kingdee.eas.scm.im.inv.InventoryF7Query");		
        this.bizInventory.setEnabled(false);		
        this.bizInventory.setVisible(false);		
        this.bizInventory.setRequired(false);
        // kdtabbedData
        // basePanel
        // priceInfoPanel
        // kdtEntry
		String kdtEntryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol1\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol3\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol4\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol5\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol6\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol7\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol8\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol9\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol15\"><c:NumberFormat>%{yyyy-MM-dd}t</c:NumberFormat></c:Style><c:Style id=\"sCol16\"><c:NumberFormat>%{yyyy-MM-dd}t</c:NumberFormat></c:Style><c:Style id=\"sCol42\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol46\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol47\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol48\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol49\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol54\"><c:NumberFormat>0</c:NumberFormat></c:Style><c:Style id=\"sCol55\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol56\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol73\"><c:NumberFormat>%{yyyy-MM-dd}t</c:NumberFormat></c:Style><c:Style id=\"sCol74\"><c:NumberFormat>%{yyyy-MM-dd hh:mm:ss}t</c:NumberFormat></c:Style><c:Style id=\"sCol77\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"ID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"unitPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol1\" /><t:Column t:key=\"amount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol2\" /><t:Column t:key=\"periodValid\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol3\" /><t:Column t:key=\"periodValidUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /><t:Column t:key=\"productID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"productName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /><t:Column t:key=\"processMaterialID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" t:styleID=\"sCol8\" /><t:Column t:key=\"processMaterialName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" t:styleID=\"sCol9\" /><t:Column t:key=\"materialID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" /><t:Column t:key=\"materialName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" /><t:Column t:key=\"materialMode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" /><t:Column t:key=\"assistantAttr\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" /><t:Column t:key=\"lot\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" /><t:Column t:key=\"mfg\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" t:styleID=\"sCol15\" /><t:Column t:key=\"exp\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" t:styleID=\"sCol16\" /><t:Column t:key=\"mainAdminOrgUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"17\" /><t:Column t:key=\"costCenterOrgUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" /><t:Column t:key=\"costObject\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"19\" /><t:Column t:key=\"costObjectName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"20\" /><t:Column t:key=\"costObjectSuite\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"21\" /><t:Column t:key=\"costObjectSuiteName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"22\" /><t:Column t:key=\"costItem\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"23\" /><t:Column t:key=\"costItemName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"24\" /><t:Column t:key=\"UnitID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"25\" /><t:Column t:key=\"qty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"26\" /><t:Column t:key=\"baseUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"27\" /><t:Column t:key=\"baseQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"28\" /><t:Column t:key=\"assistantUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"29\" /><t:Column t:key=\"assistantQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"30\" /><t:Column t:key=\"warehouse\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"31\" /><t:Column t:key=\"location\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"32\" /><t:Column t:key=\"stocker\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"33\" /><t:Column t:key=\"picker\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"34\" /><t:Column t:key=\"supplyWareHs\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"35\" /><t:Column t:key=\"supplyLocation\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"36\" /><t:Column t:key=\"customer\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"37\" /><t:Column t:key=\"supplier\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"38\" /><t:Column t:key=\"isPresent\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"39\" /><t:Column t:key=\"unitStandardCost\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"40\" /><t:Column t:key=\"standardCost\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"41\" /><t:Column t:key=\"unitFactCost\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"42\" t:styleID=\"sCol42\" /><t:Column t:key=\"factCost\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"43\" /><t:Column t:key=\"countervailQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"44\" /><t:Column t:key=\"faCardQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"45\" /><t:Column t:key=\"subWrittenOffQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"46\" t:styleID=\"sCol46\" /><t:Column t:key=\"subWrittenOffBaseQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"47\" t:styleID=\"sCol47\" /><t:Column t:key=\"subUnWriteOffQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"48\" t:styleID=\"sCol48\" /><t:Column t:key=\"subUnWriteOffBaseQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"49\" t:styleID=\"sCol49\" /><t:Column t:key=\"writtenOffAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"50\" /><t:Column t:key=\"unWriteOffAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"51\" /><t:Column t:key=\"coreBillType\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"52\" /><t:Column t:key=\"coreBillNumber\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"53\" /><t:Column t:key=\"coreBillEntrySe\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"54\" t:styleID=\"sCol54\" /><t:Column t:key=\"issueAdminOrgUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"55\" t:styleID=\"sCol55\" /><t:Column t:key=\"issuePerson\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"56\" t:styleID=\"sCol56\" /><t:Column t:key=\"poBillID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"57\" /><t:Column t:key=\"operationNo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"58\" /><t:Column t:key=\"operationID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"59\" /><t:Column t:key=\"operationNAME\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"60\" /><t:Column t:key=\"workCenterID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"61\" /><t:Column t:key=\"workCenterNAME\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"62\" /><t:Column t:key=\"remark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"63\" /><t:Column t:key=\"productLine\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"64\" /><t:Column t:key=\"classGroup\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"65\" /><t:Column t:key=\"person\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"66\" /><t:Column t:key=\"productLineWP\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"67\" /><t:Column t:key=\"projectNumCol\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"68\" /><t:Column t:key=\"trackNumCol\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"69\" /><t:Column t:key=\"saleOrderNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"70\" /><t:Column t:key=\"issueQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"71\" /><t:Column t:key=\"baseIssueQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"72\" /><t:Column t:key=\"pickingDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"73\" t:styleID=\"sCol73\" /><t:Column t:key=\"demandDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"74\" t:styleID=\"sCol74\" /><t:Column t:key=\"isAdmeasure\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"75\" /><t:Column t:key=\"isReWork\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"76\" /><t:Column t:key=\"settlePrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"77\" t:styleID=\"sCol77\" /><t:Column t:key=\"isAdjust\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"adjustNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{ID}</t:Cell><t:Cell>$Resource{unitPrice}</t:Cell><t:Cell>$Resource{amount}</t:Cell><t:Cell>$Resource{periodValid}</t:Cell><t:Cell>$Resource{periodValidUnit}</t:Cell><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{productID}</t:Cell><t:Cell>$Resource{productName}</t:Cell><t:Cell>$Resource{processMaterialID}</t:Cell><t:Cell>$Resource{processMaterialName}</t:Cell><t:Cell>$Resource{materialID}</t:Cell><t:Cell>$Resource{materialName}</t:Cell><t:Cell>$Resource{materialMode}</t:Cell><t:Cell>$Resource{assistantAttr}</t:Cell><t:Cell>$Resource{lot}</t:Cell><t:Cell>$Resource{mfg}</t:Cell><t:Cell>$Resource{exp}</t:Cell><t:Cell>$Resource{mainAdminOrgUnit}</t:Cell><t:Cell>$Resource{costCenterOrgUnit}</t:Cell><t:Cell>$Resource{costObject}</t:Cell><t:Cell>$Resource{costObjectName}</t:Cell><t:Cell>$Resource{costObjectSuite}</t:Cell><t:Cell>$Resource{costObjectSuiteName}</t:Cell><t:Cell>$Resource{costItem}</t:Cell><t:Cell>$Resource{costItemName}</t:Cell><t:Cell>$Resource{UnitID}</t:Cell><t:Cell>$Resource{qty}</t:Cell><t:Cell>$Resource{baseUnit}</t:Cell><t:Cell>$Resource{baseQty}</t:Cell><t:Cell>$Resource{assistantUnit}</t:Cell><t:Cell>$Resource{assistantQty}</t:Cell><t:Cell>$Resource{warehouse}</t:Cell><t:Cell>$Resource{location}</t:Cell><t:Cell>$Resource{stocker}</t:Cell><t:Cell>$Resource{picker}</t:Cell><t:Cell>$Resource{supplyWareHs}</t:Cell><t:Cell>$Resource{supplyLocation}</t:Cell><t:Cell>$Resource{customer}</t:Cell><t:Cell>$Resource{supplier}</t:Cell><t:Cell>$Resource{isPresent}</t:Cell><t:Cell>$Resource{unitStandardCost}</t:Cell><t:Cell>$Resource{standardCost}</t:Cell><t:Cell>$Resource{unitFactCost}</t:Cell><t:Cell>$Resource{factCost}</t:Cell><t:Cell>$Resource{countervailQty}</t:Cell><t:Cell>$Resource{faCardQty}</t:Cell><t:Cell>$Resource{subWrittenOffQty}</t:Cell><t:Cell>$Resource{subWrittenOffBaseQty}</t:Cell><t:Cell>$Resource{subUnWriteOffQty}</t:Cell><t:Cell>$Resource{subUnWriteOffBaseQty}</t:Cell><t:Cell>$Resource{writtenOffAmount}</t:Cell><t:Cell>$Resource{unWriteOffAmount}</t:Cell><t:Cell>$Resource{coreBillType}</t:Cell><t:Cell>$Resource{coreBillNumber}</t:Cell><t:Cell>$Resource{coreBillEntrySe}</t:Cell><t:Cell>$Resource{issueAdminOrgUnit}</t:Cell><t:Cell>$Resource{issuePerson}</t:Cell><t:Cell>$Resource{poBillID}</t:Cell><t:Cell>$Resource{operationNo}</t:Cell><t:Cell>$Resource{operationID}</t:Cell><t:Cell>$Resource{operationNAME}</t:Cell><t:Cell>$Resource{workCenterID}</t:Cell><t:Cell>$Resource{workCenterNAME}</t:Cell><t:Cell>$Resource{remark}</t:Cell><t:Cell>$Resource{productLine}</t:Cell><t:Cell>$Resource{classGroup}</t:Cell><t:Cell>$Resource{person}</t:Cell><t:Cell>$Resource{productLineWP}</t:Cell><t:Cell>$Resource{projectNumCol}</t:Cell><t:Cell>$Resource{trackNumCol}</t:Cell><t:Cell>$Resource{saleOrderNum}</t:Cell><t:Cell>$Resource{issueQty}</t:Cell><t:Cell>$Resource{baseIssueQty}</t:Cell><t:Cell>$Resource{pickingDate}</t:Cell><t:Cell>$Resource{demandDate}</t:Cell><t:Cell>$Resource{isAdmeasure}</t:Cell><t:Cell>$Resource{isReWork}</t:Cell><t:Cell>$Resource{settlePrice}</t:Cell><t:Cell>$Resource{isAdjust}</t:Cell><t:Cell>$Resource{adjustNum}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtEntry.setFormatXml(resHelper.translateString("kdtEntry",kdtEntryStrXML));

                this.kdtEntry.putBindContents("editData",new String[] {"id","","","","","seq","productID","productID.name","processMaterial","processMaterial.name","material","material.name","material.model","assistProperty","lot","mfg","exp","adminOrgUnit","costCenterOrgUnit","costObject","costObject.name","costObjectSuite","costObjectSuite.name","costItem","costItem.name","unit","qty","baseUnit","baseQty","assistUnit","assistQty","warehouse","location","stocker","picker","supplyWarehouse","supplyLocation","customer","supplier","isPresent","unitStandardCost","standardCost","unitActualCost","actualCost","reverseQty","faCardQty","subWrittenOffQty","subWrittenOffBaseQty","subUnWriteOffQty","subUnWriteOffBaseQty","scWrittenOffAmount","scUnWrittenOffAmount","coreBillType","coreBillNumber","coreBillEntrySe","issueAdminOrgUnit","issuePerson","orderNumber","operationNo","operation","operation.name","workCenter","workCenter.name","remark","productLine","classGroup","person","productLineWP","project","trackNumber","saleOrderNum","issueQty","baseIssueQty","pickingDate","demandDate","isAdmeasure","isReWork","settlePrice","isAdjust","adjustNum"});


        this.kdtEntry.checkParsed();
        final KDBizPromptBox kdtEntry_productID_PromptBox = new KDBizPromptBox();
        kdtEntry_productID_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7MaterialBaseInfoQuery");
        kdtEntry_productID_PromptBox.setVisible(true);
        kdtEntry_productID_PromptBox.setEditable(true);
        kdtEntry_productID_PromptBox.setDisplayFormat("$number$");
        kdtEntry_productID_PromptBox.setEditFormat("$number$");
        kdtEntry_productID_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_productID_CellEditor = new KDTDefaultCellEditor(kdtEntry_productID_PromptBox);
        this.kdtEntry.getColumn("productID").setEditor(kdtEntry_productID_CellEditor);
        ObjectValueRender kdtEntry_productID_OVR = new ObjectValueRender();
        kdtEntry_productID_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("productID").setRenderer(kdtEntry_productID_OVR);
        final KDBizPromptBox kdtEntry_processMaterialID_PromptBox = new KDBizPromptBox();
        kdtEntry_processMaterialID_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7MaterialBaseInfoQuery");
        kdtEntry_processMaterialID_PromptBox.setVisible(true);
        kdtEntry_processMaterialID_PromptBox.setEditable(true);
        kdtEntry_processMaterialID_PromptBox.setDisplayFormat("$number$");
        kdtEntry_processMaterialID_PromptBox.setEditFormat("$number$");
        kdtEntry_processMaterialID_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_processMaterialID_CellEditor = new KDTDefaultCellEditor(kdtEntry_processMaterialID_PromptBox);
        this.kdtEntry.getColumn("processMaterialID").setEditor(kdtEntry_processMaterialID_CellEditor);
        ObjectValueRender kdtEntry_processMaterialID_OVR = new ObjectValueRender();
        kdtEntry_processMaterialID_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("processMaterialID").setRenderer(kdtEntry_processMaterialID_OVR);
        final KDBizPromptBox kdtEntry_materialID_PromptBox = new KDBizPromptBox();
        kdtEntry_materialID_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7MaterialBaseInfoQuery");
        kdtEntry_materialID_PromptBox.setVisible(true);
        kdtEntry_materialID_PromptBox.setEditable(true);
        kdtEntry_materialID_PromptBox.setDisplayFormat("$number$");
        kdtEntry_materialID_PromptBox.setEditFormat("$number$");
        kdtEntry_materialID_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_materialID_CellEditor = new KDTDefaultCellEditor(kdtEntry_materialID_PromptBox);
        this.kdtEntry.getColumn("materialID").setEditor(kdtEntry_materialID_CellEditor);
        ObjectValueRender kdtEntry_materialID_OVR = new ObjectValueRender();
        kdtEntry_materialID_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("materialID").setRenderer(kdtEntry_materialID_OVR);
        KDTextField kdtEntry_materialMode_TextField = new KDTextField();
        kdtEntry_materialMode_TextField.setName("kdtEntry_materialMode_TextField");
        kdtEntry_materialMode_TextField.setMaxLength(255);
        KDTDefaultCellEditor kdtEntry_materialMode_CellEditor = new KDTDefaultCellEditor(kdtEntry_materialMode_TextField);
        this.kdtEntry.getColumn("materialMode").setEditor(kdtEntry_materialMode_CellEditor);
        final KDBizPromptBox kdtEntry_assistantAttr_PromptBox = new KDBizPromptBox();
        kdtEntry_assistantAttr_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7AsstAttrValueQuery");
        kdtEntry_assistantAttr_PromptBox.setVisible(true);
        kdtEntry_assistantAttr_PromptBox.setEditable(true);
        kdtEntry_assistantAttr_PromptBox.setDisplayFormat("$number$");
        kdtEntry_assistantAttr_PromptBox.setEditFormat("$number$");
        kdtEntry_assistantAttr_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_assistantAttr_CellEditor = new KDTDefaultCellEditor(kdtEntry_assistantAttr_PromptBox);
        this.kdtEntry.getColumn("assistantAttr").setEditor(kdtEntry_assistantAttr_CellEditor);
        ObjectValueRender kdtEntry_assistantAttr_OVR = new ObjectValueRender();
        kdtEntry_assistantAttr_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("assistantAttr").setRenderer(kdtEntry_assistantAttr_OVR);
        final KDBizPromptBox kdtEntry_mainAdminOrgUnit_PromptBox = new KDBizPromptBox();
        kdtEntry_mainAdminOrgUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");
        kdtEntry_mainAdminOrgUnit_PromptBox.setVisible(true);
        kdtEntry_mainAdminOrgUnit_PromptBox.setEditable(true);
        kdtEntry_mainAdminOrgUnit_PromptBox.setDisplayFormat("$number$");
        kdtEntry_mainAdminOrgUnit_PromptBox.setEditFormat("$number$");
        kdtEntry_mainAdminOrgUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_mainAdminOrgUnit_CellEditor = new KDTDefaultCellEditor(kdtEntry_mainAdminOrgUnit_PromptBox);
        this.kdtEntry.getColumn("mainAdminOrgUnit").setEditor(kdtEntry_mainAdminOrgUnit_CellEditor);
        ObjectValueRender kdtEntry_mainAdminOrgUnit_OVR = new ObjectValueRender();
        kdtEntry_mainAdminOrgUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("mainAdminOrgUnit").setRenderer(kdtEntry_mainAdminOrgUnit_OVR);
        final KDBizPromptBox kdtEntry_costCenterOrgUnit_PromptBox = new KDBizPromptBox();
        kdtEntry_costCenterOrgUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.org.app.CostCenterItemQuery");
        kdtEntry_costCenterOrgUnit_PromptBox.setVisible(true);
        kdtEntry_costCenterOrgUnit_PromptBox.setEditable(true);
        kdtEntry_costCenterOrgUnit_PromptBox.setDisplayFormat("$number$");
        kdtEntry_costCenterOrgUnit_PromptBox.setEditFormat("$number$");
        kdtEntry_costCenterOrgUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_costCenterOrgUnit_CellEditor = new KDTDefaultCellEditor(kdtEntry_costCenterOrgUnit_PromptBox);
        this.kdtEntry.getColumn("costCenterOrgUnit").setEditor(kdtEntry_costCenterOrgUnit_CellEditor);
        ObjectValueRender kdtEntry_costCenterOrgUnit_OVR = new ObjectValueRender();
        kdtEntry_costCenterOrgUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("costCenterOrgUnit").setRenderer(kdtEntry_costCenterOrgUnit_OVR);
        final KDBizPromptBox kdtEntry_costObject_PromptBox = new KDBizPromptBox();
        kdtEntry_costObject_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7CostObjectQuery");
        kdtEntry_costObject_PromptBox.setVisible(true);
        kdtEntry_costObject_PromptBox.setEditable(true);
        kdtEntry_costObject_PromptBox.setDisplayFormat("$number$");
        kdtEntry_costObject_PromptBox.setEditFormat("$number$");
        kdtEntry_costObject_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_costObject_CellEditor = new KDTDefaultCellEditor(kdtEntry_costObject_PromptBox);
        this.kdtEntry.getColumn("costObject").setEditor(kdtEntry_costObject_CellEditor);
        ObjectValueRender kdtEntry_costObject_OVR = new ObjectValueRender();
        kdtEntry_costObject_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("costObject").setRenderer(kdtEntry_costObject_OVR);
        final KDBizPromptBox kdtEntry_costObjectSuite_PromptBox = new KDBizPromptBox();
        kdtEntry_costObjectSuite_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.F7CostObjectSuiteQuery");
        kdtEntry_costObjectSuite_PromptBox.setVisible(true);
        kdtEntry_costObjectSuite_PromptBox.setEditable(true);
        kdtEntry_costObjectSuite_PromptBox.setDisplayFormat("$number$");
        kdtEntry_costObjectSuite_PromptBox.setEditFormat("$number$");
        kdtEntry_costObjectSuite_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_costObjectSuite_CellEditor = new KDTDefaultCellEditor(kdtEntry_costObjectSuite_PromptBox);
        this.kdtEntry.getColumn("costObjectSuite").setEditor(kdtEntry_costObjectSuite_CellEditor);
        ObjectValueRender kdtEntry_costObjectSuite_OVR = new ObjectValueRender();
        kdtEntry_costObjectSuite_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("costObjectSuite").setRenderer(kdtEntry_costObjectSuite_OVR);
        KDTextField kdtEntry_costObjectSuiteName_TextField = new KDTextField();
        kdtEntry_costObjectSuiteName_TextField.setName("kdtEntry_costObjectSuiteName_TextField");
        kdtEntry_costObjectSuiteName_TextField.setMaxLength(255);
        KDTDefaultCellEditor kdtEntry_costObjectSuiteName_CellEditor = new KDTDefaultCellEditor(kdtEntry_costObjectSuiteName_TextField);
        this.kdtEntry.getColumn("costObjectSuiteName").setEditor(kdtEntry_costObjectSuiteName_CellEditor);
        final KDBizPromptBox kdtEntry_costItem_PromptBox = new KDBizPromptBox();
        kdtEntry_costItem_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.CostItemTreeListQuery");
        kdtEntry_costItem_PromptBox.setVisible(true);
        kdtEntry_costItem_PromptBox.setEditable(true);
        kdtEntry_costItem_PromptBox.setDisplayFormat("$number$");
        kdtEntry_costItem_PromptBox.setEditFormat("$number$");
        kdtEntry_costItem_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_costItem_CellEditor = new KDTDefaultCellEditor(kdtEntry_costItem_PromptBox);
        this.kdtEntry.getColumn("costItem").setEditor(kdtEntry_costItem_CellEditor);
        ObjectValueRender kdtEntry_costItem_OVR = new ObjectValueRender();
        kdtEntry_costItem_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("costItem").setRenderer(kdtEntry_costItem_OVR);
        final KDBizPromptBox kdtEntry_UnitID_PromptBox = new KDBizPromptBox();
        kdtEntry_UnitID_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7MeasureUnitQuery");
        kdtEntry_UnitID_PromptBox.setVisible(true);
        kdtEntry_UnitID_PromptBox.setEditable(true);
        kdtEntry_UnitID_PromptBox.setDisplayFormat("$number$");
        kdtEntry_UnitID_PromptBox.setEditFormat("$number$");
        kdtEntry_UnitID_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_UnitID_CellEditor = new KDTDefaultCellEditor(kdtEntry_UnitID_PromptBox);
        this.kdtEntry.getColumn("UnitID").setEditor(kdtEntry_UnitID_CellEditor);
        ObjectValueRender kdtEntry_UnitID_OVR = new ObjectValueRender();
        kdtEntry_UnitID_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("UnitID").setRenderer(kdtEntry_UnitID_OVR);
        final KDBizPromptBox kdtEntry_baseUnit_PromptBox = new KDBizPromptBox();
        kdtEntry_baseUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7MeasureUnitQuery");
        kdtEntry_baseUnit_PromptBox.setVisible(true);
        kdtEntry_baseUnit_PromptBox.setEditable(true);
        kdtEntry_baseUnit_PromptBox.setDisplayFormat("$number$");
        kdtEntry_baseUnit_PromptBox.setEditFormat("$number$");
        kdtEntry_baseUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_baseUnit_CellEditor = new KDTDefaultCellEditor(kdtEntry_baseUnit_PromptBox);
        this.kdtEntry.getColumn("baseUnit").setEditor(kdtEntry_baseUnit_CellEditor);
        ObjectValueRender kdtEntry_baseUnit_OVR = new ObjectValueRender();
        kdtEntry_baseUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("baseUnit").setRenderer(kdtEntry_baseUnit_OVR);
        final KDBizPromptBox kdtEntry_assistantUnit_PromptBox = new KDBizPromptBox();
        kdtEntry_assistantUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7MeasureUnitQuery");
        kdtEntry_assistantUnit_PromptBox.setVisible(true);
        kdtEntry_assistantUnit_PromptBox.setEditable(true);
        kdtEntry_assistantUnit_PromptBox.setDisplayFormat("$number$");
        kdtEntry_assistantUnit_PromptBox.setEditFormat("$number$");
        kdtEntry_assistantUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_assistantUnit_CellEditor = new KDTDefaultCellEditor(kdtEntry_assistantUnit_PromptBox);
        this.kdtEntry.getColumn("assistantUnit").setEditor(kdtEntry_assistantUnit_CellEditor);
        ObjectValueRender kdtEntry_assistantUnit_OVR = new ObjectValueRender();
        kdtEntry_assistantUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("assistantUnit").setRenderer(kdtEntry_assistantUnit_OVR);
        final KDBizPromptBox kdtEntry_warehouse_PromptBox = new KDBizPromptBox();
        kdtEntry_warehouse_PromptBox.setQueryInfo("com.kingdee.eas.basedata.scm.im.inv.app.F7AllWarehouseQuery");
        kdtEntry_warehouse_PromptBox.setVisible(true);
        kdtEntry_warehouse_PromptBox.setEditable(true);
        kdtEntry_warehouse_PromptBox.setDisplayFormat("$number$");
        kdtEntry_warehouse_PromptBox.setEditFormat("$number$");
        kdtEntry_warehouse_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_warehouse_CellEditor = new KDTDefaultCellEditor(kdtEntry_warehouse_PromptBox);
        this.kdtEntry.getColumn("warehouse").setEditor(kdtEntry_warehouse_CellEditor);
        ObjectValueRender kdtEntry_warehouse_OVR = new ObjectValueRender();
        kdtEntry_warehouse_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("warehouse").setRenderer(kdtEntry_warehouse_OVR);
        final KDBizPromptBox kdtEntry_location_PromptBox = new KDBizPromptBox();
        kdtEntry_location_PromptBox.setQueryInfo("com.kingdee.eas.basedata.scm.im.inv.app.F7LocationQuery");
        kdtEntry_location_PromptBox.setVisible(true);
        kdtEntry_location_PromptBox.setEditable(true);
        kdtEntry_location_PromptBox.setDisplayFormat("$number$");
        kdtEntry_location_PromptBox.setEditFormat("$number$");
        kdtEntry_location_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_location_CellEditor = new KDTDefaultCellEditor(kdtEntry_location_PromptBox);
        this.kdtEntry.getColumn("location").setEditor(kdtEntry_location_CellEditor);
        ObjectValueRender kdtEntry_location_OVR = new ObjectValueRender();
        kdtEntry_location_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("location").setRenderer(kdtEntry_location_OVR);
        final KDBizPromptBox kdtEntry_stocker_PromptBox = new KDBizPromptBox();
        kdtEntry_stocker_PromptBox.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
        kdtEntry_stocker_PromptBox.setVisible(true);
        kdtEntry_stocker_PromptBox.setEditable(true);
        kdtEntry_stocker_PromptBox.setDisplayFormat("$number$");
        kdtEntry_stocker_PromptBox.setEditFormat("$number$");
        kdtEntry_stocker_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_stocker_CellEditor = new KDTDefaultCellEditor(kdtEntry_stocker_PromptBox);
        this.kdtEntry.getColumn("stocker").setEditor(kdtEntry_stocker_CellEditor);
        ObjectValueRender kdtEntry_stocker_OVR = new ObjectValueRender();
        kdtEntry_stocker_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("stocker").setRenderer(kdtEntry_stocker_OVR);
        final KDBizPromptBox kdtEntry_picker_PromptBox = new KDBizPromptBox();
        kdtEntry_picker_PromptBox.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
        kdtEntry_picker_PromptBox.setVisible(true);
        kdtEntry_picker_PromptBox.setEditable(true);
        kdtEntry_picker_PromptBox.setDisplayFormat("$number$");
        kdtEntry_picker_PromptBox.setEditFormat("$number$");
        kdtEntry_picker_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_picker_CellEditor = new KDTDefaultCellEditor(kdtEntry_picker_PromptBox);
        this.kdtEntry.getColumn("picker").setEditor(kdtEntry_picker_CellEditor);
        ObjectValueRender kdtEntry_picker_OVR = new ObjectValueRender();
        kdtEntry_picker_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("picker").setRenderer(kdtEntry_picker_OVR);
        final KDBizPromptBox kdtEntry_supplyWareHs_PromptBox = new KDBizPromptBox();
        kdtEntry_supplyWareHs_PromptBox.setQueryInfo("com.kingdee.eas.basedata.scm.im.inv.app.F7AllWarehouseQuery");
        kdtEntry_supplyWareHs_PromptBox.setVisible(true);
        kdtEntry_supplyWareHs_PromptBox.setEditable(true);
        kdtEntry_supplyWareHs_PromptBox.setDisplayFormat("$number$");
        kdtEntry_supplyWareHs_PromptBox.setEditFormat("$number$");
        kdtEntry_supplyWareHs_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_supplyWareHs_CellEditor = new KDTDefaultCellEditor(kdtEntry_supplyWareHs_PromptBox);
        this.kdtEntry.getColumn("supplyWareHs").setEditor(kdtEntry_supplyWareHs_CellEditor);
        ObjectValueRender kdtEntry_supplyWareHs_OVR = new ObjectValueRender();
        kdtEntry_supplyWareHs_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("supplyWareHs").setRenderer(kdtEntry_supplyWareHs_OVR);
        final KDBizPromptBox kdtEntry_supplyLocation_PromptBox = new KDBizPromptBox();
        kdtEntry_supplyLocation_PromptBox.setQueryInfo("com.kingdee.eas.basedata.scm.im.inv.app.F7LocationQuery");
        kdtEntry_supplyLocation_PromptBox.setVisible(true);
        kdtEntry_supplyLocation_PromptBox.setEditable(true);
        kdtEntry_supplyLocation_PromptBox.setDisplayFormat("$number$");
        kdtEntry_supplyLocation_PromptBox.setEditFormat("$number$");
        kdtEntry_supplyLocation_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_supplyLocation_CellEditor = new KDTDefaultCellEditor(kdtEntry_supplyLocation_PromptBox);
        this.kdtEntry.getColumn("supplyLocation").setEditor(kdtEntry_supplyLocation_CellEditor);
        ObjectValueRender kdtEntry_supplyLocation_OVR = new ObjectValueRender();
        kdtEntry_supplyLocation_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("supplyLocation").setRenderer(kdtEntry_supplyLocation_OVR);
        final KDBizPromptBox kdtEntry_customer_PromptBox = new KDBizPromptBox();
        kdtEntry_customer_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.CustomerInfoQuery");
        kdtEntry_customer_PromptBox.setVisible(true);
        kdtEntry_customer_PromptBox.setEditable(true);
        kdtEntry_customer_PromptBox.setDisplayFormat("$number$");
        kdtEntry_customer_PromptBox.setEditFormat("$number$");
        kdtEntry_customer_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_customer_CellEditor = new KDTDefaultCellEditor(kdtEntry_customer_PromptBox);
        this.kdtEntry.getColumn("customer").setEditor(kdtEntry_customer_CellEditor);
        ObjectValueRender kdtEntry_customer_OVR = new ObjectValueRender();
        kdtEntry_customer_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("customer").setRenderer(kdtEntry_customer_OVR);
        final KDBizPromptBox kdtEntry_supplier_PromptBox = new KDBizPromptBox();
        kdtEntry_supplier_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierDefaultQuery");
        kdtEntry_supplier_PromptBox.setVisible(true);
        kdtEntry_supplier_PromptBox.setEditable(true);
        kdtEntry_supplier_PromptBox.setDisplayFormat("$number$");
        kdtEntry_supplier_PromptBox.setEditFormat("$number$");
        kdtEntry_supplier_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_supplier_CellEditor = new KDTDefaultCellEditor(kdtEntry_supplier_PromptBox);
        this.kdtEntry.getColumn("supplier").setEditor(kdtEntry_supplier_CellEditor);
        ObjectValueRender kdtEntry_supplier_OVR = new ObjectValueRender();
        kdtEntry_supplier_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("supplier").setRenderer(kdtEntry_supplier_OVR);
        KDCheckBox kdtEntry_isPresent_CheckBox = new KDCheckBox();
        kdtEntry_isPresent_CheckBox.setName("kdtEntry_isPresent_CheckBox");
        KDTDefaultCellEditor kdtEntry_isPresent_CellEditor = new KDTDefaultCellEditor(kdtEntry_isPresent_CheckBox);
        this.kdtEntry.getColumn("isPresent").setEditor(kdtEntry_isPresent_CellEditor);
        KDFormattedTextField kdtEntry_faCardQty_TextField = new KDFormattedTextField();
        kdtEntry_faCardQty_TextField.setName("kdtEntry_faCardQty_TextField");
        kdtEntry_faCardQty_TextField.setVisible(true);
        kdtEntry_faCardQty_TextField.setEditable(true);
        kdtEntry_faCardQty_TextField.setHorizontalAlignment(2);
        kdtEntry_faCardQty_TextField.setDataType(1);
        kdtEntry_faCardQty_TextField.setPrecision(8);
        KDTDefaultCellEditor kdtEntry_faCardQty_CellEditor = new KDTDefaultCellEditor(kdtEntry_faCardQty_TextField);
        this.kdtEntry.getColumn("faCardQty").setEditor(kdtEntry_faCardQty_CellEditor);
        KDFormattedTextField kdtEntry_subWrittenOffQty_TextField = new KDFormattedTextField();
        kdtEntry_subWrittenOffQty_TextField.setName("kdtEntry_subWrittenOffQty_TextField");
        kdtEntry_subWrittenOffQty_TextField.setVisible(true);
        kdtEntry_subWrittenOffQty_TextField.setEditable(true);
        kdtEntry_subWrittenOffQty_TextField.setHorizontalAlignment(2);
        kdtEntry_subWrittenOffQty_TextField.setDataType(1);
        	kdtEntry_subWrittenOffQty_TextField.setMinimumValue(new java.math.BigDecimal("-99999.99999999"));
        	kdtEntry_subWrittenOffQty_TextField.setMaximumValue(new java.math.BigDecimal("99999.99999999"));
        kdtEntry_subWrittenOffQty_TextField.setPrecision(8);
        KDTDefaultCellEditor kdtEntry_subWrittenOffQty_CellEditor = new KDTDefaultCellEditor(kdtEntry_subWrittenOffQty_TextField);
        this.kdtEntry.getColumn("subWrittenOffQty").setEditor(kdtEntry_subWrittenOffQty_CellEditor);
        KDFormattedTextField kdtEntry_subWrittenOffBaseQty_TextField = new KDFormattedTextField();
        kdtEntry_subWrittenOffBaseQty_TextField.setName("kdtEntry_subWrittenOffBaseQty_TextField");
        kdtEntry_subWrittenOffBaseQty_TextField.setVisible(true);
        kdtEntry_subWrittenOffBaseQty_TextField.setEditable(true);
        kdtEntry_subWrittenOffBaseQty_TextField.setHorizontalAlignment(2);
        kdtEntry_subWrittenOffBaseQty_TextField.setDataType(1);
        	kdtEntry_subWrittenOffBaseQty_TextField.setMinimumValue(new java.math.BigDecimal("-99999.99999999"));
        	kdtEntry_subWrittenOffBaseQty_TextField.setMaximumValue(new java.math.BigDecimal("99999.99999999"));
        kdtEntry_subWrittenOffBaseQty_TextField.setPrecision(8);
        KDTDefaultCellEditor kdtEntry_subWrittenOffBaseQty_CellEditor = new KDTDefaultCellEditor(kdtEntry_subWrittenOffBaseQty_TextField);
        this.kdtEntry.getColumn("subWrittenOffBaseQty").setEditor(kdtEntry_subWrittenOffBaseQty_CellEditor);
        KDFormattedTextField kdtEntry_subUnWriteOffQty_TextField = new KDFormattedTextField();
        kdtEntry_subUnWriteOffQty_TextField.setName("kdtEntry_subUnWriteOffQty_TextField");
        kdtEntry_subUnWriteOffQty_TextField.setVisible(true);
        kdtEntry_subUnWriteOffQty_TextField.setEditable(true);
        kdtEntry_subUnWriteOffQty_TextField.setHorizontalAlignment(2);
        kdtEntry_subUnWriteOffQty_TextField.setDataType(1);
        	kdtEntry_subUnWriteOffQty_TextField.setMinimumValue(new java.math.BigDecimal("-99999.99999999"));
        	kdtEntry_subUnWriteOffQty_TextField.setMaximumValue(new java.math.BigDecimal("99999.99999999"));
        kdtEntry_subUnWriteOffQty_TextField.setPrecision(8);
        KDTDefaultCellEditor kdtEntry_subUnWriteOffQty_CellEditor = new KDTDefaultCellEditor(kdtEntry_subUnWriteOffQty_TextField);
        this.kdtEntry.getColumn("subUnWriteOffQty").setEditor(kdtEntry_subUnWriteOffQty_CellEditor);
        KDFormattedTextField kdtEntry_subUnWriteOffBaseQty_TextField = new KDFormattedTextField();
        kdtEntry_subUnWriteOffBaseQty_TextField.setName("kdtEntry_subUnWriteOffBaseQty_TextField");
        kdtEntry_subUnWriteOffBaseQty_TextField.setVisible(true);
        kdtEntry_subUnWriteOffBaseQty_TextField.setEditable(true);
        kdtEntry_subUnWriteOffBaseQty_TextField.setHorizontalAlignment(2);
        kdtEntry_subUnWriteOffBaseQty_TextField.setDataType(1);
        	kdtEntry_subUnWriteOffBaseQty_TextField.setMinimumValue(new java.math.BigDecimal("-99999.99999999"));
        	kdtEntry_subUnWriteOffBaseQty_TextField.setMaximumValue(new java.math.BigDecimal("99999.99999999"));
        kdtEntry_subUnWriteOffBaseQty_TextField.setPrecision(8);
        KDTDefaultCellEditor kdtEntry_subUnWriteOffBaseQty_CellEditor = new KDTDefaultCellEditor(kdtEntry_subUnWriteOffBaseQty_TextField);
        this.kdtEntry.getColumn("subUnWriteOffBaseQty").setEditor(kdtEntry_subUnWriteOffBaseQty_CellEditor);
        KDFormattedTextField kdtEntry_writtenOffAmount_TextField = new KDFormattedTextField();
        kdtEntry_writtenOffAmount_TextField.setName("kdtEntry_writtenOffAmount_TextField");
        kdtEntry_writtenOffAmount_TextField.setVisible(true);
        kdtEntry_writtenOffAmount_TextField.setEditable(true);
        kdtEntry_writtenOffAmount_TextField.setHorizontalAlignment(2);
        kdtEntry_writtenOffAmount_TextField.setDataType(1);
        kdtEntry_writtenOffAmount_TextField.setPrecision(4);
        KDTDefaultCellEditor kdtEntry_writtenOffAmount_CellEditor = new KDTDefaultCellEditor(kdtEntry_writtenOffAmount_TextField);
        this.kdtEntry.getColumn("writtenOffAmount").setEditor(kdtEntry_writtenOffAmount_CellEditor);
        KDFormattedTextField kdtEntry_unWriteOffAmount_TextField = new KDFormattedTextField();
        kdtEntry_unWriteOffAmount_TextField.setName("kdtEntry_unWriteOffAmount_TextField");
        kdtEntry_unWriteOffAmount_TextField.setVisible(true);
        kdtEntry_unWriteOffAmount_TextField.setEditable(true);
        kdtEntry_unWriteOffAmount_TextField.setHorizontalAlignment(2);
        kdtEntry_unWriteOffAmount_TextField.setDataType(1);
        kdtEntry_unWriteOffAmount_TextField.setPrecision(4);
        KDTDefaultCellEditor kdtEntry_unWriteOffAmount_CellEditor = new KDTDefaultCellEditor(kdtEntry_unWriteOffAmount_TextField);
        this.kdtEntry.getColumn("unWriteOffAmount").setEditor(kdtEntry_unWriteOffAmount_CellEditor);
        final KDBizPromptBox kdtEntry_coreBillType_PromptBox = new KDBizPromptBox();
        kdtEntry_coreBillType_PromptBox.setQueryInfo("com.kingdee.eas.basedata.scm.common.app.F7BillTypeQuery");
        kdtEntry_coreBillType_PromptBox.setVisible(true);
        kdtEntry_coreBillType_PromptBox.setEditable(true);
        kdtEntry_coreBillType_PromptBox.setDisplayFormat("$number$");
        kdtEntry_coreBillType_PromptBox.setEditFormat("$number$");
        kdtEntry_coreBillType_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_coreBillType_CellEditor = new KDTDefaultCellEditor(kdtEntry_coreBillType_PromptBox);
        this.kdtEntry.getColumn("coreBillType").setEditor(kdtEntry_coreBillType_CellEditor);
        ObjectValueRender kdtEntry_coreBillType_OVR = new ObjectValueRender();
        kdtEntry_coreBillType_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("coreBillType").setRenderer(kdtEntry_coreBillType_OVR);
        KDFormattedTextField kdtEntry_coreBillEntrySe_TextField = new KDFormattedTextField();
        kdtEntry_coreBillEntrySe_TextField.setName("kdtEntry_coreBillEntrySe_TextField");
        kdtEntry_coreBillEntrySe_TextField.setVisible(true);
        kdtEntry_coreBillEntrySe_TextField.setEditable(true);
        kdtEntry_coreBillEntrySe_TextField.setHorizontalAlignment(2);
        kdtEntry_coreBillEntrySe_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntry_coreBillEntrySe_CellEditor = new KDTDefaultCellEditor(kdtEntry_coreBillEntrySe_TextField);
        this.kdtEntry.getColumn("coreBillEntrySe").setEditor(kdtEntry_coreBillEntrySe_CellEditor);
        final KDBizPromptBox kdtEntry_issueAdminOrgUnit_PromptBox = new KDBizPromptBox();
        kdtEntry_issueAdminOrgUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");
        kdtEntry_issueAdminOrgUnit_PromptBox.setVisible(true);
        kdtEntry_issueAdminOrgUnit_PromptBox.setEditable(true);
        kdtEntry_issueAdminOrgUnit_PromptBox.setDisplayFormat("$number$");
        kdtEntry_issueAdminOrgUnit_PromptBox.setEditFormat("$number$");
        kdtEntry_issueAdminOrgUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_issueAdminOrgUnit_CellEditor = new KDTDefaultCellEditor(kdtEntry_issueAdminOrgUnit_PromptBox);
        this.kdtEntry.getColumn("issueAdminOrgUnit").setEditor(kdtEntry_issueAdminOrgUnit_CellEditor);
        ObjectValueRender kdtEntry_issueAdminOrgUnit_OVR = new ObjectValueRender();
        kdtEntry_issueAdminOrgUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("issueAdminOrgUnit").setRenderer(kdtEntry_issueAdminOrgUnit_OVR);
        final KDBizPromptBox kdtEntry_issuePerson_PromptBox = new KDBizPromptBox();
        kdtEntry_issuePerson_PromptBox.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
        kdtEntry_issuePerson_PromptBox.setVisible(true);
        kdtEntry_issuePerson_PromptBox.setEditable(true);
        kdtEntry_issuePerson_PromptBox.setDisplayFormat("$number$");
        kdtEntry_issuePerson_PromptBox.setEditFormat("$number$");
        kdtEntry_issuePerson_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_issuePerson_CellEditor = new KDTDefaultCellEditor(kdtEntry_issuePerson_PromptBox);
        this.kdtEntry.getColumn("issuePerson").setEditor(kdtEntry_issuePerson_CellEditor);
        ObjectValueRender kdtEntry_issuePerson_OVR = new ObjectValueRender();
        kdtEntry_issuePerson_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("issuePerson").setRenderer(kdtEntry_issuePerson_OVR);
        KDTextField kdtEntry_poBillID_TextField = new KDTextField();
        kdtEntry_poBillID_TextField.setName("kdtEntry_poBillID_TextField");
        kdtEntry_poBillID_TextField.setMaxLength(80);
        KDTDefaultCellEditor kdtEntry_poBillID_CellEditor = new KDTDefaultCellEditor(kdtEntry_poBillID_TextField);
        this.kdtEntry.getColumn("poBillID").setEditor(kdtEntry_poBillID_CellEditor);
        KDFormattedTextField kdtEntry_operationNo_TextField = new KDFormattedTextField();
        kdtEntry_operationNo_TextField.setName("kdtEntry_operationNo_TextField");
        kdtEntry_operationNo_TextField.setVisible(true);
        kdtEntry_operationNo_TextField.setEditable(true);
        kdtEntry_operationNo_TextField.setHorizontalAlignment(2);
        kdtEntry_operationNo_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntry_operationNo_CellEditor = new KDTDefaultCellEditor(kdtEntry_operationNo_TextField);
        this.kdtEntry.getColumn("operationNo").setEditor(kdtEntry_operationNo_CellEditor);
        final KDBizPromptBox kdtEntry_operationID_PromptBox = new KDBizPromptBox();
        kdtEntry_operationID_PromptBox.setQueryInfo("com.kingdee.eas.mm.basedata.app.F7OperationQuery");
        kdtEntry_operationID_PromptBox.setVisible(true);
        kdtEntry_operationID_PromptBox.setEditable(true);
        kdtEntry_operationID_PromptBox.setDisplayFormat("$number$");
        kdtEntry_operationID_PromptBox.setEditFormat("$number$");
        kdtEntry_operationID_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_operationID_CellEditor = new KDTDefaultCellEditor(kdtEntry_operationID_PromptBox);
        this.kdtEntry.getColumn("operationID").setEditor(kdtEntry_operationID_CellEditor);
        ObjectValueRender kdtEntry_operationID_OVR = new ObjectValueRender();
        kdtEntry_operationID_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("operationID").setRenderer(kdtEntry_operationID_OVR);
        final KDBizPromptBox kdtEntry_workCenterID_PromptBox = new KDBizPromptBox();
        kdtEntry_workCenterID_PromptBox.setQueryInfo("com.kingdee.eas.mm.basedata.app.F7WorkcenterQuery");
        kdtEntry_workCenterID_PromptBox.setVisible(true);
        kdtEntry_workCenterID_PromptBox.setEditable(true);
        kdtEntry_workCenterID_PromptBox.setDisplayFormat("$number$");
        kdtEntry_workCenterID_PromptBox.setEditFormat("$number$");
        kdtEntry_workCenterID_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_workCenterID_CellEditor = new KDTDefaultCellEditor(kdtEntry_workCenterID_PromptBox);
        this.kdtEntry.getColumn("workCenterID").setEditor(kdtEntry_workCenterID_CellEditor);
        ObjectValueRender kdtEntry_workCenterID_OVR = new ObjectValueRender();
        kdtEntry_workCenterID_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("workCenterID").setRenderer(kdtEntry_workCenterID_OVR);
        final KDBizPromptBox kdtEntry_productLine_PromptBox = new KDBizPromptBox();
        kdtEntry_productLine_PromptBox.setQueryInfo("com.kingdee.eas.mm.basedata.app.ProductLineQuery");
        kdtEntry_productLine_PromptBox.setVisible(true);
        kdtEntry_productLine_PromptBox.setEditable(true);
        kdtEntry_productLine_PromptBox.setDisplayFormat("$number$");
        kdtEntry_productLine_PromptBox.setEditFormat("$number$");
        kdtEntry_productLine_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_productLine_CellEditor = new KDTDefaultCellEditor(kdtEntry_productLine_PromptBox);
        this.kdtEntry.getColumn("productLine").setEditor(kdtEntry_productLine_CellEditor);
        ObjectValueRender kdtEntry_productLine_OVR = new ObjectValueRender();
        kdtEntry_productLine_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("productLine").setRenderer(kdtEntry_productLine_OVR);
        final KDBizPromptBox kdtEntry_classGroup_PromptBox = new KDBizPromptBox();
        kdtEntry_classGroup_PromptBox.setQueryInfo("com.kingdee.eas.mm.basedata.app.ClassGroupQuery");
        kdtEntry_classGroup_PromptBox.setVisible(true);
        kdtEntry_classGroup_PromptBox.setEditable(true);
        kdtEntry_classGroup_PromptBox.setDisplayFormat("$number$");
        kdtEntry_classGroup_PromptBox.setEditFormat("$number$");
        kdtEntry_classGroup_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_classGroup_CellEditor = new KDTDefaultCellEditor(kdtEntry_classGroup_PromptBox);
        this.kdtEntry.getColumn("classGroup").setEditor(kdtEntry_classGroup_CellEditor);
        ObjectValueRender kdtEntry_classGroup_OVR = new ObjectValueRender();
        kdtEntry_classGroup_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("classGroup").setRenderer(kdtEntry_classGroup_OVR);
        final KDBizPromptBox kdtEntry_person_PromptBox = new KDBizPromptBox();
        kdtEntry_person_PromptBox.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
        kdtEntry_person_PromptBox.setVisible(true);
        kdtEntry_person_PromptBox.setEditable(true);
        kdtEntry_person_PromptBox.setDisplayFormat("$number$");
        kdtEntry_person_PromptBox.setEditFormat("$number$");
        kdtEntry_person_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_person_CellEditor = new KDTDefaultCellEditor(kdtEntry_person_PromptBox);
        this.kdtEntry.getColumn("person").setEditor(kdtEntry_person_CellEditor);
        ObjectValueRender kdtEntry_person_OVR = new ObjectValueRender();
        kdtEntry_person_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("person").setRenderer(kdtEntry_person_OVR);
        final KDBizPromptBox kdtEntry_projectNumCol_PromptBox = new KDBizPromptBox();
        kdtEntry_projectNumCol_PromptBox.setQueryInfo("com.kingdee.eas.mm.project.app.F7ProjectQuery");
        kdtEntry_projectNumCol_PromptBox.setVisible(true);
        kdtEntry_projectNumCol_PromptBox.setEditable(true);
        kdtEntry_projectNumCol_PromptBox.setDisplayFormat("$number$");
        kdtEntry_projectNumCol_PromptBox.setEditFormat("$number$");
        kdtEntry_projectNumCol_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_projectNumCol_CellEditor = new KDTDefaultCellEditor(kdtEntry_projectNumCol_PromptBox);
        this.kdtEntry.getColumn("projectNumCol").setEditor(kdtEntry_projectNumCol_CellEditor);
        ObjectValueRender kdtEntry_projectNumCol_OVR = new ObjectValueRender();
        kdtEntry_projectNumCol_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("projectNumCol").setRenderer(kdtEntry_projectNumCol_OVR);
        final KDBizPromptBox kdtEntry_trackNumCol_PromptBox = new KDBizPromptBox();
        kdtEntry_trackNumCol_PromptBox.setQueryInfo("com.kingdee.eas.mm.basedata.app.TrackNumberQuery");
        kdtEntry_trackNumCol_PromptBox.setVisible(true);
        kdtEntry_trackNumCol_PromptBox.setEditable(true);
        kdtEntry_trackNumCol_PromptBox.setDisplayFormat("$number$");
        kdtEntry_trackNumCol_PromptBox.setEditFormat("$number$");
        kdtEntry_trackNumCol_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntry_trackNumCol_CellEditor = new KDTDefaultCellEditor(kdtEntry_trackNumCol_PromptBox);
        this.kdtEntry.getColumn("trackNumCol").setEditor(kdtEntry_trackNumCol_CellEditor);
        ObjectValueRender kdtEntry_trackNumCol_OVR = new ObjectValueRender();
        kdtEntry_trackNumCol_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntry.getColumn("trackNumCol").setRenderer(kdtEntry_trackNumCol_OVR);
        KDTextField kdtEntry_saleOrderNum_TextField = new KDTextField();
        kdtEntry_saleOrderNum_TextField.setName("kdtEntry_saleOrderNum_TextField");
        kdtEntry_saleOrderNum_TextField.setMaxLength(80);
        KDTDefaultCellEditor kdtEntry_saleOrderNum_CellEditor = new KDTDefaultCellEditor(kdtEntry_saleOrderNum_TextField);
        this.kdtEntry.getColumn("saleOrderNum").setEditor(kdtEntry_saleOrderNum_CellEditor);
        KDFormattedTextField kdtEntry_issueQty_TextField = new KDFormattedTextField();
        kdtEntry_issueQty_TextField.setName("kdtEntry_issueQty_TextField");
        kdtEntry_issueQty_TextField.setVisible(true);
        kdtEntry_issueQty_TextField.setEditable(true);
        kdtEntry_issueQty_TextField.setHorizontalAlignment(2);
        kdtEntry_issueQty_TextField.setDataType(1);
        kdtEntry_issueQty_TextField.setPrecision(8);
        KDTDefaultCellEditor kdtEntry_issueQty_CellEditor = new KDTDefaultCellEditor(kdtEntry_issueQty_TextField);
        this.kdtEntry.getColumn("issueQty").setEditor(kdtEntry_issueQty_CellEditor);
        KDFormattedTextField kdtEntry_baseIssueQty_TextField = new KDFormattedTextField();
        kdtEntry_baseIssueQty_TextField.setName("kdtEntry_baseIssueQty_TextField");
        kdtEntry_baseIssueQty_TextField.setVisible(true);
        kdtEntry_baseIssueQty_TextField.setEditable(true);
        kdtEntry_baseIssueQty_TextField.setHorizontalAlignment(2);
        kdtEntry_baseIssueQty_TextField.setDataType(1);
        kdtEntry_baseIssueQty_TextField.setPrecision(8);
        KDTDefaultCellEditor kdtEntry_baseIssueQty_CellEditor = new KDTDefaultCellEditor(kdtEntry_baseIssueQty_TextField);
        this.kdtEntry.getColumn("baseIssueQty").setEditor(kdtEntry_baseIssueQty_CellEditor);
        KDDatePicker kdtEntry_pickingDate_DatePicker = new KDDatePicker();
        kdtEntry_pickingDate_DatePicker.setName("kdtEntry_pickingDate_DatePicker");
        kdtEntry_pickingDate_DatePicker.setVisible(true);
        kdtEntry_pickingDate_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtEntry_pickingDate_CellEditor = new KDTDefaultCellEditor(kdtEntry_pickingDate_DatePicker);
        this.kdtEntry.getColumn("pickingDate").setEditor(kdtEntry_pickingDate_CellEditor);
        KDDatePicker kdtEntry_demandDate_DatePicker = new KDDatePicker();
        kdtEntry_demandDate_DatePicker.setName("kdtEntry_demandDate_DatePicker");
        kdtEntry_demandDate_DatePicker.setVisible(true);
        kdtEntry_demandDate_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtEntry_demandDate_CellEditor = new KDTDefaultCellEditor(kdtEntry_demandDate_DatePicker);
        this.kdtEntry.getColumn("demandDate").setEditor(kdtEntry_demandDate_CellEditor);
        KDCheckBox kdtEntry_isAdmeasure_CheckBox = new KDCheckBox();
        kdtEntry_isAdmeasure_CheckBox.setName("kdtEntry_isAdmeasure_CheckBox");
        KDTDefaultCellEditor kdtEntry_isAdmeasure_CellEditor = new KDTDefaultCellEditor(kdtEntry_isAdmeasure_CheckBox);
        this.kdtEntry.getColumn("isAdmeasure").setEditor(kdtEntry_isAdmeasure_CellEditor);
        KDCheckBox kdtEntry_isReWork_CheckBox = new KDCheckBox();
        kdtEntry_isReWork_CheckBox.setName("kdtEntry_isReWork_CheckBox");
        KDTDefaultCellEditor kdtEntry_isReWork_CellEditor = new KDTDefaultCellEditor(kdtEntry_isReWork_CheckBox);
        this.kdtEntry.getColumn("isReWork").setEditor(kdtEntry_isReWork_CellEditor);
        KDFormattedTextField kdtEntry_settlePrice_TextField = new KDFormattedTextField();
        kdtEntry_settlePrice_TextField.setName("kdtEntry_settlePrice_TextField");
        kdtEntry_settlePrice_TextField.setVisible(true);
        kdtEntry_settlePrice_TextField.setEditable(true);
        kdtEntry_settlePrice_TextField.setHorizontalAlignment(2);
        kdtEntry_settlePrice_TextField.setDataType(1);
        	kdtEntry_settlePrice_TextField.setMinimumValue(new java.math.BigDecimal("-99999.99999999"));
        	kdtEntry_settlePrice_TextField.setMaximumValue(new java.math.BigDecimal("99999.99999999"));
        kdtEntry_settlePrice_TextField.setPrecision(8);
        KDTDefaultCellEditor kdtEntry_settlePrice_CellEditor = new KDTDefaultCellEditor(kdtEntry_settlePrice_TextField);
        this.kdtEntry.getColumn("settlePrice").setEditor(kdtEntry_settlePrice_CellEditor);
        KDCheckBox kdtEntry_isAdjust_CheckBox = new KDCheckBox();
        kdtEntry_isAdjust_CheckBox.setName("kdtEntry_isAdjust_CheckBox");
        KDTDefaultCellEditor kdtEntry_isAdjust_CellEditor = new KDTDefaultCellEditor(kdtEntry_isAdjust_CheckBox);
        this.kdtEntry.getColumn("isAdjust").setEditor(kdtEntry_isAdjust_CellEditor);
        KDTextField kdtEntry_adjustNum_TextField = new KDTextField();
        kdtEntry_adjustNum_TextField.setName("kdtEntry_adjustNum_TextField");
        kdtEntry_adjustNum_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry_adjustNum_CellEditor = new KDTDefaultCellEditor(kdtEntry_adjustNum_TextField);
        this.kdtEntry.getColumn("adjustNum").setEditor(kdtEntry_adjustNum_CellEditor);
        // kdPriceInfo
		String kdPriceInfoStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol1\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol3\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol4\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol5\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol9\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol12\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol13\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"material.id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol1\" /><t:Column t:key=\"material.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol2\" /><t:Column t:key=\"material.model\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol3\" /><t:Column t:key=\"assistProperty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol4\" /><t:Column t:key=\"unit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol5\" /><t:Column t:key=\"price\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" /><t:Column t:key=\"discountType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"discount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" /><t:Column t:key=\"realPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol9\" /><t:Column t:key=\"taxRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" /><t:Column t:key=\"taxPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" /><t:Column t:key=\"realTaxPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol12\" /><t:Column t:key=\"entryID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol13\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header1\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{material.id}</t:Cell><t:Cell>$Resource{material.name}</t:Cell><t:Cell>$Resource{material.model}</t:Cell><t:Cell>$Resource{assistProperty}</t:Cell><t:Cell>$Resource{unit}</t:Cell><t:Cell>$Resource{price}</t:Cell><t:Cell>$Resource{discountType}</t:Cell><t:Cell>$Resource{discount}</t:Cell><t:Cell>$Resource{realPrice}</t:Cell><t:Cell>$Resource{taxRate}</t:Cell><t:Cell>$Resource{taxPrice}</t:Cell><t:Cell>$Resource{realTaxPrice}</t:Cell><t:Cell>$Resource{entryID}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdPriceInfo.setFormatXml(resHelper.translateString("kdPriceInfo",kdPriceInfoStrXML));

                this.kdPriceInfo.putBindContents("editData",new String[] {"id","material","material.name","material.model","assistProperty","unit","price","discountType","discount","realPrice","taxRate","taxPrice","realTaxPrice","materialReqEntryID"});


        this.kdPriceInfo.checkParsed();
        final KDBizPromptBox kdPriceInfo_material_id_PromptBox = new KDBizPromptBox();
        kdPriceInfo_material_id_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7MaterialBaseInfoQuery");
        kdPriceInfo_material_id_PromptBox.setVisible(true);
        kdPriceInfo_material_id_PromptBox.setEditable(true);
        kdPriceInfo_material_id_PromptBox.setDisplayFormat("$number$");
        kdPriceInfo_material_id_PromptBox.setEditFormat("$number$");
        kdPriceInfo_material_id_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdPriceInfo_material_id_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_material_id_PromptBox);
        this.kdPriceInfo.getColumn("material.id").setEditor(kdPriceInfo_material_id_CellEditor);
        ObjectValueRender kdPriceInfo_material_id_OVR = new ObjectValueRender();
        kdPriceInfo_material_id_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdPriceInfo.getColumn("material.id").setRenderer(kdPriceInfo_material_id_OVR);
        KDTextField kdPriceInfo_material_model_TextField = new KDTextField();
        kdPriceInfo_material_model_TextField.setName("kdPriceInfo_material_model_TextField");
        kdPriceInfo_material_model_TextField.setMaxLength(255);
        KDTDefaultCellEditor kdPriceInfo_material_model_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_material_model_TextField);
        this.kdPriceInfo.getColumn("material.model").setEditor(kdPriceInfo_material_model_CellEditor);
        final KDBizPromptBox kdPriceInfo_assistProperty_PromptBox = new KDBizPromptBox();
        kdPriceInfo_assistProperty_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7AsstAttrValueQuery");
        kdPriceInfo_assistProperty_PromptBox.setVisible(true);
        kdPriceInfo_assistProperty_PromptBox.setEditable(true);
        kdPriceInfo_assistProperty_PromptBox.setDisplayFormat("$number$");
        kdPriceInfo_assistProperty_PromptBox.setEditFormat("$number$");
        kdPriceInfo_assistProperty_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdPriceInfo_assistProperty_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_assistProperty_PromptBox);
        this.kdPriceInfo.getColumn("assistProperty").setEditor(kdPriceInfo_assistProperty_CellEditor);
        ObjectValueRender kdPriceInfo_assistProperty_OVR = new ObjectValueRender();
        kdPriceInfo_assistProperty_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdPriceInfo.getColumn("assistProperty").setRenderer(kdPriceInfo_assistProperty_OVR);
        final KDBizPromptBox kdPriceInfo_unit_PromptBox = new KDBizPromptBox();
        kdPriceInfo_unit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7MeasureUnitQuery");
        kdPriceInfo_unit_PromptBox.setVisible(true);
        kdPriceInfo_unit_PromptBox.setEditable(true);
        kdPriceInfo_unit_PromptBox.setDisplayFormat("$number$");
        kdPriceInfo_unit_PromptBox.setEditFormat("$number$");
        kdPriceInfo_unit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdPriceInfo_unit_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_unit_PromptBox);
        this.kdPriceInfo.getColumn("unit").setEditor(kdPriceInfo_unit_CellEditor);
        ObjectValueRender kdPriceInfo_unit_OVR = new ObjectValueRender();
        kdPriceInfo_unit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdPriceInfo.getColumn("unit").setRenderer(kdPriceInfo_unit_OVR);
        KDFormattedTextField kdPriceInfo_price_TextField = new KDFormattedTextField();
        kdPriceInfo_price_TextField.setName("kdPriceInfo_price_TextField");
        kdPriceInfo_price_TextField.setVisible(true);
        kdPriceInfo_price_TextField.setEditable(true);
        kdPriceInfo_price_TextField.setHorizontalAlignment(2);
        kdPriceInfo_price_TextField.setDataType(1);
        	kdPriceInfo_price_TextField.setMinimumValue(new java.math.BigDecimal("-99999.99999999"));
        	kdPriceInfo_price_TextField.setMaximumValue(new java.math.BigDecimal("99999.99999999"));
        kdPriceInfo_price_TextField.setPrecision(8);
        KDTDefaultCellEditor kdPriceInfo_price_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_price_TextField);
        this.kdPriceInfo.getColumn("price").setEditor(kdPriceInfo_price_CellEditor);
        KDComboBox kdPriceInfo_discountType_ComboBox = new KDComboBox();
        kdPriceInfo_discountType_ComboBox.setName("kdPriceInfo_discountType_ComboBox");
        kdPriceInfo_discountType_ComboBox.setVisible(true);
        kdPriceInfo_discountType_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.basedata.scm.common.DiscountModeEnum").toArray());
        KDTDefaultCellEditor kdPriceInfo_discountType_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_discountType_ComboBox);
        this.kdPriceInfo.getColumn("discountType").setEditor(kdPriceInfo_discountType_CellEditor);
        KDFormattedTextField kdPriceInfo_discount_TextField = new KDFormattedTextField();
        kdPriceInfo_discount_TextField.setName("kdPriceInfo_discount_TextField");
        kdPriceInfo_discount_TextField.setVisible(true);
        kdPriceInfo_discount_TextField.setEditable(true);
        kdPriceInfo_discount_TextField.setHorizontalAlignment(2);
        kdPriceInfo_discount_TextField.setDataType(1);
        kdPriceInfo_discount_TextField.setPrecision(16);
        KDTDefaultCellEditor kdPriceInfo_discount_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_discount_TextField);
        this.kdPriceInfo.getColumn("discount").setEditor(kdPriceInfo_discount_CellEditor);
        KDFormattedTextField kdPriceInfo_realPrice_TextField = new KDFormattedTextField();
        kdPriceInfo_realPrice_TextField.setName("kdPriceInfo_realPrice_TextField");
        kdPriceInfo_realPrice_TextField.setVisible(true);
        kdPriceInfo_realPrice_TextField.setEditable(true);
        kdPriceInfo_realPrice_TextField.setHorizontalAlignment(2);
        kdPriceInfo_realPrice_TextField.setDataType(1);
        	kdPriceInfo_realPrice_TextField.setMinimumValue(new java.math.BigDecimal("-99999.99999999"));
        	kdPriceInfo_realPrice_TextField.setMaximumValue(new java.math.BigDecimal("99999.99999999"));
        kdPriceInfo_realPrice_TextField.setPrecision(8);
        KDTDefaultCellEditor kdPriceInfo_realPrice_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_realPrice_TextField);
        this.kdPriceInfo.getColumn("realPrice").setEditor(kdPriceInfo_realPrice_CellEditor);
        KDFormattedTextField kdPriceInfo_taxRate_TextField = new KDFormattedTextField();
        kdPriceInfo_taxRate_TextField.setName("kdPriceInfo_taxRate_TextField");
        kdPriceInfo_taxRate_TextField.setVisible(true);
        kdPriceInfo_taxRate_TextField.setEditable(true);
        kdPriceInfo_taxRate_TextField.setHorizontalAlignment(2);
        kdPriceInfo_taxRate_TextField.setDataType(1);
        kdPriceInfo_taxRate_TextField.setPrecision(16);
        KDTDefaultCellEditor kdPriceInfo_taxRate_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_taxRate_TextField);
        this.kdPriceInfo.getColumn("taxRate").setEditor(kdPriceInfo_taxRate_CellEditor);
        KDFormattedTextField kdPriceInfo_taxPrice_TextField = new KDFormattedTextField();
        kdPriceInfo_taxPrice_TextField.setName("kdPriceInfo_taxPrice_TextField");
        kdPriceInfo_taxPrice_TextField.setVisible(true);
        kdPriceInfo_taxPrice_TextField.setEditable(true);
        kdPriceInfo_taxPrice_TextField.setHorizontalAlignment(2);
        kdPriceInfo_taxPrice_TextField.setDataType(1);
        	kdPriceInfo_taxPrice_TextField.setMinimumValue(new java.math.BigDecimal("-99999.99999999"));
        	kdPriceInfo_taxPrice_TextField.setMaximumValue(new java.math.BigDecimal("99999.99999999"));
        kdPriceInfo_taxPrice_TextField.setPrecision(8);
        KDTDefaultCellEditor kdPriceInfo_taxPrice_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_taxPrice_TextField);
        this.kdPriceInfo.getColumn("taxPrice").setEditor(kdPriceInfo_taxPrice_CellEditor);
        KDFormattedTextField kdPriceInfo_realTaxPrice_TextField = new KDFormattedTextField();
        kdPriceInfo_realTaxPrice_TextField.setName("kdPriceInfo_realTaxPrice_TextField");
        kdPriceInfo_realTaxPrice_TextField.setVisible(true);
        kdPriceInfo_realTaxPrice_TextField.setEditable(true);
        kdPriceInfo_realTaxPrice_TextField.setHorizontalAlignment(2);
        kdPriceInfo_realTaxPrice_TextField.setDataType(1);
        	kdPriceInfo_realTaxPrice_TextField.setMinimumValue(new java.math.BigDecimal("-99999.99999999"));
        	kdPriceInfo_realTaxPrice_TextField.setMaximumValue(new java.math.BigDecimal("99999.99999999"));
        kdPriceInfo_realTaxPrice_TextField.setPrecision(8);
        KDTDefaultCellEditor kdPriceInfo_realTaxPrice_CellEditor = new KDTDefaultCellEditor(kdPriceInfo_realTaxPrice_TextField);
        this.kdPriceInfo.getColumn("realTaxPrice").setEditor(kdPriceInfo_realTaxPrice_CellEditor);
        // prmtBizType		
        this.prmtBizType.setLabelLength(100);		
        this.prmtBizType.setQueryInfo("com.kingdee.eas.basedata.scm.common.app.BizTypeQuery");		
        this.prmtBizType.setCommitFormat("$number$");		
        this.prmtBizType.setEditFormat("$number$");		
        this.prmtBizType.setDisplayFormat("$name$");		
        this.prmtBizType.setLabelVisible(true);		
        this.prmtBizType.setVisible(true);		
        this.prmtBizType.setRequired(true);		
        this.prmtBizType.setEditable(true);
        // txtDescription		
        this.txtDescription.setVisible(true);		
        this.txtDescription.setEnabled(true);		
        this.txtDescription.setHorizontalAlignment(2);
        // prmtHeadSupplier		
        this.prmtHeadSupplier.setDisplayFormat("$name$");		
        this.prmtHeadSupplier.setEditFormat("$number$");		
        this.prmtHeadSupplier.setCommitFormat("$number$");		
        this.prmtHeadSupplier.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierQuery");		
        this.prmtHeadSupplier.setLabelVisible(true);		
        this.prmtHeadSupplier.setEditable(true);		
        this.prmtHeadSupplier.setVisible(true);		
        this.prmtHeadSupplier.setRequired(true);
        // prmtSupplyStorageOrgUnit		
        this.prmtSupplyStorageOrgUnit.setLabelVisible(true);		
        this.prmtSupplyStorageOrgUnit.setDisplayFormat("$name$");		
        this.prmtSupplyStorageOrgUnit.setEditFormat("$number$");		
        this.prmtSupplyStorageOrgUnit.setCommitFormat("$number$");		
        this.prmtSupplyStorageOrgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.StorageOrgUnitQuery");		
        this.prmtSupplyStorageOrgUnit.setRequired(true);		
        this.prmtSupplyStorageOrgUnit.setEditable(true);		
        this.prmtSupplyStorageOrgUnit.setVisible(true);		
        this.prmtSupplyStorageOrgUnit.setEnabled(true);
        // purchaseType		
        this.purchaseType.addItems(EnumUtils.getEnumList("com.kingdee.eas.scm.common.PurchaseTypeEnum").toArray());		
        this.purchaseType.setRequired(true);		
        this.purchaseType.setVisible(false);
        // btnOffSet
        this.btnOffSet.setAction((IItemAction)ActionProxyFactory.getProxy(actionOffSet, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnOffSet.setText(resHelper.getString("btnOffSet.text"));		
        this.btnOffSet.setToolTipText(resHelper.getString("btnOffSet.toolTipText"));
        // btnQuickAddLine
        this.btnQuickAddLine.setAction((IItemAction)ActionProxyFactory.getProxy(actionQuickAddLine, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnQuickAddLine.setText(resHelper.getString("btnQuickAddLine.text"));		
        this.btnQuickAddLine.setToolTipText(resHelper.getString("btnQuickAddLine.toolTipText"));
        // menuItemOffset
        this.menuItemOffset.setAction((IItemAction)ActionProxyFactory.getProxy(actionOffSet, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemOffset.setText(resHelper.getString("menuItemOffset.text"));		
        this.menuItemOffset.setToolTipText(resHelper.getString("menuItemOffset.toolTipText"));		
        this.menuItemOffset.setMnemonic(87);
        // chkmenuItemCostObjectSuite
        this.chkmenuItemCostObjectSuite.setAction((IItemAction)ActionProxyFactory.getProxy(actionCostObjectSuite, new Class[] { IItemAction.class }, getServiceContext()));		
        this.chkmenuItemCostObjectSuite.setText(resHelper.getString("chkmenuItemCostObjectSuite.text"));		
        this.chkmenuItemCostObjectSuite.setSelected(true);
        // menuItemQuickAddLine
        this.menuItemQuickAddLine.setAction((IItemAction)ActionProxyFactory.getProxy(actionQuickAddLine, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemQuickAddLine.setText(resHelper.getString("menuItemQuickAddLine.text"));		
        this.menuItemQuickAddLine.setToolTipText(resHelper.getString("menuItemQuickAddLine.toolTipText"));		
        this.menuItemQuickAddLine.setMnemonic(66);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {kDDPCreatDate,prmtCreator,prmtAuditor,pkBizDate,txtNumber,kDDPUpdatDate,prmtLastUpdateUser,prmtSourceBillType,prmtBizType,comboBaseStatus,kDDPAuditDate,cboxIsInitBill,prmtTransactionType,cboxIsOffset,txtAddFactCost,txtAddStandardCost,txtTotalAmount,bizInventory,prmtDepartment,kDSeparatorLine,prmtStorageOrgUnit,prmtCostCenterUnit,kdtEntry,txtDescription,kdPriceInfo,prmtSupplyStorageOrgUnit,isBackflush,purchaseType,prmtHeadSupplier}));
        this.setFocusCycleRoot(true);
		//Register control's property binding
		registerBindings();
		registerUIState();
		setUseAgent(true);


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
        contCreator.setBounds(new Rectangle(702, 578, 300, 19));
        this.add(contCreator, new KDLayout.Constraints(702, 578, 300, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contCreateTime.setBounds(new Rectangle(702, 600, 300, 19));
        this.add(contCreateTime, new KDLayout.Constraints(702, 600, 300, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contLastUpdateUser.setBounds(new Rectangle(356, 578, 300, 19));
        this.add(contLastUpdateUser, new KDLayout.Constraints(356, 578, 300, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateTime.setBounds(new Rectangle(356, 600, 300, 19));
        this.add(contLastUpdateTime, new KDLayout.Constraints(356, 600, 300, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNumber.setBounds(new Rectangle(10, 10, 300, 19));
        this.add(contNumber, new KDLayout.Constraints(10, 10, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBizDate.setBounds(new Rectangle(702, 10, 300, 19));
        this.add(contBizDate, new KDLayout.Constraints(702, 10, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditor.setBounds(new Rectangle(10, 578, 300, 19));
        this.add(contAuditor, new KDLayout.Constraints(10, 578, 300, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAuditTime.setBounds(new Rectangle(10, 600, 300, 19));
        this.add(contAuditTime, new KDLayout.Constraints(10, 600, 300, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBaseStatus.setBounds(new Rectangle(10, 54, 300, 19));
        this.add(contBaseStatus, new KDLayout.Constraints(10, 54, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contTransactionType.setBounds(new Rectangle(356, 33, 300, 19));
        this.add(contTransactionType, new KDLayout.Constraints(356, 33, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contStorageOrgUnit.setBounds(new Rectangle(356, 54, 300, 19));
        this.add(contStorageOrgUnit, new KDLayout.Constraints(356, 54, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contDepartment.setBounds(new Rectangle(702, 31, 300, 19));
        this.add(contDepartment, new KDLayout.Constraints(702, 31, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contSourceBillType.setBounds(new Rectangle(10, 75, 300, 19));
        this.add(contSourceBillType, new KDLayout.Constraints(10, 75, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contTotalAmount.setBounds(new Rectangle(688, 538, 300, 19));
        this.add(contTotalAmount, new KDLayout.Constraints(688, 538, 300, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAddStandardCost.setBounds(new Rectangle(10, 538, 300, 19));
        this.add(contAddStandardCost, new KDLayout.Constraints(10, 538, 300, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAddFactCost.setBounds(new Rectangle(356, 538, 300, 19));
        this.add(contAddFactCost, new KDLayout.Constraints(356, 538, 300, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        kDSeparatorLine.setBounds(new Rectangle(0, 568, 1013, 10));
        this.add(kDSeparatorLine, new KDLayout.Constraints(0, 568, 1013, 10, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        cboxIsOffset.setBounds(new Rectangle(702, 55, 95, 19));
        this.add(cboxIsOffset, new KDLayout.Constraints(702, 55, 95, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        cboxIsInitBill.setBounds(new Rectangle(801, 56, 83, 19));
        this.add(cboxIsInitBill, new KDLayout.Constraints(801, 56, 83, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contCostCenterUnit.setBounds(new Rectangle(356, 75, 300, 19));
        this.add(contCostCenterUnit, new KDLayout.Constraints(356, 75, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        ctnMain.setBounds(new Rectangle(10, 104, 991, 462));
        this.add(ctnMain, new KDLayout.Constraints(10, 104, 991, 462, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contBizType.setBounds(new Rectangle(356, 10, 300, 19));
        this.add(contBizType, new KDLayout.Constraints(356, 10, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        isBackflush.setBounds(new Rectangle(889, 57, 72, 19));
        this.add(isBackflush, new KDLayout.Constraints(889, 57, 72, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contDescription.setBounds(new Rectangle(702, 75, 300, 19));
        this.add(contDescription, new KDLayout.Constraints(702, 75, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contsupplier.setBounds(new Rectangle(10, 31, 300, 19));
        this.add(contsupplier, new KDLayout.Constraints(10, 31, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        supplycon.setBounds(new Rectangle(10, 31, 300, 19));
        this.add(supplycon, new KDLayout.Constraints(10, 31, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        prmtPurchaseType.setBounds(new Rectangle(702, 76, 300, 19));
        this.add(prmtPurchaseType, new KDLayout.Constraints(702, 76, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contCreator
        contCreator.setBoundEditor(prmtCreator);
        //contCreateTime
        contCreateTime.setBoundEditor(kDDPCreatDate);
        //contLastUpdateUser
        contLastUpdateUser.setBoundEditor(prmtLastUpdateUser);
        //contLastUpdateTime
        contLastUpdateTime.setBoundEditor(kDDPUpdatDate);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contBizDate
        contBizDate.setBoundEditor(pkBizDate);
        //contAuditor
        contAuditor.setBoundEditor(prmtAuditor);
        //contAuditTime
        contAuditTime.setBoundEditor(kDDPAuditDate);
        //contBaseStatus
        contBaseStatus.setBoundEditor(comboBaseStatus);
        //contTransactionType
        contTransactionType.setBoundEditor(prmtTransactionType);
        //contStorageOrgUnit
        contStorageOrgUnit.setBoundEditor(prmtStorageOrgUnit);
        //contDepartment
        contDepartment.setBoundEditor(prmtDepartment);
        //contSourceBillType
        contSourceBillType.setBoundEditor(prmtSourceBillType);
        //contTotalAmount
        contTotalAmount.setBoundEditor(txtTotalAmount);
        //contAddStandardCost
        contAddStandardCost.setBoundEditor(txtAddStandardCost);
        //contAddFactCost
        contAddFactCost.setBoundEditor(txtAddFactCost);
        //contCostCenterUnit
        contCostCenterUnit.setBoundEditor(prmtCostCenterUnit);
        //ctnMain
        ctnMain.getContentPane().setLayout(new KDLayout());
        ctnMain.getContentPane().putClientProperty("OriginalBounds", new Rectangle(10, 104, 991, 462));        bizInventory.setBounds(new Rectangle(797, -20, 170, 19));
        ctnMain.getContentPane().add(bizInventory, new KDLayout.Constraints(797, -20, 170, 19, 0));
        kdtabbedData.setBounds(new Rectangle(2, 2, 990, 439));
        ctnMain.getContentPane().add(kdtabbedData, new KDLayout.Constraints(2, 2, 990, 439, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //kdtabbedData
        kdtabbedData.add(basePanel, resHelper.getString("basePanel.constraints"));
        kdtabbedData.add(priceInfoPanel, resHelper.getString("priceInfoPanel.constraints"));
        //basePanel
basePanel.setLayout(new BorderLayout(0, 0));        basePanel.add(kdtEntry, BorderLayout.CENTER);
        //priceInfoPanel
priceInfoPanel.setLayout(new BorderLayout(0, 0));        priceInfoPanel.add(kdPriceInfo, BorderLayout.CENTER);
        //contBizType
        contBizType.setBoundEditor(prmtBizType);
        //contDescription
        contDescription.setBoundEditor(txtDescription);
        //contsupplier
        contsupplier.setBoundEditor(prmtHeadSupplier);
        //supplycon
        supplycon.setBoundEditor(prmtSupplyStorageOrgUnit);
        //prmtPurchaseType
        prmtPurchaseType.setBoundEditor(purchaseType);

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
        this.menuBar.add(menuTable);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkflow);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(separator9);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuItemCloudShare);
        menuFile.add(menuSubmitOption);
        menuFile.add(menuItemPageSetup);
        menuFile.add(kdSeparatorFWFile1);
        menuFile.add(separator11);
        menuFile.add(MenuItemAttachment);
        menuFile.add(separator1);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator3);
        menuFile.add(menuItemExitCurrent);
        menuFile.add(separatorFile1);
        menuFile.add(kDSeparator2);
        menuFile.add(rMenuItemSubmit);
        menuFile.add(menuItemSendMail);
        menuFile.add(rMenuItemSubmitAndAddNew);
        menuFile.add(rMenuItemSubmitAndPrint);
        //menuSubmitOption
        menuSubmitOption.add(chkMenuItemSubmitAndAddNew);
        menuSubmitOption.add(chkMenuItemSubmitAndPrint);
        //menuEdit
        menuEdit.add(menuItemCopy);
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(separatorEdit1);
        menuEdit.add(menuItemReset);
        menuEdit.add(menuItemCreateFrom);
        menuEdit.add(menuItemCopyFrom);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemOffset);
        menuEdit.add(chkmenuItemCostObjectSuite);
        menuEdit.add(menuItemEnterToNextRow);
        menuEdit.add(menuItemInvBillOptin);
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
        menuView.add(separator2);
        menuView.add(kDSeparator7);
        menuView.add(kDSeparator4);
        menuView.add(menuItemLocate);
        menuView.add(menuItemQueryGeneralInventory);
        menuView.add(menuItemQueryByMaterial);
        menuView.add(menuItemDecompose);
        menuView.add(menuItemMaterialView);
        menuView.add(menuItemCustomerView);
        menuView.add(menuItemSupplierView);
        menuView.add(kDSeparator6);
        menuView.add(menuItemSerialNumber);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemCancel);
        menuBiz.add(menuItemDelVoucher);
        menuBiz.add(menuBizProcess);
        //menuTable1
        menuTable1.add(menuItemAddLine);
        menuTable1.add(menuItemInsertLine);
        menuTable1.add(menuItemCopyLine);
        menuTable1.add(menuItemRemoveLine);
        menuTable1.add(menuItemSplitLine);
        menuTable1.add(menuItemSplitOption);
        menuTable1.add(kDSeparator5);
        menuTable1.add(menuItemQuickAddLine);
        menuTable1.add(menuItemSNsplit);
        //menuTable
        menuTable.add(separator10);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuWorkflow
        menuWorkflow.add(separatorWF1);
        menuWorkflow.add(menuItemStartWorkFlow);
        menuWorkflow.add(MenuItemWFG);
        menuWorkflow.add(separator13);
        menuWorkflow.add(menuItemAudit);
        menuWorkflow.add(menuItemWorkFlowList);
        menuWorkflow.add(menuItemUnAudit);
        menuWorkflow.add(menuItemMultiapprove);
        menuWorkflow.add(menuItemNextPerson);
        menuWorkflow.add(menuItemAuditResult);
        menuWorkflow.add(separatorWF2);
        menuWorkflow.add(menuItemViewSubmitProccess);
        menuWorkflow.add(kDMenuItemSendMessage);
        menuWorkflow.add(menuItemViewDoProccess);
        //menuHelp
        menuHelp.add(menuItemHelp);
        menuHelp.add(kDSeparator12);
        menuHelp.add(menuItemRegPro);
        menuHelp.add(separator12);
        menuHelp.add(menuItemPersonalSite);
        menuHelp.add(menuItemAbout);
        menuHelp.add(helpseparatorDiv);
        menuHelp.add(menuitemProductval);
        menuHelp.add(kDSeparatorProduct);

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnCloud);
        this.toolBar.add(btnEdit);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separator8);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separator7);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(separator5);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separator4);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(btnMaterialView);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnAudit);
        this.toolBar.add(btnUnAudit);
        this.toolBar.add(workbtnBizProcess);
        this.toolBar.add(btnOffSet);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(btnCopyLine);
        this.toolBar.add(sp_bizProcess);
        this.toolBar.add(btnQueryByMaterial);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(separator6);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnInsertLine);
        this.toolBar.add(btnRemoveLine);
        this.toolBar.add(btnSplitLine);
        this.toolBar.add(btnQuickAddLine);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnWFViewSubmitProccess);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(separatorFW6);
        this.toolBar.add(separatorFW9);
        this.toolBar.add(btnQueryGeneralInventory);
        this.toolBar.add(btnSerialNumber);
        this.toolBar.add(btnDecompose);
        this.toolBar.add(btnSplitOption);
        this.toolBar.add(btnSNsplit);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("isReversed", boolean.class, this.cboxIsOffset, "selected");
		dataBinder.registerBinding("isInitBill", boolean.class, this.cboxIsInitBill, "selected");
		dataBinder.registerBinding("isBackflush", boolean.class, this.isBackflush, "selected");
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtCreator, "data");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.kDDPCreatDate, "value");
		dataBinder.registerBinding("modifier", com.kingdee.eas.base.permission.UserInfo.class, this.prmtLastUpdateUser, "data");
		dataBinder.registerBinding("modificationTime", java.sql.Timestamp.class, this.kDDPUpdatDate, "value");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("bizDate", java.util.Date.class, this.pkBizDate, "value");
		dataBinder.registerBinding("auditor", com.kingdee.eas.base.permission.UserInfo.class, this.prmtAuditor, "data");
		dataBinder.registerBinding("auditTime", java.sql.Timestamp.class, this.kDDPAuditDate, "value");
		dataBinder.registerBinding("baseStatus", com.kingdee.eas.scm.common.BillBaseStatusEnum.class, this.comboBaseStatus, "selectedItem");
		dataBinder.registerBinding("transactionType", com.kingdee.eas.basedata.scm.common.TransactionTypeInfo.class, this.prmtTransactionType, "data");
		dataBinder.registerBinding("storageOrgUnit", com.kingdee.eas.basedata.org.StorageOrgUnitInfo.class, this.prmtStorageOrgUnit, "data");
		dataBinder.registerBinding("adminOrgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtDepartment, "data");
		dataBinder.registerBinding("sourceBillType", com.kingdee.eas.basedata.scm.common.BillTypeInfo.class, this.prmtSourceBillType, "data");
		dataBinder.registerBinding("totalAmount", java.math.BigDecimal.class, this.txtTotalAmount, "value");
		dataBinder.registerBinding("totalStandardCost", java.math.BigDecimal.class, this.txtAddStandardCost, "value");
		dataBinder.registerBinding("totalActualCost", java.math.BigDecimal.class, this.txtAddFactCost, "value");
		dataBinder.registerBinding("costCenterOrgUnit", com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo.class, this.prmtCostCenterUnit, "data");
		dataBinder.registerBinding("entry.id", com.kingdee.bos.util.BOSUuid.class, this.kdtEntry, "ID.text");
		dataBinder.registerBinding("entry", com.kingdee.eas.scm.im.inv.MaterialReqBillEntryInfo.class, this.kdtEntry, "userObject");
		dataBinder.registerBinding("entry.seq", int.class, this.kdtEntry, "seq.text");
		dataBinder.registerBinding("entry.productID", com.kingdee.eas.basedata.master.material.MaterialInfo.class, this.kdtEntry, "productID.text");
		dataBinder.registerBinding("entry.material", com.kingdee.eas.basedata.master.material.MaterialInfo.class, this.kdtEntry, "materialID.text");
		dataBinder.registerBinding("entry.productID.name", String.class, this.kdtEntry, "productName.text");
		dataBinder.registerBinding("entry.material.name", String.class, this.kdtEntry, "materialName.text");
		dataBinder.registerBinding("entry.material.model", String.class, this.kdtEntry, "materialMode.text");
		dataBinder.registerBinding("entry.unit", com.kingdee.eas.basedata.assistant.MeasureUnitInfo.class, this.kdtEntry, "UnitID.text");
		dataBinder.registerBinding("entry.qty", java.math.BigDecimal.class, this.kdtEntry, "qty.text");
		dataBinder.registerBinding("entry.warehouse", com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo.class, this.kdtEntry, "warehouse.text");
		dataBinder.registerBinding("entry.location", com.kingdee.eas.basedata.scm.im.inv.LocationInfo.class, this.kdtEntry, "location.text");
		dataBinder.registerBinding("entry.unitStandardCost", java.math.BigDecimal.class, this.kdtEntry, "unitStandardCost.text");
		dataBinder.registerBinding("entry.standardCost", java.math.BigDecimal.class, this.kdtEntry, "standardCost.text");
		dataBinder.registerBinding("entry.stocker", com.kingdee.eas.basedata.person.PersonInfo.class, this.kdtEntry, "stocker.text");
		dataBinder.registerBinding("entry.baseUnit", com.kingdee.eas.basedata.assistant.MeasureUnitInfo.class, this.kdtEntry, "baseUnit.text");
		dataBinder.registerBinding("entry.orderNumber", String.class, this.kdtEntry, "poBillID.text");
		dataBinder.registerBinding("entry.assistProperty", com.kingdee.eas.basedata.master.material.AsstAttrValueInfo.class, this.kdtEntry, "assistantAttr.text");
		dataBinder.registerBinding("entry.assistUnit", com.kingdee.eas.basedata.assistant.MeasureUnitInfo.class, this.kdtEntry, "assistantUnit.text");
		dataBinder.registerBinding("entry.assistQty", java.math.BigDecimal.class, this.kdtEntry, "assistantQty.text");
		dataBinder.registerBinding("entry.reverseQty", java.math.BigDecimal.class, this.kdtEntry, "countervailQty.text");
		dataBinder.registerBinding("entry.baseQty", java.math.BigDecimal.class, this.kdtEntry, "baseQty.text");
		dataBinder.registerBinding("entry.exp", java.util.Date.class, this.kdtEntry, "exp.text");
		dataBinder.registerBinding("entry.unitActualCost", java.math.BigDecimal.class, this.kdtEntry, "unitFactCost.text");
		dataBinder.registerBinding("entry.actualCost", java.math.BigDecimal.class, this.kdtEntry, "factCost.text");
		dataBinder.registerBinding("entry.remark", String.class, this.kdtEntry, "remark.text");
		dataBinder.registerBinding("entry.mfg", java.util.Date.class, this.kdtEntry, "mfg.text");
		dataBinder.registerBinding("entry.lot", String.class, this.kdtEntry, "lot.text");
		dataBinder.registerBinding("entry.costObject", com.kingdee.eas.basedata.assistant.CostObjectInfo.class, this.kdtEntry, "costObject.text");
		dataBinder.registerBinding("entry.costObject.name", String.class, this.kdtEntry, "costObjectName.text");
		dataBinder.registerBinding("entry.costObjectSuite", com.kingdee.eas.basedata.assistant.CostObjectSuiteInfo.class, this.kdtEntry, "costObjectSuite.text");
		dataBinder.registerBinding("entry.costObjectSuite.name", String.class, this.kdtEntry, "costObjectSuiteName.text");
		dataBinder.registerBinding("entry.costItem", com.kingdee.eas.basedata.assistant.CostItemInfo.class, this.kdtEntry, "costItem.text");
		dataBinder.registerBinding("entry.costItem.name", String.class, this.kdtEntry, "costItemName.text");
		dataBinder.registerBinding("entry.classGroup", com.kingdee.eas.mm.basedata.ClassGroupInfo.class, this.kdtEntry, "classGroup.text");
		dataBinder.registerBinding("entry.person", com.kingdee.eas.basedata.person.PersonInfo.class, this.kdtEntry, "person.text");
		dataBinder.registerBinding("entry.productLineWP", com.kingdee.eas.mm.basedata.ProductLineWPEntryInfo.class, this.kdtEntry, "productLineWP.text");
		dataBinder.registerBinding("entry.saleOrderNum", String.class, this.kdtEntry, "saleOrderNum.text");
		dataBinder.registerBinding("entry.issueQty", java.math.BigDecimal.class, this.kdtEntry, "issueQty.text");
		dataBinder.registerBinding("entry.baseIssueQty", java.math.BigDecimal.class, this.kdtEntry, "baseIssueQty.text");
		dataBinder.registerBinding("entry.pickingDate", java.sql.Timestamp.class, this.kdtEntry, "pickingDate.text");
		dataBinder.registerBinding("entry.demandDate", java.sql.Timestamp.class, this.kdtEntry, "demandDate.text");
		dataBinder.registerBinding("entry.productLine", com.kingdee.eas.mm.basedata.ProductLineInfo.class, this.kdtEntry, "productLine.text");
		dataBinder.registerBinding("entry.faCardQty", java.math.BigDecimal.class, this.kdtEntry, "faCardQty.text");
		dataBinder.registerBinding("entry.coreBillNumber", String.class, this.kdtEntry, "coreBillNumber.text");
		dataBinder.registerBinding("entry.coreBillType", java.lang.Object.class, this.kdtEntry, "coreBillType.text");
		dataBinder.registerBinding("entry.coreBillEntrySe", int.class, this.kdtEntry, "coreBillEntrySe.text");
		dataBinder.registerBinding("entry.subWrittenOffQty", java.math.BigDecimal.class, this.kdtEntry, "subWrittenOffQty.text");
		dataBinder.registerBinding("entry.subWrittenOffBaseQty", java.math.BigDecimal.class, this.kdtEntry, "subWrittenOffBaseQty.text");
		dataBinder.registerBinding("entry.subUnWriteOffQty", java.math.BigDecimal.class, this.kdtEntry, "subUnWriteOffQty.text");
		dataBinder.registerBinding("entry.subUnWriteOffBaseQty", java.math.BigDecimal.class, this.kdtEntry, "subUnWriteOffBaseQty.text");
		dataBinder.registerBinding("entry.processMaterial.name", String.class, this.kdtEntry, "processMaterialName.text");
		dataBinder.registerBinding("entry.processMaterial", com.kingdee.eas.basedata.master.material.MaterialInfo.class, this.kdtEntry, "processMaterialID.text");
		dataBinder.registerBinding("entry.issueAdminOrgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.kdtEntry, "issueAdminOrgUnit.text");
		dataBinder.registerBinding("entry.issuePerson", com.kingdee.eas.basedata.person.PersonInfo.class, this.kdtEntry, "issuePerson.text");
		dataBinder.registerBinding("entry.operationNo", int.class, this.kdtEntry, "operationNo.text");
		dataBinder.registerBinding("entry.operation", com.kingdee.eas.mm.basedata.OperationInfo.class, this.kdtEntry, "operationID.text");
		dataBinder.registerBinding("entry.operation.name", String.class, this.kdtEntry, "operationNAME.text");
		dataBinder.registerBinding("entry.workCenter", com.kingdee.eas.mm.basedata.WorkCenterInfo.class, this.kdtEntry, "workCenterID.text");
		dataBinder.registerBinding("entry.workCenter.name", String.class, this.kdtEntry, "workCenterNAME.text");
		dataBinder.registerBinding("entry.isAdmeasure", boolean.class, this.kdtEntry, "isAdmeasure.text");
		dataBinder.registerBinding("entry.isReWork", boolean.class, this.kdtEntry, "isReWork.text");
		dataBinder.registerBinding("entry.trackNumber", com.kingdee.eas.mm.basedata.TrackNumberInfo.class, this.kdtEntry, "trackNumCol.text");
		dataBinder.registerBinding("entry.project", com.kingdee.eas.mm.project.ProjectInfo.class, this.kdtEntry, "projectNumCol.text");
		dataBinder.registerBinding("entry.scWrittenOffAmount", java.math.BigDecimal.class, this.kdtEntry, "writtenOffAmount.text");
		dataBinder.registerBinding("entry.scUnWrittenOffAmount", java.math.BigDecimal.class, this.kdtEntry, "unWriteOffAmount.text");
		dataBinder.registerBinding("entry.customer", com.kingdee.eas.basedata.master.cssp.CustomerInfo.class, this.kdtEntry, "customer.text");
		dataBinder.registerBinding("entry.supplier", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.kdtEntry, "supplier.text");
		dataBinder.registerBinding("entry.isPresent", boolean.class, this.kdtEntry, "isPresent.text");
		dataBinder.registerBinding("entry.supplyWarehouse", com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo.class, this.kdtEntry, "supplyWareHs.text");
		dataBinder.registerBinding("entry.supplyLocation", com.kingdee.eas.basedata.scm.im.inv.LocationInfo.class, this.kdtEntry, "supplyLocation.text");
		dataBinder.registerBinding("entry.settlePrice", java.math.BigDecimal.class, this.kdtEntry, "settlePrice.text");
		dataBinder.registerBinding("entry.picker", com.kingdee.eas.basedata.person.PersonInfo.class, this.kdtEntry, "picker.text");
		dataBinder.registerBinding("entry.adminOrgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.kdtEntry, "mainAdminOrgUnit.text");
		dataBinder.registerBinding("entry.costCenterOrgUnit", com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo.class, this.kdtEntry, "costCenterOrgUnit.text");
		dataBinder.registerBinding("entry.isAdjust", boolean.class, this.kdtEntry, "isAdjust.text");
		dataBinder.registerBinding("entry.adjustNum", String.class, this.kdtEntry, "adjustNum.text");
		dataBinder.registerBinding("priceInfo.id", com.kingdee.bos.util.BOSUuid.class, this.kdPriceInfo, "id.text");
		dataBinder.registerBinding("priceInfo", com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryInfo.class, this.kdPriceInfo, "userObject");
		dataBinder.registerBinding("priceInfo.material.name", String.class, this.kdPriceInfo, "material.name.text");
		dataBinder.registerBinding("priceInfo.assistProperty", com.kingdee.eas.basedata.master.material.AsstAttrValueInfo.class, this.kdPriceInfo, "assistProperty.text");
		dataBinder.registerBinding("priceInfo.unit", com.kingdee.eas.basedata.assistant.MeasureUnitInfo.class, this.kdPriceInfo, "unit.text");
		dataBinder.registerBinding("priceInfo.price", java.math.BigDecimal.class, this.kdPriceInfo, "price.text");
		dataBinder.registerBinding("priceInfo.discountType", com.kingdee.util.enums.Enum.class, this.kdPriceInfo, "discountType.text");
		dataBinder.registerBinding("priceInfo.discount", java.math.BigDecimal.class, this.kdPriceInfo, "discount.text");
		dataBinder.registerBinding("priceInfo.realPrice", java.math.BigDecimal.class, this.kdPriceInfo, "realPrice.text");
		dataBinder.registerBinding("priceInfo.taxRate", java.math.BigDecimal.class, this.kdPriceInfo, "taxRate.text");
		dataBinder.registerBinding("priceInfo.taxPrice", java.math.BigDecimal.class, this.kdPriceInfo, "taxPrice.text");
		dataBinder.registerBinding("priceInfo.realTaxPrice", java.math.BigDecimal.class, this.kdPriceInfo, "realTaxPrice.text");
		dataBinder.registerBinding("priceInfo.material", com.kingdee.eas.basedata.master.material.MaterialInfo.class, this.kdPriceInfo, "material.id.text");
		dataBinder.registerBinding("priceInfo.material.model", String.class, this.kdPriceInfo, "material.model.text");
		dataBinder.registerBinding("priceInfo.materialReqEntryID", String.class, this.kdPriceInfo, "entryID.text");
		dataBinder.registerBinding("bizType", com.kingdee.eas.basedata.scm.common.BizTypeInfo.class, this.prmtBizType, "data");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "text");
		dataBinder.registerBinding("supplier", com.kingdee.eas.basedata.master.cssp.SupplierInfo.class, this.prmtHeadSupplier, "data");
		dataBinder.registerBinding("supplyStoreOrgUnit", com.kingdee.eas.basedata.org.StorageOrgUnitInfo.class, this.prmtSupplyStorageOrgUnit, "data");
		dataBinder.registerBinding("purchaseType", com.kingdee.eas.scm.common.PurchaseTypeEnum.class, this.purchaseType, "selectedItem");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.scm.im.inv.app.MaterialReqBillEditUIHandler";
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
        this.kDDPCreatDate.requestFocusInWindow();
    }


	
	protected boolean isAgentAddNew() {
		return "ADDNEW".equals(getOprtState());
	}
	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;
	        if(isUseAgent()) {
			  	if (isAgentAddNew()) {
					if (! (dataObject instanceof com.kingdee.bos.framework.agent.IObjectValueAgent)) {
						ov = com.kingdee.eas.scm.im.inv.MaterialReqBillAgent.copyOvAsNewAgent(dataObject);
					} else if (((com.kingdee.bos.framework.agent.IObjectValueAgent)dataObject).getAgentState() != com.kingdee.bos.framework.agent.AgentState.NEW) {
						((com.kingdee.eas.scm.im.inv.MaterialReqBillAgent)ov).recursiveSetAgentState(com.kingdee.bos.framework.agent.AgentState.NEW);
			  		}
			  	} else {
		        	if (! (dataObject instanceof com.kingdee.bos.framework.agent.IObjectValueAgent)) {
						ov = com.kingdee.eas.scm.im.inv.MaterialReqBillAgent.copyOvToAgent(dataObject);
		        	}
	        	}
	        }        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.scm.im.inv.MaterialReqBillInfo)ov;
    }
			protected com.kingdee.eas.basedata.org.OrgType getMainBizOrgType() {
			return com.kingdee.eas.basedata.org.OrgType.getEnum("Storage");
		}


    /**
     * output loadFields method
     */
    public void loadFields()
    {
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
	 * ????????§µ??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );
		getValidateHelper().registerBindProperty("isReversed", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isInitBill", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isBackflush", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("modifier", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("modificationTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditor", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("baseStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("transactionType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("storageOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("adminOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("sourceBillType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("totalAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("totalStandardCost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("totalActualCost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("costCenterOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.productID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.material", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.productID.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.material.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.material.model", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.unit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.qty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.warehouse", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.location", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.unitStandardCost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.standardCost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.stocker", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.baseUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.orderNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.assistProperty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.assistUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.assistQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.reverseQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.baseQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.exp", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.unitActualCost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.actualCost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.mfg", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.lot", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.costObject", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.costObject.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.costObjectSuite", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.costObjectSuite.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.costItem", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.costItem.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.classGroup", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.person", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.productLineWP", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.saleOrderNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.issueQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.baseIssueQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.pickingDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.demandDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.productLine", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.faCardQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.coreBillNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.coreBillType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.coreBillEntrySe", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.subWrittenOffQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.subWrittenOffBaseQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.subUnWriteOffQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.subUnWriteOffBaseQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.processMaterial.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.processMaterial", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.issueAdminOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.issuePerson", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.operationNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.operation", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.operation.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.workCenter", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.workCenter.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.isAdmeasure", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.isReWork", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.trackNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.project", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.scWrittenOffAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.scUnWrittenOffAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.customer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.supplier", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.isPresent", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.supplyWarehouse", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.supplyLocation", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.settlePrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.picker", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.adminOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.costCenterOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.isAdjust", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry.adjustNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.material.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.assistProperty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.unit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.price", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.discountType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.discount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.realPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.taxRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.taxPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.realTaxPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.material", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.material.model", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("priceInfo.materialReqEntryID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("supplier", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("supplyStoreOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("purchaseType", ValidateHelper.ON_SAVE);    		
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
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
		String selectorAll = System.getProperty("selector.all");
		if(StringUtils.isEmpty(selectorAll)){
			selectorAll = "true";
		}
        sic.add(new SelectorItemInfo("isReversed"));
        sic.add(new SelectorItemInfo("isInitBill"));
        sic.add(new SelectorItemInfo("isBackflush"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("creator.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("creator.id"));
        	sic.add(new SelectorItemInfo("creator.number"));
        	sic.add(new SelectorItemInfo("creator.name"));
		}
        sic.add(new SelectorItemInfo("createTime"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("modifier.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("modifier.id"));
        	sic.add(new SelectorItemInfo("modifier.number"));
        	sic.add(new SelectorItemInfo("modifier.name"));
		}
        sic.add(new SelectorItemInfo("modificationTime"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("bizDate"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("auditor.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("auditor.id"));
        	sic.add(new SelectorItemInfo("auditor.number"));
        	sic.add(new SelectorItemInfo("auditor.name"));
		}
        sic.add(new SelectorItemInfo("auditTime"));
        sic.add(new SelectorItemInfo("baseStatus"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("transactionType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("transactionType.id"));
        	sic.add(new SelectorItemInfo("transactionType.number"));
        	sic.add(new SelectorItemInfo("transactionType.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("storageOrgUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("storageOrgUnit.id"));
        	sic.add(new SelectorItemInfo("storageOrgUnit.number"));
        	sic.add(new SelectorItemInfo("storageOrgUnit.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("adminOrgUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("adminOrgUnit.id"));
        	sic.add(new SelectorItemInfo("adminOrgUnit.number"));
        	sic.add(new SelectorItemInfo("adminOrgUnit.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("sourceBillType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("sourceBillType.id"));
        	sic.add(new SelectorItemInfo("sourceBillType.number"));
        	sic.add(new SelectorItemInfo("sourceBillType.name"));
		}
        sic.add(new SelectorItemInfo("totalAmount"));
        sic.add(new SelectorItemInfo("totalStandardCost"));
        sic.add(new SelectorItemInfo("totalActualCost"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("costCenterOrgUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("costCenterOrgUnit.id"));
        	sic.add(new SelectorItemInfo("costCenterOrgUnit.number"));
        	sic.add(new SelectorItemInfo("costCenterOrgUnit.name"));
		}
    	sic.add(new SelectorItemInfo("entry.id"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("entry.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.productID.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.productID.id"));
			sic.add(new SelectorItemInfo("entry.productID.name"));
        	sic.add(new SelectorItemInfo("entry.productID.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.material.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.material.id"));
			sic.add(new SelectorItemInfo("entry.material.name"));
        	sic.add(new SelectorItemInfo("entry.material.number"));
		}
    	sic.add(new SelectorItemInfo("entry.material.model"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.unit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.unit.id"));
			sic.add(new SelectorItemInfo("entry.unit.name"));
        	sic.add(new SelectorItemInfo("entry.unit.number"));
		}
    	sic.add(new SelectorItemInfo("entry.qty"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.warehouse.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.warehouse.id"));
			sic.add(new SelectorItemInfo("entry.warehouse.name"));
        	sic.add(new SelectorItemInfo("entry.warehouse.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.location.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.location.id"));
			sic.add(new SelectorItemInfo("entry.location.name"));
        	sic.add(new SelectorItemInfo("entry.location.number"));
		}
    	sic.add(new SelectorItemInfo("entry.unitStandardCost"));
    	sic.add(new SelectorItemInfo("entry.standardCost"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.stocker.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.stocker.id"));
			sic.add(new SelectorItemInfo("entry.stocker.name"));
        	sic.add(new SelectorItemInfo("entry.stocker.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.baseUnit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.baseUnit.id"));
			sic.add(new SelectorItemInfo("entry.baseUnit.name"));
        	sic.add(new SelectorItemInfo("entry.baseUnit.number"));
		}
    	sic.add(new SelectorItemInfo("entry.orderNumber"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.assistProperty.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.assistProperty.id"));
			sic.add(new SelectorItemInfo("entry.assistProperty.name"));
        	sic.add(new SelectorItemInfo("entry.assistProperty.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.assistUnit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.assistUnit.id"));
			sic.add(new SelectorItemInfo("entry.assistUnit.name"));
        	sic.add(new SelectorItemInfo("entry.assistUnit.number"));
		}
    	sic.add(new SelectorItemInfo("entry.assistQty"));
    	sic.add(new SelectorItemInfo("entry.reverseQty"));
    	sic.add(new SelectorItemInfo("entry.baseQty"));
    	sic.add(new SelectorItemInfo("entry.exp"));
    	sic.add(new SelectorItemInfo("entry.unitActualCost"));
    	sic.add(new SelectorItemInfo("entry.actualCost"));
    	sic.add(new SelectorItemInfo("entry.remark"));
    	sic.add(new SelectorItemInfo("entry.mfg"));
    	sic.add(new SelectorItemInfo("entry.lot"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.costObject.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.costObject.id"));
			sic.add(new SelectorItemInfo("entry.costObject.name"));
        	sic.add(new SelectorItemInfo("entry.costObject.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.costObjectSuite.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.costObjectSuite.id"));
			sic.add(new SelectorItemInfo("entry.costObjectSuite.name"));
        	sic.add(new SelectorItemInfo("entry.costObjectSuite.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.costItem.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.costItem.id"));
			sic.add(new SelectorItemInfo("entry.costItem.name"));
        	sic.add(new SelectorItemInfo("entry.costItem.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.classGroup.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.classGroup.id"));
			sic.add(new SelectorItemInfo("entry.classGroup.name"));
        	sic.add(new SelectorItemInfo("entry.classGroup.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.person.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.person.id"));
			sic.add(new SelectorItemInfo("entry.person.name"));
        	sic.add(new SelectorItemInfo("entry.person.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.productLineWP.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.productLineWP.id"));
			sic.add(new SelectorItemInfo("entry.productLineWP.name"));
        	sic.add(new SelectorItemInfo("entry.productLineWP.number"));
		}
    	sic.add(new SelectorItemInfo("entry.saleOrderNum"));
    	sic.add(new SelectorItemInfo("entry.issueQty"));
    	sic.add(new SelectorItemInfo("entry.baseIssueQty"));
    	sic.add(new SelectorItemInfo("entry.pickingDate"));
    	sic.add(new SelectorItemInfo("entry.demandDate"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.productLine.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.productLine.id"));
			sic.add(new SelectorItemInfo("entry.productLine.name"));
        	sic.add(new SelectorItemInfo("entry.productLine.number"));
		}
    	sic.add(new SelectorItemInfo("entry.faCardQty"));
    	sic.add(new SelectorItemInfo("entry.coreBillNumber"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.coreBillType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.coreBillType.id"));
			sic.add(new SelectorItemInfo("entry.coreBillType.name"));
        	sic.add(new SelectorItemInfo("entry.coreBillType.number"));
		}
    	sic.add(new SelectorItemInfo("entry.coreBillEntrySe"));
    	sic.add(new SelectorItemInfo("entry.subWrittenOffQty"));
    	sic.add(new SelectorItemInfo("entry.subWrittenOffBaseQty"));
    	sic.add(new SelectorItemInfo("entry.subUnWriteOffQty"));
    	sic.add(new SelectorItemInfo("entry.subUnWriteOffBaseQty"));
    	sic.add(new SelectorItemInfo("entry.processMaterial.name"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.processMaterial.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.processMaterial.id"));
        	sic.add(new SelectorItemInfo("entry.processMaterial.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.issueAdminOrgUnit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.issueAdminOrgUnit.id"));
			sic.add(new SelectorItemInfo("entry.issueAdminOrgUnit.name"));
        	sic.add(new SelectorItemInfo("entry.issueAdminOrgUnit.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.issuePerson.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.issuePerson.id"));
			sic.add(new SelectorItemInfo("entry.issuePerson.name"));
        	sic.add(new SelectorItemInfo("entry.issuePerson.number"));
		}
    	sic.add(new SelectorItemInfo("entry.operationNo"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.operation.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.operation.id"));
			sic.add(new SelectorItemInfo("entry.operation.name"));
        	sic.add(new SelectorItemInfo("entry.operation.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.workCenter.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.workCenter.id"));
			sic.add(new SelectorItemInfo("entry.workCenter.name"));
        	sic.add(new SelectorItemInfo("entry.workCenter.number"));
		}
    	sic.add(new SelectorItemInfo("entry.isAdmeasure"));
    	sic.add(new SelectorItemInfo("entry.isReWork"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.trackNumber.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.trackNumber.id"));
			sic.add(new SelectorItemInfo("entry.trackNumber.name"));
        	sic.add(new SelectorItemInfo("entry.trackNumber.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.project.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.project.id"));
			sic.add(new SelectorItemInfo("entry.project.name"));
        	sic.add(new SelectorItemInfo("entry.project.number"));
		}
    	sic.add(new SelectorItemInfo("entry.scWrittenOffAmount"));
    	sic.add(new SelectorItemInfo("entry.scUnWrittenOffAmount"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.customer.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.customer.id"));
			sic.add(new SelectorItemInfo("entry.customer.name"));
        	sic.add(new SelectorItemInfo("entry.customer.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.supplier.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.supplier.id"));
			sic.add(new SelectorItemInfo("entry.supplier.name"));
        	sic.add(new SelectorItemInfo("entry.supplier.number"));
		}
    	sic.add(new SelectorItemInfo("entry.isPresent"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.supplyWarehouse.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.supplyWarehouse.id"));
			sic.add(new SelectorItemInfo("entry.supplyWarehouse.name"));
        	sic.add(new SelectorItemInfo("entry.supplyWarehouse.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.supplyLocation.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.supplyLocation.id"));
			sic.add(new SelectorItemInfo("entry.supplyLocation.name"));
        	sic.add(new SelectorItemInfo("entry.supplyLocation.number"));
		}
    	sic.add(new SelectorItemInfo("entry.settlePrice"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.picker.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.picker.id"));
			sic.add(new SelectorItemInfo("entry.picker.name"));
        	sic.add(new SelectorItemInfo("entry.picker.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.adminOrgUnit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.adminOrgUnit.id"));
			sic.add(new SelectorItemInfo("entry.adminOrgUnit.name"));
        	sic.add(new SelectorItemInfo("entry.adminOrgUnit.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entry.costCenterOrgUnit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entry.costCenterOrgUnit.id"));
			sic.add(new SelectorItemInfo("entry.costCenterOrgUnit.name"));
        	sic.add(new SelectorItemInfo("entry.costCenterOrgUnit.number"));
		}
    	sic.add(new SelectorItemInfo("entry.isAdjust"));
    	sic.add(new SelectorItemInfo("entry.adjustNum"));
    	sic.add(new SelectorItemInfo("priceInfo.id"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("priceInfo.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("priceInfo.material.name"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("priceInfo.assistProperty.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("priceInfo.assistProperty.id"));
			sic.add(new SelectorItemInfo("priceInfo.assistProperty.name"));
        	sic.add(new SelectorItemInfo("priceInfo.assistProperty.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("priceInfo.unit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("priceInfo.unit.id"));
			sic.add(new SelectorItemInfo("priceInfo.unit.name"));
        	sic.add(new SelectorItemInfo("priceInfo.unit.number"));
		}
    	sic.add(new SelectorItemInfo("priceInfo.price"));
    	sic.add(new SelectorItemInfo("priceInfo.discountType"));
    	sic.add(new SelectorItemInfo("priceInfo.discount"));
    	sic.add(new SelectorItemInfo("priceInfo.realPrice"));
    	sic.add(new SelectorItemInfo("priceInfo.taxRate"));
    	sic.add(new SelectorItemInfo("priceInfo.taxPrice"));
    	sic.add(new SelectorItemInfo("priceInfo.realTaxPrice"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("priceInfo.material.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("priceInfo.material.id"));
        	sic.add(new SelectorItemInfo("priceInfo.material.number"));
		}
    	sic.add(new SelectorItemInfo("priceInfo.material.model"));
    	sic.add(new SelectorItemInfo("priceInfo.materialReqEntryID"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("bizType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("bizType.id"));
        	sic.add(new SelectorItemInfo("bizType.number"));
        	sic.add(new SelectorItemInfo("bizType.name"));
		}
        sic.add(new SelectorItemInfo("description"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("supplier.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("supplier.id"));
        	sic.add(new SelectorItemInfo("supplier.number"));
        	sic.add(new SelectorItemInfo("supplier.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("supplyStoreOrgUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("supplyStoreOrgUnit.id"));
        	sic.add(new SelectorItemInfo("supplyStoreOrgUnit.number"));
        	sic.add(new SelectorItemInfo("supplyStoreOrgUnit.name"));
		}
        sic.add(new SelectorItemInfo("purchaseType"));
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
     * output actionAddNew_actionPerformed method
     */
    public void actionAddNew_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAddNew_actionPerformed(e);
    }
    	

    /**
     * output actionEdit_actionPerformed method
     */
    public void actionEdit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionEdit_actionPerformed(e);
    }
    	

    /**
     * output actionMultiapprove_actionPerformed method
     */
    public void actionMultiapprove_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionMultiapprove_actionPerformed(e);
    }
    	

    /**
     * output actionNextPerson_actionPerformed method
     */
    public void actionNextPerson_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionNextPerson_actionPerformed(e);
    }
    	

    /**
     * output actionAudit_actionPerformed method
     */
    public void actionAudit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAudit_actionPerformed(e);
    }
    	

    /**
     * output actionUnAudit_actionPerformed method
     */
    public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionUnAudit_actionPerformed(e);
    }
    	

    /**
     * output actionOffSet_actionPerformed method
     */
    public void actionOffSet_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionQuickAddLine_actionPerformed method
     */
    public void actionQuickAddLine_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCalculateLot_actionPerformed method
     */
    public void actionCalculateLot_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCostObjectSuite_actionPerformed method
     */
    public void actionCostObjectSuite_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCalculateDynQty_actionPerformed method
     */
    public void actionCalculateDynQty_actionPerformed(ActionEvent e) throws Exception
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
	public RequestContext prepareActionAddNew(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionAddNew(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAddNew() {
    	return false;
    }
	public RequestContext prepareActionEdit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionEdit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionEdit() {
    	return false;
    }
	public RequestContext prepareActionMultiapprove(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionMultiapprove(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionMultiapprove() {
    	return false;
    }
	public RequestContext prepareActionNextPerson(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionNextPerson(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionNextPerson() {
    	return false;
    }
	public RequestContext prepareActionAudit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionAudit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAudit() {
    	return false;
    }
	public RequestContext prepareActionUnAudit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionUnAudit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionUnAudit() {
    	return false;
    }
	public RequestContext prepareActionOffSet(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionOffSet() {
    	return false;
    }
	public RequestContext prepareActionQuickAddLine(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionQuickAddLine() {
    	return false;
    }
	public RequestContext prepareActionCalculateLot(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCalculateLot() {
    	return false;
    }
	public RequestContext prepareActionCostObjectSuite(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCostObjectSuite() {
    	return false;
    }
	public RequestContext prepareActionCalculateDynQty(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCalculateDynQty() {
    	return false;
    }

    /**
     * output ActionOffSet class
     */     
    protected class ActionOffSet extends ItemAction {     
    
        public ActionOffSet()
        {
            this(null);
        }

        public ActionOffSet(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            this.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt O"));
            _tempStr = resHelper.getString("ActionOffSet.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionOffSet.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionOffSet.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialReqBillEditUI.this, "ActionOffSet", "actionOffSet_actionPerformed", e);
        }
    }

    /**
     * output ActionQuickAddLine class
     */     
    protected class ActionQuickAddLine extends ItemAction {     
    
        public ActionQuickAddLine()
        {
            this(null);
        }

        public ActionQuickAddLine(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl Q"));
            _tempStr = resHelper.getString("ActionQuickAddLine.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionQuickAddLine.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionQuickAddLine.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialReqBillEditUI.this, "ActionQuickAddLine", "actionQuickAddLine_actionPerformed", e);
        }
    }

    /**
     * output ActionCalculateLot class
     */     
    protected class ActionCalculateLot extends ItemAction {     
    
        public ActionCalculateLot()
        {
            this(null);
        }

        public ActionCalculateLot(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionCalculateLot.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCalculateLot.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCalculateLot.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialReqBillEditUI.this, "ActionCalculateLot", "actionCalculateLot_actionPerformed", e);
        }
    }

    /**
     * output ActionCostObjectSuite class
     */     
    protected class ActionCostObjectSuite extends ItemAction {     
    
        public ActionCostObjectSuite()
        {
            this(null);
        }

        public ActionCostObjectSuite(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionCostObjectSuite.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCostObjectSuite.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCostObjectSuite.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialReqBillEditUI.this, "ActionCostObjectSuite", "actionCostObjectSuite_actionPerformed", e);
        }
    }

    /**
     * output ActionCalculateDynQty class
     */     
    protected class ActionCalculateDynQty extends ItemAction {     
    
        public ActionCalculateDynQty()
        {
            this(null);
        }

        public ActionCalculateDynQty(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionCalculateDynQty.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCalculateDynQty.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCalculateDynQty.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialReqBillEditUI.this, "ActionCalculateDynQty", "actionCalculateDynQty_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.scm.im.inv.client", "MaterialReqBillEditUI");
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
        return com.kingdee.eas.scm.im.inv.client.MaterialReqBillEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.scm.im.inv.MaterialReqBillFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.scm.im.inv.MaterialReqBillInfo objectValue = new com.kingdee.eas.scm.im.inv.MaterialReqBillInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }



    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kdtEntry;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}