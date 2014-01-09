package com.kingdee.eas.base.uiframe.client;

import javax.swing.JButton;

import com.kingdee.eas.myframework.util.InvokeUtils;

public class LoginFrameEx extends LoginFrame {

	public LoginFrameEx() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		try {
			Object obj = InvokeUtils.getFieldValue(this, "btnLogin");
			if (obj instanceof JButton) {
				((JButton)obj).setEnabled(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
