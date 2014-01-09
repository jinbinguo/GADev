/**
 * output package name
 */
package com.kingdee.eas.myframework.common.client;

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
public abstract class AbstractMultiColumnSortUI extends CoreUIObject
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractMultiColumnSortUI.class);
    protected ResourceBundleHelper resHelper = null;
    protected com.kingdee.bos.ctrl.swing.KDToolBar MultiColumnSortUI_toolbar;
    protected com.kingdee.bos.ctrl.swing.KDContainer contSortTable;
    protected com.kingdee.bos.ctrl.swing.KDButton btnOK;
    protected com.kingdee.bos.ctrl.swing.KDButton btnCancel;
    protected com.kingdee.bos.ctrl.swing.KDButton btnReset;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable tblMain;
    protected ActionMoveTop actionMoveTop = null;
    protected ActionMoveUp actionMoveUp = null;
    protected ActionMoveDown actionMoveDown = null;
    protected ActionMoveBottom actionMoveBottom = null;
    protected ActionOK actionOK = null;
    protected ActionCancel actionCancel = null;
    protected ActionReset actionReset = null;
    protected ActionSearchField actionSearchField = null;
    /**
     * output class constructor
     */
    public AbstractMultiColumnSortUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractMultiColumnSortUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionMoveTop
        this.actionMoveTop = new ActionMoveTop(this);
        getActionManager().registerAction("actionMoveTop", actionMoveTop);
         this.actionMoveTop.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionMoveUp
        this.actionMoveUp = new ActionMoveUp(this);
        getActionManager().registerAction("actionMoveUp", actionMoveUp);
         this.actionMoveUp.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionMoveDown
        this.actionMoveDown = new ActionMoveDown(this);
        getActionManager().registerAction("actionMoveDown", actionMoveDown);
         this.actionMoveDown.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionMoveBottom
        this.actionMoveBottom = new ActionMoveBottom(this);
        getActionManager().registerAction("actionMoveBottom", actionMoveBottom);
         this.actionMoveBottom.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionOK
        this.actionOK = new ActionOK(this);
        getActionManager().registerAction("actionOK", actionOK);
         this.actionOK.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionCancel
        this.actionCancel = new ActionCancel(this);
        getActionManager().registerAction("actionCancel", actionCancel);
         this.actionCancel.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionReset
        this.actionReset = new ActionReset(this);
        getActionManager().registerAction("actionReset", actionReset);
         this.actionReset.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        //actionSearchField
        this.actionSearchField = new ActionSearchField(this);
        getActionManager().registerAction("actionSearchField", actionSearchField);
         this.actionSearchField.addService(new com.kingdee.eas.framework.client.service.PermissionService());
        this.toolBar = new com.kingdee.bos.ctrl.swing.KDToolBar();
        this.menuBar = new com.kingdee.bos.ctrl.swing.KDMenuBar();
        this.contSortTable = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.btnOK = new com.kingdee.bos.ctrl.swing.KDButton();
        this.btnCancel = new com.kingdee.bos.ctrl.swing.KDButton();
        this.btnReset = new com.kingdee.bos.ctrl.swing.KDButton();
        this.tblMain = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.setName("MultiColumnSortUI");
        this.toolBar.setName("MultiColumnSortUI_toolbar");
        this.menuBar.setName("MultiColumnSortUI_menubar");
        this.contSortTable.setName("contSortTable");
        this.btnOK.setName("btnOK");
        this.btnCancel.setName("btnCancel");
        this.btnReset.setName("btnReset");
        this.tblMain.setName("tblMain");
        // MultiColumnSortUI
        // MultiColumnSortUI_toolbar
        // MultiColumnSortUI_menubar
        // contSortTable		
        this.contSortTable.setEnableActive(false);
        // btnOK
        this.btnOK.setAction((IItemAction)ActionProxyFactory.getProxy(actionOK, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnOK.setText(resHelper.getString("btnOK.text"));
        // btnCancel
        this.btnCancel.setAction((IItemAction)ActionProxyFactory.getProxy(actionCancel, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnCancel.setText(resHelper.getString("btnCancel.text"));
        // btnReset
        this.btnReset.setAction((IItemAction)ActionProxyFactory.getProxy(actionReset, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnReset.setText(resHelper.getString("btnReset.text"));
        // tblMain
		String tblMainStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles /><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"orderField\" t:width=\"200\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" /><t:Column t:key=\"isChoose\" t:width=\"50\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"sortType\" t:width=\"80\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header1\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{orderField}</t:Cell><t:Cell>$Resource{isChoose}</t:Cell><t:Cell>$Resource{sortType}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.tblMain.setFormatXml(resHelper.translateString("tblMain",tblMainStrXML));

        

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
		list.add(this.toolBar);
		return (com.kingdee.bos.ctrl.swing.KDToolBar[])list.toArray(new com.kingdee.bos.ctrl.swing.KDToolBar[list.size()]);
	}




    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(10, 10, 380, 530));
        this.setLayout(null);
        contSortTable.setBounds(new Rectangle(7, 5, 367, 481));
        this.add(contSortTable, null);
        btnOK.setBounds(new Rectangle(217, 497, 73, 21));
        this.add(btnOK, null);
        btnCancel.setBounds(new Rectangle(300, 497, 73, 21));
        this.add(btnCancel, null);
        btnReset.setBounds(new Rectangle(11, 498, 82, 21));
        this.add(btnReset, null);
        //contSortTable
contSortTable.getContentPane().setLayout(new BorderLayout(0, 0));        contSortTable.getContentPane().add(tblMain, BorderLayout.CENTER);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {


    }

	//Regiester control's property binding.
	private void registerBindings(){		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.myframework.common.app.MultiColumnSortUIHandler";
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
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
        return sic;
    }        
    	

    /**
     * output actionMoveTop_actionPerformed method
     */
    public void actionMoveTop_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionMoveUp_actionPerformed method
     */
    public void actionMoveUp_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionMoveDown_actionPerformed method
     */
    public void actionMoveDown_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionMoveBottom_actionPerformed method
     */
    public void actionMoveBottom_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionOK_actionPerformed method
     */
    public void actionOK_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionCancel_actionPerformed method
     */
    public void actionCancel_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionReset_actionPerformed method
     */
    public void actionReset_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionSearchField_actionPerformed method
     */
    public void actionSearchField_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionMoveTop(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionMoveTop() {
    	return false;
    }
	public RequestContext prepareActionMoveUp(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionMoveUp() {
    	return false;
    }
	public RequestContext prepareActionMoveDown(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionMoveDown() {
    	return false;
    }
	public RequestContext prepareActionMoveBottom(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionMoveBottom() {
    	return false;
    }
	public RequestContext prepareActionOK(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionOK() {
    	return false;
    }
	public RequestContext prepareActionCancel(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCancel() {
    	return false;
    }
	public RequestContext prepareActionReset(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionReset() {
    	return false;
    }
	public RequestContext prepareActionSearchField(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSearchField() {
    	return false;
    }

    /**
     * output ActionMoveTop class
     */     
    protected class ActionMoveTop extends ItemAction {     
    
        public ActionMoveTop()
        {
            this(null);
        }

        public ActionMoveTop(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionMoveTop.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMoveTop.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMoveTop.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMultiColumnSortUI.this, "ActionMoveTop", "actionMoveTop_actionPerformed", e);
        }
    }

    /**
     * output ActionMoveUp class
     */     
    protected class ActionMoveUp extends ItemAction {     
    
        public ActionMoveUp()
        {
            this(null);
        }

        public ActionMoveUp(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionMoveUp.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMoveUp.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMoveUp.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMultiColumnSortUI.this, "ActionMoveUp", "actionMoveUp_actionPerformed", e);
        }
    }

    /**
     * output ActionMoveDown class
     */     
    protected class ActionMoveDown extends ItemAction {     
    
        public ActionMoveDown()
        {
            this(null);
        }

        public ActionMoveDown(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionMoveDown.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMoveDown.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMoveDown.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMultiColumnSortUI.this, "ActionMoveDown", "actionMoveDown_actionPerformed", e);
        }
    }

    /**
     * output ActionMoveBottom class
     */     
    protected class ActionMoveBottom extends ItemAction {     
    
        public ActionMoveBottom()
        {
            this(null);
        }

        public ActionMoveBottom(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionMoveBottom.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMoveBottom.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionMoveBottom.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMultiColumnSortUI.this, "ActionMoveBottom", "actionMoveBottom_actionPerformed", e);
        }
    }

    /**
     * output ActionOK class
     */     
    protected class ActionOK extends ItemAction {     
    
        public ActionOK()
        {
            this(null);
        }

        public ActionOK(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionOK.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionOK.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionOK.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMultiColumnSortUI.this, "ActionOK", "actionOK_actionPerformed", e);
        }
    }

    /**
     * output ActionCancel class
     */     
    protected class ActionCancel extends ItemAction {     
    
        public ActionCancel()
        {
            this(null);
        }

        public ActionCancel(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionCancel.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCancel.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCancel.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMultiColumnSortUI.this, "ActionCancel", "actionCancel_actionPerformed", e);
        }
    }

    /**
     * output ActionReset class
     */     
    protected class ActionReset extends ItemAction {     
    
        public ActionReset()
        {
            this(null);
        }

        public ActionReset(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionReset.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionReset.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionReset.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMultiColumnSortUI.this, "ActionReset", "actionReset_actionPerformed", e);
        }
    }

    /**
     * output ActionSearchField class
     */     
    protected class ActionSearchField extends ItemAction {     
    
        public ActionSearchField()
        {
            this(null);
        }

        public ActionSearchField(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            this.setEnabled(false);
            _tempStr = resHelper.getString("ActionSearchField.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSearchField.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSearchField.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractMultiColumnSortUI.this, "ActionSearchField", "actionSearchField_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.myframework.common.client", "MultiColumnSortUI");
    }




}