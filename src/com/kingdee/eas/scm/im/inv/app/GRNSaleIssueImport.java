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
import com.kingdee.eas.basedata.master.cssp.*;
import com.kingdee.eas.basedata.master.material.*;
import com.kingdee.eas.basedata.master.material.app.DataImportTools;
import com.kingdee.eas.basedata.org.*;
import com.kingdee.eas.basedata.person.*;
import com.kingdee.eas.basedata.scm.common.*;
import com.kingdee.eas.basedata.scm.im.inv.*;
import com.kingdee.eas.basedata.scm.sd.channel.UpdataStorageEnum;
import com.kingdee.eas.basedata.scm.sd.sale.*;
import com.kingdee.eas.basedata.scm.sm.srm.IsUseableEnum;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.scm.common.BillBaseStatusEnum;
import com.kingdee.eas.scm.common.EntryBaseStatusEnum;
import com.kingdee.eas.scm.common.SCMBillException;
import com.kingdee.eas.scm.common.app.SCMGroupServerUtils;
import com.kingdee.eas.scm.common.app.SCMServerUtils;
import com.kingdee.eas.scm.common.util.SCMUtils;
import com.kingdee.eas.scm.im.inv.*;
import com.kingdee.eas.scm.sd.channel.SaleBackProfitTypeEnum;
import com.kingdee.eas.scm.sm.pur.util.DataImportUtils;
import com.kingdee.eas.tools.datatask.AbstractDataTransmission;
import com.kingdee.eas.tools.datatask.TaskStateEnum;
import com.kingdee.eas.tools.datatask.core.TaskExternalException;
import com.kingdee.eas.tools.datatask.log.TaskLog;
import com.kingdee.eas.tools.datatask.log.TaskLogUtil;
import com.kingdee.eas.tools.datatask.runtime.DataToken;
import com.kingdee.eas.util.ResourceBase;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.eas.util.client.ExceptionHandler;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.BaseException;
import com.kingdee.util.enums.Enum;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;
import java.util.Locale;

import org.apache.log4j.Logger;

public class GRNSaleIssueImport extends AbstractDataTransmission {

	SaleIssueBillInfo saleIssueBillInfo;
	public static String Locale_en = "l1";
	public static String Locale_zh = "l2";
	public static String Locale_tw = "l3";
	private static final String QTY_PROPERTY[] = { "qty" };
	private static final String ASSISTQTY_PROPERTY[] = { "assistQty" };
	private static final String DECIMAL_ENTRY_PROPERTY_ALL[] = { "FEntry_qty",
			"FEntry_assistQty", "FEntry_unitActualCost", "FEntry_actualCost",
			"FEntry_price", "FEntry_taxRate", "FEntry_taxPrice",
			"FEntry_discountAmount", "FEntry_discount", "FEntry_actualPrice",
			"FEntry_actualTaxPrice", "FEntry_amount", "FEntry_localAmount",
			"FEntry_tax", "FEntry_localTax", "FEntry_taxAmount",
			"FEntry_localTaxAmount" };
	private static final String DECIMAL_ENTRY_PROPERTY_ALL_KEY[] = { "qty",
			"assistQty", "unitActualCost", "actualCost", "salePrice",
			"taxRate", "taxPrice", "discountAmount", "discount", "actualPrice",
			"price", "nonTaxAmount", "localNonTaxAmount", "tax", "localTax",
			"amount", "localAmount" };
	private static final String DATE_BILL_PROPERTY[] = { "FBizDate" };
	private static final String DATE_BILL_PROPERTY_KEY[] = { "bizDate" };
	private static final String DATE_ENTRY_PROPERTY[] = { "mfg", "exp" };
	private String DECIMAL_ENTRY_PROPERTY_EXCLUDENEGATE[] = { "FEntry_price",
			"FEntry_taxPrice", "FEntry_actualPrice", "FEntry_actualTaxPrice",
			"FEntry_taxRate", "FEntry_discountAmount", "FEntry_discount" };
	private String EXCEL_DATE_ENTRY_PROPERTY[] = { "FEntry_MFG", "FEntry_EXP" };
	private static String resources = "com.kingdee.eas.scm.im.inv.app.OtherBillDataImportResource";
	StorageOrgUnitInfo sou;
	TransactionTypeInfo tti;
	CompanyOrgUnitInfo cou;
	private static final BigDecimal ZERO = new BigDecimal("0.00");
	private int currentRowIndex = 1;
	private ArrayList<String> messageList = new ArrayList<String>();

	public GRNSaleIssueImport() {
		saleIssueBillInfo = null;
		sou = null;
		tti = null;
		cou = null;
	}

	protected ICoreBase getController(Context ctx) throws TaskExternalException {
		try {
			return SaleIssueBillFactory.getLocalInstance(ctx);
		} catch (BOSException e) {
			e.printStackTrace();
			throw new TaskExternalException("", e);
		}
	}

	private static String getResource(Context ctx, String strKey,
			String resource) {
		if (strKey == null || strKey.trim().length() == 0)
			return null;
		else
			return SCMUtils.getResource(resource, strKey, ctx);
	}

