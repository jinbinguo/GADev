package com.kingdee.eas.train.app;

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
import com.kingdee.eas.train.PurRequestCollection;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.train.PurRequestInfo;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface PurRequestController extends CoreBillBaseController
{
    public PurRequestCollection getPurRequestCollection(Context ctx) throws BOSException, RemoteException;
    public PurRequestCollection getPurRequestCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public PurRequestCollection getPurRequestCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public PurRequestInfo getPurRequestInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public PurRequestInfo getPurRequestInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public PurRequestInfo getPurRequestInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public void audit(Context ctx, PurRequestInfo model) throws BOSException, EASBizException, RemoteException;
    public void unAudit(Context ctx, PurRequestInfo model) throws BOSException, EASBizException, RemoteException;
}