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
     *获得用户-System defined method
     *@param pk 取值
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
     *获得用户-System defined method
     *@param pk 取值
     *@param selector 取值
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
     *获得用户-System defined method
     *@param oql 取值
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
     *获得用户集合-System defined method
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
     *获得用户集合-System defined method
     *@param view 取集合
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
     *获得用户集合-System defined method
     *@param oql 取集合
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
     *修改用户-User defined method
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
     *根据ID获取用户信息-User defined method
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
     *批量启用禁用用户-User defined method
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
     *锁定用户-User defined method
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
     *验证旧密码是否正确，验证新密码是否符合规范，然后修改密码。-User defined method
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
     *取用户信息-User defined method
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
     *检查用户是否被授权过-User defined method
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
     *检查用户是否被授权过-User defined method
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
     *检查用户是否被授权过-User defined method
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
     *获取被删除的用户内码-User defined method
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
     *变更控制单元-User defined method
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
     *根据职员创建用户-User defined method
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
     *根据职员批量创建用户-User defined method
     *@param personIDList 用户id列表（String形式）
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
     *修改认证方式-User defined method
     *@param userIdList 用户id列表，内容是String类型
     *@param loginAuthorWay 认证方式
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
     *回收用户-User defined method
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
     *编码是否存在-User defined method
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
     *通过编码获得用户信息-User defined method
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
     *没有组织范围的用户-User defined method
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
     *设置登录方式-User defined method
     *@param userID 用户ID
     *@param authWay 认证方式
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
     *设置认证结果-User defined method
     *@param userID 用户ID
     *@param isCertified 认证结果
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
     *设置用户的角色信息-User defined method
     *@param userInfo 用户信息
     *@param fullOrgUnit 组织信息
     *@param roleID 角色ID
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
     *更新用户信息，并删除弃用的用户组织权限及角色-User defined method
     *@param pk use pk
     *@param userInfo userInfo
     *@param isUpdatePassword 是否是更改密码
     *@param orgIds 组织id
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
     *删除用户在指定组织下的所有权限信息-User defined method
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
     *修改用户-User defined method
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
     *通过AD号、名称查找或新增职员-User defined method
     *@param orgs AD号实名组织数组
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
     *根据职员批量创建用户-User defined method
     *@param personIDList 用户id列表（String形式）
     *@param password 用户密码
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
     *新增用户-User defined method
     *@param userInfo 用户信息
     *@param roleIdList 角色ID
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
     *设置用户的角色信息-User defined method
     *@param userInfo 用户信息
     *@param fullOrgUnitCol 组织信息
     *@param roleCol 角色信息
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
     *获取职员信息，并从T_HR_PersonContactMethod中获取联系信息-User defined method
     *@param personInfo 职员对象
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
     *修改用户-User defined method
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