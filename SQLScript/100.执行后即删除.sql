update a
set a.CFBizPersonID=(select b.FID from T_BD_Person b
											left join T_ORG_PositionMember c on b.fid=c.FPersonID
											left join T_ORG_Position d on d.FID=c.FPositionID
											left join T_ORG_BaseUnit e on e.fid=d.FAdminOrgUnitID
											where e.FNumber like '1003%' and b.FName_l2=a.CFSaler)
from T_ATS_RepairWO a
left join T_ORG_BaseUnit a1 on a.forgUnitId=a1.fid
where a.CFBizPersonID is null and a1.FNumber='1003';

update a
set a.CFBizPersonID=(select b.FID from T_BD_Person b
											left join T_ORG_PositionMember c on b.fid=c.FPersonID
											left join T_ORG_Position d on d.FID=c.FPositionID
											left join T_ORG_BaseUnit e on e.fid=d.FAdminOrgUnitID
											where e.FNumber like '1001%' and b.FName_l2=a.CFSaler)
from T_ATS_RepairWO a
left join T_ORG_BaseUnit a1 on a.forgUnitId=a1.fid
where a.CFBizPersonID is null and a1.FNumber='1001'


select fnumber,cfsaler,cfbizpersonid from T_ATS_RepairWO where cfbizpersonid is null