/**
 * output package name
 */
package com.kingdee.eas.train;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import com.kingdee.util.enums.IntEnum;

/**
 * output class name
 */
public class PayType extends IntEnum
{
    public static final int CASH_VALUE = 0;//alias=œ÷π∫
    public static final int CREDIT_VALUE = 1;//alias=…ﬁπ∫

    public static final PayType cash = new PayType("cash", CASH_VALUE);
    public static final PayType credit = new PayType("credit", CREDIT_VALUE);

    /**
     * construct function
     * @param integer payType
     */
    private PayType(String name, int payType)
    {
        super(name, payType);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static PayType getEnum(String payType)
    {
        return (PayType)getEnum(PayType.class, payType);
    }

    /**
     * getEnum function
     * @param String arguments
     */
    public static PayType getEnum(int payType)
    {
        return (PayType)getEnum(PayType.class, payType);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(PayType.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(PayType.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(PayType.class);
    }
}