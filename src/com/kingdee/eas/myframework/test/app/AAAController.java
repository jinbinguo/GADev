package com.kingdee.eas.myframework.test.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.myframework.test.AAACollection;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.app.CoreBillBaseController;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.test.AAAInfo;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface AAAController extends CoreBillBaseController
{
    public AAACollection getAAACollection(Context ctx) throws BOSException, RemoteException;
    public AAACollection getAAACollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public AAACollection getAAACollection(Context ctx, String oql) throws BOSException, RemoteException;
    public AAAInfo getAAAInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public AAAInfo getAAAInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public AAAInfo getAAAInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
}