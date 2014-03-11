/**
 * output package name
 */
package com.kingdee.eas.ga.rs.client;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.IColumn;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.KDPromptBox.DefaultEditor;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.auto4s.rsm.rs.util.client.RsQueryF7Utils;
import com.kingdee.eas.myframework.vo.RequireCompCollection;

/**
 * output class name
 */
public class RepairManEditUI extends AbstractRepairManEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(RepairManEditUI.class);
    
    public RepairManEditUI() throws Exception  {
        super();
    }
 
    public void onLoad() throws Exception {
    	super.onLoad();
    	actionMultiColumnSort.setVisible(false);
    	actionAudit.setVisible(false);
    	actionUnAudit.setVisible(false);
    	actionSubmit.setVisible(false);
    	actionCopy.setVisible(false);
    	
    	KDBizPromptBox prmtVehicle = new KDBizPromptBox();
    	RsQueryF7Utils.makeVehicleF7(prmtVehicle);
    	prmtVehicle.setDisplayFormat("$plateNum$");
    	DefaultEditor editor = (DefaultEditor) prmtVehicle.getEditor();
    	IColumn column = kdtEntrys.getColumn("vehicle");
    	column.setEditor(new KDTDefaultCellEditor(prmtVehicle));
    }
    
    public RequireCompCollection registerRequireComp() throws Exception {
		txtNumber.setRequired(true);
		RequireCompCollection requireCompCol = new RequireCompCollection();
		requireCompCol.add(txtname);
		requireCompCol.add(kdtEntrys,"vehicle");
		return requireCompCol;
	}
    
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception  {
        return com.kingdee.eas.ga.rs.RepairManFactory.getRemoteInstance();
    }


    protected IObjectValue createNewDetailData(KDTable table)  {
		
        return new com.kingdee.eas.ga.rs.RepairManEntryInfo();
    }


    protected com.kingdee.bos.dao.IObjectValue createNewData()  {
        com.kingdee.eas.ga.rs.RepairManInfo objectValue = new com.kingdee.eas.ga.rs.RepairManInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
		
        return objectValue;
    }

}