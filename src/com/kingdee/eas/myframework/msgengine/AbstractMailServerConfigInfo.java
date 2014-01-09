package com.kingdee.eas.myframework.msgengine;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractMailServerConfigInfo extends com.kingdee.eas.myframework.template.base.SimpleDatabaseInfo implements Serializable 
{
    public AbstractMailServerConfigInfo()
    {
        this("id");
    }
    protected AbstractMailServerConfigInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:�������������'s ���ͷ�����property 
     */
    public String getHost()
    {
        return getString("host");
    }
    public void setHost(String item)
    {
        setString("host", item);
    }
    /**
     * Object:�������������'s �˿�property 
     */
    public int getPort()
    {
        return getInt("port");
    }
    public void setPort(int item)
    {
        setInt("port", item);
    }
    /**
     * Object:�������������'s Э��property 
     */
    public String getProtocol()
    {
        return getString("protocol");
    }
    public void setProtocol(String item)
    {
        setString("protocol", item);
    }
    /**
     * Object:�������������'s ����property 
     */
    public String getPassword()
    {
        return getString("password");
    }
    public void setPassword(String item)
    {
        setString("password", item);
    }
    /**
     * Object:�������������'s �����ʺ�property 
     */
    public String getUsername()
    {
        return getString("username");
    }
    public void setUsername(String item)
    {
        setString("username", item);
    }
    /**
     * Object:�������������'s ����property 
     */
    public String getAliasname()
    {
        return getString("aliasname");
    }
    public void setAliasname(String item)
    {
        setString("aliasname", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("BFC18CB5");
    }
}