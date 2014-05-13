/**
 * output package name
 */
package com.kingdee.eas.auto4s.bdm.rsm.client;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemCollection;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.eas.auto4s.bdm.rsm.FastSearchTypeEnum;
import com.kingdee.eas.auto4s.bdm.rsm.RepairItemInfo;
import com.kingdee.eas.auto4s.bdm.util.BasedataControlUtils;
import com.kingdee.eas.common.EASBizException;

/**
 * output class name
 */
public class RepairItemEntryListUIPIEx extends RepairItemEntryListUI
{
    private static final Logger logger = CoreUIObject.getLogger(RepairItemEntryListUIPIEx.class);
    
    public RepairItemEntryListUIPIEx() throws Exception {
        super();
    }
    
    @Override
	protected FilterInfo getDefaultFilterForQuery() {
    	//return super.getDefaultFilterForQuery();
    	String parentNumber = getParentNumber();
    	FilterInfo defaultFilter = getDefaultFilter();
    	FilterInfo expandFilter = getExpandFilter();
    	
		FilterInfo megerFilter = new FilterInfo();
		RepairItemInfo info = new RepairItemInfo();
		try {
			megerFilter = BasedataControlUtils.getBasedataControlFilter(info.getBOSType());
		} catch (EASBizException e) {
			handUIException(e);
		} catch (BOSException e) {
			handUIException(e);
		}
		FilterItemCollection fic = megerFilter.getFilterItems();
		int i = 0;
		for (int size = fic.size(); i < size; i++) {
			FilterItemInfo baseInfo = fic.get(i);
			if (null != baseInfo)
				baseInfo.setPropertyName((new StringBuilder()).append("parent.").append(baseInfo.getPropertyName()).toString());
		}
		FilterInfo filterInfo = new FilterInfo();
		filterInfo.getFilterItems().add(new FilterItemInfo("parent.IsUse", new Integer(1),CompareType.EQUALS));
		FilterInfo searchFilter = new FilterInfo();
		if (!com.kingdee.bos.ctrl.swing.StringUtils.isEmpty(parentNumber)) {
			searchFilter.getFilterItems().add(new FilterItemInfo("Upper(parent.number)",(new StringBuilder()).append("%").append(parentNumber).append("%").toString().toUpperCase(), CompareType.LIKE,false, true));
			searchFilter.getFilterItems().add(new FilterItemInfo("parent.name", (new StringBuilder()).append("%").append(parentNumber).append("%").toString(), CompareType.LIKE, false, false));
			searchFilter.setMaskString("#0 OR #1");
		}

		try {
			if (null != megerFilter && 0 < megerFilter.toString().trim().length())
				filterInfo.mergeFilter(megerFilter, "AND");

			if (null != searchFilter && 0 < searchFilter.toString().trim().length())
				filterInfo.mergeFilter(searchFilter, "AND");

			if (null != defaultFilter && 0 < defaultFilter.toString().trim().length())
				filterInfo.mergeFilter(defaultFilter, "AND");

			if (null != expandFilter && 0 < expandFilter.toString().trim().length())
				filterInfo.mergeFilter(expandFilter, "AND");
		} catch (BOSException e) {
			handUIException(e);
		}

		return filterInfo;
	}
    

    protected void kdFastSearch_actionPerformed(ActionEvent e) throws Exception {

		//super.kdFastSearch_actionPerformed(e);

		setDefaultFilter(new FilterInfo());

		String fastSearchStr = fastSearchTextField.getText().trim();
		StringBuffer fastSearchStrBuffer = new StringBuffer("%");
		fastSearchStrBuffer.append(fastSearchStr).append("%");
		if (!com.kingdee.bos.ctrl.swing.StringUtils.isEmpty(fastSearchStr)) {

			String fastSearchTypeEnumStr = ((FastSearchTypeEnum) fastSearchCombox.getSelectedObjects()[0]).getValue();
			FilterItemCollection filterItemCollection = getDefaultFilter().getFilterItems();
			if ("0".equals(fastSearchTypeEnumStr)) {
				FilterInfo numberNameFilter = new FilterInfo();
				if (isLikeSearchCheckBox.isSelected()) {
					numberNameFilter.getFilterItems().add(new FilterItemInfo("parent.number",fastSearchStrBuffer.toString().toUpperCase(),CompareType.LIKE));
					numberNameFilter.getFilterItems().add(new FilterItemInfo("parent.name",fastSearchStrBuffer.toString(), CompareType.LIKE));
					numberNameFilter.setMaskString("#0 OR #1");
					getDefaultFilter().mergeFilter(numberNameFilter, "AND");
				} else {
					numberNameFilter.getFilterItems().add(new FilterItemInfo("parent.number", fastSearchStr,CompareType.EQUALS));
					numberNameFilter.getFilterItems().add(new FilterItemInfo("parent.name", fastSearchStr,CompareType.EQUALS));
					numberNameFilter.setMaskString("#0 OR #1");
					getDefaultFilter().mergeFilter(numberNameFilter, "AND");
				}
			} else if ("1".equals(fastSearchTypeEnumStr)) {
				if (isLikeSearchCheckBox.isSelected())
					filterItemCollection.add(new FilterItemInfo("parent.number", fastSearchStrBuffer,CompareType.LIKE));

				else
					filterItemCollection.add(new FilterItemInfo("parent.number",fastSearchStr, CompareType.EQUALS));
			} else if ("2".equals(fastSearchTypeEnumStr))

				if (isLikeSearchCheckBox.isSelected())
					filterItemCollection.add(new FilterItemInfo("parent.name",fastSearchStrBuffer, CompareType.LIKE));

				else
					filterItemCollection.add(new FilterItemInfo("parent.name",fastSearchStr, CompareType.EQUALS));
		}

		e.setSource(btnRefresh);
		super.actionRefresh_actionPerformed(e);
	}
   

}