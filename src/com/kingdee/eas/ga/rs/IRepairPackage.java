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

public interface IRepairPackage extends ISimpleDatabase
{
    public RepairPackageInfo getRepairPackageInfo(IObjectPK pk) throws BOSException, EASBizException;
    public RepairPackageInfo getRepairPackageInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public RepairPackageInfo getRepairPackageInfo(String oql) throws BOSException, EASBizException;
    public RepairPackageCollection getRepairPackageCollection() throws BOSException;
    public RepairPackageCollection getRepairPackageCollection(EntityViewInfo view) throws BOSException;
    public RepairPackageCollection getRepairPackageCollection(String oql) throws BOSException;
}