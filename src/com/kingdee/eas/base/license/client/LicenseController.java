package com.kingdee.eas.base.license.client;

import java.util.Map;

import com.kingdee.eas.base.license.ILicenseController;
import com.kingdee.eas.base.license.LicenseException;
import com.kingdee.eas.base.license.LicenseUserInfo;

public final class LicenseController
  implements ILicenseController
{
  public static LicenseController getInstance()
  {
    return new LicenseController();
  }

  public static LicenseController getInstance(String url) {
    return new LicenseController();
  }

  public LicenseController()
  {
  }

  public LicenseController(String serverUrl)
  {
  }

  public int requestLicense(LicenseUserInfo user, String uiClassName)
    throws LicenseException
  {
//    return LicenseClientCacheProxy.getInstance().requestLicense(user, uiClassName);
    return 1;
  }

  public int requestLicenseByUserAndSubSystem(LicenseUserInfo user, String moduleName)
    throws LicenseException
  {
//    return LicenseSrvAgentFactory.getRemoteInstance().requestLicenseByUserAndSubSystem(user, moduleName);
    return 1;
  }

  public void releaseLicenseBySessionIDAndSubSystem(String sessionID, String subSystem) throws LicenseException {
//    LicenseSrvAgentFactory.getRemoteInstance().releaseLicenseBySessionIDAndSubSystem(sessionID, subSystem);
  }

  public Map getLicenseClientCacheBaseInfo(String sessionID) throws LicenseException
  {
//    return LicenseClientCacheProxy.getInstance().getLicenseClientCacheBaseInfo(sessionID);
    return null;
  }

  public Map getUpdateLicenseClientCacheInfo(String sessionID)
    throws LicenseException
  {
//    return LicenseClientCacheProxy.getInstance().getUpdateLicenseClientCacheInfo(sessionID);
    return null;
  }

  public void releaseLicense(String sessionID, String packageName)
    throws LicenseException
  {
//    LicenseClientCacheProxy.getInstance().releaseLicense(sessionID, packageName);
  }

  public void releaseLicenseBySessionID(String sessionID)
    throws LicenseException
  {
//    try
//    {
//      LicenseSrvAgentFactory.getRemoteInstance().releaseLicenseBySessionID(sessionID);
//    }
//    catch (Exception ex) {
//      throw new LicenseException(ex);
//    }
  }

  public void reportActiveOperation(String sessionID, String packageName)
    throws LicenseException
  {
//    try
//    {
//      LicenseSrvAgentFactory.getRemoteInstance().reportActiveOperation(sessionID, packageName);
//    }
//    catch (Exception ex) {
//      throw new LicenseException(ex);
//    }
  }

  public void reportActiveModuleOperation(String sessionID, String moduleName)
    throws LicenseException
  {
//    try
//    {
//      LicenseSrvAgentFactory.getRemoteInstance().reportActiveModuleOperation(sessionID, moduleName);
//    }
//    catch (Exception ex) {
//      throw new LicenseException(ex);
//    }
  }

  public boolean isEasPackage(String packageName) throws LicenseException
  {
//    return LicenseClientCacheProxy.getInstance().isEasPackage(packageName);
    return true;
  }

  public String getModuleByPackage(String uiClassName)
    throws LicenseException
  {
    return LicenseClientCacheProxy.getInstance().getModuleByPackage(uiClassName);
  }

  public String getSubSystemNameByPackage(String uiClassName)
    throws LicenseException
  {
    return LicenseClientCacheProxy.getInstance().getSubSystemNameByPackage(uiClassName);
  }

  public String getGenuineNo() throws LicenseException
  {
    return LicenseSrvAgentFactory.getRemoteInstance().getGenuineNo();
  }

  public String getProductNo() throws LicenseException {
    return LicenseSrvAgentFactory.getRemoteInstance().getProductNo();
  }

  public int checkLicense(String fullClassName) throws LicenseException
  {
//    return LicenseClientCacheProxy.getInstance().checkLicense(fullClassName);
	  return 1;
  }
}