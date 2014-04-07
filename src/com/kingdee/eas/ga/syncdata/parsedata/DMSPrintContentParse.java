package com.kingdee.eas.ga.syncdata.parsedata;

import java.io.File;
import java.io.Serializable;

import com.kingdee.eas.ga.syncdata.DMSPrintContentEntryCollection;
import com.kingdee.eas.ga.syncdata.DMSPrintContentEntryInfo;
import com.kingdee.eas.myframework.util.PublicUtils;
import com.kingdee.eas.myframework.util.TxtFileUtils;

public class DMSPrintContentParse implements Serializable {
	
	private TxtFileUtils txtFileUtils = new TxtFileUtils();
	
	public DMSPrintContentParse(File file) throws Exception {
		txtFileUtils.readFile(file);
	}
	
	public DMSPrintContentEntryCollection parseContent() throws Exception {
		int seq = 1;
		DMSPrintContentEntryCollection dmsPrintContentEntryCol = new DMSPrintContentEntryCollection();
		while(!txtFileUtils.isEOF()) {
			DMSPrintContentEntryInfo dmsPrintContentEntryInfo = new DMSPrintContentEntryInfo();
			String content = txtFileUtils.readLineData();
			dmsPrintContentEntryInfo.setContent(content);
			dmsPrintContentEntryInfo.setSeq(seq);
			seq++;
			dmsPrintContentEntryCol.add(dmsPrintContentEntryInfo);
		}
		return dmsPrintContentEntryCol;
		
	}
	
	
	
}
