package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.eas.base.netctrl.IMutexServiceControl;
import com.kingdee.eas.base.netctrl.MutexServiceControlFactory;
import com.kingdee.eas.framework.client.mutex.DataObjectMutex;
import com.kingdee.eas.framework.client.mutex.IDataObjectMutex;

public class MutexUtils implements Serializable {
	
	public static void requestLockForUpdate(String objId) throws Throwable {
		IMutexServiceControl mutexService = MutexServiceControlFactory.getRemoteInstance();
		mutexService.requestObjIDForUpdate(objId);
		
	}

	public static void checkDataObjectLock(String objId) throws Throwable {
		IDataObjectMutex mutex = new DataObjectMutex();
		mutex.requestDataObjectLock(objId);
	}
	
	public static void checkDataObjectLockWithExcepion(String objId) {
		try {
			checkDataObjectLock(objId);
		} catch (Throwable e) {
			UIUtils.handUIExceptionAndAbort(e);
		}
	}
	
	public static void checkDataObjectLock(IObjectValue dataObject) throws Throwable {
		IDataObjectMutex mutex = new DataObjectMutex();
		mutex.requestDataObjectLock(dataObject);
	}
	
	public static void checkDataObjectLockWithExcepion(IObjectValue dataObject) {
		try {
			checkDataObjectLock(dataObject);
		} catch (Throwable e) {
			UIUtils.handUIExceptionAndAbort(e);
		}
	}
	
	

	public static void releaseDataObjectLock(IObjectValue dataObject) {
		IDataObjectMutex mutex = new DataObjectMutex();
		mutex.releaseDataObjectLock(dataObject);
	}

	public static void releaseDataObjectLock(String objId) {
		IDataObjectMutex mutex = new DataObjectMutex();
		mutex.releaseDataObjectLock(objId);
	}

	public static void batchDataService(IObjectValue requestObject, IObjectValue repleaseObject) {
		IDataObjectMutex mutex = new DataObjectMutex();
		mutex.batchDataService(requestObject, repleaseObject);
	}
}
