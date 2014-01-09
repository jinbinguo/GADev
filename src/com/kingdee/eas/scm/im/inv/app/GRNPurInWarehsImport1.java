package com.kingdee.eas.scm.im.inv.app;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.ctrl.common.util.StringUtil;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.IMetaDataLoader;
import com.kingdee.bos.metadata.MetaDataLoaderFactory;
import com.kingdee.bos.metadata.entity.EntityObjectInfo;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.codingrule.CodingRuleManagerFactory;
import com.kingdee.eas.base.codingrule.ICodingRuleManager;
import com.kingdee.eas.base.permission.IUser;
import com.kingdee.eas.base.permission.UserCollection;
import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.assistant.CurrencyCollection;
import com.kingdee.eas.basedata.assistant.CurrencyFactory;
import com.kingdee.eas.basedata.assistant.CurrencyInfo;
import com.kingdee.eas.basedata.assistant.ICurrency;
import com.kingdee.eas.basedata.assistant.IMeasureUnit;
import com.kingdee.eas.basedata.assistant.IPaymentType;
import com.kingdee.eas.basedata.assistant.MeasureUnitFactory;
import com.kingdee.eas.basedata.assistant.MeasureUnitInfo;
import com.kingdee.eas.basedata.assistant.PaymentTypeCollection;
import com.kingdee.eas.basedata.assistant.PaymentTypeFactory;
import com.kingdee.eas.basedata.assistant.PaymentTypeInfo;
import com.kingdee.eas.basedata.master.cssp.ISupplier;
import com.kingdee.eas.basedata.master.cssp.SupplierCollection;
import com.kingdee.eas.basedata.master.cssp.SupplierFactory;
import com.kingdee.eas.basedata.master.cssp.SupplierInfo;
import com.kingdee.eas.basedata.master.material.AsstAttrTypeInfo;
import com.kingdee.eas.basedata.master.material.IMaterial;
import com.kingdee.eas.basedata.master.material.IMultiMeasureUnit;
import com.kingdee.eas.basedata.master.material.MaterialCollection;
import com.kingdee.eas.basedata.master.material.MaterialCompanyInfoInfo;
import com.kingdee.eas.basedata.master.material.MaterialFactory;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.master.material.MaterialInventoryFactory;
import com.kingdee.eas.basedata.master.material.MaterialInventoryInfo;
import com.kingdee.eas.basedata.master.material.MultiMeasureUnitFactory;
import com.kingdee.eas.basedata.master.material.MultiMeasureUnitInfo;
import com.kingdee.eas.basedata.master.material.TimeUnitEnum;
import com.kingdee.eas.basedata.org.AdminOrgUnitCollection;
import com.kingdee.eas.basedata.org.AdminOrgUnitFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.basedata.org.IAdminOrgUnit;
import com.kingdee.eas.basedata.org.IOrgUnitRelation;
import com.kingdee.eas.basedata.org.IPurchaseOrgUnit;
import com.kingdee.eas.basedata.org.IStorageOrgUnit;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitCollection;
import com.kingdee.eas.basedata.org.OrgUnitRelationFactory;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitCollection;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitFactory;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo;
import com.kingdee.eas.basedata.org.StorageOrgUnitCollection;
import com.kingdee.eas.basedata.org.StorageOrgUnitFactory;
import com.kingdee.eas.basedata.org.StorageOrgUnitInfo;
import com.kingdee.eas.basedata.person.IPerson;
import com.kingdee.eas.basedata.person.PersonCollection;
import com.kingdee.eas.basedata.person.PersonFactory;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.basedata.scm.common.BillTypeInfo;
import com.kingdee.eas.basedata.scm.common.BizTypeFactory;
import com.kingdee.eas.basedata.scm.common.BizTypeInfo;
import com.kingdee.eas.basedata.scm.common.ITransactionType;
import com.kingdee.eas.basedata.scm.common.TransactionTypeCollection;
import com.kingdee.eas.basedata.scm.common.TransactionTypeFactory;
import com.kingdee.eas.basedata.scm.common.TransactionTypeInfo;
import com.kingdee.eas.basedata.scm.im.inv.ILocation;
import com.kingdee.eas.basedata.scm.im.inv.ISOAccreditWH;
import com.kingdee.eas.basedata.scm.im.inv.IWarehouse;
import com.kingdee.eas.basedata.scm.im.inv.LocationCollection;
import com.kingdee.eas.basedata.scm.im.inv.LocationFactory;
import com.kingdee.eas.basedata.scm.im.inv.LocationInfo;
import com.kingdee.eas.basedata.scm.im.inv.RITypeEnum;
import com.kingdee.eas.basedata.scm.im.inv.SOAccreditWHFactory;
import com.kingdee.eas.basedata.scm.im.inv.WHStateEnum;
import com.kingdee.eas.basedata.scm.im.inv.WarehouseCollection;
import com.kingdee.eas.basedata.scm.im.inv.WarehouseFactory;
import com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo;
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
import com.kingdee.eas.scm.im.inv.IPurInWarehsBill;
import com.kingdee.eas.scm.im.inv.PurInWarehsBillCollection;
import com.kingdee.eas.scm.im.inv.PurInWarehsBillFactory;
import com.kingdee.eas.scm.im.inv.PurInWarehsBillInfo;
import com.kingdee.eas.scm.im.inv.PurInWarehsEntryInfo;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import org.apache.log4j.Logger;

public class GRNPurInWarehsImport1 extends AbstractDataTransmission {
	PurInWarehsBillInfo PurInWarehsBillInfo;
	private static String resources = "com.kingdee.eas.scm.im.inv.app.OtherBillDataImportResource";
	private boolean IsHaveValue;
	private boolean IsError;
	// private static final int ASSISTPROPERTY = 3;
	// private static final int ASSISTUNIT = 4;
	StorageOrgUnitInfo sou;
	TransactionTypeInfo tti;
	CompanyOrgUnitInfo cou;
	String[] STRING_FIELDS = { "FNumber", "FAdminOrgUnit_number",
			"FSupplier_number", "FTransactionType_number", "FCurrency_number",
			"FPaymentType_number", "FStorageOrgUnit_number",
			"FEntry$balanceSupplier_number", "FEntry$warehouse_number",
			"FEntry$material_number", "FEntry$unit_number",
			"FEntry$baseUnit_number", "FEntry_remark",
			"FEntry$purchaseOrgUnit_number", "FEntry$storageOrgUnit_number",
			"FEntry$companyOrgUnit_number" };

	String[] DATE_FIELDS = { "FBizDate" };

	String[] STRING_FIELDS_EN = { "FAdminOrgUnit_name_l2",
			"FTransactionType_name_l2", "FCurrency_name_l2",
			"FPaymentType_name_l2", "FStorageOrgUnit_name_l2",
			"FEntry$material_name_l2" };

	private static final String[] QTY_PROPERTY = { "qty" };

	private static final String[] ASSISTQTY_PROPERTY = { "assistQty" };

	private static final String[] DECIMAL_ENTRY_PROPERTY_ALL = { "FEntry_qty",
			"FEntry_assistQty", "FEntry_taxPrice", "FEntry_price",
			"FEntry_unitPurchaseCost", "FEntry_purchaseCost", "FEntry_taxRate",
			"FEntry_scUnWrittenOffBaseQty", "FEntry_scUnWrittenOffQty",
			"FEntry_scWrittenOffBaseQty", "FEntry_scWrittenOffQty" };

