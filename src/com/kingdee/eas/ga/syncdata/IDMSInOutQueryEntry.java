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
import com.kingdee.eas.myframework.template.base.ISimpleBizBillEntry;

public interface IDMSInOutQueryEntry extends ISimpleBizBillEntry
{
    public DMSInOutQueryEntryInfo getDMSInOutQueryEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public DMSInOutQueryEntryInfo getDMSInOutQueryEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public DMSInOutQueryEntryInfo getDMSInOutQueryEntryInfo(String oql) throws BOSException, EASBizException;
    public DMSInOutQueryEntryCollection getDMSInOutQueryEntryCollection() throws BOSException;
    public DMSInOutQueryEntryCollection getDMSInOutQueryEntryCollection(EntityViewInfo view) throws BOSException;
    public DMSInOutQueryEntryCollection getDMSInOutQueryEntryCollection(String oql) throws BOSException;
}