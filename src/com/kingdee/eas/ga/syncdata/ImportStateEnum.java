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
public class ImportStateEnum extends StringEnum
{
    public static final String SUCCESS_VALUE = "1";//alias=³É¹¦
    public static final String FAILURE_VALUE = "0";//alias=Ê§°Ü

    public static final ImportStateEnum Success = new ImportStateEnum("Success", SUCCESS_VALUE);
    public static final ImportStateEnum Failure = new ImportStateEnum("Failure", FAILURE_VALUE);

    /**
     * construct function
     * @param String importStateEnum
     */
    private ImportStateEnum(String name, String importStateEnum)
    {
        super(name, importStateEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static ImportStateEnum getEnum(String importStateEnum)
    {
        return (ImportStateEnum)getEnum(ImportStateEnum.class, importStateEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(ImportStateEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(ImportStateEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(ImportStateEnum.class);
    }
}