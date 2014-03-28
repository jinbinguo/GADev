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
	 * �Ƿ����ñ�����򣬿ͻ��˷���
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
	 * �Ƿ�֧���޸ı��룬�ͻ��˷��� ��δ���ñ��������Ҳ��Ϊ�����޸ı���
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
	 * ��ȡ������򣬷����
	 * 
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static CodingRuleInfo getCodingRule(Context ctx,IObjectValue model, String orgId) throws BOSException, EASBizException {
		if (model == null) throw new EASBizException(new NumericExceptionSubItem("","��ȡ�������,ҵ�������Ϊ�գ�"));
		ICodingRuleManager iCodingRuleManager = getBizInterface(ctx);
		if (StringUtils.isEmpty(orgId)) {
			if (ctx == null) { // �ͻ��˻�ȡ��ǰ��֯
				orgId = SysContext.getSysContext().getCurrentOrgUnit().getString("id");
			} else { // ����˻�ȡ��ȡ��֯
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
	 * ��ȡ������򣬿ͻ���
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
	 * �Ƿ������������ϺŹ��ܣ������
	 * 
	 * @param ctx
	 * @param model
	 * @param orgId
	 * @return
	 * @throws BOSException
	 * @throws EASBizException
	 */
	public static boolean isUseIntermitNumber(Context ctx,IObjectValue model, String orgId) throws BOSException,	EASBizException {
		if (model == null) throw new EASBizException(new NumericExceptionSubItem("","��ȡ�������,ҵ�������Ϊ�գ�"));
		CodingRuleInfo codingRuleInfo = getCodingRule(ctx, model, orgId);
		return isUseIntermitNumber(codingRuleInfo);
	}

	/**
	 * �Ƿ������������ϺŹ��ܣ��ͻ���
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
	 * �Ƿ�������ʾ
	 * @param codingRuleInfo
	 * @return
	 */
	public static boolean isAddView(CodingRuleInfo codingRuleInfo) {
		if (codingRuleInfo == null) return false;
		return codingRuleInfo.isIsAddView();
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
		if (model == null) throw new EASBizException(new NumericExceptionSubItem("","��ȡ����,ҵ�������Ϊ�գ�"));
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
		if (model == null) throw new EASBizException(new NumericExceptionSubItem("","���ձ���,ҵ�������Ϊ�գ�"));
		if (PublicUtils.isEmpty(number)) throw new EASBizException(new NumericExceptionSubItem("","���ձ���,���벻��Ϊ��"));
		if (!isUseIntermitNumber(ctx,model,orgId)) return true;
		return getBizInterface(ctx).recycleNumber(model, orgId, number);
	}

}
