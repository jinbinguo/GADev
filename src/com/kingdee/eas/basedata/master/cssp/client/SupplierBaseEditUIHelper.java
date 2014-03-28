/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) deadcode ansi radix(10) lradix(10) 
// Source File Name:   SupplierBaseEditUIHelper.java

package com.kingdee.eas.basedata.master.cssp.client;

import java.util.Iterator;

import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.basedata.master.cssp.SupplierInfo;
import com.kingdee.eas.basedata.org.CtrlUnitInfo;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.framework.client.EditUI;
import com.kingdee.eas.util.client.EASResource;

// Referenced classes of package com.kingdee.eas.basedata.master.cssp.client:
//            AbstractSupplierAttributeUIHelper, SupplierBaseUI, ICSSPAttributeUIHelper

public class SupplierBaseEditUIHelper extends AbstractSupplierAttributeUIHelper
{

    public SupplierBaseEditUIHelper()
    {
    }

    public boolean checkBeforeWindowClosing()
    {
        return ((SupplierBaseUI)currentUI).checkBeforeWindowClosing();
    }

    public ICoreBase getBizInterface()
        throws Exception
    {
        return ((SupplierBaseUI)currentUI).getBizInterface();
    }

    public void necessaryFieldsVerify()
    {
    }

    public EditUI createEditUI()
        throws Exception
    { 
    	//反编译，改造支持UI扩展
        currentUI = new SupplierBaseUIPIEx();
        ((SupplierBaseUI)currentUI).setUiHelper(this);
        return currentUI;
    }

    public IObjectValue getObjectValue(CtrlUnitInfo curCtrlUnitInfo, BOSUuid id)
        throws Exception
    {
        return null;
    }

    public String getClassAlise()
    {
        return ((SupplierBaseUI)currentUI).getClassAlise();
    }

    public boolean saveDefaultOtherInfo(SupplierInfo item, StringBuffer errStr)
    {
        boolean isSaveSuccess = true;
        errStr.append(EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_Save_OK"));
        errStr.append("\r\n\r\n,");
        Iterator i$ = myAttachedUiHelplers.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            ICSSPAttributeUIHelper uiHelper = (ICSSPAttributeUIHelper)i$.next();
            boolean b = uiHelper.saveDefaultOtherInfo(item, errStr);
            if(isSaveSuccess)
                isSaveSuccess = b;
        } while(true);
        return isSaveSuccess;
    }

    public boolean saveOtherInfoByCopy(SupplierInfo oldSupplierInfo, SupplierInfo item, StringBuffer errStr)
    {
        boolean isSaveSuccess = true;
        errStr.append(EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_Save_OK"));
        errStr.append("\r\n\r\n,");
        for(Iterator i$ = myAttachedUiHelplers.iterator(); i$.hasNext();)
        {
            ICSSPAttributeUIHelper uiHelper = (ICSSPAttributeUIHelper)i$.next();
            isSaveSuccess = isSaveSuccess && uiHelper.saveOtherInfoByCopy(oldSupplierInfo, item, errStr);
        }

        return isSaveSuccess;
    }
}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\GA\workspace\Dev\lib\client\eas\bd_custsupp-client.jar
	Total time: 24 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/