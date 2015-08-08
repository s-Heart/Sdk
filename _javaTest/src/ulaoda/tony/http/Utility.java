package ulaoda.tony.http;

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
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 *        格式化字符
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
	 *        传入的时间
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getFormatDate(Date date) {
		return getFormatDate("yyyy-MM-dd HH:mm:ss", date);
	}

	/**
	 * 获取数据库时间
	 * 
	 * @param date
	 *        传入的时间
	 * @return 0000-00-00 00:00:00.000 .000是毫秒数
	 */
	public static Timestamp getSqlDate(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 获取格式化后的当前时间
	 * 
	 * @param pattern
	 *        格式化字符
	 * @param date
	 *        传入的时间
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
	 *        需要转换的字符串
	 * @param formatStr
	 *        需要格式的目标字符串 举例 yyyy-MM-dd HH:mm:ss
	 * @return Date 返回转换后的时间
	 * @throws ParseException
	 *         转换异常
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
	
	public static String encodeMD516(String inStr) {
		return encodeMD5(inStr).substring(8,24);
	}

	/**
	 * 根据类名和方法名执行方法
	 * 
	 * @param classPathName
	 *        类全名，包括包名
	 * @param methodName
	 *        调用的方法名称
	 * @param parmTypeList
	 *        调用的方法参数类型列表
	 * @param parmList
	 *        调用的方法参数列表
	 */
	public static Object Invoke(String classPathName, String methodName, Class<?>[] parmTypeList, Object[] parmList)
			throws Exception {

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
	 *        要抓取的url地址
	 * @param charset
	 *        网页编码方式
	 * @param timeout
	 *        超时时间，单位毫秒
	 * @return 抓取的网页内容
	 * @throws IOException
	 *         抓取异常
	 */
	public static String getWebContent(String urlString) throws IOException {
		return getWebContent(urlString, "utf-8", 10000);
	}

	/**
	 * 网页抓取方法，10秒超时
	 * 
	 * @param urlString
	 *        要抓取的url地址
	 * @param charset
	 *        网页编码方式
	 * @return 抓取的网页内容
	 * @throws IOException
	 *         抓取异常
	 */
	public static String getWebContent(String urlString, final String charset) throws IOException {
		return getWebContent(urlString, charset, 10000);
	}

	/**
	 * 网页抓取方法
	 * 
	 * @param urlString
	 *        要抓取的url地址
	 * @param charset
	 *        网页编码方式
	 * @param timeout
	 *        超时时间，单位毫秒
	 * @return 抓取的网页内容
	 * @throws IOException
	 *         抓取异常
	 */
	public static String getWebContent(String urlString, final String charset, int timeout) throws IOException {
		if (urlString == null || urlString.length() == 0) {
			return null;
		}
		urlString = (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString : ("http://" + urlString)
				.intern();
		URL url = new URL(urlString);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");// 增加报头，模拟浏览器，防止屏蔽
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
		return calendar.get(Calendar.YEAR) * 10000 + (calendar.get(Calendar.MONTH) + 1) * 100
				+ calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Date getDbMinDate() {
		return Utility.parseDate("1900-1-1", "yyyy-MM-dd");
	}

	public static int getInt(Object object) {
		int retValue = 0;
		if (null != object && !isEmpty(object.toString())) {
			try {
				retValue = Integer.valueOf(object.toString());
			} catch (Exception e) {
			}
		}
		return retValue;
	}

	public static int getInt(String str) {
		int retValue = 0;
		if (!isEmpty(str)) {
			try {
				retValue = Integer.valueOf(str.trim());
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
	private static char[] cAry = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

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
	 *        小于26
	 * @return
	 */
	public static char[] getRandomChar(int indexLength) {
		return getRandom(cAry, indexLength);
	}

	/**
	 * 随机取26内的英文字母，
	 * 
	 * @param indexLength
	 *        小于26
	 * @return
	 */
	public static String getRandomLetter(int indexLength) {
		return String.valueOf(getRandom(cAry, indexLength));
	}

	// 简体中文的编码范围从B0A1（45217）一直到F7FE（63486）
	private static int BEGIN = 45217;
	private static int END = 63486;

	// 按照声母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
	// i, u, v都不做声母, 自定规则跟随前面的字母
	private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然',
			'撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', };

	// 二十六个字母区间对应二十七个端点
	// GB2312码汉字区间十进制表示
	private static int[] table = new int[27];

	// 对应首字母区间表
	private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 't', 't', 'w', 'x', 'y', 'z', };

	// 初始化
	static {
		for (int i = 0; i < 26; i++) {
			table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
		}
		table[26] = END;// 区间表结尾
	}

	// ------------------------public方法区------------------------
	/**
	 * 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出
	 */
	public static String cn2py(String SourceStr) {
		String Result = "";
		int StrLength = SourceStr.length();
		int i;
		try {
			for (i = 0; i < StrLength; i++) {
				Result += Char2Initial(SourceStr.charAt(i));
			}
		} catch (Exception e) {
			Result = "";
		}
		return Result;
	}

	// ------------------------private方法区------------------------
	/**
	 * 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0'
	 * 
	 */
	private static String Char2Initial(char ch) {
		// // 对英文字母的处理：小写字母转换为大写，大写的直接返回
		// if (ch >= 'a' && ch <= 'z')
		// //return (char) (ch - 'a' + 'A');
		// return ch;
		
		// @到~包含数字，英文，半角符号。
		if (ch >= ' ' && ch <= '~')
			return String.valueOf(ch);

		// 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，
		// 若不是，则直接返回。
		// 若是，则在码表内的进行判断。
		int gb = gbValue(ch);// 汉字转换首字母

		if ((gb < BEGIN) || (gb > END))// 在码表区间之前，直接返回
			return "";

		int i;
		for (i = 0; i < 26; i++) {// 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
			if ((gb >= table[i]) && (gb < table[i + 1]))
				break;
		}

		if (gb == END) {// 补上GB2312区间最右端
			i = 25;
		}
		return String.valueOf(initialtable[i]); // 在码表区间中，返回首字母
	}

	/**
	 * 取出汉字的编码 cn 汉字
	 */
	private static int gbValue(char ch) {// 将一个汉字（GB2312）转换为十进制表示。
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2)
				return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}
}