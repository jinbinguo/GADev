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

public interface IPurRequestEntry extends ICoreBillEntryBase
{
    public PurRequestEntryInfo getPurRequestEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PurRequestEntryInfo getPurRequestEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PurRequestEntryInfo getPurRequestEntryInfo(String oql) throws BOSException, EASBizException;
    public PurRequestEntryCollection getPurRequestEntryCollection() throws BOSException;
    public PurRequestEntryCollection getPurRequestEntryCollection(EntityViewInfo view) throws BOSException;
    public PurRequestEntryCollection getPurRequestEntryCollection(String oql) throws BOSException;
}