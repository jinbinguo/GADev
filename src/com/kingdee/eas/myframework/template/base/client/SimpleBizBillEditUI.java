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
    
    /**����̳��Զ���ģ�淢��ʱ��bug,�޶���kdtEntrys_detailPanel*/
    public com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtEntrys_detailPanel = null;
    
 
	public SimpleBizBillEditUI() throws Exception {
		super();
	}




	public void onLoad() throws Exception {
		super.onLoad();
		/**
		 * ������ҵ����֯���õ��ݱ���ؼ�
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
		
		/**��������btnAddnewline��btnInsertNewLine��btnRemoveLines���С��ť���¼�,�����׼��Ʒδ��editUI�ϵ�action�¼�������*/
		KDWorkButton btnAddNewLine = ((DetailPanel)kdtEntrys_detailPanel).getAddNewLineButton();
		KDWorkButton btnInsertLine = ((DetailPanel)kdtEntrys_detailPanel).getInsertLineButton();
		KDWorkButton btnRemoveLines = ((DetailPanel)kdtEntrys_detailPanel).getRemoveLinesButton();
		resetBtnAction(btnAddNewLine, actionAddLine);
		resetBtnAction(btnInsertLine, actionInsertLine);
		resetBtnAction(btnRemoveLines, actionRemoveLine);
		/**���ñ�¼��*/
		registerRequireComp();
		/**���ñ����ֶ�*/
		setNumberEnabled();
		

	}
	@Override
	protected void initWorkButton() {
		super.initWorkButton();
		try {
			/**�����϶�������ť*/
			KDPanel kdtEntrys_detailPanel_controlPanel = (KDPanel) InvokeUtils.getFieldValue(kdtEntrys_detailPanel, "controlPanel");
			Rectangle rect = (Rectangle)InvokeUtils.getFieldValue(kdtEntrys_detailPanel, "rect");
			btnMultiColumnSort.setFactType(1);
			btnMultiColumnSort.setOpaque(true);
			kdtEntrys_detailPanel_controlPanel.add(btnMultiColumnSort, new com.kingdee.bos.ctrl.swing.KDLayout.Constraints(rect.width - 113, 5, 22, 19, 9));
		} catch (Exception e){}
	}
	
	/**
	 * ���ñ�¼��
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
	 * �����������и��ƺ�Ĳ����ֶε�Ĭ��ֵ 
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
	 * ����״̬ʱ�������Ĭ��ֵ
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
		/** �༭ʱ���������ñ��������[֧���޸�]δ��ѡʱ,���벻����༭*/
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
			MsgBoxEx.showInfo("�������޸ģ��������ύ������ˣ�");
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
	 * ��д����ɾ����������Ϊҵ��ģ���ɵĳ�������뷽��removeByPK(IObjectPK)����Ӵ���Ա���Ļ��գ�
	 * ����ListUI������ɾ��������¼ʱ��������������ɵĻ��մ��벻�ܻ������б��룬ֻ����һ����
	 * �Ҳ���ͬһ�����У����ܵ��¶Ϻų��֣�����ͳһ�ĵ�����˴���
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
		//��ɾ�������һ��ʱ����������¼
		if ("ADDNEW".equals(getOprtState())) {
			createNewDataEx();
		}
		loadFields();
    }
    public void actionAddNew_actionPerformed(ActionEvent e) throws Exception {
		super.actionAddNew_actionPerformed(e);
		createNewDataEx(); //��ͷĬ��ֵ 
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
	 * ����table��С��ť���¼�
	 * ����������ٰ�
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
	 * ���ñ���ؼ��ı༭��
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
		
		/** �������ñ��������[֧���޸�]δ��ѡʱ,���벻����༭*/
		if (!CodingRuleUtils.isModifiable(editData, orgId)) {
			txtNumber.setEnabled(false);
		}
	}
    /**
     * ����removeByPK(IObjectPK),��ҵ��ģ�������ɵĴ���removeByPK(IObjectPK)���Զ����ձ��룬������ͬһ�������Ը��쵽��ִ̨�У�����ͬһ����
     * ������removeByPK(IObjectPK)һ�£�δ�иĶ�����̳й�ϵ��ԭ�����Ա���ÿ���������д
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
    
    /**�ӵײ�����뿽������*/
	private void checkForSType(String actionName) throws Exception {
		checkPermission(actionName);
	}
	/**�ӵײ�����뿽������*/
	private void checkPermission(String action) throws Exception {
		if (getControlType().equalsIgnoreCase("S1")) {
			if (!getCurrentCUID().equals("00000000-0000-0000-0000-000000000000CCE7AED4"))
				throwsExceptionForNoPermission(action);
		} else if ((getControlType().equalsIgnoreCase("S3") || getControlType()
				.equalsIgnoreCase("S4")) && !action.equals("ACTION_ADDNEW") && !getCurrentCUID().equals(getCUIDFromBizobject()))
			throwsExceptionForNoPermission(action);
	}
	/**�ӵײ�����뿽������*/
    private String getCUIDFromBizobject() throws Exception {
		return getICGF().getCUID(editData.getId().toString());
	}
    /**�ӵײ�����뿽������*/
	private ICUIDGetterFacade getICGF() throws Exception {
		return CUIDGetterFacadeFactory.getRemoteInstance();
	}
	/**�ӵײ�����뿽������*/
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
    /**�ӵײ�����뿽������*/
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
    /**�ӵײ�����뿽������*/
	private void innerLoadFields() {
		EventListener lsts[] = removeDetailTableListener(com.kingdee.bos.ctrl.kdf.table.event.KDTPropertyChangeListener.class);
		loadFields();
		restoreDetailTableListener(com.kingdee.bos.ctrl.kdf.table.event.KDTPropertyChangeListener.class,lsts);
	}
	
	public void showAuditMessage() {
		showBarMessage("��˳ɹ�");
	}
	public void showUnAuditMessage() {
		showBarMessage("����˳ɹ�");
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
	 * ����ˢ�±�����
	 * @throws Exception
	 */
	public void refresh() throws Exception {
		IObjectPK pk = new ObjectUuidPK(editData.getId());
		editData = (SimpleBizBillInfo) getBizInterface().getValue(pk, getSelectors());
		setDataObject(editData);
	}
	/**
	 * ����ֵ��Ч�Ե���֤
	 */
	protected void verifyInput(ActionEvent e) throws Exception {
		super.verifyInput(e);
	
		/**����м�¼*/
		if (getDetailTable().getRowCount() < 1) {
			throw new EASBizException(new NumericExceptionSubItem("","�м�¼����Ϊ�գ�"));
		}
		/** ����¼��*/
		verifyRequireComp();
		
		
	}		
	
	/**
	 * �����к󣬸���������Ĭ��ֵ�ֶ�������ֵ
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
					MsgBoxEx.showDetailAndOK(null, "�и����쳣���ֶα�ʶ����", String.format("��¼��ʶ[%s]����,�뿪��������!",entryNames[i]), MsgBox.OK);	
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
	 * ��֤��¼��
	 */
	public void verifyRequireComp() throws Exception {
		
		/** �Ƿ����ñ������,δ���ñ���򣬲��������Ϊ�� */
		if (!CodingRuleUtils.hasCodingRule(editData, null) && StringUtils.isEmpty(txtNumber.getText())) {
			txtNumber.requestFocus();
			throw new EASBizException(new NumericExceptionSubItem("","���벻��Ϊ�գ�"));
		}
		
		RequireCompCollection requireCompCol = registerRequireComp();
		

		/**����֤��ͷ�������������֤���*/
		HashMap<String, KDTable> hashTable = new HashMap<String, KDTable>();
		HashMap<String, Vector<String>> hashColumns = new HashMap<String, Vector<String>>();
		Set<String> keySet = requireCompCol.key();
		Iterator<String> itSet = keySet.iterator();
		while (itSet.hasNext()) {
			String key = itSet.next();
			RequireCompInfo rqCompInfo = requireCompCol.get(key);
			if (!rqCompInfo.isTable()) { //����ͷ��¼��
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

		/**������ı�����*/
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
	 * ��ʼaction��enabled
	 */
	protected void initDataStatus() {
		super.initDataStatus();
		loadFields();
		int bsValue = ((BillBaseStatusEnum)boxBaseStatus.getSelectedItem()).getValue();
		if (BillBaseStatusEnum.ADD_VALUE == bsValue) { //����
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
		
		if (BillBaseStatusEnum.TEMPORARILYSAVED_VALUE == bsValue) { //����
			actionCreateTo.setEnabled(false);
			actionAudit.setEnabled(false);
			actionUnAudit.setEnabled(false);
			actionCreateFrom.setEnabled(false);
			unLockUI();
		}
		
		if (BillBaseStatusEnum.SUBMITED_VALUE == bsValue) { //�ύ
			actionCreateTo.setEnabled(false);
			actionAudit.setEnabled(true);
			actionUnAudit.setEnabled(false);
			actionCreateFrom.setEnabled(false);
			actionSave.setEnabled(false);
		}
		
		if (BillBaseStatusEnum.AUDITED_VALUE == bsValue) { //���
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
		//���ý���λ��
		if (OprtState.ADDNEW.equals(getOprtState()) ||
			OprtState.EDIT.equals(getOprtState())) {
			defaultFocusOnEdit();
		}
	}
	
	/**
	 * ���������༭״ʱ��Ĭ�ϵĽ���λ��
	 */
	public void defaultFocusOnEdit() {
		if (txtNumber.isEnabled()) {
			txtNumber.requestFocusInWindow();
		} else {
			pkBizDate.requestFocusInWindow();
		}
	}
	/**
	 * ����copyʱ������ֵ 
	 */
	@Override
	protected void setFieldsNull(AbstractObjectValue newData) {
		try {
			ResetFieldCollection resetFieldCol = resetFieldstAfterCopy();
			Vector<ResetFieldInfo> vecProperty = resetFieldCol.getVecProperty();
			for (int i = 0; vecProperty != null && i < vecProperty.size(); i++) {
				ResetFieldInfo rfi = vecProperty.get(i);
				if (!newData.containsKey(rfi.getPropertyName())) {					
					MsgBoxEx.showDetailAndOK(null, "�����쳣���ֶα�ʶ����", String.format("�ֶα�ʶ[%s]����,�뿪��������!",rfi.getPropertyName()), MsgBox.OK);					
					SysUtil.abort();
				}
				newData.put(rfi.getPropertyName(),rfi.getDefaultValue());
			}
			String[] entrysName = resetFieldCol.getEntryNames();
			for (int i = 0; entrysName != null && i < entrysName.length; i++) {
				String entryName = entrysName[i];
				if (!newData.containsKey(entryName)) {					
					MsgBoxEx.showDetailAndOK(null, "�����쳣���ֶα�ʶ����", String.format("��¼��ʶ[%s]����,�뿪��������!",entryName), MsgBox.OK);					
					SysUtil.abort();
				}
				
				Vector<ResetFieldInfo> vecEntryColumn = resetFieldCol.getEntryColumn(entryName);		
				IObjectCollection entryCol = (IObjectCollection) newData.get(entryName);
				
				for (int j = 0; entryCol != null && j < entryCol.size(); j++) {
					IObjectValue entryInfo = entryCol.getObject(j);
					for (int k = 0; vecEntryColumn != null && k < vecEntryColumn.size(); k++) {
						ResetFieldInfo rfi = vecEntryColumn.get(k);
						if (!entryInfo.containsKey(rfi.getColumnFlagName())) {
							MsgBoxEx.showDetailAndOK(null, "�����쳣���ֶα�ʶ����", String.format("��¼[%s]���б�ʶ[%s]����,�뿪��������!",rfi.getEntryName(),rfi.getColumnFlagName()), MsgBox.OK);
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