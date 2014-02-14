package com.kingdee.eas.base.permission;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractUserInfo extends com.kingdee.eas.framework.ObjectBaseInfo implements Serializable 
{
    public AbstractUserInfo()
    {
        this("id");
    }
    protected AbstractUserInfo(String pkField)
    {
        super(pkField);
        put("orgRange", new com.kingdee.eas.base.permission.OrgRangeCollection());
        put("orgRangeIncludeSubOrg", new com.kingdee.eas.base.permission.OrgRangeIncludeSubOrgCollection());
    }
    /**
     * Object:�û�'s �û���property 
     */
    public String getNumber()
    {
        return getString("number");
    }
    public void setNumber(String item)
    {
        setString("number", item);
    }
    /**
     * Object:�û�'s �û�ʵ��property 
     */
    public String getName()
    {
        return getName((Locale)null);
    }
    public void setName(String item)
    {
		setName(item,(Locale)null);
    }
    public String getName(Locale local)
    {
        return TypeConversionUtils.objToString(get("name", local));
    }
    public void setName(String item, Locale local)
    {
        put("name", item, local);
    }
    /**
     * Object:�û�'s �û�����property 
     */
    public com.kingdee.eas.base.permission.UserType getType()
    {
        return com.kingdee.eas.base.permission.UserType.getEnum(getInt("type"));
    }
    public void setType(com.kingdee.eas.base.permission.UserType item)
    {
		if (item != null) {
        setInt("type", item.getValue());
		}
    }
    /**
     * Object:�û�'s ����property 
     */
    public String getDescription()
    {
        return getDescription((Locale)null);
    }
    public void setDescription(String item)
    {
		setDescription(item,(Locale)null);
    }
    public String getDescription(Locale local)
    {
        return TypeConversionUtils.objToString(get("description", local));
    }
    public void setDescription(String item, Locale local)
    {
        put("description", item, local);
    }
    /**
     * Object:�û�'s ����property 
     */
    public String getPassword()
    {
        return getString("password");
    }
    public void setPassword(String item)
    {
        setString("password", item);
    }
    /**
     * Object:�û�'s �Ƿ����Ѿ�ɾ��property 
     */
    public boolean isIsDelete()
    {
        return getBoolean("isDelete");
    }
    public void setIsDelete(boolean item)
    {
        setBoolean("isDelete", item);
    }
    /**
     * Object:�û�'s �Ƿ�����property 
     */
    public boolean isIsLocked()
    {
        return getBoolean("isLocked");
    }
    public void setIsLocked(boolean item)
    {
        setBoolean("isLocked", item);
    }
    /**
     * Object:�û�'s �Ƿ����property 
     */
    public boolean isIsForbidden()
    {
        return getBoolean("isForbidden");
    }
    public void setIsForbidden(boolean item)
    {
        setBoolean("isForbidden", item);
    }
    /**
     * Object:�û�'s ��Чʱ��property 
     */
    public java.sql.Timestamp getEffectiveDate()
    {
        return getTimestamp("effectiveDate");
    }
    public void setEffectiveDate(java.sql.Timestamp item)
    {
        setTimestamp("effectiveDate", item);
    }
    /**
     * Object:�û�'s ʧЧʱ��property 
     */
    public java.sql.Timestamp getInvalidationDate()
    {
        return getTimestamp("invalidationDate");
    }
    public void setInvalidationDate(java.sql.Timestamp item)
    {
        setTimestamp("invalidationDate", item);
    }
    /**
     * Object:�û�'s ȱʡʹ������property 
     */
    public com.kingdee.eas.base.permission.Locale getDefLocale()
    {
        return com.kingdee.eas.base.permission.Locale.getEnum(getString("defLocale"));
    }
    public void setDefLocale(com.kingdee.eas.base.permission.Locale item)
    {
		if (item != null) {
        setString("defLocale", item.getValue());
		}
    }
    /**
     * Object:�û�'s �Ƿ�ע��property 
     */
    public boolean isIsRegister()
    {
        return getBoolean("isRegister");
    }
    public void setIsRegister(boolean item)
    {
        setBoolean("isRegister", item);
    }
    /**
     * Object:�û�'s ��¼�������property 
     */
    public int getErrCount()
    {
        return getInt("errCount");
    }
    public void setErrCount(int item)
    {
        setInt("errCount", item);
    }
    /**
     * Object: �û� 's �����û��� property 
     */
    public com.kingdee.eas.base.permission.UserGroupInfo getGroup()
    {
        return (com.kingdee.eas.base.permission.UserGroupInfo)get("group");
    }
    public void setGroup(com.kingdee.eas.base.permission.UserGroupInfo item)
    {
        put("group", item);
    }
    /**
     * Object: �û� 's �û���֯��Χ property 
     */
    public com.kingdee.eas.base.permission.OrgRangeCollection getOrgRange()
    {
        return (com.kingdee.eas.base.permission.OrgRangeCollection)get("orgRange");
    }
    /**
     * Object: �û� 's �����¼���֯����֯��Χ property 
     */
    public com.kingdee.eas.base.permission.OrgRangeIncludeSubOrgCollection getOrgRangeIncludeSubOrg()
    {
        return (com.kingdee.eas.base.permission.OrgRangeIncludeSubOrgCollection)get("orgRangeIncludeSubOrg");
    }
    /**
     * Object: �û� 's ��Ӧ�Ĳ����� property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getPerson()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("person");
    }
    public void setPerson(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("person", item);
    }
    /**
     * Object: �û� 's ������� property 
     */
    public com.kingdee.eas.base.permission.SecurityInfo getSecurity()
    {
        return (com.kingdee.eas.base.permission.SecurityInfo)get("security");
    }
    public void setSecurity(com.kingdee.eas.base.permission.SecurityInfo item)
    {
        put("security", item);
    }
    /**
     * Object:�û�'s ������Ч����property 
     */
    public java.util.Date getPWEffectiveDate()
    {
        return getDate("PWEffectiveDate");
    }
    public void setPWEffectiveDate(java.util.Date item)
    {
        setDate("PWEffectiveDate", item);
    }
    /**
     * Object:�û�'s �û�����ʱ��property 
     */
    public java.sql.Time getLockedTime()
    {
        return getTime("lockedTime");
    }
    public void setLockedTime(java.sql.Time item)
    {
        setTime("lockedTime", item);
    }
    /**
     * Object:�û�'s �Ƿ�Ϊҵ�����Աproperty 
     */
    public boolean isBizAdmin()
    {
        return getBoolean("bizAdmin");
    }
    public void setBizAdmin(boolean item)
    {
        setBoolean("bizAdmin", item);
    }
    /**
     * Object:�û�'s �Ƿ��޸Ĺ�����property 
     */
    public boolean isChangedPW()
    {
        return getBoolean("changedPW");
    }
    public void setChangedPW(boolean item)
    {
        setBoolean("changedPW", item);
    }
    /**
     * Object: �û� 's ȱʡ��֯ property 
     */
    public com.kingdee.eas.basedata.org.FullOrgUnitInfo getDefOrgUnit()
    {
        return (com.kingdee.eas.basedata.org.FullOrgUnitInfo)get("defOrgUnit");
    }
    public void setDefOrgUnit(com.kingdee.eas.basedata.org.FullOrgUnitInfo item)
    {
        put("defOrgUnit", item);
    }
    /**
     * Object: �û� 's ����ɫ property 
     */
    public com.kingdee.eas.base.permission.RoleInfo getMainRole()
    {
        return (com.kingdee.eas.base.permission.RoleInfo)get("mainRole");
    }
    public void setMainRole(com.kingdee.eas.base.permission.RoleInfo item)
    {
        put("mainRole", item);
    }
    /**
     * Object:�û�'s �����û�property 
     */
    public boolean isAgentUser()
    {
        return getBoolean("agentUser");
    }
    public void setAgentUser(boolean item)
    {
        setBoolean("agentUser", item);
    }
    /**
     * Object:�û�'s ��½��֤��ʽproperty 
     */
    public com.kingdee.eas.base.permission.LoginAuthorWayEnum getLoginAuthorWay()
    {
        return com.kingdee.eas.base.permission.LoginAuthorWayEnum.getEnum(getInt("loginAuthorWay"));
    }
    public void setLoginAuthorWay(com.kingdee.eas.base.permission.LoginAuthorWayEnum item)
    {
		if (item != null) {
        setInt("loginAuthorWay", item.getValue());
		}
    }
    /**
     * Object:�û�'s ��ʷ����property 
     */
    public String getPwdHisStr()
    {
        return getString("pwdHisStr");
    }
    public void setPwdHisStr(String item)
    {
        setString("pwdHisStr", item);
    }
    /**
     * Object:�û�'s ����idproperty 
     */
    public String getReferId()
    {
        return getString("referId");
    }
    public void setReferId(String item)
    {
        setString("referId", item);
    }
    /**
     * Object:�û�'s �����ʼ�property 
     */
    public String getEmail()
    {
        return getString("email");
    }
    public void setEmail(String item)
    {
        setString("email", item);
    }
    /**
     * Object:�û�'s ��ͥ�绰property 
     */
    public String getHomePhone()
    {
        return getString("homePhone");
    }
    public void setHomePhone(String item)
    {
        setString("homePhone", item);
    }
    /**
     * Object:�û�'s �칫�ҵ绰property 
     */
    public String getOfficePhone()
    {
        return getString("officePhone");
    }
    public void setOfficePhone(String item)
    {
        setString("officePhone", item);
    }
    /**
     * Object:�û�'s ���õ����ʼ� property 
     */
    public String getBackupEMail()
    {
        return getString("backupEMail");
    }
    public void setBackupEMail(String item)
    {
        setString("backupEMail", item);
    }
    /**
     * Object:�û�'s �ֻ�����property 
     */
    public String getCell()
    {
        return getString("cell");
    }
    public void setCell(String item)
    {
        setString("cell", item);
    }
    /**
     * Object:�û�'s �ͻ�idproperty 
     */
    public String getCustomerID()
    {
        return getString("customerID");
    }
    public void setCustomerID(String item)
    {
        setString("customerID", item);
    }
    /**
     * Object:�û�'s ��Ӧ��idproperty 
     */
    public String getSupplierID()
    {
        return getString("supplierID");
    }
    public void setSupplierID(String item)
    {
        setString("supplierID", item);
    }
    /**
     * Object:�û�'s AD�˺�property 
     */
    public String getAdNumber()
    {
        return getString("adNumber");
    }
    public void setAdNumber(String item)
    {
        setString("adNumber", item);
    }
    /**
     * Object:�û�'s �˻����property 
     */
    public com.kingdee.eas.ga.basedata.UserTypeEnum getUserType()
    {
        return com.kingdee.eas.ga.basedata.UserTypeEnum.getEnum(getString("userType"));
    }
    public void setUserType(com.kingdee.eas.ga.basedata.UserTypeEnum item)
    {
		if (item != null) {
        setString("userType", item.getValue());
		}
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("13B7DE7F");
    }
}