package com.kingdee.eas.myframework.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.kingdee.util.StringUtils;

/**
 * �������Collection
 * @author guo
 *
 */
public class BaseCollectionWithSort implements Serializable {

	private Vector<String> orders = new Vector<String>();
	private HashMap<String,Object> values = new HashMap<String,Object>();
	
	public int size() {
		return orders.size();
	}
	public void addObject(String key, Object value) throws Exception {
		if (StringUtils.isEmpty(key)) {
			throw new Exception("addObject(key,value)key����Ϊ�ջ���ַ�");
		}
		if (value == null) {
			throw new Exception("addObject(key,value)value����Ϊ��");
		}
		orders.add(key);
		values.put(key, value);
	}
	public void addObject(int index, String key, Object value) throws Exception {
		if (index < 0) {
			throw new Exception("addObject(index,key,value)index����С��0��");
		}
		if (StringUtils.isEmpty(key)) {
			throw new Exception("addObject(index,key,value)key����Ϊ�ջ���ַ���");
		}
		if (value == null) {
			throw new Exception("addObject(index,key,value)value����Ϊ�գ�");
		}
		orders.add(index, key);
		values.put(key, value);
	}
	
	public void removeObject(int index) throws Exception { 
		if (index >= size()) {
			throw new Exception("removeObject(index)index����������ţ�");
		}
		String key = orders.get(index);
		removeObject(key);
	}
	public void removeObject(String key) throws Exception {
		if (StringUtils.isEmpty(key)) {
			throw new Exception("removeObject(key)key����Ϊ�ջ���ַ���");
		}
		boolean b = orders.remove(key);
		if (b == false) {
			throw new Exception(String.format("�Ƴ�key=[%s]ֵ����",key));
		}
		values.remove(key);
	}
	
	public Object getObject(String key) {
		return values.get(key);
	}
	public Object getObject(int index) throws Exception {
		if (index >= size()) {
			throw new Exception("removeObject(index)index����������ţ�");
		} 
		String key = orders.get(index);
		return getObject(key);
	}
	
	public boolean isEmpty() {
		return values.isEmpty();
	}
	
	public boolean isExists(String key) {
		return values.get(key) != null;
	}
}
