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

public interface IRepairWOBizType extends ISimpleDatabase
{
    public RepairWOBizTypeInfo getRepairWOBizTypeInfo(IObjectPK pk) throws BOSException, EASBizException;
    public RepairWOBizTypeInfo getRepairWOBizTypeInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public RepairWOBizTypeInfo getRepairWOBizTypeInfo(String oql) throws BOSException, EASBizException;
    public RepairWOBizTypeCollection getRepairWOBizTypeCollection() throws BOSException;
    public RepairWOBizTypeCollection getRepairWOBizTypeCollection(EntityViewInfo view) throws BOSException;
    public RepairWOBizTypeCollection getRepairWOBizTypeCollection(String oql) throws BOSException;
}