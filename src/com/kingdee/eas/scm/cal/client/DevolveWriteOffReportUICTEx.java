package com.kingdee.eas.scm.cal.client;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.dao.query.SQLExecutor;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.basedata.org.FullOrgUnitCollection;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.scm.cal.CostAdjustBillFactory;
import com.kingdee.eas.scm.cal.CostAdjustBillInfo;
import com.kingdee.eas.scm.cal.util.DBUtil;
import com.kingdee.eas.scm.common.client.SCMGroupClientUtils;
import com.kingdee.eas.scm.im.inv.MaterialReqBillEntryCollection;
import com.kingdee.eas.scm.im.inv.MaterialReqBillEntryFactory;
import com.kingdee.eas.scm.im.inv.MaterialReqBillEntryInfo;
import com.kingdee.eas.scm.im.inv.MaterialReqBillFactory;
import com.kingdee.eas.scm.im.inv.MaterialReqBillInfo;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.NumericExceptionSubItem;

public class DevolveWriteOffReportUICTEx extends DevolveWriteOffReportUI {

	public DevolveWriteOffReportUICTEx() throws Exception {
		super();
		// TODO Auto-generated constructor stubbtn InverseWriteOff
	}

	@Override
	public void actionInverseWriteOff_actionPerformed(ActionEvent e)
			throws Exception {
        ArrayList list = getSelectedIdValues();
        if(list.isEmpty())
        {
            MsgBox.showWarning(this, EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_MustSelected"));
            return;
        }
		 	String ids[] = null;
	        Object obj[] = list.toArray();
	        int length = obj.length;
	        ids = new String[length];
	        System.arraycopy(((Object) (obj)), 0, ids, 0, length);
			String costAdjust="";
	        for (int i = 0; i < ids.length; i++) {
			String sql ="select distinct fnumber from t_cl_costadjustbill where t_cl_costadjustbill.fnumber"+
			" in (select CFAdjustNum from t_Im_Materialreqbillentry where FParentId IN( select DISTINCT FBILLID "+
			"from T_CL_DevolveWriteOffRecord where FParentId='"+ ids[i]+"') and CFIsAdjust=1)";
			SQLExecutor exec = new SQLExecutor(sql);
			IRowSet rs = exec.executeSQL();
					while(rs.next())
					costAdjust +=rs.getString("fnumber")+",";
					}
			if (!costAdjust.equals("")&& costAdjust!= null) {
				costAdjust= costAdjust.substring(0, costAdjust.length()-1);
				MsgBox.showDetailAndOK(null, "已存在生成的成本调整单"+costAdjust+",不允许反核销",
				"请删除成本调整单号"+costAdjust, MsgBox.OK);						
				}
			else{
				super.actionInverseWriteOff_actionPerformed(e);
			}
	}


}
