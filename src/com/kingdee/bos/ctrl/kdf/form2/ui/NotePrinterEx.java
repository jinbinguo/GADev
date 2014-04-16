/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) deadcode ansi radix(10) lradix(10) 
// Source File Name:   NotePrinter.java

package com.kingdee.bos.ctrl.kdf.form2.ui;

import com.kingdee.bos.ctrl.common.LanguageManager;
import com.kingdee.bos.ctrl.common.util.CtrlClassUtil;
import com.kingdee.bos.ctrl.print.*;
import com.kingdee.bos.ctrl.print.config.ui.PageSetupUIForForm;
import com.kingdee.bos.ctrl.print.io.IOManager;
import com.kingdee.bos.ctrl.print.preview.*;
import com.kingdee.bos.ctrl.print.preview.event.ButtonItemEvent;
import com.kingdee.bos.ctrl.print.preview.event.ButtonItemListener;
import com.kingdee.bos.ctrl.print.resource.Resources;
import com.kingdee.bos.ctrl.print.ui.IPaintFilter;
import com.kingdee.bos.ctrl.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

// Referenced classes of package com.kingdee.bos.ctrl.kdf.form2.ui:
//            AbstractNotePrint, INotePrintHelper

public class NotePrinterEx extends NotePrinter
{
    private class NoteStateListener implements INotePrintHelper.StateListener  {

        public void notifyState(int key, Object value) {
            if(key == 1)
                setButtonBackgroundVisible(((Boolean)value).booleanValue());
            else
            if(key == 2)
                setButtonBackgroundSelected(((Boolean)value).booleanValue());
        }

        private NoteStateListener()  {
            super();
        }

    }

    private static class HideBackgroundListener  implements ButtonItemListener  {

        public void handleButtonItem(ButtonItemEvent event)  {
            if(event.getButtonItem() == targetItem)  {
                IPaintFilter filter = printer.getPaintFilter();
                if(filter instanceof HiddenLayerFilter)  {
                    HiddenLayerFilter hlf = (HiddenLayerFilter)filter;
                    hlf.setPreviewApply(!hlf.isPreviewApply());
                }
            }
            printer.getPrintPreview().getPreviewBody().repaint();
        }

        private ButtonItem targetItem;
        private KDPrinter printer;

        public HideBackgroundListener(ButtonItem bi, KDPrinter prt)
        {
            targetItem = bi;
            printer = prt;
        }
    }

    private static class HiddenLayerFilter implements IPaintFilter {

        public boolean accept(Integer layer, boolean isPreviewing)  {
            if(layer.intValue() == 1) {
                if(isPreviewing)
                    return !isPreviewApply();
                else
                    return false;
            } else {
                return true;
            }
        }

        public void setApply(boolean isApply) {
            this.isApply = isApply;
        }

        public boolean isApply()  {
            return isApply;
        }

        public boolean isPreviewApply()  {
            return isPreviewApply;
        }

        public void setPreviewApply(boolean isPreviewApply) {
            this.isPreviewApply = isPreviewApply;
        }

        private boolean isApply;
        private boolean isPreviewApply;

        private HiddenLayerFilter() {
            isApply = true;
        }

    }


    public NotePrinterEx()
    {
    }

