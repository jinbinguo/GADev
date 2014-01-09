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
import com.kingdee.eas.framework.ICoreBillEntryBase;

public interface IPurInWarehsEntry extends ICoreBillEntryBase
{
    public PurInWarehsEntryInfo getPurInWarehsEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public PurInWarehsEntryInfo getPurInWarehsEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public PurInWarehsEntryInfo getPurInWarehsEntryInfo(String oql) throws BOSException, EASBizException;
    public PurInWarehsEntryCollection getPurInWarehsEntryCollection() throws BOSException;
    public PurInWarehsEntryCollection getPurInWarehsEntryCollection(EntityViewInfo view) throws BOSException;
    public PurInWarehsEntryCollection getPurInWarehsEntryCollection(String oql) throws BOSException;
}