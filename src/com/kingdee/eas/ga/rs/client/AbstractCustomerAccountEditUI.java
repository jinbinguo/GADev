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
public abstract class AbstractCustomerAccountEditUI extends com.kingdee.eas.myframework.template.base.client.SimpleDatabaseEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractCustomerAccountEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contretailSaleType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contretailDiscountRate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contrepairDiscountRate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer conttypeCode;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contfinCustomer;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contrepairSaleType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contsettlementType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contatsCustomer;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtretailSaleType;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtretailDiscountRate;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtrepairDiscountRate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txttypeCode;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtfinCustomer;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtrepairSaleType;
    protected com.kingdee.bos.ctrl.swing.KDComboBox settlementType;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtatsCustomer;
    protected com.kingdee.eas.ga.rs.CustomerAccountInfo editData = null;
    /**
     * output class constructor
     */
    public AbstractCustomerAccountEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractCustomerAccountEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        this.contretailSaleType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contretailDiscountRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contrepairDiscountRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.conttypeCode = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contfinCustomer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contrepairSaleType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contsettlementType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contatsCustomer = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtretailSaleType = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtretailDiscountRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtrepairDiscountRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txttypeCode = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtfinCustomer = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.txtrepairSaleType = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.settlementType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.prmtatsCustomer = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.contretailSaleType.setName("contretailSaleType");
        this.contretailDiscountRate.setName("contretailDiscountRate");
        this.contrepairDiscountRate.setName("contrepairDiscountRate");
        this.conttypeCode.setName("conttypeCode");
        this.contfinCustomer.setName("contfinCustomer");
        this.contrepairSaleType.setName("contrepairSaleType");
        this.contsettlementType.setName("contsettlementType");
        this.contatsCustomer.setName("contatsCustomer");
        this.txtretailSaleType.setName("txtretailSaleType");
        this.txtretailDiscountRate.setName("txtretailDiscountRate");
        this.txtrepairDiscountRate.setName("txtrepairDiscountRate");
        this.txttypeCode.setName("txttypeCode");
        this.prmtfinCustomer.setName("prmtfinCustomer");
        this.txtrepairSaleType.setName("txtrepairSaleType");
        this.settlementType.setName("settlementType");
        this.prmtatsCustomer.setName("prmtatsCustomer");
        // CoreUI		
        this.btnPrint.setVisible(false);		
        this.btnPrintPreview.setVisible(false);		
        this.kDLabelContainer3.setVisible(false);		
        this.kDLabelContainer4.setVisible(false);		
        this.txtSimpleName.setVisible(false);		
        this.txtDescription.setVisible(false);		
        this.boxDeletedStatus.setEnabled(true);
        // contretailSaleType		
        this.contretailSaleType.setBoundLabelText(resHelper.getString("contretailSaleType.boundLabelText"));		
        this.contretailSaleType.setBoundLabelLength(100);		
        this.contretailSaleType.setBoundLabelUnderline(true);		
        this.contretailSaleType.setVisible(true);
        // contretailDiscountRate		
        this.contretailDiscountRate.setBoundLabelText(resHelper.getString("contretailDiscountRate.boundLabelText"));		
        this.contretailDiscountRate.setBoundLabelLength(100);		
        this.contretailDiscountRate.setBoundLabelUnderline(true);		
        this.contretailDiscountRate.setVisible(true);
        // contrepairDiscountRate		
        this.contrepairDiscountRate.setBoundLabelText(resHelper.getString("contrepairDiscountRate.boundLabelText"));		
        this.contrepairDiscountRate.setBoundLabelLength(100);		
        this.contrepairDiscountRate.setBoundLabelUnderline(true);		
        this.contrepairDiscountRate.setVisible(true);
        // conttypeCode		
        this.conttypeCode.setBoundLabelText(resHelper.getString("conttypeCode.boundLabelText"));		
        this.conttypeCode.setBoundLabelLength(100);		
        this.conttypeCode.setBoundLabelUnderline(true);		
        this.conttypeCode.setVisible(true);
        // contfinCustomer		
        this.contfinCustomer.setBoundLabelText(resHelper.getString("contfinCustomer.boundLabelText"));		
        this.contfinCustomer.setBoundLabelLength(100);		
        this.contfinCustomer.setBoundLabelUnderline(true);		
        this.contfinCustomer.setVisible(true);
        // contrepairSaleType		
        this.contrepairSaleType.setBoundLabelText(resHelper.getString("contrepairSaleType.boundLabelText"));		
        this.contrepairSaleType.setBoundLabelLength(100);		
        this.contrepairSaleType.setBoundLabelUnderline(true);		
        this.contrepairSaleType.setVisible(true);
        // contsettlementType		
        this.contsettlementType.setBoundLabelText(resHelper.getString("contsettlementType.boundLabelText"));		
        this.contsettlementType.setBoundLabelLength(100);		
        this.contsettlementType.setBoundLabelUnderline(true);		
        this.contsettlementType.setVisible(true);
        // contatsCustomer		
        this.contatsCustomer.setBoundLabelText(resHelper.getString("contatsCustomer.boundLabelText"));		
        this.contatsCustomer.setBoundLabelLength(100);		
        this.contatsCustomer.setBoundLabelUnderline(true);		
        this.contatsCustomer.setVisible(true);
        // txtretailSaleType		
        this.txtretailSaleType.setHorizontalAlignment(2);		
        this.txtretailSaleType.setMaxLength(100);		
        this.txtretailSaleType.setRequired(true);
        // txtretailDiscountRate		
        this.txtretailDiscountRate.setHorizontalAlignment(2);		
        this.txtretailDiscountRate.setDataType(1);		
        this.txtretailDiscountRate.setSupportedEmpty(true);		
        this.txtretailDiscountRate.setMinimumValue( new java.math.BigDecimal("-999.99"));		
        this.txtretailDiscountRate.setMaximumValue( new java.math.BigDecimal("999.99"));		
        this.txtretailDiscountRate.setPrecision(2);		
        this.txtretailDiscountRate.setRequired(false);
        // txtrepairDiscountRate		
        this.txtrepairDiscountRate.setHorizontalAlignment(2);		
        this.txtrepairDiscountRate.setDataType(1);		
        this.txtrepairDiscountRate.setSupportedEmpty(true);		
        this.txtrepairDiscountRate.setMinimumValue( new java.math.BigDecimal("-999.99"));		
        this.txtrepairDiscountRate.setMaximumValue( new java.math.BigDecimal("999.99"));		
        this.txtrepairDiscountRate.setPrecision(2);		
        this.txtrepairDiscountRate.setRequired(false);
        // txttypeCode		
        this.txttypeCode.setHorizontalAlignment(2);		
        this.txttypeCode.setMaxLength(100);		
        this.txttypeCode.setRequired(true);
        // prmtfinCustomer		
        this.prmtfinCustomer.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.CustomerInfoQuery");		
        this.prmtfinCustomer.setEditable(true);		
        this.prmtfinCustomer.setDisplayFormat("$name$");		
        this.prmtfinCustomer.setEditFormat("$number$");		
        this.prmtfinCustomer.setCommitFormat("$number$");		
        this.prmtfinCustomer.setRequired(false);
        // txtrepairSaleType		
        this.txtrepairSaleType.setHorizontalAlignment(2);		
        this.txtrepairSaleType.setMaxLength(100);		
        this.txtrepairSaleType.setRequired(true);
        // settlementType		
        this.settlementType.addItems(EnumUtils.getEnumList("com.kingdee.eas.ga.rs.SettlementTypeEnum").toArray());		
        this.settlementType.setRequired(true);
        // prmtatsCustomer		
        this.prmtatsCustomer.setQueryInfo("com.kingdee.eas.auto4s.bdm.pbd.app.CustomerQuery");		
        this.prmtatsCustomer.setVisible(true);		
        this.prmtatsCustomer.setEditable(true);		
        this.prmtatsCustomer.setDisplayFormat("$name$");		
        this.prmtatsCustomer.setEditFormat("$number$");		
        this.prmtatsCustomer.setCommitFormat("$number$");		
        this.prmtatsCustomer.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {txtNumber,txtName,txtSimpleName,txtDescription,boxDeletedStatus,chkscheduled,txtretailSaleType,txtretailDiscountRate,txtrepairSaleType,txtrepairDiscountRate,txttypeCode,prmtfinCustomer,settlementType,prmtatsCustomer}));
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
        this.setBounds(new Rectangle(0, 0, 676, 192));
        this.setLayout(null);
        kDLabelContainer1.setBounds(new Rectangle(10, 7, 270, 19));
        this.add(kDLabelContainer1, null);
        kDLabelContainer2.setBounds(new Rectangle(293, 7, 270, 19));
        this.add(kDLabelContainer2, null);
        kDLabelContainer3.setBounds(new Rectangle(612, 37, 270, 19));
        this.add(kDLabelContainer3, null);
        kDLabelContainer4.setBounds(new Rectangle(605, 67, 270, 19));
        this.add(kDLabelContainer4, null);
        contdeletedStatus.setBounds(new Rectangle(10, 141, 270, 19));
        this.add(contdeletedStatus, null);
        chkscheduled.setBounds(new Rectangle(293, 141, 270, 19));
        this.add(chkscheduled, null);
        contretailSaleType.setBounds(new Rectangle(10, 33, 270, 19));
        this.add(contretailSaleType, null);
        contretailDiscountRate.setBounds(new Rectangle(293, 33, 270, 19));
        this.add(contretailDiscountRate, null);
        contrepairDiscountRate.setBounds(new Rectangle(293, 64, 270, 19));
        this.add(contrepairDiscountRate, null);
        conttypeCode.setBounds(new Rectangle(10, 89, 270, 19));
        this.add(conttypeCode, null);
        contfinCustomer.setBounds(new Rectangle(293, 89, 270, 19));
        this.add(contfinCustomer, null);
        contrepairSaleType.setBounds(new Rectangle(10, 64, 270, 19));
        this.add(contrepairSaleType, null);
        contsettlementType.setBounds(new Rectangle(10, 115, 270, 19));
        this.add(contsettlementType, null);
        contatsCustomer.setBounds(new Rectangle(293, 115, 270, 19));
        this.add(contatsCustomer, null);
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
        //contretailSaleType
        contretailSaleType.setBoundEditor(txtretailSaleType);
        //contretailDiscountRate
        contretailDiscountRate.setBoundEditor(txtretailDiscountRate);
        //contrepairDiscountRate
        contrepairDiscountRate.setBoundEditor(txtrepairDiscountRate);
        //conttypeCode
        conttypeCode.setBoundEditor(txttypeCode);
        //contfinCustomer
        contfinCustomer.setBoundEditor(prmtfinCustomer);
        //contrepairSaleType
        contrepairSaleType.setBoundEditor(txtrepairSaleType);
        //contsettlementType
        contsettlementType.setBoundEditor(settlementType);
        //contatsCustomer
        contatsCustomer.setBoundEditor(prmtatsCustomer);

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
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnReset);
        this.toolBar.add(kDSeparatorCloud);
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
		dataBinder.registerBinding("retailSaleType", String.class, this.txtretailSaleType, "text");
		dataBinder.registerBinding("retailDiscountRate", java.math.BigDecimal.class, this.txtretailDiscountRate, "value");
		dataBinder.registerBinding("repairDiscountRate", java.math.BigDecimal.class, this.txtrepairDiscountRate, "value");
		dataBinder.registerBinding("typeCode", String.class, this.txttypeCode, "text");
		dataBinder.registerBinding("finCustomer", com.kingdee.eas.basedata.master.cssp.CustomerInfo.class, this.prmtfinCustomer, "data");
		dataBinder.registerBinding("repairSaleType", String.class, this.txtrepairSaleType, "text");
		dataBinder.registerBinding("settlementType", com.kingdee.eas.ga.rs.SettlementTypeEnum.class, this.settlementType, "selectedItem");
		dataBinder.registerBinding("atsCustomer", com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo.class, this.prmtatsCustomer, "data");		
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
	    return "com.kingdee.eas.ga.rs.app.CustomerAccountEditUIHandler";
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
        this.editData = (com.kingdee.eas.ga.rs.CustomerAccountInfo)ov;
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
	 * ????????У??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );
		getValidateHelper().registerBindProperty("scheduled", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("name", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("simpleName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("deletedStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("retailSaleType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("retailDiscountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("repairDiscountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("typeCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("finCustomer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("repairSaleType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("settlementType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("atsCustomer", ValidateHelper.ON_SAVE);    		
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
        sic.add(new SelectorItemInfo("retailSaleType"));
        sic.add(new SelectorItemInfo("retailDiscountRate"));
        sic.add(new SelectorItemInfo("repairDiscountRate"));
        sic.add(new SelectorItemInfo("typeCode"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("finCustomer.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("finCustomer.id"));
        	sic.add(new SelectorItemInfo("finCustomer.number"));
        	sic.add(new SelectorItemInfo("finCustomer.name"));
		}
        sic.add(new SelectorItemInfo("repairSaleType"));
        sic.add(new SelectorItemInfo("settlementType"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("atsCustomer.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("atsCustomer.id"));
        	sic.add(new SelectorItemInfo("atsCustomer.number"));
        	sic.add(new SelectorItemInfo("atsCustomer.name"));
		}
        return sic;
    }        

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.ga.rs.client", "CustomerAccountEditUI");
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.ga.rs.client.CustomerAccountEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.ga.rs.CustomerAccountFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.ga.rs.CustomerAccountInfo objectValue = new com.kingdee.eas.ga.rs.CustomerAccountInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


        
					protected void beforeStoreFields(ActionEvent arg0) throws Exception {
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtretailSaleType.getText())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"零售销售类型"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txttypeCode.getText())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"类别代码"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtrepairSaleType.getText())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"维修销售类型"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(settlementType.getSelectedItem())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"结算类型"});
		}
			super.beforeStoreFields(arg0);
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
		vo.put("retailDiscountRate",new java.math.BigDecimal(0));
		vo.put("repairDiscountRate",new java.math.BigDecimal(0));
vo.put("settlementType",new Integer(1));
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}