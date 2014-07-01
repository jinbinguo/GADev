package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import java.util.Map;
import java.util.HashMap;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import java.util.Set;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.framework.*;
import java.util.List;
import com.kingdee.eas.scm.im.inv.app.*;

public class SaleIssueBill extends InvBillBase implements ISaleIssueBill
{
    public SaleIssueBill()
    {
        super();
        registerInterface(ISaleIssueBill.class, this);
    }
    public SaleIssueBill(Context ctx)
    {
        super(ctx);
        registerInterface(ISaleIssueBill.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("CC3E933B");
    }
    private SaleIssueBillController getController() throws BOSException
    {
        return (SaleIssueBillController)getBizController();
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@return
     */
    public SaleIssueBillInfo getSaleIssueBillInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getSaleIssueBillInfo(getContext(), pk);
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
    public SaleIssueBillInfo getSaleIssueBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getSaleIssueBillInfo(getContext(), pk, selector);
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
    public SaleIssueBillInfo getSaleIssueBillInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getSaleIssueBillInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public SaleIssueBillCollection getSaleIssueBillCollection() throws BOSException
    {
        try {
            return getController().getSaleIssueBillCollection(getContext());
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
    public SaleIssueBillCollection getSaleIssueBillCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getSaleIssueBillCollection(getContext(), view);
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
    public SaleIssueBillCollection getSaleIssueBillCollection(String oql) throws BOSException
    {
        try {
            return getController().getSaleIssueBillCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�����������ֵ�-User defined method
     *@param idList idList
     *@param param param
     *@return
     */
    public HashMap splitBillByWrittenOffQty(String[] idList, HashMap param) throws BOSException, EASBizException
    {
        try {
            return getController().splitBillByWrittenOffQty(getContext(), idList, param);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *У�����۶���Ԥ�տǰ̨�����쳣�Ͳ���������ʾ-User defined method
     *@param saleOrderIds ��ǰ���ݺ��ĵ�Ϊ���۶��������ҷǳ�����ʱ��id�б�
     */
    public void checkPreReceived(Set saleOrderIds) throws BOSException, EASBizException
    {
        try {
            getController().checkPreReceived(getContext(), saleOrderIds);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�������������˵����۳��ⵥ����������Լ�ʱ���ִ����������õĽӿ�-User defined method
     *@param settledEntriesMap �����¼��Map(keyΪ���۳��ⵥ��¼id,valueΪ���ν�������)
     *@param srcBillIdSet Դ����ID����
     *@return
     */
    public IObjectCollection createNewAuditBillBySettle(Map settledEntriesMap, Set srcBillIdSet) throws BOSException, EASBizException
    {
        try {
            return getController().createNewAuditBillBySettle(getContext(), settledEntriesMap, srcBillIdSet);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����˽����������۳��ⵥ-User defined method
     *@param billIdSet ����ID����
     */
    public void deleteBillByUnSettle(Set billIdSet) throws BOSException, EASBizException
    {
        try {
            getController().deleteBillByUnSettle(getContext(), billIdSet);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�۸���-User defined method
     *@param pk pk
     *@param description description
     *@param list list
     */
    public void changePrice(IObjectPK pk, String description, List list) throws BOSException, EASBizException
    {
        try {
            getController().changePrice(getContext(), pk, description, list);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *�������۳��ⵥ�Ƿ��ܽ��м۸����� ���ܵĻ����ش�����Ϣ-User defined method
     *@param pk pk
     *@return
     */
    public String checkChangePrice(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().checkChangePrice(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}