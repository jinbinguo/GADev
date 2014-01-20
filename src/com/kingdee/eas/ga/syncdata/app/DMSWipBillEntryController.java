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
import com.kingdee.eas.ga.syncdata.DMSWipBillEntryInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.ga.syncdata.DMSWipBillEntryCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.template.base.app.SimpleBizBillEntryController;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface DMSWipBillEntryController extends SimpleBizBillEntryController
{
    public DMSWipBillEntryInfo getDMSWipBillEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public DMSWipBillEntryInfo getDMSWipBillEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public DMSWipBillEntryInfo getDMSWipBillEntryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public DMSWipBillEntryCollection getDMSWipBillEntryCollection(Context ctx) throws BOSException, RemoteException;
    public DMSWipBillEntryCollection getDMSWipBillEntryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public DMSWipBillEntryCollection getDMSWipBillEntryCollection(Context ctx, String oql) throws BOSException, RemoteException;
}