package com.kingdee.eas.scm.cal.client;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kingdee.bos.Context;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.KDComboBox;
import com.kingdee.bos.dao.query.SQLExecutor;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.scm.cal.ApportionResultInfo;
import com.kingdee.eas.scm.cal.DevolveApportionRuleEnum;
import com.kingdee.eas.scm.cal.DevolveWriteOffFacadeFactory;
import com.kingdee.eas.scm.cal.DevolveWriteOffStandardEnum;
import com.kingdee.eas.scm.cal.IDevolveWriteOffFacade;
import com.kingdee.eas.scm.cal.info.DevolveInWarehsBillInfo;
import com.kingdee.eas.scm.cal.info.DevolveMatReqBillInfo;
import com.kingdee.eas.scm.cal.info.DevolveWriteOffResultInfo;
import com.kingdee.eas.scm.cal.info.ReturnInfo;
import com.kingdee.eas.scm.cal.util.CalculateUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;

public class DevolveWriteOffForMultiBill implements Serializable {

	private DevolveWriteOffUIPIEx ui;
	private KDTable topKdtable;
	private KDTable bottomKdTable;
	private List materialIdList;
	//private KDComboBox cmbWriteOffStandard;
	private KDComboBox cmbApportionRule;
	private DevolveWriteOffStandardEnum writeOffStandard;
//	private boolean isSameSign;
//	private int topSignNum;
	private Context ctx;
	
	public DevolveWriteOffForMultiBill(DevolveWriteOffUIPIEx ui, KDTable topKdtable, KDTable bottomKdTable, 
			KDComboBox cmbWriteOffStandard, KDComboBox cmbApportionRule) throws Exception {
		this.ui = ui;
		this.topKdtable = topKdtable;
		this.bottomKdTable = bottomKdTable;
	//	this.cmbWriteOffStandard = cmbWriteOffStandard;
		writeOffStandard = (DevolveWriteOffStandardEnum) cmbWriteOffStandard.getSelectedItem();
		this.cmbApportionRule = cmbApportionRule;
//		isSameSign = (Boolean) InvokeUtils.getFieldValue(ui, "isSameSign");
//		topSignNum = (Integer) InvokeUtils.getFieldValue(ui, "topSignNum");
		ctx = (Context) InvokeUtils.invokeMethod(ui, "getMainOrgContext", null);
		materialIdList = new ArrayList(); 
		materialIdList = (List) InvokeUtils.getFieldValue(ui, "materialIdList");
	}

