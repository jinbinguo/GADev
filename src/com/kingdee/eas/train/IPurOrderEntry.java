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

public interface IPurOrderEntry extends ICoreBillEntryBase
{
    public PurOrderEntryInfo getPurOrderEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PurOrderEntryInfo getPurOrderEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PurOrderEntryInfo getPurOrderEntryInfo(String oql) throws BOSException, EASBizException;
    public PurOrderEntryCollection getPurOrderEntryCollection() throws BOSException;
    public PurOrderEntryCollection getPurOrderEntryCollection(EntityViewInfo view) throws BOSException;
    public PurOrderEntryCollection getPurOrderEntryCollection(String oql) throws BOSException;
}