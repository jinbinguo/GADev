package com.kingdee.bos.ctrl.reportone.r1.print.browser;

import java.awt.Component;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.kingdee.bos.ctrl.common.util.StringUtil;
import com.kingdee.bos.ctrl.print.ConfigManager;
import com.kingdee.bos.ctrl.print.KDPrinter;
import com.kingdee.bos.ctrl.print.util.KDPrintUtil;
import com.kingdee.bos.ctrl.reportone.r1.common.designercore.model.ReportModel;
import com.kingdee.bos.ctrl.reportone.r1.print.common.R1PrintInfo;
import com.kingdee.bos.ctrl.reportone.r1.print.engine.RuntimeModel;
import com.kingdee.eas.base.core.util.UIUtil;
import com.kingdee.eas.myframework.util.InvokeUtils;
import com.kingdee.eas.myframework.util.UIUtils;
import com.kingdee.eas.util.SysUtil;

public class R1PrintBrowserEx extends R1PrintBrowser {
	
	private static int cnt = 1;
	private static ConfigManager config = null;
	public static int runPrintCtrl(KDPrinter printer, boolean isPreview,
			boolean isShowPrinterDialog, Component owner, String title) {
		printer.setPreviewWindowType(0);
		printer.setParentWindow(owner);
		printer.getPrintConfig().setPrintJobName(title);
		if (isPreview) {
			if (StringUtil.isEmptyString(title))
				printer.printPreview();
			else
				printer.printPreview(title);
		} else if (isShowPrinterDialog) {
			//cnt= 1;
			if (!KDPrintUtil.isPrintDirect(printer.getPrintConfig().getPrintJobName())) {
				if (cnt == 1) {
					cnt++;
					
					int aa =  printer.print2();
					config = printer.getPrintConfig();
					//config.setPrintJobName("print");
					try {
						HashMap h = KDPrintUtil.getCachedConfig();
					//	h.put("print", "print");
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return aa;
				} else {
					printer.setPrintConfig(config);
					config.getModel().setPrintDirect(false);
					printer.print();
					//deleteConfigInXml(config.getPrintJobName(),config.getPrinter());
					return -1;
				}
			}
			printer.print();
		} else {
			printer.printDirect();
		} 
		return -1;
	}
	
	@Override
	public KDPrinter getPrinterCtrl() {
		return super.getPrinterCtrl();
	}
    public int print(Object templateModel, boolean isPreview,
			boolean isShowPrinterDialog, Component owner, String title) {
		if (templateModel instanceof RuntimeModel) {
			try {
				Class[] cls = new Class[] {
					RuntimeModel.class,int.class,boolean.class,boolean.class,boolean.class,Component.class,String.class
				};
				Object[] objs = new Object[] {
					(RuntimeModel) templateModel, getMaxPagesLimit(), isPreview, isShowPrinterDialog, false, owner, title	
				};
				Method method = InvokeUtils.getMethod(this, "print", cls);
				return (Integer)InvokeUtils.invokeMethod(this,method,objs);
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
			//return 0;
		}else
			throw new IllegalArgumentException("Argument templateModel must be RuntimeModel.");
	}
    
    private int print(RuntimeModel runtimeModel, int forceStopPages, boolean isPreview, boolean isShowPrinterDialog, boolean isSync, Component owner, String title)
    {
	    try {
		   getPrinterCtrl().clear();
		   initPrintCtrl(runtimeModel);
		    // R1PrintPageProvider pageProvider = createPageProvider(forceStopPages);
		   R1PrintPageProvider pageProvider = (R1PrintPageProvider) InvokeUtils.invokeMethod(this, "createPageProvider", new Class[]{int.class},new Object[]{forceStopPages});
		       com.kingdee.bos.ctrl.kdf.form2.ui.AbstractNotePrint.WaitingDialog dlg = null;
		      if(!isSync && isPreview)
		        {
		         dlg = com.kingdee.bos.ctrl.kdf.form2.ui.AbstractNotePrint.WaitingDialog.create(owner);
		          pageProvider.setListener(new com.kingdee.bos.ctrl.kdf.form2.ui.AbstractNotePrint.PageProviderListener(dlg));
		        }
		    // generate(runtimeModel, isSync, pageProvider);
		      InvokeUtils.invokeMethod(this, "generate", new Class[] {RuntimeModel.class,boolean.class,R1PrintPageProvider.class},
			    		 new Object[]{runtimeModel, isSync, pageProvider}); 
		      if(!isSync && isPreview)
		        {
		          dlg.show();
		          if(dlg.isUserCancel())
		            {
		              pageProvider.forceStop();
		                return 2;
		            }
		        }
		     ReportModel mo = runtimeModel.getReportModel();
		    // R1PrintInfo pi = getPrintInfo(mo);
		     R1PrintInfo pi = (R1PrintInfo) InvokeUtils.invokeMethod(this, "getPrintInfo", new Class[] {ReportModel.class}, new Object[]{mo});
		     //createPrintJob(mo, pi, pageProvider);
		     InvokeUtils.invokeMethod(this, "createPrintJob", new Class[] {ReportModel.class,R1PrintInfo.class,R1PrintPageProvider.class},
		    		 new Object[]{mo, pi, pageProvider}); 
		     return runPrintCtrl(getPrinterCtrl(), isPreview, isShowPrinterDialog, owner, title);
		
	    } catch (Exception e) {
	    	return -2;
	    }
	  //  return 0;
    }
    
}
