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
public abstract class AbstractInventoryListUI extends com.kingdee.eas.framework.client.ListUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractInventoryListUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer storageOrgLabelContainer;
    protected com.kingdee.bos.ctrl.swing.KDSplitPane kDSplitPane1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer materailLabelContainer;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnQuickQuery;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAdjustInwarehs;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAdjustOutwarehs;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtstorageOrg;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kDTabbedPane1;
    protected com.kingdee.bos.ctrl.swing.KDTreeView storageTreeView;
    protected com.kingdee.bos.ctrl.swing.KDTreeView materialTreeView;
    protected com.kingdee.bos.ctrl.swing.KDTree storageTree;
    protected com.kingdee.bos.ctrl.swing.KDTree materialTree;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtMaterial;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton refreshByMaterial;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton refreshByStorageOrg;
    protected ActionRefreshByMat actionRefreshByMat = null;
    protected ActionRefreshByOrg actionRefreshByOrg = null;
    protected ActionShowRefreshInv actionShowRefreshInv = null;
    protected actionAdjustInwarehs actionAdjustInwarehs = null;
    protected actionAdjustOutwarehs actionAdjustOutwarehs = null;
    /**
     * output class constructor
     */
    public AbstractInventoryListUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractInventoryListUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        mainQueryPK = new MetaDataPK("com.kingdee.eas.scm.im.inv", "InventoryQuery");
        //actionRefreshByMat
        this.actionRefreshByMat = new ActionRefreshByMat(this);
        getActionManager().registerAction("actionRefreshByMat", actionRefreshByMat);
         this.actionRefreshByMat.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionRefreshByOrg
        this.actionRefreshByOrg = new ActionRefreshByOrg(this);
        getActionManager().registerAction("actionRefreshByOrg", actionRefreshByOrg);
         this.actionRefreshByOrg.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionShowRefreshInv
        this.actionShowRefreshInv = new ActionShowRefreshInv(this);
        getActionManager().registerAction("actionShowRefreshInv", actionShowRefreshInv);
         this.actionShowRefreshInv.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAdjustInwarehs
        this.actionAdjustInwarehs = new actionAdjustInwarehs(this);
        getActionManager().registerAction("actionAdjustInwarehs", actionAdjustInwarehs);
         this.actionAdjustInwarehs.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionAdjustOutwarehs
        this.actionAdjustOutwarehs = new actionAdjustOutwarehs(this);
        getActionManager().registerAction("actionAdjustOutwarehs", actionAdjustOutwarehs);
         this.actionAdjustOutwarehs.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.storageOrgLabelContainer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDSplitPane1 = new com.kingdee.bos.ctrl.swing.KDSplitPane();
        this.materailLabelContainer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnQuickQuery = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAdjustInwarehs = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnAdjustOutwarehs = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.prmtstorageOrg = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDTabbedPane1 = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.storageTreeView = new com.kingdee.bos.ctrl.swing.KDTreeView();
        this.materialTreeView = new com.kingdee.bos.ctrl.swing.KDTreeView();
        this.storageTree = new com.kingdee.bos.ctrl.swing.KDTree();
        this.materialTree = new com.kingdee.bos.ctrl.swing.KDTree();
        this.prmtMaterial = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.refreshByMaterial = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.refreshByStorageOrg = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.storageOrgLabelContainer.setName("storageOrgLabelContainer");
        this.kDSplitPane1.setName("kDSplitPane1");
        this.materailLabelContainer.setName("materailLabelContainer");
        this.btnQuickQuery.setName("btnQuickQuery");
        this.btnAdjustInwarehs.setName("btnAdjustInwarehs");
        this.btnAdjustOutwarehs.setName("btnAdjustOutwarehs");
        this.prmtstorageOrg.setName("prmtstorageOrg");
        this.kDTabbedPane1.setName("kDTabbedPane1");
        this.storageTreeView.setName("storageTreeView");
        this.materialTreeView.setName("materialTreeView");
        this.storageTree.setName("storageTree");
        this.materialTree.setName("materialTree");
        this.prmtMaterial.setName("prmtMaterial");
        this.refreshByMaterial.setName("refreshByMaterial");
        this.refreshByStorageOrg.setName("refreshByStorageOrg");
        // CoreUI		
        this.setPreferredSize(new Dimension(792,510));
		String tblMainStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol12\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol13\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol14\"><c:Protection hidden=\"true\" /><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol16\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol18\"><c:Alignment horizontal=\"right\" /></c:Style><c:Style id=\"sCol29\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol30\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol31\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol32\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol33\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol34\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol35\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol36\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol37\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol38\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol40\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol41\"><c:Protection hidden=\"true\" /></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\" t:configured=\"true\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:styleID=\"sCol0\" /><t:Column t:key=\"selected\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"material.number\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /><t:Column t:key=\"material.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" /><t:Column t:key=\"material.model\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" /><t:Column t:key=\"assistProperty.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" /><t:Column t:key=\"lot\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" /><t:Column t:key=\"mfg\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" /><t:Column t:key=\"exp\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" /><t:Column t:key=\"projectNumCol\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" /><t:Column t:key=\"trackNumCol\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" /><t:Column t:key=\"unit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" /><t:Column t:key=\"curStoreQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" t:styleID=\"sCol12\" /><t:Column t:key=\"matStorageUnit\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" t:styleID=\"sCol13\" /><t:Column t:key=\"curMatStoreQty\" t:width=\"100\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" t:styleID=\"sCol14\" /><t:Column t:key=\"baseUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" /><t:Column t:key=\"baseQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" t:styleID=\"sCol16\" /><t:Column t:key=\"assistUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"17\" /><t:Column t:key=\"curStoreAssistQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" t:styleID=\"sCol18\" /><t:Column t:key=\"lockQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"19\" /><t:Column t:key=\"lockBaseQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"20\" /><t:Column t:key=\"lockAssistQty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"21\" /><t:Column t:key=\"supplier\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"22\" /><t:Column t:key=\"storageOrgUnit.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"23\" /><t:Column t:key=\"storetype.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"24\" /><t:Column t:key=\"storestatus.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"25\" /><t:Column t:key=\"warehouse.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"26\" /><t:Column t:key=\"location.name\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"27\" /><t:Column t:key=\"customer\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"28\" /><t:Column t:key=\"storageOrgUnit.number\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"29\" t:styleID=\"sCol29\" /><t:Column t:key=\"storageOrgUnit.id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol30\" /><t:Column t:key=\"multiUnit.qtyPrecision\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol31\" /><t:Column t:key=\"multiBaseUnit.qtyPrecision\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol32\" /><t:Column t:key=\"storetype.id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol33\" /><t:Column t:key=\"multiUnit.baseConvsRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol34\" /><t:Column t:key=\"multiAssitUnit.qtyPrecision\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol35\" /><t:Column t:key=\"multiBaseUnit.baseConvsRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol36\" /><t:Column t:key=\"salePrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"37\" t:configured=\"true\" t:styleID=\"sCol37\" /><t:Column t:key=\"saleTaxPrice\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"38\" t:configured=\"true\" t:styleID=\"sCol38\" /><t:Column t:key=\"locationNo\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"39\" t:configured=\"true\" /><t:Column t:key=\"warehouse.id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"40\" t:configured=\"true\" t:styleID=\"sCol40\" /><t:Column t:key=\"material.id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"41\" t:configured=\"true\" t:styleID=\"sCol41\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{selected}</t:Cell><t:Cell>$Resource{material.number}</t:Cell><t:Cell>$Resource{material.name}</t:Cell><t:Cell>$Resource{material.model}</t:Cell><t:Cell>$Resource{assistProperty.name}</t:Cell><t:Cell>$Resource{lot}</t:Cell><t:Cell>$Resource{mfg}</t:Cell><t:Cell>$Resource{exp}</t:Cell><t:Cell>$Resource{projectNumCol}</t:Cell><t:Cell>$Resource{trackNumCol}</t:Cell><t:Cell>$Resource{unit}</t:Cell><t:Cell>$Resource{curStoreQty}</t:Cell><t:Cell>$Resource{matStorageUnit}</t:Cell><t:Cell>$Resource{curMatStoreQty}</t:Cell><t:Cell>$Resource{baseUnit}</t:Cell><t:Cell>$Resource{baseQty}</t:Cell><t:Cell>$Resource{assistUnit}</t:Cell><t:Cell>$Resource{curStoreAssistQty}</t:Cell><t:Cell>$Resource{lockQty}</t:Cell><t:Cell>$Resource{lockBaseQty}</t:Cell><t:Cell>$Resource{lockAssistQty}</t:Cell><t:Cell>$Resource{supplier}</t:Cell><t:Cell>$Resource{storageOrgUnit.name}</t:Cell><t:Cell>$Resource{storetype.name}</t:Cell><t:Cell>$Resource{storestatus.name}</t:Cell><t:Cell>$Resource{warehouse.name}</t:Cell><t:Cell>$Resource{location.name}</t:Cell><t:Cell>$Resource{customer}</t:Cell><t:Cell>$Resource{storageOrgUnit.number}</t:Cell><t:Cell>$Resource{storageOrgUnit.id}</t:Cell><t:Cell>$Resource{multiUnit.qtyPrecision}</t:Cell><t:Cell>$Resource{multiBaseUnit.qtyPrecision}</t:Cell><t:Cell>$Resource{storetype.id}</t:Cell><t:Cell>$Resource{multiUnit.baseConvsRate}</t:Cell><t:Cell>$Resource{multiAssitUnit.qtyPrecision}</t:Cell><t:Cell>$Resource{multiBaseUnit.baseConvsRate}</t:Cell><t:Cell>$Resource{salePrice}</t:Cell><t:Cell>$Resource{saleTaxPrice}</t:Cell><t:Cell>$Resource{locationNo}</t:Cell><t:Cell>$Resource{warehouse.id}</t:Cell><t:Cell>$Resource{material.id}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.tblMain.setFormatXml(resHelper.translateString("tblMain",tblMainStrXML));
                this.tblMain.putBindContents("mainQuery",new String[] {"id","","material.number","material.name","material.model","assistProperty.name","lot","DateOfMinDurability.mfg","DateOfMinDurability.exp","project.number","trackNumber.number","unit.name","curStoreQty","inventoryUnit.name","curMatStoreQty","baseUnit.name","baseQty","assistUnit.name","curStoreAssistQty","lockQty","lockBaseQty","lockAssistQty","supplier.name","storageOrgUnit.name","storetype.name","storestatus.name","warehouse.name","location.name","customer.name","storageOrgUnit.number","storageOrgUnit.id","multiUnit.qtyPrecision","multiBaseUnit.qtyPrecision","storetype.id","multiUnit.baseConvsRate","multiAssitUnit.qtyPrecision","multiBaseUnit.baseConvsRate","","","","warehouse.id","material.id"});

		
        this.btnAddNew.setVisible(false);		
        this.btnAddNew.setEnabled(false);		
        this.btnView.setVisible(false);		
        this.btnView.setEnabled(false);		
        this.btnEdit.setEnabled(false);		
        this.btnEdit.setVisible(false);		
        this.btnRemove.setEnabled(false);		
        this.btnRemove.setVisible(false);		
        this.btnLocate.setEnabled(false);		
        this.btnLocate.setVisible(false);		
        this.menuItemAddNew.setVisible(false);		
        this.menuItemAddNew.setEnabled(false);		
        this.menuItemImportData.setVisible(false);		
        this.menuItemImportData.setEnabled(false);		
        this.menuEdit.setEnabled(false);		
        this.menuEdit.setVisible(false);		
        this.menuItemEdit.setEnabled(false);		
        this.menuItemEdit.setVisible(false);		
        this.menuItemRemove.setEnabled(false);		
        this.menuItemRemove.setVisible(false);		
        this.menuView.setEnabled(false);		
        this.menuView.setVisible(false);
        // storageOrgLabelContainer		
        this.storageOrgLabelContainer.setBoundLabelText(resHelper.getString("storageOrgLabelContainer.boundLabelText"));		
        this.storageOrgLabelContainer.setBoundLabelUnderline(true);		
        this.storageOrgLabelContainer.setBoundLabelLength(80);
        // kDSplitPane1		
        this.kDSplitPane1.setResizeWeight(0);		
        this.kDSplitPane1.setDividerSize(6);		
        this.kDSplitPane1.setDividerLocation(170);		
        this.kDSplitPane1.setLastDividerLocation(200);		
        this.kDSplitPane1.setBackground(new java.awt.Color(236,236,232));
        // materailLabelContainer		
        this.materailLabelContainer.setBoundLabelText(resHelper.getString("materailLabelContainer.boundLabelText"));		
        this.materailLabelContainer.setBoundLabelLength(80);		
        this.materailLabelContainer.setBoundLabelUnderline(true);
        // btnQuickQuery		
        this.btnQuickQuery.setText(resHelper.getString("btnQuickQuery.text"));
        this.btnQuickQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnQuickQuery_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // btnAdjustInwarehs
        this.btnAdjustInwarehs.setAction((IItemAction)ActionProxyFactory.getProxy(actionAdjustInwarehs, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAdjustInwarehs.setText(resHelper.getString("btnAdjustInwarehs.text"));		
        this.btnAdjustInwarehs.setVisible(false);
        // btnAdjustOutwarehs
        this.btnAdjustOutwarehs.setAction((IItemAction)ActionProxyFactory.getProxy(actionAdjustOutwarehs, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAdjustOutwarehs.setText(resHelper.getString("btnAdjustOutwarehs.text"));		
        this.btnAdjustOutwarehs.setVisible(false);
        // prmtstorageOrg		
        this.prmtstorageOrg.setDisplayFormat("$name$");		
        this.prmtstorageOrg.setEditFormat("$number$");		
        this.prmtstorageOrg.setCommitFormat("$number$");		
        this.prmtstorageOrg.setQueryInfo("com.kingdee.eas.basedata.org.app.StorageOrgUnitQuery");		
        this.prmtstorageOrg.setEditable(true);		
        this.prmtstorageOrg.setLabelVisible(true);
        this.prmtstorageOrg.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    prmtstorageOrg_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // kDTabbedPane1
        this.kDTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                try {
                    kDTabbedPane1_stateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // storageTreeView		
        this.storageTreeView.setTitle(resHelper.getString("storageTreeView.title"));		
        this.storageTreeView.setShowButton(false);
        // materialTreeView		
        this.materialTreeView.setTitle(resHelper.getString("materialTreeView.title"));		
        this.materialTreeView.setShowButton(false);
        // storageTree
        this.storageTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
                try {
                    storageTree_valueChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.storageTree.addTreeWillExpandListener(new com.kingdee.bos.ctrl.swing.event.TreeWillExpandAdapter() {
            public void treeWillExpand(javax.swing.event.TreeExpansionEvent e) {
                try {
                    storageTree_treeWillExpand(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        this.storageTree.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                try {
                    storageTree_focusGained(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        // materialTree
        this.materialTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
                try {
                    materialTree_valueChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.materialTree.addTreeWillExpandListener(new com.kingdee.bos.ctrl.swing.event.TreeWillExpandAdapter() {
            public void treeWillExpand(javax.swing.event.TreeExpansionEvent e) {
                try {
                    materialTree_treeWillExpand(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        this.materialTree.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                try {
                    materialTree_focusGained(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        // prmtMaterial		
        this.prmtMaterial.setLabelVisible(true);		
        this.prmtMaterial.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7MaterialQuery");		
        this.prmtMaterial.setDisplayFormat("$name$");		
        this.prmtMaterial.setEditFormat("$number$");		
        this.prmtMaterial.setCommitFormat("$number$");		
        this.prmtMaterial.setLabelLength(150);		
        this.prmtMaterial.setEditable(true);
        // refreshByMaterial
        this.refreshByMaterial.setAction((IItemAction)ActionProxyFactory.getProxy(actionRefreshByMat, new Class[] { IItemAction.class }, getServiceContext()));		
        this.refreshByMaterial.setText(resHelper.getString("refreshByMaterial.text"));
        // refreshByStorageOrg
        this.refreshByStorageOrg.setAction((IItemAction)ActionProxyFactory.getProxy(actionRefreshByOrg, new Class[] { IItemAction.class }, getServiceContext()));		
        this.refreshByStorageOrg.setText(resHelper.getString("refreshByStorageOrg.text"));
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
        this.setBounds(new Rectangle(10, 10, 792, 510));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(10, 10, 792, 510));
        storageOrgLabelContainer.setBounds(new Rectangle(11, 10, 300, 19));
        this.add(storageOrgLabelContainer, new KDLayout.Constraints(11, 10, 300, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT));
        kDSplitPane1.setBounds(new Rectangle(10, 35, 772, 468));
        this.add(kDSplitPane1, new KDLayout.Constraints(10, 35, 772, 468, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        materailLabelContainer.setBounds(new Rectangle(349, 10, 294, 19));
        this.add(materailLabelContainer, new KDLayout.Constraints(349, 10, 294, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT));
        btnQuickQuery.setBounds(new Rectangle(676, 10, 102, 19));
        this.add(btnQuickQuery, new KDLayout.Constraints(676, 10, 102, 19, 0));
        btnAdjustInwarehs.setBounds(new Rectangle(794, 9, 95, 19));
        this.add(btnAdjustInwarehs, new KDLayout.Constraints(794, 9, 95, 19, 0));
        btnAdjustOutwarehs.setBounds(new Rectangle(900, 9, 95, 19));
        this.add(btnAdjustOutwarehs, new KDLayout.Constraints(900, 9, 95, 19, 0));
        //storageOrgLabelContainer
        storageOrgLabelContainer.setBoundEditor(prmtstorageOrg);
        //kDSplitPane1
        kDSplitPane1.add(tblMain, "right");
        kDSplitPane1.add(kDTabbedPane1, "left");
        //kDTabbedPane1
        kDTabbedPane1.add(storageTreeView, resHelper.getString("storageTreeView.constraints"));
        kDTabbedPane1.add(materialTreeView, resHelper.getString("materialTreeView.constraints"));
        //storageTreeView
        storageTreeView.setTree(storageTree);
        //materialTreeView
        materialTreeView.setTree(materialTree);
        //materailLabelContainer
        materailLabelContainer.setBoundEditor(prmtMaterial);

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
        menuView.add(menuItemQueryScheme);
        menuView.add(menuItemRefresh);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
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
        this.toolBar.add(btnRefresh);
        this.toolBar.add(btnQuery);
        this.toolBar.add(btnLocate);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(refreshByMaterial);
        this.toolBar.add(refreshByStorageOrg);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(btnQueryScheme);


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.scm.im.inv.app.InventoryListUIHandler";
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
     * output btnQuickQuery_actionPerformed method
     */
    protected void btnQuickQuery_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output prmtstorageOrg_dataChanged method
     */
    protected void prmtstorageOrg_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output kDTabbedPane1_stateChanged method
     */
    protected void kDTabbedPane1_stateChanged(javax.swing.event.ChangeEvent e) throws Exception
    {
    }

    /**
     * output storageTree_valueChanged method
     */
    protected void storageTree_valueChanged(javax.swing.event.TreeSelectionEvent e) throws Exception
    {
    }

    /**
     * output storageTree_treeWillExpand method
     */
    protected void storageTree_treeWillExpand(javax.swing.event.TreeExpansionEvent e) throws Exception
    {
    }

    /**
     * output storageTree_focusGained method
     */
    protected void storageTree_focusGained(java.awt.event.FocusEvent e) throws Exception
    {
    }

    /**
     * output materialTree_valueChanged method
     */
    protected void materialTree_valueChanged(javax.swing.event.TreeSelectionEvent e) throws Exception
    {
    }

    /**
     * output materialTree_treeWillExpand method
     */
    protected void materialTree_treeWillExpand(javax.swing.event.TreeExpansionEvent e) throws Exception
    {
    }

    /**
     * output materialTree_focusGained method
     */
    protected void materialTree_focusGained(java.awt.event.FocusEvent e) throws Exception
    {
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
        sic.add(new SelectorItemInfo("storestatus.name"));
        sic.add(new SelectorItemInfo("storetype.name"));
        sic.add(new SelectorItemInfo("warehouse.name"));
        sic.add(new SelectorItemInfo("material.name"));
        sic.add(new SelectorItemInfo("material.number"));
        sic.add(new SelectorItemInfo("id"));
        sic.add(new SelectorItemInfo("curStoreQty"));
        sic.add(new SelectorItemInfo("unit.name"));
        sic.add(new SelectorItemInfo("location.name"));
        sic.add(new SelectorItemInfo("lot"));
        sic.add(new SelectorItemInfo("storageOrgUnit.name"));
        sic.add(new SelectorItemInfo("assistUnit.name"));
        sic.add(new SelectorItemInfo("curStoreAssistQty"));
        sic.add(new SelectorItemInfo("baseUnit.name"));
        sic.add(new SelectorItemInfo("baseQty"));
        sic.add(new SelectorItemInfo("assistProperty.name"));
        sic.add(new SelectorItemInfo("material.model"));
        sic.add(new SelectorItemInfo("customer.name"));
        sic.add(new SelectorItemInfo("supplier.name"));
        sic.add(new SelectorItemInfo("lockQty"));
        sic.add(new SelectorItemInfo("lockBaseQty"));
        sic.add(new SelectorItemInfo("lockAssistQty"));
        sic.add(new SelectorItemInfo("storageOrgUnit.number"));
        sic.add(new SelectorItemInfo("project.number"));
        sic.add(new SelectorItemInfo("trackNumber.number"));
        sic.add(new SelectorItemInfo("inventoryUnit.name"));
        sic.add(new SelectorItemInfo("curMatStoreQty"));
        sic.add(new SelectorItemInfo("DateOfMinDurability.mfg"));
        sic.add(new SelectorItemInfo("DateOfMinDurability.exp"));
        sic.add(new SelectorItemInfo("storageOrgUnit.id"));
        sic.add(new SelectorItemInfo("multiUnit.qtyPrecision"));
        sic.add(new SelectorItemInfo("multiBaseUnit.qtyPrecision"));
        sic.add(new SelectorItemInfo("storetype.id"));
        sic.add(new SelectorItemInfo("multiUnit.baseConvsRate"));
        sic.add(new SelectorItemInfo("multiAssitUnit.qtyPrecision"));
        sic.add(new SelectorItemInfo("multiBaseUnit.baseConvsRate"));
        sic.add(new SelectorItemInfo("warehouse.id"));
        sic.add(new SelectorItemInfo("material.id"));
        return sic;
    }        
    	

    /**
     * output actionRefreshByMat_actionPerformed method
     */
    public void actionRefreshByMat_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionRefreshByOrg_actionPerformed method
     */
    public void actionRefreshByOrg_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionShowRefreshInv_actionPerformed method
     */
    public void actionShowRefreshInv_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAdjustInwarehs_actionPerformed method
     */
    public void actionAdjustInwarehs_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionAdjustOutwarehs_actionPerformed method
     */
    public void actionAdjustOutwarehs_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionRefreshByMat(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionRefreshByMat() {
    	return false;
    }
	public RequestContext prepareActionRefreshByOrg(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionRefreshByOrg() {
    	return false;
    }
	public RequestContext prepareActionShowRefreshInv(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionShowRefreshInv() {
    	return false;
    }
	public RequestContext prepareactionAdjustInwarehs(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionAdjustInwarehs() {
    	return false;
    }
	public RequestContext prepareactionAdjustOutwarehs(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareactionAdjustOutwarehs() {
    	return false;
    }

    /**
     * output ActionRefreshByMat class
     */     
    protected class ActionRefreshByMat extends ItemAction {     
    
        public ActionRefreshByMat()
        {
            this(null);
        }

        public ActionRefreshByMat(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionRefreshByMat.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRefreshByMat.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRefreshByMat.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInventoryListUI.this, "ActionRefreshByMat", "actionRefreshByMat_actionPerformed", e);
        }
    }

    /**
     * output ActionRefreshByOrg class
     */     
    protected class ActionRefreshByOrg extends ItemAction {     
    
        public ActionRefreshByOrg()
        {
            this(null);
        }

        public ActionRefreshByOrg(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionRefreshByOrg.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRefreshByOrg.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionRefreshByOrg.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInventoryListUI.this, "ActionRefreshByOrg", "actionRefreshByOrg_actionPerformed", e);
        }
    }

    /**
     * output ActionShowRefreshInv class
     */     
    protected class ActionShowRefreshInv extends ItemAction {     
    
        public ActionShowRefreshInv()
        {
            this(null);
        }

        public ActionShowRefreshInv(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionShowRefreshInv.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionShowRefreshInv.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionShowRefreshInv.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInventoryListUI.this, "ActionShowRefreshInv", "actionShowRefreshInv_actionPerformed", e);
        }
    }

    /**
     * output actionAdjustInwarehs class
     */     
    protected class actionAdjustInwarehs extends ItemAction {     
    
        public actionAdjustInwarehs()
        {
            this(null);
        }

        public actionAdjustInwarehs(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionAdjustInwarehs.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAdjustInwarehs.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAdjustInwarehs.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInventoryListUI.this, "actionAdjustInwarehs", "actionAdjustInwarehs_actionPerformed", e);
        }
    }

    /**
     * output actionAdjustOutwarehs class
     */     
    protected class actionAdjustOutwarehs extends ItemAction {     
    
        public actionAdjustOutwarehs()
        {
            this(null);
        }

        public actionAdjustOutwarehs(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("actionAdjustOutwarehs.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAdjustOutwarehs.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("actionAdjustOutwarehs.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractInventoryListUI.this, "actionAdjustOutwarehs", "actionAdjustOutwarehs_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.scm.im.inv.client", "InventoryListUI");
    }




}