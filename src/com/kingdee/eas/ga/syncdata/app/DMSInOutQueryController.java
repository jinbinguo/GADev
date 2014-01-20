package com.kingdee.eas.ga.syncdata.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryInfo;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryCollection;
import com.kingdee.eas.myframework.template.base.app.SimpleBizBillController;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface DMSInOutQueryController extends SimpleBizBillController
{
    public DMSInOutQueryInfo getDMSInOutQueryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public DMSInOutQueryInfo getDMSInOutQueryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public DMSInOutQueryInfo getDMSInOutQueryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public DMSInOutQueryCollection getDMSInOutQueryCollection(Context ctx) throws BOSException, RemoteException;
    public DMSInOutQueryCollection getDMSInOutQueryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public DMSInOutQueryCollection getDMSInOutQueryCollection(Context ctx, String oql) throws BOSException, RemoteException;
}