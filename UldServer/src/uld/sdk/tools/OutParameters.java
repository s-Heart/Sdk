package uld.sdk.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * 输出参数类
 */
public class OutParameters {

	/**
	 * 输出参数类型列表
	 */
	private Map<String, Integer> outParmTypes = new HashMap<String, Integer>();
	/**
	 * 输出参数返回值列表
	 */
	private Map<String, Object> outParmValues = new HashMap<String, Object>();
	/**
	 * 输出参数类型列表
	 */
	private int[] outParmTypeAry;
	/**
	 * 输出参数返回值列表
	 */
	private Object[] outParmValueAry;

	/**
	 * 获取输出参数名与类型列表
	 * 
	 * @return 输出参数名与类型列表
	 */
	public Map<String, Integer> getOutParmTypes() {
		if (outParmTypes == null) {
			outParmTypes = new HashMap<String, Integer>();
		}
		return outParmTypes;
	}

	/**
	 * 获取输出参数名与返回值列表
	 * 
	 * @return 输出参数名与返回值列表
	 */
	public Map<String, Object> getOutParmValues() {
		if (outParmValues == null) {
			outParmValues = new HashMap<String, Object>();
		}
		return outParmValues;
	}

	/**
	 * 添加输出参数名与类型
	 * 
	 * @param parameterName
	 *            the name of the parameter
	 * @param sqlType
	 *            the JDBC type code defined by <code>java.sql.Types</code>.
	 */
	public void putOutParmType(String parameterName, int sqlType) {
		getOutParmTypes().put(parameterName, sqlType);
	}

	/**
	 * 添加输出参数名与返回值列表
	 * 
	 * @param parameterName
	 *            the name of the parameter
	 * @param retValue
	 *            输出参数的返回值
	 */
	public void putOutParmsValue(String parameterName, Object retValue) {
		getOutParmValues().put(parameterName, retValue);
	}

	/**
	 * 获取输出参数类型
	 * 
	 * @param parameterName
	 *            the name of the parameter
	 * @return sqlType the JDBC type code defined by <code>java.sql.Types</code>
	 *         .
	 */
	public Integer getOutParmType(String parameterName) {
		return getOutParmTypes().get(parameterName);
	}

	/**
	 * 获取输出参数返回值
	 * 
	 * @param parameterName
	 *            the name of the parameter
	 * @return 输出参数返回值
	 */
	public Object getOutParmValue(String parameterName) {
		return getOutParmValues().get(parameterName);
	}

	/**
	 * 获取输出类型列表
	 * 
	 * @return 输出参数类型列表
	 */
	public int[] getOutParmTypeAry() {
		return outParmTypeAry;
	}

	/**
	 * 设置输出参数类型列表
	 */
	public void setOutParmTypeAry(int[] outParmTypeAry) {
		this.outParmTypeAry = outParmTypeAry;
		this.outParmValueAry = new Object[outParmTypeAry.length];
	}

	/**
	 * 获取输出参数返回值列表
	 * 
	 * @return 输出参数返回值列表
	 */
	public Object[] getOutParmValueAry() {
		return outParmValueAry;
	}

	/**
	 * 获取输出参数类型
	 * 
	 * @param parameterName
	 *            the name of the parameter
	 * @return sqlType the JDBC type code defined by <code>java.sql.Types</code>
	 *         .
	 */
	public Integer getOutParmType(Integer parameterIndex) {
		if (parameterIndex < getOutParmTypeAry().length) {
			return getOutParmTypeAry()[parameterIndex];
		} else {
			return 0;
		}
	}

	/**
	 * 获取输出参数返回值
	 * 
	 * @param parameterName
	 *            the name of the parameter
	 * @return 输出参数返回值
	 */
	public Object getOutParmValue(Integer parameterIndex) {

		if (parameterIndex < getOutParmValueAry().length) {
			return getOutParmValueAry()[parameterIndex];
		} else {
			return null;
		}
	}
}
