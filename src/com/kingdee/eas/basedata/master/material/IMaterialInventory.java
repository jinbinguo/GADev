package com.kingdee.eas.basedata.master.material;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import java.util.HashMap;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.util.*;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.framework.*;
import java.util.List;

public interface IMaterialInventory extends IMaterialPropertyBase
{
    public MaterialInventoryInfo getMaterialInventoryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public MaterialInventoryInfo getMaterialInventoryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public MaterialInventoryInfo getMaterialInventoryInfo(String oql) throws BOSException, EASBizException;
    public MaterialInventoryCollection getMaterialInventoryCollection() throws BOSException;
    public MaterialInventoryCollection getMaterialInventoryCollection(EntityViewInfo view) throws BOSException;
    public MaterialInventoryCollection getMaterialInventoryCollection(String oql) throws BOSException;
    public MaterialInventoryInfo first(IObjectPK pk) throws BOSException, EASBizException;
    public MaterialInventoryInfo previous(IObjectPK pk) throws BOSException, EASBizException;
    public MaterialInventoryInfo next(IObjectPK pk) throws BOSException, EASBizException;
    public MaterialInventoryInfo last(IObjectPK pk) throws BOSException, EASBizException;
    public void updateCheck(IObjectPK pk, MaterialInventoryInfo newInfo) throws BOSException, EASBizException;
    public MaterialInventoryInfo getInventoryInfo(String matid, String ouid) throws BOSException, EASBizException;
    public List getMaterialsExistInvInfo(String[] materialIDs, String inventoryID) throws BOSException;
    public HashMap verifyMaterialInvInfos(HashMap hashMap) throws BOSException;
}