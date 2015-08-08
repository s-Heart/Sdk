package uld.sdk.bll;

import java.io.Serializable;

public class XiaoMiErrorMsg implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	/**
	 * 错误码(200=成功，1506= cpOrderId 错误，1515=appId 错误，1516=uid 错误，1525= signature 错误)
	 */
	public String ErrCode;
	public String ErrMsg;
}
