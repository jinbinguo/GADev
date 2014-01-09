package com.kingdee.eas.myframework.template.base.app;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.framework.batch.BatchExecuteParamsEntry;
import com.kingdee.bos.framework.batch.BatchExecuteResult;
import com.kingdee.bos.metadata.entity.EntityObjectInfo;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBillBaseInfo;
import com.kingdee.eas.myframework.util.CodingRuleUtils;
import com.kingdee.eas.scm.common.BillBaseStatusEnum;
import com.kingdee.eas.scm.common.EntryBaseStatusEnum;
import com.kingdee.util.BaseException;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

public class SimpleBizBillControllerBean extends AbstractSimpleBizBillControllerBean {
    
	private static Logger logger = Logger.getLogger("com.kingdee.eas.myframework.template.base.app.SimpleBizBillControllerBean");
    


    @Override
    protected IObjectPK _addnew(Context ctx, IObjectValue model) throws BOSException ,EASBizException {
    	setNumberFromCodingRule(ctx, model); //���ޱ��룬�����ñ���������ȡ����
    	IObjectPK pk = super._addnew(ctx, model);
    	String number= model.getString("number");
    	
   	 	if (!StringUtils.isEmpty(number))
   	 		_checkNumberDup(ctx, pk, model); //�������Ƿ��ظ�
    	return pk;
    }
   
    
    /**
     * ����Ƿ�����ظ�
     */
    protected boolean _checkNumberDup(Context ctx, IObjectPK pk, IObjectValue model) throws EASBizException, BOSException {
    	try {
    		return super._checkNumberDup(ctx, pk, model);
    		
    	} catch (BaseException e) {
    		OrgUnitInfo mainOrgInfo = getMainBizOrgUnit(ctx, (CoreBillBaseInfo) model);
    		String orgId = (mainOrgInfo==null) ? null : mainOrgInfo.getString("id");
    		//���ڱ������Ϊ��������Ϻš���ȡ�õ��Ѵ��ڵı���ʱ��������ȡ�ţ�ֱ��ȡ�ÿ��ñ�ţ����ظ�ȡ�ŵĴ���>20ʱ��ֹͣȡ��
    		if (!CodingRuleUtils.isUseIntermitNumber(ctx,(CoreBaseInfo) model,orgId)) {
    			throw new EASBizException(new NumericExceptionSubItem("",e.getMessage()));
    		}
    		boolean isCheckNumberDup=false;
    		int maxCheckCount = 20;
    		int currentCheckCount = 0;
    		do {
    			try {
    				model.put("number", null);
    				setNumberFromCodingRule(ctx, model); //����ȡ��
    				currentCheckCount++;
    				isCheckNumberDup = super._checkNumberDup(ctx, pk, model);
    			} catch (Exception ee) {
    				if (currentCheckCount==maxCheckCount)
    					throw new EASBizException(new NumericExceptionSubItem("","����20���Զ���ȡ���룬�Զ��˳�ȡ�������������������������"));
    			}
			} while (!isCheckNumberDup);   
    		_save(ctx,model); //ȡ�ųɹ������±�������
    		return true;
    	}
    }
    
    /**
     * ��˵���
     */
    @Override
    protected void _audit(Context ctx, IObjectPK pk) throws BOSException,
    		EASBizException {
    	IObjectValue vo = getValue(ctx, pk);
    	if (!vo.get("baseStatus").equals(BillBaseStatusEnum.SUBMITED_VALUE)) {
    		throw new EASBizException(new NumericExceptionSubItem("","δ�ύ,�������"));
    	}
    	vo.put("baseStatus", BillBaseStatusEnum.AUDITED);
    	vo.put("auditor", getUserInfo(ctx));
    	vo.put("auditTime", getTime());

		IObjectCollection entryCol = (IObjectCollection) vo.get("entrys");
		for (int i = 0; entryCol != null && i < entryCol.size(); i++) {
			IObjectValue entryInfo = entryCol.getObject(i);
			entryInfo.put("lineStatus", EntryBaseStatusEnum.AUDITED);
		}
    	_update(ctx, pk, vo);
    	
    	auditAfter(ctx, pk, vo);
    }
    @Override
    public BatchExecuteResult batchExecute(Context ctx, String methodName, Class[] paramTypes, BatchExecuteParamsEntry[] paramEntries) throws BaseException {
    	return  super.batchExecute(ctx, methodName, paramTypes, paramEntries);

    }
    
