package com.kingdee.eas.auto4s.rsm.rs.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditListener;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectEvent;
import com.kingdee.bos.ctrl.kdf.table.event.KDTSelectListener;
import com.kingdee.bos.ctrl.kdf.table.foot.KDTFootManager;
import com.kingdee.bos.ctrl.kdf.util.render.ObjectValueRender;
import com.kingdee.bos.ctrl.kdf.util.style.Styles.HorizontalAlignment;
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
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.entity.SorterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.auto4s.bdm.pbd.BrandInfo;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
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
import com.kingdee.eas.auto4s.rsm.rs.RepairWOCollection;
import com.kingdee.eas.auto4s.rsm.rs.RepairWOInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOItemSpEntryInfo;
import com.kingdee.eas.auto4s.rsm.rs.RepairWORWOSparepartEntryInfo;
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
import com.kingdee.eas.framework.client.multiDetail.DetailPanel;
import com.kingdee.eas.ga.basedata.UserTypeEnum;
import com.kingdee.eas.ga.rs.CustomerAccountInfo;
import com.kingdee.eas.ga.rs.IEnum;
import com.kingdee.eas.ga.rs.TEnum;
import com.kingdee.eas.ga.rs.WInfo;
import com.kingdee.eas.ga.util.GAUtils;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.PermUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

public class RepairWOEditUIPIEx extends RepairWOEditUI {
	
	private static final String CR = "\n\r";
	private UserTypeEnum curUserType = null;
	//private WarrantyTypeInfo defaultWarrantTypeInfo = null;
	private RepairClassifyInfo defaultRepairClassifyInfo = null;
	private RepairTypeInfo defaultRepairTypeInfo = null;
	private boolean hasPermission_OprtRetailLine = false; //零售行操作权限 
	private boolean hasPermission_OprtRepairLine = false; //维修行操作权限
	private boolean isLoadOprtPermission = false; //是否加载了操作权限
	
