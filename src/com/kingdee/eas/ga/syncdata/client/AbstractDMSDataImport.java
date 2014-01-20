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
public abstract class AbstractDMSDataImport extends CoreUIObject
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractDMSDataImport.class);
    protected ResourceBundleHelper resHelper = null;
    protected com.kingdee.bos.ctrl.swing.KDToolBar DMSDataImport_toolbar;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer kDLabelContainer2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer labFile1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer labFile2;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer labFile3;
    protected com.kingdee.bos.ctrl.swing.KDButton btnBrower1;
    protected com.kingdee.bos.ctrl.swing.KDButton btnBrower3;
    protected com.kingdee.bos.ctrl.swing.KDButton btnBrower2;
    protected com.kingdee.bos.ctrl.swing.KDButton btnImport;
    protected com.kingdee.bos.ctrl.swing.KDContainer contMsgInfo;
    protected com.kingdee.bos.ctrl.swing.KDLabel labProgress;
    protected com.kingdee.bos.ctrl.swing.KDContainer contSpentInfo;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkShowSpent;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtServiceOrg;
    protected com.kingdee.bos.ctrl.swing.KDComboBox cmbImportType;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtFile1;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtFile2;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtFile3;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane kDScrollPane1;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtMsgInfo;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane kDScrollPane2;
    protected com.kingdee.bos.ctrl.swing.KDTextArea txtSpentInfo;
    /**
     * output class constructor
     */
    public AbstractDMSDataImport() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractDMSDataImport.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        this.toolBar = new com.kingdee.bos.ctrl.swing.KDToolBar();
        this.menuBar = new com.kingdee.bos.ctrl.swing.KDMenuBar();
        this.kDLabelContainer1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDLabelContainer2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.labFile1 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.labFile2 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.labFile3 = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.btnBrower1 = new com.kingdee.bos.ctrl.swing.KDButton();
        this.btnBrower3 = new com.kingdee.bos.ctrl.swing.KDButton();
        this.btnBrower2 = new com.kingdee.bos.ctrl.swing.KDButton();
        this.btnImport = new com.kingdee.bos.ctrl.swing.KDButton();
        this.contMsgInfo = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.labProgress = new com.kingdee.bos.ctrl.swing.KDLabel();
        this.contSpentInfo = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.chkShowSpent = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.prmtServiceOrg = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.cmbImportType = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtFile1 = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtFile2 = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtFile3 = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.kDScrollPane1 = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txtMsgInfo = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.kDScrollPane2 = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.txtSpentInfo = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.setName("DMSDataImport");
        this.toolBar.setName("DMSDataImport_toolbar");
        this.menuBar.setName("DMSDataImport_menubar");
        this.kDLabelContainer1.setName("kDLabelContainer1");
        this.kDLabelContainer2.setName("kDLabelContainer2");
        this.labFile1.setName("labFile1");
        this.labFile2.setName("labFile2");
        this.labFile3.setName("labFile3");
        this.btnBrower1.setName("btnBrower1");
        this.btnBrower3.setName("btnBrower3");
        this.btnBrower2.setName("btnBrower2");
        this.btnImport.setName("btnImport");
        this.contMsgInfo.setName("contMsgInfo");
        this.labProgress.setName("labProgress");
        this.contSpentInfo.setName("contSpentInfo");
        this.chkShowSpent.setName("chkShowSpent");
        this.prmtServiceOrg.setName("prmtServiceOrg");
        this.cmbImportType.setName("cmbImportType");
        this.txtFile1.setName("txtFile1");
        this.txtFile2.setName("txtFile2");
        this.txtFile3.setName("txtFile3");
        this.kDScrollPane1.setName("kDScrollPane1");
        this.txtMsgInfo.setName("txtMsgInfo");
        this.kDScrollPane2.setName("kDScrollPane2");
        this.txtSpentInfo.setName("txtSpentInfo");
        // DMSDataImport
        // DMSDataImport_toolbar
        // DMSDataImport_menubar
        // kDLabelContainer1		
        this.kDLabelContainer1.setBoundLabelText(resHelper.getString("kDLabelContainer1.boundLabelText"));		
        this.kDLabelContainer1.setBoundLabelLength(80);		
        this.kDLabelContainer1.setBoundLabelUnderline(true);
        // kDLabelContainer2		
        this.kDLabelContainer2.setBoundLabelText(resHelper.getString("kDLabelContainer2.boundLabelText"));		
        this.kDLabelContainer2.setBoundLabelLength(80);		
        this.kDLabelContainer2.setBoundLabelUnderline(true);
        // labFile1		
        this.labFile1.setBoundLabelText(resHelper.getString("labFile1.boundLabelText"));		
        this.labFile1.setBoundLabelLength(80);		
        this.labFile1.setBoundLabelUnderline(true);
        // labFile2		
        this.labFile2.setBoundLabelText(resHelper.getString("labFile2.boundLabelText"));		
        this.labFile2.setBoundLabelLength(80);		
        this.labFile2.setBoundLabelUnderline(true);
        // labFile3		
        this.labFile3.setBoundLabelText(resHelper.getString("labFile3.boundLabelText"));		
        this.labFile3.setBoundLabelLength(80);		
        this.labFile3.setBoundLabelUnderline(true);
        // btnBrower1		
        this.btnBrower1.setText(resHelper.getString("btnBrower1.text"));
        this.btnBrower1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnBrower1_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // btnBrower3		
        this.btnBrower3.setText(resHelper.getString("btnBrower3.text"));
        this.btnBrower3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnBrower3_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // btnBrower2		
        this.btnBrower2.setText(resHelper.getString("btnBrower2.text"));
        this.btnBrower2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnBrower2_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // btnImport		
        this.btnImport.setText(resHelper.getString("btnImport.text"));
        this.btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    btnImport_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // contMsgInfo		
        this.contMsgInfo.setTitle(resHelper.getString("contMsgInfo.title"));
        // labProgress		
        this.labProgress.setIcon(com.kingdee.eas.util.client.EASResource.getIcon("ico_mySearch"));
        // contSpentInfo		
        this.contSpentInfo.setTitle(resHelper.getString("contSpentInfo.title"));
        // chkShowSpent		
        this.chkShowSpent.setText(resHelper.getString("chkShowSpent.text"));		
        this.chkShowSpent.setMnemonic(68);
        this.chkShowSpent.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent e) {
                try {
                    chkShowSpent_stateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // prmtServiceOrg		
        this.prmtServiceOrg.setQueryInfo("com.kingdee.eas.auto4s.bdm.rsm.app.ServiceOrgUnitQuery");		
        this.prmtServiceOrg.setCommitFormat("$number$");		
        this.prmtServiceOrg.setEditFormat("$number$");		
        this.prmtServiceOrg.setDisplayFormat("$name$");		
        this.prmtServiceOrg.setRequired(true);
        // cmbImportType		
        this.cmbImportType.addItems(EnumUtils.getEnumList("com.kingdee.eas.ga.syncdata.DMSImpTypeEnum").toArray());		
        this.cmbImportType.setRequired(true);
        this.cmbImportType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                try {
                    cmbImportType_itemStateChanged(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                }
            }
        });
        // txtFile1		
        this.txtFile1.setEditable(false);		
        this.txtFile1.setRequired(true);
        this.txtFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                beforeActionPerformed(e);
                try {
                    txtFile1_actionPerformed(e);
                } catch (Exception exc) {
                    handUIException(exc);
                } finally {
                    afterActionPerformed(e);
                }
            }
        });
        // txtFile2		
        this.txtFile2.setEditable(false);		
        this.txtFile2.setRequired(true);
        // txtFile3		
        this.txtFile3.setEditable(false);		
        this.txtFile3.setRequired(true);
        // kDScrollPane1		
        this.kDScrollPane1.setAutoscrolls(true);
        // txtMsgInfo		
        this.txtMsgInfo.setEditable(false);
        // kDScrollPane2		
        this.kDScrollPane2.setAutoscrolls(true);
        // txtSpentInfo		
        this.txtSpentInfo.setEditable(false);
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
        this.setBounds(new Rectangle(10, 10, 1000, 600));
        this.setLayout(null);
        kDLabelContainer1.setBounds(new Rectangle(15, 32, 242, 18));
        this.add(kDLabelContainer1, null);
        kDLabelContainer2.setBounds(new Rectangle(15, 62, 242, 18));
        this.add(kDLabelContainer2, null);
        labFile1.setBounds(new Rectangle(15, 92, 482, 18));
        this.add(labFile1, null);
        labFile2.setBounds(new Rectangle(15, 122, 483, 18));
        this.add(labFile2, null);
        labFile3.setBounds(new Rectangle(15, 152, 483, 18));
        this.add(labFile3, null);
        btnBrower1.setBounds(new Rectangle(506, 92, 22, 18));
        this.add(btnBrower1, null);
        btnBrower3.setBounds(new Rectangle(506, 152, 22, 18));
        this.add(btnBrower3, null);
        btnBrower2.setBounds(new Rectangle(506, 122, 22, 18));
        this.add(btnBrower2, null);
        btnImport.setBounds(new Rectangle(326, 62, 98, 21));
        this.add(btnImport, null);
        contMsgInfo.setBounds(new Rectangle(16, 177, 540, 399));
        this.add(contMsgInfo, null);
        labProgress.setBounds(new Rectangle(431, 61, 99, 23));
        this.add(labProgress, null);
        contSpentInfo.setBounds(new Rectangle(574, 177, 407, 396));
        this.add(contSpentInfo, null);
        chkShowSpent.setBounds(new Rectangle(326, 31, 101, 19));
        this.add(chkShowSpent, null);
        //kDLabelContainer1
        kDLabelContainer1.setBoundEditor(prmtServiceOrg);
        //kDLabelContainer2
        kDLabelContainer2.setBoundEditor(cmbImportType);
        //labFile1
        labFile1.setBoundEditor(txtFile1);
        //labFile2
        labFile2.setBoundEditor(txtFile2);
        //labFile3
        labFile3.setBoundEditor(txtFile3);
        //contMsgInfo
