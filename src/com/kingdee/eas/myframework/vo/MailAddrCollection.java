package com.kingdee.eas.myframework.vo;

import java.io.Serializable;
import java.util.Vector;

import javax.mail.internet.InternetAddress;

import com.kingdee.eas.myframework.util.PublicUtils;

public class MailAddrCollection implements Serializable {

	private Vector<MailAddrInfo> vecMailAddr = new Vector<MailAddrInfo>();
	
	public void add(MailAddrInfo mailAddrInfo) {
		vecMailAddr.add(mailAddrInfo);
	}
	public int size() {
		return vecMailAddr.size();
	}
	
	public InternetAddress[] getIAddrs() throws Exception {
		InternetAddress[] addrs = new InternetAddress[vecMailAddr.size()];
		for (int i = 0; i < addrs.length; i++) {
			addrs[i] = vecMailAddr.get(i).getIAddr();
		}
		return addrs;
	}
	
	public boolean isEmpty() {
		return vecMailAddr.isEmpty();
	}
}
