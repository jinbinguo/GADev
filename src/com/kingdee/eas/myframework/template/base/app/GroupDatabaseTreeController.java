package com.kingdee.eas.myframework.template.base.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.myframework.template.base.GroupDatabaseTreeCollection;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.myframework.template.base.GroupDatabaseTreeInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.app.TreeBaseController;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface GroupDatabaseTreeController extends TreeBaseController
{
    public GroupDatabaseTreeInfo getGroupDatabaseTreeInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public GroupDatabaseTreeInfo getGroupDatabaseTreeInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public GroupDatabaseTreeInfo getGroupDatabaseTreeInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public GroupDatabaseTreeCollection getGroupDatabaseTreeCollection(Context ctx) throws BOSException, RemoteException;
    public GroupDatabaseTreeCollection getGroupDatabaseTreeCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public GroupDatabaseTreeCollection getGroupDatabaseTreeCollection(Context ctx, String oql) throws BOSException, RemoteException;
}