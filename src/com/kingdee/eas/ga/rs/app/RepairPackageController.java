package com.kingdee.eas.ga.rs.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.eas.ga.rs.RepairPackageInfo;
import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.myframework.template.base.app.SimpleDatabaseController;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.ga.rs.RepairPackageCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface RepairPackageController extends SimpleDatabaseController
{
    public RepairPackageInfo getRepairPackageInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public RepairPackageInfo getRepairPackageInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public RepairPackageInfo getRepairPackageInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public RepairPackageCollection getRepairPackageCollection(Context ctx) throws BOSException, RemoteException;
    public RepairPackageCollection getRepairPackageCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public RepairPackageCollection getRepairPackageCollection(Context ctx, String oql) throws BOSException, RemoteException;
}