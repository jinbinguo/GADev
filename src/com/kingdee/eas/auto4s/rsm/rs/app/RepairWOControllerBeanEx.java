package com.kingdee.eas.auto4s.rsm.rs.app;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.myframework.util.DBUtils;

public class RepairWOControllerBeanEx extends com.kingdee.eas.auto4s.rsm.rs.app.RepairWOControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.auto4s.rsm.rs.app.RepairWOControllerBeanPIEx");
    protected void _audit(Context ctx, IObjectValue model)throws BOSException
    {
	     super._audit(ctx, model);
    }
    protected void _unAudit(Context ctx, IObjectValue model)throws BOSException
    {
	     super._unAudit(ctx, model);
    }
    
    public RepairWOControllerBeanEx() {
		super();
	}
    
    @Override
    protected void _delete(Context ctx, IObjectPK pk) throws BOSException,
    		EASBizException {
    	super._delete(ctx, pk);
    	String sql = String.format("delete T_ATS_WarrRemind where FRepairWOID='%s'", pk.toString());
    	try {
			DBUtils.execute(ctx, sql);
		} catch (Exception e) {
			
		}
    	
    }
}				
