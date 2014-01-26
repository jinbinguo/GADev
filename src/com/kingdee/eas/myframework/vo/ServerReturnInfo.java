package com.kingdee.eas.myframework.vo;

import java.io.Serializable;

public class ServerReturnInfo implements Serializable{
	
	private boolean isSuccess=true;
	private boolean isException=false;
	private StringBuilder returnMsg = new StringBuilder();
	private StringBuilder exceptionMsg = new StringBuilder();
	private StringBuilder spentMsg = new StringBuilder();
	private static final String CR = "\n\r";
	
	
	public boolean isSuccess() {
		return !isException && isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public boolean isException() {
		return isException;
	}

	public void clearReturnMsg() {
		returnMsg.replace(0, this.returnMsg.length(), "");
	}
	public void clearExceptionMsg() {
		isException = false;
		exceptionMsg.replace(0, this.exceptionMsg.length(), "");
		
	}
	public void clearSpentMsg() {
		spentMsg.replace(0, this.spentMsg.length(), "");
	}
	
	public String getReturnMsg() {
		return returnMsg.toString();
	}
	public String getExceptionMsg() {
		return exceptionMsg.toString();
	}
	public String getSpentMsg() {
		return spentMsg.toString();
	}
	
	public void setReturnMsg(String returnMsg) {
		clearReturnMsg();
		this.returnMsg.append(returnMsg);
	}
	public void setExceptionMsg(String exceptionMsg) {
		clearExceptionMsg();
		isException = true;
		this.exceptionMsg.append(exceptionMsg);
	}
	public void setSpentMsg(String spentMsg) {
		clearSpentMsg();
		this.spentMsg.append(spentMsg);
	}
	
	
	public void appendReturnMsg(String msg) {
		returnMsg.append(msg);
	}
	public void appendExceptionMsg(String msg) {
		isException = true;
		exceptionMsg.append(msg);
	}
	public void appendSpentMsg(String msg) {
		spentMsg.append(msg);
	}
	
	
	public void addReturnMsg(String msg) {
		returnMsg.append(msg).append(CR);
	}
	public void addExceptionMsg(String msg) {
		isException = true;
		exceptionMsg.append(msg).append(CR);
	}
	public void addSpentMsg(String msg,long startTime) {
		spentMsg.append("Ö´ÐÐ").append(msg).append("ºÄÊ±(ms):").append(System.currentTimeMillis()-startTime).append(CR);
	}
	
}
