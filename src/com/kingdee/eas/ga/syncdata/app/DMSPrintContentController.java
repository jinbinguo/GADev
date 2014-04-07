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
import com.kingdee.eas.ga.syncdata.DMSPrintContentCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.myframework.template.base.app.SimpleBizBillController;
import com.kingdee.eas.ga.syncdata.DMSPrintContentInfo;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface DMSPrintContentController extends SimpleBizBillController
{
    public DMSPrintContentInfo getDMSPrintContentInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public DMSPrintContentInfo getDMSPrintContentInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public DMSPrintContentInfo getDMSPrintContentInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public DMSPrintContentCollection getDMSPrintContentCollection(Context ctx) throws BOSException, RemoteException;
    public DMSPrintContentCollection getDMSPrintContentCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public DMSPrintContentCollection getDMSPrintContentCollection(Context ctx, String oql) throws BOSException, RemoteException;
}