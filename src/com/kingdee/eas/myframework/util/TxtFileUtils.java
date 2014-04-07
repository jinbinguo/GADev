package com.kingdee.eas.myframework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

public class TxtFileUtils implements Serializable {
	
	private FileInputStream fis = null;
	private InputStreamReader is = null;
	private BufferedReader br = null;
	private String lineData = null;
	
	/**
	 * �����ļ�
	 * @param fileName
	 * @throws Exception
	 */
	public void readFile(String fileName) throws Exception {
		fis = new FileInputStream(fileName);
		is = new InputStreamReader(fis);
		br = new BufferedReader(is);
	}
	
	public void readFile(File file) throws Exception {
		fis = new FileInputStream(file);
		is = new InputStreamReader(fis);
		br = new BufferedReader(is);
	}
	
	/**
	 * �������ݣ��ж��Ƿ����
	 * @return
	 * @throws Exception
	 */
	public boolean isEOF() throws Exception {
		if (br == null) throw new Exception("�����ȵ���readFile����");
		lineData =  br.readLine();
		//�������У���ֱ�ӹر��ļ�
		if (lineData == null) closeFile();
		return lineData == null;
	}
	/**
	 * ������
	 * @return
	 * @throws Exception
	 */
	public String readLineData() throws Exception {
		return lineData;
	}
	
	/**
	 * �ر��ļ�
	 * @throws Exception
	 */
	public void closeFile() throws Exception {	
		if (br != null) {
			br.close();
			br = null;
		}
		if (is != null) {
			is.close();
			is = null;
		}
		if (fis != null) {
			fis.close();
			fis = null;
		}
	}
	
	
	/*FileWriter fw = new FileWriter("hello.txt"); 
����String s = "hello world"; 
����fw.write(s,0,s.length()); 
����fw.flush(); 

����OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("hello2.txt")); 
����osw.write(s,0,s.length()); 
����osw.flush(); 

����PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("hello3.txt")),true); 
����pw.println(s); */
	
	
	
		
}