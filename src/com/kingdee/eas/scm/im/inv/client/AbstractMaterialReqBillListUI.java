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
public abstract class AbstractMaterialReqBillListUI extends com.kingdee.eas.scm.im.inv.client.InvBillListUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractMaterialReqBillListUI.class);
    protected javax.swing.JToolBar.Separator separator10;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton kDelObj;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton RelObj;
    protected com.kingdee.bos.ctrl.swing.KDSeparator kDSeparator8;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemGetMaterial;
    protected com.kingdee.bos.ctrl.swing.KDMenuItem menuItemCancelMaterial;
    protected ActionGetMaterial actionGetMaterial = null;
    protected ActionCancelMaterial actionCancelMaterial = null;
    protected kDelObj1 kDelObj1 = null;
    protected RelObj1 RelObj1 = null;
    /**
     * output class constructor
     */
    public AbstractMaterialReqBillListUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractMaterialReqBillListUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        mainQueryPK = new MetaDataPK("com.kingdee.eas.scm.im.inv", "MaterialReqBillQuery");
        //actionAddNew
        String _tempStr = null;
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

        actionEdit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
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
        //actionRemove
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
        //actionGetMaterial
        this.actionGetMaterial = new ActionGetMaterial(this);
        getActionManager().registerAction("actionGetMaterial", actionGetMaterial);
         this.actionGetMaterial.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCancelMaterial
        this.actionCancelMaterial = new ActionCancelMaterial(this);
        getActionManager().registerAction("actionCancelMaterial", actionCancelMaterial);
         this.actionCancelMaterial.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //kDelObj1
        this.kDelObj1 = new kDelObj1(this);
        getActionManager().registerAction("kDelObj1", kDelObj1);
         this.kDelObj1.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //RelObj1
        this.RelObj1 = new RelObj1(this);
        getActionManager().registerAction("RelObj1", RelObj1);
         this.RelObj1.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.separator10 = new javax.swing.JToolBar.Separator();
        this.kDelObj = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.RelObj = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.kDSeparator8 = new com.kingdee.bos.ctrl.swing.KDSeparator();
        this.menuItemGetMaterial = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.menuItemCancelMaterial = new com.kingdee.bos.ctrl.swing.KDMenuItem();
        this.separator10.setName("separator10");
        this.kDelObj.setName("kDelObj");
        this.RelObj.setName("RelObj");
        this.kDSeparator8.setName("kDSeparator8");
        this.menuItemGetMaterial.setName("menuItemGetMaterial");
        this.menuItemCancelMaterial.setName("menuItemCancelMaterial");
        // CoreUI
		String tblMainStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol3\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol4\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol7\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol10\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol11\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol12\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol13\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol14\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol17\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol18\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol24\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol25\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol26\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol27\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol28\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol29\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol33\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol34\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol40\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol41\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol42\"><c:Protection hidden=\"true\" /><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol43\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol44\"><c:Protection hidden=\"true\" /><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol46\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol47\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol48\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol49\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol53\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol54\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol55\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol56\"><c:Protection hidden=\"true\" /><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol57\"><c:Protection hidden=\"true\" /><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol58\"><c:Protection hidden=\"true\" /><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol59\"><c:Protection hidden=\"true\" /><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol60\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol61\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol62\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol63\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol64\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol65\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol66\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol67\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol68\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol69\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol70\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol71\"><c:Protection hidden=\"true\" /><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol72\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol73\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol74\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol75\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol76\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol77\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol78\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol79\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol80\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol81\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol82\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol83\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol84\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol85\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol86\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol87\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol88\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol89\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol90\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol91\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol92\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol93\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol94\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol95\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol96\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol97\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol98\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol99\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol100\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol101\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol102\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol103\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol104\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"number\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" /><t:Column t:key=\"baseStatus\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"bizType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /><t:Column t:key=\"purchaseType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /><t:Column t:key=\"supplier.name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" t:styleID=\"sCol4\" /><t:Column t:key=\"transactionType.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" /><t:Column t:key=\"bizDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" /><t:Column t:key=\"storageOrgUnit.id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /><t:Column t:key=\"storageOrgUnit.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" /><t:Column t:key=\"supplyStoreOrgUnit.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" /><t:Column t:key=\"department.name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" t:styleID=\"sCol10\" /><t:Column t:key=\"totalStandardCost\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" t:styleID=\"sCol11\" /><t:Column t:key=\"totalActualCost\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" t:styleID=\"sCol12\" /><t:Column t:key=\"isOfSet\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" t:styleID=\"sCol13\" /><t:Column t:key=\"isVoucher\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" t:styleID=\"sCol14\" /><t:Column t:key=\"voucher.number\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" /><t:Column t:key=\"entry.seq\" t:width=\"50\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" /><t:Column t:key=\"productID.number\" t:width=\"0\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"17\" t:styleID=\"sCol17\" /><t:Column t:key=\"productID.name\" t:width=\"0\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" t:styleID=\"sCol18\" /><t:Column t:key=\"mainAdminOrgUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"19\" /><t:Column t:key=\"costCenter.number\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"20\" /><t:Column t:key=\"costOrgUnit.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"21\" /><t:Column t:key=\"costObjectNumber\" t:width=\"120\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"23\" /><t:Column t:key=\"costObjectName\" t:width=\"120\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"24\" /><t:Column t:key=\"costItem.number\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"25\" t:styleID=\"sCol24\" /><t:Column t:key=\"costItem.name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"26\" t:styleID=\"sCol25\" /><t:Column t:key=\"costObjectSuite.number\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"27\" t:styleID=\"sCol26\" /><t:Column t:key=\"costObjectSuite.name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"28\" t:styleID=\"sCol27\" /><t:Column t:key=\"processMaterial.number\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"29\" t:styleID=\"sCol28\" /><t:Column t:key=\"processMaterial.name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"30\" t:styleID=\"sCol29\" /><t:Column t:key=\"material.number\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"31\" /><t:Column t:key=\"material.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"32\" /><t:Column t:key=\"material.model\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"33\" /><t:Column t:key=\"kdClassNumber\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"34\" t:styleID=\"sCol33\" /><t:Column t:key=\"kdClassName\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"35\" t:styleID=\"sCol34\" /><t:Column t:key=\"entry.assistantAttr\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"36\" /><t:Column t:key=\"entry.batch\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"37\" /><t:Column t:key=\"mfg\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"38\" /><t:Column t:key=\"exp\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"39\" /><t:Column t:key=\"unit.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"40\" /><t:Column t:key=\"entry.qty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"41\" t:styleID=\"sCol40\" /><t:Column t:key=\"baseUnit\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"42\" t:styleID=\"sCol41\" /><t:Column t:key=\"baseQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"43\" t:styleID=\"sCol42\" /><t:Column t:key=\"assistUnit\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"44\" t:styleID=\"sCol43\" /><t:Column t:key=\"assistQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"45\" t:styleID=\"sCol44\" /><t:Column t:key=\"warehouse.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"46\" /><t:Column t:key=\"projectNumCol\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"47\" t:styleID=\"sCol46\" /><t:Column t:key=\"trackNumCol\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"48\" t:styleID=\"sCol47\" /><t:Column t:key=\"stocker\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"49\" t:styleID=\"sCol48\" /><t:Column t:key=\"picker\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"50\" t:styleID=\"sCol49\" /><t:Column t:key=\"location.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"51\" /><t:Column t:key=\"supplyWareHS\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"52\" /><t:Column t:key=\"supplyLocation\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"53\" /><t:Column t:key=\"customer\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"54\" t:styleID=\"sCol53\" /><t:Column t:key=\"supplier\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"55\" t:styleID=\"sCol54\" /><t:Column t:key=\"isPresent\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"56\" t:styleID=\"sCol55\" /><t:Column t:key=\"unitStandardCost\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"57\" t:styleID=\"sCol56\" /><t:Column t:key=\"standardCost\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"58\" t:styleID=\"sCol57\" /><t:Column t:key=\"unitActualCost\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"59\" t:styleID=\"sCol58\" /><t:Column t:key=\"actualCost\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"60\" t:styleID=\"sCol59\" /><t:Column t:key=\"entry.subWrittenOffQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"61\" t:styleID=\"sCol60\" /><t:Column t:key=\"entry.subWrittenOffBaseQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"62\" t:styleID=\"sCol61\" /><t:Column t:key=\"entry.subUnWriteOffQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"63\" t:styleID=\"sCol62\" /><t:Column t:key=\"entry.subUnWriteOffBaseQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"64\" t:styleID=\"sCol63\" /><t:Column t:key=\"writtenOffAmount\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"65\" t:styleID=\"sCol64\" /><t:Column t:key=\"unWriteOffAmount\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"66\" t:styleID=\"sCol65\" /><t:Column t:key=\"entry.coreBillType.name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"67\" t:styleID=\"sCol66\" /><t:Column t:key=\"entry.coreBillNumber\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"68\" t:styleID=\"sCol67\" /><t:Column t:key=\"entry.coreBillEntrySe\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"69\" t:styleID=\"sCol68\" /><t:Column t:key=\"issueAdminOrgUnit.name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"70\" t:styleID=\"sCol69\" /><t:Column t:key=\"issuePerson.name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"71\" t:styleID=\"sCol70\" /><t:Column t:key=\"reverseQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"72\" t:styleID=\"sCol71\" /><t:Column t:key=\"entry.associateQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"73\" t:styleID=\"sCol72\" /><t:Column t:key=\"returnsQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"74\" t:styleID=\"sCol73\" /><t:Column t:key=\"unReturnedBaseQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"75\" t:styleID=\"sCol74\" /><t:Column t:key=\"poID\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"76\" t:styleID=\"sCol75\" /><t:Column t:key=\"remark\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"77\" t:styleID=\"sCol76\" /><t:Column t:key=\"creator.name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"78\" t:styleID=\"sCol77\" /><t:Column t:key=\"creatorTime\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"79\" t:styleID=\"sCol78\" /><t:Column t:key=\"modifier.name\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"80\" t:styleID=\"sCol79\" /><t:Column t:key=\"modifyTime\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"81\" t:styleID=\"sCol80\" /><t:Column t:key=\"auditor\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"82\" t:styleID=\"sCol81\" /><t:Column t:key=\"auditorTime\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"83\" t:styleID=\"sCol82\" /><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"84\" t:styleID=\"sCol83\" /><t:Column t:key=\"entry.id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"85\" t:styleID=\"sCol84\" /><t:Column t:key=\"productLine\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"86\" t:styleID=\"sCol85\" /><t:Column t:key=\"classGroup\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"87\" t:styleID=\"sCol86\" /><t:Column t:key=\"person\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"88\" t:styleID=\"sCol87\" /><t:Column t:key=\"productLineWP\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"89\" t:styleID=\"sCol88\" /><t:Column t:key=\"saleOrderNum\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"91\" t:styleID=\"sCol89\" /><t:Column t:key=\"issueQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"92\" t:styleID=\"sCol90\" /><t:Column t:key=\"baseIssueQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"93\" t:styleID=\"sCol91\" /><t:Column t:key=\"pickingDate\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"94\" t:styleID=\"sCol92\" /><t:Column t:key=\"demandDate\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"95\" t:styleID=\"sCol93\" /><t:Column t:key=\"faCardQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"96\" t:styleID=\"sCol94\" /><t:Column t:key=\"isBackflush\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"97\" t:styleID=\"sCol95\" /><t:Column t:key=\"operationNo\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"98\" t:styleID=\"sCol96\" /><t:Column t:key=\"operationID\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"99\" t:styleID=\"sCol97\" /><t:Column t:key=\"operationNAME\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"100\" t:styleID=\"sCol98\" /><t:Column t:key=\"workCenterID\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"101\" t:styleID=\"sCol99\" /><t:Column t:key=\"workCenterNAME\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"102\" t:styleID=\"sCol100\" /><t:Column t:key=\"isAdmeasure\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"103\" t:styleID=\"sCol101\" /><t:Column t:key=\"isReWork\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"104\" t:styleID=\"sCol102\" /><t:Column t:key=\"description\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"105\" t:styleID=\"sCol103\" /><t:Column t:key=\"settlePrice\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"106\" t:styleID=\"sCol104\" /><t:Column t:key=\"entry.isAdjust\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"entry.adjustNum\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{number}</t:Cell><t:Cell>$Resource{baseStatus}</t:Cell><t:Cell>$Resource{bizType}</t:Cell><t:Cell>$Resource{purchaseType}</t:Cell><t:Cell>$Resource{supplier.name}</t:Cell><t:Cell>$Resource{transactionType.name}</t:Cell><t:Cell>$Resource{bizDate}</t:Cell><t:Cell>$Resource{storageOrgUnit.id}</t:Cell><t:Cell>$Resource{storageOrgUnit.name}</t:Cell><t:Cell>$Resource{supplyStoreOrgUnit.name}</t:Cell><t:Cell>$Resource{department.name}</t:Cell><t:Cell>$Resource{totalStandardCost}</t:Cell><t:Cell>$Resource{totalActualCost}</t:Cell><t:Cell>$Resource{isOfSet}</t:Cell><t:Cell>$Resource{isVoucher}</t:Cell><t:Cell>$Resource{voucher.number}</t:Cell><t:Cell>$Resource{entry.seq}</t:Cell><t:Cell>$Resource{productID.number}</t:Cell><t:Cell>$Resource{productID.name}</t:Cell><t:Cell>$Resource{mainAdminOrgUnit}</t:Cell><t:Cell>$Resource{costCenter.number}</t:Cell><t:Cell>$Resource{costOrgUnit.name}</t:Cell><t:Cell>$Resource{costObjectNumber}</t:Cell><t:Cell>$Resource{costObjectName}</t:Cell><t:Cell>$Resource{costItem.number}</t:Cell><t:Cell>$Resource{costItem.name}</t:Cell><t:Cell>$Resource{costObjectSuite.number}</t:Cell><t:Cell>$Resource{costObjectSuite.name}</t:Cell><t:Cell>$Resource{processMaterial.number}</t:Cell><t:Cell>$Resource{processMaterial.name}</t:Cell><t:Cell>$Resource{material.number}</t:Cell><t:Cell>$Resource{material.name}</t:Cell><t:Cell>$Resource{material.model}</t:Cell><t:Cell>$Resource{kdClassNumber}</t:Cell><t:Cell>$Resource{kdClassName}</t:Cell><t:Cell>$Resource{entry.assistantAttr}</t:Cell><t:Cell>$Resource{entry.batch}</t:Cell><t:Cell>$Resource{mfg}</t:Cell><t:Cell>$Resource{exp}</t:Cell><t:Cell>$Resource{unit.name}</t:Cell><t:Cell>$Resource{entry.qty}</t:Cell><t:Cell>$Resource{baseUnit}</t:Cell><t:Cell>$Resource{baseQty}</t:Cell><t:Cell>$Resource{assistUnit}</t:Cell><t:Cell>$Resource{assistQty}</t:Cell><t:Cell>$Resource{warehouse.name}</t:Cell><t:Cell>$Resource{projectNumCol}</t:Cell><t:Cell>$Resource{trackNumCol}</t:Cell><t:Cell>$Resource{stocker}</t:Cell><t:Cell>$Resource{picker}</t:Cell><t:Cell>$Resource{location.name}</t:Cell><t:Cell>$Resource{supplyWareHS}</t:Cell><t:Cell>$Resource{supplyLocation}</t:Cell><t:Cell>$Resource{customer}</t:Cell><t:Cell>$Resource{supplier}</t:Cell><t:Cell>$Resource{isPresent}</t:Cell><t:Cell>$Resource{unitStandardCost}</t:Cell><t:Cell>$Resource{standardCost}</t:Cell><t:Cell>$Resource{unitActualCost}</t:Cell><t:Cell>$Resource{actualCost}</t:Cell><t:Cell>$Resource{entry.subWrittenOffQty}</t:Cell><t:Cell>$Resource{entry.subWrittenOffBaseQty}</t:Cell><t:Cell>$Resource{entry.subUnWriteOffQty}</t:Cell><t:Cell>$Resource{entry.subUnWriteOffBaseQty}</t:Cell><t:Cell>$Resource{writtenOffAmount}</t:Cell><t:Cell>$Resource{unWriteOffAmount}</t:Cell><t:Cell>$Resource{entry.coreBillType.name}</t:Cell><t:Cell>$Resource{entry.coreBillNumber}</t:Cell><t:Cell>$Resource{entry.coreBillEntrySe}</t:Cell><t:Cell>$Resource{issueAdminOrgUnit.name}</t:Cell><t:Cell>$Resource{issuePerson.name}</t:Cell><t:Cell>$Resource{reverseQty}</t:Cell><t:Cell>$Resource{entry.associateQty}</t:Cell><t:Cell>$Resource{returnsQty}</t:Cell><t:Cell>$Resource{unReturnedBaseQty}</t:Cell><t:Cell>$Resource{poID}</t:Cell><t:Cell>$Resource{remark}</t:Cell><t:Cell>$Resource{creator.name}</t:Cell><t:Cell>$Resource{creatorTime}</t:Cell><t:Cell>$Resource{modifier.name}</t:Cell><t:Cell>$Resource{modifyTime}</t:Cell><t:Cell>$Resource{auditor}</t:Cell><t:Cell>$Resource{auditorTime}</t:Cell><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{entry.id}</t:Cell><t:Cell>$Resource{productLine}</t:Cell><t:Cell>$Resource{classGroup}</t:Cell><t:Cell>$Resource{person}</t:Cell><t:Cell>$Resource{productLineWP}</t:Cell><t:Cell>$Resource{saleOrderNum}</t:Cell><t:Cell>$Resource{issueQty}</t:Cell><t:Cell>$Resource{baseIssueQty}</t:Cell><t:Cell>$Resource{pickingDate}</t:Cell><t:Cell>$Resource{demandDate}</t:Cell><t:Cell>$Resource{faCardQty}</t:Cell><t:Cell>$Resource{isBackflush}</t:Cell><t:Cell>$Resource{operationNo}</t:Cell><t:Cell>$Resource{operationID}</t:Cell><t:Cell>$Resource{operationNAME}</t:Cell><t:Cell>$Resource{workCenterID}</t:Cell><t:Cell>$Resource{workCenterNAME}</t:Cell><t:Cell>$Resource{isAdmeasure}</t:Cell><t:Cell>$Resource{isReWork}</t:Cell><t:Cell>$Resource{description}</t:Cell><t:Cell>$Resource{settlePrice}</t:Cell><t:Cell>$Resource{entry.isAdjust}</t:Cell><t:Cell>$Resource{entry.adjustNum}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.tblMain.setFormatXml(resHelper.translateString("tblMain",tblMainStrXML));
                this.tblMain.putBindContents("mainQuery",new String[] {"number","baseStatus","bizType.name","purchaseType","supplier.name","transactionType.name","bizDate","storageOrgUnit.id","storageOrgUnit.name","supplyStoreOrgUnit.name","adminOrgUnit.name","totalStandardCost","totalActualCost","isReversed","fiVouchered","voucher.number","entry.seq","productID.number","productID.name","adminOrgUnit1.name","costCenterOrgUnit.number","costCenterOrgUnit.name","costObject.number","costObject.name","costItem.number","costItem.name","costObjectSuite.number","costObjectSuite.name","processMaterial.number","processMaterial.name","material.number","material.name","material.model","kAClass.number","kAClass.name","assistProperty.name","entry.lot","entry.mfg","entry.exp","unit.name","entry.qty","baseUnit.name","entry.baseQty","assistUnit.name","entry.assistQty","warehouse.name","project.number","trackNumber.number","stocker.name","picker.name","location.name","supplyWarehouse.name","supplyLocation.name","customer.name","supplier1.name","entry.isPresent","entry.unitStandardCost","entry.standardCost","entry.unitActualCost","entry.actualCost","entry.subWrittenOffQty","entry.subWrittenOffBaseQty","entry.subUnWriteOffQty","entry.subUnWriteOffBaseQty","entry.scWrittenOffAmount","entry.scUnWrittenOffAmount","entry.coreBillType.name","entry.coreBillNumber","entry.coreBillEntrySe","issueAdminOrgUnit.name","issuePerson.name","entry.reverseQty","entry.associateQty","entry.returnsQty","entry.unReturnedBaseQty","entry.orderNumber","entry.remark","creator.name","createTime","modifier.name","modificationTime","auditor.name","auditTime","id","entry.id","productLine.name","classGroup.name","person.name","productLineWP.name","entry.saleOrderNum","entry.issueQty","entry.baseIssueQty","entry.pickingDate","entry.demandDate","entry.faCardQty","isBackflush","entry.operationNo","operation.number","operation.name","workCenter.number","workCenter.name","entry.isAdmeasure","entry.isReWork","description","entry.settlePrice","entry.isAdjust","entry.adjustNum"});


        this.tblMain.checkParsed();
        this.tblMain.getGroupManager().setGroup(true);		
        this.btnLocate.setVisible(false);		
        this.separatorFW1.setEnabled(false);		
        this.separatorFW1.setVisible(false);		
        this.btnCopyTo.setVisible(false);		
        this.btnAuditResult.setVisible(true);		
        this.menuItemCopyTo.setVisible(false);		
        this.menuItemVoucher.setVisible(true);		
        this.menuItemVoucher.setEnabled(true);		
        this.menuItemDelVoucher.setEnabled(true);		
        this.menuItemDelVoucher.setVisible(true);		
        this.menuItemMultiapprove.setEnabled(false);		
        this.menuItemMultiapprove.setVisible(false);		
        this.kDSeparator7.setEnabled(false);		
        this.kDSeparator7.setVisible(false);		
        this.menuItemAudit.setToolTipText(resHelper.getString("menuItemAudit.toolTipText"));		
        this.menuItemUnAudit.setToolTipText(resHelper.getString("menuItemUnAudit.toolTipText"));		
        this.separator7.setVisible(false);		
        this.separator7.setEnabled(false);		
        this.menuItemMultiPrint.setVisible(true);		
        this.menuItemMultiPrintPreview.setVisible(true);		
        this.btnMultiPrint.setVisible(true);		
        this.btnMultiPrintPreview.setVisible(true);
        // separator10		
        this.separator10.setOrientation(1);		
        this.separator10.setEnabled(false);		
        this.separator10.setVisible(false);
        // kDelObj
        this.kDelObj.setAction((IItemAction)ActionProxyFactory.getProxy(kDelObj1, new Class[] { IItemAction.class }, getServiceContext()));		
        this.kDelObj.setText(resHelper.getString("kDelObj.text"));		
        this.kDelObj.setToolTipText(resHelper.getString("kDelObj.toolTipText"));		
        this.kDelObj.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_distributedeletcalog"));
        this.kDelObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    kDelObj_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // RelObj
        this.RelObj.setAction((IItemAction)ActionProxyFactory.getProxy(RelObj1, new Class[] { IItemAction.class }, getServiceContext()));		
        this.RelObj.setText(resHelper.getString("RelObj.text"));		
        this.RelObj.setToolTipText(resHelper.getString("RelObj.toolTipText"));		
        this.RelObj.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("imgTbtn_choosein"));
        this.RelObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    RelObj_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // kDSeparator8
        // menuItemGetMaterial
        this.menuItemGetMaterial.setAction((IItemAction)ActionProxyFactory.getProxy(actionGetMaterial, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemGetMaterial.setText(resHelper.getString("menuItemGetMaterial.text"));
        // menuItemCancelMaterial
        this.menuItemCancelMaterial.setAction((IItemAction)ActionProxyFactory.getProxy(actionCancelMaterial, new Class[] { IItemAction.class }, getServiceContext()));		
        this.menuItemCancelMaterial.setText(resHelper.getString("menuItemCancelMaterial.text"));
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
        tblMain.setBounds(new Rectangle(10, 10, 993, 609));
        this.add(tblMain, new KDLayout.Constraints(10, 10, 993, 609, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));

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
        this.menuBar.add(kdMenuVoucher);
        this.menuBar.add(menuTools);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemImportData);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(menuItemExportData);
        menuFile.add(menuItemCloudShare);
        menuFile.add(separatorFile1);
        menuFile.add(kdSeparatorFWFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(separator4);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(separator5);
        menuFile.add(menuItemMultiPrint);
        menuFile.add(menuItemMultiPrintPreview);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemExitCurrent);
        //menuEdit
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(separator6);
        menuEdit.add(kDSeparator3);
        menuEdit.add(kDSeparator4);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyTo);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemView);
        menuView.add(menuItemLocate);
        menuView.add(separatorView1);
        menuView.add(menuItemQuery);
        menuView.add(menuItemRefresh);
        menuView.add(menuItemSwitchView);
        menuView.add(separator8);
        menuView.add(kDSeparator5);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(menuItemQueryScheme);
        menuView.add(separator7);
        menuView.add(kDSeparator6);
        menuView.add(kDMenuPercisionConfig);
        menuView.add(kDSeparator7);
        //kDMenuPercisionConfig
        kDMenuPercisionConfig.add(menuItemPercisionConfig);
        kDMenuPercisionConfig.add(boxMenuItemNoZero);
        kDMenuPercisionConfig.add(boxIsShowSumRow);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(menuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        menuBiz.add(kDSeparator8);
        menuBiz.add(menuItemGetMaterial);
        menuBiz.add(menuItemCancelMaterial);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuWorkFlow
        menuWorkFlow.add(menuItemMultiapprove);
        menuWorkFlow.add(menuItemWorkFlowG);
        menuWorkFlow.add(menuItemNextPerson);
        menuWorkFlow.add(separatorWF1);
        menuWorkFlow.add(menuItemWorkFlowList);
        menuWorkFlow.add(menuItemAudit);
        menuWorkFlow.add(menuItemUnAudit);
        menuWorkFlow.add(menuItemAuditResult);
        menuWorkFlow.add(menuItemViewDoProccess);
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
        menuHelp.add(separator9);
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
        this.toolBar.add(btnView);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnEdit);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnRefresh);
        this.toolBar.add(btnQuery);
        this.toolBar.add(btnLocate);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separator3);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(btnMultiPrint);
        this.toolBar.add(btnMultiPrintPreview);
        this.toolBar.add(separator1);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnQueryScheme);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnNextPerson);
        this.toolBar.add(separator10);
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnWorkFlowList);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(btnAudit);
        this.toolBar.add(btnUnAudit);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separator2);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnCopyTo);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(kDelObj);
        this.toolBar.add(RelObj);


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.scm.im.inv.app.MaterialReqBillListUIHandler";
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
			return com.kingdee.eas.basedata.org.OrgType.getEnum("Storage");
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
     * output kDelObj_actionPerformed method
     */
    protected void kDelObj_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output RelObj_actionPerformed method
     */
    protected void RelObj_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
        //write your code here
    }

	public SelectorItemCollection getBOTPSelectors() {
			SelectorItemCollection sic = new SelectorItemCollection();
			return sic;
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
        sic.add(new SelectorItemInfo("transactionType.name"));
        sic.add(new SelectorItemInfo("bizDate"));
        sic.add(new SelectorItemInfo("storageOrgUnit.name"));
        sic.add(new SelectorItemInfo("baseStatus"));
        sic.add(new SelectorItemInfo("productID.number"));
        sic.add(new SelectorItemInfo("productID.name"));
        sic.add(new SelectorItemInfo("entry.id"));
        sic.add(new SelectorItemInfo("entry.seq"));
        sic.add(new SelectorItemInfo("material.number"));
        sic.add(new SelectorItemInfo("material.name"));
        sic.add(new SelectorItemInfo("unit.name"));
        sic.add(new SelectorItemInfo("entry.qty"));
        sic.add(new SelectorItemInfo("warehouse.name"));
        sic.add(new SelectorItemInfo("location.name"));
        sic.add(new SelectorItemInfo("material.model"));
        sic.add(new SelectorItemInfo("adminOrgUnit.name"));
        sic.add(new SelectorItemInfo("assistProperty.name"));
        sic.add(new SelectorItemInfo("entry.lot"));
        sic.add(new SelectorItemInfo("isReversed"));
        sic.add(new SelectorItemInfo("entry.exp"));
        sic.add(new SelectorItemInfo("baseUnit.name"));
        sic.add(new SelectorItemInfo("entry.baseQty"));
        sic.add(new SelectorItemInfo("assistUnit.name"));
        sic.add(new SelectorItemInfo("entry.assistQty"));
        sic.add(new SelectorItemInfo("entry.unitStandardCost"));
        sic.add(new SelectorItemInfo("entry.standardCost"));
        sic.add(new SelectorItemInfo("entry.actualCost"));
        sic.add(new SelectorItemInfo("entry.reverseQty"));
        sic.add(new SelectorItemInfo("entry.orderNumber"));
        sic.add(new SelectorItemInfo("entry.remark"));
        sic.add(new SelectorItemInfo("stocker.name"));
        sic.add(new SelectorItemInfo("entry.mfg"));
        sic.add(new SelectorItemInfo("fiVouchered"));
        sic.add(new SelectorItemInfo("voucher.number"));
        sic.add(new SelectorItemInfo("creator.name"));
        sic.add(new SelectorItemInfo("createTime"));
        sic.add(new SelectorItemInfo("modifier.name"));
        sic.add(new SelectorItemInfo("modificationTime"));
        sic.add(new SelectorItemInfo("auditor.name"));
        sic.add(new SelectorItemInfo("auditTime"));
        sic.add(new SelectorItemInfo("totalStandardCost"));
        sic.add(new SelectorItemInfo("totalActualCost"));
        sic.add(new SelectorItemInfo("costObject.number"));
        sic.add(new SelectorItemInfo("costObject.name"));
        sic.add(new SelectorItemInfo("entry.returnsQty"));
        sic.add(new SelectorItemInfo("entry.unReturnedBaseQty"));
        sic.add(new SelectorItemInfo("kAClass.number"));
        sic.add(new SelectorItemInfo("kAClass.name"));
        sic.add(new SelectorItemInfo("storageOrgUnit.id"));
        sic.add(new SelectorItemInfo("costItem.number"));
        sic.add(new SelectorItemInfo("costItem.name"));
        sic.add(new SelectorItemInfo("costObjectSuite.number"));
        sic.add(new SelectorItemInfo("costObjectSuite.name"));
        sic.add(new SelectorItemInfo("classGroup.name"));
        sic.add(new SelectorItemInfo("person.name"));
        sic.add(new SelectorItemInfo("productLineWP.name"));
        sic.add(new SelectorItemInfo("entry.saleOrderNum"));
        sic.add(new SelectorItemInfo("entry.issueQty"));
        sic.add(new SelectorItemInfo("entry.baseIssueQty"));
        sic.add(new SelectorItemInfo("entry.pickingDate"));
        sic.add(new SelectorItemInfo("entry.demandDate"));
        sic.add(new SelectorItemInfo("productLine.name"));
        sic.add(new SelectorItemInfo("entry.faCardQty"));
        sic.add(new SelectorItemInfo("bizType.name"));
        sic.add(new SelectorItemInfo("entry.coreBillNumber"));
        sic.add(new SelectorItemInfo("entry.coreBillType.name"));
        sic.add(new SelectorItemInfo("entry.coreBillEntrySe"));
        sic.add(new SelectorItemInfo("entry.subWrittenOffQty"));
        sic.add(new SelectorItemInfo("entry.subWrittenOffBaseQty"));
        sic.add(new SelectorItemInfo("entry.subUnWriteOffQty"));
        sic.add(new SelectorItemInfo("entry.subUnWriteOffBaseQty"));
        sic.add(new SelectorItemInfo("supplier.name"));
        sic.add(new SelectorItemInfo("issueAdminOrgUnit.name"));
        sic.add(new SelectorItemInfo("issuePerson.name"));
        sic.add(new SelectorItemInfo("processMaterial.number"));
        sic.add(new SelectorItemInfo("processMaterial.name"));
        sic.add(new SelectorItemInfo("isBackflush"));
        sic.add(new SelectorItemInfo("entry.operationNo"));
        sic.add(new SelectorItemInfo("operation.number"));
        sic.add(new SelectorItemInfo("operation.name"));
        sic.add(new SelectorItemInfo("workCenter.number"));
        sic.add(new SelectorItemInfo("workCenter.name"));
        sic.add(new SelectorItemInfo("entry.isAdmeasure"));
        sic.add(new SelectorItemInfo("entry.isReWork"));
        sic.add(new SelectorItemInfo("entry.associateQty"));
        sic.add(new SelectorItemInfo("project.number"));
        sic.add(new SelectorItemInfo("trackNumber.number"));
        sic.add(new SelectorItemInfo("entry.unitActualCost"));
        sic.add(new SelectorItemInfo("entry.scWrittenOffAmount"));
        sic.add(new SelectorItemInfo("entry.scUnWrittenOffAmount"));
        sic.add(new SelectorItemInfo("customer.name"));
        sic.add(new SelectorItemInfo("supplier1.name"));
        sic.add(new SelectorItemInfo("entry.isPresent"));
        sic.add(new SelectorItemInfo("description"));
        sic.add(new SelectorItemInfo("supplyWarehouse.name"));
        sic.add(new SelectorItemInfo("supplyLocation.name"));
        sic.add(new SelectorItemInfo("entry.settlePrice"));
        sic.add(new SelectorItemInfo("picker.name"));
        sic.add(new SelectorItemInfo("supplyStoreOrgUnit.name"));
        sic.add(new SelectorItemInfo("purchaseType"));
        sic.add(new SelectorItemInfo("entry.amount"));
        sic.add(new SelectorItemInfo("adminOrgUnit1.name"));
        sic.add(new SelectorItemInfo("costCenterOrgUnit.number"));
        sic.add(new SelectorItemInfo("costCenterOrgUnit.name"));
        sic.add(new SelectorItemInfo("entry.isAdjust"));
        sic.add(new SelectorItemInfo("entry.adjustNum"));
        return sic;
    }            protected java.util.List getQuerySorterFields() 
    { 
        java.util.List sorterFieldList = new ArrayList(); 
        return sorterFieldList; 
    } 
    protected java.util.List getQueryPKFields() 
    { 
        java.util.List pkList = new ArrayList(); 
        pkList.add("id"); 
        pkList.add("entry.id"); 
        return pkList;
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
     * output actionGetMaterial_actionPerformed method
     */
    public void actionGetMaterial_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCancelMaterial_actionPerformed method
     */
    public void actionCancelMaterial_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output kDelObj1_actionPerformed method
     */
    public void kDelObj1_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output relObj1_actionPerformed method
     */
    public void relObj1_actionPerformed(ActionEvent e) throws Exception
    {
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
	public RequestContext prepareActionGetMaterial(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionGetMaterial() {
    	return false;
    }
	public RequestContext prepareActionCancelMaterial(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCancelMaterial() {
    	return false;
    }
	public RequestContext preparekDelObj1(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPreparekDelObj1() {
    	return false;
    }
	public RequestContext prepareRelObj1(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareRelObj1() {
    	return false;
    }

    /**
     * output ActionGetMaterial class
     */     
    protected class ActionGetMaterial extends ItemAction {     
    
        public ActionGetMaterial()
        {
            this(null);
        }

        public ActionGetMaterial(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt K"));
            _tempStr = resHelper.getString("ActionGetMaterial.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionGetMaterial.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionGetMaterial.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialReqBillListUI.this, "ActionGetMaterial", "actionGetMaterial_actionPerformed", e);
        }
    }

    /**
     * output ActionCancelMaterial class
     */     
    protected class ActionCancelMaterial extends ItemAction {     
    
        public ActionCancelMaterial()
        {
            this(null);
        }

        public ActionCancelMaterial(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt B"));
            _tempStr = resHelper.getString("ActionCancelMaterial.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCancelMaterial.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCancelMaterial.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialReqBillListUI.this, "ActionCancelMaterial", "actionCancelMaterial_actionPerformed", e);
        }
    }

    /**
     * output kDelObj1 class
     */     
    protected class kDelObj1 extends ItemAction {     
    
        public kDelObj1()
        {
            this(null);
        }

        public kDelObj1(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("kDelObj1.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("kDelObj1.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("kDelObj1.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialReqBillListUI.this, "kDelObj1", "kDelObj1_actionPerformed", e);
        }
    }

    /**
     * output RelObj1 class
     */     
    protected class RelObj1 extends ItemAction {     
    
        public RelObj1()
        {
            this(null);
        }

        public RelObj1(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("RelObj1.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("RelObj1.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("RelObj1.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMaterialReqBillListUI.this, "RelObj1", "relObj1_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.scm.im.inv.client", "MaterialReqBillListUI");
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
        return objectValue;
    }




}