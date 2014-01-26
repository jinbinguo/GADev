package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.bot.BOTRelationCollection;
import com.kingdee.bos.metadata.bot.BOTRelationFactory;
import com.kingdee.bos.metadata.bot.BOTRelationInfo;
import com.kingdee.bos.metadata.bot.IBOTRelation;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.scm.common.app.SCMServerUtils;
import com.kingdee.eas.util.SysUtil;

public class BotpUtils implements Serializable {
	/**
	 * 检查是否存在目标单据 
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
	 * 检查是否存在目标单据  客户端方法
	 * @param srcId
	 * @return
	 * @throws Exception
	 */
    public static boolean hasDestBill(String srcId) throws Exception {
    	return hasDestBill(null,srcId);
    }
    
    /**
     * 保存BOTP关系
     * @param ctx
     * @param sourBillId
     * @param destBillId
     * @param mappingId
     * @throws Exception
     */
    public static void saveBotpRelation(Context ctx, String sourBillId, String destBillId, String mappingId) throws Exception {
    	IBOTRelation iBOTRelation  = null;
    	BOTRelationInfo botRelationInfo = new BOTRelationInfo();
    	if (ctx == null) {
    		iBOTRelation = BOTRelationFactory.getRemoteInstance();
    		botRelationInfo.setOperatorID(SysContext.getSysContext().getCurrentUserInfo().getNumber());
    	} else {
    		iBOTRelation = BOTRelationFactory.getLocalInstance(ctx);
    		botRelationInfo.setOperatorID(ctx.getUserName());
    	}
    	
    	BOSObjectType sourType =  BOSUuid.read(sourBillId).getType();
    	BOSObjectType destType = BOSUuid.read(destBillId).getType();
    	botRelationInfo.setSrcObjectID(sourBillId);
    	botRelationInfo.setDestObjectID(destBillId);
    	botRelationInfo.setDate(SysUtil.getAppServerTime(ctx));
    	botRelationInfo.setSrcEntityID(sourType.toString());
    	botRelationInfo.setDestEntityID(destType.toString());
    	botRelationInfo.setBOTMappingID(mappingId);
    	botRelationInfo.setIsEffected(true);
    	botRelationInfo.setType(0);
    	iBOTRelation.addnew(botRelationInfo);
    	
    }
}
