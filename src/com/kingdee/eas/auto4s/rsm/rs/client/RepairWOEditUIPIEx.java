package com.kingdee.eas.auto4s.rsm.rs.client;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Action;
import javax.swing.event.EventListenerList;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.extendcontrols.BizDataFormat;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTMergeManager;
import com.kingdee.bos.ctrl.kdf.table.KDTRow;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTSortManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditListener;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectListener;
import com.kingdee.bos.ctrl.kdf.table.foot.KDTFootManager;
import com.kingdee.bos.ctrl.kdf.util.render.ObjectValueRender;
import com.kingdee.bos.ctrl.kdf.util.style.Styles.HorizontalAlignment;
import com.kingdee.bos.ctrl.swing.KDContainer;
import com.kingdee.bos.ctrl.swing.KDLayout;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.ctrl.swing.event.CommitEvent;
import com.kingdee.bos.ctrl.swing.event.CommitListener;
import com.kingdee.bos.ctrl.swing.event.DataChangeEvent;
import com.kingdee.bos.ctrl.swing.event.DataChangeListener;
import com.kingdee.bos.ctrl.swing.event.SelectorEvent;
import com.kingdee.bos.ctrl.swing.event.SelectorListener;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.data.SortType;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemCollection;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.entity.SorterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.IUIFactory;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.auto4s.bdm.pbd.BrandInfo;
import com.kingdee.eas.auto4s.bdm.pbd.IVehicleRepairRemark;
import com.kingdee.eas.auto4s.bdm.pbd.MakeControl;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairRemarkCollection;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairRemarkFactory;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairRemarkInfo;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleRepairSenderInfo;
import com.kingdee.eas.auto4s.bdm.rsm.PaymentClassifyInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum;
import com.kingdee.eas.auto4s.bdm.rsm.RepairClassifyInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairItemEntryInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairItemStatusEnum;
import com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo;
import com.kingdee.eas.auto4s.bdm.rsm.SettlementObjectEnum;
import com.kingdee.eas.auto4s.bdm.rsm.client.RepairItemEntryListUI;
import com.kingdee.eas.auto4s.commonutil.CommonUtils;
import com.kingdee.eas.auto4s.rsm.rs.IWarrRemind;
import com.kingdee.eas.auto4s.rsm.rs.RepairWOCollection;
import com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOItemSpEntryInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum;
import com.kingdee.eas.auto4s.rsm.rs.WarrRemindFactory;
import com.kingdee.eas.auto4s.rsm.rs.WarrRemindInfo;
import com.kingdee.eas.auto4s.rsm.rs.util.client.RsQueryF7Utils;
import com.kingdee.eas.auto4s.rsm.rs.util.client.RsUtils;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.assistant.MeasureUnitInfo;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.master.material.UsedStatusEnum;
import com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.framework.client.multiDetail.DetailPanel;
import com.kingdee.eas.ga.basedata.UserTypeEnum;
import com.kingdee.eas.ga.rs.CustomerAccountInfo;
import com.kingdee.eas.ga.rs.IEnum;
import com.kingdee.eas.ga.rs.RepairWOBizTypeInfo;
import com.kingdee.eas.ga.rs.RepairWOStatusEnum;
import com.kingdee.eas.ga.rs.TEnum;
import com.kingdee.eas.ga.rs.WInfo;
import com.kingdee.eas.ga.rs.client.RepairSenderF7UI;
import com.kingdee.eas.ga.util.GAUtils;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.common.SortTypeEnum;
import com.kingdee.eas.myframework.comparators.table.KDTableComparatorUtils;
import com.kingdee.eas.myframework.comparators.table.SortColumnCollection;
import com.kingdee.eas.myframework.comparators.table.SortColumnInfo;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.InvokeUtils;
import com.kingdee.eas.myframework.util.PermUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;
import com.tool.simplecode.Invoke;

public class RepairWOEditUIPIEx extends RepairWOEditUI {
	
	private static final String CR = "\n\r";
	private UserTypeEnum curUserType = null;
	//private WarrantyTypeInfo defaultWarrantTypeInfo = null;
	private RepairClassifyInfo defaultRepairClassifyInfo = null;
	private RepairItemInfo defaultRepairItemForTXT = null;
	private RepairItemInfo defaultRepairItemForDJQ = null;
	
	private RepairTypeInfo defaultRepairTypeInfo = null;
	private boolean hasPermission_OprtRetailLine = false; //零售行操作权限 
	private boolean hasPermission_OprtRepairLine = false; //维修行操作权限
	private boolean hasPermission_OprtRetailItemspName = false; //操作配件行说明权限
	private boolean hasPermission_OprtRetailPrice = false; //操作配件行价格
	private boolean hasPermission_OprtRetailBelowCost = false; //配件低于成本价销售
	
	private BigDecimal maxRepairDiscountRate = null; //最大项目折扣率
	private BigDecimal maxRetailDiscountRate = null; //最大零售折扣率
	
	private boolean isLoadOprtPermission = false; //是否加载了操作权限
	
	public RepairWOEditUIPIEx() throws Exception {
		super();
		getUserType();
	
		//重新调整维修方式顺序
		repairWay.removeAllItems();
		repairWay.addItem(RepairWayEnum.DEFAULT);
		repairWay.addItem(RepairWayEnum.REPAIRSUPPLY);
		repairWay.addItem(RepairWayEnum.GROUPBY);
		// this.repairWay.addItems(EnumUtils.getEnumList("com.kingdee.eas.auto4s.rsm.rs.RepairWayEnum").toArray());

	}
	@Override
	protected void loadData() throws Exception {
		super.loadData();
		
	}
	
	private void getLineOprtPermission() throws Exception {
		UserInfo userInfo = SysContext.getSysContext().getCurrentUserInfo();
		ServiceOrgUnitInfo seriveOrgInfo =  (ServiceOrgUnitInfo)prmtOrgUnit.getValue();
		hasPermission_OprtRetailLine = PermUtils.hasFunctionPermission(null, userInfo, seriveOrgInfo, "oprtRetailLine");
		hasPermission_OprtRepairLine = PermUtils.hasFunctionPermission(null, userInfo, seriveOrgInfo, "oprtRepairLine");
		hasPermission_OprtRetailItemspName = PermUtils.hasFunctionPermission(null, userInfo, seriveOrgInfo, "oprtRetailItemspName");
		hasPermission_OprtRetailPrice = PermUtils.hasFunctionPermission(null, userInfo, seriveOrgInfo, "oprtRetailPrice ");
		hasPermission_OprtRetailBelowCost = PermUtils.hasFunctionPermission(null, userInfo, seriveOrgInfo, "oprtRetailBelowCost");
		isLoadOprtPermission = true;
 
	}
	@Override
	protected IObjectValue createNewData() {
		IObjectValue value =  super.createNewData();
		//value.put("Mile", 1);
		return value;
	}
	
	/**
	 * 获取用户账号类别
	 * @throws Exception
	 */
	private void getUserType() throws Exception {
		UserInfo curUserInfo = SysContext.getSysContext().getCurrentUserInfo();
		String sql = "select CFUserType from T_PM_User where FID='" + curUserInfo.getString("id") + "'";
		try {
			IRowSet rs = DBUtils.executeQuery(null, sql);
			if (rs != null && rs.next()) 
				curUserType = UserTypeEnum.getEnum(rs.getString("CFUserType"));
		} catch (Exception e) {
			UIUtils.handUIExceptionAndAbort(e);
		}
		if (curUserType == null) {
			MsgBoxEx.showInfo(String.format("当前用户[%s]必须先设置账户类别,否则不能使用维修工单！",curUserInfo.getName()));
			abort();
		} 
	}
	@Override
	public void setOprtState(String oprtType) {
		super.setOprtState(oprtType);
		prmtCustomerAccount.setEditable(true);
	    prmtCustomerAccount.setReadOnly(false);
	    prmtCustomerAccount.setEnabled(true);
	}
	@Override
	public void onLoad() throws Exception {
		
		super.onLoad();
		
		
		//给[项目/配件明细]的小行操作按钮重新绑定action
		KDWorkButton btnAddNewLine = ((DetailPanel)kdtRWOItemSpEntry_detailPanel).getAddNewLineButton();
		KDWorkButton btnInsertLine = ((DetailPanel)kdtRWOItemSpEntry_detailPanel).getInsertLineButton();
		KDWorkButton btnRemoveLines = ((DetailPanel)kdtRWOItemSpEntry_detailPanel).getRemoveLinesButton();
		resetBtnAction(btnAddNewLine, actionAddLine);
		resetBtnAction(btnInsertLine, actionInsertLine);
		resetBtnAction(btnRemoveLines, actionRemoveLine);
		
		
		//重新调整kdtRWOItemSpEntry位置
		kdtRWOItemSpEntry_detailPanel.setVisible(false);
		Rectangle r = kDPRwoItemSp.getComponent(0).getBounds();
		KDLayout.Constraints c = new KDLayout.Constraints(r.x,r.y,r.width,r.height,KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT);
		kDPRwoItemSp.add(kdtRWOItemSpEntry,c);
		
		kDPRwoItemSp.add(btnRemoveLines, new com.kingdee.bos.ctrl.swing.KDLayout.Constraints(r.width - 32, 5, 22, 19, 9));
		

		
		
		actionAddLine.setVisible(false);
		actionInsertLine.setVisible(false);
		//btnRemoveLines.setVisible(false);
		
		
		//把底盘号enable掉，解决txtVIN_focusLost重新设置车辆档案引起的重新初化维修项目、配件等分录
		txtVIN.setEnabled(false);
		
		
		//----------隐藏页签
		
		kDTabbedPane1.remove(kDPHideField);
		kDTRWOPane.remove(kDPRwoItem);
		kDTRWOPane.remove(kDPRwoSp);
		kDTRWOPane.remove(kDPSpk);
		kDTRWOPane.remove(kDPAct);
		kDTRWOPane.remove(kDPAcc);
		kDTRWOPane.remove(KDPAmount);

		//-----------隐藏工具栏按钮
		actionSubmit.setVisible(false); //提交
		actionInvalid.setVisible(false); //作废
		actionUninvalid.setVisible(false);//取消作废
		actionAdd.setVisible(false);//追加项目
		actionEnterAdd.setVisible(false);//追加确认
		actionSuggest.setVisible(false);//建议维修项目
		actionBo.setVisible(false);//BO件确认
		actionCancelBo.setVisible(false);//BO件反确认
		actionDispatching.setVisible(false);//派工
		actionCancelAssign.setVisible(false);//取消派工
		actionItemIssue.setVisible(false);//出库
		actionTimeBooking.setVisible(false);//工时登记
		actionUnTimeBooking.setVisible(false);//取消工时等级
		actionAdjust.setVisible(false);//整单调整
		
		
		txtCompanyNumber.setEnabled(false);
		
		defaultValueForAddNew();
		
		registePrmtMaterialF7();
		registePrmtRepairItemF7();
		//registePrmtRepairManF7();
		appendItemSpFoot();
		setCmbT_Permission();
		
		prmtCustomerAccount.setEditable(true);
	    prmtCustomerAccount.setReadOnly(false);
	    prmtCustomerAccount.setEnabled(true);
	    
	    actionAudit.setVisible(false);
	    actionUnAudit.setVisible(false);
	    
	    //获取维修、零售最大折扣率
		
	    UserInfo userInfo = SysContext.getSysContext().getCurrentUserInfo();
	    String sql = String.format("select isnull(CFMaxRepairDiscountRate,0) maxRepairDiscountRate," +
	    		"isnull(CFMaxRetailDiscountRate,0) maxRetailDiscountRate from T_PM_User where FID='%s'",userInfo.getString("id"));
	    IRowSet rs = DBUtils.executeQuery(null, sql);
	    if (rs!=null && rs.next()) {
	    	maxRepairDiscountRate = rs.getBigDecimal("maxRepairDiscountRate");
	    	maxRetailDiscountRate = rs.getBigDecimal("maxRetailDiscountRate");
	    }
	    
	    prmtBrand.setEnabled(false);
	    actionShowRemarkList.setEnabled(true);
	    txtTel.setRequired(false);
	}

	private void defaultValueForAddNew() throws Exception {
		if (getOprtState().equals(OprtState.ADDNEW)) {
			VehicleInfo defaultVehicleInfo = GAUtils.getDefualtVehicleInfo(null);
			prmtVehicle.setValue(defaultVehicleInfo);
			
			CustomerAccountInfo defaultCustomerAccountInfo = GAUtils.getDefaultCustomerAccountInfo(null);
			prmtCustomerAccount.setValue(defaultCustomerAccountInfo);
			
			/**上次里程+1*/
			String sql = String.format("select isnull(max(FMile),0)+1 from T_ATS_RepairWO where FVehicleID='%s'", defaultVehicleInfo.getString("id"));
			IRowSet rs = DBUtils.executeQuery(null, sql);
			if (rs != null && rs.next()) {
				BigDecimal mile = rs.getBigDecimal(1);
				txtMile.setValue(mile);
			}
			
			RepairWOBizTypeInfo defaultBizType = GAUtils.getDefaultBizType(null);
			prmtrepairBizType.setValue(defaultBizType);
			
		}
		BrandInfo brandInfo = (BrandInfo) prmtBrand.getValue();
		if (brandInfo != null)
			defaultRepairClassifyInfo = GAUtils.getDefaultRepairClassify(null,brandInfo);
		
	}
	@Override
	public void actionAddNew_actionPerformed(ActionEvent e) throws Exception {
		super.actionAddNew_actionPerformed(e);
		defaultValueForAddNew();
	}
	@Override
	public void initF7Filter() {
		super.initF7Filter();
		
		//重新绑定项目&配件F7
		IColumn colItem = kdtRWOItemSpEntry.getColumn("repairItem");
		IColumn colSp = kdtRWOItemSpEntry.getColumn("material");

		//initItemSpRepairItemEntryF7(colItem);
		//initItemSpMaterialF7(colSp);

		kdtRWOItemSpEntry.addKDTSelectListener(new KDTSelectListener() {

			public void tableSelectChanged(KDTSelectEvent kdtselectevent) {
				kdtRWOItemSpEntry.cellAtPosition(kdtselectevent.getSelectBlock().getBeginRow(),
						kdtselectevent.getSelectBlock().getBeginCol());
				
			}
			
		});
		
		kdtRWOItemSpEntry.addKDTEditListener(new KDTEditAdapter() {
			private Object oldValue = null;
			
			@Override
			public void editStarting(KDTEditEvent kdteditevent) {
				oldValue = kdteditevent.getValue();
				super.editStarting(kdteditevent);
				int rowIndex = kdteditevent.getRowIndex();
				IRow row = kdtRWOItemSpEntry.getRow(rowIndex);
				resetItemSpEditorLocked(row);
				kdtRWOItemSpEntry.cellAtPosition(kdteditevent.getRowIndex(),kdteditevent.getColIndex());
			}
			@Override
			public void editStopping(KDTEditEvent kdteditevent) {
				
				int rowIndex = kdteditevent.getRowIndex();
				IRow row = kdtRWOItemSpEntry.getRow(rowIndex);
				resetItemSpEditorLocked(row);

			}
			public void editStopped(KDTEditEvent kdteditevent) {
						
				int rowIndex = kdteditevent.getRowIndex();
				IRow row = kdtRWOItemSpEntry.getRow(rowIndex);
				Object newObj = kdteditevent.getValue();
				if (!PublicUtils.equals(oldValue, newObj)) {
					try {
						valueChangedForItemSpEntry(oldValue,kdteditevent);
					} catch (Exception e) {UIUtils.handUIException(e);}
				}
				resetItemSpEditorLocked(row);
			}
			@Override
			public void editValueChanged(KDTEditEvent kdteditevent) {
				super.editValueChanged(kdteditevent);			
			}
		});
		
		//kdtRWORepairItemEntry对KDTEditListener监听重新绑定，去除负数控制
		EventListenerList lstListenerRepairItem = kdtRWORepairItemEntry.getListenerList();
		if (lstListenerRepairItem != null) {
			Object[] listenerRepairItem = lstListenerRepairItem.getListeners(KDTEditListener.class);
			for (int i = 0; listenerRepairItem != null && i < listenerRepairItem.length; i++) {
				String listenerNameRepairItem = listenerRepairItem[i].toString();
				//去掉RepairWOEditUI$添加的匿名监听
				if (listenerNameRepairItem.startsWith("com.kingdee.eas.auto4s.rsm.rs.client.RepairWOEditUI$"))
					kdtRWORepairItemEntry.removeKDTEditListener((KDTEditListener) listenerRepairItem[i]);
			} 
		}
		
		kdtRWORepairItemEntry.addKDTEditListener(new KDTEditAdapter() {
			 public void editStarting(KDTEditEvent e) {
				 kdtRWORepairItemEntry_editStartingNew(e); 
			 }
			 
			 public void editStopped(KDTEditEvent e) {
				 kdtRWORepairItemEntry_editStoppedNew(e);
			 }
			
		});	
		

		
	}
	
	@Override
	protected void initListener() {
		super.initListener();
		KDTSortManager sm = new KDTSortManager(kdtRWOItemSpEntry) {
			int pkgIndex = kdtRWOItemSpEntry.getColumnIndex("repairPkg");
			public void sort(int colIndex, int sortType) {
				if (pkgIndex == colIndex) {
					try {
						
						SortColumnCollection sortColumns = new SortColumnCollection();
						sortColumns.add(new SortColumnInfo("repairPkg",SortTypeEnum.getEnum(sortType)));
						sortColumns.add(new SortColumnInfo("t",SortTypeEnum.getEnum(sortType)));
						sortColumns.add(new SortColumnInfo("seq",SortTypeEnum.getEnum(sortType)));
					
						KDTableComparatorUtils tblComparatorUtils = new KDTableComparatorUtils(kdtRWOItemSpEntry, sortColumns);
				    	List<IRow> lstRow = tblComparatorUtils.sort();
				    	loadFields();
				    	kdtRWOItemSpEntry.removeRows();
				    	for (int i = 0; i < lstRow.size(); i++) {
				    		kdtRWOItemSpEntry.addRow(i, lstRow.get(i));
				    	}
				    	changeHeadStatus(this.colIndex, colIndex, sortType);
					} catch (Exception e) {
						UIUtils.handUIException(e);
					}
					
				} else {
					super.sort(colIndex, sortType);
				}
			}
		};
		sm.setSortAuto(true);
		sm.setClickCount(2);
		for(int i = 0; i < kdtRWOItemSpEntry.getColumnCount(); i++)
			kdtRWOItemSpEntry.getColumn(i).setSortable(true);
		kdtRWOItemSpEntry.setSortMange(sm);
	}
	
	@Override
	public void prmtCustomerAccount_Changed() throws Exception {
		super.prmtCustomerAccount_Changed();
		if (UserTypeEnum.ForRepair.equals(curUserType)) { //维修
			txtsaleType.setText(UIRuleUtil.getString(UIRuleUtil.getProperty((IObjectValue)prmtCustomerAccount.getData(),"repairSaleType")));
			
		} else if (UserTypeEnum.ForRetail.equals(curUserType)) { //零售
			txtsaleType.setText(UIRuleUtil.getString(UIRuleUtil.getProperty((IObjectValue)prmtCustomerAccount.getData(),"retailSaleType")));
		} else {
			throw new EASBizException(new NumericExceptionSubItem("","当前用户账户类别设置不正确！"));
		}
	}
	
	
	protected void kdtRWORepairItemEntry_editStartingNew(KDTEditEvent e) {
		  KDTable tb = (KDTable)e.getSource();
          kdtRWORepairItemEntry_editStarting(e);
          if(tb.getColumn(e.getColIndex()).getFieldName().equals(RepairWOEditUI.Cell_ActualWorkTime))
              kdtRWORepairItemEntry_Starting(e);
          if(tb.getColumn(e.getColIndex()).getFieldName().equals("RepairItem"))
              repairItemNumberBefore = getRepairItemNumber(kdtRWORepairItemEntry.getCell(e.getRowIndex(), "RepairItem").getValue());

	}
	
