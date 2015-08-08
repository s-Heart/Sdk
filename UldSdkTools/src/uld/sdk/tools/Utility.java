package uld.sdk.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Environment;


public class Utility {

	/**
	 * 加密KEY
	 */
	private final static String ENCODEKEY = "UldM~!@*";

	/**
	 * 判断是否为手机号码
	 */
	public static boolean isMobile(String mobiles) {
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(mobiles);
		return matcher.matches();
	}

	/**
	 * 获取格式化后的当前时间
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getFormatDate() {
		return getFormatDate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取格式化后的当前时间
	 * 
	 * @param pattern
	 *            格式化字符
	 * @return
	 */
	public static String getFormatDate(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
		return sdf.format(Calendar.getInstance().getTime());
	}

	/**
	 * 获取格式化后的当前时间
	 * 
	 * @param date
	 *            传入的时间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getFormatDate(Date date) {
		return getFormatDate("yyyy-MM-dd HH:mm:ss", date);
	}

	/**
	 * 获取数据库时间
	 * 
	 * @param date
	 *            传入的时间
	 * @return 0000-00-00 00:00:00.000 .000是毫秒数
	 */
	public static Timestamp getSqlDate(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 获取格式化后的当前时间
	 * 
	 * @param pattern
	 *            格式化字符
	 * @param date
	 *            传入的时间
	 * @return
	 */
	public static String getFormatDate(String pattern, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
		return sdf.format(date);
	}

	/**
	 * 字符串转换到时间格式
	 * 
	 * @param dateStr
	 *            需要转换的字符串
	 * @param formatStr
	 *            需要格式的目标字符串 举例 yyyy-MM-dd HH:mm:ss
	 * @return Date 返回转换后的时间
	 * @throws ParseException
	 *             转换异常
	 */
	public static Date parseDate(String dateStr, String formatStr) {
		if (isEmpty(dateStr)) {
			return null;
		}
		if (isEmpty(formatStr)) {
			formatStr = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr, Locale.CHINA);
		Date retDate = null;
		try {
			retDate = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retDate;
	}

	/**
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(String testStr) {
		return (null == testStr || testStr.length() == 0 || "null".equalsIgnoreCase(testStr));
	}

	// /**
	// * 判断字符串是否为空
	// */
	// public static boolean isNullOrEmpty(String testStr) {
	// return (null == testStr || testStr.length() == 0 || "null"
	// .equalsIgnoreCase(testStr));
	// }

	/**
	 * DES加密
	 * 
	 * @param encodeStr
	 *            需要加密的字符串
	 * @param key
	 *            加密需要的Key
	 * @throws Exception
	 */
	public static String encodeDES(String encodeStr, String key) {
		try {
			return DESCBC.encrypDes(key, encodeStr);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * DES加密
	 * 
	 * @param encodeStr
	 *            需要加密的字符串
	 * @throws Exception
	 */
	public static String encodeDES(String encodeStr) {
		try {
			return DESCBC.encrypDes(ENCODEKEY, encodeStr);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * DES解密
	 * 
	 * @param decodeStr
	 *            需要解密的字符串
	 * @param key
	 *            解密需要的Key
	 * @throws Exception
	 */
	public static String decodeDES(String decodeStr, String key) {
		try {
			return DESCBC.parseDes(key, decodeStr);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * DES解密
	 * 
	 * @param decodeStr
	 *            需要解密的字符串
	 * @param key
	 *            解密需要的Key
	 * @throws Exception
	 */
	public static String decodeDES(String decodeStr) {
		try {
			return DESCBC.parseDes(ENCODEKEY, decodeStr);
		} catch (Exception e) {
			return "";
		}

	}

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String encodeMD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 根据类名和方法名执行方法
	 * 
	 * @param classPathName
	 *            类全名，包括包名
	 * @param methodName
	 *            调用的方法名称
	 * @param parmTypeList
	 *            调用的方法参数类型列表
	 * @param parmList
	 *            调用的方法参数列表
	 */
	public static Object Invoke(String classPathName, String methodName, Class<?>[] parmTypeList, Object[] parmList) throws Exception {

		Object retValue = null;
		Class<?> c = Class.forName(classPathName);
		Object obj = c.newInstance();
		Method method = c.getMethod(methodName, parmTypeList);
		retValue = method.invoke(obj, parmList);
		return retValue;
	}

	/**
	 * 网页抓取方法，utf-8编码，10秒超时
	 * 
	 * @param urlString
	 *            要抓取的url地址
	 * @param charset
	 *            网页编码方式
	 * @param timeout
	 *            超时时间，单位毫秒
	 * @return 抓取的网页内容
	 * @throws IOException
	 *             抓取异常
	 */
	public static String getWebContent(String urlString) throws IOException {
		return getWebContent(urlString, "utf-8", 10000);
	}

	/**
	 * 网页抓取方法，10秒超时
	 * 
	 * @param urlString
	 *            要抓取的url地址
	 * @param charset
	 *            网页编码方式
	 * @return 抓取的网页内容
	 * @throws IOException
	 *             抓取异常
	 */
	public static String getWebContent(String urlString, final String charset) throws IOException {
		return getWebContent(urlString, charset, 10000);
	}

	/**
	 * 网页抓取方法
	 * 
	 * @param urlString
	 *            要抓取的url地址
	 * @param charset
	 *            网页编码方式
	 * @param timeout
	 *            超时时间，单位毫秒
	 * @return 抓取的网页内容
	 * @throws IOException
	 *             抓取异常
	 */
	public static String getWebContent(String urlString, final String charset, int timeout) throws IOException {
		if (urlString == null || urlString.length() == 0) {
			return null;
		}
		urlString = (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString : ("http://" + urlString).intern();
		URL url = new URL(urlString);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");// 增加报头，模拟浏览器，防止屏蔽
		// 只接受text/html类型，当然也可以接受图片,pdf,*/*任意，就是tomcat/conf/web里面定义那些
		// conn.setRequestProperty("Accept", "text/html");

		conn.setConnectTimeout(timeout);
		try {
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		InputStream input = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, charset));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		if (reader != null) {
			reader.close();
		}
		if (conn != null) {
			conn.disconnect();
		}
		return sb.toString();
	}

	private static Calendar calendar = Calendar.getInstance();

	public static int getDateYear(Date date) {
		if (date == null || date.equals(parseDate("1900-1-1", "yyyy-MM-dd"))) {
			return 0;
		}
		calendar.clear();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getDateMonth(Date date) {
		if (date == null || date.equals(parseDate("1900-1-1", "yyyy-MM-dd"))) {
			return 0;
		}
		calendar.clear();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR) * 100 + calendar.get(Calendar.MONTH) + 1;
	}

	public static int getDateDay(Date date) {
		if (date == null || date.equals(parseDate("1900-1-1", "yyyy-MM-dd"))) {
			return 0;
		}
		calendar.clear();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Date getDbMinDate() {
		return Utility.parseDate("1900-1-1", "yyyy-MM-dd");
	}

	public static int getInt(Object object) {
		int retValue = 0;
		if (null != object && !isEmpty(object.toString())) {
			try {
				retValue = Integer.parseInt(object.toString().trim());
			} catch (Exception e) {
			}
		}
		return retValue;
	}

	public static int getInt(String str) {
		int retValue = 0;
		if (!isEmpty(str)) {
			try {
				retValue = Integer.parseInt(str.trim());
			} catch (Exception e) {
			}
		}
		return retValue;
	}

	public static boolean getBoolean(Object object) {
		boolean retValue = false;
		if (null != object && !isEmpty(object.toString())) {
			try {
				retValue = Boolean.valueOf(object.toString());
			} catch (Exception e) {
			}
		}
		return retValue;
	}

	public static boolean getBoolean(String str) {
		boolean retValue = false;
		if (!isEmpty(str)) {
			try {
				retValue = Boolean.valueOf(str.trim());
			} catch (Exception e) {
			}
		}
		return retValue;
	}

	public static int[] getIntAry(int maxLength) {
		int[] retValue = new int[maxLength];
		for (int i = 0; i < retValue.length; i++) {
			retValue[i] = i;
		}
		return retValue;
	}

	private static int[] iAry = getIntAry(100);
	private static char[] cAry = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	/**
	 * 随机取数组内的数
	 * 
	 * @param iAry
	 * @param indexLength
	 * @return
	 */
	public static int[] getRandom(int[] iAry, int indexLength) {
		int maxValue = iAry.length;
		if (indexLength >= maxValue) {
			indexLength = maxValue - 1;
		}
		int[] retValue = new int[indexLength];
		Random random = new Random();

		// 数组随机下标
		int tempIndex = 0;
		// 当前得到的数量
		int tempCount = 0;
		// 数组随机下标指向的数值
		int tempValue = 0;

		// 当得到数量为getCount时跳出循环,那么数组的前getCount条数据就是要获取的随机值
		while (tempCount < indexLength) {
			// 从当前得到的数量+1开始获取下标
			tempIndex = tempCount + 1 + random.nextInt(maxValue - tempCount - 1);

			// 把取到的值存入数组的前面位置
			tempValue = iAry[tempIndex];
			iAry[tempIndex] = iAry[tempCount];
			iAry[tempCount] = tempValue;

			retValue[tempCount] = tempValue;

			tempCount += 1;
		}
		return retValue;
	}

	/**
	 * 随机取100内的数，
	 * 
	 * @param indexLength
	 * @return
	 */
	public static int[] getRandom(int indexLength) {
		return getRandom(iAry, indexLength);
	}

	/**
	 * 获取100以内随机数
	 * 
	 * @param indexLength
	 * @return
	 */
	public static String getRandomStr(int indexLength) {
		String retValue = "";
		int[] tempAry = getRandom(indexLength);
		for (int i = 0; i < tempAry.length; i++) {
			retValue = retValue + String.valueOf(tempAry[i]);
		}
		return retValue.substring(0, indexLength);
	}

	/**
	 * 获取随机数
	 * 
	 * @param iAry
	 * @param indexLength
	 * @return
	 */
	public static String getRandomStr(int[] iAry, int indexLength) {
		String retValue = "";
		int[] tempAry = getRandom(iAry, indexLength);
		for (int i = 0; i < tempAry.length; i++) {
			retValue = retValue + String.valueOf(tempAry[i]);
		}
		return retValue.substring(0, indexLength);
	}

	/**
	 * 随机取数组内的数
	 * 
	 * @param iAry
	 * @param indexLength
	 * @return
	 */
	public static char[] getRandom(char[] iAry, int indexLength) {
		int maxValue = iAry.length;
		if (indexLength >= maxValue) {
			indexLength = maxValue - 1;
		}
		char[] retValue = new char[indexLength];
		Random random = new Random();

		// 数组随机下标
		int tempIndex = 0;
		// 当前得到的数量
		int tempCount = 0;
		// 数组随机下标指向的数值
		char tempValue;

		// 当得到数量为getCount时跳出循环,那么数组的前getCount条数据就是要获取的随机值
		while (tempCount < indexLength) {
			// 从当前得到的数量+1开始获取下标
			tempIndex = tempCount + 1 + random.nextInt(maxValue - tempCount - 1);

			// 把取到的值存入数组的前面位置
			tempValue = iAry[tempIndex];
			iAry[tempIndex] = iAry[tempCount];
			iAry[tempCount] = tempValue;

			retValue[tempCount] = tempValue;

			tempCount += 1;
		}
		return retValue;
	}

	/**
	 * 随机取26内的英文字母，
	 * 
	 * @param indexLength
	 *            小于26
	 * @return
	 */
	public static char[] getRandomChar(int indexLength) {
		return getRandom(cAry, indexLength);
	}

	/**
	 * 随机取26内的英文字母，
	 * 
	 * @param indexLength
	 *            小于26
	 * @return
	 */
	public static String getRandomLetter(int indexLength) {
		return String.valueOf(getRandom(cAry, indexLength));
	}
	
	/**
	 * 生成排序完成后待签名字符串
	 * @param signMap	待签名map
	 * @param equalString	等号
	 * @param connector 连接符
	 * @return
	 */
	public static String sortContent(Map<String, String> signMap, String equalString, String connector) {
		StringBuffer signsb = new StringBuffer();
		if (!(signMap instanceof TreeMap)) {
			TreeMap<String, String> treeMap = new TreeMap<String, String>();
			Set<Entry<String, String>> signSet = signMap.entrySet();
			Iterator<Entry<String, String>> iterator = signSet.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				String key = entry.getKey();
				String value = entry.getValue();
				if (!Utility.isEmpty(value)) {
					treeMap.put(key, value);
				}
			}
			signMap = treeMap;
		}

		Set<Entry<String, String>> signSet = signMap.entrySet();
		Iterator<Entry<String, String>> iterator = signSet.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value = entry.getValue();
			if (!Utility.isEmpty(value)) {
				signsb.append(key+equalString+value+connector);
			}
		}

		// 消除最后一个连接符
		String signPre = "";
		if (signsb.toString().length() > 0 && !Utility.isEmpty(connector)) {
			signPre = signsb.substring(0, signsb.length() - 1);
		}
		return signPre;
	}

	public static boolean isSDCardExist() {
		return Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED);
	}
}