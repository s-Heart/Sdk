package uld.sdk.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义游戏 接口
 */
public class GameInterface implements Serializable {
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String password;
	private String intentAction;
	private int rechargeRate;
	private String rechargeUnit;

	private Map<String, String> strBundleMap = null;
	private Map<String, Integer> intBundleMap = null;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIntentAction() {
		return intentAction;
	}

	public void setIntentAction(String intentAction) {
		this.intentAction = intentAction;
	}

	public int getRechargeRate() {
		return rechargeRate;
	}

	public void setRechargeRate(int rechargeRate) {
		this.rechargeRate = rechargeRate;
	}

	public String getRechargeUnit() {
		return rechargeUnit;
	}

	public void setRechargeUnit(String rechargeUnit) {
		this.rechargeUnit = rechargeUnit;
	}

	public Map<String, String> getStrBundleMap() {
		if (strBundleMap == null) {
			strBundleMap = new HashMap<String, String>();
		}
		return strBundleMap;
	}

	public Map<String, Integer> getIntBundleMap() {
		if (intBundleMap == null) {
			intBundleMap = new HashMap<String, Integer>();
		}
		return intBundleMap;
	}

	public void putString(String key, String value) {
		getStrBundleMap().put(key, value);
	}

	public void putString(String key, Integer value) {
		getIntBundleMap().put(key, value);
	}
}
