package com.kingdee.eas.auto4s.rsm.rs;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import java.util.Map;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.auto4s.autoframework.core.IAutoBillBase;

public interface IRepairWO extends IAutoBillBase
{
    public RepairWOCollection getRepairWOCollection() throws BOSException;
    public RepairWOCollection getRepairWOCollection(EntityViewInfo view) throws BOSException;
    public RepairWOCollection getRepairWOCollection(String oql) throws BOSException;
    public RepairWOInfo getRepairWOInfo(IObjectPK pk) throws BOSException, EASBizException;
    public RepairWOInfo getRepairWOInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public RepairWOInfo getRepairWOInfo(String oql) throws BOSException, EASBizException;
    public void uninvalid(RepairWOInfo model) throws BOSException, EASBizException;
    public void invalid(RepairWOInfo model) throws BOSException, EASBizException;
    public void assign(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void repairBreak(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void breakRestore(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void inspect(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void wash(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void workTime(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void dispatching(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void timeBooking(IObjectValue model) throws BOSException, RsmRsException, EASBizException;
    public void deleteRepairWO(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void isB(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void cancelB(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void addEnter(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void adjust(RepairWOInfo model) throws BOSException;
    public void itemIssue(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void cancelAssign(Map cancelMap) throws BOSException, RsmRsException, EASBizException;
    public boolean isAllIssue(RepairWOInfo model) throws BOSException, RsmRsException, EASBizException;
    public void unWorkTime(Map paramMap) throws BOSException;
    public void unWash(RepairWOInfo model) throws BOSException;
    public void unInspect(Map paramMap) throws BOSException, EASBizException;
    public void updateRWOVersion(RepairWOInfo model) throws BOSException, EASBizException;
    public Map getRWOFromRepairBooking(Map paramMap) throws BOSException, EASBizException;
}