package com.kingdee.eas.train;

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
import com.kingdee.eas.framework.ICoreBillBase;

public interface IPurTest extends ICoreBillBase
{
    public PurTestCollection getPurTestCollection() throws BOSException;
    public PurTestCollection getPurTestCollection(EntityViewInfo view) throws BOSException;
    public PurTestCollection getPurTestCollection(String oql) throws BOSException;
    public PurTestInfo getPurTestInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PurTestInfo getPurTestInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PurTestInfo getPurTestInfo(String oql) throws BOSException, EASBizException;
    public void audit(PurTestInfo model) throws BOSException;
    public void test1() throws BOSException;
    public void test2() throws BOSException;
    public void test3() throws BOSException;
    public void test4() throws BOSException;
    public void test5() throws BOSException;
}