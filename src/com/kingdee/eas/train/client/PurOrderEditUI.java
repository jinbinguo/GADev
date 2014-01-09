/**
 * output package name
 */
package com.kingdee.eas.train.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Action;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.framework.client.multiDetail.DetailPanel;
import com.kingdee.eas.scm.common.BillBaseStatusEnum;
import com.kingdee.eas.scm.common.EntryBaseStatusEnum;
import com.kingdee.eas.train.PurOrderEntryInfo;
import com.kingdee.eas.train.PurOrderInfo;
import com.kingdee.eas.train.PurRequestInfo;
import com.kingdee.eas.util.client.MsgBox;

/**
 * output class name
 */
public class PurOrderEditUI extends AbstractPurOrderEditUI {
	private static final Logger logger = CoreUIObject
			.getLogger(PurOrderEditUI.class);

	/**
	 * output class constructor
	 */
	public PurOrderEditUI() throws Exception {
		super();
	}


	protected com.kingdee.eas.framework.ICoreBase getBizInterface()
			throws Exception {
		return com.kingdee.eas.train.PurOrderFactory.getRemoteInstance();
	}

	protected com.kingdee.bos.dao.IObjectValue createNewData() {
		com.kingdee.eas.train.PurOrderInfo objectValue = new com.kingdee.eas.train.PurOrderInfo();
		objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo) (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		objectValue.setBizDate(new Date());
		objectValue.setStatus(BillBaseStatusEnum.ADD);

		return objectValue;
	}
	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		
		KDWorkButton btnAddNewLine = ((DetailPanel)kdtEntrys_detailPanel).getAddNewLineButton();
		KDWorkButton btnInsertLine = ((DetailPanel)kdtEntrys_detailPanel).getInsertLineButton();
		KDWorkButton btnRemoveLines = ((DetailPanel)kdtEntrys_detailPanel).getRemoveLinesButton();
		resetAction(btnAddNewLine, actionAddLine);
		resetAction(btnInsertLine, actionInsertLine);
		resetAction(btnRemoveLines, actionRemoveLine);
		
	}
	public void resetAction(KDWorkButton btn, Action action) {
		ActionListener[] l = btn.getActionListeners();
		for (int i = 0; l != null && i < l.length; i++) {
			btn.removeActionListener(l[i]);
		}
		btn.addActionListener(action);
	}
	
	protected IObjectValue createNewDetailData(KDTable table) {
		PurOrderEntryInfo entryInfo = new PurOrderEntryInfo();

		return entryInfo;
	}

	@Override
	public void actionAudit_actionPerformed(ActionEvent e) throws Exception {
		storeFields();
		if (editData.isValueChange()) {
			MsgBox.showInfo("数据已修改，需重新提交后再审核！");
			return;
		}
		if (!editData.getStatus().equals(BillBaseStatusEnum.SUBMITED)) {
			MsgBox.showInfo("单据未提交，不能审核");
			return;
		}
		super.actionAudit_actionPerformed(e);
		refresh();
	}
	
	@Override
	public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception {
		if (!editData.getStatus().equals(BillBaseStatusEnum.AUDITED)) {
			MsgBox.showInfo("单据未审核，不能反审核");
			return;
		}
		super.actionUnAudit_actionPerformed(e);
		refresh();
	}
	public void refresh() throws Exception {
		IObjectPK pk = new ObjectUuidPK(editData.getId());
		editData =  (PurOrderInfo) getBizInterface().getValue(pk, getSelectors());
		setDataObject(editData);
		loadFields();
	}
	
	@Override
	public void actionAddLine_actionPerformed(ActionEvent e) throws Exception {
		if (getOprtState().equals(OprtState.ADDNEW) && getOprtState().equals(OprtState.EDIT)) {
			MsgBox.showInfo("非编辑状态的单据，不能新增分录行！");
			return;
		}
		
		if (editData.getStatus().getValue() >= 4) {
			MsgBox.showInfo("已审核单据不允许修改！");
			return;
		}
		super.actionAddLine_actionPerformed(e);
	}
	
	@Override
	public void actionInsertLine_actionPerformed(ActionEvent e)
			throws Exception {
		if (getOprtState().equals(OprtState.ADDNEW) && getOprtState().equals(OprtState.EDIT)) {
			MsgBox.showInfo("非编辑状态的单据，不能新增分录行！");
		}
		
		if (editData.getStatus().getValue() >= 4) {
			MsgBox.showInfo("已审核单据不允许修改！");
			return;
		}
		super.actionInsertLine_actionPerformed(e);
	}
	
	@Override
	public void actionRemoveLine_actionPerformed(ActionEvent e)
			throws Exception {
		if (getOprtState().equals(OprtState.ADDNEW) && getOprtState().equals(OprtState.EDIT)) {
			MsgBox.showInfo("非编辑状态的单据，不能删除分录行！");
		}
		
		if (editData.getStatus().getValue() >= 4) {
			MsgBox.showInfo("已审核单据不允许修改！");
			return;
		}
		super.actionRemoveLine_actionPerformed(e);
	}
	
	@Override
	public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
		if (editData.getStatus().getValue() >= 4) {
			MsgBox.showInfo("已审核单据不允许删除！");
			return;
		}
		super.actionRemove_actionPerformed(e);
	}
	
	@Override
	public void actionSave_actionPerformed(ActionEvent e) throws Exception {
		if (editData.getStatus().getValue() >= 4) {
			MsgBox.showInfo("已审核单据不允许再保存！");
			return;
		}
		super.actionSave_actionPerformed(e);
	}
	@Override
	public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
		if (editData.getStatus().getValue() >= 4) {
			MsgBox.showInfo("已审核单据不允许再保存！");
			return;
		}
		super.actionSubmit_actionPerformed(e);
	}
	
	@Override
	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		if (editData.getStatus().getValue() >= 4) {
			MsgBox.showInfo("已审核单据不允许修改！");
			return;
		}
		super.actionEdit_actionPerformed(e);
	}
	
}