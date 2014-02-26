package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.auto4s.autoframework.core.AutoBillBase;
import com.kingdee.bos.dao.IObjectPK;
import java.util.Map;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.auto4s.rsm.rs.app.*;
import com.kingdee.eas.auto4s.autoframework.core.IAutoBillBase;

public class RepairWO extends AutoBillBase implements IRepairWO
{
    public RepairWO()
    {
        super();
        registerInterface(IRepairWO.class, this);
    }
    public RepairWO(Context ctx)
    {
        super(ctx);
        registerInterface(IRepairWO.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("FDBE5ECA");
    }
    private RepairWOController getController() throws BOSException
    {
        return (RepairWOController)getBizController();
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public RepairWOCollection getRepairWOCollection() throws BOSException
    {
        try {
            return getController().getRepairWOCollection(getContext());
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
    public RepairWOCollection getRepairWOCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getRepairWOCollection(getContext(), view);
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
    public RepairWOCollection getRepairWOCollection(String oql) throws BOSException
    {
        try {
            return getController().getRepairWOCollection(getContext(), oql);
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
    public RepairWOInfo getRepairWOInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairWOInfo(getContext(), pk);
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
    public RepairWOInfo getRepairWOInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairWOInfo(getContext(), pk, selector);
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
    public RepairWOInfo getRepairWOInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairWOInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ������-User defined method
     *@param model model
     */
    public void uninvalid(RepairWOInfo model) throws BOSException, EASBizException
    {
        try {
            getController().uninvalid(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����-User defined method
     *@param model model
     */
    public void invalid(RepairWOInfo model) throws BOSException, EASBizException
    {
        try {
            getController().invalid(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�ɹ�-User defined method
     *@param model model
     */
    public void assign(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().assign(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ά���ж�-User defined method
     *@param model model
     */
    public void repairBreak(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().repairBreak(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�жϻָ�-User defined method
     *@param model model
     */
    public void breakRestore(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().breakRestore(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�ʼ�-User defined method
     *@param model model
     */
    public void inspect(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().inspect(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ϴ��-User defined method
     *@param model model
     */
    public void wash(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().wash(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��ʱ�Ǽ�-User defined method
     *@param model model
     */
    public void workTime(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().workTime(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����ɹ�-User defined method
     *@param model model
     */
    public void dispatching(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().dispatching(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��ʱ�Ǽ�-User defined method
     *@param model model
     */
    public void timeBooking(IObjectValue model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().timeBooking(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ɾ����д-User defined method
     *@param model model
     */
    public void deleteRepairWO(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().deleteRepairWO(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *BO��ȷ��-User defined method
     *@param model model
     */
    public void isB(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().isB(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *BO����ȷ��-User defined method
     *@param model model
     */
    public void cancelB(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().cancelB(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *׷��ȷ��-User defined method
     *@param model model
     */
    public void addEnter(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().addEnter(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��������-User defined method
     *@param model model
     */
    public void adjust(RepairWOInfo model) throws BOSException
    {
        try {
            getController().adjust(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����-User defined method
     *@param model model
     */
    public void itemIssue(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().itemIssue(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ���ɹ�-User defined method
     *@param cancelMap cancelMap
     */
    public void cancelAssign(Map cancelMap) throws BOSException, RsmRsException, EASBizException
    {
        try {
            getController().cancelAssign(getContext(), cancelMap);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ά������Ƿ��Ѿ�ȫ������-User defined method
     *@param model model
     *@return
     */
    public boolean isAllIssue(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException
    {
        try {
            return getController().isAllIssue(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����ʱ�Ǽ�-User defined method
     *@param paramMap paramMap
     */
    public void unWorkTime(Map paramMap) throws BOSException
    {
        try {
            getController().unWorkTime(getContext(), paramMap);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��ϴ��-User defined method
     *@param model model
     */
    public void unWash(RepairWOInfo model) throws BOSException
    {
        try {
            getController().unWash(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *���ʼ�-User defined method
     *@param paramMap paramMap
     */
    public void unInspect(Map paramMap) throws BOSException, EASBizException
    {
        try {
            getController().unInspect(getContext(), paramMap);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ͬ�����ݣ��汾һ���Լ���-User defined method
     *@param model model
     */
    public void updateRWOVersion(RepairWOInfo model) throws BOSException, EASBizException
    {
        try {
            getController().updateRWOVersion(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *BT783596ԤԼ�ǼǺ󣬿ͻ���������ά�޹���ʱ�� ��ʾ���˳�����ԤԼ���Ƿ�����ά��ԤԼ���������ȷ������ť�� ���Զ�����ά��ԤԼ����ϵͳ�Զ�����ά��ԤԼ�����ɹ����ĵ�һ��BOTP���ɣ� ���������򣩣������ȡ������������ -User defined method
     *@param paramMap paramMap
     *@return
     */
    public Map getRWOFromRepairBooking(Map paramMap) throws BOSException, EASBizException
    {
        try {
            return getController().getRWOFromRepairBooking(getContext(), paramMap);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *���-User defined method
     *@param model model
     */
    public void audit(RepairWOInfo model) throws BOSException
    {
        try {
            getController().audit(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����-User defined method
     *@param model model
     */
    public void unAudit(RepairWOInfo model) throws BOSException
    {
        try {
            getController().unAudit(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}