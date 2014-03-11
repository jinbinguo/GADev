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
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.myframework.template.base.ISimpleBizBillEntry;

public interface IRepairManEntry extends ISimpleBizBillEntry
{
    public RepairManEntryInfo getRepairManEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public RepairManEntryInfo getRepairManEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public RepairManEntryInfo getRepairManEntryInfo(String oql) throws BOSException, EASBizException;
    public RepairManEntryCollection getRepairManEntryCollection() throws BOSException;
    public RepairManEntryCollection getRepairManEntryCollection(EntityViewInfo view) throws BOSException;
    public RepairManEntryCollection getRepairManEntryCollection(String oql) throws BOSException;
}