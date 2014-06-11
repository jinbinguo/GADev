package com.kingdee.eas.ga.syncdata.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.framework.*;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface DMSSyncFacadeController extends BizController
{
    public void ImportCustProfiles(Context ctx, int maxRecords) throws BOSException, RemoteException;
    public void ImportVehicleTypes(Context ctx, int maxRecords) throws BOSException, RemoteException;
    public void ImportVehicleProfiles(Context ctx, int maxRecords) throws BOSException, RemoteException;
}