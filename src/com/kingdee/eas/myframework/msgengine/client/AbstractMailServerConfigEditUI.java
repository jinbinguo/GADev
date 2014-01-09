/**
 * output package name
 */
package com.kingdee.eas.myframework.msgengine.client;

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
public abstract class AbstractMailServerConfigEditUI extends com.kingdee.eas.myframework.template.base.client.SimpleDatabaseEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractMailServerConfigEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conthost;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contport;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contprotocol;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contpassword;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer5;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contusername;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contaliasname;
    protected com.kingdee.bos.ctrl.swing.KDTextField txthost;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtport;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtprotocol;
    protected com.kingdee.bos.ctrl.swing.KDPasswordField txtPwd;
    protected com.kingdee.bos.ctrl.swing.KDPasswordField txtRePwd;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtusername;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtaliasname;
    protected com.kingdee.eas.myframework.msgengine.MailServerConfigInfo editData = null;
    /**
     * output class constructor
     */
    public AbstractMailServerConfigEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractMailServerConfigEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        this.conthost = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contport = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contprotocol = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contpassword = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer5 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contusername = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contaliasname = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txthost = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtport = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtprotocol = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtPwd = new com.kingdee.bos.ctrl.swing.KDPasswordField();
        this.txtRePwd = new com.kingdee.bos.ctrl.swing.KDPasswordField();
        this.txtusername = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtaliasname = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.conthost.setName("conthost");
        this.contport.setName("contport");
        this.contprotocol.setName("contprotocol");
        this.contpassword.setName("contpassword");
        this.kDLabelContainer5.setName("kDLabelContainer5");
        this.contusername.setName("contusername");
        this.contaliasname.setName("contaliasname");
        this.txthost.setName("txthost");
        this.txtport.setName("txtport");
        this.txtprotocol.setName("txtprotocol");
        this.txtPwd.setName("txtPwd");
        this.txtRePwd.setName("txtRePwd");
        this.txtusername.setName("txtusername");
        this.txtaliasname.setName("txtaliasname");
        // CoreUI		
        this.btnPrint.setVisible(false);		
        this.btnPrintPreview.setVisible(false);		
        this.kDLabelContainer3.setVisible(false);		
        this.kDLabelContainer4.setVisible(false);		
        this.txtSimpleName.setVisible(false);		
        this.txtDescription.setVisible(false);
        // conthost		
        this.conthost.setBoundLabelText(resHelper.getString("conthost.boundLabelText"));		
        this.conthost.setBoundLabelLength(100);		
        this.conthost.setBoundLabelUnderline(true);		
        this.conthost.setVisible(true);
        // contport		
        this.contport.setBoundLabelText(resHelper.getString("contport.boundLabelText"));		
        this.contport.setBoundLabelLength(100);		
        this.contport.setBoundLabelUnderline(true);		
        this.contport.setVisible(true);
        // contprotocol		
        this.contprotocol.setBoundLabelText(resHelper.getString("contprotocol.boundLabelText"));		
        this.contprotocol.setBoundLabelLength(100);		
        this.contprotocol.setBoundLabelUnderline(true);		
        this.contprotocol.setVisible(true);
        // contpassword		
        this.contpassword.setBoundLabelText(resHelper.getString("contpassword.boundLabelText"));		
        this.contpassword.setBoundLabelLength(100);		
        this.contpassword.setBoundLabelUnderline(true);		
        this.contpassword.setVisible(true);
        // kDLabelContainer5		
        this.kDLabelContainer5.setBoundLabelText(resHelper.getString("kDLabelContainer5.boundLabelText"));		
        this.kDLabelContainer5.setBoundLabelUnderline(true);		
        this.kDLabelContainer5.setBoundLabelLength(100);
        // contusername		
        this.contusername.setBoundLabelText(resHelper.getString("contusername.boundLabelText"));		
        this.contusername.setBoundLabelLength(100);		
        this.contusername.setBoundLabelUnderline(true);		
        this.contusername.setVisible(true);
        // contaliasname		
        this.contaliasname.setBoundLabelText(resHelper.getString("contaliasname.boundLabelText"));		
        this.contaliasname.setBoundLabelLength(100);		
        this.contaliasname.setBoundLabelUnderline(true);		
        this.contaliasname.setVisible(true);
        // txthost		
        this.txthost.setHorizontalAlignment(2);		
        this.txthost.setMaxLength(100);		
        this.txthost.setRequired(false);
        // txtport		
        this.txtport.setHorizontalAlignment(2);		
        this.txtport.setDataType(0);		
        this.txtport.setSupportedEmpty(true);		
        this.txtport.setRequired(false);
        // txtprotocol		
        this.txtprotocol.setHorizontalAlignment(2);		
        this.txtprotocol.setMaxLength(100);		
        this.txtprotocol.setRequired(false);
        // txtPwd
        // txtRePwd
        // txtusername		
        this.txtusername.setHorizontalAlignment(2);		
        this.txtusername.setMaxLength(100);		
        this.txtusername.setRequired(false);
        // txtaliasname		
        this.txtaliasname.setHorizontalAlignment(2);		
        this.txtaliasname.setMaxLength(100);		
        this.txtaliasname.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {txtNumber,txtName,txthost,txtport,txtprotocol,txtusername,txtaliasname,txtSimpleName,txtPwd,txtDescription,txtRePwd,boxDeletedStatus,chkscheduled}));
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
        this.setBounds(new Rectangle(0, 0, 361, 341));
        this.setLayout(null);
        kDLabelContainer1.setBounds(new Rectangle(10, 10, 270, 19));
        this.add(kDLabelContainer1, null);
        kDLabelContainer2.setBounds(new Rectangle(10, 34, 270, 19));
        this.add(kDLabelContainer2, null);
        kDLabelContainer3.setBounds(new Rectangle(10, 298, 270, 19));
        this.add(kDLabelContainer3, null);
        kDLabelContainer4.setBounds(new Rectangle(10, 274, 270, 19));
        this.add(kDLabelContainer4, null);
        contdeletedStatus.setBounds(new Rectangle(10, 226, 270, 19));
        this.add(contdeletedStatus, null);
        chkscheduled.setBounds(new Rectangle(10, 250, 270, 19));
        this.add(chkscheduled, null);
        conthost.setBounds(new Rectangle(10, 58, 270, 19));
        this.add(conthost, null);
        contport.setBounds(new Rectangle(10, 82, 270, 19));
        this.add(contport, null);
        contprotocol.setBounds(new Rectangle(10, 106, 270, 19));
        this.add(contprotocol, null);
        contpassword.setBounds(new Rectangle(10, 178, 270, 19));
        this.add(contpassword, null);
        kDLabelContainer5.setBounds(new Rectangle(10, 202, 270, 19));
        this.add(kDLabelContainer5, null);
        contusername.setBounds(new Rectangle(10, 130, 270, 19));
        this.add(contusername, null);
        contaliasname.setBounds(new Rectangle(10, 154, 270, 19));
        this.add(contaliasname, null);
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(txtNumber);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(txtName);
        //kDLabelContainer3
        kDLabelContainer3.setBoundEditor(txtSimpleName);
        //kDLabelContainer4
        kDLabelContainer4.setBoundEditor(txtDescription);
        //contdeletedStatus
        contdeletedStatus.setBoundEditor(boxDeletedStatus);
        //conthost
        conthost.setBoundEditor(txthost);
        //contport
        contport.setBoundEditor(txtport);
        //contprotocol
        contprotocol.setBoundEditor(txtprotocol);
        //contpassword
        contpassword.setBoundEditor(txtPwd);
        //kDLabelContainer5
        kDLabelContainer5.setBoundEditor(txtRePwd);
        //contusername
        contusername.setBoundEditor(txtusername);
        //contaliasname
        contaliasname.setBoundEditor(txtaliasname);

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
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
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
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSave);
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


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("scheduled", boolean.class, this.chkscheduled, "selected");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("name", String.class, this.txtName, "_multiLangItem");
		dataBinder.registerBinding("simpleName", String.class, this.txtSimpleName, "text");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "_multiLangItem");
		dataBinder.registerBinding("deletedStatus", com.kingdee.eas.basedata.ncm.DeletedStatusEnum.class, this.boxDeletedStatus, "selectedItem");
		dataBinder.registerBinding("host", String.class, this.txthost, "text");
		dataBinder.registerBinding("port", int.class, this.txtport, "value");
		dataBinder.registerBinding("protocol", String.class, this.txtprotocol, "text");
		dataBinder.registerBinding("password", String.class, this.txtPwd, "text");
		dataBinder.registerBinding("username", String.class, this.txtusername, "text");
		dataBinder.registerBinding("aliasname", String.class, this.txtaliasname, "text");		
	}
	//Regiester UI State
	private void registerUIState(){
	        getActionManager().registerUIState(STATUS_ADDNEW, this.txtName, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_ADDNEW, this.txtDescription, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_ADDNEW, this.txtNumber, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_ADDNEW, this.txtSimpleName, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_EDIT, this.txtName, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_EDIT, this.txtDescription, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_EDIT, this.txtNumber, ActionStateConst.ENABLED);
	        getActionManager().registerUIState(STATUS_EDIT, this.txtSimpleName, ActionStateConst.ENABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.txtName, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.txtDescription, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.txtNumber, ActionStateConst.DISABLED);					 	        		
	        getActionManager().registerUIState(STATUS_VIEW, this.txtSimpleName, ActionStateConst.DISABLED);		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.myframework.msgengine.app.MailServerConfigEditUIHandler";
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
        this.editData = (com.kingdee.eas.myframework.msgengine.MailServerConfigInfo)ov;
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
		getValidateHelper().registerBindProperty("scheduled", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("simpleName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("deletedStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("host", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("port", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("protocol", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("password", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("username", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("aliasname", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
		            this.txtName.setEnabled(true);
		            this.txtDescription.setEnabled(true);
		            this.txtNumber.setEnabled(true);
		            this.txtSimpleName.setEnabled(true);
        } else if (STATUS_EDIT.equals(this.oprtState)) {
		            this.txtName.setEnabled(true);
		            this.txtDescription.setEnabled(true);
		            this.txtNumber.setEnabled(true);
		            this.txtSimpleName.setEnabled(true);
        } else if (STATUS_VIEW.equals(this.oprtState)) {
		            this.txtName.setEnabled(false);
		            this.txtDescription.setEnabled(false);
		            this.txtNumber.setEnabled(false);
		            this.txtSimpleName.setEnabled(false);
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
        sic.add(new SelectorItemInfo("scheduled"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("name"));
        sic.add(new SelectorItemInfo("simpleName"));
        sic.add(new SelectorItemInfo("description"));
        sic.add(new SelectorItemInfo("deletedStatus"));
        sic.add(new SelectorItemInfo("host"));
        sic.add(new SelectorItemInfo("port"));
        sic.add(new SelectorItemInfo("protocol"));
        sic.add(new SelectorItemInfo("password"));
        sic.add(new SelectorItemInfo("username"));
        sic.add(new SelectorItemInfo("aliasname"));
        return sic;
    }        

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.myframework.msgengine.client", "MailServerConfigEditUI");
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.myframework.msgengine.client.MailServerConfigEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.myframework.msgengine.MailServerConfigFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.myframework.msgengine.MailServerConfigInfo objectValue = new com.kingdee.eas.myframework.msgengine.MailServerConfigInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }



    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {        
        return null;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("deletedStatus",new Integer(1));
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}