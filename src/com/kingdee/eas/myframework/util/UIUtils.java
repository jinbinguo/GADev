package com.kingdee.eas.myframework.util;

import java.awt.Container;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangArea;
import com.kingdee.bos.ctrl.extendcontrols.KDBizMultiLangBox;
import com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox;
import com.kingdee.bos.ctrl.kdf.table.ICell;
import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.swing.KDDatePicker;
import com.kingdee.bos.ctrl.swing.KDFormattedTextField;
import com.kingdee.bos.ctrl.swing.KDLabelContainer;
import com.kingdee.bos.ctrl.swing.KDNumberTextField;
import com.kingdee.bos.ctrl.swing.KDPasswordField;
import com.kingdee.bos.ctrl.swing.KDTextArea;
import com.kingdee.bos.ctrl.swing.KDTextField;
import com.kingdee.bos.ctrl.swing.KDTimePicker;
import com.kingdee.bos.ui.face.IUIWindow;
import com.kingdee.bos.ui.face.UIFactory;
import com.kingdee.bos.workflow.ActivitySuspendedException;
import com.kingdee.bos.workflow.exception.AlreadyInProcessQueueException;
import com.kingdee.bos.workflow.exception.MatchAssignmentException;
import com.kingdee.bos.workflow.exception.MissMatchParticipantException;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.OprtState;
import com.kingdee.eas.common.client.UIContext;
import com.kingdee.eas.common.client.UIFactoryName;
import com.kingdee.eas.framework.client.CoreUI;
import com.kingdee.eas.myframework.common.client.MultiColumnSortUI;
import com.kingdee.eas.myframework.comparators.table.KDTableComparatorUtils;
import com.kingdee.eas.myframework.comparators.table.SortColumnCollection;
import com.kingdee.eas.scm.common.client.helper.FormattedEditorFactory;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.eas.util.client.ExceptionHandler;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.util.NumericExceptionSubItem;

public class UIUtils implements Serializable {

	
	public static void checkRequireCompValue(KDTable tbl, String[] cols) throws Exception {
		for (int i = 0; i < tbl.getRowCount(); i++) {
			IRow row = tbl.getRow(i);
			for (int j = 0; j < cols.length; j++) {
				ICell cell =  row.getCell(cols[j]);
				int colIndex = cell.getColumnIndex();
				Object value = cell.getValue();
				if (value == null || "".equals(value.toString())) {
					String columnName = tbl.getHead().getRow(0).getCell(colIndex).getValue().toString();
					tbl.getEditManager().editCellAt(i, colIndex);
					throw new EASBizException(new NumericExceptionSubItem("",String.format("第%d行,[%s]列不能为空！",i+1,columnName)));
					
				}
			}
			
			
		}
	}
	
