package com.kingdee.eas.myframework.template.base.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.app.CoreBillBaseController;
import com.kingdee.eas.myframework.template.base.SimpleBizBillCollection;
import com.kingdee.eas.myframework.template.base.SimpleBizBillInfo;
import com.kingdee.bos.framework.batch.BatchExecuteResult;
import com.kingdee.bos.framework.batch.BatchExecuteParamsEntry;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface SimpleBizBillController extends CoreBillBaseController
{
    public SimpleBizBillCollection getSimpleBizBillCollection(Context ctx) throws BOSException, RemoteException;
    public SimpleBizBillCollection getSimpleBizBillCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public SimpleBizBillCollection getSimpleBizBillCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public SimpleBizBillInfo getSimpleBizBillInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public SimpleBizBillInfo getSimpleBizBillInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public SimpleBizBillInfo getSimpleBizBillInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public void audit(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public void unAudit(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public void batchAudit(Context ctx, IObjectPK[] arrayPK) throws BOSException, EASBizException, RemoteException;
    public void batchUnAudit(Context ctx, IObjectPK[] arrayPK) throws BOSException, EASBizException, RemoteException;
    public BatchExecuteResult batchExecuteWithTrans(Context ctx, String methodName, BatchExecuteParamsEntry[] paramEntries) throws BOSException, EASBizException, RemoteException;
}