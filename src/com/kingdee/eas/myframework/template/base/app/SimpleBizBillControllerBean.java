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
    	setNumberFromCodingRule(ctx, model); //若无编码，且启用编码规则，则获取编码
    	IObjectPK pk = super._addnew(ctx, model);
    	String number= model.getString("number");
    	
   	 	if (!StringUtils.isEmpty(number))
   	 		_checkNumberDup(ctx, pk, model); //检查编码是否重复
    	return pk;
    }
   
    
    /**
     * 检查是否编码重复
     */
    protected boolean _checkNumberDup(Context ctx, IObjectPK pk, IObjectValue model) throws EASBizException, BOSException {
    	try {
    		return super._checkNumberDup(ctx, pk, model);
    		
    	} catch (BaseException e) {
    		OrgUnitInfo mainOrgInfo = getMainBizOrgUnit(ctx, (CoreBillBaseInfo) model);
    		String orgId = (mainOrgInfo==null) ? null : mainOrgInfo.getString("id");
    		//若在编码规则为“不允许断号”，取得到已存在的编码时，需重新取号，直到取得可用编号，当重复取号的次数>20时，停止取号
    		if (!CodingRuleUtils.isUseIntermitNumber(ctx,(CoreBaseInfo) model,orgId)) {
    			throw new EASBizException(new NumericExceptionSubItem("",e.getMessage()));
    		}
    		boolean isCheckNumberDup=false;
    		int maxCheckCount = 20;
    		int currentCheckCount = 0;
    		do {
    			try {
    				model.put("number", null);
    				setNumberFromCodingRule(ctx, model); //重新取号
    				currentCheckCount++;
    				isCheckNumberDup = super._checkNumberDup(ctx, pk, model);
    			} catch (Exception ee) {
    				if (currentCheckCount==maxCheckCount)
    					throw new EASBizException(new NumericExceptionSubItem("","超过20次自动获取编码，自动退出取数，请检查编码规则后再做调整！"));
    			}
			} while (!isCheckNumberDup);   
    		_save(ctx,model); //取号成功后，重新保存数据
    		return true;
    	}
    }
    
    /**
     * 审核单据
     */
    @Override
    protected void _audit(Context ctx, IObjectPK pk) throws BOSException,
    		EASBizException {
    	IObjectValue vo = getValue(ctx, pk);
    	if (!vo.get("baseStatus").equals(BillBaseStatusEnum.SUBMITED_VALUE)) {
    		throw new EASBizException(new NumericExceptionSubItem("","未提交,不能审核"));
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
	    		throw new EASBizException(new NumericExceptionSubItem("","调用方法batchExecuteWithTrans(methodName,paramEntries)参数methodName不能为空!"));
	    	}
	    	if(paramEntries == null || paramEntries.length == 0) {
	    		throw new EASBizException(new NumericExceptionSubItem("","调用方法batchExecuteWithTrans(methodName,paramEntries)参数paramEntries不能为空!"));
	    	}
	    	Class[] paramTypes = paramEntries[0].getParamTypes();
	    	if(paramTypes == null || paramTypes.length == 0) {
	    		throw new EASBizException(new NumericExceptionSubItem("","调用方法batchExecuteWithTrans(methodName,paramEntries)中参数paramEntries的paramTypes不能为空!"));
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
     * 审核成功后的动作
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
     * 反审核成功后的动作
     * @param ctx
     * @param pk
     * @param model
     * @throws EASBizException
     * @throws BOSException
     */
    public void unAuditAfter(Context ctx, IObjectPK pk, IObjectValue model) throws EASBizException, BOSException {

    }
    
    //TODO 未实现 
    @Override
    protected void _batchAudit(Context ctx, IObjectPK[] arrayPK)
    		throws BOSException, EASBizException {
    	throw new EASBizException(new NumericExceptionSubItem("","未实现"));
    }
    //TODO 未实现 
    @Override
    protected void _batchUnAudit(Context ctx, IObjectPK[] arrayPK)
    		throws BOSException, EASBizException {
    	throw new EASBizException(new NumericExceptionSubItem("","未实现"));
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
     * 当前登录用户
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
	 * 根据编码规则获取编码
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
     * 获取主业务组织，若无主业组织字段，则按当前业务单据的业务组织类型的取组织
     */
    protected OrgUnitInfo getMainBizOrgUnit(Context ctx, CoreBillBaseInfo model) {
    	if(model.getBizOrgPropertyName() != null)
    		return (OrgUnitInfo)model.get(model.getBizOrgPropertyName());
    	else return (OrgUnitInfo)ctx.get(getMainBizOrgType(ctx,model));
    }
    /**
     * 业务单据的业务组织类型
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
    	CodingRuleUtils.recycleNumber(ctx, model, orgId, number); //启用不允许断号时，对编码的回收
    }
    


}