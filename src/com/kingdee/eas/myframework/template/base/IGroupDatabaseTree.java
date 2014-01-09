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
import com.kingdee.eas.framework.ITreeBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;

public interface IGroupDatabaseTree extends ITreeBase
{
    public GroupDatabaseTreeInfo getGroupDatabaseTreeInfo(IObjectPK pk) throws BOSException, EASBizException;
    public GroupDatabaseTreeInfo getGroupDatabaseTreeInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public GroupDatabaseTreeInfo getGroupDatabaseTreeInfo(String oql) throws BOSException, EASBizException;
    public GroupDatabaseTreeCollection getGroupDatabaseTreeCollection() throws BOSException;
    public GroupDatabaseTreeCollection getGroupDatabaseTreeCollection(EntityViewInfo view) throws BOSException;
    public GroupDatabaseTreeCollection getGroupDatabaseTreeCollection(String oql) throws BOSException;
}