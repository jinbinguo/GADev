/**
 * output package name
 */
package com.kingdee.eas.scm.cal.client;

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
public abstract class AbstractDevolveWriteOffUI extends com.kingdee.eas.scm.common.client.ManulWriteoffCommonUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractDevolveWriteOffUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conWriteOffStandard;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conApportionRule;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbWriteOffStandard;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbApportionRule;
    protected javax.swing.JToolBar.Separator separator3;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAllSelected;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnNonSelected;
    protected javax.swing.JToolBar.Separator separator4;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnWriteOffView;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnWriteOff;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnMultiBillWriteOff;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnWriteOffQuery;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnBill;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnMaterilTotal;
    protected javax.swing.JToolBar.Separator separator5;
    protected ActionAllSelected actionAllSelected = null;
    protected ActionNoSelected actionNoSelected = null;
    protected ActionWriteOffView actionWriteOffView = null;
    protected ActionWriteOff actionWriteOff = null;
    protected ActionWriteOffQuery actionWriteOffQuery = null;
    protected ActionBill actionBill = null;
    protected ActionMaterialTotal actionMaterialTotal = null;
    protected ActionMultiBillWriteOff actionMultiBillWriteOff = null;
    /**
     * output class constructor
     */
    public AbstractDevolveWriteOffUI() throws Exception
    {
        super();
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractDevolveWriteOffUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionAllSelected
        this.actionAllSelected = new ActionAllSelected(this);
        getActionManager().registerAction("actionAllSelected", actionAllSelected);
         this.actionAllSelected.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionNoSelected
        this.actionNoSelected = new ActionNoSelected(this);
        getActionManager().registerAction("actionNoSelected", actionNoSelected);
         this.actionNoSelected.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionWriteOffView
        this.actionWriteOffView = new ActionWriteOffView(this);
        getActionManager().registerAction("actionWriteOffView", actionWriteOffView);
         this.actionWriteOffView.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionWriteOff
        this.actionWriteOff = new ActionWriteOff(this);
        getActionManager().registerAction("actionWriteOff", actionWriteOff);
         this.actionWriteOff.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionWriteOffQuery
        this.actionWriteOffQuery = new ActionWriteOffQuery(this);
        getActionManager().registerAction("actionWriteOffQuery", actionWriteOffQuery);
         this.actionWriteOffQuery.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionBill
        this.actionBill = new ActionBill(this);
        getActionManager().registerAction("actionBill", actionBill);
         this.actionBill.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionMaterialTotal
        this.actionMaterialTotal = new ActionMaterialTotal(this);
        getActionManager().registerAction("actionMaterialTotal", actionMaterialTotal);
         this.actionMaterialTotal.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionMultiBillWriteOff
        this.actionMultiBillWriteOff = new ActionMultiBillWriteOff(this);
        getActionManager().registerAction("actionMultiBillWriteOff", actionMultiBillWriteOff);
         this.actionMultiBillWriteOff.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.conWriteOffStandard = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conApportionRule = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.cmbWriteOffStandard = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.cmbApportionRule = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.separator3 = new javax.swing.JToolBar.Separator();
        this.btnAllSelected = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnNonSelected = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator4 = new javax.swing.JToolBar.Separator();
        this.btnWriteOffView = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnWriteOff = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnMultiBillWriteOff = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnWriteOffQuery = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnBill = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnMaterilTotal = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.separator5 = new javax.swing.JToolBar.Separator();
        this.conWriteOffStandard.setName("conWriteOffStandard");
        this.conApportionRule.setName("conApportionRule");
        this.cmbWriteOffStandard.setName("cmbWriteOffStandard");
        this.cmbApportionRule.setName("cmbApportionRule");
        this.separator3.setName("separator3");
        this.btnAllSelected.setName("btnAllSelected");
        this.btnNonSelected.setName("btnNonSelected");
        this.separator4.setName("separator4");
        this.btnWriteOffView.setName("btnWriteOffView");
        this.btnWriteOff.setName("btnWriteOff");
        this.btnMultiBillWriteOff.setName("btnMultiBillWriteOff");
        this.btnWriteOffQuery.setName("btnWriteOffQuery");
        this.btnBill.setName("btnBill");
        this.btnMaterilTotal.setName("btnMaterilTotal");
        this.separator5.setName("separator5");
        // CoreUI

        


        
		
        this.btnUpQuery.setText(resHelper.getString("btnUpQuery.text"));
        // conWriteOffStandard		
        this.conWriteOffStandard.setBoundLabelText(resHelper.getString("conWriteOffStandard.boundLabelText"));		
        this.conWriteOffStandard.setBoundLabelLength(80);		
        this.conWriteOffStandard.setBoundLabelUnderline(true);
        // conApportionRule		
        this.conApportionRule.setBoundLabelText(resHelper.getString("conApportionRule.boundLabelText"));		
        this.conApportionRule.setBoundLabelLength(80);		
        this.conApportionRule.setBoundLabelUnderline(true);
        // cmbWriteOffStandard		
        this.cmbWriteOffStandard.addItems(EnumUtils.getEnumList("com.kingdee.eas.scm.cal.DevolveWriteOffStandardEnum").toArray());		
        this.cmbWriteOffStandard.setSelectedIndex(0);
        // cmbApportionRule		
        this.cmbApportionRule.addItems(EnumUtils.getEnumList("com.kingdee.eas.scm.cal.DevolveApportionRuleEnum").toArray());		
        this.cmbApportionRule.setSelectedIndex(0);
        // separator3		
        this.separator3.setOrientation(1);
        // btnAllSelected
        this.btnAllSelected.setAction((IItemAction)ActionProxyFactory.getProxy(actionAllSelected, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAllSelected.setText(resHelper.getString("btnAllSelected.text"));
        // btnNonSelected
        this.btnNonSelected.setAction((IItemAction)ActionProxyFactory.getProxy(actionNoSelected, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnNonSelected.setText(resHelper.getString("btnNonSelected.text"));
        // separator4
        // btnWriteOffView
        this.btnWriteOffView.setAction((IItemAction)ActionProxyFactory.getProxy(actionWriteOffView, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnWriteOffView.setText(resHelper.getString("btnWriteOffView.text"));
        // btnWriteOff
        this.btnWriteOff.setAction((IItemAction)ActionProxyFactory.getProxy(actionWriteOff, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnWriteOff.setText(resHelper.getString("btnWriteOff.text"));
        // btnMultiBillWriteOff
        this.btnMultiBillWriteOff.setAction((IItemAction)ActionProxyFactory.getProxy(actionMultiBillWriteOff, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnMultiBillWriteOff.setText(resHelper.getString("btnMultiBillWriteOff.text"));
        // btnWriteOffQuery
        this.btnWriteOffQuery.setAction((IItemAction)ActionProxyFactory.getProxy(actionWriteOffQuery, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnWriteOffQuery.setText(resHelper.getString("btnWriteOffQuery.text"));
        // btnBill
        this.btnBill.setAction((IItemAction)ActionProxyFactory.getProxy(actionBill, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnBill.setText(resHelper.getString("btnBill.text"));
        // btnMaterilTotal
        this.btnMaterilTotal.setAction((IItemAction)ActionProxyFactory.getProxy(actionMaterialTotal, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnMaterilTotal.setText(resHelper.getString("btnMaterilTotal.text"));
        // separator5
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
        this.setBounds(new Rectangle(10, 10, 1016, 600));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(10, 10, 1016, 600));
        kDContainer1.setBounds(new Rectangle(10, 319, 996, 272));
        this.add(kDContainer1, new KDLayout.Constraints(10, 319, 996, 272, KDLayout.Constraints.ANCHOR_TOP_SCALE | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        kDContainer2.setBounds(new Rectangle(10, 35, 996, 272));
        this.add(kDContainer2, new KDLayout.Constraints(10, 35, 996, 272, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        titlesPanel.setBounds(new Rectangle(7, 3, 1003, 42));
        this.add(titlesPanel, new KDLayout.Constraints(7, 3, 1003, 42, KDLayout.Constraints.ANCHOR_CENTRE | KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //kDContainer1
        kDContainer1.getContentPane().setLayout(new KDLayout());
        kDContainer1.getContentPane().putClientProperty("OriginalBounds", new Rectangle(10, 319, 996, 272));        bottomKdTable.setBounds(new Rectangle(0, 0, 996, 272));
        kDContainer1.getContentPane().add(bottomKdTable, new KDLayout.Constraints(0, 0, 996, 272, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //kDContainer2
        kDContainer2.getContentPane().setLayout(new KDLayout());
        kDContainer2.getContentPane().putClientProperty("OriginalBounds", new Rectangle(10, 35, 996, 272));        topKdtable.setBounds(new Rectangle(0, 0, 996, 272));
        kDContainer2.getContentPane().add(topKdtable, new KDLayout.Constraints(0, 0, 996, 272, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //titlesPanel
        titlesPanel.setLayout(null);        conWriteOffStandard.setBounds(new Rectangle(10, 10, 270, 19));
        titlesPanel.add(conWriteOffStandard, null);
        conApportionRule.setBounds(new Rectangle(296, 10, 270, 19));
        titlesPanel.add(conApportionRule, null);
        //conWriteOffStandard
        conWriteOffStandard.setBoundEditor(cmbWriteOffStandard);
        //conApportionRule
        conApportionRule.setBoundEditor(cmbApportionRule);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuView);
        this.menuBar.add(menuTool);
        this.menuBar.add(MenuService);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(menuItemCloudShare);
        menuFile.add(menuItemExitCurrent);
        menuFile.add(kdSeparatorFWFile1);
        //menuView
        menuView.add(menuItemQuery);
        menuView.add(menuItemRefresh);
        menuView.add(kDSeparator2);
        menuView.add(menuItemChart);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
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
        this.toolBar.add(btnUpQuery);
        this.toolBar.add(btnCloud);
        this.toolBar.add(btnDownQuery);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(separator3);
        this.toolBar.add(btnAllSelected);
        this.toolBar.add(btnNonSelected);
        this.toolBar.add(separator4);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnRefresh);
        this.toolBar.add(btnQuery);
        this.toolBar.add(separator1);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separator2);
        this.toolBar.add(btnChart);
        this.toolBar.add(btnWriteOffView);
        this.toolBar.add(btnWriteOff);
        this.toolBar.add(btnMultiBillWriteOff);
        this.toolBar.add(btnWriteOffQuery);
        this.toolBar.add(btnBill);
        this.toolBar.add(btnMaterilTotal);
        this.toolBar.add(separator5);


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.scm.cal.app.DevolveWriteOffUIHandler";
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
     * output actionAllSelected_actionPerformed method
     */
    public void actionAllSelected_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionNoSelected_actionPerformed method
     */
    public void actionNoSelected_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionWriteOffView_actionPerformed method
     */
    public void actionWriteOffView_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionWriteOff_actionPerformed method
     */
    public void actionWriteOff_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionWriteOffQuery_actionPerformed method
     */
    public void actionWriteOffQuery_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionBill_actionPerformed method
     */
    public void actionBill_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionMaterialTotal_actionPerformed method
     */
    public void actionMaterialTotal_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionMultiBillWriteOff_actionPerformed method
     */
    public void actionMultiBillWriteOff_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionAllSelected(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAllSelected() {
    	return false;
    }
	public RequestContext prepareActionNoSelected(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionNoSelected() {
    	return false;
    }
	public RequestContext prepareActionWriteOffView(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionWriteOffView() {
    	return false;
    }
	public RequestContext prepareActionWriteOff(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionWriteOff() {
    	return false;
    }
	public RequestContext prepareActionWriteOffQuery(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionWriteOffQuery() {
    	return false;
    }
	public RequestContext prepareActionBill(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionBill() {
    	return false;
    }
	public RequestContext prepareActionMaterialTotal(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionMaterialTotal() {
    	return false;
    }
	public RequestContext prepareActionMultiBillWriteOff(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionMultiBillWriteOff() {
    	return false;
    }

    /**
     * output ActionAllSelected class
     */     
    protected class ActionAllSelected extends ItemAction {     
    
        public ActionAllSelected()
        {
            this(null);
        }

        public ActionAllSelected(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAllSelected.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAllSelected.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAllSelected.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractDevolveWriteOffUI.this, "ActionAllSelected", "actionAllSelected_actionPerformed", e);
        }
    }

    /**
     * output ActionNoSelected class
     */     
    protected class ActionNoSelected extends ItemAction {     
    
        public ActionNoSelected()
        {
            this(null);
        }

        public ActionNoSelected(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionNoSelected.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionNoSelected.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionNoSelected.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractDevolveWriteOffUI.this, "ActionNoSelected", "actionNoSelected_actionPerformed", e);
        }
    }

    /**
     * output ActionWriteOffView class
     */     
    protected class ActionWriteOffView extends ItemAction {     
    
        public ActionWriteOffView()
        {
            this(null);
        }

        public ActionWriteOffView(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionWriteOffView.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionWriteOffView.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionWriteOffView.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractDevolveWriteOffUI.this, "ActionWriteOffView", "actionWriteOffView_actionPerformed", e);
        }
    }

    /**
     * output ActionWriteOff class
     */     
    protected class ActionWriteOff extends ItemAction {     
    
        public ActionWriteOff()
        {
            this(null);
        }

        public ActionWriteOff(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionWriteOff.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionWriteOff.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionWriteOff.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractDevolveWriteOffUI.this, "ActionWriteOff", "actionWriteOff_actionPerformed", e);
        }
    }

    /**
     * output ActionWriteOffQuery class
     */     
    protected class ActionWriteOffQuery extends ItemAction {     
    
        public ActionWriteOffQuery()
        {
            this(null);
        }

        public ActionWriteOffQuery(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionWriteOffQuery.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionWriteOffQuery.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionWriteOffQuery.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractDevolveWriteOffUI.this, "ActionWriteOffQuery", "actionWriteOffQuery_actionPerformed", e);
        }
    }

    /**
     * output ActionBill class
     */     
    protected class ActionBill extends ItemAction {     
    
        public ActionBill()
        {
            this(null);
        }

        public ActionBill(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionBill.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionBill.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionBill.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractDevolveWriteOffUI.this, "ActionBill", "actionBill_actionPerformed", e);
        }
    }

    /**
     * output ActionMaterialTotal class
     */     
    protected class ActionMaterialTotal extends ItemAction {     
    
        public ActionMaterialTotal()
        {
            this(null);
        }

        public ActionMaterialTotal(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionMaterialTotal.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMaterialTotal.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMaterialTotal.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractDevolveWriteOffUI.this, "ActionMaterialTotal", "actionMaterialTotal_actionPerformed", e);
        }
    }

    /**
     * output ActionMultiBillWriteOff class
     */     
    protected class ActionMultiBillWriteOff extends ItemAction {     
    
        public ActionMultiBillWriteOff()
        {
            this(null);
        }

        public ActionMultiBillWriteOff(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionMultiBillWriteOff.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMultiBillWriteOff.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMultiBillWriteOff.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractDevolveWriteOffUI.this, "ActionMultiBillWriteOff", "actionMultiBillWriteOff_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.scm.cal.client", "DevolveWriteOffUI");
    }




}