/**
 * output package name
 */
package com.kingdee.eas.ga.rs.client;

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
public abstract class AbstractRepairManEditUI extends com.kingdee.eas.myframework.template.base.client.SimpleBizBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractRepairManEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contname;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttel;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contemail;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contidNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contzipCode;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contaddr;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contorgUnit;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtname;
    protected com.kingdee.bos.ctrl.swing.KDTextField txttel;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtemail;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtidNumber;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtzipCode;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane scrollPaneaddr;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtaddr;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtorgUnit;
    protected com.kingdee.eas.ga.rs.RepairManInfo editData = null;
    /**
     * output class constructor
     */
    public AbstractRepairManEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractRepairManEditUI.class.getName());
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
        this.contname = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttel = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contemail = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contidNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contzipCode = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contaddr = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contorgUnit = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtname = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txttel = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtemail = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtidNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtzipCode = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.scrollPaneaddr = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txtaddr = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.prmtorgUnit = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.contname.setName("contname");
        this.conttel.setName("conttel");
        this.contemail.setName("contemail");
        this.contidNumber.setName("contidNumber");
        this.contzipCode.setName("contzipCode");
        this.contaddr.setName("contaddr");
        this.contorgUnit.setName("contorgUnit");
        this.txtname.setName("txtname");
        this.txttel.setName("txttel");
        this.txtemail.setName("txtemail");
        this.txtidNumber.setName("txtidNumber");
        this.txtzipCode.setName("txtzipCode");
        this.scrollPaneaddr.setName("scrollPaneaddr");
        this.txtaddr.setName("txtaddr");
        this.prmtorgUnit.setName("prmtorgUnit");
        // CoreUI		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contBizDate.setVisible(false);		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));		
        this.contDescription.setVisible(false);
		String kdtEntrysStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol2\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol3\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol4\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol5\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol6\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol7\"><c:Protection locked=\"true\" /></c:Style><c:Style id=\"sCol8\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol9\"><c:Protection locked=\"true\" hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"vehicle\" t:width=\"150\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"plateNum\" t:width=\"80\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" t:styleID=\"sCol2\" /><t:Column t:key=\"vIN\" t:width=\"180\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol3\" /><t:Column t:key=\"engineNum\" t:width=\"130\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol4\" /><t:Column t:key=\"seriesName\" t:width=\"80\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol5\" /><t:Column t:key=\"modelName\" t:width=\"180\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol6\" /><t:Column t:key=\"brandName\" t:width=\"80\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol7\" /><t:Column t:key=\"lineDesc\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol8\" /><t:Column t:key=\"lineStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol9\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{vehicle}</t:Cell><t:Cell>$Resource{plateNum}</t:Cell><t:Cell>$Resource{vIN}</t:Cell><t:Cell>$Resource{engineNum}</t:Cell><t:Cell>$Resource{seriesName}</t:Cell><t:Cell>$Resource{modelName}</t:Cell><t:Cell>$Resource{brandName}</t:Cell><t:Cell>$Resource{lineDesc}</t:Cell><t:Cell>$Resource{lineStatus}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
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


                this.kdtEntrys.putBindContents("editData",new String[] {"","vehicle","vehicle.plateNum","vehicle.vIN","vehicle.engineNum","vehicle.series","vehicle.model","vehicle.brand","lineDesc","lineStatus"});


        this.kdtEntrys.checkParsed();
        final KDBizPromptBox kdtEntrys_vehicle_PromptBox = new KDBizPromptBox();
        kdtEntrys_vehicle_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.VehicleF7Query");
        kdtEntrys_vehicle_PromptBox.setVisible(true);
        kdtEntrys_vehicle_PromptBox.setEditable(true);
        kdtEntrys_vehicle_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_vehicle_PromptBox.setEditFormat("$number$");
        kdtEntrys_vehicle_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_vehicle_CellEditor = new KDTDefaultCellEditor(kdtEntrys_vehicle_PromptBox);
        this.kdtEntrys.getColumn("vehicle").setEditor(kdtEntrys_vehicle_CellEditor);
        ObjectValueRender kdtEntrys_vehicle_OVR = new ObjectValueRender();
        kdtEntrys_vehicle_OVR.setFormat(new BizDataFormat("$number$"));
        this.kdtEntrys.getColumn("vehicle").setRenderer(kdtEntrys_vehicle_OVR);
        KDTextField kdtEntrys_plateNum_TextField = new KDTextField();
        kdtEntrys_plateNum_TextField.setName("kdtEntrys_plateNum_TextField");
        kdtEntrys_plateNum_TextField.setMaxLength(44);
        KDTDefaultCellEditor kdtEntrys_plateNum_CellEditor = new KDTDefaultCellEditor(kdtEntrys_plateNum_TextField);
        this.kdtEntrys.getColumn("plateNum").setEditor(kdtEntrys_plateNum_CellEditor);
        KDTextField kdtEntrys_vIN_TextField = new KDTextField();
        kdtEntrys_vIN_TextField.setName("kdtEntrys_vIN_TextField");
        kdtEntrys_vIN_TextField.setMaxLength(44);
        KDTDefaultCellEditor kdtEntrys_vIN_CellEditor = new KDTDefaultCellEditor(kdtEntrys_vIN_TextField);
        this.kdtEntrys.getColumn("vIN").setEditor(kdtEntrys_vIN_CellEditor);
        KDTextField kdtEntrys_engineNum_TextField = new KDTextField();
        kdtEntrys_engineNum_TextField.setName("kdtEntrys_engineNum_TextField");
        kdtEntrys_engineNum_TextField.setMaxLength(44);
        KDTDefaultCellEditor kdtEntrys_engineNum_CellEditor = new KDTDefaultCellEditor(kdtEntrys_engineNum_TextField);
        this.kdtEntrys.getColumn("engineNum").setEditor(kdtEntrys_engineNum_CellEditor);
        final KDBizPromptBox kdtEntrys_seriesName_PromptBox = new KDBizPromptBox();
        kdtEntrys_seriesName_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.SeriesQuery");
        kdtEntrys_seriesName_PromptBox.setVisible(true);
        kdtEntrys_seriesName_PromptBox.setEditable(true);
        kdtEntrys_seriesName_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_seriesName_PromptBox.setEditFormat("$number$");
        kdtEntrys_seriesName_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_seriesName_CellEditor = new KDTDefaultCellEditor(kdtEntrys_seriesName_PromptBox);
        this.kdtEntrys.getColumn("seriesName").setEditor(kdtEntrys_seriesName_CellEditor);
        ObjectValueRender kdtEntrys_seriesName_OVR = new ObjectValueRender();
        kdtEntrys_seriesName_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("seriesName").setRenderer(kdtEntrys_seriesName_OVR);
        final KDBizPromptBox kdtEntrys_modelName_PromptBox = new KDBizPromptBox();
        kdtEntrys_modelName_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.ModelQuery");
        kdtEntrys_modelName_PromptBox.setVisible(true);
        kdtEntrys_modelName_PromptBox.setEditable(true);
        kdtEntrys_modelName_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_modelName_PromptBox.setEditFormat("$number$");
        kdtEntrys_modelName_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_modelName_CellEditor = new KDTDefaultCellEditor(kdtEntrys_modelName_PromptBox);
        this.kdtEntrys.getColumn("modelName").setEditor(kdtEntrys_modelName_CellEditor);
        ObjectValueRender kdtEntrys_modelName_OVR = new ObjectValueRender();
        kdtEntrys_modelName_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("modelName").setRenderer(kdtEntrys_modelName_OVR);
        final KDBizPromptBox kdtEntrys_brandName_PromptBox = new KDBizPromptBox();
        kdtEntrys_brandName_PromptBox.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.BrandQuery");
        kdtEntrys_brandName_PromptBox.setVisible(true);
        kdtEntrys_brandName_PromptBox.setEditable(true);
        kdtEntrys_brandName_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_brandName_PromptBox.setEditFormat("$number$");
        kdtEntrys_brandName_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_brandName_CellEditor = new KDTDefaultCellEditor(kdtEntrys_brandName_PromptBox);
        this.kdtEntrys.getColumn("brandName").setEditor(kdtEntrys_brandName_CellEditor);
        ObjectValueRender kdtEntrys_brandName_OVR = new ObjectValueRender();
        kdtEntrys_brandName_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("brandName").setRenderer(kdtEntrys_brandName_OVR);
        KDTextField kdtEntrys_lineDesc_TextField = new KDTextField();
        kdtEntrys_lineDesc_TextField.setName("kdtEntrys_lineDesc_TextField");
        kdtEntrys_lineDesc_TextField.setMaxLength(255);
        KDTDefaultCellEditor kdtEntrys_lineDesc_CellEditor = new KDTDefaultCellEditor(kdtEntrys_lineDesc_TextField);
        this.kdtEntrys.getColumn("lineDesc").setEditor(kdtEntrys_lineDesc_CellEditor);
        KDComboBox kdtEntrys_lineStatus_ComboBox = new KDComboBox();
        kdtEntrys_lineStatus_ComboBox.setName("kdtEntrys_lineStatus_ComboBox");
        kdtEntrys_lineStatus_ComboBox.setVisible(true);
        kdtEntrys_lineStatus_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.scm.common.EntryBaseStatusEnum").toArray());
        KDTDefaultCellEditor kdtEntrys_lineStatus_CellEditor = new KDTDefaultCellEditor(kdtEntrys_lineStatus_ComboBox);
        this.kdtEntrys.getColumn("lineStatus").setEditor(kdtEntrys_lineStatus_CellEditor);		
        this.prmtCreator.setVisible(false);		
        this.kDDateCreateTime.setVisible(false);		
        this.prmtLastUpdateUser.setVisible(false);		
        this.kDDateLastUpdateTime.setVisible(false);		
        this.pkBizDate.setVisible(false);		
        this.txtDescription.setVisible(false);		
        this.prmtAuditor.setVisible(false);		
        this.boxBaseStatus.setVisible(false);		
        this.kDDateAuditTime.setVisible(false);
        // contname		
        this.contname.setBoundLabelText(resHelper.getString("contname.boundLabelText"));		
        this.contname.setBoundLabelLength(100);		
        this.contname.setBoundLabelUnderline(true);		
        this.contname.setVisible(true);
        // conttel		
        this.conttel.setBoundLabelText(resHelper.getString("conttel.boundLabelText"));		
        this.conttel.setBoundLabelLength(100);		
        this.conttel.setBoundLabelUnderline(true);		
        this.conttel.setVisible(true);
        // contemail		
        this.contemail.setBoundLabelText(resHelper.getString("contemail.boundLabelText"));		
        this.contemail.setBoundLabelLength(100);		
        this.contemail.setBoundLabelUnderline(true);		
        this.contemail.setVisible(true);
        // contidNumber		
        this.contidNumber.setBoundLabelText(resHelper.getString("contidNumber.boundLabelText"));		
        this.contidNumber.setBoundLabelLength(100);		
        this.contidNumber.setBoundLabelUnderline(true);		
        this.contidNumber.setVisible(true);
        // contzipCode		
        this.contzipCode.setBoundLabelText(resHelper.getString("contzipCode.boundLabelText"));		
        this.contzipCode.setBoundLabelLength(100);		
        this.contzipCode.setBoundLabelUnderline(true);		
        this.contzipCode.setVisible(true);
        // contaddr		
        this.contaddr.setBoundLabelText(resHelper.getString("contaddr.boundLabelText"));		
        this.contaddr.setBoundLabelLength(100);		
        this.contaddr.setBoundLabelUnderline(true);		
        this.contaddr.setVisible(true);
        // contorgUnit		
        this.contorgUnit.setBoundLabelText(resHelper.getString("contorgUnit.boundLabelText"));		
        this.contorgUnit.setBoundLabelLength(100);		
        this.contorgUnit.setBoundLabelUnderline(true);		
        this.contorgUnit.setVisible(true);
        // txtname		
        this.txtname.setHorizontalAlignment(2);		
        this.txtname.setMaxLength(100);		
        this.txtname.setRequired(false);
        // txttel		
        this.txttel.setHorizontalAlignment(2);		
        this.txttel.setMaxLength(100);		
        this.txttel.setRequired(false);
        // txtemail		
        this.txtemail.setHorizontalAlignment(2);		
        this.txtemail.setMaxLength(100);		
        this.txtemail.setRequired(false);
        // txtidNumber		
        this.txtidNumber.setHorizontalAlignment(2);		
        this.txtidNumber.setMaxLength(100);		
        this.txtidNumber.setRequired(false);
        // txtzipCode		
        this.txtzipCode.setHorizontalAlignment(2);		
        this.txtzipCode.setMaxLength(100);		
        this.txtzipCode.setRequired(false);
        // scrollPaneaddr
        // txtaddr		
        this.txtaddr.setRequired(false);		
        this.txtaddr.setMaxLength(255);
        // prmtorgUnit		
        this.prmtorgUnit.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");		
        this.prmtorgUnit.setVisible(true);		
        this.prmtorgUnit.setEditable(true);		
        this.prmtorgUnit.setDisplayFormat("$name$");		
        this.prmtorgUnit.setEditFormat("$number$");		
        this.prmtorgUnit.setCommitFormat("$number$");		
        this.prmtorgUnit.setRequired(true);		
        this.prmtorgUnit.setEnabled(false);
        		setOrgF7(prmtorgUnit,com.kingdee.eas.basedata.org.OrgType.getEnum("Admin"));
					
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {txtNumber,txtname,txttel,txtemail,txtidNumber,txtzipCode,txtaddr,kDDateLastUpdateTime,prmtLastUpdateUser,kDDateCreateTime,prmtCreator,prmtAuditor,txtDescription,pkBizDate,kDDateAuditTime,boxBaseStatus,kdtEntrys,prmtorgUnit}));
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
        this.setBounds(new Rectangle(0, 0, 1016, 419));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 1016, 419));
        contCreator.setBounds(new Rectangle(18, 427, 270, 19));
        this.add(contCreator, new KDLayout.Constraints(18, 427, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contCreateTime.setBounds(new Rectangle(18, 453, 270, 19));
        this.add(contCreateTime, new KDLayout.Constraints(18, 453, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateUser.setBounds(new Rectangle(373, 427, 270, 19));
        this.add(contLastUpdateUser, new KDLayout.Constraints(373, 427, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateTime.setBounds(new Rectangle(373, 453, 270, 19));
        this.add(contLastUpdateTime, new KDLayout.Constraints(373, 453, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contNumber.setBounds(new Rectangle(15, 8, 270, 19));
        this.add(contNumber, new KDLayout.Constraints(15, 8, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBizDate.setBounds(new Rectangle(755, 436, 137, 19));
        this.add(contBizDate, new KDLayout.Constraints(755, 436, 137, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contDescription.setBounds(new Rectangle(580, 431, 176, 19));
        this.add(contDescription, new KDLayout.Constraints(580, 431, 176, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contAuditor.setBounds(new Rectangle(729, 427, 270, 19));
        this.add(contAuditor, new KDLayout.Constraints(729, 427, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        kdtEntrys.setBounds(new Rectangle(5, 111, 991, 295));
        kdtEntrys_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtEntrys,new com.kingdee.eas.ga.rs.RepairManEntryInfo(),null,false);
        this.add(kdtEntrys_detailPanel, new KDLayout.Constraints(5, 111, 991, 295, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contBaseStatus.setBounds(new Rectangle(569, 439, 270, 19));
        this.add(contBaseStatus, new KDLayout.Constraints(569, 439, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contAuditTime.setBounds(new Rectangle(729, 453, 270, 19));
        this.add(contAuditTime, new KDLayout.Constraints(729, 453, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contname.setBounds(new Rectangle(349, 8, 270, 19));
        this.add(contname, new KDLayout.Constraints(349, 8, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        conttel.setBounds(new Rectangle(683, 8, 270, 19));
        this.add(conttel, new KDLayout.Constraints(683, 8, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contemail.setBounds(new Rectangle(15, 31, 270, 19));
        this.add(contemail, new KDLayout.Constraints(15, 31, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contidNumber.setBounds(new Rectangle(349, 31, 270, 19));
        this.add(contidNumber, new KDLayout.Constraints(349, 31, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contzipCode.setBounds(new Rectangle(683, 31, 270, 19));
        this.add(contzipCode, new KDLayout.Constraints(683, 31, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contaddr.setBounds(new Rectangle(13, 57, 605, 44));
        this.add(contaddr, new KDLayout.Constraints(13, 57, 605, 44, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contorgUnit.setBounds(new Rectangle(683, 57, 270, 19));
        this.add(contorgUnit, new KDLayout.Constraints(683, 57, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
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
        //contname
        contname.setBoundEditor(txtname);
        //conttel
        conttel.setBoundEditor(txttel);
        //contemail
        contemail.setBoundEditor(txtemail);
        //contidNumber
        contidNumber.setBoundEditor(txtidNumber);
        //contzipCode
        contzipCode.setBoundEditor(txtzipCode);
        //contaddr
        contaddr.setBoundEditor(scrollPaneaddr);
        //scrollPaneaddr
        scrollPaneaddr.getViewport().add(txtaddr, null);
        //contorgUnit
        contorgUnit.setBoundEditor(prmtorgUnit);

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
		dataBinder.registerBinding("entrys", com.kingdee.eas.ga.rs.RepairManEntryInfo.class, this.kdtEntrys, "userObject");
		dataBinder.registerBinding("entrys.vehicle", java.lang.Object.class, this.kdtEntrys, "vehicle.text");
		dataBinder.registerBinding("entrys.vehicle.plateNum", String.class, this.kdtEntrys, "plateNum.text");
		dataBinder.registerBinding("entrys.vehicle.vIN", String.class, this.kdtEntrys, "vIN.text");
		dataBinder.registerBinding("entrys.vehicle.engineNum", String.class, this.kdtEntrys, "engineNum.text");
		dataBinder.registerBinding("entrys.vehicle.series", java.lang.Object.class, this.kdtEntrys, "seriesName.text");
		dataBinder.registerBinding("entrys.vehicle.model", java.lang.Object.class, this.kdtEntrys, "modelName.text");
		dataBinder.registerBinding("entrys.vehicle.brand", java.lang.Object.class, this.kdtEntrys, "brandName.text");
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
		dataBinder.registerBinding("name", String.class, this.txtname, "text");
		dataBinder.registerBinding("tel", String.class, this.txttel, "text");
		dataBinder.registerBinding("email", String.class, this.txtemail, "text");
		dataBinder.registerBinding("idNumber", String.class, this.txtidNumber, "text");
		dataBinder.registerBinding("zipCode", String.class, this.txtzipCode, "text");
		dataBinder.registerBinding("addr", String.class, this.txtaddr, "text");
		dataBinder.registerBinding("orgUnit", com.kingdee.eas.basedata.org.AdminOrgUnitInfo.class, this.prmtorgUnit, "data");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.ga.rs.app.RepairManEditUIHandler";
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
        this.txtNumber.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.ga.rs.RepairManInfo)ov;
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
		return prmtorgUnit;
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
	 * ????????§µ??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );
		getValidateHelper().registerBindProperty("entrys.lineStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.lineDesc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.vehicle", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.vehicle.plateNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.vehicle.vIN", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.vehicle.engineNum", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.vehicle.series", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.vehicle.model", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.vehicle.brand", ValidateHelper.ON_SAVE);    
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
		getValidateHelper().registerBindProperty("name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("tel", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("email", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("idNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("zipCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("addr", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("orgUnit", ValidateHelper.ON_SAVE);    		
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
     * output kdtEntrys_Changed(int rowIndex,int colIndex) method
     */
    public void kdtEntrys_Changed(int rowIndex,int colIndex) throws Exception
    {
            if ("vehicle".equalsIgnoreCase(kdtEntrys.getColumn(colIndex).getKey())) {
kdtEntrys.getCell(rowIndex,"plateNum").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntrys.getCell(rowIndex,"vehicle").getValue(),"plateNum"));

}

    if ("vehicle".equalsIgnoreCase(kdtEntrys.getColumn(colIndex).getKey())) {
kdtEntrys.getCell(rowIndex,"vIN").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntrys.getCell(rowIndex,"vehicle").getValue(),"vIN"));

}

    if ("vehicle".equalsIgnoreCase(kdtEntrys.getColumn(colIndex).getKey())) {
kdtEntrys.getCell(rowIndex,"engineNum").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntrys.getCell(rowIndex,"vehicle").getValue(),"engineNum"));

}

    if ("vehicle".equalsIgnoreCase(kdtEntrys.getColumn(colIndex).getKey())) {
kdtEntrys.getCell(rowIndex,"seriesName").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntrys.getCell(rowIndex,"vehicle").getValue(),"series.name"));

}

    if ("vehicle".equalsIgnoreCase(kdtEntrys.getColumn(colIndex).getKey())) {
kdtEntrys.getCell(rowIndex,"modelName").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntrys.getCell(rowIndex,"vehicle").getValue(),"model.name"));

}

    if ("vehicle".equalsIgnoreCase(kdtEntrys.getColumn(colIndex).getKey())) {
kdtEntrys.getCell(rowIndex,"brandName").setValue(com.kingdee.bos.ui.face.UIRuleUtil.getProperty((com.kingdee.bos.dao.IObjectValue)kdtEntrys.getCell(rowIndex,"vehicle").getValue(),"brand.name"));

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
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.vehicle.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.vehicle.id"));
			sic.add(new SelectorItemInfo("entrys.vehicle.number"));
			sic.add(new SelectorItemInfo("entrys.vehicle.name"));
		}
    	sic.add(new SelectorItemInfo("entrys.vehicle.plateNum"));
    	sic.add(new SelectorItemInfo("entrys.vehicle.vIN"));
    	sic.add(new SelectorItemInfo("entrys.vehicle.engineNum"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.vehicle.series.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.vehicle.series.id"));
			sic.add(new SelectorItemInfo("entrys.vehicle.series.name"));
        	sic.add(new SelectorItemInfo("entrys.vehicle.series.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.vehicle.model.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.vehicle.model.id"));
			sic.add(new SelectorItemInfo("entrys.vehicle.model.name"));
        	sic.add(new SelectorItemInfo("entrys.vehicle.model.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.vehicle.brand.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.vehicle.brand.id"));
			sic.add(new SelectorItemInfo("entrys.vehicle.brand.name"));
        	sic.add(new SelectorItemInfo("entrys.vehicle.brand.number"));
		}
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
        sic.add(new SelectorItemInfo("name"));
        sic.add(new SelectorItemInfo("tel"));
        sic.add(new SelectorItemInfo("email"));
        sic.add(new SelectorItemInfo("idNumber"));
        sic.add(new SelectorItemInfo("zipCode"));
        sic.add(new SelectorItemInfo("addr"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("orgUnit.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("orgUnit.id"));
        	sic.add(new SelectorItemInfo("orgUnit.number"));
        	sic.add(new SelectorItemInfo("orgUnit.name"));
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
        return new MetaDataPK("com.kingdee.eas.ga.rs.client", "RepairManEditUI");
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
        return com.kingdee.eas.ga.rs.client.RepairManEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.ga.rs.RepairManFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.ga.rs.RepairManInfo objectValue = new com.kingdee.eas.ga.rs.RepairManInfo();
				if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Admin")) != null)
			objectValue.put("orgUnit",com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Admin")));
 
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/ga/rs/RepairMan";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.ga.rs.app.RepairManQuery");
	}
    
        
					protected void beforeStoreFields(ActionEvent arg0) throws Exception {
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(prmtorgUnit.getData())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"¹«Ë¾"});
		}
			super.beforeStoreFields(arg0);
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