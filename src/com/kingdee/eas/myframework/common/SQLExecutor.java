package com.kingdee.eas.myframework.common;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.eas.myframework.common.app.*;
import java.lang.String;
import com.kingdee.bos.util.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.bos.framework.*;

public class SQLExecutor extends AbstractBizCtrl implements ISQLExecutor
{
    public SQLExecutor()
    {
        super();
        registerInterface(ISQLExecutor.class, this);
    }
    public SQLExecutor(Context ctx)
    {
        super(ctx);
        registerInterface(ISQLExecutor.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("FD8403F2");
    }
    private SQLExecutorController getController() throws BOSException
    {
        return (SQLExecutorController)getBizController();
    }
    /**
     *执行更新、删除SQL-User defined method
     *@param sql SQL脚本
     */
    public void execute(String sql) throws BOSException, EASBizException
    {
        try {
            getController().execute(getContext(), sql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *查询SQL-User defined method
     *@param sql 查询SQ脚本
     *@return
     */
    public IRowSet executeQuery(String sql) throws BOSException, EASBizException
    {
        try {
            return getController().executeQuery(getContext(), sql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}