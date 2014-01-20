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
public class DMSWipBillTypeEnum extends StringEnum
{
    public static final String PURINWAREHS_VALUE = "PurInWarehs";//alias=采购入库
    public static final String PURRETURN_VALUE = "PurReturn";//alias=采购退货
    public static final String SALEISSUE_VALUE = "SaleIssue";//alias=销售出库
    public static final String SALERETURN_VALUE = "SaleReturn";//alias=销售退货
    public static final String TRANSFERINWAREHS_VALUE = "TransferInWarehs";//alias=调拨入库
    public static final String TRANSFEROUTWAREHS_VALUE = "TransferOutWarehs";//alias=调拨出库
    public static final String PROFITINWAREHS_VALUE = "ProfitInWarehs";//alias=盘盈入库
    public static final String LOSSOUTWAREHS_VALUE = "LossOutWarehs";//alias=盘亏出库

    public static final DMSWipBillTypeEnum PurInWarehs = new DMSWipBillTypeEnum("PurInWarehs", PURINWAREHS_VALUE);
    public static final DMSWipBillTypeEnum PurReturn = new DMSWipBillTypeEnum("PurReturn", PURRETURN_VALUE);
    public static final DMSWipBillTypeEnum SaleIssue = new DMSWipBillTypeEnum("SaleIssue", SALEISSUE_VALUE);
    public static final DMSWipBillTypeEnum SaleReturn = new DMSWipBillTypeEnum("SaleReturn", SALERETURN_VALUE);
    public static final DMSWipBillTypeEnum TransferInWarehs = new DMSWipBillTypeEnum("TransferInWarehs", TRANSFERINWAREHS_VALUE);
    public static final DMSWipBillTypeEnum TransferOutWarehs = new DMSWipBillTypeEnum("TransferOutWarehs", TRANSFEROUTWAREHS_VALUE);
    public static final DMSWipBillTypeEnum ProfitInWarehs = new DMSWipBillTypeEnum("ProfitInWarehs", PROFITINWAREHS_VALUE);
    public static final DMSWipBillTypeEnum LossOutWarehs = new DMSWipBillTypeEnum("LossOutWarehs", LOSSOUTWAREHS_VALUE);

    /**
     * construct function
     * @param String dMSWipBillTypeEnum
     */
    private DMSWipBillTypeEnum(String name, String dMSWipBillTypeEnum)
    {
        super(name, dMSWipBillTypeEnum);
    }
    
    /**
     * getEnum function
     * @param String arguments
     */
    public static DMSWipBillTypeEnum getEnum(String dMSWipBillTypeEnum)
    {
        return (DMSWipBillTypeEnum)getEnum(DMSWipBillTypeEnum.class, dMSWipBillTypeEnum);
    }

    /**
     * getEnumMap function
     */
    public static Map getEnumMap()
    {
        return getEnumMap(DMSWipBillTypeEnum.class);
    }

    /**
     * getEnumList function
     */
    public static List getEnumList()
    {
         return getEnumList(DMSWipBillTypeEnum.class);
    }
    
    /**
     * getIterator function
     */
    public static Iterator iterator()
    {
         return iterator(DMSWipBillTypeEnum.class);
    }
}