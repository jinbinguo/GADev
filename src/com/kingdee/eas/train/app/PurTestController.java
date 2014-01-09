package com.kingdee.eas.train.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.app.CoreBillBaseController;
import com.kingdee.eas.train.PurTestInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.train.PurTestCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface PurTestController extends CoreBillBaseController
{
    public PurTestCollection getPurTestCollection(Context ctx) throws BOSException, RemoteException;
    public PurTestCollection getPurTestCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public PurTestCollection getPurTestCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public PurTestInfo getPurTestInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public PurTestInfo getPurTestInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public PurTestInfo getPurTestInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public void audit(Context ctx, PurTestInfo model) throws BOSException, RemoteException;
    public void test1(Context ctx) throws BOSException, RemoteException;
    public void test2(Context ctx) throws BOSException, RemoteException;
    public void test3(Context ctx) throws BOSException, RemoteException;
    public void test4(Context ctx) throws BOSException, RemoteException;
    public void test5(Context ctx) throws BOSException, RemoteException;
}