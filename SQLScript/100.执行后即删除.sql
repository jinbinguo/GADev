update T_ATS_RepairWO
set CFIsPrintedSettle=0
where CFIsPrintedSettle is null

update CT_ATS_RepairWORWOItemSpEntry
set CFAllocateCount
where CFAllocateCount is null



1. DEP应付单.分录添加字段(实体名:sourceEntryAllocateCount 字段名:CFSourceEntryAllocateCount 字段说明:来源工单分录分担次数),
2. 配置支持BOTP
3. 列表界面不可见，编辑界面隐藏
4. 工单转应付单，来源工单分录分担次数 对应 分担次数

1. DEP销售出库.分录添加字段(实体名:sourceEntryAllocateCount 字段名:CFSourceEntryAllocateCount 字段说明:来源工单分录分担次数),
2. 配置支持BOTP
3. 列表界面不可见，编辑界面隐藏
4. 工单转应付单，来源工单分录分担次数 对应 分担次数

1. DEP其他出库.分录添加字段(实体名:sourceEntryAllocateCount 字段名:CFSourceEntryAllocateCount 字段说明:来源工单分录分担次数),
2. 配置支持BOTP
3. 列表界面不可见，编辑界面隐藏
4. 工单转应付单，来源工单分录分担次数 对应 分担次数

update t_ap_otherbillentry
set CFSourceEntryAllocateCount=0
where CFSourceEntryAllocateCount is null

update T_IM_SaleIssueEntry
set CFSourceEntryAllocateCount=0
where CFSourceEntryAllocateCount is null

update T_IM_OtherIssueBillEntry
set CFSourceEntryAllocateCount=0
where CFSourceEntryAllocateCount is null






