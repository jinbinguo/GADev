package com.kingdee.eas.myframework.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.kingdee.eas.myframework.vo.MailAddrCollection;
import com.kingdee.eas.myframework.vo.MailAddrInfo;
import com.kingdee.eas.myframework.vo.MailContentInfo;

public class MailSendUtils implements Serializable {
	
	private String host = null;								//�ʼ�������������
	private int port = 25; 	  								//�ʼ������������˿�
	private String protocol = "smtp";						//�ʼ�����Э��
	private String username = null;							//��¼�û���
	private String password = null;							//��¼����
	private MailAddrInfo fromAddrInfo = null;				//�����˵�ַ
	private MailAddrCollection toAddrCol = null;			//�ռ��˵�ַ
	private MailAddrCollection ccAddrCol = null;			//���͵�ַ
	private MailAddrCollection bccAddrCol =null;			//���͵�ַ
	private String charset = "uft-8";						//�ַ���
	
	public MailSendUtils(String host, int port, String protocol,String username, String password) {
		this.host = host;
		this.port = port;
		this.protocol = protocol;
		this.username = username;
		this.password = password;
		
		fromAddrInfo = new MailAddrInfo(username);
	}
	
	public MailSendUtils(String host,String username, String password) {
		this(host,25,"smtp",username,password);
	}
	