	private static final String[] DECIMAL_ENTRY_PROPERTY_ALL_KEY = { "qty",
			"assistQty", "taxPrice", "price", "unitPurchaseCost",
			"purchaseCost", "taxRate", "scUnWrittenOffBaseQty",
			"scUnWrittenOffQty", "scWrittenOffBaseQty", "scWrittenOffQty" };

	private static final BigDecimal ZERO = new BigDecimal("0.00");
	private int currentRowIndex = 1;
	private ArrayList<String> messageList = new ArrayList<String>();

	private String[] DECIMAL_ENTRY_PROPERTY_EXCLUDENEGATE = { "FEntry_price",
			"FEntry_taxPrice", "FEntry_actualPrice", "FEntry_actualTaxPrice",
			"FEntry_taxRate" };

	protected static final Logger logger = Logger
			.getLogger(GRNManufactureRecImport.class);

	public GRNPurInWarehsImport1() {
		this.PurInWarehsBillInfo = null;
		this.IsHaveValue = false;
		this.IsError = false;
		this.sou = null;
		this.tti = null;
		this.cou = null;
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
		logger.info("==========transmit=========");
		this.PurInWarehsBillInfo = null;
		this.messageList.clear();
		int k = 0;
		for (int i = 0; i < hsData.size(); i++) {
			int currentStart = Integer.parseInt(getContextParameter(
					"CURRENTSTART").toString());
			this.currentRowIndex = (currentStart + i);
			Hashtable lineData = (Hashtable) hsData.get(new Integer(i));
			if (k == 0) {
				this.PurInWarehsBillInfo = transmitHead(lineData, ctx);
				if (this.PurInWarehsBillInfo == null)
					return null;
				logger.info("==========transmit1=========");
				complateInfo(lineData, ctx);
				logger.info("==========transmit2=========");
			}
			PurInWarehsEntryInfo entryinfo = transmitEntry(lineData, ctx);
			logger.info("==========transmit3=========");
			int seq = this.PurInWarehsBillInfo.getEntry().size() + 1;
			entryinfo.setSeq(seq);
			entryinfo.setParent(this.PurInWarehsBillInfo);
			entryinfo.setStorageOrgUnit(this.sou);

			entryinfo.setCompanyOrgUnit(this.cou);
			this.PurInWarehsBillInfo.getEntry().add(entryinfo);
			k++;
		}

		BigDecimal totalActualCost = ZERO;
		for (int i = 0; i < this.PurInWarehsBillInfo.getEntry().size(); i++) {
			PurInWarehsEntryInfo entryInfo = this.PurInWarehsBillInfo
					.getEntry().get(i);
			if (entryInfo.getActualCost() != null)
				totalActualCost = totalActualCost
						.add(entryInfo.getActualCost());
		}
		this.PurInWarehsBillInfo.setTotalActualCost(totalActualCost);

		if (this.messageList.size() > 0) {
			String taskUuid = getContextParameter("TASKUUID").toString();
			for (int i = 0; i < this.messageList.size(); i++) {
				if (i != this.messageList.size() - 1) {
					LogError(taskUuid, (String) this.messageList.get(i));
				} else {
					throw new TaskExternalException((String) this.messageList
							.get(i));
				}
			}
		}

		return this.PurInWarehsBillInfo;
	}

	public int getSubmitType() {
		return 1;
	}

