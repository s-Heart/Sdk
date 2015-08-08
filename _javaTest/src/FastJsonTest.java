import com.alibaba.fastjson.JSON;

public class FastJsonTest {

	public static void main(String[] args) {
		String jsonString = "{\"memberId\":32608510,\"username\":\"ym1988ym\",\"nickname\":\"当乐_小牧\",\"gender\":\"男\",\"level\":11,\"avatar_url\":\"http://d.cn/images/item/35/002.gif\",\"created_date\":1346140985873,\"token\":\"F9A0F6A0E0D4564F56C483165A607735FA4F324\",\"error_code\":0}";
		DownjoyReturnJson jsonObject = JSON.parseObject(jsonString, DownjoyReturnJson.class);
		System.out.println(jsonObject.getMemberId());
		System.out.println(jsonObject.getToken());

		String jsonString2 = "{\"error_code\":211,\"error_msg\":\"app_key 错误\"} ";
		int beginIndex=jsonString2.indexOf("\":\"")+3;
		int endIndex=jsonString2.indexOf("\"}");
		String errormsg=jsonString2.substring(beginIndex, endIndex);
		System.out.println(errormsg);
		
		String jsonString3="{\"code\":5,\"msg\":\"sign\u65e0\u6548\",\"data\":[]}";
		KuaiYongReturnJson jsonObject3=JSON.parseObject(jsonString3, KuaiYongReturnJson.class);
		KuaiYongReturnJson json= (KuaiYongReturnJson) JSON.parseArray("[\"aaa\":1]",KuaiYongReturnJson.class);
		if (json==null) {
			System.out.println("解析失败");
		}
		System.out.println(jsonObject3);
	}
}
