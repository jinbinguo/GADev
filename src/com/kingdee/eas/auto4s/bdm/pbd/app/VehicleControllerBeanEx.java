package com.kingdee.eas.auto4s.bdm.pbd.app;

import org.apache.log4j.Logger;
import javax.ejb.*;
import java.rmi.RemoteException;
import com.kingdee.bos.*;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.metadata.rule.RuleExecutor;
import com.kingdee.bos.metadata.MetaDataPK; //import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.framework.ejb.AbstractEntityControllerBean;
import com.kingdee.bos.framework.ejb.AbstractBizControllerBean; //import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;

import java.lang.String;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemCollection;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleFactory;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

import java.util.List;
import java.util.Set;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.auto4s.bdm.pbd.BdmPbdException;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.StringUtils;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleCollection;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.eas.framework.app.DataBaseControllerBean;
import com.kingdee.eas.framework.DataBaseCollection;

public class VehicleControllerBeanEx extends
		com.kingdee.eas.auto4s.bdm.pbd.app.VehicleControllerBean {
	private static Logger logger = Logger
			.getLogger("com.kingdee.eas.auto4s.bdm.pbd.app.VehicleControllerBeanEx");

	protected List isVehicleExistByEngineNum(Context ctx, IObjectValue model,
			List list) throws BOSException, BdmPbdException {

		if (model == null)
			throw new IllegalArgumentException();

		VehicleInfo info = (VehicleInfo) model;
		FilterInfo filter = new FilterInfo();
		FilterItemCollection fic = filter.getFilterItems();
		if (!StringUtils.isEmpty(info.getEngineNum())) {
			fic.add(new FilterItemInfo("engineNum", info.getEngineNum(),CompareType.EQUALS));

			if (info.getBrand() == null)
				fic.add(new FilterItemInfo("brand", null));
			else
				fic.add(new FilterItemInfo("brand", info.getBrand().getId(),CompareType.EQUALS));
			
			if (info.getOrgUnit() != null)
				fic.add(new FilterItemInfo("orgUnit", info.getOrgUnit().getString("id")));
			else fic.add(new FilterItemInfo("orgUnit", null));

			if (info.getId() != null) {
				fic.add(new FilterItemInfo("id", info.getId(),CompareType.NOTEQUALS));
				filter.setMaskString("#0 AND #1 AND #2 AND #3");
			} else {
				filter.setMaskString("#0 AND #1 AND #2");
			}
			EntityViewInfo evi = new EntityViewInfo();
			evi.setFilter(filter);
			VehicleCollection vehicleCollection = VehicleFactory
					.getLocalInstance(ctx).getVehicleCollection(evi);

			if (vehicleCollection != null && vehicleCollection.size() > 0)
				list.add((new StringBuilder()).append("发动机号：").append(
						info.getEngineNum()).toString());
		}

		return list;
	}

	protected List isVehicleExistByPlateNum(Context ctx, IObjectValue model,
			List list) throws BOSException, BdmPbdException {

		if (model == null)
			throw new IllegalArgumentException();

		VehicleInfo info = (VehicleInfo) model;
		FilterInfo filter = new FilterInfo();
		FilterItemCollection fic = filter.getFilterItems();
		if (!StringUtils.isEmpty(info.getPlateNum())) {
			fic.add(new FilterItemInfo("plateNum", info.getPlateNum(),CompareType.EQUALS));
			
			if (info.getOrgUnit() != null)
				fic.add(new FilterItemInfo("orgUnit", info.getOrgUnit().getString("id")));
			else fic.add(new FilterItemInfo("orgUnit", null));

			if (info.getId() != null) {
				fic.add(new FilterItemInfo("id", info.getId(),CompareType.NOTEQUALS));
				filter.setMaskString("#0 AND #1 AND #2");
			}
			EntityViewInfo evi = new EntityViewInfo();
			evi.setFilter(filter);
			VehicleCollection vehicleCollection = VehicleFactory.getLocalInstance(ctx).getVehicleCollection(evi);

			if (vehicleCollection != null && vehicleCollection.size() > 0)

				list.add((new StringBuilder()).append("车牌号：").append(info.getPlateNum()).toString());
		}

		return list;
	}

	protected List isVehicleExistByVIN(Context ctx, IObjectValue model,
			List list) throws BOSException, BdmPbdException {

		if (model == null)
			throw new IllegalArgumentException();

		VehicleInfo info = (VehicleInfo) model;
		FilterInfo filter = new FilterInfo();
		FilterItemCollection fic = filter.getFilterItems();
		if (!StringUtils.isEmpty(info.getVIN())) {
			fic.add(new FilterItemInfo("vIN", info.getVIN(),CompareType.EQUALS));
			
			if (info.getOrgUnit() != null)
				fic.add(new FilterItemInfo("orgUnit", info.getOrgUnit().getString("id")));
			else fic.add(new FilterItemInfo("orgUnit", null));

			if (info.getId() != null) {
				fic.add(new FilterItemInfo("id", info.getId(),CompareType.NOTEQUALS));

				filter.setMaskString("#0 AND #1 AND #2");
			}
			EntityViewInfo evi = new EntityViewInfo();
			evi.setFilter(filter);
			VehicleCollection vehicleCollection = VehicleFactory.getLocalInstance(ctx).getVehicleCollection(evi);

			if (vehicleCollection != null && vehicleCollection.size() > 0)
				list.add((new StringBuilder()).append("底盘号：").append(info.getVIN()).toString());
		}
		return list;
	}
	
	@Override
	protected IObjectValue _getVehicleByVin(Context ctx, String VIN)
			throws BOSException, EASBizException, BdmPbdException {
	
		return super._getVehicleByVin(ctx, VIN);
		
	}

}
