package com.kingdee.eas.scm.im.inv;

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

public interface IMaterialReqBillEntry extends IInvBillBaseEntry
{
    public MaterialReqBillEntryInfo getMaterialReqBillEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public MaterialReqBillEntryInfo getMaterialReqBillEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public MaterialReqBillEntryInfo getMaterialReqBillEntryInfo(String oql) throws BOSException, EASBizException;
    public MaterialReqBillEntryCollection getMaterialReqBillEntryCollection() throws BOSException;
    public MaterialReqBillEntryCollection getMaterialReqBillEntryCollection(EntityViewInfo view) throws BOSException;
    public MaterialReqBillEntryCollection getMaterialReqBillEntryCollection(String oql) throws BOSException;
}