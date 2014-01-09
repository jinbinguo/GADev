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
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.util.StringUtils;

public class CodingRuleUtils implements Serializable {

	private static final long serialVersionUID = -1985752886637408028L;

	/**
	 * �������ӿ�
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
	 * �Ƿ����ñ�����򣬷���˷���
	 * 
	 * @param ctx
	 * @param coreBaseInfo
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean hasCodingRule(Context ctx, CoreBaseInfo coreBaseInfo,
			String orgId) throws BOSException, EASBizException {
		CodingRuleInfo codingRuleInfo = getCodingRule(ctx, coreBaseInfo, orgId);
		return codingRuleInfo != null;
	}

	/**
	 * �Ƿ����ñ�����򣬿ͻ��˷���
	 * 
	 * @param coreBaseInfo
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean hasCodingRule(CoreBaseInfo coreBaseInfo, String orgId)
			throws BOSException, EASBizException {
		return hasCodingRule(null, coreBaseInfo, orgId);
	}

	/**
	 * �Ƿ����ñ������
	 * 
	 * @param codingRuleInfo
	 * @return
	 */
	public static boolean hasCodingRule(CodingRuleInfo codingRuleInfo) {
		return codingRuleInfo != null;
	}

	/**
	 * �Ƿ�֧���޸ı��룬����˷��� ��δ���ñ��������Ҳ��Ϊ�����޸ı���
	 * 
	 * @param ctx
	 * @param coreBaseInfo
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean isModifiable(Context ctx, CoreBaseInfo coreBaseInfo,
			String orgId) throws BOSException, EASBizException {
		CodingRuleInfo codingRuleInfo = getCodingRule(ctx, coreBaseInfo, orgId);
		if (codingRuleInfo == null)
			return true;
		return codingRuleInfo.isIsModifiable();
	}

	/**
	 * �Ƿ�֧���޸ı��룬�ͻ��˷��� ��δ���ñ��������Ҳ��Ϊ�����޸ı���
	 * 
	 * @param ctx
	 * @param coreBaseInfo
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean isModifiable(CoreBaseInfo coreBaseInfo, String orgId)
			throws BOSException, EASBizException {
		return isModifiable(null, coreBaseInfo, orgId);
	}

	/**
	 * ��ȡ������򣬷����
	 * 
	 * @param ctx
	 * @param coreBaseInfo
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static CodingRuleInfo getCodingRule(Context ctx,
			CoreBaseInfo coreBaseInfo, String orgId) throws BOSException,
			EASBizException {
		if (coreBaseInfo == null)
			return null;
		ICodingRuleManager iCodingRuleManager = getBizInterface(ctx);
		if (StringUtils.isEmpty(orgId)) {
			if (ctx == null) { // �ͻ��˻�ȡ��ǰ��֯
				orgId = SysContext.getSysContext().getCurrentOrgUnit()
						.getString("id");
			} else { // ����˻�ȡ��ȡ��֯
				IMetaDataLoader loader = MetaDataLoaderFactory
						.getMetaDataLoader(ctx);
				EntityObjectInfo entity = loader.getEntity(coreBaseInfo
						.getBOSType());
				String orgType = (String) entity.getExtendedProperties().get(
						"OrgType");
				OrgUnitInfo ou = null;
				if (orgType == null || "NONE".equals(orgType)) {
					ou = ContextUtil.getCurrentCtrlUnit(ctx);
				} else {
					ou = ContextUtil.getCurrentOrgUnit(ctx, OrgType
							.getEnum(orgType));
				}
				orgId = ou.getString("id");
			}

		}
		CodingRuleInfo codingRuleInfo = iCodingRuleManager.getCodingRule(
				coreBaseInfo, orgId);
		return codingRuleInfo;
	}

	/**
	 * ��ȡ������򣬿ͻ���
	 * 
	 * @param ctx
	 * @param coreBaseInfo
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static CodingRuleInfo getCodingRule(CoreBaseInfo coreBaseInfo,
			String orgId) throws BOSException, EASBizException {
		return getCodingRule(null, coreBaseInfo, orgId);
	}

	/**
	 * �Ƿ������������ϺŹ��ܣ������
	 * 
	 * @param ctx
	 * @param coreBaseInfo
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean isUseIntermitNumber(Context ctx,
			CoreBaseInfo coreBaseInfo, String orgId) throws BOSException,
			EASBizException {
		CodingRuleInfo codingRuleInfo = getCodingRule(ctx, coreBaseInfo, orgId);
		return isUseIntermitNumber(codingRuleInfo);
	}

	/**
	 * �Ƿ������������ϺŹ��ܣ��ͻ���
	 * 
	 * @param ctx
	 * @param coreBaseInfo
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean isUseIntermitNumber(CoreBaseInfo coreBaseInfo,
			String orgId) throws BOSException, EASBizException {
		return isUseIntermitNumber(null, coreBaseInfo, orgId);
	}


	/**
	 * �Ƿ������������ϺŹ���
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
	 * ��ȡ���
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static String getNumber(Context ctx, IObjectValue model, String orgId)
			throws BOSException, EASBizException {
		return getBizInterface(ctx).getNonbreakNumber(model, orgId);
	}
	/**
	 * ���պ���
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
		if (!isUseIntermitNumber(ctx,(CoreBaseInfo)model,orgId)) return true;
		return getBizInterface(ctx).recycleNumber(model, orgId, number);
	}

}