    @Override
    protected BatchExecuteResult _batchExecuteWithTrans(Context ctx, String methodName, BatchExecuteParamsEntry[] paramEntries) throws BOSException, EASBizException {
    	try {
	    	if (StringUtils.isEmpty(methodName)) {
	    		throw new EASBizException(new NumericExceptionSubItem("","���÷���batchExecuteWithTrans(methodName,paramEntries)����methodName����Ϊ��!"));
	    	}
	    	if(paramEntries == null || paramEntries.length == 0) {
	    		throw new EASBizException(new NumericExceptionSubItem("","���÷���batchExecuteWithTrans(methodName,paramEntries)����paramEntries����Ϊ��!"));
	    	}
	    	Class[] paramTypes = paramEntries[0].getParamTypes();
	    	if(paramTypes == null || paramTypes.length == 0) {
	    		throw new EASBizException(new NumericExceptionSubItem("","���÷���batchExecuteWithTrans(methodName,paramEntries)�в���paramEntries��paramTypes����Ϊ��!"));
	    	}
	    	
	    	return batchExecute(ctx, methodName, paramTypes, paramEntries);
    	} catch (Exception e) {
	    	try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    }
    	return null;
    	/*
    	
    	Method method = InvokeUtils.getMethod(ctx,this, methodName, paramTypes);
    	for (int i = 0; i < paramEntries.length; i++){
    		try {
    			InvokeUtils.invokeMethod(ctx, this, method, paramEntries[i]);
    		} catch (Exception e) {
    			setRollbackOnly();
    			throw new EASBizException(new NumericExceptionSubItem("",e.getMessage()));
    		}
    	}
    	
    	return super._batchExecuteWithTrans(ctx, methodName, paramEntries); */
    }
     
    
    /**
     * ��˳ɹ���Ķ���
     * @param ctx
     * @param pk
     * @param model
     * @throws EASBizException
     * @throws BOSException
     */
    public void auditAfter(Context ctx, IObjectPK pk, IObjectValue model)
    		throws EASBizException, BOSException {

    }
    @Override
    protected void _unAudit(Context ctx, IObjectPK pk) throws BOSException,
    		EASBizException {
    	IObjectValue vo = getValue(ctx, pk);
    	vo.put("baseStatus", BillBaseStatusEnum.TEMPORARILYSAVED);
    	vo.put("auditor", null);
    	vo.put("auditTime", null);
    	
    	IObjectCollection entryCol = (IObjectCollection) vo.get("entrys");
		for (int i = 0; entryCol != null && i < entryCol.size(); i++) {
			IObjectValue entryInfo = entryCol.getObject(i);
			entryInfo.put("lineStatus", EntryBaseStatusEnum.TEMPORARILYSAVED);
		}
    	_update(ctx, pk, vo);
    	unAuditAfter(ctx, pk, vo);
    }
    /**
     * ����˳ɹ���Ķ���
     * @param ctx
     * @param pk
     * @param model
     * @throws EASBizException
     * @throws BOSException
     */
    public void unAuditAfter(Context ctx, IObjectPK pk, IObjectValue model) throws EASBizException, BOSException {

    }
    
    //TODO δʵ�� 
    @Override
    protected void _batchAudit(Context ctx, IObjectPK[] arrayPK)
    		throws BOSException, EASBizException {
    	throw new EASBizException(new NumericExceptionSubItem("","δʵ��"));
    }
    //TODO δʵ�� 
    @Override
    protected void _batchUnAudit(Context ctx, IObjectPK[] arrayPK)
    		throws BOSException, EASBizException {
    	throw new EASBizException(new NumericExceptionSubItem("","δʵ��"));
    }

