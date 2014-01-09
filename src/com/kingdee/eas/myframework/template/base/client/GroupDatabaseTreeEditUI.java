/**
 * output package name
 */
package com.kingdee.eas.myframework.template.base.client;

import java.awt.event.*;
import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.JComponent;

import org.apache.log4j.Logger;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.MultiLangItem;
import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.ctrl.swing.event.DataChangeListener;
import com.kingdee.bos.dao.AbstractObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.eas.basedata.framework.CUIDGetterFacadeFactory;
import com.kingdee.eas.basedata.framework.DataBaseDException;
import com.kingdee.eas.basedata.framework.ICUIDGetterFacade;
import com.kingdee.eas.basedata.ncm.DeletedStatusEnum;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.framework.*;
import com.kingdee.eas.framework.batchHandler.UtilRequest;
import com.kingdee.eas.framework.util.BaseDataFactory;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.template.base.SimpleDatabaseInfo;
import com.kingdee.eas.myframework.util.CodingRuleUtils;
import com.kingdee.eas.myframework.util.MutexUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.eas.myframework.vo.RequireCompCollection;
import com.kingdee.eas.myframework.vo.RequireCompInfo;
import com.kingdee.eas.myframework.vo.ResetFieldCollection;
import com.kingdee.eas.myframework.vo.ResetFieldInfo;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.ExceptionHandler;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

/**
 * output class name
 */
public class GroupDatabaseTreeEditUI extends AbstractGroupDatabaseTreeEditUI {
	
	private static final Logger logger = CoreUIObject.getLogger(GroupDatabaseTreeEditUI.class);
	private boolean isVerifyInput = false;
	
	public GroupDatabaseTreeEditUI() throws Exception {
		super();
	}
	

	public void onLoad() throws Exception {
		super.onLoad();
		actionSave.setVisible(false); 
		chkMenuItemSubmitAndAddNew.setSelected(false);
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
		/**���ñ�¼��*/
		registerRequireComp();
		/**���ñ����ֶ�*/
		setNumberEnabled();
	}
	
	protected void initDataStatus() {		
		super.initDataStatus();
		
		/** �������'����'��'����'��enabledֵ,ֻ���ڲ鿴״̬�ſ�ʹ��
		 * 
		 * PS: ����ΪʲôҪ��getDataObject()����ֱ��ʹ��editData�أ�
		 * 	   ��Ϊ��ʱ�����editData��δ����ֵ����getDataObject��ȡ����ĵ�editData��
		 * 
		 * PS: �ڸ���ĳ�����������ĳ�����ֱ�����editData��������֮���໥��ֵ���໥����  
		 */
		
		if(isViewStatus() && getDataObject().get("deletedStatus") != null && getDataObject().getInt("deletedStatus") == 1) {
			actionCancel.setEnabled(true);
			actionCancelCancel.setEnabled(false);
        } else if(isViewStatus() && getDataObject().get("deletedStatus") != null && getDataObject().getInt("deletedStatus") == 2)  {    
        	actionCancel.setEnabled(false);
        	actionCancelCancel.setEnabled(true);
        } else if(!isViewStatus())  {
        	actionCancel.setEnabled(false);
        	actionCancelCancel.setEnabled(false);
        }
		DeletedStatusEnum deletedStatus =  (DeletedStatusEnum) boxDeletedStatus.getSelectedItem();
		if (deletedStatus.getValue() == DeletedStatusEnum.OPEN_VALUE) {
			actionRemove.setEnabled(false);
		}
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
		requireCompCol.add(txtName);
		return requireCompCol;
	}
	
	/**
	 * �����������и��ƺ�Ĳ����ֶε�Ĭ��ֵ 
	 * @return
	 */
	public ResetFieldCollection resetFieldstAfterCopy() throws EASBizException {
		ResetFieldCollection rfc = new ResetFieldCollection();
		rfc.add(new ResetFieldInfo("deletedStatus",DeletedStatusEnum.OPEN));
		rfc.add(new ResetFieldInfo("scheduled", false));
		return rfc;
	} 
	

	/**
	 * ����״̬ʱ�������Ĭ��ֵ
	 */
	protected void createNewDataEx() throws Exception {		
		Date currentDate = SysUtil.getAppServerTime(null);
		editData.setDeletedStatus(DeletedStatusEnum.OPEN);
		editData.setScheduled(false);
	}
	
	protected IObjectValue createNewDetailData(KDTable table) {
		IObjectValue valueEntryInfo = new SimpleDatabaseInfo();
		return valueEntryInfo;
	}
	
