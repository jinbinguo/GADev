package com.kingdee.eas.scm.im.inv.app;

import org.apache.log4j.Logger;
import javax.ejb.*;
import java.rmi.RemoteException;
import com.kingdee.bos.*;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.rule.RuleExecutor;
import com.kingdee.bos.metadata.MetaDataPK;
//import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.framework.ejb.AbstractEntityControllerBean;
import com.kingdee.bos.framework.ejb.AbstractBizControllerBean;
//import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;

import java.lang.String;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.dao.IObjectPK;
import java.util.Map;

import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.scm.common.SCMBillBaseCollection;
import java.util.HashMap;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import java.util.Set;
import com.kingdee.eas.framework.CoreBillBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.eas.scm.im.inv.SaleIssueBillInfo;
import java.util.List;
import com.kingdee.eas.scm.im.inv.SaleIssueBillCollection;
import com.kingdee.eas.scm.im.inv.InvBillBaseCollection;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

public class SaleIssueBillControllerBeanEx extends com.kingdee.eas.scm.im.inv.app.SaleIssueBillControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.scm.im.inv.app.SaleIssueBillControllerBeanEx");
    @Override
    protected void _passAudit(Context ctx, IObjectPK pk, IObjectValue model)
    		throws EASBizException, BOSException {
    	super._passAudit(ctx, pk, model);
    	try {
	    	String sql = String.format("select 1 from T_IM_SaleIssueEntry a " +
					"where a.FSourceBillTypeID='HM+nytJ+S7izjFHd2/madkY+1VI=' and a.FParentID='%s' " +
					"and exists (select 1 from CT_ATS_RepairWORWOItemSpEntry b where b.FID=a.FSourceBillEntryId and b.CFIssueQty>b.CFQty)",
					pk.toString());
	    	IRowSet rs =  DBUtils.executeQuery(ctx, sql);
	    	if (rs != null && rs.next()) {
	    		throw new EASBizException(new NumericExceptionSubItem("","配件已出库，不可重复出库"));
	    	}
    	} catch (Exception e) {
    		throw new EASBizException(new NumericExceptionSubItem("",e.getMessage()));
    	}
    	
    	
    	
    }
}
