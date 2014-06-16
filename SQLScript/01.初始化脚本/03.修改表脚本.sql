﻿--add by jinbin_guo 20140210 T_ATS_RepairWO
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFDeptID' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFDeptID varchar(44);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFCustomInfo' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFCustomInfo nvarchar(255);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFSaleType' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFSaleType nvarchar(100);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFCustomerAccountI' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFCustomerAccountI varchar(44);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFCustomerAccountName' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFCustomerAccountName nvarchar(80);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFGaDept' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFGaDept nvarchar(100);
	
--add by jinbin_guo 20140210 T_ATS_RWORepairItemEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWipLineNo' AND KSQL_COL_TABNAME='T_ATS_RWORepairItemEntry')
	ALTER TABLE T_ATS_RWORepairItemEntry ADD CFWipLineNo int;
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWipFactLineNo' AND KSQL_COL_TABNAME='T_ATS_RWORepairItemEntry')
	ALTER TABLE T_ATS_RWORepairItemEntry ADD CFWipFactLineNo int;
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFItemSpEntryId' AND KSQL_COL_TABNAME='T_ATS_RWORepairItemEntry')
	ALTER TABLE T_ATS_RWORepairItemEntry ADD CFItemSpEntryId varchar(44);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWbprice' AND KSQL_COL_TABNAME='T_ATS_RWORepairItemEntry')
	ALTER TABLE T_ATS_RWORepairItemEntry ADD CFWbprice numeric(28,10);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFIsCreateTo' AND KSQL_COL_TABNAME='T_ATS_RWORepairItemEntry')
	ALTER TABLE T_ATS_RWORepairItemEntry ADD CFIsCreateTo int default 0;

--add by jinbin_guo 20140210 T_ATS_RWOSparepartEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWipLineNo' AND KSQL_COL_TABNAME='T_ATS_RWOSparepartEntry')
	ALTER TABLE T_ATS_RWOSparepartEntry ADD CFWipLineNo int;
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWipFactLineNo' AND KSQL_COL_TABNAME='T_ATS_RWOSparepartEntry')
	ALTER TABLE T_ATS_RWOSparepartEntry ADD CFWipFactLineNo int;	
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFIsCT' AND KSQL_COL_TABNAME='T_ATS_RWOSparepartEntry')
	ALTER TABLE T_ATS_RWOSparepartEntry ADD CFIsCT int;	
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFItemSpEntryId' AND KSQL_COL_TABNAME='T_ATS_RWOSparepartEntry')
	ALTER TABLE T_ATS_RWOSparepartEntry ADD CFItemSpEntryId varchar(44);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFIsCreateTo' AND KSQL_COL_TABNAME='T_ATS_RWOSparepartEntry')
	ALTER TABLE T_ATS_RWOSparepartEntry ADD CFIsCreateTo int default 0;

	
--add by jinbin_guo 20140211 T_PM_User
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFUserType' AND KSQL_COL_TABNAME='T_PM_User')
	ALTER TABLE T_PM_User ADD CFUserType varchar(100);

--add by jinbin_guo 20140212 CT_RS_W
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFSettleObject' AND KSQL_COL_TABNAME='CT_RS_W')
	ALTER TABLE CT_RS_W ADD CFSettleObject varchar(100);
	
--add by jinbin_guo 20140213 CT_ATS_RepairWORWOItemSpEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFSettlementObject' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFSettlementObject varchar(100);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWipLineNo' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFWipLineNo int;
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWipFactLineNo' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFWipFactLineNo int;
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFIsCreateTo' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFIsCreateTo int;
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFSaleType' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFSaleType nvarchar(100);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFRts' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFRts nvarchar(100);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFBillNum' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFBillNum nvarchar(100);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFPostingDate' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFPostingDate datetime;
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFIsAPSettle' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFIsAPSettle int;
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFCostAmount' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFCostAmount numeric(28,10);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFAccount' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFAccount nvarchar(100);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFISDELETE' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFISDELETE int;
	
