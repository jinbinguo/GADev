package com.kingdee.eas.scm.cal.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.scm.cal.info.ReturnInfo;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.scm.cal.info.DevolveWriteOffResultInfo;
import com.kingdee.bos.framework.*;
import java.util.HashMap;
import com.kingdee.eas.scm.common.app.ManualWriteoffCommonFacadeController;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface DevolveWriteOffFacadeController extends ManualWriteoffCommonFacadeController
{
    public ReturnInfo devolveWriteOff(Context ctx, DevolveWriteOffResultInfo info) throws BOSException, EASBizException, RemoteException;
    public boolean inverseWriteOff(Context ctx, String[] ids) throws BOSException, EASBizException, RemoteException;
    public boolean updateMaterialReqBill(Context ctx, String costAdjustId) throws BOSException, EASBizException, RemoteException;
    public ReturnInfo batchDevolveWriteOff(Context ctx, HashMap hashInfo) throws BOSException, EASBizException, RemoteException;
}