package com.kingdee.eas.ga.syncdata;

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

public interface ISyncLogEntry extends ICoreBillEntryBase
{
    public SyncLogEntryInfo getSyncLogEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public SyncLogEntryInfo getSyncLogEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public SyncLogEntryInfo getSyncLogEntryInfo(String oql) throws BOSException, EASBizException;
    public SyncLogEntryCollection getSyncLogEntryCollection() throws BOSException;
    public SyncLogEntryCollection getSyncLogEntryCollection(EntityViewInfo view) throws BOSException;
    public SyncLogEntryCollection getSyncLogEntryCollection(String oql) throws BOSException;
}