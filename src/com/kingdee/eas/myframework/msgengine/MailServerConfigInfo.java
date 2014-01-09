package com.kingdee.eas.myframework.msgengine;

import java.io.Serializable;

import com.kingdee.bos.orm.ORMCoreException;
import com.kingdee.eas.framework.bireport.bimanager.util.StringUtils;
import com.kingdee.eas.myframework.util.CryptoUtils;
import com.kingdee.eas.myframework.util.PublicUtils;

public class MailServerConfigInfo extends AbstractMailServerConfigInfo
		implements Serializable {
	public MailServerConfigInfo() {
		super();
	}

	protected MailServerConfigInfo(String pkField) {
		super(pkField);
	}
}