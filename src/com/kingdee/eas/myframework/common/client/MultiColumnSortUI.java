/**
 * output package name
 */
package com.kingdee.eas.myframework.common.client;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import com.kingdee.bos.ctrl.kdf.table.IRow;
import com.kingdee.bos.ctrl.kdf.table.KDTDefaultCellEditor;
import com.kingdee.bos.ctrl.kdf.table.KDTHead;
import com.kingdee.bos.ctrl.kdf.table.KDTRow;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectBlock;
import com.kingdee.bos.ctrl.kdf.table.KDTSelectManager;
import com.kingdee.bos.ctrl.kdf.table.KDTable;
import com.kingdee.bos.ctrl.kdf.table.event.BeforeActionEvent;
import com.kingdee.bos.ctrl.kdf.table.event.BeforeActionListener;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditAdapter;
import com.kingdee.bos.ctrl.kdf.table.event.KDTEditEvent;
import com.kingdee.bos.ctrl.swing.KDComboBox;
import com.kingdee.bos.ctrl.swing.KDWorkButton;
import com.kingdee.bos.ui.face.CoreUIObject;
import com.kingdee.eas.framework.client.FindDialog;
import com.kingdee.eas.framework.client.FindListEvent;
import com.kingdee.eas.framework.client.IFindListListener;
import com.kingdee.eas.framework.client.ListFind;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.eas.myframework.common.SortTypeEnum;
import com.kingdee.eas.myframework.comparators.table.SortColumnCollection;
import com.kingdee.eas.myframework.comparators.table.SortColumnInfo;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.util.SysUtil;
import com.kingdee.eas.util.client.EASResource;
import com.kingdee.util.enums.EnumUtils;


public class MultiColumnSortUI extends AbstractMultiColumnSortUI {
	private static final Logger logger = CoreUIObject.getLogger(MultiColumnSortUI.class);
	
	private KDWorkButton btnMoveTop = new KDWorkButton();
	private KDWorkButton btnMoveUp = new KDWorkButton();
	private KDWorkButton btnMoveDown = new KDWorkButton();
	private KDWorkButton btnMoveBottom = new KDWorkButton();
	private KDWorkButton btnSearchField = new KDWorkButton();
	private boolean isSort = false;
	private SortColumnCollection sortColumns = new SortColumnCollection();

	public MultiColumnSortUI() throws Exception {
		super();
	}

	public void storeFields() {
		super.storeFields();
	}
	
	@Override
	public void onLoad() throws Exception {
		super.onLoad();	
		initTableData();
		
	}
	
