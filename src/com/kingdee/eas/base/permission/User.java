package com.kingdee.eas.base.permission;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.ObjectBase;
import com.kingdee.bos.util.*;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.CoreBaseInfo;
import java.util.Collection;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.basedata.org.FullOrgUnitCollection;
import java.util.List;
import com.kingdee.eas.base.permission.app.*;
import com.kingdee.eas.framework.IObjectBase;

public class User extends ObjectBase implements IUser
{
    public User()
    {
        super();
        registerInterface(IUser.class, this);
    }
    public User(Context ctx)
    {
        super(ctx);
        registerInterface(IUser.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("13B7DE7F");
    }
    private UserController getController() throws BOSException
    {
        return (UserController)getBizController();
    }
    /**
     *����û�-System defined method
     *@param pk ȡֵ
     *@return
     */
    public UserInfo getUserInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getUserInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����û�-System defined method
     *@param pk ȡֵ
     *@param selector ȡֵ
     *@return
     */
    public UserInfo getUserInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getUserInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����û�-System defined method
     *@param oql ȡֵ
     *@return
     */
    public UserInfo getUserInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getUserInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����û�����-System defined method
     *@return
     */
    public UserCollection getUserCollection() throws BOSException
    {
        try {
            return getController().getUserCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����û�����-System defined method
     *@param view ȡ����
     *@return
     */
    public UserCollection getUserCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getUserCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����û�����-System defined method
     *@param oql ȡ����
     *@return
     */
    public UserCollection getUserCollection(String oql) throws BOSException
    {
        try {
            return getController().getUserCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�޸��û�-User defined method
     *@param pk pk
     *@param model model
     *@param isUpdatePassword isUpdatePassword
     */
    public void update(IObjectPK pk, IObjectValue model, boolean isUpdatePassword) throws BOSException, EASBizException
    {
        try {
            getController().update(getContext(), pk, model, isUpdatePassword);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����ID��ȡ�û���Ϣ-User defined method
     *@param id id
     *@return
     */
    public UserInfo getUserByID(IObjectPK id) throws BOSException, EASBizException
    {
        try {
            return getController().getUserByID(getContext(), id);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�������ý����û�-User defined method
     *@param users users
     *@param forbidden forbidden
     */
    public void forbidUsers(IObjectPK[] users, boolean forbidden) throws BOSException, EASBizException
    {
        try {
            getController().forbidUsers(getContext(), users, forbidden);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����û�-User defined method
     *@param users users
     *@param lock lock
     */
    public void lockUsers(IObjectPK[] users, boolean lock) throws BOSException, EASBizException
    {
        try {
            getController().lockUsers(getContext(), users, lock);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��֤�������Ƿ���ȷ����֤�������Ƿ���Ϲ淶��Ȼ���޸����롣-User defined method
     *@param id id
     *@param oldPass oldPass
     *@param newPass newPass
     */
    public void updatePass(IObjectPK id, String oldPass, String newPass) throws BOSException, EASBizException
    {
        try {
            getController().updatePass(getContext(), id, oldPass, newPass);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ�û���Ϣ-User defined method
     *@param number number
     *@return
     */
    public UserInfo getUser(String number) throws BOSException, EASBizException
    {
        try {
            return getController().getUser(getContext(), number);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����û��Ƿ���Ȩ��-User defined method
     *@param userPK userPK
     *@param orgPK orgPK
     *@return
     */
    public boolean checkOrgIsRelatedWithPerms(IObjectPK userPK, IObjectPK orgPK) throws BOSException, EASBizException
    {
        try {
            return getController().checkOrgIsRelatedWithPerms(getContext(), userPK, orgPK);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����û��Ƿ���Ȩ��-User defined method
     *@param userPK userPK
     *@param orgIDlist orgIDlist
     *@return
     */
    public boolean checkOrgIsRelatedWithPerms(IObjectPK userPK, Collection orgIDlist) throws BOSException, EASBizException
    {
        try {
            return getController().checkOrgIsRelatedWithPerms(getContext(), userPK, orgIDlist);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����û��Ƿ���Ȩ��-User defined method
     *@param userPK userPK
     *@return
     */
    public boolean checkUserIsRelatedWithPerms(IObjectPK userPK) throws BOSException, EASBizException
    {
        try {
            return getController().checkUserIsRelatedWithPerms(getContext(), userPK);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��ȡ��ɾ�����û�����-User defined method
     *@param number number
     *@return
     */
    public String getDeletedUserID(String number) throws BOSException, EASBizException
    {
        try {
            return getController().getDeletedUserID(getContext(), number);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *������Ƶ�Ԫ-User defined method
     *@param ctrlPK ctrlPK
     *@param userPK userPK
     */
    public void changeCtrlUnit(IObjectPK ctrlPK, IObjectPK userPK) throws BOSException, EASBizException
    {
        try {
            getController().changeCtrlUnit(getContext(), ctrlPK, userPK);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����ְԱ�����û�-User defined method
     *@param personId personId
     */
    public void createUserByPerson(IObjectPK personId) throws BOSException, EASBizException
    {
        try {
            getController().createUserByPerson(getContext(), personId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����ְԱ���������û�-User defined method
     *@param personIDList �û�id�б�String��ʽ��
     */
    public void batchCreateUserByPerson(List personIDList) throws BOSException, EASBizException
    {
        try {
            getController().batchCreateUserByPerson(getContext(), personIDList);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�޸���֤��ʽ-User defined method
     *@param userIdList �û�id�б�������String����
     *@param loginAuthorWay ��֤��ʽ
     */
    public void updateLoginAuthorWay(List userIdList, LoginAuthorWayEnum loginAuthorWay) throws BOSException, EASBizException
    {
        try {
            getController().updateLoginAuthorWay(getContext(), userIdList, loginAuthorWay);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����û�-User defined method
     *@param userIdList userIdList
     */
    public void callBackUser(List userIdList) throws BOSException, EASBizException
    {
        try {
            getController().callBackUser(getContext(), userIdList);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����Ƿ����-User defined method
     *@param roleNumber roleNumber
     *@return
     */
    public boolean haveThisNumber(String roleNumber) throws BOSException, EASBizException
    {
        try {
            return getController().haveThisNumber(getContext(), roleNumber);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ͨ���������û���Ϣ-User defined method
     *@param userNumber userNumber
     *@return
     */
    public UserInfo getUserInfoByNumber(String userNumber) throws BOSException, EASBizException
    {
        try {
            return getController().getUserInfoByNumber(getContext(), userNumber);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *û����֯��Χ���û�-User defined method
     *@param orgRangeType orgRangeType
     *@return
     */
    public UserCollection notOrgRangeUseCol(OrgRangeType orgRangeType) throws BOSException, EASBizException
    {
        try {
            return getController().notOrgRangeUseCol(getContext(), orgRangeType);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *���õ�¼��ʽ-User defined method
     *@param userID �û�ID
     *@param authWay ��֤��ʽ
     */
    public void setUserLoginAuthWay(List userID, LoginAuthorWayEnum authWay) throws BOSException, EASBizException
    {
        try {
            getController().setUserLoginAuthWay(getContext(), userID, authWay);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *������֤���-User defined method
     *@param userID �û�ID
     *@param isCertified ��֤���
     */
    public void setRegisterResult(List userID, boolean isCertified) throws BOSException, EASBizException
    {
        try {
            getController().setRegisterResult(getContext(), userID, isCertified);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����û��Ľ�ɫ��Ϣ-User defined method
     *@param userInfo �û���Ϣ
     *@param fullOrgUnit ��֯��Ϣ
     *@param roleID ��ɫID
     */
    public void saveRoleInfo(UserInfo userInfo, FullOrgUnitCollection fullOrgUnit, String roleID) throws BOSException, EASBizException
    {
        try {
            getController().saveRoleInfo(getContext(), userInfo, fullOrgUnit, roleID);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����û���Ϣ����ɾ�����õ��û���֯Ȩ�޼���ɫ-User defined method
     *@param pk use pk
     *@param userInfo userInfo
     *@param isUpdatePassword �Ƿ��Ǹ�������
     *@param orgIds ��֯id
     */
    public void updateAndDeleteOrgPermAndRole(IObjectPK pk, UserInfo userInfo, boolean isUpdatePassword, String orgIds) throws BOSException, EASBizException
    {
        try {
            getController().updateAndDeleteOrgPermAndRole(getContext(), pk, userInfo, isUpdatePassword, orgIds);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ɾ���û���ָ����֯�µ�����Ȩ����Ϣ-User defined method
     *@param userPK userPK
     *@param orgIdCol orgIdCol
     */
    public void deleteRelativePermAndRole(IObjectPK userPK, Collection orgIdCol) throws BOSException, EASBizException
    {
        try {
            getController().deleteRelativePermAndRole(getContext(), userPK, orgIdCol);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�޸��û�-User defined method
     *@param pk pk
     *@param model model
     *@param isUpdatePassword isUpdatePassword
     *@param isUpdateOrgRange isUpdateOrgRange
     */
    public void update(IObjectPK pk, IObjectValue model, boolean isUpdatePassword, boolean isUpdateOrgRange) throws BOSException, EASBizException
    {
        try {
            getController().update(getContext(), pk, model, isUpdatePassword, isUpdateOrgRange);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *matchPassword-User defined method
     *@param userNumber userNumber
     *@param password password
     *@return
     */
    public boolean matchPassword(String userNumber, String password) throws BOSException, EASBizException
    {
        try {
            return getController().matchPassword(getContext(), userNumber, password);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ͨ��AD�š����Ʋ��һ�����ְԱ-User defined method
     *@param orgs AD��ʵ����֯����
     *@return
     */
    public String[][] findUserByAD(String[][] orgs) throws BOSException, EASBizException
    {
        try {
            return getController().findUserByAD(getContext(), orgs);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����ְԱ���������û�-User defined method
     *@param personIDList �û�id�б�String��ʽ��
     *@param password �û�����
     */
    public void batchCreateUserByPerson(List personIDList, String password) throws BOSException, EASBizException
    {
        try {
            getController().batchCreateUserByPerson(getContext(), personIDList, password);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����û�-User defined method
     *@param userInfo �û���Ϣ
     *@param roleIdList ��ɫID
     *@return
     */
    public IObjectPK addnew(UserInfo userInfo, List roleIdList) throws BOSException, EASBizException
    {
        try {
            return getController().addnew(getContext(), userInfo, roleIdList);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����û��Ľ�ɫ��Ϣ-User defined method
     *@param userInfo �û���Ϣ
     *@param fullOrgUnitCol ��֯��Ϣ
     *@param roleCol ��ɫ��Ϣ
     */
    public void saveRoleInfo(UserInfo userInfo, FullOrgUnitCollection fullOrgUnitCol, RoleCollection roleCol) throws BOSException, EASBizException
    {
        try {
            getController().saveRoleInfo(getContext(), userInfo, fullOrgUnitCol, roleCol);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��ȡְԱ��Ϣ������T_HR_PersonContactMethod�л�ȡ��ϵ��Ϣ-User defined method
     *@param personInfo ְԱ����
     *@return
     */
    public PersonInfo getPersonWithContact(PersonInfo personInfo) throws BOSException, EASBizException
    {
        try {
            return getController().getPersonWithContact(getContext(), personInfo);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *   -User defined method
     *@param email email
     *@param personId personId
     */
    public void createWeiBoUser(String email, String personId) throws BOSException, EASBizException
    {
        try {
            getController().createWeiBoUser(getContext(), email, personId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�޸��û�-User defined method
     *@param pk pk
     *@param value value
     *@param param param
     */
    public void update(IObjectPK pk, UserInfo value, UserUpdateParam param) throws BOSException, EASBizException
    {
        try {
            getController().update(getContext(), pk, value, param);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}