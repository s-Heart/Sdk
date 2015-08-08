package uld.sdk.unite;

public class LogoutResult {
	private String channelUserId = null;
	private String channelUserName = null;

	private Boolean isLogout = false;
	private String logoutErrorMsg = null;

	private int channelId = 0;
	private String channelName = "";

	private String sign = null;

	/**
	 * @return the isLogout
	 */
	public Boolean getIsLogout() {
		return isLogout;
	}

	/**
	 * @param isLogin
	 *            the isLogin to set
	 */
	public void setIsLogout(Boolean isLogin) {
		this.isLogout = isLogin;
	}

	/**
	 * @return the loginErrorMsg
	 */
	public String getLogoutErrorMsg() {
		return logoutErrorMsg;
	}

	/**
	 * @param loginErrorMsg
	 *            the loginErrorMsg to set
	 */
	public void setLogoutErrorMsg(String loginErrorMsg) {
		this.logoutErrorMsg = loginErrorMsg;
	}

	/**
	 * @return 获取用户注册时的渠道编号
	 */
	public int getChannelId() {
		return channelId;
	}

	/**
	 * @param 设置用户注册时的渠道编号
	 */
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return 获取用户注册时的渠道名称
	 */
	public String getChannelName() {
		return channelName;
	}

	/**
	 * @param 设置用户注册时的渠道名称
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	/**
	 * @return 获取登录验证串
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param 设置登录验证串
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * @return the channelUserId
	 */
	public String getChannelUserId() {
		return channelUserId;
	}

	/**
	 * @param channelUserId
	 *            the channelUserId to set
	 */
	public void setChannelUserId(String channelUserId) {
		this.channelUserId = channelUserId;
	}

	/**
	 * @return the channelUserName
	 */
	public String getChannelUserName() {
		return channelUserName;
	}

	/**
	 * @param channelUserName
	 *            the channelUserName to set
	 */
	public void setChannelUserName(String channelUserName) {
		this.channelUserName = channelUserName;
	}
}
