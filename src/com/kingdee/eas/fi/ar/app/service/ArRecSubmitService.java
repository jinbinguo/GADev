/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) deadcode ansi radix(10) lradix(10) 
// Source File Name:   ArRecSubmitService.java

/**
 * 解决标准财务收款单不允许合计金额为0的问题
 */

package com.kingdee.eas.fi.ar.app.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.ctrl.kdf.data.logging.Logger;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.botp.BOTBillOperStateEnum;
import com.kingdee.eas.base.core.fm.ContextHelperFactory;
import com.kingdee.eas.basedata.assistant.PeriodInfo;
import com.kingdee.eas.basedata.assistant.SystemStatusCtrolUtils;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.SysConstant;
import com.kingdee.eas.fi.ap.OtherBillFactory;
import com.kingdee.eas.fi.ap.OtherBillInfo;
import com.kingdee.eas.fi.arap.app.service.handler.ArApServiceHandler;
import com.kingdee.eas.fi.arap.util.ArApBillReverseHelper;
import com.kingdee.eas.fi.cas.ArApRecPayException;
import com.kingdee.eas.fi.cas.BillStatusEnum;
import com.kingdee.eas.fi.cas.CasForArApException;
import com.kingdee.eas.fi.cas.CasForArApUtil;
import com.kingdee.eas.fi.cas.RecPayException;
import com.kingdee.eas.fi.cas.RecPayHelper;
import com.kingdee.eas.fi.cas.ReceivingBillDataProcess;
import com.kingdee.eas.fi.cas.ReceivingBillEntryInfo;
import com.kingdee.eas.fi.cas.ReceivingBillFactory;
import com.kingdee.eas.fi.cas.ReceivingBillInfo;
import com.kingdee.eas.fi.cas.ReceivingBillTypeCollection;
import com.kingdee.eas.fi.cas.ReceivingBillTypeFactory;
import com.kingdee.eas.fi.cas.ReceivingBillTypeInfo;
import com.kingdee.eas.fi.cas.SourceTypeEnum;
import com.kingdee.eas.fi.cas.app.service.RecSubmitService;
import com.kingdee.eas.fi.cas.app.service.handler.ServiceHandler;
import com.kingdee.eas.framework.SystemEnum;
import com.kingdee.eas.util.ResourceBase;
import com.kingdee.eas.util.client.EASResource;


public class ArRecSubmitService extends RecSubmitService
{

    public ArRecSubmitService()
    {
        apapServiceHandler = new ArApServiceHandler();
    }

    protected ServiceHandler getServiceHandler()
    {
        return apapServiceHandler;
    }

    public void initService(Context ctx, ReceivingBillInfo info)
        throws EASBizException, BOSException
    {
        super.initService(ctx, info);
        String number = info.getString("number");
        if(number != null && number.length() > 0)
            apapServiceHandler.checkNumberDup(ctx, info);
    }

