package com.kingdee.eas.myframework.template.base.app;

import org.apache.log4j.Logger;
import javax.ejb.*;
import java.rmi.RemoteException;
import com.kingdee.bos.*;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.rule.RuleExecutor;
import com.kingdee.bos.metadata.MetaDataPK;
//import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.framework.ejb.AbstractEntityControllerBean;
import com.kingdee.bos.framework.ejb.AbstractBizControllerBean;
//import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;

import java.lang.String;
import com.kingdee.eas.framework.app.TreeBaseControllerBean;
import com.kingdee.eas.myframework.template.base.GroupDatabaseTreeCollection;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.myframework.template.base.GroupDatabaseTreeInfo;
import com.kingdee.eas.myframework.util.CodingRuleUtils;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.TreeBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.ObjectBaseCollection;
import com.kingdee.eas.framework.DataBaseCollection;
import com.kingdee.util.BaseException;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

public class GroupDatabaseTreeControllerBean extends AbstractGroupDatabaseTreeControllerBean
{
    private static Logger logger = Logger.getLogger("com.kingdee.eas.myframework.template.base.app.GroupDatabaseTreeControllerBean");
    protected IObjectPK _addnew(Context ctx, IObjectValue model) throws BOSException ,EASBizException { 	
    	setNumberFromCodingRule(ctx, model);
    	return  super._addnew(ctx, model);
    }
    
    /**
     * ����Ƿ�����ظ�
     */
    protected void _checkNumberDup(Context ctx, IObjectValue model) throws EASBizException, BOSException {
    	try {
    		super._checkNumberDup(ctx, model);
    		
    	} catch (BaseException e) {
    		String orgId = getCurrentOrgId(ctx,model);
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
    				super._checkNumberDup(ctx, model);
    				isCheckNumberDup=true;
    			} catch (Exception ee) {
    				if (currentCheckCount==maxCheckCount)
    					throw new EASBizException(new NumericExceptionSubItem("","����20���Զ���ȡ���룬�Զ��˳�ȡ�������������������������"));
    			}
			} while (!isCheckNumberDup);   
    		//_save(ctx,model); //ȡ�ųɹ������±�������
    	}
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
    	CodingRuleUtils.recycleNumber(ctx, model, orgId, number); //���ò�����Ϻ�ʱ���Ա���Ļ���
    }
    
}