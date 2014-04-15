﻿-- add by jibin_guo 20141111 厂家业务系统WIP单中间表
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_SYN_DMSWipBill')
Create Table CT_SYN_DMSWipBill ( FNumber NVARCHAR(80),FBizDate DateTime,FID VARCHAR(44) DEFAULT '' NOT NULL ,FCreatorID VARCHAR(44),FCreateTime DateTime,FLastUpdateUserID VARCHAR(44),FLastUpdateTime DateTime,FHandlerID VARCHAR(44),FDescription NVARCHAR(80),FHasEffected INT,FAuditorID VARCHAR(44),FSourceBillID NVARCHAR(80),FSourceFunction NVARCHAR(80),FCompanyID VARCHAR(44),FControlUnitID VARCHAR(44) DEFAULT '11111111-1111-1111-1111-111111111111CCE7AED4',FFivouchered INT,FBaseStatus INT DEFAULT 0 NOT NULL ,FAuditTime DateTime,CFServiceOrgUnitID VARCHAR(44),CONSTRAINT CPK_SYN_DMSWBID1mv PRIMARY KEY (FID));
--WIP头
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_SYN_DMSWipBillEntry')
Create Table CT_SYN_DMSWipBillEntry ( FSeq INT,FID VARCHAR(44) DEFAULT '' NOT NULL ,FParentID VARCHAR(44) DEFAULT '' NOT NULL ,FLineStatus INT DEFAULT 0 NOT NULL ,FLineDesc NVARCHAR(255),CFVin NVARCHAR(100),CFCreateTime DateTime,CFMileage NUMERIC(28,16),CFPlateNum NVARCHAR(100),CFWip NVARCHAR(100),CFAccountCode NVARCHAR(100),CONSTRAINT CPK_SYN_DMSWBEI3mv PRIMARY KEY (FID));
--工时行
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_SYN_DMSWipBillEntry2')
Create Table CT_SYN_DMSWipBillEntry2 ( FID VARCHAR(44) DEFAULT '' NOT NULL ,FSeq INT DEFAULT 0 NOT NULL ,FParentID VARCHAR(44) DEFAULT '' NOT NULL ,CFSeq INT,CFBillNum NVARCHAR(100),CFBillStatus NVARCHAR(100),CFOrderStatus NVARCHAR(100),CFMaterialNum NVARCHAR(100),CFWip NVARCHAR(100),CFChaimCode NVARCHAR(100),CFRealLineSeq INT,CFLastEditTime DateTime,CFRemark NVARCHAR(255),CFDiscountRate NUMERIC(28,10),CFLineSeq INT,CFOrderQty NUMERIC(28,10),CFSalePrice NUMERIC(28,10),CFTaxBillCode NVARCHAR(100),CFAccountCode NVARCHAR(100),CONSTRAINT CPK_SYN_DMSWBE2j4v PRIMARY KEY (FID));
--零件行
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_SYN_DMSWipBillEntry3')
Create Table CT_SYN_DMSWipBillEntry3 ( FID VARCHAR(44) DEFAULT '' NOT NULL ,FSeq INT DEFAULT 0 NOT NULL ,FParentID VARCHAR(44) DEFAULT '' NOT NULL ,CFSeq INT,CFAccountCode NVARCHAR(100),CFRemark NVARCHAR(100),CFDispatchStatus NVARCHAR(100),CFTaxBillCode NVARCHAR(100),CFWip NVARCHAR(100),CFPayCode NVARCHAR(100),CFBillNum NVARCHAR(100),CFBillStatus NVARCHAR(100),CFRealLineSeq INT,CFStandardHour NUMERIC(28,10),CFLastEditTime DateTime,CFDiscountRate NUMERIC(28,10),CFLineSeq INT,CFUnitMI INT,CFHourRate NUMERIC(28,10),CONSTRAINT CPK_SYN_DMSWBE318o PRIMARY KEY (FID));

