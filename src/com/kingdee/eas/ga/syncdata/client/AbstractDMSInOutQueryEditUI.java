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
public abstract class AbstractDMSInOutQueryEditUI extends com.kingdee.eas.myframework.template.base.client.SimpleBizBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractDMSInOutQueryEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contserviceOrgUnit;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtserviceOrgUnit;
    protected com.kingdee.eas.ga.syncdata.DMSInOutQueryInfo editData = null;
    /**
     * output class constructor
     */
    public AbstractDMSInOutQueryEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractDMSInOutQueryEditUI.class.getName());
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
        this.contserviceOrgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.prmtserviceOrgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.contserviceOrgUnit.setName("contserviceOrgUnit");
        this.prmtserviceOrgUnit.setName("prmtserviceOrgUnit");
        // CoreUI		
        this.contBizDate.setBoundLabelText(resHelper.getString("contBizDate.boundLabelText"));		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));
		String kdtEntrysStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol1\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol8\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol10\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol11\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol13\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol15\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol17\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol18\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style><c:Style id=\"sCol23\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol27\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"bizDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" t:styleID=\"sCol1\" /><t:Column t:key=\"customer\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /><t:Column t:key=\"supplier\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"option\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"rqn\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"billNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"wip\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"lineSeq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol8\" /><t:Column t:key=\"materialNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"qty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol10\" /><t:Column t:key=\"supplyQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol11\" /><t:Column t:key=\"L\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"remainQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol13\" /><t:Column t:key=\"audit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"cost\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol15\" /><t:Column t:key=\"T\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"lineDesc\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol17\" /><t:Column t:key=\"lineStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol18\" /><t:Column t:key=\"easSupplier\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"easCustomer\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"easWarehouse\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"easRepairWO\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"easTaxPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol23\" /><t:Column t:key=\"easMaterial\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"easBaseUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"easVehicle\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"easRepairWOEntrySeq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol27\" /><t:Column t:key=\"easRepairWONumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"isTransferred\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{bizDate}</t:Cell><t:Cell>$Resource{customer}</t:Cell><t:Cell>$Resource{supplier}</t:Cell><t:Cell>$Resource{option}</t:Cell><t:Cell>$Resource{rqn}</t:Cell><t:Cell>$Resource{billNum}</t:Cell><t:Cell>$Resource{wip}</t:Cell><t:Cell>$Resource{lineSeq}</t:Cell><t:Cell>$Resource{materialNum}</t:Cell><t:Cell>$Resource{qty}</t:Cell><t:Cell>$Resource{supplyQty}</t:Cell><t:Cell>$Resource{L}</t:Cell><t:Cell>$Resource{remainQty}</t:Cell><t:Cell>$Resource{audit}</t:Cell><t:Cell>$Resource{cost}</t:Cell><t:Cell>$Resource{T}</t:Cell><t:Cell>$Resource{lineDesc}</t:Cell><t:Cell>$Resource{lineStatus}</t:Cell><t:Cell>$Resource{easSupplier}</t:Cell><t:Cell>$Resource{easCustomer}</t:Cell><t:Cell>$Resource{easWarehouse}</t:Cell><t:Cell>$Resource{easRepairWO}</t:Cell><t:Cell>$Resource{easTaxPrice}</t:Cell><t:Cell>$Resource{easMaterial}</t:Cell><t:Cell>$Resource{easBaseUnit}</t:Cell><t:Cell>$Resource{easVehicle}</t:Cell><t:Cell>$Resource{easRepairWOEntrySeq}</t:Cell><t:Cell>$Resource{easRepairWONumber}</t:Cell><t:Cell>$Resource{isTransferred}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtEntrys.setFormatXml(resHelper.translateString("kdtEntrys",kdtEntrysStrXML));

                this.kdtEntrys.putBindContents("editData",new String[] {"","bizDate","customer","supplier","option","rqn","billNum","wip","lineSeq","materialNum","qty","supplyQty","L","remainQty","audit","cost","T","lineDesc","lineStatus","easSupplier","easCustomer","easWarehouse","easRepairWO","easTaxPrice","easMaterial","easBaseUnit","easVehicle","easRepairWOEntrySeq","easRepairWONumber","isTransferred"});


        this.kdtEntrys.checkParsed();
        KDDatePicker kdtEntrys_bizDate_DatePicker = new KDDatePicker();
        kdtEntrys_bizDate_DatePicker.setName("kdtEntrys_bizDate_DatePicker");
        kdtEntrys_bizDate_DatePicker.setVisible(true);
        kdtEntrys_bizDate_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtEntrys_bizDate_CellEditor = new KDTDefaultCellEditor(kdtEntrys_bizDate_DatePicker);
        this.kdtEntrys.getColumn("bizDate").setEditor(kdtEntrys_bizDate_CellEditor);
        KDTextField kdtEntrys_customer_TextField = new KDTextField();
        kdtEntrys_customer_TextField.setName("kdtEntrys_customer_TextField");
        kdtEntrys_customer_TextField.setMaxLength(80);
        KDTDefaultCellEditor kdtEntrys_customer_CellEditor = new KDTDefaultCellEditor(kdtEntrys_customer_TextField);
        this.kdtEntrys.getColumn("customer").setEditor(kdtEntrys_customer_CellEditor);
        KDTextField kdtEntrys_supplier_TextField = new KDTextField();
        kdtEntrys_supplier_TextField.setName("kdtEntrys_supplier_TextField");
        kdtEntrys_supplier_TextField.setMaxLength(80);
        KDTDefaultCellEditor kdtEntrys_supplier_CellEditor = new KDTDefaultCellEditor(kdtEntrys_supplier_TextField);
        this.kdtEntrys.getColumn("supplier").setEditor(kdtEntrys_supplier_CellEditor);
        KDTextField kdtEntrys_option_TextField = new KDTextField();
        kdtEntrys_option_TextField.setName("kdtEntrys_option_TextField");
        kdtEntrys_option_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_option_CellEditor = new KDTDefaultCellEditor(kdtEntrys_option_TextField);
        this.kdtEntrys.getColumn("option").setEditor(kdtEntrys_option_CellEditor);
        KDTextField kdtEntrys_rqn_TextField = new KDTextField();
        kdtEntrys_rqn_TextField.setName("kdtEntrys_rqn_TextField");
        kdtEntrys_rqn_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_rqn_CellEditor = new KDTDefaultCellEditor(kdtEntrys_rqn_TextField);
        this.kdtEntrys.getColumn("rqn").setEditor(kdtEntrys_rqn_CellEditor);
        KDTextField kdtEntrys_billNum_TextField = new KDTextField();
        kdtEntrys_billNum_TextField.setName("kdtEntrys_billNum_TextField");
        kdtEntrys_billNum_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_billNum_CellEditor = new KDTDefaultCellEditor(kdtEntrys_billNum_TextField);
        this.kdtEntrys.getColumn("billNum").setEditor(kdtEntrys_billNum_CellEditor);
        KDTextField kdtEntrys_wip_TextField = new KDTextField();
        kdtEntrys_wip_TextField.setName("kdtEntrys_wip_TextField");
        kdtEntrys_wip_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_wip_CellEditor = new KDTDefaultCellEditor(kdtEntrys_wip_TextField);
        this.kdtEntrys.getColumn("wip").setEditor(kdtEntrys_wip_CellEditor);
        KDFormattedTextField kdtEntrys_lineSeq_TextField = new KDFormattedTextField();
        kdtEntrys_lineSeq_TextField.setName("kdtEntrys_lineSeq_TextField");
        kdtEntrys_lineSeq_TextField.setVisible(true);
        kdtEntrys_lineSeq_TextField.setEditable(true);
        kdtEntrys_lineSeq_TextField.setHorizontalAlignment(2);
        kdtEntrys_lineSeq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntrys_lineSeq_CellEditor = new KDTDefaultCellEditor(kdtEntrys_lineSeq_TextField);
        this.kdtEntrys.getColumn("lineSeq").setEditor(kdtEntrys_lineSeq_CellEditor);
        KDTextField kdtEntrys_materialNum_TextField = new KDTextField();
        kdtEntrys_materialNum_TextField.setName("kdtEntrys_materialNum_TextField");
        kdtEntrys_materialNum_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_materialNum_CellEditor = new KDTDefaultCellEditor(kdtEntrys_materialNum_TextField);
        this.kdtEntrys.getColumn("materialNum").setEditor(kdtEntrys_materialNum_CellEditor);
        KDFormattedTextField kdtEntrys_qty_TextField = new KDFormattedTextField();
        kdtEntrys_qty_TextField.setName("kdtEntrys_qty_TextField");
        kdtEntrys_qty_TextField.setVisible(true);
        kdtEntrys_qty_TextField.setEditable(true);
        kdtEntrys_qty_TextField.setHorizontalAlignment(2);
        kdtEntrys_qty_TextField.setDataType(1);
        	kdtEntrys_qty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_qty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_qty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_qty_CellEditor = new KDTDefaultCellEditor(kdtEntrys_qty_TextField);
        this.kdtEntrys.getColumn("qty").setEditor(kdtEntrys_qty_CellEditor);
        KDFormattedTextField kdtEntrys_supplyQty_TextField = new KDFormattedTextField();
        kdtEntrys_supplyQty_TextField.setName("kdtEntrys_supplyQty_TextField");
        kdtEntrys_supplyQty_TextField.setVisible(true);
        kdtEntrys_supplyQty_TextField.setEditable(true);
        kdtEntrys_supplyQty_TextField.setHorizontalAlignment(2);
        kdtEntrys_supplyQty_TextField.setDataType(1);
        	kdtEntrys_supplyQty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_supplyQty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_supplyQty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_supplyQty_CellEditor = new KDTDefaultCellEditor(kdtEntrys_supplyQty_TextField);
        this.kdtEntrys.getColumn("supplyQty").setEditor(kdtEntrys_supplyQty_CellEditor);
        KDTextField kdtEntrys_L_TextField = new KDTextField();
        kdtEntrys_L_TextField.setName("kdtEntrys_L_TextField");
        kdtEntrys_L_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_L_CellEditor = new KDTDefaultCellEditor(kdtEntrys_L_TextField);
        this.kdtEntrys.getColumn("L").setEditor(kdtEntrys_L_CellEditor);
        KDFormattedTextField kdtEntrys_remainQty_TextField = new KDFormattedTextField();
        kdtEntrys_remainQty_TextField.setName("kdtEntrys_remainQty_TextField");
        kdtEntrys_remainQty_TextField.setVisible(true);
        kdtEntrys_remainQty_TextField.setEditable(true);
        kdtEntrys_remainQty_TextField.setHorizontalAlignment(2);
        kdtEntrys_remainQty_TextField.setDataType(1);
        	kdtEntrys_remainQty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_remainQty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_remainQty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_remainQty_CellEditor = new KDTDefaultCellEditor(kdtEntrys_remainQty_TextField);
        this.kdtEntrys.getColumn("remainQty").setEditor(kdtEntrys_remainQty_CellEditor);
        KDTextField kdtEntrys_audit_TextField = new KDTextField();
        kdtEntrys_audit_TextField.setName("kdtEntrys_audit_TextField");
        kdtEntrys_audit_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_audit_CellEditor = new KDTDefaultCellEditor(kdtEntrys_audit_TextField);
        this.kdtEntrys.getColumn("audit").setEditor(kdtEntrys_audit_CellEditor);
        KDFormattedTextField kdtEntrys_cost_TextField = new KDFormattedTextField();
        kdtEntrys_cost_TextField.setName("kdtEntrys_cost_TextField");
        kdtEntrys_cost_TextField.setVisible(true);
        kdtEntrys_cost_TextField.setEditable(true);
        kdtEntrys_cost_TextField.setHorizontalAlignment(2);
        kdtEntrys_cost_TextField.setDataType(1);
        	kdtEntrys_cost_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_cost_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_cost_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_cost_CellEditor = new KDTDefaultCellEditor(kdtEntrys_cost_TextField);
        this.kdtEntrys.getColumn("cost").setEditor(kdtEntrys_cost_CellEditor);
        KDTextField kdtEntrys_T_TextField = new KDTextField();
        kdtEntrys_T_TextField.setName("kdtEntrys_T_TextField");
        kdtEntrys_T_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_T_CellEditor = new KDTDefaultCellEditor(kdtEntrys_T_TextField);
        this.kdtEntrys.getColumn("T").setEditor(kdtEntrys_T_CellEditor);
        KDComboBox kdtEntrys_lineStatus_ComboBox = new KDComboBox();
        kdtEntrys_lineStatus_ComboBox.setName("kdtEntrys_lineStatus_ComboBox");
        kdtEntrys_lineStatus_ComboBox.setVisible(true);
        kdtEntrys_lineStatus_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.scm.common.EntryBaseStatusEnum").toArray());
        KDTDefaultCellEditor kdtEntrys_lineStatus_CellEditor = new KDTDefaultCellEditor(kdtEntrys_lineStatus_ComboBox);
        this.kdtEntrys.getColumn("lineStatus").setEditor(kdtEntrys_lineStatus_CellEditor);
        final KDBizPromptBox kdtEntrys_easSupplier_PromptBox = new KDBizPromptBox();
        kdtEntrys_easSupplier_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierDefaultQuery");
        kdtEntrys_easSupplier_PromptBox.setVisible(true);
        kdtEntrys_easSupplier_PromptBox.setEditable(true);
        kdtEntrys_easSupplier_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_easSupplier_PromptBox.setEditFormat("$number$");
        kdtEntrys_easSupplier_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_easSupplier_CellEditor = new KDTDefaultCellEditor(kdtEntrys_easSupplier_PromptBox);
        this.kdtEntrys.getColumn("easSupplier").setEditor(kdtEntrys_easSupplier_CellEditor);
        ObjectValueRender kdtEntrys_easSupplier_OVR = new ObjectValueRender();
        kdtEntrys_easSupplier_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("easSupplier").setRenderer(kdtEntrys_easSupplier_OVR);
        final KDBizPromptBox kdtEntrys_easCustomer_PromptBox = new KDBizPromptBox();
        kdtEntrys_easCustomer_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.CustomerInfoQuery");
        kdtEntrys_easCustomer_PromptBox.setVisible(true);
        kdtEntrys_easCustomer_PromptBox.setEditable(true);
        kdtEntrys_easCustomer_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_easCustomer_PromptBox.setEditFormat("$number$");
        kdtEntrys_easCustomer_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_easCustomer_CellEditor = new KDTDefaultCellEditor(kdtEntrys_easCustomer_PromptBox);
        this.kdtEntrys.getColumn("easCustomer").setEditor(kdtEntrys_easCustomer_CellEditor);
        ObjectValueRender kdtEntrys_easCustomer_OVR = new ObjectValueRender();
        kdtEntrys_easCustomer_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("easCustomer").setRenderer(kdtEntrys_easCustomer_OVR);
        final KDBizPromptBox kdtEntrys_easWarehouse_PromptBox = new KDBizPromptBox();
        kdtEntrys_easWarehouse_PromptBox.setQueryInfo("com.kingdee.eas.basedata.scm.im.inv.app.F7AllWarehouseQuery");
        kdtEntrys_easWarehouse_PromptBox.setVisible(true);
        kdtEntrys_easWarehouse_PromptBox.setEditable(true);
        kdtEntrys_easWarehouse_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_easWarehouse_PromptBox.setEditFormat("$number$");
        kdtEntrys_easWarehouse_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_easWarehouse_CellEditor = new KDTDefaultCellEditor(kdtEntrys_easWarehouse_PromptBox);
        this.kdtEntrys.getColumn("easWarehouse").setEditor(kdtEntrys_easWarehouse_CellEditor);
        ObjectValueRender kdtEntrys_easWarehouse_OVR = new ObjectValueRender();
        kdtEntrys_easWarehouse_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("easWarehouse").setRenderer(kdtEntrys_easWarehouse_OVR);
        final KDBizPromptBox kdtEntrys_easRepairWO_PromptBox = new KDBizPromptBox();
        kdtEntrys_easRepairWO_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.rsm.rs.app.RepairWOQuery");
        kdtEntrys_easRepairWO_PromptBox.setVisible(true);
        kdtEntrys_easRepairWO_PromptBox.setEditable(true);
        kdtEntrys_easRepairWO_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_easRepairWO_PromptBox.setEditFormat("$number$");
        kdtEntrys_easRepairWO_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_easRepairWO_CellEditor = new KDTDefaultCellEditor(kdtEntrys_easRepairWO_PromptBox);
        this.kdtEntrys.getColumn("easRepairWO").setEditor(kdtEntrys_easRepairWO_CellEditor);
        ObjectValueRender kdtEntrys_easRepairWO_OVR = new ObjectValueRender();
        kdtEntrys_easRepairWO_OVR.setFormat(new BizDataFormat("$number$"));
        this.kdtEntrys.getColumn("easRepairWO").setRenderer(kdtEntrys_easRepairWO_OVR);
        			EntityViewInfo evikdtEntrys_easRepairWO_PromptBox = new EntityViewInfo ();
		evikdtEntrys_easRepairWO_PromptBox.setFilter(com.kingdee.eas.framework.FrameWorkUtils.getF7FilterInfoByAuthorizedOrg(com.kingdee.eas.basedata.org.OrgType.getEnum("Admin"),"OrgUnit.id"));
		kdtEntrys_easRepairWO_PromptBox.setEntityViewInfo(evikdtEntrys_easRepairWO_PromptBox);
					
        KDFormattedTextField kdtEntrys_easTaxPrice_TextField = new KDFormattedTextField();
        kdtEntrys_easTaxPrice_TextField.setName("kdtEntrys_easTaxPrice_TextField");
        kdtEntrys_easTaxPrice_TextField.setVisible(true);
        kdtEntrys_easTaxPrice_TextField.setEditable(true);
        kdtEntrys_easTaxPrice_TextField.setHorizontalAlignment(2);
        kdtEntrys_easTaxPrice_TextField.setDataType(1);
        	kdtEntrys_easTaxPrice_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_easTaxPrice_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_easTaxPrice_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_easTaxPrice_CellEditor = new KDTDefaultCellEditor(kdtEntrys_easTaxPrice_TextField);
        this.kdtEntrys.getColumn("easTaxPrice").setEditor(kdtEntrys_easTaxPrice_CellEditor);
        final KDBizPromptBox kdtEntrys_easMaterial_PromptBox = new KDBizPromptBox();
        kdtEntrys_easMaterial_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7MaterialBaseInfoQuery");
        kdtEntrys_easMaterial_PromptBox.setVisible(true);
        kdtEntrys_easMaterial_PromptBox.setEditable(true);
        kdtEntrys_easMaterial_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_easMaterial_PromptBox.setEditFormat("$number$");
        kdtEntrys_easMaterial_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_easMaterial_CellEditor = new KDTDefaultCellEditor(kdtEntrys_easMaterial_PromptBox);
        this.kdtEntrys.getColumn("easMaterial").setEditor(kdtEntrys_easMaterial_CellEditor);
        ObjectValueRender kdtEntrys_easMaterial_OVR = new ObjectValueRender();
        kdtEntrys_easMaterial_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("easMaterial").setRenderer(kdtEntrys_easMaterial_OVR);
        final KDBizPromptBox kdtEntrys_easBaseUnit_PromptBox = new KDBizPromptBox();
        kdtEntrys_easBaseUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7MeasureUnitQuery");
        kdtEntrys_easBaseUnit_PromptBox.setVisible(true);
        kdtEntrys_easBaseUnit_PromptBox.setEditable(true);
        kdtEntrys_easBaseUnit_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_easBaseUnit_PromptBox.setEditFormat("$number$");
        kdtEntrys_easBaseUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_easBaseUnit_CellEditor = new KDTDefaultCellEditor(kdtEntrys_easBaseUnit_PromptBox);
        this.kdtEntrys.getColumn("easBaseUnit").setEditor(kdtEntrys_easBaseUnit_CellEditor);
        ObjectValueRender kdtEntrys_easBaseUnit_OVR = new ObjectValueRender();
        kdtEntrys_easBaseUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("easBaseUnit").setRenderer(kdtEntrys_easBaseUnit_OVR);
        final KDBizPromptBox kdtEntrys_easVehicle_PromptBox = new KDBizPromptBox();
        kdtEntrys_easVehicle_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.VehicleF7Query");
        kdtEntrys_easVehicle_PromptBox.setVisible(true);
        kdtEntrys_easVehicle_PromptBox.setEditable(true);
        kdtEntrys_easVehicle_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_easVehicle_PromptBox.setEditFormat("$number$");
        kdtEntrys_easVehicle_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_easVehicle_CellEditor = new KDTDefaultCellEditor(kdtEntrys_easVehicle_PromptBox);
        this.kdtEntrys.getColumn("easVehicle").setEditor(kdtEntrys_easVehicle_CellEditor);
        ObjectValueRender kdtEntrys_easVehicle_OVR = new ObjectValueRender();
        kdtEntrys_easVehicle_OVR.setFormat(new BizDataFormat("$plateNum$"));
        this.kdtEntrys.getColumn("easVehicle").setRenderer(kdtEntrys_easVehicle_OVR);
        KDFormattedTextField kdtEntrys_easRepairWOEntrySeq_TextField = new KDFormattedTextField();
        kdtEntrys_easRepairWOEntrySeq_TextField.setName("kdtEntrys_easRepairWOEntrySeq_TextField");
        kdtEntrys_easRepairWOEntrySeq_TextField.setVisible(true);
        kdtEntrys_easRepairWOEntrySeq_TextField.setEditable(true);
        kdtEntrys_easRepairWOEntrySeq_TextField.setHorizontalAlignment(2);
        kdtEntrys_easRepairWOEntrySeq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtEntrys_easRepairWOEntrySeq_CellEditor = new KDTDefaultCellEditor(kdtEntrys_easRepairWOEntrySeq_TextField);
        this.kdtEntrys.getColumn("easRepairWOEntrySeq").setEditor(kdtEntrys_easRepairWOEntrySeq_CellEditor);
        KDTextField kdtEntrys_easRepairWONumber_TextField = new KDTextField();
        kdtEntrys_easRepairWONumber_TextField.setName("kdtEntrys_easRepairWONumber_TextField");
        kdtEntrys_easRepairWONumber_TextField.setMaxLength(100);
        KDTDefaultCellEditor kdtEntrys_easRepairWONumber_CellEditor = new KDTDefaultCellEditor(kdtEntrys_easRepairWONumber_TextField);
        this.kdtEntrys.getColumn("easRepairWONumber").setEditor(kdtEntrys_easRepairWONumber_CellEditor);
        KDCheckBox kdtEntrys_isTransferred_CheckBox = new KDCheckBox();
        kdtEntrys_isTransferred_CheckBox.setName("kdtEntrys_isTransferred_CheckBox");
        KDTDefaultCellEditor kdtEntrys_isTransferred_CellEditor = new KDTDefaultCellEditor(kdtEntrys_isTransferred_CheckBox);
        this.kdtEntrys.getColumn("isTransferred").setEditor(kdtEntrys_isTransferred_CellEditor);		
        this.pkBizDate.setTimeEnabled(true);
        // contserviceOrgUnit		
        this.contserviceOrgUnit.setBoundLabelText(resHelper.getString("contserviceOrgUnit.boundLabelText"));		
        this.contserviceOrgUnit.setBoundLabelLength(100);		
        this.contserviceOrgUnit.setBoundLabelUnderline(true);		
        this.contserviceOrgUnit.setVisible(true);
        // prmtserviceOrgUnit		
        this.prmtserviceOrgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");		
        this.prmtserviceOrgUnit.setVisible(true);		
        this.prmtserviceOrgUnit.setEditable(true);		
        this.prmtserviceOrgUnit.setDisplayFormat("$isEntity$");		
        this.prmtserviceOrgUnit.setEditFormat("$number$");		
        this.prmtserviceOrgUnit.setCommitFormat("$number$");		
        this.prmtserviceOrgUnit.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {kDDateLastUpdateTime,prmtLastUpdateUser,kDDateCreateTime,prmtCreator,prmtAuditor,txtDescription,pkBizDate,txtNumber,kDDateAuditTime,boxBaseStatus,kdtEntrys,prmtserviceOrgUnit}));
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
        contDescription.setBounds(new Rectangle(16, 41, 620, 19));
        this.add(contDescription, new KDLayout.Constraints(16, 41, 620, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAuditor.setBounds(new Rectangle(726, 571, 270, 19));
        this.add(contAuditor, new KDLayout.Constraints(726, 571, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        kdtEntrys.setBounds(new Rectangle(18, 78, 991, 473));
        kdtEntrys_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtEntrys,new com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryInfo(),null,false);
        this.add(kdtEntrys_detailPanel, new KDLayout.Constraints(18, 78, 991, 473, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contBaseStatus.setBounds(new Rectangle(716, 14, 270, 19));
        this.add(contBaseStatus, new KDLayout.Constraints(716, 14, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditTime.setBounds(new Rectangle(726, 597, 270, 19));
        this.add(contAuditTime, new KDLayout.Constraints(726, 597, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contserviceOrgUnit.setBounds(new Rectangle(716, 41, 270, 19));
        this.add(contserviceOrgUnit, new KDLayout.Constraints(716, 41, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
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
		dataBinder.registerBinding("entrys", com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryInfo.class, this.kdtEntrys, "userObject");
		dataBinder.registerBinding("entrys.bizDate", java.util.Date.class, this.kdtEntrys, "bizDate.text");
		dataBinder.registerBinding("entrys.option", String.class, this.kdtEntrys, "option.text");
		dataBinder.registerBinding("entrys.rqn", String.class, this.kdtEntrys, "rqn.text");
		dataBinder.registerBinding("entrys.billNum", String.class, this.kdtEntrys, "billNum.text");
		dataBinder.registerBinding("entrys.wip", String.class, this.kdtEntrys, "wip.text");
		dataBinder.registerBinding("entrys.lineSeq", int.class, this.kdtEntrys, "lineSeq.text");
		dataBinder.registerBinding("entrys.customer", String.class, this.kdtEntrys, "customer.text");
		dataBinder.registerBinding("entrys.supplier", String.class, this.kdtEntrys, "supplier.text");
		dataBinder.registerBinding("entrys.materialNum", String.class, this.kdtEntrys, "materialNum.text");
		dataBinder.registerBinding("entrys.qty", java.math.BigDecimal.class, this.kdtEntrys, "qty.text");
		dataBinder.registerBinding("entrys.supplyQty", java.math.BigDecimal.class, this.kdtEntrys, "supplyQty.text");
		dataBinder.registerBinding("entrys.remainQty", java.math.BigDecimal.class, this.kdtEntrys, "remainQty.text");
		dataBinder.registerBinding("entrys.audit", String.class, this.kdtEntrys, "audit.text");
		dataBinder.registerBinding("entrys.cost", java.math.BigDecimal.class, this.kdtEntrys, "cost.text");
		dataBinder.registerBinding("entrys.L", String.class, this.kdtEntrys, "L.text");
		dataBinder.registerBinding("entrys.T", String.class, this.kdtEntrys, "T.text");
		dataBinder.registerBinding("entrys.easSupplier", java.lang.Object.class, this.kdtEntrys, "easSupplier.text");
		dataBinder.registerBinding("entrys.easCustomer", java.lang.Object.class, this.kdtEntrys, "easCustomer.text");
		dataBinder.registerBinding("entrys.easWarehouse", java.lang.Object.class, this.kdtEntrys, "easWarehouse.text");
		dataBinder.registerBinding("entrys.easRepairWO", java.lang.Object.class, this.kdtEntrys, "easRepairWO.text");
		dataBinder.registerBinding("entrys.easTaxPrice", java.math.BigDecimal.class, this.kdtEntrys, "easTaxPrice.text");
		dataBinder.registerBinding("entrys.easMaterial", java.lang.Object.class, this.kdtEntrys, "easMaterial.text");
		dataBinder.registerBinding("entrys.easBaseUnit", java.lang.Object.class, this.kdtEntrys, "easBaseUnit.text");
		dataBinder.registerBinding("entrys.easVehicle", java.lang.Object.class, this.kdtEntrys, "easVehicle.text");
		dataBinder.registerBinding("entrys.easRepairWOEntrySeq", int.class, this.kdtEntrys, "easRepairWOEntrySeq.text");
		dataBinder.registerBinding("entrys.easRepairWONumber", String.class, this.kdtEntrys, "easRepairWONumber.text");
		dataBinder.registerBinding("entrys.isTransferred", boolean.class, this.kdtEntrys, "isTransferred.text");
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
		dataBinder.registerBinding("serviceOrgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtserviceOrgUnit, "data");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.ga.syncdata.app.DMSInOutQueryEditUIHandler";
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
        this.editData = (com.kingdee.eas.ga.syncdata.DMSInOutQueryInfo)ov;
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
	 * ????????§µ??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );
		getValidateHelper().registerBindProperty("entrys.lineStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.lineDesc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.option", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.rqn", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.billNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.wip", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.lineSeq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.customer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.supplier", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.materialNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.qty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.supplyQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.remainQty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.audit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.cost", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.L", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.T", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.easSupplier", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.easCustomer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.easWarehouse", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.easRepairWO", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.easTaxPrice", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.easMaterial", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.easBaseUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.easVehicle", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.easRepairWOEntrySeq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.easRepairWONumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.isTransferred", ValidateHelper.ON_SAVE);    
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
    	sic.add(new SelectorItemInfo("entrys.bizDate"));
    	sic.add(new SelectorItemInfo("entrys.option"));
    	sic.add(new SelectorItemInfo("entrys.rqn"));
    	sic.add(new SelectorItemInfo("entrys.billNum"));
    	sic.add(new SelectorItemInfo("entrys.wip"));
    	sic.add(new SelectorItemInfo("entrys.lineSeq"));
    	sic.add(new SelectorItemInfo("entrys.customer"));
    	sic.add(new SelectorItemInfo("entrys.supplier"));
    	sic.add(new SelectorItemInfo("entrys.materialNum"));
    	sic.add(new SelectorItemInfo("entrys.qty"));
    	sic.add(new SelectorItemInfo("entrys.supplyQty"));
    	sic.add(new SelectorItemInfo("entrys.remainQty"));
    	sic.add(new SelectorItemInfo("entrys.audit"));
    	sic.add(new SelectorItemInfo("entrys.cost"));
    	sic.add(new SelectorItemInfo("entrys.L"));
    	sic.add(new SelectorItemInfo("entrys.T"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.easSupplier.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.easSupplier.id"));
			sic.add(new SelectorItemInfo("entrys.easSupplier.name"));
        	sic.add(new SelectorItemInfo("entrys.easSupplier.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.easCustomer.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.easCustomer.id"));
			sic.add(new SelectorItemInfo("entrys.easCustomer.name"));
        	sic.add(new SelectorItemInfo("entrys.easCustomer.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.easWarehouse.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.easWarehouse.id"));
			sic.add(new SelectorItemInfo("entrys.easWarehouse.name"));
        	sic.add(new SelectorItemInfo("entrys.easWarehouse.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.easRepairWO.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.easRepairWO.id"));
			sic.add(new SelectorItemInfo("entrys.easRepairWO.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.easTaxPrice"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.easMaterial.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.easMaterial.id"));
			sic.add(new SelectorItemInfo("entrys.easMaterial.name"));
        	sic.add(new SelectorItemInfo("entrys.easMaterial.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.easBaseUnit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.easBaseUnit.id"));
			sic.add(new SelectorItemInfo("entrys.easBaseUnit.name"));
        	sic.add(new SelectorItemInfo("entrys.easBaseUnit.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.easVehicle.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.easVehicle.id"));
			sic.add(new SelectorItemInfo("entrys.easVehicle.plateNum"));
			sic.add(new SelectorItemInfo("entrys.easVehicle.name"));
        	sic.add(new SelectorItemInfo("entrys.easVehicle.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.easRepairWOEntrySeq"));
    	sic.add(new SelectorItemInfo("entrys.easRepairWONumber"));
    	sic.add(new SelectorItemInfo("entrys.isTransferred"));
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
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("serviceOrgUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("serviceOrgUnit.id"));
        	sic.add(new SelectorItemInfo("serviceOrgUnit.number"));
        	sic.add(new SelectorItemInfo("serviceOrgUnit.name"));
        	sic.add(new SelectorItemInfo("serviceOrgUnit.isEntity"));
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
        return new MetaDataPK("com.kingdee.eas.ga.syncdata.client", "DMSInOutQueryEditUI");
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
        return com.kingdee.eas.ga.syncdata.client.DMSInOutQueryEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.ga.syncdata.DMSInOutQueryFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.ga.syncdata.DMSInOutQueryInfo objectValue = new com.kingdee.eas.ga.syncdata.DMSInOutQueryInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/ga/syncdata/DMSInOutQuery";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.ga.syncdata.app.DMSInOutQueryQuery");
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