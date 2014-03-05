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
public class RepairWOStatusEnum extends StringEnum
{
    public static final String NOTSETTLE_VALUE = "1";//alias=未结算
    public static final String PARTSETTLE_VALUE = "2";//alias=部分结算
    public static final String ALLSETTLE_VALUE = "3";//alias=全部结算

    public static final RepairWOStatusEnum notSettle = new RepairWOStatusEnum("notSettle", NOTSETTLE_VALUE);
    public static final RepairWOStatusEnum partSettle = new RepairWOStatusEnum("partSettle", PARTSETTLE_VALUE);
    public static final RepairWOStatusEnum AllSettle = new RepairWOStatusEnum("AllSettle", ALLSETTLE_VALUE);

    /**
     * construct function
     * @param String repairWOStatusEnum
     */
    private RepairWOStatusEnum(String name, String repairWOStatusEnum)
    {
        super(name, repairWOStatusEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static RepairWOStatusEnum getEnum(String repairWOStatusEnum)
    {
        return (RepairWOStatusEnum)getEnum(RepairWOStatusEnum.class, repairWOStatusEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(RepairWOStatusEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(RepairWOStatusEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(RepairWOStatusEnum.class);
    }
}