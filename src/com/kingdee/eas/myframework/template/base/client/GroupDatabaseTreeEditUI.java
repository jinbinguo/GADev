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
		/**配置必录项*/
		registerRequireComp();
		/**配置编码字段*/
		setNumberEnabled();
	}
	
	protected void initDataStatus() {		
		super.initDataStatus();
		
		/** 重新设计'启用'、'禁用'的enabled值,只有在查看状态才可使用
		 * 
		 * PS: 这里为什么要用getDataObject()而不直接使用editData呢？
		 * 	   因为这时子类的editData并未被赋值，用getDataObject获取父类的的editData。
		 * 
		 * PS: 在父类的抽象类与子类的抽象类分别定义了editData对象，两者之间相互赋值又相互独立  
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
	 * 设置必录项
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
	 * 重置整单、行复制后的部分字段的默认值 
	 * @return
	 */
	public ResetFieldCollection resetFieldstAfterCopy() throws EASBizException {
		ResetFieldCollection rfc = new ResetFieldCollection();
		rfc.add(new ResetFieldInfo("deletedStatus",DeletedStatusEnum.OPEN));
		rfc.add(new ResetFieldInfo("scheduled", false));
		return rfc;
	} 
	

	/**
	 * 新增状态时单据体的默认值
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
			MsgBox.showInfo("修改中的数据，不能禁用");
			return;
		}
		if (!getMutex(editData.getString("id"))) {
			MsgBox.showInfo("对不起，当前对象已被其他的操作锁定!");
			return;
		}
		
		super.actionCancel_actionPerformed(e);
		unLockUI();
	}
	
	
	public void actionCancelCancel_actionPerformed(ActionEvent e)
			throws Exception {
		storeFields();
		if (editData.isValueChange()) {
			MsgBox.showInfo("修改中的数据，不能启用");
			return;
		}
		
		if (!getMutex(editData.getString("id"))) {
			MsgBox.showInfo("对不起，当前对象已被其他的操作锁定!");
			return;
		}
		super.actionCancelCancel_actionPerformed(e);
		lockUIForViewStatus();
	}
	


	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		/** 系统预设基础资料不允许编辑 */
		if(editData.isScheduled()) {
			String msg = EASResource.getString("com.kingdee.eas.basedata.scm.common.SCMResource.SystemSettingEdit");
			MsgBox.showInfo(this, msg);
			return;
		}
		/** 启用状态时不允许编辑*/
		if (editData.getDeletedStatus().equals(DeletedStatusEnum.OPEN)) {
			MsgBox.showInfo(this, "已启用数据,不能修改!");
			return;
		}
		
		super.actionEdit_actionPerformed(e);

		/** 编辑时，若是启用编码规则且[支持修改]未勾选时,编码不允许编辑*/
		setNumberEnabled();
		
	}
	
	public void actionSubmit_actionPerformed(ActionEvent e) throws Exception {
		MutexUtils.checkDataObjectLockWithExcepion(editData);	
		isVerifyInput = true;
		super.actionSubmit_actionPerformed(e);
		/** 编辑时，若是启用编码规则且[支持修改]未勾选时,编码不允许编辑*/
		setNumberEnabled();
	}
	

	public void actionRemove_actionPerformed(ActionEvent e) throws Exception {
		/** 系统预设基础资料不允许删除 */
		if(editData.isScheduled()) {
			String msg = EASResource.getString("com.kingdee.eas.basedata.scm.common.SCMResource.SystemSettingDel");
			MsgBox.showInfo(this, msg);
			return;
		}
		/** 启用状态基础资料不允许删除*/
		if (DeletedStatusEnum.OPEN.equals(editData.getDeletedStatus())) {
			MsgBox.showInfo(this, "已启用数据,不能删除!");
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
		if (orgId == null) {
			orgId = SysContext.getSysContext().getCurrentOrgUnit().getId().toString();
		}
		
		/** 若是启用编码规则且[支持修改]未勾选时,编码不允许编辑*/
		if (!CodingRuleUtils.isModifiable(editData, orgId)) {
			txtNumber.setEnabled(false);
		}
	}
	
	/**
	 * 界面值有效性的验证
	 */
	protected void verifyInput(ActionEvent e) throws Exception {
		if (!isVerifyInput) return;
		isVerifyInput = false;
		super.verifyInput(e);

		/** 检查必录项*/
		verifyRequireComp();
		
		/** 检查名称是否重复 */
		String strName = ((MultiLangItem)txtName.getDefaultLangItemData()).getData().toString();
		try {
			((IDataBase)getBizInterface()).checkNameDup(editData);
		} catch (Exception ee) {
			int iResult = MsgBox.showConfirm2(String.format("名称[%s]已重复存在，是否继续保存?",strName));
			if (MsgBox.CANCEL == iResult) {
				SysUtil.abort();
			}
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
			txtName.requestFocusInWindow();
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

	@Override
	protected IObjectValue createNewData() {

		return null;
	}

	@Override
	protected ICoreBase getBizInterface() throws Exception {

		return null;
	}

}