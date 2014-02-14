/**
 * output package name
 */
package com.kingdee.eas.ga.basedata;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.StringEnum;

/**
 * output class name
 */
public class UserTypeEnum extends StringEnum
{
    public static final String FORREPAIR_VALUE = "Repair";//alias=Œ¨–ﬁ’À∫≈
    public static final String FORRETAIL_VALUE = "Retail";//alias=¡„ €’À∫≈

    public static final UserTypeEnum ForRepair = new UserTypeEnum("ForRepair", FORREPAIR_VALUE);
    public static final UserTypeEnum ForRetail = new UserTypeEnum("ForRetail", FORRETAIL_VALUE);

    /**
     * construct function
     * @param String userTypeEnum
     */
    private UserTypeEnum(String name, String userTypeEnum)
    {
        super(name, userTypeEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static UserTypeEnum getEnum(String userTypeEnum)
    {
        return (UserTypeEnum)getEnum(UserTypeEnum.class, userTypeEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(UserTypeEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(UserTypeEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(UserTypeEnum.class);
    }
}