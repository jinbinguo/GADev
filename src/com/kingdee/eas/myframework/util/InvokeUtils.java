package com.kingdee.eas.myframework.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.util.NumericExceptionSubItem;

public class InvokeUtils implements Serializable{
	
	
	public static void setFieldValue(Object owner, String fieldName, Object value) throws Exception {
		Field field = getField(owner, fieldName);
		field.set(owner, value);
	}
	
	public static Object getFieldValue(Object owner, String fieldName) throws Exception {
		Field field = getField(owner, fieldName);
		return field.get(owner);
	}
	
	private static Field getField(Object owner,String fieldName) throws EASBizException,BOSException {
		Class cls = owner.getClass();
		Field field = null;
		while (field == null && cls != null) {
			try {
				field = cls.getDeclaredField(fieldName);
			} catch (Exception e) {
				
			}
			cls = cls.getSuperclass();
		}
		if (field == null) {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("对象%s不存在属性%s",owner.getClass().getName(),fieldName)));
		}
		field.setAccessible(true);
		return field;

	}
	
	
	/*
	public static Method getMethod(Context ctx, Object owner, String methodName, BatchExecuteParamsEntry param) throws EASBizException,BOSException {
		Class[] paramTypes = param.getParamTypes();
		return getMethod(ctx, owner, methodName, paramTypes);
	}
	
	
	public static Method getMethod(Object owner, String methodName, BatchExecuteParamsEntry param) throws EASBizException,BOSException {
		return getMethod(null, owner, methodName, param);
	}
	*/
	public static Method getMethod(Context ctx, Object owner, String methodName, Class[] paramTypes) throws EASBizException,BOSException {	
		Class[] newParamTypes = autoAddCtxInParamTypes(ctx, paramTypes);
		return getMethod(owner, methodName, newParamTypes);
	}
	/**
	 *  待研究方法执行时如何跳过
	 * @param owner
	 * @param methodName
	 * @param paramTypes
	 * @param clsExec 执行的类
	 * @return
	 * @throws EASBizException
	 * @throws BOSException
	
	public static Method getMethod(Object owner, String methodName, Class[] paramTypes, Class clsExec) throws EASBizException,BOSException {
		Method method = null;
		Class cls = owner.getClass();
		while (method == null && cls != null) {
			try {
				if (cls.getName().equals(clsExec.getName()))
						method = cls.getDeclaredMethod(methodName, paramTypes);
			} catch (Exception e) {}
			cls = cls.getSuperclass();
		}
		if (method == null) {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("执行对象%s不存在方法%s(%s)",clsExec.getName(),methodName,paramTypes.toString())));
		}
		method.setAccessible(true);
		return method;
	}
	 */
	public static Method getMethod(Object owner, String methodName, Class[] paramTypes) throws EASBizException,BOSException {
		Method method = null;
		Class cls = owner.getClass();
		while (method == null && cls != null) {
			try {
				method = cls.getDeclaredMethod(methodName, paramTypes);
			} catch (Exception e) {}
			cls = cls.getSuperclass();
		}
		if (method == null) {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("对象%s不存在方法%s(%s)",owner.getClass().getName(),methodName,paramTypes.toString())));
		}
		method.setAccessible(true);
		return method;
	}
	
	/*public static Object invokeMethod(Context ctx, Object owner, String methodName, BatchExecuteParamsEntry param) throws EASBizException,BOSException {
		Method method = getMethod(ctx,owner, methodName, param);
		return invokeMethod(ctx,owner, method, param);
	}
	public static Object invokeMethod(Object owner, String methodName, BatchExecuteParamsEntry param) throws EASBizException,BOSException {
		Method method = getMethod(owner, methodName, param);
		return invokeMethod(owner, method, param);
	}
	
	public static Object invokeMethod(Context ctx, Object owner, Method method, BatchExecuteParamsEntry param) throws EASBizException,BOSException {
		Object[] paramValues = autoAddCtxInParamValues(ctx, param.toArray());		
		return invokeMethod(owner, method, paramValues);
	}
	
	public static Object invokeMethod(Object owner, Method method, BatchExecuteParamsEntry param) throws EASBizException,BOSException {
		Object[] paramValues = param.toArray();
		return invokeMethod(owner, method, paramValues);
	}*/
			
	public static Object invokeMethod(Context ctx,Object owner, Method method, Object[] paramValues) throws EASBizException,BOSException {
		Object[] newParamValues = autoAddCtxInParamValues(ctx, paramValues);
		return invokeMethod(owner, method, newParamValues);
	}
	
	public static Object invokeMethod(Object owner, Method method, Object[] paramValues) throws EASBizException,BOSException {
		Object returnValues = null;
		if (method == null) throw new EASBizException(new NumericExceptionSubItem("","方法不能为空！"));
		try {
			returnValues = method.invoke(owner, paramValues);
		} catch (Exception e) {
			throw new EASBizException(new NumericExceptionSubItem("",e.getMessage()));
		}
		return returnValues;
	}
	
	public static Object invokeMethod(Object owner, String methodName, Class[] paramTypes, Object[] paramValues) throws EASBizException,BOSException {
		Method method = getMethod(owner, methodName, paramTypes);
		return invokeMethod(owner, method, paramValues);
	}
	

	
	private static Class[] autoAddCtxInParamTypes(Context ctx, Class[] paramTypes) {
		if (ctx == null) return paramTypes;
		if (paramTypes == null) paramTypes = new Class[0];
		Class[] newTypes = new Class[paramTypes.length+1];
		newTypes[0] = ctx.getClass();
		System.arraycopy(paramTypes, 0, newTypes, 1, paramTypes.length);
		return newTypes;
		
	}
	
	private static Object[] autoAddCtxInParamValues(Context ctx, Object[] paramValues) {
		if (ctx == null) return paramValues;
		if (paramValues == null) paramValues = new Class[0];
		Object[] newParamValues = new Object[paramValues.length+1];
		newParamValues[0] = ctx;
		System.arraycopy(paramValues, 0, newParamValues, 1, paramValues.length);
		return newParamValues;
	}
}
