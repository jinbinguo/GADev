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

public interface IPurRequest extends ICoreBillBase
{
    public PurRequestCollection getPurRequestCollection() throws BOSException;
    public PurRequestCollection getPurRequestCollection(EntityViewInfo view) throws BOSException;
    public PurRequestCollection getPurRequestCollection(String oql) throws BOSException;
    public PurRequestInfo getPurRequestInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PurRequestInfo getPurRequestInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PurRequestInfo getPurRequestInfo(String oql) throws BOSException, EASBizException;
    public void audit(PurRequestInfo model) throws BOSException, EASBizException;
    public void unAudit(PurRequestInfo model) throws BOSException, EASBizException;
}