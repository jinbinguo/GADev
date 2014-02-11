/**
 * output package name
 */
package com.kingdee.eas.ga.rs;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.IntEnum;

/**
 * output class name
 */
public class SettlementTypeEnum extends IntEnum
{
    public static final int CASH_VALUE = 1;//alias=ож╫А
    public static final int ONACCOUNT_VALUE = 2;//alias=╧рук

    public static final SettlementTypeEnum Cash = new SettlementTypeEnum("Cash", CASH_VALUE);
    public static final SettlementTypeEnum OnAccount = new SettlementTypeEnum("OnAccount", ONACCOUNT_VALUE);

    /**
     * construct function
     * @param integer settlementTypeEnum
     */
    private SettlementTypeEnum(String name, int settlementTypeEnum)
    {
        super(name, settlementTypeEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static SettlementTypeEnum getEnum(String settlementTypeEnum)
    {
        return (SettlementTypeEnum)getEnum(SettlementTypeEnum.class, settlementTypeEnum);
    }

    /**
     * getEnum function
     * @param String arguments
     */
    public static SettlementTypeEnum getEnum(int settlementTypeEnum)
    {
        return (SettlementTypeEnum)getEnum(SettlementTypeEnum.class, settlementTypeEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(SettlementTypeEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(SettlementTypeEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(SettlementTypeEnum.class);
    }
}