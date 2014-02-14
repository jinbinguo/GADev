package com.kingdee.eas.myframework.common.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.bos.framework.*;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface SQLExecutorController extends BizController
{
    public void execute(Context ctx, String sql) throws BOSException, EASBizException, RemoteException;
    public IRowSet executeQuery(Context ctx, String sql) throws BOSException, EASBizException, RemoteException;
}