package com.kingdee.eas.basedata.master.material;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.basedata.master.material.app.*;
import java.util.HashMap;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import java.util.List;

public class MaterialInventory extends MaterialPropertyBase implements IMaterialInventory
{
    public MaterialInventory()
    {
        super();
        registerInterface(IMaterialInventory.class, this);
    }
    public MaterialInventory(Context ctx)
    {
        super(ctx);
        registerInterface(IMaterialInventory.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("557E499F");
    }
    private MaterialInventoryController getController() throws BOSException
    {
        return (MaterialInventoryController)getBizController();
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@return
     */
    public MaterialInventoryInfo getMaterialInventoryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialInventoryInfo(getContext(), pk);
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
    public MaterialInventoryInfo getMaterialInventoryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialInventoryInfo(getContext(), pk, selector);
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
    public MaterialInventoryInfo getMaterialInventoryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getMaterialInventoryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public MaterialInventoryCollection getMaterialInventoryCollection() throws BOSException
    {
        try {
            return getController().getMaterialInventoryCollection(getContext());
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
    public MaterialInventoryCollection getMaterialInventoryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getMaterialInventoryCollection(getContext(), view);
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
    public MaterialInventoryCollection getMaterialInventoryCollection(String oql) throws BOSException
    {
        try {
            return getController().getMaterialInventoryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��һ��-User defined method
     *@param pk pk
     *@return
     */
    public MaterialInventoryInfo first(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().first(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��һ��-User defined method
     *@param pk pk
     *@return
     */
    public MaterialInventoryInfo previous(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().previous(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��һ��-User defined method
     *@param pk pk
     *@return
     */
    public MaterialInventoryInfo next(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().next(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *���һ��-User defined method
     *@param pk pk
     *@return
     */
    public MaterialInventoryInfo last(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().last(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����޸ļ��-User defined method
     *@param pk pk
     *@param newInfo newInfo
     */
    public void updateCheck(IObjectPK pk, MaterialInventoryInfo newInfo) throws BOSException, EASBizException
    {
        try {
            getController().updateCheck(getContext(), pk, newInfo);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��ȡ�������-User defined method
     *@param matid ����id
     *@param ouid �����֯id
     *@return
     */
    public MaterialInventoryInfo getInventoryInfo(String matid, String ouid) throws BOSException, EASBizException
    {
        try {
            return getController().getInventoryInfo(getContext(), matid, ouid);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�õ���ָ�������֯������Ե����ϼ���-User defined method
     *@param materialIDs ����ID����
     *@param inventoryID �����֯ID
     *@return
     */
    public List getMaterialsExistInvInfo(String[] materialIDs, String inventoryID) throws BOSException
    {
        try {
            return getController().getMaterialsExistInvInfo(getContext(), materialIDs, inventoryID);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�������������Ƿ��ж�Ӧ��֯����֯����-User defined method
     *@param hashMap hashMap
     *@return
     */
    public HashMap verifyMaterialInvInfos(HashMap hashMap) throws BOSException
    {
        try {
            return getController().verifyMaterialInvInfos(getContext(), hashMap);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}