package com.kingdee.eas.myframework.client;

import java.awt.Component;

import com.kingdee.eas.base.uiframe.client.DlgShowListener;
import com.kingdee.eas.myframework.vo.MsgTableInfo;
import com.kingdee.eas.util.client.MsgBox;
import com.kingdee.eas.util.client.MsgBoxStatisticsUtil;

public class MsgBoxEx {
	

	public static boolean isYes(int choice) {
		return MsgBox.isYes(choice);
	}

	public static boolean isNo(int choice) {
		return MsgBox.isNo(choice);
	}

	public static boolean isCancel(int choice) {
		return MsgBox.isCancel(choice);
	}

	public static boolean isOk(int choice) {
		return MsgBox.isOk(choice);
	}

	public static void showInfo(String info) {
		showInfo(null, info);
	}

	public static void showInfo(Component comp, String info) {
		MsgBox.showInfo(comp, info);
	}

	public static void showWarning(Component comp, String warning) {
		MsgBox.showWarning(comp,warning);
	}

	public static void showWarning(String warning) {
		showWarning(null, warning);
	}

	public static void showError(String error) {
		showError((Component) null, error);
	}

	public static void showError(Component comp, String error)  {
	      MsgBox.showError(comp,error);
	}

	public static void showError(String error, String errorDetail) {
		showError((Component) null, error, errorDetail);
	}

	public static void showError(Component comp, String error, String errorDetail) {
	    MsgBox.showError(comp, error, errorDetail);
	}

	public static void showDetailAndOK(Component comp, String error, String errorDetail, int msgType) {
		MsgBox.showDetailAndOK(comp, error, errorDetail, msgType);
	}

	public static int showConfirm2(String msg) {
		return showConfirm2(null, msg);
	}

	public static int showConfirm2(Component comp, String msg) {
		return MsgBox.showConfirm2(comp, msg);
	}

	public static int showConfirm2New(Component comp, String msg) {
		return MsgBox.showConfirm2New(comp, msg);
	}

	public static int showConfirm3(String msg) {
		return showConfirm3(null, msg);
	}

	public static int showConfirm3(Component comp, String msg) {
		return MsgBox.showConfirm3(comp, msg);
	}

	public static int showConfirm3a(String msg, String detail) {
		return showConfirm3a(null, msg, detail);
	}

	public static int showConfirm3a(Component comp, String msg, String detail) {
		return MsgBox.showConfirm3a(comp, msg, detail);
	}

	public static int showConfirm3(Component comp, String msg, String detail) {
		return MsgBox.showConfirm3(comp, msg, detail);
	}

	public static int showConfirm4a(String msg, String detail) {
		return showConfirm4a(null, msg, detail);
	}

	public static int showConfirm4a(Component comp, String msg, String detail) {
		return MsgBox.showConfirm4a(comp,msg,detail);
	}

	public static void showConnectionError() {
		showConnectionError(null, null);
	}

	public static void showConnectionError(String msg) {
		showConnectionError(null, msg);
	}

	public static void showConnectionError(Component comp) {
		showConnectionError(comp, null);
	}

	public static void showConnectionError(Component comp, String msg) {
		MsgBox.showConnectionError(comp, msg);
	}
	
	public static int showConfirmTable(Component comp, String msg, MsgTableInfo msgTblInfo) {		
		 MsgBoxStatisticsUtil.send(comp, msg, 5);
	     AdvMsgBoxEx msgBox = AdvMsgBoxEx.createAdvMsgBox(comp,"提示信息",msg,msgTblInfo, 3);
	     DlgShowListener listener = new DlgShowListener(msgBox);
	     msgBox.addWindowListener(listener);
	     msgBox.show();
	     return msgBox.getResult();
	}
	
	public static void showInfoTable(Component comp, String msg, MsgTableInfo msgTblInfo) {
		MsgBoxStatisticsUtil.send(comp, msg, 5);
	     AdvMsgBoxEx msgBox = AdvMsgBoxEx.createAdvMsgBox(comp,"提示信息",msg,msgTblInfo,1);
	     
	     DlgShowListener listener = new DlgShowListener(msgBox);
	     msgBox.addWindowListener(listener);
	     msgBox.show();
	}
}
