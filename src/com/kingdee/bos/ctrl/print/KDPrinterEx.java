package com.kingdee.bos.ctrl.print;

import com.kingdee.eas.util.SysUtil;

public class KDPrinterEx extends KDPrinter {

	public KDPrinterEx() {
		super();
	}

	public int print2() {
		
		int status = super.print2();
		if (status == 2) SysUtil.abort();
		return status;
	}


}