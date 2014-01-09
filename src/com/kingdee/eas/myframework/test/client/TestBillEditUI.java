package com.kingdee.eas.myframework.test.client;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.myframework.test.TestBillEntryInfo;
import com.kingdee.eas.myframework.vo.ResetFieldCollection;
import com.kingdee.eas.myframework.vo.ResetFieldInfo;
import com.kingdee.eas.scm.common.EntryBaseStatusEnum;


public class TestBillEditUI extends AbstractTestBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(TestBillEditUI.class);
    

    public TestBillEditUI() throws Exception {
        super();
    }
 

 
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception {
        return com.kingdee.eas.myframework.test.TestBillFactory.getRemoteInstance();
    }

    /**
     * 行新增默认值
     */
    protected IObjectValue createNewDetailData(KDTable table) {
    	
    	IObjectValue objectValue = super.createNewDetailData(table);
    	objectValue = objectValue.cast(TestBillEntryInfo.class);
		((TestBillEntryInfo)objectValue).setLineStatus(EntryBaseStatusEnum.ALTERING);
		return objectValue;
    }
    

    /**
     * 单据头新增时的默认值
     */
    protected com.kingdee.bos.dao.IObjectValue createNewData() {
        com.kingdee.eas.myframework.test.TestBillInfo objectValue = new com.kingdee.eas.myframework.test.TestBillInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));
        objectValue.setDescription("abcdf");
        return objectValue;
    }
    
    /**
     * 单据头新增时的默认值
     * 这个方法是为了方便系统继承使用，因自动生成createNewData()未能自动调用super.createNewData()
     * 
     */
    protected void createNewDataEx() throws Exception {
    	super.createNewDataEx();
    }
    
    /**
     * 重置拷贝时，将部分值设置初始值
     */
    @Override
    public ResetFieldCollection resetFieldstAfterCopy() throws EASBizException {
    	ResetFieldCollection rfc = super.resetFieldstAfterCopy();
    	rfc.add(new ResetFieldInfo("entrys","lineDesc",null));
    	
    	return rfc;
    }
    
   @Override
   public OrgUnitInfo getMainOrgInfo() {
	   OrgUnitInfo org = new OrgUnitInfo();
	   org.setName("abc");
	  // org.setId( BOSUuid.read("123434"));
	   return org;
   }
}