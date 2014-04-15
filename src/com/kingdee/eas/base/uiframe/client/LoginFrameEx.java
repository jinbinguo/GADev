package com.kingdee.eas.base.uiframe.client;

import java.awt.Dimension;

import javax.swing.JButton;

import com.kingdee.eas.myframework.util.InvokeUtils;

public class LoginFrameEx extends LoginFrame {

	public LoginFrameEx() throws Exception {
		super();
	}
	@Override
	public void show() {
		super.show();
		try {
			Object obj = InvokeUtils.getFieldValue(this, "btnLogin");
			if (obj instanceof JButton) {
				((JButton)obj).setEnabled(true);
			}
			getContentPane().setPreferredSize(new Dimension(634, 418));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
