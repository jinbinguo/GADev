/*
 * @(#)IExtCustomF7.java 
 * �����������������޹�˾��Ȩ����.
 * 
 * �޸ļ�¼��
 * -------------------------
 * �޸�ʱ�䣺  �޸��ˣ��޸�����
 * -------------------------
 * 
 * 
 */

package com.kingdee.eas.rpts.ctrlsqldesign.param;

import java.util.HashMap;

/**
 * ��������չ��������Դ��java���붨�Ƶ�F7�ӿڣ����ڴ���������Ķ�ѡ����ѡ���ƵȲ�����Ϣ
 * <p>
 * ��ϸ������
 * <p>
 * 
 * @author junwu_qin
 * @version 1.0 2009-6-24
 */
public interface IExtCustomF7
{
	/**
	 * ��������Դ���ʱ�����붨�Ƶ�F7�Ƿ���Զ�ѡ�����ƵĶ�ѡ��������Ϣ<br>
	 * map.put("allowMultipleSelected", "true"), true:�����ѡ,false:������<br>
	 * map.put("limit", "10") //��ѡ����
	 */
	public void setCustomMap(HashMap map);
}