    public KDPrinter getPrinter()
    {
        if(ctrlPrint == null)
        {
            ctrlPrint = new KDPrinterEx();
            HiddenLayerFilter filter = new HiddenLayerFilter();
            filter.setApply(true);
            filter.setPreviewApply(false);
            ctrlPrint.setPaintFilter(filter);
            ButtonItem bi = getButtonItemBackground();
            HideBackgroundListener listener = new HideBackgroundListener(bi, ctrlPrint);
            ctrlPrint.getPrintPreview().getPreviewBar().addButtonItem(bi, 3);
            ctrlPrint.getPrintPreview().getPreviewBar().addButtonItemListener(listener);
            ctrlPrint.unRegisterConfigUI(Resources.getMsg("tree.page"));
            ctrlPrint.registerConfigUI(Resources.getMsg("tree.page"), com.kingdee.bos.ctrl.print.config.ui.PageSetupUIForForm.class, 0);
            final AbstractAction exportExcelAction = new AbstractAction() {

                public void actionPerformed(ActionEvent e)
                {
                    if(!checkR1Preview())
                        return;
                    javax.swing.JComponent r1xlsparent = ctrlPrint.getPrintPreview().getPreviewBody();
                    KDFileChooser chooser = new KDFileChooser();
                    int result = chooser.showSaveDialog(r1xlsparent);
                    if(result == 0)
                    {
                        File file = chooser.getSelectedFile();
                        (new IOManager(getPrinter())).exportToXls2(file.getAbsolutePath(), r1xlsparent);
                    }
                }

            }
;
            if(AbstractNotePrint.isExportExcel())
            {
                getBtnExportExcel().addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e)
                    {
                        exportExcelAction.actionPerformed(e);
                    }

                }
);
                ButtonItem exportExcel = getExportExcel();
                ctrlPrint.getPrintPreview().getPreviewBar().addButtonItem(exportExcel, 3);
            }
            KeyStroke keyStroke = KeyStroke.getKeyStroke(69, 640);
            addKeyboardEvent(keyStroke, exportExcelAction);
        }
        return ctrlPrint;
    }

    public void addKeyboardEvent(KeyStroke ks, Action ac)
    {
        PreviewBody pb = getPrinter().getPrintPreview().getPreviewBody();
        InputMap inputMap = pb.getInputMap(2);
        inputMap.put(ks, ks);
        pb.getActionMap().put(ks, ac);
    }

    public void setCongifChangeListener(IConfigChangeListener ccl)
    {
        if(ccl != null && ccl != configChangeListener)
        {
            if(configChangeListener != null)
                getPrinter().removeConfigChangeListener(configChangeListener);
            getPrinter().addConfigChangeListener(ccl);
            configChangeListener = ccl;
        }
    }

    public INotePrintHelper.StateListener getNoteStateListener()
    {
        if(noteStateListener == null)
            noteStateListener = new NoteStateListener();
        return noteStateListener;
    }

    private void setButtonBackgroundVisible(boolean isVisible)
    {
        getButtonItemBackground().setVisible(isVisible);
    }

    private void setButtonBackgroundSelected(boolean isSelected)
    {
        getBtnBackground().setSelected(isSelected);
    }

    private ButtonItem getButtonItemBackground()
    {
        if(biBackground == null)
            biBackground = new ButtonItem(getBtnBackground(), 100);
        return biBackground;
    }

    private ButtonItem getExportExcel()
    {
        if(exportExcel == null)
            exportExcel = new ButtonItem(getBtnExportExcel(), 100);
        return exportExcel;
    }

    private KDToggleButton getBtnBackground()
    {
        if(btnBackground == null)
        {
            btnBackground = new KDToggleButton(getMLS("showBackground", "显示背景"));
            btnBackground.setToolTipText(getMLS("backgroundTooltip", "背景图片只预览，不打印。"));
        }
        return btnBackground;
    }

    private KDWorkButton getBtnExportExcel()
    {
        if(btnExportExcel == null)
        {
            btnExportExcel = new KDWorkButton(getMLS("exportExcel", "导出Excel"));
            btnExportExcel.setToolTipText(getMLS("exportExcelTooltip", "将套打结果导出到Excel。"));
        }
        return btnExportExcel;
    }

    public void setExportXlsPermission(boolean canExport)
    {
        _isR1Preview = canExport;
    }

    private boolean checkR1Preview()
    {
        return _isR1Preview;
    }

    private static String getMLS(String key, String defaultValue)
    {
        String res = (new StringBuilder()).append(CtrlClassUtil.getPackageName(com.kingdee.bos.ctrl.kdf.form2.ui.NotePrinter.class)).append(".ui").toString();
        return LanguageManager.getLangMessage(key, res, defaultValue);
    }

    private KDPrinterEx ctrlPrint;
    private INotePrintHelper.StateListener noteStateListener;
    private ButtonItem biBackground;
    private KDToggleButton btnBackground;
    private ButtonItem exportExcel;
    private KDWorkButton btnExportExcel;
    private IConfigChangeListener configChangeListener;
    private boolean _isR1Preview;




}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\GA\workspace\Dev\lib\client\bos\ctrl-kdf.jar
	Total time: 30 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/