package uld.sdk.bll;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import uld.sdk.tools.Utility;

public class UserWebApi {

	private static UserWebApi sInstance = new UserWebApi();

	private UserWebApi() {

	}

	public static UserWebApi getInstance() {
		return sInstance;
	}

	public int requestHttpPost(String body) {
		int responseCode = 0;
		try {
			/***
			 * 对象参数 1地址
			 * 外网测试的地址是http://218.241.144.114:42085/api/GameLog?code=123456
			 * 正式运营后的地址是http://115.182.49.81:8099/api/GameLog?code=
			 * NWFTcDVwV3c1NldlNW8ydTVMcVM2WU9vNVlxbw 2方法类型
			 */
			String URL = "";
			Date date = new Date();
			Date openServerDate = new Date(2013, 11, 26);
			openServerDate.setHours(11);

			// if(date.after(openServerDate)){
			// 正式地址
			URL = "http://115.182.49.81:8099/api/GameLog?code=NWFTcDVwV3c1NldlNW8ydTVMcVM2WU9vNVlxbw";
			// } else{
			// 测试地址、do nothing
			// URL="http://218.241.144.114:42085/api/GameLog?code=123456";
			// }

			RequestHttp rh = new RequestHttp(URL, "POST");
			responseCode = rh.request(body);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseCode;
	}

	public class RequestHttp {
		private URL url;
		private String method;

		public RequestHttp(String urlpath, String method) throws IOException {
			url = new URL(urlpath);
			this.method = method;
		}

		/**
		 * 向服务器发送post请求
		 * 
		 * @param paramliststr
		 *            参数列表的字符串
		 * @return 返回http状态吗
		 * @throws IOException
		 */
		public int request(String paramliststr) throws IOException {
			HttpURLConnection httpconnect;
			httpconnect = (HttpURLConnection) url.openConnection();
			httpconnect.setRequestMethod(method);
			httpconnect.setDoOutput(true);
			httpconnect.setDoInput(true);
			httpconnect.setUseCaches(false);
			httpconnect.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					httpconnect.getOutputStream());
			osw.write(paramliststr);
			osw.flush();
			osw.close();
			return httpconnect.getResponseCode();
		}

		public URL getUrl() {
			return url;
		}

		public void setUrl(URL url) {
			this.url = url;
		}
	}

}