    public void beforeSubmit()
        throws EASBizException, BOSException
    {
        boolean isSubmit = false;
        if(info.getBillStatus() == BillStatusEnum.SUBMIT)
            isSubmit = true;
        super.beforeSubmit();
        if(info.getEntries() == null || info.getEntries().size() == 0)
            apapServiceHandler.throwSubmitExcetion(ctx, "Condition_HeadEntries");
        PeriodInfo period = checkSystemIfStart(ctx, SystemEnum.ACCOUNTSRECEIVABLE, info.getCompany());
        if(period == null)
            throw new RecPayException(RecPayException.AR_SYSTEM_NO_PERIOD);
        arapHelper.verifyAccountView(ctx, info.getPayeeAccount(), info.getCurrency(), info.getCompany());
        if(info.isIsInitializeBill() && info.isAssoGen() && RecPayHelper.SRC_AR_BOSTYPE.equals(getSrcBillBOSType(info)))
            throw new ArApRecPayException(ArApRecPayException.BLANK, new String[] {
                "期初收、付款单不能关联应收单或应付单生成！"
            });
        if(info.getEntries() != null && info.getEntries().get(0) != null && info.getEntries().get(0).getContractNum() != null)
            info.setContractNum(info.getEntries().get(0).getContractNum());
        if(info.getRecBillType() != null)
        {
            int i = 0;
            for(int size = info.getEntries().size(); i < size; i++)
                if(info.getEntries().get(i).getRecBillType() == null || info.isIsInitializeBill())
                    info.getEntries().get(i).setRecBillType(info.getRecBillType());

        }
        if(!info.isIsInitializeBill() && info.isAssoGenByArBill() && !info.isIsTransBill())
        {
            if(info.getBillStatus() == BillStatusEnum.SUBMIT && info != null && isSubmit)
            {
                SelectorItemCollection selectorColl = new SelectorItemCollection();
                selectorColl.add(new SelectorItemInfo("*"));
                selectorColl.add(new SelectorItemInfo("entries.*"));
                selectorColl.add(new SelectorItemInfo("company.baseCurrency.id"));
                ReceivingBillInfo recInfo = ReceivingBillFactory.getLocalInstance(ctx).getReceivingBillInfo(new ObjectUuidPK(info.getId()), selectorColl);
                ArApBillReverseHelper.reverseLockAmt(ctx, recInfo, BOTBillOperStateEnum.DELETE);
            }
            ArApBillReverseHelper.reverseLockAmt(ctx, info, BOTBillOperStateEnum.ADDNEW);
        }
        serviceHandler.checkIsInQueue(ctx, info);
        if(SourceTypeEnum.AR.equals(info.getSourceType()))
        {
            if(isAddNew)
            {
                if(info.getSourceSysType() == null)
                    info.setSourceSysType(SourceTypeEnum.AR);
                if(info.isIsImport() && info.isIsInitializeBill())
                {
                    for(int i = 0; i< info.getEntries().size(); i++)
                        info.getEntries().get(i).setRecBillType(info.getRecBillType());

                }
            }
            if(info.getBizDate() == null)
                info.setBizDate(new Date());
            if(info.getCU() == null && info.getCompany().getCU() != null)
                info.setCU(info.getCompany().getCU());
            apapServiceHandler.checkNumber(ctx, info);
            checkRecInfo(ctx, info);
            if(!isAddNew && info.getBillStatus().getValue() >= 12)
                throw new CasForArApException(CasForArApException.BILLISAUDITED);
            if(info.isIsTransBill())
            {
                IObjectPK sourceBillPK = new ObjectUuidPK(info.getSourceBillId());
                if(!info.isIsTransOtherBill())
                    apapServiceHandler.reverseTransBill(ctx, sourceBillPK, info, true);
                if(info.isIsTransOtherBill())
                    apapServiceHandler.reverseTransOtherBill(ctx, info, true);
                com.kingdee.eas.base.permission.UserInfo userInfo = ContextHelperFactory.getLocalInstance(ctx).getCurrentUser();
                info.setBillStatus(BillStatusEnum.RECED);
                info.setAuditDate(new Timestamp(System.currentTimeMillis()));
                info.setRecDate(new Date());
                info.setAuditor(userInfo);
                info.setCashier(userInfo);
            }
            ReceivingBillDataProcess.setAmountProp(ctx, info);
         //删除收款单合计金额为为的提示
        //    if(ZERO.compareTo(info.getAmount()) == 0)
        //        throw new ArApRecPayException(ArApRecPayException.REC_AMTNOTZERO);
            if(info.getRecBillType() != null)
            {
                Set recTypeIDSet = new HashSet();
                recTypeIDSet.add(String.valueOf(info.getRecBillType().getId()));
                int i = 0;
                for(int size = info.getEntries().size(); i < size; i++)
                    recTypeIDSet.add(String.valueOf(info.getEntries().get(i).getRecBillType().getId()));

                EntityViewInfo view = new EntityViewInfo();
                view.getSelector().add(new SelectorItemInfo("number"));
                view.getSelector().add(new SelectorItemInfo("isPreSet"));
                view.getSelector().add(new SelectorItemInfo("preSetBillType.id"));
                FilterInfo filter = new FilterInfo();
                filter.getFilterItems().add(new FilterItemInfo("id", recTypeIDSet, CompareType.INCLUDE));
                view.setFilter(filter);
                ReceivingBillTypeCollection recTypeColl = ReceivingBillTypeFactory.getLocalInstance(ctx).getReceivingBillTypeCollection(view);
                Integer recType = null;
                recType = transRecBillType(ctx, recTypeColl, info.getRecBillType());
                boolean blueFlag = arapHelper.checkIsDefaultBlueBillType(recType);
                boolean redFlag = arapHelper.checkIsDefaultRedBillType(recType);
                int sign = info.getAmount().signum();
                if(blueFlag && sign == -1 || redFlag && sign == 1)
                    throw new ArApRecPayException(ArApRecPayException.REC_TYPEAMTNOTMATCH);
                Integer recBillType = null;
               // int i = 0;
                for(int j = 0;j < info.getEntries().size(); j++)
                {
                    ReceivingBillEntryInfo entryInfo = info.getEntries().get(j);
                    if(entryInfo.getRecBillType() == null)
                        continue;
                    recBillType = transRecBillType(ctx, recTypeColl, entryInfo.getRecBillType());
                    if(arapHelper.checkIsDefaultBlueBillType(recBillType) && entryInfo.getAmount().signum() == -1 || arapHelper.checkIsDefaultRedBillType(recBillType) && entryInfo.getAmount().signum() == 1)
                        throw new ArApRecPayException(ArApRecPayException.REC_ENTRYTYPEAMTNOTMATCH);
                }

            }
        }
    }

