package com.kingdee.eas.myframework.util;

import java.io.Serializable;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.Context;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.metadata.IMetaDataLoader;
import com.kingdee.bos.metadata.MetaDataLoaderFactory;
import com.kingdee.bos.metadata.entity.EntityObjectInfo;
import com.kingdee.eas.base.codingrule.CodingRuleInfo;
import com.kingdee.eas.base.codingrule.CodingRuleManagerFactory;
import com.kingdee.eas.base.codingrule.ICodingRuleManager;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.util.NumericExceptionSubItem;
import com.kingdee.util.StringUtils;

public class CodingRuleUtils implements Serializable {

	private static final long serialVersionUID = -1985752886637408028L;

	/**
	 * 编码规则接口
	 * 
	 * @param ctx
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static ICodingRuleManager getBizInterface(Context ctx)
			throws BOSException, EASBizException {
		ICodingRuleManager codingRuleManager = null;
		if (ctx == null)
			codingRuleManager = CodingRuleManagerFactory.getRemoteInstance();
		else
			codingRuleManager = CodingRuleManagerFactory.getLocalInstance(ctx);
		return codingRuleManager;
	}

	/**
	 * 是否启用编码规则，服务端方法
	 * 
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean hasCodingRule(Context ctx, IObjectValue model,
			String orgId) throws BOSException, EASBizException {
		CodingRuleInfo codingRuleInfo = getCodingRule(ctx, model, orgId);
		return codingRuleInfo != null;
	}

	/**
	 * 是否启用编码规则，客户端方法
	 * 
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean hasCodingRule(IObjectValue model, String orgId)
			throws BOSException, EASBizException {
		return hasCodingRule(null, model, orgId);
	}

	/**
	 * 是否启用编码规则
	 * 
	 * @param codingRuleInfo
	 * @return
	 */
	public static boolean hasCodingRule(CodingRuleInfo codingRuleInfo) {
		return codingRuleInfo != null;
	}

	/**
	 * 是否支持修改编码，服务端方法 若未启用编码规则，则也认为允许修改编码
	 * 
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean isModifiable(Context ctx, IObjectValue model,
			String orgId) throws BOSException, EASBizException {
		CodingRuleInfo codingRuleInfo = getCodingRule(ctx, model, orgId);
		if (codingRuleInfo == null)
			return true;
		return codingRuleInfo.isIsModifiable();
	}
	

	/**
	 * 是否支持修改编码，客户端方法 若未启用编码规则，则也认为允许修改编码
	 * 
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean isModifiable(IObjectValue model, String orgId)
			throws BOSException, EASBizException {
		return isModifiable(null, model, orgId);
	}
	public static boolean isModifiable(CodingRuleInfo codingRuleInfo) throws BOSException, EASBizException {
		if (codingRuleInfo == null)
			return true;
		return codingRuleInfo.isIsModifiable(); 
	}

	/**
	 * 获取编码规则，服务端
	 * 
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static CodingRuleInfo getCodingRule(Context ctx,IObjectValue model, String orgId) throws BOSException, EASBizException {
		if (model == null) throw new EASBizException(new NumericExceptionSubItem("","获取编码规则,业务对象不能为空！"));
		ICodingRuleManager iCodingRuleManager = getBizInterface(ctx);
		if (StringUtils.isEmpty(orgId)) {
			if (ctx == null) { // 客户端获取当前组织
				orgId = SysContext.getSysContext().getCurrentOrgUnit().getString("id");
			} else { // 服务端获取当取组织
				IMetaDataLoader loader = MetaDataLoaderFactory.getMetaDataLoader(ctx);
				EntityObjectInfo entity = loader.getEntity(model.getBOSType());
				String orgType = (String) entity.getExtendedProperties().get("OrgType");
				OrgUnitInfo ou = null;
				if (orgType == null || "NONE".equals(orgType)) {
					ou = ContextUtil.getCurrentCtrlUnit(ctx);
				} else {
					ou = ContextUtil.getCurrentOrgUnit(ctx, OrgType.getEnum(orgType));
				}
				orgId = ou.getString("id");
			}

		}
		CodingRuleInfo codingRuleInfo = iCodingRuleManager.getCodingRule(model, orgId);
		return codingRuleInfo;
	}

	/**
	 * 获取编码规则，客户端
	 * 
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static CodingRuleInfo getCodingRule(IObjectValue model,
			String orgId) throws BOSException, EASBizException {
		return getCodingRule(null, model, orgId);
	}

	/**
	 * 是否启用新增不断号功能，服务端
	 * 
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean isUseIntermitNumber(Context ctx,IObjectValue model, String orgId) throws BOSException,	EASBizException {
		if (model == null) throw new EASBizException(new NumericExceptionSubItem("","获取编码规则,业务对象不能为空！"));
		CodingRuleInfo codingRuleInfo = getCodingRule(ctx, model, orgId);
		return isUseIntermitNumber(codingRuleInfo);
	}

	/**
	 * 是否启用新增不断号功能，客户端
	 * 
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean isUseIntermitNumber(IObjectValue model,String orgId) throws BOSException, EASBizException {
		return isUseIntermitNumber(null, model, orgId);
	}


	/**
	 * 是否启用新增不断号功能
	 * 
	 * @param codingRuleInfo
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean isUseIntermitNumber(CodingRuleInfo codingRuleInfo)
			throws BOSException, EASBizException {
		if (codingRuleInfo == null)
			return false;
		return codingRuleInfo.isIsMainRule();
	}
	
	/**
	 * 是否新增显示
	 * @param codingRuleInfo
	 * @return
	 */
	public static boolean isAddView(CodingRuleInfo codingRuleInfo) {
		if (codingRuleInfo == null) return false;
		return codingRuleInfo.isIsAddView();
	}
	/**
	 * 获取编号
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static String getNumber(Context ctx, IObjectValue model, String orgId)
			throws BOSException, EASBizException {
		if (model == null) throw new EASBizException(new NumericExceptionSubItem("","获取编码,业务对象不能为空！"));
		return getBizInterface(ctx).getNonbreakNumber(model, orgId);
	}
	/**
	 * 回收号码
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @param number
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean recycleNumber(Context ctx, IObjectValue model,
			String orgId, String number) throws BOSException, EASBizException {
		if (model == null) throw new EASBizException(new NumericExceptionSubItem("","回收编码,业务对象不能为空！"));
		if (PublicUtils.isEmpty(number)) throw new EASBizException(new NumericExceptionSubItem("","回收编码,编码不能为空"));
		if (!isUseIntermitNumber(ctx,model,orgId)) return true;
		return getBizInterface(ctx).recycleNumber(model, orgId, number);
	}

}
