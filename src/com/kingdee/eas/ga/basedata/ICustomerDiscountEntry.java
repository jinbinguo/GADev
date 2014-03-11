package com.kingdee.eas.ga.basedata;

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

public interface ICustomerDiscountEntry extends ISimpleBizBillEntry
{
    public CustomerDiscountEntryInfo getCustomerDiscountEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public CustomerDiscountEntryInfo getCustomerDiscountEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public CustomerDiscountEntryInfo getCustomerDiscountEntryInfo(String oql) throws BOSException, EASBizException;
    public CustomerDiscountEntryCollection getCustomerDiscountEntryCollection() throws BOSException;
    public CustomerDiscountEntryCollection getCustomerDiscountEntryCollection(EntityViewInfo view) throws BOSException;
    public CustomerDiscountEntryCollection getCustomerDiscountEntryCollection(String oql) throws BOSException;
}