	public void writeOff() throws Exception {
		boolean topSelected = isSelected(topKdtable);
        boolean botSelected = isSelected(bottomKdTable);
        boolean isCheck = (Boolean) InvokeUtils.invokeMethod(ui, "beforeWriteOffCheck", new Object[] {topSelected,botSelected});
       // if(!beforeWriteOffCheck(topSelected, botSelected))
        if (!isCheck) 
        	return;
        /**
          	检查多单核销功能
         */
        if (!writeOffStandard.equals(DevolveWriteOffStandardEnum.MANUAL_WRITEOFF)) {
        	MsgBox.showWarning(ui, "多单核销仅支持手工核销方式！");
        	return;
        }
        DevolveApportionRuleEnum apportionRuleEnum =  (DevolveApportionRuleEnum) cmbApportionRule.getSelectedItem();
        if (!apportionRuleEnum.equals(DevolveApportionRuleEnum.QUANTITY)) {
        	MsgBox.showWarning(ui, "多单核销仅支持按数量分摊！");
        	return;
        }
        
        UserInfo user = SysContext.getSysContext().getCurrentUserInfo();
        if(!CalculateUtil.isAuthoriedForOrg(ui.getCompanyOrgUnitID(), user.getId().toString(), "DevMatWriteOff"))
        {
            showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "isWriteOff");
            return;
        }
        StringBuilder errorMsg = new StringBuilder();
        IRow row = null;
        //----------------分组核销
        HashMap<String, DevolveWriteOffResultInfo> hashDWFResultInfo = new HashMap<String, DevolveWriteOffResultInfo>();
        DevolveApportionRuleEnum appRule = (DevolveApportionRuleEnum)cmbApportionRule.getSelectedItem();
        for(int i = 0; topSelected && i < topKdtable.getBody().getRows().size(); i++) {
            row = topKdtable.getRow(i);
            if(Boolean.TRUE.equals(row.getCell("selected").getValue()))  {
                DevolveInWarehsBillInfo vo = buildDevolveInWarehsInfo(row);
                String materialNum = (String) row.getCell("materialNumber").getValue();
                String coreBillNumber =  vo.getCoreBillNumber();
                String key = coreBillNumber + ";" + materialNum;
                DevolveWriteOffResultInfo dwfReusltInfo = hashDWFResultInfo.get(key);
                if (dwfReusltInfo == null) {
                	dwfReusltInfo = new DevolveWriteOffResultInfo();
                	dwfReusltInfo.setCompanyOrgUnitID(ui.getCompanyOrgUnitID());
                	dwfReusltInfo.setWriteOffStandard(writeOffStandard.getValue());
                	dwfReusltInfo.setApportionRule(appRule.getValue());
                	dwfReusltInfo.setDevInWarehsList(new ArrayList<DevolveInWarehsBillInfo>());
                	dwfReusltInfo.setDevMaterialReqList(new ArrayList<DevolveMatReqBillInfo>());
                	hashDWFResultInfo.put(key, dwfReusltInfo);
                }
                List<DevolveInWarehsBillInfo> DevolveInWarehs = dwfReusltInfo.getDevInWarehsList();
                DevolveInWarehs.add(vo);
            }
        }
        for(int i = 0; botSelected && i < bottomKdTable.getBody().getRows().size(); i++) {
            row = bottomKdTable.getRow(i);
            if(Boolean.TRUE.equals(row.getCell("selected").getValue()) && materialIdList.indexOf((String)row.getCell("materialNumber").getValue()) < 0) {
            	DevolveMatReqBillInfo vo = buildDevolveMatReqInfo(row);
            	String coreBillNumber =  vo.getCoreBillNumber();
            	String materialNum = (String)row.getCell("processMaterialNumber").getValue();
            	String key = coreBillNumber + ";" + materialNum;
                DevolveWriteOffResultInfo dwfReusltInfo = hashDWFResultInfo.get(key);
                if (dwfReusltInfo == null) {
                 	dwfReusltInfo = new DevolveWriteOffResultInfo();
                 	dwfReusltInfo.setCompanyOrgUnitID(ui.getCompanyOrgUnitID());
                 	dwfReusltInfo.setWriteOffStandard(writeOffStandard.getValue());
                 	dwfReusltInfo.setApportionRule(appRule.getValue());
                 	dwfReusltInfo.setDevInWarehsList(new ArrayList<DevolveInWarehsBillInfo>());
                 	dwfReusltInfo.setDevMaterialReqList(new ArrayList<DevolveMatReqBillInfo>());
                 	hashDWFResultInfo.put(key, dwfReusltInfo);
                 }
                 List<DevolveMatReqBillInfo> DevolveMatReq = dwfReusltInfo.getDevMaterialReqList();
                 DevolveMatReq.add(vo);
            }
        }
        //------------开始按委外订单分组核销，并自动计算委外发料单的核销数量
        
        //分页查询，提高性能
        Set<String> setKey = hashDWFResultInfo.keySet();
        String[] aryKey = new String[setKey.size()];
        aryKey = setKey.toArray(aryKey);
        int pageMaxSize = 30;
        int size = aryKey.length;
        int pageCount = size / pageMaxSize + 1;
        //已核销委外入库单的核销数量SQL  委外订单号-物料编码-已核销数量
        StringBuilder subSqlInWaresh = new StringBuilder();
        subSqlInWaresh.append("select a.FPurOrderNumber as subOrderNum,c.FNumber as materialNum,isnull(sum(a.FSCWrittenOffQty),0) as writtenOffQty ")
        .append(" from T_IM_PurInWarehsEntry a")
        .append(" left join t_im_purinwarehsbill b on a.FParentID=b.FID")
        .append(" left join t_bd_material c on c.fid=a.FMaterialID")
        .append(" where b.FBaseStatus>=4 and a.FSCWrittenOffQty>=0")
        .append(" and (1=1)" ).append(" group by a.FPurOrderNumber,c.fnumber ");
        
        /*
         select a.FPurOrderNumber as subOrderNum,c.FNumber as materialNum,isnull(sum(a.FSCWrittenOffQty),0) as writtenOffQty from T_IM_PurInWarehsEntry a 
		left join t_im_purinwarehsbill b on a.FParentID=b.FID
		left join t_bd_material c on c.fid=a.FMaterialID
		where b.FBaseStatus>=4 and a.FSCWrittenOffQty>=0
		and a.FPurOrderNumber='xxxx'
		and c.FNumber='xxx'
		group by a.FPurOrderNumber,c.fnumber 
         */
        
