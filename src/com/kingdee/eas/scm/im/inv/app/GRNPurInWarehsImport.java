package com.kingdee.eas.scm.im.inv.app;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.ctrl.common.util.StringUtil;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.IMetaDataLoader;
import com.kingdee.bos.metadata.MetaDataLoaderFactory;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.codingrule.CodingRuleManagerFactory;
import com.kingdee.eas.base.codingrule.ICodingRuleManager;
import com.kingdee.eas.base.permission.*;
import com.kingdee.eas.basedata.assistant.*;
import com.kingdee.eas.basedata.master.cssp.ISupplier;
import com.kingdee.eas.basedata.master.cssp.SupplierCollection;
import com.kingdee.eas.basedata.master.cssp.SupplierFactory;
import com.kingdee.eas.basedata.master.cssp.SupplierInfo;
import com.kingdee.eas.basedata.master.material.*;
import com.kingdee.eas.basedata.org.*;
import com.kingdee.eas.basedata.person.*;
import com.kingdee.eas.basedata.scm.common.*;
import com.kingdee.eas.basedata.scm.im.inv.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.scm.common.BillBaseStatusEnum;
import com.kingdee.eas.scm.common.EntryBaseStatusEnum;
import com.kingdee.eas.scm.common.PurchaseTypeEnum;
import com.kingdee.eas.scm.common.SCMBillException;
import com.kingdee.eas.scm.common.app.SCMGroupServerUtils;
import com.kingdee.eas.scm.common.app.SCMServerUtils;
import com.kingdee.eas.scm.common.util.SCMUtils;
import com.kingdee.eas.scm.im.inv.*;
import com.kingdee.eas.scm.sm.pur.util.DataImportUtils;
import com.kingdee.eas.scm.sm.sc.DosingTypeEnum;
import com.kingdee.eas.tools.datatask.AbstractDataTransmission;
import com.kingdee.eas.tools.datatask.TaskStateEnum;
import com.kingdee.eas.tools.datatask.core.TaskExternalException;
import com.kingdee.eas.tools.datatask.log.TaskLog;
import com.kingdee.eas.tools.datatask.log.TaskLogUtil;
import com.kingdee.eas.tools.datatask.runtime.DataToken;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.eas.util.client.ExceptionHandler;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.BaseException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;

import org.apache.log4j.Logger;

public class GRNPurInWarehsImport extends AbstractDataTransmission {
	PurInWarehsBillInfo PurInWarehsBillInfo;
	private static String resources = "com.kingdee.eas.scm.im.inv.app.OtherBillDataImportResource";
	private boolean IsHaveValue;
	private boolean IsError;
	StorageOrgUnitInfo sou;
	TransactionTypeInfo tti;
	CompanyOrgUnitInfo cou;
	String STRING_FIELDS[] = { "FNumber", "FAdminOrgUnit_number",
			"FSupplier_number", "FTransactionType_number", "FCurrency_number",
			"FPaymentType_number", "FStorageOrgUnit_number",
			"FEntry$balanceSupplier_number", "FEntry$warehouse_number",
			"FEntry$material_number", "FEntry$unit_number",
			"FEntry$baseUnit_number", "FEntry_remark",
			"FEntry$purchaseOrgUnit_number", "FEntry$storageOrgUnit_number",
			"FEntry$companyOrgUnit_number" };
	String DATE_FIELDS[] = { "FBizDate" };
	String STRING_FIELDS_EN[] = { "FAdminOrgUnit_name_l2",
			"FTransactionType_name_l2", "FCurrency_name_l2",
			"FPaymentType_name_l2", "FStorageOrgUnit_name_l2",
			"FEntry$material_name_l2" };
	// String BIGDECIMAL_FIELDS[] = {
	// "FEntry_qty", "FEntry_taxPrice", "FEntry_price",
	// "FEntry_unitPurchaseCost","FEntry_purchaseCost","FEntry_taxRate"
	// };
	// String INTEGER_FIELDS[] = {
	// "FEntry_lot"
	// };
	private static final String QTY_PROPERTY[] = { "qty" };
	private static final String ASSISTQTY_PROPERTY[] = { "assistQty" };// "FEntry_unitActualCost"
	private static final String DECIMAL_ENTRY_PROPERTY_ALL[] = { "FEntry_qty",
			"FEntry_assistQty", "FEntry_taxPrice", "FEntry_price",
			"FEntry_unitPurchaseCost", "FEntry_purchaseCost", "FEntry_taxRate",
			"FEntry_scUnWrittenOffBaseQty", "FEntry_scUnWrittenOffQty",
			"FEntry_scWrittenOffBaseQty", "FEntry_scWrittenOffQty" };// "unitActualCost"
	private static final String DECIMAL_ENTRY_PROPERTY_ALL_KEY[] = { "qty",
			"assistQty", "taxPrice", "price", "unitPurchaseCost",
			"purchaseCost", "taxRate", "scUnWrittenOffBaseQty",
			"scUnWrittenOffQty", "scWrittenOffBaseQty", "scWrittenOffQty" };
	private static final BigDecimal ZERO = new BigDecimal("0.00");
	private int currentRowIndex = 1;
	private ArrayList<String> messageList = new ArrayList<String>();

	private String DECIMAL_ENTRY_PROPERTY_EXCLUDENEGATE[] = { "FEntry_price",
			"FEntry_taxPrice", "FEntry_actualPrice", "FEntry_actualTaxPrice",
			"FEntry_taxRate" };

	public GRNPurInWarehsImport() {
		PurInWarehsBillInfo = null;
		IsHaveValue = false;
		IsError = false;
		sou = null;
		tti = null;
		cou = null;
	}

	protected ICoreBase getController(Context ctx) throws TaskExternalException {
		try {
			return PurInWarehsBillFactory.getLocalInstance(ctx);
		} catch (BOSException e) {
			e.printStackTrace();
			throw new TaskExternalException("", e);
		}
	}

	@SuppressWarnings("unchecked")
	public CoreBaseInfo transmit(Hashtable hsData, Context ctx)
			throws TaskExternalException {
		PurInWarehsBillInfo = null;
		messageList.clear();
		String message = "";
		int k = 0;
		for (int i = 0; i < hsData.size(); i++) {
			int currentStart = Integer.parseInt(getContextParameter(
					"CURRENTSTART").toString());
			currentRowIndex = currentStart + i;
			Hashtable lineData = (Hashtable) hsData.get(new Integer(i));
			try {
				if (k == 0) {
					PurInWarehsBillInfo = transmitHead(lineData, ctx);
					if (PurInWarehsBillInfo == null)
						return null;
					complateInfo(lineData, ctx);
				}
			} catch (Exception ex) {
				message = String.format("第%s行：表头引入发生未知异常：%s", currentRowIndex,
						ex.getMessage());
				messageList.add(message);
			}
			try {
				PurInWarehsEntryInfo entryinfo = transmitEntry(lineData, ctx);
				int seq = PurInWarehsBillInfo.getEntry().size() + 1;
				entryinfo.setSeq(seq);
				entryinfo.setParent(PurInWarehsBillInfo);
				entryinfo.setStorageOrgUnit(sou);
				// 设置财务组织
				entryinfo.setCompanyOrgUnit(cou);
				PurInWarehsBillInfo.getEntry().add(entryinfo);
			} catch (Exception ex) {
				message = String.format("第%s行：表体引入发生未知异常：%s", currentRowIndex,
						ex.getMessage());
				messageList.add(message);
			}
			k++;
		}

		try {
			// 自动计算totalActualCost
			BigDecimal totalActualCost = ZERO;
			for (int i = 0; i < PurInWarehsBillInfo.getEntry().size(); i++) {
				PurInWarehsEntryInfo entryInfo = PurInWarehsBillInfo.getEntry()
						.get(i);
				if (entryInfo.getActualCost() != null)
					totalActualCost = totalActualCost.add(entryInfo
							.getActualCost());
			}
			PurInWarehsBillInfo.setTotalActualCost(totalActualCost);
		} catch (Exception ex) {
			message = String.format("单据：%s计算totalActualCost时发生未知异常：%s",
					PurInWarehsBillInfo.getNumber(), ex.getMessage());
			messageList.add(message);
		}
		if (messageList.size() > 0) {
			String taskUuid = getContextParameter("TASKUUID").toString();
			for (int i = 0; i < messageList.size(); i++) {
				if (i != messageList.size() - 1) {
					LogError(taskUuid, messageList.get(i));
				} else {
					throw new TaskExternalException(messageList.get(i));
				}
			}
		}

		return PurInWarehsBillInfo;
	}

	public int getSubmitType() {
		return 1;
	}