--add by jinbin_guo 20140217 CT_RS_CustomerAccount
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFAtsCustomerID' AND KSQL_COL_TABNAME='CT_RS_CustomerAccount')
	ALTER TABLE CT_RS_CustomerAccount ADD CFAtsCustomerID varchar(44);

-- add by jinbin_guo 20140217  CT_SYN_DMSWipBillEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFDeptNum' AND KSQL_COL_TABNAME='CT_SYN_DMSWipBillEntry')
	ALTER TABLE CT_SYN_DMSWipBillEntry ADD CFDeptNum nvarchar(100);
	
-- add by jinbin_guo 20140217  CT_SYN_DMSWipBillEntry2
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFSaleType' AND KSQL_COL_TABNAME='CT_SYN_DMSWipBillEntry2')
	ALTER TABLE CT_SYN_DMSWipBillEntry2 ADD CFSaleType nvarchar(100);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFCostPrice' AND KSQL_COL_TABNAME='CT_SYN_DMSWipBillEntry2')
	ALTER TABLE CT_SYN_DMSWipBillEntry2 ADD CFCostPrice numeric(28,10);
	
-- add by jinbin_guo 20140217  CT_SYN_DMSWipBillEntry3
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFSaleType' AND KSQL_COL_TABNAME='CT_SYN_DMSWipBillEntry3')
	ALTER TABLE CT_SYN_DMSWipBillEntry3 ADD CFSaleType nvarchar(100);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFPostingDate' AND KSQL_COL_TABNAME='CT_SYN_DMSWipBillEntry3')
	ALTER TABLE CT_SYN_DMSWipBillEntry3 ADD CFPostingDate datetime;
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFRts' AND KSQL_COL_TABNAME='CT_SYN_DMSWipBillEntry3')
	ALTER TABLE CT_SYN_DMSWipBillEntry3 ADD CFRts nvarchar(100);
	
-- add by jinbin_guo 20140220 T_ATS_ReceivingBill
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFSrcEntryIds' AND KSQL_COL_TABNAME='T_ATS_ReceivingBill')
	ALTER TABLE T_ATS_ReceivingBill ADD CFSrcEntryIds nvarchar(2000);
	
-- add by jinbin_guo 20140226 CT_ATS_RepairWORWOItemSpEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFTaxPrice' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFTaxPrice numeric(28,10);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFTaxAmount' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFTaxAmount numeric(28,10);
	
--add by jinbin_guo 20140226 T_PM_User
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFMaxRepairDiscountRate' AND KSQL_COL_TABNAME='T_PM_User')
	ALTER TABLE T_PM_User ADD CFMaxRepairDiscountRate numeric(5,2);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFMaxRetailDiscountRate' AND KSQL_COL_TABNAME='T_PM_User')
	ALTER TABLE T_PM_User ADD CFMaxRetailDiscountRate numeric(5,2);

-- add by jinbin_guo 20140227 CT_ATS_RepairWORWOItemSpEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFRepairPkgID' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFRepairPkgID varchar(44);
	
--add by jinbin_guo 20140227 T_ATS_RepairWO
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFRepairBizTypeID' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFRepairBizTypeID varchar(44);
	
--add by jinbin_guo 20140304 T_ATS_RepairWO	
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFRepairManID' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFRepairManID varchar(44);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFGaBillStatus' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFGaBillStatus varchar(100);
	
--add by jinbin_guo 20140310 T_ATS_RepairWO
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFFirstBookInDate' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFFirstBookInDate DateTime;
	
-- add by jinbin_guo 20140311 CT_ATS_RepairWORWOItemSpEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFInitFactPrice' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFInitFactPrice numeric(28,10);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFAllocateExenseRate' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFAllocateExenseRate numeric(5,2);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFRepairWay' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFRepairWay varchar(100);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFSupplierID' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFSupplierID varchar(44);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWprice' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFWprice numeric(28,10);	
	
