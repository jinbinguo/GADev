package com.kingdee.eas.ga.rs.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.myframework.template.base.app.SimpleDatabaseController;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.ga.rs.RepairWOBizTypeInfo;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.ga.rs.RepairWOBizTypeCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface RepairWOBizTypeController extends SimpleDatabaseController
{
    public RepairWOBizTypeInfo getRepairWOBizTypeInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public RepairWOBizTypeInfo getRepairWOBizTypeInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public RepairWOBizTypeInfo getRepairWOBizTypeInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public RepairWOBizTypeCollection getRepairWOBizTypeCollection(Context ctx) throws BOSException, RemoteException;
    public RepairWOBizTypeCollection getRepairWOBizTypeCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public RepairWOBizTypeCollection getRepairWOBizTypeCollection(Context ctx, String oql) throws BOSException, RemoteException;
}