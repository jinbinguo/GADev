package com.kingdee.eas.myframework.test;

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

public interface IAAA extends ICoreBillBase
{
    public AAACollection getAAACollection() throws BOSException;
    public AAACollection getAAACollection(EntityViewInfo view) throws BOSException;
    public AAACollection getAAACollection(String oql) throws BOSException;
    public AAAInfo getAAAInfo(IObjectPK pk) throws BOSException, EASBizException;
    public AAAInfo getAAAInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public AAAInfo getAAAInfo(String oql) throws BOSException, EASBizException;
}