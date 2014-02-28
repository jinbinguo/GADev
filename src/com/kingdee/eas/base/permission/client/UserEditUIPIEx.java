/**
 * output package name
 */
package com.kingdee.eas.base.permission.client;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.ga.basedata.UserTypeEnum;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.jdbc.rowset.IRowSet;

/**
 * output class name
 */
public class UserEditUIPIEx extends UserEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(UserEditUIPIEx.class);
    

    public UserEditUIPIEx() throws Exception {
        super();
    }
    
    @Override
    protected void resetLayout() {
    	super.resetLayout();
    	resetUIBound();
    	
    }
    
    @Override
    public void onLoad() throws Exception {
    	super.onLoad();
    	txtmaxRepairDiscountRate.setMinimumValue(0);
    	txtmaxRepairDiscountRate.setMaximumValue(100);
    	
    	txtmaxRetailDiscountRate.setMinimumValue(0);
    	txtmaxRetailDiscountRate.setMaximumValue(100);
    }
    
    @Override
    protected void prepareLoadFields() {
    	super.prepareLoadFields();
    	resetUIBound();
    	if (userInfo != null) {
    		//重新取userinfo,标准产品做了些限制，不支持二开字段，查看
    		if (OprtState.VIEW.equals(getOprtState()) || OprtState.EDIT.equals(getOprtState())) {
    			String sql = "select CFUserType,isnull(CFMaxRepairDiscountRate,0) CFMaxRepairDiscountRate,isnull(CFMaxRetailDiscountRate,0) CFMaxRetailDiscountRate from T_PM_User where FID='" + userInfo.getString("id") + "'";
    			try {
					IRowSet rs = DBUtils.executeQuery(null, sql);
					if (rs != null && rs.next()) 
						userInfo.setUserType(UserTypeEnum.getEnum(rs.getString("CFUserType")));
						userInfo.setMaxRepairDiscountRate(rs.getBigDecimal("CFMaxRepairDiscountRate"));
						userInfo.setMaxRetailDiscountRate(rs.getBigDecimal("CFMaxRetailDiscountRate"));
				} catch (Exception e) {
					UIUtils.handUIExceptionAndAbort(e);
				}
    			userType.setSelectedItem(userInfo.getUserType());
    			txtmaxRepairDiscountRate.setValue(userInfo.getMaxRepairDiscountRate());
    			txtmaxRetailDiscountRate.setValue(userInfo.getMaxRetailDiscountRate());
    		}
    		
    		
    	}
    }
    
    @Override
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
    	super.actionSubmit_actionPerformed(e);
    	UserTypeEnum userTypeEnum = (UserTypeEnum) userType.getSelectedItem();
    	BigDecimal maxRepairDiscountRate = PublicUtils.getBigDecimal(txtmaxRepairDiscountRate.getBigDecimalValue());
    	BigDecimal maxRetailDiscountRate = PublicUtils.getBigDecimal(txtmaxRetailDiscountRate.getBigDecimalValue());
    	if (userTypeEnum != null && userInfo != null) {
    		String sql = "update T_PM_User set CFUserType='" + userTypeEnum.getValue() + "'," +
    				"CFMaxRepairDiscountRate= " +maxRepairDiscountRate.doubleValue() +
    				",CFMaxRetailDiscountRate=" + maxRetailDiscountRate.doubleValue() +
    				" where FID='" + userInfo.getString("id") + "'";
    		DBUtils.execute(null, sql);
    		
    	}
    }
    @Override
    protected void prepareStoreFields() {
    	super.prepareStoreFields();
    	if (userInfo != null) userInfo.setUserType((UserTypeEnum) userType.getSelectedItem());
    }
    
    private void resetUIBound() {
    	if(!OprtState.ADDNEW.equals(getOprtState())) {
    		contuserType.setBounds(new Rectangle(10, 230, 250, 19));
	    	contmaxRepairDiscountRate.setBounds(new Rectangle(10, 255, 250, 19));
	    	contmaxRetailDiscountRate.setBounds(new Rectangle(290, 255, 250, 19));
	    	lblDescription.setBounds(new Rectangle(10, 280, 100, 19));
	    	mlaDescription.setBounds(new Rectangle(10, 305, 530, 98));

	  
	    	
    	}
    }
    @Override
    public void setBaseInfoState(boolean state) throws EASBizException,
    		BOSException {
    	super.setBaseInfoState(state);
    	contuserType.setEnabled(state);
    	contmaxRepairDiscountRate.setEnabled(state);
    	contmaxRetailDiscountRate.setEnabled(state);
    }
    
   

}