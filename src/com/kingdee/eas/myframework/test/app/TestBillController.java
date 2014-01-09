package com.kingdee.eas.myframework.test.app;

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
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.myframework.test.TestBillInfo;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.test.TestBillCollection;
import com.kingdee.eas.myframework.template.base.app.SimpleBizBillController;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface TestBillController extends SimpleBizBillController
{
    public TestBillInfo getTestBillInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public TestBillInfo getTestBillInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public TestBillInfo getTestBillInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public TestBillCollection getTestBillCollection(Context ctx) throws BOSException, RemoteException;
    public TestBillCollection getTestBillCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public TestBillCollection getTestBillCollection(Context ctx, String oql) throws BOSException, RemoteException;
}