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
     * Object:邮箱服务器配置's 发送服务器property 
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
     * Object:邮箱服务器配置's 端口property 
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
     * Object:邮箱服务器配置's 协议property 
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
     * Object:邮箱服务器配置's 密码property 
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
     * Object:邮箱服务器配置's 发送帐号property 
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
     * Object:邮箱服务器配置's 别名property 
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