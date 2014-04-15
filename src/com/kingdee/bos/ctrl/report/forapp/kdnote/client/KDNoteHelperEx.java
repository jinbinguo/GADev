package com.kingdee.bos.ctrl.report.forapp.kdnote.client;

import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.kingdee.bos.ctrl.common.ui.WindowUtil;
import com.kingdee.bos.ctrl.common.util.PathUtil;
import com.kingdee.bos.ctrl.common.util.StringUtil;
import com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate;
import com.kingdee.bos.ctrl.kdf.form2.ui.FormPrintHelper;
import com.kingdee.bos.ctrl.kdf.form2.ui.INotePrintHelper;
import com.kingdee.bos.ctrl.kdf.form2.ui.NotePrinter;
import com.kingdee.bos.ctrl.print.IPrintActionListener;
import com.kingdee.bos.ctrl.print.config.PrintJobConfig;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.NoteManageView;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.NoteTemplateManageView;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.NoteUserConfig;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.NoteVarListener;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.ui.NoteFileDialog;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.ui.NoteFileDialogEx;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.util.MultiLanguageUtil;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.util.NoteUseUtil;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.util.NoteUtil;
import com.kingdee.bos.ctrl.reportone.kdrs.biz.BizRpcReducer;
import com.kingdee.bos.ctrl.reportone.kdrs.biz.IBizContext;
import com.kingdee.bos.ctrl.reportone.kdrs.biz.IContextSupplier;
import com.kingdee.bos.ctrl.reportone.kdrs.biz.storage.IBizStorage;
import com.kingdee.bos.ctrl.reportone.kdrs.exception.KDRSException;
import com.kingdee.bos.ctrl.reportone.kdrs.exception.NotFoundException;
import com.kingdee.bos.ctrl.reportone.r1.print.browser.R1PrintBrowser;
import com.kingdee.bos.ctrl.reportone.r1.print.browser.R1PrintBrowserEx;
import com.kingdee.bos.ctrl.reportone.r1.print.data.AbstractPrintDataProvider;
import com.kingdee.eas.auto4s.rsm.rs.client.RepairWOEditUIPIEx;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.util.InvokeUtils;
import com.kingdee.util.StringUtils;

public class KDNoteHelperEx extends KDNoteHelper {
	
    private NotePrinter _notePrinter;
    private boolean _isOrgFilterSelected;
    private boolean _isOrgFilterEnabled;
  //  private String chooseTemplatePath;
    private static NoteManageView noteManageView;
    private static NoteManageView noteLimitedManageView;
    private static NoteTemplateManageView noteTemplateManageView;
    private BizRpcReducer _bizRpcReducer;
	
	public void print(String templateType, Object dataProvider, java.awt.Component owner, boolean isShowPrinterDialog, RepairWOEditUIPIEx ui)  {
		log.debug((new StringBuilder()).append("KDNote print templateType:").append(templateType).append(", dataProvider:").append(dataProvider).append(", owner:").append(owner).toString());
		try {
			 
	      Template[] templates = readDefaultTemplateEx(templateType, owner);
	      if (templates == null) return;
	      for (int i = 0; i < templates.length; i++) {
	    	  Template template = templates[i];
	    	  if (i > 0) { //重新配置打印数据源
	    		  ArrayList idList = new ArrayList();
	    		  if(ui.getDataObject() != null && !StringUtils.isEmpty(ui.getDataObject().getString("id")))
	    			  idList.add(ui.getDataObject().getString("id"));
	    		  DefaultNoteDataProvider multiDataSourceProviderProxy = new DefaultNoteDataProvider(idList);
	    		  ui.initDefaultNoteDataProvider(multiDataSourceProviderProxy);
	    		  dataProvider = multiDataSourceProviderProxy;
	    	  } 
	    	  if(template != null)  {
		          Object templateModel = loadTemplateModel(template, dataProvider, createRefResLoader());
		          INotePrintHelper helper = createPrintHelper(template.getCategory(), template.getTemplatePath(), getNotePrinter(), _listener, dataProvider);
		          if(templateModel != null && helper != null) {
		        	  String alias = (String) InvokeUtils.getFieldValue(template, "alias");
		              helper.print(templateModel, false, i == 0, owner, (new StringBuilder()).append(MultiLanguageUtil.getMLS("client.KDNoteHelper.title", "EAS套打 - ")).append(alias).toString());	      
		          }
		      }
	    	  
	      }
	      
	      
	      
	   } catch(AssertionError err) {
	      log.error("", err);
	      WindowUtil.msgboxError(MultiLanguageUtil.getMLS("client.KDNoteHelper.error", "读取套打模板失败，可能是文件损坏。"), getPrintTitle(true), owner);
	   }  catch(Exception ex) {
	      log.error("套打打印失败", ex);
	      WindowUtil.msgboxError(MultiLanguageUtil.getMLS("client.KDNoteHelper.printErrorPrompt", "套打不能正常进行。请从客户端日志了解异常信息。"), getPrintTitle(false), owner);
	   }
	}
    private final String getPrintTitle(boolean isPreview) {
    	return isPreview ? MultiLanguageUtil.getMLS("client.KDNoteHelper.previewTitle", "套打预览") : MultiLanguageUtil.getMLS("client.KDNoteHelper.printTitle", "套打打印");
    }
    
