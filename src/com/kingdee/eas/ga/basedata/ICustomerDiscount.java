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
import com.kingdee.eas.myframework.template.base.ISimpleBizBill;

public interface ICustomerDiscount extends ISimpleBizBill
{
    public CustomerDiscountInfo getCustomerDiscountInfo(IObjectPK pk) throws BOSException, EASBizException;
    public CustomerDiscountInfo getCustomerDiscountInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public CustomerDiscountInfo getCustomerDiscountInfo(String oql) throws BOSException, EASBizException;
    public CustomerDiscountCollection getCustomerDiscountCollection() throws BOSException;
    public CustomerDiscountCollection getCustomerDiscountCollection(EntityViewInfo view) throws BOSException;
    public CustomerDiscountCollection getCustomerDiscountCollection(String oql) throws BOSException;
}