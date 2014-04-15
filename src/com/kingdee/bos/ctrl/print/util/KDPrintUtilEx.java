package com.kingdee.bos.ctrl.print.util;



import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.text.MessageFormat;

import org.jdom.output.XMLOutputter;

import com.kingdee.eas.common.client.SysContext;

public class KDPrintUtilEx extends KDPrintUtil {
    private static final String directPrintConfigFilePath = (new StringBuilder()).append(System.getProperty("user.dir")).append("/cache/KDPrintConfig/{0}/{1}/{2}").toString();

    private static String getDcName() {
    	return SysContext.getSysContext().getDcName();
    }
    private static String getUserId()  {
    	return SysContext.getSysContext().getCurrentUserInfo().getId().toString();
    }
    
    
    public static void bakFile() throws Exception {
    	File file = new File(MessageFormat.format(directPrintConfigFilePath, new Object[] {getDcName(), getUserId(),"KDPrintConfig.xml"}));
    	if (file == null || !file.exists()) return;
    	String fileName = file.getAbsolutePath();
    	fileName = fileName + ".bak";
    	File fileBak = new File(fileName);
    	if (fileBak != null && fileBak.exists()) fileBak.delete();
    	FileInputStream fis = new FileInputStream(file);
    	InputStreamReader is = new InputStreamReader(fis);
    	BufferedReader br = new BufferedReader(is);
    	String lineData = null;
    	
    	FileOutputStream fout = new FileOutputStream(fileBak);
    	BufferedOutputStream bout =new BufferedOutputStream(fout);
    	
    	do {
    		lineData = br.readLine();
    		if (lineData == null) break;
    		bout.write(lineData.getBytes());
    		
    	} while(lineData != null);
    	bout.flush();
    	bout.close();
    	br.close();
    	is.close();
    	fis.close();
    	if (file.exists()) file.delete();
    	
   
   
    
    }
    
    public static void revertFile() throws Exception {
    	File file = new File(MessageFormat.format(directPrintConfigFilePath, new Object[] {getDcName(), getUserId(),"KDPrintConfig.xml.bak"}));
    	if (file == null || !file.exists()) return;
    	String fileName = file.getAbsolutePath();
    	fileName = fileName.replace(".bak", "");
    	File fileRevert = new File(fileName);
    	if (fileRevert != null && fileRevert.exists()) fileRevert.delete();
    	
    	FileInputStream fis = new FileInputStream(file);
    	InputStreamReader is = new InputStreamReader(fis);
    	BufferedReader br = new BufferedReader(is);
    	String lineData = null;
    	
    	FileOutputStream fout = new FileOutputStream(fileRevert);
    	BufferedOutputStream bout =new BufferedOutputStream(fout);
    	
    	do {
    		lineData = br.readLine();
    		if (lineData == null) break;
    		bout.write(lineData.getBytes());
    		
    	} while(lineData != null);
    	bout.flush();
    	bout.close();
    	
    	br.close();
    	is.close();
    	fis.close();
    	if (file.exists()) file.delete();
    }
	
}
