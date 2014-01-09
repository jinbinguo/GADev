package com.kingdee.eas.scm.im.inv;

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
import java.util.Set;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo;

public interface IMaterialReqBill extends IInvBillBase
{
    public MaterialReqBillInfo getMaterialReqBillInfo(IObjectPK pk) throws BOSException, EASBizException;
    public MaterialReqBillInfo getMaterialReqBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public MaterialReqBillInfo getMaterialReqBillInfo(String oql) throws BOSException, EASBizException;
    public MaterialReqBillCollection getMaterialReqBillCollection() throws BOSException;
    public MaterialReqBillCollection getMaterialReqBillCollection(EntityViewInfo view) throws BOSException;
    public MaterialReqBillCollection getMaterialReqBillCollection(String oql) throws BOSException;
    public CostCenterOrgUnitInfo getDefaultCostCenter(String userID) throws BOSException, EASBizException;
    public Set getAdminOrgsHashSet(String userID) throws BOSException, EASBizException;
    public boolean deleteable(String billId) throws BOSException, EASBizException;
    public String getLotsByMaterial(String storageOrgUnitID, String materialID) throws BOSException, EASBizException;
    public void bachLoopAduit(IObjectPK[] pk) throws BOSException, EASBizException;
    public MaterialReqBillPriceInfoEntryCollection getPriceInfo(MaterialReqBillPriceInfoEntryCollection priceInfos, Map orgUnits) throws BOSException, EASBizException;
}