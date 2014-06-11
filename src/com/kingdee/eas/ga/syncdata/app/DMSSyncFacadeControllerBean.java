package com.kingdee.eas.ga.syncdata.app;

import org.apache.log4j.Logger;

import javassist.NotFoundException;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kingdee.bos.*;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemCollection;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.entity.SelectorItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.metadata.rule.RuleExecutor;
import com.kingdee.bos.metadata.MetaDataPK;
//import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.framework.ejb.AbstractEntityControllerBean;
import com.kingdee.bos.framework.ejb.AbstractBizControllerBean;
//import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.ctrl.common.util.StringUtil;
import com.kingdee.bos.dao.IObjectValue;
import com.kingdee.bos.dao.IObjectCollection;
import com.kingdee.bos.dao.ormapping.ObjectUuidPK;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.bos.service.IServiceContext;
import com.kingdee.eas.auto4s.bdm.pbd.AllowEnum;
import com.kingdee.eas.auto4s.bdm.pbd.BaseDataStatusEnum;
import com.kingdee.eas.auto4s.bdm.pbd.BdmPbdException;
import com.kingdee.eas.auto4s.bdm.pbd.BrandCollection;
import com.kingdee.eas.auto4s.bdm.pbd.BrandCompanyCollection;
import com.kingdee.eas.auto4s.bdm.pbd.BrandCompanyFactory;
import com.kingdee.eas.auto4s.bdm.pbd.BrandDetailCollection;
import com.kingdee.eas.auto4s.bdm.pbd.BrandDetailInfo;
import com.kingdee.eas.auto4s.bdm.pbd.BrandFactory;
import com.kingdee.eas.auto4s.bdm.pbd.BrandInfo;
import com.kingdee.eas.auto4s.bdm.pbd.CommonUtilFacadeFactory;
import com.kingdee.eas.auto4s.bdm.pbd.ContactPerson;
import com.kingdee.eas.auto4s.bdm.pbd.ContactPersonCollection;
import com.kingdee.eas.auto4s.bdm.pbd.ContactPersonFactory;
import com.kingdee.eas.auto4s.bdm.pbd.ContactPersonInfo;
import com.kingdee.eas.auto4s.bdm.pbd.CustomerBelongInfo;
import com.kingdee.eas.auto4s.bdm.pbd.CustomerClassifyEnum;
import com.kingdee.eas.auto4s.bdm.pbd.CustomerCollection;
import com.kingdee.eas.auto4s.bdm.pbd.CustomerFacadeFactory;
import com.kingdee.eas.auto4s.bdm.pbd.CustomerFactory;
import com.kingdee.eas.auto4s.bdm.pbd.CustomerInfo;
import com.kingdee.eas.auto4s.bdm.pbd.CustomerRepeatRuleInfo;
import com.kingdee.eas.auto4s.bdm.pbd.DataImportException;
import com.kingdee.eas.auto4s.bdm.pbd.IContactPerson;
import com.kingdee.eas.auto4s.bdm.pbd.ICustomer;
import com.kingdee.eas.auto4s.bdm.pbd.IModel;
import com.kingdee.eas.auto4s.bdm.pbd.IVehicle;
import com.kingdee.eas.auto4s.bdm.pbd.IVehicleBelong;
import com.kingdee.eas.auto4s.bdm.pbd.ModelCollection;
import com.kingdee.eas.auto4s.bdm.pbd.ModelFactory;
import com.kingdee.eas.auto4s.bdm.pbd.ModelInfo;
import com.kingdee.eas.auto4s.bdm.pbd.SeriesFactory;
import com.kingdee.eas.auto4s.bdm.pbd.SeriesInfo;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleBelongCollection;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleBelongFactory;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleBelongInfo;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleCollection;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleCreateTypeEnum;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleFactory;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleInfo;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleSourceEnum;
import com.kingdee.eas.auto4s.bdm.pbd.VehicleStatusEnum;
import com.kingdee.eas.auto4s.bdm.pbd.app.DataImportUtils;
import com.kingdee.eas.auto4s.bdm.util.CommonUtil;
import com.kingdee.eas.auto4s.commonutil.AppCommonUtils;
import com.kingdee.eas.auto4s.commonutil.CommonUtils;
import com.kingdee.eas.base.codingrule.CodingRuleException;
import com.kingdee.eas.base.codingrule.CodingRuleManagerFactory;
import com.kingdee.eas.base.codingrule.ICodingRuleManager;
import com.kingdee.eas.base.param.IParamControl;
import com.kingdee.eas.base.param.ParamControlFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.assistant.MeasureUnitCollection;
import com.kingdee.eas.basedata.assistant.MeasureUnitFactory;
import com.kingdee.eas.basedata.assistant.MeasureUnitInfo;
import com.kingdee.eas.basedata.master.cssp.CSSPGroupInfo;
import com.kingdee.eas.basedata.master.cssp.CustomerSupplierException;
import com.kingdee.eas.basedata.master.material.MaterialGroupCollection;
import com.kingdee.eas.basedata.master.material.MaterialGroupFactory;
import com.kingdee.eas.basedata.master.material.MaterialGroupInfo;
import com.kingdee.eas.basedata.master.material.app.DataImportTools;
import com.kingdee.eas.basedata.org.AdminOrgUnitCollection;
import com.kingdee.eas.basedata.org.AdminOrgUnitFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.CtrlUnitInfo;
import com.kingdee.eas.basedata.org.FullOrgUnitCollection;
import com.kingdee.eas.basedata.org.FullOrgUnitFactory;
import com.kingdee.eas.basedata.org.FullOrgUnitInfo;
import com.kingdee.eas.basedata.org.IFullOrgUnit;
import com.kingdee.eas.basedata.org.OrgType;
import com.kingdee.eas.basedata.org.OrgUnitInfo;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.common.client.SysContext;
import com.kingdee.eas.fm.common.FMVerifyHelper;
import com.kingdee.eas.framework.DeletedStatusEnum;
import com.kingdee.eas.ga.common.DateUtil;
import com.kingdee.eas.ga.syncdata.ISyncLog;
import com.kingdee.eas.ga.syncdata.ImportStateEnum;
import com.kingdee.eas.ga.syncdata.SyncLog;
import com.kingdee.eas.ga.syncdata.SyncLogCollection;
import com.kingdee.eas.ga.syncdata.SyncLogEntryInfo;
import com.kingdee.eas.ga.syncdata.SyncLogFactory;
import com.kingdee.eas.ga.syncdata.SyncLogInfo;
import com.kingdee.eas.tools.datatask.core.TaskExternalException;
import com.kingdee.eas.tools.datatask.runtime.DataToken;
import com.kingdee.eas.util.app.ContextUtil;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.StringUtils;

