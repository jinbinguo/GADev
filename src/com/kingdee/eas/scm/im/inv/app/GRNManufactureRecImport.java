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
import com.kingdee.eas.basedata.master.material.*;
import com.kingdee.eas.basedata.master.material.TimeUnitEnum;
import com.kingdee.eas.basedata.org.*;
import com.kingdee.eas.basedata.person.*;
import com.kingdee.eas.basedata.scm.common.*;
import com.kingdee.eas.basedata.scm.im.inv.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.mm.basedata.*;
import com.kingdee.eas.mm.project.IProject;
import com.kingdee.eas.mm.project.ProjectCollection;
import com.kingdee.eas.mm.project.ProjectFactory;
import com.kingdee.eas.mm.project.ProjectInfo;
import com.kingdee.eas.scm.common.BillBaseStatusEnum;
import com.kingdee.eas.scm.common.EntryBaseStatusEnum;
import com.kingdee.eas.scm.common.SCMBillException;
import com.kingdee.eas.scm.common.app.SCMGroupServerUtils;
import com.kingdee.eas.scm.common.app.SCMServerUtils;
import com.kingdee.eas.scm.common.util.SCMUtils;
import com.kingdee.eas.scm.im.inv.*;
import com.kingdee.eas.scm.sm.pur.util.DataImportUtils;
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

public class GRNManufactureRecImport extends AbstractDataTransmission {

	ManufactureRecBillInfo ManufactureRecBillInfo;
	private static String resources = "com.kingdee.eas.scm.im.inv.app.OtherBillDataImportResource";
	private boolean IsHaveValue;
	private boolean IsError;
	StorageOrgUnitInfo sou;
	TransactionTypeInfo tti;
	CompanyOrgUnitInfo cou;
	String STRING_FIELDS[] = { "FNumber", "FTransactionType_number",
			"FStorageOrgUnit_number", "FCostCenterOrgUnit_number",
			"FAdminOrgUnit_number", "FEntry$material_number",
			"FEntry$assistProperty_number", "FEntry$material_model",
			"FEntry$costObject_number", "FEntry$unit_number",
			"FEntry$assistUnit_number", "FEntry$companyOrgUnit_number",
			"FEntry$warehouse_number", "FEntry$stocker_number",
			"FEntry$location_number", "FEntry_manuBillNumber", "FEntry_remark",
			"FEntry$project_number", "FEntry$trackNumber_number",
			"FDescription" };
	String DATE_FIELDS[] = { "FBizDate", "FEntry_mfg", "FEntry_exp" };
	String STRING_FIELDS_EN[] = { "FTransactionType_name_l2",
			"FStorageOrgUnit_name_l2", "FEntry$material_name_l2",
			"FEntry$costObject_name_l2" };
	String BIGDECIMAL_FIELDS[] = { "FEntry_qty", "FEntry_unitStandardCost",
			"FEntry_unitActualCost" };
	String INTEGER_FIELDS[] = { "FEntry_lot" };
	private static final String QTY_PROPERTY[] = { "qty" };
	private static final String ASSISTQTY_PROPERTY[] = { "assistQty" };// "FEntry_unitActualCost"
	// ,
	private static final String DECIMAL_ENTRY_PROPERTY_ALL[] = { "FEntry_qty",
			"FEntry_assistQty", "FEntry_actualCost" };// "unitActualCost",
	private static final String DECIMAL_ENTRY_PROPERTY_ALL_KEY[] = { "qty",
			"assistQty", "actualCost" };
	private static final BigDecimal ZERO = new BigDecimal("0.00");
	private int currentRowIndex = 1;
	private ArrayList<String> messageList = new ArrayList<String>();

	public GRNManufactureRecImport() {
		ManufactureRecBillInfo = null;
		IsHaveValue = false;
		IsError = false;
		sou = null;
		tti = null;
		cou = null;
	}

	protected ICoreBase getController(Context ctx) throws TaskExternalException {
		try {
			return ManufactureRecBillFactory.getLocalInstance(ctx);
		} catch (BOSException e) {
			e.printStackTrace();
			throw new TaskExternalException("", e);
		}
	}

