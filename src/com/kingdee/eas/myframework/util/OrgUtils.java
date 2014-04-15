package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import com.kingdee.bos.Context;
import com.kingdee.eas.basedata.org.AdminOrgUnit;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitCollection;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo;
import com.kingdee.eas.basedata.org.SaleOrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

public class OrgUtils implements Serializable {

	/**
	 * ������֯��ί�й�ϵ����ȡĬ��ί����֯
	 * �� isDefault = true,��ȡĬ�ϵ�ί����֯����ȡ�ױʼ�¼��������
	 * �� isDefault = false ��ȡֻ��1�ʼ�¼�������ݣ����ж�����ݣ����쳣
	 * 
	 * @param ctx
	 * @param orgUnitInfo
	 * @param fromOrgType
	 * @param toOrgType
	 * @param isDefault
	 * @return
	 * @throws Exception
	 */
	public static OrgUnitInfo getOrgUnitInfoByRelation(Context ctx,OrgUnitInfo fromOrgUnitInfo, OrgType fromOrgType, OrgType toOrgType, boolean isDefault) throws Exception {
		OrgUnitCollection orgUnitCol = getOrgUnitColByRelation(ctx, fromOrgUnitInfo, fromOrgType, toOrgType);
		if (isDefault) {
			return orgUnitCol.get(0);
		} else {
			if (orgUnitCol.size() > 1)
				throw new EASBizException(new NumericExceptionSubItem("",
						String.format("%s[%s]�ж����ί��%s��ϵ,���������",fromOrgType.getAlias(),fromOrgUnitInfo.getString("name"),
								toOrgType.getAlias())));
			return orgUnitCol.get(0);
		}
	}
	
	/**
	 * ������֯��ί�й�ϵ����ȡ��Ӧ��֯
	 * @param ctx
	 * @param orgUnitInfo
	 * @param fromOrgType
	 * @param toOrgType
	 * @return
	 * @throws Exception
	 */
	public static OrgUnitCollection getOrgUnitColByRelation(Context ctx, OrgUnitInfo fromOrgUnitInfo, OrgType fromOrgType, OrgType toOrgType) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select b.fid,b.fnumber,b.FName_L2 from T_ORG_UnitRelation a")
		.append(" left join T_ORG_BaseUnit b on b.fid=a.FToUnitID")
		.append(String.format(" where a.FFromUnitID='%s'",fromOrgUnitInfo.getString("id")))
		.append(String.format(" and a.FTypeRelationID=(select fid from T_ORG_TypeRelation c where c.FFromType=%d and c.FToType=%d)",fromOrgType.getValue(),toOrgType.getValue()))
		.append(" order by a.FIsDefault desc");
		IRowSet rs = DBUtils.executeQueryForDialect(ctx, sql.toString());
		OrgUnitCollection orgUnitCol = new OrgUnitCollection();
		while (rs != null && rs.next()) {
			OrgUnitInfo orgUnitInfo = new OrgUnitInfo();
			orgUnitInfo.put("id", rs.getString("fid"));
			orgUnitInfo.put("number", rs.getString("fnumber"));
			orgUnitInfo.put("name", rs.getString("FName_L2"));
			orgUnitCol.add(orgUnitInfo);
			
		}
		if (PublicUtils.isEmpty(orgUnitCol)) {
			throw new EASBizException(new NumericExceptionSubItem("",
					String.format("δ����%s[%s]��ί��%s��ϵ,��������",fromOrgType.getAlias(),fromOrgUnitInfo.getString("name"),
							toOrgType.getAlias())));
		}
		return orgUnitCol;
	}
	
	/**
	 * ��֯����ת���ɲɹ���֯
	 */
	public static PurchaseOrgUnitInfo castToPurchaseOrg(OrgUnitInfo orgUnitInfo) {
		return (PurchaseOrgUnitInfo) orgUnitInfo.cast(PurchaseOrgUnitInfo.class);
	}
	
	/**
	 * ��֯����ת����������֯
	 * @param orgUnitInfo
	 * @return
	 */
	public static SaleOrgUnitInfo castToSaleOrg(OrgUnitInfo orgUnitInfo) {
		return (SaleOrgUnitInfo)orgUnitInfo.cast(SaleOrgUnitInfo.class);
	}
	
	/**
	 * ��֯����ת������֯
	 * @param orgUnitInfo
	 * @return
	 */
	public static AdminOrgUnitInfo castToAmin(OrgUnitInfo orgUnitInfo) {
		return (AdminOrgUnitInfo)orgUnitInfo.cast(AdminOrgUnitInfo.class);
	}
	
	
}