        //委外订单数量
        StringBuilder subSqlSubOrder = new StringBuilder();
        subSqlSubOrder.append("select b.fnumber as subOrderNum,c.fnumber as materialNum,isnull(SUM(a.FQty),0) as subOrderQty ")
        .append(" from T_SM_SubContractOrderEntry a")
        .append(" left join T_SM_SubContractOrder b on b.FID=a.FParentID")
        .append(" left join t_bd_material c on c.fid=a.FMaterialID")
        .append(" where b.FBaseStatus>=4 and (1=1)")
        .append(" group by b.fnumber,c.fnumber");
        
        /** --委外订单
			select b.fnumber as subOrderNum,c.fnumber as materialNum,isnull(SUM(a.FQty),0) as subOrderQty from T_SM_SubContractOrderEntry a
			left join T_SM_SubContractOrder b on b.FID=a.FParentID
			left join t_bd_material c on c.fid=a.FMaterialID
			 where b.FBaseStatus>=4 and b.fnumber='WW-000011'
			 and c.FNumber='xxxx'
			 group by b.fnumber,c.fnumber 
         */
        
        HashMap<String,BigDecimal> hashInWareshValues = new HashMap<String, BigDecimal>();
        HashMap<String,BigDecimal> hashSubOrderValues = new HashMap<String, BigDecimal>();
        for (int i = 0; i < pageCount; i++) {
        	StringBuilder subWhereInWaresh = new StringBuilder();
        	StringBuilder subWhereSubOrder = new StringBuilder();
        	for (int j = i*pageMaxSize; j< size && j < (i+1)*pageMaxSize; j++) {
        		String[] key = aryKey[j].split(";");
        		String coreBillNum = key[0];
        		String materialNum = key[1];
        		subWhereInWaresh.append(String.format("(a.FPurOrderNumber='%s' and c.FNumber='%s') or ",coreBillNum,materialNum));
        		subWhereSubOrder.append(String.format("(b.FNumber='%s' and c.FNumber='%s') or ",coreBillNum,materialNum));
        	}
        	String whereInWaresh = subWhereInWaresh.substring(0,subWhereInWaresh.lastIndexOf("or"));
        	String whereSubOrder = subWhereSubOrder.substring(0,subWhereSubOrder.lastIndexOf("or"));
        	
        	String sqlInWaresh = subSqlInWaresh.toString().replace("1=1", whereInWaresh);
        	String sqlSubOrder = subSqlSubOrder.toString().replace("1=1", whereSubOrder);
        	
        	SQLExecutor execInWaresh = new SQLExecutor(sqlInWaresh);
        	SQLExecutor execSubOrder = new SQLExecutor(sqlSubOrder);
        	IRowSet rsInWaresh = execInWaresh.executeSQL();
        	while (rsInWaresh.next()) {
        		String subOrderNum = rsInWaresh.getString("subOrderNum");
        		String materialNum = rsInWaresh.getString("materialNum");
        		BigDecimal writtenOffQty = rsInWaresh.getBigDecimal("writtenOffQty");
        		String key = subOrderNum + ";"+ materialNum;
        		hashInWareshValues.put(key, writtenOffQty);
        	}
        	
        	IRowSet rsSubOrder = execSubOrder.executeSQL();
        	while (rsSubOrder.next()) {
        		String subOrderNum = rsSubOrder.getString("subOrderNum");
        		String materialNum = rsSubOrder.getString("materialNum");
        		BigDecimal subOrderQty = rsSubOrder.getBigDecimal("subOrderQty");
        		String key = subOrderNum + ";"+ materialNum;
        		hashSubOrderValues.put(key, subOrderQty);
        	}
        }

        
        Iterator itKey = setKey.iterator();
        while (itKey.hasNext()) {
        	String key = (String) itKey.next();
        	DevolveWriteOffResultInfo info = hashDWFResultInfo.get(key);
        	String subOrderNum = key.split(";")[0];
        	String materialNum = key.split(";")[1];
        	List<DevolveInWarehsBillInfo> lstDIW = info.getDevInWarehsList();
        	BigDecimal inWarehsQty = BigDecimal.ZERO;
        	for (int i = 0; i < lstDIW.size(); i++) {
        		DevolveInWarehsBillInfo diwInfo = lstDIW.get(i);
        		inWarehsQty = inWarehsQty.add(diwInfo.getQty()); //选中入库数量
        	}

        	//委外发料单.本次核销数量=(委外入库单.已核销数量+选中委外入库单)/委外订单.数量*委外发料单.数量-委外发料单.已核销数量
        	List<DevolveMatReqBillInfo> lstDMR  = info.getDevMaterialReqList();
        	for (int i = 0; i <  lstDMR.size(); i++) {
        		DevolveMatReqBillInfo dmrInfo = lstDMR.get(i);
        		BigDecimal writtenOffQty = dmrInfo.getDevWriteOffBaseQty(); //已核销数量
        		if (writtenOffQty == null) writtenOffQty = BigDecimal.ZERO;
        		BigDecimal reqQty = dmrInfo.getQty(); //委外发料单数量
        		BigDecimal totalInWarehQty = hashInWareshValues.get(key); //已核销入库数
        		totalInWarehQty = totalInWarehQty.add(inWarehsQty); //已核销入库数+选中入库数量
        		BigDecimal subContactQty = hashSubOrderValues.get(key); //委外订单数量
        		
        		BigDecimal currentWrittenOff = (totalInWarehQty.divide(subContactQty).multiply(reqQty)).subtract(writtenOffQty);
      
        		dmrInfo.setCurWriteOffBaseQty(currentWrittenOff);
        		if (totalInWarehQty.compareTo(subContactQty) > 0) {
        			errorMsg.append(String.format("委外订单号：%s,入库数量大于订单数量，请手工核销",subOrderNum ));
        			break;
        		}
        		
        	}
        	if (errorMsg.length()>10) {
        		MsgBox.showDetailAndOK(ui, "多单核销失败", errorMsg.toString(), MsgBox.OK);
        		return;
        	}
        	
        	
        }
        

