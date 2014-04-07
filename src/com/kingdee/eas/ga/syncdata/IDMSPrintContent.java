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
import com.kingdee.eas.myframework.template.base.ISimpleBizBill;

public interface IDMSPrintContent extends ISimpleBizBill
{
    public DMSPrintContentInfo getDMSPrintContentInfo(IObjectPK pk) throws BOSException, EASBizException;
    public DMSPrintContentInfo getDMSPrintContentInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public DMSPrintContentInfo getDMSPrintContentInfo(String oql) throws BOSException, EASBizException;
    public DMSPrintContentCollection getDMSPrintContentCollection() throws BOSException;
    public DMSPrintContentCollection getDMSPrintContentCollection(EntityViewInfo view) throws BOSException;
    public DMSPrintContentCollection getDMSPrintContentCollection(String oql) throws BOSException;
}