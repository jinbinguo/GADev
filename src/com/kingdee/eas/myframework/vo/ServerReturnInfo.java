package com.kingdee.eas.myframework.vo;

import java.io.Serializable;

public class ServerReturnInfo implements Serializable{
	
	public boolean isSuccess=true;
	public boolean isExption=true;
	public StringBuilder returnMsg = new StringBuilder();
	public StringBuilder exptionMsg = new StringBuilder();
	public StringBuilder spentMsg = new StringBuilder();
	
	public boolean isSuccess() {
		return !isExption && isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public boolean isExption() {
		return isExption;
	}
	public void setExption(boolean isExption) {
		this.isExption = isExption;
	}
	public String getReturnMsg() {
		return returnMsg.toString();
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg.replace(0, this.returnMsg.length(), "");
		this.returnMsg.append(returnMsg);
	}
	
	public void appendReturnMsg(String msg) {
		returnMsg.append(msg);
	}
	
	public void addReturnMsg(String msg) {
		returnMsg.append(msg+"\n\r");
	}

	public String getExptionMsg() {
		return exptionMsg.toString();
	}
	public void setExptionMsg(String exptionMsg) {
		this.exptionMsg.replace(0, this.exptionMsg.length(), "");
		this.exptionMsg.append(exptionMsg);
	}
	public void appendExptionMsg(String msg) {
		exptionMsg.append(msg);
	}
	
	public void addExptionMsg(String msg) {
		exptionMsg.append(msg+"\n\r");
	}
	
	public String getSpentMsg() {
		return spentMsg.toString();
	}
	public void setSpentMsg(String spentMsg) {
		this.spentMsg.replace(0, this.spentMsg.length(), "");
		this.spentMsg.append(spentMsg);
	}
	
	public void appendSpentMsg(String msg) {
		spentMsg.append(msg);
	}
	public void addSpentMsg(String msg) {
		spentMsg.append(msg+"\n\r");
	}
	
}