	public Session getSession() {
		Properties pros=new Properties();
		pros.setProperty("mail.transport.protocol", protocol);
		pros.setProperty("mail.host", host);
		pros.setProperty("mail.smtp.port", String.valueOf(port));
		pros.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(pros,
				new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		return session;
	}

	public void send(MailAddrCollection toAddrCol, MailContentInfo mailContentInfo, MailAddrCollection ccAddrCol, MailAddrCollection bccAddrCol)throws Exception{
		Session session = null;
		MimeMessage msg = null;
		Transport ts = null;
		try {
			session = getSession();
			msg=createMessage(session,toAddrCol,mailContentInfo,ccAddrCol,bccAddrCol);
		
			ts=session.getTransport();
			ts.connect();
			ts.sendMessage(msg, msg.getAllRecipients());
		} catch (Exception e) {
			throw e;
		} finally {
			if (ts != null) ts.close();
		}
		
	}
	

	private MimeMessage createMessage(Session session,MailAddrCollection toAddrCol, MailContentInfo mailContentInfo, MailAddrCollection ccAddrCol, MailAddrCollection bccAddrCol) throws Exception {		
		MimeMessage mimeMsg=new MimeMessage(session);
		
		if (fromAddrInfo != null) {
			mimeMsg.setFrom(fromAddrInfo.getIAddr());
		}
		if ((toAddrCol == null || toAddrCol.isEmpty()) && 
			(ccAddrCol == null || ccAddrCol.isEmpty()) &&
			(bccAddrCol == null || bccAddrCol.isEmpty())) {
			throw new Exception("�ʼ����͵�ַ����Ϊ�գ�");
		}
		if (toAddrCol != null && !toAddrCol.isEmpty()) {
			mimeMsg.setRecipients(Message.RecipientType.TO, toAddrCol.getIAddrs());
		}
		
		if (ccAddrCol != null && !ccAddrCol.isEmpty()) {
			mimeMsg.setRecipients(Message.RecipientType.CC, ccAddrCol.getIAddrs());
		}
		
		if (bccAddrCol != null && !bccAddrCol.isEmpty()) {
			mimeMsg.setRecipients(Message.RecipientType.BCC, bccAddrCol.getIAddrs());
		}
		
		mimeMsg.setSubject(mailContentInfo.getSubject());
		
		
		MimeMultipart mimeMultipart=new MimeMultipart();
		//���������ʼ����ĺ͸����ĸ���MimeBodyPart����
		MimeBodyPart contentpart=createContent(mailContentInfo.getContent(),mailContentInfo.getMapImage());
		mimeMultipart.addBodyPart(contentpart);
		
		List<String> lstAtt =mailContentInfo.getLstAtt();
		//������������ʼ����ĺ͸�����MimeMultipart����
		for(int i=0;i<lstAtt.size();i++){
			mimeMultipart.addBodyPart(createAttachment(lstAtt.get(i)));
		}
		
		//���������ʼ�����Ϊ������ϳ���MimeMultipart����
		mimeMsg.setContent(mimeMultipart);
		mimeMsg.saveChanges();
		return mimeMsg;
	}
	
	public MimeBodyPart createContent(String content, Map<String,String> mapImage)throws Exception{
		//�����������Mime��Ϣ��MimeMultipart���󣬽���MimeMultipart���󱣴浽MimeBodyPart����
		MimeBodyPart contentPart=new MimeBodyPart();
		MimeMultipart contentMultipart=new MimeMultipart("related");
		
		//�������ڱ���HTML���ĵ�MimeBodyPart���󣬲��������浽MimeMultipart��
		MimeBodyPart htmlbodypart=new MimeBodyPart();
		htmlbodypart.setContent(content,"text/html;charset=UTF-8");
		contentMultipart.addBodyPart(htmlbodypart);
		
		if(mapImage!=null && mapImage.size()>0) {
			String[] keys = PublicUtils.hashKeyToArray(mapImage);
			for (int i = 0; keys != null && i < keys.length; i++) {
				
				MimeBodyPart imageBodyPart=new MimeBodyPart();//�������ڱ���ͼƬ��MimeBodyPart���󣬲��������浽MimeMultipart��
				FileDataSource fds=new FileDataSource(mapImage.get(keys[i]));//ͼƬ���ڵ�Ŀ¼�ľ���·��
				imageBodyPart.setDataHandler(new DataHandler(fds));
				//������ʾͼƬ
				imageBodyPart.setHeader("Content-ID",keys[i]);
				contentMultipart.addBodyPart(imageBodyPart);
			}
		}
		
		//��MimeMultipart���󱣴浽MimeBodyPart����
		contentPart.setContent(contentMultipart);
		return contentPart;
	}
	
	//������ʱ�ļ����������
	private MimeBodyPart createAttachment(String filename)throws Exception {
		//�������渽����MimeBodyPart���󣬲����븽�����ݺ���Ӧ����Ϣ
		MimeBodyPart attachPart=new MimeBodyPart();
		FileDataSource fsd=new FileDataSource(filename);
		attachPart.setDataHandler(new DataHandler(fsd));
		String suffix,prefix,temFileName;
		temFileName=fsd.getName();
		suffix=temFileName.substring(temFileName.lastIndexOf("."));
		prefix=temFileName.substring(0,temFileName.lastIndexOf("."));
		//�������봦��
		//����������
		attachPart.setFileName(new String((prefix.substring(0,prefix.length())+suffix).getBytes("gb2312"),"ISO8859-1"));
		return attachPart;
	}
	
	public static void main(String[] args) {
		MailSendUtils mail = new MailSendUtils("smtp.163.com","jiraadmin@163.com","jiraadmin123");
		MailAddrCollection toAddrCol = new MailAddrCollection();
		toAddrCol.add(new MailAddrInfo("guojbin@qq.com","��"));
		MailAddrCollection ccAddrCol = new MailAddrCollection();
		ccAddrCol.add(new MailAddrInfo("guojbin@163.com","��"));
		MailAddrCollection bccAddrCol = new MailAddrCollection();
		bccAddrCol.add(new MailAddrInfo("guojbin@21cn.com","��"));
		MailContentInfo mailContentInfo= new MailContentInfo();
		mailContentInfo.setSubject("����");
		mailContentInfo.setContent("����<img src=\"cid:peak00000001\" width=\"79\" height=\"79\">aaa<img src=\"cid:peak00000002\" width=\"79\" height=\"79\">a");
		Map<String,String> hashImage = new HashMap<String, String>();
	//	hashImage.put("peak00000001", "C:\\Users\\aben\\Pictures\\IMAG0150.jpg");
		hashImage.put("peak00000002", "C:\\Users\\aben\\Pictures\\QQ��ͼ20121115114034.png");
		
		mailContentInfo.setMapImage(hashImage);
		List<String> lstAtt = new Vector<String>();
		//lstAtt.add("C:\\Users\\aben\\Pictures\\IMAG0150.jpg");
		lstAtt.add("C:\\Users\\aben\\Pictures\\QQ��ͼ20121115114034.png");
		mailContentInfo.setLstAtt(lstAtt);
		try {
			mail.send(toAddrCol, mailContentInfo, ccAddrCol, bccAddrCol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