-- add by jinbin_guo 20141115 厂家交易查询中间表	
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_SYN_DMSInOutQuery')	
Create Table CT_SYN_DMSInOutQuery ( FNumber NVARCHAR(80),FBizDate DateTime,FID VARCHAR(44) DEFAULT '' NOT NULL ,FCreatorID VARCHAR(44),FCreateTime DateTime,FLastUpdateUserID VARCHAR(44),FLastUpdateTime DateTime,FHandlerID VARCHAR(44),FDescription NVARCHAR(80),FHasEffected INT,FAuditorID VARCHAR(44),FSourceBillID NVARCHAR(80),FSourceFunction NVARCHAR(80),FCompanyID VARCHAR(44),FControlUnitID VARCHAR(44) DEFAULT '11111111-1111-1111-1111-111111111111CCE7AED4',FFivouchered INT,FBaseStatus INT DEFAULT 0 NOT NULL ,FAuditTime DateTime,CFServiceOrgUnitID VARCHAR(44),CONSTRAINT CPK_SYN_DMSIOQI9ev PRIMARY KEY (FID));
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_SYN_DMSInOutQueryEntry')
Create Table CT_SYN_DMSInOutQueryEntry ( FSeq INT,FID VARCHAR(44) DEFAULT '' NOT NULL ,FParentID VARCHAR(44) DEFAULT '' NOT NULL ,FLineStatus INT DEFAULT 0 NOT NULL ,FLineDesc NVARCHAR(255),CFBizDate DateTime,CFOption NVARCHAR(100),CFRqn NVARCHAR(100),CFBillNum NVARCHAR(100),CFWip NVARCHAR(100),CFLineSeq INT,CFCustomer NVARCHAR(80),CFSupplier NVARCHAR(80),CFMaterialNum NVARCHAR(100),CFQty NUMERIC(28,10),CFSupplyQty NUMERIC(28,10),CFRemainQty NUMERIC(28,10),CFAudit NVARCHAR(100),CFCost NUMERIC(28,10),CFL NVARCHAR(100),CFT NVARCHAR(100),CFEasSupplierID VARCHAR(44),CFEasCustomerID VARCHAR(44),CFEasWarehouseID VARCHAR(44),CFEasRepairWOID VARCHAR(44),CFEasTaxPrice NUMERIC(28,10),CFEasMaterialID VARCHAR(44),CFEasBaseUnitID VARCHAR(44),CONSTRAINT CPK_SYN_DMSIOQEdev PRIMARY KEY (FID));
-- add by jinbin_guo 20141116 DMS盘点WIPNo记录
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_SYN_DMSCheckWipNo')	
Create Table CT_SYN_DMSCheckWipNo ( FID VARCHAR(44) DEFAULT '' NOT NULL ,CFServiceOrgUnitID VARCHAR(44) DEFAULT '' NOT NULL ,CFKeyWip NVARCHAR(100) DEFAULT '' NOT NULL ,CONSTRAINT CPK_checkWipNo01 PRIMARY KEY (FID));

-- add by jinbin_guo 20140210 维修/配件明细分录
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_ATS_RepairWORWOItemSpEntry')	
Create Table CT_ATS_RepairWORWOItemSpEntry ( FID VARCHAR(44) DEFAULT '' NOT NULL ,FSeq INT DEFAULT 0 NOT NULL ,FParentID VARCHAR(44) DEFAULT '' NOT NULL ,CFSeq INT,CFT VARCHAR(100),CFItemspNum NVARCHAR(100),CFItemspName NVARCHAR(100),CFRepairItemID VARCHAR(44),CFMaterialID VARCHAR(44),CFTaocan NVARCHAR(100),CFQty NUMERIC(28,10),CFPrice NUMERIC(28,10),CFDiscountRate NUMERIC(10,2),CFAmount NUMERIC(28,10),CFI VARCHAR(100),CFIsCT INT,CFUnIssueQty NUMERIC(28,10),CFIssueQty NUMERIC(28,10),CFTaxRate NUMERIC(5,2),CFSettlementObject VARCHAR(100),CFWID VARCHAR(44),CONSTRAINT CPK_ATS_RWORWOImck PRIMARY KEY (FID));

-- add by jinbin_guo 20140210 客户账户
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_RS_CustomerAccount')	
Create Table CT_RS_CustomerAccount ( FName_l1 NVARCHAR(255),FName_l2 NVARCHAR(255),FName_l3 NVARCHAR(255),FNumber NVARCHAR(80),FDescription_l1 NVARCHAR(255),FDescription_l2 NVARCHAR(255),FDescription_l3 NVARCHAR(255),FSimpleName NVARCHAR(80),FID VARCHAR(44) DEFAULT '' NOT NULL ,FCreatorID VARCHAR(44),FCreateTime DateTime,FLastUpdateUserID VARCHAR(44),FLastUpdateTime DateTime,FControlUnitID VARCHAR(44) DEFAULT '11111111-1111-1111-1111-111111111111CCE7AED4',FDeletedStatus INT DEFAULT 1 NOT NULL ,FScheduled INT DEFAULT 0 NOT NULL ,CFRetailSaleType NVARCHAR(100),CFRetailDiscountRate NUMERIC(5,2),CFRepairDiscountRate NUMERIC(5,2),CFTypeCode NVARCHAR(100),CFFinCustomerID VARCHAR(44),CFRepairSaleType NVARCHAR(100),CFSettlementType INT,CONSTRAINT CPK_RS_Customerf72 PRIMARY KEY (FID));

