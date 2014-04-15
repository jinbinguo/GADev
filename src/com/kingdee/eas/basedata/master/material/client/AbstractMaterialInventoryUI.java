/**
 * output package name
 */
package com.kingdee.eas.basedata.master.material.client;

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
public abstract class AbstractMaterialInventoryUI extends com.kingdee.eas.framework.client.EditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractMaterialInventoryUI.class);
    protected com.kingdee.bos.ctrl.swing.KDTextField txtStorageOrgName;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCreator;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboStatus;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtOrgUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtFreezeOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtQtySafety;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtQtyMin;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtQtyMax;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtQtyMinPackage;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboAbcType;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboIssuePriorityMode;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtUnit;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboPeriodValidUnit;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboAheadUnit;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDSpinner spDaysBottom;
    protected com.kingdee.bos.ctrl.swing.KDSpinner kDSPeriodValid;
    protected com.kingdee.bos.ctrl.swing.KDSpinner kDSInWarehsAhead;
    protected com.kingdee.bos.ctrl.swing.KDSpinner kDSPrepWarnAhead;
    protected com.kingdee.bos.ctrl.swing.KDSpinner spOutWarehsAhead;
    protected com.kingdee.bos.ctrl.swing.KDSpinner spInvDaysTop;
    protected com.kingdee.bos.ctrl.swing.KDSpinner spDaysTurnover;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnApprove;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnUnApprove;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtInvPlanner;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane kDScrollPane1;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contId;
    protected com.kingdee.bos.ctrl.swing.KDSeparator kDSeparator5;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsControl;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsNegative;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsBatchNo;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsLotNumber;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsBarcode;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsSequenceNo;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsCompages;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsPeriodValid;
    protected com.kingdee.bos.ctrl.swing.KDSeparator kDSeparator6;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAbcType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contQtySafety;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contQtyMax;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contQtyMin;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contPeriodValid;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contPeriodValidUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contInWarehsAhead;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOutWarehsAhead;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contPrepWarnAhead;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAheadUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDaysBottom;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDaysTop;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDaysTurnover;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contIssuePriorityMode;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contQtyMinPackage;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer3;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer4;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer5;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer6;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer7;
    protected com.kingdee.bos.ctrl.swing.KDSeparator kDSeparator8;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboPlanningMode;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtReBookQty;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtConsumeSpeed;
    protected com.kingdee.bos.ctrl.swing.KDComboBox comboBatchPolicy;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtFixationBatchQty;
    protected com.kingdee.bos.ctrl.swing.KDSpinner spinnerPurchAheadDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDefaultWare;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtDefaultWare;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDaysPlanTurnover;
    protected com.kingdee.bos.ctrl.swing.KDNumberTextField txtDaysPlanTurnover;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer8;
    protected com.kingdee.bos.ctrl.swing.KDLabel kDLabel1;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtCheapRate;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsCheck;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer9;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtQualityOrg;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contFreezeOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDSeparator kDSeparator7;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conToolRate;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtToolRate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer10;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtDefaultlocation;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCostQty;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCostPrice;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtMaterialLoc;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtMaterialLoc_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtCostQty;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtCostPrice;
    protected com.kingdee.eas.basedata.master.material.MaterialInventoryInfo editData = null;
    protected ActionApprove actionApprove = null;
    protected ActionUnApprove actionUnApprove = null;
    /**
     * output class constructor
     */
    public AbstractMaterialInventoryUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractMaterialInventoryUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionPageSetup
        String _tempStr = null;
        actionPageSetup.setEnabled(true);
        actionPageSetup.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionPageSetup.SHORT_DESCRIPTION");
        actionPageSetup.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPageSetup.LONG_DESCRIPTION");
        actionPageSetup.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPageSetup.NAME");
        actionPageSetup.putValue(ItemAction.NAME, _tempStr);
         this.actionPageSetup.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPageSetup.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPageSetup.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionExitCurrent
        actionExitCurrent.setEnabled(true);
        actionExitCurrent.setDaemonRun(false);

        actionExitCurrent.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl W"));
        _tempStr = resHelper.getString("ActionExitCurrent.SHORT_DESCRIPTION");
        actionExitCurrent.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionExitCurrent.LONG_DESCRIPTION");
        actionExitCurrent.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionExitCurrent.NAME");
        actionExitCurrent.putValue(ItemAction.NAME, _tempStr);
         this.actionExitCurrent.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionExitCurrent.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionExitCurrent.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionHelp
        actionHelp.setEnabled(true);
        actionHelp.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionHelp.SHORT_DESCRIPTION");
        actionHelp.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionHelp.LONG_DESCRIPTION");
        actionHelp.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionHelp.NAME");
        actionHelp.putValue(ItemAction.NAME, _tempStr);
         this.actionHelp.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionHelp.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionHelp.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionAbout
        actionAbout.setEnabled(true);
        actionAbout.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionAbout.SHORT_DESCRIPTION");
        actionAbout.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAbout.LONG_DESCRIPTION");
        actionAbout.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAbout.NAME");
        actionAbout.putValue(ItemAction.NAME, _tempStr);
         this.actionAbout.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionAbout.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionAbout.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionOnLoad
        actionOnLoad.setEnabled(true);
        actionOnLoad.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionOnLoad.SHORT_DESCRIPTION");
        actionOnLoad.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionOnLoad.LONG_DESCRIPTION");
        actionOnLoad.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionOnLoad.NAME");
        actionOnLoad.putValue(ItemAction.NAME, _tempStr);
         this.actionOnLoad.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionOnLoad.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionOnLoad.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionSendMessage
        actionSendMessage.setEnabled(true);
        actionSendMessage.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionSendMessage.SHORT_DESCRIPTION");
        actionSendMessage.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSendMessage.LONG_DESCRIPTION");
        actionSendMessage.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSendMessage.NAME");
        actionSendMessage.putValue(ItemAction.NAME, _tempStr);
         this.actionSendMessage.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionSave
        actionSave.setEnabled(true);
        actionSave.setDaemonRun(false);

        actionSave.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl S"));
        _tempStr = resHelper.getString("ActionSave.SHORT_DESCRIPTION");
        actionSave.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSave.LONG_DESCRIPTION");
        actionSave.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSave.NAME");
        actionSave.putValue(ItemAction.NAME, _tempStr);
         this.actionSave.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSave.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSave.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionSubmit
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        actionSubmit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
        actionSubmit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
        actionSubmit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.NAME");
        actionSubmit.putValue(ItemAction.NAME, _tempStr);
        this.actionSubmit.setExtendProperty("canForewarn", "true");
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
        //actionCancel
        actionCancel.setEnabled(true);
        actionCancel.setDaemonRun(false);

        actionCancel.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl -"));
        _tempStr = resHelper.getString("ActionCancel.SHORT_DESCRIPTION");
        actionCancel.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionCancel.LONG_DESCRIPTION");
        actionCancel.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionCancel.NAME");
        actionCancel.putValue(ItemAction.NAME, _tempStr);
         this.actionCancel.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionCancel.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionCancel.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionCancelCancel
        actionCancelCancel.setEnabled(true);
        actionCancelCancel.setDaemonRun(false);

        actionCancelCancel.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl +"));
        _tempStr = resHelper.getString("ActionCancelCancel.SHORT_DESCRIPTION");
        actionCancelCancel.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionCancelCancel.LONG_DESCRIPTION");
        actionCancelCancel.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionCancelCancel.NAME");
        actionCancelCancel.putValue(ItemAction.NAME, _tempStr);
         this.actionCancelCancel.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionCancelCancel.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionCancelCancel.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionFirst
        actionFirst.setEnabled(false);
        actionFirst.setDaemonRun(false);

        actionFirst.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl <"));
        _tempStr = resHelper.getString("ActionFirst.SHORT_DESCRIPTION");
        actionFirst.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionFirst.LONG_DESCRIPTION");
        actionFirst.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionFirst.NAME");
        actionFirst.putValue(ItemAction.NAME, _tempStr);
         this.actionFirst.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionFirst.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionFirst.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPre
        actionPre.setEnabled(true);
        actionPre.setDaemonRun(false);

        actionPre.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl <"));
        _tempStr = resHelper.getString("ActionPre.SHORT_DESCRIPTION");
        actionPre.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPre.LONG_DESCRIPTION");
        actionPre.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPre.NAME");
        actionPre.putValue(ItemAction.NAME, _tempStr);
         this.actionPre.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPre.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPre.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionNext
        actionNext.setEnabled(true);
        actionNext.setDaemonRun(false);

        actionNext.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl >"));
        _tempStr = resHelper.getString("ActionNext.SHORT_DESCRIPTION");
        actionNext.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionNext.LONG_DESCRIPTION");
        actionNext.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionNext.NAME");
        actionNext.putValue(ItemAction.NAME, _tempStr);
         this.actionNext.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionNext.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionNext.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionLast
        actionLast.setEnabled(true);
        actionLast.setDaemonRun(false);

        actionLast.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl >"));
        _tempStr = resHelper.getString("ActionLast.SHORT_DESCRIPTION");
        actionLast.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionLast.LONG_DESCRIPTION");
        actionLast.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionLast.NAME");
        actionLast.putValue(ItemAction.NAME, _tempStr);
         this.actionLast.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionLast.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionLast.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrint
        actionPrint.setEnabled(false);
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
        actionPrintPreview.setEnabled(false);
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
        //actionCopy
        actionCopy.setEnabled(true);
        actionCopy.setDaemonRun(false);

        actionCopy.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift C"));
        _tempStr = resHelper.getString("ActionCopy.SHORT_DESCRIPTION");
        actionCopy.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionCopy.LONG_DESCRIPTION");
        actionCopy.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionCopy.NAME");
        actionCopy.putValue(ItemAction.NAME, _tempStr);
         this.actionCopy.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionCopy.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionCopy.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
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
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionEdit
        actionEdit.setEnabled(true);
        actionEdit.setDaemonRun(false);

        actionEdit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
        _tempStr = resHelper.getString("ActionEdit.SHORT_DESCRIPTION");
        actionEdit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionEdit.LONG_DESCRIPTION");
        actionEdit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionEdit.NAME");
        actionEdit.putValue(ItemAction.NAME, _tempStr);
         this.actionEdit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRemove
        actionRemove.setEnabled(false);
        actionRemove.setDaemonRun(false);

        actionRemove.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
        _tempStr = resHelper.getString("ActionRemove.SHORT_DESCRIPTION");
        actionRemove.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionRemove.LONG_DESCRIPTION");
        actionRemove.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionRemove.NAME");
        actionRemove.putValue(ItemAction.NAME, _tempStr);
         this.actionRemove.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAttachment
        actionAttachment.setEnabled(true);
        actionAttachment.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionAttachment.SHORT_DESCRIPTION");
        actionAttachment.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAttachment.LONG_DESCRIPTION");
        actionAttachment.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAttachment.NAME");
        actionAttachment.putValue(ItemAction.NAME, _tempStr);
         this.actionAttachment.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionSubmitOption
        actionSubmitOption.setEnabled(true);
        actionSubmitOption.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionSubmitOption.SHORT_DESCRIPTION");
        actionSubmitOption.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmitOption.LONG_DESCRIPTION");
        actionSubmitOption.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmitOption.NAME");
        actionSubmitOption.putValue(ItemAction.NAME, _tempStr);
         this.actionSubmitOption.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionApprove
        this.actionApprove = new ActionApprove(this);
        getActionManager().registerAction("actionApprove", actionApprove);
         this.actionApprove.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionUnApprove
        this.actionUnApprove = new ActionUnApprove(this);
        getActionManager().registerAction("actionUnApprove", actionUnApprove);
         this.actionUnApprove.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.txtStorageOrgName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtCreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtLastUpdateUser = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.comboStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtFreezeOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtQtySafety = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtQtyMin = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtQtyMax = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtQtyMinPackage = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.comboAbcType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.comboIssuePriorityMode = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.comboPeriodValidUnit = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.comboAheadUnit = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.pkLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pkCreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.spDaysBottom = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.kDSPeriodValid = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.kDSInWarehsAhead = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.kDSPrepWarnAhead = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.spOutWarehsAhead = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.spInvDaysTop = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.spDaysTurnover = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.btnApprove = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnUnApprove = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.prmtInvPlanner = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDScrollPane1 = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.kDPanel1 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.contOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contId = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDSeparator5 = new com.kingdee.bos.ctrl.swing.KDSeparator();
        this.chkIsControl = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkIsNegative = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkIsBatchNo = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkIsLotNumber = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkIsBarcode = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkIsSequenceNo = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkIsCompages = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.chkIsPeriodValid = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.kDSeparator6 = new com.kingdee.bos.ctrl.swing.KDSeparator();
        this.contAbcType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contQtySafety = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contQtyMax = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contQtyMin = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contPeriodValid = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contPeriodValidUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contInWarehsAhead = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contOutWarehsAhead = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contPrepWarnAhead = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAheadUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDaysBottom = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDaysTop = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDaysTurnover = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contIssuePriorityMode = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contQtyMinPackage = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer3 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer4 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer5 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer6 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer7 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDSeparator8 = new com.kingdee.bos.ctrl.swing.KDSeparator();
        this.comboPlanningMode = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtReBookQty = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtConsumeSpeed = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.comboBatchPolicy = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtFixationBatchQty = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.spinnerPurchAheadDate = new com.kingdee.bos.ctrl.swing.KDSpinner();
        this.contDefaultWare = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtDefaultWare = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.contDaysPlanTurnover = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtDaysPlanTurnover = new com.kingdee.bos.ctrl.swing.KDNumberTextField();
        this.kDLabelContainer8 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabel1 = new com.kingdee.bos.ctrl.swing.KDLabel();
        this.txtCheapRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.chkIsCheck = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.kDLabelContainer9 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtQualityOrg = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.contLastUpdateUser = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contFreezeOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDSeparator7 = new com.kingdee.bos.ctrl.swing.KDSeparator();
        this.conToolRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtToolRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.kDLabelContainer10 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtDefaultlocation = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.contCostQty = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCostPrice = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kdtMaterialLoc = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.txtCostQty = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtCostPrice = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtStorageOrgName.setName("txtStorageOrgName");
        this.prmtCreator.setName("prmtCreator");
        this.prmtLastUpdateUser.setName("prmtLastUpdateUser");
        this.comboStatus.setName("comboStatus");
        this.prmtOrgUnit.setName("prmtOrgUnit");
        this.prmtFreezeOrgUnit.setName("prmtFreezeOrgUnit");
        this.txtQtySafety.setName("txtQtySafety");
        this.txtQtyMin.setName("txtQtyMin");
        this.txtQtyMax.setName("txtQtyMax");
        this.txtQtyMinPackage.setName("txtQtyMinPackage");
        this.comboAbcType.setName("comboAbcType");
        this.comboIssuePriorityMode.setName("comboIssuePriorityMode");
        this.prmtUnit.setName("prmtUnit");
        this.comboPeriodValidUnit.setName("comboPeriodValidUnit");
        this.comboAheadUnit.setName("comboAheadUnit");
        this.pkLastUpdateTime.setName("pkLastUpdateTime");
        this.pkCreateTime.setName("pkCreateTime");
        this.spDaysBottom.setName("spDaysBottom");
        this.kDSPeriodValid.setName("kDSPeriodValid");
        this.kDSInWarehsAhead.setName("kDSInWarehsAhead");
        this.kDSPrepWarnAhead.setName("kDSPrepWarnAhead");
        this.spOutWarehsAhead.setName("spOutWarehsAhead");
        this.spInvDaysTop.setName("spInvDaysTop");
        this.spDaysTurnover.setName("spDaysTurnover");
        this.btnApprove.setName("btnApprove");
        this.btnUnApprove.setName("btnUnApprove");
        this.prmtInvPlanner.setName("prmtInvPlanner");
        this.kDScrollPane1.setName("kDScrollPane1");
        this.kDPanel1.setName("kDPanel1");
        this.contOrgUnit.setName("contOrgUnit");
        this.contId.setName("contId");
        this.kDSeparator5.setName("kDSeparator5");
        this.chkIsControl.setName("chkIsControl");
        this.chkIsNegative.setName("chkIsNegative");
        this.chkIsBatchNo.setName("chkIsBatchNo");
        this.chkIsLotNumber.setName("chkIsLotNumber");
        this.chkIsBarcode.setName("chkIsBarcode");
        this.chkIsSequenceNo.setName("chkIsSequenceNo");
        this.chkIsCompages.setName("chkIsCompages");
        this.chkIsPeriodValid.setName("chkIsPeriodValid");
        this.kDSeparator6.setName("kDSeparator6");
        this.contAbcType.setName("contAbcType");
        this.contUnit.setName("contUnit");
        this.contQtySafety.setName("contQtySafety");
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.contQtyMax.setName("contQtyMax");
        this.contQtyMin.setName("contQtyMin");
        this.contPeriodValid.setName("contPeriodValid");
        this.contPeriodValidUnit.setName("contPeriodValidUnit");
        this.contInWarehsAhead.setName("contInWarehsAhead");
        this.contOutWarehsAhead.setName("contOutWarehsAhead");
        this.contPrepWarnAhead.setName("contPrepWarnAhead");
        this.contAheadUnit.setName("contAheadUnit");
        this.contDaysBottom.setName("contDaysBottom");
        this.contDaysTop.setName("contDaysTop");
        this.contDaysTurnover.setName("contDaysTurnover");
        this.contIssuePriorityMode.setName("contIssuePriorityMode");
        this.contQtyMinPackage.setName("contQtyMinPackage");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.kDLabelContainer3.setName("kDLabelContainer3");
        this.kDLabelContainer4.setName("kDLabelContainer4");
        this.kDLabelContainer5.setName("kDLabelContainer5");
        this.kDLabelContainer6.setName("kDLabelContainer6");
        this.kDLabelContainer7.setName("kDLabelContainer7");
        this.kDSeparator8.setName("kDSeparator8");
        this.comboPlanningMode.setName("comboPlanningMode");
        this.txtReBookQty.setName("txtReBookQty");
        this.txtConsumeSpeed.setName("txtConsumeSpeed");
        this.comboBatchPolicy.setName("comboBatchPolicy");
        this.txtFixationBatchQty.setName("txtFixationBatchQty");
        this.spinnerPurchAheadDate.setName("spinnerPurchAheadDate");
        this.contDefaultWare.setName("contDefaultWare");
        this.prmtDefaultWare.setName("prmtDefaultWare");
        this.contDaysPlanTurnover.setName("contDaysPlanTurnover");
        this.txtDaysPlanTurnover.setName("txtDaysPlanTurnover");
        this.kDLabelContainer8.setName("kDLabelContainer8");
        this.kDLabel1.setName("kDLabel1");
        this.txtCheapRate.setName("txtCheapRate");
        this.chkIsCheck.setName("chkIsCheck");
        this.kDLabelContainer9.setName("kDLabelContainer9");
        this.prmtQualityOrg.setName("prmtQualityOrg");
        this.contLastUpdateUser.setName("contLastUpdateUser");
        this.contFreezeOrgUnit.setName("contFreezeOrgUnit");
        this.contStatus.setName("contStatus");
        this.contCreator.setName("contCreator");
        this.contCreateTime.setName("contCreateTime");
        this.contLastUpdateTime.setName("contLastUpdateTime");
        this.kDSeparator7.setName("kDSeparator7");
        this.conToolRate.setName("conToolRate");
        this.txtToolRate.setName("txtToolRate");
        this.kDLabelContainer10.setName("kDLabelContainer10");
        this.prmtDefaultlocation.setName("prmtDefaultlocation");
        this.contCostQty.setName("contCostQty");
        this.contCostPrice.setName("contCostPrice");
        this.kdtMaterialLoc.setName("kdtMaterialLoc");
        this.txtCostQty.setName("txtCostQty");
        this.txtCostPrice.setName("txtCostPrice");
        // CoreUI
        this.btnPageSetup.setAction((IItemAction)ActionProxyFactory.getProxy(actionPageSetup, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnPageSetup.setText(resHelper.getString("btnPageSetup.text"));		
        this.btnPageSetup.setToolTipText(resHelper.getString("btnPageSetup.toolTipText"));		
        this.btnPageSetup.setVisible(false);		
        this.menuFile.setText(resHelper.getString("menuFile.text"));		
        this.menuFile.setToolTipText(resHelper.getString("menuFile.toolTipText"));		
        this.menuFile.setMnemonic(70);
        this.menuItemPageSetup.setAction((IItemAction)ActionProxyFactory.getProxy(actionPageSetup, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemPageSetup.setText(resHelper.getString("menuItemPageSetup.text"));		
        this.menuItemPageSetup.setVisible(false);		
        this.menuItemPageSetup.setToolTipText(resHelper.getString("menuItemPageSetup.toolTipText"));		
        this.menuItemPageSetup.setMnemonic(84);
        this.menuItemExitCurrent.setAction((IItemAction)ActionProxyFactory.getProxy(actionExitCurrent, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemExitCurrent.setText(resHelper.getString("menuItemExitCurrent.text"));		
        this.menuItemExitCurrent.setToolTipText(resHelper.getString("menuItemExitCurrent.toolTipText"));		
        this.menuItemExitCurrent.setMnemonic(88);		
        this.menuTool.setText(resHelper.getString("menuTool.text"));		
        this.menuTool.setToolTipText(resHelper.getString("menuTool.toolTipText"));		
        this.menuTool.setMnemonic(84);
        this.menuItemSendMessage.setAction((IItemAction)ActionProxyFactory.getProxy(actionSendMessage, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemSendMessage.setText(resHelper.getString("menuItemSendMessage.text"));		
        this.menuItemSendMessage.setToolTipText(resHelper.getString("menuItemSendMessage.toolTipText"));		
        this.menuItemSendMessage.setMnemonic(77);		
        this.menuHelp.setText(resHelper.getString("menuHelp.text"));		
        this.menuHelp.setMnemonic(72);
        this.menuItemHelp.setAction((IItemAction)ActionProxyFactory.getProxy(actionHelp, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemHelp.setText(resHelper.getString("menuItemHelp.text"));		
        this.menuItemHelp.setToolTipText(resHelper.getString("menuItemHelp.toolTipText"));		
        this.menuItemHelp.setMnemonic(72);
        this.menuItemAbout.setAction((IItemAction)ActionProxyFactory.getProxy(actionAbout, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemAbout.setText(resHelper.getString("menuItemAbout.text"));		
        this.menuItemAbout.setToolTipText(resHelper.getString("menuItemAbout.toolTipText"));		
        this.menuItemAbout.setMnemonic(65);
        this.btnAddNew.setAction((IItemAction)ActionProxyFactory.getProxy(actionAddNew, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAddNew.setText(resHelper.getString("btnAddNew.text"));		
        this.btnAddNew.setToolTipText(resHelper.getString("btnAddNew.toolTipText"));
        this.btnEdit.setAction((IItemAction)ActionProxyFactory.getProxy(actionEdit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnEdit.setText(resHelper.getString("btnEdit.text"));		
        this.btnEdit.setToolTipText(resHelper.getString("btnEdit.toolTipText"));
        this.btnSave.setAction((IItemAction)ActionProxyFactory.getProxy(actionSave, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnSave.setText(resHelper.getString("btnSave.text"));		
        this.btnSave.setToolTipText(resHelper.getString("btnSave.toolTipText"));
        this.btnSubmit.setAction((IItemAction)ActionProxyFactory.getProxy(actionSubmit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnSubmit.setText(resHelper.getString("btnSubmit.text"));		
        this.btnSubmit.setToolTipText(resHelper.getString("btnSubmit.toolTipText"));
        this.btnCopy.setAction((IItemAction)ActionProxyFactory.getProxy(actionCopy, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCopy.setText(resHelper.getString("btnCopy.text"));		
        this.btnCopy.setToolTipText(resHelper.getString("btnCopy.toolTipText"));
        this.btnRemove.setAction((IItemAction)ActionProxyFactory.getProxy(actionRemove, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnRemove.setText(resHelper.getString("btnRemove.text"));		
        this.btnRemove.setToolTipText(resHelper.getString("btnRemove.toolTipText"));
        this.btnCancelCancel.setAction((IItemAction)ActionProxyFactory.getProxy(actionCancelCancel, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCancelCancel.setText(resHelper.getString("btnCancelCancel.text"));		
        this.btnCancelCancel.setToolTipText(resHelper.getString("btnCancelCancel.toolTipText"));
        this.btnCancel.setAction((IItemAction)ActionProxyFactory.getProxy(actionCancel, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCancel.setText(resHelper.getString("btnCancel.text"));		
        this.btnCancel.setToolTipText(resHelper.getString("btnCancel.toolTipText"));
        this.btnFirst.setAction((IItemAction)ActionProxyFactory.getProxy(actionFirst, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnFirst.setText(resHelper.getString("btnFirst.text"));		
        this.btnFirst.setToolTipText(resHelper.getString("btnFirst.toolTipText"));
        this.btnPre.setAction((IItemAction)ActionProxyFactory.getProxy(actionPre, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnPre.setText(resHelper.getString("btnPre.text"));		
        this.btnPre.setToolTipText(resHelper.getString("btnPre.toolTipText"));
        this.btnNext.setAction((IItemAction)ActionProxyFactory.getProxy(actionNext, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnNext.setText(resHelper.getString("btnNext.text"));		
        this.btnNext.setToolTipText(resHelper.getString("btnNext.toolTipText"));
        this.btnLast.setAction((IItemAction)ActionProxyFactory.getProxy(actionLast, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnLast.setText(resHelper.getString("btnLast.text"));		
        this.btnLast.setToolTipText(resHelper.getString("btnLast.toolTipText"));
        this.btnPrint.setAction((IItemAction)ActionProxyFactory.getProxy(actionPrint, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnPrint.setText(resHelper.getString("btnPrint.text"));		
        this.btnPrint.setToolTipText(resHelper.getString("btnPrint.toolTipText"));
        this.btnPrintPreview.setAction((IItemAction)ActionProxyFactory.getProxy(actionPrintPreview, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnPrintPreview.setText(resHelper.getString("btnPrintPreview.text"));		
        this.btnPrintPreview.setToolTipText(resHelper.getString("btnPrintPreview.toolTipText"));
        this.menuItemAddNew.setAction((IItemAction)ActionProxyFactory.getProxy(actionAddNew, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemAddNew.setText(resHelper.getString("menuItemAddNew.text"));		
        this.menuItemAddNew.setMnemonic(78);
        this.menuItemSave.setAction((IItemAction)ActionProxyFactory.getProxy(actionSave, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemSave.setText(resHelper.getString("menuItemSave.text"));
        this.menuItemSubmit.setAction((IItemAction)ActionProxyFactory.getProxy(actionSubmit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemSubmit.setText(resHelper.getString("menuItemSubmit.text"));		
        this.menuItemSubmit.setToolTipText(resHelper.getString("menuItemSubmit.toolTipText"));		
        this.menuItemSubmit.setMnemonic(83);		
        this.kDSeparator2.setVisible(false);
        this.menuItemPrint.setAction((IItemAction)ActionProxyFactory.getProxy(actionPrint, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemPrint.setText(resHelper.getString("menuItemPrint.text"));		
        this.menuItemPrint.setVisible(false);		
        this.menuItemPrint.setMnemonic(80);
        this.menuItemPrintPreview.setAction((IItemAction)ActionProxyFactory.getProxy(actionPrintPreview, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemPrintPreview.setText(resHelper.getString("menuItemPrintPreview.text"));		
        this.menuItemPrintPreview.setVisible(false);		
        this.menuItemPrintPreview.setMnemonic(86);		
        this.menuEdit.setText(resHelper.getString("menuEdit.text"));		
        this.menuEdit.setToolTipText(resHelper.getString("menuEdit.toolTipText"));		
        this.menuEdit.setMnemonic(69);
        this.menuItemCopy.setAction((IItemAction)ActionProxyFactory.getProxy(actionCopy, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemCopy.setText(resHelper.getString("menuItemCopy.text"));		
        this.menuItemCopy.setToolTipText(resHelper.getString("menuItemCopy.toolTipText"));		
        this.menuItemCopy.setMnemonic(67);		
        this.kDSeparator4.setVisible(false);		
        this.kDSeparator4.setEnabled(false);		
        this.menuView.setText(resHelper.getString("menuView.text"));		
        this.menuView.setToolTipText(resHelper.getString("menuView.toolTipText"));		
        this.menuView.setMnemonic(86);
        this.menuItemFirst.setAction((IItemAction)ActionProxyFactory.getProxy(actionFirst, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemFirst.setText(resHelper.getString("menuItemFirst.text"));		
        this.menuItemFirst.setMnemonic(70);
        this.menuItemPre.setAction((IItemAction)ActionProxyFactory.getProxy(actionPre, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemPre.setText(resHelper.getString("menuItemPre.text"));		
        this.menuItemPre.setMnemonic(80);
        this.menuItemNext.setAction((IItemAction)ActionProxyFactory.getProxy(actionNext, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemNext.setText(resHelper.getString("menuItemNext.text"));		
        this.menuItemNext.setMnemonic(78);
        this.menuItemLast.setAction((IItemAction)ActionProxyFactory.getProxy(actionLast, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemLast.setText(resHelper.getString("menuItemLast.text"));		
        this.menuItemLast.setMnemonic(76);
        this.btnAttachment.setAction((IItemAction)ActionProxyFactory.getProxy(actionAttachment, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAttachment.setText(resHelper.getString("btnAttachment.text"));		
        this.btnAttachment.setToolTipText(resHelper.getString("btnAttachment.toolTipText"));
        this.menuItemEdit.setAction((IItemAction)ActionProxyFactory.getProxy(actionEdit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemEdit.setText(resHelper.getString("menuItemEdit.text"));		
        this.menuItemEdit.setToolTipText(resHelper.getString("menuItemEdit.toolTipText"));		
        this.menuItemEdit.setMnemonic(69);
        this.menuItemRemove.setAction((IItemAction)ActionProxyFactory.getProxy(actionRemove, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemRemove.setText(resHelper.getString("menuItemRemove.text"));		
        this.menuItemRemove.setToolTipText(resHelper.getString("menuItemRemove.toolTipText"));		
        this.menuItemRemove.setMnemonic(82);		
        this.rMenuItemSubmit.setText(resHelper.getString("rMenuItemSubmit.text"));		
        this.rMenuItemSubmit.setToolTipText(resHelper.getString("rMenuItemSubmit.toolTipText"));		
        this.rMenuItemSubmit.setSelected(true);		
        this.rMenuItemSubmit.setVisible(false);		
        this.rMenuItemSubmit.setEnabled(false);		
        this.rMenuItemSubmitAndAddNew.setText(resHelper.getString("rMenuItemSubmitAndAddNew.text"));		
        this.rMenuItemSubmitAndAddNew.setToolTipText(resHelper.getString("rMenuItemSubmitAndAddNew.toolTipText"));		
        this.rMenuItemSubmitAndAddNew.setVisible(false);		
        this.rMenuItemSubmitAndAddNew.setEnabled(false);		
        this.rMenuItemSubmitAndPrint.setText(resHelper.getString("rMenuItemSubmitAndPrint.text"));		
        this.rMenuItemSubmitAndPrint.setToolTipText(resHelper.getString("rMenuItemSubmitAndPrint.toolTipText"));		
        this.rMenuItemSubmitAndPrint.setVisible(false);		
        this.rMenuItemSubmitAndPrint.setEnabled(false);
        this.menuSubmitOption.setAction((IItemAction)ActionProxyFactory.getProxy(actionSubmitOption, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuSubmitOption.setText(resHelper.getString("menuSubmitOption.text"));		
        this.menuSubmitOption.setMnemonic(67);		
        this.chkMenuItemSubmitAndAddNew.setText(resHelper.getString("chkMenuItemSubmitAndAddNew.text"));		
        this.chkMenuItemSubmitAndAddNew.setMnemonic(69);		
        this.chkMenuItemSubmitAndPrint.setText(resHelper.getString("chkMenuItemSubmitAndPrint.text"));		
        this.chkMenuItemSubmitAndPrint.setMnemonic(80);
        this.MenuItemAttachment.setAction((IItemAction)ActionProxyFactory.getProxy(actionAttachment, new Class[] { IItemAction.class }, getServiceContext()));		
        this.MenuItemAttachment.setText(resHelper.getString("MenuItemAttachment.text"));		
        this.MenuItemAttachment.setToolTipText(resHelper.getString("MenuItemAttachment.toolTipText"));		
        this.MenuItemAttachment.setMnemonic(65);		
        this.menuBiz.setText(resHelper.getString("menuBiz.text"));		
        this.menuBiz.setMnemonic(79);
        this.menuItemCancelCancel.setAction((IItemAction)ActionProxyFactory.getProxy(actionCancelCancel, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemCancelCancel.setText(resHelper.getString("menuItemCancelCancel.text"));		
        this.menuItemCancelCancel.setMnemonic(83);
        this.menuItemCancel.setAction((IItemAction)ActionProxyFactory.getProxy(actionCancel, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemCancel.setText(resHelper.getString("menuItemCancel.text"));		
        this.menuItemCancel.setMnemonic(67);		
        this.separatorFW1.setOrientation(1);		
        this.separatorFW2.setOrientation(1);		
        this.separatorFW3.setOrientation(1);
        // txtStorageOrgName		
        this.txtStorageOrgName.setEnabled(false);
        // prmtCreator		
        this.prmtCreator.setEnabled(false);		
        this.prmtCreator.setDisplayFormat("$name$");		
        this.prmtCreator.setEditFormat("$number$");		
        this.prmtCreator.setCommitFormat("$number$");		
        this.prmtCreator.setEditable(true);
        // prmtLastUpdateUser		
        this.prmtLastUpdateUser.setEnabled(false);		
        this.prmtLastUpdateUser.setDisplayFormat("$name$");		
        this.prmtLastUpdateUser.setEditFormat("$number$");		
        this.prmtLastUpdateUser.setCommitFormat("$number$");		
        this.prmtLastUpdateUser.setEditable(true);
        // comboStatus		
        this.comboStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.basedata.master.material.UsedStatusEnum").toArray());		
        this.comboStatus.setEnabled(false);
        // prmtOrgUnit		
        this.prmtOrgUnit.setDisplayFormat("$number$");		
        this.prmtOrgUnit.setEditFormat("$number$");		
        this.prmtOrgUnit.setCommitFormat("$number$");		
        this.prmtOrgUnit.setEditable(true);		
        this.prmtOrgUnit.setRequired(true);		
        this.prmtOrgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.StorageOrgUnitQuery");
        // prmtFreezeOrgUnit		
        this.prmtFreezeOrgUnit.setEnabled(false);		
        this.prmtFreezeOrgUnit.setDisplayFormat("$name$");		
        this.prmtFreezeOrgUnit.setEditFormat("$number$");		
        this.prmtFreezeOrgUnit.setCommitFormat("$number$");		
        this.prmtFreezeOrgUnit.setEditable(true);
        // txtQtySafety		
        this.txtQtySafety.setDataType(1);
        // txtQtyMin		
        this.txtQtyMin.setDataType(1);
        // txtQtyMax		
        this.txtQtyMax.setDataType(1);
        // txtQtyMinPackage		
        this.txtQtyMinPackage.setDataType(1);
        // comboAbcType		
        this.comboAbcType.addItems(EnumUtils.getEnumList("com.kingdee.eas.basedata.master.material.ABCEnum").toArray());
        // comboIssuePriorityMode		
        this.comboIssuePriorityMode.addItems(EnumUtils.getEnumList("com.kingdee.eas.basedata.master.material.IssuePriorityEnum").toArray());
        // prmtUnit		
        this.prmtUnit.setDisplayFormat("$name$");		
        this.prmtUnit.setEditFormat("$number$");		
        this.prmtUnit.setCommitFormat("$number$");		
        this.prmtUnit.setEditable(true);		
        this.prmtUnit.setRequired(true);		
        this.prmtUnit.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7MultiMeasureUnitQuery");
        // comboPeriodValidUnit		
        this.comboPeriodValidUnit.addItems(EnumUtils.getEnumList("com.kingdee.eas.basedata.master.material.TimeUnitEnum").toArray());
        // comboAheadUnit		
        this.comboAheadUnit.addItems(EnumUtils.getEnumList("com.kingdee.eas.basedata.master.material.TimeUnitEnum").toArray());
        // pkLastUpdateTime		
        this.pkLastUpdateTime.setEnabled(false);
        // pkCreateTime		
        this.pkCreateTime.setEnabled(false);
        // spDaysBottom
        // kDSPeriodValid
        // kDSInWarehsAhead
        // kDSPrepWarnAhead
        // spOutWarehsAhead
        // spInvDaysTop
        // spDaysTurnover
        // btnApprove
        this.btnApprove.setAction((IItemAction)ActionProxyFactory.getProxy(actionApprove, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnApprove.setText(resHelper.getString("btnApprove.text"));		
        this.btnApprove.setToolTipText(resHelper.getString("btnApprove.toolTipText"));
        // btnUnApprove
        this.btnUnApprove.setAction((IItemAction)ActionProxyFactory.getProxy(actionUnApprove, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnUnApprove.setText(resHelper.getString("btnUnApprove.text"));		
        this.btnUnApprove.setToolTipText(resHelper.getString("btnUnApprove.toolTipText"));
        // prmtInvPlanner		
        this.prmtInvPlanner.setDisplayFormat("$name$");		
        this.prmtInvPlanner.setEditFormat("$number$");		
        this.prmtInvPlanner.setCommitFormat("$number$");		
        this.prmtInvPlanner.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
        // kDScrollPane1		
        this.kDScrollPane1.setAutoscrolls(true);
        // kDPanel1		
        this.kDPanel1.setPreferredSize(new Dimension(550,460));
        // contOrgUnit		
        this.contOrgUnit.setBoundLabelText(resHelper.getString("contOrgUnit.boundLabelText"));		
        this.contOrgUnit.setBoundLabelLength(100);		
        this.contOrgUnit.setBoundLabelUnderline(true);
        // contId		
        this.contId.setBoundLabelText(resHelper.getString("contId.boundLabelText"));		
        this.contId.setBoundLabelLength(100);		
        this.contId.setBoundLabelUnderline(true);
        // kDSeparator5
        // chkIsControl		
        this.chkIsControl.setText(resHelper.getString("chkIsControl.text"));
        // chkIsNegative		
        this.chkIsNegative.setText(resHelper.getString("chkIsNegative.text"));
        // chkIsBatchNo		
        this.chkIsBatchNo.setText(resHelper.getString("chkIsBatchNo.text"));
        // chkIsLotNumber		
        this.chkIsLotNumber.setText(resHelper.getString("chkIsLotNumber.text"));
        // chkIsBarcode		
        this.chkIsBarcode.setText(resHelper.getString("chkIsBarcode.text"));
        // chkIsSequenceNo		
        this.chkIsSequenceNo.setText(resHelper.getString("chkIsSequenceNo.text"));
        // chkIsCompages		
        this.chkIsCompages.setText(resHelper.getString("chkIsCompages.text"));
        // chkIsPeriodValid		
        this.chkIsPeriodValid.setText(resHelper.getString("chkIsPeriodValid.text"));
        // kDSeparator6
        // contAbcType		
        this.contAbcType.setBoundLabelText(resHelper.getString("contAbcType.boundLabelText"));		
        this.contAbcType.setBoundLabelLength(100);		
        this.contAbcType.setBoundLabelUnderline(true);
        // contUnit		
        this.contUnit.setBoundLabelText(resHelper.getString("contUnit.boundLabelText"));		
        this.contUnit.setBoundLabelLength(100);		
        this.contUnit.setBoundLabelUnderline(true);
        // contQtySafety		
        this.contQtySafety.setBoundLabelText(resHelper.getString("contQtySafety.boundLabelText"));		
        this.contQtySafety.setBoundLabelLength(100);		
        this.contQtySafety.setBoundLabelUnderline(true);
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));		
        this.kDLabelContainer1.setBoundLabelLength(100);		
        this.kDLabelContainer1.setBoundLabelUnderline(true);
        // contQtyMax		
        this.contQtyMax.setBoundLabelText(resHelper.getString("contQtyMax.boundLabelText"));		
        this.contQtyMax.setBoundLabelLength(100);		
        this.contQtyMax.setBoundLabelUnderline(true);
        // contQtyMin		
        this.contQtyMin.setBoundLabelText(resHelper.getString("contQtyMin.boundLabelText"));		
        this.contQtyMin.setBoundLabelLength(100);		
        this.contQtyMin.setBoundLabelUnderline(true);
        // contPeriodValid		
        this.contPeriodValid.setBoundLabelText(resHelper.getString("contPeriodValid.boundLabelText"));		
        this.contPeriodValid.setBoundLabelLength(100);		
        this.contPeriodValid.setBoundLabelUnderline(true);
        // contPeriodValidUnit		
        this.contPeriodValidUnit.setBoundLabelText(resHelper.getString("contPeriodValidUnit.boundLabelText"));		
        this.contPeriodValidUnit.setBoundLabelLength(100);		
        this.contPeriodValidUnit.setBoundLabelUnderline(true);
        // contInWarehsAhead		
        this.contInWarehsAhead.setBoundLabelText(resHelper.getString("contInWarehsAhead.boundLabelText"));		
        this.contInWarehsAhead.setBoundLabelLength(100);		
        this.contInWarehsAhead.setBoundLabelUnderline(true);
        // contOutWarehsAhead		
        this.contOutWarehsAhead.setBoundLabelText(resHelper.getString("contOutWarehsAhead.boundLabelText"));		
        this.contOutWarehsAhead.setBoundLabelLength(100);		
        this.contOutWarehsAhead.setBoundLabelUnderline(true);
        // contPrepWarnAhead		
        this.contPrepWarnAhead.setBoundLabelText(resHelper.getString("contPrepWarnAhead.boundLabelText"));		
        this.contPrepWarnAhead.setBoundLabelLength(100);		
        this.contPrepWarnAhead.setBoundLabelUnderline(true);
        // contAheadUnit		
        this.contAheadUnit.setBoundLabelText(resHelper.getString("contAheadUnit.boundLabelText"));		
        this.contAheadUnit.setBoundLabelLength(100);		
        this.contAheadUnit.setBoundLabelUnderline(true);
        // contDaysBottom		
        this.contDaysBottom.setBoundLabelText(resHelper.getString("contDaysBottom.boundLabelText"));		
        this.contDaysBottom.setBoundLabelLength(100);		
        this.contDaysBottom.setBoundLabelUnderline(true);
        // contDaysTop		
        this.contDaysTop.setBoundLabelText(resHelper.getString("contDaysTop.boundLabelText"));		
        this.contDaysTop.setBoundLabelLength(100);		
        this.contDaysTop.setBoundLabelUnderline(true);
        // contDaysTurnover		
        this.contDaysTurnover.setBoundLabelText(resHelper.getString("contDaysTurnover.boundLabelText"));		
        this.contDaysTurnover.setBoundLabelLength(100);		
        this.contDaysTurnover.setBoundLabelUnderline(true);
        // contIssuePriorityMode		
        this.contIssuePriorityMode.setBoundLabelText(resHelper.getString("contIssuePriorityMode.boundLabelText"));		
        this.contIssuePriorityMode.setBoundLabelLength(100);		
        this.contIssuePriorityMode.setBoundLabelUnderline(true);
        // contQtyMinPackage		
        this.contQtyMinPackage.setBoundLabelText(resHelper.getString("contQtyMinPackage.boundLabelText"));		
        this.contQtyMinPackage.setBoundLabelLength(100);		
        this.contQtyMinPackage.setBoundLabelUnderline(true);
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));		
        this.kDLabelContainer2.setBoundLabelLength(100);		
        this.kDLabelContainer2.setBoundLabelUnderline(true);		
        this.kDLabelContainer2.setVisible(false);
        // kDLabelContainer3		
        this.kDLabelContainer3.setBoundLabelText(resHelper.getString("kDLabelContainer3.boundLabelText"));		
        this.kDLabelContainer3.setBoundLabelLength(100);		
        this.kDLabelContainer3.setBoundLabelUnderline(true);		
        this.kDLabelContainer3.setVisible(false);
        // kDLabelContainer4		
        this.kDLabelContainer4.setBoundLabelText(resHelper.getString("kDLabelContainer4.boundLabelText"));		
        this.kDLabelContainer4.setBoundLabelLength(100);		
        this.kDLabelContainer4.setBoundLabelUnderline(true);		
        this.kDLabelContainer4.setVisible(false);
        // kDLabelContainer5		
        this.kDLabelContainer5.setBoundLabelText(resHelper.getString("kDLabelContainer5.boundLabelText"));		
        this.kDLabelContainer5.setBoundLabelLength(100);		
        this.kDLabelContainer5.setBoundLabelUnderline(true);		
        this.kDLabelContainer5.setVisible(false);
        // kDLabelContainer6		
        this.kDLabelContainer6.setBoundLabelText(resHelper.getString("kDLabelContainer6.boundLabelText"));		
        this.kDLabelContainer6.setBoundLabelLength(100);		
        this.kDLabelContainer6.setBoundLabelUnderline(true);		
        this.kDLabelContainer6.setVisible(false);
        // kDLabelContainer7		
        this.kDLabelContainer7.setBoundLabelText(resHelper.getString("kDLabelContainer7.boundLabelText"));		
        this.kDLabelContainer7.setBoundLabelLength(100);		
        this.kDLabelContainer7.setBoundLabelUnderline(true);		
        this.kDLabelContainer7.setVisible(false);
        // kDSeparator8
        // comboPlanningMode		
        this.comboPlanningMode.addItems(EnumUtils.getEnumList("com.kingdee.eas.basedata.master.material.PlanningModeEnum").toArray());		
        this.comboPlanningMode.setSelectedIndex(0);
        // txtReBookQty		
        this.txtReBookQty.setDataType(1);
        // txtConsumeSpeed		
        this.txtConsumeSpeed.setDataType(1);
        // comboBatchPolicy		
        this.comboBatchPolicy.addItems(EnumUtils.getEnumList("com.kingdee.eas.basedata.master.material.BatchPolicyEnum").toArray());		
        this.comboBatchPolicy.setSelectedIndex(1);
        // txtFixationBatchQty		
        this.txtFixationBatchQty.setDataType(1);
        // spinnerPurchAheadDate
        // contDefaultWare		
        this.contDefaultWare.setBoundLabelText(resHelper.getString("contDefaultWare.boundLabelText"));		
        this.contDefaultWare.setBoundLabelLength(100);		
        this.contDefaultWare.setBoundLabelUnderline(true);
        // prmtDefaultWare		
        this.prmtDefaultWare.setDisplayFormat("$name$");		
        this.prmtDefaultWare.setEditFormat("$number$");		
        this.prmtDefaultWare.setCommitFormat("$number$");		
        this.prmtDefaultWare.setQueryInfo("com.kingdee.eas.basedata.scm.im.inv.app.F7WarehouseQuery");
        // contDaysPlanTurnover		
        this.contDaysPlanTurnover.setBoundLabelText(resHelper.getString("contDaysPlanTurnover.boundLabelText"));		
        this.contDaysPlanTurnover.setBoundLabelLength(100);		
        this.contDaysPlanTurnover.setBoundLabelUnderline(true);
        // txtDaysPlanTurnover
        // kDLabelContainer8		
        this.kDLabelContainer8.setBoundLabelText(resHelper.getString("kDLabelContainer8.boundLabelText"));		
        this.kDLabelContainer8.setBoundLabelLength(100);		
        this.kDLabelContainer8.setBoundLabelUnderline(true);
        // kDLabel1		
        this.kDLabel1.setText(resHelper.getString("kDLabel1.text"));
        // txtCheapRate		
        this.txtCheapRate.setDataType(1);
        // chkIsCheck		
        this.chkIsCheck.setText(resHelper.getString("chkIsCheck.text"));
        // kDLabelContainer9		
        this.kDLabelContainer9.setBoundLabelText(resHelper.getString("kDLabelContainer9.boundLabelText"));		
        this.kDLabelContainer9.setBoundLabelLength(100);		
        this.kDLabelContainer9.setBoundLabelUnderline(true);
        // prmtQualityOrg		
        this.prmtQualityOrg.setDisplayFormat("$name$");		
        this.prmtQualityOrg.setEditFormat("$number$");		
        this.prmtQualityOrg.setCommitFormat("$number$");		
        this.prmtQualityOrg.setQueryInfo("com.kingdee.eas.basedata.org.app.QualityItemQuery");
        // contLastUpdateUser		
        this.contLastUpdateUser.setBoundLabelText(resHelper.getString("contLastUpdateUser.boundLabelText"));		
        this.contLastUpdateUser.setBoundLabelLength(100);		
        this.contLastUpdateUser.setBoundLabelUnderline(true);
        // contFreezeOrgUnit		
        this.contFreezeOrgUnit.setBoundLabelText(resHelper.getString("contFreezeOrgUnit.boundLabelText"));		
        this.contFreezeOrgUnit.setBoundLabelLength(100);		
        this.contFreezeOrgUnit.setBoundLabelUnderline(true);
        // contStatus		
        this.contStatus.setBoundLabelText(resHelper.getString("contStatus.boundLabelText"));		
        this.contStatus.setBoundLabelLength(100);		
        this.contStatus.setBoundLabelUnderline(true);
        // contCreator		
        this.contCreator.setBoundLabelText(resHelper.getString("contCreator.boundLabelText"));		
        this.contCreator.setBoundLabelLength(100);		
        this.contCreator.setBoundLabelUnderline(true);
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);
        // contLastUpdateTime		
        this.contLastUpdateTime.setBoundLabelText(resHelper.getString("contLastUpdateTime.boundLabelText"));		
        this.contLastUpdateTime.setBoundLabelLength(100);		
        this.contLastUpdateTime.setBoundLabelUnderline(true);
        // kDSeparator7
        // conToolRate		
        this.conToolRate.setBoundLabelText(resHelper.getString("conToolRate.boundLabelText"));		
        this.conToolRate.setBoundLabelLength(100);		
        this.conToolRate.setBoundLabelUnderline(true);
        // txtToolRate		
        this.txtToolRate.setDataType(1);
        // kDLabelContainer10		
        this.kDLabelContainer10.setBoundLabelText(resHelper.getString("kDLabelContainer10.boundLabelText"));		
        this.kDLabelContainer10.setBoundLabelLength(100);		
        this.kDLabelContainer10.setBoundLabelUnderline(true);
        // prmtDefaultlocation		
        this.prmtDefaultlocation.setQueryInfo("com.kingdee.eas.basedata.scm.im.inv.app.LocationQuery");		
        this.prmtDefaultlocation.setCommitFormat("$number$");		
        this.prmtDefaultlocation.setEditFormat("$number$");		
        this.prmtDefaultlocation.setDisplayFormat("$name$");
        // contCostQty		
        this.contCostQty.setBoundLabelText(resHelper.getString("contCostQty.boundLabelText"));		
        this.contCostQty.setBoundLabelLength(100);		
        this.contCostQty.setBoundLabelUnderline(true);		
        this.contCostQty.setVisible(true);
        // contCostPrice		
        this.contCostPrice.setBoundLabelText(resHelper.getString("contCostPrice.boundLabelText"));		
        this.contCostPrice.setBoundLabelLength(100);		
        this.contCostPrice.setBoundLabelUnderline(true);		
        this.contCostPrice.setVisible(true);
        // kdtMaterialLoc
		String kdtMaterialLocStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol2\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"bmwLoc\" t:width=\"90\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"loc\" t:width=\"90\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol2\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{bmwLoc}</t:Cell><t:Cell>$Resource{loc}</t:Cell><t:Cell>$Resource{seq}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtMaterialLoc.setFormatXml(resHelper.translateString("kdtMaterialLoc",kdtMaterialLocStrXML));

                this.kdtMaterialLoc.putBindContents("editData",new String[] {"bmwLoc","loc","seq"});


        this.kdtMaterialLoc.checkParsed();
        KDComboBox kdtMaterialLoc_bmwLoc_ComboBox = new KDComboBox();
        kdtMaterialLoc_bmwLoc_ComboBox.setName("kdtMaterialLoc_bmwLoc_ComboBox");
        kdtMaterialLoc_bmwLoc_ComboBox.setVisible(true);
        kdtMaterialLoc_bmwLoc_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.ga.basedata.BMWLocEnum").toArray());
        KDTDefaultCellEditor kdtMaterialLoc_bmwLoc_CellEditor = new KDTDefaultCellEditor(kdtMaterialLoc_bmwLoc_ComboBox);
        this.kdtMaterialLoc.getColumn("bmwLoc").setEditor(kdtMaterialLoc_bmwLoc_CellEditor);
        KDTextField kdtMaterialLoc_loc_TextField = new KDTextField();
        kdtMaterialLoc_loc_TextField.setName("kdtMaterialLoc_loc_TextField");
        kdtMaterialLoc_loc_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtMaterialLoc_loc_CellEditor = new KDTDefaultCellEditor(kdtMaterialLoc_loc_TextField);
        this.kdtMaterialLoc.getColumn("loc").setEditor(kdtMaterialLoc_loc_CellEditor);
        // txtCostQty		
        this.txtCostQty.setHorizontalAlignment(2);		
        this.txtCostQty.setDataType(1);		
        this.txtCostQty.setSupportedEmpty(true);		
        this.txtCostQty.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtCostQty.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtCostQty.setPrecision(4);		
        this.txtCostQty.setRequired(false);
        // txtCostPrice		
        this.txtCostPrice.setHorizontalAlignment(2);		
        this.txtCostPrice.setDataType(1);		
        this.txtCostPrice.setSupportedEmpty(true);		
        this.txtCostPrice.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtCostPrice.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtCostPrice.setPrecision(4);		
        this.txtCostPrice.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {chkIsControl,txtQtySafety,txtQtyMin,txtQtyMax,spDaysBottom,spInvDaysTop,spDaysTurnover,chkIsNegative,chkIsBatchNo,chkIsSequenceNo,chkIsLotNumber,chkIsBarcode,txtQtyMinPackage,comboAbcType,chkIsCompages,comboIssuePriorityMode,prmtUnit,chkIsPeriodValid,kDSPeriodValid,comboPeriodValidUnit,kDSInWarehsAhead,spOutWarehsAhead,kDSPrepWarnAhead,comboAheadUnit,prmtInvPlanner,comboPlanningMode,txtReBookQty,txtConsumeSpeed,spinnerPurchAheadDate,comboBatchPolicy,txtFixationBatchQty,prmtDefaultWare,txtDaysPlanTurnover,txtCheapRate,prmtQualityOrg,chkIsCheck,txtToolRate,prmtDefaultlocation,comboStatus,prmtOrgUnit,prmtFreezeOrgUnit,prmtCreator,pkCreateTime,prmtLastUpdateUser,pkLastUpdateTime,txtCostPrice,txtCostQty,kdtMaterialLoc}));
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
        this.setBounds(new Rectangle(0, 0, 758, 460));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 758, 460));
        kDScrollPane1.setBounds(new Rectangle(0, 0, 749, 460));
        this.add(kDScrollPane1, new KDLayout.Constraints(0, 0, 749, 460, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDScrollPane1
        kDScrollPane1.getViewport().add(kDPanel1, null);
        //kDPanel1
        kDPanel1.setLayout(null);        contOrgUnit.setBounds(new Rectangle(8, 8, 270, 19));
        kDPanel1.add(contOrgUnit, null);
        contId.setBounds(new Rectangle(362, 8, 270, 19));
        kDPanel1.add(contId, null);
        kDSeparator5.setBounds(new Rectangle(8, 37, 620, 8));
        kDPanel1.add(kDSeparator5, null);
        chkIsControl.setBounds(new Rectangle(8, 47, 140, 19));
        kDPanel1.add(chkIsControl, null);
        chkIsNegative.setBounds(new Rectangle(245, 47, 140, 19));
        kDPanel1.add(chkIsNegative, null);
        chkIsBatchNo.setBounds(new Rectangle(495, 47, 140, 19));
        kDPanel1.add(chkIsBatchNo, null);
        chkIsLotNumber.setBounds(new Rectangle(8, 68, 140, 19));
        kDPanel1.add(chkIsLotNumber, null);
        chkIsBarcode.setBounds(new Rectangle(245, 68, 140, 19));
        kDPanel1.add(chkIsBarcode, null);
        chkIsSequenceNo.setBounds(new Rectangle(495, 68, 140, 19));
        kDPanel1.add(chkIsSequenceNo, null);
        chkIsCompages.setBounds(new Rectangle(8, 89, 140, 19));
        kDPanel1.add(chkIsCompages, null);
        chkIsPeriodValid.setBounds(new Rectangle(245, 89, 140, 19));
        kDPanel1.add(chkIsPeriodValid, null);
        kDSeparator6.setBounds(new Rectangle(8, 114, 620, 8));
        kDPanel1.add(kDSeparator6, null);
        contAbcType.setBounds(new Rectangle(8, 121, 209, 19));
        kDPanel1.add(contAbcType, null);
        contUnit.setBounds(new Rectangle(245, 121, 209, 19));
        kDPanel1.add(contUnit, null);
        contQtySafety.setBounds(new Rectangle(8, 144, 209, 19));
        kDPanel1.add(contQtySafety, null);
        kDLabelContainer1.setBounds(new Rectangle(245, 144, 209, 19));
        kDPanel1.add(kDLabelContainer1, null);
        contQtyMax.setBounds(new Rectangle(8, 167, 209, 19));
        kDPanel1.add(contQtyMax, null);
        contQtyMin.setBounds(new Rectangle(245, 167, 209, 19));
        kDPanel1.add(contQtyMin, null);
        contPeriodValid.setBounds(new Rectangle(8, 190, 209, 19));
        kDPanel1.add(contPeriodValid, null);
        contPeriodValidUnit.setBounds(new Rectangle(245, 190, 209, 19));
        kDPanel1.add(contPeriodValidUnit, null);
        contInWarehsAhead.setBounds(new Rectangle(8, 213, 209, 19));
        kDPanel1.add(contInWarehsAhead, null);
        contOutWarehsAhead.setBounds(new Rectangle(245, 213, 209, 19));
        kDPanel1.add(contOutWarehsAhead, null);
        contPrepWarnAhead.setBounds(new Rectangle(8, 236, 209, 19));
        kDPanel1.add(contPrepWarnAhead, null);
        contAheadUnit.setBounds(new Rectangle(245, 236, 209, 19));
        kDPanel1.add(contAheadUnit, null);
        contDaysBottom.setBounds(new Rectangle(8, 259, 209, 19));
        kDPanel1.add(contDaysBottom, null);
        contDaysTop.setBounds(new Rectangle(245, 259, 209, 19));
        kDPanel1.add(contDaysTop, null);
        contDaysTurnover.setBounds(new Rectangle(8, 282, 209, 19));
        kDPanel1.add(contDaysTurnover, null);
        contIssuePriorityMode.setBounds(new Rectangle(245, 282, 209, 19));
        kDPanel1.add(contIssuePriorityMode, null);
        contQtyMinPackage.setBounds(new Rectangle(8, 305, 209, 19));
        kDPanel1.add(contQtyMinPackage, null);
        kDLabelContainer2.setBounds(new Rectangle(676, 45, 270, 19));
        kDPanel1.add(kDLabelContainer2, null);
        kDLabelContainer3.setBounds(new Rectangle(679, 26, 270, 19));
        kDPanel1.add(kDLabelContainer3, null);
        kDLabelContainer4.setBounds(new Rectangle(677, 78, 203, 19));
        kDPanel1.add(kDLabelContainer4, null);
        kDLabelContainer5.setBounds(new Rectangle(678, 57, 270, 19));
        kDPanel1.add(kDLabelContainer5, null);
        kDLabelContainer6.setBounds(new Rectangle(677, 98, 270, 19));
        kDPanel1.add(kDLabelContainer6, null);
        kDLabelContainer7.setBounds(new Rectangle(678, 6, 270, 19));
        kDPanel1.add(kDLabelContainer7, null);
        kDSeparator8.setBounds(new Rectangle(0, 377, 620, 8));
        kDPanel1.add(kDSeparator8, null);
        contDefaultWare.setBounds(new Rectangle(245, 305, 209, 19));
        kDPanel1.add(contDefaultWare, null);
        contDaysPlanTurnover.setBounds(new Rectangle(8, 328, 209, 19));
        kDPanel1.add(contDaysPlanTurnover, null);
        kDLabelContainer8.setBounds(new Rectangle(244, 350, 209, 19));
        kDPanel1.add(kDLabelContainer8, null);
        kDLabel1.setBounds(new Rectangle(709, 121, 13, 19));
        kDPanel1.add(kDLabel1, null);
        chkIsCheck.setBounds(new Rectangle(495, 89, 140, 19));
        kDPanel1.add(chkIsCheck, null);
        kDLabelContainer9.setBounds(new Rectangle(8, 350, 209, 19));
        kDPanel1.add(kDLabelContainer9, null);
        contLastUpdateUser.setBounds(new Rectangle(7, 430, 270, 19));
        kDPanel1.add(contLastUpdateUser, null);
        contFreezeOrgUnit.setBounds(new Rectangle(362, 430, 270, 19));
        kDPanel1.add(contFreezeOrgUnit, null);
        contStatus.setBounds(new Rectangle(7, 383, 270, 19));
        kDPanel1.add(contStatus, null);
        contCreator.setBounds(new Rectangle(7, 407, 270, 19));
        kDPanel1.add(contCreator, null);
        contCreateTime.setBounds(new Rectangle(362, 383, 270, 19));
        kDPanel1.add(contCreateTime, null);
        contLastUpdateTime.setBounds(new Rectangle(362, 407, 270, 19));
        kDPanel1.add(contLastUpdateTime, null);
        kDSeparator7.setBounds(new Rectangle(8, 377, 625, 8));
        kDPanel1.add(kDSeparator7, null);
        conToolRate.setBounds(new Rectangle(495, 121, 209, 19));
        kDPanel1.add(conToolRate, null);
        kDLabelContainer10.setBounds(new Rectangle(245, 328, 209, 19));
        kDPanel1.add(kDLabelContainer10, null);
        contCostQty.setBounds(new Rectangle(496, 167, 209, 19));
        kDPanel1.add(contCostQty, null);
        contCostPrice.setBounds(new Rectangle(496, 144, 209, 19));
        kDPanel1.add(contCostPrice, null);
        kdtMaterialLoc.setBounds(new Rectangle(496, 195, 208, 172));
        kdtMaterialLoc_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtMaterialLoc,new com.kingdee.eas.basedata.master.material.MaterialInventoryMaterialLocInfo(),null,false);
        kDPanel1.add(kdtMaterialLoc_detailPanel, null);
        //contOrgUnit
        contOrgUnit.setBoundEditor(prmtOrgUnit);
        //contId
        contId.setBoundEditor(txtStorageOrgName);
        //contAbcType
        contAbcType.setBoundEditor(comboAbcType);
        //contUnit
        contUnit.setBoundEditor(prmtUnit);
        //contQtySafety
        contQtySafety.setBoundEditor(txtQtySafety);
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(prmtInvPlanner);
        //contQtyMax
        contQtyMax.setBoundEditor(txtQtyMax);
        //contQtyMin
        contQtyMin.setBoundEditor(txtQtyMin);
        //contPeriodValid
        contPeriodValid.setBoundEditor(kDSPeriodValid);
        //contPeriodValidUnit
        contPeriodValidUnit.setBoundEditor(comboPeriodValidUnit);
        //contInWarehsAhead
        contInWarehsAhead.setBoundEditor(kDSInWarehsAhead);
        //contOutWarehsAhead
        contOutWarehsAhead.setBoundEditor(spOutWarehsAhead);
        //contPrepWarnAhead
        contPrepWarnAhead.setBoundEditor(kDSPrepWarnAhead);
        //contAheadUnit
        contAheadUnit.setBoundEditor(comboAheadUnit);
        //contDaysBottom
        contDaysBottom.setBoundEditor(spDaysBottom);
        //contDaysTop
        contDaysTop.setBoundEditor(spInvDaysTop);
        //contDaysTurnover
        contDaysTurnover.setBoundEditor(spDaysTurnover);
        //contIssuePriorityMode
        contIssuePriorityMode.setBoundEditor(comboIssuePriorityMode);
        //contQtyMinPackage
        contQtyMinPackage.setBoundEditor(txtQtyMinPackage);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(comboPlanningMode);
        //kDLabelContainer3
        kDLabelContainer3.setBoundEditor(txtReBookQty);
        //kDLabelContainer4
        kDLabelContainer4.setBoundEditor(spinnerPurchAheadDate);
        //kDLabelContainer5
        kDLabelContainer5.setBoundEditor(txtConsumeSpeed);
        //kDLabelContainer6
        kDLabelContainer6.setBoundEditor(comboBatchPolicy);
        //kDLabelContainer7
        kDLabelContainer7.setBoundEditor(txtFixationBatchQty);
        //contDefaultWare
        contDefaultWare.setBoundEditor(prmtDefaultWare);
        //contDaysPlanTurnover
        contDaysPlanTurnover.setBoundEditor(txtDaysPlanTurnover);
        //kDLabelContainer8
        kDLabelContainer8.setBoundEditor(txtCheapRate);
        //kDLabelContainer9
        kDLabelContainer9.setBoundEditor(prmtQualityOrg);
        //contLastUpdateUser
        contLastUpdateUser.setBoundEditor(prmtLastUpdateUser);
        //contFreezeOrgUnit
        contFreezeOrgUnit.setBoundEditor(prmtFreezeOrgUnit);
        //contStatus
        contStatus.setBoundEditor(comboStatus);
        //contCreator
        contCreator.setBoundEditor(prmtCreator);
        //contCreateTime
        contCreateTime.setBoundEditor(pkCreateTime);
        //contLastUpdateTime
        contLastUpdateTime.setBoundEditor(pkLastUpdateTime);
        //conToolRate
        conToolRate.setBoundEditor(txtToolRate);
        //kDLabelContainer10
        kDLabelContainer10.setBoundEditor(prmtDefaultlocation);
        //contCostQty
        contCostQty.setBoundEditor(txtCostQty);
        //contCostPrice
        contCostPrice.setBoundEditor(txtCostPrice);

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
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuHelp
        menuHelp.add(menuItemHelp);
        menuHelp.add(kDSeparator12);
        menuHelp.add(menuItemRegPro);
        menuHelp.add(menuItemAbout);
        menuHelp.add(menuItemPersonalSite);
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
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnReset);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnApprove);
        this.toolBar.add(btnUnApprove);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("orgUnit.name", String.class, this.txtStorageOrgName, "text");
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtCreator, "data");
		dataBinder.registerBinding("lastUpdateUser", com.kingdee.eas.base.permission.UserInfo.class, this.prmtLastUpdateUser, "data");
		dataBinder.registerBinding("status", com.kingdee.eas.basedata.master.material.UsedStatusEnum.class, this.comboStatus, "selectedItem");
		dataBinder.registerBinding("orgUnit", com.kingdee.eas.basedata.org.FullOrgUnitInfo.class, this.prmtOrgUnit, "data");
		dataBinder.registerBinding("freezeOrgUnit", com.kingdee.eas.basedata.org.FullOrgUnitInfo.class, this.prmtFreezeOrgUnit, "data");
		dataBinder.registerBinding("qtySafety", java.math.BigDecimal.class, this.txtQtySafety, "value");
		dataBinder.registerBinding("qtyMin", java.math.BigDecimal.class, this.txtQtyMin, "value");
		dataBinder.registerBinding("qtyMax", java.math.BigDecimal.class, this.txtQtyMax, "value");
		dataBinder.registerBinding("qtyMinPackage", java.math.BigDecimal.class, this.txtQtyMinPackage, "value");
		dataBinder.registerBinding("abcType", com.kingdee.eas.basedata.master.material.ABCEnum.class, this.comboAbcType, "selectedItem");
		dataBinder.registerBinding("issuePriorityMode", com.kingdee.eas.basedata.master.material.IssuePriorityEnum.class, this.comboIssuePriorityMode, "selectedItem");
		dataBinder.registerBinding("unit", com.kingdee.eas.basedata.assistant.MeasureUnitInfo.class, this.prmtUnit, "data");
		dataBinder.registerBinding("periodValidUnit", com.kingdee.eas.basedata.master.material.TimeUnitEnum.class, this.comboPeriodValidUnit, "selectedItem");
		dataBinder.registerBinding("aheadUnit", com.kingdee.eas.basedata.master.material.TimeUnitEnum.class, this.comboAheadUnit, "selectedItem");
		dataBinder.registerBinding("lastUpdateTime", java.sql.Timestamp.class, this.pkLastUpdateTime, "value");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.pkCreateTime, "value");
		dataBinder.registerBinding("daysBottom", int.class, this.spDaysBottom, "value");
		dataBinder.registerBinding("periodValid", int.class, this.kDSPeriodValid, "value");
		dataBinder.registerBinding("inWarehsAhead", int.class, this.kDSInWarehsAhead, "value");
		dataBinder.registerBinding("prepWarnAhead", int.class, this.kDSPrepWarnAhead, "value");
		dataBinder.registerBinding("outWarehsAhead", int.class, this.spOutWarehsAhead, "value");
		dataBinder.registerBinding("daysTop", int.class, this.spInvDaysTop, "value");
		dataBinder.registerBinding("daysTurnover", int.class, this.spDaysTurnover, "value");
		dataBinder.registerBinding("invPlanner", com.kingdee.eas.basedata.person.PersonInfo.class, this.prmtInvPlanner, "data");
		dataBinder.registerBinding("isControl", boolean.class, this.chkIsControl, "selected");
		dataBinder.registerBinding("isNegative", boolean.class, this.chkIsNegative, "selected");
		dataBinder.registerBinding("isBatchNo", boolean.class, this.chkIsBatchNo, "selected");
		dataBinder.registerBinding("isLotNumber", boolean.class, this.chkIsLotNumber, "selected");
		dataBinder.registerBinding("isBarcode", boolean.class, this.chkIsBarcode, "selected");
		dataBinder.registerBinding("isSequenceNo", boolean.class, this.chkIsSequenceNo, "selected");
		dataBinder.registerBinding("isCompages", boolean.class, this.chkIsCompages, "selected");
		dataBinder.registerBinding("isPeriodValid", boolean.class, this.chkIsPeriodValid, "selected");
		dataBinder.registerBinding("planningMode", com.kingdee.eas.basedata.master.material.PlanningModeEnum.class, this.comboPlanningMode, "selectedItem");
		dataBinder.registerBinding("reBookQty", java.math.BigDecimal.class, this.txtReBookQty, "value");
		dataBinder.registerBinding("consumeSpeed", java.math.BigDecimal.class, this.txtConsumeSpeed, "value");
		dataBinder.registerBinding("batchPolicy", com.kingdee.eas.basedata.master.material.BatchPolicyEnum.class, this.comboBatchPolicy, "selectedItem");
		dataBinder.registerBinding("fixationBatchQty", java.math.BigDecimal.class, this.txtFixationBatchQty, "value");
		dataBinder.registerBinding("purchasingAheadDate", int.class, this.spinnerPurchAheadDate, "value");
		dataBinder.registerBinding("defaultWarehouse", com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo.class, this.prmtDefaultWare, "data");
		dataBinder.registerBinding("daysPlanTurnover", int.class, this.txtDaysPlanTurnover, "text");
		dataBinder.registerBinding("cheapRate", java.math.BigDecimal.class, this.txtCheapRate, "value");
		dataBinder.registerBinding("isCheck", boolean.class, this.chkIsCheck, "selected");
		dataBinder.registerBinding("qualityOrg", com.kingdee.eas.basedata.org.QualityOrgUnitInfo.class, this.prmtQualityOrg, "data");
		dataBinder.registerBinding("toolRate", java.math.BigDecimal.class, this.txtToolRate, "value");
		dataBinder.registerBinding("location", com.kingdee.eas.basedata.scm.im.inv.LocationInfo.class, this.prmtDefaultlocation, "data");
		dataBinder.registerBinding("MaterialLoc.seq", int.class, this.kdtMaterialLoc, "seq.text");
		dataBinder.registerBinding("MaterialLoc", com.kingdee.eas.basedata.master.material.MaterialInventoryMaterialLocInfo.class, this.kdtMaterialLoc, "userObject");
		dataBinder.registerBinding("MaterialLoc.loc", String.class, this.kdtMaterialLoc, "loc.text");
		dataBinder.registerBinding("MaterialLoc.bmwLoc", com.kingdee.util.enums.Enum.class, this.kdtMaterialLoc, "bmwLoc.text");
		dataBinder.registerBinding("CostQty", java.math.BigDecimal.class, this.txtCostQty, "value");
		dataBinder.registerBinding("CostPrice", java.math.BigDecimal.class, this.txtCostPrice, "value");		
	}
	//Regiester UI State
	private void registerUIState(){					 	        		
	        getActionManager().registerUIState(STATUS_ADDNEW, this.actionCancel, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_ADDNEW, this.actionCancelCancel, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_ADDNEW, this.actionRemove, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_ADDNEW, this.actionEdit, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_EDIT, this.actionEdit, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.actionSave, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.actionSubmit, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.actionCancel, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.actionCancelCancel, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.actionRemove, ActionStateConst.DISABLED);
	        getActionManager().registerUIState(STATUS_VIEW, this.actionEdit, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_VIEW, this.actionCopy, ActionStateConst.ENABLED);		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.basedata.master.material.app.MaterialInventoryUIHandler";
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
        this.chkIsControl.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.basedata.master.material.MaterialInventoryInfo)ov;
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
		getValidateHelper().registerBindProperty("orgUnit.name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateUser", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("status", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("orgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("freezeOrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("qtySafety", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("qtyMin", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("qtyMax", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("qtyMinPackage", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("abcType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("issuePriorityMode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("unit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("periodValidUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("aheadUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("daysBottom", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("periodValid", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("inWarehsAhead", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("prepWarnAhead", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("outWarehsAhead", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("daysTop", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("daysTurnover", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("invPlanner", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isControl", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isNegative", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isBatchNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isLotNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isBarcode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isSequenceNo", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isCompages", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isPeriodValid", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("planningMode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("reBookQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("consumeSpeed", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("batchPolicy", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("fixationBatchQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("purchasingAheadDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("defaultWarehouse", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("daysPlanTurnover", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("cheapRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("isCheck", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("qualityOrg", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("toolRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("location", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("MaterialLoc.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("MaterialLoc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("MaterialLoc.loc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("MaterialLoc.bmwLoc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("CostQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("CostPrice", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
		            this.actionCancel.setEnabled(false);
		            this.actionCancelCancel.setEnabled(false);
		            this.actionRemove.setEnabled(false);
		            this.actionEdit.setEnabled(false);
        } else if (STATUS_EDIT.equals(this.oprtState)) {
		            this.actionEdit.setEnabled(false);
        } else if (STATUS_VIEW.equals(this.oprtState)) {
		            this.actionSave.setEnabled(false);
		            this.actionSubmit.setEnabled(false);
		            this.actionCancel.setEnabled(false);
		            this.actionCancelCancel.setEnabled(false);
		            this.actionRemove.setEnabled(false);
		            this.actionEdit.setEnabled(true);
		            this.actionCopy.setEnabled(true);
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
        sic.add(new SelectorItemInfo("orgUnit.name"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("creator.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("creator.id"));
        	sic.add(new SelectorItemInfo("creator.number"));
        	sic.add(new SelectorItemInfo("creator.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("lastUpdateUser.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("lastUpdateUser.id"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.number"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.name"));
		}
        sic.add(new SelectorItemInfo("status"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("orgUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("orgUnit.id"));
        	sic.add(new SelectorItemInfo("orgUnit.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("freezeOrgUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("freezeOrgUnit.id"));
        	sic.add(new SelectorItemInfo("freezeOrgUnit.number"));
        	sic.add(new SelectorItemInfo("freezeOrgUnit.name"));
		}
        sic.add(new SelectorItemInfo("qtySafety"));
        sic.add(new SelectorItemInfo("qtyMin"));
        sic.add(new SelectorItemInfo("qtyMax"));
        sic.add(new SelectorItemInfo("qtyMinPackage"));
        sic.add(new SelectorItemInfo("abcType"));
        sic.add(new SelectorItemInfo("issuePriorityMode"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("unit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("unit.id"));
        	sic.add(new SelectorItemInfo("unit.number"));
        	sic.add(new SelectorItemInfo("unit.name"));
		}
        sic.add(new SelectorItemInfo("periodValidUnit"));
        sic.add(new SelectorItemInfo("aheadUnit"));
        sic.add(new SelectorItemInfo("lastUpdateTime"));
        sic.add(new SelectorItemInfo("createTime"));
        sic.add(new SelectorItemInfo("daysBottom"));
        sic.add(new SelectorItemInfo("periodValid"));
        sic.add(new SelectorItemInfo("inWarehsAhead"));
        sic.add(new SelectorItemInfo("prepWarnAhead"));
        sic.add(new SelectorItemInfo("outWarehsAhead"));
        sic.add(new SelectorItemInfo("daysTop"));
        sic.add(new SelectorItemInfo("daysTurnover"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("invPlanner.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("invPlanner.id"));
        	sic.add(new SelectorItemInfo("invPlanner.number"));
        	sic.add(new SelectorItemInfo("invPlanner.name"));
		}
        sic.add(new SelectorItemInfo("isControl"));
        sic.add(new SelectorItemInfo("isNegative"));
        sic.add(new SelectorItemInfo("isBatchNo"));
        sic.add(new SelectorItemInfo("isLotNumber"));
        sic.add(new SelectorItemInfo("isBarcode"));
        sic.add(new SelectorItemInfo("isSequenceNo"));
        sic.add(new SelectorItemInfo("isCompages"));
        sic.add(new SelectorItemInfo("isPeriodValid"));
        sic.add(new SelectorItemInfo("planningMode"));
        sic.add(new SelectorItemInfo("reBookQty"));
        sic.add(new SelectorItemInfo("consumeSpeed"));
        sic.add(new SelectorItemInfo("batchPolicy"));
        sic.add(new SelectorItemInfo("fixationBatchQty"));
        sic.add(new SelectorItemInfo("purchasingAheadDate"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("defaultWarehouse.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("defaultWarehouse.id"));
        	sic.add(new SelectorItemInfo("defaultWarehouse.number"));
        	sic.add(new SelectorItemInfo("defaultWarehouse.name"));
		}
        sic.add(new SelectorItemInfo("daysPlanTurnover"));
        sic.add(new SelectorItemInfo("cheapRate"));
        sic.add(new SelectorItemInfo("isCheck"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("qualityOrg.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("qualityOrg.id"));
        	sic.add(new SelectorItemInfo("qualityOrg.number"));
        	sic.add(new SelectorItemInfo("qualityOrg.name"));
		}
        sic.add(new SelectorItemInfo("toolRate"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("location.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("location.id"));
        	sic.add(new SelectorItemInfo("location.number"));
        	sic.add(new SelectorItemInfo("location.name"));
		}
    	sic.add(new SelectorItemInfo("MaterialLoc.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("MaterialLoc.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("MaterialLoc.loc"));
    	sic.add(new SelectorItemInfo("MaterialLoc.bmwLoc"));
        sic.add(new SelectorItemInfo("CostQty"));
        sic.add(new SelectorItemInfo("CostPrice"));
        return sic;
    }        
    	

    /**
     * output actionPageSetup_actionPerformed method
     */
    public void actionPageSetup_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPageSetup_actionPerformed(e);
    }
    	

    /**
     * output actionExitCurrent_actionPerformed method
     */
    public void actionExitCurrent_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionExitCurrent_actionPerformed(e);
    }
    	

    /**
     * output actionHelp_actionPerformed method
     */
    public void actionHelp_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionHelp_actionPerformed(e);
    }
    	

    /**
     * output actionAbout_actionPerformed method
     */
    public void actionAbout_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAbout_actionPerformed(e);
    }
    	

    /**
     * output actionOnLoad_actionPerformed method
     */
    public void actionOnLoad_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionOnLoad_actionPerformed(e);
    }
    	

    /**
     * output actionSendMessage_actionPerformed method
     */
    public void actionSendMessage_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSendMessage_actionPerformed(e);
    }
    	

    /**
     * output actionSave_actionPerformed method
     */
    public void actionSave_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSave_actionPerformed(e);
    }
    	

    /**
     * output actionSubmit_actionPerformed method
     */
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSubmit_actionPerformed(e);
    }
    	

    /**
     * output actionCancel_actionPerformed method
     */
    public void actionCancel_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCancel_actionPerformed(e);
    }
    	

    /**
     * output actionCancelCancel_actionPerformed method
     */
    public void actionCancelCancel_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCancelCancel_actionPerformed(e);
    }
    	

    /**
     * output actionFirst_actionPerformed method
     */
    public void actionFirst_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionFirst_actionPerformed(e);
    }
    	

    /**
     * output actionPre_actionPerformed method
     */
    public void actionPre_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionPre_actionPerformed(e);
    }
    	

    /**
     * output actionNext_actionPerformed method
     */
    public void actionNext_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionNext_actionPerformed(e);
    }
    	

    /**
     * output actionLast_actionPerformed method
     */
    public void actionLast_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionLast_actionPerformed(e);
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
     * output actionCopy_actionPerformed method
     */
    public void actionCopy_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionCopy_actionPerformed(e);
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
     * output actionRemove_actionPerformed method
     */
    public void actionRemove_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionRemove_actionPerformed(e);
    }
    	

    /**
     * output actionAttachment_actionPerformed method
     */
    public void actionAttachment_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAttachment_actionPerformed(e);
    }
    	

    /**
     * output actionSubmitOption_actionPerformed method
     */
    public void actionSubmitOption_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSubmitOption_actionPerformed(e);
    }
    	

    /**
     * output actionApprove_actionPerformed method
     */
    public void actionApprove_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionUnApprove_actionPerformed method
     */
    public void actionUnApprove_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionPageSetup(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPageSetup(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPageSetup() {
    	return false;
    }
	public RequestContext prepareActionExitCurrent(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionExitCurrent(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionExitCurrent() {
    	return false;
    }
	public RequestContext prepareActionHelp(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionHelp(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionHelp() {
    	return false;
    }
	public RequestContext prepareActionAbout(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionAbout(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAbout() {
    	return false;
    }
	public RequestContext prepareActionOnLoad(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionOnLoad(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionOnLoad() {
    	return false;
    }
	public RequestContext prepareActionSendMessage(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSendMessage(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSendMessage() {
    	return false;
    }
	public RequestContext prepareActionSave(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSave(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSave() {
    	return false;
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
	public RequestContext prepareActionCancel(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionCancel(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCancel() {
    	return false;
    }
	public RequestContext prepareActionCancelCancel(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionCancelCancel(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCancelCancel() {
    	return false;
    }
	public RequestContext prepareActionFirst(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionFirst(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionFirst() {
    	return false;
    }
	public RequestContext prepareActionPre(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPre(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPre() {
    	return false;
    }
	public RequestContext prepareActionNext(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionNext(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionNext() {
    	return false;
    }
	public RequestContext prepareActionLast(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionLast(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionLast() {
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
	public RequestContext prepareActionCopy(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionCopy(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCopy() {
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
	public RequestContext prepareActionAttachment(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionAttachment(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAttachment() {
    	return false;
    }
	public RequestContext prepareActionSubmitOption(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSubmitOption(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSubmitOption() {
    	return false;
    }
	public RequestContext prepareActionApprove(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionApprove() {
    	return false;
    }
	public RequestContext prepareActionUnApprove(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionUnApprove() {
    	return false;
    }

    /**
     * output ActionApprove class
     */     
    protected class ActionApprove extends ItemAction {     
    
        public ActionApprove()
        {
            this(null);
        }

        public ActionApprove(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionApprove.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionApprove.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionApprove.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialInventoryUI.this, "ActionApprove", "actionApprove_actionPerformed", e);
        }
    }

    /**
     * output ActionUnApprove class
     */     
    protected class ActionUnApprove extends ItemAction {     
    
        public ActionUnApprove()
        {
            this(null);
        }

        public ActionUnApprove(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionUnApprove.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionUnApprove.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionUnApprove.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialInventoryUI.this, "ActionUnApprove", "actionUnApprove_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.basedata.master.material.client", "MaterialInventoryUI");
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.basedata.master.material.client.MaterialInventoryUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.basedata.master.material.MaterialInventoryFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.basedata.master.material.MaterialInventoryInfo objectValue = new com.kingdee.eas.basedata.master.material.MaterialInventoryInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }



    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kdtMaterialLoc;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
				vo.put("reBookQty",new java.math.BigDecimal(0));
		vo.put("consumeSpeed",new java.math.BigDecimal(0));
		vo.put("fixationBatchQty",new java.math.BigDecimal(0));
		vo.put("toolRate",new java.math.BigDecimal(0));
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}