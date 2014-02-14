package com.kingdee.eas.base.permission;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
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
import com.kingdee.eas.framework.IObjectBase;

public interface IUser extends IObjectBase
{
    public UserInfo getUserInfo(IObjectPK pk) throws BOSException, EASBizException;
    public UserInfo getUserInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public UserInfo getUserInfo(String oql) throws BOSException, EASBizException;
    public UserCollection getUserCollection() throws BOSException;
    public UserCollection getUserCollection(EntityViewInfo view) throws BOSException;
    public UserCollection getUserCollection(String oql) throws BOSException;
    public void update(IObjectPK pk, IObjectValue model, boolean isUpdatePassword) throws BOSException, EASBizException;
    public UserInfo getUserByID(IObjectPK id) throws BOSException, EASBizException;
    public void forbidUsers(IObjectPK[] users, boolean forbidden) throws BOSException, EASBizException;
    public void lockUsers(IObjectPK[] users, boolean lock) throws BOSException, EASBizException;
    public void updatePass(IObjectPK id, String oldPass, String newPass) throws BOSException, EASBizException;
    public UserInfo getUser(String number) throws BOSException, EASBizException;
    public boolean checkOrgIsRelatedWithPerms(IObjectPK userPK, IObjectPK orgPK) throws BOSException, EASBizException;
    public boolean checkOrgIsRelatedWithPerms(IObjectPK userPK, Collection orgIDlist) throws BOSException, EASBizException;
    public boolean checkUserIsRelatedWithPerms(IObjectPK userPK) throws BOSException, EASBizException;
    public String getDeletedUserID(String number) throws BOSException, EASBizException;
    public void changeCtrlUnit(IObjectPK ctrlPK, IObjectPK userPK) throws BOSException, EASBizException;
    public void createUserByPerson(IObjectPK personId) throws BOSException, EASBizException;
    public void batchCreateUserByPerson(List personIDList) throws BOSException, EASBizException;
    public void updateLoginAuthorWay(List userIdList, LoginAuthorWayEnum loginAuthorWay) throws BOSException, EASBizException;
    public void callBackUser(List userIdList) throws BOSException, EASBizException;
    public boolean haveThisNumber(String roleNumber) throws BOSException, EASBizException;
    public UserInfo getUserInfoByNumber(String userNumber) throws BOSException, EASBizException;
    public UserCollection notOrgRangeUseCol(OrgRangeType orgRangeType) throws BOSException, EASBizException;
    public void setUserLoginAuthWay(List userID, LoginAuthorWayEnum authWay) throws BOSException, EASBizException;
    public void setRegisterResult(List userID, boolean isCertified) throws BOSException, EASBizException;
    public void saveRoleInfo(UserInfo userInfo, FullOrgUnitCollection fullOrgUnit, String roleID) throws BOSException, EASBizException;
    public void updateAndDeleteOrgPermAndRole(IObjectPK pk, UserInfo userInfo, boolean isUpdatePassword, String orgIds) throws BOSException, EASBizException;
    public void deleteRelativePermAndRole(IObjectPK userPK, Collection orgIdCol) throws BOSException, EASBizException;
    public void update(IObjectPK pk, IObjectValue model, boolean isUpdatePassword, boolean isUpdateOrgRange) throws BOSException, EASBizException;
    public boolean matchPassword(String userNumber, String password) throws BOSException, EASBizException;
    public String[][] findUserByAD(String[][] orgs) throws BOSException, EASBizException;
    public void batchCreateUserByPerson(List personIDList, String password) throws BOSException, EASBizException;
    public IObjectPK addnew(UserInfo userInfo, List roleIdList) throws BOSException, EASBizException;
    public void saveRoleInfo(UserInfo userInfo, FullOrgUnitCollection fullOrgUnitCol, RoleCollection roleCol) throws BOSException, EASBizException;
    public PersonInfo getPersonWithContact(PersonInfo personInfo) throws BOSException, EASBizException;
    public void createWeiBoUser(String email, String personId) throws BOSException, EASBizException;
    public void update(IObjectPK pk, UserInfo value, UserUpdateParam param) throws BOSException, EASBizException;
}