/**
 * output package name
 */
package com.kingdee.eas.auto4s.rsm.rs.client;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.kdf.data.event.RequestRowSetEvent;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.eas.auto4s.bdm.pbd.BrandInfo;
import com.kingdee.eas.auto4s.bdm.rsm.RepairBillStatusEnum;
import com.kingdee.eas.auto4s.bdm.rsm.RepairTypeInfo;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.ga.rs.RepairWOStatusEnum;
import com.kingdee.eas.myframework.util.InvokeUtils;
import com.kingdee.util.StringUtils;

public class RepairWOListUIPIEx extends RepairWOListUI {
	private static final Logger logger = CoreUIObject
			.getLogger(RepairWOListUIPIEx.class);

	public RepairWOListUIPIEx() throws Exception {
		super();
	}

	@Override
	public void onLoad() throws Exception {
		cmbGABillStatus.insertItemAt("全部", 0);
		cmbGABillStatus.setSelectedIndex(0);
		
		super.onLoad();
		int intendDeliveryTimeIndex = tblMain.getColumnIndex("IntendDeliveryTime");
		tblMain.getHead().getRow(0).getCell(intendDeliveryTimeIndex).setValue("预计出厂时间");
		setUITitle("维修历史");
		
		actionPrint.setVisible(false);
		actionPrintPreview.setVisible(false);
	}
	
	@Override
	protected void tblMain_doRequestRowSet(RequestRowSetEvent e) {
		try {
			FilterInfo myFilterInfo = (FilterInfo) InvokeUtils.getFieldValue(this, "myFilterInfo");
			FilterInfo filterInfo = new FilterInfo();
			if (cmbGABillStatus.getSelectedItem() != null && cmbGABillStatus.getSelectedItem().toString() != quanBu) {
				filterInfo.getFilterItems().add(new FilterItemInfo("gaBillStatus", ((RepairWOStatusEnum) cmbGABillStatus.getShowSelectedItem()).getValue(),CompareType.EQUALS));
			}
			filterInfo.getFilterItems().add(new FilterItemInfo("isPrintedSettle",chkSettlePrinted.isSelected()));
			myFilterInfo.mergeFilter(filterInfo,"and");
			
		} catch (Exception ee) {}
		super.tblMain_doRequestRowSet(e);
	}
	

}