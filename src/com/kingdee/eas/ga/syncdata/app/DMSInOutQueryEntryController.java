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
import com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.ga.syncdata.DMSInOutQueryEntryCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.template.base.app.SimpleBizBillEntryController;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface DMSInOutQueryEntryController extends SimpleBizBillEntryController
{
    public DMSInOutQueryEntryInfo getDMSInOutQueryEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public DMSInOutQueryEntryInfo getDMSInOutQueryEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public DMSInOutQueryEntryInfo getDMSInOutQueryEntryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public DMSInOutQueryEntryCollection getDMSInOutQueryEntryCollection(Context ctx) throws BOSException, RemoteException;
    public DMSInOutQueryEntryCollection getDMSInOutQueryEntryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public DMSInOutQueryEntryCollection getDMSInOutQueryEntryCollection(Context ctx, String oql) throws BOSException, RemoteException;
}