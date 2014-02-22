package com.kingdee.eas.auto4s.arp.aar;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractReceivingBillContentInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractReceivingBillContentInfo()
    {
        this("id");
    }
    protected AbstractReceivingBillContentInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: �տ����� 's null property 
     */
    public com.kingdee.eas.auto4s.arp.aar.ReceivingBillInfo getParent()
    {
        return (com.kingdee.eas.auto4s.arp.aar.ReceivingBillInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.auto4s.arp.aar.ReceivingBillInfo item)
    {
        put("parent", item);
    }
    /**
     * Object: �տ����� 's �տ����� property 
     */
    public com.kingdee.eas.fi.cas.ReceivingBillTypeInfo getRecBillType()
    {
        return (com.kingdee.eas.fi.cas.ReceivingBillTypeInfo)get("RecBillType");
    }
    public void setRecBillType(com.kingdee.eas.fi.cas.ReceivingBillTypeInfo item)
    {
        put("RecBillType", item);
    }
    /**
     * Object:�տ�����'s ����Ӧ�ս��property 
     */
    public java.math.BigDecimal getAmount()
    {
        return getBigDecimal("Amount");
    }
    public void setAmount(java.math.BigDecimal item)
    {
        setBigDecimal("Amount", item);
    }
    /**
     * Object:�տ�����'s �ֽ��ۿ�property 
     */
    public java.math.BigDecimal getDiscountAmount()
    {
        return getBigDecimal("DiscountAmount");
    }
    public void setDiscountAmount(java.math.BigDecimal item)
    {
        setBigDecimal("DiscountAmount", item);
    }
    /**
     * Object:�տ�����'s ����ʵ�ս��property 
     */
    public java.math.BigDecimal getActualAmount()
    {
        return getBigDecimal("ActualAmount");
    }
    public void setActualAmount(java.math.BigDecimal item)
    {
        setBigDecimal("ActualAmount", item);
    }
    /**
     * Object:�տ�����'s �б�עproperty 
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
     * Object:�տ�����'s Դ������property 
     */
    public String getSourceBillINumber()
    {
        return getString("SourceBillINumber");
    }
    public void setSourceBillINumber(String item)
    {
        setString("SourceBillINumber", item);
    }
    /**
     * Object:�տ�����'s Դ���к�property 
     */
    public int getSourceBillEntrySeq()
    {
        return getInt("SourceBillEntrySeq");
    }
    public void setSourceBillEntrySeq(int item)
    {
        setInt("SourceBillEntrySeq", item);
    }
    /**
     * Object: �տ����� 's ���̺� property 
     */
    public com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo getVehicle()
    {
        return (com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo)get("Vehicle");
    }
    public void setVehicle(com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo item)
    {
        put("Vehicle", item);
    }
    /**
     * Object: �տ����� 's �Է���Ŀ property 
     */
    public com.kingdee.eas.basedata.master.account.AccountViewInfo getOppAccount()
    {
        return (com.kingdee.eas.basedata.master.account.AccountViewInfo)get("OppAccount");
    }
    public void setOppAccount(com.kingdee.eas.basedata.master.account.AccountViewInfo item)
    {
        put("OppAccount", item);
    }
    /**
     * Object:�տ�����'s Դ����¼IDproperty 
     */
    public String getSourceBillIEntryID()
    {
        return getString("SourceBillIEntryID");
    }
    public void setSourceBillIEntryID(String item)
    {
        setString("SourceBillIEntryID", item);
    }
    /**
     * Object:�տ�����'s Դ��IDproperty 
     */
    public String getSourceBillID()
    {
        return getString("SourceBillID");
    }
    public void setSourceBillID(String item)
    {
        setString("SourceBillID", item);
    }
    /**
     * Object: �տ����� 's Դ������ property 
     */
    public com.kingdee.eas.basedata.scm.common.BillTypeInfo getSourceBillType()
    {
        return (com.kingdee.eas.basedata.scm.common.BillTypeInfo)get("SourceBillType");
    }
    public void setSourceBillType(com.kingdee.eas.basedata.scm.common.BillTypeInfo item)
    {
        put("SourceBillType", item);
    }
    /**
     * Object:�տ�����'s ���ҵ���property 
     */
    public boolean isIsMorAccount()
    {
        return getBoolean("IsMorAccount");
    }
    public void setIsMorAccount(boolean item)
    {
        setBoolean("IsMorAccount", item);
    }
    /**
     * Object:�տ�����'s ����Ӧ�ս��property 
     */
    public java.math.BigDecimal getTotalAmount()
    {
        return getBigDecimal("TotalAmount");
    }
    public void setTotalAmount(java.math.BigDecimal item)
    {
        setBigDecimal("TotalAmount", item);
    }
    /**
     * Object:�տ�����'s ����������property 
     */
    public java.math.BigDecimal getTotalActualAmount()
    {
        return getBigDecimal("TotalActualAmount");
    }
    public void setTotalActualAmount(java.math.BigDecimal item)
    {
        setBigDecimal("TotalActualAmount", item);
    }
    /**
     * Object:�տ�����'s ��ֵ�����property 
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
     * Object:�տ�����'s ʹ�ó�ֵ���property 
     */
    public java.math.BigDecimal getRechargeAmt()
    {
        return getBigDecimal("rechargeAmt");
    }
    public void setRechargeAmt(java.math.BigDecimal item)
    {
        setBigDecimal("rechargeAmt", item);
    }
    /**
     * Object:�տ�����'s ʹ�����ͽ��property 
     */
    public java.math.BigDecimal getPresAmt()
    {
        return getBigDecimal("presAmt");
    }
    public void setPresAmt(java.math.BigDecimal item)
    {
        setBigDecimal("presAmt", item);
    }
    /**
     * Object:�տ�����'s ����ȯ���property 
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
     * Object:�տ�����'s ���ֵ��ֽ��property 
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
     * Object: �տ����� 's ��Ա�� property 
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
     * Object:�տ�����'s ��Ա�������˻�IDproperty 
     */
    public String getPointAccountID()
    {
        return getString("pointAccountID");
    }
    public void setPointAccountID(String item)
    {
        setString("pointAccountID", item);
    }
    /**
     * Object:�տ�����'s ��Ա���ֽ��˻�IDproperty 
     */
    public String getCashAccountID()
    {
        return getString("cashAccountID");
    }
    public void setCashAccountID(String item)
    {
        setString("cashAccountID", item);
    }
    /**
     * Object:�տ�����'s �Ƿ���Ի��ֵ���property 
     */
    public boolean isIsPointToCash()
    {
        return getBoolean("isPointToCash");
    }
    public void setIsPointToCash(boolean item)
    {
        setBoolean("isPointToCash", item);
    }
    /**
     * Object:�տ�����'s �Ƿ���ô�ֵproperty 
     */
    public boolean isIsUseCashAccount()
    {
        return getBoolean("isUseCashAccount");
    }
    public void setIsUseCashAccount(boolean item)
    {
        setBoolean("isUseCashAccount", item);
    }
    /**
     * Object: �տ����� 's ����ȯ property 
     */
    public com.kingdee.eas.auto4s.arp.aar.GiftCardInfo getGiftCard()
    {
        return (com.kingdee.eas.auto4s.arp.aar.GiftCardInfo)get("giftCard");
    }
    public void setGiftCard(com.kingdee.eas.auto4s.arp.aar.GiftCardInfo item)
    {
        put("giftCard", item);
    }
    /**
     * Object: �տ����� 's ���� property 
     */
    public com.kingdee.eas.auto4s.arp.aar.PointSpentInfo getPointSpent()
    {
        return (com.kingdee.eas.auto4s.arp.aar.PointSpentInfo)get("pointSpent");
    }
    public void setPointSpent(com.kingdee.eas.auto4s.arp.aar.PointSpentInfo item)
    {
        put("pointSpent", item);
    }
    /**
     * Object: �տ����� 's ��ֵ������ property 
     */
    public com.kingdee.eas.auto4s.arp.aar.SavingCardSpentInfo getSavingCardSpent()
    {
        return (com.kingdee.eas.auto4s.arp.aar.SavingCardSpentInfo)get("savingCardSpent");
    }
    public void setSavingCardSpent(com.kingdee.eas.auto4s.arp.aar.SavingCardSpentInfo item)
    {
        put("savingCardSpent", item);
    }
    /**
     * Object:�տ�����'s Ԥ�տ�������property 
     */
    public java.math.BigDecimal getPRAbateAmount()
    {
        return getBigDecimal("PRAbateAmount");
    }
    public void setPRAbateAmount(java.math.BigDecimal item)
    {
        setBigDecimal("PRAbateAmount", item);
    }
    /**
     * Object:�տ�����'s Ԥ�տ��־property 
     */
    public boolean isIsPreReceive()
    {
        return getBoolean("isPreReceive");
    }
    public void setIsPreReceive(boolean item)
    {
        setBoolean("isPreReceive", item);
    }
    /**
     * Object: �տ����� 's Ԥ�տ���� property 
     */
    public com.kingdee.eas.auto4s.arp.aar.PRWriteOffBillInfo getPRAbateBill()
    {
        return (com.kingdee.eas.auto4s.arp.aar.PRWriteOffBillInfo)get("PRAbateBill");
    }
    public void setPRAbateBill(com.kingdee.eas.auto4s.arp.aar.PRWriteOffBillInfo item)
    {
        put("PRAbateBill", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("4C561EE5");
    }
}