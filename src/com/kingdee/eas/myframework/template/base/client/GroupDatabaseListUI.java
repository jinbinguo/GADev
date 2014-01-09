/**
 * output package name
 */
package com.kingdee.eas.myframework.template.base.client;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.event.TreeSelectionEvent;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectListener;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.dao.query.BizEnumValueDTO;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.basedata.ncm.DeletedStatusEnum;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ITreeBase;
import com.kingdee.eas.framework.client.tree.KDTreeNode;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.template.base.GroupDatabaseTreeInfo;
import com.kingdee.eas.myframework.template.base.IGroupDatabase;
import com.kingdee.eas.myframework.template.base.IGroupDatabaseTree;
import com.kingdee.eas.myframework.vo.MsgTableInfo;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.util.NumericExceptionSubItem;

/**
 * output class name
 */
public class GroupDatabaseListUI extends AbstractGroupDatabaseListUI {

	private static final Logger logger = CoreUIObject
			.getLogger(GroupDatabaseListUI.class);

	public GroupDatabaseListUI() throws Exception {
		super();
	}

	@Override
	protected void initGroupButton() {
		//super.initGroupButton();
		treeView.getControlPane().add(btnGroupAddNew);
		treeView.getControlPane().add(btnGroupView);
		treeView.getControlPane().add(btnGroupEdit);
		treeView.getControlPane().add(btnGroupRemove);
		treeView.getControlPane().add(btnGroupCancel); 
		treeView.getControlPane().add(btnGroupCancelCancel);
		treeView.getControlPane().add(btnGroupMoveTree); 
	}
	public void onLoad() throws Exception {
		
		super.onLoad();
		actionCancel.setVisible(true);
		actionCancelCancel.setVisible(true);

		this.pnlMain.setDividerLocation(240);
		this.pnlMain.setDividerSize(8);
		
		btnGroupCancel.setText(null);
		btnGroupCancelCancel.setText(null);
		
		
	}
	
	protected void initListener() {
		super.initListener();
		tblMain.addKDTSelectListener(new KDTSelectListener() {
			public void tableSelectChanged(KDTSelectEvent arg0) {
				if (isMultiViewEnabled())
					return;
				ArrayList selectSet = getSelectedIdValues();
				if (selectSet.size() == 1)
					actionEdit.setEnabled(true);
				else
					actionEdit.setEnabled(false);

			}
		});
	}

	/**
	 * 禁用功能
	 * 
	 * PS:取消EAS框架的[禁用]功能，原框架内的[禁用]功能有点怪怪,时好时坏
	 */
	public void actionCancel_actionPerformed(ActionEvent e) throws Exception {
		// super.actionCancel_actionPerformed(e);
		checkSelected();

		boolean isPartlyCancel = false; // 是否部分已禁用

		ArrayList<Integer> arySelRowIndex = getSelectList(); // 选中行
		boolean isSelOneRow = arySelRowIndex.size() == 1;

		String[] colNames = new String[] { "number", "status" };
		String[] titleNames = new String[] { "编码", "状态" };
		MsgTableInfo msgTblInfo = new MsgTableInfo(colNames, titleNames);
		KDTable msgTbl = msgTblInfo.getTable();

		for (int i = 0; i < arySelRowIndex.size(); i++) {
			Integer rowIndex = arySelRowIndex.get(i);
			IRow row = tblMain.getRow(rowIndex);
			BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row
					.getCell("deletedStatus").getValue();
			String deletedStatus = bizEnumDeletedStatus == null ? ""
					: (String) bizEnumDeletedStatus.getStateManager()
							.getStateValue("value");
			if ("2".equals(deletedStatus)) { // 禁用
				IRow msgRow = msgTbl.addRow();
				msgRow.getCell("number").setValue(
						row.getCell("number").getValue());
				msgRow.getCell("status").setValue(
						row.getCell("deletedStatus").getValue());
				isPartlyCancel = true;
			}
		}
		boolean isCanCancel = false;
		if (isPartlyCancel) {
			if (isSelOneRow) {
				if (MsgBox.YES == MsgBox.showConfirm2(this, "是否禁用选中数据?")) {
					isCanCancel = true;
				}
			} else {
				String msg = "选中数据部分已是禁用状态,是否继续禁用?";
				if (MsgBox.YES == MsgBoxEx.showConfirmTable(this, msg,
						msgTblInfo)) {
					isCanCancel = true;
				}
			}
		} else {
			if (MsgBox.OK == MsgBox.showConfirm2("是否禁用选中数据?")) {
				isCanCancel = true;
			}
		}

		if (isCanCancel) {
			CoreBaseCollection corebaseCol = new CoreBaseCollection();
			for (int i = 0; i < arySelRowIndex.size(); i++) {
				Integer rowIndex = arySelRowIndex.get(i);
				String id = tblMain.getRow(rowIndex).getCell("id").getValue()
						.toString();
				IObjectPK pk = new ObjectUuidPK(id);
				CoreBaseInfo corebaseInfo = getBizInterface().getValue(pk);
				corebaseInfo.put("deletedStatus", 2);
				corebaseCol.add(corebaseInfo);
			}
			getBizInterface().update(corebaseCol);
			refreshList();
		}

	}

