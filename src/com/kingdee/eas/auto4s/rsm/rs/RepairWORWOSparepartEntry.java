package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.eas.framework.CoreBillEntryBase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import java.math.BigDecimal;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.eas.framework.ICoreBillEntryBase;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.eas.auto4s.rsm.rs.app.*;

public class RepairWORWOSparepartEntry extends CoreBillEntryBase implements IRepairWORWOSparepartEntry
{
    public RepairWORWOSparepartEntry()
    {
        super();
        registerInterface(IRepairWORWOSparepartEntry.class, this);
    }
    public RepairWORWOSparepartEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IRepairWORWOSparepartEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("1BEE6B08");
    }
    private RepairWORWOSparepartEntryController getController() throws BOSException
    {
        return (RepairWORWOSparepartEntryController)getBizController();
    }
    /**
     *exists-System defined method
     *@param pk pk
     *@return
     */
    public boolean exists(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().exists(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *exists-System defined method
     *@param filter filter
     *@return
     */
    public boolean exists(FilterInfo filter) throws BOSException, EASBizException
    {
        try {
            return getController().exists(getContext(), filter);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *exists-System defined method
     *@param oql oql
     *@return
     */
    public boolean exists(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().exists(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getValue-System defined method
     *@param pk pk
     *@return
     */
    public RepairWORWOSparepartEntryInfo getRepairWORWOSparepartEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairWORWOSparepartEntryInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getValue-System defined method
     *@param pk pk
     *@param selector selector
     *@return
     */
    public RepairWORWOSparepartEntryInfo getRepairWORWOSparepartEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairWORWOSparepartEntryInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getValue-System defined method
     *@param oql oql
     *@return
     */
    public RepairWORWOSparepartEntryInfo getRepairWORWOSparepartEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getRepairWORWOSparepartEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *addnew-System defined method
     *@param model model
     *@return
     */
    public IObjectPK addnew(RepairWORWOSparepartEntryInfo model) throws BOSException, EASBizException
    {
        try {
            return getController().addnew(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *addnew-System defined method
     *@param pk pk
     *@param model model
     */
    public void addnew(IObjectPK pk, RepairWORWOSparepartEntryInfo model) throws BOSException, EASBizException
    {
        try {
            getController().addnew(getContext(), pk, model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *update-System defined method
     *@param pk pk
     *@param model model
     */
    public void update(IObjectPK pk, RepairWORWOSparepartEntryInfo model) throws BOSException, EASBizException
    {
        try {
            getController().update(getContext(), pk, model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *updatePartial-System defined method
     *@param model model
     *@param selector selector
     */
    public void updatePartial(RepairWORWOSparepartEntryInfo model, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            getController().updatePartial(getContext(), model, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *updateBigObject-System defined method
     *@param pk pk
     *@param model model
     */
    public void updateBigObject(IObjectPK pk, RepairWORWOSparepartEntryInfo model) throws BOSException
    {
        try {
            getController().updateBigObject(getContext(), pk, model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *delete-System defined method
     *@param pk pk
     */
    public void delete(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            getController().delete(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getPKList-System defined method
     *@return
     */
    public IObjectPK[] getPKList() throws BOSException, EASBizException
    {
        try {
            return getController().getPKList(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getPKList-System defined method
     *@param oql oql
     *@return
     */
    public IObjectPK[] getPKList(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getPKList(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getPKList-System defined method
     *@param filter filter
     *@param sorter sorter
     *@return
     */
    public IObjectPK[] getPKList(FilterInfo filter, SorterItemCollection sorter) throws BOSException, EASBizException
    {
        try {
            return getController().getPKList(getContext(), filter, sorter);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getCollection-System defined method
     *@return
     */
    public RepairWORWOSparepartEntryCollection getRepairWORWOSparepartEntryCollection() throws BOSException
    {
        try {
            return getController().getRepairWORWOSparepartEntryCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getCollection-System defined method
     *@param view view
     *@return
     */
    public RepairWORWOSparepartEntryCollection getRepairWORWOSparepartEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getRepairWORWOSparepartEntryCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getCollection-System defined method
     *@param oql oql
     *@return
     */
    public RepairWORWOSparepartEntryCollection getRepairWORWOSparepartEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getRepairWORWOSparepartEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *delete-System defined method
     *@param filter filter
     *@return
     */
    public IObjectPK[] delete(FilterInfo filter) throws BOSException, EASBizException
    {
        try {
            return getController().delete(getContext(), filter);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *delete-System defined method
     *@param oql oql
     *@return
     */
    public IObjectPK[] delete(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().delete(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *delete-System defined method
     *@param arrayPK arrayPK
     */
    public void delete(IObjectPK[] arrayPK) throws BOSException, EASBizException
    {
        try {
            getController().delete(getContext(), arrayPK);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *getResourceId-User defined method
     *@param oql 取源单据id集合
     *@return
     */
    public IRowSet getResourceId(String oql) throws BOSException
    {
        try {
            return getController().getResourceId(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *更新数量-User defined method
     *@param issueQty 已出库数量
     *@param entryId 分录Id
     *@param noIssueQty 未出库数量
     *@param transType 事务类型
     *@param oprateType 操作类型
     */
    public void updateQty(BigDecimal issueQty, String entryId, BigDecimal noIssueQty, String transType, String oprateType) throws BOSException
    {
        try {
            getController().updateQty(getContext(), issueQty, entryId, noIssueQty, transType, oprateType);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *出库时，不能超过未出库数量； 退货时，不能超过己出库数量；-User defined method
     *@param qty 本次出库(退库)数量
     *@param entryID 配件分录id
     *@param transType 事务类型
     *@param oprateType 操作类型
     */
    public void checkQty(BigDecimal qty, String entryID, String transType, String oprateType) throws BOSException, RsmRsException
    {
        try {
            getController().checkQty(getContext(), qty, entryID, transType, oprateType);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}