package uld.sdk.tools;

/**
 * 错误类
 */
public class MyErr {
	/**
	 * 错误代码
	 */
	private int errNo;
	/**
	 * 错误信息
	 */
	private String errMsg;

	public MyErr() {
	}

	/**
	 * 构造错误类
	 * 
	 * @param errNo
	 *            错误代码
	 * @param errMsg
	 *            错误信息
	 */
	public MyErr(int errNo, String errMsg) {
		this.errNo = errNo;
		this.errMsg = errMsg;
	}

	/**
	 * 设置错误
	 * 
	 * @param errNo
	 *            错误代码
	 * @param errMsg
	 *            错误信息
	 */
	public void setErr(int errNo, String errMsg) {
		this.errNo = errNo;
		this.errMsg = errMsg;
	}

	public int getErrNo() {
		return errNo;
	}

	public void setErrNo(int errNo) {
		this.errNo = errNo;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public boolean findErr() {
		return errNo < 0 ? true : false;
	}

	public void checkErr() throws Exception {
		if (errNo < 0) {
			throw new Exception(errMsg);
		}
	}

	public static void checkErr(int errNo, String errMsg) throws Exception {
		if (errNo < 0) {
			throw new Exception(errMsg);
		}
	}

	public void reset() {
		this.errNo = 0;
		this.errMsg = "";
	}
}
