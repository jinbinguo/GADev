/**
 * output package name
 */
package com.kingdee.eas.myframework.common;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.IntEnum;

/**
 * output class name
 */
public class SortTypeEnum extends IntEnum
{
    public static final int ASC_VALUE = 0;//alias=Ë³Ðò
    public static final int DESC_VALUE = 1;//alias=ÄæÐò

    public static final SortTypeEnum ASC = new SortTypeEnum("ASC", ASC_VALUE);
    public static final SortTypeEnum DESC = new SortTypeEnum("DESC", DESC_VALUE);

    /**
     * construct function
     * @param integer sortTypeEnum
     */
    private SortTypeEnum(String name, int sortTypeEnum)
    {
        super(name, sortTypeEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static SortTypeEnum getEnum(String sortTypeEnum)
    {
        return (SortTypeEnum)getEnum(SortTypeEnum.class, sortTypeEnum);
    }

    /**
     * getEnum function
     * @param String arguments
     */
    public static SortTypeEnum getEnum(int sortTypeEnum)
    {
        return (SortTypeEnum)getEnum(SortTypeEnum.class, sortTypeEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(SortTypeEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(SortTypeEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(SortTypeEnum.class);
    }
}