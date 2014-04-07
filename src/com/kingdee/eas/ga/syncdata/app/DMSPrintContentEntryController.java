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
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.template.base.app.SimpleBizBillEntryController;
import com.kingdee.eas.ga.syncdata.DMSPrintContentEntryInfo;
import com.kingdee.eas.ga.syncdata.DMSPrintContentEntryCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface DMSPrintContentEntryController extends SimpleBizBillEntryController
{
    public DMSPrintContentEntryInfo getDMSPrintContentEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public DMSPrintContentEntryInfo getDMSPrintContentEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public DMSPrintContentEntryInfo getDMSPrintContentEntryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public DMSPrintContentEntryCollection getDMSPrintContentEntryCollection(Context ctx) throws BOSException, RemoteException;
    public DMSPrintContentEntryCollection getDMSPrintContentEntryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public DMSPrintContentEntryCollection getDMSPrintContentEntryCollection(Context ctx, String oql) throws BOSException, RemoteException;
}