    private Template[] readDefaultTemplateEx(String templateType,
			java.awt.Component owner) throws KDRSException {
		boolean isOrgFilter = false;
		String templateName = null;
		String absDir = PathUtil.makeChildPath("/EAS_Note.kdrs", templateType);
		NoteUserConfig config = null;
		String user = NoteUtil.getUser();
		String company = NoteUtil.getCurrentOrgUnitId();
		String cau = NoteUtil.bindCompanyAndUser(company, user);
	    /*if (getLocalStorage() != null) {
			config = NoteUserConfig.load(getLocalStorage(), absDir);
			templateName = NoteUtil.getDefaultTemplate(config, company, user);
			isOrgFilter = NoteUtil.isOrgFilter(config, company, user);
		} */
		String[] templatePaths = null;
		String[] relaTemplatePaths = null;
		/*
		if (StringUtil.isEmptyString(templateName)) {
			log.debug("没有缺省模板信息。");
		} else {
			log.debug((new StringBuilder()).append("公司：").append(company).append("，用户：").append(user).append("，缺省模板: ").append(templateName).toString());
			templatePath = PathUtil.makeChildPath(absDir, templateName);
			relaTemplatePath = PathUtil.makeChildPath(templateType,templateName);
			try {
				if (!NoteUseUtil.isCanPrint(owner, getBizContent(),relaTemplatePath)) {
					config.removeDefaultTemplateName(cau);
					if (config != null && config.isDirty())
						NoteUserConfig.save(getLocalStorage(), absDir, config);
					templatePath = null;
				}
			} catch (KDRSException ex) {
				log.debug((new StringBuilder()).append(relaTemplatePath).append("获取属性：").append("NoteUseUtil.").append("user_enabled_users").append("失败").toString());
			}
		}*/
		
		InputStream kdfStream = null;
		String category = null;
		String alias = null;
		
		
		
		
		
		boolean choose = true;
		Template[] templates = null;
		do {
			if (!choose)
				break;
			if (templatePaths == null) {
				NoteFileDialogEx nfDlg = NoteFileDialogEx.create(owner,getBizRpcReducer());
				nfDlg.setShowSaveAsDefault(true);
				nfDlg.setNoteType(templateType);
				nfDlg.setUseTemplateOrgFilter(_isOrgFilterSelected ? true : isOrgFilter);
				nfDlg.setUseTemplateOrgFilterEnabled(_isOrgFilterEnabled);
				if (!nfDlg.showDialog())
					return null;
				templatePaths = nfDlg.getNotePathTextEx();
				relaTemplatePaths = nfDlg.getRelativeNotePathTextEx();
				if (templatePaths == null) {
					MsgBoxEx.showInfo("请先选择套打模版!");
					nfDlg.show();
					return null;
					
				} 
				
				if (config != null) {
				//	if (nfDlg.isSaveAsDefault()) {
				//		int subPathStart = (templateType != null ? templateType : "").length();
				//		String subPath = relaTemplatePath.substring(subPathStart);
				//		config.putDefaultTemplateName(cau, subPath);
				//	}
					config.putOrgFilter(cau, nfDlg.isUseTemplateOrgFilter());
				}
			}
			choose = false;
			templates = new Template[templatePaths.length];
			
			for (int i = 0; i < templates.length; i++) {
				String templatePath = templatePaths[i];
				String relaTemplatePath = relaTemplatePaths[i];
				try {
					kdfStream = makeInputStream(getBizRpcReducer().readTemplate(getLocalStorage(), templatePath));
					HashMap attrs = (HashMap) getBizRpcReducer().getBatchResult("IBizContent.getAttributes",new Object[] { templatePath,new String[] { "category", "alias", "name" } });
					category = (String) attrs.get("category");
					alias = (String) attrs.get("alias");
					if (StringUtil.isEmptyString(alias))
						alias = (String) attrs.get("name");
				} catch (NotFoundException ex) {
					promptGetTemplateError((new StringBuilder()).append(MultiLanguageUtil.getMLS("client.KDNoteHelper.templateNotFoundPrompt","模板不存在，请重新选择。")).append(StringUtil.RETURN).append(templatePath).toString(), owner);
				} catch (KDRSException ex) {
					promptGetTemplateError((new StringBuilder()).append(MultiLanguageUtil.getMLS("client.KDNoteHelper.stoIOErr", "模板不能正确读取。")).append(templatePath).toString(), owner);
				}
				if (kdfStream == null) {
					choose = true;
					templatePath = null;
					if (config != null)
						config.removeDefaultTemplateName(cau);
				}
				if (config != null && config.isDirty())
					NoteUserConfig.save(getLocalStorage(), absDir, config);
				Template template = new Template();
				template.setInputStream(kdfStream);
				template.setCategory(category);
				template.setTemplatePath(relaTemplatePath);
				template.setAlias(alias);
				templates[i] = template;
				
			}
			
			
		} while (true);
		
		
		//chooseTemplatePath = relaTemplatePath;
		return templates;
	}
    
