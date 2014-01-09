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
import com.kingdee.eas.train.PurOrderCollection;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.train.PurOrderInfo;
import com.kingdee.bos.framework.*;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface PurOrderController extends CoreBillBaseController
{
    public PurOrderCollection getPurOrderCollection(Context ctx) throws BOSException, RemoteException;
    public PurOrderCollection getPurOrderCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public PurOrderCollection getPurOrderCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public PurOrderInfo getPurOrderInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public PurOrderInfo getPurOrderInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public PurOrderInfo getPurOrderInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public void audit(Context ctx, PurOrderInfo model) throws BOSException, EASBizException, RemoteException;
    public void unAudit(Context ctx, PurOrderInfo model) throws BOSException, EASBizException, RemoteException;
}