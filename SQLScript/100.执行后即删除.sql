update  a
set FParentID=(select fid from T_BD_MaterialInventory
	 where FMaterialId=a.FParentID and 
	 forgunit=(select fid from T_ORG_BaseUnit where fnumber='1001'))
from CT_MS_materialLoc a
where FParentID in (select fid from T_BD_Material)


update a
set a.CFItemSPNum=(select b.FNumber from T_ATS_RepairItem b where b.FID=a.CFRepairItemID)
from CT_ATS_RepairWORWOItemSpEntry a
where a.CFItemspNum<>(select b.FNumber from T_ATS_RepairItem b where b.FID=a.CFRepairItemID)
and a.CFRepairItemID is not null


update CT_ATS_RepairWORWOItemSpEntry
set CFItemspName='',
	  CFItemspNum='',
		CFRepairItemID=''
where CFRepairItemID='?';

UPDATE T_ATS_RWORepairItemEntry
set FRepairItemId='?'
where frepairItemId='?'

