package com.kingdee.eas.rpts.ctrlsqldesign.client;

import java.awt.Dialog;
import java.awt.Frame;
import java.util.HashMap;

import com.kingdee.eas.basedata.org.OrgViewType;
import com.kingdee.eas.basedata.org.client.f7.OrgF7;
import com.kingdee.eas.rpts.ctrlsqldesign.param.IExtCustomF7;
import com.kingdee.eas.util.client.MsgBox;

/**
 * 简述：代码定制F7demo,需要实现IExtCustomF7
 * <p>
 * 详细描述：
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
	String limit = "10"; // 限制的多选行数

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
	 * 缓存数据源设计时，代码定制的F7是否可以多选、限制的多选数量等信息
	 * 
	 * @see com.kingdee.eas.rpts.ctrlsqldesign.param.IExtCustomF7#setCustomMap(java.util.HashMap)
	 */
	public void setCustomMap(HashMap map)
	{
		customMap = map;
		
		orgViewTypes = new OrgViewType[] {OrgViewType.COSTCENTER};//指定组织类型
	}

	// show前设置多选或者单选
	public void show()
	{
		Object obj = customMap.get("allowMultipleSelected");
		if (null != obj)
		{
			isMultipleSelected = new Boolean((String) obj).booleanValue();
		}
		this.setMultiSelect(isMultipleSelected); // 必须保证有设置多选单选的方法

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
			// 判断是否超过多选
			if (isMultipleSelected)
			{
				Object[] returnObjes = (Object[]) object;
				int length = returnObjes.length;
				if (length > Integer.parseInt(this.limit))
				{
					// 提示超过限制
					MsgBox.showInfo("只能选择" + limit + "行");
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
			// 判断是否超过多选
			if (isMultipleSelected)
			{
				Object[] returnObjes = (Object[]) object;
				int length = returnObjes.length;
				if (length > Integer.parseInt(this.limit))
				{
					// 提示超过限制
					MsgBox.showInfo("只能选择" + limit + "行");
					return null;
				}
			}
		}
		return object;
	}
}
