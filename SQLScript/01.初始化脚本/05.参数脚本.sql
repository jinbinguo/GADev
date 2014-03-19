
--维修工单套打路径配置，用于反写打印标记
if not exists (select * from t_bas_param where fid = 'zfcAAAALI5CogfPn')
insert into t_bas_param(FID, FDefaultVal_L1, FDefaultVal_L2, FDefaultVal_L3, FValueAlias_L1, FValueAlias_L2, FValueAlias_L3, FDesc_L1, FDesc_L2, FDesc_L3, FIsUserDefined, FName_L1, FName_L2, FName_L3, FNumber, FIsGroupControl, FCanBeModified, FDefaultVal, FOrgType, FCreatorID, FCreateTime, FLastUpdateUserID, FLastUpdateTime, FValueRange, FDataType, FSubSysID, FBaseType, FUserEdit, FUserClass, FValueAlias, FControlUnitID)
 values 
(N'zfcAAAALI5CogfPn', null, null, null, null, null, null, null, N'维修工单结算套打路径，多个套打模版用“;”隔开', null, 0, null, N'工单结算套打路径', null, N'GA001', 0, 1, null, 0, '00000000-0000-0000-0000-00000000000013B7DE7F', {ts'2014-03-18 23:23:04'}, '00000000-0000-0000-0000-00000000000013B7DE7F', {ts'2014-03-19 10:37:42'}, N'zfcAAAALI5EbKFGT', 0, N'com.kingdee.eas.auto4s.rsm.rsm', null, 0, null, null, '00000000-0000-0000-0000-000000000000CCE7AED4');

if not exists (select * from t_bas_paramvaluerange where fid = 'zfcAAAALI5EbKFGT')
insert into t_bas_paramvaluerange(FID, FexclusValue_L1, FexclusValue_L2, FexclusValue_L3, FvalueEnum_L1, FvalueEnum_L2, FvalueEnum_L3, FmaxValue, FminValue, FvalueEnum, FexclusValue)
 values 
(N'zfcAAAALI5EbKFGT', null, null, null, null, null, null, null, null, null, null);