	/**
	 * 启用功能
	 * 
	 * PS:取消EAS框架的[启用]功能，原框架内的[启用]功能未实现逻辑
	 */
	public void actionCancelCancel_actionPerformed(ActionEvent actionevent)
			throws Exception {
		// super.actionCancelCancel_actionPerformed(actionevent);
		checkSelected();

		boolean isPartlyCancelCancel = false; // 是否部分已启用

		ArrayList<Integer> arySelRowIndex = getSelectList(); // 选中行
		boolean isSelOneRow = arySelRowIndex.size() == 1;

		String[] colNames = new String[] { "number", "status" };
		String[] titleNames = new String[] { "编码", "状态" };
		MsgTableInfo msgTblInfo = new MsgTableInfo(colNames, titleNames);
		KDTable msgTbl = msgTblInfo.getTable();

		for (int i = 0; i < arySelRowIndex.size(); i++) {
			Integer rowIndex = arySelRowIndex.get(i);
			IRow row = tblMain.getRow(rowIndex);
			BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row
					.getCell("deletedStatus").getValue();
			String deletedStatus = bizEnumDeletedStatus == null ? ""
					: (String) bizEnumDeletedStatus.getStateManager()
							.getStateValue("value");
			if ("1".equals(deletedStatus)) { // 启用
				IRow msgRow = msgTbl.addRow();
				msgRow.getCell("number").setValue(
						row.getCell("number").getValue());
				msgRow.getCell("status").setValue(
						row.getCell("deletedStatus").getValue());
				isPartlyCancelCancel = true;
			}
		}
		boolean isCanCancelCancel = false;
		if (isPartlyCancelCancel) {
			if (isSelOneRow) {
				if (MsgBox.YES == MsgBox.showConfirm2(this, "是否启用选中数据?")) {
					isCanCancelCancel = true;
				}
			} else {
				String msg = "选中数据部分已是启用状态,是否继续启用?";
				if (MsgBox.YES == MsgBoxEx.showConfirmTable(this, msg,
						msgTblInfo)) {
					isCanCancelCancel = true;
				}
			}
		} else {
			if (MsgBox.OK == MsgBox.showConfirm2("是否启用选中数据?")) {
				isCanCancelCancel = true;
			}
		}

		if (isCanCancelCancel) {
			CoreBaseCollection corebaseCol = new CoreBaseCollection();
			for (int i = 0; i < arySelRowIndex.size(); i++) {
				Integer rowIndex = arySelRowIndex.get(i);
				String id = tblMain.getRow(rowIndex).getCell("id").getValue()
						.toString();
				IObjectPK pk = new ObjectUuidPK(id);
				CoreBaseInfo corebaseInfo = getBizInterface().getValue(pk);
				corebaseInfo.put("deletedStatus", 1);
				corebaseCol.add(corebaseInfo);
			}
			getBizInterface().update(corebaseCol);
			refreshList();
		}
	}
	
	

	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		checkSelected();
		ArrayList<Integer> arySelRowIndex = getSelectList();
		int currentRowIndex = arySelRowIndex.get(0);
		IRow row = tblMain.getRow(currentRowIndex);
		// --系统预设数据不允许编辑
		boolean isScheduled = (Boolean) row.getCell("scheduled").getValue();
		if (isScheduled) {
			throw new EASBizException(new NumericExceptionSubItem("",
					"系统预设数据不允许编辑!"));
		}
		// --已启用数据不允许编辑
		BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row.getCell(
				"deletedStatus").getValue();
		String deletedStatus = bizEnumDeletedStatus == null ? ""
				: (String) bizEnumDeletedStatus.getStateManager()
						.getStateValue("value");
		if ("1".equals(deletedStatus)) { // 启用
			throw new EASBizException(new NumericExceptionSubItem("",
					"已启用数据不允许编辑!"));
		}

