package com.kingdee.eas.fi.ar.app;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.fi.ar.ArApBillBaseInfo;
import com.kingdee.eas.fi.ar.OtherBillBizException;
import com.kingdee.eas.fi.ar.OtherBillInfo;
import com.kingdee.eas.fi.ar.OtherBillTypeEnum;
import com.kingdee.eas.fi.ar.OtherBillentryInfo;
import com.kingdee.eas.util.ResourceBase;

public class OtherBillControllerBeanEx extends OtherBillControllerBean {

	protected void checkValid(Context ctx, OtherBillInfo otherBillInfo)
			throws EASBizException, BOSException {

		if (otherBillInfo == null)
			return;

		if (otherBillInfo.isIsReverseBill())
			return;

		if (otherBillInfo.getCompany() == null)
			throw new OtherBillBizException(OtherBillBizException.COMPANY_NOTSET);

		if (otherBillInfo.getBillDate() == null)
			throw new OtherBillBizException(OtherBillBizException.BILLDATE_NOTSET);

		if (otherBillInfo.getAsstActType() == null)
			throw new OtherBillBizException(OtherBillBizException.ASSTACTTYPE_NOTSET);

		if (otherBillInfo.getAsstActID() == null)
			throw new OtherBillBizException(OtherBillBizException.ACCTCUSSENT_NOTSET);

		if (!isExistAsstActByCompany(ctx, otherBillInfo.getCompany(),
				otherBillInfo.getAsstActType(), otherBillInfo.getAsstActID()))

			throw new OtherBillBizException(OtherBillBizException.ACCTCUSSENT_NOTBELONGCOMORG);

		if (otherBillInfo.getSourceBillType() == null)
			throw new OtherBillBizException(OtherBillBizException.BILLTYPE_NOTSET);

		if (otherBillInfo.getBillType() == null)
			throw new OtherBillBizException(OtherBillBizException.BILLTYPE_NOTSET);

		if (otherBillInfo.getCurrency() == null)
			throw new OtherBillBizException(OtherBillBizException.CURRENCY_NOTSET);

		if (otherBillInfo.getExchangeRate() == null)
			throw new OtherBillBizException(OtherBillBizException.EXCHANGERATE_NOTSET);

		if (otherBillInfo.getEntry() == null || otherBillInfo.getEntry().size() == 0)
			throw new OtherBillBizException(OtherBillBizException.ENTRY_NOTEXIST);

		for (int j = 0; j < otherBillInfo.getEntry().size(); j++) {
			OtherBillentryInfo entry = otherBillInfo.getEntry().get(j);

			if (otherBillInfo.isIsInitializeBill()
					&& !entry.isIsPresent()
					&& entry.getRecievePayAmount() != null
					&& entry.getVerifyAmount() != null
					&& entry.getRecievePayAmount().abs().compareTo(entry.getVerifyAmount().abs()) <= 0)

				throw new OtherBillBizException(OtherBillBizException.VERIFYAMT_NO_MORETHAN_RECPAYAMOUNT);

			if (!entry.isIsPresent()) {

				if (entry.getAmount() != null&& entry.getRecievePayAmount().compareTo(entry.getAmount().add(entry.getTaxAmount())) != 0)
					throw new OtherBillBizException(OtherBillBizException.ENTRYAMOUNTNOTEQUALTAX);

				if (entry.getVerifyAmount() != null && entry.getUnVerifyAmount() != null && entry.getRecievePayAmount().compareTo(entry.getVerifyAmount().add(entry.getUnVerifyAmount())) != 0)
					throw new OtherBillBizException(OtherBillBizException.ENTRYAMOUNTNOTEQUALVERIFY);

				if (entry.getLockVerifyAmt() != null
						&& entry.getLockUnVerifyAmt() != null
						&& entry.getRecievePayAmount().compareTo(entry.getLockVerifyAmt().add(entry.getLockUnVerifyAmt())) != 0)
					throw new OtherBillBizException(OtherBillBizException.ENTRYAMOUNTNOTEQUALLOCKVERIFY);
			}

			if (!OtherBillTypeEnum.DebitAdjust.equals(otherBillInfo.getBillType())
					&& !otherBillInfo.isIsAllowanceBill()
					&& (entry.getQuantity() == null || entry.getQuantity() != null && ZERO.compareTo(entry.getQuantity()) == 0))
				throw new OtherBillBizException(OtherBillBizException.QTY_NOTSET);
		}

		boolean isAllPresent = true;
		int i = 0;
		do {
			if (i >= otherBillInfo.getEntry().size())
				break;
			OtherBillentryInfo entryInfo = (OtherBillentryInfo) otherBillInfo.getEntries().getObject(i);
			if (!entryInfo.isIsPresent()) {
				isAllPresent = false;
				break;
			}
			i++;
		} while (true);
		/*if (!isAllPresent && otherBillInfo.getRecievePlan() != null && otherBillInfo.getRecievePlan().size() > 0) {
			for (int j = 0; j < otherBillInfo.getRecievePlan().size(); j++)
				if (otherBillInfo.getRecievePlan().get(j).getRecievePayAmount() == null || otherBillInfo.getRecievePlan().get(j).getRecievePayAmount() != null
						&& ZERO.compareTo(otherBillInfo.getRecievePlan().get(j).getRecievePayAmount()) == 0)
					throw new OtherBillBizException(OtherBillBizException.RECPLAN_NOTNULLORZERO);
		}*/

		if (OtherBillTypeEnum.DebitAdjust.equals(otherBillInfo.getBillType())) {
			boolean isMaterial = false;
			boolean isExpenseItem = false;
			int rowCount = otherBillInfo.getEntry().size();
			for (i = 0; i < rowCount; i++)
				if (otherBillInfo.getEntry().get(i).getMaterial() != null)
					isMaterial = true;

				else
					isExpenseItem = true;

			if (isMaterial && isExpenseItem)
				throw new OtherBillBizException(OtherBillBizException.DEBITADJUSTNOTBOTHMTREPI);
		}

		if (!otherBillInfo.isIsCoopBuild()
				&& !otherBillInfo.isIsSplitBill()
				&& !otherBillInfo.isIsBizBill()
				&& (otherBillInfo.getRecievePlan() == null || otherBillInfo.getRecievePlan().size() == 0))

			throw new OtherBillBizException(OtherBillBizException.ENTRY_NOTEXIST);

		if (otherBillInfo.isIsTransBill()) {
			StringBuffer sb = new StringBuffer("");
			OtherBillInfo sourceBillInfo = null;
			if (otherBillInfo.getSourceBillId() == null) {
				sb.append(ResourceBase.getString("com.kingdee.eas.fi.ar.ARResources","srcBillIDIsNull", ctx.getLocale())).append(";\n");
			} else {
				SelectorItemCollection selectors = new SelectorItemCollection();
				selectors.add(new SelectorItemInfo("adminOrgUnit.id"));
				sourceBillInfo = getOtherBillInfo(ctx, new ObjectUuidPK(otherBillInfo.getSourceBillId()), selectors);
			}
			if (sourceBillInfo != null
					&& sourceBillInfo.getAdminOrgUnit() != null
					&& otherBillInfo.getAdminOrgUnitId_SourceBill() == null)
				sb.append(ResourceBase.getString("com.kingdee.eas.fi.ar.ARResources","srcOUIDIsNull", ctx.getLocale())).append(";\n");

			if (sourceBillInfo != null && sourceBillInfo.getPerson() != null
					&& otherBillInfo.getPersonID_SourceBill() == null)
				sb.append(ResourceBase.getString("com.kingdee.eas.fi.ar.ARResources","srcPersonIDIsNull", ctx.getLocale())).append(";\n");

			if (otherBillInfo.getAsstActTypeID_SourceBill() == null)
				sb.append(ResourceBase.getString("com.kingdee.eas.fi.ar.ARResources","srcAsstActTypeIDIsNull", ctx.getLocale())).append(";\n");

			if (otherBillInfo.getAsstActID_SourceBill() == null)
				sb.append(ResourceBase.getString("com.kingdee.eas.fi.ar.ARResources","srcAcctCussentIDIsNull", ctx.getLocale())).append(";\n");

			if (otherBillInfo.getBillDate_SourceBill() == null)
				sb.append(ResourceBase.getString("com.kingdee.eas.fi.ar.ARResources","srcBillDateIsNull", ctx.getLocale())).append(";\n");

			if (sb.length() > 0)
				throw new OtherBillBizException(OtherBillBizException.TRANSFAILED, new Object[] {sb.toString() });
		}
	}
	
	@Override
	protected void check4submit(Context ctx, ArApBillBaseInfo info)
			throws EASBizException, BOSException {
		if(info.getId() == null) return;
		if(!_exists(ctx, new ObjectUuidPK(info.getId())))  return;
		boolean isNotAudited = _exists(ctx, (new StringBuilder()).append("where (billStatus=1 or billStatus=2) and id = '")
				.append(info.getId()).append("'").toString());
		if(!isNotAudited)
		       throw new OtherBillBizException(OtherBillBizException.BILLISAUDITED);
	    else return;
		
		
	}
}
