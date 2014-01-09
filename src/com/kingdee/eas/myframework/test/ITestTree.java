package com.kingdee.eas.myframework.test;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.eas.myframework.template.base.ITreeDatabase;
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

public interface ITestTree extends ITreeDatabase
{
    public TestTreeInfo getTestTreeInfo(IObjectPK pk) throws BOSException, EASBizException;
    public TestTreeInfo getTestTreeInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public TestTreeInfo getTestTreeInfo(String oql) throws BOSException, EASBizException;
    public TestTreeCollection getTestTreeCollection() throws BOSException;
    public TestTreeCollection getTestTreeCollection(EntityViewInfo view) throws BOSException;
    public TestTreeCollection getTestTreeCollection(String oql) throws BOSException;
}