	public static void checkRequireCompValue(JComponent comp) throws Exception {		
		if (comp instanceof KDTextField) {
			KDLabelContainer container = ((KDTextField)comp).getLabelContainer();
			if (container != null) {
				String labelText = container.getBoundLabelText();
				if ("".equals(((KDTextField)comp).getText())) {
					((KDTextField)comp).requestFocus();
					throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {labelText});
				}				
			} else throw new EASBizException(new NumericExceptionSubItem("",String.format("必录项控件%s必须放置KDLabelContainer容器内",comp.getName())));
			
		} else if (comp instanceof KDBizPromptBox) {			
			Container container = ((KDBizPromptBox)comp).getParent();
			if (container instanceof KDLabelContainer &&  container != null) {
				String labelText = ((KDLabelContainer)container).getBoundLabelText();
				if (((KDBizPromptBox)comp).getValue() == null) {
					((KDBizPromptBox)comp).requestFocus();
					throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {labelText});
				}				
			} else throw new EASBizException(new NumericExceptionSubItem("",String.format("必录项控件%s必须放置KDLabelContainer容器内",comp.getName())));
		} else if (comp instanceof KDFormattedTextField) {
			KDLabelContainer container = ((KDFormattedTextField)comp).getLabelContainer();
			if (container != null) {
				String labelText = container.getBoundLabelText();
				if ("".equals(((KDFormattedTextField)comp).getText())) {
					((KDFormattedTextField)comp).requestFocus();
					throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {labelText});
				}				
			} else throw new EASBizException(new NumericExceptionSubItem("",String.format("必录项控件%s必须放置KDLabelContainer容器内",comp.getName())));
		} else if (comp instanceof KDPasswordField) {
			KDLabelContainer container = ((KDPasswordField)comp).getLabelContainer();
			if (container != null) {
				String labelText = container.getBoundLabelText();
				if (((KDPasswordField)comp).getPassword().length == 0) {
					((KDPasswordField)comp).requestFocus();
					throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {labelText});
				}				
			} else throw new EASBizException(new NumericExceptionSubItem("",String.format("必录项控件%s必须放置KDLabelContainer容器内",comp.getName())));

		} else if (comp instanceof KDTextArea) {
			KDLabelContainer container = ((KDTextArea)comp).getLabelContainer();
			if (container != null) {
				String labelText = container.getBoundLabelText();
				if ("".equals(((KDTextArea)comp).getText())) {
					((KDTextArea)comp).requestFocus();
					throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {labelText});
				}				
			} else throw new EASBizException(new NumericExceptionSubItem("",String.format("必录项控件%s必须放置KDLabelContainer容器内",comp.getName())));

		} else if (comp instanceof KDDatePicker) {
			Container container = ((KDDatePicker)comp).getParent();
			if (container instanceof KDLabelContainer &&  container != null) {
				String labelText = ((KDLabelContainer)container).getBoundLabelText();
				if (((KDDatePicker)comp).getValue() == null) {
					((KDDatePicker)comp).requestFocus();
					throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {labelText});
				}				
			} else throw new EASBizException(new NumericExceptionSubItem("",String.format("必录项控件%s必须放置KDLabelContainer容器内",comp.getName())));

		} else if (comp instanceof KDTimePicker) {
			Container container = ((KDTimePicker)comp).getParent();
			if (container instanceof KDLabelContainer &&  container != null) {
				String labelText = ((KDLabelContainer)container).getBoundLabelText();
				if (((KDTimePicker)comp).getValue() == null) {
					((KDTimePicker)comp).requestFocus();
					throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {labelText});
				}				
			} else throw new EASBizException(new NumericExceptionSubItem("",String.format("必录项控件%s必须放置KDLabelContainer容器内",comp.getName())));
		} else if (comp instanceof KDBizMultiLangArea) {
			KDLabelContainer container = ((KDBizMultiLangArea)comp).getLabelContainer();
			if (container != null) {
				String labelText = container.getBoundLabelText();
				if ("".equals(((KDBizMultiLangArea)comp).getDefaultLangItemData().toString())) {
					((KDBizMultiLangArea)comp).requestFocus();
					throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {labelText});
				}				
			} else throw new EASBizException(new NumericExceptionSubItem("",String.format("必录项控件%s必须放置KDLabelContainer容器内",comp.getName())));

		} else if (comp instanceof KDBizMultiLangBox) {
			KDLabelContainer container = ((KDBizMultiLangBox)comp).getLabelContainer();
			if (container != null) {
				String labelText = container.getBoundLabelText();
				if ("".equals(((KDBizMultiLangBox)comp).getDefaultLangItemData().toString())) {
					((KDBizMultiLangBox)comp).requestFocus();
					throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {labelText});
				}				
			} else throw new EASBizException(new NumericExceptionSubItem("",String.format("必录项控件%s必须放置KDLabelContainer容器内",comp.getName())));

		} else if (comp instanceof KDNumberTextField) {
			KDLabelContainer container = ((KDNumberTextField)comp).getLabelContainer();
			if (container != null) {
				String labelText = container.getBoundLabelText();
				if (((KDNumberTextField)comp).getNumberValue() == null) {
					((KDNumberTextField)comp).requestFocus();
					throw new EASBizException(EASBizException.CHECKBLANK,new Object[] {labelText});
				}				
			} else throw new EASBizException(new NumericExceptionSubItem("",String.format("必录项控件%s必须放置KDLabelContainer容器内",comp.getName())));

		}
	}

	public static void handUIException(Throwable exc) {
        Throwable e = exc;
        if(e instanceof ActivitySuspendedException) {
            e.printStackTrace();
            MsgBox.showWarning(null, EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_ctivitySuspended"));
            return;
        }
        
        if(e instanceof AlreadyInProcessQueueException) {
            e.printStackTrace();
            MsgBox.showWarning(null, EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_AlreadyInProcessQueue"));
            return;
        }
        if(e instanceof MissMatchParticipantException) {
            MsgBox.showWarning(null, EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_MissMatchParticipant"));
            return;
        }
        if(e instanceof MatchAssignmentException){
            MsgBox.showWarning(null, EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_WFMatch"));
            return;
        }
        if((e instanceof BOSException) && e.getCause() != null && (e.getCause() instanceof EASBizException)) {
            ExceptionHandler.handle("", e.getCause());
            return;
        } else {
            ExceptionHandler.handle("", e);
            return;
        }
    }

    public static void handUIExceptionAndAbort(Throwable exc)  {
        handUIException(exc);
        SysUtil.abort();
    }
    
    public static void registerMultiColumnOrder(CoreUI ui, KDTable tbl, SortColumnCollection defaultSortColumns) throws Exception {
    	if (tbl == null)
    		throw new EASBizException(new NumericExceptionSubItem("",String.format("PublicUtils.registerMultiColumnOrder参数[%s]不能为空！","tbl")));
    	SortColumnCollection currentSortColumns = (SortColumnCollection) ui.getUIContext().get("currentSortColumns");
    	if (currentSortColumns == null) currentSortColumns = new SortColumnCollection();
    	if (defaultSortColumns == null) defaultSortColumns = new SortColumnCollection();
    	
    	String sortUIName = MultiColumnSortUI.class.getName();
    	UIContext sortUIContext = new UIContext();
    	sortUIContext.put("sortTable", tbl);
    	sortUIContext.put("defaultSortColumns", defaultSortColumns);
    	sortUIContext.put("currentSortColumns", currentSortColumns);
    	sortUIContext.put("sourceUI", ui);
    	
    	IUIWindow uiWindow = UIFactory.createUIFactory(UIFactoryName.MODEL).create(sortUIName, sortUIContext, null, OprtState.VIEW);
		uiWindow.show();
		MultiColumnSortUI multiColumnSortUI = (MultiColumnSortUI) uiWindow.getUIObject();
		Map multiColSortUIContext = multiColumnSortUI.getUIContext();
		boolean isSort = (Boolean)multiColSortUIContext.get("isSort");
		SortColumnCollection sortColumns = (SortColumnCollection) multiColSortUIContext.get("sortColumns");
		ui.getUIContext().put("currentSortColumns", sortColumns);
		if (!isSort) return;
    	KDTableComparatorUtils tblComparatorUtils = new KDTableComparatorUtils(tbl, sortColumns);
    	List<IRow> lstRow = tblComparatorUtils.sort();
    	ui.loadFields();
    	tbl.removeRows();
    	for (int i = 0; i < lstRow.size(); i++) {
    		tbl.addRow(i, lstRow.get(i));
    	}
    } 
    /**
     * 精度默认为2
     * @param tblMain
     * @param columnName
     * @param isNegatived 是否支持录入负数
     * @throws EASBizException
     * @throws BOSException
     */
    public static void formatDecimal(KDTable tblMain, String columnName,boolean isNegatived) {
    	KDTDefaultCellEditor editor = FormattedEditorFactory.getBigDecimalCellEditor(2, isNegatived);
		tblMain.getColumn(columnName).setEditor(editor);
		tblMain.getColumn(columnName).getStyleAttributes().setNumberFormat("%r{#,##0.00}f");
		tblMain.getColumn(columnName).getStyleAttributes().setHorizontalAlign(com.kingdee.bos.ctrl.kdf.util.style.Styles.HorizontalAlignment.RIGHT);
	}
}
