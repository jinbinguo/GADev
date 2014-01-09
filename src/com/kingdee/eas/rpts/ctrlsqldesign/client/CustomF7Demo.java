package com.kingdee.eas.rpts.ctrlsqldesign.client;

import java.awt.Dialog;
import java.awt.Frame;
import java.util.HashMap;

import com.kingdee.eas.basedata.org.OrgViewType;
import com.kingdee.eas.basedata.org.client.f7.OrgF7;
import com.kingdee.eas.rpts.ctrlsqldesign.param.IExtCustomF7;
import com.kingdee.eas.util.client.MsgBox;

/**
 * ���������붨��F7demo,��Ҫʵ��IExtCustomF7
 * <p>
 * ��ϸ������
 * <p>
 * 
 * @author junwu_qin
 * @version 1.0 2009-5-27
 */
public class CustomF7Demo extends OrgF7 implements IExtCustomF7
{
	private static final long serialVersionUID = 1L;

	HashMap customMap = new HashMap();

	boolean isMultipleSelected = false;
	String limit = "10"; // ���ƵĶ�ѡ����

	public CustomF7Demo()
	{
		super();
	}

	public CustomF7Demo(Frame owner)
	{
		super(owner);
	}

	public CustomF7Demo(Dialog owner)
	{
		super(owner);
	}

	/**
	 * ��������Դ���ʱ�����붨�Ƶ�F7�Ƿ���Զ�ѡ�����ƵĶ�ѡ��������Ϣ
	 * 
	 * @see com.kingdee.eas.rpts.ctrlsqldesign.param.IExtCustomF7#setCustomMap(java.util.HashMap)
	 */
	public void setCustomMap(HashMap map)
	{
		customMap = map;
		
		orgViewTypes = new OrgViewType[] {OrgViewType.COSTCENTER};//ָ����֯����
	}

	// showǰ���ö�ѡ���ߵ�ѡ
	public void show()
	{
		Object obj = customMap.get("allowMultipleSelected");
		if (null != obj)
		{
			isMultipleSelected = new Boolean((String) obj).booleanValue();
		}
		this.setMultiSelect(isMultipleSelected); // ���뱣֤�����ö�ѡ��ѡ�ķ���

		obj = customMap.get("limit");
		if (null != obj)
		{
			limit = (String) obj;
		}

		super.show();
	}

	/**
	 * @see com.kingdee.eas.basedata.org.client.OrgF7PromptDialog#isCanceled()
	 */
	public boolean isCanceled()
	{
		Object object = super.getData();
		if (object instanceof Object[])
		{
			// �ж��Ƿ񳬹���ѡ
			if (isMultipleSelected)
			{
				Object[] returnObjes = (Object[]) object;
				int length = returnObjes.length;
				if (length > Integer.parseInt(this.limit))
				{
					// ��ʾ��������
					MsgBox.showInfo("ֻ��ѡ��" + limit + "��");
					return true;
				}
			}
		}

		return super.isCanceled();
	}

	public Object getData()
	{
		Object object = super.getData();
		if (object instanceof Object[])
		{
			// �ж��Ƿ񳬹���ѡ
			if (isMultipleSelected)
			{
				Object[] returnObjes = (Object[]) object;
				int length = returnObjes.length;
				if (length > Integer.parseInt(this.limit))
				{
					// ��ʾ��������
					MsgBox.showInfo("ֻ��ѡ��" + limit + "��");
					return null;
				}
			}
		}
		return object;
	}
}