-- add by jinbin_guo 20140313 CT_ATS_RepairWORWOItemSpEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFOriginalEntryId' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFOriginalEntryId varchar(44);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFOriginalQty' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFOriginalQty numeric(28,10);	
	
-- add by jinbin_guo 20140314 CT_ATS_RepairWORWOItemSpEntry	
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFIsCreateTo2' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFIsCreateTo2 int default 0;
--add by jinbin_guo 20140314 T_ATS_RepairWO
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFSaler' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFSaler nvarchar(100);
	
--add by jinbin_guo 20140319 T_ATS_RepairWO
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFIsPrintedSettle' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFIsPrintedSettle int default 0;
-- add by jinbin_guo 20140319 CT_ATS_RepairWORWOItemSpEntry	
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFSettleDate' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFSettleDate datetime;
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFPersonID' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFPersonID varchar(44);
	
-- add by jinbin_guo 20140319 CT_ATS_RepairWORWOItemSpEntry	
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFAllocateCount' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFAllocateCount int default 0;
	
-- add by jinbin_guo 20140328 CT_ATS_RepairWORWOItemSpEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFRelateItemEntryId' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFRelateItemEntryId varchar(44);
	
-- add by jinbin_guo 20140403 CT_ATS_RepairWORWOItemSpEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFGiftDeptID' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFGiftDeptID varchar(44);
	
-- add by jinbin_guo 20140409 CT_RS_CustomerAccount
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFOrgUnitID' AND KSQL_COL_TABNAME='CT_RS_CustomerAccount')
	ALTER TABLE CT_RS_CustomerAccount ADD CFOrgUnitID varchar(44);

-- add by jinbin_guo 20140409 CT_RS_RepairMan
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFOrgUnitID' AND KSQL_COL_TABNAME='CT_RS_RepairMan')
	ALTER TABLE CT_RS_RepairMan ADD CFOrgUnitID varchar(44);
	
-- add by jinbin_guo 20140411 T_BD_MaterialInventory
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFCostQty' AND KSQL_COL_TABNAME='T_BD_MaterialInventory')
	ALTER TABLE T_BD_MaterialInventory ADD CFCostQty numeric(28,10);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFCostPrice' AND KSQL_COL_TABNAME='T_BD_MaterialInventory')
	ALTER TABLE T_BD_MaterialInventory ADD CFCostPrice numeric(28,10);

-- add by jinbin_guo 20140411 CT_MS_MaterialLoc
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='FSeq' AND KSQL_COL_TABNAME='CT_MS_MaterialLoc')
	ALTER TABLE CT_MS_MaterialLoc ADD FSeq int;
	
-- add by jinbin_guo 20140328 CT_ATS_RepairWORWOItemSpEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWorktimeQty' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFWorktimeQty numeric(28,10);	
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWorktimePrice' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFWorktimePrice numeric(28,10);	
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWorktimeCost' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFWorktimeCost numeric(28,10);	
-- add by jinbin_guo 20140422 T_ATS_RepairWO
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFWipRemark' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFWipRemark nvarchar(255);
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFBizPersonID' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFBizPersonID varchar(44);
-- add by jinbin_guo 20140608 CT_ATS_RepairWORWOItemSpEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFArNumber' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFArNumber nvarchar(100);	
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFReceiveNumber' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFReceiveNumber nvarchar(100);
	
-- add by jinbin_guo 20140616 CT_ATS_RepairWORWOItemSpEntry
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFIsCreatedTo' AND KSQL_COL_TABNAME='CT_ATS_RepairWORWOItemSpEntry')
	ALTER TABLE CT_ATS_RepairWORWOItemSpEntry ADD CFIsCreatedTo int default 0;
-- add by jinbin_guo 20140616 T_ATS_RepairWO
IF NOT EXISTS (SELECT 1 FROM KSQL_USERCOLUMNS WHERE KSQL_COL_NAME ='CFBizDeptID' AND KSQL_COL_TABNAME='T_ATS_RepairWO')
	ALTER TABLE T_ATS_RepairWO ADD CFBizDeptID varchar(44);
