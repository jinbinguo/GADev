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
public class IEnum extends StringEnum
{
    public static final String I_VALUE = "I";//alias=I
    public static final String X_VALUE = "X";//alias=X
    public static final String H_VALUE = "H";//alias=H

    public static final IEnum I = new IEnum("I", I_VALUE);
    public static final IEnum X = new IEnum("X", X_VALUE);
    public static final IEnum H = new IEnum("H", H_VALUE);

    /**
     * construct function
     * @param String iEnum
     */
    private IEnum(String name, String iEnum)
    {
        super(name, iEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static IEnum getEnum(String iEnum)
    {
        return (IEnum)getEnum(IEnum.class, iEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(IEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(IEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(IEnum.class);
    }
}