package com.kingdee.eas.myframework.common.app;

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

import com.kingdee.eas.auto4s.vm.util.app.DBUtil;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

public class SQLExecutorControllerBean extends AbstractSQLExecutorControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.myframework.common.app.SQLExecutorControllerBean");
    
    @Override
    public void execute(Context ctx, String sql) throws BOSException,
    		EASBizException {
    	DbUtil.execute(ctx, sql);
    }
    
    @Override
    public IRowSet executeQuery(Context ctx, String sql) throws BOSException,
    		EASBizException {
    	return DbUtil.executeQuery(ctx, sql);
    }
}