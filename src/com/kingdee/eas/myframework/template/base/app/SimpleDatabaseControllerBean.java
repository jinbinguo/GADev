package com.kingdee.eas.myframework.template.base.app;

import org.apache.log4j.Logger;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.metadata.entity.EntityObjectInfo;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBillBaseInfo;
import com.kingdee.eas.myframework.util.CodingRuleUtils;
import com.kingdee.util.BaseException;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

public class SimpleDatabaseControllerBean extends AbstractSimpleDatabaseControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.myframework.template.base.app.SimpleDatabaseControllerBean");
    
    
    @Override
    protected IObjectPK _addnew(Context ctx, IObjectValue model) throws BOSException ,EASBizException { 	
    	setNumberFromCodingRule(ctx, model);
    	return  super._addnew(ctx, model);
    }
    
    /**
     * 检查是否编码重复
     */
    protected void _checkNumberDup(Context ctx, IObjectValue model) throws EASBizException, BOSException {
    	try {
    		super._checkNumberDup(ctx, model);
    		
    	} catch (BaseException e) {
    		String orgId = getCurrentOrgId(ctx,model);
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
    				super._checkNumberDup(ctx, model);
    				isCheckNumberDup=true;
    			} catch (Exception ee) {
    				if (currentCheckCount==maxCheckCount)
    					throw new EASBizException(new NumericExceptionSubItem("","超过20次自动获取编码，自动退出取数，请检查编码规则后再做调整！"));
    			}
			} while (!isCheckNumberDup);   
    		//_save(ctx,model); //取号成功后，重新保存数据
    	}
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
		String orgId = getCurrentOrgId(ctx, model);
		number = CodingRuleUtils.getNumber(ctx, model, orgId);
		model.put("number", number);
  
	}
    
    /**
     * 
     */
    @Override
    protected void _delete(Context ctx, IObjectPK pk) throws BOSException,
    		EASBizException {
    	IObjectValue model = getValue(ctx, pk);
    	String orgId = getCurrentOrgId(ctx,model);
    	String number = model.getString("number");
    	super._delete(ctx, pk);
    	CodingRuleUtils.recycleNumber(ctx, model, orgId, number); //启用不允许断号时，对编码的回收
    }
    
    
}