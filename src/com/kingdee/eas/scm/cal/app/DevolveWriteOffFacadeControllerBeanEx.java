package com.kingdee.eas.scm.cal.app;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.dao.query.*;
import com.kingdee.bos.framework.ejb.EJBFactory;
import com.kingdee.bos.metadata.MetaDataPK;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.basedata.assistant.*;
import com.kingdee.eas.basedata.master.cssp.SupplierInfo;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.org.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.SystemEnum;
import com.kingdee.eas.mm.common.app.SQLUtils;
import com.kingdee.eas.scm.cal.*;
import com.kingdee.eas.scm.cal.app.help.WriteOffHelp;
import com.kingdee.eas.scm.cal.info.*;
import com.kingdee.eas.scm.cal.util.CalculateUtil;
import com.kingdee.eas.scm.cal.util.DBUtil;
import com.kingdee.eas.scm.im.inv.MaterialReqBillEntryFactory;
import com.kingdee.eas.scm.im.inv.MaterialReqBillEntryInfo;
import com.kingdee.eas.scm.im.inv.MaterialReqBillFactory;
import com.kingdee.eas.scm.im.inv.MaterialReqBillInfo;
import com.kingdee.eas.scm.sm.sc.*;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import org.apache.log4j.Logger;

public class DevolveWriteOffFacadeControllerBeanEx extends
		com.kingdee.eas.scm.cal.app.DevolveWriteOffFacadeControllerBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger
			.getLogger("com.kingdee.eas.scm.cal.app.DevolveWriteOffFacadeControllerBeanEx");

	private CompanyOrgUnitInfo companyOrgUnitInfo;
	private PeriodInfo currPeriod;
	private List<String> devWarehsInfoList;