	public void submit(CoreBaseInfo coreBaseInfo, Context ctx)
			throws TaskExternalException {
		logger.info("=====submit=====");
		if ((coreBaseInfo == null)
				|| (!(coreBaseInfo instanceof PurInWarehsBillInfo)))
			return;
		try {
			PurInWarehsBillInfo bill = (PurInWarehsBillInfo) coreBaseInfo;
			IObjectPK userPK = ctx.getCaller();
			String id = getIdFromNumber(bill.getNumber(), ctx);
			IObjectPK orgPK = new ObjectUuidPK(bill.getStorageOrgUnit().getId());
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
		this.PurInWarehsBillInfo = new PurInWarehsBillInfo();
		String message = "";
		Object data = null;
		String str = null;
		boolean tryDoNumber = false;
		boolean userCodingRuleManager = false;

		Object number = ((DataToken) lineData.get("FNumber")).data;
		if ((number != null) && (number.toString().trim().length() > 0)) {
			this.PurInWarehsBillInfo.setNumber(number.toString().trim());
			String existId = getIdFromNumber(number.toString().trim(), ctx);
			if ((!isSltImportUpdate()) && (!StringUtil.isEmptyString(existId))) {
				message = String.format("第%s行：%s %s", new Object[] {
						Integer.valueOf(this.currentRowIndex),
						number.toString().trim(),
						getResource(ctx, "EXISTS", resources) });
				this.messageList.add(message);
				return this.PurInWarehsBillInfo;
			}

			if ((isSltImportUpdate()) && (!StringUtil.isEmptyString(existId))) {
				PurInWarehsBillInfo existInfo = null;
				try {
					existInfo = ((IPurInWarehsBill) getController(ctx))
							.getPurInWarehsBillInfo(new ObjectUuidPK(existId));
				} catch (Exception e) {
					message = String.format("第%s行：获取单据%s产生错误：", new Object[] {
							Integer.valueOf(this.currentRowIndex),
							number.toString().trim(), e.getMessage() });
					this.messageList.add(message);
					return this.PurInWarehsBillInfo;
				}

				if (!BillBaseStatusEnum.TEMPORARILYSAVED.equals(existInfo
						.getBaseStatus())) {
					message = String.format("第%s行：%s %s", new Object[] {
							Integer.valueOf(this.currentRowIndex),
							number.toString().trim(),
							getResource(ctx, "UnTEMPORARILYSAVEDERROE",
									resources) });
					this.messageList.add(message);
					return this.PurInWarehsBillInfo;
				}
			}

			if (StringUtil.isEmptyString(existId)) {
				userCodingRuleManager = true;
				tryDoNumber = true;
			}
		} else {
			tryDoNumber = true;
		}

		data = null;
		if (lineData.containsKey("FAdminOrgUnit_number"))
			data = ((DataToken) lineData.get("FAdminOrgUnit_number")).data;
		if (data != null) {
			str = data.toString();
			if ((str != null) && (str.length() > 0)) {
				try {
					IAdminOrgUnit iadminorg = AdminOrgUnitFactory
							.getLocalInstance(ctx);
					AdminOrgUnitCollection collection = iadminorg
							.getAdminOrgUnitCollection(getFilter(data
									.toString().trim()));
					if ((collection != null) && (collection.size() > 0)) {
						AdminOrgUnitInfo info = collection.get(0);
						if (info != null) {
							this.PurInWarehsBillInfo.setAdminOrgUnit(info);
						} else {
							message = String.format("第%s行：%s %s", new Object[] {
									Integer.valueOf(this.currentRowIndex),
									data,
									getResource(ctx, "adminOrgUnitNotExists",
											resources) });
							this.messageList.add(message);
						}
					} else {
						message = String.format("第%s行：%s %s", new Object[] {
								Integer.valueOf(this.currentRowIndex),
								data,
								getResource(ctx, "adminOrgUnitNotExists",
										resources) });
						this.messageList.add(message);
					}

				} catch (Exception ex) {
					message = String.format(
							"第%s行：FAdminOrgUnit_number引入产生错误：%s", new Object[] {
									Integer.valueOf(this.currentRowIndex),
									ex.getMessage() });
					this.messageList.add(message);
				}
			}

		}

		try {
			loadsupplier(lineData, ctx, "number", "FSupplier_number");
		} catch (Exception e_number) {
			try {
				loadsupplier(lineData, ctx, "name", "FSupplier_name_l2");
			} catch (Exception e_name) {
				message = String.format("第%s行：FSupplier_number引入产生错误：%s",
						new Object[] { Integer.valueOf(this.currentRowIndex),
								e_number.getMessage() });
				this.messageList.add(message);
			}

		}

		try {
			loadTransaction(lineData, ctx, "number", "FTransactionType_number");
		} catch (Exception e_number) {
			try {
				loadTransaction(lineData, ctx, "name",
						"FTransactionType_name_l2");
			} catch (Exception e_name) {
				message = String.format(
						"第%s行：FTransactionType_number引入产生错误：%s", new Object[] {
								Integer.valueOf(this.currentRowIndex),
								e_number.getMessage() });
				this.messageList.add(message);
			}

		}

		BillTypeInfo aBillTypeInfo = new BillTypeInfo();
		aBillTypeInfo.setId(BOSUuid
				.read("50957179-0105-1000-e000-015fc0a812fd463ED552"));
		aBillTypeInfo.setNumber("103");
		this.PurInWarehsBillInfo.setBillType(aBillTypeInfo);

		PurchaseTypeEnum purchaseType = PurchaseTypeEnum.PURCHASE;
		data = null;
		if (lineData.containsKey("FPurchaseType"))
			data = ((DataToken) lineData.get("FPurchaseType")).data;
		if (data != null) {
			str = data.toString();
			if ((str != null) && (str.length() > 0)) {
				int purchaseTypeValue = 0;
				try {
					purchaseTypeValue = Integer.parseInt(str);
					if ((purchaseTypeValue != 0) && (purchaseTypeValue != 1)) {
						message = String.format(
								"第%s行：%s FPurchaseType设置错误，必须为0或者1",
								new Object[] {
										Integer.valueOf(this.currentRowIndex),
										data });
						this.messageList.add(message);
					}
				} catch (NumberFormatException ex) {
					message = String
							.format(
									"第%s行：%s FPurchaseType设置错误，必须为0或者1",
									new Object[] {
											Integer
													.valueOf(this.currentRowIndex),
											data });
					this.messageList.add(message);
				}

				purchaseType = PurchaseTypeEnum.getEnum(purchaseTypeValue);
			}
		}
		this.PurInWarehsBillInfo.setPurchaseType(purchaseType);

		data = null;
		if (lineData.containsKey("FCurrency_number"))
			data = ((DataToken) lineData.get("FCurrency_number")).data;
		if (data != null) {
			str = data.toString();
			if ((str != null) && (str.length() > 0)) {
				try {
					ICurrency icur = CurrencyFactory.getLocalInstance(ctx);
					CurrencyCollection collection = icur
							.getCurrencyCollection(getFilter(data.toString()
									.trim()));
					if ((collection != null) && (collection.size() > 0)) {
						CurrencyInfo info = collection.get(0);
						if (info != null) {
							this.PurInWarehsBillInfo.setCurrency(info);
						} else {
							message = String
									.format(
											"第%s行：%s 币别不存在",
											new Object[] {
													Integer
															.valueOf(this.currentRowIndex),
													data });
							this.messageList.add(message);
						}
					} else {
						message = String.format("第%s行：%s 币别不存在", new Object[] {
								Integer.valueOf(this.currentRowIndex), data });
						this.messageList.add(message);
					}

				} catch (Exception ex) {
					message = String.format("第%s行：FCurrency_number引入产生错误：%s",
							new Object[] {
									Integer.valueOf(this.currentRowIndex),
									ex.getMessage() });
					this.messageList.add(message);
				}
			}
		}

		data = null;
		if (lineData.containsKey("FPaymentType_number"))
			data = ((DataToken) lineData.get("FPaymentType_number")).data;
		if (data != null) {
			str = data.toString();
			if ((str != null) && (str.length() > 0)) {
				try {
					IPaymentType ipay = PaymentTypeFactory
							.getLocalInstance(ctx);
					PaymentTypeCollection collection = ipay
							.getPaymentTypeCollection(getFilter(data.toString()
									.trim()));
					if ((collection != null) && (collection.size() > 0)) {
						PaymentTypeInfo info = collection.get(0);
						if (info != null) {
							this.PurInWarehsBillInfo.setPaymentType(info);
						} else {
							message = String
									.format(
											"第%s行：%s 付款方式不存在",
											new Object[] {
													Integer
															.valueOf(this.currentRowIndex),
													data });
							this.messageList.add(message);
						}
					} else {
						message = String.format("第%s行：%s 付款方式不存在",
								new Object[] {
										Integer.valueOf(this.currentRowIndex),
										data });
						this.messageList.add(message);
					}

				} catch (Exception ex) {
					message = String.format(
							"第%s行：FPaymentType_number引入产生错误：%s", new Object[] {
									Integer.valueOf(this.currentRowIndex),
									ex.getMessage() });
					this.messageList.add(message);
				}
			}
		}

		try {
			loadstorageOrgUnit(lineData, ctx, "number",
					"FStorageOrgUnit_number");
		} catch (Exception e_number) {
			try {
				loadstorageOrgUnit(lineData, ctx, "name",
						"FStorageOrgUnit_name_l2");
			} catch (Exception e_name) {
				message = String.format("第%s行：库存组织不存在", new Object[] { Integer
						.valueOf(this.currentRowIndex) });
				this.messageList.add(message);
			}
		}

		try {
			if (tryDoNumber) {
				boolean isUsedParam = DataImportUtils.getImportParam(ctx);
				if (isUsedParam) {
					String souID = this.sou.getId().toString().trim();
					ICodingRuleManager iCodingRuleManager = CodingRuleManagerFactory
							.getLocalInstance(ctx);
					if (iCodingRuleManager.isExist(this.PurInWarehsBillInfo,
							souID)) {
						this.PurInWarehsBillInfo
								.setNumber(iCodingRuleManager.getNumber(
										this.PurInWarehsBillInfo, souID, ""));
					} else if (!userCodingRuleManager) {
						IMetaDataLoader imeataLoader = MetaDataLoaderFactory
								.getLocalMetaDataLoader(ctx);
						EntityObjectInfo entityObjectInfo = imeataLoader
								.getEntity(this.sou.getBOSType());
						String[] materialNames = new String[1];
						materialNames[0] = entityObjectInfo.getAlias();
						throw new SCMBillException(SCMBillException.NOCORDRULE,
								materialNames);
					}
				} else if (!userCodingRuleManager) {
					throw new TaskExternalException(getResource(ctx,
							"UnUseDataImportParam", resources));
				}
			}
		} catch (Exception e) {
			throw new TaskExternalException(e.getMessage(), e.getCause());
		}
		this.PurInWarehsBillInfo.setCU(ContextUtil.getCurrentCtrlUnit(ctx));
		try {
			IOrgUnitRelation iUnitRel = OrgUnitRelationFactory
					.getLocalInstance(ctx);
			OrgUnitCollection orgCol = iUnitRel.getToUnit(this.sou.getId()
					.toString(), 4, 1);
			if (orgCol.get(0) != null) {
				this.cou = ((CompanyOrgUnitInfo) orgCol.get(0));
			} else {
				message = String.format("第%s行：%s", new Object[] {
						Integer.valueOf(this.currentRowIndex),
						getResource(ctx, "NoCompanyOrgUnit", resources) });
				this.messageList.add(message);
			}

		} catch (Exception e) {
			message = String.format("第%s行：%s", new Object[] {
					Integer.valueOf(this.currentRowIndex), e.getMessage() });
			this.messageList.add(message);
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date da = df.parse(lineData.get("FBizDate").toString());
			this.PurInWarehsBillInfo.setBizDate(da);

			Calendar bizCal = Calendar.getInstance();
			bizCal.setTime(da);
			int bizYear = bizCal.get(1);
			int bizMonth = bizCal.get(2) + 1;
			this.PurInWarehsBillInfo.setPeriod(bizMonth);
			this.PurInWarehsBillInfo.setYear(bizYear);
			DateFormat monthFormatter = new SimpleDateFormat("yyyyMM");
			DateFormat dayFormatter = new SimpleDateFormat("yyyyMMdd");
			this.PurInWarehsBillInfo.setMonth(Integer.parseInt(monthFormatter
					.format(da)));
			this.PurInWarehsBillInfo.setDay(Integer.parseInt(dayFormatter
					.format(da)));
		} catch (ParseException e1) {
			message = String.format("第%s行：%s %s", new Object[] {
					Integer.valueOf(this.currentRowIndex),
					lineData.get("FBizDate").toString(),
					getResource(ctx, "InvalidDateFormat", resources) });
			this.messageList.add(message);
		}

		BillBaseStatusEnum baseStatus = BillBaseStatusEnum.TEMPORARILYSAVED;
		if (lineData.containsKey("FBaseStatus")) {
			String baseStatusStr = ((DataToken) lineData.get("FBaseStatus")).data
					.toString();
			int billBaseStatusEnum = new Integer(baseStatusStr).intValue();
			baseStatus = BillBaseStatusEnum.getEnum(billBaseStatusEnum);
		}

		this.PurInWarehsBillInfo.setBaseStatus(baseStatus);
		this.IsHaveValue = false;
		this.IsError = false;
		data = null;
		if (lineData.containsKey("FCreator_number")) {
			data = ((DataToken) lineData.get("FCreator_number")).data;
		}
		if (data != null) {
			str = data.toString();
			if ((str != null) && (str.length() > 0)) {
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
					if ((collection != null) && (collection.size() > 0)) {
						UserInfo info = collection.get(0);
						if (info != null) {
							this.PurInWarehsBillInfo.setCreator(info);
							this.IsHaveValue = true;
						}
					}
				} catch (Exception ex) {
					message = String.format(
							"第%s行：FCreator_number:%s 引入产生错误：%s", new Object[] {
									Integer.valueOf(this.currentRowIndex),
									data.toString(), ex.getMessage() });
					this.messageList.add(message);
				}
			}
		}
		if (!this.IsHaveValue) {
			UserInfo userinfo = ContextUtil.getCurrentUserInfo(ctx);
			this.PurInWarehsBillInfo.setCreator(userinfo);
		}
		this.IsHaveValue = false;
		this.IsError = false;
		Object createdate = null;
		if (lineData.containsKey("FCreateTime")) {
			createdate = ((DataToken) lineData.get("FCreateTime")).data;
			if ((createdate != null) && (createdate.toString().length() > 0))
				try {
					createdate = df.parseObject(createdate.toString());
					this.PurInWarehsBillInfo.setCreateTime(new Timestamp(
							((Date) createdate).getTime()));
					this.IsHaveValue = true;
				} catch (ParseException e) {
					this.IsError = false;
				}
		}
		if ((!this.IsError) || (!this.IsHaveValue)) {
			Date date = new Date();
			this.PurInWarehsBillInfo
					.setCreateTime(new Timestamp(date.getTime()));
		}

		this.PurInWarehsBillInfo.setIsInTax(getBooleanField(lineData,
				"FIsInTax"));
		this.PurInWarehsBillInfo.setIsPriceInTax(getBooleanField(lineData,
				"FIsPriceInTax"));
		this.PurInWarehsBillInfo.setIsSysBill(getBooleanField(lineData,
				"FIsSysBill"));

		this.PurInWarehsBillInfo.setIsReversed(getBooleanField(lineData,
				"FIsReversed"));
		this.PurInWarehsBillInfo.setIsInitBill(getBooleanField(lineData,
				"FIsInitBill"));
		this.PurInWarehsBillInfo.setHasEffected(getBooleanField(lineData,
				"FHasEffected"));

		return this.PurInWarehsBillInfo;
	}

