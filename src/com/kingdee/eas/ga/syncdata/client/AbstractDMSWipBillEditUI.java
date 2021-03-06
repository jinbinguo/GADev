/**
 * output package name
 */
package com.kingdee.eas.ga.syncdata.client;

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
public abstract class AbstractDMSWipBillEditUI extends com.kingdee.eas.myframework.template.base.client.SimpleBizBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractDMSWipBillEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kDTabbedPane1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contserviceOrgUnit;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel2;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel3;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel4;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtEntry2;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtEntry2_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtEntry3;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtEntry3_detailPanel = null;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtserviceOrgUnit;
    protected com.kingdee.eas.ga.syncdata.DMSWipBillInfo editData = null;
    /**
     * output class constructor
     */
    public AbstractDMSWipBillEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractDMSWipBillEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSubmit
        String _tempStr = null;
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        actionSubmit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl s"));
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
        //actionAddNew
        actionAddNew.setEnabled(true);
        actionAddNew.setDaemonRun(false);

        actionAddNew.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl n"));
        _tempStr = resHelper.getString("ActionAddNew.SHORT_DESCRIPTION");
        actionAddNew.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAddNew.LONG_DESCRIPTION");
        actionAddNew.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAddNew.NAME");
        actionAddNew.putValue(ItemAction.NAME, _tempStr);
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionAudit
        actionAudit.setEnabled(true);
        actionAudit.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionAudit.SHORT_DESCRIPTION");
        actionAudit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAudit.LONG_DESCRIPTION");
        actionAudit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionAudit.NAME");
        actionAudit.putValue(ItemAction.NAME, _tempStr);
        this.actionAudit.setBindWorkFlow(true);
        this.actionAudit.setExtendProperty("canForewarn", "true");
        this.actionAudit.setExtendProperty("userDefined", "true");
        this.actionAudit.setExtendProperty("isObjectUpdateLock", "false");
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
         this.actionAudit.addService(new com.kingdee.eas.framework.client.service.WorkFlowService());
        //actionUnAudit
        actionUnAudit.setEnabled(true);
        actionUnAudit.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionUnAudit.SHORT_DESCRIPTION");
        actionUnAudit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionUnAudit.LONG_DESCRIPTION");
        actionUnAudit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionUnAudit.NAME");
        actionUnAudit.putValue(ItemAction.NAME, _tempStr);
        this.actionUnAudit.setBindWorkFlow(true);
        this.actionUnAudit.setExtendProperty("canForewarn", "true");
        this.actionUnAudit.setExtendProperty("userDefined", "true");
        this.actionUnAudit.setExtendProperty("isObjectUpdateLock", "true");
         this.actionUnAudit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionUnAudit.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
         this.actionUnAudit.addService(new com.kingdee.eas.framework.client.service.WorkFlowService());
        this.kDTabbedPane1 = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.contserviceOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDPanel2 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel3 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel4 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kdtEntry2 = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtEntry3 = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.prmtserviceOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDTabbedPane1.setName("kDTabbedPane1");
        this.contserviceOrgUnit.setName("contserviceOrgUnit");
        this.kDPanel2.setName("kDPanel2");
        this.kDPanel3.setName("kDPanel3");
        this.kDPanel4.setName("kDPanel4");
        this.kdtEntry2.setName("kdtEntry2");
        this.kdtEntry3.setName("kdtEntry3");
        this.prmtserviceOrgUnit.setName("prmtserviceOrgUnit");
        // CoreUI		
        this.contBizDate.setBoundLabelText(resHelper.getString("contBizDate.boundLabelText"));		
        this.contBizDate.setEnabled(false);		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));
		String kdtEntrysStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol3\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol7\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol8\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"vin\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"createTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"mileage\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol3\" /><t:Column t:key=\"plateNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"wip\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"accountCode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"lineDesc\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol7\" /><t:Column t:key=\"lineStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol8\" /><t:Column t:key=\"deptNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{vin}</t:Cell><t:Cell>$Resource{createTime}</t:Cell><t:Cell>$Resource{mileage}</t:Cell><t:Cell>$Resource{plateNum}</t:Cell><t:Cell>$Resource{wip}</t:Cell><t:Cell>$Resource{accountCode}</t:Cell><t:Cell>$Resource{lineDesc}</t:Cell><t:Cell>$Resource{lineStatus}</t:Cell><t:Cell>$Resource{deptNum}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtEntrys.setFormatXml(resHelper.translateString("kdtEntrys",kdtEntrysStrXML));

                this.kdtEntrys.putBindContents("editData",new String[] {"","vin","createTime","mileage","plateNum","wip","accountCode","lineDesc","lineStatus","deptNum"});


        this.kdtEntrys.checkParsed();
        KDTextField kdtEntrys_vin_TextField = new KDTextField();
        kdtEntrys_vin_TextField.setName("kdtEntrys_vin_TextField");
        kdtEntrys_vin_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_vin_CellEditor = new KDTDefaultCellEditor(kdtEntrys_vin_TextField);
        this.kdtEntrys.getColumn("vin").setEditor(kdtEntrys_vin_CellEditor);
        KDDatePicker kdtEntrys_createTime_DatePicker = new KDDatePicker();
        kdtEntrys_createTime_DatePicker.setName("kdtEntrys_createTime_DatePicker");
        kdtEntrys_createTime_DatePicker.setVisible(true);
        kdtEntrys_createTime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtEntrys_createTime_CellEditor = new KDTDefaultCellEditor(kdtEntrys_createTime_DatePicker);
        this.kdtEntrys.getColumn("createTime").setEditor(kdtEntrys_createTime_CellEditor);
        KDFormattedTextField kdtEntrys_mileage_TextField = new KDFormattedTextField();
        kdtEntrys_mileage_TextField.setName("kdtEntrys_mileage_TextField");
        kdtEntrys_mileage_TextField.setVisible(true);
        kdtEntrys_mileage_TextField.setEditable(true);
        kdtEntrys_mileage_TextField.setHorizontalAlignment(2);
        kdtEntrys_mileage_TextField.setDataType(1);
        	kdtEntrys_mileage_TextField.setMinimumValue(new java.math.BigDecimal("-3.4028234663852886E38"));
        	kdtEntrys_mileage_TextField.setMaximumValue(new java.math.BigDecimal("3.4028234663852886E38"));
        kdtEntrys_mileage_TextField.setPrecision(16);
        KDTDefaultCellEditor kdtEntrys_mileage_CellEditor = new KDTDefaultCellEditor(kdtEntrys_mileage_TextField);
        this.kdtEntrys.getColumn("mileage").setEditor(kdtEntrys_mileage_CellEditor);
        KDTextField kdtEntrys_plateNum_TextField = new KDTextField();
        kdtEntrys_plateNum_TextField.setName("kdtEntrys_plateNum_TextField");
        kdtEntrys_plateNum_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_plateNum_CellEditor = new KDTDefaultCellEditor(kdtEntrys_plateNum_TextField);
        this.kdtEntrys.getColumn("plateNum").setEditor(kdtEntrys_plateNum_CellEditor);
        KDTextField kdtEntrys_wip_TextField = new KDTextField();
        kdtEntrys_wip_TextField.setName("kdtEntrys_wip_TextField");
        kdtEntrys_wip_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_wip_CellEditor = new KDTDefaultCellEditor(kdtEntrys_wip_TextField);
        this.kdtEntrys.getColumn("wip").setEditor(kdtEntrys_wip_CellEditor);
        KDTextField kdtEntrys_accountCode_TextField = new KDTextField();
        kdtEntrys_accountCode_TextField.setName("kdtEntrys_accountCode_TextField");
        kdtEntrys_accountCode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_accountCode_CellEditor = new KDTDefaultCellEditor(kdtEntrys_accountCode_TextField);
        this.kdtEntrys.getColumn("accountCode").setEditor(kdtEntrys_accountCode_CellEditor);
        KDComboBox kdtEntrys_lineStatus_ComboBox = new KDComboBox();
        kdtEntrys_lineStatus_ComboBox.setName("kdtEntrys_lineStatus_ComboBox");
        kdtEntrys_lineStatus_ComboBox.setVisible(true);
        kdtEntrys_lineStatus_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.scm.common.EntryBaseStatusEnum").toArray());
        KDTDefaultCellEditor kdtEntrys_lineStatus_CellEditor = new KDTDefaultCellEditor(kdtEntrys_lineStatus_ComboBox);
        this.kdtEntrys.getColumn("lineStatus").setEditor(kdtEntrys_lineStatus_CellEditor);
        KDTextField kdtEntrys_deptNum_TextField = new KDTextField();
        kdtEntrys_deptNum_TextField.setName("kdtEntrys_deptNum_TextField");
        kdtEntrys_deptNum_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_deptNum_CellEditor = new KDTDefaultCellEditor(kdtEntrys_deptNum_TextField);
        this.kdtEntrys.getColumn("deptNum").setEditor(kdtEntrys_deptNum_CellEditor);		
        this.pkBizDate.setTimeEnabled(true);
        // kDTabbedPane1
        // contserviceOrgUnit		
        this.contserviceOrgUnit.setBoundLabelText(resHelper.getString("contserviceOrgUnit.boundLabelText"));		
        this.contserviceOrgUnit.setBoundLabelLength(100);		
        this.contserviceOrgUnit.setBoundLabelUnderline(true);		
        this.contserviceOrgUnit.setVisible(true);		
        this.contserviceOrgUnit.setEnabled(false);
        // kDPanel2
        // kDPanel3
        // kDPanel4
        // kdtEntry2
		String kdtEntry2StrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol2\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol3\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol5\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol8\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol9\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol12\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol17\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"accountCode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"realLineSeq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol2\" /><t:Column t:key=\"lastEditTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol3\" /><t:Column t:key=\"remark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"discountRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol5\" /><t:Column t:key=\"billNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"billStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"lineSeq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol8\" /><t:Column t:key=\"orderQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol9\" /><t:Column t:key=\"orderStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"materialNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"salePrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol12\" /><t:Column t:key=\"taxBillCode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"wip\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"chaimCode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"saleType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"costPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol17\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{accountCode}</t:Cell><t:Cell>$Resource{realLineSeq}</t:Cell><t:Cell>$Resource{lastEditTime}</t:Cell><t:Cell>$Resource{remark}</t:Cell><t:Cell>$Resource{discountRate}</t:Cell><t:Cell>$Resource{billNum}</t:Cell><t:Cell>$Resource{billStatus}</t:Cell><t:Cell>$Resource{lineSeq}</t:Cell><t:Cell>$Resource{orderQty}</t:Cell><t:Cell>$Resource{orderStatus}</t:Cell><t:Cell>$Resource{materialNum}</t:Cell><t:Cell>$Resource{salePrice}</t:Cell><t:Cell>$Resource{taxBillCode}</t:Cell><t:Cell>$Resource{wip}</t:Cell><t:Cell>$Resource{chaimCode}</t:Cell><t:Cell>$Resource{saleType}</t:Cell><t:Cell>$Resource{costPrice}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtEntry2.setFormatXml(resHelper.translateString("kdtEntry2",kdtEntry2StrXML));

                this.kdtEntry2.putBindContents("editData",new String[] {"seq","accountCode","realLineSeq","lastEditTime","remark","discountRate","billNum","billStatus","lineSeq","orderQty","orderStatus","materialNum","salePrice","taxBillCode","wip","chaimCode","saleType","costPrice"});


        this.kdtEntry2.checkParsed();
        KDTextField kdtEntry2_accountCode_TextField = new KDTextField();
        kdtEntry2_accountCode_TextField.setName("kdtEntry2_accountCode_TextField");
        kdtEntry2_accountCode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry2_accountCode_CellEditor = new KDTDefaultCellEditor(kdtEntry2_accountCode_TextField);
        this.kdtEntry2.getColumn("accountCode").setEditor(kdtEntry2_accountCode_CellEditor);
        KDFormattedTextField kdtEntry2_realLineSeq_TextField = new KDFormattedTextField();
        kdtEntry2_realLineSeq_TextField.setName("kdtEntry2_realLineSeq_TextField");
        kdtEntry2_realLineSeq_TextField.setVisible(true);
        kdtEntry2_realLineSeq_TextField.setEditable(true);
        kdtEntry2_realLineSeq_TextField.setHorizontalAlignment(2);
        kdtEntry2_realLineSeq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntry2_realLineSeq_CellEditor = new KDTDefaultCellEditor(kdtEntry2_realLineSeq_TextField);
        this.kdtEntry2.getColumn("realLineSeq").setEditor(kdtEntry2_realLineSeq_CellEditor);
        KDDatePicker kdtEntry2_lastEditTime_DatePicker = new KDDatePicker();
        kdtEntry2_lastEditTime_DatePicker.setName("kdtEntry2_lastEditTime_DatePicker");
        kdtEntry2_lastEditTime_DatePicker.setVisible(true);
        kdtEntry2_lastEditTime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtEntry2_lastEditTime_CellEditor = new KDTDefaultCellEditor(kdtEntry2_lastEditTime_DatePicker);
        this.kdtEntry2.getColumn("lastEditTime").setEditor(kdtEntry2_lastEditTime_CellEditor);
        KDTextField kdtEntry2_remark_TextField = new KDTextField();
        kdtEntry2_remark_TextField.setName("kdtEntry2_remark_TextField");
        kdtEntry2_remark_TextField.setMaxLength(255);
        KDTDefaultCellEditor kdtEntry2_remark_CellEditor = new KDTDefaultCellEditor(kdtEntry2_remark_TextField);
        this.kdtEntry2.getColumn("remark").setEditor(kdtEntry2_remark_CellEditor);
        KDFormattedTextField kdtEntry2_discountRate_TextField = new KDFormattedTextField();
        kdtEntry2_discountRate_TextField.setName("kdtEntry2_discountRate_TextField");
        kdtEntry2_discountRate_TextField.setVisible(true);
        kdtEntry2_discountRate_TextField.setEditable(true);
        kdtEntry2_discountRate_TextField.setHorizontalAlignment(2);
        kdtEntry2_discountRate_TextField.setDataType(1);
        	kdtEntry2_discountRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntry2_discountRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntry2_discountRate_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntry2_discountRate_CellEditor = new KDTDefaultCellEditor(kdtEntry2_discountRate_TextField);
        this.kdtEntry2.getColumn("discountRate").setEditor(kdtEntry2_discountRate_CellEditor);
        KDTextField kdtEntry2_billNum_TextField = new KDTextField();
        kdtEntry2_billNum_TextField.setName("kdtEntry2_billNum_TextField");
        kdtEntry2_billNum_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry2_billNum_CellEditor = new KDTDefaultCellEditor(kdtEntry2_billNum_TextField);
        this.kdtEntry2.getColumn("billNum").setEditor(kdtEntry2_billNum_CellEditor);
        KDTextField kdtEntry2_billStatus_TextField = new KDTextField();
        kdtEntry2_billStatus_TextField.setName("kdtEntry2_billStatus_TextField");
        kdtEntry2_billStatus_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry2_billStatus_CellEditor = new KDTDefaultCellEditor(kdtEntry2_billStatus_TextField);
        this.kdtEntry2.getColumn("billStatus").setEditor(kdtEntry2_billStatus_CellEditor);
        KDFormattedTextField kdtEntry2_lineSeq_TextField = new KDFormattedTextField();
        kdtEntry2_lineSeq_TextField.setName("kdtEntry2_lineSeq_TextField");
        kdtEntry2_lineSeq_TextField.setVisible(true);
        kdtEntry2_lineSeq_TextField.setEditable(true);
        kdtEntry2_lineSeq_TextField.setHorizontalAlignment(2);
        kdtEntry2_lineSeq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntry2_lineSeq_CellEditor = new KDTDefaultCellEditor(kdtEntry2_lineSeq_TextField);
        this.kdtEntry2.getColumn("lineSeq").setEditor(kdtEntry2_lineSeq_CellEditor);
        KDFormattedTextField kdtEntry2_orderQty_TextField = new KDFormattedTextField();
        kdtEntry2_orderQty_TextField.setName("kdtEntry2_orderQty_TextField");
        kdtEntry2_orderQty_TextField.setVisible(true);
        kdtEntry2_orderQty_TextField.setEditable(true);
        kdtEntry2_orderQty_TextField.setHorizontalAlignment(2);
        kdtEntry2_orderQty_TextField.setDataType(1);
        	kdtEntry2_orderQty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntry2_orderQty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntry2_orderQty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntry2_orderQty_CellEditor = new KDTDefaultCellEditor(kdtEntry2_orderQty_TextField);
        this.kdtEntry2.getColumn("orderQty").setEditor(kdtEntry2_orderQty_CellEditor);
        KDTextField kdtEntry2_orderStatus_TextField = new KDTextField();
        kdtEntry2_orderStatus_TextField.setName("kdtEntry2_orderStatus_TextField");
        kdtEntry2_orderStatus_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry2_orderStatus_CellEditor = new KDTDefaultCellEditor(kdtEntry2_orderStatus_TextField);
        this.kdtEntry2.getColumn("orderStatus").setEditor(kdtEntry2_orderStatus_CellEditor);
        KDTextField kdtEntry2_materialNum_TextField = new KDTextField();
        kdtEntry2_materialNum_TextField.setName("kdtEntry2_materialNum_TextField");
        kdtEntry2_materialNum_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry2_materialNum_CellEditor = new KDTDefaultCellEditor(kdtEntry2_materialNum_TextField);
        this.kdtEntry2.getColumn("materialNum").setEditor(kdtEntry2_materialNum_CellEditor);
        KDFormattedTextField kdtEntry2_salePrice_TextField = new KDFormattedTextField();
        kdtEntry2_salePrice_TextField.setName("kdtEntry2_salePrice_TextField");
        kdtEntry2_salePrice_TextField.setVisible(true);
        kdtEntry2_salePrice_TextField.setEditable(true);
        kdtEntry2_salePrice_TextField.setHorizontalAlignment(2);
        kdtEntry2_salePrice_TextField.setDataType(1);
        	kdtEntry2_salePrice_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntry2_salePrice_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntry2_salePrice_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntry2_salePrice_CellEditor = new KDTDefaultCellEditor(kdtEntry2_salePrice_TextField);
        this.kdtEntry2.getColumn("salePrice").setEditor(kdtEntry2_salePrice_CellEditor);
        KDTextField kdtEntry2_taxBillCode_TextField = new KDTextField();
        kdtEntry2_taxBillCode_TextField.setName("kdtEntry2_taxBillCode_TextField");
        kdtEntry2_taxBillCode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry2_taxBillCode_CellEditor = new KDTDefaultCellEditor(kdtEntry2_taxBillCode_TextField);
        this.kdtEntry2.getColumn("taxBillCode").setEditor(kdtEntry2_taxBillCode_CellEditor);
        KDTextField kdtEntry2_wip_TextField = new KDTextField();
        kdtEntry2_wip_TextField.setName("kdtEntry2_wip_TextField");
        kdtEntry2_wip_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry2_wip_CellEditor = new KDTDefaultCellEditor(kdtEntry2_wip_TextField);
        this.kdtEntry2.getColumn("wip").setEditor(kdtEntry2_wip_CellEditor);
        KDTextField kdtEntry2_chaimCode_TextField = new KDTextField();
        kdtEntry2_chaimCode_TextField.setName("kdtEntry2_chaimCode_TextField");
        kdtEntry2_chaimCode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry2_chaimCode_CellEditor = new KDTDefaultCellEditor(kdtEntry2_chaimCode_TextField);
        this.kdtEntry2.getColumn("chaimCode").setEditor(kdtEntry2_chaimCode_CellEditor);
        KDTextField kdtEntry2_saleType_TextField = new KDTextField();
        kdtEntry2_saleType_TextField.setName("kdtEntry2_saleType_TextField");
        kdtEntry2_saleType_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry2_saleType_CellEditor = new KDTDefaultCellEditor(kdtEntry2_saleType_TextField);
        this.kdtEntry2.getColumn("saleType").setEditor(kdtEntry2_saleType_CellEditor);
        KDFormattedTextField kdtEntry2_costPrice_TextField = new KDFormattedTextField();
        kdtEntry2_costPrice_TextField.setName("kdtEntry2_costPrice_TextField");
        kdtEntry2_costPrice_TextField.setVisible(true);
        kdtEntry2_costPrice_TextField.setEditable(true);
        kdtEntry2_costPrice_TextField.setHorizontalAlignment(2);
        kdtEntry2_costPrice_TextField.setDataType(1);
        	kdtEntry2_costPrice_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntry2_costPrice_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntry2_costPrice_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntry2_costPrice_CellEditor = new KDTDefaultCellEditor(kdtEntry2_costPrice_TextField);
        this.kdtEntry2.getColumn("costPrice").setEditor(kdtEntry2_costPrice_CellEditor);
        // kdtEntry3
		String kdtEntry3StrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol2\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol3\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol4\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol6\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol7\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol9\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol10\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol17\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"accountCode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"realLineSeq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol2\" /><t:Column t:key=\"standardHour\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol3\" /><t:Column t:key=\"lastEditTime\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol4\" /><t:Column t:key=\"remark\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"discountRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol6\" /><t:Column t:key=\"lineSeq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol7\" /><t:Column t:key=\"dispatchStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"unitMI\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol9\" /><t:Column t:key=\"hourRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol10\" /><t:Column t:key=\"taxBillCode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"wip\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"payCode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"billNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"billStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"saleType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"postingDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol17\" /><t:Column t:key=\"rts\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{accountCode}</t:Cell><t:Cell>$Resource{realLineSeq}</t:Cell><t:Cell>$Resource{standardHour}</t:Cell><t:Cell>$Resource{lastEditTime}</t:Cell><t:Cell>$Resource{remark}</t:Cell><t:Cell>$Resource{discountRate}</t:Cell><t:Cell>$Resource{lineSeq}</t:Cell><t:Cell>$Resource{dispatchStatus}</t:Cell><t:Cell>$Resource{unitMI}</t:Cell><t:Cell>$Resource{hourRate}</t:Cell><t:Cell>$Resource{taxBillCode}</t:Cell><t:Cell>$Resource{wip}</t:Cell><t:Cell>$Resource{payCode}</t:Cell><t:Cell>$Resource{billNum}</t:Cell><t:Cell>$Resource{billStatus}</t:Cell><t:Cell>$Resource{saleType}</t:Cell><t:Cell>$Resource{postingDate}</t:Cell><t:Cell>$Resource{rts}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtEntry3.setFormatXml(resHelper.translateString("kdtEntry3",kdtEntry3StrXML));

                this.kdtEntry3.putBindContents("editData",new String[] {"seq","accountCode","realLineSeq","standardHour","lastEditTime","remark","discountRate","lineSeq","dispatchStatus","unitMI","hourRate","taxBillCode","wip","payCode","billNum","billStatus","saleType","postingDate","rts"});


        this.kdtEntry3.checkParsed();
        KDTextField kdtEntry3_accountCode_TextField = new KDTextField();
        kdtEntry3_accountCode_TextField.setName("kdtEntry3_accountCode_TextField");
        kdtEntry3_accountCode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry3_accountCode_CellEditor = new KDTDefaultCellEditor(kdtEntry3_accountCode_TextField);
        this.kdtEntry3.getColumn("accountCode").setEditor(kdtEntry3_accountCode_CellEditor);
        KDFormattedTextField kdtEntry3_realLineSeq_TextField = new KDFormattedTextField();
        kdtEntry3_realLineSeq_TextField.setName("kdtEntry3_realLineSeq_TextField");
        kdtEntry3_realLineSeq_TextField.setVisible(true);
        kdtEntry3_realLineSeq_TextField.setEditable(true);
        kdtEntry3_realLineSeq_TextField.setHorizontalAlignment(2);
        kdtEntry3_realLineSeq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntry3_realLineSeq_CellEditor = new KDTDefaultCellEditor(kdtEntry3_realLineSeq_TextField);
        this.kdtEntry3.getColumn("realLineSeq").setEditor(kdtEntry3_realLineSeq_CellEditor);
        KDFormattedTextField kdtEntry3_standardHour_TextField = new KDFormattedTextField();
        kdtEntry3_standardHour_TextField.setName("kdtEntry3_standardHour_TextField");
        kdtEntry3_standardHour_TextField.setVisible(true);
        kdtEntry3_standardHour_TextField.setEditable(true);
        kdtEntry3_standardHour_TextField.setHorizontalAlignment(2);
        kdtEntry3_standardHour_TextField.setDataType(1);
        	kdtEntry3_standardHour_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntry3_standardHour_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntry3_standardHour_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntry3_standardHour_CellEditor = new KDTDefaultCellEditor(kdtEntry3_standardHour_TextField);
        this.kdtEntry3.getColumn("standardHour").setEditor(kdtEntry3_standardHour_CellEditor);
        KDDatePicker kdtEntry3_lastEditTime_DatePicker = new KDDatePicker();
        kdtEntry3_lastEditTime_DatePicker.setName("kdtEntry3_lastEditTime_DatePicker");
        kdtEntry3_lastEditTime_DatePicker.setVisible(true);
        kdtEntry3_lastEditTime_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtEntry3_lastEditTime_CellEditor = new KDTDefaultCellEditor(kdtEntry3_lastEditTime_DatePicker);
        this.kdtEntry3.getColumn("lastEditTime").setEditor(kdtEntry3_lastEditTime_CellEditor);
        KDTextField kdtEntry3_remark_TextField = new KDTextField();
        kdtEntry3_remark_TextField.setName("kdtEntry3_remark_TextField");
        kdtEntry3_remark_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry3_remark_CellEditor = new KDTDefaultCellEditor(kdtEntry3_remark_TextField);
        this.kdtEntry3.getColumn("remark").setEditor(kdtEntry3_remark_CellEditor);
        KDFormattedTextField kdtEntry3_discountRate_TextField = new KDFormattedTextField();
        kdtEntry3_discountRate_TextField.setName("kdtEntry3_discountRate_TextField");
        kdtEntry3_discountRate_TextField.setVisible(true);
        kdtEntry3_discountRate_TextField.setEditable(true);
        kdtEntry3_discountRate_TextField.setHorizontalAlignment(2);
        kdtEntry3_discountRate_TextField.setDataType(1);
        	kdtEntry3_discountRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntry3_discountRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntry3_discountRate_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntry3_discountRate_CellEditor = new KDTDefaultCellEditor(kdtEntry3_discountRate_TextField);
        this.kdtEntry3.getColumn("discountRate").setEditor(kdtEntry3_discountRate_CellEditor);
        KDFormattedTextField kdtEntry3_lineSeq_TextField = new KDFormattedTextField();
        kdtEntry3_lineSeq_TextField.setName("kdtEntry3_lineSeq_TextField");
        kdtEntry3_lineSeq_TextField.setVisible(true);
        kdtEntry3_lineSeq_TextField.setEditable(true);
        kdtEntry3_lineSeq_TextField.setHorizontalAlignment(2);
        kdtEntry3_lineSeq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntry3_lineSeq_CellEditor = new KDTDefaultCellEditor(kdtEntry3_lineSeq_TextField);
        this.kdtEntry3.getColumn("lineSeq").setEditor(kdtEntry3_lineSeq_CellEditor);
        KDTextField kdtEntry3_dispatchStatus_TextField = new KDTextField();
        kdtEntry3_dispatchStatus_TextField.setName("kdtEntry3_dispatchStatus_TextField");
        kdtEntry3_dispatchStatus_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry3_dispatchStatus_CellEditor = new KDTDefaultCellEditor(kdtEntry3_dispatchStatus_TextField);
        this.kdtEntry3.getColumn("dispatchStatus").setEditor(kdtEntry3_dispatchStatus_CellEditor);
        KDFormattedTextField kdtEntry3_unitMI_TextField = new KDFormattedTextField();
        kdtEntry3_unitMI_TextField.setName("kdtEntry3_unitMI_TextField");
        kdtEntry3_unitMI_TextField.setVisible(true);
        kdtEntry3_unitMI_TextField.setEditable(true);
        kdtEntry3_unitMI_TextField.setHorizontalAlignment(2);
        kdtEntry3_unitMI_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntry3_unitMI_CellEditor = new KDTDefaultCellEditor(kdtEntry3_unitMI_TextField);
        this.kdtEntry3.getColumn("unitMI").setEditor(kdtEntry3_unitMI_CellEditor);
        KDFormattedTextField kdtEntry3_hourRate_TextField = new KDFormattedTextField();
        kdtEntry3_hourRate_TextField.setName("kdtEntry3_hourRate_TextField");
        kdtEntry3_hourRate_TextField.setVisible(true);
        kdtEntry3_hourRate_TextField.setEditable(true);
        kdtEntry3_hourRate_TextField.setHorizontalAlignment(2);
        kdtEntry3_hourRate_TextField.setDataType(1);
        	kdtEntry3_hourRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntry3_hourRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntry3_hourRate_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntry3_hourRate_CellEditor = new KDTDefaultCellEditor(kdtEntry3_hourRate_TextField);
        this.kdtEntry3.getColumn("hourRate").setEditor(kdtEntry3_hourRate_CellEditor);
        KDTextField kdtEntry3_taxBillCode_TextField = new KDTextField();
        kdtEntry3_taxBillCode_TextField.setName("kdtEntry3_taxBillCode_TextField");
        kdtEntry3_taxBillCode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry3_taxBillCode_CellEditor = new KDTDefaultCellEditor(kdtEntry3_taxBillCode_TextField);
        this.kdtEntry3.getColumn("taxBillCode").setEditor(kdtEntry3_taxBillCode_CellEditor);
        KDTextField kdtEntry3_wip_TextField = new KDTextField();
        kdtEntry3_wip_TextField.setName("kdtEntry3_wip_TextField");
        kdtEntry3_wip_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry3_wip_CellEditor = new KDTDefaultCellEditor(kdtEntry3_wip_TextField);
        this.kdtEntry3.getColumn("wip").setEditor(kdtEntry3_wip_CellEditor);
        KDTextField kdtEntry3_payCode_TextField = new KDTextField();
        kdtEntry3_payCode_TextField.setName("kdtEntry3_payCode_TextField");
        kdtEntry3_payCode_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry3_payCode_CellEditor = new KDTDefaultCellEditor(kdtEntry3_payCode_TextField);
        this.kdtEntry3.getColumn("payCode").setEditor(kdtEntry3_payCode_CellEditor);
        KDTextField kdtEntry3_billNum_TextField = new KDTextField();
        kdtEntry3_billNum_TextField.setName("kdtEntry3_billNum_TextField");
        kdtEntry3_billNum_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry3_billNum_CellEditor = new KDTDefaultCellEditor(kdtEntry3_billNum_TextField);
        this.kdtEntry3.getColumn("billNum").setEditor(kdtEntry3_billNum_CellEditor);
        KDTextField kdtEntry3_billStatus_TextField = new KDTextField();
        kdtEntry3_billStatus_TextField.setName("kdtEntry3_billStatus_TextField");
        kdtEntry3_billStatus_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry3_billStatus_CellEditor = new KDTDefaultCellEditor(kdtEntry3_billStatus_TextField);
        this.kdtEntry3.getColumn("billStatus").setEditor(kdtEntry3_billStatus_CellEditor);
        KDTextField kdtEntry3_saleType_TextField = new KDTextField();
        kdtEntry3_saleType_TextField.setName("kdtEntry3_saleType_TextField");
        kdtEntry3_saleType_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry3_saleType_CellEditor = new KDTDefaultCellEditor(kdtEntry3_saleType_TextField);
        this.kdtEntry3.getColumn("saleType").setEditor(kdtEntry3_saleType_CellEditor);
        KDDatePicker kdtEntry3_postingDate_DatePicker = new KDDatePicker();
        kdtEntry3_postingDate_DatePicker.setName("kdtEntry3_postingDate_DatePicker");
        kdtEntry3_postingDate_DatePicker.setVisible(true);
        kdtEntry3_postingDate_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtEntry3_postingDate_CellEditor = new KDTDefaultCellEditor(kdtEntry3_postingDate_DatePicker);
        this.kdtEntry3.getColumn("postingDate").setEditor(kdtEntry3_postingDate_CellEditor);
        KDTextField kdtEntry3_rts_TextField = new KDTextField();
        kdtEntry3_rts_TextField.setName("kdtEntry3_rts_TextField");
        kdtEntry3_rts_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntry3_rts_CellEditor = new KDTDefaultCellEditor(kdtEntry3_rts_TextField);
        this.kdtEntry3.getColumn("rts").setEditor(kdtEntry3_rts_CellEditor);
        // prmtserviceOrgUnit		
        this.prmtserviceOrgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");		
        this.prmtserviceOrgUnit.setEditable(true);		
        this.prmtserviceOrgUnit.setDisplayFormat("$name$");		
        this.prmtserviceOrgUnit.setEditFormat("$number$");		
        this.prmtserviceOrgUnit.setCommitFormat("$number$");		
        this.prmtserviceOrgUnit.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {kDDateLastUpdateTime,prmtLastUpdateUser,kDDateCreateTime,prmtCreator,prmtAuditor,txtDescription,pkBizDate,txtNumber,kDDateAuditTime,boxBaseStatus,prmtserviceOrgUnit,kdtEntrys,kdtEntry2,kdtEntry3}));
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
        this.setBounds(new Rectangle(0, 0, 1016, 627));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1016, 627));
        contCreator.setBounds(new Rectangle(15, 571, 270, 19));
        this.add(contCreator, new KDLayout.Constraints(15, 571, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCreateTime.setBounds(new Rectangle(15, 597, 270, 19));
        this.add(contCreateTime, new KDLayout.Constraints(15, 597, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateUser.setBounds(new Rectangle(370, 571, 270, 19));
        this.add(contLastUpdateUser, new KDLayout.Constraints(370, 571, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateTime.setBounds(new Rectangle(370, 597, 270, 19));
        this.add(contLastUpdateTime, new KDLayout.Constraints(370, 597, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNumber.setBounds(new Rectangle(15, 14, 270, 19));
        this.add(contNumber, new KDLayout.Constraints(15, 14, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBizDate.setBounds(new Rectangle(366, 14, 270, 19));
        this.add(contBizDate, new KDLayout.Constraints(366, 14, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contDescription.setBounds(new Rectangle(15, 44, 620, 19));
        this.add(contDescription, new KDLayout.Constraints(15, 44, 620, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAuditor.setBounds(new Rectangle(726, 571, 270, 19));
        this.add(contAuditor, new KDLayout.Constraints(726, 571, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contBaseStatus.setBounds(new Rectangle(719, 44, 270, 19));
        this.add(contBaseStatus, new KDLayout.Constraints(719, 44, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditTime.setBounds(new Rectangle(726, 597, 270, 19));
        this.add(contAuditTime, new KDLayout.Constraints(726, 597, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        kDTabbedPane1.setBounds(new Rectangle(14, 98, 988, 446));
        this.add(kDTabbedPane1, new KDLayout.Constraints(14, 98, 988, 446, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contserviceOrgUnit.setBounds(new Rectangle(719, 13, 270, 19));
        this.add(contserviceOrgUnit, new KDLayout.Constraints(719, 13, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        //contCreator
        contCreator.setBoundEditor(prmtCreator);
        //contCreateTime
        contCreateTime.setBoundEditor(kDDateCreateTime);
        //contLastUpdateUser
        contLastUpdateUser.setBoundEditor(prmtLastUpdateUser);
        //contLastUpdateTime
        contLastUpdateTime.setBoundEditor(kDDateLastUpdateTime);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contBizDate
        contBizDate.setBoundEditor(pkBizDate);
        //contDescription
        contDescription.setBoundEditor(txtDescription);
        //contAuditor
        contAuditor.setBoundEditor(prmtAuditor);
        //contBaseStatus
        contBaseStatus.setBoundEditor(boxBaseStatus);
        //contAuditTime
        contAuditTime.setBoundEditor(kDDateAuditTime);
        //kDTabbedPane1
        kDTabbedPane1.add(kDPanel2, resHelper.getString("kDPanel2.constraints"));
        kDTabbedPane1.add(kDPanel3, resHelper.getString("kDPanel3.constraints"));
        kDTabbedPane1.add(kDPanel4, resHelper.getString("kDPanel4.constraints"));
        //kDPanel2
kDPanel2.setLayout(new BorderLayout(0, 0));        kdtEntrys_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtEntrys,new com.kingdee.eas.ga.syncdata.DMSWipBillEntryInfo(),null,false);
        kDPanel2.add(kdtEntrys_detailPanel, BorderLayout.CENTER);
        //kDPanel3
kDPanel3.setLayout(new BorderLayout(0, 0));        kdtEntry2_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtEntry2,new com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Info(),null,false);
        kDPanel3.add(kdtEntry2_detailPanel, BorderLayout.CENTER);
        //kDPanel4
kDPanel4.setLayout(new BorderLayout(0, 0));        kdtEntry3_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtEntry3,new com.kingdee.eas.ga.syncdata.DMSWipBillEntry3Info(),null,false);
        kDPanel4.add(kdtEntry3_detailPanel, BorderLayout.CENTER);
        //contserviceOrgUnit
        contserviceOrgUnit.setBoundEditor(prmtserviceOrgUnit);

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
        menuBiz.add(menuItemUnAudit);
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
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnEdit);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
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
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(btnAudit);
        this.toolBar.add(btnUnAudit);
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
        this.toolBar.add(btnMultiColumnSort);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("entrys.lineStatus", com.kingdee.util.enums.Enum.class, this.kdtEntrys, "lineStatus.text");
		dataBinder.registerBinding("entrys.lineDesc", String.class, this.kdtEntrys, "lineDesc.text");
		dataBinder.registerBinding("entrys", com.kingdee.eas.ga.syncdata.DMSWipBillEntryInfo.class, this.kdtEntrys, "userObject");
		dataBinder.registerBinding("entrys.vin", String.class, this.kdtEntrys, "vin.text");
		dataBinder.registerBinding("entrys.createTime", java.util.Date.class, this.kdtEntrys, "createTime.text");
		dataBinder.registerBinding("entrys.mileage", java.math.BigDecimal.class, this.kdtEntrys, "mileage.text");
		dataBinder.registerBinding("entrys.plateNum", String.class, this.kdtEntrys, "plateNum.text");
		dataBinder.registerBinding("entrys.wip", String.class, this.kdtEntrys, "wip.text");
		dataBinder.registerBinding("entrys.accountCode", String.class, this.kdtEntrys, "accountCode.text");
		dataBinder.registerBinding("entrys.deptNum", String.class, this.kdtEntrys, "deptNum.text");
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtCreator, "data");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.kDDateCreateTime, "value");
		dataBinder.registerBinding("lastUpdateUser", com.kingdee.eas.base.permission.UserInfo.class, this.prmtLastUpdateUser, "data");
		dataBinder.registerBinding("lastUpdateTime", java.sql.Timestamp.class, this.kDDateLastUpdateTime, "value");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("bizDate", java.util.Date.class, this.pkBizDate, "value");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "text");
		dataBinder.registerBinding("auditor", com.kingdee.eas.base.permission.UserInfo.class, this.prmtAuditor, "data");
		dataBinder.registerBinding("baseStatus", com.kingdee.eas.scm.common.BillBaseStatusEnum.class, this.boxBaseStatus, "selectedItem");
		dataBinder.registerBinding("auditTime", java.sql.Timestamp.class, this.kDDateAuditTime, "value");
		dataBinder.registerBinding("entry2.seq", int.class, this.kdtEntry2, "seq.text");
		dataBinder.registerBinding("Entry2", com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Info.class, this.kdtEntry2, "userObject");
		dataBinder.registerBinding("entry2.billNum", String.class, this.kdtEntry2, "billNum.text");
		dataBinder.registerBinding("entry2.billStatus", String.class, this.kdtEntry2, "billStatus.text");
		dataBinder.registerBinding("entry2.orderStatus", String.class, this.kdtEntry2, "orderStatus.text");
		dataBinder.registerBinding("entry2.materialNum", String.class, this.kdtEntry2, "materialNum.text");
		dataBinder.registerBinding("entry2.wip", String.class, this.kdtEntry2, "wip.text");
		dataBinder.registerBinding("entry2.chaimCode", String.class, this.kdtEntry2, "chaimCode.text");
		dataBinder.registerBinding("entry2.realLineSeq", int.class, this.kdtEntry2, "realLineSeq.text");
		dataBinder.registerBinding("entry2.lastEditTime", java.util.Date.class, this.kdtEntry2, "lastEditTime.text");
		dataBinder.registerBinding("entry2.remark", String.class, this.kdtEntry2, "remark.text");
		dataBinder.registerBinding("entry2.discountRate", java.math.BigDecimal.class, this.kdtEntry2, "discountRate.text");
		dataBinder.registerBinding("entry2.lineSeq", int.class, this.kdtEntry2, "lineSeq.text");
		dataBinder.registerBinding("entry2.orderQty", java.math.BigDecimal.class, this.kdtEntry2, "orderQty.text");
		dataBinder.registerBinding("entry2.salePrice", java.math.BigDecimal.class, this.kdtEntry2, "salePrice.text");
		dataBinder.registerBinding("entry2.taxBillCode", String.class, this.kdtEntry2, "taxBillCode.text");
		dataBinder.registerBinding("entry2.accountCode", String.class, this.kdtEntry2, "accountCode.text");
		dataBinder.registerBinding("entry2.saleType", String.class, this.kdtEntry2, "saleType.text");
		dataBinder.registerBinding("entry2.costPrice", java.math.BigDecimal.class, this.kdtEntry2, "costPrice.text");
		dataBinder.registerBinding("entry3.seq", int.class, this.kdtEntry3, "seq.text");
		dataBinder.registerBinding("Entry3", com.kingdee.eas.ga.syncdata.DMSWipBillEntry3Info.class, this.kdtEntry3, "userObject");
		dataBinder.registerBinding("entry3.accountCode", String.class, this.kdtEntry3, "accountCode.text");
		dataBinder.registerBinding("entry3.remark", String.class, this.kdtEntry3, "remark.text");
		dataBinder.registerBinding("entry3.dispatchStatus", String.class, this.kdtEntry3, "dispatchStatus.text");
		dataBinder.registerBinding("entry3.taxBillCode", String.class, this.kdtEntry3, "taxBillCode.text");
		dataBinder.registerBinding("entry3.wip", String.class, this.kdtEntry3, "wip.text");
		dataBinder.registerBinding("entry3.payCode", String.class, this.kdtEntry3, "payCode.text");
		dataBinder.registerBinding("entry3.billNum", String.class, this.kdtEntry3, "billNum.text");
		dataBinder.registerBinding("entry3.billStatus", String.class, this.kdtEntry3, "billStatus.text");
		dataBinder.registerBinding("entry3.realLineSeq", int.class, this.kdtEntry3, "realLineSeq.text");
		dataBinder.registerBinding("entry3.standardHour", java.math.BigDecimal.class, this.kdtEntry3, "standardHour.text");
		dataBinder.registerBinding("entry3.lastEditTime", java.util.Date.class, this.kdtEntry3, "lastEditTime.text");
		dataBinder.registerBinding("entry3.discountRate", java.math.BigDecimal.class, this.kdtEntry3, "discountRate.text");
		dataBinder.registerBinding("entry3.lineSeq", int.class, this.kdtEntry3, "lineSeq.text");
		dataBinder.registerBinding("entry3.unitMI", int.class, this.kdtEntry3, "unitMI.text");
		dataBinder.registerBinding("entry3.hourRate", java.math.BigDecimal.class, this.kdtEntry3, "hourRate.text");
		dataBinder.registerBinding("entry3.saleType", String.class, this.kdtEntry3, "saleType.text");
		dataBinder.registerBinding("entry3.postingDate", java.util.Date.class, this.kdtEntry3, "postingDate.text");
		dataBinder.registerBinding("entry3.rts", String.class, this.kdtEntry3, "rts.text");
		dataBinder.registerBinding("serviceOrgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtserviceOrgUnit, "data");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.ga.syncdata.app.DMSWipBillEditUIHandler";
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
        this.kDDateLastUpdateTime.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.ga.syncdata.DMSWipBillInfo)ov;
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
	 * ????????��??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );
		getValidateHelper().registerBindProperty("entrys.lineStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.lineDesc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.vin", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.mileage", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.plateNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.wip", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.accountCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.deptNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateUser", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditor", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("baseStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("auditTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entry2", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.billNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.billStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.orderStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.materialNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.wip", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.chaimCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.realLineSeq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.lastEditTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.discountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.lineSeq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.orderQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.salePrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.taxBillCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.accountCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.saleType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry2.costPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Entry3", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.accountCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.remark", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.dispatchStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.taxBillCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.wip", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.payCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.billNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.billStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.realLineSeq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.standardHour", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.lastEditTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.discountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.lineSeq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.unitMI", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.hourRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.saleType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.postingDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entry3.rts", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("serviceOrgUnit", ValidateHelper.ON_SAVE);    		
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
    	sic.add(new SelectorItemInfo("entrys.lineStatus"));
    	sic.add(new SelectorItemInfo("entrys.lineDesc"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("entrys.vin"));
    	sic.add(new SelectorItemInfo("entrys.createTime"));
    	sic.add(new SelectorItemInfo("entrys.mileage"));
    	sic.add(new SelectorItemInfo("entrys.plateNum"));
    	sic.add(new SelectorItemInfo("entrys.wip"));
    	sic.add(new SelectorItemInfo("entrys.accountCode"));
    	sic.add(new SelectorItemInfo("entrys.deptNum"));
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
			sic.add(new SelectorItemInfo("lastUpdateUser.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("lastUpdateUser.id"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.number"));
        	sic.add(new SelectorItemInfo("lastUpdateUser.name"));
		}
        sic.add(new SelectorItemInfo("lastUpdateTime"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("bizDate"));
        sic.add(new SelectorItemInfo("description"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("auditor.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("auditor.id"));
        	sic.add(new SelectorItemInfo("auditor.number"));
        	sic.add(new SelectorItemInfo("auditor.name"));
		}
        sic.add(new SelectorItemInfo("baseStatus"));
        sic.add(new SelectorItemInfo("auditTime"));
    	sic.add(new SelectorItemInfo("entry2.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Entry2.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("entry2.billNum"));
    	sic.add(new SelectorItemInfo("entry2.billStatus"));
    	sic.add(new SelectorItemInfo("entry2.orderStatus"));
    	sic.add(new SelectorItemInfo("entry2.materialNum"));
    	sic.add(new SelectorItemInfo("entry2.wip"));
    	sic.add(new SelectorItemInfo("entry2.chaimCode"));
    	sic.add(new SelectorItemInfo("entry2.realLineSeq"));
    	sic.add(new SelectorItemInfo("entry2.lastEditTime"));
    	sic.add(new SelectorItemInfo("entry2.remark"));
    	sic.add(new SelectorItemInfo("entry2.discountRate"));
    	sic.add(new SelectorItemInfo("entry2.lineSeq"));
    	sic.add(new SelectorItemInfo("entry2.orderQty"));
    	sic.add(new SelectorItemInfo("entry2.salePrice"));
    	sic.add(new SelectorItemInfo("entry2.taxBillCode"));
    	sic.add(new SelectorItemInfo("entry2.accountCode"));
    	sic.add(new SelectorItemInfo("entry2.saleType"));
    	sic.add(new SelectorItemInfo("entry2.costPrice"));
    	sic.add(new SelectorItemInfo("entry3.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("Entry3.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("entry3.accountCode"));
    	sic.add(new SelectorItemInfo("entry3.remark"));
    	sic.add(new SelectorItemInfo("entry3.dispatchStatus"));
    	sic.add(new SelectorItemInfo("entry3.taxBillCode"));
    	sic.add(new SelectorItemInfo("entry3.wip"));
    	sic.add(new SelectorItemInfo("entry3.payCode"));
    	sic.add(new SelectorItemInfo("entry3.billNum"));
    	sic.add(new SelectorItemInfo("entry3.billStatus"));
    	sic.add(new SelectorItemInfo("entry3.realLineSeq"));
    	sic.add(new SelectorItemInfo("entry3.standardHour"));
    	sic.add(new SelectorItemInfo("entry3.lastEditTime"));
    	sic.add(new SelectorItemInfo("entry3.discountRate"));
    	sic.add(new SelectorItemInfo("entry3.lineSeq"));
    	sic.add(new SelectorItemInfo("entry3.unitMI"));
    	sic.add(new SelectorItemInfo("entry3.hourRate"));
    	sic.add(new SelectorItemInfo("entry3.saleType"));
    	sic.add(new SelectorItemInfo("entry3.postingDate"));
    	sic.add(new SelectorItemInfo("entry3.rts"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("serviceOrgUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("serviceOrgUnit.id"));
        	sic.add(new SelectorItemInfo("serviceOrgUnit.number"));
        	sic.add(new SelectorItemInfo("serviceOrgUnit.name"));
		}
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
     * output actionAddNew_actionPerformed method
     */
    public void actionAddNew_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionAddNew_actionPerformed(e);
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

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.ga.syncdata.client", "DMSWipBillEditUI");
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
        return com.kingdee.eas.ga.syncdata.client.DMSWipBillEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.ga.syncdata.DMSWipBillFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.ga.syncdata.DMSWipBillInfo objectValue = new com.kingdee.eas.ga.syncdata.DMSWipBillInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/ga/syncdata/DMSWipBill";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.ga.syncdata.app.DMSWipBillQuery");
	}
    

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kdtEntrys;
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