	protected void kdtRWORepairItemEntry_editStoppedNew(KDTEditEvent e) {
		 KDTable tb = (KDTable)e.getSource();
         isModify = true;
         if(null != kdtRWORepairItemEntry.getCell(e.getRowIndex(), "SettleObject").getValue())  {
             SettlementObjectEnum setObject = (SettlementObjectEnum)kdtRWORepairItemEntry.getCell(e.getRowIndex(), "SettleObject").getValue();
             if(SettlementObjectEnum.CLUB == setObject)
                 return;
          }
         if(tb.getColumn(e.getColIndex()).getFieldName().equals("PaymentClassify"))
             try  {
                 kdtRWOItemEntryPamentChanged(e.getRowIndex(), e.getColIndex());
             } catch(Exception e1)  {
                 e1.printStackTrace();
             }
             
         try {
             if(tb.getColumn(e.getColIndex()).getFieldName().equals("RepairItem")
            		 || tb.getColumn(e.getColIndex()).getFieldName().equals("SettleObject") 
            		 || tb.getColumn(e.getColIndex()).getFieldName().equals("PaymentClassify"))  {
                 if(tb.getColumn(e.getColIndex()).getFieldName().equals("RepairItem")) {
                     String rtNumberAfter = getRepairItemNumber(kdtRWORepairItemEntry.getCell(e.getRowIndex(), "RepairItem").getValue());
                     if(null != repairItemNumberBefore && (null == rtNumberAfter || null != rtNumberAfter && !RsUtils.isStringEquals(repairItemNumberBefore, rtNumberAfter)))
                         setIsHadVipDisCountRate(kdtRWORepairItemEntry, e.getRowIndex(), false);
                     kdtRWORepairItemEntry.getCell(e.getRowIndex(), "PaymentClassify").setValue(paymentClassifyInfo);
                     if(paymentClassifyInfo != null)
                         kdtRWORepairItemEntry.getCell(e.getRowIndex(), "SettleObject").setValue(paymentClassifyInfo.getSettObj());
                     kdtRWOItemEntryPamentChanged(e.getRowIndex(), e.getColIndex());
                  }
                 if(e.getValue() != e.getOldValue()) {
                     if((kdtRWORepairItemEntry.getCell(e.getRowIndex(), "RepairPkg").getValue() == null || isBasePkg(kdtRWORepairItemEntry, e.getRowIndex())) && kdtRWORepairItemEntry.getCell(e.getRowIndex(), "ServiceActivity").getValue() == null)
                         getDisRateEntry(e.getRowIndex());
                     if((null == kdtRWORepairItemEntry.getCell(e.getRowIndex(), "SettleObject").getValue() 
                    		 || (SettlementObjectEnum)kdtRWORepairItemEntry.getCell(e.getRowIndex(), "SettleObject").getValue() != SettlementObjectEnum.CLUB)
                    		 && (kdtRWORepairItemEntry.getCell(e.getRowIndex(), "RepairPkg").getValue() == null 
                    		|| isBasePkg(kdtRWORepairItemEntry, e.getRowIndex())) 
                    		&& kdtRWORepairItemEntry.getCell(e.getRowIndex(), "ServiceActivity").getValue() == null) {
                         BigDecimal workTimeAmount = UIRuleUtil.getBigDecimal(kdtRWORepairItemEntry.getCell(e.getRowIndex(), RepairWOEditUI.Cell_WorkTimeAmount).getValue());
                         BigDecimal discountRate = UIRuleUtil.getBigDecimal(kdtRWORepairItemEntry.getCell(e.getRowIndex(), RepairWOEditUI.Cell_DiscountRate).getValue());
                         BigDecimal discountAmount = workTimeAmount.multiply(discountRate).divide(BIGDEC100, 4, 6);
                         kdtRWORepairItemEntry.getCell(e.getRowIndex(), RepairWOEditUI.Cell_DiscountAmount).setValue(discountAmount);
                         BigDecimal amount = UIRuleUtil.getBigDecimal(workTimeAmount);
                         amount = amount.multiply(BIGDEC1.subtract(discountRate));
                         kdtRWORepairItemEntry.getCell(e.getRowIndex(), RepairWOEditUI.Cell_ARAmount).setValue(amount);
                         kdtRWORepairItemEntry.getCell(e.getRowIndex(), "ActualAmount").setValue(amount);
                         if((kdtRWORepairItemEntry.getCell(e.getRowIndex(), "RepairPkg").getValue() == null ||
                        		 isBasePkg(kdtRWORepairItemEntry, e.getRowIndex())) && kdtRWORepairItemEntry.getCell(e.getRowIndex(), "ServiceActivity").getValue() == null) {
                             kdtRWORepairItemEntry.getCell(e.getRowIndex(), "WorkTimePrice").setValue(StandardPriceWarraPrice((SettlementObjectEnum)kdtRWORepairItemEntry.getCell(e.getRowIndex(), "SettleObject").getValue()));
                             kdtRWORepairItemEntry.getCell(e.getRowIndex(), "WorkTimeStdPrice").setValue(workTimeStdPrice);
                             kdtWorkTimeEntrycount(e.getRowIndex());
                          }
                      }
                 }
                 if(kdtRWORepairItemEntry.getCell(e.getRowIndex(), "RepairPkg").getValue() == null &&
                		 kdtRWORepairItemEntry.getCell(e.getRowIndex(), "ServiceActivity").getValue() == null)
                     if(tb.getColumn(e.getColIndex()).getFieldName().equals("SettleObject"))  {
                         if(kdtRWORepairItemEntry.getCell(e.getRowIndex(), "SettleObject").getValue() != null)  {
                             kdtRWORepairItemEntry.getCell(e.getRowIndex(), "WorkTimePrice").setValue(StandardPriceWarraPrice((SettlementObjectEnum)kdtRWORepairItemEntry.getCell(e.getRowIndex(), "SettleObject").getValue()));
                             kdtRWORepairItemEntry.getCell(e.getRowIndex(), "WorkTimeStdPrice").setValue(workTimeStdPrice);
                             kdtWorkTimeEntrycount(e.getRowIndex());
                         }
                         Object oldSO = e.getOldValue();
                         Object newSO = e.getValue();
                         if(!RsUtils.isEqualSettlementObjectEnum(oldSO, newSO))
                             setIsHadVipDisCountRate(kdtRWORepairItemEntry, e.getRowIndex(), false);
                     } else if(tb.getColumn(e.getColIndex()).getFieldName().equals("PaymentClassify")) {
                         Object oldPC = e.getOldValue();
                         Object newPC = e.getValue();
                         if(null == oldPC && null != newPC || null != oldPC && null == newPC || null != oldPC && null != newPC
                        		 && !((PaymentClassifyInfo)oldPC).getId().toString().equals(((PaymentClassifyInfo)newPC).getId().toString()))
                             setIsHadVipDisCountRate(kdtRWORepairItemEntry, e.getRowIndex(), false);
                      }
                 }
         } catch(Exception e2) {
	             e2.printStackTrace();
	             handUIException(e2);
         }
         String s = RsUtils.getParamItem_Value("RSM001");
         if(tb.getColumn(e.getColIndex()).getFieldName().equals(RepairWOEditUI.Cell_DiscountRate)) {
             BigDecimal old_DiscountRate = UIRuleUtil.getBigDecimal(kdtRWORepairItemEntry.getCell(e.getRowIndex(), "OldDiscountRate").getValue());
             BigDecimal DiscountRate = UIRuleUtil.getBigDecimal(e.getValue());
             if(paramValue_RSM003.equals("1") && old_DiscountRate.compareTo(DiscountRate) == -1 && !paramValue_RSM012) {
                 MsgBox.showInfo((new StringBuilder()).append(" 折扣率只能<= ").append(old_DiscountRate).toString());
                 DiscountRate = old_DiscountRate;
                 tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_DiscountRate).setValue(old_DiscountRate);
             }
             if(DiscountRate.compareTo(BIGDEC100) > 0 && !paramValue_RSM012) {
                 MsgBox.showInfo("折扣率 不能超过100 ");
                 tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_DiscountRate).setValue(old_DiscountRate);
                 DiscountRate = old_DiscountRate;
             }
             boolean isChange = isDisCountRateChanged(e.getOldValue(), e.getValue());
             setIsHadVipDisCountRate(kdtRWORepairItemEntry, e.getRowIndex(), isChange);
             BigDecimal WorkTimeAmount = UIRuleUtil.getBigDecimal(tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_WorkTimeAmount).getValue());
             tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_DiscountAmount).setValue(WorkTimeAmount.multiply(DiscountRate).divide(BIGDEC100, 4, 6));
             tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_ARAmount).setValue(WorkTimeAmount.subtract(UIRuleUtil.getBigDecimal(tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_DiscountAmount).getValue())));
             tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_ActualAmount).setValue(UIRuleUtil.getBigDecimal(tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_ARAmount).getValue()));
         } else  if(tb.getColumn(e.getColIndex()).getFieldName().equals(RepairWOEditUI.Cell_StdWorkTime))  {
             if(null == e.getValue() || (new BigDecimal(e.getValue().toString())).compareTo(BIGDEC0) == -1)  {
                // MsgBox.showInfo(" 标准工时必须 >=0 ");
                // tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_StdWorkTime).setValue(e.getOldValue());
              }
             dealAfterWorkTimeChanged(e.getRowIndex(), RepairWOEditUI.Cell_StdWorkTime, e);
         } else
         if(tb.getColumn(e.getColIndex()).getFieldName().equals(RepairWOEditUI.Cell_AssignWorkTime))
             dealAfterWorkTimeChanged(e.getRowIndex(), RepairWOEditUI.Cell_AssignWorkTime, e);
         else
         if(tb.getColumn(e.getColIndex()).getFieldName().equals(RepairWOEditUI.Cell_WorkGroup)) {
             if(null == e.getValue())  {
                 tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_Person).setValue(null);
                 tb.getCell(e.getRowIndex(), "WagePrice").setValue(BIGDEC0);
                 tb.getCell(e.getRowIndex(), "WorkTimeCost").setValue(BIGDEC0);
              }
         } else if(tb.getColumn(e.getColIndex()).getFieldName().equals(RepairWOEditUI.Cell_Person))  {
             if(null == e.getValue() && (null == s || s.equalsIgnoreCase("0")))  {
                 tb.getCell(e.getRowIndex(), "WagePrice").setValue(BIGDEC0);
                 tb.getCell(e.getRowIndex(), "WorkTimeCost").setValue(BIGDEC0);
              }
         } else if(RepairWOEditUI.Cell_DiscountAmount.equals(tb.getColumn(e.getColIndex()).getFieldName())) {
             IRow row = tb.getRow(e.getRowIndex());
             BigDecimal DiscountRate = UIRuleUtil.getBigDecimal(row.getCell(RepairWOEditUI.Cell_DiscountRate).getValue());
             BigDecimal old_DiscountRate = UIRuleUtil.getBigDecimal(row.getCell("OldDiscountRate").getValue());
             BigDecimal DiscountAmount = UIRuleUtil.getBigDecimal(e.getValue());
             BigDecimal WorkTimeAmount = UIRuleUtil.getBigDecimal(tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_WorkTimeAmount).getValue());
             if(WorkTimeAmount.compareTo(BIGDEC0) != 0)  {
                 DiscountRate = DiscountAmount.multiply(BIGDEC100).divide(WorkTimeAmount, 4, 6);
             } else  {
                 row.getCell(RepairWOEditUI.Cell_DiscountAmount).setValue(UIRuleUtil.getBigDecimal(e.getOldValue()));
                 DiscountAmount = UIRuleUtil.getBigDecimal(e.getOldValue());
             }
             if(null != paramValue_RSM003 && paramValue_RSM003.equals("1") && old_DiscountRate.compareTo(DiscountRate) == -1 && !paramValue_RSM012) {
                 row.getCell(RepairWOEditUI.Cell_DiscountRate).setValue(old_DiscountRate);
                 DiscountRate = old_DiscountRate;
                 row.getCell(RepairWOEditUI.Cell_DiscountAmount).setValue(UIRuleUtil.getBigDecimal(WorkTimeAmount.multiply(DiscountRate).divide(BIGDEC100, 4, 6)));
                 DiscountAmount = UIRuleUtil.getBigDecimal(row.getCell(RepairWOEditUI.Cell_DiscountAmount).getValue());
                 MsgBox.showInfo((new StringBuilder()).append(" 折扣率只能<= ").append(old_DiscountRate).toString());
             }
             if(DiscountRate.compareTo(BIGDEC100) > 0 && !paramValue_RSM012)  {
                 row.getCell(RepairWOEditUI.Cell_DiscountRate).setValue(old_DiscountRate);
                 DiscountRate = old_DiscountRate;
                 row.getCell(RepairWOEditUI.Cell_DiscountAmount).setValue(UIRuleUtil.getBigDecimal(WorkTimeAmount.multiply(DiscountRate).divide(BIGDEC100, 4, 6)));
                 DiscountAmount = UIRuleUtil.getBigDecimal(row.getCell(RepairWOEditUI.Cell_DiscountAmount).getValue());
                 MsgBox.showInfo("优惠金额不能大于工时金额 ");
             }
             row.getCell(RepairWOEditUI.Cell_DiscountRate).setValue(DiscountRate);
             row.getCell(RepairWOEditUI.Cell_DiscountAmount).setValue(DiscountAmount);
             row.getCell(RepairWOEditUI.Cell_ARAmount).setValue(WorkTimeAmount.subtract(DiscountAmount));
             row.getCell(RepairWOEditUI.Cell_ActualAmount).setValue(UIRuleUtil.getBigDecimal(row.getCell(RepairWOEditUI.Cell_ARAmount).getValue()));
        } else if("RepairClassify".equals(tb.getColumn(e.getColIndex()).getFieldName()))  {
    	  		if(isChangeDiscountRateForRepairClassify(tb, e.getRowIndex()))  {
	                 Object oldValue = e.getOldValue();
	                 Object newValue = e.getValue();
	                 if(oldValue == null && null != newValue || null == newValue
	                		 && null != oldValue || oldValue != null && null != newValue && !oldValue.equals(newValue))  {
	                     isModify = true;
	                     IRow row = tb.getRow(e.getRowIndex());
	                     row.getCell(RepairWOEditUI.Cell_DiscountRate).setValue(BIGDEC0);
	                     kdtWorkTimeEntrycount(e.getRowIndex());
	                     setIsHadVipDisCountRate(tb, e.getRowIndex(), false);
	                  }
	              }
	   } else if(RepairWOEditUI.Cell_ARAmount.equals(tb.getColumn(e.getColIndex()).getFieldName()) && paramValue_RSM012) {
		             BigDecimal araAmount = UIRuleUtil.getBigDecimal(e.getValue());
		             BigDecimal workTimeAmount = UIRuleUtil.getBigDecimal(tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_WorkTimeAmount).getValue());
		             tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_DiscountAmount).setValue(workTimeAmount.subtract(araAmount));
		             if(workTimeAmount.compareTo(BIGDEC0) != 0)
		                 tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_DiscountRate).setValue(UIRuleUtil.getBigDecimal(tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_DiscountAmount).getValue()).multiply(BIGDEC100).divide(workTimeAmount, 4, 6));
		             tb.getCell(e.getRowIndex(), RepairWOEditUI.Cell_ActualAmount).setValue(araAmount);
		             if(UIRuleUtil.getBigDecimal(e.getOldValue()).compareTo(araAmount) != 0)
		                 setIsHadVipDisCountRate(kdtRWORepairItemEntry, e.getRowIndex(), true);
		}
	     kdtEntryChanged(e.getRowIndex(), e.getColIndex());
	     fillMainTableTotalPrice(kdtRWORepairItemEntry);
	     if(!tb.getColumn(e.getColIndex()).getFieldName().equals(RepairWOEditUI.Cell_ActualWorkTime) && !tb.getColumn(e.getColIndex()).getFieldName().equals("ItemRemark") && !tb.getColumn(e.getColIndex()).getFieldName().equals("WorkStation"))
	         initRItemListener(e);
	     makeSPItemF7();
	     initColour();  
	}
	
	/**
	 * 取消负数的限制
	 */
    protected void kdtRWOSparepartEntry_editStopped(KDTEditEvent e) {
		int curRow = e.getRowIndex();
		int colIndex = e.getColIndex();
		if (curRow < 0)
			return;
		String curCellKey = kdtRWOSparepartEntry.getColumnKey(colIndex);
		IRow row = kdtRWOSparepartEntry.getRow(curRow);
		if (null == row.getCell("Material").getValue()) {
			row.getCell(Cell_DiscountRate).setValue(BIGDEC0);
			row.getCell(Cell_AssistProperty).setValue(null);
			row.getCell(Cell_AssistProperty).getStyleAttributes().setBackground(Color.WHITE);
			row.getCell("RepairItem").setValue(null);
			row.getCell("model").setValue(null);
			row.getCell("MaterialGroup").setValue(null);
			setIsHadVipDisCountRate(kdtRWOSparepartEntry, curRow, false);
			return;
		}
		if (!(row.getCell("Material").getValue() instanceof MaterialInfo))
			return;
		MaterialInfo materialInfo = (MaterialInfo) row.getCell("Material").getValue();
		BigDecimal IssueQty = UIRuleUtil.getBigDecimal(row.getCell(Cell_IssueQty).getValue());
		BigDecimal newValue = null;
		IColumn Unit = kdtRWOSparepartEntry.getColumn(Cell_Unit);
		if (null != materialInfo)
			RsQueryF7Utils.setMaterialUnitQuery(Unit, null, materialInfo);
		MeasureUnitInfo unitInfo = row.getCell(Cell_Unit).getValue() != null ? (MeasureUnitInfo) row.getCell(Cell_Unit).getValue(): null;
		MeasureUnitInfo baseUnitInfo = row.getCell(Cell_BaseUnit).getValue() != null ? (MeasureUnitInfo) row.getCell(Cell_BaseUnit).getValue() : null;
		BigDecimal TaxRate = UIRuleUtil.getBigDecimal(row.getCell(Cell_TaxRate).getValue());
		BigDecimal DiscountRate = UIRuleUtil.getBigDecimal(row.getCell(Cell_DiscountRate).getValue());
		BigDecimal DiscountAmount = UIRuleUtil.getBigDecimal(row.getCell(Cell_DiscountAmount).getValue());
		BigDecimal TaxPrice = UIRuleUtil.getBigDecimal(row.getCell(Cell_TaxPrice).getValue());
		BigDecimal qty = UIRuleUtil.getBigDecimal(row.getCell(Cell_Qty).getValue());
		BigDecimal temp = BIGDEC1;
		if (Cell_Qty.equals(curCellKey)) {
			newValue = e.getValue() != null ? UIRuleUtil.getBigDecimal(e.getValue()) : BIGDEC0;
			if (null != row.getCell(Cell_ID).getValue()) {
				String rowID = null;
				RepairWORWOSparepartEntryInfo info = null;
				rowID = row.getCell(Cell_ID).getValue().toString();
				try {
					if (iRepairWORWOSparepartEntry.exists(new ObjectUuidPK(rowID)))
						info = iRepairWORWOSparepartEntry.getRepairWORWOSparepartEntryInfo(new ObjectUuidPK(rowID));
					if (null != info)
						IssueQty = info.getIssueQty();
				} catch (EASBizException e1) {
					handUIException(e1);
				} catch (BOSException e1) {
					handUIException(e1);
				}
				row.getCell(Cell_IssueQty).setValue(IssueQty);
			}
			if (newValue.subtract(IssueQty).compareTo(BIGDEC0) < 0) {
				//row.getCell(Cell_Qty).setValue(e.getOldValue());
				//MsgBox.showInfo("不满足: 数量-己出库数量>=0 ");
				//abort();
			}
			try {
				getTaxPrice(curRow);
			} catch (EASBizException e2) {
				e2.printStackTrace();
			} catch (BOSException e2) {
				e2.printStackTrace();
			}
			TaxPrice = UIRuleUtil.getBigDecimal(row.getCell(Cell_TaxPrice).getValue());
			row.getCell(Cell_NoTaxPrice).setValue(TaxPrice.multiply(BIGDEC100).divide(BIGDEC100.add(TaxRate),4, 6));
			row.getCell(Cell_NoIssueQty).setValue(newValue.subtract(IssueQty));
			try {
				row.getCell(Cell_BaseQty).setValue(CommonUtils.getBaseUnitQty(materialInfo, baseUnitInfo,unitInfo, newValue));
			} catch (EASBizException e1) {
				handUIException(e1);
			} catch (BOSException e1) {
				handUIException(e1);
			}
			temp = newValue.multiply(TaxPrice);
			row.getCell(Cell_TaxAmount).setValue(temp);
			row.getCell(Cell_DiscountAmount).setValue(temp.multiply(DiscountRate).divide(BIGDEC100, 4, 6));
			row.getCell(Cell_ARAmount).setValue(temp.subtract(UIRuleUtil.getBigDecimal(row.getCell(Cell_DiscountAmount).getValue())));
			row.getCell(Cell_ActualAmount).setValue(UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue()));
			row.getCell(Cell_Tax).setValue(UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue()).multiply(TaxRate).divide(BIGDEC100.add(TaxRate), 4, 6));
			temp = UIRuleUtil.getBigDecimal(row.getCell(Cell_NoTaxPrice).getValue());
			row.getCell(Cell_NoTaxAmount).setValue(newValue.multiply(temp));
		} else if (Cell_Unit.equals(curCellKey)) {
			unitInfo = e.getValue() != null ? (MeasureUnitInfo) e.getValue() : null;
			if (null != unitInfo) 
				try {
					row.getCell(Cell_BaseQty).setValue(CommonUtils.getBaseUnitQty(materialInfo,baseUnitInfo, unitInfo, qty));
				} catch (EASBizException e1) {
					handUIException(e1);
				} catch (BOSException e1) {
					handUIException(e1);
				}
		} else if (Cell_TaxRate.equals(curCellKey)) {
			TaxRate = UIRuleUtil.getBigDecimal(e.getValue());
			row.getCell(Cell_NoTaxPrice).setValue(TaxPrice.multiply(BIGDEC100).divide(BIGDEC100.add(TaxRate),4, 6));
			temp = UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue());
			row.getCell(Cell_Tax).setValue(temp.multiply(TaxRate).divide(BIGDEC100.add(TaxRate), 4, 6));
			temp = UIRuleUtil.getBigDecimal(row.getCell(Cell_NoTaxPrice).getValue());
			row.getCell(Cell_NoTaxAmount).setValue(qty.multiply(temp));
		} else if (Cell_DiscountRate.equals(curCellKey)) {
			BigDecimal old_DiscountRate = UIRuleUtil.getBigDecimal(kdtRWOSparepartEntry.getCell(row.getRowIndex(), "OldDiscountRate").getValue());
			DiscountRate = UIRuleUtil.getBigDecimal(e.getValue());
			if (null != paramValue_RSM003 && paramValue_RSM003.equals("1")
					&& old_DiscountRate.compareTo(DiscountRate) == -1) {
				row.getCell(Cell_DiscountRate).setValue(old_DiscountRate);
				DiscountRate = old_DiscountRate;
				MsgBox.showInfo((new StringBuilder()).append(" 折扣率只能<= ").append(old_DiscountRate).toString());
			}
			if (DiscountRate.compareTo(BIGDEC100) > 0) {
				MsgBox.showInfo("折扣率 不能超过100 ");
				row.getCell(Cell_DiscountRate).setValue(old_DiscountRate);
				DiscountRate = old_DiscountRate;
			}
			boolean isChange = isDisCountRateChanged(e.getOldValue(), e.getValue());
			setIsHadVipDisCountRate(kdtRWOSparepartEntry, curRow, isChange);
			temp = UIRuleUtil.getBigDecimal(row.getCell(Cell_TaxAmount).getValue());
			row.getCell(Cell_DiscountAmount).setValue(temp.multiply(DiscountRate).divide(BIGDEC100, 4, 6));
			temp = UIRuleUtil.getBigDecimal(row.getCell(Cell_DiscountAmount).getValue());
			row.getCell(Cell_ARAmount).setValue(UIRuleUtil.getBigDecimal(row.getCell(Cell_TaxAmount).getValue()).subtract(temp));
			row.getCell(Cell_ActualAmount).setValue(UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue()));
			temp = UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue());
			row.getCell(Cell_Tax).setValue(temp.multiply(TaxRate).divide(BIGDEC100.add(TaxRate), 4, 6));
		} else if (Cell_DiscountAmount.equals(curCellKey)) {
			BigDecimal old_DiscountRate = UIRuleUtil.getBigDecimal(kdtRWOSparepartEntry.getCell(row.getRowIndex(), "OldDiscountRate").getValue());
			DiscountAmount = UIRuleUtil.getBigDecimal(e.getValue());
			temp = UIRuleUtil.getBigDecimal(row.getCell(Cell_TaxAmount).getValue());
			if (temp.compareTo(BIGDEC0) != 0) {
				DiscountRate = DiscountAmount.multiply(BIGDEC100).divide(temp,4, 6);
			} else {
				row.getCell(Cell_DiscountAmount).setValue(UIRuleUtil.getBigDecimal(e.getOldValue()));
				DiscountAmount = UIRuleUtil.getBigDecimal(e.getOldValue());
			}
			if (null != paramValue_RSM003 && paramValue_RSM003.equals("1")
					&& old_DiscountRate.compareTo(DiscountRate) == -1) {
				row.getCell(Cell_DiscountRate).setValue(old_DiscountRate);
				DiscountRate = old_DiscountRate;
				row.getCell(Cell_DiscountAmount).setValue(UIRuleUtil.getBigDecimal(e.getOldValue()));
				DiscountAmount = UIRuleUtil.getBigDecimal(e.getOldValue());
				MsgBox.showInfo((new StringBuilder()).append(" 折扣率只能<= ").append(old_DiscountRate).toString());
			}
			if (DiscountRate.compareTo(BIGDEC100) > 0) {
				row.getCell(Cell_DiscountRate).setValue(old_DiscountRate);
				DiscountRate = old_DiscountRate;
				row.getCell(Cell_DiscountAmount).setValue(UIRuleUtil.getBigDecimal(e.getOldValue()));
				DiscountAmount = UIRuleUtil.getBigDecimal(e.getOldValue());
				MsgBox.showInfo("优惠金额不能大于含税金额");
			}
			row.getCell(Cell_DiscountRate).setValue(DiscountRate);
			row.getCell(Cell_ARAmount).setValue(temp.subtract(DiscountAmount));
			row.getCell(Cell_ActualAmount).setValue(UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue()));
			temp = UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue());
			row.getCell(Cell_Tax).setValue(temp.multiply(TaxRate).divide(BIGDEC100.add(TaxRate), 4, 6));
		} else if (Cell_TaxPrice.equals(curCellKey)) {
			BigDecimal oldTaxPrice = UIRuleUtil.getBigDecimal(e.getOldValue());
			BigDecimal newTaxPrice = UIRuleUtil.getBigDecimal(e.getValue());
			if (newTaxPrice.compareTo(oldTaxPrice) != 0) {
				DiscountRate = BIGDEC0;
				setIsHadVipDisCountRate(kdtRWOSparepartEntry, curRow, false);
			}
			row.getCell(Cell_DiscountRate).setValue(DiscountRate);
			BigDecimal noTaxPrice = newTaxPrice.multiply(BIGDEC100).divide(BIGDEC100.add(TaxRate), 4, 6);
			row.getCell(Cell_NoTaxPrice).setValue(noTaxPrice);
			row.getCell(Cell_TaxAmount).setValue(newTaxPrice.multiply(qty));
			row.getCell(Cell_NoTaxAmount).setValue(noTaxPrice.multiply(qty));
			row.getCell(Cell_DiscountAmount).setValue(newTaxPrice.multiply(qty).multiply(DiscountRate).divide(BIGDEC100, 4, 6));
			row.getCell(Cell_ARAmount).setValue(newTaxPrice.multiply(qty).subtract(UIRuleUtil.getBigDecimal(row.getCell(Cell_DiscountAmount).getValue())));
			row.getCell(Cell_ActualAmount).setValue(UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue()));
			BigDecimal tax = UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue()).multiply(TaxRate).divide(BIGDEC100.add(TaxRate), 4, 6);
			row.getCell(Cell_Tax).setValue(tax);
			isModify = true;
		} else if ("RepairClassify".equals(curCellKey)) {
			if (isChangeDiscountRateForRepairClassify(kdtRWOSparepartEntry,curRow)) {
				Object oldRValue = e.getOldValue();
				Object newRValue = e.getValue();
				if (null != newRValue&& null == oldRValue
						|| null == newRValue && null != oldRValue
						|| oldRValue != null && null != newRValue
						&& !((RepairClassifyInfo) oldRValue).getId().toString().equals(((RepairClassifyInfo) newRValue).getId().toString())) {
					isModify = true;
					row.getCell(Cell_DiscountRate).setValue(BIGDEC0);
					kdtSPEntrycount(curRow);
					setIsHadVipDisCountRate(kdtRWOSparepartEntry, curRow, false);
				}
			}
		} else if (Cell_ARAmount.equals(curCellKey)) {
			BigDecimal old_DiscountRate = UIRuleUtil.getBigDecimal(kdtRWOSparepartEntry.getCell(row.getRowIndex(), "OldDiscountRate").getValue());
			BigDecimal araAmount = UIRuleUtil.getBigDecimal(e.getValue());
			temp = UIRuleUtil.getBigDecimal(row.getCell(Cell_TaxAmount).getValue());
			BigDecimal disCountAmountbyAr = temp.subtract(araAmount);
			if (temp.compareTo(BIGDEC0) != 0) {
				DiscountRate = disCountAmountbyAr.multiply(BIGDEC100).divide(temp, 4, 6);
			} else {
				row.getCell(Cell_ARAmount).setValue(UIRuleUtil.getBigDecimal(e.getOldValue()));
				araAmount = UIRuleUtil.getBigDecimal(e.getOldValue());
			}
			if (null != paramValue_RSM003 && paramValue_RSM003.equals("1")
					&& old_DiscountRate.compareTo(DiscountRate) == -1) {
				DiscountRate = old_DiscountRate;
				araAmount = UIRuleUtil.getBigDecimal(e.getOldValue());
				MsgBox.showInfo((new StringBuilder()).append(" 折扣率只能<= ").append(old_DiscountRate).toString());
			}
			if (DiscountRate.compareTo(BIGDEC100) > 0) {
				DiscountRate = old_DiscountRate;
				araAmount = UIRuleUtil.getBigDecimal(e.getOldValue());
				MsgBox.showInfo("优惠金额不能大于含税金额");
			}
			row.getCell(Cell_DiscountRate).setValue(DiscountRate);
			row.getCell(Cell_DiscountAmount).setValue(temp.subtract(araAmount));
			row.getCell(Cell_ARAmount).setValue(araAmount);
			row.getCell(Cell_ActualAmount).setValue(UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue()));
			temp = UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue());
			row.getCell(Cell_Tax).setValue(temp.multiply(TaxRate).divide(BIGDEC100.add(TaxRate), 4, 6));
		}
	}
	
	private void appendItemSpFoot() {
		BigDecimal totalAmount = BIGDEC0;
		BigDecimal totalTax = BIGDEC0;
		BigDecimal totalTaxAmount = BIGDEC0;
		KDTFootManager footManager = kdtRWOItemSpEntry.getFootManager();
		IRow footRow = null;
		if (footManager == null) {
			footManager = new KDTFootManager(kdtRWOItemSpEntry);
			kdtRWOItemSpEntry.setFootManager(footManager);
			footManager.addFootView();
		} 

		footRow = footManager.getFootRow(0);
		//footManager.getMergeManager().setMergeMode(KDTMergeManager.FREE_COLUMN_MERGE);
		if (footRow == null) footRow =footManager.addFootRow(0);
		for (int i = 0; i < kdtRWOItemSpEntry.getRowCount(); i++) {
			IRow row = kdtRWOItemSpEntry.getRow(i);
			BigDecimal amount = (BigDecimal) row.getCell("amount").getValue();
			if (amount == null) amount = BIGDEC0;
			BigDecimal taxRate = (BigDecimal)row.getCell("taxRate").getValue();
			if (taxRate == null) taxRate = BIGDEC0;
			
			totalAmount = totalAmount.add(amount);
			totalTax = totalTax.add(amount.multiply(taxRate.divide(BIGDEC100,10,BigDecimal.ROUND_HALF_UP)));
			
		}
		totalTaxAmount = totalAmount.add(totalTax);
		
		KDTMergeManager footMerge = footManager.getMergeManager();
		footRow.setMergeable(true);
		
		for (int i = 0; i < kdtRWOItemSpEntry.getColumnCount(); i++) {
			footRow.getCell(i).setValue("");
		}
		
		int tIndex = kdtRWOItemSpEntry.getColumnIndex("t");
		int itemspNumIndex = kdtRWOItemSpEntry.getColumnIndex("itemspNum");
		footMerge.mergeBlock(0, tIndex, 0, itemspNumIndex);
		
		int itemspNameIndex = kdtRWOItemSpEntry.getColumnIndex("itemspName");
		
		int repairPkgIndex = kdtRWOItemSpEntry.getColumnIndex("repairPkg");
		int isCTIndex = kdtRWOItemSpEntry.getColumnIndex("isCT");
		footMerge.mergeBlock(0, repairPkgIndex, 0, isCTIndex);
		
		int qtyIndex =  kdtRWOItemSpEntry.getColumnIndex("qty");
		int issueQtyIndex =  kdtRWOItemSpEntry.getColumnIndex("issueQty");
		footMerge.mergeBlock(0, qtyIndex, 0, issueQtyIndex);
		
		int priceIndex = kdtRWOItemSpEntry.getColumnIndex("price");
		int taxPriceIndex = kdtRWOItemSpEntry.getColumnIndex("taxPrice");
		footMerge.mergeBlock(0, priceIndex, 0, taxPriceIndex);
		
		int discountRateIndex = kdtRWOItemSpEntry.getColumnIndex("discountRate");
		int lastIndex = kdtRWOItemSpEntry.getColumnCount() -1;
		footMerge.mergeBlock(0, discountRateIndex, 0, lastIndex);
		
		
		footRow.getCell(tIndex).setValue("未税合计");
		footRow.getCell(tIndex).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);

		footRow.getCell(itemspNameIndex).setValue(totalAmount);
		footRow.getCell(itemspNameIndex).getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		footRow.getCell(itemspNameIndex).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.LEFT);
		
		
		footRow.getCell(repairPkgIndex).setValue("增值税");
		footRow.getCell(repairPkgIndex).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
		footRow.getCell(qtyIndex).setValue(totalTax);
		footRow.getCell(qtyIndex).getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		footRow.getCell(qtyIndex).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.LEFT);
		

		footRow.getCell(priceIndex).setValue("含税合计");
		footRow.getCell(priceIndex).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
		
		footRow.getCell(discountRateIndex).setValue(totalTaxAmount);
		footRow.getCell(discountRateIndex).getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		footRow.getCell(discountRateIndex).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.LEFT);
		
	}
	private void valueChangedForItemSpEntry(Object oldValue,KDTEditEvent e) throws Exception {
		int rowIndex = e.getRowIndex();
		int colIndex = e.getColIndex();
		
		int tTypeIndex = kdtRWOItemSpEntry.getColumnIndex("t");
		int qtyIndex = kdtRWOItemSpEntry.getColumnIndex("qty");
		int priceIndex = kdtRWOItemSpEntry.getColumnIndex("price");
		int taxPriceIndex = kdtRWOItemSpEntry.getColumnIndex("taxPrice");
		int discountRateIndex = kdtRWOItemSpEntry.getColumnIndex("discountRate");
		int wIndex = kdtRWOItemSpEntry.getColumnIndex("w");
		int settlementObjectIndex = kdtRWOItemSpEntry.getColumnIndex("settlementObject");
		int amountIndex = kdtRWOItemSpEntry.getColumnIndex("amount");
		int taxAmountIndex = kdtRWOItemSpEntry.getColumnIndex("taxAmount");
		int taxRateIndex = kdtRWOItemSpEntry.getColumnIndex("taxRate");
		int isCTIndex = kdtRWOItemSpEntry.getColumnIndex("isCT");
		
		TEnum tType = (TEnum) kdtRWOItemSpEntry.getRow(rowIndex).getCell(tTypeIndex).getValue();
		BigDecimal price = PublicUtils.getBigDecimal(kdtRWOItemSpEntry.getRow(rowIndex).getCell(priceIndex).getValue());
		BigDecimal taxPrice =  PublicUtils.getBigDecimal(kdtRWOItemSpEntry.getRow(rowIndex).getCell(taxPriceIndex).getValue());
		BigDecimal qty =  PublicUtils.getBigDecimal(kdtRWOItemSpEntry.getRow(rowIndex).getCell(qtyIndex).getValue());
		BigDecimal taxRate =  PublicUtils.getBigDecimal(kdtRWOItemSpEntry.getRow(rowIndex).getCell(taxRateIndex).getValue());
		boolean isCT = (Boolean) kdtRWOItemSpEntry.getRow(rowIndex).getCell(isCTIndex).getValue();
		
		if (colIndex == wIndex) {
			WInfo wInfo = (WInfo) e.getValue();
			if (wInfo != null)
				 kdtRWOItemSpEntry.getRow(rowIndex).getCell(settlementObjectIndex).setValue(wInfo.getSettleObject());
		} else if (colIndex == qtyIndex || colIndex == priceIndex || colIndex == discountRateIndex) {		
			if (colIndex == discountRateIndex) {
				BigDecimal discountRate = PublicUtils.getBigDecimal(e.getValue());
				if (PublicUtils.equals(TEnum.L, tType) && discountRate.compareTo(maxRepairDiscountRate) > 0) {
					MsgBoxEx.showInfo(String.format("折扣不能高于折扣权限[%s]！",maxRepairDiscountRate.setScale(2)));
					kdtRWOItemSpEntry.getRow(rowIndex).getCell(discountRateIndex).setValue(oldValue);
					return;
				} else if (PublicUtils.equals(TEnum.P, tType) && discountRate.compareTo(maxRetailDiscountRate) > 0) {
					MsgBoxEx.showInfo(String.format("折扣不能高于折扣权限[%s]！",maxRetailDiscountRate.setScale(2)));
					kdtRWOItemSpEntry.getRow(rowIndex).getCell(discountRateIndex).setValue(oldValue);
					return;
				}				
			}
			if (colIndex == qtyIndex && isCT && qty.compareTo(BIGDEC0) >= 0) {
				MsgBoxEx.showInfo("拆退数量不能大于0");
				kdtRWOItemSpEntry.getRow(rowIndex).getCell(qtyIndex).setValue(oldValue);
				return;
			}
			calItemSpEntryAmount(kdtRWOItemSpEntry.getRow(rowIndex));
		} else if (colIndex == amountIndex || colIndex == taxAmountIndex) {
			BigDecimal discountRate = null;
			BigDecimal amount = null;
			BigDecimal taxAmount = null;
			
			if (colIndex == amountIndex) {
				amount = PublicUtils.getBigDecimal(kdtRWOItemSpEntry.getRow(rowIndex).getCell(amountIndex).getValue());
				if (PublicUtils.equals(price, BIGDEC0) || PublicUtils.equals(qty, BIGDEC0) )
					discountRate = BIGDEC0;
				else discountRate = BIGDEC100.subtract(amount.divide(price.multiply(qty),10,BigDecimal.ROUND_HALF_UP).multiply(BIGDEC100));
				taxAmount = amount.multiply(BIGDEC1.add(taxRate.divide(BIGDEC100,10,BigDecimal.ROUND_HALF_UP)));
				
			} else if (colIndex == taxAmountIndex) {
				taxAmount =  PublicUtils.getBigDecimal(kdtRWOItemSpEntry.getRow(rowIndex).getCell(taxAmountIndex).getValue());
				if (PublicUtils.equals(taxPrice, BIGDEC0) || PublicUtils.equals(qty, BIGDEC0)) 
					discountRate = BIGDEC0;
				else discountRate = BIGDEC100.subtract(taxAmount.divide(taxPrice.multiply(qty),10,BigDecimal.ROUND_HALF_UP).multiply(BIGDEC100));
				amount = taxAmount.divide(BIGDEC1.add(taxRate.divide(BIGDEC100,10,BigDecimal.ROUND_HALF_UP)),10,BigDecimal.ROUND_HALF_UP);
			}
			
			if (PublicUtils.equals(TEnum.L, tType) && discountRate.compareTo(maxRepairDiscountRate) > 0) {
				MsgBoxEx.showInfo(String.format("折扣不能高于折扣权限[%s]！",maxRepairDiscountRate.setScale(2)));
				kdtRWOItemSpEntry.getRow(rowIndex).getCell(colIndex).setValue(oldValue);
				return;
			} else if (PublicUtils.equals(TEnum.P, tType) && discountRate.compareTo(maxRetailDiscountRate) > 0) {
				MsgBoxEx.showInfo(String.format("折扣不能高于折扣权限[%s]！",maxRetailDiscountRate.setScale(2)));
				kdtRWOItemSpEntry.getRow(rowIndex).getCell(colIndex).setValue(oldValue);
				return;
			}
			
			kdtRWOItemSpEntry.getRow(rowIndex).getCell(amountIndex).setValue(amount);
			kdtRWOItemSpEntry.getRow(rowIndex).getCell(taxAmountIndex).setValue(taxAmount);
			kdtRWOItemSpEntry.getRow(rowIndex).getCell(discountRateIndex).setValue(discountRate);
			calItemSpEntryAmount(kdtRWOItemSpEntry.getRow(rowIndex));
			
		} else if (colIndex == isCTIndex && isCT) {
			kdtRWOItemSpEntry.getRow(rowIndex).getCell("price").setValue(BIGDEC0);
			kdtRWOItemSpEntry.getRow(rowIndex).getCell("qty").setValue(new BigDecimal(-1));
			calItemSpEntryAmount(kdtRWOItemSpEntry.getRow(rowIndex));
			
		}

	}
	
	private BigDecimal getMaterialCostPrice(String stroageUnitId,MaterialInfo materialInfo) throws Exception {
		if (stroageUnitId == null || materialInfo == null) return BIGDEC0;
		

		String sql = String.format("select isnull(CFCostPrice,0) from  T_BD_MaterialInventory where FMaterialID='%s' and FOrgUnit='%s'",
				materialInfo.getString("id"),stroageUnitId);
		IRowSet rs = DBUtils.executeQuery(null, sql);
		
		if (rs != null && rs.next()) {
			BigDecimal costPrice = PublicUtils.getBigDecimal(rs.getBigDecimal(1));
			return costPrice;
		}
		return BIGDEC0;
	}
	
	private void resetItemSpEditorLocked(IRow row) {
		
		int unIssueIndex = kdtRWOItemSpEntry.getColumnIndex("unIssueQty");
		
		ICell cellT = row.getCell("t");
		ICell cellItem = row.getCell("repairItem");
		ICell cellSp = row.getCell("material");
	//	ICell cellItemSp = row.getCell("itemspNum");
		ICell cellIsCT = row.getCell("isCT");
		if ("L".equals(cellT.getValue().toString())) {  //项目
			cellItem.getStyleAttributes().setLocked(false);
			cellSp.getStyleAttributes().setLocked(true);
			cellSp.setValue(null);
		//	initItemSpRepairItemEntryF7(cellItemSp);
			cellIsCT.getStyleAttributes().setLocked(true);
			
		} else if ("P".equals(cellT.getValue().toString())) { //配件
			cellItem.getStyleAttributes().setLocked(true);
			cellItem.setValue(null);
			cellSp.getStyleAttributes().setLocked(false);
			//initItemSpMaterialF7(cellItemSp);
			cellIsCT.getStyleAttributes().setLocked(false);
			row.getCell("itemspName").getStyleAttributes().setLocked(!hasPermission_OprtRetailItemspName);
			row.getCell("price").getStyleAttributes().setLocked(!hasPermission_OprtRetailPrice);
		}
		
		
		if (!hasPermission_OprtRetailLine && PublicUtils.equals("P", cellT.getValue().toString())) {
			row.getStyleAttributes().setLocked(true);
			
		} else if (!hasPermission_OprtRepairLine && PublicUtils.equals("L", cellT.getValue().toString())) {
			row.getStyleAttributes().setLocked(true);
		}
		
		try {
			BigDecimal issueQty = PublicUtils.getBigDecimal(row.getCell("issueQty").getValue());
			boolean isAPSettle = (Boolean)row.getCell("isAPSettle").getValue();
			IEnum iType = (IEnum) row.getCell("i").getValue();
			BigDecimal amount = PublicUtils.getBigDecimal(row.getCell("amount").getValue());
			

			if (PublicUtils.equals(IEnum.X, iType) && amount.compareTo(BIGDEC0) != 0) {	
				row.getStyleAttributes().setLocked(true);
			}
			if (issueQty.compareTo(BIGDEC0) != 0) { //已有出库
				row.getStyleAttributes().setLocked(true);
			}  
			
			if (isAPSettle) { //应付结算
				row.getStyleAttributes().setLocked(true);
			} 
			if (!PublicUtils.equals(IEnum.X, iType))
				row.getCell("w").getStyleAttributes().setLocked(false);
			if (PublicUtils.equals(IEnum.X, iType) && amount.compareTo(BIGDEC0) == 0)
				row.getCell("w").getStyleAttributes().setLocked(false);
		
			

			row.getCell("repairPkg").getStyleAttributes().setLocked(false);
			
			BigDecimal unIssueQty = PublicUtils.getBigDecimal(row.getCell(unIssueIndex).getValue());
			if (unIssueQty.compareTo(BIGDEC0) == 0) {
				row.getCell(unIssueIndex).getStyleAttributes().setBackground(null);
			} else row.getCell(unIssueIndex).getStyleAttributes().setBackground(Color.RED);
			
		} catch (Exception e) {
			UIUtils.handUIException(e);
		}
		
		
	
	}
		
	@Override
	protected void prmtVehicle_dataChanged(DataChangeEvent e) throws Exception {
		super.prmtVehicle_dataChanged(e);
		boolean isChange = true;
		isChange = MakeControl.isF7ValueChanged(e);
		if(!isChange)
			return;
		txtMile.setValue(null);
		//---自动带出默认送修人及电话
		vehicleInfo = (VehicleInfo) prmtVehicle.getValue();
		txtRepairSender.setText("");
		txtTel.setText("");
		if (vehicleInfo != null) {
			
			IRowSet rs = DBUtils.executeQuery(null, String.format("SELECT isnull(CFName,'') CFName,isnull(CFTel,'') CFTel,isnull(CFAddr,'') CFAddr from CT_ATS_VehicleRepairSender where FParentID='%s'", vehicleInfo.getString("id")));
			if (rs != null && rs.next()) {
				String name = rs.getString("CFName");
				String tel = rs.getString("CFTel");
				String addr = rs.getString("CFAddr");
				if (!rs.next()) {
					txtRepairSender.setText(name);
					txtTel.setText(tel);
					
					String strCustomerInfo = name + " " + tel + CR + addr;
					txtcustomInfo.setText(strCustomerInfo);
				} else {
					btnShowRepairSender_actionPerformed(null);
				}
			} else {
				btnShowRepairSender_actionPerformed(null);
			}
			
		}
		// 带出维修历史备注
		getRepairWoRemarkHostory();
		
		
	}
	
	private void getRepairWoRemarkHostory() throws Exception {
		if (vehicleInfo == null) {
			txtRemarkList.setText("");
			return;
		}
		IVehicleRepairRemark repairRemark = VehicleRepairRemarkFactory.getRemoteInstance();
		String rwId =editData.getString("id");
		String rwSql = "";
		if (!PublicUtils.isEmpty(rwId)) {
			rwSql = String.format("and RepairWO.id<>'%s'", rwId);
		}
		String sql = String.format("where parent.id='%s' %s order by createTime desc",vehicleInfo.getString("id"),rwSql);
		VehicleRepairRemarkCollection repairRemarkCol =  repairRemark.getVehicleRepairRemarkCollection(sql);
		txtRemarkList.setText("");
		for (int i = 0; repairRemarkCol != null && i < repairRemarkCol.size(); i++) {
    		VehicleRepairRemarkInfo repairRemarkInfo = repairRemarkCol.get(i);
    		txtRemarkList.append(String.format("%d、",i+1));
    		txtRemarkList.append(repairRemarkInfo.getRemark());
    		txtRemarkList.append(CR);
    	}
		txtRemarkList.select(0, 0);
				
	}
	@Override
	protected void initOldData(IObjectValue dataObject) {
		try {
			getRepairWoRemarkHostory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.initOldData(dataObject);
	}

	private void resetBtnAction(KDWorkButton btn, Action action) {
		ActionListener[] l = btn.getActionListeners();
		for (int i = 0; l != null && i < l.length; i++) {
			btn.removeActionListener(l[i]);
		}
		btn.addActionListener(action);
	}
	@Override
	protected void cmbT_itemStateChanged(ItemEvent e) throws Exception {
		if (PublicUtils.equals(TEnum.L, cmbT.getSelectedItem())) { //维修
			labMaterial.setVisible(false);
			labRepairItem.setVisible(true);
			prmtRepairItem.setValue(null);
		} else if (PublicUtils.equals(TEnum.P, cmbT.getSelectedItem())) {//配件
			labMaterial.setVisible(true);
			labRepairItem.setVisible(false);
			prmtMaterial.setValue(null);
		}
	}
	
	@Override
	public void initUIContentLayout() {
		super.initUIContentLayout();
		
		//移动[项目/配件明细]到第1个位置
		kDTRWOPane.remove(kDPRwoItemSp);
		kDTRWOPane.insertTab(resHelper.getString("kDPRwoItemSp.constraints"), null, kDPRwoItemSp, resHelper.getString("kDPRwoItemSp.constraints"), 0);
		kDTRWOPane.setSelectedIndex(0);
		
	}
	@Override
	protected void clearItemData() {
		super.clearItemData();
		kdtRWOItemSpEntry.removeRows();
	}
	@Override
	protected KDTable getDetailTable() {
		if(kDTRWOPane.getSelectedIndex() == 0)
		   return kdtRWOItemSpEntry;
		else if(kDTRWOPane.getSelectedIndex() == 1)
		   return kdtRWORepairItemEntry;
		else if(kDTRWOPane.getSelectedIndex() == 2)
			   return kdtRWOSparepartEntry;
		else if(kDTRWOPane.getSelectedIndex() == 3)
			   return kdtRWORepairPkgEntry;
		else if(kDTRWOPane.getSelectedIndex() == 4)
			   return kdtRWOActivityEntry;
		else if(kDTRWOPane.getSelectedIndex() == 5)
			   return kdtRWOAttachmentItemEntry;
		else if(kDTRWOPane.getSelectedIndex() == 6)
			   return kdtRWOTotalAmountEntry;
		else if(kDTRWOPane.getSelectedIndex() == 7)
			return kdtRepairBreakEntry;
		else return kdtRWORepairItemEntry;
	}
	@Override
	protected IObjectValue createNewDetailData(KDTable table) {
		if (kdtRWOItemSpEntry.getName().equals(table.getName())) {
			isNotNull();
			RepairWORWOItemSpEntryInfo itemSpEntryInfo = new RepairWORWOItemSpEntryInfo();
			itemSpEntryInfo.setId(BOSUuid.create("FF1F0E1A"));
			itemSpEntryInfo.put("t",cmbT.getSelectedItem());
			CustomerAccountInfo caInfo = (CustomerAccountInfo) prmtCustomerAccount.getData();
			if (caInfo != null) {
				String sql = String.format("select isnull(CFRetailDiscountRate,0) CFRetailDiscountRate,isnull(CFRepairDiscountRate,0) CFRepairDiscountRate from CT_RS_CustomerAccount where FID='%s'",caInfo.getString("id"));
				IRowSet rs;
				try {
					rs = DBUtils.executeQuery(null, sql);
					if (rs != null && rs.next()) {
						if (PublicUtils.equals(TEnum.P,cmbT.getSelectedItem())) {
							itemSpEntryInfo.setDiscountRate(rs.getBigDecimal("CFRetailDiscountRate"));
							itemSpEntryInfo.setUnIssueQty(BIGDEC1);
						}
						else if (PublicUtils.equals(TEnum.L,cmbT.getSelectedItem())) {
							itemSpEntryInfo.setDiscountRate(rs.getBigDecimal("CFRepairDiscountRate"));
							itemSpEntryInfo.setUnIssueQty(BIGDEC0);
						}
					}
				} catch (Exception e) {
					UIUtils.handUIExceptionAndAbort(e);
				
				}
			} else itemSpEntryInfo.setDiscountRate(BIGDEC0);
			
			itemSpEntryInfo.setI(IEnum.X);
			itemSpEntryInfo.setQty(BIGDEC1);
			
			itemSpEntryInfo.setIssueQty(BIGDEC0);
			itemSpEntryInfo.setPrice(BIGDEC0);
			itemSpEntryInfo.setAmount(BIGDEC0);
			itemSpEntryInfo.setTaxRate(new BigDecimal(17.00));
			itemSpEntryInfo.setSettlementObject(SettlementObjectEnum.CUST);
			
			
			
			return itemSpEntryInfo;
		} else {
			return super.createNewDetailData(table);
		}
	}
	@Override
	public void loadFields() {

		super.loadFields();
		
		try {
			if (!isLoadOprtPermission) {
				getLineOprtPermission();
				
			}
		} catch (Exception e) {
			UIUtils.handUIException(e);
		}
		for (int i = 0;i < kdtRWOItemSpEntry.getRowCount(); i++)
			resetItemSpEditorLocked(kdtRWOItemSpEntry.getRow(i));
		prmtBrand.setEnabled(false);
		
	}
	@Override
	protected void unLockUI() {
		super.unLockUI();
		setCmbT_Permission();
		
	}
	
	private void setCmbT_Permission()  {
		if (hasPermission_OprtRepairLine && !hasPermission_OprtRetailLine) { //只有维修行权限
			cmbT.setEnabled(false);
			cmbT.setSelectedItem(TEnum.L);
			labRepairItem.setVisible(true);
		} else if (!hasPermission_OprtRepairLine && hasPermission_OprtRetailLine) { //只有配件行权限
			cmbT.setEnabled(false);
			cmbT.setSelectedItem(TEnum.P);
			labMaterial.setVisible(true);
		} else if (hasPermission_OprtRepairLine && hasPermission_OprtRetailLine) { //有配件、维修行权限
			cmbT.setEnabled(true);
			cmbT.setSelectedItem(TEnum.L);
			labRepairItem.setVisible(true);
		} else {
			cmbT.setEnabled(false);
			cmbT.setSelectedItem(null);
			labRepairItem.setVisible(false);
			labMaterial.setVisible(false);
		}
	}
	
	@Override
	protected void loadLineFields(KDTable table, IRow row, IObjectValue obj) {
		super.loadLineFields(table, row, obj);
		if (table.getName().equals(kdtRWOItemSpEntry.getName())) {
			resetItemSpEditorLocked(row);
		}
	}
	@Override
	protected void appendFootRow(KDTable table) {
		super.appendFootRow(table);
		if (kdtRWOItemSpEntry.equals(table))
			appendItemSpFoot();
	}
	@Override
	protected void setTableToSumField() {
		super.setTableToSumField();
		//setTableToSumField(kdtRWOItemSpEntry, new String[]{"qty"});
	}
	/**
	 * 重新处理addline事件，因为tab页签位置变更
	 */
    public void actionAddLine_actionPerformed(ActionEvent e) throws Exception {
    	
    	if (kDTRWOPane.getSelectedIndex() == 0 && kdtRWOItemSpEntry != null) {
    		addLine(kdtRWOItemSpEntry);
    		
			appendFootRow(kdtRWOItemSpEntry);
    	} else {
    		return;
    	}
		if (kDTRWOPane.getSelectedIndex() == 1 && kdtRWORepairItemEntry != null) {
			addLine(kdtRWORepairItemEntry);
			appendFootRow(kdtRWORepairItemEntry);
		}
		if (kDTRWOPane.getSelectedIndex() == 2 && kdtRWOSparepartEntry != null) {
			addLine(kdtRWOSparepartEntry);
			appendFootRow(kdtRWOSparepartEntry);
		}
		if (kDTRWOPane.getSelectedIndex() == 3 && kdtRWORepairPkgEntry != null) {
			addLine(kdtRWORepairPkgEntry);
			appendFootRow(kdtRWORepairPkgEntry);
		}
		if (kDTRWOPane.getSelectedIndex() == 4 && kdtRWOActivityEntry != null) {
			addLine(kdtRWOActivityEntry);
			appendFootRow(kdtRWOActivityEntry);
		}
		if (kDTRWOPane.getSelectedIndex() == 5 && kdtRWOAttachmentItemEntry != null) {
			addLine(kdtRWOAttachmentItemEntry);
			appendFootRow(kdtRWOAttachmentItemEntry);
		}
	}
    public void actionInsertLine_actionPerformed(ActionEvent e)
			throws Exception {
    	if (kDTRWOPane.getSelectedIndex() == 0 && kdtRWORepairItemEntry != null) {
			insertLine(kdtRWOItemSpEntry);
			appendFootRow(kdtRWOItemSpEntry);
		} else {
			return;
		}
		if (kDTRWOPane.getSelectedIndex() == 1 && kdtRWORepairItemEntry != null) {
			insertLine(kdtRWORepairItemEntry);
			appendFootRow(kdtRWORepairItemEntry);
		}
		if (kDTRWOPane.getSelectedIndex() == 2 && kdtRWOSparepartEntry != null) {
			insertLine(kdtRWOSparepartEntry);
			appendFootRow(kdtRWOSparepartEntry);
		}
		if (kDTRWOPane.getSelectedIndex() == 3 && kdtRWORepairPkgEntry != null) {
			insertLine(kdtRWORepairPkgEntry);
			appendFootRow(kdtRWORepairPkgEntry);
		}
		if (kDTRWOPane.getSelectedIndex() == 4 && kdtRWOActivityEntry != null) {
			insertLine(kdtRWOActivityEntry);
			appendFootRow(kdtRWOActivityEntry);
		}
		if (kDTRWOPane.getSelectedIndex() == 5 && kdtRWOAttachmentItemEntry != null) {
			insertLine(kdtRWOAttachmentItemEntry);
			appendFootRow(kdtRWOAttachmentItemEntry);
		}
	}
    
    private boolean checkRemoveLine() throws Exception {
    	if(kdtRWOItemSpEntry.getSelectManager().size() == 0) return true;
    	getHadIssueMaterialRow();
    	HashMap<Integer,Integer> hashSelRow = new HashMap<Integer, Integer>();
    	KDTSelectManager smItemSpEntry = kdtRWOItemSpEntry.getSelectManager();
    	ArrayList<KDTSelectBlock> lstItemSpEntry = smItemSpEntry.getBlocks();
    	for (int i = 0; i < lstItemSpEntry.size(); i++) {
    		KDTSelectBlock sb = lstItemSpEntry.get(i);
    		for (int j = sb.getBeginRow(); j <= sb.getEndRow(); j++) {
    			hashSelRow.put(j, j);
    		}
    	}
    	Set<Integer> setSelRow = hashSelRow.keySet();
    	Integer[] iSelRow = PublicUtils.setToInteger(setSelRow);
    	
    	//检查是否可删除
    	boolean isCanRemoveLine = true;

    	StringBuilder errMsg = new StringBuilder();
    	for (int i = 0; iSelRow != null && i < iSelRow.length; i++) {
    		IRow rowItemSpEntry = kdtRWOItemSpEntry.getRow(iSelRow[i]);
    		RepairWORWOItemSpEntryInfo rwoItemSpEntryInfo = (RepairWORWOItemSpEntryInfo) rowItemSpEntry.getUserObject();
    		String itemSpEntryId = rwoItemSpEntryInfo.getString("id");
    		TEnum tType = rwoItemSpEntryInfo.getT();
    		boolean isAPSettle = rwoItemSpEntryInfo.isIsAPSettle();
    		IEnum iType = rwoItemSpEntryInfo.getI();
    		BigDecimal amount = rwoItemSpEntryInfo.getAmount();
    		BigDecimal issueQty = rwoItemSpEntryInfo.getIssueQty();
    		if (TEnum.L.equals(tType)) { //维修项目
    			if (!hasPermission_OprtRepairLine) {
    				MsgBoxEx.showInfo("无操作维修数据行权限！");
    				return false;
    			}
    			if (isAPSettle) {
    				MsgBoxEx.showInfo("已应付结算维修数据行，不允许删除！");
    				return false;
    			}
    			if (PublicUtils.equals(IEnum.X, iType) && amount.compareTo(BIGDEC0) != 0) {
    				MsgBoxEx.showInfo("已结算维修数据行，不允许删除！");
    				return false;
    			}
    			
    			int removeRowIndex = getRowIndexByItemSpEntryId(kdtRWORepairItemEntry,itemSpEntryId);
    			if (removeRowIndex > -1) {
    				IRow row = kdtRWORepairItemEntry.getRow(removeRowIndex);
    				boolean currentCanRemoveLine = canRemoveLineForRepairItemEntry(row,errMsg);
    				if (isCanRemoveLine) isCanRemoveLine = currentCanRemoveLine;
    			}
    			
    		} else if (TEnum.P.equals(tType)) { //配件
    			if (!hasPermission_OprtRetailLine) {
    				MsgBoxEx.showInfo("无操作配件数据行权限！");
    				return false;
    			}
    			if (isAPSettle) {
    				MsgBoxEx.showInfo("已应付结算配件数据行，不允许删除！");
    				return false;
    			}
    			if (PublicUtils.equals(IEnum.X, iType) && amount.compareTo(BIGDEC0) != 0) {
    				MsgBoxEx.showInfo("已结算配件数据行，不允许删除！");
    				return false;
    			}
    			if (issueQty.compareTo(BIGDEC0) != 0) {
    				MsgBoxEx.showInfo("已出库配件数据行，不允许删除！");
    				return false;
    			}
    			
    			
    			int removeRowIndex = getRowIndexByItemSpEntryId(kdtRWOSparepartEntry,itemSpEntryId);
    			if (removeRowIndex > -1) {
    				IRow row = kdtRWOSparepartEntry.getRow(removeRowIndex);
					boolean currentCanRemoveLine = canRemoveLineForSparepartEntry(row,errMsg);
					if (isCanRemoveLine) isCanRemoveLine = currentCanRemoveLine;
    			}
    		}
    		
    	}
    	
    	if (!isCanRemoveLine) {
    		MsgBoxEx.showDetailAndOK(this, "删除行失败，请查看明细", errMsg.toString(), MsgBox.OK);
    		return false;
    	}
    	for (int i = 0; iSelRow != null && i < iSelRow.length; i++) {
    		IRow rowItemSpEntry = kdtRWOItemSpEntry.getRow(iSelRow[i]);
    		RepairWORWOItemSpEntryInfo rwoItemSpEntryInfo = (RepairWORWOItemSpEntryInfo) rowItemSpEntry.getUserObject();
    		String itemSpEntryId = rwoItemSpEntryInfo.getString("id");
    		TEnum tType = rwoItemSpEntryInfo.getT();
    		if (TEnum.L.equals(tType)) { //维修项目
    			int removeRowIndex = getRowIndexByItemSpEntryId(kdtRWORepairItemEntry,itemSpEntryId);
    			if (removeRowIndex > -1) {
    				kdtRWORepairItemEntry.removeRow(removeRowIndex);
    			}
    			
    		} else if (TEnum.P.equals(tType)) { //配件
    			int removeRowIndex = getRowIndexByItemSpEntryId(kdtRWOSparepartEntry,itemSpEntryId);
    			if (removeRowIndex > -1)
    				kdtRWOSparepartEntry.removeRow(removeRowIndex);
    		}
    	}
    	dealPkgTableAfterDeleteItemOrSpar();
		appendFootRow(kdtRWORepairItemEntry);
		appendFootRow(kdtRWOSparepartEntry);
		
    	return true;
    }
    
    private boolean canRemoveLineForRepairItemEntry(IRow row,StringBuilder errMsg) throws Exception {
    	 if (oprtState.equals("ADDNEW")
			|| RepairBillStatusEnum.SAVE.equals(editData.getStatus())
			|| RepairBillStatusEnum.SENDING.equals(editData.getStatus())
			|| RepairBillStatusEnum.ABOLISH.equals(editData.getStatus())) return true;
    	 else {
    		 if (row.getCell("IsAppend").getValue() == null
						|| row.getCell("IsAppend").getValue().equals(Boolean.FALSE)
						|| row.getCell(CeLL_ItemStatus).getValue() != null 
						&& !row.getCell(CeLL_ItemStatus).getValue().equals(RepairItemStatusEnum.SENDING)) {
					if (!row.getCell(CeLL_ItemStatus).getValue().equals(RepairItemStatusEnum.REPAIRING)
							&& !row.getCell(CeLL_ItemStatus).getValue().equals(RepairItemStatusEnum.FINISHED)
							&& !row.getCell(CeLL_ItemStatus).getValue().equals(RepairItemStatusEnum.INSPECTED)) {
					} else {
						RepairItemInfo repairItemInfo = (RepairItemInfo) row.getCell("RepairItem").getValue();
						String msg = "已派工的维修项目，不能进行删除操作！";
						if (repairItemInfo != null)
							msg = String.format("已派工[%s(%s)]的维修项目，不能进行删除操作！", repairItemInfo.getName(),repairItemInfo.getNumber());
						errMsg.append(msg).append(CR);
						return false;
					}
    		 }	
    		 return true;
    	 }
    }
    
    private boolean canRemoveLineForSparepartEntry(IRow row, StringBuilder errMsg) throws Exception {
    	if (oprtState.equals("ADDNEW")
				|| RepairBillStatusEnum.SAVE.equals(editData.getStatus())
				|| RepairBillStatusEnum.SENDING.equals(editData.getStatus())
				|| row.getCell("IsAppend").getValue() != null) {
			if (!oprtState.equals("ADDNEW")) {
				if (null != row.getCell("ID").getValue()) {
					if (null == row.getCell("Material").getValue()
							|| (row.getCell("Material").getValue() instanceof Object[])
							&& ((Object[]) (Object[]) row.getCell("Material").getValue()).length == 0)
						removeLine(kdtRWOSparepartEntry);
					MaterialInfo materialInfo = (MaterialInfo) row.getCell("Material").getValue();
					String rowID = UIRuleUtil.getString(row.getCell("ID").getValue());
					if (null != hadIssueMaterialSet && hadIssueMaterialSet.contains(rowID)) {
						String msg = String.format("配件[%s(%s)]己经生成过出库(或退货)单,不能删除！", materialInfo.getName(),materialInfo.getNumber());
						errMsg.append(msg).append(CR);
						return false;
					}
				}
				if (row.getCell(Cell_IssueQty).getValue() != null) {
					if (UIRuleUtil.getBigDecimal(row.getCell(Cell_IssueQty).getValue()).compareTo(BigDecimal.valueOf(0L)) == 1) {
						MaterialInfo materialInfo = (MaterialInfo) row.getCell("Material").getValue();
						String msg = String.format("配件[%s(%s)]已出库，不能进行删除操作！", materialInfo.getName(),materialInfo.getNumber());
						errMsg.append(msg).append(CR);
						return false;
					} else if (row.getCell("IsAppend").getValue().equals(Boolean.FALSE)
							&& (RepairBillStatusEnum.REPAIRING.equals(editData.getStatus())
									|| RepairBillStatusEnum.BREAK.equals(editData.getStatus())
									|| RepairBillStatusEnum.FINISH.equals(editData.getStatus()) 
									|| RepairBillStatusEnum.INSPECT.equals(editData.getStatus()))) {
						if (UIRuleUtil.getBigDecimal(row.getCell(Cell_IssueQty).getValue()).compareTo(BigDecimal.valueOf(0L)) == 1) {
							MaterialInfo materialInfo = (MaterialInfo) row.getCell("Material").getValue();
							String msg = String.format("配件[%s(%s)]已出库，不能进行删除操作！", materialInfo.getName(),materialInfo.getNumber());
							errMsg.append(msg).append(CR);
							return false;
						} 
					} 
				} else if (null != row.getCell("IsAppend").getValue()
						&& row.getCell("IsAppend").getValue().equals(Boolean.FALSE)
						&& (RepairBillStatusEnum.REPAIRING.equals(editData.getStatus())
								|| RepairBillStatusEnum.BREAK.equals(editData.getStatus())
								|| RepairBillStatusEnum.FINISH.equals(editData.getStatus())
								|| RepairBillStatusEnum.WASH.equals(editData.getStatus()) || RepairBillStatusEnum.INSPECT.equals(editData.getStatus()))) {
					row.getCell("IsDelete").setValue(Boolean.TRUE);
					isDisplayDeleteRows(kdtRWOSparepartEntry,row.getRowIndex());
				} else if (UIRuleUtil.getBigDecimal(row.getCell(Cell_IssueQty).getValue()).compareTo(BigDecimal.valueOf(0L)) == 1) {
					MaterialInfo materialInfo = (MaterialInfo) row.getCell("Material").getValue();
					String msg = String.format("配件[%s(%s)]已出库，不能进行删除操作！", materialInfo.getName(),materialInfo.getNumber());
					errMsg.append(msg).append(CR);
					return false;
				} 
			} 
		}
    	
    	return true;
    	
    }
    
    
    private int getRowIndexByItemSpEntryId(KDTable tbl, String itemSpEntryId) {
    	for (int i = 0; i < tbl.getRowCount(); i++) {
    		IRow row = tbl.getRow(i);
    		String rowItemSpEntryId = row.getCell("itemSpEntryId").getValue().toString();
    		if (itemSpEntryId.equals(rowItemSpEntryId)) return i;
    	}
    	return -1;
    	
    }
    
	public void actionRemoveLine_actionPerformed(ActionEvent e)
			throws Exception {
		if (kDTRWOPane.getSelectedIndex() == 0 && kdtRWOItemSpEntry != null) {
			
			if (!checkRemoveLine()) return;
			
			removeLine(kdtRWOItemSpEntry);
			appendFootRow(kdtRWOItemSpEntry	);
			appendItemSpFoot();
		} else {
			return;
		}
		
		//RepairWOInfo info = editData;
		if (kDTRWOPane.getSelectedIndex() == 1) {
			if (kdtRWORepairItemEntry != null)
				if (oprtState.equals("ADDNEW")
						|| RepairBillStatusEnum.SAVE.equals(editData.getStatus())
						|| RepairBillStatusEnum.SENDING.equals(editData.getStatus())
						|| RepairBillStatusEnum.ABOLISH.equals(editData.getStatus())) {
					removeLine(kdtRWORepairItemEntry);
					afterRemoveRepairItem();
					dealPkgTableAfterDeleteItemOrSpar();
					appendFootRow(kdtRWORepairItemEntry);
				} else if (getSelectedRow(kdtRWORepairItemEntry) != null)
					if (getSelectedRow(kdtRWORepairItemEntry).getCell("IsAppend").getValue() == null
							|| getSelectedRow(kdtRWORepairItemEntry).getCell("IsAppend").getValue().equals(Boolean.FALSE)
							|| getSelectedRow(kdtRWORepairItemEntry).getCell(CeLL_ItemStatus).getValue() != null 
							&& !getSelectedRow(kdtRWORepairItemEntry).getCell(CeLL_ItemStatus).getValue().equals(RepairItemStatusEnum.SENDING)) {
						if (!getSelectedRow(kdtRWORepairItemEntry).getCell(CeLL_ItemStatus).getValue().equals(RepairItemStatusEnum.REPAIRING)
								&& !getSelectedRow(kdtRWORepairItemEntry).getCell(CeLL_ItemStatus).getValue().equals(RepairItemStatusEnum.FINISHED)
								&& !getSelectedRow(kdtRWORepairItemEntry).getCell(CeLL_ItemStatus).getValue().equals(RepairItemStatusEnum.INSPECTED)) {
							getSelectedRow(kdtRWORepairItemEntry).getCell("IsDelete").setValue(Boolean.TRUE);
							isDisplayDeleteRows(kdtRWORepairItemEntry,getSelectedRow(kdtRWORepairItemEntry).getRowIndex());
							getSelectedRow(kdtRWORepairItemEntry).getStyleAttributes().setLocked(true);
							kdtRWORepairItemEntry_detailPanel.getRemoveLinesButton().setEnabled(false);
						} else {
							MsgBox.showInfo("已派工的维修项目，不能进行删除操作！");
							SysUtil.abort();
						}
					} else {
						removeLine(kdtRWORepairItemEntry);
						dealPkgTableAfterDeleteItemOrSpar();
						appendFootRow(kdtRWORepairItemEntry);
					}
			checkRepeatItem(kdtRWORepairItemEntry,
					"ENTRY_WORKTIMESTD_REPAIRITEM_REPEAT", "RepairItem",
					"number");
		}
		if (kDTRWOPane.getSelectedIndex() == 2) {
			deleteKdtRWOSparepartEntry();
			checkRepeat();
		}
		if (kDTRWOPane.getSelectedIndex() == 3 && kdtRWORepairPkgEntry != null) {
			beforeDelete(kdtRWORepairPkgEntry);
			removeLine(kdtRWORepairPkgEntry);
			appendFootRow(kdtRWORepairPkgEntry);
		}
		if (kDTRWOPane.getSelectedIndex() == 4 && kdtRWOActivityEntry != null) {
			beforeDelete(kdtRWOActivityEntry);
			removeLine(kdtRWOActivityEntry);
			appendFootRow(kdtRWOActivityEntry);
		}
		if (kDTRWOPane.getSelectedIndex() == 5 && kdtRWOAttachmentItemEntry != null) {
			removeLine(kdtRWOAttachmentItemEntry);
			appendFootRow(kdtRWOAttachmentItemEntry);
		}
		
		isModify = true;
	}
	
	public void initItemSpMaterialF7(IColumn column) {
		initItemSpMaterialF7(column,null);
	}
	
	public void initItemSpMaterialF7(ICell cell) {
		initItemSpMaterialF7(null,cell);
	}
	/*
	private void registePrmtRepairManF7() {
		prmtrepairMan.setDisplayFormat("$name$");
		prmtrepairMan.setCommitFormat("$name$;tel");
		prmtrepairMan.setEditFormat("$name$");
		prmtrepairMan.setEnabledMultiSelection(false);
		prmtrepairMan.addSelectorListener(new SelectorListener() {
			RepairSenderF7UI f7 = null;
			public void willShow(SelectorEvent arg0) { 
				try {
					if (vehicleInfo == null) {
						throw new EASBizException(new NumericExceptionSubItem("","请先录入车辆信息！"));
					}
					Map uictx = new HashMap();
					uictx.put("vehicleInfo", vehicleInfo);
					uictx.put("curRepairSenderInfo", prmtrepairMan.getValue());
					IUIFactory iUIFactory = UIFactory.createUIFactory(UIFactoryName.MODEL);
					IUIWindow iUIWindowSizesHEdit = iUIFactory.create(RepairSenderF7UI.class.getName(),uictx); // 获取FeedRecListUI的IUIWindow
					f7 = (RepairSenderF7UI) iUIWindowSizesHEdit.getUIObject();
					iUIWindowSizesHEdit.show();
					prmtrepairMan.setSelector(f7);
				} catch (Exception e) {
					UIUtils.handUIExceptionAndAbort(e);
				}
			}
		});
		prmtrepairMan.addDataChangeListener(new DataChangeListener() {
			public void dataChanged(DataChangeEvent datachangeevent) {
				Object obj =  prmtrepairMan.getValue();
				VehicleRepairSenderInfo repairManInfo = null;
				if (obj instanceof VehicleRepairSenderInfo) {
					repairManInfo = (VehicleRepairSenderInfo)obj;
				} else if (obj instanceof Object[]){
					repairManInfo =	(VehicleRepairSenderInfo)((Object[])obj)[0];
				}
				
				if (repairManInfo != null) {
					txtRepairSender.setText(repairManInfo.getName());
					txtTel.setText(repairManInfo.getTel());
				} else {
					txtRepairSender.setText(null);
					txtTel.setText(null);
				}
			}
			
		});
		
		prmtrepairMan.getEditor().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
			}
		});
	}
	
	*/
	private void registePrmtRepairItemF7() {

		//prmtRepairItem.setVisible(true);
		//prmtRepairItem.setEditable(true);
		prmtRepairItem.setDisplayFormat("$number$");
		prmtRepairItem.setEditFormat("$number$");
		prmtRepairItem.setCommitFormat("$number$;$name$");
		prmtRepairItem.setEnabledMultiSelection(true);
		
		ObjectValueRender kdtRWORepairItemEntry_RepairItem_OVR = new ObjectValueRender();
		kdtRWORepairItemEntry_RepairItem_OVR.setFormat(new BizDataFormat("$number$"));

		prmtRepairItem.addSelectorListener(new SelectorListener() {
			RepairItemEntryListUI repairItemEntryF7ListUI;
			public void willShow(SelectorEvent e) {
				try {
					repairItemEntryF7ListUI = new RepairItemEntryListUI();
					FilterInfo filterInfo = getVehicleFilterInfo();
					
					BrandInfo brandInfo = (BrandInfo) prmtBrand.getValue();
					if (brandInfo != null) {
						FilterInfo filterBrand = new FilterInfo();
						filterBrand.getFilterItems().add(new FilterItemInfo("parent.brand.id",brandInfo.getString("id")));
						filterInfo.mergeFilter(filterBrand, "AND");
					}
					repairItemEntryF7ListUI.setExpandFilter(filterInfo);
					repairItemEntryF7ListUI.setSingleSelect(false);
					repairItemEntryF7ListUI.setF7Use(true, null);
					prmtRepairItem.setSelector(repairItemEntryF7ListUI);
					System.out.println("");
				} catch (Exception e1) {
					handUIException(e1);
				}
			}
		});
		prmtRepairItem.addDataChangeListener(new DataChangeListener() {

			public void dataChanged(DataChangeEvent eventObj) {
				
				
				String repairItemNums = "";
				int beginRowIndex = 0;
				  if(null != eventObj && null != eventObj.getNewValue())  {
					  Object newObject = prmtRepairItem.getValue();
					  RepairItemEntryInfo repairItemEntryInfos[] = null;
					  if(newObject instanceof Object[]) {
						  Object objs[] = (Object[])(Object[])newObject;
						  repairItemEntryInfos = new RepairItemEntryInfo[objs.length];
						  System.arraycopy(((Object) (objs)), 0, repairItemEntryInfos, 0, objs.length);
	                  } else {
	                   	  return;
	                  }
					  if(repairItemEntryInfos == null || repairItemEntryInfos.length <= 0)
				          return;
					  
					  
					  KDTable kdTable = kdtRWOItemSpEntry;
					  if(null == kdTable)
				         return;
					  prmtRepairItem_dataChange(kdTable,repairItemEntryInfos);
				  }
			prmtRepairItem.setValue(null);
			}
		});
		
		prmtRepairItem.getEditor().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					String repairtItemWhereLikeStr = ((com.kingdee.bos.ctrl.swing.KDPromptBox.DefaultEditor) e
							.getSource()).getText();
					try {
						repairItemEntryF7ListUI = new RepairItemEntryListUI();
						repairItemEntryF7ListUI.setSingleSelect(false);
						repairItemEntryF7ListUI.setParentNumber(repairtItemWhereLikeStr);
						repairItemEntryF7ListUI.setExpandFilter(getVehicleFilterInfo());
						repairItemEntryF7ListUI.setF7Use(true, null);
						KDTextField txtSearch = (KDTextField) InvokeUtils.getFieldValue(repairItemEntryF7ListUI, "fastSearchTextField");
						txtSearch.setText(repairtItemWhereLikeStr);
						prmtRepairItem.setSelector(repairItemEntryF7ListUI);
					} catch (Exception e1) {
						handUIException(e1);
					}
					Object object = repairItemEntryF7ListUI.getData();
					if (null == object) {
						repairItemEntryF7ListUI.show();
						object = repairItemEntryF7ListUI.getData();
					}
					if (null != object) {
						RepairItemEntryInfo repairItemEntryInfos[] = null;
						if (object instanceof Object[]) {
							Object objs[] = (Object[]) (Object[]) object;
							repairItemEntryInfos = new RepairItemEntryInfo[objs.length];
							System.arraycopy(((Object) (objs)), 0, repairItemEntryInfos, 0, objs.length);
						} else {
							return;
						}
						prmtRepairItem_dataChange(kdtRWOItemSpEntry,repairItemEntryInfos);
						
						//repairItem_PromptBox_dataChanged(repairItemEntryInfos);
					}
				}
			}

			RepairItemEntryListUI repairItemEntryF7ListUI;
		});
	}
	
	private void prmtRepairItem_dataChange(KDTable kdTable, RepairItemEntryInfo[] repairItemEntryInfos) {
		 // KDTSelectManager selectManager = kdTable.getSelectManager();
        // KDTSelectBlock selectBlock = selectManager.get(0);
		if (PublicUtils.isEmpty(txtMile.getText())) {
			MsgBoxEx.showInfo("进厂行驶里程不能为空");
			return;
		}
         //if(selectBlock == null)
       	 int beginRowIndex = kdTable.getRowCount();
         //else beginRowIndex = selectBlock.getBeginRow();
         //if (beginRowIndex > 0 && selectBlock == null) 
       //	  beginRowIndex--;
       //  boolean isFirstRowIndex = true;
       //已全部结算，不允许添加数据
		  if (PublicUtils.equals(gaBillStatus.getSelectedItem(),RepairWOStatusEnum.AllSettle)) {
			  MsgBoxEx.showInfo("已全部结算，不允许添加新项目！");
			  prmtRepairItem.setValue(null);
			  return;
		  }
         int i = 0;
         for(int length = repairItemEntryInfos.length; i < length; i++) {
             int rowIndex = beginRowIndex + i;
             RepairItemEntryInfo repairItemEntryInfo = repairItemEntryInfos[i];
             RepairItemInfo repairItemInfo = repairItemEntryInfo.getParent();
            // repairItemNums = repairItemNums + repairItemInfo.getNumber() + ";";
           /*  if(isFirstRowIndex)  {
           	  if (beginRowIndex==0)
           		  insertLine(kdTable, rowIndex);
           	  else if (kdTable.getCell(rowIndex, "repairItem").getValue() != null
           			  || kdTable.getCell(rowIndex, "material").getValue() != null)
           		  insertLine(kdTable, rowIndex);

              } else {
                insertLine(kdTable, rowIndex);
                
              }*/
             insertLine(kdTable, rowIndex);
             kdTable.getCell(rowIndex, "repairItem").setValue(repairItemInfo);
             kdTable.getCell(rowIndex, "t").setValue(TEnum.L);
             kdTable.getCell(rowIndex,"itemspNum").setValue(repairItemInfo.getNumber());
             kdTable.getCell(rowIndex,"itemspName").setValue(repairItemInfo.getName());
             kdTable.getCell(rowIndex, "issueQty").setValue(null);
             kdTable.getCell(rowIndex, "unIssueQty").setValue(null);
             if (PublicUtils.equals(repairItemInfo, defaultRepairItemForTXT)) {
            	 kdTable.getCell(rowIndex, "price").setValue(BIGDEC0);
             } else {
            	 kdTable.getCell(rowIndex, "price").setValue(new BigDecimal(39));
             }
             if (PublicUtils.equals(repairItemInfo, defaultRepairItemForDJQ)) {
            	 kdTable.getCell(rowIndex, "qty").setValue(new BigDecimal(-1));
             }
             try {
				calItemSpEntryAmount(kdTable.getRow(rowIndex));
			} catch (Exception e) {
				UIUtils.handUIException(e);
			}
          //   isFirstRowIndex = false;
         }
         int qtyIndex = kdTable.getColumnIndex("qty");
   	  	kdTable.getEditManager().editCellAt(beginRowIndex, qtyIndex);
		
	}
	
	private KDBizPromptBox getRepairItemPrmt() {
		final KDBizPromptBox prmt= new KDBizPromptBox();
		prmt.setVisible(true);
		prmt.setEditable(true);
		prmt.setDisplayFormat("$number$");
		prmt.setEditFormat("$number$");
		prmt.setCommitFormat("$number$");
		prmt.setEnabledMultiSelection(true);
		
		ObjectValueRender kdtRWORepairItemEntry_RepairItem_OVR = new ObjectValueRender();
		kdtRWORepairItemEntry_RepairItem_OVR.setFormat(new BizDataFormat("$number$"));

		prmt.addSelectorListener(new SelectorListener() {
			RepairItemEntryListUI repairItemEntryF7ListUI;
			public void willShow(SelectorEvent e) {
				try {
					repairItemEntryF7ListUI = new RepairItemEntryListUI();
					repairItemEntryF7ListUI.setExpandFilter(getVehicleFilterInfo());
					repairItemEntryF7ListUI.setSingleSelect(false);
					repairItemEntryF7ListUI.setF7Use(true, null);
					prmt.setSelector(repairItemEntryF7ListUI);
					System.out.println("");
				} catch (Exception e1) {
					handUIException(e1);
				}
			}
		});
		prmt.addDataChangeListener(new DataChangeListener() {

			public void dataChanged(DataChangeEvent eventObj) {
				  if(null != eventObj && null != eventObj.getNewValue())  {
					  Object newObject = prmt.getValue();
					  RepairItemEntryInfo repairItemEntryInfos[] = null;
					  if(newObject instanceof Object[]) {
						  Object objs[] = (Object[])(Object[])newObject;
						  repairItemEntryInfos = new RepairItemEntryInfo[objs.length];
						  System.arraycopy(((Object) (objs)), 0, repairItemEntryInfos, 0, objs.length);
	                  } else {
	                   	  return;
	                  }
					  if(repairItemEntryInfos == null || repairItemEntryInfos.length <= 0)
				          return;
					  KDTable kdTable = kdtRWOItemSpEntry;
					  if(null == kdTable)
				         return;
					  KDTSelectManager selectManager = kdTable.getSelectManager();
			          KDTSelectBlock selectBlock = selectManager.get(0);
			          if(selectBlock == null)
			             return;
			          boolean isFirstRowIndex = true;
			          int beginRowIndex = selectBlock.getBeginRow();
			          int i = 0;
			          for(int length = repairItemEntryInfos.length; i < length; i++) {
			              int rowIndex = beginRowIndex + i;
			              RepairItemEntryInfo repairItemEntryInfo = repairItemEntryInfos[i];
			              RepairItemInfo repairItemInfo = repairItemEntryInfo.getParent();
			              if(isFirstRowIndex)  {
	
			               } else {
			                 insertLine(kdTable, rowIndex);		                 
			               }
			              kdTable.getCell(rowIndex, "repairItem").setValue(repairItemInfo);
			              kdTable.getCell(rowIndex, "t").setValue(TEnum.L);
			              kdTable.getCell(rowIndex,"itemspNum").setValue(repairItemInfo.getNumber());
			              kdTable.getCell(rowIndex,"itemspName").setValue(repairItemInfo.getName());
			              kdTable.getCell(rowIndex, "issueQty").setValue(null);
			              kdTable.getCell(rowIndex, "unIssueQty").setValue(null);
			              isFirstRowIndex = false;
			          }
				  }	
			}
		});
		
		prmt.getEditor().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					String repairtItemWhereLikeStr = ((com.kingdee.bos.ctrl.swing.KDPromptBox.DefaultEditor) e
							.getSource()).getText();
					try {
						repairItemEntryF7ListUI = new RepairItemEntryListUI();
						repairItemEntryF7ListUI.setSingleSelect(false);
						repairItemEntryF7ListUI.setParentNumber(repairtItemWhereLikeStr);
						repairItemEntryF7ListUI.setExpandFilter(getVehicleFilterInfo());
						repairItemEntryF7ListUI.setF7Use(true, null);
						prmt.setSelector(repairItemEntryF7ListUI);
					} catch (Exception e1) {
						handUIException(e1);
					}
					Object object = repairItemEntryF7ListUI.getData();
					if (null == object) {
						repairItemEntryF7ListUI.show();
						object = repairItemEntryF7ListUI.getData();
					}
					if (null != object) {
						RepairItemEntryInfo repairItemEntryInfos[] = null;
						if (object instanceof Object[]) {
							Object objs[] = (Object[]) (Object[]) object;
							repairItemEntryInfos = new RepairItemEntryInfo[objs.length];
							System.arraycopy(((Object) (objs)), 0, repairItemEntryInfos, 0, objs.length);
						} else {
							return;
						}
						//repairItem_PromptBox_dataChanged(repairItemEntryInfos);
					}
				}
			}

			RepairItemEntryListUI repairItemEntryF7ListUI;
		});
		return prmt;
	}
	
	private void registePrmtMaterialF7() {

		if (orgUnitInfo != null) {
			prmtMaterial.setQueryInfo("com.kingdee.eas.basedata.master.material.app.RepairMaterialF7Query");
			String mergeColumnKeys[] = { "number", "name", "model", "helpCode", "baseUnit.name", "baseQty" };
			prmtMaterial.setMergeColumnKeys(mergeColumnKeys);
			prmtMaterial.setDisplayFormat("$number$");
			prmtMaterial.setEditFormat("$number$");
			prmtMaterial.setCommitFormat("$number$;$name$;$alias$;$helpCode$");
			prmtMaterial.setEnabledMultiSelection(true);
			setMaterialF7Filter();
			prmtMaterial.setEntityViewInfo(sparepartEntry_Material_PromptBox.getEntityViewInfo());
			
			prmtMaterial.setAutoFocusNextComponent(false);
			prmtMaterial.addCommitListener(new CommitListener() {
						public void willCommit(CommitEvent e) {
							FilterInfo orgUnitFilter = new FilterInfo();
							FilterItemCollection filterItemCollection = orgUnitFilter.getFilterItems();
							filterItemCollection.add(new FilterItemInfo("SaleOrgUnit.id", orgUnitInfo.getId()));
							filterItemCollection.add(new FilterItemInfo("status",new Integer(UsedStatusEnum.APPROVED.getValue())));
							FilterInfo vehicleFilterInfo = getVehicleFilterInfo();
							FilterInfo megerFilterInfo = new FilterInfo();
							if (orgUnitFilter != null&& !StringUtils.isEmpty(orgUnitFilter.toString()))
								try {
									megerFilterInfo.mergeFilter(orgUnitFilter,"AND");
								} catch (Exception exc) {
									handUIException(exc);
								}
							if (vehicleFilterInfo != null
									&& !StringUtils.isEmpty(vehicleFilterInfo.toString()))
								try {
									megerFilterInfo.mergeFilter(vehicleFilterInfo, "AND");
								} catch (Exception exc) {
									handUIException(exc);
								}
							EntityViewInfo entityViewInfo = new EntityViewInfo();
							entityViewInfo.setFilter(megerFilterInfo);
							prmtMaterial.setEntityViewInfo(entityViewInfo);
							//prmtMaterial.getQueryAgent().resetRuntimeEntityView();
						}
					});
			prmtMaterial.addDataChangeListener(new DataChangeListener() {
				
				public void dataChanged(DataChangeEvent eventObj) {
					if (PublicUtils.isEmpty(txtMile.getText())) {
						MsgBox.showInfo(RepairWOEditUIPIEx.this, "进厂行驶里程不能为空");
						prmtMaterial.setValue(null);
						return;
					}
					
					  if(null != eventObj && null != eventObj.getNewValue())  {
						  Object newObject = prmtMaterial.getValue();
						  MaterialInfo materialInfos[] = null;
						  if(newObject instanceof Object[]) {
							  Object objs[] = (Object[])(Object[])newObject;
							  materialInfos = new MaterialInfo[objs.length];
							  System.arraycopy(((Object) (objs)), 0, materialInfos, 0, objs.length);
		                  } else {
		                   	  return;
		                  }
						  if(materialInfos == null || materialInfos.length <= 0)
					          return;
						  //已全部结算，不允许添加数据
						  if (PublicUtils.equals(gaBillStatus.getSelectedItem(),RepairWOStatusEnum.AllSettle)) {
							  MsgBoxEx.showInfo(RepairWOEditUIPIEx.this,"已全部结算，不允许添加新项目！");
							  prmtMaterial.setValue(null);
							  SysUtil.abort();
						  }
						  
						  KDTable kdTable = kdtRWOItemSpEntry;
						  if(null == kdTable)
					         return;
						  KDTSelectManager selectManager = kdTable.getSelectManager();
				          KDTSelectBlock selectBlock = selectManager.get(0);
				          int beginRowIndex = 0;
				       //   if(selectBlock == null)
				        	  beginRowIndex  = kdTable.getRowCount();
				        //  else beginRowIndex = selectBlock.getBeginRow();
				        //  if (beginRowIndex > 0) 
				        //	  beginRowIndex--;
				          boolean isFirstRowIndex = true;
				          
				          int i = 0;
				          for(int length = materialInfos.length; i < length; i++) {
				              int rowIndex = beginRowIndex + i;
				              MaterialInfo materialInfo = materialInfos[i];
				             /* if(isFirstRowIndex)  {
				            	  if (beginRowIndex==0)
				            		  insertLine(kdTable, rowIndex);
				            	  else if (kdTable.getCell(rowIndex, "material").getValue() != null ||
				            			  kdTable.getCell(rowIndex,"repairItem").getValue() != null)
				            		  insertLine(kdTable, rowIndex);

				               } else {
				                 insertLine(kdTable, rowIndex);
				                 
				               }*/
				              insertLine(kdTable, rowIndex);
				              kdTable.getCell(rowIndex, "material").setValue(materialInfo);
				              kdTable.getCell(rowIndex, "t").setValue(TEnum.P);
				              kdTable.getCell(rowIndex,"itemspNum").setValue(materialInfo.getNumber());
				              kdTable.getCell(rowIndex,"itemspName").setValue(materialInfo.getName());
				              isFirstRowIndex = false;
				              kdTable.getRow(rowIndex).getCell("itemspName").getStyleAttributes().setLocked(!hasPermission_OprtRetailItemspName);
				              String sql = String.format("select isnull(FPrice,0) from T_BD_MaterialSales where FMaterialID='%s' and FOrgUnit='%s'",
				            		  materialInfo.getString("id"),orgUnitInfo.getString("id"));
				              try {
								IRowSet rs = DBUtils.executeQuery(null, sql);
								if (rs != null && rs.next()) {
									kdTable.getCell(rowIndex, "price").setValue(rs.getBigDecimal(1));
									IRow row = kdTable.getRow(rowIndex);
									calItemSpEntryAmount(row);
									if (hasPermission_OprtRetailPrice)
										kdTable.getCell(rowIndex, "price").getStyleAttributes().setLocked(false);
									else kdTable.getCell(rowIndex, "price").getStyleAttributes().setLocked(true);
								}
								
							} catch (Exception e) {
								UIUtils.handUIException(e);
							}
				              
				          }
				          int qtyIndex = kdTable.getColumnIndex("qty");
		            	  kdTable.getEditManager().editCellAt(beginRowIndex, qtyIndex);
					  }	
					  prmtMaterial.setValue(null);	  
				}
			});
		}
	}
	private KDBizPromptBox getMaterialPrmt() {
    	final KDBizPromptBox prmt= new KDBizPromptBox();
		if (orgUnitInfo != null) {
			prmt.setQueryInfo("com.kingdee.eas.basedata.master.material.app.RepairMaterialF7Query");
			String mergeColumnKeys[] = { "number", "name", "model", "helpCode", "baseUnit.name", "baseQty" };
			prmt.setMergeColumnKeys(mergeColumnKeys);
			prmt.setDisplayFormat("$number$");
			prmt.setEditFormat("$number$");
			prmt.setCommitFormat("$number$;$name$;$alias$;$helpCode$");
			prmt.setEnabledMultiSelection(true);
			setMaterialF7Filter();
			prmt.setEntityViewInfo(sparepartEntry_Material_PromptBox.getEntityViewInfo());
			
			prmt.setAutoFocusNextComponent(false);
			prmt.addCommitListener(new CommitListener() {
						public void willCommit(CommitEvent e) {
							FilterInfo orgUnitFilter = new FilterInfo();
							FilterItemCollection filterItemCollection = orgUnitFilter.getFilterItems();
							filterItemCollection.add(new FilterItemInfo("SaleOrgUnit.id", orgUnitInfo.getId()));
							filterItemCollection.add(new FilterItemInfo("status",new Integer(UsedStatusEnum.APPROVED.getValue())));
							FilterInfo vehicleFilterInfo = getVehicleFilterInfo();
							FilterInfo megerFilterInfo = new FilterInfo();
							if (orgUnitFilter != null&& !StringUtils.isEmpty(orgUnitFilter.toString()))
								try {
									megerFilterInfo.mergeFilter(orgUnitFilter,"AND");
								} catch (Exception exc) {
									handUIException(exc);
								}
							if (vehicleFilterInfo != null
									&& !StringUtils.isEmpty(vehicleFilterInfo.toString()))
								try {
									megerFilterInfo.mergeFilter(vehicleFilterInfo, "AND");
								} catch (Exception exc) {
									handUIException(exc);
								}
							EntityViewInfo entityViewInfo = new EntityViewInfo();
							entityViewInfo.setFilter(megerFilterInfo);
							prmt.setEntityViewInfo(entityViewInfo);
							//prmt.getQueryAgent().resetRuntimeEntityView();
						}
					});
			prmt.addDataChangeListener(new DataChangeListener() {

				public void dataChanged(DataChangeEvent eventObj) {
					  if(null != eventObj && null != eventObj.getNewValue())  {
						  Object newObject = prmt.getValue();
						  MaterialInfo materialInfos[] = null;
						  if(newObject instanceof Object[]) {
							  Object objs[] = (Object[])(Object[])newObject;
							  materialInfos = new MaterialInfo[objs.length];
							  System.arraycopy(((Object) (objs)), 0, materialInfos, 0, objs.length);
		                  } else {
		                   	  return;
		                  }
						  if(materialInfos == null || materialInfos.length <= 0)
					          return;
						  KDTable kdTable = kdtRWOItemSpEntry;
						  if(null == kdTable)
					         return;
						  KDTSelectManager selectManager = kdTable.getSelectManager();
				          KDTSelectBlock selectBlock = selectManager.get(0);
				          int beginRowIndex = 0;
				          if(selectBlock == null)
				        	  beginRowIndex  = kdTable.getRowCount()-1;
				          else beginRowIndex = selectBlock.getBeginRow();
				          boolean isFirstRowIndex = true;
				          
				          int i = 0;
				          for(int length = materialInfos.length; i < length; i++) {
				              int rowIndex = beginRowIndex + i;
				              MaterialInfo materialInfo = materialInfos[i];
				              if(isFirstRowIndex)  {
				                  

				               } else {
				                 insertLine(kdTable, rowIndex);
				                 
				               }
				              kdTable.getCell(rowIndex, "material").setValue(materialInfo);
				              kdTable.getCell(rowIndex, "t").setValue(TEnum.P);
				              kdTable.getCell(rowIndex,"itemspNum").setValue(materialInfo.getNumber());
				              kdTable.getCell(rowIndex,"itemspName").setValue(materialInfo.getName());
				              isFirstRowIndex = false;
				          }
					  }	
				}
			});
			return prmt;
		}
		return null;
	}
    
    private void initItemSpMaterialF7(IColumn column,ICell cell) {
    	KDBizPromptBox prmt= getMaterialPrmt();
    	if (prmt != null) {
    		com.kingdee.bos.ctrl.swing.KDPromptBox.DefaultEditor editor = (com.kingdee.bos.ctrl.swing.KDPromptBox.DefaultEditor) prmt.getEditor();
			if (column != null && cell == null)
				column.setEditor(new KDTDefaultCellEditor(prmt));
			else if (column == null && cell != null) 
				cell.setEditor(new KDTDefaultCellEditor(prmt));
			else {
				MsgBoxEx.showInfo("initItemSpMaterialF7参数异常");
				abort();
			}
    	} else {
    		if (column != null) 
				column.getStyleAttributes().setLocked(true);
    	}
    }
    
    @Override
    protected void beforeStoreFields(ActionEvent arg0) throws Exception {
    	
    	//检查拆退
    	checkRepairspCT();
    	//检查配件行，成本价与可用数量
    	checkReparirSpCostAndInvQty();
    	
    	//保存至标准产品分录
    	saveRepairItemAndSpToEntry();
    	
    	//---begin--原4S标准代码，调整，取消对负数控制
    	isNotNull();
    	
		//super.beforeStoreFields(arg0);
    	//--begin 添加抽象类AbstractRepairWOEditUI.beforeStoreFields 
    	
    	//if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtTel.getText())) {
		//	throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"送修人电话"});
		//}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtRepairSender.getText())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"送修人"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(prmtRepairType.getData())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"维修类型"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtMile.getValue())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"进厂行驶里程"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(pkIntendDeliveryTime.getValue())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"预计交车时间"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(pkComeTime.getValue())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"进厂时间"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(prmtSA.getData())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"服务顾问"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(prmtOrgUnit.getData())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"公司"});
		}
		if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtNumber.getText())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"单据编号"});
		}
  
    	//--end 抽象类AbstractRepairWOEditUI.beforeStoreFields
		
		nullEntrys();
		notNullEntrys();
		removeNoUsekdtWarrTotalAmountEntry();
		if (prmtVehicle.getValue() != null && ((VehicleInfo) prmtVehicle.getValue()).getPlateNum() == null && ((VehicleInfo) prmtVehicle.getValue()).getVIN() == null) {
			MsgBox.showInfo("请维护车辆信息，车牌号码和底盘号至少要填写一项！");
			SysUtil.abort();
		}
		if (pkComeTime.getValue() != null && pkIntendDeliveryTime.getValue() != null) {
			Date returnDate = (Date) pkComeTime.getValue();
			Date deliveryDate = (Date) pkIntendDeliveryTime.getValue();
			if (returnDate.after(deliveryDate)) {
				MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NO_LESS_COMETIME"));
				SysUtil.abort();
			}
		}
		int count = kdtRWOSparepartEntry.getRowCount();
		for (int i = 0; i < count; i++) {
			if (UIRuleUtil.getBooleanValue(kdtRWOSparepartEntry.getCell(i,"IsDelete").getValue()))
				continue;
			if (UIRuleUtil.getBigDecimal(kdtRWOSparepartEntry.getCell(i, Cell_Qty).getValue()).compareTo(BigDecimal.valueOf(0L)) == -1) {
			//	MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_QTYLESSTHANZERO"));
			//	SysUtil.abort();
			//	continue;
			}
			if (UIRuleUtil.getBigDecimal(kdtRWOSparepartEntry.getCell(i, Cell_TaxRate).getValue()).compareTo(BigDecimal.valueOf(0L)) == -1) {
				MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_TAXRATELESSTHANZERO"));
				SysUtil.abort();
				continue;
			}
			if (UIRuleUtil.getBigDecimal(kdtRWOSparepartEntry.getCell(i, Cell_TaxPrice).getValue()).compareTo(BigDecimal.valueOf(0L)) == 0
					|| UIRuleUtil.getBigDecimal(kdtRWOSparepartEntry.getCell(i, Cell_TaxPrice).getValue()).compareTo(BigDecimal.valueOf(0L)) == -1) {
			//	MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_TAXPRICELESSTHANZERO"));
			//	SysUtil.abort();
			//	continue;
			}
			if (UIRuleUtil.getBigDecimal(kdtRWOSparepartEntry.getCell(i, Cell_NoIssueQty).getValue()).compareTo(BigDecimal.valueOf(0L)) == -1) {
				//MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_MINUS"));
				//SysUtil.abort();
				//continue;
			}
			if (UIRuleUtil.getBigDecimal(kdtRWOSparepartEntry.getCell(i, Cell_TaxPrice).getValue()).compareTo(BIGDEC0) <= 0) {
			//	MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_TAXPRICELESSTHANZERO"));
			//	SysUtil.abort();
			}
		}
		int attachmentCount = kdtRWOAttachmentItemEntry.getRowCount();
		for (int i = 0; i < attachmentCount; i++)
			if (UIRuleUtil.getBigDecimal(kdtRWOAttachmentItemEntry.getCell(i, Cell_DiscountRate).getValue()).compareTo(BigDecimal.valueOf(100L)) == 1) {
				MsgBox.showInfo("附加项目折扣率不能大于100！");
				SysUtil.abort();
			}
		int item = kdtRWORepairItemEntry.getRowCount();
		int sp = kdtRWOSparepartEntry.getRowCount();
		if (kdtRWORepairItemEntry.getRowCount() > 0) {
			for (int i = 0; i < kdtRWORepairItemEntry.getRowCount(); i++) {
				IRow row = kdtRWORepairItemEntry.getRow(i);
				if (kdtRWORepairItemEntry.getCell(i, "IsReturnRepair").getValue().equals(Boolean.TRUE)) {
					kdtRWORepairItemEntry.getCell(i, "ReworkReason").getStyleAttributes().setLocked(false);
					kdtRWORepairItemEntry.getCell(i, "ReworkReason").getStyleAttributes().setBackground(YELLOW);
					if (row.getCell("ReworkReason").getValue() == null)
						throw new EASBizException(EASBizException.CHECKBLANK,
								new Object[] {kdtRWORepairItemEntry.getHead().getRow(0).getCell(row.getCell("ReworkReason").getColumnIndex()).getValue() });
				} else {
					kdtRWORepairItemEntry.getCell(i, "ReworkReason").getStyleAttributes().setLocked(true);
				}
			}
		}
		for (int i = 0; i < item; i++) {
			IRow row = kdtRWORepairItemEntry.getRow(i);
			if (row.getCell("RepairItem").getValue() == null)
				throw new EASBizException(EASBizException.CHECKBLANK,new Object[] { kdtRWORepairItemEntry.getHead().getRow(0).getCell(row.getCell("RepairItem").getColumnIndex()).getValue() });
			if (row.getCell("PaymentClassify").getValue() == null)
				throw new EASBizException(EASBizException.CHECKBLANK,new Object[] { kdtRWORepairItemEntry.getHead().getRow(0).getCell(row.getCell("PaymentClassify").getColumnIndex()).getValue() });
			if (row.getCell("SettleObject").getValue() == null)
				throw new EASBizException(EASBizException.CHECKBLANK,new Object[] { kdtRWORepairItemEntry.getHead().getRow(0).getCell(row.getCell("SettleObject").getColumnIndex()).getValue() });
			if (row.getCell("RepairClassify").getValue() == null)
				throw new EASBizException(EASBizException.CHECKBLANK,new Object[] { kdtRWORepairItemEntry.getHead().getRow(0).getCell(row.getCell("RepairClassify").getColumnIndex()).getValue() });
			if (!paramValue_RSM012 && UIRuleUtil.getBigDecimal(kdtRWORepairItemEntry.getCell(i, Cell_DiscountRate).getValue()).compareTo(BigDecimal.valueOf(100L)) == 1) {
				MsgBox.showInfo("维修项目折扣率不能大于100！");
				SysUtil.abort();
			}
			if ("保险".equals(row.getCell("PaymentClassify").getValue().toString())&& prmtInsuranCompany.getValue() == null && isEnableInsuranCompany) {
				MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_INSURE"));
				prmtInsuranCompany.setEnabled(true);
				SysUtil.abort();
			}
			if ("保修".equals(row.getCell("PaymentClassify").getValue().toString())&& prmtWarrantyType.getValue() == null) {
				MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_WARRANTY"));
				prmtWarrantyType.setEnabled(true);
				SysUtil.abort();
			}
		}
		if (!paramValue_RSM012) {
		/*	for (int i = 0; i < item; i++) {
				IRow row = kdtRWORepairItemEntry.getRow(i);
				if (!UIRuleUtil.getBooleanValue(kdtRWORepairItemEntry.getCell(i, "IsDelete").getValue())&& UIRuleUtil.getBigDecimal(row.getCell(Cell_ARAmount).getValue()).compareTo(BIGDEC0) == -1) {
					MsgBox.showInfo((new StringBuilder()).append("维修项目分录第 ").append(i + 1).append(" 行应收金额只能大于等于0").toString());
					SysUtil.abort();
				}
			}*/
		}
		for (int i = 0; i < sp; i++) {
			IRow row = kdtRWOSparepartEntry.getRow(i);
			if (UIRuleUtil.getBooleanValue(row.getCell("IsDelete").getValue()))
				continue;
			if (row.getCell("Material").getValue() == null)
				throw new EASBizException(EASBizException.CHECKBLANK,new Object[] { kdtRWOSparepartEntry.getHead().getRow(0).getCell(row.getCell("Material").getColumnIndex()).getValue() });
			if (row.getCell("PaymentClassify").getValue() == null)
				throw new EASBizException(EASBizException.CHECKBLANK,new Object[] { kdtRWOSparepartEntry.getHead().getRow(0).getCell(row.getCell("PaymentClassify").getColumnIndex()).getValue() });
			if (row.getCell("SettleObject").getValue() == null)
				throw new EASBizException(EASBizException.CHECKBLANK,new Object[] { kdtRWOSparepartEntry.getHead().getRow(0).getCell(row.getCell("SettleObject").getColumnIndex()).getValue() });
			if (row.getCell(Cell_Qty).getValue() == null)
				throw new EASBizException(EASBizException.CHECKBLANK,new Object[] { kdtRWOSparepartEntry.getHead().getRow(0).getCell(row.getCell(Cell_Qty).getColumnIndex()).getValue() });
			if (row.getCell(Cell_Unit).getValue() == null)
				throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {kdtRWOSparepartEntry.getHead().getRow(0).getCell(row.getCell(Cell_Unit).getColumnIndex()).getValue() });
			if ("保险".equals(row.getCell("PaymentClassify").getValue().toString()) && prmtInsuranCompany.getValue() == null
					&& isEnableInsuranCompany) {
				MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_INSURE"));
				prmtInsuranCompany.setEnabled(true);
				SysUtil.abort();
			}
			if ("保修".equals(row.getCell("PaymentClassify").getValue().toString())&& prmtWarrantyType.getValue() == null) {
				MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_WARRANTY"));
				prmtWarrantyType.setEnabled(true);
				SysUtil.abort();
			}
		}
		checkReWrite();
		mile();
		totalAmount();
		
		//---end--原4S标准代码，调整，取消对负数控制
		
		//saveVehicleMile();
		//设置GA单据状态
		HashMap<String,String> hashSettleFlag = new HashMap<String, String>();
		for (int i = 0; i < kdtRWOItemSpEntry.getRowCount(); i++) {
			IRow row = kdtRWOItemSpEntry.getRow(i);
			IEnum iType = (IEnum) row.getCell("i").getValue();
			hashSettleFlag.put(iType.getValue(),iType.getValue());
			
		}
		if (hashSettleFlag.get(IEnum.X_VALUE) != null && hashSettleFlag.get(IEnum.I_VALUE) != null) { //部分结算
			gaBillStatus.setSelectedItem(RepairWOStatusEnum.partSettle);
		} else if (hashSettleFlag.get(IEnum.X_VALUE) != null && hashSettleFlag.get(IEnum.I_VALUE) == null) { //全部结算
			gaBillStatus.setSelectedItem(RepairWOStatusEnum.AllSettle);
		} else if (hashSettleFlag.get(IEnum.X_VALUE) == null && hashSettleFlag.get(IEnum.I_VALUE) != null) { //未结算
			gaBillStatus.setSelectedItem(RepairWOStatusEnum.notSettle);
		}
    }
    private void checkRepairspCT() throws Exception {
    	StringBuilder msg = new StringBuilder();
    	for (int i = 0; i < kdtRWOItemSpEntry.getRowCount(); i++) {
    		IRow row = kdtRWOItemSpEntry.getRow(i);
    		TEnum tType = (TEnum) row.getCell("t").getValue();
    		if (!PublicUtils.equals(TEnum.P, tType)) continue;
    		boolean isCT = (Boolean) row.getCell("isCT").getValue();
    		if (!isCT) continue;
    		BigDecimal qty = PublicUtils.getBigDecimal(row.getCell("qty").getValue());
    		String spName = (String) row.getCell("itemspName").getValue();
    		if (qty.compareTo(BIGDEC0)>=0) {
    			msg.append(String.format("第%d行，[%s]拆退数量不能大于0", i+1,spName)).append(CR);
    		}	
    	}
    	if (msg.length() > 0) {
    		MsgBoxEx.showDetailAndOK(this, "拆退数量异常", msg.toString(), MsgBox.YES);
    		abort();
    	}
    }
    
    private void checkReparirSpCostAndInvQty() throws Exception {
    	if (hasPermission_OprtRetailBelowCost) return;
    	String orgId = orgUnitInfo.getString("id");
    	StringBuilder msg = new StringBuilder();
    	for (int i = 0; i < kdtRWOItemSpEntry.getRowCount(); i++) {
    		IRow row = kdtRWOItemSpEntry.getRow(i);
    		TEnum tType = (TEnum) row.getCell("t").getValue();
    		if (!PublicUtils.equals(TEnum.P, tType)) continue;
    		MaterialInfo materialInfo = (MaterialInfo) row.getCell("material").getValue(); 
    		BigDecimal price = PublicUtils.getBigDecimal(row.getCell("taxPrice").getValue()); //含税
    		BigDecimal discountRate = PublicUtils.getBigDecimal(row.getCell("discountRate").getValue());
    		BigDecimal factPrice = price.multiply(BIGDEC1.subtract(discountRate.divide(BIGDEC100,10,BigDecimal.ROUND_HALF_UP)));
    		BigDecimal costPrice =  getMaterialCostPrice(orgId, materialInfo);
    		String spName = (String) row.getCell("itemspName").getValue();
    		if (factPrice.compareTo(costPrice)<0) {
    			msg.append(String.format("第%d行，[%s]折后价格不能低于成本价", i+1,spName)).append(CR);
    		}	
    	}
    	if (msg.length() > 0) {
    		MsgBoxEx.showDetailAndOK(this, "折后价低于成本价", msg.toString(), MsgBox.YES);
    		abort();
    	}
    	
		
	}
	@Override
    public SelectorItemCollection getSelectors() {
    	SelectorItemCollection sic = super.getSelectors();
    	sic.add(new SelectorItemInfo("lastUpdateTime"));
    	return sic;
    	
    }
   
    /**
     * 把[项目/配件明细]分别保存更新到相对应的分录里
     * @throws Exception 
     * @throws  
     */
    private void saveRepairItemAndSpToEntry() throws Exception {

    	HashMap<String,IRow> hashRepairItem = new HashMap<String, IRow>();
    	HashMap<String,IRow> hashSp = new HashMap<String, IRow>();
    	for (int i = 0; i < kdtRWORepairItemEntry.getRowCount(); i++) {
    		IRow row = kdtRWORepairItemEntry.getRow(i);
    		Object obj = row.getCell("itemSpEntryId").getValue();
    		if (obj == null) continue;
    		String id = obj.toString();
    		hashRepairItem.put(id, row);
    	}
    	for (int i = 0; i < kdtRWOSparepartEntry.getRowCount(); i++) {
    		IRow row = kdtRWOSparepartEntry.getRow(i);
    		Object obj = row.getCell("itemSpEntryId").getValue();
    		if (obj == null) continue;
    		String id = obj.toString();
    		hashSp.put(id, row);
    	}
    	
    	for (int i = 0; i < kdtRWOItemSpEntry.getRowCount(); i++) {
    		IRow row = kdtRWOItemSpEntry.getRow(i);
    		String tType = row.getCell("t").getValue().toString();
    		BigDecimal qty = (BigDecimal) row.getCell("qty").getValue(); //数量/工时
    		BigDecimal price = (BigDecimal)row.getCell("price").getValue(); //不含单价
    		BigDecimal discountRate = (BigDecimal)row.getCell("discountRate").getValue(); //折扣
    		BigDecimal taxRate = (BigDecimal)row.getCell("taxRate").getValue(); //税率
    		BigDecimal taxPrice = price.multiply(BIGDEC1.add(taxRate.divide(BIGDEC100,10,BigDecimal.ROUND_HALF_UP))); //含税单价
    		
    		RepairWORWOItemSpEntryInfo itemSpEntryInfo = ((RepairWORWOItemSpEntryInfo)row.getUserObject());
    		String itemSpEntryId = itemSpEntryInfo.getString("id");
    		boolean isChangeRepairItem = false; //是否维修项目变更
    		if ("L".equals(tType)) { //项目
    			RepairItemInfo repairItemInfo = (RepairItemInfo) row.getCell("repairItem").getValue();
    			IRow rowRepairItem = hashRepairItem.get(itemSpEntryId);
    			if (rowRepairItem == null) {
    				addLine(kdtRWORepairItemEntry);
    				rowRepairItem = kdtRWORepairItemEntry.getRow(kdtRWORepairItemEntry.getRowCount()-1);
    				hashRepairItem.put(itemSpEntryId, rowRepairItem);
    				isChangeRepairItem = true;
    				rowRepairItem.getCell("itemSpEntryId").setValue(itemSpEntryId);
    			} else {
    				RepairItemInfo oldRepairItemInfo = (RepairItemInfo) rowRepairItem.getCell("RepairItem").getValue();
    				if (!repairItemInfo.equals(oldRepairItemInfo)) {
    					isChangeRepairItem = true;
    				}
    			}
    			if (isChangeRepairItem) { //标准工时等，重新付值 
    				
    				rowRepairItem.getCell("RepairItem").setValue(repairItemInfo);
    				rowRepairItem.getCell("RepairItemName").setValue(repairItemInfo.getName());
    				rowRepairItem.getCell("ItemRemark").setValue(repairItemInfo.getName());
    			}
				rowRepairItem.getCell("StdWorkTime").setValue(qty);
				rowRepairItem.getCell("AssignWorkTime").setValue(qty);
				rowRepairItem.getCell(Cell_ActualWorkTime).setValue(qty);
				rowRepairItem.getCell(Cell_WorkTimePrice).setValue(taxPrice);
				rowRepairItem.getCell(Cell_WorkTimeStdPrice).setValue(taxPrice);
				rowRepairItem.getCell(Cell_DiscountRate).setValue(discountRate);
				
											
				//调用标准产品-更新计算金额
				rowRepairItem.getCell("SettleObject").setValue(null); //需先将结算对象置空，否则kdtWorkTimeEntrycount，会根据结算对象重取工时单价 
				kdtWorkTimeEntrycount(rowRepairItem.getRowIndex());
				rowRepairItem.getCell("SettleObject").setValue(row.getCell("settlementObject").getValue());
				
				rowRepairItem.getCell("RepairClassify").setValue(defaultRepairClassifyInfo);
    		} else if ("P".equals(tType)) { //配件
    			
      			MaterialInfo materialInfo = (MaterialInfo) row.getCell("material").getValue();
    			IRow rowSp = hashSp.get(itemSpEntryId);
    			if (rowSp == null) {
    				addLine(kdtRWOSparepartEntry);
    				rowSp = kdtRWOSparepartEntry.getRow(kdtRWOSparepartEntry.getRowCount()-1);
    				hashSp.put(itemSpEntryId, rowSp);
    				isChangeRepairItem = true;
    				rowSp.getCell("itemSpEntryId").setValue(itemSpEntryId);
    			} else {
    				MaterialInfo oldMaterialInfo = (MaterialInfo) rowSp.getCell("Material").getValue();
    				if (!materialInfo.equals(oldMaterialInfo)) {
    					isChangeRepairItem = true;
    				}
    			}
    			if (isChangeRepairItem) { //标准工时等，重新付值 
    				rowSp.getCell("Material").setValue(materialInfo);
    				rowSp.getCell("MaterialName").setValue(materialInfo.getName());
    				rowSp.getCell("model").setValue(materialInfo.getModel());
    				
    				//设置物料，并带出物料相关字段，计量单位等，物料需先设置
    				int materialColIndex = kdtRWOSparepartEntry.getColumnIndex("Material");
    				priceMaterial(rowSp.getRowIndex(),materialColIndex);
    			}
    			
    						
				//调用标准产品-更新计算金额
    			
				
				rowSp.getCell("Qty").setValue(qty); //数量
				rowSp.getCell(Cell_BaseQty).setValue(qty); //基本数量
				rowSp.getCell(Cell_TaxPrice).setValue(taxPrice); //不含税单价
				rowSp.getCell("TaxRate").setValue(taxRate); //税率
				rowSp.getCell("isCT").setValue(row.getCell("isCT").getValue()); //是否拆分
				rowSp.getCell("DiscountRate").setValue(discountRate); //折扣率
				rowSp.getCell("RepairClassify").setValue(defaultRepairClassifyInfo);
				
				BigDecimal issueQty = UIRuleUtil.getBigDecimal(rowSp.getCell(Cell_IssueQty).getValue());
				rowSp.getCell(Cell_NoIssueQty).setValue(qty.subtract(issueQty));
				rowSp.getCell("SettleObject").setValue(row.getCell("settlementObject").getValue());
				kdtSPEntrycount(rowSp.getRowIndex());
				
    		}
    		
    	}
    }
    
    public void initItemSpRepairItemEntryF7(IColumn column) {
    	initItemSpRepairItemEntryF7(column,null);
    }
    public void initItemSpRepairItemEntryF7(ICell cell) {
    	initItemSpRepairItemEntryF7(null,cell);
    }
    
    private void calItemSpEntryAmount(IRow row) throws Exception {
    	//总计=数量*(1+税率%/100)*价格*(1-折扣%/100)
    	TEnum tType = (TEnum)row.getCell("t").getValue(); //T
    	BigDecimal qty = PublicUtils.getBigDecimal(row.getCell("qty").getValue()); //数量/工时
		BigDecimal price = PublicUtils.getBigDecimal(row.getCell("price").getValue()); //不含单价
		BigDecimal discountRate = PublicUtils.getBigDecimal(row.getCell("discountRate").getValue()); //折扣
		BigDecimal taxRate = PublicUtils.getBigDecimal(row.getCell("taxRate").getValue()); //税率
		BigDecimal amount = qty.multiply(price).multiply(BIGDEC1.subtract(discountRate.divide(BIGDEC100,10,BigDecimal.ROUND_HALF_UP)));
		BigDecimal taxPrice = price.multiply(BIGDEC1.add(taxRate.divide(BIGDEC100,10,BigDecimal.ROUND_HALF_UP))); //含税单价
		BigDecimal taxAmount =  qty.multiply(taxPrice).multiply(BIGDEC1.subtract(discountRate.divide(BIGDEC100,10,BigDecimal.ROUND_HALF_UP)));//含税总计
		
		row.getCell("amount").setValue(amount);
		row.getCell("taxPrice").setValue(taxPrice);
		row.getCell("taxAmount").setValue(taxAmount);
		
		if (BIGDEC0.compareTo(amount) == 0) row.getCell("i").setValue(IEnum.X);
		else row.getCell("i").setValue(IEnum.I);
		if (PublicUtils.equals(TEnum.L, tType)) { //项目行
			row.getCell("issueQty").setValue(null);
			row.getCell("unIssueQty").setValue(null);
		} else if (PublicUtils.equals(TEnum.P, tType)) { //零件
			BigDecimal issueQty = (BigDecimal) row.getCell("issueQty").getValue(); //已出库数
			row.getCell("unIssueQty").setValue(qty.subtract(issueQty));
		}
		appendItemSpFoot();
    }
    
    private void initItemSpRepairItemEntryF7(IColumn column,ICell cell) {
    	KDBizPromptBox prmt= getRepairItemPrmt();
		KDTDefaultCellEditor repairItemDefaultCellEditor = new KDTDefaultCellEditor(prmt);
		ObjectValueRender kdtRWORepairItemEntry_RepairItem_OVR = new ObjectValueRender();
		kdtRWORepairItemEntry_RepairItem_OVR.setFormat(new BizDataFormat("$number$"));
		if (cell == null || column != null) {
			column.setRenderer(kdtRWORepairItemEntry_RepairItem_OVR);
			column.setEditor(repairItemDefaultCellEditor);
		} else if (cell != null && column ==  null) {
			cell.setRenderer(kdtRWORepairItemEntry_RepairItem_OVR);
			cell.setEditor(repairItemDefaultCellEditor);
		} else {
			MsgBoxEx.showInfo("initItemSpRepairItemEntryF7参数异常");
			abort();
		}

	}
    
    @Override
    public void isNotNull() {
    	super.isNotNull();
    	if(prmtCustomerAccount.getValue() == null) {
		   MsgBox.showInfo("账户代码不能为空！");
		   prmtCustomerAccount.requestFocus();
		   SysUtil.abort();
        }
    }
    /*
     * 取消息重复行检查
     * @see com.kingdee.eas.auto4s.rsm.rs.client.RepairWOEditUI#checkRepeat()
     */
    @Override
    protected boolean checkRepeat() {
    	return false;
    }
    
    /**
     * 取消息重复行检查
     */
    @Override
    protected void checkRepeatEntry() {
    	//super.checkRepeatEntry();
    }
    /**
     * 取消息重复行检查
     */
    @Override
    protected boolean checkRepeatItem(KDTable table, String msID,
    		String columnId, String cmpID) {
    	//return super.checkRepeatItem(table, msID, columnId, cmpID);
    	return true;
    }
    
    @Override
    public void initEntryFormat() {

    	super.initEntryFormat();
    	//--重新设置数量字段，支持负数
    	UIUtils.formatDecimal(kdtRWORepairItemEntry, "StdWorkTime",true);
        UIUtils.formatDecimal(kdtRWORepairItemEntry, "WorkTimePrice",true);
        UIUtils.formatDecimal(kdtRWORepairItemEntry, "WorkTimeStdPrice",true);
        UIUtils.formatDecimal(kdtRWORepairItemEntry, Cell_WorkTimeAmount,true);
        UIUtils.formatDecimal(kdtRWORepairItemEntry, "AssignWorkTime",true);
        UIUtils.formatDecimal(kdtRWORepairItemEntry, "WagePrice",true);
        UIUtils.formatDecimal(kdtRWORepairItemEntry, "WorkTimeCost",true);
        UIUtils.formatDecimal(kdtRWORepairItemEntry, Cell_ActualWorkTime,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_Qty,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_BaseQty,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_NoIssueQty,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_TaxPrice,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_ARAmount,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_TaxRate,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_Tax,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_NoTaxPrice,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_TaxAmount,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_NoTaxAmount,true);
        UIUtils.formatDecimal(kdtRWOSparepartEntry, Cell_IssueQty,true);
        UIUtils.formatDecimal(kdtRWOAttachmentItemEntry, "AttaItemAmount",true);
        UIUtils.formatDecimal(kdtRWOAttachmentItemEntry, Cell_ARAmount,true);
        UIUtils.formatDecimal(kdtRWOAttachmentItemEntry, Cell_DiscountAmount,true);
        UIUtils.formatDecimal(kdtRWOAttachmentItemEntry, "Cost",true);
        
        //ItemSPEntry
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "qty",true);
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "price",true);
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "taxPrice",true);
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "amount",true);
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "taxAmount",true);
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "discountRate",false);
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "taxRate",false);
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "issueQty",true);
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "unIssueQty",true);
        
        
    }

    @Override 
    public void setLoadField() {
    	VehicleInfo vehicleInfo = (VehicleInfo)prmtVehicle.getValue();
    	if (isFirstGetInsFromVehicle && vehicleInfo != null) {
    		
    		StringBuilder sql = new StringBuilder();
    		sql.append("select b.FID BrandID,b.FName_l2 BrandName,b.FNumber BrandNumber,")
    		.append("c.FName_l2 CustomerName,c.FAddress CustomerAddr,c.FPhone CustomerPhone ")
    		.append("from T_ATS_Vehicle a ")
    		.append("left join T_ATS_Brand b on a.FBrandID=b.FID ")
    		.append("left join T_ATS_Customer c on c.Fid=a.FCustomerID ")
    		.append(String.format("where a.FID='%s'", vehicleInfo.getString("id")));
    		
    		try {
    			IRowSet rs = DBUtils.executeQuery(null, sql.toString());
    			if (rs != null && rs.next()) {
    				BrandInfo brandInfo = new BrandInfo();
    				brandInfo.put("id", rs.getString("BrandID"));
    				brandInfo.put("name", rs.getString("BrandName"));
    				brandInfo.put("number", rs.getString("BrandNumber"));
    				prmtBrand.setValue(brandInfo);
    				String strCustomerInfo = rs.getString("CustomerName") + " " + rs.getString("CustomerPhone")
    					+ CR + rs.getString("CustomerAddr");
    				///txtcustomInfo.setText(strCustomerInfo);
    				//按品牌获取默认维修类型与维修种类
    				defaultRepairTypeInfo = GAUtils.getDefaultRepairType(null,brandInfo);
    				prmtRepairType.setValue(defaultRepairTypeInfo);
    				
 
    				//按品牌带出默认维修项目TXT && 维修项目-代金券
    				defaultRepairItemForTXT = GAUtils.getDefaultRepairItemForTXT(null, brandInfo);
    				defaultRepairItemForDJQ = GAUtils.getDefaultRepairItemForDJQ(null, brandInfo);
    				
    			}
    		} catch (Exception e) {
    			UIUtils.handUIExceptionAndAbort(e);
    		}
    	
    	}
    	
    	super.setLoadField();
    }
    
    protected void mile() {
		storeFields();
		if (prmtVehicle.getValue() == null) {
			MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","VEHICLE_NOT_NULL"));
			SysUtil.abort();
		} else if (pkComeTime.getValue() == null) {
			MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_COMETIME"));
			SysUtil.abort();
		} else if (txtMile.getValue() == null) {
			MsgBox.showInfo(EASResource.getString("com.kingdee.eas.auto4s.rsm.rs.client.RsResource","NOT_MILE"));
			SysUtil.abort();
		}
		RepairWOInfo info = editData;
		if (info.getVehicle() != null) {
			EntityViewInfo viewInfo = new EntityViewInfo();
			FilterInfo filterInfo = new FilterInfo();
			filterInfo.getFilterItems().add(new FilterItemInfo("Vehicle", info.getVehicle().getId().toString()));
			Date comeTime = new Date();
			if (pkComeTime.getValue() != null)
				comeTime = (Date) pkComeTime.getValue();
			filterInfo.getFilterItems().add(new FilterItemInfo("ComeTime", comeTime, CompareType.LESS));
			filterInfo.getFilterItems().add(new FilterItemInfo("status", RepairBillStatusEnum.ABOLISH,CompareType.NOTEQUALS));
			if (null != info.getId())
				filterInfo.getFilterItems().add(new FilterItemInfo("id", info.getId().toString(),CompareType.NOTEQUALS));
			viewInfo.setFilter(filterInfo);
			viewInfo.getSelector().add(new SelectorItemInfo("id"));
			viewInfo.getSelector().add(new SelectorItemInfo("Mile"));
			viewInfo.getSorter().add(new SorterItemInfo("ComeTime"));
			SorterItemInfo sortItem = viewInfo.getSorter().get(0);
			sortItem.setSortType(SortType.DESCEND);
			RepairWOCollection repairWO = null;
			try {
				repairWO = iRepairWo.getRepairWOCollection(viewInfo);
			} catch (BOSException e) {
				handUIException(e);
			}
			if (txtMile.getValue() != null) {
				double tmpDouble;
				try {
					tmpDouble = info.getMile().doubleValue();
				} catch (Exception ex) {
					MsgBox.showInfo("进厂行驶里程应输入数字！");
					SysUtil.abort();
				}
				if (info.getMile().compareTo(BigDecimal.valueOf(0L)) <= 0) {
					MsgBox.showInfo("进厂行驶里程只能为正数");
					SysUtil.abort();
				}
				if (repairWO != null && repairWO.size() != 0
						&& repairWO.get(0).getMile() != null
						&& UIRuleUtil.getBigDecimal(info.getMile()).compareTo(UIRuleUtil.getBigDecimal(repairWO.get(0).getMile())) <= 0 && isVerifyMile) {
					int status = MsgBox.showConfirm2("进厂行驶里程小于或等于上次进厂行驶里程,是否继续?");
					if (2 == status)
						SysUtil.abort();
					isVerifyMile = false;
				}
			} else {
				MsgBox.showInfo("进厂行驶里程为空");
				SysUtil.abort();
			}
		} else {
			MsgBox.showInfo("请您填写完成车辆的相关信息");
			SysUtil.abort();
		}
	}
    
    @Override
    public void actionIsShowStdItemspEntry_actionPerformed(ActionEvent e)
    		throws Exception {
    	if (menuItemIsShowStdItemspEntry.isSelected()) {
    		kDTRWOPane.insertTab(resHelper.getString("kDPRwoItem.constraints"), null, kDPRwoItem, resHelper.getString("kDPRwoItem.constraints"), 1);
    		kDTRWOPane.insertTab(resHelper.getString("kDPRwoSp.constraints"), null, kDPRwoSp, resHelper.getString("kDPRwoSp.constraints"), 2);
    	} else {
    		kDTRWOPane.remove(kDPRwoItem);
    		kDTRWOPane.remove(kDPRwoSp);
    	}
    }
    
    @Override
    public void actionAudit_actionPerformed(ActionEvent e) throws Exception {
    	super.actionAudit_actionPerformed(e);
    }
    
    @Override
    public void actionUnAudit_actionPerformed(ActionEvent e) throws Exception {

    	super.actionUnAudit_actionPerformed(e);
    }
    
    public void saveVehicleMile() throws Exception {
    	if (PublicUtils.isEmpty(editData.getString("id"))) return;
    	
    	IWarrRemind warrRemind = WarrRemindFactory.getRemoteInstance();
    	WarrRemindInfo warrRemindInfo = null;
    	if (vehicleInfo == null) return;
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    	Date dateComeTime = (Date) pkComeTime.getValue();
    	dateComeTime.setSeconds(1);
  
    	BigDecimal mile = txtMile.getBigDecimalValue();
    	
    	
    	try {
    		String sql = "";
    		sql = String.format("delete T_ATS_WarrRemind where FRepairWOID='%s'", editData.getString("id"));
    		DBUtils.execute(null, sql);
  
        	sql = String.format("select FID from T_ATS_WarrRemind WHERE FVehicleID='%s' and convert(varchar(10),FReturnTime,120)='%s' order by FReturnTime desc",
        			vehicleInfo.getString("id"),sf.format(dateComeTime));
        	
    		IRowSet rs = DBUtils.executeQueryForDialect(null, sql);
    		if (rs != null && rs.next()) {
    			warrRemindInfo = warrRemind.getWarrRemindInfo(new ObjectUuidPK(rs.getString(1)));
    		} else {
    			warrRemindInfo = new WarrRemindInfo();
    		}
    		
    		
    	} catch (Exception e) {
    		UIUtils.handUIException(e);
    		
    	}
    	warrRemindInfo.setBrand((BrandInfo) prmtBrand.getValue());
    	warrRemindInfo.setOrgUnit(getServiceOrgUnitInfo());
    	warrRemindInfo.setRepairWO(editData);
    	warrRemindInfo.setVehicle(vehicleInfo);
    	warrRemindInfo.setReturnTime(dateComeTime);
    	warrRemindInfo.setReturnMile(mile);
    	
    	warrRemind.save(warrRemindInfo);
    }
    
    @Override
    public void actionSave_actionPerformed(ActionEvent e) throws Exception {
    	checkDataChange();
    	super.actionSave_actionPerformed(e);
    	saveVehicleMile();
    //	saveRepairSender();
    	saveRemark();
    }
    
    private void saveRemark() throws Exception {
    	if (PublicUtils.isEmpty(editData.getString("id"))) return;
    	if (PublicUtils.isEmpty(txtRemark.getText().trim())) return;
    	IVehicleRepairRemark repairRemark = VehicleRepairRemarkFactory.getRemoteInstance();
    	VehicleRepairRemarkInfo repairRemarkInfo = null;
    	try {
    		repairRemarkInfo = repairRemark.getVehicleRepairRemarkInfo(String.format("where repairwo.id='%s'",editData.getString("id")));
    	} catch (Exception e) {
    		repairRemarkInfo = new VehicleRepairRemarkInfo();
    	}
    	repairRemarkInfo.setRemark(txtRemark.getText());
    	repairRemarkInfo.setCreateTime(DBUtils.getAppServerTime(null));
    	repairRemarkInfo.setRepairWO(editData);
    	repairRemarkInfo.setParent(vehicleInfo);
    	repairRemark.save(repairRemarkInfo);
    }
  /*  private void saveRepairSender() throws Exception {
    	if (PublicUtils.isEmpty(editData.getString("id"))) return;
    	IContactPerson contactPerson = ContactPersonFactory.getRemoteInstance();
		String repairSender = txtRepairSender.getText();
		String repairTel = txtTel.getText();
		ContactPersonInfo cPersonInfo = new ContactPersonInfo(); 
		try {
			cPersonInfo = contactPerson.getContactPersonInfo(String.format("where name='%s'",repairSender.trim()));
		} catch (Exception e) {
			cPersonInfo = new ContactPersonInfo();
		}
		cPersonInfo.setName(repairSender);
		cPersonInfo.setContactMobile(repairTel);
		contactPerson.save(cPersonInfo);
    	
    	
	}
	*/
	@Override
    public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
    	checkDataChange();
    	super.actionEdit_actionPerformed(e);
    }
    
    private void checkDataChange() throws Exception {
    	//**检查 是否数据已修改
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if (editData != null && !PublicUtils.isEmpty(editData.getString("id"))) {
    		String sql = String.format("select FLastUpdateTime from T_ATS_RepairWO where fid='%s'", editData.getString("id"));
    		IRowSet rs = DBUtils.executeQuery(null, sql);
    		if (rs != null && rs.next()) {
    			Date newLastUpdateTime = rs.getDate(1);
    			Date oldLastUpdateTime = editData.getLastUpdateTime();
    			String newDate = sf.format(newLastUpdateTime);
    			String oldDate = sf.format(oldLastUpdateTime);
    			if (!PublicUtils.equals(newDate, oldDate)) {
    				throw new EASBizException(new NumericExceptionSubItem("","数据已变化，请刷新后再修改保存"));
    			}
    		}
    	}
    	
    }
  
    
    @Override
    protected void btnShowRepairSender_actionPerformed(ActionEvent e)
    		throws Exception {
    	Map uictx = new HashMap();
		uictx.put("vehicleInfo", vehicleInfo);
		//uictx.put("curRepairSenderInfo", prmtrepairMan.getValue());
		IUIFactory iUIFactory = UIFactory.createUIFactory(UIFactoryName.MODEL);
		IUIWindow iUIWindowSizesHEdit = iUIFactory.create(RepairSenderF7UI.class.getName(),uictx); // 获取FeedRecListUI的IUIWindow
		RepairSenderF7UI f7 = (RepairSenderF7UI) iUIWindowSizesHEdit.getUIObject();
		iUIWindowSizesHEdit.show();
		VehicleRepairSenderInfo repairSenderInfo =  (VehicleRepairSenderInfo) f7.getData();
		if (repairSenderInfo != null && txtRepairSender.isEditable()) {
			txtRepairSender.setText(repairSenderInfo.getName());
			txtTel.setText(repairSenderInfo.getTel());
			
			String strCustomerInfo = StringUtils.cnulls(repairSenderInfo.getName()) + " " +  StringUtils.cnulls(repairSenderInfo.getTel())
			+ CR +  StringUtils.cnulls(repairSenderInfo.getAddr());
			txtcustomInfo.setText(strCustomerInfo);
		}
		
    }
    
    
}
