package uld.sdk.model;

import java.io.Serializable;

public class MessageBody implements Serializable {
	private static final long serialVersionUID = 1L;
	private String classPathName;
	private String methodName;
	private Class<?>[] parmTypeList;
	private Object[] parmList;

	public MessageBody() {
	}

	/**
	 * 构造消息体
	 * 
	 * @param classPathName
	 * @param methodName
	 * @param parmTypeList
	 * @param parmList
	 * @param isVoidRetType
	 */
	public MessageBody(String classPathName, String methodName, Class<?>[] parmTypeList, Object[] parmList) {
		this.classPathName = classPathName;
		this.methodName = methodName;
		this.parmTypeList = parmTypeList;
		this.parmList = parmList;
	}

	public String getClassPathName() {
		return classPathName;
	}

	public void setClassPathName(String classPathName) {
		this.classPathName = classPathName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParmTypeList() {
		return parmTypeList;
	}

	public void setParmTypeList(Class<?>[] parmTypeList) {
		this.parmTypeList = parmTypeList;
	}

	public Object[] getParmList() {
		return parmList;
	}

	public void setParmList(Object[] parmList) {
		this.parmList = parmList;
	}
}
