package com.kingdee.eas.ga.syncdata.client;

import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.swing.KDFileChooser;
import com.kingdee.bos.ctrl.swing.KDLabelContainer;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.auto4s.rsm.rs.util.client.RsQueryF7Utils;
import com.kingdee.eas.auto4s.rsm.rs.util.client.RsUtils;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.orgext.ServiceOrgUnitInfo;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.ga.syncdata.DMSImpTypeEnum;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryCollection;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryCollection;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryFactory;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryInfo;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry2Collection;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntry3Collection;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntryCollection;
import com.kingdee.eas.ga.syncdata.DMSWipBillFactory;
import com.kingdee.eas.ga.syncdata.DMSWipBillInfo;
import com.kingdee.eas.ga.syncdata.IDMSInOutQuery;
import com.kingdee.eas.ga.syncdata.IDMSWipBill;
import com.kingdee.eas.ga.syncdata.ISyncDataFacade;
import com.kingdee.eas.ga.syncdata.SyncDataFacadeFactory;
import com.kingdee.eas.ga.syncdata.parsedata.DMSTradeInquireParse;
import com.kingdee.eas.ga.syncdata.parsedata.WipBillHeadParse;
import com.kingdee.eas.ga.syncdata.parsedata.WipBillManHourParse;
import com.kingdee.eas.ga.syncdata.parsedata.WipBillMaterialParse;
import com.kingdee.eas.myframework.client.FileFilterImp;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.CodingRuleUtils;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.vo.ServerReturnInfo;
import com.kingdee.eas.scm.common.BillBaseStatusEnum;
import com.kingdee.eas.util.SysUtil;

public class DMSDataImport extends AbstractDMSDataImport {
	
	private static final Logger logger = CoreUIObject.getLogger(DMSDataImport.class);
	private File file1;
	private File file2;
	private File file3;
	private String defaultPath;
	private boolean isRun = false;