public class DMSSyncFacadeControllerBean extends AbstractDMSSyncFacadeControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.ga.syncdata.app.DMSSyncFacadeControllerBean");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	private String ExcelFilename = "";
	private String ImportOrg = "";
	private UserInfo currentUser = null;
	private Hashtable SeriesList= new Hashtable(); 
	private Hashtable MeasureUnitList= new Hashtable(); 
	private Hashtable MaterialGroupList= new Hashtable(); 
	private Hashtable CSSPGroupList = new Hashtable();
	private Hashtable BrandList = new Hashtable();
	private Hashtable PersonList = new Hashtable();
	private Hashtable OrgUnitList = new Hashtable();
	private Hashtable CustTypeList = new Hashtable();
	private Hashtable CustClassList = new Hashtable();
	private Hashtable CustAttributeList = new Hashtable();
	
	private Boolean isCustomerUsedCodingRule = null;

    /**
	 * 同步客户档案资料
	 * @author limin lin
	 * @param maxRecords 每次处理的最大记录数
	 */
	@Override
	protected void _ImportCustProfiles(Context ctx, int maxRecords)
			throws BOSException {
		isCustomerUsedCodingRule = null;
		currentUser = ContextUtil.getCurrentUserInfo(ctx);
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		IRowSet rs = getCustProfiles(ctx, maxRecords);
		String message = "";
		String number = "";
		String originName = "";
		String vin = "";
		int totalCount = 0;
		int errorCount = 0;
		int skipCount = 0;
		if(rs != null){
			try {
				ICustomer factory = CustomerFactory.getLocalInstance(ctx);
				while(rs.next()){
					try {
						ExcelFilename = rs.getString("FExcelFilename");
						ImportOrg = rs.getString("FImportOrg");						
						number = rs.getString("FNumber");					
						String name = rs.getString("FName_l2");
						originName = name;
						String custType = rs.getString("FCustType");
						String custClass = rs.getString("FCustClass");
						String custAttribute = rs.getString("FCustAttribute");
						Date firstDate = rs.getDate("FFirstDate");
						String phone = rs.getString("FPhone");
						String stdCustomerGroupNumber = rs.getString("FStdCustomerGroup_number");
						String stdCustomerGroupName = rs.getString("FStdCustomerGroup_name_l2");
						int isLock = rs.getInt("FIsLock");
						int serviceVerify = rs.getInt("FServiceVerify");
						int isUse = rs.getInt("FIsUse");
						String address = rs.getString("FAddress");
						String brandNumber = rs.getString("FBelong$Brand_number");
						String brandName = rs.getString("FBelong$Brand_name_l2");
						String orgUnitNumber = rs.getString("FBelong$OrgUnit_number");
						String orgUnitName = rs.getString("FBelong$OrgUnit_name_l2");
						String personNumber = rs.getString("FBelong$Person_number");
						String personName = rs.getString("FBelong$Person_name_l2");
						String prePersonNumber = rs.getString("FBelong$FPrePerson_number");
						String prePersonName = rs.getString("FBelong$FPrePerson_name_l2");
						vin = rs.getString("FVIN");
						
						try
		                {
							IVehicle vehicle = VehicleFactory.getLocalInstance(ctx);
		                    VehicleCollection collection = vehicle.getVehicleCollection((new StringBuilder()).append("where vIN ='").append(vin.trim()).append("'").toString());
		                    if(collection != null && collection.size() > 0){
		                    	//客户档案关联底盘号已经引入到EAS，如果是无名称客户，则跳过不处理当前客户档案
		                    	if(name.indexOf("无名称")>=0){
		                    		skipCount++;
		                    		message = String.format("【客户档案引入】车辆底盘号：%s 车辆档案已存在，对应记录行客户档案引入时，无名称客户跳过不再重新导入", vin);
		    						errorCount ++;
		    						if(!StringUtil.isEmptyString(number)) updateCustProfiles(ctx, number);
		    						Log(ctx, currentUser, ImportStateEnum.Failure, message);
		                    		continue;
		                    	}
		                    }
		                }
		                catch(Exception e)
		                {
		                    e.printStackTrace();
		                }
						
						CustomerInfo customer = new CustomerInfo();
						if(!StringUtils.isEmpty(number))
			            {
							customer = getCustomerInfoFromNumber(ctx, number);
			            }
						else
						{
			                number = getNumberByCodingRule(ctx, customer);
			                if(StringUtils.isEmpty(number))
			                {
			                	FMVerifyHelper.checkBlank(ctx, customer, "number");
			                    throw new TaskExternalException(DataImportTools.getResString("com.kingdee.eas.basedata.master.material.MaterialResource", "DataImport_HasNoRule_WithPara", ctx.getLocale()));
			                }
			            }
						
						//客户档案id
						String existId =  "";
						if(customer.getId() != null) existId = customer.getId().toString();
						
						//更新客户档案各字段信息
						customer.setNumber(number);
						if(name.indexOf("无名称")>=0) name = ".";
						customer.setName(name);
						customer.setCustType(DataImportUtils.getCustomerTypeValue(custType));
						customer.setCustClass(DataImportUtils.getCustomerClassifyValue(custClass));
						customer.setCustAttribute(DataImportUtils.getOwnOutValue(custAttribute));
						customer.setFirstDate(firstDate);
						if(phone.indexOf("无号码")>=0) phone = ".";
						customer.setPhone(phone);

						if(CSSPGroupList.containsKey(stdCustomerGroupNumber)){
							CSSPGroupInfo stdCustomerGroupInfo = (CSSPGroupInfo)CSSPGroupList.get(stdCustomerGroupNumber);
							customer.setStdCustomerGroup(stdCustomerGroupInfo);
						}
						else{
							CSSPGroupInfo stdCustomerGroupInfo = DataImportUtils.getCSSPGroupInfoFromNumber(ctx, stdCustomerGroupNumber);
			                if(stdCustomerGroupInfo != null){
			                     customer.setStdCustomerGroup(stdCustomerGroupInfo);
			                     CSSPGroupList.put(stdCustomerGroupNumber, stdCustomerGroupInfo);
			                }
						}
		                customer.setIsLock(isLock == 0 ? false : true);
		                customer.setServiceVerify(serviceVerify == 0 ? false : true);
		                customer.setIsUse(DataImportUtils.getDeletedStatusValue(String.valueOf(isUse)));
		                customer.setAddress(address);
		             
		                CustomerBelongInfo belongInfo = new CustomerBelongInfo();
		                BrandInfo brandInfo = new BrandInfo();
		                if(BrandList.containsKey(brandNumber)){
		                	brandInfo = (BrandInfo)BrandList.get(brandNumber);
						}
						else{
			                brandInfo = DataImportUtils.getBrandInfoFromNumber(ctx, brandNumber);
			                if(brandInfo == null){
			                	//客户来源品牌不存在
								message = String.format("【客户档案引入】底盘号：%s 对应记录行，客户来源品牌%s(%s)不存在！", vin, brandName,brandNumber);
								throw new NotFoundException(message);
			                }
			                BrandList.put(brandNumber, brandInfo);
						}
		                belongInfo.setBrand(brandInfo);
		                
		                AdminOrgUnitInfo orgUnitInfo = new AdminOrgUnitInfo();
		                if(OrgUnitList.containsKey(orgUnitNumber)){
		                	orgUnitInfo = (AdminOrgUnitInfo)OrgUnitList.get(orgUnitNumber);
						}
						else{
			                orgUnitInfo = DataImportUtils.getAdminOrgUnitInfoFromNumber(ctx, orgUnitNumber);
			                if(orgUnitInfo == null){
			                	//客户来源公司不存在
								message = String.format("【客户档案引入】车辆底盘号：%s 对应记录行，客户来源公司%s(%s)不存在！", vin, orgUnitName,orgUnitNumber);
								throw new NotFoundException(message);
			                }
			                OrgUnitList.put(orgUnitNumber, orgUnitInfo);
						}
		                belongInfo.setOrgUnit(orgUnitInfo);
		                
		                PersonInfo personInfo  = new PersonInfo();
		                if(PersonList.containsKey(personNumber)){
		                	personInfo = (PersonInfo)PersonList.get(personNumber);
						}
						else{
			                personInfo = DataImportUtils.getPersonInfoFromNumber(ctx, personNumber);
			                if(personInfo == null){
			                	//客户来源销售顾问不存在
								message = String.format("【客户档案引入】车辆底盘号：%s 对应记录行，客户来源销售顾问%s(%s)不存在！", vin, personName,personNumber);
								throw new NotFoundException(message);
			                }
			                PersonList.put(personNumber, personInfo);
						}
		                belongInfo.setPerson(personInfo);

		                PersonInfo prePersonInfo  = new PersonInfo();
		                if(PersonList.containsKey(prePersonNumber)){
		                	prePersonInfo = (PersonInfo)PersonList.get(prePersonNumber);
						}
						else{
			                prePersonInfo = DataImportUtils.getPersonInfoFromNumber(ctx, prePersonNumber);
			                if(prePersonInfo == null){
			                	//客户来源销售顾问不存在
								message = String.format("【客户档案引入】车辆底盘号：%s 对应记录行，客户来源销售顾问%s(%s)不存在！", vin, prePersonName,prePersonNumber);
								throw new NotFoundException(message);
			                }
			                PersonList.put(personNumber, personInfo);
						}
		                belongInfo.setPrePerson(prePersonInfo);	
		                if(belongInfo.getPerson() != null){

			                int belongIndex = -1;
		                	if(customer.getBelong() != null && customer.getBelong().size() > 0)
		                    {
		                        for(int i = 0; i < customer.getBelong().size(); i++)
		                        {
		                        	CustomerBelongInfo belong = customer.getBelong().get(i);
		                            if(belongInfo.getBrand().equals(belong.getBrand())
		                            		&& belongInfo.getOrgUnit().equals(belong.getOrgUnit())
		                            		&& belongInfo.getPerson().equals(belong.getPerson())){
		                            	belongIndex = i;
		                            	break;
		                            }
		                        }
		                    }
		                	if(belongIndex >= 0){
		                		customer.getBelong().set(belongIndex, belongInfo);
		                	}
		                	else{
		                		customer.getBelong().add(belongInfo);
		                	}		                	

	                        for(int i = 0; i < customer.getBelong().size(); i++)
	                        {
	                            CustomerBelongInfo belong = customer.getBelong().get(i);
	                            if(belong == null || belong.getPerson() == null){
	                            	customer.getBelong().remove(belong);
	                            }
	                        }
		                }
		                
		                //如果客户来源公司不为空，设置当前组织为来源公司所属组织
		                if(orgUnitInfo != null){
		                	customer.setCU(orgUnitInfo.getCU());
		                }
		                else
		                {
		                	CtrlUnitInfo cu = getCtrlUnitInfoWithName(ctx, ImportOrg);
		                	if(cu == null){
		                		cu = (CtrlUnitInfo)ctx.get(OrgType.ControlUnit);
		                	}
		                	customer.setCU(cu);
		                }						

						/*if(customer != null)
			            {
			                Map map = CustomerFacadeFactory.getLocalInstance(ctx).checkRepeat(customer);
			                if(map != null)
			                {
			                    CustomerRepeatRuleInfo ruleInfo = (CustomerRepeatRuleInfo)map.get("CustomerRepeatRuleInfo");
			                    StringBuffer sb = new StringBuffer();
			                    sb.append("\u6839\u636E\u5F53\u524D\u5BA2\u6237\u6863\u6848\u91CD\u590D\u89C4\u5219\u8BBE\u7F6E\u201C");
			                    sb.append(ruleInfo.getCustomerVifyRule().toString());
			                    sb.append("\u201D\uFF0C\u4E0D\u5141\u8BB8\u5F15\u5165\u91CD\u590D\u7684\u5BA2\u6237\u6863\u6848\uFF01");
			                    if(ruleInfo != null && AllowEnum.NO.equals(ruleInfo.getAddnew()))
			                        throw new TaskExternalException(sb.toString());
			                }
			            }*/
						
						checkCustomer(customer, ctx);
						
						//如果客户档案信息已经存在，则更新，否则新增
						if(!StringUtil.isEmptyString(existId)){
							customer.setLastUpdateUser(currentUser);
							customer.setLastUpdateTime(time);
							factory.update(new ObjectUuidPK(existId), customer);
						}
						else{
							customer.setCreator(currentUser);
							customer.setCreateTime(time);
							factory.addnew(customer);
						}
						
						if(customer.getCustClass() != null)
			            {
			                CustomerClassifyEnum en = customer.getCustClass();
			                if(en.equals(CustomerClassifyEnum.RETAIN))
			                {
			                    if(customer.getStdCustomer() == null && CustomerInfo.checkNumberDupl(ctx) && nameDupCheck(customer, ctx) > 0)
			                        throw new TaskExternalException("\u8D22\u52A1\u5BA2\u6237\u91CD\u590D!");
			                    Map map = new HashMap();
			                    map.put("billType", "industryCustomer");
			                    if(customer.getBelong() != null)
			                        map.put("customerBelong", customer.getBelong());
			                    map.put("info", customer);
			                    CommonUtilFacadeFactory.getLocalInstance(ctx).syncCustomerData(map);
			                }
			            }
						
						//添加主要联系人信息
						ContactPersonUpdate(ctx, customer);
						
						//更新成功后，删除临时表数据
						removeCustProfiles(ctx,number);
					}
					catch(NotFoundException e){
						e.printStackTrace();
						errorCount ++;
						if(!StringUtil.isEmptyString(number)) updateCustProfiles(ctx, number);
						Log(ctx, currentUser, ImportStateEnum.Failure, e.getLocalizedMessage());						
					}
					catch (Exception e) {
						e.printStackTrace();
						message = String.format("【客户档案引入】车辆底盘号：%s 对应记录行，发生未知异常：%s", vin, e.getLocalizedMessage());
						errorCount ++;
						if(!StringUtil.isEmptyString(number)) updateCustProfiles(ctx, number);
						Log(ctx, currentUser, ImportStateEnum.Failure, message);						
					}

					totalCount ++;
				}//while(rs.next())
			} 
			catch (SQLException e) {
				e.printStackTrace();
				message = "【客户档案引入】";
				if(!StringUtils.isEmpty(originName) || !StringUtils.isEmpty(number))
					message += String.format("车辆底盘号：%s 对应记录行引入时，", vin);
				message += String.format("发生未知异常：%s", e.getLocalizedMessage());
				Log(ctx, currentUser, ImportStateEnum.Failure, message);
			} 
		}//if(rs != null)

		if(totalCount>0){
			message = String.format("【客户档案引入】记录总数：%s 条，导入成功：%s 条", totalCount, totalCount - errorCount - skipCount);
			if(skipCount>0){
				message += String.format("，车档已导入不处理客户档案数： %s 条。", skipCount);
			}
			ImportStateEnum state = ImportStateEnum.Success;
			if(errorCount > 0){
				state = ImportStateEnum.Failure;
			}
			Log(ctx, currentUser, state , message);
		}
	}

	/**
	 * 同步车辆档案资料
	 * @author limin lin
	 * @param maxRecords 每次处理的最大记录数
	 */
	@Override
	protected void _ImportVehicleProfiles(Context ctx, int maxRecords)
			throws BOSException {
		currentUser = ContextUtil.getCurrentUserInfo(ctx);
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		IRowSet rs = getVehicleProfiles(ctx, maxRecords);
		String message = "";
		String vin = "";
		
		int totalCount = 0;
		int errorCount = 0;
		if(rs != null){
			try {
				IVehicle factory = VehicleFactory.getLocalInstance(ctx);
				while(rs.next()){
					try {
						ExcelFilename = rs.getString("FExcelFilename");
						ImportOrg = rs.getString("FImportOrg");	
						vin = rs.getString("FVIN");
						
						VehicleInfo vehicle = new VehicleInfo();
						vehicle = getVehicleInfo(ctx, rs, vehicle);

						if(!vehicle.isOtherBrandVehicle())
			            {
			                setModel(ctx, rs, vehicle);
			            } else
			            {
			            	vehicle.setModel(null);
			            	vehicle.setSeries(null);
			            	vehicle.setBrand(null);
			            	vehicle.setInner(null);
			            	vehicle.setColor(null);
			            	vehicle.setOptionItemCombine(null);
			            }
			            if(StringUtils.isEmpty(vehicle.getNumber()))
			            {
			                VehicleFactory.getLocalInstance(ctx).checkVehicleCodingRuleIsUse(vehicle);
			                AppCommonUtils.setNumberByCodingRule(ctx, vehicle);
			            }
			            setOrgUnit(ctx, rs, vehicle);
			            setCustomer(ctx, rs, vehicle);
			            setOrderCustomer(ctx, rs, vehicle);
			            if(vehicle.getCustomer() != null)
			                setMainUser(ctx, vehicle.getCustomer().getPhone(), vehicle);
			            
			            if(VehicleSourceEnum.OWN.equals(vehicle.getSource()))
			            	vehicle.setVehicleStatus(VehicleStatusEnum.ISSUE);
			            if(vehicle != null)
			            	vehicle.setName(getMegerString(vehicle.getVIN(), vehicle.getPlateNum()));
			            checkPlateNumValite(ctx, vehicle);
			            String existId = "";
			            if(vehicle.getId() != null) existId = vehicle.getId().toString();
						
						//添加车辆所属维修公司
						SetVehicleBelongs(ctx, vin, vehicle);
						
						//如果车辆档案信息已经存在，则更新，否则新增
						if(!StringUtil.isEmptyString(existId)){
							vehicle.setLastUpdateUser(currentUser);
							vehicle.setLastUpdateTime(time);
							factory.update(new ObjectUuidPK(existId), vehicle);
						}
						else{
							vehicle.setCreator(currentUser);
							vehicle.setCreateTime(time);
							factory.addnew(vehicle);
						}
						
						//更新成功后，删除临时表数据
						removeVehicleProfiles(ctx,vin);
						removeVehicleRepaireCenter(ctx, vin);
					}
					catch(NotFoundException e){
						e.printStackTrace();
						errorCount ++;
						if(!StringUtil.isEmptyString(vin)) {
							updateVehicleProfiles(ctx, vin);
							updateVehicleRepaireCenter(ctx, vin);
						}
						Log(ctx, currentUser, ImportStateEnum.Failure, e.getLocalizedMessage());						
					}
					catch (Exception e) {
						e.printStackTrace();
						message = String.format("【车辆档案引入】车辆底盘号：%s对应记录行引入时，发生未知异常：%s", vin, e.getLocalizedMessage());
						errorCount ++;
						if(!StringUtil.isEmptyString(vin)) {
							updateVehicleProfiles(ctx, vin);
							updateVehicleRepaireCenter(ctx, vin);
						}
						Log(ctx, currentUser, ImportStateEnum.Failure, message);						
					}

					totalCount ++;
				}//while(rs.next())
			} 
			catch (SQLException e) {
				e.printStackTrace();
				message = "【车辆档案引入】";
				if(!StringUtils.isEmpty(vin)){
					message += String.format("车辆底盘号%s对应记录行引入时，", vin);
				}
				message += String.format("发生未知异常：%s", e.getLocalizedMessage());
				Log(ctx, currentUser, ImportStateEnum.Failure, message);
			} 
		}//if(rs != null)
		if(totalCount>0){
			message = String.format("【车辆档案引入】记录总数：%s 条，导入成功：%s 条。", totalCount, totalCount - errorCount);
			ImportStateEnum state = ImportStateEnum.Success;
			if(errorCount > 0){
				state = ImportStateEnum.Failure;
			}
			Log(ctx, currentUser, state , message);
		}
	}

	/**
	 * 同步车型资料
	 * @author limin lin
	 * @param maxRecords 每次处理的最大记录数
	 */
	@Override
	protected void _ImportVehicleTypes(Context ctx, int maxRecords)
			throws BOSException {
		currentUser = ContextUtil.getCurrentUserInfo(ctx);
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		IRowSet rs = getVehicleType(ctx, maxRecords);
		String message = "";
		String number = "";
		String name = "";
		
		int totalCount = 0;
		int errorCount = 0;
		if(rs != null){
			try {
				IModel factory = ModelFactory.getLocalInstance(ctx);
				while(rs.next()){
					try {
						ExcelFilename = rs.getString("FExcelFilename");
						ImportOrg = rs.getString("FImportOrg");
						
						String parentNumber = rs.getString("FParent_number");
						String parentName = rs.getString("FParent_name_l2");
						number = rs.getString("FNumber");					
						name = rs.getString("FName_l2");
						String unitNumber = rs.getString("FUnit_number");					
						String unitName = rs.getString("FUnit_name_l2");
						int isUse = rs.getInt("FIsUse");
						String auditStatus = rs.getString("FAuditStatus");
						String materialGroupNumber = rs.getString("FMaterialGroup_number");
												
						ModelInfo model = getModelInfoFromNumber(ctx, number);
						//车型id
						String existId =  "";
						if(model.getId() != null) existId = model.getId().toString();

						//更新车型各字段信息
						try
						{
							SeriesInfo series = getSeriesInfo(ctx, "number", parentNumber);
							if(series != null){
								model.setParent(series);
							}
						}
						catch(Exception e_number){
							e_number.printStackTrace();
							try
							{
								SeriesInfo series = getSeriesInfo(ctx, "name", parentName);
								if(series != null){
									model.setParent(series);
								}							
							}
							catch(Exception e_name){
								e_name.printStackTrace();
								//车系不存在
								message = String.format("【车型引入】车型%s(%s)对应记录行，车系%s(%s)不存在", name, number, parentName, parentNumber);
								throw new NotFoundException(message);
							}
						}
						model.setNumber(number);
						model.setName(name);
						
						try
						{
	
							MeasureUnitInfo unit = getMeasureUnitInfo(ctx, "number", unitNumber);
							
							if(unit != null){
								model.setUnit(unit);
							}
						}
						catch(Exception e_number){
							e_number.printStackTrace();
							try
							{
								MeasureUnitInfo unit = getMeasureUnitInfo(ctx, "name", unitNumber);
								
								if(unit != null){
									model.setUnit(unit);
								}						
							}
							catch(Exception e_name){
								e_name.printStackTrace();
								//计量单位不存在
								message = String.format("【车型引入】车型%s(%s)对应记录行，计量单位%s(%s)不存在", name, number, unitName, unitNumber);
								throw new NotFoundException(message);
							}
						}
												
				        if(isUse==0)
				            model.setIsUse(DeletedStatusEnum.DELETED);
				        else
				            model.setIsUse(DeletedStatusEnum.NORMAL);
				        
				        if(auditStatus.equals("0"))
				        	model.setAuditStatus(BaseDataStatusEnum.NOAUDIT);
				        else
				        	model.setAuditStatus(BaseDataStatusEnum.AUDIT);
						
						MaterialGroupInfo group = getMaterialGroupInfo(ctx, "number", materialGroupNumber);
						model.setMaterialGroup(group);						
						
						model.setCU((CtrlUnitInfo)ctx.get(OrgType.ControlUnit));	
						
						//如果车型信息已经存在，则更新，否则新增
						if(!StringUtil.isEmptyString(existId)){
							model.setLastUpdateUser(currentUser);
							model.setLastUpdateTime(time);
							factory.update(new ObjectUuidPK(existId), model);
						}
						else{
							model.setCreator(currentUser);
							model.setCreateTime(time);
							model.setAuditor(currentUser);
							model.setAuditTime(time);
							factory.addnew(model);
						}
						
						//更新成功后，删除临时表数据
						removeVehicleType(ctx,number);
					}
					catch(NotFoundException e){
						e.printStackTrace();
						errorCount ++;
						if(!StringUtil.isEmptyString(number)) updateVehicleType(ctx, number);
						Log(ctx, currentUser, ImportStateEnum.Failure, e.getLocalizedMessage());						
					}
					catch (Exception e) {
						e.printStackTrace();
						message = String.format("【车型引入】车型%s(%s)对应记录行引入时，发生未知异常：%s", name, number, e.getLocalizedMessage());
						errorCount ++;
						if(!StringUtil.isEmptyString(number)) updateVehicleType(ctx, number);
						Log(ctx, currentUser, ImportStateEnum.Failure, message);						
					}

					totalCount ++;
				}//while(rs.next())
			} 
			catch (SQLException e) {
				e.printStackTrace();
				message = String.format("【车型引入】车型%s(%s)对应记录行，发生未知异常：%s", name, number, e.getLocalizedMessage());
				Log(ctx, currentUser, ImportStateEnum.Failure, message);
			} 
		}//if(rs != null)
		if(totalCount>0){
			message = String.format("【车型引入】记录总数：%s 条，导入成功：%s 条。", totalCount, totalCount - errorCount);
			ImportStateEnum state = ImportStateEnum.Success;
			if(errorCount > 0){
				state = ImportStateEnum.Failure;
			}
			Log(ctx, currentUser, state , message);
		}
	}
	
	protected void SetVehicleBelongs(Context ctx, String vin, VehicleInfo vehicle) throws Exception{
		currentUser = ContextUtil.getCurrentUserInfo(ctx);
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		IRowSet rs = getVehicleRepaireCenter(ctx, vin);
		String message = "";
		String parentVIN = "";
		String orgUnitNumber = "";
		String orgUnitName = "";
		
		int totalCount = 0;
		int errorCount = 0;
		if(rs != null){
			try {
				IVehicleBelong factory = VehicleBelongFactory.getLocalInstance(ctx);
				
				while(rs.next()){
					try {						
						parentVIN = rs.getString("FParent_vIN");
						orgUnitNumber = rs.getString("FServiceOrgUnit_number");					
						orgUnitName = rs.getString("FServiceOrgUnit_name_l2");						
						String servicePersonNumber = rs.getString("FServicePerson_number");					
						String servicePersonName = rs.getString("FServicePerson_name_l2");						
						int isDefault = rs.getInt("FIsDefault");
						Date serviceDate = rs.getDate("FServiceDate");
						String vehicleId = "";
						if(vehicle.getId() != null){
							vehicleId = vehicle.getId().toString();
						}
						VehicleBelongInfo belong = new VehicleBelongInfo();

						belong.setParent(vehicle);
						
						setOrgUnit(ctx, rs, belong, vehicle);
		                
		                PersonInfo personInfo  = new PersonInfo();
		                if(PersonList.containsKey(servicePersonNumber)){
		                	personInfo = (PersonInfo)PersonList.get(servicePersonNumber);
						}
						else{
			                personInfo = DataImportUtils.getPersonInfoFromNumber(ctx, servicePersonNumber);
			                if(personInfo == null){
			                	//服务顾问不存在
								message = String.format("【维修中心引入】车辆底盘号：%s 对应记录行，服务顾问%s(%s)不存在！", parentVIN, servicePersonName,servicePersonNumber);
								throw new NotFoundException(message);
			                }
			                PersonList.put(servicePersonNumber, personInfo);
						}
		                belong.setPerson(personInfo);
		                
						belong.setIsDefault(isDefault == 1);
						belong.setServiceDate(serviceDate);						

						setServicePerson(ctx, rs, belong);
						
						int existIndex= -1;
						for(int i = 0; i< vehicle.getBelong().size(); i++){
							VehicleBelongInfo info = vehicle.getBelong().get(i);
							if(info.getOrgUnit().equals(belong.getOrgUnit())){
								existIndex = i;
							}
						}
						if(existIndex>=0){
							vehicle.getBelong().set(existIndex, belong);
						}
						else{
							vehicle.getBelong().add(belong);
						}
						
					}
					catch(NotFoundException e){
						e.printStackTrace();
						errorCount ++;
						throw e;					
					}
					catch (Exception e) {
						e.printStackTrace();
						message = String.format("【维修中心引入】车辆底盘号：%s 对应记录行，发生未知异常：%s", parentVIN, e.getLocalizedMessage());
						errorCount ++;
						throw new Exception(message);					
					}

					totalCount ++;
				}//while(rs.next())
			} catch (BOSException e) {				
				e.printStackTrace();
				message = String.format("【维修中心引入】车辆底盘号：%s 对应记录行引入时，发生未知异常：%s", parentVIN, e.getLocalizedMessage());
				throw new BOSException(message);
				
			}
			catch (SQLException e) {
				e.printStackTrace();
				message = String.format("【维修中心引入】车辆底盘号：%s 对应记录行引入时，发生未知异常：%s", parentVIN, e.getLocalizedMessage());
				throw new SQLException(message);
			} 
		}//if(rs != null)
	}
	
	public void checkCustomer(CustomerInfo info, Context ctx)
	    throws EASBizException
	{
	    if(UIRuleUtil.isNull(info.getName()))
	        throw new EASBizException(EASBizException.CHECKBLANK, new Object[] {
	            "\u540D\u79F0"
	        });
	    if(UIRuleUtil.isNull(info.getNumber()))
	        throw new EASBizException(EASBizException.CHECKBLANK, new Object[] {
	            "\u7F16\u7801"
	        });
	    if(UIRuleUtil.isNull(info.getCustType()))
	        throw new EASBizException(EASBizException.CHECKBLANK, new Object[] {
	            "\u5BA2\u6237\u7C7B\u578B"
	        });
	    if(UIRuleUtil.isNull(info.getCustClass()))
	        throw new EASBizException(EASBizException.CHECKBLANK, new Object[] {
	            "\u5BA2\u6237\u7C7B\u522B"
	        });
	    if(UIRuleUtil.isNull(info.getCustAttribute()))
	        throw new EASBizException(EASBizException.CHECKBLANK, new Object[] {
	            "\u81EA\u6709/\u5916\u6765"
	        });
	    if(UIRuleUtil.isNull(info.getFirstDate()))
	        throw new EASBizException(EASBizException.CHECKBLANK, new Object[] {
	            "\u9996\u6B21\u6765\u8BBF\u65E5\u671F"
	        });
	    if(UIRuleUtil.isNull(info.getPhone()))
	        throw new EASBizException(EASBizException.CHECKBLANK, new Object[] {
	            "\u4E3B\u8054\u7CFB\u65B9\u5F0F"
	        });
	    if(!CustomerClassifyEnum.POTENTIAL.equals(info.getCustClass()) && UIRuleUtil.isNull(info.getStdCustomerGroup()))
	        throw new EASBizException(EASBizException.CHECKBLANK, new Object[] {
	            "\u8D22\u52A1\u5BA2\u6237\u5206\u7C7B"
	        });
	    else
	        return;
	}
	
	private VehicleBelongInfo getVehicleBelongInfoFrom(Context ctx, String parentId, String orgUnitNumber){
		try
		{	
			FilterInfo filter = new FilterInfo();
	        FilterItemCollection fic = filter.getFilterItems();
	        fic.add(new FilterItemInfo("PARENT.ID", parentId, CompareType.EQUALS));
	        fic.add(new FilterItemInfo("ORGUNIT.NUMBER", orgUnitNumber, CompareType.EQUALS));
	        EntityViewInfo evi = new EntityViewInfo();
	        evi.setFilter(filter);
	        VehicleBelongCollection collection = VehicleBelongFactory.getLocalInstance(ctx).getVehicleBelongCollection(evi);
			if (collection != null && collection.size() > 0)
				return collection.get(0);
		}
		catch (BOSException e)
		{
			logger.error(String.format("获取车辆所属维修中心资料出现未知错误：%s", e.getLocalizedMessage()));
		}
		return new VehicleBelongInfo();
	}
	
	private void setOrgUnit(Context ctx, IRowSet rs, VehicleBelongInfo belongInfo, VehicleInfo vehicleInfo)
	    throws Exception
	{
	    int isDefault = rs.getInt("FIsDefault");
	    String orgUnitNumber = rs.getString("FServiceOrgUnit_number");
	    
	    if(!StringUtils.isEmpty(orgUnitNumber))
	    {
	            AdminOrgUnitInfo orgUnitInfo = null;
	            orgUnitInfo = CommonUtil.getAdminOrgUnitWithNumer(ctx, orgUnitNumber);
	            if(orgUnitInfo != null)
	            {
	                belongInfo.setOrgUnit(orgUnitInfo);
	                //if(isDefault == 1)
	                    //ckOnlyIsDefalt(ctx, orgUnitInfo, vehicleInfo);
	                belongInfo.setId(getVehicleBelongId(ctx, orgUnitInfo, vehicleInfo));
	            } else
	            {
	                throw new DataImportException(DataImportException.ORGUNIT_NOT_FIND, new String[] {
	                    orgUnitNumber
	                });
	            }
	    }
	}
	
	private CtrlUnitInfo getCtrlUnitInfoWithName(Context ctx, String name) throws BOSException
	{
	    if(!StringUtils.isEmpty(name))
	    {
	        FilterInfo filter = new FilterInfo();
	        FilterItemCollection fic = filter.getFilterItems();
	        fic.add(new FilterItemInfo("name", name, CompareType.EQUALS));
	        EntityViewInfo evi = new EntityViewInfo();
	        evi.setFilter(filter);
	        AdminOrgUnitInfo orgUnitInfo = null;
	        AdminOrgUnitCollection orgUnitCollection = AdminOrgUnitFactory.getLocalInstance(ctx).getAdminOrgUnitCollection(evi);
	        if(orgUnitCollection != null && orgUnitCollection.size()>0){
	        	orgUnitInfo = orgUnitCollection.get(0);
	        }
	        if(orgUnitInfo != null){
	        	return orgUnitInfo.getCU();
	        }
	    }
	    return null;
	}
	
	private BOSUuid getVehicleBelongId(Context ctx, AdminOrgUnitInfo orgUnitInfo, VehicleInfo vehicleInfo)
	    throws TaskExternalException, BOSException
	{
	    if(orgUnitInfo == null || vehicleInfo == null || vehicleInfo.getId()== null)
	        return null;
	    IVehicleBelong vehicleBelong = VehicleBelongFactory.getLocalInstance(ctx);
	    VehicleBelongCollection vehicleBelongCol = vehicleBelong.getVehicleBelongCollection((new StringBuilder()).append("where parent = '").append(vehicleInfo.getId()).append("' and orgUnit ='").append(orgUnitInfo.getId()).append("'").toString());
	    if(vehicleBelongCol == null || vehicleBelongCol.size() < 1)
	        return null;
	    else
	        return vehicleBelongCol.get(0).getId();
	}
	
	private void ckOnlyIsDefalt(Context ctx, AdminOrgUnitInfo orgUnitInfo, VehicleInfo vehicleInfo)
	    throws BOSException, TaskExternalException, DataImportException
	{
	    if(orgUnitInfo == null || vehicleInfo == null || vehicleInfo.getId()== null)
	        return;
	    IVehicleBelong vehicleBelong = VehicleBelongFactory.getLocalInstance(ctx);
	    VehicleBelongCollection vehicleBelongCol = vehicleBelong.getVehicleBelongCollection((new StringBuilder()).append("where parent = '").append(vehicleInfo.getId()).append("' and orgUnit !='").append(orgUnitInfo.getId()).append("' and isDefault=1").toString());
	    if(vehicleBelongCol != null && vehicleBelongCol.size() > 0)
	        throw new DataImportException(DataImportException.ISDEFALSE_CANNT_MORE);
	    else
	        return;
	}
	
	private void setServicePerson(Context ctx, IRowSet rs, VehicleBelongInfo belongInfo)
	    throws Exception
	{
		String servicePersonNumber = rs.getString("FServicePerson_number");
	    if(!StringUtils.isEmpty(servicePersonNumber))
	    {
            com.kingdee.eas.basedata.person.PersonInfo personInfo = null;
            personInfo = CommonUtil.getPersonWithNumer(ctx, servicePersonNumber);
            if(personInfo != null)
                belongInfo.setPerson(personInfo);
            else
                throw new DataImportException(DataImportException.SERVICEPERSON_NOT_FIND, new String[] {
                    servicePersonNumber
                });
	    }
	}
	
	protected VehicleInfo getVehicleInfo(Context ctx, IRowSet rs, VehicleInfo vehicleInfo)
    	throws Exception
	{
	    IVehicle vehicle = VehicleFactory.getLocalInstance(ctx);
	    String source = rs.getString("FSource");
	    String vin = rs.getString("FGuaranteeNum");//FVIN
	    String plateNum = rs.getString("FInspectionNum");//FPlateNum
	    if(StringUtils.isEmpty(source))
	        throw new DataImportException(DataImportException.SOURCE_CANNT_NULL);
	    if("2".equals(source))
	    {
	        if(StringUtils.isEmpty(plateNum))
	            throw new DataImportException(DataImportException.PLATENUM_CANNT_NULL);
	    } else
	    {
	        if(StringUtils.isEmpty(vin))
	            throw new DataImportException(DataImportException.VIN_NOT_NULL);
	        if(!isVin(vin.trim()))
	            throw new DataImportException(DataImportException.VIN_NOT_CORRECT);
	    }
	    if(!StringUtils.isEmpty(source))
	    {
	        if("2".equals(source))
	        {
	            if(!StringUtils.isEmpty(plateNum) && plateNum.trim().length() > 0)
	            {
	                try
	                {
	                    VehicleCollection collection = vehicle.getVehicleCollection((new StringBuilder()).append("where plateNum ='").append(plateNum.trim()).append("'").toString());
	                    if(collection != null && collection.size() > 0)
	                        vehicleInfo = vehicle.getVehicleInfo(new ObjectUuidPK(collection.get(0).getId()));
	                }
	                catch(Exception e) {
	                }
	            }
	        } 
	        else
	        {
	            if(!StringUtils.isEmpty(vin) && vin.trim().length() > 0)
	            {
	                try
	                {
	                    VehicleCollection collection = vehicle.getVehicleCollection((new StringBuilder()).append("where vIN ='").append(vin.trim()).append("'").toString());
	                    if(collection != null && collection.size() > 0)
	                        vehicleInfo = vehicle.getVehicleInfo(new ObjectUuidPK(collection.get(0).getId()));
	                }
	                catch(Exception e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    vehicleInfo.setVIN(vin);
	    vehicleInfo.setPlateNum(plateNum);
	    Integer sourceValue = new Integer(source);
	    vehicleInfo.setInt("source", sourceValue);
	    

   	 	/*AdminOrgUnitInfo orgUnitInfo = null;
	    String orgUnitNumber = rs.getString("FOrgUnit_number");
	    if(!StringUtils.isEmpty(orgUnitNumber)){
	            orgUnitInfo = CommonUtil.getAdminOrgUnitWithNumer(ctx, orgUnitNumber);
	    }
	    //如果当前公司不为空，设置当前组织为当前公司
        if(orgUnitInfo != null){
        	vehicleInfo.setCU(orgUnitInfo.getCU());
        }
        else
        {
        	CtrlUnitInfo cu = getCtrlUnitInfoWithName(ctx, ImportOrg);
        	if(cu == null){
        		cu = (CtrlUnitInfo)ctx.get(OrgType.ControlUnit);
        	}
        	vehicleInfo.setCU(cu);
        }        */
        
	    int otherBrandVehicle = rs.getInt("FOtherBrandVehicle");
	    vehicleInfo.setOtherBrandVehicle(otherBrandVehicle == 1);
	    setOrgUnit(ctx, rs, vehicleInfo);
	    int isMortgage = rs.getInt("FIsMortgage");
	    vehicleInfo.setIsMortgage(isMortgage == 1);
		setCustomer(ctx, rs, vehicleInfo);
	    setOrderCustomer(ctx, rs, vehicleInfo);
        Date warrantyStartDate = rs.getDate("FWarrantyStartDate");
        vehicleInfo.setWarrantyStartDate(warrantyStartDate);
        Date plateDate = rs.getDate("FPlateDate");
        vehicleInfo.setPlateDate(plateDate);
        
        String vehicleId = "";
        if(vehicleInfo.getId()!=null) vehicleId = vehicleInfo.getId().toString();
        if(!StringUtils.isEmpty(vehicleId)){
	        FilterInfo filter = new FilterInfo();
	        FilterItemCollection fic = filter.getFilterItems();
	        fic.add(new FilterItemInfo("parent.id", vehicleId, CompareType.EQUALS));
	        EntityViewInfo evi = new EntityViewInfo();
	        evi.setFilter(filter);
	        VehicleBelongCollection belongs = VehicleBelongFactory.getLocalInstance(ctx).getVehicleBelongCollection(evi);
	        if(belongs != null && belongs.size() != 0)
	        {
	        	vehicleInfo.getBelong().addCollection(belongs);
	        }
        }
	    ckOtherBrandData(ctx, rs);	    
	    
	    List list = new ArrayList();
	    list = isVehicleExistByVIN(ctx, vehicleInfo, list);
	    list = isVehicleExistByPlateNum(ctx, vehicleInfo, list);
	    list = isVehicleExistByEngineNum(ctx, vehicleInfo, list);
	    list = isVehicleExistByNumber(ctx, vehicleInfo, list);
	    if(list.size() > 0)
	    {
	        throw new DataImportException(DataImportException.VEHICLE_NOT_SAME, new Object[] {
	            list.toString()
	        });
	    } else
	    {
	        vehicleInfo.setVehicleCreateType(VehicleCreateTypeEnum.MANUAL);
	        return vehicleInfo;
	    }
	}
	
	public boolean isVin(String vin)
    {
        if(StringUtils.isEmpty(vin))
        {
            return false;
        } else
        {
            String regex = "[a-zA-Z0-9]{0,17}";
            return vin.matches(regex);
        }
    }
	
	private void checkPlateNumValite(Context ctx, VehicleInfo info)
	    throws BOSException, EASBizException
	{
	    if(info == null || StringUtils.isEmpty(info.getPlateNum()))
	        return;
	    String plateNum = info.getPlateNum().trim();
	    IParamControl ipc = ParamControlFactory.getLocalInstance(ctx);
	    String str = ipc.getParamValue(new ObjectUuidPK(info.getOrgUnit().getId()), "MC002");
	    if(StringUtils.isEmpty(str))
	        return;
	    StringBuffer expBuf = new StringBuffer();
	    for(int i = 0; i < str.length(); i++)
	    {
	        for(int j = 0; j < plateNum.length(); j++)
	            if(plateNum.charAt(j) == str.charAt(i))
	                expBuf.append(plateNum.charAt(j));
	
	    }
	
	    if(expBuf != null && expBuf.length() > 0)
	        throw new BdmPbdException(BdmPbdException.PLATENUM_NOT_CORRECT, new Object[] {
	            expBuf.toString()
	        });
	    else
	        return;
	}
		
	public static String getMegerString(String vIN, String plateNum)
    {
        if(vIN == null)
            vIN = "";
        if(plateNum == null)
            plateNum = "";
        return (new StringBuilder()).append(vIN).append("/").append(plateNum).toString();
    }
	
	private void setMainUser(Context ctx, String phone, VehicleInfo vehicleInfo)
	    throws DataImportException
	{
	    if(!StringUtils.isEmpty(phone))
	    {
	            ContactPersonInfo contactPersonInfo = null;
	            try
	            {
	                contactPersonInfo = CommonUtil.getContactPersonInfoWithNumer(ctx, phone);
	            }
	            catch(Exception e)
	            {
	                e.printStackTrace();
	            }
	            if(contactPersonInfo != null)
	                vehicleInfo.setMainUser(contactPersonInfo);
	            else
	                throw new DataImportException(DataImportException.CUSTOMER_NOT_FIND, new String[] {
	                		phone
	                });
	    }
	}
	
	private void setOrgUnit(Context ctx, IRowSet rs, VehicleInfo vehicleInfo) throws Exception
	{
	    //OrgUnitInfo unitInfo = (OrgUnitInfo)ctx.get("CurOU");
	    String orgUnitNumber = rs.getString("FOrgUnit_number");
	    if(!StringUtils.isEmpty(orgUnitNumber) && !orgUnitNumber.trim().equals(""))
	    {
            AdminOrgUnitInfo orgUnitInfo = null;
            orgUnitInfo = CommonUtil.getAdminOrgUnitWithNumer(ctx, orgUnitNumber);
            if(orgUnitInfo != null && orgUnitInfo.getId() != null)
            {
                /*if(unitInfo == null || unitInfo.getId() == null || !unitInfo.getId().equals(orgUnitInfo.getId()))
                    throw new DataImportException(DataImportException.ORG_NOT_RIGHT, new String[] {
                        orgUnitNumber
                    });*/
                vehicleInfo.setOrgUnit(orgUnitInfo);
            } else
            {
                throw new DataImportException(DataImportException.ORGUNIT_NOT_FIND, new String[] {
                    orgUnitNumber
                });
            }
	    }
	}
	
	private void setCustomer(Context ctx, IRowSet rs, VehicleInfo vehicleInfo)
	    throws Exception
	{
		String customerNumber = rs.getString("FCustomer_number");
		String customerName = rs.getString("FCustomer_name_l2");
		if(vehicleInfo.getId()!=null && customerName.trim().equals(".")){
			//已存在车辆，无名称客户无需重新设置，否则多次重复导入时会变更无名称客户
			return;
		}
		if(!StringUtils.isEmpty(customerNumber) && !customerNumber.trim().equals(""))
	    {
	            CustomerInfo customerInfo = null;
	            customerInfo = CommonUtil.getCustomerWithNumer(ctx, customerNumber);
	            if(customerInfo != null)
	                vehicleInfo.setCustomer(customerInfo);
	            else
	                throw new DataImportException(DataImportException.CUSTOMER_NOT_FIND, new String[] {
	                    customerNumber
	                });
	    }
	}
	
	private void setOrderCustomer(Context ctx, IRowSet rs, VehicleInfo vehicleInfo)
	    throws Exception
	{
		String customerNumber = rs.getString("FOrderCustomer_number");
		String customerName = rs.getString("FCustomer_name_l2");
		if(vehicleInfo.getId()!=null && customerName.trim().equals(".")){
			//已存在车辆，无名称客户无需重新设置，否则多次重复导入时会变更无名称客户
			return;
		}
		if(!StringUtils.isEmpty(customerNumber) && !customerNumber.trim().equals(""))
	    {
	            CustomerInfo customerInfo = null;
	            customerInfo = CommonUtil.getCustomerWithNumer(ctx, customerNumber);
	            if(customerInfo != null)
	                vehicleInfo.setOrderCustomer(customerInfo);
	            else
	                throw new DataImportException(DataImportException.CUSTOMER_NOT_FIND, new String[] {
	                    customerNumber
	                });
	    }
	}

	
	private void setModel(Context ctx, IRowSet rs, VehicleInfo vehicleInfo)
	    throws EASBizException, BOSException, SQLException
	{
        String modelNumber = rs.getString("FModel_number").trim();
        if(!StringUtils.isEmpty(modelNumber))
        {
            ModelInfo modelInfo = null;
            try
            {
                modelInfo = CommonUtil.getModelWithNumer(ctx, modelNumber);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            if(modelInfo == null)
                throw new DataImportException(DataImportException.MODEL_NOT_FIND, new String[] {
                    modelNumber
                });
            vehicleInfo.setModel(modelInfo);
            SeriesInfo seriesInfo = SeriesFactory.getLocalInstance(ctx).getSeriesInfo(new ObjectUuidPK(modelInfo.getParent().getId()));
            if(seriesInfo != null && seriesInfo.getBrand() != null && seriesInfo.getBrand().getId() != null)
            {
                vehicleInfo.setSeries(seriesInfo);
                BrandInfo brandInfo = BrandFactory.getLocalInstance(ctx).getBrandInfo(new ObjectUuidPK(seriesInfo.getBrand().getId()));
                OrgUnitInfo unitInfo = (OrgUnitInfo)ctx.get("CurOU");
                BrandCollection brandCol = getBrandsByOrg(ctx, unitInfo);
                Set brandSet = new HashSet();
                int i = 0;
                for(int num = brandCol.size(); i < num; i++)
                    brandSet.add(brandCol.get(i).getId());

                if(!brandSet.contains(brandInfo.getId()))
                    throw new DataImportException(DataImportException.MODEL_NOTEXIST_ORGUNIT, new String[] {
                        modelNumber
                    });
                if(brandInfo != null)
                    vehicleInfo.setBrand(brandInfo);
            }
        } else
        {
        	throw new DataImportException(DataImportException.FMODEL_NUMBER_NOT_NULL);
        }
	}
	
	private BrandCollection getBrandsByOrg(Context ctx, OrgUnitInfo currentOrgUnit)
	    throws BOSException, EASBizException
	{
	    if(currentOrgUnit == null)
	        currentOrgUnit = SysContext.getSysContext().getCurrentOrgUnit();
	    
	    FilterInfo filter = getAllBrandsFilter(ctx, currentOrgUnit);
	    EntityViewInfo evi = new EntityViewInfo();
	    evi.setFilter(filter);
	    BrandCollection brands = BrandFactory.getLocalInstance(ctx).getBrandCollection(evi);
	    if(brands.size() == 0)
	        return null;
	    BrandCollection rtnBrands = new BrandCollection();
	    for(int i = 0; i < brands.size(); i++)
	    {
	        BrandInfo brandInfo = brands.get(i);
	        if(!brandInfo.isIsGroup())
	        {
	            rtnBrands.add(brandInfo);
	            continue;
	        }
	        BrandDetailCollection brandDetailCol = brandInfo.getDetail();
	        for(int j = 0; j < brandDetailCol.size(); j++)
	        {
	            BrandDetailInfo brandDetailInfo = brandDetailCol.get(j);
	            rtnBrands.add(brandDetailInfo.getDetailBrand());
	        }
	
	    }
	
	    return rtnBrands;
	}
	
	public FilterInfo getAllBrandsFilter(Context ctx, OrgUnitInfo currentOrgUnit)
	    throws BOSException, EASBizException
	{
	    if(currentOrgUnit == null)
	        currentOrgUnit = SysContext.getSysContext().getCurrentOrgUnit();
	    FilterInfo filter = getCurrentOrgIncludeSubOrgFilter(ctx, currentOrgUnit);
	    if(filter != null)
	    {
	        EntityViewInfo brandEntityViewInfo = new EntityViewInfo();
	        brandEntityViewInfo.setFilter(filter);
	        BrandCompanyCollection colls = BrandCompanyFactory.getLocalInstance(ctx).getBrandCompanyCollection(brandEntityViewInfo);
	        Set idSet = new HashSet();
	        for(int i = 0; i < colls.size(); i++)
	            idSet.add(colls.get(i).getParent().getId().toString());
	
	        if(idSet.size() > 0)
	        {
	            filter = new FilterInfo();
	            filter.getFilterItems().add(new FilterItemInfo("id", idSet, CompareType.INCLUDE));
	        }
	    }
	    return filter;
	}
	
	public FilterInfo getCurrentOrgIncludeSubOrgFilter(Context ctx, OrgUnitInfo currentOrg)
	    throws BOSException, EASBizException
	{
	    if(currentOrg == null)
	        currentOrg = SysContext.getSysContext().getCurrentOrgUnit();
	    IFullOrgUnit iou = FullOrgUnitFactory.getLocalInstance(ctx);
	    FullOrgUnitInfo info = iou.getFullOrgUnitInfo(new ObjectUuidPK(currentOrg.getId()));
	    EntityViewInfo evi = new EntityViewInfo();
	    FilterInfo filterInfo = new FilterInfo();
	    filterInfo.getFilterItems().add(new FilterItemInfo("longNumber", (new StringBuilder()).append(info.getLongNumber()).append("%").toString(), CompareType.LIKE));
	    evi.setFilter(filterInfo);
	    FullOrgUnitCollection fullOrgUnits = iou.getFullOrgUnitCollection(evi);
	    Set idSet = new HashSet();
	    for(int i = 0; i < fullOrgUnits.size(); i++)
	        idSet.add(fullOrgUnits.get(i).getId().toString());
	
	    FilterInfo filter = new FilterInfo();
	    if(idSet.size() > 0)
	        filter.getFilterItems().add(new FilterItemInfo("OrgUnit.id", idSet, CompareType.INCLUDE));
	    return filter;
	}

	private void ckOtherBrandData(Context ctx, IRowSet rs)
    	throws DataImportException, SQLException
	{
	    String model = rs.getString("FModel_number");
	    //String color = rs.getString("FColor_number");
	    //String inner = rs.getString("FInner_number");
	    //String desc = rs.getString("FOptionItemCombine_Desc");
	    String vehicleRemark = "";//rs.getString("FVehicleRemark");
	    int otherBrandVehicle = rs.getInt("FOtherBrandVehicle");	    
	    if(otherBrandVehicle == 0)
        {
            if(StringUtils.isEmpty(model))
                throw new DataImportException(DataImportException.MODEL_CANNT_NULL);
            if(!StringUtils.isEmpty(vehicleRemark))
                throw new DataImportException(DataImportException.VEHICLEREMARK_CANNT_HASDATA);
        } else
        {
            if(StringUtils.isEmpty(vehicleRemark))
                throw new DataImportException(DataImportException.VEHICLEREMARK_CANNT_NULL);
            if(!StringUtils.isEmpty(model))
                throw new DataImportException(DataImportException.MODEL_CANNT_HASDATA);
        }
	}
	
	protected List isVehicleExistByVIN(Context ctx, VehicleInfo info, List list)
	    throws BOSException, BdmPbdException, DataImportException
	{
	    if(info == null)
	        throw new IllegalArgumentException();
	    FilterInfo filter = new FilterInfo();
	    FilterItemCollection fic = filter.getFilterItems();
	    if(!StringUtils.isEmpty(info.getVIN()))
	    {
	        fic.add(new FilterItemInfo("vIN", info.getVIN(), CompareType.EQUALS));
	        if(info.getId() != null)
	        {
	            fic.add(new FilterItemInfo("id", info.getId(), CompareType.NOTEQUALS));
	            filter.setMaskString("#0 AND #1");
	        }
	        EntityViewInfo evi = new EntityViewInfo();
	        evi.setFilter(filter);
	        VehicleCollection vehicleCollection = VehicleFactory.getLocalInstance(ctx).getVehicleCollection(evi);
	        if(vehicleCollection != null && vehicleCollection.size() > 0)
	            list.add((new StringBuilder()).append("\u5E95\u76D8\u53F7\uFF1A").append(info.getVIN()).toString());
	    }
	    return list;
	}
	
	protected List isVehicleExistByPlateNum(Context ctx, VehicleInfo info, List list)
	    throws BOSException, BdmPbdException
	{
	    if(info == null)
	        throw new IllegalArgumentException();
	    FilterInfo filter = new FilterInfo();
	    FilterItemCollection fic = filter.getFilterItems();
	    if(!StringUtils.isEmpty(info.getPlateNum()))
	    {
	        fic.add(new FilterItemInfo("plateNum", info.getPlateNum(), CompareType.EQUALS));
	        if(info.getVIN() != null)
	        {
	            fic.add(new FilterItemInfo("vIN", info.getVIN(), CompareType.NOTEQUALS));
	            filter.setMaskString("#0 AND #1");
	        }
	        EntityViewInfo evi = new EntityViewInfo();
	        evi.setFilter(filter);
	        VehicleCollection vehicleCollection = VehicleFactory.getLocalInstance(ctx).getVehicleCollection(evi);
	        if(vehicleCollection != null && vehicleCollection.size() > 0)
	            list.add((new StringBuilder()).append("\u8F66\u724C\u53F7\uFF1A").append(info.getPlateNum()).toString());
	    }
	    return list;
	}
	
	protected List isVehicleExistByEngineNum(Context ctx, VehicleInfo info, List list)
	    throws BOSException, BdmPbdException
	{
	    if(info == null)
	        throw new IllegalArgumentException();
	    FilterInfo filter = new FilterInfo();
	    FilterItemCollection fic = filter.getFilterItems();
	    if(!StringUtils.isEmpty(info.getEngineNum()))
	    {
	        fic.add(new FilterItemInfo("engineNum", info.getEngineNum(), CompareType.EQUALS));
	        if(info.getVIN() != null)
	        {
	            fic.add(new FilterItemInfo("vIN", info.getVIN(), CompareType.NOTEQUALS));
	            filter.setMaskString("#0 AND #1");
	        }
	        EntityViewInfo evi = new EntityViewInfo();
	        evi.setFilter(filter);
	        VehicleCollection vehicleCollection = VehicleFactory.getLocalInstance(ctx).getVehicleCollection(evi);
	        if(vehicleCollection != null && vehicleCollection.size() > 0)
	            list.add((new StringBuilder()).append("\u53D1\u52A8\u673A\u53F7\uFF1A").append(info.getEngineNum()).toString());
	    }
	    return list;
	}
	
	protected List isVehicleExistByNumber(Context ctx, VehicleInfo info, List list)
	    throws BOSException, BdmPbdException
	{
	    if(info == null)
	        throw new IllegalArgumentException();
	    FilterInfo filter = new FilterInfo();
	    FilterItemCollection fic = filter.getFilterItems();
	    if(!StringUtils.isEmpty(info.getNumber()))
	    {
	        fic.add(new FilterItemInfo("number", info.getNumber(), CompareType.EQUALS));
	        if(info.getVIN() != null)
	        {
	            fic.add(new FilterItemInfo("vIN", info.getVIN(), CompareType.NOTEQUALS));
	            filter.setMaskString("#0 AND #1");
	        }
	        EntityViewInfo evi = new EntityViewInfo();
	        evi.setFilter(filter);
	        VehicleCollection vehicleCollection = VehicleFactory.getLocalInstance(ctx).getVehicleCollection(evi);
	        if(vehicleCollection != null && vehicleCollection.size() > 0)
	            list.add((new StringBuilder()).append("\u5185\u90E8\u7F16\u7801\uFF1A").append(info.getNumber()).toString());
	    }
	    return list;
	}

	private void ContactPersonUpdate(Context ctx, CustomerInfo customer) throws EASBizException, BOSException{
		IContactPerson factory = ContactPersonFactory.getLocalInstance(ctx);
		ContactPersonInfo info = new ContactPersonInfo();
		String name = customer.getName();
		String phone = customer.getPhone();
		String customerId = "";
		if(customer.getId()!= null) customerId = customer.getId().toString();
		info = getContactPerson(ctx, customerId, phone);
		info.setName(name);
		info.setContactMobile(customer.getPhone());
		info.setAddress(customer.getAddress());
		info.setCU(customer.getCU());
		info.setCustomer(customer);
		info.setIsMainContact(true);
		//info.setIsMainUser(true);
		BOSUuid existId = info.getId();
		
		if(existId != null && !StringUtil.isEmptyString(existId.toString())){
			info.setLastUpdateTime(customer.getLastUpdateTime());
			info.setLastUpdateUser(customer.getLastUpdateUser());
			factory.update(new ObjectUuidPK(existId), info);
		}
		else{
			info.setCreateTime(customer.getCreateTime());
			info.setCreator(customer.getCreator());
			factory.addnew(info);
		}
		
	}
	
	private ContactPersonInfo getContactPerson(Context ctx, String customerId, String phone){
		try
		{
			ContactPersonCollection collection = ContactPersonFactory.getLocalInstance(ctx).getContactPersonCollection("where customer.id = '"+ customerId +"' and contactmobile ='" + phone + "'");
			if (collection != null && collection.size() > 0)
				return collection.get(0);
		}
		catch (BOSException e)
		{
			logger.error(String.format("获取客户联系人资料出现未知错误：%s", e.getLocalizedMessage()));
		}
		return new ContactPersonInfo();
	}
	
	private int nameDupCheck(CustomerInfo info, Context ctx)
	    throws BOSException, EASBizException, Exception, CustomerSupplierException
	{
	    int i = 0;
	    if(!StringUtils.isEmpty(info.getName()))
	    {
	        FilterInfo filter = new FilterInfo();
	        filter.getFilterItems().add(new FilterItemInfo("name", info.getName().trim()));
	        EntityViewInfo viewInfo = new EntityViewInfo();
	        ICustomer iCustomer = CustomerFactory.getLocalInstance(ctx);
	        viewInfo.setFilter(filter);
	        CustomerCollection customerCollection = iCustomer.getCustomerCollection(viewInfo);
	        if(customerCollection != null && customerCollection.size() > 0)
	            i = customerCollection.size();
	    }
	    return i;
	}
	
	private CustomerInfo getCustomerInfoFromNumber(Context ctx, String number){
		try
		{	
			FilterInfo filter = new FilterInfo();
	        FilterItemCollection fic = filter.getFilterItems();
	        fic.add(new FilterItemInfo("number", number, CompareType.EQUALS));
	        EntityViewInfo evi = new EntityViewInfo();
	        evi.getSelector().add(new SelectorItemInfo("*"));
	        evi.getSelector().add(new SelectorItemInfo("belong.*"));
	        evi.getSelector().add(new SelectorItemInfo("belong.orgunit.*"));
	        evi.getSelector().add(new SelectorItemInfo("belong.brand.*"));
	        evi.getSelector().add(new SelectorItemInfo("belong.person.*"));
	        evi.setFilter(filter);
			CustomerCollection collection = CustomerFactory.getLocalInstance(ctx).getCustomerCollection(evi);
			if (collection != null && collection.size() > 0)
				return collection.get(0);
		}
		catch (BOSException e)
		{
			String message = String.format("获取客户档案出现未知错误: %s", e.getLocalizedMessage());
			logger.error(message);
			Log(ctx, currentUser, ImportStateEnum.Failure, message);
		}
		return new CustomerInfo();
	}
	
/*	private String getModelIdFromNumber(Context ctx, String number){
		String modelId = null;
		ModelInfo model = getModelInfoFromNumber(ctx, number);
		if(model != null) modelId = model.getId().toString();
		return modelId;
	}*/
	
	private ModelInfo getModelInfoFromNumber(Context ctx, String number){
		try
		{
			ModelCollection collection = ModelFactory.getLocalInstance(ctx).getModelCollection("where number ='" + number + "'");
			if (collection != null && collection.size() > 0)
				return collection.get(0);
		}
		catch (BOSException e)
		{
			logger.error(String.format("获取车型资料出现未知错误：%s", e.getLocalizedMessage()));
		}
		return new ModelInfo();
	}
	
	private MaterialGroupInfo getMaterialGroupInfo(Context ctx, String propertyName, String propertyValue) throws Exception{
		if(MaterialGroupList.containsKey(propertyValue)){
			return (MaterialGroupInfo)MaterialGroupList.get(propertyValue);
		}
		FilterInfo filter = new FilterInfo();
        FilterItemCollection fic = filter.getFilterItems();
        fic.add(new FilterItemInfo(propertyName, propertyValue, CompareType.EQUALS));
        EntityViewInfo evi = new EntityViewInfo();
        evi.setFilter(filter);
        MaterialGroupCollection groupCollection = MaterialGroupFactory.getLocalInstance(ctx).getMaterialGroupCollection(evi);
        if(groupCollection != null && groupCollection.size() != 0)
        {
        	MaterialGroupList.put(propertyValue, groupCollection.get(0));
            return groupCollection.get(0);
        } else
        {
            return null;
        }
	}
	
	private MeasureUnitInfo getMeasureUnitInfo(Context ctx, String propertyName, String propertyValue) throws Exception{
		if(MeasureUnitList.containsKey(propertyValue)){
			return (MeasureUnitInfo)MeasureUnitList.get(propertyValue);
		}
		StringBuilder builder = new StringBuilder();
		builder.append("where ").append(propertyName).append(" ='").append(propertyValue).append("'");
		String oql = builder.toString();
		MeasureUnitCollection unitCollection = MeasureUnitFactory.getLocalInstance(ctx).getMeasureUnitCollection(oql);
        if(unitCollection != null && unitCollection.size() != 0)
        {
        	MeasureUnitList.put(propertyValue, unitCollection.get(0));
            return unitCollection.get(0);
        } 
        else
        {
        	//计量单位不存在
            throw new Exception(String.format("计量单位:%s, 不存在。",propertyValue));
        }
	}
	
	private SeriesInfo getSeriesInfo(Context ctx, String propertyName, String propertyValue) throws Exception{
		if(SeriesList.containsKey(propertyValue)){
			return (SeriesInfo)SeriesList.get(propertyValue);
		}
		StringBuilder builder = new StringBuilder();
		builder.append("where ").append(propertyName).append(" ='").append(propertyValue).append("'");
		String oql = builder.toString();
		SeriesInfo seriesInfo = SeriesFactory.getLocalInstance(ctx).getSeriesInfo(oql);
	    if(seriesInfo == null){
          throw new Exception(String.format("车系:%s, 不存在。",propertyValue));
	    }
	    SeriesList.put(propertyValue, seriesInfo);
	    return seriesInfo;
	}
	
	private IRowSet getVehicleRepaireCenter(Context ctx, String vIN){
		String sql = String.format("select * from CT_SYN_VehicleRepairCenter where FTryNums < 3 AND FParent_vIN='%s' order by FExcelFilename asc, FImportOrg asc", vIN);
		//logger.info("sql = " + sql);
		try {
			return DbUtil.executeQuery(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			logger.error(String.format("从临时表获取车辆所属维修中心资料失败: %s", e.getLocalizedMessage()));
		}
		return null;
	}
	
	private void removeVehicleRepaireCenter(Context ctx, String vIN){
		String sql = String.format("delete from CT_SYN_VehicleRepairCenter where FParent_vIN = '%s' ", vIN);
		try {
			DbUtil.execute(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			logger.error(String.format("从临时表删除车辆所属维修中心资料失败: %s", e.getLocalizedMessage()));
		}
	}
	
	private void updateVehicleRepaireCenter(Context ctx, String vIN){
		String sql = String.format("update CT_SYN_VehicleRepairCenter set FTryNums = FTryNums+1 where FParent_vIN = '%s' ", vIN);
		try {
			DbUtil.execute(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			logger.error(String.format("从临时表更新车辆所属维修中心资料失败: %s", e.getLocalizedMessage()));
		}
	}
	
	private IRowSet getVehicleProfiles(Context ctx, int maxRecords){
		String sql = String.format("select top %s * from CT_SYN_VehicleProfiles where FTryNums < 3 order by FExcelFilename asc, FImportOrg asc", maxRecords);
		//logger.info("sql = " + sql);
		try {
			return DbUtil.executeQuery(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			logger.error(String.format("从临时表获取车辆档案资料失败: %s", e.getLocalizedMessage()));
		}
		return null;
	}
	
	private void removeVehicleProfiles(Context ctx, String vIN){
		String sql = String.format("delete from CT_SYN_VehicleProfiles where FVIN = '%s' ", vIN);
		try {
			DbUtil.execute(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			logger.error(String.format("从临时表删除车辆档案资料失败: %s", e.getLocalizedMessage()));
		}
	}
	
	private void updateVehicleProfiles(Context ctx, String vIN){
		String sql = String.format("update CT_SYN_VehicleProfiles set FTryNums = FTryNums+1 where FVIN = '%s' ", vIN);
		try {
			DbUtil.execute(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			logger.error(String.format("从临时表更新车辆档案资料失败: %s", e.getLocalizedMessage()));
		}
	}
	
	private IRowSet getCustProfiles(Context ctx, int maxRecords){
		String sql = String.format("select top %s * from CT_SYN_CustProfiles where FTryNums < 3 order by FExcelFilename asc, FImportOrg asc", maxRecords);
		//logger.info("sql = " + sql);
		try {
			return DbUtil.executeQuery(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			String message = String.format("从临时表获取客户档案资料失败: %s", e.getLocalizedMessage());
			logger.error(message);
			Log(ctx, currentUser, ImportStateEnum.Failure, message);
		}
		return null;
	}
	
	private void removeCustProfiles(Context ctx, String number){
		String sql = String.format("delete from CT_SYN_CustProfiles where FNumber = '%s' ", number);
		try {
			DbUtil.execute(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			String message = String.format("从临时表删除客户档案资料失败: %s", e.getLocalizedMessage());
			logger.error(message);
			Log(ctx, currentUser, ImportStateEnum.Failure, message);
		}
	}
	
	private void updateCustProfiles(Context ctx, String number){
		String sql = String.format("update CT_SYN_CustProfiles set FTryNums = FTryNums+1 where FNumber = '%s' ", number);
		try {
			DbUtil.execute(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			String message = String.format("从临时表更新客户档案资料失败: %s", e.getLocalizedMessage());
			logger.error(message);
			Log(ctx, currentUser, ImportStateEnum.Failure, message);
		}
	}
	
	private IRowSet getVehicleType(Context ctx, int maxRecords){
		String sql = String.format("select top %s * from CT_SYN_VehicleTypes where FTryNums < 3 order by FExcelFilename asc, FImportOrg asc", maxRecords);
		//logger.info("sql = " + sql);
		try {
			return DbUtil.executeQuery(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			logger.error(String.format("从临时表获取车型资料失败: %s", e.getLocalizedMessage()));
		}
		return null;
	}
	
	private void removeVehicleType(Context ctx, String number){
		String sql = String.format("delete from CT_SYN_VehicleTypes where FNumber = '%s' ", number);
		try {
			DbUtil.execute(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			logger.error(String.format("从临时表删除车型资料失败: %s", e.getLocalizedMessage()));
		}
	}
	
	private void updateVehicleType(Context ctx, String number){
		String sql = String.format("update CT_SYN_VehicleTypes set FTryNums = FTryNums+1 where FNumber = '%s' ", number);
		try {
			DbUtil.execute(ctx, sql);
			
		} catch (BOSException e) {
			//e.printStackTrace();
			logger.error(String.format("从临时表更新车型资料失败: %s", e.getLocalizedMessage()));
		}
	}
	
	private void Log(Context ctx, UserInfo importUser, ImportStateEnum importState, String message){

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date date = new Date();
			Time time = new Time(date.getTime());
			Timestamp logTime = new Timestamp(date.getTime());
			SyncLogInfo log = getSyncLog(ctx, ExcelFilename, ImportOrg);
			log.setImportState(importState);
			log.setExcelFilename(ExcelFilename);
			log.setImportOrg(ImportOrg);
			log.setDescription(message);
			
			SyncLogEntryInfo entry = new SyncLogEntryInfo();
			entry.setLogContent(message);
			entry.setLogTime(time);
			entry.setParent(log);
			log.getEntrys().add(entry);
			
			ISyncLog factory = SyncLogFactory.getLocalInstance(ctx);
			String existId = "";
			if(log.getId() != null) existId = log.getId().toString();
			if(StringUtil.isEmptyString(existId)){
				String number = String.format("LOG%s", format.format(date));
				log.setNumber(number);
				log.setCreateTime(logTime);
				log.setCreator(importUser);
				log.setBizDate(logTime);
				factory.addnew(log);
			}
			else
			{
				log.setLastUpdateTime(logTime);
				log.setLastUpdateUser(importUser);
				factory.update(new ObjectUuidPK(existId), log);
			}
		} catch (EASBizException e) {
			logger.error(String.format("记录引入日志失败: %s", e.getLocalizedMessage()));
		} catch (BOSException e) {
			logger.error(String.format("记录引入日志失败: %s", e.getLocalizedMessage()));
		}
	}
	
	private SyncLogInfo getSyncLog(Context ctx, String excelFilename, String importOrg){
		try
		{
			SyncLogCollection logs = SyncLogFactory.getLocalInstance(ctx).getSyncLogCollection(String.format("where excelfilename ='%s' and importorg = '%s' ", excelFilename, importOrg));
			if (logs != null && logs.size() > 0)
				return logs.get(0);
		}
		catch (BOSException e)
		{
			logger.error(String.format("出现未知错误：%s", e.getLocalizedMessage()));
		}
		return new SyncLogInfo();
	}
	
	protected String getNumberByCodingRule(Context ctx, IObjectValue caller)
	    throws BOSException, CodingRuleException, EASBizException
	{
	    String number = null;
	    ICodingRuleManager iCodingRuleManager = CodingRuleManagerFactory.getLocalInstance(ctx);
	    String orgId = iCodingRuleManager.getCurrentAppOUID(caller);
	    if(isCustomerUsedCodingRule(ctx, orgId))
	    {
	        if(orgId == null || orgId.trim().length() == 0)
	            orgId = "00000000-0000-0000-0000-000000000000CCE7AED4";
	        if(isCustomerUsedCodingRule(ctx, orgId))
	        {
	            ICustomer iCustomer = CustomerFactory.getLocalInstance(ctx);
	            CustomerInfo CustomerInfo = (CustomerInfo)caller;
	            number = iCustomer.readNewNumber(orgId, CustomerInfo);
	        }
	    }
	    return number;
	}
	
	private boolean isCustomerUsedCodingRule(Context ctx, String orgId)
	    throws CodingRuleException, EASBizException, BOSException
	{
	    if(isCustomerUsedCodingRule != null)
	    {
	        return isCustomerUsedCodingRule.booleanValue();
	    } else
	    {
	        ICodingRuleManager iCodingRuleManager = CodingRuleManagerFactory.getLocalInstance(ctx);
	        CustomerInfo info = new CustomerInfo();
	        boolean exist = iCodingRuleManager.isExist(info, orgId);
	        isCustomerUsedCodingRule = Boolean.valueOf(exist);
	        return exist;
	    }
	}
}