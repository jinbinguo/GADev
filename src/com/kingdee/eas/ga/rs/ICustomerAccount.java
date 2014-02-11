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
import com.kingdee.eas.myframework.template.base.ISimpleDatabase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface ICustomerAccount extends ISimpleDatabase
{
    public CustomerAccountInfo getCustomerAccountInfo(IObjectPK pk) throws BOSException, EASBizException;
    public CustomerAccountInfo getCustomerAccountInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public CustomerAccountInfo getCustomerAccountInfo(String oql) throws BOSException, EASBizException;
    public CustomerAccountCollection getCustomerAccountCollection() throws BOSException;
    public CustomerAccountCollection getCustomerAccountCollection(EntityViewInfo view) throws BOSException;
    public CustomerAccountCollection getCustomerAccountCollection(String oql) throws BOSException;
}