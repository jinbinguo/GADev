package com.kingdee.eas.base.permission.app;

import javax.ejb.*;
import java.rmi.RemoteException;
import com.kingdee.bos.*;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.rule.RuleExecutor;
import com.kingdee.bos.metadata.MetaDataPK;
//import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.framework.ejb.AbstractEntityControllerBean;
import com.kingdee.bos.framework.ejb.AbstractBizControllerBean;
//import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;
import com.kingdee.eas.framework.Result;
import com.kingdee.eas.framework.LineResult;
import com.kingdee.eas.framework.exception.EASMultiException;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.base.permission.UserCollection;
import com.kingdee.eas.base.permission.RoleCollection;
import com.kingdee.eas.base.permission.LoginAuthorWayEnum;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.app.ObjectBaseControllerBean;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.base.permission.UserUpdateParam;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.CoreBaseInfo;
import java.util.Collection;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.base.permission.OrgRangeType;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.eas.basedata.org.FullOrgUnitCollection;
import java.util.List;



public abstract class AbstractUserControllerBean extends ObjectBaseControllerBean implements UserController
{
    protected AbstractUserControllerBean()
    {
    }

    protected BOSObjectType getBOSType()
    {
        return new BOSObjectType("13B7DE7F");
    }

    public UserInfo getUserInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("28f2fdc1-00fb-1000-e000-0001c0a813e3"), new Object[]{ctx, pk});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            UserInfo retValue = (UserInfo)_getValue(ctx, pk);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (UserInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectValue _getValue(Context ctx, IObjectPK pk) throws BOSException, EASBizException
    {
        return super._getValue(ctx, pk);
    }

    public UserInfo getUserInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("28f2fdc1-00fb-1000-e000-0002c0a813e3"), new Object[]{ctx, pk, selector});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            UserInfo retValue = (UserInfo)_getValue(ctx, pk, selector);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (UserInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectValue _getValue(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        return super._getValue(ctx, pk, selector);
    }

    public UserInfo getUserInfo(Context ctx, String oql) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("28f2fdc1-00fb-1000-e000-0003c0a813e3"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            UserInfo retValue = (UserInfo)_getValue(ctx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (UserInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectValue _getValue(Context ctx, String oql) throws BOSException, EASBizException
    {
        return super._getValue(ctx, oql);
    }

    public UserCollection getUserCollection(Context ctx) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("28f2fdc1-00fb-1000-e000-0004c0a813e3"), new Object[]{ctx});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            UserCollection retValue = (UserCollection)_getCollection(ctx, svcCtx);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (UserCollection)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectCollection _getCollection(Context ctx, IServiceContext svcCtx) throws BOSException
    {
        return super._getCollection(ctx, svcCtx);
    }

    public UserCollection getUserCollection(Context ctx, EntityViewInfo view) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("28f2fdc1-00fb-1000-e000-0005c0a813e3"), new Object[]{ctx, view});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            UserCollection retValue = (UserCollection)_getCollection(ctx, svcCtx, view);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (UserCollection)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectCollection _getCollection(Context ctx, IServiceContext svcCtx, EntityViewInfo view) throws BOSException
    {
        return super._getCollection(ctx, svcCtx, view);
    }

    public UserCollection getUserCollection(Context ctx, String oql) throws BOSException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("28f2fdc1-00fb-1000-e000-0006c0a813e3"), new Object[]{ctx, oql});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            UserCollection retValue = (UserCollection)_getCollection(ctx, svcCtx, oql);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
        
          return (UserCollection)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected IObjectCollection _getCollection(Context ctx, IServiceContext svcCtx, String oql) throws BOSException
    {
        return super._getCollection(ctx, svcCtx, oql);
    }

    public void update(Context ctx, IObjectPK pk, IObjectValue model, boolean isUpdatePassword) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a18d5422-00fc-1000-e000-0001c0a813e4"), new Object[]{ctx, pk, model, new Boolean(isUpdatePassword)});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _update(ctx, pk, model, isUpdatePassword);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _update(Context ctx, IObjectPK pk, IObjectValue model, boolean isUpdatePassword) throws BOSException, EASBizException;

    public UserInfo getUserByID(Context ctx, IObjectPK id) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e4f243f7-00fa-1000-e000-0002c0a813e3"), new Object[]{ctx, id});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            UserInfo retValue = (UserInfo)_getUserByID(ctx, id);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (UserInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract UserInfo _getUserByID(Context ctx, IObjectPK id) throws BOSException, EASBizException;

    public void forbidUsers(Context ctx, IObjectPK[] users, boolean forbidden) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e62081ce-00fa-1000-e000-0004c0a813e3"), new Object[]{ctx, users, new Boolean(forbidden)});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _forbidUsers(ctx, users, forbidden);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _forbidUsers(Context ctx, IObjectPK[] users, boolean forbidden) throws BOSException, EASBizException;

    public void lockUsers(Context ctx, IObjectPK[] users, boolean lock) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("75ceb99c-00fb-1000-e000-0001c0a813e3"), new Object[]{ctx, users, new Boolean(lock)});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _lockUsers(ctx, users, lock);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _lockUsers(Context ctx, IObjectPK[] users, boolean lock) throws BOSException, EASBizException;

    public void updatePass(Context ctx, IObjectPK id, String oldPass, String newPass) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("3e0cfe0c-00fb-1000-e000-0001c0a813e3"), new Object[]{ctx, id, oldPass, newPass});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _updatePass(ctx, id, oldPass, newPass);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _updatePass(Context ctx, IObjectPK id, String oldPass, String newPass) throws BOSException, EASBizException;

    public UserInfo getUser(Context ctx, String number) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a7abd3bb-0101-1000-e000-0009c0a813a8"), new Object[]{ctx, number});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            UserInfo retValue = (UserInfo)_getUser(ctx, number);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (UserInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract IObjectValue _getUser(Context ctx, String number) throws BOSException, EASBizException;

    public boolean checkOrgIsRelatedWithPerms(Context ctx, IObjectPK userPK, IObjectPK orgPK) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("bcd5edca-0104-1000-e000-0261c0a8127c"), new Object[]{ctx, userPK, orgPK});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_checkOrgIsRelatedWithPerms(ctx, userPK, orgPK);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
            return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract boolean _checkOrgIsRelatedWithPerms(Context ctx, IObjectPK userPK, IObjectPK orgPK) throws BOSException, EASBizException;

    public boolean checkOrgIsRelatedWithPerms(Context ctx, IObjectPK userPK, Collection orgIDlist) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1c7bdf98-0115-1000-e000-0007c0a812db"), new Object[]{ctx, userPK, orgIDlist});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_checkOrgIsRelatedWithPerms(ctx, userPK, orgIDlist);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
            return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract boolean _checkOrgIsRelatedWithPerms(Context ctx, IObjectPK userPK, Collection orgIDlist) throws BOSException, EASBizException;

    public boolean checkUserIsRelatedWithPerms(Context ctx, IObjectPK userPK) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e60e9fe2-0105-1000-e000-0055c0a8127c"), new Object[]{ctx, userPK});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_checkUserIsRelatedWithPerms(ctx, userPK);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
            return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract boolean _checkUserIsRelatedWithPerms(Context ctx, IObjectPK userPK) throws BOSException, EASBizException;

    public String getDeletedUserID(Context ctx, String number) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("23e1e4a8-0106-1000-e000-0002c0a8127c"), new Object[]{ctx, number});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            String retValue = (String)_getDeletedUserID(ctx, number);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (String)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract String _getDeletedUserID(Context ctx, String number) throws BOSException, EASBizException;

    public void changeCtrlUnit(Context ctx, IObjectPK ctrlPK, IObjectPK userPK) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a1d77bc5-010b-1000-e000-000dc0a81293"), new Object[]{ctx, ctrlPK, userPK});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _changeCtrlUnit(ctx, ctrlPK, userPK);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _changeCtrlUnit(Context ctx, IObjectPK ctrlPK, IObjectPK userPK) throws BOSException, EASBizException;

    public void createUserByPerson(Context ctx, IObjectPK personId) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("e6bdbb03-010c-1000-e000-0001c0a81293"), new Object[]{ctx, personId});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _createUserByPerson(ctx, personId);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _createUserByPerson(Context ctx, IObjectPK personId) throws BOSException, EASBizException;

    public void batchCreateUserByPerson(Context ctx, List personIDList) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("43413587-0117-1000-e000-0008c0a812db"), new Object[]{ctx, personIDList});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _batchCreateUserByPerson(ctx, personIDList);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _batchCreateUserByPerson(Context ctx, List personIDList) throws BOSException, EASBizException;

    public void updateLoginAuthorWay(Context ctx, List userIdList, LoginAuthorWayEnum loginAuthorWay) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("7ebe0706-010f-1000-e000-0003c0a812eb"), new Object[]{ctx, userIdList, loginAuthorWay});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _updateLoginAuthorWay(ctx, userIdList, loginAuthorWay);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _updateLoginAuthorWay(Context ctx, List userIdList, LoginAuthorWayEnum loginAuthorWay) throws BOSException, EASBizException;

    public void callBackUser(Context ctx, List userIdList) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("984d430c-010f-1000-e000-0003c0a812eb"), new Object[]{ctx, userIdList});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _callBackUser(ctx, userIdList);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _callBackUser(Context ctx, List userIdList) throws BOSException, EASBizException;

    public boolean haveThisNumber(Context ctx, String roleNumber) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("d6563bfa-010f-1000-e000-0002c0a812eb"), new Object[]{ctx, roleNumber});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_haveThisNumber(ctx, roleNumber);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
            return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract boolean _haveThisNumber(Context ctx, String roleNumber) throws BOSException, EASBizException;

    public UserInfo getUserInfoByNumber(Context ctx, String userNumber) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("d6563bfa-010f-1000-e000-0003c0a812eb"), new Object[]{ctx, userNumber});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            UserInfo retValue = (UserInfo)_getUserInfoByNumber(ctx, userNumber);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (UserInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract IObjectValue _getUserInfoByNumber(Context ctx, String userNumber) throws BOSException, EASBizException;

    public UserCollection notOrgRangeUseCol(Context ctx, OrgRangeType orgRangeType) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("5c261403-0110-1000-e000-0005c0a812eb"), new Object[]{ctx, orgRangeType});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            UserCollection retValue = (UserCollection)_notOrgRangeUseCol(ctx, orgRangeType);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (UserCollection)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract IObjectCollection _notOrgRangeUseCol(Context ctx, OrgRangeType orgRangeType) throws BOSException, EASBizException;

    public void setUserLoginAuthWay(Context ctx, List userID, LoginAuthorWayEnum authWay) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("06ddd81d-0114-1000-e000-0008c0a812db"), new Object[]{ctx, userID, authWay});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _setUserLoginAuthWay(ctx, userID, authWay);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _setUserLoginAuthWay(Context ctx, List userID, LoginAuthorWayEnum authWay) throws BOSException, EASBizException;

    public void setRegisterResult(Context ctx, List userID, boolean isCertified) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("06ddd81d-0114-1000-e000-000ac0a812db"), new Object[]{ctx, userID, new Boolean(isCertified)});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _setRegisterResult(ctx, userID, isCertified);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _setRegisterResult(Context ctx, List userID, boolean isCertified) throws BOSException, EASBizException;

    public void saveRoleInfo(Context ctx, UserInfo userInfo, FullOrgUnitCollection fullOrgUnit, String roleID) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("43413587-0117-1000-e000-0009c0a812db"), new Object[]{ctx, userInfo, fullOrgUnit, roleID});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _saveRoleInfo(ctx, userInfo, fullOrgUnit, roleID);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _saveRoleInfo(Context ctx, UserInfo userInfo, FullOrgUnitCollection fullOrgUnit, String roleID) throws BOSException, EASBizException;

    public void updateAndDeleteOrgPermAndRole(Context ctx, IObjectPK pk, UserInfo userInfo, boolean isUpdatePassword, String orgIds) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("1bb12d9f-10c8-484a-8336-eb3d4821525c"), new Object[]{ctx, pk, userInfo, new Boolean(isUpdatePassword), orgIds});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _updateAndDeleteOrgPermAndRole(ctx, pk, userInfo, isUpdatePassword, orgIds);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _updateAndDeleteOrgPermAndRole(Context ctx, IObjectPK pk, IObjectValue userInfo, boolean isUpdatePassword, String orgIds) throws BOSException, EASBizException;

    public void deleteRelativePermAndRole(Context ctx, IObjectPK userPK, Collection orgIdCol) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("8cffeb73-954f-4ef1-946d-da2d9a502b2f"), new Object[]{ctx, userPK, orgIdCol});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _deleteRelativePermAndRole(ctx, userPK, orgIdCol);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _deleteRelativePermAndRole(Context ctx, IObjectPK userPK, Collection orgIdCol) throws BOSException, EASBizException;

    public void update(Context ctx, IObjectPK pk, IObjectValue model, boolean isUpdatePassword, boolean isUpdateOrgRange) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("efb2357e-d204-4523-8b95-17886cc55a11"), new Object[]{ctx, pk, model, new Boolean(isUpdatePassword), new Boolean(isUpdateOrgRange)});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _update(ctx, pk, model, isUpdatePassword, isUpdateOrgRange);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _update(Context ctx, IObjectPK pk, IObjectValue model, boolean isUpdatePassword, boolean isUpdateOrgRange) throws BOSException, EASBizException;

    public boolean matchPassword(Context ctx, String userNumber, String password) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("6fe955cb-9034-474b-bfd1-3eceadf80f43"), new Object[]{ctx, userNumber, password});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            boolean retValue = (boolean)_matchPassword(ctx, userNumber, password);
            svcCtx.setMethodReturnValue(new Boolean(retValue));
            }
            invokeServiceAfter(svcCtx);
            return ((Boolean)svcCtx.getMethodReturnValue()).booleanValue();
        } catch (BOSException ex) {
            throw ex;
        } catch (EASBizException ex0) {
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract boolean _matchPassword(Context ctx, String userNumber, String password) throws BOSException, EASBizException;

    public String[][] findUserByAD(Context ctx, String[][] orgs) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("4db7478f-7201-4a2c-ac84-3368e72b0b1d"), new Object[]{ctx, orgs});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            String[][] retValue = (String[][])_findUserByAD(ctx, orgs);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (String[][])svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract String[][] _findUserByAD(Context ctx, String[][] orgs) throws BOSException, EASBizException;

    public void batchCreateUserByPerson(Context ctx, List personIDList, String password) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("a340af57-b02a-4a68-b2f2-f76e6f56c7d9"), new Object[]{ctx, personIDList, password});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _batchCreateUserByPerson(ctx, personIDList, password);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _batchCreateUserByPerson(Context ctx, List personIDList, String password) throws BOSException, EASBizException;

    public IObjectPK addnew(Context ctx, UserInfo userInfo, List roleIdList) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("b13de141-9dde-4e0d-837d-7f1c55889d2f"), new Object[]{ctx, userInfo, roleIdList});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            IObjectPK retValue = (IObjectPK)_addnew(ctx, userInfo, roleIdList);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (IObjectPK)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract IObjectPK _addnew(Context ctx, UserInfo userInfo, List roleIdList) throws BOSException, EASBizException;

    public void saveRoleInfo(Context ctx, UserInfo userInfo, FullOrgUnitCollection fullOrgUnitCol, RoleCollection roleCol) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("8be51951-ee43-4cd4-9692-a11f63c6c291"), new Object[]{ctx, userInfo, fullOrgUnitCol, roleCol});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _saveRoleInfo(ctx, userInfo, fullOrgUnitCol, roleCol);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _saveRoleInfo(Context ctx, UserInfo userInfo, FullOrgUnitCollection fullOrgUnitCol, RoleCollection roleCol) throws BOSException, EASBizException;

    public PersonInfo getPersonWithContact(Context ctx, PersonInfo personInfo) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("9190539e-13ca-4add-85a3-0abc0efaafa8"), new Object[]{ctx, personInfo});
            invokeServiceBefore(svcCtx);
            if(!svcCtx.invokeBreak()) {
            PersonInfo retValue = (PersonInfo)_getPersonWithContact(ctx, personInfo);
            svcCtx.setMethodReturnValue(retValue);
            }
            invokeServiceAfter(svcCtx);
            return (PersonInfo)svcCtx.getMethodReturnValue();
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract IObjectValue _getPersonWithContact(Context ctx, IObjectValue personInfo) throws BOSException, EASBizException;

    public void createWeiBoUser(Context ctx, String email, String personId) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("8f6d1164-c601-4b12-b1cf-50495149a86c"), new Object[]{ctx, email, personId});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _createWeiBoUser(ctx, email, personId);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _createWeiBoUser(Context ctx, String email, String personId) throws BOSException, EASBizException;

    public void update(Context ctx, IObjectPK pk, UserInfo value, UserUpdateParam param) throws BOSException, EASBizException
    {
        try {
            ServiceContext svcCtx = createServiceContext(new MetaDataPK("97b9c480-b67f-4652-87ca-386be223a9fa"), new Object[]{ctx, pk, value, param});
            invokeServiceBefore(svcCtx);
              if(!svcCtx.invokeBreak()) {
            _update(ctx, pk, value, param);
            }
            invokeServiceAfter(svcCtx);
        } catch (BOSException ex) {
            this.setRollbackOnly();
            throw ex;
        } catch (EASBizException ex0) {
            this.setRollbackOnly();
            throw ex0;
        } finally {
            super.cleanUpServiceState();
        }
    }
    protected abstract void _update(Context ctx, IObjectPK pk, IObjectValue value, UserUpdateParam param) throws BOSException, EASBizException;

    public ObjectBaseCollection getObjectBaseCollection (Context ctx) throws BOSException
    {
    	return (ObjectBaseCollection)(getUserCollection(ctx).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (ObjectBaseCollection)(getUserCollection(ctx, view).cast(ObjectBaseCollection.class));
    }
    public ObjectBaseCollection getObjectBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (ObjectBaseCollection)(getUserCollection(ctx, oql).cast(ObjectBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx) throws BOSException
    {
    	return (CoreBaseCollection)(getUserCollection(ctx).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, EntityViewInfo view) throws BOSException
    {
    	return (CoreBaseCollection)(getUserCollection(ctx, view).cast(CoreBaseCollection.class));
    }
    public CoreBaseCollection getCoreBaseCollection (Context ctx, String oql) throws BOSException
    {
    	return (CoreBaseCollection)(getUserCollection(ctx, oql).cast(CoreBaseCollection.class));
    }
}