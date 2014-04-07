package com.kingdee.bos.ctrl.report.forapp.kdnote.client.ui;

import com.kingdee.bos.ctrl.common.ResourceManager;
import com.kingdee.bos.ctrl.common.layout.table.*;
import com.kingdee.bos.ctrl.common.layout.table2.TableLayout2;
import com.kingdee.bos.ctrl.common.ui.TypicalDialog;
import com.kingdee.bos.ctrl.common.ui.WindowUtil;
import com.kingdee.bos.ctrl.common.ui.restree.ResObjectNode;
import com.kingdee.bos.ctrl.common.ui.tree.*;
import com.kingdee.bos.ctrl.common.util.*;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.NoteTreeCtrl;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.multilanguage.Language;
import com.kingdee.bos.ctrl.report.forapp.kdnote.client.util.*;
import com.kingdee.bos.ctrl.reportone.kdrs.biz.*;
import com.kingdee.bos.ctrl.reportone.kdrs.biz.content.IBizContent;
import com.kingdee.bos.ctrl.reportone.kdrs.exception.KDRSException;
import com.kingdee.bos.ctrl.reportone.kdrs.exception.NotFoundException;
import com.kingdee.bos.ctrl.swing.*;
import com.kingdee.bos.ctrl.swing.util.CtrlSwingUtilities;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.TreePath;
import org.apache.log4j.Logger;

