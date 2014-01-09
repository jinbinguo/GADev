package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import java.util.Map;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import java.util.Set;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo;
import com.kingdee.eas.scm.im.inv.app.*;

public class MaterialReqBill extends InvBillBase implements IMaterialReqBill
{
    public MaterialReqBill()
    {
        super();
        registerInterface(IMaterialReqBill.class, this);
    }
    public MaterialReqBill(Context ctx)
    {
        super(ctx);
        registerInterface(IMaterialReqBill.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("500AB75E");
    }
    private MaterialReqBillController getController() throws BOSException
    {
        return (MaterialReqBillController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public MaterialReqBillInfo getMaterialReqBillInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialReqBillInfo(getContext(), pk);
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
    public MaterialReqBillInfo getMaterialReqBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialReqBillInfo(getContext(), pk, selector);
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
    public MaterialReqBillInfo getMaterialReqBillInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialReqBillInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public MaterialReqBillCollection getMaterialReqBillCollection() throws BOSException
    {
        try {
            return getController().getMaterialReqBillCollection(getContext());
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
    public MaterialReqBillCollection getMaterialReqBillCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getMaterialReqBillCollection(getContext(), view);
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
    public MaterialReqBillCollection getMaterialReqBillCollection(String oql) throws BOSException
    {
        try {
            return getController().getMaterialReqBillCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *根据当前登陆用户得到默认成本中心-User defined method
     *@param userID userID
     *@return
     */
    public CostCenterOrgUnitInfo getDefaultCostCenter(String userID) throws BOSException, EASBizException
    {
        try {
            return getController().getDefaultCostCenter(getContext(), userID);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *根据用户得到可选的成本中心-User defined method
     *@param userID userID
     *@return
     */
    public Set getAdminOrgsHashSet(String userID) throws BOSException, EASBizException
    {
        try {
            return getController().getAdminOrgsHashSet(getContext(), userID);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *是否可以删除-User defined method
     *@param billId 单据Id
     *@return
     */
    public boolean deleteable(String billId) throws BOSException, EASBizException
    {
        try {
            return getController().deleteable(getContext(), billId);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *制造接口，由制造定义-User defined method
     *@param storageOrgUnitID storageOrgUnitID
     *@param materialID materialID
     *@return
     */
    public String getLotsByMaterial(String storageOrgUnitID, String materialID) throws BOSException, EASBizException
    {
        try {
            return getController().getLotsByMaterial(getContext(), storageOrgUnitID, materialID);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *制造审核-User defined method
     *@param pk pk
     */
    public void bachLoopAduit(IObjectPK[] pk) throws BOSException, EASBizException
    {
        try {
            getController().bachLoopAduit(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *获取当前物料的价格信息-User defined method
     *@param priceInfos com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryCollection
     *@param orgUnits 单头供方组织和虚需方组织
     *@return
     */
    public MaterialReqBillPriceInfoEntryCollection getPriceInfo(MaterialReqBillPriceInfoEntryCollection priceInfos, Map orgUnits) throws BOSException, EASBizException
    {
        try {
            return getController().getPriceInfo(getContext(), priceInfos, orgUnits);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}