package com.kingdee.eas.myframework.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.netctrl.IMutexServiceControl;
import com.kingdee.eas.base.netctrl.MutexServiceControlFactory;
import com.kingdee.eas.base.netctrl.ObjectUpdateLock;
import com.kingdee.eas.base.permission.IUser;
import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.framework.client.mutex.DataObjectMutex;
import com.kingdee.eas.framework.client.mutex.IDataObjectMutex;
import com.kingdee.eas.myframework.client.MsgBoxEx;
import com.kingdee.util.NumericExceptionSubItem;

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
	
	public static ObjectUpdateLock getObjectUpdateLock(String objId) {
		IMutexServiceControl iMutexSC = MutexServiceControlFactory.getRemoteInstance();
		String dcName = SysContext.getSysContext().getDcName();
		Map updateLocks = Collections.synchronizedMap(iMutexSC.getObjIDForUpdateList());
		if(updateLocks == null) return null;
		synchronized(updateLocks) {
			Iterator iterator = updateLocks.entrySet().iterator();
			do {
				if (!iterator.hasNext()) break;
				Entry entry = (Entry)iterator.next();
				String key = (String)entry.getKey();
				ObjectUpdateLock lock = (ObjectUpdateLock)entry.getValue();
				if(dcName == null || dcName.trim().length() == 0 || lock.getDcName() != null 
						&& lock.toString().length() > 0 && lock.getDcName().trim().equals(dcName)) {
					String id = key.substring(0, key.length() - dcName.length());
					if (PublicUtils.equals(id, objId)) {
						return lock;
					}
				}
				
			} while (true);
			
		}
		return null;
	}
	public static UserInfo getLockUserInfo(Context ctx, String objId) throws Exception {
		ObjectUpdateLock lock = getObjectUpdateLock(objId);
		if (lock == null || PublicUtils.equals(lock.getContextID(), SysContext.getSysContext().getSessionID())) return null;
		IUser user = null;
		if (ctx == null) user = UserFactory.getRemoteInstance();
		else user = UserFactory.getLocalInstance(ctx);
		String userName = lock.getUserName();
		UserInfo userInfo = new UserInfo();
		if (BOSUuid.isValidLength(userName)) {
			userInfo = user.getUserInfo(new ObjectUuidPK(lock.getUserName()));
		} else {
			userInfo.setName(userName);
		}
		
		return userInfo;
	}
	
	public static void throwsLockBillException(Context ctx, String objId) throws Exception {
		UserInfo lockUserInfo = MutexUtils.getLockUserInfo(ctx, objId);
		if (lockUserInfo != null) {
			throw new EASBizException(new NumericExceptionSubItem("",String.format("用户[%s]正在编辑此单据,您现不能编辑,请稍后再试!", lockUserInfo.getName())));
		}
	}
}