    protected IObjectPK _save(Context ctx, IObjectValue model)
    		throws BOSException, EASBizException {
    	model.put("baseStatus", BillBaseStatusEnum.TEMPORARILYSAVED);
		IObjectCollection entryCol = (IObjectCollection) model.get("entrys");
		for (int i = 0; entryCol != null && i < entryCol.size(); i++) {
			IObjectValue entryInfo = entryCol.getObject(i);
			entryInfo.put("lineStatus", EntryBaseStatusEnum.TEMPORARILYSAVED);
		}
    	return super._save(ctx, model);
    }
     
	@Override
	protected IObjectPK _submit(Context ctx, IObjectValue model)
			throws BOSException, EASBizException {	
		model.put("baseStatus", BillBaseStatusEnum.SUBMITED);
		IObjectCollection entryCol = (IObjectCollection) model.get("entrys");
		for (int i = 0; entryCol != null && i < entryCol.size(); i++) {
			IObjectValue entryInfo = entryCol.getObject(i);
			entryInfo.put("lineStatus", EntryBaseStatusEnum.SUBMITED);
		}
		return super._submit(ctx, model);
	}
    /**
     * ��ǰ��¼�û�
     * @param ctx
     * @return
     * @throws BOSException
     */
	public UserInfo getUserInfo(Context ctx) throws BOSException {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(BOSUuid.read(ctx.getCaller().toString()));
		return userInfo;
	}

	public Date getCurrentDate() {
		return new Date();
	}
	public Timestamp getTime() {
		Date date = new Date();
		long time = date.getTime();
		Calendar d = Calendar.getInstance();
		d.setTime(new Timestamp(time));
		d.set(14, 0);
		Timestamp createtime = new Timestamp(d.getTime().getTime());
		return createtime;
	}
	
	
	/**
	 * ���ݱ�������ȡ����
	 * @param ctx
	 * @param model
	 * @throws BOSException
	 * @throws EASBizException
	 */
    protected void setNumberFromCodingRule(Context ctx, IObjectValue model) throws BOSException, EASBizException {
		String number = (String) model.get("number");
    	if (!StringUtils.isEmpty(number)) return;
		OrgUnitInfo mainOrgInfo = getMainBizOrgUnit(ctx, (CoreBillBaseInfo) model);
		String orgId = (mainOrgInfo==null) ? null : mainOrgInfo.getString("id");
		number = CodingRuleUtils.getNumber(ctx, model, orgId);
		model.put("number", number);
  
	}
    
    /**
     * ��ȡ��ҵ����֯��������ҵ��֯�ֶΣ��򰴵�ǰҵ�񵥾ݵ�ҵ����֯���͵�ȡ��֯
     */
    protected OrgUnitInfo getMainBizOrgUnit(Context ctx, CoreBillBaseInfo model) {
    	if(model.getBizOrgPropertyName() != null)
    		return (OrgUnitInfo)model.get(model.getBizOrgPropertyName());
    	else return (OrgUnitInfo)ctx.get(getMainBizOrgType(ctx,model));
    }
    /**
     * ҵ�񵥾ݵ�ҵ����֯����
     * @param ctx
     * @param model
     * @return
     */
    public OrgType getMainBizOrgType(Context ctx, CoreBillBaseInfo model) {
    	 EntityObjectInfo entity = null;
    	 entity = getBOSEntity(ctx, model);
    	 String orgType = (String)entity.getExtendedProperties().get("OrgType");
    	 return OrgType.getEnum(orgType);
    }
    /**
     * 
     */
    @Override
    protected void _delete(Context ctx, IObjectPK pk) throws BOSException,
    		EASBizException {
    	IObjectValue model = getValue(ctx, pk);
    	
    	OrgUnitInfo mainOrgInfo = getMainBizOrgUnit(ctx,(CoreBillBaseInfo) model);
    	String orgId = null;
    	if (mainOrgInfo != null) orgId = mainOrgInfo.getString("id");
    	String number = model.getString("number");
    	super._delete(ctx, pk);
    	CodingRuleUtils.recycleNumber(ctx, model, orgId, number); //���ò�����Ϻ�ʱ���Ա���Ļ���
    }
    


}