package com.kingdee.eas.myframework.vo;

import java.io.Serializable;

import javax.mail.internet.InternetAddress;

public class MailAddrInfo implements Serializable {
	private String mailAddr = null;
	private String aliasName = null;
	private String charset = "utf-8";
	
	public MailAddrInfo(String mailAddr) {
		this(mailAddr,null);
	}
	public MailAddrInfo(String mailAddr, String aliasName) {
		this.mailAddr = mailAddr;
		this.aliasName  = aliasName;
	}
	
	public InternetAddress getIAddr() throws Exception {
		InternetAddress addr = new InternetAddress(mailAddr,aliasName,charset);
		return addr;
	}
}