        IDevolveWriteOffFacade devWriteOffFacade = DevolveWriteOffFacadeFactory.getRemoteInstanceWithObjectContext(ctx);

        ReturnInfo writeOffResult = devWriteOffFacade.batchDevolveWriteOff(hashDWFResultInfo);
        if(writeOffResult.isSuccess())
        {
            ui.actionRefresh_actionPerformed(null);
            StringBuffer detailMessage = new StringBuffer();
            String errMsg = EASResource.getString("com.kingdee.eas.scm.cal.ManualWriteOffResource.WriteOffError");
            String numberStr = writeOffResult.getUnWriteOffInWarehsNums();
            if(numberStr != null && numberStr.length() > 0)
            {
                detailMessage.append("下列单据：\n").append(numberStr).append("\n");
                detailMessage.append(EASResource.getString("com.kingdee.eas.scm.cal.ManualWriteOffResource.NotEnoughMaterial"));
                MsgBox.showDetailAndOK(ui, errMsg, detailMessage.toString(), 8188);
                return;
            }
            if(materialIdList.size() > 0)
            {
                detailMessage.append("下列物料：\n");
                for(int i = 0; i < materialIdList.size(); i++)
                    detailMessage.append((String)materialIdList.get(i)).append("\n");

                detailMessage.append(EASResource.getString("com.kingdee.eas.scm.cal.ManualWriteOffResource.RedAndBlueIsNotSameQty"));
                MsgBox.showDetailAndOK(ui, errMsg, detailMessage.toString(), 8188);
                return;
            }
            showMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "writeOffSuccess");
        } else
        {
            showMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "writeOffAlert");
        }
        
	}
	
	 private void showMessage(String resourcePath, String key) {
		ui.setMessageText(EASResource.getString((new StringBuilder()).append(
				resourcePath).append(key).toString()));
		ui.showMessage();
	}
	
	private DevolveInWarehsBillInfo buildDevolveInWarehsInfo(IRow row)
	    {
	        String entryId = (String)row.getCell("entryId").getValue();
	        DevolveInWarehsBillInfo info = new DevolveInWarehsBillInfo(entryId);
	        info.setCurWriteOffBaseQty((BigDecimal)row.getCell("curWriteOffBaseQty").getValue());
	        info.setBillId((String)row.getCell("billId").getValue());
	        info.setNumber((String)row.getCell("number").getValue());
	        info.setBizDate((Timestamp)row.getCell("bizDate").getValue());
	        info.setBillType((String)row.getCell("billType").getValue());
	        info.setQty((BigDecimal)row.getCell("qty").getValue());
	        info.setBaseQty((BigDecimal)row.getCell("baseQty").getValue());
	        info.setMaterialCost((BigDecimal)row.getCell("materialCost").getValue());
	        info.setUnitMaterialCost((BigDecimal)row.getCell("unitMaterialCost").getValue());
	        info.setCoreBillType((String)row.getCell("coreBillType").getValue());
	        info.setCoreBillNumber((String)row.getCell("coreBillNumber").getValue());
	        info.setCoreBillEntrySeq((new Integer(row.getCell("coreBillEntrySeq").getValue().toString())).intValue());
	        info.setCoreBillId((String)row.getCell("coreBillId").getValue());
	        info.setCoreBillEntryId((String)row.getCell("coreBillEntryId").getValue());
	        info.setMaterialId((String)row.getCell("materialId").getValue());
	        info.setCompanyOrgUnitID((String)row.getCell("companyOrgUnitId").getValue());
	        info.setStorageOrgUnitId((String)row.getCell("storageOrgUnitId").getValue());
	        info.setWareHouseID((String)row.getCell("wareHouseID").getValue());
	        info.setPrecision((new Integer(row.getCell("precision").getValue().toString())).intValue());
	        info.setPricePrecision((new Integer(row.getCell("pricePrecision").getValue().toString())).intValue());
	        info.setQtyPrecision((new Integer(row.getCell("qtyPrecision").getValue().toString())).intValue());
	        info.setBaseQtyPrecision((new Integer(row.getCell("baseQtyPrecision").getValue().toString())).intValue());
	        info.setBaseUnitID((String)row.getCell("baseUnitId").getValue());
	        info.setYear((new Integer(row.getCell("bizYear").getValue().toString())).intValue());
	        info.setPeriod((new Integer(row.getCell("bizPeriod").getValue().toString())).intValue());
	        info.setDevUnWriteOffBaseQty((BigDecimal)row.getCell("unWriteOffBaseQty").getValue());
	        info.setDevUnWriteOffQty((BigDecimal)row.getCell("unWriteOffQty").getValue());
	        info.setDevWriteOffBaseQty((BigDecimal)row.getCell("writeOffBaseQty").getValue());
	        info.setDevWriteOffQty((BigDecimal)row.getCell("writeOffQty").getValue());
	        info.setLot((String)row.getCell("lot").getValue());
	        info.setSeq((new Integer(row.getCell("seq").getValue().toString())).intValue());
	        Integer isPresent = new Integer(0);
	        if(row.getCell("isPresent").getValue() != null)
	            isPresent = new Integer(row.getCell("isPresent").getValue().toString());
	        info.setPresent(isPresent.intValue() == 1);
	        info.setFiVouchered((new Integer(null != row.getCell("fiVouchered").getValue() ? row.getCell("fiVouchered").getValue().toString() : "0")).intValue() == 1);
	        info.setTransactionTypeID((String)row.getCell("transactionTypeID").getValue());
	        info.setSupplierId((String)row.getCell("supplierId").getValue());
	        info.setReverseQty((BigDecimal)row.getCell("reverseQty").getValue());
	        boolean isReversed = false;
	        if(row.getCell("isReversed").getValue() != null)
	            isReversed = (new BigDecimal(row.getCell("isReversed").getValue().toString())).compareTo(CalculateUtil.ZERO) != 0;
	        info.setReversed(isReversed);
	        info.setTax((BigDecimal)row.getCell("tax").getValue());
	        info.setLocalTax((BigDecimal)row.getCell("localTax").getValue());
	        info.setExchangeRate((BigDecimal)row.getCell("exchangeRate").getValue());
	        info.setCurrencyID((String)row.getCell("currencyID").getValue());
	        info.setBaseConvsRate((BigDecimal)row.getCell("baseConvsRate").getValue());
	        return info;
	    }

	    private DevolveMatReqBillInfo buildDevolveMatReqInfo(IRow row)
	    {
	        String entryId = (String)row.getCell("entryId").getValue();
	        DevolveMatReqBillInfo info = new DevolveMatReqBillInfo(entryId);
	        BigDecimal curWriteOffBaseQty = row.getCell("curWriteOffBaseQty").getValue() != null ? (BigDecimal)row.getCell("curWriteOffBaseQty").getValue() : CalculateUtil.ZERO;
	        info.setCurWriteOffBaseQty(curWriteOffBaseQty);
	        info.setBillId((String)row.getCell("billId").getValue());
	        info.setNumber((String)row.getCell("number").getValue());
	        info.setBizDate((Timestamp)row.getCell("bizDate").getValue());
	        info.setBillType((String)row.getCell("billType").getValue());
	        info.setQty((BigDecimal)row.getCell("qty").getValue());
	        info.setBaseQty((BigDecimal)row.getCell("baseQty").getValue());
	        info.setActualCost((BigDecimal)row.getCell("actualCost").getValue());
	        info.setUnitActualCost((BigDecimal)row.getCell("unitActualCost").getValue());
	        info.setCoreBillType((String)row.getCell("coreBillType").getValue());
	        info.setCoreBillNumber((String)row.getCell("coreBillNumber").getValue());
	        info.setCoreBillEntrySeq((new Integer(row.getCell("coreBillEntrySeq").getValue().toString())).intValue());
	        info.setCoreBillId((String)row.getCell("coreBillId").getValue());
	        info.setCoreBillEntryId((String)row.getCell("coreBillEntryId").getValue());
	        info.setMaterialId((String)row.getCell("materialId").getValue());
	        info.setCompanyOrgUnitID((String)row.getCell("companyOrgUnitId").getValue());
	        info.setStorageOrgUnitId((String)row.getCell("storageOrgUnitId").getValue());
	        info.setWareHouseID((String)row.getCell("wareHouseID").getValue());
	        info.setPrecision((new Integer(row.getCell("precision").getValue().toString())).intValue());
	        info.setPricePrecision((new Integer(row.getCell("pricePrecision").getValue().toString())).intValue());
	        info.setQtyPrecision((new Integer(row.getCell("qtyPrecision").getValue().toString())).intValue());
	        info.setBaseQtyPrecision((new Integer(row.getCell("baseQtyPrecision").getValue().toString())).intValue());
	        info.setBaseUnitID((String)row.getCell("baseUnitId").getValue());
	        info.setYear((new Integer(row.getCell("bizYear").getValue().toString())).intValue());
	        info.setPeriod((new Integer(row.getCell("bizPeriod").getValue().toString())).intValue());
	        info.setDevUnWriteOffBaseQty((BigDecimal)row.getCell("unWriteOffBaseQty").getValue());
	        info.setDevUnWriteOffQty((BigDecimal)row.getCell("unWriteOffQty").getValue());
	        info.setDevWriteOffBaseQty((BigDecimal)row.getCell("writeOffBaseQty").getValue());
	        info.setDevWriteOffQty((BigDecimal)row.getCell("writeOffQty").getValue());
	        info.setHasWriteOffBaseQty(CalculateUtil.ZERO);
	        info.setHasWriteOffQty(CalculateUtil.ZERO);
	        info.setCurrWriteOffTotalAmount(CalculateUtil.ZERO);
	        info.setDevWriteOffAmount((BigDecimal)row.getCell("writeOffAmount").getValue());
	        info.setDevUnWriteOffAmount((BigDecimal)row.getCell("unWriteOffAmount").getValue());
	        info.setLot((String)row.getCell("lot").getValue());
	        info.setSeq((new Integer(row.getCell("seq").getValue().toString())).intValue());
	        Integer isPresent = new Integer(0);
	        if(row.getCell("isPresent").getValue() != null)
	            isPresent = new Integer(row.getCell("isPresent").getValue().toString());
	        info.setPresent(isPresent.intValue() == 1);
	        info.setFiVouchered((new Integer(null != row.getCell("fiVouchered").getValue() ? row.getCell("fiVouchered").getValue().toString() : "0")).intValue() == 1);
	        info.setTransactionTypeID((String)row.getCell("transactionTypeID").getValue());
	        info.setSupplierId((String)row.getCell("supplierId").getValue());
	        info.setReverseQty((BigDecimal)row.getCell("reverseQty").getValue());
	        boolean isReversed = false;
	        if(row.getCell("isReversed").getValue() != null)
	            isReversed = (new BigDecimal(row.getCell("isReversed").getValue().toString())).compareTo(CalculateUtil.ZERO) != 0;
	        info.setReversed(isReversed);
	        info.setBaseConvsRate((BigDecimal)row.getCell("baseConvsRate").getValue());
	        return info;
	    }
	    
	protected boolean isSelected(KDTable tbl) {
		boolean flag = false;
		int count = tbl.getBody().getRows().size();
		IRow row = null;
		int i = 0;
		do {
			if (i >= count)break;
			row = tbl.getRow(i);
			if (row.getCell("selected").getValue().equals(Boolean.TRUE)) {
				flag = true;
				break;
			}
			i++;
		} while (true);
		return flag;
	}
	/*
    private boolean beforeWriteOffCheck(boolean topSelected, boolean botSelected)
    {
        Map topMap = ui.getMaterialTotalQty(topKdtable, "materialNumber");
        Map botMap = ui.getMaterialTotalQty(bottomKdTable, "materialNumber");
        java.util.List errList = new ArrayList();
        materialIdList.clear();
        if(writeOffStandard == DevolveWriteOffStandardEnum.MANUAL_WRITEOFF)
        {
            if(!topSelected && !botSelected)
            {
                showWarningMessage("com.kingdee.eas.framework.FrameWorkResource.", "Msg_MustSelected");
                return false;
            }
            if(!isCompanyOrgUnit(topKdtable, bottomKdTable))
            {
                showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "companyUnitAlert");
                return false;
            }
            if(topSelected && !ui.isSameQtySign(topMap))
            {
                showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "upIsNotSameQtySign");
                return false;
            }
            if(topSelected && !isAmountZero(topKdtable))
            {
                showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "ExistAmountZero");
                return false;
            }
            if(topSelected && botSelected && isSameSign && !isSameSignBill(bottomKdTable))
            {
                showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "allIsNotSameQtySign");
                return false;
            }
            if(topSelected && botSelected && !ui.isSameSignUpAndDown(botMap))
            {
                showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "upAndDownIsNotSameQtySign");
                return false;
            }
            if(!topSelected && botSelected && !ui.isSameQty(botMap))
            {
                showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "RedAndBlueIsNotSameQty");
                return false;
            }
            if(topSelected && !botSelected)
            {
                showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "NotSupportInWarehsSelfWriteOff");
                return false;
            }
            if(topSelected && botSelected && !isSameProcessCompany(topKdtable, bottomKdTable))
            {
                showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "NotSameSupplier");
                return false;
            }
        } else
        if(writeOffStandard == DevolveWriteOffStandardEnum.SELF_WRITEOFF)
        {
            if(!topSelected && !botSelected)
            {
                showWarningMessage("com.kingdee.eas.framework.FrameWorkResource.", "Msg_MustSelected");
                return false;
            }
            if(!isCompanyOrgUnit(topKdtable, bottomKdTable))
            {
                showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "companyUnitAlert");
                return false;
            }
        } else
        {
            if(!topSelected)
            {
                showWarningMessage("com.kingdee.eas.framework.FrameWorkResource.", "Msg_MustSelected");
                return false;
            }
            if(!isCompanyOrgUnit(topKdtable, bottomKdTable))
            {
                showWarningMessage("com.kingdee.eas.scm.cal.ManualWriteOffResource.", "companyUnitAlert");
                return false;
            }
            if(!isExistCoreBill(topKdtable, errList))
            {
                String errBillNumber = "";
                for(int i = 0; i < errList.size(); i++)
                    errBillNumber = (new StringBuilder()).append(errBillNumber).append(errList.get(i)).append("\n").toString();

                String message = EASResource.getString("com.kingdee.eas.scm.cal.ManualWriteOffResource.NotExistCoreBill");
                MsgBox.showWarning(ui, (new StringBuilder()).append(errBillNumber).append(message).toString());
                return false;
            }
        }
        return true;
    }*/
    /*
    private boolean isExistCoreBill(KDTable tbl, java.util.List errList) {
        int count = tbl.getBody().getRows().size();
        IRow row = null;
        for(int i = 0; i < count; i++)
        {
            row = tbl.getRow(i);
            if(row.getCell("selected").getValue() != null && Boolean.TRUE.equals(row.getCell("selected").getValue()) && row.getCell("coreBillEntryId").getValue() == null)
                errList.add(row.getCell("number").getValue());
        }

        return errList.size() == 0;
    }
   
    private boolean isSameProcessCompany(KDTable topTable, KDTable botTable) {
		boolean isSame = true;
		int topCount = topTable.getBody().getRows().size();
		int botCount = botTable.getBody().getRows().size();
		Object tmpObj = null;
		IRow row = null;
		for (int i = 0; i < topCount; i++) {
			row = topTable.getRow(i);
			if (row.getCell("selected").getValue() == null
					|| !Boolean.TRUE.equals(row.getCell("selected").getValue()))
				continue;
			if (tmpObj == null) {
				tmpObj = row.getCell("processCompany").getValue();
				continue;
			}
			if (!tmpObj.equals(row.getCell("processCompany").getValue())) {
				isSame = false;
				return isSame;
			}
		}

		for (int j = 0; j < botCount; j++) {
			row = botTable.getRow(j);
			if (row.getCell("selected").getValue() == null
					|| !Boolean.TRUE.equals(row.getCell("selected").getValue()))
				continue;
			if (tmpObj == null) {
				tmpObj = row.getCell("SuppliersName").getValue();
				continue;
			}
			if (!tmpObj.equals(row.getCell("SuppliersName").getValue())) {
				isSame = false;
				return isSame;
			}
		}

		return isSame;
	}
   
    private boolean isSameSignBill(KDTable tbl) {
        int count = tbl.getBody().getRows().size();
        IRow row = null;
        BigDecimal qtyTotal = null;
        for(int i = 0; i < count; i++)
        {
            row = tbl.getRow(i);
            if(row.getCell("selected").getValue() == null || !Boolean.TRUE.equals(row.getCell("selected").getValue()))
                continue;
            qtyTotal = row.getCell("curWriteOffBaseQty").getValue() != null ? (BigDecimal)row.getCell("curWriteOffBaseQty").getValue() : CalculateUtil.ZERO;
            if(topSignNum != qtyTotal.signum())
                return false;
        }

        return true;
    }
      */
    private void showWarningMessage(String resourcePath, String key) {
        String message = EASResource.getString((new StringBuilder()).append(resourcePath).append(key).toString());
        MsgBox.showWarning(ui, message);
    } 
    /*
    protected boolean isCompanyOrgUnit(KDTable topTable, KDTable bomTable)  {
        boolean flag = false;
        String top[] = getSelectedIds(topKdtable, "companyOrgUnitId");
        String bottom[] = getSelectedIds(bottomKdTable, "companyOrgUnitId");
        if(null != top && null != bottom && top.length == 1 && bottom.length == 1 && top[0].equals(bottom[0]))
        {
            ui.setCompanyOrgUnitID(top[0]);
            flag = true;
        } else
        if(null != top && top.length == 1 && bottom == null)
        {
            ui.setCompanyOrgUnitID(top[0]);
            flag = true;
        } else
        if(null != bottom && bottom.length == 1 && top == null)
        {
            ui.setCompanyOrgUnitID(bottom[0]);
            flag = true;
        }
        return flag;
    }
    private String[] getSelectedIds(KDTable tbl, String key)  {
        int count = tbl.getBody().getRows().size();
        IRow row = null;
        HashSet temp = new HashSet();
        for(int i = 0; i < count; i++) {
            row = tbl.getRow(i);
            if(row.getCell("selected").getValue() != null && row.getCell("selected").getValue().equals(Boolean.TRUE))
                temp.add((String)row.getCell(key).getValue());
        }

        String ids[] = null;
        if(!temp.isEmpty()) {
            Object obj[] = temp.toArray();
            int length = obj.length;
            ids = new String[length];
            System.arraycopy(((Object) (obj)), 0, ids, 0, length);
        }
        return ids;
    }
    
    private boolean isAmountZero(KDTable tbl)
    {
        DevolveApportionRuleEnum appRule = (DevolveApportionRuleEnum)cmbApportionRule.getSelectedItem();
        int count = tbl.getBody().getRows().size();
        IRow row = null;
        if(appRule == DevolveApportionRuleEnum.AMOUNT)
        {
            for(int i = 0; i < count; i++)
            {
                row = tbl.getRow(i);
                if(row.getCell("selected").getValue() == null || !Boolean.TRUE.equals(row.getCell("selected").getValue()))
                    continue;
                BigDecimal qtyTotal = (BigDecimal)row.getCell("materialCost").getValue();
                if(qtyTotal == null || qtyTotal.compareTo(CalculateUtil.ZERO) == 0)
                    return false;
            }

        }
        return true;
    }
    */
}
