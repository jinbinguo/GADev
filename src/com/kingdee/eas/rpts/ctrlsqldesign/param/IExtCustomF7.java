/*
 * @(#)IExtCustomF7.java 
 * 金蝶国际软件集团有限公司版权所有.
 * 
 * 修改记录：
 * -------------------------
 * 修改时间：  修改人：修改内容
 * -------------------------
 * 
 * 
 */

package com.kingdee.eas.rpts.ctrlsqldesign.param;

import java.util.HashMap;

/**
 * 简述：扩展报表数据源，java代码定制的F7接口，用于传入设计器的多选、多选限制等参数信息
 * <p>
 * 详细描述：
 * <p>
 * 
 * @author junwu_qin
 * @version 1.0 2009-6-24
 */
public interface IExtCustomF7
{
	/**
	 * 缓存数据源设计时，代码定制的F7是否可以多选、限制的多选数量等信息<br>
	 * map.put("allowMultipleSelected", "true"), true:允许多选,false:不允许<br>
	 * map.put("limit", "10") //多选数量
	 */
	public void setCustomMap(HashMap map);
}