    public void afterSubmit(IObjectPK pk)
        throws EASBizException, BOSException
    {
        super.afterSubmit(pk);
        if(info.isIsTransOtherBill() && SourceTypeEnum.AR.equals(info.getSourceType()))
            apapServiceHandler.writeBackOrderBill(ctx, info.getId().toString(), true, false);
    }

    private BOSObjectType getSrcBillBOSType(ReceivingBillInfo info)
        throws RecPayException
    {
        if(info == null || info.getEntries() == null || info.getEntries().size() < 1)
            throw new RecPayException(RecPayException.BILLENTRY_ISNULL);
        String srcBillID = null;
        int i = 0;
        int size = info.getEntries().size();
        if(i < size)
        {
            if(info.getEntries().get(i).getSourceBillId() == null)
                return null;
            srcBillID = info.getEntries().get(i).getSourceBillId();
        }
        BOSObjectType srcBOSType = BOSUuid.getBOSObjectType(srcBillID, true);
        return srcBOSType;
    }

    private PeriodInfo checkSystemIfStart(Context ctx, SystemEnum sourceType, CompanyOrgUnitInfo companyInfo)
    {
        PeriodInfo period = null;
        try
        {
            period = SystemStatusCtrolUtils.getStartPeriod(ctx, sourceType, companyInfo);
        }
        catch(EASBizException e)
        {
            Logger.error(e);
        }
        catch(BOSException e)
        {
            Logger.error(e);
        }
        return period;
    }

