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
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.myframework.template.base.IGroupDatabase;

public interface ITestGroup extends IGroupDatabase
{
    public TestGroupInfo getTestGroupInfo(IObjectPK pk) throws BOSException, EASBizException;
    public TestGroupInfo getTestGroupInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public TestGroupInfo getTestGroupInfo(String oql) throws BOSException, EASBizException;
    public TestGroupCollection getTestGroupCollection() throws BOSException;
    public TestGroupCollection getTestGroupCollection(EntityViewInfo view) throws BOSException;
    public TestGroupCollection getTestGroupCollection(String oql) throws BOSException;
}