package com.kingdee.eas.myframework.template.base;

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

public interface ISimpleBizBillEntry extends ICoreBillEntryBase
{
    public SimpleBizBillEntryInfo getSimpleBizBillEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public SimpleBizBillEntryInfo getSimpleBizBillEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public SimpleBizBillEntryInfo getSimpleBizBillEntryInfo(String oql) throws BOSException, EASBizException;
    public SimpleBizBillEntryCollection getSimpleBizBillEntryCollection() throws BOSException;
    public SimpleBizBillEntryCollection getSimpleBizBillEntryCollection(EntityViewInfo view) throws BOSException;
    public SimpleBizBillEntryCollection getSimpleBizBillEntryCollection(String oql) throws BOSException;
}