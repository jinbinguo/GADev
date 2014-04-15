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
	 * 根据组织的委托关系，获取默认委托组织
	 * 若 isDefault = true,则取默认的委托组织，或取首笔记录数的数据
	 * 若 isDefault = false 则取只有1笔记录数的数据，若有多笔数据，则报异常
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
						String.format("%s[%s]有多个的委托%s关系,请调整代码",fromOrgType.getAlias(),fromOrgUnitInfo.getString("name"),
								toOrgType.getAlias())));
			return orgUnitCol.get(0);
		}
	}
	
	/**
	 * 根据组织的委托关系，获取相应组织
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
					String.format("未配置%s[%s]的委托%s关系,请先配置",fromOrgType.getAlias(),fromOrgUnitInfo.getString("name"),
							toOrgType.getAlias())));
		}
		return orgUnitCol;
	}
	
	/**
	 * 组织类型转换成采购组织
	 */
	public static PurchaseOrgUnitInfo castToPurchaseOrg(OrgUnitInfo orgUnitInfo) {
		return (PurchaseOrgUnitInfo) orgUnitInfo.cast(PurchaseOrgUnitInfo.class);
	}
	
	/**
	 * 组织类型转换成销售组织
	 * @param orgUnitInfo
	 * @return
	 */
	public static SaleOrgUnitInfo castToSaleOrg(OrgUnitInfo orgUnitInfo) {
		return (SaleOrgUnitInfo)orgUnitInfo.cast(SaleOrgUnitInfo.class);
	}
	
	/**
	 * 组织类型转行政组织
	 * @param orgUnitInfo
	 * @return
	 */
	public static AdminOrgUnitInfo castToAmin(OrgUnitInfo orgUnitInfo) {
		return (AdminOrgUnitInfo)orgUnitInfo.cast(AdminOrgUnitInfo.class);
	}
	
	
}
