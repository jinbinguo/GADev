package com.kingdee.eas.ga.rs;

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
import com.kingdee.eas.myframework.template.base.ISimpleDatabase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface IW extends ISimpleDatabase
{
    public WInfo getWInfo(IObjectPK pk) throws BOSException, EASBizException;
    public WInfo getWInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public WInfo getWInfo(String oql) throws BOSException, EASBizException;
    public WCollection getWCollection() throws BOSException;
    public WCollection getWCollection(EntityViewInfo view) throws BOSException;
    public WCollection getWCollection(String oql) throws BOSException;
}