-- add by jinbin_guo 20140210 W
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_RS_W')	
Create Table CT_RS_W ( FName_l1 NVARCHAR(255),FName_l2 NVARCHAR(255),FName_l3 NVARCHAR(255),FNumber NVARCHAR(80),FDescription_l1 NVARCHAR(255),FDescription_l2 NVARCHAR(255),FDescription_l3 NVARCHAR(255),FSimpleName NVARCHAR(80),FID VARCHAR(44) DEFAULT '' NOT NULL ,FCreatorID VARCHAR(44),FCreateTime DateTime,FLastUpdateUserID VARCHAR(44),FLastUpdateTime DateTime,FControlUnitID VARCHAR(44) DEFAULT '11111111-1111-1111-1111-111111111111CCE7AED4',FDeletedStatus INT DEFAULT 1 NOT NULL ,FScheduled INT DEFAULT 0 NOT NULL ,CFTypeCode NVARCHAR(100),CONSTRAINT CPK_RS_WID671tudg8 PRIMARY KEY (FID));

-- add by jinbin_guo 20140227 套餐
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_RS_RepairPackage')	
Create Table CT_RS_RepairPackage ( FName_l1 NVARCHAR(255),FName_l2 NVARCHAR(255),FName_l3 NVARCHAR(255),FNumber NVARCHAR(80),FDescription_l1 NVARCHAR(255),FDescription_l2 NVARCHAR(255),FDescription_l3 NVARCHAR(255),FSimpleName NVARCHAR(80),FID VARCHAR(44) DEFAULT '' NOT NULL ,FCreatorID VARCHAR(44),FCreateTime DateTime,FLastUpdateUserID VARCHAR(44),FLastUpdateTime DateTime,FControlUnitID VARCHAR(44) DEFAULT '11111111-1111-1111-1111-111111111111CCE7AED4',FDeletedStatus INT DEFAULT 1 NOT NULL ,FScheduled INT DEFAULT 0 NOT NULL ,CONSTRAINT CPK_RS_RepairPIdb8 PRIMARY KEY (FID));

-- add by jinbin_guo 20140227 业务类型
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_RS_RepairWOBizType')	
Create Table CT_RS_RepairWOBizType ( FName_l1 NVARCHAR(255),FName_l2 NVARCHAR(255),FName_l3 NVARCHAR(255),FNumber NVARCHAR(80),FDescription_l1 NVARCHAR(255),FDescription_l2 NVARCHAR(255),FDescription_l3 NVARCHAR(255),FSimpleName NVARCHAR(80),FID VARCHAR(44) DEFAULT '' NOT NULL ,FCreatorID VARCHAR(44),FCreateTime DateTime,FLastUpdateUserID VARCHAR(44),FLastUpdateTime DateTime,FControlUnitID VARCHAR(44) DEFAULT '11111111-1111-1111-1111-111111111111CCE7AED4',FDeletedStatus INT DEFAULT 1 NOT NULL ,FScheduled INT DEFAULT 0 NOT NULL ,CONSTRAINT CPK_RS_RprWOBTIg1g PRIMARY KEY (FID));

--add by jinbin_guo 20140304 车辆历史维修备注
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_ATS_VehicleRepairRemark')	
Create Table CT_ATS_VehicleRepairRemark ( FID VARCHAR(44) DEFAULT '' NOT NULL ,FSeq INT DEFAULT 0 NOT NULL ,FParentID VARCHAR(44) DEFAULT '' NOT NULL ,CFSeq INT,CFRemark NVARCHAR(255),CFRepairWOID VARCHAR(44),CFCreateTime DateTime,CONSTRAINT CPK_ATS_VhclRRI1pr PRIMARY KEY (FID));

--add by jinbin_guo 20140310 送修人
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_RS_RepairMan')	
Create Table CT_RS_RepairMan ( FNumber NVARCHAR(80),FBizDate DateTime,FID VARCHAR(44) DEFAULT '' NOT NULL ,FCreatorID VARCHAR(44),FCreateTime DateTime,FLastUpdateUserID VARCHAR(44),FLastUpdateTime DateTime,FHandlerID VARCHAR(44),FDescription NVARCHAR(80),FHasEffected INT,FAuditorID VARCHAR(44),FSourceBillID NVARCHAR(80),FSourceFunction NVARCHAR(80),FCompanyID VARCHAR(44),FControlUnitID VARCHAR(44) DEFAULT '11111111-1111-1111-1111-111111111111CCE7AED4',FFivouchered INT,FBaseStatus INT DEFAULT 0 NOT NULL ,FAuditTime DateTime,CFName NVARCHAR(100),CFTel NVARCHAR(100),CFEmail NVARCHAR(100),CFIdNumber NVARCHAR(100),CFZipCode NVARCHAR(100),CFAddr NVARCHAR(255),CONSTRAINT CPK_RS_RepairMas16 PRIMARY KEY (FID));
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_RS_RepairManEntry')	
Create Table CT_RS_RepairManEntry ( FSeq INT,FID VARCHAR(44) DEFAULT '' NOT NULL ,FParentID VARCHAR(44) DEFAULT '' NOT NULL ,FLineStatus INT DEFAULT 0 NOT NULL ,FLineDesc NVARCHAR(255),CFVehicleID VARCHAR(44),CONSTRAINT CPK_RS_RepairME726 PRIMARY KEY (FID));

