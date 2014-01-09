package com.kingdee.eas.myframework.test.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.eas.myframework.test.TestGroupInfo;
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
import com.kingdee.eas.myframework.template.base.app.GroupDatabaseController;
import com.kingdee.eas.myframework.test.TestGroupCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface TestGroupController extends GroupDatabaseController
{
    public TestGroupInfo getTestGroupInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public TestGroupInfo getTestGroupInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public TestGroupInfo getTestGroupInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public TestGroupCollection getTestGroupCollection(Context ctx) throws BOSException, RemoteException;
    public TestGroupCollection getTestGroupCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public TestGroupCollection getTestGroupCollection(Context ctx, String oql) throws BOSException, RemoteException;
}