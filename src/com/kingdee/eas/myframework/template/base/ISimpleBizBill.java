package com.kingdee.eas.myframework.template.base;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.framework.batch.BatchExecuteResult;
import com.kingdee.bos.framework.batch.BatchExecuteParamsEntry;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.ICoreBillBase;

public interface ISimpleBizBill extends ICoreBillBase
{
    public SimpleBizBillCollection getSimpleBizBillCollection() throws BOSException;
    public SimpleBizBillCollection getSimpleBizBillCollection(EntityViewInfo view) throws BOSException;
    public SimpleBizBillCollection getSimpleBizBillCollection(String oql) throws BOSException;
    public SimpleBizBillInfo getSimpleBizBillInfo(IObjectPK pk) throws BOSException, EASBizException;
    public SimpleBizBillInfo getSimpleBizBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public SimpleBizBillInfo getSimpleBizBillInfo(String oql) throws BOSException, EASBizException;
    public void audit(IObjectPK pk) throws BOSException, EASBizException;
    public void unAudit(IObjectPK pk) throws BOSException, EASBizException;
    public void batchAudit(IObjectPK[] arrayPK) throws BOSException, EASBizException;
    public void batchUnAudit(IObjectPK[] arrayPK) throws BOSException, EASBizException;
    public BatchExecuteResult batchExecuteWithTrans(String methodName, BatchExecuteParamsEntry[] paramEntries) throws BOSException, EASBizException;
}