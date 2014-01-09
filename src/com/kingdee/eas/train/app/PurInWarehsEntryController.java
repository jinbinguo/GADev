package com.kingdee.eas.train.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.train.PurInWarehsEntryCollection;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.app.CoreBillEntryBaseController;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.eas.train.PurInWarehsEntryInfo;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface PurInWarehsEntryController extends CoreBillEntryBaseController
{
    public PurInWarehsEntryInfo getPurInWarehsEntryInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public PurInWarehsEntryInfo getPurInWarehsEntryInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public PurInWarehsEntryInfo getPurInWarehsEntryInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public PurInWarehsEntryCollection getPurInWarehsEntryCollection(Context ctx) throws BOSException, RemoteException;
    public PurInWarehsEntryCollection getPurInWarehsEntryCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public PurInWarehsEntryCollection getPurInWarehsEntryCollection(Context ctx, String oql) throws BOSException, RemoteException;
}