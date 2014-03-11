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
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.myframework.template.base.ISimpleBizBill;

public interface IRepairMan extends ISimpleBizBill
{
    public RepairManInfo getRepairManInfo(IObjectPK pk) throws BOSException, EASBizException;
    public RepairManInfo getRepairManInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public RepairManInfo getRepairManInfo(String oql) throws BOSException, EASBizException;
    public RepairManCollection getRepairManCollection() throws BOSException;
    public RepairManCollection getRepairManCollection(EntityViewInfo view) throws BOSException;
    public RepairManCollection getRepairManCollection(String oql) throws BOSException;
}