	public DMSDataImport() throws Exception {
		super();
	}

	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		makeOrgUnitF7();
		ImageIcon icon = new ImageIcon(DMSDataImport.class.getResource("loading.gif"));
		labProgress.setIcon(icon);
		labProgress.setVisible(false);
		cmbImportType_itemStateChanged(null);
		ServiceOrgUnitInfo orgUnitInfo = RsUtils.getServiceOrgUnitInfo();
		prmtServiceOrg.setValue(orgUnitInfo);
		contSpentInfo.setVisible(chkShowSpent.isSelected());
		chkShowSpent.setSize(1,1);
	}
	
	@Override
	protected void chkShowSpent_stateChanged(ChangeEvent e) throws Exception {
		contSpentInfo.setVisible(chkShowSpent.isSelected());
	}

	@Override
	protected void cmbImportType_itemStateChanged(ItemEvent e) throws Exception {

		DMSImpTypeEnum impType = (DMSImpTypeEnum) cmbImportType
				.getSelectedItem();
		if (impType.equals(DMSImpTypeEnum.WIPBILL)) { // WIN单
			labFile1.setVisible(true);
			labFile2.setVisible(true);
			labFile3.setVisible(true);
			btnBrower1.setVisible(true);
			btnBrower2.setVisible(true);
			btnBrower3.setVisible(true);
			labFile1.setBoundLabelText("WIP头");
			labFile2.setBoundLabelText("零件行");
			labFile3.setBoundLabelText("工时行");

			int file1Y = labFile1.getBounds().y;
			Rectangle recMsg = contMsgInfo.getBounds();
			contMsgInfo.setBounds(recMsg.x, file1Y + 90, recMsg.width, recMsg.height);
			
			Rectangle recSpent = contSpentInfo.getBounds();
			contSpentInfo.setBounds(recSpent.x, file1Y + 90, recSpent.width,recSpent.height);
			

		} else if (impType.equals(DMSImpTypeEnum.TRADEINQUIRE)) { // 出入库单据
			labFile1.setVisible(true);
			labFile2.setVisible(false);
			labFile3.setVisible(false);
			btnBrower1.setVisible(true);
			btnBrower2.setVisible(false);
			btnBrower3.setVisible(false);
			labFile1.setBoundLabelText("交易查询");
			int file1Y = labFile1.getBounds().y;
			Rectangle recMsg = contMsgInfo.getBounds();
			contMsgInfo.setBounds(recMsg.x, file1Y + 25, recMsg.width, recMsg.height);
			
			Rectangle recSpent = contSpentInfo.getBounds();
			contSpentInfo.setBounds(recSpent.x, file1Y + 25, recSpent.width,recSpent.height);
			
		}	
		resetFileChoose();
	}
		
	private void resetFileChoose() throws Exception {
		if (file1 != null) file1 = null;
		if (file2 != null) file2 = null;
		if (file3 != null) file3 = null;
		txtFile1.setText("");
		txtFile2.setText("");
		txtFile3.setText("");
	}
	
	private void makeOrgUnitF7() {
		UserInfo userInfo = SysContext.getSysContext().getCurrentUserInfo();
		RsQueryF7Utils.makeOrgUnitF7(userInfo, prmtServiceOrg);
	}

	protected void btnBrower1_actionPerformed(java.awt.event.ActionEvent e)
			throws Exception {
		File file = openFile(txtFile1,labFile1);
		if (file != null) file1 = file;
	}

	protected void btnBrower2_actionPerformed(java.awt.event.ActionEvent e)
			throws Exception {
		File file = openFile(txtFile2,labFile2);
		if (file != null) file2 = file;
	}

	protected void btnBrower3_actionPerformed(java.awt.event.ActionEvent e)
			throws Exception {
		File file = openFile(txtFile3,labFile3);
		if (file != null) file3 = file;
	}

	protected void btnImport_actionPerformed(java.awt.event.ActionEvent e)
			throws Exception {
		if (DMSImpTypeEnum.WIPBILL.equals(cmbImportType.getSelectedItem())) {
			if (isRun) {
				MsgBoxEx.showInfo("导入正在运行中，请销后再试!");
				return;
			}
			ServiceOrgUnitInfo serviceOrgInfo = (ServiceOrgUnitInfo) prmtServiceOrg.getData();
			if (serviceOrgInfo== null) {
				MsgBoxEx.showInfo("公司不能为空，请选择!");
				prmtServiceOrg.requestFocus();
				return;
			}
			if (file1 == null ||  !file1.exists()) {
				MsgBoxEx.showInfo("[WIP头]文件不存在，请选择!");
				btnBrower1.requestFocus();
				return;
			}
			if (file2 == null ||  !file2.exists()) {
				MsgBoxEx.showInfo("[零件行]文件不存在，请选择!");
				btnBrower2.requestFocus();
				return;
			}
			if (file3 == null ||  !file3.exists()) {
				MsgBoxEx.showInfo("[工时行]文件不存在，请选择!");
				btnBrower3.requestFocus();
				return;
			}
			labProgress.setVisible(true);
			isRun = true;
			new ThreadTimer().start();
			ThreadImportWipBill threadImportWipBill = new ThreadImportWipBill();
			threadImportWipBill.start();

			new ThreadTimer().start();
		} else if (DMSImpTypeEnum.TRADEINQUIRE.equals(cmbImportType.getSelectedItem())) {
			if (isRun) {
				MsgBoxEx.showInfo("导入正在运行中，请销后再试!");
				return;
			}
			ServiceOrgUnitInfo serviceOrgInfo = (ServiceOrgUnitInfo) prmtServiceOrg.getData();
			if (serviceOrgInfo== null) {
				MsgBoxEx.showInfo("公司不能为空，请选择!");
				prmtServiceOrg.requestFocus();
				return;
			}
			if (file1 == null ||  !file1.exists()) {
				MsgBoxEx.showInfo("[交易查询]文件不存在，请选择!");
				btnBrower1.requestFocus();
				return;
			}
			labProgress.setVisible(true);
			isRun = true;
			new ThreadTimer().start();
			ThreadImportOUTINBill threadImportOUTINBill = new ThreadImportOUTINBill();
			threadImportOUTINBill.start();
		}

	}
	
	private void importWIPBill() throws Exception {
		try {
			labProgress.setVisible(true);
			txtMsgInfo.setText("");
			txtSpentInfo.setText("");
			isRun = true;
			ServiceOrgUnitInfo serviceOrgInfo = (ServiceOrgUnitInfo) prmtServiceOrg.getData();
			
			DMSWipBillInfo dmsWipInfo = new DMSWipBillInfo();
			if (!CodingRuleUtils.hasCodingRule(dmsWipInfo, serviceOrgInfo.getString("id"))) {
				MsgBoxEx.showInfo("未配置DMSWIP单编码规则，请先配置！");
				return;
			}
			dmsWipInfo.setServiceOrgUnit(serviceOrgInfo);
			dmsWipInfo.setBizDate(SysUtil.getAppServerTime(null));
			

			
			long start = System.currentTimeMillis();
			appendSpentInfo("开始解析WIP头");
			WipBillHeadParse wipHeadParse = new WipBillHeadParse(file1);
			DMSWipBillEntryCollection dmsWipBillEntryCollection = wipHeadParse.parseContent();
			addSpentInfo("耗时(ms):" + String.valueOf(System.currentTimeMillis() - start));
	
			start = System.currentTimeMillis();
			appendSpentInfo("解析零件行");
			WipBillMaterialParse wipMaterialParse = new WipBillMaterialParse(file2);
			DMSWipBillEntry2Collection dmsWipBillEntry2Collection = wipMaterialParse.parseContent();
			addSpentInfo("耗时(ms):" + String.valueOf(System.currentTimeMillis() - start));
			
			start = System.currentTimeMillis();
			appendSpentInfo("解析工时行");
			WipBillManHourParse wipManHourParse = new WipBillManHourParse(file3);
			DMSWipBillEntry3Collection dmsWipBillEntry3Collection = wipManHourParse.parseContent();
			addSpentInfo("耗时(ms):" + String.valueOf(System.currentTimeMillis() - start));
			
			dmsWipInfo.put("entrys",dmsWipBillEntryCollection);
			dmsWipInfo.put("entry2",dmsWipBillEntry2Collection);
			dmsWipInfo.put("entry3",dmsWipBillEntry3Collection);
			
			start = System.currentTimeMillis();
			IDMSWipBill dmsWipBill = DMSWipBillFactory.getRemoteInstance();
			addSpentInfo("连接远程接口DMSWipBill耗时(ms):" + String.valueOf(System.currentTimeMillis() - start));
			dmsWipInfo.setBaseStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
			
			start = System.currentTimeMillis();
			IObjectPK pk = dmsWipBill.addnew(dmsWipInfo);
			addSpentInfo("新增DMSWIP单(ms):" + String.valueOf(System.currentTimeMillis() - start));
			
			
			start = System.currentTimeMillis();
			ISyncDataFacade syncFacade = SyncDataFacadeFactory.getRemoteInstance();
			addSpentInfo("连接远程接口SyncDataFacade耗时(ms):" + String.valueOf(System.currentTimeMillis() - start));
			
			start = System.currentTimeMillis();
			ServerReturnInfo returnInfo = syncFacade.syncWipBill(serviceOrgInfo, pk);
			addSpentInfo("服务器执行DMSWIP单转EAS维修工单耗时(ms):" + String.valueOf(System.currentTimeMillis() - start));
			isRun = false;
			addSpentInfo(returnInfo.getSpentMsg());
			addMsgInfo(returnInfo.getExptionMsg());
			if (returnInfo.isSuccess()&&!returnInfo.isExption) {
				addMsgInfo("导入成功...");
				MsgBoxEx.showInfo("导入成功...");
			} else {
				addMsgInfo("导入异常...");
				MsgBoxEx.showInfo("导入异常...");
			}
			
		} catch (Exception e) {
			isRun = false;
			addMsgInfo(PublicUtils.getStackTrace(e));
			e.printStackTrace();
			addMsgInfo("导入异常...");
			MsgBoxEx.showInfo("导入异常...");
		} finally {
			labProgress.setVisible(false);
			isRun = false;
		}
		
	}
	
	private void importOUTINBill() throws Exception {
		try {
			labProgress.setVisible(true);
			txtMsgInfo.setText("");
			txtSpentInfo.setText("");
			isRun = true;
			ServiceOrgUnitInfo serviceOrgInfo = (ServiceOrgUnitInfo) prmtServiceOrg.getData();
			
			DMSInOutQueryInfo dmsInoutQueryInfo = new DMSInOutQueryInfo();
			if (!CodingRuleUtils.hasCodingRule(dmsInoutQueryInfo, serviceOrgInfo.getString("id"))) {
				MsgBoxEx.showInfo("未配置DMS交易查询单编码规则，请先配置！");
				return;
			}
			dmsInoutQueryInfo.setServiceOrgUnit(serviceOrgInfo);
			dmsInoutQueryInfo.setBizDate(SysUtil.getAppServerTime(null));
			

			
			long start = System.currentTimeMillis();
			appendSpentInfo("开始解析交易查询");
			DMSTradeInquireParse dmsTradeInquireParse = new DMSTradeInquireParse(file1);
			DMSInOutQueryEntryCollection dmsInOutQueryEntryCol = dmsTradeInquireParse.parseContent();
			addSpentInfo("耗时(ms):" + String.valueOf(System.currentTimeMillis() - start));
	
			
			dmsInoutQueryInfo.put("entrys",dmsInOutQueryEntryCol);
			
			start = System.currentTimeMillis();
			IDMSInOutQuery dmsInOutQuery = DMSInOutQueryFactory.getRemoteInstance();
			addSpentInfo("连接远程接口DMSInOutQuery耗时(ms):" + String.valueOf(System.currentTimeMillis() - start));
			dmsInoutQueryInfo.setBaseStatus(BillBaseStatusEnum.TEMPORARILYSAVED);
			
			start = System.currentTimeMillis();
			IObjectPK pk = dmsInOutQuery.addnew(dmsInoutQueryInfo);
			addSpentInfo("新增DMS交易查询单(ms):" + String.valueOf(System.currentTimeMillis() - start));
			
			
			start = System.currentTimeMillis();
			ISyncDataFacade syncFacade = SyncDataFacadeFactory.getRemoteInstance();
			addSpentInfo("连接远程接口SyncDataFacade耗时(ms):" + String.valueOf(System.currentTimeMillis() - start));
			
			start = System.currentTimeMillis();
			ServerReturnInfo returnInfo = syncFacade.syncTradeInquire(serviceOrgInfo, pk);
			addSpentInfo("服务器执行交易查询单转EAS出入库单据耗时(ms):" + String.valueOf(System.currentTimeMillis() - start));
			isRun = false;
			addSpentInfo(returnInfo.getSpentMsg());
			addMsgInfo(returnInfo.getExptionMsg());
			if (returnInfo.isSuccess()&&!returnInfo.isExption) {
				addMsgInfo("导入成功...");
				MsgBoxEx.showInfo("导入成功...");
			} else {
				addMsgInfo("导入异常...");
				MsgBoxEx.showInfo("导入异常...");
			}
			
		} catch (Exception e) {
			isRun = false;
			addMsgInfo(PublicUtils.getStackTrace(e));
			e.printStackTrace();
			addMsgInfo("导入异常...");
			MsgBoxEx.showInfo("导入异常...");
		} finally {
			labProgress.setVisible(false);
			isRun = false;
		}
	}
	private File openFile(KDTextField txtFile, KDLabelContainer labFile) throws Exception {
		KDFileChooser fileChooser = new KDFileChooser(defaultPath);
		FileFilter filter = new FileFilterImp("Excel文件", new String[]{".xls",".xlsx"});
		fileChooser.addChoosableFileFilter(filter);
		fileChooser.setFileFilter(filter);
		int status = fileChooser.showDialog(this, labFile.getBoundLabelText());
		if (status == KDFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			defaultPath = file.getPath();
			txtFile.setText(file.getAbsolutePath());
			return file;
		}
		return null;
		
	}
	
	private void appendMsgInfo(String msg) {
		txtMsgInfo.append(msg);
	}
	
	private void addMsgInfo(String msg) {
		txtMsgInfo.append(msg);
		txtMsgInfo.append("\n\r");
	}
	
	private void appendSpentInfo(String msg) {
		txtSpentInfo.append(msg);
	}
	
	private void addSpentInfo(String msg) {
		txtSpentInfo.append(msg);
		txtSpentInfo.append("\n\r");
	}
	
	
	class ThreadImportWipBill extends Thread {
		public void run() {
			super.run();
			try {
				importWIPBill();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public synchronized void start() {
			super.start();
		}		
	}
	
	class ThreadImportOUTINBill extends Thread {
		public void run() {
			super.run();
			try {
				importOUTINBill();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public synchronized void start() {
			super.start();
		}		
	}
	
	class ThreadTimer extends Thread {
		public void run() {
			super.run();
			try {
				long start = System.currentTimeMillis();
				while (isRun) {
					sleep(100);
					labProgress.setText(String.format("%d s", (System.currentTimeMillis()-start)/1000));
				}
				super.interrupted();
			} catch (Exception e) {e.printStackTrace();}
		}

		@Override
		public synchronized void start() {
			super.start();
		}		
	}
	
}