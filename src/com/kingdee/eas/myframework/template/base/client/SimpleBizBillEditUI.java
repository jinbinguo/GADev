/**
 * output package name
 */
package com.kingdee.eas.myframework.template.base.client;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.common.CtrlUIEnv;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.util.KDTableUtil;
import com.kingdee.bos.ctrl.swing.KDPanel;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.ctrl.swing.event.DataChangeListener;
import com.kingdee.bos.dao.AbstractObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.basedata.framework.CUIDGetterFacadeFactory;
import com.kingdee.eas.basedata.framework.DataBaseDException;
import com.kingdee.eas.basedata.framework.ICUIDGetterFacade;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.framework.batchHandler.UtilRequest;
import com.kingdee.eas.framework.client.multiDetail.DetailPanel;
import com.kingdee.eas.framework.util.BaseDataFactory;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.comparators.table.SortColumnCollection;
import com.kingdee.eas.myframework.template.base.ISimpleBizBill;
import com.kingdee.eas.myframework.template.base.SimpleBizBillEntryInfo;
import com.kingdee.eas.myframework.template.base.SimpleBizBillInfo;
import com.kingdee.eas.myframework.util.CodingRuleUtils;
import com.kingdee.eas.myframework.util.InvokeUtils;
import com.kingdee.eas.myframework.util.MutexUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.eas.myframework.vo.RequireCompCollection;
import com.kingdee.eas.myframework.vo.RequireCompInfo;
import com.kingdee.eas.myframework.vo.ResetFieldCollection;
import com.kingdee.eas.myframework.vo.ResetFieldInfo;
import com.kingdee.eas.scm.common.BillBaseStatusEnum;
import com.kingdee.eas.scm.common.EntryBaseStatusEnum;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.ExceptionHandler;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

/**
 * output class name
 */