	public void submit(CoreBaseInfo coreBaseInfo, Context ctx)
			throws TaskExternalException {
		if (coreBaseInfo == null
				|| !(coreBaseInfo instanceof PurInWarehsBillInfo))
			return;
		try {
			PurInWarehsBillInfo bill = (PurInWarehsBillInfo) coreBaseInfo;
			com.kingdee.bos.dao.IObjectPK userPK = ctx.getCaller();
			String id = getIdFromNumber(bill.getNumber(), ctx);
			com.kingdee.bos.dao.IObjectPK orgPK = new ObjectUuidPK(bill
					.getStorageOrgUnit().getId());
			SCMServerUtils.checkFunctionPermission(ctx, userPK, orgPK,
					"manufacturerec_import");
			if (StringUtil.isEmptyString(id)) {
				getController(ctx).addnew(coreBaseInfo);
			} else {
				getController(ctx).delete(new ObjectUuidPK(id));
				getController(ctx).addnew(coreBaseInfo);
			}
		} catch (Exception ex) {
			throw new TaskExternalException(ex.getMessage(), ex.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	private PurInWarehsBillInfo transmitHead(Hashtable lineData, Context ctx)
			throws TaskExternalException {
		PurInWarehsBillInfo = new PurInWarehsBillInfo();
		String message = "";
		Object data = null;
		String str = null;
		boolean tryDoNumber = false;
		boolean userCodingRuleManager = false;
		// 单据编号FNumber
		Object number = ((DataToken) lineData.get("FNumber")).data;
		if (number != null && number.toString().trim().length() > 0) {
			PurInWarehsBillInfo.setNumber(number.toString().trim());
			String existId = getIdFromNumber(number.toString().trim(), ctx);
			if (!isSltImportUpdate() && !StringUtil.isEmptyString(existId)) {
				message = String.format("第%s行：%s %s", currentRowIndex, number
						.toString().trim(), getResource(ctx, "EXISTS",
						resources));
				messageList.add(message);
				return PurInWarehsBillInfo;
			}
			// throw new TaskExternalException(number.toString().trim() +
			// getResource(ctx, "EXISTS", resources));
			if (isSltImportUpdate() && !StringUtil.isEmptyString(existId)) {
				PurInWarehsBillInfo existInfo = null;
				try {
					existInfo = ((IPurInWarehsBill) getController(ctx))
							.getPurInWarehsBillInfo(new ObjectUuidPK(existId));
				} catch (Exception e) {
					message = String.format("第%s行：获取单据%s产生错误：",
							currentRowIndex, number.toString().trim(), e
									.getMessage());
					messageList.add(message);
					return PurInWarehsBillInfo;
					// throw new TaskExternalException("", e);
				}
				if (!BillBaseStatusEnum.TEMPORARILYSAVED.equals(existInfo
						.getBaseStatus())) {
					message = String.format("第%s行：%s %s", currentRowIndex,
							number.toString().trim(), getResource(ctx,
									"UnTEMPORARILYSAVEDERROE", resources));
					messageList.add(message);
					return PurInWarehsBillInfo;
					// throw new TaskExternalException(number.toString().trim()
					// + getResource(ctx, "UnTEMPORARILYSAVEDERROE",
					// resources));
				}
			}
			if (StringUtil.isEmptyString(existId)) {
				userCodingRuleManager = true;
				tryDoNumber = true;
			}
		} else {
			tryDoNumber = true;
		}
		// 部门编码FAdminOrgUnit_number
		data = null;
		if (lineData.containsKey("FAdminOrgUnit_number"))
			data = ((DataToken) lineData.get("FAdminOrgUnit_number")).data;
		if (data != null) {
			str = data.toString();
			if (str != null && str.length() > 0) {
				try {
					IAdminOrgUnit iadminorg = AdminOrgUnitFactory
							.getLocalInstance(ctx);
					AdminOrgUnitCollection collection = iadminorg
							.getAdminOrgUnitCollection(getFilter(data
									.toString().trim()));
					if (collection != null && collection.size() > 0) {
						com.kingdee.eas.basedata.org.AdminOrgUnitInfo info = collection
								.get(0);
						if (info != null)
							PurInWarehsBillInfo.setAdminOrgUnit(info);
						else {
							message = String
									.format("第%s行：%s %s", currentRowIndex,
											data, getResource(ctx,
													"adminOrgUnitNotExists",
													resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// "  " + getResource(ctx, "adminOrgUnitNotExists",
							// resources));
						}
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data, getResource(ctx, "adminOrgUnitNotExists",
										resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() +
						// "  " + getResource(ctx, "adminOrgUnitNotExists",
						// resources));
					}
				} catch (Exception ex) {
					message = String.format(
							"第%s行：FAdminOrgUnit_number引入产生错误：%s",
							currentRowIndex, ex.getMessage());
					messageList.add(message);
					// throw new TaskExternalException(ex.getMessage(),
					// ex.getCause());
				}
			}
		}
		// 供应商编码FSupplier_number
		try {
			loadsupplier(lineData, ctx, "number", "FSupplier_number");
		} catch (Exception e_number) {
			try {
				loadsupplier(lineData, ctx, "name", "FSupplier_name_l2");
			} catch (Exception e_name) {
				message = String.format("第%s行：FSupplier_number引入产生错误：%s",
						currentRowIndex, e_number.getMessage());
				messageList.add(message);
				// throw new TaskExternalException(e_number.getMessage(),
				// e_number.getCause());
			}
		}
		// FTransactionType_number
		try {
			loadTransaction(lineData, ctx, "number", "FTransactionType_number");
		} catch (Exception e_number) {
			try {
				loadTransaction(lineData, ctx, "name",
						"FTransactionType_name_l2");
			} catch (Exception e_name) {
				message = String.format(
						"第%s行：FTransactionType_number引入产生错误：%s",
						currentRowIndex, e_number.getMessage());
				messageList.add(message);
				// throw new TaskExternalException(e_number.getMessage(),
				// e_number.getCause());
			}
		}

		// FBillType_number
		BillTypeInfo aBillTypeInfo = new BillTypeInfo();
		aBillTypeInfo.setId(BOSUuid
				.read("50957179-0105-1000-e000-015fc0a812fd463ED552"));
		aBillTypeInfo.setNumber("103");
		PurInWarehsBillInfo.setBillType(aBillTypeInfo);

		// add by limin lin at Feb 24, 2012
		// FPurchaseType
		PurchaseTypeEnum purchaseType = PurchaseTypeEnum.PURCHASE;
		data = null;
		if (lineData.containsKey("FPurchaseType"))
			data = ((DataToken) lineData.get("FPurchaseType")).data;
		if (data != null) {
			str = data.toString();
			if (str != null && str.length() > 0) {
				int purchaseTypeValue = PurchaseTypeEnum.PURCHASE_VALUE;
				try {
					purchaseTypeValue = Integer.parseInt(str);
					if (purchaseTypeValue != 0 && purchaseTypeValue != 1) {
						message = String.format(
								"第%s行：%s FPurchaseType设置错误，必须为0或者1",
								currentRowIndex, data);
						messageList.add(message);
					}
				} catch (NumberFormatException ex) {
					message = String.format(
							"第%s行：%s FPurchaseType设置错误，必须为0或者1",
							currentRowIndex, data);
					messageList.add(message);
				}

				purchaseType = PurchaseTypeEnum.getEnum(purchaseTypeValue);
			}
		}
		PurInWarehsBillInfo.setPurchaseType(purchaseType);

		// FCurrency_number
		data = null;
		if (lineData.containsKey("FCurrency_number"))
			data = ((DataToken) lineData.get("FCurrency_number")).data;
		if (data != null) {
			str = data.toString();
			if (str != null && str.length() > 0)
				try {
					ICurrency icur = CurrencyFactory.getLocalInstance(ctx);
					CurrencyCollection collection = icur
							.getCurrencyCollection(getFilter(data.toString()
									.trim()));
					if (collection != null && collection.size() > 0) {
						CurrencyInfo info = collection.get(0);
						if (info != null)
							PurInWarehsBillInfo.setCurrency(info);
						else {
							message = String.format("第%s行：%s 币别不存在",
									currentRowIndex, data);
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// "  币别不存在");
						}
					} else {
						message = String.format("第%s行：%s 币别不存在",
								currentRowIndex, data);
						messageList.add(message);
						// throw new TaskExternalException(data.toString() +
						// "  币别不存在");
					}
				} catch (Exception ex) {
					message = String.format("第%s行：FCurrency_number引入产生错误：%s",
							currentRowIndex, ex.getMessage());
					messageList.add(message);
					// throw new TaskExternalException(ex.getMessage(),
					// ex.getCause());
				}
		}
		// FPaymentType_number
		data = null;
		if (lineData.containsKey("FPaymentType_number"))
			data = ((DataToken) lineData.get("FPaymentType_number")).data;
		if (data != null) {
			str = data.toString();
			if (str != null && str.length() > 0)
				try {
					IPaymentType ipay = PaymentTypeFactory
							.getLocalInstance(ctx);
					PaymentTypeCollection collection = ipay
							.getPaymentTypeCollection(getFilter(data.toString()
									.trim()));
					if (collection != null && collection.size() > 0) {
						PaymentTypeInfo info = collection.get(0);
						if (info != null)
							PurInWarehsBillInfo.setPaymentType(info);
						else {
							message = String.format("第%s行：%s 付款方式不存在",
									currentRowIndex, data);
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// "  付款方式不存在");
						}
					} else {
						message = String.format("第%s行：%s 付款方式不存在",
								currentRowIndex, data);
						messageList.add(message);
						// throw new TaskExternalException(data.toString() +
						// "  付款方式不存在");
					}
				} catch (Exception ex) {
					message = String.format(
							"第%s行：FPaymentType_number引入产生错误：%s",
							currentRowIndex, ex.getMessage());
					messageList.add(message);
					// throw new TaskExternalException(ex.getMessage(),
					// ex.getCause());
				}
		}
		// FStoreOrgUnit_number
		try {
			loadstorageOrgUnit(lineData, ctx, "number",
					"FStorageOrgUnit_number");
		} catch (Exception e_number) {
			try {
				loadstorageOrgUnit(lineData, ctx, "name",
						"FStorageOrgUnit_name_l2");
			} catch (Exception e_name) {
				message = String.format("第%s行：库存组织不存在", currentRowIndex);
				messageList.add(message);
				// throw new TaskExternalException(e_number.getMessage(),
				// e_number.getCause());
			}
		}
		try {
			if (tryDoNumber) {
				boolean isUsedParam = DataImportUtils.getImportParam(ctx);
				if (isUsedParam) {
					String souID = sou.getId().toString().trim();
					ICodingRuleManager iCodingRuleManager = CodingRuleManagerFactory
							.getLocalInstance(ctx);
					if (iCodingRuleManager.isExist(PurInWarehsBillInfo, souID))
						PurInWarehsBillInfo.setNumber(iCodingRuleManager
								.getNumber(PurInWarehsBillInfo, souID, ""));
					else if (!userCodingRuleManager) {
						IMetaDataLoader imeataLoader = MetaDataLoaderFactory
								.getLocalMetaDataLoader(ctx);
						EntityObjectInfo entityObjectInfo = imeataLoader
								.getEntity(sou.getBOSType());
						String materialNames[] = new String[1];
						materialNames[0] = entityObjectInfo.getAlias();
						throw new SCMBillException(SCMBillException.NOCORDRULE,
								materialNames);
					}
				} else if (!userCodingRuleManager)
					throw new TaskExternalException(getResource(ctx,
							"UnUseDataImportParam", resources));
			}
		} catch (Exception e) {
			throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		PurInWarehsBillInfo.setCU(ContextUtil.getCurrentCtrlUnit(ctx));
		try {
			IOrgUnitRelation iUnitRel = OrgUnitRelationFactory
					.getLocalInstance(ctx);
			OrgUnitCollection orgCol = iUnitRel.getToUnit(sou.getId()
					.toString(), 4, 1);
			if (orgCol.get(0) != null)
				cou = (CompanyOrgUnitInfo) orgCol.get(0);
			else {
				message = String.format("第%s行：%s", currentRowIndex,
						getResource(ctx, "NoCompanyOrgUnit", resources));
				messageList.add(message);
				// throw new TaskExternalException(getResource(ctx,
				// "NoCompanyOrgUnit", resources));
			}
		} catch (Exception e) {
			message = String.format("第%s行：%s", currentRowIndex, e.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(e.getMessage(), e.getCause());
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date da = df.parse(lineData.get("FBizDate").toString());
			PurInWarehsBillInfo.setBizDate(da);
			// add by limin lin at Feb 09,2012
			// 根据业务日期计算业务年度，业务期间，年，月字段值
			Calendar bizCal = Calendar.getInstance();
			bizCal.setTime(da);
			int bizYear = bizCal.get(Calendar.YEAR);
			int bizMonth = bizCal.get(Calendar.MONTH) + 1;
			PurInWarehsBillInfo.setPeriod(bizMonth);
			PurInWarehsBillInfo.setYear(bizYear);
			DateFormat monthFormatter = new SimpleDateFormat("yyyyMM");
			DateFormat dayFormatter = new SimpleDateFormat("yyyyMMdd");
			PurInWarehsBillInfo.setMonth(Integer.parseInt(monthFormatter
					.format(da)));
			PurInWarehsBillInfo.setDay(Integer
					.parseInt(dayFormatter.format(da)));
		} catch (ParseException e1) {
			message = String.format("第%s行：%s %s", currentRowIndex, lineData
					.get("FBizDate").toString(), getResource(ctx,
					"InvalidDateFormat", resources));
			messageList.add(message);
			// throw new
			// TaskExternalException(lineData.get("FBizDate").toString() +
			// getResource(ctx, "InvalidDateFormat", resources));
		}
		// modified by limin lin at Feb 09,2012
		// 单据状态处理
		BillBaseStatusEnum baseStatus = BillBaseStatusEnum.TEMPORARILYSAVED;
		if (lineData.containsKey("FBaseStatus")) {
			String baseStatusStr = ((DataToken) lineData.get("FBaseStatus")).data
					.toString();
			int billBaseStatusEnum = new Integer(baseStatusStr);
			baseStatus = BillBaseStatusEnum.getEnum(billBaseStatusEnum);
		}

		PurInWarehsBillInfo.setBaseStatus(baseStatus);
		IsHaveValue = false;
		IsError = false;
		data = null;
		if (lineData.containsKey("FCreator_number"))
			data = ((DataToken) lineData.get("FCreator_number")).data;

		if (data != null) {
			str = data.toString();
			if (str != null && str.length() > 0)
				try {
					IUser iUser = UserFactory.getLocalInstance(ctx);
					EntityViewInfo viewInfo = new EntityViewInfo();
					FilterInfo filter = new FilterInfo();
					filter.getFilterItems().add(
							new FilterItemInfo("number", str,
									CompareType.EQUALS));
					filter.setMaskString("#0");
					viewInfo.setFilter(filter);
					UserCollection collection = iUser
							.getUserCollection(viewInfo);
					if (collection != null && collection.size() > 0) {
						com.kingdee.eas.base.permission.UserInfo info = collection
								.get(0);
						if (info != null) {
							PurInWarehsBillInfo.setCreator(info);
							IsHaveValue = true;
						}
					}
				} catch (Exception ex) {
					message = String.format(
							"第%s行：FCreator_number:%s 引入产生错误：%s",
							currentRowIndex, data.toString(), ex.getMessage());
					messageList.add(message);
					// throw new TaskExternalException(ex.getMessage(),
					// ex.getCause());
				}
		}
		if (!IsHaveValue) {
			com.kingdee.eas.base.permission.UserInfo userinfo = ContextUtil
					.getCurrentUserInfo(ctx);
			PurInWarehsBillInfo.setCreator(userinfo);
		}
		IsHaveValue = false;
		IsError = false;
		Object createdate = null;
		if (lineData.containsKey("FCreateTime")) {
			createdate = ((DataToken) lineData.get("FCreateTime")).data;
			if (createdate != null && createdate.toString().length() > 0)
				try {
					createdate = df.parseObject(createdate.toString());
					PurInWarehsBillInfo.setCreateTime(new Timestamp(
							((Date) createdate).getTime()));
					IsHaveValue = true;
				} catch (ParseException e) {
					IsError = false;
				}
		}
		if (!IsError || !IsHaveValue) {
			Date date = new Date();
			PurInWarehsBillInfo.setCreateTime(new Timestamp(date.getTime()));
		}

		PurInWarehsBillInfo.setIsInTax(getBooleanField(lineData, "FIsInTax"));
		PurInWarehsBillInfo.setIsPriceInTax(getBooleanField(lineData,
				"FIsPriceInTax"));
		PurInWarehsBillInfo
				.setIsSysBill(getBooleanField(lineData, "FIsSysBill"));
		// if(lineData.containsKey("FIsReversed"))
		PurInWarehsBillInfo.setIsReversed(getBooleanField(lineData,
				"FIsReversed"));
		PurInWarehsBillInfo.setIsInitBill(getBooleanField(lineData,
				"FIsInitBill"));
		PurInWarehsBillInfo.setHasEffected(getBooleanField(lineData,
				"FHasEffected"));

		return PurInWarehsBillInfo;
	}

	@SuppressWarnings("unchecked")
	private boolean getBooleanField(Hashtable lineData, String fieldName) {
		boolean result = false;

		Object data = null;
		if (lineData.containsKey(fieldName))
			data = ((DataToken) lineData.get(fieldName)).data;
		if (data != null) {
			String str = data.toString();
			if (str != null && str.length() > 0) {
				result = Boolean.parseBoolean(str);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private PurInWarehsEntryInfo transmitEntry(Hashtable lineData, Context ctx)
			throws TaskExternalException {
		PurInWarehsEntryInfo entryInfo = new PurInWarehsEntryInfo();
		String message = "";
		Object data = null;
		String str = "";
		MaterialInfo materialInfo = new MaterialInfo();
		MeasureUnitInfo mui = new MeasureUnitInfo();
		MaterialCompanyInfoInfo materialCompanyInfo = null;
		MaterialInventoryInfo materialInventory = null;

		try {
			// 结算供应商编码FEntry$balanceSupplier_number
			Object supplierData = null;
			if (lineData.containsKey("FEntry$balanceSupplier_number"))
				supplierData = ((DataToken) lineData
						.get("FEntry$balanceSupplier_number")).data;
			if (supplierData != null
					&& supplierData.toString().trim().length() > 0) {
				ISupplier isupplier = SupplierFactory.getLocalInstance(ctx);
				SupplierCollection collection = isupplier
						.getSupplierCollection(getFilter(supplierData
								.toString().trim(), "number"));
				if (collection != null && collection.size() > 0) {
					entryInfo.setBalanceSupplier(collection.get(0));
				}
			}
			if (entryInfo.getBalanceSupplier() == null) {
				SupplierInfo info = PurInWarehsBillInfo.getSupplier();
				if (info != null) {
					entryInfo.setBalanceSupplier(info);
				} else {
					message = String.format(
							"第%s行：FEntry$balanceSupplier_number不存在或者内容为空",
							currentRowIndex);
					messageList.add(message);
				}
			}
			// 仓库编码FEntry$warehouse_number
			WarehouseInfo warehouseInfo = new WarehouseInfo();
			data = null;
			if (lineData.containsKey("FEntry$warehouse_number"))
				data = ((DataToken) lineData.get("FEntry$warehouse_number")).data;
			try {
				if (data != null && data.toString() != null
						&& data.toString().length() > 0) {
					str = data.toString();
					if (str != null) {
						IWarehouse iw = WarehouseFactory.getLocalInstance(ctx);
						WarehouseCollection collection = iw
								.getWarehouseCollection(getFilter(str));
						if (collection != null && collection.size() > 0) {
							WarehouseInfo info = collection.get(0);
							if (info != null) {
								if (!info.getWhState().equals(
										WHStateEnum.ACTIVE)) {
									message = String.format("第%s行：%s %s",
											currentRowIndex, info.getNumber(),
											getResource(ctx,
													"WarehouseNotActive",
													resources));
									messageList.add(message);
								}
								// throw new
								// TaskExternalException(info.getNumber() + " "
								// + getResource(ctx, "WarehouseNotActive",
								// resources));
								else {
									checkWarehouse(ctx, info, sou);
									entryInfo.setWarehouse(info);
									warehouseInfo = info;
								}
							} else {
								message = String.format("第%s行：%s %s",
										currentRowIndex, data,
										getResource(ctx, "WarehouseNotExists",
												resources));
								messageList.add(message);
								// throw new
								// TaskExternalException(data.toString() + " " +
								// getResource(ctx, "WarehouseNotExists",
								// resources));
							}
						} else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data, getResource(ctx,
											"WarehouseNotExists", resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx, "WarehouseNotExists",
							// resources));
						}
					}
				} else {
					message = String.format("第%s行：%s %s", currentRowIndex,
							data, getResource(ctx, "WarehouseNotExists",
									resources));
					messageList.add(message);
					// throw new TaskExternalException(getResource(ctx,
					// "WarehouseNotExists", resources));
				}
			} catch (Exception e) {
				message = String.format(
						"第%s行：FEntry$warehouse_number引入产生错误：%s",
						currentRowIndex, e.getMessage());
				messageList.add(message);
				// throw new TaskExternalException(e.getMessage(),
				// e.getCause());
			}
			// 物料编码FEntry$material_number
			data = null;
			if (lineData.containsKey("FEntry$material_number"))
				data = ((DataToken) lineData.get("FEntry$material_number")).data;
			if (data != null && data.toString().trim().length() > 0) {
				IMaterial imaterial = MaterialFactory.getLocalInstance(ctx);
				MaterialCollection collection = imaterial
						.getMaterialCollection(getFilter(data.toString()));
				if (collection != null && collection.size() > 0) {
					materialInfo = collection.get(0);
					if (materialInfo != null) {
						entryInfo.setMaterial(materialInfo);
						materialInventory = MaterialInventoryFactory
								.getLocalInstance(ctx)
								.getMaterialInventoryInfo(
										" where orgunit = '"
												+ sou.getId().toString()
												+ "' and material = '"
												+ materialInfo.getId()
														.toString() + "'");
						if (materialInventory == null) {
							message = String
									.format("第%s行：%s %s %s", currentRowIndex,
											materialInfo.getNumber(), sou
													.getNumber(), getResource(
													ctx,
													"NO_INVENTORY_PROPERTY",
													resources));
							messageList.add(message);
						}
						// throw new
						// TaskExternalException(materialInfo.getNumber() + " "
						// + sou.getNumber() + " " + getResource(ctx,
						// "NO_INVENTORY_PROPERTY", resources));
						materialCompanyInfo = imaterial.getCompanyInfo(
								materialInfo.getId().toString(), cou.getId()
										.toString());
						if (materialCompanyInfo == null) {
							message = String.format("第%s行：%s %s %s",
									currentRowIndex, materialInfo.getNumber(),
									cou.getNumber(), getResource(ctx,
											"NO_COMPANY_PROPERTY", resources));
							messageList.add(message);
						}
						// throw new
						// TaskExternalException(materialInfo.getNumber() + " "
						// + cou.getNumber() + " " + getResource(ctx,
						// "NO_COMPANY_PROPERTY", resources));
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(), getResource(ctx,
										"MaterialNotExists", resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "MaterialNotExists", resources));
					}
				} else {
					message = String.format("第%s行：%s %s", currentRowIndex, data
							.toString(), getResource(ctx, "MaterialNotExists",
							resources));
					messageList.add(message);
					// throw new TaskExternalException(data.toString() + " " +
					// getResource(ctx, "MaterialNotExists", resources));
				}
			} else {
				message = String.format("第%s行：%s", currentRowIndex,
						getResource(ctx, "MaterialNotExists", resources));
				messageList.add(message);
				// throw new TaskExternalException(getResource(ctx,
				// "MaterialNotExists", resources));
			}

			if (materialInventory != null
					&& materialInventory.isIsPeriodValid()) {
				Date mfg = entryInfo.getMfg();
				if (mfg != null) {
					TimeUnitEnum periodValidUnit = materialInventory
							.getPeriodValidUnit();
					int unit = 3;
					if (periodValidUnit != null)
						unit = periodValidUnit.getValue();
					int periodValid = materialInventory.getPeriodValid();
					Date exp2 = getEXP(mfg, unit, periodValid);
					entryInfo.setExp(exp2);
				}
			}

			// removed by limin lin at May 14, 2012
			// set the unit via material's baseunit
			entryInfo.setUnit(materialInfo.getBaseUnit());
			mui = materialInfo.getBaseUnit();
			// data = null;
			// if(lineData.containsKey("FEntry$unit_number"))
			// data = ((DataToken)lineData.get("FEntry$unit_number")).data;
			// IMeasureUnit imu = MeasureUnitFactory.getLocalInstance(ctx);
			// if (data != null)
			// {
			// str = data.toString();
			// if (str != null && str.trim().length() > 0)
			// {
			// MeasureUnitCollection collection =
			// imu.getMeasureUnitCollection(getFilter(str));
			// if (collection != null && collection.size() > 0)
			// {
			// MeasureUnitInfo measureUnitInfo = collection.get(0);
			// checkUnit(ctx, materialInfo, measureUnitInfo);
			// if (collection != null)
			// {
			// entryInfo.setUnit(measureUnitInfo);
			// mui = measureUnitInfo;
			// } else
			// {
			// message = String.format("第%s行：%s %s", currentRowIndex
			// ,data.toString(),getResource(ctx, "UnitNotExists", resources));
			// messageList.add(message);
			// //throw new TaskExternalException(data.toString() + " " +
			// getResource(ctx, "UnitNotExists", resources));
			// }
			// } else
			// {
			// message = String.format("第%s行：%s %s", currentRowIndex
			// ,data.toString(),getResource(ctx, "UnitNotExists", resources));
			// messageList.add(message);
			// //throw new TaskExternalException(data.toString() + " " +
			// getResource(ctx, "UnitNotExists", resources));
			// }
			// } else
			// {
			// message = String.format("第%s行：%s %s", currentRowIndex
			// ,data.toString(),getResource(ctx, "UnitCanNotBeNull",
			// resources));
			// messageList.add(message);
			// //throw new TaskExternalException(data.toString() + " " +
			// getResource(ctx, "UnitCanNotBeNull", resources));
			// }
			// } else
			// {
			// message = String.format("第%s行：%s %s", currentRowIndex
			// ,data.toString(),getResource(ctx, "UnitCanNotBeNull",
			// resources));
			// messageList.add(message);
			// //throw new TaskExternalException(data.toString() + " " +
			// getResource(ctx, "UnitCanNotBeNull", resources));
			// }

			entryInfo.setAssistUnit(materialInfo.getAssistUnit());
			if (entryInfo.getAssistUnit() != null) {
				MultiMeasureUnitInfo assistMmu = MultiMeasureUnitFactory
						.getLocalInstance(ctx).getMultiUnit(
								materialInfo.getId().toString(),
								entryInfo.getAssistUnit().getId().toString());
				if (assistMmu != null) {
					String ASSISTQTY_PROPERTY[] = { "FEntry_assistQty" };
					int assistQtyPrecision = assistMmu.getQtyPrecision();
					for (int i = 0; i < ASSISTQTY_PROPERTY.length; i++)
						if (entryInfo.getBigDecimal(ASSISTQTY_PROPERTY[i]) != null)
							entryInfo.setBigDecimal(ASSISTQTY_PROPERTY[i],
									entryInfo.getBigDecimal(
											ASSISTQTY_PROPERTY[i]).setScale(
											assistQtyPrecision, 4));

					if (entryInfo.getAssistQty() == null
							|| entryInfo.getAssistQty().compareTo(ZERO) == 0) {
						BigDecimal qty = entryInfo.getQty();
						BigDecimal assisqtyQty = InvAppUtils.getMultiUnitQty(
								ctx, mui, entryInfo.getAssistUnit(),
								materialInfo, qty);
						entryInfo.setAssistQty(assisqtyQty);
					}
				}
			}

			try {
				checkNumberAndAmount(entryInfo, ctx, lineData,
						DECIMAL_ENTRY_PROPERTY_ALL,
						DECIMAL_ENTRY_PROPERTY_ALL_KEY, true);
			} catch (Exception ex) {
				message = String.format("第%s行：数量和金额校验时产生错误:%s",
						currentRowIndex, ex.getMessage());
				messageList.add(message);
			}

			int amtPrecision = 6;
			if (entryInfo.getMaterial() != null)
				amtPrecision = entryInfo.getMaterial().getPricePrecision();

			// 单位采购成本处理
			BigDecimal unitPurchaseCost = ZERO;
			data = null;
			if (lineData.containsKey("FEntry_unitPurchaseCost")) {
				data = ((DataToken) lineData.get("FEntry_unitPurchaseCost")).data;
				if (data != null) {
					unitPurchaseCost = new BigDecimal(data.toString());
				}
			}
			unitPurchaseCost.setScale(amtPrecision, 4);
			entryInfo.setUnitPurchaseCost(unitPurchaseCost);

			// assistQty处理
			BigDecimal assistQty = ZERO;
			data = null;
			if (lineData.containsKey("FEntry_assistQty")) {
				data = ((DataToken) lineData.get("FEntry_assistQty")).data;
				if (data != null) {
					assistQty = new BigDecimal(data.toString());
				}
			}
			entryInfo.setBigDecimal("assistQty", assistQty);

			if (entryInfo.getAssistUnit() != null)
				entryInfo.setAssistQty((BigDecimal) entryInfo.get("assistQty"));
			else
				entryInfo.setAssistQty(ZERO);

			data = null;
			if (lineData.containsKey("FEntry$stocker_number"))
				data = ((DataToken) lineData.get("FEntry$stocker_number")).data;
			if (data != null) {
				str = data.toString();
				if (str != null && str.trim().length() > 0)
					try {
						IPerson iPerson = PersonFactory.getLocalInstance(ctx);
						PersonCollection collection = iPerson
								.getPersonCollection(getFilter(str));
						if (collection != null && collection.size() > 0) {
							com.kingdee.eas.basedata.person.PersonInfo info = collection
									.get(0);
							if (info != null)
								entryInfo.setStocker(info);
							else {
								message = String.format("第%s行：%s %s",
										currentRowIndex, data, getResource(ctx,
												"stockerNotExists", resources));
								messageList.add(message);
							}
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx, "stockerNotExists",
							// resources));
						} else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data, getResource(ctx,
											"stockerNotExists", resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx, "stockerNotExists",
							// resources));
						}
					} catch (Exception ex) {
						message = String.format(
								"第%s行：FEntry$stocker_number引入产生错误：%s",
								currentRowIndex, data, ex.getMessage());
						messageList.add(message);
						// throw new TaskExternalException(ex.getMessage(),
						// ex.getCause());
					}
			}

			data = null;
			if (lineData.containsKey("FEntry$location_number"))
				data = ((DataToken) lineData.get("FEntry$location_number")).data;
			if (warehouseInfo.isHasLocation()
					&& (data == null || data.toString().length() <= 0))
				throw new TaskExternalException(warehouseInfo.getNumber() + " "
						+ getResource(ctx, "LocationMustNotBeNull", resources));
			if (!warehouseInfo.isHasLocation() && data != null
					&& data.toString().length() > 0)
				throw new TaskExternalException(warehouseInfo.getNumber() + " "
						+ getResource(ctx, "LocationMustBeNull", resources));
			if (data != null) {
				String number = data.toString();
				if (number != null && number.trim().length() > 0) {
					ILocation il = LocationFactory.getLocalInstance(ctx);
					EntityViewInfo viewInfo = new EntityViewInfo();
					FilterInfo filter = new FilterInfo();
					filter.getFilterItems().add(
							new FilterItemInfo("number", number,
									CompareType.EQUALS));
					filter.getFilterItems().add(
							new FilterItemInfo("Warehouse.id", warehouseInfo
									.getId().toString(), CompareType.EQUALS));
					viewInfo.setFilter(filter);
					LocationCollection collection = il
							.getLocationCollection(viewInfo);
					if (collection != null && collection.size() > 0) {
						LocationInfo info = collection.get(0);
						if (info != null) {
							if (!info.getState().equals(WHStateEnum.ACTIVE)) {
								message = String.format("第%s行：%s %s",
										currentRowIndex, info.getNumber(),
										getResource(ctx, "LocationNotActive",
												resources));
								messageList.add(message);
								// throw new
								// TaskExternalException(info.getNumber() + " "
								// + getResource(ctx, "LocationNotActive",
								// resources));
							}
							if (!warehouseInfo.getId().toString().equals(
									info.getWarehouse().getId().toString())) {
								message = String
										.format(
												"第%s行：%s %s %s",
												currentRowIndex,
												info.getNumber(),
												getResource(
														ctx,
														"import_location_not_match_warehouse",
														resources),
												warehouseInfo.getNumber());
								messageList.add(message);
							}
							// throw new TaskExternalException(info.getNumber()
							// + " " + getResource(ctx,
							// "import_location_not_match_warehouse", resources)
							// + " " + warehouseInfo.getNumber());
							entryInfo.setLocation(info);
						} else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data, getResource(ctx,
											"LocationNotExists", resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx, "LocationNotExists",
							// resources));
						}
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data, getResource(ctx, "LocationNotExists",
										resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "LocationNotExists", resources));
					}
				}
			}

			Object o = null;
			if (lineData.containsKey("FEntry_remark"))
				o = ((DataToken) lineData.get("FEntry_remark")).data;
			if (o != null && o.toString().trim().length() > 0 && o != null
					&& o.toString().length() > 0)
				entryInfo.setRemark(o.toString());

			IMeasureUnit imu = MeasureUnitFactory.getLocalInstance(ctx);
			com.kingdee.bos.dao.IObjectPK pk = new ObjectUuidPK(materialInfo
					.getBaseUnit().getId());
			MeasureUnitInfo baseUnit = imu.getMeasureUnitInfo(pk);
			entryInfo.setBaseUnit(baseUnit);

			BigDecimal qty = entryInfo.getQty();
			BigDecimal baseQty = InvAppUtils.getBaseUnitQty(ctx, mui, baseUnit,
					materialInfo, qty);
			entryInfo.setBaseQty(baseQty);
			if (materialCompanyInfo != null) {
				BigDecimal standardCost = materialCompanyInfo.getStandardCost();
				if (standardCost != null) {
					BigDecimal unitstandardcost = InvAppUtils.getUnitPrice(ctx,
							materialInfo, standardCost, baseUnit, mui);
					BigDecimal standardcost = unitstandardcost
							.multiply(entryInfo.getQty());
					entryInfo.setUnitStandardCost(unitstandardcost);
					entryInfo.setStandardCost(standardcost);
				} else {
					entryInfo.setUnitStandardCost(null);
					entryInfo.setStandardCost(null);
				}
			}
			
			/*======================add by lvjida start====================*/
			BigDecimal entryQty = entryInfo.getQty();
			if(entryQty == null){
				if (lineData.containsKey("FEntry_qty")) {
					data = ((DataToken) lineData.get("FEntry_qty")).data;
					if (data != null) {
						entryQty = new BigDecimal(data.toString());
						entryInfo.setQty(entryQty);
						
						entryInfo.setUnWriteOffQty(entryQty);
						entryInfo.setUnWriteOffBaseQty(entryQty);
					}
				}
			}
			
			if (lineData.containsKey("FEntry_qty")) {
				data = ((DataToken) lineData.get("FEntry_qty")).data;
				if (data != null) {
					entryQty = new BigDecimal(data.toString());
					entryInfo.setUnWriteOffQty(entryQty);
					entryInfo.setUnWriteOffBaseQty(entryQty);
				}
			}
			
			BigDecimal entryTaxAmount = entryInfo.getTaxAmount();
			if(entryTaxAmount == null){
				if (lineData.containsKey("FEntry_taxAmount")) {
					data = ((DataToken) lineData.get("FEntry_taxAmount")).data;
					if (data != null) {
						entryTaxAmount = new BigDecimal(data.toString());
						entryInfo.setTaxAmount(entryTaxAmount);
					}
				}
			}
			
//			if (lineData.containsKey("FEntry_taxAmount")) {
//				data = ((DataToken) lineData.get("FEntry_taxAmount")).data;
//				if (data != null) {
//					entryTaxAmount = new BigDecimal(data.toString());
//					entryInfo.setUnWriteOffAmount(entryTaxAmount);
//				}
//			}
			
			BigDecimal entryRate = entryInfo.getTaxRate();
			if(entryRate == null){
				if (lineData.containsKey("FEntry_taxRate")) {
					data = ((DataToken) lineData.get("FEntry_taxRate")).data;
					if (data != null) {
						entryRate = new BigDecimal(data.toString());
						entryInfo.setTaxRate(entryRate);
					}
				}
			}
			
			
			
			if(entryQty != null && entryTaxAmount != null && entryRate != null){
				//单价 = 价税合计/数量/(1+税率/100)
				double qtyD = entryQty.doubleValue();
				logger.info("===数量 = "+qtyD);
				double taxAmountD = entryTaxAmount.doubleValue();
				logger.info("===含税金额 = "+taxAmountD);
				double rateD = entryRate.doubleValue();
				logger.info("===税率 = "+rateD);
				double priceD = taxAmountD/qtyD/(1 + rateD/100);
				logger.info("===单价 = "+priceD);
				BigDecimal price = new BigDecimal(priceD).setScale(6, BigDecimal.ROUND_HALF_UP); 
				entryInfo.setPrice(price);
				entryInfo.setUnitActualCost(price);//单位实际成本
				entryInfo.setUnitPurchaseCost(price);//单位采购成本
				entryInfo.setActualPrice(price);//实际单价
				
				//含税单价 = 价税合计/数量
				double taxPriceD = taxAmountD/qtyD;
				logger.info("===含税单价 = "+taxPriceD);
				BigDecimal taxPrice = new BigDecimal(taxPriceD).setScale(6, BigDecimal.ROUND_HALF_UP); 
				entryInfo.setTaxPrice(taxPrice);
				entryInfo.setActualTaxPrice(taxPrice);//实际含税单价	
				
				//金额 = 价税合计/(1+税率/100)
				double amountD = taxAmountD/(1+rateD/100);
				logger.info("===金额 = "+amountD);
				BigDecimal amount = new BigDecimal(amountD).setScale(2, BigDecimal.ROUND_HALF_UP); 
				entryInfo.setAmount(amount);
				entryInfo.setPurchaseCost(amount);//采购成本
				entryInfo.setActualCost(amount);//实际成本
				entryInfo.setUnWriteOffAmount(amount);//未核销金额
				//税额 = 价税合计 - 金额
				double taxD = taxAmountD - amountD;
				logger.info("===税额  = "+taxD);
				BigDecimal tax = new BigDecimal(taxD);
				entryInfo.setTax(tax);
				entryInfo.setLocalTax(tax);//本位币税额
				
				entryInfo.setLocalTaxAmount(entryTaxAmount);//本位币价税合计
				entryInfo.setDiscountAmount(new BigDecimal(0));//折扣额
				entryInfo.setDiscountRate(new BigDecimal(0));//折扣率
				
			}
			
			/*======================add by lvjida end====================*/

			// add by limin lin at Feb 27,2012
			// 根据价税合计和数量计算含税单价
			if (entryInfo.getTaxPrice() == null
					|| entryInfo.getTaxPrice() == ZERO) {
				// 价税合计
				BigDecimal taxAmount = ZERO;
				data = null;
				if (lineData.containsKey("FEntry_taxAmount")) {
					data = ((DataToken) lineData.get("FEntry_taxAmount")).data;
					if (data != null) {
						taxAmount = new BigDecimal(data.toString());
						BigDecimal taxPrice = ZERO;
						if (qty != ZERO) {
							taxPrice = taxAmount.divide(qty, amtPrecision, 4)
									.abs();
						}
						entryInfo.setTaxPrice(taxPrice);
					}
				}
			}
			// add by limin lin at Mary 01,2012
			// 单位材料成本处理
			BigDecimal unitMaterialCost = ZERO;
			data = null;
			if (lineData.containsKey("FEntry_unitMaterialCost")) {
				data = ((DataToken) lineData.get("FEntry_unitMaterialCost")).data;
				if (data != null) {
					unitMaterialCost = new BigDecimal(data.toString());
				}
			}
			unitMaterialCost.setScale(amtPrecision, 4);
			entryInfo.setUnitMaterialCost(unitMaterialCost);

			BigDecimal materialCost = unitMaterialCost.multiply(qty).setScale(
					amtPrecision, 4);
			entryInfo.setMaterialCost(materialCost);

			data = null;
			if (lineData.containsKey("FEntry$purchaseOrgUnit_number"))
				data = ((DataToken) lineData
						.get("FEntry$purchaseOrgUnit_number")).data;
			if (data != null) {
				String number = data.toString();
				if (number != null && number.trim().length() > 0) {
					try {
						IPurchaseOrgUnit ipou = PurchaseOrgUnitFactory
								.getLocalInstance(ctx);
						PurchaseOrgUnitCollection collection = ipou
								.getPurchaseOrgUnitCollection(getFilter(number
										.trim()));
						if (collection != null && collection.size() > 0) {
							PurchaseOrgUnitInfo info = collection.get(0);
							if (info != null)
								entryInfo.setPurchaseOrgUnit(info);
							else {
								message = String.format("第%s行：%s 采购组织不存在",
										currentRowIndex, data);
								messageList.add(message);
								// throw new
								// TaskExternalException(data.toString() +
								// " 采购组织不存在");
							}
						} else {
							message = String.format("第%s行：%s 采购组织不存在",
									currentRowIndex, data);
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " 采购组织不存在");
						}
					} catch (Exception ex) {
						message = String.format(
								"第%s行：FEntry$purchaseOrgUnit_number引入产生错误：%s",
								currentRowIndex, ex.getMessage());
						messageList.add(message);
						// throw new TaskExternalException(ex.getMessage(),
						// ex.getCause());
					}
				}
			}

			entryInfo
					.setIsPresent(getBooleanField(lineData, "FEntry_isPresent"));

			data = null;
			if (lineData.containsKey("FEntry$storageOrgUnit_number"))
				data = ((DataToken) lineData
						.get("FEntry$storageOrgUnit_number")).data;
			if (data != null && data.toString().trim().length() > 0) {
				IStorageOrgUnit isou = StorageOrgUnitFactory
						.getLocalInstance(ctx);
				StorageOrgUnitCollection collection = isou
						.getStorageOrgUnitCollection(getFilter(data.toString()
								.trim()));
				if (collection != null && collection.size() > 0) {
					StorageOrgUnitInfo org = collection.get(0);
					if (!org.isIsBizUnit()) {
						message = String.format("第%s行：%s", currentRowIndex,
								getResource(ctx, "StorageOrgUnitIsNotBizUnit",
										resources));
						messageList.add(message);
						// throw new TaskExternalException(getResource(ctx,
						// "StorageOrgUnitIsNotBizUnit", resources));
					} else
						entryInfo.setStorageOrgUnit(org);
				} else {
					if (sou == null) {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data, getResource(ctx,
										"StorageOrgUnitNotExists", resources));
						messageList.add(message);
						// throw new
						// TaskExternalException(data.toString().trim() + " " +
						// getResource(ctx, "StorageOrgUnitNotExists",
						// resources));
					} else
						entryInfo.setStorageOrgUnit(sou);
				}
			} else {
				if (sou == null) {
					message = String.format("第%s行：%s %s", currentRowIndex,
							data, getResource(ctx, "StorageOrgUnitNotNull",
									resources));
					messageList.add(message);
					// throw new TaskExternalException(getResource(ctx,
					// "StorageOrgUnitNotNull", resources));
				} else
					entryInfo.setStorageOrgUnit(sou);
			}
		} catch (Exception ex) {
			message = String.format("第%s行：数据引入产生错误：%s", currentRowIndex, ex
					.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(ex.getMessage(), ex.getCause());
		}

		entryInfo.setSourceBillEntrySeq(0);
		entryInfo.setPurOrderEntrySeq(0);
		entryInfo.setHasSameCou(false);
		entryInfo.setDosingType(DosingTypeEnum.BOM);
		entryInfo.setBaseStatus(EntryBaseStatusEnum.NULL);

		if (PurInWarehsBillInfo.getTransactionType() != null) {
			// if (PurInWarehsBillInfo.getTransactionType().getSourceBillType()
			// == null)
			// {
			// entryInfo.setOutLocation(entryInfo.getLocation());
			// entryInfo.setOutWarehouse(entryInfo.getWarehouse());
			// }
		}

		boolean isNegativeBill = PurInWarehsBillInfo.isIsReversed()
				^ isReturnRIType(PurInWarehsBillInfo.getTransactionType());

		if (entryInfo.getReturnBaseQty() == null)
			entryInfo.setReturnBaseQty(ZERO);
		if (entryInfo.getBaseQty() == null)
			entryInfo.setBaseQty(ZERO);
		entryInfo.setUnReturnedBaseQty(entryInfo.getBaseQty().abs().subtract(
				entryInfo.getReturnBaseQty().abs()));

		if (isNegativeBill)
			entryInfo.setUnReturnedBaseQty(entryInfo.getUnReturnedBaseQty()
					.abs().negate());
		else
			entryInfo.setUnReturnedBaseQty(entryInfo.getUnReturnedBaseQty()
					.abs());

		// add by limin lin at Feb 24, 2012
		// 如果是采购类型为委外类型，需要对未核销数量进行处理
		if (PurchaseTypeEnum.SUBCONTRACT.equals(PurInWarehsBillInfo
				.getPurchaseType())) {
			setEntryForSubContract(entryInfo);
		}

		return entryInfo;
	}

	private void setEntryForSubContract(PurInWarehsEntryInfo entryInfo) {
		DosingTypeEnum dosingType = entryInfo.getDosingType();
		if (DosingTypeEnum.NONE.equals(dosingType)) {
			entryInfo.setScUnWrittenOffQty(ZERO);
			entryInfo.setScUnWrittenOffBaseQty(ZERO);
			entryInfo.setScWrittenOffQty(formatBigDecimal(entryInfo.getQty()));
			entryInfo.setScWrittenOffBaseQty(formatBigDecimal(entryInfo
					.getBaseQty()));
		} else {
			entryInfo
					.setScUnWrittenOffQty(formatBigDecimal(entryInfo.getQty()));
			entryInfo.setScUnWrittenOffBaseQty(formatBigDecimal(entryInfo
					.getBaseQty()));
			entryInfo.setScWrittenOffQty(ZERO);
			entryInfo.setScWrittenOffBaseQty(ZERO);
		}
	}

	public BigDecimal formatBigDecimal(BigDecimal value) {
		return value != null ? value : ZERO;
	}

	public boolean isReturnRIType(TransactionTypeInfo transType) {
		if (transType == null)
			return false;
		return transType.getRIType().getValue() == 30
				|| transType.getRIType().getValue() == 20
				|| transType.getRIType().getValue() == 21
				|| transType.getRIType().getValue() == 14
				|| transType.getRIType().getValue() == 43
				|| transType.getRIType().getValue() == 22
				|| transType.getRIType().getValue() == 50;
	}

	private EntityViewInfo getFilter(String number) {
		EntityViewInfo viewInfo = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(
				new FilterItemInfo("number", number, CompareType.EQUALS));
		viewInfo.setFilter(filter);
		return viewInfo;
	}

	private EntityViewInfo getFilter(String value, String byProperty) {
		EntityViewInfo viewInfo = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(
				new FilterItemInfo(byProperty, value, CompareType.EQUALS));
		viewInfo.setFilter(filter);
		return viewInfo;
	}

	private static String getResource(Context ctx, String strKey,
			String resource) {
		if (strKey == null || strKey.trim().length() == 0)
			return null;
		else
			return SCMUtils.getResource(resource, strKey, ctx);
	}

	private String getIdFromNumber(String number, Context ctx)
			throws TaskExternalException {
		PurInWarehsBillCollection collection;
		try {
			collection = PurInWarehsBillFactory.getLocalInstance(ctx)
					.getPurInWarehsBillCollection(
							"where number ='" + number + "'");
		} catch (BOSException e) {
			throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		if (collection != null && collection.size() > 0)
			return collection.get(0).getId().toString();
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public Hashtable exportTransmit(IRowSet rs, Context ctx)
			throws TaskExternalException {
		Hashtable result = new Hashtable();
		try {
			if (rs.getString("Number") != null)
				result.put("FNumber", rs.getString("Number"));
			if (rs.getString("BizDate") != null)
				result.put("FBizDate", rs.getString("BizDate"));
			if (rs.getString("TRANSACTIONTYPE.NUMBER") != null)
				result.put("FTransactionType_number", rs
						.getString("TRANSACTIONTYPE.NUMBER"));
			if (rs.getString("TRANSACTIONTYPE.NAME") != null)
				result.put("FTransactionType_name_l2", rs
						.getString("TRANSACTIONTYPE.NAME"));
			if (rs.getString("STORAGEORGUNIT.NUMBER") != null)
				result.put("FStorageOrgUnit_number", rs
						.getString("STORAGEORGUNIT.NUMBER"));
			if (rs.getString("STORAGEORGUNIT.NAME") != null)
				result.put("FStorageOrgUnit_name_l2", rs
						.getString("STORAGEORGUNIT.NAME"));
			if (rs.getString("COSTCENTERORGUNIT.NUMBER") != null)
				result.put("FCostCenterOrgUnit_number", rs
						.getString("COSTCENTERORGUNIT.NUMBER"));
			if (rs.getString("ADMINORGUNIT.NUMBER") != null)
				result.put("FAdminOrgUnit_number", rs
						.getString("ADMINORGUNIT.NUMBER"));
			if (rs.getString("MATERIAL.NUMBER") != null)
				result.put("FEntry$material_number", rs
						.getString("MATERIAL.NUMBER"));
			if (rs.getString("MATERIAL.NAME") != null)
				result.put("FEntry$material_name_l2", rs
						.getString("MATERIAL.NAME"));
			if (rs.getString("MATERIAL.MODEL") != null)
				result.put("FEntry$material_model", rs
						.getString("MATERIAL.MODEL"));
			if (rs.getString("ASSISTPROPERTY.NUMBER") != null)
				result.put("FEntry$assistProperty_number", rs
						.getString("ASSISTPROPERTY.NUMBER"));
			if (rs.getString("COSTOBJECT.NUMBER") != null)
				result.put("FEntry$costObject_number", rs
						.getString("COSTOBJECT.NUMBER"));
			if (rs.getString("COSTOBJECT.NAME") != null)
				result.put("FEntry$costObject_name_l2", rs
						.getString("COSTOBJECT.NAME"));
			if (rs.getString("ENTRY.LOT") != null)
				result.put("FEntry_lot", rs.getString("ENTRY.LOT"));
			if (rs.getString("ENTRY.MFG") != null)
				result.put("FEntry_mfg", rs.getString("ENTRY.MFG"));
			if (rs.getString("ENTRY.EXP") != null)
				result.put("FEntry_exp", rs.getString("ENTRY.EXP"));
			if (rs.getString("UNIT.NUMBER") != null)
				result.put("FEntry$unit_number", rs.getString("UNIT.NUMBER"));
			if (rs.getString("ENTRY.QTY") != null)
				if ("".equalsIgnoreCase(DataImportUtils.transformValue(
						rs.getString("ENTRY.QTY")).toString())
						|| DataImportUtils.transformValue(
								rs.getString("ENTRY.QTY")).toString().length() < 1) {
					result.put("FEntry_qty", DataImportUtils
							.transformValue(null));
				} else {
					BigDecimal qty = new BigDecimal(rs.getString("ENTRY.QTY"));
					result.put("FEntry_qty", qty.abs());
				}
			if (rs.getString("ASSISTUNIT.NUMBER") != null)
				result.put("FEntry$assistUnit_number", rs
						.getString("ASSISTUNIT.NUMBER"));
			if (rs.getString("ENTRY.ASSISTQTY") != null)
				if ("".equalsIgnoreCase(DataImportUtils.transformValue(
						rs.getString("ENTRY.ASSISTQTY")).toString())
						|| DataImportUtils.transformValue(
								rs.getString("ENTRY.ASSISTQTY")).toString()
								.length() < 1) {
					result.put("FEntry_assistQty", DataImportUtils
							.transformValue(null));
				} else {
					BigDecimal qty = new BigDecimal(rs
							.getString("ENTRY.ASSISTQTY"));
					result.put("FEntry_assistQty", qty.abs());
				}
			if (rs.getString("WAREHOUSE.NUMBER") != null)
				result.put("FEntry$warehouse_number", rs
						.getString("WAREHOUSE.NUMBER"));
			if (rs.getString("STOCKER1.NUMBER") != null)
				result.put("FEntry$stocker_number", rs
						.getString("STOCKER1.NUMBER"));
			if (rs.getString("LOCATION.NUMBER") != null)
				result.put("FEntry$location_number", rs
						.getString("LOCATION.NUMBER"));
			if (rs.getString("ENTRY.actualCost") != null)
				if ("".equalsIgnoreCase(DataImportUtils.transformValue(
						rs.getString("ENTRY.actualCost")).toString())
						|| DataImportUtils.transformValue(
								rs.getString("ENTRY.actualCost")).toString()
								.length() < 1) {
					result.put("FEntry_actualCost", DataImportUtils
							.transformValue(null));
				} else {
					BigDecimal qty = new BigDecimal(rs
							.getString("ENTRY.actualCost"));
					result.put("FEntry_actualCost", qty.abs());
				}
			if (rs.getString("ENTRY.UNITACTUALCOST") != null)
				if ("".equalsIgnoreCase(DataImportUtils.transformValue(
						rs.getString("ENTRY.UNITACTUALCOST")).toString())
						|| DataImportUtils.transformValue(
								rs.getString("ENTRY.UNITACTUALCOST"))
								.toString().length() < 1) {
					result.put("FEntry_unitActualCost", DataImportUtils
							.transformValue(null));
				} else {
					BigDecimal qty = new BigDecimal(rs
							.getString("ENTRY.UNITACTUALCOST"));
					result.put("FEntry_unitActualCost", qty.abs());
				}
			if (rs.getString("Entry.manuBillNumber") != null)
				result.put("FEntry_manuBillNumber", rs
						.getString("Entry.manuBillNumber"));
			if (rs.getString("Entry.remark") != null)
				result.put("FEntry_remark", rs.getString("Entry.remark"));
			if (rs.getString("CreateTime") != null)
				result.put("FCreateTime", rs.getString("CreateTime"));
			if (rs.getString("Project.number") != null)
				result.put("FEntry$project_number", rs
						.getString("Project.number"));
			if (rs.getString("TrackNumber.number") != null)
				result.put("FEntry$trackNumber_number", rs
						.getString("TrackNumber.number"));
			if (rs.getString("description") != null)
				result.put("FDescription", rs.getString("description"));
		} catch (Exception Ex) {
			Ex.printStackTrace();
			throw new TaskExternalException("", Ex);
		}
		return result;
	}

	public String getExportQueryInfo(Context ctx) {
		return "com.kingdee.eas.scm.im.inv.PurInWarehsQuery";
	}

	@SuppressWarnings("unchecked")
	public FilterInfo getExportFilterForQuery(Context ctx) {
		FilterInfo filterInfo = null;
		OrgUnitCollection collection = null;
		try {
			collection = SCMGroupServerUtils.getAuthOrgByPermItem(ctx,
					OrgType.Storage, "manufacturerec_export");
		} catch (EASBizException e) {
			e.printStackTrace();
		} catch (BOSException e) {
			e.printStackTrace();
		}
		if (collection != null && collection.size() > 0) {
			LinkedHashSet keys = new LinkedHashSet();
			for (int i = 0; i < collection.size(); i++)
				keys.add(collection.get(i).getId().toString());

			filterInfo = new FilterInfo();
			filterInfo.getFilterItems().add(
					new FilterItemInfo("storageOrgUnit.id", keys,
							CompareType.INCLUDE));
		}
		FilterInfo oldFilterInfo = (FilterInfo) getContextParameter("filter");
		try {
			if (filterInfo != null)
				oldFilterInfo.mergeFilter(filterInfo, "and");
		} catch (BOSException e) {
			e.printStackTrace();
		}
		return oldFilterInfo;
	}

	public static Date getEXP(Date curDate, int unit, int unitValue) {
		if (curDate != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(curDate);
			int field = 5;
			switch (unit) {
			case 1: // '\001'
				field = 1;
				break;

			case 2: // '\002'
				field = 2;
				break;

			case 3: // '\003'
				field = 5;
				break;

			default:
				field = 5;
				break;
			}
			calendar.add(field, unitValue);
			return calendar.getTime();
		} else {
			return null;
		}
	}

	private void checkWarehouse(Context ctx, WarehouseInfo warehouseInfo,
			StorageOrgUnitInfo sou) throws TaskExternalException, BOSException,
			EASBizException {
		ISOAccreditWH iso = SOAccreditWHFactory.getLocalInstance(ctx);
		FilterInfo fi = new FilterInfo();
		fi.getFilterItems().add(
				new FilterItemInfo("WAREHOUSE", warehouseInfo.getId()
						.toString(), CompareType.EQUALS));
		fi.getFilterItems().add(
				new FilterItemInfo("STORAGEORG", sou.getId().toString(),
						CompareType.EQUALS));
		if (!iso.exists(fi)) {
			String message = String.format("第%s行：%s,%s %s", currentRowIndex,
					warehouseInfo.getNumber(), sou.getNumber(), getResource(
							ctx, "RelationNotExists", resources));
			messageList.add(message);
			return;
		}
		// throw new TaskExternalException(warehouseInfo.getNumber() + "," +
		// sou.getNumber() + " " + getResource(ctx, "RelationNotExists",
		// resources));
		else
			return;
	}

	@SuppressWarnings("unchecked")
	private void loadTransaction(Hashtable lineData, Context ctx,
			String byProperty, String propertyName)
			throws TaskExternalException {
		try {
			Object souData = ((DataToken) lineData.get(propertyName)).data;
			if (souData != null && souData.toString().trim().length() > 0) {
				ITransactionType isou = TransactionTypeFactory
						.getLocalInstance(ctx);
				TransactionTypeCollection collection = isou
						.getTransactionTypeCollection(getFilter(souData
								.toString().trim(), byProperty));
				if (collection != null && collection.size() > 0) {
					tti = collection.get(0);
					PurInWarehsBillInfo.setTransactionType(tti);
				} else {
					throw new TaskExternalException(souData.toString().trim()
							+ " "
							+ getResource(ctx, "TransactionTypeNotExists",
									resources));
				}
			} else {
				throw new TaskExternalException(getResource(ctx,
						"TransactionTypeNotNull", resources));
			}
		} catch (BOSException e) {
			throw new TaskExternalException(e.getMessage(), e.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	private void loadsupplier(Hashtable lineData, Context ctx,
			String byProperty, String propertyName)
			throws TaskExternalException {
		try {
			Object supplierInfoData = ((DataToken) lineData.get(propertyName)).data;
			if (supplierInfoData != null
					&& supplierInfoData.toString().trim().length() > 0) {

				ISupplier isupplier = SupplierFactory.getLocalInstance(ctx);
				SupplierCollection collection = isupplier
						.getSupplierCollection(getFilter(supplierInfoData
								.toString().trim(), byProperty));
				if (collection != null && collection.size() > 0) {
					PurInWarehsBillInfo.setSupplier(collection.get(0));
				} else {
					throw new TaskExternalException(supplierInfoData.toString()
							.trim()
							+ " "
							+ getResource(ctx, "SupplierNotExists", resources));
				}
			} else {
				throw new TaskExternalException(getResource(ctx,
						"SupplierNotNull", resources));
			}
		} catch (BOSException e) {
			throw new TaskExternalException(e.getMessage(), e.getCause());
		}
	}

	@SuppressWarnings("unchecked")
	private void loadstorageOrgUnit(Hashtable lineData, Context ctx,
			String byProperty, String propertyName)
			throws TaskExternalException {
		try {
			Object souData = ((DataToken) lineData.get(propertyName)).data;
			if (souData != null && souData.toString().trim().length() > 0) {
				IStorageOrgUnit isou = StorageOrgUnitFactory
						.getLocalInstance(ctx);
				StorageOrgUnitCollection collection = isou
						.getStorageOrgUnitCollection(getFilter(souData
								.toString().trim(), byProperty));
				if (collection != null && collection.size() > 0) {
					sou = collection.get(0);
					if (!sou.isIsBizUnit())
						throw new TaskExternalException(getResource(ctx,
								"StorageOrgUnitIsNotBizUnit", resources));
					PurInWarehsBillInfo.setStorageOrgUnit(sou);
				} else {
					throw new TaskExternalException(souData.toString().trim()
							+ " "
							+ getResource(ctx, "StorageOrgUnitNotExists",
									resources));
				}
			} else {
				throw new TaskExternalException(getResource(ctx,
						"StorageOrgUnitNotNull", resources));
			}
		} catch (BOSException e) {
			throw new TaskExternalException(e.getMessage(), e.getCause());
		}
	}

	private boolean checkTransactionTypeInEnum() {
		return tti != null
				&& (RITypeEnum.PurRt.equals(tti.getRIType())
						|| RITypeEnum.SaleRt.equals(tti.getRIType())
						|| RITypeEnum.ReqRt.equals(tti.getRIType()) || RITypeEnum.ManuRt
						.equals(tti.getRIType()));
	}

	@SuppressWarnings("unchecked")
	private void complateInfo(Hashtable lineData, Context ctx) {
		logger.info("==========complateInfo=========");
		logger.info("=====业务类型 = " + lineData.get("FBizType"));
		Object souData = ((DataToken) lineData.get("FBizType")).data;
		logger.info("=====业务类型 = " + souData);
		if (souData != null) {
			BizTypeInfo bizTypeInfo = null;
			try {
				bizTypeInfo = BizTypeFactory.getLocalInstance(ctx)
						.getBizTypeInfo(
								" where number = '" + souData.toString() + "'");
			} catch (EASBizException e) {
				e.printStackTrace();
			} catch (BOSException e) {
				e.printStackTrace();
			}
			PurInWarehsBillInfo.setBizType(bizTypeInfo);
		}
		// if(tti != null)
		// PurInWarehsBillInfo.setBizType(tti.getBizType());
	}

	@SuppressWarnings("unchecked")
	private void checkNumberAndAmount(PurInWarehsEntryInfo entryInfo,
			Context ctx, Hashtable lineData, String checkList[],
			String setList[], boolean checkBackType)
			throws TaskExternalException {
		for (int i = 0; i < checkList.length; i++) {
			try {
				Object o = null;
				if (lineData.containsKey(checkList[i]))
					o = ((DataToken) lineData.get(checkList[i])).data;
				if (o != null && o.toString().trim().length() > 0) {
					BigDecimal value = new BigDecimal(o.toString());
					if (value != null) {
						if (value.compareTo(ZERO) < 0)
							throw new TaskExternalException(checkList[i]
									+ getResource(ctx,
											"import_price_must_positive",
											resources));

						// 排除单价、含税单价、实际单价、实际含税单价根据事务类型取负数
						boolean exclude = false;
						for (String str : DECIMAL_ENTRY_PROPERTY_EXCLUDENEGATE) {
							if (str == DECIMAL_ENTRY_PROPERTY_ALL[i]) {
								exclude = true;
								break;
							}
						}

						if (checkTransactionTypeInEnum() && !exclude)
							entryInfo.setBigDecimal(setList[i], value.abs()
									.negate());
						else
							entryInfo.setBigDecimal(setList[i], value.abs());
						continue;
					}
					if (checkList[i].equalsIgnoreCase(setList[0]))
						throw new TaskExternalException(checkList[i]
								+ getResource(ctx, "Numbercannotbenull",
										resources));
					if (!checkList[i].equals(ASSISTQTY_PROPERTY[0]))
						entryInfo.setBigDecimal(setList[i], ZERO);
					continue;
				}
				if (checkList[i].equalsIgnoreCase(QTY_PROPERTY[0]))
					throw new TaskExternalException(checkList[i]
							+ getResource(ctx, "Numbercannotbenull", resources));
				if (!checkList[i].equals(ASSISTQTY_PROPERTY[0]))
					entryInfo.setBigDecimal(setList[i], ZERO);
				continue;
			} catch (NumberFormatException nex) {
				nex.printStackTrace();
			}
			throw new TaskExternalException(checkList[i]
					+ getResource(ctx, "FormatMustBeNumber", resources));
		}

	}

	public static BigDecimal getBaseUnitQty(MeasureUnitInfo mui,
			MaterialInfo mi, BigDecimal qty, Context ctx) {
		BigDecimal baseQty;
		BigDecimal coefficient = new BigDecimal("1.00");
		baseQty = new BigDecimal("0.00000");
		if (qty != null)
			baseQty = qty;
		else
			qty = baseQty;
		try {
			MultiMeasureUnitInfo mulUnit = getMulUnit(mui, mi, ctx);

			if (mulUnit != null) {
				coefficient = mulUnit.getBaseConvsRate();
				if (coefficient != null) {
					baseQty = qty.multiply(coefficient);
					MeasureUnitInfo baseUnit = mi.getBaseUnit();
					if (baseUnit == null) {
						IMaterial im = MaterialFactory.getLocalInstance(ctx);
						baseUnit = im.getMaterialInfo(
								new ObjectUuidPK(mi.getId())).getBaseUnit();
					}
					mulUnit = getMulUnit(baseUnit, mi, ctx);
					int precision = mulUnit.getQtyPrecision();
					baseQty = baseQty.setScale(precision, 4);
				}
			}

			return baseQty;
		} catch (BaseException e) {
			e.printStackTrace();
			ExceptionHandler.handle(e);
			return null;
		}
	}

	public static MultiMeasureUnitInfo getMulUnit(MeasureUnitInfo mui,
			MaterialInfo mi, Context ctx) throws BaseException {
		MultiMeasureUnitInfo mulUnit = null;
		IMultiMeasureUnit immu = MultiMeasureUnitFactory.getRemoteInstance();
		if (mui != null && mi != null)
			mulUnit = immu.getMultiUnit(mi.getId().toString(), mui.getId()
					.toString());
		return mulUnit;
	}

	@SuppressWarnings("unchecked")
	public boolean isSameBlock(Hashtable firstData, Hashtable currentData) {
		if (firstData == null || currentData == null)
			return false;
		DataToken firstNumber = (DataToken) firstData.get(getMainField());
		DataToken currentNumber = (DataToken) currentData.get(getMainField());
		return firstNumber != null && currentNumber != null
				&& firstNumber.data != null && !"".equals(firstNumber.data)
				&& currentNumber.data != null && !"".equals(currentNumber.data)
				&& firstNumber.equals(currentNumber);
	}

	private void LogError(String taskUuid, String message) {
		try {
			LogError(taskUuid, new Exception(message));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void LogError(String taskUuid, Exception ex) throws Exception {
		TaskStateEnum taskState = TaskStateEnum.FAILEND;
		TaskLog log;
		try {
			log = TaskLogUtil.getLog(taskUuid);
			if (log == null)
				log = new TaskLog(0);

			log.setLogUuID(taskUuid);
			log.setTaskState(taskState);
			String errorMsg = ex.getMessage();
			log.addException(errorMsg, ex);
			TaskLogUtil.putLog(log);
		} catch (Exception e1) {
			logger.error(e1);
		}
	}

	protected static final Logger logger;
	static {
		logger = Logger
				.getLogger(com.kingdee.eas.scm.im.inv.app.GRNManufactureRecImport.class);
	}
}