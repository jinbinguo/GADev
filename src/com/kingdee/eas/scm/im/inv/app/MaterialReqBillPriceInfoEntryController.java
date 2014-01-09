package com.kingdee.eas.scm.im.inv.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.scm.common.app.SCMBillEntryBaseController;
import com.kingdee.eas.scm.im.inv.MaterialReqBillPriceInfoEntryCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface MaterialReqBillPriceInfoEntryController extends SCMBillEntryBaseController
{
    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection(Context ctx) throws BOSException, RemoteException;
    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection(Context ctx, String oql) throws BOSException, RemoteException;
}