    private Template readDefaultTemplate(String templateType,
			java.awt.Component owner) throws KDRSException {
		boolean isOrgFilter = false;
		String templateName = null;
		String absDir = PathUtil.makeChildPath("/EAS_Note.kdrs", templateType);
		NoteUserConfig config = null;
		String user = NoteUtil.getUser();
		String company = NoteUtil.getCurrentOrgUnitId();
		String cau = NoteUtil.bindCompanyAndUser(company, user);
		if (getLocalStorage() != null) {
			config = NoteUserConfig.load(getLocalStorage(), absDir);
			templateName = NoteUtil.getDefaultTemplate(config, company, user);
			isOrgFilter = NoteUtil.isOrgFilter(config, company, user);
		}
		String templatePath = null;
		String relaTemplatePath = null;
		if (StringUtil.isEmptyString(templateName)) {
			log.debug("没有缺省模板信息。");
		} else {
			log.debug((new StringBuilder()).append("公司：").append(company).append("，用户：").append(user).append("，缺省模板: ").append(templateName).toString());
			templatePath = PathUtil.makeChildPath(absDir, templateName);
			relaTemplatePath = PathUtil.makeChildPath(templateType,templateName);
			try {
				if (!NoteUseUtil.isCanPrint(owner, getBizContent(),relaTemplatePath)) {
					config.removeDefaultTemplateName(cau);
					if (config != null && config.isDirty())
						NoteUserConfig.save(getLocalStorage(), absDir, config);
					templatePath = null;
				}
			} catch (KDRSException ex) {
				log.debug((new StringBuilder()).append(relaTemplatePath).append("获取属性：").append("NoteUseUtil.").append("user_enabled_users").append("失败").toString());
			}
		}
		InputStream kdfStream = null;
		String category = null;
		String alias = null;
		boolean choose = true;
		do {
			if (!choose)
				break;
			if (templatePath == null) {
				NoteFileDialogEx nfDlg = NoteFileDialogEx.create(owner,getBizRpcReducer());
				nfDlg.setShowSaveAsDefault(true);
				nfDlg.setNoteType(templateType);
				nfDlg.setUseTemplateOrgFilter(_isOrgFilterSelected ? true : isOrgFilter);
				nfDlg.setUseTemplateOrgFilterEnabled(_isOrgFilterEnabled);
				if (!nfDlg.showDialog())
					return null;
				templatePath = nfDlg.getNotePathText();
				relaTemplatePath = nfDlg.getRelativeNotePathText();
				
				if (config != null) {
					if (nfDlg.isSaveAsDefault()) {
						int subPathStart = (templateType != null ? templateType : "").length();
						String subPath = relaTemplatePath.substring(subPathStart);
						config.putDefaultTemplateName(cau, subPath);
					}
					config.putOrgFilter(cau, nfDlg.isUseTemplateOrgFilter());
				}
			}
			choose = false;
			try {
				kdfStream = makeInputStream(getBizRpcReducer().readTemplate(getLocalStorage(), templatePath));
				HashMap attrs = (HashMap) getBizRpcReducer().getBatchResult("IBizContent.getAttributes",new Object[] { templatePath,new String[] { "category", "alias", "name" } });
				category = (String) attrs.get("category");
				alias = (String) attrs.get("alias");
				if (StringUtil.isEmptyString(alias))
					alias = (String) attrs.get("name");
			} catch (NotFoundException ex) {
				promptGetTemplateError((new StringBuilder()).append(MultiLanguageUtil.getMLS("client.KDNoteHelper.templateNotFoundPrompt","模板不存在，请重新选择。")).append(StringUtil.RETURN).append(templatePath).toString(), owner);
			} catch (KDRSException ex) {
				promptGetTemplateError((new StringBuilder()).append(MultiLanguageUtil.getMLS("client.KDNoteHelper.stoIOErr", "模板不能正确读取。")).append(templatePath).toString(), owner);
			}
			if (kdfStream == null) {
				choose = true;
				templatePath = null;
				if (config != null)
					config.removeDefaultTemplateName(cau);
			}
		} while (true);
		if (config != null && config.isDirty())
			NoteUserConfig.save(getLocalStorage(), absDir, config);
		Template template = new Template();
		template.setInputStream(kdfStream);
		template.setCategory(category);
		template.setTemplatePath(relaTemplatePath);
		template.setAlias(alias);
	//	chooseTemplatePath = relaTemplatePath;
		return template;
	}
    
