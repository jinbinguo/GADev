package com.kingdee.eas.scm.im.inv.client;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.swing.tree.TreePath;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.event.KDTDataFillListener;
import com.kingdee.bos.ctrl.kdf.table.event.KDTDataRequestEvent;
import com.kingdee.bos.ctrl.swing.KDTreeView;
import com.kingdee.bos.ctrl.swing.tree.DefaultKingdeeTreeNode;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.commonquery.client.CommonQueryDialog;
import com.kingdee.eas.basedata.master.material.MaterialGroupInfo;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.basedata.org.SaleOrgUnitInfo;
import com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo;
import com.kingdee.eas.myframework.util.DBUtils;
import com.kingdee.eas.myframework.util.InvokeUtils;
import com.kingdee.eas.myframework.util.KDTableUtils;
import com.kingdee.eas.myframework.util.OrgUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.jdbc.rowset.IRowSet;

public class InventoryListUIPIEx extends InventoryListUI {

	private HashMap<String, SaleOrgUnitInfo> hashSaleOrgCache = new HashMap<String, SaleOrgUnitInfo>();
	private static final BOSUuid rootUuid = BOSUuid.read("111111111111111111111111111=");
	
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
	 * ȡ�òο��ۼ�
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
	 * ȡ�óɱ���
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

	/** ȡ�ò�λ�� */

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
	
    public EntityViewInfo getQueryViewInfo() throws BOSException {

		EntityViewInfo ev = new EntityViewInfo();
		FilterInfo evFilter = new FilterInfo();
		FilterInfo commonFilter = null; 
		try {
			CommonQueryDialog commonQuerydialog = (CommonQueryDialog) InvokeUtils.getFieldValue(this, "commonQuerydialog");
			EntityViewInfo commonEntity = commonQuerydialog.getEntityViewInfoResult();
			if (commonEntity != null)
				commonFilter = commonQuerydialog.getEntityViewInfoResult().getFilter();
		} catch (Exception e) {
			e.printStackTrace();
		}

		evFilter = mergeFilter(evFilter, getDefaultFilterForQuery(), "AND");
		evFilter.mergeFilter(commonFilter, "AND");

		if (getTabName().equals("materialTreeView")) {
			TreePath path = materialTree.getSelectionPath();
			if (path != null) {
				DefaultKingdeeTreeNode node = (DefaultKingdeeTreeNode) path.getLastPathComponent();
				if (node.getUserObject() instanceof MaterialGroupInfo) {
					MaterialGroupInfo materialGroupInfo = (MaterialGroupInfo) node.getUserObject();
					if (!materialGroupInfo.getId().equals(rootUuid))
						evFilter = mergeFilter(evFilter,getMaterialGroupFilterInfo(materialGroupInfo),"AND");
				} else if (node.getUserObject() instanceof MaterialInfo) {
					MaterialInfo materialInfo = (MaterialInfo) node.getUserObject();
					if (!materialInfo.getId().equals(rootUuid))
						evFilter = mergeFilter(evFilter,getMaterialFilterInfo(materialInfo), "AND");
				}
			}
		} else if (getTabName().equals("storageTreeView")) {
			TreePath path = storageTree.getSelectionPath();
			if (path != null) {
				DefaultKingdeeTreeNode node = (DefaultKingdeeTreeNode) path.getLastPathComponent();
				WarehouseInfo warehouseInfo = (WarehouseInfo) node.getUserObject();
				if (!warehouseInfo.getId().equals(rootUuid))
					evFilter = mergeFilter(evFilter,getStorageFilterInfo(warehouseInfo), "AND");
			}
		}

		ev.setFilter(evFilter);
		return ev;
    }
    private String getTabName() {

		if (((KDTreeView) (KDTreeView) kDTabbedPane1.getSelectedComponent()).getName().equals("storageTreeView"))
			return "storageTreeView";
		if (((KDTreeView) (KDTreeView) kDTabbedPane1.getSelectedComponent()).getName().equals("materialTreeView"))
			return "materialTreeView";
		else
			return null;
	}
}
