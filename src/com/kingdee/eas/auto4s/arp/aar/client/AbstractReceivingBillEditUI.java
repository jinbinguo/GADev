/**
 * output package name
 */
package com.kingdee.eas.auto4s.arp.aar.client;

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
public abstract class AbstractReceivingBillEditUI extends com.kingdee.eas.framework.client.CoreBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractReceivingBillEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contPerson;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCurrency;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBrand;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contReceiveObject;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contRemark;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contReceiveObjectType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contActualPayer;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizType;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kDTabbedPane1;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkisAccount;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvoucherID;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreator;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contReceiveObjectNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contReceiveObjectName;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contAuditor;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtReceiveObjectName;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvehicle;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnTotalLines;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAmtClean;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsSendSms;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcard;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contreadSN;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnPRContent;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contpaymentType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contExchangeRate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDepartment;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkBizDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtDescription;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkDate;
    protected com.kingdee.bos.ctrl.swing.KDComboBox Status;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtPerson;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCurrency;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtOrgUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtBrand;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtReceiveObject;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtRemark;
    protected com.kingdee.bos.ctrl.swing.KDComboBox ReceiveObjectType;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtActualPayer;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtBizType;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel1;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel4;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel2;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel5;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel6;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtContent;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtContent_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnMoveTypeTop;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnMoveTypeDown;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel3;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtType;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtType_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnMoveReceivingTop;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnMoveReceivingDown;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnScoreCard;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnGiftCard;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnSavingsCard;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contreceivingAmt;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contPRAbateAmt;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcashCouponAmt;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contpointToCashAmt;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contamount;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnPRWriteOff;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsavingCardAmt;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtreceivingAmt;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtPRAbateAmt;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtcashCouponAmt;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtpointToCashAmt;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtamount;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtsavingCardAmt;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtEntrys;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtEntrys_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnSplitLines;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAddLines;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnInsertLines;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnDeleteLines;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtHistory;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtvoucherID;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtCreator;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtReceiveObjectNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkAuditTime;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtLastUpdateUser;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtReceiveObject;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtAuditor;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtvehicle;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtcard;
    protected com.kingdee.bos.ctrl.swing.KDPasswordField txtreadSN;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtpaymentType;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtExchangeRate;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtDepartment;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAudit;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnFaudit;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnRec;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnCancelRec;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnLoadDoc;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAutoLoad;
    protected javax.swing.JToolBar.Separator separator4;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnViewSource;
    protected javax.swing.JToolBar.Separator separator5;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnViewCustomerProfile;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnViewCarProfile;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnEasVoucher;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnEasDelVoucher;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnCommitSettle;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemAudit;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemAntiAudit;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsrcEntryIds;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtsrcEntryIds;
    protected com.kingdee.eas.auto4s.arp.aar.ReceivingBillInfo editData = null;
    protected ActionAudit actionAudit = null;
    protected ActionFaudit actionFaudit = null;
    protected ActionViewCustomer actionViewCustomer = null;
    protected ActionViewVehicle actionViewVehicle = null;
    protected ActionLoadDoc actionLoadDoc = null;
    protected ActionAddLines actionAddLines = null;
    protected ActionSplitLines actionSplitLines = null;
    protected ActionInsetLines actionInsetLines = null;
    protected ActionDeleteLines actionDeleteLines = null;
    protected ActionTotal actionTotal = null;
    protected ActionViewSource actionViewSource = null;
    protected ActionCommitSettle actionCommitSettle = null;
    protected ActionAutoLoad actionAutoLoad = null;
    protected actionCancelRec actionCancelRec = null;
    protected actionRec actionRec = null;
    protected actionMoveTypeTop actionMoveTypeTop = null;
    protected actionMoveTypeDown actionMoveTypeDown = null;
    protected actionMoveReceivingTop actionMoveReceivingTop = null;
    protected actionMoveReceivingDown actionMoveReceivingDown = null;
    protected actionAddReceivingLines actionAddReceivingLines = null;
    protected actionInsertReceivingLines actionInsertReceivingLines = null;
    protected actionDeleteReceivingLines actionDeleteReceivingLines = null;
    protected ActionEasVoucher actionEasVoucher = null;
    protected ActionEasDelVoucher actionEasDelVoucher = null;
    protected ActionTotalLines actionTotalLines = null;
    protected actionSavingsCard actionSavingsCard = null;
    protected actionGiftCard actionGiftCard = null;
    protected actionScoreCard actionScoreCard = null;
    protected ActionPRContent actionPRContent = null;
    protected ActionPRWriteOff actionPRWriteOff = null;
    /**
     * output class constructor
     */
    public AbstractReceivingBillEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractReceivingBillEditUI.class.getName());
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
        //actionAudit
        this.actionAudit = new ActionAudit(this);
        getActionManager().registerAction("actionAudit", actionAudit);
        this.actionAudit.setBindWorkFlow(true);
        this.actionAudit.setExtendProperty("userDefined", "false");
        this.actionAudit.setExtendProperty("isObjectUpdateLock", "false");
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.WorkFlowService());
        //actionFaudit
        this.actionFaudit = new ActionFaudit(this);
        getActionManager().registerAction("actionFaudit", actionFaudit);
        this.actionFaudit.setBindWorkFlow(true);
        this.actionFaudit.setExtendProperty("userDefined", "false");
        this.actionFaudit.setExtendProperty("isObjectUpdateLock", "false");
         this.actionFaudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionFaudit.addService(new com.kingdee.eas.framework.client.service.WorkFlowService());
        //actionViewCustomer
        this.actionViewCustomer = new ActionViewCustomer(this);
        getActionManager().registerAction("actionViewCustomer", actionViewCustomer);
         this.actionViewCustomer.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewVehicle
        this.actionViewVehicle = new ActionViewVehicle(this);
        getActionManager().registerAction("actionViewVehicle", actionViewVehicle);
         this.actionViewVehicle.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionLoadDoc
        this.actionLoadDoc = new ActionLoadDoc(this);
        getActionManager().registerAction("actionLoadDoc", actionLoadDoc);
         this.actionLoadDoc.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAddLines
        this.actionAddLines = new ActionAddLines(this);
        getActionManager().registerAction("actionAddLines", actionAddLines);
         this.actionAddLines.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionSplitLines
        this.actionSplitLines = new ActionSplitLines(this);
        getActionManager().registerAction("actionSplitLines", actionSplitLines);
         this.actionSplitLines.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionInsetLines
        this.actionInsetLines = new ActionInsetLines(this);
        getActionManager().registerAction("actionInsetLines", actionInsetLines);
         this.actionInsetLines.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionDeleteLines
        this.actionDeleteLines = new ActionDeleteLines(this);
        getActionManager().registerAction("actionDeleteLines", actionDeleteLines);
         this.actionDeleteLines.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionTotal
        this.actionTotal = new ActionTotal(this);
        getActionManager().registerAction("actionTotal", actionTotal);
         this.actionTotal.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionViewSource
        this.actionViewSource = new ActionViewSource(this);
        getActionManager().registerAction("actionViewSource", actionViewSource);
         this.actionViewSource.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCommitSettle
        this.actionCommitSettle = new ActionCommitSettle(this);
        getActionManager().registerAction("actionCommitSettle", actionCommitSettle);
         this.actionCommitSettle.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAutoLoad
        this.actionAutoLoad = new ActionAutoLoad(this);
        getActionManager().registerAction("actionAutoLoad", actionAutoLoad);
         this.actionAutoLoad.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCancelRec
        this.actionCancelRec = new actionCancelRec(this);
        getActionManager().registerAction("actionCancelRec", actionCancelRec);
         this.actionCancelRec.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRec
        this.actionRec = new actionRec(this);
        getActionManager().registerAction("actionRec", actionRec);
         this.actionRec.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionMoveTypeTop
        this.actionMoveTypeTop = new actionMoveTypeTop(this);
        getActionManager().registerAction("actionMoveTypeTop", actionMoveTypeTop);
         this.actionMoveTypeTop.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionMoveTypeDown
        this.actionMoveTypeDown = new actionMoveTypeDown(this);
        getActionManager().registerAction("actionMoveTypeDown", actionMoveTypeDown);
         this.actionMoveTypeDown.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionMoveReceivingTop
        this.actionMoveReceivingTop = new actionMoveReceivingTop(this);
        getActionManager().registerAction("actionMoveReceivingTop", actionMoveReceivingTop);
         this.actionMoveReceivingTop.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionMoveReceivingDown
        this.actionMoveReceivingDown = new actionMoveReceivingDown(this);
        getActionManager().registerAction("actionMoveReceivingDown", actionMoveReceivingDown);
         this.actionMoveReceivingDown.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAddReceivingLines
        this.actionAddReceivingLines = new actionAddReceivingLines(this);
        getActionManager().registerAction("actionAddReceivingLines", actionAddReceivingLines);
         this.actionAddReceivingLines.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionInsertReceivingLines
        this.actionInsertReceivingLines = new actionInsertReceivingLines(this);
        getActionManager().registerAction("actionInsertReceivingLines", actionInsertReceivingLines);
         this.actionInsertReceivingLines.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionDeleteReceivingLines
        this.actionDeleteReceivingLines = new actionDeleteReceivingLines(this);
        getActionManager().registerAction("actionDeleteReceivingLines", actionDeleteReceivingLines);
         this.actionDeleteReceivingLines.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionEasVoucher
        this.actionEasVoucher = new ActionEasVoucher(this);
        getActionManager().registerAction("actionEasVoucher", actionEasVoucher);
         this.actionEasVoucher.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionEasDelVoucher
        this.actionEasDelVoucher = new ActionEasDelVoucher(this);
        getActionManager().registerAction("actionEasDelVoucher", actionEasDelVoucher);
         this.actionEasDelVoucher.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionTotalLines
        this.actionTotalLines = new ActionTotalLines(this);
        getActionManager().registerAction("actionTotalLines", actionTotalLines);
         this.actionTotalLines.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionSavingsCard
        this.actionSavingsCard = new actionSavingsCard(this);
        getActionManager().registerAction("actionSavingsCard", actionSavingsCard);
         this.actionSavingsCard.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionGiftCard
        this.actionGiftCard = new actionGiftCard(this);
        getActionManager().registerAction("actionGiftCard", actionGiftCard);
         this.actionGiftCard.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionScoreCard
        this.actionScoreCard = new actionScoreCard(this);
        getActionManager().registerAction("actionScoreCard", actionScoreCard);
         this.actionScoreCard.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionPRContent
        this.actionPRContent = new ActionPRContent(this);
        getActionManager().registerAction("actionPRContent", actionPRContent);
         this.actionPRContent.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionPRWriteOff
        this.actionPRWriteOff = new ActionPRWriteOff(this);
        getActionManager().registerAction("actionPRWriteOff", actionPRWriteOff);
         this.actionPRWriteOff.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBizDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDescription = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contPerson = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCurrency = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBrand = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contReceiveObject = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contRemark = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contReceiveObjectType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contActualPayer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBizType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDTabbedPane1 = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.chkisAccount = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contvoucherID = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contReceiveObjectNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateUser = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contReceiveObjectName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contAuditor = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtReceiveObjectName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.contvehicle = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnTotalLines = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAmtClean = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.chkIsSendSms = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contcard = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contreadSN = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnPRContent = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.contpaymentType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contExchangeRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDepartment = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkBizDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtDescription = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.Status = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtPerson = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtCurrency = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtBrand = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtReceiveObject = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtRemark = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.ReceiveObjectType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtActualPayer = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtBizType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDPanel1 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel4 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel2 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel5 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel6 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kdtContent = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.btnMoveTypeTop = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnMoveTypeDown = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDPanel3 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kdtType = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.btnMoveReceivingTop = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnMoveReceivingDown = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnScoreCard = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnGiftCard = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnSavingsCard = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.contreceivingAmt = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contPRAbateAmt = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcashCouponAmt = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contpointToCashAmt = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contamount = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnPRWriteOff = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.contsavingCardAmt = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtreceivingAmt = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtPRAbateAmt = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtcashCouponAmt = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtpointToCashAmt = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtamount = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtsavingCardAmt = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.kdtEntrys = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.btnSplitLines = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAddLines = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnInsertLines = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnDeleteLines = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kdtHistory = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.txtvoucherID = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtCreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDDateCreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtReceiveObjectNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.kDDateLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.pkAuditTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.prmtLastUpdateUser = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtReceiveObject = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtAuditor = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtvehicle = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtcard = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtreadSN = new com.kingdee.bos.ctrl.swing.KDPasswordField();
        this.prmtpaymentType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtExchangeRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.prmtDepartment = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.btnAudit = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnFaudit = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnRec = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnCancelRec = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnLoadDoc = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAutoLoad = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator4 = new javax.swing.JToolBar.Separator();
        this.btnViewSource = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator5 = new javax.swing.JToolBar.Separator();
        this.btnViewCustomerProfile = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnViewCarProfile = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnEasVoucher = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnEasDelVoucher = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnCommitSettle = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.menuItemAudit = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemAntiAudit = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.contsrcEntryIds = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtsrcEntryIds = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.contNumber.setName("contNumber");
        this.contBizDate.setName("contBizDate");
        this.contDescription.setName("contDescription");
        this.contDate.setName("contDate");
        this.contStatus.setName("contStatus");
        this.contPerson.setName("contPerson");
        this.contCurrency.setName("contCurrency");
        this.contOrgUnit.setName("contOrgUnit");
        this.contBrand.setName("contBrand");
        this.contReceiveObject.setName("contReceiveObject");
        this.contRemark.setName("contRemark");
        this.contReceiveObjectType.setName("contReceiveObjectType");
        this.contActualPayer.setName("contActualPayer");
        this.contBizType.setName("contBizType");
        this.kDTabbedPane1.setName("kDTabbedPane1");
        this.chkisAccount.setName("chkisAccount");
        this.contvoucherID.setName("contvoucherID");
        this.contCreator.setName("contCreator");
        this.contCreateTime.setName("contCreateTime");
        this.contReceiveObjectNumber.setName("contReceiveObjectNumber");
        this.contLastUpdateTime.setName("contLastUpdateTime");
        this.contAuditTime.setName("contAuditTime");
        this.contLastUpdateUser.setName("contLastUpdateUser");
        this.contReceiveObjectName.setName("contReceiveObjectName");
        this.contAuditor.setName("contAuditor");
        this.txtReceiveObjectName.setName("txtReceiveObjectName");
        this.contvehicle.setName("contvehicle");
        this.btnTotalLines.setName("btnTotalLines");
        this.btnAmtClean.setName("btnAmtClean");
        this.chkIsSendSms.setName("chkIsSendSms");
        this.contcard.setName("contcard");
        this.contreadSN.setName("contreadSN");
        this.btnPRContent.setName("btnPRContent");
        this.contpaymentType.setName("contpaymentType");
        this.contExchangeRate.setName("contExchangeRate");
        this.contDepartment.setName("contDepartment");
        this.txtNumber.setName("txtNumber");
        this.pkBizDate.setName("pkBizDate");
        this.txtDescription.setName("txtDescription");
        this.pkDate.setName("pkDate");
        this.Status.setName("Status");
        this.prmtPerson.setName("prmtPerson");
        this.prmtCurrency.setName("prmtCurrency");
        this.prmtOrgUnit.setName("prmtOrgUnit");
        this.prmtBrand.setName("prmtBrand");
        this.prmtReceiveObject.setName("prmtReceiveObject");
        this.txtRemark.setName("txtRemark");
        this.ReceiveObjectType.setName("ReceiveObjectType");
        this.txtActualPayer.setName("txtActualPayer");
        this.prmtBizType.setName("prmtBizType");
        this.kDPanel1.setName("kDPanel1");
        this.kDPanel4.setName("kDPanel4");
        this.kDPanel2.setName("kDPanel2");
        this.kDPanel5.setName("kDPanel5");
        this.kDPanel6.setName("kDPanel6");
        this.kdtContent.setName("kdtContent");
        this.btnMoveTypeTop.setName("btnMoveTypeTop");
        this.btnMoveTypeDown.setName("btnMoveTypeDown");
        this.kDPanel3.setName("kDPanel3");
        this.kdtType.setName("kdtType");
        this.btnMoveReceivingTop.setName("btnMoveReceivingTop");
        this.btnMoveReceivingDown.setName("btnMoveReceivingDown");
        this.btnScoreCard.setName("btnScoreCard");
        this.btnGiftCard.setName("btnGiftCard");
        this.btnSavingsCard.setName("btnSavingsCard");
        this.contreceivingAmt.setName("contreceivingAmt");
        this.contPRAbateAmt.setName("contPRAbateAmt");
        this.contcashCouponAmt.setName("contcashCouponAmt");
        this.contpointToCashAmt.setName("contpointToCashAmt");
        this.contamount.setName("contamount");
        this.btnPRWriteOff.setName("btnPRWriteOff");
        this.contsavingCardAmt.setName("contsavingCardAmt");
        this.txtreceivingAmt.setName("txtreceivingAmt");
        this.txtPRAbateAmt.setName("txtPRAbateAmt");
        this.txtcashCouponAmt.setName("txtcashCouponAmt");
        this.txtpointToCashAmt.setName("txtpointToCashAmt");
        this.txtamount.setName("txtamount");
        this.txtsavingCardAmt.setName("txtsavingCardAmt");
        this.kdtEntrys.setName("kdtEntrys");
        this.btnSplitLines.setName("btnSplitLines");
        this.btnAddLines.setName("btnAddLines");
        this.btnInsertLines.setName("btnInsertLines");
        this.btnDeleteLines.setName("btnDeleteLines");
        this.kdtHistory.setName("kdtHistory");
        this.txtvoucherID.setName("txtvoucherID");
        this.prmtCreator.setName("prmtCreator");
        this.kDDateCreateTime.setName("kDDateCreateTime");
        this.txtReceiveObjectNumber.setName("txtReceiveObjectNumber");
        this.kDDateLastUpdateTime.setName("kDDateLastUpdateTime");
        this.pkAuditTime.setName("pkAuditTime");
        this.prmtLastUpdateUser.setName("prmtLastUpdateUser");
        this.txtReceiveObject.setName("txtReceiveObject");
        this.prmtAuditor.setName("prmtAuditor");
        this.prmtvehicle.setName("prmtvehicle");
        this.prmtcard.setName("prmtcard");
        this.txtreadSN.setName("txtreadSN");
        this.prmtpaymentType.setName("prmtpaymentType");
        this.txtExchangeRate.setName("txtExchangeRate");
        this.prmtDepartment.setName("prmtDepartment");
        this.btnAudit.setName("btnAudit");
        this.btnFaudit.setName("btnFaudit");
        this.btnRec.setName("btnRec");
        this.btnCancelRec.setName("btnCancelRec");
        this.btnLoadDoc.setName("btnLoadDoc");
        this.btnAutoLoad.setName("btnAutoLoad");
        this.separator4.setName("separator4");
        this.btnViewSource.setName("btnViewSource");
        this.separator5.setName("separator5");
        this.btnViewCustomerProfile.setName("btnViewCustomerProfile");
        this.btnViewCarProfile.setName("btnViewCarProfile");
        this.btnEasVoucher.setName("btnEasVoucher");
        this.btnEasDelVoucher.setName("btnEasDelVoucher");
        this.btnCommitSettle.setName("btnCommitSettle");
        this.menuItemAudit.setName("menuItemAudit");
        this.menuItemAntiAudit.setName("menuItemAntiAudit");
        this.contsrcEntryIds.setName("contsrcEntryIds");
        this.txtsrcEntryIds.setName("txtsrcEntryIds");
        // CoreUI		
        this.setPreferredSize(new Dimension(1013,639));		
        this.btnCopy.setVisible(false);		
        this.btnPre.setText(resHelper.getString("btnPre.text"));		
        this.btnPre.setToolTipText(resHelper.getString("btnPre.toolTipText"));		
        this.btnNext.setText(resHelper.getString("btnNext.text"));		
        this.btnNext.setToolTipText(resHelper.getString("btnNext.toolTipText"));		
        this.btnLast.setText(resHelper.getString("btnLast.text"));		
        this.btnLast.setToolTipText(resHelper.getString("btnLast.toolTipText"));		
        this.btnPrintPreview.setText(resHelper.getString("btnPrintPreview.text"));		
        this.menuItemCopy.setEnabled(false);		
        this.menuItemCopy.setVisible(false);		
        this.btnAttachment.setVisible(false);		
        this.btnAddLine.setVisible(false);		
        this.btnCopyLine.setVisible(false);		
        this.btnInsertLine.setVisible(false);		
        this.btnRemoveLine.setVisible(false);		
        this.btnVoucher.setText(resHelper.getString("btnVoucher.text"));		
        this.btnVoucher.setVisible(true);		
        this.btnDelVoucher.setText(resHelper.getString("btnDelVoucher.text"));		
        this.btnDelVoucher.setVisible(true);		
        this.btnAuditResult.setVisible(false);		
        this.btnMultiapprove.setVisible(false);		
        this.btnNextPerson.setVisible(false);		
        this.separator1.setVisible(false);		
        this.menuItemCreateTo.setVisible(true);		
        this.menuItemCopyFrom.setEnabled(false);		
        this.menuItemCopyFrom.setVisible(false);		
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
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);
        // contBizDate		
        this.contBizDate.setBoundLabelText(resHelper.getString("contBizDate.boundLabelText"));		
        this.contBizDate.setBoundLabelLength(100);		
        this.contBizDate.setBoundLabelUnderline(true);		
        this.contBizDate.setBoundLabelAlignment(7);		
        this.contBizDate.setVisible(false);
        // contDescription		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));		
        this.contDescription.setBoundLabelLength(100);		
        this.contDescription.setBoundLabelUnderline(true);		
        this.contDescription.setVisible(false);
        // contDate		
        this.contDate.setBoundLabelText(resHelper.getString("contDate.boundLabelText"));		
        this.contDate.setBoundLabelLength(100);		
        this.contDate.setBoundLabelUnderline(true);		
        this.contDate.setVisible(true);
        // contStatus		
        this.contStatus.setBoundLabelText(resHelper.getString("contStatus.boundLabelText"));		
        this.contStatus.setBoundLabelLength(100);		
        this.contStatus.setBoundLabelUnderline(true);		
        this.contStatus.setVisible(true);
        // contPerson		
        this.contPerson.setBoundLabelText(resHelper.getString("contPerson.boundLabelText"));		
        this.contPerson.setBoundLabelLength(100);		
        this.contPerson.setBoundLabelUnderline(true);		
        this.contPerson.setVisible(true);
        // contCurrency		
        this.contCurrency.setBoundLabelText(resHelper.getString("contCurrency.boundLabelText"));		
        this.contCurrency.setBoundLabelLength(100);		
        this.contCurrency.setBoundLabelUnderline(true);		
        this.contCurrency.setVisible(true);
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
        // contReceiveObject		
        this.contReceiveObject.setBoundLabelText(resHelper.getString("contReceiveObject.boundLabelText"));		
        this.contReceiveObject.setBoundLabelLength(100);		
        this.contReceiveObject.setBoundLabelUnderline(true);
        // contRemark		
        this.contRemark.setBoundLabelText(resHelper.getString("contRemark.boundLabelText"));		
        this.contRemark.setBoundLabelLength(100);		
        this.contRemark.setBoundLabelUnderline(true);		
        this.contRemark.setVisible(true);
        // contReceiveObjectType		
        this.contReceiveObjectType.setBoundLabelText(resHelper.getString("contReceiveObjectType.boundLabelText"));		
        this.contReceiveObjectType.setBoundLabelLength(100);		
        this.contReceiveObjectType.setBoundLabelUnderline(true);		
        this.contReceiveObjectType.setVisible(true);
        // contActualPayer		
        this.contActualPayer.setBoundLabelText(resHelper.getString("contActualPayer.boundLabelText"));		
        this.contActualPayer.setBoundLabelLength(100);		
        this.contActualPayer.setBoundLabelUnderline(true);		
        this.contActualPayer.setVisible(true);
        // contBizType		
        this.contBizType.setBoundLabelText(resHelper.getString("contBizType.boundLabelText"));		
        this.contBizType.setBoundLabelLength(100);		
        this.contBizType.setBoundLabelUnderline(true);		
        this.contBizType.setVisible(true);
        // kDTabbedPane1
        // chkisAccount		
        this.chkisAccount.setText(resHelper.getString("chkisAccount.text"));		
        this.chkisAccount.setVisible(false);		
        this.chkisAccount.setHorizontalAlignment(2);		
        this.chkisAccount.setEnabled(false);
        // contvoucherID		
        this.contvoucherID.setBoundLabelText(resHelper.getString("contvoucherID.boundLabelText"));		
        this.contvoucherID.setBoundLabelLength(100);		
        this.contvoucherID.setBoundLabelUnderline(true);		
        this.contvoucherID.setVisible(false);		
        this.contvoucherID.setEnabled(false);
        // contCreator		
        this.contCreator.setBoundLabelText(resHelper.getString("contCreator.boundLabelText"));		
        this.contCreator.setBoundLabelLength(100);		
        this.contCreator.setBoundLabelUnderline(true);		
        this.contCreator.setEnabled(false);		
        this.contCreator.setVisible(false);
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);		
        this.contCreateTime.setEnabled(false);		
        this.contCreateTime.setVisible(false);
        // contReceiveObjectNumber		
        this.contReceiveObjectNumber.setBoundLabelText(resHelper.getString("contReceiveObjectNumber.boundLabelText"));		
        this.contReceiveObjectNumber.setBoundLabelLength(100);		
        this.contReceiveObjectNumber.setBoundLabelUnderline(true);		
        this.contReceiveObjectNumber.setVisible(false);
        // contLastUpdateTime		
        this.contLastUpdateTime.setBoundLabelText(resHelper.getString("contLastUpdateTime.boundLabelText"));		
        this.contLastUpdateTime.setBoundLabelLength(100);		
        this.contLastUpdateTime.setBoundLabelUnderline(true);		
        this.contLastUpdateTime.setEnabled(false);		
        this.contLastUpdateTime.setVisible(false);
        // contAuditTime		
        this.contAuditTime.setBoundLabelText(resHelper.getString("contAuditTime.boundLabelText"));		
        this.contAuditTime.setBoundLabelLength(100);		
        this.contAuditTime.setBoundLabelUnderline(true);		
        this.contAuditTime.setVisible(false);		
        this.contAuditTime.setEnabled(false);
        // contLastUpdateUser		
        this.contLastUpdateUser.setBoundLabelText(resHelper.getString("contLastUpdateUser.boundLabelText"));		
        this.contLastUpdateUser.setBoundLabelLength(100);		
        this.contLastUpdateUser.setBoundLabelUnderline(true);		
        this.contLastUpdateUser.setEnabled(false);		
        this.contLastUpdateUser.setVisible(false);
        // contReceiveObjectName		
        this.contReceiveObjectName.setBoundLabelText(resHelper.getString("contReceiveObjectName.boundLabelText"));		
        this.contReceiveObjectName.setBoundLabelLength(100);		
        this.contReceiveObjectName.setBoundLabelUnderline(true);		
        this.contReceiveObjectName.setVisible(false);
        // contAuditor		
        this.contAuditor.setBoundLabelText(resHelper.getString("contAuditor.boundLabelText"));		
        this.contAuditor.setBoundLabelLength(100);		
        this.contAuditor.setBoundLabelUnderline(true);		
        this.contAuditor.setEnabled(false);		
        this.contAuditor.setVisible(false);
        // txtReceiveObjectName		
        this.txtReceiveObjectName.setVisible(false);		
        this.txtReceiveObjectName.setHorizontalAlignment(2);		
        this.txtReceiveObjectName.setMaxLength(160);		
        this.txtReceiveObjectName.setRequired(false);
        // contvehicle		
        this.contvehicle.setBoundLabelText(resHelper.getString("contvehicle.boundLabelText"));		
        this.contvehicle.setBoundLabelLength(100);		
        this.contvehicle.setBoundLabelUnderline(true);		
        this.contvehicle.setVisible(true);
        // btnTotalLines
        this.btnTotalLines.setAction((IItemAction)ActionProxyFactory.getProxy(actionTotalLines, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnTotalLines.setText(resHelper.getString("btnTotalLines.text"));
        // btnAmtClean		
        this.btnAmtClean.setText(resHelper.getString("btnAmtClean.text"));
        this.btnAmtClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    actionAmtClean_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // chkIsSendSms		
        this.chkIsSendSms.setText(resHelper.getString("chkIsSendSms.text"));		
        this.chkIsSendSms.setVisible(true);		
        this.chkIsSendSms.setHorizontalAlignment(2);
        // contcard		
        this.contcard.setBoundLabelText(resHelper.getString("contcard.boundLabelText"));		
        this.contcard.setBoundLabelLength(100);		
        this.contcard.setBoundLabelUnderline(true);		
        this.contcard.setVisible(true);
        // contreadSN		
        this.contreadSN.setBoundLabelText(resHelper.getString("contreadSN.boundLabelText"));		
        this.contreadSN.setBoundLabelLength(100);		
        this.contreadSN.setBoundLabelUnderline(true);		
        this.contreadSN.setVisible(true);
        // btnPRContent
        this.btnPRContent.setAction((IItemAction)ActionProxyFactory.getProxy(actionPRContent, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnPRContent.setText(resHelper.getString("btnPRContent.text"));
        this.btnPRContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnPRContent_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // contpaymentType		
        this.contpaymentType.setBoundLabelText(resHelper.getString("contpaymentType.boundLabelText"));		
        this.contpaymentType.setBoundLabelLength(100);		
        this.contpaymentType.setBoundLabelUnderline(true);		
        this.contpaymentType.setVisible(true);
        // contExchangeRate		
        this.contExchangeRate.setBoundLabelText(resHelper.getString("contExchangeRate.boundLabelText"));		
        this.contExchangeRate.setBoundLabelLength(100);		
        this.contExchangeRate.setBoundLabelUnderline(true);		
        this.contExchangeRate.setVisible(false);		
        this.contExchangeRate.setEnabled(false);
        // contDepartment		
        this.contDepartment.setBoundLabelText(resHelper.getString("contDepartment.boundLabelText"));		
        this.contDepartment.setBoundLabelLength(100);		
        this.contDepartment.setBoundLabelUnderline(true);		
        this.contDepartment.setVisible(true);
        // txtNumber		
        this.txtNumber.setMaxLength(80);		
        this.txtNumber.setRequired(true);
        // pkBizDate		
        this.pkBizDate.setEnabled(true);
        // txtDescription		
        this.txtDescription.setMaxLength(80);
        // pkDate		
        this.pkDate.setRequired(true);
        // Status		
        this.Status.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.pbd.BillStatusEnum").toArray());		
        this.Status.setRequired(false);		
        this.Status.setEnabled(false);
        // prmtPerson		
        this.prmtPerson.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");		
        this.prmtPerson.setEditable(true);		
        this.prmtPerson.setDisplayFormat("$name$");		
        this.prmtPerson.setEditFormat("$number$");		
        this.prmtPerson.setCommitFormat("$number$");		
        this.prmtPerson.setRequired(true);
        // prmtCurrency		
        this.prmtCurrency.setQueryInfo("com.kingdee.eas.basedata.assistant.app.CurrencyQuery");		
        this.prmtCurrency.setEditable(true);		
        this.prmtCurrency.setDisplayFormat("$name$");		
        this.prmtCurrency.setEditFormat("$number$");		
        this.prmtCurrency.setCommitFormat("$number$");		
        this.prmtCurrency.setRequired(true);
        // prmtOrgUnit		
        this.prmtOrgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.CompanyOrgUnitQuery");		
        this.prmtOrgUnit.setEditable(true);		
        this.prmtOrgUnit.setDisplayFormat("$name$");		
        this.prmtOrgUnit.setEditFormat("$number$");		
        this.prmtOrgUnit.setCommitFormat("$number$");		
        this.prmtOrgUnit.setRequired(false);
        		setOrgF7(prmtOrgUnit,com.kingdee.eas.basedata.org.OrgType.getEnum("Company"));
					
        // prmtBrand		
        this.prmtBrand.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.BrandQuery");		
        this.prmtBrand.setEditable(true);		
        this.prmtBrand.setDisplayFormat("$name$");		
        this.prmtBrand.setEditFormat("$number$");		
        this.prmtBrand.setCommitFormat("$number$");		
        this.prmtBrand.setRequired(false);		
        this.prmtBrand.setEnabled(false);
        // prmtReceiveObject		
        this.prmtReceiveObject.setRequired(true);
        this.prmtReceiveObject.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtReceiveObject_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtRemark		
        this.txtRemark.setHorizontalAlignment(2);		
        this.txtRemark.setMaxLength(255);		
        this.txtRemark.setRequired(false);
        // ReceiveObjectType		
        this.ReceiveObjectType.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.bdm.arp.ARObjTypeEnum").toArray());		
        this.ReceiveObjectType.setRequired(false);
        // txtActualPayer		
        this.txtActualPayer.setHorizontalAlignment(2);		
        this.txtActualPayer.setMaxLength(40);		
        this.txtActualPayer.setRequired(true);
        // prmtBizType		
        this.prmtBizType.setQueryInfo("com.kingdee.eas.fm.fs.app.SettBizTypeQuery");		
        this.prmtBizType.setEditable(true);		
        this.prmtBizType.setDisplayFormat("$name$");		
        this.prmtBizType.setEditFormat("$number$");		
        this.prmtBizType.setCommitFormat("$number$");		
        this.prmtBizType.setRequired(false);
        // kDPanel1
        // kDPanel4
        // kDPanel2
        // kDPanel5
        // kDPanel6
        // kdtContent
		String kdtContentStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol3\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol4\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol5\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol6\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol7\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol10\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol11\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol12\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol13\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol17\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol19\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol20\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol21\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol22\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol23\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol24\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol25\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol26\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol27\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol28\"><c:Protection locked=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"RecBillType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"-1\" /><t:Column t:key=\"TotalAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"TotalActualAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /><t:Column t:key=\"Amount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"DiscountAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /><t:Column t:key=\"ActualAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"IsMorAccount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /><t:Column t:key=\"SourceBillType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" /><t:Column t:key=\"card\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" /><t:Column t:key=\"savingCardAmt\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" t:styleID=\"sCol10\" /><t:Column t:key=\"cashCouponAmt\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" t:styleID=\"sCol11\" /><t:Column t:key=\"pointToCashAmt\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" t:styleID=\"sCol12\" /><t:Column t:key=\"PRAbateAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" t:styleID=\"sCol13\" /><t:Column t:key=\"OppAccount\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" /><t:Column t:key=\"Remark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" /><t:Column t:key=\"SourceBillINumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" /><t:Column t:key=\"SourceBillEntrySeq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"17\" t:styleID=\"sCol17\" /><t:Column t:key=\"Vehicle\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" /><t:Column t:key=\"PlateNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"19\" t:styleID=\"sCol19\" /><t:Column t:key=\"presAmt\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"20\" t:styleID=\"sCol20\" /><t:Column t:key=\"rechargeAmt\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"21\" t:styleID=\"sCol21\" /><t:Column t:key=\"SourceBillID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"22\" t:styleID=\"sCol22\" /><t:Column t:key=\"SourceBillIEntryID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"23\" t:styleID=\"sCol23\" /><t:Column t:key=\"pointAccountID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"24\" t:styleID=\"sCol24\" /><t:Column t:key=\"cashAccountID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"25\" t:styleID=\"sCol25\" /><t:Column t:key=\"isPointToCash\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"26\" t:styleID=\"sCol26\" /><t:Column t:key=\"isUseCashAccount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"27\" t:styleID=\"sCol27\" /><t:Column t:key=\"isPreReceive\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"28\" t:styleID=\"sCol28\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{RecBillType}</t:Cell><t:Cell>$Resource{TotalAmount}</t:Cell><t:Cell>$Resource{TotalActualAmount}</t:Cell><t:Cell>$Resource{Amount}</t:Cell><t:Cell>$Resource{DiscountAmount}</t:Cell><t:Cell>$Resource{ActualAmount}</t:Cell><t:Cell>$Resource{IsMorAccount}</t:Cell><t:Cell>$Resource{SourceBillType}</t:Cell><t:Cell>$Resource{card}</t:Cell><t:Cell>$Resource{savingCardAmt}</t:Cell><t:Cell>$Resource{cashCouponAmt}</t:Cell><t:Cell>$Resource{pointToCashAmt}</t:Cell><t:Cell>$Resource{PRAbateAmount}</t:Cell><t:Cell>$Resource{OppAccount}</t:Cell><t:Cell>$Resource{Remark}</t:Cell><t:Cell>$Resource{SourceBillINumber}</t:Cell><t:Cell>$Resource{SourceBillEntrySeq}</t:Cell><t:Cell>$Resource{Vehicle}</t:Cell><t:Cell>$Resource{PlateNum}</t:Cell><t:Cell>$Resource{presAmt}</t:Cell><t:Cell>$Resource{rechargeAmt}</t:Cell><t:Cell>$Resource{SourceBillID}</t:Cell><t:Cell>$Resource{SourceBillIEntryID}</t:Cell><t:Cell>$Resource{pointAccountID}</t:Cell><t:Cell>$Resource{cashAccountID}</t:Cell><t:Cell>$Resource{isPointToCash}</t:Cell><t:Cell>$Resource{isUseCashAccount}</t:Cell><t:Cell>$Resource{isPreReceive}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtContent.setFormatXml(resHelper.translateString("kdtContent",kdtContentStrXML));
        kdtContent.addKDTEditListener(new KDTEditAdapter() {
		public void editStopped(KDTEditEvent e) {
			try {
				kdtContent_Changed(e.getRowIndex(),e.getColIndex());
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        this.kdtContent.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtContent_editStopping(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtContent_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtContent.putBindContents("editData",new String[] {"seq","RecBillType","TotalAmount","TotalActualAmount","Amount","DiscountAmount","ActualAmount","IsMorAccount","SourceBillType","card","savingCardAmt","cashCouponAmt","pointToCashAmt","PRAbateAmount","OppAccount","Remark","SourceBillINumber","SourceBillEntrySeq","Vehicle","Vehicle.plateNum","presAmt","rechargeAmt","SourceBillID","SourceBillIEntryID","pointAccountID","cashAccountID","isPointToCash","isUseCashAccount","isPreReceive"});


        this.kdtContent.checkParsed();
        final KDBizPromptBox kdtContent_RecBillType_PromptBox = new KDBizPromptBox();
        kdtContent_RecBillType_PromptBox.setQueryInfo("com.kingdee.eas.fi.cas.ReceivingBillTypeQuery");
        kdtContent_RecBillType_PromptBox.setVisible(true);
        kdtContent_RecBillType_PromptBox.setEditable(true);
        kdtContent_RecBillType_PromptBox.setDisplayFormat("$number$");
        kdtContent_RecBillType_PromptBox.setEditFormat("$number$");
        kdtContent_RecBillType_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtContent_RecBillType_CellEditor = new KDTDefaultCellEditor(kdtContent_RecBillType_PromptBox);
        this.kdtContent.getColumn("RecBillType").setEditor(kdtContent_RecBillType_CellEditor);
        ObjectValueRender kdtContent_RecBillType_OVR = new ObjectValueRender();
        kdtContent_RecBillType_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtContent.getColumn("RecBillType").setRenderer(kdtContent_RecBillType_OVR);
        KDFormattedTextField kdtContent_Amount_TextField = new KDFormattedTextField();
        kdtContent_Amount_TextField.setName("kdtContent_Amount_TextField");
        kdtContent_Amount_TextField.setVisible(true);
        kdtContent_Amount_TextField.setEditable(true);
        kdtContent_Amount_TextField.setHorizontalAlignment(2);
        kdtContent_Amount_TextField.setDataType(1);
        	kdtContent_Amount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtContent_Amount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtContent_Amount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtContent_Amount_CellEditor = new KDTDefaultCellEditor(kdtContent_Amount_TextField);
        this.kdtContent.getColumn("Amount").setEditor(kdtContent_Amount_CellEditor);
        KDFormattedTextField kdtContent_DiscountAmount_TextField = new KDFormattedTextField();
        kdtContent_DiscountAmount_TextField.setName("kdtContent_DiscountAmount_TextField");
        kdtContent_DiscountAmount_TextField.setVisible(true);
        kdtContent_DiscountAmount_TextField.setEditable(true);
        kdtContent_DiscountAmount_TextField.setHorizontalAlignment(2);
        kdtContent_DiscountAmount_TextField.setDataType(1);
        	kdtContent_DiscountAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtContent_DiscountAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtContent_DiscountAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtContent_DiscountAmount_CellEditor = new KDTDefaultCellEditor(kdtContent_DiscountAmount_TextField);
        this.kdtContent.getColumn("DiscountAmount").setEditor(kdtContent_DiscountAmount_CellEditor);
        KDFormattedTextField kdtContent_ActualAmount_TextField = new KDFormattedTextField();
        kdtContent_ActualAmount_TextField.setName("kdtContent_ActualAmount_TextField");
        kdtContent_ActualAmount_TextField.setVisible(true);
        kdtContent_ActualAmount_TextField.setEditable(true);
        kdtContent_ActualAmount_TextField.setHorizontalAlignment(2);
        kdtContent_ActualAmount_TextField.setDataType(1);
        	kdtContent_ActualAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtContent_ActualAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtContent_ActualAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtContent_ActualAmount_CellEditor = new KDTDefaultCellEditor(kdtContent_ActualAmount_TextField);
        this.kdtContent.getColumn("ActualAmount").setEditor(kdtContent_ActualAmount_CellEditor);
        KDCheckBox kdtContent_IsMorAccount_CheckBox = new KDCheckBox();
        kdtContent_IsMorAccount_CheckBox.setName("kdtContent_IsMorAccount_CheckBox");
        KDTDefaultCellEditor kdtContent_IsMorAccount_CellEditor = new KDTDefaultCellEditor(kdtContent_IsMorAccount_CheckBox);
        this.kdtContent.getColumn("IsMorAccount").setEditor(kdtContent_IsMorAccount_CellEditor);
        final KDBizPromptBox kdtContent_SourceBillType_PromptBox = new KDBizPromptBox();
        kdtContent_SourceBillType_PromptBox.setQueryInfo("com.kingdee.eas.basedata.scm.common.app.F7BillTypeQuery");
        kdtContent_SourceBillType_PromptBox.setVisible(true);
        kdtContent_SourceBillType_PromptBox.setEditable(true);
        kdtContent_SourceBillType_PromptBox.setDisplayFormat("$number$");
        kdtContent_SourceBillType_PromptBox.setEditFormat("$number$");
        kdtContent_SourceBillType_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtContent_SourceBillType_CellEditor = new KDTDefaultCellEditor(kdtContent_SourceBillType_PromptBox);
        this.kdtContent.getColumn("SourceBillType").setEditor(kdtContent_SourceBillType_CellEditor);
        ObjectValueRender kdtContent_SourceBillType_OVR = new ObjectValueRender();
        kdtContent_SourceBillType_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtContent.getColumn("SourceBillType").setRenderer(kdtContent_SourceBillType_OVR);
        final KDBizPromptBox kdtContent_card_PromptBox = new KDBizPromptBox();
        kdtContent_card_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.vip.mb.app.CardQuery");
        kdtContent_card_PromptBox.setVisible(true);
        kdtContent_card_PromptBox.setEditable(true);
        kdtContent_card_PromptBox.setDisplayFormat("$number$");
        kdtContent_card_PromptBox.setEditFormat("$number$");
        kdtContent_card_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtContent_card_CellEditor = new KDTDefaultCellEditor(kdtContent_card_PromptBox);
        this.kdtContent.getColumn("card").setEditor(kdtContent_card_CellEditor);
        ObjectValueRender kdtContent_card_OVR = new ObjectValueRender();
        kdtContent_card_OVR.setFormat(new BizDataFormat("$number$"));
        this.kdtContent.getColumn("card").setRenderer(kdtContent_card_OVR);
        KDFormattedTextField kdtContent_savingCardAmt_TextField = new KDFormattedTextField();
        kdtContent_savingCardAmt_TextField.setName("kdtContent_savingCardAmt_TextField");
        kdtContent_savingCardAmt_TextField.setVisible(true);
        kdtContent_savingCardAmt_TextField.setEditable(true);
        kdtContent_savingCardAmt_TextField.setHorizontalAlignment(2);
        kdtContent_savingCardAmt_TextField.setDataType(1);
        	kdtContent_savingCardAmt_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtContent_savingCardAmt_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtContent_savingCardAmt_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtContent_savingCardAmt_CellEditor = new KDTDefaultCellEditor(kdtContent_savingCardAmt_TextField);
        this.kdtContent.getColumn("savingCardAmt").setEditor(kdtContent_savingCardAmt_CellEditor);
        KDFormattedTextField kdtContent_cashCouponAmt_TextField = new KDFormattedTextField();
        kdtContent_cashCouponAmt_TextField.setName("kdtContent_cashCouponAmt_TextField");
        kdtContent_cashCouponAmt_TextField.setVisible(true);
        kdtContent_cashCouponAmt_TextField.setEditable(true);
        kdtContent_cashCouponAmt_TextField.setHorizontalAlignment(2);
        kdtContent_cashCouponAmt_TextField.setDataType(1);
        	kdtContent_cashCouponAmt_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtContent_cashCouponAmt_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtContent_cashCouponAmt_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtContent_cashCouponAmt_CellEditor = new KDTDefaultCellEditor(kdtContent_cashCouponAmt_TextField);
        this.kdtContent.getColumn("cashCouponAmt").setEditor(kdtContent_cashCouponAmt_CellEditor);
        KDFormattedTextField kdtContent_pointToCashAmt_TextField = new KDFormattedTextField();
        kdtContent_pointToCashAmt_TextField.setName("kdtContent_pointToCashAmt_TextField");
        kdtContent_pointToCashAmt_TextField.setVisible(true);
        kdtContent_pointToCashAmt_TextField.setEditable(true);
        kdtContent_pointToCashAmt_TextField.setHorizontalAlignment(2);
        kdtContent_pointToCashAmt_TextField.setDataType(1);
        	kdtContent_pointToCashAmt_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtContent_pointToCashAmt_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtContent_pointToCashAmt_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtContent_pointToCashAmt_CellEditor = new KDTDefaultCellEditor(kdtContent_pointToCashAmt_TextField);
        this.kdtContent.getColumn("pointToCashAmt").setEditor(kdtContent_pointToCashAmt_CellEditor);
        KDFormattedTextField kdtContent_PRAbateAmount_TextField = new KDFormattedTextField();
        kdtContent_PRAbateAmount_TextField.setName("kdtContent_PRAbateAmount_TextField");
        kdtContent_PRAbateAmount_TextField.setVisible(true);
        kdtContent_PRAbateAmount_TextField.setEditable(true);
        kdtContent_PRAbateAmount_TextField.setHorizontalAlignment(2);
        kdtContent_PRAbateAmount_TextField.setDataType(1);
        	kdtContent_PRAbateAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtContent_PRAbateAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtContent_PRAbateAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtContent_PRAbateAmount_CellEditor = new KDTDefaultCellEditor(kdtContent_PRAbateAmount_TextField);
        this.kdtContent.getColumn("PRAbateAmount").setEditor(kdtContent_PRAbateAmount_CellEditor);
        final KDBizPromptBox kdtContent_OppAccount_PromptBox = new KDBizPromptBox();
        kdtContent_OppAccount_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.account.app.F7AccountViewQuery");
        kdtContent_OppAccount_PromptBox.setVisible(true);
        kdtContent_OppAccount_PromptBox.setEditable(true);
        kdtContent_OppAccount_PromptBox.setDisplayFormat("$number$");
        kdtContent_OppAccount_PromptBox.setEditFormat("$number$");
        kdtContent_OppAccount_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtContent_OppAccount_CellEditor = new KDTDefaultCellEditor(kdtContent_OppAccount_PromptBox);
        this.kdtContent.getColumn("OppAccount").setEditor(kdtContent_OppAccount_CellEditor);
        ObjectValueRender kdtContent_OppAccount_OVR = new ObjectValueRender();
        kdtContent_OppAccount_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtContent.getColumn("OppAccount").setRenderer(kdtContent_OppAccount_OVR);
        KDTextField kdtContent_Remark_TextField = new KDTextField();
        kdtContent_Remark_TextField.setName("kdtContent_Remark_TextField");
        kdtContent_Remark_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtContent_Remark_CellEditor = new KDTDefaultCellEditor(kdtContent_Remark_TextField);
        this.kdtContent.getColumn("Remark").setEditor(kdtContent_Remark_CellEditor);
        KDTextField kdtContent_SourceBillINumber_TextField = new KDTextField();
        kdtContent_SourceBillINumber_TextField.setName("kdtContent_SourceBillINumber_TextField");
        kdtContent_SourceBillINumber_TextField.setMaxLength(160);
        KDTDefaultCellEditor kdtContent_SourceBillINumber_CellEditor = new KDTDefaultCellEditor(kdtContent_SourceBillINumber_TextField);
        this.kdtContent.getColumn("SourceBillINumber").setEditor(kdtContent_SourceBillINumber_CellEditor);
        KDFormattedTextField kdtContent_SourceBillEntrySeq_TextField = new KDFormattedTextField();
        kdtContent_SourceBillEntrySeq_TextField.setName("kdtContent_SourceBillEntrySeq_TextField");
        kdtContent_SourceBillEntrySeq_TextField.setVisible(true);
        kdtContent_SourceBillEntrySeq_TextField.setEditable(true);
        kdtContent_SourceBillEntrySeq_TextField.setHorizontalAlignment(2);
        kdtContent_SourceBillEntrySeq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtContent_SourceBillEntrySeq_CellEditor = new KDTDefaultCellEditor(kdtContent_SourceBillEntrySeq_TextField);
        this.kdtContent.getColumn("SourceBillEntrySeq").setEditor(kdtContent_SourceBillEntrySeq_CellEditor);
        final KDBizPromptBox kdtContent_Vehicle_PromptBox = new KDBizPromptBox();
        kdtContent_Vehicle_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.VehicleF7Query");
        kdtContent_Vehicle_PromptBox.setVisible(true);
        kdtContent_Vehicle_PromptBox.setEditable(true);
        kdtContent_Vehicle_PromptBox.setDisplayFormat("$number$");
        kdtContent_Vehicle_PromptBox.setEditFormat("$number$");
        kdtContent_Vehicle_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtContent_Vehicle_CellEditor = new KDTDefaultCellEditor(kdtContent_Vehicle_PromptBox);
        this.kdtContent.getColumn("Vehicle").setEditor(kdtContent_Vehicle_CellEditor);
        ObjectValueRender kdtContent_Vehicle_OVR = new ObjectValueRender();
        kdtContent_Vehicle_OVR.setFormat(new BizDataFormat("$vIN$"));
        this.kdtContent.getColumn("Vehicle").setRenderer(kdtContent_Vehicle_OVR);
        KDTextField kdtContent_PlateNum_TextField = new KDTextField();
        kdtContent_PlateNum_TextField.setName("kdtContent_PlateNum_TextField");
        kdtContent_PlateNum_TextField.setMaxLength(44);
        KDTDefaultCellEditor kdtContent_PlateNum_CellEditor = new KDTDefaultCellEditor(kdtContent_PlateNum_TextField);
        this.kdtContent.getColumn("PlateNum").setEditor(kdtContent_PlateNum_CellEditor);
        KDFormattedTextField kdtContent_presAmt_TextField = new KDFormattedTextField();
        kdtContent_presAmt_TextField.setName("kdtContent_presAmt_TextField");
        kdtContent_presAmt_TextField.setVisible(true);
        kdtContent_presAmt_TextField.setEditable(true);
        kdtContent_presAmt_TextField.setHorizontalAlignment(2);
        kdtContent_presAmt_TextField.setDataType(1);
        	kdtContent_presAmt_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtContent_presAmt_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtContent_presAmt_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtContent_presAmt_CellEditor = new KDTDefaultCellEditor(kdtContent_presAmt_TextField);
        this.kdtContent.getColumn("presAmt").setEditor(kdtContent_presAmt_CellEditor);
        KDFormattedTextField kdtContent_rechargeAmt_TextField = new KDFormattedTextField();
        kdtContent_rechargeAmt_TextField.setName("kdtContent_rechargeAmt_TextField");
        kdtContent_rechargeAmt_TextField.setVisible(true);
        kdtContent_rechargeAmt_TextField.setEditable(true);
        kdtContent_rechargeAmt_TextField.setHorizontalAlignment(2);
        kdtContent_rechargeAmt_TextField.setDataType(1);
        	kdtContent_rechargeAmt_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtContent_rechargeAmt_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtContent_rechargeAmt_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtContent_rechargeAmt_CellEditor = new KDTDefaultCellEditor(kdtContent_rechargeAmt_TextField);
        this.kdtContent.getColumn("rechargeAmt").setEditor(kdtContent_rechargeAmt_CellEditor);
        KDTextField kdtContent_SourceBillID_TextField = new KDTextField();
        kdtContent_SourceBillID_TextField.setName("kdtContent_SourceBillID_TextField");
        kdtContent_SourceBillID_TextField.setMaxLength(44);
        KDTDefaultCellEditor kdtContent_SourceBillID_CellEditor = new KDTDefaultCellEditor(kdtContent_SourceBillID_TextField);
        this.kdtContent.getColumn("SourceBillID").setEditor(kdtContent_SourceBillID_CellEditor);
        KDTextField kdtContent_SourceBillIEntryID_TextField = new KDTextField();
        kdtContent_SourceBillIEntryID_TextField.setName("kdtContent_SourceBillIEntryID_TextField");
        kdtContent_SourceBillIEntryID_TextField.setMaxLength(44);
        KDTDefaultCellEditor kdtContent_SourceBillIEntryID_CellEditor = new KDTDefaultCellEditor(kdtContent_SourceBillIEntryID_TextField);
        this.kdtContent.getColumn("SourceBillIEntryID").setEditor(kdtContent_SourceBillIEntryID_CellEditor);
        KDTextField kdtContent_pointAccountID_TextField = new KDTextField();
        kdtContent_pointAccountID_TextField.setName("kdtContent_pointAccountID_TextField");
        kdtContent_pointAccountID_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtContent_pointAccountID_CellEditor = new KDTDefaultCellEditor(kdtContent_pointAccountID_TextField);
        this.kdtContent.getColumn("pointAccountID").setEditor(kdtContent_pointAccountID_CellEditor);
        KDTextField kdtContent_cashAccountID_TextField = new KDTextField();
        kdtContent_cashAccountID_TextField.setName("kdtContent_cashAccountID_TextField");
        kdtContent_cashAccountID_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtContent_cashAccountID_CellEditor = new KDTDefaultCellEditor(kdtContent_cashAccountID_TextField);
        this.kdtContent.getColumn("cashAccountID").setEditor(kdtContent_cashAccountID_CellEditor);
        KDCheckBox kdtContent_isPointToCash_CheckBox = new KDCheckBox();
        kdtContent_isPointToCash_CheckBox.setName("kdtContent_isPointToCash_CheckBox");
        KDTDefaultCellEditor kdtContent_isPointToCash_CellEditor = new KDTDefaultCellEditor(kdtContent_isPointToCash_CheckBox);
        this.kdtContent.getColumn("isPointToCash").setEditor(kdtContent_isPointToCash_CellEditor);
        KDCheckBox kdtContent_isUseCashAccount_CheckBox = new KDCheckBox();
        kdtContent_isUseCashAccount_CheckBox.setName("kdtContent_isUseCashAccount_CheckBox");
        KDTDefaultCellEditor kdtContent_isUseCashAccount_CellEditor = new KDTDefaultCellEditor(kdtContent_isUseCashAccount_CheckBox);
        this.kdtContent.getColumn("isUseCashAccount").setEditor(kdtContent_isUseCashAccount_CellEditor);
        KDCheckBox kdtContent_isPreReceive_CheckBox = new KDCheckBox();
        kdtContent_isPreReceive_CheckBox.setName("kdtContent_isPreReceive_CheckBox");
        KDTDefaultCellEditor kdtContent_isPreReceive_CellEditor = new KDTDefaultCellEditor(kdtContent_isPreReceive_CheckBox);
        this.kdtContent.getColumn("isPreReceive").setEditor(kdtContent_isPreReceive_CellEditor);
        // btnMoveTypeTop
        this.btnMoveTypeTop.setAction((IItemAction)ActionProxyFactory.getProxy(actionMoveReceivingTop, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnMoveTypeTop.setDisabledIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_movetop"));
        // btnMoveTypeDown
        this.btnMoveTypeDown.setAction((IItemAction)ActionProxyFactory.getProxy(actionMoveReceivingDown, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnMoveTypeDown.setDisabledIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_movedown"));
        // kDPanel3
        // kdtType
		String kdtTypeStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol2\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol5\"><c:Protection locked=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"SettlementType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"1\" /><t:Column t:key=\"ActRecAmt\" t:width=\"120\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"PayeeAccount\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"true\" t:index=\"3\" /><t:Column t:key=\"PayeeAccountBank\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" /><t:Column t:key=\"Bank\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /><t:Column t:key=\"SettlementNumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{SettlementType}</t:Cell><t:Cell>$Resource{ActRecAmt}</t:Cell><t:Cell>$Resource{PayeeAccount}</t:Cell><t:Cell>$Resource{PayeeAccountBank}</t:Cell><t:Cell>$Resource{Bank}</t:Cell><t:Cell>$Resource{SettlementNumber}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtType.setFormatXml(resHelper.translateString("kdtType",kdtTypeStrXML));
        kdtType.addKDTEditListener(new KDTEditAdapter() {
		public void editStopped(KDTEditEvent e) {
			try {
				kdtType_Changed(e.getRowIndex(),e.getColIndex());
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        this.kdtType.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtType_editStopping(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
            public void editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtType_editStopped(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtType.putBindContents("editData",new String[] {"seq","SettlementType","ActRecAmt","PayeeAccount","PayeeAccountBank","PayeeAccountBank.bank","SettlementNumber"});


        this.kdtType.checkParsed();
        final KDBizPromptBox kdtType_SettlementType_PromptBox = new KDBizPromptBox();
        kdtType_SettlementType_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.SettlementTypeQuery");
        kdtType_SettlementType_PromptBox.setVisible(true);
        kdtType_SettlementType_PromptBox.setEditable(true);
        kdtType_SettlementType_PromptBox.setDisplayFormat("$number$");
        kdtType_SettlementType_PromptBox.setEditFormat("$number$");
        kdtType_SettlementType_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtType_SettlementType_CellEditor = new KDTDefaultCellEditor(kdtType_SettlementType_PromptBox);
        this.kdtType.getColumn("SettlementType").setEditor(kdtType_SettlementType_CellEditor);
        ObjectValueRender kdtType_SettlementType_OVR = new ObjectValueRender();
        kdtType_SettlementType_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtType.getColumn("SettlementType").setRenderer(kdtType_SettlementType_OVR);
        KDFormattedTextField kdtType_ActRecAmt_TextField = new KDFormattedTextField();
        kdtType_ActRecAmt_TextField.setName("kdtType_ActRecAmt_TextField");
        kdtType_ActRecAmt_TextField.setVisible(true);
        kdtType_ActRecAmt_TextField.setEditable(true);
        kdtType_ActRecAmt_TextField.setHorizontalAlignment(2);
        kdtType_ActRecAmt_TextField.setDataType(1);
        	kdtType_ActRecAmt_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtType_ActRecAmt_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtType_ActRecAmt_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtType_ActRecAmt_CellEditor = new KDTDefaultCellEditor(kdtType_ActRecAmt_TextField);
        this.kdtType.getColumn("ActRecAmt").setEditor(kdtType_ActRecAmt_CellEditor);
        final KDBizPromptBox kdtType_PayeeAccount_PromptBox = new KDBizPromptBox();
        kdtType_PayeeAccount_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.account.app.F7AccountViewQuery");
        kdtType_PayeeAccount_PromptBox.setVisible(true);
        kdtType_PayeeAccount_PromptBox.setEditable(true);
        kdtType_PayeeAccount_PromptBox.setDisplayFormat("$number$");
        kdtType_PayeeAccount_PromptBox.setEditFormat("$number$");
        kdtType_PayeeAccount_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtType_PayeeAccount_CellEditor = new KDTDefaultCellEditor(kdtType_PayeeAccount_PromptBox);
        this.kdtType.getColumn("PayeeAccount").setEditor(kdtType_PayeeAccount_CellEditor);
        ObjectValueRender kdtType_PayeeAccount_OVR = new ObjectValueRender();
        kdtType_PayeeAccount_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtType.getColumn("PayeeAccount").setRenderer(kdtType_PayeeAccount_OVR);
        final KDBizPromptBox kdtType_PayeeAccountBank_PromptBox = new KDBizPromptBox();
        kdtType_PayeeAccountBank_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7AccountBankQuery");
        kdtType_PayeeAccountBank_PromptBox.setVisible(true);
        kdtType_PayeeAccountBank_PromptBox.setEditable(true);
        kdtType_PayeeAccountBank_PromptBox.setDisplayFormat("$number$");
        kdtType_PayeeAccountBank_PromptBox.setEditFormat("$number$");
        kdtType_PayeeAccountBank_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtType_PayeeAccountBank_CellEditor = new KDTDefaultCellEditor(kdtType_PayeeAccountBank_PromptBox);
        this.kdtType.getColumn("PayeeAccountBank").setEditor(kdtType_PayeeAccountBank_CellEditor);
        ObjectValueRender kdtType_PayeeAccountBank_OVR = new ObjectValueRender();
        kdtType_PayeeAccountBank_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtType.getColumn("PayeeAccountBank").setRenderer(kdtType_PayeeAccountBank_OVR);
        final KDBizPromptBox kdtType_Bank_PromptBox = new KDBizPromptBox();
        kdtType_Bank_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7BankQuery");
        kdtType_Bank_PromptBox.setVisible(true);
        kdtType_Bank_PromptBox.setEditable(true);
        kdtType_Bank_PromptBox.setDisplayFormat("$number$");
        kdtType_Bank_PromptBox.setEditFormat("$number$");
        kdtType_Bank_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtType_Bank_CellEditor = new KDTDefaultCellEditor(kdtType_Bank_PromptBox);
        this.kdtType.getColumn("Bank").setEditor(kdtType_Bank_CellEditor);
        ObjectValueRender kdtType_Bank_OVR = new ObjectValueRender();
        kdtType_Bank_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtType.getColumn("Bank").setRenderer(kdtType_Bank_OVR);
        KDTextField kdtType_SettlementNumber_TextField = new KDTextField();
        kdtType_SettlementNumber_TextField.setName("kdtType_SettlementNumber_TextField");
        kdtType_SettlementNumber_TextField.setMaxLength(160);
        KDTDefaultCellEditor kdtType_SettlementNumber_CellEditor = new KDTDefaultCellEditor(kdtType_SettlementNumber_TextField);
        this.kdtType.getColumn("SettlementNumber").setEditor(kdtType_SettlementNumber_CellEditor);
        // btnMoveReceivingTop
        this.btnMoveReceivingTop.setAction((IItemAction)ActionProxyFactory.getProxy(actionMoveTypeTop, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnMoveReceivingTop.setDisabledIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_movetop"));
        this.btnMoveReceivingTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnMoveReceivingTop_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // btnMoveReceivingDown
        this.btnMoveReceivingDown.setAction((IItemAction)ActionProxyFactory.getProxy(actionMoveTypeDown, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnMoveReceivingDown.setDisabledIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_movedown"));
        this.btnMoveReceivingDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnMoveReceivingDown_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // btnScoreCard
        this.btnScoreCard.setAction((IItemAction)ActionProxyFactory.getProxy(actionScoreCard, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnScoreCard.setText(resHelper.getString("btnScoreCard.text"));
        // btnGiftCard
        this.btnGiftCard.setAction((IItemAction)ActionProxyFactory.getProxy(actionGiftCard, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnGiftCard.setText(resHelper.getString("btnGiftCard.text"));
        // btnSavingsCard
        this.btnSavingsCard.setAction((IItemAction)ActionProxyFactory.getProxy(actionSavingsCard, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnSavingsCard.setText(resHelper.getString("btnSavingsCard.text"));
        this.btnSavingsCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnSavingsCard_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // contreceivingAmt		
        this.contreceivingAmt.setBoundLabelText(resHelper.getString("contreceivingAmt.boundLabelText"));		
        this.contreceivingAmt.setBoundLabelLength(100);		
        this.contreceivingAmt.setBoundLabelUnderline(true);		
        this.contreceivingAmt.setVisible(true);
        // contPRAbateAmt		
        this.contPRAbateAmt.setBoundLabelText(resHelper.getString("contPRAbateAmt.boundLabelText"));		
        this.contPRAbateAmt.setBoundLabelLength(100);		
        this.contPRAbateAmt.setBoundLabelUnderline(true);		
        this.contPRAbateAmt.setVisible(true);
        // contcashCouponAmt		
        this.contcashCouponAmt.setBoundLabelText(resHelper.getString("contcashCouponAmt.boundLabelText"));		
        this.contcashCouponAmt.setBoundLabelLength(100);		
        this.contcashCouponAmt.setBoundLabelUnderline(true);		
        this.contcashCouponAmt.setVisible(true);
        // contpointToCashAmt		
        this.contpointToCashAmt.setBoundLabelText(resHelper.getString("contpointToCashAmt.boundLabelText"));		
        this.contpointToCashAmt.setBoundLabelLength(100);		
        this.contpointToCashAmt.setBoundLabelUnderline(true);		
        this.contpointToCashAmt.setVisible(true);
        // contamount		
        this.contamount.setBoundLabelText(resHelper.getString("contamount.boundLabelText"));		
        this.contamount.setBoundLabelLength(100);		
        this.contamount.setBoundLabelUnderline(true);		
        this.contamount.setVisible(true);
        // btnPRWriteOff
        this.btnPRWriteOff.setAction((IItemAction)ActionProxyFactory.getProxy(actionPRWriteOff, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnPRWriteOff.setText(resHelper.getString("btnPRWriteOff.text"));
        // contsavingCardAmt		
        this.contsavingCardAmt.setBoundLabelText(resHelper.getString("contsavingCardAmt.boundLabelText"));		
        this.contsavingCardAmt.setBoundLabelLength(100);		
        this.contsavingCardAmt.setBoundLabelUnderline(true);		
        this.contsavingCardAmt.setVisible(true);
        // txtreceivingAmt		
        this.txtreceivingAmt.setHorizontalAlignment(2);		
        this.txtreceivingAmt.setDataType(1);		
        this.txtreceivingAmt.setSupportedEmpty(true);		
        this.txtreceivingAmt.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtreceivingAmt.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtreceivingAmt.setPrecision(2);		
        this.txtreceivingAmt.setRequired(false);		
        this.txtreceivingAmt.setEnabled(false);
        this.txtreceivingAmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    txtreceivingAmt_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // txtPRAbateAmt		
        this.txtPRAbateAmt.setHorizontalAlignment(2);		
        this.txtPRAbateAmt.setDataType(1);		
        this.txtPRAbateAmt.setSupportedEmpty(true);		
        this.txtPRAbateAmt.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtPRAbateAmt.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtPRAbateAmt.setPrecision(2);		
        this.txtPRAbateAmt.setRequired(false);		
        this.txtPRAbateAmt.setEnabled(false);
        // txtcashCouponAmt		
        this.txtcashCouponAmt.setHorizontalAlignment(2);		
        this.txtcashCouponAmt.setDataType(1);		
        this.txtcashCouponAmt.setSupportedEmpty(true);		
        this.txtcashCouponAmt.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtcashCouponAmt.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtcashCouponAmt.setPrecision(2);		
        this.txtcashCouponAmt.setRequired(false);		
        this.txtcashCouponAmt.setEnabled(false);
        // txtpointToCashAmt		
        this.txtpointToCashAmt.setHorizontalAlignment(2);		
        this.txtpointToCashAmt.setDataType(1);		
        this.txtpointToCashAmt.setSupportedEmpty(true);		
        this.txtpointToCashAmt.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtpointToCashAmt.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtpointToCashAmt.setPrecision(2);		
        this.txtpointToCashAmt.setRequired(false);		
        this.txtpointToCashAmt.setEnabled(false);
        // txtamount		
        this.txtamount.setHorizontalAlignment(2);		
        this.txtamount.setDataType(1);		
        this.txtamount.setSupportedEmpty(true);		
        this.txtamount.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtamount.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtamount.setPrecision(2);		
        this.txtamount.setRequired(false);		
        this.txtamount.setEnabled(false);
        // txtsavingCardAmt		
        this.txtsavingCardAmt.setHorizontalAlignment(2);		
        this.txtsavingCardAmt.setDataType(1);		
        this.txtsavingCardAmt.setSupportedEmpty(true);		
        this.txtsavingCardAmt.setMinimumValue( new java.math.BigDecimal("-1.0E18"));		
        this.txtsavingCardAmt.setMaximumValue( new java.math.BigDecimal("1.0E18"));		
        this.txtsavingCardAmt.setPrecision(2);		
        this.txtsavingCardAmt.setRequired(false);		
        this.txtsavingCardAmt.setEnabled(false);
        // kdtEntrys
		String kdtEntrysStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol1\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol3\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol4\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol5\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol6\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol7\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol8\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol9\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol10\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol11\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol12\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol13\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol14\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol15\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol16\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol17\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol18\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol19\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol20\"><c:Protection locked=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"RecBillType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol1\" /><t:Column t:key=\"Amount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol2\" /><t:Column t:key=\"DiscountAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol3\" /><t:Column t:key=\"ActualAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol4\" /><t:Column t:key=\"SettlementType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol5\" /><t:Column t:key=\"PayeeAccount\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" t:styleID=\"sCol6\" /><t:Column t:key=\"OppAccount\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" t:styleID=\"sCol7\" /><t:Column t:key=\"PayeeAccountBank\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" t:styleID=\"sCol8\" /><t:Column t:key=\"Bank\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" t:styleID=\"sCol9\" /><t:Column t:key=\"SettlementNumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" t:styleID=\"sCol10\" /><t:Column t:key=\"LocalAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" t:styleID=\"sCol11\" /><t:Column t:key=\"LocalActualAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" t:styleID=\"sCol12\" /><t:Column t:key=\"Remark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" t:styleID=\"sCol13\" /><t:Column t:key=\"SourceBillIEntryID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" t:styleID=\"sCol14\" /><t:Column t:key=\"SourceBillID\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" t:styleID=\"sCol15\" /><t:Column t:key=\"SourceBillType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" t:styleID=\"sCol16\" /><t:Column t:key=\"SourceBillINumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"17\" t:styleID=\"sCol17\" /><t:Column t:key=\"SourceBillEntrySeq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" t:styleID=\"sCol18\" /><t:Column t:key=\"Vehicle\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"19\" t:styleID=\"sCol19\" /><t:Column t:key=\"PlateNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"20\" t:styleID=\"sCol20\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{RecBillType}</t:Cell><t:Cell>$Resource{Amount}</t:Cell><t:Cell>$Resource{DiscountAmount}</t:Cell><t:Cell>$Resource{ActualAmount}</t:Cell><t:Cell>$Resource{SettlementType}</t:Cell><t:Cell>$Resource{PayeeAccount}</t:Cell><t:Cell>$Resource{OppAccount}</t:Cell><t:Cell>$Resource{PayeeAccountBank}</t:Cell><t:Cell>$Resource{Bank}</t:Cell><t:Cell>$Resource{SettlementNumber}</t:Cell><t:Cell>$Resource{LocalAmount}</t:Cell><t:Cell>$Resource{LocalActualAmount}</t:Cell><t:Cell>$Resource{Remark}</t:Cell><t:Cell>$Resource{SourceBillIEntryID}</t:Cell><t:Cell>$Resource{SourceBillID}</t:Cell><t:Cell>$Resource{SourceBillType}</t:Cell><t:Cell>$Resource{SourceBillINumber}</t:Cell><t:Cell>$Resource{SourceBillEntrySeq}</t:Cell><t:Cell>$Resource{Vehicle}</t:Cell><t:Cell>$Resource{PlateNum}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtEntrys.setFormatXml(resHelper.translateString("kdtEntrys",kdtEntrysStrXML));
        kdtEntrys.addKDTEditListener(new KDTEditAdapter() {
		public void editStopped(KDTEditEvent e) {
			try {
				kdtEntrys_Changed(e.getRowIndex(),e.getColIndex());
			}
			catch (Exception exc) {
				handUIException(exc);
			}
		}
	});

        this.kdtEntrys.addKDTEditListener(new com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter() {
            public void editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) {
                try {
                    kdtEntrys_editStopping(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });

                this.kdtEntrys.putBindContents("editData",new String[] {"id","RecBillType","Amount","DiscountAmount","ActualAmount","SettlementType","PayeeAccount","OppAccount","PayeeAccountBank","PayeeAccountBank.bank","SettlementNumber","LocalAmount","LocalActualAmount","Remark","SourceBillIEntryID","SourceBillID","SourceBillType","SourceBillINumber","SourceBillEntrySeq","Vehicle","Vehicle.plateNum"});


        this.kdtEntrys.checkParsed();
        final KDBizPromptBox kdtEntrys_RecBillType_PromptBox = new KDBizPromptBox();
        kdtEntrys_RecBillType_PromptBox.setQueryInfo("com.kingdee.eas.fi.cas.ReceivingBillTypeQuery");
        kdtEntrys_RecBillType_PromptBox.setVisible(true);
        kdtEntrys_RecBillType_PromptBox.setEditable(true);
        kdtEntrys_RecBillType_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_RecBillType_PromptBox.setEditFormat("$number$");
        kdtEntrys_RecBillType_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_RecBillType_CellEditor = new KDTDefaultCellEditor(kdtEntrys_RecBillType_PromptBox);
        this.kdtEntrys.getColumn("RecBillType").setEditor(kdtEntrys_RecBillType_CellEditor);
        ObjectValueRender kdtEntrys_RecBillType_OVR = new ObjectValueRender();
        kdtEntrys_RecBillType_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("RecBillType").setRenderer(kdtEntrys_RecBillType_OVR);
        KDFormattedTextField kdtEntrys_Amount_TextField = new KDFormattedTextField();
        kdtEntrys_Amount_TextField.setName("kdtEntrys_Amount_TextField");
        kdtEntrys_Amount_TextField.setVisible(true);
        kdtEntrys_Amount_TextField.setEditable(true);
        kdtEntrys_Amount_TextField.setHorizontalAlignment(2);
        kdtEntrys_Amount_TextField.setDataType(1);
        	kdtEntrys_Amount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_Amount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_Amount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_Amount_CellEditor = new KDTDefaultCellEditor(kdtEntrys_Amount_TextField);
        this.kdtEntrys.getColumn("Amount").setEditor(kdtEntrys_Amount_CellEditor);
        KDFormattedTextField kdtEntrys_DiscountAmount_TextField = new KDFormattedTextField();
        kdtEntrys_DiscountAmount_TextField.setName("kdtEntrys_DiscountAmount_TextField");
        kdtEntrys_DiscountAmount_TextField.setVisible(true);
        kdtEntrys_DiscountAmount_TextField.setEditable(true);
        kdtEntrys_DiscountAmount_TextField.setHorizontalAlignment(2);
        kdtEntrys_DiscountAmount_TextField.setDataType(1);
        	kdtEntrys_DiscountAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_DiscountAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_DiscountAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_DiscountAmount_CellEditor = new KDTDefaultCellEditor(kdtEntrys_DiscountAmount_TextField);
        this.kdtEntrys.getColumn("DiscountAmount").setEditor(kdtEntrys_DiscountAmount_CellEditor);
        KDFormattedTextField kdtEntrys_ActualAmount_TextField = new KDFormattedTextField();
        kdtEntrys_ActualAmount_TextField.setName("kdtEntrys_ActualAmount_TextField");
        kdtEntrys_ActualAmount_TextField.setVisible(true);
        kdtEntrys_ActualAmount_TextField.setEditable(true);
        kdtEntrys_ActualAmount_TextField.setHorizontalAlignment(2);
        kdtEntrys_ActualAmount_TextField.setDataType(1);
        	kdtEntrys_ActualAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_ActualAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_ActualAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_ActualAmount_CellEditor = new KDTDefaultCellEditor(kdtEntrys_ActualAmount_TextField);
        this.kdtEntrys.getColumn("ActualAmount").setEditor(kdtEntrys_ActualAmount_CellEditor);
        final KDBizPromptBox kdtEntrys_SettlementType_PromptBox = new KDBizPromptBox();
        kdtEntrys_SettlementType_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.SettlementTypeQuery");
        kdtEntrys_SettlementType_PromptBox.setVisible(true);
        kdtEntrys_SettlementType_PromptBox.setEditable(true);
        kdtEntrys_SettlementType_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_SettlementType_PromptBox.setEditFormat("$number$");
        kdtEntrys_SettlementType_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_SettlementType_CellEditor = new KDTDefaultCellEditor(kdtEntrys_SettlementType_PromptBox);
        this.kdtEntrys.getColumn("SettlementType").setEditor(kdtEntrys_SettlementType_CellEditor);
        ObjectValueRender kdtEntrys_SettlementType_OVR = new ObjectValueRender();
        kdtEntrys_SettlementType_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("SettlementType").setRenderer(kdtEntrys_SettlementType_OVR);
        final KDBizPromptBox kdtEntrys_PayeeAccount_PromptBox = new KDBizPromptBox();
        kdtEntrys_PayeeAccount_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.account.app.F7AccountViewQuery");
        kdtEntrys_PayeeAccount_PromptBox.setVisible(true);
        kdtEntrys_PayeeAccount_PromptBox.setEditable(true);
        kdtEntrys_PayeeAccount_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_PayeeAccount_PromptBox.setEditFormat("$number$");
        kdtEntrys_PayeeAccount_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_PayeeAccount_CellEditor = new KDTDefaultCellEditor(kdtEntrys_PayeeAccount_PromptBox);
        this.kdtEntrys.getColumn("PayeeAccount").setEditor(kdtEntrys_PayeeAccount_CellEditor);
        ObjectValueRender kdtEntrys_PayeeAccount_OVR = new ObjectValueRender();
        kdtEntrys_PayeeAccount_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("PayeeAccount").setRenderer(kdtEntrys_PayeeAccount_OVR);
        final KDBizPromptBox kdtEntrys_OppAccount_PromptBox = new KDBizPromptBox();
        kdtEntrys_OppAccount_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.account.app.F7AccountViewQuery");
        kdtEntrys_OppAccount_PromptBox.setVisible(true);
        kdtEntrys_OppAccount_PromptBox.setEditable(true);
        kdtEntrys_OppAccount_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_OppAccount_PromptBox.setEditFormat("$number$");
        kdtEntrys_OppAccount_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_OppAccount_CellEditor = new KDTDefaultCellEditor(kdtEntrys_OppAccount_PromptBox);
        this.kdtEntrys.getColumn("OppAccount").setEditor(kdtEntrys_OppAccount_CellEditor);
        ObjectValueRender kdtEntrys_OppAccount_OVR = new ObjectValueRender();
        kdtEntrys_OppAccount_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("OppAccount").setRenderer(kdtEntrys_OppAccount_OVR);
        final KDBizPromptBox kdtEntrys_PayeeAccountBank_PromptBox = new KDBizPromptBox();
        kdtEntrys_PayeeAccountBank_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7AccountBankQuery");
        kdtEntrys_PayeeAccountBank_PromptBox.setVisible(true);
        kdtEntrys_PayeeAccountBank_PromptBox.setEditable(true);
        kdtEntrys_PayeeAccountBank_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_PayeeAccountBank_PromptBox.setEditFormat("$number$");
        kdtEntrys_PayeeAccountBank_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_PayeeAccountBank_CellEditor = new KDTDefaultCellEditor(kdtEntrys_PayeeAccountBank_PromptBox);
        this.kdtEntrys.getColumn("PayeeAccountBank").setEditor(kdtEntrys_PayeeAccountBank_CellEditor);
        ObjectValueRender kdtEntrys_PayeeAccountBank_OVR = new ObjectValueRender();
        kdtEntrys_PayeeAccountBank_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("PayeeAccountBank").setRenderer(kdtEntrys_PayeeAccountBank_OVR);
        final KDBizPromptBox kdtEntrys_Bank_PromptBox = new KDBizPromptBox();
        kdtEntrys_Bank_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7BankQuery");
        kdtEntrys_Bank_PromptBox.setVisible(true);
        kdtEntrys_Bank_PromptBox.setEditable(true);
        kdtEntrys_Bank_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_Bank_PromptBox.setEditFormat("$number$");
        kdtEntrys_Bank_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_Bank_CellEditor = new KDTDefaultCellEditor(kdtEntrys_Bank_PromptBox);
        this.kdtEntrys.getColumn("Bank").setEditor(kdtEntrys_Bank_CellEditor);
        ObjectValueRender kdtEntrys_Bank_OVR = new ObjectValueRender();
        kdtEntrys_Bank_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("Bank").setRenderer(kdtEntrys_Bank_OVR);
        KDTextField kdtEntrys_SettlementNumber_TextField = new KDTextField();
        kdtEntrys_SettlementNumber_TextField.setName("kdtEntrys_SettlementNumber_TextField");
        kdtEntrys_SettlementNumber_TextField.setMaxLength(160);
        KDTDefaultCellEditor kdtEntrys_SettlementNumber_CellEditor = new KDTDefaultCellEditor(kdtEntrys_SettlementNumber_TextField);
        this.kdtEntrys.getColumn("SettlementNumber").setEditor(kdtEntrys_SettlementNumber_CellEditor);
        KDFormattedTextField kdtEntrys_LocalAmount_TextField = new KDFormattedTextField();
        kdtEntrys_LocalAmount_TextField.setName("kdtEntrys_LocalAmount_TextField");
        kdtEntrys_LocalAmount_TextField.setVisible(true);
        kdtEntrys_LocalAmount_TextField.setEditable(true);
        kdtEntrys_LocalAmount_TextField.setHorizontalAlignment(2);
        kdtEntrys_LocalAmount_TextField.setDataType(1);
        	kdtEntrys_LocalAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_LocalAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_LocalAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_LocalAmount_CellEditor = new KDTDefaultCellEditor(kdtEntrys_LocalAmount_TextField);
        this.kdtEntrys.getColumn("LocalAmount").setEditor(kdtEntrys_LocalAmount_CellEditor);
        KDFormattedTextField kdtEntrys_LocalActualAmount_TextField = new KDFormattedTextField();
        kdtEntrys_LocalActualAmount_TextField.setName("kdtEntrys_LocalActualAmount_TextField");
        kdtEntrys_LocalActualAmount_TextField.setVisible(true);
        kdtEntrys_LocalActualAmount_TextField.setEditable(true);
        kdtEntrys_LocalActualAmount_TextField.setHorizontalAlignment(2);
        kdtEntrys_LocalActualAmount_TextField.setDataType(1);
        	kdtEntrys_LocalActualAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_LocalActualAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_LocalActualAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_LocalActualAmount_CellEditor = new KDTDefaultCellEditor(kdtEntrys_LocalActualAmount_TextField);
        this.kdtEntrys.getColumn("LocalActualAmount").setEditor(kdtEntrys_LocalActualAmount_CellEditor);
        KDTextField kdtEntrys_Remark_TextField = new KDTextField();
        kdtEntrys_Remark_TextField.setName("kdtEntrys_Remark_TextField");
        kdtEntrys_Remark_TextField.setMaxLength(200);
        KDTDefaultCellEditor kdtEntrys_Remark_CellEditor = new KDTDefaultCellEditor(kdtEntrys_Remark_TextField);
        this.kdtEntrys.getColumn("Remark").setEditor(kdtEntrys_Remark_CellEditor);
        KDTextField kdtEntrys_SourceBillIEntryID_TextField = new KDTextField();
        kdtEntrys_SourceBillIEntryID_TextField.setName("kdtEntrys_SourceBillIEntryID_TextField");
        kdtEntrys_SourceBillIEntryID_TextField.setMaxLength(44);
        KDTDefaultCellEditor kdtEntrys_SourceBillIEntryID_CellEditor = new KDTDefaultCellEditor(kdtEntrys_SourceBillIEntryID_TextField);
        this.kdtEntrys.getColumn("SourceBillIEntryID").setEditor(kdtEntrys_SourceBillIEntryID_CellEditor);
        KDTextField kdtEntrys_SourceBillID_TextField = new KDTextField();
        kdtEntrys_SourceBillID_TextField.setName("kdtEntrys_SourceBillID_TextField");
        kdtEntrys_SourceBillID_TextField.setMaxLength(44);
        KDTDefaultCellEditor kdtEntrys_SourceBillID_CellEditor = new KDTDefaultCellEditor(kdtEntrys_SourceBillID_TextField);
        this.kdtEntrys.getColumn("SourceBillID").setEditor(kdtEntrys_SourceBillID_CellEditor);
        final KDBizPromptBox kdtEntrys_SourceBillType_PromptBox = new KDBizPromptBox();
        kdtEntrys_SourceBillType_PromptBox.setQueryInfo("com.kingdee.eas.basedata.scm.common.app.F7BillTypeQuery");
        kdtEntrys_SourceBillType_PromptBox.setVisible(true);
        kdtEntrys_SourceBillType_PromptBox.setEditable(true);
        kdtEntrys_SourceBillType_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_SourceBillType_PromptBox.setEditFormat("$number$");
        kdtEntrys_SourceBillType_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_SourceBillType_CellEditor = new KDTDefaultCellEditor(kdtEntrys_SourceBillType_PromptBox);
        this.kdtEntrys.getColumn("SourceBillType").setEditor(kdtEntrys_SourceBillType_CellEditor);
        ObjectValueRender kdtEntrys_SourceBillType_OVR = new ObjectValueRender();
        kdtEntrys_SourceBillType_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("SourceBillType").setRenderer(kdtEntrys_SourceBillType_OVR);
        KDTextField kdtEntrys_SourceBillINumber_TextField = new KDTextField();
        kdtEntrys_SourceBillINumber_TextField.setName("kdtEntrys_SourceBillINumber_TextField");
        kdtEntrys_SourceBillINumber_TextField.setMaxLength(160);
        KDTDefaultCellEditor kdtEntrys_SourceBillINumber_CellEditor = new KDTDefaultCellEditor(kdtEntrys_SourceBillINumber_TextField);
        this.kdtEntrys.getColumn("SourceBillINumber").setEditor(kdtEntrys_SourceBillINumber_CellEditor);
        KDFormattedTextField kdtEntrys_SourceBillEntrySeq_TextField = new KDFormattedTextField();
        kdtEntrys_SourceBillEntrySeq_TextField.setName("kdtEntrys_SourceBillEntrySeq_TextField");
        kdtEntrys_SourceBillEntrySeq_TextField.setVisible(true);
        kdtEntrys_SourceBillEntrySeq_TextField.setEditable(true);
        kdtEntrys_SourceBillEntrySeq_TextField.setHorizontalAlignment(2);
        kdtEntrys_SourceBillEntrySeq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntrys_SourceBillEntrySeq_CellEditor = new KDTDefaultCellEditor(kdtEntrys_SourceBillEntrySeq_TextField);
        this.kdtEntrys.getColumn("SourceBillEntrySeq").setEditor(kdtEntrys_SourceBillEntrySeq_CellEditor);
        final KDBizPromptBox kdtEntrys_Vehicle_PromptBox = new KDBizPromptBox();
        kdtEntrys_Vehicle_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.VehicleF7Query");
        kdtEntrys_Vehicle_PromptBox.setVisible(true);
        kdtEntrys_Vehicle_PromptBox.setEditable(true);
        kdtEntrys_Vehicle_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_Vehicle_PromptBox.setEditFormat("$number$");
        kdtEntrys_Vehicle_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_Vehicle_CellEditor = new KDTDefaultCellEditor(kdtEntrys_Vehicle_PromptBox);
        this.kdtEntrys.getColumn("Vehicle").setEditor(kdtEntrys_Vehicle_CellEditor);
        ObjectValueRender kdtEntrys_Vehicle_OVR = new ObjectValueRender();
        kdtEntrys_Vehicle_OVR.setFormat(new BizDataFormat("$vIN$"));
        this.kdtEntrys.getColumn("Vehicle").setRenderer(kdtEntrys_Vehicle_OVR);
        KDTextField kdtEntrys_PlateNum_TextField = new KDTextField();
        kdtEntrys_PlateNum_TextField.setName("kdtEntrys_PlateNum_TextField");
        kdtEntrys_PlateNum_TextField.setMaxLength(44);
        KDTDefaultCellEditor kdtEntrys_PlateNum_CellEditor = new KDTDefaultCellEditor(kdtEntrys_PlateNum_TextField);
        this.kdtEntrys.getColumn("PlateNum").setEditor(kdtEntrys_PlateNum_CellEditor);
        // btnSplitLines
        this.btnSplitLines.setAction((IItemAction)ActionProxyFactory.getProxy(actionSplitLines, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnSplitLines.setText(resHelper.getString("btnSplitLines.text"));		
        this.btnSplitLines.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_split"));		
        this.btnSplitLines.setEnabled(false);		
        this.btnSplitLines.setVisible(false);
        // btnAddLines
        this.btnAddLines.setAction((IItemAction)ActionProxyFactory.getProxy(actionAddLines, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAddLines.setText(resHelper.getString("btnAddLines.text"));		
        this.btnAddLines.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_addline"));		
        this.btnAddLines.setEnabled(false);		
        this.btnAddLines.setVisible(false);
        // btnInsertLines
        this.btnInsertLines.setAction((IItemAction)ActionProxyFactory.getProxy(actionInsetLines, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnInsertLines.setText(resHelper.getString("btnInsertLines.text"));		
        this.btnInsertLines.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_insert"));		
        this.btnInsertLines.setEnabled(false);		
        this.btnInsertLines.setVisible(false);
        // btnDeleteLines
        this.btnDeleteLines.setAction((IItemAction)ActionProxyFactory.getProxy(actionDeleteLines, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnDeleteLines.setText(resHelper.getString("btnDeleteLines.text"));		
        this.btnDeleteLines.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_deleteline"));		
        this.btnDeleteLines.setEnabled(false);		
        this.btnDeleteLines.setVisible(false);
        // kdtHistory
		String kdtHistoryStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol1\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol3\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol4\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol5\"><c:Protection locked=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol6\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol7\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol8\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol9\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol11\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol12\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol13\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol14\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol15\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol16\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol17\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol18\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol19\"><c:Protection locked=\"true\" hidden=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol20\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:styleID=\"sCol0\" /><t:Column t:key=\"number\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:styleID=\"sCol1\" /><t:Column t:key=\"status\" t:width=\"80\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"Date\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /><t:Column t:key=\"RecBillType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"ActualAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" t:styleID=\"sCol5\" /><t:Column t:key=\"SettlementType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"SettlementStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /><t:Column t:key=\"BillStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" t:styleID=\"sCol8\" /><t:Column t:key=\"SourceBillINumber\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" t:styleID=\"sCol9\" /><t:Column t:key=\"SourceBillEntrySeq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" /><t:Column t:key=\"TotalAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" t:styleID=\"sCol11\" /><t:Column t:key=\"TotalActualAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" t:styleID=\"sCol12\" /><t:Column t:key=\"Person\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" t:styleID=\"sCol13\" /><t:Column t:key=\"ReceiveObjectType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" t:styleID=\"sCol14\" /><t:Column t:key=\"ReceiveObjectNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" t:styleID=\"sCol15\" /><t:Column t:key=\"ReceiveObjectName\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" t:styleID=\"sCol16\" /><t:Column t:key=\"ActualPayer\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"17\" t:styleID=\"sCol17\" /><t:Column t:key=\"OrgUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" t:styleID=\"sCol18\" /><t:Column t:key=\"LocalActualAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"19\" t:styleID=\"sCol19\" /><t:Column t:key=\"Remark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"20\" t:styleID=\"sCol20\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{number}</t:Cell><t:Cell>$Resource{status}</t:Cell><t:Cell>$Resource{Date}</t:Cell><t:Cell>$Resource{RecBillType}</t:Cell><t:Cell>$Resource{ActualAmount}</t:Cell><t:Cell>$Resource{SettlementType}</t:Cell><t:Cell>$Resource{SettlementStatus}</t:Cell><t:Cell>$Resource{BillStatus}</t:Cell><t:Cell>$Resource{SourceBillINumber}</t:Cell><t:Cell>$Resource{SourceBillEntrySeq}</t:Cell><t:Cell>$Resource{TotalAmount}</t:Cell><t:Cell>$Resource{TotalActualAmount}</t:Cell><t:Cell>$Resource{Person}</t:Cell><t:Cell>$Resource{ReceiveObjectType}</t:Cell><t:Cell>$Resource{ReceiveObjectNum}</t:Cell><t:Cell>$Resource{ReceiveObjectName}</t:Cell><t:Cell>$Resource{ActualPayer}</t:Cell><t:Cell>$Resource{OrgUnit}</t:Cell><t:Cell>$Resource{LocalActualAmount}</t:Cell><t:Cell>$Resource{Remark}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtHistory.setFormatXml(resHelper.translateString("kdtHistory",kdtHistoryStrXML));

        

        this.kdtHistory.checkParsed();
        // txtvoucherID		
        this.txtvoucherID.setVisible(true);		
        this.txtvoucherID.setHorizontalAlignment(2);		
        this.txtvoucherID.setMaxLength(100);		
        this.txtvoucherID.setRequired(false);
        // prmtCreator		
        this.prmtCreator.setEnabled(false);
        // kDDateCreateTime		
        this.kDDateCreateTime.setEnabled(false);
        // txtReceiveObjectNumber		
        this.txtReceiveObjectNumber.setVisible(false);		
        this.txtReceiveObjectNumber.setHorizontalAlignment(2);		
        this.txtReceiveObjectNumber.setMaxLength(160);		
        this.txtReceiveObjectNumber.setRequired(false);
        // kDDateLastUpdateTime		
        this.kDDateLastUpdateTime.setEnabled(false);
        // pkAuditTime		
        this.pkAuditTime.setRequired(false);		
        this.pkAuditTime.setEnabled(false);
        // prmtLastUpdateUser		
        this.prmtLastUpdateUser.setEnabled(false);
        // txtReceiveObject		
        this.txtReceiveObject.setRequestFocusEnabled(false);		
        this.txtReceiveObject.setFocusCycleRoot(true);		
        this.txtReceiveObject.setVisible(false);
        // prmtAuditor		
        this.prmtAuditor.setEnabled(false);
        // prmtvehicle		
        this.prmtvehicle.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.VehicleQuery");		
        this.prmtvehicle.setEditable(true);		
        this.prmtvehicle.setDisplayFormat("$vIN$");		
        this.prmtvehicle.setEditFormat("$number$");		
        this.prmtvehicle.setCommitFormat("$number$");		
        this.prmtvehicle.setRequired(false);
        this.prmtvehicle.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtvehicle_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtcard		
        this.prmtcard.setQueryInfo("com.kingdee.eas.auto4s.vip.mb.app.CardQuery");		
        this.prmtcard.setVisible(true);		
        this.prmtcard.setEditable(true);		
        this.prmtcard.setDisplayFormat("$number$");		
        this.prmtcard.setEditFormat("$number$");		
        this.prmtcard.setCommitFormat("$number$");		
        this.prmtcard.setRequired(false);
        // txtreadSN
        this.txtreadSN.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent e) {
                try {
                    txtreadSN_focusLost(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        this.txtreadSN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent e) {
                try {
                    txtreadSN_keyPressed(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        // prmtpaymentType		
        this.prmtpaymentType.setVisible(true);		
        this.prmtpaymentType.setEditable(true);		
        this.prmtpaymentType.setDisplayFormat("$name$");		
        this.prmtpaymentType.setEditFormat("$number$");		
        this.prmtpaymentType.setCommitFormat("$number$");		
        this.prmtpaymentType.setRequired(false);
        // txtExchangeRate		
        this.txtExchangeRate.setHorizontalAlignment(2);		
        this.txtExchangeRate.setDataType(1);		
        this.txtExchangeRate.setSupportedEmpty(true);		
        this.txtExchangeRate.setMinimumValue( new java.math.BigDecimal("-999.9999999999"));		
        this.txtExchangeRate.setMaximumValue( new java.math.BigDecimal("999.9999999999"));		
        this.txtExchangeRate.setPrecision(10);		
        this.txtExchangeRate.setRequired(true);
        // prmtDepartment		
        this.prmtDepartment.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");		
        this.prmtDepartment.setVisible(true);		
        this.prmtDepartment.setEditable(true);		
        this.prmtDepartment.setDisplayFormat("$name$");		
        this.prmtDepartment.setEditFormat("$number$");		
        this.prmtDepartment.setCommitFormat("$number$");		
        this.prmtDepartment.setRequired(false);
        // btnAudit
        this.btnAudit.setAction((IItemAction)ActionProxyFactory.getProxy(actionAudit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAudit.setText(resHelper.getString("btnAudit.text"));
        // btnFaudit
        this.btnFaudit.setAction((IItemAction)ActionProxyFactory.getProxy(actionFaudit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnFaudit.setText(resHelper.getString("btnFaudit.text"));
        // btnRec
        this.btnRec.setAction((IItemAction)ActionProxyFactory.getProxy(actionRec, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnRec.setText(resHelper.getString("btnRec.text"));
        // btnCancelRec
        this.btnCancelRec.setAction((IItemAction)ActionProxyFactory.getProxy(actionCancelRec, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCancelRec.setText(resHelper.getString("btnCancelRec.text"));
        // btnLoadDoc
        this.btnLoadDoc.setAction((IItemAction)ActionProxyFactory.getProxy(actionLoadDoc, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnLoadDoc.setText(resHelper.getString("btnLoadDoc.text"));
        this.btnLoadDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnLoadDoc_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // btnAutoLoad
        this.btnAutoLoad.setAction((IItemAction)ActionProxyFactory.getProxy(actionAutoLoad, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAutoLoad.setText(resHelper.getString("btnAutoLoad.text"));
        this.btnAutoLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnAutoLoadAutoLoad_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // separator4
        // btnViewSource
        this.btnViewSource.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewSource, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnViewSource.setText(resHelper.getString("btnViewSource.text"));
        // separator5
        // btnViewCustomerProfile
        this.btnViewCustomerProfile.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewCustomer, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnViewCustomerProfile.setText(resHelper.getString("btnViewCustomerProfile.text"));		
        this.btnViewCustomerProfile.setEnabled(false);		
        this.btnViewCustomerProfile.setVisible(false);
        // btnViewCarProfile
        this.btnViewCarProfile.setAction((IItemAction)ActionProxyFactory.getProxy(actionViewVehicle, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnViewCarProfile.setText(resHelper.getString("btnViewCarProfile.text"));		
        this.btnViewCarProfile.setEnabled(false);		
        this.btnViewCarProfile.setVisible(false);
        // btnEasVoucher
        this.btnEasVoucher.setAction((IItemAction)ActionProxyFactory.getProxy(actionEasVoucher, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnEasVoucher.setText(resHelper.getString("btnEasVoucher.text"));		
        this.btnEasVoucher.setToolTipText(resHelper.getString("btnEasVoucher.toolTipText"));		
        this.btnEasVoucher.setVisible(true);
        // btnEasDelVoucher
        this.btnEasDelVoucher.setAction((IItemAction)ActionProxyFactory.getProxy(actionEasDelVoucher, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnEasDelVoucher.setText(resHelper.getString("btnEasDelVoucher.text"));		
        this.btnEasDelVoucher.setToolTipText(resHelper.getString("btnEasDelVoucher.toolTipText"));		
        this.btnEasDelVoucher.setVisible(true);
        // btnCommitSettle
        this.btnCommitSettle.setAction((IItemAction)ActionProxyFactory.getProxy(actionCommitSettle, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCommitSettle.setText(resHelper.getString("btnCommitSettle.text"));
        // menuItemAudit
        this.menuItemAudit.setAction((IItemAction)ActionProxyFactory.getProxy(actionAudit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemAudit.setText(resHelper.getString("menuItemAudit.text"));
        // menuItemAntiAudit
        this.menuItemAntiAudit.setAction((IItemAction)ActionProxyFactory.getProxy(actionFaudit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemAntiAudit.setText(resHelper.getString("menuItemAntiAudit.text"));
        // contsrcEntryIds		
        this.contsrcEntryIds.setBoundLabelText(resHelper.getString("contsrcEntryIds.boundLabelText"));		
        this.contsrcEntryIds.setBoundLabelLength(100);		
        this.contsrcEntryIds.setBoundLabelUnderline(true);		
        this.contsrcEntryIds.setVisible(false);
        // txtsrcEntryIds		
        this.txtsrcEntryIds.setVisible(false);		
        this.txtsrcEntryIds.setHorizontalAlignment(2);		
        this.txtsrcEntryIds.setMaxLength(2000);		
        this.txtsrcEntryIds.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {pkDate,Status,prmtPerson,prmtCurrency,txtExchangeRate,prmtOrgUnit,txtreadSN,prmtBrand,txtRemark,pkAuditTime,prmtvehicle,txtReceiveObject,ReceiveObjectType,txtReceiveObjectNumber,txtReceiveObjectName,txtActualPayer,prmtBizType,chkisAccount,txtreceivingAmt,txtPRAbateAmt,txtcashCouponAmt,txtpointToCashAmt,txtvoucherID,txtamount,txtNumber,pkBizDate,txtDescription,prmtAuditor,prmtCreator,kDDateCreateTime,prmtLastUpdateUser,kDDateLastUpdateTime,kdtType,kdtEntrys,kdtContent,chkIsSendSms,prmtcard,prmtDepartment,prmtpaymentType,txtsrcEntryIds}));
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
        this.setBounds(new Rectangle(0, 0, 1013, 639));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1013, 639));
        contNumber.setBounds(new Rectangle(10, 10, 270, 19));
        this.add(contNumber, new KDLayout.Constraints(10, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBizDate.setBounds(new Rectangle(209, 656, 118, 19));
        this.add(contBizDate, new KDLayout.Constraints(209, 656, 118, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contDescription.setBounds(new Rectangle(490, 695, 150, 19));
        this.add(contDescription, new KDLayout.Constraints(490, 695, 150, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contDate.setBounds(new Rectangle(370, 10, 270, 19));
        this.add(contDate, new KDLayout.Constraints(370, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contStatus.setBounds(new Rectangle(730, 10, 270, 19));
        this.add(contStatus, new KDLayout.Constraints(730, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contPerson.setBounds(new Rectangle(10, 34, 270, 19));
        this.add(contPerson, new KDLayout.Constraints(10, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCurrency.setBounds(new Rectangle(370, 34, 270, 19));
        this.add(contCurrency, new KDLayout.Constraints(370, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contOrgUnit.setBounds(new Rectangle(730, 108, 270, 19));
        this.add(contOrgUnit, new KDLayout.Constraints(730, 108, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBrand.setBounds(new Rectangle(370, 108, 270, 19));
        this.add(contBrand, new KDLayout.Constraints(370, 108, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contReceiveObject.setBounds(new Rectangle(370, 58, 270, 19));
        this.add(contReceiveObject, new KDLayout.Constraints(370, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contRemark.setBounds(new Rectangle(10, 157, 630, 19));
        this.add(contRemark, new KDLayout.Constraints(10, 157, 630, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contReceiveObjectType.setBounds(new Rectangle(10, 58, 270, 19));
        this.add(contReceiveObjectType, new KDLayout.Constraints(10, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contActualPayer.setBounds(new Rectangle(730, 58, 270, 19));
        this.add(contActualPayer, new KDLayout.Constraints(730, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBizType.setBounds(new Rectangle(10, 109, 270, 19));
        this.add(contBizType, new KDLayout.Constraints(10, 109, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDTabbedPane1.setBounds(new Rectangle(10, 181, 1001, 446));
        this.add(kDTabbedPane1, new KDLayout.Constraints(10, 181, 1001, 446, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        chkisAccount.setBounds(new Rectangle(12, 639, 223, 19));
        this.add(chkisAccount, new KDLayout.Constraints(12, 639, 223, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvoucherID.setBounds(new Rectangle(340, 647, 270, 19));
        this.add(contvoucherID, new KDLayout.Constraints(340, 647, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contCreator.setBounds(new Rectangle(10, 646, 270, 19));
        this.add(contCreator, new KDLayout.Constraints(10, 646, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCreateTime.setBounds(new Rectangle(10, 675, 270, 19));
        this.add(contCreateTime, new KDLayout.Constraints(10, 675, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contReceiveObjectNumber.setBounds(new Rectangle(594, 651, 270, 19));
        this.add(contReceiveObjectNumber, new KDLayout.Constraints(594, 651, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contLastUpdateTime.setBounds(new Rectangle(370, 681, 270, 19));
        this.add(contLastUpdateTime, new KDLayout.Constraints(370, 681, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAuditTime.setBounds(new Rectangle(730, 650, 270, 19));
        this.add(contAuditTime, new KDLayout.Constraints(730, 650, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateUser.setBounds(new Rectangle(370, 652, 270, 19));
        this.add(contLastUpdateUser, new KDLayout.Constraints(370, 652, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contReceiveObjectName.setBounds(new Rectangle(141, 645, 270, 19));
        this.add(contReceiveObjectName, new KDLayout.Constraints(141, 645, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditor.setBounds(new Rectangle(730, 651, 270, 19));
        this.add(contAuditor, new KDLayout.Constraints(730, 651, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        txtReceiveObjectName.setBounds(new Rectangle(353, 649, 65, 19));
        this.add(txtReceiveObjectName, new KDLayout.Constraints(353, 649, 65, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvehicle.setBounds(new Rectangle(11, 82, 270, 19));
        this.add(contvehicle, new KDLayout.Constraints(11, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnTotalLines.setBounds(new Rectangle(12, 625, 130, 19));
        this.add(btnTotalLines, new KDLayout.Constraints(12, 625, 130, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnAmtClean.setBounds(new Rectangle(146, 627, 68, 19));
        this.add(btnAmtClean, new KDLayout.Constraints(146, 627, 68, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        chkIsSendSms.setBounds(new Rectangle(730, 137, 160, 19));
        this.add(chkIsSendSms, new KDLayout.Constraints(730, 137, 160, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcard.setBounds(new Rectangle(730, 82, 270, 19));
        this.add(contcard, new KDLayout.Constraints(730, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contreadSN.setBounds(new Rectangle(370, 82, 270, 19));
        this.add(contreadSN, new KDLayout.Constraints(370, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnPRContent.setBounds(new Rectangle(224, 628, 77, 19));
        this.add(btnPRContent, new KDLayout.Constraints(224, 628, 77, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contpaymentType.setBounds(new Rectangle(730, 34, 270, 19));
        this.add(contpaymentType, new KDLayout.Constraints(730, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contExchangeRate.setBounds(new Rectangle(841, 134, 161, 19));
        this.add(contExchangeRate, new KDLayout.Constraints(841, 134, 161, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contDepartment.setBounds(new Rectangle(10, 135, 270, 19));
        this.add(contDepartment, new KDLayout.Constraints(10, 135, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contsrcEntryIds.setBounds(new Rectangle(364, 131, 270, 19));
        this.add(contsrcEntryIds, new KDLayout.Constraints(364, 131, 270, 19, 0));
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contBizDate
        contBizDate.setBoundEditor(pkBizDate);
        //contDescription
        contDescription.setBoundEditor(txtDescription);
        //contDate
        contDate.setBoundEditor(pkDate);
        //contStatus
        contStatus.setBoundEditor(Status);
        //contPerson
        contPerson.setBoundEditor(prmtPerson);
        //contCurrency
        contCurrency.setBoundEditor(prmtCurrency);
        //contOrgUnit
        contOrgUnit.setBoundEditor(prmtOrgUnit);
        //contBrand
        contBrand.setBoundEditor(prmtBrand);
        //contReceiveObject
        contReceiveObject.setBoundEditor(prmtReceiveObject);
        //contRemark
        contRemark.setBoundEditor(txtRemark);
        //contReceiveObjectType
        contReceiveObjectType.setBoundEditor(ReceiveObjectType);
        //contActualPayer
        contActualPayer.setBoundEditor(txtActualPayer);
        //contBizType
        contBizType.setBoundEditor(prmtBizType);
        //kDTabbedPane1
        kDTabbedPane1.add(kDPanel1, resHelper.getString("kDPanel1.constraints"));
        kDTabbedPane1.add(kDPanel4, resHelper.getString("kDPanel4.constraints"));
        kDTabbedPane1.add(kDPanel2, resHelper.getString("kDPanel2.constraints"));
        //kDPanel1
        kDPanel1.setLayout(new KDLayout());
        kDPanel1.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1000, 413));        kDPanel5.setBounds(new Rectangle(2, 1, 991, 196));
        kDPanel1.add(kDPanel5, new KDLayout.Constraints(2, 1, 991, 196, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDPanel6.setBounds(new Rectangle(3, 202, 991, 200));
        kDPanel1.add(kDPanel6, new KDLayout.Constraints(3, 202, 991, 200, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDPanel5
        kDPanel5.setLayout(new KDLayout());
        kDPanel5.putClientProperty("OriginalBounds", new Rectangle(2, 1, 991, 196));        kdtContent.setBounds(new Rectangle(33, 1, 954, 187));
        kdtContent_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtContent,new com.kingdee.eas.auto4s.arp.aar.ReceivingBillContentInfo(),null,false);
        kDPanel5.add(kdtContent_detailPanel, new KDLayout.Constraints(33, 1, 954, 187, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnMoveTypeTop.setBounds(new Rectangle(6, 27, 22, 19));
        kDPanel5.add(btnMoveTypeTop, new KDLayout.Constraints(6, 27, 22, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnMoveTypeDown.setBounds(new Rectangle(6, 50, 22, 19));
        kDPanel5.add(btnMoveTypeDown, new KDLayout.Constraints(6, 50, 22, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDPanel6
        kDPanel6.setLayout(new KDLayout());
        kDPanel6.putClientProperty("OriginalBounds", new Rectangle(3, 202, 991, 200));        kDPanel3.setBounds(new Rectangle(676, 3, 310, 196));
        kDPanel6.add(kDPanel3, new KDLayout.Constraints(676, 3, 310, 196, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kdtType.setBounds(new Rectangle(33, 3, 639, 195));
        kdtType_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtType,new com.kingdee.eas.auto4s.arp.aar.ReceivingBillTypeInfo(),null,false);
        kDPanel6.add(kdtType_detailPanel, new KDLayout.Constraints(33, 3, 639, 195, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnMoveReceivingTop.setBounds(new Rectangle(6, 26, 22, 19));
        kDPanel6.add(btnMoveReceivingTop, new KDLayout.Constraints(6, 26, 22, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnMoveReceivingDown.setBounds(new Rectangle(6, 49, 22, 19));
        kDPanel6.add(btnMoveReceivingDown, new KDLayout.Constraints(6, 49, 22, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDPanel3
        kDPanel3.setLayout(new KDLayout());
        kDPanel3.putClientProperty("OriginalBounds", new Rectangle(676, 3, 310, 196));        btnScoreCard.setBounds(new Rectangle(238, 4, 63, 19));
        kDPanel3.add(btnScoreCard, new KDLayout.Constraints(238, 4, 63, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnGiftCard.setBounds(new Rectangle(162, 4, 70, 19));
        kDPanel3.add(btnGiftCard, new KDLayout.Constraints(162, 4, 70, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnSavingsCard.setBounds(new Rectangle(86, 4, 70, 19));
        kDPanel3.add(btnSavingsCard, new KDLayout.Constraints(86, 4, 70, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contreceivingAmt.setBounds(new Rectangle(36, 32, 242, 19));
        kDPanel3.add(contreceivingAmt, new KDLayout.Constraints(36, 32, 242, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contPRAbateAmt.setBounds(new Rectangle(36, 56, 242, 19));
        kDPanel3.add(contPRAbateAmt, new KDLayout.Constraints(36, 56, 242, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcashCouponAmt.setBounds(new Rectangle(36, 106, 242, 19));
        kDPanel3.add(contcashCouponAmt, new KDLayout.Constraints(36, 106, 242, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contpointToCashAmt.setBounds(new Rectangle(36, 130, 242, 19));
        kDPanel3.add(contpointToCashAmt, new KDLayout.Constraints(36, 130, 242, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contamount.setBounds(new Rectangle(36, 155, 242, 19));
        kDPanel3.add(contamount, new KDLayout.Constraints(36, 155, 242, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnPRWriteOff.setBounds(new Rectangle(10, 4, 70, 19));
        kDPanel3.add(btnPRWriteOff, new KDLayout.Constraints(10, 4, 70, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contsavingCardAmt.setBounds(new Rectangle(36, 81, 242, 19));
        kDPanel3.add(contsavingCardAmt, new KDLayout.Constraints(36, 81, 242, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contreceivingAmt
        contreceivingAmt.setBoundEditor(txtreceivingAmt);
        //contPRAbateAmt
        contPRAbateAmt.setBoundEditor(txtPRAbateAmt);
        //contcashCouponAmt
        contcashCouponAmt.setBoundEditor(txtcashCouponAmt);
        //contpointToCashAmt
        contpointToCashAmt.setBoundEditor(txtpointToCashAmt);
        //contamount
        contamount.setBoundEditor(txtamount);
        //contsavingCardAmt
        contsavingCardAmt.setBoundEditor(txtsavingCardAmt);
        //kDPanel4
        kDPanel4.setLayout(new KDLayout());
        kDPanel4.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1000, 413));        kdtEntrys.setBounds(new Rectangle(30, 4, 957, 399));
        kdtEntrys_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtEntrys,new com.kingdee.eas.auto4s.arp.aar.ReceivingBillEntryInfo(),null,false);
        kDPanel4.add(kdtEntrys_detailPanel, new KDLayout.Constraints(30, 4, 957, 399, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnSplitLines.setBounds(new Rectangle(599, 0, 108, 19));
        kDPanel4.add(btnSplitLines, new KDLayout.Constraints(599, 0, 108, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnAddLines.setBounds(new Rectangle(711, 0, 72, 19));
        kDPanel4.add(btnAddLines, new KDLayout.Constraints(711, 0, 72, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnInsertLines.setBounds(new Rectangle(786, 0, 74, 19));
        kDPanel4.add(btnInsertLines, new KDLayout.Constraints(786, 0, 74, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        btnDeleteLines.setBounds(new Rectangle(863, 0, 72, 19));
        kDPanel4.add(btnDeleteLines, new KDLayout.Constraints(863, 0, 72, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //kDPanel2
        kDPanel2.setLayout(new KDLayout());
        kDPanel2.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1000, 413));        kdtHistory.setBounds(new Rectangle(30, 4, 957, 395));
        kDPanel2.add(kdtHistory, new KDLayout.Constraints(30, 4, 957, 395, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contvoucherID
        contvoucherID.setBoundEditor(txtvoucherID);
        //contCreator
        contCreator.setBoundEditor(prmtCreator);
        //contCreateTime
        contCreateTime.setBoundEditor(kDDateCreateTime);
        //contReceiveObjectNumber
        contReceiveObjectNumber.setBoundEditor(txtReceiveObjectNumber);
        //contLastUpdateTime
        contLastUpdateTime.setBoundEditor(kDDateLastUpdateTime);
        //contAuditTime
        contAuditTime.setBoundEditor(pkAuditTime);
        //contLastUpdateUser
        contLastUpdateUser.setBoundEditor(prmtLastUpdateUser);
        //contReceiveObjectName
        contReceiveObjectName.setBoundEditor(txtReceiveObject);
        //contAuditor
        contAuditor.setBoundEditor(prmtAuditor);
        //contvehicle
        contvehicle.setBoundEditor(prmtvehicle);
        //contcard
        contcard.setBoundEditor(prmtcard);
        //contreadSN
        contreadSN.setBoundEditor(txtreadSN);
        //contpaymentType
        contpaymentType.setBoundEditor(prmtpaymentType);
        //contExchangeRate
        contExchangeRate.setBoundEditor(txtExchangeRate);
        //contDepartment
        contDepartment.setBoundEditor(prmtDepartment);
        //contsrcEntryIds
        contsrcEntryIds.setBoundEditor(txtsrcEntryIds);

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
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        menuBiz.add(menuItemAudit);
        menuBiz.add(menuItemAntiAudit);
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
        menuWorkflow.add(menuItemStartWorkFlow);
        menuWorkflow.add(separatorWF1);
        menuWorkflow.add(menuItemViewSubmitProccess);
        menuWorkflow.add(menuItemViewDoProccess);
        menuWorkflow.add(MenuItemWFG);
        menuWorkflow.add(menuItemWorkFlowList);
        menuWorkflow.add(separatorWF2);
        menuWorkflow.add(menuItemMultiapprove);
        menuWorkflow.add(menuItemNextPerson);
        menuWorkflow.add(menuItemAuditResult);
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
        this.toolBar.add(btnEdit);
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnSave);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnAudit);
        this.toolBar.add(btnFaudit);
        this.toolBar.add(btnRec);
        this.toolBar.add(btnCancelRec);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(btnLoadDoc);
        this.toolBar.add(btnAutoLoad);
        this.toolBar.add(separator4);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnViewSource);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(separator5);
        this.toolBar.add(btnViewCustomerProfile);
        this.toolBar.add(btnViewCarProfile);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnCopyLine);
        this.toolBar.add(btnInsertLine);
        this.toolBar.add(btnRemoveLine);
        this.toolBar.add(separatorFW6);
        this.toolBar.add(separatorFW9);
        this.toolBar.add(btnEasVoucher);
        this.toolBar.add(btnEasDelVoucher);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnWFViewSubmitProccess);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(btnCommitSettle);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("isAccount", boolean.class, this.chkisAccount, "selected");
		dataBinder.registerBinding("ReceiveObjectName", String.class, this.txtReceiveObjectName, "text");
		dataBinder.registerBinding("IsSendSms", boolean.class, this.chkIsSendSms, "selected");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("bizDate", java.util.Date.class, this.pkBizDate, "value");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "text");
		dataBinder.registerBinding("Date", java.util.Date.class, this.pkDate, "value");
		dataBinder.registerBinding("Status", com.kingdee.eas.auto4s.bdm.pbd.BillStatusEnum.class, this.Status, "selectedItem");
		dataBinder.registerBinding("Person", com.kingdee.eas.basedata.person.PersonInfo.class, this.prmtPerson, "data");
		dataBinder.registerBinding("Currency", com.kingdee.eas.basedata.assistant.CurrencyInfo.class, this.prmtCurrency, "data");
		dataBinder.registerBinding("OrgUnit", com.kingdee.eas.basedata.org.CompanyOrgUnitInfo.class, this.prmtOrgUnit, "data");
		dataBinder.registerBinding("Brand", com.kingdee.eas.auto4s.bdm.pbd.BrandInfo.class, this.prmtBrand, "data");
		dataBinder.registerBinding("ReceiveObjectID", String.class, this.prmtReceiveObject, "data");
		dataBinder.registerBinding("Remark", String.class, this.txtRemark, "text");
		dataBinder.registerBinding("ReceiveObjectType", com.kingdee.eas.auto4s.bdm.arp.ARObjTypeEnum.class, this.ReceiveObjectType, "selectedItem");
		dataBinder.registerBinding("ActualPayer", String.class, this.txtActualPayer, "text");
		dataBinder.registerBinding("BizType", com.kingdee.eas.fm.fs.SettBizTypeInfo.class, this.prmtBizType, "data");
		dataBinder.registerBinding("Content.seq", int.class, this.kdtContent, "seq.text");
		dataBinder.registerBinding("Content", com.kingdee.eas.auto4s.arp.aar.ReceivingBillContentInfo.class, this.kdtContent, "userObject");
		dataBinder.registerBinding("Content.RecBillType", java.lang.Object.class, this.kdtContent, "RecBillType.text");
		dataBinder.registerBinding("Content.Amount", java.math.BigDecimal.class, this.kdtContent, "Amount.text");
		dataBinder.registerBinding("Content.DiscountAmount", java.math.BigDecimal.class, this.kdtContent, "DiscountAmount.text");
		dataBinder.registerBinding("Content.ActualAmount", java.math.BigDecimal.class, this.kdtContent, "ActualAmount.text");
		dataBinder.registerBinding("Content.Remark", String.class, this.kdtContent, "Remark.text");
		dataBinder.registerBinding("Content.SourceBillINumber", String.class, this.kdtContent, "SourceBillINumber.text");
		dataBinder.registerBinding("Content.SourceBillEntrySeq", int.class, this.kdtContent, "SourceBillEntrySeq.text");
		dataBinder.registerBinding("Content.Vehicle", java.lang.Object.class, this.kdtContent, "Vehicle.text");
		dataBinder.registerBinding("Content.OppAccount", java.lang.Object.class, this.kdtContent, "OppAccount.text");
		dataBinder.registerBinding("Content.SourceBillIEntryID", String.class, this.kdtContent, "SourceBillIEntryID.text");
		dataBinder.registerBinding("Content.SourceBillID", String.class, this.kdtContent, "SourceBillID.text");
		dataBinder.registerBinding("Content.SourceBillType", java.lang.Object.class, this.kdtContent, "SourceBillType.text");
		dataBinder.registerBinding("Content.IsMorAccount", boolean.class, this.kdtContent, "IsMorAccount.text");
		dataBinder.registerBinding("Content.TotalAmount", java.math.BigDecimal.class, this.kdtContent, "TotalAmount.text");
		dataBinder.registerBinding("Content.TotalActualAmount", java.math.BigDecimal.class, this.kdtContent, "TotalActualAmount.text");
		dataBinder.registerBinding("Content.savingCardAmt", java.math.BigDecimal.class, this.kdtContent, "savingCardAmt.text");
		dataBinder.registerBinding("Content.rechargeAmt", java.math.BigDecimal.class, this.kdtContent, "rechargeAmt.text");
		dataBinder.registerBinding("Content.presAmt", java.math.BigDecimal.class, this.kdtContent, "presAmt.text");
		dataBinder.registerBinding("Content.cashCouponAmt", java.math.BigDecimal.class, this.kdtContent, "cashCouponAmt.text");
		dataBinder.registerBinding("Content.pointToCashAmt", java.math.BigDecimal.class, this.kdtContent, "pointToCashAmt.text");
		dataBinder.registerBinding("Content.card", java.lang.Object.class, this.kdtContent, "card.text");
		dataBinder.registerBinding("Content.pointAccountID", String.class, this.kdtContent, "pointAccountID.text");
		dataBinder.registerBinding("Content.cashAccountID", String.class, this.kdtContent, "cashAccountID.text");
		dataBinder.registerBinding("Content.isPointToCash", boolean.class, this.kdtContent, "isPointToCash.text");
		dataBinder.registerBinding("Content.isUseCashAccount", boolean.class, this.kdtContent, "isUseCashAccount.text");
		dataBinder.registerBinding("Content.PRAbateAmount", java.math.BigDecimal.class, this.kdtContent, "PRAbateAmount.text");
		dataBinder.registerBinding("Content.isPreReceive", boolean.class, this.kdtContent, "isPreReceive.text");
		dataBinder.registerBinding("Content.Vehicle.plateNum", String.class, this.kdtContent, "PlateNum.text");
		dataBinder.registerBinding("Type.seq", int.class, this.kdtType, "seq.text");
		dataBinder.registerBinding("Type", com.kingdee.eas.auto4s.arp.aar.ReceivingBillTypeInfo.class, this.kdtType, "userObject");
		dataBinder.registerBinding("Type.SettlementType", java.lang.Object.class, this.kdtType, "SettlementType.text");
		dataBinder.registerBinding("Type.PayeeAccountBank", java.lang.Object.class, this.kdtType, "PayeeAccountBank.text");
		dataBinder.registerBinding("Type.SettlementNumber", String.class, this.kdtType, "SettlementNumber.text");
		dataBinder.registerBinding("Type.ActRecAmt", java.math.BigDecimal.class, this.kdtType, "ActRecAmt.text");
		dataBinder.registerBinding("Type.PayeeAccountBank.bank", java.lang.Object.class, this.kdtType, "Bank.text");
		dataBinder.registerBinding("Type.PayeeAccount", com.kingdee.eas.basedata.master.account.AccountViewInfo.class, this.kdtType, "PayeeAccount.text");
		dataBinder.registerBinding("receivingAmt", java.math.BigDecimal.class, this.txtreceivingAmt, "value");
		dataBinder.registerBinding("WOPRAmount", java.math.BigDecimal.class, this.txtPRAbateAmt, "value");
		dataBinder.registerBinding("cashCouponAmt", java.math.BigDecimal.class, this.txtcashCouponAmt, "value");
		dataBinder.registerBinding("pointToCashAmt", java.math.BigDecimal.class, this.txtpointToCashAmt, "value");
		dataBinder.registerBinding("amount", java.math.BigDecimal.class, this.txtamount, "value");
		dataBinder.registerBinding("entrys.id", com.kingdee.bos.util.BOSUuid.class, this.kdtEntrys, "id.text");
		dataBinder.registerBinding("entrys", com.kingdee.eas.auto4s.arp.aar.ReceivingBillEntryInfo.class, this.kdtEntrys, "userObject");
		dataBinder.registerBinding("entrys.RecBillType", java.lang.Object.class, this.kdtEntrys, "RecBillType.text");
		dataBinder.registerBinding("entrys.SourceBillIEntryID", String.class, this.kdtEntrys, "SourceBillIEntryID.text");
		dataBinder.registerBinding("entrys.SourceBillID", String.class, this.kdtEntrys, "SourceBillID.text");
		dataBinder.registerBinding("entrys.SourceBillINumber", String.class, this.kdtEntrys, "SourceBillINumber.text");
		dataBinder.registerBinding("entrys.SourceBillEntrySeq", int.class, this.kdtEntrys, "SourceBillEntrySeq.text");
		dataBinder.registerBinding("entrys.DiscountAmount", java.math.BigDecimal.class, this.kdtEntrys, "DiscountAmount.text");
		dataBinder.registerBinding("entrys.Amount", java.math.BigDecimal.class, this.kdtEntrys, "Amount.text");
		dataBinder.registerBinding("entrys.ActualAmount", java.math.BigDecimal.class, this.kdtEntrys, "ActualAmount.text");
		dataBinder.registerBinding("entrys.SettlementType", java.lang.Object.class, this.kdtEntrys, "SettlementType.text");
		dataBinder.registerBinding("entrys.PayeeAccount", java.lang.Object.class, this.kdtEntrys, "PayeeAccount.text");
		dataBinder.registerBinding("entrys.OppAccount", java.lang.Object.class, this.kdtEntrys, "OppAccount.text");
		dataBinder.registerBinding("entrys.PayeeAccountBank", java.lang.Object.class, this.kdtEntrys, "PayeeAccountBank.text");
		dataBinder.registerBinding("entrys.PayeeAccountBank.bank", java.lang.Object.class, this.kdtEntrys, "Bank.text");
		dataBinder.registerBinding("entrys.SettlementNumber", String.class, this.kdtEntrys, "SettlementNumber.text");
		dataBinder.registerBinding("entrys.Remark", String.class, this.kdtEntrys, "Remark.text");
		dataBinder.registerBinding("entrys.Vehicle", java.lang.Object.class, this.kdtEntrys, "Vehicle.text");
		dataBinder.registerBinding("entrys.Vehicle.plateNum", String.class, this.kdtEntrys, "PlateNum.text");
		dataBinder.registerBinding("entrys.SourceBillType", java.lang.Object.class, this.kdtEntrys, "SourceBillType.text");
		dataBinder.registerBinding("entrys.LocalAmount", java.math.BigDecimal.class, this.kdtEntrys, "LocalAmount.text");
		dataBinder.registerBinding("entrys.LocalActualAmount", java.math.BigDecimal.class, this.kdtEntrys, "LocalActualAmount.text");
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtCreator, "data");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.kDDateCreateTime, "value");
		dataBinder.registerBinding("ReceiveObjectNumber", String.class, this.txtReceiveObjectNumber, "text");
		dataBinder.registerBinding("lastUpdateTime", java.sql.Timestamp.class, this.kDDateLastUpdateTime, "value");
		dataBinder.registerBinding("AuditTime", java.util.Date.class, this.pkAuditTime, "value");
		dataBinder.registerBinding("lastUpdateUser", com.kingdee.eas.base.permission.UserInfo.class, this.prmtLastUpdateUser, "data");
		dataBinder.registerBinding("ReceiveObjectID", String.class, this.txtReceiveObject, "text");
		dataBinder.registerBinding("auditor", com.kingdee.eas.base.permission.UserInfo.class, this.prmtAuditor, "data");
		dataBinder.registerBinding("card", com.kingdee.eas.auto4s.vip.mb.CardInfo.class, this.prmtcard, "data");
		dataBinder.registerBinding("paymentType", com.kingdee.eas.basedata.assistant.PaymentTypeInfo.class, this.prmtpaymentType, "data");
		dataBinder.registerBinding("ExchangeRate", java.math.BigDecimal.class, this.txtExchangeRate, "value");
		dataBinder.registerBinding("Department", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtDepartment, "data");
		dataBinder.registerBinding("srcEntryIds", String.class, this.txtsrcEntryIds, "text");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.auto4s.arp.aar.app.ReceivingBillEditUIHandler";
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
        this.pkDate.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.auto4s.arp.aar.ReceivingBillInfo)ov;
    }
    protected void removeByPK(IObjectPK pk) throws Exception {
    	IObjectValue editData = this.editData;
    	super.removeByPK(pk);
    	recycleNumberByOrg(editData,"Company",editData.getString("number"));
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
			return com.kingdee.eas.basedata.org.OrgType.getEnum("Company");
		}

	protected KDBizPromptBox getMainBizOrg() {
		return prmtOrgUnit;
}


    /**
     * output loadFields method
     */
    public void loadFields()
    {
        		setAutoNumberByOrg("Company");
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
		getValidateHelper().registerBindProperty("isAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ReceiveObjectName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("IsSendSms", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Date", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Status", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Person", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Currency", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("OrgUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Brand", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ReceiveObjectID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ReceiveObjectType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ActualPayer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("BizType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.RecBillType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.Amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.DiscountAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.ActualAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.Remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.SourceBillINumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.SourceBillEntrySeq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.Vehicle", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.OppAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.SourceBillIEntryID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.SourceBillID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.SourceBillType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.IsMorAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.TotalAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.TotalActualAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.savingCardAmt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.rechargeAmt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.presAmt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.cashCouponAmt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.pointToCashAmt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.card", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.pointAccountID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.cashAccountID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.isPointToCash", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.isUseCashAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.PRAbateAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.isPreReceive", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Content.Vehicle.plateNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Type.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Type", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Type.SettlementType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Type.PayeeAccountBank", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Type.SettlementNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Type.ActRecAmt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Type.PayeeAccountBank.bank", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Type.PayeeAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("receivingAmt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("WOPRAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("cashCouponAmt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("pointToCashAmt", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.RecBillType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.SourceBillIEntryID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.SourceBillID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.SourceBillINumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.SourceBillEntrySeq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.DiscountAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.ActualAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.SettlementType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.PayeeAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.OppAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.PayeeAccountBank", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.PayeeAccountBank.bank", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.SettlementNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Vehicle", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Vehicle.plateNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.SourceBillType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.LocalAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.LocalActualAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ReceiveObjectNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("AuditTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateUser", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ReceiveObjectID", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditor", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("card", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("paymentType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("ExchangeRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Department", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("srcEntryIds", ValidateHelper.ON_SAVE);    		
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
     * output actionAmtClean_actionPerformed method
     */
    protected void actionAmtClean_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnPRContent_actionPerformed method
     */
    protected void btnPRContent_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output prmtReceiveObject_dataChanged method
     */
    protected void prmtReceiveObject_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output kdtContent_editStopping method
     */
    protected void kdtContent_editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtContent_editStopped method
     */
    protected void kdtContent_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtType_editStopping method
     */
    protected void kdtType_editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output kdtType_editStopped method
     */
    protected void kdtType_editStopped(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output btnMoveReceivingTop_actionPerformed method
     */
    protected void btnMoveReceivingTop_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnMoveReceivingDown_actionPerformed method
     */
    protected void btnMoveReceivingDown_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnSavingsCard_actionPerformed method
     */
    protected void btnSavingsCard_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output txtreceivingAmt_actionPerformed method
     */
    protected void txtreceivingAmt_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output kdtEntrys_editStopping method
     */
    protected void kdtEntrys_editStopping(com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent e) throws Exception
    {
    }

    /**
     * output prmtvehicle_dataChanged method
     */
    protected void prmtvehicle_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output txtreadSN_keyPressed method
     */
    protected void txtreadSN_keyPressed(java.awt.event.KeyEvent e) throws Exception
    {
    }

    /**
     * output txtreadSN_focusLost method
     */
    protected void txtreadSN_focusLost(java.awt.event.FocusEvent e) throws Exception
    {
    }

    /**
     * output btnLoadDoc_actionPerformed method
     */
    protected void btnLoadDoc_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnAutoLoadAutoLoad_actionPerformed method
     */
    protected void btnAutoLoadAutoLoad_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }


    /**
     * output kdtContent_Changed(int rowIndex,int colIndex) method
     */
    public void kdtContent_Changed(int rowIndex,int colIndex) throws Exception
    {
            if ("Vehicle".equalsIgnoreCase(kdtContent.getColumn(colIndex).getKey())) {
kdtContent.getCell(rowIndex,"PlateNum").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtContent.getCell(rowIndex,"Vehicle").getValue(),"plateNum"));

}


    }

    /**
     * output kdtType_Changed(int rowIndex,int colIndex) method
     */
    public void kdtType_Changed(int rowIndex,int colIndex) throws Exception
    {
            if ("PayeeAccountBank".equalsIgnoreCase(kdtType.getColumn(colIndex).getKey())) {
kdtType.getCell(rowIndex,"Bank").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtType.getCell(rowIndex,"PayeeAccountBank").getValue(),"bank.name"));

}


    }

    /**
     * output kdtEntrys_Changed(int rowIndex,int colIndex) method
     */
    public void kdtEntrys_Changed(int rowIndex,int colIndex) throws Exception
    {
            if ("PayeeAccountBank".equalsIgnoreCase(kdtEntrys.getColumn(colIndex).getKey())) {
kdtEntrys.getCell(rowIndex,"Bank").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntrys.getCell(rowIndex,"PayeeAccountBank").getValue(),"bank.name"));

}

    if ("Vehicle".equalsIgnoreCase(kdtEntrys.getColumn(colIndex).getKey())) {
kdtEntrys.getCell(rowIndex,"PlateNum").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntrys.getCell(rowIndex,"Vehicle").getValue(),"plateNum"));

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
        sic.add(new SelectorItemInfo("isAccount"));
        sic.add(new SelectorItemInfo("ReceiveObjectName"));
        sic.add(new SelectorItemInfo("IsSendSms"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("bizDate"));
        sic.add(new SelectorItemInfo("description"));
        sic.add(new SelectorItemInfo("Date"));
        sic.add(new SelectorItemInfo("Status"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Person.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("Person.id"));
        	sic.add(new SelectorItemInfo("Person.number"));
        	sic.add(new SelectorItemInfo("Person.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Currency.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("Currency.id"));
        	sic.add(new SelectorItemInfo("Currency.number"));
        	sic.add(new SelectorItemInfo("Currency.name"));
		}
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
        sic.add(new SelectorItemInfo("ReceiveObjectID"));
        sic.add(new SelectorItemInfo("Remark"));
        sic.add(new SelectorItemInfo("ReceiveObjectType"));
        sic.add(new SelectorItemInfo("ActualPayer"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("BizType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("BizType.id"));
        	sic.add(new SelectorItemInfo("BizType.number"));
        	sic.add(new SelectorItemInfo("BizType.name"));
		}
    	sic.add(new SelectorItemInfo("Content.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Content.*"));
		}
		else{
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Content.RecBillType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("Content.RecBillType.id"));
			sic.add(new SelectorItemInfo("Content.RecBillType.name"));
        	sic.add(new SelectorItemInfo("Content.RecBillType.number"));
		}
    	sic.add(new SelectorItemInfo("Content.Amount"));
    	sic.add(new SelectorItemInfo("Content.DiscountAmount"));
    	sic.add(new SelectorItemInfo("Content.ActualAmount"));
    	sic.add(new SelectorItemInfo("Content.Remark"));
    	sic.add(new SelectorItemInfo("Content.SourceBillINumber"));
    	sic.add(new SelectorItemInfo("Content.SourceBillEntrySeq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Content.Vehicle.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("Content.Vehicle.id"));
			sic.add(new SelectorItemInfo("Content.Vehicle.vIN"));
			sic.add(new SelectorItemInfo("Content.Vehicle.name"));
        	sic.add(new SelectorItemInfo("Content.Vehicle.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Content.OppAccount.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("Content.OppAccount.id"));
			sic.add(new SelectorItemInfo("Content.OppAccount.name"));
        	sic.add(new SelectorItemInfo("Content.OppAccount.number"));
		}
    	sic.add(new SelectorItemInfo("Content.SourceBillIEntryID"));
    	sic.add(new SelectorItemInfo("Content.SourceBillID"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Content.SourceBillType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("Content.SourceBillType.id"));
			sic.add(new SelectorItemInfo("Content.SourceBillType.name"));
        	sic.add(new SelectorItemInfo("Content.SourceBillType.number"));
		}
    	sic.add(new SelectorItemInfo("Content.IsMorAccount"));
    	sic.add(new SelectorItemInfo("Content.TotalAmount"));
    	sic.add(new SelectorItemInfo("Content.TotalActualAmount"));
    	sic.add(new SelectorItemInfo("Content.savingCardAmt"));
    	sic.add(new SelectorItemInfo("Content.rechargeAmt"));
    	sic.add(new SelectorItemInfo("Content.presAmt"));
    	sic.add(new SelectorItemInfo("Content.cashCouponAmt"));
    	sic.add(new SelectorItemInfo("Content.pointToCashAmt"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Content.card.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("Content.card.id"));
			sic.add(new SelectorItemInfo("Content.card.number"));
		}
    	sic.add(new SelectorItemInfo("Content.pointAccountID"));
    	sic.add(new SelectorItemInfo("Content.cashAccountID"));
    	sic.add(new SelectorItemInfo("Content.isPointToCash"));
    	sic.add(new SelectorItemInfo("Content.isUseCashAccount"));
    	sic.add(new SelectorItemInfo("Content.PRAbateAmount"));
    	sic.add(new SelectorItemInfo("Content.isPreReceive"));
    	sic.add(new SelectorItemInfo("Content.Vehicle.plateNum"));
    	sic.add(new SelectorItemInfo("Type.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Type.*"));
		}
		else{
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Type.SettlementType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("Type.SettlementType.id"));
			sic.add(new SelectorItemInfo("Type.SettlementType.name"));
        	sic.add(new SelectorItemInfo("Type.SettlementType.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Type.PayeeAccountBank.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("Type.PayeeAccountBank.id"));
			sic.add(new SelectorItemInfo("Type.PayeeAccountBank.name"));
        	sic.add(new SelectorItemInfo("Type.PayeeAccountBank.number"));
		}
    	sic.add(new SelectorItemInfo("Type.SettlementNumber"));
    	sic.add(new SelectorItemInfo("Type.ActRecAmt"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Type.PayeeAccountBank.bank.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("Type.PayeeAccountBank.bank.id"));
			sic.add(new SelectorItemInfo("Type.PayeeAccountBank.bank.name"));
        	sic.add(new SelectorItemInfo("Type.PayeeAccountBank.bank.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Type.PayeeAccount.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("Type.PayeeAccount.id"));
			sic.add(new SelectorItemInfo("Type.PayeeAccount.name"));
        	sic.add(new SelectorItemInfo("Type.PayeeAccount.number"));
		}
        sic.add(new SelectorItemInfo("receivingAmt"));
        sic.add(new SelectorItemInfo("WOPRAmount"));
        sic.add(new SelectorItemInfo("cashCouponAmt"));
        sic.add(new SelectorItemInfo("pointToCashAmt"));
        sic.add(new SelectorItemInfo("amount"));
    	sic.add(new SelectorItemInfo("entrys.id"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.*"));
		}
		else{
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.RecBillType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.RecBillType.id"));
			sic.add(new SelectorItemInfo("entrys.RecBillType.name"));
        	sic.add(new SelectorItemInfo("entrys.RecBillType.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.SourceBillIEntryID"));
    	sic.add(new SelectorItemInfo("entrys.SourceBillID"));
    	sic.add(new SelectorItemInfo("entrys.SourceBillINumber"));
    	sic.add(new SelectorItemInfo("entrys.SourceBillEntrySeq"));
    	sic.add(new SelectorItemInfo("entrys.DiscountAmount"));
    	sic.add(new SelectorItemInfo("entrys.Amount"));
    	sic.add(new SelectorItemInfo("entrys.ActualAmount"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.SettlementType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.SettlementType.id"));
			sic.add(new SelectorItemInfo("entrys.SettlementType.name"));
        	sic.add(new SelectorItemInfo("entrys.SettlementType.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.PayeeAccount.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.PayeeAccount.id"));
			sic.add(new SelectorItemInfo("entrys.PayeeAccount.name"));
        	sic.add(new SelectorItemInfo("entrys.PayeeAccount.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.OppAccount.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.OppAccount.id"));
			sic.add(new SelectorItemInfo("entrys.OppAccount.name"));
        	sic.add(new SelectorItemInfo("entrys.OppAccount.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.PayeeAccountBank.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.PayeeAccountBank.id"));
			sic.add(new SelectorItemInfo("entrys.PayeeAccountBank.name"));
        	sic.add(new SelectorItemInfo("entrys.PayeeAccountBank.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.PayeeAccountBank.bank.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.PayeeAccountBank.bank.id"));
			sic.add(new SelectorItemInfo("entrys.PayeeAccountBank.bank.name"));
        	sic.add(new SelectorItemInfo("entrys.PayeeAccountBank.bank.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.SettlementNumber"));
    	sic.add(new SelectorItemInfo("entrys.Remark"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Vehicle.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Vehicle.id"));
			sic.add(new SelectorItemInfo("entrys.Vehicle.vIN"));
			sic.add(new SelectorItemInfo("entrys.Vehicle.name"));
        	sic.add(new SelectorItemInfo("entrys.Vehicle.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.Vehicle.plateNum"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.SourceBillType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.SourceBillType.id"));
			sic.add(new SelectorItemInfo("entrys.SourceBillType.name"));
        	sic.add(new SelectorItemInfo("entrys.SourceBillType.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.LocalAmount"));
    	sic.add(new SelectorItemInfo("entrys.LocalActualAmount"));
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
        sic.add(new SelectorItemInfo("ReceiveObjectNumber"));
        sic.add(new SelectorItemInfo("lastUpdateTime"));
        sic.add(new SelectorItemInfo("AuditTime"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("lastUpdateUser.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("lastUpdateUser.id"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.number"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("auditor.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("auditor.id"));
        	sic.add(new SelectorItemInfo("auditor.number"));
        	sic.add(new SelectorItemInfo("auditor.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("card.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("card.id"));
        	sic.add(new SelectorItemInfo("card.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("paymentType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("paymentType.id"));
        	sic.add(new SelectorItemInfo("paymentType.number"));
        	sic.add(new SelectorItemInfo("paymentType.name"));
		}
        sic.add(new SelectorItemInfo("ExchangeRate"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Department.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("Department.id"));
        	sic.add(new SelectorItemInfo("Department.number"));
        	sic.add(new SelectorItemInfo("Department.name"));
		}
        sic.add(new SelectorItemInfo("srcEntryIds"));
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
     * output actionAudit_actionPerformed method
     */
    public void actionAudit_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionFaudit_actionPerformed method
     */
    public void actionFaudit_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewCustomer_actionPerformed method
     */
    public void actionViewCustomer_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewVehicle_actionPerformed method
     */
    public void actionViewVehicle_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionLoadDoc_actionPerformed method
     */
    public void actionLoadDoc_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAddLines_actionPerformed method
     */
    public void actionAddLines_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionSplitLines_actionPerformed method
     */
    public void actionSplitLines_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionInsetLines_actionPerformed method
     */
    public void actionInsetLines_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionDeleteLines_actionPerformed method
     */
    public void actionDeleteLines_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionTotal_actionPerformed method
     */
    public void actionTotal_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionViewSource_actionPerformed method
     */
    public void actionViewSource_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCommitSettle_actionPerformed method
     */
    public void actionCommitSettle_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAutoLoad_actionPerformed method
     */
    public void actionAutoLoad_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCancelRec_actionPerformed method
     */
    public void actionCancelRec_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionRec_actionPerformed method
     */
    public void actionRec_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionMoveTypeTop_actionPerformed method
     */
    public void actionMoveTypeTop_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionMoveTypeDown_actionPerformed method
     */
    public void actionMoveTypeDown_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionMoveReceivingTop_actionPerformed method
     */
    public void actionMoveReceivingTop_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionMoveReceivingDown_actionPerformed method
     */
    public void actionMoveReceivingDown_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAddReceivingLines_actionPerformed method
     */
    public void actionAddReceivingLines_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionInsertReceivingLines_actionPerformed method
     */
    public void actionInsertReceivingLines_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionDeleteReceivingLines_actionPerformed method
     */
    public void actionDeleteReceivingLines_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionEasVoucher_actionPerformed method
     */
    public void actionEasVoucher_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionEasDelVoucher_actionPerformed method
     */
    public void actionEasDelVoucher_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionTotalLines_actionPerformed method
     */
    public void actionTotalLines_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionSavingsCard_actionPerformed method
     */
    public void actionSavingsCard_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionGiftCard_actionPerformed method
     */
    public void actionGiftCard_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionScoreCard_actionPerformed method
     */
    public void actionScoreCard_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionPRContent_actionPerformed method
     */
    public void actionPRContent_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionPRWriteOff_actionPerformed method
     */
    public void actionPRWriteOff_actionPerformed(ActionEvent e) throws Exception
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
	public RequestContext prepareActionFaudit(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionFaudit() {
    	return false;
    }
	public RequestContext prepareActionViewCustomer(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionViewCustomer() {
    	return false;
    }
	public RequestContext prepareActionViewVehicle(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionViewVehicle() {
    	return false;
    }
	public RequestContext prepareActionLoadDoc(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionLoadDoc() {
    	return false;
    }
	public RequestContext prepareActionAddLines(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAddLines() {
    	return false;
    }
	public RequestContext prepareActionSplitLines(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSplitLines() {
    	return false;
    }
	public RequestContext prepareActionInsetLines(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionInsetLines() {
    	return false;
    }
	public RequestContext prepareActionDeleteLines(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionDeleteLines() {
    	return false;
    }
	public RequestContext prepareActionTotal(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionTotal() {
    	return false;
    }
	public RequestContext prepareActionViewSource(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionViewSource() {
    	return false;
    }
	public RequestContext prepareActionCommitSettle(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCommitSettle() {
    	return false;
    }
	public RequestContext prepareActionAutoLoad(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAutoLoad() {
    	return false;
    }
	public RequestContext prepareactionCancelRec(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionCancelRec() {
    	return false;
    }
	public RequestContext prepareactionRec(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionRec() {
    	return false;
    }
	public RequestContext prepareactionMoveTypeTop(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionMoveTypeTop() {
    	return false;
    }
	public RequestContext prepareactionMoveTypeDown(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionMoveTypeDown() {
    	return false;
    }
	public RequestContext prepareactionMoveReceivingTop(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionMoveReceivingTop() {
    	return false;
    }
	public RequestContext prepareactionMoveReceivingDown(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionMoveReceivingDown() {
    	return false;
    }
	public RequestContext prepareactionAddReceivingLines(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionAddReceivingLines() {
    	return false;
    }
	public RequestContext prepareactionInsertReceivingLines(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionInsertReceivingLines() {
    	return false;
    }
	public RequestContext prepareactionDeleteReceivingLines(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionDeleteReceivingLines() {
    	return false;
    }
	public RequestContext prepareActionEasVoucher(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionEasVoucher() {
    	return false;
    }
	public RequestContext prepareActionEasDelVoucher(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionEasDelVoucher() {
    	return false;
    }
	public RequestContext prepareActionTotalLines(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionTotalLines() {
    	return false;
    }
	public RequestContext prepareactionSavingsCard(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionSavingsCard() {
    	return false;
    }
	public RequestContext prepareactionGiftCard(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionGiftCard() {
    	return false;
    }
	public RequestContext prepareactionScoreCard(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionScoreCard() {
    	return false;
    }
	public RequestContext prepareActionPRContent(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPRContent() {
    	return false;
    }
	public RequestContext prepareActionPRWriteOff(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPRWriteOff() {
    	return false;
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
            this.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl U"));
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
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionAudit", "actionAudit_actionPerformed", e);
        }
    }

    /**
     * output ActionFaudit class
     */     
    protected class ActionFaudit extends ItemAction {     
    
        public ActionFaudit()
        {
            this(null);
        }

        public ActionFaudit(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift U"));
            _tempStr = resHelper.getString("ActionFaudit.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionFaudit.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionFaudit.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionFaudit", "actionFaudit_actionPerformed", e);
        }
    }

    /**
     * output ActionViewCustomer class
     */     
    protected class ActionViewCustomer extends ItemAction {     
    
        public ActionViewCustomer()
        {
            this(null);
        }

        public ActionViewCustomer(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionViewCustomer.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewCustomer.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewCustomer.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionViewCustomer", "actionViewCustomer_actionPerformed", e);
        }
    }

    /**
     * output ActionViewVehicle class
     */     
    protected class ActionViewVehicle extends ItemAction {     
    
        public ActionViewVehicle()
        {
            this(null);
        }

        public ActionViewVehicle(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionViewVehicle.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewVehicle.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewVehicle.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionViewVehicle", "actionViewVehicle_actionPerformed", e);
        }
    }

    /**
     * output ActionLoadDoc class
     */     
    protected class ActionLoadDoc extends ItemAction {     
    
        public ActionLoadDoc()
        {
            this(null);
        }

        public ActionLoadDoc(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionLoadDoc.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionLoadDoc.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionLoadDoc.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionLoadDoc", "actionLoadDoc_actionPerformed", e);
        }
    }

    /**
     * output ActionAddLines class
     */     
    protected class ActionAddLines extends ItemAction {     
    
        public ActionAddLines()
        {
            this(null);
        }

        public ActionAddLines(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionAddLines.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddLines.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddLines.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionAddLines", "actionAddLines_actionPerformed", e);
        }
    }

    /**
     * output ActionSplitLines class
     */     
    protected class ActionSplitLines extends ItemAction {     
    
        public ActionSplitLines()
        {
            this(null);
        }

        public ActionSplitLines(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionSplitLines.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSplitLines.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSplitLines.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionSplitLines", "actionSplitLines_actionPerformed", e);
        }
    }

    /**
     * output ActionInsetLines class
     */     
    protected class ActionInsetLines extends ItemAction {     
    
        public ActionInsetLines()
        {
            this(null);
        }

        public ActionInsetLines(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionInsetLines.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionInsetLines.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionInsetLines.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionInsetLines", "actionInsetLines_actionPerformed", e);
        }
    }

    /**
     * output ActionDeleteLines class
     */     
    protected class ActionDeleteLines extends ItemAction {     
    
        public ActionDeleteLines()
        {
            this(null);
        }

        public ActionDeleteLines(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionDeleteLines.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionDeleteLines.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionDeleteLines.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionDeleteLines", "actionDeleteLines_actionPerformed", e);
        }
    }

    /**
     * output ActionTotal class
     */     
    protected class ActionTotal extends ItemAction {     
    
        public ActionTotal()
        {
            this(null);
        }

        public ActionTotal(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionTotal.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTotal.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTotal.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionTotal", "actionTotal_actionPerformed", e);
        }
    }

    /**
     * output ActionViewSource class
     */     
    protected class ActionViewSource extends ItemAction {     
    
        public ActionViewSource()
        {
            this(null);
        }

        public ActionViewSource(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionViewSource.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewSource.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionViewSource.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionViewSource", "actionViewSource_actionPerformed", e);
        }
    }

    /**
     * output ActionCommitSettle class
     */     
    protected class ActionCommitSettle extends ItemAction {     
    
        public ActionCommitSettle()
        {
            this(null);
        }

        public ActionCommitSettle(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionCommitSettle.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCommitSettle.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCommitSettle.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionCommitSettle", "actionCommitSettle_actionPerformed", e);
        }
    }

    /**
     * output ActionAutoLoad class
     */     
    protected class ActionAutoLoad extends ItemAction {     
    
        public ActionAutoLoad()
        {
            this(null);
        }

        public ActionAutoLoad(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAutoLoad.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAutoLoad.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAutoLoad.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionAutoLoad", "actionAutoLoad_actionPerformed", e);
        }
    }

    /**
     * output actionCancelRec class
     */     
    protected class actionCancelRec extends ItemAction {     
    
        public actionCancelRec()
        {
            this(null);
        }

        public actionCancelRec(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionCancelRec.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionCancelRec.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionCancelRec.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionCancelRec", "actionCancelRec_actionPerformed", e);
        }
    }

    /**
     * output actionRec class
     */     
    protected class actionRec extends ItemAction {     
    
        public actionRec()
        {
            this(null);
        }

        public actionRec(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionRec.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionRec.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionRec.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionRec", "actionRec_actionPerformed", e);
        }
    }

    /**
     * output actionMoveTypeTop class
     */     
    protected class actionMoveTypeTop extends ItemAction {     
    
        public actionMoveTypeTop()
        {
            this(null);
        }

        public actionMoveTypeTop(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("actionMoveTypeTop.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionMoveTypeTop.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionMoveTypeTop.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionMoveTypeTop", "actionMoveTypeTop_actionPerformed", e);
        }
    }

    /**
     * output actionMoveTypeDown class
     */     
    protected class actionMoveTypeDown extends ItemAction {     
    
        public actionMoveTypeDown()
        {
            this(null);
        }

        public actionMoveTypeDown(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionMoveTypeDown.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionMoveTypeDown.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionMoveTypeDown.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionMoveTypeDown", "actionMoveTypeDown_actionPerformed", e);
        }
    }

    /**
     * output actionMoveReceivingTop class
     */     
    protected class actionMoveReceivingTop extends ItemAction {     
    
        public actionMoveReceivingTop()
        {
            this(null);
        }

        public actionMoveReceivingTop(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionMoveReceivingTop.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionMoveReceivingTop.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionMoveReceivingTop.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionMoveReceivingTop", "actionMoveReceivingTop_actionPerformed", e);
        }
    }

    /**
     * output actionMoveReceivingDown class
     */     
    protected class actionMoveReceivingDown extends ItemAction {     
    
        public actionMoveReceivingDown()
        {
            this(null);
        }

        public actionMoveReceivingDown(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionMoveReceivingDown.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionMoveReceivingDown.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionMoveReceivingDown.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionMoveReceivingDown", "actionMoveReceivingDown_actionPerformed", e);
        }
    }

    /**
     * output actionAddReceivingLines class
     */     
    protected class actionAddReceivingLines extends ItemAction {     
    
        public actionAddReceivingLines()
        {
            this(null);
        }

        public actionAddReceivingLines(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionAddReceivingLines.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAddReceivingLines.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAddReceivingLines.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionAddReceivingLines", "actionAddReceivingLines_actionPerformed", e);
        }
    }

    /**
     * output actionInsertReceivingLines class
     */     
    protected class actionInsertReceivingLines extends ItemAction {     
    
        public actionInsertReceivingLines()
        {
            this(null);
        }

        public actionInsertReceivingLines(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionInsertReceivingLines.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionInsertReceivingLines.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionInsertReceivingLines.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionInsertReceivingLines", "actionInsertReceivingLines_actionPerformed", e);
        }
    }

    /**
     * output actionDeleteReceivingLines class
     */     
    protected class actionDeleteReceivingLines extends ItemAction {     
    
        public actionDeleteReceivingLines()
        {
            this(null);
        }

        public actionDeleteReceivingLines(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionDeleteReceivingLines.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionDeleteReceivingLines.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionDeleteReceivingLines.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionDeleteReceivingLines", "actionDeleteReceivingLines_actionPerformed", e);
        }
    }

    /**
     * output ActionEasVoucher class
     */     
    protected class ActionEasVoucher extends ItemAction {     
    
        public ActionEasVoucher()
        {
            this(null);
        }

        public ActionEasVoucher(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionEasVoucher.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionEasVoucher.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionEasVoucher.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionEasVoucher", "actionEasVoucher_actionPerformed", e);
        }
    }

    /**
     * output ActionEasDelVoucher class
     */     
    protected class ActionEasDelVoucher extends ItemAction {     
    
        public ActionEasDelVoucher()
        {
            this(null);
        }

        public ActionEasDelVoucher(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionEasDelVoucher.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionEasDelVoucher.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionEasDelVoucher.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionEasDelVoucher", "actionEasDelVoucher_actionPerformed", e);
        }
    }

    /**
     * output ActionTotalLines class
     */     
    protected class ActionTotalLines extends ItemAction {     
    
        public ActionTotalLines()
        {
            this(null);
        }

        public ActionTotalLines(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionTotalLines.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTotalLines.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionTotalLines.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionTotalLines", "actionTotalLines_actionPerformed", e);
        }
    }

    /**
     * output actionSavingsCard class
     */     
    protected class actionSavingsCard extends ItemAction {     
    
        public actionSavingsCard()
        {
            this(null);
        }

        public actionSavingsCard(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionSavingsCard.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionSavingsCard.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionSavingsCard.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionSavingsCard", "actionSavingsCard_actionPerformed", e);
        }
    }

    /**
     * output actionGiftCard class
     */     
    protected class actionGiftCard extends ItemAction {     
    
        public actionGiftCard()
        {
            this(null);
        }

        public actionGiftCard(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionGiftCard.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionGiftCard.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionGiftCard.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionGiftCard", "actionGiftCard_actionPerformed", e);
        }
    }

    /**
     * output actionScoreCard class
     */     
    protected class actionScoreCard extends ItemAction {     
    
        public actionScoreCard()
        {
            this(null);
        }

        public actionScoreCard(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionScoreCard.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionScoreCard.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionScoreCard.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "actionScoreCard", "actionScoreCard_actionPerformed", e);
        }
    }

    /**
     * output ActionPRContent class
     */     
    protected class ActionPRContent extends ItemAction {     
    
        public ActionPRContent()
        {
            this(null);
        }

        public ActionPRContent(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionPRContent.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionPRContent.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionPRContent.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionPRContent", "actionPRContent_actionPerformed", e);
        }
    }

    /**
     * output ActionPRWriteOff class
     */     
    protected class ActionPRWriteOff extends ItemAction {     
    
        public ActionPRWriteOff()
        {
            this(null);
        }

        public ActionPRWriteOff(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionPRWriteOff.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionPRWriteOff.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionPRWriteOff.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractReceivingBillEditUI.this, "ActionPRWriteOff", "actionPRWriteOff_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.auto4s.arp.aar.client", "ReceivingBillEditUI");
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
        return com.kingdee.eas.auto4s.arp.aar.client.ReceivingBillEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.auto4s.arp.aar.ReceivingBillFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.auto4s.arp.aar.ReceivingBillInfo objectValue = new com.kingdee.eas.auto4s.arp.aar.ReceivingBillInfo();
				if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")) != null && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")).getBoolean("isBizUnit"))
			objectValue.put("OrgUnit",com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")));
 
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/auto4s/arp/aar/ReceivingBill";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.auto4s.arp.aar.app.ReceivingBillQuery");
	}
    
        
					protected void beforeStoreFields(ActionEvent arg0) throws Exception {
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtActualPayer.getText())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"实际缴款人"});
		}
			super.beforeStoreFields(arg0);
		}

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kdtContent;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("Status","2");
vo.put("ReceiveObjectType","1");
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}        
				protected void setTableToSumField() {
			setTableToSumField(kdtContent,new String[] {"Amount","DiscountAmount","ActualAmount"});
			setTableToSumField(kdtType,new String[] {"ActRecAmt"});
			setTableToSumField(kdtEntrys,new String[] {"Amount","DiscountAmount","ActualAmount","LocalAmount","LocalActualAmount"});
		}


}