public abstract class SimpleBizBillEditUI extends AbstractSimpleBizBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(SimpleBizBillEditUI.class);
    
    /**解决继承自定义模版发布时的bug,无定义kdtEntrys_detailPanel*/
    public com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtEntrys_detailPanel = null;
    
 
	public SimpleBizBillEditUI() throws Exception {
		super();
	}




	public void onLoad() throws Exception {
		super.onLoad();
		/**
		 * 根据主业务组织重置单据编码控件
		 */
		KDBizPromptBox mainOrgPrmt =  getMainBizOrg();
		if (mainOrgPrmt != null) {
			mainOrgPrmt.addDataChangeListener(new DataChangeListener() {
				public void dataChanged(DataChangeEvent arg0) { 
					Object newValue = arg0.getNewValue();
					Object oldValue = arg0.getOldValue();
					if (!PublicUtils.equals(newValue, oldValue)) {
						try {
							setNumberEnabled();
						} catch (Exception e) {
							SysUtil.abort();
						}
					}
					
				}
				
			});	
		}
		
		/**重新配置btnAddnewline、btnInsertNewLine、btnRemoveLines表格小按钮的事件,解决标准产品未绑定editUI上的action事件的问题*/
		KDWorkButton btnAddNewLine = ((DetailPanel)kdtEntrys_detailPanel).getAddNewLineButton();
		KDWorkButton btnInsertLine = ((DetailPanel)kdtEntrys_detailPanel).getInsertLineButton();
		KDWorkButton btnRemoveLines = ((DetailPanel)kdtEntrys_detailPanel).getRemoveLinesButton();
		resetBtnAction(btnAddNewLine, actionAddLine);
		resetBtnAction(btnInsertLine, actionInsertLine);
		resetBtnAction(btnRemoveLines, actionRemoveLine);
		/**配置必录项*/
		registerRequireComp();
		/**配置编码字段*/
		setNumberEnabled();
		

	}
	@Override
	protected void initWorkButton() {
		super.initWorkButton();
		try {
			/**添加组合多列排序按钮*/
			KDPanel kdtEntrys_detailPanel_controlPanel = (KDPanel) InvokeUtils.getFieldValue(kdtEntrys_detailPanel, "controlPanel");
			Rectangle rect = (Rectangle)InvokeUtils.getFieldValue(kdtEntrys_detailPanel, "rect");
			btnMultiColumnSort.setFactType(1);
			btnMultiColumnSort.setOpaque(true);
			kdtEntrys_detailPanel_controlPanel.add(btnMultiColumnSort, new com.kingdee.bos.ctrl.swing.KDLayout.Constraints(rect.width - 113, 5, 22, 19, 9));
		} catch (Exception e){}
	}
	
	/**
	 * 设置必录项
	 * 
	 * @return
	 * @throws Exception 
	 */
	public RequireCompCollection registerRequireComp() throws Exception {
		txtNumber.setRequired(true);
		RequireCompCollection requireCompCol = new RequireCompCollection();
		requireCompCol.add(pkBizDate);
		requireCompCol.add(kdtEntrys,"lineDesc");
		return requireCompCol;
	}
	/**
	 * 重置整单、行复制后的部分字段的默认值 
	 * @return
	 */
	public ResetFieldCollection resetFieldstAfterCopy() throws EASBizException {
		ResetFieldCollection rfc = new ResetFieldCollection();
		rfc.add(new ResetFieldInfo("baseStatus",BillBaseStatusEnum.ADD));
		rfc.add(new ResetFieldInfo("bizDate", SysUtil.getAppServerTime(null)));
		rfc.add(new ResetFieldInfo("entrys","lineStatus", EntryBaseStatusEnum.ADD));
		return rfc;
	} 

	/**
	 * 新增状态时单据体的默认值
	 */
	protected void createNewDataEx() throws Exception {
		
		Date currentDate = SysUtil.getAppServerTime(null);
		editData.setDate("bizdate", currentDate);
	}
	
	protected IObjectValue createNewDetailData(KDTable table) {
		IObjectValue valueEntryInfo = new SimpleBizBillEntryInfo();
		return valueEntryInfo;
	}

	
	public void actionEdit_actionPerformed(ActionEvent arg0) throws Exception {
		super.actionEdit_actionPerformed(arg0);		
		/** 编辑时，若是启用编码规则且[支持修改]未勾选时,编码不允许编辑*/
		setNumberEnabled();
	}

	public void actionSave_actionPerformed(ActionEvent e) throws Exception {
		MutexUtils.checkDataObjectLockWithExcepion(editData);
		super.actionSave_actionPerformed(e);

	}
	
	public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
		super.actionSubmit_actionPerformed(e);
	}
	
	
	public void actionAudit_actionPerformed(ActionEvent e) throws Exception {
		storeFields();
		if (editData.isValueChange()) {
			MsgBoxEx.showInfo("数据已修改，需重新提交后再审核！");
			return;
		}
		IObjectPK pk =  new ObjectUuidPK(editData.getId().toString());
		MutexUtils.checkDataObjectLockWithExcepion(editData);
		((ISimpleBizBill) getBizInterface()).audit(pk);
		setOprtState(OprtState.VIEW);
		refresh();
		showAuditMessage();
		
	}
	

	public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception {
		IObjectPK pk =  new ObjectUuidPK(editData.getId().toString());
		((ISimpleBizBill) getBizInterface()).unAudit(pk);
		setOprtState(OprtState.EDIT);
		refresh();
		try {
			MutexUtils.requestLockForUpdate(pk.toString());
		} catch (Throwable e1) {
			throw new EASBizException(new NumericExceptionSubItem("",e1.toString()));
		}
		showUnAuditMessage();
		
	}
	
	public void actionCopy_actionPerformed(ActionEvent e) throws Exception {
		super.actionCopy_actionPerformed(e);
	}
	
	/**
	 * 重写单据删除动作，因为业务建模生成的抽象类代码方法removeByPK(IObjectPK)有添加代码对编码的回收，
	 * 但在ListUI对批量删除多条记录时，抽象类代码生成的回收代码不能回收所有编码，只回收一条，
	 * 且不在同一事务中，可能导致断号出现，所以统一改到服务端处理
	 */
    public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
    	checkForSType("ACTION_DELETE");
    	IObjectPK pk = new ObjectUuidPK(editData.getId());
    	if(confirmRemove())  {
    		boolean bool = UtilRequest.isPrepare("ActionRemove", this);
    		if(bool)
    			prepareRomove(null).callHandler();
    		removeByPKEx(pk);
    	}
		//当删除到最后一笔时，将新增记录
		if ("ADDNEW".equals(getOprtState())) {
			createNewDataEx();
		}
		loadFields();
    }
    public void actionAddNew_actionPerformed(ActionEvent e) throws Exception {
		super.actionAddNew_actionPerformed(e);
		createNewDataEx(); //表头默认值 
		loadFields();
	}

	@Override
	public void actionCopyLine_actionPerformed(ActionEvent e) throws Exception {
		KDTable table = getDetailTable();
		if (table == null)
			return;
		KDTSelectManager manager = table.getSelectManager();
		if (manager.getBlocks().size() > 0 && table.getRowCount() > 0) {
			KDTSelectBlock selectedBlock = (KDTSelectBlock) manager.getBlocks().get(0);
			int beginBlockRow = selectedBlock.getBeginRow();
			int endBlockRow = selectedBlock.getEndRow();
			if (manager.getBlocks().size() > 1 || beginBlockRow != endBlockRow) {
				MsgBox.showInfo(this,EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_MultiRows"));
				return;
			}
			
			addLine(getDetailTable());
			IRow copyFromRowObj = null;
			IRow copyToRowObj = null;
			int copyFromRowIndex = 0;
			int copyToRowIndex = 0;
			
			copyFromRowIndex = selectedBlock.getEndRow();
			copyToRowIndex = KDTableUtil.getLastVisibleRowIndex(table);
			copyFromRowObj = table.getRow(copyFromRowIndex);
			copyToRowObj = table.getRow(copyToRowIndex);
			for (int cellIndex = 0; cellIndex < table.getColumnCount(); cellIndex++) {
				ICell copyFromCell = copyFromRowObj.getCell(cellIndex);
				ICell copyToCell = copyToRowObj.getCell(cellIndex);
				if (copyFromCell != null && copyToCell != null) {
					Object orgValue = copyFromCell.getValue();
					copyToCell.setValue(orgValue);
				}
			}
			if (getSubKeyFieldName() != null) {
				ICell cellId = copyToRowObj.getCell(getSubKeyFieldName());
				if (cellId != null)
					cellId.setValue(null);
			}
			storeLineFields(table, copyToRowObj, (IObjectValue) copyToRowObj.getUserObject());
			afterCopyLine(table,copyToRowObj);
			loadLineFields(table, copyToRowObj, (IObjectValue) copyToRowObj.getUserObject());
			appendFootRow(getDetailTable());		
		} else {
			MsgBox.showInfo(this,EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_NoneRow"));
			return;
		}
	}
    
	/**
	 * 重置table上小按钮的事件
	 * 采用先清除再绑定
	 * 
	 * @param btn
	 * @param action
	 */
	private void resetBtnAction(KDWorkButton btn, Action action) {
		ActionListener[] l = btn.getActionListeners();
		for (int i = 0; l != null && i < l.length; i++) {
			btn.removeActionListener(l[i]);
		}
		btn.addActionListener(action);
	}

	/** 
	 * 重置编码控件的编辑性
	 * @throws Exception
	 */
	private void setNumberEnabled() throws Exception {
		
		String orgId = null;
		KDBizPromptBox prmtMainOrg =  getMainBizOrg();
		OrgType mainOrgType = getMainBizOrgType();
		if (prmtMainOrg != null && mainOrgType != null) {
			OrgUnitInfo orgInfo = (OrgUnitInfo)prmtMainOrg.getValue();
			if (orgInfo != null)
				orgId = orgInfo.getId().toString();
		}
		
		/** 若是启用编码规则且[支持修改]未勾选时,编码不允许编辑*/
		if (!CodingRuleUtils.isModifiable(editData, orgId)) {
			txtNumber.setEnabled(false);
		}
	}
    /**
     * 改造removeByPK(IObjectPK),由业务建模工具生成的代码removeByPK(IObjectPK)有自动回收编码，但不在同一事务，所以改造到后台执行，放于同一事务
     * 代码与removeByPK(IObjectPK)一致，未有改动，因继承关系的原因，所以必须得拷贝出来另写
     * @param pk
     * @throws Exception
     */
    private void removeByPKEx(IObjectPK pk) throws Exception {
		String tempState;
		tempState = getOprtState();
		setOprtState("REMOVE");
		try {
			pubFireVOChangeListener(pk.toString());
		} catch (Throwable e) {
			setOprtState(tempState);
			handUIException(e);
			abort();
		}
		getBizInterface().delete(pk);
		setOprtState(tempState);
		initOldData(null);

		setOprtState(tempState);
		initOldData(null);

		if (idList.size() > 1) {
			editData = null;
			if (actionNext.isEnabled())
				actionNext_actionPerformed(null);
			else
				actionPre_actionPerformed(null);
			idList.remove(pk.toString(), false);
			initScrollButtons();
		} else {
			idList.remove(pk.toString(), false);
			try {
				setOprtState("RELEASEALL");
				pubFireVOChangeListener(pk.toString());
			} catch (Throwable e1) {
			}
			setOprtState("ADDNEW");
			IObjectValue value =createNewData();
			if (value != null) {
				setDataObject(value);
			} else {
				MsgBox.showInfo(this,EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.confirm_close"));
				destroyWindow();
			}
			innerLoadFields();
			if (idList.size() == 0)
				actionEdit.setEnabled(false);
		}
		setSave(true);
		setSaved(true);
		return;
	}
    
    /**从底层类代码拷贝过来*/
	private void checkForSType(String actionName) throws Exception {
		checkPermission(actionName);
	}
	/**从底层类代码拷贝过来*/
	private void checkPermission(String action) throws Exception {
		if (getControlType().equalsIgnoreCase("S1")) {
			if (!getCurrentCUID().equals("00000000-0000-0000-0000-000000000000CCE7AED4"))
				throwsExceptionForNoPermission(action);
		} else if ((getControlType().equalsIgnoreCase("S3") || getControlType()
				.equalsIgnoreCase("S4")) && !action.equals("ACTION_ADDNEW") && !getCurrentCUID().equals(getCUIDFromBizobject()))
			throwsExceptionForNoPermission(action);
	}
	/**从底层类代码拷贝过来*/
    private String getCUIDFromBizobject() throws Exception {
		return getICGF().getCUID(editData.getId().toString());
	}
    /**从底层类代码拷贝过来*/
	private ICUIDGetterFacade getICGF() throws Exception {
		return CUIDGetterFacadeFactory.getRemoteInstance();
	}
	/**从底层类代码拷贝过来*/
    private void throwsExceptionForNoPermission(String action) throws Exception {
		if (action.equals("ACTION_ADDNEW"))
			throw new DataBaseDException(DataBaseDException.CAN_NOT_ADD);
		if (action.equals("ACTION_DELETE"))
			throw new DataBaseDException(DataBaseDException.CAN_NOT_DELETE);
		if (action.equals("ACTION_MODIFY"))
			throw new DataBaseDException(DataBaseDException.CAN_NOT_UPDATE);
		else
			return;
	}
    /**从底层类代码拷贝过来*/
    String controlType;
    private String getControlType() {
		if (controlType == null) {
			controlType = "";
			try {
				if (getBizInterface() == null) {
					return controlType;
				}
				controlType = BaseDataFactory.getInstance().getControlType(getBizType());
			} catch (Exception e) {
				ExceptionHandler.handle(e);
				SysUtil.abort();
			}
		}
		return controlType;
	}
    /**从底层类代码拷贝过来*/
	private void innerLoadFields() {
		EventListener lsts[] = removeDetailTableListener(com.kingdee.bos.ctrl.kdf.table.event.KDTPropertyChangeListener.class);
		loadFields();
		restoreDetailTableListener(com.kingdee.bos.ctrl.kdf.table.event.KDTPropertyChangeListener.class,lsts);
	}
	
	public void showAuditMessage() {
		showBarMessage("审核成功");
	}
	public void showUnAuditMessage() {
		showBarMessage("反审核成功");
	}
	
	private void showBarMessage(String str) {
		StringBuilder msg = new StringBuilder();
		msg.append(getClassAlise()).append(" ").append(str);
		setMessageText(msg.toString());
		setNextMessageText(msg.toString());
		setShowMessagePolicy(0);
		setIsShowTextOnly(false);
		showMessage();
	}
	/**
	 * 重新刷新本单据
	 * @throws Exception
	 */
	public void refresh() throws Exception {
		IObjectPK pk = new ObjectUuidPK(editData.getId());
		editData = (SimpleBizBillInfo) getBizInterface().getValue(pk, getSelectors());
		setDataObject(editData);
	}
	/**
	 * 界面值有效性的验证
	 */
	protected void verifyInput(ActionEvent e) throws Exception {
		super.verifyInput(e);
	
		/**检查行记录*/
		if (getDetailTable().getRowCount() < 1) {
			throw new EASBizException(new NumericExceptionSubItem("","行记录不能为空！"));
		}
		/** 检查必录项*/
		verifyRequireComp();
		
		
	}		
	
	/**
	 * 复制行后，根据配置行默认值字段重新设值
	 * @param kdtable
	 * @param row
	 */
	private void afterCopyLine(KDTable kdtable, IRow row) {
		try {
			IObjectValue iobjectvalue = (IObjectValue) row.getUserObject();
			ResetFieldCollection resetFieldCol = resetFieldstAfterCopy();
			String[] entryNames = resetFieldCol.getEntryNames();
			String tblName = kdtable.getName();
			for (int i = 0; entryNames != null && i < entryNames.length; i++) {
				if (!editData.containsKey(entryNames[i])) {		
					kdtable.removeRow(row.getRowIndex());
					MsgBoxEx.showDetailAndOK(null, "行复制异常，字段标识有误", String.format("分录标识[%s]有误,请开发检查代码!",entryNames[i]), MsgBox.OK);	
					SysUtil.abort();
				}				
				String bindCompName = dataBinder.getComponetByField(entryNames[i]).getName();
				if (PublicUtils.equals(tblName, bindCompName)) {
					Vector<ResetFieldInfo> vecEntryColumn = resetFieldCol.getEntryColumn(entryNames[i]);
					for (int j = 0; vecEntryColumn != null && j < vecEntryColumn.size(); j++) {
						ResetFieldInfo rfi = vecEntryColumn.get(j);
						iobjectvalue.put(rfi.getColumnFlagName(), rfi.getDefaultValue());
					}
				}
			}
		} catch (EASBizException e) {
			kdtable.removeRow(row.getRowIndex());
			SysUtil.abort();
		}
		
	}
	
	/**
	 * 验证必录项
	 */
	public void verifyRequireComp() throws Exception {
		
		/** 是否启用编码规则,未启用编规则，不允许编码为空 */
		if (!CodingRuleUtils.hasCodingRule(editData, null) && StringUtils.isEmpty(txtNumber.getText())) {
			txtNumber.requestFocus();
			throw new EASBizException(new NumericExceptionSubItem("","编码不能为空！"));
		}
		
		RequireCompCollection requireCompCol = registerRequireComp();
		

		/**先验证表头，并将表体的验证项保存*/
		HashMap<String, KDTable> hashTable = new HashMap<String, KDTable>();
		HashMap<String, Vector<String>> hashColumns = new HashMap<String, Vector<String>>();
		Set<String> keySet = requireCompCol.key();
		Iterator<String> itSet = keySet.iterator();
		while (itSet.hasNext()) {
			String key = itSet.next();
			RequireCompInfo rqCompInfo = requireCompCol.get(key);
			if (!rqCompInfo.isTable()) { //检查表头必录项
				UIUtils.checkRequireCompValue((JComponent)rqCompInfo.getComp());
			} else {
				KDTable table = rqCompInfo.getTable();
				String columName = rqCompInfo.getColName();
				String tblName = table.getName();
				if (hashTable.get(tblName) == null) hashTable.put(tblName, table);
				Vector<String> vecColumns = hashColumns.get(tblName);
				if (vecColumns == null) {
					vecColumns = new Vector<String>();
					hashColumns.put(tblName, vecColumns);
				}
				vecColumns.add(columName);				
			}	
		}

		/**检查表体的必须项*/
		Set<String> setTable = hashTable.keySet();
		Iterator<String> itTable = setTable.iterator();
		while (itTable.hasNext()) {
			String tblName = itTable.next();
			KDTable table = hashTable.get(tblName);
			Vector<String> vecColumns = hashColumns.get(tblName);
			String[] columns = PublicUtils.vectorToString(vecColumns);
			UIUtils.checkRequireCompValue(table, columns);
		}
	}
	
	/**
	 * 初始action的enabled
	 */
	protected void initDataStatus() {
		super.initDataStatus();
		loadFields();
		int bsValue = ((BillBaseStatusEnum)boxBaseStatus.getSelectedItem()).getValue();
		if (BillBaseStatusEnum.ADD_VALUE == bsValue) { //新增
			actionCopy.setEnabled(false);
			actionAudit.setEnabled(false);
			actionUnAudit.setEnabled(false);
			actionCreateTo.setEnabled(false);
			actionCreateFrom.setEnabled(true);
			actionPrint.setEnabled(false);
			actionPrintPreview.setEnabled(false);
		} else {
			actionPrint.setEnabled(true);
			actionPrintPreview.setEnabled(true);
		}		
		
		if (BillBaseStatusEnum.TEMPORARILYSAVED_VALUE == bsValue) { //保存
			actionCreateTo.setEnabled(false);
			actionAudit.setEnabled(false);
			actionUnAudit.setEnabled(false);
			actionCreateFrom.setEnabled(false);
			unLockUI();
		}
		
		if (BillBaseStatusEnum.SUBMITED_VALUE == bsValue) { //提交
			actionCreateTo.setEnabled(false);
			actionAudit.setEnabled(true);
			actionUnAudit.setEnabled(false);
			actionCreateFrom.setEnabled(false);
			actionSave.setEnabled(false);
		}
		
		if (BillBaseStatusEnum.AUDITED_VALUE == bsValue) { //审核
			actionAudit.setEnabled(false);
			actionUnAudit.setEnabled(true);
			actionCreateTo.setEnabled(true);
			actionCreateFrom.setEnabled(false);
			actionEdit.setEnabled(false);
			actionRemove.setEnabled(false);
			actionSave.setEnabled(false);
			actionSubmit.setEnabled(false);
			lockUIForViewStatus();
		}
		if (OprtState.EDIT.equals(getOprtState()) || OprtState.ADDNEW.equals(getOprtState())) {
			actionAddLine.setEnabled(true);
			actionInsertLine.setEnabled(true);
			actionRemoveLine.setEnabled(true);
			actionCopyLine.setEnabled(true);
		} else {
			actionAddLine.setEnabled(false);
			actionInsertLine.setEnabled(false);
			actionRemoveLine.setEnabled(false);
			actionCopyLine.setEnabled(false);
		}
		
	}
	
	protected void loadData() throws Exception {
		super.loadData();

		if ("ADDNEW".equals(getOprtState())) {
			createNewDataEx();
		}
		loadFields();
	}
	
	protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception {
		return null;
	}

	protected com.kingdee.bos.dao.IObjectValue createNewData() {
		
		return null;
	}


	@Override
	protected KDTable getDetailTable() {
		return kdtEntrys;
	}
	
	@Override
	public void doLayout() {
		super.doLayout();
		//设置焦点位置
		if (OprtState.ADDNEW.equals(getOprtState()) ||
			OprtState.EDIT.equals(getOprtState())) {
			defaultFocusOnEdit();
		}
	}
	
	/**
	 * 在新增及编辑状时，默认的焦点位置
	 */
	public void defaultFocusOnEdit() {
		if (txtNumber.isEnabled()) {
			txtNumber.requestFocusInWindow();
		} else {
			pkBizDate.requestFocusInWindow();
		}
	}
	/**
	 * 设置copy时，重置值 
	 */
	@Override
	protected void setFieldsNull(AbstractObjectValue newData) {
		try {
			ResetFieldCollection resetFieldCol = resetFieldstAfterCopy();
			Vector<ResetFieldInfo> vecProperty = resetFieldCol.getVecProperty();
			for (int i = 0; vecProperty != null && i < vecProperty.size(); i++) {
				ResetFieldInfo rfi = vecProperty.get(i);
				if (!newData.containsKey(rfi.getPropertyName())) {					
					MsgBoxEx.showDetailAndOK(null, "复制异常，字段标识有误", String.format("字段标识[%s]有误,请开发检查代码!",rfi.getPropertyName()), MsgBox.OK);					
					SysUtil.abort();
				}
				newData.put(rfi.getPropertyName(),rfi.getDefaultValue());
			}
			String[] entrysName = resetFieldCol.getEntryNames();
			for (int i = 0; entrysName != null && i < entrysName.length; i++) {
				String entryName = entrysName[i];
				if (!newData.containsKey(entryName)) {					
					MsgBoxEx.showDetailAndOK(null, "复制异常，字段标识有误", String.format("分录标识[%s]有误,请开发检查代码!",entryName), MsgBox.OK);					
					SysUtil.abort();
				}
				
				Vector<ResetFieldInfo> vecEntryColumn = resetFieldCol.getEntryColumn(entryName);		
				IObjectCollection entryCol = (IObjectCollection) newData.get(entryName);
				
				for (int j = 0; entryCol != null && j < entryCol.size(); j++) {
					IObjectValue entryInfo = entryCol.getObject(j);
					for (int k = 0; vecEntryColumn != null && k < vecEntryColumn.size(); k++) {
						ResetFieldInfo rfi = vecEntryColumn.get(k);
						if (!entryInfo.containsKey(rfi.getColumnFlagName())) {
							MsgBoxEx.showDetailAndOK(null, "复制异常，字段标识有误", String.format("分录[%s]中列标识[%s]有误,请开发检查代码!",rfi.getEntryName(),rfi.getColumnFlagName()), MsgBox.OK);
							SysUtil.abort();
						}
						entryInfo.put(rfi.getColumnFlagName(),rfi.getDefaultValue());
					}
				}
			}
			
		} catch (EASBizException e) {
			SysUtil.abort();
		}
		super.setFieldsNull(newData);
	}
	protected void afterSubmitAddNew() {
		super.afterSubmitAddNew();
		try {
			createNewDataEx();
		} catch (Exception e) {
			e.printStackTrace();
		}
		loadFields();
	}
	
	public SortColumnCollection getDefaultSortColumns() {
		return null;
	}
	
	@Override
	public void actionMultiColumnSort_actionPerformed(ActionEvent e)
			throws Exception {
		UIUtils.registerMultiColumnOrder(this, kdtEntrys, getDefaultSortColumns());
	}
}