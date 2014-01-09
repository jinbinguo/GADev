package com.kingdee.eas.myframework.client;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.KDButton;
import com.kingdee.bos.ctrl.swing.KDSeparator;
import com.kingdee.eas.myframework.vo.MsgTableInfo;
import com.kingdee.eas.util.client.AdvMsgBox;
import com.kingdee.eas.util.client.ComponentUtil;
import com.kingdee.eas.util.client.EASResource;

public class AdvMsgBoxEx extends AdvMsgBox {
	
	private static final long serialVersionUID = -7154928699274682570L;
	private boolean isDetialForTable = false;
	

	private MsgTableInfo msgTblInfo;
	public AdvMsgBoxEx(Dialog owner, String title, boolean b) {
		super(owner, title, b);
	}

	public AdvMsgBoxEx(Dialog owner, String title, String msg, String detail,
			int messageType, int option) {
		super(owner, title, msg, detail, messageType, option);
	}

	public AdvMsgBoxEx(Dialog owner, String title, String msg, String detail,
			String[] options, int messageType, int option) {
		super(owner, title, msg, detail, options, messageType, option);
	}

	public AdvMsgBoxEx(Frame owner, String title, boolean b) {
		super(owner, title, b);
	}

	public AdvMsgBoxEx(Frame owner, String title, String msg, String detail,
			int messageType, int option) {
		super(owner, title, msg, detail, messageType, option);
	}

	public AdvMsgBoxEx(Frame owner, String title, String msg, String detail,
			String[] options, int messageType, int option) {
		super(owner, title, msg, detail, options, messageType, option);
	}
	
	
	public AdvMsgBoxEx(Frame owner, String title, String msg, MsgTableInfo msgTblInfo, int messageType) {
		super(owner,title,true);
		isDetialForTable = true;
		this.msgTblInfo = msgTblInfo;
        init(msg, null, messageType, option);
	}
	
	public AdvMsgBoxEx(Dialog owner, String title, String msg, MsgTableInfo msgTblInfo, int messageType) {
		super(owner,title,true);
		isDetialForTable = true;
		this.msgTblInfo = msgTblInfo;
        init(msg, null, messageType, option);

	}
	
	protected void jbInit(JPanel bgPanel) {
		super.jbInit(bgPanel);
		
		/** 若为详细信息为表格时，重构界面*/
		if (!isDetialForTable) {		
			return;
		}
		
		Component[] comp =  getContentPane().getComponents();
		if (bgPanel == null) bgPanel = (JPanel) comp[0]; //图标容器保留
		comp[1].setVisible(false); //除图标外的容器隐藏
		
        
	    JPanel contentPane = (JPanel)getContentPane();
	    final Dimension bgPanelDimension = bgPanel.getPreferredSize();
		
		JPanel pnlBottom = new JPanel();
		pnlBottom.setLayout(null);
		pnlBottom.setBounds(0, bgPanelDimension.height, bgPanelDimension.width, 202);
		contentPane.add(pnlBottom);
		pnlBottom.setBackground(bottomBgColor);
		KDSeparator sp = new KDSeparator();
		sp.setBounds(0, 0, bgPanelDimension.width, 2);
		pnlBottom.add(sp);
		KDButton btnAdv = new KDButton(EASResource.getString("advance"));
		btnAdv.setMargin(new Insets(2, 1, 2, 1));
		btnAdv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel contentPane = (JPanel) getContentPane();
				if (isDetail) {
					contentPane.setPreferredSize(new Dimension(bgPanelDimension.width,bgPanelDimension.height + 42));
					pack();
					isDetail = false;
				} else {
					contentPane.setPreferredSize(new Dimension(bgPanelDimension.width,bgPanelDimension.height + 202));
					pack();
					isDetail = true;
				}
			}

		});
		btnAdv.setBounds(10, 11, 77, 21);
		pnlBottom.add(btnAdv);
		if (messageType == 3) {
			KDButton btnYes = null;
			if (options == null)
				btnYes = new KDButton(EASResource.getString("yes"));
			else btnYes = new KDButton(options[0]);
			btnYes.setMargin(new Insets(2, 1, 2, 1));
			btnYes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					result = 0;
					dispose();
				}
			});
	        KDButton btnNo = null;
	        if(options == null)
	            btnNo = new KDButton(EASResource.getString("no"));
	        else
	            btnNo = new KDButton(options[1]);
	        btnNo.setMargin(new Insets(2, 1, 2, 1));
	        btnNo.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	                result = 1;
	                dispose();
	            }
	        });
	        btnYes.setBounds(257, 11, 77, 21);
	        btnNo.setBounds(342, 11, 77, 21);
	       
	        pnlBottom.add(btnYes);
	        pnlBottom.add(btnNo);
	      
	        setDefaultFocus(btnYes);
	        
		} else if (messageType == 1) {			
			KDButton btnOk = null;
			btnOk = new KDButton(EASResource.getString("ok"));
			btnOk.setMargin(new Insets(2, 1, 2, 1));
			btnOk.addActionListener(new ActionListener() {
	
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	            }
	        });
	        btnOk.setBounds(342, 11, 77, 21);
	        pnlBottom.add(btnOk);
	        setDefaultFocus(btnOk);
		}
        
        
        KDTable tblDetail = msgTblInfo.getTable();
        tblDetail.setBounds(10, 43, 409, 150);
		pnlBottom.add(tblDetail);
        pack();
        setLocationRelativeTo(null);

	}
	
	public static AdvMsgBoxEx createAdvMsgBox(Component owner, String title, String msg, MsgTableInfo msgTblInfo, int messageType) {
		 Window ownerWindow = (owner instanceof Window) ? (Window)owner : ComponentUtil.getOwnerWindow(owner);
		 if(ownerWindow instanceof Frame)
			 return new AdvMsgBoxEx((Frame)ownerWindow, title, msg, msgTblInfo, messageType);
		 if(ownerWindow instanceof Dialog)
			 return new AdvMsgBoxEx((Dialog)ownerWindow,title,msg, msgTblInfo, messageType);
		 else   return null;
	}


}