	@Override
	protected void initListener() {
		super.initListener();
		tblMain.addKDTEditListener(new KDTEditAdapter() {
			@Override
			public void editStopped(KDTEditEvent event) {
				int rowIndex = event.getRowIndex();
				int colIndex = event.getColIndex();
				tblMain.getSelectManager().set(rowIndex, colIndex);
			}
			public void editValueChanged(KDTEditEvent event) {
				int rowIndex = event.getRowIndex();
				int colIndex = event.getColIndex();
				int isChooseIndex = tblMain.getColumnIndex("isChoose");
				if (colIndex == isChooseIndex) {
					boolean isChoose = (Boolean)event.getValue(); 
					tblMain.getRow(rowIndex).getCell("sortType").getStyleAttributes().setLocked(!isChoose);
					if (!isChoose) {
						tblMain.getRow(rowIndex).getCell("sortType").setValue(null);
					} else {
						tblMain.getRow(rowIndex).getCell("sortType").setValue(SortTypeEnum.ASC);
					//	tblMain.cellAtPosition(colIndex, rowIndex);
					}				
				}
			}
			
		});
	}
	@Override
	public void initUIToolBarLayout() {
		super.initUIToolBarLayout();
		btnMoveTop.setToolTipText("置顶");
		btnMoveTop.setIcon(EASResource.getIcon("imgTbtn_movetop1"));
		btnMoveTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionMoveTop_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnMoveUp.setToolTipText("向上");
		btnMoveUp.setIcon(EASResource.getIcon("imgTbtn_movetop"));
		btnMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionMoveUp_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});	
		btnMoveDown.setToolTipText("向下");
		btnMoveDown.setIcon(EASResource.getIcon("imgTbtn_movedown"));
		btnMoveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionMoveDown_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});	
		btnMoveBottom.setToolTipText("置底");
		btnMoveBottom.setIcon(EASResource.getIcon("imgTbtn_movebottom"));
		btnMoveBottom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionMoveBottom_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});	
		btnSearchField.setToolTipText("查找字段");
		btnSearchField.setIcon(EASResource.getIcon("imgTbtn_find"));
		btnSearchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionSearchField_actionPerformed(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});	
		
		contSortTable.addButton(btnMoveTop);
		contSortTable.addButton(btnMoveUp);
		contSortTable.addButton(btnMoveDown);
		contSortTable.addButton(btnMoveBottom);
		contSortTable.addButton(btnSearchField);
		
		contSortTable.setVisibleOfExpandButton(false);
		
		
	}
	
	
	private void initTableData() throws Exception {
		tblMain.checkParsed();
		tblMain.removeRows();
		tblMain.getSelectManager().setSelectMode(KDTSelectManager.MULTIPLE_ROW_SELECT);
		KDComboBox cmbSortType = new KDComboBox();
		cmbSortType.setName("cmbSortType");
		cmbSortType.setVisible(true);
		cmbSortType.addItems(EnumUtils.getEnumList("com.kingdee.eas.myframework.common.SortTypeEnum").toArray());
        KDTDefaultCellEditor sortTypeCellEdit = new KDTDefaultCellEditor(cmbSortType);
        tblMain.getColumn("sortType").setEditor(sortTypeCellEdit);

		Map uiContext = getUIContext();
		KDTable sortTable = (KDTable) uiContext.get("sortTable");
		KDTHead sortHead = sortTable.getHead();
		KDTRow sortHeadRow = sortHead.getRow(0);
		
		SortColumnCollection defaultSortColumns = (SortColumnCollection) uiContext.get("defaultSortColumns");
		SortColumnCollection currentSortColumns = (SortColumnCollection) uiContext.get("currentSortColumns");
		
		if (currentSortColumns.size() > 0) {
			for (int i = 0; i < currentSortColumns.size(); i++) {
				SortColumnInfo sortColumnInfo = currentSortColumns.get(i);
				String columnIndex = sortColumnInfo.getColumnIndex();
				int cellIndex = sortTable.getColumnIndex(columnIndex);
				SortTypeEnum sortType = sortColumnInfo.getSortType();
				if (sortTable.getColumn(cellIndex).getStyleAttributes().isHided()) continue; 
				addRow(sortTable,sortHeadRow,cellIndex,sortType,true);
			}
		} else {
			for (int i = 0; i < defaultSortColumns.size(); i++) {
				SortColumnInfo sortColumnInfo = defaultSortColumns.get(i);
				String columnIndex = sortColumnInfo.getColumnIndex();
				int cellIndex = sortTable.getColumnIndex(columnIndex);
				SortTypeEnum sortType = sortColumnInfo.getSortType();
				if (sortTable.getColumn(cellIndex).getStyleAttributes().isHided()) continue; 
				addRow(sortTable,sortHeadRow,cellIndex,sortType,true);
			}
		}
		
		for (int i = 0; i < sortTable.getColumnCount(); i++) {
			String columnIndex = sortTable.getColumnKey(i);
			if (currentSortColumns.size() > 0) {
				if (currentSortColumns.isExists(columnIndex)) continue;
			} else {
				if (defaultSortColumns.isExists(columnIndex)) continue;
			}
			
			int cellIndex = sortTable.getColumnIndex(columnIndex);
			if (sortTable.getColumn(cellIndex).getStyleAttributes().isHided()) continue; 
			addRow(sortTable,sortHeadRow,cellIndex,null,false);
		}
	}
	
	private SortColumnCollection getSortColumnCollection() throws Exception {
		SortColumnCollection sortColumnCol =new SortColumnCollection();
		for (int i = 0; i < tblMain.getRowCount(); i++) {
			IRow row = tblMain.getRow(i);
			boolean isChoose = (Boolean)row.getCell("isChoose").getValue();
			if (isChoose) {
				String columnIndex = (String)row.getCell("orderField").getUserObject();
				SortTypeEnum sortType = (SortTypeEnum)row.getCell("sortType").getValue();
				sortColumnCol.add(new SortColumnInfo(columnIndex,sortType));
			}
		}
		return sortColumnCol;
	}
	
	private void addRow(KDTable sortTable, KDTRow sortHeadRow, int cellIndex, SortTypeEnum sortType, boolean isChoose) throws Exception {
		String columnDisplayName = getColumnDisplayName(sortHeadRow, cellIndex);		
		IRow row = tblMain.addRow();
		row.getCell("orderField").setValue(columnDisplayName);
		row.getCell("orderField").setUserObject(sortTable.getColumn(cellIndex).getKey());
		row.getCell("orderField").getStyleAttributes().setLocked(true);
		row.getCell("isChoose").setValue(isChoose);
		row.getCell("isChoose").getStyleAttributes().setLocked(false);
		row.getCell("sortType").setValue(sortType);
		row.getCell("sortType").getStyleAttributes().setLocked(!isChoose);
	}
	
	private String getColumnDisplayName(KDTRow headRow, int cellIndex) throws Exception {
		return (String) headRow.getCell(cellIndex).getValue();
	}
	
	@Override
	public void actionMoveTop_actionPerformed(ActionEvent e) throws Exception {
		MySelectedBlock selectedBlock = checkSelected();
		
		int beginRowIndex  = selectedBlock.beginRow();
		int endRowIndex = selectedBlock.endRow();
		if (beginRowIndex == 0) {
			MsgBoxEx.showWarning("选中行已到首行！");
			return;
		}
		for (int i = 0; i < beginRowIndex; i++) {
			tblMain.moveRow(0, endRowIndex-i);
		}
		tblMain.getSelectManager().select(0,0,endRowIndex-beginRowIndex,0,2);
	}
	
	@Override
	public void actionMoveUp_actionPerformed(ActionEvent e) throws Exception {
		MySelectedBlock selectedBlock = checkSelected();
		
		int beginRowIndex  = selectedBlock.beginRow();
		int endRowIndex = selectedBlock.endRow();
		if (beginRowIndex == 0) {
			MsgBoxEx.showWarning("选中行已到首行！");
			return;
		}
		tblMain.moveRow(beginRowIndex-1, endRowIndex);
		tblMain.getSelectManager().select(beginRowIndex-1,0,endRowIndex-1,0,2);
	}
	
	@Override
	public void actionMoveDown_actionPerformed(ActionEvent e) throws Exception {
		MySelectedBlock selectedBlock = checkSelected();
		
		int beginRowIndex  = selectedBlock.beginRow();
		int endRowIndex = selectedBlock.endRow();
		int rowCount = tblMain.getRowCount();
		
		if (endRowIndex == rowCount-1) {
			MsgBoxEx.showWarning("选中行已到最后一行！");
			return;
		}
		tblMain.moveRow(endRowIndex+1, beginRowIndex);
		tblMain.getSelectManager().select(beginRowIndex+1,0,endRowIndex+1,0,2);

	}
	
	@Override
	public void actionMoveBottom_actionPerformed(ActionEvent e)
			throws Exception {
		MySelectedBlock selectedBlock = checkSelected();
	
		int beginRowIndex  = selectedBlock.beginRow();
		int endRowIndex = selectedBlock.endRow();
		int rowCount = tblMain.getRowCount();
		
		if (endRowIndex == rowCount-1) {
			MsgBoxEx.showWarning("选中行已到最后一行！");
			return;
		}
		for (int i = beginRowIndex; i <= endRowIndex; i++) {
			tblMain.moveRow(beginRowIndex, tblMain.getRowCount()-1);
		}
		
		tblMain.getSelectManager().select(rowCount-(endRowIndex-beginRowIndex)-1,0,rowCount-1,0,2);
	}
	
	private MySelectedBlock checkSelected() throws Exception {
		
		List<KDTSelectBlock> lstSelectd = tblMain.getSelectManager().getBlocks();
		if (lstSelectd.isEmpty()) {
			MsgBoxEx.showWarning(EASResource.getString("com.kingdee.eas.base.commonquery.client.CommonSorterPanel", "moveWarning"));
			SysUtil.abort();
		}
		MySelectedBlock selectedBlock = new MySelectedBlock(lstSelectd);
		if (!selectedBlock.isUninterrupted()) {
			SysUtil.abort();
		}
		return selectedBlock;
	}
	
	@Override
	public void actionSearchField_actionPerformed(ActionEvent e) throws Exception {
		Map uiContext = getUIContext();
		KDTable sortTable = (KDTable) uiContext.get("sortTable");
		FindDialog findDialog;
		Window win = SwingUtilities.getWindowAncestor(this);
		List<ListFind> findPropertyName = new ArrayList<ListFind>();
		ListFind cEnum = new ListFind("orderField","排列字段");
	    findPropertyName.add(cEnum);
	    if(win instanceof Frame)
	    	findDialog = new FindDialog((Frame)win, "", findPropertyName, true);
	    else  findDialog = new FindDialog((Dialog)win, "", findPropertyName, true);
	    findDialog.addFindListListener(new FindListListener(tblMain));
	    findDialog.setLocation(600, 100);
	    findDialog.show();
	}
	@Override
	public void actionOK_actionPerformed(ActionEvent e) throws Exception {
		getUIContext().put("sortColumns", getSortColumnCollection());
		getUIContext().put("isSort", true);
		getUIWindow().close();
		
	}
	
	@Override
	public void actionCancel_actionPerformed(ActionEvent e) throws Exception {
		getUIContext().put("sortColumns", getSortColumnCollection());
		getUIContext().put("isSort", false);
		getUIWindow().close();
	}
	
	@Override
	public void actionReset_actionPerformed(ActionEvent e) throws Exception {
		getUIContext().put("currentSortColumns", new SortColumnCollection());
		initTableData();
		
	}
	
	@Override
	protected void disposeUIWindow() {
		try {
			getUIContext().put("sortColumns", getSortColumnCollection());
		} catch (Exception e) {
			e.printStackTrace();
		}
		getUIContext().put("isSort", false);
		super.disposeUIWindow();
	}
	
	private void putUIContext(boolean isSort) {
		
	}
	
    private class MySelectedBlock {
    	
    	public MySelectedBlock(List<KDTSelectBlock> selectedBlocks) {
    		if (PublicUtils.isEmpty(selectedBlocks)) return;
    		vecSelectedRow.removeAllElements();
    		for (int i = 0; i < selectedBlocks.size(); i++) {
    			KDTSelectBlock selectBlock = selectedBlocks.get(i);
    			int beginRow = selectBlock.getBeginRow();
    			int endRow = selectBlock.getEndRow();
    			for (int j = beginRow; j <=endRow; j++){
    				if (vecSelectedRow.contains(j)) continue;
    				vecSelectedRow.add(j);
    			}
    		}
    		Collections.sort(vecSelectedRow);
    		
		}
    	private Vector<Integer> vecSelectedRow = new Vector<Integer>();
    	/**
    	 * 是否连续行
    	 * @return
    	 */
    	public boolean isUninterrupted() {
    		if (vecSelectedRow.size() < 2) return true;
    		int idx = vecSelectedRow.get(0);
    		for (int i = 1; i < vecSelectedRow.size(); i++) {
    			idx++;
    			int currentIdx = vecSelectedRow.get(i);
    			if (idx != currentIdx) {
    				MsgBoxEx.showWarning("多行移动必须是连续行！");
					return false;
    			}
    		}
    		return true;
    	}
    	public int beginRow() {
    		if (PublicUtils.isEmpty(vecSelectedRow)) return -1;
    		return vecSelectedRow.get(0);
    	}
    	public int endRow() {
    		if (PublicUtils.isEmpty(vecSelectedRow)) return -1;
    		return vecSelectedRow.get(vecSelectedRow.size()-1);
    	}
    }
    
    private class FindListListener implements IFindListListener {
    	private KDTable tblFind;
    	private boolean firstFind = true;
    	
    	public FindListListener(KDTable tblFind) {
    		this.tblFind = tblFind;
    	}
		public void FindClose(FindListEvent e) {
	
			
		}

		public void FindNext(FindListEvent e) {
			boolean isMatch = e.isIsMatch();
			//2:向下，1：向上
			int findDeration = e.getFindDeration();
			String content = e.getSearch();
			int activeRowIndex = tblFind.getSelectManager().getActiveRowIndex();
			if (!firstFind) {
				if (findDeration == 2) {
					activeRowIndex++;
				} else if (findDeration == 1) {
					activeRowIndex--;
				}
			}
			int currentRowIndex = activeRowIndex;
			String propertyName = e.getPropertyName();
		
			firstFind = false;
			
			if (findDeration == 2) {
				for (int i = activeRowIndex; i < tblFind.getRowCount(); i++) {
					currentRowIndex = i;
					if (find(i, content,isMatch,propertyName)) {
						tblFind.getSelectManager().select(i, 0, i, 0, 2);
						return;
					}
				}
				
				if (currentRowIndex == tblFind.getRowCount()) {
					String msg = EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_LocateLast_end");
					MsgBoxEx.showInfo(msg);
				}
			} else if (findDeration == 1){
				for (int i = activeRowIndex; i >=0; i--) {
					currentRowIndex = i;
					if (find(i, content,isMatch,propertyName)) {
						tblFind.getSelectManager().select(i, 0, i, 0, 2);
						return;
					}
				}
				if (currentRowIndex == 0) {
					String msg = EASResource.getString("com.kingdee.eas.framework.FrameWorkResource.Msg_LocateFirst_end");
					MsgBoxEx.showInfo(msg);
				}
			}

			
		}
		
		private boolean find(int rowIndex, String content, boolean isMatch, String propertyName) {
			String orderField_value = (String) tblFind.getRow(rowIndex).getCell(propertyName).getValue();
			if (isMatch) {
				return orderField_value.indexOf(content) >= 0;
			} else return content.equals(orderField_value);
		}
    	
    }
}