package com.kingdee.eas.scm.cal;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.scm.common.IManualWriteoffCommonFacade;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.scm.cal.info.ReturnInfo;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.scm.cal.info.DevolveWriteOffResultInfo;
import com.kingdee.bos.framework.*;
import java.util.HashMap;

public interface IDevolveWriteOffFacade extends IManualWriteoffCommonFacade
{
    public ReturnInfo devolveWriteOff(DevolveWriteOffResultInfo info) throws BOSException, EASBizException;
    public boolean inverseWriteOff(String[] ids) throws BOSException, EASBizException;
    public boolean updateMaterialReqBill(String costAdjustId) throws BOSException, EASBizException;
    public ReturnInfo batchDevolveWriteOff(HashMap hashInfo) throws BOSException, EASBizException;
}