//	protected boolean _inverseWriteOff(Context ctx, String ids[])
//			throws BOSException, EASBizException {		
//		logger.info("========反核销");
//		boolean flg = false;
//		for (int i = 0; i < ids.length; i++) {
//			String billId = ids[i];
//			String sql = "select distinct FBillEntryID from  T_CL_DevolveWriteOffRecord " +
//					"where FBILLTYPENUMBER='104' and FParentId= '"+ billId + "'";
//			logger.info("=====sql = "+sql);
//			IRowSet rs = DBUtil.executeQuery(ctx, sql);
//			String costAdjust="";
//				boolean abc = false;
//				try {
//					abc = rs.next();
//				} catch (SQLException e) {
//					//throw new BOSException(e.toString());
//					throw new EASBizException(new NumericExceptionSubItem("",e.toString()));
//				}
//				while(abc){
//					String maReqBillEntryId;
//					MaterialReqBillEntryInfo materialReqBillEntryInfo =null;
//					try {
//						maReqBillEntryId = rs.getString("FBillEntryID");
//					logger.info("=====maReqBillEntryId = "+maReqBillEntryId);
//					materialReqBillEntryInfo = MaterialReqBillEntryFactory.getLocalInstance(ctx)
//					.getMaterialReqBillEntryInfo(new ObjectUuidPK(BOSUuid.read(maReqBillEntryId)));
//					} catch (SQLException e) {
//						throw new EASBizException(new NumericExceptionSubItem("",e.toString()));
//					}
//					if (materialReqBillEntryInfo != null
//							&& materialReqBillEntryInfo.isIsAdjust()) {
//						String costAdjustNum = materialReqBillEntryInfo
//								.getAdjustNum();
//						CostAdjustBillInfo costAdjustBillInfo = null;
//						try{
//						costAdjustBillInfo = CostAdjustBillFactory
//								.getLocalInstance(ctx).getCostAdjustBillInfo(" where number = '" + costAdjustNum+ "'");
//						if (costAdjust==null || costAdjust.equals(""))
//						costAdjust =costAdjustBillInfo.getNumber()+",";
//						costAdjust +=costAdjustBillInfo.getNumber()+",";
//						} catch (Exception e) {
//						}
////						materialReqBillInfo.setIsAdjust(false);// 核销成本调整
////						MaterialReqBillFactory.getLocalInstance(ctx).update(
////								new ObjectUuidPK(materialReqBillInfo.getId()),
////								materialReqBillInfo);
//					}
//					try {
//						abc =rs.next();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//				if (!costAdjust.equals("")&& costAdjust!= null) {
//					/*
//												CostAdjustBillFactory.getLocalInstance(ctx)
//														.delete(
//																new ObjectUuidPK(costAdjustBillInfo
//																		.getId()));						
//											*/
//				costAdjust= costAdjust.substring(0, costAdjust.length()-1);
//				throw new EASBizException(new NumericExceptionSubItem("","请删除成本调整单"+costAdjust.toString()));
//											}
//     flg = super._inverseWriteOff(ctx, ids);
//		}
//		return flg;
////		return false;
//	}

	/**
	 * 设置期间
	 * 
	 * @param ctx
	 * @param info
	 * @throws BOSException
	 * @throws EASBizException
	 */
	@SuppressWarnings("unchecked")
	private void setWriteOffMaxBizPeriod(Context ctx,
			DevolveWriteOffResultInfo info) throws BOSException,
			EASBizException {
		// 委外入库单list
		List devInWarehsList = info.getDevInWarehsList();
		// 委外发料单list
		List devMatReqList = info.getDevMaterialReqList();

		/* 获取当前期间起始日期和入库单业务日期和发料单业务日期 3个日期的最大值 */
		// 获取财务组织的期间
		currPeriod = SystemStatusCtrolUtils.getCurrentPeriod(ctx,
				SystemEnum.INVENTORYMANAGEMENT, companyOrgUnitInfo);
		Timestamp maxBizDate = CalculateUtil.getTime(currPeriod.getBeginDate());
		Timestamp bizDate = null;
		for (int i = 0; i < devInWarehsList.size(); i++) {
			bizDate = ((DevolveInWarehsBillInfo) devInWarehsList.get(i))
					.getBizDate();
			maxBizDate = maxBizDate.compareTo(bizDate) < 0 ? bizDate
					: maxBizDate;
		}

		for (int i = 0; i < devMatReqList.size(); i++) {
			bizDate = ((DevolveMatReqBillInfo) devMatReqList.get(i))
					.getBizDate();
			maxBizDate = maxBizDate.compareTo(bizDate) < 0 ? bizDate
					: maxBizDate;
		}
		/* 获取当前期间起始日期和入库单业务日期和发料单业务日期 3个日期的最小值 */
		info.setBizDate(maxBizDate);// 业务日期

		PeriodInfo bizPeriod = PeriodUtils.getPeriodInfo(ctx, maxBizDate,
				companyOrgUnitInfo, SystemEnum.INVENTORYMANAGEMENT);
		// 是否为调整期间
		if (bizPeriod.isIsAdjustPeriod()) {
			bizPeriod = PeriodUtils.getNextPeriodInfo(bizPeriod);
		}
		info.setYear(bizPeriod.getPeriodYear());// 核销年度
		info.setPeriod(bizPeriod.getPeriodNumber());// 核销期间

	}

	/**
	 * 生成核销记录
	 * 
	 * @param ctx
	 * @param info
	 * @return
	 */
	private DevolveWriteOffGroupInfo createWrittenOffGroup(Context ctx,
			DevolveWriteOffResultInfo info) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		// 委外核销记录
		DevolveWriteOffGroupInfo groupInfo = new DevolveWriteOffGroupInfo();
		groupInfo.setId(BOSUuid.create(groupInfo.getBOSType()));// id
		groupInfo.setNumber(format.format(date));// 编码(以当前时间)
		groupInfo.setCompanyOrgUnit(companyOrgUnitInfo);// 财务组织
		groupInfo.setCalculateKind(CalculateKindEnum.INPUT_WAREHOUSE);// 出入库类型
		groupInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));// 创建时间
		groupInfo.setWriteOffDate(date);// 核销日期
		groupInfo.setBizDate(info.getBizDate());// 业务日期
		groupInfo.setWriteOffYear(info.getYear());// 核销年度
		groupInfo.setWriteOffPeriod(info.getPeriod());// 核销期间
		groupInfo.setCreator(ContextUtil.getCurrentUserInfo(ctx));// 创建者
		groupInfo.setCU(ContextUtil.getCurrentCtrlUnit(ctx));// cu
		groupInfo.setFiVouchered(false);// 是否生成凭证
		groupInfo.setCreateType(getMatCostWriteOffType(info
				.getWriteOffStandard()));// 创建类型(发料核销创建类型)
		return groupInfo;
	}

	/**
	 * 获取创建类型
	 * 
	 * @param writeoffStandard
	 * @return
	 */
	private MatCostWriteOffTypeEnum getMatCostWriteOffType(int writeoffStandard) {
		// 发料核销创建类型
		switch (writeoffStandard) {
		case 1: // '\001'手工核销
			return MatCostWriteOffTypeEnum.ManualWriteOff;
		case 2: // '\002'单向核销
			return MatCostWriteOffTypeEnum.SelfWriteOff;
		case 3: // '\003'按发料清单核销
			return MatCostWriteOffTypeEnum.OrderWriteOff;
		}
		return null;
	}

	/**
	 * 反写入库单
	 * 
	 * @param ctx
	 * @param info
	 * @throws BOSException
	 */
	@SuppressWarnings("unchecked")
	private void saveDevolveInWarehsBillInfo(Context ctx,
			DevolveWriteOffResultInfo info) throws BOSException {
		logger.info("=====更新入库单=====");
		List devInWarehsBillList;
		StringBuffer updateSQL;
		int curPeriodNumber;
		StringBuffer updateMaterialCostSQL;
		int count;
		String updateActualCost;
		Connection conn;
		PreparedStatement pm;
		PreparedStatement pm1;
		PreparedStatement pm2;
		devInWarehsBillList = info.getDevInWarehsList();
		updateSQL = new StringBuffer();
		updateSQL.append("update T_IM_PurInWarehsEntry set ");
		updateSQL
				.append(" FSCWrittenOffQty = ?, FSCWrittenOffBaseQty = ?, FSCUnWrittenOffQty = ?, FSCUnWrittenOffBaseQty = ? ");
		updateSQL.append(" where FID = ?");
		curPeriodNumber = currPeriod.getPeriodYear() * 100
				+ currPeriod.getPeriodNumber();
		updateMaterialCostSQL = new StringBuffer();
		updateMaterialCostSQL
				.append(" update T_IM_PurInWarehsEntry set FMaterialCost = ?, FUnitMaterialCost = ? ");
		updateMaterialCostSQL.append(" where FID = ?");
		int billPeriod = 0;
		count = 0;
		updateActualCost = WriteOffHelp.getCalcPurActualCostSQL();
		conn = null;
		pm = null;
		pm1 = null;
		pm2 = null;
		DevolveInWarehsBillInfo devInWarehsInfo = null;
		try {
			conn = EJBFactory.getConnection(ctx);
			logger.info("=====updateSQL = " + updateSQL);
			logger
					.info("=====updateMaterialCostSQL = "
							+ updateMaterialCostSQL);
			logger.info("=====updateActualCost = " + updateActualCost);
			pm = conn.prepareStatement(updateSQL.toString());
			pm1 = conn.prepareStatement(updateMaterialCostSQL.toString());
			pm2 = conn.prepareStatement(updateActualCost);
			for (int i = 0; i < devInWarehsBillList.size(); i++) {
				devInWarehsInfo = (DevolveInWarehsBillInfo) devInWarehsBillList
						.get(i);
				pm.setBigDecimal(1, devInWarehsInfo.getDevWriteOffQty());
				pm.setBigDecimal(2, devInWarehsInfo.getDevWriteOffBaseQty());
				pm.setBigDecimal(3, devInWarehsInfo.getDevUnWriteOffQty());
				pm.setBigDecimal(4, devInWarehsInfo.getDevUnWriteOffBaseQty());
				pm.setString(5, devInWarehsInfo.getEntryId());
				pm.addBatch();
				billPeriod = devInWarehsInfo.getYear() * 100
						+ devInWarehsInfo.getPeriod();
				if (!devInWarehsInfo.isFiVouchered()
						&& billPeriod >= curPeriodNumber) {
					pm1.setBigDecimal(1, devInWarehsInfo.getMaterialCost());
					pm1.setBigDecimal(2, devInWarehsInfo.getUnitMaterialCost());
					pm1.setString(3, devInWarehsInfo.getEntryId());
					pm1.addBatch();
					pm2.setString(1, devInWarehsInfo.getEntryId());
					pm2.setString(2, devInWarehsInfo.getEntryId());
					pm2.addBatch();
					count++;
				}
			}

			pm.executeBatch();
			if (count > 0) {
				pm1.executeBatch();
				pm2.executeBatch();
			}
		} catch (SQLException ex) {
			throw new BOSException(ex);
		} finally {
			SQLUtils.cleanup(pm);
			SQLUtils.cleanup(pm1);
			SQLUtils.cleanup(pm2, conn);
		}
	}

	/**
	 * 反写发料单
	 * 
	 * @param ctx
	 * @param devMatReqBillList
	 * @throws BOSException
	 */
	@SuppressWarnings("unchecked")
	private void saveDevolveMatReqBillInfo(Context ctx, List devMatReqBillList)
			throws BOSException {
		logger.info("=====更新发料单=====");
		StringBuffer updateSQL;
		Connection conn;
		PreparedStatement pm;
		updateSQL = new StringBuffer();
		updateSQL
				.append("update T_IM_MaterialReqBillEntry set FSubWrittenOffQty = ?, FSubWrittenOffBaseQty = ?,");
		updateSQL
				.append("FSubUnWriteOffQty = ?, FSubUnWriteOffBaseQty = ?, FSCWrittenOffAmount = ?, FSCUnWrittenOffAmount = ? ");
		updateSQL.append("where FID = ?");
		conn = null;
		pm = null;
		DevolveMatReqBillInfo devMatReqInfo = null;
		try {
			conn = EJBFactory.getConnection(ctx);
			logger.info("=====updateSQL = " + updateSQL);
			pm = conn.prepareStatement(updateSQL.toString());
			for (int i = 0; i < devMatReqBillList.size(); i++) {
				devMatReqInfo = (DevolveMatReqBillInfo) devMatReqBillList
						.get(i);
				pm.setBigDecimal(1, devMatReqInfo.getDevWriteOffQty());
				pm.setBigDecimal(2, devMatReqInfo.getDevWriteOffBaseQty());
				pm.setBigDecimal(3, devMatReqInfo.getDevUnWriteOffQty());
				pm.setBigDecimal(4, devMatReqInfo.getDevUnWriteOffBaseQty());
				pm.setBigDecimal(5, devMatReqInfo.getDevWriteOffAmount());
				pm.setBigDecimal(6, devMatReqInfo.getDevUnWriteOffAmount());
				pm.setString(7, devMatReqInfo.getEntryId());
				pm.addBatch();
			}
			pm.executeBatch();
		} catch (SQLException ex) {
			throw new BOSException(ex);
		} finally {
			DBUtil.close(conn, pm);
		}
	}

	/**
	 * 反写发料调整单号
	 * 
	 * @param ctx
	 * @param devMatReqBillList
	 * @throws BOSException
	 */
	public boolean updateMaterialReqBill(Context ctx, String costadjustbillId)throws BOSException {
		logger.info("=====反写发料调整单号=====");
		StringBuffer updateSQL;
		Connection conn;
		PreparedStatement pm;
		updateSQL = new StringBuffer();
		updateSQL.append("update t_im_materialreqbillentry a set cfisadjust=1,cfadjustnum=(select distinct c.fnumber ");
		updateSQL.append("from t_cl_costadjustbill c left join t_cl_costadjustbillentry d on c.fid=d.fparentid");
		updateSQL.append(" where d.fsourcebillentryid=a.fid and c.fid=?)");
		updateSQL.append(" where exists (select 1 from t_cl_costadjustbillentry b where b.fsourcebillentryid=a.fid and ");
		updateSQL.append("b.fparentid= ?)");
		conn = null;
		pm = null;
		try {
			conn = EJBFactory.getConnection(ctx);
			logger.info("=====updateSQL = " + updateSQL);
				pm = conn.prepareStatement(updateSQL.toString());
				pm.setString(1,costadjustbillId);
				pm.setString(2,costadjustbillId);
				pm.addBatch();
				pm.executeBatch();
		} catch (SQLException ex) {
			throw new BOSException(ex);
		} finally {
			DBUtil.close(conn, pm);
		}
		return false;
	}
	/**
	 * 批量核销
	 */
	@Override
	protected ReturnInfo _batchDevolveWriteOff(Context ctx, HashMap hashInfo)
			throws BOSException, EASBizException {
		ReturnInfo returnInfo = null;
		Collection<DevolveWriteOffResultInfo> colInfo = hashInfo.values();
		Iterator<DevolveWriteOffResultInfo> itInfo = colInfo.iterator();
		while(itInfo.hasNext()) { 
			DevolveWriteOffResultInfo info = itInfo.next();
			ReturnInfo rInfo = _devolveWriteOff(ctx, info);
			if (returnInfo == null) returnInfo = rInfo;
			else {
				returnInfo.setSuccess(returnInfo.isSuccess() && rInfo.isSuccess());
				returnInfo.setUnWriteOffInWarehsNums(returnInfo.getUnWriteOffInWarehsNums() + returnInfo.getUnWriteOffInWarehsNums());
			}			
		}
		
		return returnInfo;
	}
	/**
	 * 核销
	 * 
	 * @param ctx
	 * @param info
	 *            核销信息集合info
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	protected ReturnInfo _devolveWriteOff(Context ctx,
			DevolveWriteOffResultInfo info) throws BOSException,
			EASBizException {
		logger.info("=====核销=====");
		boolean isSuccess = false;
		ReturnInfo returnInfo = new ReturnInfo();
		devWarehsInfoList = new ArrayList<String>();
		if (info.getCompanyOrgUnitID() == null
				|| info.getCompanyOrgUnitID().length() == 0) {
			// 发票上的财务组织不存在或库存单据上库存组织的记账委托组织不存在
			throw new CalculateException(
					CalculateException.FINANCE_ORG_NOT_EXIST);
		}
		// 获取财务组织
		companyOrgUnitInfo = CompanyOrgUnitFactory.getLocalInstance(ctx)
				.getCompanyOrgUnitInfo(
						new ObjectUuidPK(info.getCompanyOrgUnitID()));
		// 设置期间
		setWriteOffMaxBizPeriod(ctx, info);
		// 核销记录
		DevolveWriteOffGroupInfo groupInfo = createWrittenOffGroup(ctx, info);

		if (info.getWriteOffStandard() == 1) {
			// 手工核销
			isSuccess = manualWriteOff(ctx, info, groupInfo);
		} else if (info.getWriteOffStandard() == 2) {
			// 单向核销
			isSuccess = selfWriteOff(ctx, info, groupInfo);
		} else {
			// 按订单核销
			isSuccess = orderWriteOff(ctx, info, groupInfo);
		}
		if (isSuccess && groupInfo.getEntry().size() > 0) {
			logger.info("=====保存反写=====");
			DevolveWriteOffGroupFactory.getLocalInstance(ctx).save(groupInfo);// 保存核销记录
			saveDevolveInWarehsBillInfo(ctx, info);// 反写入库单
			saveDevolveMatReqBillInfo(ctx, info.getDevMaterialReqList());// 反写领料单
		}
		returnInfo.setSuccess(isSuccess);
		returnInfo.setUnWriteOffInWarehsNums(getErrDevInWarehsNums());// 未核销的入库单编码
		return returnInfo;
	}

	/**
	 * 单向核销
	 * 
	 * @param ctx
	 * @param info
	 * @param groupInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean selfWriteOff(Context ctx, DevolveWriteOffResultInfo info,
			DevolveWriteOffGroupInfo groupInfo) {
		List devInWarehsList = info.getDevInWarehsList();
		List devMatReqList = info.getDevMaterialReqList();
		DevolveInWarehsBillInfo devInWarehsInfo = null;
		DevolveMatReqBillInfo devMatReqInfo = null;
		DevolveWriteOffRecordInfo inWarehsRecordInfo = null;
		DevolveWriteOffRecordInfo matReqRecordInfo = null;
		int i = 0;
		BigDecimal writeOffAmount = null;
		for (i = 0; i < devInWarehsList.size(); i++) {
			devInWarehsInfo = (DevolveInWarehsBillInfo) devInWarehsList.get(i);
			// 核销子记录
			inWarehsRecordInfo = createWrittenOffRecord(ctx, devInWarehsInfo,
					groupInfo, i + 1, i + 1);

			inWarehsRecordInfo.setCurrWriteOffQty(devInWarehsInfo
					.getDevUnWriteOffQty());// 本次核销数量
			inWarehsRecordInfo.setCurrWriteOffBaseQty(devInWarehsInfo
					.getDevUnWriteOffBaseQty());// 本次核销基本数量
			writeOffAmount = devInWarehsInfo.getDevUnWriteOffQty().multiply(
					devInWarehsInfo.getUnitMaterialCost()).setScale(
					devInWarehsInfo.getPrecision(), 4);
			if (devInWarehsInfo.getDevUnWriteOffQty().compareTo(
					devInWarehsInfo.getQty()) == 0) {
				writeOffAmount = devInWarehsInfo.getActualCost();
			}
			inWarehsRecordInfo.setCurrWriteOffAmount(writeOffAmount);// 本次核销金额
			inWarehsRecordInfo.setBillUnWriteOffBaseQty(CalculateUtil.ZERO);// 单据未核销基本数量
		}
		// 反写入库单
		setInWarehsWriteOffInfo(devInWarehsList);

		for (int j = 0; j < devMatReqList.size(); j++) {
			devMatReqInfo = (DevolveMatReqBillInfo) devMatReqList.get(j);
			// 核销子记录
			matReqRecordInfo = createWrittenOffRecord(ctx, devMatReqInfo,
					groupInfo, i + j + 1, i + j + 1);
			matReqRecordInfo.setCurrWriteOffQty(devMatReqInfo
					.getDevUnWriteOffQty());// 本次核销数量
			matReqRecordInfo.setCurrWriteOffBaseQty(devMatReqInfo
					.getDevUnWriteOffBaseQty());// 本次核销基本数量
			writeOffAmount = devMatReqInfo.getDevUnWriteOffQty().multiply(
					devMatReqInfo.getUnitActualCost()).setScale(
					devMatReqInfo.getPrecision(), 4);
			if (devMatReqInfo.getDevUnWriteOffQty().compareTo(
					devMatReqInfo.getQty()) == 0) {
				writeOffAmount = devMatReqInfo.getActualCost();
			}
			devMatReqInfo.setCurrWriteOffTotalAmount(writeOffAmount);
			matReqRecordInfo.setCurrWriteOffAmount(writeOffAmount);// 本次核销金额
			matReqRecordInfo.setBillUnWriteOffBaseQty(CalculateUtil.ZERO);// 单据未核销基本数量
		}

		setMatReqWriteOffInfo(devMatReqList);
		return true;
	}

	/**
	 * 
	 * 核销子记录
	 * 
	 * @param ctx
	 * @param calEntry
	 * @param groupInfo
	 * @param groupNO
	 * @param seq
	 * @return
	 */
	private DevolveWriteOffRecordInfo createWrittenOffRecord(Context ctx,
			WriteOffBillInfo calEntry, DevolveWriteOffGroupInfo groupInfo,
			int groupNO, int seq) {
		DevolveWriteOffRecordInfo writeOffRecordInfo = new DevolveWriteOffRecordInfo();
		writeOffRecordInfo.setId(BOSUuid
				.create(writeOffRecordInfo.getBOSType()));// id
		writeOffRecordInfo.setGroupNO(groupNO);// 小组编号
		writeOffRecordInfo.setSeq(seq);// seq
		writeOffRecordInfo.setOrderBillNumber(calEntry.getCoreBillNumber());// 订单号
		writeOffRecordInfo.setOrderBillEntrySeq(calEntry.getCoreBillEntrySeq());// 订单行号
		SupplierInfo supplierinfo = null;
		if (calEntry.getSupplierId() != null) {
			supplierinfo = new SupplierInfo();
			supplierinfo.setId(BOSUuid.read(calEntry.getSupplierId()));
		}
		writeOffRecordInfo.setSupplier(supplierinfo);// 供应商
		MaterialInfo material = null;
		if (calEntry.getMaterialId() != null) {
			material = new MaterialInfo();
			material.setId(BOSUuid.read(calEntry.getMaterialId()));
		}
		writeOffRecordInfo.setMaterial(material);// 物料
		MeasureUnitInfo baseUnit = null;
		if (calEntry.getBaseUnitID() != null) {
			baseUnit = new MeasureUnitInfo();
			baseUnit.setId(BOSUuid.read(calEntry.getBaseUnitID()));
		}
		writeOffRecordInfo.setBaseUnit(baseUnit);// 基本计量单位
		writeOffRecordInfo.setBillID(calEntry.getBillId());// 来源单据ID
		writeOffRecordInfo.setBillEntryID(calEntry.getEntryId());// 来源单据分录ID
		writeOffRecordInfo.setBillNumber(calEntry.getNumber());// 来源单据编号
		writeOffRecordInfo.setBillEntrySeq(calEntry.getSeq());// 来源单据行号
		writeOffRecordInfo.setBillDate(calEntry.getBizDate());// 单据业务日期
		writeOffRecordInfo.setBillTypeNumber(calEntry.getBillType());// 来源单据类型
		writeOffRecordInfo.setTransactionType(calEntry.getTransactionTypeID());// 事务类型
		writeOffRecordInfo.setLot(calEntry.getLot());// 批次
		writeOffRecordInfo.setCompanyOrgUnit(calEntry.getCompanyOrgUnitID());// 财务组织
		writeOffRecordInfo.setCalcutator(ContextUtil.getCurrentUserInfo(ctx));// 核销人
		groupInfo.getEntry().add(writeOffRecordInfo);
		return writeOffRecordInfo;
	}

	/**
	 * 委外入库单
	 * 
	 * @param devInWarehsBillList
	 */
	@SuppressWarnings("unchecked")
	private void setInWarehsWriteOffInfo(List devInWarehsBillList) {
		DevolveInWarehsBillInfo devInWarehsInfo = null;
		for (int i = 0; i < devInWarehsBillList.size(); i++) {
			devInWarehsInfo = (DevolveInWarehsBillInfo) devInWarehsBillList
					.get(i);
			if (devWarehsInfoList.indexOf(devInWarehsInfo.getNumber()) < 0) {
				devInWarehsInfo.setDevWriteOffQty(devInWarehsInfo.getQty());
				devInWarehsInfo.setDevWriteOffBaseQty(devInWarehsInfo
						.getBaseQty());
				devInWarehsInfo.setDevUnWriteOffQty(CalculateUtil.ZERO);
				devInWarehsInfo.setDevUnWriteOffBaseQty(CalculateUtil.ZERO);
			}
		}
	}

	/**
	 * 委外发料单
	 * 
	 * @param devMatReqBillList
	 */
	@SuppressWarnings("unchecked")
	private void setMatReqWriteOffInfo(List devMatReqBillList) {
		DevolveMatReqBillInfo devMatReqInfo = null;
		for (int i = 0; i < devMatReqBillList.size(); i++) {
			devMatReqInfo = (DevolveMatReqBillInfo) devMatReqBillList.get(i);
			devMatReqInfo.setDevWriteOffBaseQty(devMatReqInfo
					.getDevWriteOffBaseQty().add(
							devMatReqInfo.getCurWriteOffBaseQty()));
			devMatReqInfo.setDevUnWriteOffBaseQty(devMatReqInfo.getBaseQty()
					.subtract(devMatReqInfo.getDevWriteOffBaseQty()));
			BigDecimal writeOffQty = devMatReqInfo.getDevWriteOffBaseQty()
					.divide(devMatReqInfo.getBaseConvsRate(),
							devMatReqInfo.getQtyPrecision(), 4);
			// if (devMatReqInfo.getDevWriteOffBaseQty().compareTo(
			// devMatReqInfo.getBaseQty()) == 0) {
			writeOffQty = devMatReqInfo.getQty();
			// }
			devMatReqInfo.setDevWriteOffQty(writeOffQty);
			devMatReqInfo.setDevUnWriteOffQty(devMatReqInfo.getQty().subtract(
					writeOffQty));
			logger.info("=====" + devMatReqInfo.getDevWriteOffAmount());
			if (devMatReqInfo.getDevWriteOffAmount() == null) {
				devMatReqInfo.setDevWriteOffAmount(CalculateUtil.ZERO);
			}
			logger.info("=====" + devMatReqInfo.getCurrWriteOffTotalAmount());
			BigDecimal writeOffAmount = devMatReqInfo.getDevWriteOffAmount()
					.add(devMatReqInfo.getCurrWriteOffTotalAmount());
			logger.info("=====" + writeOffAmount);
			if (devMatReqInfo.getDevWriteOffBaseQty().compareTo(
					devMatReqInfo.getBaseQty()) == 0) {
				writeOffAmount = devMatReqInfo.getActualCost();
			}
			logger.info("=====" + writeOffAmount);
			devMatReqInfo.setDevWriteOffAmount(writeOffAmount);
			devMatReqInfo.setCurrWriteOffTotalAmount(CalculateUtil.ZERO);
			devMatReqInfo.setDevUnWriteOffAmount(devMatReqInfo.getActualCost()
					.subtract(writeOffAmount));
		}

	}

	/**
	 * 手工核销
	 * 
	 * @param ctx
	 * @param info
	 * @param groupInfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean manualWriteOff(Context ctx, DevolveWriteOffResultInfo info,
			DevolveWriteOffGroupInfo groupInfo) {
		//委外入库单
		List devInWarehsList = info.getDevInWarehsList();
		//委外发料单
		List devMatReqList = info.getDevMaterialReqList();
		BigDecimal total = getDevInWarehsTotal(info);
		DevolveWriteOffRecordInfo inWarehsRecordInfo = null;
		DevolveWriteOffRecordInfo matReqRecordInfo = null;
		DevolveInWarehsBillInfo devInWarehsInfo = null;
		DevolveMatReqBillInfo devMatReqInfo = null;
		for (int i = 0; i < devInWarehsList.size(); i++) {
			devInWarehsInfo = (DevolveInWarehsBillInfo) devInWarehsList.get(i);
			BigDecimal rate = null;
			if (info.getApportionRule() == 2) {
				//基本数量比率
				rate = devInWarehsInfo.getBaseQty().divide(total, 10, 4);
			} else {
				//材料成本比率
				rate = devInWarehsInfo.getMaterialCost().divide(total, 10, 4);
			}
			BigDecimal totalWriteOffAmount = CalculateUtil.ZERO;
			//当前核销总金额
			for (int j = 0; j < devMatReqList.size(); j++) {
				devMatReqInfo = (DevolveMatReqBillInfo) devMatReqList.get(j);
				if (devMatReqInfo.getCurrWriteOffTotalAmount() == null) {
					devMatReqInfo
							.setCurrWriteOffTotalAmount(CalculateUtil.ZERO);
				}
				//核销金额
				if (devMatReqInfo.getDevWriteOffAmount() == null) {
					devMatReqInfo.setDevWriteOffAmount(CalculateUtil.ZERO);
				}
				//本次核销数量=本次核销基本数量/基本计量单位转换率(数量精度)
				BigDecimal curWriteOffQty = devMatReqInfo
						.getCurWriteOffBaseQty().divide(
								devMatReqInfo.getBaseConvsRate(),
								devMatReqInfo.getQtyPrecision(), 4);
				
				if (devMatReqInfo.getCurWriteOffBaseQty().compareTo(
						devMatReqInfo.getBaseQty()) == 0) {
					//数量
					curWriteOffQty = devMatReqInfo.getQty();
				}
				//核销基本数量= 比率*本次核销基本数量(基本数量精度)
				BigDecimal writeOffBaseQty = rate.multiply(
						devMatReqInfo.getCurWriteOffBaseQty()).setScale(
						devMatReqInfo.getBaseQtyPrecision(), 4);
				if (writeOffBaseQty.compareTo(CalculateUtil.ZERO) == 0) {
					continue;
				}
				//核销数量=核销基本数量/基本计量单位转换率
				BigDecimal writeOffQty = writeOffBaseQty
						.divide(devMatReqInfo.getBaseConvsRate(), devMatReqInfo
								.getQtyPrecision(), 4);
				if (i == devInWarehsList.size() - 1) {
					//核销数量=本次核销数量-已核销数量
					writeOffQty = curWriteOffQty.subtract(devMatReqInfo
							.getHasWriteOffQty());
					writeOffBaseQty = devMatReqInfo.getCurWriteOffBaseQty()
							.subtract(devMatReqInfo.getHasWriteOffBaseQty());
					devMatReqInfo.setHasWriteOffQty(curWriteOffQty);
					devMatReqInfo.setHasWriteOffBaseQty(devMatReqInfo
							.getCurWriteOffBaseQty());
				} else {
					devMatReqInfo.setHasWriteOffQty(devMatReqInfo
							.getHasWriteOffQty().add(writeOffQty));
					devMatReqInfo.setHasWriteOffBaseQty(devMatReqInfo
							.getHasWriteOffBaseQty().add(writeOffBaseQty));
				}
				BigDecimal baseUnitPrice = devMatReqInfo.getActualCost()
						.divide(devMatReqInfo.getBaseQty(), 10, 4);
				BigDecimal writeOffAmount = writeOffBaseQty.multiply(
						baseUnitPrice)
						.setScale(devMatReqInfo.getPrecision(), 4);
				if (devMatReqInfo.getHasWriteOffBaseQty().compareTo(
						devMatReqInfo.getBaseQty()) == 0) {
					writeOffAmount = devMatReqInfo.getActualCost().subtract(
							devMatReqInfo.getCurrWriteOffTotalAmount());
				}
				devMatReqInfo.setCurrWriteOffTotalAmount(devMatReqInfo
						.getCurrWriteOffTotalAmount().add(writeOffAmount));
				totalWriteOffAmount = totalWriteOffAmount.add(writeOffAmount);
				matReqRecordInfo = createWrittenOffRecord(ctx, devMatReqInfo,
						groupInfo, i + 1, j + 2);
				matReqRecordInfo.setCurrWriteOffQty(writeOffQty);
				matReqRecordInfo.setCurrWriteOffBaseQty(writeOffBaseQty);
				matReqRecordInfo.setCurrWriteOffAmount(writeOffAmount);
				matReqRecordInfo.setBillUnWriteOffBaseQty(devMatReqInfo
						.getDevUnWriteOffBaseQty().subtract(
								devMatReqInfo.getHasWriteOffBaseQty()));
			}
			if (isNeedWriteBack(devInWarehsInfo)) {
				BigDecimal materialCost = totalWriteOffAmount.divide(
						devInWarehsInfo.getCurWriteOffBaseQty(), 10, 4)
						.multiply(devInWarehsInfo.getBaseQty()).setScale(
								devInWarehsInfo.getPrecision(), 4);
				devInWarehsInfo.setMaterialCost(materialCost);
				BigDecimal unitCost = materialCost.divide(devInWarehsInfo
						.getQty(), devInWarehsInfo.getPricePrecision(), 4);
				devInWarehsInfo.setUnitMaterialCost(unitCost);
			}
			inWarehsRecordInfo = createWrittenOffRecord(ctx, devInWarehsInfo,
					groupInfo, i + 1, 1);
			inWarehsRecordInfo.setCurrWriteOffQty(devInWarehsInfo
					.getDevUnWriteOffQty());
			inWarehsRecordInfo.setCurrWriteOffBaseQty(devInWarehsInfo
					.getDevUnWriteOffBaseQty());
			inWarehsRecordInfo.setCurrWriteOffAmount(totalWriteOffAmount);
			inWarehsRecordInfo.setBillUnWriteOffBaseQty(CalculateUtil.ZERO);
		}

		setInWarehsWriteOffInfo(devInWarehsList);
		if (devInWarehsList.size() == 0) {
			setRedBlueMatReqBillWriteOffAmount(devMatReqList);
		} else {
			setMatReqWriteOffInfo(devMatReqList);
		}
		if (devInWarehsList.size() == 0) {
			createRedAndBuleWriteOffRecord(ctx, devMatReqList, groupInfo);
		}
		return true;
	}

	/**
	 * 汇总金额、基本数量
	 * @param info
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private BigDecimal getDevInWarehsTotal(DevolveWriteOffResultInfo info) {
		BigDecimal total = CalculateUtil.ZERO;
		DevolveInWarehsBillInfo vo = null;
		List devInWarehsList = info.getDevInWarehsList();
		for (int i = 0; i < devInWarehsList.size(); i++) {
			vo = (DevolveInWarehsBillInfo) devInWarehsList.get(i);
			if (info.getApportionRule() == 1)
				total = total.add(vo.getMaterialCost());
			else
				total = total.add(vo.getBaseQty());
		}

		return total;
	}

	/**
	 * 
	 * @param info
	 * @return
	 */
	private boolean isNeedWriteBack(DevolveInWarehsBillInfo info) {
		return !info.isFiVouchered()
				&& (info.getYear() > currPeriod.getPeriodYear() || info
						.getYear() == currPeriod.getPeriodYear()
						&& info.getPeriod() >= currPeriod.getPeriodNumber());
	}

	/**
	 * 按订单核销
	 * 
	 * @param ctx
	 * @param info
	 * @param groupInfo
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	@SuppressWarnings("unchecked")
	private boolean orderWriteOff(Context ctx, DevolveWriteOffResultInfo info,
			DevolveWriteOffGroupInfo groupInfo) throws BOSException,
			EASBizException {
		logger.info("=====按订单核销=====");
		List devInWarehsList = info.getDevInWarehsList();// 入库单list
		DevolveInWarehsBillInfo devInWarehsInfo = null;// 入库单info
		DevolveMatReqBillInfo devMatReqInfo = null;// 领料单info
		SubContractOrderEntryInfo entry = null;// 委外加工件（委外订单分录）
		SubMaterialListCollection materialColl = null;// 委外加工件集合
		SubMaterialListInfo materialListInfo = null;// 委外加工件物料集合
		Map coreEntryIdMap = new HashMap();
		HashSet coreIdSet = new HashSet();
		DevolveWriteOffRecordInfo inWarehsRecordInfo = null;
		DevolveWriteOffRecordInfo matReqRecordInfo = null;
		int groupNo = 1;
		boolean isWrittenOff = false;
		Map globalMaterialMap = new HashMap();
		for (int i = 0; i < devInWarehsList.size(); i++) {
			devInWarehsInfo = (DevolveInWarehsBillInfo) devInWarehsList.get(i);
			String coreBillEntryId = devInWarehsInfo.getCoreBillEntryId();// 入库单源单据分录id
			if (coreBillEntryId == null
					|| "".equals(coreBillEntryId)
					|| !BOSUuid.read(coreBillEntryId).getType().equals(
							(new SubContractOrderEntryInfo()).getBOSType())) {
				continue;
			}
			isWrittenOff = true;
			entry = (SubContractOrderEntryInfo) SubContractOrderEntryFactory
					.getLocalInstance(ctx).getValue(
							new ObjectUuidPK(coreBillEntryId));
			materialColl = entry.getEntries1();// 委外加工件
			if (materialColl == null || materialColl.size() == 0) {
				// 委外订单的委外加工件分录为空
				devWarehsInfoList.add(devInWarehsInfo.getNumber());
				continue;
			}
			coreIdSet.clear();
			coreEntryIdMap.clear();
			for (int j = 0; j < materialColl.size(); j++) {
				materialListInfo = materialColl.get(j);
				coreIdSet.add(materialListInfo.getId().toString());
				coreEntryIdMap.put(materialListInfo.getMaterial().getId()
						.toString(), materialListInfo.getBaseQtyRate());
			}

			IRowSet rowSet = getMatReqEntryByOrder(ctx, coreIdSet);
			Map materialMap = getDevolveMatReqBillInfoColl(rowSet,
					globalMaterialMap);
			if (materialMap.size() < coreIdSet.size()) {
				// 委外订单下推的领料出库单的分录未核销数量小于委外订单的外发材料数量
				devWarehsInfoList.add(devInWarehsInfo.getNumber());
				continue;
			}
			BigDecimal totalWriteOffAmount = CalculateUtil.ZERO;
			boolean interrupt = false;
			Iterator it = materialMap.keySet().iterator();
			do {
				if (!it.hasNext()) {
					break;
				}
				String materialId = (String) it.next();
				BigDecimal baseQtyRate = (BigDecimal) coreEntryIdMap
						.get(materialId);
				int basePrecision = ((Integer) materialBaseQtyPrecision
						.get(materialId)).intValue();
				BigDecimal devUnWriteOffBaseQty = CalculateUtil.ZERO;
				if (devInWarehsInfo.getDevUnWriteOffBaseQty() != null) {
					devUnWriteOffBaseQty = devInWarehsInfo
							.getDevUnWriteOffBaseQty();
				} else {
					devUnWriteOffBaseQty = CalculateUtil.ZERO;
				}
				if (baseQtyRate == null) {
					baseQtyRate = new BigDecimal(String.valueOf("1.0000"));
				}
				/* modify ===B */
				baseQtyRate = new BigDecimal(String.valueOf("1.0000"));
				/* modify ===E */
				BigDecimal curWriteOffBaseQty = devUnWriteOffBaseQty.multiply(
						baseQtyRate).setScale(basePrecision, 4);
				List matReqBillList = (List) materialMap.get(materialId);
				for (int j = 0; j < matReqBillList.size(); j++) {
					devMatReqInfo = (DevolveMatReqBillInfo) matReqBillList
							.get(j);
					if (CalculateUtil.ZERO.compareTo(devMatReqInfo
							.getDevUnWriteOffBaseQty()) == 0) {
						continue;
					}
					//devMatReqInfo.setDevUnWriteOffBaseQty(curWriteOffBaseQty);
					// if (curWriteOffBaseQty.compareTo(devMatReqInfo
					// .getDevUnWriteOffBaseQty()) > 0) {
					// logger.info("==========aaaaa");
					// devMatReqInfo.setCurWriteOffBaseQty(devMatReqInfo
					// .getDevUnWriteOffBaseQty());
					// curWriteOffBaseQty = curWriteOffBaseQty
					// .subtract(devMatReqInfo
					// .getDevUnWriteOffBaseQty());
					// } else {
					// logger.info("==========bbbbb");

					/* modify ===B */
					BigDecimal unWriteOffBaseQty = devMatReqInfo
							.getDevUnWriteOffBaseQty();
					BigDecimal actualCost = devMatReqInfo.getActualCost();
					logger.info("=====" + actualCost);
					devMatReqInfo.setCurUnWriteOffBaseQty(CalculateUtil.ZERO);
					devMatReqInfo.setCurWriteOffBaseQty(unWriteOffBaseQty);
					devMatReqInfo.setCurWriteOffAmount(actualCost);
					/* modify ===E */
					curWriteOffBaseQty = CalculateUtil.ZERO;
					// }
					BigDecimal curWriteOffQty = devMatReqInfo
							.getCurWriteOffBaseQty().divide(
									devMatReqInfo.getBaseConvsRate(),
									devMatReqInfo.getQtyPrecision(), 4);

					BigDecimal baseUnitPrice = devMatReqInfo.getActualCost()
							.divide(devMatReqInfo.getBaseQty(), 10, 4);
					BigDecimal writeOffAmount = devMatReqInfo
							.getCurWriteOffBaseQty().multiply(baseUnitPrice)
							.setScale(devMatReqInfo.getPrecision(), 4);
					if (devMatReqInfo.getCurWriteOffBaseQty().compareTo(
							devMatReqInfo.getDevUnWriteOffBaseQty()) == 0) {
						writeOffAmount = devMatReqInfo.getActualCost()
								.subtract(devMatReqInfo.getDevWriteOffAmount());
						curWriteOffQty = devMatReqInfo.getQty().subtract(
								devMatReqInfo.getDevWriteOffQty());
					}
					devMatReqInfo.setCurrWriteOffTotalAmount(devMatReqInfo
							.getCurrWriteOffTotalAmount().add(writeOffAmount));
					totalWriteOffAmount = totalWriteOffAmount
							.add(writeOffAmount);
					matReqRecordInfo = createWrittenOffRecord(ctx,
							devMatReqInfo, groupInfo, groupNo, j + 2);
					matReqRecordInfo.setCurrWriteOffQty(curWriteOffQty);
					matReqRecordInfo.setCurrWriteOffBaseQty(curWriteOffQty);
					matReqRecordInfo.setCurrWriteOffAmount(writeOffAmount);
					matReqRecordInfo.setBillUnWriteOffBaseQty(devMatReqInfo
							.getDevUnWriteOffBaseQty().subtract(
									devMatReqInfo.getCurWriteOffBaseQty()));
					if (curWriteOffBaseQty.compareTo(CalculateUtil.ZERO) == 0) {
						break;
					}
				}
				if (curWriteOffBaseQty.compareTo(CalculateUtil.ZERO) != 0) {
					interrupt = true;
					break;
				}
				setMatReqWriteOffInfo(matReqBillList);
			} while (true);
			if (interrupt) {
				for (int k = 0; k < groupInfo.getEntry().size(); k++) {
					matReqRecordInfo = groupInfo.getEntry().get(k);
					if (matReqRecordInfo.getGroupNO() == groupNo) {
						groupInfo.getEntry().remove(matReqRecordInfo);
						k--;
					}
				}
				devWarehsInfoList.add(devInWarehsInfo.getNumber());
				continue;
			}
			if (isNeedWriteBack(devInWarehsInfo)) {
				BigDecimal materialCost = totalWriteOffAmount.divide(
						devInWarehsInfo.getCurWriteOffBaseQty(), 10, 4)
						.multiply(devInWarehsInfo.getBaseQty()).setScale(
								devInWarehsInfo.getPrecision(), 4);
				devInWarehsInfo.setMaterialCost(materialCost);
				BigDecimal unitCost = materialCost.divide(devInWarehsInfo
						.getQty(), devInWarehsInfo.getPricePrecision(), 4);
				devInWarehsInfo.setUnitMaterialCost(unitCost);
			}
			inWarehsRecordInfo = createWrittenOffRecord(ctx, devInWarehsInfo,
					groupInfo, groupNo, 1);
			logger.info("=====" + devInWarehsInfo.getDevUnWriteOffQty());
			logger.info("=====" + totalWriteOffAmount);
			inWarehsRecordInfo.setCurrWriteOffQty(devInWarehsInfo
					.getDevUnWriteOffQty());
			inWarehsRecordInfo.setCurrWriteOffBaseQty(devInWarehsInfo
					.getDevUnWriteOffBaseQty());
			inWarehsRecordInfo.setCurrWriteOffAmount(totalWriteOffAmount);
			inWarehsRecordInfo.setBillUnWriteOffBaseQty(CalculateUtil.ZERO);
			groupNo++;
			List matReqBillList;
			Iterator ite = materialMap.keySet().iterator();
			while (ite.hasNext()) {
				String materialId = (String) ite.next();
				matReqBillList = (List) materialMap.get(materialId);
				info.getDevMaterialReqList().addAll(matReqBillList);
			}

			devInWarehsInfo.setDevWriteOffQty(devInWarehsInfo.getQty());
			devInWarehsInfo.setDevWriteOffBaseQty(devInWarehsInfo.getBaseQty());
			devInWarehsInfo.setDevUnWriteOffQty(CalculateUtil.ZERO);
			devInWarehsInfo.setDevUnWriteOffBaseQty(CalculateUtil.ZERO);
		}

		return isWrittenOff;
	}

	/**
	 * 获取未核销的入库单编码
	 * 
	 * @return
	 * @throws BOSException
	 */
	private String getErrDevInWarehsNums() throws BOSException {
		logger.info("=====getErrDevInWarehsNums=====");
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < devWarehsInfoList.size(); i++) {
			result.append(devWarehsInfoList.get(i));
			if (i < devWarehsInfoList.size() - 1)
				result.append("\n");
		}
		logger.info("==========result = " + result.toString());
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	private Map getDevolveMatReqBillInfoColl(IRowSet rowSet,
			Map globalMaterialMap) throws BOSException {
		Map materialMap = new HashMap();
		DevolveMatReqBillInfo matReqBillInfo = null;
		DevolveMatReqBillInfo existMatReqBillInfo = null;
		boolean isExist = false;
		try {
			List matReqBillList;
			for (; rowSet.next(); materialMap.put(matReqBillInfo
					.getMaterialId(), matReqBillList)) {
				matReqBillInfo = buildDevolveMatReqInfo(rowSet);
				List globalMatReqBillList = (List) globalMaterialMap
						.get(matReqBillInfo.getMaterialId());
				int i = 0;
				do {
					if (globalMatReqBillList == null
							|| i >= globalMatReqBillList.size()) {
						break;
					}
					existMatReqBillInfo = (DevolveMatReqBillInfo) globalMatReqBillList
							.get(i);
					if (existMatReqBillInfo.getEntryId().equals(
							matReqBillInfo.getEntryId())) {
						matReqBillInfo = existMatReqBillInfo;
						matReqBillInfo
								.setCurWriteOffBaseQty(CalculateUtil.ZERO);
						matReqBillInfo
								.setCurrWriteOffTotalAmount(CalculateUtil.ZERO);
						isExist = true;
						break;
					}
					i++;
				} while (true);
				if (!isExist) {
					if (globalMatReqBillList == null) {
						globalMatReqBillList = new ArrayList();
					}
					globalMatReqBillList.add(matReqBillInfo);
					globalMaterialMap.put(matReqBillInfo.getMaterialId(),
							globalMatReqBillList);
				}
				matReqBillList = (List) materialMap.get(matReqBillInfo
						.getMaterialId());
				if (matReqBillList == null) {
					matReqBillList = new ArrayList();
				}
				matReqBillList.add(matReqBillInfo);
			}
		} catch (SQLException e) {
			throw new BOSException(e);
		}
		return materialMap;
	}

	@SuppressWarnings("unchecked")
	private IRowSet getMatReqEntryByOrder(Context ctx, Set sourceIdSet)
			throws BOSException {
		String queryId = "com.kingdee.eas.scm.cal.DevolveMaterialReqBillQuery";
		com.kingdee.bos.metadata.IMetaDataPK queryPk = MetaDataPK
				.create(queryId);
		IQueryExecutor queryExec = QueryExecutorFactory.getLocalInstance(ctx,
				queryPk);
		queryExec.option().isIgnorePermissionCheck = true;
		EntityViewInfo viewInfo = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(
				new FilterItemInfo("entry.coreBillEntryId", sourceIdSet,
						CompareType.INCLUDE));
		filter.getFilterItems().add(
				new FilterItemInfo("entry.unWriteOffBaseQty", new Integer(0),
						CompareType.GREATER));
		filter.setMaskString("(#0 and #1)");
		viewInfo.setFilter(filter);
		queryExec.setObjectView(viewInfo);
		String sSQL = queryExec.getSQL();
		logger.info("=====" + sSQL);
		IRowSet rowSet = DBUtil.executeQuery(ctx, sSQL);
		return rowSet;
	}

	@SuppressWarnings("unchecked")
	private DevolveMatReqBillInfo buildDevolveMatReqInfo(IRowSet rowSet)
			throws SQLException {
		DevolveMatReqBillInfo info = new DevolveMatReqBillInfo(rowSet
				.getString("entry.id"));
		info.setBillId(rowSet.getString("id"));
		info.setNumber(rowSet.getString("number"));
		info.setBizDate(rowSet.getTimestamp("bizDate"));
		info.setBillType(rowSet.getString("billType"));
		info.setQty(rowSet.getBigDecimal("entry.qty"));
		info.setBaseQty(rowSet.getBigDecimal("entry.baseQty"));
		info.setCurWriteOffBaseQty(CalculateUtil.ZERO);
		info.setActualCost(rowSet.getBigDecimal("entry.actualCost"));
		logger.info("=====" + rowSet.getBigDecimal("entry.actualCost"));
		info.setUnitActualCost(rowSet.getBigDecimal("entry.unitActualCost"));
		info.setCoreBillType(rowSet.getString("entry.coreBillType"));
		info.setCoreBillNumber(rowSet.getString("entry.coreBillNumber"));
		info.setCoreBillEntrySeq(rowSet.getInt("entry.coreBillEntrySeq"));
		info.setCoreBillId(rowSet.getString("entry.coreBillId"));
		info.setCoreBillEntryId(rowSet.getString("entry.coreBillEntryId"));
		info.setMaterialId(rowSet.getString("material.id"));
		info.setCompanyOrgUnitID(rowSet.getString("companyOrgUnitId"));
		info.setStorageOrgUnitId(rowSet.getString("storageOrgUnitId"));
		info.setWareHouseID(rowSet.getString("warehouse.id"));
		info.setPrecision(rowSet.getInt("baseCurrency.precision"));
		info.setPricePrecision(rowSet.getInt("material.pricePrecision"));
		info.setQtyPrecision(rowSet.getInt("qtyPrecision"));
		info.setBaseQtyPrecision(rowSet.getInt("baseQtyPrecision"));
		materialBaseQtyPrecision.put(info.getMaterialId(), new Integer(info
				.getBaseQtyPrecision()));
		info.setBaseUnitID(rowSet.getString("baseUnit.id"));
		info.setYear(rowSet.getInt("bizYear"));
		info.setPeriod(rowSet.getInt("bizPeriod"));
		info.setDevUnWriteOffBaseQty(rowSet
				.getBigDecimal("entry.unWriteOffBaseQty"));
		info.setDevUnWriteOffQty(rowSet.getBigDecimal("entry.unWriteOffQty"));
		info.setDevWriteOffBaseQty(rowSet
				.getBigDecimal("entry.writtenOffBaseQty"));
		info.setDevWriteOffQty(rowSet.getBigDecimal("entry.writeOffQty"));
		info.setHasWriteOffBaseQty(CalculateUtil.ZERO);
		info.setHasWriteOffQty(CalculateUtil.ZERO);
		info.setCurrWriteOffTotalAmount(CalculateUtil.ZERO);
		info.setDevWriteOffAmount(rowSet.getBigDecimal("entry.writeOffAmount"));
		info.setDevUnWriteOffAmount(rowSet
				.getBigDecimal("entry.unWriteOffAmount"));
		info.setLot(rowSet.getString("entry.lot"));
		info.setSeq(rowSet.getInt("entry.seq"));
		info.setPresent(rowSet.getBoolean("entry.isPresent"));
		info.setFiVouchered(rowSet.getBoolean("fiVouchered"));
		info.setTransactionTypeID(rowSet.getString("transactionType.id"));
		info.setReverseQty(rowSet.getBigDecimal("entry.reverseQty"));
		info.setReversed(rowSet.getBoolean("isReversed"));
		info.setBaseConvsRate(rowSet.getBigDecimal("baseConvsRate"));
		return info;
	}

	@SuppressWarnings("unchecked")
	private void setRedBlueMatReqBillWriteOffAmount(List devMatReqBillList) {
		DevolveMatReqBillInfo devMatReqInfo = null;
		for (int i = 0; i < devMatReqBillList.size(); i++) {
			devMatReqInfo = (DevolveMatReqBillInfo) devMatReqBillList.get(i);
			BigDecimal baseUnitPrice = devMatReqInfo.getActualCost().divide(
					devMatReqInfo.getBaseQty(), 10, 4);
			BigDecimal writeOffAmount = devMatReqInfo.getCurWriteOffBaseQty()
					.multiply(baseUnitPrice).setScale(
							devMatReqInfo.getPrecision(), 4);
			if (devMatReqInfo.getCurWriteOffBaseQty().compareTo(
					devMatReqInfo.getBaseQty()) == 0)
				writeOffAmount = devMatReqInfo.getActualCost();
			devMatReqInfo.setDevWriteOffBaseQty(devMatReqInfo
					.getDevWriteOffBaseQty().add(
							devMatReqInfo.getCurWriteOffBaseQty()));
			devMatReqInfo.setDevUnWriteOffBaseQty(devMatReqInfo.getBaseQty()
					.subtract(devMatReqInfo.getDevWriteOffBaseQty()));
			BigDecimal writeOffQty = devMatReqInfo.getDevWriteOffBaseQty()
					.divide(devMatReqInfo.getBaseConvsRate(),
							devMatReqInfo.getQtyPrecision(), 4);
			if (devMatReqInfo.getDevWriteOffBaseQty().compareTo(
					devMatReqInfo.getBaseQty()) == 0)
				writeOffQty = devMatReqInfo.getQty();
			devMatReqInfo.setDevWriteOffQty(writeOffQty);
			devMatReqInfo.setDevUnWriteOffQty(devMatReqInfo.getQty().subtract(
					writeOffQty));
			devMatReqInfo.setCurrWriteOffTotalAmount(writeOffAmount);
			if (devMatReqInfo.getDevWriteOffAmount() == null)
				devMatReqInfo.setDevWriteOffAmount(CalculateUtil.ZERO);
			devMatReqInfo.setDevWriteOffAmount(devMatReqInfo
					.getDevWriteOffAmount().add(
							devMatReqInfo.getCurrWriteOffTotalAmount()));
			devMatReqInfo.setDevUnWriteOffAmount(devMatReqInfo.getActualCost()
					.subtract(devMatReqInfo.getDevWriteOffAmount()));
		}

	}

	@SuppressWarnings("unchecked")
	private void createRedAndBuleWriteOffRecord(Context ctx,
			List devMatReqList, DevolveWriteOffGroupInfo groupInfo) {
		Map materialBill = new HashMap();
		ArrayList billList = null;
		DevolveMatReqBillInfo devMatReqInfo = null;
		groupInfo.setCreateType(MatCostWriteOffTypeEnum.RedBlueWriteOff);
		for (int i = 0; i < devMatReqList.size(); i++) {
			devMatReqInfo = (DevolveMatReqBillInfo) devMatReqList.get(i);
			billList = (ArrayList) materialBill.get(devMatReqInfo
					.getMaterialId());
			if (billList == null)
				billList = new ArrayList();
			billList.add(devMatReqInfo);
			materialBill.put(devMatReqInfo.getMaterialId(), billList);
		}

		DevolveWriteOffRecordInfo matReqRecordInfo = null;
		int groupIndex = 1;
		for (Iterator it = materialBill.keySet().iterator(); it.hasNext();) {
			billList = (ArrayList) materialBill.get(it.next());
			for (int i = 0; i < billList.size(); i++) {
				devMatReqInfo = (DevolveMatReqBillInfo) billList.get(i);
				matReqRecordInfo = createWrittenOffRecord(ctx, devMatReqInfo,
						groupInfo, groupIndex, i + 1);
				BigDecimal writeOffQty = devMatReqInfo.getCurWriteOffBaseQty()
						.divide(devMatReqInfo.getBaseConvsRate(),
								devMatReqInfo.getQtyPrecision(), 4);
				if (devMatReqInfo.getCurWriteOffBaseQty().compareTo(
						devMatReqInfo.getBaseQty()) == 0)
					writeOffQty = devMatReqInfo.getQty();
				matReqRecordInfo.setCurrWriteOffQty(writeOffQty);
				matReqRecordInfo.setCurrWriteOffBaseQty(devMatReqInfo
						.getCurWriteOffBaseQty());
				matReqRecordInfo.setCurrWriteOffAmount(devMatReqInfo
						.getCurrWriteOffTotalAmount());
				matReqRecordInfo.setBillUnWriteOffBaseQty(devMatReqInfo
						.getDevUnWriteOffBaseQty());
			}

			groupIndex++;
		}

	}
}
