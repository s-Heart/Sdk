package uld.sdk.nineone;

import java.io.Serializable;

public class LoginError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 错误码(0=失败，1=成功(SessionId 有效)，2= AppId 无效，3=Act 无效，4=参数无效，5= Sign 无效，11=SessionId 无效)
	 */
	public String ErrorCode;
	public String ErrorDesc;
}
