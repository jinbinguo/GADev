/**
 * output package name
 */
package com.kingdee.eas.ga.syncdata;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.StringEnum;

/**
 * output class name
 */
public class DMSImpTypeEnum extends StringEnum
{
    public static final String WIPBILL_VALUE = "WIPBILL";//alias=WIP单
    public static final String TRADEINQUIRE_VALUE = "TRADEINQUIRE";//alias=交易查询

    public static final DMSImpTypeEnum WIPBILL = new DMSImpTypeEnum("WIPBILL", WIPBILL_VALUE);
    public static final DMSImpTypeEnum TRADEINQUIRE = new DMSImpTypeEnum("TRADEINQUIRE", TRADEINQUIRE_VALUE);

    /**
     * construct function
     * @param String dMSImpTypeEnum
     */
    private DMSImpTypeEnum(String name, String dMSImpTypeEnum)
    {
        super(name, dMSImpTypeEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static DMSImpTypeEnum getEnum(String dMSImpTypeEnum)
    {
        return (DMSImpTypeEnum)getEnum(DMSImpTypeEnum.class, dMSImpTypeEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(DMSImpTypeEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(DMSImpTypeEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(DMSImpTypeEnum.class);
    }
}