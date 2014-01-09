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

public interface IPurOrder extends ICoreBillBase
{
    public PurOrderCollection getPurOrderCollection() throws BOSException;
    public PurOrderCollection getPurOrderCollection(EntityViewInfo view) throws BOSException;
    public PurOrderCollection getPurOrderCollection(String oql) throws BOSException;
    public PurOrderInfo getPurOrderInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PurOrderInfo getPurOrderInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PurOrderInfo getPurOrderInfo(String oql) throws BOSException, EASBizException;
    public void audit(PurOrderInfo model) throws BOSException, EASBizException;
    public void unAudit(PurOrderInfo model) throws BOSException, EASBizException;
}