	@SuppressWarnings("unchecked")
	private boolean getBooleanField(Hashtable lineData, String fieldName) {
		boolean result = false;

		Object data = null;
		if (lineData.containsKey(fieldName))
			data = ((DataToken) lineData.get(fieldName)).data;
		if (data != null) {
			String str = data.toString();
			if ((str != null) && (str.length() > 0)) {
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
			Object supplierData = null;
			if (lineData.containsKey("FEntry$balanceSupplier_number"))
				supplierData = ((DataToken) lineData
						.get("FEntry$balanceSupplier_number")).data;
			if ((supplierData != null)
					&& (supplierData.toString().trim().length() > 0)) {
				ISupplier isupplier = SupplierFactory.getLocalInstance(ctx);
				SupplierCollection collection = isupplier
						.getSupplierCollection(getFilter(supplierData
								.toString().trim(), "number"));
				if ((collection != null) && (collection.size() > 0)) {
					entryInfo.setBalanceSupplier(collection.get(0));
				}
			}
			if (entryInfo.getBalanceSupplier() == null) {
				SupplierInfo info = this.PurInWarehsBillInfo.getSupplier();
				if (info != null) {
					entryInfo.setBalanceSupplier(info);
				} else {
					message = String.format(
							"第%s行：FEntry$balanceSupplier_number不存在或者内容为空",
							new Object[] { Integer
									.valueOf(this.currentRowIndex) });
					this.messageList.add(message);
				}
			}

			WarehouseInfo warehouseInfo = new WarehouseInfo();
			data = null;
			if (lineData.containsKey("FEntry$warehouse_number"))
				data = ((DataToken) lineData.get("FEntry$warehouse_number")).data;
			try {
				if ((data != null) && (data.toString() != null)
						&& (data.toString().length() > 0)) {
					str = data.toString();
					if (str != null) {
						IWarehouse iw = WarehouseFactory.getLocalInstance(ctx);
						WarehouseCollection collection = iw
								.getWarehouseCollection(getFilter(str));
						if ((collection != null) && (collection.size() > 0)) {
							WarehouseInfo info = collection.get(0);
							if (info != null) {
								if (!info.getWhState().equals(
										WHStateEnum.ACTIVE)) {
									message = String
											.format(
													"第%s行：%s %s",
													new Object[] {
															Integer
																	.valueOf(this.currentRowIndex),
															info.getNumber(),
															getResource(
																	ctx,
																	"WarehouseNotActive",
																	resources) });
									this.messageList.add(message);
								} else {
									checkWarehouse(ctx, info, this.sou);
									entryInfo.setWarehouse(info);
									warehouseInfo = info;
								}
							} else {
								message = String
										.format(
												"第%s行：%s %s",
												new Object[] {
														Integer
																.valueOf(this.currentRowIndex),
														data,
														getResource(
																ctx,
																"WarehouseNotExists",
																resources) });
								this.messageList.add(message);
							}
						} else {
							message = String.format("第%s行：%s %s", new Object[] {
									Integer.valueOf(this.currentRowIndex),
									data,
									getResource(ctx, "WarehouseNotExists",
											resources) });
							this.messageList.add(message);
						}
					}
				} else {
					message = String
							.format("第%s行：%s %s", new Object[] {
									Integer.valueOf(this.currentRowIndex),
									data,
									getResource(ctx, "WarehouseNotExists",
											resources) });
					this.messageList.add(message);
				}

			} catch (Exception e) {
				message = String.format(
						"第%s行：FEntry$warehouse_number引入产生错误：%s", new Object[] {
								Integer.valueOf(this.currentRowIndex),
								e.getMessage() });
				this.messageList.add(message);
			}

			data = null;
			if (lineData.containsKey("FEntry$material_number"))
				data = ((DataToken) lineData.get("FEntry$material_number")).data;
			if ((data != null) && (data.toString().trim().length() > 0)) {
				IMaterial imaterial = MaterialFactory.getLocalInstance(ctx);
				MaterialCollection collection = imaterial
						.getMaterialCollection(getFilter(data.toString()));
				if ((collection != null) && (collection.size() > 0)) {
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
						// materialInventory =
						// imaterial.getInventoryInfo(materialInfo
						// .getId().toString(), this.sou.getId().toString());
						if (materialInventory == null) {
							message = String
									.format(
											"第%s行：%s %s %s",
											new Object[] {
													Integer
															.valueOf(this.currentRowIndex),
													materialInfo.getNumber(),
													this.sou.getNumber(),
													getResource(
															ctx,
															"NO_INVENTORY_PROPERTY",
															resources) });
							this.messageList.add(message);
						}

						materialCompanyInfo = imaterial.getCompanyInfo(
								materialInfo.getId().toString(), this.cou
										.getId().toString());
						if (materialCompanyInfo == null) {
							message = String
									.format(
											"第%s行：%s %s %s",
											new Object[] {
													Integer
															.valueOf(this.currentRowIndex),
													materialInfo.getNumber(),
													this.cou.getNumber(),
													getResource(
															ctx,
															"NO_COMPANY_PROPERTY",
															resources) });
							this.messageList.add(message);
						}
					} else {
						message = String.format("第%s行：%s %s",
								new Object[] {
										Integer.valueOf(this.currentRowIndex),
										data.toString(),
										getResource(ctx, "MaterialNotExists",
												resources) });
						this.messageList.add(message);
					}
				} else {
					message = String.format("第%s行：%s %s", new Object[] {
							Integer.valueOf(this.currentRowIndex),
							data.toString(),
							getResource(ctx, "MaterialNotExists", resources) });
					this.messageList.add(message);
				}
			} else {
				message = String.format("第%s行：%s", new Object[] {
						Integer.valueOf(this.currentRowIndex),
						getResource(ctx, "MaterialNotExists", resources) });
				this.messageList.add(message);
			}

			if ((materialInventory != null)
					&& (materialInventory.isIsPeriodValid())) {
				Date mfg = entryInfo.getMfg();
				// Date exp = entryInfo.getExp();
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

			entryInfo.setUnit(materialInfo.getBaseUnit());
			mui = materialInfo.getBaseUnit();

			entryInfo.setAssistUnit(materialInfo.getAssistUnit());
			if (entryInfo.getAssistUnit() != null) {
				MultiMeasureUnitInfo assistMmu = MultiMeasureUnitFactory
						.getLocalInstance(ctx).getMultiUnit(
								materialInfo.getId().toString(),
								entryInfo.getAssistUnit().getId().toString());
				if (assistMmu != null) {
					String[] ASSISTQTY_PROPERTY = { "FEntry_assistQty" };

					int assistQtyPrecision = assistMmu.getQtyPrecision();
					for (int i = 0; i < ASSISTQTY_PROPERTY.length; i++) {
						if (entryInfo.getBigDecimal(ASSISTQTY_PROPERTY[i]) != null)
							entryInfo.setBigDecimal(ASSISTQTY_PROPERTY[i],
									entryInfo.getBigDecimal(
											ASSISTQTY_PROPERTY[i]).setScale(
											assistQtyPrecision, 4));
					}
					if ((entryInfo.getAssistQty() == null)
							|| (entryInfo.getAssistQty().compareTo(ZERO) == 0)) {
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
						new Object[] { Integer.valueOf(this.currentRowIndex),
								ex.getMessage() });
				this.messageList.add(message);
			}

			int amtPrecision = 6;
			if (entryInfo.getMaterial() != null) {
				amtPrecision = entryInfo.getMaterial().getPricePrecision();
			}

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
			else {
				entryInfo.setAssistQty(ZERO);
			}
			data = null;
			if (lineData.containsKey("FEntry$stocker_number"))
				data = ((DataToken) lineData.get("FEntry$stocker_number")).data;
			if (data != null) {
				str = data.toString();
				if ((str != null) && (str.trim().length() > 0)) {
					try {
						IPerson iPerson = PersonFactory.getLocalInstance(ctx);
						PersonCollection collection = iPerson
								.getPersonCollection(getFilter(str));
						if ((collection != null) && (collection.size() > 0)) {
							PersonInfo info = collection.get(0);
							if (info != null) {
								entryInfo.setStocker(info);
							} else {
								message = String
										.format(
												"第%s行：%s %s",
												new Object[] {
														Integer
																.valueOf(this.currentRowIndex),
														data,
														getResource(
																ctx,
																"stockerNotExists",
																resources) });
								this.messageList.add(message);
							}
						} else {
							message = String.format("第%s行：%s %s", new Object[] {
									Integer.valueOf(this.currentRowIndex),
									data,
									getResource(ctx, "stockerNotExists",
											resources) });
							this.messageList.add(message);
						}

					} catch (Exception ex) {
						message = String.format(
								"第%s行：FEntry$stocker_number引入产生错误：%s",
								new Object[] {
										Integer.valueOf(this.currentRowIndex),
										data, ex.getMessage() });
						this.messageList.add(message);
					}
				}
			}

			data = null;
			if (lineData.containsKey("FEntry$location_number"))
				data = ((DataToken) lineData.get("FEntry$location_number")).data;
			if ((warehouseInfo.isHasLocation())
					&& ((data == null) || (data.toString().length() <= 0)))
				throw new TaskExternalException(warehouseInfo.getNumber() + " "
						+ getResource(ctx, "LocationMustNotBeNull", resources));
			if ((!warehouseInfo.isHasLocation()) && (data != null)
					&& (data.toString().length() > 0))
				throw new TaskExternalException(warehouseInfo.getNumber() + " "
						+ getResource(ctx, "LocationMustBeNull", resources));
			if (data != null) {
				String number = data.toString();
				if ((number != null) && (number.trim().length() > 0)) {
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
					if ((collection != null) && (collection.size() > 0)) {
						LocationInfo info = collection.get(0);
						if (info != null) {
							if (!info.getState().equals(WHStateEnum.ACTIVE)) {
								message = String
										.format(
												"第%s行：%s %s",
												new Object[] {
														Integer
																.valueOf(this.currentRowIndex),
														info.getNumber(),
														getResource(
																ctx,
																"LocationNotActive",
																resources) });
								this.messageList.add(message);
							}

							if (!warehouseInfo.getId().toString().equals(
									info.getWarehouse().getId().toString())) {
								message = String
										.format(
												"第%s行：%s %s %s",
												new Object[] {
														Integer
																.valueOf(this.currentRowIndex),
														info.getNumber(),
														getResource(
																ctx,
																"import_location_not_match_warehouse",
																resources),
														warehouseInfo
																.getNumber() });
								this.messageList.add(message);
							}

							entryInfo.setLocation(info);
						} else {
							message = String.format("第%s行：%s %s", new Object[] {
									Integer.valueOf(this.currentRowIndex),
									data,
									getResource(ctx, "LocationNotExists",
											resources) });
							this.messageList.add(message);
						}
					} else {
						message = String.format("第%s行：%s %s",
								new Object[] {
										Integer.valueOf(this.currentRowIndex),
										data,
										getResource(ctx, "LocationNotExists",
												resources) });
						this.messageList.add(message);
					}
				}

			}

			Object o = null;
			if (lineData.containsKey("FEntry_remark"))
				o = ((DataToken) lineData.get("FEntry_remark")).data;
			if ((o != null) && (o.toString().trim().length() > 0)
					&& (o != null) && (o.toString().length() > 0)) {
				entryInfo.setRemark(o.toString());
			}
			IMeasureUnit imu = MeasureUnitFactory.getLocalInstance(ctx);
			IObjectPK pk = new ObjectUuidPK(materialInfo.getBaseUnit().getId());
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

			if ((entryInfo.getTaxPrice() == null)
					|| (entryInfo.getTaxPrice() == ZERO)) {
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
			logger.info("=====unitMaterialCost=" + unitMaterialCost);

			BigDecimal materialCost = unitMaterialCost.multiply(qty).setScale(
					amtPrecision, 4);
			entryInfo.setMaterialCost(materialCost);
			logger.info("=====materialCost = " + materialCost);

			data = null;
			if (lineData.containsKey("FEntry$purchaseOrgUnit_number"))
				data = ((DataToken) lineData
						.get("FEntry$purchaseOrgUnit_number")).data;
			if (data != null) {
				String number = data.toString();
				if ((number != null) && (number.trim().length() > 0)) {
					try {
						IPurchaseOrgUnit ipou = PurchaseOrgUnitFactory
								.getLocalInstance(ctx);
						PurchaseOrgUnitCollection collection = ipou
								.getPurchaseOrgUnitCollection(getFilter(number
										.trim()));
						if ((collection != null) && (collection.size() > 0)) {
							PurchaseOrgUnitInfo info = collection.get(0);
							if (info != null) {
								entryInfo.setPurchaseOrgUnit(info);
							} else {
								message = String
										.format(
												"第%s行：%s 采购组织不存在",
												new Object[] {
														Integer
																.valueOf(this.currentRowIndex),
														data });
								this.messageList.add(message);
							}
						} else {
							message = String
									.format(
											"第%s行：%s 采购组织不存在",
											new Object[] {
													Integer
															.valueOf(this.currentRowIndex),
													data });
							this.messageList.add(message);
						}

					} catch (Exception ex) {
						message = String.format(
								"第%s行：FEntry$purchaseOrgUnit_number引入产生错误：%s",
								new Object[] {
										Integer.valueOf(this.currentRowIndex),
										ex.getMessage() });
						this.messageList.add(message);
					}
				}

			}

			entryInfo
					.setIsPresent(getBooleanField(lineData, "FEntry_isPresent"));

			data = null;
			if (lineData.containsKey("FEntry$storageOrgUnit_number"))
				data = ((DataToken) lineData
						.get("FEntry$storageOrgUnit_number")).data;
			if ((data != null) && (data.toString().trim().length() > 0)) {
				IStorageOrgUnit isou = StorageOrgUnitFactory
						.getLocalInstance(ctx);
				StorageOrgUnitCollection collection = isou
						.getStorageOrgUnitCollection(getFilter(data.toString()
								.trim()));
				if ((collection != null) && (collection.size() > 0)) {
					StorageOrgUnitInfo org = collection.get(0);
					if (!org.isIsBizUnit()) {
						message = String.format("第%s行：%s", new Object[] {
								Integer.valueOf(this.currentRowIndex),
								getResource(ctx, "StorageOrgUnitIsNotBizUnit",
										resources) });
						this.messageList.add(message);
					} else {
						entryInfo.setStorageOrgUnit(org);
					}
				} else if (this.sou == null) {
					message = String.format("第%s行：%s %s", new Object[] {
							Integer.valueOf(this.currentRowIndex),
							data,
							getResource(ctx, "StorageOrgUnitNotExists",
									resources) });
					this.messageList.add(message);
				} else {
					entryInfo.setStorageOrgUnit(this.sou);
				}

			} else if (this.sou == null) {
				message = String.format("第%s行：%s %s", new Object[] {
						Integer.valueOf(this.currentRowIndex), data,
						getResource(ctx, "StorageOrgUnitNotNull", resources) });
				this.messageList.add(message);
			} else {
				entryInfo.setStorageOrgUnit(this.sou);
			}
		} catch (Exception ex) {
			message = String.format("第%s行：数据引入产生错误：%s", new Object[] {
					Integer.valueOf(this.currentRowIndex), ex.getMessage() });
			this.messageList.add(message);
		}

		entryInfo.setSourceBillEntrySeq(0);
		entryInfo.setPurOrderEntrySeq(0);
		entryInfo.setHasSameCou(false);
		entryInfo.setDosingType(DosingTypeEnum.BOM);
		entryInfo.setBaseStatus(EntryBaseStatusEnum.NULL);

		if (this.PurInWarehsBillInfo.getTransactionType() != null) {
			// if
			// (this.PurInWarehsBillInfo.getTransactionType().getSourceBillType
			// () == null)
			// {
			entryInfo.setOutLocation(entryInfo.getLocation());
			entryInfo.setOutWarehouse(entryInfo.getWarehouse());
			// }
		}

		boolean isNegativeBill = this.PurInWarehsBillInfo.isIsReversed()
				^ isReturnRIType(this.PurInWarehsBillInfo.getTransactionType());

		if (entryInfo.getReturnBaseQty() == null)
			entryInfo.setReturnBaseQty(ZERO);
		if (entryInfo.getBaseQty() == null)
			entryInfo.setBaseQty(ZERO);
		entryInfo.setUnReturnedBaseQty(entryInfo.getBaseQty().abs().subtract(
				entryInfo.getReturnBaseQty().abs()));

		if (isNegativeBill)
			entryInfo.setUnReturnedBaseQty(entryInfo.getUnReturnedBaseQty()
					.abs().negate());
		else {
			entryInfo.setUnReturnedBaseQty(entryInfo.getUnReturnedBaseQty()
					.abs());
		}

		if (PurchaseTypeEnum.SUBCONTRACT.equals(this.PurInWarehsBillInfo
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
		return (transType.getRIType().getValue() == 30)
				|| (transType.getRIType().getValue() == 20)
				|| (transType.getRIType().getValue() == 21)
				|| (transType.getRIType().getValue() == 14)
				|| (transType.getRIType().getValue() == 43)
				|| (transType.getRIType().getValue() == 22)
				|| (transType.getRIType().getValue() == 50);
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
		if ((strKey == null) || (strKey.trim().length() == 0)) {
			return null;
		}
		return SCMUtils.getResource(resource, strKey, ctx);
	}

	@SuppressWarnings("unused")
	private boolean getBooleanValue(String value) {
		if (value == null)
			return false;
		value = value.trim();
		return (!value.equalsIgnoreCase("N"))
				&& (!value.equalsIgnoreCase("NO"))
				&& (!value.equalsIgnoreCase("0"))
				&& (!value.equalsIgnoreCase("false"))
				&& (!value.equalsIgnoreCase("否"));
	}

	@SuppressWarnings("unused")
	private void checkUnit(Context ctx, MaterialInfo materialInfo,
			MeasureUnitInfo mui) throws TaskExternalException, BOSException,
			EASBizException {
		IMultiMeasureUnit immu = MultiMeasureUnitFactory.getLocalInstance(ctx);
		MultiMeasureUnitInfo mmui = immu.getMultiUnit(materialInfo.getId()
				.toString(), mui.getId().toString());
		if (mmui == null)
			throw new TaskExternalException(materialInfo.getNumber()
					+ getResource(ctx, "UnitNotExists", resources));
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
		if ((collection != null) && (collection.size() > 0)) {
			return collection.get(0).getId().toString();
		}
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
				if (("".equalsIgnoreCase(DataImportUtils.transformValue(
						rs.getString("ENTRY.QTY")).toString()))
						|| (DataImportUtils.transformValue(
								rs.getString("ENTRY.QTY")).toString().length() < 1)) {
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
				if (("".equalsIgnoreCase(DataImportUtils.transformValue(
						rs.getString("ENTRY.ASSISTQTY")).toString()))
						|| (DataImportUtils.transformValue(
								rs.getString("ENTRY.ASSISTQTY")).toString()
								.length() < 1)) {
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
				if (("".equalsIgnoreCase(DataImportUtils.transformValue(
						rs.getString("ENTRY.actualCost")).toString()))
						|| (DataImportUtils.transformValue(
								rs.getString("ENTRY.actualCost")).toString()
								.length() < 1)) {
					result.put("FEntry_actualCost", DataImportUtils
							.transformValue(null));
				} else {
					BigDecimal qty = new BigDecimal(rs
							.getString("ENTRY.actualCost"));
					result.put("FEntry_actualCost", qty.abs());
				}
			if (rs.getString("ENTRY.UNITACTUALCOST") != null)
				if (("".equalsIgnoreCase(DataImportUtils.transformValue(
						rs.getString("ENTRY.UNITACTUALCOST")).toString()))
						|| (DataImportUtils.transformValue(
								rs.getString("ENTRY.UNITACTUALCOST"))
								.toString().length() < 1)) {
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
		if ((collection != null) && (collection.size() > 0)) {
			LinkedHashSet keys = new LinkedHashSet();
			for (int i = 0; i < collection.size(); i++) {
				keys.add(collection.get(i).getId().toString());
			}
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

	@SuppressWarnings("unused")
	private boolean checkLotInfo(Context ctx, MaterialInfo materialInfo,
			Object data, StorageOrgUnitInfo sou, PurInWarehsEntryInfo entryInfo)
			throws TaskExternalException, BOSException, EASBizException {
		//IMaterial iMaterial = MaterialFactory.getLocalInstance(ctx);
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
			case 1:
				field = 1;
				break;
			case 2:
				field = 2;
				break;
			case 3:
				field = 5;
				break;
			default:
				field = 5;
			}

			calendar.add(field, unitValue);
			return calendar.getTime();
		}

		return null;
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
			String message = String.format("第%s行：%s,%s %s", new Object[] {
					Integer.valueOf(this.currentRowIndex),
					warehouseInfo.getNumber(), sou.getNumber(),
					getResource(ctx, "RelationNotExists", resources) });
			this.messageList.add(message);
			return;
		}
	}

	@SuppressWarnings("unused")
	private void checkMaterialInfo(Context ctx, MaterialInfo materialInfo,
			Object data, int type) throws TaskExternalException, BOSException {
		if (type == 3) {
			AsstAttrTypeInfo asstAttrTypeInfo = materialInfo.getAssistAttr();
			if ((asstAttrTypeInfo == null) && (data != null)
					&& (data.toString().length() > 0)) {
				String message = String.format("第%s行：%s %s", new Object[] {
						Integer.valueOf(this.currentRowIndex),
						materialInfo.getNumber(),
						getResource(ctx, "NotSetAssistProperty", resources) });
				this.messageList.add(message);
				return;
			}

			if ((asstAttrTypeInfo != null)
					&& ((data == null) || (data.toString().length() <= 0))) {
				String message = String.format("第%s行：%s %s", new Object[] {
						Integer.valueOf(this.currentRowIndex),
						materialInfo.getNumber(),
						getResource(ctx, "SetAssistProperty", resources) });
				this.messageList.add(message);
				return;
			}

		} else if (type == 4) {
			MeasureUnitInfo assistUnit = materialInfo.getAssistUnit();
			if ((assistUnit == null) && (data != null)
					&& (data.toString().length() > 0)) {
				String message = String.format("第%s行：%s %s", new Object[] {
						Integer.valueOf(this.currentRowIndex),
						materialInfo.getNumber(),
						getResource(ctx, "NotSetAssistUnit", resources) });
				this.messageList.add(message);
				return;
			}

			if ((assistUnit != null)
					&& ((data == null) || (data.toString().length() <= 0))) {
				String message = String.format("第%s行：%s %s", new Object[] {
						Integer.valueOf(this.currentRowIndex),
						materialInfo.getNumber(),
						getResource(ctx, "SetAssistUnit", resources) });
				this.messageList.add(message);
				return;
			}
		}
	}

	@SuppressWarnings("unused")
	private void checkAssistUnit(Context ctx, MaterialInfo mi,
			MeasureUnitInfo mui) throws TaskExternalException {
		MeasureUnitInfo amui = mi.getAssistUnit();
		if (!amui.getId().equals(mui.getId())) {
			String message = String.format("第%s行：%s %s", new Object[] {
					Integer.valueOf(this.currentRowIndex), mi.getName(),
					getResource(ctx, "AssistQtyIsError", resources) });
			this.messageList.add(message);
			return;
		}
	}

	@SuppressWarnings("unchecked")
	private void loadTransaction(Hashtable lineData, Context ctx,
			String byProperty, String propertyName)
			throws TaskExternalException {
		try {
			Object souData = ((DataToken) lineData.get(propertyName)).data;
			if ((souData != null) && (souData.toString().trim().length() > 0)) {
				ITransactionType isou = TransactionTypeFactory
						.getLocalInstance(ctx);
				TransactionTypeCollection collection = isou
						.getTransactionTypeCollection(getFilter(souData
								.toString().trim(), byProperty));
				if ((collection != null) && (collection.size() > 0)) {
					this.tti = collection.get(0);
					this.PurInWarehsBillInfo.setTransactionType(this.tti);
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
			if ((supplierInfoData != null)
					&& (supplierInfoData.toString().trim().length() > 0)) {
				ISupplier isupplier = SupplierFactory.getLocalInstance(ctx);
				SupplierCollection collection = isupplier
						.getSupplierCollection(getFilter(supplierInfoData
								.toString().trim(), byProperty));
				if ((collection != null) && (collection.size() > 0)) {
					this.PurInWarehsBillInfo.setSupplier(collection.get(0));
				} else
					throw new TaskExternalException(supplierInfoData.toString()
							.trim()
							+ " "
							+ getResource(ctx, "SupplierNotExists", resources));
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
			if ((souData != null) && (souData.toString().trim().length() > 0)) {
				IStorageOrgUnit isou = StorageOrgUnitFactory
						.getLocalInstance(ctx);
				StorageOrgUnitCollection collection = isou
						.getStorageOrgUnitCollection(getFilter(souData
								.toString().trim(), byProperty));
				if ((collection != null) && (collection.size() > 0)) {
					this.sou = collection.get(0);
					if (!this.sou.isIsBizUnit())
						throw new TaskExternalException(getResource(ctx,
								"StorageOrgUnitIsNotBizUnit", resources));
					this.PurInWarehsBillInfo.setStorageOrgUnit(this.sou);
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

	@SuppressWarnings( { "unchecked", "unused" })
	private void loadMaterial(Hashtable lineData, Context ctx,
			String byProperty, String propertyName,
			PurInWarehsEntryInfo entryInfo, MaterialInfo materialInfo,
			MaterialInventoryInfo materialInventory)
			throws TaskExternalException {
		try {
			Object souData = ((DataToken) lineData.get(propertyName)).data;
			if ((souData != null) && (souData.toString().trim().length() > 0)) {
				IMaterial imaterial = MaterialFactory.getLocalInstance(ctx);
				MaterialCollection collection = imaterial
						.getMaterialCollection(getFilter(souData.toString()
								.trim(), byProperty));
				if ((collection != null) && (collection.size() > 0)) {
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
						// materialInventory =
						// imaterial.getInventoryInfo(materialInfo
						// .getId().toString(), this.sou.getId().toString());
					} else {
						throw new TaskExternalException(souData.toString()
								+ " "
								+ getResource(ctx, "MaterialNotExists",
										resources));
					}
				} else {
					throw new TaskExternalException(souData.toString() + " "
							+ getResource(ctx, "MaterialNotExists", resources));
				}
			} else {
				throw new TaskExternalException(getResource(ctx,
						"MaterialNotExists", resources));
			}
		} catch (BOSException e) {
			throw new TaskExternalException(e.getMessage(), e.getCause());
		} catch (EASBizException e) {
			throw new TaskExternalException(e.getMessage(), e.getCause());
		}
	}

	private boolean checkTransactionTypeInEnum() {
		return (this.tti != null)
				&& ((RITypeEnum.PurRt.equals(this.tti.getRIType()))
						|| (RITypeEnum.SaleRt.equals(this.tti.getRIType()))
						|| (RITypeEnum.ReqRt.equals(this.tti.getRIType())) || (RITypeEnum.ManuRt
						.equals(this.tti.getRIType())));
	}

	@SuppressWarnings("unused")
	private void complateEntery(PurInWarehsEntryInfo enteryInfo, Context ctx) {
		if (enteryInfo.getMaterial() != null)
			enteryInfo.setBaseUnit(enteryInfo.getMaterial().getBaseUnit());
		BigDecimal baseqty = getBaseUnitQty(enteryInfo.getUnit(), enteryInfo
				.getMaterial(), enteryInfo.getQty(), ctx);
		enteryInfo.setBaseQty(baseqty);
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
		// if (this.tti != null)
		// this.PurInWarehsBillInfo.setBizType(this.tti.getBizType());
	}

	@SuppressWarnings("unchecked")
	private void checkNumberAndAmount(PurInWarehsEntryInfo entryInfo,
			Context ctx, Hashtable lineData, String[] checkList,
			String[] setList, boolean checkBackType)
			throws TaskExternalException {
		for (int i = 0; i < checkList.length; i++) {
			try {
				Object o = null;
				if (lineData.containsKey(checkList[i]))
					o = ((DataToken) lineData.get(checkList[i])).data;
				if ((o != null) && (o.toString().trim().length() > 0)) {
					BigDecimal value = new BigDecimal(o.toString());
					if (value != null) {
						if (value.compareTo(ZERO) < 0) {
							throw new TaskExternalException(checkList[i]
									+ getResource(ctx,
											"import_price_must_positive",
											resources));
						}

						boolean exclude = false;
						for (String str : this.DECIMAL_ENTRY_PROPERTY_EXCLUDENEGATE) {
							if (str != DECIMAL_ENTRY_PROPERTY_ALL[i])
								continue;
							exclude = true;
							break;
						}

						if ((checkTransactionTypeInEnum()) && (!exclude))
							entryInfo.setBigDecimal(setList[i], value.abs()
									.negate());
						else
							entryInfo.setBigDecimal(setList[i], value.abs());
					} else {
						if (checkList[i].equalsIgnoreCase(setList[0]))
							throw new TaskExternalException(checkList[i]
									+ getResource(ctx, "Numbercannotbenull",
											resources));
						if (!checkList[i].equals(ASSISTQTY_PROPERTY[0]))
							entryInfo.setBigDecimal(setList[i], ZERO);
					}
				} else {
					if (checkList[i].equalsIgnoreCase(QTY_PROPERTY[0]))
						throw new TaskExternalException(checkList[i]
								+ getResource(ctx, "Numbercannotbenull",
										resources));
					if (!checkList[i].equals(ASSISTQTY_PROPERTY[0]))
						entryInfo.setBigDecimal(setList[i], ZERO);
				}
			} catch (NumberFormatException nex) {
				nex.printStackTrace();

				throw new TaskExternalException(checkList[i]
						+ getResource(ctx, "FormatMustBeNumber", resources));
			}
		}
	}

	@SuppressWarnings( { "unused", "unchecked" })
	private void checkNumberAndAmount(
			PurInWarehsEntryInfo purinwarehsentryinfo, Context context,
			Hashtable hashtable, HashMap hashmap, boolean flag)
			throws TaskExternalException {
	}

	public static BigDecimal getBaseUnitQty(MeasureUnitInfo mui,
			MaterialInfo mi, BigDecimal qty, Context ctx) {
		BigDecimal coefficient = new BigDecimal("1.00");
		BigDecimal baseQty = new BigDecimal("0.00000");
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
		}
		return null;
	}

	public static MultiMeasureUnitInfo getMulUnit(MeasureUnitInfo mui,
			MaterialInfo mi, Context ctx) throws BaseException {
		MultiMeasureUnitInfo mulUnit = null;
		IMultiMeasureUnit immu = MultiMeasureUnitFactory.getRemoteInstance();
		if ((mui != null) && (mi != null))
			mulUnit = immu.getMultiUnit(mi.getId().toString(), mui.getId()
					.toString());
		return mulUnit;
	}

	@SuppressWarnings("unchecked")
	public boolean isSameBlock(Hashtable firstData, Hashtable currentData) {
		if ((firstData == null) || (currentData == null))
			return false;
		DataToken firstNumber = (DataToken) firstData.get(getMainField());
		DataToken currentNumber = (DataToken) currentData.get(getMainField());
		return (firstNumber != null) && (currentNumber != null)
				&& (firstNumber.data != null) && (!"".equals(firstNumber.data))
				&& (currentNumber.data != null)
				&& (!"".equals(currentNumber.data))
				&& (firstNumber.equals(currentNumber));
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
		try {
			TaskLog log = TaskLogUtil.getLog(taskUuid);
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
}