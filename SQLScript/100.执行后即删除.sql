
update CT_MS_materialLoc a
set FParentID=(select fid from T_BD_MaterialInventory where FMaterialId=a.FPrentId and forgunit=(select fid from T_ORG_BaseUnit where fnumber='1001')
where FParentID not in (select fid from T_BD_Material)