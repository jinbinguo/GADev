package com.kingdee.eas.scm.im.inv.client;

import java.math.BigDecimal;
import java.util.HashMap;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.event.KDTDataFillListener;
import com.kingdee.bos.ctrl.kdf.table.event.KDTDataRequestEvent;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.basedata.org.PurchaseOrgUnitInfo;
import com.kingdee.eas.basedata.org.SaleOrgUnitInfo;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.KDTableUtils;
import com.kingdee.eas.myframework.util.OrgUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.jdbc.rowset.IRowSet;

public class InventoryListUIPIEx extends InventoryListUI {

	private HashMap<String, SaleOrgUnitInfo> hashSaleOrgCache = new HashMap<String, SaleOrgUnitInfo>();

	public InventoryListUIPIEx() throws Exception {
		super();
	}

	@Override
	public void onLoad() throws Exception {
		super.onLoad();
		KDTableUtils.formatDecimal(tblMain, "salePrice", true);
		KDTableUtils.formatDecimal(tblMain, "saleTaxPrice", true);
	}

	@Override
	protected void initListener() {
		super.initListener();
		tblMain.addKDTDataFillListener(new KDTDataFillListener() {
			public void afterDataFill(KDTDataRequestEvent e) {
				int firstRow = e.getFirstRow();
				int lastRow = e.getLastRow();
				try {
					resetTableExValue(firstRow, lastRow);
				} catch (Exception exc) {
					UIUtils.handUIException(exc);
				}
			}

		});
	}

	private void resetTableExValue(int firstRow, int lastRow) throws Exception {
		for (int i = firstRow; i <= lastRow; i++) {
			IRow row = tblMain.getRow(i);
			String storageOrgUnitId = (String) row.getCell("storageOrgUnit.id").getValue();
			String materialId = (String) row.getCell("material.id").getValue();
			String warehouseId = (String) row.getCell("warehouse.id").getValue();

			SaleOrgUnitInfo saleOrgUnitInfo = hashSaleOrgCache.get(storageOrgUnitId);
			if (saleOrgUnitInfo == null) {
				OrgUnitInfo storageOrgUnitInfo = new OrgUnitInfo();
				storageOrgUnitInfo.put("id", storageOrgUnitId);
				saleOrgUnitInfo = OrgUtils.castToSaleOrg(OrgUtils.getOrgUnitInfoByRelation(null, storageOrgUnitInfo,OrgType.Storage, OrgType.Sale, true));
				hashSaleOrgCache.put(storageOrgUnitId, saleOrgUnitInfo);
			}
			BigDecimal salePrice = getSalePrice(materialId, saleOrgUnitInfo);
			String loc = getLocationNo(storageOrgUnitId,materialId, warehouseId);
			tblMain.getRow(i).getCell("salePrice").setValue(salePrice);
			tblMain.getRow(i).getCell("saleTaxPrice").setValue(salePrice.multiply(new BigDecimal(1.17)));
			tblMain.getRow(i).getCell("locationNo").setValue(loc);

		}
	}

	/**
	 * 取得参考售价
	 * 
	 * @param materialId
	 * @param saleOrgUnitInfo
	 * @return
	 * @throws Exception
	 */
	private BigDecimal getSalePrice(String materialId,
			SaleOrgUnitInfo saleOrgUnitInfo) throws Exception {
		if (saleOrgUnitInfo == null)
			return BigDecimal.ZERO;
		String sql = String
				.format(
						"select isnull(FPrice,0) FPrice from T_BD_MaterialSales where FMaterialID='%s' and FOrgUnit='%s'",
						materialId, saleOrgUnitInfo.getString("id"));
		IRowSet rs = DBUtils.executeQuery(null, sql);
		if (rs != null && rs.next())
			return rs.getBigDecimal("FPrice");
		return BigDecimal.ZERO;
	}

	/**
	 * 取得成本价
	 * 
	 * @param materialId
	 * @param storageOrgUnitId
	 * @return
	 * @throws Exception
	 *             private BigDecimal getCostPrice(String materialId, String
	 *             storageOrgUnitId) throws Exception { String sql =
	 *             String.format("select isnull(CFCostPrice,0) CFCostPrice from T_BD_MaterialInventory where FMaterialID='%s' and FOrgUnit='%s'"
	 *             , materialId,storageOrgUnitId); IRowSet rs =
	 *             DBUtils.executeQuery(null, sql); if (rs != null && rs.next())
	 *             return rs.getBigDecimal("CFCostPrice"); return
	 *             BigDecimal.ZERO; }
	 */

	/** 取得仓位号 */

	private String getLocationNo(String storeageOrgUnitId, String materialId, String warehouseId)
			throws Exception {
		String sql = String.format("select CFLoc from CT_MS_materialLoc" +
				" where FParentID=(select Fid from T_BD_MaterialInventory where forgunit='%s' and fmaterialid='%s')" +
				" and CFBmwLoc=(select CFBmwLoc from T_DB_WAREHOUSE where fid='%s')",
				storeageOrgUnitId,materialId, warehouseId);
		IRowSet rs = DBUtils.executeQuery(null, sql);
		if (rs != null && rs.next())
			return rs.getString("CFLoc");
		return "";
	}
}
