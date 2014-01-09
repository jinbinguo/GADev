/**
 * output package name
 */
package com.kingdee.eas.myframework.msgengine.client;

import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.CryptoUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.vo.RequireCompCollection;
import com.kingdee.eas.util.SysUtil;

public class MailServerConfigEditUI extends AbstractMailServerConfigEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(MailServerConfigEditUI.class);
    
    private CryptoUtils cryptoUtils = new CryptoUtils(PublicUtils.CRYPTO_KEY);
    private boolean needCheckPwd = false;

    public MailServerConfigEditUI() throws Exception {
		super();
	}
    
    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	txtPwd.setBackground(UIManager.getColor("TextField.requiredBackground"));
    	txtRePwd.setBackground(UIManager.getColor("TextField.requiredBackground"));
    }
    
    @Override
    public void storeFields() {
    	super.storeFields();
    	try {
			editData.setPassword(cryptoUtils.encrypt(String.valueOf(txtPwd.getPassword())));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void loadFields() {
    	super.loadFields();
    	try {
			txtPwd.setText(cryptoUtils.decrypt(editData.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
  
    @Override
    public RequireCompCollection registerRequireComp() throws Exception {
    	RequireCompCollection reqCompCol = super.registerRequireComp();
    	reqCompCol.add(txthost);
    	reqCompCol.add(txtport);
    	reqCompCol.add(txtprotocol);
    	reqCompCol.add(txtusername);
    	return reqCompCol;
    }

    @Override  
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
    	/** 检查两次密码是否一致*/
    	needCheckPwd = true;
    	super.actionSubmit_actionPerformed(e);
    	needCheckPwd = false;
    	
    }
    
    @Override
    protected void verifyInput(ActionEvent e) throws Exception {
    	super.verifyInput(e);
    	if (needCheckPwd) {
    		if (txtPwd.getPassword().length == 0) {
    			MsgBoxEx.showInfo("密码不能为空");
    			txtPwd.requestFocus();
    			SysUtil.abort();
    		}
    		
    		if (!PublicUtils.equals(String.valueOf(txtPwd.getPassword()),String.valueOf(txtRePwd.getPassword()))) {
    			MsgBoxEx.showInfo("两次密码不一致！");
    			txtPwd.setText("");
    			txtPwd.requestFocus();
    			txtRePwd.setText("");
    			SysUtil.abort();
    		}
    		
    	}
    }
    @Override
    protected void unLockUI() {
    	super.unLockUI();
    	txtPwd.setEditable(true);
		txtRePwd.setEditable(true);
    }
    @Override
    protected void lockUIForViewStatus() {
    	super.lockUIForViewStatus();
    	txtPwd.setEditable(false);
		txtRePwd.setEditable(false);
    }
    
    protected void createNewDataEx() throws Exception {
    	super.createNewDataEx();
    	editData.setPort(25);
    	editData.setProtocol("SMTP");

	}
    
    protected com.kingdee.eas.framework.ICoreBase getBizInterface()
			throws Exception {
		return com.kingdee.eas.myframework.msgengine.MailServerConfigFactory.getRemoteInstance();
	}

    protected com.kingdee.bos.dao.IObjectValue createNewData() {
        com.kingdee.eas.myframework.msgengine.MailServerConfigInfo objectValue = new com.kingdee.eas.myframework.msgengine.MailServerConfigInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
        return objectValue;
    }

}