--add by jinbin_guo 20140310 客户折扣
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_BD_CustomerDiscount')	
Create Table CT_BD_CustomerDiscount ( FNumber NVARCHAR(80),FBizDate DateTime,FID VARCHAR(44) DEFAULT '' NOT NULL ,FCreatorID VARCHAR(44),FCreateTime DateTime,FLastUpdateUserID VARCHAR(44),FLastUpdateTime DateTime,FHandlerID VARCHAR(44),FDescription NVARCHAR(80),FHasEffected INT,FAuditorID VARCHAR(44),FSourceBillID NVARCHAR(80),FSourceFunction NVARCHAR(80),FCompanyID VARCHAR(44),FControlUnitID VARCHAR(44) DEFAULT '11111111-1111-1111-1111-111111111111CCE7AED4',FFivouchered INT,FBaseStatus INT DEFAULT 0 NOT NULL ,FAuditTime DateTime,CFEffectiveDate DateTime,CFExpirationDate DateTime,CFSaleOrgUnitID VARCHAR(44),CONSTRAINT CPK_BD_Customerip4 PRIMARY KEY (FID));
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_BD_CustomerDiscountEntry')	
Create Table CT_BD_CustomerDiscountEntry ( FSeq INT,FID VARCHAR(44) DEFAULT '' NOT NULL ,FParentID VARCHAR(44) DEFAULT '' NOT NULL ,FLineStatus INT DEFAULT 0 NOT NULL ,FLineDesc NVARCHAR(255),CFAtsCustomerID VARCHAR(44),CFRepairDiscountRate NUMERIC(5,2),CFRetailDiscountRate NUMERIC(5,2),CONSTRAINT CPK_BD_CstmrDEIlp4 PRIMARY KEY (FID));

--add by jinbin_guo 20140402 DMS打印内容中转表
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_SYN_DMSPrintContentEntry')	
CREATE TABLE CT_SYN_DMSPrintContentEntry (FSeq INT NULL,FID VARCHAR(44) DEFAULT '' NOT NULL,FParentID VARCHAR(44) DEFAULT '' NOT NULL,FLineStatus INT DEFAULT 0 NOT NULL,FLineDesc NVARCHAR(255) NULL,CFContent NVARCHAR(2000) NULL,CONSTRAINT CPK_SYN_DMSPCEIhjn PRIMARY KEY (FID));
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_SYN_DMSPrintContent')	
CREATE TABLE CT_SYN_DMSPrintContent (FNumber NVARCHAR(80) NULL,FBizDate DATETIME NULL,FID VARCHAR(44) DEFAULT '' NOT NULL,FCreatorID VARCHAR(44) NULL,FCreateTime DATETIME NULL,FLastUpdateUserID VARCHAR(44) NULL,FLastUpdateTime DATETIME NULL,FHandlerID VARCHAR(44) NULL,FDescription NVARCHAR(80) NULL,FHasEffected INT NULL,FAuditorID VARCHAR(44) NULL,FSourceBillID NVARCHAR(80) NULL,FSourceFunction NVARCHAR(80) NULL,FCompanyID VARCHAR(44) NULL,FControlUnitID VARCHAR(44) DEFAULT '11111111-1111-1111-1111-111111111111CCE7AED4' NULL,FFivouchered INT NULL,FBaseStatus INT DEFAULT 0 NOT NULL,FAuditTime DATETIME NULL,CFServiceOrgUnitID VARCHAR(44),CONSTRAINT CPK_SYN_DMSPCIDejn PRIMARY KEY (FID));

--add by jinbin_guo 20140411 物料货位
If not exists (select 1 from KSQL_USERTABLES where KSQL_TABNAME ='CT_MS_MaterialLoc')	
CREATE TABLE CT_MS_MaterialLoc (FID VARCHAR(44) DEFAULT '' NOT NULL,FSeq INT DEFAULT 0 NOT NULL,FParentID VARCHAR(44) DEFAULT '' NOT NULL,CFSeq INT NULL,CFBmwLoc varchar(100),CFLoc NVARCHAR(100) NULL,CONSTRAINT CPK_MS_MtrlIMLI442 PRIMARY KEY (FID));