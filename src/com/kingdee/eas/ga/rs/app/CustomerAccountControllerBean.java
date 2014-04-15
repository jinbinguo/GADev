package com.kingdee.eas.ga.rs.app;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.metadata.entity.EntityObjectInfo;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SorterItemCollection;
import com.kingdee.bos.metadata.entity.SorterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.DataBaseInfo;
import com.kingdee.eas.framework.util.FilterUtility;
import com.kingdee.eas.myframework.util.CodingRuleUtils;
import com.kingdee.util.BaseException;
import com.kingdee.util.NumericExceptionSubItem;

public class CustomerAccountControllerBean extends AbstractCustomerAccountControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.ga.rs.app.CustomerAccountControllerBean");
    private String controlType;
    
    /**
     * 检查是否编码重复
     */
    protected void _checkNumberDup(Context ctx, IObjectValue model) throws EASBizException, BOSException {
    	try {
    		_checkNumberDupOrginal(ctx, model);
    		
    	} catch (BaseException e) {
    		String orgId = getCurrentOrgId(ctx,model);
    		//若在编码规则为“不允许断号”，取得到已存在的编码时，需重新取号，直到取得可用编号，当重复取号的次数>20时，停止取号
    		if (!CodingRuleUtils.isUseIntermitNumber(ctx,(CoreBaseInfo) model,orgId)) {
    			throw new EASBizException(new NumericExceptionSubItem("",e.getMessage()));
    		}
    		boolean isCheckNumberDup=false;
    		int maxCheckCount = 20;
    		int currentCheckCount = 0;
    		do {
    			try {
    				model.put("number", null);
    				setNumberFromCodingRule(ctx, model); //重新取号
    				currentCheckCount++;
    				_checkNumberDupOrginal(ctx, model);
    				isCheckNumberDup=true;
    			} catch (Exception ee) {
    				if (currentCheckCount==maxCheckCount)
    					throw new EASBizException(new NumericExceptionSubItem("","超过20次自动获取编码，自动退出取数，请检查编码规则后再做调整！"));
    			}
			} while (!isCheckNumberDup);   
    	}
    }
    
    protected void _checkNameDup(Context ctx, IObjectValue model) throws BOSException, EASBizException {
		DataBaseInfo dataBaseInfo = (DataBaseInfo)model;
		FilterInfo filter = new FilterInfo();
		FilterItemInfo filterItem = new FilterItemInfo("name", dataBaseInfo.getName(), CompareType.EQUALS);
		IObjectValue orgUnitInfo = (IObjectValue) model.get("orgUnit");
		FilterItemInfo filterItemOrgUnit = new FilterItemInfo("orgUnit", orgUnitInfo.getString("id"), CompareType.EQUALS);
		filter.getFilterItems().add(filterItemOrgUnit);
		filter.getFilterItems().add(filterItem);
		if(dataBaseInfo.getId() != null)  {
			filterItem = new FilterItemInfo("id", dataBaseInfo.getId(), CompareType.NOTEQUALS);
			filter.getFilterItems().add(filterItem);
			//filter.setMaskString("#0 and #1");
		}
		if(getControlType(ctx, dataBaseInfo).equals(""))  {
			FilterInfo filterCU = getFilterForDefaultCU(ctx, model);
			if(FilterUtility.hasFilterItem(filterCU))
				filter.mergeFilter(filterCU, "AND");
		    }
		EntityViewInfo view = new EntityViewInfo();
		view.setFilter(filter);
		SorterItemCollection sorter = new SorterItemCollection();
		sorter.add(new SorterItemInfo("id"));
		if(super._exists(ctx, filter)) {
			String name = (new StringBuilder()).append(_getPropertyAlias(ctx, dataBaseInfo, "name")).append(dataBaseInfo.getName()).toString();
			throw new EASBizException(EASBizException.CHECKDUPLICATED, new Object[] {name});
		} else  {
			return;
		 }
    }
    
    /**
     * 添加按组织隔离，大部分代码从_checkNumberDup来
     * @param ctx
     * @param model
     * @throws BOSException
     * @throws EASBizException
     */
    private void _checkNumberDupOrginal(Context ctx, IObjectValue model)
			throws BOSException, EASBizException {
		DataBaseInfo dataBaseInfo = (DataBaseInfo) model;
		FilterInfo filter = new FilterInfo();
		FilterItemInfo filterItem = new FilterItemInfo("number", dataBaseInfo.getNumber(), CompareType.EQUALS);
		IObjectValue orgUnitInfo = (IObjectValue) model.get("orgUnit");
		FilterItemInfo filterItemOrgUnit = new FilterItemInfo("orgUnit", orgUnitInfo.getString("id"), CompareType.EQUALS);
		filter.getFilterItems().add(filterItem);
		filter.getFilterItems().add(filterItemOrgUnit);
		if (dataBaseInfo.getId() != null) {
			filterItem = new FilterItemInfo("id", dataBaseInfo.getId(),CompareType.NOTEQUALS);
			filter.getFilterItems().add(filterItem);
		}
		if (getControlType(ctx, dataBaseInfo).equals("")) {
			FilterInfo filterCU = getFilterForDefaultCU(ctx, model);
			if (FilterUtility.hasFilterItem(filterCU))
				filter.mergeFilter(filterCU, "AND");
		}
		EntityViewInfo view = new EntityViewInfo();
		view.setFilter(filter);
		SorterItemCollection sorter = new SorterItemCollection();
		sorter.add(new SorterItemInfo("id"));
		if (super._exists(ctx, filter)) {
			String number = (new StringBuilder()).append(_getPropertyAlias(ctx, dataBaseInfo, "number")).append(dataBaseInfo.getNumber()).toString();
			throw new EASBizException(EASBizException.CHECKDUPLICATED,new Object[] { number });
		} else {
			return;
		}
	}
    
    
    private String getControlType(Context ctx, DataBaseInfo info)
			throws BOSException {
		if (controlType != null)
			return controlType;
		controlType = "";
		EntityObjectInfo eoi = getBOSEntity(ctx, info);
		boolean hasShareType = false;
		hasShareType = eoi.containsExtendedPropertyKey("controlType");
		if (hasShareType)
			controlType = eoi.getExtendedProperty("controlType");
		else if (eoi.getBaseEntity().getName().equals("DataBaseD"))
			controlType = "D";
		return controlType;
	}
    
}