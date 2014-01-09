package com.kingdee.eas.myframework.test;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.eas.myframework.template.base.IGroupDatabaseTree;
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

public interface ITestGroupTree extends IGroupDatabaseTree
{
    public TestGroupTreeInfo getTestGroupTreeInfo(IObjectPK pk) throws BOSException, EASBizException;
    public TestGroupTreeInfo getTestGroupTreeInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public TestGroupTreeInfo getTestGroupTreeInfo(String oql) throws BOSException, EASBizException;
    public TestGroupTreeCollection getTestGroupTreeCollection() throws BOSException;
    public TestGroupTreeCollection getTestGroupTreeCollection(EntityViewInfo view) throws BOSException;
    public TestGroupTreeCollection getTestGroupTreeCollection(String oql) throws BOSException;
}