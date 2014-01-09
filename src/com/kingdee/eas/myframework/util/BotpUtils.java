package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.bot.BOTRelationCollection;
import com.kingdee.bos.metadata.bot.BOTRelationFactory;
import com.kingdee.bos.metadata.bot.IBOTRelation;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.eas.scm.common.app.SCMServerUtils;

public class BotpUtils implements Serializable {
	/**
	 * ����Ƿ����Ŀ�굥�� 
	 * @param ctx
	 * @param srcId
	 * @return
	 * @throws BOSException
	 */
	public static boolean hasDestBill(Context ctx, String srcId) throws Exception {
		//return SCMServerUtils.hasDestBill(ctx,srcId);
		IBOTRelation botRelation = null;
		if (ctx == null) botRelation = BOTRelationFactory.getRemoteInstance();
		else botRelation = BOTRelationFactory.getLocalInstance(ctx);
		EntityViewInfo viewInfo = new EntityViewInfo();
    	FilterInfo filterInfo = new FilterInfo();
    	filterInfo.getFilterItems().add(new FilterItemInfo("srcObjectID", srcId, CompareType.EQUALS));
    	viewInfo.setFilter(filterInfo);
    	viewInfo.getSelector().add(new SelectorItemInfo("id"));
    	BOTRelationCollection collection = botRelation.getCollection(viewInfo);
    	return collection != null && collection.size() > 0;
	}
	/**
	 * ����Ƿ����Ŀ�굥��  �ͻ��˷���
	 * @param srcId
	 * @return
	 * @throws Exception
	 */
    public static boolean hasDestBill(String srcId) throws Exception {
    	return hasDestBill(null,srcId);
    }
}