	@SuppressWarnings("unchecked")
	public CoreBaseInfo transmit(Hashtable hsData, Context ctx)
			throws TaskExternalException {
		saleIssueBillInfo = null;
		messageList.clear();
		String message = "";
		super.getMainField();
		int keyCursor = 0;
		for (int i = 0; i < hsData.size(); i++) {
			int currentStart = Integer.parseInt(getContextParameter(
					"CURRENTSTART").toString());
			currentRowIndex = currentStart + i;
			Hashtable lineData = (Hashtable) hsData.get(new Integer(i));
			try {
				if (keyCursor == 0) {
					saleIssueBillInfo = transmitHead(lineData, ctx);
					if (saleIssueBillInfo == null)
						return null;
					complateInfo(lineData, ctx);
				}
			} catch (Exception ex) {
				message = String.format("第%s行：表头引入发生未知异常：%s", currentRowIndex,
						ex.getMessage());
				messageList.add(message);
			}
			try {
				SaleIssueEntryInfo entry = transmitEntry(lineData, ctx);
				int seq = saleIssueBillInfo.getEntry().size() + 1;
				entry.setSeq(seq);
				entry.setParent(saleIssueBillInfo);
				entry.setStorageOrgUnit(sou);
				entry.setCompanyOrgUnit(cou);
				saleIssueBillInfo.getEntry().add(entry);
			} catch (Exception ex) {
				message = String.format("第%s行：表体引入发生未知异常：%s", currentRowIndex,
						ex.getMessage());
				messageList.add(message);
			}
			keyCursor++;
		}

		try {
			(new SaleIssueAlgorithmForInfo(saleIssueBillInfo, ctx))
					.calTotalFields();
		} catch (Exception e) {
			message = String.format("单据:%s,合计计算时产生错误：%s", saleIssueBillInfo
					.getNumber(), e.getMessage());
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
		return saleIssueBillInfo;
	}

	@SuppressWarnings("unchecked")
	private SaleIssueBillInfo transmitHead(Hashtable lineData, Context ctx)
			throws TaskExternalException {
		saleIssueBillInfo = new SaleIssueBillInfo();
		String message = "";
		boolean tryDoNumber = false;
		boolean userCodingRuleManager = false;
		Object number = ((DataToken) lineData.get("FNumber")).data;
		if (number != null && number.toString().trim().length() > 0) {
			saleIssueBillInfo.setNumber(number.toString().trim());
			String existId = getIdFromNumber(number.toString().trim(), ctx);
			if (!isSltImportUpdate() && !StringUtil.isEmptyString(existId)) {
				message = String.format("第%s行：%s %s", currentRowIndex, number
						.toString().trim(), getResource(ctx, "EXISTS",
						resources));
				messageList.add(message);
				return saleIssueBillInfo;
				// throw new TaskExternalException(number.toString().trim() +
				// getResource(ctx, "EXISTS", resources));
			}
			if (isSltImportUpdate() && !StringUtil.isEmptyString(existId)) {
				SaleIssueBillInfo existInfo = null;
				try {
					existInfo = ((ISaleIssueBill) getController(ctx))
							.getSaleIssueBillInfo(new ObjectUuidPK(existId));
				} catch (Exception e) {
					message = String.format("第%s行：获取单据%s产生错误：",
							currentRowIndex, number.toString().trim(), e
									.getMessage());
					messageList.add(message);
					return saleIssueBillInfo;
					// throw new TaskExternalException("", e);
				}
				if (!BillBaseStatusEnum.TEMPORARILYSAVED.equals(existInfo
						.getBaseStatus())) {
					message = String.format("第%s行：%s %s", currentRowIndex,
							number.toString().trim(), getResource(ctx,
									"UnTEMPORARILYSAVEDERROE", resources));
					messageList.add(message);
					return saleIssueBillInfo;
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
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < DATE_BILL_PROPERTY.length; i++) {
			Object date = null;
			if (lineData.containsKey(DATE_BILL_PROPERTY[i])) {
				date = ((DataToken) lineData.get(DATE_BILL_PROPERTY[i])).data;
			}
			if (date != null && date.toString().length() > 0) {
				try {
					date = df.parse(date.toString());
				} catch (ParseException e) {
					e.printStackTrace();
					message = String.format("第%s行：%s %s", currentRowIndex,
							date, getResource(ctx, "InvalidDateFormat",
									resources));
					messageList.add(message);
					// throw new TaskExternalException(getResource(ctx,
					// "InvalidDateFormat", resources) + date.toString());
				}
				if (date != null && (date instanceof Date)) {
					Date da = (Date) date;
					saleIssueBillInfo.setDate(DATE_BILL_PROPERTY_KEY[i], da);
					// add by limin lin at Feb 07,2012
					// 根据业务日期计算业务年度，业务期间，年，月字段值
					if (DATE_BILL_PROPERTY[i].equals("FBizDate")) {
						saleIssueBillInfo.setActBizDate(da);
						Calendar bizCal = Calendar.getInstance();
						bizCal.setTime(da);
						int bizYear = bizCal.get(Calendar.YEAR);
						int bizMonth = bizCal.get(Calendar.MONTH) + 1;
						saleIssueBillInfo.setPeriod(bizMonth);
						saleIssueBillInfo.setYear(bizYear);
						DateFormat monthFormatter = new SimpleDateFormat(
								"yyyyMM");
						DateFormat dayFormatter = new SimpleDateFormat(
								"yyyyMMdd");
						saleIssueBillInfo.setMonth(Integer
								.parseInt(monthFormatter.format(da)));
						saleIssueBillInfo.setDay(Integer.parseInt(dayFormatter
								.format(da)));
					}
				}

				continue;
			}
			if (DATE_BILL_PROPERTY[i].equals("FBizDate")) {
				message = String.format("第%s行：%s %s", currentRowIndex,
						getResource(ctx, "InvalidDateFormat", resources), date);
				messageList.add(message);
				// throw new TaskExternalException(getResource(ctx,
				// "InvalidDateFormat", resources) + date.toString());
			}
		}

		Object description = null;
		if (lineData.containsKey("FDescription"))
			description = ((DataToken) lineData.get("FDescription")).data;
		if (description != null && description.toString().trim().length() > 0)
			saleIssueBillInfo.setDescription(description.toString().trim());
		else
			saleIssueBillInfo.setDescription("");

		try {
			loadstorageOrgUnit(lineData, ctx, "number",
					"FStorageOrgUnit_number");
		} catch (Exception e_number) {
			try {
				loadstorageOrgUnit(lineData, ctx, "name",
						"FStorageOrgUnit_name_l2");
			} catch (Exception e_name) {
				// throw new TaskExternalException(e_number.getMessage(),
				// e_number.getCause());
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
				// throw new TaskExternalException(e_number.getMessage(),
				// e_number.getCause());
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
					if (iCodingRuleManager.isExist(saleIssueBillInfo, souID))
						saleIssueBillInfo.setNumber(iCodingRuleManager
								.getNumber(saleIssueBillInfo, souID, ""));
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
		try {
			Object souData = null;
			if (lineData.containsKey("FCustomer_number"))
				souData = ((DataToken) lineData.get("FCustomer_number")).data;
			if (souData != null && souData.toString().trim().length() > 0) {
				ICustomer ic = CustomerFactory.getLocalInstance(ctx);
				CustomerCollection collection = ic
						.getCustomerCollection(getFilter(souData.toString()
								.trim()));
				if (collection != null && collection.size() > 0) {
					com.kingdee.eas.basedata.master.cssp.CustomerInfo info = collection
							.get(0);
					if (info != null)
						saleIssueBillInfo.setCustomer(info);
					else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								souData, getResource(ctx,
										"SendCustomerNotExists", resources));
						messageList.add(message);
						// throw new TaskExternalException(souData.toString() +
						// " " + getResource(ctx, "SendCustomerNotExists",
						// resources));
					}
				} else {
					message = String.format("第%s行：%s %s", currentRowIndex,
							souData, getResource(ctx, "SendCustomerNotExists",
									resources));
					messageList.add(message);
					// throw new TaskExternalException(souData.toString() + " "
					// + getResource(ctx, "SendCustomerNotExists", resources));
				}
			} else {
				message = String.format("第%s行：%s %s", currentRowIndex, souData,
						getResource(ctx, "SendCustomerNotExists", resources));
				messageList.add(message);
				// throw new TaskExternalException(getResource(ctx,
				// "SendCustomerNotExists", resources));
			}
		} catch (BOSException e) {
			message = String.format("第%s行：FCustomer_number引入时产生错误：%s",
					currentRowIndex, e.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		try {
			Object souData = null;
			if (lineData.containsKey("FAdminOrgUnit_number"))
				souData = ((DataToken) lineData.get("FAdminOrgUnit_number")).data;
			if (souData != null && souData.toString().trim().length() > 0) {
				IAdminOrgUnit isou = AdminOrgUnitFactory.getLocalInstance(ctx);
				AdminOrgUnitCollection collection = isou
						.getAdminOrgUnitCollection(getFilter(souData.toString()
								.trim()));
				if (collection != null && collection.size() > 0) {
					com.kingdee.eas.basedata.org.AdminOrgUnitInfo aou = collection
							.get(0);
					saleIssueBillInfo.setAdminOrgUnit(aou);
				} else {
					message = String.format("第%s行：%s %s", currentRowIndex,
							souData, getResource(ctx, "adminOrgUnitNotExists",
									resources));
					messageList.add(message);
					// throw new TaskExternalException(souData.toString().trim()
					// + " " + getResource(ctx, "adminOrgUnitNotExists",
					// resources));
				}
			}
		} catch (BOSException e) {
			message = String.format("第%s行：FAdminOrgUnit_number引入时产生错误：%s",
					currentRowIndex, e.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		try {
			Object souData = null;
			if (lineData.containsKey("FPaymentType_number"))
				souData = ((DataToken) lineData.get("FPaymentType_number")).data;
			if (souData != null && souData.toString().trim().length() > 0) {
				IPaymentType isou = PaymentTypeFactory.getLocalInstance(ctx);
				EntityViewInfo viewInfo = new EntityViewInfo();
				FilterInfo filter = new FilterInfo();
				filter.getFilterItems().add(
						new FilterItemInfo("name", souData.toString().trim(),
								CompareType.EQUALS));
				viewInfo.setFilter(filter);
				PaymentTypeCollection collection = isou
						.getPaymentTypeCollection(viewInfo);
				PaymentTypeInfo aou = new PaymentTypeInfo();
				if (collection != null && collection.size() > 0) {
					aou = collection.get(0);
					saleIssueBillInfo.setPaymentType(aou);
				} else {
					filter.getFilterItems().removeObject(0);
					filter
							.getFilterItems()
							.add(
									new FilterItemInfo(
											"name",
											ResourceBase
													.getString(
															"com.kingdee.eas.scm.im.IMAutoGenerateResource",
															"44_SaleIssueImport",
															ctx.getLocale()),
											CompareType.EQUALS));
					viewInfo.setFilter(filter);
					collection = isou.getPaymentTypeCollection(viewInfo);
					if (collection != null && collection.size() > 0) {
						aou = collection.get(0);
						saleIssueBillInfo.setPaymentType(aou);
					}
				}
			}
		} catch (Exception e) {
			message = String.format("第%s行：FPaymentType_number引入时产生错误：%s",
					currentRowIndex, e.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		// FIsInitBill
		try {
			Object souData = null;
			if (lineData.containsKey("FIsInitBill"))
				souData = ((DataToken) lineData.get("FIsInitBill")).data;
			if (souData != null && souData.toString().trim().length() > 0) {
				if ("true".equalsIgnoreCase(souData.toString().trim())
						|| "是".equalsIgnoreCase(souData.toString().trim()))
					saleIssueBillInfo.setIsInitBill(true);
				else
					saleIssueBillInfo.setIsInitBill(false);
			} else {
				saleIssueBillInfo.setIsInitBill(false);
			}
		} catch (Exception e) {
			saleIssueBillInfo.setIsInitBill(false);
		}
		try {
			saleIssueBillInfo.setCU(ContextUtil.getCurrentCtrlUnit(ctx));
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
			boolean IsHaveValue = false;
			number = null;
			if (lineData.containsKey("FCreator_number"))
				number = ((DataToken) lineData.get("FCreator_number")).data;
			if (number != null) {
				String str = number.toString();
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
								saleIssueBillInfo.setCreator(info);
								IsHaveValue = true;
							}
						}
					} catch (Exception ex) {
						message = String.format(
								"第%s行：FCreator_number:%s 引入产生错误：%s",
								currentRowIndex, str, ex.getMessage());
						messageList.add(message);
						// throw new TaskExternalException(getResource(ctx,
						// ex.getMessage(), resources));
					}
			}
			if (!IsHaveValue) {
				com.kingdee.eas.base.permission.UserInfo userinfo = ContextUtil
						.getCurrentUserInfo(ctx);
				saleIssueBillInfo.setCreator(userinfo);
			}
			Date d = new Date();
			saleIssueBillInfo.setCreateTime(new Timestamp(d.getTime()));

			// modified by limin lin at Feb 06,2012
			// 单据状态处理
			BillBaseStatusEnum baseStatus = BillBaseStatusEnum.TEMPORARILYSAVED;
			if (lineData.containsKey("FBaseStatus")) {
				String baseStatusStr = ((DataToken) lineData.get("FBaseStatus")).data
						.toString();
				int billBaseStatusEnum = new Integer(baseStatusStr);
				baseStatus = BillBaseStatusEnum.getEnum(billBaseStatusEnum);
			}

			saleIssueBillInfo.setBaseStatus(baseStatus);
			Object currency = null;
			if (lineData.containsKey("FCurrency_number"))
				currency = ((DataToken) lineData.get("FCurrency_number")).data;
			if (currency != null) {
				String strCur = currency.toString();
				if (strCur != null && strCur.trim().length() > 0) {
					ICurrency icurreny = CurrencyFactory.getLocalInstance(ctx);
					CurrencyCollection collection = icurreny
							.getCurrencyCollection("where number='" + strCur
									+ "'");
					if (collection != null && collection.size() > 0) {
						com.kingdee.eas.basedata.assistant.CurrencyInfo info = collection
								.get(0);
						if (info != null)
							saleIssueBillInfo.setCurrency(info);
					}
				}
			}
			try {
				Object exchangeRate = null;
				if (lineData.containsKey("FExchangeRate"))
					exchangeRate = ((DataToken) lineData.get("FExchangeRate")).data;
				if (exchangeRate != null
						&& exchangeRate.toString().trim().length() > 0) {
					BigDecimal value = new BigDecimal(exchangeRate.toString());
					if (value != null)
						saleIssueBillInfo.setBigDecimal("exchangeRate", value);
				}
			} catch (NumberFormatException nex) {
				message = String
						.format(
								"第%s行：exchangeRate format error, it must be a number!!!",
								currentRowIndex);
				messageList.add(message);
				// throw new TaskExternalException(
				// "exchangeRate format error, it must be a number!!!");
			}
			try {
				Object souData = null;
				if (lineData.containsKey("FIsSysBill"))
					souData = ((DataToken) lineData.get("FIsSysBill")).data;
				if (souData != null && souData.toString().trim().length() > 0) {
					if ("true".equalsIgnoreCase(souData.toString().trim())
							|| "是".equalsIgnoreCase(souData.toString().trim()))
						saleIssueBillInfo.setIsSysBill(true);
					else
						saleIssueBillInfo.setIsSysBill(false);
				} else {
					saleIssueBillInfo.setIsSysBill(false);
				}
			} catch (Exception e) {
				saleIssueBillInfo.setIsSysBill(false);
			}
		} catch (Exception e) {
			message = String.format("第%s行：FIsSysBill引入产生错误：%s",
					currentRowIndex, e.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(e.getMessage(), e.getCause());
		}

		// add by limin lin at Feb 07,2012
		// 增加默认值
		try {
			Object souData = null;
			if (lineData.containsKey("FIsInTax"))
				souData = ((DataToken) lineData.get("FIsInTax")).data;
			if (souData != null && souData.toString().trim().length() > 0) {
				if ("true".equalsIgnoreCase(souData.toString().trim())
						|| "是".equalsIgnoreCase(souData.toString().trim()))
					saleIssueBillInfo.setIsInTax(true);
			} else {
				saleIssueBillInfo.setIsInTax(false);
			}
		} catch (Exception e) {
			saleIssueBillInfo.setIsInTax(false);
		}
		try {
			Object souData = null;
			if (lineData.containsKey("FHasEffected"))
				souData = ((DataToken) lineData.get("FHasEffected")).data;
			if (souData != null && souData.toString().trim().length() > 0) {
				if ("true".equalsIgnoreCase(souData.toString().trim())
						|| "是".equalsIgnoreCase(souData.toString().trim()))
					saleIssueBillInfo.setHasEffected(true);
			} else {
				saleIssueBillInfo.setHasEffected(false);
			}
		} catch (Exception e) {
			saleIssueBillInfo.setHasEffected(false);
		}
		try {
			Object souData = null;
			if (lineData.containsKey("FIsReversed"))
				souData = ((DataToken) lineData.get("FIsReversed")).data;
			if (souData != null && souData.toString().trim().length() > 0) {
				if ("true".equalsIgnoreCase(souData.toString().trim())
						|| "是".equalsIgnoreCase(souData.toString().trim()))
					saleIssueBillInfo.setIsReversed(true);
			} else {
				saleIssueBillInfo.setIsReversed(false);
			}
		} catch (Exception e) {
			saleIssueBillInfo.setIsReversed(false);
		}
		// 总金额和总成本计算
		BigDecimal totalAmount = ZERO;
		BigDecimal totalActualCost = ZERO;
		BigDecimal totalStandardCost = ZERO;
		BigDecimal totalLocalAmount = ZERO;
		for (int n = 0; n < saleIssueBillInfo.getEntry().size(); n++) {
			if (saleIssueBillInfo.getEntry().get(n).getAmount() != null)
				totalAmount = totalAmount.add(saleIssueBillInfo.getEntry().get(
						n).getAmount());
			if (saleIssueBillInfo.getEntry().get(n).getActualCost() != null)
				totalActualCost = totalActualCost.add(saleIssueBillInfo
						.getEntry().get(n).getActualCost());
			if (saleIssueBillInfo.getEntry().get(n).getStandardCost() != null)
				totalStandardCost = totalStandardCost.add(saleIssueBillInfo
						.getEntry().get(n).getStandardCost());
			if (saleIssueBillInfo.getEntry().get(n).getLocalAmount() != null)
				totalLocalAmount = totalLocalAmount.add(saleIssueBillInfo
						.getEntry().get(n).getLocalAmount());
		}
		saleIssueBillInfo.setTotalAmount(totalAmount);
		saleIssueBillInfo.setTotalStandardCost(totalStandardCost);
		saleIssueBillInfo.setTotalActualCost(totalActualCost);
		saleIssueBillInfo.setTotalLocalAmount(totalLocalAmount);

		BillTypeInfo aBillTypeInfo = new BillTypeInfo();
		aBillTypeInfo.setId(BOSUuid
				.read("50957179-0105-1000-e000-015bc0a812fd463ED552"));
		aBillTypeInfo.setNumber("102");
		saleIssueBillInfo.setBillType(aBillTypeInfo);
		return saleIssueBillInfo;
	}

	private String getIdFromNumber(String number, Context ctx)
			throws TaskExternalException {
		SaleIssueBillCollection collection;
		try {
			collection = SaleIssueBillFactory.getLocalInstance(ctx)
					.getSaleIssueBillCollection(
							"where number ='" + number + "'");
		} catch (BOSException e) {
			throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		if (collection != null && collection.size() > 0)
			return collection.get(0).getId().toString();
		else
			return null;
	}

	public int getSubmitType() {
		return 1;
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

	public static SaleBackProfitTypeEnum getSaleBackProfitTypeEnum(
			String value, Context ctx) throws TaskExternalException {
		if (value != null) {
			if (EnumEqualByAlias(value, SaleBackProfitTypeEnum.CUSTOMER))
				return SaleBackProfitTypeEnum.CUSTOMER;
			else
				throw new TaskExternalException(DataImportTools.getResString(
						"SaleBackProfitTypeError", ctx));
		} else {
			return null;
		}
	}

	public static boolean EnumEqualByAlias(String value, Enum enumValue) {
		return value.equals(enumValue.getAlias(new Locale(Locale_zh)))
				|| value.equals(enumValue.getAlias(new Locale(Locale_tw)));
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

	private EntityViewInfo getFilterByName(String name) {
		EntityViewInfo viewInfo = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(
				new FilterItemInfo("person.number", name, CompareType.EQUALS));
		viewInfo.setFilter(filter);
		return viewInfo;
	}

	public void submit(CoreBaseInfo coreBaseInfo, Context ctx)
			throws TaskExternalException {
		if (coreBaseInfo == null
				|| !(coreBaseInfo instanceof SaleIssueBillInfo))
			return;
		try {
			SaleIssueBillInfo bill = (SaleIssueBillInfo) coreBaseInfo;
			com.kingdee.bos.dao.IObjectPK userPK = ctx.getCaller();
			String id = getIdFromNumber(bill.getNumber(), ctx);
			com.kingdee.bos.dao.IObjectPK orgPK = new ObjectUuidPK(bill
					.getStorageOrgUnit().getId());
			SCMServerUtils.checkFunctionPermission(ctx, userPK, orgPK,
					"saleissue_import");
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
	public Hashtable exportTransmit(IRowSet rs, Context ctx)
			throws TaskExternalException {
		Hashtable result = new Hashtable();
		try {
			putTable(result, "FNumber", rs.getString("number"));
			putTable(result, "bizDate", rs.getString("bizDate"));
			putTable(result, "transactionType", rs
					.getString("transactionType.number"));
			putTable(result, "transactionTypeName", rs
					.getString("transactionType.name"));
			putTable(result, "customer", rs.getString("customer.number"));
			putTable(result, "storageOrgUnit", rs
					.getString("storageOrgUnit.number"));
			putTable(result, "storageOrgUnitName", rs
					.getString("storageOrgUnit.name"));
			putTable(result, "adminOrgUnit", rs
					.getString("adminOrgUnit.number"));
			putTable(result, "paymentTypeName", rs
					.getString("paymentType.name"));
			putTable(result, "isInitBill", rs.getString("isInitBill"));
			putTable(result, "material", rs.getString("material.number"));
			putTable(result, "materialModel", rs.getString("material.model"));
			putTable(result, "materialName", rs.getString("material.name"));
			putTable(result, "assistProperty", rs
					.getString("assistProperty.number"));
			putTable(result, "entryLOT", rs.getString("entry.lot"));
			putTable(result, "entryMFG", rs.getString("entry.mfg"));
			putTable(result, "entryEXP", rs.getString("entry.exp"));
			putTable(result, "unit", rs.getString("unit.number"));
			putTable(result, "FDescription", rs.getString("description"));
			if ("".equalsIgnoreCase(DataImportUtils.transformValue(
					rs.getString("entry.qty")).toString())
					|| DataImportUtils
							.transformValue(rs.getString("entry.qty"))
							.toString().length() < 1) {
				result.put("entryQTY", DataImportUtils.transformValue(null));
			} else {
				BigDecimal qty = new BigDecimal(rs.getString("entry.qty"));
				result.put("entryQTY", qty.abs());
			}
			putTable(result, "assistUnit", rs.getString("assistUnit.number"));
			if ("".equalsIgnoreCase(DataImportUtils.transformValue(
					rs.getString("entry.assistQty")).toString())
					|| DataImportUtils.transformValue(
							rs.getString("entry.assistQty")).toString()
							.length() < 1) {
				result.put("assistQty", DataImportUtils.transformValue(null));
			} else {
				BigDecimal assistQty = new BigDecimal(rs
						.getString("entry.assistQty"));
				result.put("assistQty", assistQty.abs());
			}
			putTable(result, "warehouse", rs.getString("warehouse.number"));
			putTable(result, "stocker", rs.getString("stocker.number"));
			putTable(result, "location", rs.getString("location.number"));
			putTable(result, "isPresent", rs.getString("entry.isPresent"));
			if ("".equalsIgnoreCase(DataImportUtils.transformValue(
					rs.getString("entry.unitActualCost")).toString())
					|| DataImportUtils.transformValue(
							rs.getString("entry.unitActualCost")).toString()
							.length() < 1) {
				result.put("unitActualCost", DataImportUtils
						.transformValue(null));
			} else {
				BigDecimal assistQty = new BigDecimal(rs
						.getString("entry.unitActualCost"));
				result.put("unitActualCost", assistQty.abs());
			}
			if ("".equalsIgnoreCase(DataImportUtils.transformValue(
					rs.getString("entry.actualCost")).toString())
					|| DataImportUtils.transformValue(
							rs.getString("entry.actualCost")).toString()
							.length() < 1) {
				result.put("actualCost", DataImportUtils.transformValue(null));
			} else {
				BigDecimal assistQty = new BigDecimal(rs
						.getString("entry.actualCost"));
				result.put("actualCost", assistQty.abs());
			}
			putTable(result, "orderNumber", rs.getString("entry.orderNumber"));
			putTable(result, "remark", rs.getString("entry.remark"));
			putTable(result, "saleOrgUnit", rs.getString("saleOrgUnit.number"));
			putTable(result, "saleGroup", rs.getString("saleGroup.number"));
			putTable(result, "salePerson", rs.getString("salePerson.number"));
			putTable(result, "balanceCustomer", rs
					.getString("balanceCustomer.number"));
			putTable(result, "orderCustomer", rs
					.getString("orderCustomer.number"));
			putTable(result, "paymentCustomer", rs
					.getString("paymentCustomer.number"));
			putTable(result, "sendAddress", rs.getString("entry.sendAddress"));
			putTable(result, "creator", rs.getString("creator.number"));
			putTable(result, "createTime", rs.getString("createTime"));
			putTable(result, "currency", rs.getString("currency.number"));
			putTable(result, "exchangeRate", rs.getString("exchangeRate"));
			putTable(result, "convertMode", rs.getString("convertMode"));
			putTable(result, "isInTax", rs.getString("isInTax"));
			putTable(result, "totalTaxAmount", rs.getString("totalTaxAmount"));
			putTable(result, "totalLocalTaxAmount", rs
					.getString("totalLocalTaxAmount"));
			putTable(result, "entry.price", rs.getString("entry.price"));
			putTable(result, "entry.taxRate", rs.getString("entry.taxRate"));
			putTable(result, "entry.taxPrice", rs.getString("entry.taxPrice"));
			putTable(result, "entry.discountType", rs
					.getString("entry.discountType"));
			putTable(result, "entry.discountAmount", rs
					.getString("entry.discountAmount"));
			putTable(result, "entry.discount", rs.getString("entry.discount"));
			putTable(result, "entry.actualPrice", rs
					.getString("entry.actualPrice"));
			putTable(result, "entry.actualTaxPrice", rs
					.getString("entry.actualTaxPrice"));
			putTable(result, "entry.amount", rs.getString("entry.amount"));
			putTable(result, "entry.localAmount", rs
					.getString("entry.localAmount"));
			putTable(result, "entry.tax", rs.getString("entry.tax"));
			putTable(result, "entry.localTax", rs.getString("entry.localTax"));
			putTable(result, "entry.taxAmount", rs.getString("entry.taxAmount"));
			putTable(result, "entry.localTaxAmount", rs
					.getString("entry.localTaxAmount"));
		} catch (Exception e) {
			throw new TaskExternalException("", e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public FilterInfo getExportFilterForQuery(Context ctx) {
		FilterInfo filterInfo = null;
		OrgUnitCollection collection = null;
		try {
			collection = SCMGroupServerUtils.getAuthOrgByPermItem(ctx,
					OrgType.Storage, "saleissue_export");
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

	public String getExportQueryInfo(Context ctx) {
		return "com.kingdee.eas.scm.im.inv.SaleIssueDataImportQuery";
	}

	@SuppressWarnings("unchecked")
	private void putTable(Hashtable htable, Object key, Object value) {
		DataImportTools.putToHashtable(htable, key, value);
	}

	public static IsUseableEnum getIsUseableEnum(String value, Context ctx)
			throws TaskExternalException {
		if (value != null && value.length() > 0) {
			if (enumCompareByAlias(value, IsUseableEnum.UnUseable))
				return IsUseableEnum.UnUseable;
			if (enumCompareByAlias(value, IsUseableEnum.Useable))
				return IsUseableEnum.Useable;
		}
		return IsUseableEnum.UnUseable;
	}

	public static boolean enumCompareByAlias(String value, Enum enumValue) {
		return value.equals(enumValue.getAlias(new Locale(Locale_zh)))
				|| value.equals(enumValue.getAlias(new Locale(Locale_tw)));
	}

	public static boolean getBooleanValue(String value, Context ctx)
			throws TaskExternalException {
		if (enumCompareByAlias(value, UpdataStorageEnum.YES))
			return true;
		return !enumCompareByAlias(value, UpdataStorageEnum.NO) ? false : false;
	}

	@SuppressWarnings("unchecked")
	private SaleIssueEntryInfo transmitEntry(Hashtable hsData, Context ctx)
			throws TaskExternalException {
		String message = "";
		if (hsData == null)
			return null;
		SaleIssueEntryInfo entryInfo = new SaleIssueEntryInfo();
		MaterialInfo materialInfo = new MaterialInfo();
		MeasureUnitInfo mui = new MeasureUnitInfo();
		MaterialCompanyInfoInfo materialCompanyInfo = null;
		MaterialInventoryInfo materialInventory = null;
		sou = saleIssueBillInfo.getStorageOrgUnit();
		// StringBuffer errorString = new StringBuffer();

		// 生产日期和到期日期设置
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < DATE_ENTRY_PROPERTY.length; i++) {
			Object o = null;
			if (hsData.containsKey(EXCEL_DATE_ENTRY_PROPERTY[i]))
				o = ((DataToken) hsData.get(EXCEL_DATE_ENTRY_PROPERTY[i])).data;
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
			// 数量和金额校验
			checkNumberAndAmount(entryInfo, ctx, hsData,
					DECIMAL_ENTRY_PROPERTY_ALL, DECIMAL_ENTRY_PROPERTY_ALL_KEY,
					true);
		} catch (Exception ex) {
			message = String.format("第%s行：数量和金额校验时产生错误:%s", currentRowIndex, ex
					.getMessage());
			messageList.add(message);
		}

		try {
			// 物料编码
			String number = null;
			Object data = null;
			if (hsData.containsKey("FEntry$material_number"))
				data = ((DataToken) hsData.get("FEntry$material_number")).data;
			if (data != null && data.toString().length() > 0) {
				number = data.toString();
				if (number != null && number.trim().length() > 0) {
					IMaterial imaterial = MaterialFactory.getLocalInstance(ctx);
					EntityViewInfo evi = getFilter(number);
					// evi.getSelector().add(new
					// SelectorItemInfo("baseUnit.*"));
					MaterialCollection collection = imaterial
							.getMaterialCollection(evi);
					if (collection != null && collection.size() > 0) {
						MaterialInfo info = collection.get(0);
						if (info != null) {
							entryInfo.setMaterial(info);
							materialInfo = info;
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

			// 批次
			data = null;
			if (hsData.containsKey("FEntry_LOT")) {
				data = ((DataToken) hsData.get("FEntry_LOT")).data;
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
			// 物料到期日期
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
			// 辅助属性
			data = null;
			if (hsData.containsKey("FEntry$assistProperty_number"))
				data = ((DataToken) hsData.get("FEntry$assistProperty_number")).data;
			checkMaterialInfo(ctx, materialInfo, data, 3);
			if (data != null) {
				number = data.toString();
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
			// 仓库编码
			WarehouseInfo warehouseInfo = new WarehouseInfo();
			data = null;
			if (hsData.containsKey("FEntry$warehouse_number"))
				data = ((DataToken) hsData.get("FEntry$warehouse_number")).data;
			if (data != null && data.toString() != null
					&& data.toString().length() > 0) {
				number = data.toString();
				if (number != null) {
					IWarehouse iw = WarehouseFactory.getLocalInstance(ctx);
					WarehouseCollection collection = iw
							.getWarehouseCollection(getFilter(number));
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
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx, "WarehouseNotExists",
							// resources));
						}
					} else {
						message = String.format("第%s行： %s %s", currentRowIndex,
								data, getResource(ctx, "WarehouseNotExists",
										resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "WarehouseNotExists", resources));
					}
				}
			} else {
				message = String.format("第%s行： %s", currentRowIndex,
						getResource(ctx, "WarehouseNotExists", resources));
				messageList.add(message);
				// throw new TaskExternalException(getResource(ctx,
				// "WarehouseNotExists", resources));
			}
			// 库存编码
			data = null;
			if (hsData.containsKey("FEntry$location_number"))
				data = ((DataToken) hsData.get("FEntry$location_number")).data;
			if (warehouseInfo.isHasLocation()
					&& (data == null || data.toString().length() <= 0))
				throw new TaskExternalException(warehouseInfo.getNumber() + " "
						+ getResource(ctx, "LocationMustNotBeNull", resources));
			if (!warehouseInfo.isHasLocation() && data != null
					&& data.toString().length() > 0)
				throw new TaskExternalException(warehouseInfo.getNumber() + " "
						+ getResource(ctx, "LocationMustBeNull", resources));
			if (data != null) {
				number = data.toString();
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
			// 仓库员
			data = null;
			if (hsData.containsKey("FEntry$stocker_number"))
				data = ((DataToken) hsData.get("FEntry$stocker_number")).data;
			if (data != null) {
				number = data.toString();
				if (number != null && number.trim().length() > 0)
					try {
						IPerson iPerson = PersonFactory.getLocalInstance(ctx);
						PersonCollection collection = iPerson
								.getPersonCollection(getFilter(number));
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
							// getResource(ctx, "stockerNotExists", resources));
						} else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data, getResource(ctx,
											"stockerNotExists", resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// getResource(ctx, "stockerNotExists", resources));
						}
					} catch (Exception ex) {
						message = String.format(
								"第%s行：FEntry$stocker_number引入产生错误：%s",
								currentRowIndex, data, ex.getMessage());
						messageList.add(message);
						// throw new TaskExternalException(getResource(ctx,
						// ex.getMessage(), resources));
					}
			}

			// removed by limin lin at May 14, 2012
			// set the unit via material's baseunit
			entryInfo.setUnit(materialInfo.getBaseUnit());
			mui = materialInfo.getBaseUnit();
			// //计量单位编码
			// data = null;
			// if(hsData.containsKey("FEntry$unit_number"))
			// data = ((DataToken)hsData.get("FEntry$unit_number")).data;
			// if (data != null)
			// {
			// number = data.toString();
			// if (number != null)
			// {
			// IMeasureUnit imu = MeasureUnitFactory.getLocalInstance(ctx);
			// MeasureUnitCollection collection =
			// imu.getMeasureUnitCollection(getFilter(number));
			// if (collection != null && collection.size() > 0)
			// {
			// MeasureUnitInfo info = collection.get(0);
			// checkUnit(ctx, materialInfo, info);
			// if (info != null)
			// {
			// entryInfo.setUnit(info);
			// mui = info;
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
			// }
			// } else
			// {
			// message = String.format("第%s行：%s", currentRowIndex
			// ,getResource(ctx, "UnitCanNotBeNull", resources));
			// messageList.add(message);
			// //throw new TaskExternalException(getResource(ctx,
			// "UnitCanNotBeNull", resources));
			// }
			if (entryInfo.getUnit() != null) {
				MultiMeasureUnitInfo mmu = MultiMeasureUnitFactory
						.getLocalInstance(ctx).getMultiUnit(
								materialInfo.getId().toString(),
								entryInfo.getUnit().getId().toString());
				if (mmu != null) {
					int qtyPrecision = mmu.getQtyPrecision();
					for (int i = 0; i < QTY_PROPERTY.length; i++)
						if (entryInfo.getBigDecimal(QTY_PROPERTY[i]) != null)
							entryInfo.setBigDecimal(QTY_PROPERTY[i], entryInfo
									.getBigDecimal(QTY_PROPERTY[i]).setScale(
											qtyPrecision, 4));

				}
			}
			// 辅助单位和辅助数量
			entryInfo.setAssistUnit(materialInfo.getAssistUnit());
			if (entryInfo.getAssistUnit() != null)
				entryInfo.setAssistQty((BigDecimal) entryInfo.get("assistQty"));
			else
				entryInfo.setAssistQty(ZERO);
			// 核心订单号
			data = null;
			if (hsData.containsKey("FEntry_saleOrderNumber"))
				data = ((DataToken) hsData.get("FEntry_saleOrderNumber")).data;
			if (data != null)
				entryInfo.setRemark(data.toString());
			// 备注
			data = null;
			if (hsData.containsKey("FEntry_remark"))
				data = ((DataToken) hsData.get("FEntry_remark")).data;
			if (data != null)
				entryInfo.setRemark(data.toString());
			// 送货地址
			data = null;
			if (hsData.containsKey("FEntry_sendAddress"))
				data = ((DataToken) hsData.get("FEntry_sendAddress")).data;
			if (data != null)
				entryInfo.setSendAddress(data.toString());
			// 销售组织
			data = null;
			if (hsData.containsKey("FEntry$saleOrgUnit_number"))
				data = ((DataToken) hsData.get("FEntry$saleOrgUnit_number")).data;
			if (data != null) {
				number = data.toString();
				if (number != null && number.trim().length() > 0) {
					ISaleOrgUnit ic = SaleOrgUnitFactory.getLocalInstance(ctx);
					SaleOrgUnitCollection collection = ic
							.getSaleOrgUnitCollection(getFilter(number));
					if (collection != null && collection.size() > 0) {
						com.kingdee.eas.basedata.org.SaleOrgUnitInfo info = collection
								.get(0);
						if (info != null)
							entryInfo.setSaleOrgUnit(info);
						else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data.toString(),
									getResource(ctx, "saleOrgUnitNotExists",
											resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx, "saleOrgUnitNotExists",
							// resources));
						}
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(), getResource(ctx,
										"saleOrgUnitNotExists", resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "saleOrgUnitNotExists",
						// resources));
					}
				} else {
					message = String.format("第%s行：%s %s", currentRowIndex, data
							.toString(), getResource(ctx, "saleOrgUnitNotNUll",
							resources));
					messageList.add(message);
					// throw new TaskExternalException(data.toString() + " " +
					// getResource(ctx, "saleOrgUnitNotNUll", resources));
				}
			}
			// 销售组
			data = null;
			if (hsData.containsKey("FEntry$saleGroup_number"))
				data = ((DataToken) hsData.get("FEntry$saleGroup_number")).data;
			if (data != null) {
				number = data.toString();
				if (number != null && number.trim().length() > 0) {
					ISaleGroup ic = SaleGroupFactory.getLocalInstance(ctx);
					SaleGroupCollection collection = ic
							.getSaleGroupCollection(getFilter(number));
					if (collection != null && collection.size() > 0) {
						com.kingdee.eas.basedata.scm.sd.sale.SaleGroupInfo info = collection
								.get(0);
						if (info != null)
							entryInfo.setSaleGroup(info);
						else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data.toString(),
									getResource(ctx, "saleGroupNotExists",
											resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx, "saleGroupNotExists",
							// resources));
						}
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(), getResource(ctx,
										"saleGroupNotExists", resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "saleGroupNotExists", resources));
					}
				}
			}
			// 销售员
			data = null;
			if (hsData.containsKey("FEntry$salePerson_number"))
				data = ((DataToken) hsData.get("FEntry$salePerson_number")).data;
			if (data != null) {
				number = data.toString();
				if (number != null && number.trim().length() > 0) {
					ISalePerson ic = SalePersonFactory.getLocalInstance(ctx);
					SalePersonCollection collection = ic
							.getSalePersonCollection(getFilterByName(number));
					if (collection != null && collection.size() > 0) {
						SalePersonInfo info = collection.get(0);
						if (info != null)
							entryInfo.setSalePerson(info.getPerson());
						else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data.toString(),
									getResource(ctx, "salePersonNotExists",
											resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx, "salePersonNotExists",
							// resources));
						}
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(), getResource(ctx,
										"salePersonNotExists", resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "salePersonNotExists",
						// resources));
					}
				}
			}
			// 应收客户
			data = null;
			if (hsData.containsKey("FEntry$balanceCustomer_number"))
				data = ((DataToken) hsData.get("FEntry$balanceCustomer_number")).data;
			if (data != null) {
				number = data.toString();
				if (number != null && number.trim().length() > 0) {
					ICustomer ic = CustomerFactory.getLocalInstance(ctx);
					CustomerCollection collection = ic
							.getCustomerCollection(getFilter(number));
					if (collection != null && collection.size() > 0) {
						com.kingdee.eas.basedata.master.cssp.CustomerInfo info = collection
								.get(0);
						if (info != null)
							entryInfo.setBalanceCustomer(info);
						else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data.toString(),
									getResource(ctx,
											"balanceCustomerNotExists",
											resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx,
							// "balanceCustomerNotExists", resources));
						}
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(), getResource(ctx,
										"balanceCustomerNotExists", resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "balanceCustomerNotExists",
						// resources));
					}
				} else {
					entryInfo.setBalanceCustomer(saleIssueBillInfo
							.getCustomer());
				}
			} else {
				entryInfo.setBalanceCustomer(saleIssueBillInfo.getCustomer());
			}
			// 订货客户
			data = null;
			if (hsData.containsKey("FEntry$orderCustomer_number"))
				data = ((DataToken) hsData.get("FEntry$orderCustomer_number")).data;
			if (data != null) {
				number = data.toString();
				if (number != null && number.trim().length() > 0) {
					ICustomer ic = CustomerFactory.getLocalInstance(ctx);
					CustomerCollection collection = ic
							.getCustomerCollection(getFilter(number));
					if (collection != null && collection.size() > 0) {
						com.kingdee.eas.basedata.master.cssp.CustomerInfo info = collection
								.get(0);
						if (info != null)
							entryInfo.setOrderCustomer(info);
						else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data.toString(),
									getResource(ctx, "CustomerNotExists",
											resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx, "CustomerNotExists",
							// resources));
						}
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(), getResource(ctx,
										"CustomerNotExists", resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "CustomerNotExists", resources));
					}
				} else {
					entryInfo.setOrderCustomer(saleIssueBillInfo.getCustomer());
				}
			} else {
				entryInfo.setOrderCustomer(saleIssueBillInfo.getCustomer());
			}
			// 付款客户
			data = null;
			if (hsData.containsKey("FEntry$paymentCustomer_number"))
				data = ((DataToken) hsData.get("FEntry$paymentCustomer_number")).data;
			if (data != null) {
				number = data.toString();
				if (number != null && number.trim().length() > 0) {
					ICustomer ic = CustomerFactory.getLocalInstance(ctx);
					CustomerCollection collection = ic
							.getCustomerCollection(getFilter(number));
					if (collection != null && collection.size() > 0) {
						com.kingdee.eas.basedata.master.cssp.CustomerInfo info = collection
								.get(0);
						if (info != null)
							entryInfo.setPaymentCustomer(info);
						else {
							message = String.format("第%s行：%s %s",
									currentRowIndex, data.toString(),
									getResource(ctx,
											"paymentCustomerNotExists",
											resources));
							messageList.add(message);
							// throw new TaskExternalException(data.toString() +
							// " " + getResource(ctx,
							// "paymentCustomerNotExists", resources));
						}
					} else {
						message = String.format("第%s行：%s %s", currentRowIndex,
								data.toString(), getResource(ctx,
										"paymentCustomerNotExists", resources));
						messageList.add(message);
						// throw new TaskExternalException(data.toString() + " "
						// + getResource(ctx, "paymentCustomerNotExists",
						// resources));
					}
				} else {
					entryInfo.setPaymentCustomer(saleIssueBillInfo
							.getCustomer());
				}
			} else {
				entryInfo.setPaymentCustomer(saleIssueBillInfo.getCustomer());
			}
			// 是否赠品
			data = null;
			if (hsData.containsKey("FEntry_isPresent"))
				data = ((DataToken) hsData.get("FEntry_isPresent")).data;
			if (data != null && data.toString().trim().length() > 0) {
				String value = data.toString();
				entryInfo.setIsPresent(getBooleanValue(value));
			} else {
				entryInfo.setIsPresent(false);
			}
			// 备注
			data = null;
			if (hsData.containsKey("FEntry_remark"))
				data = ((DataToken) hsData.get("FEntry_remark")).data;
			if (data != null && data.toString().trim().length() > 0
					&& data != null && data.toString().length() > 0)
				entryInfo.setRemark(data.toString());
			// 折扣类型
			data = null;
			if (hsData.containsKey("FEntry_discountType"))
				data = ((DataToken) hsData.get("FEntry_discountType")).data;
			if (data != null && data.toString().trim().length() > 0) {
				DiscountModeEnum discountType = DiscountModeEnum
						.getEnum(Integer.parseInt(data.toString()));
				entryInfo.setDiscountType(discountType);
			}

			// add by limin lin at Feb 27,2012
			// 根据价税合计和数量计算含税单价
			if (entryInfo.getQty() == null)
				entryInfo.setQty(ZERO);
			int amtPrecision = 4;
			if (entryInfo.getMaterial() != null)
				amtPrecision = entryInfo.getMaterial().getPricePrecision();
			if (entryInfo.getTaxPrice() == null
					|| entryInfo.getTaxPrice() == ZERO) {
				// 价税合计
				BigDecimal taxAmount = ZERO;
				data = null;
				if (hsData.containsKey("FEntry_taxAmount")) {
					data = ((DataToken) hsData.get("FEntry_taxAmount")).data;
					if (data != null) {
						BigDecimal qty = entryInfo.getQty();
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
		} catch (Exception e) {
			message = String.format("第%s行：数据引入时产生错误:%s", currentRowIndex, e
					.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		try {
			IMeasureUnit imu = MeasureUnitFactory.getLocalInstance(ctx);
			com.kingdee.bos.dao.IObjectPK pk = new ObjectUuidPK(materialInfo
					.getBaseUnit().getId());
			MeasureUnitInfo baseUnit = imu.getMeasureUnitInfo(pk);
			entryInfo.setBaseUnit(baseUnit);
			if (entryInfo.getQty() == null)
				entryInfo.setQty(ZERO);
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

			// add by limin lin at Feb 07,2012
			int amtPrecision = 4;
			if (entryInfo.getMaterial() != null)
				amtPrecision = entryInfo.getMaterial().getPricePrecision();
			// SourceBillEntrySeq默认为0
			entryInfo.setSourceBillEntrySeq(0);
			// 基本状态
			entryInfo.setBaseStatus(EntryBaseStatusEnum.NULL);
			// 根据含税单价，税率，折扣等信息计算其他价格
			// "FEntry_price"--salePrice
			// "FEntry_taxPrice"--taxPrice
			// "FEntry_actualPrice"--actualPrice
			// "FEntry_actualTaxPrice"--price
			// 实际含税单价FPrice 100
			// 含税单价FTaxPrice 100
			// 实际单价FActualPrice 100
			// 单价FSalePrice 100
			PriceInfo pinfo = new PriceInfo();
			pinfo.setPricePrecision(amtPrecision);
			pinfo.setIsInTax(saleIssueBillInfo.isIsInTax());

			pinfo.setTaxPrice(entryInfo.getTaxPrice());

			BigDecimal taxRate = entryInfo.getTaxRate();
			if (taxRate == null)
				taxRate = ZERO;
			pinfo.setTaxRate(taxRate);
			pinfo.setDiscountType(entryInfo.getDiscountType());
			pinfo.setDiscountRate(entryInfo.getDiscount());
			pinfo = InvAppUtils.getPriceInfo(pinfo);
			entryInfo.setActualPrice(pinfo.getActualPrice());
			entryInfo.setPrice(pinfo.getActualTaxPrice());
			entryInfo.setSalePrice(pinfo.getPrice());

			// 价税合计FAmount -500
			BigDecimal taxprice = pinfo.getTaxPrice();
			BigDecimal amount = qty.multiply(taxprice);
			amount = amount.setScale(amtPrecision, 4);
			entryInfo.setAmount(amount);

			BigDecimal coefficient = new BigDecimal("1.00");
			// if (entryInfo.getUnit()!=null &&
			// !entryInfo.getUnit().toString().equals
			// (entryInfo.getUnit().toString()))
			// {
			// BigDecimal qtyFrom = qty;
			// if (qtyFrom != null)
			// {
			// MultiMeasureUnitInfo mulUnitFrom =
			// InvClientUtils.getMulUnit(entryInfo.getUnit(),
			// entryInfo.getMaterial());
			// MultiMeasureUnitInfo mulUnitTo =
			// InvClientUtils.getMulUnit(entryInfo.getUnit(),
			// entryInfo.getMaterial());
			// if (mulUnitFrom != null && mulUnitTo != null)
			// coefficient =
			// mulUnitTo.getBaseConvsRate().divide(mulUnitFrom.getBaseConvsRate
			// (), 18, 4);
			// }
			// }
			if (entryInfo.getUnitStandardCost() != null
					&& entryInfo.getUnitStandardCost().compareTo(ZERO) != 0) {
				// 单位标准成本FUnitStandardCost
				BigDecimal unitStandardCost = entryInfo.getUnitStandardCost()
						.multiply(coefficient);
				unitStandardCost = unitStandardCost.setScale(entryInfo
						.getMaterial().getPricePrecision(), 4);
				entryInfo.setUnitStandardCost(unitStandardCost);
				// 标准成本FStandardCost
				BigDecimal standardCost = unitStandardCost.multiply(entryInfo
						.getQty());
				standardCost.setScale(amtPrecision, 4);
				entryInfo.setStandardCost(standardCost);
			}

			if (entryInfo.getUnitActualCost() != null
					&& entryInfo.getUnitActualCost().compareTo(ZERO) != 0) {
				// 单位实际成本FUnitActualCost 100
				BigDecimal unitActualCost = entryInfo.getUnitActualCost()
						.multiply(coefficient);
				unitActualCost = unitActualCost.setScale(entryInfo
						.getMaterial().getPricePrecision(), 4);
				entryInfo.setUnitActualCost(unitActualCost);
				// 实际成本FActualCost -500
				BigDecimal actualCost = unitActualCost.multiply(entryInfo
						.getQty());
				actualCost.setScale(amtPrecision, 4);
				entryInfo.setActualCost(actualCost);
			}

			// 未核销数量FUnWriteOffQty -5
			entryInfo.setUnWriteOffQty(qty);
			// 未核销金额FUnWriteOffAmount -500
			entryInfo.setUnWriteOffAmount(amount);
			// 核心订单行号
			entryInfo.setSaleOrderEntrySeq(0);
			// 价税合计本位币FLocalAmount -500
			BigDecimal localAmount = ZERO;
			BigDecimal exchangeRate = saleIssueBillInfo.getExchangeRate();
			if (exchangeRate == null)
				exchangeRate = new BigDecimal(1.0D);
			localAmount = amount.multiply(exchangeRate);
			localAmount = localAmount.setScale(amtPrecision, 4);
			entryInfo.setLocalAmount(localAmount);
			// 单价本位币FLocalPrice
			// BigDecimal localPrice = ZERO.compareTo(qty) != 0 ?
			// localAmount.divide(qty,
			// entryInfo.getMaterial().getPricePrecision(), 4) : ZERO;
			// entryInfo.setLocalPrice(localPrice);
			// 获取taxRate
			taxRate = taxRate.divide(new BigDecimal(100D), 10, 4);
			// 金额
			BigDecimal one = new BigDecimal(1.0D);
			BigDecimal tempB = one.add(taxRate);
			BigDecimal nonTaxAmount = amount.divide(tempB, amtPrecision, 4);
			entryInfo.setNonTaxAmount(nonTaxAmount);

			// 金额本位币FLocalNotTaxAmount -500
			BigDecimal localNonTaxAmount = localAmount.divide(tempB,
					amtPrecision, 4);
			entryInfo.setLocalNonTaxAmount(localNonTaxAmount);
			// 税额
			BigDecimal tax = amount.subtract(nonTaxAmount);
			tax = tax.setScale(amtPrecision, 4);
			entryInfo.setTax(tax);
			// 税额本位币FLocalTax
			BigDecimal localTax = localAmount.subtract(localNonTaxAmount);
			localTax = localTax.setScale(amtPrecision, 4);
			entryInfo.setLocalTax(localTax);
			if (!isPresent(entryInfo, saleIssueBillInfo.getTransactionType(),
					saleIssueBillInfo.getBillType())) {
				entryInfo.setUnWriteOffQty(entryInfo.getQty());
				if (entryInfo.getActualCost() != null)
					entryInfo.setUnWriteOffAmount(entryInfo.getActualCost());
				// 未核销基本数量FUnWriteOffBaseQty -5
				entryInfo.setUnWriteOffBaseQty(entryInfo.getBaseQty());
				entryInfo.setWrittenOffQty(ZERO);
				entryInfo.setWrittenOffBaseQty(ZERO);
				entryInfo.setWrittenOffAmount(ZERO);
			} else {
				entryInfo.setUnWriteOffQty(ZERO);
				entryInfo.setUnWriteOffBaseQty(ZERO);
				entryInfo.setUnWriteOffAmount(ZERO);
				entryInfo.setWrittenOffQty(entryInfo.getQty().abs());
				entryInfo.setWrittenOffBaseQty(entryInfo.getBaseQty().abs());
				entryInfo.setWrittenOffAmount(entryInfo.getActualCost().abs());
			}
			// FUnDeliverQty 5
			entryInfo.setUndeliverQty(entryInfo.getQty().abs());
			// FUnDeliverBaseQty 5
			entryInfo.setUndeliverBaseQty(entryInfo.getBaseQty().abs());
			// FUninQty 5
			entryInfo.setUnInQty(entryInfo.getQty().abs());
			// FUninBaseQty 5
			entryInfo.setUnInBaseQty(entryInfo.getBaseQty().abs());
			// 可退货基本数量FUnReturnedBaseQty 5
			entryInfo.setUnReturnedBaseQty(entryInfo.getBaseQty().abs());
			// FAssociateQty 5
			entryInfo.setAssociateQty(entryInfo.getQty().abs());
			// FAssociateBaseQty -5
			entryInfo.setAssociateBaseQty(entryInfo.getBaseQty());
		} catch (Exception e) {
			message = String.format("第%s行：数据引入时产生错误:%s", currentRowIndex, e
					.getMessage());
			messageList.add(message);
			// throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		// if (errorString.length() > 0)
		// throw new TaskExternalException(errorString.toString());
		// else
		return entryInfo;
	}

	protected String getIsPresentName() {
		return "isPresent";
	}

	protected boolean isPresent(InvBillBaseEntryInfo entryInfo,
			TransactionTypeInfo tti, BillTypeInfo billTypeInfo) {
		int billType = GetBillType(billTypeInfo);
		return SCMUtils.isPresentStoreType(tti, billType)
				|| entryInfo.isIsPresent();
	}

	private int GetBillType(BillTypeInfo bii) {
		int billType = 0;
		String biiid = bii.getId().toString();
		if ("50957179-0105-1000-e000-017bc0a812fd463ED552".equals(biiid)
				|| "50957179-0105-1000-e000-0172c0a812fd463ED552".equals(biiid)
				|| "50957179-0105-1000-e000-0167c0a812fd463ED552".equals(biiid)
				|| "50957179-0105-1000-e000-015fc0a812fd463ED552".equals(biiid))
			billType = 2;
		else if ("50957179-0105-1000-e000-0177c0a812fd463ED552".equals(biiid)
				|| "50957179-0105-1000-e000-016ec0a812fd463ED552".equals(biiid)
				|| "50957179-0105-1000-e000-0163c0a812fd463ED552".equals(biiid)
				|| "50957179-0105-1000-e000-015bc0a812fd463ED552".equals(biiid))
			billType = 1;
		return billType;
	}

	private boolean checkLotInfo(Context ctx, MaterialInfo materialInfo,
			Object data, StorageOrgUnitInfo sou, SaleIssueEntryInfo entryInfo)
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

	private void checkMaterialInfo(Context ctx, MaterialInfo materialInfo,
			Object data, int type) throws TaskExternalException, BOSException {
		if (type == 3) {
			com.kingdee.eas.basedata.master.material.AsstAttrTypeInfo asstAttrTypeInfo = materialInfo
					.getAssistAttr();
			if (asstAttrTypeInfo == null && data != null
					&& data.toString().length() > 0)
				throw new TaskExternalException(materialInfo.getNumber()
						+ getResource(ctx, "NotSetAssistProperty", resources));
			if (asstAttrTypeInfo != null
					&& (data == null || data.toString().length() <= 0))
				throw new TaskExternalException(materialInfo.getNumber()
						+ getResource(ctx, "SetAssistProperty", resources));
		} else if (type == 4) {
			MeasureUnitInfo assistUnit = materialInfo.getAssistUnit();
			if (assistUnit == null && data != null
					&& data.toString().length() > 0)
				throw new TaskExternalException(materialInfo.getNumber()
						+ getResource(ctx, "NotSetAssistUnit", resources));
			if (assistUnit != null
					&& (data == null || data.toString().length() <= 0))
				throw new TaskExternalException(materialInfo.getNumber()
						+ getResource(ctx, "SetAssistUnit", resources));
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
		if (!iso.exists(fi))
			throw new TaskExternalException(warehouseInfo.getNumber() + ","
					+ sou.getNumber() + " "
					+ getResource(ctx, "RelationNotExists", resources));
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
					saleIssueBillInfo.setTransactionType(tti);
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
					saleIssueBillInfo.setStorageOrgUnit(sou);
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

	private boolean getBooleanValue(String value) {
		if (value == null)
			return false;
		value = value.trim();
		return !value.equalsIgnoreCase("N") && !value.equalsIgnoreCase("NO")
				&& !value.equalsIgnoreCase("0")
				&& !value.equalsIgnoreCase("false")
				&& !value.equalsIgnoreCase("否");
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
			saleIssueBillInfo.setBizType(bizTypeInfo);
		}
		// if(tti != null)
		// saleIssueBillInfo.setBizType(tti.getBizType());
	}

	@SuppressWarnings("unchecked")
	private void checkNumberAndAmount(SaleIssueEntryInfo entryInfo,
			Context ctx, Hashtable lineData, String checkList[],
			String setList[], boolean checkBackType)
			throws TaskExternalException {
		for (int i = 0; i < DECIMAL_ENTRY_PROPERTY_ALL.length; i++) {
			try {
				Object o = null;
				if (lineData.containsKey(DECIMAL_ENTRY_PROPERTY_ALL[i]))
					o = ((DataToken) lineData
							.get(DECIMAL_ENTRY_PROPERTY_ALL[i])).data;
				if (o != null && o.toString().trim().length() > 0) {
					BigDecimal value = new BigDecimal(o.toString());
					if (value != null) {
						if (value.compareTo(ZERO) < 0)
							throw new TaskExternalException(
									DECIMAL_ENTRY_PROPERTY_ALL[i]
											+ getResource(
													ctx,
													"import_price_must_positive",
													resources));
						// add by limin lin at Feb 07, 2012
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
					if (DECIMAL_ENTRY_PROPERTY_ALL[i]
							.equalsIgnoreCase(QTY_PROPERTY[0]))
						throw new TaskExternalException(
								DECIMAL_ENTRY_PROPERTY_ALL[i]
										+ getResource(ctx,
												"Numbercannotbenull", resources));
					if (!DECIMAL_ENTRY_PROPERTY_ALL[i]
							.equals(ASSISTQTY_PROPERTY[0]))
						entryInfo.setBigDecimal(setList[i], ZERO);
					continue;
				}
				if (DECIMAL_ENTRY_PROPERTY_ALL[i]
						.equalsIgnoreCase(QTY_PROPERTY[0]))
					throw new TaskExternalException(
							DECIMAL_ENTRY_PROPERTY_ALL[i]
									+ getResource(ctx, "Numbercannotbenull",
											resources));
				if (!DECIMAL_ENTRY_PROPERTY_ALL[i]
						.equals(ASSISTQTY_PROPERTY[0]))
					entryInfo.setBigDecimal(setList[i], ZERO);
				continue;
			} catch (NumberFormatException nex) {
				nex.printStackTrace();
			}
			throw new TaskExternalException(DECIMAL_ENTRY_PROPERTY_ALL[i]
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
			MultiMeasureUnitInfo mulUnit = getMulUnit(mui, mi);

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
					mulUnit = getMulUnit(baseUnit, mi);
					int precision = mulUnit.getQtyPrecision();
					baseQty = baseQty.setScale(precision, 4);
				}
			}
			return baseQty;
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ExceptionHandler.handle(e);
			return null;
		}
	}

	public static MultiMeasureUnitInfo getMulUnit(MeasureUnitInfo mui,
			MaterialInfo mi) throws BaseException {
		MultiMeasureUnitInfo mulUnit = null;
		IMultiMeasureUnit immu = MultiMeasureUnitFactory.getRemoteInstance();
		if (mui != null && mi != null)
			mulUnit = immu.getMultiUnit(mi.getId().toString(), mui.getId()
					.toString());
		return mulUnit;
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