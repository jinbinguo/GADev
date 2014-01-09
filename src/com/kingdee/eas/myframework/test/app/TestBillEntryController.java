package com.kingdee.eas.myframework.test.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.eas.myframework.test.TestBillEntryInfo;
import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.template.base.app.SimpleBizBillEntryController;
import com.kingdee.eas.myframework.test.TestBillEntryCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface TestBillEntryController extends SimpleBizBillEntryController
{
    public TestBillEntryInfo getTestBillEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public TestBillEntryInfo getTestBillEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public TestBillEntryInfo getTestBillEntryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public TestBillEntryCollection getTestBillEntryCollection(Context ctx) throws BOSException, RemoteException;
    public TestBillEntryCollection getTestBillEntryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public TestBillEntryCollection getTestBillEntryCollection(Context ctx, String oql) throws BOSException, RemoteException;
}