package com.kingdee.eas.myframework.template.base;

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
import com.kingdee.eas.framework.IDataBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface IGroupDatabase extends IDataBase
{
    public GroupDatabaseInfo getGroupDatabaseInfo(IObjectPK pk) throws BOSException, EASBizException;
    public GroupDatabaseInfo getGroupDatabaseInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public GroupDatabaseInfo getGroupDatabaseInfo(String oql) throws BOSException, EASBizException;
    public GroupDatabaseCollection getGroupDatabaseCollection() throws BOSException;
    public GroupDatabaseCollection getGroupDatabaseCollection(EntityViewInfo view) throws BOSException;
    public GroupDatabaseCollection getGroupDatabaseCollection(String oql) throws BOSException;
}