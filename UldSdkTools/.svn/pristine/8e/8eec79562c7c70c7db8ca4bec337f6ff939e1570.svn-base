package uld.sdk.model;

import java.io.Serializable;

public class MessageReturn implements Serializable {
	private static final long serialVersionUID = 1L;
	private int errNo = 0;
	private String errMsg = "";
	private Object retObject;
	private int totalCount = 0;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public MessageReturn() {
	}

	public MessageReturn(int errNo, String errMsg) {
		this.errNo = errNo;
		this.errMsg = errMsg;
	}

	public MessageReturn(int errNo, String errMsg, Object retObject) {
		this.errNo = errNo;
		this.errMsg = errMsg;
		this.retObject = retObject;
	}

	public void setErr(int errNo, String errMsg) {
		this.errNo = errNo;
		this.errMsg = errMsg;
	}

	public void setErr(int errNo, String errMsg, Object retObject) {
		this.errNo = errNo;
		this.errMsg = errMsg;
		this.retObject = retObject;
	}

	/**
	 * 0为成功，其它为失败
	 * 
	 * @return
	 */
	public int getErrNo() {
		return errNo;
	}

	/**
	 * 0为成功，其它为失败
	 */
	public void setErrNo(int errNo) {
		this.errNo = errNo;
	}

	/**
	 * 空为成功，其它为失败
	 * 
	 * @return
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * 空为成功，其它为失败
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public boolean findErr() {
		return errNo < 0 ? true : false;
	}

	public Object getRetObject() {
		return retObject;
	}

	public void setRetObject(Object retObject) {
		this.retObject = retObject;
	}
}
