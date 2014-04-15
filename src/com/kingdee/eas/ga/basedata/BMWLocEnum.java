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
public class BMWLocEnum extends StringEnum
{
    public static final String B1_VALUE = "1";//alias=宝马一仓
    public static final String B2_VALUE = "2";//alias=宝马二仓
    public static final String B3_VALUE = "3";//alias=宝马三仓
    public static final String B4_VALUE = "4";//alias=宝马四仓
    public static final String B5_VALUE = "5";//alias=宝马五仓
    public static final String B6_VALUE = "6";//alias=宝马六仓
    public static final String B7_VALUE = "7";//alias=宝马七仓
    public static final String B8_VALUE = "8";//alias=宝马八仓
    public static final String B9_VALUE = "9";//alias=宝马九仓

    public static final BMWLocEnum B1 = new BMWLocEnum("B1", B1_VALUE);
    public static final BMWLocEnum B2 = new BMWLocEnum("B2", B2_VALUE);
    public static final BMWLocEnum B3 = new BMWLocEnum("B3", B3_VALUE);
    public static final BMWLocEnum B4 = new BMWLocEnum("B4", B4_VALUE);
    public static final BMWLocEnum B5 = new BMWLocEnum("B5", B5_VALUE);
    public static final BMWLocEnum B6 = new BMWLocEnum("B6", B6_VALUE);
    public static final BMWLocEnum B7 = new BMWLocEnum("B7", B7_VALUE);
    public static final BMWLocEnum B8 = new BMWLocEnum("B8", B8_VALUE);
    public static final BMWLocEnum B9 = new BMWLocEnum("B9", B9_VALUE);

    /**
     * construct function
     * @param String bMWLocEnum
     */
    private BMWLocEnum(String name, String bMWLocEnum)
    {
        super(name, bMWLocEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static BMWLocEnum getEnum(String bMWLocEnum)
    {
        return (BMWLocEnum)getEnum(BMWLocEnum.class, bMWLocEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(BMWLocEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(BMWLocEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(BMWLocEnum.class);
    }
}