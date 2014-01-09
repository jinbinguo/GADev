package com.kingdee.eas.myframework.test;

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

public interface ITestDatabase extends ISimpleDatabase
{
    public TestDatabaseInfo getTestDatabaseInfo(IObjectPK pk) throws BOSException, EASBizException;
    public TestDatabaseInfo getTestDatabaseInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public TestDatabaseInfo getTestDatabaseInfo(String oql) throws BOSException, EASBizException;
    public TestDatabaseCollection getTestDatabaseCollection() throws BOSException;
    public TestDatabaseCollection getTestDatabaseCollection(EntityViewInfo view) throws BOSException;
    public TestDatabaseCollection getTestDatabaseCollection(String oql) throws BOSException;
}