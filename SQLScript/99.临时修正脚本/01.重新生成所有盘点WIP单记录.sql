truncate table CT_SYN_DMSCheckWipNo;
insert into CT_SYN_DMSCheckWipNo(FID,CFServiceOrgUnitID,CFKeyWip)
select distinct dbo.newbosid('92B6011A'), a.CFWip+'-'+b.CFServiceOrgUnitID,convert(varchar(4),a.CFCreateTime,120)
from CT_SYN_DMSWipBillEntry a
left join CT_SYN_DMSWipBill b on b.FID=a.FParentID
where (a.CFAccountCode='I0000013' or a.CFAccountCode='I0000014' or a.CFAccountCode='I000005')
 and not exists (select 1 from CT_SYN_DMSCheckWipNo c where c.CFKeyWip= a.CFWip+'-'+convert(varchar(4),a.CFCreateTime,120) and c.CFServiceOrgUnitID=b.CFServiceOrgUnitID);
