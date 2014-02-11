/**
 * output package name
 */
package com.kingdee.eas.ga.rs;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.StringEnum;

/**
 * output class name
 */
public class TEnum extends StringEnum
{
    public static final String P_VALUE = "P";//alias=P
    public static final String L_VALUE = "L";//alias=L

    public static final TEnum P = new TEnum("P", P_VALUE);
    public static final TEnum L = new TEnum("L", L_VALUE);

    /**
     * construct function
     * @param String tEnum
     */
    private TEnum(String name, String tEnum)
    {
        super(name, tEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static TEnum getEnum(String tEnum)
    {
        return (TEnum)getEnum(TEnum.class, tEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(TEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(TEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(TEnum.class);
    }
}