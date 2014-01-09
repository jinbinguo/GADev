package com.kingdee.eas.myframework.template.base;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.framework.batch.BatchExecuteResult;
import com.kingdee.bos.framework.batch.BatchExecuteParamsEntry;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBillBase;
import com.kingdee.eas.myframework.template.base.app.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.ICoreBillBase;

public abstract class SimpleBizBill extends CoreBillBase implements ISimpleBizBill
{
    public SimpleBizBill()
    {
        super();
        registerInterface(ISimpleBizBill.class, this);
    }
    public SimpleBizBill(Context ctx)
    {
        super(ctx);
        registerInterface(ISimpleBizBill.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("F52D5A11");
    }
    private SimpleBizBillController getController() throws BOSException
    {
        return (SimpleBizBillController)getBizController();
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public SimpleBizBillCollection getSimpleBizBillCollection() throws BOSException
    {
        try {
            return getController().getSimpleBizBillCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@param view ȡ����
     *@return
     */
    public SimpleBizBillCollection getSimpleBizBillCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getSimpleBizBillCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@param oql ȡ����
     *@return
     */
    public SimpleBizBillCollection getSimpleBizBillCollection(String oql) throws BOSException
    {
        try {
            return getController().getSimpleBizBillCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@return
     */
    public SimpleBizBillInfo getSimpleBizBillInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getSimpleBizBillInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@param selector ȡֵ
     *@return
     */
    public SimpleBizBillInfo getSimpleBizBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getSimpleBizBillInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param oql ȡֵ
     *@return
     */
    public SimpleBizBillInfo getSimpleBizBillInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getSimpleBizBillInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *���-User defined method
     *@param pk pk
     */
    public void audit(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            getController().audit(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����-User defined method
     *@param pk pk
     */
    public void unAudit(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            getController().unAudit(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�������-User defined method
     *@param arrayPK ���
     */
    public void batchAudit(IObjectPK[] arrayPK) throws BOSException, EASBizException
    {
        try {
            getController().batchAudit(getContext(), arrayPK);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *���������-User defined method
     *@param arrayPK arrayPK
     */
    public void batchUnAudit(IObjectPK[] arrayPK) throws BOSException, EASBizException
    {
        try {
            getController().batchUnAudit(getContext(), arrayPK);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����ִ�з���(������)-User defined method
     *@param methodName ��������
     *@param paramEntries ��������
     *@return
     */
    public BatchExecuteResult batchExecuteWithTrans(String methodName, BatchExecuteParamsEntry[] paramEntries) throws BOSException, EASBizException
    {
        try {
            return getController().batchExecuteWithTrans(getContext(), methodName, paramEntries);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}