	private boolean isViewStatus() {
		return "VIEW".equals(getOprtState()) || isSaved();
		
	}
	
	public void actionCancel_actionPerformed(ActionEvent e) throws Exception {
		
		storeFields();
		if (editData.isValueChange()) {
			MsgBox.showInfo("�޸��е����ݣ����ܽ���");
			return;
		}
		if (!getMutex(editData.getString("id"))) {
			MsgBox.showInfo("�Բ��𣬵�ǰ�����ѱ������Ĳ�������!");
			return;
		}
		
		super.actionCancel_actionPerformed(e);
		unLockUI();
	}
	
	
	public void actionCancelCancel_actionPerformed(ActionEvent e)
			throws Exception {
		storeFields();
		if (editData.isValueChange()) {
			MsgBox.showInfo("�޸��е����ݣ���������");
			return;
		}
		
		if (!getMutex(editData.getString("id"))) {
			MsgBox.showInfo("�Բ��𣬵�ǰ�����ѱ������Ĳ�������!");
			return;
		}
		super.actionCancelCancel_actionPerformed(e);
		lockUIForViewStatus();
	}
	


	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		/** ϵͳԤ��������ϲ�����༭ */
		if(editData.isScheduled()) {
			String msg = EASResource.getString("com.kingdee.eas.basedata.scm.common.SCMResource.SystemSettingEdit");
			MsgBox.showInfo(this, msg);
			return;
		}
		/** ����״̬ʱ������༭*/
		if (editData.getDeletedStatus().equals(DeletedStatusEnum.OPEN)) {
			MsgBox.showInfo(this, "����������,�����޸�!");
			return;
		}
		
		super.actionEdit_actionPerformed(e);

		/** �༭ʱ���������ñ��������[֧���޸�]δ��ѡʱ,���벻����༭*/
		setNumberEnabled();
		
	}
	
	public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
		MutexUtils.checkDataObjectLockWithExcepion(editData);	
		isVerifyInput = true;
		super.actionSubmit_actionPerformed(e);
		/** �༭ʱ���������ñ��������[֧���޸�]δ��ѡʱ,���벻����༭*/
		setNumberEnabled();
	}
	

	public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
		/** ϵͳԤ��������ϲ�����ɾ�� */
		if(editData.isScheduled()) {
			String msg = EASResource.getString("com.kingdee.eas.basedata.scm.common.SCMResource.SystemSettingDel");
			MsgBox.showInfo(this, msg);
			return;
		}
		/** ����״̬�������ϲ�����ɾ��*/
		if (DeletedStatusEnum.OPEN.equals(editData.getDeletedStatus())) {
			MsgBox.showInfo(this, "����������,����ɾ��!");
			return;
		}
		
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
			IObjectValue value = getUILifeCycleHandler().innerCreateNewData(
					createNewData());
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
		if (orgId == null) {
			orgId = SysContext.getSysContext().getCurrentOrgUnit().getId().toString();
		}
		
		/** �������ñ��������[֧���޸�]δ��ѡʱ,���벻����༭*/
		if (!CodingRuleUtils.isModifiable(editData, orgId)) {
			txtNumber.setEnabled(false);
		}
	}
	
	/**
	 * ����ֵ��Ч�Ե���֤
	 */
	protected void verifyInput(ActionEvent e) throws Exception {
		if (!isVerifyInput) return;
		isVerifyInput = false;
		super.verifyInput(e);

		/** ����¼��*/
		verifyRequireComp();
		
		/** ��������Ƿ��ظ� */
		String strName = ((MultiLangItem)txtName.getDefaultLangItemData()).getData().toString();
		try {
			((IDataBase)getBizInterface()).checkNameDup(editData);
		} catch (Exception ee) {
			int iResult = MsgBox.showConfirm2(String.format("����[%s]���ظ����ڣ��Ƿ��������?",strName));
			if (MsgBox.CANCEL == iResult) {
				SysUtil.abort();
			}
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
	protected void loadData() throws Exception {
		super.loadData();

		if ("ADDNEW".equals(getOprtState())) {
			createNewDataEx();
		}
		loadFields();
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
			txtName.requestFocusInWindow();
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
	
	@Override
	protected void afterSubmitAddNew() {
		super.afterSubmitAddNew();
		try {
			createNewDataEx();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loadFields();
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

	@Override
	protected IObjectValue createNewData() {

		return null;
	}

	@Override
	protected ICoreBase getBizInterface() throws Exception {

		return null;
	}

}