	@SuppressWarnings("unchecked")
	public CoreBaseInfo transmit(Hashtable hsData, Context ctx)
			throws TaskExternalException {
		ManufactureRecBillInfo = null;
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
					ManufactureRecBillInfo = transmitHead(lineData, ctx);
					if (ManufactureRecBillInfo == null)
						return null;
					complateInfo(lineData, ctx);
				}
			} catch (Exception ex) {
				message = String.format("第%s行：表头引入发生未知异常：%s", currentRowIndex,
						ex.getMessage());
				messageList.add(message);
			}
			try {
				ManufactureRecBillEntryInfo entryinfo = transmitEntry(lineData,
						ctx);
				int seq = ManufactureRecBillInfo.getEntry().size() + 1;
				entryinfo.setSeq(seq);
				entryinfo.setParent(ManufactureRecBillInfo);
				entryinfo.setStorageOrgUnit(sou);
				// add by limin lin at Feb 06,2012
				// 设置财务组织
				entryinfo.setCompanyOrgUnit(cou);
				ManufactureRecBillInfo.getEntry().add(entryinfo);
			} catch (Exception ex) {
				message = String.format("第%s行：表体引入发生未知异常：%s", currentRowIndex,
						ex.getMessage());
				messageList.add(message);
			}
			k++;
		}
		try {
			// add by limin lin at Feb 06,2012
			// 自动计算totalActualCost
			BigDecimal totalActualCost = ZERO;
			for (int i = 0; i < ManufactureRecBillInfo.getEntry().size(); i++) {
				totalActualCost = totalActualCost.add(ManufactureRecBillInfo
						.getEntry().get(i).getActualCost());
			}
			ManufactureRecBillInfo.setTotalActualCost(totalActualCost);
		} catch (Exception ex) {
			message = String.format("单据：%s计算totalActualCost时发生未知异常：%s",
					ManufactureRecBillInfo.getNumber(), ex.getMessage());
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
		return ManufactureRecBillInfo;
	}

	public int getSubmitType() {
		return 1;
	}

	public void submit(CoreBaseInfo coreBaseInfo, Context ctx)
			throws TaskExternalException {
		if (coreBaseInfo == null
				|| !(coreBaseInfo instanceof ManufactureRecBillInfo))
			return;
		try {
			ManufactureRecBillInfo bill = (ManufactureRecBillInfo) coreBaseInfo;
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
	private ManufactureRecBillInfo transmitHead(Hashtable lineData, Context ctx)
			throws TaskExternalException {
		ManufactureRecBillInfo = new ManufactureRecBillInfo();
		String message = "";
		Object data = null;
		String str = null;
		boolean tryDoNumber = false;
		boolean userCodingRuleManager = false;
		Object number = ((DataToken) lineData.get("FNumber")).data;
		if (number != null && number.toString().trim().length() > 0) {
			ManufactureRecBillInfo.setNumber(number.toString().trim());
			String existId = getIdFromNumber(number.toString().trim(), ctx);
			if (!isSltImportUpdate() && !StringUtil.isEmptyString(existId)) {
				message = String.format("第%s行：%s %s", currentRowIndex, number
						.toString().trim(), getResource(ctx, "EXISTS",
						resources));
				messageList.add(message);
				return ManufactureRecBillInfo;
				// throw new TaskExternalException(number.toString().trim() +
				// getResource(ctx, "EXISTS", resources));
			}
			if (isSltImportUpdate() && !StringUtil.isEmptyString(existId)) {
				ManufactureRecBillInfo existInfo = null;
				try {
					existInfo = ((IManufactureRecBill) getController(ctx))
							.getManufactureRecBillInfo(new ObjectUuidPK(existId));
				} catch (Exception e) {
					message = String.format("第%s行：获取单据%s产生错误：",
							currentRowIndex, number.toString().trim(), e
									.getMessage());
					messageList.add(message);
					return ManufactureRecBillInfo;
					// throw new TaskExternalException("", e);
				}
				if (!BillBaseStatusEnum.TEMPORARILYSAVED.equals(existInfo
						.getBaseStatus())) {
					message = String.format("第%s行：%s %s", currentRowIndex,
							number.toString().trim(), getResource(ctx,
									"UnTEMPORARILYSAVEDERROE", resources));
					messageList.add(message);
					return ManufactureRecBillInfo;
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
		String description = "";
		if (lineData.containsKey("FDescription"))
			description = (String) ((DataToken) lineData.get("FDescription")).data;
		if (!StringUtil.isEmptyString(description))
			// if (description != null && description.toString().trim().length()
			// > 0)
			ManufactureRecBillInfo
					.setDescription(description.toString().trim());
		else
			ManufactureRecBillInfo.setDescription("");
		try {
			loadstorageOrgUnit(lineData, ctx, "number",
					"FStorageOrgUnit_number");
		} catch (Exception e_number) {
			try {
				loadstorageOrgUnit(lineData, ctx, "name",
						"FStorageOrgUnit_name_l2");
			} catch (Exception e_name) {
				// throw new
				// TaskExternalException("["+number.toString().trim()+"]"
				// +e_number.getMessage(), e_number.getCause());
				message = String.format("第%s行：库存组织不存在", currentRowIndex);
				messageList.add(message);
			}
		}
		try {
			loadTransaction(lineData, ctx, "number", "FTransactionType_number");
		} catch (Exception e_number) {
			try {
				loadTransaction(lineData, ctx, "name",
						"FTransactionType_name_l2");
			} catch (Exception e_name) {
				// throw new TaskExternalException(
				// "[第"+rowNum+"行]"+e_number.getMessage(), e_number.getCause());
				message = String.format("第%s行：事务类型编码不存在", currentRowIndex);
				messageList.add(message);
			}
		}
		try {
			if (tryDoNumber) {
				boolean isUsedParam = DataImportUtils.getImportParam(ctx);
				if (isUsedParam) {
					String souID = sou.getId().toString().trim();
					ICodingRuleManager iCodingRuleManager = CodingRuleManagerFactory
							.getLocalInstance(ctx);
					if (iCodingRuleManager.isExist(ManufactureRecBillInfo,
							souID))
						ManufactureRecBillInfo.setNumber(iCodingRuleManager
								.getNumber(ManufactureRecBillInfo, souID, ""));
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
		ManufactureRecBillInfo.setCU(ContextUtil.getCurrentCtrlUnit(ctx));
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
			ManufactureRecBillInfo.setBizDate(da);
			// add by limin lin at Feb 04,2012
			// 根据业务日期计算业务年度，业务期间，年，月字段值
			Calendar bizCal = Calendar.getInstance();
			bizCal.setTime(da);
			int bizYear = bizCal.get(Calendar.YEAR);
			int bizMonth = bizCal.get(Calendar.MONTH) + 1;
			ManufactureRecBillInfo.setPeriod(bizMonth);
			ManufactureRecBillInfo.setYear(bizYear);
			DateFormat monthFormatter = new SimpleDateFormat("yyyyMM");
			DateFormat dayFormatter = new SimpleDateFormat("yyyyMMdd");
			ManufactureRecBillInfo.setMonth(Integer.parseInt(monthFormatter
					.format(da)));
			ManufactureRecBillInfo.setDay(Integer.parseInt(dayFormatter
					.format(da)));
		} catch (ParseException e1) {
			message = String.format("第%s行：%s %s", currentRowIndex, lineData
					.get("FBizDate").toString(), getResource(ctx,
					"InvalidDateFormat", resources));
			messageList.add(message);
			// throw new
			// TaskExternalException(lineData.get("FBizDate").toString() +
			// getResource(ctx, "InvalidDateFormat", resources));
		}
		// modified by limin lin at Feb 04,2012
		// 单据状态处理
		BillBaseStatusEnum baseStatus = BillBaseStatusEnum.TEMPORARILYSAVED;
		if (lineData.containsKey("FBaseStatus")) {
			String baseStatusStr = ((DataToken) lineData.get("FBaseStatus")).data
					.toString();
			int billBaseStatusEnum = new Integer(baseStatusStr);
			baseStatus = BillBaseStatusEnum.getEnum(billBaseStatusEnum);
		}

		ManufactureRecBillInfo.setBaseStatus(baseStatus);
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
							ManufactureRecBillInfo.setCreator(info);
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
			ManufactureRecBillInfo.setCreator(userinfo);
		}
		IsHaveValue = false;
		IsError = false;
		Object createdate = null;
		if (lineData.containsKey("FCreateTime")) {
			createdate = ((DataToken) lineData.get("FCreateTime")).data;
			if (createdate != null && createdate.toString().length() > 0)
				try {
					createdate = df.parseObject(createdate.toString());
					ManufactureRecBillInfo.setCreateTime(new Timestamp(
							((Date) createdate).getTime()));
					IsHaveValue = true;
				} catch (ParseException e) {
					IsError = false;
				}
		}
		if (!IsError || !IsHaveValue) {
			Date date = new Date();
			ManufactureRecBillInfo.setCreateTime(new Timestamp(date.getTime()));
		}
		BillTypeInfo aBillTypeInfo = new BillTypeInfo();
		aBillTypeInfo.setId(BOSUuid
				.read("50957179-0105-1000-e000-0167c0a812fd463ED552"));
		aBillTypeInfo.setNumber("105");
		ManufactureRecBillInfo.setBillType(aBillTypeInfo);
		data = ((DataToken) lineData.get("FCostCenterOrgUnit_number")).data;
		if (data != null && data.toString().trim().length() > 0) {
			try {

				ICostCenterOrgUnit iCostCenterOrgUnit = CostCenterOrgUnitFactory
						.getLocalInstance(ctx);
				CostCenterOrgUnitCollection collection = iCostCenterOrgUnit
						.getCostCenterOrgUnitCollection(getFilter(data
								.toString().trim()));
				if (collection != null && collection.size() > 0) {
					com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo info = collection
							.get(0);
					if (info != null)
						ManufactureRecBillInfo.setCostCenterOrgUnit(info);
					else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(),
								getResource(ctx, "CostCenterOrgUnitNotExists",
										resources));
						messageList.add(message);
					}
					// throw new TaskExternalException(data.toString() + "  " +
					// getResource(ctx, "CostCenterOrgUnitNotExists",
					// resources));
				} else {
					message = String.format("第%s行：%s %s", currentRowIndex, data
							.toString(), getResource(ctx,
							"CostCenterOrgUnitNotExists", resources));
					messageList.add(message);
					// throw new TaskExternalException(data.toString() + "  " +
					// getResource(ctx, "CostCenterOrgUnitNotExists",
					// resources));
				}

			} catch (Exception ex) {
				message = String.format("第%s行：%s成本中心引入产生错误 ：%s",
						currentRowIndex, data.toString(), ex.getMessage());
				messageList.add(message);
				// throw new TaskExternalException(ex.getMessage(),
				// ex.getCause());
			}
		}
		data = ((DataToken) lineData.get("FAdminOrgUnit_number")).data;
		if (data != null) {
			str = data.toString();
			if (str != null && str.length() > 0)
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
							ManufactureRecBillInfo.setAdminOrgUnit(info);
						else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data.toString(),
									getResource(ctx, "adminOrgUnitNotExists",
											resources));
							messageList.add(message);
						}
						// throw new TaskExternalException(data.toString() +
						// "  " + getResource(ctx, "adminOrgUnitNotExists",
						// resources));
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(), getResource(ctx,
										"adminOrgUnitNotExists", resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() +
						// "  " + getResource(ctx, "adminOrgUnitNotExists",
						// resources));
					}
				} catch (Exception ex) {
					message = String.format(
							"第%s行：FAdminOrgUnit_number：%s 引入产生错误 ：%s",
							currentRowIndex, data.toString(), ex.getMessage());
					messageList.add(message);
					// throw new TaskExternalException(ex.getMessage(),
					// ex.getCause());
				}
		}
		boolean isReversed = false;
		data = ((DataToken) lineData.get("FIsReversed")).data;
		if (data != null) {
			str = data.toString();
			if (str != null && str.length() > 0) {
				isReversed = Boolean.parseBoolean(str);
			}
		}
		ManufactureRecBillInfo.setIsReversed(isReversed);
		boolean isInitBill = false;
		data = ((DataToken) lineData.get("FIsInitBill")).data;
		if (data != null) {
			str = data.toString();
			if (str != null && str.length() > 0) {
				isInitBill = Boolean.parseBoolean(str);
			}
		}
		ManufactureRecBillInfo.setIsInitBill(isInitBill);
		return ManufactureRecBillInfo;
	}

	@SuppressWarnings("unchecked")
	private ManufactureRecBillEntryInfo transmitEntry(Hashtable lineData,
			Context ctx) throws TaskExternalException {
		ManufactureRecBillEntryInfo entryInfo = new ManufactureRecBillEntryInfo();
		String message = "";
		Object data = null;
		String str = "";
		MaterialInfo materialInfo = new MaterialInfo();
		MeasureUnitInfo mui = new MeasureUnitInfo();
		MaterialCompanyInfoInfo materialCompanyInfo = null;
		MaterialInventoryInfo materialInventory = null;
		String DATE_ENTRY_PROPERTY[] = { "FEntry_mfg", "FEntry_exp" };
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < DATE_ENTRY_PROPERTY.length; i++) {
			if (!lineData.containsKey(DATE_ENTRY_PROPERTY[i]))
				continue;
			Object o = ((DataToken) lineData.get(DATE_ENTRY_PROPERTY[i])).data;
			if (o == null || o.toString().length() <= 0)
				continue;
			try {
				o = df.parse(o.toString());
			} catch (ParseException pex) {
				pex.printStackTrace();
				message = String.format("第%s行：%s %s", currentRowIndex,
						getResource(ctx, "InvalidDateFormat", resources), o
								.toString());
				messageList.add(message);
				// throw new TaskExternalException(getResource(ctx,
				// "InvalidDateFormat", resources) + o.toString());
			}
			if (o == null || !(o instanceof Date))
				continue;
			Date value = (Date) o;
			if (value != null)
				entryInfo.setDate(DATE_ENTRY_PROPERTY[i], value);
		}

		try {
			data = ((DataToken) lineData.get("FEntry$material_number")).data;
			if (data != null && data.toString().length() > 0) {
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
								message = String.format("第%s行：%s %s %s",
										currentRowIndex, materialInfo
												.getNumber(), sou.getNumber(),
										getResource(ctx,
												"NO_INVENTORY_PROPERTY",
												resources));
								messageList.add(message);
							}
							// throw new
							// TaskExternalException(materialInfo.getNumber() +
							// " " + sou.getNumber() + " " + getResource(ctx,
							// "NO_INVENTORY_PROPERTY", resources));
							materialCompanyInfo = imaterial.getCompanyInfo(
									materialInfo.getId().toString(), cou
											.getId().toString());
							if (materialCompanyInfo == null) {
								message = String.format("第%s行：%s %s %s",
										currentRowIndex, materialInfo
												.getNumber(), cou.getNumber(),
										getResource(ctx, "NO_COMPANY_PROPERTY",
												resources));
								messageList.add(message);
							}
							// throw new
							// TaskExternalException(materialInfo.getNumber() +
							// " " + cou.getNumber() + " " + getResource(ctx,
							// "NO_COMPANY_PROPERTY", resources));
						} else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data.toString(),
									getResource(ctx, "MaterialNotExists",
											resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx, "MaterialNotExists",
							// resources));
						}
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(), getResource(ctx,
										"MaterialNotExists", resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "MaterialNotExists", resources));
					}
				}
			} else {
				message = String.format("第%s行：%s", currentRowIndex,
						getResource(ctx, "MaterialNotExists", resources));
				messageList.add(message);
				// throw new TaskExternalException(getResource(ctx,
				// "MaterialNotExists", resources));
			}
		} catch (Exception ex) {
			message = String.format("第%s行：FEntry$material_number引入产生错误：%s",
					currentRowIndex, ex.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(ex.getMessage(), ex.getCause());
		}
		try {
			data = null;
			if (lineData.containsKey("FEntry_lot")) {
				data = ((DataToken) lineData.get("FEntry_lot")).data;
				if (checkLotInfo(ctx, materialInfo, data, sou, entryInfo))
					entryInfo.setLot(null);
				else
					entryInfo.setLot(data.toString().trim());
			}
			if (data == null || data.toString() == null
					|| data.toString().trim().length() == 0)
				entryInfo.setLot(null);
			else
				entryInfo.setLot(data.toString().trim());
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
		} catch (Exception ex) {
			message = String.format("第%s行：FEntry_lot引入产生错误： %s",
					currentRowIndex, ex.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(ex.getMessage(), ex.getCause());
		}
		// removed by limin lin at May 14, 2012
		// set the unit via material's baseunit
		entryInfo.setUnit(materialInfo.getBaseUnit());
		mui = materialInfo.getBaseUnit();
		// try
		// {
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
		// if (!materialInfo.equals(new MaterialInfo()))
		// {
		// checkUnit(ctx, materialInfo, measureUnitInfo);
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
		// ,data.toString(),getResource(ctx, "UnitCanNotBeNull", resources));
		// messageList.add(message);
		// //throw new TaskExternalException(data.toString() + " " +
		// getResource(ctx, "UnitCanNotBeNull", resources));
		// }
		// } else
		// {
		// message = String.format("第%s行：%s", currentRowIndex ,getResource(ctx,
		// "UnitCanNotBeNull", resources));
		// messageList.add(message);
		// //throw new TaskExternalException(data.toString() + " " +
		// getResource(ctx, "UnitCanNotBeNull", resources));
		// }
		// }
		// catch (Exception ex)
		// {
		// message = String.format("第%s行：FEntry$unit_number引入产生错误： %s",
		// currentRowIndex ,ex.getMessage());
		// messageList.add(message);
		// //throw new TaskExternalException(ex.getMessage(), ex.getCause());
		// }
		try {
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
		} catch (Exception ex) {
			message = String.format("第%s行：FEntry_assistQty引入产生错误： %s",
					currentRowIndex, ex.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(ex.getMessage(), ex.getCause());
		}
		checkNumberAndAmount(entryInfo, ctx, lineData,
				DECIMAL_ENTRY_PROPERTY_ALL, DECIMAL_ENTRY_PROPERTY_ALL_KEY,
				true);
		// add by limin lin at Feb 04, 2012
		// 单位实际成本处理
		BigDecimal unitActualCost = ZERO;
		data = null;
		if (lineData.containsKey("FEntry_unitActualCost")) {
			data = ((DataToken) lineData.get("FEntry_unitActualCost")).data;
			try {
				if (data != null) {
					unitActualCost = new BigDecimal(data.toString());
				}
			} catch (NumberFormatException nex) {
				nex.printStackTrace();
				message = String.format("第%s行：FEntry_unitActualCost %s",
						currentRowIndex, getResource(ctx, "FormatMustBeNumber",
								resources));
				messageList.add(message);
				// throw new TaskExternalException(checkList[i] +
				// getResource(ctx, "FormatMustBeNumber", resources));
			}
		}
		entryInfo.setBigDecimal("unitActualCost", unitActualCost);
		// add by limin lin at Feb 10, 2012
		// acutalCost和associateQty处理
		if (entryInfo.getQty() != null) {
			entryInfo
					.setActualCost(entryInfo.getQty().multiply(unitActualCost));
			entryInfo.setAssociateQty(entryInfo.getQty().abs());
		}

		// add by limin lin at Feb 06, 2012
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

		try {
			data = null;
			if (lineData.containsKey("FEntry$assistProperty_number")) {
				data = ((DataToken) lineData
						.get("FEntry$assistProperty_number")).data;
				checkMaterialInfo(ctx, materialInfo, data, 3);
			}
			if (data != null) {
				String number = data.toString();
				if (number != null && number.trim().length() > 0) {
					IAsstAttrValue iasstAttrValue = AsstAttrValueFactory
							.getLocalInstance(ctx);
					AsstAttrValueCollection collection = iasstAttrValue
							.getAsstAttrValueCollection("where number='"
									+ number + "'");
					if (collection != null && collection.size() > 0) {
						com.kingdee.eas.basedata.master.material.AsstAttrValueInfo info = collection
								.get(0);
						if (info != null)
							entryInfo.setAssistProperty(info);
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								number, getResource(ctx, "AsstAttrValueExists",
										resources));
						messageList.add(message);
						// throw new TaskExternalException(number +
						// getResource(ctx, "AsstAttrValueExists", resources));
					}
				}
			}
		} catch (BOSException e) {
			message = String.format(
					"第%s行：FEntry$assistProperty_number引入发生错误：%s",
					currentRowIndex, e.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		if (entryInfo.getAssistUnit() != null)
			entryInfo.setAssistQty((BigDecimal) entryInfo.get("assistQty"));
		else
			entryInfo.setAssistQty(ZERO);
		data = null;
		if (lineData.containsKey("FEntry$costObject_number"))
			data = ((DataToken) lineData.get("FEntry$costObject_number")).data;
		if (data != null) {
			str = data.toString();
			if (str != null && str.trim().length() > 0)
				try {
					ICostObject iCostObject = CostObjectFactory
							.getLocalInstance(ctx);
					CostObjectCollection collection = iCostObject
							.getCostObjectCollection(getFilter(str));
					if (collection != null && collection.size() > 0) {
						com.kingdee.eas.basedata.assistant.CostObjectInfo info = collection
								.get(0);
						if (info != null)
							entryInfo.setCostObject(info);
						else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data.toString(),
									getResource(ctx, "costObjectNotExist",
											resources));
							messageList.add(message);
						}
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "costObjectNotExist", resources));
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(), getResource(ctx,
										"costObjectNotExist", resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "costObjectNotExist", resources));
					}
				} catch (Exception ex) {
					message = String.format(
							"第%s行：FEntry$costObject_number引入发生错误：%s",
							currentRowIndex, ex.getMessage());
					messageList.add(message);
					// throw new TaskExternalException(ex.getMessage(),
					// ex.getCause());
				}
		}
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
							if (!info.getWhState().equals(WHStateEnum.ACTIVE)) {
								message = String.format("第%s行：%s %s",
										currentRowIndex, info.getNumber(),
										getResource(ctx, "WarehouseNotActive",
												resources));
								messageList.add(message);
							}
							// throw new TaskExternalException(info.getNumber()
							// + " " + getResource(ctx, "WarehouseNotActive",
							// resources));
							else {
								checkWarehouse(ctx, info, sou);
								entryInfo.setWarehouse(info);
								warehouseInfo = info;
							}
						} else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data, getResource(ctx,
											"WarehouseNotExists", resources));
							messageList.add(message);
						}
					} else {
						message = String.format("第%s行： %s %s", currentRowIndex,
								data, getResource(ctx, "WarehouseNotExists",
										resources));
						messageList.add(message);
					}
				}
			} else {
				message = String.format("第%s行：%s", currentRowIndex,
						getResource(ctx, "WarehouseNotExists", resources));
				messageList.add(message);
			}
		} catch (Exception e) {
			message = String.format("第%s行：FEntry$warehouse_number引入产生错误：%s",
					currentRowIndex, e.getMessage());
			messageList.add(message);
		}
		data = null;
		if (lineData.containsKey("FEntry$stocker_number"))
			data = ((DataToken) lineData.get("FEntry$stocker_number")).data;
		if (data != null) {
			str = data.toString();
			if (str != null && str.trim().length() > 0) {

				IPerson iPerson = null;
				PersonCollection collection = null;
				try {
					iPerson = PersonFactory.getLocalInstance(ctx);
					collection = iPerson.getPersonCollection(getFilter(str));
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
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data, getResource(ctx, "stockerNotExists",
										resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "stockerNotExists", resources));
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
		}
		try {
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
								// throw new
								// TaskExternalException(info.getNumber() + " "
								// + getResource(ctx,
								// "import_location_not_match_warehouse",
								// resources) + " " +
								// warehouseInfo.getNumber());
							}
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
		} catch (Exception ex) {
			message = String.format("第%s行：FEntry$location_number引入产生错误：%s",
					currentRowIndex, ex.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(ex.getMessage(), ex.getCause());
		}
		Object omanuBillNumber = null;
		if (lineData.containsKey("FEntry_manuBillNumber"))
			omanuBillNumber = ((DataToken) lineData
					.get("FEntry_manuBillNumber")).data;
		if (omanuBillNumber != null
				&& omanuBillNumber.toString().trim().length() > 0
				&& omanuBillNumber != null
				&& omanuBillNumber.toString().length() > 0)
			entryInfo.setManuBillNumber(omanuBillNumber.toString());
		Object o = null;
		if (lineData.containsKey("FEntry_remark"))
			o = ((DataToken) lineData.get("FEntry_remark")).data;
		if (o != null && o.toString().trim().length() > 0 && o != null
				&& o.toString().length() > 0)
			entryInfo.setRemark(o.toString());
		try {
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
		} catch (Exception e) {
			message = String.format("第%s行：产生错误：%s", currentRowIndex, e
					.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		try {
			data = null;
			if (lineData.containsKey("FEntry$project_number"))
				data = ((DataToken) lineData.get("FEntry$project_number")).data;
			if (data != null) {
				String number = data.toString();
				if (number != null && number.trim().length() > 0) {
					IProject iProject = ProjectFactory.getLocalInstance(ctx);
					EntityViewInfo viewInfo = new EntityViewInfo();
					HashSet status = new HashSet();
					status.add(new Integer(4));
					FilterInfo filter = new FilterInfo();
					filter.getFilterItems().add(
							new FilterItemInfo("number", number,
									CompareType.EQUALS));
					filter.getFilterItems().add(
							new FilterItemInfo("baseStatus", status,
									CompareType.INCLUDE));
					FilterInfo filter2 = new FilterInfo();
					CtrlUnitInfo cu = ManufactureRecBillInfo
							.getStorageOrgUnit().getCU();
					ObjectUuidPK cuPK = null;
					if (cu == null)
						cuPK = new ObjectUuidPK(BOSUuid
								.create((new ProjectInfo()).getBOSType()));
					else
						cuPK = new ObjectUuidPK(cu.getId());
					try {
						filter2 = iProject.getDatabaseDFilter(cuPK, "id",
								"CU.id");
					} catch (EASBizException e) {
						ExceptionHandler.handle(e);
					} catch (BOSException e) {
						ExceptionHandler.handle(e);
					}
					if (filter2 != null)
						filter.mergeFilter(filter2, "and");
					viewInfo.setFilter(filter);
					ProjectCollection coll = iProject
							.getProjectCollection(viewInfo);
					if (coll != null && coll.size() > 0) {
						ProjectInfo projectInfo = coll.get(0);
						entryInfo.setProject(projectInfo);
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data, getResource(ctx, "PROJECT_NUMBER_ERROR",
										resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "PROJECT_NUMBER_ERROR",
						// resources));
					}
				}
			}
		} catch (Exception ex) {
			message = String.format("第%s行：FEntry$project_number引入产生错误：%s",
					currentRowIndex, ex.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(ex.getMessage(), ex.getCause());
		}
		try {
			data = null;
			if (lineData.containsKey("FEntry$trackNumber_number"))
				data = ((DataToken) lineData.get("FEntry$trackNumber_number")).data;
			if (data != null) {
				String number = data.toString();
				if (number != null && number.trim().length() > 0) {
					ITrackNumber iTrackNumber = TrackNumberFactory
							.getLocalInstance(ctx);
					EntityViewInfo viewInfo = new EntityViewInfo();
					HashSet status = new HashSet();
					status.add(new Integer(10));
					FilterInfo filter = new FilterInfo();
					filter.getFilterItems().add(
							new FilterItemInfo("number", number,
									CompareType.EQUALS));
					filter.getFilterItems().add(
							new FilterItemInfo("status", status,
									CompareType.INCLUDE));
					FilterInfo filter2 = new FilterInfo();
					CtrlUnitInfo cu = ManufactureRecBillInfo
							.getStorageOrgUnit().getCU();
					ObjectUuidPK cuPK = null;
					if (cu == null)
						cuPK = new ObjectUuidPK(BOSUuid
								.create((new ProjectInfo()).getBOSType()));
					else
						cuPK = new ObjectUuidPK(cu.getId());
					try {
						filter2 = iTrackNumber.getDatabaseDFilter(cuPK, "id",
								"CU.id");
					} catch (EASBizException e) {
						ExceptionHandler.handle(e);
					} catch (BOSException e) {
						ExceptionHandler.handle(e);
					}
					if (filter2 != null)
						filter.mergeFilter(filter2, "and");
					viewInfo.setFilter(filter);
					TrackNumberCollection coll = iTrackNumber
							.getTrackNumberCollection(viewInfo);
					if (coll != null && coll.size() > 0) {
						com.kingdee.eas.mm.basedata.TrackNumberInfo trackNumberInfo = coll
								.get(0);
						entryInfo.setTrackNumber(trackNumberInfo);
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data, getResource(ctx, "TRACK_NUMBER_ERROR",
										resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "TRACK_NUMBER_ERROR", resources));
					}
				}
			}
		} catch (Exception ex) {
			message = String.format("第%s行：FEntry$trackNumber_number引入产生错误：%s",
					currentRowIndex, ex.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(ex.getMessage(), ex.getCause());
		}
		entryInfo.setIsPresent(false);
		entryInfo.setSourceBillEntrySeq(0);
		entryInfo.setManuBillEntrySeq(0);
		entryInfo.setSaleOrderEntrySeq(0);
		entryInfo.setBaseStatus(EntryBaseStatusEnum.ADD);
		return entryInfo;
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
		ManufactureRecBillCollection collection;
		try {
			collection = ManufactureRecBillFactory.getLocalInstance(ctx)
					.getManufactureRecBillCollection(
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
		return "com.kingdee.eas.scm.im.inv.ManufactureRecDataImportQuery";
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

	private boolean checkLotInfo(Context ctx, MaterialInfo materialInfo,
			Object data, StorageOrgUnitInfo sou,
			ManufactureRecBillEntryInfo entryInfo)
			throws TaskExternalException, BOSException, EASBizException {
		// IMaterial iMaterial = MaterialFactory.getLocalInstance(ctx);
		MaterialInventoryInfo materialInventoryInfo = MaterialInventoryFactory
				.getLocalInstance(ctx).getMaterialInventoryInfo(
						" where orgunit = '" + sou.getId().toString()
								+ "' and material = '"
								+ materialInfo.getId().toString() + "'");
		// MaterialInventoryInfo materialInventoryInfo =
		// iMaterial.getInventoryInfo(materialInfo.getId().toString(),
		// sou.getId().toString());
		boolean isLotNumber = materialInventoryInfo.isIsLotNumber();
		if ((!isLotNumber) && (data != null) && (data.toString().length() > 0))
			return false;
		if ((isLotNumber)
				&& ((data == null) || (data.toString().length() <= 0))) {
			String message = String.format("第%s行：%s %s", new Object[] {
					Integer.valueOf(this.currentRowIndex),
					materialInfo.getNumber(),
					getResource(ctx, "LotManager", resources) });
			this.messageList.add(message);
			return false;
		}

		return true;
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

	private void checkMaterialInfo(Context ctx, MaterialInfo materialInfo,
			Object data, int type) throws TaskExternalException, BOSException {
		if (type == 3) {
			com.kingdee.eas.basedata.master.material.AsstAttrTypeInfo asstAttrTypeInfo = materialInfo
					.getAssistAttr();
			if (asstAttrTypeInfo == null && data != null
					&& data.toString().length() > 0) {
				String message = String.format("第%s行：%s %s", currentRowIndex,
						materialInfo.getNumber(), getResource(ctx,
								"NotSetAssistProperty", resources));
				messageList.add(message);
				return;
			}
			// throw new TaskExternalException(materialInfo.getNumber() +
			// getResource(ctx, "NotSetAssistProperty", resources));
			if (asstAttrTypeInfo != null
					&& (data == null || data.toString().length() <= 0)) {
				String message = String.format("第%s行：%s %s", currentRowIndex,
						materialInfo.getNumber(), getResource(ctx,
								"SetAssistProperty", resources));
				messageList.add(message);
				return;
			}
			// throw new TaskExternalException(materialInfo.getNumber() +
			// getResource(ctx, "SetAssistProperty", resources));
		} else if (type == 4) {
			MeasureUnitInfo assistUnit = materialInfo.getAssistUnit();
			if (assistUnit == null && data != null
					&& data.toString().length() > 0) {
				String message = String.format("第%s行：%s %s", currentRowIndex,
						materialInfo.getNumber(), getResource(ctx,
								"NotSetAssistUnit", resources));
				messageList.add(message);
				return;
			}
			// throw new TaskExternalException(materialInfo.getNumber() +
			// getResource(ctx, "NotSetAssistUnit", resources));
			if (assistUnit != null
					&& (data == null || data.toString().length() <= 0)) {
				String message = String.format("第%s行：%s %s", currentRowIndex,
						materialInfo.getNumber(), getResource(ctx,
								"SetAssistUnit", resources));
				messageList.add(message);
				return;
			}
			// throw new TaskExternalException(materialInfo.getNumber() +
			// getResource(ctx, "SetAssistUnit", resources));
		}
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
					ManufactureRecBillInfo.setTransactionType(tti);
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
					ManufactureRecBillInfo.setStorageOrgUnit(sou);
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
			ManufactureRecBillInfo.setBizType(bizTypeInfo);
		}
		// if(tti != null)
		// ManufactureRecBillInfo.setBizType(tti.getBizType());
	}

	@SuppressWarnings("unchecked")
	private void checkNumberAndAmount(ManufactureRecBillEntryInfo entryInfo,
			Context ctx, Hashtable lineData, String checkList[],
			String setList[], boolean checkBackType)
			throws TaskExternalException {
		String message = "";
		for (int i = 0; i < checkList.length; i++) {
			try {
				Object o = null;
				if (lineData.containsKey(checkList[i]))
					o = ((DataToken) lineData.get(checkList[i])).data;
				if (o != null && o.toString().trim().length() > 0) {
					BigDecimal value = new BigDecimal(o.toString());
					if (value != null) {
						if (value.compareTo(ZERO) < 0) {
							message = String.format("第%s行：%s %s",
									currentRowIndex, checkList[i], getResource(
											ctx, "import_price_must_positive",
											resources));
							messageList.add(message);
							continue;
						}
						// throw new TaskExternalException(checkList[i] +
						// getResource(ctx, "import_price_must_positive",
						// resources));
						if (checkTransactionTypeInEnum())
							entryInfo.setBigDecimal(setList[i], value.abs()
									.negate());
						else
							entryInfo.setBigDecimal(setList[i], value.abs());
						continue;
					}
					if (checkList[i].equalsIgnoreCase(setList[0])) {
						message = String.format("第%s行：%s %s", currentRowIndex,
								checkList[i], getResource(ctx,
										"Numbercannotbenull", resources));
						messageList.add(message);
						continue;
					}
					// throw new TaskExternalException(checkList[i] +
					// getResource(ctx, "Numbercannotbenull", resources));
					if (!checkList[i].equals(ASSISTQTY_PROPERTY[0]))
						entryInfo.setBigDecimal(setList[i], ZERO);
					continue;
				}
				if (checkList[i].equalsIgnoreCase(QTY_PROPERTY[0])) {
					message = String.format("第%s行：%s %s", currentRowIndex,
							checkList[i], getResource(ctx,
									"Numbercannotbenull", resources));
					messageList.add(message);
					continue;
				}
				// throw new TaskExternalException(checkList[i] +
				// getResource(ctx, "Numbercannotbenull", resources));
				if (!checkList[i].equals(ASSISTQTY_PROPERTY[0]))
					entryInfo.setBigDecimal(setList[i], ZERO);
				continue;
			} catch (NumberFormatException nex) {
				nex.printStackTrace();
				message = String.format("第%s行：%s %s", currentRowIndex,
						checkList[i], getResource(ctx, "FormatMustBeNumber",
								resources));
				messageList.add(message);
				// throw new TaskExternalException(checkList[i] +
				// getResource(ctx, "FormatMustBeNumber", resources));
			}
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