package com.kingdee.eas.myframework.msgengine;

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

public interface IMailServerConfig extends ISimpleDatabase
{
    public MailServerConfigInfo getMailServerConfigInfo(IObjectPK pk) throws BOSException, EASBizException;
    public MailServerConfigInfo getMailServerConfigInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public MailServerConfigInfo getMailServerConfigInfo(String oql) throws BOSException, EASBizException;
    public MailServerConfigCollection getMailServerConfigCollection() throws BOSException;
    public MailServerConfigCollection getMailServerConfigCollection(EntityViewInfo view) throws BOSException;
    public MailServerConfigCollection getMailServerConfigCollection(String oql) throws BOSException;
}