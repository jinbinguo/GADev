package com.kingdee.eas.myframework.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailContentInfo implements Serializable {

	private String subject= null;							//�ʼ�����
	private String content ="";								//�ʼ�����
	private Map<String,String> mapImage = new HashMap<String, String>(); //Ƕ��ͼƬ�б�
	private List<String> lstAtt = new ArrayList<String>(); //�����б�
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Map<String, String> getMapImage() {
		return mapImage;
	}
	public void setMapImage(Map<String, String> mapImage) {
		this.mapImage = mapImage;
	}
	public List<String> getLstAtt() {
		return lstAtt;
	}
	public void setLstAtt(List<String> lstAtt) {
		this.lstAtt = lstAtt;
	}
	
	
}
