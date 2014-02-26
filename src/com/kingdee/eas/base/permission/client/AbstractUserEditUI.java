/**
 * output package name
 */
package com.kingdee.eas.base.permission.client;

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
public abstract class AbstractUserEditUI extends com.kingdee.eas.framework.client.CoreUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractUserEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcNumber;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcUserRelation;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox pmtUserRelation;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcGroup;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox pmtGroup;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcInuseDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker dpkInuseDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcInvaildDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker dpkInvaildDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcSecurity;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbSecurity;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcPWInvaildDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker dpkPWInvaildDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcType;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbUserType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcPWEffectiveDate;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker dpkPWEffectiveDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcPassword;
    protected com.kingdee.bos.ctrl.swing.KDPasswordField pwdPassword;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcDefLang;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbDefLang;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcConfirmPassword;
    protected com.kingdee.bos.ctrl.swing.KDPasswordField pwdConfirmPassword;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangArea mlaDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabel lblDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcCU;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkIsBizAdmin;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnAddNew;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnSubmit;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton btnPassword;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangBox mlbUserRelation;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox txtCU;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lblPwdAuthorWay;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbPwdAuthorWay;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer labEmail;
    protected com.kingdee.bos.ctrl.swing.KDFilterTextField txtCell;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtEmail;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer labCell;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer labOfficePhone;
    protected com.kingdee.bos.ctrl.swing.KDFilterTextField txtOfficePhone;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lablBackEmail;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtBackEmail;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer labHomePhone;
    protected com.kingdee.bos.ctrl.swing.KDFilterTextField txtHomePhone;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcDefaultOrg;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox pmtDefaultOrg;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer lbcAdNumber;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtAdNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contuserType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contmaxRepairDiscountRate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contmaxRetailDiscountRate;
    protected com.kingdee.bos.ctrl.swing.KDComboBox userType;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtmaxRepairDiscountRate;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtmaxRetailDiscountRate;
    protected com.kingdee.eas.base.permission.UserInfo entityUser = null;
    protected ActionAddNew actionAddNew = null;
    protected ActionSubmit actionSubmit = null;
    protected ActionPassword actionPassword = null;
    /**
     * output class constructor
     */
    public AbstractUserEditUI() throws Exception
    {
        super();
        this.defaultObjectName = "entityUser";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractUserEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionAddNew
        this.actionAddNew = new ActionAddNew(this);
        getActionManager().registerAction("actionAddNew", actionAddNew);
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionAddNew.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionSubmit
        this.actionSubmit = new ActionSubmit(this);
        getActionManager().registerAction("actionSubmit", actionSubmit);
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPassword
        this.actionPassword = new ActionPassword(this);
        getActionManager().registerAction("actionPassword", actionPassword);
         this.actionPassword.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPassword.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPassword.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        this.lbcNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.lbcUserRelation = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.pmtUserRelation = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.lbcGroup = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.pmtGroup = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.lbcInuseDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.dpkInuseDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.lbcInvaildDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.dpkInvaildDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.lbcSecurity = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.cmbSecurity = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.lbcPWInvaildDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.dpkPWInvaildDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.lbcType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.cmbUserType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.lbcPWEffectiveDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.dpkPWEffectiveDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.lbcPassword = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.pwdPassword = new com.kingdee.bos.ctrl.swing.KDPasswordField();
        this.lbcDefLang = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.cmbDefLang = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.lbcConfirmPassword = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.pwdConfirmPassword = new com.kingdee.bos.ctrl.swing.KDPasswordField();
        this.mlaDescription = new com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangArea();
        this.lblDescription = new com.kingdee.bos.ctrl.swing.KDLabel();
        this.lbcCU = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkIsBizAdmin = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.btnAddNew = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnSubmit = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.btnPassword = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.mlbUserRelation = new com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangBox();
        this.txtCU = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.lblPwdAuthorWay = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.cmbPwdAuthorWay = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.labEmail = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtCell = new com.kingdee.bos.ctrl.swing.KDFilterTextField();
        this.txtEmail = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.labCell = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.labOfficePhone = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtOfficePhone = new com.kingdee.bos.ctrl.swing.KDFilterTextField();
        this.lablBackEmail = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtBackEmail = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.labHomePhone = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtHomePhone = new com.kingdee.bos.ctrl.swing.KDFilterTextField();
        this.lbcDefaultOrg = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.pmtDefaultOrg = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.lbcAdNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtAdNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.contuserType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contmaxRepairDiscountRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contmaxRetailDiscountRate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.userType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtmaxRepairDiscountRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.txtmaxRetailDiscountRate = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.lbcNumber.setName("lbcNumber");
        this.txtNumber.setName("txtNumber");
        this.lbcUserRelation.setName("lbcUserRelation");
        this.pmtUserRelation.setName("pmtUserRelation");
        this.lbcGroup.setName("lbcGroup");
        this.pmtGroup.setName("pmtGroup");
        this.lbcInuseDate.setName("lbcInuseDate");
        this.dpkInuseDate.setName("dpkInuseDate");
        this.lbcInvaildDate.setName("lbcInvaildDate");
        this.dpkInvaildDate.setName("dpkInvaildDate");
        this.lbcSecurity.setName("lbcSecurity");
        this.cmbSecurity.setName("cmbSecurity");
        this.lbcPWInvaildDate.setName("lbcPWInvaildDate");
        this.dpkPWInvaildDate.setName("dpkPWInvaildDate");
        this.lbcType.setName("lbcType");
        this.cmbUserType.setName("cmbUserType");
        this.lbcPWEffectiveDate.setName("lbcPWEffectiveDate");
        this.dpkPWEffectiveDate.setName("dpkPWEffectiveDate");
        this.lbcPassword.setName("lbcPassword");
        this.pwdPassword.setName("pwdPassword");
        this.lbcDefLang.setName("lbcDefLang");
        this.cmbDefLang.setName("cmbDefLang");
        this.lbcConfirmPassword.setName("lbcConfirmPassword");
        this.pwdConfirmPassword.setName("pwdConfirmPassword");
        this.mlaDescription.setName("mlaDescription");
        this.lblDescription.setName("lblDescription");
        this.lbcCU.setName("lbcCU");
        this.chkIsBizAdmin.setName("chkIsBizAdmin");
        this.btnAddNew.setName("btnAddNew");
        this.btnSubmit.setName("btnSubmit");
        this.btnPassword.setName("btnPassword");
        this.mlbUserRelation.setName("mlbUserRelation");
        this.txtCU.setName("txtCU");
        this.lblPwdAuthorWay.setName("lblPwdAuthorWay");
        this.cmbPwdAuthorWay.setName("cmbPwdAuthorWay");
        this.labEmail.setName("labEmail");
        this.txtCell.setName("txtCell");
        this.txtEmail.setName("txtEmail");
        this.labCell.setName("labCell");
        this.labOfficePhone.setName("labOfficePhone");
        this.txtOfficePhone.setName("txtOfficePhone");
        this.lablBackEmail.setName("lablBackEmail");
        this.txtBackEmail.setName("txtBackEmail");
        this.labHomePhone.setName("labHomePhone");
        this.txtHomePhone.setName("txtHomePhone");
        this.lbcDefaultOrg.setName("lbcDefaultOrg");
        this.pmtDefaultOrg.setName("pmtDefaultOrg");
        this.lbcAdNumber.setName("lbcAdNumber");
        this.txtAdNumber.setName("txtAdNumber");
        this.contuserType.setName("contuserType");
        this.contmaxRepairDiscountRate.setName("contmaxRepairDiscountRate");
        this.contmaxRetailDiscountRate.setName("contmaxRetailDiscountRate");
        this.userType.setName("userType");
        this.txtmaxRepairDiscountRate.setName("txtmaxRepairDiscountRate");
        this.txtmaxRetailDiscountRate.setName("txtmaxRetailDiscountRate");
        // CoreUI
        // lbcNumber		
        this.lbcNumber.setBoundLabelText(resHelper.getString("lbcNumber.boundLabelText"));		
        this.lbcNumber.setBoundLabelLength(100);		
        this.lbcNumber.setBoundLabelUnderline(true);
        // txtNumber		
        this.txtNumber.setText(resHelper.getString("txtNumber.text"));		
        this.txtNumber.setMaxLength(80);		
        this.txtNumber.setRequired(true);
        // lbcUserRelation		
        this.lbcUserRelation.setBoundLabelText(resHelper.getString("lbcUserRelation.boundLabelText"));		
        this.lbcUserRelation.setBoundLabelLength(100);		
        this.lbcUserRelation.setBoundLabelUnderline(true);
        // pmtUserRelation		
        this.pmtUserRelation.setEditable(true);		
        this.pmtUserRelation.setRequired(true);
        this.pmtUserRelation.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    pmtUserRelation_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // lbcGroup		
        this.lbcGroup.setBoundLabelText(resHelper.getString("lbcGroup.boundLabelText"));		
        this.lbcGroup.setBoundLabelLength(100);		
        this.lbcGroup.setBoundLabelUnderline(true);
        // pmtGroup		
        this.pmtGroup.setDisplayFormat("$name$");		
        this.pmtGroup.setEditFormat("$number$");		
        this.pmtGroup.setCommitFormat("$number$");		
        this.pmtGroup.setQueryInfo("com.kingdee.eas.base.permission.app.UserGroupQuery");		
        this.pmtGroup.setEditable(true);
        // lbcInuseDate		
        this.lbcInuseDate.setBoundLabelText(resHelper.getString("lbcInuseDate.boundLabelText"));		
        this.lbcInuseDate.setBoundLabelLength(100);		
        this.lbcInuseDate.setBoundLabelUnderline(true);
        // dpkInuseDate
        // lbcInvaildDate		
        this.lbcInvaildDate.setBoundLabelText(resHelper.getString("lbcInvaildDate.boundLabelText"));		
        this.lbcInvaildDate.setBoundLabelLength(100);		
        this.lbcInvaildDate.setBoundLabelUnderline(true);
        // dpkInvaildDate
        // lbcSecurity		
        this.lbcSecurity.setBoundLabelText(resHelper.getString("lbcSecurity.boundLabelText"));		
        this.lbcSecurity.setBoundLabelLength(100);		
        this.lbcSecurity.setBoundLabelUnderline(true);
        // cmbSecurity		
        this.cmbSecurity.setRequired(true);
        this.cmbSecurity.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    cmbSecurity_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        this.cmbSecurity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                try {
                    cmbSecurity_mouseEntered(e);
                } catch(Exception exc) {
                    handUIException(exc);
                }
            }
        });
        // lbcPWInvaildDate		
        this.lbcPWInvaildDate.setBoundLabelText(resHelper.getString("lbcPWInvaildDate.boundLabelText"));		
        this.lbcPWInvaildDate.setBoundLabelLength(100);		
        this.lbcPWInvaildDate.setBoundLabelUnderline(true);
        // dpkPWInvaildDate
        this.dpkPWInvaildDate.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    dpkPWInvaildDate_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // lbcType		
        this.lbcType.setBoundLabelText(resHelper.getString("lbcType.boundLabelText"));		
        this.lbcType.setBoundLabelLength(100);		
        this.lbcType.setBoundLabelUnderline(true);
        // cmbUserType		
        this.cmbUserType.addItems(resHelper.getArray("cmbUserType.items"));		
        this.cmbUserType.setRequired(true);
        this.cmbUserType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    cmbUserType_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // lbcPWEffectiveDate		
        this.lbcPWEffectiveDate.setBoundLabelText(resHelper.getString("lbcPWEffectiveDate.boundLabelText"));		
        this.lbcPWEffectiveDate.setBoundLabelLength(100);		
        this.lbcPWEffectiveDate.setBoundLabelUnderline(true);
        // dpkPWEffectiveDate
        this.dpkPWEffectiveDate.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    dpkPWEffectiveDate_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // lbcPassword		
        this.lbcPassword.setBoundLabelText(resHelper.getString("lbcPassword.boundLabelText"));		
        this.lbcPassword.setBoundLabelLength(100);		
        this.lbcPassword.setBoundLabelUnderline(true);
        // pwdPassword		
        this.pwdPassword.setText(resHelper.getString("pwdPassword.text"));
        // lbcDefLang		
        this.lbcDefLang.setBoundLabelText(resHelper.getString("lbcDefLang.boundLabelText"));		
        this.lbcDefLang.setBoundLabelLength(100);		
        this.lbcDefLang.setBoundLabelUnderline(true);
        // cmbDefLang		
        this.cmbDefLang.addItems(EnumUtils.getEnumList("com.kingdee.eas.base.permission.Locale").toArray());
        // lbcConfirmPassword		
        this.lbcConfirmPassword.setBoundLabelText(resHelper.getString("lbcConfirmPassword.boundLabelText"));		
        this.lbcConfirmPassword.setBoundLabelLength(100);		
        this.lbcConfirmPassword.setBoundLabelUnderline(true);
        // pwdConfirmPassword		
        this.pwdConfirmPassword.setText(resHelper.getString("pwdConfirmPassword.text"));
        // mlaDescription		
        this.mlaDescription.setMaxLength(80);
        // lblDescription		
        this.lblDescription.setText(resHelper.getString("lblDescription.text"));
        // lbcCU		
        this.lbcCU.setBoundLabelText(resHelper.getString("lbcCU.boundLabelText"));		
        this.lbcCU.setBoundLabelLength(100);		
        this.lbcCU.setBoundLabelUnderline(true);
        // chkIsBizAdmin		
        this.chkIsBizAdmin.setText(resHelper.getString("chkIsBizAdmin.text"));
        this.chkIsBizAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    chkIsBizAdmin_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // btnAddNew
        this.btnAddNew.setAction((IItemAction)ActionProxyFactory.getProxy(actionAddNew, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnAddNew.setText(resHelper.getString("btnAddNew.text"));		
        this.btnAddNew.setToolTipText(resHelper.getString("btnAddNew.toolTipText"));
        // btnSubmit
        this.btnSubmit.setAction((IItemAction)ActionProxyFactory.getProxy(actionSubmit, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnSubmit.setText(resHelper.getString("btnSubmit.text"));		
        this.btnSubmit.setToolTipText(resHelper.getString("btnSubmit.toolTipText"));
        // btnPassword
        this.btnPassword.setAction((IItemAction)ActionProxyFactory.getProxy(actionPassword, new Class[] { IItemAction.class }, getServiceContext()));		
        this.btnPassword.setText(resHelper.getString("btnPassword.text"));		
        this.btnPassword.setToolTipText(resHelper.getString("btnPassword.toolTipText"));
        // mlbUserRelation		
        this.mlbUserRelation.setMaxLength(80);
        // txtCU		
        this.txtCU.setEditable(true);		
        this.txtCU.setRequired(true);
        this.txtCU.addDataChangeListener(new com.kingdee.bos.ctrl.swing.event.DataChangeListener() {
            public void dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) {
                try {
                    txtCU_dataChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // lblPwdAuthorWay		
        this.lblPwdAuthorWay.setBoundLabelText(resHelper.getString("lblPwdAuthorWay.boundLabelText"));		
        this.lblPwdAuthorWay.setBoundLabelLength(100);		
        this.lblPwdAuthorWay.setBoundLabelUnderline(true);
        // cmbPwdAuthorWay
        // labEmail		
        this.labEmail.setBoundLabelText(resHelper.getString("labEmail.boundLabelText"));		
        this.labEmail.setBoundLabelUnderline(true);		
        this.labEmail.setBoundLabelLength(100);
        // txtCell		
        this.txtCell.setText(resHelper.getString("txtCell.text"));		
        this.txtCell.setFilterType(1);		
        this.txtCell.setMaxLength(50);		
        this.txtCell.setValidCharacters("+-()");
        // txtEmail		
        this.txtEmail.setMaxLength(100);
        // labCell		
        this.labCell.setBoundLabelText(resHelper.getString("labCell.boundLabelText"));		
        this.labCell.setBoundLabelUnderline(true);		
        this.labCell.setBoundLabelLength(100);
        // labOfficePhone		
        this.labOfficePhone.setBoundLabelText(resHelper.getString("labOfficePhone.boundLabelText"));		
        this.labOfficePhone.setBoundLabelUnderline(true);		
        this.labOfficePhone.setBoundLabelLength(100);
        // txtOfficePhone		
        this.txtOfficePhone.setText(resHelper.getString("txtOfficePhone.text"));		
        this.txtOfficePhone.setFilterType(1);		
        this.txtOfficePhone.setMaxLength(50);		
        this.txtOfficePhone.setValidCharacters("+-()");
        // lablBackEmail		
        this.lablBackEmail.setBoundLabelText(resHelper.getString("lablBackEmail.boundLabelText"));		
        this.lablBackEmail.setBoundLabelUnderline(true);		
        this.lablBackEmail.setBoundLabelLength(100);
        // txtBackEmail		
        this.txtBackEmail.setMaxLength(100);
        // labHomePhone		
        this.labHomePhone.setBoundLabelText(resHelper.getString("labHomePhone.boundLabelText"));		
        this.labHomePhone.setBoundLabelUnderline(true);		
        this.labHomePhone.setBoundLabelLength(100);
        // txtHomePhone		
        this.txtHomePhone.setText(resHelper.getString("txtHomePhone.text"));		
        this.txtHomePhone.setValidCharacters("+-()");		
        this.txtHomePhone.setFilterType(1);		
        this.txtHomePhone.setMaxLength(50);
        // lbcDefaultOrg		
        this.lbcDefaultOrg.setBoundLabelText(resHelper.getString("lbcDefaultOrg.boundLabelText"));		
        this.lbcDefaultOrg.setBoundLabelLength(100);		
        this.lbcDefaultOrg.setBoundLabelUnderline(true);
        // pmtDefaultOrg
        // lbcAdNumber		
        this.lbcAdNumber.setBoundLabelText(resHelper.getString("lbcAdNumber.boundLabelText"));		
        this.lbcAdNumber.setBoundLabelLength(100);		
        this.lbcAdNumber.setBoundLabelUnderline(true);
        // txtAdNumber
        // contuserType		
        this.contuserType.setBoundLabelText(resHelper.getString("contuserType.boundLabelText"));		
        this.contuserType.setBoundLabelLength(100);		
        this.contuserType.setBoundLabelUnderline(true);		
        this.contuserType.setVisible(true);
        // contmaxRepairDiscountRate		
        this.contmaxRepairDiscountRate.setBoundLabelText(resHelper.getString("contmaxRepairDiscountRate.boundLabelText"));		
        this.contmaxRepairDiscountRate.setBoundLabelLength(100);		
        this.contmaxRepairDiscountRate.setBoundLabelUnderline(true);		
        this.contmaxRepairDiscountRate.setVisible(true);
        // contmaxRetailDiscountRate		
        this.contmaxRetailDiscountRate.setBoundLabelText(resHelper.getString("contmaxRetailDiscountRate.boundLabelText"));		
        this.contmaxRetailDiscountRate.setBoundLabelLength(100);		
        this.contmaxRetailDiscountRate.setBoundLabelUnderline(true);		
        this.contmaxRetailDiscountRate.setVisible(true);
        // userType		
        this.userType.setVisible(true);		
        this.userType.setRequired(false);		
        this.userType.addItems(EnumUtils.getEnumList("com.kingdee.eas.ga.basedata.UserTypeEnum").toArray());
        // txtmaxRepairDiscountRate		
        this.txtmaxRepairDiscountRate.setVisible(true);		
        this.txtmaxRepairDiscountRate.setHorizontalAlignment(2);		
        this.txtmaxRepairDiscountRate.setDataType(1);		
        this.txtmaxRepairDiscountRate.setSupportedEmpty(true);		
        this.txtmaxRepairDiscountRate.setMinimumValue( new java.math.BigDecimal("-999.99"));		
        this.txtmaxRepairDiscountRate.setMaximumValue( new java.math.BigDecimal("999.99"));		
        this.txtmaxRepairDiscountRate.setPrecision(2);		
        this.txtmaxRepairDiscountRate.setRequired(false);
        // txtmaxRetailDiscountRate		
        this.txtmaxRetailDiscountRate.setVisible(true);		
        this.txtmaxRetailDiscountRate.setHorizontalAlignment(2);		
        this.txtmaxRetailDiscountRate.setDataType(1);		
        this.txtmaxRetailDiscountRate.setSupportedEmpty(true);		
        this.txtmaxRetailDiscountRate.setMinimumValue( new java.math.BigDecimal("-999.99"));		
        this.txtmaxRetailDiscountRate.setMaximumValue( new java.math.BigDecimal("999.99"));		
        this.txtmaxRetailDiscountRate.setPrecision(2);		
        this.txtmaxRetailDiscountRate.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {userType,txtmaxRepairDiscountRate}));
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
        this.setBounds(new Rectangle(10, 10, 550, 458));
        this.setLayout(null);
        lbcNumber.setBounds(new Rectangle(10, 10, 250, 19));
        this.add(lbcNumber, null);
        lbcUserRelation.setBounds(new Rectangle(10, 54, 250, 19));
        this.add(lbcUserRelation, null);
        lbcGroup.setBounds(new Rectangle(290, 10, 250, 19));
        this.add(lbcGroup, null);
        lbcInuseDate.setBounds(new Rectangle(10, 76, 250, 19));
        this.add(lbcInuseDate, null);
        lbcInvaildDate.setBounds(new Rectangle(290, 76, 250, 19));
        this.add(lbcInvaildDate, null);
        lbcSecurity.setBounds(new Rectangle(290, 54, 250, 19));
        this.add(lbcSecurity, null);
        lbcPWInvaildDate.setBounds(new Rectangle(290, 142, 250, 19));
        this.add(lbcPWInvaildDate, null);
        lbcType.setBounds(new Rectangle(10, 32, 250, 19));
        this.add(lbcType, null);
        lbcPWEffectiveDate.setBounds(new Rectangle(10, 142, 250, 19));
        this.add(lbcPWEffectiveDate, null);
        lbcPassword.setBounds(new Rectangle(10, 120, 250, 19));
        this.add(lbcPassword, null);
        lbcDefLang.setBounds(new Rectangle(290, 98, 250, 19));
        this.add(lbcDefLang, null);
        lbcConfirmPassword.setBounds(new Rectangle(290, 120, 250, 19));
        this.add(lbcConfirmPassword, null);
        mlaDescription.setBounds(new Rectangle(10, 332, 530, 98));
        this.add(mlaDescription, null);
        lblDescription.setBounds(new Rectangle(10, 310, 100, 19));
        this.add(lblDescription, null);
        lbcCU.setBounds(new Rectangle(290, 32, 250, 19));
        this.add(lbcCU, null);
        chkIsBizAdmin.setBounds(new Rectangle(10, 98, 140, 19));
        this.add(chkIsBizAdmin, null);
        mlbUserRelation.setBounds(new Rectangle(110, 54, 150, 19));
        this.add(mlbUserRelation, null);
        lblPwdAuthorWay.setBounds(new Rectangle(10, 164, 250, 19));
        this.add(lblPwdAuthorWay, null);
        labEmail.setBounds(new Rectangle(10, 186, 250, 19));
        this.add(labEmail, null);
        labCell.setBounds(new Rectangle(290, 164, 249, 19));
        this.add(labCell, null);
        labOfficePhone.setBounds(new Rectangle(290, 186, 249, 19));
        this.add(labOfficePhone, null);
        lablBackEmail.setBounds(new Rectangle(10, 208, 250, 19));
        this.add(lablBackEmail, null);
        labHomePhone.setBounds(new Rectangle(290, 208, 250, 19));
        this.add(labHomePhone, null);
        lbcDefaultOrg.setBounds(new Rectangle(10, 230, 250, 19));
        this.add(lbcDefaultOrg, null);
        lbcAdNumber.setBounds(new Rectangle(290, 230, 250, 19));
        this.add(lbcAdNumber, null);
        contuserType.setBounds(new Rectangle(10, 253, 250, 19));
        this.add(contuserType, null);
        contmaxRepairDiscountRate.setBounds(new Rectangle(10, 279, 250, 19));
        this.add(contmaxRepairDiscountRate, null);
        contmaxRetailDiscountRate.setBounds(new Rectangle(290, 279, 250, 19));
        this.add(contmaxRetailDiscountRate, null);
        //lbcNumber
        lbcNumber.setBoundEditor(txtNumber);
        //lbcUserRelation
        lbcUserRelation.setBoundEditor(pmtUserRelation);
        //lbcGroup
        lbcGroup.setBoundEditor(pmtGroup);
        //lbcInuseDate
        lbcInuseDate.setBoundEditor(dpkInuseDate);
        //lbcInvaildDate
        lbcInvaildDate.setBoundEditor(dpkInvaildDate);
        //lbcSecurity
        lbcSecurity.setBoundEditor(cmbSecurity);
        //lbcPWInvaildDate
        lbcPWInvaildDate.setBoundEditor(dpkPWInvaildDate);
        //lbcType
        lbcType.setBoundEditor(cmbUserType);
        //lbcPWEffectiveDate
        lbcPWEffectiveDate.setBoundEditor(dpkPWEffectiveDate);
        //lbcPassword
        lbcPassword.setBoundEditor(pwdPassword);
        //lbcDefLang
        lbcDefLang.setBoundEditor(cmbDefLang);
        //lbcConfirmPassword
        lbcConfirmPassword.setBoundEditor(pwdConfirmPassword);
        //lbcCU
        lbcCU.setBoundEditor(txtCU);
        //lblPwdAuthorWay
        lblPwdAuthorWay.setBoundEditor(cmbPwdAuthorWay);
        //labEmail
        labEmail.setBoundEditor(txtEmail);
        //labCell
        labCell.setBoundEditor(txtCell);
        //labOfficePhone
        labOfficePhone.setBoundEditor(txtOfficePhone);
        //lablBackEmail
        lablBackEmail.setBoundEditor(txtBackEmail);
        //labHomePhone
        labHomePhone.setBoundEditor(txtHomePhone);
        //lbcDefaultOrg
        lbcDefaultOrg.setBoundEditor(pmtDefaultOrg);
        //lbcAdNumber
        lbcAdNumber.setBoundEditor(txtAdNumber);
        //contuserType
        contuserType.setBoundEditor(userType);
        //contmaxRepairDiscountRate
        contmaxRepairDiscountRate.setBoundEditor(txtmaxRepairDiscountRate);
        //contmaxRetailDiscountRate
        contmaxRetailDiscountRate.setBoundEditor(txtmaxRetailDiscountRate);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuTool);
        this.menuBar.add(MenuService);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemPageSetup);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(menuItemCloudShare);
        menuFile.add(kdSeparatorFWFile1);
        menuFile.add(menuItemExitCurrent);
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
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnCloud);
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnPassword);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("maxRepairDiscountRate", java.math.BigDecimal.class, this.txtmaxRepairDiscountRate, "value");
		dataBinder.registerBinding("maxRetailDiscountRate", java.math.BigDecimal.class, this.txtmaxRetailDiscountRate, "value");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.base.permission.app.UserEditUIHandler";
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
        this.userType.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.entityUser = (com.kingdee.eas.base.permission.UserInfo)ov;
    }
			protected com.kingdee.eas.basedata.org.OrgType getMainBizOrgType() {
			return com.kingdee.eas.basedata.org.OrgType.getEnum("ControlUnit");
		}


    /**
     * output loadFields method
     */
    public void loadFields()
    {
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
		getValidateHelper().registerBindProperty("maxRepairDiscountRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("maxRetailDiscountRate", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
    }

    /**
     * output pmtUserRelation_dataChanged method
     */
    protected void pmtUserRelation_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output cmbSecurity_itemStateChanged method
     */
    protected void cmbSecurity_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output cmbSecurity_mouseEntered method
     */
    protected void cmbSecurity_mouseEntered(java.awt.event.MouseEvent e) throws Exception
    {
    }

    /**
     * output dpkPWInvaildDate_dataChanged method
     */
    protected void dpkPWInvaildDate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output cmbUserType_itemStateChanged method
     */
    protected void cmbUserType_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output dpkPWEffectiveDate_dataChanged method
     */
    protected void dpkPWEffectiveDate_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
    {
    }

    /**
     * output chkIsBizAdmin_actionPerformed method
     */
    protected void chkIsBizAdmin_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
        //write your code here
    }

    /**
     * output txtCU_dataChanged method
     */
    protected void txtCU_dataChanged(com.kingdee.bos.ctrl.swing.event.DataChangeEvent e) throws Exception
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
        sic.add(new SelectorItemInfo("maxRepairDiscountRate"));
        sic.add(new SelectorItemInfo("maxRetailDiscountRate"));
        return sic;
    }        
    	

    /**
     * output actionAddNew_actionPerformed method
     */
    public void actionAddNew_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionSubmit_actionPerformed method
     */
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception
    {
    }
    	

    /**
     * output actionPassword_actionPerformed method
     */
    public void actionPassword_actionPerformed(ActionEvent e) throws Exception
    {
    }
	public RequestContext prepareActionAddNew(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionAddNew() {
    	return false;
    }
	public RequestContext prepareActionSubmit(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSubmit() {
    	return false;
    }
	public RequestContext prepareActionPassword(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPassword() {
    	return false;
    }

    /**
     * output ActionAddNew class
     */     
    protected class ActionAddNew extends ItemAction {     
    
        public ActionAddNew()
        {
            this(null);
        }

        public ActionAddNew(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionAddNew.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddNew.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionAddNew.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractUserEditUI.this, "ActionAddNew", "actionAddNew_actionPerformed", e);
        }
    }

    /**
     * output ActionSubmit class
     */     
    protected class ActionSubmit extends ItemAction {     
    
        public ActionSubmit()
        {
            this(null);
        }

        public ActionSubmit(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionSubmit.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractUserEditUI.this, "ActionSubmit", "actionSubmit_actionPerformed", e);
        }
    }

    /**
     * output ActionPassword class
     */     
    protected class ActionPassword extends ItemAction {     
    
        public ActionPassword()
        {
            this(null);
        }

        public ActionPassword(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionPassword.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionPassword.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionPassword.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractUserEditUI.this, "ActionPassword", "actionPassword_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.base.permission.client", "UserEditUI");
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.base.permission.client.UserEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.base.permission.UserFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.base.permission.UserInfo objectValue = new com.kingdee.eas.base.permission.UserInfo();
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
				vo.put("maxRepairDiscountRate",new java.math.BigDecimal(0));
		vo.put("maxRetailDiscountRate",new java.math.BigDecimal(0));
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}