		super.actionEdit_actionPerformed(e);
	}

	public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
		// --系统预设数据不允许删除
		// --已启用状态数据不允许删除
		checkSelected();
		ArrayList<Integer> arySelRowIndex = getSelectList();
		String[] colNames = new String[] { "number", "reason" };
		String[] titleNames = new String[] { "编码", "原因" };
		int[] colWidths = new int[] { 130, 250 };
		MsgTableInfo msgTblInfo = new MsgTableInfo(colNames, titleNames,
				colWidths);
		boolean isCanRemove = true;
		KDTable msgTbl = msgTblInfo.getTable();
		for (int i = 0; i < arySelRowIndex.size(); i++) {
			Integer rowIndex = arySelRowIndex.get(i);
			IRow row = tblMain.getRow(rowIndex);
			BizEnumValueDTO bizEnumDeletedStatus = (BizEnumValueDTO) row
					.getCell("deletedStatus").getValue();
			String deletedStatus = bizEnumDeletedStatus == null ? ""
					: (String) bizEnumDeletedStatus.getStateManager()
							.getStateValue("value");
			boolean isScheduled = (Boolean) row.getCell("scheduled").getValue();
			if (isScheduled) {
				isCanRemove = false;
				IRow msgRow = msgTbl.addRow();
				msgRow.getCell("number").setValue(
						row.getCell("number").getValue());
				msgRow.getCell("reason").setValue("系统预设,不允许删除!");
				continue;
			}
			if ("1".equals(deletedStatus)) { // 启用状态
				isCanRemove = false;
				IRow msgRow = msgTbl.addRow();
				msgRow.getCell("number").setValue(
						row.getCell("number").getValue());
				msgRow.getCell("reason").setValue("启用状态,不允许删除.");
			}
		}
		if (!isCanRemove) {
			MsgBoxEx.showInfoTable(this, "删除数据失败!", msgTblInfo);
			SysUtil.abort();
		}

		super.actionRemove_actionPerformed(e);
	}



	public void actionGroupRemove_actionPerformed(ActionEvent e)
			throws Exception {
		checkTreeNodeSelected(e);
		/** 系统预设数据不允许删除 */
		String selTreeId = getSelectedNodeKeyValue();
		IGroupDatabaseTree treeInterface = (IGroupDatabaseTree) getTreeInterface();
		GroupDatabaseTreeInfo treeInfo = treeInterface.getGroupDatabaseTreeInfo(new ObjectUuidPK(BOSUuid.read(selTreeId)));
		if (treeInfo.isScheduled()) {
			String msg = EASResource.getString("com.kingdee.eas.basedata.scm.common.SCMResource.SystemSettingDel");
			MsgBox.showInfo(this, msg);
			return;
		}
		/** 存在分组数据时，不允许删除分组 */
		IGroupDatabase bizInterface = (IGroupDatabase) getBizInterface();
		boolean isExists = bizInterface.exists(String.format("where treeid='%s'", selTreeId));
		if (isExists) {
			String msg = "存在分组明细数据，不允许删除分组!";
			MsgBox.showInfo(this, msg);
			return;
		}
		super.actionGroupRemove_actionPerformed(e);
	}

	public void actionAddNew_actionPerformed(ActionEvent e) throws Exception {
		checkTreeNodeSelected(e);
		super.actionAddNew_actionPerformed(e);
	}

	public void actionGroupEdit_actionPerformed(ActionEvent e) throws Exception {
		checkTreeNodeSelected(e);

		/** 系统预设数据不允许修改 */
		KDTreeNode treeNode = getSelectedTreeNode();
		GroupDatabaseTreeInfo treeInfo = (GroupDatabaseTreeInfo) treeNode.getUserObject();
	
		if (treeInfo.isScheduled()) {
			String msg = EASResource.getString("com.kingdee.eas.basedata.scm.common.SCMResource.SystemSettingEdit");
			MsgBoxEx.showInfo(this, msg);
			return;
		}
		if (treeInfo.getDeletedStatus().getValue() == DeletedStatusEnum.OPEN_VALUE) {
			String msg = "启用状态数据，不能修改！";
			MsgBoxEx.showInfo(this, msg);
			return;
		}
		super.actionGroupEdit_actionPerformed(e);
	}
	
	@Override
	public void actionGroupCancel_actionPerformed(ActionEvent e)
			throws Exception {		
		checkTreeNodeSelected(e);
		if (MsgBox.YES != MsgBoxEx.showConfirm2("是否禁用选中组别?")) return;
		
		ITreeBase iTreeBase = getTreeInterface();
		KDTreeNode treeNode = getSelectedTreeNode();
		GroupDatabaseTreeInfo treeInfo = (GroupDatabaseTreeInfo) treeNode.getUserObject();
		IObjectPK pk = new ObjectUuidPK(treeInfo.getId().toString());
		iTreeBase.cancel(pk, treeInfo);
		treeInfo.setDeletedStatus(DeletedStatusEnum.CLOSE);
		actionGroupCancel.setEnabled(false);
		actionGroupCancelCancel.setEnabled(true);
		MsgBoxEx.showInfo("组别禁用！");
		
	}
	
	@Override
	public void actionGroupCancelCancel_actionPerformed(ActionEvent e)
			throws Exception {
		checkTreeNodeSelected(e);
		if (MsgBox.YES != MsgBoxEx.showConfirm2("是否启用选中组别?")) return;
		ITreeBase iTreeBase = getTreeInterface();
		KDTreeNode treeNode = getSelectedTreeNode();
		GroupDatabaseTreeInfo treeInfo = (GroupDatabaseTreeInfo) treeNode.getUserObject();
		IObjectPK pk = new ObjectUuidPK(treeInfo.getId().toString());
		iTreeBase.cancelCancel(pk, treeInfo);
		treeInfo.setDeletedStatus(DeletedStatusEnum.OPEN);
		actionGroupCancel.setEnabled(true);
		actionGroupCancelCancel.setEnabled(false);
		MsgBoxEx.showInfo("组别启用！");
		
	}
	@Override
	protected void treeMain_valueChanged(TreeSelectionEvent e) throws Exception {
		super.treeMain_valueChanged(e);
		Object obj = getSelectedTreeNode().getUserObject();
		if (!(obj instanceof GroupDatabaseTreeInfo)) {
			actionGroupCancel.setEnabled(false);
			actionGroupCancelCancel.setEnabled(false);
			return;
		}
		GroupDatabaseTreeInfo treeInfo = (GroupDatabaseTreeInfo) obj;
		int deletedStatus = treeInfo.getDeletedStatus().getValue();
		if (deletedStatus == DeletedStatusEnum.OPEN_VALUE) {
			actionGroupCancel.setEnabled(true);
			actionGroupCancelCancel.setEnabled(false);		
		} else if (deletedStatus == DeletedStatusEnum.CLOSE_VALUE) {
			actionGroupCancel.setEnabled(false);
			actionGroupCancelCancel.setEnabled(true);
		} else {
			actionGroupCancel.setEnabled(false);
			actionGroupCancelCancel.setEnabled(false);
		}
	}
	
	@Override
	protected SelectorItemCollection getSelectorForTree() {
		SelectorItemCollection sc = new SelectorItemCollection();
		sc.add(new SelectorItemInfo("id"));
		sc.add(new SelectorItemInfo("number"));
		sc.add(new SelectorItemInfo("name"));
		sc.add(new SelectorItemInfo("longNumber"));
		sc.add(new SelectorItemInfo("isleaf"));
		sc.add(new SelectorItemInfo("parent"));
		sc.add(new SelectorItemInfo("scheduled"));
		sc.add(new SelectorItemInfo("cu"));
		sc.add(new SelectorItemInfo("deletedStatus"));
		sc.add(new SelectorItemInfo("level"));
		
		return sc;
	}
	@Override
	protected String getGroupEditUIName() {
		return null;
	}

	@Override
	protected String getQueryFieldName() {
		return null;
	}

	@Override
	protected IObjectPK getSelectedTreeKeyValue() {
		return null;
	}

	@Override
	protected ITreeBase getTreeInterface() throws Exception {
		return null;
	}

}