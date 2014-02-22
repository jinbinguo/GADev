package com.kingdee.eas.auto4s.arp.aar;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractReceivingBillInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractReceivingBillInfo()
    {
        this("id");
    }
    protected AbstractReceivingBillInfo(String pkField)
    {
        super(pkField);
        put("Type", new com.kingdee.eas.auto4s.arp.aar.ReceivingBillTypeCollection());
        put("entrys", new com.kingdee.eas.auto4s.arp.aar.ReceivingBillEntryCollection());
        put("Content", new com.kingdee.eas.auto4s.arp.aar.ReceivingBillContentCollection());
    }
    /**
     * Object: ǰ̨�տ 's ��¼ property 
     */
    public com.kingdee.eas.auto4s.arp.aar.ReceivingBillEntryCollection getEntrys()
    {
        return (com.kingdee.eas.auto4s.arp.aar.ReceivingBillEntryCollection)get("entrys");
    }
    /**
     * Object:ǰ̨�տ's �Ƿ�����ƾ֤property 
     */
    public boolean isFivouchered()
    {
        return getBoolean("Fivouchered");
    }
    public void setFivouchered(boolean item)
    {
        setBoolean("Fivouchered", item);
    }
    /**
     * Object: ǰ̨�տ 's �տ����� property 
     */
    public com.kingdee.eas.auto4s.arp.aar.ReceivingBillTypeCollection getType()
    {
        return (com.kingdee.eas.auto4s.arp.aar.ReceivingBillTypeCollection)get("Type");
    }
    /**
     * Object:ǰ̨�տ's �տ�����property 
     */
    public java.util.Date getDate()
    {
        return getDate("Date");
    }
    public void setDate(java.util.Date item)
    {
        setDate("Date", item);
    }
    /**
     * Object:ǰ̨�տ's ״̬property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.BillStatusEnum getStatus()
    {
        return com.kingdee.eas.auto4s.bdm.pbd.BillStatusEnum.getEnum(getString("Status"));
    }
    public void setStatus(com.kingdee.eas.auto4s.bdm.pbd.BillStatusEnum item)
    {
		if (item != null) {
        setString("Status", item.getValue());
		}
    }
    /**
     * Object: ǰ̨�տ 's �տ��� property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getPerson()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("Person");
    }
    public void setPerson(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("Person", item);
    }
    /**
     * Object: ǰ̨�տ 's �ұ� property 
     */
    public com.kingdee.eas.basedata.assistant.CurrencyInfo getCurrency()
    {
        return (com.kingdee.eas.basedata.assistant.CurrencyInfo)get("Currency");
    }
    public void setCurrency(com.kingdee.eas.basedata.assistant.CurrencyInfo item)
    {
        put("Currency", item);
    }
    /**
     * Object:ǰ̨�տ's ����property 
     */
    public java.math.BigDecimal getExchangeRate()
    {
        return getBigDecimal("ExchangeRate");
    }
    public void setExchangeRate(java.math.BigDecimal item)
    {
        setBigDecimal("ExchangeRate", item);
    }
    /**
     * Object: ǰ̨�տ 's ��˾ property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("OrgUnit");
    }
    public void setOrgUnit(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("OrgUnit", item);
    }
    /**
     * Object: ǰ̨�տ 's Ʒ�� property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.BrandInfo getBrand()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.BrandInfo)get("Brand");
    }
    public void setBrand(com.kingdee.eas.auto4s.bdm.pbd.BrandInfo item)
    {
        put("Brand", item);
    }
    /**
     * Object:ǰ̨�տ's �տעproperty 
     */
    public String getRemark()
    {
        return getString("Remark");
    }
    public void setRemark(String item)
    {
        setString("Remark", item);
    }
    /**
     * Object:ǰ̨�տ's ���ʱ��property 
     */
    public java.util.Date getAuditTime()
    {
        return getDate("AuditTime");
    }
    public void setAuditTime(java.util.Date item)
    {
        setDate("AuditTime", item);
    }
    /**
     * Object:ǰ̨�տ's �տ����property 
     */
    public String getReceiveObjectID()
    {
        return getString("ReceiveObjectID");
    }
    public void setReceiveObjectID(String item)
    {
        setString("ReceiveObjectID", item);
    }
    /**
     * Object:ǰ̨�տ's �տ��������property 
     */
    public com.kingdee.eas.auto4s.bdm.arp.ARObjTypeEnum getReceiveObjectType()
    {
        return com.kingdee.eas.auto4s.bdm.arp.ARObjTypeEnum.getEnum(getString("ReceiveObjectType"));
    }
    public void setReceiveObjectType(com.kingdee.eas.auto4s.bdm.arp.ARObjTypeEnum item)
    {
		if (item != null) {
        setString("ReceiveObjectType", item.getValue());
		}
    }
    /**
     * Object:ǰ̨�տ's �տ�������property 
     */
    public String getReceiveObjectNumber()
    {
        return getString("ReceiveObjectNumber");
    }
    public void setReceiveObjectNumber(String item)
    {
        setString("ReceiveObjectNumber", item);
    }
    /**
     * Object:ǰ̨�տ's �տ��������property 
     */
    public String getReceiveObjectName()
    {
        return getString("ReceiveObjectName");
    }
    public void setReceiveObjectName(String item)
    {
        setString("ReceiveObjectName", item);
    }
    /**
     * Object:ǰ̨�տ's ʵ�ʽɿ���property 
     */
    public String getActualPayer()
    {
        return getString("ActualPayer");
    }
    public void setActualPayer(String item)
    {
        setString("ActualPayer", item);
    }
    /**
     * Object: ǰ̨�տ 's ҵ������ property 
     */
    public com.kingdee.eas.fm.fs.SettBizTypeInfo getBizType()
    {
        return (com.kingdee.eas.fm.fs.SettBizTypeInfo)get("BizType");
    }
    public void setBizType(com.kingdee.eas.fm.fs.SettBizTypeInfo item)
    {
        put("BizType", item);
    }
    /**
     * Object: ǰ̨�տ 's �տ����� property 
     */
    public com.kingdee.eas.auto4s.arp.aar.ReceivingBillContentCollection getContent()
    {
        return (com.kingdee.eas.auto4s.arp.aar.ReceivingBillContentCollection)get("Content");
    }
    /**
     * Object:ǰ̨�տ's ���˱�ʶproperty 
     */
    public boolean isIsAccount()
    {
        return getBoolean("isAccount");
    }
    public void setIsAccount(boolean item)
    {
        setBoolean("isAccount", item);
    }
    /**
     * Object: ǰ̨�տ 's ƾ֤�� property 
     */
    public com.kingdee.eas.fi.gl.VoucherInfo getVoucher()
    {
        return (com.kingdee.eas.fi.gl.VoucherInfo)get("voucher");
    }
    public void setVoucher(com.kingdee.eas.fi.gl.VoucherInfo item)
    {
        put("voucher", item);
    }
    /**
     * Object:ǰ̨�տ's �տ�ϼ�property 
     */
    public java.math.BigDecimal getReceivingAmt()
    {
        return getBigDecimal("receivingAmt");
    }
    public void setReceivingAmt(java.math.BigDecimal item)
    {
        setBigDecimal("receivingAmt", item);
    }
    /**
     * Object:ǰ̨�տ's ��ֵ�����property 
     */
    public java.math.BigDecimal getSavingCardAmt()
    {
        return getBigDecimal("savingCardAmt");
    }
    public void setSavingCardAmt(java.math.BigDecimal item)
    {
        setBigDecimal("savingCardAmt", item);
    }
    /**
     * Object:ǰ̨�տ's ����ȯ���property 
     */
    public java.math.BigDecimal getCashCouponAmt()
    {
        return getBigDecimal("cashCouponAmt");
    }
    public void setCashCouponAmt(java.math.BigDecimal item)
    {
        setBigDecimal("cashCouponAmt", item);
    }
    /**
     * Object:ǰ̨�տ's ���ֵ��ֽ��property 
     */
    public java.math.BigDecimal getPointToCashAmt()
    {
        return getBigDecimal("pointToCashAmt");
    }
    public void setPointToCashAmt(java.math.BigDecimal item)
    {
        setBigDecimal("pointToCashAmt", item);
    }
    /**
     * Object:ǰ̨�տ's �ϼ�property 
     */
    public java.math.BigDecimal getAmount()
    {
        return getBigDecimal("amount");
    }
    public void setAmount(java.math.BigDecimal item)
    {
        setBigDecimal("amount", item);
    }
    /**
     * Object:ǰ̨�տ's �Ƿ��Ͷ���property 
     */
    public boolean isIsSendSms()
    {
        return getBoolean("IsSendSms");
    }
    public void setIsSendSms(boolean item)
    {
        setBoolean("IsSendSms", item);
    }
    /**
     * Object: ǰ̨�տ 's ��Ա�� property 
     */
    public com.kingdee.eas.auto4s.vip.mb.CardInfo getCard()
    {
        return (com.kingdee.eas.auto4s.vip.mb.CardInfo)get("card");
    }
    public void setCard(com.kingdee.eas.auto4s.vip.mb.CardInfo item)
    {
        put("card", item);
    }
    /**
     * Object:ǰ̨�տ's ����Ա����property 
     */
    public String getReadSN()
    {
        return getString("readSN");
    }
    public void setReadSN(String item)
    {
        setString("readSN", item);
    }
    /**
     * Object: ǰ̨�տ 's ���ʽ property 
     */
    public com.kingdee.eas.basedata.assistant.PaymentTypeInfo getPaymentType()
    {
        return (com.kingdee.eas.basedata.assistant.PaymentTypeInfo)get("paymentType");
    }
    public void setPaymentType(com.kingdee.eas.basedata.assistant.PaymentTypeInfo item)
    {
        put("paymentType", item);
    }
    /**
     * Object:ǰ̨�տ's ��Ԥ�ս��property 
     */
    public java.math.BigDecimal getWOPRAmount()
    {
        return getBigDecimal("WOPRAmount");
    }
    public void setWOPRAmount(java.math.BigDecimal item)
    {
        setBigDecimal("WOPRAmount", item);
    }
    /**
     * Object: ǰ̨�տ 's ���� property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getDepartment()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("Department");
    }
    public void setDepartment(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("Department", item);
    }
    /**
     * Object:ǰ̨�տ's Դ����¼Idsproperty 
     */
    public String getSrcEntryIds()
    {
        return getString("srcEntryIds");
    }
    public void setSrcEntryIds(String item)
    {
        setString("srcEntryIds", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("E97C32F4");
    }
}