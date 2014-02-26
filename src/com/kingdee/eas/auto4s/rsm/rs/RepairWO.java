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
     *取集合-System defined method
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
     *取集合-System defined method
     *@param view 取集合
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
     *取集合-System defined method
     *@param oql 取集合
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
     *取值-System defined method
     *@param pk 取值
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
     *取值-System defined method
     *@param pk 取值
     *@param selector 取值
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
     *取值-System defined method
     *@param oql 取值
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
     *取消作废-User defined method
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
     *作废-User defined method
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
     *派工-User defined method
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
     *维修中断-User defined method
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
     *中断恢复-User defined method
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
     *质检-User defined method
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
     *洗车-User defined method
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
     *工时登记-User defined method
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
     *工单派工-User defined method
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
     *工时登记-User defined method
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
     *删除反写-User defined method
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
     *BO件确认-User defined method
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
     *BO件反确认-User defined method
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
     *追加确认-User defined method
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
     *整单调整-User defined method
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
     *出库-User defined method
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
     *取消派工-User defined method
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
     *维修配件是否已经全部出库-User defined method
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
     *反工时登记-User defined method
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
     *反洗车-User defined method
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
     *反质检-User defined method
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
     *同步数据，版本一致性检验-User defined method
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
     *BT783596预约登记后，客户进厂新增维修工单时， 提示：此车辆已预约，是否引入维修预约单？点击“确定”按钮， 则自动引入维修预约单（系统自动根据维修预约单生成工单的第一个BOTP生成， 按编码排序），点击“取消”，则不引入 -User defined method
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
     *审核-User defined method
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
     *反审核-User defined method
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