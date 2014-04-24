/**
 * output package name
 */
package com.kingdee.eas.auto4s.rsm.rs.client;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.data.event.RequestRowSetEvent;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.swing.LimitedLengthDocument;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.IUIFactory;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.eas.base.uiframe.client.UIModelDialog;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.ga.rs.RepairWOStatusEnum;
import com.kingdee.eas.ga.rs.client.RepairWOAllocateExpenseUI;
import com.kingdee.eas.myframework.util.InvokeUtils;
import com.kingdee.eas.myframework.util.MutexUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.UIUtils;

public class RepairWOListUIPIEx extends RepairWOListUI {
	private static final Logger logger = CoreUIObject
			.getLogger(RepairWOListUIPIEx.class);

	public RepairWOListUIPIEx() throws Exception {
		super();
	}

	@Override
	public void onLoad() throws Exception {
		cmbGABillStatus.removeAllItems();
		cmbGABillStatus.insertItemAt("全部", 0);
		cmbGABillStatus.insertItemAt("未结算", 1);
		cmbGABillStatus.insertItemAt("结算", 2);
		
		cmbGABillStatus.setSelectedIndex(0);
		super.onLoad();
		int intendDeliveryTimeIndex = tblMain.getColumnIndex("IntendDeliveryTime");
		tblMain.getHead().getRow(0).getCell(intendDeliveryTimeIndex).setValue("预计出厂时间");
		setUITitle("维修历史");
		
		actionPrint.setVisible(false);
		actionPrintPreview.setVisible(false);
		
		kDComeTime1.setRequired(false);
		kdComeTime2.setRequired(false);
		
		txtNumber.setVisible(false);
		txtVehicle.setVisible(false);
		txtVIN.setVisible(false);
		
		
		txtNumberEx.setDocument(new LimitedLengthDocumentEx());
		
		txtNumberEx.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent key) {
				txtNumber.setText(txtNumberEx.getText());
				
				if (key.getKeyCode() == KeyEvent.VK_ENTER && !PublicUtils.isEmpty(txtNumberEx.getText())) {
					try {
						btnSearch_actionPerformed(null);
						
						IRow row0 = tblMain.getRow(0);
						IRow row1 = tblMain.getRow(1);
						if (row0 != null && row1 == null) {
							tblMain.getSelectManager().select(0, 0);
							showEditUI();
							return;
						}
					} catch (Exception e) {
						UIUtils.handUIException(e);
					}
					tblMain.getSelectManager().select(0, 0);
					txtNumberEx.requestFocus();
					txtNumberEx.selectAll();
					
				}
			}
		});
		txtVehicleEx.setDocument(new LimitedLengthDocumentEx());
		txtVehicleEx.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent key) {
				txtVehicle.setText(txtVehicleEx.getText());
				if (key.getKeyCode() == KeyEvent.VK_ENTER && !PublicUtils.isEmpty(txtVehicleEx.getText())) {
					try {
						btnSearch_actionPerformed(null);
						IRow row0 = tblMain.getRow(0);
						IRow row1 = tblMain.getRow(1);
						if (row0 != null && row1 == null) {
							tblMain.getSelectManager().select(0, 0);
							showEditUI();
							return;
						}
					} catch (Exception e) {
						UIUtils.handUIException(e);
					}
					tblMain.getSelectManager().select(0, 0);
					txtVehicleEx.requestFocus();
					txtVehicleEx.selectAll();
				}
			}
		});
		txtVINEx.setDocument(new LimitedLengthDocumentEx());
		txtVINEx.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent key) {
				txtVIN.setText(txtVINEx.getText());
				if (key.getKeyCode() == KeyEvent.VK_ENTER && !PublicUtils.isEmpty(txtVINEx.getText())) {
					try {
						btnSearch_actionPerformed(null);
						IRow row0 = tblMain.getRow(0);
						IRow row1 = tblMain.getRow(1);
						if (row0 != null && row1 == null) {
							tblMain.getSelectManager().select(0, 0);
							showEditUI();
							return;
						}
					} catch (Exception e) {
						UIUtils.handUIException(e);
					}
					tblMain.getSelectManager().select(0, 0);
					txtVINEx.requestFocus();
					txtVINEx.selectAll();
				}
			}
		});
	}
	
	private void showEditUI() throws Exception {
		UIContext uictx = new UIContext(this);
		uictx.put("ID", getSelectedKeyValue());
		prepareUIContext(uictx, new ActionEvent(btnView, 0, "Double Clicked"));
		
		IUIFactory iUIFactory = UIFactory.createUIFactory(UIFactoryName.NEWTAB);
		IUIWindow iUIWindow = iUIFactory.create(RepairWOEditUIPIEx.class.getName(),uictx,null,"FINDVIEW"); 
		iUIWindow.show();
	}
	
	@Override
	protected void tblMain_doRequestRowSet(RequestRowSetEvent e) {
		try {
			FilterInfo myFilterInfo = (FilterInfo) InvokeUtils.getFieldValue(this, "myFilterInfo");
			FilterInfo filterInfo = new FilterInfo();
			if (PublicUtils.equals("未结算", cmbGABillStatus.getSelectedItem())) {
				filterInfo.getFilterItems().add(new FilterItemInfo("gaBillStatus", RepairWOStatusEnum.ALLSETTLE_VALUE,CompareType.NOTEQUALS));
			} else if (PublicUtils.equals("结算", cmbGABillStatus.getSelectedItem())) {
				filterInfo.getFilterItems().add(new FilterItemInfo("gaBillStatus", RepairWOStatusEnum.ALLSETTLE_VALUE));
			}
			
			//if (cmbGABillStatus.getSelectedItem() != null && cmbGABillStatus.getSelectedItem().toString() != quanBu) {
			//	filterInfo.getFilterItems().add(new FilterItemInfo("gaBillStatus", ((RepairWOStatusEnum) cmbGABillStatus.getShowSelectedItem()).getValue(),CompareType.EQUALS));
			//}
			
			if (cmbSettlePrinted.getSelectedItem() != null && !PublicUtils.equals(quanBu, cmbSettlePrinted.getSelectedItem())) {
				if (PublicUtils.equals("是", cmbSettlePrinted.getSelectedItem())) {
					filterInfo.getFilterItems().add(new FilterItemInfo("isPrintedSettle",true));
				} else if  (PublicUtils.equals("否", cmbSettlePrinted.getSelectedItem())) {
					filterInfo.getFilterItems().add(new FilterItemInfo("isPrintedSettle",false));
				}
			}
			
			myFilterInfo.mergeFilter(filterInfo,"and");
			
		} catch (Exception ee) {}
		super.tblMain_doRequestRowSet(e);
	}
	
	@Override
	public void actionEdit_actionPerformed(ActionEvent e) throws Exception {
		checkSelected();
		MutexUtils.throwsLockBillException(null,getSelectedKeyValue());
		
		super.actionEdit_actionPerformed(e);
	}
	
	@Override
	protected void btnSearch_actionPerformed(ActionEvent e) throws Exception {
		InvokeUtils.setFieldValue(this, "isQueryBtnClicked",true);
		searchFilter();
		execQuery();
		txtNumber.requestFocus();
	}
	
	@Override
	public void prmtOrgUnitprmtBrand() {
		super.prmtOrgUnitprmtBrand();
		prmtSA.setValue(null);
	}
	
	@Override
	public void onShow() throws Exception {
		super.onShow();
		txtNumberEx.requestFocus();
	}
	
	@Override
	protected void timeinitialize() {
		kDComeTime1.setValue(null);
		kdComeTime2.setValue(null);
	}
	
	class LimitedLengthDocumentEx extends LimitedLengthDocument {
		@Override
		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {
			if (str.equals("\n")) return;
			super.insertString(offs, str, a);
		
		}

	}

}