    static IBizStorage getLocalStorage() {
    	return NoteUtil.getLocalBizStorage();
    }
    
    private BizRpcReducer getBizRpcReducer() {
     if(_bizRpcReducer == null)
        _bizRpcReducer = new BizRpcReducer(new IContextSupplier() {
            public IBizContext getContext()  {
            	return KDNoteHelper.getContext();
            }
        });
     return _bizRpcReducer;
    }
    
    private void promptGetTemplateError(String content, java.awt.Component owner) {
    	WindowUtil.msgboxError(content, MultiLanguageUtil.getMLS("client.KDNoteHelper.getNoteTemplate", "获取套打模板"), owner);
    }
    
    protected INotePrintHelper createPrintHelper(String category, String templatePath, NotePrinter notePrinter, 
    		IPrintActionListener printActionListener, Object dataProvider) {
    	INotePrintHelper helper = null;
    	if("kdrs-form".equals(category)) {
    		helper = new FormPrintHelper();
    		((FormPrintHelper)helper).setFormVarListener(new NoteVarListener.FormVarListener(getEasVarListener()));
        } else if("r1-print".equals(category))  {
        	helper = new R1PrintBrowserEx();
        	((R1PrintBrowserEx)helper).setVariantListener(new NoteVarListener.R1VarListener(getEasVarListener()));
        }
    	if(helper != null) {
    		if(printActionListener != null)  {
    			notePrinter.getPrinter().removePrintActionListener(printActionListener);
    			notePrinter.getPrinter().addPrintActionListener(printActionListener);
        }
	    helper.setPrinterCtrl(notePrinter.getPrinter());
	    helper.setStateListener(notePrinter.getNoteStateListener());
	    final PrinterConfigChangeHandler handler = new PrinterConfigChangeHandler();
	    handler.setLocalStorage(getLocalStorage());
	    handler.setCurrentTemplatePath(templatePath);
	    handler.setPrinter(notePrinter.getPrinter());
	    notePrinter.setCongifChangeListener(handler);
	    helper.setCustomizePrintJobConfig(new com.kingdee.bos.ctrl.kdf.form2.ui.INotePrintHelper.ICustomizePrintJobConfig() {
                public void customize(PrintJobConfig printJobConfig) {
                	handler.initValue(printJobConfig);
                }
                public void setConfigChangeHandlerEnabled(boolean enabled) {
                	handler.setHandlerEnabled(enabled);
                }
	    });
	    bindDataProviderInfoWithPreview(notePrinter, dataProvider);
        } else {
        	log.error("不能构造INotePrintHelper的实例。");
        }
    	return helper;
    }
    
    private static void bindDataProviderInfoWithPreview(NotePrinter notePrinter, Object dataProvider)  {
    	final StringBuffer sbPrompt = new StringBuffer((new StringBuilder()).append("Data-provider is:").append(StringUtil.RETURN).toString());
    	if(dataProvider instanceof DefaultNoteDataProvider) {
    		sbPrompt.append("DefaultNoteDataProvider");
        } else {
        	sbPrompt.append(dataProvider.getClass().getName());
        	if(dataProvider instanceof AbstractPrintDataProvider)  {
        		sbPrompt.append(StringUtil.RETURN);
        		sbPrompt.append("It extends AbstractPrintDataProvider.");
            }
        	if(dataProvider instanceof BOSQueryDelegate) {
		         sbPrompt.append(StringUtil.RETURN);
		         sbPrompt.append("It implements BOSQueryDelegate.");
            }
        }
    }
}
