package com.kingdee.eas.train;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.ICoreBillBase;

public interface IPurInWarehs extends ICoreBillBase
{
    public PurInWarehsCollection getPurInWarehsCollection() throws BOSException;
    public PurInWarehsCollection getPurInWarehsCollection(EntityViewInfo view) throws BOSException;
    public PurInWarehsCollection getPurInWarehsCollection(String oql) throws BOSException;
    public PurInWarehsInfo getPurInWarehsInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PurInWarehsInfo getPurInWarehsInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PurInWarehsInfo getPurInWarehsInfo(String oql) throws BOSException, EASBizException;
}