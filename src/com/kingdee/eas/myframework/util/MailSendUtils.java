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
	
	private String host = null;								//邮件服务器主机名
	private int port = 25; 	  								//邮件服务器主机端口
	private String protocol = "smtp";						//邮件传输协议
	private String username = null;							//登录用户名
	private String password = null;							//登录密码
	private MailAddrInfo fromAddrInfo = null;				//发件人地址
	private MailAddrCollection toAddrCol = null;			//收件人地址
	private MailAddrCollection ccAddrCol = null;			//抄送地址
	private MailAddrCollection bccAddrCol =null;			//暗送地址
	private String charset = "uft-8";						//字符集
	
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
			throw new Exception("邮件发送地址不能为空！");
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
		//创建代表邮件正文和附件的各个MimeBodyPart对象
		MimeBodyPart contentpart=createContent(mailContentInfo.getContent(),mailContentInfo.getMapImage());
		mimeMultipart.addBodyPart(contentpart);
		
		List<String> lstAtt =mailContentInfo.getLstAtt();
		//创建用于组合邮件正文和附件的MimeMultipart对象
		for(int i=0;i<lstAtt.size();i++){
			mimeMultipart.addBodyPart(createAttachment(lstAtt.get(i)));
		}
		
		//设置整个邮件内容为最终组合出的MimeMultipart对象
		mimeMsg.setContent(mimeMultipart);
		mimeMsg.saveChanges();
		return mimeMsg;
	}
	
	public MimeBodyPart createContent(String content, Map<String,String> mapImage)throws Exception{
		//创建代表组合Mime消息的MimeMultipart对象，将该MimeMultipart对象保存到MimeBodyPart对象
		MimeBodyPart contentPart=new MimeBodyPart();
		MimeMultipart contentMultipart=new MimeMultipart("related");
		
		//创建用于保存HTML正文的MimeBodyPart对象，并将它保存到MimeMultipart中
		MimeBodyPart htmlbodypart=new MimeBodyPart();
		htmlbodypart.setContent(content,"text/html;charset=UTF-8");
		contentMultipart.addBodyPart(htmlbodypart);
		
		if(mapImage!=null && mapImage.size()>0) {
			String[] keys = PublicUtils.hashKeyToArray(mapImage);
			for (int i = 0; keys != null && i < keys.length; i++) {
				
				MimeBodyPart imageBodyPart=new MimeBodyPart();//创建用于保存图片的MimeBodyPart对象，并将它保存到MimeMultipart中
				FileDataSource fds=new FileDataSource(mapImage.get(keys[i]));//图片所在的目录的绝对路径
				imageBodyPart.setDataHandler(new DataHandler(fds));
				//正文显示图片
				imageBodyPart.setHeader("Content-ID",keys[i]);
				contentMultipart.addBodyPart(imageBodyPart);
			}
		}
		
		//将MimeMultipart对象保存到MimeBodyPart对象
		contentPart.setContent(contentMultipart);
		return contentPart;
	}
	
	//处理临时文件名的随机数
	private MimeBodyPart createAttachment(String filename)throws Exception {
		//创建保存附件的MimeBodyPart对象，并加入附件内容和相应的信息
		MimeBodyPart attachPart=new MimeBodyPart();
		FileDataSource fsd=new FileDataSource(filename);
		attachPart.setDataHandler(new DataHandler(fsd));
		String suffix,prefix,temFileName;
		temFileName=fsd.getName();
		suffix=temFileName.substring(temFileName.lastIndexOf("."));
		prefix=temFileName.substring(0,temFileName.lastIndexOf("."));
		//中文乱码处理
		//处理附件名过
		attachPart.setFileName(new String((prefix.substring(0,prefix.length())+suffix).getBytes("gb2312"),"ISO8859-1"));
		return attachPart;
	}
	
	public static void main(String[] args) {
		MailSendUtils mail = new MailSendUtils("smtp.163.com","jiraadmin@163.com","jiraadmin123");
		MailAddrCollection toAddrCol = new MailAddrCollection();
		toAddrCol.add(new MailAddrInfo("guojbin@qq.com","郭"));
		MailAddrCollection ccAddrCol = new MailAddrCollection();
		ccAddrCol.add(new MailAddrInfo("guojbin@163.com","郭"));
		MailAddrCollection bccAddrCol = new MailAddrCollection();
		bccAddrCol.add(new MailAddrInfo("guojbin@21cn.com","郭"));
		MailContentInfo mailContentInfo= new MailContentInfo();
		mailContentInfo.setSubject("标题");
		mailContentInfo.setContent("内容<img src=\"cid:peak00000001\" width=\"79\" height=\"79\">aaa<img src=\"cid:peak00000002\" width=\"79\" height=\"79\">a");
		Map<String,String> hashImage = new HashMap<String, String>();
	//	hashImage.put("peak00000001", "C:\\Users\\aben\\Pictures\\IMAG0150.jpg");
		hashImage.put("peak00000002", "C:\\Users\\aben\\Pictures\\QQ截图20121115114034.png");
		
		mailContentInfo.setMapImage(hashImage);
		List<String> lstAtt = new Vector<String>();
		//lstAtt.add("C:\\Users\\aben\\Pictures\\IMAG0150.jpg");
		lstAtt.add("C:\\Users\\aben\\Pictures\\QQ截图20121115114034.png");
		mailContentInfo.setLstAtt(lstAtt);
		try {
			mail.send(toAddrCol, mailContentInfo, ccAddrCol, bccAddrCol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
