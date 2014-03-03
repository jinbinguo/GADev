package com.kingdee.eas.basedata.scm.im.inv.app;

import java.util.Iterator;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.framework.DynamicObject;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.eas.basedata.master.material.IMaterialInventory;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.master.material.MaterialInventoryCollection;
import com.kingdee.eas.basedata.master.material.MaterialInventoryFactory;
import com.kingdee.eas.basedata.master.material.MaterialInventoryInfo;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.basedata.scm.im.inv.LocationInfo;
import com.kingdee.eas.basedata.scm.im.inv.WarehouseInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;

public class InvDepServiceHelperEx extends InvDepServiceHelper {
	public static void reWriteMaterialWHLocEx(Context ctx, OrgUnitInfo storage,
			AbstractObjectCollection entries) throws BOSException,
			EASBizException {

		IMaterialInventory iMaterialInventory = MaterialInventoryFactory.getLocalInstance(ctx);
		CoreBaseCollection updates = new CoreBaseCollection();
		Iterator it = entries.iterator();
		do {
			if (!it.hasNext())
				break;
			CoreBaseInfo ent = (CoreBaseInfo) it.next();
			//ObjectUuidPK pk = new ObjectUuidPK(ent.get(ent.getPKField()).toString());
			IObjectValue entry = ent;//(new DynamicObject()).getValue(ent.getBOSType(), pk);
			MaterialInfo material = (MaterialInfo) entry.get("material");
			FilterInfo filter = new FilterInfo();
			filter.getFilterItems().add(new FilterItemInfo("material", material.getId().toString(),CompareType.EQUALS));

			filter.getFilterItems().add(new FilterItemInfo("orgUnit", storage.getId().toString(),CompareType.EQUALS));

			EntityViewInfo view = new EntityViewInfo();
			view.setFilter(filter);

			MaterialInventoryCollection colls = iMaterialInventory
					.getMaterialInventoryCollection(view);
			if (colls.size() > 0) {
				MaterialInventoryInfo materialInventoryInfo = colls.get(0);

				materialInventoryInfo.setDefaultWarehouse((WarehouseInfo) entry.get("defaultWarehouse"));

				materialInventoryInfo.setLocation((LocationInfo) entry.get("defaultLocation"));
				updates.add(materialInventoryInfo);
			}
		} while (true);
		iMaterialInventory.update(updates);
	}
}
