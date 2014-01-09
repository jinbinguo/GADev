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
import com.kingdee.eas.framework.ICoreBillEntryBase;

public interface IPurTestEntry extends ICoreBillEntryBase
{
    public PurTestEntryInfo getPurTestEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PurTestEntryInfo getPurTestEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PurTestEntryInfo getPurTestEntryInfo(String oql) throws BOSException, EASBizException;
    public PurTestEntryCollection getPurTestEntryCollection() throws BOSException;
    public PurTestEntryCollection getPurTestEntryCollection(EntityViewInfo view) throws BOSException;
    public PurTestEntryCollection getPurTestEntryCollection(String oql) throws BOSException;
}