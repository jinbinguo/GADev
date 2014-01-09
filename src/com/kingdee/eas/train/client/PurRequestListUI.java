/**
 * output package name
 */
package com.kingdee.eas.train.client;

import org.apache.log4j.Logger;

import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.base.commonquery.client.CommonQueryDialog;
import com.kingdee.eas.base.commonquery.client.CustomerQueryPanel;

/**
 * output class name
 */
public class PurRequestListUI extends AbstractPurRequestListUI {
	private static final Logger logger = CoreUIObject
			.getLogger(PurRequestListUI.class);
	
	private PurRequestQueryUI filterDialog;

	/**
	 * output class constructor
	 */
	public PurRequestListUI() throws Exception {
		super();
	}

	/**
	 * output storeFields method
	 */
	public void storeFields() {
		super.storeFields();
	}

	protected com.kingdee.eas.framework.ICoreBase getBizInterface()
			throws Exception {
		return com.kingdee.eas.train.PurRequestFactory.getRemoteInstance();
	}

	/**
	 * output createNewData method
	 */
	protected com.kingdee.bos.dao.IObjectValue createNewData() {
		com.kingdee.eas.train.PurRequestInfo objectValue = new com.kingdee.eas.train.PurRequestInfo();

		return objectValue;
	}

	@Override
	protected boolean initDefaultFilter() {
		// TODO Auto-generated method stub
		return true;
	}
	
	protected CommonQueryDialog initCommonQueryDialog() {
		CommonQueryDialog dialog = super.initCommonQueryDialog();
		try {
			dialog.addUserPanel(getFilterDialog());
			dialog.setShowFilter(true);
			dialog.setShowSorter(true);
			dialog.setHeight(380);
			dialog.setWidth(500);
			dialog.setTitle("≤È—Ø");

		} catch (Exception e) {
			handUIException(e);
		}
		return dialog;
	}

	private CustomerQueryPanel getFilterDialog() throws Exception {
		if (filterDialog == null)
			filterDialog = new PurRequestQueryUI();
		return filterDialog;

	}
	
}