package com.kingdee.eas.base.permission.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.app.ObjectBaseController;
import com.kingdee.eas.base.permission.UserCollection;
import com.kingdee.eas.base.permission.RoleCollection;
import com.kingdee.eas.base.permission.LoginAuthorWayEnum;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.eas.base.permission.UserUpdateParam;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.CoreBaseInfo;
import java.util.Collection;
import com.kingdee.eas.base.permission.OrgRangeType;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.org.FullOrgUnitCollection;
import java.util.List;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface UserController extends ObjectBaseController
{
    public UserInfo getUserInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public UserInfo getUserInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public UserInfo getUserInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public UserCollection getUserCollection(Context ctx) throws BOSException, RemoteException;
    public UserCollection getUserCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public UserCollection getUserCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public void update(Context ctx, IObjectPK pk, IObjectValue model, boolean isUpdatePassword) throws BOSException, EASBizException, RemoteException;
    public UserInfo getUserByID(Context ctx, IObjectPK id) throws BOSException, EASBizException, RemoteException;
    public void forbidUsers(Context ctx, IObjectPK[] users, boolean forbidden) throws BOSException, EASBizException, RemoteException;
    public void lockUsers(Context ctx, IObjectPK[] users, boolean lock) throws BOSException, EASBizException, RemoteException;
    public void updatePass(Context ctx, IObjectPK id, String oldPass, String newPass) throws BOSException, EASBizException, RemoteException;
    public UserInfo getUser(Context ctx, String number) throws BOSException, EASBizException, RemoteException;
    public boolean checkOrgIsRelatedWithPerms(Context ctx, IObjectPK userPK, IObjectPK orgPK) throws BOSException, EASBizException, RemoteException;
    public boolean checkOrgIsRelatedWithPerms(Context ctx, IObjectPK userPK, Collection orgIDlist) throws BOSException, EASBizException, RemoteException;
    public boolean checkUserIsRelatedWithPerms(Context ctx, IObjectPK userPK) throws BOSException, EASBizException, RemoteException;
    public String getDeletedUserID(Context ctx, String number) throws BOSException, EASBizException, RemoteException;
    public void changeCtrlUnit(Context ctx, IObjectPK ctrlPK, IObjectPK userPK) throws BOSException, EASBizException, RemoteException;
    public void createUserByPerson(Context ctx, IObjectPK personId) throws BOSException, EASBizException, RemoteException;
    public void batchCreateUserByPerson(Context ctx, List personIDList) throws BOSException, EASBizException, RemoteException;
    public void updateLoginAuthorWay(Context ctx, List userIdList, LoginAuthorWayEnum loginAuthorWay) throws BOSException, EASBizException, RemoteException;
    public void callBackUser(Context ctx, List userIdList) throws BOSException, EASBizException, RemoteException;
    public boolean haveThisNumber(Context ctx, String roleNumber) throws BOSException, EASBizException, RemoteException;
    public UserInfo getUserInfoByNumber(Context ctx, String userNumber) throws BOSException, EASBizException, RemoteException;
    public UserCollection notOrgRangeUseCol(Context ctx, OrgRangeType orgRangeType) throws BOSException, EASBizException, RemoteException;
    public void setUserLoginAuthWay(Context ctx, List userID, LoginAuthorWayEnum authWay) throws BOSException, EASBizException, RemoteException;
    public void setRegisterResult(Context ctx, List userID, boolean isCertified) throws BOSException, EASBizException, RemoteException;
    public void saveRoleInfo(Context ctx, UserInfo userInfo, FullOrgUnitCollection fullOrgUnit, String roleID) throws BOSException, EASBizException, RemoteException;
    public void updateAndDeleteOrgPermAndRole(Context ctx, IObjectPK pk, UserInfo userInfo, boolean isUpdatePassword, String orgIds) throws BOSException, EASBizException, RemoteException;
    public void deleteRelativePermAndRole(Context ctx, IObjectPK userPK, Collection orgIdCol) throws BOSException, EASBizException, RemoteException;
    public void update(Context ctx, IObjectPK pk, IObjectValue model, boolean isUpdatePassword, boolean isUpdateOrgRange) throws BOSException, EASBizException, RemoteException;
    public boolean matchPassword(Context ctx, String userNumber, String password) throws BOSException, EASBizException, RemoteException;
    public String[][] findUserByAD(Context ctx, String[][] orgs) throws BOSException, EASBizException, RemoteException;
    public void batchCreateUserByPerson(Context ctx, List personIDList, String password) throws BOSException, EASBizException, RemoteException;
    public IObjectPK addnew(Context ctx, UserInfo userInfo, List roleIdList) throws BOSException, EASBizException, RemoteException;
    public void saveRoleInfo(Context ctx, UserInfo userInfo, FullOrgUnitCollection fullOrgUnitCol, RoleCollection roleCol) throws BOSException, EASBizException, RemoteException;
    public PersonInfo getPersonWithContact(Context ctx, PersonInfo personInfo) throws BOSException, EASBizException, RemoteException;
    public void createWeiBoUser(Context ctx, String email, String personId) throws BOSException, EASBizException, RemoteException;
    public void update(Context ctx, IObjectPK pk, UserInfo value, UserUpdateParam param) throws BOSException, EASBizException, RemoteException;
}