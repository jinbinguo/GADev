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

public interface IDMSWipBillEntry extends ISimpleBizBillEntry
{
    public DMSWipBillEntryInfo getDMSWipBillEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public DMSWipBillEntryInfo getDMSWipBillEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public DMSWipBillEntryInfo getDMSWipBillEntryInfo(String oql) throws BOSException, EASBizException;
    public DMSWipBillEntryCollection getDMSWipBillEntryCollection() throws BOSException;
    public DMSWipBillEntryCollection getDMSWipBillEntryCollection(EntityViewInfo view) throws BOSException;
    public DMSWipBillEntryCollection getDMSWipBillEntryCollection(String oql) throws BOSException;
}