public class NoteFileDialogEx extends TypicalDialog
    implements TreeSelectionListener, TreeExpansionListener, ListSelectionListener {
   
	private class ListElement  {
        public String getName()  {
            return name;
        }
        public String getAlias() {
            return alias;
        }
        public String getCategory() {
            return category;
        }

        public String toString()  {
            if(StringUtil.isEmptyString(alias))
                return name;
            if(isShowFileName()) {
                StringBuffer sb = new StringBuffer();
                sb.append(alias);
                sb.append("(");
                sb.append(name);
                sb.append(")");
                return sb.toString();
            } else {
                return alias;
            }
        }

        public int compareTo(Object another) {
            return toString().compareTo(another.toString());
        }

        private String name;
        private String alias;
        private String category;

        public ListElement(String name, String alias, String category)  {
            super();
            this.name = name;
            this.alias = alias;
            this.category = category;
        }
    }

    class CustomListCellRender  implements ListCellRenderer  {

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            JComponent c = (JComponent)render.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            c.setBorder(null);
            JCheckBox lab = new JCheckBox();
            ListElement le = (ListElement)value;
           // if("r1-print".equals(le.getCategory()))
             //   lab.setIcon(ResourceManager.getIcon(com.kingdee.bos.ctrl.report.forapp.kdnote.client.ui.NoteFileDialogEx.class, "res.r1_print.gif"));
           // else
            //    lab.setIcon(ResourceManager.getIcon(com.kingdee.bos.ctrl.report.forapp.kdnote.client.ui.NoteFileDialogEx.class, "res/empty.gif"));
            lab.setSelected(isSelected);
            lab.setOpaque(true);
            lab.setBackground(c.getBackground());
            TableLayout tl = TableLayout.splitCol(2);
            tl.colStyle(0).setWidth(22);
            tl.colStyle(1).setPriX(1);
            KDPanel pan = new KDPanel(tl);
            pan.add(lab, tl.cell(0));
            pan.add(c, tl.cell(1));
            return pan;
        }

        ListCellRenderer render;

        CustomListCellRender() {
            super();
            render = new DefaultListCellRenderer();
        }
        
    }

    private static class NoteTemplatesPanel extends KDPanel  {

        public Language getSelecteLanguage() {
            return lang;
        }

        private KDList lstTemplates;
        private KDCheckBox cbUseOrgFilter;
        private KDComboBox multiLanguage;
        private Language lang;


        NoteTemplatesPanel()  {
            multiLanguage = new KDComboBox(Language.SUPPORT_LANGUAGES);
            lang = Language.locale2Language(Locale.getDefault());
            lstTemplates = new KDList();
            cbUseOrgFilter = new KDCheckBox(MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.useOrgFilter", "只显示当前组织的模板"));
            TableLayout2 layout = new TableLayout2(2, 2);
            layout.setFixedHeight(0, 20);
            layout.setRatableHeight(1, 1);
            layout.setRatableWidth(0, 1);
            layout.setRatableWidth(1, 1);
            layout.setRowsSpacing(TableLayout2.ALL, 3);
            setLayout(layout);
        //    add(cbUseOrgFilter, TableLayout2.param(0, 0));
            add(CtrlSwingUtilities.createLabelContainer(multiLanguage, MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.temLanguage", "模板语言"), 80), TableLayout2.param(0, 1));
            multiLanguage.setSelectedItem(lang);
            add(new KDScrollPane(lstTemplates), TableLayout2.param(1, 0, 1, 1));
        }
    }


    public NoteFileDialogEx()  {
        _isShowSaveAsDefault = false;
        _isShowFileName = false;
        initControls();
    }

    public NoteFileDialogEx(Frame owner)  {
        super(owner);
        _isShowSaveAsDefault = false;
        _isShowFileName = false;
        initControls();
    }

    public NoteFileDialogEx(Dialog owner)  {
        super(owner);
        _isShowSaveAsDefault = false;
        _isShowFileName = false;
        initControls();
    }

    public NoteFileDialogEx(Frame owner, BizRpcReducer rpcReducer)  {
        super(owner);
        _isShowSaveAsDefault = false;
        _isShowFileName = false;
        _rpcReducer = rpcReducer;
        initControls();
    }

    public NoteFileDialogEx(Dialog owner, BizRpcReducer rpcReducer)  {
        super(owner);
        _isShowSaveAsDefault = false;
        _isShowFileName = false;
        _rpcReducer = rpcReducer;
        initControls();
    }

    public NoteFileDialogEx(BizRpcReducer rpcReducer)  {
        _isShowSaveAsDefault = false;
        _isShowFileName = false;
        _rpcReducer = rpcReducer;
        initControls();
    }

    public static NoteFileDialogEx create(Component owner, BizRpcReducer rpcReducer)  {
        Window win;
        if(owner instanceof Window)
            win = (Window)owner;
        else
            win = SwingUtilities.getWindowAncestor(owner);
        NoteFileDialogEx inst;
        if(win instanceof Frame)
            inst = new NoteFileDialogEx((Frame)win, rpcReducer);
        else
        if(win instanceof Dialog)
            inst = new NoteFileDialogEx((Dialog)win, rpcReducer);
        else
            inst = new NoteFileDialogEx(rpcReducer);
        return inst;
    }

    public void setNoteType(String noteType) {
        _tree.setPathTextToShow(noteType);
    }

    protected IBizContext getBizContext() {
        return KDNoteHelper.getContext();
    }

    protected void todoInit()  {
        setTitle(MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.selectTemplate", "多选择模板"));
        setSize(640, 480);
        super.btnOk.setEnabled(false);
    }

    protected void todoAddControls(Container parent, Cell thisCell) {
        Table tb = thisCell.split(2, 1);
        KDSplitPane sep = new KDSplitPane(1);
        sep.setLeftComponent(_tree.getTreeUI());
        sep.setRightComponent(_rightPanel);
        sep.setDividerLocation(220);
        parent.add(sep, tb.cell(0));
        if(_isShowSaveAsDefault)  {
            _chkSaveAsDefault = new KDCheckBox(MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.saveAsDefault", "将本次选中保存为缺省模板（可在“系统平台-套打-套打配置”中更改）"));
         //   parent.add(_chkSaveAsDefault, tb.cell(1));
        }
    }

    private void initControls()  {
        _rightPanel = new NoteTemplatesPanel();
        _lstFile = _rightPanel.lstTemplates;
        _lstFile.setCellRenderer(new CustomListCellRender());
        
        _lstFile.addListSelectionListener(this);
        IContextSupplier supplier = new IContextSupplier() {

            public IBizContext getContext()  {
                return getBizContext();
            }
        };
        try  {
            _tree = new NoteTreeCtrl(supplier, null, false);
        }
        catch(KDRSException ex)
        {
            LogUtil.showException(log, ex);
            WindowUtil.msgboxError(ex.toString(), "Error", this);
        }
        _tree.setRelativeRoot("/EAS_Note.kdrs");
        _tree.getTreeUI().addTreeSelectionListener(this);
        TreeExpansionListener tels[] = _tree.getTreeUI().getTree().getTreeExpansionListeners();
        for(int i = 0; i < tels.length; i++)
            if(tels[i] instanceof com.kingdee.bos.ctrl.common.ui.tree.TreeUI.EventLisnter)
                _tree.getTreeUI().getTree().removeTreeExpansionListener(tels[i]);

        _tree.getTreeUI().getTree().addTreeExpansionListener(this);
        _tree.setShowLeaf(false);
        _tree.getTreeUI().setRealtimeSelect(false);
        _tree.getTreeUI().getTree().setShowsRootHandles(true);
        _lstFile.setSelectionMode(0);
        _lstFile.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e)  {
            	
            	
                if(e.getClickCount() != 2)
                    return;
                int idx = _lstFile.locationToIndex(e.getPoint());
                if(idx >= 0)
                    onOk();
            }
        });
        _rightPanel.cbUseOrgFilter.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                refreshTemplateList();
            }
        });
        _rightPanel.multiLanguage.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e)  {
                _rightPanel.lang = (Language)_rightPanel.multiLanguage.getSelectedItem();
                refreshTemplateList();
            }
        });
    }

    public void setNoteName(String noteName) {
        int idx = 0;
        int i = 0;
        for(int c = _lstFile.getElementCount() - 1; i < c; i++)
        {
            ListElement le = (ListElement)_lstFile.getElement(i);
            if(StringUtil.equalsIgnoreCase(le.getName(), noteName))
                idx = i;
        }

        _lstFile.setSelectedIndex(idx);
        super.btnOk.setEnabled(idx != -1);
    }

    public String getNoteName() {
        ListElement le = (ListElement)_lstFile.getSelectedValue();
        if(le != null)
            return le.getName();
        else
            return null;
    }

    public String getNoteAlias()  {
        ListElement le = (ListElement)_lstFile.getSelectedValue();
        if(le != null)
            return le.getAlias();
        else
            return null;
    }

    public String getNoteDir()  {
        return PathUtil.makeChildPath(_tree.getRelativeRoot(), _tree.getTreeUI().getSelectionPathText());
    }

    public String getNotePathText()  {
        return PathUtil.makeChildPath(getNoteDir(), getNoteName());
    }

    public String getNoteType()  {
        return _tree.getTreeUI().getSelectionPathText();
    }

    public String getRelativeNotePathText()  {
        return PathUtil.makeChildPath(getNoteType(), getNoteName());
    }

    public void valueChanged(ListSelectionEvent e)  {
        btnOk.setEnabled(_lstFile.getSelectedIndex() != -1);
    }

    private void refreshTemplateList()  {
        TreeUINode selectedNode = _tree.getTreeUI().getSelectionNode();
        HashMap cacheValues = (HashMap)selectedNode.getUserObject();
        super.btnOk.setEnabled(false);
        _lstFile.removeAllElements();
        HashMap vals = null;
        if(cacheValues == null)
        {
            if(selectedNode.isChildrenLoaded())
                return;
            vals = expandNode(selectedNode, getNoteDir());
        } else
        {
            vals = cacheValues;
        }
        if(vals == null)
            return;
        String names[] = (String[])(String[])vals.get("name");
        String alias[] = (String[])(String[])vals.get("alias");
        String categories[] = (String[])(String[])vals.get("category");
        String orgss[] = (String[])(String[])vals.get("eas_template_relation_orgs");
        if(names != null && log.isDebugEnabled())
            log.debug((new StringBuilder()).append("entries:").append(names.length).toString());
        boolean doFilter = _rightPanel.cbUseOrgFilter.isSelected();
        int idxKDFStart = 0;
        IBizContent bizCtn = _tree.getBizContent();
        for(int i = 0; i < names.length; i++)
        {
            boolean isKDF = "kdrs-form".equals(categories[i]);
            boolean isR1 = "r1-print".equals(categories[i]);
            if(!isKDF && !isR1 || !_rightPanel.lang.equal(Language.readTempLang(names[i])) && !isKDF)
                continue;
            try
            {
                boolean isCanUse = NoteUseUtil.isCanUse(bizCtn, (new StringBuilder()).append(((ResObjectNode)selectedNode).getResPath()).append("/").append(names[i]).toString());
                if(!isCanUse)
                    continue;
            }
            catch(KDRSException ex)
            {
                log.debug((new StringBuilder()).append(selectedNode).append("获取属性：NoteUseUtil.").append("user_enabled_users").append("失败").toString());
                continue;
            }
            if(doFilter && (orgss[i] == null || orgss[i].length() == 0 || orgss[i].indexOf(NoteUtil.getCurrentOrgUnitId()) == -1))
            {
                if(log.isDebugEnabled())
                    log.debug((new StringBuilder()).append("ignore teplate/r1templat:").append(names[i]).toString());
                continue;
            }
            ListElement element = new ListElement(names[i], alias[i], categories[i]);
            int idx = idxKDFStart;
            if(isR1)
            {
                int j = 0;
                do
                {
                    if(j >= idxKDFStart)
                        break;
                    if(element.compareTo(_lstFile.getElement(j)) < 0)
                    {
                        idx = j;
                        break;
                    }
                    j++;
                } while(true);
                idxKDFStart++;
            } else
            if(isKDF)
            {
                int j = _lstFile.getElementCount() - 1;
                do
                {
                    if(j < idxKDFStart)
                        break;
                    if(element.compareTo(_lstFile.getElement(j)) > 0)
                    {
                        idx = j + 1;
                        break;
                    }
                    j--;
                } while(true);
            }
            _lstFile.insertElement(element, idx);
        }

        if(_lstFile.getElementCount() > 0)
            _lstFile.setSelectedIndex(0);
        btnOk.setEnabled(_lstFile.getSelectedIndex() != -1);
    }

    public void valueChanged(TreeSelectionEvent e)  {
        refreshTemplateList();
    }

    public void setShowSaveAsDefault(boolean b)   {
        _isShowSaveAsDefault = b;
    }

    public boolean isSaveAsDefault()
    {
        return _chkSaveAsDefault != null && _chkSaveAsDefault.isSelected();
    }

    public boolean isShowFileName()
    {
        return _isShowFileName;
    }

    public void setShowFileName(boolean isShowFileName)
    {
        _isShowFileName = isShowFileName;
    }

    public final boolean isUseTemplateOrgFilter()
    {
        return _rightPanel.cbUseOrgFilter.isSelected();
    }

    public final void setUseTemplateOrgFilter(boolean useTemplateOrgFilter)
    {
        _rightPanel.cbUseOrgFilter.setSelected(useTemplateOrgFilter);
        _tree.setUseTemplateOrgFilter(useTemplateOrgFilter);
    }

    public void setUseTemplateOrgFilterEnabled(boolean enabled)
    {
        _rightPanel.cbUseOrgFilter.setEnabled(enabled);
    }

    public boolean showDialog()
    {
        if(!isAddControls)
        {
            String absPath = PathUtil.makeChildPath("/EAS_Note.kdrs", _tree.getPathTextToShow());
            HashMap multiResult[] = null;
            if(_rpcReducer != null)
                try
                {
                    multiResult = (HashMap[])(HashMap[])_rpcReducer.getBatchResult("IBizContent.list2", new Object[] {
                        absPath, List_Attrs
                    });
                }
                catch(NotFoundException e)
                {
                    WindowUtil.msgboxError((new StringBuilder()).append(MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.pathError1", "程序指定模板所在文件夹：")).append(_tree.getPathTextToShow()).append(MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.pathError", "不存在，请联系套打管理员检查套打管理中该路径是否正确！")).toString(), MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.pathErrorTitle", "路径错误"), this);
                    return false;
                }
                catch(KDRSException e)
                {
                    log.error("GetBatchResult list2 error.", e);
                }
            else
                try
                {
                    multiResult = getBizContext().getBizContent().list2(absPath, List_Attrs);
                }
                catch(NotFoundException e)
                {
                    WindowUtil.msgboxError((new StringBuilder()).append(MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.pathError1", "程序指定模板所在文件夹：")).append(_tree.getPathTextToShow()).append(MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.pathError", "不存在，请联系套打管理员检查套打管理中该路径是否正确！")).toString(), MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.pathErrorTitle", "路径错误"), this);
                    return false;
                }
                catch(KDRSException e)
                {
                    log.error("", e);
                    if(e instanceof NotFoundException)
                    {
                        WindowUtil.msgboxError((new StringBuilder()).append(MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.pathError1", "程序指定模板所在文件夹：")).append(_tree.getPathTextToShow()).append(MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.pathError", "不存在，请联系套打管理员检查套打管理中该路径是否正确！")).toString(), MultiLanguageUtil.getMLS("ui.NoteFileDialogEx.pathErrorTitle", "路径错误"), this);
                        return false;
                    }
                }
            TreeUINode node = _tree.getTreeUI().getRootNode();
            node.setChildrenLoaded(true);
            if(multiResult != null)
            {
                int relaRootDeep = PathUtil.splitPath("/EAS_Note.kdrs").length - 1;
                int i = relaRootDeep;
                do
                {
                    if(i >= multiResult.length - 1)
                        break;
                    String name = (String)multiResult[i].get("name");
                    String alias = (String)multiResult[i].get("alias");
                    TreeUINode childNode;
                    try
                    {
                        childNode = _tree.newTreeNode("folder", name, alias);
                    }
                    catch(Exception e)
                    {
                        log.error("", e);
                        break;
                    }
                    _tree.getTreeUI().addChildNode(childNode, node);
                    childNode.setChildrenLoaded(true);
                    node = childNode;
                    i++;
                } while(true);
                node.setUserObject(multiResult[multiResult.length - 1]);
                createUnloadedChildNode(node, multiResult[multiResult.length - 1]);
                _tree.getTreeUI().setSelectionNode(node);
                _tree.getTreeUI().expand(_tree.getTreeUI().getSelectionPath());
            }
        }
        return super.showDialog();
    }

    private void createUnloadedChildNode(TreeUINode targetNode, HashMap vals)
    {
        String names[] = (String[])(String[])vals.get("name");
        String alias[] = (String[])(String[])vals.get("alias");
        String categories[] = (String[])(String[])vals.get("category");
        for(int i = 0; i < names.length; i++)
        {
            if(!"folder".equals(categories[i]))
                continue;
            TreeUINode childNode = null;
            try
            {
                childNode = _tree.newTreeNode("folder", names[i], alias[i]);
            }
            catch(Exception e)
            {
                log.error("", e);
            }
            if(childNode != null)
                _tree.getTreeUI().addChildNode(childNode, targetNode);
        }

    }

    private HashMap expandNode(TreeUINode targetNode, String absPathText)
    {
        HashMap vals = null;
        try
        {
            vals = getBizContext().getBizContent().list(absPathText, List_Attrs);
        }
        catch(KDRSException e1)
        {
            log.error("取文件夹内容列表时发生异常。", e1);
        }
        if(vals != null)
        {
            targetNode.setUserObject(vals);
            targetNode.setChildrenLoaded(true);
            createUnloadedChildNode(targetNode, vals);
        }
        return vals;
    }

    public void treeExpanded(TreeExpansionEvent event)
    {
        TreePath treePath = event.getPath();
        if(treePath != null)
        {
            TreeUINode targetNode = (TreeUINode)treePath.getLastPathComponent();
            if(!targetNode.isChildrenLoaded())
            {
                String targetPathText = TreeUtil.makePathText(treePath);
                String absPathText = PathUtil.makeChildPath(_tree.getRelativeRoot(), targetPathText);
                expandNode(targetNode, absPathText);
            }
        }
    }

    public void treeCollapsed(TreeExpansionEvent treeexpansionevent)
    {
    }

    private static Logger log = LogUtil.getPackageLogger(com.kingdee.bos.ctrl.report.forapp.kdnote.client.ui.NoteFileDialogEx.class);
    public static final HashMap List_Attrs;
    private static NoteTreeCtrl _tree;
    private KDList _lstFile;
    private NoteTemplatesPanel _rightPanel;
    private BizRpcReducer _rpcReducer;
    private KDCheckBox _chkSaveAsDefault;
    private boolean _isShowSaveAsDefault;
    private boolean _isShowFileName;

    static 
    {
        List_Attrs = new HashMap();
        List_Attrs.put("name", null);
        List_Attrs.put("alias", null);
        List_Attrs.put("category", null);
        List_Attrs.put("eas_template_relation_orgs", null);
    }




}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\GA\workspace\Dev\lib\client\bos\ctrl-note.jar
	Total time: 47 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/