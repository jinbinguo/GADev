package com.kingdee.eas.scm.im.inv;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.scm.common.ISCMBillEntryBase;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface IMaterialReqBillPriceInfoEntry extends ISCMBillEntryBase
{
    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public MaterialReqBillPriceInfoEntryInfo getMaterialReqBillPriceInfoEntryInfo(String oql) throws BOSException, EASBizException;
    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection() throws BOSException;
    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection(EntityViewInfo view) throws BOSException;
    public MaterialReqBillPriceInfoEntryCollection getMaterialReqBillPriceInfoEntryCollection(String oql) throws BOSException;
}