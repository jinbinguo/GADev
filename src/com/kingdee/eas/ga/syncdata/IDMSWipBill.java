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

public interface IDMSWipBill extends ISimpleBizBill
{
    public DMSWipBillInfo getDMSWipBillInfo(IObjectPK pk) throws BOSException, EASBizException;
    public DMSWipBillInfo getDMSWipBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public DMSWipBillInfo getDMSWipBillInfo(String oql) throws BOSException, EASBizException;
    public DMSWipBillCollection getDMSWipBillCollection() throws BOSException;
    public DMSWipBillCollection getDMSWipBillCollection(EntityViewInfo view) throws BOSException;
    public DMSWipBillCollection getDMSWipBillCollection(String oql) throws BOSException;
}