contMsgInfo.getContentPane().setLayout(new BorderLayout(0, 0));        contMsgInfo.getContentPane().add(kDScrollPane1, BorderLayout.CENTER);
        //kDScrollPane1
        kDScrollPane1.getViewport().add(txtMsgInfo, null);
        //contSpentInfo
contSpentInfo.getContentPane().setLayout(new BorderLayout(0, 0));        contSpentInfo.getContentPane().add(kDScrollPane2, BorderLayout.CENTER);
        //kDScrollPane2
        kDScrollPane2.getViewport().add(txtSpentInfo, null);

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
	    return "com.kingdee.eas.ga.syncdata.app.DMSDataImportHandler";
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
     * output btnBrower1_actionPerformed method
     */
    protected void btnBrower1_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnBrower3_actionPerformed method
     */
    protected void btnBrower3_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnBrower2_actionPerformed method
     */
    protected void btnBrower2_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output btnImport_actionPerformed method
     */
    protected void btnImport_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
    }

    /**
     * output chkShowSpent_stateChanged method
     */
    protected void chkShowSpent_stateChanged(javax.swing.event.ChangeEvent e) throws Exception
    {
    }

    /**
     * output cmbImportType_itemStateChanged method
     */
    protected void cmbImportType_itemStateChanged(java.awt.event.ItemEvent e) throws Exception
    {
    }

    /**
     * output txtFile1_actionPerformed method
     */
    protected void txtFile1_actionPerformed(java.awt.event.ActionEvent e) throws Exception
    {
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
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.ga.syncdata.client", "DMSDataImport");
    }




}