    private void checkRecInfo(Context ctx, ReceivingBillInfo info)
        throws EASBizException, BOSException
    {
        String resourcePath = "com.kingdee.eas.fi.ar.ARRecPayResource";
        String message = ResourceBase.getString(resourcePath, "recBillTypeIsNull", ctx.getLocale());
        if(info.getRecBillType() == null)
            throw new EASBizException(EASBizException.CHECKBLANK, new String[] {
                message
            });
        if(info.getSourceType() != null && !info.getSourceType().equals(info.getRecBillType().getSourceSysType()))
            throw new RecPayException(RecPayException.RECBILLTYPENOTMATCHSYSTYPE);
        PeriodInfo currentPeriod = CasForArApUtil.getCurrentPeriod(ctx, info.getCompany());
        if(currentPeriod != null && info.getBizDate() != null && currentPeriod.getBeginDate().compareTo(info.getBizDate()) > 0 && !info.isIsInitializeBill())
            throw new RecPayException(RecPayException.BILLDATEMUSTGREATTHANBEGINDATE);
        if(info.isIsTransBill())
        {
            StringBuffer sb = new StringBuffer("");
            if(info.isIsTransOtherBill())
            {
                OtherBillInfo sourceBillInfo = null;
                if(info.getSourceBillId() == null)
                    sb.append(ResourceBase.getString("com.kingdee.eas.fi.ap.APResources", "srcBillIDIsNull", ctx.getLocale())).append(";\n");
                else
                if((new OtherBillInfo()).getBOSType().equals(BOSUuid.read(info.getSourceBillId()).getType()))
                    sourceBillInfo = OtherBillFactory.getLocalInstance(ctx).getOtherBillInfo((new StringBuilder()).append("SELECT adminOrgUnit, person WHERE id = '").append(info.getSourceBillId()).append("'").toString());
                if(sourceBillInfo != null && sourceBillInfo.getAdminOrgUnit() != null && info.getAdminOrgUnitId_SourceBill() == null)
                    sb.append(ResourceBase.getString("com.kingdee.eas.fi.ap.APResources", "srcOUIDIsNull", ctx.getLocale())).append(";\n");
                if(sourceBillInfo != null && sourceBillInfo.getPerson() != null && info.getPersonID_SourceBill() == null)
                    sb.append(ResourceBase.getString("com.kingdee.eas.fi.ap.APResources", "srcPersonIDIsNull", ctx.getLocale())).append(";\n");
                if(info.getAsstActTypeID_SourceBill() == null)
                    sb.append(ResourceBase.getString("com.kingdee.eas.fi.ap.APResources", "srcAsstActTypeIDIsNull", ctx.getLocale())).append(";\n");
                if(info.getAsstActID_SourceBill() == null)
                    sb.append(ResourceBase.getString("com.kingdee.eas.fi.ap.APResources", "srcAcctCussentIDIsNull", ctx.getLocale())).append(";\n");
                if(info.getBillDate_SourceBill() == null)
                    sb.append(ResourceBase.getString("com.kingdee.eas.fi.ap.APResources", "srcBillDateIsNull", ctx.getLocale())).append(";\n");
                if(sb.length() > 0)
                    throw new CasForArApException(CasForArApException.TRANSFAILED, new Object[] {
                        sb.toString()
                    });
            } else
            {
                ReceivingBillInfo sourceBillInfo = null;
                if(info.getSourceBillId() == null)
                    sb.append(EASResource.getString("com.kingdee.eas.fi.cas.CasResources", "srcBillIDIsNull")).append(";\n");
                else
                    sourceBillInfo = ReceivingBillFactory.getLocalInstance(ctx).getReceivingBillInfo((new StringBuilder()).append("SELECT adminOrgUnit, person WHERE id = '").append(info.getSourceBillId()).append("'").toString());
                if(sourceBillInfo != null && sourceBillInfo.getAdminOrgUnit() != null && info.getAdminOrgUnitId_SourceBill() == null)
                    sb.append(EASResource.getString("com.kingdee.eas.fi.cas.CasResources", "srcOUIDIsNull")).append(";\n");
                if(sourceBillInfo != null && sourceBillInfo.getPerson() != null && info.getPersonID_SourceBill() == null)
                    sb.append(EASResource.getString("com.kingdee.eas.fi.cas.CasResources", "srcPersonIDIsNull")).append(";\n");
                if(info.getAsstActTypeID_SourceBill() == null)
                    sb.append(EASResource.getString("com.kingdee.eas.fi.cas.CasResources", "srcAsstActTypeIDIsNull")).append(";\n");
                if(sourceBillInfo != null && sourceBillInfo.getPayerID() != null && info.getAsstActID_SourceBill() == null)
                    sb.append(EASResource.getString("com.kingdee.eas.fi.cas.CasResources", "srcAcctCussentIDIsNull")).append(";\n");
                if(info.getBillDate_SourceBill() == null)
                    sb.append(EASResource.getString("com.kingdee.eas.fi.cas.CasResources", "srcBillDateIsNull")).append(";\n");
                if(sb.length() > 0)
                    throw new CasForArApException(CasForArApException.TRANSFAILED, new Object[] {
                        sb.toString()
                    });
            }
        }
        BigDecimal verifyAmont = null;
        BigDecimal recAmont = null;
        int i = 0;
        for(int size = info.getEntries().size(); i < size; i++)
        {
            verifyAmont = info.getEntries().get(i).getAmountVc() != null ? info.getEntries().get(i).getAmountVc() : SysConstant.BIGZERO;
            recAmont = info.getEntries().get(i).getAmount() != null ? info.getEntries().get(i).getAmount() : SysConstant.BIGZERO;
            if(SysConstant.BIGZERO.compareTo(verifyAmont) != 0 && recAmont.abs().compareTo(verifyAmont.abs()) <= 0)
                throw new CasForArApException(CasForArApException.VERIFYAMT_NO_MORETHAN_RECPAYAMOUNT);
        }

    }

    private Integer transRecBillType(Context ctx, ReceivingBillTypeCollection recTypeColl, ReceivingBillTypeInfo recTypeInfo)
        throws EASBizException, BOSException
    {
        Integer recType = null;
        recTypeInfo = recTypeColl.get(recTypeInfo.getId());
        if(!recTypeInfo.isIsPreSet() && recTypeInfo.getPreSetBillType() != null)
        {
            String typeid = recTypeInfo.getPreSetBillType().getId().toString();
            if("DAWSqQEREADgAAGVwKgSfCqo2zU=".equals(typeid))
                recType = new Integer(100);
            else
            if("DAWSqQEREADgAAILwKgSfCqo2zU=".equals(typeid))
                recType = new Integer(101);
            else
            if("DAWSqQEREADgAAInwKgSfCqo2zU=".equals(typeid))
                recType = new Integer(102);
            else
            if("DAWSqQEREADgAAIswKgSfCqo2zU=".equals(typeid))
                recType = new Integer(103);
            else
            if("DAWSqQEREADgAAIOwKgSfCqo2zU=".equals(typeid))
                recType = new Integer(104);
            else
            if("DAWSqQEREADgAAIvwKgSfCqo2zU=".equals(typeid))
                recType = new Integer(105);
        } else
        {
            recType = new Integer(recTypeInfo.getNumber());
        }
        return recType;
    }

    private ArApServiceHandler apapServiceHandler;
    private static final BigDecimal ZERO = new BigDecimal("0.00");

}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\GA\workspace\Dev\lib\patch\sp-fi_arap-server.jar
	Total time: 30 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/