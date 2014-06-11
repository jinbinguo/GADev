package com.kingdee.eas.ga.syncdata;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.framework.*;

public interface IDMSSyncFacade extends IBizCtrl
{
    public void ImportCustProfiles(int maxRecords) throws BOSException;
    public void ImportVehicleTypes(int maxRecords) throws BOSException;
    public void ImportVehicleProfiles(int maxRecords) throws BOSException;
}