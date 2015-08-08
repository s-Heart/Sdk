package uld.sdk.tools;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;

/**
 * 数据库操作类 2012
 */
public class SQLHelper {
	/**
	 * 驱动
	 */
	public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	/**
	 * 不允许实例化该类
	 */
	private SQLHelper() {
	}

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * 获取一个数据库连接
	 * 
	 * @param connectionString
	 *            包含用户密码的连接串如：jdbc:sqlserver://127.0.0.1:1433;databaseName=WHV1; user =sa;password=123456;
	 * @return 数据库连接
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection(String connectionString) throws SQLException, ClassNotFoundException {
		// 获取驱动,这里使用的是 sqljdbc_1.2.2828.100_chs.exe,不同版本的驱动,语句有所不同
		try {
			return DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 获取一个 Statement 该 Statement 已经设置数据集 可以滚动,可以更新
	 * 
	 * @param connectionString
	 *            数据库连接字符串
	 * @return 如果获取失败抛出异常
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static Statement getStatement(String connectionString) throws SQLException, ClassNotFoundException {
		Connection conn = getConnection(connectionString);
		try {
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			closeConnection(conn);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 获取一个 Statement 该 Statement 已经设置数据集 可以滚动,可以更新
	 * 
	 * @param conn
	 *            数据库连接
	 * @return 如果获取失败抛出异常
	 * @throws SQLException
	 */
	private static Statement getStatement(Connection conn) throws SQLException {
		try {
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 获取一个带参数的 PreparedStatement 该 PreparedStatement 已经设置数据集 可以滚动,可以更新
	 * 
	 * @param connectionString
	 *            数据库连接字符串
	 * @param cmdText
	 *            需要 ? 参数的 SQL 语句
	 * @param cmdParams
	 *            SQL 语句的参数表
	 * @return 如果获取失败抛出异常
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static PreparedStatement getPreparedStatement(String connectionString, String cmdText, Object... cmdParams) throws SQLException, ClassNotFoundException {
		Connection conn = getConnection(connectionString);
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement(cmdText, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			int i = 1;
			for (Object item : cmdParams) {
				pstmt.setObject(i, item);
				i++;
			}
			return pstmt;
		} catch (SQLException e) {
			closeConnection(conn);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 获取一个带参数的 PreparedStatement 该 PreparedStatement 已经设置数据集 可以滚动,可以更新
	 * 
	 * @param conn
	 *            数据库连接
	 * @param cmdText
	 *            需要 ? 参数的 SQL 语句
	 * @param cmdParams
	 *            SQL 语句的参数表
	 * @return 如果获取失败抛出异常
	 * @throws SQLException
	 */
	private static PreparedStatement getPreparedStatement(Connection conn, String cmdText, Object... cmdParams) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			int i = 1;
			for (Object item : cmdParams) {
				pstmt = conn.prepareStatement(cmdText, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				pstmt.setObject(i, item);
				i++;
			}
			return pstmt;
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 获取CallableStatement
	 * 
	 * @param connectionString
	 *            数据库连接串
	 * @param procCmdText
	 *            存储过程命令，如：
	 * @param cmdParams
	 * @param cmdOutParas
	 * @return 如果失败将抛出异常
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static CallableStatement getCallableStatement(String connectionString, String procCmdText, Object[] cmdParams, int[] cmdOutParas) throws SQLException, ClassNotFoundException {
		Connection conn = getConnection(connectionString);
		try {
			CallableStatement cstmt = null;
			cstmt = conn.prepareCall(procCmdText);
			int i = 1;
			if (isReturnCall(procCmdText)) {
				cstmt.registerOutParameter(i++, java.sql.Types.INTEGER);
			}
			for (Object item : cmdParams) {
				cstmt.setObject(i, item);
				i++;
			}
			for (int item : cmdOutParas) {
				cstmt.registerOutParameter(i, item);
				i++;
			}
			return cstmt;
		} catch (SQLException e) {
			closeConnection(conn);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 获取CallableStatement
	 * 
	 * @param connectionString
	 *            数据库连接字符串
	 * @param procCmdText
	 *            存储过程命令
	 * @param cmdParams
	 * @param cmdOutParas
	 * @return 如果失败将抛出异常
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static CallableStatement getCallableStatement(String connectionString, String procCmdText, Map<String, Object> cmdParams, Map<String, Integer> cmdOutParas) throws SQLException,
			ClassNotFoundException {
		Connection conn = getConnection(connectionString);
		try {
			CallableStatement cstmt = conn.prepareCall(procCmdText);
			if (isReturnCall(procCmdText)) {
				cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			}
			Iterator<Entry<String, Object>> iter = cmdParams.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Object> entry = (Entry<String, Object>) iter.next();
				cstmt.setObject(entry.getKey().toString(), entry.getValue());
			}

			Iterator<Entry<String, Integer>> iterOut = cmdOutParas.entrySet().iterator();
			while (iterOut.hasNext()) {
				Map.Entry<String, Integer> entry = (Entry<String, Integer>) iterOut.next();
				cstmt.registerOutParameter(entry.getKey().toString(), Integer.parseInt(entry.getValue().toString()));
			}
			return cstmt;
		} catch (SQLException e) {
			closeConnection(conn);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 执行 SQL 语句,返回结果为整型 主要用于执行非查询语句
	 * 
	 * @param cmdText
	 *            SQL 语句
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void execSql(String connectionString, String cmdText) throws SQLException, ClassNotFoundException {
		Statement stmt = getStatement(connectionString);
		try {
			stmt.executeUpdate(cmdText);
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		} finally {
			closeAll(stmt);
		}
	}

	/**
	 * 执行 SQL 语句,返回结果为整型 主要用于执行非查询语句
	 * 
	 * @param cmdText
	 *            SQL 语句
	 */
	public static void execSql(Connection conn, String cmdText) throws SQLException {
		Statement stmt = getStatement(conn);
		try {
			stmt.executeUpdate(cmdText);
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		} finally {
			closeAll(stmt);
		}
	}

	/**
	 * 执行 SQL 语句,返回结果为整型 主要用于执行非查询语句
	 * 
	 * @param connectionString
	 * 
	 * @param cmdText
	 *            需要 ? 参数的 SQL 语句
	 * @param cmdParams
	 *            SQL 语句的参数表
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void execSql(String connectionString, String cmdText, Object... cmdParams) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = getPreparedStatement(connectionString, cmdText, cmdParams);
		try {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		} finally {
			closeAll(pstmt);
		}
	}

	/**
	 * 执行 SQL 语句,返回结果为整型 主要用于执行非查询语句
	 * 
	 * @param conn
	 *            数据库连接
	 * @param cmdText
	 *            需要 ? 参数的 SQL 语句
	 * @param cmdParams
	 *            SQL 语句的参数表
	 */
	public static void execSql(Connection conn, String cmdText, Object... cmdParams) throws SQLException {
		PreparedStatement pstmt = getPreparedStatement(conn, cmdText, cmdParams);
		try {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		} finally {
			closeAll(pstmt);
		}
	}

	/**
	 * 返回结果集的第一行的一列的值,其他忽略
	 * 
	 * @param cmdText
	 *            SQL 语句
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Object execScalar(String connectionString, String cmdText) throws SQLException, ClassNotFoundException {
		ResultSet rs = query(connectionString, cmdText);
		try {
			return buildScalar(rs);
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		} finally {
			closeAll(rs);
		}
	}

	/**
	 * 返回结果集的第一行的一列的值,其他忽略
	 * 
	 * @param conn
	 *            数据库连接
	 * @param cmdText
	 *            SQL 语句
	 * @return
	 * @throws Exception
	 */
	public static Object execScalar(Connection conn, String cmdText) throws Exception {
		ResultSet rs = query(conn, cmdText);
		try {
			return buildScalar(rs);
		} catch (Exception e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new Exception("数据库异常", e);
		} finally {
			closeAll(rs);
		}
	}

	/**
	 * 返回结果集的第一行的一列的值,其他忽略
	 * 
	 * @param cmdText
	 *            需要 ? 参数的 SQL 语句
	 * @param cmdParams
	 *            SQL 语句的参数表
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Object execScalar(String connectionString, String cmdText, Object... cmdParams) throws SQLException, ClassNotFoundException {
		ResultSet rs = query(connectionString, cmdText, cmdParams);
		try {
			return buildScalar(rs);
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		} finally {
			closeAll(rs);
		}
	}

	/**
	 * 返回结果集的第一行的一列的值,其他忽略
	 * 
	 * @param conn
	 *            数据库连接
	 * @param cmdText
	 *            需要 ? 参数的 SQL 语句
	 * @param cmdParams
	 *            SQL 语句的参数表
	 * @return
	 */
	public static Object execScalar(Connection conn, String cmdText, Object... cmdParams) throws Exception {
		ResultSet rs = query(conn, cmdText, cmdParams);
		try {
			return buildScalar(rs);
		} catch (Exception e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new Exception("数据库异常", e);
		} finally {
			closeAll(rs);
		}
	}

	/**
	 * 返回结果集 需要在外面进行关闭操作 SQLHelper.closeAll(ResultSet);
	 * 
	 * @param cmdText
	 *            SQL 语句
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet query(String connectionString, String cmdText) throws SQLException, ClassNotFoundException {
		Statement stmt = getStatement(connectionString);
		try {
			return stmt.executeQuery(cmdText);
		} catch (SQLException e) {
			closeAll(stmt);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 返回结果集 需要在外面进行关闭操作 SQLHelper.closeAll(ResultSet);
	 * 
	 * @param conn
	 * @param cmdText
	 *            SQL 语句
	 * @return
	 */
	public static ResultSet query(Connection conn, String cmdText) throws SQLException {
		Statement stmt = getStatement(conn);
		try {
			return stmt.executeQuery(cmdText);
		} catch (SQLException e) {
			closeAll(stmt);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 返回结果集 需要在外面进行关闭操作 SQLHelper.closeAll(ResultSet);
	 * 
	 * @param cmdText
	 *            需要 ? 参数的 SQL 语句
	 * @param cmdParams
	 *            SQL 语句的参数表
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet query(String connectionString, String cmdText, Object... cmdParams) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = getPreparedStatement(connectionString, cmdText, cmdParams);
		try {
			return pstmt.executeQuery();
		} catch (SQLException e) {
			closeAll(pstmt);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 返回结果集 需要在外面进行关闭操作 SQLHelper.closeAll(ResultSet);
	 * 
	 * @param conn
	 *            数据库连接
	 * @param cmdText
	 *            需要 ? 参数的 SQL 语句
	 * @param cmdParams
	 *            SQL 语句的参数表
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet query(Connection conn, String cmdText, Object... cmdParams) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = getPreparedStatement(conn, cmdText, cmdParams);
		try {
			return pstmt.executeQuery();
		} catch (SQLException e) {
			closeAll(pstmt);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	private static Object buildScalar(ResultSet rs) throws SQLException {
		if (rs != null && rs.next()) {
			return rs.getObject(1);
		} else {
			return null;
		}
	}

	/**
	 * 执行存储过程，返回结果集 需要在外面进行关闭操作 SQLHelper.closeAll(ResultSet);
	 * 
	 * @param connectionString
	 * @param storedProcName
	 * @param commandParameters
	 * @param commandOutParas
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet runProcedure(String connectionString, String storedProcName, Object[] commandParameters, int[] commandOutParas) throws SQLException, ClassNotFoundException {
		CallableStatement cstmt = getCallableStatement(connectionString, storedProcName, commandParameters, commandOutParas);
		try {
			return cstmt.executeQuery();
		} catch (SQLException e) {
			closeAll(cstmt);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 执行存储过程，返回结果集 需要在外面进行关闭操作 SQLHelper.closeAll(ResultSet);
	 * 
	 * @param connectionString
	 * @param storedProcName
	 * @param commandParameters
	 * @param commandOutParas
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet runProcedure(String connectionString, String storedProcName, Map<String, Object> commandParameters, Map<String, Integer> commandOutParas) throws SQLException,
			ClassNotFoundException {
		CallableStatement cstmt = getCallableStatement(connectionString, storedProcName, commandParameters, commandOutParas);
		try {
			return cstmt.executeQuery();
		} catch (SQLException e) {
			closeAll(cstmt);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 执行存储过程，返回结果集 需要在外面进行关闭操作 SQLHelper.closeAll(ResultSet);
	 * 
	 * @param connectionString
	 * @param storedProcName
	 * @param commandParameters
	 * @param commandOutParas
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static ResultSet runProcedure(String connectionString, String storedProcName, Map<String, Object> commandParameters, OutParameters outParameters) throws SQLException,
			ClassNotFoundException {
		CallableStatement cstmt = getCallableStatement(connectionString, storedProcName, commandParameters, outParameters.getOutParmTypes());
		try {
			return cstmt.executeQuery();
		} catch (SQLException e) {
			closeAll(cstmt);
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		}
	}

	/**
	 * 执行存储过程
	 * 
	 * @param connectionString
	 * @param storedProcName
	 * @param commandParameters
	 * @param commandOutParas
	 *            length必须为2才能得到返回值，且第一个输入参数为返回值 ，整型 原因：java执行存储过程不支持ReturnValue类型，为了将数据库中的主键Id传出， 在存储过程中需定义2个输出参数 ErrNo int 和 ErrMsg String，并且将返回值传给ErrNo 这样执行此方法就可以得到数据库中的返回值了
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static int runProcedureNonQuery(String connectionString, String storedProcName, Object[] commandParameters, int[] commandOutParas) throws SQLException, ClassNotFoundException {
		CallableStatement cstmt = getCallableStatement(connectionString, storedProcName, commandParameters, commandOutParas);
		try {
			cstmt.execute();
			int retValue = 0;
			if (isReturnCall(storedProcName)) {
				retValue = cstmt.getInt(1);
			}
			return retValue;
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		} finally {
			closeAll(cstmt);
		}
	}

	public static int runProcedureNonQuery(String connectionString, String storedProcName, Object[] commandParameters, OutParameters outParameters) throws SQLException, ClassNotFoundException {
		CallableStatement cstmt = getCallableStatement(connectionString, storedProcName, commandParameters, outParameters.getOutParmTypeAry());
		try {
			cstmt.execute();
			int retValue = 0;
			if (isReturnCall(storedProcName)) {
				retValue = cstmt.getInt(1);
			}

			int inParmsLength = commandParameters.length;
			int outParmsLength = outParameters.getOutParmTypeAry().length;
			for (int i = 0; i < outParmsLength; i++) {
				outParameters.getOutParmValueAry()[outParmsLength - i - 1] = cstmt.getObject(inParmsLength + outParmsLength - i);
			}
			return retValue;
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		} finally {
			closeAll(cstmt);
		}
	}

	/**
	 * 执行存储过程
	 * 
	 * @param connectionString
	 * @param storedProcName
	 * @param commandParameters
	 * @param commandOutParas
	 *            length必须为2才能得到返回值，且第一个输入参数为返回值 ，整型 原因：java执行存储过程不支持ReturnValue类型，为了将数据库中的主键Id传出， 在存储过程中需定义2个输出参数 ErrNo int 和 ErrMsg String，并且将返回值传给ErrNo 这样执行此方法就可以得到数据库中的返回值了
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static int runProcedureNonQuery(String connectionString, String storedProcName, Map<String, Object> commandParameters, Map<String, Integer> commandOutParas) throws SQLException,
			ClassNotFoundException {
		CallableStatement cstmt = getCallableStatement(connectionString, storedProcName, commandParameters, commandOutParas);
		try {
			cstmt.execute();
			int retValue = 0;
			if (isReturnCall(storedProcName)) {
				retValue = cstmt.getInt(1);
			}
			return retValue;
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		} finally {
			closeAll(cstmt);
		}
	}

	/**
	 * 执行存储过程
	 * 
	 * @param connectionString
	 * @param storedProcName
	 * @param commandParameters
	 * @param outParameters
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static int runProcedureNonQuery(String connectionString, String storedProcName, Map<String, Object> commandParameters, OutParameters outParameters) throws SQLException,
			ClassNotFoundException {
		CallableStatement cstmt = getCallableStatement(connectionString, storedProcName, commandParameters, outParameters.getOutParmTypes());
		try {
			cstmt.execute();
			int retValue = 0;
			if (isReturnCall(storedProcName)) {
				retValue = cstmt.getInt(1);
			}
			for (String key : outParameters.getOutParmTypes().keySet()) {
				outParameters.putOutParmsValue(key, cstmt.getObject(key));
			}
			return retValue;
		} catch (SQLException e) {
			LogHelper.log(Level.SEVERE, e.getMessage(), e);
			throw new SQLException("数据库异常", e);
		} finally {
			closeAll(cstmt);
		}
	}

	/**
	 * 关闭对象
	 * 
	 * @param obj
	 */
	public static void close(Object obj) {
		if (obj == null) {
			return;
		}
		try {
			if (obj instanceof CallableStatement) {
				CallableStatement tempObj = ((CallableStatement) obj);
				if (!tempObj.isClosed()) {
					tempObj.close();
				}
			} else if (obj instanceof PreparedStatement) {
				PreparedStatement tempObj = ((PreparedStatement) obj);
				if (!tempObj.isClosed()) {
					tempObj.close();
				}
			} else if (obj instanceof Statement) {
				Statement tempObj = ((Statement) obj);
				if (!tempObj.isClosed()) {
					tempObj.close();
				}
			} else if (obj instanceof ResultSet) {
				ResultSet tempObj = ((ResultSet) obj);
				if (!tempObj.isClosed()) {
					tempObj.close();
				}
			} else if (obj instanceof Connection) {
				Connection tempObj = ((Connection) obj);
				if (!tempObj.isClosed()) {
					tempObj.close();
				}
			}
		} catch (SQLException e) {
			// LogHelper.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * 关闭数据库连接对象
	 * 
	 * @param obj
	 */
	public static void closeConnection(Object obj) {
		if (obj == null) {
			return;
		}
		Connection conn = null;
		try {

			if (obj instanceof CallableStatement) {
				conn = ((CallableStatement) obj).getConnection();
			} else if (obj instanceof PreparedStatement) {
				conn = ((PreparedStatement) obj).getConnection();
			} else if (obj instanceof Statement) {
				conn = ((Statement) obj).getConnection();
			} else if (obj instanceof ResultSet) {
				Statement statement = ((ResultSet) obj).getStatement();
				if (statement != null) {
					conn = statement.getConnection();
				}
			} else if (obj instanceof Connection) {
				conn = ((Connection) obj);
			}

			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// LogHelper.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	/**
	 * 关闭数据库连接对象和本身 1、先关闭ResultSet（如果不为null，且没有关） 2、关闭Statement（如果不为null，且没有关） 3、关闭Connection（如果不为null，且没有关）
	 * 
	 * @param obj
	 *            可以为ResultSet Statement Connection
	 */
	public static void closeAll(Object obj) {
		if (obj == null) {
			return;
		}
		Connection conn = null;
		ResultSet rs = null;
		try {
			if (obj instanceof CallableStatement) {
				CallableStatement tempObj = ((CallableStatement) obj);
				conn = tempObj.getConnection();
				rs = tempObj.getResultSet();
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (!tempObj.isClosed()) {
					tempObj.close();
				}
			} else if (obj instanceof PreparedStatement) {
				PreparedStatement tempObj = ((PreparedStatement) obj);
				conn = tempObj.getConnection();
				rs = tempObj.getResultSet();
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (!tempObj.isClosed()) {
					tempObj.close();
				}
			} else if (obj instanceof Statement) {
				Statement tempObj = ((Statement) obj);
				conn = tempObj.getConnection();
				rs = tempObj.getResultSet();
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (!tempObj.isClosed()) {
					tempObj.close();
				}
			} else if (obj instanceof ResultSet) {
				ResultSet tempObj = ((ResultSet) obj);
				Statement statement = tempObj.getStatement();
				if (statement != null) {
					conn = statement.getConnection();
				}
				if (!tempObj.isClosed()) {
					tempObj.close();
				}
			} else if (obj instanceof Connection) {
				Connection tempObj = ((Connection) obj);
				conn = tempObj;
				if (!tempObj.isClosed()) {
					tempObj.close();
				}
			}

			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// LogHelper.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	/**
	 * 是否带有返回值存储过程字符串
	 * 
	 * @param callProString
	 *            存储过程字符串
	 * @return
	 */
	public static boolean isReturnCall(String callProString) {
		boolean retVal = false;
		if (callProString.indexOf("? =") > 0 || callProString.indexOf("?=") > 0) {
			retVal = true;
		}
		return retVal;
	}
}