	public RepairWOEditUIPIEx() throws Exception {
		super();
		getUserType();
	
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
		isLoadOprtPermission = true;
 
	}
	@Override
	protected IObjectValue createNewData() {
		IObjectValue value =  super.createNewData();
		value.put("Mile", 1);
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
	public void onLoad() throws Exception {
		
		super.onLoad();
		
		
		//给[项目/配件明细]的小行操作按钮重新绑定action
		KDWorkButton btnAddNewLine = ((DetailPanel)kdtRWOItemSpEntry_detailPanel).getAddNewLineButton();
		KDWorkButton btnInsertLine = ((DetailPanel)kdtRWOItemSpEntry_detailPanel).getInsertLineButton();
		KDWorkButton btnRemoveLines = ((DetailPanel)kdtRWOItemSpEntry_detailPanel).getRemoveLinesButton();
		resetBtnAction(btnAddNewLine, actionAddLine);
		resetBtnAction(btnInsertLine, actionInsertLine);
		resetBtnAction(btnRemoveLines, actionRemoveLine);
		
		//把底盘号enable掉，解决txtVIN_focusLost重新设置车辆档案引起的重新初化维修项目、配件等分录
		txtVIN.setEnabled(false);
		
		//----------隐藏页签
		
		kDTabbedPane1.remove(kDPHideField);
		//kDTRWOPane.remove(kDPRwoItem);
		//kDTRWOPane.remove(kDPRwoSp);
		kDTRWOPane.remove(kDPSpk);
		kDTRWOPane.remove(kDPAct);
		kDTRWOPane.remove(kDPAcc);
		
		
		//--------------
		
		txtCompanyNumber.setEnabled(false);
		
		defaultValueForAddNew();
		

		registePrmtMaterialF7();
		registePrmtRepairItemF7();
		appendItemSpFoot();
		
	}
	private void defaultValueForAddNew() throws Exception {
		if (getOprtState().equals(OprtState.ADDNEW)) {
			VehicleInfo defaultVehicleInfo = GAUtils.getDefualtVehicleInfo(null);
			prmtVehicle.setValue(defaultVehicleInfo);
			
			CustomerAccountInfo defaultCustomerAccountInfo = GAUtils.getDefaultCustomerAccountInfo(null);
			prmtCustomerAccount.setValue(defaultCustomerAccountInfo);
			
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
				resetItemSpEditorLocked(row);
				Object newObj = kdteditevent.getValue();
				if (!PublicUtils.equals(oldValue, newObj))
					valueChangedForItemSpEntry(kdteditevent);
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
		int totalAmountIndex = kdtRWOItemSpEntry.getColumnIndex("itemspNum");
		footRow.getCell(totalAmountIndex).setValue("总额合计(含税)");
		footRow.getCell(totalAmountIndex).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
		int totalAmountIndex1 = kdtRWOItemSpEntry.getColumnIndex("itemspName");
		footRow.getCell(totalAmountIndex1).setValue(totalTaxAmount);
		footRow.getCell(totalAmountIndex1).getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		footRow.getCell(totalAmountIndex1).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.LEFT);
		
		int totalTaxIndex = kdtRWOItemSpEntry.getColumnIndex("isCT");
		footRow.getCell(totalTaxIndex).setValue("增值税");
		footRow.getCell(totalTaxIndex).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
		footRow.getCell(totalTaxIndex+1).setValue(totalTax);
		footRow.getCell(totalTaxIndex+1).getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		footRow.getCell(totalTaxIndex+1).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.LEFT);
		
		int totalTaxAmountIndex = kdtRWOItemSpEntry.getColumnIndex("issueQty");
		footRow.getCell(totalTaxAmountIndex).setValue("总额合计");
		footRow.getCell(totalTaxAmountIndex).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.RIGHT);
		footRow.getCell(totalTaxAmountIndex+1).setValue(totalAmount);
		footRow.getCell(totalTaxAmountIndex+1).getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		footRow.getCell(totalTaxAmountIndex+1).getStyleAttributes().setHorizontalAlign(HorizontalAlignment.LEFT);
		
	}
	private void valueChangedForItemSpEntry(KDTEditEvent e) {
		int rowIndex = e.getRowIndex();
		int colIndex = e.getColIndex();
		
		int tTypeIndex = kdtRWOItemSpEntry.getColumnIndex("t");
		int repairItemIndex = kdtRWOItemSpEntry.getColumnIndex("repairItem");
		int materialIndex = kdtRWOItemSpEntry.getColumnIndex("material");
		int qtyIndex = kdtRWOItemSpEntry.getColumnIndex("qty");
		int priceIndex = kdtRWOItemSpEntry.getColumnIndex("price");
		int discountRateIndex = kdtRWOItemSpEntry.getColumnIndex("discountRate");
		int wIndex = kdtRWOItemSpEntry.getColumnIndex("w");
		int settlementObjectIndex = kdtRWOItemSpEntry.getColumnIndex("settlementObject");
		
		if (colIndex == wIndex) {
			WInfo wInfo = (WInfo) e.getValue();
			if (wInfo != null)
				 kdtRWOItemSpEntry.getRow(rowIndex).getCell(settlementObjectIndex).setValue(wInfo.getSettleObject());
		} else if (colIndex == qtyIndex || colIndex == priceIndex || colIndex == discountRateIndex) {
			calItemSpEntryAmount(kdtRWOItemSpEntry.getRow(rowIndex));
		}

	}
	
	private void resetItemSpEditorLocked(IRow row) {
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
		}
		if (!hasPermission_OprtRetailLine && PublicUtils.equals("P", cellT.getValue().toString())) {
			row.getStyleAttributes().setLocked(true);
			
		} else if (!hasPermission_OprtRepairLine && PublicUtils.equals("L", cellT.getValue().toString())) {
			row.getStyleAttributes().setLocked(true);
		}
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
						if (PublicUtils.equals(TEnum.P,cmbT.getSelectedItem()))
							itemSpEntryInfo.setDiscountRate(rs.getBigDecimal("CFRetailDiscountRate"));
						else if (PublicUtils.equals(TEnum.L,cmbT.getSelectedItem()))
						itemSpEntryInfo.setDiscountRate(rs.getBigDecimal("CFRepairDiscountRate"));
					}
				} catch (Exception e) {
					UIUtils.handUIExceptionAndAbort(e);
				
				}
			} else itemSpEntryInfo.setDiscountRate(BIGDEC0);
			
			itemSpEntryInfo.setI(IEnum.X);
			itemSpEntryInfo.setQty(BIGDEC1);
			itemSpEntryInfo.setUnIssueQty(BIGDEC1);
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
			if (!isLoadOprtPermission)
				getLineOprtPermission();
		} catch (Exception e) {
			UIUtils.handUIException(e);
		}
		for (int i = 0;i < kdtRWOItemSpEntry.getRowCount(); i++)
			resetItemSpEditorLocked(kdtRWOItemSpEntry.getRow(i));
		
		
	}
	@Override
	protected void unLockUI() {
		super.unLockUI();
		
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
		setTableToSumField(kdtRWOItemSpEntry, new String[]{"qty"});
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
    	for (int i = 0; i < iSelRow.length; i++) {
    		IRow rowItemSpEntry = kdtRWOItemSpEntry.getRow(iSelRow[i]);
    		RepairWORWOItemSpEntryInfo rwoItemSpEntryInfo = (RepairWORWOItemSpEntryInfo) rowItemSpEntry.getUserObject();
    		String itemSpEntryId = rwoItemSpEntryInfo.getString("id");
    		TEnum tType = rwoItemSpEntryInfo.getT();
    		if (TEnum.L.equals(tType)) { //维修项目
    			if (!hasPermission_OprtRepairLine) {
    				MsgBoxEx.showInfo("无操作维修数据行权限！");
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
    	for (int i = 0; i < iSelRow.length; i++) {
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
	
	private void registePrmtRepairItemF7() {

		//prmtRepairItem.setVisible(true);
		//prmtRepairItem.setEditable(true);
		prmtRepairItem.setDisplayFormat("$number$");
		prmtRepairItem.setEditFormat("$number$");
		prmtRepairItem.setCommitFormat("$number$");
		prmtRepairItem.setEnabledMultiSelection(true);
		
		ObjectValueRender kdtRWORepairItemEntry_RepairItem_OVR = new ObjectValueRender();
		kdtRWORepairItemEntry_RepairItem_OVR.setFormat(new BizDataFormat("$number$"));

		prmtRepairItem.addSelectorListener(new SelectorListener() {
			RepairItemEntryListUI repairItemEntryF7ListUI;
			public void willShow(SelectorEvent e) {
				try {
					repairItemEntryF7ListUI = new RepairItemEntryListUI();
					repairItemEntryF7ListUI.setExpandFilter(getVehicleFilterInfo());
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
					  KDTSelectManager selectManager = kdTable.getSelectManager();
			          KDTSelectBlock selectBlock = selectManager.get(0);
			          int beginRowIndex = 0;
			          if(selectBlock == null)
			        	  beginRowIndex = kdTable.getRowCount();
			          else beginRowIndex = selectBlock.getBeginRow();
			          if (beginRowIndex > 0 && selectBlock == null) 
			        	  beginRowIndex--;
			          boolean isFirstRowIndex = true;
			          
			          int i = 0;
			          for(int length = repairItemEntryInfos.length; i < length; i++) {
			              int rowIndex = beginRowIndex + i;
			              RepairItemEntryInfo repairItemEntryInfo = repairItemEntryInfos[i];
			              RepairItemInfo repairItemInfo = repairItemEntryInfo.getParent();
			              repairItemNums = repairItemNums + repairItemInfo.getNumber() + ";";
			              if(isFirstRowIndex)  {
			            	  if (beginRowIndex==0)
			            		  insertLine(kdTable, rowIndex);
			            	  else if (kdTable.getCell(rowIndex, "repairItem").getValue() != null
			            			  || kdTable.getCell(rowIndex, "material").getValue() != null)
			            		  insertLine(kdTable, rowIndex);

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
			prmtRepairItem.setValue(repairItemNums);
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
						//repairItem_PromptBox_dataChanged(repairItemEntryInfos);
					}
				}
			}

			RepairItemEntryListUI repairItemEntryF7ListUI;
		});
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
						  KDTable kdTable = kdtRWOItemSpEntry;
						  if(null == kdTable)
					         return;
						  KDTSelectManager selectManager = kdTable.getSelectManager();
				          KDTSelectBlock selectBlock = selectManager.get(0);
				          int beginRowIndex = 0;
				          if(selectBlock == null)
				        	  beginRowIndex  = kdTable.getRowCount();
				          else beginRowIndex = selectBlock.getBeginRow();
				          if (beginRowIndex > 0 && selectBlock == null) 
				        	  beginRowIndex--;
				          boolean isFirstRowIndex = true;
				          
				          int i = 0;
				          for(int length = materialInfos.length; i < length; i++) {
				              int rowIndex = beginRowIndex + i;
				              MaterialInfo materialInfo = materialInfos[i];
				              if(isFirstRowIndex)  {
				            	  if (beginRowIndex==0)
				            		  insertLine(kdTable, rowIndex);
				            	  else if (kdTable.getCell(rowIndex, "material").getValue() != null ||
				            			  kdTable.getCell(rowIndex,"repairItem").getValue() != null)
				            		  insertLine(kdTable, rowIndex);

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
    	saveRepairItemAndSpToEntry();
    	
    	//---begin--原4S标准代码，调整，取消对负数控制
    	isNotNull();
    	
		//super.beforeStoreFields(arg0);
    	//--begin 添加抽象类AbstractRepairWOEditUI.beforeStoreFields 
    	
    	if (com.kingdee.bos.ui.face.UIRuleUtil.isNull(txtTel.getText())) {
			throw new com.kingdee.eas.common.EASBizException(com.kingdee.eas.common.EASBizException.CHECKBLANK,new Object[] {"送修人电话"});
		}
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
    
    private void calItemSpEntryAmount(IRow row) {
    	//总计=数量*(1+税率%/100)*价格*(1-折扣%/100)
    	TEnum tType = (TEnum)row.getCell("t").getValue(); //T
    	BigDecimal qty = (BigDecimal) row.getCell("qty").getValue(); //数量/工时
		BigDecimal price = (BigDecimal)row.getCell("price").getValue(); //不含单价
		BigDecimal discountRate = (BigDecimal)row.getCell("discountRate").getValue(); //折扣
		BigDecimal taxRate = (BigDecimal)row.getCell("taxRate").getValue(); //税率
		BigDecimal amount = qty.multiply(price).multiply(BIGDEC1.subtract(discountRate.divide(BIGDEC100,10,BigDecimal.ROUND_HALF_UP)));
		row.getCell("amount").setValue(amount);
		
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
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "amount",true);
        UIUtils.formatDecimal(kdtRWOItemSpEntry, "discountRate",false);
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
    				txtcustomInfo.setText(strCustomerInfo);
    				//按品牌获取默认维修类型与维修种类
    				defaultRepairTypeInfo = GAUtils.getDefaultRepairType(null,brandInfo);
    				prmtRepairType.setValue(defaultRepairTypeInfo);
    				
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
				//	int status = MsgBox.showConfirm2("进厂行驶里程小于或等于上次进厂行驶里程,是否继续?");
				//	if (2 == status)
				//		SysUtil.abort();
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

}
