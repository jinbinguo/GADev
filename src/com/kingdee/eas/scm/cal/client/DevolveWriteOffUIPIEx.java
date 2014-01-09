package com.kingdee.eas.scm.cal.client;

import java.awt.event.ActionEvent;

import com.kingdee.eas.scm.cal.DevolveWriteOffStandardEnum;

public class DevolveWriteOffUIPIEx extends DevolveWriteOffUI {

	public DevolveWriteOffUIPIEx() throws Exception {
		super();
	}
	
	@Override
	public void actionMultiBillWriteOff_actionPerformed(ActionEvent e)
			throws Exception {
		DevolveWriteOffForMultiBill writeOffMultiBill = 
			new DevolveWriteOffForMultiBill(this,topKdtable,bottomKdTable, cmbWriteOffStandard,cmbApportionRule);
		writeOffMultiBill.writeOff();
			
	}
	
	//标准核销功能
	@Override
	public void actionWriteOff_actionPerformed(ActionEvent arg0)
			throws Exception {
		super.actionWriteOff_actionPerformed(arg0);
		
	}
	


}
